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
import java.io.StringWriter;
import org.junit.Test;

/**
 * Tests for {@link JsonWriter} static initializer ({@code <clinit>}).
 *
 * <p>The static initializer sets up:
 * <ul>
 *   <li>VALID_JSON_NUMBER_PATTERN - regex for validating JSON numbers</li>
 *   <li>REPLACEMENT_CHARS - escape sequences for control characters</li>
 *   <li>HTML_SAFE_REPLACEMENT_CHARS - additional escapes for HTML safety</li>
 * </ul>
 */
public class JsonWriterClaude_clinitTest {

  // ==================== VALID_JSON_NUMBER_PATTERN Tests (lines 166-167) ====================

  @Test
  public void testValidJsonNumberPattern_validInteger() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    // Custom Number that produces a valid integer string
    Number validNumber = new CustomNumber("42");
    writer.beginArray().value(validNumber).endArray();
    writer.close();

    assertEquals("[42]", stringWriter.toString());
  }

  @Test
  public void testValidJsonNumberPattern_validNegativeInteger() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    Number validNumber = new CustomNumber("-123");
    writer.beginArray().value(validNumber).endArray();
    writer.close();

    assertEquals("[-123]", stringWriter.toString());
  }

  @Test
  public void testValidJsonNumberPattern_validDecimal() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    Number validNumber = new CustomNumber("3.14159");
    writer.beginArray().value(validNumber).endArray();
    writer.close();

    assertEquals("[3.14159]", stringWriter.toString());
  }

  @Test
  public void testValidJsonNumberPattern_validExponent() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    Number validNumber = new CustomNumber("1e10");
    writer.beginArray().value(validNumber).endArray();
    writer.close();

    assertEquals("[1e10]", stringWriter.toString());
  }

  @Test
  public void testValidJsonNumberPattern_validExponentUppercase() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    Number validNumber = new CustomNumber("1E10");
    writer.beginArray().value(validNumber).endArray();
    writer.close();

    assertEquals("[1E10]", stringWriter.toString());
  }

  @Test
  public void testValidJsonNumberPattern_validNegativeExponent() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    Number validNumber = new CustomNumber("5e-3");
    writer.beginArray().value(validNumber).endArray();
    writer.close();

    assertEquals("[5e-3]", stringWriter.toString());
  }

  @Test
  public void testValidJsonNumberPattern_validPositiveExponent() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    Number validNumber = new CustomNumber("5e+3");
    writer.beginArray().value(validNumber).endArray();
    writer.close();

    assertEquals("[5e+3]", stringWriter.toString());
  }

  @Test
  public void testValidJsonNumberPattern_validDecimalWithExponent() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    Number validNumber = new CustomNumber("1.5e10");
    writer.beginArray().value(validNumber).endArray();
    writer.close();

    assertEquals("[1.5e10]", stringWriter.toString());
  }

  @Test
  public void testValidJsonNumberPattern_zero() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    Number validNumber = new CustomNumber("0");
    writer.beginArray().value(validNumber).endArray();
    writer.close();

    assertEquals("[0]", stringWriter.toString());
  }

  @Test
  public void testValidJsonNumberPattern_negativeZero() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    Number validNumber = new CustomNumber("-0");
    writer.beginArray().value(validNumber).endArray();
    writer.close();

    assertEquals("[-0]", stringWriter.toString());
  }

  @Test
  public void testValidJsonNumberPattern_invalidLeadingZero_throwsException() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    Number invalidNumber = new CustomNumber("01");
    writer.beginArray();
    assertThrows(IllegalArgumentException.class, () -> writer.value(invalidNumber));
    writer.endArray();
    writer.close();
  }

  @Test
  public void testValidJsonNumberPattern_invalidFormat_throwsException() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    Number invalidNumber = new CustomNumber("abc");
    writer.beginArray();
    assertThrows(IllegalArgumentException.class, () -> writer.value(invalidNumber));
    writer.endArray();
    writer.close();
  }

  @Test
  public void testValidJsonNumberPattern_invalidPlusPrefix_throwsException() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    Number invalidNumber = new CustomNumber("+5");
    writer.beginArray();
    assertThrows(IllegalArgumentException.class, () -> writer.value(invalidNumber));
    writer.endArray();
    writer.close();
  }

  @Test
  public void testValidJsonNumberPattern_invalidDecimalOnly_throwsException() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    Number invalidNumber = new CustomNumber(".5");
    writer.beginArray();
    assertThrows(IllegalArgumentException.class, () -> writer.value(invalidNumber));
    writer.endArray();
    writer.close();
  }

  @Test
  public void testValidJsonNumberPattern_invalidTrailingDecimal_throwsException()
      throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    Number invalidNumber = new CustomNumber("5.");
    writer.beginArray();
    assertThrows(IllegalArgumentException.class, () -> writer.value(invalidNumber));
    writer.endArray();
    writer.close();
  }

  // ==================== REPLACEMENT_CHARS Tests (lines 183-193) ====================
  // These test the escape sequences for control characters (U+0000 to U+001F)

  @Test
  public void testReplacementChars_nullCharacter() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value("\u0000").endArray();
    writer.close();

    assertEquals("[\"\\u0000\"]", stringWriter.toString());
  }

  @Test
  public void testReplacementChars_controlCharacter_0x01() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value("\u0001").endArray();
    writer.close();

    assertEquals("[\"\\u0001\"]", stringWriter.toString());
  }

  @Test
  public void testReplacementChars_controlCharacter_0x1f() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value("\u001f").endArray();
    writer.close();

    assertEquals("[\"\\u001f\"]", stringWriter.toString());
  }

  @Test
  public void testReplacementChars_allControlCharacters() throws IOException {
    // Test all control characters from 0x00 to 0x1f
    for (int i = 0; i <= 0x1f; i++) {
      StringWriter stringWriter = new StringWriter();
      JsonWriter writer = new JsonWriter(stringWriter);

      String controlChar = String.valueOf((char) i);
      writer.beginArray().value(controlChar).endArray();
      writer.close();

      String output = stringWriter.toString();
      // Should contain escaped version
      assertTrue(
          "Control character 0x" + Integer.toHexString(i) + " should be escaped",
          output.contains("\\"));
    }
  }

  @Test
  public void testReplacementChars_quote() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value("\"").endArray();
    writer.close();

    assertEquals("[\"\\\"\"]", stringWriter.toString());
  }

  @Test
  public void testReplacementChars_backslash() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value("\\").endArray();
    writer.close();

    assertEquals("[\"\\\\\"]", stringWriter.toString());
  }

  @Test
  public void testReplacementChars_tab() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value("\t").endArray();
    writer.close();

    assertEquals("[\"\\t\"]", stringWriter.toString());
  }

  @Test
  public void testReplacementChars_backspace() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value("\b").endArray();
    writer.close();

    assertEquals("[\"\\b\"]", stringWriter.toString());
  }

  @Test
  public void testReplacementChars_newline() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value("\n").endArray();
    writer.close();

    assertEquals("[\"\\n\"]", stringWriter.toString());
  }

  @Test
  public void testReplacementChars_carriageReturn() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value("\r").endArray();
    writer.close();

    assertEquals("[\"\\r\"]", stringWriter.toString());
  }

  @Test
  public void testReplacementChars_formFeed() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value("\f").endArray();
    writer.close();

    assertEquals("[\"\\f\"]", stringWriter.toString());
  }

  @Test
  public void testReplacementChars_multipleSpecialChars() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value("\t\n\r").endArray();
    writer.close();

    assertEquals("[\"\\t\\n\\r\"]", stringWriter.toString());
  }

  // ==================== HTML_SAFE_REPLACEMENT_CHARS Tests (lines 194-199) ====================

  @Test
  public void testHtmlSafeReplacementChars_lessThan() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    writer.setHtmlSafe(true);

    writer.beginArray().value("<").endArray();
    writer.close();

    assertEquals("[\"\\u003c\"]", stringWriter.toString());
  }

  @Test
  public void testHtmlSafeReplacementChars_greaterThan() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    writer.setHtmlSafe(true);

    writer.beginArray().value(">").endArray();
    writer.close();

    assertEquals("[\"\\u003e\"]", stringWriter.toString());
  }

  @Test
  public void testHtmlSafeReplacementChars_ampersand() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    writer.setHtmlSafe(true);

    writer.beginArray().value("&").endArray();
    writer.close();

    assertEquals("[\"\\u0026\"]", stringWriter.toString());
  }

  @Test
  public void testHtmlSafeReplacementChars_equals() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    writer.setHtmlSafe(true);

    writer.beginArray().value("=").endArray();
    writer.close();

    assertEquals("[\"\\u003d\"]", stringWriter.toString());
  }

  @Test
  public void testHtmlSafeReplacementChars_singleQuote() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    writer.setHtmlSafe(true);

    writer.beginArray().value("'").endArray();
    writer.close();

    assertEquals("[\"\\u0027\"]", stringWriter.toString());
  }

  @Test
  public void testHtmlSafeReplacementChars_allHtmlChars() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    writer.setHtmlSafe(true);

    writer.beginArray().value("<>&='").endArray();
    writer.close();

    assertEquals("[\"\\u003c\\u003e\\u0026\\u003d\\u0027\"]", stringWriter.toString());
  }

  @Test
  public void testHtmlSafeReplacementChars_htmlTag() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    writer.setHtmlSafe(true);

    writer.beginArray().value("<script>alert('xss')</script>").endArray();
    writer.close();

    assertEquals(
        "[\"\\u003cscript\\u003ealert(\\u0027xss\\u0027)\\u003c/script\\u003e\"]",
        stringWriter.toString());
  }

  @Test
  public void testHtmlSafeReplacementChars_withControlChars() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    writer.setHtmlSafe(true);

    // Combines HTML escaping with control character escaping
    writer.beginArray().value("<\t>").endArray();
    writer.close();

    assertEquals("[\"\\u003c\\t\\u003e\"]", stringWriter.toString());
  }

  @Test
  public void testHtmlSafeReplacementChars_notHtmlSafe_doesNotEscape() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    writer.setHtmlSafe(false);

    writer.beginArray().value("<>&='").endArray();
    writer.close();

    assertEquals("[\"<>&='\"]", stringWriter.toString());
  }

  @Test
  public void testReplacementCharsClone_htmlSafeInheritsControlCharEscaping() throws IOException {
    // This tests that HTML_SAFE_REPLACEMENT_CHARS is properly cloned from REPLACEMENT_CHARS
    // and still has all control character escapes
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    writer.setHtmlSafe(true);

    writer.beginArray().value("\u0000\t\n<>").endArray();
    writer.close();

    assertEquals("[\"\\u0000\\t\\n\\u003c\\u003e\"]", stringWriter.toString());
  }

  // ==================== Combined Tests ====================

  @Test
  public void testStaticInitialization_createsWorkingWriter() throws IOException {
    // Simple test to ensure static initialization completes successfully
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginObject().name("test").value("value").endObject();
    writer.close();

    assertEquals("{\"test\":\"value\"}", stringWriter.toString());
  }

  @Test
  public void testStaticInitialization_patternAndCharsWorkTogether() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    writer.setHtmlSafe(true);

    // Test that uses both pattern validation (via Number) and char replacement
    writer.beginObject();
    writer.name("num").value(new CustomNumber("123"));
    writer.name("str").value("<script>");
    writer.name("ctrl").value("\t\n");
    writer.endObject();
    writer.close();

    assertEquals(
        "{\"num\":123,\"str\":\"\\u003cscript\\u003e\",\"ctrl\":\"\\t\\n\"}",
        stringWriter.toString());
  }

  // ==================== Unicode Line Separator Tests ====================
  // These are handled in the string() method, not static init, but verify escaping works

  @Test
  public void testUnicodeLineSeparator() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value("\u2028").endArray();
    writer.close();

    assertEquals("[\"\\u2028\"]", stringWriter.toString());
  }

  @Test
  public void testUnicodeParagraphSeparator() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value("\u2029").endArray();
    writer.close();

    assertEquals("[\"\\u2029\"]", stringWriter.toString());
  }

  // ==================== Helper Class ====================

  /** Custom Number class for testing VALID_JSON_NUMBER_PATTERN validation. */
  private static class CustomNumber extends Number {
    private static final long serialVersionUID = 1L;
    private final String value;

    CustomNumber(String value) {
      this.value = value;
    }

    @Override
    public int intValue() {
      return 0;
    }

    @Override
    public long longValue() {
      return 0;
    }

    @Override
    public float floatValue() {
      return 0;
    }

    @Override
    public double doubleValue() {
      return 0;
    }

    @Override
    public String toString() {
      return value;
    }
  }
}
