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

package com.google.gson.internal;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import org.junit.Test;

/**
 * Unit tests for {@link GsonTypes}.
 *
 * @author Claude
 */
public class GsonTypesClaudeTest {

  // ========== newParameterizedTypeWithOwner Tests ==========

  @Test
  public void testNewParameterizedTypeWithOwner_SimpleGenericClass() {
    ParameterizedType type = GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    assertThat(type.getRawType()).isEqualTo(List.class);
    assertThat(type.getActualTypeArguments()).asList().containsExactly(String.class);
    assertThat(type.getOwnerType()).isNull();
  }

  @Test
  public void testNewParameterizedTypeWithOwner_MultipleTypeArguments() {
    ParameterizedType type = GsonTypes.newParameterizedTypeWithOwner(null, Map.class, String.class, Integer.class);
    assertThat(type.getRawType()).isEqualTo(Map.class);
    assertThat(type.getActualTypeArguments()).asList().containsExactly(String.class, Integer.class).inOrder();
  }

  @Test
  public void testNewParameterizedTypeWithOwner_NestedParameterizedTypes() {
    ParameterizedType innerType = GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    ParameterizedType outerType = GsonTypes.newParameterizedTypeWithOwner(null, List.class, innerType);
    assertThat(outerType.getRawType()).isEqualTo(List.class);
    Type[] typeArgs = outerType.getActualTypeArguments();
    assertThat(typeArgs).hasLength(1);
    assertThat(typeArgs[0]).isInstanceOf(ParameterizedType.class);
    ParameterizedType nestedType = (ParameterizedType) typeArgs[0];
    assertThat(nestedType.getRawType()).isEqualTo(List.class);
  }

  @Test
  public void testNewParameterizedTypeWithOwner_WithOwnerType() {
    ParameterizedType type = GsonTypes.newParameterizedTypeWithOwner(
        OuterClass.class, OuterClass.InnerGenericClass.class, String.class);
    assertThat(type.getOwnerType()).isEqualTo(OuterClass.class);
    assertThat(type.getRawType()).isEqualTo(OuterClass.InnerGenericClass.class);
  }

  @Test
  public void testNewParameterizedTypeWithOwner_NullRawTypeThrows() {
    assertThrows(NullPointerException.class, () ->
        GsonTypes.newParameterizedTypeWithOwner(null, null, String.class));
  }

  @Test
  public void testNewParameterizedTypeWithOwner_NullTypeArgumentThrows() {
    assertThrows(NullPointerException.class, () ->
        GsonTypes.newParameterizedTypeWithOwner(null, List.class, (Type) null));
  }

  @Test
  public void testNewParameterizedTypeWithOwner_PrimitiveTypeArgumentThrows() {
    assertThrows(IllegalArgumentException.class, () ->
        GsonTypes.newParameterizedTypeWithOwner(null, List.class, int.class));
  }

  @Test
  public void testNewParameterizedTypeWithOwner_RequiresOwnerTypeThrows() {
    // Non-static inner class requires owner type
    assertThrows(IllegalArgumentException.class, () ->
        GsonTypes.newParameterizedTypeWithOwner(null, OuterClass.InnerGenericClass.class, String.class));
  }

  @Test
  public void testNewParameterizedTypeWithOwner_StaticNestedClassNoOwnerRequired() {
    // Static nested class doesn't require owner type
    ParameterizedType type = GsonTypes.newParameterizedTypeWithOwner(
        null, OuterClass.StaticNestedGenericClass.class, String.class);
    assertThat(type.getOwnerType()).isNull();
    assertThat(type.getRawType()).isEqualTo(OuterClass.StaticNestedGenericClass.class);
  }

  @Test
  public void testNewParameterizedTypeWithOwner_Serializable() throws Exception {
    ParameterizedType type = GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    assertThat(type).isInstanceOf(java.io.Serializable.class);
  }

  // ========== arrayOf Tests ==========

  @Test
  public void testArrayOf_SimpleType() {
    GenericArrayType arrayType = GsonTypes.arrayOf(String.class);
    assertThat(arrayType.getGenericComponentType()).isEqualTo(String.class);
  }

  @Test
  public void testArrayOf_ParameterizedType() {
    ParameterizedType listOfString = GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    GenericArrayType arrayType = GsonTypes.arrayOf(listOfString);
    Type componentType = arrayType.getGenericComponentType();
    assertThat(componentType).isInstanceOf(ParameterizedType.class);
    ParameterizedType parameterized = (ParameterizedType) componentType;
    assertThat(parameterized.getRawType()).isEqualTo(List.class);
  }

  @Test
  public void testArrayOf_GenericArrayType() {
    // Array of arrays
    GenericArrayType innerArray = GsonTypes.arrayOf(String.class);
    GenericArrayType outerArray = GsonTypes.arrayOf(innerArray);
    Type componentType = outerArray.getGenericComponentType();
    assertThat(componentType).isInstanceOf(GenericArrayType.class);
  }

