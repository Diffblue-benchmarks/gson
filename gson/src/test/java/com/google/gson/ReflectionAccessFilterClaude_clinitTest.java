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

package com.google.gson;

import static com.google.common.truth.Truth.assertThat;

import com.google.gson.ReflectionAccessFilter.FilterResult;
import org.junit.Test;

/**
 * Tests for static initialization ({@code <clinit>}) of {@link ReflectionAccessFilter.FilterResult}.
 * These tests ensure all enum constants are properly initialized during class loading.
 *
 * @author Claude
 */
public class ReflectionAccessFilterClaude_clinitTest {

  /**
   * Tests that accessing the ALLOW constant triggers class initialization
   * and the constant is properly initialized.
   */
  @Test
  public void testAllowConstantInitialization() {
    FilterResult allow = FilterResult.ALLOW;
    assertThat(allow).isNotNull();
    assertThat(allow.name()).isEqualTo("ALLOW");
    assertThat(allow.ordinal()).isEqualTo(0);
  }

  /**
   * Tests that accessing the INDECISIVE constant triggers class initialization
   * and the constant is properly initialized.
   */
  @Test
  public void testIndecisiveConstantInitialization() {
    FilterResult indecisive = FilterResult.INDECISIVE;
    assertThat(indecisive).isNotNull();
    assertThat(indecisive.name()).isEqualTo("INDECISIVE");
    assertThat(indecisive.ordinal()).isEqualTo(1);
  }

  /**
   * Tests that accessing the BLOCK_INACCESSIBLE constant triggers class initialization
   * and the constant is properly initialized.
   */
  @Test
  public void testBlockInaccessibleConstantInitialization() {
    FilterResult blockInaccessible = FilterResult.BLOCK_INACCESSIBLE;
    assertThat(blockInaccessible).isNotNull();
    assertThat(blockInaccessible.name()).isEqualTo("BLOCK_INACCESSIBLE");
    assertThat(blockInaccessible.ordinal()).isEqualTo(2);
  }

  /**
   * Tests that accessing the BLOCK_ALL constant triggers class initialization
   * and the constant is properly initialized.
   */
  @Test
  public void testBlockAllConstantInitialization() {
    FilterResult blockAll = FilterResult.BLOCK_ALL;
    assertThat(blockAll).isNotNull();
    assertThat(blockAll.name()).isEqualTo("BLOCK_ALL");
    assertThat(blockAll.ordinal()).isEqualTo(3);
  }

  /**
   * Tests that values() returns all four initialized enum constants.
   * This covers the enum declaration and all constant declarations
   * as the class must be fully initialized to return all values.
   */
  @Test
  public void testValuesReturnsAllInitializedConstants() {
    FilterResult[] values = FilterResult.values();

    assertThat(values).hasLength(4);
    assertThat(values[0]).isEqualTo(FilterResult.ALLOW);
    assertThat(values[1]).isEqualTo(FilterResult.INDECISIVE);
    assertThat(values[2]).isEqualTo(FilterResult.BLOCK_INACCESSIBLE);
    assertThat(values[3]).isEqualTo(FilterResult.BLOCK_ALL);
  }

  /**
   * Tests that the enum class is an enum type after initialization.
   */
  @Test
  public void testFilterResultIsEnumType() {
    assertThat(FilterResult.class.isEnum()).isTrue();
  }

  /**
   * Tests that each enum constant's declaring class is FilterResult.
   */
  @Test
  public void testEnumConstantsDeclaringClass() {
    assertThat(FilterResult.ALLOW.getDeclaringClass()).isEqualTo(FilterResult.class);
    assertThat(FilterResult.INDECISIVE.getDeclaringClass()).isEqualTo(FilterResult.class);
    assertThat(FilterResult.BLOCK_INACCESSIBLE.getDeclaringClass()).isEqualTo(FilterResult.class);
    assertThat(FilterResult.BLOCK_ALL.getDeclaringClass()).isEqualTo(FilterResult.class);
  }

  /**
   * Tests that valueOf works for all constants, confirming proper initialization.
   */
  @Test
  public void testValueOfAllConstants() {
    assertThat(FilterResult.valueOf("ALLOW")).isEqualTo(FilterResult.ALLOW);
    assertThat(FilterResult.valueOf("INDECISIVE")).isEqualTo(FilterResult.INDECISIVE);
    assertThat(FilterResult.valueOf("BLOCK_INACCESSIBLE")).isEqualTo(FilterResult.BLOCK_INACCESSIBLE);
    assertThat(FilterResult.valueOf("BLOCK_ALL")).isEqualTo(FilterResult.BLOCK_ALL);
  }
}
