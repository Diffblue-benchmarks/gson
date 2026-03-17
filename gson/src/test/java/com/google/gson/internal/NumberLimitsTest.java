/*
 * Copyright (C) 2026 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.gson.internal;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.junit.Test;

public class NumberLimitsTest {

  @Test
  public void testPrivateConstructor() throws Exception {
    Constructor<NumberLimits> constructor = NumberLimits.class.getDeclaredConstructor();
    constructor.setAccessible(true);
    constructor.newInstance();
  }

  @Test
  public void testParseBigDecimalValid() {
    BigDecimal result = NumberLimits.parseBigDecimal("123.456");
    assertThat(result).isEqualTo(new BigDecimal("123.456"));
  }

  @Test
  public void testParseBigDecimalLargeNumber() {
    String largeNumber = "1" + "0".repeat(9000);
    BigDecimal result = NumberLimits.parseBigDecimal(largeNumber);
    assertThat(result).isEqualTo(new BigDecimal(largeNumber));
  }

  @Test
  public void testParseBigDecimalTooLong() {
    String tooLong = "1" + "0".repeat(10001);
    NumberFormatException exception =
        assertThrows(NumberFormatException.class, () -> NumberLimits.parseBigDecimal(tooLong));
    assertThat(exception.getMessage()).startsWith("Number string too large:");
  }

  @Test
  public void testParseBigDecimalUnsupportedScale() {
    String largeScaleNumber = "1E10000";
    NumberFormatException exception =
        assertThrows(
            NumberFormatException.class, () -> NumberLimits.parseBigDecimal(largeScaleNumber));
    assertThat(exception.getMessage()).startsWith("Number has unsupported scale:");
  }

  @Test
  public void testParseBigDecimalNegativeScaleLimit() {
    String negativeScaleNumber = "1E-10000";
    NumberFormatException exception =
        assertThrows(
            NumberFormatException.class, () -> NumberLimits.parseBigDecimal(negativeScaleNumber));
    assertThat(exception.getMessage()).startsWith("Number has unsupported scale:");
  }

  @Test
  public void testParseBigIntegerValid() {
    BigInteger result = NumberLimits.parseBigInteger("123456789");
    assertThat(result).isEqualTo(new BigInteger("123456789"));
  }

  @Test
  public void testParseBigIntegerLargeNumber() {
    String largeNumber = "1" + "0".repeat(9000);
    BigInteger result = NumberLimits.parseBigInteger(largeNumber);
    assertThat(result).isEqualTo(new BigInteger(largeNumber));
  }

  @Test
  public void testParseBigIntegerTooLong() {
    String tooLong = "1" + "0".repeat(10001);
    NumberFormatException exception =
        assertThrows(NumberFormatException.class, () -> NumberLimits.parseBigInteger(tooLong));
    assertThat(exception.getMessage()).startsWith("Number string too large:");
  }
}
