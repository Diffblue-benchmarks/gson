package com.google.gson.internal.reflect;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ReflectionHelperDiffblueTest {
  /**
   * Test {@link ReflectionHelper#isStatic(Class)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectionHelper#isStatic(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ReflectionHelper.isStatic(Class)"})
  public void testIsStatic_whenJavaLangObject_thenReturnFalse() {
    // Arrange
    Class<Object> clazz = Object.class;

    // Act and Assert
    assertFalse(ReflectionHelper.isStatic(clazz));
  }

  /**
   * Test {@link ReflectionHelper#isAnonymousOrNonStaticLocal(Class)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectionHelper#isAnonymousOrNonStaticLocal(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ReflectionHelper.isAnonymousOrNonStaticLocal(Class)"})
  public void testIsAnonymousOrNonStaticLocal_whenJavaLangObject_thenReturnFalse() {
    // Arrange
    Class<Object> clazz = Object.class;

    // Act and Assert
    assertFalse(ReflectionHelper.isAnonymousOrNonStaticLocal(clazz));
  }

  /**
   * Test {@link ReflectionHelper#isRecord(Class)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectionHelper#isRecord(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ReflectionHelper.isRecord(Class)"})
  public void testIsRecord_whenJavaLangObject_thenReturnFalse() {
    // Arrange
    Class<Object> raw = Object.class;

    // Act and Assert
    assertFalse(ReflectionHelper.isRecord(raw));
  }

  /**
   * Test {@link
   * ReflectionHelper#createExceptionForUnexpectedIllegalAccess(IllegalAccessException)}.
   *
   * <p>Method under test: {@link
   * ReflectionHelper#createExceptionForUnexpectedIllegalAccess(IllegalAccessException)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RuntimeException ReflectionHelper.createExceptionForUnexpectedIllegalAccess(IllegalAccessException)"
  })
  public void testCreateExceptionForUnexpectedIllegalAccess() {
    // Arrange, Act and Assert
    assertThrows(
        RuntimeException.class,
        () ->
            ReflectionHelper.createExceptionForUnexpectedIllegalAccess(
                new IllegalAccessException("foo")));
  }
}
