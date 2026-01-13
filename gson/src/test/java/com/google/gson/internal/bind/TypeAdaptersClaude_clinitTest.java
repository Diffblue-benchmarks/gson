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
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonPrimitive;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Currency;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.junit.Test;

/**
 * Tests for the static initializer of {@link TypeAdapters}.
 * These tests verify that all the static TypeAdapter and TypeAdapterFactory fields
 * are properly initialized by accessing and testing them.
 */
public class TypeAdaptersClaude_clinitTest {

  // ==========================================================================
  // CLASS adapter and factory tests
  // ==========================================================================

  @Test
  public void classAdapter_exists() {
    assertNotNull(TypeAdapters.CLASS);
    assertNotNull(TypeAdapters.CLASS_FACTORY);
  }

  @Test
  public void classAdapter_writeThrowsException() throws IOException {
    try {
      StringWriter sw = new StringWriter();
      JsonWriter writer = new JsonWriter(sw);
      TypeAdapters.CLASS.write(writer, String.class);
      fail("Expected UnsupportedOperationException");
    } catch (UnsupportedOperationException e) {
      assertTrue(e.getMessage().contains("Attempted to serialize java.lang.Class"));
    }
  }

  @Test
  public void classAdapter_readThrowsException() throws IOException {
    try {
      JsonReader reader = new JsonReader(new StringReader("\"java.lang.String\""));
      TypeAdapters.CLASS.read(reader);
      fail("Expected UnsupportedOperationException");
    } catch (UnsupportedOperationException e) {
      assertTrue(e.getMessage().contains("Attempted to deserialize a java.lang.Class"));
    }
  }

  @Test
  public void classAdapter_nullSafe() throws IOException {
    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    TypeAdapters.CLASS.write(writer, null);
    assertEquals("null", sw.toString());

    JsonReader reader = new JsonReader(new StringReader("null"));
    Class<?> result = TypeAdapters.CLASS.read(reader);
    assertNull(result);
  }

  // ==========================================================================
  // BIT_SET adapter and factory tests
  // ==========================================================================

  @Test
  public void bitSetAdapter_exists() {
    assertNotNull(TypeAdapters.BIT_SET);
    assertNotNull(TypeAdapters.BIT_SET_FACTORY);
  }

  @Test
  public void bitSetAdapter_write() throws IOException {
    BitSet bitSet = new BitSet();
    bitSet.set(0);
    bitSet.set(2);
    bitSet.set(4);

    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    TypeAdapters.BIT_SET.write(writer, bitSet);
    assertEquals("[1,0,1,0,1]", sw.toString());
  }

