package com.google.gson.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class JavaVersionDiffblueTest {
  /** Method under test: {@link JavaVersion#parseMajorJavaVersion(String)} */
  @Test
  public void testParseMajorJavaVersion() {
    // Arrange, Act and Assert
    assertEquals(0, JavaVersion.parseMajorJavaVersion("1.0.2"));
    assertEquals(6, JavaVersion.parseMajorJavaVersion("[._]"));
    assertEquals(42, JavaVersion.parseMajorJavaVersion("42"));
    assertEquals(6, JavaVersion.parseMajorJavaVersion(""));
    assertEquals(42, JavaVersion.parseMajorJavaVersion("42[._]"));
  }

  /** Method under test: {@link JavaVersion#isJava9OrLater()} */
  @Test
  public void testIsJava9OrLater() {
    // Arrange, Act and Assert
    assertTrue(JavaVersion.isJava9OrLater());
  }
}
