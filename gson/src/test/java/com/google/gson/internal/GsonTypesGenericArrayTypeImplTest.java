/*
 * Copyright (C) 2024 Google Inc.
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

package com.google.gson.internal;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import org.junit.Test;

public final class GsonTypesGenericArrayTypeImplTest {

  @Test
  public void testGetGenericComponentType() {
    GenericArrayType arrayType = GsonTypes.arrayOf(String.class);

    assertThat(arrayType.getGenericComponentType()).isEqualTo(String.class);
  }

  @Test
  public void testGetGenericComponentTypeParameterized() {
    ParameterizedType listOfString = (ParameterizedType) new TypeToken<List<String>>() {}.getType();
    GenericArrayType arrayType = GsonTypes.arrayOf(listOfString);

    assertThat(arrayType.getGenericComponentType()).isEqualTo(listOfString);
  }

  @Test
  public void testEqualsWithSameComponentType() {
    GenericArrayType a = GsonTypes.arrayOf(String.class);
    GenericArrayType b = GsonTypes.arrayOf(String.class);

    assertThat(a).isEqualTo(b);
  }

  @Test
  public void testEqualsWithDifferentComponentType() {
    GenericArrayType a = GsonTypes.arrayOf(String.class);
    GenericArrayType b = GsonTypes.arrayOf(Integer.class);

    assertThat(a).isNotEqualTo(b);
  }

  @Test
  public void testEqualsWithNonGenericArrayType() {
    GenericArrayType a = GsonTypes.arrayOf(String.class);

    assertThat(a.equals("not a GenericArrayType")).isFalse();
  }

  @Test
  public void testHashCode() {
    GenericArrayType a = GsonTypes.arrayOf(String.class);
    GenericArrayType b = GsonTypes.arrayOf(String.class);

    assertThat(a.hashCode()).isEqualTo(b.hashCode());
  }

  @Test
  public void testToString() {
    GenericArrayType arrayType = GsonTypes.arrayOf(String.class);

    assertThat(arrayType.toString()).isEqualTo("java.lang.String[]");
  }

  @Test
  public void testToStringParameterized() {
    Type listOfString = new TypeToken<List<String>>() {}.getType();
    GenericArrayType arrayType = GsonTypes.arrayOf(listOfString);

    assertThat(arrayType.toString()).isEqualTo("java.util.List<java.lang.String>[]");
  }

  @Test
  public void testConstructorNullThrowsNullPointerException() {
    assertThrows(NullPointerException.class, () -> GsonTypes.arrayOf(null));
  }
}
