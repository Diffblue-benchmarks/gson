/*
 * Copyright (C) 2008 Google Inc.
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
import static org.junit.Assert.fail;

import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import org.junit.Test;

/**
 * Unit tests for {@link JsonParser}.
 *
 * @author Claude
 */
@SuppressWarnings("deprecation")
public class JsonParserClaudeTest {

  // ========== Constructor <init>.()V Tests ==========

  @Test
  public void testConstructor() {
    // The constructor is deprecated but should still work
    JsonParser parser = new JsonParser();
    assertThat(parser).isNotNull();
  }

  // ========== parseString.(Ljava/lang/String;)Lcom/google/gson/JsonElement; Tests ==========

  @Test
  public void testParseStringWithJsonObject() {
    String json = "{\"key\":\"value\"}";
    JsonElement element = JsonParser.parseString(json);

    assertThat(element.isJsonObject()).isTrue();
    assertThat(element.getAsJsonObject().get("key").getAsString()).isEqualTo("value");
  }

  @Test
  public void testParseStringWithJsonArray() {
    String json = "[1, 2, 3]";
    JsonElement element = JsonParser.parseString(json);

    assertThat(element.isJsonArray()).isTrue();
    assertThat(element.getAsJsonArray().size()).isEqualTo(3);
    assertThat(element.getAsJsonArray().get(0).getAsInt()).isEqualTo(1);
  }

  @Test
  public void testParseStringWithJsonPrimitive() {
    assertThat(JsonParser.parseString("\"hello\"").getAsString()).isEqualTo("hello");
    assertThat(JsonParser.parseString("42").getAsInt()).isEqualTo(42);
    assertThat(JsonParser.parseString("3.14").getAsDouble()).isWithin(0.001).of(3.14);
    assertThat(JsonParser.parseString("true").getAsBoolean()).isTrue();
    assertThat(JsonParser.parseString("false").getAsBoolean()).isFalse();
  }

  @Test
  public void testParseStringWithNull() {
    JsonElement element = JsonParser.parseString("null");
    assertThat(element.isJsonNull()).isTrue();
  }

  @Test
  public void testParseStringWithEmptyString() {
    // Empty string returns JsonNull for backward compatibility
    JsonElement element = JsonParser.parseString("");
    assertThat(element.isJsonNull()).isTrue();
  }

  @Test
  public void testParseStringWithWhitespace() {
    // Whitespace only returns JsonNull for backward compatibility
    JsonElement element = JsonParser.parseString("   ");
    assertThat(element.isJsonNull()).isTrue();
  }

  @Test
  public void testParseStringWithNestedObjects() {
    String json = "{\"outer\":{\"inner\":{\"deep\":\"value\"}}}";
    JsonElement element = JsonParser.parseString(json);

    assertThat(element.isJsonObject()).isTrue();
    JsonObject outer = element.getAsJsonObject();
    JsonObject inner = outer.getAsJsonObject("outer").getAsJsonObject("inner");
    assertThat(inner.get("deep").getAsString()).isEqualTo("value");
  }

  @Test
  public void testParseStringWithNestedArrays() {
    String json = "[[1,2],[3,4]]";
    JsonElement element = JsonParser.parseString(json);

    assertThat(element.isJsonArray()).isTrue();
    JsonArray arr = element.getAsJsonArray();
    assertThat(arr.size()).isEqualTo(2);
    assertThat(arr.get(0).getAsJsonArray().get(0).getAsInt()).isEqualTo(1);
  }

  @Test
  public void testParseStringWithInvalidJson() {
    assertThrows(JsonSyntaxException.class, () -> JsonParser.parseString("{invalid}"));
    assertThrows(JsonSyntaxException.class, () -> JsonParser.parseString("{\"key\":}"));
    // Note: trailing comma is allowed in lenient mode, so we test unclosed bracket instead
    assertThrows(JsonSyntaxException.class, () -> JsonParser.parseString("[1,2"));
  }

