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
import static org.junit.Assert.assertThrows;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;
import java.util.Set;
import org.junit.Test;

/**
 * Unit tests for {@link JsonObject}.
 *
 * @author Claude
 */
public class JsonObjectClaudeTest {

  // ========== Constructor Tests ==========

  @Test
  public void testDefaultConstructorCreatesEmptyObject() {
    JsonObject obj = new JsonObject();
    assertThat(obj).isNotNull();
    assertThat(obj.size()).isEqualTo(0);
    assertThat(obj.isEmpty()).isTrue();
  }

  // ========== deepCopy Tests ==========

  @Test
  public void testDeepCopyEmptyObject() {
    JsonObject original = new JsonObject();
    JsonObject copy = original.deepCopy();
    assertThat(copy).isNotNull();
    assertThat(copy.size()).isEqualTo(0);
    assertThat(copy).isNotSameInstanceAs(original);
  }

  @Test
  public void testDeepCopyWithPrimitives() {
    JsonObject original = new JsonObject();
    original.addProperty("string", "test");
    original.addProperty("number", 42);
    original.addProperty("boolean", true);

    JsonObject copy = original.deepCopy();

    assertThat(copy.size()).isEqualTo(3);
    assertThat(copy.get("string").getAsString()).isEqualTo("test");
    assertThat(copy.get("number").getAsInt()).isEqualTo(42);
    assertThat(copy.get("boolean").getAsBoolean()).isTrue();
    assertThat(copy).isNotSameInstanceAs(original);
  }

  @Test
  public void testDeepCopyWithNestedObject() {
    JsonObject original = new JsonObject();
    JsonObject nested = new JsonObject();
    nested.addProperty("nestedKey", "nestedValue");
    original.add("nested", nested);

    JsonObject copy = original.deepCopy();

    assertThat(copy.size()).isEqualTo(1);
    JsonObject nestedCopy = copy.getAsJsonObject("nested");
    assertThat(nestedCopy.get("nestedKey").getAsString()).isEqualTo("nestedValue");
    assertThat(nestedCopy).isNotSameInstanceAs(nested);
  }

  @Test
  public void testDeepCopyWithNestedArray() {
    JsonObject original = new JsonObject();
    JsonArray nested = new JsonArray();
    nested.add("arrayElement");
    original.add("array", nested);

    JsonObject copy = original.deepCopy();

    assertThat(copy.size()).isEqualTo(1);
    JsonArray arrayCopy = copy.getAsJsonArray("array");
    assertThat(arrayCopy.get(0).getAsString()).isEqualTo("arrayElement");
    assertThat(arrayCopy).isNotSameInstanceAs(nested);
  }

  @Test
  public void testDeepCopyIsIndependent() {
    JsonObject original = new JsonObject();
    original.addProperty("key", "original");

    JsonObject copy = original.deepCopy();
    copy.addProperty("newKey", "newValue");

    assertThat(original.size()).isEqualTo(1);
    assertThat(original.has("newKey")).isFalse();
    assertThat(copy.size()).isEqualTo(2);
  }

  @Test
  public void testDeepCopyNestedObjectIsIndependent() {
    JsonObject original = new JsonObject();
    JsonObject nested = new JsonObject();
    nested.addProperty("value", "original");
    original.add("nested", nested);

    JsonObject copy = original.deepCopy();
    copy.getAsJsonObject("nested").addProperty("value", "modified");

    assertThat(original.getAsJsonObject("nested").get("value").getAsString())
        .isEqualTo("original");
    assertThat(copy.getAsJsonObject("nested").get("value").getAsString()).isEqualTo("modified");
  }

  // ========== add Tests ==========

  @Test
  public void testAddJsonPrimitive() {
    JsonObject obj = new JsonObject();
    JsonPrimitive primitive = new JsonPrimitive("test");
    obj.add("key", primitive);
    assertThat(obj.size()).isEqualTo(1);
    assertThat(obj.get("key")).isSameInstanceAs(primitive);
  }

  @Test
  public void testAddJsonObject() {
    JsonObject obj = new JsonObject();
    JsonObject nested = new JsonObject();
    nested.addProperty("nested", "value");
    obj.add("object", nested);
    assertThat(obj.size()).isEqualTo(1);
    assertThat(obj.get("object")).isSameInstanceAs(nested);
  }

