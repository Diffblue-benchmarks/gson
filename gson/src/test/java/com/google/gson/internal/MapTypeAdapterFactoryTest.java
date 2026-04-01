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

package com.google.gson.internal;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public final class MapTypeAdapterFactoryTest {

  private Gson gson;
  private Gson gsonComplexKeys;

  @Before
  public void setUp() {
    gson = new Gson();
    gsonComplexKeys = new GsonBuilder().enableComplexMapKeySerialization().create();
  }

  @Test
  public void testReadNullMap() {
    Map<String, String> result = gson.fromJson("null", new TypeToken<Map<String, String>>() {}.getType());
    assertThat(result).isNull();
  }

  @Test
  public void testReadSimpleStringMap() {
    Map<String, String> result = gson.fromJson(
        "{\"key1\":\"value1\",\"key2\":\"value2\"}", new TypeToken<Map<String, String>>() {}.getType());
    assertThat(result).hasSize(2);
    assertThat(result.get("key1")).isEqualTo("value1");
    assertThat(result.get("key2")).isEqualTo("value2");
  }

  @Test
  public void testReadMapWithIntegerKeys() {
    Map<Integer, String> result = gson.fromJson(
        "{\"1\":\"one\",\"2\":\"two\"}", new TypeToken<Map<Integer, String>>() {}.getType());
    assertThat(result.get(1)).isEqualTo("one");
    assertThat(result.get(2)).isEqualTo("two");
  }

  @Test
  public void testReadArrayMapFormat() {
    Map<String, String> result = gsonComplexKeys.fromJson(
        "[[\"key1\",\"value1\"],[\"key2\",\"value2\"]]",
        new TypeToken<Map<String, String>>() {}.getType());
    assertThat(result.get("key1")).isEqualTo("value1");
    assertThat(result.get("key2")).isEqualTo("value2");
  }

  @Test
  public void testReadDuplicateKeyThrows() {
    assertThrows(JsonSyntaxException.class, () ->
        gson.fromJson("{\"key\":\"v1\",\"key\":\"v2\"}", new TypeToken<Map<String, String>>() {}.getType()));
  }

  @Test
  public void testReadDuplicateKeyInArrayFormatThrows() {
    assertThrows(JsonSyntaxException.class, () ->
        gsonComplexKeys.fromJson(
            "[[\"key\",\"v1\"],[\"key\",\"v2\"]]",
            new TypeToken<Map<String, String>>() {}.getType()));
  }

  @Test
  public void testWriteNullMap() {
    String json = gson.toJson(null, new TypeToken<Map<String, String>>() {}.getType());
    assertThat(json).isEqualTo("null");
  }

  @Test
  public void testWriteSimpleStringMap() {
    Map<String, String> map = new LinkedHashMap<>();
    map.put("a", "1");
    map.put("b", "2");
    String json = gson.toJson(map, new TypeToken<Map<String, String>>() {}.getType());
    assertThat(json).isEqualTo("{\"a\":\"1\",\"b\":\"2\"}");
  }

  @Test
  public void testWriteMapWithIntegerKeys() {
    Map<Integer, String> map = new LinkedHashMap<>();
    map.put(1, "one");
    map.put(2, "two");
    String json = gson.toJson(map, new TypeToken<Map<Integer, String>>() {}.getType());
    assertThat(json).isEqualTo("{\"1\":\"one\",\"2\":\"two\"}");
  }

  @Test
  public void testWriteMapWithBooleanKeys() {
    Map<Boolean, String> map = new LinkedHashMap<>();
    map.put(true, "yes");
    map.put(false, "no");
    String json = gson.toJson(map, new TypeToken<Map<Boolean, String>>() {}.getType());
    assertThat(json).isEqualTo("{\"true\":\"yes\",\"false\":\"no\"}");
  }

  @Test
  public void testWriteComplexKeysAsArray() {
    Map<Point, String> map = new LinkedHashMap<>();
    map.put(new Point(1, 2), "a");
    map.put(new Point(3, 4), "b");
    String json = gsonComplexKeys.toJson(map, new TypeToken<Map<Point, String>>() {}.getType());
    assertThat(json).contains("\"a\"");
    assertThat(json).contains("\"b\"");
    assertThat(json).startsWith("[[");
  }

  @Test
  public void testWriteComplexKeysAsPrimitiveStrings() {
    Map<String, Integer> map = new LinkedHashMap<>();
    map.put("x", 10);
    map.put("y", 20);
    String json = gsonComplexKeys.toJson(map, new TypeToken<Map<String, Integer>>() {}.getType());
    assertThat(json).isEqualTo("{\"x\":10,\"y\":20}");
  }

  @Test
  public void testRoundTripStringMap() {
    Map<String, Integer> original = new LinkedHashMap<>();
    original.put("one", 1);
    original.put("two", 2);
    String json = gson.toJson(original, new TypeToken<Map<String, Integer>>() {}.getType());
    Map<String, Integer> result = gson.fromJson(json, new TypeToken<Map<String, Integer>>() {}.getType());
    assertThat(result).isEqualTo(original);
  }

  @Test
  public void testRoundTripComplexKeyMap() {
    Map<Point, String> original = new LinkedHashMap<>();
    original.put(new Point(5, 6), "a");
    original.put(new Point(8, 8), "b");
    String json = gsonComplexKeys.toJson(original, new TypeToken<Map<Point, String>>() {}.getType());
    Map<Point, String> result = gsonComplexKeys.fromJson(json, new TypeToken<Map<Point, String>>() {}.getType());
    assertThat(result).hasSize(2);
  }

  private static final class Point {
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
      return 31 * x + y;
    }
  }
}