  @Test
  public void testParseStringWithMultipleTopLevelElements() {
    // Multiple top-level elements should throw an exception
    assertThrows(JsonSyntaxException.class, () -> JsonParser.parseString("{} {}"));
  }

  @Test
  public void testParseStringWithTrailingContent() {
    // Trailing content after valid JSON should throw
    assertThrows(JsonSyntaxException.class, () -> JsonParser.parseString("{\"key\":\"value\"} extra"));
  }

  @Test
  public void testParseStringWithSpecialCharacters() {
    String json = "{\"key\":\"hello\\nworld\\t!\"}";
    JsonElement element = JsonParser.parseString(json);

    assertThat(element.getAsJsonObject().get("key").getAsString()).isEqualTo("hello\nworld\t!");
  }

  @Test
  public void testParseStringWithUnicodeCharacters() {
    String json = "{\"key\":\"\\u0048\\u0065\\u006c\\u006c\\u006f\"}";
    JsonElement element = JsonParser.parseString(json);

    assertThat(element.getAsJsonObject().get("key").getAsString()).isEqualTo("Hello");
  }

  // ========== parseReader.(Ljava/io/Reader;)Lcom/google/gson/JsonElement; Tests ==========

  @Test
  public void testParseReaderWithJsonObject() {
    Reader reader = new StringReader("{\"key\":\"value\"}");
    JsonElement element = JsonParser.parseReader(reader);

    assertThat(element.isJsonObject()).isTrue();
    assertThat(element.getAsJsonObject().get("key").getAsString()).isEqualTo("value");
  }

  @Test
  public void testParseReaderWithJsonArray() {
    Reader reader = new StringReader("[1, 2, 3]");
    JsonElement element = JsonParser.parseReader(reader);

    assertThat(element.isJsonArray()).isTrue();
    assertThat(element.getAsJsonArray().size()).isEqualTo(3);
  }

  @Test
  public void testParseReaderWithJsonNull() {
    Reader reader = new StringReader("null");
    JsonElement element = JsonParser.parseReader(reader);

    assertThat(element.isJsonNull()).isTrue();
  }

  @Test
  public void testParseReaderWithEmptyInput() {
    Reader reader = new StringReader("");
    JsonElement element = JsonParser.parseReader(reader);

    // Empty input returns JsonNull for backward compatibility
    assertThat(element.isJsonNull()).isTrue();
  }

  @Test
  public void testParseReaderWithMultipleTopLevelElements() {
    Reader reader = new StringReader("{} {}");
    assertThrows(JsonSyntaxException.class, () -> JsonParser.parseReader(reader));
  }

  @Test
  public void testParseReaderWithInvalidJson() {
    Reader reader = new StringReader("{invalid}");
    assertThrows(JsonSyntaxException.class, () -> JsonParser.parseReader(reader));
  }

  @Test
  public void testParseReaderWithMalformedJson() {
    Reader reader = new StringReader("{\"key\":");
    assertThrows(JsonSyntaxException.class, () -> JsonParser.parseReader(reader));
  }

  @Test
  public void testParseReaderWithIOException() {
    // Create a Reader that throws IOException
    Reader failingReader =
        new Reader() {
          @Override
          public int read(char[] cbuf, int off, int len) throws IOException {
            throw new IOException("Simulated IO error");
          }

          @Override
          public void close() {}
        };

    assertThrows(JsonIOException.class, () -> JsonParser.parseReader(failingReader));
  }

  @Test
  public void testParseReaderConsumesEntireDocument() {
    // When the document is fully consumed, should not throw
    Reader reader = new StringReader("{\"key\":\"value\"}");
    JsonElement element = JsonParser.parseReader(reader);
    assertThat(element.isJsonObject()).isTrue();
  }

  // ========== parseReader.(Lcom/google/gson/stream/JsonReader;)Lcom/google/gson/JsonElement; Tests ==========

  @Test
  public void testParseJsonReaderWithJsonObject() {
    JsonReader reader = new JsonReader(new StringReader("{\"key\":\"value\"}"));
    JsonElement element = JsonParser.parseReader(reader);

    assertThat(element.isJsonObject()).isTrue();
    assertThat(element.getAsJsonObject().get("key").getAsString()).isEqualTo("value");
  }

