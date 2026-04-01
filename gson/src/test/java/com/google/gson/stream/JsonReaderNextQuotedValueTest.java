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

/** Unit tests for {@link JsonReader#nextString()} focusing on {@code nextQuotedValue}. */
public final class JsonReaderNextQuotedValueTest {

  @Test
  public void testNextQuotedValueStrictModeUnescapedControlChar() throws IOException {
    // In STRICT mode, unescaped control characters (U+0000–U+001F) must be rejected
    String json = "\"\u0001\"";
    JsonReader reader = new JsonReader(new StringReader(json));
    reader.setStrictness(Strictness.STRICT);
    MalformedJsonException e = assertThrows(MalformedJsonException.class, reader::nextString);
    assertThat(e).hasMessageThat().contains("Unescaped control characters");
    reader.close();
  }

  @Test
  public void testNextQuotedValueStrictModeUnescapedTab() throws IOException {
    // Tab (U+0009) is also a control character forbidden in strict mode
    String json = "\"\t\"";
    JsonReader reader = new JsonReader(new StringReader(json));
    reader.setStrictness(Strictness.STRICT);
    MalformedJsonException e = assertThrows(MalformedJsonException.class, reader::nextString);
    assertThat(e).hasMessageThat().contains("Unescaped control characters");
    reader.close();
  }

  @Test
  public void testNextQuotedValueNewlineInStringLenientMode() throws IOException {
    // A literal newline inside a quoted string should be accepted in LENIENT mode
    // and the lineNumber counter should be updated (line 1165-1166)
    String json = "\"hello\nworld\"";
    JsonReader reader = new JsonReader(new StringReader(json));
    reader.setStrictness(Strictness.LENIENT);
    String value = reader.nextString();
    assertThat(value).isEqualTo("hello\nworld");
    reader.close();
  }

  @Test
  public void testNextQuotedValueMultipleNewlinesInString() throws IOException {
    // Multiple literal newlines inside a string in LENIENT mode, each increments lineNumber
    String json = "\"a\nb\nc\"";
    JsonReader reader = new JsonReader(new StringReader(json));
    reader.setStrictness(Strictness.LENIENT);
    String value = reader.nextString();
    assertThat(value).isEqualTo("a\nb\nc");
    reader.close();
  }

  @Test
  public void testNextQuotedValueUnterminatedString() throws IOException {
    // A string without a closing quote should trigger "Unterminated string" (line 1177)
    String json = "\"unterminated";
    JsonReader reader = new JsonReader(new StringReader(json));
    MalformedJsonException e = assertThrows(MalformedJsonException.class, reader::nextString);
    assertThat(e).hasMessageThat().contains("Unterminated string");
    reader.close();
  }

  @Test
  public void testNextQuotedValueSpanningBufferBoundary() throws IOException {
    // A string longer than the 1024-char buffer forces the builder code path (lines 1170-1175)
    // and requires fillBuffer to continue reading
    int length = 2000;
    StringBuilder sb = new StringBuilder("\"");
    for (int i = 0; i < length; i++) {
      sb.append('a');
    }
    sb.append('"');
    JsonReader reader = new JsonReader(new StringReader(sb.toString()));
    String value = reader.nextString();
    assertThat(value.length()).isEqualTo(length);
    assertThat(value).matches("a+");
    reader.close();
  }

  @Test
  public void testNextQuotedValueSpanningBufferBoundaryWithNewline() throws IOException {
    // A long string with embedded newlines spanning the buffer boundary (covers 1165-1166
    // combined with 1170-1175 when the string is read across buffer chunks)
    int segmentLength = 600;
    StringBuilder sb = new StringBuilder("\"");
    for (int i = 0; i < segmentLength; i++) {
      sb.append('b');
    }
    sb.append('\n');
    for (int i = 0; i < segmentLength; i++) {
      sb.append('c');
    }
    sb.append('"');

    JsonReader reader = new JsonReader(new StringReader(sb.toString()));
    reader.setStrictness(Strictness.LENIENT);
    String value = reader.nextString();
    assertThat(value.length()).isEqualTo(segmentLength + 1 + segmentLength);
    assertThat(value.charAt(segmentLength)).isEqualTo('\n');
    reader.close();
  }

  @Test
  public void testNextQuotedValueUnterminatedAfterBufferFill() throws IOException {
    // An unterminated string longer than the buffer forces fillBuffer calls (line 1176-1177)
    int length = 1500;
    StringBuilder sb = new StringBuilder("\"");
    for (int i = 0; i < length; i++) {
      sb.append('x');
    }
    // No closing quote
    JsonReader reader = new JsonReader(new StringReader(sb.toString()));
    MalformedJsonException e = assertThrows(MalformedJsonException.class, reader::nextString);
    assertThat(e).hasMessageThat().contains("Unterminated string");
    reader.close();
  }
}
