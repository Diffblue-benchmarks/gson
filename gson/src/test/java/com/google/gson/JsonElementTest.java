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

/**
 * Unit test for the {@link JsonElement} class.
 *
 * @author Claude
 */
public final class JsonElementTest {

  @Test
  public void testGetAsJsonPrimitiveThrowsForJsonObject() {
    JsonObject object = new JsonObject();
    IllegalStateException e =
        assertThrows(IllegalStateException.class, () -> object.getAsJsonPrimitive());
    assertThat(e).hasMessageThat().contains("Not a JSON Primitive");
  }

  @Test
  public void testGetAsJsonPrimitiveThrowsForJsonArray() {
    JsonArray array = new JsonArray();
    IllegalStateException e =
        assertThrows(IllegalStateException.class, () -> array.getAsJsonPrimitive());
    assertThat(e).hasMessageThat().contains("Not a JSON Primitive");
  }

  @Test
  public void testGetAsJsonPrimitiveThrowsForJsonNull() {
    IllegalStateException e =
        assertThrows(IllegalStateException.class, () -> JsonNull.INSTANCE.getAsJsonPrimitive());
    assertThat(e).hasMessageThat().contains("Not a JSON Primitive");
  }

  @Test
  public void testGetAsJsonNullSucceeds() {
    JsonNull result = JsonNull.INSTANCE.getAsJsonNull();
    assertThat(result).isSameInstanceAs(JsonNull.INSTANCE);
  }

  @Test
  public void testGetAsJsonNullThrowsForJsonObject() {
    JsonObject object = new JsonObject();
    IllegalStateException e =
        assertThrows(IllegalStateException.class, () -> object.getAsJsonNull());
    assertThat(e).hasMessageThat().contains("Not a JSON Null");
  }

  @Test
  public void testGetAsJsonNullThrowsForJsonArray() {
    JsonArray array = new JsonArray();
    IllegalStateException e =
        assertThrows(IllegalStateException.class, () -> array.getAsJsonNull());
    assertThat(e).hasMessageThat().contains("Not a JSON Null");
  }

  @Test
  public void testGetAsJsonNullThrowsForJsonPrimitive() {
    JsonPrimitive primitive = new JsonPrimitive("test");
    IllegalStateException e =
        assertThrows(IllegalStateException.class, () -> primitive.getAsJsonNull());
    assertThat(e).hasMessageThat().contains("Not a JSON Null");
  }

  @Test
  public void testGetAsNumberThrowsForJsonObject() {
    JsonObject object = new JsonObject();
    UnsupportedOperationException e =
        assertThrows(UnsupportedOperationException.class, () -> object.getAsNumber());
    assertThat(e).hasMessageThat().isEqualTo("JsonObject");
  }

  @Test
  public void testGetAsNumberThrowsForJsonNull() {
    UnsupportedOperationException e =
        assertThrows(UnsupportedOperationException.class, () -> JsonNull.INSTANCE.getAsNumber());
    assertThat(e).hasMessageThat().isEqualTo("JsonNull");
  }

  @Test
  public void testGetAsDoubleThrowsForJsonObject() {
    JsonObject object = new JsonObject();
    UnsupportedOperationException e =
        assertThrows(UnsupportedOperationException.class, () -> object.getAsDouble());
    assertThat(e).hasMessageThat().isEqualTo("JsonObject");
  }

  @Test
  public void testGetAsDoubleThrowsForJsonNull() {
    UnsupportedOperationException e =
        assertThrows(UnsupportedOperationException.class, () -> JsonNull.INSTANCE.getAsDouble());
    assertThat(e).hasMessageThat().isEqualTo("JsonNull");
  }

  @Test
  public void testGetAsFloatThrowsForJsonObject() {
    JsonObject object = new JsonObject();
    UnsupportedOperationException e =
        assertThrows(UnsupportedOperationException.class, () -> object.getAsFloat());
    assertThat(e).hasMessageThat().isEqualTo("JsonObject");
  }

  @Test
  public void testGetAsFloatThrowsForJsonNull() {
    UnsupportedOperationException e =
        assertThrows(UnsupportedOperationException.class, () -> JsonNull.INSTANCE.getAsFloat());
    assertThat(e).hasMessageThat().isEqualTo("JsonNull");
  }

  @Test
  public void testGetAsLongThrowsForJsonObject() {
    JsonObject object = new JsonObject();
    UnsupportedOperationException e =
        assertThrows(UnsupportedOperationException.class, () -> object.getAsLong());
    assertThat(e).hasMessageThat().isEqualTo("JsonObject");
  }

