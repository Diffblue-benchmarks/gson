package com.example;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ClassWithUnreferencedNoArgsConstructorDiffblueTest {
  /**
   * Method under test: default or parameterless constructor of {@link
   * ClassWithUnreferencedNoArgsConstructor}
   */
  @Test
  public void testNewClassWithUnreferencedNoArgsConstructor() {
    // Arrange, Act and Assert
    assertEquals(-3, (new ClassWithUnreferencedNoArgsConstructor()).i);
  }
}
