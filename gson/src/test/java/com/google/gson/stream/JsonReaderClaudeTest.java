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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.google.gson.Strictness;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import org.junit.Test;

/**
 * Test class for {@link JsonReader}.
 */
public class JsonReaderClaudeTest {

  // ==================== Constructor Tests ====================

  @Test
  public void testConstructor_nullReader_throwsNullPointerException() {
    assertThrows(NullPointerException.class, () -> new JsonReader(null));
  }

  @Test
  public void testConstructor_validReader_createsInstance() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{}"))) {
      assertNotNull(reader);
    }
  }

  // ==================== setLenient/isLenient Tests ====================

  @Test
  @SuppressWarnings("deprecation")
  public void testSetLenient_true_setsLenientMode() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{}"))) {
      reader.setLenient(true);
      assertTrue(reader.isLenient());
      assertEquals(Strictness.LENIENT, reader.getStrictness());
    }
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testSetLenient_false_setsLegacyStrictMode() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{}"))) {
      reader.setLenient(true);
      reader.setLenient(false);
      assertFalse(reader.isLenient());
      assertEquals(Strictness.LEGACY_STRICT, reader.getStrictness());
    }
  }

  @Test
  public void testIsLenient_defaultValue_returnsFalse() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{}"))) {
      assertFalse(reader.isLenient());
    }
  }

  // ==================== setStrictness/getStrictness Tests ====================

  @Test
  public void testSetStrictness_null_throwsNullPointerException() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{}"))) {
      assertThrows(NullPointerException.class, () -> reader.setStrictness(null));
    }
  }

  @Test
  public void testSetStrictness_lenient_setsLenientMode() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{}"))) {
      reader.setStrictness(Strictness.LENIENT);
      assertEquals(Strictness.LENIENT, reader.getStrictness());
      assertTrue(reader.isLenient());
    }
  }

  @Test
  public void testSetStrictness_legacyStrict_setsLegacyStrictMode() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{}"))) {
      reader.setStrictness(Strictness.LEGACY_STRICT);
      assertEquals(Strictness.LEGACY_STRICT, reader.getStrictness());
      assertFalse(reader.isLenient());
    }
  }

  @Test
  public void testSetStrictness_strict_setsStrictMode() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{}"))) {
      reader.setStrictness(Strictness.STRICT);
      assertEquals(Strictness.STRICT, reader.getStrictness());
      assertFalse(reader.isLenient());
    }
  }

  @Test
  public void testGetStrictness_defaultValue_returnsLegacyStrict() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{}"))) {
      assertEquals(Strictness.LEGACY_STRICT, reader.getStrictness());
    }
  }

  // ==================== setNestingLimit/getNestingLimit Tests ====================

  @Test
  public void testSetNestingLimit_negativeValue_throwsIllegalArgumentException() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{}"))) {
      IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
          () -> reader.setNestingLimit(-1));
      assertTrue(ex.getMessage().contains("Invalid nesting limit: -1"));
    }
  }

  @Test
  public void testSetNestingLimit_zero_succeeds() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{}"))) {
      reader.setNestingLimit(0);
      assertEquals(0, reader.getNestingLimit());
    }
  }

  @Test
  public void testSetNestingLimit_positiveValue_succeeds() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{}"))) {
      reader.setNestingLimit(100);
      assertEquals(100, reader.getNestingLimit());
    }
  }

  @Test
  public void testGetNestingLimit_defaultValue_returns255() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{}"))) {
      assertEquals(JsonReader.DEFAULT_NESTING_LIMIT, reader.getNestingLimit());
      assertEquals(255, reader.getNestingLimit());
    }
  }

  @Test
  public void testNestingLimit_exceededOnArray_throwsMalformedJsonException() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("[[]]"))) {
      reader.setNestingLimit(1);
      reader.beginArray();
      MalformedJsonException ex = assertThrows(MalformedJsonException.class,
          () -> reader.beginArray());
      assertTrue(ex.getMessage().contains("Nesting limit 1 reached"));
    }
  }

  @Test
  public void testNestingLimit_exceededOnObject_throwsMalformedJsonException() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{\"a\":{}}"))) {
      reader.setNestingLimit(1);
      reader.beginObject();
      assertEquals("a", reader.nextName());
      MalformedJsonException ex = assertThrows(MalformedJsonException.class,
          () -> reader.beginObject());
      assertTrue(ex.getMessage().contains("Nesting limit 1 reached"));
    }
  }

  // ==================== beginArray/endArray Tests ====================

  @Test
  public void testBeginArray_validJson_succeeds() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("[1,2,3]"))) {
      reader.beginArray();
      assertEquals(JsonToken.NUMBER, reader.peek());
    }
  }

  @Test
  public void testBeginArray_notArray_throwsIllegalStateException() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{}"))) {
      IllegalStateException ex = assertThrows(IllegalStateException.class,
          () -> reader.beginArray());
      assertTrue(ex.getMessage().contains("Expected BEGIN_ARRAY but was BEGIN_OBJECT"));
    }
  }

  @Test
  public void testEndArray_validJson_succeeds() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("[]"))) {
      reader.beginArray();
      reader.endArray();
      assertEquals(JsonToken.END_DOCUMENT, reader.peek());
    }
  }

  @Test
  public void testEndArray_notAtEndOfArray_throwsIllegalStateException() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("[1]"))) {
      reader.beginArray();
      IllegalStateException ex = assertThrows(IllegalStateException.class,
          () -> reader.endArray());
      assertTrue(ex.getMessage().contains("Expected END_ARRAY but was NUMBER"));
    }
  }

  @Test
  public void testArrayWithElements_iterateAll_succeeds() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("[1,2,3]"))) {
      reader.beginArray();
      assertEquals(1, reader.nextInt());
      assertEquals(2, reader.nextInt());
      assertEquals(3, reader.nextInt());
      reader.endArray();
    }
  }

  // ==================== beginObject/endObject Tests ====================

  @Test
  public void testBeginObject_validJson_succeeds() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{\"key\":\"value\"}"))) {
      reader.beginObject();
      assertEquals(JsonToken.NAME, reader.peek());
    }
  }

  @Test
  public void testBeginObject_notObject_throwsIllegalStateException() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("[]"))) {
      IllegalStateException ex = assertThrows(IllegalStateException.class,
          () -> reader.beginObject());
      assertTrue(ex.getMessage().contains("Expected BEGIN_OBJECT but was BEGIN_ARRAY"));
    }
  }

  @Test
  public void testEndObject_validJson_succeeds() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{}"))) {
      reader.beginObject();
      reader.endObject();
      assertEquals(JsonToken.END_DOCUMENT, reader.peek());
    }
  }

  @Test
  public void testEndObject_notAtEndOfObject_throwsIllegalStateException() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{\"key\":\"value\"}"))) {
      reader.beginObject();
      IllegalStateException ex = assertThrows(IllegalStateException.class,
          () -> reader.endObject());
      assertTrue(ex.getMessage().contains("Expected END_OBJECT but was NAME"));
    }
  }

  @Test
  public void testObjectWithProperties_iterateAll_succeeds() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{\"a\":1,\"b\":2}"))) {
      reader.beginObject();
      assertEquals("a", reader.nextName());
      assertEquals(1, reader.nextInt());
      assertEquals("b", reader.nextName());
      assertEquals(2, reader.nextInt());
      reader.endObject();
    }
  }

  // ==================== hasNext Tests ====================

  @Test
  public void testHasNext_emptyArray_returnsFalse() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("[]"))) {
      reader.beginArray();
      assertFalse(reader.hasNext());
    }
  }

  @Test
  public void testHasNext_nonEmptyArray_returnsTrue() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("[1]"))) {
      reader.beginArray();
      assertTrue(reader.hasNext());
    }
  }

  @Test
  public void testHasNext_emptyObject_returnsFalse() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{}"))) {
      reader.beginObject();
      assertFalse(reader.hasNext());
    }
  }

  @Test
  public void testHasNext_nonEmptyObject_returnsTrue() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{\"key\":1}"))) {
      reader.beginObject();
      assertTrue(reader.hasNext());
    }
  }

  @Test
  public void testHasNext_afterLastElement_returnsFalse() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("[1,2]"))) {
      reader.beginArray();
      var unused1 = reader.nextInt();
      assertTrue(reader.hasNext());
      var unused2 = reader.nextInt();
      assertFalse(reader.hasNext());
    }
  }

  // ==================== peek Tests ====================

  @Test
  public void testPeek_beginArray_returnsBeginArray() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("[]"))) {
      assertEquals(JsonToken.BEGIN_ARRAY, reader.peek());
    }
  }

  @Test
  public void testPeek_beginObject_returnsBeginObject() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{}"))) {
      assertEquals(JsonToken.BEGIN_OBJECT, reader.peek());
    }
  }

  @Test
  public void testPeek_string_returnsString() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("\"hello\""))) {
      assertEquals(JsonToken.STRING, reader.peek());
    }
  }

  @Test
  public void testPeek_number_returnsNumber() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("123"))) {
      assertEquals(JsonToken.NUMBER, reader.peek());
    }
  }

  @Test
  public void testPeek_booleanTrue_returnsBoolean() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("true"))) {
      assertEquals(JsonToken.BOOLEAN, reader.peek());
    }
  }

  @Test
  public void testPeek_booleanFalse_returnsBoolean() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("false"))) {
      assertEquals(JsonToken.BOOLEAN, reader.peek());
    }
  }

  @Test
  public void testPeek_null_returnsNull() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("null"))) {
      assertEquals(JsonToken.NULL, reader.peek());
    }
  }

  @Test
  public void testPeek_endArray_returnsEndArray() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("[]"))) {
      reader.beginArray();
      assertEquals(JsonToken.END_ARRAY, reader.peek());
    }
  }

  @Test
  public void testPeek_endObject_returnsEndObject() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{}"))) {
      reader.beginObject();
      assertEquals(JsonToken.END_OBJECT, reader.peek());
    }
  }

  @Test
  public void testPeek_name_returnsName() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{\"key\":1}"))) {
      reader.beginObject();
      assertEquals(JsonToken.NAME, reader.peek());
    }
  }

  @Test
  public void testPeek_endOfDocument_returnsEndDocument() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("true"))) {
      var unused = reader.nextBoolean();
      assertEquals(JsonToken.END_DOCUMENT, reader.peek());
    }
  }

  @Test
  public void testPeek_calledMultipleTimes_returnsSameResult() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{}"))) {
      assertEquals(JsonToken.BEGIN_OBJECT, reader.peek());
      assertEquals(JsonToken.BEGIN_OBJECT, reader.peek());
      assertEquals(JsonToken.BEGIN_OBJECT, reader.peek());
    }
  }

  // ==================== nextName Tests ====================

  @Test
  public void testNextName_validName_returnsName() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{\"myKey\":1}"))) {
      reader.beginObject();
      assertEquals("myKey", reader.nextName());
    }
  }

  @Test
  public void testNextName_notName_throwsIllegalStateException() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("[1]"))) {
      reader.beginArray();
      IllegalStateException ex = assertThrows(IllegalStateException.class,
          () -> reader.nextName());
      assertTrue(ex.getMessage().contains("Expected a name but was NUMBER"));
    }
  }

  @Test
  public void testNextName_emptyName_returnsEmptyString() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{\"\":1}"))) {
      reader.beginObject();
      assertEquals("", reader.nextName());
    }
  }

  @Test
  public void testNextName_escapedCharacters_unescapes() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{\"hello\\nworld\":1}"))) {
      reader.beginObject();
      assertEquals("hello\nworld", reader.nextName());
    }
  }

  @Test
  public void testNextName_singleQuoted_requiresLenient() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{'key':1}"))) {
      reader.setStrictness(Strictness.LENIENT);
      reader.beginObject();
      assertEquals("key", reader.nextName());
    }
  }

  @Test
  public void testNextName_unquoted_requiresLenient() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{key:1}"))) {
      reader.setStrictness(Strictness.LENIENT);
      reader.beginObject();
      assertEquals("key", reader.nextName());
    }
  }

  // ==================== nextString Tests ====================

  @Test
  public void testNextString_validString_returnsString() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("\"hello\""))) {
      assertEquals("hello", reader.nextString());
    }
  }

  @Test
  public void testNextString_emptyString_returnsEmptyString() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("\"\""))) {
      assertEquals("", reader.nextString());
    }
  }

  @Test
  public void testNextString_notString_throwsIllegalStateException() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("true"))) {
      IllegalStateException ex = assertThrows(IllegalStateException.class,
          () -> reader.nextString());
      assertTrue(ex.getMessage().contains("Expected a string but was BOOLEAN"));
    }
  }

  @Test
  public void testNextString_escapedCharacters_unescapes() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("\"hello\\tworld\\n\""))) {
      assertEquals("hello\tworld\n", reader.nextString());
    }
  }

  @Test
  public void testNextString_unicodeEscape_unescapes() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("\"\\u0041\\u0042\\u0043\""))) {
      assertEquals("ABC", reader.nextString());
    }
  }

  @Test
  public void testNextString_number_convertsToString() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("123"))) {
      assertEquals("123", reader.nextString());
    }
  }

  @Test
  public void testNextString_longNumber_convertsToString() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("9007199254740993"))) {
      assertEquals("9007199254740993", reader.nextString());
    }
  }

  @Test
  public void testNextString_singleQuoted_requiresLenient() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("'hello'"))) {
      reader.setStrictness(Strictness.LENIENT);
      assertEquals("hello", reader.nextString());
    }
  }

  // ==================== nextBoolean Tests ====================

  @Test
  public void testNextBoolean_true_returnsTrue() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("true"))) {
      assertTrue(reader.nextBoolean());
    }
  }

  @Test
  public void testNextBoolean_false_returnsFalse() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("false"))) {
      assertFalse(reader.nextBoolean());
    }
  }

  @Test
  public void testNextBoolean_notBoolean_throwsIllegalStateException() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("\"true\""))) {
      IllegalStateException ex = assertThrows(IllegalStateException.class,
          () -> reader.nextBoolean());
      assertTrue(ex.getMessage().contains("Expected a boolean but was STRING"));
    }
  }

  @Test
  public void testNextBoolean_upperCaseTrue_succeedsInLegacyStrictMode() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("TRUE"))) {
      reader.setStrictness(Strictness.LEGACY_STRICT);
      assertTrue(reader.nextBoolean());
    }
  }

  @Test
  public void testNextBoolean_upperCaseTrue_failsInStrictMode() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("TRUE"))) {
      reader.setStrictness(Strictness.STRICT);
      assertThrows(MalformedJsonException.class, () -> reader.nextBoolean());
    }
  }

  @Test
  public void testNextBoolean_mixedCase_succeedsInLegacyStrictMode() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("TrUe"))) {
      reader.setStrictness(Strictness.LEGACY_STRICT);
      assertTrue(reader.nextBoolean());
    }
  }

  // ==================== nextNull Tests ====================

  @Test
  public void testNextNull_validNull_succeeds() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("null"))) {
      reader.nextNull();
      assertEquals(JsonToken.END_DOCUMENT, reader.peek());
    }
  }

  @Test
  public void testNextNull_notNull_throwsIllegalStateException() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("true"))) {
      IllegalStateException ex = assertThrows(IllegalStateException.class,
          () -> reader.nextNull());
      assertTrue(ex.getMessage().contains("Expected null but was BOOLEAN"));
    }
  }

  @Test
  public void testNextNull_upperCaseNull_succeedsInLegacyStrictMode() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("NULL"))) {
      reader.setStrictness(Strictness.LEGACY_STRICT);
      reader.nextNull();
    }
  }

  @Test
  public void testNextNull_upperCaseNull_failsInStrictMode() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("NULL"))) {
      reader.setStrictness(Strictness.STRICT);
      assertThrows(MalformedJsonException.class, () -> reader.nextNull());
    }
  }

  // ==================== nextDouble Tests ====================

  @Test
  public void testNextDouble_integer_returnsDouble() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("123"))) {
      assertEquals(123.0, reader.nextDouble(), 0.0);
    }
  }

  @Test
  public void testNextDouble_decimal_returnsDouble() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("123.456"))) {
      assertEquals(123.456, reader.nextDouble(), 0.0001);
    }
  }

  @Test
  public void testNextDouble_negative_returnsDouble() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("-123.456"))) {
      assertEquals(-123.456, reader.nextDouble(), 0.0001);
    }
  }

  @Test
  public void testNextDouble_scientificNotation_returnsDouble() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("1.23e10"))) {
      assertEquals(1.23e10, reader.nextDouble(), 0.0001);
    }
  }

  @Test
  public void testNextDouble_stringNumber_parsesString() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("\"123.456\""))) {
      assertEquals(123.456, reader.nextDouble(), 0.0001);
    }
  }

  @Test
  public void testNextDouble_notNumber_throwsIllegalStateException() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("true"))) {
      IllegalStateException ex = assertThrows(IllegalStateException.class,
          () -> reader.nextDouble());
      assertTrue(ex.getMessage().contains("Expected a double but was BOOLEAN"));
    }
  }

  @Test
  public void testNextDouble_nan_requiresLenient() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("NaN"))) {
      reader.setStrictness(Strictness.LENIENT);
      assertTrue(Double.isNaN(reader.nextDouble()));
    }
  }

  @Test
  public void testNextDouble_nan_failsInLegacyStrictMode() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("NaN"))) {
      reader.setStrictness(Strictness.LEGACY_STRICT);
      assertThrows(MalformedJsonException.class, () -> reader.nextDouble());
    }
  }

  @Test
  public void testNextDouble_infinity_requiresLenient() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("Infinity"))) {
      reader.setStrictness(Strictness.LENIENT);
      assertTrue(Double.isInfinite(reader.nextDouble()));
    }
  }

  @Test
  public void testNextDouble_negativeInfinity_requiresLenient() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("-Infinity"))) {
      reader.setStrictness(Strictness.LENIENT);
      assertEquals(Double.NEGATIVE_INFINITY, reader.nextDouble(), 0.0);
    }
  }

  @Test
  public void testNextDouble_stringNan_failsInLegacyStrictMode() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("\"NaN\""))) {
      reader.setStrictness(Strictness.LEGACY_STRICT);
      assertThrows(MalformedJsonException.class, () -> reader.nextDouble());
    }
  }

  @Test
  public void testNextDouble_zero_returnsZero() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("0"))) {
      assertEquals(0.0, reader.nextDouble(), 0.0);
    }
  }

  @Test
  public void testNextDouble_negativeZero_returnsNegativeZero() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("-0.0"))) {
      double result = reader.nextDouble();
      assertEquals(0.0, result, 0.0);
      assertEquals(Double.NEGATIVE_INFINITY, 1.0 / result, 0.0);
    }
  }

  // ==================== nextLong Tests ====================

  @Test
  public void testNextLong_validLong_returnsLong() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("123"))) {
      assertEquals(123L, reader.nextLong());
    }
  }

  @Test
  public void testNextLong_negativeLong_returnsLong() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("-123"))) {
      assertEquals(-123L, reader.nextLong());
    }
  }

  @Test
  public void testNextLong_maxLong_returnsLong() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("9223372036854775807"))) {
      assertEquals(Long.MAX_VALUE, reader.nextLong());
    }
  }

  @Test
  public void testNextLong_minLong_returnsLong() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("-9223372036854775808"))) {
      assertEquals(Long.MIN_VALUE, reader.nextLong());
    }
  }

  @Test
  public void testNextLong_stringNumber_parsesString() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("\"123\""))) {
      assertEquals(123L, reader.nextLong());
    }
  }

  @Test
  public void testNextLong_notNumber_throwsIllegalStateException() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("true"))) {
      IllegalStateException ex = assertThrows(IllegalStateException.class,
          () -> reader.nextLong());
      assertTrue(ex.getMessage().contains("Expected a long but was BOOLEAN"));
    }
  }

  @Test
  public void testNextLong_decimalNumber_throwsNumberFormatException() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("123.5"))) {
      assertThrows(NumberFormatException.class, () -> reader.nextLong());
    }
  }

  @Test
  public void testNextLong_decimalWholeNumber_succeeds() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("123.0"))) {
      assertEquals(123L, reader.nextLong());
    }
  }

  // ==================== nextInt Tests ====================

  @Test
  public void testNextInt_validInt_returnsInt() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("123"))) {
      assertEquals(123, reader.nextInt());
    }
  }

  @Test
  public void testNextInt_negativeInt_returnsInt() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("-123"))) {
      assertEquals(-123, reader.nextInt());
    }
  }

  @Test
  public void testNextInt_maxInt_returnsInt() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("2147483647"))) {
      assertEquals(Integer.MAX_VALUE, reader.nextInt());
    }
  }

  @Test
  public void testNextInt_minInt_returnsInt() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("-2147483648"))) {
      assertEquals(Integer.MIN_VALUE, reader.nextInt());
    }
  }

  @Test
  public void testNextInt_stringNumber_parsesString() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("\"123\""))) {
      assertEquals(123, reader.nextInt());
    }
  }

  @Test
  public void testNextInt_notNumber_throwsIllegalStateException() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("true"))) {
      IllegalStateException ex = assertThrows(IllegalStateException.class,
          () -> reader.nextInt());
      assertTrue(ex.getMessage().contains("Expected an int but was BOOLEAN"));
    }
  }

  @Test
  public void testNextInt_overflow_throwsNumberFormatException() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("2147483648"))) {
      assertThrows(NumberFormatException.class, () -> reader.nextInt());
    }
  }

  @Test
  public void testNextInt_decimalWholeNumber_succeeds() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("123.0"))) {
      assertEquals(123, reader.nextInt());
    }
  }

  @Test
  public void testNextInt_decimalNumber_throwsNumberFormatException() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("123.5"))) {
      assertThrows(NumberFormatException.class, () -> reader.nextInt());
    }
  }

  // ==================== close Tests ====================

  @Test
  public void testClose_succeeds() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    reader.close();
  }

  @Test
  public void testClose_closesUnderlyingReader() throws IOException {
    final boolean[] closed = {false};
    Reader underlyingReader = new StringReader("{}") {
      @Override
      public void close() {
        closed[0] = true;
      }
    };
    JsonReader reader = new JsonReader(underlyingReader);
    reader.close();
    assertTrue(closed[0]);
  }

  @Test
  public void testClose_afterClose_operationsFail() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    reader.close();
    assertThrows(IllegalStateException.class, () -> reader.peek());
  }

  // ==================== skipValue Tests ====================

  @Test
  public void testSkipValue_string_skips() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("[\"hello\",1]"))) {
      reader.beginArray();
      reader.skipValue();
      assertEquals(1, reader.nextInt());
    }
  }

  @Test
  public void testSkipValue_number_skips() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("[123,1]"))) {
      reader.beginArray();
      reader.skipValue();
      assertEquals(1, reader.nextInt());
    }
  }

  @Test
  public void testSkipValue_boolean_skips() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("[true,1]"))) {
      reader.beginArray();
      reader.skipValue();
      assertEquals(1, reader.nextInt());
    }
  }

  @Test
  public void testSkipValue_null_skips() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("[null,1]"))) {
      reader.beginArray();
      reader.skipValue();
      assertEquals(1, reader.nextInt());
    }
  }

  @Test
  public void testSkipValue_array_skipsEntireArray() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("[[1,2,3],1]"))) {
      reader.beginArray();
      reader.skipValue();
      assertEquals(1, reader.nextInt());
    }
  }

  @Test
  public void testSkipValue_object_skipsEntireObject() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("[{\"a\":1,\"b\":2},1]"))) {
      reader.beginArray();
      reader.skipValue();
      assertEquals(1, reader.nextInt());
    }
  }

  @Test
  public void testSkipValue_nestedStructure_skipsAll() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("[{\"a\":[1,2,{\"b\":3}]},1]"))) {
      reader.beginArray();
      reader.skipValue();
      assertEquals(1, reader.nextInt());
    }
  }

  @Test
  public void testSkipValue_name_skipsName() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{\"key\":1}"))) {
      reader.beginObject();
      reader.skipValue();
      assertEquals(1, reader.nextInt());
    }
  }

  @Test
  public void testSkipValue_endOfDocument_noEffect() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("null"))) {
      reader.nextNull();
      assertEquals(JsonToken.END_DOCUMENT, reader.peek());
      reader.skipValue();
      assertEquals(JsonToken.END_DOCUMENT, reader.peek());
    }
  }

  @Test
  public void testSkipValue_endArray_skipsEndArray() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("[[]]"))) {
      reader.beginArray();
      reader.beginArray();
      reader.endArray();
      reader.skipValue();
    }
  }

  @Test
  public void testSkipValue_endObject_skipsEndObject() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("[{}]"))) {
      reader.beginArray();
      reader.beginObject();
      reader.endObject();
      reader.skipValue();
    }
  }

  // ==================== toString Tests ====================

  @Test
  public void testToString_includesClassName() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{}"))) {
      String str = reader.toString();
      assertTrue(str.contains("JsonReader"));
    }
  }

  @Test
  public void testToString_includesLocationInfo() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{}"))) {
      String str = reader.toString();
      assertTrue(str.contains("line"));
      assertTrue(str.contains("column"));
    }
  }

  @Test
  public void testToString_includesPath() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{}"))) {
      String str = reader.toString();
      assertTrue(str.contains("path"));
    }
  }

  // ==================== locationString Tests ====================

  @Test
  public void testLocationString_atStart_showsLine1Column1() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{}"))) {
      // Call peek to initialize peeked state
      var unused = reader.peek();
      String location = reader.locationString();
      assertTrue(location.contains("line 1"));
      assertTrue(location.contains("column"));
    }
  }

  @Test
  public void testLocationString_afterNewline_updatesLineNumber() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("[\n1]"))) {
      reader.beginArray();
      var unused = reader.peek();
      String location = reader.locationString();
      assertTrue(location.contains("line 2"));
    }
  }

  // ==================== getPath Tests ====================

  @Test
  public void testGetPath_atRoot_returnsDollar() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{}"))) {
      assertEquals("$", reader.getPath());
    }
  }

  @Test
  public void testGetPath_inObject_includesPropertyName() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{\"key\":1}"))) {
      reader.beginObject();
      var unused = reader.nextName();
      assertEquals("$.key", reader.getPath());
    }
  }

  @Test
  public void testGetPath_inArray_includesIndex() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("[1,2,3]"))) {
      reader.beginArray();
      assertEquals("$[0]", reader.getPath());
      var unused1 = reader.nextInt();
      assertEquals("$[1]", reader.getPath());
      var unused2 = reader.nextInt();
      assertEquals("$[2]", reader.getPath());
    }
  }

  @Test
  public void testGetPath_nestedStructure_includesFullPath() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{\"a\":{\"b\":[1,2]}}"))) {
      reader.beginObject();
      var unused1 = reader.nextName();
      reader.beginObject();
      var unused2 = reader.nextName();
      reader.beginArray();
      var unused3 = reader.nextInt();
      assertEquals("$.a.b[1]", reader.getPath());
    }
  }

  @Test
  public void testGetPath_afterSkipValue_updatesPath() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{\"a\":1,\"b\":2}"))) {
      reader.beginObject();
      reader.skipValue();  // Skip "a"
      reader.skipValue();  // Skip 1
      String name = reader.nextName();
      assertEquals("b", name);
      assertEquals("$.b", reader.getPath());
    }
  }

  // ==================== getPreviousPath Tests ====================

  @Test
  public void testGetPreviousPath_atRoot_returnsDollar() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{}"))) {
      assertEquals("$", reader.getPreviousPath());
    }
  }

  @Test
  public void testGetPreviousPath_inArray_returnsCurrentIndex() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("[1,2,3]"))) {
      reader.beginArray();
      assertEquals("$[0]", reader.getPreviousPath());
      var unused1 = reader.nextInt();
      assertEquals("$[0]", reader.getPreviousPath());
      var unused2 = reader.nextInt();
      assertEquals("$[1]", reader.getPreviousPath());
    }
  }

  @Test
  public void testGetPreviousPath_afterReadingValue_returnsLastIndex() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("[1,2]"))) {
      reader.beginArray();
      var unused1 = reader.nextInt();
      var unused2 = reader.nextInt();
      assertEquals("$[1]", reader.getPreviousPath());
    }
  }

  @Test
  public void testGetPreviousPath_afterReadingProperty_returnsPropertyPath() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{\"a\":1,\"b\":2}"))) {
      reader.beginObject();
      var unused1 = reader.nextName();
      var unused2 = reader.nextInt();
      assertEquals("$.a", reader.getPreviousPath());
      var unused3 = reader.nextName();
      var unused4 = reader.nextInt();
      assertEquals("$.b", reader.getPreviousPath());
    }
  }

  // ==================== doPeek Tests (indirect via various scenarios) ====================

  @Test
  public void testDoPeek_commentSlashSlash_requiresLenient() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("// comment\n1"))) {
      reader.setStrictness(Strictness.LENIENT);
      assertEquals(1, reader.nextInt());
    }
  }

  @Test
  public void testDoPeek_commentHash_requiresLenient() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("# comment\n1"))) {
      reader.setStrictness(Strictness.LENIENT);
      assertEquals(1, reader.nextInt());
    }
  }

  @Test
  public void testDoPeek_cStyleComment_requiresLenient() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("/* comment */1"))) {
      reader.setStrictness(Strictness.LENIENT);
      assertEquals(1, reader.nextInt());
    }
  }

  @Test
  public void testDoPeek_semicolonSeparator_requiresLenient() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("[1;2]"))) {
      reader.setStrictness(Strictness.LENIENT);
      reader.beginArray();
      assertEquals(1, reader.nextInt());
      assertEquals(2, reader.nextInt());
    }
  }

  @Test
  public void testDoPeek_equalSeparator_requiresLenient() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{a=1}"))) {
      reader.setStrictness(Strictness.LENIENT);
      reader.beginObject();
      assertEquals("a", reader.nextName());
      assertEquals(1, reader.nextInt());
    }
  }

  @Test
  public void testDoPeek_arrowSeparator_requiresLenient() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{a=>1}"))) {
      reader.setStrictness(Strictness.LENIENT);
      reader.beginObject();
      assertEquals("a", reader.nextName());
      assertEquals(1, reader.nextInt());
    }
  }

  @Test
  public void testDoPeek_trailingCommaInArray_requiresLenient() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("[1,]"))) {
      reader.setStrictness(Strictness.LENIENT);
      reader.beginArray();
      assertEquals(1, reader.nextInt());
      reader.nextNull();
      reader.endArray();
    }
  }

  @Test
  public void testDoPeek_nonExecutePrefix_requiresLenient() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader(")]}'\n{}"))) {
      reader.setStrictness(Strictness.LENIENT);
      reader.beginObject();
      reader.endObject();
    }
  }

  @Test
  public void testDoPeek_multipleTopLevelValues_requiresLenient() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("1 2 3"))) {
      reader.setStrictness(Strictness.LENIENT);
      assertEquals(1, reader.nextInt());
      assertEquals(2, reader.nextInt());
      assertEquals(3, reader.nextInt());
    }
  }

  // ==================== Edge cases and error conditions ====================

  @Test
  public void testUnterminatedString_throwsMalformedJsonException() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("\"unterminated"))) {
      assertThrows(MalformedJsonException.class, () -> reader.nextString());
    }
  }

  @Test
  public void testUnterminatedArray_throwsEOFException() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("[1,2"))) {
      reader.beginArray();
      var unused1 = reader.nextInt();
      var unused2 = reader.nextInt();
      assertThrows(java.io.EOFException.class, () -> reader.peek());
    }
  }

  @Test
  public void testUnterminatedObject_throwsEOFException() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{\"a\":1"))) {
      reader.beginObject();
      var unused1 = reader.nextName();
      var unused2 = reader.nextInt();
      assertThrows(java.io.EOFException.class, () -> reader.peek());
    }
  }

  @Test
  public void testInvalidUnicodeEscape_throwsMalformedJsonException() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("\"\\uXYZW\""))) {
      assertThrows(MalformedJsonException.class, () -> reader.nextString());
    }
  }

  @Test
  public void testInvalidEscapeSequence_throwsMalformedJsonException() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("\"\\q\""))) {
      assertThrows(MalformedJsonException.class, () -> reader.nextString());
    }
  }

  @Test
  public void testBomHandling_skipsBom() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("\ufeff{}"))) {
      reader.beginObject();
      reader.endObject();
    }
  }

  @Test
  public void testWhitespaceHandling_skipsWhitespace() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("  \t\r\n{  \t\r\n}  "))) {
      reader.beginObject();
      reader.endObject();
    }
  }

  @Test
  public void testLeadingZero_failsInNumber() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("00"))) {
      // Leading zero is not allowed in JSON numbers
      // The behavior depends on strictness
      reader.setStrictness(Strictness.STRICT);
      assertThrows(MalformedJsonException.class, () -> reader.nextInt());
    }
  }

  @Test
  public void testEmptyDocument_throwsEOFException() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader(""))) {
      assertThrows(java.io.EOFException.class, () -> reader.peek());
    }
  }

  @Test
  public void testWhitespaceOnlyDocument_throwsEOFException() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("   \t\n\r  "))) {
      assertThrows(java.io.EOFException.class, () -> reader.peek());
    }
  }

  @Test
  public void testControlCharactersInStrictMode_throwsMalformedJsonException() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("\"hello\u0000world\""))) {
      reader.setStrictness(Strictness.STRICT);
      assertThrows(MalformedJsonException.class, () -> reader.nextString());
    }
  }

  @Test
  public void testControlCharactersInLegacyStrictMode_succeeds() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("\"hello\u0000world\""))) {
      reader.setStrictness(Strictness.LEGACY_STRICT);
      assertEquals("hello\u0000world", reader.nextString());
    }
  }

  @Test
  public void testEscapedSingleQuote_failsInStrictMode() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("\"test\\'quote\""))) {
      reader.setStrictness(Strictness.STRICT);
      assertThrows(MalformedJsonException.class, () -> reader.nextString());
    }
  }

  @Test
  public void testEscapedSingleQuote_succeedsInLegacyStrictMode() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("\"test\\'quote\""))) {
      reader.setStrictness(Strictness.LEGACY_STRICT);
      assertEquals("test'quote", reader.nextString());
    }
  }

  @Test
  public void testEscapedNewline_failsInStrictMode() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("\"test\\\nvalue\""))) {
      reader.setStrictness(Strictness.STRICT);
      assertThrows(MalformedJsonException.class, () -> reader.nextString());
    }
  }

  @Test
  public void testEscapedNewline_succeedsInLegacyStrictMode() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("\"test\\\nvalue\""))) {
      reader.setStrictness(Strictness.LEGACY_STRICT);
      assertEquals("test\nvalue", reader.nextString());
    }
  }

  // ==================== Complex structure tests ====================

  @Test
  public void testComplexNestedStructure() throws IOException {
    String json = "{\"users\":[{\"name\":\"Alice\",\"age\":30},{\"name\":\"Bob\",\"age\":25}]}";
    try (JsonReader reader = new JsonReader(new StringReader(json))) {
      reader.beginObject();
      assertEquals("users", reader.nextName());
      reader.beginArray();

      reader.beginObject();
      assertEquals("name", reader.nextName());
      assertEquals("Alice", reader.nextString());
      assertEquals("age", reader.nextName());
      assertEquals(30, reader.nextInt());
      reader.endObject();

      reader.beginObject();
      assertEquals("name", reader.nextName());
      assertEquals("Bob", reader.nextString());
      assertEquals("age", reader.nextName());
      assertEquals(25, reader.nextInt());
      reader.endObject();

      reader.endArray();
      reader.endObject();
    }
  }

  @Test
  public void testDeeplyNestedArrays() throws IOException {
    String json = "[[[[[[1]]]]]]";
    try (JsonReader reader = new JsonReader(new StringReader(json))) {
      reader.beginArray();
      reader.beginArray();
      reader.beginArray();
      reader.beginArray();
      reader.beginArray();
      reader.beginArray();
      assertEquals(1, reader.nextInt());
      reader.endArray();
      reader.endArray();
      reader.endArray();
      reader.endArray();
      reader.endArray();
      reader.endArray();
    }
  }

  @Test
  public void testMixedValueTypes() throws IOException {
    String json = "[1, \"two\", true, null, 3.14, {\"nested\": false}]";
    try (JsonReader reader = new JsonReader(new StringReader(json))) {
      reader.beginArray();
      assertEquals(1, reader.nextInt());
      assertEquals("two", reader.nextString());
      assertTrue(reader.nextBoolean());
      reader.nextNull();
      assertEquals(3.14, reader.nextDouble(), 0.001);
      reader.beginObject();
      assertEquals("nested", reader.nextName());
      assertFalse(reader.nextBoolean());
      reader.endObject();
      reader.endArray();
    }
  }

  // ==================== Large numbers ====================

  @Test
  public void testVeryLargeNumber() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("123456789012345678901234567890"))) {
      // This number is too large for long, so it's read as NUMBER type
      assertEquals(JsonToken.NUMBER, reader.peek());
      assertEquals("123456789012345678901234567890", reader.nextString());
    }
  }

  @Test
  public void testVerySmallDecimal() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("0.00000000001"))) {
      assertEquals(1e-11, reader.nextDouble(), 1e-15);
    }
  }

  // ==================== Special cases ====================

  @Test
  public void testEmptyObject() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{}"))) {
      reader.beginObject();
      assertFalse(reader.hasNext());
      reader.endObject();
    }
  }

  @Test
  public void testEmptyArray() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("[]"))) {
      reader.beginArray();
      assertFalse(reader.hasNext());
      reader.endArray();
    }
  }

  @Test
  public void testSingletonArray() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("[42]"))) {
      reader.beginArray();
      assertTrue(reader.hasNext());
      assertEquals(42, reader.nextInt());
      assertFalse(reader.hasNext());
      reader.endArray();
    }
  }

  @Test
  public void testSingletonObject() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{\"only\":true}"))) {
      reader.beginObject();
      assertTrue(reader.hasNext());
      assertEquals("only", reader.nextName());
      assertTrue(reader.nextBoolean());
      assertFalse(reader.hasNext());
      reader.endObject();
    }
  }

  // ==================== Verifying state doesn't change on peek ====================

  @Test
  public void testPeekDoesNotConsumeToken() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("123"))) {
      assertEquals(JsonToken.NUMBER, reader.peek());
      assertEquals(JsonToken.NUMBER, reader.peek());
      assertEquals(123, reader.nextInt());
      assertEquals(JsonToken.END_DOCUMENT, reader.peek());
    }
  }

  @Test
  public void testHasNextDoesNotConsumeToken() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("[1]"))) {
      reader.beginArray();
      assertTrue(reader.hasNext());
      assertTrue(reader.hasNext());
      assertEquals(1, reader.nextInt());
      assertFalse(reader.hasNext());
      assertFalse(reader.hasNext());
    }
  }
}
