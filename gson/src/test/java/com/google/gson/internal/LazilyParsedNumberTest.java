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

package com.google.gson.internal;

import static com.google.common.truth.Truth.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import org.junit.Test;

public class LazilyParsedNumberTest {

  @Test
  public void testConstructor() {
    LazilyParsedNumber number = new LazilyParsedNumber("123");
    assertThat(number).isNotNull();
    assertThat(number.toString()).isEqualTo("123");
  }

  @Test
  public void testIntValue() {
    LazilyParsedNumber number = new LazilyParsedNumber("123");
    assertThat(number.intValue()).isEqualTo(123);
  }

  @Test
  public void testIntValueWithLargeNumber() {
    LazilyParsedNumber number = new LazilyParsedNumber("2147483648");
    assertThat(number.intValue()).isEqualTo(-2147483648);
  }

  @Test
  public void testIntValueWithDecimal() {
    LazilyParsedNumber number = new LazilyParsedNumber("123.45");
    assertThat(number.intValue()).isEqualTo(123);
  }

  @Test
  public void testLongValue() {
    LazilyParsedNumber number = new LazilyParsedNumber("123456789");
    assertThat(number.longValue()).isEqualTo(123456789L);
  }

  @Test
  public void testLongValueWithDecimal() {
    LazilyParsedNumber number = new LazilyParsedNumber("123.45");
    assertThat(number.longValue()).isEqualTo(123L);
  }

  @Test
  public void testFloatValue() {
    LazilyParsedNumber number = new LazilyParsedNumber("123.45");
    assertThat(number.floatValue()).isEqualTo(123.45f);
  }

  @Test
  public void testDoubleValue() {
    LazilyParsedNumber number = new LazilyParsedNumber("123.45");
    assertThat(number.doubleValue()).isEqualTo(123.45);
  }

  @Test
  public void testToString() {
    LazilyParsedNumber number = new LazilyParsedNumber("123.45");
    assertThat(number.toString()).isEqualTo("123.45");
  }

  @Test
  public void testHashCode() {
    LazilyParsedNumber number1 = new LazilyParsedNumber("123");
    LazilyParsedNumber number2 = new LazilyParsedNumber("123");
    assertThat(number1.hashCode()).isEqualTo(number2.hashCode());
  }

  @Test
  public void testEquals() {
    LazilyParsedNumber number1 = new LazilyParsedNumber("123");
    LazilyParsedNumber number2 = new LazilyParsedNumber("123");
    LazilyParsedNumber number3 = new LazilyParsedNumber("456");

    assertThat(number1.equals(number1)).isTrue();
    assertThat(number1.equals(number2)).isTrue();
    assertThat(number1.equals(number3)).isFalse();
    assertThat(number1.equals(null)).isFalse();
    assertThat(number1.equals("123")).isFalse();
  }

  @Test
  public void testSerialization() throws Exception {
    LazilyParsedNumber number = new LazilyParsedNumber("123.45");

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(baos);
    oos.writeObject(number);
    oos.close();

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    ObjectInputStream ois = new ObjectInputStream(bais);
    Object deserialized = ois.readObject();
    ois.close();

    assertThat(deserialized).isInstanceOf(BigDecimal.class);
    assertThat(deserialized).isEqualTo(new BigDecimal("123.45"));
  }

  @Test
  public void testNegativeIntValue() {
    LazilyParsedNumber number = new LazilyParsedNumber("-123");
    assertThat(number.intValue()).isEqualTo(-123);
  }

  @Test
  public void testNegativeFloatValue() {
    LazilyParsedNumber number = new LazilyParsedNumber("-123.45");
    assertThat(number.floatValue()).isEqualTo(-123.45f);
  }

  @Test
  public void testNegativeDoubleValue() {
    LazilyParsedNumber number = new LazilyParsedNumber("-123.45");
    assertThat(number.doubleValue()).isEqualTo(-123.45);
  }

  @Test
  public void testZeroValue() {
    LazilyParsedNumber number = new LazilyParsedNumber("0");
    assertThat(number.intValue()).isEqualTo(0);
    assertThat(number.longValue()).isEqualTo(0L);
    assertThat(number.floatValue()).isEqualTo(0.0f);
    assertThat(number.doubleValue()).isEqualTo(0.0);
  }
}
