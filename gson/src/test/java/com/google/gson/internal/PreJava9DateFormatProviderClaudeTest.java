package com.google.gson.internal;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.junit.Test;

/** Tests for {@link PreJava9DateFormatProvider}. */
public class PreJava9DateFormatProviderClaudeTest {

  // Use a fixed date for consistent testing: January 15, 2024 at 14:30:45 UTC
  // Note: We need a specific timezone to get predictable output
  private static final TimeZone TEST_TIMEZONE = TimeZone.getTimeZone("America/New_York");

  private DateFormat getFormat(int dateStyle, int timeStyle) {
    DateFormat format = PreJava9DateFormatProvider.getUsDateTimeFormat(dateStyle, timeStyle);
    format.setTimeZone(TEST_TIMEZONE);
    return format;
  }

  // ==========================================================================
  // Tests for SHORT date style with all time styles
  // ==========================================================================

  @Test
  public void getUsDateTimeFormat_shortDate_shortTime() {
    DateFormat format = getFormat(DateFormat.SHORT, DateFormat.SHORT);
    assertNotNull(format);
    assertTrue(format instanceof SimpleDateFormat);
    SimpleDateFormat sdf = (SimpleDateFormat) format;
    assertEquals("M/d/yy h:mm a", sdf.toPattern());
  }

  @Test
  public void getUsDateTimeFormat_shortDate_mediumTime() {
    DateFormat format = getFormat(DateFormat.SHORT, DateFormat.MEDIUM);
    assertNotNull(format);
    assertTrue(format instanceof SimpleDateFormat);
    SimpleDateFormat sdf = (SimpleDateFormat) format;
    assertEquals("M/d/yy h:mm:ss a", sdf.toPattern());
  }

  @Test
  public void getUsDateTimeFormat_shortDate_longTime() {
    DateFormat format = getFormat(DateFormat.SHORT, DateFormat.LONG);
    assertNotNull(format);
    assertTrue(format instanceof SimpleDateFormat);
    SimpleDateFormat sdf = (SimpleDateFormat) format;
    assertEquals("M/d/yy h:mm:ss a z", sdf.toPattern());
  }

  @Test
  public void getUsDateTimeFormat_shortDate_fullTime() {
    DateFormat format = getFormat(DateFormat.SHORT, DateFormat.FULL);
    assertNotNull(format);
    assertTrue(format instanceof SimpleDateFormat);
    SimpleDateFormat sdf = (SimpleDateFormat) format;
    // FULL and LONG time styles produce the same pattern
    assertEquals("M/d/yy h:mm:ss a z", sdf.toPattern());
  }

  // ==========================================================================
  // Tests for MEDIUM date style with all time styles
  // ==========================================================================

  @Test
  public void getUsDateTimeFormat_mediumDate_shortTime() {
    DateFormat format = getFormat(DateFormat.MEDIUM, DateFormat.SHORT);
    assertNotNull(format);
    assertTrue(format instanceof SimpleDateFormat);
    SimpleDateFormat sdf = (SimpleDateFormat) format;
    assertEquals("MMM d, yyyy h:mm a", sdf.toPattern());
  }

  @Test
  public void getUsDateTimeFormat_mediumDate_mediumTime() {
    DateFormat format = getFormat(DateFormat.MEDIUM, DateFormat.MEDIUM);
    assertNotNull(format);
    assertTrue(format instanceof SimpleDateFormat);
    SimpleDateFormat sdf = (SimpleDateFormat) format;
    assertEquals("MMM d, yyyy h:mm:ss a", sdf.toPattern());
  }

  @Test
  public void getUsDateTimeFormat_mediumDate_longTime() {
    DateFormat format = getFormat(DateFormat.MEDIUM, DateFormat.LONG);
    assertNotNull(format);
    assertTrue(format instanceof SimpleDateFormat);
    SimpleDateFormat sdf = (SimpleDateFormat) format;
    assertEquals("MMM d, yyyy h:mm:ss a z", sdf.toPattern());
  }

  @Test
  public void getUsDateTimeFormat_mediumDate_fullTime() {
    DateFormat format = getFormat(DateFormat.MEDIUM, DateFormat.FULL);
    assertNotNull(format);
    assertTrue(format instanceof SimpleDateFormat);
    SimpleDateFormat sdf = (SimpleDateFormat) format;
    assertEquals("MMM d, yyyy h:mm:ss a z", sdf.toPattern());
  }

