/*
 * Copyright (C) 2022 Google Inc.
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

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.Test;

/**
 * Tests for {@link TypeToken}.
 *
 * @author Claude
 */
@SuppressWarnings("deprecation") // Testing deprecated isAssignableFrom methods
public class TypeTokenClaudeTest {

  // ==========================================================================
  // Helper classes for testing
  // ==========================================================================

  private static class SimpleClass {
    @SuppressWarnings("unused")
    public String field;
  }

  private static class SubClass extends SimpleClass {}

  private interface SimpleInterface {}

  private static class InterfaceImpl implements SimpleInterface {}

  private static class GenericClass<T> {
    @SuppressWarnings("unused")
    public T value;
  }

  private static class BoundedGenericClass<T extends Number> {
    @SuppressWarnings("unused")
    public T value;
  }

  // ==========================================================================
  // Constructor tests - protected TypeToken()
  // ==========================================================================

  @Test
  public void testConstructor_withSimpleClass() {
    TypeToken<String> token = new TypeToken<String>() {};
    assertThat(token).isNotNull();
    assertThat(token.getType()).isEqualTo(String.class);
    assertThat(token.getRawType()).isEqualTo(String.class);
  }

  @Test
  public void testConstructor_withGenericType() {
    TypeToken<List<String>> token = new TypeToken<List<String>>() {};
    assertThat(token).isNotNull();
    assertThat(token.getRawType()).isEqualTo(List.class);
    Type type = token.getType();
    assertThat(type).isInstanceOf(ParameterizedType.class);
    ParameterizedType pType = (ParameterizedType) type;
    assertThat(pType.getRawType()).isEqualTo(List.class);
    assertThat(pType.getActualTypeArguments()[0]).isEqualTo(String.class);
  }

  @Test
  public void testConstructor_withNestedGenericType() {
    TypeToken<Map<String, List<Integer>>> token = new TypeToken<Map<String, List<Integer>>>() {};
    assertThat(token).isNotNull();
    assertThat(token.getRawType()).isEqualTo(Map.class);
    Type type = token.getType();
    assertThat(type).isInstanceOf(ParameterizedType.class);
    ParameterizedType pType = (ParameterizedType) type;
    assertThat(pType.getActualTypeArguments()).hasLength(2);
  }

  @Test
  public void testConstructor_withArrayType() {
    TypeToken<String[]> token = new TypeToken<String[]>() {};
    assertThat(token).isNotNull();
    assertThat(token.getRawType()).isEqualTo(String[].class);
    // TypeToken canonicalizes array types to GenericArrayType
    assertThat(token.getType()).isInstanceOf(GenericArrayType.class);
    GenericArrayType arrayType = (GenericArrayType) token.getType();
    assertThat(arrayType.getGenericComponentType()).isEqualTo(String.class);
  }

  @Test
  public void testConstructor_withPrimitiveType() {
    TypeToken<Integer> token = new TypeToken<Integer>() {};
    assertThat(token).isNotNull();
    assertThat(token.getRawType()).isEqualTo(Integer.class);
  }

  @Test
  public void testConstructor_withWildcardType() {
    TypeToken<List<? extends Number>> token = new TypeToken<List<? extends Number>>() {};
    assertThat(token).isNotNull();
    assertThat(token.getRawType()).isEqualTo(List.class);
    Type type = token.getType();
    assertThat(type).isInstanceOf(ParameterizedType.class);
  }

  @Test
  public void testConstructor_rawTypeToken_throwsIllegalStateException() {
    // Create a direct subclass of TypeToken without type parameter (raw type)
    // This should throw an IllegalStateException
    IllegalStateException exception =
        assertThrows(
            IllegalStateException.class,
            () -> {
              @SuppressWarnings({"rawtypes", "unused"})
              TypeToken token = new TypeToken() {};
            });
    assertThat(exception.getMessage()).contains("TypeToken must be created with a type argument");
  }

  // ==========================================================================
  // getRawType tests
  // ==========================================================================

  @Test
  public void testGetRawType_forSimpleClass() {
    TypeToken<String> token = new TypeToken<String>() {};
    assertThat(token.getRawType()).isEqualTo(String.class);
  }

