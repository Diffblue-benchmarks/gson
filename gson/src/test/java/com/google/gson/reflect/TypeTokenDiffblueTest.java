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

import com.diffblue.cover.annotations.MaintainedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.internal.util.reflection.GenericMetadataSupport.TypeVarBoundedType;

public class TypeTokenDiffblueTest {
  /**
   * Test {@link TypeToken#TypeToken()}.
   *
   * <p>Method under test: {@link TypeToken#TypeToken()}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void TypeToken.<init>()"})
  public void testNewTypeToken() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> new TypeToken<>());
  }

  /**
   * Test {@link TypeToken#isAssignableFrom(Class)} with {@code cls}.
   *
   * <ul>
   *   <li>Given {@code Object}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link TypeToken#isAssignableFrom(Class)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"boolean TypeToken.isAssignableFrom(Class)"})
  public void testIsAssignableFromWithCls_givenJavaLangObject_thenReturnTrue() {
    // Arrange
    Class<Object> type = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type);
    Class<Object> cls = Object.class;

    // Act and Assert
    assertTrue(getResult.isAssignableFrom(cls));
    Class<Object> expectedRawType = Object.class;
    assertEquals(expectedRawType, getResult.getRawType());
    assertSame(cls, getResult.getType());
  }

  /**
   * Test {@link TypeToken#isAssignableFrom(Class)} with {@code cls}.
   *
   * <ul>
   *   <li>Given {@code Object}.
   *   <li>When {@code null}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link TypeToken#isAssignableFrom(Class)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"boolean TypeToken.isAssignableFrom(Class)"})
  public void testIsAssignableFromWithCls_givenJavaLangObject_whenNull_thenReturnFalse() {
    // Arrange
    Class<Object> type = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type);

    // Act and Assert
    assertFalse(getResult.isAssignableFrom((Class<?>) null));
    Class<Object> expectedRawType = Object.class;
    assertEquals(expectedRawType, getResult.getRawType());
    assertSame(type, getResult.getType());
  }

  /**
   * Test {@link TypeToken#isAssignableFrom(Type)} with {@code from}.
   *
   * <ul>
   *   <li>Given {@code Object}.
   *   <li>When {@code null}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link TypeToken#isAssignableFrom(Type)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"boolean TypeToken.isAssignableFrom(Type)"})
  public void testIsAssignableFromWithFrom_givenJavaLangObject_whenNull_thenReturnFalse() {
    // Arrange
    Class<Object> type = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type);

    // Act and Assert
    assertFalse(getResult.isAssignableFrom((Type) null));
    Class<Object> expectedRawType = Object.class;
    assertEquals(expectedRawType, getResult.getRawType());
    assertSame(type, getResult.getType());
  }

  /**
   * Test {@link TypeToken#isAssignableFrom(Type)} with {@code from}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link TypeToken#isAssignableFrom(Type)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"boolean TypeToken.isAssignableFrom(Type)"})
  public void testIsAssignableFromWithFrom_whenJavaLangObject_thenReturnTrue() {
    // Arrange
    Class<Object> type = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type);
    Class<Object> from = Object.class;

    // Act and Assert
    assertTrue(getResult.isAssignableFrom((Type) from));
    Class<Object> expectedRawType = Object.class;
    assertEquals(expectedRawType, getResult.getRawType());
    assertSame(from, getResult.getType());
  }

  /**
   * Test {@link TypeToken#isAssignableFrom(TypeToken)} with {@code token}.
   *
   * <ul>
   *   <li>Given {@code Object}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link TypeToken#isAssignableFrom(TypeToken)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"boolean TypeToken.isAssignableFrom(TypeToken)"})
  public void testIsAssignableFromWithToken_givenJavaLangObject_thenReturnTrue() {
    // Arrange
    Class<Object> type = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type);
    Class<Object> type2 = Object.class;
    TypeToken<?> token = TypeToken.get(type2);

    // Act and Assert
    assertTrue(getResult.isAssignableFrom(token));
  }

  /**
   * Test {@link TypeToken#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link TypeToken#equals(Object)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"boolean TypeToken.equals(Object)", "int TypeToken.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Class<Object> type = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type);

    // Act and Assert
    assertNotEquals(getResult, "42");
  }

  /**
   * Test {@link TypeToken#get(Class)} with {@code Class}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return RawType is {@link Object}.
   * </ul>
   *
   * <p>Method under test: {@link TypeToken#get(Class)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"TypeToken TypeToken.get(Class)"})
  public void testGetWithClass_whenJavaLangObject_thenReturnRawTypeIsObject() {
    // Arrange
    Class<Object> type = Object.class;

    // Act
    TypeToken<Object> actualGetResult = TypeToken.get(type);

    // Assert
    Class<Object> expectedRawType = Object.class;
    assertEquals(expectedRawType, actualGetResult.getRawType());
    assertSame(type, actualGetResult.getType());
  }

  /**
   * Test {@link TypeToken#getParameterized(Type, Type[])}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link TypeToken#getParameterized(Type, Type[])}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"TypeToken TypeToken.getParameterized(Type, Type[])"})
  public void testGetParameterized_whenJavaLangObject_thenThrowIllegalArgumentException() {
    // Arrange
    Class<Object> rawType = Object.class;

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> TypeToken.getParameterized(rawType, new TypeVarBoundedType(null)));
  }

  /**
   * Test {@link TypeToken#getParameterized(Type, Type[])}.
   *
   * <ul>
   *   <li>When {@code TypeVariable}.
   * </ul>
   *
   * <p>Method under test: {@link TypeToken#getParameterized(Type, Type[])}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"TypeToken TypeToken.getParameterized(Type, Type[])"})
  public void testGetParameterized_whenJavaLangReflectTypeVariable() {
    // Arrange
    Class<TypeVariable> rawType = TypeVariable.class;
    Class<Object> forNameResult = Object.class;

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> TypeToken.getParameterized(rawType, forNameResult));
  }

  /**
   * Test {@link TypeToken#getArray(Type)}.
   *
   * <ul>
   *   <li>Given {@code Object}.
   *   <li>Then return RawType Name is {@code [[LObject;}.
   * </ul>
   *
   * <p>Method under test: {@link TypeToken#getArray(Type)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"TypeToken TypeToken.getArray(Type)"})
  public void testGetArray_givenJavaLangObject_thenReturnRawTypeNameIsLjavaLangObject() {
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

  /**
   * Test {@link TypeToken#getArray(Type)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return RawType Name is {@code [LObject;}.
   * </ul>
   *
   * <p>Method under test: {@link TypeToken#getArray(Type)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"TypeToken TypeToken.getArray(Type)"})
  public void testGetArray_whenJavaLangObject_thenReturnRawTypeNameIsLjavaLangObject() {
    // Arrange
    Class<Object> componentType = Object.class;

    // Act
    TypeToken<?> actualArray = TypeToken.getArray(componentType);

    // Assert
    assertEquals("[Ljava.lang.Object;", actualArray.getRawType().getName());
    assertEquals("java.lang.Object[]", actualArray.getType().getTypeName());
  }
}
