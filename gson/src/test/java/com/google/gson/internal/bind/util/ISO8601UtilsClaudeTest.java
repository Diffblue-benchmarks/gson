/*
 * Copyright (C) 2015 Google Inc.
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

package com.google.gson.internal.bind.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import org.junit.Test;

/** Tests for {@link ISO8601Utils}. */
public class ISO8601UtilsClaudeTest {

  private static final TimeZone UTC = TimeZone.getTimeZone("UTC");

  // ==========================================================================
  // format(Date) tests
  // ==========================================================================

  @Test
  public void format_basicDate_returnsIso8601WithZSuffix() {
    Calendar cal = new GregorianCalendar(UTC);
    cal.set(2023, Calendar.JUNE, 15, 14, 30, 45);
    cal.set(Calendar.MILLISECOND, 0);
    Date date = cal.getTime();

    String result = ISO8601Utils.format(date);

    assertEquals("2023-06-15T14:30:45Z", result);
  }

  @Test
  public void format_epochDate_returnsCorrectFormat() {
    Date epoch = new Date(0L);

    String result = ISO8601Utils.format(epoch);

    assertEquals("1970-01-01T00:00:00Z", result);
  }

  @Test
  public void format_dateWithLeadingZeros_padsCorrectly() {
    Calendar cal = new GregorianCalendar(UTC);
    cal.set(2023, Calendar.JANUARY, 5, 3, 7, 9);
    cal.set(Calendar.MILLISECOND, 0);
    Date date = cal.getTime();

    String result = ISO8601Utils.format(date);

    assertEquals("2023-01-05T03:07:09Z", result);
  }

  @Test
  public void format_yearBoundary_formatsCorrectly() {
    Calendar cal = new GregorianCalendar(UTC);
    cal.set(2023, Calendar.DECEMBER, 31, 23, 59, 59);
    cal.set(Calendar.MILLISECOND, 0);
    Date date = cal.getTime();

    String result = ISO8601Utils.format(date);

    assertEquals("2023-12-31T23:59:59Z", result);
  }

  @Test
  public void format_leapYear_february29_formatsCorrectly() {
    Calendar cal = new GregorianCalendar(UTC);
    cal.set(2024, Calendar.FEBRUARY, 29, 12, 0, 0);
    cal.set(Calendar.MILLISECOND, 0);
    Date date = cal.getTime();

    String result = ISO8601Utils.format(date);

    assertEquals("2024-02-29T12:00:00Z", result);
  }

  // ==========================================================================
  // format(Date, boolean) tests - millis = false
  // ==========================================================================

  @Test
  public void format_withoutMillis_omitsMilliseconds() {
    Calendar cal = new GregorianCalendar(UTC);
    cal.set(2023, Calendar.JUNE, 15, 14, 30, 45);
    cal.set(Calendar.MILLISECOND, 123);
    Date date = cal.getTime();

    String result = ISO8601Utils.format(date, false);

    assertEquals("2023-06-15T14:30:45Z", result);
  }

  // ==========================================================================
  // format(Date, boolean) tests - millis = true
  // ==========================================================================

  @Test
  public void format_withMillis_includesMilliseconds() {
    Calendar cal = new GregorianCalendar(UTC);
    cal.set(2023, Calendar.JUNE, 15, 14, 30, 45);
    cal.set(Calendar.MILLISECOND, 123);
    Date date = cal.getTime();

    String result = ISO8601Utils.format(date, true);

    assertEquals("2023-06-15T14:30:45.123Z", result);
  }

  @Test
  public void format_withMillis_zeroMilliseconds_padsCorrectly() {
    Calendar cal = new GregorianCalendar(UTC);
    cal.set(2023, Calendar.JUNE, 15, 14, 30, 45);
    cal.set(Calendar.MILLISECOND, 0);
    Date date = cal.getTime();

    String result = ISO8601Utils.format(date, true);

    assertEquals("2023-06-15T14:30:45.000Z", result);
  }

  @Test
  public void format_withMillis_singleDigitMillis_padsCorrectly() {
    Calendar cal = new GregorianCalendar(UTC);
    cal.set(2023, Calendar.JUNE, 15, 14, 30, 45);
    cal.set(Calendar.MILLISECOND, 5);
    Date date = cal.getTime();

    String result = ISO8601Utils.format(date, true);

    assertEquals("2023-06-15T14:30:45.005Z", result);
  }

