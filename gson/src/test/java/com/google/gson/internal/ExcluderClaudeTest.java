/*
 * Copyright (C) 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.gson.internal;

import static com.google.common.truth.Truth.assertThat;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import org.junit.Test;

/**
 * Unit tests for {@link Excluder}.
 *
 * @author Claude
 */
public class ExcluderClaudeTest {

  // ========== Default Constructor <init>.()V Tests ==========

  @Test
  public void testDefaultExcluderExists() {
    // Test that Excluder.DEFAULT exists and is non-null
    assertThat(Excluder.DEFAULT).isNotNull();
  }

  @Test
  public void testDefaultExcluderIsTypeAdapterFactory() {
    // Excluder implements TypeAdapterFactory
    assertThat(Excluder.DEFAULT).isInstanceOf(com.google.gson.TypeAdapterFactory.class);
  }

  @Test
  public void testDefaultExcluderDoesNotExcludeRegularClass() {
    // Regular class should not be excluded by default
    boolean excluded = Excluder.DEFAULT.excludeClass(String.class, true);
    assertThat(excluded).isFalse();
  }

  @Test
  public void testDefaultExcluderDoesNotExcludeRegularField() throws NoSuchFieldException {
    // Regular public field should not be excluded by default
    Field field = TestFieldClass.class.getDeclaredField("regularField");
    boolean excluded = Excluder.DEFAULT.excludeField(field, true);
    assertThat(excluded).isFalse();
  }

  @Test
  public void testDefaultExcluderExcludesTransientField() throws NoSuchFieldException {
    // Transient field should be excluded by default
    Field field = TestFieldClass.class.getDeclaredField("transientField");
    boolean excluded = Excluder.DEFAULT.excludeField(field, true);
    assertThat(excluded).isTrue();
  }

  @Test
  public void testDefaultExcluderExcludesStaticField() throws NoSuchFieldException {
    // Static field should be excluded by default
    Field field = TestFieldClass.class.getDeclaredField("staticField");
    boolean excluded = Excluder.DEFAULT.excludeField(field, true);
    assertThat(excluded).isTrue();
  }

  // ========== clone.()Lcom/google/gson/internal/Excluder; Tests ==========

  @Test
  public void testCloneReturnsNewInstance() {
    // Clone should return a new Excluder instance, not the same one
    Excluder cloned = Excluder.DEFAULT.withVersion(1.0); // withVersion calls clone internally
    assertThat(cloned).isNotSameInstanceAs(Excluder.DEFAULT);
  }

  @Test
  public void testClonePreservesVersionSetting() {
    // Verify that clone preserves version by chaining with version check
    Excluder excluder = Excluder.DEFAULT.withVersion(1.0);
    // Should exclude class with @Since(2.0)
    assertThat(excluder.excludeClass(SinceVersion2.class, true)).isTrue();
    // Should not exclude class with @Since(0.5)
    assertThat(excluder.excludeClass(SinceVersion0_5.class, true)).isFalse();
  }

  @Test
  public void testCloneDoesNotAffectOriginal() {
    // Modifying the clone shouldn't affect the original DEFAULT
    Excluder modified = Excluder.DEFAULT.withVersion(1.0);
    // Original should still use IGNORE_VERSIONS behavior
    assertThat(Excluder.DEFAULT.excludeClass(SinceVersion2.class, true)).isFalse();
    assertThat(modified.excludeClass(SinceVersion2.class, true)).isTrue();
  }

  // ========== withVersion.(D)Lcom/google/gson/internal/Excluder; Tests ==========

  @Test
  public void testWithVersionReturnsNewExcluder() {
    Excluder original = Excluder.DEFAULT;
    Excluder versioned = original.withVersion(1.0);
    assertThat(versioned).isNotSameInstanceAs(original);
  }

  @Test
  public void testWithVersionExcludesClassWithSinceAnnotation() {
    Excluder excluder = Excluder.DEFAULT.withVersion(1.0);
    // Should exclude class with @Since(2.0) when version is 1.0
    assertThat(excluder.excludeClass(SinceVersion2.class, true)).isTrue();
  }

  @Test
  public void testWithVersionIncludesClassWithSinceAnnotationMet() {
    Excluder excluder = Excluder.DEFAULT.withVersion(2.0);
    // Should not exclude class with @Since(2.0) when version is 2.0
    assertThat(excluder.excludeClass(SinceVersion2.class, true)).isFalse();
  }

  @Test
  public void testWithVersionExcludesClassWithUntilAnnotation() {
    Excluder excluder = Excluder.DEFAULT.withVersion(2.0);
    // Should exclude class with @Until(2.0) when version is 2.0
    assertThat(excluder.excludeClass(UntilVersion2.class, true)).isTrue();
  }

