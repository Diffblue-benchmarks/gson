package com.google.gson.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    // Arrange, Act and Assert
    assertThrows(
        JsonSyntaxException.class,
        () ->
            Streams.parse(
                new JsonReader(new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray()))));
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
    JsonNull expectedAsJsonNull = ((JsonNull) actualParseResult).INSTANCE;
    assertSame(expectedAsJsonNull, actualParseResult.getAsJsonNull());
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
    assertSame(actualParseResult, actualParseResult.getAsJsonPrimitive());
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
    JsonReader reader = new JsonReader(new StringReader(Boolean.FALSE.toString()));
    reader.setStrictness(Strictness.LENIENT);

    // Act
    JsonElement actualParseResult = Streams.parse(reader);

    // Assert
    assertTrue(actualParseResult instanceof JsonPrimitive);
    assertEquals('f', actualParseResult.getAsCharacter());
    assertTrue(((JsonPrimitive) actualParseResult).isBoolean());
    String expectedAsString = Boolean.FALSE.toString();
    assertEquals(expectedAsString, actualParseResult.getAsString());
    assertSame(actualParseResult, actualParseResult.getAsJsonPrimitive());
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
    assertSame(actualParseResult, actualParseResult.getAsJsonPrimitive());
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
   *   <li>Then return AsString is {@code nd}.
   * </ul>
   *
   * <p>Method under test: {@link Streams#parse(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Streams.parse(JsonReader)"})
  public void testParse_thenReturnAsStringIsNd() throws JsonParseException, IOException {
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
    assertSame(actualParseResult, actualParseResult.getAsJsonPrimitive());
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
    assertSame(actualParseResult, actualParseResult.getAsJsonPrimitive());
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
    // Arrange, Act and Assert
    assertThrows(
        JsonSyntaxException.class,
        () ->
            Streams.parse(
                new JsonReader(new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray()))));
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
    assertSame(actualParseResult, actualParseResult.getAsJsonArray());
  }

  /**
   * Test {@link Streams#parse(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.
   *   <li>Then return AsCharacter is {@code t}.
   * </ul>
   *
   * <p>Method under test: {@link Streams#parse(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Streams.parse(JsonReader)"})
  public void testParse_whenJsonPrimitiveWithBoolIsTrue_thenReturnAsCharacterIsT()
      throws JsonParseException {
    // Arrange and Act
    JsonElement actualParseResult = Streams.parse(new JsonTreeReader(new JsonPrimitive(true)));

    // Assert
    assertTrue(actualParseResult instanceof JsonPrimitive);
    assertEquals('t', actualParseResult.getAsCharacter());
    assertTrue(actualParseResult.getAsBoolean());
    String expectedAsString = Boolean.TRUE.toString();
    assertEquals(expectedAsString, actualParseResult.getAsString());
    assertSame(actualParseResult, actualParseResult.getAsJsonPrimitive());
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
    assertSame(actualParseResult, actualParseResult.getAsJsonPrimitive());
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
    JsonNull expectedAsJsonNull = ((JsonNull) actualParseResult).INSTANCE;
    assertSame(expectedAsJsonNull, actualParseResult.getAsJsonNull());
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
    assertSame(actualParseResult, actualParseResult.getAsJsonObject());
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
    BigDecimal expectedAsBigDecimal = new BigDecimal("42");
    assertEquals(expectedAsBigDecimal, actualParseResult.getAsBigDecimal());
    assertEquals('*', actualParseResult.getAsByte());
    assertSame(actualParseResult, actualParseResult.getAsJsonPrimitive());
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
    JsonNull expectedAsJsonNull = ((JsonNull) actualParseResult).INSTANCE;
    assertSame(expectedAsJsonNull, actualParseResult.getAsJsonNull());
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
    // Arrange and Act
    JsonElement actualParseResult =
        Streams.parse(new JsonReader(new StringReader(Boolean.FALSE.toString())));

    // Assert
    assertTrue(actualParseResult instanceof JsonPrimitive);
    assertEquals('f', actualParseResult.getAsCharacter());
    assertTrue(((JsonPrimitive) actualParseResult).isBoolean());
    String expectedAsString = Boolean.FALSE.toString();
    assertEquals(expectedAsString, actualParseResult.getAsString());
    assertSame(actualParseResult, actualParseResult.getAsJsonPrimitive());
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
    String expectedAsString = Boolean.FALSE.toString();
    assertEquals(expectedAsString, actualParseResult.getAsString());
    assertSame(actualParseResult, actualParseResult.getAsJsonPrimitive());
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
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link Streams#write(JsonElement, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Streams.write(JsonElement, JsonWriter)"})
  public void testWrite_thenThrowUnsupportedOperationException() throws IOException {
    // Arrange
    JsonElement element = mock(JsonElement.class);
    when(element.isJsonNull()).thenThrow(new UnsupportedOperationException("Couldn't write "));

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> Streams.write(element, new JsonWriter(new StringWriter())));
    verify(element).isJsonNull();
  }

  /**
   * Test {@link Streams#writerForAppendable(Appendable)}.
   *
   * <ul>
   *   <li>When {@link CharArrayWriter#CharArrayWriter(int)} with one.
   *   <li>Then return {@link CharArrayWriter}.
   * </ul>
   *
   * <p>Method under test: {@link Streams#writerForAppendable(Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Writer Streams.writerForAppendable(Appendable)"})
  public void testWriterForAppendable_whenCharArrayWriterWithOne_thenReturnCharArrayWriter() {
    // Arrange and Act
    Writer actualWriterForAppendableResult = Streams.writerForAppendable(new CharArrayWriter(1));

    // Assert
    assertTrue(actualWriterForAppendableResult instanceof CharArrayWriter);
    assertEquals(0, ((CharArrayWriter) actualWriterForAppendableResult).size());
  }
}
