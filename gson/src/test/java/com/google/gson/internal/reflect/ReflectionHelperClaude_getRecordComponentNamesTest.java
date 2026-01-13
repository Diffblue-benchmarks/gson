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

import org.junit.Test;

/**
 * Tests for {@link ReflectionHelper#getRecordComponentNames(Class)}.
 */
public class ReflectionHelperClaude_getRecordComponentNamesTest {

  // ==========================================================================
  // Tests for records with single component
  // ==========================================================================

  @Test
  public void getRecordComponentNames_singleComponent_returnsCorrectName() {
    String[] names = ReflectionHelper.getRecordComponentNames(SingleComponentRecord.class);
    assertNotNull(names);
    assertEquals(1, names.length);
    assertEquals("name", names[0]);
  }

  @Test
  public void getRecordComponentNames_singleIntComponent_returnsCorrectName() {
    String[] names = ReflectionHelper.getRecordComponentNames(SingleIntRecord.class);
    assertNotNull(names);
    assertEquals(1, names.length);
    assertEquals("value", names[0]);
  }

  // ==========================================================================
  // Tests for records with multiple components
  // ==========================================================================

  @Test
  public void getRecordComponentNames_twoComponents_returnsCorrectNames() {
    String[] names = ReflectionHelper.getRecordComponentNames(TwoComponentRecord.class);
    assertNotNull(names);
    assertEquals(2, names.length);
    assertEquals("firstName", names[0]);
    assertEquals("lastName", names[1]);
  }

  @Test
  public void getRecordComponentNames_threeComponents_returnsCorrectNames() {
    String[] names = ReflectionHelper.getRecordComponentNames(ThreeComponentRecord.class);
    assertNotNull(names);
    assertEquals(3, names.length);
    assertEquals("name", names[0]);
    assertEquals("age", names[1]);
    assertEquals("active", names[2]);
  }

  @Test
  public void getRecordComponentNames_manyComponents_returnsCorrectNames() {
    String[] names = ReflectionHelper.getRecordComponentNames(ManyComponentRecord.class);
    assertNotNull(names);
    assertEquals(5, names.length);
    assertArrayEquals(new String[] {"a", "b", "c", "d", "e"}, names);
  }

  // ==========================================================================
  // Tests for empty record
  // ==========================================================================

  @Test
  public void getRecordComponentNames_emptyRecord_returnsEmptyArray() {
    String[] names = ReflectionHelper.getRecordComponentNames(EmptyRecord.class);
    assertNotNull(names);
    assertEquals(0, names.length);
  }

  // ==========================================================================
  // Tests for records with different component types
  // ==========================================================================

  @Test
  public void getRecordComponentNames_primitiveComponents_returnsCorrectNames() {
    String[] names = ReflectionHelper.getRecordComponentNames(PrimitiveComponentsRecord.class);
    assertNotNull(names);
    assertEquals(4, names.length);
    assertArrayEquals(new String[] {"intVal", "longVal", "doubleVal", "boolVal"}, names);
  }

  @Test
  public void getRecordComponentNames_arrayComponent_returnsCorrectName() {
    String[] names = ReflectionHelper.getRecordComponentNames(ArrayComponentRecord.class);
    assertNotNull(names);
    assertEquals(1, names.length);
    assertEquals("items", names[0]);
  }

  @Test
  public void getRecordComponentNames_objectComponent_returnsCorrectName() {
    String[] names = ReflectionHelper.getRecordComponentNames(ObjectComponentRecord.class);
    assertNotNull(names);
    assertEquals(1, names.length);
    assertEquals("data", names[0]);
  }

  // ==========================================================================
  // Tests for nested records
  // ==========================================================================

  @Test
  public void getRecordComponentNames_nestedRecord_returnsCorrectNames() {
    String[] names = ReflectionHelper.getRecordComponentNames(OuterClass.NestedRecord.class);
    assertNotNull(names);
    assertEquals(2, names.length);
    assertEquals("id", names[0]);
    assertEquals("description", names[1]);
  }

  // ==========================================================================
  // Tests for generic records
  // ==========================================================================

  @Test
  public void getRecordComponentNames_genericRecord_returnsCorrectName() {
    String[] names = ReflectionHelper.getRecordComponentNames(GenericRecord.class);
    assertNotNull(names);
    assertEquals(1, names.length);
    assertEquals("value", names[0]);
  }

  @Test
  public void getRecordComponentNames_multipleGenericComponents_returnsCorrectNames() {
    String[] names = ReflectionHelper.getRecordComponentNames(MultiGenericRecord.class);
    assertNotNull(names);
    assertEquals(2, names.length);
    assertEquals("key", names[0]);
    assertEquals("value", names[1]);
  }

  // ==========================================================================
  // Tests for component order preservation
  // ==========================================================================

  @Test
  public void getRecordComponentNames_preservesDeclarationOrder() {
    String[] names = ReflectionHelper.getRecordComponentNames(OrderTestRecord.class);
    assertNotNull(names);
    assertEquals(4, names.length);
    // Verify order matches declaration order
    assertEquals("zebra", names[0]);
    assertEquals("apple", names[1]);
    assertEquals("mango", names[2]);
    assertEquals("banana", names[3]);
  }

  // ==========================================================================
  // Tests for records with special naming
  // ==========================================================================

  @Test
  public void getRecordComponentNames_underscoreNames_returnsCorrectNames() {
    String[] names = ReflectionHelper.getRecordComponentNames(UnderscoreNamesRecord.class);
    assertNotNull(names);
    assertEquals(2, names.length);
    assertEquals("first_name", names[0]);
    assertEquals("last_name", names[1]);
  }

  @Test
  public void getRecordComponentNames_singleCharNames_returnsCorrectNames() {
    String[] names = ReflectionHelper.getRecordComponentNames(SingleCharNamesRecord.class);
    assertNotNull(names);
    assertEquals(3, names.length);
    assertEquals("x", names[0]);
    assertEquals("y", names[1]);
    assertEquals("z", names[2]);
  }

  // ==========================================================================
  // Record definitions for testing
  // ==========================================================================

  record SingleComponentRecord(String name) {}

  record SingleIntRecord(int value) {}

  record TwoComponentRecord(String firstName, String lastName) {}

  record ThreeComponentRecord(String name, int age, boolean active) {}

  record ManyComponentRecord(String a, String b, String c, String d, String e) {}

  record EmptyRecord() {}

  record PrimitiveComponentsRecord(int intVal, long longVal, double doubleVal, boolean boolVal) {}

  record ArrayComponentRecord(String[] items) {}

  record ObjectComponentRecord(Object data) {}

  record GenericRecord<T>(T value) {}

  record MultiGenericRecord<K, V>(K key, V value) {}

  record OrderTestRecord(String zebra, String apple, String mango, String banana) {}

  record UnderscoreNamesRecord(String first_name, String last_name) {}

  record SingleCharNamesRecord(int x, int y, int z) {}

  static class OuterClass {
    record NestedRecord(int id, String description) {}
  }
}
