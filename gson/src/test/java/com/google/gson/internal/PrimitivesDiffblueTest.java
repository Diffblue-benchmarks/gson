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
}
