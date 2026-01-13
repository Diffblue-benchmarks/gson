/*
 * Copyright (C) 2022 Google Inc.
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
import java.util.NoSuchElementException;
import org.junit.Test;

/**
 * Tests for {@link JsonArray}.
 *
 * @author Claude
 */
public class JsonArrayClaudeTest {

  // ==========================================================================
  // Tests for constructors
  // ==========================================================================

  @Test
  public void testDefaultConstructor() {
    JsonArray array = new JsonArray();
    assertThat(array.size()).isEqualTo(0);
    assertThat(array.isEmpty()).isTrue();
  }

  @Test
  public void testCapacityConstructor() {
    JsonArray array = new JsonArray(10);
    assertThat(array.size()).isEqualTo(0);
    assertThat(array.isEmpty()).isTrue();
  }

  @Test
  public void testCapacityConstructorWithZero() {
    JsonArray array = new JsonArray(0);
    assertThat(array.size()).isEqualTo(0);
    assertThat(array.isEmpty()).isTrue();
  }

  @Test
  public void testCapacityConstructorWithNegative() {
    assertThrows(IllegalArgumentException.class, () -> new JsonArray(-1));
  }

  // ==========================================================================
  // Tests for deepCopy()
  // ==========================================================================

  @Test
  public void testDeepCopyEmpty() {
    JsonArray original = new JsonArray();
    JsonArray copy = original.deepCopy();

    assertThat(copy).isNotSameInstanceAs(original);
    assertThat(copy.size()).isEqualTo(0);
  }

  @Test
  public void testDeepCopyWithElements() {
    JsonArray original = new JsonArray();
    original.add(new JsonPrimitive("test"));
    original.add(new JsonPrimitive(123));

    JsonArray copy = original.deepCopy();

    assertThat(copy).isNotSameInstanceAs(original);
    assertThat(copy.size()).isEqualTo(2);
    assertThat(copy.get(0).getAsString()).isEqualTo("test");
    assertThat(copy.get(1).getAsInt()).isEqualTo(123);

    // Verify it's a deep copy - modifying original doesn't affect copy
    original.add("another");
    assertThat(original.size()).isEqualTo(3);
    assertThat(copy.size()).isEqualTo(2);
  }

  @Test
  public void testDeepCopyWithNestedArray() {
    JsonArray nested = new JsonArray();
    nested.add("inner");

    JsonArray original = new JsonArray();
    original.add(nested);

    JsonArray copy = original.deepCopy();

    assertThat(copy.get(0)).isNotSameInstanceAs(nested);
    assertThat(copy.get(0).getAsJsonArray().get(0).getAsString()).isEqualTo("inner");
  }

  @Test
  public void testDeepCopyWithNestedObject() {
    JsonObject nested = new JsonObject();
    nested.addProperty("key", "value");

    JsonArray original = new JsonArray();
    original.add(nested);

    JsonArray copy = original.deepCopy();

    assertThat(copy.get(0)).isNotSameInstanceAs(nested);
    assertThat(copy.get(0).getAsJsonObject().get("key").getAsString()).isEqualTo("value");
  }

  // ==========================================================================
  // Tests for add(Boolean)
  // ==========================================================================

  @Test
  public void testAddBooleanTrue() {
    JsonArray array = new JsonArray();
    array.add(Boolean.TRUE);

    assertThat(array.size()).isEqualTo(1);
    assertThat(array.get(0).getAsBoolean()).isTrue();
  }

  @Test
  public void testAddBooleanFalse() {
    JsonArray array = new JsonArray();
    array.add(Boolean.FALSE);

    assertThat(array.size()).isEqualTo(1);
    assertThat(array.get(0).getAsBoolean()).isFalse();
  }

  @Test
  public void testAddBooleanNull() {
    JsonArray array = new JsonArray();
    array.add((Boolean) null);

    assertThat(array.size()).isEqualTo(1);
    assertThat(array.get(0)).isEqualTo(JsonNull.INSTANCE);
  }

  // ==========================================================================
  // Tests for add(Character)
  // ==========================================================================

  @Test
  public void testAddCharacter() {
    JsonArray array = new JsonArray();
    array.add('A');

    assertThat(array.size()).isEqualTo(1);
    assertThat(array.get(0).getAsString()).isEqualTo("A");
  }

  @Test
  public void testAddCharacterNull() {
    JsonArray array = new JsonArray();
    array.add((Character) null);

    assertThat(array.size()).isEqualTo(1);
    assertThat(array.get(0)).isEqualTo(JsonNull.INSTANCE);
  }

  // ==========================================================================
  // Tests for add(Number)
  // ==========================================================================

  @Test
  public void testAddNumberInteger() {
    JsonArray array = new JsonArray();
    array.add(42);

    assertThat(array.size()).isEqualTo(1);
    assertThat(array.get(0).getAsInt()).isEqualTo(42);
  }

