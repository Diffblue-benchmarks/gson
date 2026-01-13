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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import com.google.gson.FormattingStyle;
import com.google.gson.Strictness;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.Test;

/** Test class for {@link JsonWriter}. */
public class JsonWriterClaudeTest {

  // ==================== Constructor Tests ====================

  @Test
  public void testConstructor_withValidWriter() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    assertNotNull(writer);
    // Verify default state by checking getters
    assertEquals(FormattingStyle.COMPACT, writer.getFormattingStyle());
    assertEquals(Strictness.LEGACY_STRICT, writer.getStrictness());
    assertFalse(writer.isHtmlSafe());
    assertTrue(writer.getSerializeNulls());
    assertFalse(writer.isLenient());

    writer.beginArray().endArray();
    writer.close();
  }

  @Test
  public void testConstructor_withNullWriter_throwsNullPointerException() {
    assertThrows(NullPointerException.class, () -> new JsonWriter(null));
  }

  // ==================== setIndent Tests ====================

  @Test
  public void testSetIndent_emptyString_setsCompactFormatting() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.setIndent("");

    assertEquals(FormattingStyle.COMPACT, writer.getFormattingStyle());
    writer.beginArray().value(1).value(2).endArray();
    writer.close();
    assertEquals("[1,2]", stringWriter.toString());
  }

  @Test
  public void testSetIndent_twoSpaces_setsPrettyFormatting() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.setIndent("  ");

    assertEquals("  ", writer.getFormattingStyle().getIndent());
    assertEquals("\n", writer.getFormattingStyle().getNewline());
    writer.beginArray().value(1).value(2).endArray();
    writer.close();
    assertEquals("[\n  1,\n  2\n]", stringWriter.toString());
  }

  @Test
  public void testSetIndent_fourSpaces_setsFourSpaceIndent() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.setIndent("    ");

    assertEquals("    ", writer.getFormattingStyle().getIndent());
    writer.beginArray().value(1).endArray();
    writer.close();
    assertEquals("[\n    1\n]", stringWriter.toString());
  }

  @Test
  public void testSetIndent_tab_setsTabIndent() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.setIndent("\t");

    assertEquals("\t", writer.getFormattingStyle().getIndent());
    writer.beginArray().value(1).endArray();
    writer.close();
    assertEquals("[\n\t1\n]", stringWriter.toString());
  }

  // ==================== setFormattingStyle / getFormattingStyle Tests ====================

  @Test
  public void testSetFormattingStyle_compact() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.setFormattingStyle(FormattingStyle.COMPACT);

    assertSame(FormattingStyle.COMPACT, writer.getFormattingStyle());
    writer.beginObject().name("key").value("value").endObject();
    writer.close();
    assertEquals("{\"key\":\"value\"}", stringWriter.toString());
  }

  @Test
  public void testSetFormattingStyle_pretty() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.setFormattingStyle(FormattingStyle.PRETTY);

    assertSame(FormattingStyle.PRETTY, writer.getFormattingStyle());
    writer.beginObject().name("key").value("value").endObject();
    writer.close();
    assertEquals("{\n  \"key\": \"value\"\n}", stringWriter.toString());
  }

  @Test
  public void testSetFormattingStyle_customStyle() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    FormattingStyle customStyle =
        FormattingStyle.PRETTY.withIndent("\t").withNewline("\r\n").withSpaceAfterSeparators(false);
    writer.setFormattingStyle(customStyle);

    assertEquals(customStyle, writer.getFormattingStyle());
    assertEquals("\t", writer.getFormattingStyle().getIndent());
    assertEquals("\r\n", writer.getFormattingStyle().getNewline());
    writer.beginObject().name("key").value("value").endObject();
    writer.close();
    assertEquals("{\r\n\t\"key\":\"value\"\r\n}", stringWriter.toString());
  }

  @Test
  public void testSetFormattingStyle_nullStyle_throwsNullPointerException() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    assertThrows(NullPointerException.class, () -> writer.setFormattingStyle(null));
    writer.beginArray().endArray();
    writer.close();
  }

  @Test
  public void testSetFormattingStyle_spaceAfterSeparatorsNoNewline() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    // Create a compact style but with space after separators
    FormattingStyle compactWithSpaces =
        FormattingStyle.COMPACT.withSpaceAfterSeparators(true);
    writer.setFormattingStyle(compactWithSpaces);

    writer.beginArray().value(1).value(2).endArray();
    writer.close();
    // With spaceAfterSeparators but no newline, comma should have space after it
    assertEquals("[1, 2]", stringWriter.toString());
  }

  // ==================== setLenient / isLenient Tests ====================

  @Test
  public void testSetLenient_true_setsLenientStrictness() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.setLenient(true);

    assertTrue(writer.isLenient());
    assertEquals(Strictness.LENIENT, writer.getStrictness());
    writer.beginArray().endArray();
    writer.close();
  }

  @Test
  public void testSetLenient_false_setsLegacyStrictStrictness() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.setLenient(false);

    assertFalse(writer.isLenient());
    assertEquals(Strictness.LEGACY_STRICT, writer.getStrictness());
    writer.beginArray().endArray();
    writer.close();
  }

  @Test
  public void testIsLenient_defaultIsFalse() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    assertFalse(writer.isLenient());
    writer.beginArray().endArray();
    writer.close();
  }

  // ==================== setStrictness / getStrictness Tests ====================

  @Test
  public void testSetStrictness_lenient() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.setStrictness(Strictness.LENIENT);

    assertEquals(Strictness.LENIENT, writer.getStrictness());
    assertTrue(writer.isLenient());
    writer.beginArray().endArray();
    writer.close();
  }

  @Test
  public void testSetStrictness_legacyStrict() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.setStrictness(Strictness.LEGACY_STRICT);

    assertEquals(Strictness.LEGACY_STRICT, writer.getStrictness());
    assertFalse(writer.isLenient());
    writer.beginArray().endArray();
    writer.close();
  }

  @Test
  public void testSetStrictness_strict() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.setStrictness(Strictness.STRICT);

    assertEquals(Strictness.STRICT, writer.getStrictness());
    assertFalse(writer.isLenient());
    writer.beginArray().endArray();
    writer.close();
  }

  @Test
  public void testSetStrictness_null_throwsNullPointerException() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    assertThrows(NullPointerException.class, () -> writer.setStrictness(null));
    writer.beginArray().endArray();
    writer.close();
  }

  @Test
  public void testGetStrictness_defaultIsLegacyStrict() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    assertEquals(Strictness.LEGACY_STRICT, writer.getStrictness());
    writer.beginArray().endArray();
    writer.close();
  }

  // ==================== setHtmlSafe / isHtmlSafe Tests ====================

  @Test
  public void testSetHtmlSafe_true_escapesHtmlCharacters() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.setHtmlSafe(true);

    assertTrue(writer.isHtmlSafe());
    writer.beginArray().value("<script>alert('xss')</script>").endArray();
    writer.close();
    assertEquals("[\"\\u003cscript\\u003ealert(\\u0027xss\\u0027)\\u003c/script\\u003e\"]",
        stringWriter.toString());
  }

  @Test
  public void testSetHtmlSafe_false_doesNotEscapeHtmlCharacters() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.setHtmlSafe(false);

    assertFalse(writer.isHtmlSafe());
    writer.beginArray().value("<>&='").endArray();
    writer.close();
    assertEquals("[\"<>&='\"]", stringWriter.toString());
  }

  @Test
  public void testIsHtmlSafe_defaultIsFalse() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    assertFalse(writer.isHtmlSafe());
    writer.beginArray().endArray();
    writer.close();
  }

  @Test
  public void testSetHtmlSafe_escapesLessThan() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.setHtmlSafe(true);
    writer.beginArray().value("<").endArray();
    writer.close();

    assertEquals("[\"\\u003c\"]", stringWriter.toString());
  }

  @Test
  public void testSetHtmlSafe_escapesGreaterThan() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.setHtmlSafe(true);
    writer.beginArray().value(">").endArray();
    writer.close();

    assertEquals("[\"\\u003e\"]", stringWriter.toString());
  }

  @Test
  public void testSetHtmlSafe_escapesAmpersand() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.setHtmlSafe(true);
    writer.beginArray().value("&").endArray();
    writer.close();

    assertEquals("[\"\\u0026\"]", stringWriter.toString());
  }

  @Test
  public void testSetHtmlSafe_escapesEquals() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.setHtmlSafe(true);
    writer.beginArray().value("=").endArray();
    writer.close();

    assertEquals("[\"\\u003d\"]", stringWriter.toString());
  }

  @Test
  public void testSetHtmlSafe_escapesSingleQuote() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.setHtmlSafe(true);
    writer.beginArray().value("'").endArray();
    writer.close();

    assertEquals("[\"\\u0027\"]", stringWriter.toString());
  }

  // ==================== setSerializeNulls / getSerializeNulls Tests ====================

  @Test
  public void testSetSerializeNulls_true_serializesNullObjectMembers() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.setSerializeNulls(true);

    assertTrue(writer.getSerializeNulls());
    writer.beginObject().name("key").nullValue().endObject();
    writer.close();
    assertEquals("{\"key\":null}", stringWriter.toString());
  }

  @Test
  public void testSetSerializeNulls_false_skipsNullObjectMembers() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.setSerializeNulls(false);

    assertFalse(writer.getSerializeNulls());
    writer.beginObject().name("key").nullValue().endObject();
    writer.close();
    assertEquals("{}", stringWriter.toString());
  }

  @Test
  public void testSetSerializeNulls_false_stillSerializesArrayNulls() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.setSerializeNulls(false);
    writer.beginArray().nullValue().value(1).nullValue().endArray();
    writer.close();

    assertEquals("[null,1,null]", stringWriter.toString());
  }

  @Test
  public void testGetSerializeNulls_defaultIsTrue() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    assertTrue(writer.getSerializeNulls());
    writer.beginArray().endArray();
    writer.close();
  }

  // ==================== beginArray / endArray Tests ====================

  @Test
  public void testBeginArray_endArray_emptyArray() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    JsonWriter returnValue = writer.beginArray();
    assertSame(writer, returnValue);
    returnValue = writer.endArray();
    assertSame(writer, returnValue);

    writer.close();
    assertEquals("[]", stringWriter.toString());
  }

  @Test
  public void testBeginArray_endArray_withValues() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value(1).value(2).value(3).endArray();
    writer.close();

    assertEquals("[1,2,3]", stringWriter.toString());
  }

  @Test
  public void testBeginArray_nestedArrays() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().beginArray().value(1).endArray().beginArray().value(2).endArray().endArray();
    writer.close();

    assertEquals("[[1],[2]]", stringWriter.toString());
  }

  @Test
  public void testEndArray_withoutBeginArray_throwsIllegalStateException() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    assertThrows(IllegalStateException.class, () -> writer.endArray());
    writer.beginArray().endArray();
    writer.close();
  }

  @Test
  public void testEndArray_onObject_throwsIllegalStateException() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginObject();
    assertThrows(IllegalStateException.class, () -> writer.endArray());
    writer.endObject();
    writer.close();
  }

  // ==================== beginObject / endObject Tests ====================

  @Test
  public void testBeginObject_endObject_emptyObject() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    JsonWriter returnValue = writer.beginObject();
    assertSame(writer, returnValue);
    returnValue = writer.endObject();
    assertSame(writer, returnValue);

    writer.close();
    assertEquals("{}", stringWriter.toString());
  }

  @Test
  public void testBeginObject_endObject_withMembers() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginObject().name("a").value(1).name("b").value(2).endObject();
    writer.close();

    assertEquals("{\"a\":1,\"b\":2}", stringWriter.toString());
  }

  @Test
  public void testBeginObject_nestedObjects() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginObject()
        .name("outer")
        .beginObject()
        .name("inner")
        .value("value")
        .endObject()
        .endObject();
    writer.close();

    assertEquals("{\"outer\":{\"inner\":\"value\"}}", stringWriter.toString());
  }

  @Test
  public void testEndObject_withoutBeginObject_throwsIllegalStateException() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    assertThrows(IllegalStateException.class, () -> writer.endObject());
    writer.beginObject().endObject();
    writer.close();
  }

  @Test
  public void testEndObject_onArray_throwsIllegalStateException() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray();
    assertThrows(IllegalStateException.class, () -> writer.endObject());
    writer.endArray();
    writer.close();
  }

  @Test
  public void testEndObject_withDanglingName_throwsIllegalStateException() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginObject().name("key");
    assertThrows(IllegalStateException.class, () -> writer.endObject());
    writer.value("value").endObject();
    writer.close();
  }

  // ==================== name Tests ====================

  @Test
  public void testName_returnsThis() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginObject();
    JsonWriter returnValue = writer.name("key");
    assertSame(writer, returnValue);

    writer.value("value").endObject();
    writer.close();
  }

  @Test
  public void testName_withNullName_throwsNullPointerException() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginObject();
    assertThrows(NullPointerException.class, () -> writer.name(null));
    writer.endObject();
    writer.close();
  }

  @Test
  public void testName_outsideObject_throwsIllegalStateException() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    assertThrows(IllegalStateException.class, () -> writer.name("key"));
    writer.beginArray().endArray();
    writer.close();
  }

  @Test
  public void testName_inArray_throwsIllegalStateException() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray();
    assertThrows(IllegalStateException.class, () -> writer.name("key"));
    writer.endArray();
    writer.close();
  }

  @Test
  public void testName_calledTwiceWithoutValue_throwsIllegalStateException() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginObject().name("first");
    assertThrows(IllegalStateException.class, () -> writer.name("second"));
    writer.value("value").endObject();
    writer.close();
  }

  @Test
  public void testName_withSpecialCharacters() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginObject().name("key\"with\nspecial").value(1).endObject();
    writer.close();

    assertEquals("{\"key\\\"with\\nspecial\":1}", stringWriter.toString());
  }

  // ==================== value(String) Tests ====================

  @Test
  public void testValueString_returnsThis() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray();
    JsonWriter returnValue = writer.value("test");
    assertSame(writer, returnValue);

    writer.endArray();
    writer.close();
  }

  @Test
  public void testValueString_simpleString() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value("hello").endArray();
    writer.close();

    assertEquals("[\"hello\"]", stringWriter.toString());
  }

  @Test
  public void testValueString_nullValue() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value((String) null).endArray();
    writer.close();

    assertEquals("[null]", stringWriter.toString());
  }

  @Test
  public void testValueString_escapesQuotes() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value("hello \"world\"").endArray();
    writer.close();

    assertEquals("[\"hello \\\"world\\\"\"]", stringWriter.toString());
  }

  @Test
  public void testValueString_escapesBackslash() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value("path\\to\\file").endArray();
    writer.close();

    assertEquals("[\"path\\\\to\\\\file\"]", stringWriter.toString());
  }

  @Test
  public void testValueString_escapesNewline() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value("line1\nline2").endArray();
    writer.close();

    assertEquals("[\"line1\\nline2\"]", stringWriter.toString());
  }

  @Test
  public void testValueString_escapesTab() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value("a\tb").endArray();
    writer.close();

    assertEquals("[\"a\\tb\"]", stringWriter.toString());
  }

  @Test
  public void testValueString_escapesCarriageReturn() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value("a\rb").endArray();
    writer.close();

    assertEquals("[\"a\\rb\"]", stringWriter.toString());
  }

  @Test
  public void testValueString_escapesFormFeed() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value("a\fb").endArray();
    writer.close();

    assertEquals("[\"a\\fb\"]", stringWriter.toString());
  }

  @Test
  public void testValueString_escapesBackspace() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value("a\bb").endArray();
    writer.close();

    assertEquals("[\"a\\bb\"]", stringWriter.toString());
  }

  @Test
  public void testValueString_escapesControlCharacters() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value("\u0000\u001f").endArray();
    writer.close();

    assertEquals("[\"\\u0000\\u001f\"]", stringWriter.toString());
  }

  @Test
  public void testValueString_escapesUnicodeLineSeparators() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value("\u2028\u2029").endArray();
    writer.close();

    assertEquals("[\"\\u2028\\u2029\"]", stringWriter.toString());
  }

  @Test
  public void testValueString_emptyString() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value("").endArray();
    writer.close();

    assertEquals("[\"\"]", stringWriter.toString());
  }

  // ==================== value(boolean) Tests ====================

  @Test
  public void testValueBoolean_true() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value(true).endArray();
    writer.close();

    assertEquals("[true]", stringWriter.toString());
  }

  @Test
  public void testValueBoolean_false() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value(false).endArray();
    writer.close();

    assertEquals("[false]", stringWriter.toString());
  }

  @Test
  public void testValueBoolean_returnsThis() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray();
    JsonWriter returnValue = writer.value(true);
    assertSame(writer, returnValue);

    writer.endArray();
    writer.close();
  }

  // ==================== value(Boolean) Tests ====================

  @Test
  public void testValueBooleanObject_true() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value(Boolean.TRUE).endArray();
    writer.close();

    assertEquals("[true]", stringWriter.toString());
  }

  @Test
  public void testValueBooleanObject_false() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value(Boolean.FALSE).endArray();
    writer.close();

    assertEquals("[false]", stringWriter.toString());
  }

  @Test
  public void testValueBooleanObject_null() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value((Boolean) null).endArray();
    writer.close();

    assertEquals("[null]", stringWriter.toString());
  }

  @Test
  public void testValueBooleanObject_returnsThis() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray();
    JsonWriter returnValue = writer.value(Boolean.TRUE);
    assertSame(writer, returnValue);

    writer.endArray();
    writer.close();
  }

  // ==================== value(float) Tests ====================

  @Test
  public void testValueFloat_positiveValue() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value(3.14f).endArray();
    writer.close();

    assertEquals("[3.14]", stringWriter.toString());
  }

  @Test
  public void testValueFloat_negativeValue() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value(-2.5f).endArray();
    writer.close();

    assertEquals("[-2.5]", stringWriter.toString());
  }

  @Test
  public void testValueFloat_zero() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value(0.0f).endArray();
    writer.close();

    assertEquals("[0.0]", stringWriter.toString());
  }

  @Test
  public void testValueFloat_nan_throwsIllegalArgumentException() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray();
    assertThrows(IllegalArgumentException.class, () -> writer.value(Float.NaN));
    writer.endArray();
    writer.close();
  }

  @Test
  public void testValueFloat_positiveInfinity_throwsIllegalArgumentException() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray();
    assertThrows(IllegalArgumentException.class, () -> writer.value(Float.POSITIVE_INFINITY));
    writer.endArray();
    writer.close();
  }

  @Test
  public void testValueFloat_negativeInfinity_throwsIllegalArgumentException() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray();
    assertThrows(IllegalArgumentException.class, () -> writer.value(Float.NEGATIVE_INFINITY));
    writer.endArray();
    writer.close();
  }

  @Test
  public void testValueFloat_nan_lenient_writesNaN() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    writer.setStrictness(Strictness.LENIENT);

    writer.beginArray().value(Float.NaN).endArray();
    writer.close();

    assertEquals("[NaN]", stringWriter.toString());
  }

  @Test
  public void testValueFloat_infinity_lenient_writesInfinity() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    writer.setStrictness(Strictness.LENIENT);

    writer.beginArray().value(Float.POSITIVE_INFINITY).value(Float.NEGATIVE_INFINITY).endArray();
    writer.close();

    assertEquals("[Infinity,-Infinity]", stringWriter.toString());
  }

  @Test
  public void testValueFloat_returnsThis() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray();
    JsonWriter returnValue = writer.value(1.0f);
    assertSame(writer, returnValue);

    writer.endArray();
    writer.close();
  }

  // ==================== value(double) Tests ====================

  @Test
  public void testValueDouble_positiveValue() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value(3.14159).endArray();
    writer.close();

    assertEquals("[3.14159]", stringWriter.toString());
  }

  @Test
  public void testValueDouble_negativeValue() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value(-2.71828).endArray();
    writer.close();

    assertEquals("[-2.71828]", stringWriter.toString());
  }

  @Test
  public void testValueDouble_zero() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value(0.0).endArray();
    writer.close();

    assertEquals("[0.0]", stringWriter.toString());
  }

  @Test
  public void testValueDouble_nan_throwsIllegalArgumentException() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray();
    assertThrows(IllegalArgumentException.class, () -> writer.value(Double.NaN));
    writer.endArray();
    writer.close();
  }

  @Test
  public void testValueDouble_positiveInfinity_throwsIllegalArgumentException() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray();
    assertThrows(IllegalArgumentException.class, () -> writer.value(Double.POSITIVE_INFINITY));
    writer.endArray();
    writer.close();
  }

  @Test
  public void testValueDouble_negativeInfinity_throwsIllegalArgumentException() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray();
    assertThrows(IllegalArgumentException.class, () -> writer.value(Double.NEGATIVE_INFINITY));
    writer.endArray();
    writer.close();
  }

  @Test
  public void testValueDouble_nan_lenient_writesNaN() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    writer.setStrictness(Strictness.LENIENT);

    writer.beginArray().value(Double.NaN).endArray();
    writer.close();

    assertEquals("[NaN]", stringWriter.toString());
  }

  @Test
  public void testValueDouble_infinity_lenient_writesInfinity() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    writer.setStrictness(Strictness.LENIENT);

    writer.beginArray().value(Double.POSITIVE_INFINITY).value(Double.NEGATIVE_INFINITY).endArray();
    writer.close();

    assertEquals("[Infinity,-Infinity]", stringWriter.toString());
  }

  @Test
  public void testValueDouble_returnsThis() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray();
    JsonWriter returnValue = writer.value(1.0);
    assertSame(writer, returnValue);

    writer.endArray();
    writer.close();
  }

  // ==================== value(long) Tests ====================

  @Test
  public void testValueLong_positiveValue() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value(123456789012345L).endArray();
    writer.close();

    assertEquals("[123456789012345]", stringWriter.toString());
  }

  @Test
  public void testValueLong_negativeValue() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value(-987654321098765L).endArray();
    writer.close();

    assertEquals("[-987654321098765]", stringWriter.toString());
  }

  @Test
  public void testValueLong_zero() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value(0L).endArray();
    writer.close();

    assertEquals("[0]", stringWriter.toString());
  }

  @Test
  public void testValueLong_maxValue() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value(Long.MAX_VALUE).endArray();
    writer.close();

    assertEquals("[" + Long.MAX_VALUE + "]", stringWriter.toString());
  }

  @Test
  public void testValueLong_minValue() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value(Long.MIN_VALUE).endArray();
    writer.close();

    assertEquals("[" + Long.MIN_VALUE + "]", stringWriter.toString());
  }

  @Test
  public void testValueLong_returnsThis() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray();
    JsonWriter returnValue = writer.value(123L);
    assertSame(writer, returnValue);

    writer.endArray();
    writer.close();
  }

  // ==================== value(Number) Tests ====================

  @Test
  public void testValueNumber_integer() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value(Integer.valueOf(42)).endArray();
    writer.close();

    assertEquals("[42]", stringWriter.toString());
  }

  @Test
  public void testValueNumber_long() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value(Long.valueOf(9999999999L)).endArray();
    writer.close();

    assertEquals("[9999999999]", stringWriter.toString());
  }

  @Test
  public void testValueNumber_byte() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value(Byte.valueOf((byte) 127)).endArray();
    writer.close();

    assertEquals("[127]", stringWriter.toString());
  }

  @Test
  public void testValueNumber_short() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value(Short.valueOf((short) 32767)).endArray();
    writer.close();

    assertEquals("[32767]", stringWriter.toString());
  }

  @Test
  public void testValueNumber_bigDecimal() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value(new BigDecimal("123456789.123456789")).endArray();
    writer.close();

    assertEquals("[123456789.123456789]", stringWriter.toString());
  }

  @Test
  public void testValueNumber_bigInteger() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value(new BigInteger("123456789012345678901234567890")).endArray();
    writer.close();

    assertEquals("[123456789012345678901234567890]", stringWriter.toString());
  }

  @Test
  public void testValueNumber_atomicInteger() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value(new AtomicInteger(42)).endArray();
    writer.close();

    assertEquals("[42]", stringWriter.toString());
  }

  @Test
  public void testValueNumber_atomicLong() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value(new AtomicLong(9999999999L)).endArray();
    writer.close();

    assertEquals("[9999999999]", stringWriter.toString());
  }

  @Test
  public void testValueNumber_null() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value((Number) null).endArray();
    writer.close();

    assertEquals("[null]", stringWriter.toString());
  }

  @Test
  public void testValueNumber_double() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value(Double.valueOf(3.14)).endArray();
    writer.close();

    assertEquals("[3.14]", stringWriter.toString());
  }

  @Test
  public void testValueNumber_float() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value(Float.valueOf(2.5f)).endArray();
    writer.close();

    assertEquals("[2.5]", stringWriter.toString());
  }

  @Test
  public void testValueNumber_doubleNaN_throwsIllegalArgumentException() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray();
    assertThrows(IllegalArgumentException.class, () -> writer.value(Double.valueOf(Double.NaN)));
    writer.endArray();
    writer.close();
  }

  @Test
  public void testValueNumber_doubleInfinity_throwsIllegalArgumentException() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray();
    assertThrows(
        IllegalArgumentException.class,
        () -> writer.value(Double.valueOf(Double.POSITIVE_INFINITY)));
    writer.endArray();
    writer.close();
  }

  @Test
  public void testValueNumber_floatNaN_throwsIllegalArgumentException() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray();
    assertThrows(IllegalArgumentException.class, () -> writer.value(Float.valueOf(Float.NaN)));
    writer.endArray();
    writer.close();
  }

  @Test
  public void testValueNumber_floatInfinity_throwsIllegalArgumentException() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray();
    assertThrows(
        IllegalArgumentException.class,
        () -> writer.value(Float.valueOf(Float.POSITIVE_INFINITY)));
    writer.endArray();
    writer.close();
  }

  @Test
  public void testValueNumber_invalidNumberFormat_throwsIllegalArgumentException()
      throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    // Create a custom Number that produces invalid JSON
    Number invalidNumber =
        new Number() {
          private static final long serialVersionUID = 1L;

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
            return "invalid_number";
          }
        };

    writer.beginArray();
    assertThrows(IllegalArgumentException.class, () -> writer.value(invalidNumber));
    writer.endArray();
    writer.close();
  }

  @Test
  public void testValueNumber_lenient_allowsNaN() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    writer.setStrictness(Strictness.LENIENT);

    writer.beginArray().value(Double.valueOf(Double.NaN)).endArray();
    writer.close();

    assertEquals("[NaN]", stringWriter.toString());
  }

  @Test
  public void testValueNumber_lenient_allowsInfinity() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    writer.setStrictness(Strictness.LENIENT);

    writer
        .beginArray()
        .value(Double.valueOf(Double.POSITIVE_INFINITY))
        .value(Double.valueOf(Double.NEGATIVE_INFINITY))
        .endArray();
    writer.close();

    assertEquals("[Infinity,-Infinity]", stringWriter.toString());
  }

  @Test
  public void testValueNumber_returnsThis() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray();
    JsonWriter returnValue = writer.value(Integer.valueOf(123));
    assertSame(writer, returnValue);

    writer.endArray();
    writer.close();
  }

  // ==================== nullValue Tests ====================

  @Test
  public void testNullValue_inArray() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().nullValue().endArray();
    writer.close();

    assertEquals("[null]", stringWriter.toString());
  }

  @Test
  public void testNullValue_inObject_serializeNullsTrue() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    writer.setSerializeNulls(true);

    writer.beginObject().name("key").nullValue().endObject();
    writer.close();

    assertEquals("{\"key\":null}", stringWriter.toString());
  }

  @Test
  public void testNullValue_inObject_serializeNullsFalse() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    writer.setSerializeNulls(false);

    writer.beginObject().name("key").nullValue().endObject();
    writer.close();

    assertEquals("{}", stringWriter.toString());
  }

  @Test
  public void testNullValue_inObject_serializeNullsFalse_otherMembersStillWritten()
      throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    writer.setSerializeNulls(false);

    writer.beginObject().name("a").nullValue().name("b").value(1).endObject();
    writer.close();

    assertEquals("{\"b\":1}", stringWriter.toString());
  }

  @Test
  public void testNullValue_returnsThis() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray();
    JsonWriter returnValue = writer.nullValue();
    assertSame(writer, returnValue);

    writer.endArray();
    writer.close();
  }

  // ==================== jsonValue Tests ====================

  @Test
  public void testJsonValue_writesRawJson() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().jsonValue("{\"raw\":true}").endArray();
    writer.close();

    assertEquals("[{\"raw\":true}]", stringWriter.toString());
  }

  @Test
  public void testJsonValue_nullValue_writesNull() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().jsonValue(null).endArray();
    writer.close();

    assertEquals("[null]", stringWriter.toString());
  }

  @Test
  public void testJsonValue_inObject() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginObject().name("key").jsonValue("[1,2,3]").endObject();
    writer.close();

    assertEquals("{\"key\":[1,2,3]}", stringWriter.toString());
  }

  @Test
  public void testJsonValue_returnsThis() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray();
    JsonWriter returnValue = writer.jsonValue("true");
    assertSame(writer, returnValue);

    writer.endArray();
    writer.close();
  }

  @Test
  public void testJsonValue_arbitraryContent() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    // jsonValue allows any content, even invalid JSON
    writer.beginArray().jsonValue("any content").endArray();
    writer.close();

    assertEquals("[any content]", stringWriter.toString());
  }

  // ==================== flush Tests ====================

  @Test
  public void testFlush_flushesUnderlyingWriter() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value(1);
    writer.flush();

    // Content should be in the writer after flush
    assertTrue(stringWriter.toString().contains("[1"));
    writer.endArray();
    writer.close();
  }

  @Test
  public void testFlush_afterClose_throwsIllegalStateException() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().endArray();
    writer.close();

    assertThrows(IllegalStateException.class, () -> writer.flush());
  }

  // ==================== close Tests ====================

  @Test
  public void testClose_completeDocument_succeeds() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().endArray();
    writer.close();

    assertEquals("[]", stringWriter.toString());
  }

  @Test
  public void testClose_emptyDocument_throwsIOException() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    assertThrows(IOException.class, () -> writer.close());
  }

  @Test
  public void testClose_incompleteArray_throwsIOException() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray();
    assertThrows(IOException.class, () -> writer.close());
  }

  @Test
  public void testClose_incompleteObject_throwsIOException() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginObject();
    assertThrows(IOException.class, () -> writer.close());
  }

  @Test
  public void testClose_nestedIncomplete_throwsIOException() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().beginObject();
    assertThrows(IOException.class, () -> writer.close());
  }

  // ==================== Multiple Top-Level Values Tests ====================

  @Test
  public void testMultipleTopLevelValues_notLenient_throwsIllegalStateException()
      throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().endArray();
    assertThrows(IllegalStateException.class, () -> writer.value(1));
    writer.close();
  }

  @Test
  public void testMultipleTopLevelValues_lenient_succeeds() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    writer.setStrictness(Strictness.LENIENT);

    writer.beginArray().endArray();
    writer.value(42);
    writer.close();

    assertEquals("[]42", stringWriter.toString());
  }

  // ==================== Complex Structure Tests ====================

  @Test
  public void testComplexStructure_nestedObjectsAndArrays() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer
        .beginObject()
        .name("name")
        .value("John")
        .name("age")
        .value(30)
        .name("active")
        .value(true)
        .name("score")
        .value(95.5)
        .name("tags")
        .beginArray()
        .value("a")
        .value("b")
        .endArray()
        .name("metadata")
        .beginObject()
        .name("created")
        .value(1234567890L)
        .endObject()
        .endObject();
    writer.close();

    assertEquals(
        "{\"name\":\"John\",\"age\":30,\"active\":true,\"score\":95.5,\"tags\":[\"a\",\"b\"],\"metadata\":{\"created\":1234567890}}",
        stringWriter.toString());
  }

  @Test
  public void testComplexStructure_prettyPrinted() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    writer.setFormattingStyle(FormattingStyle.PRETTY);

    writer.beginObject().name("array").beginArray().value(1).value(2).endArray().endObject();
    writer.close();

    String expected = "{\n  \"array\": [\n    1,\n    2\n  ]\n}";
    assertEquals(expected, stringWriter.toString());
  }

  @Test
  public void testDeeplyNestedArrays() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().beginArray().beginArray().value(1).endArray().endArray().endArray();
    writer.close();

    assertEquals("[[[1]]]", stringWriter.toString());
  }

  @Test
  public void testDeeplyNestedObjects() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer
        .beginObject()
        .name("a")
        .beginObject()
        .name("b")
        .beginObject()
        .name("c")
        .value(1)
        .endObject()
        .endObject()
        .endObject();
    writer.close();

    assertEquals("{\"a\":{\"b\":{\"c\":1}}}", stringWriter.toString());
  }

  // ==================== Object Member with Deferred Name Tests ====================

  @Test
  public void testObjectMember_valueWritesDeferredName() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginObject().name("key").value("value").endObject();
    writer.close();

    assertEquals("{\"key\":\"value\"}", stringWriter.toString());
  }

  @Test
  public void testObjectMember_beginArrayWritesDeferredName() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginObject().name("array").beginArray().endArray().endObject();
    writer.close();

    assertEquals("{\"array\":[]}", stringWriter.toString());
  }

  @Test
  public void testObjectMember_beginObjectWritesDeferredName() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginObject().name("nested").beginObject().endObject().endObject();
    writer.close();

    assertEquals("{\"nested\":{}}", stringWriter.toString());
  }

  // ==================== Chaining Tests ====================

  @Test
  public void testMethodChaining_returnsCorrectInstance() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    JsonWriter result =
        writer
            .beginObject()
            .name("a")
            .value("b")
            .name("c")
            .value(1)
            .name("d")
            .value(true)
            .name("e")
            .nullValue()
            .name("f")
            .beginArray()
            .value(1.5)
            .value(2L)
            .endArray()
            .endObject();

    assertSame(writer, result);
    writer.close();
  }

  // ==================== Edge Cases Tests ====================

  @Test
  public void testUnicodeString() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray().value("Hello \u4e16\u754c").endArray();
    writer.close();

    assertEquals("[\"Hello \u4e16\u754c\"]", stringWriter.toString());
  }

  @Test
  public void testVeryLongString() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    StringBuilder longString = new StringBuilder();
    for (int i = 0; i < 10000; i++) {
      longString.append('a');
    }

    writer.beginArray().value(longString.toString()).endArray();
    writer.close();

    assertTrue(stringWriter.toString().length() > 10000);
  }

  @Test
  public void testEmptyStringValue() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginObject().name("").value("").endObject();
    writer.close();

    assertEquals("{\"\":\"\"}",
        stringWriter.toString());
  }

  // ==================== Writer Exception Handling Tests ====================

  @Test
  public void testWriteToClosedWriter() throws IOException {
    StringWriter stringWriter = new StringWriter();
    stringWriter.close();
    JsonWriter writer = new JsonWriter(stringWriter);

    // StringWriter doesn't throw on close, but some writers do
    writer.beginArray().endArray();
    writer.close();
  }

  // ==================== Stack Overflow Prevention Tests ====================

  @Test
  public void testManyNestedLevels() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    // Test stack growing (initial size is 32)
    int depth = 100;
    for (int i = 0; i < depth; i++) {
      writer.beginArray();
    }
    for (int i = 0; i < depth; i++) {
      writer.endArray();
    }
    writer.close();

    String result = stringWriter.toString();
    assertTrue(result.startsWith("[[["));
    assertTrue(result.endsWith("]]]"));
  }
}
