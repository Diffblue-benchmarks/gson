package com.google.gson;

import static org.junit.Assert.assertEquals;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.internal.reflect.ReflectionHelperDiffblueTestFactory;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class FieldAttributesDiffblueTest {
  /**
   * Test {@link FieldAttributes#toString()}.
   *
   * <p>Method under test: {@link FieldAttributes#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String FieldAttributes.toString()"})
  public void testToString() throws NoSuchFieldException {
    // Arrange, Act and Assert
    assertEquals(
        "private java.lang.String"
            + " com.google.gson.internal.reflect.ReflectionHelperDiffblueTestFactory$TestClass"
            + ".testField",
        new FieldAttributes(ReflectionHelperDiffblueTestFactory.createFieldForFieldToString())
            .toString());
  }
}
