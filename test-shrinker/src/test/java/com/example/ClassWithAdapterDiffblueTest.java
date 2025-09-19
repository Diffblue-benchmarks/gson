package com.example;

import static org.junit.Assert.assertEquals;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ClassWithAdapterDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ClassWithAdapter#ClassWithAdapter(int)}
   *   <li>{@link ClassWithAdapter#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ClassWithAdapter.<init>(int)",
    "java.lang.String ClassWithAdapter.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    ClassWithAdapter actualClassWithAdapter = new ClassWithAdapter(1);

    // Assert
    assertEquals("ClassWithAdapter[1]", actualClassWithAdapter.toString());
    assertEquals(1, actualClassWithAdapter.i.intValue());
  }
}
