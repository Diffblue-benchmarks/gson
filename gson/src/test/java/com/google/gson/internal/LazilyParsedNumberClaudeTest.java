/*
 * Copyright (C) 2011 Google Inc.
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
package com.google.gson.internal;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;

/**
 * Unit tests for {@link LazilyParsedNumber}.
 *
 * <p>Tests cover:
 * <ul>
 *   <li>Constructor - initializes with a string value</li>
 *   <li>{@link LazilyParsedNumber#intValue()} - parses integer with fallback to long and BigDecimal</li>
 *   <li>{@link LazilyParsedNumber#longValue()} - parses long with fallback to BigDecimal</li>
 *   <li>{@link LazilyParsedNumber#floatValue()} - parses float</li>
 *   <li>{@link LazilyParsedNumber#doubleValue()} - parses double</li>
 *   <li>{@link LazilyParsedNumber#toString()} - returns the original string value</li>
 *   <li>{@link LazilyParsedNumber#hashCode()} - hash code based on string value</li>
 *   <li>{@link LazilyParsedNumber#equals(Object)} - equality based on string value</li>
 * </ul>
 */
public class LazilyParsedNumberClaudeTest {

  // ==================== Tests for constructor ====================

  @Test
  public void testConstructor_withValidIntegerString() {
    LazilyParsedNumber number = new LazilyParsedNumber("42");
    assertThat(number.toString()).isEqualTo("42");
  }

  @Test
  public void testConstructor_withValidDecimalString() {
    LazilyParsedNumber number = new LazilyParsedNumber("3.14159");
    assertThat(number.toString()).isEqualTo("3.14159");
  }

  @Test
  public void testConstructor_withNegativeNumber() {
    LazilyParsedNumber number = new LazilyParsedNumber("-100");
    assertThat(number.toString()).isEqualTo("-100");
  }

  @Test
  public void testConstructor_withScientificNotation() {
    LazilyParsedNumber number = new LazilyParsedNumber("1.5E10");
    assertThat(number.toString()).isEqualTo("1.5E10");
  }

  // ==================== Tests for intValue() ====================

  @Test
  public void testIntValue_simpleInteger() {
    // First branch: Integer.parseInt succeeds
    LazilyParsedNumber number = new LazilyParsedNumber("42");
    assertThat(number.intValue()).isEqualTo(42);
  }

  @Test
  public void testIntValue_negativeInteger() {
    LazilyParsedNumber number = new LazilyParsedNumber("-42");
    assertThat(number.intValue()).isEqualTo(-42);
  }

  @Test
  public void testIntValue_maxInteger() {
    LazilyParsedNumber number = new LazilyParsedNumber(String.valueOf(Integer.MAX_VALUE));
    assertThat(number.intValue()).isEqualTo(Integer.MAX_VALUE);
  }

  @Test
  public void testIntValue_minInteger() {
    LazilyParsedNumber number = new LazilyParsedNumber(String.valueOf(Integer.MIN_VALUE));
    assertThat(number.intValue()).isEqualTo(Integer.MIN_VALUE);
  }

  @Test
  public void testIntValue_longFallback() {
    // Second branch: Integer.parseInt fails, Long.parseLong succeeds
    // Use a value that fits in long but not int
    long longValue = (long) Integer.MAX_VALUE + 1;
    LazilyParsedNumber number = new LazilyParsedNumber(String.valueOf(longValue));
    // Result is cast to int, so it wraps around
    assertThat(number.intValue()).isEqualTo((int) longValue);
  }

  @Test
  public void testIntValue_bigDecimalFallback_withDecimal() {
    // Third branch: Both parseInt and parseLong fail (decimal number)
    LazilyParsedNumber number = new LazilyParsedNumber("3.14");
    assertThat(number.intValue()).isEqualTo(3);
  }

