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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.bind.DefaultDateTypeAdapter.DateType;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.junit.Test;

/**
 * Tests for {@link DefaultDateTypeAdapter.DateType} constructor.
 *
 * <p>The DateType constructor is protected, so we test it by creating custom subclasses that call
 * the constructor. This exercises the constructor code path (lines 93-95).
 */
public class DefaultDateTypeAdapterClaude_constructorTest {

  /**
   * Custom DateType implementation for java.sql.Timestamp. This tests the protected constructor by
   * creating a new subclass that calls super(Class).
   */
  private static final DateType<Timestamp> TIMESTAMP =
      new DateType<Timestamp>(Timestamp.class) {
        @Override
        protected Timestamp deserialize(Date date) {
          return new Timestamp(date.getTime());
        }
      };

  @Test
  public void constructor_withTimestampClass_createsValidDateType() {
    // Creating the TIMESTAMP DateType above already exercises the constructor.
    // This test verifies that the DateType was correctly initialized.
    TypeAdapterFactory factory = TIMESTAMP.createAdapterFactory("yyyy-MM-dd HH:mm:ss");
    assertNotNull(factory);
  }

  @Test
  public void constructor_customDateType_canCreatePatternBasedFactory() {
    TypeAdapterFactory factory = TIMESTAMP.createAdapterFactory("yyyy-MM-dd HH:mm:ss");
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
    TypeAdapter<Timestamp> adapter = gson.getAdapter(Timestamp.class);
    assertNotNull(adapter);
  }

  @Test
  public void constructor_customDateType_canCreateStyleBasedFactory() {
    TypeAdapterFactory factory = TIMESTAMP.createAdapterFactory(DateFormat.SHORT, DateFormat.SHORT);
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
    TypeAdapter<Timestamp> adapter = gson.getAdapter(Timestamp.class);
    assertNotNull(adapter);
  }

  @Test
  public void constructor_customDateType_canSerializeTimestamp() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

      TypeAdapterFactory factory = TIMESTAMP.createAdapterFactory("yyyy-MM-dd HH:mm:ss");
      Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
      TypeAdapter<Timestamp> adapter = gson.getAdapter(Timestamp.class);

      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      cal.set(2023, Calendar.JUNE, 15, 14, 30, 45);
      cal.set(Calendar.MILLISECOND, 0);
      Timestamp timestamp = new Timestamp(cal.getTimeInMillis());

      StringWriter stringWriter = new StringWriter();
      JsonWriter writer = new JsonWriter(stringWriter);
      adapter.write(writer, timestamp);
      assertEquals("\"2023-06-15 14:30:45\"", stringWriter.toString());
    } finally {
      TimeZone.setDefault(originalTimeZone);
    }
  }

  @Test
  public void constructor_customDateType_canDeserializeTimestamp() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

      TypeAdapterFactory factory = TIMESTAMP.createAdapterFactory("yyyy-MM-dd HH:mm:ss");
      Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
      TypeAdapter<Timestamp> adapter = gson.getAdapter(Timestamp.class);

      JsonReader reader = new JsonReader(new StringReader("\"2023-06-15 14:30:45\""));
      Timestamp result = adapter.read(reader);

      assertNotNull(result);
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

  @Test
  public void constructor_customDateType_roundTripPreservesData() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

      TypeAdapterFactory factory = TIMESTAMP.createAdapterFactory("yyyy-MM-dd HH:mm:ss");
      Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();

      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      cal.set(2023, Calendar.JUNE, 15, 14, 30, 45);
      cal.set(Calendar.MILLISECOND, 0);
      Timestamp original = new Timestamp(cal.getTimeInMillis());

      String json = gson.toJson(original, Timestamp.class);
      Timestamp result = gson.fromJson(json, Timestamp.class);

      // Compare time values (ignoring nanos precision beyond milliseconds)
      assertEquals(original.getTime() / 1000, result.getTime() / 1000);
    } finally {
      TimeZone.setDefault(originalTimeZone);
    }
  }

  @Test
  public void constructor_builtInDateType_isCorrectlyInitialized() {
    // Verify that the built-in DateType.DATE is correctly initialized via the constructor
    TypeAdapterFactory factory = DateType.DATE.createAdapterFactory("yyyy-MM-dd");
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
    TypeAdapter<Date> adapter = gson.getAdapter(Date.class);
    assertNotNull(adapter);
  }

  @Test
  public void constructor_customDateType_factoryReturnsNullForWrongType() {
    TypeAdapterFactory factory = TIMESTAMP.createAdapterFactory("yyyy-MM-dd");
    Gson gson = new Gson();
    // Factory should return null for Date.class since it was created for Timestamp.class
    TypeAdapter<Date> adapter = factory.create(gson, com.google.gson.reflect.TypeToken.get(Date.class));
    // The factory created for Timestamp should not match Date
    assertEquals(null, adapter);
  }

  /**
   * Another custom DateType to verify the constructor works with different Date subclasses. Using
   * java.sql.Date which is a different class than java.util.Date.
   */
  private static final DateType<java.sql.Date> SQL_DATE =
      new DateType<java.sql.Date>(java.sql.Date.class) {
        @Override
        protected java.sql.Date deserialize(Date date) {
          return new java.sql.Date(date.getTime());
        }
      };

  @Test
  public void constructor_sqlDateType_canCreateFactory() {
    TypeAdapterFactory factory = SQL_DATE.createAdapterFactory("yyyy-MM-dd");
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
    TypeAdapter<java.sql.Date> adapter = gson.getAdapter(java.sql.Date.class);
    assertNotNull(adapter);
  }

  @Test
  public void constructor_sqlDateType_canSerializeAndDeserialize() throws IOException {
    TimeZone originalTimeZone = TimeZone.getDefault();
    try {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

      TypeAdapterFactory factory = SQL_DATE.createAdapterFactory("yyyy-MM-dd");
      Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();

      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      cal.set(2023, Calendar.JUNE, 15, 0, 0, 0);
      cal.set(Calendar.MILLISECOND, 0);
      java.sql.Date original = new java.sql.Date(cal.getTimeInMillis());

      String json = gson.toJson(original, java.sql.Date.class);
      assertEquals("\"2023-06-15\"", json);

      java.sql.Date result = gson.fromJson(json, java.sql.Date.class);
      assertNotNull(result);

      Calendar resultCal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      resultCal.setTime(result);
      assertEquals(2023, resultCal.get(Calendar.YEAR));
      assertEquals(Calendar.JUNE, resultCal.get(Calendar.MONTH));
      assertEquals(15, resultCal.get(Calendar.DAY_OF_MONTH));
    } finally {
      TimeZone.setDefault(originalTimeZone);
    }
  }
}
