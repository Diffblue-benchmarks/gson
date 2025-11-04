package com.example;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ClassWithHasArgsConstructorDiffblueTest {
  /** Method under test: {@link ClassWithHasArgsConstructor#ClassWithHasArgsConstructor(int)} */
  @Test
  public void testNewClassWithHasArgsConstructor() {
    // Arrange, Act and Assert
    assertEquals(1, (new ClassWithHasArgsConstructor(1)).i);
  }
}
