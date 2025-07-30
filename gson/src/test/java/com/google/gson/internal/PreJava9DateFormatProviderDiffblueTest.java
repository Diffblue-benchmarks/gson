package com.google.gson.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PreJava9DateFormatProviderDiffblueTest {
  /**
   * Test {@link PreJava9DateFormatProvider#getUsDateTimeFormat(int, int)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link PreJava9DateFormatProvider#getUsDateTimeFormat(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DateFormat PreJava9DateFormatProvider.getUsDateTimeFormat(int, int)"})
  public void testGetUsDateTimeFormat_whenMinusOne_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> PreJava9DateFormatProvider.getUsDateTimeFormat(-1, 1));
  }

  /**
   * Test {@link PreJava9DateFormatProvider#getUsDateTimeFormat(int, int)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link PreJava9DateFormatProvider#getUsDateTimeFormat(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DateFormat PreJava9DateFormatProvider.getUsDateTimeFormat(int, int)"})
  public void testGetUsDateTimeFormat_whenMinusOne_thenThrowIllegalArgumentException2() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> PreJava9DateFormatProvider.getUsDateTimeFormat(1, -1));
  }

  /**
   * Test {@link PreJava9DateFormatProvider#getUsDateTimeFormat(int, int)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return toPattern is {@code MMMM d, yyyy h:mm:ss a z}.
   * </ul>
   *
   * <p>Method under test: {@link PreJava9DateFormatProvider#getUsDateTimeFormat(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DateFormat PreJava9DateFormatProvider.getUsDateTimeFormat(int, int)"})
  public void testGetUsDateTimeFormat_whenOne_thenReturnToPatternIsMmmmDYyyyHMmSsAZ() {
    // Arrange and Act
    DateFormat actualUsDateTimeFormat = PreJava9DateFormatProvider.getUsDateTimeFormat(1, 1);

    // Assert
    assertTrue(actualUsDateTimeFormat.getNumberFormat() instanceof DecimalFormat);
    assertTrue(actualUsDateTimeFormat instanceof SimpleDateFormat);
    assertTrue(actualUsDateTimeFormat.getCalendar() instanceof GregorianCalendar);
    assertEquals(
        "MMMM d, yyyy h:mm:ss a z", ((SimpleDateFormat) actualUsDateTimeFormat).toPattern());
    assertTrue(actualUsDateTimeFormat.isLenient());
  }

  /**
   * Test {@link PreJava9DateFormatProvider#getUsDateTimeFormat(int, int)}.
   *
   * <ul>
   *   <li>When three.
   *   <li>Then return toPattern is {@code EEEE, MMMM d, yyyy h:mm a}.
   * </ul>
   *
   * <p>Method under test: {@link PreJava9DateFormatProvider#getUsDateTimeFormat(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DateFormat PreJava9DateFormatProvider.getUsDateTimeFormat(int, int)"})
  public void testGetUsDateTimeFormat_whenThree_thenReturnToPatternIsEeeeMmmmDYyyyHMmA() {
    // Arrange and Act
    DateFormat actualUsDateTimeFormat = PreJava9DateFormatProvider.getUsDateTimeFormat(0, 3);

    // Assert
    assertTrue(actualUsDateTimeFormat.getNumberFormat() instanceof DecimalFormat);
    assertTrue(actualUsDateTimeFormat instanceof SimpleDateFormat);
    assertTrue(actualUsDateTimeFormat.getCalendar() instanceof GregorianCalendar);
    assertEquals(
        "EEEE, MMMM d, yyyy h:mm a", ((SimpleDateFormat) actualUsDateTimeFormat).toPattern());
    assertTrue(actualUsDateTimeFormat.isLenient());
  }

  /**
   * Test {@link PreJava9DateFormatProvider#getUsDateTimeFormat(int, int)}.
   *
   * <ul>
   *   <li>When three.
   *   <li>Then return toPattern is {@code M/d/yy h:mm:ss a z}.
   * </ul>
   *
   * <p>Method under test: {@link PreJava9DateFormatProvider#getUsDateTimeFormat(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DateFormat PreJava9DateFormatProvider.getUsDateTimeFormat(int, int)"})
  public void testGetUsDateTimeFormat_whenThree_thenReturnToPatternIsMDYyHMmSsAZ() {
    // Arrange and Act
    DateFormat actualUsDateTimeFormat = PreJava9DateFormatProvider.getUsDateTimeFormat(3, 0);

    // Assert
    assertTrue(actualUsDateTimeFormat.getNumberFormat() instanceof DecimalFormat);
    assertTrue(actualUsDateTimeFormat instanceof SimpleDateFormat);
    assertTrue(actualUsDateTimeFormat.getCalendar() instanceof GregorianCalendar);
    assertEquals("M/d/yy h:mm:ss a z", ((SimpleDateFormat) actualUsDateTimeFormat).toPattern());
    assertTrue(actualUsDateTimeFormat.isLenient());
  }

  /**
   * Test {@link PreJava9DateFormatProvider#getUsDateTimeFormat(int, int)}.
   *
   * <ul>
   *   <li>When two.
   *   <li>Then return toPattern is {@code EEEE, MMMM d, yyyy h:mm:ss a}.
   * </ul>
   *
   * <p>Method under test: {@link PreJava9DateFormatProvider#getUsDateTimeFormat(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DateFormat PreJava9DateFormatProvider.getUsDateTimeFormat(int, int)"})
  public void testGetUsDateTimeFormat_whenTwo_thenReturnToPatternIsEeeeMmmmDYyyyHMmSsA() {
    // Arrange and Act
    DateFormat actualUsDateTimeFormat = PreJava9DateFormatProvider.getUsDateTimeFormat(0, 2);

    // Assert
    assertTrue(actualUsDateTimeFormat.getNumberFormat() instanceof DecimalFormat);
    assertTrue(actualUsDateTimeFormat instanceof SimpleDateFormat);
    assertTrue(actualUsDateTimeFormat.getCalendar() instanceof GregorianCalendar);
    assertEquals(
        "EEEE, MMMM d, yyyy h:mm:ss a", ((SimpleDateFormat) actualUsDateTimeFormat).toPattern());
    assertTrue(actualUsDateTimeFormat.isLenient());
  }

  /**
   * Test {@link PreJava9DateFormatProvider#getUsDateTimeFormat(int, int)}.
   *
   * <ul>
   *   <li>When two.
   *   <li>Then return toPattern is {@code MMM d, yyyy h:mm:ss a z}.
   * </ul>
   *
   * <p>Method under test: {@link PreJava9DateFormatProvider#getUsDateTimeFormat(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DateFormat PreJava9DateFormatProvider.getUsDateTimeFormat(int, int)"})
  public void testGetUsDateTimeFormat_whenTwo_thenReturnToPatternIsMmmDYyyyHMmSsAZ() {
    // Arrange and Act
    DateFormat actualUsDateTimeFormat = PreJava9DateFormatProvider.getUsDateTimeFormat(2, 0);

    // Assert
    assertTrue(actualUsDateTimeFormat.getNumberFormat() instanceof DecimalFormat);
    assertTrue(actualUsDateTimeFormat instanceof SimpleDateFormat);
    assertTrue(actualUsDateTimeFormat.getCalendar() instanceof GregorianCalendar);
    assertEquals(
        "MMM d, yyyy h:mm:ss a z", ((SimpleDateFormat) actualUsDateTimeFormat).toPattern());
    assertTrue(actualUsDateTimeFormat.isLenient());
  }

  /**
   * Test {@link PreJava9DateFormatProvider#getUsDateTimeFormat(int, int)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return toPattern is {@code EEEE, MMMM d, yyyy h:mm:ss a z}.
   * </ul>
   *
   * <p>Method under test: {@link PreJava9DateFormatProvider#getUsDateTimeFormat(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DateFormat PreJava9DateFormatProvider.getUsDateTimeFormat(int, int)"})
  public void testGetUsDateTimeFormat_whenZero_thenReturnToPatternIsEeeeMmmmDYyyyHMmSsAZ() {
    // Arrange and Act
    DateFormat actualUsDateTimeFormat = PreJava9DateFormatProvider.getUsDateTimeFormat(0, 0);

    // Assert
    assertTrue(actualUsDateTimeFormat.getNumberFormat() instanceof DecimalFormat);
    assertTrue(actualUsDateTimeFormat instanceof SimpleDateFormat);
    assertTrue(actualUsDateTimeFormat.getCalendar() instanceof GregorianCalendar);
    assertEquals(
        "EEEE, MMMM d, yyyy h:mm:ss a z", ((SimpleDateFormat) actualUsDateTimeFormat).toPattern());
    assertTrue(actualUsDateTimeFormat.isLenient());
  }
}
