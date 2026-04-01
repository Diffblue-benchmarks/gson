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

@SuppressWarnings("deprecation")
public final class JsonElementTest {

  @Test
  public void testConstructorDeprecated() {
    // Verify instantiation of a subclass exercises the deprecated JsonElement() constructor
    JsonObject obj = new JsonObject();
    assertThat(obj).isNotNull();
  }

  @Test
  public void testDeepCopyJsonObject() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", "value");
    JsonElement copy = obj.deepCopy();
    assertThat(copy).isInstanceOf(JsonObject.class);
    assertThat(copy.getAsJsonObject().get("key").getAsString()).isEqualTo("value");
  }

  @Test
  public void testDeepCopyJsonArray() {
    JsonArray arr = new JsonArray();
    arr.add(1);
    JsonElement copy = arr.deepCopy();
    assertThat(copy).isInstanceOf(JsonArray.class);
    assertThat(copy.getAsJsonArray().get(0).getAsInt()).isEqualTo(1);
  }

  @Test
  public void testDeepCopyJsonPrimitive() {
    JsonPrimitive prim = new JsonPrimitive("hello");
    JsonElement copy = prim.deepCopy();
    assertThat(copy).isInstanceOf(JsonPrimitive.class);
    assertThat(copy.getAsString()).isEqualTo("hello");
  }

  @Test
  public void testDeepCopyJsonNull() {
    JsonNull nullElem = JsonNull.INSTANCE;
    JsonElement copy = nullElem.deepCopy();
    assertThat(copy).isInstanceOf(JsonNull.class);
  }

  @Test
  public void testIsJsonArrayTrue() {
    JsonArray arr = new JsonArray();
    assertThat(arr.isJsonArray()).isTrue();
  }

  @Test
  public void testIsJsonArrayFalse() {
    JsonObject obj = new JsonObject();
    assertThat(obj.isJsonArray()).isFalse();
  }

  @Test
  public void testIsJsonObjectTrue() {
    JsonObject obj = new JsonObject();
    assertThat(obj.isJsonObject()).isTrue();
  }

  @Test
  public void testIsJsonObjectFalse() {
    JsonArray arr = new JsonArray();
    assertThat(arr.isJsonObject()).isFalse();
  }

  @Test
  public void testIsJsonPrimitiveTrue() {
    JsonPrimitive prim = new JsonPrimitive(42);
    assertThat(prim.isJsonPrimitive()).isTrue();
  }

  @Test
  public void testIsJsonPrimitiveFalse() {
    JsonObject obj = new JsonObject();
    assertThat(obj.isJsonPrimitive()).isFalse();
  }

  @Test
  public void testIsJsonNullTrue() {
    assertThat(JsonNull.INSTANCE.isJsonNull()).isTrue();
  }

  @Test
  public void testIsJsonNullFalse() {
    JsonObject obj = new JsonObject();
    assertThat(obj.isJsonNull()).isFalse();
  }

  @Test
  public void testGetAsJsonObjectSuccess() {
    JsonObject obj = new JsonObject();
    assertThat(obj.getAsJsonObject()).isSameInstanceAs(obj);
  }

  @Test
  public void testGetAsJsonObjectThrowsWhenNotObject() {
    JsonArray arr = new JsonArray();
    IllegalStateException e = assertThrows(IllegalStateException.class, arr::getAsJsonObject);
    assertThat(e).hasMessageThat().contains("Not a JSON Object");
  }

  @Test
  public void testGetAsJsonArraySuccess() {
    JsonArray arr = new JsonArray();
    assertThat(arr.getAsJsonArray()).isSameInstanceAs(arr);
  }

  @Test
  public void testGetAsJsonArrayThrowsWhenNotArray() {
    JsonObject obj = new JsonObject();
    IllegalStateException e = assertThrows(IllegalStateException.class, obj::getAsJsonArray);
    assertThat(e).hasMessageThat().contains("Not a JSON Array");
  }

  @Test
  public void testGetAsJsonPrimitiveSuccess() {
    JsonPrimitive prim = new JsonPrimitive("test");
    assertThat(prim.getAsJsonPrimitive()).isSameInstanceAs(prim);
  }

  @Test
  public void testGetAsJsonPrimitiveThrowsWhenNotPrimitive() {
    JsonObject obj = new JsonObject();
    IllegalStateException e = assertThrows(IllegalStateException.class, obj::getAsJsonPrimitive);
    assertThat(e).hasMessageThat().contains("Not a JSON Primitive");
  }

  @Test
  public void testGetAsJsonNullSuccess() {
    assertThat(JsonNull.INSTANCE.getAsJsonNull()).isSameInstanceAs(JsonNull.INSTANCE);
  }

  @Test
  public void testGetAsJsonNullThrowsWhenNotNull() {
    JsonObject obj = new JsonObject();
    IllegalStateException e = assertThrows(IllegalStateException.class, obj::getAsJsonNull);
    assertThat(e).hasMessageThat().contains("Not a JSON Null");
  }

  @Test
  public void testGetAsBooleanThrowsUnsupported() {
    JsonObject obj = new JsonObject();
    assertThrows(UnsupportedOperationException.class, obj::getAsBoolean);
  }

  @Test
  public void testGetAsNumberThrowsUnsupported() {
    JsonObject obj = new JsonObject();
    assertThrows(UnsupportedOperationException.class, obj::getAsNumber);
  }

  @Test
  public void testGetAsStringThrowsUnsupported() {
    JsonObject obj = new JsonObject();
    assertThrows(UnsupportedOperationException.class, obj::getAsString);
  }

  @Test
  public void testGetAsDoubleThrowsUnsupported() {
    JsonObject obj = new JsonObject();
    assertThrows(UnsupportedOperationException.class, obj::getAsDouble);
  }

  @Test
  public void testGetAsFloatThrowsUnsupported() {
    JsonObject obj = new JsonObject();
    assertThrows(UnsupportedOperationException.class, obj::getAsFloat);
  }

  @Test
  public void testGetAsLongThrowsUnsupported() {
    JsonObject obj = new JsonObject();
    assertThrows(UnsupportedOperationException.class, obj::getAsLong);
  }

  @Test
  public void testGetAsIntThrowsUnsupported() {
    JsonObject obj = new JsonObject();
    assertThrows(UnsupportedOperationException.class, obj::getAsInt);
  }

  @Test
  public void testGetAsByteThrowsUnsupported() {
    JsonObject obj = new JsonObject();
    assertThrows(UnsupportedOperationException.class, obj::getAsByte);
  }

  @Test
  public void testGetAsCharacterThrowsUnsupported() {
    JsonObject obj = new JsonObject();
    assertThrows(UnsupportedOperationException.class, obj::getAsCharacter);
  }

  @Test
  public void testGetAsBigDecimalThrowsUnsupported() {
    JsonObject obj = new JsonObject();
    assertThrows(UnsupportedOperationException.class, obj::getAsBigDecimal);
  }

  @Test
  public void testGetAsBigIntegerThrowsUnsupported() {
    JsonObject obj = new JsonObject();
    assertThrows(UnsupportedOperationException.class, obj::getAsBigInteger);
  }

  @Test
  public void testGetAsShortThrowsUnsupported() {
    JsonObject obj = new JsonObject();
    assertThrows(UnsupportedOperationException.class, obj::getAsShort);
  }

  @Test
  public void testToStringJsonObject() {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", "value");
    assertThat(obj.toString()).isEqualTo("{\"key\":\"value\"}");
  }

  @Test
  public void testToStringJsonArray() {
    JsonArray arr = new JsonArray();
    arr.add(1);
    arr.add("two");
    assertThat(arr.toString()).isEqualTo("[1,\"two\"]");
  }

  @Test
  public void testToStringJsonPrimitive() {
    JsonPrimitive prim = new JsonPrimitive("hello");
    assertThat(prim.toString()).isEqualTo("\"hello\"");
  }

  @Test
  public void testToStringJsonNull() {
    assertThat(JsonNull.INSTANCE.toString()).isEqualTo("null");
  }
}
