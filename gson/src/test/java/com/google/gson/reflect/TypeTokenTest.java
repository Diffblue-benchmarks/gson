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

package com.google.gson.reflect;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public final class TypeTokenTest {

  @Test
  public void testProtectedConstructorSimpleType() {
    TypeToken<String> token = new TypeToken<String>() {};

    assertThat(token.getRawType()).isEqualTo(String.class);
    assertThat(token.getType()).isEqualTo(String.class);
  }

  @Test
  public void testProtectedConstructorParameterizedType() {
    TypeToken<List<String>> token = new TypeToken<List<String>>() {};

    assertThat(token.getRawType()).isEqualTo(List.class);
    ParameterizedType type = (ParameterizedType) token.getType();
    assertThat(type.getActualTypeArguments()[0]).isEqualTo(String.class);
  }

  @Test
  @SuppressWarnings("rawtypes")
  public void testProtectedConstructorRawTypeThrows() {
    IllegalStateException e = assertThrows(IllegalStateException.class, () -> new TypeToken() {});

    assertThat(e).hasMessageThat().contains("TypeToken must be created with a type argument");
  }

  @Test
  public void testProtectedConstructorCapturedTypeVariableThrows() {
    IllegalArgumentException e =
        assertThrows(IllegalArgumentException.class, TypeTokenTest::createWithTypeVariable);

    assertThat(e)
        .hasMessageThat()
        .contains("TypeToken type argument must not contain a type variable");
  }

  @Test
  public void testGetRawType() {
    TypeToken<String> token = new TypeToken<String>() {};

    assertThat(token.getRawType()).isEqualTo(String.class);
  }

  @Test
  public void testGetType() {
    TypeToken<String> token = new TypeToken<String>() {};

    assertThat(token.getType()).isEqualTo(String.class);
  }

  @Test
  public void testGetStaticFactoryClass() {
    TypeToken<String> token = TypeToken.get(String.class);

    assertThat(token.getRawType()).isEqualTo(String.class);
    assertThat(token.getType()).isEqualTo(String.class);
  }

  @Test
  public void testGetStaticFactoryType() {
    TypeToken<?> token = TypeToken.get((Type) String.class);

    assertThat(token.getRawType()).isEqualTo(String.class);
    assertThat(token.getType()).isEqualTo(String.class);
  }

  @Test
  public void testHashCode() {
    TypeToken<String> token1 = TypeToken.get(String.class);
    TypeToken<String> token2 = TypeToken.get(String.class);

    assertThat(token1.hashCode()).isEqualTo(token2.hashCode());
  }

  @Test
  public void testHashCodeDifferentForDifferentTypes() {
    TypeToken<String> token1 = TypeToken.get(String.class);
    TypeToken<Integer> token2 = TypeToken.get(Integer.class);

    assertThat(token1.hashCode()).isNotEqualTo(token2.hashCode());
  }

  @Test
  public void testEqualsReturnsTrueForSameType() {
    TypeToken<String> token1 = TypeToken.get(String.class);
    TypeToken<String> token2 = TypeToken.get(String.class);

    assertThat(token1).isEqualTo(token2);
  }

  @Test
  public void testEqualsReturnsFalseForDifferentType() {
    TypeToken<String> token1 = TypeToken.get(String.class);
    TypeToken<Integer> token2 = TypeToken.get(Integer.class);

    assertThat(token1).isNotEqualTo(token2);
  }

  @Test
  public void testEqualsReturnsFalseForNonTypeToken() {
    TypeToken<String> token = TypeToken.get(String.class);

    assertThat(token).isNotEqualTo("not a TypeToken");
  }

  @Test
  public void testToString() {
    TypeToken<String> token = TypeToken.get(String.class);

    assertThat(token.toString()).isEqualTo("java.lang.String");
  }

  @Test
  public void testToStringParameterizedType() {
    TypeToken<?> token = TypeToken.getParameterized(List.class, String.class);

    assertThat(token.toString()).isEqualTo("java.util.List<java.lang.String>");
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testIsAssignableFromNullReturnsFalse() {
    TypeToken<String> token = TypeToken.get(String.class);

    assertThat(token.isAssignableFrom((Type) null)).isFalse();
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testIsAssignableFromSameType() {
    TypeToken<String> token = TypeToken.get(String.class);

    assertThat(token.isAssignableFrom(String.class)).isTrue();
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testIsAssignableFromSubclass() {
    TypeToken<Number> token = TypeToken.get(Number.class);

    assertThat(token.isAssignableFrom(Integer.class)).isTrue();
    assertThat(token.isAssignableFrom(String.class)).isFalse();
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testIsAssignableFromTypeToken() {
    TypeToken<Number> token = TypeToken.get(Number.class);

    assertThat(token.isAssignableFrom(TypeToken.get(Integer.class))).isTrue();
    assertThat(token.isAssignableFrom(TypeToken.get(String.class))).isFalse();
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testIsAssignableFromParameterizedTypeSameArgs() {
    TypeToken<?> listStringToken = TypeToken.getParameterized(List.class, String.class);

    assertThat(listStringToken.isAssignableFrom(listStringToken.getType())).isTrue();
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testIsAssignableFromParameterizedTypeDifferentArgs() {
    TypeToken<?> listStringToken = TypeToken.getParameterized(List.class, String.class);
    TypeToken<?> listIntToken = TypeToken.getParameterized(List.class, Integer.class);

    assertThat(listStringToken.isAssignableFrom(listIntToken.getType())).isFalse();
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testIsAssignableFromGenericArrayType() {
    TypeToken<?> arrayToken = TypeToken.getArray(String.class);

    assertThat(arrayToken.isAssignableFrom(String[].class)).isTrue();
  }

  @Test
  public void testGetParameterized() {
    TypeToken<?> token = TypeToken.getParameterized(List.class, String.class);

    assertThat(token.getRawType()).isEqualTo(List.class);
    ParameterizedType type = (ParameterizedType) token.getType();
    assertThat(type.getActualTypeArguments()[0]).isEqualTo(String.class);
  }

  @Test
  public void testGetParameterizedMap() {
    TypeToken<?> token = TypeToken.getParameterized(Map.class, String.class, Integer.class);

    assertThat(token.getRawType()).isEqualTo(Map.class);
    ParameterizedType type = (ParameterizedType) token.getType();
    assertThat(type.getActualTypeArguments()[0]).isEqualTo(String.class);
    assertThat(type.getActualTypeArguments()[1]).isEqualTo(Integer.class);
  }

  @Test
  public void testGetParameterizedNonClassThrows() {
    Type listStringType = TypeToken.getParameterized(List.class, String.class).getType();

    IllegalArgumentException e =
        assertThrows(
            IllegalArgumentException.class,
            () -> TypeToken.getParameterized(listStringType, String.class));

    assertThat(e).hasMessageThat().contains("rawType must be of type Class");
  }

  @Test
  public void testGetParameterizedWrongArgCountThrows() {
    IllegalArgumentException e =
        assertThrows(
            IllegalArgumentException.class,
            () -> TypeToken.getParameterized(List.class, String.class, Integer.class));

    assertThat(e).hasMessageThat().contains("requires");
  }

  @Test
  public void testGetParameterizedNoArgsForNonGenericClass() {
    TypeToken<?> token = TypeToken.getParameterized(String.class);

    assertThat(token.getRawType()).isEqualTo(String.class);
    assertThat(token.getType()).isEqualTo(String.class);
  }

  @Test
  public void testGetArray() {
    TypeToken<?> token = TypeToken.getArray(String.class);

    assertThat(token.getRawType()).isEqualTo(String[].class);
  }

  @Test
  public void testGetArrayParameterizedComponentType() {
    Type listStringType = TypeToken.getParameterized(List.class, String.class).getType();
    TypeToken<?> token = TypeToken.getArray(listStringType);

    assertThat(token.getRawType()).isEqualTo(List[].class);
  }

  @Test
  public void testGetParameterizedNullTypeArgumentThrows() {
    NullPointerException e =
        assertThrows(
            NullPointerException.class,
            () -> TypeToken.getParameterized(List.class, new Type[] {null}));

    assertThat(e).hasMessageThat().contains("Type argument must not be null");
  }

  private static <T> TypeToken<T> createWithTypeVariable() {
    return new TypeToken<T>() {};
  }
}
