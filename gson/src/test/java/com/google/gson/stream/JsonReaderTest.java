/*
 * Copyright (C) 2026 Google Inc.
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

package com.google.gson.stream;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.fail;

import com.google.gson.Strictness;
import java.io.IOException;
import java.io.StringReader;
import org.junit.Test;

public class JsonReaderTest {

  @Test
  public void testConstructor() {
    StringReader reader = new StringReader("{}");
    JsonReader jsonReader = new JsonReader(reader);
    assertThat(jsonReader).isNotNull();
  }

  @Test
  public void testConstructor_withNullReader_throwsException() {
    try {
      new JsonReader(null);
      fail("Expected NullPointerException");
    } catch (NullPointerException expected) {
    }
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testSetLenient_withTrue() {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    reader.setLenient(true);
    assertThat(reader.isLenient()).isTrue();
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testSetLenient_withFalse() {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    reader.setLenient(false);
    assertThat(reader.isLenient()).isFalse();
  }

  @Test
  public void testIsLenient_defaultValue() {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    assertThat(reader.isLenient()).isFalse();
  }

  @Test
  public void testSetStrictness_withLenient() {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    reader.setStrictness(Strictness.LENIENT);
    assertThat(reader.getStrictness()).isEqualTo(Strictness.LENIENT);
  }

  @Test
  public void testSetStrictness_withStrict() {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    reader.setStrictness(Strictness.STRICT);
    assertThat(reader.getStrictness()).isEqualTo(Strictness.STRICT);
  }

  @Test
  public void testSetStrictness_withLegacyStrict() {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    reader.setStrictness(Strictness.LEGACY_STRICT);
    assertThat(reader.getStrictness()).isEqualTo(Strictness.LEGACY_STRICT);
  }

  @Test
  public void testSetStrictness_withNull_throwsException() {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    try {
      reader.setStrictness(null);
      fail("Expected NullPointerException");
    } catch (NullPointerException expected) {
    }
  }

  @Test
  public void testGetStrictness_defaultValue() {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    assertThat(reader.getStrictness()).isEqualTo(Strictness.LEGACY_STRICT);
  }

  @Test
  public void testSetNestingLimit_withValidLimit() {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    reader.setNestingLimit(10);
    assertThat(reader.getNestingLimit()).isEqualTo(10);
  }

  @Test
  public void testSetNestingLimit_withZero() {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    reader.setNestingLimit(0);
    assertThat(reader.getNestingLimit()).isEqualTo(0);
  }

  @Test
  public void testSetNestingLimit_withNegative_throwsException() {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    try {
      reader.setNestingLimit(-1);
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException expected) {
      assertThat(expected.getMessage()).contains("Invalid nesting limit");
    }
  }

  @Test
  public void testGetNestingLimit_defaultValue() {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    assertThat(reader.getNestingLimit()).isEqualTo(255);
  }

  @Test
  public void testBeginArray() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[]"));
    reader.beginArray();
    reader.endArray();
  }

  @Test
  public void testBeginArray_withNonArray_throwsException() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    try {
      reader.beginArray();
      fail("Expected IllegalStateException");
    } catch (IllegalStateException expected) {
      assertThat(expected.getMessage()).contains("BEGIN_ARRAY");
    }
  }

  @Test
  public void testEndArray() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[]"));
    reader.beginArray();
    reader.endArray();
  }

  @Test
  public void testEndArray_withNonEnd_throwsException() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[1]"));
    reader.beginArray();
    try {
      reader.endArray();
      fail("Expected IllegalStateException");
    } catch (IllegalStateException expected) {
      assertThat(expected.getMessage()).contains("END_ARRAY");
    }
  }

  @Test
  public void testBeginObject() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    reader.beginObject();
    reader.endObject();
  }

  @Test
  public void testBeginObject_withNonObject_throwsException() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[]"));
    try {
      reader.beginObject();
      fail("Expected IllegalStateException");
    } catch (IllegalStateException expected) {
      assertThat(expected.getMessage()).contains("BEGIN_OBJECT");
    }
  }

  @Test
  public void testEndObject() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    reader.beginObject();
    reader.endObject();
  }

  @Test
  public void testEndObject_withNonEnd_throwsException() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{\"key\":\"value\"}"));
    reader.beginObject();
    try {
      reader.endObject();
      fail("Expected IllegalStateException");
    } catch (IllegalStateException expected) {
      assertThat(expected.getMessage()).contains("END_OBJECT");
    }
  }

  @Test
  public void testHasNext_withElements() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[1,2]"));
    reader.beginArray();
    assertThat(reader.hasNext()).isTrue();
  }

  @Test
  public void testHasNext_withoutElements() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[]"));
    reader.beginArray();
    assertThat(reader.hasNext()).isFalse();
  }

  @Test
  public void testPeek_withString() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("\"test\""));
    assertThat(reader.peek()).isEqualTo(JsonToken.STRING);
  }

  @Test
  public void testPeek_withNumber() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("123"));
    assertThat(reader.peek()).isEqualTo(JsonToken.NUMBER);
  }

  @Test
  public void testPeek_withBoolean() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("true"));
    assertThat(reader.peek()).isEqualTo(JsonToken.BOOLEAN);
  }

  @Test
  public void testPeek_withNull() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("null"));
    assertThat(reader.peek()).isEqualTo(JsonToken.NULL);
  }

  @Test
  public void testPeek_withArray() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[]"));
    assertThat(reader.peek()).isEqualTo(JsonToken.BEGIN_ARRAY);
  }

  @Test
  public void testPeek_withObject() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    assertThat(reader.peek()).isEqualTo(JsonToken.BEGIN_OBJECT);
  }

  @Test
  public void testNextName() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{\"key\":\"value\"}"));
    reader.beginObject();
    assertThat(reader.nextName()).isEqualTo("key");
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testNextName_withSingleQuote() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{'key':'value'}"));
    reader.setLenient(true);
    reader.beginObject();
    assertThat(reader.nextName()).isEqualTo("key");
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testNextName_withUnquoted() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{key:\"value\"}"));
    reader.setLenient(true);
    reader.beginObject();
    assertThat(reader.nextName()).isEqualTo("key");
  }

  @Test
  public void testNextString() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("\"test\""));
    assertThat(reader.nextString()).isEqualTo("test");
  }

  @Test
  public void testNextString_withNumber() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("123"));
    assertThat(reader.nextString()).isEqualTo("123");
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testNextString_withSingleQuote() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("'test'"));
    reader.setLenient(true);
    assertThat(reader.nextString()).isEqualTo("test");
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testNextString_withUnquoted() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("test"));
    reader.setLenient(true);
    assertThat(reader.nextString()).isEqualTo("test");
  }

  @Test
  public void testNextBoolean_withTrue() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("true"));
    assertThat(reader.nextBoolean()).isTrue();
  }

  @Test
  public void testNextBoolean_withFalse() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("false"));
    assertThat(reader.nextBoolean()).isFalse();
  }

  @Test
  public void testNextBoolean_withNonBoolean_throwsException() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("123"));
    try {
      reader.nextBoolean();
      fail("Expected IllegalStateException");
    } catch (IllegalStateException expected) {
    }
  }

  @Test
  public void testNextNull() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("null"));
    reader.nextNull();
    assertThat(reader.peek()).isEqualTo(JsonToken.END_DOCUMENT);
  }

  @Test
  public void testNextNull_withNonNull_throwsException() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("123"));
    try {
      reader.nextNull();
      fail("Expected IllegalStateException");
    } catch (IllegalStateException expected) {
    }
  }

  @Test
  public void testNextDouble_withInteger() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("123"));
    assertThat(reader.nextDouble()).isEqualTo(123.0);
  }

  @Test
  public void testNextDouble_withDecimal() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("123.45"));
    assertThat(reader.nextDouble()).isEqualTo(123.45);
  }

  @Test
  public void testNextDouble_withExponent() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("1.23e2"));
    assertThat(reader.nextDouble()).isEqualTo(123.0);
  }

  @Test
  public void testNextDouble_withNegative() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("-123.45"));
    assertThat(reader.nextDouble()).isEqualTo(-123.45);
  }

  @Test
  public void testNextDouble_withString() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("\"123.45\""));
    assertThat(reader.nextDouble()).isEqualTo(123.45);
  }

  @Test
  public void testNextLong_withInteger() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("123"));
    assertThat(reader.nextLong()).isEqualTo(123L);
  }

  @Test
  public void testNextLong_withNegative() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("-123"));
    assertThat(reader.nextLong()).isEqualTo(-123L);
  }

  @Test
  public void testNextLong_withLargeNumber() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("9223372036854775807"));
    assertThat(reader.nextLong()).isEqualTo(9223372036854775807L);
  }

  @Test
  public void testNextLong_withString() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("\"123\""));
    assertThat(reader.nextLong()).isEqualTo(123L);
  }

  @Test
  public void testNextLong_withDecimal_throwsException() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("123.45"));
    try {
      reader.nextLong();
      fail("Expected NumberFormatException");
    } catch (NumberFormatException expected) {
    }
  }

  @Test
  public void testNextInt_withInteger() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("123"));
    assertThat(reader.nextInt()).isEqualTo(123);
  }

  @Test
  public void testNextInt_withNegative() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("-123"));
    assertThat(reader.nextInt()).isEqualTo(-123);
  }

  @Test
  public void testNextInt_withString() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("\"123\""));
    assertThat(reader.nextInt()).isEqualTo(123);
  }

  @Test
  public void testNextInt_withDecimal_throwsException() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("123.45"));
    try {
      reader.nextInt();
      fail("Expected NumberFormatException");
    } catch (NumberFormatException expected) {
    }
  }

  @Test
  public void testNextInt_withTooLarge_throwsException() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("9223372036854775807"));
    try {
      reader.nextInt();
      fail("Expected NumberFormatException");
    } catch (NumberFormatException expected) {
    }
  }

  @Test
  public void testClose() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    reader.close();
  }

  @Test
  public void testSkipValue_withString() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[\"test\", 123]"));
    reader.beginArray();
    reader.skipValue();
    assertThat(reader.nextInt()).isEqualTo(123);
  }

  @Test
  public void testSkipValue_withNumber() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[123, \"test\"]"));
    reader.beginArray();
    reader.skipValue();
    assertThat(reader.nextString()).isEqualTo("test");
  }

  @Test
  public void testSkipValue_withObject() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[{\"key\":\"value\"}, 123]"));
    reader.beginArray();
    reader.skipValue();
    assertThat(reader.nextInt()).isEqualTo(123);
  }

  @Test
  public void testSkipValue_withArray() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[[1,2,3], 123]"));
    reader.beginArray();
    reader.skipValue();
    assertThat(reader.nextInt()).isEqualTo(123);
  }

  @Test
  public void testSkipValue_withNestedStructures() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[{\"arr\":[1,2,{\"nested\":true}]}, 123]"));
    reader.beginArray();
    reader.skipValue();
    assertThat(reader.nextInt()).isEqualTo(123);
  }

  @Test
  public void testGetPath_atRoot() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    assertThat(reader.getPath()).isEqualTo("$");
  }

  @Test
  public void testGetPath_inArray() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[1,2,3]"));
    reader.beginArray();
    reader.nextInt();
    assertThat(reader.getPath()).isEqualTo("$[1]");
  }

  @Test
  public void testGetPath_inObject() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{\"key\":\"value\"}"));
    reader.beginObject();
    reader.nextName();
    assertThat(reader.getPath()).isEqualTo("$.key");
  }

  @Test
  public void testGetPath_nested() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{\"outer\":{\"inner\":[1,2]}}"));
    reader.beginObject();
    reader.nextName();
    reader.beginObject();
    reader.nextName();
    reader.beginArray();
    reader.nextInt();
    assertThat(reader.getPath()).isEqualTo("$.outer.inner[1]");
  }

  @Test
  public void testGetPreviousPath_afterArrayElement() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[1,2]"));
    reader.beginArray();
    reader.nextInt();
    assertThat(reader.getPreviousPath()).isEqualTo("$[0]");
  }

  @Test
  public void testGetPreviousPath_afterObjectValue() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{\"key\":\"value\"}"));
    reader.beginObject();
    reader.nextName();
    reader.nextString();
    assertThat(reader.getPreviousPath()).isEqualTo("$.key");
  }

  @Test
  public void testToString() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    assertThat(reader.toString()).contains("JsonReader");
  }

  @Test
  public void testNextString_withEscapeSequences() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("\"test\\nline\\ttab\""));
    assertThat(reader.nextString()).isEqualTo("test\nline\ttab");
  }

  @Test
  public void testNextString_withUnicodeEscape() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("\"test\\u0041\""));
    assertThat(reader.nextString()).isEqualTo("testA");
  }

  @Test
  public void testArrayWithMultipleElements() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[1,2,3,4,5]"));
    reader.beginArray();
    assertThat(reader.nextInt()).isEqualTo(1);
    assertThat(reader.nextInt()).isEqualTo(2);
    assertThat(reader.nextInt()).isEqualTo(3);
    assertThat(reader.nextInt()).isEqualTo(4);
    assertThat(reader.nextInt()).isEqualTo(5);
    reader.endArray();
  }

  @Test
  public void testObjectWithMultipleFields() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{\"a\":1,\"b\":2,\"c\":3}"));
    reader.beginObject();
    assertThat(reader.nextName()).isEqualTo("a");
    assertThat(reader.nextInt()).isEqualTo(1);
    assertThat(reader.nextName()).isEqualTo("b");
    assertThat(reader.nextInt()).isEqualTo(2);
    assertThat(reader.nextName()).isEqualTo("c");
    assertThat(reader.nextInt()).isEqualTo(3);
    reader.endObject();
  }

  @Test
  public void testNestedArrays() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[[1,2],[3,4]]"));
    reader.beginArray();
    reader.beginArray();
    assertThat(reader.nextInt()).isEqualTo(1);
    assertThat(reader.nextInt()).isEqualTo(2);
    reader.endArray();
    reader.beginArray();
    assertThat(reader.nextInt()).isEqualTo(3);
    assertThat(reader.nextInt()).isEqualTo(4);
    reader.endArray();
    reader.endArray();
  }

  @Test
  public void testNestedObjects() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{\"outer\":{\"inner\":1}}"));
    reader.beginObject();
    assertThat(reader.nextName()).isEqualTo("outer");
    reader.beginObject();
    assertThat(reader.nextName()).isEqualTo("inner");
    assertThat(reader.nextInt()).isEqualTo(1);
    reader.endObject();
    reader.endObject();
  }

  @Test
  public void testMixedNestedStructures() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{\"array\":[1,{\"nested\":2}]}"));
    reader.beginObject();
    assertThat(reader.nextName()).isEqualTo("array");
    reader.beginArray();
    assertThat(reader.nextInt()).isEqualTo(1);
    reader.beginObject();
    assertThat(reader.nextName()).isEqualTo("nested");
    assertThat(reader.nextInt()).isEqualTo(2);
    reader.endObject();
    reader.endArray();
    reader.endObject();
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testLenientMode_withComments() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("// comment\n{\"key\":\"value\"}"));
    reader.setLenient(true);
    reader.beginObject();
    assertThat(reader.nextName()).isEqualTo("key");
    assertThat(reader.nextString()).isEqualTo("value");
    reader.endObject();
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testLenientMode_withTrailingComma() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[1,2,3,]"));
    reader.setLenient(true);
    reader.beginArray();
    assertThat(reader.nextInt()).isEqualTo(1);
    assertThat(reader.nextInt()).isEqualTo(2);
    assertThat(reader.nextInt()).isEqualTo(3);
    reader.nextNull();
    reader.endArray();
  }

  @Test
  public void testNestingLimit_exceedsLimit() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[[[]]]"));
    reader.setNestingLimit(2);
    reader.beginArray();
    reader.beginArray();
    try {
      reader.beginArray();
      fail("Expected IOException");
    } catch (IOException expected) {
      assertThat(expected.getMessage()).contains("Nesting limit");
    }
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testNextDouble_withInfinity() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("Infinity"));
    reader.setLenient(true);
    assertThat(reader.nextDouble()).isPositiveInfinity();
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testNextDouble_withNegativeInfinity() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("-Infinity"));
    reader.setLenient(true);
    assertThat(reader.nextDouble()).isNegativeInfinity();
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testNextDouble_withNaN() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("NaN"));
    reader.setLenient(true);
    assertThat(reader.nextDouble()).isNaN();
  }

  @Test
  public void testPeek_multipleCalls() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("123"));
    assertThat(reader.peek()).isEqualTo(JsonToken.NUMBER);
    assertThat(reader.peek()).isEqualTo(JsonToken.NUMBER);
    assertThat(reader.nextInt()).isEqualTo(123);
  }

  @Test
  public void testHasNext_afterEndOfArray() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[1]"));
    reader.beginArray();
    reader.nextInt();
    assertThat(reader.hasNext()).isFalse();
  }

  @Test
  public void testHasNext_afterEndOfObject() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{\"key\":\"value\"}"));
    reader.beginObject();
    reader.nextName();
    reader.nextString();
    assertThat(reader.hasNext()).isFalse();
  }

  @Test
  public void testNextString_withEmptyString() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("\"\""));
    assertThat(reader.nextString()).isEqualTo("");
  }

  @Test
  public void testNextName_withEmptyName() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{\"\":\"value\"}"));
    reader.beginObject();
    assertThat(reader.nextName()).isEqualTo("");
  }

  @Test
  public void testArrayWithNull() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[null,1,null]"));
    reader.beginArray();
    reader.nextNull();
    assertThat(reader.nextInt()).isEqualTo(1);
    reader.nextNull();
    reader.endArray();
  }

  @Test
  public void testObjectWithNullValue() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{\"key\":null}"));
    reader.beginObject();
    assertThat(reader.nextName()).isEqualTo("key");
    reader.nextNull();
    reader.endObject();
  }

  @Test
  public void testEmptyArray() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[]"));
    reader.beginArray();
    assertThat(reader.hasNext()).isFalse();
    reader.endArray();
  }

  @Test
  public void testEmptyObject() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    reader.beginObject();
    assertThat(reader.hasNext()).isFalse();
    reader.endObject();
  }

  @Test
  public void testSkipValue_withBoolean() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[true, 123]"));
    reader.beginArray();
    reader.skipValue();
    assertThat(reader.nextInt()).isEqualTo(123);
  }

  @Test
  public void testSkipValue_withNull() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[null, 123]"));
    reader.beginArray();
    reader.skipValue();
    assertThat(reader.nextInt()).isEqualTo(123);
  }

  @Test
  public void testNextLong_withZero() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("0"));
    assertThat(reader.nextLong()).isEqualTo(0L);
  }

  @Test
  public void testNextInt_withZero() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("0"));
    assertThat(reader.nextInt()).isEqualTo(0);
  }

  @Test
  public void testNextDouble_withZero() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("0"));
    assertThat(reader.nextDouble()).isEqualTo(0.0);
  }

  @Test
  public void testNextDouble_withZeroDecimal() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("0.0"));
    assertThat(reader.nextDouble()).isEqualTo(0.0);
  }

  @Test
  public void testEscapeSequence_unterminatedEscape() {
    JsonReader reader = new JsonReader(new StringReader("[\"\\"));
    try {
      reader.beginArray();
      reader.nextString();
      fail("Expected MalformedJsonException");
    } catch (IOException expected) {
      assertThat(expected.getMessage()).contains("Unterminated");
    }
  }

  @Test
  public void testEscapeSequence_unterminatedUnicodeEscape() {
    JsonReader reader = new JsonReader(new StringReader("[\"\\u12"));
    try {
      reader.beginArray();
      reader.nextString();
      fail("Expected MalformedJsonException");
    } catch (IOException expected) {
      assertThat(expected.getMessage()).contains("Unterminated");
    }
  }

  @Test
  public void testEscapeSequence_unicodeWithLowercaseHex() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("\"\\uabcd\""));
    assertThat(reader.nextString()).isEqualTo("\uabcd");
  }

  @Test
  public void testEscapeSequence_unicodeWithUppercaseHex() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("\"\\uABCD\""));
    assertThat(reader.nextString()).isEqualTo("\uABCD");
  }

  @Test
  public void testEscapeSequence_unicodeWithMixedCaseHex() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("\"\\uAbCd\""));
    assertThat(reader.nextString()).isEqualTo("\uAbCd");
  }

  @Test
  public void testEscapeSequence_malformedUnicodeEscape() {
    JsonReader reader = new JsonReader(new StringReader("\"\\u123g\""));
    try {
      reader.nextString();
      fail("Expected MalformedJsonException");
    } catch (IOException expected) {
      assertThat(expected.getMessage()).contains("Malformed Unicode escape");
    }
  }

  @Test
  public void testEscapeSequence_backspace() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("\"\\b\""));
    assertThat(reader.nextString()).isEqualTo("\b");
  }

  @Test
  public void testEscapeSequence_carriageReturn() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("\"\\r\""));
    assertThat(reader.nextString()).isEqualTo("\r");
  }

  @Test
  public void testEscapeSequence_formFeed() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("\"\\f\""));
    assertThat(reader.nextString()).isEqualTo("\f");
  }

  @Test
  public void testEscapeSequence_newlineInStrictMode() {
    JsonReader reader = new JsonReader(new StringReader("\"\\\n\""));
    reader.setStrictness(Strictness.STRICT);
    try {
      reader.nextString();
      fail("Expected MalformedJsonException");
    } catch (IOException expected) {
      assertThat(expected.getMessage()).contains("Cannot escape a newline character in strict mode");
    }
  }

  @Test
  public void testEscapeSequence_newlineInNonStrictMode() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("\"\\\n\""));
    reader.setStrictness(Strictness.LENIENT);
    assertThat(reader.nextString()).isEqualTo("\n");
  }

  @Test
  public void testEscapeSequence_singleQuoteInStrictMode() {
    JsonReader reader = new JsonReader(new StringReader("\"\\'\""));
    reader.setStrictness(Strictness.STRICT);
    try {
      reader.nextString();
      fail("Expected MalformedJsonException");
    } catch (IOException expected) {
      assertThat(expected.getMessage()).contains("Invalid escaped character \"'\" in strict mode");
    }
  }

  @Test
  public void testEscapeSequence_singleQuoteInNonStrictMode() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("\"\\'\""));
    reader.setStrictness(Strictness.LENIENT);
    assertThat(reader.nextString()).isEqualTo("'");
  }

  @Test
  public void testEscapeSequence_invalidEscapeCharacter() {
    JsonReader reader = new JsonReader(new StringReader("\"\\x\""));
    try {
      reader.nextString();
      fail("Expected MalformedJsonException");
    } catch (IOException expected) {
      assertThat(expected.getMessage()).contains("Invalid escape sequence");
    }
  }
}