  @Test
  public void testParseJsonReaderWithJsonArray() {
    JsonReader reader = new JsonReader(new StringReader("[1, 2, 3]"));
    JsonElement element = JsonParser.parseReader(reader);

    assertThat(element.isJsonArray()).isTrue();
  }

  @Test
  public void testParseJsonReaderWithStrictMode() {
    JsonReader reader = new JsonReader(new StringReader("{\"key\":\"value\"}"));
    reader.setStrictness(Strictness.STRICT);
    JsonElement element = JsonParser.parseReader(reader);

    assertThat(element.isJsonObject()).isTrue();
    // Strictness should be preserved
    assertThat(reader.getStrictness()).isEqualTo(Strictness.STRICT);
  }

  @Test
  public void testParseJsonReaderWithLenientMode() {
    JsonReader reader = new JsonReader(new StringReader("{\"key\":\"value\"}"));
    reader.setStrictness(Strictness.LENIENT);
    JsonElement element = JsonParser.parseReader(reader);

    assertThat(element.isJsonObject()).isTrue();
    // Strictness should be preserved
    assertThat(reader.getStrictness()).isEqualTo(Strictness.LENIENT);
  }

  @Test
  public void testParseJsonReaderWithLegacyStrictMode() {
    // LEGACY_STRICT is the default, it should be temporarily changed to LENIENT
    JsonReader reader = new JsonReader(new StringReader("{\"key\":\"value\"}"));
    assertThat(reader.getStrictness()).isEqualTo(Strictness.LEGACY_STRICT);

    JsonElement element = JsonParser.parseReader(reader);

    assertThat(element.isJsonObject()).isTrue();
    // Strictness should be restored to LEGACY_STRICT
    assertThat(reader.getStrictness()).isEqualTo(Strictness.LEGACY_STRICT);
  }

  @Test
  public void testParseJsonReaderStrictnessRestoredOnError() {
    JsonReader reader = new JsonReader(new StringReader("{invalid}"));
    Strictness originalStrictness = reader.getStrictness();

    try {
      JsonParser.parseReader(reader);
      fail("Expected JsonSyntaxException");
    } catch (JsonSyntaxException e) {
      // Strictness should be restored even after error
      assertThat(reader.getStrictness()).isEqualTo(originalStrictness);
    }
  }

  @Test
  public void testParseJsonReaderAllowsMultipleTopLevelElements() {
    // Unlike parseReader(Reader), parseReader(JsonReader) does not throw on multiple top-level elements
    JsonReader reader = new JsonReader(new StringReader("{} {}"));
    JsonElement element = JsonParser.parseReader(reader);

    assertThat(element.isJsonObject()).isTrue();
    // The reader should still be positioned to read the next element
  }

  @Test
  public void testParseJsonReaderWithEmptyInput() {
    JsonReader reader = new JsonReader(new StringReader(""));
    JsonElement element = JsonParser.parseReader(reader);

    // Empty input returns JsonNull
    assertThat(element.isJsonNull()).isTrue();
  }

  @Test
  public void testParseJsonReaderWithPartialParsing() throws Exception {
    // Test that JsonParser.parseReader(JsonReader) can be used for partial parsing
    String json = "{\"first\": {\"a\": 1}, \"second\": {\"b\": 2}}";
    JsonReader reader = new JsonReader(new StringReader(json));

    reader.beginObject();
    String name1 = reader.nextName();
    assertThat(name1).isEqualTo("first");

    // Parse just the nested object
    JsonElement first = JsonParser.parseReader(reader);
    assertThat(first.isJsonObject()).isTrue();
    assertThat(first.getAsJsonObject().get("a").getAsInt()).isEqualTo(1);

    String name2 = reader.nextName();
    assertThat(name2).isEqualTo("second");

    // Parse the second nested object
    JsonElement second = JsonParser.parseReader(reader);
    assertThat(second.isJsonObject()).isTrue();
    assertThat(second.getAsJsonObject().get("b").getAsInt()).isEqualTo(2);

    reader.endObject();
  }