  @Test
  public void testGetAsLongThrowsForJsonNull() {
    UnsupportedOperationException e =
        assertThrows(UnsupportedOperationException.class, () -> JsonNull.INSTANCE.getAsLong());
    assertThat(e).hasMessageThat().isEqualTo("JsonNull");
  }

  @Test
  public void testGetAsIntThrowsForJsonObject() {
    JsonObject object = new JsonObject();
    UnsupportedOperationException e =
        assertThrows(UnsupportedOperationException.class, () -> object.getAsInt());
    assertThat(e).hasMessageThat().isEqualTo("JsonObject");
  }

  @Test
  public void testGetAsIntThrowsForJsonNull() {
    UnsupportedOperationException e =
        assertThrows(UnsupportedOperationException.class, () -> JsonNull.INSTANCE.getAsInt());
    assertThat(e).hasMessageThat().isEqualTo("JsonNull");
  }

  @Test
  public void testGetAsByteThrowsForJsonObject() {
    JsonObject object = new JsonObject();
    UnsupportedOperationException e =
        assertThrows(UnsupportedOperationException.class, () -> object.getAsByte());
    assertThat(e).hasMessageThat().isEqualTo("JsonObject");
  }

  @Test
  public void testGetAsByteThrowsForJsonNull() {
    UnsupportedOperationException e =
        assertThrows(UnsupportedOperationException.class, () -> JsonNull.INSTANCE.getAsByte());
    assertThat(e).hasMessageThat().isEqualTo("JsonNull");
  }

  @SuppressWarnings("deprecation")
  @Test
  public void testGetAsCharacterThrowsForJsonObject() {
    JsonObject object = new JsonObject();
    UnsupportedOperationException e =
        assertThrows(UnsupportedOperationException.class, () -> object.getAsCharacter());
    assertThat(e).hasMessageThat().isEqualTo("JsonObject");
  }

  @SuppressWarnings("deprecation")
  @Test
  public void testGetAsCharacterThrowsForJsonNull() {
    UnsupportedOperationException e =
        assertThrows(UnsupportedOperationException.class, () -> JsonNull.INSTANCE.getAsCharacter());
    assertThat(e).hasMessageThat().isEqualTo("JsonNull");
  }

  @Test
  public void testGetAsBigDecimalThrowsForJsonObject() {
    JsonObject object = new JsonObject();
    UnsupportedOperationException e =
        assertThrows(UnsupportedOperationException.class, () -> object.getAsBigDecimal());
    assertThat(e).hasMessageThat().isEqualTo("JsonObject");
  }

  @Test
  public void testGetAsBigDecimalThrowsForJsonNull() {
    UnsupportedOperationException e =
        assertThrows(
            UnsupportedOperationException.class, () -> JsonNull.INSTANCE.getAsBigDecimal());
    assertThat(e).hasMessageThat().isEqualTo("JsonNull");
  }

  @Test
  public void testGetAsBigIntegerThrowsForJsonObject() {
    JsonObject object = new JsonObject();
    UnsupportedOperationException e =
        assertThrows(UnsupportedOperationException.class, () -> object.getAsBigInteger());
    assertThat(e).hasMessageThat().isEqualTo("JsonObject");
  }

  @Test
  public void testGetAsBigIntegerThrowsForJsonNull() {
    UnsupportedOperationException e =
        assertThrows(
            UnsupportedOperationException.class, () -> JsonNull.INSTANCE.getAsBigInteger());
    assertThat(e).hasMessageThat().isEqualTo("JsonNull");
  }

  @Test
  public void testGetAsShortThrowsForJsonObject() {
    JsonObject object = new JsonObject();
    UnsupportedOperationException e =
        assertThrows(UnsupportedOperationException.class, () -> object.getAsShort());
    assertThat(e).hasMessageThat().isEqualTo("JsonObject");
  }

  @Test
  public void testGetAsShortThrowsForJsonNull() {
    UnsupportedOperationException e =
        assertThrows(UnsupportedOperationException.class, () -> JsonNull.INSTANCE.getAsShort());
    assertThat(e).hasMessageThat().isEqualTo("JsonNull");
  }

  @Test
  public void testToStringJsonNull() {
    assertThat(JsonNull.INSTANCE.toString()).isEqualTo("null");
  }

  @Test
  public void testToStringJsonObject() {
    JsonObject object = new JsonObject();
    object.addProperty("key", "value");
    assertThat(object.toString()).isEqualTo("{\"key\":\"value\"}");
  }

  @Test
  public void testToStringJsonArray() {
    JsonArray array = new JsonArray();
    array.add(1);
    array.add("test");
    assertThat(array.toString()).isEqualTo("[1,\"test\"]");
  }
}
