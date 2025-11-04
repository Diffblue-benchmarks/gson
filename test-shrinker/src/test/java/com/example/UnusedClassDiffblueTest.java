package com.example;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UnusedClassDiffblueTest {
  /** Method under test: default or parameterless constructor of {@link UnusedClass} */
  @Test
  public void testNewUnusedClass() {
    // Arrange, Act and Assert
    assertEquals(0, (new UnusedClass()).i);
  }
}
