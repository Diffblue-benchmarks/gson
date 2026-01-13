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

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.NoSuchElementException;
import org.junit.Test;

/**
 * Unit tests for {@link JsonStreamParser}.
 *
 * @author Claude
 */
public class JsonStreamParserClaudeTest {

  // ========== Constructor <init>.(Ljava/lang/String;)V Tests ==========

  @Test
  public void testConstructorWithString() {
    JsonStreamParser parser = new JsonStreamParser("{\"key\":\"value\"}");
    assertThat(parser).isNotNull();
    assertThat(parser.hasNext()).isTrue();
  }

  @Test
  public void testConstructorWithEmptyString() {
    JsonStreamParser parser = new JsonStreamParser("");
    assertThat(parser).isNotNull();
    // Empty input throws JsonIOException due to EOFException on hasNext()
    assertThrows(JsonIOException.class, parser::hasNext);
  }

  @Test
  public void testConstructorWithWhitespaceOnlyString() {
    JsonStreamParser parser = new JsonStreamParser("   \t\n   ");
    assertThat(parser).isNotNull();
    // Whitespace-only input throws JsonIOException due to EOFException on hasNext()
    assertThrows(JsonIOException.class, parser::hasNext);
  }

  @Test
  public void testConstructorWithMultipleElements() {
    // LENIENT mode allows parsing multiple concatenated JSON elements
    JsonStreamParser parser = new JsonStreamParser("[1] {\"key\":\"value\"} \"string\"");
    assertThat(parser).isNotNull();
    assertThat(parser.hasNext()).isTrue();
  }

  @Test
  public void testConstructorWithSinglePrimitive() {
    JsonStreamParser parser = new JsonStreamParser("42");
    assertThat(parser).isNotNull();
    assertThat(parser.hasNext()).isTrue();
  }

  @Test
  public void testConstructorWithNullJson() {
    JsonStreamParser parser = new JsonStreamParser("null");
    assertThat(parser).isNotNull();
    assertThat(parser.hasNext()).isTrue();
  }

  // ========== Constructor <init>.(Ljava/io/Reader;)V Tests ==========

  @Test
  public void testConstructorWithReader() {
    Reader reader = new StringReader("{\"key\":\"value\"}");
    JsonStreamParser parser = new JsonStreamParser(reader);
    assertThat(parser).isNotNull();
    assertThat(parser.hasNext()).isTrue();
  }

  @Test
  public void testConstructorWithReaderEmptyInput() {
    Reader reader = new StringReader("");
    JsonStreamParser parser = new JsonStreamParser(reader);
    assertThat(parser).isNotNull();
    // Empty input throws JsonIOException due to EOFException on hasNext()
    assertThrows(JsonIOException.class, parser::hasNext);
  }

  @Test
  public void testConstructorWithReaderMultipleElements() {
    Reader reader = new StringReader("[1, 2] {\"a\":1} true");
    JsonStreamParser parser = new JsonStreamParser(reader);
    assertThat(parser).isNotNull();
    assertThat(parser.hasNext()).isTrue();
  }

  // ========== hasNext.()Z Tests ==========

  @Test
  public void testHasNextWithSingleElement() {
    JsonStreamParser parser = new JsonStreamParser("{\"key\":\"value\"}");
    assertThat(parser.hasNext()).isTrue();
    JsonElement unused = parser.next();
    assertThat(unused).isNotNull();
    assertThat(parser.hasNext()).isFalse();
  }

  @Test
  public void testHasNextWithMultipleElements() {
    JsonStreamParser parser = new JsonStreamParser("[1] [2] [3]");

    assertThat(parser.hasNext()).isTrue();
    assertThat(parser.next().isJsonArray()).isTrue();
    assertThat(parser.hasNext()).isTrue();
    assertThat(parser.next().isJsonArray()).isTrue();
    assertThat(parser.hasNext()).isTrue();
    assertThat(parser.next().isJsonArray()).isTrue();
    assertThat(parser.hasNext()).isFalse();
  }

  @Test
  public void testHasNextConsecutiveCalls() {
    JsonStreamParser parser = new JsonStreamParser("{\"key\":\"value\"}");
    // Multiple consecutive calls to hasNext() should return the same result
    assertThat(parser.hasNext()).isTrue();
    assertThat(parser.hasNext()).isTrue();
    assertThat(parser.hasNext()).isTrue();
    assertThat(parser.next().isJsonObject()).isTrue();
    assertThat(parser.hasNext()).isFalse();
    assertThat(parser.hasNext()).isFalse();
  }

