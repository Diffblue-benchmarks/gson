package com.google.gson.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.gson.ReflectionAccessFilter;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.mockito.Mockito;

public class ReflectionAccessFilterHelperDiffblueTest {
  /** Method under test: {@link ReflectionAccessFilterHelper#isJavaType(Class)} */
  @Test
  public void testIsJavaType() {
    // Arrange
    Class<Object> c = Object.class;

    // Act and Assert
    assertTrue(ReflectionAccessFilterHelper.isJavaType(c));
  }

  /** Method under test: {@link ReflectionAccessFilterHelper#isAndroidType(Class)} */
  @Test
  public void testIsAndroidType() {
    // Arrange
    Class<Object> c = Object.class;

    // Act and Assert
    assertTrue(ReflectionAccessFilterHelper.isAndroidType(c));
  }

  /** Method under test: {@link ReflectionAccessFilterHelper#isAnyPlatformType(Class)} */
  @Test
  public void testIsAnyPlatformType() {
    // Arrange
    Class<Object> c = Object.class;

    // Act and Assert
    assertTrue(ReflectionAccessFilterHelper.isAnyPlatformType(c));
  }

  /** Method under test: {@link ReflectionAccessFilterHelper#getFilterResult(List, Class)} */
  @Test
  public void testGetFilterResult() {
    // Arrange
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();
    Class<Object> c = Object.class;

    // Act and Assert
    assertEquals(
        ReflectionAccessFilter.FilterResult.ALLOW,
        ReflectionAccessFilterHelper.getFilterResult(reflectionFilters, c));
  }

  /** Method under test: {@link ReflectionAccessFilterHelper#getFilterResult(List, Class)} */
  @Test
  public void testGetFilterResult2() {
    // Arrange
    ReflectionAccessFilter reflectionAccessFilter = mock(ReflectionAccessFilter.class);
    when(reflectionAccessFilter.check(Mockito.<Class<Object>>any()))
        .thenReturn(ReflectionAccessFilter.FilterResult.ALLOW);

    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();
    reflectionFilters.add(reflectionAccessFilter);
    Class<Object> c = Object.class;

    // Act
    ReflectionAccessFilter.FilterResult actualFilterResult =
        ReflectionAccessFilterHelper.getFilterResult(reflectionFilters, c);

    // Assert
    verify(reflectionAccessFilter).check(isA(Class.class));
    assertEquals(ReflectionAccessFilter.FilterResult.ALLOW, actualFilterResult);
  }

  /** Method under test: {@link ReflectionAccessFilterHelper#getFilterResult(List, Class)} */
  @Test
  public void testGetFilterResult3() {
    // Arrange
    ReflectionAccessFilter reflectionAccessFilter = mock(ReflectionAccessFilter.class);
    when(reflectionAccessFilter.check(Mockito.<Class<Object>>any()))
        .thenReturn(ReflectionAccessFilter.FilterResult.INDECISIVE);

    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();
    reflectionFilters.add(reflectionAccessFilter);
    Class<Object> c = Object.class;

    // Act
    ReflectionAccessFilter.FilterResult actualFilterResult =
        ReflectionAccessFilterHelper.getFilterResult(reflectionFilters, c);

    // Assert
    verify(reflectionAccessFilter).check(isA(Class.class));
    assertEquals(ReflectionAccessFilter.FilterResult.ALLOW, actualFilterResult);
  }
}