  @Test
  public void format_withMillis_twoDigitMillis_padsCorrectly() {
    Calendar cal = new GregorianCalendar(UTC);
    cal.set(2023, Calendar.JUNE, 15, 14, 30, 45);
    cal.set(Calendar.MILLISECOND, 50);
    Date date = cal.getTime();

    String result = ISO8601Utils.format(date, true);

    assertEquals("2023-06-15T14:30:45.050Z", result);
  }

  @Test
  public void format_withMillis_maxMillis_formatsCorrectly() {
    Calendar cal = new GregorianCalendar(UTC);
    cal.set(2023, Calendar.JUNE, 15, 14, 30, 45);
    cal.set(Calendar.MILLISECOND, 999);
    Date date = cal.getTime();

    String result = ISO8601Utils.format(date, true);

    assertEquals("2023-06-15T14:30:45.999Z", result);
  }

  // ==========================================================================
  // format(Date, boolean, TimeZone) tests - UTC timezone
  // ==========================================================================

  @Test
  public void format_utcTimezone_producesZSuffix() {
    Calendar cal = new GregorianCalendar(UTC);
    cal.set(2023, Calendar.JUNE, 15, 14, 30, 45);
    cal.set(Calendar.MILLISECOND, 0);
    Date date = cal.getTime();

    String result = ISO8601Utils.format(date, false, UTC);

    assertEquals("2023-06-15T14:30:45Z", result);
  }

  // ==========================================================================
  // format(Date, boolean, TimeZone) tests - positive offset timezone
  // ==========================================================================

  @Test
  public void format_positiveOffsetTimezone_producesPositiveOffset() {
    TimeZone tz = TimeZone.getTimeZone("GMT+05:30");
    Calendar cal = new GregorianCalendar(tz);
    cal.set(2023, Calendar.JUNE, 15, 14, 30, 45);
    cal.set(Calendar.MILLISECOND, 0);
    Date date = cal.getTime();

    String result = ISO8601Utils.format(date, false, tz);

    assertEquals("2023-06-15T14:30:45+05:30", result);
  }

  @Test
  public void format_positiveWholeHourOffset_producesCorrectOffset() {
    TimeZone tz = TimeZone.getTimeZone("GMT+08:00");
    Calendar cal = new GregorianCalendar(tz);
    cal.set(2023, Calendar.JUNE, 15, 14, 30, 45);
    cal.set(Calendar.MILLISECOND, 0);
    Date date = cal.getTime();

    String result = ISO8601Utils.format(date, false, tz);

    assertEquals("2023-06-15T14:30:45+08:00", result);
  }

  // ==========================================================================
  // format(Date, boolean, TimeZone) tests - negative offset timezone
  // ==========================================================================

  @Test
  public void format_negativeOffsetTimezone_producesNegativeOffset() {
    TimeZone tz = TimeZone.getTimeZone("GMT-05:00");
    Calendar cal = new GregorianCalendar(tz);
    cal.set(2023, Calendar.JUNE, 15, 14, 30, 45);
    cal.set(Calendar.MILLISECOND, 0);
    Date date = cal.getTime();

    String result = ISO8601Utils.format(date, false, tz);

    assertEquals("2023-06-15T14:30:45-05:00", result);
  }

  @Test
  public void format_negativeHalfHourOffset_producesCorrectOffset() {
    TimeZone tz = TimeZone.getTimeZone("GMT-03:30");
    Calendar cal = new GregorianCalendar(tz);
    cal.set(2023, Calendar.JUNE, 15, 14, 30, 45);
    cal.set(Calendar.MILLISECOND, 0);
    Date date = cal.getTime();

    String result = ISO8601Utils.format(date, false, tz);

    assertEquals("2023-06-15T14:30:45-03:30", result);
  }

  // ==========================================================================
  // format(Date, boolean, TimeZone) tests - combined millis and timezone
  // ==========================================================================

  @Test
  public void format_withMillisAndPositiveTimezone_includesBoth() {
    TimeZone tz = TimeZone.getTimeZone("GMT+05:30");
    Calendar cal = new GregorianCalendar(tz);
    cal.set(2023, Calendar.JUNE, 15, 14, 30, 45);
    cal.set(Calendar.MILLISECOND, 123);
    Date date = cal.getTime();

    String result = ISO8601Utils.format(date, true, tz);

    assertEquals("2023-06-15T14:30:45.123+05:30", result);
  }

  @Test
  public void format_withMillisAndNegativeTimezone_includesBoth() {
    TimeZone tz = TimeZone.getTimeZone("GMT-07:00");
    Calendar cal = new GregorianCalendar(tz);
    cal.set(2023, Calendar.JUNE, 15, 14, 30, 45);
    cal.set(Calendar.MILLISECOND, 456);
    Date date = cal.getTime();

    String result = ISO8601Utils.format(date, true, tz);

    assertEquals("2023-06-15T14:30:45.456-07:00", result);
  }