  @Test
  public void testHasNextWithEmptyInput() {
    JsonStreamParser parser = new JsonStreamParser("");
    // Empty input throws JsonIOException due to EOFException on hasNext()
    assertThrows(JsonIOException.class, parser::hasNext);
  }

  @Test
  public void testHasNextWithWhitespaceOnly() {
    JsonStreamParser parser = new JsonStreamParser("   ");
    // Whitespace-only input throws JsonIOException due to EOFException on hasNext()
    assertThrows(JsonIOException.class, parser::hasNext);
  }

  @Test
  public void testHasNextReturnsTrueForPartiallyMalformedJson() {
    // In lenient mode, hasNext() uses peek() which can identify the token type,
    // even for partially malformed JSON. The actual error occurs during next().
    JsonStreamParser parser = new JsonStreamParser("\"unclosed string");
    // hasNext() returns true because peek() sees there's something to parse
    assertThat(parser.hasNext()).isTrue();
    // The actual error occurs when trying to parse
    assertThrows(JsonSyntaxException.class, parser::next);
  }

  @Test
  public void testHasNextReturnsTrueForMalformedObject() {
    // Even malformed objects like "{invalid" will have hasNext() return true
    JsonStreamParser parser = new JsonStreamParser("{invalid");
    // hasNext() returns true because peek() sees BEGIN_OBJECT token
    assertThat(parser.hasNext()).isTrue();
    // The actual error occurs when trying to parse
    assertThrows(JsonSyntaxException.class, parser::next);
  }

  @Test
  public void testHasNextThrowsJsonIOExceptionForIOError() {
    Reader failingReader =
        new Reader() {
          @Override
          public int read(char[] cbuf, int off, int len) throws IOException {
            throw new IOException("Simulated IO error");
          }

          @Override
          public void close() {}
        };

    JsonStreamParser parser = new JsonStreamParser(failingReader);
    assertThrows(JsonIOException.class, parser::hasNext);
  }

  // ========== next.()Lcom/google/gson/JsonElement; Tests ==========

  @Test
  public void testNextWithJsonObject() {
    JsonStreamParser parser = new JsonStreamParser("{\"key\":\"value\"}");
    JsonElement element = parser.next();

    assertThat(element.isJsonObject()).isTrue();
    assertThat(element.getAsJsonObject().get("key").getAsString()).isEqualTo("value");
  }

  @Test
  public void testNextWithJsonArray() {
    JsonStreamParser parser = new JsonStreamParser("[1, 2, 3]");
    JsonElement element = parser.next();

    assertThat(element.isJsonArray()).isTrue();
    assertThat(element.getAsJsonArray().size()).isEqualTo(3);
    assertThat(element.getAsJsonArray().get(0).getAsInt()).isEqualTo(1);
    assertThat(element.getAsJsonArray().get(1).getAsInt()).isEqualTo(2);
    assertThat(element.getAsJsonArray().get(2).getAsInt()).isEqualTo(3);
  }

  @Test
  public void testNextWithJsonPrimitive() {
    JsonStreamParser parser = new JsonStreamParser("42");
    JsonElement element = parser.next();

    assertThat(element.isJsonPrimitive()).isTrue();
    assertThat(element.getAsInt()).isEqualTo(42);
  }

  @Test
  public void testNextWithJsonString() {
    // In lenient mode, single-quoted strings are allowed
    JsonStreamParser parser = new JsonStreamParser("'hello'");
    JsonElement element = parser.next();

    assertThat(element.isJsonPrimitive()).isTrue();
    assertThat(element.getAsString()).isEqualTo("hello");
  }

  @Test
  public void testNextWithJsonBoolean() {
    JsonStreamParser parser = new JsonStreamParser("true false");

    JsonElement first = parser.next();
    assertThat(first.isJsonPrimitive()).isTrue();
    assertThat(first.getAsBoolean()).isTrue();

    JsonElement second = parser.next();
    assertThat(second.isJsonPrimitive()).isTrue();
    assertThat(second.getAsBoolean()).isFalse();
  }

  @Test
  public void testNextWithJsonNull() {
    JsonStreamParser parser = new JsonStreamParser("null");
    JsonElement element = parser.next();

    assertThat(element.isJsonNull()).isTrue();
  }

