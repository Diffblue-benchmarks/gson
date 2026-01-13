/*
 * Copyright (C) 2021 Google Inc.
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

package com.google.gson.internal.reflect;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import org.junit.Test;

/**
 * Tests for {@link ReflectionHelper#tryMakeAccessible(java.lang.reflect.Constructor)}.
 */
public class ReflectionHelperClaude_tryMakeAccessibleTest {

  // ==========================================================================
  // Tests for successful access (returns null)
  // ==========================================================================

  @Test
  public void tryMakeAccessible_publicConstructor_returnsNull() throws Exception {
    Constructor<?> constructor = PublicClass.class.getDeclaredConstructor();
    String result = ReflectionHelper.tryMakeAccessible(constructor);
    assertNull(result);
    assertTrue(constructor.isAccessible());
  }

  @Test
  public void tryMakeAccessible_privateConstructor_returnsNull() throws Exception {
    Constructor<?> constructor = PrivateConstructorClass.class.getDeclaredConstructor();
    String result = ReflectionHelper.tryMakeAccessible(constructor);
    assertNull(result);
    assertTrue(constructor.isAccessible());
  }

  @Test
  public void tryMakeAccessible_protectedConstructor_returnsNull() throws Exception {
    Constructor<?> constructor = ProtectedConstructorClass.class.getDeclaredConstructor();
    String result = ReflectionHelper.tryMakeAccessible(constructor);
    assertNull(result);
    assertTrue(constructor.isAccessible());
  }

  @Test
  public void tryMakeAccessible_packagePrivateConstructor_returnsNull() throws Exception {
    Constructor<?> constructor = PackagePrivateConstructorClass.class.getDeclaredConstructor();
    String result = ReflectionHelper.tryMakeAccessible(constructor);
    assertNull(result);
    assertTrue(constructor.isAccessible());
  }

  @Test
  public void tryMakeAccessible_constructorWithParameters_returnsNull() throws Exception {
    Constructor<?> constructor = PublicClass.class.getDeclaredConstructor(String.class, int.class);
    String result = ReflectionHelper.tryMakeAccessible(constructor);
    assertNull(result);
    assertTrue(constructor.isAccessible());
  }

  @Test
  public void tryMakeAccessible_alreadyAccessibleConstructor_returnsNull() throws Exception {
    Constructor<?> constructor = PrivateConstructorClass.class.getDeclaredConstructor();
    constructor.setAccessible(true);
    String result = ReflectionHelper.tryMakeAccessible(constructor);
    assertNull(result);
    assertTrue(constructor.isAccessible());
  }

  // ==========================================================================
  // Tests for inner class constructors
  // ==========================================================================

  @Test
  public void tryMakeAccessible_staticInnerClassPrivateConstructor_returnsNull() throws Exception {
    Constructor<?> constructor = StaticInnerClass.class.getDeclaredConstructor();
    String result = ReflectionHelper.tryMakeAccessible(constructor);
    assertNull(result);
    assertTrue(constructor.isAccessible());
  }

  @Test
  public void tryMakeAccessible_staticInnerClassPublicConstructor_returnsNull() throws Exception {
    Constructor<?> constructor = PublicStaticInnerClass.class.getDeclaredConstructor();
    String result = ReflectionHelper.tryMakeAccessible(constructor);
    assertNull(result);
    assertTrue(constructor.isAccessible());
  }

  // ==========================================================================
  // Tests for constructors from java.lang classes
  // ==========================================================================

  @Test
  public void tryMakeAccessible_javaLangStringConstructor_returnsNull() throws Exception {
    Constructor<?> constructor = String.class.getDeclaredConstructor();
    String result = ReflectionHelper.tryMakeAccessible(constructor);
    assertNull(result);
  }

  @Test
  public void tryMakeAccessible_javaLangIntegerConstructor_returnsNull() throws Exception {
    Constructor<?> constructor = Integer.class.getDeclaredConstructor(int.class);
    String result = ReflectionHelper.tryMakeAccessible(constructor);
    assertNull(result);
  }

  // ==========================================================================
  // Tests for verifying error message format when failure occurs
  // ==========================================================================

  @Test
  public void tryMakeAccessible_errorMessageContainsConstructorDescription() throws Exception {
    // We verify that when failure occurs, the error message contains the constructor description
    // by checking the format of the constructorToString method
    Constructor<?> constructor = PrivateConstructorClass.class.getDeclaredConstructor();
    String constructorString = ReflectionHelper.constructorToString(constructor);
    assertTrue(constructorString.contains("PrivateConstructorClass"));
    assertTrue(constructorString.contains("()"));
  }

  @Test
  public void tryMakeAccessible_errorMessageFormatContainsExpectedParts() throws Exception {
    // Verify the error message format by checking constructorToString output
    Constructor<?> constructor = PublicClass.class.getDeclaredConstructor(String.class, int.class);
    String constructorString = ReflectionHelper.constructorToString(constructor);
    assertTrue(constructorString.contains("PublicClass"));
    assertTrue(constructorString.contains("String"));
    assertTrue(constructorString.contains("int"));
  }

  // ==========================================================================
  // Tests for various constructor types
  // ==========================================================================

  @Test
  public void tryMakeAccessible_constructorWithArrayParam_returnsNull() throws Exception {
    Constructor<?> constructor = PublicClass.class.getDeclaredConstructor(String[].class);
    String result = ReflectionHelper.tryMakeAccessible(constructor);
    assertNull(result);
    assertTrue(constructor.isAccessible());
  }

  @Test
  public void tryMakeAccessible_constructorWithPrimitiveArrayParam_returnsNull() throws Exception {
    Constructor<?> constructor = PublicClass.class.getDeclaredConstructor(int[].class);
    String result = ReflectionHelper.tryMakeAccessible(constructor);
    assertNull(result);
    assertTrue(constructor.isAccessible());
  }

  @Test
  public void tryMakeAccessible_constructorWithManyParams_returnsNull() throws Exception {
    Constructor<?> constructor = PublicClass.class.getDeclaredConstructor(
        String.class, int.class, boolean.class, double.class);
    String result = ReflectionHelper.tryMakeAccessible(constructor);
    assertNull(result);
    assertTrue(constructor.isAccessible());
  }

  // ==========================================================================
  // Tests for deeply nested class constructors
  // ==========================================================================

  @Test
  public void tryMakeAccessible_deeplyNestedPrivateConstructor_returnsNull() throws Exception {
    Constructor<?> constructor = StaticInnerClass.DeeplyNested.class.getDeclaredConstructor();
    String result = ReflectionHelper.tryMakeAccessible(constructor);
    assertNull(result);
    assertTrue(constructor.isAccessible());
  }

  // ==========================================================================
  // Helper classes for testing
  // ==========================================================================

  public static class PublicClass {
    public PublicClass() {}

    public PublicClass(String s, int i) {}

    public PublicClass(String[] arr) {}

    public PublicClass(int[] arr) {}

    public PublicClass(String s, int i, boolean b, double d) {}
  }

  public static class PrivateConstructorClass {
    private PrivateConstructorClass() {}
  }

  public static class ProtectedConstructorClass {
    protected ProtectedConstructorClass() {}
  }

  public static class PackagePrivateConstructorClass {
    PackagePrivateConstructorClass() {}
  }

  private static class StaticInnerClass {
    private StaticInnerClass() {}

    private static class DeeplyNested {
      private DeeplyNested() {}
    }
  }

  public static class PublicStaticInnerClass {
    public PublicStaticInnerClass() {}
  }
}
