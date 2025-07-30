package com.google.gson.internal;

import static org.junit.Assert.assertEquals;
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
    Class<Object> c = Object.class;

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
    Class<Object> c = Object.class;

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
  public void testGetFilterResult_whenArrayList() {
    // Arrange
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();
    Class<Object> c = Object.class;

    // Act and Assert
    assertEquals(
        FilterResult.ALLOW, ReflectionAccessFilterHelper.getFilterResult(reflectionFilters, c));
  }
}
