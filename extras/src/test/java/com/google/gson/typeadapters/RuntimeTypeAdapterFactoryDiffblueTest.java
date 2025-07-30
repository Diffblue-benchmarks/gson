package com.google.gson.typeadapters;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RuntimeTypeAdapterFactoryDiffblueTest {
  /**
   * Test {@link RuntimeTypeAdapterFactory#recognizeSubtypes()}.
   *
   * <p>Method under test: {@link RuntimeTypeAdapterFactory#recognizeSubtypes()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
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

  /**
   * Test {@link RuntimeTypeAdapterFactory#create(Gson, TypeToken)}.
   *
   * <ul>
   *   <li>Given {@code Object}.
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link RuntimeTypeAdapterFactory#create(Gson, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "com.google.gson.TypeAdapter RuntimeTypeAdapterFactory.create(Gson, TypeToken)"
  })
  public void testCreate_givenJavaLangObject_whenNull_thenReturnNull() {
    // Arrange
    Class<Object> baseType = Object.class;
    RuntimeTypeAdapterFactory<Object> ofResult = RuntimeTypeAdapterFactory.of(baseType);

    // Act and Assert
    assertNull(ofResult.create(new Gson(), null));
  }
}
