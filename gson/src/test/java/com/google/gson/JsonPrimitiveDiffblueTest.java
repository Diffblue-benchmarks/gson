package com.google.gson;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import com.google.gson.internal.LazilyParsedNumber;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.junit.Test;

public class JsonPrimitiveDiffblueTest {
  /** Method under test: {@link JsonPrimitive#deepCopy()} */
  @Test
  public void testDeepCopy() {
    // Arrange
    JsonPrimitive jsonPrimitive = new JsonPrimitive("String");

    // Act and Assert
    assertSame(jsonPrimitive, jsonPrimitive.deepCopy());
  }

  /** Method under test: {@link JsonPrimitive#isBoolean()} */
  @Test
  public void testIsBoolean() {
    // Arrange, Act and Assert
    assertFalse((new JsonPrimitive("String")).isBoolean());
    assertTrue((new JsonPrimitive(true)).isBoolean());
  }

  /** Method under test: {@link JsonPrimitive#getAsBoolean()} */
  @Test
  public void testGetAsBoolean() {
    // Arrange, Act and Assert
    assertFalse((new JsonPrimitive("String")).getAsBoolean());
    assertTrue((new JsonPrimitive(true)).getAsBoolean());
  }

  /** Method under test: {@link JsonPrimitive#isNumber()} */
  @Test
  public void testIsNumber() {
    // Arrange, Act and Assert
    assertFalse((new JsonPrimitive("String")).isNumber());
  }

  /** Method under test: {@link JsonPrimitive#getAsNumber()} */
  @Test
  public void testGetAsNumber() {
    // Arrange and Act
    Number actualAsNumber = (new JsonPrimitive("String")).getAsNumber();

    // Assert
    assertTrue(actualAsNumber instanceof LazilyParsedNumber);
    assertEquals("String", actualAsNumber.toString());
  }

  /** Method under test: {@link JsonPrimitive#getAsNumber()} */
  @Test
  public void testGetAsNumber2() {
    // Arrange, Act and Assert
    assertThrows(
        UnsupportedOperationException.class, () -> (new JsonPrimitive(true)).getAsNumber());
  }

  /** Method under test: {@link JsonPrimitive#isString()} */
  @Test
  public void testIsString() {
    // Arrange, Act and Assert
    assertTrue((new JsonPrimitive("String")).isString());
    assertFalse((new JsonPrimitive(true)).isString());
  }

  /** Method under test: {@link JsonPrimitive#getAsString()} */
  @Test
  public void testGetAsString() {
    // Arrange, Act and Assert
    assertEquals("String", (new JsonPrimitive("String")).getAsString());
  }

  /** Method under test: {@link JsonPrimitive#getAsString()} */
  @Test
  public void testGetAsString2() {
    // Arrange and Act
    String actualAsString = (new JsonPrimitive(true)).getAsString();

    // Assert
    assertEquals(Boolean.TRUE.toString(), actualAsString);
  }

  /** Method under test: {@link JsonPrimitive#getAsDouble()} */
  @Test
  public void testGetAsDouble() {
    // Arrange, Act and Assert
    assertEquals(42.0d, (new JsonPrimitive("42")).getAsDouble(), 0.0);
  }

  /** Method under test: {@link JsonPrimitive#getAsBigDecimal()} */
  @Test
  public void testGetAsBigDecimal() {
    // Arrange and Act
    BigDecimal actualAsBigDecimal = (new JsonPrimitive("42")).getAsBigDecimal();

    // Assert
    assertEquals(new BigDecimal("42"), actualAsBigDecimal);
  }

  /** Method under test: {@link JsonPrimitive#getAsBigInteger()} */
  @Test
  public void testGetAsBigInteger() {
    // Arrange and Act
    BigInteger actualAsBigInteger = (new JsonPrimitive("42")).getAsBigInteger();

    // Assert
    assertEquals("42", actualAsBigInteger.toString());
    assertEquals(1, actualAsBigInteger.getLowestSetBit());
    assertEquals(1, actualAsBigInteger.signum());
    assertArrayEquals(new byte[] {'*'}, actualAsBigInteger.toByteArray());
  }

  /** Method under test: {@link JsonPrimitive#getAsFloat()} */
  @Test
  public void testGetAsFloat() {
    // Arrange, Act and Assert
    assertEquals(42.0f, (new JsonPrimitive("42")).getAsFloat(), 0.0f);
  }

  /** Method under test: {@link JsonPrimitive#getAsLong()} */
  @Test
  public void testGetAsLong() {
    // Arrange, Act and Assert
    assertEquals(42L, (new JsonPrimitive("42")).getAsLong());
  }