  // ==========================================================================
  // parse(String, ParsePosition) tests - basic date-time with Z
  // ==========================================================================

  @Test
  public void parse_basicDateTimeWithZ_parsesCorrectly() throws ParseException {
    ParsePosition pos = new ParsePosition(0);

    Date result = ISO8601Utils.parse("2023-06-15T14:30:45Z", pos);

    assertNotNull(result);
    Calendar cal = new GregorianCalendar(UTC);
    cal.setTime(result);
    assertEquals(2023, cal.get(Calendar.YEAR));
    assertEquals(Calendar.JUNE, cal.get(Calendar.MONTH));
    assertEquals(15, cal.get(Calendar.DAY_OF_MONTH));
    assertEquals(14, cal.get(Calendar.HOUR_OF_DAY));
    assertEquals(30, cal.get(Calendar.MINUTE));
    assertEquals(45, cal.get(Calendar.SECOND));
    assertEquals(0, cal.get(Calendar.MILLISECOND));
    assertEquals(20, pos.getIndex());
  }

  @Test
  public void parse_dateTimeWithMillisAndZ_parsesCorrectly() throws ParseException {
    ParsePosition pos = new ParsePosition(0);

    Date result = ISO8601Utils.parse("2023-06-15T14:30:45.123Z", pos);

    assertNotNull(result);
    Calendar cal = new GregorianCalendar(UTC);
    cal.setTime(result);
    assertEquals(2023, cal.get(Calendar.YEAR));
    assertEquals(Calendar.JUNE, cal.get(Calendar.MONTH));
    assertEquals(15, cal.get(Calendar.DAY_OF_MONTH));
    assertEquals(14, cal.get(Calendar.HOUR_OF_DAY));
    assertEquals(30, cal.get(Calendar.MINUTE));
    assertEquals(45, cal.get(Calendar.SECOND));
    assertEquals(123, cal.get(Calendar.MILLISECOND));
    assertEquals(24, pos.getIndex());
  }

  // ==========================================================================
  // parse(String, ParsePosition) tests - compact format without separators
  // ==========================================================================

  @Test
  public void parse_compactDateFormat_parsesCorrectly() throws ParseException {
    ParsePosition pos = new ParsePosition(0);

    Date result = ISO8601Utils.parse("20230615T143045Z", pos);

    assertNotNull(result);
    Calendar cal = new GregorianCalendar(UTC);
    cal.setTime(result);
    assertEquals(2023, cal.get(Calendar.YEAR));
    assertEquals(Calendar.JUNE, cal.get(Calendar.MONTH));
    assertEquals(15, cal.get(Calendar.DAY_OF_MONTH));
    assertEquals(14, cal.get(Calendar.HOUR_OF_DAY));
    assertEquals(30, cal.get(Calendar.MINUTE));
    assertEquals(45, cal.get(Calendar.SECOND));
  }

  @Test
  public void parse_compactTimeFormat_parsesCorrectly() throws ParseException {
    ParsePosition pos = new ParsePosition(0);

    Date result = ISO8601Utils.parse("2023-06-15T1430Z", pos);

    assertNotNull(result);
    Calendar cal = new GregorianCalendar(UTC);
    cal.setTime(result);
    assertEquals(14, cal.get(Calendar.HOUR_OF_DAY));
    assertEquals(30, cal.get(Calendar.MINUTE));
    assertEquals(0, cal.get(Calendar.SECOND));
  }

  // ==========================================================================
  // parse(String, ParsePosition) tests - date only (no time)
  // ==========================================================================

  @Test
  public void parse_dateOnlyFormat_parsesWithDefaultTimeAsZero() throws ParseException {
    ParsePosition pos = new ParsePosition(0);

    Date result = ISO8601Utils.parse("2023-06-15", pos);

    assertNotNull(result);
    Calendar cal = Calendar.getInstance();
    cal.setTime(result);
    assertEquals(2023, cal.get(Calendar.YEAR));
    assertEquals(Calendar.JUNE, cal.get(Calendar.MONTH));
    assertEquals(15, cal.get(Calendar.DAY_OF_MONTH));
    assertEquals(0, cal.get(Calendar.HOUR_OF_DAY));
    assertEquals(0, cal.get(Calendar.MINUTE));
    assertEquals(0, cal.get(Calendar.SECOND));
    assertEquals(10, pos.getIndex());
  }

