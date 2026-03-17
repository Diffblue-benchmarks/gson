/*
 * Copyright (C) 2026 Google Inc.
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

public class ExcluderTest {

  private static class SampleClass {
    public String publicField;
    private String privateField;
    protected String protectedField;
    transient String transientField;
    static String staticField;
    @Expose String exposedField;
    @Expose(serialize = false) String serializeExcludedField;
    @Expose(deserialize = false) String deserializeExcludedField;
    @Since(1.5) String sinceField;
    @Until(2.0) String untilField;
  }

  @Since(1.5)
  private static class SinceClass {
    String field;
  }

  @Until(2.0)
  private static class UntilClass {
    String field;
  }

  private class InnerClass {
    String field;
  }

  private static class StaticNestedClass {
    String field;
  }

  @Test
  public void testWithVersion() {
    Excluder excluder = Excluder.DEFAULT;
    Excluder withVersion = excluder.withVersion(1.0);

    assertThat(withVersion).isNotNull();
    assertThat(withVersion).isNotSameInstanceAs(excluder);
  }

  @Test
  public void testWithModifiers() {
    Excluder excluder = Excluder.DEFAULT;
    Excluder withModifiers = excluder.withModifiers(Modifier.PRIVATE, Modifier.PROTECTED);

    assertThat(withModifiers).isNotNull();
    assertThat(withModifiers).isNotSameInstanceAs(excluder);
  }

  @Test
  public void testWithModifiersEmpty() {
    Excluder excluder = Excluder.DEFAULT;
    Excluder withModifiers = excluder.withModifiers();

    assertThat(withModifiers).isNotNull();
    assertThat(withModifiers).isNotSameInstanceAs(excluder);
  }

  @Test
  public void testDisableInnerClassSerialization() {
    Excluder excluder = Excluder.DEFAULT;
    Excluder disabled = excluder.disableInnerClassSerialization();

    assertThat(disabled).isNotNull();
    assertThat(disabled).isNotSameInstanceAs(excluder);
  }

  @Test
  public void testExcludeFieldsWithoutExposeAnnotation() {
    Excluder excluder = Excluder.DEFAULT;
    Excluder withExpose = excluder.excludeFieldsWithoutExposeAnnotation();

    assertThat(withExpose).isNotNull();
    assertThat(withExpose).isNotSameInstanceAs(excluder);
  }

  @Test
  public void testWithExclusionStrategyForSerialization() {
    ExclusionStrategy strategy = new ExclusionStrategy() {
      @Override
      public boolean shouldSkipField(FieldAttributes f) {
        return false;
      }

      @Override
      public boolean shouldSkipClass(Class<?> clazz) {
        return false;
      }
    };

    Excluder excluder = Excluder.DEFAULT;
    Excluder withStrategy = excluder.withExclusionStrategy(strategy, true, false);

    assertThat(withStrategy).isNotNull();
    assertThat(withStrategy).isNotSameInstanceAs(excluder);
  }

  @Test
  public void testWithExclusionStrategyForDeserialization() {
    ExclusionStrategy strategy = new ExclusionStrategy() {
      @Override
      public boolean shouldSkipField(FieldAttributes f) {
        return false;
      }

      @Override
      public boolean shouldSkipClass(Class<?> clazz) {
        return false;
      }
    };

    Excluder excluder = Excluder.DEFAULT;
    Excluder withStrategy = excluder.withExclusionStrategy(strategy, false, true);

    assertThat(withStrategy).isNotNull();
    assertThat(withStrategy).isNotSameInstanceAs(excluder);
  }

  @Test
  public void testWithExclusionStrategyForBoth() {
    ExclusionStrategy strategy = new ExclusionStrategy() {
      @Override
      public boolean shouldSkipField(FieldAttributes f) {
        return false;
      }

      @Override
      public boolean shouldSkipClass(Class<?> clazz) {
        return false;
      }
    };

    Excluder excluder = Excluder.DEFAULT;
    Excluder withStrategy = excluder.withExclusionStrategy(strategy, true, true);

    assertThat(withStrategy).isNotNull();
    assertThat(withStrategy).isNotSameInstanceAs(excluder);
  }

  @Test
  public void testCreateTypeAdapterReturnsNullWhenNotExcluded() {
    Excluder excluder = Excluder.DEFAULT;
    Gson gson = new Gson();
    TypeToken<String> typeToken = TypeToken.get(String.class);

    TypeAdapter<String> adapter = excluder.create(gson, typeToken);

    assertThat(adapter).isNull();
  }

  @Test
  public void testCreateTypeAdapterForExcludedClass() throws IOException {
    ExclusionStrategy strategy = new ExclusionStrategy() {
      @Override
      public boolean shouldSkipField(FieldAttributes f) {
        return false;
      }

      @Override
      public boolean shouldSkipClass(Class<?> clazz) {
        return clazz == SampleClass.class;
      }
    };

    Excluder excluder = Excluder.DEFAULT.withExclusionStrategy(strategy, true, false);
    Gson gson = new GsonBuilder().setExclusionStrategies(strategy).create();
    TypeToken<SampleClass> typeToken = TypeToken.get(SampleClass.class);

    TypeAdapter<SampleClass> adapter = excluder.create(gson, typeToken);

    assertThat(adapter).isNotNull();

    StringWriter writer = new StringWriter();
    JsonWriter jsonWriter = new JsonWriter(writer);
    adapter.write(jsonWriter, new SampleClass());

    assertThat(writer.toString()).isEqualTo("null");
  }

  @Test
  public void testCreateTypeAdapterForExcludedClassDeserialization() throws IOException {
    ExclusionStrategy strategy = new ExclusionStrategy() {
      @Override
      public boolean shouldSkipField(FieldAttributes f) {
        return false;
      }

      @Override
      public boolean shouldSkipClass(Class<?> clazz) {
        return clazz == String.class;
      }
    };

    Excluder excluder = Excluder.DEFAULT.withExclusionStrategy(strategy, false, true);
    Gson gson = new Gson();
    TypeToken<String> typeToken = TypeToken.get(String.class);

    TypeAdapter<String> adapter = excluder.create(gson, typeToken);

    assertThat(adapter).isNotNull();

    JsonReader reader = new JsonReader(new StringReader("\"test\""));
    String result = adapter.read(reader);

    assertThat(result).isNull();
  }

  @Test
  public void testExcludeFieldWithTransientModifier() throws NoSuchFieldException {
    Excluder excluder = Excluder.DEFAULT;
    Field field = SampleClass.class.getDeclaredField("transientField");

    boolean excluded = excluder.excludeField(field, true);

    assertThat(excluded).isTrue();
  }

  @Test
  public void testExcludeFieldWithStaticModifier() throws NoSuchFieldException {
    Excluder excluder = Excluder.DEFAULT;
    Field field = SampleClass.class.getDeclaredField("staticField");

    boolean excluded = excluder.excludeField(field, true);

    assertThat(excluded).isTrue();
  }

  @Test
  public void testExcludeFieldWithVersion() throws NoSuchFieldException {
    Excluder excluder = Excluder.DEFAULT.withVersion(1.0);
    Field field = SampleClass.class.getDeclaredField("sinceField");

    boolean excluded = excluder.excludeField(field, true);

    assertThat(excluded).isTrue();
  }

  @Test
  public void testExcludeFieldWithValidVersion() throws NoSuchFieldException {
    Excluder excluder = Excluder.DEFAULT.withVersion(2.0);
    Field field = SampleClass.class.getDeclaredField("sinceField");

    boolean excluded = excluder.excludeField(field, true);

    assertThat(excluded).isFalse();
  }

  @Test
  public void testExcludeFieldWithUntilVersion() throws NoSuchFieldException {
    Excluder excluder = Excluder.DEFAULT.withVersion(3.0);
    Field field = SampleClass.class.getDeclaredField("untilField");

    boolean excluded = excluder.excludeField(field, true);

    assertThat(excluded).isTrue();
  }

  @Test
  public void testExcludeFieldWithValidUntilVersion() throws NoSuchFieldException {
    Excluder excluder = Excluder.DEFAULT.withVersion(1.5);
    Field field = SampleClass.class.getDeclaredField("untilField");

    boolean excluded = excluder.excludeField(field, true);

    assertThat(excluded).isFalse();
  }

  @Test
  public void testExcludeFieldWithExposeAnnotation() throws NoSuchFieldException {
    Excluder excluder = Excluder.DEFAULT.excludeFieldsWithoutExposeAnnotation();
    Field field = SampleClass.class.getDeclaredField("exposedField");

    boolean excluded = excluder.excludeField(field, true);

    assertThat(excluded).isFalse();
  }

  @Test
  public void testExcludeFieldWithoutExposeAnnotation() throws NoSuchFieldException {
    Excluder excluder = Excluder.DEFAULT.excludeFieldsWithoutExposeAnnotation();
    Field field = SampleClass.class.getDeclaredField("publicField");

    boolean excluded = excluder.excludeField(field, true);

    assertThat(excluded).isTrue();
  }

  @Test
  public void testExcludeFieldWithExposeSerializeFalse() throws NoSuchFieldException {
    Excluder excluder = Excluder.DEFAULT.excludeFieldsWithoutExposeAnnotation();
    Field field = SampleClass.class.getDeclaredField("serializeExcludedField");

    boolean excluded = excluder.excludeField(field, true);

    assertThat(excluded).isTrue();
  }

  @Test
  public void testExcludeFieldWithExposeDeserializeFalse() throws NoSuchFieldException {
    Excluder excluder = Excluder.DEFAULT.excludeFieldsWithoutExposeAnnotation();
    Field field = SampleClass.class.getDeclaredField("deserializeExcludedField");

    boolean excluded = excluder.excludeField(field, false);

    assertThat(excluded).isTrue();
  }

  @Test
  public void testExcludeFieldWithCustomStrategy() throws NoSuchFieldException {
    ExclusionStrategy strategy = new ExclusionStrategy() {
      @Override
      public boolean shouldSkipField(FieldAttributes f) {
        return f.getName().equals("publicField");
      }

      @Override
      public boolean shouldSkipClass(Class<?> clazz) {
        return false;
      }
    };

    Excluder excluder = Excluder.DEFAULT.withExclusionStrategy(strategy, true, false);
    Field field = SampleClass.class.getDeclaredField("publicField");

    boolean excluded = excluder.excludeField(field, true);

    assertThat(excluded).isTrue();
  }

  @Test
  public void testExcludeFieldNotExcludedBySerialization() throws NoSuchFieldException {
    ExclusionStrategy strategy = new ExclusionStrategy() {
      @Override
      public boolean shouldSkipField(FieldAttributes f) {
        return f.getName().equals("publicField");
      }

      @Override
      public boolean shouldSkipClass(Class<?> clazz) {
        return false;
      }
    };

    Excluder excluder = Excluder.DEFAULT.withExclusionStrategy(strategy, true, false);
    Field field = SampleClass.class.getDeclaredField("publicField");

    boolean excluded = excluder.excludeField(field, false);

    assertThat(excluded).isFalse();
  }

  @Test
  public void testExcludeClassWithVersion() {
    Excluder excluder = Excluder.DEFAULT.withVersion(1.0);

    boolean excluded = excluder.excludeClass(SinceClass.class, true);

    assertThat(excluded).isTrue();
  }

  @Test
  public void testExcludeClassWithValidVersion() {
    Excluder excluder = Excluder.DEFAULT.withVersion(2.0);

    boolean excluded = excluder.excludeClass(SinceClass.class, true);

    assertThat(excluded).isFalse();
  }

  @Test
  public void testExcludeClassWithUntilVersion() {
    Excluder excluder = Excluder.DEFAULT.withVersion(3.0);

    boolean excluded = excluder.excludeClass(UntilClass.class, true);

    assertThat(excluded).isTrue();
  }

  @Test
  public void testExcludeClassWithValidUntilVersion() {
    Excluder excluder = Excluder.DEFAULT.withVersion(1.5);

    boolean excluded = excluder.excludeClass(UntilClass.class, true);

    assertThat(excluded).isFalse();
  }

  @Test
  public void testExcludeInnerClass() {
    Excluder excluder = Excluder.DEFAULT.disableInnerClassSerialization();

    boolean excluded = excluder.excludeClass(InnerClass.class, true);

    assertThat(excluded).isTrue();
  }

  @Test
  public void testNotExcludeStaticNestedClass() {
    Excluder excluder = Excluder.DEFAULT.disableInnerClassSerialization();

    boolean excluded = excluder.excludeClass(StaticNestedClass.class, true);

    assertThat(excluded).isFalse();
  }

  @Test
  public void testExcludeAnonymousClass() {
    Runnable anonymous = new Runnable() {
      @Override
      public void run() {
      }
    };

    Excluder excluder = Excluder.DEFAULT;

    boolean excluded = excluder.excludeClass(anonymous.getClass(), false);

    assertThat(excluded).isTrue();
  }

  @Test
  public void testNotExcludeAnonymousClassForSerialization() {
    Runnable anonymous = new Runnable() {
      @Override
      public void run() {
      }
    };

    Excluder excluder = Excluder.DEFAULT;

    boolean excluded = excluder.excludeClass(anonymous.getClass(), true);

    assertThat(excluded).isFalse();
  }

  @Test
  public void testExcludeClassWithCustomStrategy() {
    ExclusionStrategy strategy = new ExclusionStrategy() {
      @Override
      public boolean shouldSkipField(FieldAttributes f) {
        return false;
      }

      @Override
      public boolean shouldSkipClass(Class<?> clazz) {
        return clazz == SampleClass.class;
      }
    };

    Excluder excluder = Excluder.DEFAULT.withExclusionStrategy(strategy, true, false);

    boolean excluded = excluder.excludeClass(SampleClass.class, true);

    assertThat(excluded).isTrue();
  }

  @Test
  public void testNotExcludeClassWithDeserializationStrategy() {
    ExclusionStrategy strategy = new ExclusionStrategy() {
      @Override
      public boolean shouldSkipField(FieldAttributes f) {
        return false;
      }

      @Override
      public boolean shouldSkipClass(Class<?> clazz) {
        return clazz == SampleClass.class;
      }
    };

    Excluder excluder = Excluder.DEFAULT.withExclusionStrategy(strategy, true, false);

    boolean excluded = excluder.excludeClass(SampleClass.class, false);

    assertThat(excluded).isFalse();
  }

  @Test
  public void testExcludeClassReturnsDefaultBehavior() {
    Excluder excluder = Excluder.DEFAULT;

    boolean excluded = excluder.excludeClass(SampleClass.class, true);

    assertThat(excluded).isFalse();
  }

  @Test
  public void testCreateTypeAdapterWithPartialExclusion() throws IOException {
    ExclusionStrategy serializeStrategy = new ExclusionStrategy() {
      @Override
      public boolean shouldSkipField(FieldAttributes f) {
        return false;
      }

      @Override
      public boolean shouldSkipClass(Class<?> clazz) {
        return clazz == SampleClass.class;
      }
    };

    Excluder excluder = Excluder.DEFAULT.withExclusionStrategy(serializeStrategy, true, false);
    Gson gson = new Gson();
    TypeToken<SampleClass> typeToken = TypeToken.get(SampleClass.class);

    TypeAdapter<SampleClass> adapter = excluder.create(gson, typeToken);

    assertThat(adapter).isNotNull();

    StringWriter writer = new StringWriter();
    JsonWriter jsonWriter = new JsonWriter(writer);
    adapter.write(jsonWriter, new SampleClass());
    assertThat(writer.toString()).isEqualTo("null");

    JsonReader reader = new JsonReader(new StringReader("{\"publicField\":\"test\"}"));
    SampleClass result = adapter.read(reader);
    assertThat(result).isNotNull();
  }

  @Test
  public void testExcludeFieldWithExcludedFieldType() throws NoSuchFieldException {
    ExclusionStrategy strategy = new ExclusionStrategy() {
      @Override
      public boolean shouldSkipField(FieldAttributes f) {
        return false;
      }

      @Override
      public boolean shouldSkipClass(Class<?> clazz) {
        return clazz == String.class;
      }
    };

    Excluder excluder = Excluder.DEFAULT.withExclusionStrategy(strategy, true, false);
    Field field = SampleClass.class.getDeclaredField("publicField");

    boolean excluded = excluder.excludeField(field, true);

    assertThat(excluded).isTrue();
  }

  @Test
  public void testExcludeFieldWithMultipleStrategies() throws NoSuchFieldException {
    ExclusionStrategy strategy1 = new ExclusionStrategy() {
      @Override
      public boolean shouldSkipField(FieldAttributes f) {
        return false;
      }

      @Override
      public boolean shouldSkipClass(Class<?> clazz) {
        return false;
      }
    };

    ExclusionStrategy strategy2 = new ExclusionStrategy() {
      @Override
      public boolean shouldSkipField(FieldAttributes f) {
        return f.getName().equals("publicField");
      }

      @Override
      public boolean shouldSkipClass(Class<?> clazz) {
        return false;
      }
    };

    Excluder excluder = Excluder.DEFAULT
        .withExclusionStrategy(strategy1, true, false)
        .withExclusionStrategy(strategy2, true, false);
    Field field = SampleClass.class.getDeclaredField("publicField");

    boolean excluded = excluder.excludeField(field, true);

    assertThat(excluded).isTrue();
  }

  @Test
  public void testExcludeClassWithMultipleStrategies() {
    ExclusionStrategy strategy1 = new ExclusionStrategy() {
      @Override
      public boolean shouldSkipField(FieldAttributes f) {
        return false;
      }

      @Override
      public boolean shouldSkipClass(Class<?> clazz) {
        return false;
      }
    };

    ExclusionStrategy strategy2 = new ExclusionStrategy() {
      @Override
      public boolean shouldSkipField(FieldAttributes f) {
        return false;
      }

      @Override
      public boolean shouldSkipClass(Class<?> clazz) {
        return clazz == SampleClass.class;
      }
    };

    Excluder excluder = Excluder.DEFAULT
        .withExclusionStrategy(strategy1, true, false)
        .withExclusionStrategy(strategy2, true, false);

    boolean excluded = excluder.excludeClass(SampleClass.class, true);

    assertThat(excluded).isTrue();
  }

  @Test
  public void testWithModifiersMultiple() throws NoSuchFieldException {
    Excluder excluder = Excluder.DEFAULT.withModifiers(Modifier.PRIVATE);
    Field field = SampleClass.class.getDeclaredField("privateField");

    boolean excluded = excluder.excludeField(field, true);

    assertThat(excluded).isTrue();
  }

  @Test
  public void testNotExcludeFieldWithDifferentModifier() throws NoSuchFieldException {
    Excluder excluder = Excluder.DEFAULT.withModifiers(Modifier.PRIVATE);
    Field field = SampleClass.class.getDeclaredField("publicField");

    boolean excluded = excluder.excludeField(field, true);

    assertThat(excluded).isFalse();
  }
}
