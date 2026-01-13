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
 * Unit tests for {@link JsonPrimitive}.
 *
 * @author Claude
 */
public class JsonPrimitiveClaudeTest {

  // ========== Constructor Tests ==========

  // ----- Boolean Constructor Tests -----

  @Test
  public void testConstructor_withBooleanTrue_createsBooleanPrimitive() {
    JsonPrimitive primitive = new JsonPrimitive(Boolean.TRUE);
    assertThat(primitive.isBoolean()).isTrue();
    assertThat(primitive.getAsBoolean()).isTrue();
  }

  @Test
  public void testConstructor_withBooleanFalse_createsBooleanPrimitive() {
    JsonPrimitive primitive = new JsonPrimitive(Boolean.FALSE);
    assertThat(primitive.isBoolean()).isTrue();
    assertThat(primitive.getAsBoolean()).isFalse();
  }

  @Test
  public void testConstructor_withNullBoolean_throwsNullPointerException() {
    Boolean nullBool = null;
    assertThrows(NullPointerException.class, () -> new JsonPrimitive(nullBool));
  }

  // ----- Number Constructor Tests -----

  @Test
  public void testConstructor_withInteger_createsNumberPrimitive() {
    JsonPrimitive primitive = new JsonPrimitive(42);
    assertThat(primitive.isNumber()).isTrue();
    assertThat(primitive.getAsInt()).isEqualTo(42);
  }

  @Test
  public void testConstructor_withLong_createsNumberPrimitive() {
    JsonPrimitive primitive = new JsonPrimitive(123456789012345L);
    assertThat(primitive.isNumber()).isTrue();
    assertThat(primitive.getAsLong()).isEqualTo(123456789012345L);
  }

  @Test
  public void testConstructor_withDouble_createsNumberPrimitive() {
    JsonPrimitive primitive = new JsonPrimitive(3.14159);
    assertThat(primitive.isNumber()).isTrue();
    assertThat(primitive.getAsDouble()).isWithin(0.00001).of(3.14159);
  }

  @Test
  public void testConstructor_withFloat_createsNumberPrimitive() {
    JsonPrimitive primitive = new JsonPrimitive(2.718f);
    assertThat(primitive.isNumber()).isTrue();
    assertThat(primitive.getAsFloat()).isWithin(0.001f).of(2.718f);
  }

  @Test
  public void testConstructor_withByte_createsNumberPrimitive() {
    JsonPrimitive primitive = new JsonPrimitive((byte) 127);
    assertThat(primitive.isNumber()).isTrue();
    assertThat(primitive.getAsByte()).isEqualTo((byte) 127);
  }

  @Test
  public void testConstructor_withShort_createsNumberPrimitive() {
    JsonPrimitive primitive = new JsonPrimitive((short) 32000);
    assertThat(primitive.isNumber()).isTrue();
    assertThat(primitive.getAsShort()).isEqualTo((short) 32000);
  }

  @Test
  public void testConstructor_withBigDecimal_createsNumberPrimitive() {
    BigDecimal value = new BigDecimal("123.456789012345678901234567890");
    JsonPrimitive primitive = new JsonPrimitive(value);
    assertThat(primitive.isNumber()).isTrue();
    assertThat(primitive.getAsBigDecimal()).isEqualTo(value);
  }

  @Test
  public void testConstructor_withBigInteger_createsNumberPrimitive() {
    BigInteger value = new BigInteger("12345678901234567890123456789");
    JsonPrimitive primitive = new JsonPrimitive(value);
    assertThat(primitive.isNumber()).isTrue();
    assertThat(primitive.getAsBigInteger()).isEqualTo(value);
  }

  @Test
  public void testConstructor_withNullNumber_throwsNullPointerException() {
    Number nullNum = null;
    assertThrows(NullPointerException.class, () -> new JsonPrimitive(nullNum));
  }

  // ----- String Constructor Tests -----

  @Test
  public void testConstructor_withString_createsStringPrimitive() {
    JsonPrimitive primitive = new JsonPrimitive("hello");
    assertThat(primitive.isString()).isTrue();
    assertThat(primitive.getAsString()).isEqualTo("hello");
  }

  @Test
  public void testConstructor_withEmptyString_createsStringPrimitive() {
    JsonPrimitive primitive = new JsonPrimitive("");
    assertThat(primitive.isString()).isTrue();
    assertThat(primitive.getAsString()).isEmpty();
  }

  @Test
  public void testConstructor_withNullString_throwsNullPointerException() {
    String nullStr = null;
    assertThrows(NullPointerException.class, () -> new JsonPrimitive(nullStr));
  }

  // ----- Character Constructor Tests -----

  @Test
  public void testConstructor_withCharacter_createsStringPrimitive() {
    // Character is converted to a single-character String internally
    JsonPrimitive primitive = new JsonPrimitive('a');
    assertThat(primitive.isString()).isTrue();
    assertThat(primitive.getAsString()).isEqualTo("a");
  }

