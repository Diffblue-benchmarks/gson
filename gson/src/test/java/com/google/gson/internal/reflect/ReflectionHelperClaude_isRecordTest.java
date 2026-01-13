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
 * Tests for {@link ReflectionHelper#isRecord(Class)}.
 */
public class ReflectionHelperClaude_isRecordTest {

  // ==========================================================================
  // Tests for record classes (should return true)
  // ==========================================================================

  @Test
  public void isRecord_simpleRecord_returnsTrue() {
    assertTrue(ReflectionHelper.isRecord(SimpleRecord.class));
  }

  @Test
  public void isRecord_recordWithMultipleComponents_returnsTrue() {
    assertTrue(ReflectionHelper.isRecord(MultiComponentRecord.class));
  }

  @Test
  public void isRecord_recordWithNoComponents_returnsTrue() {
    assertTrue(ReflectionHelper.isRecord(EmptyRecord.class));
  }

  @Test
  public void isRecord_nestedRecord_returnsTrue() {
    assertTrue(ReflectionHelper.isRecord(OuterClass.NestedRecord.class));
  }

  @Test
  public void isRecord_recordWithArrayComponent_returnsTrue() {
    assertTrue(ReflectionHelper.isRecord(RecordWithArray.class));
  }

  @Test
  public void isRecord_recordWithGenericComponent_returnsTrue() {
    assertTrue(ReflectionHelper.isRecord(GenericRecord.class));
  }

  // ==========================================================================
  // Tests for non-record classes (should return false)
  // ==========================================================================

  @Test
  public void isRecord_regularClass_returnsFalse() {
    assertFalse(ReflectionHelper.isRecord(RegularClass.class));
  }

  @Test
  public void isRecord_abstractClass_returnsFalse() {
    assertFalse(ReflectionHelper.isRecord(AbstractClass.class));
  }

  @Test
  public void isRecord_interface_returnsFalse() {
    assertFalse(ReflectionHelper.isRecord(TestInterface.class));
  }

  @Test
  public void isRecord_enum_returnsFalse() {
    assertFalse(ReflectionHelper.isRecord(TestEnum.class));
  }

  @Test
  public void isRecord_annotation_returnsFalse() {
    assertFalse(ReflectionHelper.isRecord(TestAnnotation.class));
  }

  @Test
  public void isRecord_innerClass_returnsFalse() {
    assertFalse(ReflectionHelper.isRecord(OuterClass.InnerClass.class));
  }

  @Test
  public void isRecord_staticNestedClass_returnsFalse() {
    assertFalse(ReflectionHelper.isRecord(OuterClass.StaticNestedClass.class));
  }

  // ==========================================================================
  // Tests for primitive types and arrays (should return false)
  // ==========================================================================

  @Test
  public void isRecord_primitiveInt_returnsFalse() {
    assertFalse(ReflectionHelper.isRecord(int.class));
  }

  @Test
  public void isRecord_primitiveBoolean_returnsFalse() {
    assertFalse(ReflectionHelper.isRecord(boolean.class));
  }

  @Test
  public void isRecord_primitiveVoid_returnsFalse() {
    assertFalse(ReflectionHelper.isRecord(void.class));
  }

  @Test
  public void isRecord_primitiveArray_returnsFalse() {
    assertFalse(ReflectionHelper.isRecord(int[].class));
  }

  @Test
  public void isRecord_objectArray_returnsFalse() {
    assertFalse(ReflectionHelper.isRecord(String[].class));
  }

  @Test
  public void isRecord_recordArray_returnsFalse() {
    // Arrays of records are not themselves records
    assertFalse(ReflectionHelper.isRecord(SimpleRecord[].class));
  }

  // ==========================================================================
  // Tests for Java standard library classes (should return false)
  // ==========================================================================

  @Test
  public void isRecord_javaLangString_returnsFalse() {
    assertFalse(ReflectionHelper.isRecord(String.class));
  }

  @Test
  public void isRecord_javaLangObject_returnsFalse() {
    assertFalse(ReflectionHelper.isRecord(Object.class));
  }

  @Test
  public void isRecord_javaLangInteger_returnsFalse() {
    assertFalse(ReflectionHelper.isRecord(Integer.class));
  }

  @Test
  public void isRecord_javaLangRecord_returnsFalse() {
    // The Record class itself is not a record
    assertFalse(ReflectionHelper.isRecord(Record.class));
  }

  // ==========================================================================
  // Tests for anonymous and local classes (should return false)
  // ==========================================================================

  @Test
  public void isRecord_anonymousClass_returnsFalse() {
    Object anonymous =
        new Runnable() {
          @Override
          public void run() {}
        };
    assertFalse(ReflectionHelper.isRecord(anonymous.getClass()));
  }

  @Test
  public void isRecord_localClass_returnsFalse() {
    class LocalClass {}
    assertFalse(ReflectionHelper.isRecord(LocalClass.class));
  }

  // ==========================================================================
  // Record definitions for testing
  // ==========================================================================

  record SimpleRecord(String name) {}

  record MultiComponentRecord(String name, int age, boolean active) {}

  record EmptyRecord() {}

  record RecordWithArray(String[] items) {}

  record GenericRecord<T>(T value) {}

  // ==========================================================================
  // Non-record class definitions for testing
  // ==========================================================================

  static class RegularClass {
    @SuppressWarnings("unused")
    private String name;
  }

  abstract static class AbstractClass {
    abstract void doSomething();
  }

  interface TestInterface {
    void doSomething();
  }

  enum TestEnum {
    VALUE1,
    VALUE2
  }

  @interface TestAnnotation {}

  static class OuterClass {
    record NestedRecord(String value) {}

    class InnerClass {}

    static class StaticNestedClass {}
  }
}
