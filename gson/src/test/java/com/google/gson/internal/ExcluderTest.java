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
import org.junit.Before;
import org.junit.Test;

public final class ExcluderTest {

  private Excluder excluder;

  @Before
  public void setUp() {
    excluder = Excluder.DEFAULT.clone();
  }

  @Test
  public void testCloneReturnsNewInstance() {
    Excluder cloned = Excluder.DEFAULT.clone();
    assertThat(cloned).isNotSameInstanceAs(Excluder.DEFAULT);
  }

  @Test
  public void testWithVersionSetsVersion() {
    Excluder versioned = excluder.withVersion(2.0);
    assertThat(versioned).isNotSameInstanceAs(excluder);
  }

  @Test
  public void testWithVersionDoesNotModifyOriginal() {
    excluder.withVersion(2.0);
    // The original excluder still uses IGNORE_VERSIONS (-1.0)
    // A field with @Since(1.0) should not be excluded by original excluder
    assertThat(excluder.excludeClass(SinceClass.class, true)).isFalse();
  }

  @Test
  public void testWithModifiersExcludesMatchingModifiers() throws NoSuchFieldException {
    Excluder modifierExcluder = excluder.withModifiers(Modifier.PROTECTED);
    Field protectedField = SampleClass.class.getDeclaredField("protectedField");
    assertThat(modifierExcluder.excludeField(protectedField, true)).isTrue();
  }

  @Test
  public void testWithModifiersDoesNotExcludeNonMatchingModifiers() throws NoSuchFieldException {
    Excluder modifierExcluder = excluder.withModifiers(Modifier.PROTECTED);
    Field publicField = SampleClass.class.getDeclaredField("publicField");
    assertThat(modifierExcluder.excludeField(publicField, true)).isFalse();
  }

  @Test
  public void testWithModifiersReturnsNewInstance() {
    Excluder result = excluder.withModifiers(Modifier.PRIVATE);
    assertThat(result).isNotSameInstanceAs(excluder);
  }

  @Test
  public void testDisableInnerClassSerializationReturnsNewInstance() {
    Excluder result = excluder.disableInnerClassSerialization();
    assertThat(result).isNotSameInstanceAs(excluder);
  }

  @Test
  public void testDisableInnerClassSerializationExcludesInnerClass() {
    Excluder result = excluder.disableInnerClassSerialization();
    assertThat(result.excludeClass(InnerClassHolder.InnerClass.class, true)).isTrue();
  }

  @Test
  public void testDefaultDoesNotExcludeInnerClass() {
    // By default, inner classes are serialized
    assertThat(excluder.excludeClass(InnerClassHolder.InnerClass.class, true)).isFalse();
  }

  @Test
  public void testExcludeFieldsWithoutExposeAnnotationReturnsNewInstance() {
    Excluder result = excluder.excludeFieldsWithoutExposeAnnotation();
    assertThat(result).isNotSameInstanceAs(excluder);
  }

  @Test
  public void testExcludeFieldsWithoutExposeAnnotationExcludesFieldWithoutExpose()
      throws NoSuchFieldException {
    Excluder result = excluder.excludeFieldsWithoutExposeAnnotation();
    Field field = SampleClass.class.getDeclaredField("publicField");
    assertThat(result.excludeField(field, true)).isTrue();
  }

  @Test
  public void testExcludeFieldsWithoutExposeAnnotationIncludesFieldWithExpose()
      throws NoSuchFieldException {
    Excluder result = excluder.excludeFieldsWithoutExposeAnnotation();
    Field field = ExposedClass.class.getDeclaredField("exposedField");
    assertThat(result.excludeField(field, true)).isFalse();
  }

  @Test
  public void testExcludeFieldsWithoutExposeAnnotationExcludesFieldWithExposeSerializeFalse()
      throws NoSuchFieldException {
    Excluder result = excluder.excludeFieldsWithoutExposeAnnotation();
    Field field = ExposedClass.class.getDeclaredField("serializeDisabledField");
    assertThat(result.excludeField(field, true)).isTrue();
  }

