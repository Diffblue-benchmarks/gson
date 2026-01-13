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
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.junit.Test;

/**
 * Tests for {@link ReflectionHelper#getAccessor(Class, java.lang.reflect.Field)}.
 */
public class ReflectionHelperClaude_getAccessorTest {

  // ==========================================================================
  // Tests for single component records
  // ==========================================================================

  @Test
  public void getAccessor_singleStringComponent_returnsCorrectMethod() throws Exception {
    Field field = SingleComponentRecord.class.getDeclaredField("name");
    Method accessor = ReflectionHelper.getAccessor(SingleComponentRecord.class, field);

    assertNotNull(accessor);
    assertEquals("name", accessor.getName());
    assertEquals(String.class, accessor.getReturnType());
    assertEquals(0, accessor.getParameterCount());
  }

  @Test
  public void getAccessor_singleIntComponent_returnsCorrectMethod() throws Exception {
    Field field = SingleIntRecord.class.getDeclaredField("value");
    Method accessor = ReflectionHelper.getAccessor(SingleIntRecord.class, field);

    assertNotNull(accessor);
    assertEquals("value", accessor.getName());
    assertEquals(int.class, accessor.getReturnType());
    assertEquals(0, accessor.getParameterCount());
  }

  // ==========================================================================
  // Tests for multiple component records
  // ==========================================================================

  @Test
  public void getAccessor_multipleComponents_returnsCorrectMethodForEach() throws Exception {
    // Test first component
    Field firstNameField = PersonRecord.class.getDeclaredField("firstName");
    Method firstNameAccessor = ReflectionHelper.getAccessor(PersonRecord.class, firstNameField);
    assertNotNull(firstNameAccessor);
    assertEquals("firstName", firstNameAccessor.getName());
    assertEquals(String.class, firstNameAccessor.getReturnType());

    // Test second component
    Field lastNameField = PersonRecord.class.getDeclaredField("lastName");
    Method lastNameAccessor = ReflectionHelper.getAccessor(PersonRecord.class, lastNameField);
    assertNotNull(lastNameAccessor);
    assertEquals("lastName", lastNameAccessor.getName());
    assertEquals(String.class, lastNameAccessor.getReturnType());

    // Test third component
    Field ageField = PersonRecord.class.getDeclaredField("age");
    Method ageAccessor = ReflectionHelper.getAccessor(PersonRecord.class, ageField);
    assertNotNull(ageAccessor);
    assertEquals("age", ageAccessor.getName());
    assertEquals(int.class, ageAccessor.getReturnType());
  }

  // ==========================================================================
  // Tests for different component types
  // ==========================================================================

  @Test
  public void getAccessor_primitiveComponents_returnsCorrectMethods() throws Exception {
    Field intField = PrimitiveRecord.class.getDeclaredField("intVal");
    Method intAccessor = ReflectionHelper.getAccessor(PrimitiveRecord.class, intField);
    assertNotNull(intAccessor);
    assertEquals("intVal", intAccessor.getName());
    assertEquals(int.class, intAccessor.getReturnType());

    Field longField = PrimitiveRecord.class.getDeclaredField("longVal");
    Method longAccessor = ReflectionHelper.getAccessor(PrimitiveRecord.class, longField);
    assertNotNull(longAccessor);
    assertEquals("longVal", longAccessor.getName());
    assertEquals(long.class, longAccessor.getReturnType());

    Field doubleField = PrimitiveRecord.class.getDeclaredField("doubleVal");
    Method doubleAccessor = ReflectionHelper.getAccessor(PrimitiveRecord.class, doubleField);
    assertNotNull(doubleAccessor);
    assertEquals("doubleVal", doubleAccessor.getName());
    assertEquals(double.class, doubleAccessor.getReturnType());

    Field boolField = PrimitiveRecord.class.getDeclaredField("boolVal");
    Method boolAccessor = ReflectionHelper.getAccessor(PrimitiveRecord.class, boolField);
    assertNotNull(boolAccessor);
    assertEquals("boolVal", boolAccessor.getName());
    assertEquals(boolean.class, boolAccessor.getReturnType());
  }

  @Test
  public void getAccessor_arrayComponent_returnsCorrectMethod() throws Exception {
    Field field = ArrayRecord.class.getDeclaredField("items");
    Method accessor = ReflectionHelper.getAccessor(ArrayRecord.class, field);

    assertNotNull(accessor);
    assertEquals("items", accessor.getName());
    assertEquals(String[].class, accessor.getReturnType());
  }

  @Test
  public void getAccessor_objectComponent_returnsCorrectMethod() throws Exception {
    Field field = ObjectRecord.class.getDeclaredField("data");
    Method accessor = ReflectionHelper.getAccessor(ObjectRecord.class, field);

    assertNotNull(accessor);
    assertEquals("data", accessor.getName());
    assertEquals(Object.class, accessor.getReturnType());
  }

  // ==========================================================================
  // Tests for nested records
  // ==========================================================================

