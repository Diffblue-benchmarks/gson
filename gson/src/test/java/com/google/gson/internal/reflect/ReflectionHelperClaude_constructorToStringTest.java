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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map;
import org.junit.Test;

/**
 * Tests for {@link ReflectionHelper#constructorToString(java.lang.reflect.Constructor)}.
 */
public class ReflectionHelperClaude_constructorToStringTest {

  // ==========================================================================
  // Tests for constructors with no parameters
  // ==========================================================================

  @Test
  public void constructorToString_noParams() throws Exception {
    Constructor<?> constructor = TestClass.class.getDeclaredConstructor();
    String result = ReflectionHelper.constructorToString(constructor);
    assertEquals(
        "com.google.gson.internal.reflect.ReflectionHelperClaude_constructorToStringTest$TestClass()",
        result);
  }

  @Test
  public void constructorToString_noParams_privateConstructor() throws Exception {
    Constructor<?> constructor = PrivateConstructorClass.class.getDeclaredConstructor();
    String result = ReflectionHelper.constructorToString(constructor);
    assertEquals(
        "com.google.gson.internal.reflect.ReflectionHelperClaude_constructorToStringTest$PrivateConstructorClass()",
        result);
  }

  // ==========================================================================
  // Tests for constructors with one parameter
  // ==========================================================================

  @Test
  public void constructorToString_oneStringParam() throws Exception {
    Constructor<?> constructor = TestClass.class.getDeclaredConstructor(String.class);
    String result = ReflectionHelper.constructorToString(constructor);
    assertEquals(
        "com.google.gson.internal.reflect.ReflectionHelperClaude_constructorToStringTest$TestClass(String)",
        result);
  }

  @Test
  public void constructorToString_oneIntParam() throws Exception {
    Constructor<?> constructor = TestClass.class.getDeclaredConstructor(int.class);
    String result = ReflectionHelper.constructorToString(constructor);
    assertEquals(
        "com.google.gson.internal.reflect.ReflectionHelperClaude_constructorToStringTest$TestClass(int)",
        result);
  }

  @Test
  public void constructorToString_oneObjectParam() throws Exception {
    Constructor<?> constructor = TestClass.class.getDeclaredConstructor(Object.class);
    String result = ReflectionHelper.constructorToString(constructor);
    assertEquals(
        "com.google.gson.internal.reflect.ReflectionHelperClaude_constructorToStringTest$TestClass(Object)",
        result);
  }

  // ==========================================================================
  // Tests for constructors with multiple parameters
  // ==========================================================================

  @Test
  public void constructorToString_twoParams() throws Exception {
    Constructor<?> constructor = TestClass.class.getDeclaredConstructor(String.class, int.class);
    String result = ReflectionHelper.constructorToString(constructor);
    assertEquals(
        "com.google.gson.internal.reflect.ReflectionHelperClaude_constructorToStringTest$TestClass(String, int)",
        result);
  }

  @Test
  public void constructorToString_threeParams() throws Exception {
    Constructor<?> constructor = TestClass.class.getDeclaredConstructor(String.class, int.class, boolean.class);
    String result = ReflectionHelper.constructorToString(constructor);
    assertEquals(
        "com.google.gson.internal.reflect.ReflectionHelperClaude_constructorToStringTest$TestClass(String, int, boolean)",
        result);
  }

  @Test
  public void constructorToString_manyParams() throws Exception {
    Constructor<?> constructor = TestClass.class.getDeclaredConstructor(
        String.class, int.class, boolean.class, double.class, long.class);
    String result = ReflectionHelper.constructorToString(constructor);
    assertEquals(
        "com.google.gson.internal.reflect.ReflectionHelperClaude_constructorToStringTest$TestClass(String, int, boolean, double, long)",
        result);
  }

  // ==========================================================================
  // Tests for constructors with array parameters
  // ==========================================================================

  @Test
  public void constructorToString_stringArrayParam() throws Exception {
    Constructor<?> constructor = TestClass.class.getDeclaredConstructor(String[].class);
    String result = ReflectionHelper.constructorToString(constructor);
    assertEquals(
        "com.google.gson.internal.reflect.ReflectionHelperClaude_constructorToStringTest$TestClass(String[])",
        result);
  }

  @Test
  public void constructorToString_primitiveArrayParam() throws Exception {
    Constructor<?> constructor = TestClass.class.getDeclaredConstructor(int[].class);
    String result = ReflectionHelper.constructorToString(constructor);
    assertEquals(
        "com.google.gson.internal.reflect.ReflectionHelperClaude_constructorToStringTest$TestClass(int[])",
        result);
  }

  @Test
  public void constructorToString_twoDimensionalArrayParam() throws Exception {
    Constructor<?> constructor = TestClass.class.getDeclaredConstructor(int[][].class);
    String result = ReflectionHelper.constructorToString(constructor);
    assertEquals(
        "com.google.gson.internal.reflect.ReflectionHelperClaude_constructorToStringTest$TestClass(int[][])",
        result);
  }

  // ==========================================================================
  // Tests for constructors from java.lang classes
  // ==========================================================================

  @Test
  public void constructorToString_javaLangString_noParams() throws Exception {
    Constructor<?> constructor = String.class.getDeclaredConstructor();
    String result = ReflectionHelper.constructorToString(constructor);
    assertEquals("java.lang.String()", result);
  }

  @Test
  public void constructorToString_javaLangString_charArrayParam() throws Exception {
    Constructor<?> constructor = String.class.getDeclaredConstructor(char[].class);
    String result = ReflectionHelper.constructorToString(constructor);
    assertEquals("java.lang.String(char[])", result);
  }

