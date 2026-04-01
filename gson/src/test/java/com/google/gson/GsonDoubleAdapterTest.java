/*
 * Copyright (C) 2024 Google Inc.
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

import org.junit.Before;
import org.junit.Test;

public final class GsonDoubleAdapterTest {

  private Gson gson;

  @Before
  public void setUp() {
    gson = new Gson();
  }

  @Test
  public void testDoubleAdapterReadNull() {
    Double result = gson.fromJson("null", Double.class);

    assertThat(result).isNull();
  }

  @Test
  public void testDoubleAdapterReadValue() {
    Double result = gson.fromJson("3.14", Double.class);

    assertThat(result).isEqualTo(3.14);
  }

  @Test
  public void testDoubleAdapterWriteNullValue() {
    String json = gson.toJson(null, Double.class);

    assertThat(json).isEqualTo("null");
  }

  @Test
  public void testDoubleAdapterWriteRegularValue() {
    String json = gson.toJson(2.718);

    assertThat(json).isEqualTo("2.718");
  }

  @Test
  public void testDoubleAdapterWriteNanThrows() {
    assertThrows(IllegalArgumentException.class, () -> gson.toJson(Double.NaN));
  }

  @Test
  public void testDoubleAdapterWriteInfinityThrows() {
    assertThrows(
        IllegalArgumentException.class, () -> gson.toJson(Double.POSITIVE_INFINITY));
  }
}