  @Test
  public void testNextWithMultipleElements() {
    JsonStreamParser parser = new JsonStreamParser("[1, 2] {\"a\":\"b\"} 42 true null");

    JsonElement first = parser.next();
    assertThat(first.isJsonArray()).isTrue();
    assertThat(first.getAsJsonArray().size()).isEqualTo(2);

    JsonElement second = parser.next();
    assertThat(second.isJsonObject()).isTrue();
    assertThat(second.getAsJsonObject().get("a").getAsString()).isEqualTo("b");

    JsonElement third = parser.next();
    assertThat(third.isJsonPrimitive()).isTrue();
    assertThat(third.getAsInt()).isEqualTo(42);

    JsonElement fourth = parser.next();
    assertThat(fourth.isJsonPrimitive()).isTrue();
    assertThat(fourth.getAsBoolean()).isTrue();

    JsonElement fifth = parser.next();
    assertThat(fifth.isJsonNull()).isTrue();

    assertThat(parser.hasNext()).isFalse();
  }

  @Test
  public void testNextWithNestedObjects() {
    JsonStreamParser parser = new JsonStreamParser("{\"outer\":{\"inner\":{\"deep\":\"value\"}}}");
    JsonElement element = parser.next();

    assertThat(element.isJsonObject()).isTrue();
    JsonObject outer = element.getAsJsonObject();
    JsonObject inner = outer.getAsJsonObject("outer").getAsJsonObject("inner");
    assertThat(inner.get("deep").getAsString()).isEqualTo("value");
  }

  @Test
  public void testNextWithNestedArrays() {
    JsonStreamParser parser = new JsonStreamParser("[[1, 2], [3, 4]]");
    JsonElement element = parser.next();

    assertThat(element.isJsonArray()).isTrue();
    JsonArray array = element.getAsJsonArray();
    assertThat(array.size()).isEqualTo(2);
    assertThat(array.get(0).getAsJsonArray().get(0).getAsInt()).isEqualTo(1);
    assertThat(array.get(1).getAsJsonArray().get(1).getAsInt()).isEqualTo(4);
  }

  @Test
  public void testNextThrowsNoSuchElementExceptionWhenExhausted() {
    JsonStreamParser parser = new JsonStreamParser("{\"key\":\"value\"}");
    JsonElement consumed = parser.next(); // Consume the only element
    assertThat(consumed.isJsonObject()).isTrue();
    assertThrows(NoSuchElementException.class, parser::next);
  }

  @Test
  public void testNextThrowsJsonIOExceptionOnEmptyInput() {
    JsonStreamParser parser = new JsonStreamParser("");
    // Empty input causes JsonIOException (because hasNext() throws JsonIOException for EOF)
    assertThrows(JsonIOException.class, parser::next);
  }

  @Test
  public void testNextThrowsJsonIOExceptionOnWhitespaceOnly() {
    JsonStreamParser parser = new JsonStreamParser("   ");
    // Whitespace-only input causes JsonIOException (because hasNext() throws JsonIOException for EOF)
    assertThrows(JsonIOException.class, parser::next);
  }

  @Test
  public void testNextThrowsJsonParseExceptionForMalformedJson() {
    JsonStreamParser parser = new JsonStreamParser("{\"key\":");
    // When trying to parse malformed JSON, JsonParseException (or subclass) is thrown
    assertThrows(JsonParseException.class, parser::next);
  }

  @Test
  public void testNextWithUnicodeEscapes() {
    JsonStreamParser parser = new JsonStreamParser("{\"key\":\"\\u0048\\u0065\\u006c\\u006c\\u006f\"}");
    JsonElement element = parser.next();

    assertThat(element.getAsJsonObject().get("key").getAsString()).isEqualTo("Hello");
  }

  @Test
  public void testNextWithSpecialCharacters() {
    JsonStreamParser parser = new JsonStreamParser("{\"key\":\"hello\\nworld\\t!\"}");
    JsonElement element = parser.next();

    assertThat(element.getAsJsonObject().get("key").getAsString()).isEqualTo("hello\nworld\t!");
  }

  @Test
  public void testNextWithEmptyObject() {
    JsonStreamParser parser = new JsonStreamParser("{}");
    JsonElement element = parser.next();

    assertThat(element.isJsonObject()).isTrue();
    assertThat(element.getAsJsonObject().size()).isEqualTo(0);
  }

  @Test
  public void testNextWithEmptyArray() {
    JsonStreamParser parser = new JsonStreamParser("[]");
    JsonElement element = parser.next();

    assertThat(element.isJsonArray()).isTrue();
    assertThat(element.getAsJsonArray().size()).isEqualTo(0);
  }

  @Test
  public void testNextWithScientificNotation() {
    JsonStreamParser parser = new JsonStreamParser("1.5e10");
    JsonElement element = parser.next();

    assertThat(element.isJsonPrimitive()).isTrue();
    assertThat(element.getAsDouble()).isWithin(0.001).of(1.5e10);
  }