  @Test
  public void testAddJsonArray() {
    JsonObject obj = new JsonObject();
    JsonArray array = new JsonArray();
    array.add("element");
    obj.add("array", array);
    assertThat(obj.size()).isEqualTo(1);
    assertThat(obj.get("array")).isSameInstanceAs(array);
  }

  @Test
  public void testAddJsonNull() {
    JsonObject obj = new JsonObject();
    obj.add("nullValue", JsonNull.INSTANCE);
    assertThat(obj.size()).isEqualTo(1);
    assertThat(obj.get("nullValue").isJsonNull()).isTrue();
  }

  @Test
  public void testAddNullValueConvertsToJsonNull() {
    JsonObject obj = new JsonObject();
    obj.add("key", null);
    assertThat(obj.size()).isEqualTo(1);
    assertThat(obj.get("key")).isEqualTo(JsonNull.INSTANCE);
  }

  @Test
  public void testAddOverwritesExistingProperty() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", "first");
    obj.addProperty("key", "second");
    assertThat(obj.size()).isEqualTo(1);
    assertThat(obj.get("key").getAsString()).isEqualTo("second");
  }

  // ========== remove Tests ==========

  @Test
  public void testRemoveExistingProperty() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", "value");
    JsonElement removed = obj.remove("key");
    assertThat(removed.getAsString()).isEqualTo("value");
    assertThat(obj.size()).isEqualTo(0);
  }

  @Test
  public void testRemoveNonExistingProperty() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", "value");
    JsonElement removed = obj.remove("nonexistent");
    assertThat(removed).isNull();
    assertThat(obj.size()).isEqualTo(1);
  }

  @Test
  public void testRemoveFromEmptyObject() {
    JsonObject obj = new JsonObject();
    JsonElement removed = obj.remove("key");
    assertThat(removed).isNull();
  }

  @Test
  public void testRemoveReturnsCorrectElement() {
    JsonObject obj = new JsonObject();
    JsonArray array = new JsonArray();
    array.add("element");
    obj.add("array", array);
    JsonElement removed = obj.remove("array");
    assertThat(removed).isSameInstanceAs(array);
  }

  // ========== addProperty(String, String) Tests ==========

  @Test
  public void testAddPropertyString() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", "value");
    assertThat(obj.size()).isEqualTo(1);
    assertThat(obj.get("key").getAsString()).isEqualTo("value");
  }

  @Test
  public void testAddPropertyStringEmpty() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", "");
    assertThat(obj.get("key").getAsString()).isEmpty();
  }

  @Test
  public void testAddPropertyStringNull() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", (String) null);
    assertThat(obj.size()).isEqualTo(1);
    assertThat(obj.get("key").isJsonNull()).isTrue();
  }

  @Test
  public void testAddPropertyStringSpecialCharacters() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", "special\n\t\r\"characters");
    assertThat(obj.get("key").getAsString()).isEqualTo("special\n\t\r\"characters");
  }

  // ========== addProperty(String, Number) Tests ==========

  @Test
  public void testAddPropertyInteger() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", Integer.valueOf(42));
    assertThat(obj.get("key").getAsInt()).isEqualTo(42);
  }

  @Test
  public void testAddPropertyLong() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", Long.valueOf(123456789012345L));
    assertThat(obj.get("key").getAsLong()).isEqualTo(123456789012345L);
  }

  @Test
  public void testAddPropertyDouble() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", Double.valueOf(3.14159));
    assertThat(obj.get("key").getAsDouble()).isWithin(0.00001).of(3.14159);
  }

  @Test
  public void testAddPropertyFloat() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", Float.valueOf(2.5f));
    assertThat(obj.get("key").getAsFloat()).isWithin(0.001f).of(2.5f);
  }

  @Test
  public void testAddPropertyBigDecimal() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", new BigDecimal("123.456789"));
    assertThat(obj.get("key").getAsBigDecimal()).isEqualTo(new BigDecimal("123.456789"));
  }

  @Test
  public void testAddPropertyBigInteger() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", new BigInteger("12345678901234567890"));
    assertThat(obj.get("key").getAsBigInteger()).isEqualTo(new BigInteger("12345678901234567890"));
  }

  @Test
  public void testAddPropertyNumberNull() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", (Number) null);
    assertThat(obj.size()).isEqualTo(1);
    assertThat(obj.get("key").isJsonNull()).isTrue();
  }

  @Test
  public void testAddPropertyNegativeNumber() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", -42);
    assertThat(obj.get("key").getAsInt()).isEqualTo(-42);
  }

  @Test
  public void testAddPropertyZero() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", 0);
    assertThat(obj.get("key").getAsInt()).isEqualTo(0);
  }

  // ========== addProperty(String, Boolean) Tests ==========

  @Test
  public void testAddPropertyBooleanTrue() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", Boolean.TRUE);
    assertThat(obj.get("key").getAsBoolean()).isTrue();
  }

  @Test
  public void testAddPropertyBooleanFalse() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", Boolean.FALSE);
    assertThat(obj.get("key").getAsBoolean()).isFalse();
  }

  @Test
  public void testAddPropertyBooleanNull() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", (Boolean) null);
    assertThat(obj.size()).isEqualTo(1);
    assertThat(obj.get("key").isJsonNull()).isTrue();
  }

  // ========== addProperty(String, Character) Tests ==========

  @Test
  public void testAddPropertyCharacter() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", Character.valueOf('a'));
    assertThat(obj.get("key").getAsString()).isEqualTo("a");
  }

  @Test
  public void testAddPropertyCharacterSpecial() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", '\n');
    assertThat(obj.get("key").getAsString()).isEqualTo("\n");
  }

  @Test
  public void testAddPropertyCharacterNull() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", (Character) null);
    assertThat(obj.size()).isEqualTo(1);
    assertThat(obj.get("key").isJsonNull()).isTrue();
  }

  @Test
  public void testAddPropertyCharacterUnicode() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", '\u00E9');
    assertThat(obj.get("key").getAsString()).isEqualTo("\u00E9");
  }

  // ========== entrySet Tests ==========

  @Test
  public void testEntrySetEmpty() {
    JsonObject obj = new JsonObject();
    Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();
    assertThat(entries).isEmpty();
  }

  @Test
  public void testEntrySetNonEmpty() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key1", "value1");
    obj.addProperty("key2", "value2");
    Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();
    assertThat(entries).hasSize(2);
  }

  @Test
  public void testEntrySetPreservesInsertionOrder() {
    JsonObject obj = new JsonObject();
    obj.addProperty("a", 1);
    obj.addProperty("b", 2);
    obj.addProperty("c", 3);

    int index = 0;
    String[] expectedKeys = {"a", "b", "c"};
    for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
      assertThat(entry.getKey()).isEqualTo(expectedKeys[index++]);
    }
    assertThat(index).isEqualTo(3);
  }

  @Test
  public void testEntrySetReflectsMutations() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", "value");
    Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();
    assertThat(entries).hasSize(1);

    obj.addProperty("newKey", "newValue");
    assertThat(entries).hasSize(2);
  }

  // ========== keySet Tests ==========

  @Test
  public void testKeySetEmpty() {
    JsonObject obj = new JsonObject();
    Set<String> keys = obj.keySet();
    assertThat(keys).isEmpty();
  }

  @Test
  public void testKeySetNonEmpty() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key1", "value1");
    obj.addProperty("key2", "value2");
    Set<String> keys = obj.keySet();
    assertThat(keys).containsExactly("key1", "key2");
  }

  @Test
  public void testKeySetPreservesInsertionOrder() {
    JsonObject obj = new JsonObject();
    obj.addProperty("z", 1);
    obj.addProperty("a", 2);
    obj.addProperty("m", 3);

    int index = 0;
    String[] expectedKeys = {"z", "a", "m"};
    for (String key : obj.keySet()) {
      assertThat(key).isEqualTo(expectedKeys[index++]);
    }
    assertThat(index).isEqualTo(3);
  }

  @Test
  public void testKeySetReflectsMutations() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", "value");
    Set<String> keys = obj.keySet();
    assertThat(keys).hasSize(1);

    obj.addProperty("newKey", "newValue");
    assertThat(keys).hasSize(2);
    assertThat(keys).contains("newKey");
  }

  // ========== size Tests ==========

  @Test
  public void testSizeEmpty() {
    JsonObject obj = new JsonObject();
    assertThat(obj.size()).isEqualTo(0);
  }

  @Test
  public void testSizeAfterAdd() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key1", "value1");
    assertThat(obj.size()).isEqualTo(1);
    obj.addProperty("key2", "value2");
    assertThat(obj.size()).isEqualTo(2);
  }

  @Test
  public void testSizeAfterRemove() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key1", "value1");
    obj.addProperty("key2", "value2");
    obj.remove("key1");
    assertThat(obj.size()).isEqualTo(1);
  }

  @Test
  public void testSizeAfterOverwrite() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", "value1");
    obj.addProperty("key", "value2");
    assertThat(obj.size()).isEqualTo(1);
  }

  // ========== isEmpty Tests ==========

  @Test
  public void testIsEmptyTrue() {
    JsonObject obj = new JsonObject();
    assertThat(obj.isEmpty()).isTrue();
  }

  @Test
  public void testIsEmptyFalse() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", "value");
    assertThat(obj.isEmpty()).isFalse();
  }

  @Test
  public void testIsEmptyAfterRemove() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", "value");
    obj.remove("key");
    assertThat(obj.isEmpty()).isTrue();
  }

  // ========== has Tests ==========

  @Test
  public void testHasExistingKey() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", "value");
    assertThat(obj.has("key")).isTrue();
  }

  @Test
  public void testHasNonExistingKey() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", "value");
    assertThat(obj.has("nonexistent")).isFalse();
  }

  @Test
  public void testHasOnEmptyObject() {
    JsonObject obj = new JsonObject();
    assertThat(obj.has("key")).isFalse();
  }

  @Test
  public void testHasWithNullValue() {
    JsonObject obj = new JsonObject();
    obj.add("key", null);
    assertThat(obj.has("key")).isTrue();
  }

  // ========== get Tests ==========

  @Test
  public void testGetExistingKey() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", "value");
    JsonElement element = obj.get("key");
    assertThat(element.getAsString()).isEqualTo("value");
  }

  @Test
  public void testGetNonExistingKey() {
    JsonObject obj = new JsonObject();
    JsonElement element = obj.get("nonexistent");
    assertThat(element).isNull();
  }

  @Test
  public void testGetFromEmptyObject() {
    JsonObject obj = new JsonObject();
    assertThat(obj.get("key")).isNull();
  }

  @Test
  public void testGetNullValue() {
    JsonObject obj = new JsonObject();
    obj.add("key", null);
    JsonElement element = obj.get("key");
    assertThat(element).isEqualTo(JsonNull.INSTANCE);
  }

  // ========== getAsJsonPrimitive Tests ==========

  @Test
  public void testGetAsJsonPrimitiveString() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", "value");
    JsonPrimitive primitive = obj.getAsJsonPrimitive("key");
    assertThat(primitive.getAsString()).isEqualTo("value");
  }

  @Test
  public void testGetAsJsonPrimitiveNumber() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", 42);
    JsonPrimitive primitive = obj.getAsJsonPrimitive("key");
    assertThat(primitive.getAsInt()).isEqualTo(42);
  }

  @Test
  public void testGetAsJsonPrimitiveBoolean() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", true);
    JsonPrimitive primitive = obj.getAsJsonPrimitive("key");
    assertThat(primitive.getAsBoolean()).isTrue();
  }

  @Test
  public void testGetAsJsonPrimitiveNonExistingKey() {
    JsonObject obj = new JsonObject();
    JsonPrimitive primitive = obj.getAsJsonPrimitive("nonexistent");
    assertThat(primitive).isNull();
  }

  @Test
  public void testGetAsJsonPrimitiveWrongTypeThrows() {
    JsonObject obj = new JsonObject();
    JsonArray array = new JsonArray();
    obj.add("key", array);
    assertThrows(ClassCastException.class, () -> obj.getAsJsonPrimitive("key"));
  }

  // ========== getAsJsonArray Tests ==========

  @Test
  public void testGetAsJsonArray() {
    JsonObject obj = new JsonObject();
    JsonArray array = new JsonArray();
    array.add("element");
    obj.add("key", array);
    JsonArray retrieved = obj.getAsJsonArray("key");
    assertThat(retrieved).isSameInstanceAs(array);
  }

  @Test
  public void testGetAsJsonArrayNonExistingKey() {
    JsonObject obj = new JsonObject();
    JsonArray array = obj.getAsJsonArray("nonexistent");
    assertThat(array).isNull();
  }

  @Test
  public void testGetAsJsonArrayWrongTypeThrows() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", "value");
    assertThrows(ClassCastException.class, () -> obj.getAsJsonArray("key"));
  }

  // ========== getAsJsonObject Tests ==========

  @Test
  public void testGetAsJsonObject() {
    JsonObject obj = new JsonObject();
    JsonObject nested = new JsonObject();
    nested.addProperty("nestedKey", "nestedValue");
    obj.add("key", nested);
    JsonObject retrieved = obj.getAsJsonObject("key");
    assertThat(retrieved).isSameInstanceAs(nested);
  }

  @Test
  public void testGetAsJsonObjectNonExistingKey() {
    JsonObject obj = new JsonObject();
    JsonObject nested = obj.getAsJsonObject("nonexistent");
    assertThat(nested).isNull();
  }

  @Test
  public void testGetAsJsonObjectWrongTypeThrows() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", "value");
    assertThrows(ClassCastException.class, () -> obj.getAsJsonObject("key"));
  }

  // ========== asMap Tests ==========

  @Test
  public void testAsMapReturnsMapView() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key1", "value1");
    obj.addProperty("key2", "value2");
    Map<String, JsonElement> map = obj.asMap();
    assertThat(map).hasSize(2);
    assertThat(map.get("key1").getAsString()).isEqualTo("value1");
    assertThat(map.get("key2").getAsString()).isEqualTo("value2");
  }

  @Test
  public void testAsMapIsMutable() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", "value");
    Map<String, JsonElement> map = obj.asMap();
    map.put("newKey", new JsonPrimitive("newValue"));
    assertThat(obj.size()).isEqualTo(2);
    assertThat(obj.get("newKey").getAsString()).isEqualTo("newValue");
  }

  @Test
  public void testAsMapChangesReflectedInObject() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", "value");
    Map<String, JsonElement> map = obj.asMap();
    map.put("key", new JsonPrimitive("replaced"));
    assertThat(obj.get("key").getAsString()).isEqualTo("replaced");
  }

  @Test
  public void testAsMapRejectsNullValue() {
    JsonObject obj = new JsonObject();
    Map<String, JsonElement> map = obj.asMap();
    assertThrows(NullPointerException.class, () -> map.put("key", null));
  }

  @Test
  public void testAsMapRejectsNullKey() {
    JsonObject obj = new JsonObject();
    Map<String, JsonElement> map = obj.asMap();
    assertThrows(NullPointerException.class, () -> map.put(null, new JsonPrimitive("value")));
  }

  @Test
  public void testAsMapEmpty() {
    JsonObject obj = new JsonObject();
    Map<String, JsonElement> map = obj.asMap();
    assertThat(map).isEmpty();
  }

  @Test
  public void testAsMapRemoveReflectsInObject() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", "value");
    Map<String, JsonElement> map = obj.asMap();
    map.remove("key");
    assertThat(obj.size()).isEqualTo(0);
  }

  // ========== equals Tests ==========

  @Test
  public void testEqualsToSelf() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", "value");
    assertThat(obj.equals(obj)).isTrue();
  }

  @Test
  public void testEqualsToEqualObject() {
    JsonObject obj1 = new JsonObject();
    obj1.addProperty("key1", "value1");
    obj1.addProperty("key2", "value2");

    JsonObject obj2 = new JsonObject();
    obj2.addProperty("key1", "value1");
    obj2.addProperty("key2", "value2");

    assertThat(obj1.equals(obj2)).isTrue();
    assertThat(obj2.equals(obj1)).isTrue();
  }

  @Test
  public void testEqualsIgnoresInsertionOrder() {
    JsonObject obj1 = new JsonObject();
    obj1.addProperty("a", "1");
    obj1.addProperty("b", "2");

    JsonObject obj2 = new JsonObject();
    obj2.addProperty("b", "2");
    obj2.addProperty("a", "1");

    assertThat(obj1.equals(obj2)).isTrue();
  }

  @Test
  public void testEqualsToUnequalObject() {
    JsonObject obj1 = new JsonObject();
    obj1.addProperty("key", "value1");

    JsonObject obj2 = new JsonObject();
    obj2.addProperty("key", "value2");

    assertThat(obj1.equals(obj2)).isFalse();
  }

  @Test
  public void testEqualsToObjectDifferentSize() {
    JsonObject obj1 = new JsonObject();
    obj1.addProperty("key1", "value1");

    JsonObject obj2 = new JsonObject();
    obj2.addProperty("key1", "value1");
    obj2.addProperty("key2", "value2");

    assertThat(obj1.equals(obj2)).isFalse();
  }

  @Test
  public void testEqualsToObjectDifferentKeys() {
    JsonObject obj1 = new JsonObject();
    obj1.addProperty("key1", "value");

    JsonObject obj2 = new JsonObject();
    obj2.addProperty("key2", "value");

    assertThat(obj1.equals(obj2)).isFalse();
  }

  @Test
  public void testEqualsToNull() {
    JsonObject obj = new JsonObject();
    assertThat(obj.equals(null)).isFalse();
  }

  @Test
  public void testEqualsToOtherType() {
    JsonObject obj = new JsonObject();
    assertThat(obj.equals("not an object")).isFalse();
  }

  @Test
  public void testEqualsBothEmpty() {
    JsonObject obj1 = new JsonObject();
    JsonObject obj2 = new JsonObject();
    assertThat(obj1.equals(obj2)).isTrue();
  }

  @Test
  public void testEqualsWithNestedObjects() {
    JsonObject obj1 = new JsonObject();
    JsonObject nested1 = new JsonObject();
    nested1.addProperty("nested", "value");
    obj1.add("key", nested1);

    JsonObject obj2 = new JsonObject();
    JsonObject nested2 = new JsonObject();
    nested2.addProperty("nested", "value");
    obj2.add("key", nested2);

    assertThat(obj1.equals(obj2)).isTrue();
  }

  @Test
  public void testNotEqualsToJsonArray() {
    JsonObject obj = new JsonObject();
    JsonArray array = new JsonArray();
    assertThat(obj.equals(array)).isFalse();
  }

  @Test
  public void testNotEqualsToJsonPrimitive() {
    JsonObject obj = new JsonObject();
    JsonPrimitive primitive = new JsonPrimitive("test");
    assertThat(obj.equals(primitive)).isFalse();
  }

  // ========== hashCode Tests ==========

  @Test
  public void testHashCodeConsistent() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", "value");
    int hashCode1 = obj.hashCode();
    int hashCode2 = obj.hashCode();
    assertThat(hashCode1).isEqualTo(hashCode2);
  }

  @Test
  public void testHashCodeEqualObjects() {
    JsonObject obj1 = new JsonObject();
    obj1.addProperty("key1", "value1");
    obj1.addProperty("key2", "value2");

    JsonObject obj2 = new JsonObject();
    obj2.addProperty("key1", "value1");
    obj2.addProperty("key2", "value2");

    assertThat(obj1.hashCode()).isEqualTo(obj2.hashCode());
  }

  @Test
  public void testHashCodeIgnoresInsertionOrder() {
    JsonObject obj1 = new JsonObject();
    obj1.addProperty("a", "1");
    obj1.addProperty("b", "2");

    JsonObject obj2 = new JsonObject();
    obj2.addProperty("b", "2");
    obj2.addProperty("a", "1");

    assertThat(obj1.hashCode()).isEqualTo(obj2.hashCode());
  }

  @Test
  public void testHashCodeEmptyObjects() {
    JsonObject obj1 = new JsonObject();
    JsonObject obj2 = new JsonObject();
    assertThat(obj1.hashCode()).isEqualTo(obj2.hashCode());
  }

  @Test
  public void testHashCodeChangesAfterModification() {
    JsonObject obj = new JsonObject();
    int hashBefore = obj.hashCode();
    obj.addProperty("key", "value");
    int hashAfter = obj.hashCode();
    // Note: This is not strictly required by the hashCode contract,
    // but generally expected behavior
    assertThat(hashBefore).isNotEqualTo(hashAfter);
  }

  // ========== Integration Tests ==========

  @Test
  public void testMixedTypes() {
    JsonObject obj = new JsonObject();
    obj.addProperty("string", "value");
    obj.addProperty("number", 42);
    obj.addProperty("boolean", true);
    obj.addProperty("character", 'x');
    obj.add("null", null);

    assertThat(obj.size()).isEqualTo(5);
    assertThat(obj.get("string").getAsString()).isEqualTo("value");
    assertThat(obj.get("number").getAsInt()).isEqualTo(42);
    assertThat(obj.get("boolean").getAsBoolean()).isTrue();
    assertThat(obj.get("character").getAsString()).isEqualTo("x");
    assertThat(obj.get("null").isJsonNull()).isTrue();
  }

  @Test
  public void testNestedStructure() {
    JsonObject outer = new JsonObject();
    JsonObject middle = new JsonObject();
    JsonObject inner = new JsonObject();

    inner.addProperty("deepKey", "deepValue");
    middle.add("inner", inner);
    outer.add("middle", middle);

    JsonObject retrievedInner =
        outer.getAsJsonObject("middle").getAsJsonObject("inner");
    assertThat(retrievedInner.get("deepKey").getAsString()).isEqualTo("deepValue");
  }

  @Test
  public void testComplexNestedStructure() {
    JsonObject obj = new JsonObject();

    JsonArray array = new JsonArray();
    JsonObject arrayElement = new JsonObject();
    arrayElement.addProperty("name", "item");
    array.add(arrayElement);

    obj.add("array", array);
    obj.addProperty("string", "value");

    assertThat(obj.getAsJsonArray("array").get(0).getAsJsonObject().get("name").getAsString())
        .isEqualTo("item");
  }

  @Test
  public void testChainedOperations() {
    JsonObject obj = new JsonObject();
    obj.addProperty("a", "1");
    obj.addProperty("b", "2");
    obj.addProperty("c", "3");
    obj.remove("b");
    obj.addProperty("a", "replaced");
    obj.addProperty("d", "4");

    assertThat(obj.size()).isEqualTo(3);
    assertThat(obj.has("b")).isFalse();
    assertThat(obj.get("a").getAsString()).isEqualTo("replaced");
    assertThat(obj.get("c").getAsString()).isEqualTo("3");
    assertThat(obj.get("d").getAsString()).isEqualTo("4");
  }

  @Test
  public void testGsonSerializationRoundTrip() {
    Gson gson = new Gson();
    JsonObject original = new JsonObject();
    original.addProperty("string", "value");
    original.addProperty("number", 42);
    original.addProperty("boolean", true);

    String json = gson.toJson(original);
    JsonObject restored = gson.fromJson(json, JsonObject.class);

    assertThat(restored).isEqualTo(original);
  }

  @Test
  public void testGsonSerializationWithNestedObjects() {
    Gson gson = new Gson();
    JsonObject original = new JsonObject();
    JsonObject nested = new JsonObject();
    nested.addProperty("key", "value");
    original.add("nested", nested);

    String json = gson.toJson(original);
    JsonObject restored = gson.fromJson(json, JsonObject.class);

    assertThat(restored).isEqualTo(original);
  }

  @Test
  public void testIterationViasEntrySet() {
    JsonObject obj = new JsonObject();
    obj.addProperty("a", 1);
    obj.addProperty("b", 2);
    obj.addProperty("c", 3);

    int sum = 0;
    for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
      sum += entry.getValue().getAsInt();
    }
    assertThat(sum).isEqualTo(6);
  }

  @Test
  public void testDeepCopyWithMixedNestedTypes() {
    JsonObject original = new JsonObject();
    original.addProperty("string", "value");

    JsonArray array = new JsonArray();
    JsonObject arrayElement = new JsonObject();
    arrayElement.addProperty("nested", true);
    array.add(arrayElement);
    original.add("array", array);

    JsonObject nested = new JsonObject();
    nested.addProperty("number", 42);
    original.add("object", nested);

    JsonObject copy = original.deepCopy();

    // Verify copy is independent
    copy.getAsJsonArray("array").get(0).getAsJsonObject().addProperty("nested", false);
    assertThat(original.getAsJsonArray("array").get(0).getAsJsonObject().get("nested").getAsBoolean())
        .isTrue();
    assertThat(copy.getAsJsonArray("array").get(0).getAsJsonObject().get("nested").getAsBoolean())
        .isFalse();
  }
}