  @Test
  public void testWithVersionIncludesClassWithUntilAnnotationNotMet() {
    Excluder excluder = Excluder.DEFAULT.withVersion(1.0);
    // Should not exclude class with @Until(2.0) when version is 1.0
    assertThat(excluder.excludeClass(UntilVersion2.class, true)).isFalse();
  }

  @Test
  public void testWithVersionZero() {
    Excluder excluder = Excluder.DEFAULT.withVersion(0.0);
    // Version 0.0 should include @Since(0.0) fields
    assertThat(excluder.excludeClass(SinceVersion0.class, true)).isFalse();
    // But should exclude @Since(1.0) fields
    assertThat(excluder.excludeClass(SinceVersion1.class, true)).isTrue();
  }

  @Test
  public void testWithVersionExcludesFieldWithSinceAnnotation() throws NoSuchFieldException {
    Excluder excluder = Excluder.DEFAULT.withVersion(1.0);
    Field field = VersionedFieldClass.class.getDeclaredField("sinceTwo");
    assertThat(excluder.excludeField(field, true)).isTrue();
  }

  @Test
  public void testWithVersionIncludesFieldWithSinceAnnotationMet() throws NoSuchFieldException {
    Excluder excluder = Excluder.DEFAULT.withVersion(2.0);
    Field field = VersionedFieldClass.class.getDeclaredField("sinceOne");
    assertThat(excluder.excludeField(field, true)).isFalse();
  }

  @Test
  public void testWithVersionExcludesFieldWithUntilAnnotation() throws NoSuchFieldException {
    Excluder excluder = Excluder.DEFAULT.withVersion(2.0);
    Field field = VersionedFieldClass.class.getDeclaredField("untilTwo");
    assertThat(excluder.excludeField(field, true)).isTrue();
  }

  @Test
  public void testWithVersionIncludesFieldWithUntilAnnotationNotMet() throws NoSuchFieldException {
    Excluder excluder = Excluder.DEFAULT.withVersion(1.0);
    Field field = VersionedFieldClass.class.getDeclaredField("untilTwo");
    assertThat(excluder.excludeField(field, true)).isFalse();
  }

  @Test
  public void testWithVersionFieldBothSinceAndUntil() throws NoSuchFieldException {
    // Test field with both @Since and @Until annotations
    Excluder excluder = Excluder.DEFAULT.withVersion(1.5);
    Field field = VersionedFieldClass.class.getDeclaredField("sinceOneUntilTwo");
    assertThat(excluder.excludeField(field, true)).isFalse();

    // Version before @Since should exclude
    excluder = Excluder.DEFAULT.withVersion(0.5);
    assertThat(excluder.excludeField(field, true)).isTrue();

    // Version at @Until should exclude
    excluder = Excluder.DEFAULT.withVersion(2.0);
    assertThat(excluder.excludeField(field, true)).isTrue();
  }

  // ========== withModifiers.([I)Lcom/google/gson/internal/Excluder; Tests ==========

  @Test
  public void testWithModifiersReturnsNewExcluder() {
    Excluder original = Excluder.DEFAULT;
    Excluder modified = original.withModifiers(Modifier.PRIVATE);
    assertThat(modified).isNotSameInstanceAs(original);
  }

  @Test
  public void testWithModifiersExcludesPrivateField() throws NoSuchFieldException {
    Excluder excluder = Excluder.DEFAULT.withModifiers(Modifier.PRIVATE);
    Field field = TestFieldClass.class.getDeclaredField("privateField");
    assertThat(excluder.excludeField(field, true)).isTrue();
  }

  @Test
  public void testWithModifiersExcludesStaticField() throws NoSuchFieldException {
    Excluder excluder = Excluder.DEFAULT.withModifiers(Modifier.STATIC);
    Field field = TestFieldClass.class.getDeclaredField("staticField");
    assertThat(excluder.excludeField(field, true)).isTrue();
  }

  @Test
  public void testWithModifiersExcludesMultipleModifiers() throws NoSuchFieldException {
    Excluder excluder = Excluder.DEFAULT.withModifiers(Modifier.PRIVATE, Modifier.STATIC);
    Field privateField = TestFieldClass.class.getDeclaredField("privateField");
    Field staticField = TestFieldClass.class.getDeclaredField("staticField");
    assertThat(excluder.excludeField(privateField, true)).isTrue();
    assertThat(excluder.excludeField(staticField, true)).isTrue();
  }

  @Test
  public void testWithModifiersIncludesFieldWithoutExcludedModifier() throws NoSuchFieldException {
    Excluder excluder = Excluder.DEFAULT.withModifiers(Modifier.PRIVATE);
    Field field = TestFieldClass.class.getDeclaredField("regularField");
    assertThat(excluder.excludeField(field, true)).isFalse();
  }

