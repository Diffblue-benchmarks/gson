/*
 * Copyright (C) 2008 Google Inc.
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
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.bind.DefaultDateTypeAdapter.DateType;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.junit.Test;

/** Tests for {@link DefaultDateTypeAdapter}. */
public class DefaultDateTypeAdapterClaudeTest {

  // ==========================================================================
  // Factory creation tests
  // ==========================================================================

  @Test
  public void factory_defaultStyleFactory_createsAdapterForDate() {
    Gson gson = new GsonBuilder()
        .registerTypeAdapterFactory(DefaultDateTypeAdapter.DEFAULT_STYLE_FACTORY)
        .create();
    TypeAdapter<Date> adapter = gson.getAdapter(Date.class);
    assertNotNull(adapter);
  }

  @Test
  public void factory_defaultStyleFactory_returnsNullForNonDateType() {
    Gson gson = new Gson();
    TypeAdapterFactory factory = DefaultDateTypeAdapter.DEFAULT_STYLE_FACTORY;
    TypeAdapter<String> adapter = factory.create(gson, com.google.gson.reflect.TypeToken.get(String.class));
    assertNull(adapter);
  }

  @Test
  public void factory_defaultStyleFactory_toStringIsDescriptive() {
    String str = DefaultDateTypeAdapter.DEFAULT_STYLE_FACTORY.toString();
    assertEquals("DefaultDateTypeAdapter#DEFAULT_STYLE_FACTORY", str);
  }

  @Test
  public void factory_createWithPattern_createsAdapter() {
    TypeAdapterFactory factory = DateType.DATE.createAdapterFactory("yyyy-MM-dd");
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
    TypeAdapter<Date> adapter = gson.getAdapter(Date.class);
    assertNotNull(adapter);
  }

  @Test
  public void factory_createWithStyleParams_createsAdapter() {
    TypeAdapterFactory factory = DateType.DATE.createAdapterFactory(DateFormat.SHORT, DateFormat.SHORT);
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
    TypeAdapter<Date> adapter = gson.getAdapter(Date.class);
    assertNotNull(adapter);
  }

  @Test
  public void factory_createWithPattern_returnsNullForNonDateType() {
    TypeAdapterFactory factory = DateType.DATE.createAdapterFactory("yyyy-MM-dd");
    Gson gson = new Gson();
    TypeAdapter<String> adapter = factory.create(gson, com.google.gson.reflect.TypeToken.get(String.class));
    assertNull(adapter);
  }

  @Test
  public void factory_createWithStyleParams_returnsNullForNonDateType() {
    TypeAdapterFactory factory = DateType.DATE.createAdapterFactory(DateFormat.SHORT, DateFormat.SHORT);
    Gson gson = new Gson();
    TypeAdapter<String> adapter = factory.create(gson, com.google.gson.reflect.TypeToken.get(String.class));
    assertNull(adapter);
  }

  @Test
  public void factory_createWithMixedStyles_shortDateLongTime_createsAdapter() throws IOException {
    TypeAdapterFactory factory = DateType.DATE.createAdapterFactory(DateFormat.SHORT, DateFormat.LONG);
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
    TypeAdapter<Date> adapter = gson.getAdapter(Date.class);
    assertNotNull(adapter);

    // Verify it can serialize a date
    Calendar cal = Calendar.getInstance();
    cal.set(2023, Calendar.JUNE, 15, 14, 30, 45);
    cal.set(Calendar.MILLISECOND, 0);
    Date date = cal.getTime();

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, date);

