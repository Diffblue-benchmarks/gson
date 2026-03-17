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

import org.junit.Test;

public class FieldNamingPolicyTest {

  @Test
  public void testSeparateCamelCaseWithUnderscore() {
    String result = FieldNamingPolicy.separateCamelCase("someFieldName", '_');

    assertThat(result).isEqualTo("some_Field_Name");
  }

  @Test
  public void testSeparateCamelCaseWithSpace() {
    String result = FieldNamingPolicy.separateCamelCase("someFieldName", ' ');

    assertThat(result).isEqualTo("some Field Name");
  }

  @Test
  public void testSeparateCamelCaseWithDash() {
    String result = FieldNamingPolicy.separateCamelCase("aStringField", '-');

    assertThat(result).isEqualTo("a-String-Field");
  }

  @Test
  public void testSeparateCamelCaseWithConsecutiveUppercase() {
    String result = FieldNamingPolicy.separateCamelCase("aURL", '_');

    assertThat(result).isEqualTo("a_U_R_L");
  }

  @Test
  public void testSeparateCamelCaseEmptyString() {
    String result = FieldNamingPolicy.separateCamelCase("", '_');

    assertThat(result).isEqualTo("");
  }

  @Test
  public void testSeparateCamelCaseStartsWithUppercase() {
    String result = FieldNamingPolicy.separateCamelCase("SomeField", '_');

    assertThat(result).isEqualTo("Some_Field");
  }

  @Test
  public void testSeparateCamelCaseNoUppercase() {
    String result = FieldNamingPolicy.separateCamelCase("lowercase", '_');

    assertThat(result).isEqualTo("lowercase");
  }

  @Test
  public void testUpperCaseFirstLetterAlreadyUppercase() {
    String result = FieldNamingPolicy.upperCaseFirstLetter("SomeField");

    assertThat(result).isEqualTo("SomeField");
  }

  @Test
  public void testUpperCaseFirstLetterLowercaseAtStart() {
    String result = FieldNamingPolicy.upperCaseFirstLetter("someField");

    assertThat(result).isEqualTo("SomeField");
  }

  @Test
  public void testUpperCaseFirstLetterWithLeadingNonLetter() {
    String result = FieldNamingPolicy.upperCaseFirstLetter("_someField");

    assertThat(result).isEqualTo("_SomeField");
  }

  @Test
  public void testUpperCaseFirstLetterNoLetters() {
    String result = FieldNamingPolicy.upperCaseFirstLetter("123");

    assertThat(result).isEqualTo("123");
  }

  @Test
  public void testUpperCaseFirstLetterEmptyString() {
    String result = FieldNamingPolicy.upperCaseFirstLetter("");

    assertThat(result).isEqualTo("");
  }

  @Test
  public void testUpperCaseFirstLetterSingleLowercaseLetter() {
    String result = FieldNamingPolicy.upperCaseFirstLetter("a");

    assertThat(result).isEqualTo("A");
  }

  @Test
  public void testUpperCaseFirstLetterWithMultipleLeadingNonLetters() {
    String result = FieldNamingPolicy.upperCaseFirstLetter("__field");

    assertThat(result).isEqualTo("__Field");
  }
}
