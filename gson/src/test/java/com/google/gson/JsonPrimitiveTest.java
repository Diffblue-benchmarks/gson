/*
 * Copyright (C) 2026 Google Inc.
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

import java.math.BigDecimal;
import java.math.BigInteger;
import org.junit.Test;

public class JsonPrimitiveTest {

  @Test
  public void testBooleanConstructor() {
    JsonPrimitive primitive = new JsonPrimitive(Boolean.TRUE);
    assertThat(primitive).isNotNull();
    assertThat(primitive.isBoolean()).isTrue();
  }

  @Test
  public void testBooleanConstructorWithFalse() {
    JsonPrimitive primitive = new JsonPrimitive(Boolean.FALSE);
    assertThat(primitive.isBoolean()).isTrue();
    assertThat(primitive.getAsBoolean()).isFalse();
  }

  @Test
  public void testNumberConstructor() {
    JsonPrimitive primitive = new JsonPrimitive(123);
    assertThat(primitive).isNotNull();
    assertThat(primitive.isNumber()).isTrue();
  }

  @Test
  public void testNumberConstructorWithLong() {
    JsonPrimitive primitive = new JsonPrimitive(123L);
    assertThat(primitive.isNumber()).isTrue();
    assertThat(primitive.getAsLong()).isEqualTo(123L);
  }

  @Test
  public void testStringConstructor() {
    JsonPrimitive primitive = new JsonPrimitive("test");
    assertThat(primitive).isNotNull();
    assertThat(primitive.isString()).isTrue();
  }

  @Test
  public void testStringConstructorWithEmptyString() {
    JsonPrimitive primitive = new JsonPrimitive("");
    assertThat(primitive.isString()).isTrue();
    assertThat(primitive.getAsString()).isEmpty();
  }

  @Test
  public void testCharacterConstructor() {
    JsonPrimitive primitive = new JsonPrimitive('a');
    assertThat(primitive).isNotNull();
    assertThat(primitive.isString()).isTrue();
    assertThat(primitive.getAsString()).isEqualTo("a");
  }

  @Test
  public void testCharacterConstructorWithSpecialChar() {
    JsonPrimitive primitive = new JsonPrimitive('\n');
    assertThat(primitive.isString()).isTrue();
    assertThat(primitive.getAsString()).isEqualTo("\n");
  }

  @Test
  public void testDeepCopy() {
    JsonPrimitive primitive = new JsonPrimitive("test");
    JsonPrimitive copy = primitive.deepCopy();
    assertThat(copy).isSameInstanceAs(primitive);
  }

  @Test
  public void testDeepCopyWithNumber() {
    JsonPrimitive primitive = new JsonPrimitive(123);
    JsonPrimitive copy = primitive.deepCopy();
    assertThat(copy).isSameInstanceAs(primitive);
  }

  @Test
  public void testIsBoolean() {
    JsonPrimitive boolPrimitive = new JsonPrimitive(true);
    assertThat(boolPrimitive.isBoolean()).isTrue();

    JsonPrimitive stringPrimitive = new JsonPrimitive("true");
    assertThat(stringPrimitive.isBoolean()).isFalse();
  }

  @Test
  public void testGetAsBooleanWithBoolean() {
    JsonPrimitive primitive = new JsonPrimitive(true);
    assertThat(primitive.getAsBoolean()).isTrue();
  }

  @Test
  public void testGetAsBooleanWithString() {
    JsonPrimitive primitive = new JsonPrimitive("true");
    assertThat(primitive.getAsBoolean()).isTrue();

    JsonPrimitive falsePrimitive = new JsonPrimitive("false");
    assertThat(falsePrimitive.getAsBoolean()).isFalse();
  }

  @Test
  public void testGetAsBooleanWithStringIgnoresCase() {
    JsonPrimitive primitive = new JsonPrimitive("TrUe");
    assertThat(primitive.getAsBoolean()).isTrue();
  }

  @Test
  public void testGetAsBooleanWithNumber() {
    JsonPrimitive primitive = new JsonPrimitive(1);
    assertThat(primitive.getAsBoolean()).isFalse();
  }

  @Test
  public void testIsNumber() {
    JsonPrimitive numPrimitive = new JsonPrimitive(123);
    assertThat(numPrimitive.isNumber()).isTrue();

    JsonPrimitive stringPrimitive = new JsonPrimitive("123");
    assertThat(stringPrimitive.isNumber()).isFalse();
  }

  @Test
  public void testGetAsNumberWithNumber() {
    JsonPrimitive primitive = new JsonPrimitive(123);
    assertThat(primitive.getAsNumber()).isEqualTo(123);
  }

  @Test
  public void testGetAsNumberWithString() {
    JsonPrimitive primitive = new JsonPrimitive("123");
    assertThat(primitive.getAsNumber().intValue()).isEqualTo(123);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetAsNumberWithBoolean() {
    JsonPrimitive primitive = new JsonPrimitive(true);
    primitive.getAsNumber();
  }

  @Test
  public void testIsString() {
    JsonPrimitive stringPrimitive = new JsonPrimitive("test");
    assertThat(stringPrimitive.isString()).isTrue();

    JsonPrimitive numPrimitive = new JsonPrimitive(123);
    assertThat(numPrimitive.isString()).isFalse();
  }

  @Test
  public void testGetAsStringWithString() {
    JsonPrimitive primitive = new JsonPrimitive("test");
    assertThat(primitive.getAsString()).isEqualTo("test");
  }

  @Test
  public void testGetAsStringWithNumber() {
    JsonPrimitive primitive = new JsonPrimitive(123);
    assertThat(primitive.getAsString()).isEqualTo("123");
  }

  @Test
  public void testGetAsStringWithBoolean() {
    JsonPrimitive primitive = new JsonPrimitive(true);
    assertThat(primitive.getAsString()).isEqualTo("true");
  }

  @Test
  public void testGetAsDouble() {
    JsonPrimitive primitive = new JsonPrimitive(123.45);
    assertThat(primitive.getAsDouble()).isEqualTo(123.45);
  }

  @Test
  public void testGetAsDoubleWithString() {
    JsonPrimitive primitive = new JsonPrimitive("123.45");
    assertThat(primitive.getAsDouble()).isEqualTo(123.45);
  }

  @Test
  public void testGetAsBigDecimal() {
    JsonPrimitive primitive = new JsonPrimitive(new BigDecimal("123.45"));
    assertThat(primitive.getAsBigDecimal()).isEqualTo(new BigDecimal("123.45"));
  }

  @Test
  public void testGetAsBigDecimalWithString() {
    JsonPrimitive primitive = new JsonPrimitive("123.45");
    assertThat(primitive.getAsBigDecimal()).isEqualTo(new BigDecimal("123.45"));
  }

  @Test
  public void testGetAsBigInteger() {
    JsonPrimitive primitive = new JsonPrimitive(new BigInteger("12345"));
    assertThat(primitive.getAsBigInteger()).isEqualTo(new BigInteger("12345"));
  }

  @Test
  public void testGetAsBigIntegerWithIntegralNumber() {
    JsonPrimitive primitive = new JsonPrimitive(123L);
    assertThat(primitive.getAsBigInteger()).isEqualTo(BigInteger.valueOf(123L));
  }

  @Test
  public void testGetAsBigIntegerWithString() {
    JsonPrimitive primitive = new JsonPrimitive("12345");
    assertThat(primitive.getAsBigInteger()).isEqualTo(new BigInteger("12345"));
  }

  @Test
  public void testGetAsFloat() {
    JsonPrimitive primitive = new JsonPrimitive(123.45f);
    assertThat(primitive.getAsFloat()).isEqualTo(123.45f);
  }

  @Test
  public void testGetAsFloatWithString() {
    JsonPrimitive primitive = new JsonPrimitive("123.45");
    assertThat(primitive.getAsFloat()).isEqualTo(123.45f);
  }

  @Test
  public void testGetAsLong() {
    JsonPrimitive primitive = new JsonPrimitive(123L);
    assertThat(primitive.getAsLong()).isEqualTo(123L);
  }

  @Test
  public void testGetAsLongWithString() {
    JsonPrimitive primitive = new JsonPrimitive("123");
    assertThat(primitive.getAsLong()).isEqualTo(123L);
  }

  @Test
  public void testGetAsShort() {
    JsonPrimitive primitive = new JsonPrimitive((short) 123);
    assertThat(primitive.getAsShort()).isEqualTo((short) 123);
  }

  @Test
  public void testGetAsShortWithString() {
    JsonPrimitive primitive = new JsonPrimitive("123");
    assertThat(primitive.getAsShort()).isEqualTo((short) 123);
  }

  @Test
  public void testGetAsInt() {
    JsonPrimitive primitive = new JsonPrimitive(123);
    assertThat(primitive.getAsInt()).isEqualTo(123);
  }

  @Test
  public void testGetAsIntWithString() {
    JsonPrimitive primitive = new JsonPrimitive("123");
    assertThat(primitive.getAsInt()).isEqualTo(123);
  }

  @Test
  public void testGetAsByte() {
    JsonPrimitive primitive = new JsonPrimitive((byte) 123);
    assertThat(primitive.getAsByte()).isEqualTo((byte) 123);
  }

  @Test
  public void testGetAsByteWithString() {
    JsonPrimitive primitive = new JsonPrimitive("123");
    assertThat(primitive.getAsByte()).isEqualTo((byte) 123);
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testGetAsCharacter() {
    JsonPrimitive primitive = new JsonPrimitive("abc");
    assertThat(primitive.getAsCharacter()).isEqualTo('a');
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testGetAsCharacterWithSingleChar() {
    JsonPrimitive primitive = new JsonPrimitive('x');
    assertThat(primitive.getAsCharacter()).isEqualTo('x');
  }

  @Test(expected = UnsupportedOperationException.class)
  @SuppressWarnings("deprecation")
  public void testGetAsCharacterWithEmptyString() {
    JsonPrimitive primitive = new JsonPrimitive("");
    primitive.getAsCharacter();
  }

  @Test
  public void testHashCodeWithBoolean() {
    JsonPrimitive primitive1 = new JsonPrimitive(true);
    JsonPrimitive primitive2 = new JsonPrimitive(true);
    assertThat(primitive1.hashCode()).isEqualTo(primitive2.hashCode());
  }

  @Test
  public void testHashCodeWithString() {
    JsonPrimitive primitive1 = new JsonPrimitive("test");
    JsonPrimitive primitive2 = new JsonPrimitive("test");
    assertThat(primitive1.hashCode()).isEqualTo(primitive2.hashCode());
  }

  @Test
  public void testHashCodeWithInteger() {
    JsonPrimitive primitive1 = new JsonPrimitive(123);
    JsonPrimitive primitive2 = new JsonPrimitive(123);
    assertThat(primitive1.hashCode()).isEqualTo(primitive2.hashCode());
  }

  @Test
  public void testHashCodeWithLong() {
    JsonPrimitive primitive1 = new JsonPrimitive(123L);
    JsonPrimitive primitive2 = new JsonPrimitive(123L);
    assertThat(primitive1.hashCode()).isEqualTo(primitive2.hashCode());
  }

  @Test
  public void testHashCodeWithDouble() {
    JsonPrimitive primitive1 = new JsonPrimitive(123.45);
    JsonPrimitive primitive2 = new JsonPrimitive(123.45);
    assertThat(primitive1.hashCode()).isEqualTo(primitive2.hashCode());
  }

  @Test
  public void testEqualsWithSameInstance() {
    JsonPrimitive primitive = new JsonPrimitive("test");
    assertThat(primitive.equals(primitive)).isTrue();
  }

  @Test
  public void testEqualsWithNull() {
    JsonPrimitive primitive = new JsonPrimitive("test");
    assertThat(primitive.equals(null)).isFalse();
  }

  @Test
  public void testEqualsWithDifferentClass() {
    JsonPrimitive primitive = new JsonPrimitive("test");
    assertThat(primitive.equals("test")).isFalse();
  }

  @Test
  public void testEqualsWithString() {
    JsonPrimitive primitive1 = new JsonPrimitive("test");
    JsonPrimitive primitive2 = new JsonPrimitive("test");
    JsonPrimitive primitive3 = new JsonPrimitive("other");
    assertThat(primitive1.equals(primitive2)).isTrue();
    assertThat(primitive1.equals(primitive3)).isFalse();
  }

  @Test
  public void testEqualsWithBoolean() {
    JsonPrimitive primitive1 = new JsonPrimitive(true);
    JsonPrimitive primitive2 = new JsonPrimitive(true);
    JsonPrimitive primitive3 = new JsonPrimitive(false);
    assertThat(primitive1.equals(primitive2)).isTrue();
    assertThat(primitive1.equals(primitive3)).isFalse();
  }

  @Test
  public void testEqualsWithIntegralNumbers() {
    JsonPrimitive primitive1 = new JsonPrimitive(123);
    JsonPrimitive primitive2 = new JsonPrimitive(123L);
    assertThat(primitive1.equals(primitive2)).isTrue();
  }

  @Test
  public void testEqualsWithDifferentIntegralNumbers() {
    JsonPrimitive primitive1 = new JsonPrimitive(123);
    JsonPrimitive primitive2 = new JsonPrimitive(456);
    assertThat(primitive1.equals(primitive2)).isFalse();
  }

  @Test
  public void testEqualsWithBigInteger() {
    JsonPrimitive primitive1 = new JsonPrimitive(new BigInteger("123"));
    JsonPrimitive primitive2 = new JsonPrimitive(123L);
    assertThat(primitive1.equals(primitive2)).isTrue();
  }

  @Test
  public void testEqualsWithBigDecimal() {
    JsonPrimitive primitive1 = new JsonPrimitive(new BigDecimal("123.00"));
    JsonPrimitive primitive2 = new JsonPrimitive(new BigDecimal("123.0"));
    assertThat(primitive1.equals(primitive2)).isTrue();
  }

  @Test
  public void testEqualsWithFloatingPointNumbers() {
    JsonPrimitive primitive1 = new JsonPrimitive(123.45);
    JsonPrimitive primitive2 = new JsonPrimitive(123.45);
    assertThat(primitive1.equals(primitive2)).isTrue();
  }

  @Test
  public void testEqualsWithDifferentFloatingPointNumbers() {
    JsonPrimitive primitive1 = new JsonPrimitive(123.45);
    JsonPrimitive primitive2 = new JsonPrimitive(123.46);
    assertThat(primitive1.equals(primitive2)).isFalse();
  }

  @Test
  public void testEqualsWithNaN() {
    JsonPrimitive primitive1 = new JsonPrimitive(Double.NaN);
    JsonPrimitive primitive2 = new JsonPrimitive(Double.NaN);
    assertThat(primitive1.equals(primitive2)).isTrue();
  }

  @Test
  public void testEqualsWithMixedIntegralAndFloatingPoint() {
    JsonPrimitive primitive1 = new JsonPrimitive(123);
    JsonPrimitive primitive2 = new JsonPrimitive(123.5);
    assertThat(primitive1.equals(primitive2)).isFalse();
  }
}