  @Test
  public void constructorToString_javaLangString_charArrayIntIntParams() throws Exception {
    Constructor<?> constructor = String.class.getDeclaredConstructor(char[].class, int.class, int.class);
    String result = ReflectionHelper.constructorToString(constructor);
    assertEquals("java.lang.String(char[], int, int)", result);
  }

  @Test
  public void constructorToString_javaLangInteger() throws Exception {
    Constructor<?> constructor = Integer.class.getDeclaredConstructor(int.class);
    String result = ReflectionHelper.constructorToString(constructor);
    assertEquals("java.lang.Integer(int)", result);
  }

  // ==========================================================================
  // Tests for inner class constructors
  // ==========================================================================

  @Test
  public void constructorToString_staticInnerClass() throws Exception {
    Constructor<?> constructor = StaticInnerClass.class.getDeclaredConstructor();
    String result = ReflectionHelper.constructorToString(constructor);
    assertEquals(
        "com.google.gson.internal.reflect.ReflectionHelperClaude_constructorToStringTest$StaticInnerClass()",
        result);
  }

  @Test
  public void constructorToString_staticInnerClassWithParams() throws Exception {
    Constructor<?> constructor = StaticInnerClass.class.getDeclaredConstructor(String.class, int.class);
    String result = ReflectionHelper.constructorToString(constructor);
    assertEquals(
        "com.google.gson.internal.reflect.ReflectionHelperClaude_constructorToStringTest$StaticInnerClass(String, int)",
        result);
  }

  @Test
  public void constructorToString_deeplyNestedClass() throws Exception {
    Constructor<?> constructor = StaticInnerClass.DeeplyNested.class.getDeclaredConstructor();
    String result = ReflectionHelper.constructorToString(constructor);
    assertEquals(
        "com.google.gson.internal.reflect.ReflectionHelperClaude_constructorToStringTest$StaticInnerClass$DeeplyNested()",
        result);
  }

  // ==========================================================================
  // Tests for constructors with generic type parameters (erased to raw types)
  // ==========================================================================

  @Test
  public void constructorToString_listParam() throws Exception {
    Constructor<?> constructor = TestClass.class.getDeclaredConstructor(List.class);
    String result = ReflectionHelper.constructorToString(constructor);
    assertEquals(
        "com.google.gson.internal.reflect.ReflectionHelperClaude_constructorToStringTest$TestClass(List)",
        result);
  }

  @Test
  public void constructorToString_mapParam() throws Exception {
    Constructor<?> constructor = TestClass.class.getDeclaredConstructor(Map.class);
    String result = ReflectionHelper.constructorToString(constructor);
    assertEquals(
        "com.google.gson.internal.reflect.ReflectionHelperClaude_constructorToStringTest$TestClass(Map)",
        result);
  }

  // ==========================================================================
  // Tests for format verification
  // ==========================================================================

  @Test
  public void constructorToString_startsWithDeclaringClassName() throws Exception {
    Constructor<?> constructor = TestClass.class.getDeclaredConstructor();
    String result = ReflectionHelper.constructorToString(constructor);
    assertTrue(result.startsWith("com.google.gson.internal.reflect.ReflectionHelperClaude_constructorToStringTest$TestClass"));
  }

  @Test
  public void constructorToString_containsParentheses() throws Exception {
    Constructor<?> constructor = TestClass.class.getDeclaredConstructor();
    String result = ReflectionHelper.constructorToString(constructor);
    assertTrue(result.contains("("));
    assertTrue(result.contains(")"));
  }

  @Test
  public void constructorToString_endsWithClosingParen() throws Exception {
    Constructor<?> constructor = TestClass.class.getDeclaredConstructor(String.class);
    String result = ReflectionHelper.constructorToString(constructor);
    assertTrue(result.endsWith(")"));
  }

  @Test
  public void constructorToString_multipleParams_separatedByCommaSpace() throws Exception {
    Constructor<?> constructor = TestClass.class.getDeclaredConstructor(String.class, int.class);
    String result = ReflectionHelper.constructorToString(constructor);
    assertTrue(result.contains(", "));
  }

  // ==========================================================================
  // Tests for all primitive types
  // ==========================================================================

  @Test
  public void constructorToString_allPrimitiveTypes() throws Exception {
    Constructor<?> constructor = TestClass.class.getDeclaredConstructor(
        byte.class, short.class, int.class, long.class,
        float.class, double.class, boolean.class, char.class);
    String result = ReflectionHelper.constructorToString(constructor);
    assertEquals(
        "com.google.gson.internal.reflect.ReflectionHelperClaude_constructorToStringTest$TestClass(byte, short, int, long, float, double, boolean, char)",
        result);
  }

  // ==========================================================================
  // Helper classes for testing
  // ==========================================================================

  public static class TestClass {
    public TestClass() {}
    public TestClass(String s) {}
    public TestClass(int i) {}
    public TestClass(Object o) {}
    public TestClass(String s, int i) {}
    public TestClass(String s, int i, boolean b) {}
    public TestClass(String s, int i, boolean b, double d, long l) {}
    public TestClass(String[] arr) {}
    public TestClass(int[] arr) {}
    public TestClass(int[][] arr) {}
    public TestClass(List<String> list) {}
    public TestClass(Map<String, Integer> map) {}
    public TestClass(byte b, short s, int i, long l, float f, double d, boolean bool, char c) {}
  }

  public static class PrivateConstructorClass {
    private PrivateConstructorClass() {}
  }

  public static class StaticInnerClass {
    public StaticInnerClass() {}
    public StaticInnerClass(String s, int i) {}

    public static class DeeplyNested {
      public DeeplyNested() {}
    }
  }
}
