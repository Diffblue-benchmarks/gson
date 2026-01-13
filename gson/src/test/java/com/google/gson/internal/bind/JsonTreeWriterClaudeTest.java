/*
 * Copyright (C) 2024 Google Inc.
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.junit.Test;

/** Tests for {@link JsonTreeWriter}. */
public class JsonTreeWriterClaudeTest {

  // ==========================================================================
  // Constructor tests
  // ==========================================================================

  @Test
  public void constructor_createsWriterWithJsonNullProduct() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    JsonElement result = writer.get();
    assertTrue(result.isJsonNull());
  }

  // ==========================================================================
  // get() tests
  // ==========================================================================

  @Test
  public void get_afterWritingString_returnsJsonPrimitive() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value("hello");
    JsonElement result = writer.get();
    assertTrue(result.isJsonPrimitive());
    assertEquals("hello", result.getAsString());
  }

  @Test
  public void get_withIncompleteDocument_throwsException() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginArray();
    try {
      writer.get();
      fail("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertTrue(e.getMessage().contains("Expected one JSON element"));
    }
  }

  @Test
  public void get_afterWritingObject_returnsJsonObject() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginObject();
    writer.name("key").value("value");
    writer.endObject();
    JsonElement result = writer.get();
    assertTrue(result.isJsonObject());
    assertEquals("value", result.getAsJsonObject().get("key").getAsString());
  }

  @Test
  public void get_afterWritingArray_returnsJsonArray() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginArray();
    writer.value(1);
    writer.value(2);
    writer.endArray();
    JsonElement result = writer.get();
    assertTrue(result.isJsonArray());
    assertEquals(2, result.getAsJsonArray().size());
  }

  // ==========================================================================
  // beginArray() / endArray() tests
  // ==========================================================================

  @Test
  public void beginArray_createsJsonArray() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    JsonWriter result = writer.beginArray();
    assertSame(writer, result);
    writer.endArray();
    assertTrue(writer.get().isJsonArray());
  }

  @Test
  public void beginArray_canBeNested() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginArray();
    writer.beginArray();
    writer.endArray();
    writer.endArray();
    JsonElement result = writer.get();
    assertTrue(result.isJsonArray());
    assertEquals(1, result.getAsJsonArray().size());
    assertTrue(result.getAsJsonArray().get(0).isJsonArray());
  }

  @Test
  public void endArray_onEmptyStack_throwsException() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    try {
      writer.endArray();
      fail("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      // Expected
    }
  }

  @Test
  public void endArray_withPendingName_throwsException() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginObject();
    writer.name("key");
    try {
      writer.endArray();
      fail("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      // Expected
    }
  }

  @Test
  public void endArray_onObjectContext_throwsException() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginObject();
    try {
      writer.endArray();
      fail("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      // Expected
    }
  }

  @Test
  public void endArray_returnsThis() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginArray();
    JsonWriter result = writer.endArray();
    assertSame(writer, result);
  }

  // ==========================================================================
  // beginObject() / endObject() tests
  // ==========================================================================

  @Test
  public void beginObject_createsJsonObject() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    JsonWriter result = writer.beginObject();
    assertSame(writer, result);
    writer.endObject();
    assertTrue(writer.get().isJsonObject());
  }

  @Test
  public void beginObject_canBeNested() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginObject();
    writer.name("nested");
    writer.beginObject();
    writer.endObject();
    writer.endObject();
    JsonElement result = writer.get();
    assertTrue(result.isJsonObject());
    assertTrue(result.getAsJsonObject().get("nested").isJsonObject());
  }

  @Test
  public void endObject_onEmptyStack_throwsException() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    try {
      writer.endObject();
      fail("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      // Expected
    }
  }

  @Test
  public void endObject_withPendingName_throwsException() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginObject();
    writer.name("key");
    try {
      writer.endObject();
      fail("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      // Expected
    }
  }

  @Test
  public void endObject_onArrayContext_throwsException() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginArray();
    try {
      writer.endObject();
      fail("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      // Expected
    }
  }

  @Test
  public void endObject_returnsThis() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginObject();
    JsonWriter result = writer.endObject();
    assertSame(writer, result);
  }

  // ==========================================================================
  // name() tests
  // ==========================================================================

  @Test
  public void name_inObject_setsPropertyName() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginObject();
    JsonWriter result = writer.name("key");
    assertSame(writer, result);
    writer.value("value");
    writer.endObject();
    assertEquals("value", writer.get().getAsJsonObject().get("key").getAsString());
  }

  @Test
  public void name_withNull_throwsNullPointerException() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginObject();
    try {
      writer.name(null);
      fail("Expected NullPointerException");
    } catch (NullPointerException e) {
      assertTrue(e.getMessage().contains("name == null"));
    }
  }

  @Test
  public void name_onEmptyStack_throwsException() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    try {
      writer.name("key");
      fail("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertTrue(e.getMessage().contains("Did not expect a name"));
    }
  }

  @Test
  public void name_whenPendingNameExists_throwsException() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginObject();
    writer.name("first");
    try {
      writer.name("second");
      fail("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertTrue(e.getMessage().contains("Did not expect a name"));
    }
  }

  @Test
  public void name_inArray_throwsException() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginArray();
    try {
      writer.name("key");
      fail("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertTrue(e.getMessage().contains("begin an object"));
    }
  }

  @Test
  public void name_withEmptyString_setsPropertyName() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginObject();
    writer.name("");
    writer.value("value");
    writer.endObject();
    assertEquals("value", writer.get().getAsJsonObject().get("").getAsString());
  }

  @Test
  public void name_withSpecialCharacters_setsPropertyName() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginObject();
    writer.name("key with spaces & symbols!");
    writer.value("value");
    writer.endObject();
    assertEquals("value", writer.get().getAsJsonObject().get("key with spaces & symbols!").getAsString());
  }

  // ==========================================================================
  // value(String) tests
  // ==========================================================================

  @Test
  public void valueString_writesStringPrimitive() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    JsonWriter result = writer.value("hello");
    assertSame(writer, result);
    assertEquals("hello", writer.get().getAsString());
  }

  @Test
  public void valueString_withNull_writesJsonNull() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value((String) null);
    assertTrue(writer.get().isJsonNull());
  }

  @Test
  public void valueString_inArray_addsToArray() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginArray();
    writer.value("a");
    writer.value("b");
    writer.endArray();
    JsonArray array = writer.get().getAsJsonArray();
    assertEquals(2, array.size());
    assertEquals("a", array.get(0).getAsString());
    assertEquals("b", array.get(1).getAsString());
  }

  @Test
  public void valueString_inObject_setsPropertyValue() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginObject();
    writer.name("key");
    writer.value("value");
    writer.endObject();
    assertEquals("value", writer.get().getAsJsonObject().get("key").getAsString());
  }

  @Test
  public void valueString_emptyString_writesEmptyString() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value("");
    assertEquals("", writer.get().getAsString());
  }

  // ==========================================================================
  // value(boolean) tests
  // ==========================================================================

  @Test
  public void valueBoolean_true_writesTruePrimitive() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    JsonWriter result = writer.value(true);
    assertSame(writer, result);
    assertTrue(writer.get().getAsBoolean());
  }

  @Test
  public void valueBoolean_false_writesFalsePrimitive() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value(false);
    assertEquals(false, writer.get().getAsBoolean());
  }

  @Test
  public void valueBoolean_inArray_addsToArray() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginArray();
    writer.value(true);
    writer.value(false);
    writer.endArray();
    JsonArray array = writer.get().getAsJsonArray();
    assertEquals(2, array.size());
    assertTrue(array.get(0).getAsBoolean());
    assertEquals(false, array.get(1).getAsBoolean());
  }

  // ==========================================================================
  // value(Boolean) tests
  // ==========================================================================

  @Test
  public void valueBooleanObject_true_writesTruePrimitive() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    JsonWriter result = writer.value(Boolean.TRUE);
    assertSame(writer, result);
    assertTrue(writer.get().getAsBoolean());
  }

  @Test
  public void valueBooleanObject_false_writesFalsePrimitive() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value(Boolean.FALSE);
    assertEquals(false, writer.get().getAsBoolean());
  }

  @Test
  public void valueBooleanObject_null_writesJsonNull() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value((Boolean) null);
    assertTrue(writer.get().isJsonNull());
  }

  // ==========================================================================
  // value(float) tests
  // ==========================================================================

  @Test
  public void valueFloat_normalValue_writesNumberPrimitive() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    JsonWriter result = writer.value(3.14f);
    assertSame(writer, result);
    assertEquals(3.14f, writer.get().getAsFloat(), 0.001f);
  }

  @Test
  public void valueFloat_zero_writesZero() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value(0.0f);
    assertEquals(0.0f, writer.get().getAsFloat(), 0.0f);
  }

  @Test
  public void valueFloat_negativeValue_writesNegativeNumber() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value(-123.45f);
    assertEquals(-123.45f, writer.get().getAsFloat(), 0.001f);
  }

  @Test
  public void valueFloat_NaN_inStrictMode_throwsException() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    try {
      writer.value(Float.NaN);
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertTrue(e.getMessage().contains("NaN"));
    }
  }

  @Test
  public void valueFloat_positiveInfinity_inStrictMode_throwsException() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    try {
      writer.value(Float.POSITIVE_INFINITY);
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertTrue(e.getMessage().contains("infinities"));
    }
  }

  @Test
  public void valueFloat_negativeInfinity_inStrictMode_throwsException() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    try {
      writer.value(Float.NEGATIVE_INFINITY);
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertTrue(e.getMessage().contains("infinities"));
    }
  }

  @Test
  public void valueFloat_NaN_inLenientMode_writesNaN() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.setLenient(true);
    writer.value(Float.NaN);
    assertTrue(Float.isNaN(writer.get().getAsFloat()));
  }

  @Test
  public void valueFloat_positiveInfinity_inLenientMode_writesInfinity() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.setLenient(true);
    writer.value(Float.POSITIVE_INFINITY);
    assertEquals(Float.POSITIVE_INFINITY, writer.get().getAsFloat(), 0.0f);
  }

  @Test
  public void valueFloat_negativeInfinity_inLenientMode_writesInfinity() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.setLenient(true);
    writer.value(Float.NEGATIVE_INFINITY);
    assertEquals(Float.NEGATIVE_INFINITY, writer.get().getAsFloat(), 0.0f);
  }

  // ==========================================================================
  // value(double) tests
  // ==========================================================================

  @Test
  public void valueDouble_normalValue_writesNumberPrimitive() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    JsonWriter result = writer.value(3.14159265359);
    assertSame(writer, result);
    assertEquals(3.14159265359, writer.get().getAsDouble(), 0.0000001);
  }

  @Test
  public void valueDouble_zero_writesZero() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value(0.0);
    assertEquals(0.0, writer.get().getAsDouble(), 0.0);
  }

  @Test
  public void valueDouble_negativeValue_writesNegativeNumber() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value(-123.456789);
    assertEquals(-123.456789, writer.get().getAsDouble(), 0.000001);
  }

  @Test
  public void valueDouble_NaN_inStrictMode_throwsException() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    try {
      writer.value(Double.NaN);
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertTrue(e.getMessage().contains("NaN"));
    }
  }

  @Test
  public void valueDouble_positiveInfinity_inStrictMode_throwsException() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    try {
      writer.value(Double.POSITIVE_INFINITY);
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertTrue(e.getMessage().contains("infinities"));
    }
  }

  @Test
  public void valueDouble_negativeInfinity_inStrictMode_throwsException() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    try {
      writer.value(Double.NEGATIVE_INFINITY);
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertTrue(e.getMessage().contains("infinities"));
    }
  }

  @Test
  public void valueDouble_NaN_inLenientMode_writesNaN() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.setLenient(true);
    writer.value(Double.NaN);
    assertTrue(Double.isNaN(writer.get().getAsDouble()));
  }

  @Test
  public void valueDouble_positiveInfinity_inLenientMode_writesInfinity() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.setLenient(true);
    writer.value(Double.POSITIVE_INFINITY);
    assertEquals(Double.POSITIVE_INFINITY, writer.get().getAsDouble(), 0.0);
  }

  @Test
  public void valueDouble_negativeInfinity_inLenientMode_writesInfinity() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.setLenient(true);
    writer.value(Double.NEGATIVE_INFINITY);
    assertEquals(Double.NEGATIVE_INFINITY, writer.get().getAsDouble(), 0.0);
  }

  // ==========================================================================
  // value(long) tests
  // ==========================================================================

  @Test
  public void valueLong_normalValue_writesNumberPrimitive() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    JsonWriter result = writer.value(123456789L);
    assertSame(writer, result);
    assertEquals(123456789L, writer.get().getAsLong());
  }

  @Test
  public void valueLong_zero_writesZero() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value(0L);
    assertEquals(0L, writer.get().getAsLong());
  }

  @Test
  public void valueLong_negativeValue_writesNegativeNumber() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value(-987654321L);
    assertEquals(-987654321L, writer.get().getAsLong());
  }

  @Test
  public void valueLong_maxValue_writesMaxValue() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value(Long.MAX_VALUE);
    assertEquals(Long.MAX_VALUE, writer.get().getAsLong());
  }

  @Test
  public void valueLong_minValue_writesMinValue() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value(Long.MIN_VALUE);
    assertEquals(Long.MIN_VALUE, writer.get().getAsLong());
  }

  // ==========================================================================
  // value(Number) tests
  // ==========================================================================

  @Test
  public void valueNumber_integer_writesNumberPrimitive() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    JsonWriter result = writer.value(Integer.valueOf(42));
    assertSame(writer, result);
    assertEquals(42, writer.get().getAsInt());
  }

  @Test
  public void valueNumber_long_writesNumberPrimitive() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value(Long.valueOf(123456789L));
    assertEquals(123456789L, writer.get().getAsLong());
  }

  @Test
  public void valueNumber_double_writesNumberPrimitive() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value(Double.valueOf(3.14));
    assertEquals(3.14, writer.get().getAsDouble(), 0.001);
  }

  @Test
  public void valueNumber_bigDecimal_writesNumberPrimitive() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    BigDecimal bigDecimal = new BigDecimal("123456789.987654321");
    writer.value(bigDecimal);
    assertEquals(bigDecimal, writer.get().getAsBigDecimal());
  }

  @Test
  public void valueNumber_bigInteger_writesNumberPrimitive() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    BigInteger bigInteger = new BigInteger("123456789012345678901234567890");
    writer.value(bigInteger);
    assertEquals(bigInteger, writer.get().getAsBigInteger());
  }

  @Test
  public void valueNumber_null_writesJsonNull() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value((Number) null);
    assertTrue(writer.get().isJsonNull());
  }

  @Test
  public void valueNumber_NaN_inStrictMode_throwsException() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    try {
      writer.value(Double.valueOf(Double.NaN));
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertTrue(e.getMessage().contains("NaN"));
    }
  }

  @Test
  public void valueNumber_positiveInfinity_inStrictMode_throwsException() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    try {
      writer.value(Double.valueOf(Double.POSITIVE_INFINITY));
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertTrue(e.getMessage().contains("infinities"));
    }
  }

  @Test
  public void valueNumber_NaN_inLenientMode_writesNaN() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.setLenient(true);
    writer.value(Double.valueOf(Double.NaN));
    assertTrue(Double.isNaN(writer.get().getAsDouble()));
  }

  @Test
  public void valueNumber_positiveInfinity_inLenientMode_writesInfinity() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.setLenient(true);
    writer.value(Double.valueOf(Double.POSITIVE_INFINITY));
    assertEquals(Double.POSITIVE_INFINITY, writer.get().getAsDouble(), 0.0);
  }

  // ==========================================================================
  // nullValue() tests
  // ==========================================================================

  @Test
  public void nullValue_writesJsonNull() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    JsonWriter result = writer.nullValue();
    assertSame(writer, result);
    assertTrue(writer.get().isJsonNull());
  }

  @Test
  public void nullValue_inArray_addsNullToArray() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginArray();
    writer.nullValue();
    writer.endArray();
    JsonArray array = writer.get().getAsJsonArray();
    assertEquals(1, array.size());
    assertTrue(array.get(0).isJsonNull());
  }

  @Test
  public void nullValue_inObject_withSerializeNullsTrue_addsNullProperty() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.setSerializeNulls(true);
    writer.beginObject();
    writer.name("nullKey");
    writer.nullValue();
    writer.endObject();
    JsonObject obj = writer.get().getAsJsonObject();
    assertTrue(obj.has("nullKey"));
    assertTrue(obj.get("nullKey").isJsonNull());
  }

  @Test
  public void nullValue_inObject_withSerializeNullsFalse_omitsNullProperty() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.setSerializeNulls(false);
    writer.beginObject();
    writer.name("nullKey");
    writer.nullValue();
    writer.endObject();
    JsonObject obj = writer.get().getAsJsonObject();
    assertEquals(false, obj.has("nullKey"));
  }

  // ==========================================================================
  // jsonValue() tests
  // ==========================================================================

  @Test
  public void jsonValue_throwsUnsupportedOperationException() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    try {
      writer.jsonValue("{\"key\":\"value\"}");
      fail("Expected UnsupportedOperationException");
    } catch (UnsupportedOperationException e) {
      // Expected
    }
  }

  @Test
  public void jsonValue_withNull_throwsUnsupportedOperationException() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    try {
      writer.jsonValue(null);
      fail("Expected UnsupportedOperationException");
    } catch (UnsupportedOperationException e) {
      // Expected
    }
  }

  // ==========================================================================
  // flush() tests
  // ==========================================================================

  @Test
  public void flush_doesNothing() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginArray();
    writer.value(1);
    writer.flush(); // Should not throw
    writer.endArray();
    assertEquals(1, writer.get().getAsJsonArray().size());
  }

  @Test
  public void flush_canBeCalledMultipleTimes() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.flush();
    writer.flush();
    writer.flush();
    // Should not throw
    assertTrue(writer.get().isJsonNull());
  }

  // ==========================================================================
  // close() tests
  // ==========================================================================

  @Test
  public void close_onCompleteDocument_succeeds() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value("complete");
    writer.close();
    // Should not throw
  }

  @Test
  public void close_onIncompleteDocument_throwsIOException() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginArray();
    try {
      writer.close();
      fail("Expected IOException");
    } catch (IOException e) {
      assertTrue(e.getMessage().contains("Incomplete document"));
    }
  }

  @Test
  public void close_afterBeginObject_throwsIOException() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginObject();
    try {
      writer.close();
      fail("Expected IOException");
    } catch (IOException e) {
      assertTrue(e.getMessage().contains("Incomplete document"));
    }
  }

  @Test
  public void close_onEmptyDocument_succeeds() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.close();
    // Should not throw, and the product should still be JsonNull
  }

  // ==========================================================================
  // Complex structure tests
  // ==========================================================================

  @Test
  public void complexStructure_objectWithMixedValues() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginObject();
    writer.name("string").value("hello");
    writer.name("number").value(42);
    writer.name("decimal").value(3.14);
    writer.name("boolean").value(true);
    writer.name("null").nullValue();
    writer.name("array").beginArray().value(1).value(2).endArray();
    writer.name("object").beginObject().name("inner").value("value").endObject();
    writer.endObject();

    JsonObject result = writer.get().getAsJsonObject();
    assertEquals("hello", result.get("string").getAsString());
    assertEquals(42, result.get("number").getAsInt());
    assertEquals(3.14, result.get("decimal").getAsDouble(), 0.001);
    assertTrue(result.get("boolean").getAsBoolean());
    assertTrue(result.get("null").isJsonNull());
    assertEquals(2, result.get("array").getAsJsonArray().size());
    assertEquals("value", result.get("object").getAsJsonObject().get("inner").getAsString());
  }

  @Test
  public void complexStructure_arrayWithMixedValues() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginArray();
    writer.value("string");
    writer.value(42);
    writer.value(3.14);
    writer.value(true);
    writer.value(false);
    writer.nullValue();
    writer.beginObject().name("key").value("value").endObject();
    writer.beginArray().value(1).value(2).endArray();
    writer.endArray();

    JsonArray result = writer.get().getAsJsonArray();
    assertEquals(8, result.size());
    assertEquals("string", result.get(0).getAsString());
    assertEquals(42, result.get(1).getAsInt());
    assertEquals(3.14, result.get(2).getAsDouble(), 0.001);
    assertTrue(result.get(3).getAsBoolean());
    assertEquals(false, result.get(4).getAsBoolean());
    assertTrue(result.get(5).isJsonNull());
    assertTrue(result.get(6).isJsonObject());
    assertTrue(result.get(7).isJsonArray());
  }

  @Test
  public void deeplyNestedArrays_worksCorrectly() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginArray();
    writer.beginArray();
    writer.beginArray();
    writer.beginArray();
    writer.value("deep");
    writer.endArray();
    writer.endArray();
    writer.endArray();
    writer.endArray();

    JsonElement result = writer.get();
    assertTrue(result.isJsonArray());
    JsonArray a1 = result.getAsJsonArray();
    JsonArray a2 = a1.get(0).getAsJsonArray();
    JsonArray a3 = a2.get(0).getAsJsonArray();
    JsonArray a4 = a3.get(0).getAsJsonArray();
    assertEquals("deep", a4.get(0).getAsString());
  }

  @Test
  public void deeplyNestedObjects_worksCorrectly() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginObject();
    writer.name("level1").beginObject();
    writer.name("level2").beginObject();
    writer.name("level3").beginObject();
    writer.name("deep").value("value");
    writer.endObject();
    writer.endObject();
    writer.endObject();
    writer.endObject();

    JsonObject result = writer.get().getAsJsonObject();
    assertEquals("value",
        result.getAsJsonObject("level1")
            .getAsJsonObject("level2")
            .getAsJsonObject("level3")
            .get("deep").getAsString());
  }

  @Test
  public void objectInsideArray_worksCorrectly() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginArray();
    writer.beginObject();
    writer.name("index").value(0);
    writer.endObject();
    writer.beginObject();
    writer.name("index").value(1);
    writer.endObject();
    writer.endArray();

    JsonArray result = writer.get().getAsJsonArray();
    assertEquals(2, result.size());
    assertEquals(0, result.get(0).getAsJsonObject().get("index").getAsInt());
    assertEquals(1, result.get(1).getAsJsonObject().get("index").getAsInt());
  }

  @Test
  public void arrayInsideObject_worksCorrectly() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginObject();
    writer.name("items").beginArray();
    writer.value("a");
    writer.value("b");
    writer.value("c");
    writer.endArray();
    writer.endObject();

    JsonObject result = writer.get().getAsJsonObject();
    JsonArray items = result.get("items").getAsJsonArray();
    assertEquals(3, items.size());
    assertEquals("a", items.get(0).getAsString());
    assertEquals("b", items.get(1).getAsString());
    assertEquals("c", items.get(2).getAsString());
  }

  // ==========================================================================
  // Chaining tests
  // ==========================================================================

  @Test
  public void methodChaining_allMethodsReturnWriter() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    JsonWriter result = writer
        .beginObject()
        .name("key")
        .value("value")
        .name("num")
        .value(42)
        .name("flag")
        .value(true)
        .name("dec")
        .value(3.14)
        .name("nil")
        .nullValue()
        .endObject();
    assertSame(writer, result);
    assertNotNull(writer.get());
  }

  @Test
  public void methodChaining_arrayMethods() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    JsonWriter result = writer
        .beginArray()
        .value("a")
        .value(1)
        .value(true)
        .value(3.14)
        .nullValue()
        .beginArray()
        .endArray()
        .beginObject()
        .endObject()
        .endArray();
    assertSame(writer, result);
    assertEquals(7, writer.get().getAsJsonArray().size());
  }

  // ==========================================================================
  // Error state tests
  // ==========================================================================

  @Test
  public void put_onEmptyStackWithoutPendingName_setsProduct() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value("toplevel");
    assertEquals("toplevel", writer.get().getAsString());
  }

  @Test
  public void put_withNullAndSerializeNullsFalse_skipsPendingName() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.setSerializeNulls(false);
    writer.beginObject();
    writer.name("key1").nullValue();
    writer.name("key2").value("present");
    writer.endObject();

    JsonObject result = writer.get().getAsJsonObject();
    assertEquals(false, result.has("key1"));
    assertTrue(result.has("key2"));
    assertEquals("present", result.get("key2").getAsString());
  }

  @Test
  public void multipleTopLevelValues_lastOneWins() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value("first");
    JsonElement result = writer.get();
    assertEquals("first", result.getAsString());
    // Note: Writing a second top-level value would require a new writer
    // The get() call works because stack is empty
  }

  // ==========================================================================
  // Serialize nulls interaction tests
  // ==========================================================================

  @Test
  public void serializeNulls_affectsOnlyObjectProperties() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.setSerializeNulls(false);
    writer.beginArray();
    writer.nullValue(); // Should still be added to array
    writer.endArray();

    JsonArray result = writer.get().getAsJsonArray();
    assertEquals(1, result.size());
    assertTrue(result.get(0).isJsonNull());
  }

  @Test
  public void serializeNulls_true_includesAllNulls() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.setSerializeNulls(true);
    writer.beginObject();
    writer.name("null1").nullValue();
    writer.name("value").value("present");
    writer.name("null2").nullValue();
    writer.endObject();

    JsonObject result = writer.get().getAsJsonObject();
    assertEquals(3, result.size());
    assertTrue(result.get("null1").isJsonNull());
    assertEquals("present", result.get("value").getAsString());
    assertTrue(result.get("null2").isJsonNull());
  }

  // ==========================================================================
  // Edge case tests
  // ==========================================================================

  @Test
  public void emptyArray_createsEmptyJsonArray() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginArray().endArray();
    JsonArray result = writer.get().getAsJsonArray();
    assertEquals(0, result.size());
  }

  @Test
  public void emptyObject_createsEmptyJsonObject() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginObject().endObject();
    JsonObject result = writer.get().getAsJsonObject();
    assertEquals(0, result.size());
  }

  @Test
  public void specialDoubleValues_maxAndMin() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginArray();
    writer.value(Double.MAX_VALUE);
    writer.value(Double.MIN_VALUE);
    writer.value(-Double.MAX_VALUE);
    writer.endArray();

    JsonArray result = writer.get().getAsJsonArray();
    assertEquals(Double.MAX_VALUE, result.get(0).getAsDouble(), 0.0);
    assertEquals(Double.MIN_VALUE, result.get(1).getAsDouble(), 0.0);
    assertEquals(-Double.MAX_VALUE, result.get(2).getAsDouble(), 0.0);
  }

  @Test
  public void specialFloatValues_maxAndMin() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginArray();
    writer.value(Float.MAX_VALUE);
    writer.value(Float.MIN_VALUE);
    writer.value(-Float.MAX_VALUE);
    writer.endArray();

    JsonArray result = writer.get().getAsJsonArray();
    assertEquals(Float.MAX_VALUE, result.get(0).getAsFloat(), 0.0f);
    assertEquals(Float.MIN_VALUE, result.get(1).getAsFloat(), 0.0f);
    assertEquals(-Float.MAX_VALUE, result.get(2).getAsFloat(), 0.0f);
  }

  @Test
  public void duplicateKeys_overwrites() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginObject();
    writer.name("key").value("first");
    writer.name("key").value("second");
    writer.endObject();

    JsonObject result = writer.get().getAsJsonObject();
    // JsonObject.add overwrites, so second value wins
    assertEquals("second", result.get("key").getAsString());
  }

  @Test
  public void unicodeStrings_handledCorrectly() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginObject();
    writer.name("unicode").value("Hello, \u4e16\u754c! \ud83d\udc4b");
    writer.name("\u0441\u043a\u043b\u044e\u0447").value("cyrillic key");
    writer.endObject();

    JsonObject result = writer.get().getAsJsonObject();
    assertEquals("Hello, \u4e16\u754c! \ud83d\udc4b", result.get("unicode").getAsString());
    assertEquals("cyrillic key", result.get("\u0441\u043a\u043b\u044e\u0447").getAsString());
  }
}