  @Test
  public void testAddNumberDouble() {
    JsonArray array = new JsonArray();
    array.add(3.14);

    assertThat(array.size()).isEqualTo(1);
    assertThat(array.get(0).getAsDouble()).isWithin(0.001).of(3.14);
  }

  @Test
  public void testAddNumberLong() {
    JsonArray array = new JsonArray();
    array.add(Long.MAX_VALUE);

    assertThat(array.size()).isEqualTo(1);
    assertThat(array.get(0).getAsLong()).isEqualTo(Long.MAX_VALUE);
  }

  @Test
  public void testAddNumberNull() {
    JsonArray array = new JsonArray();
    array.add((Number) null);

    assertThat(array.size()).isEqualTo(1);
    assertThat(array.get(0)).isEqualTo(JsonNull.INSTANCE);
  }

  @Test
  public void testAddBigDecimal() {
    JsonArray array = new JsonArray();
    array.add(new BigDecimal("123.456"));

    assertThat(array.size()).isEqualTo(1);
    assertThat(array.get(0).getAsBigDecimal()).isEqualTo(new BigDecimal("123.456"));
  }

  @Test
  public void testAddBigInteger() {
    JsonArray array = new JsonArray();
    array.add(new BigInteger("12345678901234567890"));

    assertThat(array.size()).isEqualTo(1);
    assertThat(array.get(0).getAsBigInteger()).isEqualTo(new BigInteger("12345678901234567890"));
  }

  // ==========================================================================
  // Tests for add(String)
  // ==========================================================================

  @Test
  public void testAddString() {
    JsonArray array = new JsonArray();
    array.add("hello");

    assertThat(array.size()).isEqualTo(1);
    assertThat(array.get(0).getAsString()).isEqualTo("hello");
  }

  @Test
  public void testAddEmptyString() {
    JsonArray array = new JsonArray();
    array.add("");

    assertThat(array.size()).isEqualTo(1);
    assertThat(array.get(0).getAsString()).isEmpty();
  }

  @Test
  public void testAddStringNull() {
    JsonArray array = new JsonArray();
    array.add((String) null);

    assertThat(array.size()).isEqualTo(1);
    assertThat(array.get(0)).isEqualTo(JsonNull.INSTANCE);
  }

  // ==========================================================================
  // Tests for add(JsonElement)
  // ==========================================================================

  @Test
  public void testAddJsonPrimitive() {
    JsonArray array = new JsonArray();
    JsonPrimitive primitive = new JsonPrimitive("test");
    array.add(primitive);

    assertThat(array.size()).isEqualTo(1);
    assertThat(array.get(0)).isSameInstanceAs(primitive);
  }

  @Test
  public void testAddJsonArray() {
    JsonArray array = new JsonArray();
    JsonArray nested = new JsonArray();
    nested.add("inner");
    array.add(nested);

    assertThat(array.size()).isEqualTo(1);
    assertThat(array.get(0)).isSameInstanceAs(nested);
  }

  @Test
  public void testAddJsonObject() {
    JsonArray array = new JsonArray();
    JsonObject obj = new JsonObject();
    obj.addProperty("key", "value");
    array.add(obj);

    assertThat(array.size()).isEqualTo(1);
    assertThat(array.get(0)).isSameInstanceAs(obj);
  }

  @Test
  public void testAddJsonNull() {
    JsonArray array = new JsonArray();
    array.add(JsonNull.INSTANCE);

    assertThat(array.size()).isEqualTo(1);
    assertThat(array.get(0)).isEqualTo(JsonNull.INSTANCE);
  }

  @Test
  public void testAddJsonElementNull() {
    JsonArray array = new JsonArray();
    array.add((JsonElement) null);

    assertThat(array.size()).isEqualTo(1);
    assertThat(array.get(0)).isEqualTo(JsonNull.INSTANCE);
  }

  // ==========================================================================
  // Tests for addAll(JsonArray)
  // ==========================================================================

  @Test
  public void testAddAll() {
    JsonArray array = new JsonArray();
    array.add("first");

    JsonArray toAdd = new JsonArray();
    toAdd.add("second");
    toAdd.add("third");

    array.addAll(toAdd);

    assertThat(array.size()).isEqualTo(3);
    assertThat(array.get(0).getAsString()).isEqualTo("first");
    assertThat(array.get(1).getAsString()).isEqualTo("second");
    assertThat(array.get(2).getAsString()).isEqualTo("third");
  }

  @Test
  public void testAddAllEmpty() {
    JsonArray array = new JsonArray();
    array.add("first");

    JsonArray toAdd = new JsonArray();
    array.addAll(toAdd);

    assertThat(array.size()).isEqualTo(1);
  }

