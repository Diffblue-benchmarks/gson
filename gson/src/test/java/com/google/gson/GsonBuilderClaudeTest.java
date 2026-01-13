/*
 * Copyright (C) 2022 Google Inc.
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

package com.google.gson;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Test;

/**
 * Tests for {@link GsonBuilder}.
 *
 * @author Claude
 */
public class GsonBuilderClaudeTest {

  // ==========================================================================
  // Helper classes for testing
  // ==========================================================================

  @SuppressWarnings("unused")
  private static class SimpleObject {
    private String name;
    private int value;

    public SimpleObject() {}

    public SimpleObject(String name, int value) {
      this.name = name;
      this.value = value;
    }
  }

  @SuppressWarnings("unused")
  private static class ClassWithModifiers {
    private String privateField = "private";
    public String publicField = "public";
    protected String protectedField = "protected";
    transient String transientField = "transient";
    static String staticField = "static";
    volatile String volatileField = "volatile";
  }

  @SuppressWarnings("unused")
  private static class ClassWithExposeAnnotation {
    @Expose private String exposedField = "exposed";
    private String notExposedField = "notExposed";
    @Expose(serialize = true, deserialize = true)
    private String fullyExposed = "fullyExposed";
    @Expose(serialize = false, deserialize = true)
    private String deserializeOnly = "deserializeOnly";
  }

  @SuppressWarnings("unused")
  private static class VersionedClass {
    @Since(1.0)
    private String field1 = "field1";

    @Since(2.0)
    private String field2 = "field2";

    @Until(1.5)
    private String fieldUntil = "fieldUntil";

    private String noVersionField = "noVersion";
  }

  private static class OuterClass {
    @SuppressWarnings("unused")
    private String outerField = "outer";

    private class InnerClass {
      @SuppressWarnings("unused")
      private String innerField = "inner";
    }

    private static class StaticNestedClass {
      @SuppressWarnings("unused")
      private String nestedField = "nested";
    }
  }

  @SuppressWarnings("unused")
  private static class ObjectWithNulls {
    private String nullableField;
    private Integer nullableInteger;
  }

  @SuppressWarnings("unused")
  private static class Point {
    private int x;
    private int y;

    public Point() {}

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public String toString() {
      return "(" + x + "," + y + ")";
    }

    @Override
    public boolean equals(Object obj) {
      if (obj instanceof Point) {
        Point other = (Point) obj;
        return x == other.x && y == other.y;
      }
      return false;
    }

    @Override
    public int hashCode() {
      return x * 31 + y;
    }
  }

  // ==========================================================================
  // Default constructor tests: <init>.()V
  // ==========================================================================

  @Test
  public void testDefaultConstructor_createsInstance() {
    GsonBuilder builder = new GsonBuilder();
    assertThat(builder).isNotNull();
  }

  @Test
  public void testDefaultConstructor_createProducesValidGson() {
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();
    assertThat(gson).isNotNull();
  }

  @Test
  public void testDefaultConstructor_defaultGsonHasDefaultSettings() {
    Gson gson = new GsonBuilder().create();
    assertThat(gson.serializeNulls()).isFalse();
    assertThat(gson.htmlSafe()).isTrue();
    assertThat(gson.fieldNamingStrategy()).isEqualTo(FieldNamingPolicy.IDENTITY);
  }

  // ==========================================================================
  // Constructor from Gson tests: <init>.(Lcom/google/gson/Gson;)V
  // This is package-private, so we test it via Gson.newBuilder()
  // ==========================================================================

  @Test
  public void testGsonConstructor_viaNewBuilder_preservesSerializeNulls() {
    Gson original = new GsonBuilder().serializeNulls().create();
    Gson rebuilt = original.newBuilder().create();
    assertThat(rebuilt.serializeNulls()).isTrue();
  }

  @Test
  public void testGsonConstructor_viaNewBuilder_preservesHtmlSafe() {
    Gson original = new GsonBuilder().disableHtmlEscaping().create();
    Gson rebuilt = original.newBuilder().create();
    assertThat(rebuilt.htmlSafe()).isFalse();
  }

  @Test
  public void testGsonConstructor_viaNewBuilder_preservesFieldNamingStrategy() {
    Gson original = new GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
        .create();
    Gson rebuilt = original.newBuilder().create();
    assertThat(rebuilt.fieldNamingStrategy()).isEqualTo(FieldNamingPolicy.UPPER_CAMEL_CASE);
  }

  @Test
  public void testGsonConstructor_viaNewBuilder_preservesFormattingStyle() {
    Gson original = new GsonBuilder().setFormattingStyle(FormattingStyle.PRETTY).create();
    Gson rebuilt = original.newBuilder().create();
    String json = rebuilt.toJson(new SimpleObject("test", 1));
    assertThat(json).contains("\n");
  }

  @Test
  public void testGsonConstructor_viaNewBuilder_allowsModification() {
    Gson original = new GsonBuilder().create();
    assertThat(original.serializeNulls()).isFalse();

    Gson modified = original.newBuilder().serializeNulls().create();
    assertThat(modified.serializeNulls()).isTrue();
    assertThat(original.serializeNulls()).isFalse();
  }

  @Test
  public void testGsonConstructor_viaNewBuilder_preservesLongSerializationPolicy() {
    Gson original = new GsonBuilder()
        .setLongSerializationPolicy(LongSerializationPolicy.STRING)
        .create();
    Gson rebuilt = original.newBuilder().create();
    String json = rebuilt.toJson(Long.MAX_VALUE);
    assertThat(json).isEqualTo("\"" + Long.MAX_VALUE + "\"");
  }

  @Test
  public void testGsonConstructor_viaNewBuilder_preservesStrictness() {
    Gson original = new GsonBuilder().setStrictness(Strictness.STRICT).create();
    Gson rebuilt = original.newBuilder().create();
    StringReader sr = new StringReader("{}");
    JsonReader reader = rebuilt.newJsonReader(sr);
    assertThat(reader.getStrictness()).isEqualTo(Strictness.STRICT);
  }

  @Test
  public void testGsonConstructor_viaNewBuilder_preservesGenerateNonExecutableJson() {
    Gson original = new GsonBuilder().generateNonExecutableJson().create();
    Gson rebuilt = original.newBuilder().create();
    String json = rebuilt.toJson(new SimpleObject("test", 1));
    assertThat(json).startsWith(")]}'\n");
  }

  @Test
  public void testGsonConstructor_viaNewBuilder_preservesSerializeSpecialFloatingPointValues() {
    Gson original = new GsonBuilder().serializeSpecialFloatingPointValues().create();
    Gson rebuilt = original.newBuilder().create();
    String json = rebuilt.toJson(Double.NaN);
    assertThat(json).isEqualTo("NaN");
  }