  @Test
  public void testArrayOf_WildcardType() {
    WildcardType wildcard = GsonTypes.subtypeOf(Number.class);
    GenericArrayType arrayType = GsonTypes.arrayOf(wildcard);
    assertThat(arrayType.getGenericComponentType()).isInstanceOf(WildcardType.class);
  }

  @Test
  public void testArrayOf_NullComponentTypeThrows() {
    assertThrows(NullPointerException.class, () -> GsonTypes.arrayOf(null));
  }

  @Test
  public void testArrayOf_Serializable() {
    GenericArrayType arrayType = GsonTypes.arrayOf(String.class);
    assertThat(arrayType).isInstanceOf(java.io.Serializable.class);
  }

  @Test
  public void testArrayOf_ToString() {
    GenericArrayType arrayType = GsonTypes.arrayOf(String.class);
    assertThat(arrayType.toString()).isEqualTo("java.lang.String[]");
  }

  // ========== subtypeOf Tests ==========

  @Test
  public void testSubtypeOf_SimpleType() {
    WildcardType wildcard = GsonTypes.subtypeOf(Number.class);
    assertThat(wildcard.getUpperBounds()).asList().containsExactly(Number.class);
    assertThat(wildcard.getLowerBounds()).isEmpty();
  }

  @Test
  public void testSubtypeOf_ObjectType() {
    WildcardType wildcard = GsonTypes.subtypeOf(Object.class);
    assertThat(wildcard.getUpperBounds()).asList().containsExactly(Object.class);
    assertThat(wildcard.getLowerBounds()).isEmpty();
  }

  @Test
  public void testSubtypeOf_WildcardTypeWithUpperBounds() {
    // When bound is already a WildcardType, should extract its upper bounds
    WildcardType innerWildcard = GsonTypes.subtypeOf(CharSequence.class);
    WildcardType outerWildcard = GsonTypes.subtypeOf(innerWildcard);
    // Should extract the upper bound from the inner wildcard
    assertThat(outerWildcard.getUpperBounds()).asList().containsExactly(CharSequence.class);
  }

  @Test
  public void testSubtypeOf_ToString() {
    WildcardType wildcard = GsonTypes.subtypeOf(Number.class);
    assertThat(wildcard.toString()).isEqualTo("? extends java.lang.Number");
  }

  @Test
  public void testSubtypeOf_ToStringWithObject() {
    WildcardType wildcard = GsonTypes.subtypeOf(Object.class);
    assertThat(wildcard.toString()).isEqualTo("?");
  }

  // ========== supertypeOf Tests ==========

  @Test
  public void testSupertypeOf_SimpleType() {
    WildcardType wildcard = GsonTypes.supertypeOf(String.class);
    assertThat(wildcard.getUpperBounds()).asList().containsExactly(Object.class);
    assertThat(wildcard.getLowerBounds()).asList().containsExactly(String.class);
  }

  @Test
  public void testSupertypeOf_WildcardTypeWithLowerBounds() {
    // When bound is already a WildcardType with lower bounds, should extract them
    WildcardType innerWildcard = GsonTypes.supertypeOf(String.class);
    WildcardType outerWildcard = GsonTypes.supertypeOf(innerWildcard);
    // Should extract the lower bound from the inner wildcard
    assertThat(outerWildcard.getLowerBounds()).asList().containsExactly(String.class);
    assertThat(outerWildcard.getUpperBounds()).asList().containsExactly(Object.class);
  }

  @Test
  public void testSupertypeOf_ToString() {
    WildcardType wildcard = GsonTypes.supertypeOf(String.class);
    assertThat(wildcard.toString()).isEqualTo("? super java.lang.String");
  }

  // ========== canonicalize Tests ==========

  @Test
  public void testCanonicalize_ClassType() {
    Type canonical = GsonTypes.canonicalize(String.class);
    assertThat(canonical).isEqualTo(String.class);
  }

  @Test
  public void testCanonicalize_ArrayClass() {
    Type canonical = GsonTypes.canonicalize(String[].class);
    // Array class should be converted to GenericArrayType
    assertThat(canonical).isInstanceOf(GenericArrayType.class);
    GenericArrayType arrayType = (GenericArrayType) canonical;
    assertThat(arrayType.getGenericComponentType()).isEqualTo(String.class);
  }

  @Test
  public void testCanonicalize_MultiDimensionalArray() {
    Type canonical = GsonTypes.canonicalize(String[][].class);
    // Should create nested GenericArrayTypes
    assertThat(canonical).isInstanceOf(GenericArrayType.class);
    GenericArrayType outerArray = (GenericArrayType) canonical;
    Type componentType = outerArray.getGenericComponentType();
    assertThat(componentType).isInstanceOf(GenericArrayType.class);
    GenericArrayType innerArray = (GenericArrayType) componentType;
    assertThat(innerArray.getGenericComponentType()).isEqualTo(String.class);
  }

