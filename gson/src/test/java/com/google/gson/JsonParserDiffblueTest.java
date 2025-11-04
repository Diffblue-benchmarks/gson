package com.google.gson;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.stream.JsonReader;
import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.junit.Test;

public class JsonParserDiffblueTest {
  /** Method under test: {@link JsonParser#parseString(String)} */
  @Test
  public void testParseString() throws JsonSyntaxException {
    // Arrange and Act
    JsonElement actualParseStringResult = JsonParser.parseString("Json");

    // Assert
    assertTrue(actualParseStringResult instanceof JsonPrimitive);
    Number asNumber = actualParseStringResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("Json", actualParseStringResult.getAsString());
    assertEquals("Json", asNumber.toString());
    assertEquals('J', actualParseStringResult.getAsCharacter());
    assertFalse(actualParseStringResult.getAsBoolean());
    assertFalse(actualParseStringResult.isJsonArray());
    assertFalse(actualParseStringResult.isJsonNull());
    assertFalse(actualParseStringResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualParseStringResult).isBoolean());
    assertFalse(((JsonPrimitive) actualParseStringResult).isNumber());
    assertTrue(actualParseStringResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualParseStringResult).isString());
    assertSame(actualParseStringResult, actualParseStringResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonParser#parseString(String)} */
  @Test
  public void testParseString2() throws JsonSyntaxException {
    // Arrange, Act and Assert
    assertThrows(JsonSyntaxException.class, () -> JsonParser.parseString("in == null"));
  }

  /** Method under test: {@link JsonParser#parseString(String)} */
  @Test
  public void testParseString3() throws JsonSyntaxException {
    // Arrange and Act
    JsonElement actualParseStringResult = JsonParser.parseString("42");

    // Assert
    assertTrue(actualParseStringResult instanceof JsonPrimitive);
    Number asNumber = actualParseStringResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("42", actualParseStringResult.getAsString());
    assertEquals("42", asNumber.toString());
    BigInteger asBigInteger = actualParseStringResult.getAsBigInteger();
    assertEquals("42", asBigInteger.toString());
    assertEquals('4', actualParseStringResult.getAsCharacter());
    assertEquals(1, asBigInteger.getLowestSetBit());
    assertEquals(1, asBigInteger.signum());
    assertEquals(42, actualParseStringResult.getAsInt());
    assertEquals(42.0d, actualParseStringResult.getAsDouble(), 0.0);
    assertEquals(42.0f, actualParseStringResult.getAsFloat(), 0.0f);
    assertEquals(42L, actualParseStringResult.getAsLong());
    assertEquals((short) 42, actualParseStringResult.getAsShort());
    assertFalse(actualParseStringResult.getAsBoolean());
    assertFalse(actualParseStringResult.isJsonArray());
    assertFalse(actualParseStringResult.isJsonNull());
    assertFalse(actualParseStringResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualParseStringResult).isBoolean());
    assertFalse(((JsonPrimitive) actualParseStringResult).isString());
    assertTrue(actualParseStringResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualParseStringResult).isNumber());
    BigDecimal expectedAsBigDecimal = new BigDecimal("42");
    assertEquals(expectedAsBigDecimal, actualParseStringResult.getAsBigDecimal());
    assertEquals('*', actualParseStringResult.getAsByte());
    assertSame(actualParseStringResult, actualParseStringResult.getAsJsonPrimitive());
    assertArrayEquals(new byte[] {'*'}, asBigInteger.toByteArray());
  }

  /** Method under test: {@link JsonParser#parseString(String)} */
  @Test
  public void testParseString4() throws JsonSyntaxException {
    // Arrange and Act
    JsonElement actualParseStringResult = JsonParser.parseString("");

    // Assert
    assertTrue(actualParseStringResult instanceof JsonNull);
    assertFalse(actualParseStringResult.isJsonArray());
    assertFalse(actualParseStringResult.isJsonObject());
    assertFalse(actualParseStringResult.isJsonPrimitive());
    assertTrue(actualParseStringResult.isJsonNull());
    JsonNull expectedAsJsonNull = ((JsonNull) actualParseStringResult).INSTANCE;
    assertSame(expectedAsJsonNull, actualParseStringResult.getAsJsonNull());
  }

  /** Method under test: {@link JsonParser#parseReader(JsonReader)} */
  @Test
  public void testParseReader() throws JsonIOException, JsonSyntaxException {
    // Arrange and Act
    JsonElement actualParseReaderResult =
        JsonParser.parseReader(new JsonReader(new StringReader("foo")));

    // Assert
    assertTrue(actualParseReaderResult instanceof JsonPrimitive);
    Number asNumber = actualParseReaderResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("foo", actualParseReaderResult.getAsString());
    assertEquals("foo", asNumber.toString());
    assertEquals('f', actualParseReaderResult.getAsCharacter());
    assertFalse(actualParseReaderResult.getAsBoolean());
    assertFalse(actualParseReaderResult.isJsonArray());
    assertFalse(actualParseReaderResult.isJsonNull());
    assertFalse(actualParseReaderResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualParseReaderResult).isBoolean());
    assertFalse(((JsonPrimitive) actualParseReaderResult).isNumber());
    assertTrue(actualParseReaderResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualParseReaderResult).isString());
    assertSame(actualParseReaderResult, actualParseReaderResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonParser#parseReader(JsonReader)} */
  @Test
  public void testParseReader2() throws JsonIOException, JsonSyntaxException {
    // Arrange and Act
    JsonElement actualParseReaderResult =
        JsonParser.parseReader(new JsonReader(new StringReader(Boolean.FALSE.toString())));

    // Assert
    assertTrue(actualParseReaderResult instanceof JsonPrimitive);
    assertEquals('f', actualParseReaderResult.getAsCharacter());
    assertFalse(actualParseReaderResult.getAsBoolean());
    assertFalse(actualParseReaderResult.isJsonArray());
    assertFalse(actualParseReaderResult.isJsonNull());
    assertFalse(actualParseReaderResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualParseReaderResult).isNumber());
    assertFalse(((JsonPrimitive) actualParseReaderResult).isString());
    assertTrue(actualParseReaderResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualParseReaderResult).isBoolean());
    String expectedAsString = Boolean.FALSE.toString();
    assertEquals(expectedAsString, actualParseReaderResult.getAsString());
    assertSame(actualParseReaderResult, actualParseReaderResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonParser#parseReader(JsonReader)} */
  @Test
  public void testParseReader3() throws JsonIOException, JsonSyntaxException {
    // Arrange and Act
    JsonElement actualParseReaderResult =
        JsonParser.parseReader(new JsonReader(new StringReader("FALSE")));

    // Assert
    assertTrue(actualParseReaderResult instanceof JsonPrimitive);
    assertEquals('f', actualParseReaderResult.getAsCharacter());
    assertFalse(actualParseReaderResult.getAsBoolean());
    assertFalse(actualParseReaderResult.isJsonArray());
    assertFalse(actualParseReaderResult.isJsonNull());
    assertFalse(actualParseReaderResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualParseReaderResult).isNumber());
    assertFalse(((JsonPrimitive) actualParseReaderResult).isString());
    assertTrue(actualParseReaderResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualParseReaderResult).isBoolean());
    String expectedAsString = Boolean.FALSE.toString();
    assertEquals(expectedAsString, actualParseReaderResult.getAsString());
    assertSame(actualParseReaderResult, actualParseReaderResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonParser#parseReader(JsonReader)} */
  @Test
  public void testParseReader4() throws JsonIOException, JsonSyntaxException {
    // Arrange and Act
    JsonElement actualParseReaderResult =
        JsonParser.parseReader(new JsonReader(new StringReader("42")));

    // Assert
    assertTrue(actualParseReaderResult instanceof JsonPrimitive);
    Number asNumber = actualParseReaderResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("42", actualParseReaderResult.getAsString());
    assertEquals("42", asNumber.toString());
    BigInteger asBigInteger = actualParseReaderResult.getAsBigInteger();
    assertEquals("42", asBigInteger.toString());
    assertEquals('4', actualParseReaderResult.getAsCharacter());
    assertEquals(1, asBigInteger.getLowestSetBit());
    assertEquals(1, asBigInteger.signum());
    assertEquals(42, actualParseReaderResult.getAsInt());
    assertEquals(42.0d, actualParseReaderResult.getAsDouble(), 0.0);
    assertEquals(42.0f, actualParseReaderResult.getAsFloat(), 0.0f);
    assertEquals(42L, actualParseReaderResult.getAsLong());
    assertEquals((short) 42, actualParseReaderResult.getAsShort());
    assertFalse(actualParseReaderResult.getAsBoolean());
    assertFalse(actualParseReaderResult.isJsonArray());
    assertFalse(actualParseReaderResult.isJsonNull());
    assertFalse(actualParseReaderResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualParseReaderResult).isBoolean());
    assertFalse(((JsonPrimitive) actualParseReaderResult).isString());
    assertTrue(actualParseReaderResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualParseReaderResult).isNumber());
    BigDecimal expectedAsBigDecimal = new BigDecimal("42");
    assertEquals(expectedAsBigDecimal, actualParseReaderResult.getAsBigDecimal());
    assertEquals('*', actualParseReaderResult.getAsByte());
    assertSame(actualParseReaderResult, actualParseReaderResult.getAsJsonPrimitive());
    assertArrayEquals(new byte[] {'*'}, asBigInteger.toByteArray());
  }

  /** Method under test: {@link JsonParser#parseReader(JsonReader)} */
  @Test
  public void testParseReader5() throws JsonIOException, JsonSyntaxException {
    // Arrange and Act
    JsonElement actualParseReaderResult =
        JsonParser.parseReader(new JsonReader(new StringReader("")));

    // Assert
    assertTrue(actualParseReaderResult instanceof JsonNull);
    assertFalse(actualParseReaderResult.isJsonArray());
    assertFalse(actualParseReaderResult.isJsonObject());
    assertFalse(actualParseReaderResult.isJsonPrimitive());
    assertTrue(actualParseReaderResult.isJsonNull());
    JsonNull expectedAsJsonNull = ((JsonNull) actualParseReaderResult).INSTANCE;
    assertSame(expectedAsJsonNull, actualParseReaderResult.getAsJsonNull());
  }

  /** Method under test: {@link JsonParser#parseReader(JsonReader)} */
  @Test
  public void testParseReader6() throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    JsonTreeReader reader = new JsonTreeReader(JsonNull.INSTANCE);

    // Act
    JsonElement actualParseReaderResult = JsonParser.parseReader(reader);

    // Assert
    assertTrue(actualParseReaderResult instanceof JsonNull);
    assertFalse(actualParseReaderResult.isJsonArray());
    assertFalse(actualParseReaderResult.isJsonObject());
    assertFalse(actualParseReaderResult.isJsonPrimitive());
    assertFalse(reader.hasNext());
    assertTrue(actualParseReaderResult.isJsonNull());
    JsonNull expectedAsJsonNull = ((JsonNull) actualParseReaderResult).INSTANCE;
    assertSame(expectedAsJsonNull, actualParseReaderResult.getAsJsonNull());
  }

  /** Method under test: {@link JsonParser#parseReader(JsonReader)} */
  @Test
  public void testParseReader7() throws JsonIOException, JsonSyntaxException {
    // Arrange and Act
    JsonElement actualParseReaderResult =
        JsonParser.parseReader(
            new JsonReader(new BufferedReader(new StringReader("End of input"), 1)));

    // Assert
    assertTrue(actualParseReaderResult instanceof JsonPrimitive);
    Number asNumber = actualParseReaderResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("End", actualParseReaderResult.getAsString());
    assertEquals("End", asNumber.toString());
    assertEquals('E', actualParseReaderResult.getAsCharacter());
    assertFalse(actualParseReaderResult.getAsBoolean());
    assertFalse(actualParseReaderResult.isJsonArray());
    assertFalse(actualParseReaderResult.isJsonNull());
    assertFalse(actualParseReaderResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualParseReaderResult).isBoolean());
    assertFalse(((JsonPrimitive) actualParseReaderResult).isNumber());
    assertTrue(actualParseReaderResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualParseReaderResult).isString());
    assertSame(actualParseReaderResult, actualParseReaderResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonParser#parseReader(JsonReader)} */
  @Test
  public void testParseReader8() throws JsonIOException, JsonSyntaxException {
    // Arrange and Act
    JsonElement actualParseReaderResult =
        JsonParser.parseReader(
            new JsonReader(new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray())));

    // Assert
    assertTrue(actualParseReaderResult instanceof JsonPrimitive);
    Number asNumber = actualParseReaderResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("\u0001\u0006\u0001\u0006", actualParseReaderResult.getAsString());
    assertEquals("\u0001\u0006\u0001\u0006", asNumber.toString());
    assertEquals('\u0001', actualParseReaderResult.getAsCharacter());
    assertFalse(actualParseReaderResult.getAsBoolean());
    assertFalse(actualParseReaderResult.isJsonArray());
    assertFalse(actualParseReaderResult.isJsonNull());
    assertFalse(actualParseReaderResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualParseReaderResult).isBoolean());
    assertFalse(((JsonPrimitive) actualParseReaderResult).isNumber());
    assertTrue(actualParseReaderResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualParseReaderResult).isString());
    assertSame(actualParseReaderResult, actualParseReaderResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonParser#parseReader(JsonReader)} */
  @Test
  public void testParseReader9() throws JsonIOException, JsonSyntaxException {
    // Arrange
    JsonReader reader = new JsonReader(new StringReader("foo"));
    reader.setStrictness(Strictness.LENIENT);

    // Act
    JsonElement actualParseReaderResult = JsonParser.parseReader(reader);

    // Assert
    assertTrue(actualParseReaderResult instanceof JsonPrimitive);
    Number asNumber = actualParseReaderResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("foo", actualParseReaderResult.getAsString());
    assertEquals("foo", asNumber.toString());
    assertEquals('f', actualParseReaderResult.getAsCharacter());
    assertFalse(actualParseReaderResult.getAsBoolean());
    assertFalse(actualParseReaderResult.isJsonArray());
    assertFalse(actualParseReaderResult.isJsonNull());
    assertFalse(actualParseReaderResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualParseReaderResult).isBoolean());
    assertFalse(((JsonPrimitive) actualParseReaderResult).isNumber());
    assertTrue(actualParseReaderResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualParseReaderResult).isString());
    assertSame(actualParseReaderResult, actualParseReaderResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonParser#parseReader(JsonReader)} */
  @Test
  public void testParseReader10() throws JsonIOException, JsonSyntaxException {
    // Arrange and Act
    JsonElement actualParseReaderResult =
        JsonParser.parseReader(
            new JsonReader(new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray())));

    // Assert
    assertTrue(actualParseReaderResult instanceof JsonPrimitive);
    Number asNumber = actualParseReaderResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("\u0006\u0001\u0006", actualParseReaderResult.getAsString());
    assertEquals("\u0006\u0001\u0006", asNumber.toString());
    assertEquals('\u0006', actualParseReaderResult.getAsCharacter());
    assertFalse(actualParseReaderResult.getAsBoolean());
    assertFalse(actualParseReaderResult.isJsonArray());
    assertFalse(actualParseReaderResult.isJsonNull());
    assertFalse(actualParseReaderResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualParseReaderResult).isBoolean());
    assertFalse(((JsonPrimitive) actualParseReaderResult).isNumber());
    assertTrue(actualParseReaderResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualParseReaderResult).isString());
    assertSame(actualParseReaderResult, actualParseReaderResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonParser#parseReader(JsonReader)} */
  @Test
  public void testParseReader11() throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    StringReader stringReader = new StringReader("End of input");
    stringReader.read("\u0001\u0006\u0001\u0006".toCharArray(), 1, 1);

    // Act
    JsonElement actualParseReaderResult =
        JsonParser.parseReader(new JsonReader(new BufferedReader(stringReader, 1)));

    // Assert
    assertTrue(actualParseReaderResult instanceof JsonPrimitive);
    Number asNumber = actualParseReaderResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("nd", actualParseReaderResult.getAsString());
    assertEquals("nd", asNumber.toString());
    assertEquals('n', actualParseReaderResult.getAsCharacter());
    assertFalse(actualParseReaderResult.getAsBoolean());
    assertFalse(actualParseReaderResult.isJsonArray());
    assertFalse(actualParseReaderResult.isJsonNull());
    assertFalse(actualParseReaderResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualParseReaderResult).isBoolean());
    assertFalse(((JsonPrimitive) actualParseReaderResult).isNumber());
    assertTrue(actualParseReaderResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualParseReaderResult).isString());
    assertSame(actualParseReaderResult, actualParseReaderResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonParser#parseReader(JsonReader)} */
  @Test
  public void testParseReader12() throws JsonIOException, JsonSyntaxException {
    // Arrange
    JsonReader reader = new JsonReader(new StringReader(""));
    reader.setStrictness(Strictness.STRICT);

    // Act
    JsonElement actualParseReaderResult = JsonParser.parseReader(reader);

    // Assert
    assertTrue(actualParseReaderResult instanceof JsonNull);
    assertFalse(actualParseReaderResult.isJsonArray());
    assertFalse(actualParseReaderResult.isJsonObject());
    assertFalse(actualParseReaderResult.isJsonPrimitive());
    assertTrue(actualParseReaderResult.isJsonNull());
    JsonNull expectedAsJsonNull = ((JsonNull) actualParseReaderResult).INSTANCE;
    assertSame(expectedAsJsonNull, actualParseReaderResult.getAsJsonNull());
  }

  /** Method under test: {@link JsonParser#parseReader(Reader)} */
  @Test
  public void testParseReader13() throws JsonIOException, JsonSyntaxException {
    // Arrange and Act
    JsonElement actualParseReaderResult = JsonParser.parseReader(new StringReader("foo"));

    // Assert
    assertTrue(actualParseReaderResult instanceof JsonPrimitive);
    Number asNumber = actualParseReaderResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("foo", actualParseReaderResult.getAsString());
    assertEquals("foo", asNumber.toString());
    assertEquals('f', actualParseReaderResult.getAsCharacter());
    assertFalse(actualParseReaderResult.getAsBoolean());
    assertFalse(actualParseReaderResult.isJsonArray());
    assertFalse(actualParseReaderResult.isJsonNull());
    assertFalse(actualParseReaderResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualParseReaderResult).isBoolean());
    assertFalse(((JsonPrimitive) actualParseReaderResult).isNumber());
    assertTrue(actualParseReaderResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualParseReaderResult).isString());
    assertSame(actualParseReaderResult, actualParseReaderResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonParser#parseReader(Reader)} */
  @Test
  public void testParseReader14() throws JsonIOException, JsonSyntaxException {
    // Arrange, Act and Assert
    assertThrows(
        JsonSyntaxException.class, () -> JsonParser.parseReader(new StringReader("in == null")));
  }

  /** Method under test: {@link JsonParser#parseReader(Reader)} */
  @Test
  public void testParseReader15() throws JsonIOException, JsonSyntaxException {
    // Arrange and Act
    JsonElement actualParseReaderResult =
        JsonParser.parseReader(new StringReader(Boolean.FALSE.toString()));

    // Assert
    assertTrue(actualParseReaderResult instanceof JsonPrimitive);
    assertEquals('f', actualParseReaderResult.getAsCharacter());
    assertFalse(actualParseReaderResult.getAsBoolean());
    assertFalse(actualParseReaderResult.isJsonArray());
    assertFalse(actualParseReaderResult.isJsonNull());
    assertFalse(actualParseReaderResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualParseReaderResult).isNumber());
    assertFalse(((JsonPrimitive) actualParseReaderResult).isString());
    assertTrue(actualParseReaderResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualParseReaderResult).isBoolean());
    String expectedAsString = Boolean.FALSE.toString();
    assertEquals(expectedAsString, actualParseReaderResult.getAsString());
    assertSame(actualParseReaderResult, actualParseReaderResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonParser#parseReader(Reader)} */
  @Test
  public void testParseReader16() throws JsonIOException, JsonSyntaxException {
    // Arrange and Act
    JsonElement actualParseReaderResult = JsonParser.parseReader(new StringReader("FALSE"));

    // Assert
    assertTrue(actualParseReaderResult instanceof JsonPrimitive);
    assertEquals('f', actualParseReaderResult.getAsCharacter());
    assertFalse(actualParseReaderResult.getAsBoolean());
    assertFalse(actualParseReaderResult.isJsonArray());
    assertFalse(actualParseReaderResult.isJsonNull());
    assertFalse(actualParseReaderResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualParseReaderResult).isNumber());
    assertFalse(((JsonPrimitive) actualParseReaderResult).isString());
    assertTrue(actualParseReaderResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualParseReaderResult).isBoolean());
    String expectedAsString = Boolean.FALSE.toString();
    assertEquals(expectedAsString, actualParseReaderResult.getAsString());
    assertSame(actualParseReaderResult, actualParseReaderResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonParser#parseReader(Reader)} */
  @Test
  public void testParseReader17() throws JsonIOException, JsonSyntaxException {
    // Arrange and Act
    JsonElement actualParseReaderResult = JsonParser.parseReader(new StringReader("42"));

    // Assert
    assertTrue(actualParseReaderResult instanceof JsonPrimitive);
    Number asNumber = actualParseReaderResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("42", actualParseReaderResult.getAsString());
    assertEquals("42", asNumber.toString());
    BigInteger asBigInteger = actualParseReaderResult.getAsBigInteger();
    assertEquals("42", asBigInteger.toString());
    assertEquals('4', actualParseReaderResult.getAsCharacter());
    assertEquals(1, asBigInteger.getLowestSetBit());
    assertEquals(1, asBigInteger.signum());
    assertEquals(42, actualParseReaderResult.getAsInt());
    assertEquals(42.0d, actualParseReaderResult.getAsDouble(), 0.0);
    assertEquals(42.0f, actualParseReaderResult.getAsFloat(), 0.0f);
    assertEquals(42L, actualParseReaderResult.getAsLong());
    assertEquals((short) 42, actualParseReaderResult.getAsShort());
    assertFalse(actualParseReaderResult.getAsBoolean());
    assertFalse(actualParseReaderResult.isJsonArray());
    assertFalse(actualParseReaderResult.isJsonNull());
    assertFalse(actualParseReaderResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualParseReaderResult).isBoolean());
    assertFalse(((JsonPrimitive) actualParseReaderResult).isString());
    assertTrue(actualParseReaderResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualParseReaderResult).isNumber());
    BigDecimal expectedAsBigDecimal = new BigDecimal("42");
    assertEquals(expectedAsBigDecimal, actualParseReaderResult.getAsBigDecimal());
    assertEquals('*', actualParseReaderResult.getAsByte());
    assertSame(actualParseReaderResult, actualParseReaderResult.getAsJsonPrimitive());
    assertArrayEquals(new byte[] {'*'}, asBigInteger.toByteArray());
  }

  /** Method under test: {@link JsonParser#parseReader(Reader)} */
  @Test
  public void testParseReader18() throws JsonIOException, JsonSyntaxException {
    // Arrange and Act
    JsonElement actualParseReaderResult = JsonParser.parseReader(new StringReader(""));

    // Assert
    assertTrue(actualParseReaderResult instanceof JsonNull);
    assertFalse(actualParseReaderResult.isJsonArray());
    assertFalse(actualParseReaderResult.isJsonObject());
    assertFalse(actualParseReaderResult.isJsonPrimitive());
    assertTrue(actualParseReaderResult.isJsonNull());
    JsonNull expectedAsJsonNull = ((JsonNull) actualParseReaderResult).INSTANCE;
    assertSame(expectedAsJsonNull, actualParseReaderResult.getAsJsonNull());
  }

  /** Method under test: {@link JsonParser#parse(JsonReader)} */
  @Test
  public void testParse() throws JsonIOException, JsonSyntaxException {
    // Arrange
    JsonParser jsonParser = new JsonParser();

    // Act
    JsonElement actualParseResult = jsonParser.parse(new JsonReader(new StringReader("foo")));

    // Assert
    assertTrue(actualParseResult instanceof JsonPrimitive);
    Number asNumber = actualParseResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("foo", actualParseResult.getAsString());
    assertEquals("foo", asNumber.toString());
    assertEquals('f', actualParseResult.getAsCharacter());
    assertFalse(actualParseResult.getAsBoolean());
    assertFalse(actualParseResult.isJsonArray());
    assertFalse(actualParseResult.isJsonNull());
    assertFalse(actualParseResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualParseResult).isBoolean());
    assertFalse(((JsonPrimitive) actualParseResult).isNumber());
    assertTrue(actualParseResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualParseResult).isString());
    assertSame(actualParseResult, actualParseResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonParser#parse(JsonReader)} */
  @Test
  public void testParse2() throws JsonIOException, JsonSyntaxException {
    // Arrange
    JsonParser jsonParser = new JsonParser();

    // Act
    JsonElement actualParseResult =
        jsonParser.parse(new JsonReader(new StringReader(Boolean.FALSE.toString())));

    // Assert
    assertTrue(actualParseResult instanceof JsonPrimitive);
    assertEquals('f', actualParseResult.getAsCharacter());
    assertFalse(actualParseResult.getAsBoolean());
    assertFalse(actualParseResult.isJsonArray());
    assertFalse(actualParseResult.isJsonNull());
    assertFalse(actualParseResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualParseResult).isNumber());
    assertFalse(((JsonPrimitive) actualParseResult).isString());
    assertTrue(actualParseResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualParseResult).isBoolean());
    String expectedAsString = Boolean.FALSE.toString();
    assertEquals(expectedAsString, actualParseResult.getAsString());
    assertSame(actualParseResult, actualParseResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonParser#parse(JsonReader)} */
  @Test
  public void testParse3() throws JsonIOException, JsonSyntaxException {
    // Arrange
    JsonParser jsonParser = new JsonParser();

    // Act
    JsonElement actualParseResult = jsonParser.parse(new JsonReader(new StringReader("FALSE")));

    // Assert
    assertTrue(actualParseResult instanceof JsonPrimitive);
    assertEquals('f', actualParseResult.getAsCharacter());
    assertFalse(actualParseResult.getAsBoolean());
    assertFalse(actualParseResult.isJsonArray());
    assertFalse(actualParseResult.isJsonNull());
    assertFalse(actualParseResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualParseResult).isNumber());
    assertFalse(((JsonPrimitive) actualParseResult).isString());
    assertTrue(actualParseResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualParseResult).isBoolean());
    String expectedAsString = Boolean.FALSE.toString();
    assertEquals(expectedAsString, actualParseResult.getAsString());
    assertSame(actualParseResult, actualParseResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonParser#parse(JsonReader)} */
  @Test
  public void testParse4() throws JsonIOException, JsonSyntaxException {
    // Arrange
    JsonParser jsonParser = new JsonParser();

    // Act
    JsonElement actualParseResult = jsonParser.parse(new JsonReader(new StringReader("42")));

    // Assert
    assertTrue(actualParseResult instanceof JsonPrimitive);
    Number asNumber = actualParseResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("42", actualParseResult.getAsString());
    assertEquals("42", asNumber.toString());
    BigInteger asBigInteger = actualParseResult.getAsBigInteger();
    assertEquals("42", asBigInteger.toString());
    assertEquals('4', actualParseResult.getAsCharacter());
    assertEquals(1, asBigInteger.getLowestSetBit());
    assertEquals(1, asBigInteger.signum());
    assertEquals(42, actualParseResult.getAsInt());
    assertEquals(42.0d, actualParseResult.getAsDouble(), 0.0);
    assertEquals(42.0f, actualParseResult.getAsFloat(), 0.0f);
    assertEquals(42L, actualParseResult.getAsLong());
    assertEquals((short) 42, actualParseResult.getAsShort());
    assertFalse(actualParseResult.getAsBoolean());
    assertFalse(actualParseResult.isJsonArray());
    assertFalse(actualParseResult.isJsonNull());
    assertFalse(actualParseResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualParseResult).isBoolean());
    assertFalse(((JsonPrimitive) actualParseResult).isString());
    assertTrue(actualParseResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualParseResult).isNumber());
    BigDecimal expectedAsBigDecimal = new BigDecimal("42");
    assertEquals(expectedAsBigDecimal, actualParseResult.getAsBigDecimal());
    assertEquals('*', actualParseResult.getAsByte());
    assertSame(actualParseResult, actualParseResult.getAsJsonPrimitive());
    assertArrayEquals(new byte[] {'*'}, asBigInteger.toByteArray());
  }

  /** Method under test: {@link JsonParser#parse(JsonReader)} */
  @Test
  public void testParse5() throws JsonIOException, JsonSyntaxException {
    // Arrange
    JsonParser jsonParser = new JsonParser();

    // Act
    JsonElement actualParseResult = jsonParser.parse(new JsonReader(new StringReader("")));

    // Assert
    assertSame(((JsonNull) actualParseResult).INSTANCE, actualParseResult);
  }

  /** Method under test: {@link JsonParser#parse(JsonReader)} */
  @Test
  public void testParse6() throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    JsonParser jsonParser = new JsonParser();
    JsonTreeReader json = new JsonTreeReader(JsonNull.INSTANCE);

    // Act
    JsonElement actualParseResult = jsonParser.parse(json);

    // Assert
    assertFalse(json.hasNext());
    assertSame(((JsonNull) actualParseResult).INSTANCE, actualParseResult);
  }

  /** Method under test: {@link JsonParser#parse(JsonReader)} */
  @Test
  public void testParse7() throws JsonIOException, JsonSyntaxException {
    // Arrange
    JsonParser jsonParser = new JsonParser();

    // Act
    JsonElement actualParseResult =
        jsonParser.parse(new JsonReader(new BufferedReader(new StringReader("End of input"), 1)));

    // Assert
    assertTrue(actualParseResult instanceof JsonPrimitive);
    Number asNumber = actualParseResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("End", actualParseResult.getAsString());
    assertEquals("End", asNumber.toString());
    assertEquals('E', actualParseResult.getAsCharacter());
    assertFalse(actualParseResult.getAsBoolean());
    assertFalse(actualParseResult.isJsonArray());
    assertFalse(actualParseResult.isJsonNull());
    assertFalse(actualParseResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualParseResult).isBoolean());
    assertFalse(((JsonPrimitive) actualParseResult).isNumber());
    assertTrue(actualParseResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualParseResult).isString());
    assertSame(actualParseResult, actualParseResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonParser#parse(JsonReader)} */
  @Test
  public void testParse8() throws JsonIOException, JsonSyntaxException {
    // Arrange
    JsonParser jsonParser = new JsonParser();

    // Act
    JsonElement actualParseResult =
        jsonParser.parse(
            new JsonReader(new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray())));

    // Assert
    assertTrue(actualParseResult instanceof JsonPrimitive);
    Number asNumber = actualParseResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("\u0001\u0006\u0001\u0006", actualParseResult.getAsString());
    assertEquals("\u0001\u0006\u0001\u0006", asNumber.toString());
    assertEquals('\u0001', actualParseResult.getAsCharacter());
    assertFalse(actualParseResult.getAsBoolean());
    assertFalse(actualParseResult.isJsonArray());
    assertFalse(actualParseResult.isJsonNull());
    assertFalse(actualParseResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualParseResult).isBoolean());
    assertFalse(((JsonPrimitive) actualParseResult).isNumber());
    assertTrue(actualParseResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualParseResult).isString());
    assertSame(actualParseResult, actualParseResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonParser#parse(JsonReader)} */
  @Test
  public void testParse9() throws JsonIOException, JsonSyntaxException {
    // Arrange
    JsonParser jsonParser = new JsonParser();

    JsonReader json = new JsonReader(new StringReader("foo"));
    json.setStrictness(Strictness.LENIENT);

    // Act
    JsonElement actualParseResult = jsonParser.parse(json);

    // Assert
    assertTrue(actualParseResult instanceof JsonPrimitive);
    Number asNumber = actualParseResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("foo", actualParseResult.getAsString());
    assertEquals("foo", asNumber.toString());
    assertEquals('f', actualParseResult.getAsCharacter());
    assertFalse(actualParseResult.getAsBoolean());
    assertFalse(actualParseResult.isJsonArray());
    assertFalse(actualParseResult.isJsonNull());
    assertFalse(actualParseResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualParseResult).isBoolean());
    assertFalse(((JsonPrimitive) actualParseResult).isNumber());
    assertTrue(actualParseResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualParseResult).isString());
    assertSame(actualParseResult, actualParseResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonParser#parse(JsonReader)} */
  @Test
  public void testParse10() throws JsonIOException, JsonSyntaxException {
    // Arrange
    JsonParser jsonParser = new JsonParser();

    // Act
    JsonElement actualParseResult =
        jsonParser.parse(new JsonReader(new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray())));

    // Assert
    assertTrue(actualParseResult instanceof JsonPrimitive);
    Number asNumber = actualParseResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("\u0006\u0001\u0006", actualParseResult.getAsString());
    assertEquals("\u0006\u0001\u0006", asNumber.toString());
    assertEquals('\u0006', actualParseResult.getAsCharacter());
    assertFalse(actualParseResult.getAsBoolean());
    assertFalse(actualParseResult.isJsonArray());
    assertFalse(actualParseResult.isJsonNull());
    assertFalse(actualParseResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualParseResult).isBoolean());
    assertFalse(((JsonPrimitive) actualParseResult).isNumber());
    assertTrue(actualParseResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualParseResult).isString());
    assertSame(actualParseResult, actualParseResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonParser#parse(JsonReader)} */
  @Test
  public void testParse11() throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    JsonParser jsonParser = new JsonParser();

    StringReader stringReader = new StringReader("End of input");
    stringReader.read("\u0001\u0006\u0001\u0006".toCharArray(), 1, 1);

    // Act
    JsonElement actualParseResult =
        jsonParser.parse(new JsonReader(new BufferedReader(stringReader, 1)));

    // Assert
    assertTrue(actualParseResult instanceof JsonPrimitive);
    Number asNumber = actualParseResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("nd", actualParseResult.getAsString());
    assertEquals("nd", asNumber.toString());
    assertEquals('n', actualParseResult.getAsCharacter());
    assertFalse(actualParseResult.getAsBoolean());
    assertFalse(actualParseResult.isJsonArray());
    assertFalse(actualParseResult.isJsonNull());
    assertFalse(actualParseResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualParseResult).isBoolean());
    assertFalse(((JsonPrimitive) actualParseResult).isNumber());
    assertTrue(actualParseResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualParseResult).isString());
    assertSame(actualParseResult, actualParseResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonParser#parse(JsonReader)} */
  @Test
  public void testParse12() throws JsonIOException, JsonSyntaxException {
    // Arrange
    JsonParser jsonParser = new JsonParser();

    JsonReader json = new JsonReader(new StringReader(""));
    json.setStrictness(Strictness.STRICT);

    // Act
    JsonElement actualParseResult = jsonParser.parse(json);

    // Assert
    assertSame(((JsonNull) actualParseResult).INSTANCE, actualParseResult);
  }

  /** Method under test: {@link JsonParser#parse(Reader)} */
  @Test
  public void testParse13() throws JsonIOException, JsonSyntaxException {
    // Arrange
    JsonParser jsonParser = new JsonParser();

    // Act
    JsonElement actualParseResult = jsonParser.parse(new StringReader("foo"));

    // Assert
    assertTrue(actualParseResult instanceof JsonPrimitive);
    Number asNumber = actualParseResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("foo", actualParseResult.getAsString());
    assertEquals("foo", asNumber.toString());
    assertEquals('f', actualParseResult.getAsCharacter());
    assertFalse(actualParseResult.getAsBoolean());
    assertFalse(actualParseResult.isJsonArray());
    assertFalse(actualParseResult.isJsonNull());
    assertFalse(actualParseResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualParseResult).isBoolean());
    assertFalse(((JsonPrimitive) actualParseResult).isNumber());
    assertTrue(actualParseResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualParseResult).isString());
    assertSame(actualParseResult, actualParseResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonParser#parse(Reader)} */
  @Test
  public void testParse14() throws JsonIOException, JsonSyntaxException {
    // Arrange
    JsonParser jsonParser = new JsonParser();

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> jsonParser.parse(new StringReader("in == null")));
  }

  /** Method under test: {@link JsonParser#parse(Reader)} */
  @Test
  public void testParse15() throws JsonIOException, JsonSyntaxException {
    // Arrange
    JsonParser jsonParser = new JsonParser();

    // Act
    JsonElement actualParseResult = jsonParser.parse(new StringReader(Boolean.FALSE.toString()));

    // Assert
    assertTrue(actualParseResult instanceof JsonPrimitive);
    assertEquals('f', actualParseResult.getAsCharacter());
    assertFalse(actualParseResult.getAsBoolean());
    assertFalse(actualParseResult.isJsonArray());
    assertFalse(actualParseResult.isJsonNull());
    assertFalse(actualParseResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualParseResult).isNumber());
    assertFalse(((JsonPrimitive) actualParseResult).isString());
    assertTrue(actualParseResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualParseResult).isBoolean());
    String expectedAsString = Boolean.FALSE.toString();
    assertEquals(expectedAsString, actualParseResult.getAsString());
    assertSame(actualParseResult, actualParseResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonParser#parse(Reader)} */
  @Test
  public void testParse16() throws JsonIOException, JsonSyntaxException {
    // Arrange
    JsonParser jsonParser = new JsonParser();

    // Act
    JsonElement actualParseResult = jsonParser.parse(new StringReader("FALSE"));

    // Assert
    assertTrue(actualParseResult instanceof JsonPrimitive);
    assertEquals('f', actualParseResult.getAsCharacter());
    assertFalse(actualParseResult.getAsBoolean());
    assertFalse(actualParseResult.isJsonArray());
    assertFalse(actualParseResult.isJsonNull());
    assertFalse(actualParseResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualParseResult).isNumber());
    assertFalse(((JsonPrimitive) actualParseResult).isString());
    assertTrue(actualParseResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualParseResult).isBoolean());
    String expectedAsString = Boolean.FALSE.toString();
    assertEquals(expectedAsString, actualParseResult.getAsString());
    assertSame(actualParseResult, actualParseResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonParser#parse(Reader)} */
  @Test
  public void testParse17() throws JsonIOException, JsonSyntaxException {
    // Arrange
    JsonParser jsonParser = new JsonParser();

    // Act
    JsonElement actualParseResult = jsonParser.parse(new StringReader("42"));

    // Assert
    assertTrue(actualParseResult instanceof JsonPrimitive);
    Number asNumber = actualParseResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("42", actualParseResult.getAsString());
    assertEquals("42", asNumber.toString());
    BigInteger asBigInteger = actualParseResult.getAsBigInteger();
    assertEquals("42", asBigInteger.toString());
    assertEquals('4', actualParseResult.getAsCharacter());
    assertEquals(1, asBigInteger.getLowestSetBit());
    assertEquals(1, asBigInteger.signum());
    assertEquals(42, actualParseResult.getAsInt());
    assertEquals(42.0d, actualParseResult.getAsDouble(), 0.0);
    assertEquals(42.0f, actualParseResult.getAsFloat(), 0.0f);
    assertEquals(42L, actualParseResult.getAsLong());
    assertEquals((short) 42, actualParseResult.getAsShort());
    assertFalse(actualParseResult.getAsBoolean());
    assertFalse(actualParseResult.isJsonArray());
    assertFalse(actualParseResult.isJsonNull());
    assertFalse(actualParseResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualParseResult).isBoolean());
    assertFalse(((JsonPrimitive) actualParseResult).isString());
    assertTrue(actualParseResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualParseResult).isNumber());
    BigDecimal expectedAsBigDecimal = new BigDecimal("42");
    assertEquals(expectedAsBigDecimal, actualParseResult.getAsBigDecimal());
    assertEquals('*', actualParseResult.getAsByte());
    assertSame(actualParseResult, actualParseResult.getAsJsonPrimitive());
    assertArrayEquals(new byte[] {'*'}, asBigInteger.toByteArray());
  }

  /** Method under test: {@link JsonParser#parse(Reader)} */
  @Test
  public void testParse18() throws JsonIOException, JsonSyntaxException {
    // Arrange
    JsonParser jsonParser = new JsonParser();

    // Act
    JsonElement actualParseResult = jsonParser.parse(new StringReader(""));

    // Assert
    assertSame(((JsonNull) actualParseResult).INSTANCE, actualParseResult);
  }

  /** Method under test: {@link JsonParser#parse(String)} */
  @Test
  public void testParse19() throws JsonSyntaxException {
    // Arrange and Act
    JsonElement actualParseResult = (new JsonParser()).parse("Json");

    // Assert
    assertTrue(actualParseResult instanceof JsonPrimitive);
    Number asNumber = actualParseResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("Json", actualParseResult.getAsString());
    assertEquals("Json", asNumber.toString());
    assertEquals('J', actualParseResult.getAsCharacter());
    assertFalse(actualParseResult.getAsBoolean());
    assertFalse(actualParseResult.isJsonArray());
    assertFalse(actualParseResult.isJsonNull());
    assertFalse(actualParseResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualParseResult).isBoolean());
    assertFalse(((JsonPrimitive) actualParseResult).isNumber());
    assertTrue(actualParseResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualParseResult).isString());
    assertSame(actualParseResult, actualParseResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonParser#parse(String)} */
  @Test
  public void testParse20() throws JsonSyntaxException {
    // Arrange, Act and Assert
    assertThrows(JsonSyntaxException.class, () -> (new JsonParser()).parse("in == null"));
  }

  /** Method under test: {@link JsonParser#parse(String)} */
  @Test
  public void testParse21() throws JsonSyntaxException {
    // Arrange and Act
    JsonElement actualParseResult = (new JsonParser()).parse("42");

    // Assert
    assertTrue(actualParseResult instanceof JsonPrimitive);
    Number asNumber = actualParseResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("42", actualParseResult.getAsString());
    assertEquals("42", asNumber.toString());
    BigInteger asBigInteger = actualParseResult.getAsBigInteger();
    assertEquals("42", asBigInteger.toString());
    assertEquals('4', actualParseResult.getAsCharacter());
    assertEquals(1, asBigInteger.getLowestSetBit());
    assertEquals(1, asBigInteger.signum());
    assertEquals(42, actualParseResult.getAsInt());
    assertEquals(42.0d, actualParseResult.getAsDouble(), 0.0);
    assertEquals(42.0f, actualParseResult.getAsFloat(), 0.0f);
    assertEquals(42L, actualParseResult.getAsLong());
    assertEquals((short) 42, actualParseResult.getAsShort());
    assertFalse(actualParseResult.getAsBoolean());
    assertFalse(actualParseResult.isJsonArray());
    assertFalse(actualParseResult.isJsonNull());
    assertFalse(actualParseResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualParseResult).isBoolean());
    assertFalse(((JsonPrimitive) actualParseResult).isString());
    assertTrue(actualParseResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualParseResult).isNumber());
    BigDecimal expectedAsBigDecimal = new BigDecimal("42");
    assertEquals(expectedAsBigDecimal, actualParseResult.getAsBigDecimal());
    assertEquals('*', actualParseResult.getAsByte());
    assertSame(actualParseResult, actualParseResult.getAsJsonPrimitive());
    assertArrayEquals(new byte[] {'*'}, asBigInteger.toByteArray());
  }

  /** Method under test: {@link JsonParser#parse(String)} */
  @Test
  public void testParse22() throws JsonSyntaxException {
    // Arrange and Act
    JsonElement actualParseResult = (new JsonParser()).parse("");

    // Assert
    assertSame(((JsonNull) actualParseResult).INSTANCE, actualParseResult);
  }
}
