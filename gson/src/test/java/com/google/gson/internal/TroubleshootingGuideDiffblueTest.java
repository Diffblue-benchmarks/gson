package com.google.gson.internal;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TroubleshootingGuideDiffblueTest {
  /** Method under test: {@link TroubleshootingGuide#createUrl(String)} */
  @Test
  public void testCreateUrl() {
    // Arrange, Act and Assert
    assertEquals(
        "https://github.com/google/gson/blob/main/Troubleshooting.md#https://example.org/example",
        TroubleshootingGuide.createUrl("https://example.org/example"));
  }
}