  @Test
  public void testCanonicalize_ParameterizedType() throws NoSuchFieldException {
    // Get a real ParameterizedType from reflection
    Type fieldType = GenericFieldsClass.class.getDeclaredField("listOfStrings").getGenericType();
    Type canonical = GsonTypes.canonicalize(fieldType);
    assertThat(canonical).isInstanceOf(ParameterizedType.class);
    // Should be equal according to GsonTypes.equals
    assertThat(GsonTypes.equals(fieldType, canonical)).isTrue();
  }

  @Test
  public void testCanonicalize_GenericArrayType() throws NoSuchFieldException {
    // Get a real GenericArrayType from reflection
    Type fieldType = GenericFieldsClass.class.getDeclaredField("arrayOfLists").getGenericType();
    Type canonical = GsonTypes.canonicalize(fieldType);
    assertThat(canonical).isInstanceOf(GenericArrayType.class);
    assertThat(GsonTypes.equals(fieldType, canonical)).isTrue();
  }

  @Test
  public void testCanonicalize_WildcardType() throws NoSuchFieldException {
    // Get a real WildcardType from reflection
    Type fieldType = GenericFieldsClass.class.getDeclaredField("listOfExtendsNumber").getGenericType();
    ParameterizedType parameterized = (ParameterizedType) fieldType;
    Type wildcardType = parameterized.getActualTypeArguments()[0];
    Type canonical = GsonTypes.canonicalize(wildcardType);
    assertThat(canonical).isInstanceOf(WildcardType.class);
    assertThat(GsonTypes.equals(wildcardType, canonical)).isTrue();
  }

  @Test
  public void testCanonicalize_TypeVariablePassThrough() throws NoSuchFieldException {
    // TypeVariables should pass through as-is
    TypeVariable<?>[] typeParams = GenericClass.class.getTypeParameters();
    Type canonical = GsonTypes.canonicalize(typeParams[0]);
    // Type variables are returned as-is
    assertThat(canonical).isEqualTo(typeParams[0]);
  }

  @Test
  public void testCanonicalize_IsSerializable() throws NoSuchFieldException {
    Type fieldType = GenericFieldsClass.class.getDeclaredField("listOfStrings").getGenericType();
    Type canonical = GsonTypes.canonicalize(fieldType);
    assertThat(canonical).isInstanceOf(java.io.Serializable.class);
  }

  // ========== getRawType Tests ==========

  @Test
  public void testGetRawType_Class() {
    Class<?> rawType = GsonTypes.getRawType(String.class);
    assertThat(rawType).isEqualTo(String.class);
  }

  @Test
  public void testGetRawType_ParameterizedType() {
    ParameterizedType type = GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    Class<?> rawType = GsonTypes.getRawType(type);
    assertThat(rawType).isEqualTo(List.class);
  }

  @Test
  public void testGetRawType_GenericArrayType() {
    GenericArrayType arrayType = GsonTypes.arrayOf(String.class);
    Class<?> rawType = GsonTypes.getRawType(arrayType);
    assertThat(rawType).isEqualTo(String[].class);
  }

  @Test
  public void testGetRawType_GenericArrayOfParameterizedType() {
    ParameterizedType listOfString = GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    GenericArrayType arrayType = GsonTypes.arrayOf(listOfString);
    Class<?> rawType = GsonTypes.getRawType(arrayType);
    assertThat(rawType).isEqualTo(List[].class);
  }

  @Test
  public void testGetRawType_TypeVariable() {
    TypeVariable<?>[] typeParams = GenericClass.class.getTypeParameters();
    Class<?> rawType = GsonTypes.getRawType(typeParams[0]);
    // Type variables return Object.class
    assertThat(rawType).isEqualTo(Object.class);
  }

  @Test
  public void testGetRawType_WildcardTypeExtendsNumber() {
    WildcardType wildcard = GsonTypes.subtypeOf(Number.class);
    Class<?> rawType = GsonTypes.getRawType(wildcard);
    assertThat(rawType).isEqualTo(Number.class);
  }

  @Test
  public void testGetRawType_WildcardTypeSuperString() {
    WildcardType wildcard = GsonTypes.supertypeOf(String.class);
    Class<?> rawType = GsonTypes.getRawType(wildcard);
    // Super type wildcard returns the upper bound (Object.class)
    assertThat(rawType).isEqualTo(Object.class);
  }

  @Test
  public void testGetRawType_NullThrows() {
    assertThrows(IllegalArgumentException.class, () -> GsonTypes.getRawType(null));
  }

  // ========== equals Tests ==========

  @Test
  public void testEquals_BothNull() {
    assertThat(GsonTypes.equals(null, null)).isTrue();
  }

  @Test
  public void testEquals_SameInstance() {
    Type type = String.class;
    assertThat(GsonTypes.equals(type, type)).isTrue();
  }

  @Test
  public void testEquals_ClassTypes() {
    assertThat(GsonTypes.equals(String.class, String.class)).isTrue();
    assertThat(GsonTypes.equals(String.class, Integer.class)).isFalse();
  }