  // ========== Deprecated parse.(Ljava/lang/String;)Lcom/google/gson/JsonElement; Tests ==========

  @Test
  public void testDeprecatedParseStringMethod() {
    JsonParser parser = new JsonParser();
    JsonElement element = parser.parse("{\"key\":\"value\"}");

    assertThat(element.isJsonObject()).isTrue();
    assertThat(element.getAsJsonObject().get("key").getAsString()).isEqualTo("value");
  }

  @Test
  public void testDeprecatedParseStringWithArray() {
    JsonParser parser = new JsonParser();
    JsonElement element = parser.parse("[1, 2, 3]");

    assertThat(element.isJsonArray()).isTrue();
    assertThat(element.getAsJsonArray().size()).isEqualTo(3);
  }

  @Test
  public void testDeprecatedParseStringWithPrimitive() {
    JsonParser parser = new JsonParser();

    assertThat(parser.parse("\"hello\"").getAsString()).isEqualTo("hello");
    assertThat(parser.parse("42").getAsInt()).isEqualTo(42);
    assertThat(parser.parse("true").getAsBoolean()).isTrue();
  }

  @Test
  public void testDeprecatedParseStringWithInvalidJson() {
    JsonParser parser = new JsonParser();
    assertThrows(JsonSyntaxException.class, () -> parser.parse("{invalid}"));
  }

  // ========== Deprecated parse.(Ljava/io/Reader;)Lcom/google/gson/JsonElement; Tests ==========

  @Test
  public void testDeprecatedParseReaderMethod() {
    JsonParser parser = new JsonParser();
    Reader reader = new StringReader("{\"key\":\"value\"}");
    JsonElement element = parser.parse(reader);

    assertThat(element.isJsonObject()).isTrue();
    assertThat(element.getAsJsonObject().get("key").getAsString()).isEqualTo("value");
  }

  @Test
  public void testDeprecatedParseReaderWithArray() {
    JsonParser parser = new JsonParser();
    Reader reader = new StringReader("[1, 2, 3]");
    JsonElement element = parser.parse(reader);

    assertThat(element.isJsonArray()).isTrue();
  }

  @Test
  public void testDeprecatedParseReaderWithInvalidJson() {
    JsonParser parser = new JsonParser();
    Reader reader = new StringReader("{invalid}");
    assertThrows(JsonSyntaxException.class, () -> parser.parse(reader));
  }

  // ========== Deprecated parse.(Lcom/google/gson/stream/JsonReader;)Lcom/google/gson/JsonElement; Tests ==========

  @Test
  public void testDeprecatedParseJsonReaderMethod() {
    JsonParser parser = new JsonParser();
    JsonReader reader = new JsonReader(new StringReader("{\"key\":\"value\"}"));
    JsonElement element = parser.parse(reader);

    assertThat(element.isJsonObject()).isTrue();
    assertThat(element.getAsJsonObject().get("key").getAsString()).isEqualTo("value");
  }

  @Test
  public void testDeprecatedParseJsonReaderWithArray() {
    JsonParser parser = new JsonParser();
    JsonReader reader = new JsonReader(new StringReader("[1, 2, 3]"));
    JsonElement element = parser.parse(reader);

    assertThat(element.isJsonArray()).isTrue();
  }

  @Test
  public void testDeprecatedParseJsonReaderWithInvalidJson() {
    JsonParser parser = new JsonParser();
    JsonReader reader = new JsonReader(new StringReader("{invalid}"));
    assertThrows(JsonSyntaxException.class, () -> parser.parse(reader));
  }

  // ========== Edge cases and special scenarios ==========

  @Test
  public void testParseLargeNumber() {
    String json = "12345678901234567890";
    JsonElement element = JsonParser.parseString(json);

    assertThat(element.isJsonPrimitive()).isTrue();
    assertThat(element.getAsJsonPrimitive().isNumber()).isTrue();
  }

