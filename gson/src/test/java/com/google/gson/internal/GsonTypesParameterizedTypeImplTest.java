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
import static org.junit.Assert.assertThrows;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public final class GsonTypesParameterizedTypeImplTest {

  // Non-static inner class: requiresOwnerType returns true for it
  class NonStaticInner<T> {}

  @Test
  public void testConstructorSimple() {
    ParameterizedType type =
        GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    assertThat(type.getRawType()).isEqualTo(List.class);
    assertThat(type.getOwnerType()).isNull();
    assertThat(type.getActualTypeArguments()).asList().containsExactly(String.class);
  }

  @Test
  public void testConstructorRequiresOwnerTypeThrows() {
    assertThrows(
        IllegalArgumentException.class,
        () -> GsonTypes.newParameterizedTypeWithOwner(null, NonStaticInner.class, String.class));
  }

  @Test
  public void testConstructorWithOwnerType() {
    ParameterizedType type =
        GsonTypes.newParameterizedTypeWithOwner(
            Map.class, Map.Entry.class, String.class, Integer.class);
    assertThat(type.getOwnerType()).isEqualTo(Map.class);
    assertThat(type.getRawType()).isEqualTo(Map.Entry.class);
    assertThat(type.getActualTypeArguments())
        .asList()
        .containsExactly(String.class, Integer.class)
        .inOrder();
  }

  @Test
  public void testGetActualTypeArguments() {
    ParameterizedType type =
        GsonTypes.newParameterizedTypeWithOwner(null, Map.class, String.class, Integer.class);
    Type[] args = type.getActualTypeArguments();
    assertThat(args).asList().containsExactly(String.class, Integer.class).inOrder();
  }

  @Test
  public void testGetActualTypeArgumentsReturnsClone() {
    ParameterizedType type =
        GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    Type[] args = type.getActualTypeArguments();
    args[0] = Integer.class;
    assertThat(type.getActualTypeArguments()[0]).isEqualTo(String.class);
  }

  @Test
  public void testGetRawType() {
    ParameterizedType type =
        GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    assertThat(type.getRawType()).isEqualTo(List.class);
  }

  @Test
  public void testGetOwnerTypeNull() {
    ParameterizedType type =
        GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    assertThat(type.getOwnerType()).isNull();
  }

  @Test
  public void testGetOwnerTypeNonNull() {
    ParameterizedType type =
        GsonTypes.newParameterizedTypeWithOwner(
            Map.class, Map.Entry.class, String.class, Integer.class);
    assertThat(type.getOwnerType()).isEqualTo(Map.class);
  }

  @Test
  public void testEquals() {
    ParameterizedType type1 =
        GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    ParameterizedType type2 =
        GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    assertThat(type1).isEqualTo(type2);
  }

  @Test
  public void testEqualsDifferentTypeArgs() {
    ParameterizedType type1 =
        GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    ParameterizedType type2 =
        GsonTypes.newParameterizedTypeWithOwner(null, List.class, Integer.class);
    assertThat(type1).isNotEqualTo(type2);
  }

  @Test
  public void testEqualsNotAParameterizedType() {
    ParameterizedType type =
        GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    assertThat(type).isNotEqualTo("not a type");
  }

  @Test
  public void testHashCode() {
    ParameterizedType type1 =
        GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    ParameterizedType type2 =
        GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    assertThat(type1.hashCode()).isEqualTo(type2.hashCode());
  }

  @Test
  public void testHashCodeWithOwnerType() {
    ParameterizedType type1 =
        GsonTypes.newParameterizedTypeWithOwner(
            Map.class, Map.Entry.class, String.class, Integer.class);
    ParameterizedType type2 =
        GsonTypes.newParameterizedTypeWithOwner(
            Map.class, Map.Entry.class, String.class, Integer.class);
    assertThat(type1.hashCode()).isEqualTo(type2.hashCode());
  }

  @Test
  public void testToStringNoTypeArguments() {
    ParameterizedType type = GsonTypes.newParameterizedTypeWithOwner(null, List.class);
    assertThat(type.toString()).isEqualTo("java.util.List");
  }

  @Test
  public void testToStringSingleTypeArgument() {
    ParameterizedType type =
        GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    assertThat(type.toString()).isEqualTo("java.util.List<java.lang.String>");
  }

  @Test
  public void testToStringMultipleTypeArguments() {
    ParameterizedType type =
        GsonTypes.newParameterizedTypeWithOwner(null, Map.class, String.class, Integer.class);
    assertThat(type.toString()).isEqualTo("java.util.Map<java.lang.String, java.lang.Integer>");
  }
}