  @Test
  public void testAddAllToEmpty() {
    JsonArray array = new JsonArray();

    JsonArray toAdd = new JsonArray();
    toAdd.add("first");
    array.addAll(toAdd);

    assertThat(array.size()).isEqualTo(1);
    assertThat(array.get(0).getAsString()).isEqualTo("first");
  }

  // ==========================================================================
  // Tests for set(int, JsonElement)
  // ==========================================================================

  @Test
  public void testSet() {
    JsonArray array = new JsonArray();
    array.add("original");

    JsonElement previous = array.set(0, new JsonPrimitive("replaced"));

    assertThat(array.get(0).getAsString()).isEqualTo("replaced");
    assertThat(previous.getAsString()).isEqualTo("original");
  }

  @Test
  public void testSetWithNull() {
    JsonArray array = new JsonArray();
    array.add("original");

    array.set(0, null);

    assertThat(array.get(0)).isEqualTo(JsonNull.INSTANCE);
  }

  @Test
  public void testSetIndexOutOfBounds() {
    JsonArray array = new JsonArray();
    array.add("first");

    assertThrows(IndexOutOfBoundsException.class, () -> array.set(5, new JsonPrimitive("test")));
  }

  @Test
  public void testSetNegativeIndex() {
    JsonArray array = new JsonArray();
    array.add("first");

    assertThrows(IndexOutOfBoundsException.class, () -> array.set(-1, new JsonPrimitive("test")));
  }

  // ==========================================================================
  // Tests for remove(JsonElement)
  // ==========================================================================

  @Test
  public void testRemoveElement() {
    JsonArray array = new JsonArray();
    JsonPrimitive element = new JsonPrimitive("test");
    array.add(element);

    boolean removed = array.remove(element);

    assertThat(removed).isTrue();
    assertThat(array.size()).isEqualTo(0);
  }

  @Test
  public void testRemoveElementNotFound() {
    JsonArray array = new JsonArray();
    array.add(new JsonPrimitive("test"));

    boolean removed = array.remove(new JsonPrimitive("other"));

    assertThat(removed).isFalse();
    assertThat(array.size()).isEqualTo(1);
  }

  @Test
  public void testRemoveElementByEquality() {
    JsonArray array = new JsonArray();
    array.add(new JsonPrimitive("test"));

    // JsonPrimitive uses value equality
    boolean removed = array.remove(new JsonPrimitive("test"));

    assertThat(removed).isTrue();
    assertThat(array.size()).isEqualTo(0);
  }

  @Test
  public void testRemoveElementFirstOccurrence() {
    JsonArray array = new JsonArray();
    array.add(new JsonPrimitive("test"));
    array.add(new JsonPrimitive("test"));

    array.remove(new JsonPrimitive("test"));

    assertThat(array.size()).isEqualTo(1);
    assertThat(array.get(0).getAsString()).isEqualTo("test");
  }

  // ==========================================================================
  // Tests for remove(int)
  // ==========================================================================

  @Test
  public void testRemoveByIndex() {
    JsonArray array = new JsonArray();
    array.add("first");
    array.add("second");
    array.add("third");

    JsonElement removed = array.remove(1);

    assertThat(removed.getAsString()).isEqualTo("second");
    assertThat(array.size()).isEqualTo(2);
    assertThat(array.get(0).getAsString()).isEqualTo("first");
    assertThat(array.get(1).getAsString()).isEqualTo("third");
  }

  @Test
  public void testRemoveByIndexFirst() {
    JsonArray array = new JsonArray();
    array.add("first");
    array.add("second");

    JsonElement removed = array.remove(0);

    assertThat(removed.getAsString()).isEqualTo("first");
    assertThat(array.size()).isEqualTo(1);
    assertThat(array.get(0).getAsString()).isEqualTo("second");
  }

  @Test
  public void testRemoveByIndexLast() {
    JsonArray array = new JsonArray();
    array.add("first");
    array.add("second");

    JsonElement removed = array.remove(1);

    assertThat(removed.getAsString()).isEqualTo("second");
    assertThat(array.size()).isEqualTo(1);
    assertThat(array.get(0).getAsString()).isEqualTo("first");
  }

  @Test
  public void testRemoveByIndexOutOfBounds() {
    JsonArray array = new JsonArray();
    array.add("first");

    assertThrows(IndexOutOfBoundsException.class, () -> array.remove(5));
  }

  @Test
  public void testRemoveByNegativeIndex() {
    JsonArray array = new JsonArray();
    array.add("first");

    assertThrows(IndexOutOfBoundsException.class, () -> array.remove(-1));
  }

  // ==========================================================================
  // Tests for contains(JsonElement)
  // ==========================================================================

  @Test
  public void testContainsTrue() {
    JsonArray array = new JsonArray();
    JsonPrimitive element = new JsonPrimitive("test");
    array.add(element);

    assertThat(array.contains(element)).isTrue();
  }

