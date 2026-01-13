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
import java.sql.Time;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import org.junit.Test;

/** Tests for {@link SqlTimeTypeAdapter}. */
public class SqlTimeTypeAdapterClaudeTest {

  // ==========================================================================
  // read() tests - null handling
  // ==========================================================================

  @Test
  public void read_nullJson_returnsNull() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Time> adapter = gson.getAdapter(Time.class);

    JsonReader reader = new JsonReader(new StringReader("null"));
    Time result = adapter.read(reader);
    assertNull(result);
  }

  // ==========================================================================
  // read() tests - valid time strings
  // ==========================================================================

  @Test
  public void read_validTimeString_parsesCorrectly() throws IOException {
    Locale originalLocale = Locale.getDefault();
    try {
      Locale.setDefault(Locale.US);

      Gson gson = new Gson();
      TypeAdapter<Time> adapter = gson.getAdapter(Time.class);

      // The format is "hh:mm:ss a" (12-hour format with AM/PM)
      JsonReader reader = new JsonReader(new StringReader("\"02:30:45 PM\""));
      Time result = adapter.read(reader);

      assertNotNull(result);
      Calendar cal = Calendar.getInstance();
      cal.setTime(result);
      assertEquals(14, cal.get(Calendar.HOUR_OF_DAY));
      assertEquals(30, cal.get(Calendar.MINUTE));
      assertEquals(45, cal.get(Calendar.SECOND));
    } finally {
      Locale.setDefault(originalLocale);
    }
  }

  @Test
  public void read_morningTime_parsesCorrectly() throws IOException {
    Locale originalLocale = Locale.getDefault();
    try {
      Locale.setDefault(Locale.US);

      Gson gson = new Gson();
      TypeAdapter<Time> adapter = gson.getAdapter(Time.class);

      JsonReader reader = new JsonReader(new StringReader("\"09:15:30 AM\""));
      Time result = adapter.read(reader);

      assertNotNull(result);
      Calendar cal = Calendar.getInstance();
      cal.setTime(result);
      assertEquals(9, cal.get(Calendar.HOUR_OF_DAY));
      assertEquals(15, cal.get(Calendar.MINUTE));
      assertEquals(30, cal.get(Calendar.SECOND));
    } finally {
      Locale.setDefault(originalLocale);
    }
  }

  @Test
  public void read_midnight_parsesCorrectly() throws IOException {
    Locale originalLocale = Locale.getDefault();
    try {
      Locale.setDefault(Locale.US);

      Gson gson = new Gson();
      TypeAdapter<Time> adapter = gson.getAdapter(Time.class);

      JsonReader reader = new JsonReader(new StringReader("\"12:00:00 AM\""));
      Time result = adapter.read(reader);

      assertNotNull(result);
      Calendar cal = Calendar.getInstance();
      cal.setTime(result);
      assertEquals(0, cal.get(Calendar.HOUR_OF_DAY));
      assertEquals(0, cal.get(Calendar.MINUTE));
      assertEquals(0, cal.get(Calendar.SECOND));
    } finally {
      Locale.setDefault(originalLocale);
    }
  }

  @Test
  public void read_noon_parsesCorrectly() throws IOException {
    Locale originalLocale = Locale.getDefault();
    try {
      Locale.setDefault(Locale.US);

      Gson gson = new Gson();
      TypeAdapter<Time> adapter = gson.getAdapter(Time.class);

      JsonReader reader = new JsonReader(new StringReader("\"12:00:00 PM\""));
      Time result = adapter.read(reader);

      assertNotNull(result);
      Calendar cal = Calendar.getInstance();
      cal.setTime(result);
      assertEquals(12, cal.get(Calendar.HOUR_OF_DAY));
      assertEquals(0, cal.get(Calendar.MINUTE));
      assertEquals(0, cal.get(Calendar.SECOND));
    } finally {
      Locale.setDefault(originalLocale);
    }
  }

  @Test
  public void read_oneSecondBeforeMidnight_parsesCorrectly() throws IOException {
    Locale originalLocale = Locale.getDefault();
    try {
      Locale.setDefault(Locale.US);

      Gson gson = new Gson();
      TypeAdapter<Time> adapter = gson.getAdapter(Time.class);

      JsonReader reader = new JsonReader(new StringReader("\"11:59:59 PM\""));
      Time result = adapter.read(reader);

      assertNotNull(result);
      Calendar cal = Calendar.getInstance();
      cal.setTime(result);
      assertEquals(23, cal.get(Calendar.HOUR_OF_DAY));
      assertEquals(59, cal.get(Calendar.MINUTE));
      assertEquals(59, cal.get(Calendar.SECOND));
    } finally {
      Locale.setDefault(originalLocale);
    }
  }

  // ==========================================================================
  // read() tests - invalid time strings
  // ==========================================================================

  @Test
  public void read_invalidTimeString_throwsJsonSyntaxException() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Time> adapter = gson.getAdapter(Time.class);

    JsonReader reader = new JsonReader(new StringReader("\"not-a-time\""));
    try {
      adapter.read(reader);
      fail("Expected JsonSyntaxException");
    } catch (JsonSyntaxException e) {
      assertTrue(e.getMessage().contains("Failed parsing"));
      assertTrue(e.getMessage().contains("not-a-time"));
      assertTrue(e.getMessage().contains("SQL Time"));
    }
  }

  @Test
  public void read_emptyString_throwsJsonSyntaxException() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Time> adapter = gson.getAdapter(Time.class);

    JsonReader reader = new JsonReader(new StringReader("\"\""));
    try {
      adapter.read(reader);
      fail("Expected JsonSyntaxException");
    } catch (JsonSyntaxException e) {
      assertTrue(e.getMessage().contains("Failed parsing"));
    }
  }

  @Test
  public void read_wrongFormatTime_throwsJsonSyntaxException() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Time> adapter = gson.getAdapter(Time.class);

    // 24-hour format instead of 12-hour with AM/PM
    JsonReader reader = new JsonReader(new StringReader("\"14:30:45\""));
    try {
      adapter.read(reader);
      fail("Expected JsonSyntaxException");
    } catch (JsonSyntaxException e) {
      assertTrue(e.getMessage().contains("Failed parsing"));
      assertTrue(e.getMessage().contains("14:30:45"));
    }
  }

  @Test
  public void read_invalidTime_includesPathInErrorMessage() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Time> adapter = gson.getAdapter(Time.class);

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
  public void write_nullTime_writesNull() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Time> adapter = gson.getAdapter(Time.class);

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, null);
    assertEquals("null", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - valid times
  // ==========================================================================

  @Test
  public void write_validTime_writesFormattedTime() throws IOException {
    Locale originalLocale = Locale.getDefault();
    try {
      Locale.setDefault(Locale.US);

      Gson gson = new Gson();
      TypeAdapter<Time> adapter = gson.getAdapter(Time.class);

      // Create a Time for 2:30:45 PM
      Calendar cal = Calendar.getInstance();
      cal.set(Calendar.HOUR_OF_DAY, 14);
      cal.set(Calendar.MINUTE, 30);
      cal.set(Calendar.SECOND, 45);
      Time time = new Time(cal.getTimeInMillis());

      StringWriter stringWriter = new StringWriter();
      JsonWriter writer = new JsonWriter(stringWriter);
      adapter.write(writer, time);

      assertEquals("\"02:30:45 PM\"", stringWriter.toString());
    } finally {
      Locale.setDefault(originalLocale);
    }
  }

  @Test
  public void write_morningTime_writesFormattedTime() throws IOException {
    Locale originalLocale = Locale.getDefault();
    try {
      Locale.setDefault(Locale.US);

      Gson gson = new Gson();
      TypeAdapter<Time> adapter = gson.getAdapter(Time.class);

      Calendar cal = Calendar.getInstance();
      cal.set(Calendar.HOUR_OF_DAY, 9);
      cal.set(Calendar.MINUTE, 15);
      cal.set(Calendar.SECOND, 30);
      Time time = new Time(cal.getTimeInMillis());

      StringWriter stringWriter = new StringWriter();
      JsonWriter writer = new JsonWriter(stringWriter);
      adapter.write(writer, time);

      assertEquals("\"09:15:30 AM\"", stringWriter.toString());
    } finally {
      Locale.setDefault(originalLocale);
    }
  }

  @Test
  public void write_midnight_writesFormattedTime() throws IOException {
    Locale originalLocale = Locale.getDefault();
    try {
      Locale.setDefault(Locale.US);

      Gson gson = new Gson();
      TypeAdapter<Time> adapter = gson.getAdapter(Time.class);

      Calendar cal = Calendar.getInstance();
      cal.set(Calendar.HOUR_OF_DAY, 0);
      cal.set(Calendar.MINUTE, 0);
      cal.set(Calendar.SECOND, 0);
      Time time = new Time(cal.getTimeInMillis());

      StringWriter stringWriter = new StringWriter();
      JsonWriter writer = new JsonWriter(stringWriter);
      adapter.write(writer, time);

      assertEquals("\"12:00:00 AM\"", stringWriter.toString());
    } finally {
      Locale.setDefault(originalLocale);
    }
  }

  @Test
  public void write_noon_writesFormattedTime() throws IOException {
    Locale originalLocale = Locale.getDefault();
    try {
      Locale.setDefault(Locale.US);

      Gson gson = new Gson();
      TypeAdapter<Time> adapter = gson.getAdapter(Time.class);

      Calendar cal = Calendar.getInstance();
      cal.set(Calendar.HOUR_OF_DAY, 12);
      cal.set(Calendar.MINUTE, 0);
      cal.set(Calendar.SECOND, 0);
      Time time = new Time(cal.getTimeInMillis());

      StringWriter stringWriter = new StringWriter();
      JsonWriter writer = new JsonWriter(stringWriter);
      adapter.write(writer, time);

      assertEquals("\"12:00:00 PM\"", stringWriter.toString());
    } finally {
      Locale.setDefault(originalLocale);
    }
  }

  // ==========================================================================
  // Round-trip tests
  // ==========================================================================

  @Test
  public void roundTrip_preservesTimePart() throws IOException {
    Locale originalLocale = Locale.getDefault();
    try {
      Locale.setDefault(Locale.US);

      Gson gson = new Gson();

      Calendar cal = Calendar.getInstance();
      cal.set(Calendar.HOUR_OF_DAY, 14);
      cal.set(Calendar.MINUTE, 30);
      cal.set(Calendar.SECOND, 45);
      Time original = new Time(cal.getTimeInMillis());

      String json = gson.toJson(original);
      Time result = gson.fromJson(json, Time.class);

      Calendar resultCal = Calendar.getInstance();
      resultCal.setTime(result);
      assertEquals(14, resultCal.get(Calendar.HOUR_OF_DAY));
      assertEquals(30, resultCal.get(Calendar.MINUTE));
      assertEquals(45, resultCal.get(Calendar.SECOND));
    } finally {
      Locale.setDefault(originalLocale);
    }
  }

  @Test
  public void roundTrip_variousHours_preservesData() throws IOException {
    Locale originalLocale = Locale.getDefault();
    try {
      Locale.setDefault(Locale.US);

      Gson gson = new Gson();

      // Test every hour of the day
      for (int hour = 0; hour < 24; hour++) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, 30);
        cal.set(Calendar.SECOND, 15);
        Time original = new Time(cal.getTimeInMillis());

        String json = gson.toJson(original);
        Time result = gson.fromJson(json, Time.class);

        Calendar resultCal = Calendar.getInstance();
        resultCal.setTime(result);
        assertEquals("Failed for hour " + hour, hour, resultCal.get(Calendar.HOUR_OF_DAY));
        assertEquals(30, resultCal.get(Calendar.MINUTE));
        assertEquals(15, resultCal.get(Calendar.SECOND));
      }
    } finally {
      Locale.setDefault(originalLocale);
    }
  }

  // ==========================================================================
  // Gson integration tests
  // ==========================================================================

  @Test
  public void gson_serializeSqlTime_usesAdapter() {
    Locale originalLocale = Locale.getDefault();
    try {
      Locale.setDefault(Locale.US);

      Gson gson = new Gson();

      Calendar cal = Calendar.getInstance();
      cal.set(Calendar.HOUR_OF_DAY, 14);
      cal.set(Calendar.MINUTE, 30);
      cal.set(Calendar.SECOND, 45);
      Time time = new Time(cal.getTimeInMillis());

      String json = gson.toJson(time);
      assertEquals("\"02:30:45 PM\"", json);
    } finally {
      Locale.setDefault(originalLocale);
    }
  }

  @Test
  public void gson_deserializeSqlTime_usesAdapter() {
    Locale originalLocale = Locale.getDefault();
    try {
      Locale.setDefault(Locale.US);

      Gson gson = new Gson();

      Time result = gson.fromJson("\"02:30:45 PM\"", Time.class);

      assertNotNull(result);
      Calendar cal = Calendar.getInstance();
      cal.setTime(result);
      assertEquals(14, cal.get(Calendar.HOUR_OF_DAY));
      assertEquals(30, cal.get(Calendar.MINUTE));
      assertEquals(45, cal.get(Calendar.SECOND));
    } finally {
      Locale.setDefault(originalLocale);
    }
  }

  // ==========================================================================
  // Type-specific tests (verifying it's a java.sql.Time)
  // ==========================================================================

  @Test
  public void read_returnsJavaSqlTime() throws IOException {
    Locale originalLocale = Locale.getDefault();
    try {
      Locale.setDefault(Locale.US);

      Gson gson = new Gson();
      TypeAdapter<Time> adapter = gson.getAdapter(Time.class);

      JsonReader reader = new JsonReader(new StringReader("\"02:30:45 PM\""));
      Time result = adapter.read(reader);

      assertNotNull(result);
      assertEquals(java.sql.Time.class, result.getClass());
    } finally {
      Locale.setDefault(originalLocale);
    }
  }

  // ==========================================================================
  // Tests with objects containing Time fields
  // ==========================================================================

  @Test
  public void gson_serializeObjectWithSqlTimeField_usesAdapter() {
    Locale originalLocale = Locale.getDefault();
    try {
      Locale.setDefault(Locale.US);

      Gson gson = new Gson();

      Calendar cal = Calendar.getInstance();
      cal.set(Calendar.HOUR_OF_DAY, 14);
      cal.set(Calendar.MINUTE, 30);
      cal.set(Calendar.SECOND, 45);

      ObjectWithSqlTime obj = new ObjectWithSqlTime();
      obj.time = new Time(cal.getTimeInMillis());
      obj.name = "test";

      String json = gson.toJson(obj);
      assertTrue(json.contains("\"02:30:45 PM\""));
      assertTrue(json.contains("\"test\""));
    } finally {
      Locale.setDefault(originalLocale);
    }
  }

  @Test
  public void gson_deserializeObjectWithSqlTimeField_usesAdapter() {
    Locale originalLocale = Locale.getDefault();
    try {
      Locale.setDefault(Locale.US);

      Gson gson = new Gson();

      String json = "{\"time\":\"02:30:45 PM\",\"name\":\"test\"}";
      ObjectWithSqlTime result = gson.fromJson(json, ObjectWithSqlTime.class);

      assertNotNull(result);
      assertNotNull(result.time);
      assertEquals("test", result.name);

      Calendar cal = Calendar.getInstance();
      cal.setTime(result.time);
      assertEquals(14, cal.get(Calendar.HOUR_OF_DAY));
      assertEquals(30, cal.get(Calendar.MINUTE));
      assertEquals(45, cal.get(Calendar.SECOND));
    } finally {
      Locale.setDefault(originalLocale);
    }
  }

  @Test
  public void gson_deserializeObjectWithNullSqlTimeField_returnsNullField() {
    Gson gson = new Gson();

    String json = "{\"time\":null,\"name\":\"test\"}";
    ObjectWithSqlTime result = gson.fromJson(json, ObjectWithSqlTime.class);

    assertNotNull(result);
    assertNull(result.time);
    assertEquals("test", result.name);
  }

  // Helper class for testing objects with sql.Time fields
  @SuppressWarnings("unused")
  private static class ObjectWithSqlTime {
    Time time;
    String name;
  }
}