  @Test
  public void parse_compactDateOnlyFormat_parsesCorrectly() throws ParseException {
    ParsePosition pos = new ParsePosition(0);

    Date result = ISO8601Utils.parse("20230615", pos);

    assertNotNull(result);
    Calendar cal = Calendar.getInstance();
    cal.setTime(result);
    assertEquals(2023, cal.get(Calendar.YEAR));
    assertEquals(Calendar.JUNE, cal.get(Calendar.MONTH));
    assertEquals(15, cal.get(Calendar.DAY_OF_MONTH));
    assertEquals(8, pos.getIndex());
  }

  // ==========================================================================
  // parse(String, ParsePosition) tests - positive timezone offset
  // ==========================================================================

  @Test
  public void parse_positiveTimezoneWithColon_parsesCorrectly() throws ParseException {
    ParsePosition pos = new ParsePosition(0);

    Date result = ISO8601Utils.parse("2023-06-15T14:30:45+05:30", pos);

    assertNotNull(result);
    // The result should be the same instant as 2023-06-15T09:00:45Z
    Calendar cal = new GregorianCalendar(UTC);
    cal.setTime(result);
    assertEquals(2023, cal.get(Calendar.YEAR));
    assertEquals(Calendar.JUNE, cal.get(Calendar.MONTH));
    assertEquals(15, cal.get(Calendar.DAY_OF_MONTH));
    assertEquals(9, cal.get(Calendar.HOUR_OF_DAY));
    assertEquals(0, cal.get(Calendar.MINUTE));
    assertEquals(45, cal.get(Calendar.SECOND));
    assertEquals(25, pos.getIndex());
  }

  @Test
  public void parse_positiveTimezoneWithoutColon_parsesCorrectly() throws ParseException {
    ParsePosition pos = new ParsePosition(0);

    Date result = ISO8601Utils.parse("2023-06-15T14:30:45+0530", pos);

    assertNotNull(result);
    Calendar cal = new GregorianCalendar(UTC);
    cal.setTime(result);
    assertEquals(9, cal.get(Calendar.HOUR_OF_DAY));
    assertEquals(0, cal.get(Calendar.MINUTE));
    assertEquals(24, pos.getIndex());
  }

  @Test
  public void parse_positiveTimezoneHoursOnly_parsesCorrectly() throws ParseException {
    ParsePosition pos = new ParsePosition(0);

    Date result = ISO8601Utils.parse("2023-06-15T14:30:45+05", pos);

    assertNotNull(result);
    Calendar cal = new GregorianCalendar(UTC);
    cal.setTime(result);
    assertEquals(9, cal.get(Calendar.HOUR_OF_DAY));
    assertEquals(30, cal.get(Calendar.MINUTE));
    // Note: offset is incremented by timezoneOffset.length() which becomes 5 after "00" is appended,
    // even though the actual string only has 3 chars for timezone. This is an artifact of the impl.
    assertEquals(24, pos.getIndex());
  }

  // ==========================================================================
  // parse(String, ParsePosition) tests - negative timezone offset
  // ==========================================================================

  @Test
  public void parse_negativeTimezoneWithColon_parsesCorrectly() throws ParseException {
    ParsePosition pos = new ParsePosition(0);

    Date result = ISO8601Utils.parse("2023-06-15T14:30:45-05:00", pos);

    assertNotNull(result);
    // The result should be the same instant as 2023-06-15T19:30:45Z
    Calendar cal = new GregorianCalendar(UTC);
    cal.setTime(result);
    assertEquals(19, cal.get(Calendar.HOUR_OF_DAY));
    assertEquals(30, cal.get(Calendar.MINUTE));
  }

  @Test
  public void parse_negativeTimezoneWithoutColon_parsesCorrectly() throws ParseException {
    ParsePosition pos = new ParsePosition(0);

    Date result = ISO8601Utils.parse("2023-06-15T14:30:45-0500", pos);

    assertNotNull(result);
    Calendar cal = new GregorianCalendar(UTC);
    cal.setTime(result);
    assertEquals(19, cal.get(Calendar.HOUR_OF_DAY));
    assertEquals(30, cal.get(Calendar.MINUTE));
  }

  @Test
  public void parse_negativeTimezoneHoursOnly_parsesCorrectly() throws ParseException {
    ParsePosition pos = new ParsePosition(0);

    Date result = ISO8601Utils.parse("2023-06-15T14:30:45-07", pos);

    assertNotNull(result);
    Calendar cal = new GregorianCalendar(UTC);
    cal.setTime(result);
    assertEquals(21, cal.get(Calendar.HOUR_OF_DAY));
    assertEquals(30, cal.get(Calendar.MINUTE));
  }

