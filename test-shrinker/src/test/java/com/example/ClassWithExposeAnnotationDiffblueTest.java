package com.example;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ClassWithExposeAnnotationDiffblueTest {
  /**
   * Method under test: default or parameterless constructor of {@link ClassWithExposeAnnotation}
   */
  @Test
  public void testNewClassWithExposeAnnotation() {
    // Arrange and Act
    ClassWithExposeAnnotation actualClassWithExposeAnnotation = new ClassWithExposeAnnotation();

    // Assert
    assertEquals(0, actualClassWithExposeAnnotation.i);
    assertEquals(0, actualClassWithExposeAnnotation.i2);
  }
}
