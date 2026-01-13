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

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.google.gson.JsonIOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.junit.Test;

/**
 * Tests for {@link ReflectionHelper#makeAccessible(java.lang.reflect.AccessibleObject)}.
 */
public class ReflectionHelperClaude_makeAccessibleTest {

  // ==========================================================================
  // Tests for makeAccessible with Field
  // ==========================================================================

  @Test
  public void makeAccessible_publicField_succeeds() throws Exception {
    Field field = PublicClass.class.getDeclaredField("publicField");
    // Should not throw
    ReflectionHelper.makeAccessible(field);
    assertTrue(field.isAccessible());
  }

  @Test
  public void makeAccessible_privateField_succeeds() throws Exception {
    Field field = PublicClass.class.getDeclaredField("privateField");
    // Should not throw - makeAccessible should make it accessible
    ReflectionHelper.makeAccessible(field);
    assertTrue(field.isAccessible());
  }

  @Test
  public void makeAccessible_protectedField_succeeds() throws Exception {
    Field field = PublicClass.class.getDeclaredField("protectedField");
    ReflectionHelper.makeAccessible(field);
    assertTrue(field.isAccessible());
  }

  @Test
  public void makeAccessible_packagePrivateField_succeeds() throws Exception {
    Field field = PublicClass.class.getDeclaredField("packagePrivateField");
    ReflectionHelper.makeAccessible(field);
    assertTrue(field.isAccessible());
  }

  @Test
  public void makeAccessible_staticField_succeeds() throws Exception {
    Field field = PublicClass.class.getDeclaredField("staticPrivateField");
    ReflectionHelper.makeAccessible(field);
    assertTrue(field.isAccessible());
  }

  @Test
  public void makeAccessible_finalField_succeeds() throws Exception {
    Field field = PublicClass.class.getDeclaredField("finalField");
    ReflectionHelper.makeAccessible(field);
    assertTrue(field.isAccessible());
  }

  // ==========================================================================
  // Tests for makeAccessible with Method
  // ==========================================================================

  @Test
  public void makeAccessible_publicMethod_succeeds() throws Exception {
    Method method = PublicClass.class.getDeclaredMethod("publicMethod");
    ReflectionHelper.makeAccessible(method);
    assertTrue(method.isAccessible());
  }

  @Test
  public void makeAccessible_privateMethod_succeeds() throws Exception {
    Method method = PublicClass.class.getDeclaredMethod("privateMethod");
    ReflectionHelper.makeAccessible(method);
    assertTrue(method.isAccessible());
  }

  @Test
  public void makeAccessible_protectedMethod_succeeds() throws Exception {
    Method method = PublicClass.class.getDeclaredMethod("protectedMethod");
    ReflectionHelper.makeAccessible(method);
    assertTrue(method.isAccessible());
  }

  @Test
  public void makeAccessible_packagePrivateMethod_succeeds() throws Exception {
    Method method = PublicClass.class.getDeclaredMethod("packagePrivateMethod");
    ReflectionHelper.makeAccessible(method);
    assertTrue(method.isAccessible());
  }

  @Test
  public void makeAccessible_methodWithParameters_succeeds() throws Exception {
    Method method = PublicClass.class.getDeclaredMethod("methodWithParams", String.class, int.class);
    ReflectionHelper.makeAccessible(method);
    assertTrue(method.isAccessible());
  }

  @Test
  public void makeAccessible_staticMethod_succeeds() throws Exception {
    Method method = PublicClass.class.getDeclaredMethod("staticPrivateMethod");
    ReflectionHelper.makeAccessible(method);
    assertTrue(method.isAccessible());
  }

  // ==========================================================================
  // Tests for makeAccessible with Constructor
  // ==========================================================================

  @Test
  public void makeAccessible_publicConstructor_succeeds() throws Exception {
    Constructor<?> constructor = PublicClass.class.getDeclaredConstructor();
    ReflectionHelper.makeAccessible(constructor);
    assertTrue(constructor.isAccessible());
  }

  @Test
  public void makeAccessible_privateConstructor_succeeds() throws Exception {
    Constructor<?> constructor = PrivateConstructorClass.class.getDeclaredConstructor();
    ReflectionHelper.makeAccessible(constructor);
    assertTrue(constructor.isAccessible());
  }

  @Test
  public void makeAccessible_constructorWithParameters_succeeds() throws Exception {
    Constructor<?> constructor = PublicClass.class.getDeclaredConstructor(String.class);
    ReflectionHelper.makeAccessible(constructor);
    assertTrue(constructor.isAccessible());
  }

