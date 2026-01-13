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

import java.lang.reflect.Field;
import org.junit.Test;

/**
 * Tests for {@link ReflectionHelper#fieldToString(java.lang.reflect.Field)}.
 */
public class ReflectionHelperClaude_fieldToStringTest {

  // ==========================================================================
  // Tests for basic field types
  // ==========================================================================

  @Test
  public void fieldToString_publicField() throws Exception {
    Field field = TestClass.class.getDeclaredField("publicField");
    String result = ReflectionHelper.fieldToString(field);
    assertEquals(
        "com.google.gson.internal.reflect.ReflectionHelperClaude_fieldToStringTest$TestClass#publicField",
        result);
  }

  @Test
  public void fieldToString_privateField() throws Exception {
    Field field = TestClass.class.getDeclaredField("privateField");
    String result = ReflectionHelper.fieldToString(field);
    assertEquals(
        "com.google.gson.internal.reflect.ReflectionHelperClaude_fieldToStringTest$TestClass#privateField",
        result);
  }

  @Test
  public void fieldToString_protectedField() throws Exception {
    Field field = TestClass.class.getDeclaredField("protectedField");
    String result = ReflectionHelper.fieldToString(field);
    assertEquals(
        "com.google.gson.internal.reflect.ReflectionHelperClaude_fieldToStringTest$TestClass#protectedField",
        result);
  }

  @Test
  public void fieldToString_packagePrivateField() throws Exception {
    Field field = TestClass.class.getDeclaredField("packagePrivateField");
    String result = ReflectionHelper.fieldToString(field);
    assertEquals(
        "com.google.gson.internal.reflect.ReflectionHelperClaude_fieldToStringTest$TestClass#packagePrivateField",
        result);
  }

  // ==========================================================================
  // Tests for static and final fields
  // ==========================================================================

  @Test
  public void fieldToString_staticField() throws Exception {
    Field field = TestClass.class.getDeclaredField("staticField");
    String result = ReflectionHelper.fieldToString(field);
    assertEquals(
        "com.google.gson.internal.reflect.ReflectionHelperClaude_fieldToStringTest$TestClass#staticField",
        result);
  }

  @Test
  public void fieldToString_finalField() throws Exception {
    Field field = TestClass.class.getDeclaredField("finalField");
    String result = ReflectionHelper.fieldToString(field);
    assertEquals(
        "com.google.gson.internal.reflect.ReflectionHelperClaude_fieldToStringTest$TestClass#finalField",
        result);
  }

  @Test
  public void fieldToString_staticFinalField() throws Exception {
    Field field = TestClass.class.getDeclaredField("staticFinalField");
    String result = ReflectionHelper.fieldToString(field);
    assertEquals(
        "com.google.gson.internal.reflect.ReflectionHelperClaude_fieldToStringTest$TestClass#staticFinalField",
        result);
  }

  // ==========================================================================
  // Tests for different field types (type is not included in output)
  // ==========================================================================

  @Test
  public void fieldToString_intField() throws Exception {
    Field field = TestClass.class.getDeclaredField("intField");
    String result = ReflectionHelper.fieldToString(field);
    assertEquals(
        "com.google.gson.internal.reflect.ReflectionHelperClaude_fieldToStringTest$TestClass#intField",
        result);
  }

  @Test
  public void fieldToString_doubleField() throws Exception {
    Field field = TestClass.class.getDeclaredField("doubleField");
    String result = ReflectionHelper.fieldToString(field);
    assertEquals(
        "com.google.gson.internal.reflect.ReflectionHelperClaude_fieldToStringTest$TestClass#doubleField",
        result);
  }

  @Test
  public void fieldToString_objectField() throws Exception {
    Field field = TestClass.class.getDeclaredField("objectField");
    String result = ReflectionHelper.fieldToString(field);
    assertEquals(
        "com.google.gson.internal.reflect.ReflectionHelperClaude_fieldToStringTest$TestClass#objectField",
        result);
  }

  @Test
  public void fieldToString_arrayField() throws Exception {
    Field field = TestClass.class.getDeclaredField("arrayField");
    String result = ReflectionHelper.fieldToString(field);
    assertEquals(
        "com.google.gson.internal.reflect.ReflectionHelperClaude_fieldToStringTest$TestClass#arrayField",
        result);
  }

