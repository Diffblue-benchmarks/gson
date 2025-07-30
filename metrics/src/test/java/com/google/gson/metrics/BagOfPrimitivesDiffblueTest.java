package com.google.gson.metrics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class BagOfPrimitivesDiffblueTest {
  /**
   * Test {@link BagOfPrimitives#BagOfPrimitives()}.
   *
   * <p>Method under test: {@link BagOfPrimitives#BagOfPrimitives()}
   */
  @Test
  @DisplayName("Test new BagOfPrimitives()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void BagOfPrimitives.<init>()"})
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

  /**
   * Test {@link BagOfPrimitives#BagOfPrimitives(long, int, boolean, String)}.
   *
   * <p>Method under test: {@link BagOfPrimitives#BagOfPrimitives(long, int, boolean, String)}
   */
  @Test
  @DisplayName("Test new BagOfPrimitives(long, int, boolean, String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void BagOfPrimitives.<init>(long, int, boolean, String)"})
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

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link BagOfPrimitives#toString()}
   *   <li>{@link BagOfPrimitives#getIntValue()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"int BagOfPrimitives.getIntValue()", "String BagOfPrimitives.toString()"})
  void testGettersAndSetters() {
    // Arrange
    BagOfPrimitives bagOfPrimitives = new BagOfPrimitives();

    // Act
    String actualToStringResult = bagOfPrimitives.toString();

    // Assert
    assertEquals("(longValue=0,intValue=0,booleanValue=false,stringValue=)", actualToStringResult);
    assertEquals(0, bagOfPrimitives.getIntValue());
  }

  /**
   * Test {@link BagOfPrimitives#getExpectedJson()}.
   *
   * <p>Method under test: {@link BagOfPrimitives#getExpectedJson()}
   */
  @Test
  @DisplayName("Test getExpectedJson()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String BagOfPrimitives.getExpectedJson()"})
  void testGetExpectedJson() {
    // Arrange, Act and Assert
    assertEquals(
        "{\"longValue\":0,\"intValue\":0,\"booleanValue\":false,\"stringValue\":\"\"}",
        new BagOfPrimitives().getExpectedJson());
  }

  /**
   * Test {@link BagOfPrimitives#equals(Object)}, and {@link BagOfPrimitives#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link BagOfPrimitives#equals(Object)}
   *   <li>{@link BagOfPrimitives#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean BagOfPrimitives.equals(Object)", "int BagOfPrimitives.hashCode()"})
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
   * Test {@link BagOfPrimitives#equals(Object)}, and {@link BagOfPrimitives#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link BagOfPrimitives#equals(Object)}
   *   <li>{@link BagOfPrimitives#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean BagOfPrimitives.equals(Object)", "int BagOfPrimitives.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    BagOfPrimitives bagOfPrimitives = new BagOfPrimitives();

    // Act and Assert
    assertEquals(bagOfPrimitives, bagOfPrimitives);
    int expectedHashCodeResult = bagOfPrimitives.hashCode();
    assertEquals(expectedHashCodeResult, bagOfPrimitives.hashCode());
  }

  /**
   * Test {@link BagOfPrimitives#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link BagOfPrimitives#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean BagOfPrimitives.equals(Object)", "int BagOfPrimitives.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    BagOfPrimitives bagOfPrimitives = new BagOfPrimitives(42L, 42, true, "42");

    // Act and Assert
    assertNotEquals(bagOfPrimitives, new BagOfPrimitives());
  }

  /**
   * Test {@link BagOfPrimitives#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link BagOfPrimitives#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean BagOfPrimitives.equals(Object)", "int BagOfPrimitives.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    BagOfPrimitives bagOfPrimitives =
        new BagOfPrimitives(BagOfPrimitives.DEFAULT_VALUE, 42, true, "42");

    // Act and Assert
    assertNotEquals(bagOfPrimitives, new BagOfPrimitives());
  }

  /**
   * Test {@link BagOfPrimitives#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link BagOfPrimitives#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean BagOfPrimitives.equals(Object)", "int BagOfPrimitives.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    BagOfPrimitives bagOfPrimitives =
        new BagOfPrimitives(BagOfPrimitives.DEFAULT_VALUE, 0, true, "42");

    // Act and Assert
    assertNotEquals(bagOfPrimitives, new BagOfPrimitives());
  }

  /**
   * Test {@link BagOfPrimitives#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link BagOfPrimitives#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean BagOfPrimitives.equals(Object)", "int BagOfPrimitives.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    BagOfPrimitives bagOfPrimitives =
        new BagOfPrimitives(BagOfPrimitives.DEFAULT_VALUE, 0, false, "42");

    // Act and Assert
    assertNotEquals(bagOfPrimitives, new BagOfPrimitives());
  }

  /**
   * Test {@link BagOfPrimitives#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link BagOfPrimitives#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean BagOfPrimitives.equals(Object)", "int BagOfPrimitives.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new BagOfPrimitives(), null);
  }

  /**
   * Test {@link BagOfPrimitives#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link BagOfPrimitives#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean BagOfPrimitives.equals(Object)", "int BagOfPrimitives.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new BagOfPrimitives(), "Different type to BagOfPrimitives");
  }
}
