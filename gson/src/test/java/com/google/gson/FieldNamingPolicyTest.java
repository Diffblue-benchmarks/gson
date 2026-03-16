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

package com.google.gson;

import static com.google.common.truth.Truth.assertThat;

import java.lang.reflect.Field;
import org.junit.Test;

public class FieldNamingPolicyTest {

  @Test
  public void testIdentity() throws Exception {
    Field field = TestClass.class.getDeclaredField("someFieldName");
    assertThat(FieldNamingPolicy.IDENTITY.translateName(field)).isEqualTo("someFieldName");

    field = TestClass.class.getDeclaredField("_someFieldName");
    assertThat(FieldNamingPolicy.IDENTITY.translateName(field)).isEqualTo("_someFieldName");

    field = TestClass.class.getDeclaredField("aStringField");
    assertThat(FieldNamingPolicy.IDENTITY.translateName(field)).isEqualTo("aStringField");
  }

  @Test
  public void testUpperCamelCase() throws Exception {
    Field field = TestClass.class.getDeclaredField("someFieldName");
    assertThat(FieldNamingPolicy.UPPER_CAMEL_CASE.translateName(field)).isEqualTo("SomeFieldName");

    field = TestClass.class.getDeclaredField("_someFieldName");
    assertThat(FieldNamingPolicy.UPPER_CAMEL_CASE.translateName(field))
        .isEqualTo("_SomeFieldName");

    field = TestClass.class.getDeclaredField("aStringField");
    assertThat(FieldNamingPolicy.UPPER_CAMEL_CASE.translateName(field)).isEqualTo("AStringField");

    field = TestClass.class.getDeclaredField("aURL");
    assertThat(FieldNamingPolicy.UPPER_CAMEL_CASE.translateName(field)).isEqualTo("AURL");
  }

  @Test
  public void testUpperCamelCaseWithSpaces() throws Exception {
    Field field = TestClass.class.getDeclaredField("someFieldName");
    assertThat(FieldNamingPolicy.UPPER_CAMEL_CASE_WITH_SPACES.translateName(field))
        .isEqualTo("Some Field Name");

    field = TestClass.class.getDeclaredField("_someFieldName");
    assertThat(FieldNamingPolicy.UPPER_CAMEL_CASE_WITH_SPACES.translateName(field))
        .isEqualTo("_Some Field Name");

    field = TestClass.class.getDeclaredField("aStringField");
    assertThat(FieldNamingPolicy.UPPER_CAMEL_CASE_WITH_SPACES.translateName(field))
        .isEqualTo("A String Field");
  }

  @Test
  public void testUpperCaseWithUnderscores() throws Exception {
    Field field = TestClass.class.getDeclaredField("someFieldName");
    assertThat(FieldNamingPolicy.UPPER_CASE_WITH_UNDERSCORES.translateName(field))
        .isEqualTo("SOME_FIELD_NAME");

    field = TestClass.class.getDeclaredField("_someFieldName");
    assertThat(FieldNamingPolicy.UPPER_CASE_WITH_UNDERSCORES.translateName(field))
        .isEqualTo("_SOME_FIELD_NAME");

    field = TestClass.class.getDeclaredField("aStringField");
    assertThat(FieldNamingPolicy.UPPER_CASE_WITH_UNDERSCORES.translateName(field))
        .isEqualTo("A_STRING_FIELD");

    field = TestClass.class.getDeclaredField("aURL");
    assertThat(FieldNamingPolicy.UPPER_CASE_WITH_UNDERSCORES.translateName(field))
        .isEqualTo("A_U_R_L");
  }

  @Test
  public void testLowerCaseWithUnderscores() throws Exception {
    Field field = TestClass.class.getDeclaredField("someFieldName");
    assertThat(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES.translateName(field))
        .isEqualTo("some_field_name");

    field = TestClass.class.getDeclaredField("_someFieldName");
    assertThat(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES.translateName(field))
        .isEqualTo("_some_field_name");

    field = TestClass.class.getDeclaredField("aStringField");
    assertThat(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES.translateName(field))
        .isEqualTo("a_string_field");

    field = TestClass.class.getDeclaredField("aURL");
    assertThat(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES.translateName(field))
        .isEqualTo("a_u_r_l");
  }

