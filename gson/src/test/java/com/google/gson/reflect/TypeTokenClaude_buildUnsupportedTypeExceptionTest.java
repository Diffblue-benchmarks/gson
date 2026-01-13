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

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.List;
import org.junit.Test;

/**
 * Tests for {@link TypeToken} focusing on the buildUnsupportedTypeException method.
 *
 * <p>The buildUnsupportedTypeException method is private but is called from the deprecated
 * isAssignableFrom(Type) method when the TypeToken's internal type is not a Class,
 * ParameterizedType, or GenericArrayType (e.g., when it's a WildcardType).
 *
 * @author Claude
 */
@SuppressWarnings("deprecation") // Testing deprecated isAssignableFrom method
public class TypeTokenClaude_buildUnsupportedTypeExceptionTest {

  // ==========================================================================
  // Tests that trigger buildUnsupportedTypeException via isAssignableFrom
  // ==========================================================================

  @Test
  public void testBuildUnsupportedTypeException_wildcardType() {
    // Create a TypeToken with a WildcardType as its internal type
    // WildcardType is not Class, ParameterizedType, or GenericArrayType
    // so isAssignableFrom should throw via buildUnsupportedTypeException

    // Get the WildcardType from List<? extends Number>
    Type listType = new TypeToken<List<? extends Number>>() {}.getType();
    ParameterizedType parameterizedType = (ParameterizedType) listType;
    Type wildcardType = parameterizedType.getActualTypeArguments()[0];

    // Verify it's actually a WildcardType
    assertThat(wildcardType).isInstanceOf(WildcardType.class);

    // Create a TypeToken with the WildcardType
    TypeToken<?> wildcardToken = TypeToken.get(wildcardType);

    // Calling isAssignableFrom should throw IllegalArgumentException
    // because WildcardType is not supported
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> wildcardToken.isAssignableFrom(String.class));