  @Test
  public void testEquals_ParameterizedTypes() {
    ParameterizedType type1 = GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    ParameterizedType type2 = GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    ParameterizedType type3 = GsonTypes.newParameterizedTypeWithOwner(null, List.class, Integer.class);

    assertThat(GsonTypes.equals(type1, type2)).isTrue();
    assertThat(GsonTypes.equals(type1, type3)).isFalse();
  }

  @Test
  public void testEquals_ParameterizedTypesWithDifferentRawTypes() {
    ParameterizedType type1 = GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    ParameterizedType type2 = GsonTypes.newParameterizedTypeWithOwner(null, Set.class, String.class);
    assertThat(GsonTypes.equals(type1, type2)).isFalse();
  }

  @Test
  public void testEquals_ParameterizedTypesWithDifferentOwnerTypes() {
    ParameterizedType type1 = GsonTypes.newParameterizedTypeWithOwner(
        OuterClass.class, OuterClass.InnerGenericClass.class, String.class);
    ParameterizedType type2 = GsonTypes.newParameterizedTypeWithOwner(
        OuterClass.class, OuterClass.InnerGenericClass.class, String.class);
    assertThat(GsonTypes.equals(type1, type2)).isTrue();
  }

  @Test
  public void testEquals_GenericArrayTypes() {
    GenericArrayType type1 = GsonTypes.arrayOf(String.class);
    GenericArrayType type2 = GsonTypes.arrayOf(String.class);
    GenericArrayType type3 = GsonTypes.arrayOf(Integer.class);

    assertThat(GsonTypes.equals(type1, type2)).isTrue();
    assertThat(GsonTypes.equals(type1, type3)).isFalse();
  }

  @Test
  public void testEquals_WildcardTypes() {
    WildcardType type1 = GsonTypes.subtypeOf(Number.class);
    WildcardType type2 = GsonTypes.subtypeOf(Number.class);
    WildcardType type3 = GsonTypes.subtypeOf(String.class);
    WildcardType type4 = GsonTypes.supertypeOf(String.class);

    assertThat(GsonTypes.equals(type1, type2)).isTrue();
    assertThat(GsonTypes.equals(type1, type3)).isFalse();
    assertThat(GsonTypes.equals(type1, type4)).isFalse();
  }

  @Test
  public void testEquals_TypeVariables() {
    TypeVariable<?>[] typeParams1 = GenericClass.class.getTypeParameters();
    TypeVariable<?>[] typeParams2 = GenericClass.class.getTypeParameters();
    TypeVariable<?>[] typeParams3 = OtherGenericClass.class.getTypeParameters();

    assertThat(GsonTypes.equals(typeParams1[0], typeParams2[0])).isTrue();
    assertThat(GsonTypes.equals(typeParams1[0], typeParams3[0])).isFalse();
  }

  @Test
  public void testEquals_DifferentTypeCategories() {
    Type classType = String.class;
    ParameterizedType parameterizedType = GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    GenericArrayType arrayType = GsonTypes.arrayOf(String.class);
    WildcardType wildcardType = GsonTypes.subtypeOf(String.class);

    assertThat(GsonTypes.equals(classType, parameterizedType)).isFalse();
    assertThat(GsonTypes.equals(parameterizedType, arrayType)).isFalse();
    assertThat(GsonTypes.equals(arrayType, wildcardType)).isFalse();
    assertThat(GsonTypes.equals(wildcardType, classType)).isFalse();
  }

  @Test
  public void testEquals_ParameterizedTypeVsNonParameterized() {
    ParameterizedType parameterized = GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    assertThat(GsonTypes.equals(parameterized, String.class)).isFalse();
    assertThat(GsonTypes.equals(String.class, parameterized)).isFalse();
  }

  @Test
  public void testEquals_GenericArrayVsNonGenericArray() {
    GenericArrayType genericArray = GsonTypes.arrayOf(String.class);
    assertThat(GsonTypes.equals(genericArray, String[].class)).isFalse();
    assertThat(GsonTypes.equals(String[].class, genericArray)).isFalse();
  }

  // ========== typeToString Tests ==========

  @Test
  public void testTypeToString_Class() {
    String result = GsonTypes.typeToString(String.class);
    assertThat(result).isEqualTo("java.lang.String");
  }

  @Test
  public void testTypeToString_PrimitiveClass() {
    String result = GsonTypes.typeToString(int.class);
    assertThat(result).isEqualTo("int");
  }

  @Test
  public void testTypeToString_ArrayClass() {
    String result = GsonTypes.typeToString(String[].class);
    assertThat(result).isEqualTo("[Ljava.lang.String;");
  }

  @Test
  public void testTypeToString_ParameterizedType() {
    ParameterizedType type = GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    String result = GsonTypes.typeToString(type);
    assertThat(result).isEqualTo("java.util.List<java.lang.String>");
  }

  @Test
  public void testTypeToString_GenericArrayType() {
    GenericArrayType type = GsonTypes.arrayOf(String.class);
    String result = GsonTypes.typeToString(type);
    assertThat(result).isEqualTo("java.lang.String[]");
  }

