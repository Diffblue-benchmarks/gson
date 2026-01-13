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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests for {@link ReflectionHelper#isAnonymousOrNonStaticLocal(Class)}.
 */
public class ReflectionHelperClaude_isAnonymousOrNonStaticLocalTest {

  // ==========================================================================
  // Tests for anonymous classes
  // ==========================================================================

  @Test
  public void isAnonymousOrNonStaticLocal_anonymousClass_returnsTrue() {
    Object anonymous =
        new Runnable() {
          @Override
          public void run() {}
        };
    assertTrue(ReflectionHelper.isAnonymousOrNonStaticLocal(anonymous.getClass()));
  }

  @Test
  public void isAnonymousOrNonStaticLocal_anonymousClassInStaticMethod_returnsTrue() {
    Class<?> anonymousClass = getAnonymousClassFromStaticMethod();
    assertTrue(ReflectionHelper.isAnonymousOrNonStaticLocal(anonymousClass));
  }

  private static Class<?> getAnonymousClassFromStaticMethod() {
    Object anonymous =
        new Runnable() {
          @Override
          public void run() {}
        };
    return anonymous.getClass();
  }

  // ==========================================================================
  // Tests for local classes in instance methods (non-static local)
  // ==========================================================================

  @Test
  public void isAnonymousOrNonStaticLocal_localClassInInstanceMethod_returnsTrue() {
    class LocalClass {}
    assertTrue(ReflectionHelper.isAnonymousOrNonStaticLocal(LocalClass.class));
  }

  // ==========================================================================
  // Tests for local classes in static methods
  // ==========================================================================

  @Test
  public void isAnonymousOrNonStaticLocal_localClassInStaticMethod_returnsTrue() {
    // Local classes in static methods are still considered "non-static local" by this method
    // because the class itself doesn't have the static modifier
    Class<?> localClass = getLocalClassFromStaticMethod();
    assertTrue(ReflectionHelper.isAnonymousOrNonStaticLocal(localClass));
  }

  private static Class<?> getLocalClassFromStaticMethod() {
    class LocalInStaticMethod {}
    return LocalInStaticMethod.class;
  }

  // ==========================================================================
  // Tests for static nested classes (should return false)
  // ==========================================================================

  @Test
  public void isAnonymousOrNonStaticLocal_staticNestedClass_returnsFalse() {
    assertFalse(ReflectionHelper.isAnonymousOrNonStaticLocal(StaticNestedClass.class));
  }

  @Test
  public void isAnonymousOrNonStaticLocal_publicStaticNestedClass_returnsFalse() {
    assertFalse(ReflectionHelper.isAnonymousOrNonStaticLocal(PublicStaticNestedClass.class));
  }

  @Test
  public void isAnonymousOrNonStaticLocal_privateStaticNestedClass_returnsFalse() {
    assertFalse(ReflectionHelper.isAnonymousOrNonStaticLocal(PrivateStaticNestedClass.class));
  }

  @Test
  public void isAnonymousOrNonStaticLocal_deeplyNestedStaticClass_returnsFalse() {
    assertFalse(
        ReflectionHelper.isAnonymousOrNonStaticLocal(StaticNestedClass.DeeplyNestedStatic.class));
  }

  // ==========================================================================
  // Tests for non-static inner classes (not local, so should return false)
  // ==========================================================================

  @Test
  public void isAnonymousOrNonStaticLocal_innerClass_returnsFalse() {
    // Inner classes are member classes, not local classes
    assertFalse(ReflectionHelper.isAnonymousOrNonStaticLocal(InnerClass.class));
  }

  @Test
  public void isAnonymousOrNonStaticLocal_publicInnerClass_returnsFalse() {
    assertFalse(ReflectionHelper.isAnonymousOrNonStaticLocal(PublicInnerClass.class));
  }

  @Test
  public void isAnonymousOrNonStaticLocal_privateInnerClass_returnsFalse() {
    assertFalse(ReflectionHelper.isAnonymousOrNonStaticLocal(PrivateInnerClass.class));
  }

  // ==========================================================================
  // Tests for top-level classes (should return false)
  // ==========================================================================

  @Test
  public void isAnonymousOrNonStaticLocal_topLevelClass_returnsFalse() {
    assertFalse(
        ReflectionHelper.isAnonymousOrNonStaticLocal(
            ReflectionHelperClaude_isAnonymousOrNonStaticLocalTest.class));
  }

  @Test
  public void isAnonymousOrNonStaticLocal_javaLangString_returnsFalse() {
    assertFalse(ReflectionHelper.isAnonymousOrNonStaticLocal(String.class));
  }

  @Test
  public void isAnonymousOrNonStaticLocal_javaLangObject_returnsFalse() {
    assertFalse(ReflectionHelper.isAnonymousOrNonStaticLocal(Object.class));
  }

  // ==========================================================================
  // Tests for primitive types (should return false)
  // ==========================================================================

  @Test
  public void isAnonymousOrNonStaticLocal_primitiveInt_returnsFalse() {
    assertFalse(ReflectionHelper.isAnonymousOrNonStaticLocal(int.class));
  }

  @Test
  public void isAnonymousOrNonStaticLocal_primitiveBoolean_returnsFalse() {
    assertFalse(ReflectionHelper.isAnonymousOrNonStaticLocal(boolean.class));
  }

  // ==========================================================================
  // Tests for array types (should return false)
  // ==========================================================================

  @Test
  public void isAnonymousOrNonStaticLocal_arrayType_returnsFalse() {
    assertFalse(ReflectionHelper.isAnonymousOrNonStaticLocal(int[].class));
  }

  @Test
  public void isAnonymousOrNonStaticLocal_objectArrayType_returnsFalse() {
    assertFalse(ReflectionHelper.isAnonymousOrNonStaticLocal(String[].class));
  }

  // ==========================================================================
  // Tests for interfaces (should return false)
  // ==========================================================================

  @Test
  public void isAnonymousOrNonStaticLocal_nestedInterface_returnsFalse() {
    // Nested interfaces are implicitly static
    assertFalse(ReflectionHelper.isAnonymousOrNonStaticLocal(NestedInterface.class));
  }

  @Test
  public void isAnonymousOrNonStaticLocal_topLevelInterface_returnsFalse() {
    assertFalse(ReflectionHelper.isAnonymousOrNonStaticLocal(Runnable.class));
  }

  // ==========================================================================
  // Tests for enums (should return false)
  // ==========================================================================

  @Test
  public void isAnonymousOrNonStaticLocal_nestedEnum_returnsFalse() {
    // Nested enums are implicitly static
    assertFalse(ReflectionHelper.isAnonymousOrNonStaticLocal(NestedEnum.class));
  }

  // ==========================================================================
  // Helper classes for testing
  // ==========================================================================

  static class StaticNestedClass {
    static class DeeplyNestedStatic {}
  }

  public static class PublicStaticNestedClass {}

  private static class PrivateStaticNestedClass {}

  class InnerClass {}

  public class PublicInnerClass {}

  private class PrivateInnerClass {}

  interface NestedInterface {}

  enum NestedEnum {
    VALUE
  }
}
