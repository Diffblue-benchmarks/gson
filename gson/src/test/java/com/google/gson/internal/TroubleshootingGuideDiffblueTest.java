package com.google.gson.internal;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class TroubleshootingGuideDiffblueTest {
  /**
   * Test {@link TroubleshootingGuide#createUrl(String)}.
   *
   * <p>Method under test: {@link TroubleshootingGuide#createUrl(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String TroubleshootingGuide.createUrl(String)"})
  public void testCreateUrl() {
    // Arrange, Act and Assert
    assertEquals(
        "https://github.com/google/gson/blob/main/Troubleshooting.md#https://example.org/example",
        TroubleshootingGuide.createUrl("https://example.org/example"));
  }
}
