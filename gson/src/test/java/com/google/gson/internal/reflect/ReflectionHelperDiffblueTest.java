package com.google.gson.internal.reflect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.JsonIOException;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ReflectionHelperDiffblueTest {
  /**
   * Test {@link ReflectionHelper#makeAccessible(AccessibleObject)}.
   *
   * <ul>
   *   <li>When createNonRecordField.
   *   <li>Then createNonRecordField Accessible.
   * </ul>
   *
   * <p>Method under test: {@link ReflectionHelper#makeAccessible(AccessibleObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ReflectionHelper.makeAccessible(AccessibleObject)"})
  public void testMakeAccessible_whenCreateNonRecordField_thenCreateNonRecordFieldAccessible()
      throws JsonIOException {
    // Arrange
    Field object = ReflectionHelperFactory.createNonRecordField();

    // Act
    ReflectionHelper.makeAccessible(object);

    // Assert
    assertTrue(object.isAccessible());
  }

  /**
   * Test {@link ReflectionHelper#getAccessibleObjectDescription(AccessibleObject, boolean)}.
   *
   * <p>Method under test: {@link ReflectionHelper#getAccessibleObjectDescription(AccessibleObject,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.lang.String ReflectionHelper.getAccessibleObjectDescription(AccessibleObject, boolean)"
  })
  public void testGetAccessibleObjectDescription() {
    // Arrange, Act and Assert
    assertEquals(
        "Field 'com.google.gson.internal.reflect.ReflectionHelperFactory$TestClass#testField'",
        ReflectionHelper.getAccessibleObjectDescription(
            ReflectionHelperFactory.createNonRecordField(), true));
  }

  /**
   * Test {@link ReflectionHelper#getAccessibleObjectDescription(AccessibleObject, boolean)}.
   *
   * <p>Method under test: {@link ReflectionHelper#getAccessibleObjectDescription(AccessibleObject,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.lang.String ReflectionHelper.getAccessibleObjectDescription(AccessibleObject, boolean)"
  })
  public void testGetAccessibleObjectDescription2() {
    // Arrange, Act and Assert
    assertEquals(
        "field 'com.google.gson.internal.reflect.ReflectionHelperFactory$TestClass#testField'",
        ReflectionHelper.getAccessibleObjectDescription(
            ReflectionHelperFactory.createNonRecordField(), false));
  }

  /**
   * Test {@link ReflectionHelper#fieldToString(Field)}.
   *
   * <p>Method under test: {@link ReflectionHelper#fieldToString(Field)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String ReflectionHelper.fieldToString(Field)"})
  public void testFieldToString() {
    // Arrange, Act and Assert
    assertEquals(
        "com.google.gson.internal.reflect.ReflectionHelperFactory$TestClass#testField",
        ReflectionHelper.fieldToString(ReflectionHelperFactory.createNonRecordField()));
  }

  /**
   * Test {@link ReflectionHelper#constructorToString(Constructor)}.
   *
   * <p>Method under test: {@link ReflectionHelper#constructorToString(Constructor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String ReflectionHelper.constructorToString(Constructor)"})
  public void testConstructorToString() {
    // Arrange
    Constructor<?> constructor = ReflectionHelperFactory.createConstructorWithParameters();

    // Act and Assert
    assertEquals(
        "com.google.gson.internal.reflect.ReflectionHelperFactory$TestClass(String, int)",
        ReflectionHelper.constructorToString(constructor));
  }

  /**
   * Test {@link ReflectionHelper#isStatic(Class)}.
   *
   * <ul>
   *   <li>When createRecordClass.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectionHelper#isStatic(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ReflectionHelper.isStatic(Class)"})
  public void testIsStatic_whenCreateRecordClass_thenReturnTrue() {
    // Arrange
    Class<?> clazz = ReflectionHelperFactory.createRecordClass();

    // Act and Assert
    assertTrue(ReflectionHelper.isStatic(clazz));
  }

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
   *   <li>When createRecordClass.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectionHelper#isAnonymousOrNonStaticLocal(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ReflectionHelper.isAnonymousOrNonStaticLocal(Class)"})
  public void testIsAnonymousOrNonStaticLocal_whenCreateRecordClass_thenReturnFalse() {
    // Arrange
    Class<?> clazz = ReflectionHelperFactory.createRecordClass();

    // Act and Assert
    assertFalse(ReflectionHelper.isAnonymousOrNonStaticLocal(clazz));
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
   * Test {@link ReflectionHelper#tryMakeAccessible(Constructor)}.
   *
   * <ul>
   *   <li>When createConstructorWithParameters.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectionHelper#tryMakeAccessible(Constructor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String ReflectionHelper.tryMakeAccessible(Constructor)"})
  public void testTryMakeAccessible_whenCreateConstructorWithParameters_thenReturnNull() {
    // Arrange
    Constructor<?> constructor = ReflectionHelperFactory.createConstructorWithParameters();

    // Act and Assert
    assertNull(ReflectionHelper.tryMakeAccessible(constructor));
    assertTrue(constructor.isAccessible());
  }

  /**
   * Test {@link ReflectionHelper#isRecord(Class)}.
   *
   * <p>Method under test: {@link ReflectionHelper#isRecord(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ReflectionHelper.isRecord(Class)"})
  public void testIsRecord() {
    // Arrange
    Class<?> raw = ReflectionHelperFactory.createRecordClass();

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
    "RuntimeException"
        + " ReflectionHelper.createExceptionForUnexpectedIllegalAccess(IllegalAccessException)"
  })
  public void testCreateExceptionForUnexpectedIllegalAccess() {
    // Arrange, Act and Assert
    assertThrows(
        RuntimeException.class,
        () ->
            ReflectionHelper.createExceptionForUnexpectedIllegalAccess(
                new IllegalAccessException()));
  }
}
