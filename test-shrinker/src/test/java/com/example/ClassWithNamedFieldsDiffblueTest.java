package com.example;

import static org.junit.Assert.assertEquals;

import com.diffblue.cover.annotations.MaintainedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ClassWithNamedFieldsDiffblueTest {
  /**
   * Test {@link ClassWithNamedFields#ClassWithNamedFields(int)}.
   *
   * <p>Method under test: {@link ClassWithNamedFields#ClassWithNamedFields(int)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void ClassWithNamedFields.<init>(int)"})
  public void testNewClassWithNamedFields() {
    // Arrange and Act
    ClassWithNamedFields actualClassWithNamedFields = new ClassWithNamedFields(1);

    // Assert
    assertEquals((short) -1, actualClassWithNamedFields.notAccessedField);
    assertEquals(1, actualClassWithNamedFields.myField);
  }
}
