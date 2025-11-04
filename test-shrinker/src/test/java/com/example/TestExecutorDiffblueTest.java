package com.example;

import static org.junit.Assert.assertEquals;

import com.diffblue.cover.annotations.MaintainedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class TestExecutorDiffblueTest {
  /**
   * Test {@link TestExecutor#same(Object)}.
   *
   * <p>Method under test: {@link TestExecutor#same(Object)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"Object TestExecutor.same(Object)"})
  public void testSame() {
    // Arrange, Act and Assert
    assertEquals("42", TestExecutor.same("42"));
  }
}