  @Test
  public void testConstructor_withCharacterUnicode_createsStringPrimitive() {
    JsonPrimitive primitive = new JsonPrimitive('\u00e9'); // é
    assertThat(primitive.isString()).isTrue();
    assertThat(primitive.getAsString()).isEqualTo("\u00e9");
  }

  @Test
  public void testConstructor_withNullCharacter_throwsNullPointerException() {
    Character nullChar = null;
    assertThrows(NullPointerException.class, () -> new JsonPrimitive(nullChar));
  }

  // ========== deepCopy Tests ==========

  @Test
  public void testDeepCopy_returnsSameInstance() {
    // JsonPrimitive is immutable, so deepCopy returns the same instance
    JsonPrimitive primitive = new JsonPrimitive("test");
    JsonPrimitive copy = primitive.deepCopy();
    assertThat(copy).isSameInstanceAs(primitive);
  }

  @Test
  public void testDeepCopy_withBoolean_returnsSameInstance() {
    JsonPrimitive primitive = new JsonPrimitive(true);
    JsonPrimitive copy = primitive.deepCopy();
    assertThat(copy).isSameInstanceAs(primitive);
  }

  @Test
  public void testDeepCopy_withNumber_returnsSameInstance() {
    JsonPrimitive primitive = new JsonPrimitive(42);
    JsonPrimitive copy = primitive.deepCopy();
    assertThat(copy).isSameInstanceAs(primitive);
  }

  // ========== isBoolean Tests ==========

  @Test
  public void testIsBoolean_onBooleanPrimitive_returnsTrue() {
    JsonPrimitive primitive = new JsonPrimitive(true);
    assertThat(primitive.isBoolean()).isTrue();
  }

  @Test
  public void testIsBoolean_onStringPrimitive_returnsFalse() {
    JsonPrimitive primitive = new JsonPrimitive("true");
    assertThat(primitive.isBoolean()).isFalse();
  }

  @Test
  public void testIsBoolean_onNumberPrimitive_returnsFalse() {
    JsonPrimitive primitive = new JsonPrimitive(1);
    assertThat(primitive.isBoolean()).isFalse();
  }

  // ========== getAsBoolean Tests ==========

  @Test
  public void testGetAsBoolean_onBooleanTrue_returnsTrue() {
    JsonPrimitive primitive = new JsonPrimitive(true);
    assertThat(primitive.getAsBoolean()).isTrue();
  }

  @Test
  public void testGetAsBoolean_onBooleanFalse_returnsFalse() {
    JsonPrimitive primitive = new JsonPrimitive(false);
    assertThat(primitive.getAsBoolean()).isFalse();
  }

  @Test
  public void testGetAsBoolean_onStringTrue_returnsTrue() {
    // Boolean.parseBoolean is case-insensitive
    JsonPrimitive primitive = new JsonPrimitive("true");
    assertThat(primitive.getAsBoolean()).isTrue();
  }

  @Test
  public void testGetAsBoolean_onStringTrueMixedCase_returnsTrue() {
    JsonPrimitive primitive = new JsonPrimitive("TrUe");
    assertThat(primitive.getAsBoolean()).isTrue();
  }

  @Test
  public void testGetAsBoolean_onStringFalse_returnsFalse() {
    JsonPrimitive primitive = new JsonPrimitive("false");
    assertThat(primitive.getAsBoolean()).isFalse();
  }

  @Test
  public void testGetAsBoolean_onStringOther_returnsFalse() {
    // Boolean.parseBoolean returns false for any non-"true" string
    JsonPrimitive primitive = new JsonPrimitive("yes");
    assertThat(primitive.getAsBoolean()).isFalse();
  }

  @Test
  public void testGetAsBoolean_onNumber_returnsFalse() {
    // Numbers are converted to string first, then parsed
    JsonPrimitive primitive = new JsonPrimitive(1);
    assertThat(primitive.getAsBoolean()).isFalse();
  }

  // ========== isNumber Tests ==========

  @Test
  public void testIsNumber_onNumberPrimitive_returnsTrue() {
    JsonPrimitive primitive = new JsonPrimitive(42);
    assertThat(primitive.isNumber()).isTrue();
  }

  @Test
  public void testIsNumber_onBooleanPrimitive_returnsFalse() {
    JsonPrimitive primitive = new JsonPrimitive(true);
    assertThat(primitive.isNumber()).isFalse();
  }

  @Test
  public void testIsNumber_onStringPrimitive_returnsFalse() {
    JsonPrimitive primitive = new JsonPrimitive("42");
    assertThat(primitive.isNumber()).isFalse();
  }

  // ========== getAsNumber Tests ==========

  @Test
  public void testGetAsNumber_onInteger_returnsNumber() {
    JsonPrimitive primitive = new JsonPrimitive(42);
    Number number = primitive.getAsNumber();
    assertThat(number.intValue()).isEqualTo(42);
  }

