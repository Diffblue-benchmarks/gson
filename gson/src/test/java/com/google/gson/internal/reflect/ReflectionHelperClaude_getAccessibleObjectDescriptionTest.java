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
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.junit.Test;

/**
 * Tests for {@link ReflectionHelper#getAccessibleObjectDescription(java.lang.reflect.AccessibleObject, boolean)}.
 */
public class ReflectionHelperClaude_getAccessibleObjectDescriptionTest {

  // ==========================================================================
  // Tests for Field descriptions
  // ==========================================================================

  @Test
  public void getAccessibleObjectDescription_field_lowercaseFirstLetter() throws Exception {
    Field field = TestClass.class.getDeclaredField("stringField");
    String description = ReflectionHelper.getAccessibleObjectDescription(field, false);
    assertEquals(
        "field 'com.google.gson.internal.reflect.ReflectionHelperClaude_getAccessibleObjectDescriptionTest$TestClass#stringField'",
        description);
  }

  @Test
  public void getAccessibleObjectDescription_field_uppercaseFirstLetter() throws Exception {
    Field field = TestClass.class.getDeclaredField("stringField");
    String description = ReflectionHelper.getAccessibleObjectDescription(field, true);
    assertEquals(
        "Field 'com.google.gson.internal.reflect.ReflectionHelperClaude_getAccessibleObjectDescriptionTest$TestClass#stringField'",
        description);
  }

  @Test
  public void getAccessibleObjectDescription_privateField_lowercaseFirstLetter() throws Exception {
    Field field = TestClass.class.getDeclaredField("privateIntField");
    String description = ReflectionHelper.getAccessibleObjectDescription(field, false);
    assertEquals(
        "field 'com.google.gson.internal.reflect.ReflectionHelperClaude_getAccessibleObjectDescriptionTest$TestClass#privateIntField'",
        description);
  }

  @Test
  public void getAccessibleObjectDescription_privateField_uppercaseFirstLetter() throws Exception {
    Field field = TestClass.class.getDeclaredField("privateIntField");
    String description = ReflectionHelper.getAccessibleObjectDescription(field, true);
    assertEquals(
        "Field 'com.google.gson.internal.reflect.ReflectionHelperClaude_getAccessibleObjectDescriptionTest$TestClass#privateIntField'",
        description);
  }

  @Test
  public void getAccessibleObjectDescription_staticField() throws Exception {
    Field field = TestClass.class.getDeclaredField("staticField");
    String description = ReflectionHelper.getAccessibleObjectDescription(field, false);
    assertTrue(description.contains("field"));
    assertTrue(description.contains("staticField"));
  }

  // ==========================================================================
  // Tests for Method descriptions
  // ==========================================================================

  @Test
  public void getAccessibleObjectDescription_methodNoParams_lowercaseFirstLetter() throws Exception {
    Method method = TestClass.class.getDeclaredMethod("noParamsMethod");
    String description = ReflectionHelper.getAccessibleObjectDescription(method, false);
    assertEquals(
        "method 'com.google.gson.internal.reflect.ReflectionHelperClaude_getAccessibleObjectDescriptionTest$TestClass#noParamsMethod()'",
        description);
  }

  @Test
  public void getAccessibleObjectDescription_methodNoParams_uppercaseFirstLetter() throws Exception {
    Method method = TestClass.class.getDeclaredMethod("noParamsMethod");
    String description = ReflectionHelper.getAccessibleObjectDescription(method, true);
    assertEquals(
        "Method 'com.google.gson.internal.reflect.ReflectionHelperClaude_getAccessibleObjectDescriptionTest$TestClass#noParamsMethod()'",
        description);
  }

  @Test
  public void getAccessibleObjectDescription_methodWithOneParam() throws Exception {
    Method method = TestClass.class.getDeclaredMethod("oneParamMethod", String.class);
    String description = ReflectionHelper.getAccessibleObjectDescription(method, false);
    assertEquals(
        "method 'com.google.gson.internal.reflect.ReflectionHelperClaude_getAccessibleObjectDescriptionTest$TestClass#oneParamMethod(String)'",
        description);
  }