  @Test
  public void testContainsByValue() {
    JsonArray array = new JsonArray();
    array.add(new JsonPrimitive("test"));

    // Uses value equality
    assertThat(array.contains(new JsonPrimitive("test"))).isTrue();
  }

  @Test
  public void testContainsFalse() {
    JsonArray array = new JsonArray();
    array.add(new JsonPrimitive("test"));

    assertThat(array.contains(new JsonPrimitive("other"))).isFalse();
  }

  @Test
  public void testContainsNull() {
    JsonArray array = new JsonArray();
    array.add(JsonNull.INSTANCE);

    assertThat(array.contains(JsonNull.INSTANCE)).isTrue();
    assertThat(array.contains(null)).isFalse();
  }

  @Test
  public void testContainsEmptyArray() {
    JsonArray array = new JsonArray();

    assertThat(array.contains(new JsonPrimitive("test"))).isFalse();
  }

  // ==========================================================================
  // Tests for size()
  // ==========================================================================

  @Test
  public void testSizeEmpty() {
    JsonArray array = new JsonArray();
    assertThat(array.size()).isEqualTo(0);
  }

  @Test
  public void testSizeAfterAdd() {
    JsonArray array = new JsonArray();
    array.add("first");
    assertThat(array.size()).isEqualTo(1);
    array.add("second");
    assertThat(array.size()).isEqualTo(2);
  }

  @Test
  public void testSizeAfterRemove() {
    JsonArray array = new JsonArray();
    array.add("first");
    array.add("second");
    array.remove(0);
    assertThat(array.size()).isEqualTo(1);
  }

  // ==========================================================================
  // Tests for isEmpty()
  // ==========================================================================

  @Test
  public void testIsEmptyTrue() {
    JsonArray array = new JsonArray();
    assertThat(array.isEmpty()).isTrue();
  }

  @Test
  public void testIsEmptyFalse() {
    JsonArray array = new JsonArray();
    array.add("test");
    assertThat(array.isEmpty()).isFalse();
  }

  @Test
  public void testIsEmptyAfterRemoval() {
    JsonArray array = new JsonArray();
    array.add("test");
    array.remove(0);
    assertThat(array.isEmpty()).isTrue();
  }

  // ==========================================================================
  // Tests for iterator()
  // ==========================================================================

  @Test
  public void testIteratorEmpty() {
    JsonArray array = new JsonArray();
    Iterator<JsonElement> iter = array.iterator();

    assertThat(iter.hasNext()).isFalse();
  }

  @Test
  public void testIterator() {
    JsonArray array = new JsonArray();
    array.add("first");
    array.add("second");
    array.add("third");

    Iterator<JsonElement> iter = array.iterator();

    assertThat(iter.hasNext()).isTrue();
    assertThat(iter.next().getAsString()).isEqualTo("first");
    assertThat(iter.hasNext()).isTrue();
    assertThat(iter.next().getAsString()).isEqualTo("second");
    assertThat(iter.hasNext()).isTrue();
    assertThat(iter.next().getAsString()).isEqualTo("third");
    assertThat(iter.hasNext()).isFalse();
  }

  @Test
  public void testIteratorNoSuchElement() {
    JsonArray array = new JsonArray();
    Iterator<JsonElement> iter = array.iterator();

    assertThrows(NoSuchElementException.class, iter::next);
  }

  @Test
  public void testForEachIteration() {
    JsonArray array = new JsonArray();
    array.add("first");
    array.add("second");

    int count = 0;
    for (JsonElement element : array) {
      count++;
      assertThat(element).isNotNull();
    }
    assertThat(count).isEqualTo(2);
  }

  // ==========================================================================
  // Tests for get(int)
  // ==========================================================================

  @Test
  public void testGet() {
    JsonArray array = new JsonArray();
    array.add("test");

    assertThat(array.get(0).getAsString()).isEqualTo("test");
  }

  @Test
  public void testGetMultiple() {
    JsonArray array = new JsonArray();
    array.add("first");
    array.add("second");

    assertThat(array.get(0).getAsString()).isEqualTo("first");
    assertThat(array.get(1).getAsString()).isEqualTo("second");
  }

  @Test
  public void testGetOutOfBounds() {
    JsonArray array = new JsonArray();
    array.add("first");

    assertThrows(IndexOutOfBoundsException.class, () -> array.get(5));
  }

  @Test
  public void testGetNegativeIndex() {
    JsonArray array = new JsonArray();
    array.add("first");

    assertThrows(IndexOutOfBoundsException.class, () -> array.get(-1));
  }

  @Test
  public void testGetEmptyArray() {
    JsonArray array = new JsonArray();

    assertThrows(IndexOutOfBoundsException.class, () -> array.get(0));
  }

  // ==========================================================================
  // Tests for getAsNumber()
  // ==========================================================================

