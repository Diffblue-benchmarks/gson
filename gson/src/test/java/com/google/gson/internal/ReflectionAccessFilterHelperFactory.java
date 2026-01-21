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

package com.google.gson.internal;

import com.diffblue.cover.annotations.InterestingTestFactory;
import java.lang.reflect.AccessibleObject;

/**
 * Factory class for creating AccessibleObject instances for testing ReflectionAccessFilterHelper.
 * This factory helps the Diffblue Cover tool generate tests that require valid AccessibleObject
 * instances.
 */
public class ReflectionAccessFilterHelperFactory {

  /**
   * Creates a valid AccessibleObject (Field) for testing ReflectionAccessFilterHelper.canAccess.
   * Returns a public field from a simple test class to avoid NullPointerException.
   *
   * @return a valid AccessibleObject
   */
  @InterestingTestFactory
  public static AccessibleObject createAccessibleObject() {
    try {
      return TestClass.class.getDeclaredField("testField");
    } catch (NoSuchFieldException e) {
      throw new RuntimeException("Failed to create test field", e);
    }
  }

  /**
   * Creates a valid Object instance that can be used with AccessibleObject.canAccess. Returns an
   * instance of the test class.
   *
   * @return a valid Object instance
   */
  @InterestingTestFactory
  public static Object createTestObject() {
    return new TestClass();
  }

  /** Simple test class with a field that can be used for testing. */
  private static class TestClass {
    public String testField;
  }
}
