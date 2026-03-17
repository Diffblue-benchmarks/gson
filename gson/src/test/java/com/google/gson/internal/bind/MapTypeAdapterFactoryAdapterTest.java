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
import static org.junit.Assert.fail;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Test;

public class MapTypeAdapterFactoryAdapterTest {

  @Test
  public void testReadNull() throws IOException {
    Gson gson = new Gson();
    String json = "null";

    Map<String, String> result = gson.fromJson(json, new TypeToken<Map<String, String>>() {}.getType());

    assertThat(result).isNull();
  }

  @Test
  public void testReadObjectFormat() throws IOException {
    Gson gson = new Gson();
    String json = "{\"key1\":\"value1\",\"key2\":\"value2\"}";

    Map<String, String> result = gson.fromJson(json, new TypeToken<Map<String, String>>() {}.getType());

    assertThat(result).isNotNull();
    assertThat(result).hasSize(2);
    assertThat(result.get("key1")).isEqualTo("value1");
    assertThat(result.get("key2")).isEqualTo("value2");
  }

  @Test
  public void testReadArrayFormat() throws IOException {
    Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
    String json = "[[\"key1\",\"value1\"],[\"key2\",\"value2\"]]";

    Map<String, String> result = gson.fromJson(json, new TypeToken<Map<String, String>>() {}.getType());

    assertThat(result).isNotNull();
    assertThat(result).hasSize(2);
    assertThat(result.get("key1")).isEqualTo("value1");
    assertThat(result.get("key2")).isEqualTo("value2");
  }

  @Test
  public void testReadDuplicateKeyInObject() {
    Gson gson = new Gson();
    String json = "{\"key1\":\"value1\",\"key1\":\"value2\"}";

    try {
      gson.fromJson(json, new TypeToken<Map<String, String>>() {}.getType());
      fail("Expected JsonSyntaxException for duplicate key");
    } catch (JsonSyntaxException e) {
      assertThat(e.getMessage()).contains("duplicate key");
    }
  }

  @Test
  public void testReadDuplicateKeyInArray() {
    Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
    String json = "[[\"key1\",\"value1\"],[\"key1\",\"value2\"]]";

    try {
      gson.fromJson(json, new TypeToken<Map<String, String>>() {}.getType());
      fail("Expected JsonSyntaxException for duplicate key");
    } catch (JsonSyntaxException e) {
      assertThat(e.getMessage()).contains("duplicate key");
    }
  }

  @Test
  public void testWriteNull() throws IOException {
    Gson gson = new Gson();
    Map<String, String> map = null;

    String json = gson.toJson(map, new TypeToken<Map<String, String>>() {}.getType());

    assertThat(json).isEqualTo("null");
  }

  @Test
  public void testWriteSimpleMap() throws IOException {
    Gson gson = new Gson();
    Map<String, String> map = new LinkedHashMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");

    String json = gson.toJson(map, new TypeToken<Map<String, String>>() {}.getType());

    assertThat(json).isEqualTo("{\"key1\":\"value1\",\"key2\":\"value2\"}");
  }

  @Test
  public void testWriteSimpleMapWithComplexKeySerializationDisabled() throws IOException {
    Gson gson = new Gson();
    Map<Integer, String> map = new LinkedHashMap<>();
    map.put(1, "value1");
    map.put(2, "value2");

    String json = gson.toJson(map, new TypeToken<Map<Integer, String>>() {}.getType());

    assertThat(json).isEqualTo("{\"1\":\"value1\",\"2\":\"value2\"}");
  }

  @Test
  public void testWriteSimpleMapWithComplexKeySerializationEnabled() throws IOException {
    Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
    Map<String, String> map = new LinkedHashMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");

    String json = gson.toJson(map, new TypeToken<Map<String, String>>() {}.getType());

    // With complex key serialization but simple string keys, should still use object format
    assertThat(json).isEqualTo("{\"key1\":\"value1\",\"key2\":\"value2\"}");
  }

  static class Point {
    int x;
    int y;

    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object o) {
      if (!(o instanceof Point)) return false;
      Point p = (Point) o;
      return x == p.x && y == p.y;
    }

