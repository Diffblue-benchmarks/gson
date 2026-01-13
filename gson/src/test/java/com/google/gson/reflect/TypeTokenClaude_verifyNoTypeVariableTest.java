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

import java.util.List;
import java.util.Map;
import org.junit.Test;

/**
 * Tests for {@link TypeToken} focusing on the verifyNoTypeVariable method.
 *
 * <p>The verifyNoTypeVariable method is private but is called during TypeToken construction when
 * type variables are captured. These tests create TypeToken instances that trigger the various
 * branches of verifyNoTypeVariable to ensure proper coverage.
 *
 * @author Claude
 */
public class TypeTokenClaude_verifyNoTypeVariableTest {

  // ==========================================================================
  // Test for TypeVariable case (lines 122-130)
  // When a type variable is captured directly as the type argument
  // ==========================================================================

  @Test
  public void testVerifyNoTypeVariable_directTypeVariable() {
    // This test creates a TypeToken that captures a type variable T directly
    // This should throw IllegalArgumentException with message about type variable
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> captureDirectTypeVariable());
    assertThat(exception.getMessage()).contains("TypeToken type argument must not contain a type variable");
    assertThat(exception.getMessage()).contains("captured type variable");
    assertThat(exception.getMessage()).contains("declared by");
  }

  @SuppressWarnings("unused")
  private static <T> TypeToken<T> captureDirectTypeVariable() {
    // Captures T directly - triggers lines 122-130
    return new TypeToken<T>() {};
  }

  // ==========================================================================
  // Test for TypeVariable in GenericArrayType case (lines 131-132)
  // When a type variable is captured as component type of an array
  // ==========================================================================

  @Test
  public void testVerifyNoTypeVariable_typeVariableInGenericArray() {
    // This test creates a TypeToken with an array of type variable T[]
    // The verifyNoTypeVariable will recursively check the array component type
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> captureTypeVariableInArray());
    assertThat(exception.getMessage()).contains("TypeToken type argument must not contain a type variable");
  }

  @SuppressWarnings("unused")
  private static <T> TypeToken<T[]> captureTypeVariableInArray() {
    // Captures T[] - triggers line 132 (recursive call for GenericArrayType)
    return new TypeToken<T[]>() {};
  }

  // ==========================================================================
  // Test for TypeVariable in ParameterizedType case (lines 133-142)
  // When a type variable is captured inside a parameterized type
  // ==========================================================================

  @Test
  public void testVerifyNoTypeVariable_typeVariableInParameterizedType() {
    // This test creates a TypeToken with List<T> where T is a type variable
    // The verifyNoTypeVariable will check the type arguments of the ParameterizedType
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> captureTypeVariableInList());
    assertThat(exception.getMessage()).contains("TypeToken type argument must not contain a type variable");
  }

  @SuppressWarnings("unused")
  private static <T> TypeToken<List<T>> captureTypeVariableInList() {
    // Captures List<T> - triggers lines 133-142 (ParameterizedType branch)
    return new TypeToken<List<T>>() {};
  }

  @Test
  public void testVerifyNoTypeVariable_typeVariableInNestedParameterizedType() {
    // This test creates a TypeToken with Map<String, List<T>> where T is a type variable
    // The verifyNoTypeVariable will recursively check nested parameterized types
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> captureTypeVariableInNestedParameterizedType());
    assertThat(exception.getMessage()).contains("TypeToken type argument must not contain a type variable");
  }

  @SuppressWarnings("unused")
  private static <T> TypeToken<Map<String, List<T>>> captureTypeVariableInNestedParameterizedType() {
    // Captures Map<String, List<T>> - triggers nested ParameterizedType check
    return new TypeToken<Map<String, List<T>>>() {};
  }

  @Test
  public void testVerifyNoTypeVariable_multipleTypeVariables() {
    // This test creates a TypeToken with Map<K, V> where both K and V are type variables
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> captureMultipleTypeVariables());
    assertThat(exception.getMessage()).contains("TypeToken type argument must not contain a type variable");
  }

  @SuppressWarnings("unused")
  private static <K, V> TypeToken<Map<K, V>> captureMultipleTypeVariables() {
    // Captures Map<K, V> - both K and V are type variables
    return new TypeToken<Map<K, V>>() {};
  }

  // ==========================================================================
  // Test for TypeVariable in WildcardType upper bounds case (lines 143-150)
  // When a type variable is captured inside a wildcard bound
  // ==========================================================================

  @Test
  public void testVerifyNoTypeVariable_typeVariableInWildcardUpperBound() {
    // This test creates a TypeToken with List<? extends T> where T is a type variable
    // The verifyNoTypeVariable will check the upper bounds of the WildcardType
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> captureTypeVariableInWildcardUpperBound());
    assertThat(exception.getMessage()).contains("TypeToken type argument must not contain a type variable");
  }

  @SuppressWarnings("unused")
  private static <T> TypeToken<List<? extends T>> captureTypeVariableInWildcardUpperBound() {
    // Captures List<? extends T> - triggers lines 148-150 (upper bounds check)
    return new TypeToken<List<? extends T>>() {};
  }

  @Test
  public void testVerifyNoTypeVariable_typeVariableInWildcardLowerBound() {
    // This test creates a TypeToken with List<? super T> where T is a type variable
    // The verifyNoTypeVariable will check the lower bounds of the WildcardType (line 146)
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> captureTypeVariableInWildcardLowerBound());
    assertThat(exception.getMessage()).contains("TypeToken type argument must not contain a type variable");
  }

  @SuppressWarnings("unused")
  private static <T> TypeToken<List<? super T>> captureTypeVariableInWildcardLowerBound() {
    // Captures List<? super T> - triggers lines 145-147 (lower bounds check)
    return new TypeToken<List<? super T>>() {};
  }

  // ==========================================================================
  // Test for ParameterizedType with owner type (line 136-137)
  // When an inner class has an owner type that needs to be checked
  // ==========================================================================

  // Inner class to test owner type checking
  private class Inner<T> {}

  @Test
  public void testVerifyNoTypeVariable_typeVariableInOwnerType() {
    // This test creates a situation where the owner type contains a type variable
    // The GenericOuter<T>.Inner pattern has an owner type GenericOuter<T>
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> captureTypeVariableInOwnerType());
    assertThat(exception.getMessage()).contains("TypeToken type argument must not contain a type variable");
  }

  @SuppressWarnings("unused")
  private <T> TypeToken<GenericOuter<T>.Inner> captureTypeVariableInOwnerType() {
    // Captures GenericOuter<T>.Inner - triggers lines 136-137 (owner type check)
    return new TypeToken<GenericOuter<T>.Inner>() {};
  }

  // Helper class with inner class for owner type testing
  private static class GenericOuter<T> {
    @SuppressWarnings("unused")
    class Inner {}
  }

  // ==========================================================================
  // Test for complex nested type with multiple branches
  // ==========================================================================

  @Test
  public void testVerifyNoTypeVariable_complexNestedType() {
    // This test creates a TypeToken with a complex nested type
    // Map<List<T>, Map<String, T[]>> to trigger multiple branches
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> captureComplexNestedType());
    assertThat(exception.getMessage()).contains("TypeToken type argument must not contain a type variable");
  }

  @SuppressWarnings("unused")
  private static <T> TypeToken<Map<List<T>, Map<String, T[]>>> captureComplexNestedType() {
    // Complex nested type with type variables in different positions
    return new TypeToken<Map<List<T>, Map<String, T[]>>>() {};
  }

  // ==========================================================================
  // Verify that valid TypeTokens without type variables work correctly
  // ==========================================================================

  @Test
  public void testVerifyNoTypeVariable_validConcreteType() {
    // This should not throw - no type variables involved
    TypeToken<String> token = new TypeToken<String>() {};
    assertThat(token.getRawType()).isEqualTo(String.class);
  }

  @Test
  public void testVerifyNoTypeVariable_validParameterizedType() {
    // This should not throw - concrete type arguments
    TypeToken<List<String>> token = new TypeToken<List<String>>() {};
    assertThat(token.getRawType()).isEqualTo(List.class);
  }

  @Test
  public void testVerifyNoTypeVariable_validWildcardType() {
    // This should not throw - wildcard with concrete bound
    TypeToken<List<? extends Number>> token = new TypeToken<List<? extends Number>>() {};
    assertThat(token.getRawType()).isEqualTo(List.class);
  }

  @Test
  public void testVerifyNoTypeVariable_validArrayType() {
    // This should not throw - concrete array type
    TypeToken<String[]> token = new TypeToken<String[]>() {};
    assertThat(token.getRawType()).isEqualTo(String[].class);
  }

  @Test
  public void testVerifyNoTypeVariable_validGenericArrayType() {
    // This should not throw - array of concrete parameterized type
    TypeToken<List<String>[]> token = new TypeToken<List<String>[]>() {};
    assertThat(token.getRawType()).isEqualTo(List[].class);
  }

  @Test
  public void testVerifyNoTypeVariable_validNestedParameterizedType() {
    // This should not throw - nested concrete parameterized types
    TypeToken<Map<String, List<Integer>>> token = new TypeToken<Map<String, List<Integer>>>() {};
    assertThat(token.getRawType()).isEqualTo(Map.class);
  }

  // ==========================================================================
  // Test error message details
  // ==========================================================================

  @Test
  public void testVerifyNoTypeVariable_errorMessageContainsTypeVariableName() {
    // Verify the error message includes the name of the captured type variable
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> captureDirectTypeVariable());
    // The type variable is named "T"
    assertThat(exception.getMessage()).contains("T");
  }

  @Test
  public void testVerifyNoTypeVariable_errorMessageContainsTroubleshootingUrl() {
    // Verify the error message includes the troubleshooting guide URL
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> captureDirectTypeVariable());
    assertThat(exception.getMessage()).contains("typetoken-type-variable");
  }

  // ==========================================================================
  // Test with bounded type variables
  // ==========================================================================

  @Test
  public void testVerifyNoTypeVariable_boundedTypeVariable() {
    // Type variable with bounds should also be rejected
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> captureBoundedTypeVariable());
    assertThat(exception.getMessage()).contains("TypeToken type argument must not contain a type variable");
  }

  @SuppressWarnings("unused")
  private static <T extends Number> TypeToken<T> captureBoundedTypeVariable() {
    // Captures T where T extends Number
    return new TypeToken<T>() {};
  }

  @Test
  public void testVerifyNoTypeVariable_boundedTypeVariableInList() {
    // Bounded type variable in parameterized type should also be rejected
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> captureBoundedTypeVariableInList());
    assertThat(exception.getMessage()).contains("TypeToken type argument must not contain a type variable");
  }

  @SuppressWarnings("unused")
  private static <T extends Comparable<T>> TypeToken<List<T>> captureBoundedTypeVariableInList() {
    // Captures List<T> where T extends Comparable<T>
    return new TypeToken<List<T>>() {};
  }
}