  @Test
  public void testGetRawType_forGenericList() {
    TypeToken<List<String>> token = new TypeToken<List<String>>() {};
    assertThat(token.getRawType()).isEqualTo(List.class);
  }

  @Test
  public void testGetRawType_forGenericMap() {
    TypeToken<Map<String, Integer>> token = new TypeToken<Map<String, Integer>>() {};
    assertThat(token.getRawType()).isEqualTo(Map.class);
  }

  @Test
  public void testGetRawType_forArray() {
    TypeToken<Integer[]> token = new TypeToken<Integer[]>() {};
    assertThat(token.getRawType()).isEqualTo(Integer[].class);
  }

  @Test
  public void testGetRawType_forNestedGeneric() {
    TypeToken<List<Map<String, Integer>>> token = new TypeToken<List<Map<String, Integer>>>() {};
    assertThat(token.getRawType()).isEqualTo(List.class);
  }

  @Test
  public void testGetRawType_usingGetMethod() {
    TypeToken<?> token = TypeToken.get(String.class);
    assertThat(token.getRawType()).isEqualTo(String.class);
  }

  // ==========================================================================
  // getType tests
  // ==========================================================================

  @Test
  public void testGetType_forSimpleClass() {
    TypeToken<String> token = new TypeToken<String>() {};
    assertThat(token.getType()).isEqualTo(String.class);
  }

  @Test
  public void testGetType_forGenericType() {
    TypeToken<List<String>> token = new TypeToken<List<String>>() {};
    Type type = token.getType();
    assertThat(type).isInstanceOf(ParameterizedType.class);
    ParameterizedType pType = (ParameterizedType) type;
    assertThat(pType.getRawType()).isEqualTo(List.class);
    assertThat(pType.getActualTypeArguments()[0]).isEqualTo(String.class);
  }

  @Test
  public void testGetType_forNestedGenericType() {
    TypeToken<Map<String, List<Integer>>> token = new TypeToken<Map<String, List<Integer>>>() {};
    Type type = token.getType();
    assertThat(type).isInstanceOf(ParameterizedType.class);
    ParameterizedType pType = (ParameterizedType) type;
    assertThat(pType.getRawType()).isEqualTo(Map.class);
    Type[] args = pType.getActualTypeArguments();
    assertThat(args[0]).isEqualTo(String.class);
    assertThat(args[1]).isInstanceOf(ParameterizedType.class);
    ParameterizedType nestedType = (ParameterizedType) args[1];
    assertThat(nestedType.getRawType()).isEqualTo(List.class);
    assertThat(nestedType.getActualTypeArguments()[0]).isEqualTo(Integer.class);
  }

  @Test
  public void testGetType_forArrayType() {
    TypeToken<String[]> token = new TypeToken<String[]>() {};
    // TypeToken canonicalizes array types to GenericArrayType
    assertThat(token.getType()).isInstanceOf(GenericArrayType.class);
    GenericArrayType arrayType = (GenericArrayType) token.getType();
    assertThat(arrayType.getGenericComponentType()).isEqualTo(String.class);
  }

  @Test
  public void testGetType_forGenericArrayType() {
    TypeToken<List<String>[]> token = new TypeToken<List<String>[]>() {};
    Type type = token.getType();
    assertThat(type).isInstanceOf(GenericArrayType.class);
    GenericArrayType arrayType = (GenericArrayType) type;
    assertThat(arrayType.getGenericComponentType()).isInstanceOf(ParameterizedType.class);
  }

  // ==========================================================================
  // isAssignableFrom(Class) tests - deprecated but still testing
  // ==========================================================================

  @Test
  public void testIsAssignableFromClass_sameClass() {
    TypeToken<String> token = new TypeToken<String>() {};
    assertThat(token.isAssignableFrom(String.class)).isTrue();
  }

  @Test
  public void testIsAssignableFromClass_subClass() {
    TypeToken<Number> token = new TypeToken<Number>() {};
    assertThat(token.isAssignableFrom(Integer.class)).isTrue();
  }

  @Test
  public void testIsAssignableFromClass_superClass() {
    TypeToken<Integer> token = new TypeToken<Integer>() {};
    assertThat(token.isAssignableFrom(Number.class)).isFalse();
  }