  @Test
  public void testExcludeFieldsWithoutExposeAnnotationExcludesFieldWithExposeDeserializeFalse()
      throws NoSuchFieldException {
    Excluder result = excluder.excludeFieldsWithoutExposeAnnotation();
    Field field = ExposedClass.class.getDeclaredField("deserializeDisabledField");
    assertThat(result.excludeField(field, false)).isTrue();
  }

  @Test
  public void testWithExclusionStrategyForSerializationReturnsNewInstance() {
    ExclusionStrategy strategy = new AlwaysExcludeStrategy();
    Excluder result = excluder.withExclusionStrategy(strategy, true, false);
    assertThat(result).isNotSameInstanceAs(excluder);
  }

  @Test
  public void testWithExclusionStrategyForSerializationExcludesClass() {
    ExclusionStrategy strategy = new AlwaysExcludeStrategy();
    Excluder result = excluder.withExclusionStrategy(strategy, true, false);
    assertThat(result.excludeClass(String.class, true)).isTrue();
  }

  @Test
  public void testWithExclusionStrategyForDeserializationExcludesClass() {
    ExclusionStrategy strategy = new AlwaysExcludeStrategy();
    Excluder result = excluder.withExclusionStrategy(strategy, false, true);
    assertThat(result.excludeClass(String.class, false)).isTrue();
  }

  @Test
  public void testWithExclusionStrategyNotAppliedToOtherDirection() {
    ExclusionStrategy strategy = new AlwaysExcludeStrategy();
    Excluder result = excluder.withExclusionStrategy(strategy, true, false);
    // Deserialization should not be excluded since strategy only applies to serialization
    assertThat(result.excludeClass(String.class, false)).isFalse();
  }

  @Test
  public void testWithExclusionStrategyForBothDirections() {
    ExclusionStrategy strategy = new AlwaysExcludeStrategy();
    Excluder result = excluder.withExclusionStrategy(strategy, true, true);
    assertThat(result.excludeClass(String.class, true)).isTrue();
    assertThat(result.excludeClass(String.class, false)).isTrue();
  }

  @Test
  public void testCreateReturnsNullForNonExcludedType() {
    Gson gson = new GsonBuilder().create();
    TypeAdapter<String> adapter = excluder.create(gson, TypeToken.get(String.class));
    assertThat(adapter).isNull();
  }

  @Test
  public void testCreateReturnsAdapterForExcludedTypeOnSerialization() throws IOException {
    ExclusionStrategy strategy = new AlwaysExcludeStrategy();
    Excluder result = excluder.withExclusionStrategy(strategy, true, false);
    Gson gson = new GsonBuilder().create();
    TypeAdapter<String> adapter = result.create(gson, TypeToken.get(String.class));
    assertThat(adapter).isNotNull();

    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    adapter.write(writer, "hello");
    writer.flush();
    assertThat(sw.toString()).isEqualTo("null");
  }

  @Test
  public void testCreateReturnsAdapterForExcludedTypeOnDeserialization() throws IOException {
    ExclusionStrategy strategy = new AlwaysExcludeStrategy();
    Excluder result = excluder.withExclusionStrategy(strategy, false, true);
    Gson gson = new GsonBuilder().create();
    TypeAdapter<String> adapter = result.create(gson, TypeToken.get(String.class));
    assertThat(adapter).isNotNull();

    JsonReader reader = new JsonReader(new StringReader("\"hello\""));
    String value = adapter.read(reader);
    assertThat(value).isNull();
  }

  @Test
  public void testExcludeFieldTransient() throws NoSuchFieldException {
    Field field = SampleClass.class.getDeclaredField("transientField");
    assertThat(excluder.excludeField(field, true)).isTrue();
  }