  @Test
  public void bitSetAdapter_read() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[1,0,1,0,1]"));
    BitSet bitSet = TypeAdapters.BIT_SET.read(reader);
    assertTrue(bitSet.get(0));
    assertTrue(!bitSet.get(1));
    assertTrue(bitSet.get(2));
    assertTrue(!bitSet.get(3));
    assertTrue(bitSet.get(4));
  }

  @Test
  public void bitSetAdapter_nullSafe() throws IOException {
    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    TypeAdapters.BIT_SET.write(writer, null);
    assertEquals("null", sw.toString());

    JsonReader reader = new JsonReader(new StringReader("null"));
    BitSet result = TypeAdapters.BIT_SET.read(reader);
    assertNull(result);
  }

  // ==========================================================================
  // BOOLEAN adapter and factory tests
  // ==========================================================================

  @Test
  public void booleanAdapter_exists() {
    assertNotNull(TypeAdapters.BOOLEAN);
    assertNotNull(TypeAdapters.BOOLEAN_FACTORY);
  }

  @Test
  public void booleanAdapter_readWrite() throws IOException {
    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    TypeAdapters.BOOLEAN.write(writer, true);
    assertEquals("true", sw.toString());

    JsonReader reader = new JsonReader(new StringReader("false"));
    Boolean result = TypeAdapters.BOOLEAN.read(reader);
    assertEquals(Boolean.FALSE, result);
  }

  @Test
  public void booleanAsStringAdapter_exists() {
    assertNotNull(TypeAdapters.BOOLEAN_AS_STRING);
  }

  @Test
  public void booleanAsStringAdapter_readWrite() throws IOException {
    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    TypeAdapters.BOOLEAN_AS_STRING.write(writer, true);
    assertEquals("\"true\"", sw.toString());

    JsonReader reader = new JsonReader(new StringReader("\"true\""));
    Boolean result = TypeAdapters.BOOLEAN_AS_STRING.read(reader);
    assertEquals(Boolean.TRUE, result);
  }

  // ==========================================================================
  // BYTE adapter and factory tests
  // ==========================================================================

  @Test
  public void byteAdapter_exists() {
    assertNotNull(TypeAdapters.BYTE);
    assertNotNull(TypeAdapters.BYTE_FACTORY);
  }

  @Test
  public void byteAdapter_readWrite() throws IOException {
    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    TypeAdapters.BYTE.write(writer, (byte) 42);
    assertEquals("42", sw.toString());

    JsonReader reader = new JsonReader(new StringReader("42"));
    Number result = TypeAdapters.BYTE.read(reader);
    assertEquals((byte) 42, result.byteValue());
  }

  // ==========================================================================
  // SHORT adapter and factory tests
  // ==========================================================================

  @Test
  public void shortAdapter_exists() {
    assertNotNull(TypeAdapters.SHORT);
    assertNotNull(TypeAdapters.SHORT_FACTORY);
  }

  @Test
  public void shortAdapter_readWrite() throws IOException {
    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    TypeAdapters.SHORT.write(writer, (short) 1000);
    assertEquals("1000", sw.toString());

    JsonReader reader = new JsonReader(new StringReader("1000"));
    Number result = TypeAdapters.SHORT.read(reader);
    assertEquals((short) 1000, result.shortValue());
  }

  // ==========================================================================
  // INTEGER adapter and factory tests
  // ==========================================================================

  @Test
  public void integerAdapter_exists() {
    assertNotNull(TypeAdapters.INTEGER);
    assertNotNull(TypeAdapters.INTEGER_FACTORY);
  }

  @Test
  public void integerAdapter_readWrite() throws IOException {
    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    TypeAdapters.INTEGER.write(writer, 12345);
    assertEquals("12345", sw.toString());

    JsonReader reader = new JsonReader(new StringReader("12345"));
    Number result = TypeAdapters.INTEGER.read(reader);
    assertEquals(12345, result.intValue());
  }

  // ==========================================================================
  // ATOMIC_INTEGER adapter and factory tests
  // ==========================================================================

  @Test
  public void atomicIntegerAdapter_exists() {
    assertNotNull(TypeAdapters.ATOMIC_INTEGER);
    assertNotNull(TypeAdapters.ATOMIC_INTEGER_FACTORY);
  }

  @Test
  public void atomicIntegerAdapter_readWrite() throws IOException {
    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    TypeAdapters.ATOMIC_INTEGER.write(writer, new AtomicInteger(99));
    assertEquals("99", sw.toString());

    JsonReader reader = new JsonReader(new StringReader("99"));
    AtomicInteger result = TypeAdapters.ATOMIC_INTEGER.read(reader);
    assertEquals(99, result.get());
  }

  @Test
  public void atomicIntegerAdapter_nullSafe() throws IOException {
    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    TypeAdapters.ATOMIC_INTEGER.write(writer, null);
    assertEquals("null", sw.toString());

    JsonReader reader = new JsonReader(new StringReader("null"));
    AtomicInteger result = TypeAdapters.ATOMIC_INTEGER.read(reader);
    assertNull(result);
  }

  // ==========================================================================
  // ATOMIC_BOOLEAN adapter and factory tests
  // ==========================================================================

  @Test
  public void atomicBooleanAdapter_exists() {
    assertNotNull(TypeAdapters.ATOMIC_BOOLEAN);
    assertNotNull(TypeAdapters.ATOMIC_BOOLEAN_FACTORY);
  }

  @Test
  public void atomicBooleanAdapter_readWrite() throws IOException {
    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    TypeAdapters.ATOMIC_BOOLEAN.write(writer, new AtomicBoolean(true));
    assertEquals("true", sw.toString());

    JsonReader reader = new JsonReader(new StringReader("true"));
    AtomicBoolean result = TypeAdapters.ATOMIC_BOOLEAN.read(reader);
    assertTrue(result.get());
  }

  @Test
  public void atomicBooleanAdapter_nullSafe() throws IOException {
    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    TypeAdapters.ATOMIC_BOOLEAN.write(writer, null);
    assertEquals("null", sw.toString());

    JsonReader reader = new JsonReader(new StringReader("null"));
    AtomicBoolean result = TypeAdapters.ATOMIC_BOOLEAN.read(reader);
    assertNull(result);
  }

  // ==========================================================================
  // ATOMIC_INTEGER_ARRAY adapter and factory tests
  // ==========================================================================

  @Test
  public void atomicIntegerArrayAdapter_exists() {
    assertNotNull(TypeAdapters.ATOMIC_INTEGER_ARRAY);
    assertNotNull(TypeAdapters.ATOMIC_INTEGER_ARRAY_FACTORY);
  }

  @Test
  public void atomicIntegerArrayAdapter_readWrite() throws IOException {
    AtomicIntegerArray array = new AtomicIntegerArray(new int[] {1, 2, 3});

    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    TypeAdapters.ATOMIC_INTEGER_ARRAY.write(writer, array);
    assertEquals("[1,2,3]", sw.toString());

    JsonReader reader = new JsonReader(new StringReader("[1,2,3]"));
    AtomicIntegerArray result = TypeAdapters.ATOMIC_INTEGER_ARRAY.read(reader);
    assertEquals(3, result.length());
    assertEquals(1, result.get(0));
    assertEquals(2, result.get(1));
    assertEquals(3, result.get(2));
  }

  @Test
  public void atomicIntegerArrayAdapter_nullSafe() throws IOException {
    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    TypeAdapters.ATOMIC_INTEGER_ARRAY.write(writer, null);
    assertEquals("null", sw.toString());

    JsonReader reader = new JsonReader(new StringReader("null"));
    AtomicIntegerArray result = TypeAdapters.ATOMIC_INTEGER_ARRAY.read(reader);
    assertNull(result);
  }

  // ==========================================================================
  // LONG adapter tests
  // ==========================================================================

  @Test
  public void longAdapter_exists() {
    assertNotNull(TypeAdapters.LONG);
  }

  @Test
  public void longAdapter_readWrite() throws IOException {
    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    TypeAdapters.LONG.write(writer, 9876543210L);
    assertEquals("9876543210", sw.toString());

    JsonReader reader = new JsonReader(new StringReader("9876543210"));
    Number result = TypeAdapters.LONG.read(reader);
    assertEquals(9876543210L, result.longValue());
  }

  // ==========================================================================
  // FLOAT adapter tests
  // ==========================================================================

  @Test
  public void floatAdapter_exists() {
    assertNotNull(TypeAdapters.FLOAT);
  }

  @Test
  public void floatAdapter_readWrite() throws IOException {
    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    TypeAdapters.FLOAT.write(writer, 3.14f);
    assertTrue(sw.toString().startsWith("3.14"));

    JsonReader reader = new JsonReader(new StringReader("3.14"));
    Number result = TypeAdapters.FLOAT.read(reader);
    assertEquals(3.14f, result.floatValue(), 0.001f);
  }

  // ==========================================================================
  // DOUBLE adapter tests
  // ==========================================================================

  @Test
  public void doubleAdapter_exists() {
    assertNotNull(TypeAdapters.DOUBLE);
  }

  @Test
  public void doubleAdapter_readWrite() throws IOException {
    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    TypeAdapters.DOUBLE.write(writer, 3.14159265);
    assertEquals("3.14159265", sw.toString());

    JsonReader reader = new JsonReader(new StringReader("3.14159265"));
    Number result = TypeAdapters.DOUBLE.read(reader);
    assertEquals(3.14159265, result.doubleValue(), 0.00000001);
  }

  // ==========================================================================
  // CHARACTER adapter and factory tests
  // ==========================================================================

  @Test
  public void characterAdapter_exists() {
    assertNotNull(TypeAdapters.CHARACTER);
    assertNotNull(TypeAdapters.CHARACTER_FACTORY);
  }

  @Test
  public void characterAdapter_readWrite() throws IOException {
    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    TypeAdapters.CHARACTER.write(writer, 'A');
    assertEquals("\"A\"", sw.toString());

    JsonReader reader = new JsonReader(new StringReader("\"A\""));
    Character result = TypeAdapters.CHARACTER.read(reader);
    assertEquals(Character.valueOf('A'), result);
  }

  // ==========================================================================
  // STRING adapter and factory tests
  // ==========================================================================

  @Test
  public void stringAdapter_exists() {
    assertNotNull(TypeAdapters.STRING);
    assertNotNull(TypeAdapters.STRING_FACTORY);
  }

  @Test
  public void stringAdapter_readWrite() throws IOException {
    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    TypeAdapters.STRING.write(writer, "hello");
    assertEquals("\"hello\"", sw.toString());

    JsonReader reader = new JsonReader(new StringReader("\"hello\""));
    String result = TypeAdapters.STRING.read(reader);
    assertEquals("hello", result);
  }

  // ==========================================================================
  // BIG_DECIMAL adapter tests
  // ==========================================================================

  @Test
  public void bigDecimalAdapter_exists() {
    assertNotNull(TypeAdapters.BIG_DECIMAL);
  }

  @Test
  public void bigDecimalAdapter_readWrite() throws IOException {
    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    TypeAdapters.BIG_DECIMAL.write(writer, new BigDecimal("123.456"));
    assertEquals("123.456", sw.toString());

    JsonReader reader = new JsonReader(new StringReader("\"123.456\""));
    BigDecimal result = TypeAdapters.BIG_DECIMAL.read(reader);
    assertEquals(new BigDecimal("123.456"), result);
  }

  // ==========================================================================
  // BIG_INTEGER adapter tests
  // ==========================================================================

  @Test
  public void bigIntegerAdapter_exists() {
    assertNotNull(TypeAdapters.BIG_INTEGER);
  }

  @Test
  public void bigIntegerAdapter_readWrite() throws IOException {
    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    TypeAdapters.BIG_INTEGER.write(writer, new BigInteger("123456789012345678901234567890"));
    assertEquals("123456789012345678901234567890", sw.toString());

    JsonReader reader = new JsonReader(new StringReader("\"123456789012345678901234567890\""));
    BigInteger result = TypeAdapters.BIG_INTEGER.read(reader);
    assertEquals(new BigInteger("123456789012345678901234567890"), result);
  }

  // ==========================================================================
  // LAZILY_PARSED_NUMBER adapter tests
  // ==========================================================================

  @Test
  public void lazilyParsedNumberAdapter_exists() {
    assertNotNull(TypeAdapters.LAZILY_PARSED_NUMBER);
  }

  // ==========================================================================
  // STRING_BUILDER adapter and factory tests
  // ==========================================================================

  @Test
  public void stringBuilderAdapter_exists() {
    assertNotNull(TypeAdapters.STRING_BUILDER);
    assertNotNull(TypeAdapters.STRING_BUILDER_FACTORY);
  }

  @Test
  public void stringBuilderAdapter_readWrite() throws IOException {
    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    TypeAdapters.STRING_BUILDER.write(writer, new StringBuilder("test"));
    assertEquals("\"test\"", sw.toString());

    JsonReader reader = new JsonReader(new StringReader("\"test\""));
    StringBuilder result = TypeAdapters.STRING_BUILDER.read(reader);
    assertEquals("test", result.toString());
  }

  // ==========================================================================
  // STRING_BUFFER adapter and factory tests
  // ==========================================================================

  @Test
  public void stringBufferAdapter_exists() {
    assertNotNull(TypeAdapters.STRING_BUFFER);
    assertNotNull(TypeAdapters.STRING_BUFFER_FACTORY);
  }

  @Test
  public void stringBufferAdapter_readWrite() throws IOException {
    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    TypeAdapters.STRING_BUFFER.write(writer, new StringBuffer("test"));
    assertEquals("\"test\"", sw.toString());

    JsonReader reader = new JsonReader(new StringReader("\"test\""));
    StringBuffer result = TypeAdapters.STRING_BUFFER.read(reader);
    assertEquals("test", result.toString());
  }

  // ==========================================================================
  // URL adapter and factory tests
  // ==========================================================================

  @Test
  public void urlAdapter_exists() {
    assertNotNull(TypeAdapters.URL);
    assertNotNull(TypeAdapters.URL_FACTORY);
  }

  @Test
  public void urlAdapter_readWrite() throws Exception {
    URL url = new URL("https://example.com/path");

    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    TypeAdapters.URL.write(writer, url);
    assertEquals("\"https://example.com/path\"", sw.toString());

    JsonReader reader = new JsonReader(new StringReader("\"https://example.com/path\""));
    URL result = TypeAdapters.URL.read(reader);
    assertEquals(url, result);
  }

  // ==========================================================================
  // URI adapter and factory tests
  // ==========================================================================

  @Test
  public void uriAdapter_exists() {
    assertNotNull(TypeAdapters.URI);
    assertNotNull(TypeAdapters.URI_FACTORY);
  }

  @Test
  public void uriAdapter_readWrite() throws Exception {
    URI uri = new URI("https://example.com/path");

    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    TypeAdapters.URI.write(writer, uri);
    assertEquals("\"https://example.com/path\"", sw.toString());

    JsonReader reader = new JsonReader(new StringReader("\"https://example.com/path\""));
    URI result = TypeAdapters.URI.read(reader);
    assertEquals(uri, result);
  }

  // ==========================================================================
  // INET_ADDRESS adapter and factory tests
  // ==========================================================================

  @Test
  public void inetAddressAdapter_exists() {
    assertNotNull(TypeAdapters.INET_ADDRESS);
    assertNotNull(TypeAdapters.INET_ADDRESS_FACTORY);
  }

  @Test
  public void inetAddressAdapter_readWrite() throws Exception {
    InetAddress address = InetAddress.getByName("127.0.0.1");

    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    TypeAdapters.INET_ADDRESS.write(writer, address);
    assertEquals("\"127.0.0.1\"", sw.toString());

    JsonReader reader = new JsonReader(new StringReader("\"127.0.0.1\""));
    InetAddress result = TypeAdapters.INET_ADDRESS.read(reader);
    assertEquals(address, result);
  }

  // ==========================================================================
  // UUID adapter and factory tests
  // ==========================================================================

  @Test
  public void uuidAdapter_exists() {
    assertNotNull(TypeAdapters.UUID);
    assertNotNull(TypeAdapters.UUID_FACTORY);
  }

  @Test
  public void uuidAdapter_readWrite() throws IOException {
    UUID uuid = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");

    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    TypeAdapters.UUID.write(writer, uuid);
    assertEquals("\"550e8400-e29b-41d4-a716-446655440000\"", sw.toString());

    JsonReader reader = new JsonReader(new StringReader("\"550e8400-e29b-41d4-a716-446655440000\""));
    UUID result = TypeAdapters.UUID.read(reader);
    assertEquals(uuid, result);
  }

  // ==========================================================================
  // CURRENCY adapter and factory tests
  // ==========================================================================

  @Test
  public void currencyAdapter_exists() {
    assertNotNull(TypeAdapters.CURRENCY);
    assertNotNull(TypeAdapters.CURRENCY_FACTORY);
  }

  @Test
  public void currencyAdapter_readWrite() throws IOException {
    Currency currency = Currency.getInstance("USD");

    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    TypeAdapters.CURRENCY.write(writer, currency);
    assertEquals("\"USD\"", sw.toString());

    JsonReader reader = new JsonReader(new StringReader("\"USD\""));
    Currency result = TypeAdapters.CURRENCY.read(reader);
    assertEquals(currency, result);
  }

  @Test
  public void currencyAdapter_nullSafe() throws IOException {
    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    TypeAdapters.CURRENCY.write(writer, null);
    assertEquals("null", sw.toString());

    JsonReader reader = new JsonReader(new StringReader("null"));
    Currency result = TypeAdapters.CURRENCY.read(reader);
    assertNull(result);
  }

  // ==========================================================================
  // CALENDAR adapter and factory tests
  // ==========================================================================

  @Test
  public void calendarAdapter_exists() {
    assertNotNull(TypeAdapters.CALENDAR);
    assertNotNull(TypeAdapters.CALENDAR_FACTORY);
  }

  @Test
  public void calendarAdapter_readWrite() throws IOException {
    Calendar calendar = new GregorianCalendar(2024, Calendar.JANUARY, 15, 10, 30, 45);

    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    TypeAdapters.CALENDAR.write(writer, calendar);
    String json = sw.toString();
    assertTrue(json.contains("\"year\":2024"));
    assertTrue(json.contains("\"month\":0")); // January is 0
    assertTrue(json.contains("\"dayOfMonth\":15"));
    assertTrue(json.contains("\"hourOfDay\":10"));
    assertTrue(json.contains("\"minute\":30"));
    assertTrue(json.contains("\"second\":45"));

    JsonReader reader = new JsonReader(new StringReader(json));
    Calendar result = TypeAdapters.CALENDAR.read(reader);
    assertEquals(2024, result.get(Calendar.YEAR));
    assertEquals(Calendar.JANUARY, result.get(Calendar.MONTH));
    assertEquals(15, result.get(Calendar.DAY_OF_MONTH));
    assertEquals(10, result.get(Calendar.HOUR_OF_DAY));
    assertEquals(30, result.get(Calendar.MINUTE));
    assertEquals(45, result.get(Calendar.SECOND));
  }

  // ==========================================================================
  // LOCALE adapter and factory tests
  // ==========================================================================

  @Test
  public void localeAdapter_exists() {
    assertNotNull(TypeAdapters.LOCALE);
    assertNotNull(TypeAdapters.LOCALE_FACTORY);
  }

  @Test
  public void localeAdapter_readWrite() throws IOException {
    Locale locale = Locale.US;

    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    TypeAdapters.LOCALE.write(writer, locale);
    assertEquals("\"en_US\"", sw.toString());

    JsonReader reader = new JsonReader(new StringReader("\"en_US\""));
    Locale result = TypeAdapters.LOCALE.read(reader);
    assertEquals(locale, result);
  }

  // ==========================================================================
  // JSON_ELEMENT adapter and factory tests
  // ==========================================================================

  @Test
  public void jsonElementAdapter_exists() {
    assertNotNull(TypeAdapters.JSON_ELEMENT);
    assertNotNull(TypeAdapters.JSON_ELEMENT_FACTORY);
  }

  @Test
  public void jsonElementAdapter_readWrite() throws IOException {
    JsonElement element = new JsonPrimitive("test");

    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    TypeAdapters.JSON_ELEMENT.write(writer, element);
    assertEquals("\"test\"", sw.toString());

    JsonReader reader = new JsonReader(new StringReader("\"test\""));
    JsonElement result = TypeAdapters.JSON_ELEMENT.read(reader);
    assertEquals(element, result);
  }

  // ==========================================================================
  // ENUM_FACTORY tests
  // ==========================================================================

  @Test
  public void enumFactory_exists() {
    assertNotNull(TypeAdapters.ENUM_FACTORY);
  }

  // ==========================================================================
  // Factory creation via Gson integration tests
  // ==========================================================================

  @Test
  public void factoriesCreateAdaptersViaGson() {
    Gson gson = new Gson();

    // Verify factories can create adapters through Gson
    assertNotNull(TypeAdapters.CLASS_FACTORY.create(gson, TypeToken.get(Class.class)));
    assertNotNull(TypeAdapters.BIT_SET_FACTORY.create(gson, TypeToken.get(BitSet.class)));
    assertNotNull(TypeAdapters.BOOLEAN_FACTORY.create(gson, TypeToken.get(Boolean.class)));
    assertNotNull(TypeAdapters.BOOLEAN_FACTORY.create(gson, TypeToken.get(boolean.class)));
    assertNotNull(TypeAdapters.BYTE_FACTORY.create(gson, TypeToken.get(Byte.class)));
    assertNotNull(TypeAdapters.BYTE_FACTORY.create(gson, TypeToken.get(byte.class)));
    assertNotNull(TypeAdapters.SHORT_FACTORY.create(gson, TypeToken.get(Short.class)));
    assertNotNull(TypeAdapters.SHORT_FACTORY.create(gson, TypeToken.get(short.class)));
    assertNotNull(TypeAdapters.INTEGER_FACTORY.create(gson, TypeToken.get(Integer.class)));
    assertNotNull(TypeAdapters.INTEGER_FACTORY.create(gson, TypeToken.get(int.class)));
    assertNotNull(TypeAdapters.ATOMIC_INTEGER_FACTORY.create(gson, TypeToken.get(AtomicInteger.class)));
    assertNotNull(TypeAdapters.ATOMIC_BOOLEAN_FACTORY.create(gson, TypeToken.get(AtomicBoolean.class)));
    assertNotNull(TypeAdapters.ATOMIC_INTEGER_ARRAY_FACTORY.create(gson, TypeToken.get(AtomicIntegerArray.class)));
    assertNotNull(TypeAdapters.CHARACTER_FACTORY.create(gson, TypeToken.get(Character.class)));
    assertNotNull(TypeAdapters.CHARACTER_FACTORY.create(gson, TypeToken.get(char.class)));
    assertNotNull(TypeAdapters.STRING_FACTORY.create(gson, TypeToken.get(String.class)));
    assertNotNull(TypeAdapters.STRING_BUILDER_FACTORY.create(gson, TypeToken.get(StringBuilder.class)));
    assertNotNull(TypeAdapters.STRING_BUFFER_FACTORY.create(gson, TypeToken.get(StringBuffer.class)));
    assertNotNull(TypeAdapters.URL_FACTORY.create(gson, TypeToken.get(URL.class)));
    assertNotNull(TypeAdapters.URI_FACTORY.create(gson, TypeToken.get(URI.class)));
    assertNotNull(TypeAdapters.INET_ADDRESS_FACTORY.create(gson, TypeToken.get(InetAddress.class)));
    assertNotNull(TypeAdapters.UUID_FACTORY.create(gson, TypeToken.get(UUID.class)));
    assertNotNull(TypeAdapters.CURRENCY_FACTORY.create(gson, TypeToken.get(Currency.class)));
    assertNotNull(TypeAdapters.CALENDAR_FACTORY.create(gson, TypeToken.get(Calendar.class)));
    assertNotNull(TypeAdapters.CALENDAR_FACTORY.create(gson, TypeToken.get(GregorianCalendar.class)));
    assertNotNull(TypeAdapters.LOCALE_FACTORY.create(gson, TypeToken.get(Locale.class)));
    assertNotNull(TypeAdapters.JSON_ELEMENT_FACTORY.create(gson, TypeToken.get(JsonElement.class)));
  }

  // ==========================================================================
  // Factory returns null for non-matching types
  // ==========================================================================

  @Test
  public void factoriesReturnNullForNonMatchingTypes() {
    Gson gson = new Gson();

    // These factories should return null for non-matching types
    assertNull(TypeAdapters.CLASS_FACTORY.create(gson, TypeToken.get(String.class)));
    assertNull(TypeAdapters.BIT_SET_FACTORY.create(gson, TypeToken.get(String.class)));
    assertNull(TypeAdapters.BOOLEAN_FACTORY.create(gson, TypeToken.get(String.class)));
    assertNull(TypeAdapters.BYTE_FACTORY.create(gson, TypeToken.get(String.class)));
    assertNull(TypeAdapters.SHORT_FACTORY.create(gson, TypeToken.get(String.class)));
    assertNull(TypeAdapters.INTEGER_FACTORY.create(gson, TypeToken.get(String.class)));
    assertNull(TypeAdapters.STRING_FACTORY.create(gson, TypeToken.get(Integer.class)));
    assertNull(TypeAdapters.URL_FACTORY.create(gson, TypeToken.get(String.class)));
    assertNull(TypeAdapters.URI_FACTORY.create(gson, TypeToken.get(String.class)));
    assertNull(TypeAdapters.UUID_FACTORY.create(gson, TypeToken.get(String.class)));
  }
}
