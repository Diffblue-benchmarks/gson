/*
 * Copyright (C) 2008 Google Inc.
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

public class JsonNullTest {

  @Test
  public void testDeepCopyReturnsSameInstance() {
    JsonNull original = JsonNull.INSTANCE;
    JsonNull copy = original.deepCopy();
    assertThat(copy).isSameInstanceAs(original);
  }

  @Test
  public void testIsJsonNull() {
    JsonNull jsonNull = JsonNull.INSTANCE;
    assertThat(jsonNull.isJsonNull()).isTrue();
    assertThat(jsonNull.isJsonArray()).isFalse();
    assertThat(jsonNull.isJsonObject()).isFalse();
    assertThat(jsonNull.isJsonPrimitive()).isFalse();
  }

  @Test
  public void testGetAsJsonNull() {
    JsonNull jsonNull = JsonNull.INSTANCE;
    assertThat(jsonNull.getAsJsonNull()).isSameInstanceAs(jsonNull);
  }

  @Test
  public void testGetAsJsonArray_throwsException() {
    JsonNull jsonNull = JsonNull.INSTANCE;
    IllegalStateException exception =
        assertThrows(IllegalStateException.class, () -> jsonNull.getAsJsonArray());
    assertThat(exception).hasMessageThat().contains("Not a JSON Array");
  }

  @Test
  public void testGetAsJsonObject_throwsException() {
    JsonNull jsonNull = JsonNull.INSTANCE;
    IllegalStateException exception =
        assertThrows(IllegalStateException.class, () -> jsonNull.getAsJsonObject());
    assertThat(exception).hasMessageThat().contains("Not a JSON Object");
  }

  @Test
  public void testGetAsJsonPrimitive_throwsException() {
    JsonNull jsonNull = JsonNull.INSTANCE;
    IllegalStateException exception =
        assertThrows(IllegalStateException.class, () -> jsonNull.getAsJsonPrimitive());
    assertThat(exception).hasMessageThat().contains("Not a JSON Primitive");
  }

  @Test
  public void testGetAsBoolean_throwsException() {
    JsonNull jsonNull = JsonNull.INSTANCE;
    assertThrows(UnsupportedOperationException.class, () -> jsonNull.getAsBoolean());
  }

  @Test
  public void testGetAsNumber_throwsException() {
    JsonNull jsonNull = JsonNull.INSTANCE;
    assertThrows(UnsupportedOperationException.class, () -> jsonNull.getAsNumber());
  }

  @Test
  public void testGetAsString_throwsException() {
    JsonNull jsonNull = JsonNull.INSTANCE;
    assertThrows(UnsupportedOperationException.class, () -> jsonNull.getAsString());
  }

  @Test
  public void testGetAsDouble_throwsException() {
    JsonNull jsonNull = JsonNull.INSTANCE;
    assertThrows(UnsupportedOperationException.class, () -> jsonNull.getAsDouble());
  }

  @Test
  public void testGetAsInt_throwsException() {
    JsonNull jsonNull = JsonNull.INSTANCE;
    assertThrows(UnsupportedOperationException.class, () -> jsonNull.getAsInt());
  }

  @Test
  public void testGetAsLong_throwsException() {
    JsonNull jsonNull = JsonNull.INSTANCE;
    assertThrows(UnsupportedOperationException.class, () -> jsonNull.getAsLong());
  }

  @Test
  public void testEqualsWithSameInstance() {
    JsonNull jsonNull = JsonNull.INSTANCE;
    assertThat(jsonNull.equals(jsonNull)).isTrue();
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testEqualsWithDifferentInstances() {
    JsonNull jsonNull1 = JsonNull.INSTANCE;
    JsonNull jsonNull2 = new JsonNull();
    assertThat(jsonNull1.equals(jsonNull2)).isTrue();
  }

  @Test
  public void testEqualsWithNull() {
    JsonNull jsonNull = JsonNull.INSTANCE;
    assertThat(jsonNull.equals(null)).isFalse();
  }

  @Test
  @SuppressWarnings("EqualsIncompatibleType")
  public void testEqualsWithDifferentType() {
    JsonNull jsonNull = JsonNull.INSTANCE;
    assertThat(jsonNull.equals(new JsonPrimitive("null"))).isFalse();
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testHashCode() {
    JsonNull jsonNull1 = JsonNull.INSTANCE;
    JsonNull jsonNull2 = new JsonNull();
    assertThat(jsonNull1.hashCode()).isEqualTo(jsonNull2.hashCode());
  }

  @Test
  public void testToString() {
    JsonNull jsonNull = JsonNull.INSTANCE;
    assertThat(jsonNull.toString()).isEqualTo("null");
  }
}
