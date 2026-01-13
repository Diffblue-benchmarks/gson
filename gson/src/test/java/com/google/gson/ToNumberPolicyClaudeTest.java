/*
 * Copyright (C) 2021 Google Inc.
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

import org.junit.Test;

/**
 * Unit tests for {@link ToNumberPolicy}.
 *
 * @author Claude
 */
public class ToNumberPolicyClaudeTest {

  // ========== values() Tests ==========

  @Test
  public void testValuesReturnsAllEnumConstants() {
    ToNumberPolicy[] values = ToNumberPolicy.values();
    assertThat(values).hasLength(4);
  }

  @Test
  public void testValuesContainsDouble() {
    ToNumberPolicy[] values = ToNumberPolicy.values();
    assertThat(values).asList().contains(ToNumberPolicy.DOUBLE);
  }

  @Test
  public void testValuesContainsLazilyParsedNumber() {
    ToNumberPolicy[] values = ToNumberPolicy.values();
    assertThat(values).asList().contains(ToNumberPolicy.LAZILY_PARSED_NUMBER);
  }

  @Test
  public void testValuesContainsLongOrDouble() {
    ToNumberPolicy[] values = ToNumberPolicy.values();
    assertThat(values).asList().contains(ToNumberPolicy.LONG_OR_DOUBLE);
  }

  @Test
  public void testValuesContainsBigDecimal() {
    ToNumberPolicy[] values = ToNumberPolicy.values();
    assertThat(values).asList().contains(ToNumberPolicy.BIG_DECIMAL);
  }

  @Test
  public void testValuesOrderMatchesDeclaration() {
    ToNumberPolicy[] values = ToNumberPolicy.values();
    assertThat(values[0]).isEqualTo(ToNumberPolicy.DOUBLE);
    assertThat(values[1]).isEqualTo(ToNumberPolicy.LAZILY_PARSED_NUMBER);
    assertThat(values[2]).isEqualTo(ToNumberPolicy.LONG_OR_DOUBLE);
    assertThat(values[3]).isEqualTo(ToNumberPolicy.BIG_DECIMAL);
  }

  @Test
  public void testValuesReturnsNewArrayEachTime() {
    ToNumberPolicy[] values1 = ToNumberPolicy.values();
    ToNumberPolicy[] values2 = ToNumberPolicy.values();
    assertThat(values1).isNotSameInstanceAs(values2);
  }

  @Test
  public void testValuesArrayContainsSameInstances() {
    ToNumberPolicy[] values1 = ToNumberPolicy.values();
    ToNumberPolicy[] values2 = ToNumberPolicy.values();
    for (int i = 0; i < values1.length; i++) {
      assertThat(values1[i]).isSameInstanceAs(values2[i]);
    }
  }

  // ========== valueOf() Tests ==========

  @Test
  public void testValueOfDouble() {
    ToNumberPolicy policy = ToNumberPolicy.valueOf("DOUBLE");
    assertThat(policy).isEqualTo(ToNumberPolicy.DOUBLE);
  }

  @Test
  public void testValueOfLazilyParsedNumber() {
    ToNumberPolicy policy = ToNumberPolicy.valueOf("LAZILY_PARSED_NUMBER");
    assertThat(policy).isEqualTo(ToNumberPolicy.LAZILY_PARSED_NUMBER);
  }

  @Test
  public void testValueOfLongOrDouble() {
    ToNumberPolicy policy = ToNumberPolicy.valueOf("LONG_OR_DOUBLE");
    assertThat(policy).isEqualTo(ToNumberPolicy.LONG_OR_DOUBLE);
  }

  @Test
  public void testValueOfBigDecimal() {
    ToNumberPolicy policy = ToNumberPolicy.valueOf("BIG_DECIMAL");
    assertThat(policy).isEqualTo(ToNumberPolicy.BIG_DECIMAL);
  }

