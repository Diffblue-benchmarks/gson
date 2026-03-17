/*
 * Copyright (C) 2024 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.gson.internal;

import static com.google.common.truth.Truth.assertThat;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import org.junit.Test;

public class GsonTypesGenericArrayTypeImplTest {

  @Test
  public void testArrayOfCreatesGenericArrayType() {
    GenericArrayType arrayType = GsonTypes.arrayOf(String.class);

    assertThat(arrayType).isNotNull();
    assertThat(arrayType.getGenericComponentType()).isEqualTo(String.class);
  }

  @Test(expected = NullPointerException.class)
  public void testArrayOfWithNullComponentType() {
    GsonTypes.arrayOf(null);
  }

  @Test
  public void testGetGenericComponentTypeWithSimpleClass() {
    GenericArrayType arrayType = GsonTypes.arrayOf(Integer.class);

    Type componentType = arrayType.getGenericComponentType();

    assertThat(componentType).isEqualTo(Integer.class);
  }

  @Test
  public void testGetGenericComponentTypeWithParameterizedType() {
    ParameterizedType listOfString =
        GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    GenericArrayType arrayType = GsonTypes.arrayOf(listOfString);

    Type componentType = arrayType.getGenericComponentType();

    assertThat(componentType).isInstanceOf(ParameterizedType.class);
    ParameterizedType paramType = (ParameterizedType) componentType;
    assertThat(paramType.getRawType()).isEqualTo(List.class);
  }

  @Test
  public void testEqualsWithSameComponentType() {
    GenericArrayType arrayType1 = GsonTypes.arrayOf(String.class);
    GenericArrayType arrayType2 = GsonTypes.arrayOf(String.class);

    assertThat(arrayType1.equals(arrayType2)).isTrue();
  }

  @Test
  public void testEqualsWithDifferentComponentType() {
    GenericArrayType arrayType1 = GsonTypes.arrayOf(String.class);
    GenericArrayType arrayType2 = GsonTypes.arrayOf(Integer.class);

    assertThat(arrayType1.equals(arrayType2)).isFalse();
  }

  @Test
  public void testEqualsWithNonGenericArrayType() {
    GenericArrayType arrayType = GsonTypes.arrayOf(String.class);

    assertThat(arrayType.equals("not an array type")).isFalse();
  }

  @Test
  public void testEqualsWithSameInstance() {
    GenericArrayType arrayType = GsonTypes.arrayOf(String.class);

    assertThat(arrayType.equals(arrayType)).isTrue();
  }

  @Test
  public void testHashCodeConsistency() {
    GenericArrayType arrayType1 = GsonTypes.arrayOf(String.class);
    GenericArrayType arrayType2 = GsonTypes.arrayOf(String.class);

    assertThat(arrayType1.hashCode()).isEqualTo(arrayType2.hashCode());
  }

  @Test
  public void testHashCodeDifferentForDifferentTypes() {
    GenericArrayType arrayType1 = GsonTypes.arrayOf(String.class);
    GenericArrayType arrayType2 = GsonTypes.arrayOf(Integer.class);

    assertThat(arrayType1.hashCode()).isNotEqualTo(arrayType2.hashCode());
  }

  @Test
  public void testToStringWithSimpleClass() {
    GenericArrayType arrayType = GsonTypes.arrayOf(String.class);

    String result = arrayType.toString();

    assertThat(result).isEqualTo("java.lang.String[]");
  }

  @Test
  public void testToStringWithParameterizedType() {
    ParameterizedType listOfString =
        GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    GenericArrayType arrayType = GsonTypes.arrayOf(listOfString);

    String result = arrayType.toString();

    assertThat(result).contains("List");
    assertThat(result).contains("String");
    assertThat(result).endsWith("[]");
  }

  @Test
  public void testToStringWithNestedArray() {
    GenericArrayType innerArray = GsonTypes.arrayOf(String.class);
    GenericArrayType outerArray = GsonTypes.arrayOf(innerArray);

    String result = outerArray.toString();

    assertThat(result).isEqualTo("java.lang.String[][]");
  }

  @Test
  public void testEqualsWithParameterizedComponentTypes() {
    ParameterizedType listOfString1 =
        GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    ParameterizedType listOfString2 =
        GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    GenericArrayType arrayType1 = GsonTypes.arrayOf(listOfString1);
    GenericArrayType arrayType2 = GsonTypes.arrayOf(listOfString2);

    assertThat(arrayType1.equals(arrayType2)).isTrue();
  }
}
