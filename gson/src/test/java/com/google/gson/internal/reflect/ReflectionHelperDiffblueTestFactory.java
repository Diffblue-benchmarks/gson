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

import com.diffblue.cover.annotations.InterestingTestFactory;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;

/** Factory class for creating Constructor instances for Diffblue Cover testing. */
public class ReflectionHelperDiffblueTestFactory {

  /**
   * Creates a valid Constructor instance. This factory method avoids the NullPointerException that
   * would occur if a null Constructor is passed to ReflectionHelper.constructorToString().
   *
   * @return a Constructor instance for testing
   * @throws NoSuchMethodException if the constructor cannot be found
   */
  @InterestingTestFactory
  public static Constructor<?> createConstructor() throws NoSuchMethodException {
    // Use a simple test class with a no-arg constructor
    return TestClass.class.getDeclaredConstructor();
  }

  /**
   * Creates a valid Constructor instance with parameters. This factory method provides a
   * constructor with parameters for more comprehensive testing.
   *
   * @return a Constructor instance with parameters for testing
   * @throws NoSuchMethodException if the constructor cannot be found
   */
  @InterestingTestFactory
  public static Constructor<?> createConstructorWithParameters() throws NoSuchMethodException {
    // Use a constructor with multiple parameter types
    return TestClass.class.getDeclaredConstructor(String.class, int.class, boolean.class);
  }

  /**
   * Creates a valid AccessibleObject instance (Field). This factory method avoids the
   * NullPointerException that would occur if a null AccessibleObject is passed to
   * ReflectionHelper.makeAccessible().
   *
   * @return a Field instance (which is an AccessibleObject) for testing
   * @throws NoSuchFieldException if the field cannot be found
   */
  @InterestingTestFactory
  public static AccessibleObject createAccessibleObjectAsField() throws NoSuchFieldException {
    return TestClass.class.getDeclaredField("testField");
  }

  /**
   * Creates a valid AccessibleObject instance (Method). This factory method provides a Method
   * (which is an AccessibleObject) for testing.
   *
   * @return a Method instance (which is an AccessibleObject) for testing
   * @throws NoSuchMethodException if the method cannot be found
   */
  @InterestingTestFactory
  public static AccessibleObject createAccessibleObjectAsMethod() throws NoSuchMethodException {
    return TestClass.class.getDeclaredMethod("testMethod");
  }

  /**
   * Creates a valid AccessibleObject instance (Constructor). This factory method provides a
   * Constructor (which is an AccessibleObject) for testing.
   *
   * @return a Constructor instance (which is an AccessibleObject) for testing
   * @throws NoSuchMethodException if the constructor cannot be found
   */
  @InterestingTestFactory
  public static AccessibleObject createAccessibleObjectAsConstructor()
      throws NoSuchMethodException {
    return TestClass.class.getDeclaredConstructor();
  }

  /**
   * Creates a simple valid AccessibleObject instance for getAccessibleObjectDescription. This
   * factory method provides a basic Field that can be safely passed to methods expecting a non-null
   * AccessibleObject parameter, avoiding NullPointerException.
   *
   * @return a simple Field instance (which is an AccessibleObject) for testing
   * @throws NoSuchFieldException if the field cannot be found
   */
  @InterestingTestFactory
  public static AccessibleObject createSimpleAccessibleObject() throws NoSuchFieldException {
    return TestClass.class.getDeclaredField("testField");
  }

  /**
   * Creates a valid Field instance for testing getAccessor. This factory method provides a non-null
   * Field that can be safely passed to ReflectionHelper.getAccessor(), avoiding the
   * NullPointerException that would occur if a null Field parameter is passed.
   *
   * @return a Field instance for testing
   * @throws NoSuchFieldException if the field cannot be found
   */
  @InterestingTestFactory
  public static java.lang.reflect.Field createField() throws NoSuchFieldException {
    return TestClass.class.getDeclaredField("testField");
  }

  /**
   * Creates a valid Class instance for testing getRecordComponentNames. This factory method
   * provides a non-null Class that can be safely passed to
   * ReflectionHelper.getRecordComponentNames(), avoiding the NullPointerException that would occur
   * if a null Class parameter is passed.
   *
   * @return a Class instance for testing
   */
  @InterestingTestFactory
  public static Class<?> createClass() {
    return TestClass.class;
  }

  /**
   * Creates a valid Field instance for testing fieldToString. This factory method provides a
   * non-null Field that can be safely passed to ReflectionHelper.fieldToString(), avoiding the
   * NullPointerException that would occur if a null Field parameter is passed.
   *
   * @return a Field instance for testing fieldToString
   * @throws NoSuchFieldException if the field cannot be found
   */
  @InterestingTestFactory
  public static java.lang.reflect.Field createFieldForFieldToString() throws NoSuchFieldException {
    return TestClass.class.getDeclaredField("testField");
  }

