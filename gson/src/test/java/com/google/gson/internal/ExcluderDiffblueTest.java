package com.google.gson.internal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ExcluderDiffblueTest {
  /**
   * Test {@link Excluder#clone()}.
   *
   * <p>Method under test: {@link Excluder#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Excluder Excluder.clone()"})
  public void testClone() {
    // Arrange, Act and Assert
    assertFalse(Excluder.DEFAULT.clone().excludeClass(null, true));
  }

  /**
   * Test {@link Excluder#withModifiers(int[])}.
   *
   * <p>Method under test: {@link Excluder#withModifiers(int[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Excluder Excluder.withModifiers(int[])"})
  public void testWithModifiers() {
    // Arrange, Act and Assert
    assertFalse(Excluder.DEFAULT.withModifiers(1, 0, 1, 0).excludeClass(null, true));
  }

  /**
   * Test {@link Excluder#excludeFieldsWithoutExposeAnnotation()}.
   *
   * <p>Method under test: {@link Excluder#excludeFieldsWithoutExposeAnnotation()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Excluder Excluder.excludeFieldsWithoutExposeAnnotation()"})
  public void testExcludeFieldsWithoutExposeAnnotation() {
    // Arrange, Act and Assert
    assertFalse(Excluder.DEFAULT.excludeFieldsWithoutExposeAnnotation().excludeClass(null, true));
  }

  /**
   * Test {@link Excluder#withExclusionStrategy(ExclusionStrategy, boolean, boolean)}.
   *
   * <ul>
   *   <li>When {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link Excluder#withExclusionStrategy(ExclusionStrategy, boolean,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Excluder Excluder.withExclusionStrategy(ExclusionStrategy, boolean, boolean)"
  })
  public void testWithExclusionStrategy_whenFalse() {
    // Arrange, Act and Assert
    assertFalse(
        Excluder.DEFAULT
            .withExclusionStrategy(mock(ExclusionStrategy.class), false, false)
            .excludeClass(null, true));
  }

  /**
   * Test {@link Excluder#withExclusionStrategy(ExclusionStrategy, boolean, boolean)}.
   *
   * <ul>
   *   <li>When {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link Excluder#withExclusionStrategy(ExclusionStrategy, boolean,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Excluder Excluder.withExclusionStrategy(ExclusionStrategy, boolean, boolean)"
  })
  public void testWithExclusionStrategy_whenTrue() {
    // Arrange, Act and Assert
    assertFalse(
        Excluder.DEFAULT
            .withExclusionStrategy(mock(ExclusionStrategy.class), true, true)
            .excludeClass(null, true));
  }

  /**
   * Test {@link Excluder#create(Gson, TypeToken)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Excluder#create(Gson, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"com.google.gson.TypeAdapter Excluder.create(Gson, TypeToken)"})
  public void testCreate_whenJavaLangObject_thenReturnNull() {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> type2 = TypeToken.get(type);

    // Act and Assert
    assertNull(Excluder.DEFAULT.create(gson, type2));
  }

  /**
   * Test {@link Excluder#excludeClass(Class, boolean)}.
   *
   * <ul>
   *   <li>When {@code Enum}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link Excluder#excludeClass(Class, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Excluder.excludeClass(Class, boolean)"})
  public void testExcludeClass_whenJavaLangEnum_thenReturnFalse() {
    // Arrange
    Class<Enum> clazz = Enum.class;

    // Act and Assert
    assertFalse(Excluder.DEFAULT.excludeClass(clazz, false));
  }

  /**
   * Test {@link Excluder#excludeClass(Class, boolean)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link Excluder#excludeClass(Class, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Excluder.excludeClass(Class, boolean)"})
  public void testExcludeClass_whenJavaLangObject_thenReturnFalse() {
    // Arrange
    Class<Object> clazz = Object.class;

    // Act and Assert
    assertFalse(Excluder.DEFAULT.excludeClass(clazz, false));
  }

  /**
   * Test {@link Excluder#excludeClass(Class, boolean)}.
   *
   * <ul>
   *   <li>When {@code true}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link Excluder#excludeClass(Class, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Excluder.excludeClass(Class, boolean)"})
  public void testExcludeClass_whenTrue_thenReturnFalse() {
    // Arrange
    Class<Object> clazz = Object.class;

    // Act and Assert
    assertFalse(Excluder.DEFAULT.excludeClass(clazz, true));
  }

  /**
   * Test new {@link Excluder} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link Excluder}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Excluder.<init>()"})
  public void testNewExcluder() {
    // Arrange, Act and Assert
    assertFalse(new Excluder().excludeClass(null, true));
  }
}