  @Test
  public void testIsAssignableFromClass_unrelatedClass() {
    TypeToken<String> token = new TypeToken<String>() {};
    assertThat(token.isAssignableFrom(Integer.class)).isFalse();
  }

  @Test
  public void testIsAssignableFromClass_interfaceImplementation() {
    TypeToken<SimpleInterface> token = new TypeToken<SimpleInterface>() {};
    assertThat(token.isAssignableFrom(InterfaceImpl.class)).isTrue();
  }

  @Test
  public void testIsAssignableFromClass_null() {
    TypeToken<String> token = new TypeToken<String>() {};
    assertThat(token.isAssignableFrom((Class<?>) null)).isFalse();
  }

  @Test
  public void testIsAssignableFromClass_objectType() {
    TypeToken<Object> token = new TypeToken<Object>() {};
    assertThat(token.isAssignableFrom(String.class)).isTrue();
    assertThat(token.isAssignableFrom(Integer.class)).isTrue();
    assertThat(token.isAssignableFrom(Object.class)).isTrue();
  }

  // ==========================================================================
  // isAssignableFrom(Type) tests - deprecated but still testing
  // ==========================================================================

  @Test
  public void testIsAssignableFromType_sameType() {
    TypeToken<String> token = new TypeToken<String>() {};
    assertThat(token.isAssignableFrom((Type) String.class)).isTrue();
  }

  @Test
  public void testIsAssignableFromType_subType() {
    TypeToken<Number> token = new TypeToken<Number>() {};
    assertThat(token.isAssignableFrom((Type) Integer.class)).isTrue();
  }

  @Test
  public void testIsAssignableFromType_parameterizedType() {
    TypeToken<List<String>> token = new TypeToken<List<String>>() {};
    Type listStringType = new TypeToken<List<String>>() {}.getType();
    assertThat(token.isAssignableFrom(listStringType)).isTrue();
  }

  @Test
  public void testIsAssignableFromType_parameterizedType_differentTypeArgument() {
    TypeToken<List<String>> token = new TypeToken<List<String>>() {};
    Type listIntegerType = new TypeToken<List<Integer>>() {}.getType();
    assertThat(token.isAssignableFrom(listIntegerType)).isFalse();
  }

  @Test
  public void testIsAssignableFromType_genericArrayType() {
    TypeToken<List<String>[]> token = new TypeToken<List<String>[]>() {};
    Type arrayType = new TypeToken<List<String>[]>() {}.getType();
    assertThat(token.isAssignableFrom(arrayType)).isTrue();
  }

  @Test
  public void testIsAssignableFromType_null() {
    TypeToken<String> token = new TypeToken<String>() {};
    assertThat(token.isAssignableFrom((Type) null)).isFalse();
  }

  @Test
  public void testIsAssignableFromType_collectionHierarchy() {
    TypeToken<Collection<String>> token = new TypeToken<Collection<String>>() {};
    Type listType = new TypeToken<List<String>>() {}.getType();
    assertThat(token.isAssignableFrom(listType)).isTrue();
  }

  @Test
  public void testIsAssignableFromType_arrayListToList() {
    TypeToken<List<String>> token = new TypeToken<List<String>>() {};
    Type arrayListType = new TypeToken<ArrayList<String>>() {}.getType();
    assertThat(token.isAssignableFrom(arrayListType)).isTrue();
  }

  // ==========================================================================
  // isAssignableFrom(TypeToken) tests - deprecated but still testing
  // ==========================================================================

  @Test
  public void testIsAssignableFromTypeToken_sameType() {
    TypeToken<String> token1 = new TypeToken<String>() {};
    TypeToken<String> token2 = new TypeToken<String>() {};
    assertThat(token1.isAssignableFrom(token2)).isTrue();
  }

  @Test
  public void testIsAssignableFromTypeToken_subType() {
    TypeToken<Number> token1 = new TypeToken<Number>() {};
    TypeToken<Integer> token2 = new TypeToken<Integer>() {};
    assertThat(token1.isAssignableFrom(token2)).isTrue();
  }

