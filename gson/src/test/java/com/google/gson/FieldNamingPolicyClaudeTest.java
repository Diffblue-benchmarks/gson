/*
 * Copyright (C) 2009 Google Inc.
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

import java.lang.reflect.Field;
import org.junit.Test;

/**
 * Tests for {@link FieldNamingPolicy}.
 *
 * @author Claude
 */
public class FieldNamingPolicyClaudeTest {

  /** Test class with various field names for testing naming policies. */
  @SuppressWarnings("unused")
  private static class TestClass {
    String simpleField;
    String someFieldName;
    String _someFieldName;
    String aStringField;
    String aURL;
    String URLConnection;
    String A;
    String AB;
    String ABC;
    String a;
    String ab;
    String _a;
    String _single;
    String __test;
    String a123;
    String a123B;
    String HTMLParser;
    String simpleXMLParser;
    String IOError;
  }

  // ==========================================================================
  // values() tests
  // ==========================================================================

  @Test
  public void testValues_returnsAllEnumConstants() {
    FieldNamingPolicy[] values = FieldNamingPolicy.values();
    assertThat(values).hasLength(7);
  }

  @Test
  public void testValues_containsIdentity() {
    FieldNamingPolicy[] values = FieldNamingPolicy.values();
    assertThat(values).asList().contains(FieldNamingPolicy.IDENTITY);
  }

  @Test
  public void testValues_containsUpperCamelCase() {
    FieldNamingPolicy[] values = FieldNamingPolicy.values();
    assertThat(values).asList().contains(FieldNamingPolicy.UPPER_CAMEL_CASE);
  }

  @Test
  public void testValues_containsUpperCamelCaseWithSpaces() {
    FieldNamingPolicy[] values = FieldNamingPolicy.values();
    assertThat(values).asList().contains(FieldNamingPolicy.UPPER_CAMEL_CASE_WITH_SPACES);
  }

  @Test
  public void testValues_containsUpperCaseWithUnderscores() {
    FieldNamingPolicy[] values = FieldNamingPolicy.values();
    assertThat(values).asList().contains(FieldNamingPolicy.UPPER_CASE_WITH_UNDERSCORES);
  }

  @Test
  public void testValues_containsLowerCaseWithUnderscores() {
    FieldNamingPolicy[] values = FieldNamingPolicy.values();
    assertThat(values).asList().contains(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
  }

  @Test
  public void testValues_containsLowerCaseWithDashes() {
    FieldNamingPolicy[] values = FieldNamingPolicy.values();
    assertThat(values).asList().contains(FieldNamingPolicy.LOWER_CASE_WITH_DASHES);
  }

  @Test
  public void testValues_containsLowerCaseWithDots() {
    FieldNamingPolicy[] values = FieldNamingPolicy.values();
    assertThat(values).asList().contains(FieldNamingPolicy.LOWER_CASE_WITH_DOTS);
  }

  @Test
  public void testValues_ordinalSequence() {
    FieldNamingPolicy[] values = FieldNamingPolicy.values();
    for (int i = 0; i < values.length; i++) {
      assertThat(values[i].ordinal()).isEqualTo(i);
    }
  }

  @Test
  public void testValues_returnsNewArrayEachTime() {
    FieldNamingPolicy[] values1 = FieldNamingPolicy.values();
    FieldNamingPolicy[] values2 = FieldNamingPolicy.values();
    assertThat(values1).isNotSameInstanceAs(values2);
    assertThat(values1).isEqualTo(values2);
  }

  // ==========================================================================
  // valueOf() tests
  // ==========================================================================

  @Test
  public void testValueOf_identity_returnsIdentity() {
    assertThat(FieldNamingPolicy.valueOf("IDENTITY")).isEqualTo(FieldNamingPolicy.IDENTITY);
  }

  @Test
  public void testValueOf_upperCamelCase_returnsUpperCamelCase() {
    assertThat(FieldNamingPolicy.valueOf("UPPER_CAMEL_CASE"))
        .isEqualTo(FieldNamingPolicy.UPPER_CAMEL_CASE);
  }

  @Test
  public void testValueOf_upperCamelCaseWithSpaces_returnsUpperCamelCaseWithSpaces() {
    assertThat(FieldNamingPolicy.valueOf("UPPER_CAMEL_CASE_WITH_SPACES"))
        .isEqualTo(FieldNamingPolicy.UPPER_CAMEL_CASE_WITH_SPACES);
  }

  @Test
  public void testValueOf_upperCaseWithUnderscores_returnsUpperCaseWithUnderscores() {
    assertThat(FieldNamingPolicy.valueOf("UPPER_CASE_WITH_UNDERSCORES"))
        .isEqualTo(FieldNamingPolicy.UPPER_CASE_WITH_UNDERSCORES);
  }

  @Test
  public void testValueOf_lowerCaseWithUnderscores_returnsLowerCaseWithUnderscores() {
    assertThat(FieldNamingPolicy.valueOf("LOWER_CASE_WITH_UNDERSCORES"))
        .isEqualTo(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
  }

  @Test
  public void testValueOf_lowerCaseWithDashes_returnsLowerCaseWithDashes() {
    assertThat(FieldNamingPolicy.valueOf("LOWER_CASE_WITH_DASHES"))
        .isEqualTo(FieldNamingPolicy.LOWER_CASE_WITH_DASHES);
  }

  @Test
  public void testValueOf_lowerCaseWithDots_returnsLowerCaseWithDots() {
    assertThat(FieldNamingPolicy.valueOf("LOWER_CASE_WITH_DOTS"))
        .isEqualTo(FieldNamingPolicy.LOWER_CASE_WITH_DOTS);
  }

  @Test
  public void testValueOf_invalidName_throwsIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class, () -> FieldNamingPolicy.valueOf("INVALID_NAME"));
  }

