/*
 * Copyright (C) 2024 Google Inc.
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

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.google.gson.FormattingStyle;
import com.google.gson.Strictness;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.Before;
import org.junit.Test;

public final class JsonWriterTest {

  private StringWriter stringWriter;
  private JsonWriter writer;

  @Before
  public void setUp() {
    stringWriter = new StringWriter();
    writer = new JsonWriter(stringWriter);
  }

  @Test
  public void testConstructorNullThrows() {
    assertThrows(NullPointerException.class, () -> new JsonWriter(null));
  }

  @Test
  public void testConstructorDefaultsCompact() throws IOException {
    writer.beginObject();
    writer.endObject();
    assertThat(stringWriter.toString()).isEqualTo("{}");
  }

  @Test
  public void testSetIndentEmpty() throws IOException {
    writer.setIndent("");
    writer.beginObject();
    writer.endObject();
    assertThat(stringWriter.toString()).isEqualTo("{}");
  }

  @Test
  public void testSetIndentNonEmpty() throws IOException {
    writer.setIndent("  ");
    writer.beginArray();
    writer.value("x");
    writer.endArray();
    assertThat(stringWriter.toString()).isEqualTo("[\n  \"x\"\n]");
  }

  @Test
  public void testSetFormattingStyleCompact() throws IOException {
    writer.setFormattingStyle(FormattingStyle.COMPACT);
    writer.beginObject();
    writer.name("a").value("b");
    writer.endObject();
    assertThat(stringWriter.toString()).isEqualTo("{\"a\":\"b\"}");
  }

  @Test
  public void testSetFormattingStylePretty() throws IOException {
    writer.setFormattingStyle(FormattingStyle.PRETTY);
    writer.beginArray();
    writer.value(1L);
    writer.value(2L);
    writer.endArray();
    assertThat(stringWriter.toString()).isEqualTo("[\n  1,\n  2\n]");
  }

  @Test
  public void testSetFormattingStyleNull() {
    assertThrows(NullPointerException.class, () -> writer.setFormattingStyle(null));
  }

  @Test
  public void testGetFormattingStyleDefault() {
    assertThat(writer.getFormattingStyle()).isEqualTo(FormattingStyle.COMPACT);
  }

  @Test
  public void testGetFormattingStyleAfterSet() {
    writer.setFormattingStyle(FormattingStyle.PRETTY);
    assertThat(writer.getFormattingStyle()).isEqualTo(FormattingStyle.PRETTY);
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testSetLenientTrue() {
    writer.setLenient(true);
    assertThat(writer.isLenient()).isTrue();
    assertThat(writer.getStrictness()).isEqualTo(Strictness.LENIENT);
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testSetLenientFalse() {
    writer.setLenient(false);
    assertThat(writer.isLenient()).isFalse();
    assertThat(writer.getStrictness()).isEqualTo(Strictness.LEGACY_STRICT);
  }

  @Test
  public void testIsLenientDefault() {
    assertThat(writer.isLenient()).isFalse();
  }

  @Test
  public void testSetStrictnessStrict() {
    writer.setStrictness(Strictness.STRICT);
    assertThat(writer.getStrictness()).isEqualTo(Strictness.STRICT);
  }

  @Test
  public void testSetStrictnessNull() {
    assertThrows(NullPointerException.class, () -> writer.setStrictness(null));
  }

  @Test
  public void testGetStrictnessDefault() {
    assertThat(writer.getStrictness()).isEqualTo(Strictness.LEGACY_STRICT);
  }

  @Test
  public void testSetHtmlSafeTrue() {
    writer.setHtmlSafe(true);
    assertThat(writer.isHtmlSafe()).isTrue();
  }

  @Test
  public void testSetHtmlSafeFalse() {
    writer.setHtmlSafe(false);
    assertThat(writer.isHtmlSafe()).isFalse();
  }

  @Test
  public void testIsHtmlSafeDefault() {
    assertThat(writer.isHtmlSafe()).isFalse();
  }

  @Test
  public void testSetSerializeNullsFalse() {
    writer.setSerializeNulls(false);
    assertThat(writer.getSerializeNulls()).isFalse();
  }

  @Test
  public void testGetSerializeNullsDefault() {
    assertThat(writer.getSerializeNulls()).isTrue();
  }

  @Test
  public void testBeginAndEndArray() throws IOException {
    writer.beginArray();
    writer.endArray();
    assertThat(stringWriter.toString()).isEqualTo("[]");
  }

  @Test
  public void testBeginAndEndObject() throws IOException {
    writer.beginObject();
    writer.endObject();
    assertThat(stringWriter.toString()).isEqualTo("{}");
  }

  @Test
  public void testBeginArrayReturnsThis() throws IOException {
    assertThat(writer.beginArray()).isSameInstanceAs(writer);
    writer.endArray();
  }

  @Test
  public void testEndArrayReturnsThis() throws IOException {
    writer.beginArray();
    assertThat(writer.endArray()).isSameInstanceAs(writer);
  }

  @Test
  public void testBeginObjectReturnsThis() throws IOException {
    assertThat(writer.beginObject()).isSameInstanceAs(writer);
    writer.endObject();
  }

  @Test
  public void testEndObjectReturnsThis() throws IOException {
    writer.beginObject();
    assertThat(writer.endObject()).isSameInstanceAs(writer);
  }

  @Test
  public void testEndArrayWithoutBeginThrows() {
    assertThrows(IllegalStateException.class, () -> writer.endArray());
  }

  @Test
  public void testEndObjectWithoutBeginThrows() {
    assertThrows(IllegalStateException.class, () -> writer.endObject());
  }

  @Test
  public void testNameRequiresObject() throws IOException {
    writer.beginArray();
    assertThrows(IllegalStateException.class, () -> writer.name("key"));
    writer.endArray();
  }

  @Test
  public void testNameNull() throws IOException {
    writer.beginObject();
    assertThrows(NullPointerException.class, () -> writer.name(null));
    writer.endObject();
  }

  @Test
  public void testNameDoubleName() throws IOException {
    writer.beginObject();
    writer.name("key");
    assertThrows(IllegalStateException.class, () -> writer.name("other"));
  }

  @Test
  public void testNameAndValueString() throws IOException {
    writer.beginObject();
    writer.name("key").value("val");
    writer.endObject();
    assertThat(stringWriter.toString()).isEqualTo("{\"key\":\"val\"}");
  }

  @Test
  public void testValueStringNull() throws IOException {
    writer.value((String) null);
    assertThat(stringWriter.toString()).isEqualTo("null");
  }

  @Test
  public void testValueStringNonNull() throws IOException {
    writer.value("hello");
    assertThat(stringWriter.toString()).isEqualTo("\"hello\"");
  }

  @Test
  public void testValueBooleanTrue() throws IOException {
    writer.value(true);
    assertThat(stringWriter.toString()).isEqualTo("true");
  }

  @Test
  public void testValueBooleanFalse() throws IOException {
    writer.value(false);
    assertThat(stringWriter.toString()).isEqualTo("false");
  }

  @Test
  public void testValueBooleanObjectNull() throws IOException {
    writer.value((Boolean) null);
    assertThat(stringWriter.toString()).isEqualTo("null");
  }

  @Test
  public void testValueBooleanObjectTrue() throws IOException {
    writer.value(Boolean.TRUE);
    assertThat(stringWriter.toString()).isEqualTo("true");
  }

  @Test
  public void testValueFloat() throws IOException {
    writer.value(1.5f);
    assertThat(stringWriter.toString()).isEqualTo("1.5");
  }

  @Test
  public void testValueFloatNaNStrict() {
    assertThrows(IllegalArgumentException.class, () -> writer.value(Float.NaN));
  }

  @Test
  public void testValueFloatNaNLenient() throws IOException {
    writer.setStrictness(Strictness.LENIENT);
    writer.value(Float.NaN);
    assertThat(stringWriter.toString()).isEqualTo("NaN");
  }

  @Test
  public void testValueFloatInfiniteStrict() {
    assertThrows(IllegalArgumentException.class, () -> writer.value(Float.POSITIVE_INFINITY));
  }

  @Test
  public void testValueDouble() throws IOException {
    writer.value(3.14);
    assertThat(stringWriter.toString()).isEqualTo("3.14");
  }

  @Test
  public void testValueDoubleNaNStrict() {
    assertThrows(IllegalArgumentException.class, () -> writer.value(Double.NaN));
  }

  @Test
  public void testValueDoubleInfinityLenient() throws IOException {
    writer.setStrictness(Strictness.LENIENT);
    writer.value(Double.POSITIVE_INFINITY);
    assertThat(stringWriter.toString()).isEqualTo("Infinity");
  }

  @Test
  public void testValueLong() throws IOException {
    writer.value(42L);
    assertThat(stringWriter.toString()).isEqualTo("42");
  }

  @Test
  public void testValueNumberNull() throws IOException {
    writer.value((Number) null);
    assertThat(stringWriter.toString()).isEqualTo("null");
  }

  @Test
  public void testValueNumberInteger() throws IOException {
    writer.value(Integer.valueOf(7));
    assertThat(stringWriter.toString()).isEqualTo("7");
  }

  @Test
  public void testValueNumberLongObject() throws IOException {
    writer.value(Long.valueOf(123456789012345L));
    assertThat(stringWriter.toString()).isEqualTo("123456789012345");
  }

  @Test
  public void testValueNumberBigDecimal() throws IOException {
    writer.value(new BigDecimal("12.345"));
    assertThat(stringWriter.toString()).isEqualTo("12.345");
  }

  @Test
  public void testValueNumberBigInteger() throws IOException {
    writer.value(new BigInteger("9999999999999999999"));
    assertThat(stringWriter.toString()).isEqualTo("9999999999999999999");
  }

  @Test
  public void testValueNumberAtomicInteger() throws IOException {
    writer.value(new AtomicInteger(5));
    assertThat(stringWriter.toString()).isEqualTo("5");
  }

  @Test
  public void testValueNumberAtomicLong() throws IOException {
    writer.value(new AtomicLong(99L));
    assertThat(stringWriter.toString()).isEqualTo("99");
  }

  @Test
  public void testValueNumberInvalidNaNStrict() {
    // Double-based number that returns NaN via toString for a custom type
    // Use Double.NaN as a Number reference (goes through non-alwaysValid path)
    assertThrows(
        IllegalArgumentException.class,
        () -> writer.value(Double.valueOf(Double.NaN)));
  }

  @Test
  public void testValueNumberInvalidStringThrows() {
    Number badNumber = new Number() {
      @Override public int intValue() { return 0; }
      @Override public long longValue() { return 0; }
      @Override public float floatValue() { return 0; }
      @Override public double doubleValue() { return 0; }
      @Override public String toString() { return "not-a-number"; }
    };
    assertThrows(IllegalArgumentException.class, () -> writer.value(badNumber));
  }

  @Test
  public void testNullValue() throws IOException {
    writer.nullValue();
    assertThat(stringWriter.toString()).isEqualTo("null");
  }

  @Test
  public void testNullValueInObjectSerializeNullsFalse() throws IOException {
    writer.setSerializeNulls(false);
    writer.beginObject();
    writer.name("key").nullValue();
    writer.endObject();
    assertThat(stringWriter.toString()).isEqualTo("{}");
  }

  @Test
  public void testNullValueInObjectSerializeNullsTrue() throws IOException {
    writer.setSerializeNulls(true);
    writer.beginObject();
    writer.name("key").nullValue();
    writer.endObject();
    assertThat(stringWriter.toString()).isEqualTo("{\"key\":null}");
  }

  @Test
  public void testJsonValueNull() throws IOException {
    writer.jsonValue(null);
    assertThat(stringWriter.toString()).isEqualTo("null");
  }

  @Test
  public void testJsonValueNonNull() throws IOException {
    writer.jsonValue("{\"raw\":true}");
    assertThat(stringWriter.toString()).isEqualTo("{\"raw\":true}");
  }

  @Test
  public void testFlush() throws IOException {
    writer.beginObject();
    writer.endObject();
    writer.flush();
    assertThat(stringWriter.toString()).isEqualTo("{}");
  }

  @Test
  public void testFlushOnClosedWriterThrows() throws IOException {
    writer.beginObject();
    writer.endObject();
    writer.close();
    assertThrows(IllegalStateException.class, () -> writer.flush());
  }

  @Test
  public void testCloseCompleteDocument() throws IOException {
    writer.beginObject();
    writer.endObject();
    writer.close();
    assertThat(stringWriter.toString()).isEqualTo("{}");
  }

  @Test
  public void testCloseIncompleteDocumentThrows() throws IOException {
    writer.beginObject();
    assertThrows(IOException.class, () -> writer.close());
  }

  @Test
  public void testCloseSingleValueDocument() throws IOException {
    writer.value("done");
    writer.close();
    assertThat(stringWriter.toString()).isEqualTo("\"done\"");
  }

  @Test
  public void testMultipleTopLevelValuesStrict() throws IOException {
    writer.value("first");
    assertThrows(IllegalStateException.class, () -> writer.value("second"));
  }

  @Test
  public void testMultipleTopLevelValuesLenient() throws IOException {
    writer.setStrictness(Strictness.LENIENT);
    writer.value("first");
    writer.value("second");
    assertThat(stringWriter.toString()).isEqualTo("\"first\"\"second\"");
  }

  @Test
  public void testHtmlSafeEscaping() throws IOException {
    writer.setHtmlSafe(true);
    writer.value("<b>&amp;='x'</b>");
    assertThat(stringWriter.toString())
        .isEqualTo("\"\\u003cb\\u003e\\u0026amp;\\u003d\\u0027x\\u0027\\u003c/b\\u003e\"");
  }

  @Test
  public void testStringEscapingSpecialChars() throws IOException {
    writer.value("tab\tnewline\n");
    assertThat(stringWriter.toString()).isEqualTo("\"tab\\tnewline\\n\"");
  }

  @Test
  public void testStringEscapingUnicode2028() throws IOException {
    writer.value("\u2028");
    assertThat(stringWriter.toString()).isEqualTo("\"\\u2028\"");
  }

  @Test
  public void testStringEscapingUnicode2029() throws IOException {
    writer.value("\u2029");
    assertThat(stringWriter.toString()).isEqualTo("\"\\u2029\"");
  }

  @Test
  public void testNestedObjectAndArray() throws IOException {
    writer.beginObject();
    writer.name("arr");
    writer.beginArray();
    writer.value(1L);
    writer.value(2L);
    writer.endArray();
    writer.endObject();
    assertThat(stringWriter.toString()).isEqualTo("{\"arr\":[1,2]}");
  }

  @Test
  public void testPrettyPrintingWithFormattingStylePretty() throws IOException {
    writer.setFormattingStyle(FormattingStyle.PRETTY);
    writer.beginObject();
    writer.name("key").value("value");
    writer.endObject();
    assertThat(stringWriter.toString()).isEqualTo("{\n  \"key\": \"value\"\n}");
  }

  @Test
  public void testDanglingNameOnCloseThrows() throws IOException {
    writer.beginObject();
    writer.name("key");
    assertThrows(IllegalStateException.class, () -> writer.endObject());
  }
}