  @Test
  public void testNextWithNegativeNumber() {
    JsonStreamParser parser = new JsonStreamParser("-42");
    JsonElement element = parser.next();

    assertThat(element.getAsInt()).isEqualTo(-42);
  }

  @Test
  public void testNextWithLargeNumber() {
    JsonStreamParser parser = new JsonStreamParser("12345678901234567890");
    JsonElement element = parser.next();

    assertThat(element.isJsonPrimitive()).isTrue();
    assertThat(element.getAsJsonPrimitive().isNumber()).isTrue();
  }

  @Test
  public void testNextWithMixedTypesArray() {
    JsonStreamParser parser = new JsonStreamParser("[1, \"two\", true, null, {\"key\":\"value\"}]");
    JsonElement element = parser.next();

    JsonArray arr = element.getAsJsonArray();
    assertThat(arr.size()).isEqualTo(5);
    assertThat(arr.get(0).getAsInt()).isEqualTo(1);
    assertThat(arr.get(1).getAsString()).isEqualTo("two");
    assertThat(arr.get(2).getAsBoolean()).isTrue();
    assertThat(arr.get(3).isJsonNull()).isTrue();
    assertThat(arr.get(4).isJsonObject()).isTrue();
  }

  @Test
  public void testNextWithWhitespaceBetweenElements() {
    JsonStreamParser parser = new JsonStreamParser("   {\"a\":1}   {\"b\":2}   ");

    JsonElement first = parser.next();
    assertThat(first.getAsJsonObject().get("a").getAsInt()).isEqualTo(1);

    JsonElement second = parser.next();
    assertThat(second.getAsJsonObject().get("b").getAsInt()).isEqualTo(2);

    assertThat(parser.hasNext()).isFalse();
  }

  @Test
  public void testNextWithNewlinesBetweenElements() {
    JsonStreamParser parser = new JsonStreamParser("{\"a\":1}\n{\"b\":2}\n{\"c\":3}");

    assertThat(parser.next().getAsJsonObject().get("a").getAsInt()).isEqualTo(1);
    assertThat(parser.next().getAsJsonObject().get("b").getAsInt()).isEqualTo(2);
    assertThat(parser.next().getAsJsonObject().get("c").getAsInt()).isEqualTo(3);
    assertThat(parser.hasNext()).isFalse();
  }

  // ========== remove.()V Tests ==========

  @Test
  public void testRemoveThrowsUnsupportedOperationException() {
    JsonStreamParser parser = new JsonStreamParser("{\"key\":\"value\"}");
    assertThrows(UnsupportedOperationException.class, parser::remove);
  }

  @Test
  public void testRemoveThrowsEvenAfterNext() {
    JsonStreamParser parser = new JsonStreamParser("{\"key\":\"value\"}");
    JsonElement element = parser.next();
    assertThat(element.isJsonObject()).isTrue();
    assertThrows(UnsupportedOperationException.class, parser::remove);
  }

  @Test
  public void testRemoveThrowsOnEmptyParser() {
    JsonStreamParser parser = new JsonStreamParser("");
    assertThrows(UnsupportedOperationException.class, parser::remove);
  }

  // ========== Iterator Interface Tests ==========

  @Test
  public void testImplementsIteratorInterface() {
    JsonStreamParser parser = new JsonStreamParser("{}");
    // Verify that JsonStreamParser implements Iterator<JsonElement>
    assertThat(parser).isInstanceOf(java.util.Iterator.class);
  }

  @Test
  public void testIteratorPatternWithWhileLoop() {
    JsonStreamParser parser = new JsonStreamParser("{\"a\":1} {\"b\":2} {\"c\":3}");

    int count = 0;
    while (parser.hasNext()) {
      JsonElement element = parser.next();
      assertThat(element.isJsonObject()).isTrue();
      count++;
    }

    assertThat(count).isEqualTo(3);
  }

  // ========== Lenient Mode Tests ==========

  @Test
  public void testLenientModeSingleQuotedStrings() {
    // LENIENT mode allows single-quoted strings
    JsonStreamParser parser = new JsonStreamParser("{'key':'value'}");
    JsonElement element = parser.next();

    assertThat(element.isJsonObject()).isTrue();
    assertThat(element.getAsJsonObject().get("key").getAsString()).isEqualTo("value");
  }

  @Test
  public void testLenientModeUnquotedKeys() {
    // LENIENT mode allows unquoted keys
    JsonStreamParser parser = new JsonStreamParser("{key:\"value\"}");
    JsonElement element = parser.next();

    assertThat(element.isJsonObject()).isTrue();
    assertThat(element.getAsJsonObject().get("key").getAsString()).isEqualTo("value");
  }

