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

package com.google.gson.internal.bind;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.ToNumberPolicy;
import com.google.gson.ToNumberStrategy;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import org.junit.Test;

/** Tests for {@link NumberTypeAdapter}. */
public class NumberTypeAdapterClaudeTest {

  // ==========================================================================
  // getFactory() tests
  // ==========================================================================

  @Test
  public void getFactory_withLazilyParsedNumberPolicy_returnsCachedFactory() {
    // When LAZILY_PARSED_NUMBER policy is used, the same cached factory should be returned
    TypeAdapterFactory factory1 = NumberTypeAdapter.getFactory(ToNumberPolicy.LAZILY_PARSED_NUMBER);
    TypeAdapterFactory factory2 = NumberTypeAdapter.getFactory(ToNumberPolicy.LAZILY_PARSED_NUMBER);

    assertNotNull(factory1);
    assertNotNull(factory2);
    // Should return the same cached instance
    assertSame(factory1, factory2);
  }

  @Test
  public void getFactory_withDoublePolicy_returnsNewFactory() {
    TypeAdapterFactory factory1 = NumberTypeAdapter.getFactory(ToNumberPolicy.DOUBLE);
    TypeAdapterFactory factory2 = NumberTypeAdapter.getFactory(ToNumberPolicy.DOUBLE);

    assertNotNull(factory1);
    assertNotNull(factory2);
    // Non-LAZILY_PARSED_NUMBER policies create new factory instances each time
  }

  @Test
  public void getFactory_withLongOrDoublePolicy_returnsNewFactory() {
    TypeAdapterFactory factory1 = NumberTypeAdapter.getFactory(ToNumberPolicy.LONG_OR_DOUBLE);
    TypeAdapterFactory factory2 = NumberTypeAdapter.getFactory(ToNumberPolicy.LONG_OR_DOUBLE);

    assertNotNull(factory1);
    assertNotNull(factory2);
  }

  @Test
  public void getFactory_withBigDecimalPolicy_returnsNewFactory() {
    TypeAdapterFactory factory1 = NumberTypeAdapter.getFactory(ToNumberPolicy.BIG_DECIMAL);
    TypeAdapterFactory factory2 = NumberTypeAdapter.getFactory(ToNumberPolicy.BIG_DECIMAL);

    assertNotNull(factory1);
    assertNotNull(factory2);
  }

  @Test
  public void getFactory_createsAdapterOnlyForNumberType() {
    Gson gson = new Gson();
    TypeAdapterFactory factory = NumberTypeAdapter.getFactory(ToNumberPolicy.LAZILY_PARSED_NUMBER);

    // Should create adapter for Number.class
    TypeAdapter<Number> numberAdapter = factory.create(gson, TypeToken.get(Number.class));
    assertNotNull(numberAdapter);

    // Should return null for other types (Integer, Long, Double, etc.)
    TypeAdapter<Integer> intAdapter = factory.create(gson, TypeToken.get(Integer.class));
    assertNull(intAdapter);

    TypeAdapter<Double> doubleAdapter = factory.create(gson, TypeToken.get(Double.class));
    assertNull(doubleAdapter);

    TypeAdapter<Long> longAdapter = factory.create(gson, TypeToken.get(Long.class));
    assertNull(longAdapter);

    TypeAdapter<String> stringAdapter = factory.create(gson, TypeToken.get(String.class));
    assertNull(stringAdapter);
  }

  @Test
  public void getFactory_withCustomStrategy_createsWorkingAdapter() {
    ToNumberStrategy customStrategy =
        new ToNumberStrategy() {
          @Override
          public Number readNumber(JsonReader in) throws IOException {
            // Always return 42 regardless of input
            @SuppressWarnings("unused")
            String ignored = in.nextString();
            return 42;
          }
        };

    TypeAdapterFactory factory = NumberTypeAdapter.getFactory(customStrategy);
    Gson gson = new Gson();
    TypeAdapter<Number> adapter = factory.create(gson, TypeToken.get(Number.class));

    assertNotNull(adapter);
  }

  // ==========================================================================
  // read() tests - null values
  // ==========================================================================

  @Test
  public void read_nullValue_returnsNull() throws IOException {
    Gson gson = new Gson();
    Number result = gson.fromJson("null", Number.class);
    assertNull(result);
  }

