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

package com.google.gson.typeadapters;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.junit.Test;

@SuppressWarnings("JavaUtilDate")
public final class UtcDateTypeAdapterTest {
  private final Gson gson =
      new GsonBuilder().registerTypeAdapter(Date.class, new UtcDateTypeAdapter()).create();

  @Test
  public void testLocalTimeZone() {
    Date expected = new Date();
    String json = gson.toJson(expected);
    Date actual = gson.fromJson(json, Date.class);
    assertThat(actual.getTime()).isEqualTo(expected.getTime());
  }

  @Test
  public void testDifferentTimeZones() {
    for (String timeZone : TimeZone.getAvailableIDs()) {
      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(timeZone));
      Date expected = cal.getTime();
      String json = gson.toJson(expected);
      // System.out.println(json + ": " + timeZone);
      Date actual = gson.fromJson(json, Date.class);
      assertThat(actual.getTime()).isEqualTo(expected.getTime());
    }
  }

  /**
   * JDK 1.7 introduced support for XXX format to indicate UTC date. But Android is older JDK. We
   * want to make sure that this date is parseable in Android.
   */
  @Test
  public void testUtcDatesOnJdkBefore1_7() {
    Gson gson =
        new GsonBuilder().registerTypeAdapter(Date.class, new UtcDateTypeAdapter()).create();
    Date date = gson.fromJson("'2014-12-05T04:00:00.000Z'", Date.class);
    assertThat(date.getTime()).isEqualTo(1417752000000L);
  }

  @Test
  public void testUtcWithJdk7Default() {
    Date expected = new Date();
    SimpleDateFormat iso8601Format =
        new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.US);
    iso8601Format.setTimeZone(TimeZone.getTimeZone("UTC"));
    String expectedJson = "\"" + iso8601Format.format(expected) + "\"";
    String actualJson = gson.toJson(expected);
    assertThat(actualJson).isEqualTo(expectedJson);
    Date actual = gson.fromJson(expectedJson, Date.class);
    assertThat(actual.getTime()).isEqualTo(expected.getTime());
  }

  @Test
  public void testNullDateSerialization() {
    String json = gson.toJson(null, Date.class);
    assertThat(json).isEqualTo("null");
  }

  @Test
  public void testWellFormedParseException() {
    var e =
        assertThrows(
            JsonParseException.class, () -> gson.fromJson("2017-06-20T14:32:30", Date.class));
    assertThat(e)
        .hasMessageThat()
        .isEqualTo(
            "java.text.ParseException: Failed to parse date ['2017-06-20T14']: 2017-06-20T14");
  }

  @Test
  public void testNullDateDeserialization() {
    Date date = gson.fromJson("null", Date.class);
    assertThat(date).isNull();
  }

  @Test
  public void testDateWithPositiveTimezoneOffset() {
    // Date with +05:30 timezone offset (India Standard Time)
    Date date = gson.fromJson("'2014-12-05T09:30:00.000+05:30'", Date.class);
    // 2014-12-05T09:30 IST = 2014-12-05T04:00 UTC = 1417752000000L
    assertThat(date.getTime()).isEqualTo(1417752000000L);
  }

  @Test
  public void testDateWithNegativeTimezoneOffset() {
    // Date with -05:00 timezone offset (EST)
    Date date = gson.fromJson("'2014-12-04T23:00:00.000-05:00'", Date.class);
    // 2014-12-04T23:00 EST = 2014-12-05T04:00 UTC = 1417752000000L
    assertThat(date.getTime()).isEqualTo(1417752000000L);
  }

  @Test
  public void testParseExceptionForMissingTimezoneIndicator() {
    // Date without any timezone indicator
    JsonParseException e =
        assertThrows(
            JsonParseException.class, () -> gson.fromJson("'2017-06-20T14:32:30.000'", Date.class));
    assertThat(e).hasMessageThat().contains("No time zone indicator");
  }

  @Test
  public void testParseExceptionForInvalidTimezoneIndicator() {
    // Date with invalid timezone indicator 'X'
    JsonParseException e =
        assertThrows(
            JsonParseException.class, () -> gson.fromJson("'2017-06-20T14:32:30.000X'", Date.class));
    assertThat(e).hasMessageThat().contains("Invalid time zone indicator");
  }

  @Test
  public void testParseExceptionForInvalidDigitInDate() {
    // Date with invalid character 'A' in year
    JsonParseException e =
        assertThrows(
            JsonParseException.class, () -> gson.fromJson("'201A-06-20T14:32:30.000Z'", Date.class));
    assertThat(e).hasMessageThat().contains("Invalid number");
  }

  @Test
  public void testParseExceptionForInvalidDigitInMiddleOfNumber() {
    // Date with invalid character 'B' in month (second digit)
    JsonParseException e =
        assertThrows(
            JsonParseException.class, () -> gson.fromJson("'2017-0B-20T14:32:30.000Z'", Date.class));
    assertThat(e).hasMessageThat().contains("Invalid number");
  }

  @Test
  public void testFormatWithPositiveTimezoneOffset()
      throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    // Test the private format method with a positive timezone offset (e.g., +05:30 IST)
    Method formatMethod =
        UtcDateTypeAdapter.class.getDeclaredMethod(
            "format", Date.class, boolean.class, TimeZone.class);
    formatMethod.setAccessible(true);

    // Create a date: 2014-12-05T09:30:00.000 in IST (+05:30)
    Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+05:30"));
    cal.set(2014, Calendar.DECEMBER, 5, 9, 30, 0);
    cal.set(Calendar.MILLISECOND, 0);
    Date date = cal.getTime();

    TimeZone istZone = TimeZone.getTimeZone("GMT+05:30");
    String formatted = (String) formatMethod.invoke(null, date, true, istZone);

    // Should format with +05:30 offset
    assertThat(formatted).isEqualTo("2014-12-05T09:30:00.000+05:30");
  }

  @Test
  public void testFormatWithNegativeTimezoneOffset()
      throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    // Test the private format method with a negative timezone offset (e.g., -05:00 EST)
    Method formatMethod =
        UtcDateTypeAdapter.class.getDeclaredMethod(
            "format", Date.class, boolean.class, TimeZone.class);
    formatMethod.setAccessible(true);

    // Create a date: 2014-12-04T23:00:00.000 in EST (-05:00)
    Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT-05:00"));
    cal.set(2014, Calendar.DECEMBER, 4, 23, 0, 0);
    cal.set(Calendar.MILLISECOND, 0);
    Date date = cal.getTime();

    TimeZone estZone = TimeZone.getTimeZone("GMT-05:00");
    String formatted = (String) formatMethod.invoke(null, date, true, estZone);

    // Should format with -05:00 offset
    assertThat(formatted).isEqualTo("2014-12-04T23:00:00.000-05:00");
  }

  @Test
  public void testFormatWithNonZeroMinutesInOffset()
      throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    // Test the private format method with a timezone offset that has non-zero minutes
    // e.g., Nepal Standard Time (+05:45)
    Method formatMethod =
        UtcDateTypeAdapter.class.getDeclaredMethod(
            "format", Date.class, boolean.class, TimeZone.class);
    formatMethod.setAccessible(true);

    // Create a date at a specific time in Nepal timezone
    Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+05:45"));
    cal.set(2020, Calendar.JUNE, 15, 12, 30, 45);
    cal.set(Calendar.MILLISECOND, 123);
    Date date = cal.getTime();

    TimeZone nepalZone = TimeZone.getTimeZone("GMT+05:45");
    String formatted = (String) formatMethod.invoke(null, date, true, nepalZone);

    // Should format with +05:45 offset
    assertThat(formatted).isEqualTo("2020-06-15T12:30:45.123+05:45");
  }
}
