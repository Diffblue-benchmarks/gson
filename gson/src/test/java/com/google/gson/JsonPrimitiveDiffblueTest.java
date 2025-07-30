package com.google.gson;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.internal.LazilyParsedNumber;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JsonPrimitiveDiffblueTest {
  /**
   * Test {@link JsonPrimitive#JsonPrimitive(Boolean)}.
   *
   * <p>Method under test: {@link JsonPrimitive#JsonPrimitive(Boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonPrimitive.<init>(Boolean)"})
  public void testNewJsonPrimitive() {
    // Arrange and Act
    JsonPrimitive actualJsonPrimitive = new JsonPrimitive(true);

    // Assert
    assertEquals('t', actualJsonPrimitive.getAsCharacter());
    assertFalse(actualJsonPrimitive.isJsonArray());
    assertFalse(actualJsonPrimitive.isJsonNull());
    assertFalse(actualJsonPrimitive.isJsonObject());
    assertFalse(actualJsonPrimitive.isNumber());
    assertFalse(actualJsonPrimitive.isString());
    assertTrue(actualJsonPrimitive.isJsonPrimitive());
    assertTrue(actualJsonPrimitive.getAsBoolean());
    assertTrue(actualJsonPrimitive.isBoolean());
    String expectedAsString = Boolean.TRUE.toString();
    assertEquals(expectedAsString, actualJsonPrimitive.getAsString());
    assertSame(actualJsonPrimitive, actualJsonPrimitive.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonPrimitive#JsonPrimitive(Character)}.
   *
   * <p>Method under test: {@link JsonPrimitive#JsonPrimitive(Character)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonPrimitive.<init>(Character)"})
  public void testNewJsonPrimitive2() {
    // Arrange and Act
    JsonPrimitive actualJsonPrimitive = new JsonPrimitive('A');

    // Assert
    assertTrue(actualJsonPrimitive.getAsNumber() instanceof LazilyParsedNumber);
    assertEquals("A", actualJsonPrimitive.getAsString());
    assertEquals('A', actualJsonPrimitive.getAsCharacter());
    assertFalse(actualJsonPrimitive.isJsonArray());
    assertFalse(actualJsonPrimitive.isJsonNull());
    assertFalse(actualJsonPrimitive.isJsonObject());
    assertFalse(actualJsonPrimitive.getAsBoolean());
    assertFalse(actualJsonPrimitive.isBoolean());
    assertFalse(actualJsonPrimitive.isNumber());
    assertTrue(actualJsonPrimitive.isJsonPrimitive());
    assertTrue(actualJsonPrimitive.isString());
    assertSame(actualJsonPrimitive, actualJsonPrimitive.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonPrimitive#JsonPrimitive(String)}.
   *
   * <p>Method under test: {@link JsonPrimitive#JsonPrimitive(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonPrimitive.<init>(String)"})
  public void testNewJsonPrimitive3() {
    // Arrange and Act
    JsonPrimitive actualJsonPrimitive = new JsonPrimitive("String");

    // Assert
    assertTrue(actualJsonPrimitive.getAsNumber() instanceof LazilyParsedNumber);
    assertEquals("String", actualJsonPrimitive.getAsString());
    assertEquals('S', actualJsonPrimitive.getAsCharacter());
    assertFalse(actualJsonPrimitive.isJsonArray());
    assertFalse(actualJsonPrimitive.isJsonNull());
    assertFalse(actualJsonPrimitive.isJsonObject());
    assertFalse(actualJsonPrimitive.getAsBoolean());
    assertFalse(actualJsonPrimitive.isBoolean());
    assertFalse(actualJsonPrimitive.isNumber());
    assertTrue(actualJsonPrimitive.isJsonPrimitive());
    assertTrue(actualJsonPrimitive.isString());
    assertSame(actualJsonPrimitive, actualJsonPrimitive.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonPrimitive#JsonPrimitive(Number)}.
   *
   * <ul>
   *   <li>When valueOf one.
   *   <li>Then return AsString is {@code 1}.
   * </ul>
   *
   * <p>Method under test: {@link JsonPrimitive#JsonPrimitive(Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonPrimitive.<init>(Number)"})
  public void testNewJsonPrimitive_whenValueOfOne_thenReturnAsStringIs1() {
    // Arrange
    Integer number = Integer.valueOf(1);

    // Act
    JsonPrimitive actualJsonPrimitive = new JsonPrimitive(number);

    // Assert
    assertEquals("1", actualJsonPrimitive.getAsString());
    assertEquals('1', actualJsonPrimitive.getAsCharacter());
    assertEquals(1, actualJsonPrimitive.getAsInt());
    assertEquals(1.0d, actualJsonPrimitive.getAsDouble(), 0.0);
    assertEquals(1.0f, actualJsonPrimitive.getAsFloat(), 0.0f);
    assertEquals(1L, actualJsonPrimitive.getAsLong());
    assertEquals((byte) 1, actualJsonPrimitive.getAsByte());
    assertEquals((short) 1, actualJsonPrimitive.getAsShort());
    assertFalse(actualJsonPrimitive.isJsonArray());
    assertFalse(actualJsonPrimitive.isJsonNull());
    assertFalse(actualJsonPrimitive.isJsonObject());
    assertFalse(actualJsonPrimitive.getAsBoolean());
    assertFalse(actualJsonPrimitive.isBoolean());
    assertFalse(actualJsonPrimitive.isString());
    assertTrue(actualJsonPrimitive.isJsonPrimitive());
    assertTrue(actualJsonPrimitive.isNumber());
    BigDecimal expectedAsBigDecimal = new BigDecimal("1");
    assertEquals(expectedAsBigDecimal, actualJsonPrimitive.getAsBigDecimal());
    assertSame(actualJsonPrimitive, actualJsonPrimitive.getAsJsonPrimitive());
    assertSame(number, actualJsonPrimitive.getAsNumber());
  }

  /**
   * Test {@link JsonPrimitive#deepCopy()}.
   *
   * <p>Method under test: {@link JsonPrimitive#deepCopy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonPrimitive JsonPrimitive.deepCopy()"})
  public void testDeepCopy() {
    // Arrange
    JsonPrimitive jsonPrimitive = new JsonPrimitive("String");

    // Act and Assert
    assertSame(jsonPrimitive, jsonPrimitive.deepCopy());
  }

  /**
   * Test {@link JsonPrimitive#isBoolean()}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonPrimitive#isBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonPrimitive.isBoolean()"})
  public void testIsBoolean_givenJsonPrimitiveWithBoolIsTrue_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new JsonPrimitive(true).isBoolean());
  }

  /**
   * Test {@link JsonPrimitive#isBoolean()}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(String)} with {@code String}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JsonPrimitive#isBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonPrimitive.isBoolean()"})
  public void testIsBoolean_givenJsonPrimitiveWithString_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new JsonPrimitive("String").isBoolean());
  }

  /**
   * Test {@link JsonPrimitive#isNumber()}.
   *
   * <p>Method under test: {@link JsonPrimitive#isNumber()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonPrimitive.isNumber()"})
  public void testIsNumber() {
    // Arrange, Act and Assert
    assertFalse(new JsonPrimitive("String").isNumber());
  }

  /**
   * Test {@link JsonPrimitive#getAsNumber()}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(String)} with {@code String}.
   *   <li>Then return {@link LazilyParsedNumber}.
   * </ul>
   *
   * <p>Method under test: {@link JsonPrimitive#getAsNumber()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number JsonPrimitive.getAsNumber()"})
  public void testGetAsNumber_givenJsonPrimitiveWithString_thenReturnLazilyParsedNumber() {
    // Arrange and Act
    Number actualAsNumber = new JsonPrimitive("String").getAsNumber();

    // Assert
    assertTrue(actualAsNumber instanceof LazilyParsedNumber);
    assertEquals("String", actualAsNumber.toString());
  }

  /**
   * Test {@link JsonPrimitive#getAsNumber()}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonPrimitive#getAsNumber()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number JsonPrimitive.getAsNumber()"})
  public void testGetAsNumber_thenThrowUnsupportedOperationException() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> new JsonPrimitive(true).getAsNumber());
  }

  /**
   * Test {@link JsonPrimitive#isString()}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JsonPrimitive#isString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonPrimitive.isString()"})
  public void testIsString_givenJsonPrimitiveWithBoolIsTrue_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new JsonPrimitive(true).isString());
  }

  /**
   * Test {@link JsonPrimitive#isString()}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(String)} with {@code String}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonPrimitive#isString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonPrimitive.isString()"})
  public void testIsString_givenJsonPrimitiveWithString_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new JsonPrimitive("String").isString());
  }

  /**
   * Test {@link JsonPrimitive#getAsString()}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.
   *   <li>Then return {@link Boolean#TRUE} toString.
   * </ul>
   *
   * <p>Method under test: {@link JsonPrimitive#getAsString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonPrimitive.getAsString()"})
  public void testGetAsString_givenJsonPrimitiveWithBoolIsTrue_thenReturnTrueToString() {
    // Arrange and Act
    String actualAsString = new JsonPrimitive(true).getAsString();

    // Assert
    assertEquals(Boolean.TRUE.toString(), actualAsString);
  }

  /**
   * Test {@link JsonPrimitive#getAsString()}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(String)} with {@code String}.
   *   <li>Then return {@code String}.
   * </ul>
   *
   * <p>Method under test: {@link JsonPrimitive#getAsString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonPrimitive.getAsString()"})
  public void testGetAsString_givenJsonPrimitiveWithString_thenReturnString() {
    // Arrange, Act and Assert
    assertEquals("String", new JsonPrimitive("String").getAsString());
  }

  /**
   * Test {@link JsonPrimitive#getAsDouble()}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(String)} with string is {@code 42}.
   *   <li>Then return forty-two.
   * </ul>
   *
   * <p>Method under test: {@link JsonPrimitive#getAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JsonPrimitive.getAsDouble()"})
  public void testGetAsDouble_givenJsonPrimitiveWithStringIs42_thenReturnFortyTwo() {
    // Arrange, Act and Assert
    assertEquals(42.0d, new JsonPrimitive("42").getAsDouble(), 0.0);
  }

  /**
   * Test {@link JsonPrimitive#getAsBigDecimal()}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(String)} with string is {@code 42}.
   *   <li>Then return {@link BigDecimal#BigDecimal(String)} with {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link JsonPrimitive#getAsBigDecimal()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigDecimal JsonPrimitive.getAsBigDecimal()"})
  public void testGetAsBigDecimal_givenJsonPrimitiveWithStringIs42_thenReturnBigDecimalWith42() {
    // Arrange and Act
    BigDecimal actualAsBigDecimal = new JsonPrimitive("42").getAsBigDecimal();

    // Assert
    assertEquals(new BigDecimal("42"), actualAsBigDecimal);
  }

  /**
   * Test {@link JsonPrimitive#getAsBigInteger()}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(String)} with string is {@code 42}.
   *   <li>Then return toString is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link JsonPrimitive#getAsBigInteger()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigInteger JsonPrimitive.getAsBigInteger()"})
  public void testGetAsBigInteger_givenJsonPrimitiveWithStringIs42_thenReturnToStringIs42() {
    // Arrange and Act
    BigInteger actualAsBigInteger = new JsonPrimitive("42").getAsBigInteger();

    // Assert
    assertEquals("42", actualAsBigInteger.toString());
    assertEquals(1, actualAsBigInteger.getLowestSetBit());
    assertEquals(1, actualAsBigInteger.signum());
    assertArrayEquals(new byte[] {'*'}, actualAsBigInteger.toByteArray());
  }

  /**
   * Test {@link JsonPrimitive#getAsFloat()}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(String)} with string is {@code 42}.
   *   <li>Then return forty-two.
   * </ul>
   *
   * <p>Method under test: {@link JsonPrimitive#getAsFloat()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"float JsonPrimitive.getAsFloat()"})
  public void testGetAsFloat_givenJsonPrimitiveWithStringIs42_thenReturnFortyTwo() {
    // Arrange, Act and Assert
    assertEquals(42.0f, new JsonPrimitive("42").getAsFloat(), 0.0f);
  }

  /**
   * Test {@link JsonPrimitive#getAsLong()}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(String)} with string is {@code 42}.
   *   <li>Then return forty-two.
   * </ul>
   *
   * <p>Method under test: {@link JsonPrimitive#getAsLong()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JsonPrimitive.getAsLong()"})
  public void testGetAsLong_givenJsonPrimitiveWithStringIs42_thenReturnFortyTwo() {
    // Arrange, Act and Assert
    assertEquals(42L, new JsonPrimitive("42").getAsLong());
  }

  /**
   * Test {@link JsonPrimitive#getAsShort()}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(String)} with string is {@code 42}.
   *   <li>Then return forty-two.
   * </ul>
   *
   * <p>Method under test: {@link JsonPrimitive#getAsShort()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"short JsonPrimitive.getAsShort()"})
  public void testGetAsShort_givenJsonPrimitiveWithStringIs42_thenReturnFortyTwo() {
    // Arrange, Act and Assert
    assertEquals((short) 42, new JsonPrimitive("42").getAsShort());
  }

  /**
   * Test {@link JsonPrimitive#getAsByte()}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(String)} with string is {@code 42}.
   *   <li>Then return {@code *}.
   * </ul>
   *
   * <p>Method under test: {@link JsonPrimitive#getAsByte()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte JsonPrimitive.getAsByte()"})
  public void testGetAsByte_givenJsonPrimitiveWithStringIs42_thenReturnAsterisk() {
    // Arrange, Act and Assert
    assertEquals('*', new JsonPrimitive("42").getAsByte());
  }

  /**
   * Test {@link JsonPrimitive#equals(Object)}, and {@link JsonPrimitive#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link JsonPrimitive#equals(Object)}
   *   <li>{@link JsonPrimitive#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonPrimitive.equals(Object)", "int JsonPrimitive.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    JsonPrimitive jsonPrimitive = new JsonPrimitive("String");
    JsonPrimitive jsonPrimitive2 = new JsonPrimitive("String");

    // Act and Assert
    assertEquals(jsonPrimitive, jsonPrimitive2);
    int expectedHashCodeResult = jsonPrimitive.hashCode();
    assertEquals(expectedHashCodeResult, jsonPrimitive2.hashCode());
  }

  /**
   * Test {@link JsonPrimitive#equals(Object)}, and {@link JsonPrimitive#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link JsonPrimitive#equals(Object)}
   *   <li>{@link JsonPrimitive#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonPrimitive.equals(Object)", "int JsonPrimitive.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    JsonPrimitive jsonPrimitive = new JsonPrimitive("String");

    // Act and Assert
    assertEquals(jsonPrimitive, jsonPrimitive);
    int expectedHashCodeResult = jsonPrimitive.hashCode();
    assertEquals(expectedHashCodeResult, jsonPrimitive.hashCode());
  }

  /**
   * Test {@link JsonPrimitive#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link JsonPrimitive#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonPrimitive.equals(Object)", "int JsonPrimitive.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    JsonPrimitive jsonPrimitive = new JsonPrimitive("java.lang.Boolean");

    // Act and Assert
    assertNotEquals(jsonPrimitive, new JsonPrimitive("String"));
  }

  /**
   * Test {@link JsonPrimitive#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link JsonPrimitive#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonPrimitive.equals(Object)", "int JsonPrimitive.hashCode()"})
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new JsonPrimitive("String"), null);
  }

  /**
   * Test {@link JsonPrimitive#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link JsonPrimitive#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonPrimitive.equals(Object)", "int JsonPrimitive.hashCode()"})
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new JsonPrimitive("String"), "Different type to JsonPrimitive");
  }
}
