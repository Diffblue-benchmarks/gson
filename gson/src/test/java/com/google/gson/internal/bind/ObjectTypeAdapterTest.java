/*
 * Copyright (C) 2011 Google Inc.
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.ToNumberPolicy;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class ObjectTypeAdapterTest {

  @Test
  public void testGetFactoryWithDoublePolicy() {
    TypeAdapterFactory factory = ObjectTypeAdapter.getFactory(ToNumberPolicy.DOUBLE);
    assertThat(factory).isNotNull();
  }

  @Test
  public void testGetFactoryWithLazilyParsedNumberPolicy() {
    TypeAdapterFactory factory = ObjectTypeAdapter.getFactory(ToNumberPolicy.LAZILY_PARSED_NUMBER);
    assertThat(factory).isNotNull();
  }

  @Test
  public void testFactoryCreatesAdapterForObjectType() {
    Gson gson = new Gson();
    TypeAdapterFactory factory = ObjectTypeAdapter.getFactory(ToNumberPolicy.DOUBLE);
    TypeAdapter<Object> adapter = factory.create(gson, TypeToken.get(Object.class));
    assertThat(adapter).isNotNull();
  }

  @Test
  public void testFactoryReturnsNullForNonObjectType() {
    Gson gson = new Gson();
    TypeAdapterFactory factory = ObjectTypeAdapter.getFactory(ToNumberPolicy.DOUBLE);
    TypeAdapter<String> adapter = factory.create(gson, TypeToken.get(String.class));
    assertThat(adapter).isNull();
  }

  @Test
  public void testReadString() throws IOException {
    Gson gson = new GsonBuilder()
        .registerTypeAdapterFactory(ObjectTypeAdapter.getFactory(ToNumberPolicy.DOUBLE))
        .create();
    Object result = gson.fromJson("\"hello\"", Object.class);
    assertThat(result).isEqualTo("hello");
  }

  @Test
  public void testReadNumber() throws IOException {
    Gson gson = new GsonBuilder()
        .registerTypeAdapterFactory(ObjectTypeAdapter.getFactory(ToNumberPolicy.DOUBLE))
        .create();
    Object result = gson.fromJson("42.5", Object.class);
    assertThat(result).isInstanceOf(Double.class);
    assertThat(result).isEqualTo(42.5);
  }

  @Test
  public void testReadNumberWithLazilyParsedNumber() throws IOException {
    Gson gson = new GsonBuilder()
        .registerTypeAdapterFactory(ObjectTypeAdapter.getFactory(ToNumberPolicy.LAZILY_PARSED_NUMBER))
        .create();
    Object result = gson.fromJson("123", Object.class);
    assertThat(result).isNotNull();
  }

  @Test
  public void testReadBoolean() throws IOException {
    Gson gson = new GsonBuilder()
        .registerTypeAdapterFactory(ObjectTypeAdapter.getFactory(ToNumberPolicy.DOUBLE))
        .create();
    Object result = gson.fromJson("true", Object.class);
    assertThat(result).isEqualTo(true);
  }

  @Test
  public void testReadNull() throws IOException {
    Gson gson = new GsonBuilder()
        .registerTypeAdapterFactory(ObjectTypeAdapter.getFactory(ToNumberPolicy.DOUBLE))
        .create();
    Object result = gson.fromJson("null", Object.class);
    assertThat(result).isNull();
  }

  @Test
  public void testReadEmptyArray() throws IOException {
    Gson gson = new GsonBuilder()
        .registerTypeAdapterFactory(ObjectTypeAdapter.getFactory(ToNumberPolicy.DOUBLE))
        .create();
    Object result = gson.fromJson("[]", Object.class);
    assertThat(result).isInstanceOf(List.class);
    assertThat(((List<?>) result).size()).isEqualTo(0);
  }

  @Test
  public void testReadArrayWithValues() throws IOException {
    Gson gson = new GsonBuilder()
        .registerTypeAdapterFactory(ObjectTypeAdapter.getFactory(ToNumberPolicy.DOUBLE))
        .create();
    Object result = gson.fromJson("[1, \"two\", true, null]", Object.class);
    assertThat(result).isInstanceOf(List.class);
    List<?> list = (List<?>) result;
    assertThat(list.size()).isEqualTo(4);
    assertThat(list.get(0)).isEqualTo(1.0);
    assertThat(list.get(1)).isEqualTo("two");
    assertThat(list.get(2)).isEqualTo(true);
    assertThat(list.get(3)).isNull();
  }

  @Test
  public void testReadNestedArray() throws IOException {
    Gson gson = new GsonBuilder()
        .registerTypeAdapterFactory(ObjectTypeAdapter.getFactory(ToNumberPolicy.DOUBLE))
        .create();
    Object result = gson.fromJson("[[1, 2], [3, 4]]", Object.class);
    assertThat(result).isInstanceOf(List.class);
    List<?> list = (List<?>) result;
    assertThat(list.size()).isEqualTo(2);
    assertThat(list.get(0)).isInstanceOf(List.class);
    assertThat(list.get(1)).isInstanceOf(List.class);
  }

  @Test
  public void testReadEmptyObject() throws IOException {
    Gson gson = new GsonBuilder()
        .registerTypeAdapterFactory(ObjectTypeAdapter.getFactory(ToNumberPolicy.DOUBLE))
        .create();
    Object result = gson.fromJson("{}", Object.class);
    assertThat(result).isInstanceOf(Map.class);
    assertThat(((Map<?, ?>) result).size()).isEqualTo(0);
  }

  @Test
  public void testReadObjectWithProperties() throws IOException {
    Gson gson = new GsonBuilder()
        .registerTypeAdapterFactory(ObjectTypeAdapter.getFactory(ToNumberPolicy.DOUBLE))
        .create();
    Object result = gson.fromJson("{\"name\": \"John\", \"age\": 30}", Object.class);
    assertThat(result).isInstanceOf(Map.class);
    Map<?, ?> map = (Map<?, ?>) result;
    assertThat(map.size()).isEqualTo(2);
    assertThat(map.get("name")).isEqualTo("John");
    assertThat(map.get("age")).isEqualTo(30.0);
  }

  @Test
  public void testReadNestedObject() throws IOException {
    Gson gson = new GsonBuilder()
        .registerTypeAdapterFactory(ObjectTypeAdapter.getFactory(ToNumberPolicy.DOUBLE))
        .create();
    Object result = gson.fromJson("{\"outer\": {\"inner\": \"value\"}}", Object.class);
    assertThat(result).isInstanceOf(Map.class);
    Map<?, ?> map = (Map<?, ?>) result;
    assertThat(map.get("outer")).isInstanceOf(Map.class);
    Map<?, ?> nested = (Map<?, ?>) map.get("outer");
    assertThat(nested.get("inner")).isEqualTo("value");
  }

  @Test
  public void testReadArrayInObject() throws IOException {
    Gson gson = new GsonBuilder()
        .registerTypeAdapterFactory(ObjectTypeAdapter.getFactory(ToNumberPolicy.DOUBLE))
        .create();
    Object result = gson.fromJson("{\"items\": [1, 2, 3]}", Object.class);
    assertThat(result).isInstanceOf(Map.class);
    Map<?, ?> map = (Map<?, ?>) result;
    assertThat(map.get("items")).isInstanceOf(List.class);
    List<?> items = (List<?>) map.get("items");
    assertThat(items.size()).isEqualTo(3);
  }

  @Test
  public void testReadObjectInArray() throws IOException {
    Gson gson = new GsonBuilder()
        .registerTypeAdapterFactory(ObjectTypeAdapter.getFactory(ToNumberPolicy.DOUBLE))
        .create();
    Object result = gson.fromJson("[{\"key\": \"value\"}]", Object.class);
    assertThat(result).isInstanceOf(List.class);
    List<?> list = (List<?>) result;
    assertThat(list.size()).isEqualTo(1);
    assertThat(list.get(0)).isInstanceOf(Map.class);
  }

  @Test
  public void testReadComplexNestedStructure() throws IOException {
    Gson gson = new GsonBuilder()
        .registerTypeAdapterFactory(ObjectTypeAdapter.getFactory(ToNumberPolicy.DOUBLE))
        .create();
    String json = "{\"users\": [{\"name\": \"Alice\", \"scores\": [10, 20]}, {\"name\": \"Bob\", \"scores\": [15, 25]}]}";
    Object result = gson.fromJson(json, Object.class);
    assertThat(result).isInstanceOf(Map.class);
    Map<?, ?> map = (Map<?, ?>) result;
    assertThat(map.get("users")).isInstanceOf(List.class);
    List<?> users = (List<?>) map.get("users");
    assertThat(users.size()).isEqualTo(2);
  }

  @Test
  public void testWriteNull() throws IOException {
    Gson gson = new GsonBuilder()
        .registerTypeAdapterFactory(ObjectTypeAdapter.getFactory(ToNumberPolicy.DOUBLE))
        .create();
    String json = gson.toJson(null, Object.class);
    assertThat(json).isEqualTo("null");
  }

  @Test
  public void testWriteString() throws IOException {
    Gson gson = new GsonBuilder()
        .registerTypeAdapterFactory(ObjectTypeAdapter.getFactory(ToNumberPolicy.DOUBLE))
        .create();
    String json = gson.toJson("hello", Object.class);
    assertThat(json).isEqualTo("\"hello\"");
  }

  @Test
  public void testWriteNumber() throws IOException {
    Gson gson = new GsonBuilder()
        .registerTypeAdapterFactory(ObjectTypeAdapter.getFactory(ToNumberPolicy.DOUBLE))
        .create();
    String json = gson.toJson(42, Object.class);
    assertThat(json).isEqualTo("42");
  }

  @Test
  public void testWriteBoolean() throws IOException {
    Gson gson = new GsonBuilder()
        .registerTypeAdapterFactory(ObjectTypeAdapter.getFactory(ToNumberPolicy.DOUBLE))
        .create();
    String json = gson.toJson(true, Object.class);
    assertThat(json).isEqualTo("true");
  }

  @Test
  public void testWriteObjectWithNoTypeAdapter() throws IOException {
    Gson gson = new GsonBuilder()
        .registerTypeAdapterFactory(ObjectTypeAdapter.getFactory(ToNumberPolicy.DOUBLE))
        .create();
    TypeAdapter<Object> adapter = gson.getAdapter(Object.class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, new Object());
    assertThat(stringWriter.toString()).isEqualTo("{}");
  }

  @Test
  public void testReadMultipleLevelsOfNesting() throws IOException {
    Gson gson = new GsonBuilder()
        .registerTypeAdapterFactory(ObjectTypeAdapter.getFactory(ToNumberPolicy.DOUBLE))
        .create();
    String json = "[[[1]]]";
    Object result = gson.fromJson(json, Object.class);
    assertThat(result).isInstanceOf(List.class);
    List<?> level1 = (List<?>) result;
    assertThat(level1.size()).isEqualTo(1);
    assertThat(level1.get(0)).isInstanceOf(List.class);
    List<?> level2 = (List<?>) level1.get(0);
    assertThat(level2.size()).isEqualTo(1);
    assertThat(level2.get(0)).isInstanceOf(List.class);
    List<?> level3 = (List<?>) level2.get(0);
    assertThat(level3.size()).isEqualTo(1);
    assertThat(level3.get(0)).isEqualTo(1.0);
  }

  @Test
  public void testReadObjectWithMultipleNestingLevels() throws IOException {
    Gson gson = new GsonBuilder()
        .registerTypeAdapterFactory(ObjectTypeAdapter.getFactory(ToNumberPolicy.DOUBLE))
        .create();
    String json = "{\"a\": {\"b\": {\"c\": \"value\"}}}";
    Object result = gson.fromJson(json, Object.class);
    assertThat(result).isInstanceOf(Map.class);
    Map<?, ?> level1 = (Map<?, ?>) result;
    assertThat(level1.get("a")).isInstanceOf(Map.class);
    Map<?, ?> level2 = (Map<?, ?>) level1.get("a");
    assertThat(level2.get("b")).isInstanceOf(Map.class);
    Map<?, ?> level3 = (Map<?, ?>) level2.get("b");
    assertThat(level3.get("c")).isEqualTo("value");
  }

  @Test
  public void testReadArrayWithMixedNesting() throws IOException {
    Gson gson = new GsonBuilder()
        .registerTypeAdapterFactory(ObjectTypeAdapter.getFactory(ToNumberPolicy.DOUBLE))
        .create();
    String json = "[1, [2, [3, 4]], 5]";
    Object result = gson.fromJson(json, Object.class);
    assertThat(result).isInstanceOf(List.class);
    List<?> list = (List<?>) result;
    assertThat(list.size()).isEqualTo(3);
    assertThat(list.get(0)).isEqualTo(1.0);
    assertThat(list.get(1)).isInstanceOf(List.class);
    assertThat(list.get(2)).isEqualTo(5.0);
  }

  @Test
  public void testReadObjectWithNullValues() throws IOException {
    Gson gson = new GsonBuilder()
        .registerTypeAdapterFactory(ObjectTypeAdapter.getFactory(ToNumberPolicy.DOUBLE))
        .create();
    String json = "{\"key1\": null, \"key2\": \"value\"}";
    Object result = gson.fromJson(json, Object.class);
    assertThat(result).isInstanceOf(Map.class);
    Map<?, ?> map = (Map<?, ?>) result;
    assertThat(map.size()).isEqualTo(2);
    assertThat(map.get("key1")).isNull();
    assertThat(map.get("key2")).isEqualTo("value");
  }

  @Test
  public void testReadArrayWithAllTerminalTypes() throws IOException {
    Gson gson = new GsonBuilder()
        .registerTypeAdapterFactory(ObjectTypeAdapter.getFactory(ToNumberPolicy.DOUBLE))
        .create();
    String json = "[\"string\", 123, true, false, null]";
    Object result = gson.fromJson(json, Object.class);
    assertThat(result).isInstanceOf(List.class);
    List<?> list = (List<?>) result;
    assertThat(list.size()).isEqualTo(5);
    assertThat(list.get(0)).isEqualTo("string");
    assertThat(list.get(1)).isEqualTo(123.0);
    assertThat(list.get(2)).isEqualTo(true);
    assertThat(list.get(3)).isEqualTo(false);
    assertThat(list.get(4)).isNull();
  }
}
