package com.google.gson.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.ReflectionAccessFilter;
import com.google.gson.ReflectionAccessFilter.FilterResult;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ReflectionAccessFilterHelperDiffblueTest {
  /**
   * Test {@link ReflectionAccessFilterHelper#isJavaType(Class)} with {@code c}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectionAccessFilterHelper#isJavaType(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ReflectionAccessFilterHelper.isJavaType(Class)"})
  public void testIsJavaTypeWithC_whenJavaLangObject_thenReturnTrue() {
    // Arrange
    Class<Object> c = Object.class;

    // Act and Assert
    assertTrue(ReflectionAccessFilterHelper.isJavaType(c));
  }

  /**
   * Test {@link ReflectionAccessFilterHelper#isAndroidType(Class)} with {@code c}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectionAccessFilterHelper#isAndroidType(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ReflectionAccessFilterHelper.isAndroidType(Class)"})
  public void testIsAndroidTypeWithC_whenJavaLangObject_thenReturnTrue() {
    // Arrange
    Class<Object> c = Object.class;

    // Act and Assert
    assertTrue(ReflectionAccessFilterHelper.isAndroidType(c));
  }

  /**
   * Test {@link ReflectionAccessFilterHelper#isAnyPlatformType(Class)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectionAccessFilterHelper#isAnyPlatformType(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ReflectionAccessFilterHelper.isAnyPlatformType(Class)"})
  public void testIsAnyPlatformType_whenJavaLangObject_thenReturnTrue() {
    // Arrange
    Class<Object> c = Object.class;

    // Act and Assert
    assertTrue(ReflectionAccessFilterHelper.isAnyPlatformType(c));
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
  @MethodsUnderTest({"FilterResult ReflectionAccessFilterHelper.getFilterResult(List, Class)"})
  public void testGetFilterResult_givenBlock_all_android_thenReturnBlockAll() {
    // Arrange
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();
    reflectionFilters.add(ReflectionAccessFilter.BLOCK_ALL_ANDROID);
    Class<Object> c = Object.class;

    // Act and Assert
    assertEquals(
        FilterResult.BLOCK_ALL, ReflectionAccessFilterHelper.getFilterResult(reflectionFilters, c));
  }

  /**
   * Test {@link ReflectionAccessFilterHelper#getFilterResult(List, Class)}.
   *
   * <ul>
   *   <li>Given {@link ReflectionAccessFilter#BLOCK_ALL_JAVA}.
   *   <li>When {@link ArrayList#ArrayList()} add {@link ReflectionAccessFilter#BLOCK_ALL_JAVA}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectionAccessFilterHelper#getFilterResult(List, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"FilterResult ReflectionAccessFilterHelper.getFilterResult(List, Class)"})
  public void testGetFilterResult_givenBlock_all_java_whenArrayListAddBlock_all_java() {
    // Arrange
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();
    reflectionFilters.add(ReflectionAccessFilter.BLOCK_ALL_JAVA);
    Class<Object> c = Object.class;

    // Act and Assert
    assertEquals(
        FilterResult.BLOCK_ALL, ReflectionAccessFilterHelper.getFilterResult(reflectionFilters, c));
  }

  /**
   * Test {@link ReflectionAccessFilterHelper#getFilterResult(List, Class)}.
   *
   * <ul>
   *   <li>Given {@link ReflectionAccessFilter#BLOCK_ALL_PLATFORM}.
   *   <li>When {@link ArrayList#ArrayList()} add {@link ReflectionAccessFilter#BLOCK_ALL_PLATFORM}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectionAccessFilterHelper#getFilterResult(List, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"FilterResult ReflectionAccessFilterHelper.getFilterResult(List, Class)"})
  public void testGetFilterResult_givenBlock_all_platform_whenArrayListAddBlock_all_platform() {
    // Arrange
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();
    reflectionFilters.add(ReflectionAccessFilter.BLOCK_ALL_PLATFORM);
    Class<Object> c = Object.class;

    // Act and Assert
    assertEquals(
        FilterResult.BLOCK_ALL, ReflectionAccessFilterHelper.getFilterResult(reflectionFilters, c));
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
  @MethodsUnderTest({"FilterResult ReflectionAccessFilterHelper.getFilterResult(List, Class)"})
  public void testGetFilterResult_givenBlock_inaccessible_java_thenReturnBlockInaccessible() {
    // Arrange
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();
    reflectionFilters.add(ReflectionAccessFilter.BLOCK_INACCESSIBLE_JAVA);
    Class<Object> c = Object.class;

    // Act and Assert
    assertEquals(
        FilterResult.BLOCK_INACCESSIBLE,
        ReflectionAccessFilterHelper.getFilterResult(reflectionFilters, c));
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
  @MethodsUnderTest({"FilterResult ReflectionAccessFilterHelper.getFilterResult(List, Class)"})
  public void testGetFilterResult_whenArrayList_thenReturnAllow() {
    // Arrange
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();
    Class<Object> c = Object.class;

    // Act and Assert
    assertEquals(
        FilterResult.ALLOW, ReflectionAccessFilterHelper.getFilterResult(reflectionFilters, c));
  }
}
