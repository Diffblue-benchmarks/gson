package com.google.gson.internal;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.junit.Test;

/** Tests for {@link NumberLimits}. */
public class NumberLimitsClaudeTest {

  // ==========================================================================
  // parseBigDecimal tests
  // ==========================================================================

  @Test
  public void parseBigDecimal_validSimpleNumber() {
    BigDecimal result = NumberLimits.parseBigDecimal("123.456");
    assertEquals(new BigDecimal("123.456"), result);
  }

  @Test
  public void parseBigDecimal_validIntegerWithoutDecimalPoint() {
    BigDecimal result = NumberLimits.parseBigDecimal("12345");
    assertEquals(new BigDecimal("12345"), result);
  }

  @Test
  public void parseBigDecimal_zero() {
    BigDecimal result = NumberLimits.parseBigDecimal("0");
    assertEquals(BigDecimal.ZERO, result);
  }

  @Test
  public void parseBigDecimal_negativeNumber() {
    BigDecimal result = NumberLimits.parseBigDecimal("-999.99");
    assertEquals(new BigDecimal("-999.99"), result);
  }

  @Test
  public void parseBigDecimal_scientificNotation() {
    BigDecimal result = NumberLimits.parseBigDecimal("1.5E10");
    assertEquals(new BigDecimal("1.5E10"), result);
  }

  @Test
  public void parseBigDecimal_negativeExponent() {
    BigDecimal result = NumberLimits.parseBigDecimal("1.5E-10");
    assertEquals(new BigDecimal("1.5E-10"), result);
  }

  @Test
  public void parseBigDecimal_verySmallScale() {
    // Scale within acceptable range
    BigDecimal result = NumberLimits.parseBigDecimal("1E-9999");
    assertEquals(new BigDecimal("1E-9999"), result);
  }

  @Test
  public void parseBigDecimal_veryLargePositiveExponent() {
    // Large positive exponent results in negative scale, within limits
    BigDecimal result = NumberLimits.parseBigDecimal("1E9999");
    assertEquals(new BigDecimal("1E9999"), result);
  }

  @Test
  public void parseBigDecimal_scaleAtPositiveBoundary() {
    // Scale of exactly 9999 should be accepted
    BigDecimal result = NumberLimits.parseBigDecimal("1E-9999");
    assertEquals(9999, result.scale());
  }

  @Test
  public void parseBigDecimal_scaleAtNegativeBoundary() {
    // Negative scale of -9999 (from positive exponent) should be accepted
    BigDecimal result = NumberLimits.parseBigDecimal("1E9999");
    assertEquals(-9999, result.scale());
  }

  @Test
  public void parseBigDecimal_scaleExceedsPositiveLimit() {
    // Scale of 10000 or more should throw
    try {
      NumberLimits.parseBigDecimal("1E-10000");
      fail("Expected NumberFormatException for scale >= 10000");
    } catch (NumberFormatException e) {
      assertTrue(e.getMessage().contains("unsupported scale"));
    }
  }

  @Test
  public void parseBigDecimal_scaleExceedsNegativeLimit() {
    // Large positive exponent creates negative scale exceeding -10000
    try {
      NumberLimits.parseBigDecimal("1E10000");
      fail("Expected NumberFormatException for scale <= -10000");
    } catch (NumberFormatException e) {
      assertTrue(e.getMessage().contains("unsupported scale"));
    }
  }

  @Test
  public void parseBigDecimal_veryLargeNegativeScale() {
    // This should throw because abs(scale) >= 10000
    try {
      NumberLimits.parseBigDecimal("1E100000");
      fail("Expected NumberFormatException");
    } catch (NumberFormatException e) {
      assertTrue(e.getMessage().contains("unsupported scale"));
    }
  }

