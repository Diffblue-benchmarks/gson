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

import org.junit.Test;

public class FormattingStyleTest {

  @Test
  public void testWithNewline() {
    FormattingStyle style = FormattingStyle.COMPACT.withNewline("\n");
    assertThat(style.getNewline()).isEqualTo("\n");
    assertThat(style.getIndent()).isEqualTo("");
    assertThat(style.usesSpaceAfterSeparators()).isFalse();
  }

  @Test
  public void testWithNewlineCarriageReturn() {
    FormattingStyle style = FormattingStyle.COMPACT.withNewline("\r\n");
    assertThat(style.getNewline()).isEqualTo("\r\n");
  }

  @Test
  public void testWithNewlineInvalidCharacters() {
    try {
      FormattingStyle.COMPACT.withNewline("abc");
      throw new AssertionError("Expected IllegalArgumentException");
    } catch (IllegalArgumentException expected) {
      assertThat(expected.getMessage()).contains("Only combinations of \\n and \\r are allowed in newline");
    }
  }

  @Test
  public void testWithNewlineNull() {
    try {
      FormattingStyle.COMPACT.withNewline(null);
      throw new AssertionError("Expected NullPointerException");
    } catch (NullPointerException expected) {
      assertThat(expected.getMessage()).contains("newline == null");
    }
  }

  @Test
  public void testWithIndent() {
    FormattingStyle style = FormattingStyle.COMPACT.withIndent("  ");
    assertThat(style.getIndent()).isEqualTo("  ");
    assertThat(style.getNewline()).isEqualTo("");
    assertThat(style.usesSpaceAfterSeparators()).isFalse();
  }

  @Test
  public void testWithIndentTabs() {
    FormattingStyle style = FormattingStyle.COMPACT.withIndent("\t\t");
    assertThat(style.getIndent()).isEqualTo("\t\t");
  }

  @Test
  public void testWithIndentMixed() {
    FormattingStyle style = FormattingStyle.COMPACT.withIndent(" \t ");
    assertThat(style.getIndent()).isEqualTo(" \t ");
  }

  @Test
  public void testWithIndentInvalidCharacters() {
    try {
      FormattingStyle.COMPACT.withIndent("abc");
      throw new AssertionError("Expected IllegalArgumentException");
    } catch (IllegalArgumentException expected) {
      assertThat(expected.getMessage()).contains("Only combinations of spaces and tabs are allowed in indent");
    }
  }

  @Test
  public void testWithIndentNull() {
    try {
      FormattingStyle.COMPACT.withIndent(null);
      throw new AssertionError("Expected NullPointerException");
    } catch (NullPointerException expected) {
      assertThat(expected.getMessage()).contains("indent == null");
    }
  }

  @Test
  public void testWithSpaceAfterSeparators() {
    FormattingStyle style = FormattingStyle.COMPACT.withSpaceAfterSeparators(true);
    assertThat(style.usesSpaceAfterSeparators()).isTrue();
    assertThat(style.getNewline()).isEqualTo("");
    assertThat(style.getIndent()).isEqualTo("");
  }

  @Test
  public void testWithSpaceAfterSeparatorsFalse() {
    FormattingStyle style = FormattingStyle.PRETTY.withSpaceAfterSeparators(false);
    assertThat(style.usesSpaceAfterSeparators()).isFalse();
  }

  @Test
  public void testGetNewline() {
    assertThat(FormattingStyle.COMPACT.getNewline()).isEqualTo("");
    assertThat(FormattingStyle.PRETTY.getNewline()).isEqualTo("\n");
  }

  @Test
  public void testGetIndent() {
    assertThat(FormattingStyle.COMPACT.getIndent()).isEqualTo("");
    assertThat(FormattingStyle.PRETTY.getIndent()).isEqualTo("  ");
  }

  @Test
  public void testUsesSpaceAfterSeparators() {
    assertThat(FormattingStyle.COMPACT.usesSpaceAfterSeparators()).isFalse();
    assertThat(FormattingStyle.PRETTY.usesSpaceAfterSeparators()).isTrue();
  }

  @Test
  public void testChainedConfiguration() {
    FormattingStyle style = FormattingStyle.COMPACT
        .withNewline("\r\n")
        .withIndent("\t")
        .withSpaceAfterSeparators(true);

    assertThat(style.getNewline()).isEqualTo("\r\n");
    assertThat(style.getIndent()).isEqualTo("\t");
    assertThat(style.usesSpaceAfterSeparators()).isTrue();
  }

  @Test
  public void testImmutability() {
    FormattingStyle original = FormattingStyle.COMPACT;
    FormattingStyle modified = original.withNewline("\n");

    assertThat(original.getNewline()).isEqualTo("");
    assertThat(modified.getNewline()).isEqualTo("\n");
  }
}
