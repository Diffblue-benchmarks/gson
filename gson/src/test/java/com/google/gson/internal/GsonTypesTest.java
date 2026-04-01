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
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.junit.Test;

public final class GsonTypesTest {

  @Test
  public void testConstructorThrowsUnsupportedOperation() throws Exception {
    java.lang.reflect.Constructor<GsonTypes> constructor =
        GsonTypes.class.getDeclaredConstructor();
    constructor.setAccessible(true);
    try {
      constructor.newInstance();
    } catch (java.lang.reflect.InvocationTargetException e) {
      assertThat(e.getCause()).isInstanceOf(UnsupportedOperationException.class);
    }
  }

  @Test
  public void testNewParameterizedTypeWithOwner() {
    ParameterizedType type =
        GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    assertThat(type.getRawType()).isEqualTo(List.class);
    assertThat(type.getActualTypeArguments()).asList().containsExactly(String.class);
    assertThat(type.getOwnerType()).isNull();
  }

  @Test
  public void testNewParameterizedTypeWithOwnerNonNull() {
    ParameterizedType type =
        GsonTypes.newParameterizedTypeWithOwner(
            Map.class, Map.Entry.class, String.class, Integer.class);
    assertThat(type.getRawType()).isEqualTo(Map.Entry.class);
    assertThat(type.getActualTypeArguments()).asList().containsExactly(String.class, Integer.class);
  }

  @Test
  public void testArrayOf() {
    GenericArrayType arrayType = GsonTypes.arrayOf(String.class);
    assertThat(arrayType.getGenericComponentType()).isEqualTo(String.class);
  }

  @Test
  public void testSubtypeOf() {
    WildcardType wildcard = GsonTypes.subtypeOf(String.class);
    assertThat(wildcard.getUpperBounds()).asList().containsExactly(String.class);
    assertThat(wildcard.getLowerBounds()).isEmpty();
  }

  @Test
  public void testSubtypeOfWildcard() {
    WildcardType inner = GsonTypes.subtypeOf(CharSequence.class);
    WildcardType outer = GsonTypes.subtypeOf(inner);
    assertThat(outer.getUpperBounds()).asList().containsExactly(CharSequence.class);
  }

  @Test
  public void testSupertypeOf() {
    WildcardType wildcard = GsonTypes.supertypeOf(String.class);
    assertThat(wildcard.getLowerBounds()).asList().containsExactly(String.class);
    assertThat(wildcard.getUpperBounds()).asList().containsExactly(Object.class);
  }

  @Test
  public void testSupertypeOfWildcard() {
    WildcardType inner = GsonTypes.supertypeOf(String.class);
    WildcardType outer = GsonTypes.supertypeOf(inner);
    assertThat(outer.getLowerBounds()).asList().containsExactly(String.class);
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
    assertThat(((GenericArrayType) result).getGenericComponentType()).isEqualTo(String.class);
  }

  @Test
  public void testCanonicalizeParameterizedType() throws Exception {
    Type listStringType = new ArrayList<String>() {}.getClass().getGenericSuperclass();
    Type result = GsonTypes.canonicalize(listStringType);
    assertThat(result).isInstanceOf(ParameterizedType.class);
    assertThat(((ParameterizedType) result).getRawType()).isEqualTo(ArrayList.class);
  }

  @Test
  public void testCanonicalizeGenericArrayType() {
    GenericArrayType arrayType = GsonTypes.arrayOf(String.class);
    Type result = GsonTypes.canonicalize(arrayType);
    assertThat(result).isInstanceOf(GenericArrayType.class);
    assertThat(((GenericArrayType) result).getGenericComponentType()).isEqualTo(String.class);
  }

  @Test
  public void testCanonicalizeWildcardType() {
    WildcardType wildcard = GsonTypes.subtypeOf(String.class);
    Type result = GsonTypes.canonicalize(wildcard);
    assertThat(result).isInstanceOf(WildcardType.class);
  }

  @Test
  public void testGetRawTypeClass() {
    assertThat(GsonTypes.getRawType(String.class)).isEqualTo(String.class);
  }