  @Test
  public void testGetAsNumber_onString_returnsLazilyParsedNumber() {
    JsonPrimitive primitive = new JsonPrimitive("123");
    Number number = primitive.getAsNumber();
    assertThat(number.intValue()).isEqualTo(123);
  }

  @Test
  public void testGetAsNumber_onBoolean_throwsUnsupportedOperationException() {
    JsonPrimitive primitive = new JsonPrimitive(true);
    assertThrows(UnsupportedOperationException.class, primitive::getAsNumber);
  }

  @Test
  public void testGetAsNumber_onInvalidString_throwsNumberFormatExceptionOnAccess() {
    JsonPrimitive primitive = new JsonPrimitive("not a number");
    Number number = primitive.getAsNumber(); // LazilyParsedNumber created
    // Exception thrown when accessing the value
    assertThrows(NumberFormatException.class, number::intValue);
  }

  // ========== isString Tests ==========

  @Test
  public void testIsString_onStringPrimitive_returnsTrue() {
    JsonPrimitive primitive = new JsonPrimitive("hello");
    assertThat(primitive.isString()).isTrue();
  }

  @Test
  public void testIsString_onCharacterPrimitive_returnsTrue() {
    // Character is stored as String
    JsonPrimitive primitive = new JsonPrimitive('a');
    assertThat(primitive.isString()).isTrue();
  }

  @Test
  public void testIsString_onBooleanPrimitive_returnsFalse() {
    JsonPrimitive primitive = new JsonPrimitive(true);
    assertThat(primitive.isString()).isFalse();
  }

  @Test
  public void testIsString_onNumberPrimitive_returnsFalse() {
    JsonPrimitive primitive = new JsonPrimitive(42);
    assertThat(primitive.isString()).isFalse();
  }

  // ========== getAsString Tests ==========

  @Test
  public void testGetAsString_onString_returnsString() {
    JsonPrimitive primitive = new JsonPrimitive("hello world");
    assertThat(primitive.getAsString()).isEqualTo("hello world");
  }

  @Test
  public void testGetAsString_onNumber_returnsStringRepresentation() {
    JsonPrimitive primitive = new JsonPrimitive(42);
    assertThat(primitive.getAsString()).isEqualTo("42");
  }

  @Test
  public void testGetAsString_onBoolean_returnsStringRepresentation() {
    JsonPrimitive primitiveTrue = new JsonPrimitive(true);
    JsonPrimitive primitiveFalse = new JsonPrimitive(false);
    assertThat(primitiveTrue.getAsString()).isEqualTo("true");
    assertThat(primitiveFalse.getAsString()).isEqualTo("false");
  }

  @Test
  public void testGetAsString_onDouble_returnsStringRepresentation() {
    JsonPrimitive primitive = new JsonPrimitive(3.14);
    assertThat(primitive.getAsString()).isEqualTo("3.14");
  }

  // ========== getAsDouble Tests ==========

  @Test
  public void testGetAsDouble_onDoublePrimitive_returnsDouble() {
    JsonPrimitive primitive = new JsonPrimitive(3.14159);
    assertThat(primitive.getAsDouble()).isWithin(0.00001).of(3.14159);
  }

  @Test
  public void testGetAsDouble_onIntegerPrimitive_returnsDouble() {
    JsonPrimitive primitive = new JsonPrimitive(42);
    assertThat(primitive.getAsDouble()).isEqualTo(42.0);
  }

  @Test
  public void testGetAsDouble_onStringPrimitive_parsesDouble() {
    JsonPrimitive primitive = new JsonPrimitive("3.14159");
    assertThat(primitive.getAsDouble()).isWithin(0.00001).of(3.14159);
  }

  @Test
  public void testGetAsDouble_onInvalidString_throwsNumberFormatException() {
    JsonPrimitive primitive = new JsonPrimitive("not a double");
    assertThrows(NumberFormatException.class, primitive::getAsDouble);
  }

  @Test
  public void testGetAsDouble_onNaN_returnsNaN() {
    JsonPrimitive primitive = new JsonPrimitive(Double.NaN);
    assertThat(Double.isNaN(primitive.getAsDouble())).isTrue();
  }

  @Test
  public void testGetAsDouble_onPositiveInfinity_returnsInfinity() {
    JsonPrimitive primitive = new JsonPrimitive(Double.POSITIVE_INFINITY);
    assertThat(primitive.getAsDouble()).isPositiveInfinity();
  }

  @Test
  public void testGetAsDouble_onNegativeInfinity_returnsNegativeInfinity() {
    JsonPrimitive primitive = new JsonPrimitive(Double.NEGATIVE_INFINITY);
    assertThat(primitive.getAsDouble()).isNegativeInfinity();
  }

  // ========== getAsBigDecimal Tests ==========

  @Test
  public void testGetAsBigDecimal_onBigDecimal_returnsBigDecimal() {
    BigDecimal expected = new BigDecimal("123.456789012345678901234567890");
    JsonPrimitive primitive = new JsonPrimitive(expected);
    assertThat(primitive.getAsBigDecimal()).isEqualTo(expected);
  }

