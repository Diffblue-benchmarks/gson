package com.example;

import static org.junit.Assert.assertEquals;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ClassWithSerializedNameDiffblueTest {
  /**
   * Test {@link ClassWithSerializedName#ClassWithSerializedName(int)}.
   *
   * <p>Method under test: {@link ClassWithSerializedName#ClassWithSerializedName(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassWithSerializedName.<init>(int)"})
  public void testNewClassWithSerializedName() {
    // Arrange and Act
    ClassWithSerializedName actualClassWithSerializedName = new ClassWithSerializedName(1);

    // Assert
    assertEquals((short) -1, actualClassWithSerializedName.notAccessedField);
    assertEquals(1, actualClassWithSerializedName.i);
  }
}