  // ==========================================================================
  // parse(String, ParsePosition) tests - +0000 and +00:00 treated as UTC
  // ==========================================================================

  @Test
  public void parse_plusZeroTimezoneWithoutColon_treatedAsUtc() throws ParseException {
    ParsePosition pos = new ParsePosition(0);

    Date result = ISO8601Utils.parse("2023-06-15T14:30:45+0000", pos);

    assertNotNull(result);
    Calendar cal = new GregorianCalendar(UTC);
    cal.setTime(result);
    assertEquals(14, cal.get(Calendar.HOUR_OF_DAY));
    assertEquals(30, cal.get(Calendar.MINUTE));
    assertEquals(45, cal.get(Calendar.SECOND));
  }

  @Test
  public void parse_plusZeroTimezoneWithColon_treatedAsUtc() throws ParseException {
    ParsePosition pos = new ParsePosition(0);

    Date result = ISO8601Utils.parse("2023-06-15T14:30:45+00:00", pos);

    assertNotNull(result);
    Calendar cal = new GregorianCalendar(UTC);
    cal.setTime(result);
    assertEquals(14, cal.get(Calendar.HOUR_OF_DAY));
    assertEquals(30, cal.get(Calendar.MINUTE));
    assertEquals(45, cal.get(Calendar.SECOND));
  }

  // ==========================================================================
  // parse(String, ParsePosition) tests - millisecond precision variations
  // ==========================================================================

  @Test
  public void parse_oneDigitMillis_parsesCorrectly() throws ParseException {
    ParsePosition pos = new ParsePosition(0);

    Date result = ISO8601Utils.parse("2023-06-15T14:30:45.5Z", pos);

    assertNotNull(result);
    Calendar cal = new GregorianCalendar(UTC);
    cal.setTime(result);
    assertEquals(500, cal.get(Calendar.MILLISECOND));
  }

  @Test
  public void parse_twoDigitMillis_parsesCorrectly() throws ParseException {
    ParsePosition pos = new ParsePosition(0);

    Date result = ISO8601Utils.parse("2023-06-15T14:30:45.12Z", pos);

    assertNotNull(result);
    Calendar cal = new GregorianCalendar(UTC);
    cal.setTime(result);
    assertEquals(120, cal.get(Calendar.MILLISECOND));
  }

  @Test
  public void parse_threeDigitMillis_parsesCorrectly() throws ParseException {
    ParsePosition pos = new ParsePosition(0);

    Date result = ISO8601Utils.parse("2023-06-15T14:30:45.123Z", pos);

    assertNotNull(result);
    Calendar cal = new GregorianCalendar(UTC);
    cal.setTime(result);
    assertEquals(123, cal.get(Calendar.MILLISECOND));
  }

  @Test
  public void parse_moreThanThreeDigitMillis_truncatesToThreeDigits() throws ParseException {
    ParsePosition pos = new ParsePosition(0);

    Date result = ISO8601Utils.parse("2023-06-15T14:30:45.123456789Z", pos);

    assertNotNull(result);
    Calendar cal = new GregorianCalendar(UTC);
    cal.setTime(result);
    // Only first 3 digits are parsed
    assertEquals(123, cal.get(Calendar.MILLISECOND));
  }

  // ==========================================================================
  // parse(String, ParsePosition) tests - optional seconds
  // ==========================================================================

  @Test
  public void parse_noSecondsWithZ_parsesCorrectly() throws ParseException {
    ParsePosition pos = new ParsePosition(0);

    Date result = ISO8601Utils.parse("2023-06-15T14:30Z", pos);

    assertNotNull(result);
    Calendar cal = new GregorianCalendar(UTC);
    cal.setTime(result);
    assertEquals(14, cal.get(Calendar.HOUR_OF_DAY));
    assertEquals(30, cal.get(Calendar.MINUTE));
    assertEquals(0, cal.get(Calendar.SECOND));
  }

  @Test
  public void parse_noSecondsWithTimezone_parsesCorrectly() throws ParseException {
    ParsePosition pos = new ParsePosition(0);

    Date result = ISO8601Utils.parse("2023-06-15T14:30+05:30", pos);

    assertNotNull(result);
    Calendar cal = new GregorianCalendar(UTC);
    cal.setTime(result);
    assertEquals(9, cal.get(Calendar.HOUR_OF_DAY));
    assertEquals(0, cal.get(Calendar.MINUTE));
    assertEquals(0, cal.get(Calendar.SECOND));
  }

  // ==========================================================================
  // parse(String, ParsePosition) tests - leap seconds
  // ==========================================================================