  @Test
  public void testExcludeFieldStatic() throws NoSuchFieldException {
    Field field = SampleClass.class.getDeclaredField("staticField");
    assertThat(excluder.excludeField(field, true)).isTrue();
  }

  @Test
  public void testExcludeFieldPublicNotExcluded() throws NoSuchFieldException {
    Field field = SampleClass.class.getDeclaredField("publicField");
    assertThat(excluder.excludeField(field, true)).isFalse();
  }

  @Test
  public void testExcludeFieldWithSinceVersionTooHigh() throws NoSuchFieldException {
    Excluder versioned = excluder.withVersion(1.0);
    Field field = SampleClass.class.getDeclaredField("sinceField");
    // sinceField has @Since(2.0), and version is 1.0, so it should be excluded
    assertThat(versioned.excludeField(field, true)).isTrue();
  }

  @Test
  public void testExcludeFieldWithSinceVersionValid() throws NoSuchFieldException {
    Excluder versioned = excluder.withVersion(3.0);
    Field field = SampleClass.class.getDeclaredField("sinceField");
    // sinceField has @Since(2.0), and version is 3.0, so it should be included
    assertThat(versioned.excludeField(field, true)).isFalse();
  }

  @Test
  public void testExcludeFieldWithUntilVersionExpired() throws NoSuchFieldException {
    Excluder versioned = excluder.withVersion(3.0);
    Field field = SampleClass.class.getDeclaredField("untilField");
    // untilField has @Until(2.0), and version is 3.0, so it should be excluded
    assertThat(versioned.excludeField(field, true)).isTrue();
  }

  @Test
  public void testExcludeFieldWithUntilVersionValid() throws NoSuchFieldException {
    Excluder versioned = excluder.withVersion(1.0);
    Field field = SampleClass.class.getDeclaredField("untilField");
    // untilField has @Until(2.0), and version is 1.0, so it should be included
    assertThat(versioned.excludeField(field, true)).isFalse();
  }

  @Test
  public void testExcludeFieldByFieldExclusionStrategy() throws NoSuchFieldException {
    ExclusionStrategy strategy = new AlwaysExcludeFieldStrategy();
    Excluder result = excluder.withExclusionStrategy(strategy, true, false);
    Field field = SampleClass.class.getDeclaredField("publicField");
    assertThat(result.excludeField(field, true)).isTrue();
  }

  @Test
  public void testExcludeClassWithSinceVersionTooHigh() {
    Excluder versioned = excluder.withVersion(1.0);
    assertThat(versioned.excludeClass(SinceClass.class, true)).isTrue();
  }

  @Test
  public void testExcludeClassWithSinceVersionValid() {
    Excluder versioned = excluder.withVersion(3.0);
    assertThat(versioned.excludeClass(SinceClass.class, true)).isFalse();
  }

  @Test
  public void testExcludeClassWithUntilVersionExpired() {
    Excluder versioned = excluder.withVersion(3.0);
    assertThat(versioned.excludeClass(UntilClass.class, true)).isTrue();
  }

  @Test
  public void testExcludeClassWithUntilVersionValid() {
    Excluder versioned = excluder.withVersion(1.0);
    assertThat(versioned.excludeClass(UntilClass.class, true)).isFalse();
  }

  @Test
  public void testExcludeClassAnonymousClassForDeserialization() {
    Runnable anonymous = new Runnable() {
      @Override
      public void run() {}
    };
    // Anonymous classes should be excluded for deserialization
    assertThat(excluder.excludeClass(anonymous.getClass(), false)).isTrue();
  }

  @Test
  public void testExcludeClassAnonymousClassForSerializationNotExcluded() {
    Runnable anonymous = new Runnable() {
      @Override
      public void run() {}
    };
    // Anonymous classes should NOT be excluded for serialization by default
    assertThat(excluder.excludeClass(anonymous.getClass(), true)).isFalse();
  }

