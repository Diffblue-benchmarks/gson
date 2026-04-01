/*
 * Copyright (C) 2024 Google Inc.
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

package com.google.gson.internal;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import java.io.IOException;
import java.io.Writer;
import org.junit.Test;

public final class StreamsAppendableWriterTest {

  private Writer newAppendableWriter(StringBuilder sb) {
    return Streams.writerForAppendable(sb);
  }

  @Test
  public void testWriteCharArray() throws IOException {
    StringBuilder sb = new StringBuilder();
    Writer writer = newAppendableWriter(sb);

    writer.write(new char[]{'h', 'e', 'l', 'l', 'o'}, 0, 5);

    assertThat(sb.toString()).isEqualTo("hello");
  }

  @Test
  public void testWriteCharArrayWithOffsetAndLength() throws IOException {
    StringBuilder sb = new StringBuilder();
    Writer writer = newAppendableWriter(sb);

    writer.write(new char[]{'h', 'e', 'l', 'l', 'o'}, 1, 3);

    assertThat(sb.toString()).isEqualTo("ell");
  }

  @Test
  public void testFlushDoesNotThrow() throws IOException {
    StringBuilder sb = new StringBuilder();
    Writer writer = newAppendableWriter(sb);

    writer.flush();

    assertThat(sb.toString()).isEqualTo("");
  }

  @Test
  public void testCloseDoesNotThrow() throws IOException {
    StringBuilder sb = new StringBuilder();
    Writer writer = newAppendableWriter(sb);

    writer.close();

    assertThat(sb.toString()).isEqualTo("");
  }

  @Test
  public void testWriteInt() throws IOException {
    StringBuilder sb = new StringBuilder();
    Writer writer = newAppendableWriter(sb);

    writer.write((int) 'A');

    assertThat(sb.toString()).isEqualTo("A");
  }

  @Test
  public void testWriteIntAppendsChar() throws IOException {
    StringBuilder sb = new StringBuilder();
    Writer writer = newAppendableWriter(sb);

    writer.write((int) 'x');
    writer.write((int) 'y');

    assertThat(sb.toString()).isEqualTo("xy");
  }

  @Test
  public void testWriteString() throws IOException {
    StringBuilder sb = new StringBuilder();
    Writer writer = newAppendableWriter(sb);

    writer.write("hello", 0, 5);

    assertThat(sb.toString()).isEqualTo("hello");
  }

  @Test
  public void testWriteStringWithOffsetAndLength() throws IOException {
    StringBuilder sb = new StringBuilder();
    Writer writer = newAppendableWriter(sb);

    writer.write("hello world", 6, 5);

    assertThat(sb.toString()).isEqualTo("world");
  }

  @Test
  public void testWriteStringNullThrowsNullPointerException() throws IOException {
    StringBuilder sb = new StringBuilder();
    Writer writer = newAppendableWriter(sb);

    assertThrows(NullPointerException.class, () -> writer.write((String) null, 0, 0));
  }

  @Test
  public void testAppendCharSequence() throws IOException {
    StringBuilder sb = new StringBuilder();
    Writer writer = newAppendableWriter(sb);

    Writer result = writer.append("hello");

    assertThat(sb.toString()).isEqualTo("hello");
    assertThat(result).isSameInstanceAs(writer);
  }

  @Test
  public void testAppendNullCharSequence() throws IOException {
    StringBuilder sb = new StringBuilder();
    Writer writer = newAppendableWriter(sb);

    Writer result = writer.append(null);

    assertThat(sb.toString()).isEqualTo("null");
    assertThat(result).isSameInstanceAs(writer);
  }

  @Test
  public void testAppendCharSequenceWithStartAndEnd() throws IOException {
    StringBuilder sb = new StringBuilder();
    Writer writer = newAppendableWriter(sb);

    Writer result = writer.append("hello world", 6, 11);

    assertThat(sb.toString()).isEqualTo("world");
    assertThat(result).isSameInstanceAs(writer);
  }

  @Test
  public void testAppendCharSequenceWithStartAndEndReturnsWriter() throws IOException {
    StringBuilder sb = new StringBuilder();
    Writer writer = newAppendableWriter(sb);

    Writer result = writer.append("abc", 0, 2);

    assertThat(sb.toString()).isEqualTo("ab");
    assertThat(result).isSameInstanceAs(writer);
  }

  @Test
  public void testWriterForAppendableReturnsAppendableWriterForNonWriter() {
    StringBuilder sb = new StringBuilder();

    Writer writer = Streams.writerForAppendable(sb);

    assertThat(writer).isNotInstanceOf(StringBuilder.class);
  }

  @Test
  public void testWriterForAppendableReturnsSameWriterIfAlreadyWriter() throws IOException {
    java.io.StringWriter sw = new java.io.StringWriter();

    Writer writer = Streams.writerForAppendable(sw);

    assertThat(writer).isSameInstanceAs(sw);
  }
}
