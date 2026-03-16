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

public class JsonPrimitiveTest {

  @Test
  public void testBooleanPrimitive() {
    JsonPrimitive jsonPrimitive = new JsonPrimitive(true);
    assertThat(jsonPrimitive.isBoolean()).isTrue();
    assertThat(jsonPrimitive.getAsBoolean()).isTrue();
    assertThat(jsonPrimitive.isNumber()).isFalse();
    assertThat(jsonPrimitive.isString()).isFalse();
  }

  @Test
  public void testBooleanPrimitive_false() {
    JsonPrimitive jsonPrimitive = new JsonPrimitive(false);
    assertThat(jsonPrimitive.isBoolean()).isTrue();
    assertThat(jsonPrimitive.getAsBoolean()).isFalse();
  }

  @Test
  public void testNumberPrimitive_integer() {
    JsonPrimitive jsonPrimitive = new JsonPrimitive(123);
    assertThat(jsonPrimitive.isNumber()).isTrue();
    assertThat(jsonPrimitive.getAsInt()).isEqualTo(123);
    assertThat(jsonPrimitive.getAsLong()).isEqualTo(123L);
    assertThat(jsonPrimitive.getAsDouble()).isEqualTo(123.0);
    assertThat(jsonPrimitive.isBoolean()).isFalse();
    assertThat(jsonPrimitive.isString()).isFalse();
  }

  @Test
  public void testNumberPrimitive_double() {
    JsonPrimitive jsonPrimitive = new JsonPrimitive(123.45);
    assertThat(jsonPrimitive.isNumber()).isTrue();
    assertThat(jsonPrimitive.getAsDouble()).isEqualTo(123.45);
    assertThat(jsonPrimitive.getAsFloat()).isEqualTo(123.45f);
  }

  @Test
  public void testNumberPrimitive_long() {
    JsonPrimitive jsonPrimitive = new JsonPrimitive(123456789L);
    assertThat(jsonPrimitive.isNumber()).isTrue();
    assertThat(jsonPrimitive.getAsLong()).isEqualTo(123456789L);
  }

  @Test
  public void testNumberPrimitive_bigInteger() {
    BigInteger bigInt = new BigInteger("12345678901234567890");
    JsonPrimitive jsonPrimitive = new JsonPrimitive(bigInt);
    assertThat(jsonPrimitive.isNumber()).isTrue();
    assertThat(jsonPrimitive.getAsBigInteger()).isEqualTo(bigInt);
  }

  @Test
  public void testNumberPrimitive_bigDecimal() {
    BigDecimal bigDec = new BigDecimal("123.456789");
    JsonPrimitive jsonPrimitive = new JsonPrimitive(bigDec);
    assertThat(jsonPrimitive.isNumber()).isTrue();
    assertThat(jsonPrimitive.getAsBigDecimal()).isEqualTo(bigDec);
  }

  @Test
  public void testStringPrimitive() {
    JsonPrimitive jsonPrimitive = new JsonPrimitive("hello");
    assertThat(jsonPrimitive.isString()).isTrue();
    assertThat(jsonPrimitive.getAsString()).isEqualTo("hello");
    assertThat(jsonPrimitive.isBoolean()).isFalse();
    assertThat(jsonPrimitive.isNumber()).isFalse();
  }

