package com.google.gson.reflect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import org.junit.Test;
import org.mockito.internal.util.reflection.GenericMetadataSupport;

public class TypeTokenDiffblueTest {
  /** Method under test: {@link TypeToken#isAssignableFrom(TypeToken)} */
  @Test
  public void testIsAssignableFrom() {
    // Arrange
    Class<Object> type = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type);
    Class<Object> type2 = Object.class;
    TypeToken<?> token = TypeToken.get(type2);

    // Act and Assert
    assertTrue(getResult.isAssignableFrom(token));
  }

  /** Method under test: {@link TypeToken#isAssignableFrom(Class)} */
  @Test
  public void testIsAssignableFrom2() {
    // Arrange
    Class<Object> type = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type);
    Class<Object> cls = Object.class;

    // Act and Assert
    assertTrue(getResult.isAssignableFrom(cls));
    Class<Object> expectedRawType = Object.class;
    Class<? super Object> rawType = getResult.getRawType();
    assertEquals(expectedRawType, rawType);
    assertSame(cls, rawType);
    assertSame(cls, getResult.getType());
  }

  /** Method under test: {@link TypeToken#isAssignableFrom(Class)} */
  @Test
  public void testIsAssignableFrom3() {
    // Arrange
    Class<Object> type = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type);

    // Act and Assert
    assertFalse(getResult.isAssignableFrom((Class<?>) null));
    Class<Object> expectedRawType = Object.class;
    Class<? super Object> rawType = getResult.getRawType();
    assertEquals(expectedRawType, rawType);
    assertSame(type, rawType);
    assertSame(type, getResult.getType());
  }

  /** Method under test: {@link TypeToken#isAssignableFrom(Type)} */
  @Test
  public void testIsAssignableFrom4() {
    // Arrange
    Class<Object> type = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type);
    Class<Object> from = Object.class;

    // Act and Assert
    assertTrue(getResult.isAssignableFrom((Type) from));
    Class<Object> expectedRawType = Object.class;
    Class<? super Object> rawType = getResult.getRawType();
    assertEquals(expectedRawType, rawType);
    assertSame(from, rawType);
    assertSame(from, getResult.getType());
  }

  /** Method under test: {@link TypeToken#isAssignableFrom(Type)} */
  @Test
  public void testIsAssignableFrom5() {
    // Arrange
    Class<Object> type = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type);

    // Act and Assert
    assertFalse(getResult.isAssignableFrom((Type) null));
    Class<Object> expectedRawType = Object.class;
    Class<? super Object> rawType = getResult.getRawType();
    assertEquals(expectedRawType, rawType);
    assertSame(type, rawType);
    assertSame(type, getResult.getType());
  }

  /** Method under test: {@link TypeToken#get(Class)} */
  @Test
  public void testGet() {
    // Arrange
    Class<Object> type = Object.class;

    // Act
    TypeToken<Object> actualGetResult = TypeToken.get(type);

    // Assert
    Class<Object> expectedRawType = Object.class;
    Class<? super Object> rawType = actualGetResult.getRawType();
    assertEquals(expectedRawType, rawType);
    assertSame(type, rawType);
    assertSame(type, actualGetResult.getType());
  }

  /** Method under test: {@link TypeToken#getParameterized(Type, Type[])} */
  @Test
  public void testGetParameterized() {
    // Arrange
    Class<Object> rawType = Object.class;

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            TypeToken.getParameterized(
                rawType, new GenericMetadataSupport.TypeVarBoundedType(null)));
  }

  /** Method under test: {@link TypeToken#getParameterized(Type, Type[])} */
  @Test
  public void testGetParameterized2() {
    // Arrange
    Class<TypeVariable> rawType = TypeVariable.class;
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> TypeToken.getParameterized(rawType, forNameResult));
  }

  /** Method under test: {@link TypeToken#getArray(Type)} */
  @Test
  public void testGetArray() {
    // Arrange
    Class<Object> componentType = Object.class;

    // Act
    TypeToken<?> actualArray = TypeToken.getArray(componentType);

    // Assert
    assertEquals("[Ljava.lang.Object;", actualArray.getRawType().getName());
    assertEquals("java.lang.Object[]", actualArray.getType().getTypeName());
  }

  /** Method under test: {@link TypeToken#getArray(Type)} */
  @Test
  public void testGetArray2() {
    // Arrange
    GenericArrayType componentType = mock(GenericArrayType.class);
    Class<Object> forNameResult = Object.class;
    when(componentType.getGenericComponentType()).thenReturn(forNameResult);

    // Act
    TypeToken<?> actualArray = TypeToken.getArray(componentType);

    // Assert
    verify(componentType).getGenericComponentType();
    assertEquals("[[Ljava.lang.Object;", actualArray.getRawType().getName());
    assertEquals("java.lang.Object[][]", actualArray.getType().getTypeName());
  }

  /** Method under test: {@link TypeToken#equals(Object)} */
  @Test
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Class<Object> type = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type);

    // Act and Assert
    assertNotEquals(getResult, "42");
  }

  /** Method under test: {@link TypeToken#TypeToken()} */
  @Test
  public void testNewTypeToken() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> new TypeToken<>());
  }
}
