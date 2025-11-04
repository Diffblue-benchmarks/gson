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
import java.util.Iterator;
import org.junit.Test;

public class JsonArrayDiffblueTest {
  /** Method under test: {@link JsonArray#deepCopy()} */
  @Test
  public void testDeepCopy() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);

    // Act and Assert
    assertEquals(jsonArray, jsonArray.deepCopy());
  }

  /** Method under test: {@link JsonArray#deepCopy()} */
  @Test
  public void testDeepCopy2() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(true);

    // Act and Assert
    assertEquals(jsonArray, jsonArray.deepCopy());
  }

  /** Method under test: {@link JsonArray#deepCopy()} */
  @Test
  public void testDeepCopy3() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(JsonNull.INSTANCE);
    jsonArray.add(true);

    // Act and Assert
    assertEquals(jsonArray, jsonArray.deepCopy());
  }

  /** Method under test: {@link JsonArray#deepCopy()} */
  @Test
  public void testDeepCopy4() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new JsonArray(3));
    jsonArray.add(true);

    // Act and Assert
    assertEquals(jsonArray, jsonArray.deepCopy());
  }

  /** Method under test: {@link JsonArray#deepCopy()} */
  @Test
  public void testDeepCopy5() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new JsonObject());
    jsonArray.add(true);

    // Act and Assert
    assertEquals(jsonArray, jsonArray.deepCopy());
  }

  /** Method under test: {@link JsonArray#deepCopy()} */
  @Test
  public void testDeepCopy6() {
    // Arrange
    JsonObject element = new JsonObject();
    element.addProperty("com.google.gson.JsonObject", "Value");

    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(element);
    jsonArray.add(true);

    // Act and Assert
    assertEquals(jsonArray, jsonArray.deepCopy());
  }

  /** Method under test: {@link JsonArray#add(JsonElement)} */
  @Test
  public void testAdd() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);

    // Act
    jsonArray.add((JsonElement) null);

    // Assert
    Iterator<JsonElement> iteratorResult = jsonArray.iterator();
    JsonElement nextResult = iteratorResult.next();
    assertTrue(nextResult instanceof JsonNull);
    assertEquals(1, jsonArray.size());
    assertFalse(jsonArray.isEmpty());
    assertFalse(nextResult.isJsonArray());
    assertFalse(nextResult.isJsonObject());
    assertFalse(nextResult.isJsonPrimitive());
    assertFalse(iteratorResult.hasNext());
    assertTrue(nextResult.isJsonNull());
    assertSame(nextResult, nextResult.getAsJsonNull());
  }

  /** Method under test: {@link JsonArray#add(Boolean)} */
  @Test
  public void testAdd2() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);

    // Act
    jsonArray.add(true);

    // Assert
    Iterator<JsonElement> iteratorResult = jsonArray.iterator();
    JsonElement nextResult = iteratorResult.next();
    assertTrue(nextResult instanceof JsonPrimitive);
    assertEquals('t', jsonArray.getAsCharacter());
    assertEquals('t', nextResult.getAsCharacter());
    assertEquals(1, jsonArray.size());
    assertFalse(jsonArray.isEmpty());
    assertFalse(nextResult.isJsonArray());
    assertFalse(nextResult.isJsonNull());
    assertFalse(nextResult.isJsonObject());
    assertFalse(((JsonPrimitive) nextResult).isNumber());
    assertFalse(((JsonPrimitive) nextResult).isString());
    assertFalse(iteratorResult.hasNext());
    assertTrue(jsonArray.getAsBoolean());
    assertTrue(nextResult.getAsBoolean());
    assertTrue(nextResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) nextResult).isBoolean());
    String expectedAsString = Boolean.TRUE.toString();
    assertEquals(expectedAsString, jsonArray.getAsString());
    String expectedAsString2 = Boolean.TRUE.toString();
    assertEquals(expectedAsString2, nextResult.getAsString());
    assertSame(nextResult, nextResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonArray#add(Boolean)} */
  @Test
  public void testAdd3() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);

    // Act
    jsonArray.add((Boolean) null);

    // Assert
    Iterator<JsonElement> iteratorResult = jsonArray.iterator();
    JsonElement nextResult = iteratorResult.next();
    assertTrue(nextResult instanceof JsonNull);
    assertEquals(1, jsonArray.size());
    assertFalse(jsonArray.isEmpty());
    assertFalse(nextResult.isJsonArray());
    assertFalse(nextResult.isJsonObject());
    assertFalse(nextResult.isJsonPrimitive());
    assertFalse(iteratorResult.hasNext());
    assertTrue(nextResult.isJsonNull());
    assertSame(nextResult, nextResult.getAsJsonNull());
  }

  /** Method under test: {@link JsonArray#add(Character)} */
  @Test
  public void testAdd4() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);

    // Act
    jsonArray.add('A');

    // Assert
    Iterator<JsonElement> iteratorResult = jsonArray.iterator();
    JsonElement nextResult = iteratorResult.next();
    assertTrue(nextResult instanceof JsonPrimitive);
    Number asNumber = jsonArray.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    Number asNumber2 = nextResult.getAsNumber();
    assertTrue(asNumber2 instanceof LazilyParsedNumber);
    assertEquals("A", jsonArray.getAsString());
    assertEquals("A", nextResult.getAsString());
    assertEquals("A", asNumber.toString());
    assertEquals("A", asNumber2.toString());
    assertEquals('A', jsonArray.getAsCharacter());
    assertEquals('A', nextResult.getAsCharacter());
    assertEquals(1, jsonArray.size());
    assertFalse(jsonArray.getAsBoolean());
    assertFalse(jsonArray.isEmpty());
    assertFalse(nextResult.getAsBoolean());
    assertFalse(nextResult.isJsonArray());
    assertFalse(nextResult.isJsonNull());
    assertFalse(nextResult.isJsonObject());
    assertFalse(((JsonPrimitive) nextResult).isBoolean());
    assertFalse(((JsonPrimitive) nextResult).isNumber());
    assertFalse(iteratorResult.hasNext());
    assertTrue(nextResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) nextResult).isString());
    assertSame(nextResult, nextResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonArray#add(Character)} */
  @Test
  public void testAdd5() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);

    // Act
    jsonArray.add((Character) null);

    // Assert
    Iterator<JsonElement> iteratorResult = jsonArray.iterator();
    JsonElement nextResult = iteratorResult.next();
    assertTrue(nextResult instanceof JsonNull);
    assertEquals(1, jsonArray.size());
    assertFalse(jsonArray.isEmpty());
    assertFalse(nextResult.isJsonArray());
    assertFalse(nextResult.isJsonObject());
    assertFalse(nextResult.isJsonPrimitive());
    assertFalse(iteratorResult.hasNext());
    assertTrue(nextResult.isJsonNull());
    assertSame(nextResult, nextResult.getAsJsonNull());
  }

  /** Method under test: {@link JsonArray#add(Number)} */
  @Test
  public void testAdd6() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    Integer number = Integer.valueOf(1);

    // Act
    jsonArray.add(number);

    // Assert
    Iterator<JsonElement> iteratorResult = jsonArray.iterator();
    JsonElement nextResult = iteratorResult.next();
    assertTrue(nextResult instanceof JsonPrimitive);
    assertEquals("1", jsonArray.getAsString());
    assertEquals("1", nextResult.getAsString());
    BigInteger asBigInteger = jsonArray.getAsBigInteger();
    assertEquals("1", asBigInteger.toString());
    assertEquals('1', jsonArray.getAsCharacter());
    assertEquals('1', nextResult.getAsCharacter());
    assertEquals(0, asBigInteger.getLowestSetBit());
    int asInt = jsonArray.getAsInt();
    assertEquals(1, asInt);
    assertEquals(1, asBigInteger.signum());
    assertEquals(1.0d, jsonArray.getAsDouble(), 0.0);
    assertEquals(1.0d, nextResult.getAsDouble(), 0.0);
    assertEquals(1.0f, jsonArray.getAsFloat(), 0.0f);
    assertEquals(1.0f, nextResult.getAsFloat(), 0.0f);
    assertEquals(1L, jsonArray.getAsLong());
    assertEquals(1L, nextResult.getAsLong());
    assertEquals((byte) 1, jsonArray.getAsByte());
    assertEquals((byte) 1, nextResult.getAsByte());
    assertEquals((short) 1, jsonArray.getAsShort());
    assertEquals((short) 1, nextResult.getAsShort());
    assertFalse(jsonArray.getAsBoolean());
    assertFalse(jsonArray.isEmpty());
    assertFalse(nextResult.getAsBoolean());
    assertFalse(nextResult.isJsonArray());
    assertFalse(nextResult.isJsonNull());
    assertFalse(nextResult.isJsonObject());
    assertFalse(((JsonPrimitive) nextResult).isBoolean());
    assertFalse(((JsonPrimitive) nextResult).isString());
    assertFalse(iteratorResult.hasNext());
    assertTrue(nextResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) nextResult).isNumber());
    BigDecimal expectedAsBigDecimal = new BigDecimal("1");
    assertEquals(expectedAsBigDecimal, jsonArray.getAsBigDecimal());
    BigDecimal expectedAsBigDecimal2 = new BigDecimal("1");
    assertEquals(expectedAsBigDecimal2, nextResult.getAsBigDecimal());
    assertSame(asBigInteger, nextResult.getAsBigInteger());
    assertSame(number, asInt);
    assertSame(number, jsonArray.getAsNumber());
    assertSame(number, jsonArray.size());
    assertSame(number, nextResult.getAsInt());
    assertSame(number, nextResult.getAsNumber());
    assertSame(nextResult, nextResult.getAsJsonPrimitive());
    assertArrayEquals(new byte[] {1}, asBigInteger.toByteArray());
  }

  /** Method under test: {@link JsonArray#add(Number)} */
  @Test
  public void testAdd7() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);

    // Act
    jsonArray.add((Number) null);

    // Assert
    Iterator<JsonElement> iteratorResult = jsonArray.iterator();
    JsonElement nextResult = iteratorResult.next();
    assertTrue(nextResult instanceof JsonNull);
    assertEquals(1, jsonArray.size());
    assertFalse(jsonArray.isEmpty());
    assertFalse(nextResult.isJsonArray());
    assertFalse(nextResult.isJsonObject());
    assertFalse(nextResult.isJsonPrimitive());
    assertFalse(iteratorResult.hasNext());
    assertTrue(nextResult.isJsonNull());
    assertSame(nextResult, nextResult.getAsJsonNull());
  }

  /** Method under test: {@link JsonArray#add(String)} */
  @Test
  public void testAdd8() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);

    // Act
    jsonArray.add("String");

    // Assert
    Iterator<JsonElement> iteratorResult = jsonArray.iterator();
    JsonElement nextResult = iteratorResult.next();
    assertTrue(nextResult instanceof JsonPrimitive);
    Number asNumber = jsonArray.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    Number asNumber2 = nextResult.getAsNumber();
    assertTrue(asNumber2 instanceof LazilyParsedNumber);
    assertEquals("String", jsonArray.getAsString());
    assertEquals("String", nextResult.getAsString());
    assertEquals("String", asNumber.toString());
    assertEquals("String", asNumber2.toString());
    assertEquals('S', jsonArray.getAsCharacter());
    assertEquals('S', nextResult.getAsCharacter());
    assertEquals(1, jsonArray.size());
    assertFalse(jsonArray.getAsBoolean());
    assertFalse(jsonArray.isEmpty());
    assertFalse(nextResult.getAsBoolean());
    assertFalse(nextResult.isJsonArray());
    assertFalse(nextResult.isJsonNull());
    assertFalse(nextResult.isJsonObject());
    assertFalse(((JsonPrimitive) nextResult).isBoolean());
    assertFalse(((JsonPrimitive) nextResult).isNumber());
    assertFalse(iteratorResult.hasNext());
    assertTrue(nextResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) nextResult).isString());
    assertSame(nextResult, nextResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonArray#add(String)} */
  @Test
  public void testAdd9() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);

    // Act
    jsonArray.add((String) null);

    // Assert
    Iterator<JsonElement> iteratorResult = jsonArray.iterator();
    JsonElement nextResult = iteratorResult.next();
    assertTrue(nextResult instanceof JsonNull);
    assertEquals(1, jsonArray.size());
    assertFalse(jsonArray.isEmpty());
    assertFalse(nextResult.isJsonArray());
    assertFalse(nextResult.isJsonObject());
    assertFalse(nextResult.isJsonPrimitive());
    assertFalse(iteratorResult.hasNext());
    assertTrue(nextResult.isJsonNull());
    assertSame(nextResult, nextResult.getAsJsonNull());
  }

  /** Method under test: {@link JsonArray#remove(JsonElement)} */
  @Test
  public void testRemove() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);

    // Act
    boolean actualRemoveResult = jsonArray.remove(JsonNull.INSTANCE);

    // Assert
    assertEquals(0, jsonArray.size());
    assertFalse(actualRemoveResult);
    assertFalse(jsonArray.iterator().hasNext());
    assertTrue(jsonArray.isEmpty());
  }

  /** Method under test: {@link JsonArray#remove(JsonElement)} */
  @Test
  public void testRemove2() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(JsonNull.INSTANCE);

    // Act
    boolean actualRemoveResult = jsonArray.remove(JsonNull.INSTANCE);

    // Assert
    assertEquals(0, jsonArray.size());
    assertFalse(jsonArray.iterator().hasNext());
    assertTrue(jsonArray.isEmpty());
    assertTrue(actualRemoveResult);
  }

  /** Method under test: {@link JsonArray#remove(JsonElement)} */
  @Test
  public void testRemove3() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(JsonNull.INSTANCE);
    JsonArray element = new JsonArray(3);

    // Act
    boolean actualRemoveResult = jsonArray.remove(element);

    // Assert
    assertEquals(0, element.size());
    assertFalse(actualRemoveResult);
    assertFalse(element.iterator().hasNext());
    assertTrue(element.isEmpty());
  }

  /** Method under test: {@link JsonArray#remove(JsonElement)} */
  @Test
  public void testRemove4() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(true);
    jsonArray.add(JsonNull.INSTANCE);
    JsonArray element = new JsonArray(3);

    // Act
    boolean actualRemoveResult = jsonArray.remove(element);

    // Assert
    assertEquals(0, element.size());
    assertFalse(actualRemoveResult);
    assertFalse(element.iterator().hasNext());
    assertTrue(element.isEmpty());
  }

  /** Method under test: {@link JsonArray#remove(JsonElement)} */
  @Test
  public void testRemove5() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new JsonArray(3));
    JsonArray element = new JsonArray(3);

    // Act and Assert
    assertTrue(jsonArray.remove(element));
    assertEquals(jsonArray, element);
  }

  /** Method under test: {@link JsonArray#remove(JsonElement)} */
  @Test
  public void testRemove6() {
    // Arrange
    JsonArray element = new JsonArray(3);
    element.add(true);

    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(element);
    JsonArray element2 = new JsonArray(3);

    // Act
    boolean actualRemoveResult = jsonArray.remove(element2);

    // Assert
    assertEquals(0, element2.size());
    assertFalse(actualRemoveResult);
    assertFalse(element2.iterator().hasNext());
    assertTrue(element2.isEmpty());
  }

  /** Method under test: {@link JsonArray#contains(JsonElement)} */
  @Test
  public void testContains() {
    // Arrange, Act and Assert
    assertFalse((new JsonArray(3)).contains(JsonNull.INSTANCE));
  }

  /** Method under test: {@link JsonArray#contains(JsonElement)} */
  @Test
  public void testContains2() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(JsonNull.INSTANCE);

    // Act and Assert
    assertTrue(jsonArray.contains(JsonNull.INSTANCE));
  }

  /** Method under test: {@link JsonArray#contains(JsonElement)} */
  @Test
  public void testContains3() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(JsonNull.INSTANCE);

    // Act and Assert
    assertFalse(jsonArray.contains(new JsonArray(3)));
  }

  /** Method under test: {@link JsonArray#contains(JsonElement)} */
  @Test
  public void testContains4() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(true);
    jsonArray.add(JsonNull.INSTANCE);

    // Act and Assert
    assertFalse(jsonArray.contains(new JsonArray(3)));
  }

  /** Method under test: {@link JsonArray#contains(JsonElement)} */
  @Test
  public void testContains5() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new JsonArray(3));

    // Act and Assert
    assertTrue(jsonArray.contains(new JsonArray(3)));
  }

  /** Method under test: {@link JsonArray#contains(JsonElement)} */
  @Test
  public void testContains6() {
    // Arrange
    JsonArray element = new JsonArray(3);
    element.add(true);

    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(element);

    // Act and Assert
    assertFalse(jsonArray.contains(new JsonArray(3)));
  }

  /** Method under test: {@link JsonArray#contains(JsonElement)} */
  @Test
  public void testContains7() {
    // Arrange
    JsonArray element = new JsonArray(3);
    element.add(true);

    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(element);

    JsonArray element2 = new JsonArray(3);
    element2.add(false);

    // Act and Assert
    assertFalse(jsonArray.contains(element2));
  }

  /** Method under test: {@link JsonArray#contains(JsonElement)} */
  @Test
  public void testContains8() {
    // Arrange
    JsonArray element = new JsonArray(3);
    element.add(true);

    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(element);

    JsonArray element2 = new JsonArray(3);
    element2.add(JsonNull.INSTANCE);

    // Act and Assert
    assertFalse(jsonArray.contains(element2));
  }

  /** Method under test: {@link JsonArray#size()} */
  @Test
  public void testSize() {
    // Arrange, Act and Assert
    assertEquals(0, (new JsonArray(3)).size());
  }

  /** Method under test: {@link JsonArray#isEmpty()} */
  @Test
  public void testIsEmpty() {
    // Arrange, Act and Assert
    assertTrue((new JsonArray(3)).isEmpty());
  }

  /** Method under test: {@link JsonArray#isEmpty()} */
  @Test
  public void testIsEmpty2() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(true);

    // Act and Assert
    assertFalse(jsonArray.isEmpty());
  }

  /** Method under test: {@link JsonArray#iterator()} */
  @Test
  public void testIterator() {
    // Arrange, Act and Assert
    assertFalse((new JsonArray(3)).iterator().hasNext());
  }

  /** Method under test: {@link JsonArray#get(int)} */
  @Test
  public void testGet() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(false);
    jsonArray.add(true);

    // Act
    JsonElement actualGetResult = jsonArray.get(1);

    // Assert
    assertTrue(actualGetResult instanceof JsonPrimitive);
    assertEquals('t', actualGetResult.getAsCharacter());
    assertFalse(actualGetResult.isJsonArray());
    assertFalse(actualGetResult.isJsonNull());
    assertFalse(actualGetResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualGetResult).isNumber());
    assertFalse(((JsonPrimitive) actualGetResult).isString());
    assertTrue(actualGetResult.getAsBoolean());
    assertTrue(actualGetResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualGetResult).isBoolean());
    String expectedAsString = Boolean.TRUE.toString();
    assertEquals(expectedAsString, actualGetResult.getAsString());
    assertSame(actualGetResult, actualGetResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonArray#getAsNumber()} */
  @Test
  public void testGetAsNumber() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new JsonArray(3)).getAsNumber());
  }

  /** Method under test: {@link JsonArray#getAsNumber()} */
  @Test
  public void testGetAsNumber2() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add('\u0001');

    // Act
    Number actualAsNumber = jsonArray.getAsNumber();

    // Assert
    assertTrue(actualAsNumber instanceof LazilyParsedNumber);
    assertEquals("\u0001", actualAsNumber.toString());
  }

  /** Method under test: {@link JsonArray#getAsNumber()} */
  @Test
  public void testGetAsNumber3() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    Integer number = Integer.valueOf(1);
    jsonArray.add(number);

    // Act and Assert
    assertSame(number, jsonArray.getAsNumber());
  }

  /** Method under test: {@link JsonArray#getAsNumber()} */
  @Test
  public void testGetAsNumber4() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new JsonArray(3));

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsNumber());
  }

  /** Method under test: {@link JsonArray#getAsString()} */
  @Test
  public void testGetAsString() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new JsonArray(3)).getAsString());
  }

  /** Method under test: {@link JsonArray#getAsString()} */
  @Test
  public void testGetAsString2() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(true);

    // Act
    String actualAsString = jsonArray.getAsString();

    // Assert
    assertEquals(Boolean.TRUE.toString(), actualAsString);
  }

  /** Method under test: {@link JsonArray#getAsString()} */
  @Test
  public void testGetAsString3() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add('\u0001');

    // Act and Assert
    assertEquals("\u0001", jsonArray.getAsString());
  }

  /** Method under test: {@link JsonArray#getAsString()} */
  @Test
  public void testGetAsString4() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(Integer.valueOf(1));

    // Act and Assert
    assertEquals("1", jsonArray.getAsString());
  }

  /** Method under test: {@link JsonArray#getAsString()} */
  @Test
  public void testGetAsString5() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new JsonArray(3));

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsString());
  }

  /** Method under test: {@link JsonArray#getAsDouble()} */
  @Test
  public void testGetAsDouble() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new JsonArray(3)).getAsDouble());
  }

  /** Method under test: {@link JsonArray#getAsDouble()} */
  @Test
  public void testGetAsDouble2() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(Integer.valueOf(1));

    // Act and Assert
    assertEquals(1.0d, jsonArray.getAsDouble(), 0.0);
  }

  /** Method under test: {@link JsonArray#getAsDouble()} */
  @Test
  public void testGetAsDouble3() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add("42");

    // Act and Assert
    assertEquals(42.0d, jsonArray.getAsDouble(), 0.0);
  }

  /** Method under test: {@link JsonArray#getAsDouble()} */
  @Test
  public void testGetAsDouble4() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new JsonArray(3));

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsDouble());
  }

  /** Method under test: {@link JsonArray#getAsBigDecimal()} */
  @Test
  public void testGetAsBigDecimal() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new JsonArray(3)).getAsBigDecimal());
  }

  /** Method under test: {@link JsonArray#getAsBigDecimal()} */
  @Test
  public void testGetAsBigDecimal2() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(Integer.valueOf(1));

    // Act
    BigDecimal actualAsBigDecimal = jsonArray.getAsBigDecimal();

    // Assert
    assertEquals(new BigDecimal("1"), actualAsBigDecimal);
  }

  /** Method under test: {@link JsonArray#getAsBigDecimal()} */
  @Test
  public void testGetAsBigDecimal3() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new JsonArray(3));

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsBigDecimal());
  }

  /** Method under test: {@link JsonArray#getAsBigInteger()} */
  @Test
  public void testGetAsBigInteger() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new JsonArray(3)).getAsBigInteger());
  }

  /** Method under test: {@link JsonArray#getAsBigInteger()} */
  @Test
  public void testGetAsBigInteger2() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(Integer.valueOf(1));

    // Act
    BigInteger actualAsBigInteger = jsonArray.getAsBigInteger();

    // Assert
    Iterator<JsonElement> iteratorResult = jsonArray.iterator();
    JsonElement nextResult = iteratorResult.next();
    assertTrue(nextResult instanceof JsonPrimitive);
    assertFalse(iteratorResult.hasNext());
    BigInteger bigInteger = actualAsBigInteger.ONE;
    assertSame(bigInteger, actualAsBigInteger);
    assertSame(bigInteger, nextResult.getAsBigInteger());
  }

  /** Method under test: {@link JsonArray#getAsBigInteger()} */
  @Test
  public void testGetAsBigInteger3() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add("42");

    // Act
    BigInteger actualAsBigInteger = jsonArray.getAsBigInteger();

    // Assert
    Iterator<JsonElement> iteratorResult = jsonArray.iterator();
    assertTrue(iteratorResult.next() instanceof JsonPrimitive);
    assertEquals("42", actualAsBigInteger.toString());
    assertEquals(1, actualAsBigInteger.getLowestSetBit());
    assertEquals(1, actualAsBigInteger.signum());
    assertFalse(iteratorResult.hasNext());
    assertArrayEquals(new byte[] {'*'}, actualAsBigInteger.toByteArray());
  }

  /** Method under test: {@link JsonArray#getAsBigInteger()} */
  @Test
  public void testGetAsBigInteger4() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add((byte) 'A');

    // Act
    BigInteger actualAsBigInteger = jsonArray.getAsBigInteger();

    // Assert
    Iterator<JsonElement> iteratorResult = jsonArray.iterator();
    assertTrue(iteratorResult.next() instanceof JsonPrimitive);
    assertEquals("65", actualAsBigInteger.toString());
    assertEquals(0, actualAsBigInteger.getLowestSetBit());
    assertEquals(1, actualAsBigInteger.signum());
    assertFalse(iteratorResult.hasNext());
    assertArrayEquals(new byte[] {'A'}, actualAsBigInteger.toByteArray());
  }

  /** Method under test: {@link JsonArray#getAsBigInteger()} */
  @Test
  public void testGetAsBigInteger5() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(1L);

    // Act
    BigInteger actualAsBigInteger = jsonArray.getAsBigInteger();

    // Assert
    Iterator<JsonElement> iteratorResult = jsonArray.iterator();
    JsonElement nextResult = iteratorResult.next();
    assertTrue(nextResult instanceof JsonPrimitive);
    assertFalse(iteratorResult.hasNext());
    BigInteger bigInteger = actualAsBigInteger.ONE;
    assertSame(bigInteger, actualAsBigInteger);
    assertSame(bigInteger, nextResult.getAsBigInteger());
  }

  /** Method under test: {@link JsonArray#getAsBigInteger()} */
  @Test
  public void testGetAsBigInteger6() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add((short) 1);

    // Act
    BigInteger actualAsBigInteger = jsonArray.getAsBigInteger();

    // Assert
    Iterator<JsonElement> iteratorResult = jsonArray.iterator();
    JsonElement nextResult = iteratorResult.next();
    assertTrue(nextResult instanceof JsonPrimitive);
    assertFalse(iteratorResult.hasNext());
    BigInteger bigInteger = actualAsBigInteger.ONE;
    assertSame(bigInteger, actualAsBigInteger);
    assertSame(bigInteger, nextResult.getAsBigInteger());
  }

  /** Method under test: {@link JsonArray#getAsBigInteger()} */
  @Test
  public void testGetAsBigInteger7() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new JsonArray(3));

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsBigInteger());
  }

  /** Method under test: {@link JsonArray#getAsFloat()} */
  @Test
  public void testGetAsFloat() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new JsonArray(3)).getAsFloat());
  }

  /** Method under test: {@link JsonArray#getAsFloat()} */
  @Test
  public void testGetAsFloat2() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(Integer.valueOf(1));

    // Act and Assert
    assertEquals(1.0f, jsonArray.getAsFloat(), 0.0f);
  }

  /** Method under test: {@link JsonArray#getAsFloat()} */
  @Test
  public void testGetAsFloat3() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add("42");

    // Act and Assert
    assertEquals(42.0f, jsonArray.getAsFloat(), 0.0f);
  }

  /** Method under test: {@link JsonArray#getAsFloat()} */
  @Test
  public void testGetAsFloat4() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new JsonArray(3));

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsFloat());
  }

  /** Method under test: {@link JsonArray#getAsLong()} */
  @Test
  public void testGetAsLong() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new JsonArray(3)).getAsLong());
  }

  /** Method under test: {@link JsonArray#getAsLong()} */
  @Test
  public void testGetAsLong2() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(Integer.valueOf(1));

    // Act and Assert
    assertEquals(1L, jsonArray.getAsLong());
  }

  /** Method under test: {@link JsonArray#getAsLong()} */
  @Test
  public void testGetAsLong3() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add("42");

    // Act and Assert
    assertEquals(42L, jsonArray.getAsLong());
  }

  /** Method under test: {@link JsonArray#getAsLong()} */
  @Test
  public void testGetAsLong4() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new JsonArray(3));

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsLong());
  }

  /** Method under test: {@link JsonArray#getAsInt()} */
  @Test
  public void testGetAsInt() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new JsonArray(3)).getAsInt());
  }

  /** Method under test: {@link JsonArray#getAsInt()} */
  @Test
  public void testGetAsInt2() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    Integer number = Integer.valueOf(1);
    jsonArray.add(number);

    // Act
    int actualAsInt = jsonArray.getAsInt();

    // Assert
    assertEquals(1, actualAsInt);
    assertSame(number, actualAsInt);
  }

  /** Method under test: {@link JsonArray#getAsInt()} */
  @Test
  public void testGetAsInt3() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add("42");

    // Act and Assert
    assertEquals(42, jsonArray.getAsInt());
  }

  /** Method under test: {@link JsonArray#getAsInt()} */
  @Test
  public void testGetAsInt4() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new JsonArray(3));

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsInt());
  }

  /** Method under test: {@link JsonArray#getAsByte()} */
  @Test
  public void testGetAsByte() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new JsonArray(3)).getAsByte());
  }

  /** Method under test: {@link JsonArray#getAsByte()} */
  @Test
  public void testGetAsByte2() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(Integer.valueOf(1));

    // Act and Assert
    assertEquals((byte) 1, jsonArray.getAsByte());
  }

  /** Method under test: {@link JsonArray#getAsByte()} */
  @Test
  public void testGetAsByte3() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add("42");

    // Act and Assert
    assertEquals('*', jsonArray.getAsByte());
  }

  /** Method under test: {@link JsonArray#getAsByte()} */
  @Test
  public void testGetAsByte4() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new JsonArray(3));

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsByte());
  }

  /** Method under test: {@link JsonArray#getAsCharacter()} */
  @Test
  public void testGetAsCharacter() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new JsonArray(3)).getAsCharacter());
  }

  /** Method under test: {@link JsonArray#getAsCharacter()} */
  @Test
  public void testGetAsCharacter2() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(true);

    // Act and Assert
    assertEquals('t', jsonArray.getAsCharacter());
  }

  /** Method under test: {@link JsonArray#getAsCharacter()} */
  @Test
  public void testGetAsCharacter3() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add('\u0001');

    // Act and Assert
    assertEquals('\u0001', jsonArray.getAsCharacter());
  }

  /** Method under test: {@link JsonArray#getAsCharacter()} */
  @Test
  public void testGetAsCharacter4() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(Integer.valueOf(1));

    // Act and Assert
    assertEquals('1', jsonArray.getAsCharacter());
  }

  /** Method under test: {@link JsonArray#getAsCharacter()} */
  @Test
  public void testGetAsCharacter5() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new JsonArray(3));

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsCharacter());
  }

  /** Method under test: {@link JsonArray#getAsShort()} */
  @Test
  public void testGetAsShort() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new JsonArray(3)).getAsShort());
  }

  /** Method under test: {@link JsonArray#getAsShort()} */
  @Test
  public void testGetAsShort2() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(Integer.valueOf(1));

    // Act and Assert
    assertEquals((short) 1, jsonArray.getAsShort());
  }

  /** Method under test: {@link JsonArray#getAsShort()} */
  @Test
  public void testGetAsShort3() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add("42");

    // Act and Assert
    assertEquals((short) 42, jsonArray.getAsShort());
  }

  /** Method under test: {@link JsonArray#getAsShort()} */
  @Test
  public void testGetAsShort4() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new JsonArray(3));

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsShort());
  }

  /** Method under test: {@link JsonArray#getAsBoolean()} */
  @Test
  public void testGetAsBoolean() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new JsonArray(3)).getAsBoolean());
  }

  /** Method under test: {@link JsonArray#getAsBoolean()} */
  @Test
  public void testGetAsBoolean2() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(true);

    // Act and Assert
    assertTrue(jsonArray.getAsBoolean());
  }

  /** Method under test: {@link JsonArray#getAsBoolean()} */
  @Test
  public void testGetAsBoolean3() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add('\u0001');

    // Act and Assert
    assertFalse(jsonArray.getAsBoolean());
  }

  /** Method under test: {@link JsonArray#getAsBoolean()} */
  @Test
  public void testGetAsBoolean4() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(Integer.valueOf(1));

    // Act and Assert
    assertFalse(jsonArray.getAsBoolean());
  }

  /** Method under test: {@link JsonArray#getAsBoolean()} */
  @Test
  public void testGetAsBoolean5() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new JsonArray(3));

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsBoolean());
  }

  /** Method under test: {@link JsonArray#asList()} */
  @Test
  public void testAsList() {
    // Arrange, Act and Assert
    assertTrue((new JsonArray(3)).asList().isEmpty());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link JsonArray#equals(Object)}
   *   <li>{@link JsonArray#hashCode()}
   * </ul>
   */
  @Test
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    JsonArray jsonArray2 = new JsonArray(3);

    // Act and Assert
    assertEquals(jsonArray, jsonArray2);
    int expectedHashCodeResult = jsonArray.hashCode();
    assertEquals(expectedHashCodeResult, jsonArray2.hashCode());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link JsonArray#equals(Object)}
   *   <li>{@link JsonArray#hashCode()}
   * </ul>
   */
  @Test
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);

    // Act and Assert
    assertEquals(jsonArray, jsonArray);
    int expectedHashCodeResult = jsonArray.hashCode();
    assertEquals(expectedHashCodeResult, jsonArray.hashCode());
  }

  /** Method under test: {@link JsonArray#equals(Object)} */
  @Test
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(true);

    // Act and Assert
    assertNotEquals(jsonArray, new JsonArray(3));
  }

  /** Method under test: {@link JsonArray#equals(Object)} */
  @Test
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new JsonArray(3), null);
  }

  /** Method under test: {@link JsonArray#equals(Object)} */
  @Test
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new JsonArray(3), "Different type to JsonArray");
  }

  /** Method under test: {@link JsonArray#JsonArray()} */
  @Test
  public void testNewJsonArray() {
    // Arrange and Act
    JsonArray actualJsonArray = new JsonArray();

    // Assert
    assertEquals(0, actualJsonArray.size());
    assertFalse(actualJsonArray.isJsonNull());
    assertFalse(actualJsonArray.isJsonObject());
    assertFalse(actualJsonArray.isJsonPrimitive());
    assertFalse(actualJsonArray.iterator().hasNext());
    assertTrue(actualJsonArray.isEmpty());
    assertTrue(actualJsonArray.isJsonArray());
    assertSame(actualJsonArray, actualJsonArray.getAsJsonArray());
  }

  /** Method under test: {@link JsonArray#JsonArray(int)} */
  @Test
  public void testNewJsonArray2() {
    // Arrange and Act
    JsonArray actualJsonArray = new JsonArray(3);

    // Assert
    assertEquals(0, actualJsonArray.size());
    assertFalse(actualJsonArray.isJsonNull());
    assertFalse(actualJsonArray.isJsonObject());
    assertFalse(actualJsonArray.isJsonPrimitive());
    assertFalse(actualJsonArray.iterator().hasNext());
    assertTrue(actualJsonArray.isEmpty());
    assertTrue(actualJsonArray.isJsonArray());
    assertSame(actualJsonArray, actualJsonArray.getAsJsonArray());
  }
}
