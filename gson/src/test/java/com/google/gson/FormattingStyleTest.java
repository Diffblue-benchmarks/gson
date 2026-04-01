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

public final class FormattingStyleTest {

  @Test
  public void testCompactDefaults() {
    assertThat(FormattingStyle.COMPACT.getNewline()).isEqualTo("");
    assertThat(FormattingStyle.COMPACT.getIndent()).isEqualTo("");
    assertThat(FormattingStyle.COMPACT.usesSpaceAfterSeparators()).isFalse();
  }

  @Test
  public void testPrettyDefaults() {
    assertThat(FormattingStyle.PRETTY.getNewline()).isEqualTo("\n");
    assertThat(FormattingStyle.PRETTY.getIndent()).isEqualTo("  ");
    assertThat(FormattingStyle.PRETTY.usesSpaceAfterSeparators()).isTrue();
  }

  @Test
  public void testWithNewlineValidValues() {
    FormattingStyle style = FormattingStyle.COMPACT.withNewline("\n");
    assertThat(style.getNewline()).isEqualTo("\n");

    style = FormattingStyle.COMPACT.withNewline("\r\n");
    assertThat(style.getNewline()).isEqualTo("\r\n");

    style = FormattingStyle.COMPACT.withNewline("");
    assertThat(style.getNewline()).isEqualTo("");
  }

  @Test
  public void testWithNewlineInvalidValue() {
    assertThrows(
        IllegalArgumentException.class,
        () -> FormattingStyle.COMPACT.withNewline("\t"));
  }

  @Test
  public void testWithNewlineNullThrows() {
    assertThrows(
        NullPointerException.class,
        () -> FormattingStyle.COMPACT.withNewline(null));
  }

  @Test
  public void testWithNewlinePreservesOtherFields() {
    FormattingStyle style = FormattingStyle.PRETTY.withNewline("\r\n");
    assertThat(style.getNewline()).isEqualTo("\r\n");
    assertThat(style.getIndent()).isEqualTo("  ");
    assertThat(style.usesSpaceAfterSeparators()).isTrue();
  }

  @Test
  public void testWithIndentValidValues() {
    FormattingStyle style = FormattingStyle.COMPACT.withIndent("    ");
    assertThat(style.getIndent()).isEqualTo("    ");

    style = FormattingStyle.COMPACT.withIndent("\t");
    assertThat(style.getIndent()).isEqualTo("\t");

    style = FormattingStyle.COMPACT.withIndent("");
    assertThat(style.getIndent()).isEqualTo("");
  }

  @Test
  public void testWithIndentInvalidValue() {
    assertThrows(
        IllegalArgumentException.class,
        () -> FormattingStyle.COMPACT.withIndent("  \n  "));
  }

  @Test
  public void testWithIndentNullThrows() {
    assertThrows(
        NullPointerException.class,
        () -> FormattingStyle.COMPACT.withIndent(null));
  }

  @Test
  public void testWithIndentPreservesOtherFields() {
    FormattingStyle style = FormattingStyle.PRETTY.withIndent("\t");
    assertThat(style.getNewline()).isEqualTo("\n");
    assertThat(style.getIndent()).isEqualTo("\t");
    assertThat(style.usesSpaceAfterSeparators()).isTrue();
  }

  @Test
  public void testWithSpaceAfterSeparatorsTrue() {
    FormattingStyle style = FormattingStyle.COMPACT.withSpaceAfterSeparators(true);
    assertThat(style.usesSpaceAfterSeparators()).isTrue();
  }

  @Test
  public void testWithSpaceAfterSeparatorsFalse() {
    FormattingStyle style = FormattingStyle.PRETTY.withSpaceAfterSeparators(false);
    assertThat(style.usesSpaceAfterSeparators()).isFalse();
  }

  @Test
  public void testWithSpaceAfterSeparatorsPreservesOtherFields() {
    FormattingStyle style = FormattingStyle.PRETTY.withSpaceAfterSeparators(false);
    assertThat(style.getNewline()).isEqualTo("\n");
    assertThat(style.getIndent()).isEqualTo("  ");
    assertThat(style.usesSpaceAfterSeparators()).isFalse();
  }

  @Test
  public void testGetNewline() {
    FormattingStyle style = FormattingStyle.COMPACT.withNewline("\r\n");
    assertThat(style.getNewline()).isEqualTo("\r\n");
  }

  @Test
  public void testGetIndent() {
    FormattingStyle style = FormattingStyle.COMPACT.withIndent("   ");
    assertThat(style.getIndent()).isEqualTo("   ");
  }

  @Test
  public void testUsesSpaceAfterSeparators() {
    assertThat(FormattingStyle.COMPACT.usesSpaceAfterSeparators()).isFalse();
    assertThat(FormattingStyle.PRETTY.usesSpaceAfterSeparators()).isTrue();
  }
}