  @Test
  public void testWithModifiersEmptyArrayIncludesAll() throws NoSuchFieldException {
    // Empty array means no modifier-based exclusions
    Excluder excluder = Excluder.DEFAULT.withModifiers();
    Field transientField = TestFieldClass.class.getDeclaredField("transientField");
    Field staticField = TestFieldClass.class.getDeclaredField("staticField");
    // Without any modifier exclusions, these should not be excluded (except for other rules)
    assertThat(excluder.excludeField(transientField, true)).isFalse();
    assertThat(excluder.excludeField(staticField, true)).isFalse();
  }

  @Test
  public void testWithModifiersOverridesDefault() throws NoSuchFieldException {
    // By default, transient and static are excluded
    // Using withModifiers with only PRIVATE should not exclude transient/static
    Excluder excluder = Excluder.DEFAULT.withModifiers(Modifier.PRIVATE);
    Field transientField = TestFieldClass.class.getDeclaredField("transientField");
    // Now transient should NOT be excluded because we only excluded PRIVATE
    assertThat(excluder.excludeField(transientField, true)).isFalse();
  }

  @Test
  public void testWithModifiersExcludesTransient() throws NoSuchFieldException {
    Excluder excluder = Excluder.DEFAULT.withModifiers(Modifier.TRANSIENT);
    Field field = TestFieldClass.class.getDeclaredField("transientField");
    assertThat(excluder.excludeField(field, true)).isTrue();
  }

  // ========== disableInnerClassSerialization.()Lcom/google/gson/internal/Excluder; Tests ==========

  @Test
  public void testDisableInnerClassSerializationReturnsNewExcluder() {
    Excluder original = Excluder.DEFAULT;
    Excluder modified = original.disableInnerClassSerialization();
    assertThat(modified).isNotSameInstanceAs(original);
  }

  @Test
  public void testDisableInnerClassSerializationExcludesNonStaticInnerClass() {
    Excluder excluder = Excluder.DEFAULT.disableInnerClassSerialization();
    // Non-static inner classes should be excluded
    assertThat(excluder.excludeClass(OuterClass.InnerClass.class, true)).isTrue();
  }

  @Test
  public void testDisableInnerClassSerializationDoesNotExcludeStaticNestedClass() {
    Excluder excluder = Excluder.DEFAULT.disableInnerClassSerialization();
    // Static nested classes should NOT be excluded
    assertThat(excluder.excludeClass(OuterClass.StaticNestedClass.class, true)).isFalse();
  }

  @Test
  public void testDisableInnerClassSerializationDoesNotExcludeTopLevelClass() {
    Excluder excluder = Excluder.DEFAULT.disableInnerClassSerialization();
    // Top-level classes should NOT be excluded
    assertThat(excluder.excludeClass(String.class, true)).isFalse();
  }

  @Test
  public void testDefaultDoesNotExcludeInnerClass() {
    // By default, inner classes are NOT excluded for serialization
    assertThat(Excluder.DEFAULT.excludeClass(OuterClass.InnerClass.class, true)).isFalse();
  }

  @Test
  public void testDisableInnerClassSerializationFieldOfInnerClassType() throws NoSuchFieldException {
    Excluder excluder = Excluder.DEFAULT.disableInnerClassSerialization();
    // A field whose type is an inner class should be excluded
    Field field = OuterClass.class.getDeclaredField("innerField");
    assertThat(excluder.excludeField(field, true)).isTrue();
  }

  @Test
  public void testDisableInnerClassSerializationFieldOfStaticNestedClassType()
      throws NoSuchFieldException {
    Excluder excluder = Excluder.DEFAULT.disableInnerClassSerialization();
    // A field whose type is a static nested class should NOT be excluded
    Field field = OuterClass.class.getDeclaredField("staticNestedField");
    assertThat(excluder.excludeField(field, true)).isFalse();
  }

  // ========== excludeFieldsWithoutExposeAnnotation.()Lcom/google/gson/internal/Excluder; Tests =

  @Test
  public void testExcludeFieldsWithoutExposeAnnotationReturnsNewExcluder() {
    Excluder original = Excluder.DEFAULT;
    Excluder modified = original.excludeFieldsWithoutExposeAnnotation();
    assertThat(modified).isNotSameInstanceAs(original);
  }

  @Test
  public void testExcludeFieldsWithoutExposeAnnotationExcludesUnexposed() throws NoSuchFieldException {
    Excluder excluder = Excluder.DEFAULT.excludeFieldsWithoutExposeAnnotation();
    Field field = ExposeClass.class.getDeclaredField("hidden");
    assertThat(excluder.excludeField(field, true)).isTrue();
  }