  @Test
  public void testIsAssignableFromTypeToken_genericType() {
    TypeToken<List<String>> token1 = new TypeToken<List<String>>() {};
    TypeToken<List<String>> token2 = new TypeToken<List<String>>() {};
    assertThat(token1.isAssignableFrom(token2)).isTrue();
  }

  @Test
  public void testIsAssignableFromTypeToken_differentGenericArguments() {
    TypeToken<List<String>> token1 = new TypeToken<List<String>>() {};
    TypeToken<List<Integer>> token2 = new TypeToken<List<Integer>>() {};
    assertThat(token1.isAssignableFrom(token2)).isFalse();
  }

  @Test
  public void testIsAssignableFromTypeToken_collectionHierarchy() {
    TypeToken<Collection<String>> token1 = new TypeToken<Collection<String>>() {};
    TypeToken<List<String>> token2 = new TypeToken<List<String>>() {};
    assertThat(token1.isAssignableFrom(token2)).isTrue();
  }

  // ==========================================================================
  // hashCode tests
  // ==========================================================================

  @Test
  public void testHashCode_sameType() {
    TypeToken<String> token1 = new TypeToken<String>() {};
    TypeToken<String> token2 = new TypeToken<String>() {};
    assertThat(token1.hashCode()).isEqualTo(token2.hashCode());
  }

  @Test
  public void testHashCode_sameGenericType() {
    TypeToken<List<String>> token1 = new TypeToken<List<String>>() {};
    TypeToken<List<String>> token2 = new TypeToken<List<String>>() {};
    assertThat(token1.hashCode()).isEqualTo(token2.hashCode());
  }

  @Test
  public void testHashCode_differentTypes() {
    TypeToken<String> token1 = new TypeToken<String>() {};
    TypeToken<Integer> token2 = new TypeToken<Integer>() {};
    // Hash codes MAY be different, but it's not guaranteed
    // We just verify that hashCode() can be called without error and returns valid values
    int hash1 = token1.hashCode();
    int hash2 = token2.hashCode();
    // Both should be non-zero (very unlikely to be zero for these types)
    assertThat(hash1 != 0 || hash2 != 0).isTrue();
  }

  @Test
  public void testHashCode_consistentAcrossCalls() {
    TypeToken<Map<String, Integer>> token = new TypeToken<Map<String, Integer>>() {};
    int hash1 = token.hashCode();
    int hash2 = token.hashCode();
    int hash3 = token.hashCode();
    assertThat(hash1).isEqualTo(hash2);
    assertThat(hash2).isEqualTo(hash3);
  }

  @Test
  public void testHashCode_fromGetMethod() {
    TypeToken<?> token1 = TypeToken.get(String.class);
    TypeToken<String> token2 = new TypeToken<String>() {};
    assertThat(token1.hashCode()).isEqualTo(token2.hashCode());
  }

  // ==========================================================================
  // equals tests
  // ==========================================================================

  @Test
  public void testEquals_sameInstance() {
    TypeToken<String> token = new TypeToken<String>() {};
    assertThat(token.equals(token)).isTrue();
  }

  @Test
  public void testEquals_sameType() {
    TypeToken<String> token1 = new TypeToken<String>() {};
    TypeToken<String> token2 = new TypeToken<String>() {};
    assertThat(token1.equals(token2)).isTrue();
    assertThat(token2.equals(token1)).isTrue();
  }

  @Test
  public void testEquals_differentTypes() {
    TypeToken<String> token1 = new TypeToken<String>() {};
    TypeToken<Integer> token2 = new TypeToken<Integer>() {};
    assertThat(token1.equals(token2)).isFalse();
  }

  @Test
  public void testEquals_sameGenericType() {
    TypeToken<List<String>> token1 = new TypeToken<List<String>>() {};
    TypeToken<List<String>> token2 = new TypeToken<List<String>>() {};
    assertThat(token1.equals(token2)).isTrue();
  }

  @Test
  public void testEquals_differentGenericTypeArguments() {
    TypeToken<List<String>> token1 = new TypeToken<List<String>>() {};
    TypeToken<List<Integer>> token2 = new TypeToken<List<Integer>>() {};
    assertThat(token1.equals(token2)).isFalse();
  }

