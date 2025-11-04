package com.google.gson.internal.reflect;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class ReflectionHelperDiffblueTest {
  /**
   * Method under test: {@link
   * ReflectionHelper#createExceptionForUnexpectedIllegalAccess(IllegalAccessException)}
   */
  @Test
  public void testCreateExceptionForUnexpectedIllegalAccess() {
    // Arrange, Act and Assert
    assertThrows(
        RuntimeException.class,
        () ->
            ReflectionHelper.createExceptionForUnexpectedIllegalAccess(
                new IllegalAccessException("foo")));
  }

  /** Method under test: {@link ReflectionHelper#isAnonymousOrNonStaticLocal(Class)} */
  @Test
  public void testIsAnonymousOrNonStaticLocal() {
    // Arrange
    Class<Object> clazz = Object.class;

    // Act and Assert
    assertFalse(ReflectionHelper.isAnonymousOrNonStaticLocal(clazz));
  }

  /** Method under test: {@link ReflectionHelper#isRecord(Class)} */
  @Test
  public void testIsRecord() {
    // Arrange
    Class<Object> raw = Object.class;

    // Act and Assert
    assertFalse(ReflectionHelper.isRecord(raw));
  }

  /** Method under test: {@link ReflectionHelper#isStatic(Class)} */
  @Test
  public void testIsStatic() {
    // Arrange
    Class<Object> clazz = Object.class;

    // Act and Assert
    assertFalse(ReflectionHelper.isStatic(clazz));
  }
}