  @Test
  public void parse_leapSecond60_truncatedTo59() throws ParseException {
    ParsePosition pos = new ParsePosition(0);

    Date result = ISO8601Utils.parse("2023-06-15T23:59:60Z", pos);

    assertNotNull(result);
    Calendar cal = new GregorianCalendar(UTC);
    cal.setTime(result);
    assertEquals(59, cal.get(Calendar.SECOND));
  }

  @Test
  public void parse_leapSecond61_truncatedTo59() throws ParseException {
    ParsePosition pos = new ParsePosition(0);

    Date result = ISO8601Utils.parse("2023-06-15T23:59:61Z", pos);

    assertNotNull(result);
    Calendar cal = new GregorianCalendar(UTC);
    cal.setTime(result);
    assertEquals(59, cal.get(Calendar.SECOND));
  }

  @Test
  public void parse_leapSecond62_truncatedTo59() throws ParseException {
    ParsePosition pos = new ParsePosition(0);

    Date result = ISO8601Utils.parse("2023-06-15T23:59:62Z", pos);

    assertNotNull(result);
    Calendar cal = new GregorianCalendar(UTC);
    cal.setTime(result);
    assertEquals(59, cal.get(Calendar.SECOND));
  }

  // ==========================================================================
  // parse(String, ParsePosition) tests - error cases
  // ==========================================================================

  @Test
  public void parse_noTimezoneIndicator_throwsParseException() {
    ParsePosition pos = new ParsePosition(0);

    try {
      ISO8601Utils.parse("2023-06-15T14:30:45", pos);
      fail("Expected ParseException");
    } catch (ParseException e) {
      assertTrue(e.getMessage().contains("No time zone indicator"));
    }
  }

  @Test
  public void parse_invalidTimezoneIndicator_throwsParseException() {
    ParsePosition pos = new ParsePosition(0);

    try {
      ISO8601Utils.parse("2023-06-15T14:30:45X", pos);
      fail("Expected ParseException");
    } catch (ParseException e) {
      assertTrue(e.getMessage().contains("Invalid time zone indicator"));
    }
  }

  @Test
  public void parse_invalidYear_throwsParseException() {
    ParsePosition pos = new ParsePosition(0);

    try {
      ISO8601Utils.parse("ABCD-06-15T14:30:45Z", pos);
      fail("Expected ParseException");
    } catch (ParseException e) {
      assertTrue(e.getMessage().contains("Failed to parse date"));
    }
  }

  @Test
  public void parse_invalidMonth_throwsParseException() {
    ParsePosition pos = new ParsePosition(0);

    try {
      ISO8601Utils.parse("2023-XX-15T14:30:45Z", pos);
      fail("Expected ParseException");
    } catch (ParseException e) {
      assertTrue(e.getMessage().contains("Failed to parse date"));
    }
  }

  @Test
  public void parse_invalidDay_throwsParseException() {
    ParsePosition pos = new ParsePosition(0);

    try {
      ISO8601Utils.parse("2023-06-XXT14:30:45Z", pos);
      fail("Expected ParseException");
    } catch (ParseException e) {
      assertTrue(e.getMessage().contains("Failed to parse date"));
    }
  }

  @Test
  public void parse_invalidHour_throwsParseException() {
    ParsePosition pos = new ParsePosition(0);

    try {
      ISO8601Utils.parse("2023-06-15TXX:30:45Z", pos);
      fail("Expected ParseException");
    } catch (ParseException e) {
      assertTrue(e.getMessage().contains("Failed to parse date"));
    }
  }

  @Test
  public void parse_invalidMinute_throwsParseException() {
    ParsePosition pos = new ParsePosition(0);

    try {
      ISO8601Utils.parse("2023-06-15T14:XX:45Z", pos);
      fail("Expected ParseException");
    } catch (ParseException e) {
      assertTrue(e.getMessage().contains("Failed to parse date"));
    }
  }

  @Test
  public void parse_invalidSecond_throwsParseException() {
    ParsePosition pos = new ParsePosition(0);

    try {
      ISO8601Utils.parse("2023-06-15T14:30:XXZ", pos);
      fail("Expected ParseException");
    } catch (ParseException e) {
      assertTrue(e.getMessage().contains("Failed to parse date"));
    }
  }

  @Test
  public void parse_tooShortString_throwsParseException() {
    ParsePosition pos = new ParsePosition(0);

    try {
      ISO8601Utils.parse("2023", pos);
      fail("Expected ParseException");
    } catch (ParseException e) {
      assertTrue(e.getMessage().contains("Failed to parse date"));
    }
  }