  @Test
  public void getAccessibleObjectDescription_methodWithMultipleParams() throws Exception {
    Method method = TestClass.class.getDeclaredMethod("multiParamMethod", String.class, int.class, boolean.class);
    String description = ReflectionHelper.getAccessibleObjectDescription(method, false);
    assertEquals(
        "method 'com.google.gson.internal.reflect.ReflectionHelperClaude_getAccessibleObjectDescriptionTest$TestClass#multiParamMethod(String, int, boolean)'",
        description);
  }

  @Test
  public void getAccessibleObjectDescription_methodWithArrayParam() throws Exception {
    Method method = TestClass.class.getDeclaredMethod("arrayParamMethod", String[].class);
    String description = ReflectionHelper.getAccessibleObjectDescription(method, false);
    assertEquals(
        "method 'com.google.gson.internal.reflect.ReflectionHelperClaude_getAccessibleObjectDescriptionTest$TestClass#arrayParamMethod(String[])'",
        description);
  }

  @Test
  public void getAccessibleObjectDescription_methodWithPrimitiveArrayParam() throws Exception {
    Method method = TestClass.class.getDeclaredMethod("primitiveArrayParamMethod", int[].class);
    String description = ReflectionHelper.getAccessibleObjectDescription(method, false);
    assertEquals(
        "method 'com.google.gson.internal.reflect.ReflectionHelperClaude_getAccessibleObjectDescriptionTest$TestClass#primitiveArrayParamMethod(int[])'",
        description);
  }

  @Test
  public void getAccessibleObjectDescription_staticMethod() throws Exception {
    Method method = TestClass.class.getDeclaredMethod("staticMethod");
    String description = ReflectionHelper.getAccessibleObjectDescription(method, false);
    assertTrue(description.contains("method"));
    assertTrue(description.contains("staticMethod"));
    assertTrue(description.contains("()"));
  }

  @Test
  public void getAccessibleObjectDescription_privateMethod() throws Exception {
    Method method = TestClass.class.getDeclaredMethod("privateMethod");
    String description = ReflectionHelper.getAccessibleObjectDescription(method, false);
    assertTrue(description.contains("method"));
    assertTrue(description.contains("privateMethod"));
  }

  // ==========================================================================
  // Tests for Constructor descriptions
  // ==========================================================================

  @Test
  public void getAccessibleObjectDescription_constructorNoParams_lowercaseFirstLetter() throws Exception {
    Constructor<?> constructor = TestClass.class.getDeclaredConstructor();
    String description = ReflectionHelper.getAccessibleObjectDescription(constructor, false);
    assertEquals(
        "constructor 'com.google.gson.internal.reflect.ReflectionHelperClaude_getAccessibleObjectDescriptionTest$TestClass()'",
        description);
  }

  @Test
  public void getAccessibleObjectDescription_constructorNoParams_uppercaseFirstLetter() throws Exception {
    Constructor<?> constructor = TestClass.class.getDeclaredConstructor();
    String description = ReflectionHelper.getAccessibleObjectDescription(constructor, true);
    assertEquals(
        "Constructor 'com.google.gson.internal.reflect.ReflectionHelperClaude_getAccessibleObjectDescriptionTest$TestClass()'",
        description);
  }

  @Test
  public void getAccessibleObjectDescription_constructorWithOneParam() throws Exception {
    Constructor<?> constructor = TestClass.class.getDeclaredConstructor(String.class);
    String description = ReflectionHelper.getAccessibleObjectDescription(constructor, false);
    assertEquals(
        "constructor 'com.google.gson.internal.reflect.ReflectionHelperClaude_getAccessibleObjectDescriptionTest$TestClass(String)'",
        description);
  }

  @Test
  public void getAccessibleObjectDescription_constructorWithMultipleParams() throws Exception {
    Constructor<?> constructor = TestClass.class.getDeclaredConstructor(String.class, int.class);
    String description = ReflectionHelper.getAccessibleObjectDescription(constructor, false);
    assertEquals(
        "constructor 'com.google.gson.internal.reflect.ReflectionHelperClaude_getAccessibleObjectDescriptionTest$TestClass(String, int)'",
        description);
  }

  @Test
  public void getAccessibleObjectDescription_privateConstructor() throws Exception {
    Constructor<?> constructor = PrivateConstructorClass.class.getDeclaredConstructor();
    String description = ReflectionHelper.getAccessibleObjectDescription(constructor, false);
    assertTrue(description.contains("constructor"));
    assertTrue(description.contains("PrivateConstructorClass"));
    assertTrue(description.contains("()"));
  }

