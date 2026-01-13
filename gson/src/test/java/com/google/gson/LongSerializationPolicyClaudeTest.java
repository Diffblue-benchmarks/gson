/*
 * Copyright (C) 2009 Google Inc.
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
 * Unit tests for {@link LongSerializationPolicy}.
 *
 * @author Claude
 */
public class LongSerializationPolicyClaudeTest {

  // ========== values() Tests ==========

  @Test
  public void testValuesReturnsAllEnumConstants() {
    LongSerializationPolicy[] values = LongSerializationPolicy.values();
    assertThat(values).hasLength(2);
  }

  @Test
  public void testValuesContainsDefault() {
    LongSerializationPolicy[] values = LongSerializationPolicy.values();
    assertThat(values).asList().contains(LongSerializationPolicy.DEFAULT);
  }

  @Test
  public void testValuesContainsString() {
    LongSerializationPolicy[] values = LongSerializationPolicy.values();
    assertThat(values).asList().contains(LongSerializationPolicy.STRING);
  }

  @Test
  public void testValuesOrderIsDefaultThenString() {
    LongSerializationPolicy[] values = LongSerializationPolicy.values();
    assertThat(values[0]).isEqualTo(LongSerializationPolicy.DEFAULT);
    assertThat(values[1]).isEqualTo(LongSerializationPolicy.STRING);
  }

  @Test
  public void testValuesReturnsNewArrayEachTime() {
    LongSerializationPolicy[] values1 = LongSerializationPolicy.values();
    LongSerializationPolicy[] values2 = LongSerializationPolicy.values();
    assertThat(values1).isNotSameInstanceAs(values2);
  }

  // ========== valueOf() Tests ==========

  @Test
  public void testValueOfDefault() {
    LongSerializationPolicy policy = LongSerializationPolicy.valueOf("DEFAULT");
    assertThat(policy).isEqualTo(LongSerializationPolicy.DEFAULT);
  }

  @Test
  public void testValueOfString() {
    LongSerializationPolicy policy = LongSerializationPolicy.valueOf("STRING");
    assertThat(policy).isEqualTo(LongSerializationPolicy.STRING);
  }

