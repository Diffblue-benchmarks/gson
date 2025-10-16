/*
 * Copyright (C) 2025 Google Inc.
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

import com.diffblue.cover.annotations.InterestingTestFactory;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Factory class for creating AccessibleObject instances for testing ReflectionHelper methods.
 */
public class ReflectionHelperTestFactory {

  /**
   * A simple test class with various constructors, fields, and methods to use for reflection.
   */
  private static class TestClass {
    private String testField;
    public int publicField;

    public TestClass() {}

    public TestClass(String param) {}

    public TestClass(int a, String b) {}

    public void testMethod() {}

    public String testMethodWithParam(int param) {
      return String.valueOf(param);
    }
  }

  /**
   * Creates a valid Constructor object for testing constructorToString method.
   * This factory method provides a non-null Constructor object to avoid NullPointerException
   * in ReflectionHelper.constructorToString.
   *
   * @return a Constructor instance for testing
   * @throws NoSuchMethodException if the constructor cannot be found (should not happen)
   */
  @InterestingTestFactory
  public static Constructor<?> createConstructor() throws NoSuchMethodException {
    return TestClass.class.getDeclaredConstructor();
  }

  /**
   * Creates a Constructor with single parameter for testing.
   *
   * @return a Constructor instance with one parameter
   * @throws NoSuchMethodException if the constructor cannot be found (should not happen)
   */
  @InterestingTestFactory
  public static Constructor<?> createConstructorWithSingleParameter() throws NoSuchMethodException {
    return TestClass.class.getDeclaredConstructor(String.class);
  }

  /**
   * Creates a Constructor with multiple parameters for testing.
   *
   * @return a Constructor instance with multiple parameters
   * @throws NoSuchMethodException if the constructor cannot be found (should not happen)
   */
  @InterestingTestFactory
  public static Constructor<?> createConstructorWithMultipleParameters() throws NoSuchMethodException {
    return TestClass.class.getDeclaredConstructor(int.class, String.class);
  }

  /**
   * Creates an AccessibleObject (Field) for testing makeAccessible method.
   * This prevents NullPointerException in ReflectionHelper.makeAccessible and getAccessibleObjectDescription.
   *
   * @return an AccessibleObject (Field) instance for testing
   * @throws NoSuchFieldException if the field cannot be found (should not happen)
   */
  @InterestingTestFactory
  public static AccessibleObject createAccessibleObjectField() throws NoSuchFieldException {
    return TestClass.class.getDeclaredField("testField");
  }

  /**
   * Creates an AccessibleObject (Method) for testing makeAccessible method.
   * This prevents NullPointerException in ReflectionHelper.makeAccessible and getAccessibleObjectDescription.
   *
   * @return an AccessibleObject (Method) instance for testing
   * @throws NoSuchMethodException if the method cannot be found (should not happen)
   */
  @InterestingTestFactory
  public static AccessibleObject createAccessibleObjectMethod() throws NoSuchMethodException {
    return TestClass.class.getDeclaredMethod("testMethod");
  }

  /**
   * Creates an AccessibleObject (Constructor) for testing makeAccessible method.
   * This prevents NullPointerException in ReflectionHelper.makeAccessible and getAccessibleObjectDescription.
   *
   * @return an AccessibleObject (Constructor) instance for testing
   * @throws NoSuchMethodException if the constructor cannot be found (should not happen)
   */
  @InterestingTestFactory
  public static AccessibleObject createAccessibleObjectConstructor() throws NoSuchMethodException {
    return TestClass.class.getDeclaredConstructor();
  }

  /**
   * Creates a Field instance for testing fieldToString method.
   *
   * @return a Field instance for testing
   * @throws NoSuchFieldException if the field cannot be found (should not happen)
   */
  @InterestingTestFactory
  public static Field createField() throws NoSuchFieldException {
    return TestClass.class.getDeclaredField("testField");
  }

  /**
   * Creates a Field instance with public visibility for testing.
   *
   * @return a public Field instance for testing
   * @throws NoSuchFieldException if the field cannot be found (should not happen)
   */
  @InterestingTestFactory
  public static Field createPublicField() throws NoSuchFieldException {
    return TestClass.class.getDeclaredField("publicField");
  }

  /**
   * Creates a Method instance for testing method-related methods.
   *
   * @return a Method instance for testing
   * @throws NoSuchMethodException if the method cannot be found (should not happen)
   */
  @InterestingTestFactory
  public static Method createMethod() throws NoSuchMethodException {
    return TestClass.class.getDeclaredMethod("testMethod");
  }

  /**
   * Creates a Method instance with parameters for testing.
   *
   * @return a Method instance with parameters for testing
   * @throws NoSuchMethodException if the method cannot be found (should not happen)
   */
  @InterestingTestFactory
  public static Method createMethodWithParameter() throws NoSuchMethodException {
    return TestClass.class.getDeclaredMethod("testMethodWithParam", int.class);
  }
}
