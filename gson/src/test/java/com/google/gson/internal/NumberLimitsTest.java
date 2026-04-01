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

package com.google.gson.internal;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import org.junit.Test;

public final class NumberLimitsTest {

  @Test
  public void testParseBigDecimalSimple() {
    BigDecimal result = NumberLimits.parseBigDecimal("123.456");

    assertThat(result).isEqualTo(new BigDecimal("123.456"));
  }

  @Test
  public void testParseBigDecimalNegative() {
    BigDecimal result = NumberLimits.parseBigDecimal("-987.654");

    assertThat(result).isEqualTo(new BigDecimal("-987.654"));
  }

  @Test
  public void testParseBigDecimalStringTooLarge() {
    char[] chars = new char[10_001];
    Arrays.fill(chars, '1');
    String hugeNumber = new String(chars);

    NumberFormatException e =
        assertThrows(NumberFormatException.class, () -> NumberLimits.parseBigDecimal(hugeNumber));
    assertThat(e).hasMessageThat().contains("Number string too large");
  }

  @Test
  public void testParseBigDecimalUnsupportedScale() {
    // e.g. 1e-10000 has scale 10000 which is >= 10_000
    String hugeScale = "1e-10000";

    NumberFormatException e =
        assertThrows(NumberFormatException.class, () -> NumberLimits.parseBigDecimal(hugeScale));
    assertThat(e).hasMessageThat().contains("Number has unsupported scale");
  }

  @Test
  public void testParseBigDecimalPositiveLargeScale() {
    // scale of 1e10000 is -10000, abs is 10000 >= 10_000
    String hugePositiveScale = "1e10000";

    NumberFormatException e =
        assertThrows(
            NumberFormatException.class, () -> NumberLimits.parseBigDecimal(hugePositiveScale));
    assertThat(e).hasMessageThat().contains("Number has unsupported scale");
  }

  @Test
  public void testParseBigDecimalScaleJustWithinLimit() {
    // scale of 9999 should be fine
    String s = "1e-9999";
    BigDecimal result = NumberLimits.parseBigDecimal(s);

    assertThat(result).isEqualTo(new BigDecimal(s));
  }

  @Test
  public void testParseBigIntegerSimple() {
    BigInteger result = NumberLimits.parseBigInteger("12345678901234567890");

    assertThat(result).isEqualTo(new BigInteger("12345678901234567890"));
  }

  @Test
  public void testParseBigIntegerNegative() {
    BigInteger result = NumberLimits.parseBigInteger("-42");

    assertThat(result).isEqualTo(new BigInteger("-42"));
  }

  @Test
  public void testParseBigIntegerStringTooLarge() {
    char[] chars = new char[10_001];
    Arrays.fill(chars, '9');
    String hugeNumber = new String(chars);

    NumberFormatException e =
        assertThrows(NumberFormatException.class, () -> NumberLimits.parseBigInteger(hugeNumber));
    assertThat(e).hasMessageThat().contains("Number string too large");
  }
}