  /** Method under test: {@link JsonPrimitive#getAsShort()} */
  @Test
  public void testGetAsShort() {
    // Arrange, Act and Assert
    assertEquals((short) 42, (new JsonPrimitive("42")).getAsShort());
  }

  /** Method under test: {@link JsonPrimitive#getAsInt()} */
  @Test
  public void testGetAsInt() {
    // Arrange, Act and Assert
    assertEquals(42, (new JsonPrimitive("42")).getAsInt());
  }

  /** Method under test: {@link JsonPrimitive#getAsByte()} */
  @Test
  public void testGetAsByte() {
    // Arrange, Act and Assert
    assertEquals('*', (new JsonPrimitive("42")).getAsByte());
  }

  /** Method under test: {@link JsonPrimitive#getAsCharacter()} */
  @Test
  public void testGetAsCharacter() {
    // Arrange, Act and Assert
    assertEquals('S', (new JsonPrimitive("String")).getAsCharacter());
    assertThrows(
        UnsupportedOperationException.class, () -> (new JsonPrimitive("")).getAsCharacter());
    assertEquals('t', (new JsonPrimitive(true)).getAsCharacter());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link JsonPrimitive#equals(Object)}
   *   <li>{@link JsonPrimitive#hashCode()}
   * </ul>
   */
  @Test
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
   * Methods under test:
   *
   * <ul>
   *   <li>{@link JsonPrimitive#equals(Object)}
   *   <li>{@link JsonPrimitive#hashCode()}
   * </ul>
   */
  @Test
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    JsonPrimitive jsonPrimitive = new JsonPrimitive("String");

    // Act and Assert
    assertEquals(jsonPrimitive, jsonPrimitive);
    int expectedHashCodeResult = jsonPrimitive.hashCode();
    assertEquals(expectedHashCodeResult, jsonPrimitive.hashCode());
  }

  /** Method under test: {@link JsonPrimitive#equals(Object)} */
  @Test
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    JsonPrimitive jsonPrimitive = new JsonPrimitive("java.lang.Boolean");

    // Act and Assert
    assertNotEquals(jsonPrimitive, new JsonPrimitive("String"));
  }

  /** Method under test: {@link JsonPrimitive#equals(Object)} */
  @Test
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new JsonPrimitive("String"), null);
  }

  /** Method under test: {@link JsonPrimitive#equals(Object)} */
  @Test
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new JsonPrimitive("String"), "Different type to JsonPrimitive");
  }

  /** Method under test: {@link JsonPrimitive#JsonPrimitive(Boolean)} */
  @Test
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

  /** Method under test: {@link JsonPrimitive#JsonPrimitive(Character)} */
  @Test
  public void testNewJsonPrimitive2() {
    // Arrange and Act
    JsonPrimitive actualJsonPrimitive = new JsonPrimitive('A');

    // Assert
    Number asNumber = actualJsonPrimitive.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("A", actualJsonPrimitive.getAsString());
    assertEquals("A", asNumber.toString());
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

  /** Method under test: {@link JsonPrimitive#JsonPrimitive(Number)} */
  @Test
  public void testNewJsonPrimitive3() {
    // Arrange
    Integer number = Integer.valueOf(1);

    // Act
    JsonPrimitive actualJsonPrimitive = new JsonPrimitive(number);

    // Assert
    assertEquals("1", actualJsonPrimitive.getAsString());
    BigInteger asBigInteger = actualJsonPrimitive.getAsBigInteger();
    assertEquals("1", asBigInteger.toString());
    assertEquals('1', actualJsonPrimitive.getAsCharacter());
    assertEquals(0, asBigInteger.getLowestSetBit());
    int asInt = actualJsonPrimitive.getAsInt();
    assertEquals(1, asInt);
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
    assertSame(number, asInt);
    assertSame(number, actualJsonPrimitive.getAsNumber());
    assertSame(number, asBigInteger.signum());
    assertArrayEquals(new byte[] {1}, asBigInteger.toByteArray());
  }

  /** Method under test: {@link JsonPrimitive#JsonPrimitive(String)} */
  @Test
  public void testNewJsonPrimitive4() {
    // Arrange and Act
    JsonPrimitive actualJsonPrimitive = new JsonPrimitive("String");

    // Assert
    Number asNumber = actualJsonPrimitive.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("String", actualJsonPrimitive.getAsString());
    assertEquals("String", asNumber.toString());
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
}
