/*
 * Copyright (C) 2010 Google Inc.
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

package com.google.gson.functional;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Ignore;
import org.junit.Test;

public class MapAsArrayTypeAdapterTest {

  @Test
  public void testSerializeComplexMapWithTypeAdapter() {
    Type type = new TypeToken<Map<Point, String>>() {}.getType();
    Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();

    Map<Point, String> original = new LinkedHashMap<>();
    original.put(new Point(5, 5), "a");
    original.put(new Point(8, 8), "b");
    String json = gson.toJson(original, type);
    assertThat(json).isEqualTo("[[{\"x\":5,\"y\":5},\"a\"],[{\"x\":8,\"y\":8},\"b\"]]");
    assertThat(gson.<Map<Point, String>>fromJson(json, type)).isEqualTo(original);

    // test that registering a type adapter for one map doesn't interfere with others
    Map<String, Boolean> otherMap = new LinkedHashMap<>();
    otherMap.put("t", true);
    otherMap.put("f", false);
    assertThat(gson.toJson(otherMap, Map.class)).isEqualTo("{\"t\":true,\"f\":false}");
    assertThat(gson.toJson(otherMap, new TypeToken<Map<String, Boolean>>() {}.getType()))
        .isEqualTo("{\"t\":true,\"f\":false}");
    assertThat(
            gson.<Object>fromJson(
                "{\"t\":true,\"f\":false}", new TypeToken<Map<String, Boolean>>() {}.getType()))
        .isEqualTo(otherMap);
  }

  @Test
  public void testTwoTypesCollapseToOneDeserialize() {
    Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();

    String s = "[[\"1.00\",\"a\"],[\"1.0\",\"b\"]]";
    Type type = new TypeToken<Map<Double, String>>() {}.getType();
    var e = assertThrows(JsonSyntaxException.class, () -> gson.fromJson(s, type));
    assertThat(e).hasMessageThat().isEqualTo("duplicate key: 1.0");
  }

  @Test
  public void testMultipleEnableComplexKeyRegistrationHasNoEffect() {
    Type type = new TypeToken<Map<Point, String>>() {}.getType();
    Gson gson =
        new GsonBuilder()
            .enableComplexMapKeySerialization()
            .enableComplexMapKeySerialization()
            .create();

    Map<Point, String> original = new LinkedHashMap<>();
    original.put(new Point(6, 5), "abc");
    original.put(new Point(1, 8), "def");
    String json = gson.toJson(original, type);
    assertThat(json).isEqualTo("[[{\"x\":6,\"y\":5},\"abc\"],[{\"x\":1,\"y\":8},\"def\"]]");
    assertThat(gson.<Map<Point, String>>fromJson(json, type)).isEqualTo(original);
  }

  @Test
  public void testMapWithTypeVariableSerialization() {
    Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
    PointWithProperty<Point> map = new PointWithProperty<>();
    map.map.put(new Point(2, 3), new Point(4, 5));
    Type type = new TypeToken<PointWithProperty<Point>>() {}.getType();
    String json = gson.toJson(map, type);
    assertThat(json).isEqualTo("{\"map\":[[{\"x\":2,\"y\":3},{\"x\":4,\"y\":5}]]}");
  }

  @Test
  public void testMapWithTypeVariableDeserialization() {
    Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
    String json = "{map:[[{x:2,y:3},{x:4,y:5}]]}";
    Type type = new TypeToken<PointWithProperty<Point>>() {}.getType();
    PointWithProperty<Point> map = gson.fromJson(json, type);
    Point key = map.map.keySet().iterator().next();
    Point value = map.map.values().iterator().next();
    assertThat(key).isEqualTo(new Point(2, 3));
    assertThat(value).isEqualTo(new Point(4, 5));
  }

  static class Point {
    int x;
    int y;

    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    Point() {}

    @Override
    public boolean equals(Object o) {
      return o instanceof Point && ((Point) o).x == x && ((Point) o).y == y;
    }

    @Override
    public int hashCode() {
      return x * 37 + y;
    }

    @Override
    public String toString() {
      return "(" + x + "," + y + ")";
    }
  }

  static class PointWithProperty<T> {
    Map<Point, T> map = new HashMap<>();
  }

  @Test
  public void testSerializeMapWithIntegerKeyAndComplexKeySerialization() {
    Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
    Map<Integer, String> map = new LinkedHashMap<>();
    map.put(123, "a");
    map.put(456, "b");
    Type type = new TypeToken<Map<Integer, String>>() {}.getType();
    String json = gson.toJson(map, type);
    assertThat(json).isEqualTo("{\"123\":\"a\",\"456\":\"b\"}");
  }

  @Test
  public void testSerializeMapWithDoubleKeyAndComplexKeySerialization() {
    Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
    Map<Double, String> map = new LinkedHashMap<>();
    map.put(1.5, "a");
    map.put(2.75, "b");
    Type type = new TypeToken<Map<Double, String>>() {}.getType();
    String json = gson.toJson(map, type);
    assertThat(json).isEqualTo("{\"1.5\":\"a\",\"2.75\":\"b\"}");
  }

  @Test
  public void testSerializeMapWithBooleanKeyAndComplexKeySerialization() {
    Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
    Map<Boolean, String> map = new LinkedHashMap<>();
    map.put(true, "a");
    map.put(false, "b");
    Type type = new TypeToken<Map<Boolean, String>>() {}.getType();
    String json = gson.toJson(map, type);
    assertThat(json).isEqualTo("{\"true\":\"a\",\"false\":\"b\"}");
  }

  @Test
  public void testSerializeMapWithNullKeyAndComplexKeySerialization() {
    Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
    Map<String, String> map = new LinkedHashMap<>();
    map.put(null, "a");
    map.put("key", "b");
    Type type = new TypeToken<Map<String, String>>() {}.getType();
    String json = gson.toJson(map, type);
    assertThat(json).isEqualTo("{\"null\":\"a\",\"key\":\"b\"}");
  }

  @Test
  public void testSerializeMapWithCustomBooleanKeyAdapter() {
    // Custom adapter that converts BooleanKey to a boolean JsonPrimitive
    // The write() method outputs a boolean, so toJsonTree() returns a boolean JsonPrimitive
    TypeAdapter<BooleanKey> booleanKeyAdapter =
        new TypeAdapter<BooleanKey>() {
          @Override
          public void write(JsonWriter out, BooleanKey value) throws IOException {
            out.value(value.value);
          }

          @Override
          public BooleanKey read(JsonReader in) throws IOException {
            return new BooleanKey(in.nextBoolean());
          }
        };

    Gson gson =
        new GsonBuilder()
            .enableComplexMapKeySerialization()
            .registerTypeAdapter(BooleanKey.class, booleanKeyAdapter)
            .create();

    Map<BooleanKey, String> map = new LinkedHashMap<>();
    map.put(new BooleanKey(true), "yes");
    map.put(new BooleanKey(false), "no");
    Type type = new TypeToken<Map<BooleanKey, String>>() {}.getType();
    String json = gson.toJson(map, type);
    assertThat(json).isEqualTo("{\"true\":\"yes\",\"false\":\"no\"}");
  }

  static class BooleanKey {
    final boolean value;

    BooleanKey(boolean value) {
      this.value = value;
    }

    @Override
    public boolean equals(Object o) {
      return o instanceof BooleanKey && ((BooleanKey) o).value == value;
    }

    @Override
    public int hashCode() {
      return Boolean.hashCode(value);
    }
  }
}
