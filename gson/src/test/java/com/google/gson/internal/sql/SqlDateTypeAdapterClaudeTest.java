/*
 * Copyright (C) 2011 Google Inc.
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

package com.google.gson.internal.sql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import org.junit.Test;

/** Tests for {@link SqlDateTypeAdapter}. */
public class SqlDateTypeAdapterClaudeTest {

  // ==========================================================================
  // read() tests - null handling
  // ==========================================================================

  @Test
  public void read_nullJson_returnsNull() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Date> adapter = gson.getAdapter(Date.class);

    JsonReader reader = new JsonReader(new StringReader("null"));
    Date result = adapter.read(reader);
    assertNull(result);
  }

  // ==========================================================================
  // read() tests - valid date strings
  // ==========================================================================

  @Test
  public void read_validDateString_parsesCorrectly() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    Locale originalLocale = Locale.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
      Locale.setDefault(Locale.US);

      Gson gson = new Gson();
      TypeAdapter<Date> adapter = gson.getAdapter(Date.class);

      // The format is "MMM d, yyyy"
      JsonReader reader = new JsonReader(new StringReader("\"Jun 15, 2023\""));
      Date result = adapter.read(reader);

      assertNotNull(result);
      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      cal.setTime(result);
      assertEquals(2023, cal.get(Calendar.YEAR));
      assertEquals(Calendar.JUNE, cal.get(Calendar.MONTH));
      assertEquals(15, cal.get(Calendar.DAY_OF_MONTH));
    } finally {
      TimeZone.setDefault(originalTimeZone);
      Locale.setDefault(originalLocale);
    }
  }

  @Test
  public void read_singleDigitDay_parsesCorrectly() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    Locale originalLocale = Locale.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
      Locale.setDefault(Locale.US);

      Gson gson = new Gson();
      TypeAdapter<Date> adapter = gson.getAdapter(Date.class);

      // Single digit day - "d" pattern allows both "5" and "05"
      JsonReader reader = new JsonReader(new StringReader("\"Jun 5, 2023\""));
      Date result = adapter.read(reader);

      assertNotNull(result);
      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      cal.setTime(result);
      assertEquals(2023, cal.get(Calendar.YEAR));
      assertEquals(Calendar.JUNE, cal.get(Calendar.MONTH));
      assertEquals(5, cal.get(Calendar.DAY_OF_MONTH));
    } finally {
      TimeZone.setDefault(originalTimeZone);
      Locale.setDefault(originalLocale);
    }
  }

  @Test
  public void read_january_parsesCorrectly() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    Locale originalLocale = Locale.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
      Locale.setDefault(Locale.US);

      Gson gson = new Gson();
      TypeAdapter<Date> adapter = gson.getAdapter(Date.class);

      JsonReader reader = new JsonReader(new StringReader("\"Jan 1, 2020\""));
      Date result = adapter.read(reader);

      assertNotNull(result);
      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      cal.setTime(result);
      assertEquals(2020, cal.get(Calendar.YEAR));
      assertEquals(Calendar.JANUARY, cal.get(Calendar.MONTH));
      assertEquals(1, cal.get(Calendar.DAY_OF_MONTH));
    } finally {
      TimeZone.setDefault(originalTimeZone);
      Locale.setDefault(originalLocale);
    }
  }

  @Test
  public void read_december_parsesCorrectly() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    Locale originalLocale = Locale.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
      Locale.setDefault(Locale.US);

      Gson gson = new Gson();
      TypeAdapter<Date> adapter = gson.getAdapter(Date.class);

      JsonReader reader = new JsonReader(new StringReader("\"Dec 31, 2020\""));
      Date result = adapter.read(reader);

      assertNotNull(result);
      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      cal.setTime(result);
      assertEquals(2020, cal.get(Calendar.YEAR));
      assertEquals(Calendar.DECEMBER, cal.get(Calendar.MONTH));
      assertEquals(31, cal.get(Calendar.DAY_OF_MONTH));
    } finally {
      TimeZone.setDefault(originalTimeZone);
      Locale.setDefault(originalLocale);
    }
  }

  // ==========================================================================
  // read() tests - invalid date strings
  // ==========================================================================

  @Test
  public void read_invalidDateString_throwsJsonSyntaxException() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Date> adapter = gson.getAdapter(Date.class);

    JsonReader reader = new JsonReader(new StringReader("\"not-a-date\""));
    try {
      adapter.read(reader);
      fail("Expected JsonSyntaxException");
    } catch (JsonSyntaxException e) {
      assertTrue(e.getMessage().contains("Failed parsing"));
      assertTrue(e.getMessage().contains("not-a-date"));
      assertTrue(e.getMessage().contains("SQL Date"));
    }
  }

  @Test
  public void read_emptyString_throwsJsonSyntaxException() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Date> adapter = gson.getAdapter(Date.class);

    JsonReader reader = new JsonReader(new StringReader("\"\""));
    try {
      adapter.read(reader);
      fail("Expected JsonSyntaxException");
    } catch (JsonSyntaxException e) {
      assertTrue(e.getMessage().contains("Failed parsing"));
    }
  }

  @Test
  public void read_wrongFormatDate_throwsJsonSyntaxException() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Date> adapter = gson.getAdapter(Date.class);

    // ISO format instead of "MMM d, yyyy"
    JsonReader reader = new JsonReader(new StringReader("\"2023-06-15\""));
    try {
      adapter.read(reader);
      fail("Expected JsonSyntaxException");
    } catch (JsonSyntaxException e) {
      assertTrue(e.getMessage().contains("Failed parsing"));
      assertTrue(e.getMessage().contains("2023-06-15"));
    }
  }

  @Test
  public void read_invalidDate_includesPathInErrorMessage() throws IOException {
    Gson gson = new Gson();
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
  // write() tests - null handling
  // ==========================================================================

  @Test
  public void write_nullDate_writesNull() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Date> adapter = gson.getAdapter(Date.class);

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, null);
    assertEquals("null", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - valid dates
  // ==========================================================================

  @Test
  public void write_validDate_writesFormattedDate() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    Locale originalLocale = Locale.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
      Locale.setDefault(Locale.US);

      Gson gson = new Gson();
      TypeAdapter<Date> adapter = gson.getAdapter(Date.class);

      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      cal.set(2023, Calendar.JUNE, 15, 0, 0, 0);
      cal.set(Calendar.MILLISECOND, 0);
      Date date = new Date(cal.getTimeInMillis());

      StringWriter stringWriter = new StringWriter();
      JsonWriter writer = new JsonWriter(stringWriter);
      adapter.write(writer, date);

      assertEquals("\"Jun 15, 2023\"", stringWriter.toString());
    } finally {
      TimeZone.setDefault(originalTimeZone);
      Locale.setDefault(originalLocale);
    }
  }

  @Test
  public void write_singleDigitDay_writesFormattedDate() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    Locale originalLocale = Locale.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
      Locale.setDefault(Locale.US);

      Gson gson = new Gson();
      TypeAdapter<Date> adapter = gson.getAdapter(Date.class);

      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      cal.set(2023, Calendar.JUNE, 5, 0, 0, 0);
      cal.set(Calendar.MILLISECOND, 0);
      Date date = new Date(cal.getTimeInMillis());

      StringWriter stringWriter = new StringWriter();
      JsonWriter writer = new JsonWriter(stringWriter);
      adapter.write(writer, date);

      // The "d" pattern uses single digit for days < 10
      assertEquals("\"Jun 5, 2023\"", stringWriter.toString());
    } finally {
      TimeZone.setDefault(originalTimeZone);
      Locale.setDefault(originalLocale);
    }
  }

  // ==========================================================================
  // Round-trip tests
  // ==========================================================================

  @Test
  public void roundTrip_preservesDatePart() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    Locale originalLocale = Locale.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
      Locale.setDefault(Locale.US);

      Gson gson = new Gson();

      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      cal.set(2023, Calendar.JUNE, 15, 0, 0, 0);
      cal.set(Calendar.MILLISECOND, 0);
      Date original = new Date(cal.getTimeInMillis());

      String json = gson.toJson(original);
      Date result = gson.fromJson(json, Date.class);

      Calendar resultCal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      resultCal.setTime(result);
      assertEquals(2023, resultCal.get(Calendar.YEAR));
      assertEquals(Calendar.JUNE, resultCal.get(Calendar.MONTH));
      assertEquals(15, resultCal.get(Calendar.DAY_OF_MONTH));
    } finally {
      TimeZone.setDefault(originalTimeZone);
      Locale.setDefault(originalLocale);
    }
  }

  @Test
  public void roundTrip_multipleMonths_preservesData() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    Locale originalLocale = Locale.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
      Locale.setDefault(Locale.US);

      Gson gson = new Gson();

      int[] months = {
        Calendar.JANUARY,
        Calendar.FEBRUARY,
        Calendar.MARCH,
        Calendar.APRIL,
        Calendar.MAY,
        Calendar.JUNE,
        Calendar.JULY,
        Calendar.AUGUST,
        Calendar.SEPTEMBER,
        Calendar.OCTOBER,
        Calendar.NOVEMBER,
        Calendar.DECEMBER
      };

      for (int month : months) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        cal.set(2023, month, 15, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date original = new Date(cal.getTimeInMillis());

        String json = gson.toJson(original);
        Date result = gson.fromJson(json, Date.class);

        Calendar resultCal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        resultCal.setTime(result);
        assertEquals("Failed for month " + month, month, resultCal.get(Calendar.MONTH));
      }
    } finally {
      TimeZone.setDefault(originalTimeZone);
      Locale.setDefault(originalLocale);
    }
  }

  // ==========================================================================
  // Edge cases
  // ==========================================================================

  @Test
  public void write_epochDate_formatsCorrectly() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    Locale originalLocale = Locale.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
      Locale.setDefault(Locale.US);

      Gson gson = new Gson();
      TypeAdapter<Date> adapter = gson.getAdapter(Date.class);

      Date epoch = new Date(0L);

      StringWriter stringWriter = new StringWriter();
      JsonWriter writer = new JsonWriter(stringWriter);
      adapter.write(writer, epoch);
      assertEquals("\"Jan 1, 1970\"", stringWriter.toString());
    } finally {
      TimeZone.setDefault(originalTimeZone);
      Locale.setDefault(originalLocale);
    }
  }

  @Test
  public void read_epochDateString_parsesCorrectly() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    Locale originalLocale = Locale.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
      Locale.setDefault(Locale.US);

      Gson gson = new Gson();
      TypeAdapter<Date> adapter = gson.getAdapter(Date.class);

      JsonReader reader = new JsonReader(new StringReader("\"Jan 1, 1970\""));
      Date result = adapter.read(reader);

      // Epoch is 0 ms, but the format only includes date, so time is reset to midnight
      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      cal.setTime(result);
      assertEquals(1970, cal.get(Calendar.YEAR));
      assertEquals(Calendar.JANUARY, cal.get(Calendar.MONTH));
      assertEquals(1, cal.get(Calendar.DAY_OF_MONTH));
    } finally {
      TimeZone.setDefault(originalTimeZone);
      Locale.setDefault(originalLocale);
    }
  }

  @Test
  public void write_futureDate_formatsCorrectly() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    Locale originalLocale = Locale.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
      Locale.setDefault(Locale.US);

      Gson gson = new Gson();
      TypeAdapter<Date> adapter = gson.getAdapter(Date.class);

      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      cal.set(2099, Calendar.DECEMBER, 31, 0, 0, 0);
      cal.set(Calendar.MILLISECOND, 0);
      Date date = new Date(cal.getTimeInMillis());

      StringWriter stringWriter = new StringWriter();
      JsonWriter writer = new JsonWriter(stringWriter);
      adapter.write(writer, date);
      assertEquals("\"Dec 31, 2099\"", stringWriter.toString());
    } finally {
      TimeZone.setDefault(originalTimeZone);
      Locale.setDefault(originalLocale);
    }
  }

  @Test
  public void write_historicDate_formatsCorrectly() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    Locale originalLocale = Locale.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
      Locale.setDefault(Locale.US);

      Gson gson = new Gson();
      TypeAdapter<Date> adapter = gson.getAdapter(Date.class);

      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      cal.set(1900, Calendar.JANUARY, 1, 0, 0, 0);
      cal.set(Calendar.MILLISECOND, 0);
      Date date = new Date(cal.getTimeInMillis());

      StringWriter stringWriter = new StringWriter();
      JsonWriter writer = new JsonWriter(stringWriter);
      adapter.write(writer, date);
      assertEquals("\"Jan 1, 1900\"", stringWriter.toString());
    } finally {
      TimeZone.setDefault(originalTimeZone);
      Locale.setDefault(originalLocale);
    }
  }

  // ==========================================================================
  // Gson integration tests
  // ==========================================================================

  @Test
  public void gson_serializeSqlDate_usesAdapter() {
    TimeZone originalTimeZone = TimeZone.getDefault();
    Locale originalLocale = Locale.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
      Locale.setDefault(Locale.US);

      Gson gson = new Gson();

      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      cal.set(2023, Calendar.JUNE, 15, 0, 0, 0);
      cal.set(Calendar.MILLISECOND, 0);
      Date date = new Date(cal.getTimeInMillis());

      String json = gson.toJson(date);
      assertEquals("\"Jun 15, 2023\"", json);
    } finally {
      TimeZone.setDefault(originalTimeZone);
      Locale.setDefault(originalLocale);
    }
  }

  @Test
  public void gson_deserializeSqlDate_usesAdapter() {
    TimeZone originalTimeZone = TimeZone.getDefault();
    Locale originalLocale = Locale.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
      Locale.setDefault(Locale.US);

      Gson gson = new Gson();

      Date result = gson.fromJson("\"Jun 15, 2023\"", Date.class);

      assertNotNull(result);
      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      cal.setTime(result);
      assertEquals(2023, cal.get(Calendar.YEAR));
      assertEquals(Calendar.JUNE, cal.get(Calendar.MONTH));
      assertEquals(15, cal.get(Calendar.DAY_OF_MONTH));
    } finally {
      TimeZone.setDefault(originalTimeZone);
      Locale.setDefault(originalLocale);
    }
  }

  // ==========================================================================
  // Type-specific tests (verifying it's a java.sql.Date)
  // ==========================================================================

  @Test
  public void read_returnsJavaSqlDate() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    Locale originalLocale = Locale.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
      Locale.setDefault(Locale.US);

      Gson gson = new Gson();
      TypeAdapter<Date> adapter = gson.getAdapter(Date.class);

      JsonReader reader = new JsonReader(new StringReader("\"Jun 15, 2023\""));
      Date result = adapter.read(reader);

      assertNotNull(result);
      assertEquals(java.sql.Date.class, result.getClass());
    } finally {
      TimeZone.setDefault(originalTimeZone);
      Locale.setDefault(originalLocale);
    }
  }

  // ==========================================================================
  // Leap year tests
  // ==========================================================================

  @Test
  public void roundTrip_leapYearDate_preservesData() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    Locale originalLocale = Locale.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
      Locale.setDefault(Locale.US);

      Gson gson = new Gson();

      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      cal.set(2024, Calendar.FEBRUARY, 29, 0, 0, 0);
      cal.set(Calendar.MILLISECOND, 0);
      Date original = new Date(cal.getTimeInMillis());

      String json = gson.toJson(original);
      assertEquals("\"Feb 29, 2024\"", json);

      Date result = gson.fromJson(json, Date.class);

      Calendar resultCal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      resultCal.setTime(result);
      assertEquals(2024, resultCal.get(Calendar.YEAR));
      assertEquals(Calendar.FEBRUARY, resultCal.get(Calendar.MONTH));
      assertEquals(29, resultCal.get(Calendar.DAY_OF_MONTH));
    } finally {
      TimeZone.setDefault(originalTimeZone);
      Locale.setDefault(originalLocale);
    }
  }

  // ==========================================================================
  // Tests with objects containing Date fields
  // ==========================================================================

  @Test
  public void gson_serializeObjectWithSqlDateField_usesAdapter() {
    TimeZone originalTimeZone = TimeZone.getDefault();
    Locale originalLocale = Locale.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
      Locale.setDefault(Locale.US);

      Gson gson = new Gson();

      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      cal.set(2023, Calendar.JUNE, 15, 0, 0, 0);
      cal.set(Calendar.MILLISECOND, 0);

      ObjectWithSqlDate obj = new ObjectWithSqlDate();
      obj.date = new Date(cal.getTimeInMillis());
      obj.name = "test";

      String json = gson.toJson(obj);
      assertTrue(json.contains("\"Jun 15, 2023\""));
      assertTrue(json.contains("\"test\""));
    } finally {
      TimeZone.setDefault(originalTimeZone);
      Locale.setDefault(originalLocale);
    }
  }

  @Test
  public void gson_deserializeObjectWithSqlDateField_usesAdapter() {
    TimeZone originalTimeZone = TimeZone.getDefault();
    Locale originalLocale = Locale.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
      Locale.setDefault(Locale.US);

      Gson gson = new Gson();

      String json = "{\"date\":\"Jun 15, 2023\",\"name\":\"test\"}";
      ObjectWithSqlDate result = gson.fromJson(json, ObjectWithSqlDate.class);

      assertNotNull(result);
      assertNotNull(result.date);
      assertEquals("test", result.name);

      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      cal.setTime(result.date);
      assertEquals(2023, cal.get(Calendar.YEAR));
      assertEquals(Calendar.JUNE, cal.get(Calendar.MONTH));
      assertEquals(15, cal.get(Calendar.DAY_OF_MONTH));
    } finally {
      TimeZone.setDefault(originalTimeZone);
      Locale.setDefault(originalLocale);
    }
  }

  @Test
  public void gson_deserializeObjectWithNullSqlDateField_returnsNullField() {
    Gson gson = new Gson();

    String json = "{\"date\":null,\"name\":\"test\"}";
    ObjectWithSqlDate result = gson.fromJson(json, ObjectWithSqlDate.class);

    assertNotNull(result);
    assertNull(result.date);
    assertEquals("test", result.name);
  }

  // Helper class for testing objects with sql.Date fields
  @SuppressWarnings("unused")
  private static class ObjectWithSqlDate {
    Date date;
    String name;
  }
}