  @Test
  public void testValueOfWithInvalidNameThrowsException() {
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          ToNumberPolicy.valueOf("INVALID");
        });
  }

  @Test
  public void testValueOfWithNullThrowsException() {
    assertThrows(
        NullPointerException.class,
        () -> {
          ToNumberPolicy.valueOf(null);
        });
  }

  @Test
  public void testValueOfIsCaseSensitive() {
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          ToNumberPolicy.valueOf("double");
        });
  }

  @Test
  public void testValueOfWithEmptyStringThrowsException() {
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          ToNumberPolicy.valueOf("");
        });
  }

  @Test
  public void testValueOfWithWhitespaceThrowsException() {
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          ToNumberPolicy.valueOf(" DOUBLE");
        });
  }

  @Test
  public void testValueOfWithTrailingWhitespaceThrowsException() {
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          ToNumberPolicy.valueOf("DOUBLE ");
        });
  }

  @Test
  public void testValueOfWithLowercaseUnderscoreThrowsException() {
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          ToNumberPolicy.valueOf("long_or_double");
        });
  }

  // ========== Enum Properties Tests ==========

  @Test
  public void testDoubleOrdinal() {
    assertThat(ToNumberPolicy.DOUBLE.ordinal()).isEqualTo(0);
  }

  @Test
  public void testLazilyParsedNumberOrdinal() {
    assertThat(ToNumberPolicy.LAZILY_PARSED_NUMBER.ordinal()).isEqualTo(1);
  }

  @Test
  public void testLongOrDoubleOrdinal() {
    assertThat(ToNumberPolicy.LONG_OR_DOUBLE.ordinal()).isEqualTo(2);
  }

  @Test
  public void testBigDecimalOrdinal() {
    assertThat(ToNumberPolicy.BIG_DECIMAL.ordinal()).isEqualTo(3);
  }

  @Test
  public void testDoubleName() {
    assertThat(ToNumberPolicy.DOUBLE.name()).isEqualTo("DOUBLE");
  }

  @Test
  public void testLazilyParsedNumberName() {
    assertThat(ToNumberPolicy.LAZILY_PARSED_NUMBER.name()).isEqualTo("LAZILY_PARSED_NUMBER");
  }

  @Test
  public void testLongOrDoubleName() {
    assertThat(ToNumberPolicy.LONG_OR_DOUBLE.name()).isEqualTo("LONG_OR_DOUBLE");
  }

  @Test
  public void testBigDecimalName() {
    assertThat(ToNumberPolicy.BIG_DECIMAL.name()).isEqualTo("BIG_DECIMAL");
  }

  // ========== Comparison Tests ==========

  @Test
  public void testSameEnumConstantIsEqual() {
    assertThat(ToNumberPolicy.DOUBLE).isEqualTo(ToNumberPolicy.DOUBLE);
    assertThat(ToNumberPolicy.LAZILY_PARSED_NUMBER).isEqualTo(ToNumberPolicy.LAZILY_PARSED_NUMBER);
    assertThat(ToNumberPolicy.LONG_OR_DOUBLE).isEqualTo(ToNumberPolicy.LONG_OR_DOUBLE);
    assertThat(ToNumberPolicy.BIG_DECIMAL).isEqualTo(ToNumberPolicy.BIG_DECIMAL);
  }

  @Test
  public void testDifferentEnumConstantsNotEqual() {
    assertThat(ToNumberPolicy.DOUBLE).isNotEqualTo(ToNumberPolicy.LAZILY_PARSED_NUMBER);
    assertThat(ToNumberPolicy.DOUBLE).isNotEqualTo(ToNumberPolicy.LONG_OR_DOUBLE);
    assertThat(ToNumberPolicy.DOUBLE).isNotEqualTo(ToNumberPolicy.BIG_DECIMAL);
    assertThat(ToNumberPolicy.LAZILY_PARSED_NUMBER).isNotEqualTo(ToNumberPolicy.LONG_OR_DOUBLE);
    assertThat(ToNumberPolicy.LAZILY_PARSED_NUMBER).isNotEqualTo(ToNumberPolicy.BIG_DECIMAL);
    assertThat(ToNumberPolicy.LONG_OR_DOUBLE).isNotEqualTo(ToNumberPolicy.BIG_DECIMAL);
  }

  @Test
  public void testEnumConstantsSameInstanceAcrossValues() {
    ToNumberPolicy[] values = ToNumberPolicy.values();
    assertThat(values[0]).isSameInstanceAs(ToNumberPolicy.DOUBLE);
    assertThat(values[1]).isSameInstanceAs(ToNumberPolicy.LAZILY_PARSED_NUMBER);
    assertThat(values[2]).isSameInstanceAs(ToNumberPolicy.LONG_OR_DOUBLE);
    assertThat(values[3]).isSameInstanceAs(ToNumberPolicy.BIG_DECIMAL);
  }

  // ========== ToNumberStrategy Interface Implementation Tests ==========

  @Test
  public void testDoubleImplementsToNumberStrategy() {
    assertThat(ToNumberPolicy.DOUBLE).isInstanceOf(ToNumberStrategy.class);
  }

  @Test
  public void testLazilyParsedNumberImplementsToNumberStrategy() {
    assertThat(ToNumberPolicy.LAZILY_PARSED_NUMBER).isInstanceOf(ToNumberStrategy.class);
  }

  @Test
  public void testLongOrDoubleImplementsToNumberStrategy() {
    assertThat(ToNumberPolicy.LONG_OR_DOUBLE).isInstanceOf(ToNumberStrategy.class);
  }

  @Test
  public void testBigDecimalImplementsToNumberStrategy() {
    assertThat(ToNumberPolicy.BIG_DECIMAL).isInstanceOf(ToNumberStrategy.class);
  }

  // ========== valueOf Round-trip Tests ==========

  @Test
  public void testValueOfRoundTripDouble() {
    ToNumberPolicy original = ToNumberPolicy.DOUBLE;
    ToNumberPolicy fromValueOf = ToNumberPolicy.valueOf(original.name());
    assertThat(fromValueOf).isSameInstanceAs(original);
  }

  @Test
  public void testValueOfRoundTripLazilyParsedNumber() {
    ToNumberPolicy original = ToNumberPolicy.LAZILY_PARSED_NUMBER;
    ToNumberPolicy fromValueOf = ToNumberPolicy.valueOf(original.name());
    assertThat(fromValueOf).isSameInstanceAs(original);
  }

  @Test
  public void testValueOfRoundTripLongOrDouble() {
    ToNumberPolicy original = ToNumberPolicy.LONG_OR_DOUBLE;
    ToNumberPolicy fromValueOf = ToNumberPolicy.valueOf(original.name());
    assertThat(fromValueOf).isSameInstanceAs(original);
  }

  @Test
  public void testValueOfRoundTripBigDecimal() {
    ToNumberPolicy original = ToNumberPolicy.BIG_DECIMAL;
    ToNumberPolicy fromValueOf = ToNumberPolicy.valueOf(original.name());
    assertThat(fromValueOf).isSameInstanceAs(original);
  }

  @Test
  public void testAllConstantsRoundTrip() {
    for (ToNumberPolicy policy : ToNumberPolicy.values()) {
      ToNumberPolicy fromValueOf = ToNumberPolicy.valueOf(policy.name());
      assertThat(fromValueOf).isSameInstanceAs(policy);
    }
  }
}