  @Test
  public void testExcludeFieldsWithoutExposeAnnotationIncludesExposed() throws NoSuchFieldException {
    Excluder excluder = Excluder.DEFAULT.excludeFieldsWithoutExposeAnnotation();
    Field field = ExposeClass.class.getDeclaredField("exposed");
    assertThat(excluder.excludeField(field, true)).isFalse();
  }

  @Test
  public void testExcludeFieldsWithoutExposeAnnotationSerializeOnly() throws NoSuchFieldException {
    Excluder excluder = Excluder.DEFAULT.excludeFieldsWithoutExposeAnnotation();
    Field field = ExposeClass.class.getDeclaredField("serializeOnly");
    // Should include for serialization
    assertThat(excluder.excludeField(field, true)).isFalse();
    // Should exclude for deserialization
    assertThat(excluder.excludeField(field, false)).isTrue();
  }

  @Test
  public void testExcludeFieldsWithoutExposeAnnotationDeserializeOnly() throws NoSuchFieldException {
    Excluder excluder = Excluder.DEFAULT.excludeFieldsWithoutExposeAnnotation();
    Field field = ExposeClass.class.getDeclaredField("deserializeOnly");
    // Should exclude for serialization
    assertThat(excluder.excludeField(field, true)).isTrue();
    // Should include for deserialization
    assertThat(excluder.excludeField(field, false)).isFalse();
  }

  @Test
  public void testExcludeFieldsWithoutExposeAnnotationBothFalse() throws NoSuchFieldException {
    Excluder excluder = Excluder.DEFAULT.excludeFieldsWithoutExposeAnnotation();
    Field field = ExposeClass.class.getDeclaredField("exposedButBothFalse");
    // Should exclude for both serialization and deserialization
    assertThat(excluder.excludeField(field, true)).isTrue();
    assertThat(excluder.excludeField(field, false)).isTrue();
  }

  @Test
  public void testDefaultDoesNotRequireExposeAnnotation() throws NoSuchFieldException {
    // By default, fields without @Expose should NOT be excluded
    Field field = ExposeClass.class.getDeclaredField("hidden");
    assertThat(Excluder.DEFAULT.excludeField(field, true)).isFalse();
  }

  // ========== withExclusionStrategy Tests ==========

  @Test
  public void testWithExclusionStrategyReturnsNewExcluder() {
    ExclusionStrategy strategy = createFieldNameStrategy("test");
    Excluder original = Excluder.DEFAULT;
    Excluder modified = original.withExclusionStrategy(strategy, true, true);
    assertThat(modified).isNotSameInstanceAs(original);
  }

  @Test
  public void testWithExclusionStrategyForSerializationOnly() throws NoSuchFieldException {
    ExclusionStrategy strategy = createFieldNameStrategy("regularField");
    Excluder excluder = Excluder.DEFAULT.withExclusionStrategy(strategy, true, false);
    Field field = TestFieldClass.class.getDeclaredField("regularField");
    // Should exclude for serialization
    assertThat(excluder.excludeField(field, true)).isTrue();
    // Should NOT exclude for deserialization
    assertThat(excluder.excludeField(field, false)).isFalse();
  }

  @Test
  public void testWithExclusionStrategyForDeserializationOnly() throws NoSuchFieldException {
    ExclusionStrategy strategy = createFieldNameStrategy("regularField");
    Excluder excluder = Excluder.DEFAULT.withExclusionStrategy(strategy, false, true);
    Field field = TestFieldClass.class.getDeclaredField("regularField");
    // Should NOT exclude for serialization
    assertThat(excluder.excludeField(field, true)).isFalse();
    // Should exclude for deserialization
    assertThat(excluder.excludeField(field, false)).isTrue();
  }

  @Test
  public void testWithExclusionStrategyForBoth() throws NoSuchFieldException {
    ExclusionStrategy strategy = createFieldNameStrategy("regularField");
    Excluder excluder = Excluder.DEFAULT.withExclusionStrategy(strategy, true, true);
    Field field = TestFieldClass.class.getDeclaredField("regularField");
    // Should exclude for both
    assertThat(excluder.excludeField(field, true)).isTrue();
    assertThat(excluder.excludeField(field, false)).isTrue();
  }

  @Test
  public void testWithExclusionStrategyForNeither() throws NoSuchFieldException {
    ExclusionStrategy strategy = createFieldNameStrategy("regularField");
    // Neither serialization nor deserialization - strategy effectively not applied
    Excluder excluder = Excluder.DEFAULT.withExclusionStrategy(strategy, false, false);
    Field field = TestFieldClass.class.getDeclaredField("regularField");
    // Should NOT exclude for either since strategy wasn't applied to either
    assertThat(excluder.excludeField(field, true)).isFalse();
    assertThat(excluder.excludeField(field, false)).isFalse();
  }

