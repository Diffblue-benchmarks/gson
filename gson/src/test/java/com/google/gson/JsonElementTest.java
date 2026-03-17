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
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.junit.Test;

public class JsonElementTest {

  @Test
  public void testIsJsonArray() {
    JsonElement jsonArray = new JsonArray();
    assertThat(jsonArray.isJsonArray()).isTrue();

    JsonElement jsonObject = new JsonObject();
    assertThat(jsonObject.isJsonArray()).isFalse();

    JsonElement jsonPrimitive = new JsonPrimitive("test");
    assertThat(jsonPrimitive.isJsonArray()).isFalse();

    JsonElement jsonNull = JsonNull.INSTANCE;
    assertThat(jsonNull.isJsonArray()).isFalse();
  }

  @Test
  public void testIsJsonObject() {
    JsonElement jsonObject = new JsonObject();
    assertThat(jsonObject.isJsonObject()).isTrue();

    JsonElement jsonArray = new JsonArray();
    assertThat(jsonArray.isJsonObject()).isFalse();

    JsonElement jsonPrimitive = new JsonPrimitive("test");
    assertThat(jsonPrimitive.isJsonObject()).isFalse();

    JsonElement jsonNull = JsonNull.INSTANCE;
    assertThat(jsonNull.isJsonObject()).isFalse();
  }

  @Test
  public void testIsJsonPrimitive() {
    JsonElement jsonPrimitive = new JsonPrimitive("test");
    assertThat(jsonPrimitive.isJsonPrimitive()).isTrue();

    JsonElement jsonObject = new JsonObject();
    assertThat(jsonObject.isJsonPrimitive()).isFalse();

    JsonElement jsonArray = new JsonArray();
    assertThat(jsonArray.isJsonPrimitive()).isFalse();

    JsonElement jsonNull = JsonNull.INSTANCE;
    assertThat(jsonNull.isJsonPrimitive()).isFalse();
  }

  @Test
  public void testIsJsonNull() {
    JsonElement jsonNull = JsonNull.INSTANCE;
    assertThat(jsonNull.isJsonNull()).isTrue();

    JsonElement jsonObject = new JsonObject();
    assertThat(jsonObject.isJsonNull()).isFalse();

    JsonElement jsonArray = new JsonArray();
    assertThat(jsonArray.isJsonNull()).isFalse();

    JsonElement jsonPrimitive = new JsonPrimitive("test");
    assertThat(jsonPrimitive.isJsonNull()).isFalse();
  }

  @Test
  public void testGetAsJsonObject() {
    JsonObject jsonObject = new JsonObject();
    JsonElement element = jsonObject;
    assertThat(element.getAsJsonObject()).isSameInstanceAs(jsonObject);
  }