  @Test
  public void testValueOf_lowerCaseName_throwsIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class, () -> FieldNamingPolicy.valueOf("identity"));
  }

  @Test
  public void testValueOf_nullName_throwsNullPointerException() {
    assertThrows(NullPointerException.class, () -> FieldNamingPolicy.valueOf(null));
  }

  @Test
  public void testValueOf_emptyString_throwsIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class, () -> FieldNamingPolicy.valueOf(""));
  }

  // ==========================================================================
  // IDENTITY translateName tests
  // ==========================================================================

  @Test
  public void testIdentity_simpleField_returnsUnchanged() throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("simpleField");
    assertThat(FieldNamingPolicy.IDENTITY.translateName(field)).isEqualTo("simpleField");
  }

  @Test
  public void testIdentity_someFieldName_returnsUnchanged() throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("someFieldName");
    assertThat(FieldNamingPolicy.IDENTITY.translateName(field)).isEqualTo("someFieldName");
  }

  @Test
  public void testIdentity_underscorePrefixedField_returnsUnchanged() throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("_someFieldName");
    assertThat(FieldNamingPolicy.IDENTITY.translateName(field)).isEqualTo("_someFieldName");
  }

  // ==========================================================================
  // UPPER_CAMEL_CASE translateName tests (tests upperCaseFirstLetter indirectly)
  // ==========================================================================

  @Test
  public void testUpperCamelCase_simpleField_capitalizeFirstLetter() throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("simpleField");
    assertThat(FieldNamingPolicy.UPPER_CAMEL_CASE.translateName(field)).isEqualTo("SimpleField");
  }

  @Test
  public void testUpperCamelCase_someFieldName_capitalizeFirstLetter() throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("someFieldName");
    assertThat(FieldNamingPolicy.UPPER_CAMEL_CASE.translateName(field)).isEqualTo("SomeFieldName");
  }

  @Test
  public void testUpperCamelCase_underscorePrefixedField_preservesUnderscore()
      throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("_someFieldName");
    assertThat(FieldNamingPolicy.UPPER_CAMEL_CASE.translateName(field)).isEqualTo("_SomeFieldName");
  }

  @Test
  public void testUpperCamelCase_singleUppercaseLetter_unchanged() throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("A");
    assertThat(FieldNamingPolicy.UPPER_CAMEL_CASE.translateName(field)).isEqualTo("A");
  }

  @Test
  public void testUpperCamelCase_singleLowercaseLetter_capitalized() throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("a");
    assertThat(FieldNamingPolicy.UPPER_CAMEL_CASE.translateName(field)).isEqualTo("A");
  }

  @Test
  public void testUpperCamelCase_twoLowercaseLetters_capitalizeFirst() throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("ab");
    assertThat(FieldNamingPolicy.UPPER_CAMEL_CASE.translateName(field)).isEqualTo("Ab");
  }

  @Test
  public void testUpperCamelCase_underscorePrefix_a_preservesUnderscoreAndCapitalizes()
      throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("_a");
    assertThat(FieldNamingPolicy.UPPER_CAMEL_CASE.translateName(field)).isEqualTo("_A");
  }

  @Test
  public void testUpperCamelCase_singleUnderscoreField_unchanged() throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("_single");
    assertThat(FieldNamingPolicy.UPPER_CAMEL_CASE.translateName(field)).isEqualTo("_Single");
  }

  @Test
  public void testUpperCamelCase_doubleUnderscoreTest_preservesUnderscoresAndCapitalizes()
      throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("__test");
    assertThat(FieldNamingPolicy.UPPER_CAMEL_CASE.translateName(field)).isEqualTo("__Test");
  }

  @Test
  public void testUpperCamelCase_fieldWithNumbers_capitalizeFirstLetter()
      throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("a123");
    assertThat(FieldNamingPolicy.UPPER_CAMEL_CASE.translateName(field)).isEqualTo("A123");
  }

  @Test
  public void testUpperCamelCase_allCapsField_unchanged() throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("AB");
    assertThat(FieldNamingPolicy.UPPER_CAMEL_CASE.translateName(field)).isEqualTo("AB");
  }

  @Test
  public void testUpperCamelCase_alreadyCapitalized_unchanged() throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("URLConnection");
    assertThat(FieldNamingPolicy.UPPER_CAMEL_CASE.translateName(field)).isEqualTo("URLConnection");
  }

  // ==========================================================================
  // UPPER_CAMEL_CASE_WITH_SPACES translateName tests
  // (tests both separateCamelCase and upperCaseFirstLetter indirectly)
  // ==========================================================================

  @Test
  public void testUpperCamelCaseWithSpaces_someFieldName_separatesAndCapitalizes()
      throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("someFieldName");
    assertThat(FieldNamingPolicy.UPPER_CAMEL_CASE_WITH_SPACES.translateName(field))
        .isEqualTo("Some Field Name");
  }

  @Test
  public void testUpperCamelCaseWithSpaces_underscorePrefixedField_preservesUnderscore()
      throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("_someFieldName");
    assertThat(FieldNamingPolicy.UPPER_CAMEL_CASE_WITH_SPACES.translateName(field))
        .isEqualTo("_Some Field Name");
  }

  @Test
  public void testUpperCamelCaseWithSpaces_simpleField_capitalizesAndSeparates()
      throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("simpleField");
    assertThat(FieldNamingPolicy.UPPER_CAMEL_CASE_WITH_SPACES.translateName(field))
        .isEqualTo("Simple Field");
  }

  @Test
  public void testUpperCamelCaseWithSpaces_aURL_separatesEachUppercase()
      throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("aURL");
    assertThat(FieldNamingPolicy.UPPER_CAMEL_CASE_WITH_SPACES.translateName(field))
        .isEqualTo("A U R L");
  }

  // ==========================================================================
  // UPPER_CASE_WITH_UNDERSCORES translateName tests (tests separateCamelCase indirectly)
  // ==========================================================================

  @Test
  public void testUpperCaseWithUnderscores_someFieldName_separatesAndUppercases()
      throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("someFieldName");
    assertThat(FieldNamingPolicy.UPPER_CASE_WITH_UNDERSCORES.translateName(field))
        .isEqualTo("SOME_FIELD_NAME");
  }

  @Test
  public void testUpperCaseWithUnderscores_underscorePrefixedField_preservesPrefixAndSeparates()
      throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("_someFieldName");
    assertThat(FieldNamingPolicy.UPPER_CASE_WITH_UNDERSCORES.translateName(field))
        .isEqualTo("_SOME_FIELD_NAME");
  }

  @Test
  public void testUpperCaseWithUnderscores_aStringField_separatesAndUppercases()
      throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("aStringField");
    assertThat(FieldNamingPolicy.UPPER_CASE_WITH_UNDERSCORES.translateName(field))
        .isEqualTo("A_STRING_FIELD");
  }

  @Test
  public void testUpperCaseWithUnderscores_aURL_separatesEachUppercase()
      throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("aURL");
    assertThat(FieldNamingPolicy.UPPER_CASE_WITH_UNDERSCORES.translateName(field))
        .isEqualTo("A_U_R_L");
  }

  // ==========================================================================
  // LOWER_CASE_WITH_UNDERSCORES translateName tests (tests separateCamelCase indirectly)
  // ==========================================================================

  @Test
  public void testLowerCaseWithUnderscores_someFieldName_separatesAndLowercases()
      throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("someFieldName");
    assertThat(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES.translateName(field))
        .isEqualTo("some_field_name");
  }

  @Test
  public void testLowerCaseWithUnderscores_underscorePrefixedField_preservesPrefixAndSeparates()
      throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("_someFieldName");
    assertThat(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES.translateName(field))
        .isEqualTo("_some_field_name");
  }

  @Test
  public void testLowerCaseWithUnderscores_aStringField_separatesAndLowercases()
      throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("aStringField");
    assertThat(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES.translateName(field))
        .isEqualTo("a_string_field");
  }

  @Test
  public void testLowerCaseWithUnderscores_aURL_separatesEachUppercase()
      throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("aURL");
    assertThat(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES.translateName(field))
        .isEqualTo("a_u_r_l");
  }

  // ==========================================================================
  // LOWER_CASE_WITH_DASHES translateName tests (tests separateCamelCase indirectly)
  // ==========================================================================

  @Test
  public void testLowerCaseWithDashes_someFieldName_separatesWithDashes()
      throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("someFieldName");
    assertThat(FieldNamingPolicy.LOWER_CASE_WITH_DASHES.translateName(field))
        .isEqualTo("some-field-name");
  }

  @Test
  public void testLowerCaseWithDashes_underscorePrefixedField_preservesPrefixAndSeparates()
      throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("_someFieldName");
    assertThat(FieldNamingPolicy.LOWER_CASE_WITH_DASHES.translateName(field))
        .isEqualTo("_some-field-name");
  }

  @Test
  public void testLowerCaseWithDashes_aStringField_separatesWithDashes()
      throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("aStringField");
    assertThat(FieldNamingPolicy.LOWER_CASE_WITH_DASHES.translateName(field))
        .isEqualTo("a-string-field");
  }

  @Test
  public void testLowerCaseWithDashes_aURL_separatesEachUppercase() throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("aURL");
    assertThat(FieldNamingPolicy.LOWER_CASE_WITH_DASHES.translateName(field)).isEqualTo("a-u-r-l");
  }

  // ==========================================================================
  // LOWER_CASE_WITH_DOTS translateName tests (tests separateCamelCase indirectly)
  // ==========================================================================

  @Test
  public void testLowerCaseWithDots_someFieldName_separatesWithDots() throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("someFieldName");
    assertThat(FieldNamingPolicy.LOWER_CASE_WITH_DOTS.translateName(field))
        .isEqualTo("some.field.name");
  }

  @Test
  public void testLowerCaseWithDots_underscorePrefixedField_preservesPrefixAndSeparates()
      throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("_someFieldName");
    assertThat(FieldNamingPolicy.LOWER_CASE_WITH_DOTS.translateName(field))
        .isEqualTo("_some.field.name");
  }

  @Test
  public void testLowerCaseWithDots_aStringField_separatesWithDots() throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("aStringField");
    assertThat(FieldNamingPolicy.LOWER_CASE_WITH_DOTS.translateName(field))
        .isEqualTo("a.string.field");
  }

  @Test
  public void testLowerCaseWithDots_aURL_separatesEachUppercase() throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("aURL");
    assertThat(FieldNamingPolicy.LOWER_CASE_WITH_DOTS.translateName(field)).isEqualTo("a.u.r.l");
  }

  // ==========================================================================
  // Additional separateCamelCase coverage tests (via translateName)
  // ==========================================================================

  @Test
  public void testSeparateCamelCase_singleLetter_noSeparation() throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("a");
    assertThat(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES.translateName(field)).isEqualTo("a");
  }

  @Test
  public void testSeparateCamelCase_allUppercase_separatesEachLetter() throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("ABC");
    assertThat(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES.translateName(field))
        .isEqualTo("a_b_c");
  }

  @Test
  public void testSeparateCamelCase_acronymAtEnd_separatesEachLetter() throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("HTMLParser");
    assertThat(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES.translateName(field))
        .isEqualTo("h_t_m_l_parser");
  }

  @Test
  public void testSeparateCamelCase_acronymInMiddle_separatesCorrectly()
      throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("simpleXMLParser");
    assertThat(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES.translateName(field))
        .isEqualTo("simple_x_m_l_parser");
  }

  @Test
  public void testSeparateCamelCase_numbersAfterLetter_noSeparation() throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("a123");
    assertThat(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES.translateName(field)).isEqualTo("a123");
  }

  @Test
  public void testSeparateCamelCase_numbersFollowedByUppercase_separatesBeforeUppercase()
      throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("a123B");
    assertThat(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES.translateName(field))
        .isEqualTo("a123_b");
  }

  @Test
  public void testSeparateCamelCase_IOError_separatesEachLetter() throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("IOError");
    assertThat(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES.translateName(field))
        .isEqualTo("i_o_error");
  }

  // ==========================================================================
  // Additional upperCaseFirstLetter coverage tests (via translateName)
  // ==========================================================================

  @Test
  public void testUpperCaseFirstLetter_alreadyUppercase_unchanged() throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("ABC");
    assertThat(FieldNamingPolicy.UPPER_CAMEL_CASE.translateName(field)).isEqualTo("ABC");
  }

  @Test
  public void testUpperCaseFirstLetter_underscorePrefixed_capitalizeAfterPrefix()
      throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("_single");
    // Should capitalize the first letter after the underscore
    assertThat(FieldNamingPolicy.UPPER_CAMEL_CASE.translateName(field)).isEqualTo("_Single");
  }

  @Test
  public void testUpperCaseFirstLetter_doubleUnderscore_capitalizesAfterUnderscores()
      throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("__test");
    // Should capitalize the 't' after the underscores
    assertThat(FieldNamingPolicy.UPPER_CAMEL_CASE.translateName(field)).isEqualTo("__Test");
  }

  // ==========================================================================
  // FieldNamingStrategy interface tests
  // ==========================================================================

  @Test
  public void testIsFieldNamingStrategy() {
    for (FieldNamingPolicy policy : FieldNamingPolicy.values()) {
      assertThat(policy).isInstanceOf(FieldNamingStrategy.class);
    }
  }

  // ==========================================================================
  // Enum property tests
  // ==========================================================================

  @Test
  public void testName_identity_returnsCorrectName() {
    assertThat(FieldNamingPolicy.IDENTITY.name()).isEqualTo("IDENTITY");
  }

  @Test
  public void testName_lowerCaseWithDots_returnsCorrectName() {
    assertThat(FieldNamingPolicy.LOWER_CASE_WITH_DOTS.name()).isEqualTo("LOWER_CASE_WITH_DOTS");
  }

  @Test
  public void testOrdinal_identity_isFirst() {
    assertThat(FieldNamingPolicy.IDENTITY.ordinal()).isEqualTo(0);
  }

  @Test
  public void testOrdinal_lowerCaseWithDots_isLast() {
    assertThat(FieldNamingPolicy.LOWER_CASE_WITH_DOTS.ordinal()).isEqualTo(6);
  }

  // ==========================================================================
  // Edge case tests for empty string handling
  // ==========================================================================

  /** Test class with empty string field name (not possible in Java, so skip). */
  // Note: Java does not allow empty field names, so we cannot directly test this edge case.
}