  @Test
  public void testWithExclusionStrategyClassExclusion() {
    ExclusionStrategy strategy = createClassExclusionStrategy(String.class);
    Excluder excluder = Excluder.DEFAULT.withExclusionStrategy(strategy, true, true);
    assertThat(excluder.excludeClass(String.class, true)).isTrue();
    assertThat(excluder.excludeClass(Integer.class, true)).isFalse();
  }

  @Test
  public void testWithExclusionStrategyMultipleStrategies() throws NoSuchFieldException {
    ExclusionStrategy strategy1 = createFieldNameStrategy("regularField");
    ExclusionStrategy strategy2 = createFieldNameStrategy("privateField");
    Excluder excluder =
        Excluder.DEFAULT
            .withExclusionStrategy(strategy1, true, true)
            .withExclusionStrategy(strategy2, true, true);
    Field regularField = TestFieldClass.class.getDeclaredField("regularField");
    Field privateField = TestFieldClass.class.getDeclaredField("privateField");
    // Both fields should be excluded
    assertThat(excluder.excludeField(regularField, true)).isTrue();
    assertThat(excluder.excludeField(privateField, true)).isTrue();
  }

  // ========== create.(Lcom/google/gson/Gson;Lcom/google/gson/reflect/TypeToken;) Tests ==========

  @Test
  public void testCreateReturnsNullForNonExcludedClass() {
    Gson gson = new Gson();
    TypeAdapter<?> adapter = Excluder.DEFAULT.create(gson, TypeToken.get(String.class));
    // Null means "I don't handle this type, let another factory do it"
    assertThat(adapter).isNull();
  }

  @Test
  public void testCreateReturnsAdapterForExcludedClass() {
    // Create an excluder that excludes String.class
    ExclusionStrategy strategy = createClassExclusionStrategy(String.class);
    Excluder excluder = Excluder.DEFAULT.withExclusionStrategy(strategy, true, true);

    Gson gson = new GsonBuilder().setExclusionStrategies(strategy).create();
    TypeAdapter<?> adapter = excluder.create(gson, TypeToken.get(String.class));
    assertThat(adapter).isNotNull();
  }

  @Test
  public void testCreateReturnsAdapterForClassExcludedOnSerializeOnly() throws IOException {
    // Create an excluder that excludes String.class for serialization only
    ExclusionStrategy strategy = createClassExclusionStrategy(String.class);
    Excluder excluder = Excluder.DEFAULT.withExclusionStrategy(strategy, true, false);

    // Create Gson with a custom factory using our excluder
    Gson gson =
        new GsonBuilder().addSerializationExclusionStrategy(strategy).create();
    TypeAdapter<?> adapter = excluder.create(gson, TypeToken.get(String.class));
    // Should return an adapter since serialization is excluded
    assertThat(adapter).isNotNull();
  }

  @Test
  public void testCreateReturnsAdapterForClassExcludedOnDeserializeOnly() throws IOException {
    // Create an excluder that excludes String.class for deserialization only
    ExclusionStrategy strategy = createClassExclusionStrategy(String.class);
    Excluder excluder = Excluder.DEFAULT.withExclusionStrategy(strategy, false, true);

    Gson gson =
        new GsonBuilder().addDeserializationExclusionStrategy(strategy).create();
    TypeAdapter<?> adapter = excluder.create(gson, TypeToken.get(String.class));
    // Should return an adapter since deserialization is excluded
    assertThat(adapter).isNotNull();
  }

  @Test
  public void testCreateAdapterSerializeWritesNull() throws IOException {
    ExclusionStrategy strategy = createClassExclusionStrategy(TestFieldClass.class);
    Excluder excluder = Excluder.DEFAULT.withExclusionStrategy(strategy, true, true);

    Gson gson =
        new GsonBuilder().setExclusionStrategies(strategy).create();
    TypeAdapter<TestFieldClass> adapter =
        excluder.create(gson, TypeToken.get(TestFieldClass.class));

    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    adapter.write(writer, new TestFieldClass());
    assertThat(sw.toString()).isEqualTo("null");
  }

  @Test
  public void testCreateAdapterDeserializeSkipsValueAndReturnsNull() throws IOException {
    ExclusionStrategy strategy = createClassExclusionStrategy(TestFieldClass.class);
    Excluder excluder = Excluder.DEFAULT.withExclusionStrategy(strategy, true, true);

    Gson gson =
        new GsonBuilder().setExclusionStrategies(strategy).create();
    TypeAdapter<TestFieldClass> adapter =
        excluder.create(gson, TypeToken.get(TestFieldClass.class));

    StringReader sr = new StringReader("{\"regularField\":\"test\"}");
    JsonReader reader = new JsonReader(sr);
    TestFieldClass result = adapter.read(reader);
    assertThat(result).isNull();
  }

