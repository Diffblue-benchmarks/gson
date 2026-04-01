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

import com.google.gson.Strictness;
import java.io.IOException;
import java.io.StringReader;
import org.junit.Test;

/** Unit tests for {@link JsonReader} targeting uncovered paths in {@code peekNumber()}. */
public final class JsonReaderPeekNumberTest {

  @Test
  public void testPeekNumberNegativeInteger() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[-42]"));
    reader.beginArray();
    assertThat(reader.nextLong()).isEqualTo(-42L);
    reader.endArray();
    reader.close();
  }

  @Test
  public void testPeekNumberNegativeDecimal() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[-1.5]"));
    reader.beginArray();
    assertThat(reader.nextDouble()).isEqualTo(-1.5);
    reader.endArray();
    reader.close();
  }

  @Test
  public void testPeekNumberNegativeExponent() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[1e-2]"));
    reader.beginArray();
    assertThat(reader.nextDouble()).isEqualTo(0.01);
    reader.endArray();
    reader.close();
  }

  @Test
  public void testPeekNumberPositiveExponent() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[1e+2]"));
    reader.beginArray();
    assertThat(reader.nextDouble()).isEqualTo(100.0);
    reader.endArray();
    reader.close();
  }

  @Test
  public void testPeekNumberExponentNotation() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[1e2]"));
    reader.beginArray();
    assertThat(reader.nextDouble()).isEqualTo(100.0);
    reader.endArray();
    reader.close();
  }

  @Test
  public void testPeekNumberFractionThenExponent() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[1.5e2]"));
    reader.beginArray();
    assertThat(reader.nextDouble()).isEqualTo(150.0);
    reader.endArray();
    reader.close();
  }

  @Test
  public void testPeekNumberInvalidMinusAfterDigit() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[1-2]"));
    reader.setStrictness(Strictness.LENIENT);
    reader.beginArray();
    assertThat(reader.peek()).isEqualTo(JsonToken.STRING);
    assertThat(reader.nextString()).isEqualTo("1-2");
    reader.endArray();
    reader.close();
  }

  @Test
  public void testPeekNumberInvalidPlusAtStart() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[+1]"));
    reader.setStrictness(Strictness.LENIENT);
    reader.beginArray();
    assertThat(reader.peek()).isEqualTo(JsonToken.STRING);
    assertThat(reader.nextString()).isEqualTo("+1");
    reader.endArray();
    reader.close();
  }

  @Test
  public void testPeekNumberEAfterSign() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[-e2]"));
    reader.setStrictness(Strictness.LENIENT);
    reader.beginArray();
    assertThat(reader.peek()).isEqualTo(JsonToken.STRING);
    assertThat(reader.nextString()).isEqualTo("-e2");
    reader.endArray();
    reader.close();
  }

  @Test
  public void testPeekNumberDotAtStart() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[.5]"));
    reader.setStrictness(Strictness.LENIENT);
    reader.beginArray();
    assertThat(reader.peek()).isEqualTo(JsonToken.STRING);
    assertThat(reader.nextString()).isEqualTo(".5");
    reader.endArray();
    reader.close();
  }

  @Test
  public void testPeekNumberLeadingZero() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[01]"));
    reader.setStrictness(Strictness.LENIENT);
    reader.beginArray();
    assertThat(reader.peek()).isEqualTo(JsonToken.STRING);
    assertThat(reader.nextString()).isEqualTo("01");
    reader.endArray();
    reader.close();
  }

  @Test
  public void testPeekNumberInvalidEndingSign() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[-]"));
    reader.setStrictness(Strictness.LENIENT);
    reader.beginArray();
    assertThat(reader.peek()).isEqualTo(JsonToken.STRING);
    assertThat(reader.nextString()).isEqualTo("-");
    reader.endArray();
    reader.close();
  }

  @Test
  public void testPeekNumberInvalidEndingExponentE() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[1e]"));
    reader.setStrictness(Strictness.LENIENT);
    reader.beginArray();
    assertThat(reader.peek()).isEqualTo(JsonToken.STRING);
    assertThat(reader.nextString()).isEqualTo("1e");
    reader.endArray();
    reader.close();
  }

  @Test
  public void testPeekNumberInvalidEndingExponentSign() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[1e+]"));
    reader.setStrictness(Strictness.LENIENT);
    reader.beginArray();
    assertThat(reader.peek()).isEqualTo(JsonToken.STRING);
    assertThat(reader.nextString()).isEqualTo("1e+");
    reader.endArray();
    reader.close();
  }

  @Test
  public void testPeekNumberEOFDuringNumber() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[123"));
    reader.setStrictness(Strictness.LENIENT);
    reader.beginArray();
    assertThat(reader.nextLong()).isEqualTo(123L);
    reader.close();
  }

  @Test
  public void testPeekNumberSpanningBufferBoundary() throws IOException {
    // Construct a JSON array where the number starts near the end of the 1024-char buffer,
    // forcing fillBuffer to be called mid-number (covering the p=pos; l=limit refill path).
    // Buffer is 1024 chars: '[' + 1022 spaces + '1' fills the first buffer exactly,
    // so '2345]' is read on the next fillBuffer call while parsing the number.
    String padding = new String(new char[1022]).replace('\0', ' ');
    String json = "[" + padding + "12345]";
    JsonReader reader = new JsonReader(new StringReader(json));
    reader.beginArray();
    assertThat(reader.nextLong()).isEqualTo(12345L);
    reader.endArray();
    reader.close();
  }

  @Test
  public void testPeekNumberTooLong() throws IOException {
    // A number with more than BUFFER_SIZE (1024) digits triggers the "number too long" path,
    // returning PEEKED_NONE and falling through to PEEKED_UNQUOTED in lenient mode.
    StringBuilder sb = new StringBuilder("[1");
    for (int i = 0; i < 2048; i++) {
      sb.append('2');
    }
    sb.append(']');
    JsonReader reader = new JsonReader(new StringReader(sb.toString()));
    reader.setStrictness(Strictness.LENIENT);
    reader.beginArray();
    assertThat(reader.peek()).isEqualTo(JsonToken.STRING);
    reader.skipValue();
    reader.endArray();
    reader.close();
  }
}
