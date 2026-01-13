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

import org.junit.Test;

/**
 * Unit tests for {@link ToNumberPolicy} static initialization ({@code <clinit>}).
 *
 * <p>These tests cover the static initialization of the enum constants which occurs when the class
 * is first loaded.
 *
 * @author Claude
 */
public class ToNumberPolicyClaude_clinitTest {

  /**
   * Tests that the DOUBLE enum constant is properly initialized. Accessing this constant triggers
   * class initialization and covers line 40.
   */
  @Test
  public void testDoubleConstantInitialization() {
    ToNumberPolicy policy = ToNumberPolicy.DOUBLE;
    assertThat(policy).isNotNull();
    assertThat(policy.name()).isEqualTo("DOUBLE");
    assertThat(policy.ordinal()).isEqualTo(0);
  }

  /**
   * Tests that the LAZILY_PARSED_NUMBER enum constant is properly initialized. Accessing this
   * constant triggers class initialization and covers line 51.
   */
  @Test
  public void testLazilyParsedNumberConstantInitialization() {
    ToNumberPolicy policy = ToNumberPolicy.LAZILY_PARSED_NUMBER;
    assertThat(policy).isNotNull();
    assertThat(policy.name()).isEqualTo("LAZILY_PARSED_NUMBER");
    assertThat(policy.ordinal()).isEqualTo(1);
  }

  /**
   * Tests that the LONG_OR_DOUBLE enum constant is properly initialized. Accessing this constant
   * triggers class initialization and covers line 67.
   */
  @Test
  public void testLongOrDoubleConstantInitialization() {
    ToNumberPolicy policy = ToNumberPolicy.LONG_OR_DOUBLE;
    assertThat(policy).isNotNull();
    assertThat(policy.name()).isEqualTo("LONG_OR_DOUBLE");
    assertThat(policy.ordinal()).isEqualTo(2);
  }

  /**
   * Tests that the BIG_DECIMAL enum constant is properly initialized. Accessing this constant
   * triggers class initialization and covers line 101.
   */
  @Test
  public void testBigDecimalConstantInitialization() {
    ToNumberPolicy policy = ToNumberPolicy.BIG_DECIMAL;
    assertThat(policy).isNotNull();
    assertThat(policy.name()).isEqualTo("BIG_DECIMAL");
    assertThat(policy.ordinal()).isEqualTo(3);
  }

  /**
   * Tests that all enum constants are initialized in the values() array. This triggers class
   * initialization and covers lines 34, 40, 51, 67, and 101.
   */
  @Test
  public void testAllConstantsInitializedViaValues() {
    ToNumberPolicy[] values = ToNumberPolicy.values();
    assertThat(values).hasLength(4);
    assertThat(values[0]).isEqualTo(ToNumberPolicy.DOUBLE);
    assertThat(values[1]).isEqualTo(ToNumberPolicy.LAZILY_PARSED_NUMBER);
    assertThat(values[2]).isEqualTo(ToNumberPolicy.LONG_OR_DOUBLE);
    assertThat(values[3]).isEqualTo(ToNumberPolicy.BIG_DECIMAL);
  }

  /**
   * Tests that valueOf correctly returns initialized enum constants. This also triggers class
   * initialization and verifies the constants are properly set up.
   */
  @Test
  public void testConstantsInitializedViaValueOf() {
    assertThat(ToNumberPolicy.valueOf("DOUBLE")).isSameInstanceAs(ToNumberPolicy.DOUBLE);
    assertThat(ToNumberPolicy.valueOf("LAZILY_PARSED_NUMBER"))
        .isSameInstanceAs(ToNumberPolicy.LAZILY_PARSED_NUMBER);
    assertThat(ToNumberPolicy.valueOf("LONG_OR_DOUBLE"))
        .isSameInstanceAs(ToNumberPolicy.LONG_OR_DOUBLE);
    assertThat(ToNumberPolicy.valueOf("BIG_DECIMAL")).isSameInstanceAs(ToNumberPolicy.BIG_DECIMAL);
  }

  /**
   * Tests that each enum constant implements ToNumberStrategy interface. This verifies the enum
   * declaration on line 34 properly implements the interface.
   */
  @Test
  public void testEnumImplementsToNumberStrategy() {
    assertThat(ToNumberPolicy.DOUBLE).isInstanceOf(ToNumberStrategy.class);
    assertThat(ToNumberPolicy.LAZILY_PARSED_NUMBER).isInstanceOf(ToNumberStrategy.class);
    assertThat(ToNumberPolicy.LONG_OR_DOUBLE).isInstanceOf(ToNumberStrategy.class);
    assertThat(ToNumberPolicy.BIG_DECIMAL).isInstanceOf(ToNumberStrategy.class);
  }

  /**
   * Tests that the enum class itself is properly initialized. Accessing the class triggers static
   * initialization.
   */
  @Test
  public void testEnumClassInitialization() {
    Class<ToNumberPolicy> clazz = ToNumberPolicy.class;
    assertThat(clazz.isEnum()).isTrue();
    assertThat(clazz.getEnumConstants()).hasLength(4);
  }
}