  @Test
  public void parse_emptyString_throwsParseException() {
    ParsePosition pos = new ParsePosition(0);

    try {
      ISO8601Utils.parse("", pos);
      fail("Expected ParseException");
    } catch (ParseException e) {
      assertTrue(e.getMessage().contains("Failed to parse date"));
    }
  }

  @Test
  public void parse_invalidCalendarDate_throwsParseException() {
    ParsePosition pos = new ParsePosition(0);

    try {
      // February 30 is invalid
      ISO8601Utils.parse("2023-02-30", pos);
      fail("Expected ParseException");
    } catch (ParseException e) {
      // Expected - calendar is non-lenient
      assertNotNull(e.getMessage());
    }
  }

  @Test
  public void parse_nonLeapYearFebruary29_throwsParseException() {
    ParsePosition pos = new ParsePosition(0);

    try {
      // 2023 is not a leap year
      ISO8601Utils.parse("2023-02-29", pos);
      fail("Expected ParseException");
    } catch (ParseException e) {
      // Expected - calendar is non-lenient
      assertNotNull(e.getMessage());
    }
  }

  // ==========================================================================
  // parse(String, ParsePosition) tests - ParsePosition handling
  // ==========================================================================

  @Test
  public void parse_withNonZeroStartIndex_parsesFromIndex() throws ParseException {
    ParsePosition pos = new ParsePosition(5);

    // Prefix "DATE:" before the actual date
    Date result = ISO8601Utils.parse("DATE:2023-06-15T14:30:45Z", pos);

    assertNotNull(result);
    Calendar cal = new GregorianCalendar(UTC);
    cal.setTime(result);
    assertEquals(2023, cal.get(Calendar.YEAR));
    assertEquals(Calendar.JUNE, cal.get(Calendar.MONTH));
    assertEquals(15, cal.get(Calendar.DAY_OF_MONTH));
    assertEquals(25, pos.getIndex());
  }

  @Test
  public void parse_updatesParsePositionCorrectly() throws ParseException {
    ParsePosition pos = new ParsePosition(0);

    ISO8601Utils.parse("2023-06-15T14:30:45Z", pos);

    assertEquals(20, pos.getIndex());
  }

  // ==========================================================================
  // parse(String, ParsePosition) tests - special cases
  // ==========================================================================

  @Test
  public void parse_epochDate_parsesCorrectly() throws ParseException {
    ParsePosition pos = new ParsePosition(0);

    Date result = ISO8601Utils.parse("1970-01-01T00:00:00Z", pos);

    assertNotNull(result);
    assertEquals(0L, result.getTime());
  }

  @Test
  public void parse_maxValidDate_parsesCorrectly() throws ParseException {
    ParsePosition pos = new ParsePosition(0);

    Date result = ISO8601Utils.parse("9999-12-31T23:59:59Z", pos);

    assertNotNull(result);
    Calendar cal = new GregorianCalendar(UTC);
    cal.setTime(result);
    assertEquals(9999, cal.get(Calendar.YEAR));
    assertEquals(Calendar.DECEMBER, cal.get(Calendar.MONTH));
    assertEquals(31, cal.get(Calendar.DAY_OF_MONTH));
  }

  // ==========================================================================
  // Round-trip tests
  // ==========================================================================

  @Test
  public void roundTrip_formatThenParse_preservesDate() throws ParseException {
    Calendar cal = new GregorianCalendar(UTC);
    cal.set(2023, Calendar.JUNE, 15, 14, 30, 45);
    cal.set(Calendar.MILLISECOND, 0);
    Date original = cal.getTime();

    String formatted = ISO8601Utils.format(original);
    Date parsed = ISO8601Utils.parse(formatted, new ParsePosition(0));

    assertEquals(original.getTime(), parsed.getTime());
  }

  @Test
  public void roundTrip_formatWithMillisThenParse_preservesMillis() throws ParseException {
    Calendar cal = new GregorianCalendar(UTC);
    cal.set(2023, Calendar.JUNE, 15, 14, 30, 45);
    cal.set(Calendar.MILLISECOND, 123);
    Date original = cal.getTime();

    String formatted = ISO8601Utils.format(original, true);
    Date parsed = ISO8601Utils.parse(formatted, new ParsePosition(0));

    assertEquals(original.getTime(), parsed.getTime());
  }

