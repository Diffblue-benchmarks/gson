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

package com.google.gson;

import static com.google.common.truth.Truth.assertThat;

import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.MalformedJsonException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class GsonTest {

  @Test
  public void testDefaultConstructor() {
    Gson gson = new Gson();
    assertThat(gson).isNotNull();
    assertThat(gson.serializeNulls()).isFalse();
    assertThat(gson.htmlSafe()).isTrue();
  }

  @Test
  public void testNewBuilder() {
    Gson gson = new Gson();
    GsonBuilder builder = gson.newBuilder();
    assertThat(builder).isNotNull();
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testExcluder() {
    Gson gson = new Gson();
    assertThat(gson.excluder()).isNotNull();
  }

  @Test
  public void testFieldNamingStrategy() {
    Gson gson = new Gson();
    assertThat(gson.fieldNamingStrategy()).isNotNull();
  }

  @Test
  public void testSerializeNulls() {
    Gson gson = new Gson();
    assertThat(gson.serializeNulls()).isFalse();

    Gson gsonWithNulls = new GsonBuilder().serializeNulls().create();
    assertThat(gsonWithNulls.serializeNulls()).isTrue();
  }

  @Test
  public void testHtmlSafe() {
    Gson gson = new Gson();
    assertThat(gson.htmlSafe()).isTrue();

    Gson gsonNotHtmlSafe = new GsonBuilder().disableHtmlEscaping().create();
    assertThat(gsonNotHtmlSafe.htmlSafe()).isFalse();
  }

  @Test
  public void testGetAdapterWithTypeToken() {
    Gson gson = new Gson();
    TypeToken<String> typeToken = TypeToken.get(String.class);
    TypeAdapter<String> adapter = gson.getAdapter(typeToken);
    assertThat(adapter).isNotNull();
  }

  @Test
  public void testGetAdapterWithClass() {
    Gson gson = new Gson();
    TypeAdapter<String> adapter = gson.getAdapter(String.class);
    assertThat(adapter).isNotNull();
  }

  @Test
  public void testGetAdapterForComplexType() {
    Gson gson = new Gson();
    TypeToken<List<String>> typeToken = new TypeToken<List<String>>() {};
    TypeAdapter<List<String>> adapter = gson.getAdapter(typeToken);
    assertThat(adapter).isNotNull();
  }

  @Test
  public void testToJsonStringSimpleObject() {
    Gson gson = new Gson();
    String json = gson.toJson("hello");
    assertThat(json).isEqualTo("\"hello\"");
  }

  @Test
  public void testToJsonStringWithType() {
    Gson gson = new Gson();
    String json = gson.toJson("hello", String.class);
    assertThat(json).isEqualTo("\"hello\"");
  }

  @Test
  public void testToJsonStringComplexObject() {
    Gson gson = new Gson();
    Map<String, String> map = new HashMap<String, String>();
    map.put("key", "value");
    String json = gson.toJson(map);
    assertThat(json).isEqualTo("{\"key\":\"value\"}");
  }

  @Test
  public void testToJsonAppendable() throws IOException {
    Gson gson = new Gson();
    StringWriter writer = new StringWriter();
    gson.toJson("hello", writer);
    assertThat(writer.toString()).isEqualTo("\"hello\"");
  }

  @Test
  public void testToJsonAppendableWithType() throws IOException {
    Gson gson = new Gson();
    StringWriter writer = new StringWriter();
    gson.toJson("hello", String.class, writer);
    assertThat(writer.toString()).isEqualTo("\"hello\"");
  }

  @Test
  public void testToJsonWriter() throws IOException {
    Gson gson = new Gson();
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    gson.toJson("hello", String.class, writer);
    assertThat(stringWriter.toString()).isEqualTo("\"hello\"");
  }

  @Test
  public void testToJsonTreeSimpleObject() {
    Gson gson = new Gson();
    JsonElement element = gson.toJsonTree("hello");
    assertThat(element).isInstanceOf(JsonPrimitive.class);
    assertThat(element.getAsString()).isEqualTo("hello");
  }

  @Test
  public void testToJsonTreeWithType() {
    Gson gson = new Gson();
    JsonElement element = gson.toJsonTree("hello", String.class);
    assertThat(element).isInstanceOf(JsonPrimitive.class);
    assertThat(element.getAsString()).isEqualTo("hello");
  }

  @Test
  public void testToJsonTreeComplexObject() {
    Gson gson = new Gson();
    Map<String, String> map = new HashMap<String, String>();
    map.put("key", "value");
    JsonElement element = gson.toJsonTree(map);
    assertThat(element).isInstanceOf(JsonObject.class);
    assertThat(element.getAsJsonObject().get("key").getAsString()).isEqualTo("value");
  }

  @Test
  public void testToJsonElement() {
    Gson gson = new Gson();
    JsonPrimitive element = new JsonPrimitive("test");
    String json = gson.toJson(element);
    assertThat(json).isEqualTo("\"test\"");
  }

  @Test
  public void testToJsonElementAppendable() throws IOException {
    Gson gson = new Gson();
    JsonPrimitive element = new JsonPrimitive("test");
    StringWriter writer = new StringWriter();
    gson.toJson(element, writer);
    assertThat(writer.toString()).isEqualTo("\"test\"");
  }

  @Test
  public void testToJsonElementWriter() throws IOException {
    Gson gson = new Gson();
    JsonPrimitive element = new JsonPrimitive("test");
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    gson.toJson(element, writer);
    assertThat(stringWriter.toString()).isEqualTo("\"test\"");
  }

  @Test
  public void testFromJsonStringClass() {
    Gson gson = new Gson();
    String result = gson.fromJson("\"hello\"", String.class);
    assertThat(result).isEqualTo("hello");
  }

  @Test
  public void testFromJsonStringType() {
    Gson gson = new Gson();
    String result = gson.fromJson("\"hello\"", String.class);
    assertThat(result).isEqualTo("hello");
  }

  @Test
  public void testFromJsonStringTypeToken() {
    Gson gson = new Gson();
    TypeToken<String> typeToken = TypeToken.get(String.class);
    String result = gson.fromJson("\"hello\"", typeToken);
    assertThat(result).isEqualTo("hello");
  }

  @Test
  public void testFromJsonReaderClass() throws IOException {
    Gson gson = new Gson();
    StringReader stringReader = new StringReader("\"hello\"");
    String result = gson.fromJson(stringReader, String.class);
    assertThat(result).isEqualTo("hello");
  }

  @Test
  public void testFromJsonReaderType() throws IOException {
    Gson gson = new Gson();
    StringReader stringReader = new StringReader("\"hello\"");
    String result = gson.fromJson(stringReader, String.class);
    assertThat(result).isEqualTo("hello");
  }

  @Test
  public void testFromJsonReaderTypeToken() throws IOException {
    Gson gson = new Gson();
    StringReader stringReader = new StringReader("\"hello\"");
    TypeToken<String> typeToken = TypeToken.get(String.class);
    String result = gson.fromJson(stringReader, typeToken);
    assertThat(result).isEqualTo("hello");
  }

  @Test
  public void testFromJsonJsonReaderType() throws IOException {
    Gson gson = new Gson();
    JsonReader reader = new JsonReader(new StringReader("\"hello\""));
    String result = gson.fromJson(reader, String.class);
    assertThat(result).isEqualTo("hello");
  }

  @Test
  public void testFromJsonJsonReaderTypeToken() throws IOException {
    Gson gson = new Gson();
    JsonReader reader = new JsonReader(new StringReader("\"hello\""));
    TypeToken<String> typeToken = TypeToken.get(String.class);
    String result = gson.fromJson(reader, typeToken);
    assertThat(result).isEqualTo("hello");
  }

  @Test
  public void testFromJsonElementClass() {
    Gson gson = new Gson();
    JsonPrimitive element = new JsonPrimitive("hello");
    String result = gson.fromJson(element, String.class);
    assertThat(result).isEqualTo("hello");
  }

  @Test
  public void testFromJsonElementType() {
    Gson gson = new Gson();
    JsonPrimitive element = new JsonPrimitive("hello");
    String result = gson.fromJson(element, String.class);
    assertThat(result).isEqualTo("hello");
  }

  @Test
  public void testFromJsonElementTypeToken() {
    Gson gson = new Gson();
    JsonPrimitive element = new JsonPrimitive("hello");
    TypeToken<String> typeToken = TypeToken.get(String.class);
    String result = gson.fromJson(element, typeToken);
    assertThat(result).isEqualTo("hello");
  }

  @Test
  public void testNewJsonWriter() throws IOException {
    Gson gson = new Gson();
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = gson.newJsonWriter(stringWriter);
    assertThat(writer).isNotNull();
    writer.value("test");
    writer.close();
    assertThat(stringWriter.toString()).isEqualTo("\"test\"");
  }

  @Test
  public void testNewJsonReader() throws IOException {
    Gson gson = new Gson();
    StringReader stringReader = new StringReader("\"test\"");
    JsonReader reader = gson.newJsonReader(stringReader);
    assertThat(reader).isNotNull();
    assertThat(reader.nextString()).isEqualTo("test");
  }

  @Test
  public void testToString() {
    Gson gson = new Gson();
    String result = gson.toString();
    assertThat(result).contains("Gson");
  }

  @Test
  public void testSerializeNullsInObject() {
    Gson gson = new GsonBuilder().serializeNulls().create();
    Map<String, String> map = new HashMap<String, String>();
    map.put("key", null);
    String json = gson.toJson(map);
    assertThat(json).contains("null");
  }

  @Test
  public void testDontSerializeNullsByDefault() {
    Gson gson = new Gson();
    Map<String, String> map = new HashMap<String, String>();
    map.put("key", null);
    String json = gson.toJson(map);
    assertThat(json).isEqualTo("{}");
  }

  @Test
  public void testHtmlEscaping() {
    Gson gson = new Gson();
    String result = gson.toJson("<script>");
    assertThat(result).contains("\\u003c");
  }

  @Test
  public void testDisableHtmlEscaping() {
    Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    String result = gson.toJson("<script>");
    assertThat(result).contains("<script>");
  }

  @Test
  public void testSerializeNumber() {
    Gson gson = new Gson();
    String json = gson.toJson(123);
    assertThat(json).isEqualTo("123");
  }

  @Test
  public void testDeserializeNumber() {
    Gson gson = new Gson();
    Integer result = gson.fromJson("123", Integer.class);
    assertThat(result).isEqualTo(123);
  }

  @Test
  public void testSerializeDouble() {
    Gson gson = new Gson();
    String json = gson.toJson(123.456);
    assertThat(json).isEqualTo("123.456");
  }

  @Test
  public void testDeserializeDouble() {
    Gson gson = new Gson();
    Double result = gson.fromJson("123.456", Double.class);
    assertThat(result).isEqualTo(123.456);
  }

  @Test
  public void testSerializeBoolean() {
    Gson gson = new Gson();
    String json = gson.toJson(true);
    assertThat(json).isEqualTo("true");
  }

  @Test
  public void testDeserializeBoolean() {
    Gson gson = new Gson();
    Boolean result = gson.fromJson("true", Boolean.class);
    assertThat(result).isTrue();
  }

  @Test
  public void testSerializeList() {
    Gson gson = new Gson();
    List<String> list = new ArrayList<String>();
    list.add("item1");
    list.add("item2");
    String json = gson.toJson(list);
    assertThat(json).isEqualTo("[\"item1\",\"item2\"]");
  }

  @Test
  public void testDeserializeList() {
    Gson gson = new Gson();
    TypeToken<List<String>> typeToken = new TypeToken<List<String>>() {};
    List<String> result = gson.fromJson("[\"item1\",\"item2\"]", typeToken.getType());
    assertThat(result).hasSize(2);
    assertThat(result.get(0)).isEqualTo("item1");
    assertThat(result.get(1)).isEqualTo("item2");
  }

  @Test
  public void testSerializeComplexMap() {
    Gson gson = new Gson();
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("string", "value");
    map.put("number", 123);
    map.put("boolean", true);
    String json = gson.toJson(map);
    assertThat(json).contains("\"string\":\"value\"");
    assertThat(json).contains("\"number\":123");
    assertThat(json).contains("\"boolean\":true");
  }

  @Test
  public void testDeserializeComplexMap() {
    Gson gson = new Gson();
    String json = "{\"string\":\"value\",\"number\":123,\"boolean\":true}";
    TypeToken<Map<String, Object>> typeToken = new TypeToken<Map<String, Object>>() {};
    Map<String, Object> result = gson.fromJson(json, typeToken.getType());
    assertThat(result).hasSize(3);
    assertThat(result.get("string")).isEqualTo("value");
    assertThat(result.get("number")).isEqualTo(123.0);
    assertThat(result.get("boolean")).isEqualTo(true);
  }

  @Test
  public void testFromJsonNullString() {
    Gson gson = new Gson();
    String result = gson.fromJson((String) null, String.class);
    assertThat(result).isNull();
  }

  @Test
  public void testFromJsonEmptyString() {
    Gson gson = new Gson();
    String result = gson.fromJson("", String.class);
    assertThat(result).isNull();
  }

  @Test
  public void testSerializeJsonNull() {
    Gson gson = new Gson();
    String json = gson.toJson(JsonNull.INSTANCE);
    assertThat(json).isEqualTo("null");
  }

  @Test
  public void testDeserializeJsonNull() {
    Gson gson = new Gson();
    JsonElement result = gson.fromJson("null", JsonElement.class);
    assertThat(result).isInstanceOf(JsonNull.class);
  }

  @Test
  public void testSerializeJsonObject() {
    Gson gson = new Gson();
    JsonObject obj = new JsonObject();
    obj.addProperty("key", "value");
    String json = gson.toJson(obj);
    assertThat(json).isEqualTo("{\"key\":\"value\"}");
  }

  @Test
  public void testDeserializeJsonObject() {
    Gson gson = new Gson();
    JsonElement result = gson.fromJson("{\"key\":\"value\"}", JsonElement.class);
    assertThat(result).isInstanceOf(JsonObject.class);
    assertThat(result.getAsJsonObject().get("key").getAsString()).isEqualTo("value");
  }

  @Test
  public void testSerializeJsonArray() {
    Gson gson = new Gson();
    JsonArray array = new JsonArray();
    array.add("item1");
    array.add("item2");
    String json = gson.toJson(array);
    assertThat(json).isEqualTo("[\"item1\",\"item2\"]");
  }

  @Test
  public void testDeserializeJsonArray() {
    Gson gson = new Gson();
    JsonElement result = gson.fromJson("[\"item1\",\"item2\"]", JsonElement.class);
    assertThat(result).isInstanceOf(JsonArray.class);
    assertThat(result.getAsJsonArray().size()).isEqualTo(2);
  }

  @Test(expected = JsonSyntaxException.class)
  public void testFromJsonMalformedJson() {
    Gson gson = new Gson();
    gson.fromJson("{malformed", String.class);
  }

  @Test
  public void testSerializeLong() {
    Gson gson = new Gson();
    String json = gson.toJson(123456789L);
    assertThat(json).isEqualTo("123456789");
  }

  @Test
  public void testDeserializeLong() {
    Gson gson = new Gson();
    Long result = gson.fromJson("123456789", Long.class);
    assertThat(result).isEqualTo(123456789L);
  }

  @Test
  public void testNewBuilderPreservesSettings() {
    Gson original = new GsonBuilder()
        .serializeNulls()
        .disableHtmlEscaping()
        .create();

    Gson rebuilt = original.newBuilder().create();
    assertThat(rebuilt.serializeNulls()).isTrue();
    assertThat(rebuilt.htmlSafe()).isFalse();
  }

  @Test
  public void testToJsonWithNullObject() {
    Gson gson = new Gson();
    String json = gson.toJson(null);
    assertThat(json).isEqualTo("null");
  }

  @Test
  public void testToJsonWithNullObjectAndType() {
    Gson gson = new Gson();
    String json = gson.toJson(null, String.class);
    assertThat(json).isEqualTo("null");
  }

  @Test
  public void testSerializeFloat() {
    Gson gson = new Gson();
    String json = gson.toJson(123.45f);
    assertThat(json).isEqualTo("123.45");
  }

  @Test
  public void testDeserializeFloat() {
    Gson gson = new Gson();
    Float result = gson.fromJson("123.45", Float.class);
    assertThat(result).isWithin(0.01f).of(123.45f);
  }

  static class TestObject {
    String name;
    int value;

    TestObject(String name, int value) {
      this.name = name;
      this.value = value;
    }
  }

  @Test
  public void testSerializeCustomObject() {
    Gson gson = new Gson();
    TestObject obj = new TestObject("test", 123);
    String json = gson.toJson(obj);
    assertThat(json).contains("\"name\":\"test\"");
    assertThat(json).contains("\"value\":123");
  }

  @Test
  public void testDeserializeCustomObject() {
    Gson gson = new Gson();
    String json = "{\"name\":\"test\",\"value\":123}";
    TestObject result = gson.fromJson(json, TestObject.class);
    assertThat(result).isNotNull();
    assertThat(result.name).isEqualTo("test");
    assertThat(result.value).isEqualTo(123);
  }

  @Test
  public void testJsonWriterConfiguration() throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = gson.newJsonWriter(stringWriter);
    assertThat(writer).isNotNull();
    assertThat(writer.isLenient()).isFalse();
  }

  @Test
  public void testJsonReaderConfiguration() throws IOException {
    Gson gson = new Gson();
    StringReader stringReader = new StringReader("\"test\"");
    JsonReader reader = gson.newJsonReader(stringReader);
    assertThat(reader).isNotNull();
    assertThat(reader.isLenient()).isFalse();
  }

  @Test
  public void testSerializeShort() {
    Gson gson = new Gson();
    String json = gson.toJson((short) 123);
    assertThat(json).isEqualTo("123");
  }

  @Test
  public void testDeserializeShort() {
    Gson gson = new Gson();
    Short result = gson.fromJson("123", Short.class);
    assertThat(result).isEqualTo((short) 123);
  }

  @Test
  public void testSerializeByte() {
    Gson gson = new Gson();
    String json = gson.toJson((byte) 123);
    assertThat(json).isEqualTo("123");
  }

  @Test
  public void testDeserializeByte() {
    Gson gson = new Gson();
    Byte result = gson.fromJson("123", Byte.class);
    assertThat(result).isEqualTo((byte) 123);
  }

  @Test
  public void testSerializeCharacter() {
    Gson gson = new Gson();
    String json = gson.toJson('a');
    assertThat(json).isEqualTo("\"a\"");
  }

  @Test
  public void testDeserializeCharacter() {
    Gson gson = new Gson();
    Character result = gson.fromJson("\"a\"", Character.class);
    assertThat(result).isEqualTo('a');
  }

  @Test
  public void testAssertFullConsumptionThrowsWhenJsonNotFullyConsumed() throws Exception {
    Method method = Gson.class.getDeclaredMethod("assertFullConsumption", Object.class, JsonReader.class);
    method.setAccessible(true);

    JsonReader mockReader = new JsonReader(new StringReader("")) {
      @Override
      public JsonToken peek() throws IOException {
        return JsonToken.BEGIN_OBJECT;
      }
    };

    try {
      method.invoke(null, "nonNullObject", mockReader);
      throw new AssertionError("Expected JsonSyntaxException");
    } catch (java.lang.reflect.InvocationTargetException e) {
      assertThat(e.getCause()).isInstanceOf(JsonSyntaxException.class);
      assertThat(e.getCause().getMessage()).contains("JSON document was not fully consumed");
    }
  }

  @Test
  public void testAssertFullConsumptionHandlesMalformedJsonException() throws Exception {
    Method method = Gson.class.getDeclaredMethod("assertFullConsumption", Object.class, JsonReader.class);
    method.setAccessible(true);

    JsonReader mockReader = new JsonReader(new StringReader("")) {
      @Override
      public JsonToken peek() throws IOException {
        throw new MalformedJsonException("Test malformed JSON");
      }
    };

    try {
      method.invoke(null, "nonNullObject", mockReader);
      throw new AssertionError("Expected JsonSyntaxException");
    } catch (java.lang.reflect.InvocationTargetException e) {
      assertThat(e.getCause()).isInstanceOf(JsonSyntaxException.class);
      assertThat(e.getCause().getCause()).isInstanceOf(MalformedJsonException.class);
    }
  }

  @Test
  public void testAssertFullConsumptionHandlesIOException() throws Exception {
    Method method = Gson.class.getDeclaredMethod("assertFullConsumption", Object.class, JsonReader.class);
    method.setAccessible(true);

    JsonReader mockReader = new JsonReader(new StringReader("")) {
      @Override
      public JsonToken peek() throws IOException {
        throw new IOException("Test IO error");
      }
    };

    try {
      method.invoke(null, "nonNullObject", mockReader);
      throw new AssertionError("Expected JsonIOException");
    } catch (java.lang.reflect.InvocationTargetException e) {
      assertThat(e.getCause()).isInstanceOf(JsonIOException.class);
      assertThat(e.getCause().getCause()).isInstanceOf(IOException.class);
    }
  }
}
