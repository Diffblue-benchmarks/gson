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

import java.util.Map;
import java.util.Set;
import org.junit.Test;

public class JsonObjectTest {

  @Test
  public void testEmptyObject() {
    JsonObject jsonObject = new JsonObject();
    assertThat(jsonObject.size()).isEqualTo(0);
    assertThat(jsonObject.isEmpty()).isTrue();
  }

  @Test
  public void testAddProperty_string() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("key", "value");
    assertThat(jsonObject.size()).isEqualTo(1);
    assertThat(jsonObject.get("key").getAsString()).isEqualTo("value");
  }

  @Test
  public void testAddProperty_string_null() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("key", (String) null);
    assertThat(jsonObject.size()).isEqualTo(1);
    assertThat(jsonObject.get("key").isJsonNull()).isTrue();
  }

  @Test
  public void testAddProperty_number() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("key", 123);
    assertThat(jsonObject.size()).isEqualTo(1);
    assertThat(jsonObject.get("key").getAsInt()).isEqualTo(123);
  }

  @Test
  public void testAddProperty_number_null() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("key", (Number) null);
    assertThat(jsonObject.size()).isEqualTo(1);
    assertThat(jsonObject.get("key").isJsonNull()).isTrue();
  }

  @Test
  public void testAddProperty_boolean() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("key", true);
    assertThat(jsonObject.size()).isEqualTo(1);
    assertThat(jsonObject.get("key").getAsBoolean()).isTrue();
  }

  @Test
  public void testAddProperty_boolean_null() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("key", (Boolean) null);
    assertThat(jsonObject.size()).isEqualTo(1);
    assertThat(jsonObject.get("key").isJsonNull()).isTrue();
  }

  @Test
  public void testAddProperty_character() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("key", 'a');
    assertThat(jsonObject.size()).isEqualTo(1);
    assertThat(jsonObject.get("key").getAsString()).isEqualTo("a");
  }

  @Test
  public void testAddProperty_character_null() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("key", (Character) null);
    assertThat(jsonObject.size()).isEqualTo(1);
    assertThat(jsonObject.get("key").isJsonNull()).isTrue();
  }

  @Test
  public void testAdd() {
    JsonObject jsonObject = new JsonObject();
    JsonPrimitive primitive = new JsonPrimitive(123);
    jsonObject.add("key", primitive);
    assertThat(jsonObject.size()).isEqualTo(1);
    assertThat(jsonObject.get("key")).isSameInstanceAs(primitive);
  }

  @Test
  public void testAdd_null() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("key", null);
    assertThat(jsonObject.size()).isEqualTo(1);
    assertThat(jsonObject.get("key").isJsonNull()).isTrue();
  }

  @Test
  public void testAdd_overwrite() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("key", "value1");
    jsonObject.addProperty("key", "value2");
    assertThat(jsonObject.size()).isEqualTo(1);
    assertThat(jsonObject.get("key").getAsString()).isEqualTo("value2");
  }

  @Test
  public void testRemove() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("key1", "value1");
    jsonObject.addProperty("key2", "value2");

    JsonElement removed = jsonObject.remove("key1");
    assertThat(removed.getAsString()).isEqualTo("value1");
    assertThat(jsonObject.size()).isEqualTo(1);
    assertThat(jsonObject.has("key1")).isFalse();
  }

  @Test
  public void testRemove_nonExistent() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("key", "value");
    JsonElement removed = jsonObject.remove("nonExistent");
    assertThat(removed).isNull();
    assertThat(jsonObject.size()).isEqualTo(1);
  }

  @Test
  public void testHas() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("key", "value");
    assertThat(jsonObject.has("key")).isTrue();
    assertThat(jsonObject.has("nonExistent")).isFalse();
  }

  @Test
  public void testGet() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("key", "value");
    assertThat(jsonObject.get("key").getAsString()).isEqualTo("value");
  }

  @Test
  public void testGet_nonExistent() {
    JsonObject jsonObject = new JsonObject();
    assertThat(jsonObject.get("nonExistent")).isNull();
  }

  @Test
  public void testGetAsJsonPrimitive() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("key", "value");
    JsonPrimitive primitive = jsonObject.getAsJsonPrimitive("key");
    assertThat(primitive.getAsString()).isEqualTo("value");
  }

  @Test
  public void testGetAsJsonPrimitive_nonExistent() {
    JsonObject jsonObject = new JsonObject();
    assertThat(jsonObject.getAsJsonPrimitive("nonExistent")).isNull();
  }

  @Test
  public void testGetAsJsonArray() {
    JsonObject jsonObject = new JsonObject();
    JsonArray array = new JsonArray();
    jsonObject.add("key", array);
    assertThat(jsonObject.getAsJsonArray("key")).isSameInstanceAs(array);
  }

  @Test
  public void testGetAsJsonArray_nonExistent() {
    JsonObject jsonObject = new JsonObject();
    assertThat(jsonObject.getAsJsonArray("nonExistent")).isNull();
  }

  @Test
  public void testGetAsJsonObject() {
    JsonObject jsonObject = new JsonObject();
    JsonObject nested = new JsonObject();
    jsonObject.add("key", nested);
    assertThat(jsonObject.getAsJsonObject("key")).isSameInstanceAs(nested);
  }

  @Test
  public void testGetAsJsonObject_nonExistent() {
    JsonObject jsonObject = new JsonObject();
    assertThat(jsonObject.getAsJsonObject("nonExistent")).isNull();
  }

  @Test
  public void testEntrySet() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("key1", "value1");
    jsonObject.addProperty("key2", "value2");

    Set<Map.Entry<String, JsonElement>> entrySet = jsonObject.entrySet();
    assertThat(entrySet.size()).isEqualTo(2);
  }

  @Test
  public void testEntrySet_maintainsOrder() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("key1", "value1");
    jsonObject.addProperty("key2", "value2");
    jsonObject.addProperty("key3", "value3");

    Set<Map.Entry<String, JsonElement>> entrySet = jsonObject.entrySet();
    String[] keys = entrySet.stream().map(Map.Entry::getKey).toArray(String[]::new);
    assertThat(keys).asList().containsExactly("key1", "key2", "key3").inOrder();
  }

  @Test
  public void testKeySet() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("key1", "value1");
    jsonObject.addProperty("key2", "value2");

    Set<String> keySet = jsonObject.keySet();
    assertThat(keySet).containsExactly("key1", "key2");
  }

  @Test
  public void testAsMap() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("key1", "value1");
    jsonObject.addProperty("key2", "value2");

    Map<String, JsonElement> map = jsonObject.asMap();
    assertThat(map.size()).isEqualTo(2);
    assertThat(map.get("key1").getAsString()).isEqualTo("value1");
  }

  @Test
  public void testAsMap_modificationsReflected() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("key1", "value1");

    Map<String, JsonElement> map = jsonObject.asMap();
    map.put("key2", new JsonPrimitive("value2"));

    assertThat(jsonObject.size()).isEqualTo(2);
    assertThat(jsonObject.get("key2").getAsString()).isEqualTo("value2");
  }

  @Test
  public void testDeepCopy_empty() {
    JsonObject original = new JsonObject();
    JsonObject copy = original.deepCopy();
    assertThat(copy).isNotSameInstanceAs(original);
    assertThat(copy.size()).isEqualTo(0);
  }

  @Test
  public void testDeepCopy_withProperties() {
    JsonObject original = new JsonObject();
    original.addProperty("key1", "value1");
    original.addProperty("key2", 123);

    JsonObject nested = new JsonObject();
    nested.addProperty("nestedKey", "nestedValue");
    original.add("nested", nested);

    JsonObject copy = original.deepCopy();

    assertThat(copy).isNotSameInstanceAs(original);
    assertThat(copy.size()).isEqualTo(3);
    assertThat(copy.get("key1").getAsString()).isEqualTo("value1");
    assertThat(copy.get("key2").getAsInt()).isEqualTo(123);

    // Verify deep copy
    JsonObject copiedNested = copy.getAsJsonObject("nested");
    assertThat(copiedNested).isNotSameInstanceAs(nested);
    assertThat(copiedNested.get("nestedKey").getAsString()).isEqualTo("nestedValue");

    // Modifying copy should not affect original
    copiedNested.addProperty("newKey", "newValue");
    assertThat(nested.has("newKey")).isFalse();
  }

  @Test
  public void testIsJsonObject() {
    JsonObject jsonObject = new JsonObject();
    assertThat(jsonObject.isJsonObject()).isTrue();
    assertThat(jsonObject.isJsonArray()).isFalse();
    assertThat(jsonObject.isJsonPrimitive()).isFalse();
    assertThat(jsonObject.isJsonNull()).isFalse();
  }

  @Test
  public void testEquals_sameInstance() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("key", "value");
    assertThat(jsonObject.equals(jsonObject)).isTrue();
  }

  @Test
  public void testEquals_equalObjects() {
    JsonObject obj1 = new JsonObject();
    obj1.addProperty("key1", "value1");
    obj1.addProperty("key2", 123);

    JsonObject obj2 = new JsonObject();
    obj2.addProperty("key1", "value1");
    obj2.addProperty("key2", 123);

    assertThat(obj1.equals(obj2)).isTrue();
    assertThat(obj1.hashCode()).isEqualTo(obj2.hashCode());
  }

  @Test
  public void testEquals_differentOrder() {
    JsonObject obj1 = new JsonObject();
    obj1.addProperty("key1", "value1");
    obj1.addProperty("key2", "value2");

    JsonObject obj2 = new JsonObject();
    obj2.addProperty("key2", "value2");
    obj2.addProperty("key1", "value1");

    // Equals should ignore order
    assertThat(obj1.equals(obj2)).isTrue();
  }

  @Test
  public void testEquals_differentSize() {
    JsonObject obj1 = new JsonObject();
    obj1.addProperty("key1", "value1");

    JsonObject obj2 = new JsonObject();
    obj2.addProperty("key1", "value1");
    obj2.addProperty("key2", "value2");

    assertThat(obj1.equals(obj2)).isFalse();
  }

  @Test
  public void testEquals_differentValues() {
    JsonObject obj1 = new JsonObject();
    obj1.addProperty("key", "value1");

    JsonObject obj2 = new JsonObject();
    obj2.addProperty("key", "value2");

    assertThat(obj1.equals(obj2)).isFalse();
  }

  @Test
  public void testEquals_null() {
    JsonObject jsonObject = new JsonObject();
    assertThat(jsonObject.equals(null)).isFalse();
  }

  @Test
  @SuppressWarnings("EqualsIncompatibleType")
  public void testEquals_differentType() {
    JsonObject jsonObject = new JsonObject();
    assertThat(jsonObject.equals(new JsonArray())).isFalse();
  }

  @Test
  public void testToString_empty() {
    JsonObject jsonObject = new JsonObject();
    assertThat(jsonObject.toString()).isEqualTo("{}");
  }

  @Test
  public void testToString_withProperties() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("key1", "value1");
    jsonObject.addProperty("key2", 123);
    jsonObject.addProperty("key3", true);
    assertThat(jsonObject.toString()).isEqualTo("{\"key1\":\"value1\",\"key2\":123,\"key3\":true}");
  }

  @Test
  public void testToString_nested() {
    JsonObject inner = new JsonObject();
    inner.addProperty("innerKey", "innerValue");

    JsonObject outer = new JsonObject();
    outer.add("nested", inner);

    assertThat(outer.toString()).isEqualTo("{\"nested\":{\"innerKey\":\"innerValue\"}}");
  }
}