  @Test
  public void testGetAsNumber() {
    JsonArray array = new JsonArray();
    array.add(42);

    assertThat(array.getAsNumber().intValue()).isEqualTo(42);
  }

  @Test
  public void testGetAsNumberEmptyArray() {
    JsonArray array = new JsonArray();

    IllegalStateException exception =
        assertThrows(IllegalStateException.class, array::getAsNumber);
    assertThat(exception.getMessage()).contains("Array must have size 1");
  }

  @Test
  public void testGetAsNumberMultipleElements() {
    JsonArray array = new JsonArray();
    array.add(1);
    array.add(2);

    IllegalStateException exception =
        assertThrows(IllegalStateException.class, array::getAsNumber);
    assertThat(exception.getMessage()).contains("Array must have size 1");
  }

  // ==========================================================================
  // Tests for getAsString()
  // ==========================================================================

  @Test
  public void testGetAsString() {
    JsonArray array = new JsonArray();
    array.add("hello");

    assertThat(array.getAsString()).isEqualTo("hello");
  }

  @Test
  public void testGetAsStringEmptyArray() {
    JsonArray array = new JsonArray();

    IllegalStateException exception =
        assertThrows(IllegalStateException.class, array::getAsString);
    assertThat(exception.getMessage()).contains("Array must have size 1");
  }

  @Test
  public void testGetAsStringMultipleElements() {
    JsonArray array = new JsonArray();
    array.add("first");
    array.add("second");

    IllegalStateException exception =
        assertThrows(IllegalStateException.class, array::getAsString);
    assertThat(exception.getMessage()).contains("Array must have size 1");
  }

  // ==========================================================================
  // Tests for getAsDouble()
  // ==========================================================================

  @Test
  public void testGetAsDouble() {
    JsonArray array = new JsonArray();
    array.add(3.14);

    assertThat(array.getAsDouble()).isWithin(0.001).of(3.14);
  }

  @Test
  public void testGetAsDoubleEmptyArray() {
    JsonArray array = new JsonArray();

    IllegalStateException exception =
        assertThrows(IllegalStateException.class, array::getAsDouble);
    assertThat(exception.getMessage()).contains("Array must have size 1");
  }

  @Test
  public void testGetAsDoubleMultipleElements() {
    JsonArray array = new JsonArray();
    array.add(1.0);
    array.add(2.0);

    IllegalStateException exception =
        assertThrows(IllegalStateException.class, array::getAsDouble);
    assertThat(exception.getMessage()).contains("Array must have size 1");
  }

  // ==========================================================================
  // Tests for getAsBigDecimal()
  // ==========================================================================

  @Test
  public void testGetAsBigDecimal() {
    JsonArray array = new JsonArray();
    array.add(new BigDecimal("123.456"));

    assertThat(array.getAsBigDecimal()).isEqualTo(new BigDecimal("123.456"));
  }

  @Test
  public void testGetAsBigDecimalFromInteger() {
    JsonArray array = new JsonArray();
    array.add(42);

    assertThat(array.getAsBigDecimal()).isEqualTo(new BigDecimal("42"));
  }

  @Test
  public void testGetAsBigDecimalEmptyArray() {
    JsonArray array = new JsonArray();

    IllegalStateException exception =
        assertThrows(IllegalStateException.class, array::getAsBigDecimal);
    assertThat(exception.getMessage()).contains("Array must have size 1");
  }

  @Test
  public void testGetAsBigDecimalMultipleElements() {
    JsonArray array = new JsonArray();
    array.add(1);
    array.add(2);

    IllegalStateException exception =
        assertThrows(IllegalStateException.class, array::getAsBigDecimal);
    assertThat(exception.getMessage()).contains("Array must have size 1");
  }

  // ==========================================================================
  // Tests for getAsBigInteger()
  // ==========================================================================

  @Test
  public void testGetAsBigInteger() {
    JsonArray array = new JsonArray();
    array.add(new BigInteger("12345678901234567890"));

    assertThat(array.getAsBigInteger()).isEqualTo(new BigInteger("12345678901234567890"));
  }

  @Test
  public void testGetAsBigIntegerFromInteger() {
    JsonArray array = new JsonArray();
    array.add(42);

    assertThat(array.getAsBigInteger()).isEqualTo(new BigInteger("42"));
  }

  @Test
  public void testGetAsBigIntegerEmptyArray() {
    JsonArray array = new JsonArray();

    IllegalStateException exception =
        assertThrows(IllegalStateException.class, array::getAsBigInteger);
    assertThat(exception.getMessage()).contains("Array must have size 1");
  }

  @Test
  public void testGetAsBigIntegerMultipleElements() {
    JsonArray array = new JsonArray();
    array.add(1);
    array.add(2);

    IllegalStateException exception =
        assertThrows(IllegalStateException.class, array::getAsBigInteger);
    assertThat(exception.getMessage()).contains("Array must have size 1");
  }

