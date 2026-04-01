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

/** Unit tests for {@link JsonReader#skipValue()} covering escape and newline handling in quoted values. */
public final class JsonReaderSkipQuotedValueTest {

  @Test
  public void testSkipQuotedValueWithEscapeSequence() throws IOException {
    // String with a backslash escape — covers the '\\' branch (lines 1255-1258)
    JsonReader reader = new JsonReader(new StringReader("[\"hello\\\\world\"]"));
    reader.beginArray();
    reader.skipValue();
    reader.endArray();
    reader.close();
  }

  @Test
  public void testSkipQuotedValueWithEscapeSequenceUnicode() throws IOException {
    // String with a unicode escape — also covers the '\\' branch (lines 1255-1258)
    JsonReader reader = new JsonReader(new StringReader("[\"\\u0041BC\"]"));
    reader.beginArray();
    reader.skipValue();
    reader.endArray();
    reader.close();
  }

  @Test
  public void testSkipQuotedValueWithNewlineCharacter() throws IOException {
    // String containing a literal newline — covers the '\n' branch (lines 1260-1261)
    // Lenient mode is required to allow literal newlines inside strings
    JsonReader reader = new JsonReader(new StringReader("[\"hello\nworld\"]"));
    reader.setStrictness(Strictness.LENIENT);
    reader.beginArray();
    reader.skipValue();
    reader.endArray();
    reader.close();
  }

  @Test
  public void testSkipQuotedValueUnterminatedString() throws IOException {
    // Unterminated string — covers fillBuffer loop exit (lines 1264-1265) and
    // the "Unterminated string" exception (line 1266)
    JsonReader reader = new JsonReader(new StringReader("[\"unterminated"));
    reader.setStrictness(Strictness.LENIENT);
    reader.beginArray();
    MalformedJsonException e = assertThrows(MalformedJsonException.class, reader::skipValue);
    assertThat(e).hasMessageThat().contains("Unterminated string");
    reader.close();
  }

  @Test
  public void testSkipQuotedValueDoubleQuotedWithEscape() throws IOException {
    // Double-quoted string with escape via skipValue on a JSON object value
    JsonReader reader = new JsonReader(new StringReader("{\"key\":\"val\\tue\"}"));
    reader.beginObject();
    reader.nextName();
    reader.skipValue();
    reader.endObject();
    reader.close();
  }
}