  @Test
  public void testGetRawTypeParameterizedType() {
    ParameterizedType pt = GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    assertThat(GsonTypes.getRawType(pt)).isEqualTo(List.class);
  }

  @Test
  public void testGetRawTypeGenericArrayType() {
    GenericArrayType gat = GsonTypes.arrayOf(String.class);
    assertThat(GsonTypes.getRawType(gat)).isEqualTo(String[].class);
  }

  @Test
  public void testGetRawTypeWildcardType() {
    WildcardType wt = GsonTypes.subtypeOf(String.class);
    assertThat(GsonTypes.getRawType(wt)).isEqualTo(String.class);
  }

  @Test
  public void testGetRawTypeTypeVariable() throws Exception {
    Type typeVariable = List.class.getTypeParameters()[0];
    assertThat(GsonTypes.getRawType(typeVariable)).isEqualTo(Object.class);
  }

  @Test
  public void testGetRawTypeUnsupportedThrows() {
    assertThrows(
        IllegalArgumentException.class,
        () ->
            GsonTypes.getRawType(
                new Type() {
                  @Override
                  public String getTypeName() {
                    return "custom";
                  }
                }));
  }

  @Test
  public void testEqualsIdentical() {
    assertThat(GsonTypes.equals(String.class, String.class)).isTrue();
  }

  @Test
  public void testEqualsNullBoth() {
    assertThat(GsonTypes.equals(null, null)).isTrue();
  }

  @Test
  public void testEqualsClassDifferent() {
    assertThat(GsonTypes.equals(String.class, Integer.class)).isFalse();
  }

  @Test
  public void testEqualsParameterizedTypes() {
    ParameterizedType pt1 =
        GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    ParameterizedType pt2 =
        GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    assertThat(GsonTypes.equals(pt1, pt2)).isTrue();
  }

  @Test
  public void testEqualsParameterizedVsNonParameterized() {
    ParameterizedType pt = GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    assertThat(GsonTypes.equals(pt, String.class)).isFalse();
  }

  @Test
  public void testEqualsGenericArrayTypes() {
    GenericArrayType ga1 = GsonTypes.arrayOf(String.class);
    GenericArrayType ga2 = GsonTypes.arrayOf(String.class);
    assertThat(GsonTypes.equals(ga1, ga2)).isTrue();
  }

  @Test
  public void testEqualsGenericArrayVsNonArray() {
    GenericArrayType ga = GsonTypes.arrayOf(String.class);
    assertThat(GsonTypes.equals(ga, String.class)).isFalse();
  }

  @Test
  public void testEqualsWildcardTypes() {
    WildcardType wa = GsonTypes.subtypeOf(String.class);
    WildcardType wb = GsonTypes.subtypeOf(String.class);
    assertThat(GsonTypes.equals(wa, wb)).isTrue();
  }

  @Test
  public void testEqualsWildcardVsNonWildcard() {
    WildcardType wa = GsonTypes.subtypeOf(String.class);
    assertThat(GsonTypes.equals(wa, String.class)).isFalse();
  }

  @Test
  public void testEqualsTypeVariables() throws Exception {
    Type tv1 = List.class.getTypeParameters()[0];
    Type tv2 = List.class.getTypeParameters()[0];
    assertThat(GsonTypes.equals(tv1, tv2)).isTrue();
  }

  @Test
  public void testEqualsTypeVariableDifferent() throws Exception {
    Type tv1 = List.class.getTypeParameters()[0];
    Type tv2 = Map.class.getTypeParameters()[0];
    assertThat(GsonTypes.equals(tv1, tv2)).isFalse();
  }

  @Test
  public void testEqualsTypeVariableVsNonTypeVariable() throws Exception {
    Type tv = List.class.getTypeParameters()[0];
    assertThat(GsonTypes.equals(tv, String.class)).isFalse();
  }

  @Test
  public void testEqualsUnknownTypeReturnsFalse() {
    Type custom1 =
        new Type() {
          @Override
          public String getTypeName() {
            return "custom1";
          }
        };
    Type custom2 =
        new Type() {
          @Override
          public String getTypeName() {
            return "custom2";
          }
        };
    assertThat(GsonTypes.equals(custom1, custom2)).isFalse();
  }

