package com.example;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ClassWithHasArgsConstructorDiffblueTest {
  /**
   * Test {@link ClassWithHasArgsConstructor#ClassWithHasArgsConstructor(int)}.
   *
   * <p>Method under test: {@link ClassWithHasArgsConstructor#ClassWithHasArgsConstructor(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassWithHasArgsConstructor.<init>(int)"})
  public void testNewClassWithHasArgsConstructor() {
    // Arrange, Act and Assert
    assertEquals(1, new ClassWithHasArgsConstructor(1).i);
  }
}