    // Verify the exception message contains expected content
    assertThat(exception.getMessage()).contains("Unsupported type");
    assertThat(exception.getMessage()).contains("expected one of");
    assertThat(exception.getMessage()).contains("Class");
    assertThat(exception.getMessage()).contains("ParameterizedType");
    assertThat(exception.getMessage()).contains("GenericArrayType");
    assertThat(exception.getMessage()).contains("but got");
    assertThat(exception.getMessage()).contains("WildcardType");
    assertThat(exception.getMessage()).contains("for type token");
  }

  @Test
  public void testBuildUnsupportedTypeException_wildcardTypeWithLowerBound() {
    // Test with a wildcard that has a lower bound: ? super String
    Type listType = new TypeToken<List<? super String>>() {}.getType();
    ParameterizedType parameterizedType = (ParameterizedType) listType;
    Type wildcardType = parameterizedType.getActualTypeArguments()[0];

    assertThat(wildcardType).isInstanceOf(WildcardType.class);

    TypeToken<?> wildcardToken = TypeToken.get(wildcardType);

    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> wildcardToken.isAssignableFrom(Object.class));

    assertThat(exception.getMessage()).contains("Unsupported type");
    assertThat(exception.getMessage()).contains("WildcardType");
  }

  @Test
  public void testBuildUnsupportedTypeException_unboundedWildcard() {
    // Test with an unbounded wildcard: ?
    Type listType = new TypeToken<List<?>>() {}.getType();
    ParameterizedType parameterizedType = (ParameterizedType) listType;
    Type wildcardType = parameterizedType.getActualTypeArguments()[0];

    assertThat(wildcardType).isInstanceOf(WildcardType.class);

    TypeToken<?> wildcardToken = TypeToken.get(wildcardType);

    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> wildcardToken.isAssignableFrom(Integer.class));

    assertThat(exception.getMessage()).contains("Unsupported type");
  }

  @Test
  public void testBuildUnsupportedTypeException_isAssignableFromWithType() {
    // Test isAssignableFrom(Type) overload
    Type wildcardType = getWildcardType();
    TypeToken<?> wildcardToken = TypeToken.get(wildcardType);

    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> wildcardToken.isAssignableFrom((Type) String.class));

    assertThat(exception.getMessage()).contains("Unsupported type");
  }

  @Test
  public void testBuildUnsupportedTypeException_isAssignableFromWithTypeToken() {
    // Test isAssignableFrom(TypeToken) overload - this also calls isAssignableFrom(Type)
    Type wildcardType = getWildcardType();
    TypeToken<?> wildcardToken = TypeToken.get(wildcardType);
    TypeToken<String> stringToken = new TypeToken<String>() {};

    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> wildcardToken.isAssignableFrom(stringToken));

    assertThat(exception.getMessage()).contains("Unsupported type");
  }

  @Test
  public void testBuildUnsupportedTypeException_messageContainsAllExpectedTypes() {
    // Verify the exception message lists all three expected types
    Type wildcardType = getWildcardType();
    TypeToken<?> wildcardToken = TypeToken.get(wildcardType);

    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> wildcardToken.isAssignableFrom(String.class));

    String message = exception.getMessage();
    // Check that all expected type names are in the message
    assertThat(message).contains("java.lang.Class");
    assertThat(message).contains("java.lang.reflect.ParameterizedType");
    assertThat(message).contains("java.lang.reflect.GenericArrayType");
  }

  @Test
  public void testBuildUnsupportedTypeException_messageContainsActualType() {
    // Verify the exception message contains information about the actual type
    Type wildcardType = getWildcardType();
    TypeToken<?> wildcardToken = TypeToken.get(wildcardType);

    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> wildcardToken.isAssignableFrom(String.class));

    String message = exception.getMessage();
    // The message should contain the actual type class name
    assertThat(message).contains("but got:");
    // It should also contain info about the type token
    assertThat(message).contains("for type token:");
  }

  @Test
  public void testBuildUnsupportedTypeException_exceptionTypeIsIllegalArgument() {
    // Verify that the exception type is specifically IllegalArgumentException
    Type wildcardType = getWildcardType();
    TypeToken<?> wildcardToken = TypeToken.get(wildcardType);

    Exception exception = null;
    try {
      @SuppressWarnings("unused")
      boolean unused = wildcardToken.isAssignableFrom(String.class);
    } catch (Exception e) {
      exception = e;
    }

    assertThat(exception).isNotNull();
    assertThat(exception).isInstanceOf(IllegalArgumentException.class);
  }

  // ==========================================================================
  // Tests that verify supported types do NOT trigger the exception
  // ==========================================================================

  @Test
  public void testIsAssignableFrom_classType_noException() {
    // Class type should be supported
    TypeToken<String> token = new TypeToken<String>() {};
    // Should not throw
    boolean result = token.isAssignableFrom(String.class);
    assertThat(result).isTrue();
  }

  @Test
  public void testIsAssignableFrom_parameterizedType_noException() {
    // ParameterizedType should be supported
    TypeToken<List<String>> token = new TypeToken<List<String>>() {};
    // Should not throw
    boolean result = token.isAssignableFrom(new TypeToken<List<String>>() {}.getType());
    assertThat(result).isTrue();
  }

  @Test
  public void testIsAssignableFrom_genericArrayType_noException() {
    // GenericArrayType should be supported
    TypeToken<List<String>[]> token = new TypeToken<List<String>[]>() {};
    // Should not throw
    boolean result = token.isAssignableFrom(new TypeToken<List<String>[]>() {}.getType());
    assertThat(result).isTrue();
  }

  // ==========================================================================
  // Helper methods
  // ==========================================================================

  /**
   * Helper method to get a WildcardType.
   */
  private static Type getWildcardType() {
    Type listType = new TypeToken<List<? extends Number>>() {}.getType();
    ParameterizedType parameterizedType = (ParameterizedType) listType;
    return parameterizedType.getActualTypeArguments()[0];
  }
}
