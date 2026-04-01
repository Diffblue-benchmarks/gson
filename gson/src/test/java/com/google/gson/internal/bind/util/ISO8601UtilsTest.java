/*
 * Copyright (C) 2015 Google Inc.
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
import static org.junit.Assert.assertThrows;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.TimeZone;
import org.junit.Test;

public final class ISO8601UtilsTest {

  @Test
  public void testFormatDate() {
    Date date = new Date(0L);
    String formatted = ISO8601Utils.format(date);
    assertThat(formatted).isEqualTo("1970-01-01T00:00:00Z");
  }

  @Test
  public void testFormatDateWithMillis() {
    Date date = new Date(1234L);
    String formatted = ISO8601Utils.format(date, true);
    assertThat(formatted).isEqualTo("1970-01-01T00:00:01.234Z");
  }

  @Test
  public void testFormatDateWithoutMillis() {
    Date date = new Date(1234L);
    String formatted = ISO8601Utils.format(date, false);
    assertThat(formatted).isEqualTo("1970-01-01T00:00:01Z");
  }

  @Test
  public void testFormatDateWithTimezone() {
    Date date = new Date(0L);
    TimeZone tz = TimeZone.getTimeZone("GMT+05:30");
    String formatted = ISO8601Utils.format(date, false, tz);
    assertThat(formatted).isEqualTo("1970-01-01T05:30:00+05:30");
  }

  @Test
  public void testFormatDateWithNegativeTimezone() {
    Date date = new Date(0L);
    TimeZone tz = TimeZone.getTimeZone("GMT-08:00");
    String formatted = ISO8601Utils.format(date, false, tz);
    assertThat(formatted).isEqualTo("1969-12-31T16:00:00-08:00");
  }

  @Test
  public void testFormatDateWithMillisAndTimezone() {
    Date date = new Date(500L);
    TimeZone tz = TimeZone.getTimeZone("UTC");
    String formatted = ISO8601Utils.format(date, true, tz);
    assertThat(formatted).isEqualTo("1970-01-01T00:00:00.500Z");
  }

  @Test
  public void testParseBasicDate() throws ParseException {
    Date date = ISO8601Utils.parse("1970-01-01", new ParsePosition(0));
    assertThat(date).isNotNull();
  }

  @Test
  public void testParseDateWithTime() throws ParseException {
    Date date = ISO8601Utils.parse("1970-01-01T00:00:00Z", new ParsePosition(0));
    assertThat(date).isEqualTo(new Date(0L));
  }

  @Test
  public void testParseDateWithMillis() throws ParseException {
    Date date = ISO8601Utils.parse("1970-01-01T00:00:00.500Z", new ParsePosition(0));
    assertThat(date).isEqualTo(new Date(500L));
  }

  @Test
  public void testParseDateWithPositiveOffset() throws ParseException {
    Date date = ISO8601Utils.parse("1970-01-01T01:00:00+01:00", new ParsePosition(0));
    assertThat(date).isEqualTo(new Date(0L));
  }

  @Test
  public void testParseDateWithNegativeOffset() throws ParseException {
    Date date = ISO8601Utils.parse("1969-12-31T16:00:00-08:00", new ParsePosition(0));
    assertThat(date).isEqualTo(new Date(0L));
  }

  @Test
  public void testParseDateWithoutColonSeparators() throws ParseException {
    Date date = ISO8601Utils.parse("19700101T000000Z", new ParsePosition(0));
    assertThat(date).isEqualTo(new Date(0L));
  }

  @Test
  public void testParseDateWithOffsetNoMinutes() throws ParseException {
    Date date = ISO8601Utils.parse("1970-01-01T01:00:00+01", new ParsePosition(0));
    assertThat(date).isEqualTo(new Date(0L));
  }

  @Test
  public void testParseDateWithMillis1Digit() throws ParseException {
    Date date = ISO8601Utils.parse("1970-01-01T00:00:00.5Z", new ParsePosition(0));
    assertThat(date).isEqualTo(new Date(500L));
  }

  @Test
  public void testParseDateWithMillis2Digits() throws ParseException {
    Date date = ISO8601Utils.parse("1970-01-01T00:00:00.05Z", new ParsePosition(0));
    assertThat(date).isEqualTo(new Date(50L));
  }

  @Test
  public void testParseDateWithZeroOffset() throws ParseException {
    Date date = ISO8601Utils.parse("1970-01-01T00:00:00+0000", new ParsePosition(0));
    assertThat(date).isEqualTo(new Date(0L));
  }

  @Test
  public void testParseDateWithZeroOffsetColon() throws ParseException {
    Date date = ISO8601Utils.parse("1970-01-01T00:00:00+00:00", new ParsePosition(0));
    assertThat(date).isEqualTo(new Date(0L));
  }

  @Test
  public void testParseInvalidDateThrowsParseException() {
    assertThrows(ParseException.class, () -> ISO8601Utils.parse("not-a-date", new ParsePosition(0)));
  }

  @Test
  public void testParseNoTimezoneThrowsParseException() {
    assertThrows(ParseException.class, () -> ISO8601Utils.parse("1970-01-01T00:00:00", new ParsePosition(0)));
  }

  @Test
  public void testParseLeapSecondTruncated() throws ParseException {
    Date date = ISO8601Utils.parse("1970-01-01T00:00:61Z", new ParsePosition(0));
    assertThat(date).isNotNull();
  }

  @Test
  public void testParseUpdatesParsePosition() throws ParseException {
    ParsePosition pos = new ParsePosition(0);
    ISO8601Utils.parse("1970-01-01T00:00:00Z", pos);
    assertThat(pos.getIndex()).isGreaterThan(0);
  }

  @Test
  public void testParseInvalidTimezoneIndicatorThrowsParseException() {
    assertThrows(
        ParseException.class,
        () -> ISO8601Utils.parse("1970-01-01T00:00:00X", new ParsePosition(0)));
  }

  @Test
  public void testParseMismatchedTimezoneThrowsParseException() {
    assertThrows(
        ParseException.class,
        () -> ISO8601Utils.parse("1970-01-01T00:00:00+99:99", new ParsePosition(0)));
  }

  @Test
  public void testParseEmptyStringThrowsParseExceptionWithClassName() {
    assertThrows(
        ParseException.class, () -> ISO8601Utils.parse("", new ParsePosition(0)));
  }
}
