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
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.internal.util.reflection.GenericMetadataSupport;
import org.mockito.internal.util.reflection.GenericMetadataSupport.TypeVarBoundedType;

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
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link TypeToken#toString()}
   *   <li>{@link TypeToken#getRawType()}
   *   <li>{@link TypeToken#getType()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Class TypeToken.getRawType()",
    "Type TypeToken.getType()",
    "String TypeToken.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    Class<Object> type = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type);

    // Act
    String actualToStringResult = getResult.toString();
    Class<? super Object> actualRawType = getResult.getRawType();
    Type actualType = getResult.getType();

    // Assert
    assertEquals("java.lang.Object", actualToStringResult);
    Class<Object> expectedRawType = Object.class;
    assertEquals(expectedRawType, actualRawType);
    assertSame(type, actualRawType);
    assertSame(type, actualType);
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
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
    Class<Object> expectedRawType = Object.class;
    assertEquals(expectedRawType, getResult.getRawType());
    assertSame(from, getResult.getType());
  }

  /**
   * Test {@link TypeToken#isAssignableFrom(TypeToken)} with {@code token}.
   *
   * <ul>
   *   <li>Given {@code Object}.
   *   <li>When {@link Object}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link TypeToken#isAssignableFrom(TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TypeToken.isAssignableFrom(TypeToken)"})
  public void testIsAssignableFromWithToken_givenJavaLangObject_whenObject_thenReturnTrue() {
    // Arrange
    Class<Object> type = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type);
    Class<Object> type2 = Object.class;
    TypeToken<?> token = TypeToken.get(type2);

    // Act and Assert
    assertTrue(getResult.isAssignableFrom(token));
  }

  /**
   * Test {@link TypeToken#isAssignableFrom(TypeToken)} with {@code token}.
   *
   * <ul>
   *   <li>When Array is {@link Object}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link TypeToken#isAssignableFrom(TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TypeToken.isAssignableFrom(TypeToken)"})
  public void testIsAssignableFromWithToken_whenArrayIsObject_thenReturnTrue() {
    // Arrange
    Class<Object> type = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type);
    Class<Object> componentType = Object.class;
    TypeToken<?> token = TypeToken.getArray(componentType);

    // Act and Assert
    assertTrue(getResult.isAssignableFrom(token));
  }

  /**
   * Test {@link TypeToken#equals(Object)}, and {@link TypeToken#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link TypeToken#equals(Object)}
   *   <li>{@link TypeToken#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TypeToken.equals(Object)", "int TypeToken.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Class<Object> type = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type);
    Class<Object> type2 = Object.class;
    TypeToken<Object> getResult2 = TypeToken.get(type2);

    // Act and Assert
    assertEquals(getResult, getResult2);
    int expectedHashCodeResult = getResult.hashCode();
    assertEquals(expectedHashCodeResult, getResult2.hashCode());
  }

  /**
   * Test {@link TypeToken#equals(Object)}, and {@link TypeToken#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link TypeToken#equals(Object)}
   *   <li>{@link TypeToken#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TypeToken.equals(Object)", "int TypeToken.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Class<Object> type = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type);

    // Act and Assert
    assertEquals(getResult, getResult);
    int expectedHashCodeResult = getResult.hashCode();
    assertEquals(expectedHashCodeResult, getResult.hashCode());
  }

  /**
   * Test {@link TypeToken#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link TypeToken#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TypeToken.equals(Object)", "int TypeToken.hashCode()"})
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Class<Object> type = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type);

    // Act and Assert
    assertNotEquals(getResult, null);
  }

  /**
   * Test {@link TypeToken#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link TypeToken#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TypeToken.equals(Object)", "int TypeToken.hashCode()"})
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Class<Object> type = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type);

    // Act and Assert
    assertNotEquals(getResult, "Different type to TypeToken");
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
    assertSame(type, actualGetResult.getType());
  }

  /**
   * Test {@link TypeToken#get(Type)} with {@code Type}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return RawType is {@link Object}.
   * </ul>
   *
   * <p>Method under test: {@link TypeToken#get(Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeToken TypeToken.get(Type)"})
  public void testGetWithType_whenJavaLangObject_thenReturnRawTypeIsObject() {
    // Arrange
    Class<Object> type = Object.class;

    // Act
    TypeToken<?> actualGetResult = TypeToken.get(type);

    // Assert
    Class<Object> expectedRawType = Object.class;
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
    Class<Object> forNameResult = Object.class;

    // Act
    TypeToken<?> actualParameterized = TypeToken.getParameterized(rawType, forNameResult);

    // Assert
    assertEquals(
        "com.google.gson.reflect.TypeToken<java.lang.Object>",
        actualParameterized.getType().getTypeName());
    Class<TypeToken> expectedRawType = TypeToken.class;
    assertEquals(expectedRawType, actualParameterized.getRawType());
  }

  /**
   * Test {@link TypeToken#getParameterized(Type, Type[])}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return RawType is {@link Object}.
   * </ul>
   *
   * <p>Method under test: {@link TypeToken#getParameterized(Type, Type[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeToken TypeToken.getParameterized(Type, Type[])"})
  public void testGetParameterized_whenJavaLangObject_thenReturnRawTypeIsObject() {
    // Arrange
    Class<Object> rawType = Object.class;

    // Act
    TypeToken<?> actualParameterized = TypeToken.getParameterized(rawType);

    // Assert
    Class<Object> expectedRawType = Object.class;
    assertEquals(expectedRawType, actualParameterized.getRawType());
    assertSame(rawType, actualParameterized.getType());
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
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
