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
import org.junit.Test;

/**
 * Unit tests for {@link JsonElement}.
 *
 * <p>Since JsonElement is an abstract class, these tests verify the behavior through its concrete
 * subclasses: JsonObject, JsonArray, JsonPrimitive, and JsonNull.
 *
 * @author Claude
 */
public class JsonElementClaudeTest {

  // ========== isJsonArray Tests ==========

  @Test
  public void testIsJsonArray_onJsonArray_returnsTrue() {
    JsonElement element = new JsonArray();
    assertThat(element.isJsonArray()).isTrue();
  }

  @Test
  public void testIsJsonArray_onJsonObject_returnsFalse() {
    JsonElement element = new JsonObject();
    assertThat(element.isJsonArray()).isFalse();
  }

  @Test
  public void testIsJsonArray_onJsonPrimitive_returnsFalse() {
    JsonElement element = new JsonPrimitive("test");
    assertThat(element.isJsonArray()).isFalse();
  }

  @Test
  public void testIsJsonArray_onJsonNull_returnsFalse() {
    JsonElement element = JsonNull.INSTANCE;
    assertThat(element.isJsonArray()).isFalse();
  }

  // ========== isJsonObject Tests ==========

  @Test
  public void testIsJsonObject_onJsonObject_returnsTrue() {
    JsonElement element = new JsonObject();
    assertThat(element.isJsonObject()).isTrue();
  }

  @Test
  public void testIsJsonObject_onJsonArray_returnsFalse() {
    JsonElement element = new JsonArray();
    assertThat(element.isJsonObject()).isFalse();
  }

  @Test
  public void testIsJsonObject_onJsonPrimitive_returnsFalse() {
    JsonElement element = new JsonPrimitive(42);
    assertThat(element.isJsonObject()).isFalse();
  }

  @Test
  public void testIsJsonObject_onJsonNull_returnsFalse() {
    JsonElement element = JsonNull.INSTANCE;
    assertThat(element.isJsonObject()).isFalse();
  }

  // ========== isJsonPrimitive Tests ==========

  @Test
  public void testIsJsonPrimitive_onJsonPrimitive_returnsTrue() {
    JsonElement element = new JsonPrimitive(true);
    assertThat(element.isJsonPrimitive()).isTrue();
  }

  @Test
  public void testIsJsonPrimitive_onJsonPrimitiveString_returnsTrue() {
    JsonElement element = new JsonPrimitive("hello");
    assertThat(element.isJsonPrimitive()).isTrue();
  }

  @Test
  public void testIsJsonPrimitive_onJsonPrimitiveNumber_returnsTrue() {
    JsonElement element = new JsonPrimitive(123);
    assertThat(element.isJsonPrimitive()).isTrue();
  }

  @Test
  public void testIsJsonPrimitive_onJsonObject_returnsFalse() {
    JsonElement element = new JsonObject();
    assertThat(element.isJsonPrimitive()).isFalse();
  }

  @Test
  public void testIsJsonPrimitive_onJsonArray_returnsFalse() {
    JsonElement element = new JsonArray();
    assertThat(element.isJsonPrimitive()).isFalse();
  }

  @Test
  public void testIsJsonPrimitive_onJsonNull_returnsFalse() {
    JsonElement element = JsonNull.INSTANCE;
    assertThat(element.isJsonPrimitive()).isFalse();
  }

  // ========== isJsonNull Tests ==========

  @Test
  public void testIsJsonNull_onJsonNull_returnsTrue() {
    JsonElement element = JsonNull.INSTANCE;
    assertThat(element.isJsonNull()).isTrue();
  }

  @Test
  public void testIsJsonNull_onNewJsonNull_returnsTrue() {
    @SuppressWarnings("deprecation")
    JsonElement element = new JsonNull();
    assertThat(element.isJsonNull()).isTrue();
  }

  @Test
  public void testIsJsonNull_onJsonObject_returnsFalse() {
    JsonElement element = new JsonObject();
    assertThat(element.isJsonNull()).isFalse();
  }

  @Test
  public void testIsJsonNull_onJsonArray_returnsFalse() {
    JsonElement element = new JsonArray();
    assertThat(element.isJsonNull()).isFalse();
  }

  @Test
  public void testIsJsonNull_onJsonPrimitive_returnsFalse() {
    JsonElement element = new JsonPrimitive("test");
    assertThat(element.isJsonNull()).isFalse();
  }

  // ========== getAsJsonObject Tests ==========

