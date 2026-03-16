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
import java.util.Iterator;
import java.util.List;
import org.junit.Test;

public class JsonArrayTest {

  @Test
  public void testEmptyArray() {
    JsonArray jsonArray = new JsonArray();
    assertThat(jsonArray.size()).isEqualTo(0);
    assertThat(jsonArray.isEmpty()).isTrue();
  }

  @Test
  public void testArrayWithInitialCapacity() {
    JsonArray jsonArray = new JsonArray(10);
    assertThat(jsonArray.size()).isEqualTo(0);
    assertThat(jsonArray.isEmpty()).isTrue();
  }

  @Test
  public void testArrayWithNegativeCapacity_throwsException() {
    assertThrows(IllegalArgumentException.class, () -> new JsonArray(-1));
  }

  @Test
  public void testAddBoolean() {
    JsonArray jsonArray = new JsonArray();
    jsonArray.add(true);
    assertThat(jsonArray.size()).isEqualTo(1);
    assertThat(jsonArray.get(0).getAsBoolean()).isTrue();
  }

  @Test
  public void testAddBoolean_null() {
    JsonArray jsonArray = new JsonArray();
    jsonArray.add((Boolean) null);
    assertThat(jsonArray.size()).isEqualTo(1);
    assertThat(jsonArray.get(0).isJsonNull()).isTrue();
  }

  @Test
  public void testAddCharacter() {
    JsonArray jsonArray = new JsonArray();
    jsonArray.add('a');
    assertThat(jsonArray.size()).isEqualTo(1);
    assertThat(jsonArray.get(0).getAsString()).isEqualTo("a");
  }

  @Test
  public void testAddCharacter_null() {
    JsonArray jsonArray = new JsonArray();
    jsonArray.add((Character) null);
    assertThat(jsonArray.size()).isEqualTo(1);
    assertThat(jsonArray.get(0).isJsonNull()).isTrue();
  }

  @Test
  public void testAddNumber() {
    JsonArray jsonArray = new JsonArray();
    jsonArray.add(123);
    assertThat(jsonArray.size()).isEqualTo(1);
    assertThat(jsonArray.get(0).getAsInt()).isEqualTo(123);
  }

  @Test
  public void testAddNumber_null() {
    JsonArray jsonArray = new JsonArray();
    jsonArray.add((Number) null);
    assertThat(jsonArray.size()).isEqualTo(1);
    assertThat(jsonArray.get(0).isJsonNull()).isTrue();
  }

  @Test
  public void testAddString() {
    JsonArray jsonArray = new JsonArray();
    jsonArray.add("hello");
    assertThat(jsonArray.size()).isEqualTo(1);
    assertThat(jsonArray.get(0).getAsString()).isEqualTo("hello");
  }

  @Test
  public void testAddString_null() {
    JsonArray jsonArray = new JsonArray();
    jsonArray.add((String) null);
    assertThat(jsonArray.size()).isEqualTo(1);
    assertThat(jsonArray.get(0).isJsonNull()).isTrue();
  }

  @Test
  public void testAddJsonElement() {
    JsonArray jsonArray = new JsonArray();
    JsonPrimitive primitive = new JsonPrimitive(123);
    jsonArray.add(primitive);
    assertThat(jsonArray.size()).isEqualTo(1);
    assertThat(jsonArray.get(0)).isSameInstanceAs(primitive);
  }

  @Test
  public void testAddJsonElement_null() {
    JsonArray jsonArray = new JsonArray();
    jsonArray.add((JsonElement) null);
    assertThat(jsonArray.size()).isEqualTo(1);
    assertThat(jsonArray.get(0).isJsonNull()).isTrue();
  }

  @Test
  public void testAddAll() {
    JsonArray array1 = new JsonArray();
    array1.add(1);
    array1.add(2);

    JsonArray array2 = new JsonArray();
    array2.add(3);
    array2.add(4);

    array1.addAll(array2);
    assertThat(array1.size()).isEqualTo(4);
    assertThat(array1.get(2).getAsInt()).isEqualTo(3);
    assertThat(array1.get(3).getAsInt()).isEqualTo(4);
  }