  @Test
  public void testEquals_null() {
    TypeToken<String> token = new TypeToken<String>() {};
    assertThat(token.equals(null)).isFalse();
  }

  @Test
  public void testEquals_differentObjectType() {
    TypeToken<String> token = new TypeToken<String>() {};
    assertThat(token.equals("not a type token")).isFalse();
    assertThat(token.equals(String.class)).isFalse();
  }

  @Test
  public void testEquals_fromGetMethodVsAnonymous() {
    TypeToken<?> token1 = TypeToken.get(String.class);
    TypeToken<String> token2 = new TypeToken<String>() {};
    assertThat(token1.equals(token2)).isTrue();
    assertThat(token2.equals(token1)).isTrue();
  }

  @Test
  public void testEquals_complexNestedType() {
    TypeToken<Map<String, List<Integer>>> token1 = new TypeToken<Map<String, List<Integer>>>() {};
    TypeToken<Map<String, List<Integer>>> token2 = new TypeToken<Map<String, List<Integer>>>() {};
    assertThat(token1.equals(token2)).isTrue();
  }

  @Test
  public void testEquals_arrayTypes() {
    TypeToken<String[]> token1 = new TypeToken<String[]>() {};
    TypeToken<String[]> token2 = new TypeToken<String[]>() {};
    assertThat(token1.equals(token2)).isTrue();
  }

  @Test
  public void testEquals_differentArrayTypes() {
    TypeToken<String[]> token1 = new TypeToken<String[]>() {};
    TypeToken<Integer[]> token2 = new TypeToken<Integer[]>() {};
    assertThat(token1.equals(token2)).isFalse();
  }

  // ==========================================================================
  // toString tests
  // ==========================================================================

  @Test
  public void testToString_simpleClass() {
    TypeToken<String> token = new TypeToken<String>() {};
    assertThat(token.toString()).isEqualTo("java.lang.String");
  }

  @Test
  public void testToString_primitiveWrapperClass() {
    TypeToken<Integer> token = new TypeToken<Integer>() {};
    assertThat(token.toString()).isEqualTo("java.lang.Integer");
  }

  @Test
  public void testToString_genericList() {
    TypeToken<List<String>> token = new TypeToken<List<String>>() {};
    assertThat(token.toString()).isEqualTo("java.util.List<java.lang.String>");
  }

  @Test
  public void testToString_genericMap() {
    TypeToken<Map<String, Integer>> token = new TypeToken<Map<String, Integer>>() {};
    assertThat(token.toString()).isEqualTo("java.util.Map<java.lang.String, java.lang.Integer>");
  }

  @Test
  public void testToString_nestedGenericType() {
    TypeToken<List<Map<String, Integer>>> token = new TypeToken<List<Map<String, Integer>>>() {};
    assertThat(token.toString())
        .isEqualTo("java.util.List<java.util.Map<java.lang.String, java.lang.Integer>>");
  }

  @Test
  public void testToString_arrayType() {
    TypeToken<String[]> token = new TypeToken<String[]>() {};
    assertThat(token.toString()).isEqualTo("java.lang.String[]");
  }

  @Test
  public void testToString_genericArrayType() {
    TypeToken<List<String>[]> token = new TypeToken<List<String>[]>() {};
    assertThat(token.toString()).isEqualTo("java.util.List<java.lang.String>[]");
  }

  @Test
  public void testToString_wildcardType() {
    TypeToken<List<? extends Number>> token = new TypeToken<List<? extends Number>>() {};
    assertThat(token.toString()).isEqualTo("java.util.List<? extends java.lang.Number>");
  }

  // ==========================================================================
  // get(Type) tests
  // ==========================================================================

  @Test
  public void testGetType_simpleClass() {
    TypeToken<?> token = TypeToken.get(String.class);
    assertThat(token.getType()).isEqualTo(String.class);
    assertThat(token.getRawType()).isEqualTo(String.class);
  }

  @Test
  public void testGetType_primitiveClass() {
    TypeToken<?> token = TypeToken.get(int.class);
    assertThat(token.getType()).isEqualTo(int.class);
    assertThat(token.getRawType()).isEqualTo(int.class);
  }

