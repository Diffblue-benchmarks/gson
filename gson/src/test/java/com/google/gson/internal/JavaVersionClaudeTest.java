/*
 * Copyright (C) 2017 The Gson authors
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
package com.google.gson.internal;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;

/**
 * Unit tests for {@link JavaVersion}.
 *
 * <p>Tests cover the three public/package-visible methods:
 * <ul>
 *   <li>{@link JavaVersion#parseMajorJavaVersion(String)} - parses version strings</li>
 *   <li>{@link JavaVersion#getMajorJavaVersion()} - returns cached major version</li>
 *   <li>{@link JavaVersion#isJava9OrLater()} - checks if running Java 9+</li>
 * </ul>
 */
public class JavaVersionClaudeTest {

  // ==================== Tests for getMajorJavaVersion() ====================

  @Test
  public void testGetMajorJavaVersion_returnsAtLeastMinimumSupported() {
    // Gson currently requires at least Java 8
    assertThat(JavaVersion.getMajorJavaVersion()).isAtLeast(8);
  }

  @Test
  public void testGetMajorJavaVersion_returnsReasonableValue() {
    // The version should be a reasonable value (not unreasonably high)
    int version = JavaVersion.getMajorJavaVersion();
    assertThat(version).isAtMost(100);
  }

  // ==================== Tests for isJava9OrLater() ====================

  @Test
  public void testIsJava9OrLater_consistentWithGetMajorJavaVersion() {
    int majorVersion = JavaVersion.getMajorJavaVersion();
    boolean isJava9OrLater = JavaVersion.isJava9OrLater();

    if (majorVersion >= 9) {
      assertThat(isJava9OrLater).isTrue();
    } else {
      assertThat(isJava9OrLater).isFalse();
    }
  }

  // ==================== Tests for parseMajorJavaVersion() ====================

  // ----- Legacy 1.x style version strings (Java 6, 7, 8) -----

  @Test
  public void testParseMajorJavaVersion_java6Legacy() {
    // http://www.oracle.com/technetwork/java/javase/version-6-141920.html
    assertThat(JavaVersion.parseMajorJavaVersion("1.6.0")).isEqualTo(6);
    assertThat(JavaVersion.parseMajorJavaVersion("1.6.0_45")).isEqualTo(6);
  }

  @Test
  public void testParseMajorJavaVersion_java7Legacy() {
    // http://www.oracle.com/technetwork/java/javase/jdk7-naming-418744.html
    assertThat(JavaVersion.parseMajorJavaVersion("1.7.0")).isEqualTo(7);
    assertThat(JavaVersion.parseMajorJavaVersion("1.7.0_80")).isEqualTo(7);
  }

  @Test
  public void testParseMajorJavaVersion_java8Legacy() {
    assertThat(JavaVersion.parseMajorJavaVersion("1.8")).isEqualTo(8);
    assertThat(JavaVersion.parseMajorJavaVersion("1.8.0")).isEqualTo(8);
    assertThat(JavaVersion.parseMajorJavaVersion("1.8.0_131")).isEqualTo(8);
    assertThat(JavaVersion.parseMajorJavaVersion("1.8.0_60-ea")).isEqualTo(8);
    assertThat(JavaVersion.parseMajorJavaVersion("1.8.0_111-internal")).isEqualTo(8);
  }

  @Test
  public void testParseMajorJavaVersion_java8OpenJDKVariants() {
    // OpenJDK 8 variants per https://github.com/AdoptOpenJDK/openjdk-build/issues/93
    assertThat(JavaVersion.parseMajorJavaVersion("1.8.0-internal")).isEqualTo(8);
    assertThat(JavaVersion.parseMajorJavaVersion("1.8.0_131-adoptopenjdk")).isEqualTo(8);
  }

  // ----- New style version strings (Java 9+) -----