  @Test
  public void testSet() {
    JsonArray jsonArray = new JsonArray();
    jsonArray.add(1);
    jsonArray.add(2);
    jsonArray.add(3);

    JsonElement previous = jsonArray.set(1, new JsonPrimitive(99));
    assertThat(previous.getAsInt()).isEqualTo(2);
    assertThat(jsonArray.get(1).getAsInt()).isEqualTo(99);
  }

  @Test
  public void testSet_null() {
    JsonArray jsonArray = new JsonArray();
    jsonArray.add(1);
    jsonArray.set(0, null);
    assertThat(jsonArray.get(0).isJsonNull()).isTrue();
  }

  @Test
  public void testSet_outOfBounds_throwsException() {
    JsonArray jsonArray = new JsonArray();
    jsonArray.add(1);
    assertThrows(IndexOutOfBoundsException.class, () -> jsonArray.set(5, new JsonPrimitive(99)));
  }

  @Test
  public void testRemoveByElement() {
    JsonArray jsonArray = new JsonArray();
    JsonPrimitive element = new JsonPrimitive(123);
    jsonArray.add(element);
    jsonArray.add(456);

    boolean removed = jsonArray.remove(element);
    assertThat(removed).isTrue();
    assertThat(jsonArray.size()).isEqualTo(1);
  }

  @Test
  public void testRemoveByElement_notPresent() {
    JsonArray jsonArray = new JsonArray();
    jsonArray.add(123);
    boolean removed = jsonArray.remove(new JsonPrimitive(456));
    assertThat(removed).isFalse();
    assertThat(jsonArray.size()).isEqualTo(1);
  }

  @Test
  public void testRemoveByIndex() {
    JsonArray jsonArray = new JsonArray();
    jsonArray.add(1);
    jsonArray.add(2);
    jsonArray.add(3);

    JsonElement removed = jsonArray.remove(1);
    assertThat(removed.getAsInt()).isEqualTo(2);
    assertThat(jsonArray.size()).isEqualTo(2);
    assertThat(jsonArray.get(0).getAsInt()).isEqualTo(1);
    assertThat(jsonArray.get(1).getAsInt()).isEqualTo(3);
  }

  @Test
  public void testRemoveByIndex_outOfBounds_throwsException() {
    JsonArray jsonArray = new JsonArray();
    jsonArray.add(1);
    assertThrows(IndexOutOfBoundsException.class, () -> jsonArray.remove(5));
  }

  @Test
  public void testContains() {
    JsonArray jsonArray = new JsonArray();
    JsonPrimitive element = new JsonPrimitive(123);
    jsonArray.add(element);
    assertThat(jsonArray.contains(element)).isTrue();
    assertThat(jsonArray.contains(new JsonPrimitive(456))).isFalse();
  }

  @Test
  public void testGet() {
    JsonArray jsonArray = new JsonArray();
    jsonArray.add(123);
    assertThat(jsonArray.get(0).getAsInt()).isEqualTo(123);
  }

  @Test
  public void testGet_outOfBounds_throwsException() {
    JsonArray jsonArray = new JsonArray();
    jsonArray.add(1);
    assertThrows(IndexOutOfBoundsException.class, () -> jsonArray.get(5));
  }

  @Test
  public void testIterator() {
    JsonArray jsonArray = new JsonArray();
    jsonArray.add(1);
    jsonArray.add(2);
    jsonArray.add(3);

    Iterator<JsonElement> iterator = jsonArray.iterator();
    assertThat(iterator.hasNext()).isTrue();
    assertThat(iterator.next().getAsInt()).isEqualTo(1);
    assertThat(iterator.next().getAsInt()).isEqualTo(2);
    assertThat(iterator.next().getAsInt()).isEqualTo(3);
    assertThat(iterator.hasNext()).isFalse();
  }