  // ==========================================================================
  // Tests for fields from java.lang classes
  // ==========================================================================

  @Test
  public void fieldToString_javaLangStringField() throws Exception {
    Field field = String.class.getDeclaredField("value");
    String result = ReflectionHelper.fieldToString(field);
    assertEquals("java.lang.String#value", result);
  }

  @Test
  public void fieldToString_javaLangIntegerField() throws Exception {
    Field field = Integer.class.getDeclaredField("value");
    String result = ReflectionHelper.fieldToString(field);
    assertEquals("java.lang.Integer#value", result);
  }

  // ==========================================================================
  // Tests for inner class fields
  // ==========================================================================

  @Test
  public void fieldToString_innerClassField() throws Exception {
    Field field = InnerClass.class.getDeclaredField("innerField");
    String result = ReflectionHelper.fieldToString(field);
    assertEquals(
        "com.google.gson.internal.reflect.ReflectionHelperClaude_fieldToStringTest$InnerClass#innerField",
        result);
  }

  @Test
  public void fieldToString_staticInnerClassField() throws Exception {
    Field field = StaticInnerClass.class.getDeclaredField("staticInnerField");
    String result = ReflectionHelper.fieldToString(field);
    assertEquals(
        "com.google.gson.internal.reflect.ReflectionHelperClaude_fieldToStringTest$StaticInnerClass#staticInnerField",
        result);
  }

  @Test
  public void fieldToString_deeplyNestedClassField() throws Exception {
    Field field = StaticInnerClass.DeeplyNested.class.getDeclaredField("deepField");
    String result = ReflectionHelper.fieldToString(field);
    assertEquals(
        "com.google.gson.internal.reflect.ReflectionHelperClaude_fieldToStringTest$StaticInnerClass$DeeplyNested#deepField",
        result);
  }

  // ==========================================================================
  // Tests to verify format (declaringClass#fieldName)
  // ==========================================================================

  @Test
  public void fieldToString_containsHashSeparator() throws Exception {
    Field field = TestClass.class.getDeclaredField("publicField");
    String result = ReflectionHelper.fieldToString(field);
    assertTrue(result.contains("#"));
  }

  @Test
  public void fieldToString_startsWithDeclaringClassName() throws Exception {
    Field field = TestClass.class.getDeclaredField("publicField");
    String result = ReflectionHelper.fieldToString(field);
    assertTrue(result.startsWith("com.google.gson.internal.reflect.ReflectionHelperClaude_fieldToStringTest$TestClass"));
  }

  @Test
  public void fieldToString_endsWithFieldName() throws Exception {
    Field field = TestClass.class.getDeclaredField("publicField");
    String result = ReflectionHelper.fieldToString(field);
    assertTrue(result.endsWith("publicField"));
  }

  // ==========================================================================
  // Tests for inherited fields
  // ==========================================================================

  @Test
  public void fieldToString_inheritedField_reportsDeclaringClass() throws Exception {
    // getDeclaredField only returns fields declared in the class itself,
    // so we need to use the parent class to get the inherited field
    Field field = ParentClass.class.getDeclaredField("parentField");
    String result = ReflectionHelper.fieldToString(field);
    assertEquals(
        "com.google.gson.internal.reflect.ReflectionHelperClaude_fieldToStringTest$ParentClass#parentField",
        result);
  }

  @Test
  public void fieldToString_childClassOwnField() throws Exception {
    Field field = ChildClass.class.getDeclaredField("childField");
    String result = ReflectionHelper.fieldToString(field);
    assertEquals(
        "com.google.gson.internal.reflect.ReflectionHelperClaude_fieldToStringTest$ChildClass#childField",
        result);
  }

  // ==========================================================================
  // Helper classes for testing
  // ==========================================================================

  public static class TestClass {
    public String publicField;
    private String privateField;
    protected String protectedField;
    String packagePrivateField;
    static String staticField;
    final String finalField = "final";
    static final String staticFinalField = "staticFinal";
    int intField;
    double doubleField;
    Object objectField;
    String[] arrayField;
  }

  public class InnerClass {
    String innerField;
  }

  public static class StaticInnerClass {
    String staticInnerField;

    public static class DeeplyNested {
      String deepField;
    }
  }

  public static class ParentClass {
    public String parentField;
  }

  public static class ChildClass extends ParentClass {
    public String childField;
  }
}
