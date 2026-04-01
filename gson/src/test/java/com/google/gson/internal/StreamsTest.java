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

import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonNull;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import org.junit.Test;

public final class StreamsTest {

  @Test
  public void testParseEmptyReaderReturnsJsonNull() {
    JsonReader reader = new JsonReader(new StringReader(""));
    JsonElement result = Streams.parse(reader);
    assertThat(result).isEqualTo(JsonNull.INSTANCE);
  }

  @Test
  public void testParseJsonString() {
    JsonReader reader = new JsonReader(new StringReader("\"hello\""));
    JsonElement result = Streams.parse(reader);
    assertThat(result.getAsString()).isEqualTo("hello");
  }

  @Test
  public void testParseJsonNumber() {
    JsonReader reader = new JsonReader(new StringReader("42"));
    JsonElement result = Streams.parse(reader);
    assertThat(result.getAsInt()).isEqualTo(42);
  }

  @Test
  public void testParseJsonObject() {
    JsonReader reader = new JsonReader(new StringReader("{\"key\":\"value\"}"));
    JsonElement result = Streams.parse(reader);
    assertThat(result.getAsJsonObject().get("key").getAsString()).isEqualTo("value");
  }

  @Test
  public void testParseMalformedJsonThrowsJsonSyntaxException() {
    JsonReader reader = new JsonReader(new StringReader("{malformed"));
    assertThrows(JsonSyntaxException.class, () -> Streams.parse(reader));
  }

  @Test
  public void testParseIncompleteJsonThrowsJsonSyntaxException() {
    JsonReader reader = new JsonReader(new StringReader("{\"key\":"));
    assertThrows(JsonSyntaxException.class, () -> Streams.parse(reader));
  }

  @Test
  public void testWriteJsonElement() throws IOException {
    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    Streams.write(new JsonPrimitive("hello"), writer);
    writer.flush();
    assertThat(sw.toString()).isEqualTo("\"hello\"");
  }

  @Test
  public void testWriteJsonNull() throws IOException {
    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    Streams.write(JsonNull.INSTANCE, writer);
    writer.flush();
    assertThat(sw.toString()).isEqualTo("null");
  }

  @Test
  public void testWriterForAppendableWithWriter() {
    StringWriter sw = new StringWriter();
    Writer result = Streams.writerForAppendable(sw);
    assertThat(result).isSameInstanceAs(sw);
  }

  @Test
  public void testWriterForAppendableWithNonWriter() throws IOException {
    StringBuilder sb = new StringBuilder();
    Writer result = Streams.writerForAppendable(sb);
    assertThat(result).isNotSameInstanceAs(sb);
    result.write("test");
    result.flush();
    assertThat(sb.toString()).isEqualTo("test");
  }

  @Test
  public void testConstructorThrowsUnsupportedOperationException() {
    assertThrows(UnsupportedOperationException.class, () -> {
      try {
        java.lang.reflect.Constructor<Streams> constructor =
            Streams.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        constructor.newInstance();
      } catch (java.lang.reflect.InvocationTargetException e) {
        throw e.getCause();
      }
    });
  }
}