  // ==========================================================================
  // Tests for LONG date style with all time styles
  // ==========================================================================

  @Test
  public void getUsDateTimeFormat_longDate_shortTime() {
    DateFormat format = getFormat(DateFormat.LONG, DateFormat.SHORT);
    assertNotNull(format);
    assertTrue(format instanceof SimpleDateFormat);
    SimpleDateFormat sdf = (SimpleDateFormat) format;
    assertEquals("MMMM d, yyyy h:mm a", sdf.toPattern());
  }

  @Test
  public void getUsDateTimeFormat_longDate_mediumTime() {
    DateFormat format = getFormat(DateFormat.LONG, DateFormat.MEDIUM);
    assertNotNull(format);
    assertTrue(format instanceof SimpleDateFormat);
    SimpleDateFormat sdf = (SimpleDateFormat) format;
    assertEquals("MMMM d, yyyy h:mm:ss a", sdf.toPattern());
  }

  @Test
  public void getUsDateTimeFormat_longDate_longTime() {
    DateFormat format = getFormat(DateFormat.LONG, DateFormat.LONG);
    assertNotNull(format);
    assertTrue(format instanceof SimpleDateFormat);
    SimpleDateFormat sdf = (SimpleDateFormat) format;
    assertEquals("MMMM d, yyyy h:mm:ss a z", sdf.toPattern());
  }

  @Test
  public void getUsDateTimeFormat_longDate_fullTime() {
    DateFormat format = getFormat(DateFormat.LONG, DateFormat.FULL);
    assertNotNull(format);
    assertTrue(format instanceof SimpleDateFormat);
    SimpleDateFormat sdf = (SimpleDateFormat) format;
    assertEquals("MMMM d, yyyy h:mm:ss a z", sdf.toPattern());
  }

  // ==========================================================================
  // Tests for FULL date style with all time styles
  // ==========================================================================

  @Test
  public void getUsDateTimeFormat_fullDate_shortTime() {
    DateFormat format = getFormat(DateFormat.FULL, DateFormat.SHORT);
    assertNotNull(format);
    assertTrue(format instanceof SimpleDateFormat);
    SimpleDateFormat sdf = (SimpleDateFormat) format;
    assertEquals("EEEE, MMMM d, yyyy h:mm a", sdf.toPattern());
  }

  @Test
  public void getUsDateTimeFormat_fullDate_mediumTime() {
    DateFormat format = getFormat(DateFormat.FULL, DateFormat.MEDIUM);
    assertNotNull(format);
    assertTrue(format instanceof SimpleDateFormat);
    SimpleDateFormat sdf = (SimpleDateFormat) format;
    assertEquals("EEEE, MMMM d, yyyy h:mm:ss a", sdf.toPattern());
  }

  @Test
  public void getUsDateTimeFormat_fullDate_longTime() {
    DateFormat format = getFormat(DateFormat.FULL, DateFormat.LONG);
    assertNotNull(format);
    assertTrue(format instanceof SimpleDateFormat);
    SimpleDateFormat sdf = (SimpleDateFormat) format;
    assertEquals("EEEE, MMMM d, yyyy h:mm:ss a z", sdf.toPattern());
  }

  @Test
  public void getUsDateTimeFormat_fullDate_fullTime() {
    DateFormat format = getFormat(DateFormat.FULL, DateFormat.FULL);
    assertNotNull(format);
    assertTrue(format instanceof SimpleDateFormat);
    SimpleDateFormat sdf = (SimpleDateFormat) format;
    assertEquals("EEEE, MMMM d, yyyy h:mm:ss a z", sdf.toPattern());
  }

  // ==========================================================================
  // Tests for invalid date style (exercises default branch of date switch)
  // ==========================================================================

  @Test
  public void getUsDateTimeFormat_invalidDateStyle_throwsException() {
    try {
      PreJava9DateFormatProvider.getUsDateTimeFormat(-1, DateFormat.SHORT);
      fail("Expected IllegalArgumentException for invalid date style");
    } catch (IllegalArgumentException e) {
      assertTrue(e.getMessage().contains("Unknown DateFormat style"));
      assertTrue(e.getMessage().contains("-1"));
    }
  }

