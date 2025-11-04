package com.google.gson.stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.google.gson.JsonNull;
import com.google.gson.Strictness;
import com.google.gson.internal.bind.JsonTreeReader;
import java.io.CharArrayReader;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import org.junit.Test;

public class JsonReaderDiffblueTest {
  /** Method under test: {@link JsonReader#setLenient(boolean)} */
  @Test
  public void testSetLenient() {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));

    // Act
    jsonReader.setLenient(true);

    // Assert
    assertEquals(Strictness.LENIENT, jsonReader.getStrictness());
    assertTrue(jsonReader.isLenient());
  }

  /** Method under test: {@link JsonReader#setLenient(boolean)} */
  @Test
  public void testSetLenient2() {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));

    // Act
    jsonReader.setLenient(false);

    // Assert
    assertEquals(Strictness.LEGACY_STRICT, jsonReader.getStrictness());
    assertFalse(jsonReader.isLenient());
  }

  /** Method under test: {@link JsonReader#setLenient(boolean)} */
  @Test
  public void testSetLenient3() {
    // Arrange
    JsonReader jsonReader = new JsonReader(new InputStreamReader(mock(DataInputStream.class)));

    // Act
    jsonReader.setLenient(true);

    // Assert
    assertEquals(Strictness.LENIENT, jsonReader.getStrictness());
    assertTrue(jsonReader.isLenient());
  }

  /** Method under test: {@link JsonReader#isLenient()} */
  @Test
  public void testIsLenient() {
    // Arrange, Act and Assert
    assertFalse((new JsonReader(new StringReader("foo"))).isLenient());
    assertFalse((new JsonReader(new InputStreamReader(mock(DataInputStream.class)))).isLenient());
  }

  /** Method under test: {@link JsonReader#isLenient()} */
  @Test
  public void testIsLenient2() {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertTrue(jsonReader.isLenient());
  }

  /** Method under test: {@link JsonReader#setNestingLimit(int)} */
  @Test
  public void testSetNestingLimit() {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));

    // Act
    jsonReader.setNestingLimit(1);

    // Assert
    assertEquals(1, jsonReader.getNestingLimit());
  }

  /** Method under test: {@link JsonReader#setNestingLimit(int)} */
  @Test
  public void testSetNestingLimit2() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> (new JsonReader(new StringReader("foo"))).setNestingLimit(-1));
  }

  /** Method under test: {@link JsonReader#beginArray()} */
  @Test
  public void testBeginArray() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class, () -> (new JsonReader(new StringReader("foo"))).beginArray());
    assertThrows(
        IllegalStateException.class,
        () -> (new JsonReader(new StringReader(Boolean.FALSE.toString()))).beginArray());
    assertThrows(
        IllegalStateException.class,
        () -> (new JsonReader(new StringReader("FALSE"))).beginArray());
    assertThrows(
        MalformedJsonException.class,
        () ->
            (new JsonReader(
                    new StringReader(
                        "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed"
                            + " JSON")))
                .beginArray());
    assertThrows(
        MalformedJsonException.class,
        () -> (new JsonReader(new StringReader(" at line "))).beginArray());
    assertThrows(
        MalformedJsonException.class,
        () -> (new JsonReader(new StringReader("\nSee "))).beginArray());
    assertThrows(
        IllegalStateException.class, () -> (new JsonReader(new StringReader("42"))).beginArray());
    assertThrows(EOFException.class, () -> (new JsonReader(new StringReader(""))).beginArray());
    assertThrows(
        MalformedJsonException.class,
        () ->
            (new JsonReader(new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray())))
                .beginArray());
    assertThrows(
        MalformedJsonException.class,
        () ->
            (new JsonReader(new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray())))
                .beginArray());
  }

  /** Method under test: {@link JsonReader#beginArray()} */
  @Test
  public void testBeginArray2() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.beginArray());
  }

  /** Method under test: {@link JsonReader#beginArray()} */
  @Test
  public void testBeginArray3() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(Boolean.FALSE.toString()));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.beginArray());
  }

  /** Method under test: {@link JsonReader#beginArray()} */
  @Test
  public void testBeginArray4() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(""));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(EOFException.class, () -> jsonReader.beginArray());
  }

  /** Method under test: {@link JsonReader#beginArray()} */
  @Test
  public void testBeginArray5() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("Expected "));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.beginArray());
  }

  /** Method under test: {@link JsonReader#beginArray()} */
  @Test
  public void testBeginArray6() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.STRICT);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.beginArray());
  }

  /** Method under test: {@link JsonReader#endArray()} */
  @Test
  public void testEndArray() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class, () -> (new JsonReader(new StringReader("foo"))).endArray());
    assertThrows(
        IllegalStateException.class,
        () -> (new JsonReader(new StringReader(Boolean.FALSE.toString()))).endArray());
    assertThrows(
        IllegalStateException.class, () -> (new JsonReader(new StringReader("FALSE"))).endArray());
    assertThrows(
        MalformedJsonException.class,
        () ->
            (new JsonReader(
                    new StringReader(
                        "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed"
                            + " JSON")))
                .endArray());
    assertThrows(
        MalformedJsonException.class,
        () -> (new JsonReader(new StringReader(" at line "))).endArray());
    assertThrows(
        MalformedJsonException.class,
        () -> (new JsonReader(new StringReader("\nSee "))).endArray());
    assertThrows(
        IllegalStateException.class, () -> (new JsonReader(new StringReader("42"))).endArray());
    assertThrows(EOFException.class, () -> (new JsonReader(new StringReader(""))).endArray());
    assertThrows(
        MalformedJsonException.class,
        () ->
            (new JsonReader(new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray())))
                .endArray());
    assertThrows(
        MalformedJsonException.class,
        () ->
            (new JsonReader(new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray()))).endArray());
  }

  /** Method under test: {@link JsonReader#endArray()} */
  @Test
  public void testEndArray2() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.endArray());
  }

  /** Method under test: {@link JsonReader#endArray()} */
  @Test
  public void testEndArray3() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(Boolean.FALSE.toString()));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.endArray());
  }

  /** Method under test: {@link JsonReader#endArray()} */
  @Test
  public void testEndArray4() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(""));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(EOFException.class, () -> jsonReader.endArray());
  }

  /** Method under test: {@link JsonReader#endArray()} */
  @Test
  public void testEndArray5() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("END_ARRAY"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.endArray());
  }

  /** Method under test: {@link JsonReader#endArray()} */
  @Test
  public void testEndArray6() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.STRICT);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.endArray());
  }

  /** Method under test: {@link JsonReader#endArray()} */
  @Test
  public void testEndArray7() throws IOException {
    // Arrange
    StringReader in = new StringReader("END_ARRAY");
    in.read("\u0001\u0006\u0001\u0006".toCharArray(), 1, 1);

    JsonReader jsonReader = new JsonReader(in);
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.endArray());
  }

  /** Method under test: {@link JsonReader#endArray()} */
  @Test
  public void testEndArray8() throws IOException {
    // Arrange
    StringReader in = new StringReader("unexpected-json-structure");
    in.read("\u0001\u0006\u0001\u0006".toCharArray(), 1, 1);

    JsonReader jsonReader = new JsonReader(in);
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.endArray());
  }

  /** Method under test: {@link JsonReader#beginObject()} */
  @Test
  public void testBeginObject() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> (new JsonReader(new StringReader("foo"))).beginObject());
    assertThrows(
        IllegalStateException.class,
        () -> (new JsonReader(new StringReader(Boolean.FALSE.toString()))).beginObject());
    assertThrows(
        IllegalStateException.class,
        () -> (new JsonReader(new StringReader("FALSE"))).beginObject());
    assertThrows(
        MalformedJsonException.class,
        () ->
            (new JsonReader(
                    new StringReader(
                        "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed"
                            + " JSON")))
                .beginObject());
    assertThrows(
        MalformedJsonException.class,
        () -> (new JsonReader(new StringReader(" at line "))).beginObject());
    assertThrows(
        MalformedJsonException.class,
        () -> (new JsonReader(new StringReader("\nSee "))).beginObject());
    assertThrows(
        IllegalStateException.class, () -> (new JsonReader(new StringReader("42"))).beginObject());
    assertThrows(EOFException.class, () -> (new JsonReader(new StringReader(""))).beginObject());
    assertThrows(
        MalformedJsonException.class,
        () ->
            (new JsonReader(new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray())))
                .beginObject());
    assertThrows(
        MalformedJsonException.class,
        () ->
            (new JsonReader(new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray())))
                .beginObject());
  }

  /** Method under test: {@link JsonReader#beginObject()} */
  @Test
  public void testBeginObject2() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.beginObject());
  }

  /** Method under test: {@link JsonReader#beginObject()} */
  @Test
  public void testBeginObject3() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(Boolean.FALSE.toString()));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.beginObject());
  }

  /** Method under test: {@link JsonReader#beginObject()} */
  @Test
  public void testBeginObject4() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(""));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(EOFException.class, () -> jsonReader.beginObject());
  }

  /** Method under test: {@link JsonReader#beginObject()} */
  @Test
  public void testBeginObject5() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("Expected "));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.beginObject());
  }

  /** Method under test: {@link JsonReader#beginObject()} */
  @Test
  public void testBeginObject6() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.STRICT);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.beginObject());
  }

  /** Method under test: {@link JsonReader#endObject()} */
  @Test
  public void testEndObject() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class, () -> (new JsonReader(new StringReader("foo"))).endObject());
    assertThrows(
        IllegalStateException.class,
        () -> (new JsonReader(new StringReader(Boolean.FALSE.toString()))).endObject());
    assertThrows(
        IllegalStateException.class, () -> (new JsonReader(new StringReader("FALSE"))).endObject());
    assertThrows(
        MalformedJsonException.class,
        () ->
            (new JsonReader(
                    new StringReader(
                        "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed"
                            + " JSON")))
                .endObject());
    assertThrows(
        MalformedJsonException.class,
        () -> (new JsonReader(new StringReader(" at line "))).endObject());
    assertThrows(
        MalformedJsonException.class,
        () -> (new JsonReader(new StringReader("\nSee "))).endObject());
    assertThrows(
        IllegalStateException.class, () -> (new JsonReader(new StringReader("42"))).endObject());
    assertThrows(EOFException.class, () -> (new JsonReader(new StringReader(""))).endObject());
    assertThrows(
        MalformedJsonException.class,
        () ->
            (new JsonReader(new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray())))
                .endObject());
    assertThrows(
        MalformedJsonException.class,
        () ->
            (new JsonReader(new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray()))).endObject());
  }

  /** Method under test: {@link JsonReader#endObject()} */
  @Test
  public void testEndObject2() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.endObject());
  }

  /** Method under test: {@link JsonReader#endObject()} */
  @Test
  public void testEndObject3() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(Boolean.FALSE.toString()));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.endObject());
  }

  /** Method under test: {@link JsonReader#endObject()} */
  @Test
  public void testEndObject4() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(""));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(EOFException.class, () -> jsonReader.endObject());
  }

  /** Method under test: {@link JsonReader#endObject()} */
  @Test
  public void testEndObject5() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("END_OBJECT"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.endObject());
  }

  /** Method under test: {@link JsonReader#endObject()} */
  @Test
  public void testEndObject6() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.STRICT);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.endObject());
  }

  /** Method under test: {@link JsonReader#endObject()} */
  @Test
  public void testEndObject7() throws IOException {
    // Arrange
    StringReader in = new StringReader("END_OBJECT");
    in.read("\u0001\u0006\u0001\u0006".toCharArray(), 1, 1);

    JsonReader jsonReader = new JsonReader(in);
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.endObject());
  }

  /** Method under test: {@link JsonReader#endObject()} */
  @Test
  public void testEndObject8() throws IOException {
    // Arrange
    StringReader in = new StringReader("unexpected-json-structure");
    in.read("\u0001\u0006\u0001\u0006".toCharArray(), 1, 1);

    JsonReader jsonReader = new JsonReader(in);
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.endObject());
  }

  /** Method under test: {@link JsonReader#hasNext()} */
  @Test
  public void testHasNext() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class, () -> (new JsonReader(new StringReader("foo"))).hasNext());
    assertThrows(
        MalformedJsonException.class,
        () ->
            (new JsonReader(
                    new StringReader(
                        "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed"
                            + " JSON")))
                .hasNext());
    assertThrows(
        MalformedJsonException.class,
        () -> (new JsonReader(new StringReader(" at line "))).hasNext());
    assertThrows(
        MalformedJsonException.class, () -> (new JsonReader(new StringReader("\nSee "))).hasNext());
    assertThrows(EOFException.class, () -> (new JsonReader(new StringReader(""))).hasNext());
    assertThrows(
        MalformedJsonException.class,
        () ->
            (new JsonReader(new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray())))
                .hasNext());
    assertThrows(
        MalformedJsonException.class,
        () -> (new JsonReader(new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray()))).hasNext());
  }

  /** Method under test: {@link JsonReader#hasNext()} */
  @Test
  public void testHasNext2() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(""));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(EOFException.class, () -> jsonReader.hasNext());
  }

  /** Method under test: {@link JsonReader#hasNext()} */
  @Test
  public void testHasNext3() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.STRICT);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.hasNext());
  }

  /** Method under test: {@link JsonReader#peek()} */
  @Test
  public void testPeek() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class, () -> (new JsonReader(new StringReader("foo"))).peek());
    assertThrows(
        MalformedJsonException.class,
        () ->
            (new JsonReader(
                    new StringReader(
                        "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed"
                            + " JSON")))
                .peek());
    assertThrows(
        MalformedJsonException.class, () -> (new JsonReader(new StringReader(" at line "))).peek());
    assertThrows(
        MalformedJsonException.class, () -> (new JsonReader(new StringReader("\nSee "))).peek());
    assertThrows(EOFException.class, () -> (new JsonReader(new StringReader(""))).peek());
    assertThrows(
        MalformedJsonException.class,
        () ->
            (new JsonReader(new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray()))).peek());
    assertThrows(
        MalformedJsonException.class,
        () -> (new JsonReader(new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray()))).peek());
  }

  /** Method under test: {@link JsonReader#peek()} */
  @Test
  public void testPeek2() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(""));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(EOFException.class, () -> jsonReader.peek());
  }

  /** Method under test: {@link JsonReader#peek()} */
  @Test
  public void testPeek3() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.STRICT);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.peek());
  }

  /** Method under test: {@link JsonReader#doPeek()} */
  @Test
  public void testDoPeek() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class, () -> (new JsonReader(new StringReader("foo"))).doPeek());
    assertThrows(
        MalformedJsonException.class,
        () ->
            (new JsonReader(
                    new StringReader(
                        "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed"
                            + " JSON")))
                .doPeek());
    assertThrows(
        MalformedJsonException.class,
        () -> (new JsonReader(new StringReader(" at line "))).doPeek());
    assertThrows(
        MalformedJsonException.class, () -> (new JsonReader(new StringReader("\nSee "))).doPeek());
    assertThrows(EOFException.class, () -> (new JsonReader(new StringReader(""))).doPeek());
    assertThrows(
        MalformedJsonException.class,
        () ->
            (new JsonReader(new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray())))
                .doPeek());
    assertThrows(
        MalformedJsonException.class,
        () -> (new JsonReader(new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray()))).doPeek());
  }

  /** Method under test: {@link JsonReader#doPeek()} */
  @Test
  public void testDoPeek2() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(Boolean.FALSE.toString()));

    // Act and Assert
    assertEquals(6, jsonReader.doPeek());
    assertEquals(6, jsonReader.peeked);
  }

  /** Method under test: {@link JsonReader#doPeek()} */
  @Test
  public void testDoPeek3() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("FALSE"));

    // Act and Assert
    assertEquals(6, jsonReader.doPeek());
    assertEquals(6, jsonReader.peeked);
  }

  /** Method under test: {@link JsonReader#doPeek()} */
  @Test
  public void testDoPeek4() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("42"));

    // Act and Assert
    assertEquals(15, jsonReader.doPeek());
    assertEquals(15, jsonReader.peeked);
  }

  /** Method under test: {@link JsonReader#doPeek()} */
  @Test
  public void testDoPeek5() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertEquals(10, jsonReader.doPeek());
    assertEquals(10, jsonReader.peeked);
  }

  /** Method under test: {@link JsonReader#doPeek()} */
  @Test
  public void testDoPeek6() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(Boolean.FALSE.toString()));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertEquals(6, jsonReader.doPeek());
    assertEquals(6, jsonReader.peeked);
  }

  /** Method under test: {@link JsonReader#doPeek()} */
  @Test
  public void testDoPeek7() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(""));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(EOFException.class, () -> jsonReader.doPeek());
  }

  /** Method under test: {@link JsonReader#doPeek()} */
  @Test
  public void testDoPeek8() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.STRICT);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.doPeek());
  }

  /** Method under test: {@link JsonReader#doPeek()} */
  @Test
  public void testDoPeek9() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("End of input"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertEquals(10, jsonReader.doPeek());
    assertEquals(10, jsonReader.peeked);
  }

  /** Method under test: {@link JsonReader#doPeek()} */
  @Test
  public void testDoPeek10() throws IOException {
    // Arrange
    StringReader in = new StringReader("End of input");
    in.read("\u0001\u0006\u0001\u0006".toCharArray(), 1, 1);

    JsonReader jsonReader = new JsonReader(in);
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertEquals(10, jsonReader.doPeek());
    assertEquals(10, jsonReader.peeked);
  }

  /** Method under test: {@link JsonReader#nextName()} */
  @Test
  public void testNextName() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class, () -> (new JsonReader(new StringReader("foo"))).nextName());
    assertThrows(
        IllegalStateException.class,
        () -> (new JsonReader(new StringReader(Boolean.FALSE.toString()))).nextName());
    assertThrows(
        IllegalStateException.class, () -> (new JsonReader(new StringReader("FALSE"))).nextName());
    assertThrows(
        MalformedJsonException.class,
        () ->
            (new JsonReader(
                    new StringReader(
                        "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed"
                            + " JSON")))
                .nextName());
    assertThrows(
        MalformedJsonException.class,
        () -> (new JsonReader(new StringReader(" at line "))).nextName());
    assertThrows(
        MalformedJsonException.class,
        () -> (new JsonReader(new StringReader("\nSee "))).nextName());
    assertThrows(
        IllegalStateException.class, () -> (new JsonReader(new StringReader("42"))).nextName());
    assertThrows(EOFException.class, () -> (new JsonReader(new StringReader(""))).nextName());
    assertThrows(
        MalformedJsonException.class,
        () ->
            (new JsonReader(new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray())))
                .nextName());
    assertThrows(
        MalformedJsonException.class,
        () ->
            (new JsonReader(new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray()))).nextName());
  }

  /** Method under test: {@link JsonReader#nextName()} */
  @Test
  public void testNextName2() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.nextName());
  }

  /** Method under test: {@link JsonReader#nextName()} */
  @Test
  public void testNextName3() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(Boolean.FALSE.toString()));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.nextName());
  }

  /** Method under test: {@link JsonReader#nextName()} */
  @Test
  public void testNextName4() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(""));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(EOFException.class, () -> jsonReader.nextName());
  }

  /** Method under test: {@link JsonReader#nextName()} */
  @Test
  public void testNextName5() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("Expected "));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.nextName());
  }

  /** Method under test: {@link JsonReader#nextName()} */
  @Test
  public void testNextName6() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.STRICT);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.nextName());
  }

  /** Method under test: {@link JsonReader#nextString()} */
  @Test
  public void testNextString() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class, () -> (new JsonReader(new StringReader("foo"))).nextString());
    assertThrows(
        IllegalStateException.class,
        () -> (new JsonReader(new StringReader(Boolean.FALSE.toString()))).nextString());
    assertThrows(
        IllegalStateException.class,
        () -> (new JsonReader(new StringReader("FALSE"))).nextString());
    assertThrows(
        MalformedJsonException.class,
        () ->
            (new JsonReader(
                    new StringReader(
                        "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed"
                            + " JSON")))
                .nextString());
    assertThrows(
        MalformedJsonException.class,
        () -> (new JsonReader(new StringReader(" at line "))).nextString());
    assertThrows(
        MalformedJsonException.class,
        () -> (new JsonReader(new StringReader("\nSee "))).nextString());
    assertEquals("42", (new JsonReader(new StringReader("42"))).nextString());
    assertThrows(EOFException.class, () -> (new JsonReader(new StringReader(""))).nextString());
    assertThrows(
        MalformedJsonException.class,
        () ->
            (new JsonReader(new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray())))
                .nextString());
    assertThrows(
        MalformedJsonException.class,
        () ->
            (new JsonReader(new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray())))
                .nextString());
  }

  /** Method under test: {@link JsonReader#nextString()} */
  @Test
  public void testNextString2() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertEquals("foo", jsonReader.nextString());
  }

  /** Method under test: {@link JsonReader#nextString()} */
  @Test
  public void testNextString3() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(Boolean.FALSE.toString()));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.nextString());
  }

  /** Method under test: {@link JsonReader#nextString()} */
  @Test
  public void testNextString4() throws IOException {
    // Arrange
    JsonReader jsonReader =
        new JsonReader(
            new StringReader(
                "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed JSON"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertEquals("Use", jsonReader.nextString());
  }

  /** Method under test: {@link JsonReader#nextString()} */
  @Test
  public void testNextString5() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(""));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(EOFException.class, () -> jsonReader.nextString());
  }

  /** Method under test: {@link JsonReader#nextString()} */
  @Test
  public void testNextString6() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.STRICT);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.nextString());
  }

  /** Method under test: {@link JsonReader#nextString()} */
  @Test
  public void testNextString7() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("Expected "));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertEquals("Expected", jsonReader.nextString());
  }

  /** Method under test: {@link JsonReader#nextBoolean()} */
  @Test
  public void testNextBoolean() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> (new JsonReader(new StringReader("foo"))).nextBoolean());
    assertFalse((new JsonReader(new StringReader(Boolean.FALSE.toString()))).nextBoolean());
    assertFalse((new JsonReader(new StringReader("FALSE"))).nextBoolean());
    assertThrows(
        MalformedJsonException.class,
        () ->
            (new JsonReader(
                    new StringReader(
                        "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed"
                            + " JSON")))
                .nextBoolean());
    assertThrows(
        MalformedJsonException.class,
        () -> (new JsonReader(new StringReader(" at line "))).nextBoolean());
    assertThrows(
        MalformedJsonException.class,
        () -> (new JsonReader(new StringReader("\nSee "))).nextBoolean());
    assertThrows(
        IllegalStateException.class, () -> (new JsonReader(new StringReader("42"))).nextBoolean());
    assertThrows(EOFException.class, () -> (new JsonReader(new StringReader(""))).nextBoolean());
    assertThrows(
        MalformedJsonException.class,
        () ->
            (new JsonReader(new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray())))
                .nextBoolean());
    assertThrows(
        MalformedJsonException.class,
        () ->
            (new JsonReader(new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray())))
                .nextBoolean());
  }

  /** Method under test: {@link JsonReader#nextBoolean()} */
  @Test
  public void testNextBoolean2() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.nextBoolean());
  }

  /** Method under test: {@link JsonReader#nextBoolean()} */
  @Test
  public void testNextBoolean3() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(Boolean.FALSE.toString()));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertFalse(jsonReader.nextBoolean());
  }

  /** Method under test: {@link JsonReader#nextBoolean()} */
  @Test
  public void testNextBoolean4() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(""));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(EOFException.class, () -> jsonReader.nextBoolean());
  }

  /** Method under test: {@link JsonReader#nextBoolean()} */
  @Test
  public void testNextBoolean5() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("Expected "));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.nextBoolean());
  }

  /** Method under test: {@link JsonReader#nextBoolean()} */
  @Test
  public void testNextBoolean6() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.STRICT);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.nextBoolean());
  }

  /** Method under test: {@link JsonReader#nextNull()} */
  @Test
  public void testNextNull() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class, () -> (new JsonReader(new StringReader("foo"))).nextNull());
    assertThrows(
        IllegalStateException.class,
        () -> (new JsonReader(new StringReader(Boolean.FALSE.toString()))).nextNull());
    assertThrows(
        IllegalStateException.class, () -> (new JsonReader(new StringReader("FALSE"))).nextNull());
    assertThrows(
        MalformedJsonException.class,
        () ->
            (new JsonReader(
                    new StringReader(
                        "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed"
                            + " JSON")))
                .nextNull());
    assertThrows(
        MalformedJsonException.class,
        () -> (new JsonReader(new StringReader(" at line "))).nextNull());
    assertThrows(
        MalformedJsonException.class,
        () -> (new JsonReader(new StringReader("\nSee "))).nextNull());
    assertThrows(
        IllegalStateException.class, () -> (new JsonReader(new StringReader("42"))).nextNull());
    assertThrows(EOFException.class, () -> (new JsonReader(new StringReader(""))).nextNull());
    assertThrows(
        MalformedJsonException.class,
        () ->
            (new JsonReader(new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray())))
                .nextNull());
    assertThrows(
        MalformedJsonException.class,
        () ->
            (new JsonReader(new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray()))).nextNull());
  }

  /** Method under test: {@link JsonReader#nextNull()} */
  @Test
  public void testNextNull2() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.nextNull());
  }

  /** Method under test: {@link JsonReader#nextNull()} */
  @Test
  public void testNextNull3() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(Boolean.FALSE.toString()));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.nextNull());
  }

  /** Method under test: {@link JsonReader#nextNull()} */
  @Test
  public void testNextNull4() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(""));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(EOFException.class, () -> jsonReader.nextNull());
  }

  /** Method under test: {@link JsonReader#nextNull()} */
  @Test
  public void testNextNull5() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("Expected "));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.nextNull());
  }

  /** Method under test: {@link JsonReader#nextNull()} */
  @Test
  public void testNextNull6() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.STRICT);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.nextNull());
  }

  /** Method under test: {@link JsonReader#nextDouble()} */
  @Test
  public void testNextDouble() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class, () -> (new JsonReader(new StringReader("foo"))).nextDouble());
    assertThrows(
        IllegalStateException.class,
        () -> (new JsonReader(new StringReader(Boolean.FALSE.toString()))).nextDouble());
    assertThrows(
        IllegalStateException.class,
        () -> (new JsonReader(new StringReader("FALSE"))).nextDouble());
    assertThrows(
        MalformedJsonException.class,
        () ->
            (new JsonReader(
                    new StringReader(
                        "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed"
                            + " JSON")))
                .nextDouble());
    assertThrows(
        MalformedJsonException.class,
        () -> (new JsonReader(new StringReader(" at line "))).nextDouble());
    assertThrows(
        MalformedJsonException.class,
        () -> (new JsonReader(new StringReader("\nSee "))).nextDouble());
    assertEquals(42.0d, (new JsonReader(new StringReader("42"))).nextDouble(), 0.0);
    assertThrows(EOFException.class, () -> (new JsonReader(new StringReader(""))).nextDouble());
    assertThrows(
        MalformedJsonException.class,
        () ->
            (new JsonReader(new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray())))
                .nextDouble());
    assertThrows(
        MalformedJsonException.class,
        () ->
            (new JsonReader(new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray())))
                .nextDouble());
  }

  /** Method under test: {@link JsonReader#nextDouble()} */
  @Test
  public void testNextDouble2() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(Boolean.FALSE.toString()));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.nextDouble());
  }

  /** Method under test: {@link JsonReader#nextDouble()} */
  @Test
  public void testNextDouble3() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(""));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(EOFException.class, () -> jsonReader.nextDouble());
  }

  /** Method under test: {@link JsonReader#nextDouble()} */
  @Test
  public void testNextDouble4() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.STRICT);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.nextDouble());
  }

  /** Method under test: {@link JsonReader#nextLong()} */
  @Test
  public void testNextLong() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class, () -> (new JsonReader(new StringReader("foo"))).nextLong());
    assertThrows(
        IllegalStateException.class,
        () -> (new JsonReader(new StringReader(Boolean.FALSE.toString()))).nextLong());
    assertThrows(
        IllegalStateException.class, () -> (new JsonReader(new StringReader("FALSE"))).nextLong());
    assertThrows(
        MalformedJsonException.class,
        () ->
            (new JsonReader(
                    new StringReader(
                        "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed"
                            + " JSON")))
                .nextLong());
    assertThrows(
        MalformedJsonException.class,
        () -> (new JsonReader(new StringReader(" at line "))).nextLong());
    assertThrows(
        MalformedJsonException.class,
        () -> (new JsonReader(new StringReader("\nSee "))).nextLong());
    assertEquals(42L, (new JsonReader(new StringReader("42"))).nextLong());
    assertThrows(EOFException.class, () -> (new JsonReader(new StringReader(""))).nextLong());
    assertThrows(
        MalformedJsonException.class,
        () ->
            (new JsonReader(new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray())))
                .nextLong());
    assertThrows(
        MalformedJsonException.class,
        () ->
            (new JsonReader(new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray()))).nextLong());
  }

  /** Method under test: {@link JsonReader#nextLong()} */
  @Test
  public void testNextLong2() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(Boolean.FALSE.toString()));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.nextLong());
  }

  /** Method under test: {@link JsonReader#nextLong()} */
  @Test
  public void testNextLong3() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(""));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(EOFException.class, () -> jsonReader.nextLong());
  }

  /** Method under test: {@link JsonReader#nextLong()} */
  @Test
  public void testNextLong4() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.STRICT);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.nextLong());
  }

  /** Method under test: {@link JsonReader#nextInt()} */
  @Test
  public void testNextInt() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class, () -> (new JsonReader(new StringReader("foo"))).nextInt());
    assertThrows(
        IllegalStateException.class,
        () -> (new JsonReader(new StringReader(Boolean.FALSE.toString()))).nextInt());
    assertThrows(
        IllegalStateException.class, () -> (new JsonReader(new StringReader("FALSE"))).nextInt());
    assertThrows(
        MalformedJsonException.class,
        () ->
            (new JsonReader(
                    new StringReader(
                        "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed"
                            + " JSON")))
                .nextInt());
    assertThrows(
        MalformedJsonException.class,
        () -> (new JsonReader(new StringReader(" at line "))).nextInt());
    assertThrows(
        MalformedJsonException.class, () -> (new JsonReader(new StringReader("\nSee "))).nextInt());
    assertEquals(42, (new JsonReader(new StringReader("42"))).nextInt());
    assertThrows(EOFException.class, () -> (new JsonReader(new StringReader(""))).nextInt());
    assertThrows(
        MalformedJsonException.class,
        () ->
            (new JsonReader(new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray())))
                .nextInt());
    assertThrows(
        MalformedJsonException.class,
        () -> (new JsonReader(new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray()))).nextInt());
  }

  /** Method under test: {@link JsonReader#nextInt()} */
  @Test
  public void testNextInt2() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(Boolean.FALSE.toString()));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.nextInt());
  }

  /** Method under test: {@link JsonReader#nextInt()} */
  @Test
  public void testNextInt3() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(""));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(EOFException.class, () -> jsonReader.nextInt());
  }

  /** Method under test: {@link JsonReader#nextInt()} */
  @Test
  public void testNextInt4() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.STRICT);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.nextInt());
  }

  /** Method under test: {@link JsonReader#close()} */
  @Test
  public void testClose() throws IOException {
    // Arrange
    DataInputStream dataInputStream = mock(DataInputStream.class);
    doNothing().when(dataInputStream).close();

    // Act
    (new JsonReader(new InputStreamReader(dataInputStream))).close();

    // Assert
    verify(dataInputStream).close();
  }

  /** Method under test: {@link JsonReader#skipValue()} */
  @Test
  public void testSkipValue() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class, () -> (new JsonReader(new StringReader("foo"))).skipValue());
    assertThrows(
        MalformedJsonException.class,
        () ->
            (new JsonReader(
                    new StringReader(
                        "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed"
                            + " JSON")))
                .skipValue());
    assertThrows(
        MalformedJsonException.class,
        () -> (new JsonReader(new StringReader(" at line "))).skipValue());
    assertThrows(
        MalformedJsonException.class,
        () -> (new JsonReader(new StringReader("\nSee "))).skipValue());
    assertThrows(EOFException.class, () -> (new JsonReader(new StringReader(""))).skipValue());
    assertThrows(
        MalformedJsonException.class,
        () ->
            (new JsonReader(new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray())))
                .skipValue());
    assertThrows(
        MalformedJsonException.class,
        () ->
            (new JsonReader(new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray()))).skipValue());
  }

  /** Method under test: {@link JsonReader#skipValue()} */
  @Test
  public void testSkipValue2() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(""));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(EOFException.class, () -> jsonReader.skipValue());
  }

  /** Method under test: {@link JsonReader#skipValue()} */
  @Test
  public void testSkipValue3() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.STRICT);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.skipValue());
  }

  /** Method under test: {@link JsonReader#locationString()} */
  @Test
  public void testLocationString() {
    // Arrange, Act and Assert
    assertEquals(
        " at line 1 column 1 path $", (new JsonReader(new StringReader("foo"))).locationString());
    assertEquals(
        " at line 1 column 1 path $",
        ((JsonReader) new JsonTreeReader(JsonNull.INSTANCE)).locationString());
    assertEquals(
        " at line 1 column 1 path $",
        (new JsonReader(new InputStreamReader(mock(DataInputStream.class)))).locationString());
  }

  /** Method under test: {@link JsonReader#getPath()} */
  @Test
  public void testGetPath() {
    // Arrange, Act and Assert
    assertEquals("$", (new JsonReader(new StringReader("foo"))).getPath());
    assertEquals(
        "$", (new JsonReader(new InputStreamReader(mock(DataInputStream.class)))).getPath());
  }

  /** Method under test: {@link JsonReader#getPreviousPath()} */
  @Test
  public void testGetPreviousPath() {
    // Arrange, Act and Assert
    assertEquals("$", (new JsonReader(new StringReader("foo"))).getPreviousPath());
    assertEquals(
        "$",
        (new JsonReader(new InputStreamReader(mock(DataInputStream.class)))).getPreviousPath());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link JsonReader#setStrictness(Strictness)}
   *   <li>{@link JsonReader#toString()}
   *   <li>{@link JsonReader#getNestingLimit()}
   *   <li>{@link JsonReader#getStrictness()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));

    // Act
    jsonReader.setStrictness(Strictness.LENIENT);
    String actualToStringResult = jsonReader.toString();
    int actualNestingLimit = jsonReader.getNestingLimit();

    // Assert that nothing has changed
    assertEquals("JsonReader at line 1 column 1 path $", actualToStringResult);
    assertEquals(255, actualNestingLimit);
    assertEquals(Strictness.LENIENT, jsonReader.getStrictness());
  }

  /** Method under test: {@link JsonReader#JsonReader(Reader)} */
  @Test
  public void testNewJsonReader() {
    // Arrange and Act
    JsonReader actualJsonReader = new JsonReader(new StringReader("foo"));

    // Assert
    assertEquals("$", actualJsonReader.getPath());
    assertEquals("$", actualJsonReader.getPreviousPath());
    assertEquals(0, actualJsonReader.peeked);
    assertEquals(255, actualJsonReader.getNestingLimit());
    assertEquals(Strictness.LEGACY_STRICT, actualJsonReader.getStrictness());
    assertFalse(actualJsonReader.isLenient());
  }

  /** Method under test: {@link JsonReader#JsonReader(Reader)} */
  @Test
  public void testNewJsonReader2() {
    // Arrange and Act
    JsonReader actualJsonReader =
        new JsonReader(new InputStreamReader(mock(DataInputStream.class)));

    // Assert
    assertEquals("$", actualJsonReader.getPath());
    assertEquals("$", actualJsonReader.getPreviousPath());
    assertEquals(0, actualJsonReader.peeked);
    assertEquals(255, actualJsonReader.getNestingLimit());
    assertEquals(Strictness.LEGACY_STRICT, actualJsonReader.getStrictness());
    assertFalse(actualJsonReader.isLenient());
  }
}
