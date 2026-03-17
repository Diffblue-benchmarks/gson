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
import static org.junit.Assert.assertThrows;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import org.junit.Test;

public class GsonTypesWildcardTypeTest {

  @Test
  public void testSubtypeOfCreatesUpperBoundWildcard() {
    WildcardType wildcardType = GsonTypes.subtypeOf(String.class);

    Type[] upperBounds = wildcardType.getUpperBounds();
    Type[] lowerBounds = wildcardType.getLowerBounds();

    assertThat(upperBounds).hasLength(1);
    assertThat(upperBounds[0]).isEqualTo(String.class);
    assertThat(lowerBounds).hasLength(0);
  }

  @Test
  public void testSubtypeOfObjectReturnsUnboundedWildcard() {
    WildcardType wildcardType = GsonTypes.subtypeOf(Object.class);

    Type[] upperBounds = wildcardType.getUpperBounds();
    Type[] lowerBounds = wildcardType.getLowerBounds();

    assertThat(upperBounds).hasLength(1);
    assertThat(upperBounds[0]).isEqualTo(Object.class);
    assertThat(lowerBounds).hasLength(0);
  }

  @Test
  public void testSupertypeOfCreatesLowerBoundWildcard() {
    WildcardType wildcardType = GsonTypes.supertypeOf(String.class);

    Type[] upperBounds = wildcardType.getUpperBounds();
    Type[] lowerBounds = wildcardType.getLowerBounds();

    assertThat(upperBounds).hasLength(1);
    assertThat(upperBounds[0]).isEqualTo(Object.class);
    assertThat(lowerBounds).hasLength(1);
    assertThat(lowerBounds[0]).isEqualTo(String.class);
  }

  @Test
  public void testWildcardTypeToStringWithUpperBound() {
    WildcardType wildcardType = GsonTypes.subtypeOf(CharSequence.class);

    String result = wildcardType.toString();

    assertThat(result).isEqualTo("? extends java.lang.CharSequence");
  }

  @Test
  public void testWildcardTypeToStringWithObjectUpperBound() {
    WildcardType wildcardType = GsonTypes.subtypeOf(Object.class);

    String result = wildcardType.toString();

    assertThat(result).isEqualTo("?");
  }

  @Test
  public void testWildcardTypeToStringWithLowerBound() {
    WildcardType wildcardType = GsonTypes.supertypeOf(Integer.class);

    String result = wildcardType.toString();

    assertThat(result).isEqualTo("? super java.lang.Integer");
  }

  @Test
  public void testWildcardTypeEqualsWithSameUpperBound() {
    WildcardType wildcardType1 = GsonTypes.subtypeOf(String.class);
    WildcardType wildcardType2 = GsonTypes.subtypeOf(String.class);

    boolean result = wildcardType1.equals(wildcardType2);

    assertThat(result).isTrue();
  }

  @Test
  public void testWildcardTypeEqualsWithDifferentUpperBound() {
    WildcardType wildcardType1 = GsonTypes.subtypeOf(String.class);
    WildcardType wildcardType2 = GsonTypes.subtypeOf(Integer.class);

    boolean result = wildcardType1.equals(wildcardType2);

    assertThat(result).isFalse();
  }

  @Test
  public void testWildcardTypeEqualsWithSameLowerBound() {
    WildcardType wildcardType1 = GsonTypes.supertypeOf(String.class);
    WildcardType wildcardType2 = GsonTypes.supertypeOf(String.class);

    boolean result = wildcardType1.equals(wildcardType2);

    assertThat(result).isTrue();
  }

  @Test
  public void testWildcardTypeEqualsWithDifferentLowerBound() {
    WildcardType wildcardType1 = GsonTypes.supertypeOf(String.class);
    WildcardType wildcardType2 = GsonTypes.supertypeOf(Integer.class);

    boolean result = wildcardType1.equals(wildcardType2);

    assertThat(result).isFalse();
  }

