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
import static org.junit.Assert.assertThrows;

import org.junit.Test;

/**
 * Unit tests for {@link Strictness}.
 *
 * @author Claude
 */
public class StrictnessClaudeTest {

  // ========== values() Tests ==========

  @Test
  public void testValuesReturnsAllEnumConstants() {
    Strictness[] values = Strictness.values();
    assertThat(values).hasLength(3);
  }

  @Test
  public void testValuesContainsLenient() {
    Strictness[] values = Strictness.values();
    assertThat(values).asList().contains(Strictness.LENIENT);
  }

  @Test
  public void testValuesContainsLegacyStrict() {
    Strictness[] values = Strictness.values();
    assertThat(values).asList().contains(Strictness.LEGACY_STRICT);
  }

  @Test
  public void testValuesContainsStrict() {
    Strictness[] values = Strictness.values();
    assertThat(values).asList().contains(Strictness.STRICT);
  }

  @Test
  public void testValuesOrderIsLenientLegacyStrictStrict() {
    Strictness[] values = Strictness.values();
    assertThat(values[0]).isEqualTo(Strictness.LENIENT);
    assertThat(values[1]).isEqualTo(Strictness.LEGACY_STRICT);
    assertThat(values[2]).isEqualTo(Strictness.STRICT);
  }

  @Test
  public void testValuesReturnsNewArrayEachTime() {
    Strictness[] values1 = Strictness.values();
    Strictness[] values2 = Strictness.values();
    assertThat(values1).isNotSameInstanceAs(values2);
  }

  // ========== valueOf() Tests ==========

  @Test
  public void testValueOfLenient() {
    Strictness strictness = Strictness.valueOf("LENIENT");
    assertThat(strictness).isEqualTo(Strictness.LENIENT);
  }

  @Test
  public void testValueOfLegacyStrict() {
    Strictness strictness = Strictness.valueOf("LEGACY_STRICT");
    assertThat(strictness).isEqualTo(Strictness.LEGACY_STRICT);
  }

  @Test
  public void testValueOfStrict() {
    Strictness strictness = Strictness.valueOf("STRICT");
    assertThat(strictness).isEqualTo(Strictness.STRICT);
  }

  @Test
  public void testValueOfWithInvalidNameThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> {
      Strictness.valueOf("INVALID");
    });
  }

  @Test
  public void testValueOfWithNullThrowsException() {
    assertThrows(NullPointerException.class, () -> {
      Strictness.valueOf(null);
    });
  }

  @Test
  public void testValueOfIsCaseSensitive() {
    assertThrows(IllegalArgumentException.class, () -> {
      Strictness.valueOf("lenient");
    });
  }

  @Test
  public void testValueOfWithEmptyStringThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> {
      Strictness.valueOf("");
    });
  }

  @Test
  public void testValueOfWithWhitespaceThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> {
      Strictness.valueOf(" LENIENT");
    });
  }

  @Test
  public void testValueOfWithTrailingWhitespaceThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> {
      Strictness.valueOf("LENIENT ");
    });
  }

  // ========== Enum Properties Tests ==========

  @Test
  public void testLenientOrdinal() {
    assertThat(Strictness.LENIENT.ordinal()).isEqualTo(0);
  }

  @Test
  public void testLegacyStrictOrdinal() {
    assertThat(Strictness.LEGACY_STRICT.ordinal()).isEqualTo(1);
  }

  @Test
  public void testStrictOrdinal() {
    assertThat(Strictness.STRICT.ordinal()).isEqualTo(2);
  }

  @Test
  public void testLenientName() {
    assertThat(Strictness.LENIENT.name()).isEqualTo("LENIENT");
  }

  @Test
  public void testLegacyStrictName() {
    assertThat(Strictness.LEGACY_STRICT.name()).isEqualTo("LEGACY_STRICT");
  }

  @Test
  public void testStrictName() {
    assertThat(Strictness.STRICT.name()).isEqualTo("STRICT");
  }

  // ========== Comparison Tests ==========

  @Test
  public void testLenientNotEqualToLegacyStrict() {
    assertThat(Strictness.LENIENT).isNotEqualTo(Strictness.LEGACY_STRICT);
  }

  @Test
  public void testLenientNotEqualToStrict() {
    assertThat(Strictness.LENIENT).isNotEqualTo(Strictness.STRICT);
  }

  @Test
  public void testLegacyStrictNotEqualToStrict() {
    assertThat(Strictness.LEGACY_STRICT).isNotEqualTo(Strictness.STRICT);
  }

  @Test
  public void testSameEnumConstantIsEqual() {
    assertThat(Strictness.LENIENT).isEqualTo(Strictness.LENIENT);
    assertThat(Strictness.LEGACY_STRICT).isEqualTo(Strictness.LEGACY_STRICT);
    assertThat(Strictness.STRICT).isEqualTo(Strictness.STRICT);
  }

  @Test
  public void testToStringReturnsName() {
    assertThat(Strictness.LENIENT.toString()).isEqualTo("LENIENT");
    assertThat(Strictness.LEGACY_STRICT.toString()).isEqualTo("LEGACY_STRICT");
    assertThat(Strictness.STRICT.toString()).isEqualTo("STRICT");
  }
}
