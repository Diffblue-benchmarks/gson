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

public final class LongSerializationPolicyTest {

  @Test
  public void testDefaultSerializeNull() {
    JsonElement result = LongSerializationPolicy.DEFAULT.serialize(null);
    assertThat(result).isEqualTo(JsonNull.INSTANCE);
  }

  @Test
  public void testDefaultSerializeValue() {
    JsonElement result = LongSerializationPolicy.DEFAULT.serialize(123L);
    assertThat(result).isInstanceOf(JsonPrimitive.class);
    assertThat(result.getAsLong()).isEqualTo(123L);
  }

  @Test
  public void testStringSerializeNull() {
    JsonElement result = LongSerializationPolicy.STRING.serialize(null);
    assertThat(result).isEqualTo(JsonNull.INSTANCE);
  }

  @Test
  public void testStringSerializeValue() {
    JsonElement result = LongSerializationPolicy.STRING.serialize(123L);
    assertThat(result).isInstanceOf(JsonPrimitive.class);
    assertThat(result.getAsString()).isEqualTo("123");
  }
}