  @Test
  public void testWildcardTypeEqualsWithNonWildcardType() {
    WildcardType wildcardType = GsonTypes.subtypeOf(String.class);

    boolean result = wildcardType.equals(String.class);

    assertThat(result).isFalse();
  }

  @Test
  public void testWildcardTypeEqualsWithNull() {
    WildcardType wildcardType = GsonTypes.subtypeOf(String.class);

    boolean result = wildcardType.equals(null);

    assertThat(result).isFalse();
  }

  @Test
  public void testWildcardTypeHashCodeConsistency() {
    WildcardType wildcardType1 = GsonTypes.subtypeOf(String.class);
    WildcardType wildcardType2 = GsonTypes.subtypeOf(String.class);

    int hashCode1 = wildcardType1.hashCode();
    int hashCode2 = wildcardType2.hashCode();

    assertThat(hashCode1).isEqualTo(hashCode2);
  }

  @Test
  public void testWildcardTypeHashCodeWithUpperBound() {
    WildcardType wildcardType = GsonTypes.subtypeOf(String.class);

    int hashCode = wildcardType.hashCode();

    assertThat(hashCode).isNotEqualTo(0);
  }

  @Test
  public void testWildcardTypeHashCodeWithLowerBound() {
    WildcardType wildcardType = GsonTypes.supertypeOf(String.class);

    int hashCode = wildcardType.hashCode();

    assertThat(hashCode).isNotEqualTo(0);
  }

  @Test
  public void testWildcardTypeHashCodeDifferentForDifferentBounds() {
    WildcardType wildcardType1 = GsonTypes.subtypeOf(String.class);
    WildcardType wildcardType2 = GsonTypes.subtypeOf(Integer.class);

    int hashCode1 = wildcardType1.hashCode();
    int hashCode2 = wildcardType2.hashCode();

    assertThat(hashCode1).isNotEqualTo(hashCode2);
  }

  @Test
  public void testSubtypeOfWithSerializableInterface() {
    WildcardType wildcardType = GsonTypes.subtypeOf(Serializable.class);

    Type[] upperBounds = wildcardType.getUpperBounds();

    assertThat(upperBounds).hasLength(1);
    assertThat(upperBounds[0]).isEqualTo(Serializable.class);
  }

  @Test
  public void testSupertypeOfWithNumberClass() {
    WildcardType wildcardType = GsonTypes.supertypeOf(Number.class);

    Type[] upperBounds = wildcardType.getUpperBounds();
    Type[] lowerBounds = wildcardType.getLowerBounds();

    assertThat(upperBounds).hasLength(1);
    assertThat(upperBounds[0]).isEqualTo(Object.class);
    assertThat(lowerBounds).hasLength(1);
    assertThat(lowerBounds[0]).isEqualTo(Number.class);
  }

  @Test
  public void testWildcardTypeGetUpperBoundsReturnsNewArray() {
    WildcardType wildcardType = GsonTypes.subtypeOf(String.class);

    Type[] upperBounds1 = wildcardType.getUpperBounds();
    Type[] upperBounds2 = wildcardType.getUpperBounds();

    assertThat(upperBounds1).isNotSameInstanceAs(upperBounds2);
    assertThat(upperBounds1).isEqualTo(upperBounds2);
  }

  @Test
  public void testWildcardTypeGetLowerBoundsReturnsNewArray() {
    WildcardType wildcardType = GsonTypes.supertypeOf(String.class);

    Type[] lowerBounds1 = wildcardType.getLowerBounds();
    Type[] lowerBounds2 = wildcardType.getLowerBounds();

    assertThat(lowerBounds1).isNotSameInstanceAs(lowerBounds2);
    assertThat(lowerBounds1).isEqualTo(lowerBounds2);
  }

  @Test
  public void testWildcardTypeEqualsSameInstance() {
    WildcardType wildcardType = GsonTypes.subtypeOf(String.class);

    boolean result = wildcardType.equals(wildcardType);

    assertThat(result).isTrue();
  }