  @Test
  public void testCreateAdapterSerializeOnlyDelegatesForDeserialization() throws IOException {
    ExclusionStrategy strategy = createClassExclusionStrategy(SimpleClass.class);
    Excluder excluder = Excluder.DEFAULT.withExclusionStrategy(strategy, true, false);

    Gson gson =
        new GsonBuilder().addSerializationExclusionStrategy(strategy).create();
    TypeAdapter<SimpleClass> adapter = excluder.create(gson, TypeToken.get(SimpleClass.class));

    // Serialization should write null
    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    adapter.write(writer, new SimpleClass("test"));
    assertThat(sw.toString()).isEqualTo("null");

    // Deserialization should delegate and work normally
    StringReader sr = new StringReader("{\"value\":\"hello\"}");
    JsonReader reader = new JsonReader(sr);
    SimpleClass result = adapter.read(reader);
    assertThat(result).isNotNull();
    assertThat(result.value).isEqualTo("hello");
  }

  @Test
  public void testCreateAdapterDeserializeOnlyDelegatesForSerialization() throws IOException {
    ExclusionStrategy strategy = createClassExclusionStrategy(SimpleClass.class);
    Excluder excluder = Excluder.DEFAULT.withExclusionStrategy(strategy, false, true);

    Gson gson =
        new GsonBuilder().addDeserializationExclusionStrategy(strategy).create();
    TypeAdapter<SimpleClass> adapter = excluder.create(gson, TypeToken.get(SimpleClass.class));

    // Serialization should delegate and work normally
    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    adapter.write(writer, new SimpleClass("test"));
    assertThat(sw.toString()).contains("\"value\":\"test\"");

    // Deserialization should skip value and return null
    StringReader sr = new StringReader("{\"value\":\"hello\"}");
    JsonReader reader = new JsonReader(sr);
    SimpleClass result = adapter.read(reader);
    assertThat(result).isNull();
  }

  // ========== excludeField.(Ljava/lang/reflect/Field;Z)Z Tests ==========

  @Test
  public void testExcludeFieldExcludesFieldWithMatchingModifier() throws NoSuchFieldException {
    // Default excludes TRANSIENT and STATIC
    Field transientField = TestFieldClass.class.getDeclaredField("transientField");
    assertThat(Excluder.DEFAULT.excludeField(transientField, true)).isTrue();
  }

  @Test
  public void testExcludeFieldDoesNotExcludeFieldWithoutMatchingModifier()
      throws NoSuchFieldException {
    Field regularField = TestFieldClass.class.getDeclaredField("regularField");
    assertThat(Excluder.DEFAULT.excludeField(regularField, true)).isFalse();
  }

  @Test
  public void testExcludeFieldExcludesFieldWithSinceAnnotation() throws NoSuchFieldException {
    Excluder excluder = Excluder.DEFAULT.withVersion(1.0);
    Field field = VersionedFieldClass.class.getDeclaredField("sinceTwo");
    assertThat(excluder.excludeField(field, true)).isTrue();
  }

  @Test
  public void testExcludeFieldExcludesFieldWithUntilAnnotation() throws NoSuchFieldException {
    Excluder excluder = Excluder.DEFAULT.withVersion(2.0);
    Field field = VersionedFieldClass.class.getDeclaredField("untilTwo");
    assertThat(excluder.excludeField(field, true)).isTrue();
  }

  @Test
  public void testExcludeFieldWithSerializeTrueAndDeserializeFalse() throws NoSuchFieldException {
    Excluder excluder = Excluder.DEFAULT.excludeFieldsWithoutExposeAnnotation();
    Field field = ExposeClass.class.getDeclaredField("serializeOnly");
    // serialize=true should pass for serialization
    assertThat(excluder.excludeField(field, true)).isFalse();
    // serialize=false (deserialize=false) should fail for deserialization
    assertThat(excluder.excludeField(field, false)).isTrue();
  }

  @Test
  public void testExcludeFieldExcludesFieldOfExcludedClassType() throws NoSuchFieldException {
    Excluder excluder = Excluder.DEFAULT.disableInnerClassSerialization();
    Field field = OuterClass.class.getDeclaredField("innerField");
    // Field type is inner class, should be excluded
    assertThat(excluder.excludeField(field, true)).isTrue();
  }

  @Test
  public void testExcludeFieldExcludesFieldByExclusionStrategy() throws NoSuchFieldException {
    ExclusionStrategy strategy = createFieldNameStrategy("regularField");
    Excluder excluder = Excluder.DEFAULT.withExclusionStrategy(strategy, true, true);
    Field field = TestFieldClass.class.getDeclaredField("regularField");
    assertThat(excluder.excludeField(field, true)).isTrue();
  }

