/*
 * Copyright (C) 2026 Google Inc.
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

import java.lang.reflect.Constructor;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.junit.Test;

public class GsonTypesTest {

  @Test
  public void testConstructorThrowsException() throws Exception {
    Constructor<GsonTypes> constructor = GsonTypes.class.getDeclaredConstructor();
    constructor.setAccessible(true);
    try {
      constructor.newInstance();
      throw new AssertionError("Expected UnsupportedOperationException");
    } catch (InvocationTargetException e) {
      assertThat(e.getCause()).isInstanceOf(UnsupportedOperationException.class);
    }
  }

  @Test
  public void testNewParameterizedTypeWithOwner() {
    ParameterizedType type = GsonTypes.newParameterizedTypeWithOwner(
        null, List.class, String.class);
    assertThat(type).isNotNull();
    assertThat(type.getRawType()).isEqualTo(List.class);
    assertThat(type.getActualTypeArguments()).hasLength(1);
    assertThat(type.getActualTypeArguments()[0]).isEqualTo(String.class);
  }

  @Test
  public void testArrayOf() {
    GenericArrayType arrayType = GsonTypes.arrayOf(String.class);
    assertThat(arrayType).isNotNull();
    assertThat(arrayType.getGenericComponentType()).isEqualTo(String.class);
  }

  @Test
  public void testSubtypeOfClass() {
    WildcardType wildcardType = GsonTypes.subtypeOf(CharSequence.class);
    assertThat(wildcardType).isNotNull();
    assertThat(wildcardType.getUpperBounds()).hasLength(1);
    assertThat(wildcardType.getUpperBounds()[0]).isEqualTo(CharSequence.class);
    assertThat(wildcardType.getLowerBounds()).hasLength(0);
  }

  @Test
  public void testSubtypeOfWildcard() {
    WildcardType original = GsonTypes.subtypeOf(String.class);
    WildcardType nested = GsonTypes.subtypeOf(original);
    assertThat(nested).isNotNull();
    assertThat(nested.getUpperBounds()).hasLength(1);
  }

  @Test
  public void testSupertypeOfClass() {
    WildcardType wildcardType = GsonTypes.supertypeOf(String.class);
    assertThat(wildcardType).isNotNull();
    assertThat(wildcardType.getUpperBounds()).hasLength(1);
    assertThat(wildcardType.getUpperBounds()[0]).isEqualTo(Object.class);
    assertThat(wildcardType.getLowerBounds()).hasLength(1);
    assertThat(wildcardType.getLowerBounds()[0]).isEqualTo(String.class);
  }

  @Test
  public void testSupertypeOfWildcard() {
    WildcardType original = GsonTypes.supertypeOf(String.class);
    WildcardType nested = GsonTypes.supertypeOf(original);
    assertThat(nested).isNotNull();
    assertThat(nested.getLowerBounds()).hasLength(1);
  }

  @Test
  public void testCanonicalizeClass() {
    Type result = GsonTypes.canonicalize(String.class);
    assertThat(result).isEqualTo(String.class);
  }

  @Test
  public void testCanonicalizeArrayClass() {
    Type result = GsonTypes.canonicalize(String[].class);
    assertThat(result).isInstanceOf(GenericArrayType.class);
  }

  @Test
  public void testCanonicalizeParameterizedType() {
    ParameterizedType original = GsonTypes.newParameterizedTypeWithOwner(
        null, List.class, String.class);
    Type result = GsonTypes.canonicalize(original);
    assertThat(result).isInstanceOf(ParameterizedType.class);
  }

  @Test
  public void testCanonicalizeGenericArrayType() {
    GenericArrayType original = GsonTypes.arrayOf(String.class);
    Type result = GsonTypes.canonicalize(original);
    assertThat(result).isInstanceOf(GenericArrayType.class);
  }

  @Test
  public void testCanonicalizeWildcardType() {
    WildcardType original = GsonTypes.subtypeOf(String.class);
    Type result = GsonTypes.canonicalize(original);
    assertThat(result).isInstanceOf(WildcardType.class);
  }

  @Test
  public void testCanonicalizeOtherType() {
    TypeVariable<?>[] typeParams = List.class.getTypeParameters();
    Type result = GsonTypes.canonicalize(typeParams[0]);
    assertThat(result).isEqualTo(typeParams[0]);
  }

  @Test
  public void testGetRawTypeForClass() {
    Class<?> result = GsonTypes.getRawType(String.class);
    assertThat(result).isEqualTo(String.class);
  }

  @Test
  public void testGetRawTypeForParameterizedType() {
    ParameterizedType type = GsonTypes.newParameterizedTypeWithOwner(
        null, List.class, String.class);
    Class<?> result = GsonTypes.getRawType(type);
    assertThat(result).isEqualTo(List.class);
  }

  @Test
  public void testGetRawTypeForGenericArrayType() {
    GenericArrayType arrayType = GsonTypes.arrayOf(String.class);
    Class<?> result = GsonTypes.getRawType(arrayType);
    assertThat(result.isArray()).isTrue();
    assertThat(result.getComponentType()).isEqualTo(String.class);
  }

  @Test
  public void testGetRawTypeForTypeVariable() {
    TypeVariable<?>[] typeParams = List.class.getTypeParameters();
    Class<?> result = GsonTypes.getRawType(typeParams[0]);
    assertThat(result).isEqualTo(Object.class);
  }

  @Test
  public void testGetRawTypeForWildcardType() {
    WildcardType wildcardType = GsonTypes.subtypeOf(String.class);
    Class<?> result = GsonTypes.getRawType(wildcardType);
    assertThat(result).isEqualTo(String.class);
  }

  @Test
  public void testGetRawTypeForUnsupportedType() {
    try {
      GsonTypes.getRawType(null);
      throw new AssertionError("Expected IllegalArgumentException");
    } catch (IllegalArgumentException expected) {
      assertThat(expected.getMessage()).contains("Expected a Class");
    }
  }

  @Test
  public void testEqualsWithSameTypes() {
    boolean result = GsonTypes.equals(String.class, String.class);
    assertThat(result).isTrue();
  }

  @Test
  public void testEqualsWithNullTypes() {
    boolean result = GsonTypes.equals(null, null);
    assertThat(result).isTrue();
  }

  @Test
  public void testEqualsWithClass() {
    boolean result = GsonTypes.equals(String.class, Integer.class);
    assertThat(result).isFalse();
  }

  @Test
  public void testEqualsWithParameterizedTypes() {
    ParameterizedType type1 = GsonTypes.newParameterizedTypeWithOwner(
        null, List.class, String.class);
    ParameterizedType type2 = GsonTypes.newParameterizedTypeWithOwner(
        null, List.class, String.class);
    boolean result = GsonTypes.equals(type1, type2);
    assertThat(result).isTrue();
  }

  @Test
  public void testEqualsWithDifferentParameterizedTypes() {
    ParameterizedType type1 = GsonTypes.newParameterizedTypeWithOwner(
        null, List.class, String.class);
    ParameterizedType type2 = GsonTypes.newParameterizedTypeWithOwner(
        null, List.class, Integer.class);
    boolean result = GsonTypes.equals(type1, type2);
    assertThat(result).isFalse();
  }

  @Test
  public void testEqualsWithGenericArrayTypes() {
    GenericArrayType type1 = GsonTypes.arrayOf(String.class);
    GenericArrayType type2 = GsonTypes.arrayOf(String.class);
    boolean result = GsonTypes.equals(type1, type2);
    assertThat(result).isTrue();
  }

  @Test
  public void testEqualsWithDifferentGenericArrayTypes() {
    GenericArrayType type1 = GsonTypes.arrayOf(String.class);
    GenericArrayType type2 = GsonTypes.arrayOf(Integer.class);
    boolean result = GsonTypes.equals(type1, type2);
    assertThat(result).isFalse();
  }

  @Test
  public void testEqualsWithWildcardTypes() {
    WildcardType type1 = GsonTypes.subtypeOf(String.class);
    WildcardType type2 = GsonTypes.subtypeOf(String.class);
    boolean result = GsonTypes.equals(type1, type2);
    assertThat(result).isTrue();
  }

  @Test
  public void testEqualsWithDifferentWildcardTypes() {
    WildcardType type1 = GsonTypes.subtypeOf(String.class);
    WildcardType type2 = GsonTypes.supertypeOf(String.class);
    boolean result = GsonTypes.equals(type1, type2);
    assertThat(result).isFalse();
  }

  @Test
  public void testEqualsWithTypeVariables() {
    TypeVariable<?>[] typeParams1 = List.class.getTypeParameters();
    TypeVariable<?>[] typeParams2 = List.class.getTypeParameters();
    boolean result = GsonTypes.equals(typeParams1[0], typeParams2[0]);
    assertThat(result).isTrue();
  }

  @Test
  public void testEqualsWithDifferentTypeVariables() {
    TypeVariable<?>[] typeParams1 = List.class.getTypeParameters();
    TypeVariable<?>[] typeParams2 = Map.class.getTypeParameters();
    boolean result = GsonTypes.equals(typeParams1[0], typeParams2[0]);
    assertThat(result).isFalse();
  }

  @Test
  public void testEqualsWithMismatchedTypes() {
    boolean result = GsonTypes.equals(String.class, GsonTypes.arrayOf(String.class));
    assertThat(result).isFalse();
  }

  @Test
  public void testTypeToStringForClass() {
    String result = GsonTypes.typeToString(String.class);
    assertThat(result).isEqualTo("java.lang.String");
  }

  @Test
  public void testTypeToStringForParameterizedType() {
    ParameterizedType type = GsonTypes.newParameterizedTypeWithOwner(
        null, List.class, String.class);
    String result = GsonTypes.typeToString(type);
    assertThat(result).contains("java.util.List");
  }

  @Test
  public void testGetArrayComponentTypeForGenericArray() {
    GenericArrayType arrayType = GsonTypes.arrayOf(String.class);
    Type componentType = GsonTypes.getArrayComponentType(arrayType);
    assertThat(componentType).isEqualTo(String.class);
  }

  @Test
  public void testGetArrayComponentTypeForClassArray() {
    Type componentType = GsonTypes.getArrayComponentType(String[].class);
    assertThat(componentType).isEqualTo(String.class);
  }

  @Test
  public void testGetCollectionElementType() {
    Type elementType = GsonTypes.getCollectionElementType(
        StringList.class, StringList.class);
    assertThat(elementType).isEqualTo(String.class);
  }

  @Test
  public void testGetCollectionElementTypeForRawType() {
    Type elementType = GsonTypes.getCollectionElementType(
        ArrayList.class, ArrayList.class);
    assertThat(elementType).isInstanceOf(TypeVariable.class);
  }

  @Test
  public void testGetMapKeyAndValueTypesForProperties() {
    Type[] types = GsonTypes.getMapKeyAndValueTypes(Properties.class, Properties.class);
    assertThat(types).hasLength(2);
    assertThat(types[0]).isEqualTo(String.class);
    assertThat(types[1]).isEqualTo(String.class);
  }

  @Test
  public void testGetMapKeyAndValueTypesForParameterizedMap() {
    Type[] types = GsonTypes.getMapKeyAndValueTypes(StringMap.class, StringMap.class);
    assertThat(types).hasLength(2);
    assertThat(types[0]).isEqualTo(String.class);
    assertThat(types[1]).isEqualTo(Integer.class);
  }

  @Test
  public void testGetMapKeyAndValueTypesForRawMap() {
    Type[] types = GsonTypes.getMapKeyAndValueTypes(HashMap.class, HashMap.class);
    assertThat(types).hasLength(2);
    assertThat(types[0]).isInstanceOf(TypeVariable.class);
    assertThat(types[1]).isInstanceOf(TypeVariable.class);
  }

  @Test
  public void testResolveWithTypeVariable() {
    Type resolved = GsonTypes.resolve(
        StringList.class, StringList.class, List.class.getTypeParameters()[0]);
    assertThat(resolved).isEqualTo(String.class);
  }

  @Test
  public void testResolveWithClassArray() {
    Type resolved = GsonTypes.resolve(String.class, String.class, String[].class);
    assertThat(resolved).isEqualTo(String[].class);
  }

  @Test
  public void testResolveWithGenericArrayType() {
    GenericArrayType arrayType = GsonTypes.arrayOf(List.class.getTypeParameters()[0]);
    Type resolved = GsonTypes.resolve(StringList.class, StringList.class, arrayType);
    assertThat(resolved).isInstanceOf(GenericArrayType.class);
  }

  @Test
  public void testResolveWithParameterizedType() {
    ParameterizedType listOfT = GsonTypes.newParameterizedTypeWithOwner(
        null, List.class, List.class.getTypeParameters()[0]);
    Type resolved = GsonTypes.resolve(StringList.class, StringList.class, listOfT);
    assertThat(resolved).isInstanceOf(ParameterizedType.class);
  }

  @Test
  public void testResolveWithWildcardTypeLowerBound() {
    WildcardType wildcardType = GsonTypes.supertypeOf(List.class.getTypeParameters()[0]);
    Type resolved = GsonTypes.resolve(StringList.class, StringList.class, wildcardType);
    assertThat(resolved).isInstanceOf(WildcardType.class);
  }

  @Test
  public void testResolveWithWildcardTypeUpperBound() {
    WildcardType wildcardType = GsonTypes.subtypeOf(List.class.getTypeParameters()[0]);
    Type resolved = GsonTypes.resolve(StringList.class, StringList.class, wildcardType);
    assertThat(resolved).isInstanceOf(WildcardType.class);
  }

  @Test
  public void testResolveWithRecursiveTypeVariable() {
    Type resolved = GsonTypes.resolve(
        RecursiveType.class, RecursiveType.class,
        Comparable.class.getTypeParameters()[0]);
    assertThat(resolved).isNotNull();
  }

  @Test
  public void testCheckNotPrimitiveWithNonPrimitive() {
    GsonTypes.checkNotPrimitive(String.class);
  }

  @Test
  public void testCheckNotPrimitiveWithPrimitive() {
    try {
      GsonTypes.checkNotPrimitive(int.class);
      throw new AssertionError("Expected IllegalArgumentException");
    } catch (IllegalArgumentException expected) {
      assertThat(expected.getMessage()).contains("Primitive type is not allowed");
    }
  }

  @Test
  public void testRequiresOwnerTypeForStaticClass() {
    boolean result = GsonTypes.requiresOwnerType(StaticNestedClass.class);
    assertThat(result).isFalse();
  }

  @Test
  public void testRequiresOwnerTypeForInnerClass() {
    boolean result = GsonTypes.requiresOwnerType(InnerClass.class);
    assertThat(result).isTrue();
  }

  @Test
  public void testRequiresOwnerTypeForTopLevelClass() {
    boolean result = GsonTypes.requiresOwnerType(String.class);
    assertThat(result).isFalse();
  }

  @Test
  public void testRequiresOwnerTypeForNonClass() {
    ParameterizedType type = GsonTypes.newParameterizedTypeWithOwner(
        null, List.class, String.class);
    boolean result = GsonTypes.requiresOwnerType(type);
    assertThat(result).isFalse();
  }

  static class StringList extends ArrayList<String> {
    private static final long serialVersionUID = 1L;
  }

  static class StringMap extends HashMap<String, Integer> {
    private static final long serialVersionUID = 1L;
  }

  static class RecursiveType<T extends Comparable<T>> {}

  static class StaticNestedClass {}

  class InnerClass {}
}
