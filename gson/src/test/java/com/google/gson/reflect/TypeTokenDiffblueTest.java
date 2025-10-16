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
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.GsonBuilderTestFactory;
import com.google.gson.internal.reflect.ReflectionHelperTestFactory;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class TypeTokenDiffblueTest {
  /**
   * Test {@link TypeToken#TypeToken()}.
   *
   * <p>Method under test: {@link TypeToken#TypeToken()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TypeToken.isAssignableFrom(Class)"})
  public void testIsAssignableFromWithCls_givenJavaLangObject_thenReturnTrue() {
    // Arrange
    Class<Object> type = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type);
    Class<Object> cls = Object.class;

    // Act and Assert
    assertTrue(getResult.isAssignableFrom(cls));
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TypeToken.isAssignableFrom(Class)"})
  public void testIsAssignableFromWithCls_givenJavaLangObject_whenNull_thenReturnFalse() {
    // Arrange
    Class<Object> type = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type);

    // Act and Assert
    assertFalse(getResult.isAssignableFrom((Class<?>) null));
  }

  /**
   * Test {@link TypeToken#isAssignableFrom(Type)} with {@code from}.
   *
   * <ul>
   *   <li>Given {@code Object}.
   *   <li>When createType.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link TypeToken#isAssignableFrom(Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TypeToken.isAssignableFrom(Type)"})
  public void testIsAssignableFromWithFrom_givenJavaLangObject_whenCreateType_thenReturnTrue() {
    // Arrange
    Class<Object> type = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type);

    // Act and Assert
    assertTrue(getResult.isAssignableFrom(GsonBuilderTestFactory.createType()));
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TypeToken.isAssignableFrom(Type)"})
  public void testIsAssignableFromWithFrom_givenJavaLangObject_whenNull_thenReturnFalse() {
    // Arrange
    Class<Object> type = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type);

    // Act and Assert
    assertFalse(getResult.isAssignableFrom((Type) null));
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TypeToken.isAssignableFrom(Type)"})
  public void testIsAssignableFromWithFrom_whenJavaLangObject_thenReturnTrue() {
    // Arrange
    Class<Object> type = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type);
    Class<Object> from = Object.class;

    // Act and Assert
    assertTrue(getResult.isAssignableFrom((Type) from));
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TypeToken.equals(Object)", "int TypeToken.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() throws NoSuchFieldException {
    // Arrange
    Class<Object> type = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type);

    // Act and Assert
    assertNotEquals(getResult, ReflectionHelperTestFactory.createPublicField());
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeToken TypeToken.get(Class)"})
  public void testGetWithClass_whenJavaLangObject_thenReturnRawTypeIsObject() {
    // Arrange
    Class<Object> type = Object.class;

    // Act
    TypeToken<Object> actualGetResult = TypeToken.get(type);

    // Assert
    Class<Object> expectedRawType = Object.class;
    assertEquals(expectedRawType, actualGetResult.getRawType());
  }

  /**
   * Test {@link TypeToken#get(Type)} with {@code Type}.
   *
   * <ul>
   *   <li>When createType.
   *   <li>Then return RawType is {@link String}.
   * </ul>
   *
   * <p>Method under test: {@link TypeToken#get(Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeToken TypeToken.get(Type)"})
  public void testGetWithType_whenCreateType_thenReturnRawTypeIsString() {
    // Arrange
    Type type = GsonBuilderTestFactory.createType();

    // Act
    TypeToken<?> actualGetResult = TypeToken.get(type);

    // Assert
    Class<String> expectedRawType = String.class;
    assertEquals(expectedRawType, actualGetResult.getRawType());
    assertSame(type, actualGetResult.getType());
  }

  /**
   * Test {@link TypeToken#getParameterized(Type, Type[])}.
   *
   * <p>Method under test: {@link TypeToken#getParameterized(Type, Type[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeToken TypeToken.getParameterized(Type, Type[])"})
  public void testGetParameterized() {
    // Arrange
    Class<TypeToken> rawType = TypeToken.class;

    // Act
    TypeToken<?> actualParameterized =
        TypeToken.getParameterized(rawType, GsonBuilderTestFactory.createType());

    // Assert
    assertEquals(
        "com.google.gson.reflect.TypeToken<java.lang.String>",
        actualParameterized.getType().getTypeName());
    Class<TypeToken> expectedRawType = TypeToken.class;
    assertEquals(expectedRawType, actualParameterized.getRawType());
  }

  /**
   * Test {@link TypeToken#getParameterized(Type, Type[])}.
   *
   * <ul>
   *   <li>When createType.
   *   <li>Then return RawType is {@link String}.
   * </ul>
   *
   * <p>Method under test: {@link TypeToken#getParameterized(Type, Type[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeToken TypeToken.getParameterized(Type, Type[])"})
  public void testGetParameterized_whenCreateType_thenReturnRawTypeIsString() {
    // Arrange
    Type rawType = GsonBuilderTestFactory.createType();

    // Act
    TypeToken<?> actualParameterized = TypeToken.getParameterized(rawType);

    // Assert
    Class<String> expectedRawType = String.class;
    assertEquals(expectedRawType, actualParameterized.getRawType());
    assertSame(rawType, actualParameterized.getType());
  }

  /**
   * Test {@link TypeToken#getParameterized(Type, Type[])}.
   *
   * <ul>
   *   <li>When createType.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link TypeToken#getParameterized(Type, Type[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeToken TypeToken.getParameterized(Type, Type[])"})
  public void testGetParameterized_whenCreateType_thenThrowIllegalArgumentException() {
    // Arrange
    Type rawType = GsonBuilderTestFactory.createType();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> TypeToken.getParameterized(rawType, GsonBuilderTestFactory.createType()));
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeToken TypeToken.getParameterized(Type, Type[])"})
  public void testGetParameterized_whenJavaLangReflectTypeVariable() {
    // Arrange
    Class<TypeVariable> rawType = TypeVariable.class;

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> TypeToken.getParameterized(rawType, GsonBuilderTestFactory.createType()));
  }

  /**
   * Test {@link TypeToken#getArray(Type)}.
   *
   * <ul>
   *   <li>Given createType.
   *   <li>Then return RawType Name is {@code [[LString;}.
   * </ul>
   *
   * <p>Method under test: {@link TypeToken#getArray(Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeToken TypeToken.getArray(Type)"})
  public void testGetArray_givenCreateType_thenReturnRawTypeNameIsLjavaLangString() {
    // Arrange
    GenericArrayType componentType = mock(GenericArrayType.class);
    when(componentType.getGenericComponentType()).thenReturn(GsonBuilderTestFactory.createType());

    // Act
    TypeToken<?> actualArray = TypeToken.getArray(componentType);

    // Assert
    verify(componentType).getGenericComponentType();
    assertEquals("[[Ljava.lang.String;", actualArray.getRawType().getName());
    assertEquals("java.lang.String[][]", actualArray.getType().getTypeName());
  }

  /**
   * Test {@link TypeToken#getArray(Type)}.
   *
   * <ul>
   *   <li>When createType.
   *   <li>Then return RawType Name is {@code [LString;}.
   * </ul>
   *
   * <p>Method under test: {@link TypeToken#getArray(Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeToken TypeToken.getArray(Type)"})
  public void testGetArray_whenCreateType_thenReturnRawTypeNameIsLjavaLangString() {
    // Arrange and Act
    TypeToken<?> actualArray = TypeToken.getArray(GsonBuilderTestFactory.createType());

    // Assert
    assertEquals("[Ljava.lang.String;", actualArray.getRawType().getName());
    assertEquals("java.lang.String[]", actualArray.getType().getTypeName());
  }
}