  @Test
  public void testGetAsJsonObject_onJsonObject_returnsSameInstance() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", "value");
    JsonElement element = obj;
    assertThat(element.getAsJsonObject()).isSameInstanceAs(obj);
  }

  @Test
  public void testGetAsJsonObject_onJsonArray_throwsIllegalStateException() {
    JsonElement element = new JsonArray();
    IllegalStateException exception =
        assertThrows(IllegalStateException.class, element::getAsJsonObject);
    assertThat(exception.getMessage()).contains("Not a JSON Object");
  }

  @Test
  public void testGetAsJsonObject_onJsonPrimitive_throwsIllegalStateException() {
    JsonElement element = new JsonPrimitive("test");
    IllegalStateException exception =
        assertThrows(IllegalStateException.class, element::getAsJsonObject);
    assertThat(exception.getMessage()).contains("Not a JSON Object");
  }

  @Test
  public void testGetAsJsonObject_onJsonNull_throwsIllegalStateException() {
    JsonElement element = JsonNull.INSTANCE;
    IllegalStateException exception =
        assertThrows(IllegalStateException.class, element::getAsJsonObject);
    assertThat(exception.getMessage()).contains("Not a JSON Object");
  }

  // ========== getAsJsonArray Tests ==========

  @Test
  public void testGetAsJsonArray_onJsonArray_returnsSameInstance() {
    JsonArray arr = new JsonArray();
    arr.add("element");
    JsonElement element = arr;
    assertThat(element.getAsJsonArray()).isSameInstanceAs(arr);
  }

  @Test
  public void testGetAsJsonArray_onJsonObject_throwsIllegalStateException() {
    JsonElement element = new JsonObject();
    IllegalStateException exception =
        assertThrows(IllegalStateException.class, element::getAsJsonArray);
    assertThat(exception.getMessage()).contains("Not a JSON Array");
  }

  @Test
  public void testGetAsJsonArray_onJsonPrimitive_throwsIllegalStateException() {
    JsonElement element = new JsonPrimitive(123);
    IllegalStateException exception =
        assertThrows(IllegalStateException.class, element::getAsJsonArray);
    assertThat(exception.getMessage()).contains("Not a JSON Array");
  }

  @Test
  public void testGetAsJsonArray_onJsonNull_throwsIllegalStateException() {
    JsonElement element = JsonNull.INSTANCE;
    IllegalStateException exception =
        assertThrows(IllegalStateException.class, element::getAsJsonArray);
    assertThat(exception.getMessage()).contains("Not a JSON Array");
  }

  // ========== getAsJsonPrimitive Tests ==========

  @Test
  public void testGetAsJsonPrimitive_onJsonPrimitive_returnsSameInstance() {
    JsonPrimitive prim = new JsonPrimitive("hello");
    JsonElement element = prim;
    assertThat(element.getAsJsonPrimitive()).isSameInstanceAs(prim);
  }

  @Test
  public void testGetAsJsonPrimitive_onBooleanPrimitive_returnsSameInstance() {
    JsonPrimitive prim = new JsonPrimitive(true);
    JsonElement element = prim;
    assertThat(element.getAsJsonPrimitive()).isSameInstanceAs(prim);
  }

  @Test
  public void testGetAsJsonPrimitive_onNumberPrimitive_returnsSameInstance() {
    JsonPrimitive prim = new JsonPrimitive(42);
    JsonElement element = prim;
    assertThat(element.getAsJsonPrimitive()).isSameInstanceAs(prim);
  }

  @Test
  public void testGetAsJsonPrimitive_onJsonObject_throwsIllegalStateException() {
    JsonElement element = new JsonObject();
    IllegalStateException exception =
        assertThrows(IllegalStateException.class, element::getAsJsonPrimitive);
    assertThat(exception.getMessage()).contains("Not a JSON Primitive");
  }

  @Test
  public void testGetAsJsonPrimitive_onJsonArray_throwsIllegalStateException() {
    JsonElement element = new JsonArray();
    IllegalStateException exception =
        assertThrows(IllegalStateException.class, element::getAsJsonPrimitive);
    assertThat(exception.getMessage()).contains("Not a JSON Primitive");
  }

  @Test
  public void testGetAsJsonPrimitive_onJsonNull_throwsIllegalStateException() {
    JsonElement element = JsonNull.INSTANCE;
    IllegalStateException exception =
        assertThrows(IllegalStateException.class, element::getAsJsonPrimitive);
    assertThat(exception.getMessage()).contains("Not a JSON Primitive");
  }

  // ========== getAsJsonNull Tests ==========

  @Test
  public void testGetAsJsonNull_onJsonNull_returnsSameInstance() {
    JsonElement element = JsonNull.INSTANCE;
    assertThat(element.getAsJsonNull()).isSameInstanceAs(JsonNull.INSTANCE);
  }

  @Test
  public void testGetAsJsonNull_onNewJsonNull_returnsInstance() {
    @SuppressWarnings("deprecation")
    JsonNull nullElement = new JsonNull();
    JsonElement element = nullElement;
    assertThat(element.getAsJsonNull()).isSameInstanceAs(nullElement);
  }

  @Test
  public void testGetAsJsonNull_onJsonObject_throwsIllegalStateException() {
    JsonElement element = new JsonObject();
    IllegalStateException exception =
        assertThrows(IllegalStateException.class, element::getAsJsonNull);
    assertThat(exception.getMessage()).contains("Not a JSON Null");
  }

  @Test
  public void testGetAsJsonNull_onJsonArray_throwsIllegalStateException() {
    JsonElement element = new JsonArray();
    IllegalStateException exception =
        assertThrows(IllegalStateException.class, element::getAsJsonNull);
    assertThat(exception.getMessage()).contains("Not a JSON Null");
  }

  @Test
  public void testGetAsJsonNull_onJsonPrimitive_throwsIllegalStateException() {
    JsonElement element = new JsonPrimitive("test");
    IllegalStateException exception =
        assertThrows(IllegalStateException.class, element::getAsJsonNull);
    assertThat(exception.getMessage()).contains("Not a JSON Null");
  }

  // ========== getAsBoolean Tests ==========

  @Test
  public void testGetAsBoolean_onBooleanPrimitiveTrue_returnsTrue() {
    JsonElement element = new JsonPrimitive(true);
    assertThat(element.getAsBoolean()).isTrue();
  }

  @Test
  public void testGetAsBoolean_onBooleanPrimitiveFalse_returnsFalse() {
    JsonElement element = new JsonPrimitive(false);
    assertThat(element.getAsBoolean()).isFalse();
  }

  @Test
  public void testGetAsBoolean_onStringPrimitiveTrue_returnsTrue() {
    JsonElement element = new JsonPrimitive("true");
    assertThat(element.getAsBoolean()).isTrue();
  }

  @Test
  public void testGetAsBoolean_onStringPrimitiveTrueUppercase_returnsTrue() {
    JsonElement element = new JsonPrimitive("TRUE");
    assertThat(element.getAsBoolean()).isTrue();
  }

  @Test
  public void testGetAsBoolean_onStringPrimitiveFalse_returnsFalse() {
    JsonElement element = new JsonPrimitive("false");
    assertThat(element.getAsBoolean()).isFalse();
  }

  @Test
  public void testGetAsBoolean_onStringPrimitiveOther_returnsFalse() {
    JsonElement element = new JsonPrimitive("not a boolean");
    assertThat(element.getAsBoolean()).isFalse();
  }

  @Test
  public void testGetAsBoolean_onJsonObject_throwsUnsupportedOperationException() {
    JsonElement element = new JsonObject();
    assertThrows(UnsupportedOperationException.class, element::getAsBoolean);
  }

  @Test
  public void testGetAsBoolean_onJsonNull_throwsUnsupportedOperationException() {
    JsonElement element = JsonNull.INSTANCE;
    assertThrows(UnsupportedOperationException.class, element::getAsBoolean);
  }

  @Test
  public void testGetAsBoolean_onSingleElementArray_returnsValue() {
    JsonArray array = new JsonArray();
    array.add(true);
    assertThat(array.getAsBoolean()).isTrue();
  }

  // ========== getAsNumber Tests ==========

  @Test
  public void testGetAsNumber_onIntegerPrimitive_returnsNumber() {
    JsonElement element = new JsonPrimitive(42);
    Number number = element.getAsNumber();
    assertThat(number.intValue()).isEqualTo(42);
  }

  @Test
  public void testGetAsNumber_onLongPrimitive_returnsNumber() {
    JsonElement element = new JsonPrimitive(123456789012345L);
    Number number = element.getAsNumber();
    assertThat(number.longValue()).isEqualTo(123456789012345L);
  }

  @Test
  public void testGetAsNumber_onDoublePrimitive_returnsNumber() {
    JsonElement element = new JsonPrimitive(3.14159);
    Number number = element.getAsNumber();
    assertThat(number.doubleValue()).isWithin(0.00001).of(3.14159);
  }

  @Test
  public void testGetAsNumber_onStringPrimitive_returnsLazyNumber() {
    JsonElement element = new JsonPrimitive("12345");
    Number number = element.getAsNumber();
    assertThat(number.intValue()).isEqualTo(12345);
  }

  @Test
  public void testGetAsNumber_onBooleanPrimitive_throwsUnsupportedOperationException() {
    JsonElement element = new JsonPrimitive(true);
    assertThrows(UnsupportedOperationException.class, element::getAsNumber);
  }

  @Test
  public void testGetAsNumber_onJsonObject_throwsUnsupportedOperationException() {
    JsonElement element = new JsonObject();
    assertThrows(UnsupportedOperationException.class, element::getAsNumber);
  }

  @Test
  public void testGetAsNumber_onJsonNull_throwsUnsupportedOperationException() {
    JsonElement element = JsonNull.INSTANCE;
    assertThrows(UnsupportedOperationException.class, element::getAsNumber);
  }

  @Test
  public void testGetAsNumber_onSingleElementArray_returnsNumber() {
    JsonArray array = new JsonArray();
    array.add(99);
    assertThat(array.getAsNumber().intValue()).isEqualTo(99);
  }

  // ========== getAsString Tests ==========

  @Test
  public void testGetAsString_onStringPrimitive_returnsString() {
    JsonElement element = new JsonPrimitive("hello world");
    assertThat(element.getAsString()).isEqualTo("hello world");
  }

  @Test
  public void testGetAsString_onEmptyStringPrimitive_returnsEmptyString() {
    JsonElement element = new JsonPrimitive("");
    assertThat(element.getAsString()).isEmpty();
  }

  @Test
  public void testGetAsString_onNumberPrimitive_returnsStringRepresentation() {
    JsonElement element = new JsonPrimitive(42);
    assertThat(element.getAsString()).isEqualTo("42");
  }

  @Test
  public void testGetAsString_onBooleanPrimitive_returnsStringRepresentation() {
    JsonElement element = new JsonPrimitive(true);
    assertThat(element.getAsString()).isEqualTo("true");
  }

  @Test
  public void testGetAsString_onBooleanPrimitiveFalse_returnsStringRepresentation() {
    JsonElement element = new JsonPrimitive(false);
    assertThat(element.getAsString()).isEqualTo("false");
  }

  @Test
  public void testGetAsString_onJsonObject_throwsUnsupportedOperationException() {
    JsonElement element = new JsonObject();
    assertThrows(UnsupportedOperationException.class, element::getAsString);
  }

  @Test
  public void testGetAsString_onJsonNull_throwsUnsupportedOperationException() {
    JsonElement element = JsonNull.INSTANCE;
    assertThrows(UnsupportedOperationException.class, element::getAsString);
  }

  @Test
  public void testGetAsString_onSingleElementArray_returnsString() {
    JsonArray array = new JsonArray();
    array.add("test");
    assertThat(array.getAsString()).isEqualTo("test");
  }

  // ========== getAsDouble Tests ==========

  @Test
  public void testGetAsDouble_onDoublePrimitive_returnsDouble() {
    JsonElement element = new JsonPrimitive(3.14159);
    assertThat(element.getAsDouble()).isWithin(0.00001).of(3.14159);
  }

  @Test
  public void testGetAsDouble_onIntegerPrimitive_returnsDouble() {
    JsonElement element = new JsonPrimitive(42);
    assertThat(element.getAsDouble()).isEqualTo(42.0);
  }

  @Test
  public void testGetAsDouble_onStringPrimitive_parsesDouble() {
    JsonElement element = new JsonPrimitive("3.14159");
    assertThat(element.getAsDouble()).isWithin(0.00001).of(3.14159);
  }

  @Test
  public void testGetAsDouble_onInvalidStringPrimitive_throwsNumberFormatException() {
    JsonElement element = new JsonPrimitive("not a number");
    assertThrows(NumberFormatException.class, element::getAsDouble);
  }

  @Test
  public void testGetAsDouble_onJsonObject_throwsUnsupportedOperationException() {
    JsonElement element = new JsonObject();
    assertThrows(UnsupportedOperationException.class, element::getAsDouble);
  }

  @Test
  public void testGetAsDouble_onJsonNull_throwsUnsupportedOperationException() {
    JsonElement element = JsonNull.INSTANCE;
    assertThrows(UnsupportedOperationException.class, element::getAsDouble);
  }

  @Test
  public void testGetAsDouble_onSingleElementArray_returnsDouble() {
    JsonArray array = new JsonArray();
    array.add(2.718);
    assertThat(array.getAsDouble()).isWithin(0.001).of(2.718);
  }

  // ========== getAsFloat Tests ==========

  @Test
  public void testGetAsFloat_onFloatPrimitive_returnsFloat() {
    JsonElement element = new JsonPrimitive(3.14f);
    assertThat(element.getAsFloat()).isWithin(0.001f).of(3.14f);
  }

  @Test
  public void testGetAsFloat_onIntegerPrimitive_returnsFloat() {
    JsonElement element = new JsonPrimitive(42);
    assertThat(element.getAsFloat()).isEqualTo(42.0f);
  }

  @Test
  public void testGetAsFloat_onStringPrimitive_parsesFloat() {
    JsonElement element = new JsonPrimitive("3.14");
    assertThat(element.getAsFloat()).isWithin(0.01f).of(3.14f);
  }

  @Test
  public void testGetAsFloat_onInvalidStringPrimitive_throwsNumberFormatException() {
    JsonElement element = new JsonPrimitive("not a float");
    assertThrows(NumberFormatException.class, element::getAsFloat);
  }

  @Test
  public void testGetAsFloat_onJsonObject_throwsUnsupportedOperationException() {
    JsonElement element = new JsonObject();
    assertThrows(UnsupportedOperationException.class, element::getAsFloat);
  }

  @Test
  public void testGetAsFloat_onJsonNull_throwsUnsupportedOperationException() {
    JsonElement element = JsonNull.INSTANCE;
    assertThrows(UnsupportedOperationException.class, element::getAsFloat);
  }

  @Test
  public void testGetAsFloat_onSingleElementArray_returnsFloat() {
    JsonArray array = new JsonArray();
    array.add(1.5f);
    assertThat(array.getAsFloat()).isWithin(0.01f).of(1.5f);
  }

  // ========== getAsLong Tests ==========

  @Test
  public void testGetAsLong_onLongPrimitive_returnsLong() {
    JsonElement element = new JsonPrimitive(123456789012345L);
    assertThat(element.getAsLong()).isEqualTo(123456789012345L);
  }

  @Test
  public void testGetAsLong_onIntegerPrimitive_returnsLong() {
    JsonElement element = new JsonPrimitive(42);
    assertThat(element.getAsLong()).isEqualTo(42L);
  }

  @Test
  public void testGetAsLong_onStringPrimitive_parsesLong() {
    JsonElement element = new JsonPrimitive("123456789012345");
    assertThat(element.getAsLong()).isEqualTo(123456789012345L);
  }

  @Test
  public void testGetAsLong_onInvalidStringPrimitive_throwsNumberFormatException() {
    JsonElement element = new JsonPrimitive("not a long");
    assertThrows(NumberFormatException.class, element::getAsLong);
  }

  @Test
  public void testGetAsLong_onJsonObject_throwsUnsupportedOperationException() {
    JsonElement element = new JsonObject();
    assertThrows(UnsupportedOperationException.class, element::getAsLong);
  }

  @Test
  public void testGetAsLong_onJsonNull_throwsUnsupportedOperationException() {
    JsonElement element = JsonNull.INSTANCE;
    assertThrows(UnsupportedOperationException.class, element::getAsLong);
  }

  @Test
  public void testGetAsLong_onSingleElementArray_returnsLong() {
    JsonArray array = new JsonArray();
    array.add(999L);
    assertThat(array.getAsLong()).isEqualTo(999L);
  }

  // ========== getAsInt Tests ==========

  @Test
  public void testGetAsInt_onIntegerPrimitive_returnsInt() {
    JsonElement element = new JsonPrimitive(42);
    assertThat(element.getAsInt()).isEqualTo(42);
  }

  @Test
  public void testGetAsInt_onNegativeIntegerPrimitive_returnsInt() {
    JsonElement element = new JsonPrimitive(-100);
    assertThat(element.getAsInt()).isEqualTo(-100);
  }

  @Test
  public void testGetAsInt_onStringPrimitive_parsesInt() {
    JsonElement element = new JsonPrimitive("12345");
    assertThat(element.getAsInt()).isEqualTo(12345);
  }

  @Test
  public void testGetAsInt_onInvalidStringPrimitive_throwsNumberFormatException() {
    JsonElement element = new JsonPrimitive("not an int");
    assertThrows(NumberFormatException.class, element::getAsInt);
  }

  @Test
  public void testGetAsInt_onJsonObject_throwsUnsupportedOperationException() {
    JsonElement element = new JsonObject();
    assertThrows(UnsupportedOperationException.class, element::getAsInt);
  }

  @Test
  public void testGetAsInt_onJsonNull_throwsUnsupportedOperationException() {
    JsonElement element = JsonNull.INSTANCE;
    assertThrows(UnsupportedOperationException.class, element::getAsInt);
  }

  @Test
  public void testGetAsInt_onSingleElementArray_returnsInt() {
    JsonArray array = new JsonArray();
    array.add(77);
    assertThat(array.getAsInt()).isEqualTo(77);
  }

  // ========== getAsByte Tests ==========

  @Test
  public void testGetAsByte_onBytePrimitive_returnsByte() {
    JsonElement element = new JsonPrimitive((byte) 42);
    assertThat(element.getAsByte()).isEqualTo((byte) 42);
  }

  @Test
  public void testGetAsByte_onIntegerPrimitive_returnsByte() {
    JsonElement element = new JsonPrimitive(100);
    assertThat(element.getAsByte()).isEqualTo((byte) 100);
  }

  @Test
  public void testGetAsByte_onStringPrimitive_parsesByte() {
    JsonElement element = new JsonPrimitive("120");
    assertThat(element.getAsByte()).isEqualTo((byte) 120);
  }

  @Test
  public void testGetAsByte_onInvalidStringPrimitive_throwsNumberFormatException() {
    JsonElement element = new JsonPrimitive("not a byte");
    assertThrows(NumberFormatException.class, element::getAsByte);
  }

  @Test
  public void testGetAsByte_onJsonObject_throwsUnsupportedOperationException() {
    JsonElement element = new JsonObject();
    assertThrows(UnsupportedOperationException.class, element::getAsByte);
  }

  @Test
  public void testGetAsByte_onJsonNull_throwsUnsupportedOperationException() {
    JsonElement element = JsonNull.INSTANCE;
    assertThrows(UnsupportedOperationException.class, element::getAsByte);
  }

  @Test
  public void testGetAsByte_onSingleElementArray_returnsByte() {
    JsonArray array = new JsonArray();
    array.add((byte) 55);
    assertThat(array.getAsByte()).isEqualTo((byte) 55);
  }

  // ========== getAsCharacter Tests ==========

  @Test
  public void testGetAsCharacter_onSingleCharacterString_returnsCharacter() {
    JsonElement element = new JsonPrimitive("a");
    @SuppressWarnings("deprecation")
    char c = element.getAsCharacter();
    assertThat(c).isEqualTo('a');
  }

  @Test
  public void testGetAsCharacter_onMultiCharacterString_returnsFirstCharacter() {
    JsonElement element = new JsonPrimitive("hello");
    @SuppressWarnings("deprecation")
    char c = element.getAsCharacter();
    assertThat(c).isEqualTo('h');
  }

  @Test
  public void testGetAsCharacter_onCharacterPrimitive_returnsCharacter() {
    JsonElement element = new JsonPrimitive('z');
    @SuppressWarnings("deprecation")
    char c = element.getAsCharacter();
    assertThat(c).isEqualTo('z');
  }

  @Test
  public void testGetAsCharacter_onNumberPrimitive_returnsFirstCharacter() {
    JsonElement element = new JsonPrimitive(42);
    @SuppressWarnings("deprecation")
    char c = element.getAsCharacter();
    assertThat(c).isEqualTo('4');
  }

  @Test
  public void testGetAsCharacter_onEmptyString_throwsUnsupportedOperationException() {
    JsonElement element = new JsonPrimitive("");
    assertThrows(UnsupportedOperationException.class, element::getAsCharacter);
  }

  @Test
  public void testGetAsCharacter_onJsonObject_throwsUnsupportedOperationException() {
    JsonElement element = new JsonObject();
    assertThrows(UnsupportedOperationException.class, element::getAsCharacter);
  }

  @Test
  public void testGetAsCharacter_onJsonNull_throwsUnsupportedOperationException() {
    JsonElement element = JsonNull.INSTANCE;
    assertThrows(UnsupportedOperationException.class, element::getAsCharacter);
  }

  @Test
  public void testGetAsCharacter_onSingleElementArray_returnsCharacter() {
    JsonArray array = new JsonArray();
    array.add("x");
    @SuppressWarnings("deprecation")
    char c = array.getAsCharacter();
    assertThat(c).isEqualTo('x');
  }

  // ========== getAsBigDecimal Tests ==========

  @Test
  public void testGetAsBigDecimal_onBigDecimalPrimitive_returnsBigDecimal() {
    BigDecimal expected = new BigDecimal("123.456789012345678901234567890");
    JsonElement element = new JsonPrimitive(expected);
    assertThat(element.getAsBigDecimal()).isEqualTo(expected);
  }

  @Test
  public void testGetAsBigDecimal_onIntegerPrimitive_returnsBigDecimal() {
    JsonElement element = new JsonPrimitive(42);
    assertThat(element.getAsBigDecimal()).isEqualTo(new BigDecimal("42"));
  }

  @Test
  public void testGetAsBigDecimal_onDoublePrimitive_returnsBigDecimal() {
    JsonElement element = new JsonPrimitive(3.14);
    assertThat(element.getAsBigDecimal()).isEqualTo(new BigDecimal("3.14"));
  }

  @Test
  public void testGetAsBigDecimal_onStringPrimitive_parsesBigDecimal() {
    JsonElement element = new JsonPrimitive("123.456");
    assertThat(element.getAsBigDecimal()).isEqualTo(new BigDecimal("123.456"));
  }

  @Test
  public void testGetAsBigDecimal_onInvalidStringPrimitive_throwsNumberFormatException() {
    JsonElement element = new JsonPrimitive("not a number");
    assertThrows(NumberFormatException.class, element::getAsBigDecimal);
  }

  @Test
  public void testGetAsBigDecimal_onJsonObject_throwsUnsupportedOperationException() {
    JsonElement element = new JsonObject();
    assertThrows(UnsupportedOperationException.class, element::getAsBigDecimal);
  }

  @Test
  public void testGetAsBigDecimal_onJsonNull_throwsUnsupportedOperationException() {
    JsonElement element = JsonNull.INSTANCE;
    assertThrows(UnsupportedOperationException.class, element::getAsBigDecimal);
  }

  @Test
  public void testGetAsBigDecimal_onSingleElementArray_returnsBigDecimal() {
    JsonArray array = new JsonArray();
    array.add(new BigDecimal("99.99"));
    assertThat(array.getAsBigDecimal()).isEqualTo(new BigDecimal("99.99"));
  }

  // ========== getAsBigInteger Tests ==========

  @Test
  public void testGetAsBigInteger_onBigIntegerPrimitive_returnsBigInteger() {
    BigInteger expected = new BigInteger("12345678901234567890123456789");
    JsonElement element = new JsonPrimitive(expected);
    assertThat(element.getAsBigInteger()).isEqualTo(expected);
  }

  @Test
  public void testGetAsBigInteger_onIntegerPrimitive_returnsBigInteger() {
    JsonElement element = new JsonPrimitive(42);
    assertThat(element.getAsBigInteger()).isEqualTo(BigInteger.valueOf(42));
  }

  @Test
  public void testGetAsBigInteger_onLongPrimitive_returnsBigInteger() {
    JsonElement element = new JsonPrimitive(123456789012345L);
    assertThat(element.getAsBigInteger()).isEqualTo(BigInteger.valueOf(123456789012345L));
  }

  @Test
  public void testGetAsBigInteger_onStringPrimitive_parsesBigInteger() {
    JsonElement element = new JsonPrimitive("12345678901234567890");
    assertThat(element.getAsBigInteger()).isEqualTo(new BigInteger("12345678901234567890"));
  }

  @Test
  public void testGetAsBigInteger_onInvalidStringPrimitive_throwsNumberFormatException() {
    JsonElement element = new JsonPrimitive("not a number");
    assertThrows(NumberFormatException.class, element::getAsBigInteger);
  }

  @Test
  public void testGetAsBigInteger_onJsonObject_throwsUnsupportedOperationException() {
    JsonElement element = new JsonObject();
    assertThrows(UnsupportedOperationException.class, element::getAsBigInteger);
  }

  @Test
  public void testGetAsBigInteger_onJsonNull_throwsUnsupportedOperationException() {
    JsonElement element = JsonNull.INSTANCE;
    assertThrows(UnsupportedOperationException.class, element::getAsBigInteger);
  }

  @Test
  public void testGetAsBigInteger_onSingleElementArray_returnsBigInteger() {
    JsonArray array = new JsonArray();
    array.add(new BigInteger("987654321"));
    assertThat(array.getAsBigInteger()).isEqualTo(new BigInteger("987654321"));
  }

  // ========== getAsShort Tests ==========

  @Test
  public void testGetAsShort_onShortPrimitive_returnsShort() {
    JsonElement element = new JsonPrimitive((short) 12345);
    assertThat(element.getAsShort()).isEqualTo((short) 12345);
  }

  @Test
  public void testGetAsShort_onIntegerPrimitive_returnsShort() {
    JsonElement element = new JsonPrimitive(100);
    assertThat(element.getAsShort()).isEqualTo((short) 100);
  }

  @Test
  public void testGetAsShort_onStringPrimitive_parsesShort() {
    JsonElement element = new JsonPrimitive("32000");
    assertThat(element.getAsShort()).isEqualTo((short) 32000);
  }

  @Test
  public void testGetAsShort_onInvalidStringPrimitive_throwsNumberFormatException() {
    JsonElement element = new JsonPrimitive("not a short");
    assertThrows(NumberFormatException.class, element::getAsShort);
  }

  @Test
  public void testGetAsShort_onJsonObject_throwsUnsupportedOperationException() {
    JsonElement element = new JsonObject();
    assertThrows(UnsupportedOperationException.class, element::getAsShort);
  }

  @Test
  public void testGetAsShort_onJsonNull_throwsUnsupportedOperationException() {
    JsonElement element = JsonNull.INSTANCE;
    assertThrows(UnsupportedOperationException.class, element::getAsShort);
  }

  @Test
  public void testGetAsShort_onSingleElementArray_returnsShort() {
    JsonArray array = new JsonArray();
    array.add((short) 444);
    assertThat(array.getAsShort()).isEqualTo((short) 444);
  }

  // ========== toString Tests ==========

  @Test
  public void testToString_onJsonObject_returnsJsonString() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", "value");
    assertThat(obj.toString()).isEqualTo("{\"key\":\"value\"}");
  }

  @Test
  public void testToString_onEmptyJsonObject_returnsEmptyObjectString() {
    JsonObject obj = new JsonObject();
    assertThat(obj.toString()).isEqualTo("{}");
  }

  @Test
  public void testToString_onJsonArray_returnsJsonString() {
    JsonArray arr = new JsonArray();
    arr.add("a");
    arr.add("b");
    assertThat(arr.toString()).isEqualTo("[\"a\",\"b\"]");
  }

  @Test
  public void testToString_onEmptyJsonArray_returnsEmptyArrayString() {
    JsonArray arr = new JsonArray();
    assertThat(arr.toString()).isEqualTo("[]");
  }

  @Test
  public void testToString_onStringPrimitive_returnsQuotedString() {
    JsonElement element = new JsonPrimitive("hello");
    assertThat(element.toString()).isEqualTo("\"hello\"");
  }

  @Test
  public void testToString_onStringPrimitiveWithQuotes_escapesQuotes() {
    JsonElement element = new JsonPrimitive("with \"quote\"");
    assertThat(element.toString()).isEqualTo("\"with \\\"quote\\\"\"");
  }

  @Test
  public void testToString_onIntegerPrimitive_returnsNumberString() {
    JsonElement element = new JsonPrimitive(42);
    assertThat(element.toString()).isEqualTo("42");
  }

  @Test
  public void testToString_onDoublePrimitive_returnsNumberString() {
    JsonElement element = new JsonPrimitive(3.14);
    assertThat(element.toString()).isEqualTo("3.14");
  }

  @Test
  public void testToString_onBooleanPrimitive_returnsBooleanString() {
    JsonElement trueElement = new JsonPrimitive(true);
    JsonElement falseElement = new JsonPrimitive(false);
    assertThat(trueElement.toString()).isEqualTo("true");
    assertThat(falseElement.toString()).isEqualTo("false");
  }

  @Test
  public void testToString_onJsonNull_returnsNullString() {
    assertThat(JsonNull.INSTANCE.toString()).isEqualTo("null");
  }

  @Test
  public void testToString_onNestedStructure_returnsCorrectJson() {
    JsonObject obj = new JsonObject();
    obj.add("nullValue", JsonNull.INSTANCE);
    JsonArray arr = new JsonArray();
    arr.add(1);
    obj.add("array", arr);
    assertThat(obj.toString()).isEqualTo("{\"nullValue\":null,\"array\":[1]}");
  }

  @Test
  public void testToString_onNaN_outputsNaN() {
    JsonElement element = new JsonPrimitive(Double.NaN);
    assertThat(element.toString()).isEqualTo("NaN");
  }

  @Test
  public void testToString_onPositiveInfinity_outputsInfinity() {
    JsonElement element = new JsonPrimitive(Double.POSITIVE_INFINITY);
    assertThat(element.toString()).isEqualTo("Infinity");
  }

  @Test
  public void testToString_onNegativeInfinity_outputsNegativeInfinity() {
    JsonElement element = new JsonPrimitive(Double.NEGATIVE_INFINITY);
    assertThat(element.toString()).isEqualTo("-Infinity");
  }

  @Test
  public void testToString_onSpecialCharacters_escapesCorrectly() {
    JsonElement element = new JsonPrimitive("line1\nline2\ttab");
    assertThat(element.toString()).isEqualTo("\"line1\\nline2\\ttab\"");
  }

  // ========== Edge Cases and Integration Tests ==========

  @Test
  public void testTypeCheckingMultipleTimes() {
    JsonElement element = new JsonPrimitive("test");
    // Verify type checking is idempotent
    assertThat(element.isJsonPrimitive()).isTrue();
    assertThat(element.isJsonPrimitive()).isTrue();
    assertThat(element.isJsonArray()).isFalse();
    assertThat(element.isJsonArray()).isFalse();
  }

  @Test
  public void testAllTypeChecksOnSameElement() {
    JsonObject obj = new JsonObject();
    assertThat(obj.isJsonObject()).isTrue();
    assertThat(obj.isJsonArray()).isFalse();
    assertThat(obj.isJsonPrimitive()).isFalse();
    assertThat(obj.isJsonNull()).isFalse();
  }

  @Test
  public void testChainedConversions() {
    JsonObject obj = new JsonObject();
    obj.addProperty("number", 42);

    JsonElement element = obj;
    JsonObject retrieved = element.getAsJsonObject();
    JsonElement numberElement = retrieved.get("number");
    int value = numberElement.getAsInt();

    assertThat(value).isEqualTo(42);
  }

  @Test
  public void testValueExtractionFromNestedArray() {
    JsonArray outer = new JsonArray();
    JsonArray inner = new JsonArray();
    inner.add(123);
    outer.add(inner);

    JsonElement innerElement = outer.get(0);
    JsonArray innerArray = innerElement.getAsJsonArray();
    int value = innerArray.get(0).getAsInt();

    assertThat(value).isEqualTo(123);
  }

  @Test
  public void testValueExtractionFromNestedObject() {
    JsonObject outer = new JsonObject();
    JsonObject inner = new JsonObject();
    inner.addProperty("value", "nested");
    outer.add("inner", inner);

    String value = outer.get("inner").getAsJsonObject().get("value").getAsString();
    assertThat(value).isEqualTo("nested");
  }

  @Test
  public void testMultipleGetAsCallsOnSameElement() {
    JsonElement element = new JsonPrimitive(42);
    // Should be able to call multiple times
    assertThat(element.getAsInt()).isEqualTo(42);
    assertThat(element.getAsLong()).isEqualTo(42L);
    assertThat(element.getAsDouble()).isEqualTo(42.0);
    assertThat(element.getAsString()).isEqualTo("42");
  }
}
