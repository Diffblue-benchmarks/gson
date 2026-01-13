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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Constructor;
import org.junit.Test;

/**
 * Tests for {@link ReflectionHelper#getCanonicalRecordConstructor(Class)}.
 */
public class ReflectionHelperClaude_getCanonicalRecordConstructorTest {

  // ==========================================================================
  // Tests for empty record
  // ==========================================================================

  @Test
  public void getCanonicalRecordConstructor_emptyRecord_returnsNoArgConstructor() {
    Constructor<EmptyRecord> constructor =
        ReflectionHelper.getCanonicalRecordConstructor(EmptyRecord.class);

    assertNotNull(constructor);
    assertEquals(0, constructor.getParameterCount());
  }

  @Test
  public void getCanonicalRecordConstructor_emptyRecord_canInstantiate() throws Exception {
    Constructor<EmptyRecord> constructor =
        ReflectionHelper.getCanonicalRecordConstructor(EmptyRecord.class);

    EmptyRecord instance = constructor.newInstance();
    assertNotNull(instance);
  }

  // ==========================================================================
  // Tests for single component records
  // ==========================================================================

  @Test
  public void getCanonicalRecordConstructor_singleStringComponent_returnsCorrectConstructor() {
    Constructor<SingleStringRecord> constructor =
        ReflectionHelper.getCanonicalRecordConstructor(SingleStringRecord.class);

    assertNotNull(constructor);
    assertEquals(1, constructor.getParameterCount());
    assertArrayEquals(new Class<?>[] {String.class}, constructor.getParameterTypes());
  }

  @Test
  public void getCanonicalRecordConstructor_singleIntComponent_returnsCorrectConstructor() {
    Constructor<SingleIntRecord> constructor =
        ReflectionHelper.getCanonicalRecordConstructor(SingleIntRecord.class);

    assertNotNull(constructor);
    assertEquals(1, constructor.getParameterCount());
    assertArrayEquals(new Class<?>[] {int.class}, constructor.getParameterTypes());
  }

  @Test
  public void getCanonicalRecordConstructor_singleComponent_canInstantiate() throws Exception {
    Constructor<SingleStringRecord> constructor =
        ReflectionHelper.getCanonicalRecordConstructor(SingleStringRecord.class);

    SingleStringRecord instance = constructor.newInstance("test");
    assertNotNull(instance);
    assertEquals("test", instance.name());
  }

  // ==========================================================================
  // Tests for multiple component records
  // ==========================================================================

  @Test
  public void getCanonicalRecordConstructor_twoComponents_returnsCorrectConstructor() {
    Constructor<TwoComponentRecord> constructor =
        ReflectionHelper.getCanonicalRecordConstructor(TwoComponentRecord.class);

    assertNotNull(constructor);
    assertEquals(2, constructor.getParameterCount());
    assertArrayEquals(
        new Class<?>[] {String.class, int.class}, constructor.getParameterTypes());
  }

  @Test
  public void getCanonicalRecordConstructor_threeComponents_returnsCorrectConstructor() {
    Constructor<ThreeComponentRecord> constructor =
        ReflectionHelper.getCanonicalRecordConstructor(ThreeComponentRecord.class);

    assertNotNull(constructor);
    assertEquals(3, constructor.getParameterCount());
    assertArrayEquals(
        new Class<?>[] {String.class, int.class, boolean.class}, constructor.getParameterTypes());
  }

  @Test
  public void getCanonicalRecordConstructor_multipleComponents_canInstantiate() throws Exception {
    Constructor<ThreeComponentRecord> constructor =
        ReflectionHelper.getCanonicalRecordConstructor(ThreeComponentRecord.class);

    ThreeComponentRecord instance = constructor.newInstance("John", 30, true);
    assertNotNull(instance);
    assertEquals("John", instance.name());
    assertEquals(30, instance.age());
    assertEquals(true, instance.active());
  }

  // ==========================================================================
  // Tests for records with different component types
  // ==========================================================================

  @Test
  public void getCanonicalRecordConstructor_allPrimitives_returnsCorrectConstructor() {
    Constructor<AllPrimitivesRecord> constructor =
        ReflectionHelper.getCanonicalRecordConstructor(AllPrimitivesRecord.class);

    assertNotNull(constructor);
    assertEquals(8, constructor.getParameterCount());
    assertArrayEquals(
        new Class<?>[] {
          byte.class, short.class, int.class, long.class,
          float.class, double.class, boolean.class, char.class
        },
        constructor.getParameterTypes());
  }

  @Test
  public void getCanonicalRecordConstructor_arrayComponent_returnsCorrectConstructor() {
    Constructor<ArrayRecord> constructor =
        ReflectionHelper.getCanonicalRecordConstructor(ArrayRecord.class);

    assertNotNull(constructor);
    assertEquals(1, constructor.getParameterCount());
    assertArrayEquals(new Class<?>[] {String[].class}, constructor.getParameterTypes());
  }

