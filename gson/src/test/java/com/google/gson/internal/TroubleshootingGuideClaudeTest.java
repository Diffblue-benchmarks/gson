/*
 * Copyright (C) 2008 Google Inc.
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/** Tests for {@link TroubleshootingGuide}. */
public class TroubleshootingGuideClaudeTest {

  private static final String BASE_URL =
      "https://github.com/google/gson/blob/main/Troubleshooting.md#";

  // ==========================================================================
  // createUrl tests
  // ==========================================================================

  @Test
  public void createUrl_withSimpleId_returnsCorrectUrl() {
    String id = "overview";
    String result = TroubleshootingGuide.createUrl(id);
    assertEquals(BASE_URL + "overview", result);
  }

  @Test
  public void createUrl_withHyphenatedId_returnsCorrectUrl() {
    String id = "class-not-found";
    String result = TroubleshootingGuide.createUrl(id);
    assertEquals(BASE_URL + "class-not-found", result);
  }

  @Test
  public void createUrl_withUnderscoreId_returnsCorrectUrl() {
    String id = "some_section";
    String result = TroubleshootingGuide.createUrl(id);
    assertEquals(BASE_URL + "some_section", result);
  }

  @Test
  public void createUrl_withNumbersInId_returnsCorrectUrl() {
    String id = "error-404";
    String result = TroubleshootingGuide.createUrl(id);
    assertEquals(BASE_URL + "error-404", result);
  }

  @Test
  public void createUrl_withEmptyString_returnsBaseUrl() {
    String id = "";
    String result = TroubleshootingGuide.createUrl(id);
    assertEquals(BASE_URL, result);
  }

  @Test
  public void createUrl_withSpecialCharacters_appendsThemDirectly() {
    // The method does simple string concatenation, so special characters are passed through
    String id = "troubleshooting%20guide";
    String result = TroubleshootingGuide.createUrl(id);
    assertEquals(BASE_URL + "troubleshooting%20guide", result);
  }

  @Test
  public void createUrl_returnsStringStartingWithHttps() {
    String result = TroubleshootingGuide.createUrl("test");
    assertTrue(result.startsWith("https://"));
  }

  @Test
  public void createUrl_returnsGithubUrl() {
    String result = TroubleshootingGuide.createUrl("test");
    assertTrue(result.contains("github.com/google/gson"));
  }

  @Test
  public void createUrl_containsTroubleshootingMdPath() {
    String result = TroubleshootingGuide.createUrl("test");
    assertTrue(result.contains("Troubleshooting.md"));
  }

  @Test
  public void createUrl_containsAnchorSeparator() {
    String result = TroubleshootingGuide.createUrl("test");
    assertTrue(result.contains("#"));
  }

  @Test
  public void createUrl_withLongId_returnsCorrectUrl() {
    String id = "this-is-a-very-long-section-identifier-for-testing-purposes";
    String result = TroubleshootingGuide.createUrl(id);
    assertEquals(BASE_URL + id, result);
  }

  @Test
  public void createUrl_multipleCalls_returnConsistentResults() {
    String id = "consistency-check";
    String result1 = TroubleshootingGuide.createUrl(id);
    String result2 = TroubleshootingGuide.createUrl(id);
    assertEquals(result1, result2);
  }

  @Test
  public void createUrl_differentIds_returnDifferentUrls() {
    String result1 = TroubleshootingGuide.createUrl("section-one");
    String result2 = TroubleshootingGuide.createUrl("section-two");
    assertTrue(!result1.equals(result2));
  }
}