  @Test
  public void testGetAsSingleElement_number() {
    JsonArray jsonArray = new JsonArray();
    jsonArray.add(123);
    assertThat(jsonArray.getAsNumber().intValue()).isEqualTo(123);
  }

  @Test
  public void testGetAsSingleElement_string() {
    JsonArray jsonArray = new JsonArray();
    jsonArray.add("hello");
    assertThat(jsonArray.getAsString()).isEqualTo("hello");
  }

  @Test
  public void testGetAsSingleElement_double() {
    JsonArray jsonArray = new JsonArray();
    jsonArray.add(123.45);
    assertThat(jsonArray.getAsDouble()).isEqualTo(123.45);
  }

  @Test
  public void testGetAsSingleElement_float() {
    JsonArray jsonArray = new JsonArray();
    jsonArray.add(123.45f);
    assertThat(jsonArray.getAsFloat()).isEqualTo(123.45f);
  }

  @Test
  public void testGetAsSingleElement_long() {
    JsonArray jsonArray = new JsonArray();
    jsonArray.add(123456789L);
    assertThat(jsonArray.getAsLong()).isEqualTo(123456789L);
  }

  @Test
  public void testGetAsSingleElement_int() {
    JsonArray jsonArray = new JsonArray();
    jsonArray.add(123);
    assertThat(jsonArray.getAsInt()).isEqualTo(123);
  }

  @Test
  public void testGetAsSingleElement_byte() {
    JsonArray jsonArray = new JsonArray();
    jsonArray.add((byte) 127);
    assertThat(jsonArray.getAsByte()).isEqualTo((byte) 127);
  }

  @Test
  public void testGetAsSingleElement_short() {
    JsonArray jsonArray = new JsonArray();
    jsonArray.add((short) 32000);
    assertThat(jsonArray.getAsShort()).isEqualTo((short) 32000);
  }

  @Test
  public void testGetAsSingleElement_boolean() {
    JsonArray jsonArray = new JsonArray();
    jsonArray.add(true);
    assertThat(jsonArray.getAsBoolean()).isTrue();
  }

  @Test
  public void testGetAsSingleElement_bigDecimal() {
    JsonArray jsonArray = new JsonArray();
    BigDecimal value = new BigDecimal("123.456");
    jsonArray.add(value);
    assertThat(jsonArray.getAsBigDecimal()).isEqualTo(value);
  }

  @Test
  public void testGetAsSingleElement_bigInteger() {
    JsonArray jsonArray = new JsonArray();
    BigInteger value = new BigInteger("12345678901234567890");
    jsonArray.add(value);
    assertThat(jsonArray.getAsBigInteger()).isEqualTo(value);
  }

  @Test
  public void testGetAsSingleElement_emptyArray_throwsException() {
    JsonArray jsonArray = new JsonArray();
    IllegalStateException exception =
        assertThrows(IllegalStateException.class, () -> jsonArray.getAsNumber());
    assertThat(exception).hasMessageThat().contains("Array must have size 1, but has size 0");
  }

  @Test
  public void testGetAsSingleElement_multipleElements_throwsException() {
    JsonArray jsonArray = new JsonArray();
    jsonArray.add(1);
    jsonArray.add(2);
    IllegalStateException exception =
        assertThrows(IllegalStateException.class, () -> jsonArray.getAsNumber());
    assertThat(exception).hasMessageThat().contains("Array must have size 1, but has size 2");
  }

  @Test
  public void testAsList() {
    JsonArray jsonArray = new JsonArray();
    jsonArray.add(1);
    jsonArray.add(2);

    List<JsonElement> list = jsonArray.asList();
    assertThat(list.size()).isEqualTo(2);
    assertThat(list.get(0).getAsInt()).isEqualTo(1);
    assertThat(list.get(1).getAsInt()).isEqualTo(2);
  }

  @Test
  public void testAsList_modificationsReflected() {
    JsonArray jsonArray = new JsonArray();
    jsonArray.add(1);

    List<JsonElement> list = jsonArray.asList();
    list.add(new JsonPrimitive(2));

    assertThat(jsonArray.size()).isEqualTo(2);
    assertThat(jsonArray.get(1).getAsInt()).isEqualTo(2);
  }