  @Test
  public void getCanonicalRecordConstructor_objectComponent_returnsCorrectConstructor() {
    Constructor<ObjectRecord> constructor =
        ReflectionHelper.getCanonicalRecordConstructor(ObjectRecord.class);

    assertNotNull(constructor);
    assertEquals(1, constructor.getParameterCount());
    assertArrayEquals(new Class<?>[] {Object.class}, constructor.getParameterTypes());
  }

  // ==========================================================================
  // Tests for nested records
  // ==========================================================================

  @Test
  public void getCanonicalRecordConstructor_nestedRecord_returnsCorrectConstructor() {
    Constructor<OuterClass.NestedRecord> constructor =
        ReflectionHelper.getCanonicalRecordConstructor(OuterClass.NestedRecord.class);

    assertNotNull(constructor);
    assertEquals(2, constructor.getParameterCount());
    assertArrayEquals(
        new Class<?>[] {int.class, String.class}, constructor.getParameterTypes());
  }

  @Test
  public void getCanonicalRecordConstructor_nestedRecord_canInstantiate() throws Exception {
    Constructor<OuterClass.NestedRecord> constructor =
        ReflectionHelper.getCanonicalRecordConstructor(OuterClass.NestedRecord.class);

    OuterClass.NestedRecord instance = constructor.newInstance(1, "desc");
    assertNotNull(instance);
    assertEquals(1, instance.id());
    assertEquals("desc", instance.description());
  }

  // ==========================================================================
  // Tests for generic records
  // ==========================================================================

  @Test
  public void getCanonicalRecordConstructor_genericRecord_returnsCorrectConstructor() {
    @SuppressWarnings("rawtypes")
    Constructor<GenericRecord> constructor =
        ReflectionHelper.getCanonicalRecordConstructor(GenericRecord.class);

    assertNotNull(constructor);
    assertEquals(1, constructor.getParameterCount());
    // Generic type erases to Object
    assertArrayEquals(new Class<?>[] {Object.class}, constructor.getParameterTypes());
  }

  @Test
  public void getCanonicalRecordConstructor_multiGenericRecord_returnsCorrectConstructor() {
    @SuppressWarnings("rawtypes")
    Constructor<MultiGenericRecord> constructor =
        ReflectionHelper.getCanonicalRecordConstructor(MultiGenericRecord.class);

    assertNotNull(constructor);
    assertEquals(2, constructor.getParameterCount());
    // Generic types erase to Object
    assertArrayEquals(new Class<?>[] {Object.class, Object.class}, constructor.getParameterTypes());
  }

  // ==========================================================================
  // Tests for parameter order preservation
  // ==========================================================================

  @Test
  public void getCanonicalRecordConstructor_preservesParameterOrder() {
    Constructor<OrderTestRecord> constructor =
        ReflectionHelper.getCanonicalRecordConstructor(OrderTestRecord.class);

    assertNotNull(constructor);
    assertEquals(4, constructor.getParameterCount());
    // Verify parameters are in declaration order
    assertArrayEquals(
        new Class<?>[] {String.class, int.class, double.class, boolean.class},
        constructor.getParameterTypes());
  }

  @Test
  public void getCanonicalRecordConstructor_preservesParameterOrder_canInstantiate()
      throws Exception {
    Constructor<OrderTestRecord> constructor =
        ReflectionHelper.getCanonicalRecordConstructor(OrderTestRecord.class);

    OrderTestRecord instance = constructor.newInstance("zebra", 42, 3.14, false);
    assertNotNull(instance);
    assertEquals("zebra", instance.first());
    assertEquals(42, instance.second());
    assertEquals(3.14, instance.third(), 0.001);
    assertEquals(false, instance.fourth());
  }

  // ==========================================================================
  // Tests for records with complex types
  // ==========================================================================

  @Test
  public void getCanonicalRecordConstructor_nestedRecordComponent_returnsCorrectConstructor() {
    Constructor<RecordWithNestedRecord> constructor =
        ReflectionHelper.getCanonicalRecordConstructor(RecordWithNestedRecord.class);

    assertNotNull(constructor);
    assertEquals(2, constructor.getParameterCount());
    assertArrayEquals(
        new Class<?>[] {String.class, SingleStringRecord.class}, constructor.getParameterTypes());
  }

  // ==========================================================================
  // Record definitions for testing
  // ==========================================================================

  record EmptyRecord() {}

  record SingleStringRecord(String name) {}

  record SingleIntRecord(int value) {}

  record TwoComponentRecord(String name, int age) {}

  record ThreeComponentRecord(String name, int age, boolean active) {}

  record AllPrimitivesRecord(
      byte b, short s, int i, long l, float f, double d, boolean bool, char c) {}

  record ArrayRecord(String[] items) {}

  record ObjectRecord(Object data) {}

  record GenericRecord<T>(T value) {}

  record MultiGenericRecord<K, V>(K key, V value) {}

  record OrderTestRecord(String first, int second, double third, boolean fourth) {}

  record RecordWithNestedRecord(String id, SingleStringRecord nested) {}

  static class OuterClass {
    record NestedRecord(int id, String description) {}
  }
}