  @Test
  public void testTypeToString_WildcardTypeExtends() {
    WildcardType type = GsonTypes.subtypeOf(Number.class);
    String result = GsonTypes.typeToString(type);
    assertThat(result).isEqualTo("? extends java.lang.Number");
  }

  @Test
  public void testTypeToString_WildcardTypeSuper() {
    WildcardType type = GsonTypes.supertypeOf(String.class);
    String result = GsonTypes.typeToString(type);
    assertThat(result).isEqualTo("? super java.lang.String");
  }

  @Test
  public void testTypeToString_UnboundedWildcard() {
    WildcardType type = GsonTypes.subtypeOf(Object.class);
    String result = GsonTypes.typeToString(type);
    assertThat(result).isEqualTo("?");
  }

  @Test
  public void testTypeToString_NestedParameterizedType() {
    ParameterizedType innerType = GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    ParameterizedType outerType = GsonTypes.newParameterizedTypeWithOwner(null, Map.class, String.class, innerType);
    String result = GsonTypes.typeToString(outerType);
    assertThat(result).isEqualTo("java.util.Map<java.lang.String, java.util.List<java.lang.String>>");
  }

  // ========== getArrayComponentType Tests ==========

  @Test
  public void testGetArrayComponentType_GenericArrayType() {
    GenericArrayType arrayType = GsonTypes.arrayOf(String.class);
    Type componentType = GsonTypes.getArrayComponentType(arrayType);
    assertThat(componentType).isEqualTo(String.class);
  }

  @Test
  public void testGetArrayComponentType_ClassArray() {
    Type componentType = GsonTypes.getArrayComponentType(String[].class);
    assertThat(componentType).isEqualTo(String.class);
  }

  @Test
  public void testGetArrayComponentType_MultiDimensionalArray() {
    Type componentType = GsonTypes.getArrayComponentType(String[][].class);
    assertThat(componentType).isEqualTo(String[].class);
  }

  @Test
  public void testGetArrayComponentType_GenericArrayOfParameterized() {
    ParameterizedType listOfString = GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    GenericArrayType arrayType = GsonTypes.arrayOf(listOfString);
    Type componentType = GsonTypes.getArrayComponentType(arrayType);
    assertThat(GsonTypes.equals(componentType, listOfString)).isTrue();
  }

  @Test
  public void testGetArrayComponentType_NonArrayReturnsNull() {
    // When given a non-array class, getComponentType() returns null
    Type componentType = GsonTypes.getArrayComponentType(String.class);
    assertThat(componentType).isNull();
  }

  // ========== getCollectionElementType Tests ==========

  @Test
  public void testGetCollectionElementType_List() throws NoSuchFieldException {
    Type fieldType = GenericFieldsClass.class.getDeclaredField("listOfStrings").getGenericType();
    Type elementType = GsonTypes.getCollectionElementType(fieldType, ArrayList.class);
    assertThat(elementType).isEqualTo(String.class);
  }

  @Test
  public void testGetCollectionElementType_Set() throws NoSuchFieldException {
    Type fieldType = GenericFieldsClass.class.getDeclaredField("setOfIntegers").getGenericType();
    Type elementType = GsonTypes.getCollectionElementType(fieldType, HashSet.class);
    assertThat(elementType).isEqualTo(Integer.class);
  }

  @Test
  public void testGetCollectionElementType_RawList() {
    // Raw List (no generics) returns the unresolved type variable
    Type elementType = GsonTypes.getCollectionElementType(ArrayList.class, ArrayList.class);
    // For raw types, the type variable is returned unresolved
    assertThat(elementType).isInstanceOf(TypeVariable.class);
  }

  @Test
  public void testGetCollectionElementType_ParameterizedElementType() throws NoSuchFieldException {
    Type fieldType = GenericFieldsClass.class.getDeclaredField("listOfListOfStrings").getGenericType();
    Type elementType = GsonTypes.getCollectionElementType(fieldType, ArrayList.class);
    assertThat(elementType).isInstanceOf(ParameterizedType.class);
    ParameterizedType parameterizedElement = (ParameterizedType) elementType;
    assertThat(parameterizedElement.getRawType()).isEqualTo(List.class);
  }

  @Test
  public void testGetCollectionElementType_NonCollectionThrows() {
    assertThrows(IllegalArgumentException.class, () ->
        GsonTypes.getCollectionElementType(String.class, String.class));
  }

  @Test
  public void testGetCollectionElementType_FromSupertype() {
    // Test a subclass of ArrayList
    ParameterizedType type = GsonTypes.newParameterizedTypeWithOwner(null, StringArrayList.class);
    Type elementType = GsonTypes.getCollectionElementType(type, StringArrayList.class);
    assertThat(elementType).isEqualTo(String.class);
  }

  // ========== getMapKeyAndValueTypes Tests ==========

