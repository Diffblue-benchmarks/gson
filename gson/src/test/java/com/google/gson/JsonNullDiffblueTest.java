package com.google.gson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class JsonNullDiffblueTest {
  /** Method under test: {@link JsonNull#deepCopy()} */
  @Test
  public void testDeepCopy() {
    // Arrange
    JsonNull jsonNull = JsonNull.INSTANCE;

    // Act
    JsonNull actualDeepCopyResult = jsonNull.deepCopy();

    // Assert
    JsonNull jsonNull2 = actualDeepCopyResult.INSTANCE;
    assertSame(jsonNull2, jsonNull.getAsJsonNull());
    assertSame(jsonNull2, actualDeepCopyResult);
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link JsonNull#equals(Object)}
   *   <li>{@link JsonNull#hashCode()}
   * </ul>
   */
  @Test
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    JsonNull jsonNull = JsonNull.INSTANCE;
    JsonNull jsonNull2 = JsonNull.INSTANCE;

    // Act and Assert
    assertEquals(jsonNull, jsonNull2);
    int expectedHashCodeResult = jsonNull.hashCode();
    assertEquals(expectedHashCodeResult, jsonNull2.hashCode());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link JsonNull#equals(Object)}
   *   <li>{@link JsonNull#hashCode()}
   * </ul>
   */
  @Test
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    JsonNull jsonNull = JsonNull.INSTANCE;

    // Act and Assert
    assertEquals(jsonNull, jsonNull);
    int expectedHashCodeResult = jsonNull.hashCode();
    assertEquals(expectedHashCodeResult, jsonNull.hashCode());
  }

  /** Method under test: {@link JsonNull#equals(Object)} */
  @Test
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(JsonNull.INSTANCE, 3);
  }

  /** Method under test: {@link JsonNull#equals(Object)} */
  @Test
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(JsonNull.INSTANCE, null);
  }

  /** Method under test: {@link JsonNull#equals(Object)} */
  @Test
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(JsonNull.INSTANCE, "Different type to JsonNull");
  }

  /** Method under test: default or parameterless constructor of {@link JsonNull} */
  @Test
  public void testNewJsonNull() {
    // Arrange and Act
    JsonNull actualJsonNull = new JsonNull();

    // Assert
    assertFalse(actualJsonNull.isJsonArray());
    assertFalse(actualJsonNull.isJsonObject());
    assertFalse(actualJsonNull.isJsonPrimitive());
    assertTrue(actualJsonNull.isJsonNull());
    assertSame(actualJsonNull, actualJsonNull.getAsJsonNull());
  }
}
