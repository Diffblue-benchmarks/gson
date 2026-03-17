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

import org.junit.Test;

public class LongSerializationPolicyTest {

  @Test
  public void testDefaultSerializeNull() {
    JsonElement result = LongSerializationPolicy.DEFAULT.serialize(null);
    assertThat(result).isEqualTo(JsonNull.INSTANCE);
  }

  @Test
  public void testDefaultSerializeValue() {
    JsonElement result = LongSerializationPolicy.DEFAULT.serialize(123L);
    assertThat(result.isJsonPrimitive()).isTrue();
    assertThat(result.getAsLong()).isEqualTo(123L);
  }

  @Test
  public void testDefaultSerializeZero() {
    JsonElement result = LongSerializationPolicy.DEFAULT.serialize(0L);
    assertThat(result.isJsonPrimitive()).isTrue();
    assertThat(result.getAsLong()).isEqualTo(0L);
  }

  @Test
  public void testDefaultSerializeNegativeValue() {
    JsonElement result = LongSerializationPolicy.DEFAULT.serialize(-456L);
    assertThat(result.isJsonPrimitive()).isTrue();
    assertThat(result.getAsLong()).isEqualTo(-456L);
  }

  @Test
  public void testDefaultSerializeMaxValue() {
    JsonElement result = LongSerializationPolicy.DEFAULT.serialize(Long.MAX_VALUE);
    assertThat(result.isJsonPrimitive()).isTrue();
    assertThat(result.getAsLong()).isEqualTo(Long.MAX_VALUE);
  }

  @Test
  public void testDefaultSerializeMinValue() {
    JsonElement result = LongSerializationPolicy.DEFAULT.serialize(Long.MIN_VALUE);
    assertThat(result.isJsonPrimitive()).isTrue();
    assertThat(result.getAsLong()).isEqualTo(Long.MIN_VALUE);
  }

  @Test
  public void testStringSerializeNull() {
    JsonElement result = LongSerializationPolicy.STRING.serialize(null);
    assertThat(result).isEqualTo(JsonNull.INSTANCE);
  }

  @Test
  public void testStringSerializeValue() {
    JsonElement result = LongSerializationPolicy.STRING.serialize(123L);
    assertThat(result.isJsonPrimitive()).isTrue();
    assertThat(result.getAsString()).isEqualTo("123");
  }

  @Test
  public void testStringSerializeZero() {
    JsonElement result = LongSerializationPolicy.STRING.serialize(0L);
    assertThat(result.isJsonPrimitive()).isTrue();
    assertThat(result.getAsString()).isEqualTo("0");
  }

  @Test
  public void testStringSerializeNegativeValue() {
    JsonElement result = LongSerializationPolicy.STRING.serialize(-456L);
    assertThat(result.isJsonPrimitive()).isTrue();
    assertThat(result.getAsString()).isEqualTo("-456");
  }

  @Test
  public void testStringSerializeMaxValue() {
    JsonElement result = LongSerializationPolicy.STRING.serialize(Long.MAX_VALUE);
    assertThat(result.isJsonPrimitive()).isTrue();
    assertThat(result.getAsString()).isEqualTo(String.valueOf(Long.MAX_VALUE));
  }

  @Test
  public void testStringSerializeMinValue() {
    JsonElement result = LongSerializationPolicy.STRING.serialize(Long.MIN_VALUE);
    assertThat(result.isJsonPrimitive()).isTrue();
    assertThat(result.getAsString()).isEqualTo(String.valueOf(Long.MIN_VALUE));
  }
}
