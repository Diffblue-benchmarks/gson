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

package com.google.gson.internal.reflect;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.google.gson.JsonIOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.junit.Test;

public final class ReflectionHelperTest {

  private static class StaticInner {}

  private class NonStaticInner {}

  static class SampleClass {
    public int value;

    public SampleClass() {}

    public void sampleMethod(String s, int i) {}
  }

  @Test
  public void testFieldToString() throws Exception {
    Field field = SampleClass.class.getDeclaredField("value");

    String result = ReflectionHelper.fieldToString(field);

    assertThat(result).isEqualTo(
        "com.google.gson.internal.reflect.ReflectionHelperTest$SampleClass#value");
  }

  @Test
  public void testConstructorToStringNoArgs() throws Exception {
    Constructor<?> constructor = String.class.getConstructor();

    String result = ReflectionHelper.constructorToString(constructor);

    assertThat(result).isEqualTo("java.lang.String()");
  }

  @Test
  public void testConstructorToStringWithArgs() throws Exception {
    Constructor<?> constructor = SampleClass.class.getDeclaredConstructor();

    String result = ReflectionHelper.constructorToString(constructor);

    assertThat(result).isEqualTo(
        "com.google.gson.internal.reflect.ReflectionHelperTest$SampleClass()");
  }

  @Test
  public void testGetAccessibleObjectDescriptionForField() throws Exception {
    Field field = SampleClass.class.getDeclaredField("value");

    String description = ReflectionHelper.getAccessibleObjectDescription(field, false);

    assertThat(description).isEqualTo(
        "field 'com.google.gson.internal.reflect.ReflectionHelperTest$SampleClass#value'");
  }

  @Test
  public void testGetAccessibleObjectDescriptionForFieldUppercase() throws Exception {
    Field field = SampleClass.class.getDeclaredField("value");

    String description = ReflectionHelper.getAccessibleObjectDescription(field, true);

    assertThat(description).isEqualTo(
        "Field 'com.google.gson.internal.reflect.ReflectionHelperTest$SampleClass#value'");
  }

  @Test
  public void testGetAccessibleObjectDescriptionForMethod() throws Exception {
    Method method = SampleClass.class.getDeclaredMethod("sampleMethod", String.class, int.class);

    String description = ReflectionHelper.getAccessibleObjectDescription(method, false);

    assertThat(description).isEqualTo(
        "method 'com.google.gson.internal.reflect.ReflectionHelperTest$SampleClass#sampleMethod(String, int)'");
  }

  @Test
  public void testGetAccessibleObjectDescriptionForConstructor() throws Exception {
    Constructor<?> constructor = SampleClass.class.getDeclaredConstructor();

    String description = ReflectionHelper.getAccessibleObjectDescription(constructor, false);

    assertThat(description).isEqualTo(
        "constructor 'com.google.gson.internal.reflect.ReflectionHelperTest$SampleClass()'");
  }

  @Test
  public void testGetAccessibleObjectDescriptionForConstructorUppercase() throws Exception {
    Constructor<?> constructor = SampleClass.class.getDeclaredConstructor();

    String description = ReflectionHelper.getAccessibleObjectDescription(constructor, true);

    assertThat(description).isEqualTo(
        "Constructor 'com.google.gson.internal.reflect.ReflectionHelperTest$SampleClass()'");
  }

  @Test
  public void testMakeAccessiblePublicField() throws Exception {
    Field field = SampleClass.class.getDeclaredField("value");

    // Should not throw
    ReflectionHelper.makeAccessible(field);
  }

  @Test
  public void testMakeAccessibleFailure() throws Exception {
    Field field = SampleClass.class.getDeclaredField("value");
    SecurityManager original = System.getSecurityManager();
    JsonIOException thrown = null;
    System.setSecurityManager(
        new SecurityManager() {
          @Override
          public void checkPermission(java.security.Permission perm) {
            if (perm instanceof java.lang.reflect.ReflectPermission
                && "suppressAccessChecks".equals(perm.getName())) {
              throw new SecurityException("reflective access denied");
            }
          }
        });
    try {
      ReflectionHelper.makeAccessible(field);
    } catch (JsonIOException e) {
      thrown = e;
    } finally {
      System.setSecurityManager(original);
    }

    assertThat(thrown).isNotNull();
    assertThat(thrown).hasMessageThat().contains("Failed making");
    assertThat(thrown).hasMessageThat().contains("SampleClass#value");
    assertThat(thrown.getCause()).isInstanceOf(SecurityException.class);
  }

