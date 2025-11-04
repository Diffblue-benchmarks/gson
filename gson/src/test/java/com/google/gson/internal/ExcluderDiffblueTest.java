package com.google.gson.internal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import org.mockito.Mockito;

public class ExcluderDiffblueTest {
  /** Method under test: {@link Excluder#clone()} */
  @Test
  public void testClone() {
    // Arrange, Act and Assert
    assertFalse(Excluder.DEFAULT.clone().excludeClass(null, true));
  }

  /** Method under test: {@link Excluder#withModifiers(int[])} */
  @Test
  public void testWithModifiers() {
    // Arrange, Act and Assert
    assertFalse(Excluder.DEFAULT.withModifiers(1, 0, 1, 0).excludeClass(null, true));
  }

  /** Method under test: {@link Excluder#excludeFieldsWithoutExposeAnnotation()} */
  @Test
  public void testExcludeFieldsWithoutExposeAnnotation() {
    // Arrange, Act and Assert
    assertFalse(Excluder.DEFAULT.excludeFieldsWithoutExposeAnnotation().excludeClass(null, true));
  }

  /**
   * Method under test: {@link Excluder#withExclusionStrategy(ExclusionStrategy, boolean, boolean)}
   */
  @Test
  public void testWithExclusionStrategy() {
    // Arrange, Act and Assert
    assertFalse(
        Excluder.DEFAULT
            .withExclusionStrategy(mock(ExclusionStrategy.class), true, true)
            .excludeClass(null, true));
    assertFalse(
        Excluder.DEFAULT
            .withExclusionStrategy(mock(ExclusionStrategy.class), false, false)
            .excludeClass(null, true));
  }

  /** Method under test: {@link Excluder#create(Gson, TypeToken)} */
  @Test
  public void testCreate() {
    // Arrange
    Gson gson = new Gson();
    TypeToken<Object> type = mock(TypeToken.class);
    Class<Object> forNameResult = Object.class;
    Mockito.<Class<? super Object>>when(type.getRawType()).thenReturn(forNameResult);

    // Act
    TypeAdapter<Object> actualCreateResult = Excluder.DEFAULT.create(gson, type);

    // Assert
    verify(type).getRawType();
    assertNull(actualCreateResult);
  }

  /** Method under test: {@link Excluder#create(Gson, TypeToken)} */
  @Test
  public void testCreate2() {
    // Arrange
    Gson gson = new Gson();
    TypeToken<Object> type = mock(TypeToken.class);
    Class<Object> forNameResult = Object.class;
    Mockito.<Class<? super Object>>when(type.getRawType()).thenReturn(forNameResult);

    // Act
    TypeAdapter<Object> actualCreateResult = Excluder.DEFAULT.create(gson, type);

    // Assert
    verify(type).getRawType();
    assertNull(actualCreateResult);
  }

  /** Method under test: {@link Excluder#excludeClass(Class, boolean)} */
  @Test
  public void testExcludeClass() {
    // Arrange
    Class<Object> clazz = Object.class;

    // Act and Assert
    assertFalse(Excluder.DEFAULT.excludeClass(clazz, true));
  }

  /** Method under test: {@link Excluder#excludeClass(Class, boolean)} */
  @Test
  public void testExcludeClass2() {
    // Arrange
    Class<Object> clazz = Object.class;

    // Act and Assert
    assertFalse(Excluder.DEFAULT.excludeClass(clazz, false));
  }

  /** Method under test: {@link Excluder#excludeClass(Class, boolean)} */
  @Test
  public void testExcludeClass3() {
    // Arrange
    Class<Object> clazz = Object.class;

    // Act and Assert
    assertFalse(Excluder.DEFAULT.excludeClass(clazz, false));
  }

  /** Method under test: default or parameterless constructor of {@link Excluder} */
  @Test
  public void testNewExcluder() {
    // Arrange, Act and Assert
    assertFalse((new Excluder()).excludeClass(null, true));
  }
}
