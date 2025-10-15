package com.google.gson.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.lang.reflect.Type;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.internal.util.reflection.GenericMetadataSupport;
import org.mockito.internal.util.reflection.GenericMetadataSupport.TypeVarBoundedType;

public class PrimitivesDiffblueTest {
  /**
   * Test {@link Primitives#isPrimitive(Type)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   * </ul>
   *
   * <p>Method under test: {@link Primitives#isPrimitive(Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Primitives.isPrimitive(Type)"})
  public void testIsPrimitive_whenJavaLangObject() {
    // Arrange
    Class<Object> type = Object.class;

    // Act and Assert
    assertFalse(Primitives.isPrimitive(type));
  }

  /**
   * Test {@link Primitives#isPrimitive(Type)}.
   *
   * <ul>
   *   <li>When {@link GenericMetadataSupport.TypeVarBoundedType#TypeVarBoundedType(TypeVariable)}
   *       with typeVariable is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Primitives#isPrimitive(Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Primitives.isPrimitive(Type)"})
  public void testIsPrimitive_whenTypeVarBoundedTypeWithTypeVariableIsNull() {
    // Arrange, Act and Assert
    assertFalse(Primitives.isPrimitive(new TypeVarBoundedType(null)));
  }

  /**
   * Test {@link Primitives#isWrapperType(Type)}.
   *
   * <ul>
   *   <li>When {@code Boolean}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link Primitives#isWrapperType(Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Primitives.isWrapperType(Type)"})
  public void testIsWrapperType_whenJavaLangBoolean_thenReturnTrue() {
    // Arrange
    Class<Boolean> type = Boolean.class;

    // Act and Assert
    assertTrue(Primitives.isWrapperType(type));
  }

  /**
   * Test {@link Primitives#isWrapperType(Type)}.
   *
   * <ul>
   *   <li>When {@code Byte}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link Primitives#isWrapperType(Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Primitives.isWrapperType(Type)"})
  public void testIsWrapperType_whenJavaLangByte_thenReturnTrue() {
    // Arrange
    Class<Byte> type = Byte.class;

    // Act and Assert
    assertTrue(Primitives.isWrapperType(type));
  }

  /**
   * Test {@link Primitives#isWrapperType(Type)}.
   *
   * <ul>
   *   <li>When {@code Character}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link Primitives#isWrapperType(Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Primitives.isWrapperType(Type)"})
  public void testIsWrapperType_whenJavaLangCharacter_thenReturnTrue() {
    // Arrange
    Class<Character> type = Character.class;

    // Act and Assert
    assertTrue(Primitives.isWrapperType(type));
  }

  /**
   * Test {@link Primitives#isWrapperType(Type)}.
   *
   * <ul>
   *   <li>When {@code Double}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link Primitives#isWrapperType(Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Primitives.isWrapperType(Type)"})
  public void testIsWrapperType_whenJavaLangDouble_thenReturnTrue() {
    // Arrange
    Class<Double> type = Double.class;

    // Act and Assert
    assertTrue(Primitives.isWrapperType(type));
  }

  /**
   * Test {@link Primitives#isWrapperType(Type)}.
   *
   * <ul>
   *   <li>When {@code Float}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link Primitives#isWrapperType(Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Primitives.isWrapperType(Type)"})
  public void testIsWrapperType_whenJavaLangFloat_thenReturnTrue() {
    // Arrange
    Class<Float> type = Float.class;

    // Act and Assert
    assertTrue(Primitives.isWrapperType(type));
  }

  /**
   * Test {@link Primitives#isWrapperType(Type)}.
   *
   * <ul>
   *   <li>When {@code Integer}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link Primitives#isWrapperType(Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Primitives.isWrapperType(Type)"})
  public void testIsWrapperType_whenJavaLangInteger_thenReturnTrue() {
    // Arrange
    Class<Integer> type = Integer.class;

    // Act and Assert
    assertTrue(Primitives.isWrapperType(type));
  }

  /**
   * Test {@link Primitives#isWrapperType(Type)}.
   *
   * <ul>
   *   <li>When {@code Long}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link Primitives#isWrapperType(Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Primitives.isWrapperType(Type)"})
  public void testIsWrapperType_whenJavaLangLong_thenReturnTrue() {
    // Arrange
    Class<Long> type = Long.class;

    // Act and Assert
    assertTrue(Primitives.isWrapperType(type));
  }

  /**
   * Test {@link Primitives#isWrapperType(Type)}.
   *
   * <ul>
   *   <li>When {@code Short}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link Primitives#isWrapperType(Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Primitives.isWrapperType(Type)"})
  public void testIsWrapperType_whenJavaLangShort_thenReturnTrue() {
    // Arrange
    Class<Short> type = Short.class;

    // Act and Assert
    assertTrue(Primitives.isWrapperType(type));
  }

  /**
   * Test {@link Primitives#isWrapperType(Type)}.
   *
   * <ul>
   *   <li>When {@code Void}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link Primitives#isWrapperType(Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Primitives.isWrapperType(Type)"})
  public void testIsWrapperType_whenJavaLangVoid_thenReturnTrue() {
    // Arrange
    Class<Void> type = Void.class;

    // Act and Assert
    assertTrue(Primitives.isWrapperType(type));
  }

  /**
   * Test {@link Primitives#isWrapperType(Type)}.
   *
   * <ul>
   *   <li>When {@link GenericMetadataSupport.TypeVarBoundedType#TypeVarBoundedType(TypeVariable)}
   *       with typeVariable is {@code null}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link Primitives#isWrapperType(Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Primitives.isWrapperType(Type)"})
  public void testIsWrapperType_whenTypeVarBoundedTypeWithTypeVariableIsNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(Primitives.isWrapperType(new TypeVarBoundedType(null)));
  }

  /**
   * Test {@link Primitives#wrap(Class)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return {@link Object}.
   * </ul>
   *
   * <p>Method under test: {@link Primitives#wrap(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class Primitives.wrap(Class)"})
  public void testWrap_whenJavaLangObject_thenReturnObject() {
    // Arrange
    Class<Object> type = Object.class;

    // Act
    Class<Object> actualWrapResult = Primitives.wrap(type);

    // Assert
    Class<Object> expectedWrapResult = Object.class;
    assertEquals(expectedWrapResult, actualWrapResult);
  }

  /**
   * Test {@link Primitives#wrap(Class)}.
   *
   * <ul>
   *   <li>When {@link Boolean#TYPE}.
   *   <li>Then return {@link Boolean}.
   * </ul>
   *
   * <p>Method under test: {@link Primitives#wrap(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class Primitives.wrap(Class)"})
  public void testWrap_whenType_thenReturnBoolean() {
    // Arrange and Act
    Class<Object> actualWrapResult = Primitives.wrap(Boolean.TYPE);

    // Assert
    Class<Boolean> expectedWrapResult = Boolean.class;
    assertEquals(expectedWrapResult, actualWrapResult);
  }

  /**
   * Test {@link Primitives#wrap(Class)}.
   *
   * <ul>
   *   <li>When {@link Byte#TYPE}.
   *   <li>Then return {@link Byte}.
   * </ul>
   *
   * <p>Method under test: {@link Primitives#wrap(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class Primitives.wrap(Class)"})
  public void testWrap_whenType_thenReturnByte() {
    // Arrange and Act
    Class<Object> actualWrapResult = Primitives.wrap(Byte.TYPE);

    // Assert
    Class<Byte> expectedWrapResult = Byte.class;
    assertEquals(expectedWrapResult, actualWrapResult);
  }

  /**
   * Test {@link Primitives#wrap(Class)}.
   *
   * <ul>
   *   <li>When {@link Character#TYPE}.
   *   <li>Then return {@link Character}.
   * </ul>
   *
   * <p>Method under test: {@link Primitives#wrap(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class Primitives.wrap(Class)"})
  public void testWrap_whenType_thenReturnCharacter() {
    // Arrange and Act
    Class<Object> actualWrapResult = Primitives.wrap(Character.TYPE);

    // Assert
    Class<Character> expectedWrapResult = Character.class;
    assertEquals(expectedWrapResult, actualWrapResult);
  }

  /**
   * Test {@link Primitives#wrap(Class)}.
   *
   * <ul>
   *   <li>When {@link Double#TYPE}.
   *   <li>Then return {@link Double}.
   * </ul>
   *
   * <p>Method under test: {@link Primitives#wrap(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class Primitives.wrap(Class)"})
  public void testWrap_whenType_thenReturnDouble() {
    // Arrange and Act
    Class<Object> actualWrapResult = Primitives.wrap(Double.TYPE);

    // Assert
    Class<Double> expectedWrapResult = Double.class;
    assertEquals(expectedWrapResult, actualWrapResult);
  }

  /**
   * Test {@link Primitives#wrap(Class)}.
   *
   * <ul>
   *   <li>When {@link Float#TYPE}.
   *   <li>Then return {@link Float}.
   * </ul>
   *
   * <p>Method under test: {@link Primitives#wrap(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class Primitives.wrap(Class)"})
  public void testWrap_whenType_thenReturnFloat() {
    // Arrange and Act
    Class<Object> actualWrapResult = Primitives.wrap(Float.TYPE);

    // Assert
    Class<Float> expectedWrapResult = Float.class;
    assertEquals(expectedWrapResult, actualWrapResult);
  }

  /**
   * Test {@link Primitives#wrap(Class)}.
   *
   * <ul>
   *   <li>When {@link Integer#TYPE}.
   *   <li>Then return {@link Integer}.
   * </ul>
   *
   * <p>Method under test: {@link Primitives#wrap(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class Primitives.wrap(Class)"})
  public void testWrap_whenType_thenReturnInteger() {
    // Arrange and Act
    Class<Object> actualWrapResult = Primitives.wrap(Integer.TYPE);

    // Assert
    Class<Integer> expectedWrapResult = Integer.class;
    assertEquals(expectedWrapResult, actualWrapResult);
  }

  /**
   * Test {@link Primitives#wrap(Class)}.
   *
   * <ul>
   *   <li>When {@link Long#TYPE}.
   *   <li>Then return {@link Long}.
   * </ul>
   *
   * <p>Method under test: {@link Primitives#wrap(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class Primitives.wrap(Class)"})
  public void testWrap_whenType_thenReturnLong() {
    // Arrange and Act
    Class<Object> actualWrapResult = Primitives.wrap(Long.TYPE);

    // Assert
    Class<Long> expectedWrapResult = Long.class;
    assertEquals(expectedWrapResult, actualWrapResult);
  }

  /**
   * Test {@link Primitives#wrap(Class)}.
   *
   * <ul>
   *   <li>When {@link Short#TYPE}.
   *   <li>Then return {@link Short}.
   * </ul>
   *
   * <p>Method under test: {@link Primitives#wrap(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class Primitives.wrap(Class)"})
  public void testWrap_whenType_thenReturnShort() {
    // Arrange and Act
    Class<Object> actualWrapResult = Primitives.wrap(Short.TYPE);

    // Assert
    Class<Short> expectedWrapResult = Short.class;
    assertEquals(expectedWrapResult, actualWrapResult);
  }

  /**
   * Test {@link Primitives#wrap(Class)}.
   *
   * <ul>
   *   <li>When {@link Void#TYPE}.
   *   <li>Then return {@link Void}.
   * </ul>
   *
   * <p>Method under test: {@link Primitives#wrap(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class Primitives.wrap(Class)"})
  public void testWrap_whenType_thenReturnVoid() {
    // Arrange and Act
    Class<Object> actualWrapResult = Primitives.wrap(Void.TYPE);

    // Assert
    Class<Void> expectedWrapResult = Void.class;
    assertEquals(expectedWrapResult, actualWrapResult);
  }

  /**
   * Test {@link Primitives#unwrap(Class)}.
   *
   * <ul>
   *   <li>When {@code Boolean}.
   *   <li>Then return Name is {@code boolean}.
   * </ul>
   *
   * <p>Method under test: {@link Primitives#unwrap(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class Primitives.unwrap(Class)"})
  public void testUnwrap_whenJavaLangBoolean_thenReturnNameIsBoolean() {
    // Arrange
    Class<Boolean> type = Boolean.class;

    // Act
    Class<Object> actualUnwrapResult = Primitives.unwrap(type);

    // Assert
    assertEquals("boolean", actualUnwrapResult.getName());
  }

  /**
   * Test {@link Primitives#unwrap(Class)}.
   *
   * <ul>
   *   <li>When {@code Byte}.
   *   <li>Then return Name is {@code byte}.
   * </ul>
   *
   * <p>Method under test: {@link Primitives#unwrap(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class Primitives.unwrap(Class)"})
  public void testUnwrap_whenJavaLangByte_thenReturnNameIsByte() {
    // Arrange
    Class<Byte> type = Byte.class;

    // Act
    Class<Object> actualUnwrapResult = Primitives.unwrap(type);

    // Assert
    assertEquals("byte", actualUnwrapResult.getName());
  }

  /**
   * Test {@link Primitives#unwrap(Class)}.
   *
   * <ul>
   *   <li>When {@code Character}.
   *   <li>Then return Name is {@code char}.
   * </ul>
   *
   * <p>Method under test: {@link Primitives#unwrap(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class Primitives.unwrap(Class)"})
  public void testUnwrap_whenJavaLangCharacter_thenReturnNameIsChar() {
    // Arrange
    Class<Character> type = Character.class;

    // Act
    Class<Object> actualUnwrapResult = Primitives.unwrap(type);

    // Assert
    assertEquals("char", actualUnwrapResult.getName());
  }

  /**
   * Test {@link Primitives#unwrap(Class)}.
   *
   * <ul>
   *   <li>When {@code Double}.
   *   <li>Then return Name is {@code double}.
   * </ul>
   *
   * <p>Method under test: {@link Primitives#unwrap(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class Primitives.unwrap(Class)"})
  public void testUnwrap_whenJavaLangDouble_thenReturnNameIsDouble() {
    // Arrange
    Class<Double> type = Double.class;

    // Act
    Class<Object> actualUnwrapResult = Primitives.unwrap(type);

    // Assert
    assertEquals("double", actualUnwrapResult.getName());
  }

  /**
   * Test {@link Primitives#unwrap(Class)}.
   *
   * <ul>
   *   <li>When {@code Float}.
   *   <li>Then return Name is {@code float}.
   * </ul>
   *
   * <p>Method under test: {@link Primitives#unwrap(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class Primitives.unwrap(Class)"})
  public void testUnwrap_whenJavaLangFloat_thenReturnNameIsFloat() {
    // Arrange
    Class<Float> type = Float.class;

    // Act
    Class<Object> actualUnwrapResult = Primitives.unwrap(type);

    // Assert
    assertEquals("float", actualUnwrapResult.getName());
  }

  /**
   * Test {@link Primitives#unwrap(Class)}.
   *
   * <ul>
   *   <li>When {@code Integer}.
   *   <li>Then return Name is {@code int}.
   * </ul>
   *
   * <p>Method under test: {@link Primitives#unwrap(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class Primitives.unwrap(Class)"})
  public void testUnwrap_whenJavaLangInteger_thenReturnNameIsInt() {
    // Arrange
    Class<Integer> type = Integer.class;

    // Act
    Class<Object> actualUnwrapResult = Primitives.unwrap(type);

    // Assert
    assertEquals("int", actualUnwrapResult.getName());
  }

  /**
   * Test {@link Primitives#unwrap(Class)}.
   *
   * <ul>
   *   <li>When {@code Long}.
   *   <li>Then return Name is {@code long}.
   * </ul>
   *
   * <p>Method under test: {@link Primitives#unwrap(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class Primitives.unwrap(Class)"})
  public void testUnwrap_whenJavaLangLong_thenReturnNameIsLong() {
    // Arrange
    Class<Long> type = Long.class;

    // Act
    Class<Object> actualUnwrapResult = Primitives.unwrap(type);

    // Assert
    assertEquals("long", actualUnwrapResult.getName());
  }

  /**
   * Test {@link Primitives#unwrap(Class)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return {@link Object}.
   * </ul>
   *
   * <p>Method under test: {@link Primitives#unwrap(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class Primitives.unwrap(Class)"})
  public void testUnwrap_whenJavaLangObject_thenReturnObject() {
    // Arrange
    Class<Object> type = Object.class;

    // Act
    Class<Object> actualUnwrapResult = Primitives.unwrap(type);

    // Assert
    Class<Object> expectedUnwrapResult = Object.class;
    assertEquals(expectedUnwrapResult, actualUnwrapResult);
  }

  /**
   * Test {@link Primitives#unwrap(Class)}.
   *
   * <ul>
   *   <li>When {@code Short}.
   *   <li>Then return Name is {@code short}.
   * </ul>
   *
   * <p>Method under test: {@link Primitives#unwrap(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class Primitives.unwrap(Class)"})
  public void testUnwrap_whenJavaLangShort_thenReturnNameIsShort() {
    // Arrange
    Class<Short> type = Short.class;

    // Act
    Class<Object> actualUnwrapResult = Primitives.unwrap(type);

    // Assert
    assertEquals("short", actualUnwrapResult.getName());
  }

  /**
   * Test {@link Primitives#unwrap(Class)}.
   *
   * <ul>
   *   <li>When {@code Void}.
   *   <li>Then return Name is {@code void}.
   * </ul>
   *
   * <p>Method under test: {@link Primitives#unwrap(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class Primitives.unwrap(Class)"})
  public void testUnwrap_whenJavaLangVoid_thenReturnNameIsVoid() {
    // Arrange
    Class<Void> type = Void.class;

    // Act
    Class<Object> actualUnwrapResult = Primitives.unwrap(type);

    // Assert
    assertEquals("void", actualUnwrapResult.getName());
  }
}