  @Test
  public void getAccessibleObjectDescription_constructorWithArrayParam() throws Exception {
    Constructor<?> constructor = TestClass.class.getDeclaredConstructor(String[].class);
    String description = ReflectionHelper.getAccessibleObjectDescription(constructor, false);
    assertEquals(
        "constructor 'com.google.gson.internal.reflect.ReflectionHelperClaude_getAccessibleObjectDescriptionTest$TestClass(String[])'",
        description);
  }

  // ==========================================================================
  // Tests for inner class members
  // ==========================================================================

  @Test
  public void getAccessibleObjectDescription_innerClassField() throws Exception {
    Field field = InnerClass.class.getDeclaredField("innerField");
    String description = ReflectionHelper.getAccessibleObjectDescription(field, false);
    assertTrue(description.contains("field"));
    assertTrue(description.contains("InnerClass"));
    assertTrue(description.contains("innerField"));
  }

  @Test
  public void getAccessibleObjectDescription_innerClassMethod() throws Exception {
    Method method = InnerClass.class.getDeclaredMethod("innerMethod");
    String description = ReflectionHelper.getAccessibleObjectDescription(method, false);
    assertTrue(description.contains("method"));
    assertTrue(description.contains("InnerClass"));
    assertTrue(description.contains("innerMethod"));
  }

  @Test
  public void getAccessibleObjectDescription_staticInnerClassConstructor() throws Exception {
    Constructor<?> constructor = StaticInnerClass.class.getDeclaredConstructor();
    String description = ReflectionHelper.getAccessibleObjectDescription(constructor, false);
    assertTrue(description.contains("constructor"));
    assertTrue(description.contains("StaticInnerClass"));
  }

  // ==========================================================================
  // Tests for edge cases
  // ==========================================================================

  @Test
  public void getAccessibleObjectDescription_fieldFromJavaLang() throws Exception {
    Field field = String.class.getDeclaredField("value");
    String description = ReflectionHelper.getAccessibleObjectDescription(field, false);
    assertEquals("field 'java.lang.String#value'", description);
  }

  @Test
  public void getAccessibleObjectDescription_methodFromJavaLang() throws Exception {
    Method method = String.class.getDeclaredMethod("length");
    String description = ReflectionHelper.getAccessibleObjectDescription(method, false);
    assertEquals("method 'java.lang.String#length()'", description);
  }

  @Test
  public void getAccessibleObjectDescription_constructorFromJavaLang() throws Exception {
    Constructor<?> constructor = String.class.getDeclaredConstructor();
    String description = ReflectionHelper.getAccessibleObjectDescription(constructor, false);
    assertEquals("constructor 'java.lang.String()'", description);
  }

  @Test
  public void getAccessibleObjectDescription_constructorWithObjectParam() throws Exception {
    Constructor<?> constructor = TestClass.class.getDeclaredConstructor(Object.class);
    String description = ReflectionHelper.getAccessibleObjectDescription(constructor, false);
    assertTrue(description.contains("Object"));
  }

  @Test
  public void getAccessibleObjectDescription_methodWithObjectReturnType() throws Exception {
    // Return type is not included in the description
    Method method = TestClass.class.getDeclaredMethod("getObject");
    String description = ReflectionHelper.getAccessibleObjectDescription(method, false);
    assertTrue(description.contains("method"));
    assertTrue(description.contains("getObject"));
    assertTrue(description.contains("()"));
  }

  // ==========================================================================
  // Helper classes for testing
  // ==========================================================================

  public static class TestClass {
    public String stringField;
    private int privateIntField;
    static String staticField;

    public TestClass() {}

    public TestClass(String param) {}

    public TestClass(String param1, int param2) {}

    public TestClass(String[] arrayParam) {}

    public TestClass(Object objParam) {}

    public void noParamsMethod() {}

    public void oneParamMethod(String param) {}

    public void multiParamMethod(String s, int i, boolean b) {}

    public void arrayParamMethod(String[] arr) {}

    public void primitiveArrayParamMethod(int[] arr) {}

    public static void staticMethod() {}

    private void privateMethod() {}

    public Object getObject() {
      return null;
    }
  }

  public static class PrivateConstructorClass {
    private PrivateConstructorClass() {}
  }

  public class InnerClass {
    String innerField;

    void innerMethod() {}
  }

  public static class StaticInnerClass {
    private StaticInnerClass() {}
  }
}
