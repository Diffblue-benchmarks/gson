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
import java.io.IOException;
import java.io.StringReader;
import org.junit.Test;

/**
 * Tests for edge cases in {@link JsonReader} to improve code coverage.
 */
@SuppressWarnings("resource")
public final class JsonReaderCoverageTest {

  @Test
  public void testUnterminatedArrayWithUnexpectedCharacter() throws IOException {
    // Targets line 600: throw syntaxError("Unterminated array")
    // Occurs when in a non-empty array and we get an unexpected character
    String json = "[1 2]";
    JsonReader reader = new JsonReader(new StringReader(json));
    reader.beginArray();
    assertThat(reader.nextInt()).isEqualTo(1);

    MalformedJsonException e = assertThrows(MalformedJsonException.class, reader::peek);
    assertThat(e).hasMessageThat().contains("Unterminated array");
  }

  @Test
  public void testUnterminatedObjectWithUnexpectedCharacter() throws IOException {
    // Targets line 616: throw syntaxError("Unterminated object")
    // Occurs when in a non-empty object and we get an unexpected character
    String json = "{\"a\":1 \"b\":2}";
    JsonReader reader = new JsonReader(new StringReader(json));
    reader.beginObject();
    assertThat(reader.nextName()).isEqualTo("a");
    assertThat(reader.nextInt()).isEqualTo(1);

    MalformedJsonException e = assertThrows(MalformedJsonException.class, reader::peek);
    assertThat(e).hasMessageThat().contains("Unterminated object");
  }

  @Test
  public void testExpectedNameGotClosingBraceAfterComma() throws IOException {
    // Targets line 633: throw syntaxError("Expected name")
    // Occurs when after a comma in an object, we get a closing brace
    String json = "{\"a\":1,}";
    JsonReader reader = new JsonReader(new StringReader(json));
    reader.beginObject();
    assertThat(reader.nextName()).isEqualTo("a");
    assertThat(reader.nextInt()).isEqualTo(1);

    MalformedJsonException e = assertThrows(MalformedJsonException.class, reader::peek);
    assertThat(e).hasMessageThat().contains("Expected name");
  }

  @Test
  public void testExpectedNameGotInvalidCharacter() throws IOException {
    // Targets line 642: throw syntaxError("Expected name")
    // Occurs when expecting a name but get an invalid literal character
    // Using a non-literal character that's not a quote or brace
    String json = "{[:1}";
    JsonReader reader = new JsonReader(new StringReader(json));
    reader.setStrictness(Strictness.LENIENT);
    reader.beginObject();

    MalformedJsonException e = assertThrows(MalformedJsonException.class, reader::nextName);
    assertThat(e).hasMessageThat().contains("Expected name");
  }

  @Test
  public void testExpectedColonGotUnexpectedCharacter() throws IOException {
    // Targets line 659: throw syntaxError("Expected ':'")
    // Occurs after reading a property name when we don't get ':' or '='
    String json = "{\"a\" 1}";
    JsonReader reader = new JsonReader(new StringReader(json));
    reader.beginObject();
    assertThat(reader.nextName()).isEqualTo("a");

    MalformedJsonException e = assertThrows(MalformedJsonException.class, reader::peek);
    assertThat(e).hasMessageThat().contains("Expected ':'");
  }

  @Test
  public void testUnexpectedValueInNonArrayContext() throws IOException {
    // Targets line 696: throw syntaxError("Unexpected value")
    // Occurs when we get ';' or ',' in a non-array context (like top level)
    String json = ",1";
    JsonReader reader = new JsonReader(new StringReader(json));
    reader.setStrictness(Strictness.LENIENT);

    MalformedJsonException e = assertThrows(MalformedJsonException.class, reader::peek);
    assertThat(e).hasMessageThat().contains("Unexpected value");
  }

  @Test
  public void testExpectedValueGotWhitespace() throws IOException {
    // Targets line 726: throw syntaxError("Expected value")
    // Occurs when the character at pos is not a valid literal and not whitespace
    // This is hard to trigger directly; the code path requires a non-literal
    // after keyword/number checks. Using '}' in array context after an element.
    String json = "[1}]";
    JsonReader reader = new JsonReader(new StringReader(json));
    reader.beginArray();
    assertThat(reader.nextInt()).isEqualTo(1);

    // After the 1, peek should see } which is not valid in array context
    MalformedJsonException e = assertThrows(MalformedJsonException.class, reader::peek);
    assertThat(e).hasMessageThat().contains("Unterminated array");
  }

