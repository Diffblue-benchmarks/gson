package com.google.gson.internal;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
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
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class StreamsDiffblueTest {
  /**
   * Test {@link Streams#parse(JsonReader)}.
   *
   * <p>Method under test: {@link Streams#parse(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Streams.parse(JsonReader)"})
  public void testParse() throws JsonParseException {
    // Arrange, Act and Assert
    assertThrows(
        JsonSyntaxException.class,
        () ->
            Streams.parse(
                new JsonReader(
                    new StringReader(
                        "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed"
                            + " JSON"))));
  }

  /**
   * Test {@link Streams#parse(JsonReader)}.
   *
   * <p>Method under test: {@link Streams#parse(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Streams.parse(JsonReader)"})
  public void testParse2() throws JsonParseException {
    // Arrange
    CharArrayReader in = new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray());

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> Streams.parse(new JsonReader(in)));
  }

  /**
   * Test {@link Streams#parse(JsonReader)}.
   *
   * <ul>
   *   <li>Given eleven.
   *   <li>Then return AsString is {@code t}.
   * </ul>
   *
   * <p>Method under test: {@link Streams#parse(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Streams.parse(JsonReader)"})
  public void testParse_givenEleven_thenReturnAsStringIsT() throws JsonParseException, IOException {
    // Arrange
    StringReader in = new StringReader("End of input");
    in.skip(11L);

    JsonReader reader = new JsonReader(in);
    reader.setStrictness(Strictness.LENIENT);

    // Act
    JsonElement actualParseResult = Streams.parse(reader);

    // Assert
    assertTrue(actualParseResult instanceof JsonPrimitive);
    Number asNumber = actualParseResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("t", actualParseResult.getAsString());
    assertEquals("t", asNumber.toString());
    assertEquals('t', actualParseResult.getAsCharacter());
    JsonPrimitive actualAsJsonPrimitive = actualParseResult.getAsJsonPrimitive();
    assertSame(actualParseResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link Streams#parse(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then return {@link JsonNull}.
   * </ul>
   *
   * <p>Method under test: {@link Streams#parse(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Streams.parse(JsonReader)"})
  public void testParse_givenLenient_whenStringReaderWithEmptyString_thenReturnJsonNull()
      throws JsonParseException {
    // Arrange
    JsonReader reader = new JsonReader(new StringReader(""));
    reader.setStrictness(Strictness.LENIENT);

    // Act
    JsonElement actualParseResult = Streams.parse(reader);

    // Assert
    assertTrue(actualParseResult instanceof JsonNull);
    assertTrue(actualParseResult.isJsonNull());
    assertSame(((JsonNull) actualParseResult).INSTANCE, actualParseResult.getAsJsonNull());
  }

  /**
   * Test {@link Streams#parse(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code End of input}.
   *   <li>Then return AsString is {@code End}.
   * </ul>
   *
   * <p>Method under test: {@link Streams#parse(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Streams.parse(JsonReader)"})
  public void testParse_givenLenient_whenStringReaderWithEndOfInput_thenReturnAsStringIsEnd()
      throws JsonParseException {
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
    JsonPrimitive actualAsJsonPrimitive = actualParseResult.getAsJsonPrimitive();
    assertSame(actualParseResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link Streams#parse(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then return Boolean.
   * </ul>
   *
   * <p>Method under test: {@link Streams#parse(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Streams.parse(JsonReader)"})
  public void testParse_givenLenient_whenStringReaderWithFalseToString_thenReturnBoolean()
      throws JsonParseException {
    // Arrange
    StringReader in = new StringReader(Boolean.FALSE.toString());

    JsonReader reader = new JsonReader(in);
    reader.setStrictness(Strictness.LENIENT);

    // Act
    JsonElement actualParseResult = Streams.parse(reader);

    // Assert
    assertTrue(actualParseResult instanceof JsonPrimitive);
    assertEquals('f', actualParseResult.getAsCharacter());
    assertTrue(((JsonPrimitive) actualParseResult).isBoolean());
    assertEquals(Boolean.FALSE.toString(), actualParseResult.getAsString());
    JsonPrimitive actualAsJsonPrimitive = actualParseResult.getAsJsonPrimitive();
    assertSame(actualParseResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link Streams#parse(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then return AsString is {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link Streams#parse(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Streams.parse(JsonReader)"})
  public void testParse_givenLenient_whenStringReaderWithFoo_thenReturnAsStringIsFoo()
      throws JsonParseException {
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
    JsonPrimitive actualAsJsonPrimitive = actualParseResult.getAsJsonPrimitive();
    assertSame(actualParseResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link Streams#parse(JsonReader)}.
   *
   * <ul>
   *   <li>Given one.
   *   <li>When {@link StringReader#StringReader(String)} with {@code End of input} skip one.
   *   <li>Then return AsString is {@code nd}.
   * </ul>
   *
   * <p>Method under test: {@link Streams#parse(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Streams.parse(JsonReader)"})
  public void testParse_givenOne_whenStringReaderWithEndOfInputSkipOne_thenReturnAsStringIsNd()
      throws JsonParseException, IOException {
    // Arrange
    StringReader in = new StringReader("End of input");
    in.skip(1L);

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
    JsonPrimitive actualAsJsonPrimitive = actualParseResult.getAsJsonPrimitive();
    assertSame(actualParseResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link Streams#parse(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code STRICT}.
   *   <li>When {@link JsonReader#JsonReader(Reader)} with in is {@link
   *       StringReader#StringReader(String)} Strictness is {@code STRICT}.
   * </ul>
   *
   * <p>Method under test: {@link Streams#parse(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Streams.parse(JsonReader)"})
  public void testParse_givenStrict_whenJsonReaderWithInIsStringReaderStrictnessIsStrict()
      throws JsonParseException {
    // Arrange
    JsonReader reader = new JsonReader(new StringReader("foo"));
    reader.setStrictness(Strictness.STRICT);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> Streams.parse(reader));
  }

  /**
   * Test {@link Streams#parse(JsonReader)}.
   *
   * <ul>
   *   <li>Then return AsString is {@code Use}.
   * </ul>
   *
   * <p>Method under test: {@link Streams#parse(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Streams.parse(JsonReader)"})
  public void testParse_thenReturnAsStringIsUse() throws JsonParseException {
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
    JsonPrimitive actualAsJsonPrimitive = actualParseResult.getAsJsonPrimitive();
    assertSame(actualParseResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link Streams#parse(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link CharArrayReader#CharArrayReader(char[])} with {@code ﻿} toCharArray.
   * </ul>
   *
   * <p>Method under test: {@link Streams#parse(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Streams.parse(JsonReader)"})
  public void testParse_whenCharArrayReaderWithZeroWidthNoBreakSpaceToCharArray()
      throws JsonParseException {
    // Arrange
    CharArrayReader in = new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray());

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> Streams.parse(new JsonReader(in)));
  }

  /**
   * Test {@link Streams#parse(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link FileReader#FileReader(FileDescriptor)} with {@link
   *       FileDescriptor#FileDescriptor()}.
   *   <li>Then throw {@link JsonIOException}.
   * </ul>
   *
   * <p>Method under test: {@link Streams#parse(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Streams.parse(JsonReader)"})
  public void testParse_whenFileReaderWithFileDescriptor_thenThrowJsonIOException()
      throws JsonParseException {
    // Arrange, Act and Assert
    assertThrows(
        JsonIOException.class,
        () -> Streams.parse(new JsonReader(new FileReader(new FileDescriptor()))));
  }

  /**
   * Test {@link Streams#parse(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonArray#JsonArray(int)} with capacity is three.
   *   <li>Then return {@link JsonArray}.
   * </ul>
   *
   * <p>Method under test: {@link Streams#parse(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Streams.parse(JsonReader)"})
  public void testParse_whenJsonArrayWithCapacityIsThree_thenReturnJsonArray()
      throws JsonParseException {
    // Arrange and Act
    JsonElement actualParseResult = Streams.parse(new JsonTreeReader(new JsonArray(3)));

    // Assert
    assertTrue(actualParseResult instanceof JsonArray);
    assertEquals(0, ((JsonArray) actualParseResult).size());
    assertFalse(((JsonArray) actualParseResult).iterator().hasNext());
    assertTrue(((JsonArray) actualParseResult).isEmpty());
    assertTrue(actualParseResult.isJsonArray());
    JsonArray actualAsJsonArray = actualParseResult.getAsJsonArray();
    assertSame(actualParseResult, actualAsJsonArray);
  }

  /**
   * Test {@link Streams#parse(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.
   *   <li>Then return AsBoolean.
   * </ul>
   *
   * <p>Method under test: {@link Streams#parse(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Streams.parse(JsonReader)"})
  public void testParse_whenJsonPrimitiveWithBoolIsTrue_thenReturnAsBoolean()
      throws JsonParseException {
    // Arrange and Act
    JsonElement actualParseResult = Streams.parse(new JsonTreeReader(new JsonPrimitive(true)));

    // Assert
    assertTrue(actualParseResult instanceof JsonPrimitive);
    assertTrue(actualParseResult.getAsBoolean());
    assertEquals(Boolean.TRUE.toString(), actualParseResult.getAsString());
    JsonPrimitive actualAsJsonPrimitive = actualParseResult.getAsJsonPrimitive();
    assertSame(actualParseResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link Streams#parse(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonPrimitive#JsonPrimitive(String)} with {@code String}.
   *   <li>Then return AsString is {@code String}.
   * </ul>
   *
   * <p>Method under test: {@link Streams#parse(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Streams.parse(JsonReader)"})
  public void testParse_whenJsonPrimitiveWithString_thenReturnAsStringIsString()
      throws JsonParseException {
    // Arrange and Act
    JsonElement actualParseResult = Streams.parse(new JsonTreeReader(new JsonPrimitive("String")));

    // Assert
    assertTrue(actualParseResult instanceof JsonPrimitive);
    Number asNumber = actualParseResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("String", actualParseResult.getAsString());
    assertEquals("String", asNumber.toString());
    assertEquals('S', actualParseResult.getAsCharacter());
    JsonPrimitive actualAsJsonPrimitive = actualParseResult.getAsJsonPrimitive();
    assertSame(actualParseResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link Streams#parse(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE}.
   *   <li>Then return {@link JsonNull}.
   * </ul>
   *
   * <p>Method under test: {@link Streams#parse(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Streams.parse(JsonReader)"})
  public void testParse_whenJsonTreeReaderWithElementIsInstance_thenReturnJsonNull()
      throws JsonParseException {
    // Arrange and Act
    JsonElement actualParseResult = Streams.parse(new JsonTreeReader(JsonNull.INSTANCE));

    // Assert
    assertTrue(actualParseResult instanceof JsonNull);
    assertTrue(actualParseResult.isJsonNull());
    assertSame(((JsonNull) actualParseResult).INSTANCE, actualParseResult.getAsJsonNull());
  }

  /**
   * Test {@link Streams#parse(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonObject} (default constructor).
   *   <li>Then return {@link JsonObject}.
   * </ul>
   *
   * <p>Method under test: {@link Streams#parse(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Streams.parse(JsonReader)"})
  public void testParse_whenJsonTreeReaderWithElementIsJsonObject_thenReturnJsonObject()
      throws JsonParseException {
    // Arrange and Act
    JsonElement actualParseResult = Streams.parse(new JsonTreeReader(new JsonObject()));

    // Assert
    assertTrue(actualParseResult instanceof JsonObject);
    assertEquals(0, ((JsonObject) actualParseResult).size());
    assertTrue(actualParseResult.isJsonObject());
    assertTrue(((JsonObject) actualParseResult).isEmpty());
    JsonObject actualAsJsonObject = actualParseResult.getAsJsonObject();
    assertSame(actualParseResult, actualAsJsonObject);
  }

  /**
   * Test {@link Streams#parse(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then return AsString is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link Streams#parse(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Streams.parse(JsonReader)"})
  public void testParse_whenStringReaderWith42_thenReturnAsStringIs42() throws JsonParseException {
    // Arrange and Act
    JsonElement actualParseResult = Streams.parse(new JsonReader(new StringReader("42")));

    // Assert
    assertTrue(actualParseResult instanceof JsonPrimitive);
    assertEquals("42", actualParseResult.getAsString());
    assertEquals('4', actualParseResult.getAsCharacter());
    assertEquals(42, actualParseResult.getAsInt());
    assertEquals(42.0d, actualParseResult.getAsDouble(), 0.0);
    assertEquals(42.0f, actualParseResult.getAsFloat(), 0.0f);
    assertEquals(42L, actualParseResult.getAsLong());
    assertEquals((short) 42, actualParseResult.getAsShort());
    assertTrue(((JsonPrimitive) actualParseResult).isNumber());
    assertEquals(new BigDecimal("42"), actualParseResult.getAsBigDecimal());
    assertEquals('*', actualParseResult.getAsByte());
    JsonPrimitive actualAsJsonPrimitive = actualParseResult.getAsJsonPrimitive();
    assertSame(actualParseResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link Streams#parse(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code at line}.
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link Streams#parse(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Streams.parse(JsonReader)"})
  public void testParse_whenStringReaderWithAtLine_thenThrowJsonSyntaxException()
      throws JsonParseException {
    // Arrange, Act and Assert
    assertThrows(
        JsonSyntaxException.class,
        () -> Streams.parse(new JsonReader(new StringReader(" at line "))));
  }

  /**
   * Test {@link Streams#parse(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then return {@link JsonNull}.
   * </ul>
   *
   * <p>Method under test: {@link Streams#parse(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Streams.parse(JsonReader)"})
  public void testParse_whenStringReaderWithEmptyString_thenReturnJsonNull()
      throws JsonParseException {
    // Arrange and Act
    JsonElement actualParseResult = Streams.parse(new JsonReader(new StringReader("")));

    // Assert
    assertTrue(actualParseResult instanceof JsonNull);
    assertTrue(actualParseResult.isJsonNull());
    assertSame(((JsonNull) actualParseResult).INSTANCE, actualParseResult.getAsJsonNull());
  }

  /**
   * Test {@link Streams#parse(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then return Boolean.
   * </ul>
   *
   * <p>Method under test: {@link Streams#parse(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Streams.parse(JsonReader)"})
  public void testParse_whenStringReaderWithFalseToString_thenReturnBoolean()
      throws JsonParseException {
    // Arrange
    StringReader in = new StringReader(Boolean.FALSE.toString());

    // Act
    JsonElement actualParseResult = Streams.parse(new JsonReader(in));

    // Assert
    assertTrue(actualParseResult instanceof JsonPrimitive);
    assertEquals('f', actualParseResult.getAsCharacter());
    assertTrue(((JsonPrimitive) actualParseResult).isBoolean());
    assertEquals(Boolean.FALSE.toString(), actualParseResult.getAsString());
    JsonPrimitive actualAsJsonPrimitive = actualParseResult.getAsJsonPrimitive();
    assertSame(actualParseResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link Streams#parse(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code FALSE}.
   *   <li>Then return Boolean.
   * </ul>
   *
   * <p>Method under test: {@link Streams#parse(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Streams.parse(JsonReader)"})
  public void testParse_whenStringReaderWithFalse_thenReturnBoolean() throws JsonParseException {
    // Arrange and Act
    JsonElement actualParseResult = Streams.parse(new JsonReader(new StringReader("FALSE")));

    // Assert
    assertTrue(actualParseResult instanceof JsonPrimitive);
    assertEquals('f', actualParseResult.getAsCharacter());
    assertTrue(((JsonPrimitive) actualParseResult).isBoolean());
    assertEquals(Boolean.FALSE.toString(), actualParseResult.getAsString());
    JsonPrimitive actualAsJsonPrimitive = actualParseResult.getAsJsonPrimitive();
    assertSame(actualParseResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link Streams#parse(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link Streams#parse(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Streams.parse(JsonReader)"})
  public void testParse_whenStringReaderWithFoo_thenThrowJsonSyntaxException()
      throws JsonParseException {
    // Arrange, Act and Assert
    assertThrows(
        JsonSyntaxException.class, () -> Streams.parse(new JsonReader(new StringReader("foo"))));
  }

  /**
   * Test {@link Streams#parse(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code See}.
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link Streams#parse(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Streams.parse(JsonReader)"})
  public void testParse_whenStringReaderWithSee_thenThrowJsonSyntaxException()
      throws JsonParseException {
    // Arrange, Act and Assert
    assertThrows(
        JsonSyntaxException.class, () -> Streams.parse(new JsonReader(new StringReader("\nSee "))));
  }

  /**
   * Test {@link Streams#write(JsonElement, JsonWriter)}.
   *
   * <p>Method under test: {@link Streams#write(JsonElement, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Streams.write(JsonElement, JsonWriter)"})
  public void testWrite() throws IOException {
    // Arrange
    JsonArray element = new JsonArray(3);
    JsonWriter writer = new JsonWriter(new StringWriter());

    // Act
    Streams.write(element, writer);

    // Assert
    assertEquals("[]", writer.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        writer.getStack());
  }

  /**
   * Test {@link Streams#write(JsonElement, JsonWriter)}.
   *
   * <p>Method under test: {@link Streams#write(JsonElement, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Streams.write(JsonElement, JsonWriter)"})
  public void testWrite2() throws IOException {
    // Arrange
    JsonObject element = new JsonObject();
    JsonWriter writer = new JsonWriter(new StringWriter());

    // Act
    Streams.write(element, writer);

    // Assert
    assertEquals("{}", writer.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        writer.getStack());
  }

  /**
   * Test {@link Streams#write(JsonElement, JsonWriter)}.
   *
   * <ul>
   *   <li>Given {@code A}.
   *   <li>Then {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()} Out toString is {@code [65,true]}.
   * </ul>
   *
   * <p>Method under test: {@link Streams#write(JsonElement, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Streams.write(JsonElement, JsonWriter)"})
  public void testWrite_givenA_thenJsonWriterWithOutIsStringWriterOutToStringIs65True()
      throws IOException {
    // Arrange
    JsonArray element = new JsonArray(3);
    element.add((byte) 'A');
    element.add(true);
    JsonWriter writer = new JsonWriter(new StringWriter());

    // Act
    Streams.write(element, writer);

    // Assert
    assertEquals("[65,true]", writer.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        writer.getStack());
  }

  /**
   * Test {@link Streams#write(JsonElement, JsonWriter)}.
   *
   * <ul>
   *   <li>Given {@code false}.
   *   <li>Then {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()} Out toString is {@code [false,true]}.
   * </ul>
   *
   * <p>Method under test: {@link Streams#write(JsonElement, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Streams.write(JsonElement, JsonWriter)"})
  public void testWrite_givenFalse_thenJsonWriterWithOutIsStringWriterOutToStringIsFalseTrue()
      throws IOException {
    // Arrange
    JsonArray element = new JsonArray(3);
    element.add(false);
    element.add(true);
    JsonWriter writer = new JsonWriter(new StringWriter());

    // Act
    Streams.write(element, writer);

    // Assert
    assertEquals("[false,true]", writer.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        writer.getStack());
  }

  /**
   * Test {@link Streams#write(JsonElement, JsonWriter)}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>Then {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()} Out toString is {@code [10.0,true]}.
   * </ul>
   *
   * <p>Method under test: {@link Streams#write(JsonElement, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Streams.write(JsonElement, JsonWriter)"})
  public void testWrite_givenTen_thenJsonWriterWithOutIsStringWriterOutToStringIs100True()
      throws IOException {
    // Arrange
    JsonArray element = new JsonArray(3);
    element.add(10.0d);
    element.add(true);
    JsonWriter writer = new JsonWriter(new StringWriter());

    // Act
    Streams.write(element, writer);

    // Assert
    assertEquals("[10.0,true]", writer.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        writer.getStack());
  }

  /**
   * Test {@link Streams#write(JsonElement, JsonWriter)}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>Then {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()} Out toString is {@code [10.0,true]}.
   * </ul>
   *
   * <p>Method under test: {@link Streams#write(JsonElement, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Streams.write(JsonElement, JsonWriter)"})
  public void testWrite_givenTen_thenJsonWriterWithOutIsStringWriterOutToStringIs100True2()
      throws IOException {
    // Arrange
    JsonArray element = new JsonArray(3);
    element.add(10.0f);
    element.add(true);
    JsonWriter writer = new JsonWriter(new StringWriter());

    // Act
    Streams.write(element, writer);

    // Assert
    assertEquals("[10.0,true]", writer.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        writer.getStack());
  }

  /**
   * Test {@link Streams#write(JsonElement, JsonWriter)}.
   *
   * <ul>
   *   <li>Given three.
   *   <li>When {@link JsonArray#JsonArray(int)} with capacity is three add three.
   * </ul>
   *
   * <p>Method under test: {@link Streams#write(JsonElement, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Streams.write(JsonElement, JsonWriter)"})
  public void testWrite_givenThree_whenJsonArrayWithCapacityIsThreeAddThree() throws IOException {
    // Arrange
    JsonArray element = new JsonArray(3);
    element.add(3L);
    element.add(true);
    JsonWriter writer = new JsonWriter(new StringWriter());

    // Act
    Streams.write(element, writer);

    // Assert
    assertEquals("[3,true]", writer.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        writer.getStack());
  }

  /**
   * Test {@link Streams#write(JsonElement, JsonWriter)}.
   *
   * <ul>
   *   <li>Given three.
   *   <li>When {@link JsonArray#JsonArray(int)} with capacity is three add three.
   * </ul>
   *
   * <p>Method under test: {@link Streams#write(JsonElement, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Streams.write(JsonElement, JsonWriter)"})
  public void testWrite_givenThree_whenJsonArrayWithCapacityIsThreeAddThree2() throws IOException {
    // Arrange
    JsonArray element = new JsonArray(3);
    element.add((short) 3);
    element.add(true);
    JsonWriter writer = new JsonWriter(new StringWriter());

    // Act
    Streams.write(element, writer);

    // Assert
    assertEquals("[3,true]", writer.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        writer.getStack());
  }

  /**
   * Test {@link Streams#write(JsonElement, JsonWriter)}.
   *
   * <ul>
   *   <li>Given valueOf three.
   *   <li>When {@link JsonArray#JsonArray(int)} with capacity is three add valueOf three.
   * </ul>
   *
   * <p>Method under test: {@link Streams#write(JsonElement, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Streams.write(JsonElement, JsonWriter)"})
  public void testWrite_givenValueOfThree_whenJsonArrayWithCapacityIsThreeAddValueOfThree()
      throws IOException {
    // Arrange
    JsonArray element = new JsonArray(3);
    element.add(Integer.valueOf(3));
    element.add(true);
    JsonWriter writer = new JsonWriter(new StringWriter());

    // Act
    Streams.write(element, writer);

    // Assert
    assertEquals("[3,true]", writer.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        writer.getStack());
  }

  /**
   * Test {@link Streams#write(JsonElement, JsonWriter)}.
   *
   * <ul>
   *   <li>Then {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()} Out toString is {@code "null"}.
   * </ul>
   *
   * <p>Method under test: {@link Streams#write(JsonElement, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Streams.write(JsonElement, JsonWriter)"})
  public void testWrite_thenJsonWriterWithOutIsStringWriterOutToStringIsNull() throws IOException {
    // Arrange
    JsonPrimitive element = new JsonPrimitive("null");
    JsonWriter writer = new JsonWriter(new StringWriter());

    // Act
    Streams.write(element, writer);

    // Assert
    assertEquals("\"null\"", writer.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        writer.getStack());
  }

  /**
   * Test {@link Streams#write(JsonElement, JsonWriter)}.
   *
   * <ul>
   *   <li>Then {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()} Out toString is {@code {"null":null,"Property":null}}.
   * </ul>
   *
   * <p>Method under test: {@link Streams#write(JsonElement, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Streams.write(JsonElement, JsonWriter)"})
  public void testWrite_thenJsonWriterWithOutIsStringWriterOutToStringIsNullNullPropertyNull()
      throws IOException {
    // Arrange
    JsonObject element = new JsonObject();
    element.add("null", JsonNull.INSTANCE);
    element.add("Property", JsonNull.INSTANCE);
    JsonWriter writer = new JsonWriter(new StringWriter());

    // Act
    Streams.write(element, writer);

    // Assert
    assertEquals("{\"null\":null,\"Property\":null}", writer.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        writer.getStack());
  }

  /**
   * Test {@link Streams#write(JsonElement, JsonWriter)}.
   *
   * <ul>
   *   <li>Then {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()} Out toString is {@code [null,true]}.
   * </ul>
   *
   * <p>Method under test: {@link Streams#write(JsonElement, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Streams.write(JsonElement, JsonWriter)"})
  public void testWrite_thenJsonWriterWithOutIsStringWriterOutToStringIsNullTrue()
      throws IOException {
    // Arrange
    JsonArray element = new JsonArray(3);
    element.add(JsonNull.INSTANCE);
    element.add(true);
    JsonWriter writer = new JsonWriter(new StringWriter());

    // Act
    Streams.write(element, writer);

    // Assert
    assertEquals("[null,true]", writer.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        writer.getStack());
  }

  /**
   * Test {@link Streams#write(JsonElement, JsonWriter)}.
   *
   * <ul>
   *   <li>Then {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()} Out toString is {@code {"Property":null}}.
   * </ul>
   *
   * <p>Method under test: {@link Streams#write(JsonElement, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Streams.write(JsonElement, JsonWriter)"})
  public void testWrite_thenJsonWriterWithOutIsStringWriterOutToStringIsPropertyNull()
      throws IOException {
    // Arrange
    JsonObject element = new JsonObject();
    element.add("Property", JsonNull.INSTANCE);
    JsonWriter writer = new JsonWriter(new StringWriter());

    // Act
    Streams.write(element, writer);

    // Assert
    assertEquals("{\"Property\":null}", writer.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        writer.getStack());
  }

  /**
   * Test {@link Streams#write(JsonElement, JsonWriter)}.
   *
   * <ul>
   *   <li>Then {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()} Out toString is {@code [true]}.
   * </ul>
   *
   * <p>Method under test: {@link Streams#write(JsonElement, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Streams.write(JsonElement, JsonWriter)"})
  public void testWrite_thenJsonWriterWithOutIsStringWriterOutToStringIsTrue() throws IOException {
    // Arrange
    JsonArray element = new JsonArray(3);
    element.add(true);
    JsonWriter writer = new JsonWriter(new StringWriter());

    // Act
    Streams.write(element, writer);

    // Assert
    assertEquals("[true]", writer.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        writer.getStack());
  }

  /**
   * Test {@link Streams#write(JsonElement, JsonWriter)}.
   *
   * <ul>
   *   <li>Then {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()} Out toString is {@link Boolean#TRUE} toString.
   * </ul>
   *
   * <p>Method under test: {@link Streams#write(JsonElement, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Streams.write(JsonElement, JsonWriter)"})
  public void testWrite_thenJsonWriterWithOutIsStringWriterOutToStringIsTrueToString()
      throws IOException {
    // Arrange
    JsonPrimitive element = new JsonPrimitive(true);
    JsonWriter writer = new JsonWriter(new StringWriter());

    // Act
    Streams.write(element, writer);

    // Assert
    assertEquals(Boolean.TRUE.toString(), writer.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        writer.getStack());
  }

  /**
   * Test {@link Streams#write(JsonElement, JsonWriter)}.
   *
   * <ul>
   *   <li>Then {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()} Out toString is {@code ["\u0001",true]}.
   * </ul>
   *
   * <p>Method under test: {@link Streams#write(JsonElement, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Streams.write(JsonElement, JsonWriter)"})
  public void testWrite_thenJsonWriterWithOutIsStringWriterOutToStringIsU0001True()
      throws IOException {
    // Arrange
    JsonArray element = new JsonArray(3);
    element.add('\u0001');
    element.add(true);
    JsonWriter writer = new JsonWriter(new StringWriter());

    // Act
    Streams.write(element, writer);

    // Assert
    assertEquals("[\"\\u0001\",true]", writer.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        writer.getStack());
  }

  /**
   * Test {@link Streams#write(JsonElement, JsonWriter)}.
   *
   * <ul>
   *   <li>Then {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()} Out toString is {@code "\u0003"}.
   * </ul>
   *
   * <p>Method under test: {@link Streams#write(JsonElement, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Streams.write(JsonElement, JsonWriter)"})
  public void testWrite_thenJsonWriterWithOutIsStringWriterOutToStringIsU0003() throws IOException {
    // Arrange
    JsonPrimitive element = new JsonPrimitive('\u0003');
    JsonWriter writer = new JsonWriter(new StringWriter());

    // Act
    Streams.write(element, writer);

    // Assert
    assertEquals("\"\\u0003\"", writer.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        writer.getStack());
  }

  /**
   * Test {@link Streams#write(JsonElement, JsonWriter)}.
   *
   * <ul>
   *   <li>When {@link JsonNull#INSTANCE}.
   *   <li>Then {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()} Out toString is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Streams#write(JsonElement, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Streams.write(JsonElement, JsonWriter)"})
  public void testWrite_whenInstance_thenJsonWriterWithOutIsStringWriterOutToStringIsNull()
      throws IOException {
    // Arrange
    JsonWriter writer = new JsonWriter(new StringWriter());

    // Act
    Streams.write(JsonNull.INSTANCE, writer);

    // Assert
    assertEquals("null", writer.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        writer.getStack());
  }

  /**
   * Test {@link Streams#write(JsonElement, JsonWriter)}.
   *
   * <ul>
   *   <li>When {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()} HtmlSafe is {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link Streams#write(JsonElement, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Streams.write(JsonElement, JsonWriter)"})
  public void testWrite_whenJsonWriterWithOutIsStringWriterHtmlSafeIsTrue() throws IOException {
    // Arrange
    JsonPrimitive element = new JsonPrimitive("null");

    JsonWriter writer = new JsonWriter(new StringWriter());
    writer.setHtmlSafe(true);

    // Act
    Streams.write(element, writer);

    // Assert
    assertEquals("\"null\"", writer.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        writer.getStack());
  }

  /**
   * Test {@link Streams#writerForAppendable(Appendable)}.
   *
   * <ul>
   *   <li>When {@link CharArrayWriter#CharArrayWriter()}.
   *   <li>Then return {@link CharArrayWriter}.
   * </ul>
   *
   * <p>Method under test: {@link Streams#writerForAppendable(Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Writer Streams.writerForAppendable(Appendable)"})
  public void testWriterForAppendable_whenCharArrayWriter_thenReturnCharArrayWriter() {
    // Arrange and Act
    Writer actualWriterForAppendableResult = Streams.writerForAppendable(new CharArrayWriter());

    // Assert
    assertTrue(actualWriterForAppendableResult instanceof CharArrayWriter);
    assertEquals(0, ((CharArrayWriter) actualWriterForAppendableResult).size());
  }
}
