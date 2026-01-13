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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.MalformedJsonException;
import java.io.IOException;
import org.junit.Test;

/** Tests for {@link JsonTreeReader}. */
public class JsonTreeReaderClaudeTest {

  // ==========================================================================
  // Constructor tests
  // ==========================================================================

  @Test
  public void constructor_withJsonObject_createsReader() throws IOException {
    JsonObject obj = new JsonObject();
    JsonTreeReader reader = new JsonTreeReader(obj);
    assertEquals(JsonToken.BEGIN_OBJECT, reader.peek());
  }

  @Test
  public void constructor_withJsonArray_createsReader() throws IOException {
    JsonArray array = new JsonArray();
    JsonTreeReader reader = new JsonTreeReader(array);
    assertEquals(JsonToken.BEGIN_ARRAY, reader.peek());
  }

  @Test
  public void constructor_withJsonPrimitiveString_createsReader() throws IOException {
    JsonPrimitive primitive = new JsonPrimitive("test");
    JsonTreeReader reader = new JsonTreeReader(primitive);
    assertEquals(JsonToken.STRING, reader.peek());
  }

  @Test
  public void constructor_withJsonPrimitiveNumber_createsReader() throws IOException {
    JsonPrimitive primitive = new JsonPrimitive(42);
    JsonTreeReader reader = new JsonTreeReader(primitive);
    assertEquals(JsonToken.NUMBER, reader.peek());
  }

  @Test
  public void constructor_withJsonPrimitiveBoolean_createsReader() throws IOException {
    JsonPrimitive primitive = new JsonPrimitive(true);
    JsonTreeReader reader = new JsonTreeReader(primitive);
    assertEquals(JsonToken.BOOLEAN, reader.peek());
  }