  @Test
  public void roundTrip_formatWithTimezoneThenParse_preservesDate() throws ParseException {
    TimeZone tz = TimeZone.getTimeZone("GMT+05:30");
    Calendar cal = new GregorianCalendar(tz);
    cal.set(2023, Calendar.JUNE, 15, 14, 30, 45);
    cal.set(Calendar.MILLISECOND, 0);
    Date original = cal.getTime();

    String formatted = ISO8601Utils.format(original, false, tz);
    Date parsed = ISO8601Utils.parse(formatted, new ParsePosition(0));

    assertEquals(original.getTime(), parsed.getTime());
  }

  @Test
  public void roundTrip_formatWithMillisAndTimezoneThenParse_preservesBoth() throws ParseException {
    TimeZone tz = TimeZone.getTimeZone("GMT-07:00");
    Calendar cal = new GregorianCalendar(tz);
    cal.set(2023, Calendar.JUNE, 15, 14, 30, 45);
    cal.set(Calendar.MILLISECOND, 789);
    Date original = cal.getTime();

    String formatted = ISO8601Utils.format(original, true, tz);
    Date parsed = ISO8601Utils.parse(formatted, new ParsePosition(0));

    assertEquals(original.getTime(), parsed.getTime());
  }

  // ==========================================================================
  // Edge cases for format
  // ==========================================================================

  @Test
  public void format_negativeYear_formatsCorrectly() {
    // Creating a date in year -1 (2 BCE in astronomical year numbering)
    Calendar cal = new GregorianCalendar(UTC);
    cal.set(Calendar.ERA, GregorianCalendar.BC);
    cal.set(1, Calendar.JUNE, 15, 14, 30, 45);
    cal.set(Calendar.MILLISECOND, 0);
    Date date = cal.getTime();

    // The format method will handle this, though ISO 8601 representation
    // of negative years may vary
    String result = ISO8601Utils.format(date);

    assertNotNull(result);
    assertTrue(result.contains("T"));
    assertTrue(result.endsWith("Z"));
  }

  @Test
  public void format_differentTimezonesSameInstant_producesDifferentLocalTimes() {
    Date date = new Date(1686839445000L); // 2023-06-15T14:30:45Z

    String utcResult = ISO8601Utils.format(date, false, UTC);
    TimeZone estTz = TimeZone.getTimeZone("GMT-05:00");
    String estResult = ISO8601Utils.format(date, false, estTz);

    // UTC shows 14:30
    assertTrue(utcResult.contains("T14:30:45Z"));
    // EST shows 09:30
    assertTrue(estResult.contains("T09:30:45-05:00"));
  }

  // ==========================================================================
  // Additional parse error cases
  // ==========================================================================

  @Test
  public void parse_mismatchingTimezone_throwsParseException() {
    ParsePosition pos = new ParsePosition(0);

    try {
      // Invalid timezone format that would cause mismatch
      ISO8601Utils.parse("2023-06-15T14:30:45+99:99", pos);
      fail("Expected ParseException");
    } catch (ParseException e) {
      assertTrue(e.getMessage().contains("Mismatching time zone indicator")
          || e.getMessage().contains("Failed to parse"));
    }
  }

  @Test
  public void parse_nullDatePreservesMessageFormat() {
    ParsePosition pos = new ParsePosition(0);

    try {
      ISO8601Utils.parse(null, pos);
      fail("Expected exception");
    } catch (ParseException e) {
      assertTrue(e.getMessage().contains("null"));
    } catch (NullPointerException e) {
      // Also acceptable - null handling at string level
    }
  }

  // ==========================================================================
  // Tests for various timezone ID normalizations
  // ==========================================================================

  @Test
  public void parse_timezoneWithColonReturnsNormalizedId_parsesCorrectly() throws ParseException {
    ParsePosition pos = new ParsePosition(0);

    // +01:00 which may be normalized to GMT+01:00
    Date result = ISO8601Utils.parse("2023-06-15T14:30:45+01:00", pos);

    assertNotNull(result);
    Calendar cal = new GregorianCalendar(UTC);
    cal.setTime(result);
    assertEquals(13, cal.get(Calendar.HOUR_OF_DAY)); // 14:30 + 1 hour offset = 13:30 UTC
    assertEquals(30, cal.get(Calendar.MINUTE));
  }

  @Test
  public void parse_timezoneWithoutColonReturnsNormalizedId_parsesCorrectly() throws ParseException {
    ParsePosition pos = new ParsePosition(0);

    // +0100 which may be normalized to GMT+01:00
    Date result = ISO8601Utils.parse("2023-06-15T14:30:45+0100", pos);

    assertNotNull(result);
    Calendar cal = new GregorianCalendar(UTC);
    cal.setTime(result);
    assertEquals(13, cal.get(Calendar.HOUR_OF_DAY));
    assertEquals(30, cal.get(Calendar.MINUTE));
  }
}
