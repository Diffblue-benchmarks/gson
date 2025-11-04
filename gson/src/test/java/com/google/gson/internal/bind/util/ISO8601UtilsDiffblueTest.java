package com.google.gson.internal.bind.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import org.junit.Test;

public class ISO8601UtilsDiffblueTest {
  /** Method under test: {@link ISO8601Utils#format(Date)} */
  @Test
  public void testFormat() {
    // Arrange, Act and Assert
    assertEquals(
        "1970-01-01T00:00:00Z",
        ISO8601Utils.format(
            Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())));
    assertEquals(
        "1970-01-01T00:00:00.000Z",
        ISO8601Utils.format(
            Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()),
            true));
    assertEquals(
        "1970-01-01T00:00:00Z",
        ISO8601Utils.format(
            Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()),
            false));
  }

  /** Method under test: {@link ISO8601Utils#format(java.util.Date)} */
  @Test
  public void testFormat2() {
    // Arrange
    java.sql.Date date = mock(java.sql.Date.class);
    when(date.getTime()).thenReturn(10L);

    // Act
    String actualFormatResult = ISO8601Utils.format(date);

    // Assert
    verify(date).getTime();
    assertEquals("1970-01-01T00:00:00Z", actualFormatResult);
  }

  /** Method under test: {@link ISO8601Utils#format(java.util.Date, boolean)} */
  @Test
  public void testFormat3() {
    // Arrange
    java.sql.Date date = mock(java.sql.Date.class);
    when(date.getTime()).thenReturn(10L);

    // Act
    String actualFormatResult = ISO8601Utils.format(date, true);

    // Assert
    verify(date).getTime();
    assertEquals("1970-01-01T00:00:00.010Z", actualFormatResult);
  }

  /** Method under test: {@link ISO8601Utils#format(Date, boolean, TimeZone)} */
  @Test
  public void testFormat4() {
    // Arrange
    Date date =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertEquals(
        "1969-12-31T16:00:00.000-08:00",
        ISO8601Utils.format(date, true, TimeZone.getTimeZone("America/Los_Angeles")));
  }

  /** Method under test: {@link ISO8601Utils#format(Date, boolean, TimeZone)} */
  @Test
  public void testFormat5() {
    // Arrange
    Date date =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    SimpleTimeZone tz = new SimpleTimeZone(1, "foo");
    tz.setRawOffset(0);

    // Act and Assert
    assertEquals("1970-01-01T00:00:00Z", ISO8601Utils.format(date, false, tz));
  }

  /** Method under test: {@link ISO8601Utils#format(java.util.Date, boolean, TimeZone)} */
  @Test
  public void testFormat6() {
    // Arrange
    java.sql.Date date = mock(java.sql.Date.class);
    when(date.getTime()).thenReturn(10L);

    // Act
    String actualFormatResult =
        ISO8601Utils.format(date, true, TimeZone.getTimeZone("America/Los_Angeles"));

    // Assert
    verify(date).getTime();
    assertEquals("1969-12-31T16:00:00.010-08:00", actualFormatResult);
  }

  /** Method under test: {@link ISO8601Utils#format(java.util.Date, boolean, TimeZone)} */
  @Test
  public void testFormat7() {
    // Arrange
    java.sql.Date date = mock(java.sql.Date.class);
    when(date.getTime()).thenReturn(10L);

    // Act
    String actualFormatResult =
        ISO8601Utils.format(date, true, new SimpleTimeZone(1, "yyyy-MM-ddThh:mm:ss"));

    // Assert
    verify(date).getTime();
    assertEquals("1970-01-01T00:00:00.011+00:00", actualFormatResult);
  }

  /** Method under test: {@link ISO8601Utils#parse(String, ParsePosition)} */
  @Test
  public void testParse() throws ParseException {
    // Arrange, Act and Assert
    assertThrows(
        ParseException.class, () -> ISO8601Utils.parse("2020-03-01", new ParsePosition(1)));
    assertThrows(ParseException.class, () -> ISO8601Utils.parse("20200301", new ParsePosition(1)));
    assertThrows(
        ParseException.class, () -> ISO8601Utils.parse("Invalid number: ", new ParsePosition(1)));
    assertThrows(ParseException.class, () -> ISO8601Utils.parse("", new ParsePosition(1)));
    assertThrows(
        ParseException.class, () -> ISO8601Utils.parse("2020-03-01", new ParsePosition(-1)));
    assertThrows(ParseException.class, () -> ISO8601Utils.parse("+0000", new ParsePosition(1)));
    assertThrows(ParseException.class, () -> ISO8601Utils.parse(null, new ParsePosition(-1)));
  }

  /** Method under test: {@link ISO8601Utils#parse(String, ParsePosition)} */
  @Test
  public void testParse2() throws ParseException {
    // Arrange
    ParsePosition pos = new ParsePosition(0);

    // Act
    Date actualParseResult = ISO8601Utils.parse("2020-03-01", pos);

    // Assert
    assertEquals("2020-03-01", (new SimpleDateFormat("yyyy-MM-dd")).format(actualParseResult));
    assertEquals(10, pos.getIndex());
  }
}
