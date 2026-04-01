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

public final class JsonPrimitiveTest {

  @Test
  public void testConstructorBoolean() {
    JsonPrimitive p = new JsonPrimitive(true);
    assertThat(p.isBoolean()).isTrue();
    assertThat(p.getAsBoolean()).isTrue();
  }

  @Test
  public void testConstructorBooleanFalse() {
    JsonPrimitive p = new JsonPrimitive(false);
    assertThat(p.isBoolean()).isTrue();
    assertThat(p.getAsBoolean()).isFalse();
  }

  @Test
  public void testConstructorBooleanNull() {
    assertThrows(NullPointerException.class, () -> new JsonPrimitive((Boolean) null));
  }

  @Test
  public void testConstructorNumber() {
    JsonPrimitive p = new JsonPrimitive(42);
    assertThat(p.isNumber()).isTrue();
    assertThat(p.getAsInt()).isEqualTo(42);
  }

  @Test
  public void testConstructorNumberNull() {
    assertThrows(NullPointerException.class, () -> new JsonPrimitive((Number) null));
  }

  @Test
  public void testConstructorString() {
    JsonPrimitive p = new JsonPrimitive("hello");
    assertThat(p.isString()).isTrue();
    assertThat(p.getAsString()).isEqualTo("hello");
  }

  @Test
  public void testConstructorStringNull() {
    assertThrows(NullPointerException.class, () -> new JsonPrimitive((String) null));
  }

  @Test
  public void testConstructorCharacter() {
    JsonPrimitive p = new JsonPrimitive('x');
    assertThat(p.isString()).isTrue();
    assertThat(p.getAsString()).isEqualTo("x");
  }

  @Test
  public void testConstructorCharacterNull() {
    assertThrows(NullPointerException.class, () -> new JsonPrimitive((Character) null));
  }

  @Test
  public void testDeepCopy() {
    JsonPrimitive p = new JsonPrimitive("hello");
    assertThat(p.deepCopy()).isSameInstanceAs(p);
  }

  @Test
  public void testIsBoolean() {
    assertThat(new JsonPrimitive(true).isBoolean()).isTrue();
    assertThat(new JsonPrimitive(1).isBoolean()).isFalse();
    assertThat(new JsonPrimitive("true").isBoolean()).isFalse();
  }

  @Test
  public void testGetAsBooleanFromString() {
    assertThat(new JsonPrimitive("true").getAsBoolean()).isTrue();
    assertThat(new JsonPrimitive("TRUE").getAsBoolean()).isTrue();
    assertThat(new JsonPrimitive("false").getAsBoolean()).isFalse();
    assertThat(new JsonPrimitive("other").getAsBoolean()).isFalse();
  }

  @Test
  public void testIsNumber() {
    assertThat(new JsonPrimitive(1).isNumber()).isTrue();
    assertThat(new JsonPrimitive("1").isNumber()).isFalse();
    assertThat(new JsonPrimitive(true).isNumber()).isFalse();
  }

  @Test
  public void testGetAsNumberFromNumber() {
    JsonPrimitive p = new JsonPrimitive(3.14);
    assertThat(p.getAsNumber().doubleValue()).isEqualTo(3.14);
  }

  @Test
  public void testGetAsNumberFromString() {
    JsonPrimitive p = new JsonPrimitive("100");
    assertThat(p.getAsNumber().intValue()).isEqualTo(100);
  }

  @Test
  public void testGetAsNumberFromBooleanThrows() {
    JsonPrimitive p = new JsonPrimitive(true);
    assertThrows(UnsupportedOperationException.class, p::getAsNumber);
  }

  @Test
  public void testIsString() {
    assertThat(new JsonPrimitive("hello").isString()).isTrue();
    assertThat(new JsonPrimitive(1).isString()).isFalse();
    assertThat(new JsonPrimitive(true).isString()).isFalse();
  }

  @Test
  public void testGetAsStringFromString() {
    assertThat(new JsonPrimitive("hello").getAsString()).isEqualTo("hello");
  }

  @Test
  public void testGetAsStringFromNumber() {
    assertThat(new JsonPrimitive(42).getAsString()).isEqualTo("42");
  }

  @Test
  public void testGetAsStringFromBoolean() {
    assertThat(new JsonPrimitive(true).getAsString()).isEqualTo("true");
    assertThat(new JsonPrimitive(false).getAsString()).isEqualTo("false");
  }

  @Test
  public void testGetAsDoubleFromNumber() {
    assertThat(new JsonPrimitive(2.5).getAsDouble()).isEqualTo(2.5);
  }

  @Test
  public void testGetAsDoubleFromString() {
    assertThat(new JsonPrimitive("3.14").getAsDouble()).isEqualTo(3.14);
  }

  @Test
  public void testGetAsBigDecimalFromBigDecimal() {
    BigDecimal bd = new BigDecimal("1.23456789");
    assertThat(new JsonPrimitive(bd).getAsBigDecimal()).isEqualTo(bd);
  }

  @Test
  public void testGetAsBigDecimalFromString() {
    assertThat(new JsonPrimitive("1.23").getAsBigDecimal()).isEqualTo(new BigDecimal("1.23"));
  }

  @Test
  public void testGetAsBigIntegerFromBigInteger() {
    BigInteger bi = new BigInteger("123456789012345678901234567890");
    assertThat(new JsonPrimitive(bi).getAsBigInteger()).isEqualTo(bi);
  }

