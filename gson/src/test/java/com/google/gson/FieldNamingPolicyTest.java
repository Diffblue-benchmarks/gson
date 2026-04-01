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

public final class FieldNamingPolicyTest {

  @Test
  public void testSeparateCamelCaseSimpleWord() {
    assertThat(FieldNamingPolicy.separateCamelCase("myField", '_')).isEqualTo("my_Field");
  }

  @Test
  public void testSeparateCamelCaseMultipleWords() {
    assertThat(FieldNamingPolicy.separateCamelCase("myFieldName", '_'))
        .isEqualTo("my_Field_Name");
  }

  @Test
  public void testSeparateCamelCaseSingleWord() {
    assertThat(FieldNamingPolicy.separateCamelCase("field", '_')).isEqualTo("field");
  }

  @Test
  public void testSeparateCamelCaseAlreadyUpperCase() {
    assertThat(FieldNamingPolicy.separateCamelCase("Field", '_')).isEqualTo("Field");
  }

  @Test
  public void testSeparateCamelCaseWithDotSeparator() {
    assertThat(FieldNamingPolicy.separateCamelCase("myField", '.')).isEqualTo("my.Field");
  }

  @Test
  public void testSeparateCamelCaseEmptyString() {
    assertThat(FieldNamingPolicy.separateCamelCase("", '_')).isEqualTo("");
  }

  @Test
  public void testUpperCaseFirstLetterLowerCase() {
    assertThat(FieldNamingPolicy.upperCaseFirstLetter("myField")).isEqualTo("MyField");
  }

  @Test
  public void testUpperCaseFirstLetterAlreadyUpperCase() {
    assertThat(FieldNamingPolicy.upperCaseFirstLetter("MyField")).isEqualTo("MyField");
  }

  @Test
  public void testUpperCaseFirstLetterEmptyString() {
    assertThat(FieldNamingPolicy.upperCaseFirstLetter("")).isEqualTo("");
  }

  @Test
  public void testUpperCaseFirstLetterNonLetterPrefix() {
    assertThat(FieldNamingPolicy.upperCaseFirstLetter("_myField")).isEqualTo("_MyField");
  }

  @Test
  public void testUpperCaseFirstLetterAllNonLetters() {
    assertThat(FieldNamingPolicy.upperCaseFirstLetter("123")).isEqualTo("123");
  }

  @Test
  public void testUpperCaseFirstLetterSingleLowerCase() {
    assertThat(FieldNamingPolicy.upperCaseFirstLetter("a")).isEqualTo("A");
  }
}