  @Test
  public void testValueOfWithInvalidNameThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> {
      LongSerializationPolicy.valueOf("INVALID");
    });
  }

  @Test
  public void testValueOfWithNullThrowsException() {
    assertThrows(NullPointerException.class, () -> {
      LongSerializationPolicy.valueOf(null);
    });
  }

  @Test
  public void testValueOfIsCaseSensitive() {
    assertThrows(IllegalArgumentException.class, () -> {
      LongSerializationPolicy.valueOf("default");
    });
  }

  @Test
  public void testValueOfWithEmptyStringThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> {
      LongSerializationPolicy.valueOf("");
    });
  }

  @Test
  public void testValueOfWithWhitespaceThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> {
      LongSerializationPolicy.valueOf(" DEFAULT");
    });
  }

  // ========== serialize() Tests for DEFAULT policy ==========

  @Test
  public void testDefaultSerializePositiveValue() {
    JsonElement element = LongSerializationPolicy.DEFAULT.serialize(123L);
    assertThat(element.isJsonPrimitive()).isTrue();
    assertThat(element.getAsLong()).isEqualTo(123L);
  }

  @Test
  public void testDefaultSerializeNegativeValue() {
    JsonElement element = LongSerializationPolicy.DEFAULT.serialize(-456L);
    assertThat(element.isJsonPrimitive()).isTrue();
    assertThat(element.getAsLong()).isEqualTo(-456L);
  }

  @Test
  public void testDefaultSerializeZero() {
    JsonElement element = LongSerializationPolicy.DEFAULT.serialize(0L);
    assertThat(element.isJsonPrimitive()).isTrue();
    assertThat(element.getAsLong()).isEqualTo(0L);
  }

  @Test
  public void testDefaultSerializeMaxValue() {
    JsonElement element = LongSerializationPolicy.DEFAULT.serialize(Long.MAX_VALUE);
    assertThat(element.isJsonPrimitive()).isTrue();
    assertThat(element.getAsLong()).isEqualTo(Long.MAX_VALUE);
  }

  @Test
  public void testDefaultSerializeMinValue() {
    JsonElement element = LongSerializationPolicy.DEFAULT.serialize(Long.MIN_VALUE);
    assertThat(element.isJsonPrimitive()).isTrue();
    assertThat(element.getAsLong()).isEqualTo(Long.MIN_VALUE);
  }

  @Test
  public void testDefaultSerializeNullReturnsJsonNull() {
    JsonElement element = LongSerializationPolicy.DEFAULT.serialize(null);
    assertThat(element).isSameInstanceAs(JsonNull.INSTANCE);
  }

  @Test
  public void testDefaultSerializeReturnsNumber() {
    JsonElement element = LongSerializationPolicy.DEFAULT.serialize(123456789012345L);
    assertThat(element.getAsJsonPrimitive().isNumber()).isTrue();
  }

  // ========== serialize() Tests for STRING policy ==========

  @Test
  public void testStringSerializePositiveValue() {
    JsonElement element = LongSerializationPolicy.STRING.serialize(123L);
    assertThat(element.isJsonPrimitive()).isTrue();
    assertThat(element.getAsString()).isEqualTo("123");
  }

  @Test
  public void testStringSerializeNegativeValue() {
    JsonElement element = LongSerializationPolicy.STRING.serialize(-456L);
    assertThat(element.isJsonPrimitive()).isTrue();
    assertThat(element.getAsString()).isEqualTo("-456");
  }

  @Test
  public void testStringSerializeZero() {
    JsonElement element = LongSerializationPolicy.STRING.serialize(0L);
    assertThat(element.isJsonPrimitive()).isTrue();
    assertThat(element.getAsString()).isEqualTo("0");
  }

  @Test
  public void testStringSerializeMaxValue() {
    JsonElement element = LongSerializationPolicy.STRING.serialize(Long.MAX_VALUE);
    assertThat(element.isJsonPrimitive()).isTrue();
    assertThat(element.getAsString()).isEqualTo(String.valueOf(Long.MAX_VALUE));
  }

  @Test
  public void testStringSerializeMinValue() {
    JsonElement element = LongSerializationPolicy.STRING.serialize(Long.MIN_VALUE);
    assertThat(element.isJsonPrimitive()).isTrue();
    assertThat(element.getAsString()).isEqualTo(String.valueOf(Long.MIN_VALUE));
  }

  @Test
  public void testStringSerializeNullReturnsJsonNull() {
    JsonElement element = LongSerializationPolicy.STRING.serialize(null);
    assertThat(element).isSameInstanceAs(JsonNull.INSTANCE);
  }

  @Test
  public void testStringSerializeReturnsString() {
    JsonElement element = LongSerializationPolicy.STRING.serialize(123456789012345L);
    assertThat(element.getAsJsonPrimitive().isString()).isTrue();
  }

  // ========== Enum Properties Tests ==========

  @Test
  public void testDefaultOrdinal() {
    assertThat(LongSerializationPolicy.DEFAULT.ordinal()).isEqualTo(0);
  }

  @Test
  public void testStringOrdinal() {
    assertThat(LongSerializationPolicy.STRING.ordinal()).isEqualTo(1);
  }

  @Test
  public void testDefaultName() {
    assertThat(LongSerializationPolicy.DEFAULT.name()).isEqualTo("DEFAULT");
  }

  @Test
  public void testStringName() {
    assertThat(LongSerializationPolicy.STRING.name()).isEqualTo("STRING");
  }

  // ========== Comparison Tests ==========

  @Test
  public void testDefaultNotEqualToString() {
    assertThat(LongSerializationPolicy.DEFAULT).isNotEqualTo(LongSerializationPolicy.STRING);
  }

  @Test
  public void testSameEnumConstantIsEqual() {
    assertThat(LongSerializationPolicy.DEFAULT).isEqualTo(LongSerializationPolicy.DEFAULT);
    assertThat(LongSerializationPolicy.STRING).isEqualTo(LongSerializationPolicy.STRING);
  }

  @Test
  public void testDefaultSerializeDifferentFromString() {
    Long value = 12345L;
    JsonElement defaultResult = LongSerializationPolicy.DEFAULT.serialize(value);
    JsonElement stringResult = LongSerializationPolicy.STRING.serialize(value);

    // Default produces a number, String produces a string
    assertThat(defaultResult.getAsJsonPrimitive().isNumber()).isTrue();
    assertThat(stringResult.getAsJsonPrimitive().isString()).isTrue();
  }
}
