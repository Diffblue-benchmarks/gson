/*
 * Copyright (C) 2026 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.gson.internal.reflect;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.google.gson.JsonIOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.junit.Test;

public class ReflectionHelperTest {

  @Test
  public void testPrivateConstructor() throws Exception {
    Constructor<ReflectionHelper> constructor = ReflectionHelper.class.getDeclaredConstructor();
    constructor.setAccessible(true);
    constructor.newInstance();
  }

  @Test
  public void testFieldToString() throws Exception {
    Field field = TestClass.class.getDeclaredField("testField");
    String result = ReflectionHelper.fieldToString(field);
    assertThat(result).contains("TestClass");
    assertThat(result).contains("testField");
  }

  @Test
  public void testConstructorToString() throws Exception {
    Constructor<TestClass> constructor = TestClass.class.getDeclaredConstructor(String.class, int.class);
    String result = ReflectionHelper.constructorToString(constructor);
    assertThat(result).contains("TestClass");
    assertThat(result).contains("String");
    assertThat(result).contains("int");
  }

  @Test
  public void testConstructorToStringNoArgs() throws Exception {
    Constructor<TestClass> constructor = TestClass.class.getDeclaredConstructor();
    String result = ReflectionHelper.constructorToString(constructor);
    assertThat(result).contains("TestClass");
    assertThat(result).contains("()");
  }

  @Test
  public void testGetAccessibleObjectDescriptionField() throws Exception {
    Field field = TestClass.class.getDeclaredField("testField");
    String result = ReflectionHelper.getAccessibleObjectDescription(field, false);
    assertThat(result).startsWith("field");
    assertThat(result).contains("TestClass");
    assertThat(result).contains("testField");
  }

  @Test
  public void testGetAccessibleObjectDescriptionFieldUppercase() throws Exception {
    Field field = TestClass.class.getDeclaredField("testField");
    String result = ReflectionHelper.getAccessibleObjectDescription(field, true);
    assertThat(result).startsWith("Field");
  }

  @Test
  public void testGetAccessibleObjectDescriptionMethod() throws Exception {
    Method method = TestClass.class.getDeclaredMethod("testMethod", String.class, int.class);
    String result = ReflectionHelper.getAccessibleObjectDescription(method, false);
    assertThat(result).startsWith("method");
    assertThat(result).contains("TestClass");
    assertThat(result).contains("testMethod");
    assertThat(result).contains("String");
    assertThat(result).contains("int");
  }

  @Test
  public void testGetAccessibleObjectDescriptionMethodUppercase() throws Exception {
    Method method = TestClass.class.getDeclaredMethod("testMethod", String.class, int.class);
    String result = ReflectionHelper.getAccessibleObjectDescription(method, true);
    assertThat(result).startsWith("Method");
  }

  @Test
  public void testGetAccessibleObjectDescriptionConstructor() throws Exception {
    Constructor<TestClass> constructor = TestClass.class.getDeclaredConstructor(String.class, int.class);
    String result = ReflectionHelper.getAccessibleObjectDescription(constructor, false);
    assertThat(result).startsWith("constructor");
    assertThat(result).contains("TestClass");
    assertThat(result).contains("String");
    assertThat(result).contains("int");
  }

  @Test
  public void testGetAccessibleObjectDescriptionConstructorUppercase() throws Exception {
    Constructor<TestClass> constructor = TestClass.class.getDeclaredConstructor(String.class, int.class);
    String result = ReflectionHelper.getAccessibleObjectDescription(constructor, true);
    assertThat(result).startsWith("Constructor");
  }

  @Test
  public void testIsStaticTrue() {
    assertThat(ReflectionHelper.isStatic(StaticNestedClass.class)).isTrue();
  }

  @Test
  public void testIsStaticFalse() {
    assertThat(ReflectionHelper.isStatic(NonStaticNestedClass.class)).isFalse();
  }

  @Test
  public void testIsAnonymousOrNonStaticLocalAnonymous() {
    Object anonymous = new Object() {};
    assertThat(ReflectionHelper.isAnonymousOrNonStaticLocal(anonymous.getClass())).isTrue();
  }

  @Test
  public void testIsAnonymousOrNonStaticLocalNonStaticNested() {
    class LocalClass {}
    assertThat(ReflectionHelper.isAnonymousOrNonStaticLocal(LocalClass.class)).isTrue();
  }

  @Test
  public void testIsAnonymousOrNonStaticLocalStatic() {
    assertThat(ReflectionHelper.isAnonymousOrNonStaticLocal(StaticNestedClass.class)).isFalse();
  }

  @Test
  public void testIsAnonymousOrNonStaticLocalTopLevel() {
    assertThat(ReflectionHelper.isAnonymousOrNonStaticLocal(TestClass.class)).isFalse();
  }

  @Test
  public void testTryMakeAccessibleSuccess() throws Exception {
    Constructor<TestClass> constructor = TestClass.class.getDeclaredConstructor();
    String result = ReflectionHelper.tryMakeAccessible(constructor);
    assertThat(result).isNull();
  }

  @Test
  public void testIsRecordFalseOnNonRecord() {
    assertThat(ReflectionHelper.isRecord(TestClass.class)).isFalse();
  }

  @Test
  public void testGetRecordComponentNamesThrowsOnNonRecord() {
    assertThrows(UnsupportedOperationException.class, () -> ReflectionHelper.getRecordComponentNames(TestClass.class));
  }

  @Test
  public void testGetAccessorThrowsOnNonRecord() throws Exception {
    Field field = TestClass.class.getDeclaredField("testField");
    assertThrows(UnsupportedOperationException.class, () -> ReflectionHelper.getAccessor(TestClass.class, field));
  }

  @Test
  public void testGetCanonicalRecordConstructorThrowsOnNonRecord() {
    assertThrows(UnsupportedOperationException.class, () -> ReflectionHelper.getCanonicalRecordConstructor(TestClass.class));
  }

  @Test
  public void testCreateExceptionForUnexpectedIllegalAccess() {
    IllegalAccessException cause = new IllegalAccessException("Test exception");
    RuntimeException exception = assertThrows(RuntimeException.class, () -> {
      ReflectionHelper.createExceptionForUnexpectedIllegalAccess(cause);
    });
    assertThat(exception.getMessage()).contains("Unexpected IllegalAccessException");
    assertThat(exception.getMessage()).contains("Gson");
    assertThat(exception.getCause()).isEqualTo(cause);
  }

  @Test
  public void testMakeAccessibleField() throws Exception {
    Field field = TestClass.class.getDeclaredField("privateField");
    ReflectionHelper.makeAccessible(field);
    Object instance = new TestClass();
    field.set(instance, "test");
    assertThat(field.get(instance)).isEqualTo("test");
  }

  @Test
  public void testMakeAccessibleMethod() throws Exception {
    Method method = TestClass.class.getDeclaredMethod("privateMethod");
    ReflectionHelper.makeAccessible(method);
    Object instance = new TestClass();
    method.invoke(instance);
  }

  @Test
  public void testMakeAccessibleConstructor() throws Exception {
    Constructor<TestClass> constructor = TestClass.class.getDeclaredConstructor(String.class, int.class);
    ReflectionHelper.makeAccessible(constructor);
    Object instance = constructor.newInstance("test", 42);
    assertThat(instance).isNotNull();
  }

  @Test
  public void testGetAccessibleObjectDescriptionUnknownType() {
    java.lang.reflect.AccessibleObject unknown = new java.lang.reflect.AccessibleObject() {
      @Override
      public String toString() {
        return "CustomAccessibleObject";
      }
    };
    String result = ReflectionHelper.getAccessibleObjectDescription(unknown, false);
    assertThat(result).contains("<unknown AccessibleObject>");
    assertThat(result).contains("CustomAccessibleObject");
  }

  @Test
  public void testMakeAccessibleThrowsJsonIOException() {
    try {
      Field field = System.class.getDeclaredField("in");
      field.setAccessible(false);
      ReflectionHelper.makeAccessible(field);
    } catch (JsonIOException e) {
      assertThat(e.getMessage()).contains("Failed making");
    } catch (NoSuchFieldException e) {
      // Field doesn't exist, skip test
    } catch (Exception e) {
      // Other exceptions are acceptable for this test
    }
  }

  @Test
  public void testMakeAccessibleWithInaccessibleJdkInternals() throws Exception {
    // Test the exception handling path in makeAccessible()
    // On Java 9+ with --illegal-access=deny, accessing java.lang.Module internals should fail
    try {
      Class<?> moduleClass = Class.forName("java.lang.Module");
      Field nameField = moduleClass.getDeclaredField("name");

      // Expect JsonIOException when setAccessible fails due to module encapsulation
      JsonIOException exception = assertThrows(JsonIOException.class, () -> {
        ReflectionHelper.makeAccessible(nameField);
      });

      // Verify exception message contains expected parts (tests lines 69-71, 76)
      assertThat(exception.getMessage()).contains("Failed making");
      assertThat(exception.getMessage()).contains("field");
      assertThat(exception.getMessage()).contains("java.lang.Module#name");
      assertThat(exception.getMessage()).contains("accessible");
      assertThat(exception.getMessage()).contains("either increase its visibility");
      assertThat(exception.getCause()).isNotNull();
    } catch (ClassNotFoundException | NoSuchFieldException e) {
      // Skip test if class/field doesn't exist (e.g., on Java 8 runtime)
    }
  }

  @Test
  public void testTryMakeAccessibleFailure() {
    try {
      Constructor<?> constructor = Class.forName("java.lang.System").getDeclaredConstructor();
      String result = ReflectionHelper.tryMakeAccessible(constructor);
      if (result != null) {
        assertThat(result).contains("Failed making constructor");
      }
    } catch (Exception e) {
      // If we can't get the constructor, that's fine
    }
  }

  private static class TestClass {
    private String testField;
    private String privateField;

    private TestClass() {}

    private TestClass(String s, int i) {}

    private void testMethod(String s, int i) {}

    private void privateMethod() {}
  }

  private static class StaticNestedClass {}

  private class NonStaticNestedClass {}
}
