package com.google.gson.typeadapters;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.Strictness;
import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.stream.JsonReader;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.StringReader;
import org.junit.Test;

public class UtcDateTypeAdapterDiffblueTest {
  /** Method under test: {@link UtcDateTypeAdapter#read(JsonReader)} */
  @Test
  public void testRead() throws IOException {
    // Arrange
    UtcDateTypeAdapter utcDateTypeAdapter = new UtcDateTypeAdapter();

    // Act and Assert
    assertThrows(
        JsonParseException.class,
        () -> utcDateTypeAdapter.read(new JsonReader(new StringReader("42"))));
  }

  /** Method under test: {@link UtcDateTypeAdapter#read(JsonReader)} */
  @Test
  public void testRead2() throws IOException {
    // Arrange
    UtcDateTypeAdapter utcDateTypeAdapter = new UtcDateTypeAdapter();

    JsonReader in = new JsonReader(new StringReader("foo"));
    in.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(JsonParseException.class, () -> utcDateTypeAdapter.read(in));
  }

  /** Method under test: {@link UtcDateTypeAdapter#read(JsonReader)} */
  @Test
  public void testRead3() throws IOException {
    // Arrange
    UtcDateTypeAdapter utcDateTypeAdapter = new UtcDateTypeAdapter();
    JsonTreeReader in = new JsonTreeReader(new JsonNull());

    // Act and Assert
    assertNull(utcDateTypeAdapter.read(in));
    assertFalse(in.hasNext());
  }

  /** Method under test: {@link UtcDateTypeAdapter#read(JsonReader)} */
  @Test
  public void testRead4() throws IOException {
    // Arrange
    UtcDateTypeAdapter utcDateTypeAdapter = new UtcDateTypeAdapter();

    JsonReader in =
        new JsonReader(
            new StringReader(
                "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed JSON"));
    in.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(JsonParseException.class, () -> utcDateTypeAdapter.read(in));
  }

  /** Method under test: {@link UtcDateTypeAdapter#read(JsonReader)} */
  @Test
  public void testRead5() throws IOException {
    // Arrange
    UtcDateTypeAdapter utcDateTypeAdapter = new UtcDateTypeAdapter();

    JsonReader in = new JsonReader(new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray()));
    in.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(JsonParseException.class, () -> utcDateTypeAdapter.read(in));
  }

  /** Method under test: {@link UtcDateTypeAdapter#read(JsonReader)} */
  @Test
  public void testRead6() throws IOException {
    // Arrange
    UtcDateTypeAdapter utcDateTypeAdapter = new UtcDateTypeAdapter();

    // Act and Assert
    assertThrows(
        JsonParseException.class,
        () -> utcDateTypeAdapter.read(new JsonTreeReader(new JsonPrimitive("String"))));
  }

  /** Method under test: {@link UtcDateTypeAdapter#read(JsonReader)} */
  @Test
  public void testRead7() throws IOException {
    // Arrange
    UtcDateTypeAdapter utcDateTypeAdapter = new UtcDateTypeAdapter();

    JsonReader in = new JsonReader(new StringReader("Expected "));
    in.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(JsonParseException.class, () -> utcDateTypeAdapter.read(in));
  }

  /** Method under test: {@link UtcDateTypeAdapter#read(JsonReader)} */
  @Test
  public void testRead8() throws IOException {
    // Arrange
    UtcDateTypeAdapter utcDateTypeAdapter = new UtcDateTypeAdapter();

    JsonReader in = new JsonReader(new CharArrayReader("1\u0006\u0001\u0006".toCharArray()));
    in.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(JsonParseException.class, () -> utcDateTypeAdapter.read(in));
  }
}
