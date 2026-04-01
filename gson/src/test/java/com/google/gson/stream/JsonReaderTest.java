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

package com.google.gson.stream;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.google.gson.Strictness;
import java.io.EOFException;
import java.io.IOException;
import java.io.StringReader;
import org.junit.Test;

/** Unit tests for {@link JsonReader}. */
public final class JsonReaderTest {

  @Test
  public void testConstructorNullThrows() {
    assertThrows(NullPointerException.class, () -> new JsonReader(null));
  }

  @Test
  public void testConstructorValid() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    assertThat(reader.peek()).isEqualTo(JsonToken.BEGIN_OBJECT);
    reader.close();
  }

  @SuppressWarnings("deprecation")
  @Test
  public void testSetLenientTrue() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    reader.setLenient(true);
    assertThat(reader.isLenient()).isTrue();
    assertThat(reader.getStrictness()).isEqualTo(Strictness.LENIENT);
    reader.close();
  }

  @SuppressWarnings("deprecation")
  @Test
  public void testSetLenientFalse() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    reader.setLenient(false);
    assertThat(reader.isLenient()).isFalse();
    assertThat(reader.getStrictness()).isEqualTo(Strictness.LEGACY_STRICT);
    reader.close();
  }

  @Test
  public void testIsLenientDefault() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    assertThat(reader.isLenient()).isFalse();
    reader.close();
  }

  @Test
  public void testSetStrictnessStrict() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    reader.setStrictness(Strictness.STRICT);
    assertThat(reader.getStrictness()).isEqualTo(Strictness.STRICT);
    reader.close();
  }

  @Test
  public void testSetStrictnessNull() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    assertThrows(NullPointerException.class, () -> reader.setStrictness(null));
    reader.close();
  }

  @Test
  public void testGetStrictnessDefault() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    assertThat(reader.getStrictness()).isEqualTo(Strictness.LEGACY_STRICT);
    reader.close();
  }

  @Test
  public void testSetNestingLimitValid() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    reader.setNestingLimit(5);
    assertThat(reader.getNestingLimit()).isEqualTo(5);
    reader.close();
  }

  @Test
  public void testSetNestingLimitZero() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    reader.setNestingLimit(0);
    assertThat(reader.getNestingLimit()).isEqualTo(0);
    reader.close();
  }

  @Test
  public void testSetNestingLimitNegativeThrows() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    IllegalArgumentException e =
        assertThrows(IllegalArgumentException.class, () -> reader.setNestingLimit(-1));
    assertThat(e).hasMessageThat().contains("Invalid nesting limit");
    reader.close();
  }

  @Test
  public void testGetNestingLimitDefault() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    assertThat(reader.getNestingLimit()).isGreaterThan(0);
    reader.close();
  }

  @Test
  public void testBeginAndEndArray() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[1,2]"));
    reader.beginArray();
    assertThat(reader.hasNext()).isTrue();
    assertThat(reader.nextInt()).isEqualTo(1);
    assertThat(reader.nextInt()).isEqualTo(2);
    assertThat(reader.hasNext()).isFalse();
    reader.endArray();
    reader.close();
  }

  @Test
  public void testBeginArrayWrongTokenThrows() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    assertThrows(IllegalStateException.class, reader::beginArray);
    reader.close();
  }

  @Test
  public void testEndArrayWrongTokenThrows() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[1]"));
    reader.beginArray();
    assertThrows(IllegalStateException.class, reader::endArray);
    reader.close();
  }

  @Test
  public void testBeginAndEndObject() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{\"key\":\"value\"}"));
    reader.beginObject();
    assertThat(reader.hasNext()).isTrue();
    assertThat(reader.nextName()).isEqualTo("key");
    assertThat(reader.nextString()).isEqualTo("value");
    assertThat(reader.hasNext()).isFalse();
    reader.endObject();
    reader.close();
  }

  @Test
  public void testBeginObjectWrongTokenThrows() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[1]"));
    assertThrows(IllegalStateException.class, reader::beginObject);
    reader.close();
  }

  @Test
  public void testEndObjectWrongTokenThrows() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{\"a\":1}"));
    reader.beginObject();
    assertThrows(IllegalStateException.class, reader::endObject);
    reader.close();
  }

  @Test
  public void testHasNextInArray() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[]"));
    reader.beginArray();
    assertThat(reader.hasNext()).isFalse();
    reader.endArray();
    reader.close();
  }

  @Test
  public void testHasNextInObject() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    reader.beginObject();
    assertThat(reader.hasNext()).isFalse();
    reader.endObject();
    reader.close();
  }

  @Test
  public void testPeekBeginObject() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    assertThat(reader.peek()).isEqualTo(JsonToken.BEGIN_OBJECT);
    reader.close();
  }

  @Test
  public void testPeekBeginArray() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[]"));
    assertThat(reader.peek()).isEqualTo(JsonToken.BEGIN_ARRAY);
    reader.close();
  }

  @Test
  public void testPeekString() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[\"hello\"]"));
    reader.beginArray();
    assertThat(reader.peek()).isEqualTo(JsonToken.STRING);
    reader.close();
  }

  @Test
  public void testPeekNumber() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[42]"));
    reader.beginArray();
    assertThat(reader.peek()).isEqualTo(JsonToken.NUMBER);
    reader.close();
  }

  @Test
  public void testPeekBoolean() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[true]"));
    reader.beginArray();
    assertThat(reader.peek()).isEqualTo(JsonToken.BOOLEAN);
    reader.close();
  }

  @Test
  public void testPeekNull() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[null]"));
    reader.beginArray();
    assertThat(reader.peek()).isEqualTo(JsonToken.NULL);
    reader.close();
  }

  @Test
  public void testPeekEndDocument() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    reader.beginObject();
    reader.endObject();
    assertThat(reader.peek()).isEqualTo(JsonToken.END_DOCUMENT);
    reader.close();
  }

  @Test
  public void testNextNameDoubleQuoted() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{\"name\":1}"));
    reader.beginObject();
    assertThat(reader.nextName()).isEqualTo("name");
    reader.close();
  }

  @Test
  public void testNextNameWrongTokenThrows() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{\"name\":\"value\"}"));
    reader.beginObject();
    reader.nextName();
    assertThrows(IllegalStateException.class, reader::nextName);
    reader.close();
  }

  @Test
  public void testNextStringDoubleQuoted() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[\"hello\"]"));
    reader.beginArray();
    assertThat(reader.nextString()).isEqualTo("hello");
    reader.endArray();
    reader.close();
  }

  @Test
  public void testNextStringFromNumber() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[42]"));
    reader.beginArray();
    assertThat(reader.nextString()).isEqualTo("42");
    reader.endArray();
    reader.close();
  }

  @Test
  public void testNextStringWrongTokenThrows() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[true]"));
    reader.beginArray();
    assertThrows(IllegalStateException.class, reader::nextString);
    reader.close();
  }

  @Test
  public void testNextBooleanTrue() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[true]"));
    reader.beginArray();
    assertThat(reader.nextBoolean()).isTrue();
    reader.endArray();
    reader.close();
  }

  @Test
  public void testNextBooleanFalse() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[false]"));
    reader.beginArray();
    assertThat(reader.nextBoolean()).isFalse();
    reader.endArray();
    reader.close();
  }

  @Test
  public void testNextBooleanWrongTokenThrows() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[\"true\"]"));
    reader.beginArray();
    assertThrows(IllegalStateException.class, reader::nextBoolean);
    reader.close();
  }

  @Test
  public void testNextNull() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[null]"));
    reader.beginArray();
    reader.nextNull();
    reader.endArray();
    reader.close();
  }

  @Test
  public void testNextNullWrongTokenThrows() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[true]"));
    reader.beginArray();
    assertThrows(IllegalStateException.class, reader::nextNull);
    reader.close();
  }

  @Test
  public void testNextDouble() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[1.5]"));
    reader.beginArray();
    assertThat(reader.nextDouble()).isEqualTo(1.5);
    reader.endArray();
    reader.close();
  }

  @Test
  public void testNextDoubleFromLong() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[42]"));
    reader.beginArray();
    assertThat(reader.nextDouble()).isEqualTo(42.0);
    reader.endArray();
    reader.close();
  }

  @Test
  public void testNextDoubleWrongTokenThrows() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[true]"));
    reader.beginArray();
    assertThrows(IllegalStateException.class, reader::nextDouble);
    reader.close();
  }

  @Test
  public void testNextLong() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[100]"));
    reader.beginArray();
    assertThat(reader.nextLong()).isEqualTo(100L);
    reader.endArray();
    reader.close();
  }

  @Test
  public void testNextLongWrongTokenThrows() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[true]"));
    reader.beginArray();
    assertThrows(IllegalStateException.class, reader::nextLong);
    reader.close();
  }

  @Test
  public void testNextInt() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[42]"));
    reader.beginArray();
    assertThat(reader.nextInt()).isEqualTo(42);
    reader.endArray();
    reader.close();
  }

  @Test
  public void testNextIntWrongTokenThrows() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[true]"));
    reader.beginArray();
    assertThrows(IllegalStateException.class, reader::nextInt);
    reader.close();
  }

  @Test
  public void testClose() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    reader.close();
  }

  @Test
  public void testSkipValueObject() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{\"a\":{\"b\":1}}"));
    reader.beginObject();
    reader.nextName();
    reader.skipValue();
    reader.endObject();
    reader.close();
  }

  @Test
  public void testSkipValueArray() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[1,[2,3],4]"));
    reader.beginArray();
    reader.nextInt();
    reader.skipValue();
    assertThat(reader.nextInt()).isEqualTo(4);
    reader.endArray();
    reader.close();
  }

  @Test
  public void testSkipValuePrimitive() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[\"skip\",42]"));
    reader.beginArray();
    reader.skipValue();
    assertThat(reader.nextInt()).isEqualTo(42);
    reader.endArray();
    reader.close();
  }

  @Test
  public void testToString() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    assertThat(reader.toString()).contains("JsonReader");
    reader.close();
  }

  @Test
  public void testGetPath() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{\"a\":1}"));
    assertThat(reader.getPath()).isEqualTo("$");
    reader.beginObject();
    assertThat(reader.getPath()).isEqualTo("$.");
    reader.nextName();
    assertThat(reader.getPath()).isEqualTo("$.a");
    reader.close();
  }

  @Test
  public void testGetPreviousPath() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[1,2]"));
    reader.beginArray();
    assertThat(reader.getPreviousPath()).isEqualTo("$[0]");
    reader.nextInt();
    assertThat(reader.getPreviousPath()).isEqualTo("$[0]");
    reader.close();
  }

  @Test
  public void testGetPathInArray() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[1,2,3]"));
    reader.beginArray();
    assertThat(reader.getPath()).isEqualTo("$[0]");
    reader.nextInt();
    assertThat(reader.getPath()).isEqualTo("$[1]");
    reader.close();
  }

  @Test
  public void testNestingLimitExceeded() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[[]]"));
    reader.setNestingLimit(1);
    reader.beginArray();
    assertThrows(IOException.class, reader::beginArray);
    reader.close();
  }

  @Test
  public void testNextStringWithEscapeSequence() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[\"hello\\nworld\"]"));
    reader.beginArray();
    assertThat(reader.nextString()).isEqualTo("hello\nworld");
    reader.endArray();
    reader.close();
  }

  @Test
  public void testNextStringWithUnicodeEscape() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[\"\\u0041\"]"));
    reader.beginArray();
    assertThat(reader.nextString()).isEqualTo("A");
    reader.endArray();
    reader.close();
  }

  @Test
  public void testLenientModeAllowsNaN() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[NaN]"));
    reader.setStrictness(Strictness.LENIENT);
    reader.beginArray();
    assertThat(Double.isNaN(reader.nextDouble())).isTrue();
    reader.endArray();
    reader.close();
  }

  @Test
  public void testStrictModeDisallowsNaN() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[NaN]"));
    reader.setStrictness(Strictness.LENIENT);
    reader.setStrictness(Strictness.LEGACY_STRICT);
    reader.beginArray();
    assertThrows(IOException.class, reader::nextDouble);
    reader.close();
  }

  @Test
  public void testMultipleValuesObject() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{\"a\":1,\"b\":2}"));
    reader.beginObject();
    assertThat(reader.nextName()).isEqualTo("a");
    assertThat(reader.nextInt()).isEqualTo(1);
    assertThat(reader.nextName()).isEqualTo("b");
    assertThat(reader.nextInt()).isEqualTo(2);
    reader.endObject();
    reader.close();
  }

  @Test
  public void testNestedObjectAndArray() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{\"arr\":[1,2]}"));
    reader.beginObject();
    assertThat(reader.nextName()).isEqualTo("arr");
    reader.beginArray();
    assertThat(reader.nextInt()).isEqualTo(1);
    assertThat(reader.nextInt()).isEqualTo(2);
    reader.endArray();
    reader.endObject();
    reader.close();
  }

  @Test
  public void testPeekEndArray() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[]"));
    reader.beginArray();
    assertThat(reader.peek()).isEqualTo(JsonToken.END_ARRAY);
    reader.close();
  }

  @Test
  public void testPeekEndObject() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    reader.beginObject();
    assertThat(reader.peek()).isEqualTo(JsonToken.END_OBJECT);
    reader.close();
  }

  @Test
  public void testPeekName() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{\"key\":1}"));
    reader.beginObject();
    assertThat(reader.peek()).isEqualTo(JsonToken.NAME);
    reader.close();
  }

  @Test
  public void testNextIntFromDouble() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[1.0]"));
    reader.beginArray();
    assertThat(reader.nextInt()).isEqualTo(1);
    reader.endArray();
    reader.close();
  }

  @Test
  public void testNextLongFromString() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[\"123\"]"));
    reader.setStrictness(Strictness.LENIENT);
    reader.beginArray();
    assertThat(reader.nextLong()).isEqualTo(123L);
    reader.endArray();
    reader.close();
  }

  @Test
  public void testSkipValueEndObject() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    reader.beginObject();
    reader.skipValue();
    reader.close();
  }

  @Test
  public void testSkipValueEndDocument() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    reader.beginObject();
    reader.endObject();
    reader.skipValue();
    reader.close();
  }

  @Test
  public void testSkipToCStyleComment() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("/* comment */ 42"));
    reader.setStrictness(Strictness.LENIENT);
    assertThat(reader.nextInt()).isEqualTo(42);
    reader.close();
  }

  @Test
  public void testSkipToUnterminatedCStyleComment() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("/* unterminated"));
    reader.setStrictness(Strictness.LENIENT);
    MalformedJsonException e = assertThrows(MalformedJsonException.class, reader::peek);
    assertThat(e).hasMessageThat().contains("Unterminated comment");
    reader.close();
  }

  @Test
  public void testSkipToCommentWithNewlines() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("/* line1\nline2 */ 42"));
    reader.setStrictness(Strictness.LENIENT);
    assertThat(reader.nextInt()).isEqualTo(42);
    reader.close();
  }

  @Test
  public void testSkipToEndOfLineCommentWithNewline() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("// comment\n42"));
    reader.setStrictness(Strictness.LENIENT);
    assertThat(reader.nextInt()).isEqualTo(42);
    reader.close();
  }

  @Test
  public void testSkipToEndOfLineCommentWithCarriageReturn() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("// comment\r42"));
    reader.setStrictness(Strictness.LENIENT);
    assertThat(reader.nextInt()).isEqualTo(42);
    reader.close();
  }

  @Test
  public void testSkipToHashCommentWithNewline() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("# comment\n42"));
    reader.setStrictness(Strictness.LENIENT);
    assertThat(reader.nextInt()).isEqualTo(42);
    reader.close();
  }

  @Test
  public void testSkipToHashCommentWithCarriageReturn() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("# comment\r42"));
    reader.setStrictness(Strictness.LENIENT);
    assertThat(reader.nextInt()).isEqualTo(42);
    reader.close();
  }

  @Test
  public void testNextNonWhitespaceNewlineIncrementsLineNumber() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("\n42"));
    reader.setStrictness(Strictness.LENIENT);
    assertThat(reader.nextInt()).isEqualTo(42);
    reader.close();
  }

  @Test
  public void testNextNonWhitespaceTab() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("\t42"));
    reader.setStrictness(Strictness.LENIENT);
    assertThat(reader.nextInt()).isEqualTo(42);
    reader.close();
  }

  @Test
  public void testNextNonWhitespaceCarriageReturn() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("\r42"));
    reader.setStrictness(Strictness.LENIENT);
    assertThat(reader.nextInt()).isEqualTo(42);
    reader.close();
  }

  @Test
  public void testNextNonWhitespaceThrowsEofExceptionOnTruncatedArray() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("["));
    reader.beginArray();
    assertThrows(EOFException.class, reader::peek);
    reader.close();
  }

  @Test
  public void testNextNonWhitespaceSlashNotFollowedByCommentChar() throws IOException {
    // In LENIENT mode, '/' followed by a char that is neither '*' nor '/' causes
    // nextNonWhitespace to return '/', which then results in a syntax error.
    JsonReader reader = new JsonReader(new StringReader("/1"));
    reader.setStrictness(Strictness.LENIENT);
    assertThrows(MalformedJsonException.class, reader::peek);
    reader.close();
  }

  @Test
  public void testPushExceedsInitialStackCapacity() throws IOException {
    // The internal stack starts with capacity 32. Creating JSON with more than 32 levels of
    // nesting causes the stack arrays to be resized (lines 1476-1479 in JsonReader).
    int depth = 33;
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < depth; i++) {
      sb.append('[');
    }
    sb.append("1");
    for (int i = 0; i < depth; i++) {
      sb.append(']');
    }
    JsonReader reader = new JsonReader(new StringReader(sb.toString()));
    for (int i = 0; i < depth; i++) {
      reader.beginArray();
    }
    assertThat(reader.nextInt()).isEqualTo(1);
    for (int i = 0; i < depth; i++) {
      reader.endArray();
    }
    reader.close();
  }

  @Test
  public void testNextNonWhitespaceSlashAtBufferBoundary() throws IOException {
    // Place '/' exactly at the end of the 1024-char buffer to exercise the path where
    // p == l after reading '/' and fillBuffer(2) must be called to peek at next char.
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < JsonReader.BUFFER_SIZE - 1; i++) {
      sb.append(' ');
    }
    sb.append("/* comment */42");
    JsonReader reader = new JsonReader(new StringReader(sb.toString()));
    reader.setStrictness(Strictness.LENIENT);
    assertThat(reader.nextInt()).isEqualTo(42);
    reader.close();
  }

  @Test
  public void testSkipValueUnquotedString() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[unquoted, 42]"));
    reader.setStrictness(Strictness.LENIENT);
    reader.beginArray();
    reader.skipValue();
    assertThat(reader.nextInt()).isEqualTo(42);
    reader.endArray();
    reader.close();
  }

  @Test
  public void testSkipValueSingleQuotedString() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("['value', 42]"));
    reader.setStrictness(Strictness.LENIENT);
    reader.beginArray();
    reader.skipValue();
    assertThat(reader.nextInt()).isEqualTo(42);
    reader.endArray();
    reader.close();
  }

  @Test
  public void testSkipValueNumber() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[42, 100]"));
    reader.beginArray();
    reader.skipValue();
    assertThat(reader.nextInt()).isEqualTo(100);
    reader.endArray();
    reader.close();
  }

  @Test
  public void testSkipValueUnquotedNameUpdatesPath() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{unquoted: 1}"));
    reader.setStrictness(Strictness.LENIENT);
    reader.beginObject();
    reader.skipValue();
    assertThat(reader.getPath()).isEqualTo("$.<skipped>");
    reader.skipValue();
    reader.endObject();
    reader.close();
  }

  @Test
  public void testSkipValueSingleQuotedNameUpdatesPath() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{'name': 1}"));
    reader.setStrictness(Strictness.LENIENT);
    reader.beginObject();
    reader.skipValue();
    assertThat(reader.getPath()).isEqualTo("$.<skipped>");
    reader.skipValue();
    reader.endObject();
    reader.close();
  }

  @Test
  public void testSkipValueDoubleQuotedNameUpdatesPath() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{\"key\": 1}"));
    reader.beginObject();
    reader.skipValue();
    assertThat(reader.getPath()).isEqualTo("$.<skipped>");
    reader.skipValue();
    reader.endObject();
    reader.close();
  }

  @Test
  public void testNextIntLongOverflowThrows() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[9999999999]"));
    reader.beginArray();
    NumberFormatException e = assertThrows(NumberFormatException.class, reader::nextInt);
    assertThat(e).hasMessageThat().contains("9999999999");
    reader.close();
  }

  @Test
  public void testNextIntFromDoubleQuotedString() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[\"42\"]"));
    reader.beginArray();
    assertThat(reader.nextInt()).isEqualTo(42);
    reader.endArray();
    reader.close();
  }

  @Test
  public void testNextIntFromUnquotedString() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[+42]"));
    reader.setStrictness(Strictness.LENIENT);
    reader.beginArray();
    assertThat(reader.nextInt()).isEqualTo(42);
    reader.endArray();
    reader.close();
  }

  @Test
  public void testNextIntFromFractionalStringThrows() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[\"1.5\"]"));
    reader.beginArray();
    NumberFormatException e = assertThrows(NumberFormatException.class, reader::nextInt);
    assertThat(e).hasMessageThat().contains("1.5");
    reader.close();
  }

  @Test
  public void testNextStringUnquoted() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[hello]"));
    reader.setStrictness(Strictness.LENIENT);
    reader.beginArray();
    assertThat(reader.nextString()).isEqualTo("hello");
    reader.endArray();
    reader.close();
  }

  @Test
  public void testNextStringSingleQuoted() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("['hello']"));
    reader.setStrictness(Strictness.LENIENT);
    reader.beginArray();
    assertThat(reader.nextString()).isEqualTo("hello");
    reader.endArray();
    reader.close();
  }

  @Test
  public void testNextStringFromFloatNumber() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[1.5]"));
    reader.beginArray();
    assertThat(reader.nextString()).isEqualTo("1.5");
    reader.endArray();
    reader.close();
  }

  @Test
  public void testNextStringFromBufferedAfterFailedLong() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[1.5]"));
    reader.beginArray();
    assertThrows(NumberFormatException.class, reader::nextLong);
    assertThat(reader.nextString()).isEqualTo("1.5");
    reader.endArray();
    reader.close();
  }
}
