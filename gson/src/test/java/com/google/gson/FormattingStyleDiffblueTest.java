package com.google.gson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FormattingStyleDiffblueTest {
  /** Method under test: {@link FormattingStyle#withNewline(String)} */
  @Test
  public void testWithNewline() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> FormattingStyle.COMPACT.withNewline("Newline"));
  }

  /** Method under test: {@link FormattingStyle#withNewline(String)} */
  @Test
  public void testWithNewline2() {
    // Arrange and Act
    FormattingStyle actualWithNewlineResult = FormattingStyle.COMPACT.withNewline("\n");

    // Assert
    assertEquals("", actualWithNewlineResult.getIndent());
    assertEquals("\n", actualWithNewlineResult.getNewline());
  }

  /** Method under test: {@link FormattingStyle#withNewline(String)} */
  @Test
  public void testWithNewline3() {
    // Arrange and Act
    FormattingStyle actualWithNewlineResult = FormattingStyle.PRETTY.withNewline("\n");

    // Assert
    assertEquals("  ", actualWithNewlineResult.getIndent());
    assertEquals("\n", actualWithNewlineResult.getNewline());
  }

  /** Method under test: {@link FormattingStyle#withNewline(String)} */
  @Test
  public void testWithNewline4() {
    // Arrange and Act
    FormattingStyle actualWithNewlineResult = FormattingStyle.COMPACT.withNewline("\n\n");

    // Assert
    assertEquals("", actualWithNewlineResult.getIndent());
    assertEquals("\n\n", actualWithNewlineResult.getNewline());
  }

  /** Method under test: {@link FormattingStyle#withIndent(String)} */
  @Test
  public void testWithIndent() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> FormattingStyle.COMPACT.withIndent("Indent"));
    assertThrows(IllegalArgumentException.class, () -> FormattingStyle.PRETTY.withIndent("Indent"));
  }

  /** Method under test: {@link FormattingStyle#withIndent(String)} */
  @Test
  public void testWithIndent2() {
    // Arrange and Act
    FormattingStyle actualWithIndentResult = FormattingStyle.COMPACT.withIndent(" ");

    // Assert
    assertEquals(" ", actualWithIndentResult.getIndent());
    assertEquals("", actualWithIndentResult.getNewline());
  }

  /** Method under test: {@link FormattingStyle#withIndent(String)} */
  @Test
  public void testWithIndent3() {
    // Arrange and Act
    FormattingStyle actualWithIndentResult = FormattingStyle.COMPACT.withIndent("  ");

    // Assert
    assertEquals("  ", actualWithIndentResult.getIndent());
    assertEquals("", actualWithIndentResult.getNewline());
  }

  /** Method under test: {@link FormattingStyle#withSpaceAfterSeparators(boolean)} */
  @Test
  public void testWithSpaceAfterSeparators() {
    // Arrange and Act
    FormattingStyle actualWithSpaceAfterSeparatorsResult =
        FormattingStyle.COMPACT.withSpaceAfterSeparators(true);

    // Assert
    assertEquals("", actualWithSpaceAfterSeparatorsResult.getIndent());
    assertEquals("", actualWithSpaceAfterSeparatorsResult.getNewline());
  }

  /** Method under test: {@link FormattingStyle#withSpaceAfterSeparators(boolean)} */
  @Test
  public void testWithSpaceAfterSeparators2() {
    // Arrange and Act
    FormattingStyle actualWithSpaceAfterSeparatorsResult =
        FormattingStyle.PRETTY.withSpaceAfterSeparators(true);

    // Assert
    assertEquals("  ", actualWithSpaceAfterSeparatorsResult.getIndent());
    assertEquals("\n", actualWithSpaceAfterSeparatorsResult.getNewline());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link FormattingStyle#getIndent()}
   *   <li>{@link FormattingStyle#getNewline()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    FormattingStyle formattingStyle = FormattingStyle.COMPACT;

    // Act
    String actualIndent = formattingStyle.getIndent();

    // Assert
    assertEquals("", actualIndent);
    assertEquals("", formattingStyle.getNewline());
  }

  /** Method under test: {@link FormattingStyle#usesSpaceAfterSeparators()} */
  @Test
  public void testUsesSpaceAfterSeparators() {
    // Arrange, Act and Assert
    assertFalse(FormattingStyle.COMPACT.usesSpaceAfterSeparators());
    assertTrue(FormattingStyle.PRETTY.usesSpaceAfterSeparators());
  }
}