  @Test
  public void testTypeToStringClass() {
    assertThat(GsonTypes.typeToString(String.class)).isEqualTo("java.lang.String");
  }

  @Test
  public void testTypeToStringParameterizedType() {
    ParameterizedType pt = GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    assertThat(GsonTypes.typeToString(pt)).isEqualTo("java.util.List<java.lang.String>");
  }

  @Test
  public void testGetArrayComponentTypeGenericArray() {
    GenericArrayType gat = GsonTypes.arrayOf(String.class);
    assertThat(GsonTypes.getArrayComponentType(gat)).isEqualTo(String.class);
  }

  @Test
  public void testGetArrayComponentTypeClass() {
    assertThat(GsonTypes.getArrayComponentType(String[].class)).isEqualTo(String.class);
  }

  @Test
  public void testGetCollectionElementTypeRaw() {
    Type elementType = GsonTypes.getCollectionElementType(ArrayList.class, ArrayList.class);
    // Raw collection returns the type variable E (not Object.class) when context is raw
    assertThat(elementType).isNotNull();
  }

  @Test
  public void testGetCollectionElementTypeParameterized() {
    ParameterizedType listStringType =
        GsonTypes.newParameterizedTypeWithOwner(null, ArrayList.class, String.class);
    Type elementType =
        GsonTypes.getCollectionElementType(listStringType, ArrayList.class);
    assertThat(elementType).isEqualTo(String.class);
  }

  @Test
  public void testGetMapKeyAndValueTypesProperties() {
    Type[] types = GsonTypes.getMapKeyAndValueTypes(Properties.class, Properties.class);
    assertThat(types[0]).isEqualTo(String.class);
    assertThat(types[1]).isEqualTo(String.class);
  }

  @Test
  public void testGetMapKeyAndValueTypesParameterized() {
    ParameterizedType mapType =
        GsonTypes.newParameterizedTypeWithOwner(
            null, HashMap.class, String.class, Integer.class);
    Type[] types = GsonTypes.getMapKeyAndValueTypes(mapType, HashMap.class);
    assertThat(types[0]).isEqualTo(String.class);
    assertThat(types[1]).isEqualTo(Integer.class);
  }

  @Test
  public void testGetMapKeyAndValueTypesRaw() {
    Type[] types = GsonTypes.getMapKeyAndValueTypes(HashMap.class, HashMap.class);
    assertThat(types).hasLength(2);
    // Raw map returns type variables K and V
    assertThat(types[0]).isNotNull();
    assertThat(types[1]).isNotNull();
  }

  @Test
  public void testResolveSimpleClass() {
    Type result = GsonTypes.resolve(String.class, String.class, String.class);
    assertThat(result).isEqualTo(String.class);
  }

  @Test
  public void testResolveArrayClass() {
    Type result = GsonTypes.resolve(String[].class, String[].class, String[].class);
    assertThat(result).isEqualTo(String[].class);
  }

  @Test
  public void testResolveGenericArrayType() {
    GenericArrayType gat = GsonTypes.arrayOf(String.class);
    Type result = GsonTypes.resolve(Object.class, Object.class, gat);
    assertThat(result).isInstanceOf(GenericArrayType.class);
  }