  /**
   * Creates a valid Class instance for testing getCanonicalRecordConstructor. This factory method
   * provides a non-null Class that can be safely passed to
   * ReflectionHelper.getCanonicalRecordConstructor(), avoiding the NullPointerException that would
   * occur if a null Class parameter is passed.
   *
   * @return a Class instance for testing getCanonicalRecordConstructor
   */
  @InterestingTestFactory
  public static Class<?> createClassForGetCanonicalRecordConstructor() {
    return TestClass.class;
  }

  /**
   * Creates a valid Class instance to avoid NullPointerException in getCanonicalRecordConstructor.
   * This factory method provides a non-null Class parameter that prevents the NPE at
   * ReflectionHelper.java:273 where getRecordComponents.invoke(raw) is called. When raw is null,
   * the invoke method throws "Cannot invoke Object.getClass() because obj is null".
   *
   * @return a non-null Class instance for testing
   */
  @InterestingTestFactory
  public static Class<?> createNonNullClass() {
    return TestClass.class;
  }

  /**
   * Creates a valid AccessibleObject instance for testing makeAccessible. This factory method
   * provides a non-null AccessibleObject that can be safely passed to
   * ReflectionHelper.makeAccessible(), avoiding the NullPointerException that would occur when
   * getAccessibleObjectDescription attempts to call toString() on a null object.
   *
   * @return an AccessibleObject instance (Field) for testing makeAccessible
   * @throws NoSuchFieldException if the field cannot be found
   */
  @InterestingTestFactory
  public static AccessibleObject createAccessibleObjectForMakeAccessible()
      throws NoSuchFieldException {
    return TestClass.class.getDeclaredField("testField");
  }

  /**
   * Creates a valid Constructor instance for testing constructorToString. This factory method
   * provides a non-null Constructor that can be safely passed to
   * ReflectionHelper.constructorToString(), avoiding the NullPointerException that would occur if a
   * null Constructor parameter is passed.
   *
   * @return a Constructor instance for testing constructorToString
   * @throws NoSuchMethodException if the constructor cannot be found
   */
  @InterestingTestFactory
  public static Constructor<?> createConstructorForConstructorToString()
      throws NoSuchMethodException {
    return TestClass.class.getDeclaredConstructor();
  }

  /**
   * Creates a valid AccessibleObject instance for testing getAccessibleObjectDescription. This
   * factory method provides a non-null AccessibleObject (Field) that can be safely passed to
   * ReflectionHelper.getAccessibleObjectDescription(), avoiding the NullPointerException that would
   * occur when the method attempts to call toString() on a null object parameter at line 106.
   *
   * @return an AccessibleObject instance (Field) for testing getAccessibleObjectDescription
   * @throws NoSuchFieldException if the field cannot be found
   */
  @InterestingTestFactory
  public static AccessibleObject createAccessibleObjectForGetAccessibleObjectDescription()
      throws NoSuchFieldException {
    return TestClass.class.getDeclaredField("testField");
  }

  /**
   * Creates a valid Field instance for testing getAccessor specifically. This factory method
   * provides a non-null Field that can be safely passed to ReflectionHelper.getAccessor(), avoiding
   * the NullPointerException that would occur when getAccessor attempts to call field.getName() at
   * ReflectionHelper.java:292. The Field is obtained from a test class to ensure it has a valid
   * name and declaring class.
   *
   * @return a Field instance for testing getAccessor
   * @throws NoSuchFieldException if the field cannot be found
   */
  @InterestingTestFactory
  public static java.lang.reflect.Field createFieldForGetAccessor() throws NoSuchFieldException {
    return TestClass.class.getDeclaredField("testField");
  }

  /**
   * Creates a valid Constructor instance for testing tryMakeAccessible. This factory method
   * provides a non-null Constructor that can be safely passed to
   * ReflectionHelper.tryMakeAccessible(), avoiding the NullPointerException that would occur when
   * the method attempts to call constructorToString at line 172, which then tries to call
   * constructor.getDeclaringClass() at line 125.
   *
   * @return a Constructor instance for testing tryMakeAccessible
   * @throws NoSuchMethodException if the constructor cannot be found
   */
  @InterestingTestFactory
  public static Constructor<?> createConstructorForTryMakeAccessible()
      throws NoSuchMethodException {
    return TestClass.class.getDeclaredConstructor();
  }

  /**
   * Creates a valid Class instance for testing getRecordComponentNames. This factory method
   * provides a non-null Class that can be safely passed to
   * ReflectionHelper.getRecordComponentNames(), avoiding the NullPointerException that would occur
   * at line 259 when getRecordComponents.invoke() is called with a null Class parameter. The method
   * returns TestClass.class which is a valid, non-null Class instance.
   *
   * @return a Class instance for testing getRecordComponentNames
   */
  @InterestingTestFactory
  public static Class<?> createClassForGetRecordComponentNames() {
    return TestClass.class;
  }

  /** Simple test class used to obtain valid Constructor instances. */
  private static class TestClass {
    @SuppressWarnings("unused")
    private String testField;

    @SuppressWarnings("unused")
    public TestClass() {}

    @SuppressWarnings("unused")
    public TestClass(String str, int num, boolean flag) {}

    @SuppressWarnings("unused")
    private void testMethod() {}
  }
}
