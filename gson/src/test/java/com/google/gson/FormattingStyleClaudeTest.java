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

package com.google.gson;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

/**
 * Tests for {@link FormattingStyle}.
 *
 * @author Claude
 */
public class FormattingStyleClaudeTest {

  // ==========================================================================
  // Static constant tests
  // ==========================================================================

  @Test
  public void testCompact_hasEmptyNewline() {
    assertThat(FormattingStyle.COMPACT.getNewline()).isEmpty();
  }

  @Test
  public void testCompact_hasEmptyIndent() {
    assertThat(FormattingStyle.COMPACT.getIndent()).isEmpty();
  }

  @Test
  public void testCompact_doesNotUseSpaceAfterSeparators() {
    assertThat(FormattingStyle.COMPACT.usesSpaceAfterSeparators()).isFalse();
  }

  @Test
  public void testPretty_hasNewline() {
    assertThat(FormattingStyle.PRETTY.getNewline()).isEqualTo("\n");
  }

  @Test
  public void testPretty_hasTwoSpaceIndent() {
    assertThat(FormattingStyle.PRETTY.getIndent()).isEqualTo("  ");
  }

  @Test
  public void testPretty_usesSpaceAfterSeparators() {
    assertThat(FormattingStyle.PRETTY.usesSpaceAfterSeparators()).isTrue();
  }

  // ==========================================================================
  // withNewline tests
  // ==========================================================================

  @Test
  public void testWithNewline_validNewline_createsNewInstance() {
    FormattingStyle original = FormattingStyle.COMPACT;
    FormattingStyle modified = original.withNewline("\n");
    assertThat(modified).isNotSameInstanceAs(original);
  }

  @Test
  public void testWithNewline_preservesOtherProperties() {
    FormattingStyle original = FormattingStyle.PRETTY;
    FormattingStyle modified = original.withNewline("\r\n");
    assertThat(modified.getNewline()).isEqualTo("\r\n");
    assertThat(modified.getIndent()).isEqualTo(original.getIndent());
    assertThat(modified.usesSpaceAfterSeparators()).isEqualTo(original.usesSpaceAfterSeparators());
  }

  @Test
  public void testWithNewline_emptyString_succeeds() {
    FormattingStyle style = FormattingStyle.PRETTY.withNewline("");
    assertThat(style.getNewline()).isEmpty();
  }

  @Test
  public void testWithNewline_lineFeed_succeeds() {
    FormattingStyle style = FormattingStyle.COMPACT.withNewline("\n");
    assertThat(style.getNewline()).isEqualTo("\n");
  }

  @Test
  public void testWithNewline_carriageReturn_succeeds() {
    FormattingStyle style = FormattingStyle.COMPACT.withNewline("\r");
    assertThat(style.getNewline()).isEqualTo("\r");
  }

  @Test
  public void testWithNewline_carriageReturnLineFeed_succeeds() {
    FormattingStyle style = FormattingStyle.COMPACT.withNewline("\r\n");
    assertThat(style.getNewline()).isEqualTo("\r\n");
  }

  @Test
  public void testWithNewline_multipleNewlines_succeeds() {
    FormattingStyle style = FormattingStyle.COMPACT.withNewline("\n\n");
    assertThat(style.getNewline()).isEqualTo("\n\n");
  }

  @Test
  public void testWithNewline_complexNewlinePattern_succeeds() {
    FormattingStyle style = FormattingStyle.COMPACT.withNewline("\r\n\r\n");
    assertThat(style.getNewline()).isEqualTo("\r\n\r\n");
  }

  @Test
  public void testWithNewline_null_throwsNullPointerException() {
    assertThrows(NullPointerException.class, () -> FormattingStyle.COMPACT.withNewline(null));
  }

