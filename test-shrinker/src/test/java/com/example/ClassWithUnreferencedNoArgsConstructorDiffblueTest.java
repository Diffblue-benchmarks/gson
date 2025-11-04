package com.example;

import static org.junit.Assert.assertEquals;

import com.diffblue.cover.annotations.MaintainedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ClassWithUnreferencedNoArgsConstructorDiffblueTest {
  /**
   * Test new {@link ClassWithUnreferencedNoArgsConstructor} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link
   * ClassWithUnreferencedNoArgsConstructor}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void ClassWithUnreferencedNoArgsConstructor.<init>()"})
  public void testNewClassWithUnreferencedNoArgsConstructor() {
    // Arrange, Act and Assert
    assertEquals(-3, (new ClassWithUnreferencedNoArgsConstructor()).i);
  }
}
