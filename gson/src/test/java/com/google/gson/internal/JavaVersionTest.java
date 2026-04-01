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

public final class JavaVersionTest {

  @Test
  public void testParseMajorJavaVersionLegacyStyle() {
    assertThat(JavaVersion.parseMajorJavaVersion("1.8.0_191")).isEqualTo(8);
  }

  @Test
  public void testParseMajorJavaVersionNewStyle() {
    assertThat(JavaVersion.parseMajorJavaVersion("9.0.4")).isEqualTo(9);
  }

  @Test
  public void testParseMajorJavaVersionSingleNumber() {
    assertThat(JavaVersion.parseMajorJavaVersion("11")).isEqualTo(11);
  }

  @Test
  public void testParseMajorJavaVersionNonDottedPrefix() {
    // e.g. "9-debian" style — parseDotted returns -1, falls back to extractBeginningInt
    assertThat(JavaVersion.parseMajorJavaVersion("9-debian")).isEqualTo(9);
  }

  @Test
  public void testParseMajorJavaVersionUnparseable() {
    // No numeric prefix at all — should return default 6
    assertThat(JavaVersion.parseMajorJavaVersion("unknown")).isEqualTo(6);
  }

  @Test
  public void testParseMajorJavaVersionEmptyString() {
    assertThat(JavaVersion.parseMajorJavaVersion("")).isEqualTo(6);
  }

  @Test
  public void testParseMajorJavaVersionLegacyStyle16() {
    assertThat(JavaVersion.parseMajorJavaVersion("1.6.0_45")).isEqualTo(6);
  }

  @Test
  public void testGetMajorJavaVersion() {
    int version = JavaVersion.getMajorJavaVersion();
    assertThat(version).isAtLeast(6);
  }

  @Test
  public void testIsJava9OrLaterConsistentWithGetMajorJavaVersion() {
    boolean expected = JavaVersion.getMajorJavaVersion() >= 9;
    assertThat(JavaVersion.isJava9OrLater()).isEqualTo(expected);
  }
}