  @Test
  public void testIntValue_bigDecimalFallback_withLargeNumber() {
    // Third branch: Both parseInt and parseLong fail (number exceeds long)
    String hugeNumber = "99999999999999999999";
    LazilyParsedNumber number = new LazilyParsedNumber(hugeNumber);
    // BigDecimal converts to int (will overflow/wrap)
    // This should not throw - just verify the method executes successfully
    var unused = number.intValue();
  }

  @Test
  public void testIntValue_scientificNotation() {
    // Falls back to BigDecimal parsing
    LazilyParsedNumber number = new LazilyParsedNumber("1E2");
    assertThat(number.intValue()).isEqualTo(100);
  }

  // ==================== Tests for longValue() ====================

  @Test
  public void testLongValue_simpleLong() {
    // First branch: Long.parseLong succeeds
    LazilyParsedNumber number = new LazilyParsedNumber("1234567890");
    assertThat(number.longValue()).isEqualTo(1234567890L);
  }

  @Test
  public void testLongValue_negativeLong() {
    LazilyParsedNumber number = new LazilyParsedNumber("-9876543210");
    assertThat(number.longValue()).isEqualTo(-9876543210L);
  }

  @Test
  public void testLongValue_maxLong() {
    LazilyParsedNumber number = new LazilyParsedNumber(String.valueOf(Long.MAX_VALUE));
    assertThat(number.longValue()).isEqualTo(Long.MAX_VALUE);
  }

  @Test
  public void testLongValue_minLong() {
    LazilyParsedNumber number = new LazilyParsedNumber(String.valueOf(Long.MIN_VALUE));
    assertThat(number.longValue()).isEqualTo(Long.MIN_VALUE);
  }

  @Test
  public void testLongValue_bigDecimalFallback_withDecimal() {
    // Second branch: Long.parseLong fails (decimal number)
    LazilyParsedNumber number = new LazilyParsedNumber("123.456");
    assertThat(number.longValue()).isEqualTo(123L);
  }

  @Test
  public void testLongValue_bigDecimalFallback_withVeryLargeNumber() {
    // Second branch: Long.parseLong fails (number exceeds long)
    String hugeNumber = "99999999999999999999";
    LazilyParsedNumber number = new LazilyParsedNumber(hugeNumber);
    // BigDecimal converts to long (will overflow/wrap)
    // This should not throw - just verify the method executes successfully
    var unused = number.longValue();
  }

  @Test
  public void testLongValue_scientificNotation() {
    // Falls back to BigDecimal parsing
    LazilyParsedNumber number = new LazilyParsedNumber("1.5E3");
    assertThat(number.longValue()).isEqualTo(1500L);
  }

  // ==================== Tests for floatValue() ====================

  @Test
  public void testFloatValue_simpleFloat() {
    LazilyParsedNumber number = new LazilyParsedNumber("3.14");
    assertThat(number.floatValue()).isWithin(0.001f).of(3.14f);
  }

  @Test
  public void testFloatValue_integer() {
    LazilyParsedNumber number = new LazilyParsedNumber("42");
    assertThat(number.floatValue()).isEqualTo(42.0f);
  }

  @Test
  public void testFloatValue_negativeFloat() {
    LazilyParsedNumber number = new LazilyParsedNumber("-2.5");
    assertThat(number.floatValue()).isEqualTo(-2.5f);
  }

  @Test
  public void testFloatValue_scientificNotation() {
    LazilyParsedNumber number = new LazilyParsedNumber("1.5E10");
    assertThat(number.floatValue()).isEqualTo(1.5E10f);
  }

  @Test
  public void testFloatValue_verySmallNumber() {
    LazilyParsedNumber number = new LazilyParsedNumber("1.5E-10");
    assertThat(number.floatValue()).isWithin(1E-15f).of(1.5E-10f);
  }

  // ==================== Tests for doubleValue() ====================

  @Test
  public void testDoubleValue_simpleDouble() {
    LazilyParsedNumber number = new LazilyParsedNumber("3.141592653589793");
    assertThat(number.doubleValue()).isWithin(1E-15).of(3.141592653589793);
  }

