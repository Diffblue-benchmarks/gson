package com.example;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ClassWithUnreferencedHasArgsConstructorDiffblueTest {
  /**
   * Method under test: {@link
   * ClassWithUnreferencedHasArgsConstructor#ClassWithUnreferencedHasArgsConstructor(int)}
   */
  @Test
  public void testNewClassWithUnreferencedHasArgsConstructor() {
    // Arrange, Act and Assert
    assertEquals(1, (new ClassWithUnreferencedHasArgsConstructor(1)).i);
  }
}
