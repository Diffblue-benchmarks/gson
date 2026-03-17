/*
 * Copyright (C) 2026 Google Inc.
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
import static org.junit.Assert.fail;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class GsonTypesParameterizedTypeImplTest {

  // Helper class to test non-static inner class
  public class NonStaticInnerClass {}

  @Test
  public void testConstructorWithValidParameters() {
    Type[] typeArgs = {String.class};
    ParameterizedType type =
        GsonTypes.newParameterizedTypeWithOwner(null, List.class, typeArgs);

    assertThat(type.getRawType()).isEqualTo(List.class);
    assertThat(type.getActualTypeArguments()).hasLength(1);
    assertThat(type.getActualTypeArguments()[0]).isEqualTo(String.class);
    assertThat(type.getOwnerType()).isNull();
  }

  @Test
  public void testConstructorWithOwnerType() {
    Type[] typeArgs = {String.class, Integer.class};
    ParameterizedType type =
        GsonTypes.newParameterizedTypeWithOwner(Map.class, Map.Entry.class, typeArgs);

    assertThat(type.getRawType()).isEqualTo(Map.Entry.class);
    assertThat(type.getActualTypeArguments()).hasLength(2);
    assertThat(type.getActualTypeArguments()[0]).isEqualTo(String.class);
    assertThat(type.getActualTypeArguments()[1]).isEqualTo(Integer.class);
    assertThat(type.getOwnerType()).isEqualTo(Map.class);
  }

  @Test
  public void testConstructorThrowsWhenRawTypeIsNull() {
    try {
      GsonTypes.newParameterizedTypeWithOwner(null, null, String.class);
      fail("Expected NullPointerException");
    } catch (NullPointerException expected) {
    }
  }

  @Test
  public void testConstructorThrowsWhenOwnerTypeRequiredButNotProvided() {
    try {
      GsonTypes.newParameterizedTypeWithOwner(null, NonStaticInnerClass.class, String.class);
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException expected) {
      assertThat(expected.getMessage()).contains("Must specify owner type");
    }
  }

  @Test
  public void testConstructorThrowsWhenTypeArgumentIsNull() {
    Type[] typeArgs = {null};
    try {
      GsonTypes.newParameterizedTypeWithOwner(null, List.class, typeArgs);
      fail("Expected NullPointerException");
    } catch (NullPointerException expected) {
    }
  }

  @Test
  public void testConstructorThrowsWhenTypeArgumentIsPrimitive() {
    try {
      GsonTypes.newParameterizedTypeWithOwner(null, List.class, int.class);
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException expected) {
      assertThat(expected.getMessage()).contains("Primitive type is not allowed");
    }
  }

  @Test
  public void testGetActualTypeArguments() {
    Type[] typeArgs = {String.class, Integer.class};
    ParameterizedType type = GsonTypes.newParameterizedTypeWithOwner(null, Map.class, typeArgs);

    Type[] result = type.getActualTypeArguments();
    assertThat(result).hasLength(2);
    assertThat(result[0]).isEqualTo(String.class);
    assertThat(result[1]).isEqualTo(Integer.class);

    // Verify that the returned array is a clone and not the internal array
    result[0] = Double.class;
    assertThat(type.getActualTypeArguments()[0]).isEqualTo(String.class);
  }

  @Test
  public void testGetRawType() {
    ParameterizedType type =
        GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);

    assertThat(type.getRawType()).isEqualTo(List.class);
  }

  @Test
  public void testGetOwnerType() {
    ParameterizedType type =
        GsonTypes.newParameterizedTypeWithOwner(Map.class, Map.Entry.class, String.class, Integer.class);

    assertThat(type.getOwnerType()).isEqualTo(Map.class);
  }

  @Test
  public void testGetOwnerTypeReturnsNullWhenNotSet() {
    ParameterizedType type =
        GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);

    assertThat(type.getOwnerType()).isNull();
  }

  @Test
  public void testEqualsWithSameInstance() {
    ParameterizedType type =
        GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);

    assertThat(type.equals(type)).isTrue();
  }

  @Test
  public void testEqualsWithEquivalentType() {
    ParameterizedType type1 =
        GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    ParameterizedType type2 =
        GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);

    assertThat(type1.equals(type2)).isTrue();
  }

  @Test
  public void testEqualsWithDifferentRawType() {
    ParameterizedType type1 =
        GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    ParameterizedType type2 =
        GsonTypes.newParameterizedTypeWithOwner(null, Map.class, String.class, Integer.class);

    assertThat(type1.equals(type2)).isFalse();
  }

  @Test
  public void testEqualsWithDifferentTypeArguments() {
    ParameterizedType type1 =
        GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    ParameterizedType type2 =
        GsonTypes.newParameterizedTypeWithOwner(null, List.class, Integer.class);

    assertThat(type1.equals(type2)).isFalse();
  }

  @Test
  public void testEqualsWithNonParameterizedType() {
    ParameterizedType type =
        GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);

    assertThat(type.equals(String.class)).isFalse();
  }

  @Test
  public void testHashCode() {
    ParameterizedType type =
        GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);

    int hashCode = type.hashCode();
    assertThat(hashCode).isNotEqualTo(0);
  }

  @Test
  public void testHashCodeConsistency() {
    ParameterizedType type =
        GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);

    int hashCode1 = type.hashCode();
    int hashCode2 = type.hashCode();
    assertThat(hashCode1).isEqualTo(hashCode2);
  }

  @Test
  public void testHashCodeWithOwnerType() {
    ParameterizedType type =
        GsonTypes.newParameterizedTypeWithOwner(Map.class, Map.Entry.class, String.class, Integer.class);

    int hashCode = type.hashCode();
    assertThat(hashCode).isNotEqualTo(0);
  }

  @Test
  public void testToStringWithNoTypeArguments() {
    ParameterizedType type = GsonTypes.newParameterizedTypeWithOwner(null, List.class);

    assertThat(type.toString()).isEqualTo("java.util.List");
  }

  @Test
  public void testToStringWithSingleTypeArgument() {
    ParameterizedType type =
        GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);

    assertThat(type.toString()).isEqualTo("java.util.List<java.lang.String>");
  }

  @Test
  public void testToStringWithMultipleTypeArguments() {
    ParameterizedType type =
        GsonTypes.newParameterizedTypeWithOwner(null, Map.class, String.class, Integer.class);

    assertThat(type.toString()).isEqualTo("java.util.Map<java.lang.String, java.lang.Integer>");
  }
}