  @Test
  public void testExcludeFieldChecksBothExclusionStrategies() throws NoSuchFieldException {
    ExclusionStrategy serializationStrategy = createFieldNameStrategy("regularField");
    ExclusionStrategy deserializationStrategy = createFieldNameStrategy("privateField");
    Excluder excluder =
        Excluder.DEFAULT
            .withExclusionStrategy(serializationStrategy, true, false)
            .withExclusionStrategy(deserializationStrategy, false, true);

    Field regularField = TestFieldClass.class.getDeclaredField("regularField");
    Field privateField = TestFieldClass.class.getDeclaredField("privateField");

    // regularField excluded only for serialization
    assertThat(excluder.excludeField(regularField, true)).isTrue();
    assertThat(excluder.excludeField(regularField, false)).isFalse();

    // privateField excluded only for deserialization
    assertThat(excluder.excludeField(privateField, true)).isFalse();
    assertThat(excluder.excludeField(privateField, false)).isTrue();
  }

  // ========== excludeClass.(Ljava/lang/Class;Z)Z Tests ==========

  @Test
  public void testExcludeClassReturnsFalseForRegularClass() {
    assertThat(Excluder.DEFAULT.excludeClass(String.class, true)).isFalse();
    assertThat(Excluder.DEFAULT.excludeClass(Integer.class, false)).isFalse();
  }

  @Test
  public void testExcludeClassExcludesClassWithSinceAnnotation() {
    Excluder excluder = Excluder.DEFAULT.withVersion(1.0);
    assertThat(excluder.excludeClass(SinceVersion2.class, true)).isTrue();
  }

  @Test
  public void testExcludeClassExcludesClassWithUntilAnnotation() {
    Excluder excluder = Excluder.DEFAULT.withVersion(2.0);
    assertThat(excluder.excludeClass(UntilVersion2.class, true)).isTrue();
  }

  @Test
  public void testExcludeClassExcludesInnerClassWhenDisabled() {
    Excluder excluder = Excluder.DEFAULT.disableInnerClassSerialization();
    assertThat(excluder.excludeClass(OuterClass.InnerClass.class, true)).isTrue();
  }

  @Test
  public void testExcludeClassDoesNotExcludeStaticNestedClass() {
    Excluder excluder = Excluder.DEFAULT.disableInnerClassSerialization();
    assertThat(excluder.excludeClass(OuterClass.StaticNestedClass.class, true)).isFalse();
  }

  @Test
  public void testExcludeClassExcludesAnonymousClassForDeserialization() {
    // Anonymous classes should be excluded for deserialization
    Runnable anonymous = new Runnable() {
      @Override
      public void run() {}
    };
    assertThat(Excluder.DEFAULT.excludeClass(anonymous.getClass(), false)).isTrue();
  }

  @Test
  public void testExcludeClassDoesNotExcludeAnonymousClassForSerialization() {
    // Anonymous classes should NOT be excluded for serialization (custom adapter might handle)
    Runnable anonymous = new Runnable() {
      @Override
      public void run() {}
    };
    assertThat(Excluder.DEFAULT.excludeClass(anonymous.getClass(), true)).isFalse();
  }

  @Test
  public void testExcludeClassDoesNotExcludeAnonymousEnumSubclass() {
    // Anonymous enum subclasses should NOT be excluded even for deserialization
    assertThat(Excluder.DEFAULT.excludeClass(EnumWithBody.VALUE.getClass(), false)).isFalse();
  }

  @Test
  public void testExcludeClassExcludesLocalClassForDeserialization() {
    // Local class should be excluded for deserialization
    class LocalClass {}
    assertThat(Excluder.DEFAULT.excludeClass(LocalClass.class, false)).isTrue();
  }

  @Test
  public void testExcludeClassDoesNotExcludeLocalClassForSerialization() {
    // Local class should NOT be excluded for serialization
    class LocalClass {}
    assertThat(Excluder.DEFAULT.excludeClass(LocalClass.class, true)).isFalse();
  }

  @Test
  public void testExcludeClassByExclusionStrategy() {
    ExclusionStrategy strategy = createClassExclusionStrategy(String.class);
    Excluder excluder = Excluder.DEFAULT.withExclusionStrategy(strategy, true, true);
    assertThat(excluder.excludeClass(String.class, true)).isTrue();
    assertThat(excluder.excludeClass(Integer.class, true)).isFalse();
  }

  @Test
  public void testExcludeClassByExclusionStrategySerializationOnly() {
    ExclusionStrategy strategy = createClassExclusionStrategy(String.class);
    Excluder excluder = Excluder.DEFAULT.withExclusionStrategy(strategy, true, false);
    assertThat(excluder.excludeClass(String.class, true)).isTrue();
    assertThat(excluder.excludeClass(String.class, false)).isFalse();
  }

