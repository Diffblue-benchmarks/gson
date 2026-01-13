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
 * Tests for {@link ReflectionHelper#isStatic(Class)}.
 */
public class ReflectionHelperClaude_isStaticTest {

  // ==========================================================================
  // Tests for static nested classes
  // ==========================================================================

  @Test
  public void isStatic_staticNestedClass_returnsTrue() {
    assertTrue(ReflectionHelper.isStatic(StaticNestedClass.class));
  }

  @Test
  public void isStatic_publicStaticNestedClass_returnsTrue() {
    assertTrue(ReflectionHelper.isStatic(PublicStaticNestedClass.class));
  }

  @Test
  public void isStatic_privateStaticNestedClass_returnsTrue() {
    assertTrue(ReflectionHelper.isStatic(PrivateStaticNestedClass.class));
  }

  @Test
  public void isStatic_protectedStaticNestedClass_returnsTrue() {
    assertTrue(ReflectionHelper.isStatic(ProtectedStaticNestedClass.class));
  }

  @Test
  public void isStatic_deeplyNestedStaticClass_returnsTrue() {
    assertTrue(ReflectionHelper.isStatic(StaticNestedClass.DeeplyNestedStatic.class));
  }

  // ==========================================================================
  // Tests for non-static inner classes
  // ==========================================================================

  @Test
  public void isStatic_innerClass_returnsFalse() {
    assertFalse(ReflectionHelper.isStatic(InnerClass.class));
  }

  @Test
  public void isStatic_publicInnerClass_returnsFalse() {
    assertFalse(ReflectionHelper.isStatic(PublicInnerClass.class));
  }

  @Test
  public void isStatic_privateInnerClass_returnsFalse() {
    assertFalse(ReflectionHelper.isStatic(PrivateInnerClass.class));
  }

  @Test
  public void isStatic_protectedInnerClass_returnsFalse() {
    assertFalse(ReflectionHelper.isStatic(ProtectedInnerClass.class));
  }

  // ==========================================================================
  // Tests for top-level classes
  // ==========================================================================

  @Test
  public void isStatic_topLevelClass_returnsFalse() {
    // Top-level classes cannot have the static modifier
    assertFalse(ReflectionHelper.isStatic(ReflectionHelperClaude_isStaticTest.class));
  }

  @Test
  public void isStatic_javaLangString_returnsFalse() {
    assertFalse(ReflectionHelper.isStatic(String.class));
  }

  @Test
  public void isStatic_javaLangInteger_returnsFalse() {
    assertFalse(ReflectionHelper.isStatic(Integer.class));
  }

  @Test
  public void isStatic_javaLangObject_returnsFalse() {
    assertFalse(ReflectionHelper.isStatic(Object.class));
  }

  // ==========================================================================
  // Tests for primitive types
  // ==========================================================================

  @Test
  public void isStatic_primitiveInt_returnsFalse() {
    assertFalse(ReflectionHelper.isStatic(int.class));
  }

  @Test
  public void isStatic_primitiveBoolean_returnsFalse() {
    assertFalse(ReflectionHelper.isStatic(boolean.class));
  }

  @Test
  public void isStatic_primitiveDouble_returnsFalse() {
    assertFalse(ReflectionHelper.isStatic(double.class));
  }

  // ==========================================================================
  // Tests for array types
  // ==========================================================================

  @Test
  public void isStatic_arrayOfPrimitive_returnsFalse() {
    assertFalse(ReflectionHelper.isStatic(int[].class));
  }

  @Test
  public void isStatic_arrayOfObject_returnsFalse() {
    assertFalse(ReflectionHelper.isStatic(String[].class));
  }

  @Test
  public void isStatic_arrayOfStaticNestedClass_returnsFalse() {
    // Arrays themselves are not static classes
    assertFalse(ReflectionHelper.isStatic(StaticNestedClass[].class));
  }

  // ==========================================================================
  // Tests for interfaces
  // ==========================================================================

  @Test
  public void isStatic_staticNestedInterface_returnsTrue() {
    // Nested interfaces are implicitly static
    assertTrue(ReflectionHelper.isStatic(StaticNestedInterface.class));
  }

  @Test
  public void isStatic_topLevelInterface_returnsFalse() {
    assertFalse(ReflectionHelper.isStatic(Runnable.class));
  }

  // ==========================================================================
  // Tests for enums
  // ==========================================================================

  @Test
  public void isStatic_staticNestedEnum_returnsTrue() {
    // Nested enums are implicitly static
    assertTrue(ReflectionHelper.isStatic(StaticNestedEnum.class));
  }

  @Test
  public void isStatic_topLevelEnum_returnsFalse() {
    assertFalse(ReflectionHelper.isStatic(TopLevelEnum.class));
  }

  // ==========================================================================
  // Tests for anonymous classes
  // ==========================================================================

  @Test
  public void isStatic_anonymousClass_returnsFalse() {
    Runnable anonymous = new Runnable() {
      @Override
      public void run() {}
    };
    assertFalse(ReflectionHelper.isStatic(anonymous.getClass()));
  }

  // ==========================================================================
  // Tests for local classes
  // ==========================================================================

  @Test
  public void isStatic_localClass_returnsFalse() {
    class LocalClass {}
    assertFalse(ReflectionHelper.isStatic(LocalClass.class));
  }

  @Test
  public void isStatic_localClassInStaticMethod_returnsFalse() {
    // Even local classes in static methods are not static
    Class<?> localClass = getLocalClassFromStaticMethod();
    assertFalse(ReflectionHelper.isStatic(localClass));
  }

  private static Class<?> getLocalClassFromStaticMethod() {
    class LocalInStaticMethod {}
    return LocalInStaticMethod.class;
  }

  // ==========================================================================
  // Tests for mixed nesting
  // ==========================================================================

  @Test
  public void isStatic_innerClassInsideStaticClass_returnsFalse() {
    assertFalse(ReflectionHelper.isStatic(StaticNestedClass.InnerInsideStatic.class));
  }

  @Test
  public void isStatic_staticClassInsideInnerClass_returnsTrue() {
    assertTrue(ReflectionHelper.isStatic(InnerClass.StaticInsideInner.class));
  }

  // ==========================================================================
  // Helper classes for testing
  // ==========================================================================

  static class StaticNestedClass {
    static class DeeplyNestedStatic {}
    class InnerInsideStatic {}
  }

  public static class PublicStaticNestedClass {}

  private static class PrivateStaticNestedClass {}

  protected static class ProtectedStaticNestedClass {}

  class InnerClass {
    static class StaticInsideInner {}
  }

  public class PublicInnerClass {}

  private class PrivateInnerClass {}

  protected class ProtectedInnerClass {}

  interface StaticNestedInterface {}

  enum StaticNestedEnum {
    VALUE
  }
}

// Top-level enum for testing
enum TopLevelEnum {
  VALUE
}
