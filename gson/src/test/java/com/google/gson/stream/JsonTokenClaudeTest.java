/*
 * Copyright (C) 2010 Google Inc.
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

package com.google.gson.stream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test class for {@link JsonToken}.
 */
public class JsonTokenClaudeTest {

  // ==================== values() Tests ====================

  @Test
  public void testValues_returnsAllEnumConstants() {
    JsonToken[] values = JsonToken.values();

    assertEquals(10, values.length);
  }

  @Test
  public void testValues_containsAllExpectedTokens() {
    JsonToken[] values = JsonToken.values();

    JsonToken[] expected = {
      JsonToken.BEGIN_ARRAY,
      JsonToken.END_ARRAY,
      JsonToken.BEGIN_OBJECT,
      JsonToken.END_OBJECT,
      JsonToken.NAME,
      JsonToken.STRING,
      JsonToken.NUMBER,
      JsonToken.BOOLEAN,
      JsonToken.NULL,
      JsonToken.END_DOCUMENT
    };

    assertArrayEquals(expected, values);
  }

  @Test
  public void testValues_returnsNewArrayEachCall() {
    JsonToken[] values1 = JsonToken.values();
    JsonToken[] values2 = JsonToken.values();

    // values() should return a new array each time to prevent modification
    assertTrue(values1 != values2);
    assertArrayEquals(values1, values2);
  }

  @Test
  public void testValues_maintainsDeclarationOrder() {
    JsonToken[] values = JsonToken.values();

    assertEquals(JsonToken.BEGIN_ARRAY, values[0]);
    assertEquals(JsonToken.END_ARRAY, values[1]);
    assertEquals(JsonToken.BEGIN_OBJECT, values[2]);
    assertEquals(JsonToken.END_OBJECT, values[3]);
    assertEquals(JsonToken.NAME, values[4]);
    assertEquals(JsonToken.STRING, values[5]);
    assertEquals(JsonToken.NUMBER, values[6]);
    assertEquals(JsonToken.BOOLEAN, values[7]);
    assertEquals(JsonToken.NULL, values[8]);
    assertEquals(JsonToken.END_DOCUMENT, values[9]);
  }

  @Test
  public void testValues_ordinalMatchesArrayIndex() {
    JsonToken[] values = JsonToken.values();

    for (int i = 0; i < values.length; i++) {
      assertEquals(i, values[i].ordinal());
    }
  }

  // ==================== valueOf(String) Tests ====================

  @Test
  public void testValueOf_beginArray_returnsBeginArray() {
    JsonToken token = JsonToken.valueOf("BEGIN_ARRAY");

    assertSame(JsonToken.BEGIN_ARRAY, token);
  }

  @Test
  public void testValueOf_endArray_returnsEndArray() {
    JsonToken token = JsonToken.valueOf("END_ARRAY");

    assertSame(JsonToken.END_ARRAY, token);
  }

  @Test
  public void testValueOf_beginObject_returnsBeginObject() {
    JsonToken token = JsonToken.valueOf("BEGIN_OBJECT");

    assertSame(JsonToken.BEGIN_OBJECT, token);
  }

  @Test
  public void testValueOf_endObject_returnsEndObject() {
    JsonToken token = JsonToken.valueOf("END_OBJECT");

    assertSame(JsonToken.END_OBJECT, token);
  }

  @Test
  public void testValueOf_name_returnsName() {
    JsonToken token = JsonToken.valueOf("NAME");

    assertSame(JsonToken.NAME, token);
  }

  @Test
  public void testValueOf_string_returnsString() {
    JsonToken token = JsonToken.valueOf("STRING");

    assertSame(JsonToken.STRING, token);
  }

  @Test
  public void testValueOf_number_returnsNumber() {
    JsonToken token = JsonToken.valueOf("NUMBER");

    assertSame(JsonToken.NUMBER, token);
  }

  @Test
  public void testValueOf_boolean_returnsBoolean() {
    JsonToken token = JsonToken.valueOf("BOOLEAN");

    assertSame(JsonToken.BOOLEAN, token);
  }

  @Test
  public void testValueOf_null_returnsNull() {
    JsonToken token = JsonToken.valueOf("NULL");

    assertSame(JsonToken.NULL, token);
  }

  @Test
  public void testValueOf_endDocument_returnsEndDocument() {
    JsonToken token = JsonToken.valueOf("END_DOCUMENT");

    assertSame(JsonToken.END_DOCUMENT, token);
  }

  @Test
  public void testValueOf_invalidName_throwsIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class, () -> JsonToken.valueOf("INVALID"));
  }

  @Test
  public void testValueOf_lowercaseName_throwsIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class, () -> JsonToken.valueOf("begin_array"));
  }

  @Test
  public void testValueOf_emptyString_throwsIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class, () -> JsonToken.valueOf(""));
  }

  @Test
  public void testValueOf_nullName_throwsNullPointerException() {
    assertThrows(NullPointerException.class, () -> JsonToken.valueOf(null));
  }

  @Test
  public void testValueOf_nameWithWhitespace_throwsIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class, () -> JsonToken.valueOf(" BEGIN_ARRAY"));
    assertThrows(IllegalArgumentException.class, () -> JsonToken.valueOf("BEGIN_ARRAY "));
  }

  @Test
  public void testValueOf_partialName_throwsIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class, () -> JsonToken.valueOf("BEGIN"));
    assertThrows(IllegalArgumentException.class, () -> JsonToken.valueOf("ARRAY"));
  }

  // ==================== Consistency Tests ====================

  @Test
  public void testValuesAndValueOfConsistency() {
    // Every value from values() should be retrievable via valueOf()
    for (JsonToken token : JsonToken.values()) {
      JsonToken retrieved = JsonToken.valueOf(token.name());
      assertSame(token, retrieved);
    }
  }

  @Test
  public void testEnumConstantsAreNotNull() {
    for (JsonToken token : JsonToken.values()) {
      assertNotNull(token);
      assertNotNull(token.name());
    }
  }

  @Test
  public void testEnumNameMatchesConstantName() {
    assertEquals("BEGIN_ARRAY", JsonToken.BEGIN_ARRAY.name());
    assertEquals("END_ARRAY", JsonToken.END_ARRAY.name());
    assertEquals("BEGIN_OBJECT", JsonToken.BEGIN_OBJECT.name());
    assertEquals("END_OBJECT", JsonToken.END_OBJECT.name());
    assertEquals("NAME", JsonToken.NAME.name());
    assertEquals("STRING", JsonToken.STRING.name());
    assertEquals("NUMBER", JsonToken.NUMBER.name());
    assertEquals("BOOLEAN", JsonToken.BOOLEAN.name());
    assertEquals("NULL", JsonToken.NULL.name());
    assertEquals("END_DOCUMENT", JsonToken.END_DOCUMENT.name());
  }
}