  @Test
  public void testAsList_nullNotAllowed() {
    JsonArray jsonArray = new JsonArray();
    List<JsonElement> list = jsonArray.asList();
    assertThrows(NullPointerException.class, () -> list.add(null));
  }

  @Test
  public void testDeepCopy_empty() {
    JsonArray original = new JsonArray();
    JsonArray copy = original.deepCopy();
    assertThat(copy).isNotSameInstanceAs(original);
    assertThat(copy.size()).isEqualTo(0);
  }

  @Test
  public void testDeepCopy_withElements() {
    JsonArray original = new JsonArray();
    original.add(1);
    original.add("hello");

    JsonObject nestedObject = new JsonObject();
    nestedObject.addProperty("key", "value");
    original.add(nestedObject);

    JsonArray copy = original.deepCopy();

    assertThat(copy).isNotSameInstanceAs(original);
    assertThat(copy.size()).isEqualTo(3);
    assertThat(copy.get(0).getAsInt()).isEqualTo(1);
    assertThat(copy.get(1).getAsString()).isEqualTo("hello");

    // Verify deep copy
    JsonObject copiedObject = copy.get(2).getAsJsonObject();
    assertThat(copiedObject).isNotSameInstanceAs(nestedObject);
    assertThat(copiedObject.get("key").getAsString()).isEqualTo("value");

    // Modifying copy should not affect original
    copiedObject.addProperty("newKey", "newValue");
    assertThat(nestedObject.has("newKey")).isFalse();
  }

  @Test
  public void testIsJsonArray() {
    JsonArray jsonArray = new JsonArray();
    assertThat(jsonArray.isJsonArray()).isTrue();
    assertThat(jsonArray.isJsonObject()).isFalse();
    assertThat(jsonArray.isJsonPrimitive()).isFalse();
    assertThat(jsonArray.isJsonNull()).isFalse();
  }

  @Test
  public void testEquals_sameInstance() {
    JsonArray jsonArray = new JsonArray();
    jsonArray.add(1);
    assertThat(jsonArray.equals(jsonArray)).isTrue();
  }

  @Test
  public void testEquals_equalArrays() {
    JsonArray array1 = new JsonArray();
    array1.add(1);
    array1.add("hello");

    JsonArray array2 = new JsonArray();
    array2.add(1);
    array2.add("hello");

    assertThat(array1.equals(array2)).isTrue();
    assertThat(array1.hashCode()).isEqualTo(array2.hashCode());
  }

  @Test
  public void testEquals_differentOrder() {
    JsonArray array1 = new JsonArray();
    array1.add(1);
    array1.add(2);

    JsonArray array2 = new JsonArray();
    array2.add(2);
    array2.add(1);

    assertThat(array1.equals(array2)).isFalse();
  }

  @Test
  public void testEquals_differentSize() {
    JsonArray array1 = new JsonArray();
    array1.add(1);

    JsonArray array2 = new JsonArray();
    array2.add(1);
    array2.add(2);

    assertThat(array1.equals(array2)).isFalse();
  }

  @Test
  public void testEquals_null() {
    JsonArray jsonArray = new JsonArray();
    assertThat(jsonArray.equals(null)).isFalse();
  }

  @Test
  @SuppressWarnings("EqualsIncompatibleType")
  public void testEquals_differentType() {
    JsonArray jsonArray = new JsonArray();
    assertThat(jsonArray.equals(new JsonObject())).isFalse();
  }

  @Test
  public void testToString_empty() {
    JsonArray jsonArray = new JsonArray();
    assertThat(jsonArray.toString()).isEqualTo("[]");
  }

  @Test
  public void testToString_withElements() {
    JsonArray jsonArray = new JsonArray();
    jsonArray.add(1);
    jsonArray.add("hello");
    jsonArray.add(true);
    assertThat(jsonArray.toString()).isEqualTo("[1,\"hello\",true]");
  }
}
