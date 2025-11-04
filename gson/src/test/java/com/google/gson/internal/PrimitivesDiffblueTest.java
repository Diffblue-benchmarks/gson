package com.google.gson.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.lang.reflect.Type;
import org.junit.Test;
import org.mockito.internal.util.reflection.GenericMetadataSupport;

public class PrimitivesDiffblueTest {
  /** Method under test: {@link Primitives#isPrimitive(Type)} */
  @Test
  public void testIsPrimitive() {
    // Arrange, Act and Assert
    assertFalse(Primitives.isPrimitive(new GenericMetadataSupport.TypeVarBoundedType(null)));
  }

  /** Method under test: {@link Primitives#isPrimitive(Type)} */
  @Test
  public void testIsPrimitive2() {
    // Arrange
    Class<Object> type = Object.class;

    // Act and Assert
    assertFalse(Primitives.isPrimitive(type));
  }

  /** Method under test: {@link Primitives#wrap(Class)} */
  @Test
  public void testWrap() {
    // Arrange
    Class<Object> type = Object.class;

    // Act
    Class<Object> actualWrapResult = Primitives.wrap(type);

    // Assert
    Class<Object> expectedWrapResult = Object.class;
    assertEquals(expectedWrapResult, actualWrapResult);
  }

  /** Method under test: {@link Primitives#unwrap(Class)} */
  @Test
  public void testUnwrap() {
    // Arrange
    Class<Object> type = Object.class;

    // Act
    Class<Object> actualUnwrapResult = Primitives.unwrap(type);

    // Assert
    Class<Object> expectedUnwrapResult = Object.class;
    assertEquals(expectedUnwrapResult, actualUnwrapResult);
  }
}
