/*
 * Copyright (C) 2009 Google Inc.
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
import static org.junit.Assert.assertThrows;

import com.google.gson.stream.JsonReader;
import java.io.StringReader;
import org.junit.Test;

@SuppressWarnings("deprecation")
public final class JsonParserTest {

  @Test
  public void testDeprecatedConstructor() {
    JsonParser parser = new JsonParser();
    assertThat(parser).isNotNull();
  }

  @Test
  public void testParseString() {
    JsonElement element = JsonParser.parseString("{\"key\":\"value\"}");
    assertThat(element.isJsonObject()).isTrue();
    assertThat(element.getAsJsonObject().get("key").getAsString()).isEqualTo("value");
  }

  @Test
  public void testParseStringPrimitive() {
    JsonElement element = JsonParser.parseString("\"hello\"");
    assertThat(element.getAsString()).isEqualTo("hello");
  }

  @Test
  public void testParseStringArray() {
    JsonElement element = JsonParser.parseString("[1,2,3]");
    assertThat(element.isJsonArray()).isTrue();
    assertThat(element.getAsJsonArray().size()).isEqualTo(3);
  }

  @Test
  public void testParseStringInvalid() {
    assertThrows(JsonSyntaxException.class, () -> JsonParser.parseString("{invalid}"));
  }

  @Test
  public void testParseReader() {
    JsonElement element = JsonParser.parseReader(new StringReader("{\"a\":1}"));
    assertThat(element.isJsonObject()).isTrue();
    assertThat(element.getAsJsonObject().get("a").getAsInt()).isEqualTo(1);
  }

  @Test
  public void testParseReaderInvalid() {
    assertThrows(JsonSyntaxException.class,
        () -> JsonParser.parseReader(new StringReader("{bad json}")));
  }

  @Test
  public void testParseReaderMultipleTopLevel() {
    assertThrows(JsonSyntaxException.class,
        () -> JsonParser.parseReader(new StringReader("{} {}")));
  }

  @Test
  public void testParseJsonReader() {
    JsonReader jsonReader = new JsonReader(new StringReader("{\"x\":42}"));
    JsonElement element = JsonParser.parseReader(jsonReader);
    assertThat(element.isJsonObject()).isTrue();
    assertThat(element.getAsJsonObject().get("x").getAsInt()).isEqualTo(42);
  }

  @Test
  public void testParseJsonReaderLenientMode() {
    JsonReader jsonReader = new JsonReader(new StringReader("'singleQuoted'"));
    jsonReader.setStrictness(Strictness.LENIENT);
    JsonElement element = JsonParser.parseReader(jsonReader);
    assertThat(element.getAsString()).isEqualTo("singleQuoted");
  }

  @Test
  public void testDeprecatedParseString() {
    JsonParser parser = new JsonParser();
    JsonElement element = parser.parse("{\"key\":\"value\"}");
    assertThat(element.isJsonObject()).isTrue();
    assertThat(element.getAsJsonObject().get("key").getAsString()).isEqualTo("value");
  }

  @Test
  public void testDeprecatedParseReader() {
    JsonParser parser = new JsonParser();
    JsonElement element = parser.parse(new StringReader("[1,2]"));
    assertThat(element.isJsonArray()).isTrue();
    assertThat(element.getAsJsonArray().size()).isEqualTo(2);
  }

  @Test
  public void testDeprecatedParseJsonReader() {
    JsonParser parser = new JsonParser();
    JsonReader jsonReader = new JsonReader(new StringReader("true"));
    JsonElement element = parser.parse(jsonReader);
    assertThat(element.getAsBoolean()).isTrue();
  }
}