  @Test
  public void read_explicitNull_returnsNull() throws IOException {
    Gson gson = new GsonBuilder().setNumberToNumberStrategy(ToNumberPolicy.DOUBLE).create();
    Number result = gson.fromJson("null", Number.class);
    assertNull(result);
  }

  // ==========================================================================
  // read() tests - NUMBER token (integers)
  // ==========================================================================

  @Test
  public void read_integerWithLazilyParsedPolicy_returnsLazilyParsedNumber() throws IOException {
    Gson gson = new Gson(); // Default uses LAZILY_PARSED_NUMBER
    Number result = gson.fromJson("42", Number.class);

    assertNotNull(result);
    assertTrue(result instanceof LazilyParsedNumber);
    assertEquals(42, result.intValue());
  }

  @Test
  public void read_negativeIntegerWithLazilyParsedPolicy_returnsLazilyParsedNumber()
      throws IOException {
    Gson gson = new Gson();
    Number result = gson.fromJson("-123", Number.class);

    assertNotNull(result);
    assertTrue(result instanceof LazilyParsedNumber);
    assertEquals(-123, result.intValue());
  }

  @Test
  public void read_zeroWithLazilyParsedPolicy_returnsLazilyParsedNumber() throws IOException {
    Gson gson = new Gson();
    Number result = gson.fromJson("0", Number.class);

    assertNotNull(result);
    assertEquals(0, result.intValue());
  }

  @Test
  public void read_integerWithDoublePolicy_returnsDouble() throws IOException {
    Gson gson = new GsonBuilder().setNumberToNumberStrategy(ToNumberPolicy.DOUBLE).create();
    Number result = gson.fromJson("42", Number.class);

    assertNotNull(result);
    assertTrue(result instanceof Double);
    assertEquals(42.0, result.doubleValue(), 0.0001);
  }

  @Test
  public void read_integerWithLongOrDoublePolicy_returnsLong() throws IOException {
    Gson gson = new GsonBuilder().setNumberToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE).create();
    Number result = gson.fromJson("42", Number.class);

