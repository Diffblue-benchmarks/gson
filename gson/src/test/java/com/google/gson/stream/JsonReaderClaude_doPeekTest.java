/*
 * Copyright (C) 2010 Google Inc.
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import com.google.gson.Strictness;
import java.io.IOException;
import java.io.StringReader;
import org.junit.Test;

/**
 * Tests for {@link JsonReader#doPeek()} method to improve branch coverage.
 */
public class JsonReaderClaude_doPeekTest {

  // ==================== Line 600: Unterminated array error ====================
  // Triggered when in NONEMPTY_ARRAY and character after element is not ']', ';', or ','

  @Test
  public void testDoPeek_unterminatedArray_unexpectedCharacterAfterElement() throws IOException {
    // After reading first element, next char should be ] or , but we provide an invalid char
    try (JsonReader reader = new JsonReader(new StringReader("[1 2]"))) {
      reader.beginArray();
      var unused = reader.nextInt();
      // Now we're in NONEMPTY_ARRAY, next char is space then '2' which is invalid
      MalformedJsonException ex = assertThrows(MalformedJsonException.class, () -> reader.peek());
      assertTrue(ex.getMessage().contains("Unterminated array"));
    }
  }

  @Test
  public void testDoPeek_unterminatedArray_unexpectedColonAfterElement() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("[1:2]"))) {
      reader.beginArray();
      var unused = reader.nextInt();
      MalformedJsonException ex = assertThrows(MalformedJsonException.class, () -> reader.peek());
      assertTrue(ex.getMessage().contains("Unterminated array"));
    }
  }

  // ==================== Line 612: Semicolon separator in object (lenient) ====================
  // Triggered when in NONEMPTY_OBJECT and semicolon is used as separator

  @Test
  public void testDoPeek_objectSemicolonSeparator_lenientMode() throws IOException {
    // Use semicolon as separator between key-value pairs in object
    try (JsonReader reader = new JsonReader(new StringReader("{\"a\":1;\"b\":2}"))) {
      reader.setStrictness(Strictness.LENIENT);
      reader.beginObject();
      assertEquals("a", reader.nextName());
      assertEquals(1, reader.nextInt());
      // Now in NONEMPTY_OBJECT, next char is ';' which requires lenient mode
      assertEquals("b", reader.nextName());
      assertEquals(2, reader.nextInt());
      reader.endObject();
    }
  }

  @Test
  public void testDoPeek_objectSemicolonSeparator_strictMode_fails() throws IOException {
    // Use a string value "x" instead of number because reading number 1; causes
    // the semicolon to be checked during peekNumber's isLiteral call
    try (JsonReader reader = new JsonReader(new StringReader("{\"a\":\"x\";\"b\":2}"))) {
      reader.setStrictness(Strictness.LEGACY_STRICT);
      reader.beginObject();
      assertEquals("a", reader.nextName());
      assertEquals("x", reader.nextString());
      // Semicolon separator is not allowed in strict mode - triggers checkLenient at doPeek line 612
      MalformedJsonException ex = assertThrows(MalformedJsonException.class, () -> reader.peek());
      assertTrue(ex.getMessage().contains("Strictness.LENIENT"));
    }
  }

  // ==================== Line 616: Unterminated object error ====================
  // Triggered when in NONEMPTY_OBJECT and unexpected character after value

  @Test
  public void testDoPeek_unterminatedObject_unexpectedCharacterAfterValue() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{\"a\":1 \"b\":2}"))) {
      reader.beginObject();
      assertEquals("a", reader.nextName());
      assertEquals(1, reader.nextInt());
      // No comma or } after 1, just space then "b"
      MalformedJsonException ex = assertThrows(MalformedJsonException.class, () -> reader.peek());
      assertTrue(ex.getMessage().contains("Unterminated object"));
    }
  }

  @Test
  public void testDoPeek_unterminatedObject_unexpectedBracketAfterValue() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{\"a\":1[}"))) {
      reader.beginObject();
      assertEquals("a", reader.nextName());
      assertEquals(1, reader.nextInt());
      MalformedJsonException ex = assertThrows(MalformedJsonException.class, () -> reader.peek());
      assertTrue(ex.getMessage().contains("Unterminated object"));
    }
  }

  // ==================== Line 633: Expected name after comma in object ====================
  // Triggered when '}' is found after comma expecting a property name

  @Test
  public void testDoPeek_expectedName_closingBraceAfterComma() throws IOException {
    // After comma in object, we expect a name but get '}'
    try (JsonReader reader = new JsonReader(new StringReader("{\"a\":1,}"))) {
      reader.beginObject();
      assertEquals("a", reader.nextName());
      assertEquals(1, reader.nextInt());
      // Now expecting a name after comma, but we have '}'
      MalformedJsonException ex = assertThrows(MalformedJsonException.class, () -> reader.peek());
      assertTrue(ex.getMessage().contains("Expected name"));
    }
  }

  @Test
  public void testDoPeek_expectedName_closingBraceAfterSemicolon_lenient() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{\"a\":1;}"))) {
      reader.setStrictness(Strictness.LENIENT);
      reader.beginObject();
      assertEquals("a", reader.nextName());
      assertEquals(1, reader.nextInt());
      // After semicolon, expect name but get '}'
      MalformedJsonException ex = assertThrows(MalformedJsonException.class, () -> reader.peek());
      assertTrue(ex.getMessage().contains("Expected name"));
    }
  }

  // ==================== Line 642: Expected name with non-literal char ====================
  // Triggered when unquoted name starts with non-literal character
  // Non-literal chars are: { } [ ] : , whitespace (and / \ ; # = in non-lenient mode)

  @Test
  public void testDoPeek_expectedName_bracketInsteadOfName() throws IOException {
    // '[' is a non-literal character in any mode
    try (JsonReader reader = new JsonReader(new StringReader("{[:1}"))) {
      reader.setStrictness(Strictness.LENIENT);
      reader.beginObject();
      // '[' is not valid as a property name start
      MalformedJsonException ex = assertThrows(MalformedJsonException.class, () -> reader.peek());
      assertTrue(ex.getMessage().contains("Expected name"));
    }
  }

  @Test
  public void testDoPeek_expectedName_colonInsteadOfName() throws IOException {
    // ':' is a non-literal character
    try (JsonReader reader = new JsonReader(new StringReader("{::1}"))) {
      reader.setStrictness(Strictness.LENIENT);
      reader.beginObject();
      MalformedJsonException ex = assertThrows(MalformedJsonException.class, () -> reader.peek());
      assertTrue(ex.getMessage().contains("Expected name"));
    }
  }

  @Test
  public void testDoPeek_expectedName_commaInsteadOfName() throws IOException {
    // ',' is a non-literal character
    try (JsonReader reader = new JsonReader(new StringReader("{,:1}"))) {
      reader.setStrictness(Strictness.LENIENT);
      reader.beginObject();
      MalformedJsonException ex = assertThrows(MalformedJsonException.class, () -> reader.peek());
      assertTrue(ex.getMessage().contains("Expected name"));
    }
  }

  // ==================== Line 659: Expected ':' error ====================
  // Triggered when there's no colon after property name

  @Test
  public void testDoPeek_expectedColon_nothingAfterName() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{\"a\"1}"))) {
      reader.beginObject();
      assertEquals("a", reader.nextName());
      // After name, we expect ':' but get '1'
      MalformedJsonException ex = assertThrows(MalformedJsonException.class, () -> reader.peek());
      assertTrue(ex.getMessage().contains("Expected ':'"));
    }
  }

  @Test
  public void testDoPeek_expectedColon_commaAfterName() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{\"a\",1}"))) {
      reader.beginObject();
      assertEquals("a", reader.nextName());
      MalformedJsonException ex = assertThrows(MalformedJsonException.class, () -> reader.peek());
      assertTrue(ex.getMessage().contains("Expected ':'"));
    }
  }

  @Test
  public void testDoPeek_expectedColon_closeBraceAfterName() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{\"a\"}"))) {
      reader.beginObject();
      assertEquals("a", reader.nextName());
      MalformedJsonException ex = assertThrows(MalformedJsonException.class, () -> reader.peek());
      assertTrue(ex.getMessage().contains("Expected ':'"));
    }
  }

  // ==================== Line 696: Unexpected value (comma outside array) ====================
  // Triggered when ',' or ';' appears where a value is expected in non-array context

  @Test
  public void testDoPeek_unexpectedValue_commaInObjectValue() throws IOException {
    // Comma where a value is expected in object
    try (JsonReader reader = new JsonReader(new StringReader("{\"a\":,}"))) {
      reader.beginObject();
      assertEquals("a", reader.nextName());
      // After ':', we expect a value but get ','
      MalformedJsonException ex = assertThrows(MalformedJsonException.class, () -> reader.peek());
      assertTrue(ex.getMessage().contains("Unexpected value"));
    }
  }

  @Test
  public void testDoPeek_unexpectedValue_semicolonInObjectValue_lenient() throws IOException {
    try (JsonReader reader = new JsonReader(new StringReader("{\"a\":;}"))) {
      reader.setStrictness(Strictness.LENIENT);
      reader.beginObject();
      assertEquals("a", reader.nextName());
      // After ':', we expect a value but get ';'
      MalformedJsonException ex = assertThrows(MalformedJsonException.class, () -> reader.peek());
      assertTrue(ex.getMessage().contains("Unexpected value"));
    }
  }

  @Test
  public void testDoPeek_unexpectedValue_commaAtDocumentRoot() throws IOException {
    // Comma at document root
    try (JsonReader reader = new JsonReader(new StringReader(","))) {
      MalformedJsonException ex = assertThrows(MalformedJsonException.class, () -> reader.peek());
      assertTrue(ex.getMessage().contains("Unexpected value"));
    }
  }

  @Test
  public void testDoPeek_unexpectedValue_closeBracketAtDocumentRoot() throws IOException {
    // Close bracket at document root (not in array context)
    try (JsonReader reader = new JsonReader(new StringReader("]"))) {
      MalformedJsonException ex = assertThrows(MalformedJsonException.class, () -> reader.peek());
      assertTrue(ex.getMessage().contains("Unexpected value"));
    }
  }

  // ==================== Line 726: Expected value with non-literal char ====================
  // Triggered when value position has non-literal character that's not a keyword or number
  // The character must pass isLiteral check but not be a valid keyword/number start
  // Non-literals: { } [ ] : , whitespace (and / \ ; # = in non-lenient)
  // Keywords start with: t, T, f, F, n, N
  // Numbers start with: digit or -

  @Test
  public void testDoPeek_expectedValue_colonAtRoot() throws IOException {
    // ':' is a non-literal character, triggers "Expected value"
    try (JsonReader reader = new JsonReader(new StringReader(":"))) {
      MalformedJsonException ex = assertThrows(MalformedJsonException.class, () -> reader.peek());
      assertTrue(ex.getMessage().contains("Expected value"));
    }
  }

  @Test
  public void testDoPeek_expectedValue_closeBraceAtRoot() throws IOException {
    // '}' at root is a non-literal, triggers "Expected value"
    try (JsonReader reader = new JsonReader(new StringReader("}"))) {
      MalformedJsonException ex = assertThrows(MalformedJsonException.class, () -> reader.peek());
      assertTrue(ex.getMessage().contains("Expected value"));
    }
  }

  @Test
  public void testDoPeek_expectedValue_colonInObjectValue() throws IOException {
    // Inside object value position, ':' is a non-literal
    try (JsonReader reader = new JsonReader(new StringReader("{\"a\"::}"))) {
      reader.beginObject();
      assertEquals("a", reader.nextName());
      // After first ':', we get another ':' which is invalid
      MalformedJsonException ex = assertThrows(MalformedJsonException.class, () -> reader.peek());
      assertTrue(ex.getMessage().contains("Expected value"));
    }
  }

  @Test
  public void testDoPeek_expectedValue_closeBracketInObjectValue() throws IOException {
    // ']' in object value position is non-literal - triggers "Unexpected value" at line 696
    // because it falls through from line 681 switch case ']' into the ';' / ',' case
    try (JsonReader reader = new JsonReader(new StringReader("{\"a\":]}"))) {
      reader.beginObject();
      assertEquals("a", reader.nextName());
      MalformedJsonException ex = assertThrows(MalformedJsonException.class, () -> reader.peek());
      assertTrue(ex.getMessage().contains("Unexpected value"));
    }
  }

  // ==================== Additional edge cases for better coverage ====================

  @Test
  public void testDoPeek_arrayWithTrailingComma_lenient_treatedAsNull() throws IOException {
    // In lenient mode, trailing comma in array means implicit null
    try (JsonReader reader = new JsonReader(new StringReader("[1,]"))) {
      reader.setStrictness(Strictness.LENIENT);
      reader.beginArray();
      assertEquals(1, reader.nextInt());
      // After comma with no value, in lenient mode it's null
      assertEquals(JsonToken.NULL, reader.peek());
      reader.nextNull();
      reader.endArray();
    }
  }

  @Test
  public void testDoPeek_arrayWithMultipleCommas_lenient() throws IOException {
    // Multiple commas mean multiple nulls in lenient mode
    // [,,] has 3 implied elements: null, null, null (or 2 depending on parsing)
    try (JsonReader reader = new JsonReader(new StringReader("[,,]"))) {
      reader.setStrictness(Strictness.LENIENT);
      reader.beginArray();
      // First implied null (before first comma)
      assertEquals(JsonToken.NULL, reader.peek());
      reader.nextNull();
      // Second implied null (between commas)
      assertEquals(JsonToken.NULL, reader.peek());
      reader.nextNull();
      // Third implied null (after last comma before ])
      assertEquals(JsonToken.NULL, reader.peek());
      reader.nextNull();
      reader.endArray();
    }
  }

  @Test
  public void testDoPeek_semicolonAsArraySeparator_lenient() throws IOException {
    // Semicolon as array element separator in lenient mode
    try (JsonReader reader = new JsonReader(new StringReader("[1;2;3]"))) {
      reader.setStrictness(Strictness.LENIENT);
      reader.beginArray();
      assertEquals(1, reader.nextInt());
      assertEquals(2, reader.nextInt());
      assertEquals(3, reader.nextInt());
      reader.endArray();
    }
  }

  @Test
  public void testDoPeek_equalsAsNameValueSeparator_lenient() throws IOException {
    // '=' instead of ':' in lenient mode
    try (JsonReader reader = new JsonReader(new StringReader("{\"a\"=1}"))) {
      reader.setStrictness(Strictness.LENIENT);
      reader.beginObject();
      assertEquals("a", reader.nextName());
      assertEquals(1, reader.nextInt());
      reader.endObject();
    }
  }

  @Test
  public void testDoPeek_arrowAsNameValueSeparator_lenient() throws IOException {
    // '=>' instead of ':' in lenient mode
    try (JsonReader reader = new JsonReader(new StringReader("{\"a\"=>1}"))) {
      reader.setStrictness(Strictness.LENIENT);
      reader.beginObject();
      assertEquals("a", reader.nextName());
      assertEquals(1, reader.nextInt());
      reader.endObject();
    }
  }
}