  @Test
  public void testGetAsJsonObjectFailure() {
    JsonElement jsonArray = new JsonArray();
    try {
      jsonArray.getAsJsonObject();
      fail("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertThat(e.getMessage()).contains("Not a JSON Object");
    }
  }

  @Test
  public void testGetAsJsonArray() {
    JsonArray jsonArray = new JsonArray();
    JsonElement element = jsonArray;
    assertThat(element.getAsJsonArray()).isSameInstanceAs(jsonArray);
  }

  @Test
  public void testGetAsJsonArrayFailure() {
    JsonElement jsonObject = new JsonObject();
    try {
      jsonObject.getAsJsonArray();
      fail("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertThat(e.getMessage()).contains("Not a JSON Array");
    }
  }

  @Test
  public void testGetAsJsonPrimitive() {
    JsonPrimitive jsonPrimitive = new JsonPrimitive("test");
    JsonElement element = jsonPrimitive;
    assertThat(element.getAsJsonPrimitive()).isSameInstanceAs(jsonPrimitive);
  }

  @Test
  public void testGetAsJsonPrimitiveFailure() {
    JsonElement jsonObject = new JsonObject();
    try {
      jsonObject.getAsJsonPrimitive();
      fail("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertThat(e.getMessage()).contains("Not a JSON Primitive");
    }
  }

  @Test
  public void testGetAsJsonNull() {
    JsonNull jsonNull = JsonNull.INSTANCE;
    JsonElement element = jsonNull;
    assertThat(element.getAsJsonNull()).isSameInstanceAs(jsonNull);
  }

  @Test
  public void testGetAsJsonNullFailure() {
    JsonElement jsonObject = new JsonObject();
    try {
      jsonObject.getAsJsonNull();
      fail("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertThat(e.getMessage()).contains("Not a JSON Null");
    }
  }

  @Test
  public void testGetAsBooleanUnsupported() {
    JsonElement jsonObject = new JsonObject();
    try {
      jsonObject.getAsBoolean();
      fail("Expected UnsupportedOperationException");
    } catch (UnsupportedOperationException e) {
      assertThat(e.getMessage()).isEqualTo("JsonObject");
    }
  }

  @Test
  public void testGetAsNumberUnsupported() {
    JsonElement jsonObject = new JsonObject();
    try {
      jsonObject.getAsNumber();
      fail("Expected UnsupportedOperationException");
    } catch (UnsupportedOperationException e) {
      assertThat(e.getMessage()).isEqualTo("JsonObject");
    }
  }

  @Test
  public void testGetAsStringUnsupported() {
    JsonElement jsonObject = new JsonObject();
    try {
      jsonObject.getAsString();
      fail("Expected UnsupportedOperationException");
    } catch (UnsupportedOperationException e) {
      assertThat(e.getMessage()).isEqualTo("JsonObject");
    }
  }

  @Test
  public void testGetAsDoubleUnsupported() {
    JsonElement jsonObject = new JsonObject();
    try {
      jsonObject.getAsDouble();
      fail("Expected UnsupportedOperationException");
    } catch (UnsupportedOperationException e) {
      assertThat(e.getMessage()).isEqualTo("JsonObject");
    }
  }

  @Test
  public void testGetAsFloatUnsupported() {
    JsonElement jsonObject = new JsonObject();
    try {
      jsonObject.getAsFloat();
      fail("Expected UnsupportedOperationException");
    } catch (UnsupportedOperationException e) {
      assertThat(e.getMessage()).isEqualTo("JsonObject");
    }
  }

  @Test
  public void testGetAsLongUnsupported() {
    JsonElement jsonObject = new JsonObject();
    try {
      jsonObject.getAsLong();
      fail("Expected UnsupportedOperationException");
    } catch (UnsupportedOperationException e) {
      assertThat(e.getMessage()).isEqualTo("JsonObject");
    }
  }

  @Test
  public void testGetAsIntUnsupported() {
    JsonElement jsonObject = new JsonObject();
    try {
      jsonObject.getAsInt();
      fail("Expected UnsupportedOperationException");
    } catch (UnsupportedOperationException e) {
      assertThat(e.getMessage()).isEqualTo("JsonObject");
    }
  }

  @Test
  public void testGetAsByteUnsupported() {
    JsonElement jsonObject = new JsonObject();
    try {
      jsonObject.getAsByte();
      fail("Expected UnsupportedOperationException");
    } catch (UnsupportedOperationException e) {
      assertThat(e.getMessage()).isEqualTo("JsonObject");
    }
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testGetAsCharacterUnsupported() {
    JsonElement jsonObject = new JsonObject();
    try {
      jsonObject.getAsCharacter();
      fail("Expected UnsupportedOperationException");
    } catch (UnsupportedOperationException e) {
      assertThat(e.getMessage()).isEqualTo("JsonObject");
    }
  }

  @Test
  public void testGetAsBigDecimalUnsupported() {
    JsonElement jsonObject = new JsonObject();
    try {
      jsonObject.getAsBigDecimal();
      fail("Expected UnsupportedOperationException");
    } catch (UnsupportedOperationException e) {
      assertThat(e.getMessage()).isEqualTo("JsonObject");
    }
  }

  @Test
  public void testGetAsBigIntegerUnsupported() {
    JsonElement jsonObject = new JsonObject();
    try {
      jsonObject.getAsBigInteger();
      fail("Expected UnsupportedOperationException");
    } catch (UnsupportedOperationException e) {
      assertThat(e.getMessage()).isEqualTo("JsonObject");
    }
  }

  @Test
  public void testGetAsShortUnsupported() {
    JsonElement jsonObject = new JsonObject();
    try {
      jsonObject.getAsShort();
      fail("Expected UnsupportedOperationException");
    } catch (UnsupportedOperationException e) {
      assertThat(e.getMessage()).isEqualTo("JsonObject");
    }
  }

  @Test
  public void testToStringJsonObject() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("key", "value");
    String result = jsonObject.toString();
    assertThat(result).isEqualTo("{\"key\":\"value\"}");
  }

  @Test
  public void testToStringJsonArray() {
    JsonArray jsonArray = new JsonArray();
    jsonArray.add(1);
    jsonArray.add(2);
    String result = jsonArray.toString();
    assertThat(result).isEqualTo("[1,2]");
  }

  @Test
  public void testToStringJsonPrimitive() {
    JsonPrimitive jsonPrimitive = new JsonPrimitive("test");
    String result = jsonPrimitive.toString();
    assertThat(result).isEqualTo("\"test\"");
  }

  @Test
  public void testToStringJsonNull() {
    JsonNull jsonNull = JsonNull.INSTANCE;
    String result = jsonNull.toString();
    assertThat(result).isEqualTo("null");
  }

  @Test
  public void testDeepCopyJsonObject() {
    JsonObject original = new JsonObject();
    original.addProperty("key", "value");
    JsonElement copy = original.deepCopy();
    assertThat(copy).isNotSameInstanceAs(original);
    assertThat(copy.toString()).isEqualTo(original.toString());
  }

  @Test
  public void testDeepCopyJsonArray() {
    JsonArray original = new JsonArray();
    original.add(1);
    original.add(2);
    JsonElement copy = original.deepCopy();
    assertThat(copy).isNotSameInstanceAs(original);
    assertThat(copy.toString()).isEqualTo(original.toString());
  }

  @Test
  public void testDeepCopyJsonPrimitive() {
    JsonPrimitive original = new JsonPrimitive("test");
    JsonElement copy = original.deepCopy();
    assertThat(copy).isSameInstanceAs(original);
  }

  @Test
  public void testDeepCopyJsonNull() {
    JsonNull original = JsonNull.INSTANCE;
    JsonElement copy = original.deepCopy();
    assertThat(copy).isSameInstanceAs(original);
  }
}