  @Test
  public void testDoubleValue_integer() {
    LazilyParsedNumber number = new LazilyParsedNumber("42");
    assertThat(number.doubleValue()).isEqualTo(42.0);
  }

  @Test
  public void testDoubleValue_negativeDouble() {
    LazilyParsedNumber number = new LazilyParsedNumber("-2.71828");
    assertThat(number.doubleValue()).isWithin(1E-10).of(-2.71828);
  }

  @Test
  public void testDoubleValue_scientificNotation() {
    LazilyParsedNumber number = new LazilyParsedNumber("6.022E23");
    assertThat(number.doubleValue()).isWithin(1E18).of(6.022E23);
  }

  @Test
  public void testDoubleValue_verySmallNumber() {
    LazilyParsedNumber number = new LazilyParsedNumber("1.6E-19");
    assertThat(number.doubleValue()).isWithin(1E-25).of(1.6E-19);
  }

  // ==================== Tests for toString() ====================

  @Test
  public void testToString_returnsOriginalValue() {
    String originalValue = "123.456789";
    LazilyParsedNumber number = new LazilyParsedNumber(originalValue);
    assertThat(number.toString()).isEqualTo(originalValue);
  }

  @Test
  public void testToString_preservesFormatting() {
    // Verify the original string representation is preserved
    LazilyParsedNumber number = new LazilyParsedNumber("007");
    assertThat(number.toString()).isEqualTo("007");
  }

  @Test
  public void testToString_preservesScientificNotation() {
    LazilyParsedNumber number = new LazilyParsedNumber("1.23E+45");
    assertThat(number.toString()).isEqualTo("1.23E+45");
  }

  // ==================== Tests for hashCode() ====================

  @Test
  public void testHashCode_sameValueSameHashCode() {
    LazilyParsedNumber number1 = new LazilyParsedNumber("42");
    LazilyParsedNumber number2 = new LazilyParsedNumber("42");
    assertThat(number1.hashCode()).isEqualTo(number2.hashCode());
  }

  @Test
  public void testHashCode_differentValuesDifferentHashCode() {
    LazilyParsedNumber number1 = new LazilyParsedNumber("42");
    LazilyParsedNumber number2 = new LazilyParsedNumber("43");
    // Note: Different strings typically have different hash codes
    assertThat(number1.hashCode()).isNotEqualTo(number2.hashCode());
  }

  @Test
  public void testHashCode_consistentWithStringHashCode() {
    String value = "123.456";
    LazilyParsedNumber number = new LazilyParsedNumber(value);
    assertThat(number.hashCode()).isEqualTo(value.hashCode());
  }

  @Test
  public void testHashCode_consistentAcrossMultipleCalls() {
    LazilyParsedNumber number = new LazilyParsedNumber("999");
    int firstHash = number.hashCode();
    int secondHash = number.hashCode();
    assertThat(firstHash).isEqualTo(secondHash);
  }

  // ==================== Tests for equals() ====================

  @Test
  public void testEquals_sameInstance() {
    // First branch: this == obj
    LazilyParsedNumber number = new LazilyParsedNumber("42");
    assertThat(number.equals(number)).isTrue();
  }

  @Test
  public void testEquals_sameValue() {
    // Second branch: obj is LazilyParsedNumber with same value
    LazilyParsedNumber number1 = new LazilyParsedNumber("42");
    LazilyParsedNumber number2 = new LazilyParsedNumber("42");
    assertThat(number1.equals(number2)).isTrue();
  }

  @Test
  public void testEquals_differentValues() {
    // Second branch: obj is LazilyParsedNumber with different value
    LazilyParsedNumber number1 = new LazilyParsedNumber("42");
    LazilyParsedNumber number2 = new LazilyParsedNumber("43");
    assertThat(number1.equals(number2)).isFalse();
  }

  @Test
  public void testEquals_null() {
    // Third branch: obj is null
    LazilyParsedNumber number = new LazilyParsedNumber("42");
    assertThat(number.equals(null)).isFalse();
  }