  @Test
  public void getAccessor_nestedRecord_returnsCorrectMethod() throws Exception {
    Field field = OuterClass.NestedRecord.class.getDeclaredField("id");
    Method accessor = ReflectionHelper.getAccessor(OuterClass.NestedRecord.class, field);

    assertNotNull(accessor);
    assertEquals("id", accessor.getName());
    assertEquals(int.class, accessor.getReturnType());
  }

  // ==========================================================================
  // Tests for generic records
  // ==========================================================================

  @Test
  public void getAccessor_genericRecord_returnsCorrectMethod() throws Exception {
    Field field = GenericRecord.class.getDeclaredField("value");
    Method accessor = ReflectionHelper.getAccessor(GenericRecord.class, field);

    assertNotNull(accessor);
    assertEquals("value", accessor.getName());
    // Generic type erases to Object
    assertEquals(Object.class, accessor.getReturnType());
  }

  // ==========================================================================
  // Tests for accessor method invocation
  // ==========================================================================

  @Test
  public void getAccessor_canInvokeAccessor() throws Exception {
    SingleComponentRecord record = new SingleComponentRecord("test");

    Field field = SingleComponentRecord.class.getDeclaredField("name");
    Method accessor = ReflectionHelper.getAccessor(SingleComponentRecord.class, field);

    Object result = accessor.invoke(record);
    assertEquals("test", result);
  }

  @Test
  public void getAccessor_canInvokeMultipleAccessors() throws Exception {
    PersonRecord record = new PersonRecord("John", "Doe", 30);

    Field firstNameField = PersonRecord.class.getDeclaredField("firstName");
    Method firstNameAccessor = ReflectionHelper.getAccessor(PersonRecord.class, firstNameField);
    assertEquals("John", firstNameAccessor.invoke(record));

    Field lastNameField = PersonRecord.class.getDeclaredField("lastName");
    Method lastNameAccessor = ReflectionHelper.getAccessor(PersonRecord.class, lastNameField);
    assertEquals("Doe", lastNameAccessor.invoke(record));

    Field ageField = PersonRecord.class.getDeclaredField("age");
    Method ageAccessor = ReflectionHelper.getAccessor(PersonRecord.class, ageField);
    assertEquals(30, ageAccessor.invoke(record));
  }

  @Test
  public void getAccessor_canInvokePrimitiveAccessors() throws Exception {
    PrimitiveRecord record = new PrimitiveRecord(42, 100L, 3.14, true);

    Field intField = PrimitiveRecord.class.getDeclaredField("intVal");
    Method intAccessor = ReflectionHelper.getAccessor(PrimitiveRecord.class, intField);
    assertEquals(42, intAccessor.invoke(record));

    Field longField = PrimitiveRecord.class.getDeclaredField("longVal");
    Method longAccessor = ReflectionHelper.getAccessor(PrimitiveRecord.class, longField);
    assertEquals(100L, longAccessor.invoke(record));

    Field doubleField = PrimitiveRecord.class.getDeclaredField("doubleVal");
    Method doubleAccessor = ReflectionHelper.getAccessor(PrimitiveRecord.class, doubleField);
    assertEquals(3.14, doubleAccessor.invoke(record));

    Field boolField = PrimitiveRecord.class.getDeclaredField("boolVal");
    Method boolAccessor = ReflectionHelper.getAccessor(PrimitiveRecord.class, boolField);
    assertEquals(true, boolAccessor.invoke(record));
  }

  // ==========================================================================
  // Tests for special naming
  // ==========================================================================

  @Test
  public void getAccessor_underscoreNames_returnsCorrectMethod() throws Exception {
    Field field = UnderscoreRecord.class.getDeclaredField("first_name");
    Method accessor = ReflectionHelper.getAccessor(UnderscoreRecord.class, field);

    assertNotNull(accessor);
    assertEquals("first_name", accessor.getName());
  }

  @Test
  public void getAccessor_singleCharName_returnsCorrectMethod() throws Exception {
    Field field = CoordinateRecord.class.getDeclaredField("x");
    Method accessor = ReflectionHelper.getAccessor(CoordinateRecord.class, field);

    assertNotNull(accessor);
    assertEquals("x", accessor.getName());
    assertEquals(int.class, accessor.getReturnType());
  }

  // ==========================================================================
  // Record definitions for testing
  // ==========================================================================

  record SingleComponentRecord(String name) {}

  record SingleIntRecord(int value) {}

  record PersonRecord(String firstName, String lastName, int age) {}

  record PrimitiveRecord(int intVal, long longVal, double doubleVal, boolean boolVal) {}

  record ArrayRecord(String[] items) {}

  record ObjectRecord(Object data) {}

  record GenericRecord<T>(T value) {}

  record UnderscoreRecord(String first_name) {}

  record CoordinateRecord(int x, int y, int z) {}

  static class OuterClass {
    record NestedRecord(int id, String description) {}
  }
}
