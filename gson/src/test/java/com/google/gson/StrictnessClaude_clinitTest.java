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

import org.junit.Test;

/**
 * Unit tests for {@link Strictness} static initialization ({@code <clinit>}).
 *
 * <p>These tests ensure that the enum constants are properly initialized during class loading.
 *
 * @author Claude
 */
public class StrictnessClaude_clinitTest {

  /**
   * Tests that accessing LENIENT triggers class initialization and the constant is properly
   * created.
   */
  @Test
  public void testLenientConstantInitialization() {
    // Accessing any enum constant triggers <clinit> which initializes all constants
    Strictness lenient = Strictness.LENIENT;
    assertThat(lenient).isNotNull();
    assertThat(lenient.name()).isEqualTo("LENIENT");
    assertThat(lenient.ordinal()).isEqualTo(0);
  }

  /**
   * Tests that accessing LEGACY_STRICT triggers class initialization and the constant is properly
   * created.
   */
  @Test
  public void testLegacyStrictConstantInitialization() {
    Strictness legacyStrict = Strictness.LEGACY_STRICT;
    assertThat(legacyStrict).isNotNull();
    assertThat(legacyStrict.name()).isEqualTo("LEGACY_STRICT");
    assertThat(legacyStrict.ordinal()).isEqualTo(1);
  }

  /**
   * Tests that accessing STRICT triggers class initialization and the constant is properly created.
   */
  @Test
  public void testStrictConstantInitialization() {
    Strictness strict = Strictness.STRICT;
    assertThat(strict).isNotNull();
    assertThat(strict.name()).isEqualTo("STRICT");
    assertThat(strict.ordinal()).isEqualTo(2);
  }

  /**
   * Tests that values() returns all three enum constants that were initialized during class
   * loading.
   */
  @Test
  public void testAllConstantsInitializedViaValues() {
    // values() returns all constants created during <clinit>
    Strictness[] values = Strictness.values();
    assertThat(values).hasLength(3);
    assertThat(values[0]).isEqualTo(Strictness.LENIENT);
    assertThat(values[1]).isEqualTo(Strictness.LEGACY_STRICT);
    assertThat(values[2]).isEqualTo(Strictness.STRICT);
  }

  /** Tests that the enum class itself can be accessed and is properly initialized. */
  @Test
  public void testEnumClassInitialization() {
    Class<Strictness> enumClass = Strictness.class;
    assertThat(enumClass).isNotNull();
    assertThat(enumClass.isEnum()).isTrue();
    assertThat(enumClass.getEnumConstants()).hasLength(3);
  }
}
