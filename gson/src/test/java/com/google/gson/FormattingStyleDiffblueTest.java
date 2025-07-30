package com.google.gson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class FormattingStyleDiffblueTest {
  /**
   * Test {@link FormattingStyle#withNewline(String)}.
   *
   * <ul>
   *   <li>Given {@link FormattingStyle#COMPACT}.
   *   <li>When lf lf lf.
   *   <li>Then return Newline is lf lf lf.
   * </ul>
   *
   * <p>Method under test: {@link FormattingStyle#withNewline(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"FormattingStyle FormattingStyle.withNewline(String)"})
  public void testWithNewline_givenCompact_whenLfLfLf_thenReturnNewlineIsLfLfLf() {
    // Arrange and Act
    FormattingStyle actualWithNewlineResult = FormattingStyle.COMPACT.withNewline("\n\n\n");

    // Assert
    assertEquals("", actualWithNewlineResult.getIndent());
    assertEquals("\n\n\n", actualWithNewlineResult.getNewline());
  }

  /**
   * Test {@link FormattingStyle#withNewline(String)}.
   *
   * <ul>
   *   <li>Given {@link FormattingStyle#COMPACT}.
   *   <li>When lf lf.
   *   <li>Then return Newline is lf lf.
   * </ul>
   *
   * <p>Method under test: {@link FormattingStyle#withNewline(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"FormattingStyle FormattingStyle.withNewline(String)"})
  public void testWithNewline_givenCompact_whenLfLf_thenReturnNewlineIsLfLf() {
    // Arrange and Act
    FormattingStyle actualWithNewlineResult = FormattingStyle.COMPACT.withNewline("\n\n");

    // Assert
    assertEquals("", actualWithNewlineResult.getIndent());
    assertEquals("\n\n", actualWithNewlineResult.getNewline());
  }

  /**
   * Test {@link FormattingStyle#withNewline(String)}.
   *
   * <ul>
   *   <li>Given {@link FormattingStyle#COMPACT}.
   *   <li>When lf.
   *   <li>Then return Newline is lf.
   * </ul>
   *
   * <p>Method under test: {@link FormattingStyle#withNewline(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"FormattingStyle FormattingStyle.withNewline(String)"})
  public void testWithNewline_givenCompact_whenLf_thenReturnNewlineIsLf() {
    // Arrange and Act
    FormattingStyle actualWithNewlineResult = FormattingStyle.COMPACT.withNewline("\n");

    // Assert
    assertEquals("", actualWithNewlineResult.getIndent());
    assertEquals("\n", actualWithNewlineResult.getNewline());
  }

  /**
   * Test {@link FormattingStyle#withNewline(String)}.
   *
   * <ul>
   *   <li>Given {@link FormattingStyle#COMPACT}.
   *   <li>When {@code Newline}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link FormattingStyle#withNewline(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"FormattingStyle FormattingStyle.withNewline(String)"})
  public void testWithNewline_givenCompact_whenNewline_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> FormattingStyle.COMPACT.withNewline("Newline"));
  }

  /**
   * Test {@link FormattingStyle#withNewline(String)}.
   *
   * <ul>
   *   <li>Given {@link FormattingStyle#PRETTY}.
   *   <li>When lf.
   *   <li>Then return Indent is space space.
   * </ul>
   *
   * <p>Method under test: {@link FormattingStyle#withNewline(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"FormattingStyle FormattingStyle.withNewline(String)"})
  public void testWithNewline_givenPretty_whenLf_thenReturnIndentIsSpaceSpace() {
    // Arrange and Act
    FormattingStyle actualWithNewlineResult = FormattingStyle.PRETTY.withNewline("\n");

    // Assert
    assertEquals("  ", actualWithNewlineResult.getIndent());
    assertEquals("\n", actualWithNewlineResult.getNewline());
  }

  /**
   * Test {@link FormattingStyle#withIndent(String)}.
   *
   * <ul>
   *   <li>Given {@link FormattingStyle#COMPACT}.
   *   <li>When {@code Indent}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link FormattingStyle#withIndent(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"FormattingStyle FormattingStyle.withIndent(String)"})
  public void testWithIndent_givenCompact_whenIndent_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> FormattingStyle.COMPACT.withIndent("Indent"));
  }

  /**
   * Test {@link FormattingStyle#withIndent(String)}.
   *
   * <ul>
   *   <li>Given {@link FormattingStyle#COMPACT}.
   *   <li>When space space.
   *   <li>Then return Indent is space space.
   * </ul>
   *
   * <p>Method under test: {@link FormattingStyle#withIndent(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"FormattingStyle FormattingStyle.withIndent(String)"})
  public void testWithIndent_givenCompact_whenSpaceSpace_thenReturnIndentIsSpaceSpace() {
    // Arrange and Act
    FormattingStyle actualWithIndentResult = FormattingStyle.COMPACT.withIndent("  ");

    // Assert
    assertEquals("  ", actualWithIndentResult.getIndent());
    assertEquals("", actualWithIndentResult.getNewline());
  }

  /**
   * Test {@link FormattingStyle#withIndent(String)}.
   *
   * <ul>
   *   <li>Given {@link FormattingStyle#COMPACT}.
   *   <li>When space.
   *   <li>Then return Indent is space.
   * </ul>
   *
   * <p>Method under test: {@link FormattingStyle#withIndent(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"FormattingStyle FormattingStyle.withIndent(String)"})
  public void testWithIndent_givenCompact_whenSpace_thenReturnIndentIsSpace() {
    // Arrange and Act
    FormattingStyle actualWithIndentResult = FormattingStyle.COMPACT.withIndent(" ");

    // Assert
    assertEquals(" ", actualWithIndentResult.getIndent());
    assertEquals("", actualWithIndentResult.getNewline());
  }

  /**
   * Test {@link FormattingStyle#withIndent(String)}.
   *
   * <ul>
   *   <li>Given {@link FormattingStyle#PRETTY}.
   *   <li>When {@code Indent}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link FormattingStyle#withIndent(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"FormattingStyle FormattingStyle.withIndent(String)"})
  public void testWithIndent_givenPretty_whenIndent_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> FormattingStyle.PRETTY.withIndent("Indent"));
  }

  /**
   * Test {@link FormattingStyle#withSpaceAfterSeparators(boolean)}.
   *
   * <ul>
   *   <li>Given {@link FormattingStyle#COMPACT}.
   *   <li>Then return Indent is empty string.
   * </ul>
   *
   * <p>Method under test: {@link FormattingStyle#withSpaceAfterSeparators(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"FormattingStyle FormattingStyle.withSpaceAfterSeparators(boolean)"})
  public void testWithSpaceAfterSeparators_givenCompact_thenReturnIndentIsEmptyString() {
    // Arrange and Act
    FormattingStyle actualWithSpaceAfterSeparatorsResult =
        FormattingStyle.COMPACT.withSpaceAfterSeparators(true);

    // Assert
    assertEquals("", actualWithSpaceAfterSeparatorsResult.getIndent());
    assertEquals("", actualWithSpaceAfterSeparatorsResult.getNewline());
  }

  /**
   * Test {@link FormattingStyle#withSpaceAfterSeparators(boolean)}.
   *
   * <ul>
   *   <li>Given {@link FormattingStyle#PRETTY}.
   *   <li>Then return Indent is space space.
   * </ul>
   *
   * <p>Method under test: {@link FormattingStyle#withSpaceAfterSeparators(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"FormattingStyle FormattingStyle.withSpaceAfterSeparators(boolean)"})
  public void testWithSpaceAfterSeparators_givenPretty_thenReturnIndentIsSpaceSpace() {
    // Arrange and Act
    FormattingStyle actualWithSpaceAfterSeparatorsResult =
        FormattingStyle.PRETTY.withSpaceAfterSeparators(true);

    // Assert
    assertEquals("  ", actualWithSpaceAfterSeparatorsResult.getIndent());
    assertEquals("\n", actualWithSpaceAfterSeparatorsResult.getNewline());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link FormattingStyle#getIndent()}
   *   <li>{@link FormattingStyle#getNewline()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String FormattingStyle.getIndent()", "String FormattingStyle.getNewline()"})
  public void testGettersAndSetters() {
    // Arrange
    FormattingStyle formattingStyle = FormattingStyle.COMPACT;

    // Act
    String actualIndent = formattingStyle.getIndent();

    // Assert
    assertEquals("", actualIndent);
    assertEquals("", formattingStyle.getNewline());
  }

  /**
   * Test {@link FormattingStyle#usesSpaceAfterSeparators()}.
   *
   * <ul>
   *   <li>Given {@link FormattingStyle#COMPACT}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link FormattingStyle#usesSpaceAfterSeparators()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FormattingStyle.usesSpaceAfterSeparators()"})
  public void testUsesSpaceAfterSeparators_givenCompact_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(FormattingStyle.COMPACT.usesSpaceAfterSeparators());
  }

  /**
   * Test {@link FormattingStyle#usesSpaceAfterSeparators()}.
   *
   * <ul>
   *   <li>Given {@link FormattingStyle#PRETTY}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link FormattingStyle#usesSpaceAfterSeparators()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FormattingStyle.usesSpaceAfterSeparators()"})
  public void testUsesSpaceAfterSeparators_givenPretty_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(FormattingStyle.PRETTY.usesSpaceAfterSeparators());
  }
}
