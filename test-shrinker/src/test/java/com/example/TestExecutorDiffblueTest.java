package com.example;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestExecutorDiffblueTest {
  /** Method under test: {@link TestExecutor#same(Object)} */
  @Test
  public void testSame() {
    // Arrange, Act and Assert
    assertEquals("42", TestExecutor.same("42"));
  }
}