    String result = stringWriter.toString();
    assertTrue(result.startsWith("\""));
    assertTrue(result.endsWith("\""));
  }

  @Test
  public void factory_createWithMixedStyles_longDateShortTime_createsAdapter() throws IOException {
    TypeAdapterFactory factory = DateType.DATE.createAdapterFactory(DateFormat.LONG, DateFormat.SHORT);
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
    TypeAdapter<Date> adapter = gson.getAdapter(Date.class);
    assertNotNull(adapter);

    // Verify it can serialize a date
    Calendar cal = Calendar.getInstance();
    cal.set(2023, Calendar.JUNE, 15, 14, 30, 45);
    cal.set(Calendar.MILLISECOND, 0);
    Date date = cal.getTime();

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, date);

    String result = stringWriter.toString();
    assertTrue(result.startsWith("\""));
    assertTrue(result.endsWith("\""));
  }

  @Test
  public void factory_createWithPattern_factoryToStringIncludesAdapterInfo() {
    TypeAdapterFactory factory = DateType.DATE.createAdapterFactory("yyyy-MM-dd");
    String str = factory.toString();
    // Factory wraps the adapter via TypeAdapters.newFactory
    assertNotNull(str);
    assertTrue(str.contains("Date"));
  }

  @Test
  public void factory_createWithStyleParams_factoryToStringIncludesAdapterInfo() {
    TypeAdapterFactory factory = DateType.DATE.createAdapterFactory(DateFormat.SHORT, DateFormat.SHORT);
    String str = factory.toString();
    // Factory wraps the adapter via TypeAdapters.newFactory
    assertNotNull(str);
    assertTrue(str.contains("Date"));
  }

  // ==========================================================================
  // write() tests - null handling
  // ==========================================================================

  @Test
  public void write_nullDate_writesNull() throws IOException {
    TypeAdapterFactory factory = DateType.DATE.createAdapterFactory("yyyy-MM-dd");
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
    TypeAdapter<Date> adapter = gson.getAdapter(Date.class);

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, null);
    assertEquals("null", stringWriter.toString());
  }

  @Test
  public void write_nullDateWithDefaultStyle_writesNull() throws IOException {
    Gson gson = new GsonBuilder()
        .registerTypeAdapterFactory(DefaultDateTypeAdapter.DEFAULT_STYLE_FACTORY)
        .create();
    TypeAdapter<Date> adapter = gson.getAdapter(Date.class);

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, null);
    assertEquals("null", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - with pattern
  // ==========================================================================

  @Test
  public void write_dateWithSimplePattern_writesFormattedDate() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

      TypeAdapterFactory factory = DateType.DATE.createAdapterFactory("yyyy-MM-dd");
      Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
      TypeAdapter<Date> adapter = gson.getAdapter(Date.class);

      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      cal.set(2023, Calendar.JUNE, 15, 0, 0, 0);
      cal.set(Calendar.MILLISECOND, 0);
      Date date = cal.getTime();

      StringWriter stringWriter = new StringWriter();
      JsonWriter writer = new JsonWriter(stringWriter);
      adapter.write(writer, date);
      assertEquals("\"2023-06-15\"", stringWriter.toString());
    } finally {
      TimeZone.setDefault(originalTimeZone);
    }
  }

  @Test
  public void write_dateWithDateTimePattern_writesFormattedDateTime() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

      TypeAdapterFactory factory = DateType.DATE.createAdapterFactory("yyyy-MM-dd HH:mm:ss");
      Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
      TypeAdapter<Date> adapter = gson.getAdapter(Date.class);

      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      cal.set(2023, Calendar.JUNE, 15, 14, 30, 45);
      cal.set(Calendar.MILLISECOND, 0);
      Date date = cal.getTime();

      StringWriter stringWriter = new StringWriter();
      JsonWriter writer = new JsonWriter(stringWriter);
      adapter.write(writer, date);
      assertEquals("\"2023-06-15 14:30:45\"", stringWriter.toString());
    } finally {
      TimeZone.setDefault(originalTimeZone);
    }
  }

  // ==========================================================================
  // read() tests - null handling
  // ==========================================================================

  @Test
  public void read_nullJson_returnsNull() throws IOException {
    TypeAdapterFactory factory = DateType.DATE.createAdapterFactory("yyyy-MM-dd");
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
    TypeAdapter<Date> adapter = gson.getAdapter(Date.class);

    JsonReader reader = new JsonReader(new StringReader("null"));
    Date result = adapter.read(reader);
    assertNull(result);
  }

  @Test
  public void read_nullJsonWithDefaultStyle_returnsNull() throws IOException {
    Gson gson = new GsonBuilder()
        .registerTypeAdapterFactory(DefaultDateTypeAdapter.DEFAULT_STYLE_FACTORY)
        .create();
    TypeAdapter<Date> adapter = gson.getAdapter(Date.class);

    JsonReader reader = new JsonReader(new StringReader("null"));
    Date result = adapter.read(reader);
    assertNull(result);
  }

  // ==========================================================================
  // read() tests - valid date strings
  // ==========================================================================

  @Test
  public void read_dateWithSimplePattern_parsesCorrectly() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

      TypeAdapterFactory factory = DateType.DATE.createAdapterFactory("yyyy-MM-dd");
      Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
      TypeAdapter<Date> adapter = gson.getAdapter(Date.class);

      JsonReader reader = new JsonReader(new StringReader("\"2023-06-15\""));
      Date result = adapter.read(reader);

      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      cal.setTime(result);
      assertEquals(2023, cal.get(Calendar.YEAR));
      assertEquals(Calendar.JUNE, cal.get(Calendar.MONTH));
      assertEquals(15, cal.get(Calendar.DAY_OF_MONTH));
    } finally {
      TimeZone.setDefault(originalTimeZone);
    }
  }

  @Test
  public void read_dateWithDateTimePattern_parsesCorrectly() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

      TypeAdapterFactory factory = DateType.DATE.createAdapterFactory("yyyy-MM-dd HH:mm:ss");
      Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
      TypeAdapter<Date> adapter = gson.getAdapter(Date.class);

      JsonReader reader = new JsonReader(new StringReader("\"2023-06-15 14:30:45\""));
      Date result = adapter.read(reader);

      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      cal.setTime(result);
      assertEquals(2023, cal.get(Calendar.YEAR));
      assertEquals(Calendar.JUNE, cal.get(Calendar.MONTH));
      assertEquals(15, cal.get(Calendar.DAY_OF_MONTH));
      assertEquals(14, cal.get(Calendar.HOUR_OF_DAY));
      assertEquals(30, cal.get(Calendar.MINUTE));
      assertEquals(45, cal.get(Calendar.SECOND));
    } finally {
      TimeZone.setDefault(originalTimeZone);
    }
  }

  // ==========================================================================
  // read() tests - ISO8601 fallback
  // ==========================================================================

  @Test
  public void read_iso8601Date_fallsBackToIso8601Parser() throws IOException {
    // Use a pattern that won't match ISO8601 format so the fallback is used
    TypeAdapterFactory factory = DateType.DATE.createAdapterFactory("dd/MM/yyyy");
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
    TypeAdapter<Date> adapter = gson.getAdapter(Date.class);

    JsonReader reader = new JsonReader(new StringReader("\"2023-06-15T14:30:45Z\""));
    Date result = adapter.read(reader);

    assertNotNull(result);
    Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
    cal.setTime(result);
    assertEquals(2023, cal.get(Calendar.YEAR));
    assertEquals(Calendar.JUNE, cal.get(Calendar.MONTH));
    assertEquals(15, cal.get(Calendar.DAY_OF_MONTH));
  }

  @Test
  public void read_iso8601DateWithTimezone_parsesCorrectly() throws IOException {
    TypeAdapterFactory factory = DateType.DATE.createAdapterFactory("dd/MM/yyyy");
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
    TypeAdapter<Date> adapter = gson.getAdapter(Date.class);

    JsonReader reader = new JsonReader(new StringReader("\"2023-06-15T14:30:45+00:00\""));
    Date result = adapter.read(reader);

    assertNotNull(result);
    Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
    cal.setTime(result);
    assertEquals(2023, cal.get(Calendar.YEAR));
  }

  // ==========================================================================
  // read() tests - invalid date strings
  // ==========================================================================

  @Test
  public void read_invalidDateString_throwsJsonSyntaxException() throws IOException {
    TypeAdapterFactory factory = DateType.DATE.createAdapterFactory("yyyy-MM-dd");
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
    TypeAdapter<Date> adapter = gson.getAdapter(Date.class);

    JsonReader reader = new JsonReader(new StringReader("\"not-a-date\""));
    try {
      adapter.read(reader);
      fail("Expected JsonSyntaxException");
    } catch (JsonSyntaxException e) {
      assertTrue(e.getMessage().contains("Failed parsing"));
      assertTrue(e.getMessage().contains("not-a-date"));
    }
  }

  @Test
  public void read_emptyString_throwsJsonSyntaxException() throws IOException {
    TypeAdapterFactory factory = DateType.DATE.createAdapterFactory("yyyy-MM-dd");
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
    TypeAdapter<Date> adapter = gson.getAdapter(Date.class);

    JsonReader reader = new JsonReader(new StringReader("\"\""));
    try {
      adapter.read(reader);
      fail("Expected JsonSyntaxException");
    } catch (JsonSyntaxException e) {
      assertTrue(e.getMessage().contains("Failed parsing"));
    }
  }

  // ==========================================================================
  // toString() tests
  // ==========================================================================

  @Test
  public void toString_withSimpleDatePattern_showsPattern() {
    TypeAdapterFactory factory = DateType.DATE.createAdapterFactory("yyyy-MM-dd");
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
    TypeAdapter<Date> adapter = gson.getAdapter(Date.class);

    String str = adapter.toString();
    assertEquals("DefaultDateTypeAdapter(yyyy-MM-dd)", str);
  }

  @Test
  public void toString_withComplexPattern_showsPattern() {
    TypeAdapterFactory factory = DateType.DATE.createAdapterFactory("yyyy-MM-dd HH:mm:ss.SSS");
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
    TypeAdapter<Date> adapter = gson.getAdapter(Date.class);

    String str = adapter.toString();
    assertEquals("DefaultDateTypeAdapter(yyyy-MM-dd HH:mm:ss.SSS)", str);
  }

  @Test
  public void toString_withStyleParams_showsClassName() {
    TypeAdapterFactory factory = DateType.DATE.createAdapterFactory(DateFormat.SHORT, DateFormat.SHORT);
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
    TypeAdapter<Date> adapter = gson.getAdapter(Date.class);

    String str = adapter.toString();
    // Style-based DateFormat may be SimpleDateFormat or other implementations
    assertTrue(str.startsWith("DefaultDateTypeAdapter("));
    assertTrue(str.endsWith(")"));
  }

  @Test
  public void toString_withDefaultStyleFactory_showsClassName() {
    Gson gson = new GsonBuilder()
        .registerTypeAdapterFactory(DefaultDateTypeAdapter.DEFAULT_STYLE_FACTORY)
        .create();
    TypeAdapter<Date> adapter = gson.getAdapter(Date.class);

    String str = adapter.toString();
    assertTrue(str.startsWith("DefaultDateTypeAdapter("));
    assertTrue(str.endsWith(")"));
  }

  // ==========================================================================
  // Round-trip tests
  // ==========================================================================

  @Test
  public void roundTrip_dateWithPattern_preservesData() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

      TypeAdapterFactory factory = DateType.DATE.createAdapterFactory("yyyy-MM-dd HH:mm:ss");
      Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();

      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      cal.set(2023, Calendar.JUNE, 15, 14, 30, 45);
      cal.set(Calendar.MILLISECOND, 0);
      Date original = cal.getTime();

      String json = gson.toJson(original);
      Date result = gson.fromJson(json, Date.class);

      // Compare time in milliseconds, ignoring milliseconds not in pattern
      assertEquals(original.getTime() / 1000, result.getTime() / 1000);
    } finally {
      TimeZone.setDefault(originalTimeZone);
    }
  }

  @Test
  public void roundTrip_dateWithDateOnlyPattern_preservesDatePart() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

      TypeAdapterFactory factory = DateType.DATE.createAdapterFactory("yyyy-MM-dd");
      Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();

      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      cal.set(2023, Calendar.JUNE, 15, 0, 0, 0);
      cal.set(Calendar.MILLISECOND, 0);
      Date original = cal.getTime();

      String json = gson.toJson(original);
      Date result = gson.fromJson(json, Date.class);

      Calendar resultCal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      resultCal.setTime(result);
      assertEquals(2023, resultCal.get(Calendar.YEAR));
      assertEquals(Calendar.JUNE, resultCal.get(Calendar.MONTH));
      assertEquals(15, resultCal.get(Calendar.DAY_OF_MONTH));
    } finally {
      TimeZone.setDefault(originalTimeZone);
    }
  }

  // ==========================================================================
  // Style-based adapter tests
  // ==========================================================================

  @Test
  public void styleBasedAdapter_shortStyle_formatsDate() throws IOException {
    TypeAdapterFactory factory = DateType.DATE.createAdapterFactory(DateFormat.SHORT, DateFormat.SHORT);
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
    TypeAdapter<Date> adapter = gson.getAdapter(Date.class);

    Calendar cal = Calendar.getInstance();
    cal.set(2023, Calendar.JUNE, 15, 14, 30, 0);
    cal.set(Calendar.MILLISECOND, 0);
    Date date = cal.getTime();

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, date);

    String result = stringWriter.toString();
    // Result should be a quoted string
    assertTrue(result.startsWith("\""));
    assertTrue(result.endsWith("\""));
  }

  @Test
  public void styleBasedAdapter_mediumStyle_formatsDate() throws IOException {
    TypeAdapterFactory factory = DateType.DATE.createAdapterFactory(DateFormat.MEDIUM, DateFormat.MEDIUM);
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
    TypeAdapter<Date> adapter = gson.getAdapter(Date.class);

    Calendar cal = Calendar.getInstance();
    cal.set(2023, Calendar.JUNE, 15, 14, 30, 0);
    cal.set(Calendar.MILLISECOND, 0);
    Date date = cal.getTime();

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, date);

    String result = stringWriter.toString();
    assertTrue(result.startsWith("\""));
    assertTrue(result.endsWith("\""));
  }

  @Test
  public void styleBasedAdapter_longStyle_formatsDate() throws IOException {
    TypeAdapterFactory factory = DateType.DATE.createAdapterFactory(DateFormat.LONG, DateFormat.LONG);
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
    TypeAdapter<Date> adapter = gson.getAdapter(Date.class);

    Calendar cal = Calendar.getInstance();
    cal.set(2023, Calendar.JUNE, 15, 14, 30, 0);
    cal.set(Calendar.MILLISECOND, 0);
    Date date = cal.getTime();

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, date);

    String result = stringWriter.toString();
    assertTrue(result.startsWith("\""));
    assertTrue(result.endsWith("\""));
  }

  @Test
  public void styleBasedAdapter_fullStyle_formatsDate() throws IOException {
    TypeAdapterFactory factory = DateType.DATE.createAdapterFactory(DateFormat.FULL, DateFormat.FULL);
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
    TypeAdapter<Date> adapter = gson.getAdapter(Date.class);

    Calendar cal = Calendar.getInstance();
    cal.set(2023, Calendar.JUNE, 15, 14, 30, 0);
    cal.set(Calendar.MILLISECOND, 0);
    Date date = cal.getTime();

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, date);

    String result = stringWriter.toString();
    assertTrue(result.startsWith("\""));
    assertTrue(result.endsWith("\""));
  }

  // ==========================================================================
  // Locale handling tests
  // ==========================================================================

  @Test
  public void localeHandling_nonUsLocale_stillParsesUsFormat() throws IOException {
    Locale originalLocale = Locale.getDefault();
    TimeZone originalTimeZone = TimeZone.getDefault();
    try {
      Locale.setDefault(Locale.GERMANY);
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

      TypeAdapterFactory factory = DateType.DATE.createAdapterFactory("yyyy-MM-dd");
      Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
      TypeAdapter<Date> adapter = gson.getAdapter(Date.class);

      // Serialize
      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      cal.set(2023, Calendar.JUNE, 15, 0, 0, 0);
      cal.set(Calendar.MILLISECOND, 0);
      Date date = cal.getTime();

      StringWriter stringWriter = new StringWriter();
      JsonWriter writer = new JsonWriter(stringWriter);
      adapter.write(writer, date);
      assertEquals("\"2023-06-15\"", stringWriter.toString());

      // Deserialize
      JsonReader reader = new JsonReader(new StringReader("\"2023-06-15\""));
      Date result = adapter.read(reader);

      Calendar resultCal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      resultCal.setTime(result);
      assertEquals(2023, resultCal.get(Calendar.YEAR));
      assertEquals(Calendar.JUNE, resultCal.get(Calendar.MONTH));
      assertEquals(15, resultCal.get(Calendar.DAY_OF_MONTH));
    } finally {
      Locale.setDefault(originalLocale);
      TimeZone.setDefault(originalTimeZone);
    }
  }

  // ==========================================================================
  // Edge cases
  // ==========================================================================

  @Test
  public void write_epochDate_formatsCorrectly() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

      TypeAdapterFactory factory = DateType.DATE.createAdapterFactory("yyyy-MM-dd HH:mm:ss");
      Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
      TypeAdapter<Date> adapter = gson.getAdapter(Date.class);

      Date epoch = new Date(0L);

      StringWriter stringWriter = new StringWriter();
      JsonWriter writer = new JsonWriter(stringWriter);
      adapter.write(writer, epoch);
      assertEquals("\"1970-01-01 00:00:00\"", stringWriter.toString());
    } finally {
      TimeZone.setDefault(originalTimeZone);
    }
  }

  @Test
  public void read_epochDateString_parsesCorrectly() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

      TypeAdapterFactory factory = DateType.DATE.createAdapterFactory("yyyy-MM-dd HH:mm:ss");
      Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
      TypeAdapter<Date> adapter = gson.getAdapter(Date.class);

      JsonReader reader = new JsonReader(new StringReader("\"1970-01-01 00:00:00\""));
      Date result = adapter.read(reader);

      assertEquals(0L, result.getTime());
    } finally {
      TimeZone.setDefault(originalTimeZone);
    }
  }

  @Test
  public void write_futureDate_formatsCorrectly() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

      TypeAdapterFactory factory = DateType.DATE.createAdapterFactory("yyyy-MM-dd");
      Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
      TypeAdapter<Date> adapter = gson.getAdapter(Date.class);

      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      cal.set(2099, Calendar.DECEMBER, 31, 0, 0, 0);
      cal.set(Calendar.MILLISECOND, 0);
      Date date = cal.getTime();

      StringWriter stringWriter = new StringWriter();
      JsonWriter writer = new JsonWriter(stringWriter);
      adapter.write(writer, date);
      assertEquals("\"2099-12-31\"", stringWriter.toString());
    } finally {
      TimeZone.setDefault(originalTimeZone);
    }
  }

  @Test
  public void write_historicDate_formatsCorrectly() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

      TypeAdapterFactory factory = DateType.DATE.createAdapterFactory("yyyy-MM-dd");
      Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
      TypeAdapter<Date> adapter = gson.getAdapter(Date.class);

      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      cal.set(1900, Calendar.JANUARY, 1, 0, 0, 0);
      cal.set(Calendar.MILLISECOND, 0);
      Date date = cal.getTime();

      StringWriter stringWriter = new StringWriter();
      JsonWriter writer = new JsonWriter(stringWriter);
      adapter.write(writer, date);
      assertEquals("\"1900-01-01\"", stringWriter.toString());
    } finally {
      TimeZone.setDefault(originalTimeZone);
    }
  }

  // ==========================================================================
  // Integration with GsonBuilder
  // ==========================================================================

  @Test
  public void gsonBuilder_setDateFormat_pattern_usesDefaultDateTypeAdapter() {
    Gson gson = new GsonBuilder()
        .setDateFormat("yyyy-MM-dd")
        .create();

    TimeZone originalTimeZone = TimeZone.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      cal.set(2023, Calendar.JUNE, 15, 0, 0, 0);
      cal.set(Calendar.MILLISECOND, 0);
      Date date = cal.getTime();

      String json = gson.toJson(date);
      assertEquals("\"2023-06-15\"", json);
    } finally {
      TimeZone.setDefault(originalTimeZone);
    }
  }

  @Test
  public void gsonBuilder_setDateFormat_style_usesDefaultDateTypeAdapter() {
    Gson gson = new GsonBuilder()
        .setDateFormat(DateFormat.SHORT)
        .create();

    Calendar cal = Calendar.getInstance();
    cal.set(2023, Calendar.JUNE, 15, 14, 30, 0);
    cal.set(Calendar.MILLISECOND, 0);
    Date date = cal.getTime();

    String json = gson.toJson(date);
    // Result should be a quoted date string
    assertTrue(json.startsWith("\""));
    assertTrue(json.endsWith("\""));
  }

  @Test
  public void gsonBuilder_setDateFormat_styleWithTime_usesDefaultDateTypeAdapter() {
    Gson gson = new GsonBuilder()
        .setDateFormat(DateFormat.SHORT, DateFormat.SHORT)
        .create();

    Calendar cal = Calendar.getInstance();
    cal.set(2023, Calendar.JUNE, 15, 14, 30, 0);
    cal.set(Calendar.MILLISECOND, 0);
    Date date = cal.getTime();

    String json = gson.toJson(date);
    // Result should be a quoted date/time string
    assertTrue(json.startsWith("\""));
    assertTrue(json.endsWith("\""));
  }

  // ==========================================================================
  // Multiple format parsing tests
  // ==========================================================================

  @Test
  public void read_differentLocaleFormat_parsedByAlternativeFormat() throws IOException {
    Locale originalLocale = Locale.getDefault();
    TimeZone originalTimeZone = TimeZone.getDefault();
    try {
      // Set a non-US locale to ensure multiple formats are added
      Locale.setDefault(Locale.FRANCE);
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

      TypeAdapterFactory factory = DateType.DATE.createAdapterFactory("MMM d, yyyy");
      Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
      TypeAdapter<Date> adapter = gson.getAdapter(Date.class);

      // US format should still be parsed (first format in the list is US locale)
      JsonReader reader = new JsonReader(new StringReader("\"Jun 15, 2023\""));
      Date result = adapter.read(reader);

      Calendar resultCal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      resultCal.setTime(result);
      assertEquals(2023, resultCal.get(Calendar.YEAR));
      assertEquals(Calendar.JUNE, resultCal.get(Calendar.MONTH));
      assertEquals(15, resultCal.get(Calendar.DAY_OF_MONTH));
    } finally {
      Locale.setDefault(originalLocale);
      TimeZone.setDefault(originalTimeZone);
    }
  }

  // ==========================================================================
  // Special characters in date format tests
  // ==========================================================================

  @Test
  public void write_patternWithLiterals_formatsCorrectly() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

      TypeAdapterFactory factory = DateType.DATE.createAdapterFactory("'Date:' yyyy-MM-dd");
      Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
      TypeAdapter<Date> adapter = gson.getAdapter(Date.class);

      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      cal.set(2023, Calendar.JUNE, 15, 0, 0, 0);
      cal.set(Calendar.MILLISECOND, 0);
      Date date = cal.getTime();

      StringWriter stringWriter = new StringWriter();
      JsonWriter writer = new JsonWriter(stringWriter);
      adapter.write(writer, date);
      assertEquals("\"Date: 2023-06-15\"", stringWriter.toString());
    } finally {
      TimeZone.setDefault(originalTimeZone);
    }
  }

  @Test
  public void roundTrip_patternWithLiterals_preservesData() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

      TypeAdapterFactory factory = DateType.DATE.createAdapterFactory("'Date:' yyyy-MM-dd");
      Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();

      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      cal.set(2023, Calendar.JUNE, 15, 0, 0, 0);
      cal.set(Calendar.MILLISECOND, 0);
      Date original = cal.getTime();

      String json = gson.toJson(original);
      Date result = gson.fromJson(json, Date.class);

      Calendar resultCal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      resultCal.setTime(result);
      assertEquals(2023, resultCal.get(Calendar.YEAR));
      assertEquals(Calendar.JUNE, resultCal.get(Calendar.MONTH));
      assertEquals(15, resultCal.get(Calendar.DAY_OF_MONTH));
    } finally {
      TimeZone.setDefault(originalTimeZone);
    }
  }

  // ==========================================================================
  // Path information in error messages
  // ==========================================================================

  @Test
  public void read_invalidDate_includesPathInErrorMessage() throws IOException {
    TypeAdapterFactory factory = DateType.DATE.createAdapterFactory("yyyy-MM-dd");
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
    TypeAdapter<Date> adapter = gson.getAdapter(Date.class);

    JsonReader reader = new JsonReader(new StringReader("\"invalid\""));
    try {
      adapter.read(reader);
      fail("Expected JsonSyntaxException");
    } catch (JsonSyntaxException e) {
      assertTrue(e.getMessage().contains("path"));
    }
  }

  // ==========================================================================
  // Pattern with milliseconds
  // ==========================================================================

  @Test
  public void write_patternWithMillis_formatsCorrectly() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

      TypeAdapterFactory factory = DateType.DATE.createAdapterFactory("yyyy-MM-dd HH:mm:ss.SSS");
      Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
      TypeAdapter<Date> adapter = gson.getAdapter(Date.class);

      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      cal.set(2023, Calendar.JUNE, 15, 14, 30, 45);
      cal.set(Calendar.MILLISECOND, 123);
      Date date = cal.getTime();

      StringWriter stringWriter = new StringWriter();
      JsonWriter writer = new JsonWriter(stringWriter);
      adapter.write(writer, date);
      assertEquals("\"2023-06-15 14:30:45.123\"", stringWriter.toString());
    } finally {
      TimeZone.setDefault(originalTimeZone);
    }
  }

  @Test
  public void roundTrip_patternWithMillis_preservesMilliseconds() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

      TypeAdapterFactory factory = DateType.DATE.createAdapterFactory("yyyy-MM-dd HH:mm:ss.SSS");
      Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();

      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      cal.set(2023, Calendar.JUNE, 15, 14, 30, 45);
      cal.set(Calendar.MILLISECOND, 123);
      Date original = cal.getTime();

      String json = gson.toJson(original);
      Date result = gson.fromJson(json, Date.class);

      assertEquals(original.getTime(), result.getTime());
    } finally {
      TimeZone.setDefault(originalTimeZone);
    }
  }
}