  @Test
  public void testGetMapKeyAndValueTypes_HashMap() throws NoSuchFieldException {
    Type fieldType = GenericFieldsClass.class.getDeclaredField("mapStringInteger").getGenericType();
    Type[] keyValueTypes = GsonTypes.getMapKeyAndValueTypes(fieldType, HashMap.class);
    assertThat(keyValueTypes).hasLength(2);
    assertThat(keyValueTypes[0]).isEqualTo(String.class);
    assertThat(keyValueTypes[1]).isEqualTo(Integer.class);
  }

  @Test
  public void testGetMapKeyAndValueTypes_LinkedHashMap() {
    ParameterizedType type = GsonTypes.newParameterizedTypeWithOwner(null, LinkedHashMap.class, Long.class, Double.class);
    Type[] keyValueTypes = GsonTypes.getMapKeyAndValueTypes(type, LinkedHashMap.class);
    assertThat(keyValueTypes[0]).isEqualTo(Long.class);
    assertThat(keyValueTypes[1]).isEqualTo(Double.class);
  }

  @Test
  public void testGetMapKeyAndValueTypes_RawMap() {
    // Raw Map returns unresolved type variables
    Type[] keyValueTypes = GsonTypes.getMapKeyAndValueTypes(HashMap.class, HashMap.class);
    // For raw types, the type variables are returned unresolved
    assertThat(keyValueTypes[0]).isInstanceOf(TypeVariable.class);
    assertThat(keyValueTypes[1]).isInstanceOf(TypeVariable.class);
  }

  @Test
  public void testGetMapKeyAndValueTypes_Properties() {
    // Properties is a special case - should return String, String
    Type[] keyValueTypes = GsonTypes.getMapKeyAndValueTypes(Properties.class, Properties.class);
    assertThat(keyValueTypes[0]).isEqualTo(String.class);
    assertThat(keyValueTypes[1]).isEqualTo(String.class);
  }

  @Test
  public void testGetMapKeyAndValueTypes_PropertiesSubclass() {
    // Subclass of Properties should also return String, String
    Type[] keyValueTypes = GsonTypes.getMapKeyAndValueTypes(CustomProperties.class, CustomProperties.class);
    assertThat(keyValueTypes[0]).isEqualTo(String.class);
    assertThat(keyValueTypes[1]).isEqualTo(String.class);
  }

  @Test
  public void testGetMapKeyAndValueTypes_ParameterizedValueType() throws NoSuchFieldException {
    Type fieldType = GenericFieldsClass.class.getDeclaredField("mapWithListValue").getGenericType();
    Type[] keyValueTypes = GsonTypes.getMapKeyAndValueTypes(fieldType, HashMap.class);
    assertThat(keyValueTypes[0]).isEqualTo(String.class);
    assertThat(keyValueTypes[1]).isInstanceOf(ParameterizedType.class);
    ParameterizedType valueType = (ParameterizedType) keyValueTypes[1];
    assertThat(valueType.getRawType()).isEqualTo(List.class);
  }

  @Test
  public void testGetMapKeyAndValueTypes_NonMapThrows() {
    assertThrows(IllegalArgumentException.class, () ->
        GsonTypes.getMapKeyAndValueTypes(String.class, String.class));
  }

  // ========== resolve Tests ==========

  @Test
  public void testResolve_ClassType() {
    Type resolved = GsonTypes.resolve(String.class, String.class, Integer.class);
    assertThat(resolved).isEqualTo(Integer.class);
  }

  @Test
  public void testResolve_TypeVariable() throws NoSuchFieldException {
    // Generic class with a field of type T
    Type fieldType = GenericClass.class.getDeclaredField("value").getGenericType();
    // Resolve in context of StringGenericClass which extends GenericClass<String>
    Type resolved = GsonTypes.resolve(StringGenericClass.class, StringGenericClass.class, fieldType);
    assertThat(resolved).isEqualTo(String.class);
  }

  @Test
  public void testResolve_ParameterizedType() throws NoSuchFieldException {
    // Get a field with generic type List<T>
    Type fieldType = GenericClass.class.getDeclaredField("listOfT").getGenericType();
    // Resolve in context of StringGenericClass
    Type resolved = GsonTypes.resolve(StringGenericClass.class, StringGenericClass.class, fieldType);
    assertThat(resolved).isInstanceOf(ParameterizedType.class);
    ParameterizedType parameterized = (ParameterizedType) resolved;
    assertThat(parameterized.getRawType()).isEqualTo(List.class);
    assertThat(parameterized.getActualTypeArguments()[0]).isEqualTo(String.class);
  }

  @Test
  public void testResolve_GenericArrayType() throws NoSuchFieldException {
    // Get a field with generic array type T[]
    Type fieldType = GenericClass.class.getDeclaredField("arrayOfT").getGenericType();
    // Resolve in context of StringGenericClass
    Type resolved = GsonTypes.resolve(StringGenericClass.class, StringGenericClass.class, fieldType);
    assertThat(resolved).isInstanceOf(GenericArrayType.class);
    GenericArrayType arrayType = (GenericArrayType) resolved;
    assertThat(arrayType.getGenericComponentType()).isEqualTo(String.class);
  }

