package com.example;

import static org.junit.Assert.assertEquals;

import com.diffblue.cover.annotations.MaintainedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ClassWithNoArgsConstructorDiffblueTest {
  /**
   * Test new {@link ClassWithNoArgsConstructor} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link
   * ClassWithNoArgsConstructor}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void ClassWithNoArgsConstructor.<init>()"})
  public void testNewClassWithNoArgsConstructor() {
    // Arrange, Act and Assert
    assertEquals(-3, (new ClassWithNoArgsConstructor()).i);
  }
}
