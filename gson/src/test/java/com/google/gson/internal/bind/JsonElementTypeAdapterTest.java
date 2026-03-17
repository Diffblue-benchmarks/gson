/*
 * Copyright (C) 2024 Google Inc.
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

package com.google.gson.internal.bind;

import static com.google.common.truth.Truth.assertThat;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;

public class JsonElementTypeAdapterTest {

  @Test
  public void testReadString() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("\"test\""));
    JsonElement element = JsonElementTypeAdapter.ADAPTER.read(reader);
    assertThat(element.isJsonPrimitive()).isTrue();
    assertThat(element.getAsString()).isEqualTo("test");
  }

  @Test
  public void testReadNumber() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("123"));
    JsonElement element = JsonElementTypeAdapter.ADAPTER.read(reader);
    assertThat(element.isJsonPrimitive()).isTrue();
    assertThat(element.getAsInt()).isEqualTo(123);
  }

  @Test
  public void testReadBoolean() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("true"));
    JsonElement element = JsonElementTypeAdapter.ADAPTER.read(reader);
    assertThat(element.isJsonPrimitive()).isTrue();
    assertThat(element.getAsBoolean()).isTrue();
  }

  @Test
  public void testReadNull() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("null"));
    JsonElement element = JsonElementTypeAdapter.ADAPTER.read(reader);
    assertThat(element.isJsonNull()).isTrue();
  }

  @Test
  public void testReadEmptyArray() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[]"));
    JsonElement element = JsonElementTypeAdapter.ADAPTER.read(reader);
    assertThat(element.isJsonArray()).isTrue();
    assertThat(element.getAsJsonArray().size()).isEqualTo(0);
  }

  @Test
  public void testReadArrayWithElements() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[1, 2, 3]"));
    JsonElement element = JsonElementTypeAdapter.ADAPTER.read(reader);
    assertThat(element.isJsonArray()).isTrue();
    JsonArray array = element.getAsJsonArray();
    assertThat(array.size()).isEqualTo(3);
    assertThat(array.get(0).getAsInt()).isEqualTo(1);
    assertThat(array.get(1).getAsInt()).isEqualTo(2);
    assertThat(array.get(2).getAsInt()).isEqualTo(3);
  }

  @Test
  public void testReadEmptyObject() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    JsonElement element = JsonElementTypeAdapter.ADAPTER.read(reader);
    assertThat(element.isJsonObject()).isTrue();
    assertThat(element.getAsJsonObject().size()).isEqualTo(0);
  }

  @Test
  public void testReadObjectWithMembers() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{\"key\":\"value\"}"));
    JsonElement element = JsonElementTypeAdapter.ADAPTER.read(reader);
    assertThat(element.isJsonObject()).isTrue();
    JsonObject obj = element.getAsJsonObject();
    assertThat(obj.has("key")).isTrue();
    assertThat(obj.get("key").getAsString()).isEqualTo("value");
  }

  @Test
  public void testReadNestedArray() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[[1, 2], [3, 4]]"));
    JsonElement element = JsonElementTypeAdapter.ADAPTER.read(reader);
    assertThat(element.isJsonArray()).isTrue();
    JsonArray array = element.getAsJsonArray();
    assertThat(array.size()).isEqualTo(2);
    assertThat(array.get(0).getAsJsonArray().get(0).getAsInt()).isEqualTo(1);
    assertThat(array.get(1).getAsJsonArray().get(1).getAsInt()).isEqualTo(4);
  }

  @Test
  public void testReadNestedObject() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{\"outer\":{\"inner\":\"value\"}}"));
    JsonElement element = JsonElementTypeAdapter.ADAPTER.read(reader);
    assertThat(element.isJsonObject()).isTrue();
    JsonObject obj = element.getAsJsonObject();
    assertThat(obj.has("outer")).isTrue();
    JsonObject inner = obj.get("outer").getAsJsonObject();
    assertThat(inner.get("inner").getAsString()).isEqualTo("value");
  }

  @Test
  public void testReadArrayWithMixedTypes() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[\"string\", 123, true, null]"));
    JsonElement element = JsonElementTypeAdapter.ADAPTER.read(reader);
    assertThat(element.isJsonArray()).isTrue();
    JsonArray array = element.getAsJsonArray();
    assertThat(array.size()).isEqualTo(4);
    assertThat(array.get(0).getAsString()).isEqualTo("string");
    assertThat(array.get(1).getAsInt()).isEqualTo(123);
    assertThat(array.get(2).getAsBoolean()).isTrue();
    assertThat(array.get(3).isJsonNull()).isTrue();
  }

  @Test
  public void testReadObjectWithMultipleMembers() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{\"a\":1,\"b\":2,\"c\":3}"));
    JsonElement element = JsonElementTypeAdapter.ADAPTER.read(reader);
    assertThat(element.isJsonObject()).isTrue();
    JsonObject obj = element.getAsJsonObject();
    assertThat(obj.size()).isEqualTo(3);
    assertThat(obj.get("a").getAsInt()).isEqualTo(1);
    assertThat(obj.get("b").getAsInt()).isEqualTo(2);
    assertThat(obj.get("c").getAsInt()).isEqualTo(3);
  }

  @Test
  public void testReadComplexNestedStructure() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{\"array\":[1,{\"nested\":true}],\"obj\":{\"key\":\"value\"}}"));
    JsonElement element = JsonElementTypeAdapter.ADAPTER.read(reader);
    assertThat(element.isJsonObject()).isTrue();
    JsonObject obj = element.getAsJsonObject();
    assertThat(obj.get("array").getAsJsonArray().get(1).getAsJsonObject().get("nested").getAsBoolean()).isTrue();
  }

  @Test
  public void testReadDeeplyNestedArray() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[[[1]]]"));
    JsonElement element = JsonElementTypeAdapter.ADAPTER.read(reader);
    assertThat(element.isJsonArray()).isTrue();
    JsonArray level1 = element.getAsJsonArray();
    JsonArray level2 = level1.get(0).getAsJsonArray();
    JsonArray level3 = level2.get(0).getAsJsonArray();
    assertThat(level3.get(0).getAsInt()).isEqualTo(1);
  }

  @Test
  public void testWriteNull() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    JsonElementTypeAdapter.ADAPTER.write(writer, null);
    assertThat(stringWriter.toString()).isEqualTo("null");
  }

  @Test
  public void testWriteJsonNull() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    JsonElementTypeAdapter.ADAPTER.write(writer, JsonNull.INSTANCE);
    assertThat(stringWriter.toString()).isEqualTo("null");
  }

  @Test
  public void testWriteStringPrimitive() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    JsonElementTypeAdapter.ADAPTER.write(writer, new JsonPrimitive("test"));
    assertThat(stringWriter.toString()).isEqualTo("\"test\"");
  }

  @Test
  public void testWriteNumberPrimitive() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    JsonElementTypeAdapter.ADAPTER.write(writer, new JsonPrimitive(123));
    assertThat(stringWriter.toString()).isEqualTo("123");
  }

  @Test
  public void testWriteBooleanPrimitive() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    JsonElementTypeAdapter.ADAPTER.write(writer, new JsonPrimitive(true));
    assertThat(stringWriter.toString()).isEqualTo("true");
  }

  @Test
  public void testWriteEmptyArray() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    JsonElementTypeAdapter.ADAPTER.write(writer, new JsonArray());
    assertThat(stringWriter.toString()).isEqualTo("[]");
  }

  @Test
  public void testWriteArrayWithElements() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    JsonArray array = new JsonArray();
    array.add(new JsonPrimitive(1));
    array.add(new JsonPrimitive(2));
    array.add(new JsonPrimitive(3));
    JsonElementTypeAdapter.ADAPTER.write(writer, array);
    assertThat(stringWriter.toString()).isEqualTo("[1,2,3]");
  }

  @Test
  public void testWriteEmptyObject() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    JsonElementTypeAdapter.ADAPTER.write(writer, new JsonObject());
    assertThat(stringWriter.toString()).isEqualTo("{}");
  }

  @Test
  public void testWriteObjectWithMembers() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    JsonObject obj = new JsonObject();
    obj.add("key", new JsonPrimitive("value"));
    JsonElementTypeAdapter.ADAPTER.write(writer, obj);
    assertThat(stringWriter.toString()).isEqualTo("{\"key\":\"value\"}");
  }

  @Test
  public void testWriteNestedArray() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    JsonArray inner1 = new JsonArray();
    inner1.add(new JsonPrimitive(1));
    inner1.add(new JsonPrimitive(2));
    JsonArray inner2 = new JsonArray();
    inner2.add(new JsonPrimitive(3));
    inner2.add(new JsonPrimitive(4));
    JsonArray outer = new JsonArray();
    outer.add(inner1);
    outer.add(inner2);
    JsonElementTypeAdapter.ADAPTER.write(writer, outer);
    assertThat(stringWriter.toString()).isEqualTo("[[1,2],[3,4]]");
  }

  @Test
  public void testWriteNestedObject() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    JsonObject inner = new JsonObject();
    inner.add("inner", new JsonPrimitive("value"));
    JsonObject outer = new JsonObject();
    outer.add("outer", inner);
    JsonElementTypeAdapter.ADAPTER.write(writer, outer);
    assertThat(stringWriter.toString()).isEqualTo("{\"outer\":{\"inner\":\"value\"}}");
  }

  @Test
  public void testWriteArrayWithMixedTypes() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    JsonArray array = new JsonArray();
    array.add(new JsonPrimitive("string"));
    array.add(new JsonPrimitive(123));
    array.add(new JsonPrimitive(true));
    array.add(JsonNull.INSTANCE);
    JsonElementTypeAdapter.ADAPTER.write(writer, array);
    assertThat(stringWriter.toString()).isEqualTo("[\"string\",123,true,null]");
  }

  @Test
  public void testWriteObjectWithMultipleMembers() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    JsonObject obj = new JsonObject();
    obj.add("a", new JsonPrimitive(1));
    obj.add("b", new JsonPrimitive(2));
    obj.add("c", new JsonPrimitive(3));
    JsonElementTypeAdapter.ADAPTER.write(writer, obj);
    assertThat(stringWriter.toString()).isEqualTo("{\"a\":1,\"b\":2,\"c\":3}");
  }

  @Test
  public void testWriteComplexNestedStructure() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    JsonObject nestedObj = new JsonObject();
    nestedObj.add("nested", new JsonPrimitive(true));
    JsonArray array = new JsonArray();
    array.add(new JsonPrimitive(1));
    array.add(nestedObj);
    JsonObject innerObj = new JsonObject();
    innerObj.add("key", new JsonPrimitive("value"));
    JsonObject outerObj = new JsonObject();
    outerObj.add("array", array);
    outerObj.add("obj", innerObj);
    JsonElementTypeAdapter.ADAPTER.write(writer, outerObj);
    assertThat(stringWriter.toString()).isEqualTo("{\"array\":[1,{\"nested\":true}],\"obj\":{\"key\":\"value\"}}");
  }

  @Test
  public void testReadDecimalNumber() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("123.45"));
    JsonElement element = JsonElementTypeAdapter.ADAPTER.read(reader);
    assertThat(element.isJsonPrimitive()).isTrue();
    assertThat(element.getAsDouble()).isEqualTo(123.45);
  }

  @Test
  public void testReadFalseBoolean() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("false"));
    JsonElement element = JsonElementTypeAdapter.ADAPTER.read(reader);
    assertThat(element.isJsonPrimitive()).isTrue();
    assertThat(element.getAsBoolean()).isFalse();
  }

  @Test
  public void testWriteFalseBooleanPrimitive() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    JsonElementTypeAdapter.ADAPTER.write(writer, new JsonPrimitive(false));
    assertThat(stringWriter.toString()).isEqualTo("false");
  }

  @Test
  public void testWriteDecimalNumberPrimitive() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    JsonElementTypeAdapter.ADAPTER.write(writer, new JsonPrimitive(123.45));
    assertThat(stringWriter.toString()).isEqualTo("123.45");
  }

  @Test
  public void testReadArrayWithNestedObjects() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[{\"a\":1},{\"b\":2}]"));
    JsonElement element = JsonElementTypeAdapter.ADAPTER.read(reader);
    assertThat(element.isJsonArray()).isTrue();
    JsonArray array = element.getAsJsonArray();
    assertThat(array.size()).isEqualTo(2);
    assertThat(array.get(0).getAsJsonObject().get("a").getAsInt()).isEqualTo(1);
    assertThat(array.get(1).getAsJsonObject().get("b").getAsInt()).isEqualTo(2);
  }

  @Test
  public void testReadObjectWithNestedArrays() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{\"a\":[1,2],\"b\":[3,4]}"));
    JsonElement element = JsonElementTypeAdapter.ADAPTER.read(reader);
    assertThat(element.isJsonObject()).isTrue();
    JsonObject obj = element.getAsJsonObject();
    assertThat(obj.get("a").getAsJsonArray().get(0).getAsInt()).isEqualTo(1);
    assertThat(obj.get("b").getAsJsonArray().get(1).getAsInt()).isEqualTo(4);
  }

  @Test
  public void testWriteArrayWithNestedObjects() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    JsonObject obj1 = new JsonObject();
    obj1.add("a", new JsonPrimitive(1));
    JsonObject obj2 = new JsonObject();
    obj2.add("b", new JsonPrimitive(2));
    JsonArray array = new JsonArray();
    array.add(obj1);
    array.add(obj2);
    JsonElementTypeAdapter.ADAPTER.write(writer, array);
    assertThat(stringWriter.toString()).isEqualTo("[{\"a\":1},{\"b\":2}]");
  }

  @Test
  public void testWriteObjectWithNestedArrays() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    JsonArray array1 = new JsonArray();
    array1.add(new JsonPrimitive(1));
    array1.add(new JsonPrimitive(2));
    JsonArray array2 = new JsonArray();
    array2.add(new JsonPrimitive(3));
    array2.add(new JsonPrimitive(4));
    JsonObject obj = new JsonObject();
    obj.add("a", array1);
    obj.add("b", array2);
    JsonElementTypeAdapter.ADAPTER.write(writer, obj);
    assertThat(stringWriter.toString()).isEqualTo("{\"a\":[1,2],\"b\":[3,4]}");
  }

  @Test
  public void testReadFromJsonTreeReader() throws IOException {
    JsonObject obj = new JsonObject();
    obj.add("key", new JsonPrimitive("value"));
    JsonTreeReader reader = new JsonTreeReader(obj);
    JsonElement element = JsonElementTypeAdapter.ADAPTER.read(reader);
    assertThat(element.isJsonObject()).isTrue();
    assertThat(element.getAsJsonObject().get("key").getAsString()).isEqualTo("value");
  }

  @Test
  public void testReadArrayFromJsonTreeReader() throws IOException {
    JsonArray array = new JsonArray();
    array.add(new JsonPrimitive(1));
    array.add(new JsonPrimitive(2));
    JsonTreeReader reader = new JsonTreeReader(array);
    JsonElement element = JsonElementTypeAdapter.ADAPTER.read(reader);
    assertThat(element.isJsonArray()).isTrue();
    assertThat(element.getAsJsonArray().size()).isEqualTo(2);
  }

  @Test
  public void testReadPrimitiveFromJsonTreeReader() throws IOException {
    JsonPrimitive primitive = new JsonPrimitive("test");
    JsonTreeReader reader = new JsonTreeReader(primitive);
    JsonElement element = JsonElementTypeAdapter.ADAPTER.read(reader);
    assertThat(element.isJsonPrimitive()).isTrue();
    assertThat(element.getAsString()).isEqualTo("test");
  }
}