  @Test
  public void testEquals_differentType() {
    // Third branch: obj is not a LazilyParsedNumber
    LazilyParsedNumber number = new LazilyParsedNumber("42");
    assertThat(number.equals("42")).isFalse();
    assertThat(number.equals(42)).isFalse();
    assertThat(number.equals(42L)).isFalse();
    assertThat(number.equals(42.0)).isFalse();
  }

  @Test
  public void testEquals_numericallyEqualButDifferentString() {
    // "42" and "42.0" are numerically equal but have different string values
    LazilyParsedNumber number1 = new LazilyParsedNumber("42");
    LazilyParsedNumber number2 = new LazilyParsedNumber("42.0");
    assertThat(number1.equals(number2)).isFalse();
  }

  @Test
  public void testEquals_symmetric() {
    LazilyParsedNumber number1 = new LazilyParsedNumber("100");
    LazilyParsedNumber number2 = new LazilyParsedNumber("100");
    assertThat(number1.equals(number2)).isTrue();
    assertThat(number2.equals(number1)).isTrue();
  }

  @Test
  public void testEquals_transitive() {
    LazilyParsedNumber number1 = new LazilyParsedNumber("100");
    LazilyParsedNumber number2 = new LazilyParsedNumber("100");
    LazilyParsedNumber number3 = new LazilyParsedNumber("100");
    assertThat(number1.equals(number2)).isTrue();
    assertThat(number2.equals(number3)).isTrue();
    assertThat(number1.equals(number3)).isTrue();
  }

  // ==================== Tests for hashCode/equals contract ====================

  @Test
  public void testHashCodeEqualsContract_equalObjectsSameHashCode() {
    LazilyParsedNumber number1 = new LazilyParsedNumber("3.14159");
    LazilyParsedNumber number2 = new LazilyParsedNumber("3.14159");
    assertThat(number1.equals(number2)).isTrue();
    assertThat(number1.hashCode()).isEqualTo(number2.hashCode());
  }

  // ==================== Edge case tests ====================

  @Test
  public void testEdgeCase_zero() {
    LazilyParsedNumber number = new LazilyParsedNumber("0");
    assertThat(number.intValue()).isEqualTo(0);
    assertThat(number.longValue()).isEqualTo(0L);
    assertThat(number.floatValue()).isEqualTo(0.0f);
    assertThat(number.doubleValue()).isEqualTo(0.0);
    assertThat(number.toString()).isEqualTo("0");
  }

  @Test
  public void testEdgeCase_negativeZero() {
    LazilyParsedNumber number = new LazilyParsedNumber("-0");
    assertThat(number.intValue()).isEqualTo(0);
    assertThat(number.longValue()).isEqualTo(0L);
    // Note: -0.0f is a valid IEEE 754 representation
    assertThat(number.floatValue()).isEqualTo(-0.0f);
    assertThat(number.doubleValue()).isEqualTo(-0.0);
  }

  @Test
  public void testEdgeCase_leadingZeros() {
    LazilyParsedNumber number = new LazilyParsedNumber("007");
    // Integer.parseInt handles leading zeros
    assertThat(number.intValue()).isEqualTo(7);
    assertThat(number.toString()).isEqualTo("007");
  }

  @Test
  public void testEdgeCase_positiveInfinity() {
    LazilyParsedNumber number = new LazilyParsedNumber("Infinity");
    assertThat(number.floatValue()).isPositiveInfinity();
    assertThat(number.doubleValue()).isPositiveInfinity();
  }

  @Test
  public void testEdgeCase_negativeInfinity() {
    LazilyParsedNumber number = new LazilyParsedNumber("-Infinity");
    assertThat(number.floatValue()).isNegativeInfinity();
    assertThat(number.doubleValue()).isNegativeInfinity();
  }

  @Test
  public void testEdgeCase_nan() {
    LazilyParsedNumber number = new LazilyParsedNumber("NaN");
    assertThat(number.floatValue()).isNaN();
    assertThat(number.doubleValue()).isNaN();
  }
}