  @Test
  public void testStringPrimitive_empty() {
    JsonPrimitive jsonPrimitive = new JsonPrimitive("");
    assertThat(jsonPrimitive.isString()).isTrue();
    assertThat(jsonPrimitive.getAsString()).isEmpty();
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testCharacterPrimitive() {
    JsonPrimitive jsonPrimitive = new JsonPrimitive('a');
    assertThat(jsonPrimitive.isString()).isTrue();
    assertThat(jsonPrimitive.getAsString()).isEqualTo("a");
    assertThat(jsonPrimitive.getAsCharacter()).isEqualTo('a');
  }

  @Test
  public void testStringAsNumber() {
    JsonPrimitive jsonPrimitive = new JsonPrimitive("123");
    assertThat(jsonPrimitive.isString()).isTrue();
    assertThat(jsonPrimitive.getAsNumber().intValue()).isEqualTo(123);
    assertThat(jsonPrimitive.getAsInt()).isEqualTo(123);
  }

  @Test
  public void testStringAsBoolean_true() {
    JsonPrimitive jsonPrimitive = new JsonPrimitive("true");
    assertThat(jsonPrimitive.getAsBoolean()).isTrue();
  }

  @Test
  public void testStringAsBoolean_false() {
    JsonPrimitive jsonPrimitive = new JsonPrimitive("false");
    assertThat(jsonPrimitive.getAsBoolean()).isFalse();
  }

  @Test
  public void testStringAsBoolean_invalidString() {
    JsonPrimitive jsonPrimitive = new JsonPrimitive("not-a-boolean");
    assertThat(jsonPrimitive.getAsBoolean()).isFalse();
  }

  @Test
  public void testGetAsByte() {
    JsonPrimitive jsonPrimitive = new JsonPrimitive(127);
    assertThat(jsonPrimitive.getAsByte()).isEqualTo((byte) 127);
  }

  @Test
  public void testGetAsShort() {
    JsonPrimitive jsonPrimitive = new JsonPrimitive(32000);
    assertThat(jsonPrimitive.getAsShort()).isEqualTo((short) 32000);
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testGetAsCharacter_emptyString_throwsException() {
    JsonPrimitive jsonPrimitive = new JsonPrimitive("");
    assertThrows(UnsupportedOperationException.class, () -> jsonPrimitive.getAsCharacter());
  }

  @Test
  public void testDeepCopyReturnsSameInstance() {
    JsonPrimitive original = new JsonPrimitive(123);
    JsonPrimitive copy = original.deepCopy();
    assertThat(copy).isSameInstanceAs(original);
  }

  @Test
  public void testIsJsonPrimitive() {
    JsonPrimitive jsonPrimitive = new JsonPrimitive(123);
    assertThat(jsonPrimitive.isJsonPrimitive()).isTrue();
    assertThat(jsonPrimitive.isJsonArray()).isFalse();
    assertThat(jsonPrimitive.isJsonObject()).isFalse();
    assertThat(jsonPrimitive.isJsonNull()).isFalse();
  }

  @Test
  public void testEqualsWithSameValue_integer() {
    JsonPrimitive p1 = new JsonPrimitive(123);
    JsonPrimitive p2 = new JsonPrimitive(123);
    assertThat(p1.equals(p2)).isTrue();
    assertThat(p1.hashCode()).isEqualTo(p2.hashCode());
  }

  @Test
  public void testEqualsWithSameValue_string() {
    JsonPrimitive p1 = new JsonPrimitive("hello");
    JsonPrimitive p2 = new JsonPrimitive("hello");
    assertThat(p1.equals(p2)).isTrue();
    assertThat(p1.hashCode()).isEqualTo(p2.hashCode());
  }

  @Test
  public void testEqualsWithSameValue_boolean() {
    JsonPrimitive p1 = new JsonPrimitive(true);
    JsonPrimitive p2 = new JsonPrimitive(true);
    assertThat(p1.equals(p2)).isTrue();
  }

  @Test
  public void testEqualsWithDifferentValue() {
    JsonPrimitive p1 = new JsonPrimitive(123);
    JsonPrimitive p2 = new JsonPrimitive(456);
    assertThat(p1.equals(p2)).isFalse();
  }

  @Test
  public void testEqualsWithDifferentTypes() {
    JsonPrimitive p1 = new JsonPrimitive(123);
    JsonPrimitive p2 = new JsonPrimitive("123");
    assertThat(p1.equals(p2)).isFalse();
  }

  @Test
  public void testEqualsWithNull() {
    JsonPrimitive p1 = new JsonPrimitive(123);
    assertThat(p1.equals(null)).isFalse();
  }

  @Test
  public void testEqualsIntegerAndLong() {
    JsonPrimitive intPrimitive = new JsonPrimitive(123);
    JsonPrimitive longPrimitive = new JsonPrimitive(123L);
    assertThat(intPrimitive.equals(longPrimitive)).isTrue();
  }

  @Test
  public void testEqualsDoubleAndBigDecimal() {
    JsonPrimitive doublePrimitive = new JsonPrimitive(123.0);
    JsonPrimitive bigDecPrimitive = new JsonPrimitive(new BigDecimal("123.00"));
    assertThat(doublePrimitive.equals(bigDecPrimitive)).isTrue();
  }

  @Test
  public void testConstructorWithNullBoolean_throwsException() {
    assertThrows(NullPointerException.class, () -> new JsonPrimitive((Boolean) null));
  }

  @Test
  public void testConstructorWithNullNumber_throwsException() {
    assertThrows(NullPointerException.class, () -> new JsonPrimitive((Number) null));
  }

  @Test
  public void testConstructorWithNullString_throwsException() {
    assertThrows(NullPointerException.class, () -> new JsonPrimitive((String) null));
  }

  @Test
  public void testConstructorWithNullCharacter_throwsException() {
    assertThrows(NullPointerException.class, () -> new JsonPrimitive((Character) null));
  }

  @Test
  public void testToString_number() {
    JsonPrimitive jsonPrimitive = new JsonPrimitive(123);
    assertThat(jsonPrimitive.toString()).isEqualTo("123");
  }

  @Test
  public void testToString_string() {
    JsonPrimitive jsonPrimitive = new JsonPrimitive("hello");
    assertThat(jsonPrimitive.toString()).isEqualTo("\"hello\"");
  }

  @Test
  public void testToString_boolean() {
    JsonPrimitive jsonPrimitive = new JsonPrimitive(true);
    assertThat(jsonPrimitive.toString()).isEqualTo("true");
  }

  @Test
  public void testGetAsNumber_fromBoolean_throwsException() {
    JsonPrimitive jsonPrimitive = new JsonPrimitive(true);
    assertThrows(UnsupportedOperationException.class, () -> jsonPrimitive.getAsNumber());
  }
}