  @Test
  public void testExcludeClassByExclusionStrategyDeserializationOnly() {
    ExclusionStrategy strategy = createClassExclusionStrategy(String.class);
    Excluder excluder = Excluder.DEFAULT.withExclusionStrategy(strategy, false, true);
    assertThat(excluder.excludeClass(String.class, true)).isFalse();
    assertThat(excluder.excludeClass(String.class, false)).isTrue();
  }

  // ========== Integration Tests ==========

  @Test
  public void testExcluderIntegrationWithGson() {
    // Test that excluder works properly when integrated with Gson via GsonBuilder
    Gson gson = new GsonBuilder().setVersion(1.0).create();
    VersionedFieldClass obj = new VersionedFieldClass();
    String json = gson.toJson(obj);
    // sinceTwo should be excluded (requires version 2.0)
    assertThat(json).doesNotContain("sinceTwo");
    // sinceOne should be included (requires version 1.0)
    assertThat(json).contains("sinceOne");
    // untilTwo should be included (available until version 2.0)
    assertThat(json).contains("untilTwo");
  }

  @Test
  public void testExcluderIntegrationWithExposeAnnotation() {
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    ExposeClass obj = new ExposeClass();
    obj.exposed = "visible";
    obj.hidden = "invisible";
    String json = gson.toJson(obj);
    assertThat(json).contains("exposed");
    assertThat(json).doesNotContain("hidden");
  }

  @Test
  public void testExcluderIntegrationWithDisableInnerClass() {
    Gson gson = new GsonBuilder().disableInnerClassSerialization().create();
    OuterClass outer = new OuterClass();
    outer.innerField = outer.new InnerClass();
    outer.innerField.value = "test";
    outer.outerValue = "outer";
    String json = gson.toJson(outer);
    // Inner class field should be excluded
    assertThat(json).doesNotContain("innerField");
    assertThat(json).contains("outerValue");
  }

  @Test
  public void testExcluderIntegrationWithModifiers() {
    Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.PRIVATE).create();
    TestFieldClass obj = new TestFieldClass();
    obj.regularField = "regular";
    String json = gson.toJson(obj);
    assertThat(json).contains("regularField");
    // Private field should not appear
    assertThat(json).doesNotContain("privateField");
  }

  // ========== Helper Methods and Classes ==========

  private static ExclusionStrategy createFieldNameStrategy(String fieldName) {
    return new ExclusionStrategy() {
      @Override
      public boolean shouldSkipField(FieldAttributes f) {
        return f.getName().equals(fieldName);
      }

      @Override
      public boolean shouldSkipClass(Class<?> clazz) {
        return false;
      }
    };
  }

  private static ExclusionStrategy createClassExclusionStrategy(Class<?> excludedClass) {
    return new ExclusionStrategy() {
      @Override
      public boolean shouldSkipField(FieldAttributes f) {
        return false;
      }

      @Override
      public boolean shouldSkipClass(Class<?> clazz) {
        return clazz == excludedClass;
      }
    };
  }

  @SuppressWarnings("unused")
  private static class TestFieldClass {
    String regularField = "regular";
    private String privateField = "private";
    transient String transientField = "transient";
    static String staticField = "static";
    protected String protectedField = "protected";
  }

  @Since(2.0)
  private static class SinceVersion2 {}

  @Since(1.0)
  private static class SinceVersion1 {}

  @Since(0.5)
  private static class SinceVersion0_5 {}

  @Since(0.0)
  private static class SinceVersion0 {}

  @Until(2.0)
  private static class UntilVersion2 {}

  @SuppressWarnings("unused")
  private static class VersionedFieldClass {
    @Since(1.0)
    String sinceOne = "sinceOne";

    @Since(2.0)
    String sinceTwo = "sinceTwo";

    @Until(2.0)
    String untilTwo = "untilTwo";

    @Since(1.0)
    @Until(2.0)
    String sinceOneUntilTwo = "sinceOneUntilTwo";
  }

  @SuppressWarnings("unused")
  private static class ExposeClass {
    @Expose
    String exposed;

    String hidden;

    @Expose(serialize = true, deserialize = false)
    String serializeOnly;

    @Expose(serialize = false, deserialize = true)
    String deserializeOnly;

    @Expose(serialize = false, deserialize = false)
    String exposedButBothFalse;
  }

  @SuppressWarnings({"unused", "InnerClassMayBeStatic"})
  private static class OuterClass {
    String outerValue;
    InnerClass innerField;
    StaticNestedClass staticNestedField;

    class InnerClass {
      String value;
    }

    static class StaticNestedClass {
      String value;
    }
  }

  @SuppressWarnings("unused")
  private static class SimpleClass {
    String value;

    SimpleClass() {}

    SimpleClass(String value) {
      this.value = value;
    }
  }

  private enum EnumWithBody {
    VALUE {
      @Override
      public String toString() {
        return "custom";
      }
    }
  }
}
