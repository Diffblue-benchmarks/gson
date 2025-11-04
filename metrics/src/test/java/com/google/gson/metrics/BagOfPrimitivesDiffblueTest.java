package com.google.gson.metrics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class BagOfPrimitivesDiffblueTest {
  /** Method under test: {@link BagOfPrimitives#getExpectedJson()} */
  @Test
  void testGetExpectedJson() {
    // Arrange, Act and Assert
    assertEquals(
        "{\"longValue\":0,\"intValue\":0,\"booleanValue\":false,\"stringValue\":\"\"}",
        (new BagOfPrimitives()).getExpectedJson());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link BagOfPrimitives#equals(Object)}
   *   <li>{@link BagOfPrimitives#hashCode()}
   * </ul>
   */
  @Test
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    BagOfPrimitives bagOfPrimitives = new BagOfPrimitives();
    BagOfPrimitives bagOfPrimitives2 = new BagOfPrimitives();

    // Act and Assert
    assertEquals(bagOfPrimitives, bagOfPrimitives2);
    int expectedHashCodeResult = bagOfPrimitives.hashCode();
    assertEquals(expectedHashCodeResult, bagOfPrimitives2.hashCode());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link BagOfPrimitives#equals(Object)}
   *   <li>{@link BagOfPrimitives#hashCode()}
   * </ul>
   */
  @Test
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    BagOfPrimitives bagOfPrimitives = new BagOfPrimitives();

    // Act and Assert
    assertEquals(bagOfPrimitives, bagOfPrimitives);
    int expectedHashCodeResult = bagOfPrimitives.hashCode();
    assertEquals(expectedHashCodeResult, bagOfPrimitives.hashCode());
  }

  /** Method under test: {@link BagOfPrimitives#equals(Object)} */
  @Test
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    BagOfPrimitives bagOfPrimitives = new BagOfPrimitives(42L, 42, true, "42");

    // Act and Assert
    assertNotEquals(bagOfPrimitives, new BagOfPrimitives());
  }

  /** Method under test: {@link BagOfPrimitives#equals(Object)} */
  @Test
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    BagOfPrimitives bagOfPrimitives =
        new BagOfPrimitives(BagOfPrimitives.DEFAULT_VALUE, 42, true, "42");

    // Act and Assert
    assertNotEquals(bagOfPrimitives, new BagOfPrimitives());
  }

  /** Method under test: {@link BagOfPrimitives#equals(Object)} */
  @Test
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    BagOfPrimitives bagOfPrimitives =
        new BagOfPrimitives(BagOfPrimitives.DEFAULT_VALUE, 0, true, "42");

    // Act and Assert
    assertNotEquals(bagOfPrimitives, new BagOfPrimitives());
  }

  /** Method under test: {@link BagOfPrimitives#equals(Object)} */
  @Test
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    BagOfPrimitives bagOfPrimitives =
        new BagOfPrimitives(BagOfPrimitives.DEFAULT_VALUE, 0, false, "42");

    // Act and Assert
    assertNotEquals(bagOfPrimitives, new BagOfPrimitives());
  }

  /** Method under test: {@link BagOfPrimitives#equals(Object)} */
  @Test
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new BagOfPrimitives(), null);
  }

  /** Method under test: {@link BagOfPrimitives#equals(Object)} */
  @Test
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new BagOfPrimitives(), "Different type to BagOfPrimitives");
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link BagOfPrimitives#toString()}
   *   <li>{@link BagOfPrimitives#getIntValue()}
   * </ul>
   */
  @Test
  void testGettersAndSetters() {
    // Arrange
    BagOfPrimitives bagOfPrimitives = new BagOfPrimitives();

    // Act
    String actualToStringResult = bagOfPrimitives.toString();

    // Assert
    assertEquals("(longValue=0,intValue=0,booleanValue=false,stringValue=)", actualToStringResult);
    assertEquals(0, bagOfPrimitives.getIntValue());
  }

  /** Method under test: {@link BagOfPrimitives#BagOfPrimitives()} */
  @Test
  void testNewBagOfPrimitives() {
    // Arrange and Act
    BagOfPrimitives actualBagOfPrimitives = new BagOfPrimitives();

    // Assert
    assertEquals("", actualBagOfPrimitives.stringValue);
    assertEquals(
        "{\"longValue\":0,\"intValue\":0,\"booleanValue\":false,\"stringValue\":\"\"}",
        actualBagOfPrimitives.getExpectedJson());
    assertEquals(0, actualBagOfPrimitives.getIntValue());
    assertFalse(actualBagOfPrimitives.booleanValue);
    assertEquals(BagOfPrimitives.DEFAULT_VALUE, actualBagOfPrimitives.longValue);
  }

  /** Method under test: {@link BagOfPrimitives#BagOfPrimitives(long, int, boolean, String)} */
  @Test
  void testNewBagOfPrimitives2() {
    // Arrange and Act
    BagOfPrimitives actualBagOfPrimitives = new BagOfPrimitives(42L, 42, true, "42");

    // Assert
    assertEquals("42", actualBagOfPrimitives.stringValue);
    assertEquals(
        "{\"longValue\":42,\"intValue\":42,\"booleanValue\":true,\"stringValue\":\"42\"}",
        actualBagOfPrimitives.getExpectedJson());
    assertEquals(42, actualBagOfPrimitives.getIntValue());
    assertEquals(42L, actualBagOfPrimitives.longValue);
    assertTrue(actualBagOfPrimitives.booleanValue);
  }
}
