package com.example;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ClassWithVersionAnnotationsDiffblueTest {
  /**
   * Method under test: default or parameterless constructor of {@link ClassWithVersionAnnotations}
   */
  @Test
  public void testNewClassWithVersionAnnotations() {
    // Arrange and Act
    ClassWithVersionAnnotations actualClassWithVersionAnnotations =
        new ClassWithVersionAnnotations();

    // Assert
    assertEquals(0, actualClassWithVersionAnnotations.i1);
    assertEquals(0, actualClassWithVersionAnnotations.i2);
    assertEquals(0, actualClassWithVersionAnnotations.i3);
    assertEquals(0, actualClassWithVersionAnnotations.i4);
  }
}
