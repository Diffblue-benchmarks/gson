package com.google.gson;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import com.google.gson.internal.LazilyParsedNumber;
import java.io.CharArrayReader;
import java.io.FileDescriptor;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.junit.Test;

public class JsonStreamParserDiffblueTest {
  /** Method under test: {@link JsonStreamParser#next()} */
  @Test
  public void testNext() throws JsonParseException {
    // Arrange
    JsonStreamParser jsonStreamParser = new JsonStreamParser("Json");

    // Act
    JsonElement actualNextResult = jsonStreamParser.next();

    // Assert
    assertTrue(actualNextResult instanceof JsonPrimitive);
    Number asNumber = actualNextResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("Json", actualNextResult.getAsString());
    assertEquals("Json", asNumber.toString());
    assertEquals('J', actualNextResult.getAsCharacter());
    assertFalse(actualNextResult.getAsBoolean());
    assertFalse(actualNextResult.isJsonArray());
    assertFalse(actualNextResult.isJsonNull());
    assertFalse(actualNextResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualNextResult).isBoolean());
    assertFalse(((JsonPrimitive) actualNextResult).isNumber());
    assertFalse(jsonStreamParser.hasNext());
    assertTrue(actualNextResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualNextResult).isString());
    assertSame(actualNextResult, actualNextResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonStreamParser#next()} */
  @Test
  public void testNext2() throws JsonParseException {
    // Arrange
    JsonStreamParser jsonStreamParser = new JsonStreamParser("42");

    // Act
    JsonElement actualNextResult = jsonStreamParser.next();

    // Assert
    assertTrue(actualNextResult instanceof JsonPrimitive);
    Number asNumber = actualNextResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("42", actualNextResult.getAsString());
    assertEquals("42", asNumber.toString());
    BigInteger asBigInteger = actualNextResult.getAsBigInteger();
    assertEquals("42", asBigInteger.toString());
    assertEquals('4', actualNextResult.getAsCharacter());
    assertEquals(1, asBigInteger.getLowestSetBit());
    assertEquals(1, asBigInteger.signum());
    assertEquals(42, actualNextResult.getAsInt());
    assertEquals(42.0d, actualNextResult.getAsDouble(), 0.0);
    assertEquals(42.0f, actualNextResult.getAsFloat(), 0.0f);
    assertEquals(42L, actualNextResult.getAsLong());
    assertEquals((short) 42, actualNextResult.getAsShort());
    assertFalse(actualNextResult.getAsBoolean());
    assertFalse(actualNextResult.isJsonArray());
    assertFalse(actualNextResult.isJsonNull());
    assertFalse(actualNextResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualNextResult).isBoolean());
    assertFalse(((JsonPrimitive) actualNextResult).isString());
    assertFalse(jsonStreamParser.hasNext());
    assertTrue(actualNextResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualNextResult).isNumber());
    BigDecimal expectedAsBigDecimal = new BigDecimal("42");
    assertEquals(expectedAsBigDecimal, actualNextResult.getAsBigDecimal());
    assertEquals('*', actualNextResult.getAsByte());
    assertSame(actualNextResult, actualNextResult.getAsJsonPrimitive());
    assertArrayEquals(new byte[] {'*'}, asBigInteger.toByteArray());
  }

  /** Method under test: {@link JsonStreamParser#next()} */
  @Test
  public void testNext3() throws JsonParseException {
    // Arrange, Act and Assert
    assertThrows(JsonIOException.class, () -> (new JsonStreamParser("")).next());
  }

  /** Method under test: {@link JsonStreamParser#next()} */
  @Test
  public void testNext4() throws JsonParseException {
    // Arrange
    JsonStreamParser jsonStreamParser = new JsonStreamParser(new StringReader("foo"));

    // Act
    JsonElement actualNextResult = jsonStreamParser.next();

    // Assert
    assertTrue(actualNextResult instanceof JsonPrimitive);
    Number asNumber = actualNextResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("foo", actualNextResult.getAsString());
    assertEquals("foo", asNumber.toString());
    assertEquals('f', actualNextResult.getAsCharacter());
    assertFalse(actualNextResult.getAsBoolean());
    assertFalse(actualNextResult.isJsonArray());
    assertFalse(actualNextResult.isJsonNull());
    assertFalse(actualNextResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualNextResult).isBoolean());
    assertFalse(((JsonPrimitive) actualNextResult).isNumber());
    assertFalse(jsonStreamParser.hasNext());
    assertTrue(actualNextResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualNextResult).isString());
    assertSame(actualNextResult, actualNextResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonStreamParser#next()} */
  @Test
  public void testNext5() throws JsonParseException {
    // Arrange
    JsonStreamParser jsonStreamParser =
        new JsonStreamParser(new StringReader(Boolean.FALSE.toString()));

    // Act
    JsonElement actualNextResult = jsonStreamParser.next();

    // Assert
    assertTrue(actualNextResult instanceof JsonPrimitive);
    assertEquals('f', actualNextResult.getAsCharacter());
    assertFalse(actualNextResult.getAsBoolean());
    assertFalse(actualNextResult.isJsonArray());
    assertFalse(actualNextResult.isJsonNull());
    assertFalse(actualNextResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualNextResult).isNumber());
    assertFalse(((JsonPrimitive) actualNextResult).isString());
    assertFalse(jsonStreamParser.hasNext());
    assertTrue(actualNextResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualNextResult).isBoolean());
    String expectedAsString = Boolean.FALSE.toString();
    assertEquals(expectedAsString, actualNextResult.getAsString());
    assertSame(actualNextResult, actualNextResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonStreamParser#next()} */
  @Test
  public void testNext6() throws JsonParseException {
    // Arrange
    JsonStreamParser jsonStreamParser = new JsonStreamParser(new StringReader("FALSE"));

    // Act
    JsonElement actualNextResult = jsonStreamParser.next();

    // Assert
    assertTrue(actualNextResult instanceof JsonPrimitive);
    assertEquals('f', actualNextResult.getAsCharacter());
    assertFalse(actualNextResult.getAsBoolean());
    assertFalse(actualNextResult.isJsonArray());
    assertFalse(actualNextResult.isJsonNull());
    assertFalse(actualNextResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualNextResult).isNumber());
    assertFalse(((JsonPrimitive) actualNextResult).isString());
    assertFalse(jsonStreamParser.hasNext());
    assertTrue(actualNextResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualNextResult).isBoolean());
    String expectedAsString = Boolean.FALSE.toString();
    assertEquals(expectedAsString, actualNextResult.getAsString());
    assertSame(actualNextResult, actualNextResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonStreamParser#next()} */
  @Test
  public void testNext7() throws JsonParseException {
    // Arrange
    JsonStreamParser jsonStreamParser = new JsonStreamParser(new StringReader("falsefalse"));

    // Act
    JsonElement actualNextResult = jsonStreamParser.next();

    // Assert
    assertTrue(actualNextResult instanceof JsonPrimitive);
    Number asNumber = actualNextResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("falsefalse", actualNextResult.getAsString());
    assertEquals("falsefalse", asNumber.toString());
    assertEquals('f', actualNextResult.getAsCharacter());
    assertFalse(actualNextResult.getAsBoolean());
    assertFalse(actualNextResult.isJsonArray());
    assertFalse(actualNextResult.isJsonNull());
    assertFalse(actualNextResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualNextResult).isBoolean());
    assertFalse(((JsonPrimitive) actualNextResult).isNumber());
    assertFalse(jsonStreamParser.hasNext());
    assertTrue(actualNextResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualNextResult).isString());
    assertSame(actualNextResult, actualNextResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonStreamParser#next()} */
  @Test
  public void testNext8() throws JsonParseException {
    // Arrange, Act and Assert
    assertThrows(
        JsonIOException.class,
        () -> (new JsonStreamParser(new FileReader(new FileDescriptor()))).next());
  }

  /** Method under test: {@link JsonStreamParser#hasNext()} */
  @Test
  public void testHasNext() {
    // Arrange, Act and Assert
    assertTrue((new JsonStreamParser("Json")).hasNext());
    assertTrue((new JsonStreamParser("42")).hasNext());
    assertThrows(JsonIOException.class, () -> (new JsonStreamParser("")).hasNext());
    assertTrue((new JsonStreamParser(new StringReader("foo"))).hasNext());
    assertTrue((new JsonStreamParser(new StringReader(Boolean.FALSE.toString()))).hasNext());
    assertTrue((new JsonStreamParser(new StringReader("FALSE"))).hasNext());
    assertTrue(
        (new JsonStreamParser(new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray())))
            .hasNext());
    assertThrows(
        JsonIOException.class,
        () -> (new JsonStreamParser(new FileReader(new FileDescriptor()))).hasNext());
    assertTrue(
        (new JsonStreamParser(new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray()))).hasNext());
    assertTrue(
        (new JsonStreamParser(new CharArrayReader("\n\u0006\u0001\u0006".toCharArray())))
            .hasNext());
  }

  /** Method under test: {@link JsonStreamParser#remove()} */
  @Test
  public void testRemove() {
    // Arrange, Act and Assert
    assertThrows(
        UnsupportedOperationException.class, () -> (new JsonStreamParser("Json")).remove());
  }

  /** Method under test: {@link JsonStreamParser#JsonStreamParser(Reader)} */
  @Test
  public void testNewJsonStreamParser() throws JsonParseException {
    // Arrange and Act
    JsonStreamParser actualJsonStreamParser = new JsonStreamParser(new StringReader("foo"));

    // Assert
    JsonElement nextResult = actualJsonStreamParser.next();
    assertTrue(nextResult instanceof JsonPrimitive);
    Number asNumber = nextResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("foo", nextResult.getAsString());
    assertEquals("foo", asNumber.toString());
    assertEquals('f', nextResult.getAsCharacter());
    assertFalse(nextResult.getAsBoolean());
    assertFalse(nextResult.isJsonArray());
    assertFalse(nextResult.isJsonNull());
    assertFalse(nextResult.isJsonObject());
    assertFalse(((JsonPrimitive) nextResult).isBoolean());
    assertFalse(((JsonPrimitive) nextResult).isNumber());
    assertFalse(actualJsonStreamParser.hasNext());
    assertTrue(nextResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) nextResult).isString());
    assertSame(nextResult, nextResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonStreamParser#JsonStreamParser(String)} */
  @Test
  public void testNewJsonStreamParser2() throws JsonParseException {
    // Arrange and Act
    JsonStreamParser actualJsonStreamParser = new JsonStreamParser("Json");

    // Assert
    JsonElement nextResult = actualJsonStreamParser.next();
    assertTrue(nextResult instanceof JsonPrimitive);
    Number asNumber = nextResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("Json", nextResult.getAsString());
    assertEquals("Json", asNumber.toString());
    assertEquals('J', nextResult.getAsCharacter());
    assertFalse(nextResult.getAsBoolean());
    assertFalse(nextResult.isJsonArray());
    assertFalse(nextResult.isJsonNull());
    assertFalse(nextResult.isJsonObject());
    assertFalse(((JsonPrimitive) nextResult).isBoolean());
    assertFalse(((JsonPrimitive) nextResult).isNumber());
    assertFalse(actualJsonStreamParser.hasNext());
    assertTrue(nextResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) nextResult).isString());
    assertSame(nextResult, nextResult.getAsJsonPrimitive());
  }
}
