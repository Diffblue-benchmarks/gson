package com.google.gson.internal;

import static org.junit.Assert.assertEquals;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.math.BigDecimal;
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
}