  @Test
  public void getUsDateTimeFormat_invalidDateStyle_positiveValue_throwsException() {
    try {
      PreJava9DateFormatProvider.getUsDateTimeFormat(4, DateFormat.SHORT);
      fail("Expected IllegalArgumentException for invalid date style");
    } catch (IllegalArgumentException e) {
      assertTrue(e.getMessage().contains("Unknown DateFormat style"));
      assertTrue(e.getMessage().contains("4"));
    }
  }

  @Test
  public void getUsDateTimeFormat_invalidDateStyle_largeValue_throwsException() {
    try {
      PreJava9DateFormatProvider.getUsDateTimeFormat(100, DateFormat.MEDIUM);
      fail("Expected IllegalArgumentException for invalid date style");
    } catch (IllegalArgumentException e) {
      assertTrue(e.getMessage().contains("Unknown DateFormat style"));
      assertTrue(e.getMessage().contains("100"));
    }
  }

  // ==========================================================================
  // Tests for invalid time style (exercises default branch of time switch)
  // ==========================================================================

  @Test
  public void getUsDateTimeFormat_invalidTimeStyle_throwsException() {
    try {
      PreJava9DateFormatProvider.getUsDateTimeFormat(DateFormat.SHORT, -1);
      fail("Expected IllegalArgumentException for invalid time style");
    } catch (IllegalArgumentException e) {
      assertTrue(e.getMessage().contains("Unknown DateFormat style"));
      assertTrue(e.getMessage().contains("-1"));
    }
  }

  @Test
  public void getUsDateTimeFormat_invalidTimeStyle_positiveValue_throwsException() {
    try {
      PreJava9DateFormatProvider.getUsDateTimeFormat(DateFormat.SHORT, 4);
      fail("Expected IllegalArgumentException for invalid time style");
    } catch (IllegalArgumentException e) {
      assertTrue(e.getMessage().contains("Unknown DateFormat style"));
      assertTrue(e.getMessage().contains("4"));
    }
  }

  @Test
  public void getUsDateTimeFormat_invalidTimeStyle_largeValue_throwsException() {
    try {
      PreJava9DateFormatProvider.getUsDateTimeFormat(DateFormat.MEDIUM, 100);
      fail("Expected IllegalArgumentException for invalid time style");
    } catch (IllegalArgumentException e) {
      assertTrue(e.getMessage().contains("Unknown DateFormat style"));
      assertTrue(e.getMessage().contains("100"));
    }
  }

  // ==========================================================================
  // Tests for both invalid styles
  // ==========================================================================

  @Test
  public void getUsDateTimeFormat_bothStylesInvalid_throwsExceptionForDateFirst() {
    // When both are invalid, date style is checked first
    try {
      PreJava9DateFormatProvider.getUsDateTimeFormat(-1, -1);
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertTrue(e.getMessage().contains("Unknown DateFormat style"));
    }
  }

  // ==========================================================================
  // Tests verifying the format actually works with dates
  // ==========================================================================

  /** Create a date in the test timezone */
  private Date createTestDate(int year, int month, int day, int hour, int minute, int second) {
    Calendar cal = Calendar.getInstance(TEST_TIMEZONE);
    cal.set(year, month, day, hour, minute, second);
    cal.set(Calendar.MILLISECOND, 0);
    return cal.getTime();
  }

  @Test
  public void getUsDateTimeFormat_formatsDateCorrectly_shortShort() {
    DateFormat format = getFormat(DateFormat.SHORT, DateFormat.SHORT);
    // Create a known date: Monday, January 15, 2024 at 2:30 PM EST
    Date testDate = createTestDate(2024, Calendar.JANUARY, 15, 14, 30, 45);

    String formatted = format.format(testDate);
    // Expected: "1/15/24 2:30 PM"
    assertTrue("Expected date to contain '1/15/24' but was: " + formatted, formatted.contains("1/15/24"));
    assertTrue("Expected time to contain '2:30 PM' but was: " + formatted, formatted.contains("2:30 PM"));
  }

