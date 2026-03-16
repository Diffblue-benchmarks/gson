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
  public void testDefault() {
    JsonElement element = LongSerializationPolicy.DEFAULT.serialize(123L);
    assertThat(element.isJsonPrimitive()).isTrue();
    assertThat(element.getAsLong()).isEqualTo(123L);
  }

  @Test
  public void testDefault_null() {
    JsonElement element = LongSerializationPolicy.DEFAULT.serialize(null);
    assertThat(element.isJsonNull()).isTrue();
  }

  @Test
  public void testDefault_maxValue() {
    JsonElement element = LongSerializationPolicy.DEFAULT.serialize(Long.MAX_VALUE);
    assertThat(element.getAsLong()).isEqualTo(Long.MAX_VALUE);
  }

  @Test
  public void testDefault_minValue() {
    JsonElement element = LongSerializationPolicy.DEFAULT.serialize(Long.MIN_VALUE);
    assertThat(element.getAsLong()).isEqualTo(Long.MIN_VALUE);
  }

  @Test
  public void testString() {
    JsonElement element = LongSerializationPolicy.STRING.serialize(123L);
    assertThat(element.isJsonPrimitive()).isTrue();
    assertThat(element.getAsString()).isEqualTo("123");
  }

  @Test
  public void testString_null() {
    JsonElement element = LongSerializationPolicy.STRING.serialize(null);
    assertThat(element.isJsonNull()).isTrue();
  }

  @Test
  public void testString_maxValue() {
    JsonElement element = LongSerializationPolicy.STRING.serialize(Long.MAX_VALUE);
    assertThat(element.getAsString()).isEqualTo(String.valueOf(Long.MAX_VALUE));
  }

  @Test
  public void testString_minValue() {
    JsonElement element = LongSerializationPolicy.STRING.serialize(Long.MIN_VALUE);
    assertThat(element.getAsString()).isEqualTo(String.valueOf(Long.MIN_VALUE));
  }

  @Test
  public void testString_negative() {
    JsonElement element = LongSerializationPolicy.STRING.serialize(-456L);
    assertThat(element.getAsString()).isEqualTo("-456");
  }
}
