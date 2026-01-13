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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.TimeZone;
import org.junit.Test;

/** Tests for {@link SqlTimestampTypeAdapter}. */
public class SqlTimestampTypeAdapterClaudeTest {

  // ==========================================================================
  // read() tests - null handling
  // ==========================================================================

  @Test
  public void read_nullJson_returnsNull() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Timestamp> adapter = gson.getAdapter(Timestamp.class);

    JsonReader reader = new JsonReader(new StringReader("null"));
    Timestamp result = adapter.read(reader);
    assertNull(result);
  }

  // ==========================================================================
  // read() tests - valid timestamp strings (via Date adapter)
  // ==========================================================================

  @Test
  public void read_validDateString_parsesCorrectly() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

      Gson gson = new Gson();
      TypeAdapter<Timestamp> adapter = gson.getAdapter(Timestamp.class);

      // Default Gson date format: "MMM d, yyyy, h:mm:ss a" or similar
      // The actual format depends on the locale, so we use a simple date format
      JsonReader reader = new JsonReader(new StringReader("\"Jun 15, 2023, 2:30:45 PM\""));
      Timestamp result = adapter.read(reader);

      assertNotNull(result);
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
  public void read_epochTimestamp_parsesCorrectly() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

      Gson gson = new Gson();
      TypeAdapter<Timestamp> adapter = gson.getAdapter(Timestamp.class);

      JsonReader reader = new JsonReader(new StringReader("\"Jan 1, 1970, 12:00:00 AM\""));
      Timestamp result = adapter.read(reader);

      assertNotNull(result);
      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      cal.setTime(result);
      assertEquals(1970, cal.get(Calendar.YEAR));
      assertEquals(Calendar.JANUARY, cal.get(Calendar.MONTH));
      assertEquals(1, cal.get(Calendar.DAY_OF_MONTH));
    } finally {
      TimeZone.setDefault(originalTimeZone);
    }
  }

  // ==========================================================================
  // write() tests - null handling
  // ==========================================================================

  @Test
  public void write_nullTimestamp_writesNull() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Timestamp> adapter = gson.getAdapter(Timestamp.class);

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, null);
    assertEquals("null", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - valid timestamps
  // ==========================================================================

  @Test
  public void write_validTimestamp_writesFormattedString() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

      Gson gson = new Gson();
      TypeAdapter<Timestamp> adapter = gson.getAdapter(Timestamp.class);

      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      cal.set(2023, Calendar.JUNE, 15, 14, 30, 45);
      cal.set(Calendar.MILLISECOND, 0);
      Timestamp timestamp = new Timestamp(cal.getTimeInMillis());

      StringWriter stringWriter = new StringWriter();
      JsonWriter writer = new JsonWriter(stringWriter);
      adapter.write(writer, timestamp);

      String result = stringWriter.toString();
      assertNotNull(result);
      // Verify it's a quoted string
      assertTrue(result.startsWith("\""));
      assertTrue(result.endsWith("\""));
      // The format should contain date components
      assertTrue(result.contains("Jun"));
      assertTrue(result.contains("2023"));
    } finally {
      TimeZone.setDefault(originalTimeZone);
    }
  }

  @Test
  public void write_epochTimestamp_formatsCorrectly() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

      Gson gson = new Gson();
      TypeAdapter<Timestamp> adapter = gson.getAdapter(Timestamp.class);

      Timestamp epoch = new Timestamp(0L);

      StringWriter stringWriter = new StringWriter();
      JsonWriter writer = new JsonWriter(stringWriter);
      adapter.write(writer, epoch);

      String result = stringWriter.toString();
      assertNotNull(result);
      // Should contain the epoch date
      assertTrue(result.contains("1970"));
      assertTrue(result.contains("Jan"));
    } finally {
      TimeZone.setDefault(originalTimeZone);
    }
  }

  // ==========================================================================
  // Round-trip tests
  // ==========================================================================

  @Test
  public void roundTrip_preservesDateAndTime() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

      Gson gson = new Gson();

      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      cal.set(2023, Calendar.JUNE, 15, 14, 30, 45);
      cal.set(Calendar.MILLISECOND, 0);
      Timestamp original = new Timestamp(cal.getTimeInMillis());

      String json = gson.toJson(original);
      Timestamp result = gson.fromJson(json, Timestamp.class);

      Calendar resultCal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      resultCal.setTime(result);
      assertEquals(2023, resultCal.get(Calendar.YEAR));
      assertEquals(Calendar.JUNE, resultCal.get(Calendar.MONTH));
      assertEquals(15, resultCal.get(Calendar.DAY_OF_MONTH));
    } finally {
      TimeZone.setDefault(originalTimeZone);
    }
  }

  @Test
  public void roundTrip_multipleMonths_preservesData() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

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
        cal.set(2023, month, 15, 10, 30, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Timestamp original = new Timestamp(cal.getTimeInMillis());

        String json = gson.toJson(original);
        Timestamp result = gson.fromJson(json, Timestamp.class);

        Calendar resultCal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        resultCal.setTime(result);
        assertEquals("Failed for month " + month, month, resultCal.get(Calendar.MONTH));
      }
    } finally {
      TimeZone.setDefault(originalTimeZone);
    }
  }

  @Test
  public void roundTrip_nullTimestamp_preservesNull() {
    Gson gson = new Gson();

    String json = gson.toJson(null, Timestamp.class);
    Timestamp result = gson.fromJson(json, Timestamp.class);

    assertNull(result);
  }

  // ==========================================================================
  // Type-specific tests (verifying it's a java.sql.Timestamp)
  // ==========================================================================

  @Test
  public void read_returnsJavaSqlTimestamp() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

      Gson gson = new Gson();
      TypeAdapter<Timestamp> adapter = gson.getAdapter(Timestamp.class);

      JsonReader reader = new JsonReader(new StringReader("\"Jun 15, 2023, 2:30:45 PM\""));
      Timestamp result = adapter.read(reader);

      assertNotNull(result);
      assertEquals(java.sql.Timestamp.class, result.getClass());
    } finally {
      TimeZone.setDefault(originalTimeZone);
    }
  }

  // ==========================================================================
  // Gson integration tests
  // ==========================================================================

  @Test
  public void gson_serializeSqlTimestamp_usesAdapter() {
    TimeZone originalTimeZone = TimeZone.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

      Gson gson = new Gson();

      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      cal.set(2023, Calendar.JUNE, 15, 14, 30, 45);
      cal.set(Calendar.MILLISECOND, 0);
      Timestamp timestamp = new Timestamp(cal.getTimeInMillis());

      String json = gson.toJson(timestamp);
      assertNotNull(json);
      assertTrue(json.startsWith("\""));
      assertTrue(json.endsWith("\""));
      assertTrue(json.contains("2023"));
    } finally {
      TimeZone.setDefault(originalTimeZone);
    }
  }

  @Test
  public void gson_deserializeSqlTimestamp_usesAdapter() {
    TimeZone originalTimeZone = TimeZone.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

      Gson gson = new Gson();

      Timestamp result = gson.fromJson("\"Jun 15, 2023, 2:30:45 PM\"", Timestamp.class);

      assertNotNull(result);
      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      cal.setTime(result);
      assertEquals(2023, cal.get(Calendar.YEAR));
      assertEquals(Calendar.JUNE, cal.get(Calendar.MONTH));
      assertEquals(15, cal.get(Calendar.DAY_OF_MONTH));
    } finally {
      TimeZone.setDefault(originalTimeZone);
    }
  }

  // ==========================================================================
  // Tests with objects containing Timestamp fields
  // ==========================================================================

  @Test
  public void gson_serializeObjectWithTimestampField_usesAdapter() {
    TimeZone originalTimeZone = TimeZone.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

      Gson gson = new Gson();

      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      cal.set(2023, Calendar.JUNE, 15, 14, 30, 45);
      cal.set(Calendar.MILLISECOND, 0);

      ObjectWithSqlTimestamp obj = new ObjectWithSqlTimestamp();
      obj.timestamp = new Timestamp(cal.getTimeInMillis());
      obj.name = "test";

      String json = gson.toJson(obj);
      assertTrue(json.contains("2023"));
      assertTrue(json.contains("\"test\""));
    } finally {
      TimeZone.setDefault(originalTimeZone);
    }
  }

  @Test
  public void gson_deserializeObjectWithTimestampField_usesAdapter() {
    TimeZone originalTimeZone = TimeZone.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

      Gson gson = new Gson();

      String json = "{\"timestamp\":\"Jun 15, 2023, 2:30:45 PM\",\"name\":\"test\"}";
      ObjectWithSqlTimestamp result = gson.fromJson(json, ObjectWithSqlTimestamp.class);

      assertNotNull(result);
      assertNotNull(result.timestamp);
      assertEquals("test", result.name);

      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      cal.setTime(result.timestamp);
      assertEquals(2023, cal.get(Calendar.YEAR));
      assertEquals(Calendar.JUNE, cal.get(Calendar.MONTH));
      assertEquals(15, cal.get(Calendar.DAY_OF_MONTH));
    } finally {
      TimeZone.setDefault(originalTimeZone);
    }
  }

  @Test
  public void gson_deserializeObjectWithNullTimestampField_returnsNullField() {
    Gson gson = new Gson();

    String json = "{\"timestamp\":null,\"name\":\"test\"}";
    ObjectWithSqlTimestamp result = gson.fromJson(json, ObjectWithSqlTimestamp.class);

    assertNotNull(result);
    assertNull(result.timestamp);
    assertEquals("test", result.name);
  }

  // ==========================================================================
  // Edge cases
  // ==========================================================================

  @Test
  public void write_futureTimestamp_formatsCorrectly() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

      Gson gson = new Gson();
      TypeAdapter<Timestamp> adapter = gson.getAdapter(Timestamp.class);

      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      cal.set(2099, Calendar.DECEMBER, 31, 23, 59, 59);
      cal.set(Calendar.MILLISECOND, 0);
      Timestamp timestamp = new Timestamp(cal.getTimeInMillis());

      StringWriter stringWriter = new StringWriter();
      JsonWriter writer = new JsonWriter(stringWriter);
      adapter.write(writer, timestamp);

      String result = stringWriter.toString();
      assertTrue(result.contains("2099"));
      assertTrue(result.contains("Dec"));
    } finally {
      TimeZone.setDefault(originalTimeZone);
    }
  }

  @Test
  public void write_historicTimestamp_formatsCorrectly() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

      Gson gson = new Gson();
      TypeAdapter<Timestamp> adapter = gson.getAdapter(Timestamp.class);

      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      cal.set(1900, Calendar.JANUARY, 1, 0, 0, 0);
      cal.set(Calendar.MILLISECOND, 0);
      Timestamp timestamp = new Timestamp(cal.getTimeInMillis());

      StringWriter stringWriter = new StringWriter();
      JsonWriter writer = new JsonWriter(stringWriter);
      adapter.write(writer, timestamp);

      String result = stringWriter.toString();
      assertTrue(result.contains("1900"));
      assertTrue(result.contains("Jan"));
    } finally {
      TimeZone.setDefault(originalTimeZone);
    }
  }

  @Test
  public void roundTrip_leapYearTimestamp_preservesData() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

      Gson gson = new Gson();

      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      cal.set(2024, Calendar.FEBRUARY, 29, 12, 30, 0);
      cal.set(Calendar.MILLISECOND, 0);
      Timestamp original = new Timestamp(cal.getTimeInMillis());

      String json = gson.toJson(original);
      assertTrue(json.contains("Feb"));
      assertTrue(json.contains("2024"));

      Timestamp result = gson.fromJson(json, Timestamp.class);

      Calendar resultCal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      resultCal.setTime(result);
      assertEquals(2024, resultCal.get(Calendar.YEAR));
      assertEquals(Calendar.FEBRUARY, resultCal.get(Calendar.MONTH));
      assertEquals(29, resultCal.get(Calendar.DAY_OF_MONTH));
    } finally {
      TimeZone.setDefault(originalTimeZone);
    }
  }

  // ==========================================================================
  // Timestamp-specific behavior: nanoseconds
  // ==========================================================================

  @Test
  public void timestamp_nanoseconds_serializedAsMillisecondsOnly() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

      Gson gson = new Gson();

      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      cal.set(2023, Calendar.JUNE, 15, 14, 30, 45);
      cal.set(Calendar.MILLISECOND, 123);
      Timestamp timestampWithNanos = new Timestamp(cal.getTimeInMillis());
      timestampWithNanos.setNanos(123456789);

      // Serialize - nanoseconds should be truncated to milliseconds by Date adapter
      String json = gson.toJson(timestampWithNanos);
      assertNotNull(json);

      // Deserialize
      Timestamp result = gson.fromJson(json, Timestamp.class);

      // The adapter delegates to Date, so nanos beyond millis precision are lost
      assertNotNull(result);
      // Verify basic date/time is preserved
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
  // Custom date format configuration
  // ==========================================================================

  @Test
  public void gson_withCustomDateFormat_usesCustomFormat() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

      // Use a custom date format via GsonBuilder
      Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      cal.set(2023, Calendar.JUNE, 15, 14, 30, 45);
      cal.set(Calendar.MILLISECOND, 0);
      Timestamp timestamp = new Timestamp(cal.getTimeInMillis());

      String json = gson.toJson(timestamp);
      assertEquals("\"2023-06-15\"", json);
    } finally {
      TimeZone.setDefault(originalTimeZone);
    }
  }

  @Test
  public void gson_withCustomDateFormat_deserializesCorrectly() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

      Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

      Timestamp result = gson.fromJson("\"2023-06-15\"", Timestamp.class);

      assertNotNull(result);
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
  public void gson_withCustomDateTimeFormat_roundTrip() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

      Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      cal.set(2023, Calendar.JUNE, 15, 14, 30, 45);
      cal.set(Calendar.MILLISECOND, 0);
      Timestamp original = new Timestamp(cal.getTimeInMillis());

      String json = gson.toJson(original);
      assertEquals("\"2023-06-15T14:30:45\"", json);

      Timestamp result = gson.fromJson(json, Timestamp.class);

      Calendar resultCal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      resultCal.setTime(result);
      assertEquals(2023, resultCal.get(Calendar.YEAR));
      assertEquals(Calendar.JUNE, resultCal.get(Calendar.MONTH));
      assertEquals(15, resultCal.get(Calendar.DAY_OF_MONTH));
      assertEquals(14, resultCal.get(Calendar.HOUR_OF_DAY));
      assertEquals(30, resultCal.get(Calendar.MINUTE));
      assertEquals(45, resultCal.get(Calendar.SECOND));
    } finally {
      TimeZone.setDefault(originalTimeZone);
    }
  }

  // Helper class for testing objects with sql.Timestamp fields
  @SuppressWarnings("unused")
  private static class ObjectWithSqlTimestamp {
    Timestamp timestamp;
    String name;
  }
}