  // ==========================================================================
  // Tests for getAsFloat()
  // ==========================================================================

  @Test
  public void testGetAsFloat() {
    JsonArray array = new JsonArray();
    array.add(3.14f);

    assertThat(array.getAsFloat()).isWithin(0.001f).of(3.14f);
  }

  @Test
  public void testGetAsFloatEmptyArray() {
    JsonArray array = new JsonArray();

    IllegalStateException exception =
        assertThrows(IllegalStateException.class, array::getAsFloat);
    assertThat(exception.getMessage()).contains("Array must have size 1");
  }

  @Test
  public void testGetAsFloatMultipleElements() {
    JsonArray array = new JsonArray();
    array.add(1.0f);
    array.add(2.0f);

    IllegalStateException exception =
        assertThrows(IllegalStateException.class, array::getAsFloat);
    assertThat(exception.getMessage()).contains("Array must have size 1");
  }

  // ==========================================================================
  // Tests for getAsLong()
  // ==========================================================================

  @Test
  public void testGetAsLong() {
    JsonArray array = new JsonArray();
    array.add(Long.MAX_VALUE);

    assertThat(array.getAsLong()).isEqualTo(Long.MAX_VALUE);
  }

  @Test
  public void testGetAsLongEmptyArray() {
    JsonArray array = new JsonArray();

    IllegalStateException exception =
        assertThrows(IllegalStateException.class, array::getAsLong);
    assertThat(exception.getMessage()).contains("Array must have size 1");
  }

  @Test
  public void testGetAsLongMultipleElements() {
    JsonArray array = new JsonArray();
    array.add(1L);
    array.add(2L);

    IllegalStateException exception =
        assertThrows(IllegalStateException.class, array::getAsLong);
    assertThat(exception.getMessage()).contains("Array must have size 1");
  }

  // ==========================================================================
  // Tests for getAsInt()
  // ==========================================================================

  @Test
  public void testGetAsInt() {
    JsonArray array = new JsonArray();
    array.add(42);

    assertThat(array.getAsInt()).isEqualTo(42);
  }

  @Test
  public void testGetAsIntEmptyArray() {
    JsonArray array = new JsonArray();

    IllegalStateException exception = assertThrows(IllegalStateException.class, array::getAsInt);
    assertThat(exception.getMessage()).contains("Array must have size 1");
  }

  @Test
  public void testGetAsIntMultipleElements() {
    JsonArray array = new JsonArray();
    array.add(1);
    array.add(2);

    IllegalStateException exception = assertThrows(IllegalStateException.class, array::getAsInt);
    assertThat(exception.getMessage()).contains("Array must have size 1");
  }

  // ==========================================================================
  // Tests for getAsByte()
  // ==========================================================================

  @Test
  public void testGetAsByte() {
    JsonArray array = new JsonArray();
    array.add((byte) 42);

    assertThat(array.getAsByte()).isEqualTo((byte) 42);
  }

  @Test
  public void testGetAsByteEmptyArray() {
    JsonArray array = new JsonArray();

    IllegalStateException exception = assertThrows(IllegalStateException.class, array::getAsByte);
    assertThat(exception.getMessage()).contains("Array must have size 1");
  }

  @Test
  public void testGetAsByteMultipleElements() {
    JsonArray array = new JsonArray();
    array.add(1);
    array.add(2);

    IllegalStateException exception = assertThrows(IllegalStateException.class, array::getAsByte);
    assertThat(exception.getMessage()).contains("Array must have size 1");
  }

  // ==========================================================================
  // Tests for getAsCharacter()
  // ==========================================================================