  @Test
  public void testGsonConstructor_viaNewBuilder_preservesObjectToNumberStrategy() {
    Gson original = new GsonBuilder()
        .setObjectToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE)
        .create();
    Gson rebuilt = original.newBuilder().create();
    Object result = rebuilt.fromJson("42", Object.class);
    assertThat(result).isEqualTo(42L);
  }

  @Test
  public void testGsonConstructor_viaNewBuilder_preservesNumberToNumberStrategy() {
    Gson original = new GsonBuilder()
        .setNumberToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE)
        .create();
    Gson rebuilt = original.newBuilder().create();
    Number result = rebuilt.fromJson("42", Number.class);
    assertThat(result).isEqualTo(42L);
  }

  // ==========================================================================
  // setVersion tests
  // ==========================================================================

  @Test
  public void testSetVersion_returnsBuilder() {
    GsonBuilder builder = new GsonBuilder();
    GsonBuilder result = builder.setVersion(1.0);
    assertThat(result).isSameInstanceAs(builder);
  }

  @Test
  public void testSetVersion_includesFieldsUpToVersion() {
    Gson gson = new GsonBuilder().setVersion(1.0).create();
    String json = gson.toJson(new VersionedClass());
    assertThat(json).contains("field1");
    assertThat(json).doesNotContain("field2");
    assertThat(json).contains("fieldUntil");
    assertThat(json).contains("noVersion");
  }

  @Test
  public void testSetVersion_includesFieldsAtExactVersion() {
    Gson gson = new GsonBuilder().setVersion(2.0).create();
    String json = gson.toJson(new VersionedClass());
    assertThat(json).contains("field1");
    assertThat(json).contains("field2");
    assertThat(json).doesNotContain("fieldUntil");
    assertThat(json).contains("noVersion");
  }

  @Test
  public void testSetVersion_excludesFieldsAfterUntilVersion() {
    Gson gson = new GsonBuilder().setVersion(1.5).create();
    String json = gson.toJson(new VersionedClass());
    assertThat(json).contains("field1");
    assertThat(json).doesNotContain("field2");
    assertThat(json).doesNotContain("fieldUntil");
  }

  @Test
  public void testSetVersion_negativeVersion_throwsException() {
    GsonBuilder builder = new GsonBuilder();
    assertThrows(IllegalArgumentException.class, () -> builder.setVersion(-1.0));
  }

  @Test
  public void testSetVersion_nanVersion_throwsException() {
    GsonBuilder builder = new GsonBuilder();
    assertThrows(IllegalArgumentException.class, () -> builder.setVersion(Double.NaN));
  }

  @Test
  public void testSetVersion_zeroVersion_isValid() {
    Gson gson = new GsonBuilder().setVersion(0.0).create();
    assertThat(gson).isNotNull();
  }

  @Test
  public void testSetVersion_positiveInfinity_isValid() {
    Gson gson = new GsonBuilder().setVersion(Double.POSITIVE_INFINITY).create();
    String json = gson.toJson(new VersionedClass());
    assertThat(json).contains("field1");
    assertThat(json).contains("field2");
  }

  // ==========================================================================
  // excludeFieldsWithModifiers tests
  // ==========================================================================

  @Test
  public void testExcludeFieldsWithModifiers_returnsBuilder() {
    GsonBuilder builder = new GsonBuilder();
    GsonBuilder result = builder.excludeFieldsWithModifiers(Modifier.STATIC);
    assertThat(result).isSameInstanceAs(builder);
  }

  @Test
  public void testExcludeFieldsWithModifiers_excludesTransient() {
    Gson gson = new GsonBuilder()
        .excludeFieldsWithModifiers(Modifier.TRANSIENT)
        .create();
    String json = gson.toJson(new ClassWithModifiers());
    assertThat(json).doesNotContain("transientField");
    assertThat(json).contains("privateField");
    assertThat(json).contains("staticField");
  }

  @Test
  public void testExcludeFieldsWithModifiers_excludesStatic() {
    Gson gson = new GsonBuilder()
        .excludeFieldsWithModifiers(Modifier.STATIC)
        .create();
    String json = gson.toJson(new ClassWithModifiers());
    assertThat(json).doesNotContain("staticField");
    assertThat(json).contains("privateField");
  }

  @Test
  public void testExcludeFieldsWithModifiers_excludesMultipleModifiers() {
    Gson gson = new GsonBuilder()
        .excludeFieldsWithModifiers(Modifier.STATIC, Modifier.TRANSIENT)
        .create();
    String json = gson.toJson(new ClassWithModifiers());
    assertThat(json).doesNotContain("staticField");
    assertThat(json).doesNotContain("transientField");
    assertThat(json).contains("privateField");
  }

  @Test
  public void testExcludeFieldsWithModifiers_excludesProtected() {
    Gson gson = new GsonBuilder()
        .excludeFieldsWithModifiers(Modifier.PROTECTED)
        .create();
    String json = gson.toJson(new ClassWithModifiers());
    assertThat(json).doesNotContain("protectedField");
    assertThat(json).contains("privateField");
  }

  @Test
  public void testExcludeFieldsWithModifiers_emptyArray_includesAllFields() {
    Gson gson = new GsonBuilder()
        .excludeFieldsWithModifiers()
        .create();
    String json = gson.toJson(new ClassWithModifiers());
    assertThat(json).contains("privateField");
    assertThat(json).contains("publicField");
    assertThat(json).contains("protectedField");
    assertThat(json).contains("transientField");
    assertThat(json).contains("staticField");
    assertThat(json).contains("volatileField");
  }

  @Test
  public void testExcludeFieldsWithModifiers_nullArray_throwsException() {
    GsonBuilder builder = new GsonBuilder();
    assertThrows(NullPointerException.class, () -> builder.excludeFieldsWithModifiers(null));
  }

  // ==========================================================================
  // generateNonExecutableJson tests
  // ==========================================================================

  @Test
  public void testGenerateNonExecutableJson_returnsBuilder() {
    GsonBuilder builder = new GsonBuilder();
    GsonBuilder result = builder.generateNonExecutableJson();
    assertThat(result).isSameInstanceAs(builder);
  }

  @Test
  public void testGenerateNonExecutableJson_addsPrefix() {
    Gson gson = new GsonBuilder().generateNonExecutableJson().create();
    String json = gson.toJson(new SimpleObject("test", 1));
    assertThat(json).startsWith(")]}'\n");
  }

  @Test
  public void testGenerateNonExecutableJson_prefixAddedToArray() {
    Gson gson = new GsonBuilder().generateNonExecutableJson().create();
    String json = gson.toJson(new int[] {1, 2, 3});
    assertThat(json).startsWith(")]}'\n");
    assertThat(json).contains("[1,2,3]");
  }

  @Test
  public void testGenerateNonExecutableJson_prefixAddedToObject() {
    Gson gson = new GsonBuilder().generateNonExecutableJson().create();
    JsonObject obj = new JsonObject();
    obj.addProperty("key", "value");
    String json = gson.toJson(obj);
    assertThat(json).startsWith(")]}'\n");
    assertThat(json).contains("{\"key\":\"value\"}");
  }

  @Test
  public void testGenerateNonExecutableJson_preservedInNewBuilder() {
    Gson original = new GsonBuilder().generateNonExecutableJson().create();
    Gson rebuilt = original.newBuilder().create();
    String json = rebuilt.toJson(new SimpleObject("test", 1));
    assertThat(json).startsWith(")]}'\n");
  }

  // ==========================================================================
  // excludeFieldsWithoutExposeAnnotation tests
  // ==========================================================================

  @Test
  public void testExcludeFieldsWithoutExposeAnnotation_returnsBuilder() {
    GsonBuilder builder = new GsonBuilder();
    GsonBuilder result = builder.excludeFieldsWithoutExposeAnnotation();
    assertThat(result).isSameInstanceAs(builder);
  }

  @Test
  public void testExcludeFieldsWithoutExposeAnnotation_onlyIncludesExposedFields() {
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(new ClassWithExposeAnnotation());
    assertThat(json).contains("exposedField");
    assertThat(json).contains("fullyExposed");
    assertThat(json).doesNotContain("notExposedField");
  }

  @Test
  public void testExcludeFieldsWithoutExposeAnnotation_respectsSerializeFlag() {
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(new ClassWithExposeAnnotation());
    assertThat(json).doesNotContain("deserializeOnly");
  }

  @Test
  public void testExcludeFieldsWithoutExposeAnnotation_respectsDeserializeFlag() {
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    String jsonInput = "{\"deserializeOnly\":\"test\",\"notExposedField\":\"ignored\"}";
    ClassWithExposeAnnotation result = gson.fromJson(jsonInput, ClassWithExposeAnnotation.class);
    assertThat(result.deserializeOnly).isEqualTo("test");
    assertThat(result.notExposedField).isEqualTo("notExposed");
  }

  // ==========================================================================
  // serializeNulls tests
  // ==========================================================================

  @Test
  public void testSerializeNulls_returnsBuilder() {
    GsonBuilder builder = new GsonBuilder();
    GsonBuilder result = builder.serializeNulls();
    assertThat(result).isSameInstanceAs(builder);
  }

  @Test
  public void testSerializeNulls_includesNullFieldsInOutput() {
    Gson gson = new GsonBuilder().serializeNulls().create();
    ObjectWithNulls obj = new ObjectWithNulls();
    String json = gson.toJson(obj);
    assertThat(json).contains("\"nullableField\":null");
    assertThat(json).contains("\"nullableInteger\":null");
  }

  @Test
  public void testSerializeNulls_defaultDoesNotIncludeNulls() {
    Gson gson = new GsonBuilder().create();
    ObjectWithNulls obj = new ObjectWithNulls();
    String json = gson.toJson(obj);
    assertThat(json).doesNotContain("nullableField");
    assertThat(json).doesNotContain("nullableInteger");
  }

  @Test
  public void testSerializeNulls_gsonReportsTrue() {
    Gson gson = new GsonBuilder().serializeNulls().create();
    assertThat(gson.serializeNulls()).isTrue();
  }

  // ==========================================================================
  // enableComplexMapKeySerialization tests
  // ==========================================================================

  @Test
  public void testEnableComplexMapKeySerialization_returnsBuilder() {
    GsonBuilder builder = new GsonBuilder();
    GsonBuilder result = builder.enableComplexMapKeySerialization();
    assertThat(result).isSameInstanceAs(builder);
  }

  @Test
  public void testEnableComplexMapKeySerialization_serializesComplexKeysAsArrays() {
    Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
    Map<Point, String> map = new LinkedHashMap<>();
    map.put(new Point(1, 2), "a");
    map.put(new Point(3, 4), "b");
    Type mapType = new TypeToken<Map<Point, String>>() {}.getType();
    String json = gson.toJson(map, mapType);
    assertThat(json).startsWith("[[");
    assertThat(json).contains("\"x\":1");
    assertThat(json).contains("\"y\":2");
  }

  @Test
  public void testEnableComplexMapKeySerialization_deserializesComplexKeysFromArrays() {
    Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
    String json = "[[{\"x\":1,\"y\":2},\"a\"],[{\"x\":3,\"y\":4},\"b\"]]";
    Type mapType = new TypeToken<Map<Point, String>>() {}.getType();
    Map<Point, String> result = gson.fromJson(json, mapType);
    assertThat(result).hasSize(2);
    assertThat(result.get(new Point(1, 2))).isEqualTo("a");
    assertThat(result.get(new Point(3, 4))).isEqualTo("b");
  }

  @Test
  public void testEnableComplexMapKeySerialization_simpleKeysStillWorkAsObjects() {
    Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
    Map<String, Integer> map = new LinkedHashMap<>();
    map.put("key1", 1);
    map.put("key2", 2);
    String json = gson.toJson(map);
    assertThat(json).isEqualTo("{\"key1\":1,\"key2\":2}");
  }

  // ==========================================================================
  // disableInnerClassSerialization tests
  // ==========================================================================

  @Test
  public void testDisableInnerClassSerialization_returnsBuilder() {
    GsonBuilder builder = new GsonBuilder();
    GsonBuilder result = builder.disableInnerClassSerialization();
    assertThat(result).isSameInstanceAs(builder);
  }

  @Test
  public void testDisableInnerClassSerialization_serializesInnerClassAsNull() {
    Gson gson = new GsonBuilder().disableInnerClassSerialization().create();
    OuterClass outer = new OuterClass();
    @SuppressWarnings("unused")
    OuterClass.InnerClass inner = outer.new InnerClass();
    String json = gson.toJson(inner);
    assertThat(json).isEqualTo("null");
  }

  @Test
  public void testDisableInnerClassSerialization_allowsStaticNestedClasses() {
    Gson gson = new GsonBuilder().disableInnerClassSerialization().create();
    OuterClass.StaticNestedClass nested = new OuterClass.StaticNestedClass();
    String json = gson.toJson(nested);
    assertThat(json).contains("nestedField");
    assertThat(json).isNotEqualTo("null");
  }

  @Test
  public void testDisableInnerClassSerialization_defaultIncludesInnerClasses() {
    Gson gson = new GsonBuilder().create();
    OuterClass outer = new OuterClass();
    OuterClass.InnerClass inner = outer.new InnerClass();
    String json = gson.toJson(inner);
    assertThat(json).contains("innerField");
  }

  // ==========================================================================
  // setLongSerializationPolicy tests
  // ==========================================================================

  @Test
  public void testSetLongSerializationPolicy_returnsBuilder() {
    GsonBuilder builder = new GsonBuilder();
    GsonBuilder result = builder.setLongSerializationPolicy(LongSerializationPolicy.DEFAULT);
    assertThat(result).isSameInstanceAs(builder);
  }

  @Test
  public void testSetLongSerializationPolicy_defaultSerializesAsNumber() {
    Gson gson = new GsonBuilder()
        .setLongSerializationPolicy(LongSerializationPolicy.DEFAULT)
        .create();
    String json = gson.toJson(Long.MAX_VALUE);
    assertThat(json).isEqualTo(String.valueOf(Long.MAX_VALUE));
  }

  @Test
  public void testSetLongSerializationPolicy_stringSerializesAsString() {
    Gson gson = new GsonBuilder()
        .setLongSerializationPolicy(LongSerializationPolicy.STRING)
        .create();
    String json = gson.toJson(Long.MAX_VALUE);
    assertThat(json).isEqualTo("\"" + Long.MAX_VALUE + "\"");
  }

  @Test
  public void testSetLongSerializationPolicy_nullPolicy_throwsException() {
    GsonBuilder builder = new GsonBuilder();
    assertThrows(NullPointerException.class, () -> builder.setLongSerializationPolicy(null));
  }

  @Test
  public void testSetLongSerializationPolicy_affectsPrimitiveLong() {
    Gson gson = new GsonBuilder()
        .setLongSerializationPolicy(LongSerializationPolicy.STRING)
        .create();
    long value = 123456789L;
    String json = gson.toJson(value);
    assertThat(json).isEqualTo("\"123456789\"");
  }

  // ==========================================================================
  // setFieldNamingPolicy tests
  // ==========================================================================

  @Test
  public void testSetFieldNamingPolicy_returnsBuilder() {
    GsonBuilder builder = new GsonBuilder();
    GsonBuilder result = builder.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY);
    assertThat(result).isSameInstanceAs(builder);
  }

  @Test
  public void testSetFieldNamingPolicy_identity() {
    Gson gson = new GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
        .create();
    assertThat(gson.fieldNamingStrategy()).isEqualTo(FieldNamingPolicy.IDENTITY);
  }

  @Test
  public void testSetFieldNamingPolicy_upperCamelCase() {
    Gson gson = new GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
        .create();
    String json = gson.toJson(new SimpleObject("test", 1));
    assertThat(json).contains("\"Name\"");
    assertThat(json).contains("\"Value\"");
  }

  @Test
  public void testSetFieldNamingPolicy_lowerCaseWithUnderscores() {
    Gson gson = new GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create();
    assertThat(gson.fieldNamingStrategy()).isEqualTo(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
  }

  @Test
  public void testSetFieldNamingPolicy_lowerCaseWithDashes() {
    Gson gson = new GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES)
        .create();
    assertThat(gson.fieldNamingStrategy()).isEqualTo(FieldNamingPolicy.LOWER_CASE_WITH_DASHES);
  }

  // ==========================================================================
  // setFieldNamingStrategy tests
  // ==========================================================================

  @Test
  public void testSetFieldNamingStrategy_returnsBuilder() {
    GsonBuilder builder = new GsonBuilder();
    FieldNamingStrategy strategy = field -> "custom_" + field.getName();
    GsonBuilder result = builder.setFieldNamingStrategy(strategy);
    assertThat(result).isSameInstanceAs(builder);
  }

  @Test
  public void testSetFieldNamingStrategy_customStrategy() {
    FieldNamingStrategy strategy = field -> "prefix_" + field.getName();
    Gson gson = new GsonBuilder().setFieldNamingStrategy(strategy).create();
    String json = gson.toJson(new SimpleObject("test", 1));
    assertThat(json).contains("\"prefix_name\"");
    assertThat(json).contains("\"prefix_value\"");
  }

  @Test
  public void testSetFieldNamingStrategy_nullStrategy_throwsException() {
    GsonBuilder builder = new GsonBuilder();
    assertThrows(NullPointerException.class, () -> builder.setFieldNamingStrategy(null));
  }

  @Test
  public void testSetFieldNamingStrategy_preservedInGson() {
    FieldNamingStrategy strategy = field -> "custom_" + field.getName();
    Gson gson = new GsonBuilder().setFieldNamingStrategy(strategy).create();
    assertThat(gson.fieldNamingStrategy()).isSameInstanceAs(strategy);
  }

  // ==========================================================================
  // setObjectToNumberStrategy tests
  // ==========================================================================

  @Test
  public void testSetObjectToNumberStrategy_returnsBuilder() {
    GsonBuilder builder = new GsonBuilder();
    GsonBuilder result = builder.setObjectToNumberStrategy(ToNumberPolicy.DOUBLE);
    assertThat(result).isSameInstanceAs(builder);
  }

  @Test
  public void testSetObjectToNumberStrategy_double() {
    Gson gson = new GsonBuilder()
        .setObjectToNumberStrategy(ToNumberPolicy.DOUBLE)
        .create();
    Object result = gson.fromJson("42", Object.class);
    assertThat(result).isInstanceOf(Double.class);
    assertThat(result).isEqualTo(42.0);
  }

  @Test
  public void testSetObjectToNumberStrategy_longOrDouble() {
    Gson gson = new GsonBuilder()
        .setObjectToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE)
        .create();
    Object intResult = gson.fromJson("42", Object.class);
    assertThat(intResult).isInstanceOf(Long.class);
    assertThat(intResult).isEqualTo(42L);

    Object doubleResult = gson.fromJson("42.5", Object.class);
    assertThat(doubleResult).isInstanceOf(Double.class);
    assertThat(doubleResult).isEqualTo(42.5);
  }

  @Test
  public void testSetObjectToNumberStrategy_bigDecimal() {
    Gson gson = new GsonBuilder()
        .setObjectToNumberStrategy(ToNumberPolicy.BIG_DECIMAL)
        .create();
    Object result = gson.fromJson("42.123", Object.class);
    assertThat(result).isInstanceOf(java.math.BigDecimal.class);
  }

  @Test
  public void testSetObjectToNumberStrategy_lazilyParsedNumber() {
    Gson gson = new GsonBuilder()
        .setObjectToNumberStrategy(ToNumberPolicy.LAZILY_PARSED_NUMBER)
        .create();
    Object result = gson.fromJson("42", Object.class);
    assertThat(result).isInstanceOf(Number.class);
  }

  @Test
  public void testSetObjectToNumberStrategy_nullStrategy_throwsException() {
    GsonBuilder builder = new GsonBuilder();
    assertThrows(NullPointerException.class, () -> builder.setObjectToNumberStrategy(null));
  }

  // ==========================================================================
  // setNumberToNumberStrategy tests
  // ==========================================================================

  @Test
  public void testSetNumberToNumberStrategy_returnsBuilder() {
    GsonBuilder builder = new GsonBuilder();
    GsonBuilder result = builder.setNumberToNumberStrategy(ToNumberPolicy.DOUBLE);
    assertThat(result).isSameInstanceAs(builder);
  }

  @Test
  public void testSetNumberToNumberStrategy_double() {
    Gson gson = new GsonBuilder()
        .setNumberToNumberStrategy(ToNumberPolicy.DOUBLE)
        .create();
    Number result = gson.fromJson("42", Number.class);
    assertThat(result).isInstanceOf(Double.class);
    assertThat(result).isEqualTo(42.0);
  }

  @Test
  public void testSetNumberToNumberStrategy_longOrDouble() {
    Gson gson = new GsonBuilder()
        .setNumberToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE)
        .create();
    Number intResult = gson.fromJson("42", Number.class);
    assertThat(intResult).isInstanceOf(Long.class);
    assertThat(intResult).isEqualTo(42L);
  }

  @Test
  public void testSetNumberToNumberStrategy_nullStrategy_throwsException() {
    GsonBuilder builder = new GsonBuilder();
    assertThrows(NullPointerException.class, () -> builder.setNumberToNumberStrategy(null));
  }

  // ==========================================================================
  // setExclusionStrategies tests
  // ==========================================================================

  @Test
  public void testSetExclusionStrategies_returnsBuilder() {
    GsonBuilder builder = new GsonBuilder();
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
    GsonBuilder result = builder.setExclusionStrategies(strategy);
    assertThat(result).isSameInstanceAs(builder);
  }

  @Test
  public void testSetExclusionStrategies_excludesFieldByName() {
    ExclusionStrategy strategy = new ExclusionStrategy() {
      @Override
      public boolean shouldSkipField(FieldAttributes f) {
        return f.getName().equals("name");
      }

      @Override
      public boolean shouldSkipClass(Class<?> clazz) {
        return false;
      }
    };
    Gson gson = new GsonBuilder().setExclusionStrategies(strategy).create();
    String json = gson.toJson(new SimpleObject("test", 42));
    assertThat(json).doesNotContain("name");
    assertThat(json).contains("value");
  }

  @Test
  public void testSetExclusionStrategies_excludesClass() {
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
    Gson gson = new GsonBuilder().setExclusionStrategies(strategy).create();
    String json = gson.toJson(new SimpleObject("test", 42));
    assertThat(json).doesNotContain("test");
    assertThat(json).contains("42");
  }

  @Test
  public void testSetExclusionStrategies_multipleStrategies() {
    ExclusionStrategy strategy1 = new ExclusionStrategy() {
      @Override
      public boolean shouldSkipField(FieldAttributes f) {
        return f.getName().equals("name");
      }

      @Override
      public boolean shouldSkipClass(Class<?> clazz) {
        return false;
      }
    };
    ExclusionStrategy strategy2 = new ExclusionStrategy() {
      @Override
      public boolean shouldSkipField(FieldAttributes f) {
        return f.getName().equals("value");
      }

      @Override
      public boolean shouldSkipClass(Class<?> clazz) {
        return false;
      }
    };
    Gson gson = new GsonBuilder().setExclusionStrategies(strategy1, strategy2).create();
    String json = gson.toJson(new SimpleObject("test", 42));
    assertThat(json).doesNotContain("name");
    assertThat(json).doesNotContain("value");
  }

  @Test
  public void testSetExclusionStrategies_nullArray_throwsException() {
    GsonBuilder builder = new GsonBuilder();
    assertThrows(NullPointerException.class, () -> builder.setExclusionStrategies((ExclusionStrategy[]) null));
  }

  // ==========================================================================
  // addSerializationExclusionStrategy tests
  // ==========================================================================

  @Test
  public void testAddSerializationExclusionStrategy_returnsBuilder() {
    GsonBuilder builder = new GsonBuilder();
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
    GsonBuilder result = builder.addSerializationExclusionStrategy(strategy);
    assertThat(result).isSameInstanceAs(builder);
  }

  @Test
  public void testAddSerializationExclusionStrategy_appliesOnlyToSerialization() {
    ExclusionStrategy strategy = new ExclusionStrategy() {
      @Override
      public boolean shouldSkipField(FieldAttributes f) {
        return f.getName().equals("name");
      }

      @Override
      public boolean shouldSkipClass(Class<?> clazz) {
        return false;
      }
    };
    Gson gson = new GsonBuilder().addSerializationExclusionStrategy(strategy).create();

    // Serialization should exclude 'name'
    String json = gson.toJson(new SimpleObject("test", 42));
    assertThat(json).doesNotContain("name");

    // Deserialization should include 'name'
    SimpleObject result = gson.fromJson("{\"name\":\"hello\",\"value\":99}", SimpleObject.class);
    assertThat(result.name).isEqualTo("hello");
  }

  @Test
  public void testAddSerializationExclusionStrategy_nullStrategy_throwsException() {
    GsonBuilder builder = new GsonBuilder();
    assertThrows(NullPointerException.class, () -> builder.addSerializationExclusionStrategy(null));
  }

  // ==========================================================================
  // addDeserializationExclusionStrategy tests
  // ==========================================================================

  @Test
  public void testAddDeserializationExclusionStrategy_returnsBuilder() {
    GsonBuilder builder = new GsonBuilder();
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
    GsonBuilder result = builder.addDeserializationExclusionStrategy(strategy);
    assertThat(result).isSameInstanceAs(builder);
  }

  @Test
  public void testAddDeserializationExclusionStrategy_appliesOnlyToDeserialization() {
    ExclusionStrategy strategy = new ExclusionStrategy() {
      @Override
      public boolean shouldSkipField(FieldAttributes f) {
        return f.getName().equals("name");
      }

      @Override
      public boolean shouldSkipClass(Class<?> clazz) {
        return false;
      }
    };
    Gson gson = new GsonBuilder().addDeserializationExclusionStrategy(strategy).create();

    // Serialization should include 'name'
    String json = gson.toJson(new SimpleObject("test", 42));
    assertThat(json).contains("name");

    // Deserialization should exclude 'name' - the field keeps its default value
    SimpleObject result = gson.fromJson("{\"name\":\"ignored\",\"value\":99}", SimpleObject.class);
    assertThat(result.name).isNull();
    assertThat(result.value).isEqualTo(99);
  }

  @Test
  public void testAddDeserializationExclusionStrategy_nullStrategy_throwsException() {
    GsonBuilder builder = new GsonBuilder();
    assertThrows(NullPointerException.class, () -> builder.addDeserializationExclusionStrategy(null));
  }

  // ==========================================================================
  // setPrettyPrinting tests
  // ==========================================================================

  @Test
  public void testSetPrettyPrinting_returnsBuilder() {
    GsonBuilder builder = new GsonBuilder();
    GsonBuilder result = builder.setPrettyPrinting();
    assertThat(result).isSameInstanceAs(builder);
  }

  @Test
  public void testSetPrettyPrinting_formatsWithNewlines() {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String json = gson.toJson(new SimpleObject("test", 42));
    assertThat(json).contains("\n");
  }

  @Test
  public void testSetPrettyPrinting_formatsWithIndentation() {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String json = gson.toJson(new SimpleObject("test", 42));
    assertThat(json).contains("  ");
  }

  @Test
  public void testSetPrettyPrinting_defaultIsCompact() {
    Gson gson = new GsonBuilder().create();
    String json = gson.toJson(new SimpleObject("test", 42));
    assertThat(json).doesNotContain("\n");
    assertThat(json).doesNotContain("  ");
  }

  // ==========================================================================
  // setFormattingStyle tests
  // ==========================================================================

  @Test
  public void testSetFormattingStyle_returnsBuilder() {
    GsonBuilder builder = new GsonBuilder();
    GsonBuilder result = builder.setFormattingStyle(FormattingStyle.COMPACT);
    assertThat(result).isSameInstanceAs(builder);
  }

  @Test
  public void testSetFormattingStyle_compact() {
    Gson gson = new GsonBuilder().setFormattingStyle(FormattingStyle.COMPACT).create();
    String json = gson.toJson(new SimpleObject("test", 42));
    assertThat(json).doesNotContain("\n");
    assertThat(json).doesNotContain(" ");
  }

  @Test
  public void testSetFormattingStyle_pretty() {
    Gson gson = new GsonBuilder().setFormattingStyle(FormattingStyle.PRETTY).create();
    String json = gson.toJson(new SimpleObject("test", 42));
    assertThat(json).contains("\n");
    assertThat(json).contains("  ");
  }

  @Test
  public void testSetFormattingStyle_nullStyle_throwsException() {
    GsonBuilder builder = new GsonBuilder();
    assertThrows(NullPointerException.class, () -> builder.setFormattingStyle(null));
  }

  @Test
  public void testSetFormattingStyle_customStyle() {
    FormattingStyle custom = FormattingStyle.COMPACT.withNewline("\r\n");
    Gson gson = new GsonBuilder().setFormattingStyle(custom).create();
    assertThat(gson).isNotNull();
  }

  // ==========================================================================
  // setLenient tests (deprecated, delegates to setStrictness)
  // ==========================================================================

  @Test
  public void testSetLenient_returnsBuilder() {
    GsonBuilder builder = new GsonBuilder();
    @SuppressWarnings("deprecation")
    GsonBuilder result = builder.setLenient();
    assertThat(result).isSameInstanceAs(builder);
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testSetLenient_allowsUnquotedStrings() {
    Gson gson = new GsonBuilder().setLenient().create();
    String result = gson.fromJson("unquoted", String.class);
    assertThat(result).isEqualTo("unquoted");
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testSetLenient_allowsComments() throws IOException {
    Gson gson = new GsonBuilder().setLenient().create();
    JsonReader reader = gson.newJsonReader(new StringReader("// comment\n\"value\""));
    assertThat(reader.getStrictness()).isEqualTo(Strictness.LENIENT);
  }

  // ==========================================================================
  // setStrictness tests
  // ==========================================================================

  @Test
  public void testSetStrictness_returnsBuilder() {
    GsonBuilder builder = new GsonBuilder();
    GsonBuilder result = builder.setStrictness(Strictness.STRICT);
    assertThat(result).isSameInstanceAs(builder);
  }

  @Test
  public void testSetStrictness_strict() {
    Gson gson = new GsonBuilder().setStrictness(Strictness.STRICT).create();
    JsonReader reader = gson.newJsonReader(new StringReader("{}"));
    assertThat(reader.getStrictness()).isEqualTo(Strictness.STRICT);
  }

  @Test
  public void testSetStrictness_lenient() {
    Gson gson = new GsonBuilder().setStrictness(Strictness.LENIENT).create();
    JsonReader reader = gson.newJsonReader(new StringReader("{}"));
    assertThat(reader.getStrictness()).isEqualTo(Strictness.LENIENT);
  }

  @Test
  public void testSetStrictness_legacyStrict() {
    Gson gson = new GsonBuilder().setStrictness(Strictness.LEGACY_STRICT).create();
    JsonReader reader = gson.newJsonReader(new StringReader("{}"));
    assertThat(reader.getStrictness()).isEqualTo(Strictness.LEGACY_STRICT);
  }

  @Test
  public void testSetStrictness_nullStrictness_throwsException() {
    GsonBuilder builder = new GsonBuilder();
    assertThrows(NullPointerException.class, () -> builder.setStrictness(null));
  }

  @Test
  public void testSetStrictness_affectsJsonWriter() throws IOException {
    Gson gson = new GsonBuilder().setStrictness(Strictness.STRICT).create();
    StringWriter sw = new StringWriter();
    JsonWriter writer = gson.newJsonWriter(sw);
    assertThat(writer.getStrictness()).isEqualTo(Strictness.STRICT);
  }

  // ==========================================================================
  // disableHtmlEscaping tests
  // ==========================================================================

  @Test
  public void testDisableHtmlEscaping_returnsBuilder() {
    GsonBuilder builder = new GsonBuilder();
    GsonBuilder result = builder.disableHtmlEscaping();
    assertThat(result).isSameInstanceAs(builder);
  }

  @Test
  public void testDisableHtmlEscaping_doesNotEscapeHtmlCharacters() {
    Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    String json = gson.toJson("<script>alert('xss')</script>");
    assertThat(json).contains("<script>");
    assertThat(json).contains("</script>");
  }

  @Test
  public void testDisableHtmlEscaping_gsonReportsFalse() {
    Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    assertThat(gson.htmlSafe()).isFalse();
  }

  @Test
  public void testDisableHtmlEscaping_defaultEscapesHtml() {
    Gson gson = new GsonBuilder().create();
    String json = gson.toJson("<script>");
    assertThat(json).doesNotContain("<");
    assertThat(json).doesNotContain(">");
  }

  @Test
  public void testDisableHtmlEscaping_preservesAmpersand() {
    Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    String json = gson.toJson("A & B");
    assertThat(json).contains("&");
    assertThat(json).doesNotContain("\\u0026");
  }

  // ==========================================================================
  // setDateFormat(String) tests
  // ==========================================================================

  @Test
  public void testSetDateFormatString_returnsBuilder() {
    GsonBuilder builder = new GsonBuilder();
    GsonBuilder result = builder.setDateFormat("yyyy-MM-dd");
    assertThat(result).isSameInstanceAs(builder);
  }

  @Test
  public void testSetDateFormatString_formatsDateAccordingly() {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    Date date = new Date(0);
    String json = gson.toJson(date);
    assertThat(json).contains("1970");
  }

  @Test
  public void testSetDateFormatString_invalidPattern_throwsException() {
    GsonBuilder builder = new GsonBuilder();
    assertThrows(IllegalArgumentException.class, () -> builder.setDateFormat("invalid{pattern"));
  }

  @Test
  public void testSetDateFormatString_nullPatternResetsToDefault() {
    Gson gson = new GsonBuilder()
        .setDateFormat("yyyy-MM-dd")
        .setDateFormat((String) null)
        .create();
    Date date = new Date(0);
    String json = gson.toJson(date);
    assertThat(json).isNotEmpty();
  }

  @Test
  public void testSetDateFormatString_parsesDateWithPattern() {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    Date date = gson.fromJson("\"1970-01-01\"", Date.class);
    assertThat(date).isNotNull();
  }

  // ==========================================================================
  // setDateFormat(int) tests
  // ==========================================================================

  @Test
  @SuppressWarnings("deprecation")
  public void testSetDateFormatInt_returnsBuilder() {
    GsonBuilder builder = new GsonBuilder();
    GsonBuilder result = builder.setDateFormat(DateFormat.MEDIUM);
    assertThat(result).isSameInstanceAs(builder);
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testSetDateFormatInt_usesDateStyle() {
    Gson gson = new GsonBuilder().setDateFormat(DateFormat.SHORT).create();
    Date date = new Date(0);
    String json = gson.toJson(date);
    assertThat(json).isNotEmpty();
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testSetDateFormatInt_invalidStyle_throwsException() {
    GsonBuilder builder = new GsonBuilder();
    assertThrows(IllegalArgumentException.class, () -> builder.setDateFormat(-1));
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testSetDateFormatInt_invalidStyleTooHigh_throwsException() {
    GsonBuilder builder = new GsonBuilder();
    assertThrows(IllegalArgumentException.class, () -> builder.setDateFormat(4));
  }

  // ==========================================================================
  // setDateFormat(int, int) tests
  // ==========================================================================

  @Test
  public void testSetDateFormatIntInt_returnsBuilder() {
    GsonBuilder builder = new GsonBuilder();
    GsonBuilder result = builder.setDateFormat(DateFormat.MEDIUM, DateFormat.SHORT);
    assertThat(result).isSameInstanceAs(builder);
  }

  @Test
  public void testSetDateFormatIntInt_usesDateAndTimeStyle() {
    Gson gson = new GsonBuilder()
        .setDateFormat(DateFormat.MEDIUM, DateFormat.SHORT)
        .create();
    Date date = new Date(0);
    String json = gson.toJson(date);
    assertThat(json).isNotEmpty();
  }

  @Test
  public void testSetDateFormatIntInt_invalidDateStyle_throwsException() {
    GsonBuilder builder = new GsonBuilder();
    assertThrows(IllegalArgumentException.class, () -> builder.setDateFormat(-1, DateFormat.SHORT));
  }

  @Test
  public void testSetDateFormatIntInt_invalidTimeStyle_throwsException() {
    GsonBuilder builder = new GsonBuilder();
    assertThrows(IllegalArgumentException.class, () -> builder.setDateFormat(DateFormat.SHORT, -1));
  }

  @Test
  public void testSetDateFormatIntInt_allValidStyles() {
    int[] validStyles = {DateFormat.FULL, DateFormat.LONG, DateFormat.MEDIUM, DateFormat.SHORT};
    for (int dateStyle : validStyles) {
      for (int timeStyle : validStyles) {
        Gson gson = new GsonBuilder().setDateFormat(dateStyle, timeStyle).create();
        assertThat(gson).isNotNull();
      }
    }
  }

  // ==========================================================================
  // registerTypeAdapter tests
  // ==========================================================================

  @Test
  public void testRegisterTypeAdapter_returnsBuilder() {
    GsonBuilder builder = new GsonBuilder();
    TypeAdapter<String> adapter = new TypeAdapter<String>() {
      @Override
      public void write(JsonWriter out, String value) throws IOException {
        out.value(value);
      }

      @Override
      public String read(JsonReader in) throws IOException {
        return in.nextString();
      }
    };
    GsonBuilder result = builder.registerTypeAdapter(String.class, adapter);
    assertThat(result).isSameInstanceAs(builder);
  }

  @Test
  public void testRegisterTypeAdapter_customTypeAdapter() {
    TypeAdapter<Point> adapter = new TypeAdapter<Point>() {
      @Override
      public void write(JsonWriter out, Point value) throws IOException {
        out.value(value.x + "," + value.y);
      }

      @Override
      public Point read(JsonReader in) throws IOException {
        String[] parts = in.nextString().split(",");
        return new Point(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
      }
    };
    Gson gson = new GsonBuilder().registerTypeAdapter(Point.class, adapter).create();

    Point point = new Point(3, 4);
    String json = gson.toJson(point);
    assertThat(json).isEqualTo("\"3,4\"");

    Point parsed = gson.fromJson("\"5,6\"", Point.class);
    assertThat(parsed.x).isEqualTo(5);
    assertThat(parsed.y).isEqualTo(6);
  }

  @Test
  public void testRegisterTypeAdapter_jsonSerializer() {
    JsonSerializer<Point> serializer = (src, typeOfSrc, context) ->
        new JsonPrimitive(src.x + ":" + src.y);
    Gson gson = new GsonBuilder().registerTypeAdapter(Point.class, serializer).create();
    String json = gson.toJson(new Point(1, 2));
    assertThat(json).isEqualTo("\"1:2\"");
  }

  @Test
  public void testRegisterTypeAdapter_jsonDeserializer() {
    JsonDeserializer<Point> deserializer = (json, typeOfT, context) -> {
      String[] parts = json.getAsString().split(":");
      return new Point(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    };
    Gson gson = new GsonBuilder().registerTypeAdapter(Point.class, deserializer).create();
    Point point = gson.fromJson("\"7:8\"", Point.class);
    assertThat(point.x).isEqualTo(7);
    assertThat(point.y).isEqualTo(8);
  }

  @Test
  public void testRegisterTypeAdapter_instanceCreator() {
    InstanceCreator<Point> creator = type -> new Point(0, 0);
    Gson gson = new GsonBuilder().registerTypeAdapter(Point.class, creator).create();
    Point point = gson.fromJson("{\"x\":9,\"y\":10}", Point.class);
    assertThat(point.x).isEqualTo(9);
    assertThat(point.y).isEqualTo(10);
  }

  @Test
  public void testRegisterTypeAdapter_nullType_throwsException() {
    GsonBuilder builder = new GsonBuilder();
    TypeAdapter<String> adapter = new TypeAdapter<String>() {
      @Override
      public void write(JsonWriter out, String value) throws IOException {}

      @Override
      public String read(JsonReader in) throws IOException {
        return null;
      }
    };
    assertThrows(NullPointerException.class, () -> builder.registerTypeAdapter(null, adapter));
  }

  @Test
  public void testRegisterTypeAdapter_nullAdapter_throwsException() {
    GsonBuilder builder = new GsonBuilder();
    assertThrows(NullPointerException.class, () -> builder.registerTypeAdapter(String.class, null));
  }

  @Test
  public void testRegisterTypeAdapter_invalidAdapter_throwsException() {
    GsonBuilder builder = new GsonBuilder();
    Object notAnAdapter = new Object();
    assertThrows(IllegalArgumentException.class, () -> builder.registerTypeAdapter(String.class, notAnAdapter));
  }

  @Test
  public void testRegisterTypeAdapter_objectClass_throwsException() {
    GsonBuilder builder = new GsonBuilder();
    TypeAdapter<Object> adapter = new TypeAdapter<Object>() {
      @Override
      public void write(JsonWriter out, Object value) throws IOException {}

      @Override
      public Object read(JsonReader in) throws IOException {
        return null;
      }
    };
    assertThrows(IllegalArgumentException.class, () -> builder.registerTypeAdapter(Object.class, adapter));
  }

  // ==========================================================================
  // registerTypeAdapterFactory tests
  // ==========================================================================

  @Test
  public void testRegisterTypeAdapterFactory_returnsBuilder() {
    GsonBuilder builder = new GsonBuilder();
    TypeAdapterFactory factory = new TypeAdapterFactory() {
      @Override
      public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        return null;
      }
    };
    GsonBuilder result = builder.registerTypeAdapterFactory(factory);
    assertThat(result).isSameInstanceAs(builder);
  }

  @Test
  public void testRegisterTypeAdapterFactory_customFactory() {
    final int[] callCount = {0};
    TypeAdapterFactory factory = new TypeAdapterFactory() {
      @Override
      public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        if (type.getRawType() == Point.class) {
          callCount[0]++;
          @SuppressWarnings("unchecked")
          TypeAdapter<T> adapter = (TypeAdapter<T>) new TypeAdapter<Point>() {
            @Override
            public void write(JsonWriter out, Point value) throws IOException {
              out.value(value.toString());
            }

            @Override
            public Point read(JsonReader in) throws IOException {
              return new Point(0, 0);
            }
          };
          return adapter;
        }
        return null;
      }
    };
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
    String json = gson.toJson(new Point(1, 2));
    assertThat(json).isEqualTo("\"(1,2)\"");
    assertThat(callCount[0]).isEqualTo(1);
  }

  @Test
  public void testRegisterTypeAdapterFactory_nullFactory_throwsException() {
    GsonBuilder builder = new GsonBuilder();
    assertThrows(NullPointerException.class, () -> builder.registerTypeAdapterFactory(null));
  }

  // ==========================================================================
  // registerTypeHierarchyAdapter tests
  // ==========================================================================

  @Test
  public void testRegisterTypeHierarchyAdapter_returnsBuilder() {
    GsonBuilder builder = new GsonBuilder();
    TypeAdapter<Number> adapter = new TypeAdapter<Number>() {
      @Override
      public void write(JsonWriter out, Number value) throws IOException {
        out.value(value);
      }

      @Override
      public Number read(JsonReader in) throws IOException {
        return in.nextDouble();
      }
    };
    GsonBuilder result = builder.registerTypeHierarchyAdapter(Number.class, adapter);
    assertThat(result).isSameInstanceAs(builder);
  }

  @Test
  public void testRegisterTypeHierarchyAdapter_appliesToSubclasses() {
    JsonSerializer<Number> serializer = (src, typeOfSrc, context) ->
        new JsonPrimitive("NUM:" + src);
    Gson gson = new GsonBuilder()
        .registerTypeHierarchyAdapter(Number.class, serializer)
        .create();

    String intJson = gson.toJson(42);
    assertThat(intJson).isEqualTo("\"NUM:42\"");

    String doubleJson = gson.toJson(3.14);
    assertThat(doubleJson).isEqualTo("\"NUM:3.14\"");

    String longJson = gson.toJson(Long.MAX_VALUE);
    assertThat(longJson).isEqualTo("\"NUM:" + Long.MAX_VALUE + "\"");
  }

  @Test
  public void testRegisterTypeHierarchyAdapter_nullBaseType_throwsException() {
    GsonBuilder builder = new GsonBuilder();
    TypeAdapter<Number> adapter = new TypeAdapter<Number>() {
      @Override
      public void write(JsonWriter out, Number value) throws IOException {}

      @Override
      public Number read(JsonReader in) throws IOException {
        return null;
      }
    };
    assertThrows(NullPointerException.class, () -> builder.registerTypeHierarchyAdapter(null, adapter));
  }

  @Test
  public void testRegisterTypeHierarchyAdapter_nullAdapter_throwsException() {
    GsonBuilder builder = new GsonBuilder();
    assertThrows(NullPointerException.class, () -> builder.registerTypeHierarchyAdapter(Number.class, null));
  }

  @Test
  public void testRegisterTypeHierarchyAdapter_invalidAdapter_throwsException() {
    GsonBuilder builder = new GsonBuilder();
    Object notAnAdapter = new Object();
    assertThrows(IllegalArgumentException.class, () -> builder.registerTypeHierarchyAdapter(Number.class, notAnAdapter));
  }

  // ==========================================================================
  // serializeSpecialFloatingPointValues tests
  // ==========================================================================

  @Test
  public void testSerializeSpecialFloatingPointValues_returnsBuilder() {
    GsonBuilder builder = new GsonBuilder();
    GsonBuilder result = builder.serializeSpecialFloatingPointValues();
    assertThat(result).isSameInstanceAs(builder);
  }

  @Test
  public void testSerializeSpecialFloatingPointValues_serializesNaN() {
    Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues().create();
    String json = gson.toJson(Double.NaN);
    assertThat(json).isEqualTo("NaN");
  }

  @Test
  public void testSerializeSpecialFloatingPointValues_serializesPositiveInfinity() {
    Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues().create();
    String json = gson.toJson(Double.POSITIVE_INFINITY);
    assertThat(json).isEqualTo("Infinity");
  }

  @Test
  public void testSerializeSpecialFloatingPointValues_serializesNegativeInfinity() {
    Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues().create();
    String json = gson.toJson(Double.NEGATIVE_INFINITY);
    assertThat(json).isEqualTo("-Infinity");
  }

  @Test
  public void testSerializeSpecialFloatingPointValues_floatNaN() {
    Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues().create();
    String json = gson.toJson(Float.NaN);
    assertThat(json).isEqualTo("NaN");
  }

  @Test
  public void testSerializeSpecialFloatingPointValues_floatInfinity() {
    Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues().create();
    String json = gson.toJson(Float.POSITIVE_INFINITY);
    assertThat(json).isEqualTo("Infinity");
  }

  @Test
  public void testSerializeSpecialFloatingPointValues_defaultThrowsForNaN() {
    Gson gson = new GsonBuilder().create();
    assertThrows(IllegalArgumentException.class, () -> gson.toJson(Double.NaN));
  }

  @Test
  public void testSerializeSpecialFloatingPointValues_defaultThrowsForInfinity() {
    Gson gson = new GsonBuilder().create();
    assertThrows(IllegalArgumentException.class, () -> gson.toJson(Double.POSITIVE_INFINITY));
  }

  // ==========================================================================
  // disableJdkUnsafe tests
  // ==========================================================================

  @Test
  public void testDisableJdkUnsafe_returnsBuilder() {
    GsonBuilder builder = new GsonBuilder();
    GsonBuilder result = builder.disableJdkUnsafe();
    assertThat(result).isSameInstanceAs(builder);
  }

  @Test
  public void testDisableJdkUnsafe_stillWorksForClassesWithDefaultConstructor() {
    Gson gson = new GsonBuilder().disableJdkUnsafe().create();
    SimpleObject obj = gson.fromJson("{\"name\":\"test\",\"value\":42}", SimpleObject.class);
    assertThat(obj.name).isEqualTo("test");
    assertThat(obj.value).isEqualTo(42);
  }

  // ==========================================================================
  // addReflectionAccessFilter tests
  // ==========================================================================

  @Test
  public void testAddReflectionAccessFilter_returnsBuilder() {
    GsonBuilder builder = new GsonBuilder();
    ReflectionAccessFilter filter = new ReflectionAccessFilter() {
      @Override
      public FilterResult check(Class<?> rawClass) {
        return FilterResult.ALLOW;
      }
    };
    GsonBuilder result = builder.addReflectionAccessFilter(filter);
    assertThat(result).isSameInstanceAs(builder);
  }

  @Test
  public void testAddReflectionAccessFilter_filterBlocksClass() {
    ReflectionAccessFilter filter = new ReflectionAccessFilter() {
      @Override
      public FilterResult check(Class<?> rawClass) {
        if (rawClass == Point.class) {
          return FilterResult.BLOCK_ALL;
        }
        return FilterResult.ALLOW;
      }
    };
    Gson gson = new GsonBuilder().addReflectionAccessFilter(filter).create();
    assertThrows(JsonIOException.class, () -> gson.fromJson("{\"x\":1,\"y\":2}", Point.class));
  }

  @Test
  public void testAddReflectionAccessFilter_nullFilter_throwsException() {
    GsonBuilder builder = new GsonBuilder();
    assertThrows(NullPointerException.class, () -> builder.addReflectionAccessFilter(null));
  }

  @Test
  public void testAddReflectionAccessFilter_multipleFiltersInReverseOrder() {
    final StringBuilder order = new StringBuilder();
    ReflectionAccessFilter filter1 = new ReflectionAccessFilter() {
      @Override
      public FilterResult check(Class<?> rawClass) {
        order.append("1");
        return FilterResult.INDECISIVE;
      }
    };
    ReflectionAccessFilter filter2 = new ReflectionAccessFilter() {
      @Override
      public FilterResult check(Class<?> rawClass) {
        order.append("2");
        return FilterResult.INDECISIVE;
      }
    };
    Gson gson = new GsonBuilder()
        .addReflectionAccessFilter(filter1)
        .addReflectionAccessFilter(filter2)
        .create();
    @SuppressWarnings("unused")
    Point unused = gson.fromJson("{\"x\":1,\"y\":2}", Point.class);
    // Filters are invoked in reverse order (most recently added first)
    assertThat(order.toString()).startsWith("2");
  }

  // ==========================================================================
  // create tests
  // ==========================================================================

  @Test
  public void testCreate_returnsNonNull() {
    Gson gson = new GsonBuilder().create();
    assertThat(gson).isNotNull();
  }

  @Test
  public void testCreate_canBeCalledMultipleTimes() {
    GsonBuilder builder = new GsonBuilder().serializeNulls();
    Gson gson1 = builder.create();
    Gson gson2 = builder.create();
    assertThat(gson1).isNotSameInstanceAs(gson2);
    assertThat(gson1.serializeNulls()).isTrue();
    assertThat(gson2.serializeNulls()).isTrue();
  }

  @Test
  public void testCreate_doesNotAffectBuilder() {
    GsonBuilder builder = new GsonBuilder();
    Gson gson1 = builder.create();
    builder.serializeNulls();
    Gson gson2 = builder.create();
    assertThat(gson1.serializeNulls()).isFalse();
    assertThat(gson2.serializeNulls()).isTrue();
  }

  @Test
  public void testCreate_combinesAllSettings() {
    Gson gson = new GsonBuilder()
        .serializeNulls()
        .disableHtmlEscaping()
        .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
        .setLongSerializationPolicy(LongSerializationPolicy.STRING)
        .setFormattingStyle(FormattingStyle.PRETTY)
        .setStrictness(Strictness.LENIENT)
        .create();

    assertThat(gson.serializeNulls()).isTrue();
    assertThat(gson.htmlSafe()).isFalse();
    assertThat(gson.fieldNamingStrategy()).isEqualTo(FieldNamingPolicy.UPPER_CAMEL_CASE);
  }

  @Test
  public void testCreate_preservesTypeAdapters() {
    TypeAdapter<Point> adapter = new TypeAdapter<Point>() {
      @Override
      public void write(JsonWriter out, Point value) throws IOException {
        out.value("custom");
      }

      @Override
      public Point read(JsonReader in) throws IOException {
        @SuppressWarnings("unused")
        String unused = in.nextString();
        return new Point(0, 0);
      }
    };
    Gson gson = new GsonBuilder().registerTypeAdapter(Point.class, adapter).create();
    String json = gson.toJson(new Point(1, 2));
    assertThat(json).isEqualTo("\"custom\"");
  }

  // ==========================================================================
  // Builder chaining tests
  // ==========================================================================

  @Test
  public void testBuilderChaining_allMethodsReturnBuilder() {
    GsonBuilder builder = new GsonBuilder()
        .setVersion(1.0)
        .excludeFieldsWithModifiers(Modifier.STATIC)
        .generateNonExecutableJson()
        .excludeFieldsWithoutExposeAnnotation()
        .serializeNulls()
        .enableComplexMapKeySerialization()
        .disableInnerClassSerialization()
        .setLongSerializationPolicy(LongSerializationPolicy.DEFAULT)
        .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
        .setFieldNamingStrategy(FieldNamingPolicy.IDENTITY)
        .setObjectToNumberStrategy(ToNumberPolicy.DOUBLE)
        .setNumberToNumberStrategy(ToNumberPolicy.DOUBLE)
        .setPrettyPrinting()
        .setFormattingStyle(FormattingStyle.PRETTY)
        .setStrictness(Strictness.LENIENT)
        .disableHtmlEscaping()
        .setDateFormat("yyyy-MM-dd")
        .serializeSpecialFloatingPointValues()
        .disableJdkUnsafe();

    Gson gson = builder.create();
    assertThat(gson).isNotNull();
  }

  // ==========================================================================
  // Edge case and combination tests
  // ==========================================================================

  @Test
  public void testMultipleVersionCalls_lastOneWins() {
    Gson gson = new GsonBuilder()
        .setVersion(1.0)
        .setVersion(2.0)
        .create();
    String json = gson.toJson(new VersionedClass());
    assertThat(json).contains("field2");
  }

  @Test
  public void testMultipleDateFormatCalls_lastOneWins() {
    Gson gson = new GsonBuilder()
        .setDateFormat("yyyy-MM-dd")
        .setDateFormat(DateFormat.SHORT, DateFormat.SHORT)
        .create();
    assertThat(gson).isNotNull();
  }

  @Test
  public void testMultipleFieldNamingPolicyCalls_lastOneWins() {
    Gson gson = new GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create();
    assertThat(gson.fieldNamingStrategy()).isEqualTo(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
  }

  @Test
  public void testExclusionStrategiesAreCumulative() {
    ExclusionStrategy strategy1 = new ExclusionStrategy() {
      @Override
      public boolean shouldSkipField(FieldAttributes f) {
        return f.getName().equals("name");
      }

      @Override
      public boolean shouldSkipClass(Class<?> clazz) {
        return false;
      }
    };
    ExclusionStrategy strategy2 = new ExclusionStrategy() {
      @Override
      public boolean shouldSkipField(FieldAttributes f) {
        return f.getName().equals("value");
      }

      @Override
      public boolean shouldSkipClass(Class<?> clazz) {
        return false;
      }
    };

    Gson gson = new GsonBuilder()
        .setExclusionStrategies(strategy1)
        .setExclusionStrategies(strategy2)
        .create();

    String json = gson.toJson(new SimpleObject("test", 42));
    assertThat(json).doesNotContain("name");
    assertThat(json).doesNotContain("value");
  }
}
