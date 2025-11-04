package com.example;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ClassWithNoArgsConstructorDiffblueTest {
  /**
   * Method under test: default or parameterless constructor of {@link ClassWithNoArgsConstructor}
   */
  @Test
  public void testNewClassWithNoArgsConstructor() {
    // Arrange, Act and Assert
    assertEquals(-3, (new ClassWithNoArgsConstructor()).i);
  }
}
