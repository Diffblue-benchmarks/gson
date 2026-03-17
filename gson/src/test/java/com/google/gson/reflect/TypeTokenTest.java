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

package com.google.gson.reflect;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.fail;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class TypeTokenTest {

  @Test
  public void testProtectedConstructor() {
    TypeToken<List<String>> token = new TypeToken<List<String>>() {};
    assertThat(token.getRawType()).isEqualTo(List.class);
    assertThat(token.getType()).isInstanceOf(ParameterizedType.class);
  }

  @Test
  public void testProtectedConstructorWithClass() {
    TypeToken<String> token = new TypeToken<String>() {};
    assertThat(token.getRawType()).isEqualTo(String.class);
    assertThat(token.getType()).isEqualTo(String.class);
  }

  @Test
  public void testProtectedConstructorWithArray() {
    TypeToken<String[]> token = new TypeToken<String[]>() {};
    assertThat(token.getRawType()).isEqualTo(String[].class);
    assertThat(token.getType()).isNotNull();
  }

  @Test
  @SuppressWarnings({"rawtypes", "unchecked"})
  public void testProtectedConstructorRawTypeThrows() {
    try {
      TypeToken<?> token = new TypeToken() {};
      fail("Expected IllegalStateException");
    } catch (IllegalStateException expected) {
      assertThat(expected.getMessage()).contains("TypeToken must be created with a type argument");
    }
  }

  @Test
  public void testProtectedConstructorIndirectSubclassThrows() {
    try {
      // Create indirect subclass by extending an existing TypeToken subclass
      TypeToken<?> derived = new IndirectTypeTokenSubclass();
      fail("Expected IllegalStateException");
    } catch (IllegalStateException expected) {
      assertThat(expected.getMessage()).contains("Must only create direct subclasses");
    }
  }

  private static class DirectSubclass extends TypeToken<String> {}

  private static class IndirectTypeTokenSubclass extends DirectSubclass {}

  @Test
  public void testProtectedConstructorWithTypeVariable() {
    String originalValue = System.getProperty("gson.allowCapturingTypeVariables");
    try {
      System.setProperty("gson.allowCapturingTypeVariables", "false");
      new TypeTokenWithTypeVariable<String>();
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException expected) {
      assertThat(expected.getMessage()).contains("must not contain a type variable");
    } finally {
      if (originalValue == null) {
        System.clearProperty("gson.allowCapturingTypeVariables");
      } else {
        System.setProperty("gson.allowCapturingTypeVariables", originalValue);
      }
    }
  }

  private static class TypeTokenWithTypeVariable<T> extends TypeToken<List<T>> {}

  @Test
  public void testProtectedConstructorWithTypeVariableAllowed() {
    String originalValue = System.getProperty("gson.allowCapturingTypeVariables");
    try {
      System.setProperty("gson.allowCapturingTypeVariables", "true");
      TypeToken<?> token = new TypeTokenWithTypeVariable<String>();
      assertThat(token).isNotNull();
    } finally {
      if (originalValue == null) {
        System.clearProperty("gson.allowCapturingTypeVariables");
      } else {
        System.setProperty("gson.allowCapturingTypeVariables", originalValue);
      }
    }
  }

  @Test
  public void testGetRawType() {
    TypeToken<List<String>> token = new TypeToken<List<String>>() {};
    assertThat(token.getRawType()).isEqualTo(List.class);
  }

  @Test
  public void testGetType() {
    TypeToken<List<String>> token = new TypeToken<List<String>>() {};
    Type type = token.getType();
    assertThat(type).isInstanceOf(ParameterizedType.class);
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testIsAssignableFromClass() {
    TypeToken<Object> token = new TypeToken<Object>() {};
    assertThat(token.isAssignableFrom(ArrayList.class)).isTrue();
    assertThat(token.isAssignableFrom(String.class)).isTrue();
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testIsAssignableFromType() {
    TypeToken<Object> token = new TypeToken<Object>() {};
    assertThat(token.isAssignableFrom(String.class)).isTrue();
    assertThat(token.isAssignableFrom((Type) null)).isFalse();
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testIsAssignableFromSameType() {
    TypeToken<String> token = new TypeToken<String>() {};
    assertThat(token.isAssignableFrom(String.class)).isTrue();
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testIsAssignableFromClassType() {
    TypeToken<Object> token = new TypeToken<Object>() {};
    assertThat(token.isAssignableFrom(String.class)).isTrue();
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testIsAssignableFromParameterizedType() {
    TypeToken<List<String>> token = new TypeToken<List<String>>() {};
    Type listStringType = new TypeToken<List<String>>() {}.getType();
    assertThat(token.isAssignableFrom(listStringType)).isTrue();
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testIsAssignableFromGenericArrayType() {
    TypeToken<List<String>[]> token = new TypeToken<List<String>[]>() {};
    Type arrayType = new TypeToken<List<String>[]>() {}.getType();
    assertThat(token.isAssignableFrom(arrayType)).isTrue();
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testIsAssignableFromUnsupportedTypeThrows() {
    // Create a TypeToken with a WildcardType, which is unsupported in isAssignableFrom
    Type wildcardType = new TypeToken<List<? extends String>>() {}.getType();
    ParameterizedType paramType = (ParameterizedType) wildcardType;
    Type wildcard = paramType.getActualTypeArguments()[0];

    TypeToken<?> token = TypeToken.get(wildcard);
    try {
      token.isAssignableFrom(String.class);
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException expected) {
      assertThat(expected.getMessage()).contains("Unsupported type");
    }
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testIsAssignableFromTypeToken() {
    TypeToken<Object> objectToken = new TypeToken<Object>() {};
    TypeToken<String> stringToken = new TypeToken<String>() {};
    assertThat(objectToken.isAssignableFrom(stringToken)).isTrue();
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testIsAssignableFromGenericArrayTypeWithParameterizedComponent() {
    TypeToken<List<String>[]> token = new TypeToken<List<String>[]>() {};
    Type listStringArrayType = new TypeToken<List<String>[]>() {}.getType();
    assertThat(token.isAssignableFrom(listStringArrayType)).isTrue();
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testIsAssignableFromGenericArrayTypeWithClassArray() {
    TypeToken<Object[]> token = new TypeToken<Object[]>() {};
    assertThat(token.isAssignableFrom(String[].class)).isTrue();
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testIsAssignableFromParameterizedTypeWithNull() {
    TypeToken<List<String>> token = new TypeToken<List<String>>() {};
    assertThat(token.isAssignableFrom((Type) null)).isFalse();
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testIsAssignableFromParameterizedTypeWithSameType() {
    TypeToken<List<String>> token = new TypeToken<List<String>>() {};
    Type listStringType = new TypeToken<List<String>>() {}.getType();
    assertThat(token.isAssignableFrom(listStringType)).isTrue();
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testIsAssignableFromParameterizedTypeWithSubclass() {
    TypeToken<List<String>> token = new TypeToken<List<String>>() {};
    Type arrayListStringType = new TypeToken<ArrayList<String>>() {}.getType();
    assertThat(token.isAssignableFrom(arrayListStringType)).isTrue();
  }

  @Test
  public void testHashCode() {
    TypeToken<String> token1 = new TypeToken<String>() {};
    TypeToken<String> token2 = new TypeToken<String>() {};
    assertThat(token1.hashCode()).isEqualTo(token2.hashCode());
  }

  @Test
  public void testHashCodeWithDifferentTypes() {
    TypeToken<String> stringToken = new TypeToken<String>() {};
    TypeToken<Integer> intToken = new TypeToken<Integer>() {};
    assertThat(stringToken.hashCode()).isNotEqualTo(intToken.hashCode());
  }

  @Test
  public void testEquals() {
    TypeToken<String> token1 = new TypeToken<String>() {};
    TypeToken<String> token2 = new TypeToken<String>() {};
    assertThat(token1.equals(token2)).isTrue();
  }

  @Test
  public void testEqualsWithDifferentTypes() {
    TypeToken<String> stringToken = new TypeToken<String>() {};
    TypeToken<Integer> intToken = new TypeToken<Integer>() {};
    assertThat(stringToken.equals(intToken)).isFalse();
  }

  @Test
  public void testEqualsWithNonTypeToken() {
    TypeToken<String> token = new TypeToken<String>() {};
    assertThat(token.equals("string")).isFalse();
  }

  @Test
  public void testToString() {
    TypeToken<String> token = new TypeToken<String>() {};
    String result = token.toString();
    assertThat(result).contains("String");
  }

  @Test
  public void testToStringWithParameterizedType() {
    TypeToken<List<String>> token = new TypeToken<List<String>>() {};
    String result = token.toString();
    assertThat(result).isNotEmpty();
  }

  @Test
  public void testGetWithType() {
    TypeToken<?> token = TypeToken.get(String.class);
    assertThat(token.getRawType()).isEqualTo(String.class);
    assertThat(token.getType()).isEqualTo(String.class);
  }

  @Test
  public void testGetWithClass() {
    TypeToken<String> token = TypeToken.get(String.class);
    assertThat(token.getRawType()).isEqualTo(String.class);
    assertThat(token.getType()).isEqualTo(String.class);
  }

  @Test
  public void testGetParameterized() {
    TypeToken<?> token = TypeToken.getParameterized(List.class, String.class);
    assertThat(token.getRawType()).isEqualTo(List.class);
    assertThat(token.getType()).isInstanceOf(ParameterizedType.class);
  }

  @Test
  public void testGetParameterizedWithMultipleTypeArguments() {
    TypeToken<?> token = TypeToken.getParameterized(Map.class, String.class, Integer.class);
    assertThat(token.getRawType()).isEqualTo(Map.class);
    assertThat(token.getType()).isInstanceOf(ParameterizedType.class);
  }

  @Test
  public void testGetParameterizedWithNoTypeArguments() {
    TypeToken<?> token = TypeToken.getParameterized(String.class);
    assertThat(token.getRawType()).isEqualTo(String.class);
  }

  @Test
  public void testGetParameterizedWithNonClassThrows() {
    try {
      TypeToken.getParameterized(new TypeToken<List<String>>() {}.getType(), String.class);
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException expected) {
      assertThat(expected.getMessage()).contains("rawType must be of type Class");
    }
  }

  @Test
  public void testGetParameterizedWithWrongNumberOfTypeArgumentsThrows() {
    try {
      TypeToken.getParameterized(List.class, String.class, Integer.class);
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException expected) {
      assertThat(expected.getMessage()).contains("requires 1 type arguments, but got 2");
    }
  }

  @Test
  public void testGetParameterizedWithInvalidTypeArgumentThrows() {
    try {
      TypeToken.getParameterized(List.class, (Type) null);
      fail("Expected NullPointerException");
    } catch (NullPointerException expected) {
      assertThat(expected.getMessage()).contains("Type argument must not be null");
    }
  }

  @Test
  public void testGetParameterizedWithBoundsViolationThrows() {
    try {
      // Enum<E extends Enum<E>> requires E to extend Enum<E>
      // String does not extend Enum, so this violates the bound
      TypeToken.getParameterized(Enum.class, String.class);
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException expected) {
      assertThat(expected.getMessage()).contains("does not satisfy bounds");
    }
  }

  @Test
  public void testGetParameterizedWithValidBounds() {
    // String implements Comparable<String>
    TypeToken<?> token = TypeToken.getParameterized(Comparable.class, String.class);
    assertThat(token.getRawType()).isEqualTo(Comparable.class);
  }

  @Test
  public void testGetArray() {
    TypeToken<?> token = TypeToken.getArray(String.class);
    assertThat(token.getRawType()).isEqualTo(String[].class);
  }

  @Test
  public void testGetArrayWithParameterizedType() {
    Type listStringType = new TypeToken<List<String>>() {}.getType();
    TypeToken<?> token = TypeToken.getArray(listStringType);
    assertThat(token.getType()).isInstanceOf(GenericArrayType.class);
  }

  @Test
  public void testGetParameterizedWithNullRawTypeThrows() {
    try {
      TypeToken.getParameterized(null, String.class);
      fail("Expected NullPointerException");
    } catch (NullPointerException expected) {
    }
  }

  @Test
  public void testGetParameterizedWithNullTypeArgumentsThrows() {
    try {
      TypeToken.getParameterized(List.class, (Type[]) null);
      fail("Expected NullPointerException");
    } catch (NullPointerException expected) {
    }
  }

  @Test
  public void testGetParameterizedWithNestedType() {
    // Test with a nested type like Map.Entry
    TypeToken<?> token = TypeToken.getParameterized(java.util.Map.Entry.class, String.class, Integer.class);
    assertThat(token.getRawType()).isEqualTo(java.util.Map.Entry.class);
  }
}