  @Test
  public void testGetAsBigDecimal_onInteger_returnsBigDecimal() {
    JsonPrimitive primitive = new JsonPrimitive(42);
    assertThat(primitive.getAsBigDecimal()).isEqualTo(new BigDecimal("42"));
  }

  @Test
  public void testGetAsBigDecimal_onString_parsesBigDecimal() {
    JsonPrimitive primitive = new JsonPrimitive("123.456");
    assertThat(primitive.getAsBigDecimal()).isEqualTo(new BigDecimal("123.456"));
  }

  @Test
  public void testGetAsBigDecimal_onInvalidString_throwsNumberFormatException() {
    JsonPrimitive primitive = new JsonPrimitive("not a number");
    assertThrows(NumberFormatException.class, primitive::getAsBigDecimal);
  }

  // ========== getAsBigInteger Tests ==========

  @Test
  public void testGetAsBigInteger_onBigInteger_returnsBigInteger() {
    BigInteger expected = new BigInteger("12345678901234567890123456789");
    JsonPrimitive primitive = new JsonPrimitive(expected);
    assertThat(primitive.getAsBigInteger()).isEqualTo(expected);
  }

  @Test
  public void testGetAsBigInteger_onInteger_returnsBigInteger() {
    JsonPrimitive primitive = new JsonPrimitive(42);
    assertThat(primitive.getAsBigInteger()).isEqualTo(BigInteger.valueOf(42));
  }

  @Test
  public void testGetAsBigInteger_onLong_returnsBigInteger() {
    JsonPrimitive primitive = new JsonPrimitive(123456789012345L);
    assertThat(primitive.getAsBigInteger()).isEqualTo(BigInteger.valueOf(123456789012345L));
  }

  @Test
  public void testGetAsBigInteger_onShort_returnsBigInteger() {
    JsonPrimitive primitive = new JsonPrimitive((short) 1000);
    assertThat(primitive.getAsBigInteger()).isEqualTo(BigInteger.valueOf(1000));
  }

  @Test
  public void testGetAsBigInteger_onByte_returnsBigInteger() {
    JsonPrimitive primitive = new JsonPrimitive((byte) 100);
    assertThat(primitive.getAsBigInteger()).isEqualTo(BigInteger.valueOf(100));
  }

  @Test
  public void testGetAsBigInteger_onString_parsesBigInteger() {
    JsonPrimitive primitive = new JsonPrimitive("12345678901234567890");
    assertThat(primitive.getAsBigInteger()).isEqualTo(new BigInteger("12345678901234567890"));
  }

  @Test
  public void testGetAsBigInteger_onDouble_parsesThroughString() {
    // Non-integral numbers are parsed from string
    JsonPrimitive primitive = new JsonPrimitive(3.14);
    // Double's toString creates "3.14", which is not a valid BigInteger
    assertThrows(NumberFormatException.class, primitive::getAsBigInteger);
  }

  @Test
  public void testGetAsBigInteger_onInvalidString_throwsNumberFormatException() {
    JsonPrimitive primitive = new JsonPrimitive("not a number");
    assertThrows(NumberFormatException.class, primitive::getAsBigInteger);
  }

  // ========== getAsFloat Tests ==========

  @Test
  public void testGetAsFloat_onFloatPrimitive_returnsFloat() {
    JsonPrimitive primitive = new JsonPrimitive(3.14f);
    assertThat(primitive.getAsFloat()).isWithin(0.001f).of(3.14f);
  }

  @Test
  public void testGetAsFloat_onIntegerPrimitive_returnsFloat() {
    JsonPrimitive primitive = new JsonPrimitive(42);
    assertThat(primitive.getAsFloat()).isEqualTo(42.0f);
  }

  @Test
  public void testGetAsFloat_onStringPrimitive_parsesFloat() {
    JsonPrimitive primitive = new JsonPrimitive("3.14");
    assertThat(primitive.getAsFloat()).isWithin(0.01f).of(3.14f);
  }

  @Test
  public void testGetAsFloat_onInvalidString_throwsNumberFormatException() {
    JsonPrimitive primitive = new JsonPrimitive("not a float");
    assertThrows(NumberFormatException.class, primitive::getAsFloat);
  }

  // ========== getAsLong Tests ==========

  @Test
  public void testGetAsLong_onLongPrimitive_returnsLong() {
    JsonPrimitive primitive = new JsonPrimitive(123456789012345L);
    assertThat(primitive.getAsLong()).isEqualTo(123456789012345L);
  }

  @Test
  public void testGetAsLong_onIntegerPrimitive_returnsLong() {
    JsonPrimitive primitive = new JsonPrimitive(42);
    assertThat(primitive.getAsLong()).isEqualTo(42L);
  }

  @Test
  public void testGetAsLong_onStringPrimitive_parsesLong() {
    JsonPrimitive primitive = new JsonPrimitive("123456789012345");
    assertThat(primitive.getAsLong()).isEqualTo(123456789012345L);
  }

