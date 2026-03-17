/*
 * Copyright (C) 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.gson.internal.bind;

import static com.google.common.truth.Truth.assertThat;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.junit.Test;

public class JsonTreeWriterTest {

  @Test
  public void testConstructor() {
    JsonTreeWriter writer = new JsonTreeWriter();
    assertThat(writer).isNotNull();
  }

  @Test
  public void testGetWithEmptyStack() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    JsonElement result = writer.get();
    assertThat(result).isInstanceOf(JsonNull.class);
  }

  @Test(expected = IllegalStateException.class)
  public void testGetWithNonEmptyStack() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginArray();
    writer.get();
  }

  @Test
  public void testBeginArray() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginArray();
    writer.endArray();
    JsonElement result = writer.get();
    assertThat(result).isInstanceOf(JsonArray.class);
    assertThat(((JsonArray) result).size()).isEqualTo(0);
  }

  @Test(expected = IllegalStateException.class)
  public void testEndArrayWithEmptyStack() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.endArray();
  }

  @Test(expected = IllegalStateException.class)
  public void testEndArrayWithPendingName() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginObject();
    writer.name("test");
    writer.endArray();
  }

  @Test(expected = IllegalStateException.class)
  public void testEndArrayWithObject() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginObject();
    writer.endArray();
  }

  @Test
  public void testBeginObject() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginObject();
    writer.endObject();
    JsonElement result = writer.get();
    assertThat(result).isInstanceOf(JsonObject.class);
    assertThat(((JsonObject) result).size()).isEqualTo(0);
  }

  @Test(expected = IllegalStateException.class)
  public void testEndObjectWithEmptyStack() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.endObject();
  }

  @Test(expected = IllegalStateException.class)
  public void testEndObjectWithPendingName() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginObject();
    writer.name("test");
    writer.endObject();
  }

  @Test(expected = IllegalStateException.class)
  public void testEndObjectWithArray() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginArray();
    writer.endObject();
  }

  @Test
  public void testNameInObject() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginObject();
    writer.name("test");
    writer.value("value");
    writer.endObject();
    JsonElement result = writer.get();
    assertThat(result).isInstanceOf(JsonObject.class);
    JsonObject obj = (JsonObject) result;
    assertThat(obj.has("test")).isTrue();
    assertThat(obj.get("test").getAsString()).isEqualTo("value");
  }

  @Test(expected = NullPointerException.class)
  public void testNameWithNull() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginObject();
    writer.name(null);
  }

  @Test(expected = IllegalStateException.class)
  public void testNameWithEmptyStack() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.name("test");
  }

  @Test(expected = IllegalStateException.class)
  public void testNameWithPendingName() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginObject();
    writer.name("test");
    writer.name("another");
  }

  @Test(expected = IllegalStateException.class)
  public void testNameInArray() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginArray();
    writer.name("test");
  }

  @Test
  public void testValueStringNonNull() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value("test");
    JsonElement result = writer.get();
    assertThat(result).isInstanceOf(JsonPrimitive.class);
    assertThat(result.getAsString()).isEqualTo("test");
  }

  @Test
  public void testValueStringNull() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value((String) null);
    JsonElement result = writer.get();
    assertThat(result).isInstanceOf(JsonNull.class);
  }

  @Test
  public void testValueBoolean() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value(true);
    JsonElement result = writer.get();
    assertThat(result).isInstanceOf(JsonPrimitive.class);
    assertThat(result.getAsBoolean()).isTrue();
  }

  @Test
  public void testValueBooleanObjectNonNull() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value(Boolean.FALSE);
    JsonElement result = writer.get();
    assertThat(result).isInstanceOf(JsonPrimitive.class);
    assertThat(result.getAsBoolean()).isFalse();
  }

  @Test
  public void testValueBooleanObjectNull() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value((Boolean) null);
    JsonElement result = writer.get();
    assertThat(result).isInstanceOf(JsonNull.class);
  }

  @Test
  public void testValueFloat() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value(3.14f);
    JsonElement result = writer.get();
    assertThat(result).isInstanceOf(JsonPrimitive.class);
    assertThat(result.getAsFloat()).isEqualTo(3.14f);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testValueFloatNaN() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value(Float.NaN);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testValueFloatPositiveInfinity() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value(Float.POSITIVE_INFINITY);
  }

  @SuppressWarnings("deprecation")
  @Test
  public void testValueFloatNaNLenient() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.setLenient(true);
    writer.value(Float.NaN);
    JsonElement result = writer.get();
    assertThat(result).isInstanceOf(JsonPrimitive.class);
    assertThat(result.getAsFloat()).isNaN();
  }

  @Test
  public void testValueDouble() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value(2.71828);
    JsonElement result = writer.get();
    assertThat(result).isInstanceOf(JsonPrimitive.class);
    assertThat(result.getAsDouble()).isEqualTo(2.71828);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testValueDoubleNaN() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value(Double.NaN);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testValueDoubleNegativeInfinity() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value(Double.NEGATIVE_INFINITY);
  }

  @SuppressWarnings("deprecation")
  @Test
  public void testValueDoubleInfinityLenient() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.setLenient(true);
    writer.value(Double.POSITIVE_INFINITY);
    JsonElement result = writer.get();
    assertThat(result).isInstanceOf(JsonPrimitive.class);
    assertThat(result.getAsDouble()).isPositiveInfinity();
  }

  @Test
  public void testValueLong() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value(12345L);
    JsonElement result = writer.get();
    assertThat(result).isInstanceOf(JsonPrimitive.class);
    assertThat(result.getAsLong()).isEqualTo(12345L);
  }

  @Test
  public void testValueNumberInteger() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value(Integer.valueOf(42));
    JsonElement result = writer.get();
    assertThat(result).isInstanceOf(JsonPrimitive.class);
    assertThat(result.getAsInt()).isEqualTo(42);
  }

  @Test
  public void testValueNumberBigDecimal() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value(new BigDecimal("123.456"));
    JsonElement result = writer.get();
    assertThat(result).isInstanceOf(JsonPrimitive.class);
    assertThat(result.getAsBigDecimal()).isEqualTo(new BigDecimal("123.456"));
  }

  @Test
  public void testValueNumberBigInteger() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value(new BigInteger("999999999999999999"));
    JsonElement result = writer.get();
    assertThat(result).isInstanceOf(JsonPrimitive.class);
    assertThat(result.getAsBigInteger()).isEqualTo(new BigInteger("999999999999999999"));
  }

  @Test
  public void testValueNumberNull() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value((Number) null);
    JsonElement result = writer.get();
    assertThat(result).isInstanceOf(JsonNull.class);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testValueNumberNaN() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value(Double.valueOf(Double.NaN));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testValueNumberInfinity() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value(Double.valueOf(Double.POSITIVE_INFINITY));
  }

  @SuppressWarnings("deprecation")
  @Test
  public void testValueNumberNaNLenient() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.setLenient(true);
    writer.value(Double.valueOf(Double.NaN));
    JsonElement result = writer.get();
    assertThat(result).isInstanceOf(JsonPrimitive.class);
    assertThat(result.getAsDouble()).isNaN();
  }

  @Test
  public void testNullValue() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.nullValue();
    JsonElement result = writer.get();
    assertThat(result).isInstanceOf(JsonNull.class);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testJsonValue() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.jsonValue("{\"test\": \"value\"}");
  }

  @Test
  public void testFlush() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.flush();
  }

  @Test
  public void testClose() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.close();
  }

  @Test(expected = IOException.class)
  public void testCloseWithIncompleteDocument() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginArray();
    writer.close();
  }

  @Test
  public void testArrayWithMultipleValues() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginArray();
    writer.value("one");
    writer.value(2);
    writer.value(true);
    writer.nullValue();
    writer.endArray();
    JsonElement result = writer.get();
    assertThat(result).isInstanceOf(JsonArray.class);
    JsonArray array = (JsonArray) result;
    assertThat(array.size()).isEqualTo(4);
    assertThat(array.get(0).getAsString()).isEqualTo("one");
    assertThat(array.get(1).getAsInt()).isEqualTo(2);
    assertThat(array.get(2).getAsBoolean()).isTrue();
    assertThat(array.get(3)).isInstanceOf(JsonNull.class);
  }

  @Test
  public void testObjectWithMultipleProperties() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginObject();
    writer.name("string");
    writer.value("text");
    writer.name("number");
    writer.value(42);
    writer.name("boolean");
    writer.value(false);
    writer.name("null");
    writer.nullValue();
    writer.endObject();
    JsonElement result = writer.get();
    assertThat(result).isInstanceOf(JsonObject.class);
    JsonObject obj = (JsonObject) result;
    assertThat(obj.size()).isEqualTo(4);
    assertThat(obj.get("string").getAsString()).isEqualTo("text");
    assertThat(obj.get("number").getAsInt()).isEqualTo(42);
    assertThat(obj.get("boolean").getAsBoolean()).isFalse();
    assertThat(obj.get("null")).isInstanceOf(JsonNull.class);
  }

  @Test
  public void testNestedArrays() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginArray();
    writer.value(1);
    writer.beginArray();
    writer.value(2);
    writer.value(3);
    writer.endArray();
    writer.value(4);
    writer.endArray();
    JsonElement result = writer.get();
    assertThat(result).isInstanceOf(JsonArray.class);
    JsonArray array = (JsonArray) result;
    assertThat(array.size()).isEqualTo(3);
    assertThat(array.get(0).getAsInt()).isEqualTo(1);
    assertThat(array.get(1)).isInstanceOf(JsonArray.class);
    assertThat(array.get(2).getAsInt()).isEqualTo(4);
    JsonArray nested = array.get(1).getAsJsonArray();
    assertThat(nested.size()).isEqualTo(2);
    assertThat(nested.get(0).getAsInt()).isEqualTo(2);
    assertThat(nested.get(1).getAsInt()).isEqualTo(3);
  }

  @Test
  public void testNestedObjects() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginObject();
    writer.name("outer");
    writer.beginObject();
    writer.name("inner");
    writer.value("value");
    writer.endObject();
    writer.endObject();
    JsonElement result = writer.get();
    assertThat(result).isInstanceOf(JsonObject.class);
    JsonObject obj = (JsonObject) result;
    assertThat(obj.has("outer")).isTrue();
    JsonObject outer = obj.get("outer").getAsJsonObject();
    assertThat(outer.has("inner")).isTrue();
    assertThat(outer.get("inner").getAsString()).isEqualTo("value");
  }

  @Test
  public void testObjectInArray() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginArray();
    writer.beginObject();
    writer.name("key");
    writer.value("value");
    writer.endObject();
    writer.endArray();
    JsonElement result = writer.get();
    assertThat(result).isInstanceOf(JsonArray.class);
    JsonArray array = (JsonArray) result;
    assertThat(array.size()).isEqualTo(1);
    assertThat(array.get(0)).isInstanceOf(JsonObject.class);
    JsonObject obj = array.get(0).getAsJsonObject();
    assertThat(obj.get("key").getAsString()).isEqualTo("value");
  }

  @Test
  public void testArrayInObject() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginObject();
    writer.name("array");
    writer.beginArray();
    writer.value(1);
    writer.value(2);
    writer.endArray();
    writer.endObject();
    JsonElement result = writer.get();
    assertThat(result).isInstanceOf(JsonObject.class);
    JsonObject obj = (JsonObject) result;
    assertThat(obj.has("array")).isTrue();
    assertThat(obj.get("array")).isInstanceOf(JsonArray.class);
    JsonArray array = obj.get("array").getAsJsonArray();
    assertThat(array.size()).isEqualTo(2);
    assertThat(array.get(0).getAsInt()).isEqualTo(1);
    assertThat(array.get(1).getAsInt()).isEqualTo(2);
  }

  @Test
  public void testObjectWithNullPropertyWhenSerializeNullsEnabled() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.setSerializeNulls(true);
    writer.beginObject();
    writer.name("nullProp");
    writer.nullValue();
    writer.endObject();
    JsonElement result = writer.get();
    assertThat(result).isInstanceOf(JsonObject.class);
    JsonObject obj = (JsonObject) result;
    assertThat(obj.has("nullProp")).isTrue();
    assertThat(obj.get("nullProp")).isInstanceOf(JsonNull.class);
  }

  @Test
  public void testObjectWithNullPropertyWhenSerializeNullsDisabled() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.setSerializeNulls(false);
    writer.beginObject();
    writer.name("nullProp");
    writer.nullValue();
    writer.endObject();
    JsonElement result = writer.get();
    assertThat(result).isInstanceOf(JsonObject.class);
    JsonObject obj = (JsonObject) result;
    assertThat(obj.has("nullProp")).isFalse();
  }

  @Test
  public void testValueOverwriteProduct() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value(1);
    writer.value(2);
    JsonElement result = writer.get();
    assertThat(result).isInstanceOf(JsonPrimitive.class);
    assertThat(result.getAsInt()).isEqualTo(2);
  }

  @Test(expected = IllegalStateException.class)
  public void testValueInObjectWithoutName() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginObject();
    writer.value("value");
  }
}
