package com.google.gson.internal.bind.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ISO8601UtilsDiffblueTest {
  /**
   * Test {@link ISO8601Utils#format(Date, boolean, TimeZone)} with {@code date}, {@code millis},
   * {@code tz}.
   *
   * <ul>
   *   <li>Then return {@code 1969-12-31T16:00:00.000-08:00}.
   * </ul>
   *
   * <p>Method under test: {@link ISO8601Utils#format(Date, boolean, TimeZone)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ISO8601Utils.format(Date, boolean, TimeZone)"})
  public void testFormatWithDateMillisTz_thenReturn19691231t1600000000800() {
    // Arrange
    Date date =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertEquals(
        "1969-12-31T16:00:00.000-08:00",
        ISO8601Utils.format(date, true, TimeZone.getTimeZone("America/Los_Angeles")));
  }

  /**
   * Test {@link ISO8601Utils#format(Date, boolean, TimeZone)} with {@code date}, {@code millis},
   * {@code tz}.
   *
   * <ul>
   *   <li>When {@code false}.
   *   <li>Then return {@code 1970-01-01T00:00:00Z}.
   * </ul>
   *
   * <p>Method under test: {@link ISO8601Utils#format(Date, boolean, TimeZone)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ISO8601Utils.format(Date, boolean, TimeZone)"})
  public void testFormatWithDateMillisTz_whenFalse_thenReturn19700101t000000z() {
    // Arrange
    Date date =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertEquals(
        "1970-01-01T00:00:00Z", ISO8601Utils.format(date, false, new SimpleTimeZone(0, "foo")));
  }

  /**
   * Test {@link ISO8601Utils#format(Date, boolean)} with {@code date}, {@code millis}.
   *
   * <ul>
   *   <li>Then return {@code 1970-01-01T00:00:00.000Z}.
   * </ul>
   *
   * <p>Method under test: {@link ISO8601Utils#format(Date, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ISO8601Utils.format(Date, boolean)"})
  public void testFormatWithDateMillis_thenReturn19700101t000000000z() {
    // Arrange, Act and Assert
    assertEquals(
        "1970-01-01T00:00:00.000Z",
        ISO8601Utils.format(
            Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()),
            true));
  }

  /**
   * Test {@link ISO8601Utils#format(Date, boolean)} with {@code date}, {@code millis}.
   *
   * <ul>
   *   <li>When {@code false}.
   *   <li>Then return {@code 1970-01-01T00:00:00Z}.
   * </ul>
   *
   * <p>Method under test: {@link ISO8601Utils#format(Date, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ISO8601Utils.format(Date, boolean)"})
  public void testFormatWithDateMillis_whenFalse_thenReturn19700101t000000z() {
    // Arrange, Act and Assert
    assertEquals(
        "1970-01-01T00:00:00Z",
        ISO8601Utils.format(
            Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()),
            false));
  }

  /**
   * Test {@link ISO8601Utils#format(Date)} with {@code date}.
   *
   * <ul>
   *   <li>Then return {@code 1970-01-01T00:00:00Z}.
   * </ul>
   *
   * <p>Method under test: {@link ISO8601Utils#format(Date)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ISO8601Utils.format(Date)"})
  public void testFormatWithDate_thenReturn19700101t000000z() {
    // Arrange, Act and Assert
    assertEquals(
        "1970-01-01T00:00:00Z",
        ISO8601Utils.format(
            Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())));
  }

  /**
   * Test {@link ISO8601Utils#parse(String, ParsePosition)}.
   *
   * <ul>
   *   <li>Then return {@link SimpleDateFormat#SimpleDateFormat(String)} with {@code yyyy-MM-dd}
   *       format is {@code 2020-03-01}.
   * </ul>
   *
   * <p>Method under test: {@link ISO8601Utils#parse(String, ParsePosition)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date ISO8601Utils.parse(String, ParsePosition)"})
  public void testParse_thenReturnSimpleDateFormatWithYyyyMmDdFormatIs20200301()
      throws ParseException {
    // Arrange
    ParsePosition pos = new ParsePosition(0);

    // Act
    Date actualParseResult = ISO8601Utils.parse("2020-03-01", pos);

    // Assert
    assertEquals("2020-03-01", new SimpleDateFormat("yyyy-MM-dd").format(actualParseResult));
    assertEquals(10, pos.getIndex());
  }

  /**
   * Test {@link ISO8601Utils#parse(String, ParsePosition)}.
   *
   * <ul>
   *   <li>When {@code +0000}.
   *   <li>Then throw {@link ParseException}.
   * </ul>
   *
   * <p>Method under test: {@link ISO8601Utils#parse(String, ParsePosition)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date ISO8601Utils.parse(String, ParsePosition)"})
  public void testParse_when0000_thenThrowParseException() throws ParseException {
    // Arrange, Act and Assert
    assertThrows(ParseException.class, () -> ISO8601Utils.parse("+0000", new ParsePosition(1)));
  }

  /**
   * Test {@link ISO8601Utils#parse(String, ParsePosition)}.
   *
   * <ul>
   *   <li>When {@code 2020-03-01}.
   *   <li>Then throw {@link ParseException}.
   * </ul>
   *
   * <p>Method under test: {@link ISO8601Utils#parse(String, ParsePosition)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date ISO8601Utils.parse(String, ParsePosition)"})
  public void testParse_when20200301_thenThrowParseException() throws ParseException {
    // Arrange, Act and Assert
    assertThrows(
        ParseException.class, () -> ISO8601Utils.parse("2020-03-01", new ParsePosition(1)));
  }

  /**
   * Test {@link ISO8601Utils#parse(String, ParsePosition)}.
   *
   * <ul>
   *   <li>When {@code 20200301}.
   *   <li>Then throw {@link ParseException}.
   * </ul>
   *
   * <p>Method under test: {@link ISO8601Utils#parse(String, ParsePosition)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date ISO8601Utils.parse(String, ParsePosition)"})
  public void testParse_when20200301_thenThrowParseException2() throws ParseException {
    // Arrange, Act and Assert
    assertThrows(ParseException.class, () -> ISO8601Utils.parse("20200301", new ParsePosition(1)));
  }

  /**
   * Test {@link ISO8601Utils#parse(String, ParsePosition)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then throw {@link ParseException}.
   * </ul>
   *
   * <p>Method under test: {@link ISO8601Utils#parse(String, ParsePosition)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date ISO8601Utils.parse(String, ParsePosition)"})
  public void testParse_whenEmptyString_thenThrowParseException() throws ParseException {
    // Arrange, Act and Assert
    assertThrows(ParseException.class, () -> ISO8601Utils.parse("", new ParsePosition(1)));
  }

  /**
   * Test {@link ISO8601Utils#parse(String, ParsePosition)}.
   *
   * <ul>
   *   <li>When {@code Invalid number:}.
   *   <li>Then throw {@link ParseException}.
   * </ul>
   *
   * <p>Method under test: {@link ISO8601Utils#parse(String, ParsePosition)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date ISO8601Utils.parse(String, ParsePosition)"})
  public void testParse_whenInvalidNumber_thenThrowParseException() throws ParseException {
    // Arrange, Act and Assert
    assertThrows(
        ParseException.class, () -> ISO8601Utils.parse("Invalid number: ", new ParsePosition(1)));
  }

  /**
   * Test {@link ISO8601Utils#parse(String, ParsePosition)}.
   *
   * <ul>
   *   <li>When {@link ParsePosition#ParsePosition(int)} with minus one.
   *   <li>Then throw {@link ParseException}.
   * </ul>
   *
   * <p>Method under test: {@link ISO8601Utils#parse(String, ParsePosition)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date ISO8601Utils.parse(String, ParsePosition)"})
  public void testParse_whenParsePositionWithMinusOne_thenThrowParseException()
      throws ParseException {
    // Arrange, Act and Assert
    assertThrows(
        ParseException.class, () -> ISO8601Utils.parse("2020-03-01", new ParsePosition(-1)));
  }

  /**
   * Test {@link ISO8601Utils#parse(String, ParsePosition)}.
   *
   * <ul>
   *   <li>When {@link ParsePosition#ParsePosition(int)} with minus one.
   *   <li>Then throw {@link ParseException}.
   * </ul>
   *
   * <p>Method under test: {@link ISO8601Utils#parse(String, ParsePosition)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date ISO8601Utils.parse(String, ParsePosition)"})
  public void testParse_whenParsePositionWithMinusOne_thenThrowParseException2()
      throws ParseException {
    // Arrange, Act and Assert
    assertThrows(ParseException.class, () -> ISO8601Utils.parse(null, new ParsePosition(-1)));
  }
}
