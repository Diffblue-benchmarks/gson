package com.example;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ClassWithNamedFieldsDiffblueTest {
  /** Method under test: {@link ClassWithNamedFields#ClassWithNamedFields(int)} */
  @Test
  public void testNewClassWithNamedFields() {
    // Arrange and Act
    ClassWithNamedFields actualClassWithNamedFields = new ClassWithNamedFields(1);

    // Assert
    assertEquals((short) -1, actualClassWithNamedFields.notAccessedField);
    assertEquals(1, actualClassWithNamedFields.myField);
  }
}
