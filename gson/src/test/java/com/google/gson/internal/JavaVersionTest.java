/*
 * Copyright (C) 2022 Google Inc.
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

public class JavaVersionTest {

  @Test
  public void testParseMajorJavaVersion_withLegacyFormat() {
    assertThat(JavaVersion.parseMajorJavaVersion("1.8.0_292")).isEqualTo(8);
  }

  @Test
  public void testParseMajorJavaVersion_withLegacyFormatMinimal() {
    assertThat(JavaVersion.parseMajorJavaVersion("1.7")).isEqualTo(7);
  }

  @Test
  public void testParseMajorJavaVersion_withModernFormat() {
    assertThat(JavaVersion.parseMajorJavaVersion("9.0.4")).isEqualTo(9);
  }

  @Test
  public void testParseMajorJavaVersion_withModernFormatSingleDigit() {
    assertThat(JavaVersion.parseMajorJavaVersion("11")).isEqualTo(11);
  }

  @Test
  public void testParseMajorJavaVersion_withModernFormatHighVersion() {
    assertThat(JavaVersion.parseMajorJavaVersion("17.0.1")).isEqualTo(17);
  }

  @Test
  public void testParseMajorJavaVersion_withDebianFormat() {
    assertThat(JavaVersion.parseMajorJavaVersion("9-debian")).isEqualTo(9);
  }

  @Test
  public void testParseMajorJavaVersion_withNumberAtBeginning() {
    assertThat(JavaVersion.parseMajorJavaVersion("11-ea")).isEqualTo(11);
  }

  @Test
  public void testParseMajorJavaVersion_withNonNumericStart() {
    assertThat(JavaVersion.parseMajorJavaVersion("abc123")).isEqualTo(6);
  }

  @Test
  public void testParseMajorJavaVersion_withEmptyString() {
    assertThat(JavaVersion.parseMajorJavaVersion("")).isEqualTo(6);
  }

  @Test
  public void testParseMajorJavaVersion_withNonNumeric() {
    assertThat(JavaVersion.parseMajorJavaVersion("invalid")).isEqualTo(6);
  }

  @Test
  public void testParseMajorJavaVersion_withMixedContent() {
    assertThat(JavaVersion.parseMajorJavaVersion("version-8")).isEqualTo(6);
  }

  @Test
  public void testParseMajorJavaVersion_withUnderscoreSeparator() {
    assertThat(JavaVersion.parseMajorJavaVersion("1_8_0")).isEqualTo(8);
  }

  @Test
  public void testGetMajorJavaVersion_returnsPositiveValue() {
    int version = JavaVersion.getMajorJavaVersion();
    assertThat(version).isAtLeast(6);
  }

  @Test
  public void testIsJava9OrLater_withCurrentJavaVersion() {
    int version = JavaVersion.getMajorJavaVersion();
    boolean isJava9OrLater = JavaVersion.isJava9OrLater();
    assertThat(isJava9OrLater).isEqualTo(version >= 9);
  }
}