  @Test
  public void testExcludeClassByExclusionStrategy() {
    ExclusionStrategy strategy = new AlwaysExcludeStrategy();
    Excluder result = excluder.withExclusionStrategy(strategy, true, false);
    assertThat(result.excludeClass(Object.class, true)).isTrue();
  }

  @Test
  public void testExcludeClassRegularClassNotExcluded() {
    assertThat(excluder.excludeClass(String.class, true)).isFalse();
    assertThat(excluder.excludeClass(String.class, false)).isFalse();
  }

  @Test
  public void testCreateAdapterExcludedSerializationReadDelegatesToRealAdapter() throws IOException {
    // skipSerialize=true, skipDeserialize=false: read() must delegate to real adapter (line 134)
    ExclusionStrategy strategy = new AlwaysExcludeStrategy();
    Excluder result = excluder.withExclusionStrategy(strategy, true, false);
    Gson gson = new GsonBuilder().create();
    TypeAdapter<String> adapter = result.create(gson, TypeToken.get(String.class));
    assertThat(adapter).isNotNull();

    JsonReader reader = new JsonReader(new StringReader("\"hello\""));
    String value = adapter.read(reader);
    assertThat(value).isEqualTo("hello");
  }

  @Test
  public void testCreateAdapterExcludedDeserializationWriteDelegatesToRealAdapter()
      throws IOException {
    // skipSerialize=false, skipDeserialize=true: write() must delegate to real adapter (line 143)
    ExclusionStrategy strategy = new AlwaysExcludeStrategy();
    Excluder result = excluder.withExclusionStrategy(strategy, false, true);
    Gson gson = new GsonBuilder().create();
    TypeAdapter<String> adapter = result.create(gson, TypeToken.get(String.class));
    assertThat(adapter).isNotNull();

    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    adapter.write(writer, "world");
    writer.flush();
    assertThat(sw.toString()).isEqualTo("\"world\"");
  }

  @Test
  public void testCreateAdapterDelegateLazyInitializedOnlyOnce() throws IOException {
    // Verifies that delegate() (lines 149-153) is lazily created and reused on subsequent calls
    ExclusionStrategy strategy = new AlwaysExcludeStrategy();
    Excluder result = excluder.withExclusionStrategy(strategy, true, false);
    Gson gson = new GsonBuilder().create();
    TypeAdapter<String> adapter = result.create(gson, TypeToken.get(String.class));
    assertThat(adapter).isNotNull();

    // Call read() twice to exercise delegate caching (d != null path on second call)
    JsonReader reader1 = new JsonReader(new StringReader("\"first\""));
    String value1 = adapter.read(reader1);
    assertThat(value1).isEqualTo("first");

    JsonReader reader2 = new JsonReader(new StringReader("\"second\""));
    String value2 = adapter.read(reader2);
    assertThat(value2).isEqualTo("second");
  }

  // Test data classes

  @Since(2.0)
  private static class SinceClass {}

  @Until(2.0)
  private static class UntilClass {}

  private static class SampleClass {
    public String publicField;
    protected String protectedField;
    public transient String transientField;
    public static String staticField;
    @Since(2.0) public String sinceField;
    @Until(2.0) public String untilField;
  }

  private static class ExposedClass {
    @Expose public String exposedField;
    @Expose(serialize = false) public String serializeDisabledField;
    @Expose(deserialize = false) public String deserializeDisabledField;
  }

  private static class InnerClassHolder {
    class InnerClass {}
  }

  private static class AlwaysExcludeStrategy implements ExclusionStrategy {
    @Override
    public boolean shouldSkipField(FieldAttributes f) {
      return true;
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
      return true;
    }
  }

  private static class AlwaysExcludeFieldStrategy implements ExclusionStrategy {
    @Override
    public boolean shouldSkipField(FieldAttributes f) {
      return true;
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
      return false;
    }
  }
}