  @Test
  @SuppressWarnings("deprecation")
  public void testGetAsCharacter() {
    JsonArray array = new JsonArray();
    array.add("A");

    assertThat(array.getAsCharacter()).isEqualTo('A');
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testGetAsCharacterEmptyArray() {
    JsonArray array = new JsonArray();

    IllegalStateException exception =
        assertThrows(IllegalStateException.class, array::getAsCharacter);
    assertThat(exception.getMessage()).contains("Array must have size 1");
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testGetAsCharacterMultipleElements() {
    JsonArray array = new JsonArray();
    array.add("A");
    array.add("B");

    IllegalStateException exception =
        assertThrows(IllegalStateException.class, array::getAsCharacter);
    assertThat(exception.getMessage()).contains("Array must have size 1");
  }

  // ==========================================================================
  // Tests for getAsShort()
  // ==========================================================================

  @Test
  public void testGetAsShort() {
    JsonArray array = new JsonArray();
    array.add((short) 1000);

    assertThat(array.getAsShort()).isEqualTo((short) 1000);
  }

  @Test
  public void testGetAsShortEmptyArray() {
    JsonArray array = new JsonArray();

    IllegalStateException exception = assertThrows(IllegalStateException.class, array::getAsShort);
    assertThat(exception.getMessage()).contains("Array must have size 1");
  }

  @Test
  public void testGetAsShortMultipleElements() {
    JsonArray array = new JsonArray();
    array.add(1);
    array.add(2);

    IllegalStateException exception = assertThrows(IllegalStateException.class, array::getAsShort);
    assertThat(exception.getMessage()).contains("Array must have size 1");
  }

  // ==========================================================================
  // Tests for getAsBoolean()
  // ==========================================================================

  @Test
  public void testGetAsBooleanTrue() {
    JsonArray array = new JsonArray();
    array.add(true);

    assertThat(array.getAsBoolean()).isTrue();
  }

  @Test
  public void testGetAsBooleanFalse() {
    JsonArray array = new JsonArray();
    array.add(false);

    assertThat(array.getAsBoolean()).isFalse();
  }

  @Test
  public void testGetAsBooleanEmptyArray() {
    JsonArray array = new JsonArray();

    IllegalStateException exception =
        assertThrows(IllegalStateException.class, array::getAsBoolean);
    assertThat(exception.getMessage()).contains("Array must have size 1");
  }

  @Test
  public void testGetAsBooleanMultipleElements() {
    JsonArray array = new JsonArray();
    array.add(true);
    array.add(false);

    IllegalStateException exception =
        assertThrows(IllegalStateException.class, array::getAsBoolean);
    assertThat(exception.getMessage()).contains("Array must have size 1");
  }

  // ==========================================================================
  // Tests for asList()
  // ==========================================================================

  @Test
  public void testAsList() {
    JsonArray array = new JsonArray();
    array.add("first");
    array.add("second");

    List<JsonElement> list = array.asList();

    assertThat(list.size()).isEqualTo(2);
    assertThat(list.get(0).getAsString()).isEqualTo("first");
    assertThat(list.get(1).getAsString()).isEqualTo("second");
  }

  @Test
  public void testAsListEmpty() {
    JsonArray array = new JsonArray();
    List<JsonElement> list = array.asList();

    assertThat(list).isEmpty();
  }

  @Test
  public void testAsListMutable() {
    JsonArray array = new JsonArray();
    array.add("first");

    List<JsonElement> list = array.asList();
    list.add(new JsonPrimitive("second"));

    // Changes to list reflect in array
    assertThat(array.size()).isEqualTo(2);
    assertThat(array.get(1).getAsString()).isEqualTo("second");
  }

  @Test
  public void testAsListModificationReflectsInArray() {
    JsonArray array = new JsonArray();
    array.add("first");
    array.add("second");

    List<JsonElement> list = array.asList();
    list.remove(0);

    assertThat(array.size()).isEqualTo(1);
    assertThat(array.get(0).getAsString()).isEqualTo("second");
  }

  @Test
  public void testAsListNullNotAllowed() {
    JsonArray array = new JsonArray();
    List<JsonElement> list = array.asList();

    assertThrows(NullPointerException.class, () -> list.add(null));
  }

  @Test
  public void testArrayModificationReflectsInList() {
    JsonArray array = new JsonArray();
    array.add("first");

    List<JsonElement> list = array.asList();
    array.add("second");

    assertThat(list.size()).isEqualTo(2);
    assertThat(list.get(1).getAsString()).isEqualTo("second");
  }

  // ==========================================================================
  // Tests for equals(Object)
  // ==========================================================================

  @Test
  public void testEqualsSameInstance() {
    JsonArray array = new JsonArray();
    array.add("test");

    assertThat(array.equals(array)).isTrue();
  }

  @Test
  public void testEqualsEquivalentArrays() {
    JsonArray array1 = new JsonArray();
    array1.add("first");
    array1.add(42);

    JsonArray array2 = new JsonArray();
    array2.add("first");
    array2.add(42);

    assertThat(array1.equals(array2)).isTrue();
    assertThat(array2.equals(array1)).isTrue();
  }

  @Test
  public void testEqualsEmpty() {
    JsonArray array1 = new JsonArray();
    JsonArray array2 = new JsonArray();

    assertThat(array1.equals(array2)).isTrue();
  }

  @Test
  public void testEqualsDifferentElements() {
    JsonArray array1 = new JsonArray();
    array1.add("first");

    JsonArray array2 = new JsonArray();
    array2.add("second");

    assertThat(array1.equals(array2)).isFalse();
  }

  @Test
  public void testEqualsDifferentSize() {
    JsonArray array1 = new JsonArray();
    array1.add("first");

    JsonArray array2 = new JsonArray();
    array2.add("first");
    array2.add("second");

    assertThat(array1.equals(array2)).isFalse();
  }

  @Test
  public void testEqualsDifferentOrder() {
    JsonArray array1 = new JsonArray();
    array1.add("first");
    array1.add("second");

    JsonArray array2 = new JsonArray();
    array2.add("second");
    array2.add("first");

    assertThat(array1.equals(array2)).isFalse();
  }

  @Test
  public void testEqualsNull() {
    JsonArray array = new JsonArray();

    assertThat(array.equals(null)).isFalse();
  }

  @Test
  public void testEqualsDifferentType() {
    JsonArray array = new JsonArray();
    array.add("test");

    assertThat(array.equals("test")).isFalse();
    assertThat(array.equals(new JsonObject())).isFalse();
    assertThat(array.equals(new JsonPrimitive("test"))).isFalse();
  }

  @Test
  public void testEqualsWithNestedArrays() {
    JsonArray nested1 = new JsonArray();
    nested1.add("inner");

    JsonArray array1 = new JsonArray();
    array1.add(nested1);

    JsonArray nested2 = new JsonArray();
    nested2.add("inner");

    JsonArray array2 = new JsonArray();
    array2.add(nested2);

    assertThat(array1.equals(array2)).isTrue();
  }

  // ==========================================================================
  // Tests for hashCode()
  // ==========================================================================

  @Test
  public void testHashCodeEmpty() {
    JsonArray array1 = new JsonArray();
    JsonArray array2 = new JsonArray();

    assertThat(array1.hashCode()).isEqualTo(array2.hashCode());
  }

  @Test
  public void testHashCodeEqualArrays() {
    JsonArray array1 = new JsonArray();
    array1.add("test");
    array1.add(42);

    JsonArray array2 = new JsonArray();
    array2.add("test");
    array2.add(42);

    assertThat(array1.hashCode()).isEqualTo(array2.hashCode());
  }

  @Test
  public void testHashCodeDifferentArrays() {
    JsonArray array1 = new JsonArray();
    array1.add("first");

    JsonArray array2 = new JsonArray();
    array2.add("second");

    // Different arrays should (likely) have different hash codes
    // Not guaranteed, but very likely
    assertThat(array1.hashCode()).isNotEqualTo(array2.hashCode());
  }

  @Test
  public void testHashCodeConsistent() {
    JsonArray array = new JsonArray();
    array.add("test");
    array.add(42);

    int hashCode1 = array.hashCode();
    int hashCode2 = array.hashCode();

    assertThat(hashCode1).isEqualTo(hashCode2);
  }

  @Test
  public void testHashCodeWithNestedArrays() {
    JsonArray nested1 = new JsonArray();
    nested1.add("inner");

    JsonArray array1 = new JsonArray();
    array1.add(nested1);

    JsonArray nested2 = new JsonArray();
    nested2.add("inner");

    JsonArray array2 = new JsonArray();
    array2.add(nested2);

    assertThat(array1.hashCode()).isEqualTo(array2.hashCode());
  }

  // ==========================================================================
  // Additional edge case tests
  // ==========================================================================

  @Test
  public void testMixedTypes() {
    JsonArray array = new JsonArray();
    array.add("string");
    array.add(42);
    array.add(true);
    array.add(JsonNull.INSTANCE);
    array.add(new JsonObject());
    array.add(new JsonArray());

    assertThat(array.size()).isEqualTo(6);
    assertThat(array.get(0).getAsString()).isEqualTo("string");
    assertThat(array.get(1).getAsInt()).isEqualTo(42);
    assertThat(array.get(2).getAsBoolean()).isTrue();
    assertThat(array.get(3)).isEqualTo(JsonNull.INSTANCE);
    assertThat(array.get(4).isJsonObject()).isTrue();
    assertThat(array.get(5).isJsonArray()).isTrue();
  }

  @Test
  public void testDeeplyCopy() {
    // Create a 3-level nested structure
    JsonArray deepNested = new JsonArray();
    deepNested.add("deepest");

    JsonArray middleNested = new JsonArray();
    middleNested.add(deepNested);

    JsonArray original = new JsonArray();
    original.add(middleNested);

    JsonArray copy = original.deepCopy();

    // Verify all levels are deeply copied
    JsonArray copiedMiddle = copy.get(0).getAsJsonArray();
    JsonArray copiedDeep = copiedMiddle.get(0).getAsJsonArray();

    assertThat(copiedMiddle).isNotSameInstanceAs(middleNested);
    assertThat(copiedDeep).isNotSameInstanceAs(deepNested);
    assertThat(copiedDeep.get(0).getAsString()).isEqualTo("deepest");
  }

  @Test
  public void testConstructorWithLargeCapacity() {
    JsonArray array = new JsonArray(1000);
    assertThat(array.isEmpty()).isTrue();

    // Should be able to add elements without issue
    for (int i = 0; i < 1000; i++) {
      array.add(i);
    }
    assertThat(array.size()).isEqualTo(1000);
  }
}