    @Override
    public int hashCode() {
      return x * 31 + y;
    }
  }

  @Test
  public void testWriteComplexKeys() throws IOException {
    Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
    Map<Point, String> map = new LinkedHashMap<>();
    map.put(new Point(1, 2), "value1");
    map.put(new Point(3, 4), "value2");

    String json = gson.toJson(map, new TypeToken<Map<Point, String>>() {}.getType());

    // Complex keys should be serialized as array format
    assertThat(json).contains("[[");
    assertThat(json).contains("value1");
    assertThat(json).contains("value2");
  }

  @Test
  public void testWriteWithNumberKey() throws IOException {
    Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
    Map<Integer, String> map = new LinkedHashMap<>();
    map.put(42, "value1");
    map.put(100, "value2");

    String json = gson.toJson(map, new TypeToken<Map<Integer, String>>() {}.getType());

    // Number keys should use object format
    assertThat(json).isEqualTo("{\"42\":\"value1\",\"100\":\"value2\"}");
  }

  @Test
  public void testWriteWithBooleanKey() throws IOException {
    Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
    Map<Boolean, String> map = new LinkedHashMap<>();
    map.put(true, "value1");
    map.put(false, "value2");

    String json = gson.toJson(map, new TypeToken<Map<Boolean, String>>() {}.getType());

    // Boolean keys should use object format
    assertThat(json).isEqualTo("{\"true\":\"value1\",\"false\":\"value2\"}");
  }

  @Test
  public void testWriteWithNullKey() throws IOException {
    Gson gson = new GsonBuilder()
        .enableComplexMapKeySerialization()
        .serializeNulls()
        .create();
    Map<String, String> map = new HashMap<>();
    map.put(null, "value1");
    map.put("key2", "value2");

    String json = gson.toJson(map, new TypeToken<Map<String, String>>() {}.getType());

    // Null key should be serialized as "null"
    assertThat(json).contains("\"null\"");
    assertThat(json).contains("value1");
  }

  @Test
  public void testReadAndWriteRoundTrip() throws IOException {
    Gson gson = new Gson();
    Map<String, Integer> original = new LinkedHashMap<>();
    original.put("a", 1);
    original.put("b", 2);
    original.put("c", 3);

    String json = gson.toJson(original, new TypeToken<Map<String, Integer>>() {}.getType());
    Map<String, Integer> result = gson.fromJson(json, new TypeToken<Map<String, Integer>>() {}.getType());

    assertThat(result).isEqualTo(original);
  }

  @Test
  public void testReadAndWriteRoundTripWithComplexKeys() throws IOException {
    Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
    Map<Point, String> original = new LinkedHashMap<>();
    original.put(new Point(1, 2), "a");
    original.put(new Point(3, 4), "b");

    String json = gson.toJson(original, new TypeToken<Map<Point, String>>() {}.getType());
    Map<Point, String> result = gson.fromJson(json, new TypeToken<Map<Point, String>>() {}.getType());

    assertThat(result).hasSize(2);
    assertThat(result.get(new Point(1, 2))).isEqualTo("a");
    assertThat(result.get(new Point(3, 4))).isEqualTo("b");
  }

  @Test
  public void testWriteEmptyMap() throws IOException {
    Gson gson = new Gson();
    Map<String, String> map = new HashMap<>();

    String json = gson.toJson(map, new TypeToken<Map<String, String>>() {}.getType());

    assertThat(json).isEqualTo("{}");
  }

  @Test
  public void testReadEmptyObject() throws IOException {
    Gson gson = new Gson();
    String json = "{}";

    Map<String, String> result = gson.fromJson(json, new TypeToken<Map<String, String>>() {}.getType());

    assertThat(result).isNotNull();
    assertThat(result).isEmpty();
  }

  @Test
  public void testReadEmptyArray() throws IOException {
    Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
    String json = "[]";

    Map<String, String> result = gson.fromJson(json, new TypeToken<Map<String, String>>() {}.getType());

    assertThat(result).isNotNull();
    assertThat(result).isEmpty();
  }
}
