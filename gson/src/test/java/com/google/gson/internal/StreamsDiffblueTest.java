package com.google.gson.internal;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import com.google.gson.Strictness;
import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.FileDescriptor;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.junit.Test;

public class StreamsDiffblueTest {
  /** Method under test: {@link Streams#parse(JsonReader)} */
  @Test
  public void testParse() throws JsonParseException {
    // Arrange, Act and Assert
    assertThrows(
        JsonSyntaxException.class, () -> Streams.parse(new JsonReader(new StringReader("foo"))));
    assertThrows(
        JsonSyntaxException.class,
        () ->
            Streams.parse(
                new JsonReader(
                    new StringReader(
                        "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed"
                            + " JSON"))));
    assertThrows(
        JsonSyntaxException.class,
        () -> Streams.parse(new JsonReader(new StringReader(" at line "))));
    assertThrows(
        JsonSyntaxException.class, () -> Streams.parse(new JsonReader(new StringReader("\nSee "))));
    assertThrows(
        JsonSyntaxException.class,
        () ->
            Streams.parse(
                new JsonReader(new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray()))));
    assertThrows(
        JsonIOException.class,
        () -> Streams.parse(new JsonReader(new FileReader(new FileDescriptor()))));
    assertThrows(
        JsonSyntaxException.class,
        () ->
            Streams.parse(
                new JsonReader(new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray()))));
  }

  /** Method under test: {@link Streams#parse(JsonReader)} */
  @Test
  public void testParse2() throws JsonParseException {
    // Arrange and Act
    JsonElement actualParseResult =
        Streams.parse(new JsonReader(new StringReader(Boolean.FALSE.toString())));

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

  /** Method under test: {@link Streams#parse(JsonReader)} */
  @Test
  public void testParse3() throws JsonParseException {
    // Arrange and Act
    JsonElement actualParseResult = Streams.parse(new JsonReader(new StringReader("FALSE")));

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

  /** Method under test: {@link Streams#parse(JsonReader)} */
  @Test
  public void testParse4() throws JsonParseException {
    // Arrange and Act
    JsonElement actualParseResult = Streams.parse(new JsonReader(new StringReader("42")));

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

  /** Method under test: {@link Streams#parse(JsonReader)} */
  @Test
  public void testParse5() throws JsonParseException {
    // Arrange and Act
    JsonElement actualParseResult = Streams.parse(new JsonReader(new StringReader("")));

    // Assert
    assertTrue(actualParseResult instanceof JsonNull);
    assertFalse(actualParseResult.isJsonArray());
    assertFalse(actualParseResult.isJsonObject());
    assertFalse(actualParseResult.isJsonPrimitive());
    assertTrue(actualParseResult.isJsonNull());
    JsonNull expectedAsJsonNull = ((JsonNull) actualParseResult).INSTANCE;
    assertSame(expectedAsJsonNull, actualParseResult.getAsJsonNull());
  }

  /** Method under test: {@link Streams#parse(JsonReader)} */
  @Test
  public void testParse6() throws JsonParseException, IOException {
    // Arrange
    JsonTreeReader reader = new JsonTreeReader(JsonNull.INSTANCE);

    // Act
    JsonElement actualParseResult = Streams.parse(reader);

    // Assert
    assertTrue(actualParseResult instanceof JsonNull);
    assertFalse(actualParseResult.isJsonArray());
    assertFalse(actualParseResult.isJsonObject());
    assertFalse(actualParseResult.isJsonPrimitive());
    assertFalse(reader.hasNext());
    assertTrue(actualParseResult.isJsonNull());
    JsonNull expectedAsJsonNull = ((JsonNull) actualParseResult).INSTANCE;
    assertSame(expectedAsJsonNull, actualParseResult.getAsJsonNull());
  }

  /** Method under test: {@link Streams#parse(JsonReader)} */
  @Test
  public void testParse7() throws JsonParseException {
    // Arrange
    JsonReader reader = new JsonReader(new StringReader("foo"));
    reader.setStrictness(Strictness.LENIENT);

    // Act
    JsonElement actualParseResult = Streams.parse(reader);

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

  /** Method under test: {@link Streams#parse(JsonReader)} */
  @Test
  public void testParse8() throws JsonParseException, IOException {
    // Arrange
    JsonTreeReader reader = new JsonTreeReader(new JsonObject());

    // Act
    JsonElement actualParseResult = Streams.parse(reader);

    // Assert
    assertTrue(actualParseResult instanceof JsonObject);
    assertEquals(0, ((JsonObject) actualParseResult).size());
    assertFalse(actualParseResult.isJsonArray());
    assertFalse(actualParseResult.isJsonNull());
    assertFalse(actualParseResult.isJsonPrimitive());
    assertFalse(reader.hasNext());
    assertTrue(actualParseResult.isJsonObject());
    assertTrue(((JsonObject) actualParseResult).isEmpty());
    assertSame(actualParseResult, actualParseResult.getAsJsonObject());
  }

  /** Method under test: {@link Streams#parse(JsonReader)} */
  @Test
  public void testParse9() throws JsonParseException, IOException {
    // Arrange
    JsonTreeReader reader = new JsonTreeReader(new JsonArray(3));

    // Act
    JsonElement actualParseResult = Streams.parse(reader);

    // Assert
    assertTrue(actualParseResult instanceof JsonArray);
    assertEquals(0, ((JsonArray) actualParseResult).size());
    assertFalse(actualParseResult.isJsonNull());
    assertFalse(actualParseResult.isJsonObject());
    assertFalse(actualParseResult.isJsonPrimitive());
    assertFalse(reader.hasNext());
    assertFalse(((JsonArray) actualParseResult).iterator().hasNext());
    assertTrue(((JsonArray) actualParseResult).isEmpty());
    assertTrue(actualParseResult.isJsonArray());
    assertSame(actualParseResult, actualParseResult.getAsJsonArray());
  }

  /** Method under test: {@link Streams#parse(JsonReader)} */
  @Test
  public void testParse10() throws JsonParseException, IOException {
    // Arrange
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive("String"));

    // Act
    JsonElement actualParseResult = Streams.parse(reader);

    // Assert
    assertTrue(actualParseResult instanceof JsonPrimitive);
    Number asNumber = actualParseResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("String", actualParseResult.getAsString());
    assertEquals("String", asNumber.toString());
    assertEquals('S', actualParseResult.getAsCharacter());
    assertFalse(actualParseResult.getAsBoolean());
    assertFalse(actualParseResult.isJsonArray());
    assertFalse(actualParseResult.isJsonNull());
    assertFalse(actualParseResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualParseResult).isBoolean());
    assertFalse(((JsonPrimitive) actualParseResult).isNumber());
    assertFalse(reader.hasNext());
    assertTrue(actualParseResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualParseResult).isString());
    assertSame(actualParseResult, actualParseResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link Streams#parse(JsonReader)} */
  @Test
  public void testParse11() throws JsonParseException, IOException {
    // Arrange
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive(true));

    // Act
    JsonElement actualParseResult = Streams.parse(reader);

    // Assert
    assertTrue(actualParseResult instanceof JsonPrimitive);
    assertEquals('t', actualParseResult.getAsCharacter());
    assertFalse(actualParseResult.isJsonArray());
    assertFalse(actualParseResult.isJsonNull());
    assertFalse(actualParseResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualParseResult).isNumber());
    assertFalse(((JsonPrimitive) actualParseResult).isString());
    assertFalse(reader.hasNext());
    assertTrue(actualParseResult.getAsBoolean());
    assertTrue(actualParseResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualParseResult).isBoolean());
    String expectedAsString = Boolean.TRUE.toString();
    assertEquals(expectedAsString, actualParseResult.getAsString());
    assertSame(actualParseResult, actualParseResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link Streams#parse(JsonReader)} */
  @Test
  public void testParse12() throws JsonParseException {
    // Arrange
    JsonReader reader = new JsonReader(new StringReader(Boolean.FALSE.toString()));
    reader.setStrictness(Strictness.LENIENT);

    // Act
    JsonElement actualParseResult = Streams.parse(reader);

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

  /** Method under test: {@link Streams#parse(JsonReader)} */
  @Test
  public void testParse13() throws JsonParseException {
    // Arrange
    JsonReader reader =
        new JsonReader(
            new StringReader(
                "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed JSON"));
    reader.setStrictness(Strictness.LENIENT);

    // Act
    JsonElement actualParseResult = Streams.parse(reader);

    // Assert
    assertTrue(actualParseResult instanceof JsonPrimitive);
    Number asNumber = actualParseResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("Use", actualParseResult.getAsString());
    assertEquals("Use", asNumber.toString());
    assertEquals('U', actualParseResult.getAsCharacter());
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

  /** Method under test: {@link Streams#parse(JsonReader)} */
  @Test
  public void testParse14() throws JsonParseException {
    // Arrange
    JsonReader reader = new JsonReader(new StringReader(""));
    reader.setStrictness(Strictness.LENIENT);

    // Act
    JsonElement actualParseResult = Streams.parse(reader);

    // Assert
    assertTrue(actualParseResult instanceof JsonNull);
    assertFalse(actualParseResult.isJsonArray());
    assertFalse(actualParseResult.isJsonObject());
    assertFalse(actualParseResult.isJsonPrimitive());
    assertTrue(actualParseResult.isJsonNull());
    JsonNull expectedAsJsonNull = ((JsonNull) actualParseResult).INSTANCE;
    assertSame(expectedAsJsonNull, actualParseResult.getAsJsonNull());
  }

  /** Method under test: {@link Streams#parse(JsonReader)} */
  @Test
  public void testParse15() throws JsonParseException {
    // Arrange
    JsonReader reader = new JsonReader(new StringReader("foo"));
    reader.setStrictness(Strictness.STRICT);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> Streams.parse(reader));
  }

  /** Method under test: {@link Streams#parse(JsonReader)} */
  @Test
  public void testParse16() throws JsonParseException {
    // Arrange
    JsonReader reader = new JsonReader(new StringReader("End of input"));
    reader.setStrictness(Strictness.LENIENT);

    // Act
    JsonElement actualParseResult = Streams.parse(reader);

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

  /** Method under test: {@link Streams#parse(JsonReader)} */
  @Test
  public void testParse17() throws JsonParseException, IOException {
    // Arrange
    StringReader in = new StringReader("End of input");
    in.read("\u0001\u0006\u0001\u0006".toCharArray(), 1, 1);

    JsonReader reader = new JsonReader(in);
    reader.setStrictness(Strictness.LENIENT);

    // Act
    JsonElement actualParseResult = Streams.parse(reader);

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

  /** Method under test: {@link Streams#write(JsonElement, JsonWriter)} */
  @Test
  public void testWrite() throws IOException {
    // Arrange
    JsonElement element = mock(JsonElement.class);
    when(element.isJsonNull()).thenReturn(true);

    // Act
    Streams.write(element, new JsonWriter(new StringWriter()));

    // Assert
    verify(element).isJsonNull();
  }

  /** Method under test: {@link Streams#writerForAppendable(Appendable)} */
  @Test
  public void testWriterForAppendable() {
    // Arrange and Act
    Writer actualWriterForAppendableResult = Streams.writerForAppendable(new CharArrayWriter(1));

    // Assert
    assertTrue(actualWriterForAppendableResult instanceof CharArrayWriter);
    assertEquals(0, ((CharArrayWriter) actualWriterForAppendableResult).size());
  }
}
