package com.google.gson.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class LazilyParsedNumberDiffblueTest {
  /** Method under test: {@link LazilyParsedNumber#intValue()} */
  @Test
  public void testIntValue() {
    // Arrange, Act and Assert
    assertEquals(42, (new LazilyParsedNumber("42")).intValue());
  }

  /** Method under test: {@link LazilyParsedNumber#longValue()} */
  @Test
  public void testLongValue() {
    // Arrange, Act and Assert
    assertEquals(42L, (new LazilyParsedNumber("42")).longValue());
  }

  /** Method under test: {@link LazilyParsedNumber#floatValue()} */
  @Test
  public void testFloatValue() {
    // Arrange, Act and Assert
    assertEquals(42.0f, (new LazilyParsedNumber("42")).floatValue(), 0.0f);
  }

  /** Method under test: {@link LazilyParsedNumber#doubleValue()} */
  @Test
  public void testDoubleValue() {
    // Arrange, Act and Assert
    assertEquals(42.0d, (new LazilyParsedNumber("42")).doubleValue(), 0.0);
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link LazilyParsedNumber#equals(Object)}
   *   <li>{@link LazilyParsedNumber#hashCode()}
   * </ul>
   */
  @Test
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    LazilyParsedNumber lazilyParsedNumber = new LazilyParsedNumber("42");
    LazilyParsedNumber lazilyParsedNumber2 = new LazilyParsedNumber("42");

    // Act and Assert
    assertEquals(lazilyParsedNumber, lazilyParsedNumber2);
    int expectedHashCodeResult = lazilyParsedNumber.hashCode();
    assertEquals(expectedHashCodeResult, lazilyParsedNumber2.hashCode());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link LazilyParsedNumber#equals(Object)}
   *   <li>{@link LazilyParsedNumber#hashCode()}
   * </ul>
   */
  @Test
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    LazilyParsedNumber lazilyParsedNumber = new LazilyParsedNumber("42");

    // Act and Assert
    assertEquals(lazilyParsedNumber, lazilyParsedNumber);
    int expectedHashCodeResult = lazilyParsedNumber.hashCode();
    assertEquals(expectedHashCodeResult, lazilyParsedNumber.hashCode());
  }

  /** Method under test: {@link LazilyParsedNumber#equals(Object)} */
  @Test
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    LazilyParsedNumber lazilyParsedNumber = new LazilyParsedNumber("Value");

    // Act and Assert
    assertNotEquals(lazilyParsedNumber, new LazilyParsedNumber("42"));
  }

  /** Method under test: {@link LazilyParsedNumber#equals(Object)} */
  @Test
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new LazilyParsedNumber("42"), null);
  }

  /** Method under test: {@link LazilyParsedNumber#equals(Object)} */
  @Test
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new LazilyParsedNumber("42"), "Different type to LazilyParsedNumber");
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link LazilyParsedNumber#LazilyParsedNumber(String)}
   *   <li>{@link LazilyParsedNumber#toString()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals("42", (new LazilyParsedNumber("42")).toString());
  }
}