  @Test
  public void testLowerCaseWithDashes() throws Exception {
    Field field = TestClass.class.getDeclaredField("someFieldName");
    assertThat(FieldNamingPolicy.LOWER_CASE_WITH_DASHES.translateName(field))
        .isEqualTo("some-field-name");

    field = TestClass.class.getDeclaredField("_someFieldName");
    assertThat(FieldNamingPolicy.LOWER_CASE_WITH_DASHES.translateName(field))
        .isEqualTo("_some-field-name");

    field = TestClass.class.getDeclaredField("aStringField");
    assertThat(FieldNamingPolicy.LOWER_CASE_WITH_DASHES.translateName(field))
        .isEqualTo("a-string-field");

    field = TestClass.class.getDeclaredField("aURL");
    assertThat(FieldNamingPolicy.LOWER_CASE_WITH_DASHES.translateName(field)).isEqualTo("a-u-r-l");
  }

  @Test
  public void testLowerCaseWithDots() throws Exception {
    Field field = TestClass.class.getDeclaredField("someFieldName");
    assertThat(FieldNamingPolicy.LOWER_CASE_WITH_DOTS.translateName(field))
        .isEqualTo("some.field.name");

    field = TestClass.class.getDeclaredField("_someFieldName");
    assertThat(FieldNamingPolicy.LOWER_CASE_WITH_DOTS.translateName(field))
        .isEqualTo("_some.field.name");

    field = TestClass.class.getDeclaredField("aStringField");
    assertThat(FieldNamingPolicy.LOWER_CASE_WITH_DOTS.translateName(field))
        .isEqualTo("a.string.field");

    field = TestClass.class.getDeclaredField("aURL");
    assertThat(FieldNamingPolicy.LOWER_CASE_WITH_DOTS.translateName(field)).isEqualTo("a.u.r.l");
  }

  @Test
  public void testSeparateCamelCase_withUnderscore() {
    assertThat(FieldNamingPolicy.separateCamelCase("someFieldName", '_'))
        .isEqualTo("some_Field_Name");
    assertThat(FieldNamingPolicy.separateCamelCase("aURL", '_')).isEqualTo("a_U_R_L");
    assertThat(FieldNamingPolicy.separateCamelCase("_someFieldName", '_'))
        .isEqualTo("_some_Field_Name");
    assertThat(FieldNamingPolicy.separateCamelCase("lowercase", '_')).isEqualTo("lowercase");
    assertThat(FieldNamingPolicy.separateCamelCase("ALLUPPERCASE", '_'))
        .isEqualTo("A_L_L_U_P_P_E_R_C_A_S_E");
  }

  @Test
  public void testSeparateCamelCase_withSpace() {
    assertThat(FieldNamingPolicy.separateCamelCase("someFieldName", ' '))
        .isEqualTo("some Field Name");
    assertThat(FieldNamingPolicy.separateCamelCase("aURL", ' ')).isEqualTo("a U R L");
  }

  @Test
  public void testUpperCaseFirstLetter_alreadyUpper() {
    assertThat(FieldNamingPolicy.upperCaseFirstLetter("SomeField")).isEqualTo("SomeField");
    assertThat(FieldNamingPolicy.upperCaseFirstLetter("ALLCAPS")).isEqualTo("ALLCAPS");
  }

  @Test
  public void testUpperCaseFirstLetter_needsUpper() {
    assertThat(FieldNamingPolicy.upperCaseFirstLetter("someField")).isEqualTo("SomeField");
    assertThat(FieldNamingPolicy.upperCaseFirstLetter("a")).isEqualTo("A");
  }

  @Test
  public void testUpperCaseFirstLetter_withLeadingUnderscore() {
    assertThat(FieldNamingPolicy.upperCaseFirstLetter("_someField")).isEqualTo("_SomeField");
    assertThat(FieldNamingPolicy.upperCaseFirstLetter("__field")).isEqualTo("__Field");
  }

  @Test
  public void testUpperCaseFirstLetter_noLetters() {
    assertThat(FieldNamingPolicy.upperCaseFirstLetter("123")).isEqualTo("123");
    assertThat(FieldNamingPolicy.upperCaseFirstLetter("_")).isEqualTo("_");
    assertThat(FieldNamingPolicy.upperCaseFirstLetter("")).isEqualTo("");
  }

  @Test
  public void testUpperCaseFirstLetter_withNumbers() {
    assertThat(FieldNamingPolicy.upperCaseFirstLetter("123field")).isEqualTo("123Field");
  }

  @SuppressWarnings("IdentifierName")
  private static class TestClass {
    @SuppressWarnings("unused")
    String someFieldName;

    @SuppressWarnings("unused")
    String _someFieldName;

    @SuppressWarnings("unused")
    String aStringField;

    @SuppressWarnings("unused")
    String aURL;
  }
}