  @Test
  public void testResolve_ClassArray() throws NoSuchFieldException {
    // Get a field with class array type Object[]
    Type fieldType = GenericClass.class.getDeclaredField("objectArray").getGenericType();
    // Resolve should return as-is since no type variables involved
    Type resolved = GsonTypes.resolve(String.class, String.class, fieldType);
    assertThat(resolved).isEqualTo(Object[].class);
  }

  @Test
  public void testResolve_WildcardTypeWithUpperBound() {
    WildcardType wildcard = GsonTypes.subtypeOf(Number.class);
    // Wildcard with simple bound should remain unchanged
    Type resolved = GsonTypes.resolve(String.class, String.class, wildcard);
    assertThat(GsonTypes.equals(resolved, wildcard)).isTrue();
  }

  @Test
  public void testResolve_WildcardTypeWithLowerBound() {
    WildcardType wildcard = GsonTypes.supertypeOf(String.class);
    // Wildcard with simple bound should remain unchanged
    Type resolved = GsonTypes.resolve(Integer.class, Integer.class, wildcard);
    assertThat(GsonTypes.equals(resolved, wildcard)).isTrue();
  }

  @Test
  public void testResolve_UnresolvedTypeVariable() {
    // Type variable that cannot be resolved
    TypeVariable<?>[] typeParams = GenericClass.class.getTypeParameters();
    // When context doesn't provide type argument, should return the type variable
    Type resolved = GsonTypes.resolve(GenericClass.class, GenericClass.class, typeParams[0]);
    assertThat(resolved).isEqualTo(typeParams[0]);
  }

  @Test
  public void testResolve_NestedParameterizedTypes() throws NoSuchFieldException {
    // Get a field with nested generic type Map<T, List<T>>
    Type fieldType = GenericClass.class.getDeclaredField("mapTListT").getGenericType();
    // Resolve in context of StringGenericClass
    Type resolved = GsonTypes.resolve(StringGenericClass.class, StringGenericClass.class, fieldType);
    assertThat(resolved).isInstanceOf(ParameterizedType.class);
    ParameterizedType mapType = (ParameterizedType) resolved;
    assertThat(mapType.getRawType()).isEqualTo(Map.class);
    Type[] typeArgs = mapType.getActualTypeArguments();
    assertThat(typeArgs[0]).isEqualTo(String.class);
    assertThat(typeArgs[1]).isInstanceOf(ParameterizedType.class);
  }

  // ========== checkNotPrimitive Tests ==========

  @Test
  public void testCheckNotPrimitive_NonPrimitiveDoesNotThrow() {
    // Should not throw for non-primitive types
    GsonTypes.checkNotPrimitive(String.class);
    GsonTypes.checkNotPrimitive(Integer.class);
    GsonTypes.checkNotPrimitive(Object.class);
  }

  @Test
  public void testCheckNotPrimitive_IntThrows() {
    assertThrows(IllegalArgumentException.class, () -> GsonTypes.checkNotPrimitive(int.class));
  }

  @Test
  public void testCheckNotPrimitive_BooleanThrows() {
    assertThrows(IllegalArgumentException.class, () -> GsonTypes.checkNotPrimitive(boolean.class));
  }

  @Test
  public void testCheckNotPrimitive_LongThrows() {
    assertThrows(IllegalArgumentException.class, () -> GsonTypes.checkNotPrimitive(long.class));
  }

  @Test
  public void testCheckNotPrimitive_DoubleThrows() {
    assertThrows(IllegalArgumentException.class, () -> GsonTypes.checkNotPrimitive(double.class));
  }

  @Test
  public void testCheckNotPrimitive_FloatThrows() {
    assertThrows(IllegalArgumentException.class, () -> GsonTypes.checkNotPrimitive(float.class));
  }

  @Test
  public void testCheckNotPrimitive_ShortThrows() {
    assertThrows(IllegalArgumentException.class, () -> GsonTypes.checkNotPrimitive(short.class));
  }

  @Test
  public void testCheckNotPrimitive_ByteThrows() {
    assertThrows(IllegalArgumentException.class, () -> GsonTypes.checkNotPrimitive(byte.class));
  }

  @Test
  public void testCheckNotPrimitive_CharThrows() {
    assertThrows(IllegalArgumentException.class, () -> GsonTypes.checkNotPrimitive(char.class));
  }

  @Test
  public void testCheckNotPrimitive_VoidThrows() {
    assertThrows(IllegalArgumentException.class, () -> GsonTypes.checkNotPrimitive(void.class));
  }

  @Test
  public void testCheckNotPrimitive_ArrayOfPrimitiveDoesNotThrow() {
    // int[] is not a primitive type itself
    GsonTypes.checkNotPrimitive(int[].class);
  }

  @Test
  public void testCheckNotPrimitive_NullDoesNotThrow() {
    // Null is not a primitive
    GsonTypes.checkNotPrimitive(null);
  }

