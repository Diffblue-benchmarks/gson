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

package com.google.gson.internal;

import static com.google.common.truth.Truth.assertThat;

import java.io.IOException;
import java.io.Writer;
import org.junit.Test;

public class StreamsTest {

  @Test
  public void testWriterForAppendableWithWriter() {
    StringBuilder sb = new StringBuilder();
    Writer result = Streams.writerForAppendable(sb);
    assertThat(result).isNotNull();
  }

  @Test
  public void testWriterForAppendableWithStringBuilder() {
    StringBuilder sb = new StringBuilder();
    Writer result = Streams.writerForAppendable(sb);
    assertThat(result).isNotNull();
  }

  @Test
  public void testAppendableWriterWriteCharArray() throws IOException {
    StringBuilder sb = new StringBuilder();
    Writer writer = Streams.writerForAppendable(sb);

    char[] chars = {'h', 'e', 'l', 'l', 'o'};
    writer.write(chars, 0, 5);

    assertThat(sb.toString()).isEqualTo("hello");
  }

  @Test
  public void testAppendableWriterWriteCharArrayWithOffset() throws IOException {
    StringBuilder sb = new StringBuilder();
    Writer writer = Streams.writerForAppendable(sb);

    char[] chars = {'h', 'e', 'l', 'l', 'o', ' ', 'w', 'o', 'r', 'l', 'd'};
    writer.write(chars, 6, 5);

    assertThat(sb.toString()).isEqualTo("world");
  }

  @Test
  public void testAppendableWriterFlush() throws IOException {
    StringBuilder sb = new StringBuilder();
    Writer writer = Streams.writerForAppendable(sb);

    writer.write("test");
    writer.flush();

    assertThat(sb.toString()).isEqualTo("test");
  }

  @Test
  public void testAppendableWriterClose() throws IOException {
    StringBuilder sb = new StringBuilder();
    Writer writer = Streams.writerForAppendable(sb);

    writer.write("test");
    writer.close();

    assertThat(sb.toString()).isEqualTo("test");
  }

  @Test
  public void testAppendableWriterWriteSingleChar() throws IOException {
    StringBuilder sb = new StringBuilder();
    Writer writer = Streams.writerForAppendable(sb);

    writer.write('a');
    writer.write('b');
    writer.write('c');

    assertThat(sb.toString()).isEqualTo("abc");
  }

  @Test
  public void testAppendableWriterWriteSingleCharAsInt() throws IOException {
    StringBuilder sb = new StringBuilder();
    Writer writer = Streams.writerForAppendable(sb);

    writer.write(65);
    writer.write(66);
    writer.write(67);

    assertThat(sb.toString()).isEqualTo("ABC");
  }

  @Test
  public void testAppendableWriterWriteString() throws IOException {
    StringBuilder sb = new StringBuilder();
    Writer writer = Streams.writerForAppendable(sb);

    writer.write("hello world", 0, 11);

    assertThat(sb.toString()).isEqualTo("hello world");
  }

  @Test
  public void testAppendableWriterWriteStringWithOffset() throws IOException {
    StringBuilder sb = new StringBuilder();
    Writer writer = Streams.writerForAppendable(sb);

    writer.write("hello world", 6, 5);

    assertThat(sb.toString()).isEqualTo("world");
  }

  @Test
  public void testAppendableWriterWriteStringWithNullThrows() throws IOException {
    StringBuilder sb = new StringBuilder();
    Writer writer = Streams.writerForAppendable(sb);

    try {
      writer.write((String) null, 0, 0);
      throw new AssertionError("Expected NullPointerException");
    } catch (NullPointerException expected) {
    }
  }

  @Test
  public void testAppendableWriterAppendCharSequence() throws IOException {
    StringBuilder sb = new StringBuilder();
    Writer writer = Streams.writerForAppendable(sb);

    Writer result = writer.append("hello");

    assertThat(sb.toString()).isEqualTo("hello");
    assertThat(result).isSameInstanceAs(writer);
  }

  @Test
  public void testAppendableWriterAppendNull() throws IOException {
    StringBuilder sb = new StringBuilder();
    Writer writer = Streams.writerForAppendable(sb);

    Writer result = writer.append(null);

    assertThat(sb.toString()).isEqualTo("null");
    assertThat(result).isSameInstanceAs(writer);
  }

  @Test
  public void testAppendableWriterAppendCharSequenceWithRange() throws IOException {
    StringBuilder sb = new StringBuilder();
    Writer writer = Streams.writerForAppendable(sb);

    Writer result = writer.append("hello world", 6, 11);

    assertThat(sb.toString()).isEqualTo("world");
    assertThat(result).isSameInstanceAs(writer);
  }

  @Test
  public void testAppendableWriterAppendCharSequenceWithRangeNull() throws IOException {
    StringBuilder sb = new StringBuilder();
    Writer writer = Streams.writerForAppendable(sb);

    Writer result = writer.append(null, 0, 4);

    assertThat(sb.toString()).isEqualTo("null");
    assertThat(result).isSameInstanceAs(writer);
  }
}