  @Test
  public void testResolveParameterizedType() {
    ParameterizedType pt = GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class);
    Type result = GsonTypes.resolve(Object.class, Object.class, pt);
    assertThat(result).isInstanceOf(ParameterizedType.class);
  }

  @Test
  public void testResolveWildcardType() {
    WildcardType wt = GsonTypes.subtypeOf(String.class);
    Type result = GsonTypes.resolve(Object.class, Object.class, wt);
    assertThat(result).isInstanceOf(WildcardType.class);
  }

  @Test
  public void testResolveTypeVariableFromParameterizedContext() {
    ParameterizedType listStringType =
        GsonTypes.newParameterizedTypeWithOwner(null, ArrayList.class, String.class);
    Type typeVar = ArrayList.class.getTypeParameters()[0];
    Type result = GsonTypes.resolve(listStringType, ArrayList.class, typeVar);
    assertThat(result).isEqualTo(String.class);
  }

  @Test
  public void testCheckNotPrimitivePasses() {
    GsonTypes.checkNotPrimitive(String.class);
  }

  @Test
  public void testCheckNotPrimitiveThrowsForPrimitive() {
    assertThrows(
        IllegalArgumentException.class, () -> GsonTypes.checkNotPrimitive(int.class));
  }

  @Test
  public void testCheckNotPrimitivePassesForNonClassType() {
    GsonTypes.checkNotPrimitive(GsonTypes.subtypeOf(String.class));
  }

  @Test
  public void testRequiresOwnerTypeForTopLevelClass() {
    assertThat(GsonTypes.requiresOwnerType(String.class)).isFalse();
  }

  @Test
  public void testRequiresOwnerTypeForStaticInnerClass() {
    assertThat(GsonTypes.requiresOwnerType(Map.Entry.class)).isFalse();
  }

  @Test
  public void testRequiresOwnerTypeForNonClassType() {
    assertThat(GsonTypes.requiresOwnerType(GsonTypes.subtypeOf(String.class))).isFalse();
  }

  @Test
  public void testRequiresOwnerTypeForNonStaticInnerClass() {
    assertThat(GsonTypes.requiresOwnerType(NonStaticInner.class)).isTrue();
  }

  @Test
  public void testResolveTypeVariableSeenAgainReturnsCachedResult() {
    // HashMap has type parameters K and V; use K twice to trigger the cached-result path (line 360)
    TypeVariable<?> K = HashMap.class.getTypeParameters()[0];
    ParameterizedType mapKK =
        GsonTypes.newParameterizedTypeWithOwner(null, HashMap.class, K, K);
    ParameterizedType contextType =
        GsonTypes.newParameterizedTypeWithOwner(null, HashMap.class, String.class, Integer.class);

    Type result = GsonTypes.resolve(contextType, HashMap.class, mapKK);

    assertThat(result).isInstanceOf(ParameterizedType.class);
    Type[] typeArgs = ((ParameterizedType) result).getActualTypeArguments();
    // Both positions had TypeVariable K; K resolves to String (first arg in context).
    // The second occurrence hits the cached-result path and also returns String.
    assertThat(typeArgs[0]).isEqualTo(String.class);
    assertThat(typeArgs[1]).isEqualTo(String.class);
  }

  @Test
  public void testResolveWildcardLowerBoundResolvable() {
    // ? super E in context of ArrayList<String> → lowerBound resolves to String (lines 423-427)
    TypeVariable<?> typeVarE = ArrayList.class.getTypeParameters()[0];
    WildcardType supertypeOfE = GsonTypes.supertypeOf(typeVarE);
    ParameterizedType listStringType =
        GsonTypes.newParameterizedTypeWithOwner(null, ArrayList.class, String.class);

    Type result = GsonTypes.resolve(listStringType, ArrayList.class, supertypeOfE);

    assertThat(result).isInstanceOf(WildcardType.class);
    assertThat(((WildcardType) result).getLowerBounds()[0]).isEqualTo(String.class);
  }

  @Test
  public void testResolveWildcardUpperBoundResolvable() {
    // ? extends E in context of ArrayList<String> → upperBound resolves to String (lines 433-434)
    TypeVariable<?> typeVarE = ArrayList.class.getTypeParameters()[0];
    WildcardType subtypeOfE = GsonTypes.subtypeOf(typeVarE);
    ParameterizedType listStringType =
        GsonTypes.newParameterizedTypeWithOwner(null, ArrayList.class, String.class);

    Type result = GsonTypes.resolve(listStringType, ArrayList.class, subtypeOfE);

    assertThat(result).isInstanceOf(WildcardType.class);
    assertThat(((WildcardType) result).getUpperBounds()[0]).isEqualTo(String.class);
  }

  @SuppressWarnings("InnerClassMayBeStatic")
  class NonStaticInner {}
}