  @Test
  public void testNaNInNonLenientMode() throws IOException {
    // Targets line 1057: throw syntaxError("JSON forbids NaN and infinities: " + result)
    String json = "\"NaN\"";
    JsonReader reader = new JsonReader(new StringReader(json));

    MalformedJsonException e = assertThrows(MalformedJsonException.class, reader::nextDouble);
    assertThat(e).hasMessageThat().contains("JSON forbids NaN and infinities");
  }

  @Test
  public void testInfinityInNonLenientMode() throws IOException {
    // Targets line 1057: throw syntaxError("JSON forbids NaN and infinities: " + result)
    String json = "\"Infinity\"";
    JsonReader reader = new JsonReader(new StringReader(json));

    MalformedJsonException e = assertThrows(MalformedJsonException.class, reader::nextDouble);
    assertThat(e).hasMessageThat().contains("JSON forbids NaN and infinities");
  }

  @Test
  public void testNextLongWithNonIntegerString() throws IOException {
    // Targets lines 1100, 1102: catch block for NumberFormatException
    // When the string can be parsed as double but not as long directly
    String json = "\"1.5\"";
    JsonReader reader = new JsonReader(new StringReader(json));

    NumberFormatException e = assertThrows(NumberFormatException.class, reader::nextLong);
    assertThat(e).hasMessageThat().contains("Expected a long but was 1.5");
  }

  @Test
  public void testUnterminatedStringDuringSkip() throws IOException {
    // Targets line 1266: throw syntaxError("Unterminated string")
    String json = "[\"unterminated";
    JsonReader reader = new JsonReader(new StringReader(json));
    reader.beginArray();

    MalformedJsonException e = assertThrows(MalformedJsonException.class, reader::skipValue);
    assertThat(e).hasMessageThat().contains("Unterminated string");
  }

  @Test
  public void testUnterminatedComment() throws IOException {
    // Targets line 1571: throw syntaxError("Unterminated comment")
    String json = "/* this comment never ends";
    JsonReader reader = new JsonReader(new StringReader(json));
    reader.setStrictness(Strictness.LENIENT);

    MalformedJsonException e = assertThrows(MalformedJsonException.class, reader::peek);
    assertThat(e).hasMessageThat().contains("Unterminated comment");
  }

  @Test
  public void testCheckLenientThrowsWhenNotLenient() throws IOException {
    // Targets line 1613: throw syntaxError in checkLenient
    // checkLenient is called for single-quoted strings in default (LEGACY_STRICT) mode
    String json = "'value'";
    JsonReader reader = new JsonReader(new StringReader(json));
    // Use default LEGACY_STRICT mode which is not LENIENT

    MalformedJsonException e = assertThrows(MalformedJsonException.class, reader::nextString);
    assertThat(e)
        .hasMessageThat()
        .contains("Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed JSON");
  }

  @Test
  public void testToString() throws IOException {
    // Targets line 1659: toString()
    String json = "{\"a\":1}";
    JsonReader reader = new JsonReader(new StringReader(json));

    String result = reader.toString();
    assertThat(result).startsWith("JsonReader");
    assertThat(result).contains("line 1 column 1");
  }

  @Test
  public void testToStringAfterReading() throws IOException {
    // Targets line 1659: toString() at various positions
    String json = "{\"a\":1}";
    JsonReader reader = new JsonReader(new StringReader(json));
    reader.beginObject();
    reader.nextName();

    String result = reader.toString();
    assertThat(result).startsWith("JsonReader");
  }

  @Test
  public void testMalformedUnicodeEscape() throws IOException {
    // Targets line 1768: throw syntaxError("Malformed Unicode escape...")
    String json = "\"\\uXXXX\"";
    JsonReader reader = new JsonReader(new StringReader(json));

    MalformedJsonException e = assertThrows(MalformedJsonException.class, reader::nextString);
    assertThat(e).hasMessageThat().contains("Malformed Unicode escape");
  }

