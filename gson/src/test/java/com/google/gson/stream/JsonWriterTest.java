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

package com.google.gson.stream;

import static com.google.common.truth.Truth.assertThat;

import com.google.gson.FormattingStyle;
import com.google.gson.Strictness;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.Test;

public class JsonWriterTest {

  @Test
  public void testConstructor() throws IOException {
    StringWriter stringWriter = new StringWriter();

    JsonWriter writer = new JsonWriter(stringWriter);

    assertThat(writer).isNotNull();
    assertThat(writer.getFormattingStyle()).isEqualTo(FormattingStyle.COMPACT);
  }

  @Test
  public void testSetIndentWithEmptyString() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.setIndent("");

    assertThat(writer.getFormattingStyle()).isEqualTo(FormattingStyle.COMPACT);
  }

  @Test
  public void testSetIndentWithNonEmptyString() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.setIndent("  ");
    writer.beginArray();
    writer.value(1);
    writer.endArray();

    assertThat(stringWriter.toString()).isEqualTo("[\n  1\n]");
  }

  @Test
  public void testSetFormattingStyleCompact() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.setFormattingStyle(FormattingStyle.COMPACT);
    writer.beginObject();
    writer.name("key");
    writer.value("value");
    writer.endObject();

    assertThat(stringWriter.toString()).isEqualTo("{\"key\":\"value\"}");
  }

  @Test
  public void testSetFormattingStylePretty() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.setFormattingStyle(FormattingStyle.PRETTY);
    writer.beginObject();
    writer.name("key");
    writer.value("value");
    writer.endObject();

    assertThat(stringWriter.toString()).contains("{\n");
  }

  @Test
  public void testGetFormattingStyle() {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    FormattingStyle result = writer.getFormattingStyle();

    assertThat(result).isEqualTo(FormattingStyle.COMPACT);
  }

  @SuppressWarnings("deprecation")
  @Test
  public void testSetLenientTrue() {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.setLenient(true);

    assertThat(writer.isLenient()).isTrue();
  }

  @SuppressWarnings("deprecation")
  @Test
  public void testSetLenientFalse() {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.setLenient(false);

    assertThat(writer.isLenient()).isFalse();
  }

  @Test
  public void testIsLenient() {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    boolean result = writer.isLenient();

    assertThat(result).isFalse();
  }

  @Test
  public void testSetStrictness() {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.setStrictness(Strictness.LENIENT);

    assertThat(writer.getStrictness()).isEqualTo(Strictness.LENIENT);
  }

  @Test
  public void testGetStrictness() {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    Strictness result = writer.getStrictness();

    assertThat(result).isEqualTo(Strictness.LEGACY_STRICT);
  }

  @Test
  public void testSetHtmlSafeTrue() {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.setHtmlSafe(true);

    assertThat(writer.isHtmlSafe()).isTrue();
  }

  @Test
  public void testSetHtmlSafeFalse() {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.setHtmlSafe(false);

    assertThat(writer.isHtmlSafe()).isFalse();
  }

  @Test
  public void testIsHtmlSafe() {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    boolean result = writer.isHtmlSafe();

    assertThat(result).isFalse();
  }

  @Test
  public void testSetSerializeNullsTrue() {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.setSerializeNulls(true);

    assertThat(writer.getSerializeNulls()).isTrue();
  }

  @Test
  public void testSetSerializeNullsFalse() {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.setSerializeNulls(false);

    assertThat(writer.getSerializeNulls()).isFalse();
  }

  @Test
  public void testGetSerializeNulls() {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    boolean result = writer.getSerializeNulls();

    assertThat(result).isTrue();
  }

  @Test
  public void testBeginArray() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray();
    writer.endArray();

    assertThat(stringWriter.toString()).isEqualTo("[]");
  }

  @Test
  public void testEndArray() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray();
    writer.value(1);
    writer.endArray();

    assertThat(stringWriter.toString()).isEqualTo("[1]");
  }

  @Test
  public void testBeginObject() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginObject();
    writer.endObject();

    assertThat(stringWriter.toString()).isEqualTo("{}");
  }

  @Test
  public void testEndObject() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginObject();
    writer.name("key");
    writer.value("value");
    writer.endObject();

    assertThat(stringWriter.toString()).isEqualTo("{\"key\":\"value\"}");
  }

  @Test
  public void testName() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginObject();
    writer.name("testName");
    writer.value("testValue");
    writer.endObject();

    assertThat(stringWriter.toString()).isEqualTo("{\"testName\":\"testValue\"}");
  }

  @Test
  public void testNameWithInvalidState() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    try {
      writer.name("invalid");
      throw new AssertionError("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertThat(e.getMessage()).contains("Please begin an object before writing a name");
    }
  }

  @Test
  public void testValueString() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.value("hello");

    assertThat(stringWriter.toString()).isEqualTo("\"hello\"");
  }

  @Test
  public void testValueStringNull() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.value((String) null);

    assertThat(stringWriter.toString()).isEqualTo("null");
  }

  @Test
  public void testValueBoolean() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.value(true);

    assertThat(stringWriter.toString()).isEqualTo("true");
  }

  @Test
  public void testValueBooleanFalse() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.value(false);

    assertThat(stringWriter.toString()).isEqualTo("false");
  }

  @Test
  public void testValueBooleanObject() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.value(Boolean.TRUE);

    assertThat(stringWriter.toString()).isEqualTo("true");
  }

  @Test
  public void testValueBooleanObjectNull() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.value((Boolean) null);

    assertThat(stringWriter.toString()).isEqualTo("null");
  }

  @Test
  public void testValueFloat() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.value(1.5f);

    assertThat(stringWriter.toString()).isEqualTo("1.5");
  }

  @Test
  public void testValueFloatNaNInStrictMode() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    try {
      writer.value(Float.NaN);
      throw new AssertionError("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertThat(e.getMessage()).contains("Numeric values must be finite");
    }
  }

  @Test
  public void testValueFloatInfinityInStrictMode() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    try {
      writer.value(Float.POSITIVE_INFINITY);
      throw new AssertionError("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertThat(e.getMessage()).contains("Numeric values must be finite");
    }
  }

  @Test
  public void testValueDouble() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.value(2.5);

    assertThat(stringWriter.toString()).isEqualTo("2.5");
  }

  @Test
  public void testValueDoubleNaNInStrictMode() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    try {
      writer.value(Double.NaN);
      throw new AssertionError("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertThat(e.getMessage()).contains("Numeric values must be finite");
    }
  }

  @Test
  public void testValueDoubleInfinityInStrictMode() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    try {
      writer.value(Double.POSITIVE_INFINITY);
      throw new AssertionError("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertThat(e.getMessage()).contains("Numeric values must be finite");
    }
  }

  @Test
  public void testValueLong() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.value(123L);

    assertThat(stringWriter.toString()).isEqualTo("123");
  }

  @Test
  public void testValueNumberInteger() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.value(Integer.valueOf(42));

    assertThat(stringWriter.toString()).isEqualTo("42");
  }

  @Test
  public void testValueNumberBigDecimal() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.value(new BigDecimal("123.456"));

    assertThat(stringWriter.toString()).isEqualTo("123.456");
  }

  @Test
  public void testValueNumberBigInteger() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.value(new BigInteger("9876543210"));

    assertThat(stringWriter.toString()).isEqualTo("9876543210");
  }

  @Test
  public void testValueNumberAtomicInteger() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.value(new AtomicInteger(99));

    assertThat(stringWriter.toString()).isEqualTo("99");
  }

  @Test
  public void testValueNumberAtomicLong() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.value(new AtomicLong(1234567890L));

    assertThat(stringWriter.toString()).isEqualTo("1234567890");
  }

  @Test
  public void testValueNumberNull() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.value((Number) null);

    assertThat(stringWriter.toString()).isEqualTo("null");
  }

  @Test
  public void testNullValue() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.nullValue();

    assertThat(stringWriter.toString()).isEqualTo("null");
  }

  @Test
  public void testNullValueWithSerializeNullsFalse() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    writer.setSerializeNulls(false);

    writer.beginObject();
    writer.name("key");
    writer.nullValue();
    writer.endObject();

    assertThat(stringWriter.toString()).isEqualTo("{}");
  }

  @Test
  public void testNullValueWithSerializeNullsTrue() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    writer.setSerializeNulls(true);

    writer.beginObject();
    writer.name("key");
    writer.nullValue();
    writer.endObject();

    assertThat(stringWriter.toString()).isEqualTo("{\"key\":null}");
  }

  @Test
  public void testJsonValue() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.jsonValue("{\"raw\":\"json\"}");

    assertThat(stringWriter.toString()).isEqualTo("{\"raw\":\"json\"}");
  }

  @Test
  public void testJsonValueNull() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.jsonValue(null);

    assertThat(stringWriter.toString()).isEqualTo("null");
  }

  @Test
  public void testFlush() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    writer.beginArray();

    writer.flush();

    assertThat(stringWriter.toString()).isEqualTo("[");
  }

  @Test
  public void testFlushWhenClosed() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    writer.beginArray();
    writer.endArray();
    writer.close();

    try {
      writer.flush();
      throw new AssertionError("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertThat(e.getMessage()).contains("JsonWriter is closed");
    }
  }

  @Test
  public void testClose() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    writer.beginArray();
    writer.endArray();

    writer.close();

    assertThat(stringWriter.toString()).isEqualTo("[]");
  }

  @Test
  public void testCloseWithIncompleteDocument() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    writer.beginArray();

    try {
      writer.close();
      throw new AssertionError("Expected IOException");
    } catch (IOException e) {
      assertThat(e.getMessage()).contains("Incomplete document");
    }
  }

  @Test
  public void testHtmlSafeEncoding() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    writer.setHtmlSafe(true);

    writer.value("<script>alert('xss')</script>");

    String result = stringWriter.toString();
    assertThat(result).contains("\\u003c");
    assertThat(result).contains("\\u003e");
  }

  @Test
  public void testStringEscaping() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.value("line1\nline2\ttab\"quote\\backslash");

    String result = stringWriter.toString();
    assertThat(result).contains("\\n");
    assertThat(result).contains("\\t");
    assertThat(result).contains("\\\"");
    assertThat(result).contains("\\\\");
  }

  @Test
  public void testMultipleValues() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray();
    writer.value(1);
    writer.value(2);
    writer.value(3);
    writer.endArray();

    assertThat(stringWriter.toString()).isEqualTo("[1,2,3]");
  }

  @Test
  public void testNestedArrays() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray();
    writer.beginArray();
    writer.value(1);
    writer.endArray();
    writer.endArray();

    assertThat(stringWriter.toString()).isEqualTo("[[1]]");
  }

  @Test
  public void testNestedObjects() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginObject();
    writer.name("outer");
    writer.beginObject();
    writer.name("inner");
    writer.value("value");
    writer.endObject();
    writer.endObject();

    assertThat(stringWriter.toString()).isEqualTo("{\"outer\":{\"inner\":\"value\"}}");
  }

  @Test
  public void testLenientMultipleTopLevelValues() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    writer.setStrictness(Strictness.LENIENT);

    writer.value(1);
    writer.value(2);

    assertThat(stringWriter.toString()).isEqualTo("12");
  }

  @Test
  public void testStrictMultipleTopLevelValuesThrows() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    writer.value(1);

    try {
      writer.value(2);
      throw new AssertionError("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertThat(e.getMessage()).contains("JSON must have only one top-level value");
    }
  }

  @Test
  public void testLenientNaN() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    writer.setStrictness(Strictness.LENIENT);

    writer.value(Double.NaN);

    assertThat(stringWriter.toString()).isEqualTo("NaN");
  }

  @Test
  public void testLenientInfinity() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    writer.setStrictness(Strictness.LENIENT);

    writer.value(Double.POSITIVE_INFINITY);

    assertThat(stringWriter.toString()).isEqualTo("Infinity");
  }

  @Test
  public void testPrettyPrintingWithIndent() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    writer.setIndent("    ");

    writer.beginObject();
    writer.name("key");
    writer.value("value");
    writer.endObject();

    String result = stringWriter.toString();
    assertThat(result).contains("{\n");
    assertThat(result).contains("    ");
  }

  @Test
  public void testArrayWithMultipleTypes() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginArray();
    writer.value(1);
    writer.value("string");
    writer.value(true);
    writer.nullValue();
    writer.endArray();

    assertThat(stringWriter.toString()).isEqualTo("[1,\"string\",true,null]");
  }

  @Test
  public void testObjectWithMultipleProperties() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.beginObject();
    writer.name("number");
    writer.value(42);
    writer.name("string");
    writer.value("text");
    writer.name("boolean");
    writer.value(false);
    writer.endObject();

    assertThat(stringWriter.toString()).isEqualTo("{\"number\":42,\"string\":\"text\",\"boolean\":false}");
  }

  @Test
  public void testValueStringWithLineSeparatorChar() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.value("before\u2028after");

    assertThat(stringWriter.toString()).isEqualTo("\"before\\u2028after\"");
  }

  @Test
  public void testValueStringWithParagraphSeparatorChar() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.value("before\u2029after");

    assertThat(stringWriter.toString()).isEqualTo("\"before\\u2029after\"");
  }

  @Test
  public void testValueStringWithUnicodeCharsNotRequiringEscape() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.value("café\u00e9");

    assertThat(stringWriter.toString()).isEqualTo("\"café\u00e9\"");
  }

  @Test
  public void testValueNumberCustomInfinityInStrictMode() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    Number infinityNumber = new Number() {
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
        return Float.POSITIVE_INFINITY;
      }

      @Override
      public double doubleValue() {
        return Double.POSITIVE_INFINITY;
      }

      @Override
      public String toString() {
        return "Infinity";
      }
    };

    try {
      writer.value(infinityNumber);
      throw new AssertionError("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertThat(e.getMessage()).contains("Numeric values must be finite");
    }
  }

  @Test
  public void testValueNumberCustomNegativeInfinityInStrictMode() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    Number negativeInfinityNumber = new Number() {
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
        return Float.NEGATIVE_INFINITY;
      }

      @Override
      public double doubleValue() {
        return Double.NEGATIVE_INFINITY;
      }

      @Override
      public String toString() {
        return "-Infinity";
      }
    };

    try {
      writer.value(negativeInfinityNumber);
      throw new AssertionError("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertThat(e.getMessage()).contains("Numeric values must be finite");
    }
  }

  @Test
  public void testValueNumberCustomNaNInStrictMode() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    Number nanNumber = new Number() {
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
        return Float.NaN;
      }

      @Override
      public double doubleValue() {
        return Double.NaN;
      }

      @Override
      public String toString() {
        return "NaN";
      }
    };

    try {
      writer.value(nanNumber);
      throw new AssertionError("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertThat(e.getMessage()).contains("Numeric values must be finite");
    }
  }

  @Test
  public void testValueNumberCustomInvalidJsonNumber() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    Number invalidNumber = new Number() {
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
        return "not-a-number";
      }
    };

    try {
      writer.value(invalidNumber);
      throw new AssertionError("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertThat(e.getMessage()).contains("is not a valid JSON number");
    }
  }

  @Test
  public void testValueNumberCustomInfinityInLenientMode() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    writer.setStrictness(Strictness.LENIENT);

    Number infinityNumber = new Number() {
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
        return Float.POSITIVE_INFINITY;
      }

      @Override
      public double doubleValue() {
        return Double.POSITIVE_INFINITY;
      }

      @Override
      public String toString() {
        return "Infinity";
      }
    };

    writer.value(infinityNumber);

    assertThat(stringWriter.toString()).isEqualTo("Infinity");
  }

  @Test
  public void testValueNumberCustomValidJsonNumber() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    Number validNumber = new Number() {
      @Override
      public int intValue() {
        return 123;
      }

      @Override
      public long longValue() {
        return 123;
      }

      @Override
      public float floatValue() {
        return 123.45f;
      }

      @Override
      public double doubleValue() {
        return 123.45;
      }

      @Override
      public String toString() {
        return "123.45";
      }
    };

    writer.value(validNumber);

    assertThat(stringWriter.toString()).isEqualTo("123.45");
  }

  @Test
  public void testValueNumberFloatObject() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.value(Float.valueOf(1.5f));

    assertThat(stringWriter.toString()).isEqualTo("1.5");
  }

  @Test
  public void testValueNumberDoubleObject() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    writer.value(Double.valueOf(2.5));

    assertThat(stringWriter.toString()).isEqualTo("2.5");
  }
}
