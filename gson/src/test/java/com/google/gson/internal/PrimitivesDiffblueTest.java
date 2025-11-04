package com.google.gson.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import com.diffblue.cover.annotations.MaintainedByDiffblue;
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
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link Primitives#isPrimitive(Type)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"boolean Primitives.isPrimitive(Type)"})
  public void testIsPrimitive_whenJavaLangObject_thenReturnFalse() {
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
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link Primitives#isPrimitive(Type)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"boolean Primitives.isPrimitive(Type)"})
  public void testIsPrimitive_whenTypeVarBoundedTypeWithTypeVariableIsNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(Primitives.isPrimitive(new TypeVarBoundedType(null)));
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
  @Category(MaintainedByDiffblue.class)
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
  @Category(MaintainedByDiffblue.class)
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
