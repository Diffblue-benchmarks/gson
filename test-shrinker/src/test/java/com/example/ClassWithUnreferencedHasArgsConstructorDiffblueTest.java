package com.example;

import static org.junit.Assert.assertEquals;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ClassWithUnreferencedHasArgsConstructorDiffblueTest {
  /**
   * Test {@link
   * ClassWithUnreferencedHasArgsConstructor#ClassWithUnreferencedHasArgsConstructor(int)}.
   *
   * <p>Method under test: {@link
   * ClassWithUnreferencedHasArgsConstructor#ClassWithUnreferencedHasArgsConstructor(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassWithUnreferencedHasArgsConstructor.<init>(int)"})
  public void testNewClassWithUnreferencedHasArgsConstructor() {
    // Arrange, Act and Assert
    assertEquals(1, new ClassWithUnreferencedHasArgsConstructor(1).i);
  }
}