  @Test
  public void testParseMajorJavaVersion_java9NewStyle() {
    // New style from Java 9+
    assertThat(JavaVersion.parseMajorJavaVersion("9")).isEqualTo(9);
    assertThat(JavaVersion.parseMajorJavaVersion("9.0.4")).isEqualTo(9);
    assertThat(JavaVersion.parseMajorJavaVersion("9-ea+19")).isEqualTo(9);
    assertThat(JavaVersion.parseMajorJavaVersion("9+100")).isEqualTo(9);
    assertThat(JavaVersion.parseMajorJavaVersion("9.0.1+20")).isEqualTo(9);
    assertThat(JavaVersion.parseMajorJavaVersion("9.1.1+20")).isEqualTo(9);
  }

  @Test
  public void testParseMajorJavaVersion_java9Debian() {
    // Debian as reported in https://github.com/google/gson/issues/1310
    assertThat(JavaVersion.parseMajorJavaVersion("9-Debian")).isEqualTo(9);
  }

  @Test
  public void testParseMajorJavaVersion_java10() {
    assertThat(JavaVersion.parseMajorJavaVersion("10")).isEqualTo(10);
    assertThat(JavaVersion.parseMajorJavaVersion("10.0.1")).isEqualTo(10);
    assertThat(JavaVersion.parseMajorJavaVersion("10.0.2+13")).isEqualTo(10);
  }

  @Test
  public void testParseMajorJavaVersion_java11() {
    assertThat(JavaVersion.parseMajorJavaVersion("11")).isEqualTo(11);
    assertThat(JavaVersion.parseMajorJavaVersion("11.0.1")).isEqualTo(11);
    assertThat(JavaVersion.parseMajorJavaVersion("11.0.11+9")).isEqualTo(11);
  }

  @Test
  public void testParseMajorJavaVersion_java17() {
    assertThat(JavaVersion.parseMajorJavaVersion("17")).isEqualTo(17);
    assertThat(JavaVersion.parseMajorJavaVersion("17.0.1")).isEqualTo(17);
    assertThat(JavaVersion.parseMajorJavaVersion("17.0.2+8")).isEqualTo(17);
  }

  @Test
  public void testParseMajorJavaVersion_java21() {
    assertThat(JavaVersion.parseMajorJavaVersion("21")).isEqualTo(21);
    assertThat(JavaVersion.parseMajorJavaVersion("21.0.1")).isEqualTo(21);
  }

  // ----- Edge cases and fallback behavior -----

  @Test
  public void testParseMajorJavaVersion_unknownFormat_defaultsTo6() {
    // Unknown formats should default to 6 (minimum supported JDK version)
    assertThat(JavaVersion.parseMajorJavaVersion("Java9")).isEqualTo(6);
    assertThat(JavaVersion.parseMajorJavaVersion("unknown")).isEqualTo(6);
    assertThat(JavaVersion.parseMajorJavaVersion("")).isEqualTo(6);
  }

  @Test
  public void testParseMajorJavaVersion_nonNumericPrefix_defaultsTo6() {
    // Strings starting with non-digits that can't be parsed
    assertThat(JavaVersion.parseMajorJavaVersion("abc")).isEqualTo(6);
    assertThat(JavaVersion.parseMajorJavaVersion("version-11")).isEqualTo(6);
  }

  @Test
  public void testParseMajorJavaVersion_extractsLeadingDigits() {
    // The extractBeginningInt fallback extracts leading digits
    assertThat(JavaVersion.parseMajorJavaVersion("11-custom")).isEqualTo(11);
    assertThat(JavaVersion.parseMajorJavaVersion("17suffix")).isEqualTo(17);
  }

  @Test
  public void testParseMajorJavaVersion_dottedVersionFirstPartIsOne() {
    // When first part is 1, it uses the second part as version
    assertThat(JavaVersion.parseMajorJavaVersion("1.5.0")).isEqualTo(5);
    assertThat(JavaVersion.parseMajorJavaVersion("1.9.0")).isEqualTo(9);
  }

  @Test
  public void testParseMajorJavaVersion_underscoreSeparator() {
    // Underscore is also a valid separator in the split regex
    assertThat(JavaVersion.parseMajorJavaVersion("1_8_0")).isEqualTo(8);
  }
}