  @Test
  public void testGetAsLong_onInvalidString_throwsNumberFormatException() {
    JsonPrimitive primitive = new JsonPrimitive("not a long");
    assertThrows(NumberFormatException.class, primitive::getAsLong);
  }

  // ========== getAsShort Tests ==========

  @Test
  public void testGetAsShort_onShortPrimitive_returnsShort() {
    JsonPrimitive primitive = new JsonPrimitive((short) 12345);
    assertThat(primitive.getAsShort()).isEqualTo((short) 12345);
  }

  @Test
  public void testGetAsShort_onIntegerPrimitive_returnsShort() {
    JsonPrimitive primitive = new JsonPrimitive(100);
    assertThat(primitive.getAsShort()).isEqualTo((short) 100);
  }

  @Test
  public void testGetAsShort_onStringPrimitive_parsesShort() {
    JsonPrimitive primitive = new JsonPrimitive("32000");
    assertThat(primitive.getAsShort()).isEqualTo((short) 32000);
  }

  @Test
  public void testGetAsShort_onInvalidString_throwsNumberFormatException() {
    JsonPrimitive primitive = new JsonPrimitive("not a short");
    assertThrows(NumberFormatException.class, primitive::getAsShort);
  }

  // ========== getAsInt Tests ==========

  @Test
  public void testGetAsInt_onIntegerPrimitive_returnsInt() {
    JsonPrimitive primitive = new JsonPrimitive(42);
    assertThat(primitive.getAsInt()).isEqualTo(42);
  }

  @Test
  public void testGetAsInt_onNegativeInteger_returnsInt() {
    JsonPrimitive primitive = new JsonPrimitive(-100);
    assertThat(primitive.getAsInt()).isEqualTo(-100);
  }

  @Test
  public void testGetAsInt_onStringPrimitive_parsesInt() {
    JsonPrimitive primitive = new JsonPrimitive("12345");
    assertThat(primitive.getAsInt()).isEqualTo(12345);
  }

  @Test
  public void testGetAsInt_onInvalidString_throwsNumberFormatException() {
    JsonPrimitive primitive = new JsonPrimitive("not an int");
    assertThrows(NumberFormatException.class, primitive::getAsInt);
  }

  // ========== getAsByte Tests ==========

  @Test
  public void testGetAsByte_onBytePrimitive_returnsByte() {
    JsonPrimitive primitive = new JsonPrimitive((byte) 42);
    assertThat(primitive.getAsByte()).isEqualTo((byte) 42);
  }

  @Test
  public void testGetAsByte_onIntegerPrimitive_returnsByte() {
    JsonPrimitive primitive = new JsonPrimitive(100);
    assertThat(primitive.getAsByte()).isEqualTo((byte) 100);
  }

  @Test
  public void testGetAsByte_onStringPrimitive_parsesByte() {
    JsonPrimitive primitive = new JsonPrimitive("120");
    assertThat(primitive.getAsByte()).isEqualTo((byte) 120);
  }

  @Test
  public void testGetAsByte_onInvalidString_throwsNumberFormatException() {
    JsonPrimitive primitive = new JsonPrimitive("not a byte");
    assertThrows(NumberFormatException.class, primitive::getAsByte);
  }

  // ========== getAsCharacter Tests ==========

