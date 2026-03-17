/*
 * Copyright (C) 2024 Google Inc.
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

package com.google.gson.internal.bind.util;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import org.junit.Test;

public class ISO8601UtilsTest {

  @Test
  public void testFormatDateWithDefaultTimezone() {
    Calendar calendar = new GregorianCalendar(2024, Calendar.MARCH, 15, 10, 30, 45);
    calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
    Date date = calendar.getTime();
    String formatted = ISO8601Utils.format(date);
    assertThat(formatted).isEqualTo("2024-03-15T10:30:45Z");
  }

  @Test
  public void testFormatDateWithMillis() {
    Calendar calendar = new GregorianCalendar(2024, Calendar.MARCH, 15, 10, 30, 45);
    calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
    calendar.set(Calendar.MILLISECOND, 123);
    Date date = calendar.getTime();
    String formatted = ISO8601Utils.format(date, true);
    assertThat(formatted).isEqualTo("2024-03-15T10:30:45.123Z");
  }

  @Test
  public void testFormatDateWithoutMillis() {
    Calendar calendar = new GregorianCalendar(2024, Calendar.MARCH, 15, 10, 30, 45);
    calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
    calendar.set(Calendar.MILLISECOND, 123);
    Date date = calendar.getTime();
    String formatted = ISO8601Utils.format(date, false);
    assertThat(formatted).isEqualTo("2024-03-15T10:30:45Z");
  }

  @Test
  public void testFormatDateWithUTCTimezone() {
    Calendar calendar = new GregorianCalendar(2024, Calendar.MARCH, 15, 10, 30, 45);
    calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
    Date date = calendar.getTime();
    String formatted = ISO8601Utils.format(date, false, TimeZone.getTimeZone("UTC"));
    assertThat(formatted).isEqualTo("2024-03-15T10:30:45Z");
  }

  @Test
  public void testFormatDateWithPositiveTimezoneOffset() {
    Calendar calendar = new GregorianCalendar(2024, Calendar.MARCH, 15, 10, 30, 45);
    calendar.setTimeZone(TimeZone.getTimeZone("GMT+05:30"));
    Date date = calendar.getTime();
    String formatted = ISO8601Utils.format(date, false, TimeZone.getTimeZone("GMT+05:30"));
    assertThat(formatted).contains("+05:30");
  }

  @Test
  public void testFormatDateWithNegativeTimezoneOffset() {
    Calendar calendar = new GregorianCalendar(2024, Calendar.MARCH, 15, 10, 30, 45);
    calendar.setTimeZone(TimeZone.getTimeZone("GMT-08:00"));
    Date date = calendar.getTime();
    String formatted = ISO8601Utils.format(date, false, TimeZone.getTimeZone("GMT-08:00"));
    assertThat(formatted).contains("-08:00");
  }

  @Test
  public void testFormatDateWithMillisAndTimezone() {
    Calendar calendar = new GregorianCalendar(2024, Calendar.MARCH, 15, 10, 30, 45);
    calendar.setTimeZone(TimeZone.getTimeZone("GMT+01:00"));
    calendar.set(Calendar.MILLISECOND, 999);
    Date date = calendar.getTime();
    String formatted = ISO8601Utils.format(date, true, TimeZone.getTimeZone("GMT+01:00"));
    assertThat(formatted).contains(".999");
    assertThat(formatted).contains("+01:00");
  }

  @Test
  public void testParseBasicDate() throws ParseException {
    String dateStr = "2024-03-15T10:30:45Z";
    ParsePosition pos = new ParsePosition(0);
    Date date = ISO8601Utils.parse(dateStr, pos);
    Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
    calendar.setTime(date);
    assertThat(calendar.get(Calendar.YEAR)).isEqualTo(2024);
    assertThat(calendar.get(Calendar.MONTH)).isEqualTo(Calendar.MARCH);
    assertThat(calendar.get(Calendar.DAY_OF_MONTH)).isEqualTo(15);
    assertThat(calendar.get(Calendar.HOUR_OF_DAY)).isEqualTo(10);
    assertThat(calendar.get(Calendar.MINUTE)).isEqualTo(30);
    assertThat(calendar.get(Calendar.SECOND)).isEqualTo(45);
  }

  @Test
  public void testParseDateWithMillis() throws ParseException {
    String dateStr = "2024-03-15T10:30:45.123Z";
    ParsePosition pos = new ParsePosition(0);
    Date date = ISO8601Utils.parse(dateStr, pos);
    Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
    calendar.setTime(date);
    assertThat(calendar.get(Calendar.MILLISECOND)).isEqualTo(123);
  }

  @Test
  public void testParseDateWithSingleDigitMillis() throws ParseException {
    String dateStr = "2024-03-15T10:30:45.1Z";
    ParsePosition pos = new ParsePosition(0);
    Date date = ISO8601Utils.parse(dateStr, pos);
    Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
    calendar.setTime(date);
    assertThat(calendar.get(Calendar.MILLISECOND)).isEqualTo(100);
  }

  @Test
  public void testParseDateWithTwoDigitMillis() throws ParseException {
    String dateStr = "2024-03-15T10:30:45.12Z";
    ParsePosition pos = new ParsePosition(0);
    Date date = ISO8601Utils.parse(dateStr, pos);
    Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
    calendar.setTime(date);
    assertThat(calendar.get(Calendar.MILLISECOND)).isEqualTo(120);
  }

  @Test
  public void testParseDateWithPositiveTimezoneOffset() throws ParseException {
    String dateStr = "2024-03-15T10:30:45+05:30";
    ParsePosition pos = new ParsePosition(0);
    Date date = ISO8601Utils.parse(dateStr, pos);
    assertThat(date).isNotNull();
  }

  @Test
  public void testParseDateWithNegativeTimezoneOffset() throws ParseException {
    String dateStr = "2024-03-15T10:30:45-08:00";
    ParsePosition pos = new ParsePosition(0);
    Date date = ISO8601Utils.parse(dateStr, pos);
    assertThat(date).isNotNull();
  }

  @Test
  public void testParseDateWithShortTimezoneOffset() throws ParseException {
    String dateStr = "2024-03-15T10:30:45+05";
    ParsePosition pos = new ParsePosition(0);
    Date date = ISO8601Utils.parse(dateStr, pos);
    assertThat(date).isNotNull();
  }

  @Test
  public void testParseDateWithZeroTimezoneOffset() throws ParseException {
    String dateStr = "2024-03-15T10:30:45+00:00";
    ParsePosition pos = new ParsePosition(0);
    Date date = ISO8601Utils.parse(dateStr, pos);
    assertThat(date).isNotNull();
  }

  @Test
  public void testParseDateWithCompactFormat() throws ParseException {
    String dateStr = "20240315T103045Z";
    ParsePosition pos = new ParsePosition(0);
    Date date = ISO8601Utils.parse(dateStr, pos);
    Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
    calendar.setTime(date);
    assertThat(calendar.get(Calendar.YEAR)).isEqualTo(2024);
    assertThat(calendar.get(Calendar.MONTH)).isEqualTo(Calendar.MARCH);
    assertThat(calendar.get(Calendar.DAY_OF_MONTH)).isEqualTo(15);
  }

  @Test
  public void testParseDateWithoutTime() throws ParseException {
    String dateStr = "2024-03-15";
    ParsePosition pos = new ParsePosition(0);
    Date date = ISO8601Utils.parse(dateStr, pos);
    Calendar calendar = new GregorianCalendar();
    calendar.setTime(date);
    assertThat(calendar.get(Calendar.YEAR)).isEqualTo(2024);
    assertThat(calendar.get(Calendar.MONTH)).isEqualTo(Calendar.MARCH);
    assertThat(calendar.get(Calendar.DAY_OF_MONTH)).isEqualTo(15);
  }

  @Test
  public void testParseDateWithoutSeconds() throws ParseException {
    String dateStr = "2024-03-15T10:30Z";
    ParsePosition pos = new ParsePosition(0);
    Date date = ISO8601Utils.parse(dateStr, pos);
    Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
    calendar.setTime(date);
    assertThat(calendar.get(Calendar.HOUR_OF_DAY)).isEqualTo(10);
    assertThat(calendar.get(Calendar.MINUTE)).isEqualTo(30);
    assertThat(calendar.get(Calendar.SECOND)).isEqualTo(0);
  }

  @Test
  public void testParseDateWithLeapSecond() throws ParseException {
    String dateStr = "2024-03-15T10:30:60Z";
    ParsePosition pos = new ParsePosition(0);
    Date date = ISO8601Utils.parse(dateStr, pos);
    Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
    calendar.setTime(date);
    assertThat(calendar.get(Calendar.SECOND)).isEqualTo(59);
  }

  @Test
  public void testParseDateWithDoubleLeapSecond() throws ParseException {
    String dateStr = "2024-03-15T10:30:61Z";
    ParsePosition pos = new ParsePosition(0);
    Date date = ISO8601Utils.parse(dateStr, pos);
    Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
    calendar.setTime(date);
    assertThat(calendar.get(Calendar.SECOND)).isEqualTo(59);
  }

  @Test
  public void testParseDateWithTripleLeapSecond() throws ParseException {
    String dateStr = "2024-03-15T10:30:62Z";
    ParsePosition pos = new ParsePosition(0);
    Date date = ISO8601Utils.parse(dateStr, pos);
    Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
    calendar.setTime(date);
    assertThat(calendar.get(Calendar.SECOND)).isEqualTo(59);
  }

  @Test
  public void testParseInvalidDateNoTimezone() {
    String dateStr = "2024-03-15T10:30:45";
    ParsePosition pos = new ParsePosition(0);
    try {
      ISO8601Utils.parse(dateStr, pos);
      fail("Expected ParseException");
    } catch (ParseException e) {
      assertThat(e.getMessage()).contains("No time zone indicator");
    }
  }

  @Test
  public void testParseInvalidDateBadTimezone() {
    String dateStr = "2024-03-15T10:30:45X";
    ParsePosition pos = new ParsePosition(0);
    try {
      ISO8601Utils.parse(dateStr, pos);
      fail("Expected ParseException");
    } catch (ParseException e) {
      assertThat(e.getMessage()).contains("Invalid time zone indicator");
    }
  }

  @Test
  public void testParseInvalidDateBadFormat() {
    String dateStr = "not-a-date";
    ParsePosition pos = new ParsePosition(0);
    try {
      ISO8601Utils.parse(dateStr, pos);
      fail("Expected ParseException");
    } catch (ParseException e) {
      assertThat(e.getMessage()).contains("Failed to parse date");
    }
  }

  @Test(expected = NullPointerException.class)
  public void testParseNullDate() throws ParseException {
    ParsePosition pos = new ParsePosition(0);
    ISO8601Utils.parse(null, pos);
  }

  @Test
  public void testParseWithNonZeroStartPosition() throws ParseException {
    String dateStr = "prefix2024-03-15T10:30:45Z";
    ParsePosition pos = new ParsePosition(6);
    Date date = ISO8601Utils.parse(dateStr, pos);
    assertThat(date).isNotNull();
  }

  @Test
  public void testParseDateWithInvalidTimezoneId() {
    String dateStr = "2024-03-15T10:30:45+99:99";
    ParsePosition pos = new ParsePosition(0);
    try {
      ISO8601Utils.parse(dateStr, pos);
      fail("Expected ParseException");
    } catch (ParseException e) {
      assertThat(e.getMessage()).contains("Mismatching time zone indicator");
    }
  }

  @Test
  public void testFormatDateWithSingleDigitMonth() {
    Calendar calendar = new GregorianCalendar(2024, Calendar.JANUARY, 5, 10, 30, 45);
    calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
    Date date = calendar.getTime();
    String formatted = ISO8601Utils.format(date);
    assertThat(formatted).isEqualTo("2024-01-05T10:30:45Z");
  }

  @Test
  public void testFormatDateWithSingleDigitDay() {
    Calendar calendar = new GregorianCalendar(2024, Calendar.MARCH, 5, 10, 30, 45);
    calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
    Date date = calendar.getTime();
    String formatted = ISO8601Utils.format(date);
    assertThat(formatted).isEqualTo("2024-03-05T10:30:45Z");
  }

  @Test
  public void testFormatDateWithSingleDigitHour() {
    Calendar calendar = new GregorianCalendar(2024, Calendar.MARCH, 15, 5, 30, 45);
    calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
    Date date = calendar.getTime();
    String formatted = ISO8601Utils.format(date);
    assertThat(formatted).isEqualTo("2024-03-15T05:30:45Z");
  }

  @Test
  public void testFormatDateWithSingleDigitMinute() {
    Calendar calendar = new GregorianCalendar(2024, Calendar.MARCH, 15, 10, 5, 45);
    calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
    Date date = calendar.getTime();
    String formatted = ISO8601Utils.format(date);
    assertThat(formatted).isEqualTo("2024-03-15T10:05:45Z");
  }

  @Test
  public void testFormatDateWithSingleDigitSecond() {
    Calendar calendar = new GregorianCalendar(2024, Calendar.MARCH, 15, 10, 30, 5);
    calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
    Date date = calendar.getTime();
    String formatted = ISO8601Utils.format(date);
    assertThat(formatted).isEqualTo("2024-03-15T10:30:05Z");
  }

  @Test
  public void testFormatDateWithSingleDigitMillis() {
    Calendar calendar = new GregorianCalendar(2024, Calendar.MARCH, 15, 10, 30, 45);
    calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
    calendar.set(Calendar.MILLISECOND, 5);
    Date date = calendar.getTime();
    String formatted = ISO8601Utils.format(date, true);
    assertThat(formatted).isEqualTo("2024-03-15T10:30:45.005Z");
  }

  @Test
  public void testFormatDateWithTwoDigitMillis() {
    Calendar calendar = new GregorianCalendar(2024, Calendar.MARCH, 15, 10, 30, 45);
    calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
    calendar.set(Calendar.MILLISECOND, 50);
    Date date = calendar.getTime();
    String formatted = ISO8601Utils.format(date, true);
    assertThat(formatted).isEqualTo("2024-03-15T10:30:45.050Z");
  }

  @Test
  public void testFormatDateWithMidnightTime() {
    Calendar calendar = new GregorianCalendar(2024, Calendar.MARCH, 15, 0, 0, 0);
    calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
    calendar.set(Calendar.MILLISECOND, 0);
    Date date = calendar.getTime();
    String formatted = ISO8601Utils.format(date);
    assertThat(formatted).isEqualTo("2024-03-15T00:00:00Z");
  }

  @Test
  public void testFormatDateWithEndOfDayTime() {
    Calendar calendar = new GregorianCalendar(2024, Calendar.MARCH, 15, 23, 59, 59);
    calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
    calendar.set(Calendar.MILLISECOND, 999);
    Date date = calendar.getTime();
    String formatted = ISO8601Utils.format(date, true);
    assertThat(formatted).isEqualTo("2024-03-15T23:59:59.999Z");
  }

  @Test
  public void testParseDateWithCompactFormatNoColons() throws ParseException {
    String dateStr = "20240315T1030Z";
    ParsePosition pos = new ParsePosition(0);
    Date date = ISO8601Utils.parse(dateStr, pos);
    Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
    calendar.setTime(date);
    assertThat(calendar.get(Calendar.HOUR_OF_DAY)).isEqualTo(10);
    assertThat(calendar.get(Calendar.MINUTE)).isEqualTo(30);
  }

  @Test
  public void testParseDateCompactWithSeconds() throws ParseException {
    String dateStr = "20240315T103045Z";
    ParsePosition pos = new ParsePosition(0);
    Date date = ISO8601Utils.parse(dateStr, pos);
    Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
    calendar.setTime(date);
    assertThat(calendar.get(Calendar.SECOND)).isEqualTo(45);
  }

  @Test
  public void testParseDateWithTimezoneOffsetNoColon() throws ParseException {
    String dateStr = "2024-03-15T10:30:45+0530";
    ParsePosition pos = new ParsePosition(0);
    Date date = ISO8601Utils.parse(dateStr, pos);
    assertThat(date).isNotNull();
  }

  @Test
  public void testFormatRoundTrip() throws ParseException {
    Calendar calendar = new GregorianCalendar(2024, Calendar.MARCH, 15, 10, 30, 45);
    calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
    calendar.set(Calendar.MILLISECOND, 123);
    Date originalDate = calendar.getTime();

    String formatted = ISO8601Utils.format(originalDate, true);
    ParsePosition pos = new ParsePosition(0);
    Date parsedDate = ISO8601Utils.parse(formatted, pos);

    assertThat(parsedDate.getTime()).isEqualTo(originalDate.getTime());
  }

  @Test
  public void testFormatRoundTripWithoutMillis() throws ParseException {
    Calendar calendar = new GregorianCalendar(2024, Calendar.MARCH, 15, 10, 30, 45);
    calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
    calendar.set(Calendar.MILLISECOND, 0);
    Date originalDate = calendar.getTime();

    String formatted = ISO8601Utils.format(originalDate, false);
    ParsePosition pos = new ParsePosition(0);
    Date parsedDate = ISO8601Utils.parse(formatted, pos);

    assertThat(parsedDate.getTime()).isEqualTo(originalDate.getTime());
  }

  @Test
  public void testParseUpdatesPosition() throws ParseException {
    String dateStr = "2024-03-15T10:30:45Z";
    ParsePosition pos = new ParsePosition(0);
    ISO8601Utils.parse(dateStr, pos);
    assertThat(pos.getIndex()).isEqualTo(dateStr.length());
  }

  @Test
  public void testParseDateWithExtraMillisDigits() throws ParseException {
    String dateStr = "2024-03-15T10:30:45.123456Z";
    ParsePosition pos = new ParsePosition(0);
    Date date = ISO8601Utils.parse(dateStr, pos);
    Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
    calendar.setTime(date);
    assertThat(calendar.get(Calendar.MILLISECOND)).isEqualTo(123);
  }

  @Test
  public void testFormatDateWithTimezoneHavingMinutesOnly() {
    Calendar calendar = new GregorianCalendar(2024, Calendar.MARCH, 15, 10, 30, 45);
    calendar.setTimeZone(TimeZone.getTimeZone("GMT+00:30"));
    Date date = calendar.getTime();
    String formatted = ISO8601Utils.format(date, false, TimeZone.getTimeZone("GMT+00:30"));
    assertThat(formatted).contains("+00:30");
  }

  @Test
  public void testParseDateWithZeroOffset() throws ParseException {
    String dateStr = "2024-03-15T10:30:45+0000";
    ParsePosition pos = new ParsePosition(0);
    Date date = ISO8601Utils.parse(dateStr, pos);
    assertThat(date).isNotNull();
  }

  @Test
  public void testParseDateTooShort() {
    String dateStr = "202";
    ParsePosition pos = new ParsePosition(0);
    try {
      ISO8601Utils.parse(dateStr, pos);
      fail("Expected ParseException");
    } catch (ParseException e) {
      assertThat(e.getMessage()).contains("Failed to parse date");
    }
  }

  @Test
  public void testParseDateWithInvalidCharacter() {
    String dateStr = "2024-0X-15T10:30:45Z";
    ParsePosition pos = new ParsePosition(0);
    try {
      ISO8601Utils.parse(dateStr, pos);
      fail("Expected ParseException");
    } catch (ParseException e) {
      assertThat(e.getMessage()).contains("Failed to parse date");
    }
  }

  @Test
  public void testParseDateWithAllDigitMillisUntilEnd() throws ParseException {
    String dateStr = "2024-03-15T10:30:45.123";
    ParsePosition pos = new ParsePosition(0);
    try {
      ISO8601Utils.parse(dateStr, pos);
      fail("Expected ParseException");
    } catch (ParseException e) {
      assertThat(e.getMessage()).contains("No time zone indicator");
    }
  }

  @Test
  public void testParseDateWithInvalidMonthCharacter() {
    String dateStr = "2024-AB-15T10:30:45Z";
    ParsePosition pos = new ParsePosition(0);
    try {
      ISO8601Utils.parse(dateStr, pos);
      fail("Expected ParseException");
    } catch (ParseException e) {
      assertThat(e.getMessage()).contains("Invalid number");
    }
  }

  @Test
  public void testParseDateWithInvalidDayCharacter() {
    String dateStr = "2024-03-XYT10:30:45Z";
    ParsePosition pos = new ParsePosition(0);
    try {
      ISO8601Utils.parse(dateStr, pos);
      fail("Expected ParseException");
    } catch (ParseException e) {
      assertThat(e.getMessage()).contains("Invalid number");
    }
  }

  @Test
  public void testParseDateWithInvalidHourCharacter() {
    String dateStr = "2024-03-15TXY:30:45Z";
    ParsePosition pos = new ParsePosition(0);
    try {
      ISO8601Utils.parse(dateStr, pos);
      fail("Expected ParseException");
    } catch (ParseException e) {
      assertThat(e.getMessage()).contains("Invalid number");
    }
  }
}
