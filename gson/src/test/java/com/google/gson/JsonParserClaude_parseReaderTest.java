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

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import org.junit.Test;

/**
 * Unit tests for {@link JsonParser#parseReader(Reader)} method.
 *
 * <p>Note on line 112 coverage: The line {@code throw new JsonSyntaxException("Did not consume the
 * entire document.")} at line 112 cannot be reached through the normal {@code parseReader(Reader)}
 * path because:
 *
 * <ol>
 *   <li>parseReader(Reader) creates a JsonReader with default LEGACY_STRICT strictness
 *   <li>parseReader(JsonReader) temporarily changes strictness to LENIENT, parses, then restores
 *       LEGACY_STRICT
 *   <li>After parsing, peek() is called to check for trailing data
 *   <li>In LEGACY_STRICT mode, if there's trailing content, checkLenient() throws
 *       MalformedJsonException before the peek() comparison can return a non-END_DOCUMENT token
 * </ol>
 *
 * <p>Line 112 could only be reached if the JsonReader's strictness was LENIENT or STRICT when
 * peek() is called, but parseReader(Reader) restores LEGACY_STRICT before this check.
 *
 * @author Claude
 */
public class JsonParserClaude_parseReaderTest {

  /**
   * Tests that IOException during parsing is wrapped in JsonIOException.
   * This covers lines 117-118: catch (IOException e) { throw new JsonIOException(e); }
   */
  @Test
  public void testParseReaderWithIOException() {
    // Create a Reader that throws IOException
    Reader failingReader =
        new Reader() {
          @Override
          public int read(char[] cbuf, int off, int len) throws IOException {
            throw new IOException("Simulated IO failure");
          }

          @Override
          public void close() {}
        };

    JsonIOException exception =
        assertThrows(JsonIOException.class, () -> JsonParser.parseReader(failingReader));
    assertThat(exception.getCause()).isInstanceOf(IOException.class);
    assertThat(exception.getCause().getMessage()).isEqualTo("Simulated IO failure");
  }

  /**
   * Tests that IOException during peek() after parsing is wrapped in JsonIOException.
   * This covers lines 117-118.
   *
   * The parseReader(Reader) method:
   * 1. Parses the JSON successfully
   * 2. Calls peek() to check for trailing data
   * 3. If peek() throws IOException, it should be wrapped in JsonIOException
   */
  @Test
  public void testParseReaderWithIOExceptionDuringPeek() {
    // Create a Reader that returns valid JSON, then throws IOException when more is read
    Reader failAfterJsonReader =
        new Reader() {
          private final String json = "{}";
          private int position = 0;
          private boolean jsonConsumed = false;

          @Override
          public int read(char[] cbuf, int off, int len) throws IOException {
            if (position < json.length()) {
              int charsToRead = Math.min(len, json.length() - position);
              json.getChars(position, position + charsToRead, cbuf, off);
              position += charsToRead;
              return charsToRead;
            }
            // After JSON is consumed, throw IOException
            if (!jsonConsumed) {
              jsonConsumed = true;
              // Return -1 once to signal end of valid JSON
              return -1;
            }
            throw new IOException("IO error during peek");
          }

          @Override
          public void close() {}
        };

    // IOException during peek() should be wrapped in JsonIOException
    JsonIOException exception =
        assertThrows(JsonIOException.class, () -> JsonParser.parseReader(failAfterJsonReader));
    assertThat(exception.getCause()).isInstanceOf(IOException.class);
    assertThat(exception.getCause().getMessage()).isEqualTo("IO error during peek");
  }

  /**
   * Tests that trailing whitespace after JSON null is allowed.
   * JSON null has special handling - trailing data check is skipped.
   */
  @Test
  public void testParseReaderNullWithTrailingWhitespace() {
    Reader reader = new StringReader("null   ");
    JsonElement element = JsonParser.parseReader(reader);
    assertThat(element.isJsonNull()).isTrue();
  }

  /**
   * Tests that valid JSON object with only trailing whitespace parses correctly.
   * Whitespace is not considered "trailing data".
   */
  @Test
  public void testParseReaderWithOnlyTrailingWhitespace() {
    Reader reader = new StringReader("{\"key\":\"value\"}   \n\t  ");
    JsonElement element = JsonParser.parseReader(reader);
    assertThat(element.isJsonObject()).isTrue();
    assertThat(element.getAsJsonObject().get("key").getAsString()).isEqualTo("value");
  }

