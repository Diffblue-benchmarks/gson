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

package com.google.gson;

import static com.google.common.truth.Truth.assertThat;

import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import org.junit.Test;

public class JsonParserTest {

  @Test
  @SuppressWarnings("deprecation")
  public void testConstructor() {
    JsonParser parser = new JsonParser();
    assertThat(parser).isNotNull();
  }

  @Test
  public void testParseStringWithObject() {
    String json = "{\"key\": \"value\"}";
    JsonElement element = JsonParser.parseString(json);
    assertThat(element.isJsonObject()).isTrue();
    assertThat(element.getAsJsonObject().get("key").getAsString()).isEqualTo("value");
  }

  @Test
  public void testParseStringWithArray() {
    String json = "[1, 2, 3]";
    JsonElement element = JsonParser.parseString(json);
    assertThat(element.isJsonArray()).isTrue();
    assertThat(element.getAsJsonArray().size()).isEqualTo(3);
  }

  @Test
  public void testParseStringWithPrimitive() {
    String json = "\"hello\"";
    JsonElement element = JsonParser.parseString(json);
    assertThat(element.isJsonPrimitive()).isTrue();
    assertThat(element.getAsString()).isEqualTo("hello");
  }

  @Test
  public void testParseReaderWithObject() throws IOException {
    Reader reader = new StringReader("{\"key\": \"value\"}");
    JsonElement element = JsonParser.parseReader(reader);
    assertThat(element.isJsonObject()).isTrue();
    assertThat(element.getAsJsonObject().get("key").getAsString()).isEqualTo("value");
  }

  @Test
  public void testParseReaderWithArray() throws IOException {
    Reader reader = new StringReader("[1, 2, 3]");
    JsonElement element = JsonParser.parseReader(reader);
    assertThat(element.isJsonArray()).isTrue();
    assertThat(element.getAsJsonArray().size()).isEqualTo(3);
  }

  @Test
  public void testParseReaderWithNull() throws IOException {
    Reader reader = new StringReader("null");
    JsonElement element = JsonParser.parseReader(reader);
    assertThat(element.isJsonNull()).isTrue();
  }

  @Test(expected = JsonSyntaxException.class)
  public void testParseReaderWithTrailingData() {
    Reader reader = new StringReader("true false");
    JsonParser.parseReader(reader);
  }

  @Test(expected = JsonSyntaxException.class)
  public void testParseReaderWithMalformedJson() {
    Reader reader = new StringReader("{invalid json}");
    JsonParser.parseReader(reader);
  }

  @Test(expected = JsonIOException.class)
  public void testParseReaderWithIOException() {
    Reader reader = new Reader() {
      private int callCount = 0;

      @Override
      public int read(char[] cbuf, int off, int len) throws IOException {
        callCount++;
        if (callCount == 1) {
          String json = "123";
          int toCopy = Math.min(json.length(), len);
          json.getChars(0, toCopy, cbuf, off);
          return toCopy;
        }
        throw new IOException("Test exception");
      }

      @Override
      public void close() throws IOException {
      }
    };
    JsonParser.parseReader(reader);
  }

  @Test
  public void testParseJsonReaderWithObject() throws IOException {
    JsonReader jsonReader = new JsonReader(new StringReader("{\"key\": \"value\"}"));
    JsonElement element = JsonParser.parseReader(jsonReader);
    assertThat(element.isJsonObject()).isTrue();
    assertThat(element.getAsJsonObject().get("key").getAsString()).isEqualTo("value");
  }

  @Test
  public void testParseJsonReaderWithArray() throws IOException {
    JsonReader jsonReader = new JsonReader(new StringReader("[1, 2, 3]"));
    JsonElement element = JsonParser.parseReader(jsonReader);
    assertThat(element.isJsonArray()).isTrue();
    assertThat(element.getAsJsonArray().size()).isEqualTo(3);
  }

  @Test
  public void testParseJsonReaderWithStrictness() throws IOException {
    JsonReader jsonReader = new JsonReader(new StringReader("{\"key\": \"value\"}"));
    jsonReader.setStrictness(Strictness.STRICT);
    JsonElement element = JsonParser.parseReader(jsonReader);
    assertThat(element.isJsonObject()).isTrue();
    assertThat(jsonReader.getStrictness()).isEqualTo(Strictness.STRICT);
  }

  @Test
  public void testParseJsonReaderWithLegacyStrictness() throws IOException {
    JsonReader jsonReader = new JsonReader(new StringReader("{\"key\": \"value\"}"));
    jsonReader.setStrictness(Strictness.LEGACY_STRICT);
    JsonElement element = JsonParser.parseReader(jsonReader);
    assertThat(element.isJsonObject()).isTrue();
    assertThat(jsonReader.getStrictness()).isEqualTo(Strictness.LEGACY_STRICT);
  }

  @Test(expected = JsonParseException.class)
  public void testParseJsonReaderWithStackOverflow() throws IOException {
    StringBuilder deepJson = new StringBuilder();
    for (int i = 0; i < 10000; i++) {
      deepJson.append("[");
    }
    deepJson.append("1");
    for (int i = 0; i < 10000; i++) {
      deepJson.append("]");
    }
    JsonReader jsonReader = new JsonReader(new StringReader(deepJson.toString()));
    JsonParser.parseReader(jsonReader);
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testParseStringDeprecated() {
    JsonParser parser = new JsonParser();
    String json = "{\"key\": \"value\"}";
    JsonElement element = parser.parse(json);
    assertThat(element.isJsonObject()).isTrue();
    assertThat(element.getAsJsonObject().get("key").getAsString()).isEqualTo("value");
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testParseReaderDeprecated() throws IOException {
    JsonParser parser = new JsonParser();
    Reader reader = new StringReader("{\"key\": \"value\"}");
    JsonElement element = parser.parse(reader);
    assertThat(element.isJsonObject()).isTrue();
    assertThat(element.getAsJsonObject().get("key").getAsString()).isEqualTo("value");
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testParseJsonReaderDeprecated() throws IOException {
    JsonParser parser = new JsonParser();
    JsonReader jsonReader = new JsonReader(new StringReader("{\"key\": \"value\"}"));
    JsonElement element = parser.parse(jsonReader);
    assertThat(element.isJsonObject()).isTrue();
    assertThat(element.getAsJsonObject().get("key").getAsString()).isEqualTo("value");
  }
}
