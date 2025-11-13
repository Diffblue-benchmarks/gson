package com.example;

import static org.junit.Assert.assertEquals;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ClassWithExposeAnnotationDiffblueTest {
  /**
   * Test new {@link ClassWithExposeAnnotation} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link ClassWithExposeAnnotation}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassWithExposeAnnotation.<init>()"})
  public void testNewClassWithExposeAnnotation() {
    // Arrange and Act
    ClassWithExposeAnnotation actualClassWithExposeAnnotation = new ClassWithExposeAnnotation();

    // Assert
    assertEquals(0, actualClassWithExposeAnnotation.i);
    assertEquals(0, actualClassWithExposeAnnotation.i2);
  }
}
