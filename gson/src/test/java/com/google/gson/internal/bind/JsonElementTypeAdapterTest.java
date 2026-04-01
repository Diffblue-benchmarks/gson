/*
 * Copyright (C) 2024 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.gson.internal.bind;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

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

public final class JsonElementTypeAdapterTest {

  private static JsonElement readJson(String json) throws IOException {
    JsonReader reader = new JsonReader(new StringReader(json));
    return JsonElementTypeAdapter.ADAPTER.read(reader);
  }

  private static String writeJson(JsonElement element) throws IOException {
    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    JsonElementTypeAdapter.ADAPTER.write(writer, element);
    writer.flush();
    return sw.toString();
  }

  @Test
  public void testReadString() throws IOException {
    JsonElement result = readJson("\"hello\"");
    assertThat(result).isInstanceOf(JsonPrimitive.class);
    assertThat(result.getAsString()).isEqualTo("hello");
  }

  @Test
  public void testReadNumber() throws IOException {
    JsonElement result = readJson("42");
    assertThat(result).isInstanceOf(JsonPrimitive.class);
    assertThat(result.getAsInt()).isEqualTo(42);
  }

  @Test
  public void testReadBoolean() throws IOException {
    JsonElement trueResult = readJson("true");
    assertThat(trueResult).isInstanceOf(JsonPrimitive.class);
    assertThat(trueResult.getAsBoolean()).isTrue();

    JsonElement falseResult = readJson("false");
    assertThat(falseResult).isInstanceOf(JsonPrimitive.class);
    assertThat(falseResult.getAsBoolean()).isFalse();
  }

  @Test
  public void testReadNull() throws IOException {
    JsonElement result = readJson("null");
    assertThat(result).isEqualTo(JsonNull.INSTANCE);
  }

  @Test
  public void testReadEmptyArray() throws IOException {
    JsonElement result = readJson("[]");
    assertThat(result).isInstanceOf(JsonArray.class);
    assertThat(result.getAsJsonArray().size()).isEqualTo(0);
  }

  @Test
  public void testReadArrayWithElements() throws IOException {
    JsonElement result = readJson("[1, \"two\", true]");
    assertThat(result).isInstanceOf(JsonArray.class);
    JsonArray array = result.getAsJsonArray();
    assertThat(array.size()).isEqualTo(3);
    assertThat(array.get(0).getAsInt()).isEqualTo(1);
    assertThat(array.get(1).getAsString()).isEqualTo("two");
    assertThat(array.get(2).getAsBoolean()).isTrue();
  }

  @Test
  public void testReadEmptyObject() throws IOException {
    JsonElement result = readJson("{}");
    assertThat(result).isInstanceOf(JsonObject.class);
    assertThat(result.getAsJsonObject().size()).isEqualTo(0);
  }

  @Test
  public void testReadObjectWithFields() throws IOException {
    JsonElement result = readJson("{\"key\": \"value\", \"num\": 99}");
    assertThat(result).isInstanceOf(JsonObject.class);
    JsonObject obj = result.getAsJsonObject();
    assertThat(obj.get("key").getAsString()).isEqualTo("value");
    assertThat(obj.get("num").getAsInt()).isEqualTo(99);
  }

  @Test
  public void testReadNestedObject() throws IOException {
    JsonElement result = readJson("{\"outer\": {\"inner\": [1, 2]}}");
    assertThat(result).isInstanceOf(JsonObject.class);
    JsonObject outer = result.getAsJsonObject();
    JsonObject innerObj = outer.getAsJsonObject("outer");
    JsonArray innerArr = innerObj.getAsJsonArray("inner");
    assertThat(innerArr.get(0).getAsInt()).isEqualTo(1);
    assertThat(innerArr.get(1).getAsInt()).isEqualTo(2);
  }

  @Test
  public void testReadNestedArrays() throws IOException {
    JsonElement result = readJson("[[1, 2], [3, 4]]");
    assertThat(result).isInstanceOf(JsonArray.class);
    JsonArray outer = result.getAsJsonArray();
    assertThat(outer.get(0).getAsJsonArray().get(0).getAsInt()).isEqualTo(1);
    assertThat(outer.get(1).getAsJsonArray().get(1).getAsInt()).isEqualTo(4);
  }

  @Test
  public void testWriteNull() throws IOException {
    assertThat(writeJson(null)).isEqualTo("null");
  }

  @Test
  public void testWriteJsonNull() throws IOException {
    assertThat(writeJson(JsonNull.INSTANCE)).isEqualTo("null");
  }

  @Test
  public void testWriteStringPrimitive() throws IOException {
    assertThat(writeJson(new JsonPrimitive("hello"))).isEqualTo("\"hello\"");
  }

  @Test
  public void testWriteNumberPrimitive() throws IOException {
    assertThat(writeJson(new JsonPrimitive(42))).isEqualTo("42");
  }

  @Test
  public void testWriteBooleanPrimitive() throws IOException {
    assertThat(writeJson(new JsonPrimitive(true))).isEqualTo("true");
    assertThat(writeJson(new JsonPrimitive(false))).isEqualTo("false");
  }

  @Test
  public void testWriteEmptyArray() throws IOException {
    assertThat(writeJson(new JsonArray())).isEqualTo("[]");
  }

  @Test
  public void testWriteArrayWithElements() throws IOException {
    JsonArray array = new JsonArray();
    array.add(1);
    array.add("two");
    array.add(true);
    String output = writeJson(array);
    assertThat(output).isEqualTo("[1,\"two\",true]");
  }

  @Test
  public void testWriteEmptyObject() throws IOException {
    assertThat(writeJson(new JsonObject())).isEqualTo("{}");
  }

  @Test
  public void testWriteObjectWithFields() throws IOException {
    JsonObject obj = new JsonObject();
    obj.addProperty("a", "b");
    obj.addProperty("n", 5);
    String output = writeJson(obj);
    assertThat(output).isEqualTo("{\"a\":\"b\",\"n\":5}");
  }

  @Test
  public void testWriteNestedStructure() throws IOException {
    JsonObject obj = new JsonObject();
    JsonArray arr = new JsonArray();
    arr.add(1);
    arr.add(2);
    obj.add("arr", arr);
    String output = writeJson(obj);
    assertThat(output).isEqualTo("{\"arr\":[1,2]}");
  }

  @Test
  public void testReadViaJsonTreeReader() throws IOException {
    JsonObject original = new JsonObject();
    original.addProperty("key", "value");
    JsonReader treeReader = new JsonTreeReader(original);
    JsonElement result = JsonElementTypeAdapter.ADAPTER.read(treeReader);
    assertThat(result).isEqualTo(original);
  }

  @Test
  public void testRoundTrip() throws IOException {
    String json = "{\"name\":\"test\",\"values\":[1,2,null,true]}";
    JsonElement parsed = readJson(json);
    String output = writeJson(parsed);
    assertThat(output).isEqualTo(json);
  }
}
