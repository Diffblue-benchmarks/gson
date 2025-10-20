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

package com.google.gson.internal;

import com.diffblue.cover.annotations.InterestingTestFactory;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

/** Factory class for creating test instances for ReflectionAccessFilterHelper tests. */
public class ReflectionAccessFilterHelperDiffblueTestFactory {

  /**
   * Factory method to create a valid AccessibleObject instance for testing. This prevents
   * NullPointerException when testing canAccess method.
   *
   * @return a valid AccessibleObject instance (a Field from a simple test class)
   */
  @InterestingTestFactory
  public static AccessibleObject createAccessibleObject() {
    try {
      // Create a simple test class and get one of its fields
      Field field = TestClass.class.getDeclaredField("testField");
      return field;
    } catch (NoSuchFieldException e) {
      throw new RuntimeException("Failed to create AccessibleObject for testing", e);
    }
  }

  /**
   * Factory method to create a valid Object instance for testing canAccess method. This provides a
   * non-null object that corresponds to the AccessibleObject created by createAccessibleObject(),
   * preventing NullPointerException when the reflection API invokes canAccess.
   *
   * @return a valid Object instance (an instance of TestClass)
   */
  @InterestingTestFactory
  public static Object createObjectForCanAccess() {
    return new TestClass();
  }

  /** Simple test class used to obtain an AccessibleObject (Field) for testing. */
  private static class TestClass {
    @SuppressWarnings("unused")
    private String testField;
  }
}
