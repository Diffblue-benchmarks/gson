package com.google.gson.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JavaVersionDiffblueTest {
  /**
   * Test {@link JavaVersion#parseMajorJavaVersion(String)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return forty-two.
   * </ul>
   *
   * <p>Method under test: {@link JavaVersion#parseMajorJavaVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JavaVersion.parseMajorJavaVersion(String)"})
  public void testParseMajorJavaVersion_when42_thenReturnFortyTwo() {
    // Arrange, Act and Assert
    assertEquals(42, JavaVersion.parseMajorJavaVersion("42"));
  }

  /**
   * Test {@link JavaVersion#parseMajorJavaVersion(String)}.
   *
   * <ul>
   *   <li>When {@code 42[._]}.
   *   <li>Then return forty-two.
   * </ul>
   *
   * <p>Method under test: {@link JavaVersion#parseMajorJavaVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JavaVersion.parseMajorJavaVersion(String)"})
  public void testParseMajorJavaVersion_when42_thenReturnFortyTwo2() {
    // Arrange, Act and Assert
    assertEquals(42, JavaVersion.parseMajorJavaVersion("42[._]"));
  }

  /**
   * Test {@link JavaVersion#parseMajorJavaVersion(String)}.
   *
   * <ul>
   *   <li>When {@code 1.0.2}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link JavaVersion#parseMajorJavaVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JavaVersion.parseMajorJavaVersion(String)"})
  public void testParseMajorJavaVersion_when102_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0, JavaVersion.parseMajorJavaVersion("1.0.2"));
  }

  /**
   * Test {@link JavaVersion#parseMajorJavaVersion(String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return six.
   * </ul>
   *
   * <p>Method under test: {@link JavaVersion#parseMajorJavaVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JavaVersion.parseMajorJavaVersion(String)"})
  public void testParseMajorJavaVersion_whenEmptyString_thenReturnSix() {
    // Arrange, Act and Assert
    assertEquals(6, JavaVersion.parseMajorJavaVersion(""));
  }

  /**
   * Test {@link JavaVersion#parseMajorJavaVersion(String)}.
   *
   * <ul>
   *   <li>When {@code [._]}.
   * </ul>
   *
   * <p>Method under test: {@link JavaVersion#parseMajorJavaVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JavaVersion.parseMajorJavaVersion(String)"})
  public void testParseMajorJavaVersion_whenLeftSquareBracketDotUnderscoreRightSquareBracket() {
    // Arrange, Act and Assert
    assertEquals(6, JavaVersion.parseMajorJavaVersion("[._]"));
  }

  /**
   * Test {@link JavaVersion#isJava9OrLater()}.
   *
   * <p>Method under test: {@link JavaVersion#isJava9OrLater()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JavaVersion.isJava9OrLater()"})
  public void testIsJava9OrLater() {
    // Arrange, Act and Assert
    assertTrue(JavaVersion.isJava9OrLater());
  }
}