  @Test
  public void testGetType_parameterizedType() {
    Type listType = new TypeToken<List<String>>() {}.getType();
    TypeToken<?> token = TypeToken.get(listType);
    assertThat(token.getType()).isEqualTo(listType);
    assertThat(token.getRawType()).isEqualTo(List.class);
  }

  @Test
  public void testGetType_genericArrayType() {
    Type arrayType = new TypeToken<List<String>[]>() {}.getType();
    TypeToken<?> token = TypeToken.get(arrayType);
    assertThat(token.getType()).isEqualTo(arrayType);
  }

  @Test
  public void testGetType_nullType_throwsNullPointerException() {
    assertThrows(NullPointerException.class, () -> TypeToken.get((Type) null));
  }

  @Test
  public void testGetType_wildcardType() {
    Type wildcardType = new TypeToken<List<? extends Number>>() {}.getType();
    TypeToken<?> token = TypeToken.get(wildcardType);
    assertThat(token.getType()).isNotNull();
    assertThat(token.getRawType()).isEqualTo(List.class);
  }

  // ==========================================================================
  // get(Class) tests
  // ==========================================================================

  @Test
  public void testGetClass_simpleClass() {
    TypeToken<String> token = TypeToken.get(String.class);
    assertThat(token.getType()).isEqualTo(String.class);
    assertThat(token.getRawType()).isEqualTo(String.class);
  }

  @Test
  public void testGetClass_primitiveClass() {
    TypeToken<Integer> token = TypeToken.get(int.class);
    assertThat(token.getType()).isEqualTo(int.class);
    assertThat(token.getRawType()).isEqualTo(int.class);
  }

  @Test
  public void testGetClass_arrayClass() {
    TypeToken<String[]> token = TypeToken.get(String[].class);
    // TypeToken canonicalizes array types to GenericArrayType
    assertThat(token.getType()).isInstanceOf(GenericArrayType.class);
    assertThat(token.getRawType()).isEqualTo(String[].class);
  }

  @Test
  public void testGetClass_interfaceClass() {
    TypeToken<List> token = TypeToken.get(List.class);
    assertThat(token.getType()).isEqualTo(List.class);
    assertThat(token.getRawType()).isEqualTo(List.class);
  }

  @Test
  public void testGetClass_nullClass_throwsNullPointerException() {
    assertThrows(NullPointerException.class, () -> TypeToken.get((Class<?>) null));
  }

  @Test
  public void testGetClass_objectClass() {
    TypeToken<Object> token = TypeToken.get(Object.class);
    assertThat(token.getType()).isEqualTo(Object.class);
    assertThat(token.getRawType()).isEqualTo(Object.class);
  }

  // ==========================================================================
  // getParameterized tests
  // ==========================================================================

  @Test
  public void testGetParameterized_singleTypeArgument() {
    TypeToken<?> token = TypeToken.getParameterized(List.class, String.class);
    assertThat(token.getRawType()).isEqualTo(List.class);
    Type type = token.getType();
    assertThat(type).isInstanceOf(ParameterizedType.class);
    ParameterizedType pType = (ParameterizedType) type;
    assertThat(pType.getActualTypeArguments()[0]).isEqualTo(String.class);
  }

  @Test
  public void testGetParameterized_multipleTypeArguments() {
    TypeToken<?> token = TypeToken.getParameterized(Map.class, String.class, Integer.class);
    assertThat(token.getRawType()).isEqualTo(Map.class);
    Type type = token.getType();
    assertThat(type).isInstanceOf(ParameterizedType.class);
    ParameterizedType pType = (ParameterizedType) type;
    assertThat(pType.getActualTypeArguments()[0]).isEqualTo(String.class);
    assertThat(pType.getActualTypeArguments()[1]).isEqualTo(Integer.class);
  }

  @Test
  public void testGetParameterized_nestedParameterizedType() {
    Type listStringType = TypeToken.getParameterized(List.class, String.class).getType();
    TypeToken<?> token = TypeToken.getParameterized(Map.class, String.class, listStringType);
    assertThat(token.getRawType()).isEqualTo(Map.class);
    Type type = token.getType();
    assertThat(type).isInstanceOf(ParameterizedType.class);
    ParameterizedType pType = (ParameterizedType) type;
    assertThat(pType.getActualTypeArguments()[0]).isEqualTo(String.class);
    assertThat(pType.getActualTypeArguments()[1]).isInstanceOf(ParameterizedType.class);
  }

