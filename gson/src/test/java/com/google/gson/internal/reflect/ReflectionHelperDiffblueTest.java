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
   *   <li>When createPublicField.
   *   <li>Then createPublicField Accessible.
   * </ul>
   *
   * <p>Method under test: {@link ReflectionHelper#makeAccessible(AccessibleObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ReflectionHelper.makeAccessible(AccessibleObject)"})
  public void testMakeAccessible_whenCreatePublicField_thenCreatePublicFieldAccessible()
      throws JsonIOException, NoSuchFieldException {
    // Arrange
    Field object = ReflectionHelperTestFactory.createPublicField();

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
  public void testGetAccessibleObjectDescription() throws NoSuchFieldException {
    // Arrange, Act and Assert
    assertEquals(
        "Field 'com.google.gson.internal.reflect.ReflectionHelperTestFactory$TestClass#publicField'",
        ReflectionHelper.getAccessibleObjectDescription(
            ReflectionHelperTestFactory.createPublicField(), true));
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
  public void testGetAccessibleObjectDescription2() throws NoSuchFieldException {
    // Arrange, Act and Assert
    assertEquals(
        "field 'com.google.gson.internal.reflect.ReflectionHelperTestFactory$TestClass#publicField'",
        ReflectionHelper.getAccessibleObjectDescription(
            ReflectionHelperTestFactory.createPublicField(), false));
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
  public void testGetAccessibleObjectDescription3() throws NoSuchMethodException {
    // Arrange, Act and Assert
    assertEquals(
        "Constructor 'com.google.gson.internal.reflect.ReflectionHelperTestFactory$TestClass()'",
        ReflectionHelper.getAccessibleObjectDescription(
            ReflectionHelperTestFactory.createAccessibleObjectConstructor(), true));
  }

  /**
   * Test {@link ReflectionHelper#getAccessibleObjectDescription(AccessibleObject, boolean)}.
   *
   * <ul>
   *   <li>Then return a string.
   * </ul>
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
  public void testGetAccessibleObjectDescription_thenReturnAString() throws NoSuchMethodException {
    // Arrange, Act and Assert
    assertEquals(
        "method 'com.google.gson.internal.reflect.ReflectionHelperTestFactory$TestClass#testMethodWithParam"
            + "(int)'",
        ReflectionHelper.getAccessibleObjectDescription(
            ReflectionHelperTestFactory.createMethodWithParameter(), false));
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
  public void testFieldToString() throws NoSuchFieldException {
    // Arrange, Act and Assert
    assertEquals(
        "com.google.gson.internal.reflect.ReflectionHelperTestFactory$TestClass#publicField",
        ReflectionHelper.fieldToString(ReflectionHelperTestFactory.createPublicField()));
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
  public void testConstructorToString() throws NoSuchMethodException {
    // Arrange
    Constructor<?> constructor = ReflectionHelperTestFactory.createConstructorWithSingleParameter();

    // Act and Assert
    assertEquals(
        "com.google.gson.internal.reflect.ReflectionHelperTestFactory$TestClass(String)",
        ReflectionHelper.constructorToString(constructor));
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
  public void testConstructorToString2() throws NoSuchMethodException {
    // Arrange
    Constructor<?> constructor =
        ReflectionHelperTestFactory.createConstructorWithMultipleParameters();

    // Act and Assert
    assertEquals(
        "com.google.gson.internal.reflect.ReflectionHelperTestFactory$TestClass(int, String)",
        ReflectionHelper.constructorToString(constructor));
  }

  /**
   * Test {@link ReflectionHelper#isStatic(Class)}.
   *
   * <ul>
   *   <li>When createClass.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectionHelper#isStatic(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ReflectionHelper.isStatic(Class)"})
  public void testIsStatic_whenCreateClass_thenReturnTrue() {
    // Arrange
    Class<?> clazz = ReflectionHelperTestFactory.createClass();

    // Act and Assert
    assertTrue(ReflectionHelper.isStatic(clazz));
  }

  /**
   * Test {@link ReflectionHelper#isStatic(Class)}.
   *
   * <ul>
   *   <li>When createStringClass.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectionHelper#isStatic(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ReflectionHelper.isStatic(Class)"})
  public void testIsStatic_whenCreateStringClass_thenReturnFalse() {
    // Arrange
    Class<?> clazz = ReflectionHelperTestFactory.createStringClass();

    // Act and Assert
    assertFalse(ReflectionHelper.isStatic(clazz));
  }

  /**
   * Test {@link ReflectionHelper#isAnonymousOrNonStaticLocal(Class)}.
   *
   * <ul>
   *   <li>When createClass.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectionHelper#isAnonymousOrNonStaticLocal(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ReflectionHelper.isAnonymousOrNonStaticLocal(Class)"})
  public void testIsAnonymousOrNonStaticLocal_whenCreateClass_thenReturnFalse() {
    // Arrange
    Class<?> clazz = ReflectionHelperTestFactory.createClass();

    // Act and Assert
    assertFalse(ReflectionHelper.isAnonymousOrNonStaticLocal(clazz));
  }

  /**
   * Test {@link ReflectionHelper#isAnonymousOrNonStaticLocal(Class)}.
   *
   * <ul>
   *   <li>When createStringClass.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectionHelper#isAnonymousOrNonStaticLocal(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ReflectionHelper.isAnonymousOrNonStaticLocal(Class)"})
  public void testIsAnonymousOrNonStaticLocal_whenCreateStringClass_thenReturnFalse() {
    // Arrange
    Class<?> clazz = ReflectionHelperTestFactory.createStringClass();

    // Act and Assert
    assertFalse(ReflectionHelper.isAnonymousOrNonStaticLocal(clazz));
  }

  /**
   * Test {@link ReflectionHelper#tryMakeAccessible(Constructor)}.
   *
   * <ul>
   *   <li>When createConstructorWithSingleParameter.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectionHelper#tryMakeAccessible(Constructor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String ReflectionHelper.tryMakeAccessible(Constructor)"})
  public void testTryMakeAccessible_whenCreateConstructorWithSingleParameter_thenReturnNull()
      throws NoSuchMethodException {
    // Arrange
    Constructor<?> constructor = ReflectionHelperTestFactory.createConstructorWithSingleParameter();

    // Act and Assert
    assertNull(ReflectionHelper.tryMakeAccessible(constructor));
    assertTrue(constructor.isAccessible());
  }

  /**
   * Test {@link ReflectionHelper#isRecord(Class)}.
   *
   * <ul>
   *   <li>When createStringClass.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectionHelper#isRecord(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ReflectionHelper.isRecord(Class)"})
  public void testIsRecord_whenCreateStringClass_thenReturnFalse() {
    // Arrange
    Class<?> raw = ReflectionHelperTestFactory.createStringClass();

    // Act and Assert
    assertFalse(ReflectionHelper.isRecord(raw));
  }

  /**
   * Test {@link ReflectionHelper#getAccessor(Class, Field)}.
   *
   * <ul>
   *   <li>When createPublicField.
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectionHelper#getAccessor(Class, Field)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.reflect.Method ReflectionHelper.getAccessor(Class, Field)"})
  public void testGetAccessor_whenCreatePublicField_thenThrowRuntimeException()
      throws NoSuchFieldException {
    // Arrange
    Class<?> raw = ReflectionHelperTestFactory.createStringClass();

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () -> ReflectionHelper.getAccessor(raw, ReflectionHelperTestFactory.createPublicField()));
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
                new IllegalAccessException()));
  }
}
