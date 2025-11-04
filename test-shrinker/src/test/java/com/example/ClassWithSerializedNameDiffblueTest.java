package com.example;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ClassWithSerializedNameDiffblueTest {
  /** Method under test: {@link ClassWithSerializedName#ClassWithSerializedName(int)} */
  @Test
  public void testNewClassWithSerializedName() {
    // Arrange and Act
    ClassWithSerializedName actualClassWithSerializedName = new ClassWithSerializedName(1);

    // Assert
    assertEquals((short) -1, actualClassWithSerializedName.notAccessedField);
    assertEquals(1, actualClassWithSerializedName.i);
  }
}