  @Test
  public void testGetParameterized_nonGenericClass_noTypeArguments() {
    // Non-generic class with no type arguments should delegate to get(Class)
    TypeToken<?> token = TypeToken.getParameterized(String.class);
    assertThat(token.getRawType()).isEqualTo(String.class);
    assertThat(token.getType()).isEqualTo(String.class);
  }

  @Test
  public void testGetParameterized_wrongNumberOfTypeArguments_throwsException() {
    // List expects 1 type argument but 2 are provided
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> TypeToken.getParameterized(List.class, String.class, Integer.class));
    assertThat(exception.getMessage()).contains("requires 1 type arguments");
    assertThat(exception.getMessage()).contains("got 2");
  }

  @Test
  public void testGetParameterized_missingTypeArguments_throwsException() {
    // List expects 1 type argument but none provided
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> TypeToken.getParameterized(List.class));
    assertThat(exception.getMessage()).contains("requires 1 type arguments");
    assertThat(exception.getMessage()).contains("got 0");
  }

  @Test
  public void testGetParameterized_nullRawType_throwsNullPointerException() {
    assertThrows(NullPointerException.class, () -> TypeToken.getParameterized(null, String.class));
  }

  @Test
  public void testGetParameterized_nullTypeArguments_throwsNullPointerException() {
    assertThrows(NullPointerException.class, () -> TypeToken.getParameterized(List.class, (Type[]) null));
  }

  @Test
  public void testGetParameterized_nullTypeArgument_throwsNullPointerException() {
    assertThrows(
        NullPointerException.class,
        () -> TypeToken.getParameterized(List.class, (Type) null));
  }

  @Test
  public void testGetParameterized_nonClassRawType_throwsException() {
    // Raw type must be a Class, not a ParameterizedType
    Type listType = new TypeToken<List<String>>() {}.getType();
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class, () -> TypeToken.getParameterized(listType, String.class));
    assertThat(exception.getMessage()).contains("rawType must be of type Class");
  }

  @Test
  public void testGetParameterized_boundedTypeParameter() {
    // BoundedGenericClass<T extends Number> should accept Integer
    TypeToken<?> token = TypeToken.getParameterized(BoundedGenericClass.class, Integer.class);
    assertThat(token.getRawType()).isEqualTo(BoundedGenericClass.class);
  }

  @Test
  public void testGetParameterized_violatedBound_throwsException() {
    // BoundedGenericClass<T extends Number> should reject String
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> TypeToken.getParameterized(BoundedGenericClass.class, String.class));
    assertThat(exception.getMessage()).contains("does not satisfy bounds");
  }

  // ==========================================================================
  // getArray tests
  // ==========================================================================

  @Test
  public void testGetArray_simpleClass() {
    TypeToken<?> token = TypeToken.getArray(String.class);
    assertThat(token.getRawType()).isEqualTo(String[].class);
    assertThat(token.getType()).isInstanceOf(GenericArrayType.class);
  }

  @Test
  public void testGetArray_primitiveClass() {
    TypeToken<?> token = TypeToken.getArray(int.class);
    assertThat(token.getRawType()).isEqualTo(int[].class);
    assertThat(token.getType()).isInstanceOf(GenericArrayType.class);
  }

  @Test
  public void testGetArray_parameterizedType() {
    Type listType = new TypeToken<List<String>>() {}.getType();
    TypeToken<?> token = TypeToken.getArray(listType);
    assertThat(token.getType()).isInstanceOf(GenericArrayType.class);
    GenericArrayType arrayType = (GenericArrayType) token.getType();
    assertThat(arrayType.getGenericComponentType()).isEqualTo(listType);
  }

  @Test
  public void testGetArray_nestedArray() {
    TypeToken<?> stringArrayToken = TypeToken.getArray(String.class);
    TypeToken<?> token = TypeToken.getArray(stringArrayToken.getType());
    // This creates String[][]
    assertThat(token.getType()).isInstanceOf(GenericArrayType.class);
  }

  @Test
  public void testGetArray_toStringVerification() {
    TypeToken<?> token = TypeToken.getArray(String.class);
    assertThat(token.toString()).isEqualTo("java.lang.String[]");
  }

  // ==========================================================================
  // Additional edge case tests
  // ==========================================================================

  @Test
  public void testTypeToken_withVoidType() {
    TypeToken<Void> token = new TypeToken<Void>() {};
    assertThat(token.getRawType()).isEqualTo(Void.class);
  }

  @Test
  public void testTypeToken_equalsContractWithHashCode() {
    // If equals returns true, hashCode should be the same
    TypeToken<List<String>> token1 = new TypeToken<List<String>>() {};
    TypeToken<List<String>> token2 = new TypeToken<List<String>>() {};
    if (token1.equals(token2)) {
      assertThat(token1.hashCode()).isEqualTo(token2.hashCode());
    }
  }

  @Test
  public void testTypeToken_setOfGenericType() {
    TypeToken<Set<List<String>>> token = new TypeToken<Set<List<String>>>() {};
    assertThat(token.getRawType()).isEqualTo(Set.class);
    Type type = token.getType();
    assertThat(type).isInstanceOf(ParameterizedType.class);
    ParameterizedType pType = (ParameterizedType) type;
    Type innerType = pType.getActualTypeArguments()[0];
    assertThat(innerType).isInstanceOf(ParameterizedType.class);
    ParameterizedType innerPType = (ParameterizedType) innerType;
    assertThat(innerPType.getRawType()).isEqualTo(List.class);
  }

  @Test
  public void testGetParameterized_hashMapType() {
    TypeToken<?> token = TypeToken.getParameterized(HashMap.class, String.class, Object.class);
    assertThat(token.getRawType()).isEqualTo(HashMap.class);
    Type type = token.getType();
    assertThat(type).isInstanceOf(ParameterizedType.class);
    ParameterizedType pType = (ParameterizedType) type;
    assertThat(pType.getActualTypeArguments()).hasLength(2);
    assertThat(pType.getActualTypeArguments()[0]).isEqualTo(String.class);
    assertThat(pType.getActualTypeArguments()[1]).isEqualTo(Object.class);
  }

  @Test
  public void testIsAssignableFromType_classArray() {
    TypeToken<String[]> token = new TypeToken<String[]>() {};
    assertThat(token.isAssignableFrom(String[].class)).isTrue();
    assertThat(token.isAssignableFrom(Object[].class)).isFalse();
  }

  @Test
  public void testIsAssignableFromType_genericArrayFromClassArray() {
    TypeToken<List<String>[]> token = new TypeToken<List<String>[]>() {};
    // An array of raw List - the isAssignableFrom check is strict about type parameters
    // so List[] (raw) is not considered assignable to List<String>[]
    // but the same generic type should be assignable
    Type listStringArrayType = new TypeToken<List<String>[]>() {}.getType();
    assertThat(token.isAssignableFrom(listStringArrayType)).isTrue();
  }

  @Test
  public void testTypeToken_primitiveArrayTypes() {
    TypeToken<int[]> intArrayToken = new TypeToken<int[]>() {};
    assertThat(intArrayToken.getRawType()).isEqualTo(int[].class);

    TypeToken<double[]> doubleArrayToken = new TypeToken<double[]>() {};
    assertThat(doubleArrayToken.getRawType()).isEqualTo(double[].class);

    TypeToken<boolean[]> boolArrayToken = new TypeToken<boolean[]>() {};
    assertThat(boolArrayToken.getRawType()).isEqualTo(boolean[].class);
  }

  @Test
  public void testGetParameterized_withWildcardBound() {
    // Create Map<String, ? extends Number> programmatically
    Type wildcardType = new TypeToken<List<? extends Number>>() {}.getType();
    ParameterizedType pType = (ParameterizedType) wildcardType;
    Type wildcardArg = pType.getActualTypeArguments()[0];

    // Use the wildcard type as an argument to another parameterized type
    TypeToken<?> token = TypeToken.getParameterized(List.class, wildcardArg);
    assertThat(token.getRawType()).isEqualTo(List.class);
  }
}