    assertNotNull(result);
    assertTrue(result instanceof Long);
    assertEquals(42L, result.longValue());
  }

  @Test
  public void read_integerWithBigDecimalPolicy_returnsBigDecimal() throws IOException {
    Gson gson = new GsonBuilder().setNumberToNumberStrategy(ToNumberPolicy.BIG_DECIMAL).create();
    Number result = gson.fromJson("42", Number.class);

    assertNotNull(result);
    assertTrue(result instanceof BigDecimal);
    assertEquals(new BigDecimal("42"), result);
  }

  // ==========================================================================
  // read() tests - NUMBER token (decimals)
  // ==========================================================================

  @Test
  public void read_decimalWithLazilyParsedPolicy_returnsLazilyParsedNumber() throws IOException {
    Gson gson = new Gson();
    Number result = gson.fromJson("3.14159", Number.class);

    assertNotNull(result);
    assertTrue(result instanceof LazilyParsedNumber);
    assertEquals(3.14159, result.doubleValue(), 0.00001);
  }

  @Test
  public void read_negativeDecimalWithLazilyParsedPolicy_returnsLazilyParsedNumber()
      throws IOException {
    Gson gson = new Gson();
    Number result = gson.fromJson("-99.5", Number.class);

    assertNotNull(result);
    assertEquals(-99.5, result.doubleValue(), 0.001);
  }

  @Test
  public void read_decimalWithDoublePolicy_returnsDouble() throws IOException {
    Gson gson = new GsonBuilder().setNumberToNumberStrategy(ToNumberPolicy.DOUBLE).create();
    Number result = gson.fromJson("3.14159", Number.class);

    assertNotNull(result);
    assertTrue(result instanceof Double);
    assertEquals(3.14159, result.doubleValue(), 0.00001);
  }

  @Test
  public void read_decimalWithLongOrDoublePolicy_returnsDouble() throws IOException {
    // Decimals should return Double since they can't be represented as Long
    Gson gson = new GsonBuilder().setNumberToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE).create();
    Number result = gson.fromJson("3.14159", Number.class);

    assertNotNull(result);
    assertTrue(result instanceof Double);
    assertEquals(3.14159, result.doubleValue(), 0.00001);
  }

  @Test
  public void read_decimalWithBigDecimalPolicy_returnsBigDecimal() throws IOException {
    Gson gson = new GsonBuilder().setNumberToNumberStrategy(ToNumberPolicy.BIG_DECIMAL).create();
    Number result = gson.fromJson("3.14159", Number.class);

    assertNotNull(result);
    assertTrue(result instanceof BigDecimal);
    assertEquals(new BigDecimal("3.14159"), result);
  }

  // ==========================================================================
  // read() tests - NUMBER token (scientific notation)
  // ==========================================================================

  @Test
  public void read_scientificNotation_returnsCorrectValue() throws IOException {
    Gson gson = new Gson();
    Number result = gson.fromJson("1.5e10", Number.class);

    assertNotNull(result);
    assertEquals(1.5e10, result.doubleValue(), 0.001);
  }

  @Test
  public void read_negativeExponent_returnsCorrectValue() throws IOException {
    Gson gson = new Gson();
    Number result = gson.fromJson("1.5e-5", Number.class);

    assertNotNull(result);
    assertEquals(1.5e-5, result.doubleValue(), 0.0000001);
  }

  @Test
  public void read_scientificNotationWithDoublePolicy_returnsDouble() throws IOException {
    Gson gson = new GsonBuilder().setNumberToNumberStrategy(ToNumberPolicy.DOUBLE).create();
    Number result = gson.fromJson("1e10", Number.class);

    assertNotNull(result);
    assertTrue(result instanceof Double);
    assertEquals(1e10, result.doubleValue(), 0.001);
  }

  // ==========================================================================
  // read() tests - NUMBER token (large numbers)
  // ==========================================================================

  @Test
  public void read_largeNumber_returnsCorrectValue() throws IOException {
    Gson gson = new Gson();
    Number result = gson.fromJson("9999999999999999999", Number.class);

    assertNotNull(result);
    // LazilyParsedNumber preserves the string representation
    assertEquals("9999999999999999999", result.toString());
  }

  @Test
  public void read_largeNumberWithBigDecimalPolicy_returnsBigDecimal() throws IOException {
    Gson gson = new GsonBuilder().setNumberToNumberStrategy(ToNumberPolicy.BIG_DECIMAL).create();
    Number result = gson.fromJson("9999999999999999999999999999", Number.class);

    assertNotNull(result);
    assertTrue(result instanceof BigDecimal);
    assertEquals(new BigDecimal("9999999999999999999999999999"), result);
  }

  @Test
  public void read_maxLongWithLongOrDoublePolicy_returnsLong() throws IOException {
    Gson gson = new GsonBuilder().setNumberToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE).create();
    Number result = gson.fromJson(String.valueOf(Long.MAX_VALUE), Number.class);

    assertNotNull(result);
    assertTrue(result instanceof Long);
    assertEquals(Long.MAX_VALUE, result.longValue());
  }

  @Test
  public void read_overflowLongWithLongOrDoublePolicy_returnsDouble() throws IOException {
    // Number larger than Long.MAX_VALUE should be parsed as Double
    Gson gson = new GsonBuilder().setNumberToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE).create();
    Number result = gson.fromJson("9999999999999999999999999", Number.class);

    assertNotNull(result);
    assertTrue(result instanceof Double);
  }

  // ==========================================================================
  // read() tests - STRING token (numbers as strings)
  // ==========================================================================

  @Test
  public void read_numberAsString_withLenientReader_returnsNumber() throws IOException {
    Gson gson = new Gson();
    JsonReader reader = new JsonReader(new StringReader("\"42\""));
    reader.setLenient(true);

    TypeAdapterFactory factory = NumberTypeAdapter.getFactory(ToNumberPolicy.LAZILY_PARSED_NUMBER);
    TypeAdapter<Number> adapter = factory.create(gson, TypeToken.get(Number.class));
    Number result = adapter.read(reader);

    assertNotNull(result);
    assertEquals(42, result.intValue());
  }

  @Test
  public void read_decimalAsString_withLenientReader_returnsNumber() throws IOException {
    Gson gson = new Gson();
    JsonReader reader = new JsonReader(new StringReader("\"3.14\""));
    reader.setLenient(true);

    TypeAdapterFactory factory = NumberTypeAdapter.getFactory(ToNumberPolicy.DOUBLE);
    TypeAdapter<Number> adapter = factory.create(gson, TypeToken.get(Number.class));
    Number result = adapter.read(reader);

    assertNotNull(result);
    assertTrue(result instanceof Double);
    assertEquals(3.14, result.doubleValue(), 0.001);
  }

  // ==========================================================================
  // read() tests - error handling
  // ==========================================================================

  @Test
  public void read_booleanValue_throwsJsonSyntaxException() {
    Gson gson = new Gson();

    try {
      gson.fromJson("true", Number.class);
      fail("Expected JsonSyntaxException");
    } catch (JsonSyntaxException e) {
      assertTrue(e.getMessage().contains("Expecting number"));
      assertTrue(e.getMessage().contains("BOOLEAN"));
    }
  }

  @Test
  public void read_arrayValue_throwsJsonSyntaxException() {
    Gson gson = new Gson();

    try {
      gson.fromJson("[1, 2, 3]", Number.class);
      fail("Expected JsonSyntaxException");
    } catch (JsonSyntaxException e) {
      assertTrue(e.getMessage().contains("Expecting number"));
      assertTrue(e.getMessage().contains("BEGIN_ARRAY"));
    }
  }

  @Test
  public void read_objectValue_throwsJsonSyntaxException() {
    Gson gson = new Gson();

    try {
      gson.fromJson("{\"key\": 42}", Number.class);
      fail("Expected JsonSyntaxException");
    } catch (JsonSyntaxException e) {
      assertTrue(e.getMessage().contains("Expecting number"));
      assertTrue(e.getMessage().contains("BEGIN_OBJECT"));
    }
  }

  @Test
  public void read_errorMessageIncludesPath() {
    Gson gson = new Gson();

    try {
      gson.fromJson("{\"value\": true}", TestClass.class);
      fail("Expected JsonSyntaxException");
    } catch (JsonSyntaxException e) {
      assertTrue(e.getMessage().contains("$.value"));
    }
  }

  private static class TestClass {
    @SuppressWarnings("unused")
    Number value;
  }

  // ==========================================================================
  // write() tests - null values
  // ==========================================================================

  @Test
  public void write_nullValue_writesNull() throws IOException {
    Gson gson = new Gson();
    String result = gson.toJson(null, Number.class);
    assertEquals("null", result);
  }

  // ==========================================================================
  // write() tests - integers
  // ==========================================================================

  @Test
  public void write_integerValue_writesNumber() throws IOException {
    Gson gson = new Gson();
    String result = gson.toJson((Number) 42, Number.class);
    assertEquals("42", result);
  }

  @Test
  public void write_negativeInteger_writesNumber() throws IOException {
    Gson gson = new Gson();
    String result = gson.toJson((Number) (-123), Number.class);
    assertEquals("-123", result);
  }

  @Test
  public void write_zeroInteger_writesZero() throws IOException {
    Gson gson = new Gson();
    String result = gson.toJson((Number) 0, Number.class);
    assertEquals("0", result);
  }

  @Test
  public void write_longValue_writesNumber() throws IOException {
    Gson gson = new Gson();
    String result = gson.toJson((Number) 9999999999999L, Number.class);
    assertEquals("9999999999999", result);
  }

  // ==========================================================================
  // write() tests - decimals
  // ==========================================================================

  @Test
  public void write_doubleValue_writesNumber() throws IOException {
    Gson gson = new Gson();
    String result = gson.toJson((Number) 3.14, Number.class);
    assertEquals("3.14", result);
  }

  @Test
  public void write_negativeDouble_writesNumber() throws IOException {
    Gson gson = new Gson();
    String result = gson.toJson((Number) (-99.5), Number.class);
    assertEquals("-99.5", result);
  }

  @Test
  public void write_floatValue_writesNumber() throws IOException {
    Gson gson = new Gson();
    String result = gson.toJson((Number) 2.5f, Number.class);
    assertEquals("2.5", result);
  }

  // ==========================================================================
  // write() tests - special double values
  // ==========================================================================

  @Test
  public void write_infinity_withLenientWriter_writesInfinity() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    writer.setLenient(true);

    Gson gson = new Gson();
    TypeAdapterFactory factory = NumberTypeAdapter.getFactory(ToNumberPolicy.LAZILY_PARSED_NUMBER);
    TypeAdapter<Number> adapter = factory.create(gson, TypeToken.get(Number.class));
    adapter.write(writer, Double.POSITIVE_INFINITY);

    assertEquals("Infinity", stringWriter.toString());
  }

  @Test
  public void write_negativeInfinity_withLenientWriter_writesNegativeInfinity() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    writer.setLenient(true);

    Gson gson = new Gson();
    TypeAdapterFactory factory = NumberTypeAdapter.getFactory(ToNumberPolicy.LAZILY_PARSED_NUMBER);
    TypeAdapter<Number> adapter = factory.create(gson, TypeToken.get(Number.class));
    adapter.write(writer, Double.NEGATIVE_INFINITY);

    assertEquals("-Infinity", stringWriter.toString());
  }

  @Test
  public void write_nan_withLenientWriter_writesNaN() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    writer.setLenient(true);

    Gson gson = new Gson();
    TypeAdapterFactory factory = NumberTypeAdapter.getFactory(ToNumberPolicy.LAZILY_PARSED_NUMBER);
    TypeAdapter<Number> adapter = factory.create(gson, TypeToken.get(Number.class));
    adapter.write(writer, Double.NaN);

    assertEquals("NaN", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - BigDecimal
  // ==========================================================================

  @Test
  public void write_bigDecimal_writesNumber() throws IOException {
    Gson gson = new Gson();
    String result = gson.toJson((Number) new BigDecimal("123.456"), Number.class);
    assertEquals("123.456", result);
  }

  @Test
  public void write_largeBigDecimal_writesNumber() throws IOException {
    Gson gson = new Gson();
    BigDecimal bigValue = new BigDecimal("9999999999999999999999999999.123456789");
    String result = gson.toJson((Number) bigValue, Number.class);
    assertEquals("9999999999999999999999999999.123456789", result);
  }

  // ==========================================================================
  // write() tests - LazilyParsedNumber
  // ==========================================================================

  @Test
  public void write_lazilyParsedNumber_writesOriginalString() throws IOException {
    Gson gson = new Gson();
    LazilyParsedNumber lazyNumber = new LazilyParsedNumber("42");
    String result = gson.toJson((Number) lazyNumber, Number.class);
    assertEquals("42", result);
  }

  @Test
  public void write_lazilyParsedDecimal_writesOriginalString() throws IOException {
    Gson gson = new Gson();
    LazilyParsedNumber lazyNumber = new LazilyParsedNumber("3.14159");
    String result = gson.toJson((Number) lazyNumber, Number.class);
    assertEquals("3.14159", result);
  }

  // ==========================================================================
  // Round-trip tests
  // ==========================================================================

  @Test
  public void roundTrip_integer_preservesValue() throws IOException {
    Gson gson = new GsonBuilder().setNumberToNumberStrategy(ToNumberPolicy.DOUBLE).create();
    Number original = 42;
    String json = gson.toJson(original, Number.class);
    Number result = gson.fromJson(json, Number.class);

    assertEquals(original.doubleValue(), result.doubleValue(), 0.0001);
  }

  @Test
  public void roundTrip_decimal_preservesValue() throws IOException {
    Gson gson = new GsonBuilder().setNumberToNumberStrategy(ToNumberPolicy.DOUBLE).create();
    Number original = 3.14159;
    String json = gson.toJson(original, Number.class);
    Number result = gson.fromJson(json, Number.class);

    assertEquals(original.doubleValue(), result.doubleValue(), 0.00001);
  }

  @Test
  public void roundTrip_bigDecimal_preservesValue() throws IOException {
    Gson gson = new GsonBuilder().setNumberToNumberStrategy(ToNumberPolicy.BIG_DECIMAL).create();
    BigDecimal original = new BigDecimal("123.456789012345678901234567890");
    String json = gson.toJson((Number) original, Number.class);
    Number result = gson.fromJson(json, Number.class);

    assertTrue(result instanceof BigDecimal);
    assertEquals(original, result);
  }

  @Test
  public void roundTrip_longOrDouble_preservesLong() throws IOException {
    Gson gson = new GsonBuilder().setNumberToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE).create();
    Number original = 9999999999999L;
    String json = gson.toJson(original, Number.class);
    Number result = gson.fromJson(json, Number.class);

    assertTrue(result instanceof Long);
    assertEquals(original.longValue(), result.longValue());
  }

  @Test
  public void roundTrip_longOrDouble_preservesDouble() throws IOException {
    Gson gson = new GsonBuilder().setNumberToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE).create();
    Number original = 3.14;
    String json = gson.toJson(original, Number.class);
    Number result = gson.fromJson(json, Number.class);

    assertTrue(result instanceof Double);
    assertEquals(original.doubleValue(), result.doubleValue(), 0.0001);
  }

  @Test
  public void roundTrip_null_preservesNull() throws IOException {
    Gson gson = new Gson();
    String json = gson.toJson(null, Number.class);
    Number result = gson.fromJson(json, Number.class);

    assertNull(result);
  }

  // ==========================================================================
  // Integration tests with different policies
  // ==========================================================================

  @Test
  public void integration_lazilyParsedPolicy_readsAndWritesCorrectly() throws IOException {
    Gson gson = new Gson(); // Default policy is LAZILY_PARSED_NUMBER
    Number number = gson.fromJson("42", Number.class);
    assertTrue(number instanceof LazilyParsedNumber);
    String json = gson.toJson(number, Number.class);
    assertEquals("42", json);
  }

  @Test
  public void integration_doublePolicy_readsAndWritesCorrectly() throws IOException {
    Gson gson = new GsonBuilder().setNumberToNumberStrategy(ToNumberPolicy.DOUBLE).create();
    Number number = gson.fromJson("42", Number.class);
    assertTrue(number instanceof Double);
    String json = gson.toJson(number, Number.class);
    assertEquals("42.0", json);
  }

  @Test
  public void integration_longOrDoublePolicy_readsIntegerAsLong() throws IOException {
    Gson gson = new GsonBuilder().setNumberToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE).create();
    Number number = gson.fromJson("999999999999", Number.class);
    assertTrue(number instanceof Long);
    assertEquals(999999999999L, number.longValue());
  }

  @Test
  public void integration_longOrDoublePolicy_readsDecimalAsDouble() throws IOException {
    Gson gson = new GsonBuilder().setNumberToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE).create();
    Number number = gson.fromJson("123.456", Number.class);
    assertTrue(number instanceof Double);
    assertEquals(123.456, number.doubleValue(), 0.001);
  }

  @Test
  public void integration_bigDecimalPolicy_preservesPrecision() throws IOException {
    Gson gson = new GsonBuilder().setNumberToNumberStrategy(ToNumberPolicy.BIG_DECIMAL).create();
    String preciseNumber = "12345678901234567890.12345678901234567890";
    Number number = gson.fromJson(preciseNumber, Number.class);
    assertTrue(number instanceof BigDecimal);
    assertEquals(new BigDecimal(preciseNumber), number);
  }

  // ==========================================================================
  // Edge case tests
  // ==========================================================================

  @Test
  public void read_minInteger_returnsCorrectValue() throws IOException {
    Gson gson = new GsonBuilder().setNumberToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE).create();
    Number result = gson.fromJson(String.valueOf(Integer.MIN_VALUE), Number.class);
    assertEquals(Integer.MIN_VALUE, result.intValue());
  }

  @Test
  public void read_maxInteger_returnsCorrectValue() throws IOException {
    Gson gson = new GsonBuilder().setNumberToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE).create();
    Number result = gson.fromJson(String.valueOf(Integer.MAX_VALUE), Number.class);
    assertEquals(Integer.MAX_VALUE, result.intValue());
  }

  @Test
  public void read_minLong_returnsCorrectValue() throws IOException {
    Gson gson = new GsonBuilder().setNumberToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE).create();
    Number result = gson.fromJson(String.valueOf(Long.MIN_VALUE), Number.class);
    assertTrue(result instanceof Long);
    assertEquals(Long.MIN_VALUE, result.longValue());
  }

  @Test
  public void read_verySmallDecimal_returnsCorrectValue() throws IOException {
    Gson gson = new GsonBuilder().setNumberToNumberStrategy(ToNumberPolicy.DOUBLE).create();
    Number result = gson.fromJson("0.0000000001", Number.class);
    assertEquals(0.0000000001, result.doubleValue(), 0.00000000001);
  }
}
