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

/** Unit tests for {@link JsonReader#doPeek()} covering uncovered branches. */
public final class JsonReaderDoPeekTest {

  @Test
  public void testSemicolonSeparatorInArrayLenient() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[1;2]"));
    reader.setStrictness(Strictness.LENIENT);
    reader.beginArray();
    assertThat(reader.nextInt()).isEqualTo(1);
    assertThat(reader.nextInt()).isEqualTo(2);
    reader.endArray();
    reader.close();
  }

  @Test
  public void testUnterminatedArrayThrows() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[1 2]"));
    reader.beginArray();
    reader.nextInt();
    assertThrows(MalformedJsonException.class, reader::peek);
    reader.close();
  }

  @Test
  public void testSemicolonSeparatorInObjectLenient() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{\"a\":1;\"b\":2}"));
    reader.setStrictness(Strictness.LENIENT);
    reader.beginObject();
    assertThat(reader.nextName()).isEqualTo("a");
    assertThat(reader.nextInt()).isEqualTo(1);
    assertThat(reader.nextName()).isEqualTo("b");
    assertThat(reader.nextInt()).isEqualTo(2);
    reader.endObject();
    reader.close();
  }

  @Test
  public void testUnterminatedObjectThrows() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{\"a\":1 \"b\":2}"));
    reader.beginObject();
    reader.nextName();
    reader.nextInt();
    assertThrows(MalformedJsonException.class, reader::peek);
    reader.close();
  }

  @Test
  public void testSingleQuotedNameLenient() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{'key':1}"));
    reader.setStrictness(Strictness.LENIENT);
    reader.beginObject();
    assertThat(reader.nextName()).isEqualTo("key");
    assertThat(reader.nextInt()).isEqualTo(1);
    reader.endObject();
    reader.close();
  }

  @Test
  public void testClosingBraceAfterNonemptyObjectThrows() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{\"a\":1,}"));
    reader.beginObject();
    reader.nextName();
    reader.nextInt();
    assertThrows(MalformedJsonException.class, reader::peek);
    reader.close();
  }

  @Test
  public void testUnquotedNameLenient() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{key:1}"));
    reader.setStrictness(Strictness.LENIENT);
    reader.beginObject();
    assertThat(reader.nextName()).isEqualTo("key");
    assertThat(reader.nextInt()).isEqualTo(1);
    reader.endObject();
    reader.close();
  }

  @Test
  public void testNonLiteralCharInNamePositionLenientThrows() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{/:1}"));
    reader.setStrictness(Strictness.LENIENT);
    reader.beginObject();
    assertThrows(MalformedJsonException.class, reader::peek);
    reader.close();
  }

  @Test
  public void testEqualsSignSeparatorLenient() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{\"a\"=\"b\"}"));
    reader.setStrictness(Strictness.LENIENT);
    reader.beginObject();
    assertThat(reader.nextName()).isEqualTo("a");
    assertThat(reader.nextString()).isEqualTo("b");
    reader.endObject();
    reader.close();
  }

  @Test
  public void testArrowSeparatorLenient() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{\"a\"=>\"b\"}"));
    reader.setStrictness(Strictness.LENIENT);
    reader.beginObject();
    assertThat(reader.nextName()).isEqualTo("a");
    assertThat(reader.nextString()).isEqualTo("b");
    reader.endObject();
    reader.close();
  }

  @Test
  public void testMissingColonThrows() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{\"a\" 1}"));
    reader.beginObject();
    reader.nextName();
    assertThrows(MalformedJsonException.class, reader::peek);
    reader.close();
  }

  @Test
  public void testMultipleTopLevelValuesLenient() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("1 2"));
    reader.setStrictness(Strictness.LENIENT);
    assertThat(reader.nextInt()).isEqualTo(1);
    assertThat(reader.peek()).isEqualTo(JsonToken.NUMBER);
    assertThat(reader.nextInt()).isEqualTo(2);
    reader.close();
  }

  @Test
  public void testPeekAfterCloseThrows() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    reader.close();
    assertThrows(IllegalStateException.class, reader::peek);
  }

  @Test
  public void testLenientNullFromCommaAtArrayStart() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[,1]"));
    reader.setStrictness(Strictness.LENIENT);
    reader.beginArray();
    assertThat(reader.peek()).isEqualTo(JsonToken.NULL);
    reader.nextNull();
    assertThat(reader.nextInt()).isEqualTo(1);
    reader.endArray();
    reader.close();
  }

  @Test
  public void testCommaAtTopLevelThrows() throws IOException {
    JsonReader reader = new JsonReader(new StringReader(","));
    reader.setStrictness(Strictness.LENIENT);
    assertThrows(MalformedJsonException.class, reader::peek);
    reader.close();
  }

  @Test
  public void testSingleQuotedValueLenient() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("'hello'"));
    reader.setStrictness(Strictness.LENIENT);
    assertThat(reader.peek()).isEqualTo(JsonToken.STRING);
    assertThat(reader.nextString()).isEqualTo("hello");
    reader.close();
  }

  @Test
  public void testNonLiteralValueThrows() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{\"key\": }"));
    reader.beginObject();
    reader.nextName();
    assertThrows(MalformedJsonException.class, reader::peek);
    reader.close();
  }
}
