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

import com.google.gson.internal.Excluder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

/**
 * Tests for {@link Gson}.
 *
 * @author Claude
 */
public class GsonClaudeTest {

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

    public String getName() {
      return name;
    }

    public int getValue() {
      return value;
    }
  }

  @SuppressWarnings("unused")
  private static class NestedObject {
    private String id;
    private SimpleObject nested;

    public NestedObject() {}

    public NestedObject(String id, SimpleObject nested) {
      this.id = id;
      this.nested = nested;
    }
  }

  // ==========================================================================
  // Default constructor tests
  // ==========================================================================

  @Test
  public void testDefaultConstructor_createsInstance() {
    Gson gson = new Gson();
    assertThat(gson).isNotNull();
  }

  @Test
  public void testDefaultConstructor_hasDefaultFieldNamingStrategy() {
    Gson gson = new Gson();
    assertThat(gson.fieldNamingStrategy()).isEqualTo(FieldNamingPolicy.IDENTITY);
  }

  @Test
  public void testDefaultConstructor_serializeNullsIsFalse() {
    Gson gson = new Gson();
    assertThat(gson.serializeNulls()).isFalse();
  }

  @Test
  public void testDefaultConstructor_htmlSafeIsTrue() {
    Gson gson = new Gson();
    assertThat(gson.htmlSafe()).isTrue();
  }

  @Test
  public void testDefaultConstructor_excluderIsDefault() {
    Gson gson = new Gson();
    assertThat(gson.excluder()).isEqualTo(Excluder.DEFAULT);
  }

  // ==========================================================================
  // Complex constructor tests (via GsonBuilder)
  // ==========================================================================

  @Test
  public void testComplexConstructor_withSerializeNulls() {
    Gson gson = new GsonBuilder().serializeNulls().create();
    assertThat(gson.serializeNulls()).isTrue();
  }

  @Test
  public void testComplexConstructor_withDisableHtmlEscaping() {
    Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    assertThat(gson.htmlSafe()).isFalse();
  }

  @Test
  public void testComplexConstructor_withCustomFieldNaming() {
    Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
    assertThat(gson.fieldNamingStrategy()).isEqualTo(FieldNamingPolicy.UPPER_CAMEL_CASE);
  }

  @Test
  public void testComplexConstructor_withCustomFieldNamingStrategy() {
    FieldNamingStrategy customStrategy = field -> "custom_" + field.getName();
    Gson gson = new GsonBuilder().setFieldNamingStrategy(customStrategy).create();
    assertThat(gson.fieldNamingStrategy()).isSameInstanceAs(customStrategy);
  }

  @Test
  public void testComplexConstructor_withStrictness() {
    Gson gson = new GsonBuilder().setStrictness(Strictness.STRICT).create();
    // Verify strictness is applied during serialization
    String json = gson.toJson(new SimpleObject("test", 1));
    assertThat(json).isNotEmpty();
  }

  // ==========================================================================
  // newBuilder tests
  // ==========================================================================

  @Test
  public void testNewBuilder_returnsNonNull() {
    Gson gson = new Gson();
    GsonBuilder builder = gson.newBuilder();
    assertThat(builder).isNotNull();
  }

  @Test
  public void testNewBuilder_preservesSerializeNulls() {
    Gson gson = new GsonBuilder().serializeNulls().create();
    Gson newGson = gson.newBuilder().create();
    assertThat(newGson.serializeNulls()).isTrue();
  }

  @Test
  public void testNewBuilder_preservesHtmlSafe() {
    Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    Gson newGson = gson.newBuilder().create();
    assertThat(newGson.htmlSafe()).isFalse();
  }

  @Test
  public void testNewBuilder_preservesFieldNamingStrategy() {
    Gson gson =
        new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    Gson newGson = gson.newBuilder().create();
    assertThat(newGson.fieldNamingStrategy()).isEqualTo(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
  }

  @Test
  public void testNewBuilder_allowsModification() {
    Gson gson = new Gson();
    Gson modifiedGson = gson.newBuilder().serializeNulls().create();
    assertThat(gson.serializeNulls()).isFalse();
    assertThat(modifiedGson.serializeNulls()).isTrue();
  }

  // ==========================================================================
  // excluder tests
  // ==========================================================================

  @Test
  public void testExcluder_returnsExcluder() {
    Gson gson = new Gson();
    Excluder excluder = gson.excluder();
    assertThat(excluder).isNotNull();
  }

  @Test
  public void testExcluder_defaultExcluder() {
    Gson gson = new Gson();
    assertThat(gson.excluder()).isSameInstanceAs(Excluder.DEFAULT);
  }

  // ==========================================================================
  // fieldNamingStrategy tests
  // ==========================================================================

  @Test
  public void testFieldNamingStrategy_returnsDefault() {
    Gson gson = new Gson();
    assertThat(gson.fieldNamingStrategy()).isEqualTo(FieldNamingPolicy.IDENTITY);
  }

  @Test
  public void testFieldNamingStrategy_returnsCustomStrategy() {
    FieldNamingStrategy custom = field -> "prefix_" + field.getName();
    Gson gson = new GsonBuilder().setFieldNamingStrategy(custom).create();
    assertThat(gson.fieldNamingStrategy()).isSameInstanceAs(custom);
  }

  // ==========================================================================
  // serializeNulls tests
  // ==========================================================================

  @Test
  public void testSerializeNulls_defaultIsFalse() {
    Gson gson = new Gson();
    assertThat(gson.serializeNulls()).isFalse();
  }

  @Test
  public void testSerializeNulls_canBeEnabled() {
    Gson gson = new GsonBuilder().serializeNulls().create();
    assertThat(gson.serializeNulls()).isTrue();
  }

  @Test
  public void testSerializeNulls_affectsSerialization() {
    Gson gson = new GsonBuilder().serializeNulls().create();
    SimpleObject obj = new SimpleObject();
    String json = gson.toJson(obj);
    assertThat(json).contains("\"name\":null");
  }

  // ==========================================================================
  // htmlSafe tests
  // ==========================================================================

  @Test
  public void testHtmlSafe_defaultIsTrue() {
    Gson gson = new Gson();
    assertThat(gson.htmlSafe()).isTrue();
  }

  @Test
  public void testHtmlSafe_canBeDisabled() {
    Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    assertThat(gson.htmlSafe()).isFalse();
  }

  @Test
  public void testHtmlSafe_escapesHtmlCharacters() {
    Gson gson = new Gson();
    String json = gson.toJson("<script>alert('xss')</script>");
    assertThat(json).doesNotContain("<");
    assertThat(json).doesNotContain(">");
  }

  @Test
  public void testHtmlSafe_disabledDoesNotEscape() {
    Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    String json = gson.toJson("<script>");
    assertThat(json).contains("<script>");
  }

  // ==========================================================================
  // checkValidFloatingPoint tests
  // ==========================================================================

  @Test
  public void testCheckValidFloatingPoint_validDouble_noException() {
    // Should not throw for valid doubles
    Gson.checkValidFloatingPoint(1.0);
    Gson.checkValidFloatingPoint(0.0);
    Gson.checkValidFloatingPoint(-1.0);
    Gson.checkValidFloatingPoint(Double.MAX_VALUE);
    Gson.checkValidFloatingPoint(Double.MIN_VALUE);
  }

  @Test
  public void testCheckValidFloatingPoint_nan_throwsException() {
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> Gson.checkValidFloatingPoint(Double.NaN));
    assertThat(exception.getMessage()).contains("NaN");
  }

  @Test
  public void testCheckValidFloatingPoint_positiveInfinity_throwsException() {
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> Gson.checkValidFloatingPoint(Double.POSITIVE_INFINITY));
    assertThat(exception.getMessage()).contains("Infinity");
  }

  @Test
  public void testCheckValidFloatingPoint_negativeInfinity_throwsException() {
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> Gson.checkValidFloatingPoint(Double.NEGATIVE_INFINITY));
    assertThat(exception.getMessage()).contains("Infinity");
  }

  // ==========================================================================
  // getAdapter(TypeToken) tests
  // ==========================================================================

  @Test
  public void testGetAdapterTypeToken_string_returnsAdapter() {
    Gson gson = new Gson();
    TypeAdapter<String> adapter = gson.getAdapter(TypeToken.get(String.class));
    assertThat(adapter).isNotNull();
  }

  @Test
  public void testGetAdapterTypeToken_integer_returnsAdapter() {
    Gson gson = new Gson();
    TypeAdapter<Integer> adapter = gson.getAdapter(TypeToken.get(Integer.class));
    assertThat(adapter).isNotNull();
  }

  @Test
  public void testGetAdapterTypeToken_list_returnsAdapter() {
    Gson gson = new Gson();
    TypeAdapter<List<String>> adapter = gson.getAdapter(new TypeToken<List<String>>() {});
    assertThat(adapter).isNotNull();
  }

  @Test
  public void testGetAdapterTypeToken_nullType_throwsNullPointerException() {
    Gson gson = new Gson();
    assertThrows(NullPointerException.class, () -> gson.getAdapter((TypeToken<?>) null));
  }

  @Test
  public void testGetAdapterTypeToken_customObject_returnsAdapter() {
    Gson gson = new Gson();
    TypeAdapter<SimpleObject> adapter = gson.getAdapter(TypeToken.get(SimpleObject.class));
    assertThat(adapter).isNotNull();
  }

  @Test
  public void testGetAdapterTypeToken_returnsSameAdapterForSameType() {
    Gson gson = new Gson();
    TypeAdapter<String> adapter1 = gson.getAdapter(TypeToken.get(String.class));
    TypeAdapter<String> adapter2 = gson.getAdapter(TypeToken.get(String.class));
    assertThat(adapter1).isSameInstanceAs(adapter2);
  }

  // ==========================================================================
  // getAdapter(Class) tests
  // ==========================================================================

  @Test
  public void testGetAdapterClass_string_returnsAdapter() {
    Gson gson = new Gson();
    TypeAdapter<String> adapter = gson.getAdapter(String.class);
    assertThat(adapter).isNotNull();
  }

  @Test
  public void testGetAdapterClass_int_returnsAdapter() {
    Gson gson = new Gson();
    TypeAdapter<Integer> adapter = gson.getAdapter(int.class);
    assertThat(adapter).isNotNull();
  }

  @Test
  public void testGetAdapterClass_boolean_returnsAdapter() {
    Gson gson = new Gson();
    TypeAdapter<Boolean> adapter = gson.getAdapter(boolean.class);
    assertThat(adapter).isNotNull();
  }

  @Test
  public void testGetAdapterClass_customObject_returnsAdapter() {
    Gson gson = new Gson();
    TypeAdapter<SimpleObject> adapter = gson.getAdapter(SimpleObject.class);
    assertThat(adapter).isNotNull();
  }

  // ==========================================================================
  // getDelegateAdapter tests
  // ==========================================================================

  @Test
  public void testGetDelegateAdapter_nullSkipPast_throwsNullPointerException() {
    Gson gson = new Gson();
    assertThrows(
        NullPointerException.class,
        () -> gson.getDelegateAdapter(null, TypeToken.get(String.class)));
  }

  @Test
  public void testGetDelegateAdapter_nullType_throwsNullPointerException() {
    Gson gson = new Gson();
    TypeAdapterFactory factory = new TypeAdapterFactory() {
      @Override
      public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        return null;
      }
    };
    assertThrows(NullPointerException.class, () -> gson.getDelegateAdapter(factory, null));
  }

  @Test
  public void testGetDelegateAdapter_unregisteredFactory_fallsBackToGetAdapter() {
    Gson gson = new Gson();
    TypeAdapterFactory factory = new TypeAdapterFactory() {
      @Override
      public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        return null;
      }
    };
    // Should fall back to getAdapter behavior for unregistered factory
    TypeAdapter<String> adapter = gson.getDelegateAdapter(factory, TypeToken.get(String.class));
    assertThat(adapter).isNotNull();
  }

  @Test
  public void testGetDelegateAdapter_withRegisteredFactory() {
    TypeAdapterFactory statsFactory = new TypeAdapterFactory() {
      @Override
      public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        if (type.getRawType() == String.class) {
          // Get delegate and return a wrapper
          TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);
          return delegate;
        }
        return null;
      }
    };
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(statsFactory).create();
    TypeAdapter<String> adapter = gson.getAdapter(String.class);
    assertThat(adapter).isNotNull();
  }

  // ==========================================================================
  // toJsonTree(Object) tests
  // ==========================================================================

  @Test
  public void testToJsonTree_null_returnsJsonNull() {
    Gson gson = new Gson();
    JsonElement element = gson.toJsonTree(null);
    assertThat(element).isEqualTo(JsonNull.INSTANCE);
  }

  @Test
  public void testToJsonTree_string_returnsJsonPrimitive() {
    Gson gson = new Gson();
    JsonElement element = gson.toJsonTree("hello");
    assertThat(element.isJsonPrimitive()).isTrue();
    assertThat(element.getAsString()).isEqualTo("hello");
  }

  @Test
  public void testToJsonTree_integer_returnsJsonPrimitive() {
    Gson gson = new Gson();
    JsonElement element = gson.toJsonTree(42);
    assertThat(element.isJsonPrimitive()).isTrue();
    assertThat(element.getAsInt()).isEqualTo(42);
  }

  @Test
  public void testToJsonTree_boolean_returnsJsonPrimitive() {
    Gson gson = new Gson();
    JsonElement element = gson.toJsonTree(true);
    assertThat(element.isJsonPrimitive()).isTrue();
    assertThat(element.getAsBoolean()).isTrue();
  }

  @Test
  public void testToJsonTree_object_returnsJsonObject() {
    Gson gson = new Gson();
    SimpleObject obj = new SimpleObject("test", 123);
    JsonElement element = gson.toJsonTree(obj);
    assertThat(element.isJsonObject()).isTrue();
    assertThat(element.getAsJsonObject().get("name").getAsString()).isEqualTo("test");
    assertThat(element.getAsJsonObject().get("value").getAsInt()).isEqualTo(123);
  }

  @Test
  public void testToJsonTree_list_returnsJsonArray() {
    Gson gson = new Gson();
    List<String> list = Arrays.asList("a", "b", "c");
    JsonElement element = gson.toJsonTree(list);
    assertThat(element.isJsonArray()).isTrue();
    assertThat(element.getAsJsonArray().size()).isEqualTo(3);
  }

  // ==========================================================================
  // toJsonTree(Object, Type) tests
  // ==========================================================================

  @Test
  public void testToJsonTreeWithType_genericList() {
    Gson gson = new Gson();
    List<String> list = Arrays.asList("x", "y");
    Type listType = new TypeToken<List<String>>() {}.getType();
    JsonElement element = gson.toJsonTree(list, listType);
    assertThat(element.isJsonArray()).isTrue();
    assertThat(element.getAsJsonArray().size()).isEqualTo(2);
  }

  @Test
  public void testToJsonTreeWithType_map() {
    Gson gson = new Gson();
    Map<String, Integer> map = new HashMap<>();
    map.put("one", 1);
    map.put("two", 2);
    Type mapType = new TypeToken<Map<String, Integer>>() {}.getType();
    JsonElement element = gson.toJsonTree(map, mapType);
    assertThat(element.isJsonObject()).isTrue();
    assertThat(element.getAsJsonObject().get("one").getAsInt()).isEqualTo(1);
  }

  @Test
  public void testToJsonTreeWithType_nullSource() {
    Gson gson = new Gson();
    JsonElement element = gson.toJsonTree(null, String.class);
    assertThat(element).isEqualTo(JsonNull.INSTANCE);
  }

  // ==========================================================================
  // toJson(Object) tests
  // ==========================================================================

  @Test
  public void testToJsonObject_null_returnsNullString() {
    Gson gson = new Gson();
    String json = gson.toJson(null);
    assertThat(json).isEqualTo("null");
  }

  @Test
  public void testToJsonObject_string() {
    Gson gson = new Gson();
    String json = gson.toJson("hello");
    assertThat(json).isEqualTo("\"hello\"");
  }

  @Test
  public void testToJsonObject_integer() {
    Gson gson = new Gson();
    String json = gson.toJson(42);
    assertThat(json).isEqualTo("42");
  }

  @Test
  public void testToJsonObject_boolean() {
    Gson gson = new Gson();
    String json = gson.toJson(true);
    assertThat(json).isEqualTo("true");
  }

  @Test
  public void testToJsonObject_simpleObject() {
    Gson gson = new Gson();
    SimpleObject obj = new SimpleObject("test", 99);
    String json = gson.toJson(obj);
    assertThat(json).contains("\"name\":\"test\"");
    assertThat(json).contains("\"value\":99");
  }

  @Test
  public void testToJsonObject_emptyList() {
    Gson gson = new Gson();
    String json = gson.toJson(Collections.emptyList());
    assertThat(json).isEqualTo("[]");
  }

  @Test
  public void testToJsonObject_list() {
    Gson gson = new Gson();
    List<Integer> list = Arrays.asList(1, 2, 3);
    String json = gson.toJson(list);
    assertThat(json).isEqualTo("[1,2,3]");
  }

  // ==========================================================================
  // toJson(Object, Type) tests
  // ==========================================================================

  @Test
  public void testToJsonObjectType_genericList() {
    Gson gson = new Gson();
    List<String> list = Arrays.asList("a", "b");
    Type listType = new TypeToken<List<String>>() {}.getType();
    String json = gson.toJson(list, listType);
    assertThat(json).isEqualTo("[\"a\",\"b\"]");
  }

  @Test
  public void testToJsonObjectType_map() {
    Gson gson = new Gson();
    Map<String, Integer> map = new HashMap<>();
    map.put("key", 100);
    Type mapType = new TypeToken<Map<String, Integer>>() {}.getType();
    String json = gson.toJson(map, mapType);
    assertThat(json).contains("\"key\":100");
  }

  @Test
  public void testToJsonObjectType_nullWithType() {
    Gson gson = new Gson();
    String json = gson.toJson(null, String.class);
    assertThat(json).isEqualTo("null");
  }

  // ==========================================================================
  // toJson(Object, Appendable) tests
  // ==========================================================================

  @Test
  public void testToJsonObjectAppendable_writesToAppendable() {
    Gson gson = new Gson();
    StringBuilder sb = new StringBuilder();
    gson.toJson("test", sb);
    assertThat(sb.toString()).isEqualTo("\"test\"");
  }

  @Test
  public void testToJsonObjectAppendable_null_writesNull() {
    Gson gson = new Gson();
    StringBuilder sb = new StringBuilder();
    gson.toJson(null, sb);
    assertThat(sb.toString()).isEqualTo("null");
  }

  @Test
  public void testToJsonObjectAppendable_object() {
    Gson gson = new Gson();
    StringWriter writer = new StringWriter();
    SimpleObject obj = new SimpleObject("foo", 42);
    gson.toJson(obj, writer);
    String json = writer.toString();
    assertThat(json).contains("\"name\":\"foo\"");
    assertThat(json).contains("\"value\":42");
  }

  // ==========================================================================
  // toJson(Object, Type, Appendable) tests
  // ==========================================================================

  @Test
  public void testToJsonObjectTypeAppendable_writesToAppendable() {
    Gson gson = new Gson();
    StringBuilder sb = new StringBuilder();
    List<Integer> list = Arrays.asList(1, 2, 3);
    Type listType = new TypeToken<List<Integer>>() {}.getType();
    gson.toJson(list, listType, sb);
    assertThat(sb.toString()).isEqualTo("[1,2,3]");
  }

  @Test
  public void testToJsonObjectTypeAppendable_nullValue() {
    Gson gson = new Gson();
    StringBuilder sb = new StringBuilder();
    gson.toJson(null, String.class, sb);
    assertThat(sb.toString()).isEqualTo("null");
  }

  // ==========================================================================
  // toJson(Object, Type, JsonWriter) tests
  // ==========================================================================

  @Test
  public void testToJsonObjectTypeJsonWriter_writesToWriter() throws IOException {
    Gson gson = new Gson();
    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    gson.toJson("hello", String.class, writer);
    assertThat(sw.toString()).isEqualTo("\"hello\"");
  }

  @Test
  public void testToJsonObjectTypeJsonWriter_restoresStrictness() throws IOException {
    Gson gson = new Gson();
    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    writer.setStrictness(Strictness.STRICT);
    gson.toJson("test", String.class, writer);
    assertThat(writer.getStrictness()).isEqualTo(Strictness.STRICT);
  }

  @Test
  public void testToJsonObjectTypeJsonWriter_restoresHtmlSafe() throws IOException {
    Gson gson = new Gson();
    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    writer.setHtmlSafe(false);
    gson.toJson("test", String.class, writer);
    assertThat(writer.isHtmlSafe()).isFalse();
  }

  @Test
  public void testToJsonObjectTypeJsonWriter_restoresSerializeNulls() throws IOException {
    Gson gson = new Gson();
    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    writer.setSerializeNulls(true);
    gson.toJson("test", String.class, writer);
    assertThat(writer.getSerializeNulls()).isTrue();
  }

  // ==========================================================================
  // toJson(JsonElement) tests
  // ==========================================================================

  @Test
  public void testToJsonJsonElement_primitive() {
    Gson gson = new Gson();
    JsonElement element = new JsonPrimitive("test");
    String json = gson.toJson(element);
    assertThat(json).isEqualTo("\"test\"");
  }

  @Test
  public void testToJsonJsonElement_object() {
    Gson gson = new Gson();
    JsonObject obj = new JsonObject();
    obj.addProperty("key", "value");
    String json = gson.toJson(obj);
    assertThat(json).isEqualTo("{\"key\":\"value\"}");
  }

  @Test
  public void testToJsonJsonElement_array() {
    Gson gson = new Gson();
    JsonArray array = new JsonArray();
    array.add(1);
    array.add(2);
    String json = gson.toJson(array);
    assertThat(json).isEqualTo("[1,2]");
  }

  @Test
  public void testToJsonJsonElement_null() {
    Gson gson = new Gson();
    String json = gson.toJson(JsonNull.INSTANCE);
    assertThat(json).isEqualTo("null");
  }

  // ==========================================================================
  // toJson(JsonElement, Appendable) tests
  // ==========================================================================

  @Test
  public void testToJsonJsonElementAppendable_writesToAppendable() {
    Gson gson = new Gson();
    StringBuilder sb = new StringBuilder();
    JsonElement element = new JsonPrimitive(123);
    gson.toJson(element, sb);
    assertThat(sb.toString()).isEqualTo("123");
  }

  @Test
  public void testToJsonJsonElementAppendable_complexObject() {
    Gson gson = new Gson();
    StringBuilder sb = new StringBuilder();
    JsonObject obj = new JsonObject();
    obj.addProperty("name", "test");
    JsonArray arr = new JsonArray();
    arr.add(1);
    arr.add(2);
    obj.add("values", arr);
    gson.toJson(obj, sb);
    String json = sb.toString();
    assertThat(json).contains("\"name\":\"test\"");
    assertThat(json).contains("\"values\":[1,2]");
  }

  // ==========================================================================
  // toJson(JsonElement, JsonWriter) tests
  // ==========================================================================

  @Test
  public void testToJsonJsonElementJsonWriter_writesToWriter() throws IOException {
    Gson gson = new Gson();
    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    JsonElement element = new JsonPrimitive("hello");
    gson.toJson(element, writer);
    assertThat(sw.toString()).isEqualTo("\"hello\"");
  }

  @Test
  public void testToJsonJsonElementJsonWriter_restoresSettings() throws IOException {
    Gson gson = new Gson();
    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    writer.setStrictness(Strictness.STRICT);
    writer.setHtmlSafe(false);
    writer.setSerializeNulls(true);

    gson.toJson(new JsonPrimitive("test"), writer);

    assertThat(writer.getStrictness()).isEqualTo(Strictness.STRICT);
    assertThat(writer.isHtmlSafe()).isFalse();
    assertThat(writer.getSerializeNulls()).isTrue();
  }

  // ==========================================================================
  // newJsonWriter tests
  // ==========================================================================

  @Test
  public void testNewJsonWriter_returnsConfiguredWriter() throws IOException {
    Gson gson = new Gson();
    StringWriter sw = new StringWriter();
    JsonWriter writer = gson.newJsonWriter(sw);
    assertThat(writer).isNotNull();
  }

  @Test
  public void testNewJsonWriter_appliesHtmlSafe() throws IOException {
    Gson gson = new Gson();
    StringWriter sw = new StringWriter();
    JsonWriter writer = gson.newJsonWriter(sw);
    assertThat(writer.isHtmlSafe()).isTrue();
  }

  @Test
  public void testNewJsonWriter_appliesDisabledHtmlSafe() throws IOException {
    Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    StringWriter sw = new StringWriter();
    JsonWriter writer = gson.newJsonWriter(sw);
    assertThat(writer.isHtmlSafe()).isFalse();
  }

  @Test
  public void testNewJsonWriter_appliesSerializeNulls() throws IOException {
    Gson gson = new GsonBuilder().serializeNulls().create();
    StringWriter sw = new StringWriter();
    JsonWriter writer = gson.newJsonWriter(sw);
    assertThat(writer.getSerializeNulls()).isTrue();
  }

  @Test
  public void testNewJsonWriter_appliesStrictness() throws IOException {
    Gson gson = new GsonBuilder().setStrictness(Strictness.STRICT).create();
    StringWriter sw = new StringWriter();
    JsonWriter writer = gson.newJsonWriter(sw);
    assertThat(writer.getStrictness()).isEqualTo(Strictness.STRICT);
  }

  @Test
  public void testNewJsonWriter_defaultStrictnessIsLegacyStrict() throws IOException {
    Gson gson = new Gson();
    StringWriter sw = new StringWriter();
    JsonWriter writer = gson.newJsonWriter(sw);
    assertThat(writer.getStrictness()).isEqualTo(Strictness.LEGACY_STRICT);
  }

  @Test
  public void testNewJsonWriter_appliesFormattingStyle() throws IOException {
    Gson gson = new GsonBuilder().setFormattingStyle(FormattingStyle.PRETTY).create();
    StringWriter sw = new StringWriter();
    JsonWriter writer = gson.newJsonWriter(sw);
    // Verify pretty printing is enabled by writing an object
    writer.beginObject();
    writer.name("key").value("value");
    writer.endObject();
    String output = sw.toString();
    assertThat(output).contains("\n");
  }

  @Test
  public void testNewJsonWriter_nonExecutableJson() throws IOException {
    Gson gson = new GsonBuilder().generateNonExecutableJson().create();
    StringWriter sw = new StringWriter();
    JsonWriter writer = gson.newJsonWriter(sw);
    writer.beginObject();
    writer.endObject();
    String output = sw.toString();
    assertThat(output).startsWith(")]}'\n");
  }

  // ==========================================================================
  // newJsonReader tests
  // ==========================================================================

  @Test
  public void testNewJsonReader_returnsConfiguredReader() {
    Gson gson = new Gson();
    StringReader sr = new StringReader("{}");
    JsonReader reader = gson.newJsonReader(sr);
    assertThat(reader).isNotNull();
  }

  @Test
  public void testNewJsonReader_defaultStrictnessIsLegacyStrict() {
    Gson gson = new Gson();
    StringReader sr = new StringReader("{}");
    JsonReader reader = gson.newJsonReader(sr);
    assertThat(reader.getStrictness()).isEqualTo(Strictness.LEGACY_STRICT);
  }

  @Test
  public void testNewJsonReader_appliesStrictness() {
    Gson gson = new GsonBuilder().setStrictness(Strictness.STRICT).create();
    StringReader sr = new StringReader("{}");
    JsonReader reader = gson.newJsonReader(sr);
    assertThat(reader.getStrictness()).isEqualTo(Strictness.STRICT);
  }

  @Test
  public void testNewJsonReader_appliesLenientStrictness() {
    Gson gson = new GsonBuilder().setStrictness(Strictness.LENIENT).create();
    StringReader sr = new StringReader("{}");
    JsonReader reader = gson.newJsonReader(sr);
    assertThat(reader.getStrictness()).isEqualTo(Strictness.LENIENT);
  }

  // ==========================================================================
  // fromJson(String, Class) tests
  // ==========================================================================

  @Test
  public void testFromJsonStringClass_null_returnsNull() {
    Gson gson = new Gson();
    String result = gson.fromJson((String) null, String.class);
    assertThat(result).isNull();
  }

  @Test
  public void testFromJsonStringClass_emptyString_returnsNull() {
    Gson gson = new Gson();
    String result = gson.fromJson("", String.class);
    assertThat(result).isNull();
  }

  @Test
  public void testFromJsonStringClass_string() {
    Gson gson = new Gson();
    String result = gson.fromJson("\"hello\"", String.class);
    assertThat(result).isEqualTo("hello");
  }

  @Test
  public void testFromJsonStringClass_integer() {
    Gson gson = new Gson();
    Integer result = gson.fromJson("42", Integer.class);
    assertThat(result).isEqualTo(42);
  }

  @Test
  public void testFromJsonStringClass_boolean() {
    Gson gson = new Gson();
    Boolean result = gson.fromJson("true", Boolean.class);
    assertThat(result).isTrue();
  }

  @Test
  public void testFromJsonStringClass_object() {
    Gson gson = new Gson();
    SimpleObject result = gson.fromJson("{\"name\":\"test\",\"value\":99}", SimpleObject.class);
    assertThat(result.getName()).isEqualTo("test");
    assertThat(result.getValue()).isEqualTo(99);
  }

  @Test
  public void testFromJsonStringClass_invalidJson_throwsJsonSyntaxException() {
    Gson gson = new Gson();
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("{invalid}", Object.class));
  }

  @Test
  public void testFromJsonStringClass_trailingData_throwsJsonSyntaxException() {
    Gson gson = new Gson();
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("\"hello\" extra", String.class));
  }

  // ==========================================================================
  // fromJson(String, Type) tests
  // ==========================================================================

  @Test
  public void testFromJsonStringType_genericList() {
    Gson gson = new Gson();
    Type listType = new TypeToken<List<String>>() {}.getType();
    List<String> result = gson.fromJson("[\"a\",\"b\",\"c\"]", listType);
    assertThat(result).containsExactly("a", "b", "c");
  }

  @Test
  public void testFromJsonStringType_map() {
    Gson gson = new Gson();
    Type mapType = new TypeToken<Map<String, Integer>>() {}.getType();
    Map<String, Integer> result = gson.fromJson("{\"one\":1,\"two\":2}", mapType);
    assertThat(result).containsEntry("one", 1);
    assertThat(result).containsEntry("two", 2);
  }

  @Test
  public void testFromJsonStringType_null_returnsNull() {
    Gson gson = new Gson();
    Type type = String.class;
    String result = gson.fromJson((String) null, type);
    assertThat(result).isNull();
  }

  // ==========================================================================
  // fromJson(String, TypeToken) tests
  // ==========================================================================

  @Test
  public void testFromJsonStringTypeToken_list() {
    Gson gson = new Gson();
    TypeToken<List<Integer>> typeToken = new TypeToken<List<Integer>>() {};
    List<Integer> result = gson.fromJson("[1,2,3]", typeToken);
    assertThat(result).containsExactly(1, 2, 3);
  }

  @Test
  public void testFromJsonStringTypeToken_null_returnsNull() {
    Gson gson = new Gson();
    TypeToken<String> typeToken = TypeToken.get(String.class);
    String result = gson.fromJson((String) null, typeToken);
    assertThat(result).isNull();
  }

  @Test
  public void testFromJsonStringTypeToken_emptyString_returnsNull() {
    Gson gson = new Gson();
    TypeToken<String> typeToken = TypeToken.get(String.class);
    String result = gson.fromJson("", typeToken);
    assertThat(result).isNull();
  }

  // ==========================================================================
  // fromJson(Reader, Class) tests
  // ==========================================================================

  @Test
  public void testFromJsonReaderClass_readsFromReader() {
    Gson gson = new Gson();
    Reader reader = new StringReader("\"hello\"");
    String result = gson.fromJson(reader, String.class);
    assertThat(result).isEqualTo("hello");
  }

  @Test
  public void testFromJsonReaderClass_object() {
    Gson gson = new Gson();
    Reader reader = new StringReader("{\"name\":\"foo\",\"value\":42}");
    SimpleObject result = gson.fromJson(reader, SimpleObject.class);
    assertThat(result.getName()).isEqualTo("foo");
    assertThat(result.getValue()).isEqualTo(42);
  }

  @Test
  public void testFromJsonReaderClass_emptyReader_returnsNull() {
    Gson gson = new Gson();
    Reader reader = new StringReader("");
    String result = gson.fromJson(reader, String.class);
    assertThat(result).isNull();
  }

  // ==========================================================================
  // fromJson(Reader, Type) tests
  // ==========================================================================

  @Test
  public void testFromJsonReaderType_genericList() {
    Gson gson = new Gson();
    Reader reader = new StringReader("[\"x\",\"y\",\"z\"]");
    Type listType = new TypeToken<List<String>>() {}.getType();
    List<String> result = gson.fromJson(reader, listType);
    assertThat(result).containsExactly("x", "y", "z");
  }

  @Test
  public void testFromJsonReaderType_map() {
    Gson gson = new Gson();
    Reader reader = new StringReader("{\"a\":1,\"b\":2}");
    Type mapType = new TypeToken<Map<String, Integer>>() {}.getType();
    Map<String, Integer> result = gson.fromJson(reader, mapType);
    assertThat(result).containsEntry("a", 1);
    assertThat(result).containsEntry("b", 2);
  }

  // ==========================================================================
  // fromJson(Reader, TypeToken) tests
  // ==========================================================================

  @Test
  public void testFromJsonReaderTypeToken_list() {
    Gson gson = new Gson();
    Reader reader = new StringReader("[10,20,30]");
    TypeToken<List<Integer>> typeToken = new TypeToken<List<Integer>>() {};
    List<Integer> result = gson.fromJson(reader, typeToken);
    assertThat(result).containsExactly(10, 20, 30);
  }

  @Test
  public void testFromJsonReaderTypeToken_throwsOnTrailingData() {
    Gson gson = new Gson();
    Reader reader = new StringReader("\"hello\" more data");
    TypeToken<String> typeToken = TypeToken.get(String.class);
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(reader, typeToken));
  }

  // ==========================================================================
  // fromJson(JsonReader, Type) tests
  // ==========================================================================

  @Test
  public void testFromJsonJsonReaderType_readsFromJsonReader() throws IOException {
    Gson gson = new Gson();
    JsonReader reader = new JsonReader(new StringReader("\"test\""));
    Type type = String.class;
    String result = gson.fromJson(reader, type);
    assertThat(result).isEqualTo("test");
  }

  @Test
  public void testFromJsonJsonReaderType_allowsTrailingData() throws IOException {
    Gson gson = new Gson();
    JsonReader reader = new JsonReader(new StringReader("\"first\" \"second\""));
    reader.setStrictness(Strictness.LENIENT);
    Type type = String.class;
    String result = gson.fromJson(reader, type);
    assertThat(result).isEqualTo("first");
    // Should be able to read more
    String second = gson.fromJson(reader, type);
    assertThat(second).isEqualTo("second");
  }

  @Test
  public void testFromJsonJsonReaderType_restoresStrictness() throws IOException {
    Gson gson = new Gson();
    JsonReader reader = new JsonReader(new StringReader("\"test\""));
    reader.setStrictness(Strictness.STRICT);
    @SuppressWarnings("unused")
    String unused = gson.fromJson(reader, String.class);
    assertThat(reader.getStrictness()).isEqualTo(Strictness.STRICT);
  }

  // ==========================================================================
  // fromJson(JsonReader, TypeToken) tests
  // ==========================================================================

  @Test
  public void testFromJsonJsonReaderTypeToken_readsFromJsonReader() throws IOException {
    Gson gson = new Gson();
    JsonReader reader = new JsonReader(new StringReader("[1,2,3]"));
    TypeToken<List<Integer>> typeToken = new TypeToken<List<Integer>>() {};
    List<Integer> result = gson.fromJson(reader, typeToken);
    assertThat(result).containsExactly(1, 2, 3);
  }

  @Test
  public void testFromJsonJsonReaderTypeToken_emptyDocument_returnsNull() throws IOException {
    Gson gson = new Gson();
    JsonReader reader = new JsonReader(new StringReader(""));
    TypeToken<String> typeToken = TypeToken.get(String.class);
    String result = gson.fromJson(reader, typeToken);
    assertThat(result).isNull();
  }

  @Test
  public void testFromJsonJsonReaderTypeToken_appliesGsonStrictness() throws IOException {
    Gson gson = new GsonBuilder().setStrictness(Strictness.LENIENT).create();
    // Unquoted strings are only allowed in LENIENT mode
    JsonReader reader = new JsonReader(new StringReader("unquoted"));
    reader.setStrictness(Strictness.STRICT); // This will be overridden by Gson's setting
    TypeToken<String> typeToken = TypeToken.get(String.class);
    String result = gson.fromJson(reader, typeToken);
    assertThat(result).isEqualTo("unquoted");
  }

  // ==========================================================================
  // fromJson(JsonElement, Class) tests
  // ==========================================================================

  @Test
  public void testFromJsonJsonElementClass_null_returnsNull() {
    Gson gson = new Gson();
    String result = gson.fromJson((JsonElement) null, String.class);
    assertThat(result).isNull();
  }

  @Test
  public void testFromJsonJsonElementClass_jsonNull_returnsNull() {
    Gson gson = new Gson();
    String result = gson.fromJson(JsonNull.INSTANCE, String.class);
    assertThat(result).isNull();
  }

  @Test
  public void testFromJsonJsonElementClass_primitive() {
    Gson gson = new Gson();
    JsonElement element = new JsonPrimitive("hello");
    String result = gson.fromJson(element, String.class);
    assertThat(result).isEqualTo("hello");
  }

  @Test
  public void testFromJsonJsonElementClass_object() {
    Gson gson = new Gson();
    JsonObject obj = new JsonObject();
    obj.addProperty("name", "test");
    obj.addProperty("value", 123);
    SimpleObject result = gson.fromJson(obj, SimpleObject.class);
    assertThat(result.getName()).isEqualTo("test");
    assertThat(result.getValue()).isEqualTo(123);
  }

  @Test
  public void testFromJsonJsonElementClass_array() {
    Gson gson = new Gson();
    JsonArray array = new JsonArray();
    array.add(1);
    array.add(2);
    array.add(3);
    int[] result = gson.fromJson(array, int[].class);
    assertThat(result).asList().containsExactly(1, 2, 3);
  }

  // ==========================================================================
  // fromJson(JsonElement, Type) tests
  // ==========================================================================

  @Test
  public void testFromJsonJsonElementType_genericList() {
    Gson gson = new Gson();
    JsonArray array = new JsonArray();
    array.add("a");
    array.add("b");
    Type listType = new TypeToken<List<String>>() {}.getType();
    List<String> result = gson.fromJson(array, listType);
    assertThat(result).containsExactly("a", "b");
  }

  @Test
  public void testFromJsonJsonElementType_map() {
    Gson gson = new Gson();
    JsonObject obj = new JsonObject();
    obj.addProperty("key1", 10);
    obj.addProperty("key2", 20);
    Type mapType = new TypeToken<Map<String, Integer>>() {}.getType();
    Map<String, Integer> result = gson.fromJson(obj, mapType);
    assertThat(result).containsEntry("key1", 10);
    assertThat(result).containsEntry("key2", 20);
  }

  @Test
  public void testFromJsonJsonElementType_null_returnsNull() {
    Gson gson = new Gson();
    Type type = String.class;
    String result = gson.fromJson((JsonElement) null, type);
    assertThat(result).isNull();
  }

  // ==========================================================================
  // fromJson(JsonElement, TypeToken) tests
  // ==========================================================================

  @Test
  public void testFromJsonJsonElementTypeToken_list() {
    Gson gson = new Gson();
    JsonArray array = new JsonArray();
    array.add(100);
    array.add(200);
    TypeToken<List<Integer>> typeToken = new TypeToken<List<Integer>>() {};
    List<Integer> result = gson.fromJson(array, typeToken);
    assertThat(result).containsExactly(100, 200);
  }

  @Test
  public void testFromJsonJsonElementTypeToken_null_returnsNull() {
    Gson gson = new Gson();
    TypeToken<String> typeToken = TypeToken.get(String.class);
    String result = gson.fromJson((JsonElement) null, typeToken);
    assertThat(result).isNull();
  }

  // ==========================================================================
  // toString tests
  // ==========================================================================

  @Test
  public void testToString_containsSerializeNulls() {
    Gson gson = new Gson();
    String str = gson.toString();
    assertThat(str).contains("serializeNulls:false");
  }

  @Test
  public void testToString_containsSerializeNullsTrue() {
    Gson gson = new GsonBuilder().serializeNulls().create();
    String str = gson.toString();
    assertThat(str).contains("serializeNulls:true");
  }

  @Test
  public void testToString_containsFactories() {
    Gson gson = new Gson();
    String str = gson.toString();
    assertThat(str).contains("factories:");
  }

  @Test
  public void testToString_containsInstanceCreators() {
    Gson gson = new Gson();
    String str = gson.toString();
    assertThat(str).contains("instanceCreators:");
  }

  @Test
  public void testToString_startsWithBrace() {
    Gson gson = new Gson();
    String str = gson.toString();
    assertThat(str).startsWith("{");
  }

  @Test
  public void testToString_endsWithBrace() {
    Gson gson = new Gson();
    String str = gson.toString();
    assertThat(str).endsWith("}");
  }

  // ==========================================================================
  // Round-trip tests
  // ==========================================================================

  @Test
  public void testRoundTrip_simpleObject() {
    Gson gson = new Gson();
    SimpleObject original = new SimpleObject("roundtrip", 12345);
    String json = gson.toJson(original);
    SimpleObject restored = gson.fromJson(json, SimpleObject.class);
    assertThat(restored.getName()).isEqualTo(original.getName());
    assertThat(restored.getValue()).isEqualTo(original.getValue());
  }

  @Test
  public void testRoundTrip_nestedObject() {
    Gson gson = new Gson();
    NestedObject original = new NestedObject("outer", new SimpleObject("inner", 999));
    String json = gson.toJson(original);
    NestedObject restored = gson.fromJson(json, NestedObject.class);
    assertThat(restored.id).isEqualTo("outer");
    assertThat(restored.nested.getName()).isEqualTo("inner");
    assertThat(restored.nested.getValue()).isEqualTo(999);
  }

  @Test
  public void testRoundTrip_list() {
    Gson gson = new Gson();
    List<SimpleObject> original = new ArrayList<>();
    original.add(new SimpleObject("first", 1));
    original.add(new SimpleObject("second", 2));
    Type listType = new TypeToken<List<SimpleObject>>() {}.getType();
    String json = gson.toJson(original, listType);
    List<SimpleObject> restored = gson.fromJson(json, listType);
    assertThat(restored).hasSize(2);
    assertThat(restored.get(0).getName()).isEqualTo("first");
    assertThat(restored.get(1).getName()).isEqualTo("second");
  }

  @Test
  public void testRoundTrip_map() {
    Gson gson = new Gson();
    Map<String, SimpleObject> original = new HashMap<>();
    original.put("a", new SimpleObject("obj_a", 10));
    original.put("b", new SimpleObject("obj_b", 20));
    Type mapType = new TypeToken<Map<String, SimpleObject>>() {}.getType();
    String json = gson.toJson(original, mapType);
    Map<String, SimpleObject> restored = gson.fromJson(json, mapType);
    assertThat(restored).hasSize(2);
    assertThat(restored.get("a").getName()).isEqualTo("obj_a");
    assertThat(restored.get("b").getValue()).isEqualTo(20);
  }

  // ==========================================================================
  // Edge case tests
  // ==========================================================================

  @Test
  public void testSerialize_specialCharacters() {
    Gson gson = new Gson();
    String special = "Line1\nLine2\tTabbed\"Quoted\"";
    String json = gson.toJson(special);
    String restored = gson.fromJson(json, String.class);
    assertThat(restored).isEqualTo(special);
  }

  @Test
  public void testSerialize_unicodeCharacters() {
    Gson gson = new Gson();
    String unicode = "日本語テスト\u0000";
    String json = gson.toJson(unicode);
    String restored = gson.fromJson(json, String.class);
    assertThat(restored).isEqualTo(unicode);
  }

  @Test
  public void testSerialize_emptyObject() {
    Gson gson = new Gson();
    JsonObject empty = new JsonObject();
    String json = gson.toJson(empty);
    assertThat(json).isEqualTo("{}");
  }

  @Test
  public void testSerialize_emptyArray() {
    Gson gson = new Gson();
    JsonArray empty = new JsonArray();
    String json = gson.toJson(empty);
    assertThat(json).isEqualTo("[]");
  }

  @Test
  public void testDeserialize_jsonNull() {
    Gson gson = new Gson();
    JsonElement element = JsonParser.parseString("null");
    assertThat(element).isEqualTo(JsonNull.INSTANCE);
  }

  @Test
  public void testSerialize_longValue() {
    Gson gson = new Gson();
    long value = Long.MAX_VALUE;
    String json = gson.toJson(value);
    assertThat(json).isEqualTo(String.valueOf(Long.MAX_VALUE));
  }

  @Test
  public void testSerialize_doubleValue() {
    Gson gson = new Gson();
    double value = 3.14159265359;
    String json = gson.toJson(value);
    assertThat(Double.parseDouble(json)).isWithin(1e-10).of(value);
  }

  @Test
  public void testSerialize_nanThrowsException() {
    Gson gson = new Gson();
    assertThrows(IllegalArgumentException.class, () -> gson.toJson(Double.NaN));
  }

  @Test
  public void testSerialize_infinityThrowsException() {
    Gson gson = new Gson();
    assertThrows(IllegalArgumentException.class, () -> gson.toJson(Double.POSITIVE_INFINITY));
  }

  @Test
  public void testSerialize_specialFloatingPointValuesEnabled() {
    Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues().create();
    String nanJson = gson.toJson(Double.NaN);
    assertThat(nanJson).isEqualTo("NaN");
    String infJson = gson.toJson(Double.POSITIVE_INFINITY);
    assertThat(infJson).isEqualTo("Infinity");
  }

  // ==========================================================================
  // Custom TypeAdapter tests with getDelegateAdapter
  // ==========================================================================

  @Test
  public void testCustomTypeAdapterFactory_withDelegateAdapter() {
    final int[] readCount = {0};
    final int[] writeCount = {0};

    TypeAdapterFactory countingFactory = new TypeAdapterFactory() {
      @Override
      public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        if (type.getRawType() != SimpleObject.class) {
          return null;
        }
        TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);
        return new TypeAdapter<T>() {
          @Override
          public void write(JsonWriter out, T value) throws IOException {
            writeCount[0]++;
            delegate.write(out, value);
          }

          @Override
          public T read(JsonReader in) throws IOException {
            readCount[0]++;
            return delegate.read(in);
          }
        };
      }
    };

    Gson gson = new GsonBuilder().registerTypeAdapterFactory(countingFactory).create();

    SimpleObject obj = new SimpleObject("test", 42);
    String json = gson.toJson(obj);
    assertThat(writeCount[0]).isEqualTo(1);

    @SuppressWarnings("unused")
    SimpleObject unused = gson.fromJson(json, SimpleObject.class);
    assertThat(readCount[0]).isEqualTo(1);
  }

  // ==========================================================================
  // Configuration combination tests
  // ==========================================================================

  @Test
  public void testCombinedConfiguration_serializeNullsAndPrettyPrint() {
    Gson gson = new GsonBuilder()
        .serializeNulls()
        .setFormattingStyle(FormattingStyle.PRETTY)
        .create();

    SimpleObject obj = new SimpleObject();
    String json = gson.toJson(obj);
    assertThat(json).contains("\"name\": null");
    assertThat(json).contains("\n");
  }

  @Test
  public void testCombinedConfiguration_disableHtmlEscapingAndSerializeNulls() {
    Gson gson = new GsonBuilder()
        .disableHtmlEscaping()
        .serializeNulls()
        .create();

    assertThat(gson.htmlSafe()).isFalse();
    assertThat(gson.serializeNulls()).isTrue();
  }

  // ==========================================================================
  // Primitive type tests
  // ==========================================================================

  @Test
  public void testSerialize_primitiveInt() {
    Gson gson = new Gson();
    assertThat(gson.toJson(123)).isEqualTo("123");
  }

  @Test
  public void testSerialize_primitiveLong() {
    Gson gson = new Gson();
    assertThat(gson.toJson(123L)).isEqualTo("123");
  }

  @Test
  public void testSerialize_primitiveDouble() {
    Gson gson = new Gson();
    assertThat(gson.toJson(1.5)).isEqualTo("1.5");
  }

  @Test
  public void testSerialize_primitiveFloat() {
    Gson gson = new Gson();
    assertThat(gson.toJson(1.5f)).isEqualTo("1.5");
  }

  @Test
  public void testSerialize_primitiveBoolean() {
    Gson gson = new Gson();
    assertThat(gson.toJson(true)).isEqualTo("true");
    assertThat(gson.toJson(false)).isEqualTo("false");
  }

  @Test
  public void testSerialize_primitiveChar() {
    Gson gson = new Gson();
    assertThat(gson.toJson('a')).isEqualTo("\"a\"");
  }

  @Test
  public void testDeserialize_primitiveInt() {
    Gson gson = new Gson();
    assertThat(gson.fromJson("123", int.class)).isEqualTo(123);
  }

  @Test
  public void testDeserialize_primitiveLong() {
    Gson gson = new Gson();
    assertThat(gson.fromJson("123", long.class)).isEqualTo(123L);
  }

  @Test
  public void testDeserialize_primitiveDouble() {
    Gson gson = new Gson();
    assertThat(gson.fromJson("1.5", double.class)).isEqualTo(1.5);
  }

  @Test
  public void testDeserialize_primitiveFloat() {
    Gson gson = new Gson();
    assertThat(gson.fromJson("1.5", float.class)).isEqualTo(1.5f);
  }

  @Test
  public void testDeserialize_primitiveBoolean() {
    Gson gson = new Gson();
    assertThat(gson.fromJson("true", boolean.class)).isTrue();
    assertThat(gson.fromJson("false", boolean.class)).isFalse();
  }

  // ==========================================================================
  // LongSerializationPolicy tests
  // ==========================================================================

  @Test
  public void testLongSerializationPolicy_default() {
    Gson gson = new GsonBuilder().setLongSerializationPolicy(LongSerializationPolicy.DEFAULT).create();
    String json = gson.toJson(Long.MAX_VALUE);
    assertThat(json).isEqualTo(String.valueOf(Long.MAX_VALUE));
  }

  @Test
  public void testLongSerializationPolicy_string() {
    Gson gson = new GsonBuilder().setLongSerializationPolicy(LongSerializationPolicy.STRING).create();
    String json = gson.toJson(Long.MAX_VALUE);
    assertThat(json).isEqualTo("\"" + Long.MAX_VALUE + "\"");
  }

  // ==========================================================================
  // Thread safety tests
  // ==========================================================================

  @Test
  public void testThreadSafety_concurrentSerialization() throws InterruptedException {
    Gson gson = new Gson();
    int threadCount = 10;
    Thread[] threads = new Thread[threadCount];
    final boolean[] success = {true};

    for (int i = 0; i < threadCount; i++) {
      final int index = i;
      threads[i] = new Thread(() -> {
        try {
          SimpleObject obj = new SimpleObject("thread" + index, index);
          String json = gson.toJson(obj);
          SimpleObject restored = gson.fromJson(json, SimpleObject.class);
          if (!restored.getName().equals("thread" + index) || restored.getValue() != index) {
            success[0] = false;
          }
        } catch (Exception e) {
          success[0] = false;
        }
      });
    }

    for (Thread thread : threads) {
      thread.start();
    }

    for (Thread thread : threads) {
      thread.join();
    }

    assertThat(success[0]).isTrue();
  }

  // ==========================================================================
  // FutureTypeAdapter tests
  // ==========================================================================

  @Test
  public void testFutureTypeAdapter_constructor_createsInstance() {
    // FutureTypeAdapter is package-private, can be accessed from same package
    Gson.FutureTypeAdapter<String> adapter = new Gson.FutureTypeAdapter<>();
    assertThat(adapter).isNotNull();
  }

  @Test
  public void testFutureTypeAdapter_setDelegate_setsDelegate() throws IOException {
    Gson.FutureTypeAdapter<String> futureAdapter = new Gson.FutureTypeAdapter<>();
    Gson gson = new Gson();
    TypeAdapter<String> stringAdapter = gson.getAdapter(String.class);

    futureAdapter.setDelegate(stringAdapter);

    // Verify delegate was set by calling getSerializationDelegate
    assertThat(futureAdapter.getSerializationDelegate()).isSameInstanceAs(stringAdapter);
  }

  @Test
  public void testFutureTypeAdapter_setDelegate_throwsAssertionErrorWhenCalledTwice() {
    Gson.FutureTypeAdapter<String> futureAdapter = new Gson.FutureTypeAdapter<>();
    Gson gson = new Gson();
    TypeAdapter<String> stringAdapter = gson.getAdapter(String.class);

    futureAdapter.setDelegate(stringAdapter);

    // Calling setDelegate again should throw AssertionError
    AssertionError error =
        assertThrows(AssertionError.class, () -> futureAdapter.setDelegate(stringAdapter));
    assertThat(error.getMessage()).contains("Delegate is already set");
  }

  @Test
  public void testFutureTypeAdapter_getSerializationDelegate_throwsWhenDelegateNotSet() {
    Gson.FutureTypeAdapter<String> futureAdapter = new Gson.FutureTypeAdapter<>();

    IllegalStateException exception =
        assertThrows(IllegalStateException.class, futureAdapter::getSerializationDelegate);
    assertThat(exception.getMessage())
        .contains("Adapter for type with cyclic dependency has been used");
  }

  @Test
  public void testFutureTypeAdapter_read_delegatesToActualAdapter() throws IOException {
    Gson.FutureTypeAdapter<String> futureAdapter = new Gson.FutureTypeAdapter<>();
    Gson gson = new Gson();
    TypeAdapter<String> stringAdapter = gson.getAdapter(String.class);
    futureAdapter.setDelegate(stringAdapter);

    JsonReader reader = new JsonReader(new StringReader("\"test value\""));
    String result = futureAdapter.read(reader);

    assertThat(result).isEqualTo("test value");
  }

  @Test
  public void testFutureTypeAdapter_read_throwsWhenDelegateNotSet() throws IOException {
    Gson.FutureTypeAdapter<String> futureAdapter = new Gson.FutureTypeAdapter<>();
    JsonReader reader = new JsonReader(new StringReader("\"test\""));

    IllegalStateException exception =
        assertThrows(IllegalStateException.class, () -> futureAdapter.read(reader));
    assertThat(exception.getMessage())
        .contains("Adapter for type with cyclic dependency has been used");
  }

  @Test
  public void testFutureTypeAdapter_write_delegatesToActualAdapter() throws IOException {
    Gson.FutureTypeAdapter<String> futureAdapter = new Gson.FutureTypeAdapter<>();
    Gson gson = new Gson();
    TypeAdapter<String> stringAdapter = gson.getAdapter(String.class);
    futureAdapter.setDelegate(stringAdapter);

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    futureAdapter.write(writer, "hello world");

    assertThat(stringWriter.toString()).isEqualTo("\"hello world\"");
  }

  @Test
  public void testFutureTypeAdapter_write_throwsWhenDelegateNotSet() {
    Gson.FutureTypeAdapter<String> futureAdapter = new Gson.FutureTypeAdapter<>();
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    IllegalStateException exception =
        assertThrows(IllegalStateException.class, () -> futureAdapter.write(writer, "test"));
    assertThat(exception.getMessage())
        .contains("Adapter for type with cyclic dependency has been used");
  }

  @Test
  public void testFutureTypeAdapter_read_withIntegerAdapter() throws IOException {
    Gson.FutureTypeAdapter<Integer> futureAdapter = new Gson.FutureTypeAdapter<>();
    Gson gson = new Gson();
    TypeAdapter<Integer> intAdapter = gson.getAdapter(Integer.class);
    futureAdapter.setDelegate(intAdapter);

    JsonReader reader = new JsonReader(new StringReader("42"));
    Integer result = futureAdapter.read(reader);

    assertThat(result).isEqualTo(42);
  }

  @Test
  public void testFutureTypeAdapter_write_withIntegerAdapter() throws IOException {
    Gson.FutureTypeAdapter<Integer> futureAdapter = new Gson.FutureTypeAdapter<>();
    Gson gson = new Gson();
    TypeAdapter<Integer> intAdapter = gson.getAdapter(Integer.class);
    futureAdapter.setDelegate(intAdapter);

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    futureAdapter.write(writer, 123);

    assertThat(stringWriter.toString()).isEqualTo("123");
  }

  @Test
  public void testFutureTypeAdapter_read_withCustomObject() throws IOException {
    Gson.FutureTypeAdapter<SimpleObject> futureAdapter = new Gson.FutureTypeAdapter<>();
    Gson gson = new Gson();
    TypeAdapter<SimpleObject> objAdapter = gson.getAdapter(SimpleObject.class);
    futureAdapter.setDelegate(objAdapter);

    JsonReader reader = new JsonReader(new StringReader("{\"name\":\"test\",\"value\":99}"));
    SimpleObject result = futureAdapter.read(reader);

    assertThat(result.getName()).isEqualTo("test");
    assertThat(result.getValue()).isEqualTo(99);
  }

  @Test
  public void testFutureTypeAdapter_write_withCustomObject() throws IOException {
    Gson.FutureTypeAdapter<SimpleObject> futureAdapter = new Gson.FutureTypeAdapter<>();
    Gson gson = new Gson();
    TypeAdapter<SimpleObject> objAdapter = gson.getAdapter(SimpleObject.class);
    futureAdapter.setDelegate(objAdapter);

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    SimpleObject obj = new SimpleObject("hello", 42);
    futureAdapter.write(writer, obj);

    String json = stringWriter.toString();
    assertThat(json).contains("\"name\":\"hello\"");
    assertThat(json).contains("\"value\":42");
  }

  @Test
  public void testFutureTypeAdapter_write_withNullValue() throws IOException {
    Gson.FutureTypeAdapter<String> futureAdapter = new Gson.FutureTypeAdapter<>();
    Gson gson = new Gson();
    // Use nullSafe version to properly handle null values
    TypeAdapter<String> stringAdapter = gson.getAdapter(String.class).nullSafe();
    futureAdapter.setDelegate(stringAdapter);

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    futureAdapter.write(writer, null);

    assertThat(stringWriter.toString()).isEqualTo("null");
  }

  @Test
  public void testFutureTypeAdapter_read_jsonNull() throws IOException {
    Gson.FutureTypeAdapter<String> futureAdapter = new Gson.FutureTypeAdapter<>();
    Gson gson = new Gson();
    TypeAdapter<String> stringAdapter = gson.getAdapter(String.class).nullSafe();
    futureAdapter.setDelegate(stringAdapter);

    JsonReader reader = new JsonReader(new StringReader("null"));
    String result = futureAdapter.read(reader);

    assertThat(result).isNull();
  }

  @Test
  public void testFutureTypeAdapter_roundTrip() throws IOException {
    Gson.FutureTypeAdapter<SimpleObject> futureAdapter = new Gson.FutureTypeAdapter<>();
    Gson gson = new Gson();
    TypeAdapter<SimpleObject> objAdapter = gson.getAdapter(SimpleObject.class);
    futureAdapter.setDelegate(objAdapter);

    // Write
    SimpleObject original = new SimpleObject("roundtrip", 777);
    StringWriter stringWriter = new StringWriter();
    JsonWriter jsonWriter = new JsonWriter(stringWriter);
    futureAdapter.write(jsonWriter, original);
    String json = stringWriter.toString();

    // Read
    JsonReader jsonReader = new JsonReader(new StringReader(json));
    SimpleObject restored = futureAdapter.read(jsonReader);

    assertThat(restored.getName()).isEqualTo(original.getName());
    assertThat(restored.getValue()).isEqualTo(original.getValue());
  }

  @Test
  public void testFutureTypeAdapter_getSerializationDelegate_returnsDelegateAfterSet() {
    Gson.FutureTypeAdapter<String> futureAdapter = new Gson.FutureTypeAdapter<>();
    Gson gson = new Gson();
    TypeAdapter<String> stringAdapter = gson.getAdapter(String.class);

    futureAdapter.setDelegate(stringAdapter);

    // Call multiple times to ensure consistent behavior
    assertThat(futureAdapter.getSerializationDelegate()).isSameInstanceAs(stringAdapter);
    assertThat(futureAdapter.getSerializationDelegate()).isSameInstanceAs(stringAdapter);
  }

  @Test
  public void testFutureTypeAdapter_usedInCyclicTypeDependency() {
    // This test demonstrates the real-world use case of FutureTypeAdapter:
    // handling types with cyclic dependencies
    Gson gson = new Gson();

    // A Node class that references itself
    TypeAdapter<Node> nodeAdapter = gson.getAdapter(Node.class);
    assertThat(nodeAdapter).isNotNull();

    // Serialize and deserialize a node with children
    Node root = new Node("root");
    Node child1 = new Node("child1");
    Node child2 = new Node("child2");
    root.children = Arrays.asList(child1, child2);

    String json = gson.toJson(root);
    assertThat(json).contains("\"name\":\"root\"");

    Node restored = gson.fromJson(json, Node.class);
    assertThat(restored.name).isEqualTo("root");
    assertThat(restored.children).hasSize(2);
    assertThat(restored.children.get(0).name).isEqualTo("child1");
  }

  // Helper class for testing cyclic type dependencies
  @SuppressWarnings("unused")
  private static class Node {
    String name;
    List<Node> children;

    Node() {}

    Node(String name) {
      this.name = name;
    }
  }
}