  @Test
  public void testGetAsBigIntegerFromIntegral() {
    assertThat(new JsonPrimitive(42L).getAsBigInteger()).isEqualTo(BigInteger.valueOf(42));
  }

  @Test
  public void testGetAsBigIntegerFromString() {
    assertThat(new JsonPrimitive("100").getAsBigInteger()).isEqualTo(BigInteger.valueOf(100));
  }

  @Test
  public void testGetAsFloat() {
    assertThat(new JsonPrimitive(1.5f).getAsFloat()).isEqualTo(1.5f);
    assertThat(new JsonPrimitive("2.5").getAsFloat()).isEqualTo(2.5f);
  }

  @Test
  public void testGetAsLong() {
    assertThat(new JsonPrimitive(100L).getAsLong()).isEqualTo(100L);
    assertThat(new JsonPrimitive("200").getAsLong()).isEqualTo(200L);
  }

  @Test
  public void testGetAsShort() {
    assertThat(new JsonPrimitive((short) 5).getAsShort()).isEqualTo((short) 5);
    assertThat(new JsonPrimitive("10").getAsShort()).isEqualTo((short) 10);
  }

  @Test
  public void testGetAsInt() {
    assertThat(new JsonPrimitive(7).getAsInt()).isEqualTo(7);
    assertThat(new JsonPrimitive("8").getAsInt()).isEqualTo(8);
  }

  @Test
  public void testGetAsByte() {
    assertThat(new JsonPrimitive((byte) 3).getAsByte()).isEqualTo((byte) 3);
    assertThat(new JsonPrimitive("4").getAsByte()).isEqualTo((byte) 4);
  }

  @SuppressWarnings("deprecation")
  @Test
  public void testGetAsCharacter() {
    assertThat(new JsonPrimitive('A').getAsCharacter()).isEqualTo('A');
    assertThat(new JsonPrimitive("hello").getAsCharacter()).isEqualTo('h');
  }

  @SuppressWarnings("deprecation")
  @Test
  public void testGetAsCharacterEmptyStringThrows() {
    JsonPrimitive p = new JsonPrimitive("");
    assertThrows(UnsupportedOperationException.class, p::getAsCharacter);
  }

  @Test
  public void testHashCodeBoolean() {
    JsonPrimitive p1 = new JsonPrimitive(true);
    JsonPrimitive p2 = new JsonPrimitive(true);
    assertThat(p1.hashCode()).isEqualTo(p2.hashCode());
  }

  @Test
  public void testHashCodeIntegral() {
    JsonPrimitive p = new JsonPrimitive(42L);
    long val = 42L;
    int expected = (int) (val ^ (val >>> 32));
    assertThat(p.hashCode()).isEqualTo(expected);
  }

  @Test
  public void testHashCodeDouble() {
    JsonPrimitive p = new JsonPrimitive(3.14);
    long bits = Double.doubleToLongBits(3.14);
    int expected = (int) (bits ^ (bits >>> 32));
    assertThat(p.hashCode()).isEqualTo(expected);
  }

  @Test
  public void testHashCodeString() {
    JsonPrimitive p = new JsonPrimitive("hello");
    assertThat(p.hashCode()).isEqualTo("hello".hashCode());
  }

  @Test
  public void testEqualsSameInstance() {
    JsonPrimitive p = new JsonPrimitive("hello");
    assertThat(p.equals(p)).isTrue();
  }

  @Test
  public void testEqualsNull() {
    assertThat(new JsonPrimitive("hello").equals(null)).isFalse();
  }

  @Test
  public void testEqualsOtherType() {
    assertThat(new JsonPrimitive("hello").equals("hello")).isFalse();
  }

  @Test
  public void testEqualsString() {
    assertThat(new JsonPrimitive("hello").equals(new JsonPrimitive("hello"))).isTrue();
    assertThat(new JsonPrimitive("hello").equals(new JsonPrimitive("world"))).isFalse();
  }

  @Test
  public void testEqualsBoolean() {
    assertThat(new JsonPrimitive(true).equals(new JsonPrimitive(true))).isTrue();
    assertThat(new JsonPrimitive(true).equals(new JsonPrimitive(false))).isFalse();
  }

  @Test
  public void testEqualsIntegral() {
    assertThat(new JsonPrimitive(42).equals(new JsonPrimitive(42L))).isTrue();
    assertThat(new JsonPrimitive(42).equals(new JsonPrimitive(43))).isFalse();
  }

  @Test
  public void testEqualsIntegralWithBigInteger() {
    assertThat(new JsonPrimitive(42).equals(new JsonPrimitive(BigInteger.valueOf(42)))).isTrue();
    assertThat(new JsonPrimitive(42).equals(new JsonPrimitive(BigInteger.valueOf(43)))).isFalse();
  }

  @Test
  public void testEqualsDouble() {
    assertThat(new JsonPrimitive(1.5).equals(new JsonPrimitive(1.5))).isTrue();
    assertThat(new JsonPrimitive(1.5).equals(new JsonPrimitive(2.5))).isFalse();
  }

  @Test
  public void testEqualsDoubleNaN() {
    assertThat(new JsonPrimitive(Double.NaN).equals(new JsonPrimitive(Double.NaN))).isTrue();
  }

  @Test
  public void testEqualsBigDecimal() {
    assertThat(new JsonPrimitive(new BigDecimal("1.0")).equals(new JsonPrimitive(new BigDecimal("1.00")))).isTrue();
    assertThat(new JsonPrimitive(new BigDecimal("1.0")).equals(new JsonPrimitive(new BigDecimal("2.0")))).isFalse();
  }
}
