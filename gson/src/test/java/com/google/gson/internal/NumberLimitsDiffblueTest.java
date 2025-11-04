package com.google.gson.internal;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.junit.Test;

public class NumberLimitsDiffblueTest {
  /** Method under test: {@link NumberLimits#parseBigDecimal(String)} */
  @Test
  public void testParseBigDecimal() throws NumberFormatException {
    // Arrange and Act
    BigDecimal actualParseBigDecimalResult = NumberLimits.parseBigDecimal("2.3");

    // Assert
    assertEquals(new BigDecimal("2.3"), actualParseBigDecimalResult);
  }

  /** Method under test: {@link NumberLimits#parseBigInteger(String)} */
  @Test
  public void testParseBigInteger() throws NumberFormatException {
    // Arrange and Act
    BigInteger actualParseBigIntegerResult = NumberLimits.parseBigInteger("42");

    // Assert
    assertEquals("42", actualParseBigIntegerResult.toString());
    assertEquals(1, actualParseBigIntegerResult.getLowestSetBit());
    assertEquals(1, actualParseBigIntegerResult.signum());
    assertArrayEquals(new byte[] {'*'}, actualParseBigIntegerResult.toByteArray());
  }
}
