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

public class JsonParserTest {

  @Test
  public void testParseString_object() {
    String json = "{\"key\":\"value\"}";
    JsonElement element = JsonParser.parseString(json);
    assertThat(element.isJsonObject()).isTrue();
    assertThat(element.getAsJsonObject().get("key").getAsString()).isEqualTo("value");
  }

  @Test
  public void testParseString_array() {
    String json = "[1,2,3]";
    JsonElement element = JsonParser.parseString(json);
    assertThat(element.isJsonArray()).isTrue();
    JsonArray array = element.getAsJsonArray();
    assertThat(array.size()).isEqualTo(3);
    assertThat(array.get(0).getAsInt()).isEqualTo(1);
  }

  @Test
  public void testParseString_primitive() {
    String json = "\"hello\"";
    JsonElement element = JsonParser.parseString(json);
    assertThat(element.isJsonPrimitive()).isTrue();
    assertThat(element.getAsString()).isEqualTo("hello");
  }

  @Test
  public void testParseString_number() {
    String json = "123";
    JsonElement element = JsonParser.parseString(json);
    assertThat(element.isJsonPrimitive()).isTrue();
    assertThat(element.getAsInt()).isEqualTo(123);
  }

  @Test
  public void testParseString_boolean() {
    String json = "true";
    JsonElement element = JsonParser.parseString(json);
    assertThat(element.isJsonPrimitive()).isTrue();
    assertThat(element.getAsBoolean()).isTrue();
  }

  @Test
  public void testParseString_null() {
    String json = "null";
    JsonElement element = JsonParser.parseString(json);
    assertThat(element.isJsonNull()).isTrue();
  }

  @Test
  public void testParseString_nestedObject() {
    String json = "{\"outer\":{\"inner\":\"value\"}}";
    JsonElement element = JsonParser.parseString(json);
    JsonObject outer = element.getAsJsonObject();
    JsonObject inner = outer.getAsJsonObject("outer");
    assertThat(inner.get("inner").getAsString()).isEqualTo("value");
  }

  @Test
  public void testParseString_emptyObject() {
    String json = "{}";
    JsonElement element = JsonParser.parseString(json);
    assertThat(element.isJsonObject()).isTrue();
    assertThat(element.getAsJsonObject().size()).isEqualTo(0);
  }

  @Test
  public void testParseString_emptyArray() {
    String json = "[]";
    JsonElement element = JsonParser.parseString(json);
    assertThat(element.isJsonArray()).isTrue();
    assertThat(element.getAsJsonArray().size()).isEqualTo(0);
  }

  @Test
  public void testParseString_invalidJson_throwsException() {
    String json = "{invalid}";
    assertThrows(JsonSyntaxException.class, () -> JsonParser.parseString(json));
  }

  @Test
  public void testParseString_multipleTopLevel_throwsException() {
    String json = "{}{}";
    assertThrows(JsonSyntaxException.class, () -> JsonParser.parseString(json));
  }

  @Test
  public void testParseString_trailingData_throwsException() {
    String json = "{} extra";
    assertThrows(JsonSyntaxException.class, () -> JsonParser.parseString(json));
  }

  @Test
  public void testParseReader_object() {
    String json = "{\"key\":\"value\"}";
    JsonElement element = JsonParser.parseReader(new StringReader(json));
    assertThat(element.isJsonObject()).isTrue();
    assertThat(element.getAsJsonObject().get("key").getAsString()).isEqualTo("value");
  }

  @Test
  public void testParseReader_multipleTopLevel_throwsException() {
    String json = "{}{}";
    assertThrows(JsonSyntaxException.class, () -> JsonParser.parseReader(new StringReader(json)));
  }

  @Test
  public void testParseReader_jsonReader_singleElement() throws Exception {
    String json = "{\"key\":\"value\"}";
    JsonReader reader = new JsonReader(new StringReader(json));
    JsonElement element = JsonParser.parseReader(reader);
    assertThat(element.isJsonObject()).isTrue();
    assertThat(element.getAsJsonObject().get("key").getAsString()).isEqualTo("value");
  }

  @Test
  public void testParseReader_jsonReader_multipleElements() throws Exception {
    String json = "{\"first\":1}{\"second\":2}";
    JsonReader reader = new JsonReader(new StringReader(json));

    JsonElement first = JsonParser.parseReader(reader);
    assertThat(first.getAsJsonObject().get("first").getAsInt()).isEqualTo(1);

    JsonElement second = JsonParser.parseReader(reader);
    assertThat(second.getAsJsonObject().get("second").getAsInt()).isEqualTo(2);
  }

  @Test
  public void testParseReader_jsonReader_partialParsing() throws Exception {
    String json = "{\"skipObj\": {\"skipKey\": \"skipValue\"}, \"obj\": {\"key\": \"value\"}}";
    JsonReader jsonReader = new JsonReader(new StringReader(json));

    jsonReader.beginObject();
    while (jsonReader.hasNext()) {
      String fieldName = jsonReader.nextName();
      if (fieldName.equals("skipObj")) {
        jsonReader.skipValue();
      } else {
        JsonElement element = JsonParser.parseReader(jsonReader);
        assertThat(element.isJsonObject()).isTrue();
        assertThat(element.getAsJsonObject().get("key").getAsString()).isEqualTo("value");
      }
    }
    jsonReader.endObject();
  }

  @Test
  public void testParseString_complexJson() {
    String json =
        "{"
            + "\"name\":\"John\","
            + "\"age\":30,"
            + "\"active\":true,"
            + "\"address\":{\"city\":\"NYC\"},"
            + "\"hobbies\":[\"reading\",\"coding\"]"
            + "}";
    JsonElement element = JsonParser.parseString(json);
    JsonObject obj = element.getAsJsonObject();
    assertThat(obj.get("name").getAsString()).isEqualTo("John");
    assertThat(obj.get("age").getAsInt()).isEqualTo(30);
    assertThat(obj.get("active").getAsBoolean()).isTrue();
    assertThat(obj.getAsJsonObject("address").get("city").getAsString()).isEqualTo("NYC");
    assertThat(obj.getAsJsonArray("hobbies").size()).isEqualTo(2);
  }

  @Test
  public void testParseString_unicodeEscapes() {
    String json = "\"\\u0048\\u0065\\u006C\\u006C\\u006F\"";
    JsonElement element = JsonParser.parseString(json);
    assertThat(element.getAsString()).isEqualTo("Hello");
  }

  @Test
  public void testParseString_escapedCharacters() {
    String json = "\"line1\\nline2\\ttab\"";
    JsonElement element = JsonParser.parseString(json);
    assertThat(element.getAsString()).isEqualTo("line1\nline2\ttab");
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testConstructor_deprecated() {
    JsonParser parser = new JsonParser();
    assertThat(parser).isNotNull();
  }

  @Test
  public void testParseString_numberFormats() {
    assertThat(JsonParser.parseString("123").getAsInt()).isEqualTo(123);
    assertThat(JsonParser.parseString("123.45").getAsDouble()).isEqualTo(123.45);
    assertThat(JsonParser.parseString("-123").getAsInt()).isEqualTo(-123);
    assertThat(JsonParser.parseString("1.23e2").getAsDouble()).isEqualTo(123.0);
    assertThat(JsonParser.parseString("1.23E+2").getAsDouble()).isEqualTo(123.0);
  }

}