  @Test
  @SuppressWarnings("deprecation")
  public void testGetAsCharacter_onSingleCharString_returnsCharacter() {
    JsonPrimitive primitive = new JsonPrimitive("a");
    assertThat(primitive.getAsCharacter()).isEqualTo('a');
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testGetAsCharacter_onMultiCharString_returnsFirstCharacter() {
    JsonPrimitive primitive = new JsonPrimitive("hello");
    assertThat(primitive.getAsCharacter()).isEqualTo('h');
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testGetAsCharacter_onCharacterPrimitive_returnsCharacter() {
    JsonPrimitive primitive = new JsonPrimitive('z');
    assertThat(primitive.getAsCharacter()).isEqualTo('z');
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testGetAsCharacter_onNumberPrimitive_returnsFirstChar() {
    JsonPrimitive primitive = new JsonPrimitive(42);
    // Number is converted to string "42", first char is '4'
    assertThat(primitive.getAsCharacter()).isEqualTo('4');
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testGetAsCharacter_onBooleanPrimitive_returnsFirstChar() {
    JsonPrimitive primitive = new JsonPrimitive(true);
    // Boolean is converted to string "true", first char is 't'
    assertThat(primitive.getAsCharacter()).isEqualTo('t');
  }

  @Test
  public void testGetAsCharacter_onEmptyString_throwsUnsupportedOperationException() {
    JsonPrimitive primitive = new JsonPrimitive("");
    assertThrows(UnsupportedOperationException.class, primitive::getAsCharacter);
  }

  // ========== hashCode Tests ==========

  @Test
  public void testHashCode_equalPrimitivesHaveSameHashCode() {
    JsonPrimitive primitive1 = new JsonPrimitive(42);
    JsonPrimitive primitive2 = new JsonPrimitive(42);
    assertThat(primitive1.hashCode()).isEqualTo(primitive2.hashCode());
  }

  @Test
  public void testHashCode_sameIntegralValue_differentTypes_haveSameHashCode() {
    // Integral types with same value should have same hash code
    JsonPrimitive intPrimitive = new JsonPrimitive(42);
    JsonPrimitive longPrimitive = new JsonPrimitive(42L);
    assertThat(intPrimitive.hashCode()).isEqualTo(longPrimitive.hashCode());
  }

  @Test
  public void testHashCode_bigInteger_consistentWithLong() {
    JsonPrimitive bigIntPrimitive = new JsonPrimitive(BigInteger.valueOf(42));
    JsonPrimitive longPrimitive = new JsonPrimitive(42L);
    assertThat(bigIntPrimitive.hashCode()).isEqualTo(longPrimitive.hashCode());
  }

  @Test
  public void testHashCode_floatingPoint_consistentHashing() {
    JsonPrimitive double1 = new JsonPrimitive(3.14);
    JsonPrimitive double2 = new JsonPrimitive(3.14);
    assertThat(double1.hashCode()).isEqualTo(double2.hashCode());
  }

  @Test
  public void testHashCode_stringPrimitive_consistentHashing() {
    JsonPrimitive string1 = new JsonPrimitive("hello");
    JsonPrimitive string2 = new JsonPrimitive("hello");
    assertThat(string1.hashCode()).isEqualTo(string2.hashCode());
  }

  @Test
  public void testHashCode_booleanPrimitive_consistentHashing() {
    JsonPrimitive bool1 = new JsonPrimitive(true);
    JsonPrimitive bool2 = new JsonPrimitive(true);
    assertThat(bool1.hashCode()).isEqualTo(bool2.hashCode());
  }

  @Test
  public void testHashCode_shortPrimitive_consistentWithInt() {
    JsonPrimitive shortPrimitive = new JsonPrimitive((short) 42);
    JsonPrimitive intPrimitive = new JsonPrimitive(42);
    assertThat(shortPrimitive.hashCode()).isEqualTo(intPrimitive.hashCode());
  }

  @Test
  public void testHashCode_bytePrimitive_consistentWithInt() {
    JsonPrimitive bytePrimitive = new JsonPrimitive((byte) 42);
    JsonPrimitive intPrimitive = new JsonPrimitive(42);
    assertThat(bytePrimitive.hashCode()).isEqualTo(intPrimitive.hashCode());
  }

  // ========== equals Tests ==========

  @Test
  public void testEquals_sameInstance_returnsTrue() {
    JsonPrimitive primitive = new JsonPrimitive(42);
    assertThat(primitive.equals(primitive)).isTrue();
  }

  @Test
  public void testEquals_null_returnsFalse() {
    JsonPrimitive primitive = new JsonPrimitive(42);
    assertThat(primitive.equals(null)).isFalse();
  }

  @Test
  public void testEquals_differentClass_returnsFalse() {
    JsonPrimitive primitive = new JsonPrimitive(42);
    assertThat(primitive.equals("42")).isFalse();
  }

  @Test
  public void testEquals_sameIntValue_returnsTrue() {
    JsonPrimitive primitive1 = new JsonPrimitive(42);
    JsonPrimitive primitive2 = new JsonPrimitive(42);
    assertThat(primitive1.equals(primitive2)).isTrue();
  }

  @Test
  public void testEquals_differentIntValue_returnsFalse() {
    JsonPrimitive primitive1 = new JsonPrimitive(42);
    JsonPrimitive primitive2 = new JsonPrimitive(43);
    assertThat(primitive1.equals(primitive2)).isFalse();
  }

  @Test
  public void testEquals_intAndLong_sameValue_returnsTrue() {
    // Integral types with same value are equal
    JsonPrimitive intPrimitive = new JsonPrimitive(42);
    JsonPrimitive longPrimitive = new JsonPrimitive(42L);
    assertThat(intPrimitive.equals(longPrimitive)).isTrue();
  }

  @Test
  public void testEquals_intAndShort_sameValue_returnsTrue() {
    JsonPrimitive intPrimitive = new JsonPrimitive(42);
    JsonPrimitive shortPrimitive = new JsonPrimitive((short) 42);
    assertThat(intPrimitive.equals(shortPrimitive)).isTrue();
  }

  @Test
  public void testEquals_intAndByte_sameValue_returnsTrue() {
    JsonPrimitive intPrimitive = new JsonPrimitive(42);
    JsonPrimitive bytePrimitive = new JsonPrimitive((byte) 42);
    assertThat(intPrimitive.equals(bytePrimitive)).isTrue();
  }

  @Test
  public void testEquals_longAndBigInteger_sameValue_returnsTrue() {
    JsonPrimitive longPrimitive = new JsonPrimitive(42L);
    JsonPrimitive bigIntPrimitive = new JsonPrimitive(BigInteger.valueOf(42));
    assertThat(longPrimitive.equals(bigIntPrimitive)).isTrue();
  }

  @Test
  public void testEquals_bigInteger_sameValue_returnsTrue() {
    BigInteger value = new BigInteger("12345678901234567890");
    JsonPrimitive primitive1 = new JsonPrimitive(value);
    JsonPrimitive primitive2 = new JsonPrimitive(value);
    assertThat(primitive1.equals(primitive2)).isTrue();
  }

  @Test
  public void testEquals_bigIntegerAndOtherIntegral_differentValue_returnsFalse() {
    JsonPrimitive bigIntPrimitive = new JsonPrimitive(BigInteger.valueOf(42));
    JsonPrimitive intPrimitive = new JsonPrimitive(43);
    assertThat(bigIntPrimitive.equals(intPrimitive)).isFalse();
  }

  @Test
  public void testEquals_bigDecimal_sameValue_differentScale_returnsTrue() {
    // BigDecimal 0 and 0.00 should be equal (compareTo is used, not equals)
    JsonPrimitive primitive1 = new JsonPrimitive(new BigDecimal("0"));
    JsonPrimitive primitive2 = new JsonPrimitive(new BigDecimal("0.00"));
    assertThat(primitive1.equals(primitive2)).isTrue();
  }

  @Test
  public void testEquals_bigDecimal_differentValue_returnsFalse() {
    JsonPrimitive primitive1 = new JsonPrimitive(new BigDecimal("1.0"));
    JsonPrimitive primitive2 = new JsonPrimitive(new BigDecimal("2.0"));
    assertThat(primitive1.equals(primitive2)).isFalse();
  }

  @Test
  public void testEquals_doubleValues_sameValue_returnsTrue() {
    JsonPrimitive primitive1 = new JsonPrimitive(3.14);
    JsonPrimitive primitive2 = new JsonPrimitive(3.14);
    assertThat(primitive1.equals(primitive2)).isTrue();
  }

  @Test
  public void testEquals_doubleValues_differentValue_returnsFalse() {
    JsonPrimitive primitive1 = new JsonPrimitive(3.14);
    JsonPrimitive primitive2 = new JsonPrimitive(2.71);
    assertThat(primitive1.equals(primitive2)).isFalse();
  }

  @Test
  public void testEquals_nan_bothNaN_returnsTrue() {
    // NaN == NaN is false in Java, but equals should treat them as equal
    JsonPrimitive primitive1 = new JsonPrimitive(Double.NaN);
    JsonPrimitive primitive2 = new JsonPrimitive(Double.NaN);
    assertThat(primitive1.equals(primitive2)).isTrue();
  }

  @Test
  public void testEquals_floatNaN_bothNaN_returnsTrue() {
    JsonPrimitive primitive1 = new JsonPrimitive(Float.NaN);
    JsonPrimitive primitive2 = new JsonPrimitive(Float.NaN);
    assertThat(primitive1.equals(primitive2)).isTrue();
  }

  @Test
  public void testEquals_doubleAndFloat_sameValue_returnsTrue() {
    JsonPrimitive doublePrimitive = new JsonPrimitive(3.0);
    JsonPrimitive floatPrimitive = new JsonPrimitive(3.0f);
    assertThat(doublePrimitive.equals(floatPrimitive)).isTrue();
  }

  @Test
  public void testEquals_positiveAndNegativeZero_returnsTrue() {
    // Don't differentiate between -0.0 and +0.0
    JsonPrimitive positiveZero = new JsonPrimitive(0.0);
    JsonPrimitive negativeZero = new JsonPrimitive(-0.0);
    assertThat(positiveZero.equals(negativeZero)).isTrue();
  }

  @Test
  public void testEquals_sameStringValue_returnsTrue() {
    JsonPrimitive primitive1 = new JsonPrimitive("hello");
    JsonPrimitive primitive2 = new JsonPrimitive("hello");
    assertThat(primitive1.equals(primitive2)).isTrue();
  }

  @Test
  public void testEquals_differentStringValue_returnsFalse() {
    JsonPrimitive primitive1 = new JsonPrimitive("hello");
    JsonPrimitive primitive2 = new JsonPrimitive("world");
    assertThat(primitive1.equals(primitive2)).isFalse();
  }

  @Test
  public void testEquals_sameBooleanValue_returnsTrue() {
    JsonPrimitive primitive1 = new JsonPrimitive(true);
    JsonPrimitive primitive2 = new JsonPrimitive(true);
    assertThat(primitive1.equals(primitive2)).isTrue();
  }

  @Test
  public void testEquals_differentBooleanValue_returnsFalse() {
    JsonPrimitive primitive1 = new JsonPrimitive(true);
    JsonPrimitive primitive2 = new JsonPrimitive(false);
    assertThat(primitive1.equals(primitive2)).isFalse();
  }

  @Test
  public void testEquals_booleanAndString_returnsFalse() {
    JsonPrimitive boolPrimitive = new JsonPrimitive(true);
    JsonPrimitive stringPrimitive = new JsonPrimitive("true");
    assertThat(boolPrimitive.equals(stringPrimitive)).isFalse();
  }

  @Test
  public void testEquals_numberAndString_returnsFalse() {
    JsonPrimitive numberPrimitive = new JsonPrimitive(42);
    JsonPrimitive stringPrimitive = new JsonPrimitive("42");
    assertThat(numberPrimitive.equals(stringPrimitive)).isFalse();
  }

  @Test
  public void testEquals_characterPrimitive_sameChar_returnsTrue() {
    // Characters are stored as strings
    JsonPrimitive primitive1 = new JsonPrimitive('a');
    JsonPrimitive primitive2 = new JsonPrimitive('a');
    assertThat(primitive1.equals(primitive2)).isTrue();
  }

  @Test
  public void testEquals_characterAndString_sameValue_returnsTrue() {
    // Character is stored as single-char string
    JsonPrimitive charPrimitive = new JsonPrimitive('a');
    JsonPrimitive stringPrimitive = new JsonPrimitive("a");
    assertThat(charPrimitive.equals(stringPrimitive)).isTrue();
  }

  @Test
  public void testEquals_infinityValues_sameSign_returnsTrue() {
    JsonPrimitive posInf1 = new JsonPrimitive(Double.POSITIVE_INFINITY);
    JsonPrimitive posInf2 = new JsonPrimitive(Double.POSITIVE_INFINITY);
    assertThat(posInf1.equals(posInf2)).isTrue();

    JsonPrimitive negInf1 = new JsonPrimitive(Double.NEGATIVE_INFINITY);
    JsonPrimitive negInf2 = new JsonPrimitive(Double.NEGATIVE_INFINITY);
    assertThat(negInf1.equals(negInf2)).isTrue();
  }

  @Test
  public void testEquals_infinityValues_differentSign_returnsFalse() {
    JsonPrimitive posInf = new JsonPrimitive(Double.POSITIVE_INFINITY);
    JsonPrimitive negInf = new JsonPrimitive(Double.NEGATIVE_INFINITY);
    assertThat(posInf.equals(negInf)).isFalse();
  }

  // ========== Edge Cases ==========

  @Test
  public void testEmptyStringAsNumber_throwsNumberFormatException() {
    JsonPrimitive primitive = new JsonPrimitive("");
    assertThrows(NumberFormatException.class, primitive::getAsDouble);
  }

  @Test
  public void testGetAsBigDecimal_onDouble_parsesFromString() {
    // Double value is converted to string first, then parsed as BigDecimal
    JsonPrimitive primitive = new JsonPrimitive(3.14);
    BigDecimal result = primitive.getAsBigDecimal();
    assertThat(result).isEqualTo(new BigDecimal("3.14"));
  }

  @Test
  public void testGetAsNumber_onBigDecimal_returnsBigDecimal() {
    BigDecimal value = new BigDecimal("123.456");
    JsonPrimitive primitive = new JsonPrimitive(value);
    assertThat(primitive.getAsNumber()).isEqualTo(value);
  }

  @Test
  public void testLargeIntegralValues() {
    BigInteger largeValue = new BigInteger("99999999999999999999999999999999");
    JsonPrimitive primitive1 = new JsonPrimitive(largeValue);
    JsonPrimitive primitive2 = new JsonPrimitive(largeValue);
    assertThat(primitive1.equals(primitive2)).isTrue();
    assertThat(primitive1.hashCode()).isEqualTo(primitive2.hashCode());
  }

  @Test
  public void testNegativeIntegralValues() {
    JsonPrimitive primitive1 = new JsonPrimitive(-42);
    JsonPrimitive primitive2 = new JsonPrimitive(-42L);
    assertThat(primitive1.equals(primitive2)).isTrue();
  }

  @Test
  public void testMaxAndMinValues() {
    JsonPrimitive maxInt = new JsonPrimitive(Integer.MAX_VALUE);
    JsonPrimitive minInt = new JsonPrimitive(Integer.MIN_VALUE);
    assertThat(maxInt.getAsInt()).isEqualTo(Integer.MAX_VALUE);
    assertThat(minInt.getAsInt()).isEqualTo(Integer.MIN_VALUE);
  }

  @Test
  public void testWhitespaceString() {
    JsonPrimitive primitive = new JsonPrimitive("   ");
    assertThat(primitive.isString()).isTrue();
    assertThat(primitive.getAsString()).isEqualTo("   ");
  }

  @Test
  public void testSpecialCharactersInString() {
    JsonPrimitive primitive = new JsonPrimitive("hello\nworld\ttab");
    assertThat(primitive.getAsString()).isEqualTo("hello\nworld\ttab");
  }

  @Test
  public void testUnicodeString() {
    JsonPrimitive primitive = new JsonPrimitive("\u00e9\u00e0\u00fc");
    assertThat(primitive.getAsString()).isEqualTo("\u00e9\u00e0\u00fc");
  }
}