  @Test
  public void parseBigDecimal_stringLengthAtLimit() {
    // String of exactly 10000 characters should be accepted
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 10000; i++) {
      sb.append("1");
    }
    BigDecimal result = NumberLimits.parseBigDecimal(sb.toString());
    assertNotNull(result);
  }

  @Test
  public void parseBigDecimal_stringLengthExceedsLimit() {
    // String of 10001 characters should throw
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 10001; i++) {
      sb.append("1");
    }
    try {
      NumberLimits.parseBigDecimal(sb.toString());
      fail("Expected NumberFormatException for string too long");
    } catch (NumberFormatException e) {
      assertTrue(e.getMessage().contains("Number string too large"));
    }
  }

  @Test
  public void parseBigDecimal_invalidFormat() {
    try {
      NumberLimits.parseBigDecimal("not_a_number");
      fail("Expected NumberFormatException for invalid format");
    } catch (NumberFormatException e) {
      // Expected
    }
  }

  @Test
  public void parseBigDecimal_emptyString() {
    try {
      NumberLimits.parseBigDecimal("");
      fail("Expected NumberFormatException for empty string");
    } catch (NumberFormatException e) {
      // Expected
    }
  }

  @Test
  public void parseBigDecimal_leadingZeros() {
    BigDecimal result = NumberLimits.parseBigDecimal("00123.456");
    assertEquals(new BigDecimal("00123.456"), result);
  }

  @Test
  public void parseBigDecimal_trailingZeros() {
    BigDecimal result = NumberLimits.parseBigDecimal("123.4560");
    assertEquals(new BigDecimal("123.4560"), result);
  }

  @Test
  public void parseBigDecimal_plusSign() {
    BigDecimal result = NumberLimits.parseBigDecimal("+123.456");
    assertEquals(new BigDecimal("+123.456"), result);
  }

  // ==========================================================================
  // parseBigInteger tests
  // ==========================================================================

  @Test
  public void parseBigInteger_validSimpleNumber() {
    BigInteger result = NumberLimits.parseBigInteger("12345");
    assertEquals(new BigInteger("12345"), result);
  }

  @Test
  public void parseBigInteger_zero() {
    BigInteger result = NumberLimits.parseBigInteger("0");
    assertEquals(BigInteger.ZERO, result);
  }

  @Test
  public void parseBigInteger_negativeNumber() {
    BigInteger result = NumberLimits.parseBigInteger("-99999");
    assertEquals(new BigInteger("-99999"), result);
  }

  @Test
  public void parseBigInteger_veryLargeNumber() {
    // Large number within string length limit
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 1000; i++) {
      sb.append("9");
    }
    BigInteger result = NumberLimits.parseBigInteger(sb.toString());
    assertEquals(new BigInteger(sb.toString()), result);
  }

  @Test
  public void parseBigInteger_stringLengthAtLimit() {
    // String of exactly 10000 characters should be accepted
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 10000; i++) {
      sb.append("1");
    }
    BigInteger result = NumberLimits.parseBigInteger(sb.toString());
    assertNotNull(result);
  }

  @Test
  public void parseBigInteger_stringLengthExceedsLimit() {
    // String of 10001 characters should throw
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 10001; i++) {
      sb.append("1");
    }
    try {
      NumberLimits.parseBigInteger(sb.toString());
      fail("Expected NumberFormatException for string too long");
    } catch (NumberFormatException e) {
      assertTrue(e.getMessage().contains("Number string too large"));
    }
  }

  @Test
  public void parseBigInteger_invalidFormat() {
    try {
      NumberLimits.parseBigInteger("not_a_number");
      fail("Expected NumberFormatException for invalid format");
    } catch (NumberFormatException e) {
      // Expected
    }
  }

  @Test
  public void parseBigInteger_emptyString() {
    try {
      NumberLimits.parseBigInteger("");
      fail("Expected NumberFormatException for empty string");
    } catch (NumberFormatException e) {
      // Expected
    }
  }

  @Test
  public void parseBigInteger_decimalPointNotAllowed() {
    try {
      NumberLimits.parseBigInteger("123.456");
      fail("Expected NumberFormatException for decimal point");
    } catch (NumberFormatException e) {
      // Expected - BigInteger doesn't accept decimal points
    }
  }

  @Test
  public void parseBigInteger_leadingZeros() {
    BigInteger result = NumberLimits.parseBigInteger("00123");
    assertEquals(new BigInteger("123"), result);
  }

  @Test
  public void parseBigInteger_plusSign() {
    BigInteger result = NumberLimits.parseBigInteger("+123");
    assertEquals(new BigInteger("123"), result);
  }

  @Test
  public void parseBigInteger_maxLongValue() {
    BigInteger result = NumberLimits.parseBigInteger(String.valueOf(Long.MAX_VALUE));
    assertEquals(BigInteger.valueOf(Long.MAX_VALUE), result);
  }

  @Test
  public void parseBigInteger_minLongValue() {
    BigInteger result = NumberLimits.parseBigInteger(String.valueOf(Long.MIN_VALUE));
    assertEquals(BigInteger.valueOf(Long.MIN_VALUE), result);
  }

  @Test
  public void parseBigInteger_beyondLongMax() {
    String beyondLongMax = "9223372036854775808"; // Long.MAX_VALUE + 1
    BigInteger result = NumberLimits.parseBigInteger(beyondLongMax);
    assertEquals(new BigInteger(beyondLongMax), result);
  }

  // ==========================================================================
  // Error message format tests
  // ==========================================================================

  @Test
  public void parseBigDecimal_errorMessageIncludesPartialString() {
    // Create a string longer than 10000 characters
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 10001; i++) {
      sb.append((i % 10));
    }
    try {
      NumberLimits.parseBigDecimal(sb.toString());
      fail("Expected NumberFormatException");
    } catch (NumberFormatException e) {
      // Error message should include the first 30 characters followed by "..."
      String message = e.getMessage();
      assertTrue(message.contains("..."));
      assertTrue(message.contains("Number string too large"));
    }
  }

  @Test
  public void parseBigInteger_errorMessageIncludesPartialString() {
    // Create a string longer than 10000 characters
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 10001; i++) {
      sb.append((i % 10));
    }
    try {
      NumberLimits.parseBigInteger(sb.toString());
      fail("Expected NumberFormatException");
    } catch (NumberFormatException e) {
      // Error message should include the first 30 characters followed by "..."
      String message = e.getMessage();
      assertTrue(message.contains("..."));
      assertTrue(message.contains("Number string too large"));
    }
  }
}