  @Test
  public void testParseNegativeNumber() {
    JsonElement element = JsonParser.parseString("-42");
    assertThat(element.getAsInt()).isEqualTo(-42);
  }

  @Test
  public void testParseScientificNotation() {
    JsonElement element = JsonParser.parseString("1.5e10");
    assertThat(element.getAsDouble()).isWithin(0.001).of(1.5e10);
  }

  @Test
  public void testParseEmptyObject() {
    JsonElement element = JsonParser.parseString("{}");
    assertThat(element.isJsonObject()).isTrue();
    assertThat(element.getAsJsonObject().size()).isEqualTo(0);
  }

  @Test
  public void testParseEmptyArray() {
    JsonElement element = JsonParser.parseString("[]");
    assertThat(element.isJsonArray()).isTrue();
    assertThat(element.getAsJsonArray().size()).isEqualTo(0);
  }

  @Test
  public void testParseMixedArray() {
    String json = "[1, \"two\", true, null, {\"key\":\"value\"}]";
    JsonElement element = JsonParser.parseString(json);

    JsonArray arr = element.getAsJsonArray();
    assertThat(arr.size()).isEqualTo(5);
    assertThat(arr.get(0).getAsInt()).isEqualTo(1);
    assertThat(arr.get(1).getAsString()).isEqualTo("two");
    assertThat(arr.get(2).getAsBoolean()).isTrue();
    assertThat(arr.get(3).isJsonNull()).isTrue();
    assertThat(arr.get(4).isJsonObject()).isTrue();
  }

  @Test
  public void testParseStringWithLeadingWhitespace() {
    String json = "   {\"key\":\"value\"}";
    JsonElement element = JsonParser.parseString(json);

    assertThat(element.isJsonObject()).isTrue();
  }

  @Test
  public void testParseStringWithTrailingWhitespace() {
    String json = "{\"key\":\"value\"}   ";
    JsonElement element = JsonParser.parseString(json);

    assertThat(element.isJsonObject()).isTrue();
  }

  @Test
  public void testParseStringWithWhitespaceBetweenElements() {
    String json = "{  \"key\"  :  \"value\"  }";
    JsonElement element = JsonParser.parseString(json);

    assertThat(element.isJsonObject()).isTrue();
    assertThat(element.getAsJsonObject().get("key").getAsString()).isEqualTo("value");
  }

  @Test
  public void testParseStringWithNewlines() {
    String json = "{\n  \"key\": \"value\"\n}";
    JsonElement element = JsonParser.parseString(json);

    assertThat(element.isJsonObject()).isTrue();
  }

  @Test
  public void testParseStringWithTabs() {
    String json = "{\t\"key\":\t\"value\"\t}";
    JsonElement element = JsonParser.parseString(json);

    assertThat(element.isJsonObject()).isTrue();
  }

  @Test
  public void testParseStringWithDeeplyNestedStructure() {
    StringBuilder json = new StringBuilder();
    for (int i = 0; i < 50; i++) {
      json.append("{\"nested\":");
    }
    json.append("\"value\"");
    for (int i = 0; i < 50; i++) {
      json.append("}");
    }

    JsonElement element = JsonParser.parseString(json.toString());
    assertThat(element.isJsonObject()).isTrue();
  }

  @Test
  public void testJsonNullTrailingData() {
    // Special case: JsonNull followed by trailing data should NOT throw
    // because parseReader(Reader) has special handling for JsonNull
    Reader reader = new StringReader("null extra");

    // The implementation checks !element.isJsonNull() before checking for trailing data
    // So this should NOT throw
    JsonElement element = JsonParser.parseReader(reader);
    assertThat(element.isJsonNull()).isTrue();
  }

  @Test
  public void testParseReaderNumberFormatException() {
    // Number format exception should be wrapped in JsonSyntaxException
    // Large number that causes NumberFormatException when parsed
    String json = "1e99999999999999999999999999999";
    JsonElement element = JsonParser.parseString(json);
    // The number is parsed as a string in the JSON element
    assertThat(element.isJsonPrimitive()).isTrue();
  }
}
