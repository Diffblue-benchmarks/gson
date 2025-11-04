package com.example;

import static org.junit.Assert.assertEquals;

import com.diffblue.cover.annotations.MaintainedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class UnusedClassDiffblueTest {
  /**
   * Test new {@link UnusedClass} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link UnusedClass}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void UnusedClass.<init>()"})
  public void testNewUnusedClass() {
    // Arrange, Act and Assert
    assertEquals(0, (new UnusedClass()).i);
  }
}
