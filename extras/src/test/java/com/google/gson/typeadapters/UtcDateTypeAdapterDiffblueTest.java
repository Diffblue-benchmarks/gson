package com.google.gson.typeadapters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.Strictness;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class UtcDateTypeAdapterDiffblueTest {
  /**
   * Test {@link UtcDateTypeAdapter#write(JsonWriter, Date)} with {@code JsonWriter}, {@code Date}.
   *
   * <ul>
   *   <li>When {@link JsonTreeWriter} (default constructor).
   *   <li>Then {@link JsonTreeWriter} (default constructor) {@link JsonNull}.
   * </ul>
   *
   * <p>Method under test: {@link UtcDateTypeAdapter#write(JsonWriter, Date)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void UtcDateTypeAdapter.write(JsonWriter, Date)"})
  public void testWriteWithJsonWriterDate_whenJsonTreeWriter_thenJsonTreeWriterJsonNull()
      throws IOException {
    // Arrange
    UtcDateTypeAdapter utcDateTypeAdapter = new UtcDateTypeAdapter();
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    utcDateTypeAdapter.write(out, null);

    // Assert that nothing has changed
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonNull);
    assertFalse(getResult.isJsonPrimitive());
    assertTrue(getResult.isJsonNull());
  }

  /**
   * Test {@link UtcDateTypeAdapter#write(JsonWriter, Date)} with {@code JsonWriter}, {@code Date}.
   *
   * <ul>
   *   <li>When {@link JsonTreeWriter} (default constructor).
   *   <li>Then {@link JsonTreeWriter} (default constructor) {@link JsonPrimitive}.
   * </ul>
   *
   * <p>Method under test: {@link UtcDateTypeAdapter#write(JsonWriter, Date)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void UtcDateTypeAdapter.write(JsonWriter, Date)"})
  public void testWriteWithJsonWriterDate_whenJsonTreeWriter_thenJsonTreeWriterJsonPrimitive()
      throws IOException {
    // Arrange
    UtcDateTypeAdapter utcDateTypeAdapter = new UtcDateTypeAdapter();
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    utcDateTypeAdapter.write(
        out, Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

    // Assert
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonPrimitive);
    Number asNumber = getResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("1970-01-01T00:00:00.000Z", getResult.getAsString());
    assertEquals("1970-01-01T00:00:00.000Z", asNumber.toString());
    assertEquals('1', getResult.getAsCharacter());
    assertFalse(getResult.getAsBoolean());
    assertFalse(getResult.isJsonNull());
    assertFalse(((JsonPrimitive) getResult).isBoolean());
    assertFalse(((JsonPrimitive) getResult).isNumber());
    assertTrue(getResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) getResult).isString());
    assertSame(getResult, getResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link UtcDateTypeAdapter#read(JsonReader)}.
   *
   * <p>Method under test: {@link UtcDateTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date UtcDateTypeAdapter.read(JsonReader)"})
  public void testRead() throws IOException {
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

  /**
   * Test {@link UtcDateTypeAdapter#read(JsonReader)}.
   *
   * <p>Method under test: {@link UtcDateTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date UtcDateTypeAdapter.read(JsonReader)"})
  public void testRead2() throws IOException {
    // Arrange
    UtcDateTypeAdapter utcDateTypeAdapter = new UtcDateTypeAdapter();
    CharArrayReader in = new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray());

    JsonReader in2 = new JsonReader(in);
    in2.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(JsonParseException.class, () -> utcDateTypeAdapter.read(in2));
  }

  /**
   * Test {@link UtcDateTypeAdapter#read(JsonReader)}.
   *
   * <p>Method under test: {@link UtcDateTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date UtcDateTypeAdapter.read(JsonReader)"})
  public void testRead3() throws IOException {
    // Arrange
    UtcDateTypeAdapter utcDateTypeAdapter = new UtcDateTypeAdapter();

    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(1L);

    JsonReader in2 = new JsonReader(in);
    in2.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(JsonParseException.class, () -> utcDateTypeAdapter.read(in2));
  }

  /**
   * Test {@link UtcDateTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then throw {@link JsonParseException}.
   * </ul>
   *
   * <p>Method under test: {@link UtcDateTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date UtcDateTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_whenStringReaderWithFoo_thenThrowJsonParseException()
      throws IOException {
    // Arrange
    UtcDateTypeAdapter utcDateTypeAdapter = new UtcDateTypeAdapter();

    JsonReader in = new JsonReader(new StringReader("foo"));
    in.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(JsonParseException.class, () -> utcDateTypeAdapter.read(in));
  }

  /**
   * Test {@link UtcDateTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given six.
   *   <li>When {@link StringReader#StringReader(String)} with {@code at line} skip six.
   *   <li>Then throw {@link JsonParseException}.
   * </ul>
   *
   * <p>Method under test: {@link UtcDateTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date UtcDateTypeAdapter.read(JsonReader)"})
  public void testRead_givenSix_whenStringReaderWithAtLineSkipSix_thenThrowJsonParseException()
      throws IOException {
    // Arrange
    UtcDateTypeAdapter utcDateTypeAdapter = new UtcDateTypeAdapter();

    StringReader in = new StringReader(" at line ");
    in.skip(6L);

    JsonReader in2 = new JsonReader(in);
    in2.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(JsonParseException.class, () -> utcDateTypeAdapter.read(in2));
  }

  /**
   * Test {@link UtcDateTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given three.
   * </ul>
   *
   * <p>Method under test: {@link UtcDateTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date UtcDateTypeAdapter.read(JsonReader)"})
  public void testRead_givenThree() throws IOException {
    // Arrange
    UtcDateTypeAdapter utcDateTypeAdapter = new UtcDateTypeAdapter();

    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.read("\u0001\u0006\u0001\u0006".toCharArray(), 1, 3);
    in.skip(6L);

    JsonReader in2 = new JsonReader(in);
    in2.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(JsonParseException.class, () -> utcDateTypeAdapter.read(in2));
  }

  /**
   * Test {@link UtcDateTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link CharArrayReader#CharArrayReader(char[])} with {@code ﻿} toCharArray.
   * </ul>
   *
   * <p>Method under test: {@link UtcDateTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date UtcDateTypeAdapter.read(JsonReader)"})
  public void testRead_whenCharArrayReaderWithZeroWidthNoBreakSpaceToCharArray()
      throws IOException {
    // Arrange
    UtcDateTypeAdapter utcDateTypeAdapter = new UtcDateTypeAdapter();
    CharArrayReader in = new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray());

    JsonReader in2 = new JsonReader(in);
    in2.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(JsonParseException.class, () -> utcDateTypeAdapter.read(in2));
  }

  /**
   * Test {@link UtcDateTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonPrimitive#JsonPrimitive(String)} with {@code String}.
   *   <li>Then throw {@link JsonParseException}.
   * </ul>
   *
   * <p>Method under test: {@link UtcDateTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date UtcDateTypeAdapter.read(JsonReader)"})
  public void testRead_whenJsonPrimitiveWithString_thenThrowJsonParseException()
      throws IOException {
    // Arrange
    UtcDateTypeAdapter utcDateTypeAdapter = new UtcDateTypeAdapter();

    // Act and Assert
    assertThrows(
        JsonParseException.class,
        () -> utcDateTypeAdapter.read(new JsonTreeReader(new JsonPrimitive("String"))));
  }

  /**
   * Test {@link UtcDateTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link JsonNull}
   *       (default constructor).
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link UtcDateTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date UtcDateTypeAdapter.read(JsonReader)"})
  public void testRead_whenJsonTreeReaderWithElementIsJsonNull_thenReturnNull() throws IOException {
    // Arrange
    UtcDateTypeAdapter utcDateTypeAdapter = new UtcDateTypeAdapter();
    JsonTreeReader in = new JsonTreeReader(new JsonNull());

    // Act
    Date actualReadResult = utcDateTypeAdapter.read(in);

    // Assert
    assertNull(actualReadResult);
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link UtcDateTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then throw {@link JsonParseException}.
   * </ul>
   *
   * <p>Method under test: {@link UtcDateTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date UtcDateTypeAdapter.read(JsonReader)"})
  public void testRead_whenStringReaderWith42_thenThrowJsonParseException() throws IOException {
    // Arrange
    UtcDateTypeAdapter utcDateTypeAdapter = new UtcDateTypeAdapter();

    // Act and Assert
    assertThrows(
        JsonParseException.class,
        () -> utcDateTypeAdapter.read(new JsonReader(new StringReader("42"))));
  }
}