  @Test
  public void testLenientModeUnquotedStringValues() {
    // LENIENT mode allows unquoted string values
    JsonStreamParser parser = new JsonStreamParser("{\"key\":value}");
    JsonElement element = parser.next();

    assertThat(element.isJsonObject()).isTrue();
    assertThat(element.getAsJsonObject().get("key").getAsString()).isEqualTo("value");
  }

  @Test
  public void testLenientModeUnquotedValue() {
    // LENIENT mode allows unquoted values
    JsonStreamParser parser = new JsonStreamParser("{\"key\":unquoted}");
    JsonElement element = parser.next();

    assertThat(element.isJsonObject()).isTrue();
    assertThat(element.getAsJsonObject().get("key").getAsString()).isEqualTo("unquoted");
  }

  @Test
  public void testLenientModeMultipleTopLevelValues() {
    // LENIENT mode allows multiple top-level values without separators
    JsonStreamParser parser = new JsonStreamParser("[1, 2, 3][4, 5]");

    JsonElement first = parser.next();
    assertThat(first.isJsonArray()).isTrue();
    assertThat(first.getAsJsonArray().size()).isEqualTo(3);

    JsonElement second = parser.next();
    assertThat(second.isJsonArray()).isTrue();
    assertThat(second.getAsJsonArray().size()).isEqualTo(2);
  }

  // ========== Edge Cases ==========

  @Test
  public void testDeeplyNestedStructure() {
    StringBuilder json = new StringBuilder();
    for (int i = 0; i < 50; i++) {
      json.append("{\"nested\":");
    }
    json.append("\"value\"");
    for (int i = 0; i < 50; i++) {
      json.append("}");
    }

    JsonStreamParser parser = new JsonStreamParser(json.toString());
    JsonElement element = parser.next();

    assertThat(element.isJsonObject()).isTrue();
    assertThat(parser.hasNext()).isFalse();
  }

  @Test
  public void testMultipleElementsWithDifferentTypes() {
    // Test parsing of multiple elements with different JSON types
    JsonStreamParser parser = new JsonStreamParser("null true false 123 \"string\" [] {}");

    assertThat(parser.next().isJsonNull()).isTrue();
    assertThat(parser.next().getAsBoolean()).isTrue();
    assertThat(parser.next().getAsBoolean()).isFalse();
    assertThat(parser.next().getAsInt()).isEqualTo(123);
    assertThat(parser.next().getAsString()).isEqualTo("string");
    assertThat(parser.next().isJsonArray()).isTrue();
    assertThat(parser.next().isJsonObject()).isTrue();
    assertThat(parser.hasNext()).isFalse();
  }

  @Test
  public void testLargeArrayParsing() {
    StringBuilder json = new StringBuilder("[");
    for (int i = 0; i < 1000; i++) {
      if (i > 0) {
        json.append(",");
      }
      json.append(i);
    }
    json.append("]");

    JsonStreamParser parser = new JsonStreamParser(json.toString());
    JsonElement element = parser.next();

    assertThat(element.isJsonArray()).isTrue();
    assertThat(element.getAsJsonArray().size()).isEqualTo(1000);
  }

  @Test
  public void testReaderClosedAfterParsing() throws IOException {
    // Create a reader that tracks if it's been closed
    final boolean[] closed = {false};
    Reader reader =
        new StringReader("{\"key\":\"value\"}") {
          @Override
          public void close() {
            super.close();
            closed[0] = true;
          }
        };

    JsonStreamParser parser = new JsonStreamParser(reader);
    JsonElement element = parser.next();
    assertThat(element.isJsonObject()).isTrue();
    // Note: JsonStreamParser doesn't close the reader; that's the caller's responsibility
    // This test verifies the behavior
    assertThat(closed[0]).isFalse();
  }

  @Test
  public void testConstructorWithStringContainingOnlyNull() {
    JsonStreamParser parser = new JsonStreamParser("null");
    assertThat(parser.hasNext()).isTrue();
    JsonElement element = parser.next();
    assertThat(element.isJsonNull()).isTrue();
    assertThat(parser.hasNext()).isFalse();
  }

  @Test
  public void testDoubleQuotedString() {
    JsonStreamParser parser = new JsonStreamParser("\"hello world\"");
    JsonElement element = parser.next();
    assertThat(element.isJsonPrimitive()).isTrue();
    assertThat(element.getAsString()).isEqualTo("hello world");
  }
}