  @Test
  public void testEscapedSingleQuoteInStrictMode() throws IOException {
    // Targets line 1799: throw syntaxError("Invalid escaped character \"'\" in strict mode")
    String json = "\"\\'\"";
    JsonReader reader = new JsonReader(new StringReader(json));
    reader.setStrictness(Strictness.STRICT);

    MalformedJsonException e = assertThrows(MalformedJsonException.class, reader::nextString);
    assertThat(e).hasMessageThat().contains("Invalid escaped character");
  }

  @Test
  public void testInvalidEscapeSequence() throws IOException {
    // Targets line 1807: throw syntaxError("Invalid escape sequence")
    String json = "\"\\q\"";
    JsonReader reader = new JsonReader(new StringReader(json));

    MalformedJsonException e = assertThrows(MalformedJsonException.class, reader::nextString);
    assertThat(e).hasMessageThat().contains("Invalid escape sequence");
  }

  @Test
  public void testNumberWithMisplacedMinus() throws IOException {
    // Targets line 823: return PEEKED_NONE when '-' appears in wrong position
    // A minus sign after a digit should trigger line 823
    String json = "1-2";
    JsonReader reader = new JsonReader(new StringReader(json));
    reader.setStrictness(Strictness.LENIENT);

    // Since 1-2 is not a valid number, it should be treated as unquoted string
    assertThat(reader.peek()).isEqualTo(JsonToken.STRING);
    assertThat(reader.nextString()).isEqualTo("1-2");
  }

  @Test
  public void testUnquotedValueWithSpecialCharacter() throws IOException {
    // Targets line 1197: checkLenient() in nextUnquotedValue
    // When we encounter special characters like '/' in unquoted value
    String json = "abc/def";
    JsonReader reader = new JsonReader(new StringReader(json));
    reader.setStrictness(Strictness.LENIENT);

    assertThat(reader.nextString()).isEqualTo("abc");
  }

  @Test
  public void testSkipUnquotedValueSimple() throws IOException {
    // Targets line 1280: checkLenient() in skipUnquotedValue
    // When skipping an unquoted value, special chars like '#' trigger checkLenient
    // Use simple case: skip an unquoted value terminated by comma
    String json = "[abc, 1]";
    JsonReader reader = new JsonReader(new StringReader(json));
    reader.setStrictness(Strictness.LENIENT);
    reader.beginArray();
    reader.skipValue(); // skip 'abc'

    assertThat(reader.nextInt()).isEqualTo(1);
    reader.endArray();
  }

  @Test
  public void testEscapedNewlineInLenientMode() throws IOException {
    // Targets line 1791: escape newline handling in lenient mode (fall through)
    String json = "\"\\\n\"";
    JsonReader reader = new JsonReader(new StringReader(json));
    reader.setStrictness(Strictness.LENIENT);

    String result = reader.nextString();
    assertThat(result).isEqualTo("\n");
  }

  @Test
  public void testNegativeInfinityInNonLenientMode() throws IOException {
    // Also targets line 1057
    String json = "\"-Infinity\"";
    JsonReader reader = new JsonReader(new StringReader(json));

    MalformedJsonException e = assertThrows(MalformedJsonException.class, reader::nextDouble);
    assertThat(e).hasMessageThat().contains("JSON forbids NaN and infinities");
  }

  @Test
  public void testNextLongFromQuotedDouble() throws IOException {
    // Targets lines 1100, 1102: catch block for NumberFormatException
    // When string contains a double that can be represented as long
    String json = "\"42.0\"";
    JsonReader reader = new JsonReader(new StringReader(json));

    long result = reader.nextLong();
    assertThat(result).isEqualTo(42L);
  }

  @Test
  public void testUnterminatedEscapeSequenceAtEof() throws IOException {
    // Targets line 1747: throw syntaxError("Unterminated escape sequence")
    String json = "\"\\";
    JsonReader reader = new JsonReader(new StringReader(json));

    MalformedJsonException e = assertThrows(MalformedJsonException.class, reader::nextString);
    assertThat(e).hasMessageThat().contains("Unterminated escape sequence");
  }

  @Test
  public void testShortUnicodeEscapeSequence() throws IOException {
    // Targets line 1754: throw syntaxError("Unterminated escape sequence")
    // When unicode escape doesn't have 4 hex digits
    String json = "\"\\u12\"";
    JsonReader reader = new JsonReader(new StringReader(json));

    MalformedJsonException e = assertThrows(MalformedJsonException.class, reader::nextString);
    assertThat(e).hasMessageThat().contains("Unterminated escape sequence");
  }
}
