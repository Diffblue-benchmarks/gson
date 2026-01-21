/*
 * Copyright (C) 2024 Google Inc.
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

/**
 * Factory class for creating reflection objects for testing ReflectionHelper. This factory helps
 * the Diffblue Cover tool generate tests that require valid Constructor, Field, and
 * AccessibleObject instances.
 */
public class ReflectionHelperFactory {

  /**
   * Creates a valid Constructor object for testing constructorToString. Returns a constructor from
   * a simple test class to avoid NullPointerException.
   *
   * @return a valid Constructor object
   */
  @InterestingTestFactory
  public static Constructor<?> createConstructor() {
    try {
      return TestClass.class.getDeclaredConstructor();
    } catch (NoSuchMethodException e) {
      throw new RuntimeException("Failed to create test constructor", e);
    }
  }

  /**
   * Creates a valid Constructor object with parameters for testing constructorToString. Returns a
   * constructor with parameters from a simple test class to avoid NullPointerException.
   *
   * @return a valid Constructor object with parameters
   */
  @InterestingTestFactory
  public static Constructor<?> createConstructorWithParameters() {
    try {
      return TestClass.class.getDeclaredConstructor(String.class, int.class);
    } catch (NoSuchMethodException e) {
      throw new RuntimeException("Failed to create test constructor with parameters", e);
    }
  }

  /**
   * Creates a valid Field object for testing fieldToString. Returns a field from a simple test
   * class to avoid NullPointerException.
   *
   * @return a valid Field object
   */
  @InterestingTestFactory
  public static Field createField() {
    try {
      return TestClass.class.getDeclaredField("testField");
    } catch (NoSuchFieldException e) {
      throw new RuntimeException("Failed to create test field", e);
    }
  }

  /**
   * Creates a valid AccessibleObject (Field) for testing getAccessibleObjectDescription. Returns a
   * field from a simple test class to avoid NullPointerException.
   *
   * @return a valid AccessibleObject instance
   */
  @InterestingTestFactory
  public static AccessibleObject createAccessibleObject() {
    try {
      return TestClass.class.getDeclaredField("testField");
    } catch (NoSuchFieldException e) {
      throw new RuntimeException("Failed to create test accessible object", e);
    }
  }

  /**
   * Creates a valid Class object that is NOT a record for testing getAccessor. This method returns
   * a regular class to avoid UnsupportedOperationException when records are not supported.
   *
   * @return a valid non-record Class object
   */
  @InterestingTestFactory
  public static Class<?> createNonRecordClass() {
    return TestClass.class;
  }

  /**
   * Creates a valid Field object from a non-record class for testing getAccessor. Returns a field
   * that won't trigger the record-related code path.
   *
   * @return a valid Field object from a non-record class
   */
  @InterestingTestFactory
  public static Field createNonRecordField() {
    try {
      return TestClass.class.getDeclaredField("testField");
    } catch (NoSuchFieldException e) {
      throw new RuntimeException("Failed to create test field from non-record class", e);
    }
  }

  /**
   * Creates a valid Class object that is a record for testing getRecordComponentNames. This method
   * returns a record class to avoid UnsupportedOperationException. Only returns a record class if
   * the JVM supports records.
   *
   * @return a valid record Class object if records are supported, otherwise a regular class
   */
  @InterestingTestFactory
  public static Class<?> createRecordClass() {
    // Try to load a record class if records are supported on this JVM
    // We use a known record class from the test suite
    try {
      Class<?> recordClass =
          Class.forName("com.google.gson.functional.Java17RecordTest$RecordWithCustomNames");
      // Verify it's actually a record using ReflectionHelper
      if (ReflectionHelper.isRecord(recordClass)) {
        return recordClass;
      }
    } catch (ClassNotFoundException e) {
      // Records not available or class not found
    }
    // If records are not supported, return a regular class
    // This prevents getRecordComponentNames from being called
    return TestClass.class;
  }

  /**
   * Creates a valid Class object for testing that explicitly checks if it's a record. Returns a
   * record class only if records are supported on the JVM.
   *
   * @return a Class object appropriate for the JVM's record support
   */
  @InterestingTestFactory
  public static Class<?> createClassForRecordTesting() {
    return createRecordClass();
  }

  /** Simple test class with fields and constructors that can be used for testing. */
  private static class TestClass {
    public String testField;
    public int anotherField;

    public TestClass() {}

    public TestClass(String testField, int anotherField) {
      this.testField = testField;
      this.anotherField = anotherField;
    }
  }
}
