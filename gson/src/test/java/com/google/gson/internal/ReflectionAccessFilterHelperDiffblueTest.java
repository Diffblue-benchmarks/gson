package com.google.gson.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.ReflectionAccessFilter;
import com.google.gson.ReflectionAccessFilter.FilterResult;
import com.google.gson.internal.reflect.ReflectionHelperTestFactory;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class ReflectionAccessFilterHelperDiffblueTest {
  /**
   * Test {@link ReflectionAccessFilterHelper#isJavaType(Class)} with {@code c}.
   *
   * <ul>
   *   <li>When createClass.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectionAccessFilterHelper#isJavaType(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ReflectionAccessFilterHelper.isJavaType(Class)"})
  public void testIsJavaTypeWithC_whenCreateClass_thenReturnFalse() {
    // Arrange
    Class<?> c = ReflectionHelperTestFactory.createClass();

    // Act and Assert
    assertFalse(ReflectionAccessFilterHelper.isJavaType(c));
  }

  /**
   * Test {@link ReflectionAccessFilterHelper#isJavaType(Class)} with {@code c}.
   *
   * <ul>
   *   <li>When createStringClass.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectionAccessFilterHelper#isJavaType(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ReflectionAccessFilterHelper.isJavaType(Class)"})
  public void testIsJavaTypeWithC_whenCreateStringClass_thenReturnTrue() {
    // Arrange
    Class<?> c = ReflectionHelperTestFactory.createStringClass();

    // Act and Assert
    assertTrue(ReflectionAccessFilterHelper.isJavaType(c));
  }

  /**
   * Test {@link ReflectionAccessFilterHelper#isAndroidType(Class)} with {@code c}.
   *
   * <ul>
   *   <li>When createClass.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectionAccessFilterHelper#isAndroidType(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ReflectionAccessFilterHelper.isAndroidType(Class)"})
  public void testIsAndroidTypeWithC_whenCreateClass_thenReturnFalse() {
    // Arrange
    Class<?> c = ReflectionHelperTestFactory.createClass();

    // Act and Assert
    assertFalse(ReflectionAccessFilterHelper.isAndroidType(c));
  }

  /**
   * Test {@link ReflectionAccessFilterHelper#isAndroidType(Class)} with {@code c}.
   *
   * <ul>
   *   <li>When createStringClass.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectionAccessFilterHelper#isAndroidType(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ReflectionAccessFilterHelper.isAndroidType(Class)"})
  public void testIsAndroidTypeWithC_whenCreateStringClass_thenReturnTrue() {
    // Arrange
    Class<?> c = ReflectionHelperTestFactory.createStringClass();

    // Act and Assert
    assertTrue(ReflectionAccessFilterHelper.isAndroidType(c));
  }

  /**
   * Test {@link ReflectionAccessFilterHelper#isAnyPlatformType(Class)}.
   *
   * <ul>
   *   <li>When createClass.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectionAccessFilterHelper#isAnyPlatformType(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ReflectionAccessFilterHelper.isAnyPlatformType(Class)"})
  public void testIsAnyPlatformType_whenCreateClass_thenReturnFalse() {
    // Arrange
    Class<?> c = ReflectionHelperTestFactory.createClass();

    // Act and Assert
    assertFalse(ReflectionAccessFilterHelper.isAnyPlatformType(c));
  }

  /**
   * Test {@link ReflectionAccessFilterHelper#isAnyPlatformType(Class)}.
   *
   * <ul>
   *   <li>When createStringClass.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectionAccessFilterHelper#isAnyPlatformType(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ReflectionAccessFilterHelper.isAnyPlatformType(Class)"})
  public void testIsAnyPlatformType_whenCreateStringClass_thenReturnTrue() {
    // Arrange
    Class<?> c = ReflectionHelperTestFactory.createStringClass();

    // Act and Assert
    assertTrue(ReflectionAccessFilterHelper.isAnyPlatformType(c));
  }

  /**
   * Test {@link ReflectionAccessFilterHelper#getFilterResult(List, Class)}.
   *
   * <ul>
   *   <li>Given {@link ReflectionAccessFilter#BLOCK_ALL_ANDROID}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectionAccessFilterHelper#getFilterResult(List, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ReflectionAccessFilter.FilterResult ReflectionAccessFilterHelper.getFilterResult(List, Class)"
  })
  public void testGetFilterResult_givenBlock_all_android() {
    // Arrange
    ReflectionAccessFilter reflectionAccessFilter = mock(ReflectionAccessFilter.class);
    when(reflectionAccessFilter.check(Mockito.<Class<?>>any())).thenReturn(FilterResult.ALLOW);

    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();
    reflectionFilters.add(reflectionAccessFilter);
    reflectionFilters.add(0, ReflectionAccessFilter.BLOCK_ALL_ANDROID);
    Class<?> c = ReflectionHelperTestFactory.createClass();

    // Act
    FilterResult actualFilterResult =
        ReflectionAccessFilterHelper.getFilterResult(reflectionFilters, c);

    // Assert
    verify(reflectionAccessFilter).check(isA(Class.class));
    assertEquals(FilterResult.ALLOW, actualFilterResult);
  }

  /**
   * Test {@link ReflectionAccessFilterHelper#getFilterResult(List, Class)}.
   *
   * <ul>
   *   <li>Given {@link ReflectionAccessFilter#BLOCK_ALL_ANDROID}.
   *   <li>Then return {@code BLOCK_ALL}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectionAccessFilterHelper#getFilterResult(List, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ReflectionAccessFilter.FilterResult ReflectionAccessFilterHelper.getFilterResult(List, Class)"
  })
  public void testGetFilterResult_givenBlock_all_android_thenReturnBlockAll() {
    // Arrange
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();
    reflectionFilters.add(mock(ReflectionAccessFilter.class));
    reflectionFilters.add(0, ReflectionAccessFilter.BLOCK_ALL_ANDROID);
    Class<?> c = ReflectionHelperTestFactory.createStringClass();

    // Act and Assert
    assertEquals(
        FilterResult.BLOCK_ALL, ReflectionAccessFilterHelper.getFilterResult(reflectionFilters, c));
  }

  /**
   * Test {@link ReflectionAccessFilterHelper#getFilterResult(List, Class)}.
   *
   * <ul>
   *   <li>Given {@link ReflectionAccessFilter#BLOCK_ALL_JAVA}.
   *   <li>When {@link ArrayList#ArrayList()} add zero and {@link
   *       ReflectionAccessFilter#BLOCK_ALL_JAVA}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectionAccessFilterHelper#getFilterResult(List, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ReflectionAccessFilter.FilterResult ReflectionAccessFilterHelper.getFilterResult(List, Class)"
  })
  public void testGetFilterResult_givenBlock_all_java_whenArrayListAddZeroAndBlock_all_java() {
    // Arrange
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();
    reflectionFilters.add(mock(ReflectionAccessFilter.class));
    reflectionFilters.add(0, ReflectionAccessFilter.BLOCK_ALL_JAVA);
    Class<?> c = ReflectionHelperTestFactory.createStringClass();

    // Act and Assert
    assertEquals(
        FilterResult.BLOCK_ALL, ReflectionAccessFilterHelper.getFilterResult(reflectionFilters, c));
  }

  /**
   * Test {@link ReflectionAccessFilterHelper#getFilterResult(List, Class)}.
   *
   * <ul>
   *   <li>Given {@link ReflectionAccessFilter#BLOCK_ALL_JAVA}.
   *   <li>When {@link ArrayList#ArrayList()} add zero and {@link
   *       ReflectionAccessFilter#BLOCK_ALL_JAVA}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectionAccessFilterHelper#getFilterResult(List, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ReflectionAccessFilter.FilterResult ReflectionAccessFilterHelper.getFilterResult(List, Class)"
  })
  public void testGetFilterResult_givenBlock_all_java_whenArrayListAddZeroAndBlock_all_java2() {
    // Arrange
    ReflectionAccessFilter reflectionAccessFilter = mock(ReflectionAccessFilter.class);
    when(reflectionAccessFilter.check(Mockito.<Class<?>>any())).thenReturn(FilterResult.ALLOW);

    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();
    reflectionFilters.add(reflectionAccessFilter);
    reflectionFilters.add(0, ReflectionAccessFilter.BLOCK_ALL_JAVA);
    Class<?> c = ReflectionHelperTestFactory.createClass();

    // Act
    FilterResult actualFilterResult =
        ReflectionAccessFilterHelper.getFilterResult(reflectionFilters, c);

    // Assert
    verify(reflectionAccessFilter).check(isA(Class.class));
    assertEquals(FilterResult.ALLOW, actualFilterResult);
  }

  /**
   * Test {@link ReflectionAccessFilterHelper#getFilterResult(List, Class)}.
   *
   * <ul>
   *   <li>Given {@link ReflectionAccessFilter#BLOCK_ALL_PLATFORM}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectionAccessFilterHelper#getFilterResult(List, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ReflectionAccessFilter.FilterResult ReflectionAccessFilterHelper.getFilterResult(List, Class)"
  })
  public void testGetFilterResult_givenBlock_all_platform() {
    // Arrange
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();
    reflectionFilters.add(mock(ReflectionAccessFilter.class));
    reflectionFilters.add(0, ReflectionAccessFilter.BLOCK_ALL_PLATFORM);
    Class<?> c = ReflectionHelperTestFactory.createStringClass();

    // Act and Assert
    assertEquals(
        FilterResult.BLOCK_ALL, ReflectionAccessFilterHelper.getFilterResult(reflectionFilters, c));
  }

  /**
   * Test {@link ReflectionAccessFilterHelper#getFilterResult(List, Class)}.
   *
   * <ul>
   *   <li>Given {@link ReflectionAccessFilter#BLOCK_ALL_PLATFORM}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectionAccessFilterHelper#getFilterResult(List, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ReflectionAccessFilter.FilterResult ReflectionAccessFilterHelper.getFilterResult(List, Class)"
  })
  public void testGetFilterResult_givenBlock_all_platform2() {
    // Arrange
    ReflectionAccessFilter reflectionAccessFilter = mock(ReflectionAccessFilter.class);
    when(reflectionAccessFilter.check(Mockito.<Class<?>>any())).thenReturn(FilterResult.ALLOW);

    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();
    reflectionFilters.add(reflectionAccessFilter);
    reflectionFilters.add(0, ReflectionAccessFilter.BLOCK_ALL_PLATFORM);
    Class<?> c = ReflectionHelperTestFactory.createClass();

    // Act
    FilterResult actualFilterResult =
        ReflectionAccessFilterHelper.getFilterResult(reflectionFilters, c);

    // Assert
    verify(reflectionAccessFilter).check(isA(Class.class));
    assertEquals(FilterResult.ALLOW, actualFilterResult);
  }

  /**
   * Test {@link ReflectionAccessFilterHelper#getFilterResult(List, Class)}.
   *
   * <ul>
   *   <li>Given {@link ReflectionAccessFilter#BLOCK_INACCESSIBLE_JAVA}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectionAccessFilterHelper#getFilterResult(List, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ReflectionAccessFilter.FilterResult ReflectionAccessFilterHelper.getFilterResult(List, Class)"
  })
  public void testGetFilterResult_givenBlock_inaccessible_java() {
    // Arrange
    ReflectionAccessFilter reflectionAccessFilter = mock(ReflectionAccessFilter.class);
    when(reflectionAccessFilter.check(Mockito.<Class<?>>any())).thenReturn(FilterResult.ALLOW);

    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();
    reflectionFilters.add(reflectionAccessFilter);
    reflectionFilters.add(0, ReflectionAccessFilter.BLOCK_INACCESSIBLE_JAVA);
    Class<?> c = ReflectionHelperTestFactory.createClass();

    // Act
    FilterResult actualFilterResult =
        ReflectionAccessFilterHelper.getFilterResult(reflectionFilters, c);

    // Assert
    verify(reflectionAccessFilter).check(isA(Class.class));
    assertEquals(FilterResult.ALLOW, actualFilterResult);
  }

  /**
   * Test {@link ReflectionAccessFilterHelper#getFilterResult(List, Class)}.
   *
   * <ul>
   *   <li>Given {@link ReflectionAccessFilter#BLOCK_INACCESSIBLE_JAVA}.
   *   <li>Then return {@code BLOCK_INACCESSIBLE}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectionAccessFilterHelper#getFilterResult(List, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ReflectionAccessFilter.FilterResult ReflectionAccessFilterHelper.getFilterResult(List, Class)"
  })
  public void testGetFilterResult_givenBlock_inaccessible_java_thenReturnBlockInaccessible() {
    // Arrange
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();
    reflectionFilters.add(mock(ReflectionAccessFilter.class));
    reflectionFilters.add(0, ReflectionAccessFilter.BLOCK_INACCESSIBLE_JAVA);
    Class<?> c = ReflectionHelperTestFactory.createStringClass();

    // Act and Assert
    assertEquals(
        FilterResult.BLOCK_INACCESSIBLE,
        ReflectionAccessFilterHelper.getFilterResult(reflectionFilters, c));
  }

  /**
   * Test {@link ReflectionAccessFilterHelper#getFilterResult(List, Class)}.
   *
   * <ul>
   *   <li>Given {@link ReflectionAccessFilter} {@link ReflectionAccessFilter#check(Class)} return
   *       {@code ALLOW}.
   *   <li>Then calls {@link ReflectionAccessFilter#check(Class)}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectionAccessFilterHelper#getFilterResult(List, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ReflectionAccessFilter.FilterResult ReflectionAccessFilterHelper.getFilterResult(List, Class)"
  })
  public void testGetFilterResult_givenReflectionAccessFilterCheckReturnAllow_thenCallsCheck() {
    // Arrange
    ReflectionAccessFilter reflectionAccessFilter = mock(ReflectionAccessFilter.class);
    when(reflectionAccessFilter.check(Mockito.<Class<?>>any())).thenReturn(FilterResult.ALLOW);

    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();
    reflectionFilters.add(reflectionAccessFilter);
    Class<?> c = ReflectionHelperTestFactory.createStringClass();

    // Act
    FilterResult actualFilterResult =
        ReflectionAccessFilterHelper.getFilterResult(reflectionFilters, c);

    // Assert
    verify(reflectionAccessFilter).check(isA(Class.class));
    assertEquals(FilterResult.ALLOW, actualFilterResult);
  }

  /**
   * Test {@link ReflectionAccessFilterHelper#getFilterResult(List, Class)}.
   *
   * <ul>
   *   <li>Given {@link ReflectionAccessFilter} {@link ReflectionAccessFilter#check(Class)} return
   *       {@code INDECISIVE}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectionAccessFilterHelper#getFilterResult(List, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ReflectionAccessFilter.FilterResult ReflectionAccessFilterHelper.getFilterResult(List, Class)"
  })
  public void testGetFilterResult_givenReflectionAccessFilterCheckReturnIndecisive() {
    // Arrange
    ReflectionAccessFilter reflectionAccessFilter = mock(ReflectionAccessFilter.class);
    when(reflectionAccessFilter.check(Mockito.<Class<?>>any())).thenReturn(FilterResult.INDECISIVE);

    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();
    reflectionFilters.add(reflectionAccessFilter);
    Class<?> c = ReflectionHelperTestFactory.createStringClass();

    // Act
    FilterResult actualFilterResult =
        ReflectionAccessFilterHelper.getFilterResult(reflectionFilters, c);

    // Assert
    verify(reflectionAccessFilter).check(isA(Class.class));
    assertEquals(FilterResult.ALLOW, actualFilterResult);
  }

  /**
   * Test {@link ReflectionAccessFilterHelper#getFilterResult(List, Class)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return {@code ALLOW}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectionAccessFilterHelper#getFilterResult(List, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ReflectionAccessFilter.FilterResult ReflectionAccessFilterHelper.getFilterResult(List, Class)"
  })
  public void testGetFilterResult_whenArrayList_thenReturnAllow() {
    // Arrange
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();
    Class<?> c = ReflectionHelperTestFactory.createStringClass();

    // Act and Assert
    assertEquals(
        FilterResult.ALLOW, ReflectionAccessFilterHelper.getFilterResult(reflectionFilters, c));
  }

  /**
   * Test {@link ReflectionAccessFilterHelper#canAccess(AccessibleObject, Object)}.
   *
   * <ul>
   *   <li>When createField.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectionAccessFilterHelper#canAccess(AccessibleObject, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ReflectionAccessFilterHelper.canAccess(AccessibleObject, Object)"})
  public void testCanAccess_whenCreateField_thenReturnFalse() throws NoSuchFieldException {
    // Arrange
    Field accessibleObject = ReflectionAccessFilterHelperTestFactory.createField();

    // Act and Assert
    assertFalse(
        ReflectionAccessFilterHelper.canAccess(
            accessibleObject, ReflectionAccessFilterHelperTestFactory.createObjectForAccess()));
  }

  /**
   * Test {@link ReflectionAccessFilterHelper#canAccess(AccessibleObject, Object)}.
   *
   * <ul>
   *   <li>When createPublicAccessibleObject.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectionAccessFilterHelper#canAccess(AccessibleObject, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ReflectionAccessFilterHelper.canAccess(AccessibleObject, Object)"})
  public void testCanAccess_whenCreatePublicAccessibleObject_thenReturnTrue()
      throws NoSuchFieldException {
    // Arrange
    AccessibleObject accessibleObject =
        ReflectionAccessFilterHelperTestFactory.createPublicAccessibleObject();

    // Act and Assert
    assertTrue(
        ReflectionAccessFilterHelper.canAccess(
            accessibleObject, ReflectionAccessFilterHelperTestFactory.createObjectForAccess()));
  }
}