  @Test
  public void testWildcardTypeLowerBoundAndUpperBoundNotEqual() {
    WildcardType upperBoundWildcard = GsonTypes.subtypeOf(String.class);
    WildcardType lowerBoundWildcard = GsonTypes.supertypeOf(String.class);

    boolean result = upperBoundWildcard.equals(lowerBoundWildcard);

    assertThat(result).isFalse();
  }

  @Test
  public void testSubtypeOfWithNestedWildcard() {
    WildcardType innerWildcard = GsonTypes.subtypeOf(String.class);
    WildcardType outerWildcard = GsonTypes.subtypeOf(innerWildcard);

    Type[] upperBounds = outerWildcard.getUpperBounds();

    assertThat(upperBounds).hasLength(1);
    assertThat(upperBounds[0]).isEqualTo(String.class);
  }

  @Test
  public void testSupertypeOfWithNestedWildcard() {
    WildcardType innerWildcard = GsonTypes.supertypeOf(String.class);
    WildcardType outerWildcard = GsonTypes.supertypeOf(innerWildcard);

    Type[] lowerBounds = outerWildcard.getLowerBounds();

    assertThat(lowerBounds).hasLength(1);
    assertThat(lowerBounds[0]).isEqualTo(String.class);
  }

  @Test
  public void testWildcardTypeConstructorWithMultipleLowerBounds() throws Exception {
    Class<?> wildcardTypeImplClass = Class.forName("com.google.gson.internal.GsonTypes$WildcardTypeImpl");
    Constructor<?> constructor = wildcardTypeImplClass.getDeclaredConstructor(Type[].class, Type[].class);
    constructor.setAccessible(true);

    Type[] upperBounds = new Type[] {Object.class};
    Type[] lowerBounds = new Type[] {String.class, Integer.class};

    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      try {
        constructor.newInstance(upperBounds, lowerBounds);
      } catch (Exception e) {
        if (e.getCause() instanceof IllegalArgumentException) {
          throw (IllegalArgumentException) e.getCause();
        }
        throw new RuntimeException(e);
      }
    });

    assertThat(exception.getMessage()).isEqualTo("At most one lower bound is supported");
  }

  @Test
  public void testWildcardTypeConstructorWithMultipleUpperBounds() throws Exception {
    Class<?> wildcardTypeImplClass = Class.forName("com.google.gson.internal.GsonTypes$WildcardTypeImpl");
    Constructor<?> constructor = wildcardTypeImplClass.getDeclaredConstructor(Type[].class, Type[].class);
    constructor.setAccessible(true);

    Type[] upperBounds = new Type[] {String.class, Integer.class};
    Type[] lowerBounds = new Type[] {};

    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      try {
        constructor.newInstance(upperBounds, lowerBounds);
      } catch (Exception e) {
        if (e.getCause() instanceof IllegalArgumentException) {
          throw (IllegalArgumentException) e.getCause();
        }
        throw new RuntimeException(e);
      }
    });

    assertThat(exception.getMessage()).isEqualTo("Exactly one upper bound must be specified");
  }

  @Test
  public void testWildcardTypeConstructorWithZeroUpperBounds() throws Exception {
    Class<?> wildcardTypeImplClass = Class.forName("com.google.gson.internal.GsonTypes$WildcardTypeImpl");
    Constructor<?> constructor = wildcardTypeImplClass.getDeclaredConstructor(Type[].class, Type[].class);
    constructor.setAccessible(true);

    Type[] upperBounds = new Type[] {};
    Type[] lowerBounds = new Type[] {};

    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      try {
        constructor.newInstance(upperBounds, lowerBounds);
      } catch (Exception e) {
        if (e.getCause() instanceof IllegalArgumentException) {
          throw (IllegalArgumentException) e.getCause();
        }
        throw new RuntimeException(e);
      }
    });

    assertThat(exception.getMessage()).isEqualTo("Exactly one upper bound must be specified");
  }

  @Test
  public void testWildcardTypeConstructorWithLowerBoundAndNonObjectUpperBound() throws Exception {
    Class<?> wildcardTypeImplClass = Class.forName("com.google.gson.internal.GsonTypes$WildcardTypeImpl");
    Constructor<?> constructor = wildcardTypeImplClass.getDeclaredConstructor(Type[].class, Type[].class);
    constructor.setAccessible(true);

    Type[] upperBounds = new Type[] {String.class};
    Type[] lowerBounds = new Type[] {Integer.class};

    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      try {
        constructor.newInstance(upperBounds, lowerBounds);
      } catch (Exception e) {
        if (e.getCause() instanceof IllegalArgumentException) {
          throw (IllegalArgumentException) e.getCause();
        }
        throw new RuntimeException(e);
      }
    });

    assertThat(exception.getMessage()).isEqualTo("When lower bound is specified, upper bound must be Object");
  }

  @Test
  public void testWildcardTypeConstructorWithPrimitiveUpperBound() throws Exception {
    Class<?> wildcardTypeImplClass = Class.forName("com.google.gson.internal.GsonTypes$WildcardTypeImpl");
    Constructor<?> constructor = wildcardTypeImplClass.getDeclaredConstructor(Type[].class, Type[].class);
    constructor.setAccessible(true);

    Type[] upperBounds = new Type[] {int.class};
    Type[] lowerBounds = new Type[] {};

    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      try {
        constructor.newInstance(upperBounds, lowerBounds);
      } catch (Exception e) {
        if (e.getCause() instanceof IllegalArgumentException) {
          throw (IllegalArgumentException) e.getCause();
        }
        throw new RuntimeException(e);
      }
    });

    assertThat(exception.getMessage()).isEqualTo("Primitive type is not allowed");
  }

  @Test
  public void testWildcardTypeConstructorWithPrimitiveLowerBound() throws Exception {
    Class<?> wildcardTypeImplClass = Class.forName("com.google.gson.internal.GsonTypes$WildcardTypeImpl");
    Constructor<?> constructor = wildcardTypeImplClass.getDeclaredConstructor(Type[].class, Type[].class);
    constructor.setAccessible(true);

    Type[] upperBounds = new Type[] {Object.class};
    Type[] lowerBounds = new Type[] {int.class};

    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      try {
        constructor.newInstance(upperBounds, lowerBounds);
      } catch (Exception e) {
        if (e.getCause() instanceof IllegalArgumentException) {
          throw (IllegalArgumentException) e.getCause();
        }
        throw new RuntimeException(e);
      }
    });

    assertThat(exception.getMessage()).isEqualTo("Primitive type is not allowed");
  }

  @Test
  public void testWildcardTypeConstructorWithNullUpperBound() throws Exception {
    Class<?> wildcardTypeImplClass = Class.forName("com.google.gson.internal.GsonTypes$WildcardTypeImpl");
    Constructor<?> constructor = wildcardTypeImplClass.getDeclaredConstructor(Type[].class, Type[].class);
    constructor.setAccessible(true);

    Type[] upperBounds = new Type[] {null};
    Type[] lowerBounds = new Type[] {};

    assertThrows(NullPointerException.class, () -> {
      try {
        constructor.newInstance(upperBounds, lowerBounds);
      } catch (Exception e) {
        if (e.getCause() instanceof NullPointerException) {
          throw (NullPointerException) e.getCause();
        }
        throw new RuntimeException(e);
      }
    });
  }

  @Test
  public void testWildcardTypeConstructorWithNullLowerBound() throws Exception {
    Class<?> wildcardTypeImplClass = Class.forName("com.google.gson.internal.GsonTypes$WildcardTypeImpl");
    Constructor<?> constructor = wildcardTypeImplClass.getDeclaredConstructor(Type[].class, Type[].class);
    constructor.setAccessible(true);

    Type[] upperBounds = new Type[] {Object.class};
    Type[] lowerBounds = new Type[] {null};

    assertThrows(NullPointerException.class, () -> {
      try {
        constructor.newInstance(upperBounds, lowerBounds);
      } catch (Exception e) {
        if (e.getCause() instanceof NullPointerException) {
          throw (NullPointerException) e.getCause();
        }
        throw new RuntimeException(e);
      }
    });
  }
}