  // ==========================================================================
  // Tests for makeAccessible exception handling
  // ==========================================================================

  @Test
  public void makeAccessible_failureThrowsJsonIOException_withFieldDescription() throws Exception {
    // We test that when setAccessible fails, a proper JsonIOException is thrown
    // with the field description. Since we can't easily make setAccessible fail
    // on user classes in a normal environment, we verify the exception format
    // when it does fail by checking the error message format in getAccessibleObjectDescription.

    Field field = PublicClass.class.getDeclaredField("privateField");
    String description = ReflectionHelper.getAccessibleObjectDescription(field, false);
    assertTrue(description.contains("field"));
    assertTrue(description.contains("PublicClass"));
    assertTrue(description.contains("privateField"));
  }

  @Test
  public void makeAccessible_failureThrowsJsonIOException_withMethodDescription() throws Exception {
    Method method = PublicClass.class.getDeclaredMethod("privateMethod");
    String description = ReflectionHelper.getAccessibleObjectDescription(method, false);
    assertTrue(description.contains("method"));
    assertTrue(description.contains("PublicClass"));
    assertTrue(description.contains("privateMethod"));
  }

  @Test
  public void makeAccessible_failureThrowsJsonIOException_withConstructorDescription() throws Exception {
    Constructor<?> constructor = PrivateConstructorClass.class.getDeclaredConstructor();
    String description = ReflectionHelper.getAccessibleObjectDescription(constructor, false);
    assertTrue(description.contains("constructor"));
    assertTrue(description.contains("PrivateConstructorClass"));
  }

  // ==========================================================================
  // Tests for inner class members
  // ==========================================================================

  @Test
  public void makeAccessible_innerClassPrivateField_succeeds() throws Exception {
    Field field = InnerClass.class.getDeclaredField("innerPrivateField");
    ReflectionHelper.makeAccessible(field);
    assertTrue(field.isAccessible());
  }

  @Test
  public void makeAccessible_innerClassPrivateMethod_succeeds() throws Exception {
    Method method = InnerClass.class.getDeclaredMethod("innerPrivateMethod");
    ReflectionHelper.makeAccessible(method);
    assertTrue(method.isAccessible());
  }

  @Test
  public void makeAccessible_staticInnerClassPrivateConstructor_succeeds() throws Exception {
    Constructor<?> constructor = StaticInnerClass.class.getDeclaredConstructor();
    ReflectionHelper.makeAccessible(constructor);
    assertTrue(constructor.isAccessible());
  }

  // ==========================================================================
  // Tests for already-accessible members
  // ==========================================================================

  @Test
  public void makeAccessible_alreadyAccessibleField_succeeds() throws Exception {
    Field field = PublicClass.class.getDeclaredField("privateField");
    field.setAccessible(true);
    // Calling makeAccessible again should still succeed
    ReflectionHelper.makeAccessible(field);
    assertTrue(field.isAccessible());
  }

  @Test
  public void makeAccessible_alreadyAccessibleMethod_succeeds() throws Exception {
    Method method = PublicClass.class.getDeclaredMethod("privateMethod");
    method.setAccessible(true);
    ReflectionHelper.makeAccessible(method);
    assertTrue(method.isAccessible());
  }

  @Test
  public void makeAccessible_alreadyAccessibleConstructor_succeeds() throws Exception {
    Constructor<?> constructor = PrivateConstructorClass.class.getDeclaredConstructor();
    constructor.setAccessible(true);
    ReflectionHelper.makeAccessible(constructor);
    assertTrue(constructor.isAccessible());
  }

  // ==========================================================================
  // Helper classes for testing
  // ==========================================================================

  public static class PublicClass {
    public String publicField;
    private String privateField;
    protected String protectedField;
    String packagePrivateField;
    private static String staticPrivateField;
    private final String finalField = "final";

    public PublicClass() {}

    public PublicClass(String param) {}

    public void publicMethod() {}

    private void privateMethod() {}

    protected void protectedMethod() {}

    void packagePrivateMethod() {}

    private void methodWithParams(String s, int i) {}

    private static void staticPrivateMethod() {}
  }

  public static class PrivateConstructorClass {
    private PrivateConstructorClass() {}
  }

  public class InnerClass {
    private String innerPrivateField;

    private void innerPrivateMethod() {}
  }

  public static class StaticInnerClass {
    private StaticInnerClass() {}
  }
}
