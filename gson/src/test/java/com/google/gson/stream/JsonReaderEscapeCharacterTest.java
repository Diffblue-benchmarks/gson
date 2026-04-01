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

/** Unit tests for {@link JsonReader} escape character handling. */
public final class JsonReaderEscapeCharacterTest {

  @Test
  public void testReadEscapeCharacterUnterminatedAtStart() throws IOException {
    // JSON string with backslash at end of stream — triggers "Unterminated escape sequence"
    JsonReader reader = new JsonReader(new StringReader("\"\\"));
    MalformedJsonException e = assertThrows(MalformedJsonException.class, reader::nextString);
    assertThat(e).hasMessageThat().contains("Unterminated escape sequence");
    reader.close();
  }

  @Test
  public void testReadEscapeCharacterUnterminatedUnicode() throws IOException {
    // Unicode escape with only 2 hex digits — triggers "Unterminated escape sequence"
    JsonReader reader = new JsonReader(new StringReader("\"\\u00\""));
    MalformedJsonException e = assertThrows(MalformedJsonException.class, reader::nextString);
    assertThat(e).hasMessageThat().contains("Unterminated escape sequence");
    reader.close();
  }

  @Test
  public void testReadEscapeCharacterUnicodeLowercaseHex() throws IOException {
    // Unicode escape with lowercase hex digits a-f
    JsonReader reader = new JsonReader(new StringReader("\"\\u00af\""));
    assertThat(reader.nextString()).isEqualTo("\u00af");
    reader.close();
  }

  @Test
  public void testReadEscapeCharacterUnicodeUppercaseHex() throws IOException {
    // Unicode escape with uppercase hex digits A-F
    JsonReader reader = new JsonReader(new StringReader("\"\\u00AF\""));
    assertThat(reader.nextString()).isEqualTo("\u00AF");
    reader.close();
  }

  @Test
  public void testReadEscapeCharacterMalformedUnicode() throws IOException {
    // Unicode escape with invalid hex digit — triggers "Malformed Unicode escape"
    JsonReader reader = new JsonReader(new StringReader("\"\\u00GH\""));
    MalformedJsonException e = assertThrows(MalformedJsonException.class, reader::nextString);
    assertThat(e).hasMessageThat().contains("Malformed Unicode escape");
    reader.close();
  }

  @Test
  public void testReadEscapeCharacterTab() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("\"\\t\""));
    assertThat(reader.nextString()).isEqualTo("\t");
    reader.close();
  }

  @Test
  public void testReadEscapeCharacterBackspace() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("\"\\b\""));
    assertThat(reader.nextString()).isEqualTo("\b");
    reader.close();
  }

  @Test
  public void testReadEscapeCharacterCarriageReturn() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("\"\\r\""));
    assertThat(reader.nextString()).isEqualTo("\r");
    reader.close();
  }

  @Test
  public void testReadEscapeCharacterFormFeed() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("\"\\f\""));
    assertThat(reader.nextString()).isEqualTo("\f");
    reader.close();
  }

  @Test
  public void testReadEscapeCharacterNewlineInStrictMode() throws IOException {
    // Literal newline after backslash in STRICT mode — throws exception
    JsonReader reader = new JsonReader(new StringReader("\"\\\n\""));
    reader.setStrictness(Strictness.STRICT);
    MalformedJsonException e = assertThrows(MalformedJsonException.class, reader::nextString);
    assertThat(e).hasMessageThat().contains("Cannot escape a newline character in strict mode");
    reader.close();
  }

  @Test
  public void testReadEscapeCharacterNewlineInLenientMode() throws IOException {
    // Literal newline after backslash in LENIENT mode — returns newline char via fall-through
    JsonReader reader = new JsonReader(new StringReader("\"\\\n\""));
    reader.setStrictness(Strictness.LENIENT);
    assertThat(reader.nextString()).isEqualTo("\n");
    reader.close();
  }

  @Test
  public void testReadEscapeCharacterSingleQuoteInStrictMode() throws IOException {
    // Escaped single quote in STRICT mode — throws exception
    JsonReader reader = new JsonReader(new StringReader("\"\\'\""));
    reader.setStrictness(Strictness.STRICT);
    MalformedJsonException e = assertThrows(MalformedJsonException.class, reader::nextString);
    assertThat(e).hasMessageThat().contains("Invalid escaped character");
    reader.close();
  }

  @Test
  public void testReadEscapeCharacterSingleQuoteInLenientMode() throws IOException {
    // Escaped single quote in LENIENT mode — returns the single quote character
    JsonReader reader = new JsonReader(new StringReader("\"\\'\""));
    reader.setStrictness(Strictness.LENIENT);
    assertThat(reader.nextString()).isEqualTo("'");
    reader.close();
  }

  @Test
  public void testReadEscapeCharacterInvalidSequence() throws IOException {
    // Unknown escape sequence — triggers "Invalid escape sequence"
    JsonReader reader = new JsonReader(new StringReader("\"\\q\""));
    MalformedJsonException e = assertThrows(MalformedJsonException.class, reader::nextString);
    assertThat(e).hasMessageThat().contains("Invalid escape sequence");
    reader.close();
  }
}