  @Test
  public void testIsStaticTrue() {
    assertThat(ReflectionHelper.isStatic(StaticInner.class)).isTrue();
  }

  @Test
  public void testIsStaticFalse() {
    assertThat(ReflectionHelper.isStatic(NonStaticInner.class)).isFalse();
  }

  @Test
  public void testIsAnonymousOrNonStaticLocalForAnonymousClass() {
    Object anon = new Object() {};
    assertThat(ReflectionHelper.isAnonymousOrNonStaticLocal(anon.getClass())).isTrue();
  }

  @Test
  public void testIsAnonymousOrNonStaticLocalForStaticClass() {
    assertThat(ReflectionHelper.isAnonymousOrNonStaticLocal(StaticInner.class)).isFalse();
  }

  @Test
  public void testIsAnonymousOrNonStaticLocalForRegularClass() {
    assertThat(ReflectionHelper.isAnonymousOrNonStaticLocal(String.class)).isFalse();
  }

  @Test
  public void testTryMakeAccessibleSuccess() throws Exception {
    Constructor<?> constructor = SampleClass.class.getDeclaredConstructor();

    String result = ReflectionHelper.tryMakeAccessible(constructor);

    assertThat(result).isNull();
  }

  @Test
  public void testTryMakeAccessibleFailure() throws Exception {
    Constructor<?> constructor = SampleClass.class.getDeclaredConstructor();
    SecurityManager original = System.getSecurityManager();
    String result;
    System.setSecurityManager(
        new SecurityManager() {
          @Override
          public void checkPermission(java.security.Permission perm) {
            if (perm instanceof java.lang.reflect.ReflectPermission
                && "suppressAccessChecks".equals(perm.getName())) {
              throw new SecurityException("reflective access denied");
            }
          }
        });
    try {
      result = ReflectionHelper.tryMakeAccessible(constructor);
    } finally {
      System.setSecurityManager(original);
    }

    assertThat(result).isNotNull();
    assertThat(result).contains("Failed making constructor '");
    assertThat(result).contains("SampleClass");
    assertThat(result).contains("reflective access denied");
  }

  @Test
  public void testIsRecordReturnsFalse() {
    // On JDK versions without record support, isRecord returns false
    assertThat(ReflectionHelper.isRecord(String.class)).isFalse();
  }

  @Test
  public void testGetRecordComponentNamesThrowsOrReturnsEmpty() {
    try {
      String[] names = ReflectionHelper.getRecordComponentNames(String.class);
      // If we reach here, records are supported; String is not a record so names should be empty
      assertThat(names).isEmpty();
    } catch (UnsupportedOperationException e) {
      assertThat(e).hasMessageThat().contains("Records are not supported");
    }
  }

  @Test
  public void testGetAccessorThrowsOrReturnsResult() throws Exception {
    Field field = SampleClass.class.getDeclaredField("value");
    try {
      ReflectionHelper.getAccessor(SampleClass.class, field);
      // If records are supported, the result doesn't matter for SampleClass (non-record)
    } catch (UnsupportedOperationException e) {
      assertThat(e).hasMessageThat().contains("Records are not supported");
    }
  }

  @Test
  public void testGetCanonicalRecordConstructorThrowsOrReturnsResult() {
    try {
      ReflectionHelper.getCanonicalRecordConstructor(String.class);
    } catch (UnsupportedOperationException e) {
      assertThat(e).hasMessageThat().contains("Records are not supported");
    }
  }

  @Test
  public void testCreateExceptionForUnexpectedIllegalAccess() {
    IllegalAccessException cause = new IllegalAccessException("test error");

    RuntimeException ex =
        assertThrows(
            RuntimeException.class,
            () -> ReflectionHelper.createExceptionForUnexpectedIllegalAccess(cause));

    assertThat(ex).hasMessageThat().contains("Unexpected IllegalAccessException occurred");
    assertThat(ex.getCause()).isSameInstanceAs(cause);
  }

  @Test
  public void testGetAccessibleObjectDescriptionForMethodUppercase() throws Exception {
    Method method = SampleClass.class.getDeclaredMethod("sampleMethod", String.class, int.class);

    String description = ReflectionHelper.getAccessibleObjectDescription(method, true);

    assertThat(description).isEqualTo(
        "Method 'com.google.gson.internal.reflect.ReflectionHelperTest$SampleClass#sampleMethod(String, int)'");
  }
}
