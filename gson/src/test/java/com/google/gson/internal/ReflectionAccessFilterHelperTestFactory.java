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

package com.google.gson.internal;

import com.diffblue.cover.annotations.InterestingTestFactory;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

/**
 * Factory class for creating test objects for ReflectionAccessFilterHelper methods.
 */
public class ReflectionAccessFilterHelperTestFactory {

  /**
   * A simple test class with fields to use for reflection testing.
   */
  public static class TestClass {
    private String privateField;
    public String publicField;

    public TestClass() {
      this.privateField = "private";
      this.publicField = "public";
    }
  }

  /**
   * Creates an AccessibleObject (Field) for testing canAccess method.
   * This prevents NullPointerException in ReflectionAccessFilterHelper.canAccess.
   *
   * @return an AccessibleObject (Field) instance for testing
   * @throws NoSuchFieldException if the field cannot be found (should not happen)
   */
  @InterestingTestFactory
  public static AccessibleObject createAccessibleObject() throws NoSuchFieldException {
    return TestClass.class.getDeclaredField("privateField");
  }

  /**
   * Creates a public AccessibleObject (Field) for testing canAccess method.
   *
   * @return a public AccessibleObject (Field) instance for testing
   * @throws NoSuchFieldException if the field cannot be found (should not happen)
   */
  @InterestingTestFactory
  public static AccessibleObject createPublicAccessibleObject() throws NoSuchFieldException {
    return TestClass.class.getDeclaredField("publicField");
  }

  /**
   * Creates an Object instance to use as the second parameter for canAccess method.
   * This prevents NullPointerException when canAccess tries to check access on the object.
   *
   * @return an Object instance (TestClass) for testing
   */
  @InterestingTestFactory
  public static Object createObjectForAccess() {
    return new TestClass();
  }

  /**
   * Creates a Field instance for testing.
   *
   * @return a Field instance for testing
   * @throws NoSuchFieldException if the field cannot be found (should not happen)
   */
  @InterestingTestFactory
  public static Field createField() throws NoSuchFieldException {
    return TestClass.class.getDeclaredField("privateField");
  }
}