  @Test
  public void testWithNewline_invalidCharacter_throwsIllegalArgumentException() {
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class, () -> FormattingStyle.COMPACT.withNewline("invalid"));
    assertThat(exception.getMessage()).contains("newline");
  }

  @Test
  public void testWithNewline_spaceIsInvalid_throwsIllegalArgumentException() {
    assertThrows(
        IllegalArgumentException.class, () -> FormattingStyle.COMPACT.withNewline(" "));
  }

  @Test
  public void testWithNewline_tabIsInvalid_throwsIllegalArgumentException() {
    assertThrows(
        IllegalArgumentException.class, () -> FormattingStyle.COMPACT.withNewline("\t"));
  }

  @Test
  public void testWithNewline_mixedInvalid_throwsIllegalArgumentException() {
    assertThrows(
        IllegalArgumentException.class, () -> FormattingStyle.COMPACT.withNewline("\n "));
  }

  // ==========================================================================
  // withIndent tests
  // ==========================================================================

  @Test
  public void testWithIndent_validIndent_createsNewInstance() {
    FormattingStyle original = FormattingStyle.COMPACT;
    FormattingStyle modified = original.withIndent("  ");
    assertThat(modified).isNotSameInstanceAs(original);
  }

  @Test
  public void testWithIndent_preservesOtherProperties() {
    FormattingStyle original = FormattingStyle.PRETTY;
    FormattingStyle modified = original.withIndent("\t");
    assertThat(modified.getIndent()).isEqualTo("\t");
    assertThat(modified.getNewline()).isEqualTo(original.getNewline());
    assertThat(modified.usesSpaceAfterSeparators()).isEqualTo(original.usesSpaceAfterSeparators());
  }

  @Test
  public void testWithIndent_emptyString_succeeds() {
    FormattingStyle style = FormattingStyle.PRETTY.withIndent("");
    assertThat(style.getIndent()).isEmpty();
  }

  @Test
  public void testWithIndent_singleSpace_succeeds() {
    FormattingStyle style = FormattingStyle.COMPACT.withIndent(" ");
    assertThat(style.getIndent()).isEqualTo(" ");
  }

  @Test
  public void testWithIndent_twoSpaces_succeeds() {
    FormattingStyle style = FormattingStyle.COMPACT.withIndent("  ");
    assertThat(style.getIndent()).isEqualTo("  ");
  }

  @Test
  public void testWithIndent_fourSpaces_succeeds() {
    FormattingStyle style = FormattingStyle.COMPACT.withIndent("    ");
    assertThat(style.getIndent()).isEqualTo("    ");
  }

  @Test
  public void testWithIndent_singleTab_succeeds() {
    FormattingStyle style = FormattingStyle.COMPACT.withIndent("\t");
    assertThat(style.getIndent()).isEqualTo("\t");
  }

  @Test
  public void testWithIndent_multipleTabs_succeeds() {
    FormattingStyle style = FormattingStyle.COMPACT.withIndent("\t\t");
    assertThat(style.getIndent()).isEqualTo("\t\t");
  }

  @Test
  public void testWithIndent_mixedSpacesAndTabs_succeeds() {
    FormattingStyle style = FormattingStyle.COMPACT.withIndent(" \t ");
    assertThat(style.getIndent()).isEqualTo(" \t ");
  }

  @Test
  public void testWithIndent_null_throwsNullPointerException() {
    assertThrows(NullPointerException.class, () -> FormattingStyle.COMPACT.withIndent(null));
  }

  @Test
  public void testWithIndent_invalidCharacter_throwsIllegalArgumentException() {
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class, () -> FormattingStyle.COMPACT.withIndent("abc"));
    assertThat(exception.getMessage()).contains("indent");
  }

  @Test
  public void testWithIndent_newlineIsInvalid_throwsIllegalArgumentException() {
    assertThrows(
        IllegalArgumentException.class, () -> FormattingStyle.COMPACT.withIndent("\n"));
  }

  @Test
  public void testWithIndent_carriageReturnIsInvalid_throwsIllegalArgumentException() {
    assertThrows(
        IllegalArgumentException.class, () -> FormattingStyle.COMPACT.withIndent("\r"));
  }

  @Test
  public void testWithIndent_mixedInvalid_throwsIllegalArgumentException() {
    assertThrows(
        IllegalArgumentException.class, () -> FormattingStyle.COMPACT.withIndent("  x"));
  }

  // ==========================================================================
  // withSpaceAfterSeparators tests
  // ==========================================================================

  @Test
  public void testWithSpaceAfterSeparators_true_createsNewInstance() {
    FormattingStyle original = FormattingStyle.COMPACT;
    FormattingStyle modified = original.withSpaceAfterSeparators(true);
    assertThat(modified).isNotSameInstanceAs(original);
  }

  @Test
  public void testWithSpaceAfterSeparators_false_createsNewInstance() {
    FormattingStyle original = FormattingStyle.PRETTY;
    FormattingStyle modified = original.withSpaceAfterSeparators(false);
    assertThat(modified).isNotSameInstanceAs(original);
  }

  @Test
  public void testWithSpaceAfterSeparators_true_preservesOtherProperties() {
    FormattingStyle original = FormattingStyle.COMPACT;
    FormattingStyle modified = original.withSpaceAfterSeparators(true);
    assertThat(modified.usesSpaceAfterSeparators()).isTrue();
    assertThat(modified.getNewline()).isEqualTo(original.getNewline());
    assertThat(modified.getIndent()).isEqualTo(original.getIndent());
  }

  @Test
  public void testWithSpaceAfterSeparators_false_preservesOtherProperties() {
    FormattingStyle original = FormattingStyle.PRETTY;
    FormattingStyle modified = original.withSpaceAfterSeparators(false);
    assertThat(modified.usesSpaceAfterSeparators()).isFalse();
    assertThat(modified.getNewline()).isEqualTo(original.getNewline());
    assertThat(modified.getIndent()).isEqualTo(original.getIndent());
  }

  @Test
  public void testWithSpaceAfterSeparators_sameValue_createsNewInstance() {
    // Even when setting the same value, a new instance should be created
    FormattingStyle original = FormattingStyle.PRETTY;
    FormattingStyle modified = original.withSpaceAfterSeparators(true);
    assertThat(modified).isNotSameInstanceAs(original);
    assertThat(modified.usesSpaceAfterSeparators()).isTrue();
  }

  // ==========================================================================
  // getNewline tests
  // ==========================================================================

  @Test
  public void testGetNewline_returnsConfiguredNewline() {
    FormattingStyle style = FormattingStyle.COMPACT.withNewline("\r\n");
    assertThat(style.getNewline()).isEqualTo("\r\n");
  }

  @Test
  public void testGetNewline_returnsEmptyStringForCompact() {
    assertThat(FormattingStyle.COMPACT.getNewline()).isEqualTo("");
  }

  @Test
  public void testGetNewline_consistentAcrossMultipleCalls() {
    FormattingStyle style = FormattingStyle.PRETTY;
    String first = style.getNewline();
    String second = style.getNewline();
    assertThat(first).isSameInstanceAs(second);
  }

  // ==========================================================================
  // getIndent tests
  // ==========================================================================

  @Test
  public void testGetIndent_returnsConfiguredIndent() {
    FormattingStyle style = FormattingStyle.COMPACT.withIndent("\t\t");
    assertThat(style.getIndent()).isEqualTo("\t\t");
  }

  @Test
  public void testGetIndent_returnsEmptyStringForCompact() {
    assertThat(FormattingStyle.COMPACT.getIndent()).isEqualTo("");
  }

  @Test
  public void testGetIndent_consistentAcrossMultipleCalls() {
    FormattingStyle style = FormattingStyle.PRETTY;
    String first = style.getIndent();
    String second = style.getIndent();
    assertThat(first).isSameInstanceAs(second);
  }

  // ==========================================================================
  // usesSpaceAfterSeparators tests
  // ==========================================================================

  @Test
  public void testUsesSpaceAfterSeparators_returnsTrueWhenEnabled() {
    FormattingStyle style = FormattingStyle.COMPACT.withSpaceAfterSeparators(true);
    assertThat(style.usesSpaceAfterSeparators()).isTrue();
  }

  @Test
  public void testUsesSpaceAfterSeparators_returnsFalseWhenDisabled() {
    FormattingStyle style = FormattingStyle.PRETTY.withSpaceAfterSeparators(false);
    assertThat(style.usesSpaceAfterSeparators()).isFalse();
  }

  @Test
  public void testUsesSpaceAfterSeparators_consistentAcrossMultipleCalls() {
    FormattingStyle style = FormattingStyle.PRETTY;
    boolean first = style.usesSpaceAfterSeparators();
    boolean second = style.usesSpaceAfterSeparators();
    assertThat(first).isEqualTo(second);
  }

  // ==========================================================================
  // Chaining tests (using multiple with* methods)
  // ==========================================================================

  @Test
  public void testChaining_allMethodsCanBeChained() {
    FormattingStyle style =
        FormattingStyle.COMPACT
            .withNewline("\n")
            .withIndent("    ")
            .withSpaceAfterSeparators(true);

    assertThat(style.getNewline()).isEqualTo("\n");
    assertThat(style.getIndent()).isEqualTo("    ");
    assertThat(style.usesSpaceAfterSeparators()).isTrue();
  }

  @Test
  public void testChaining_lastValueWins() {
    FormattingStyle style =
        FormattingStyle.COMPACT
            .withIndent("  ")
            .withIndent("\t")
            .withIndent("    ");

    assertThat(style.getIndent()).isEqualTo("    ");
  }

  @Test
  public void testChaining_preservesIntermediateStates() {
    FormattingStyle step1 = FormattingStyle.COMPACT.withNewline("\n");
    FormattingStyle step2 = step1.withIndent("  ");
    FormattingStyle step3 = step2.withSpaceAfterSeparators(true);

    // Original is unchanged
    assertThat(FormattingStyle.COMPACT.getNewline()).isEmpty();
    assertThat(FormattingStyle.COMPACT.getIndent()).isEmpty();
    assertThat(FormattingStyle.COMPACT.usesSpaceAfterSeparators()).isFalse();

    // Step1 only has newline changed
    assertThat(step1.getNewline()).isEqualTo("\n");
    assertThat(step1.getIndent()).isEmpty();
    assertThat(step1.usesSpaceAfterSeparators()).isFalse();

    // Step2 has newline and indent changed
    assertThat(step2.getNewline()).isEqualTo("\n");
    assertThat(step2.getIndent()).isEqualTo("  ");
    assertThat(step2.usesSpaceAfterSeparators()).isFalse();

    // Step3 has all changed
    assertThat(step3.getNewline()).isEqualTo("\n");
    assertThat(step3.getIndent()).isEqualTo("  ");
    assertThat(step3.usesSpaceAfterSeparators()).isTrue();
  }

  // ==========================================================================
  // Immutability tests
  // ==========================================================================

  @Test
  public void testImmutability_withNewlineDoesNotModifyOriginal() {
    FormattingStyle original = FormattingStyle.PRETTY;
    String originalNewline = original.getNewline();

    @SuppressWarnings("unused")
    FormattingStyle modified = original.withNewline("\r\n");

    assertThat(original.getNewline()).isEqualTo(originalNewline);
  }

  @Test
  public void testImmutability_withIndentDoesNotModifyOriginal() {
    FormattingStyle original = FormattingStyle.PRETTY;
    String originalIndent = original.getIndent();

    @SuppressWarnings("unused")
    FormattingStyle modified = original.withIndent("\t\t\t");

    assertThat(original.getIndent()).isEqualTo(originalIndent);
  }

  @Test
  public void testImmutability_withSpaceAfterSeparatorsDoesNotModifyOriginal() {
    FormattingStyle original = FormattingStyle.PRETTY;
    boolean originalSpace = original.usesSpaceAfterSeparators();

    @SuppressWarnings("unused")
    FormattingStyle modified = original.withSpaceAfterSeparators(false);

    assertThat(original.usesSpaceAfterSeparators()).isEqualTo(originalSpace);
  }

  @Test
  public void testImmutability_staticConstantsAreUnchanged() {
    // Perform various modifications
    @SuppressWarnings("unused")
    FormattingStyle modified1 =
        FormattingStyle.COMPACT.withNewline("\n").withIndent("  ").withSpaceAfterSeparators(true);
    @SuppressWarnings("unused")
    FormattingStyle modified2 =
        FormattingStyle.PRETTY.withNewline("").withIndent("").withSpaceAfterSeparators(false);

    // Verify static constants are unchanged
    assertThat(FormattingStyle.COMPACT.getNewline()).isEmpty();
    assertThat(FormattingStyle.COMPACT.getIndent()).isEmpty();
    assertThat(FormattingStyle.COMPACT.usesSpaceAfterSeparators()).isFalse();

    assertThat(FormattingStyle.PRETTY.getNewline()).isEqualTo("\n");
    assertThat(FormattingStyle.PRETTY.getIndent()).isEqualTo("  ");
    assertThat(FormattingStyle.PRETTY.usesSpaceAfterSeparators()).isTrue();
  }
}
