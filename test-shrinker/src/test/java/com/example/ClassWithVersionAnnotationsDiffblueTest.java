package com.example;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ClassWithVersionAnnotationsDiffblueTest {
  /**
   * Test new {@link ClassWithVersionAnnotations} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link
   * ClassWithVersionAnnotations}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassWithVersionAnnotations.<init>()"})
  public void testNewClassWithVersionAnnotations() {
    // Arrange and Act
    ClassWithVersionAnnotations actualClassWithVersionAnnotations =
        new ClassWithVersionAnnotations();

    // Assert
    assertEquals(0, actualClassWithVersionAnnotations.i1);
    assertEquals(0, actualClassWithVersionAnnotations.i2);
    assertEquals(0, actualClassWithVersionAnnotations.i3);
    assertEquals(0, actualClassWithVersionAnnotations.i4);
  }
}
