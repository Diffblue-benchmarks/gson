package com.google.gson.typeadapters;

import static org.junit.Assert.assertSame;

import com.diffblue.cover.annotations.MaintainedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RuntimeTypeAdapterFactoryDiffblueTest {
  /**
   * Test {@link RuntimeTypeAdapterFactory#recognizeSubtypes()}.
   *
   * <p>Method under test: {@link RuntimeTypeAdapterFactory#recognizeSubtypes()}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"RuntimeTypeAdapterFactory RuntimeTypeAdapterFactory.recognizeSubtypes()"})
  public void testRecognizeSubtypes() {
    // Arrange
    Class<Object> baseType = Object.class;
    RuntimeTypeAdapterFactory<Object> ofResult = RuntimeTypeAdapterFactory.of(baseType);

    // Act and Assert
    assertSame(ofResult, ofResult.recognizeSubtypes());
  }

  /**
   * Test {@link RuntimeTypeAdapterFactory#registerSubtype(Class)} with {@code type}.
   *
   * <p>Method under test: {@link RuntimeTypeAdapterFactory#registerSubtype(Class)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"RuntimeTypeAdapterFactory RuntimeTypeAdapterFactory.registerSubtype(Class)"})
  public void testRegisterSubtypeWithType() {
    // Arrange
    Class<Object> baseType = Object.class;
    RuntimeTypeAdapterFactory<Object> ofResult = RuntimeTypeAdapterFactory.of(baseType);
    Class<Object> type = Object.class;

    // Act and Assert
    assertSame(ofResult, ofResult.registerSubtype(type));
  }

  /**
   * Test {@link RuntimeTypeAdapterFactory#registerSubtype(Class, String)} with {@code type}, {@code
   * label}.
   *
   * <p>Method under test: {@link RuntimeTypeAdapterFactory#registerSubtype(Class, String)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
    "RuntimeTypeAdapterFactory RuntimeTypeAdapterFactory.registerSubtype(Class, String)"
  })
  public void testRegisterSubtypeWithTypeLabel() {
    // Arrange
    Class<Object> baseType = Object.class;
    RuntimeTypeAdapterFactory<Object> ofResult = RuntimeTypeAdapterFactory.of(baseType);
    Class<Object> type = Object.class;

    // Act and Assert
    assertSame(ofResult, ofResult.registerSubtype(type, "Label"));
  }
}
