package com.google.gson.internal;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class NumberLimitsDiffblueTest {
  /**
   * Test {@link NumberLimits#parseBigDecimal(String)}.
   *
   * <ul>
   *   <li>When {@code 2.3}.
   *   <li>Then return {@link BigDecimal#BigDecimal(String)} with {@code 2.3}.
   * </ul>
   *
   * <p>Method under test: {@link NumberLimits#parseBigDecimal(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigDecimal NumberLimits.parseBigDecimal(String)"})
  public void testParseBigDecimal_when23_thenReturnBigDecimalWith23() throws NumberFormatException {
    // Arrange and Act
    BigDecimal actualParseBigDecimalResult = NumberLimits.parseBigDecimal("2.3");

    // Assert
    assertEquals(new BigDecimal("2.3"), actualParseBigDecimalResult);
  }

  /**
   * Test {@link NumberLimits#parseBigInteger(String)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return toString is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link NumberLimits#parseBigInteger(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigInteger NumberLimits.parseBigInteger(String)"})
  public void testParseBigInteger_when42_thenReturnToStringIs42() throws NumberFormatException {
    // Arrange and Act
    BigInteger actualParseBigIntegerResult = NumberLimits.parseBigInteger("42");

    // Assert
    assertEquals("42", actualParseBigIntegerResult.toString());
    assertEquals(1, actualParseBigIntegerResult.getLowestSetBit());
    assertEquals(1, actualParseBigIntegerResult.signum());
    assertArrayEquals(new byte[] {'*'}, actualParseBigIntegerResult.toByteArray());
  }
}