  @Test
  public void testCheckNotPrimitive_ParameterizedTypeDoesNotThrow() {
    ParameterizedType type = GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    GsonTypes.checkNotPrimitive(type);
  }

  // ========== requiresOwnerType Tests ==========

  @Test
  public void testRequiresOwnerType_TopLevelClass() {
    assertThat(GsonTypes.requiresOwnerType(String.class)).isFalse();
    assertThat(GsonTypes.requiresOwnerType(List.class)).isFalse();
  }

  @Test
  public void testRequiresOwnerType_StaticNestedClass() {
    assertThat(GsonTypes.requiresOwnerType(OuterClass.StaticNestedGenericClass.class)).isFalse();
  }

  @Test
  public void testRequiresOwnerType_NonStaticInnerClass() {
    assertThat(GsonTypes.requiresOwnerType(OuterClass.InnerGenericClass.class)).isTrue();
  }

  @Test
  public void testRequiresOwnerType_NonClassType() {
    ParameterizedType type = GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    assertThat(GsonTypes.requiresOwnerType(type)).isFalse();
  }

  @Test
  public void testRequiresOwnerType_LocalClass() {
    class LocalClass {}
    // Local class: getDeclaringClass() returns null (not the enclosing class)
    // so requiresOwnerType returns false
    assertThat(GsonTypes.requiresOwnerType(LocalClass.class)).isFalse();
  }

  @Test
  public void testRequiresOwnerType_AnonymousClass() {
    Runnable anonymous = new Runnable() {
      @Override
      public void run() {}
    };
    // Anonymous classes: getDeclaringClass() returns null (not the enclosing class)
    // so requiresOwnerType returns false
    assertThat(GsonTypes.requiresOwnerType(anonymous.getClass())).isFalse();
  }

  // ========== Additional Integration Tests ==========

  @Test
  public void testHashCodeConsistency() {
    ParameterizedType type1 = GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    ParameterizedType type2 = GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    assertThat(type1.hashCode()).isEqualTo(type2.hashCode());
  }

  @Test
  public void testEqualsSymmetry() {
    ParameterizedType type1 = GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    ParameterizedType type2 = GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    assertThat(GsonTypes.equals(type1, type2)).isTrue();
    assertThat(GsonTypes.equals(type2, type1)).isTrue();
  }

  @Test
  public void testCanonicalizeIdempotent() {
    ParameterizedType type = GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    Type canonical1 = GsonTypes.canonicalize(type);
    Type canonical2 = GsonTypes.canonicalize(canonical1);
    assertThat(GsonTypes.equals(canonical1, canonical2)).isTrue();
  }

  @Test
  public void testWildcardTypeImplEqualsWithReflectionWildcard() throws NoSuchFieldException {
    // Get a real wildcard from reflection
    Type fieldType = GenericFieldsClass.class.getDeclaredField("listOfExtendsNumber").getGenericType();
    ParameterizedType parameterized = (ParameterizedType) fieldType;
    WildcardType reflectionWildcard = (WildcardType) parameterized.getActualTypeArguments()[0];

    // Create an equivalent wildcard using GsonTypes
    WildcardType gsonWildcard = GsonTypes.subtypeOf(Number.class);

    // They should be equal according to GsonTypes.equals
    assertThat(GsonTypes.equals(reflectionWildcard, gsonWildcard)).isTrue();
    assertThat(GsonTypes.equals(gsonWildcard, reflectionWildcard)).isTrue();
  }

  @Test
  public void testParameterizedTypeToStringWithNoTypeArguments() {
    // ParameterizedType with no type arguments
    ParameterizedType type = GsonTypes.newParameterizedTypeWithOwner(
        OuterClass.class, OuterClass.InnerGenericClass.class);
    String result = type.toString();
    // Should just show the raw type name without <>
    assertThat(result).contains("InnerGenericClass");
    assertThat(result).doesNotContain("<>");
  }

  // ========== Helper Classes ==========

  @SuppressWarnings("unused")
  static class GenericFieldsClass {
    List<String> listOfStrings;
    Set<Integer> setOfIntegers;
    List<? extends Number> listOfExtendsNumber;
    List<String>[] arrayOfLists;
    List<List<String>> listOfListOfStrings;
    Map<String, Integer> mapStringInteger;
    Map<String, List<Integer>> mapWithListValue;
  }

  @SuppressWarnings("unused")
  static class GenericClass<T> {
    T value;
    List<T> listOfT;
    T[] arrayOfT;
    Object[] objectArray;
    Map<T, List<T>> mapTListT;
  }

  static class StringGenericClass extends GenericClass<String> {}

  @SuppressWarnings("unused")
  static class OtherGenericClass<T> {
    T value;
  }

  @SuppressWarnings("unused")
  static class OuterClass {
    class InnerGenericClass<T> {}
    static class StaticNestedGenericClass<T> {}
  }

  static class StringArrayList extends ArrayList<String> {
    private static final long serialVersionUID = 1L;
  }

  static class CustomProperties extends Properties {
    private static final long serialVersionUID = 1L;
  }
}
