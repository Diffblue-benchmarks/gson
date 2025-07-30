package com.google.gson.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LazilyParsedNumberDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link LazilyParsedNumber#LazilyParsedNumber(String)}
   *   <li>{@link LazilyParsedNumber#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LazilyParsedNumber.<init>(String)",
    "String LazilyParsedNumber.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals("42", new LazilyParsedNumber("42").toString());
  }

  /**
   * Test {@link LazilyParsedNumber#intValue()}.
   *
   * <ul>
   *   <li>Given {@link LazilyParsedNumber#LazilyParsedNumber(String)} with value is {@code 42}.
   *   <li>Then return forty-two.
   * </ul>
   *
   * <p>Method under test: {@link LazilyParsedNumber#intValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int LazilyParsedNumber.intValue()"})
  public void testIntValue_givenLazilyParsedNumberWithValueIs42_thenReturnFortyTwo() {
    // Arrange, Act and Assert
    assertEquals(42, new LazilyParsedNumber("42").intValue());
  }

  /**
   * Test {@link LazilyParsedNumber#longValue()}.
   *
   * <ul>
   *   <li>Given {@link LazilyParsedNumber#LazilyParsedNumber(String)} with value is {@code 42}.
   *   <li>Then return forty-two.
   * </ul>
   *
   * <p>Method under test: {@link LazilyParsedNumber#longValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long LazilyParsedNumber.longValue()"})
  public void testLongValue_givenLazilyParsedNumberWithValueIs42_thenReturnFortyTwo() {
    // Arrange, Act and Assert
    assertEquals(42L, new LazilyParsedNumber("42").longValue());
  }

  /**
   * Test {@link LazilyParsedNumber#floatValue()}.
   *
   * <ul>
   *   <li>Given {@link LazilyParsedNumber#LazilyParsedNumber(String)} with value is {@code 42}.
   *   <li>Then return forty-two.
   * </ul>
   *
   * <p>Method under test: {@link LazilyParsedNumber#floatValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"float LazilyParsedNumber.floatValue()"})
  public void testFloatValue_givenLazilyParsedNumberWithValueIs42_thenReturnFortyTwo() {
    // Arrange, Act and Assert
    assertEquals(42.0f, new LazilyParsedNumber("42").floatValue(), 0.0f);
  }

  /**
   * Test {@link LazilyParsedNumber#doubleValue()}.
   *
   * <ul>
   *   <li>Given {@link LazilyParsedNumber#LazilyParsedNumber(String)} with value is {@code 42}.
   *   <li>Then return forty-two.
   * </ul>
   *
   * <p>Method under test: {@link LazilyParsedNumber#doubleValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double LazilyParsedNumber.doubleValue()"})
  public void testDoubleValue_givenLazilyParsedNumberWithValueIs42_thenReturnFortyTwo() {
    // Arrange, Act and Assert
    assertEquals(42.0d, new LazilyParsedNumber("42").doubleValue(), 0.0);
  }

  /**
   * Test {@link LazilyParsedNumber#equals(Object)}, and {@link LazilyParsedNumber#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link LazilyParsedNumber#equals(Object)}
   *   <li>{@link LazilyParsedNumber#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean LazilyParsedNumber.equals(Object)",
    "int LazilyParsedNumber.hashCode()"
  })
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
   * Test {@link LazilyParsedNumber#equals(Object)}, and {@link LazilyParsedNumber#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link LazilyParsedNumber#equals(Object)}
   *   <li>{@link LazilyParsedNumber#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean LazilyParsedNumber.equals(Object)",
    "int LazilyParsedNumber.hashCode()"
  })
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    LazilyParsedNumber lazilyParsedNumber = new LazilyParsedNumber("42");

    // Act and Assert
    assertEquals(lazilyParsedNumber, lazilyParsedNumber);
    int expectedHashCodeResult = lazilyParsedNumber.hashCode();
    assertEquals(expectedHashCodeResult, lazilyParsedNumber.hashCode());
  }

  /**
   * Test {@link LazilyParsedNumber#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link LazilyParsedNumber#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean LazilyParsedNumber.equals(Object)",
    "int LazilyParsedNumber.hashCode()"
  })
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    LazilyParsedNumber lazilyParsedNumber = new LazilyParsedNumber("Value");

    // Act and Assert
    assertNotEquals(lazilyParsedNumber, new LazilyParsedNumber("42"));
  }

  /**
   * Test {@link LazilyParsedNumber#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link LazilyParsedNumber#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean LazilyParsedNumber.equals(Object)",
    "int LazilyParsedNumber.hashCode()"
  })
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new LazilyParsedNumber("42"), null);
  }

  /**
   * Test {@link LazilyParsedNumber#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link LazilyParsedNumber#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean LazilyParsedNumber.equals(Object)",
    "int LazilyParsedNumber.hashCode()"
  })
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new LazilyParsedNumber("42"), "Different type to LazilyParsedNumber");
  }
}