  @Test
  public void constructor_withJsonNull_createsReader() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(JsonNull.INSTANCE);
    assertEquals(JsonToken.NULL, reader.peek());
  }

  // ==========================================================================
  // beginArray() / endArray() tests
  // ==========================================================================

  @Test
  public void beginArray_onEmptyArray_succeeds() throws IOException {
    JsonArray array = new JsonArray();
    JsonTreeReader reader = new JsonTreeReader(array);
    reader.beginArray();
    assertEquals(JsonToken.END_ARRAY, reader.peek());
    reader.endArray();
    assertEquals(JsonToken.END_DOCUMENT, reader.peek());
  }

  @Test
  public void beginArray_onArrayWithElements_allowsIteration() throws IOException {
    JsonArray array = new JsonArray();
    array.add(1);
    array.add(2);
    array.add(3);
    JsonTreeReader reader = new JsonTreeReader(array);
    reader.beginArray();
    assertEquals(1, reader.nextInt());
    assertEquals(2, reader.nextInt());
    assertEquals(3, reader.nextInt());
    reader.endArray();
    assertEquals(JsonToken.END_DOCUMENT, reader.peek());
  }

  @Test
  public void beginArray_onNonArray_throwsException() throws IOException {
    JsonObject obj = new JsonObject();
    JsonTreeReader reader = new JsonTreeReader(obj);
    try {
      reader.beginArray();
      fail("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertTrue(e.getMessage().contains("Expected BEGIN_ARRAY"));
    }
  }

  @Test
  public void endArray_onNonEndArray_throwsException() throws IOException {
    JsonArray array = new JsonArray();
    array.add(1);
    JsonTreeReader reader = new JsonTreeReader(array);
    reader.beginArray();
    try {
      reader.endArray();
      fail("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertTrue(e.getMessage().contains("Expected END_ARRAY"));
    }
  }

  // ==========================================================================
  // beginObject() / endObject() tests
  // ==========================================================================

  @Test
  public void beginObject_onEmptyObject_succeeds() throws IOException {
    JsonObject obj = new JsonObject();
    JsonTreeReader reader = new JsonTreeReader(obj);
    reader.beginObject();
    assertEquals(JsonToken.END_OBJECT, reader.peek());
    reader.endObject();
    assertEquals(JsonToken.END_DOCUMENT, reader.peek());
  }

  @Test
  public void beginObject_onObjectWithProperties_allowsIteration() throws IOException {
    JsonObject obj = new JsonObject();
    obj.addProperty("name", "test");
    obj.addProperty("value", 42);
    JsonTreeReader reader = new JsonTreeReader(obj);
    reader.beginObject();
    assertEquals(JsonToken.NAME, reader.peek());
    String name1 = reader.nextName();
    if ("name".equals(name1)) {
      assertEquals("test", reader.nextString());
      assertEquals("value", reader.nextName());
      assertEquals(42, reader.nextInt());
    } else {
      assertEquals("value", name1);
      assertEquals(42, reader.nextInt());
      assertEquals("name", reader.nextName());
      assertEquals("test", reader.nextString());
    }
    reader.endObject();
    assertEquals(JsonToken.END_DOCUMENT, reader.peek());
  }

  @Test
  public void beginObject_onNonObject_throwsException() throws IOException {
    JsonArray array = new JsonArray();
    JsonTreeReader reader = new JsonTreeReader(array);
    try {
      reader.beginObject();
      fail("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertTrue(e.getMessage().contains("Expected BEGIN_OBJECT"));
    }
  }

  @Test
  public void endObject_onNonEndObject_throwsException() throws IOException {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", "value");
    JsonTreeReader reader = new JsonTreeReader(obj);
    reader.beginObject();
    try {
      reader.endObject();
      fail("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertTrue(e.getMessage().contains("Expected END_OBJECT"));
    }
  }

  // ==========================================================================
  // hasNext() tests
  // ==========================================================================

  @Test
  public void hasNext_inEmptyArray_returnsFalse() throws IOException {
    JsonArray array = new JsonArray();
    JsonTreeReader reader = new JsonTreeReader(array);
    reader.beginArray();
    assertFalse(reader.hasNext());
  }

  @Test
  public void hasNext_inArrayWithElements_returnsTrue() throws IOException {
    JsonArray array = new JsonArray();
    array.add(1);
    JsonTreeReader reader = new JsonTreeReader(array);
    reader.beginArray();
    assertTrue(reader.hasNext());
    reader.nextInt();
    assertFalse(reader.hasNext());
  }

  @Test
  public void hasNext_inEmptyObject_returnsFalse() throws IOException {
    JsonObject obj = new JsonObject();
    JsonTreeReader reader = new JsonTreeReader(obj);
    reader.beginObject();
    assertFalse(reader.hasNext());
  }

  @Test
  public void hasNext_inObjectWithProperties_returnsTrue() throws IOException {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", "value");
    JsonTreeReader reader = new JsonTreeReader(obj);
    reader.beginObject();
    assertTrue(reader.hasNext());
    reader.nextName();
    reader.nextString();
    assertFalse(reader.hasNext());
  }

  @Test
  public void hasNext_atEndDocument_returnsFalse() throws IOException {
    JsonPrimitive primitive = new JsonPrimitive("test");
    JsonTreeReader reader = new JsonTreeReader(primitive);
    reader.nextString();
    assertFalse(reader.hasNext());
  }

  // ==========================================================================
  // peek() tests
  // ==========================================================================

  @Test
  public void peek_onJsonObject_returnsBEGIN_OBJECT() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonObject());
    assertEquals(JsonToken.BEGIN_OBJECT, reader.peek());
  }

  @Test
  public void peek_onJsonArray_returnsBEGIN_ARRAY() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonArray());
    assertEquals(JsonToken.BEGIN_ARRAY, reader.peek());
  }

  @Test
  public void peek_onString_returnsSTRING() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive("test"));
    assertEquals(JsonToken.STRING, reader.peek());
  }

  @Test
  public void peek_onNumber_returnsNUMBER() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive(123));
    assertEquals(JsonToken.NUMBER, reader.peek());
  }

  @Test
  public void peek_onBoolean_returnsBOOLEAN() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive(true));
    assertEquals(JsonToken.BOOLEAN, reader.peek());
  }

  @Test
  public void peek_onNull_returnsNULL() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(JsonNull.INSTANCE);
    assertEquals(JsonToken.NULL, reader.peek());
  }

  @Test
  public void peek_afterEndOfDocument_returnsEND_DOCUMENT() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive("test"));
    reader.nextString();
    assertEquals(JsonToken.END_DOCUMENT, reader.peek());
  }

  @Test
  public void peek_afterClose_throwsException() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive("test"));
    reader.close();
    try {
      reader.peek();
      fail("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertTrue(e.getMessage().contains("closed"));
    }
  }

  @Test
  public void peek_onNestedArray_handlesRecursion() throws IOException {
    JsonArray outer = new JsonArray();
    JsonArray inner = new JsonArray();
    inner.add(1);
    inner.add(2);
    outer.add(inner);
    outer.add(3);

    JsonTreeReader reader = new JsonTreeReader(outer);
    reader.beginArray();
    assertEquals(JsonToken.BEGIN_ARRAY, reader.peek());
    reader.beginArray();
    assertEquals(JsonToken.NUMBER, reader.peek());
    assertEquals(1, reader.nextInt());
    assertEquals(2, reader.nextInt());
    reader.endArray();
    assertEquals(3, reader.nextInt());
    reader.endArray();
  }

  // ==========================================================================
  // nextName() tests
  // ==========================================================================

  @Test
  public void nextName_inObject_returnsPropertyName() throws IOException {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", "value");
    JsonTreeReader reader = new JsonTreeReader(obj);
    reader.beginObject();
    assertEquals("key", reader.nextName());
  }

  @Test
  public void nextName_onNonName_throwsException() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive("test"));
    try {
      reader.nextName();
      fail("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertTrue(e.getMessage().contains("Expected NAME"));
    }
  }

  @Test
  public void nextName_withSpecialCharacters_returnsCorrectName() throws IOException {
    JsonObject obj = new JsonObject();
    obj.addProperty("key with spaces", "value");
    JsonTreeReader reader = new JsonTreeReader(obj);
    reader.beginObject();
    assertEquals("key with spaces", reader.nextName());
  }

  // ==========================================================================
  // nextString() tests
  // ==========================================================================

  @Test
  public void nextString_onString_returnsValue() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive("hello"));
    assertEquals("hello", reader.nextString());
  }

  @Test
  public void nextString_onNumber_returnsStringRepresentation() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive(42));
    assertEquals("42", reader.nextString());
  }

  @Test
  public void nextString_onDecimalNumber_returnsStringRepresentation() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive(3.14));
    assertEquals("3.14", reader.nextString());
  }

  @Test
  public void nextString_onEmptyString_returnsEmptyString() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive(""));
    assertEquals("", reader.nextString());
  }

  @Test
  public void nextString_onBoolean_throwsException() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive(true));
    try {
      reader.nextString();
      fail("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertTrue(e.getMessage().contains("Expected STRING"));
    }
  }

  @Test
  public void nextString_onNull_throwsException() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(JsonNull.INSTANCE);
    try {
      reader.nextString();
      fail("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertTrue(e.getMessage().contains("Expected STRING"));
    }
  }

  @Test
  public void nextString_onObject_throwsException() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonObject());
    try {
      reader.nextString();
      fail("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertTrue(e.getMessage().contains("Expected STRING"));
    }
  }

  @Test
  public void nextString_onArray_throwsException() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonArray());
    try {
      reader.nextString();
      fail("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertTrue(e.getMessage().contains("Expected STRING"));
    }
  }

  // ==========================================================================
  // nextBoolean() tests
  // ==========================================================================

  @Test
  public void nextBoolean_onTrue_returnsTrue() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive(true));
    assertTrue(reader.nextBoolean());
  }

  @Test
  public void nextBoolean_onFalse_returnsFalse() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive(false));
    assertFalse(reader.nextBoolean());
  }

  @Test
  public void nextBoolean_onNonBoolean_throwsException() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive("true"));
    try {
      reader.nextBoolean();
      fail("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertTrue(e.getMessage().contains("Expected BOOLEAN"));
    }
  }

  // ==========================================================================
  // nextNull() tests
  // ==========================================================================

  @Test
  public void nextNull_onNull_succeeds() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(JsonNull.INSTANCE);
    reader.nextNull();
    assertEquals(JsonToken.END_DOCUMENT, reader.peek());
  }

  @Test
  public void nextNull_onNonNull_throwsException() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive("null"));
    try {
      reader.nextNull();
      fail("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertTrue(e.getMessage().contains("Expected NULL"));
    }
  }

  // ==========================================================================
  // nextDouble() tests
  // ==========================================================================

  @Test
  public void nextDouble_onInteger_returnsDouble() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive(42));
    assertEquals(42.0, reader.nextDouble(), 0.0001);
  }

  @Test
  public void nextDouble_onDecimal_returnsValue() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive(3.14159));
    assertEquals(3.14159, reader.nextDouble(), 0.00001);
  }

  @Test
  public void nextDouble_onStringNumber_returnsValue() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive("2.718"));
    assertEquals(2.718, reader.nextDouble(), 0.0001);
  }

  @Test
  public void nextDouble_onNaN_inStrictMode_throwsException() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive(Double.NaN));
    try {
      reader.nextDouble();
      fail("Expected MalformedJsonException");
    } catch (MalformedJsonException e) {
      assertTrue(e.getMessage().contains("NaN"));
    }
  }

  @Test
  public void nextDouble_onInfinity_inStrictMode_throwsException() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive(Double.POSITIVE_INFINITY));
    try {
      reader.nextDouble();
      fail("Expected MalformedJsonException");
    } catch (MalformedJsonException e) {
      assertTrue(e.getMessage().contains("infinities"));
    }
  }

  @Test
  public void nextDouble_onNaN_inLenientMode_returnsNaN() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive(Double.NaN));
    reader.setLenient(true);
    assertTrue(Double.isNaN(reader.nextDouble()));
  }

  @Test
  public void nextDouble_onPositiveInfinity_inLenientMode_returnsInfinity() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive(Double.POSITIVE_INFINITY));
    reader.setLenient(true);
    assertEquals(Double.POSITIVE_INFINITY, reader.nextDouble(), 0.0);
  }

  @Test
  public void nextDouble_onNegativeInfinity_inLenientMode_returnsInfinity() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive(Double.NEGATIVE_INFINITY));
    reader.setLenient(true);
    assertEquals(Double.NEGATIVE_INFINITY, reader.nextDouble(), 0.0);
  }

  @Test
  public void nextDouble_onBoolean_throwsException() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive(true));
    try {
      reader.nextDouble();
      fail("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertTrue(e.getMessage().contains("Expected NUMBER"));
    }
  }

  // ==========================================================================
  // nextLong() tests
  // ==========================================================================

  @Test
  public void nextLong_onInteger_returnsValue() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive(42));
    assertEquals(42L, reader.nextLong());
  }

  @Test
  public void nextLong_onLargeNumber_returnsValue() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive(Long.MAX_VALUE));
    assertEquals(Long.MAX_VALUE, reader.nextLong());
  }

  @Test
  public void nextLong_onStringNumber_returnsValue() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive("123456789"));
    assertEquals(123456789L, reader.nextLong());
  }

  @Test
  public void nextLong_onBoolean_throwsException() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive(true));
    try {
      reader.nextLong();
      fail("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertTrue(e.getMessage().contains("Expected NUMBER"));
    }
  }

  // ==========================================================================
  // nextInt() tests
  // ==========================================================================

  @Test
  public void nextInt_onInteger_returnsValue() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive(42));
    assertEquals(42, reader.nextInt());
  }

  @Test
  public void nextInt_onNegativeInteger_returnsValue() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive(-123));
    assertEquals(-123, reader.nextInt());
  }

  @Test
  public void nextInt_onStringNumber_returnsValue() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive("456"));
    assertEquals(456, reader.nextInt());
  }

  @Test
  public void nextInt_onBoolean_throwsException() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive(true));
    try {
      reader.nextInt();
      fail("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertTrue(e.getMessage().contains("Expected NUMBER"));
    }
  }

  // ==========================================================================
  // nextJsonElement() tests
  // ==========================================================================

  @Test
  public void nextJsonElement_onPrimitive_returnsElement() throws IOException {
    JsonPrimitive primitive = new JsonPrimitive("test");
    JsonTreeReader reader = new JsonTreeReader(primitive);
    JsonElement result = reader.nextJsonElement();
    assertSame(primitive, result);
  }

  @Test
  public void nextJsonElement_onObject_returnsElement() throws IOException {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", "value");
    JsonTreeReader reader = new JsonTreeReader(obj);
    JsonElement result = reader.nextJsonElement();
    assertSame(obj, result);
  }

  @Test
  public void nextJsonElement_onArray_returnsElement() throws IOException {
    JsonArray array = new JsonArray();
    array.add(1);
    array.add(2);
    JsonTreeReader reader = new JsonTreeReader(array);
    JsonElement result = reader.nextJsonElement();
    assertSame(array, result);
  }

  @Test
  public void nextJsonElement_onNull_returnsJsonNull() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(JsonNull.INSTANCE);
    JsonElement result = reader.nextJsonElement();
    assertTrue(result.isJsonNull());
  }

  @Test
  public void nextJsonElement_onName_throwsException() throws IOException {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", "value");
    JsonTreeReader reader = new JsonTreeReader(obj);
    reader.beginObject();
    try {
      reader.nextJsonElement();
      fail("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertTrue(e.getMessage().contains("Unexpected NAME"));
    }
  }

  @Test
  public void nextJsonElement_onEndArray_throwsException() throws IOException {
    JsonArray array = new JsonArray();
    JsonTreeReader reader = new JsonTreeReader(array);
    reader.beginArray();
    try {
      reader.nextJsonElement();
      fail("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertTrue(e.getMessage().contains("Unexpected END_ARRAY"));
    }
  }

  @Test
  public void nextJsonElement_onEndObject_throwsException() throws IOException {
    JsonObject obj = new JsonObject();
    JsonTreeReader reader = new JsonTreeReader(obj);
    reader.beginObject();
    try {
      reader.nextJsonElement();
      fail("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertTrue(e.getMessage().contains("Unexpected END_OBJECT"));
    }
  }

  @Test
  public void nextJsonElement_onEndDocument_throwsException() throws IOException {
    JsonPrimitive primitive = new JsonPrimitive("test");
    JsonTreeReader reader = new JsonTreeReader(primitive);
    reader.nextString();
    try {
      reader.nextJsonElement();
      fail("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertTrue(e.getMessage().contains("Unexpected END_DOCUMENT"));
    }
  }

  @Test
  public void nextJsonElement_inArray_returnsElementAndAdvances() throws IOException {
    JsonArray array = new JsonArray();
    JsonObject obj = new JsonObject();
    obj.addProperty("key", "value");
    array.add(obj);
    array.add(42);

    JsonTreeReader reader = new JsonTreeReader(array);
    reader.beginArray();
    JsonElement element = reader.nextJsonElement();
    assertSame(obj, element);
    assertEquals(42, reader.nextInt());
    reader.endArray();
  }

  // ==========================================================================
  // close() tests
  // ==========================================================================

  @Test
  public void close_preventsSubsequentReads() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive("test"));
    reader.close();
    try {
      reader.peek();
      fail("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertTrue(e.getMessage().contains("closed"));
    }
  }

  @Test
  public void close_canBeCalledMultipleTimes() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive("test"));
    reader.close();
    reader.close();
    // Should not throw
  }

  // ==========================================================================
  // skipValue() tests
  // ==========================================================================

  @Test
  public void skipValue_onPrimitive_skips() throws IOException {
    JsonArray array = new JsonArray();
    array.add(1);
    array.add(2);
    array.add(3);
    JsonTreeReader reader = new JsonTreeReader(array);
    reader.beginArray();
    reader.skipValue();
    assertEquals(2, reader.nextInt());
    reader.skipValue();
    reader.endArray();
  }

  @Test
  public void skipValue_onName_skips() throws IOException {
    JsonObject obj = new JsonObject();
    obj.addProperty("skip", "ignored");
    obj.addProperty("keep", "value");
    JsonTreeReader reader = new JsonTreeReader(obj);
    reader.beginObject();
    // Order might vary, but we can demonstrate skip works
    String name1 = reader.nextName();
    reader.skipValue();
    if (reader.hasNext()) {
      String name2 = reader.nextName();
      reader.skipValue();
    }
    reader.endObject();
  }

  @Test
  public void skipValue_onNestedObject_skips() throws IOException {
    JsonArray array = new JsonArray();
    JsonObject nested = new JsonObject();
    nested.addProperty("a", "b");
    array.add(nested);
    array.add(42);

    JsonTreeReader reader = new JsonTreeReader(array);
    reader.beginArray();
    reader.skipValue();
    assertEquals(42, reader.nextInt());
    reader.endArray();
  }

  @Test
  public void skipValue_onNestedArray_skips() throws IOException {
    JsonObject obj = new JsonObject();
    JsonArray nested = new JsonArray();
    nested.add(1);
    nested.add(2);
    obj.add("array", nested);
    obj.addProperty("number", 99);

    JsonTreeReader reader = new JsonTreeReader(obj);
    reader.beginObject();
    // Read properties and skip the array
    while (reader.hasNext()) {
      String name = reader.nextName();
      if ("array".equals(name)) {
        reader.skipValue();
      } else {
        assertEquals(99, reader.nextInt());
      }
    }
    reader.endObject();
  }

  @Test
  public void skipValue_onEndDocument_doesNothing() throws IOException {
    JsonPrimitive primitive = new JsonPrimitive("test");
    JsonTreeReader reader = new JsonTreeReader(primitive);
    reader.nextString();
    reader.skipValue(); // Should not throw
    assertEquals(JsonToken.END_DOCUMENT, reader.peek());
  }

  @Test
  public void skipValue_onEndArray_callsEndArray() throws IOException {
    JsonArray array = new JsonArray();
    JsonTreeReader reader = new JsonTreeReader(array);
    reader.beginArray();
    reader.skipValue(); // Should call endArray internally
    assertEquals(JsonToken.END_DOCUMENT, reader.peek());
  }

  @Test
  public void skipValue_onEndObject_callsEndObject() throws IOException {
    JsonObject obj = new JsonObject();
    JsonTreeReader reader = new JsonTreeReader(obj);
    reader.beginObject();
    reader.skipValue(); // Should call endObject internally
    assertEquals(JsonToken.END_DOCUMENT, reader.peek());
  }

  // ==========================================================================
  // toString() tests
  // ==========================================================================

  @Test
  public void toString_atRoot_containsClassName() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive("test"));
    String result = reader.toString();
    assertTrue(result.contains("JsonTreeReader"));
    assertTrue(result.contains("$"));
  }

  @Test
  public void toString_inObject_containsPath() throws IOException {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", "value");
    JsonTreeReader reader = new JsonTreeReader(obj);
    reader.beginObject();
    reader.nextName();
    String result = reader.toString();
    assertTrue(result.contains("key"));
  }

  @Test
  public void toString_inArray_containsPath() throws IOException {
    JsonArray array = new JsonArray();
    array.add(1);
    array.add(2);
    JsonTreeReader reader = new JsonTreeReader(array);
    reader.beginArray();
    reader.nextInt();
    String result = reader.toString();
    assertTrue(result.contains("["));
  }

  // ==========================================================================
  // promoteNameToValue() tests
  // ==========================================================================

  @Test
  public void promoteNameToValue_convertsNameToString() throws IOException {
    JsonObject obj = new JsonObject();
    obj.addProperty("keyName", "value");
    JsonTreeReader reader = new JsonTreeReader(obj);
    reader.beginObject();
    reader.promoteNameToValue();
    // After promotion, the name becomes a string value
    assertEquals("keyName", reader.nextString());
    // And the original value is still there
    assertEquals("value", reader.nextString());
    reader.endObject();
  }

  @Test
  public void promoteNameToValue_onNonName_throwsException() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive("test"));
    try {
      reader.promoteNameToValue();
      fail("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertTrue(e.getMessage().contains("Expected NAME"));
    }
  }

  @Test
  public void promoteNameToValue_withMultipleProperties_worksCorrectly() throws IOException {
    JsonObject obj = new JsonObject();
    obj.addProperty("first", 1);
    obj.addProperty("second", 2);
    JsonTreeReader reader = new JsonTreeReader(obj);
    reader.beginObject();

    // Promote first property name to value
    reader.promoteNameToValue();
    String keyAsValue1 = reader.nextString();
    int value1 = reader.nextInt();

    // Promote second property name to value
    reader.promoteNameToValue();
    String keyAsValue2 = reader.nextString();
    int value2 = reader.nextInt();

    reader.endObject();

    // Verify we got both keys and values (order may vary)
    assertTrue(
        ("first".equals(keyAsValue1) && value1 == 1)
            || ("second".equals(keyAsValue1) && value1 == 2));
    assertTrue(
        ("first".equals(keyAsValue2) && value2 == 1)
            || ("second".equals(keyAsValue2) && value2 == 2));
  }

  // ==========================================================================
  // getPath() tests
  // ==========================================================================

  @Test
  public void getPath_atRoot_returnsDollarSign() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive("test"));
    assertEquals("$", reader.getPath());
  }

  @Test
  public void getPath_inArray_returnsIndexPath() throws IOException {
    JsonArray array = new JsonArray();
    array.add(1);
    array.add(2);
    array.add(3);
    JsonTreeReader reader = new JsonTreeReader(array);
    reader.beginArray();
    assertEquals("$[0]", reader.getPath());
    reader.nextInt();
    assertEquals("$[1]", reader.getPath());
    reader.nextInt();
    assertEquals("$[2]", reader.getPath());
    reader.nextInt();
    assertEquals("$[3]", reader.getPath());
  }

  @Test
  public void getPath_inObject_returnsPropertyPath() throws IOException {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", "value");
    JsonTreeReader reader = new JsonTreeReader(obj);
    reader.beginObject();
    assertEquals("$.", reader.getPath());
    reader.nextName();
    assertTrue(reader.getPath().contains("key"));
    reader.nextString();
    // Path updates after reading the value
  }

  @Test
  public void getPath_inNestedStructure_returnsFullPath() throws IOException {
    JsonObject obj = new JsonObject();
    JsonArray array = new JsonArray();
    JsonObject nested = new JsonObject();
    nested.addProperty("deep", "value");
    array.add(nested);
    obj.add("items", array);

    JsonTreeReader reader = new JsonTreeReader(obj);
    reader.beginObject();
    reader.nextName(); // "items"
    reader.beginArray();
    reader.beginObject();
    reader.nextName(); // "deep"
    String path = reader.getPath();
    assertTrue(path.contains("items"));
    assertTrue(path.contains("[0]"));
    assertTrue(path.contains("deep"));
  }

  // ==========================================================================
  // getPreviousPath() tests
  // ==========================================================================

  @Test
  public void getPreviousPath_atRoot_returnsDollarSign() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive("test"));
    assertEquals("$", reader.getPreviousPath());
  }

  @Test
  public void getPreviousPath_inArray_returnsDecrementedIndex() throws IOException {
    JsonArray array = new JsonArray();
    array.add(1);
    array.add(2);
    array.add(3);
    JsonTreeReader reader = new JsonTreeReader(array);
    reader.beginArray();
    reader.nextInt();
    assertEquals("$[0]", reader.getPreviousPath());
    reader.nextInt();
    assertEquals("$[1]", reader.getPreviousPath());
    reader.nextInt();
    assertEquals("$[2]", reader.getPreviousPath());
  }

  @Test
  public void getPreviousPath_afterReadingValue_showsPreviousPosition() throws IOException {
    JsonArray array = new JsonArray();
    array.add("a");
    array.add("b");

    JsonTreeReader reader = new JsonTreeReader(array);
    reader.beginArray();
    assertEquals("$[0]", reader.getPath());
    reader.nextString(); // reads "a"
    assertEquals("$[0]", reader.getPreviousPath());
    assertEquals("$[1]", reader.getPath());
    reader.nextString(); // reads "b"
    assertEquals("$[1]", reader.getPreviousPath());
    assertEquals("$[2]", reader.getPath());
  }

  // ==========================================================================
  // Complex structure tests
  // ==========================================================================

  @Test
  public void complexStructure_canBeFullyTraversed() throws IOException {
    JsonObject root = new JsonObject();
    root.addProperty("string", "hello");
    root.addProperty("number", 42);
    root.addProperty("boolean", true);
    root.add("null", JsonNull.INSTANCE);

    JsonArray array = new JsonArray();
    array.add(1);
    array.add(2);
    root.add("array", array);

    JsonObject nested = new JsonObject();
    nested.addProperty("inner", "value");
    root.add("object", nested);

    JsonTreeReader reader = new JsonTreeReader(root);
    reader.beginObject();
    while (reader.hasNext()) {
      String name = reader.nextName();
      switch (name) {
        case "string":
          assertEquals("hello", reader.nextString());
          break;
        case "number":
          assertEquals(42, reader.nextInt());
          break;
        case "boolean":
          assertTrue(reader.nextBoolean());
          break;
        case "null":
          reader.nextNull();
          break;
        case "array":
          reader.beginArray();
          assertEquals(1, reader.nextInt());
          assertEquals(2, reader.nextInt());
          reader.endArray();
          break;
        case "object":
          reader.beginObject();
          assertEquals("inner", reader.nextName());
          assertEquals("value", reader.nextString());
          reader.endObject();
          break;
        default:
          fail("Unexpected property: " + name);
      }
    }
    reader.endObject();
    assertEquals(JsonToken.END_DOCUMENT, reader.peek());
  }

  @Test
  public void deeplyNestedArrays_handleStackCorrectly() throws IOException {
    // Create deeply nested array structure
    JsonArray root = new JsonArray();
    JsonArray current = root;
    for (int i = 0; i < 20; i++) {
      JsonArray nested = new JsonArray();
      current.add(nested);
      current = nested;
    }
    current.add(42);

    JsonTreeReader reader = new JsonTreeReader(root);
    for (int i = 0; i < 21; i++) {
      reader.beginArray();
    }
    assertEquals(42, reader.nextInt());
    for (int i = 0; i < 21; i++) {
      reader.endArray();
    }
    assertEquals(JsonToken.END_DOCUMENT, reader.peek());
  }

  @Test
  public void deeplyNestedObjects_handleStackCorrectly() throws IOException {
    // Create deeply nested object structure
    JsonObject root = new JsonObject();
    JsonObject current = root;
    for (int i = 0; i < 20; i++) {
      JsonObject nested = new JsonObject();
      current.add("nested", nested);
      current = nested;
    }
    current.addProperty("value", "deep");

    JsonTreeReader reader = new JsonTreeReader(root);
    for (int i = 0; i < 21; i++) {
      reader.beginObject();
      String name = reader.nextName();
      assertTrue("nested".equals(name) || "value".equals(name));
    }
    assertEquals("deep", reader.nextString());
    for (int i = 0; i < 21; i++) {
      reader.endObject();
    }
    assertEquals(JsonToken.END_DOCUMENT, reader.peek());
  }

  // ==========================================================================
  // Edge case tests
  // ==========================================================================

  @Test
  public void arrayWithMixedTypes_handlesAllTypes() throws IOException {
    JsonArray array = new JsonArray();
    array.add("string");
    array.add(42);
    array.add(3.14);
    array.add(true);
    array.add(JsonNull.INSTANCE);
    array.add(new JsonObject());
    array.add(new JsonArray());

    JsonTreeReader reader = new JsonTreeReader(array);
    reader.beginArray();
    assertEquals(JsonToken.STRING, reader.peek());
    assertEquals("string", reader.nextString());
    assertEquals(JsonToken.NUMBER, reader.peek());
    assertEquals(42, reader.nextInt());
    assertEquals(JsonToken.NUMBER, reader.peek());
    assertEquals(3.14, reader.nextDouble(), 0.001);
    assertEquals(JsonToken.BOOLEAN, reader.peek());
    assertTrue(reader.nextBoolean());
    assertEquals(JsonToken.NULL, reader.peek());
    reader.nextNull();
    assertEquals(JsonToken.BEGIN_OBJECT, reader.peek());
    reader.beginObject();
    reader.endObject();
    assertEquals(JsonToken.BEGIN_ARRAY, reader.peek());
    reader.beginArray();
    reader.endArray();
    reader.endArray();
  }

  @Test
  public void pathTracking_afterSkipValue_showsSkipped() throws IOException {
    JsonObject obj = new JsonObject();
    obj.addProperty("skipped", "value");
    obj.addProperty("kept", "data");

    JsonTreeReader reader = new JsonTreeReader(obj);
    reader.beginObject();
    // Skip the first property entirely
    reader.skipValue(); // This skips the name and pushes value, sets path to <skipped>
    reader.skipValue(); // This skips the value
    // Read the second property
    reader.nextName();
    reader.nextString();
    reader.endObject();
  }

  @Test
  public void peek_calledMultipleTimes_returnsSameToken() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive("test"));
    assertEquals(JsonToken.STRING, reader.peek());
    assertEquals(JsonToken.STRING, reader.peek());
    assertEquals(JsonToken.STRING, reader.peek());
    assertEquals("test", reader.nextString());
  }

  @Test
  public void readNumberAsMultipleTypes_worksCorrectly() throws IOException {
    // Test that number can be read as string, then create new reader for other types
    JsonTreeReader reader1 = new JsonTreeReader(new JsonPrimitive(42));
    assertEquals("42", reader1.nextString());

    JsonTreeReader reader2 = new JsonTreeReader(new JsonPrimitive(42));
    assertEquals(42, reader2.nextInt());

    JsonTreeReader reader3 = new JsonTreeReader(new JsonPrimitive(42));
    assertEquals(42L, reader3.nextLong());

    JsonTreeReader reader4 = new JsonTreeReader(new JsonPrimitive(42));
    assertEquals(42.0, reader4.nextDouble(), 0.001);
  }

  // ==========================================================================
  // Path index increment tests
  // ==========================================================================

  @Test
  public void endArray_incrementsParentPathIndex() throws IOException {
    JsonArray outer = new JsonArray();
    JsonArray inner1 = new JsonArray();
    JsonArray inner2 = new JsonArray();
    outer.add(inner1);
    outer.add(inner2);

    JsonTreeReader reader = new JsonTreeReader(outer);
    reader.beginArray();
    assertEquals("$[0]", reader.getPath());
    reader.beginArray();
    reader.endArray();
    assertEquals("$[1]", reader.getPath());
    reader.beginArray();
    reader.endArray();
    assertEquals("$[2]", reader.getPath());
  }

  @Test
  public void endObject_incrementsParentPathIndex() throws IOException {
    JsonArray outer = new JsonArray();
    outer.add(new JsonObject());
    outer.add(new JsonObject());

    JsonTreeReader reader = new JsonTreeReader(outer);
    reader.beginArray();
    assertEquals("$[0]", reader.getPath());
    reader.beginObject();
    reader.endObject();
    assertEquals("$[1]", reader.getPath());
    reader.beginObject();
    reader.endObject();
    assertEquals("$[2]", reader.getPath());
  }

  // ==========================================================================
  // Custom JsonElement subclass test
  // ==========================================================================

  @Test
  public void peek_onCustomJsonElement_throwsException() throws IOException {
    // Create a custom JsonElement subclass (this tests the error path)
    JsonElement custom =
        new JsonElement() {
          @Override
          public JsonElement deepCopy() {
            return this;
          }
        };

    JsonTreeReader reader = new JsonTreeReader(custom);
    try {
      reader.peek();
      fail("Expected MalformedJsonException");
    } catch (MalformedJsonException e) {
      assertTrue(e.getMessage().contains("Custom JsonElement subclass"));
    }
  }

}