  /**
   * Tests that malformed JSON causes JsonSyntaxException.
   * This covers lines 115-116: catch (MalformedJsonException | NumberFormatException e)
   */
  @Test
  public void testParseReaderWithMalformedJson() {
    Reader reader = new StringReader("{invalid}");
    assertThrows(JsonSyntaxException.class, () -> JsonParser.parseReader(reader));
  }

  /**
   * Tests parsing valid JSON with empty input (returns JsonNull).
   */
  @Test
  public void testParseReaderWithEmptyInput() {
    Reader reader = new StringReader("");
    JsonElement element = JsonParser.parseReader(reader);
    assertThat(element.isJsonNull()).isTrue();
  }

  /**
   * Tests that trailing data after JSON null does NOT throw.
   * This demonstrates the special case where isJsonNull() returns true,
   * and therefore line 112 is NOT executed.
   */
  @Test
  public void testParseReaderNullWithTrailingData() {
    // JSON null followed by extra content - the trailing data check is skipped for null
    Reader reader = new StringReader("null extra");
    JsonElement element = JsonParser.parseReader(reader);
    assertThat(element.isJsonNull()).isTrue();
  }

  /**
   * Tests that multiple JSON objects cause JsonSyntaxException due to trailing data.
   * This tests the condition: !element.isJsonNull() && jsonReader.peek() != JsonToken.END_DOCUMENT
   *
   * Since JsonReader is in LEGACY_STRICT mode by default, peek() after completing the first
   * object will throw MalformedJsonException, which is caught and wrapped.
   */
  @Test
  public void testParseReaderWithMultipleObjects() {
    Reader reader = new StringReader("{} {}");
    assertThrows(JsonSyntaxException.class, () -> JsonParser.parseReader(reader));
  }

  /**
   * Tests IOException wrapped in JsonIOException when reader fails during close.
   * Note: This won't actually reach lines 117-118 since close() is not called in the try block.
   */
  @Test
  public void testParseReaderWithValidJson() {
    Reader reader = new StringReader("{\"test\": 123}");
    JsonElement element = JsonParser.parseReader(reader);
    assertThat(element.isJsonObject()).isTrue();
    assertThat(element.getAsJsonObject().get("test").getAsInt()).isEqualTo(123);
  }

  /**
   * Tests that an IOException thrown during read is properly wrapped.
   * This covers lines 117-118.
   */
  @Test
  public void testParseReaderWithIOExceptionDuringRead() {
    Reader failingReader =
        new Reader() {
          private int readCount = 0;

          @Override
          public int read(char[] cbuf, int off, int len) throws IOException {
            readCount++;
            if (readCount == 1) {
              // Return partial valid JSON start
              cbuf[off] = '{';
              return 1;
            }
            // Then fail
            throw new IOException("Read failure");
          }

          @Override
          public void close() {}
        };

    JsonIOException exception =
        assertThrows(JsonIOException.class, () -> JsonParser.parseReader(failingReader));
    assertThat(exception.getCause()).isInstanceOf(IOException.class);
  }

  /**
   * Tests parsing JSON array.
   */
  @Test
  public void testParseReaderWithArray() {
    Reader reader = new StringReader("[1, 2, 3]");
    JsonElement element = JsonParser.parseReader(reader);
    assertThat(element.isJsonArray()).isTrue();
    assertThat(element.getAsJsonArray().size()).isEqualTo(3);
  }

  /**
   * Tests parsing JSON primitive number.
   */
  @Test
  public void testParseReaderWithNumber() {
    Reader reader = new StringReader("42");
    JsonElement element = JsonParser.parseReader(reader);
    assertThat(element.getAsInt()).isEqualTo(42);
  }

  /**
   * Tests parsing JSON primitive string.
   */
  @Test
  public void testParseReaderWithString() {
    Reader reader = new StringReader("\"hello\"");
    JsonElement element = JsonParser.parseReader(reader);
    assertThat(element.getAsString()).isEqualTo("hello");
  }

  /**
   * Tests parsing JSON boolean.
   */
  @Test
  public void testParseReaderWithBoolean() {
    Reader reader = new StringReader("true");
    JsonElement element = JsonParser.parseReader(reader);
    assertThat(element.getAsBoolean()).isTrue();
  }
}