  @Test
  public void getUsDateTimeFormat_formatsDateCorrectly_mediumMedium() {
    DateFormat format = getFormat(DateFormat.MEDIUM, DateFormat.MEDIUM);
    Date testDate = createTestDate(2024, Calendar.JANUARY, 15, 14, 30, 45);

    String formatted = format.format(testDate);
    // Expected: "Jan 15, 2024 2:30:45 PM"
    assertTrue("Expected date to contain 'Jan 15, 2024' but was: " + formatted, formatted.contains("Jan 15, 2024"));
    assertTrue("Expected time to contain '2:30:45 PM' but was: " + formatted, formatted.contains("2:30:45 PM"));
  }

  @Test
  public void getUsDateTimeFormat_formatsDateCorrectly_longLong() {
    DateFormat format = getFormat(DateFormat.LONG, DateFormat.LONG);
    Date testDate = createTestDate(2024, Calendar.JANUARY, 15, 14, 30, 45);

    String formatted = format.format(testDate);
    // Expected: "January 15, 2024 2:30:45 PM EST" (or similar timezone)
    assertTrue("Expected date to contain 'January 15, 2024' but was: " + formatted, formatted.contains("January 15, 2024"));
    assertTrue("Expected time to contain '2:30:45 PM' but was: " + formatted, formatted.contains("2:30:45 PM"));
  }

  @Test
  public void getUsDateTimeFormat_formatsDateCorrectly_fullFull() {
    DateFormat format = getFormat(DateFormat.FULL, DateFormat.FULL);
    Date testDate = createTestDate(2024, Calendar.JANUARY, 15, 14, 30, 45);

    String formatted = format.format(testDate);
    // Expected: "Monday, January 15, 2024 2:30:45 PM EST"
    assertTrue("Expected date to contain 'Monday' but was: " + formatted, formatted.contains("Monday"));
    assertTrue("Expected date to contain 'January 15, 2024' but was: " + formatted, formatted.contains("January 15, 2024"));
  }

  // ==========================================================================
  // Test that the format uses US locale
  // ==========================================================================

  @Test
  public void getUsDateTimeFormat_usesUsLocale() {
    DateFormat format = PreJava9DateFormatProvider.getUsDateTimeFormat(DateFormat.MEDIUM, DateFormat.MEDIUM);
    assertTrue(format instanceof SimpleDateFormat);
    SimpleDateFormat sdf = (SimpleDateFormat) format;

    // The AM/PM marker in US locale should be uppercase
    sdf.setTimeZone(TEST_TIMEZONE);
    Date amDate = createTestDate(2024, Calendar.JANUARY, 15, 10, 30, 0); // 10:30 AM
    String formatted = sdf.format(amDate);
    assertTrue("Expected AM marker but was: " + formatted, formatted.contains("AM"));

    Date pmDate = createTestDate(2024, Calendar.JANUARY, 15, 14, 30, 0); // 2:30 PM
    formatted = sdf.format(pmDate);
    assertTrue("Expected PM marker but was: " + formatted, formatted.contains("PM"));
  }

  // ==========================================================================
  // Test that each call returns a new instance (not shared/cached)
  // ==========================================================================

  @Test
  public void getUsDateTimeFormat_returnsNewInstanceEachCall() {
    DateFormat format1 = PreJava9DateFormatProvider.getUsDateTimeFormat(DateFormat.SHORT, DateFormat.SHORT);
    DateFormat format2 = PreJava9DateFormatProvider.getUsDateTimeFormat(DateFormat.SHORT, DateFormat.SHORT);

    assertNotSame("Each call should return a new instance", format1, format2);
    // But they should be equivalent
    assertEquals(((SimpleDateFormat) format1).toPattern(), ((SimpleDateFormat) format2).toPattern());
  }

  // ==========================================================================
  // Verify DateFormat style constants (documentation/sanity check)
  // ==========================================================================

  @Test
  public void dateFormatStyleConstants_haveExpectedValues() {
    // These are the standard DateFormat style constants
    assertEquals(0, DateFormat.FULL);
    assertEquals(1, DateFormat.LONG);
    assertEquals(2, DateFormat.MEDIUM);
    assertEquals(3, DateFormat.SHORT);
  }
}
