package com.google.gson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.stream.JsonReader;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JsonParserDiffblueTest {
  /**
   * Test {@link JsonParser#parseString(String)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return AsString is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parseString(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parseString(String)"})
  public void testParseString_when42_thenReturnAsStringIs42() throws JsonSyntaxException {
    // Arrange and Act
    JsonElement actualParseStringResult = JsonParser.parseString("42");

    // Assert
    assertTrue(actualParseStringResult instanceof JsonPrimitive);
    assertEquals("42", actualParseStringResult.getAsString());
    assertEquals('4', actualParseStringResult.getAsCharacter());
    assertEquals(42, actualParseStringResult.getAsInt());
    assertEquals(42.0d, actualParseStringResult.getAsDouble(), 0.0);
    assertEquals(42.0f, actualParseStringResult.getAsFloat(), 0.0f);
    assertEquals(42L, actualParseStringResult.getAsLong());
    assertEquals((short) 42, actualParseStringResult.getAsShort());
    assertFalse(((JsonPrimitive) actualParseStringResult).isString());
    assertTrue(((JsonPrimitive) actualParseStringResult).isNumber());
    assertEquals(new BigDecimal("42"), actualParseStringResult.getAsBigDecimal());
    assertEquals('*', actualParseStringResult.getAsByte());
    JsonPrimitive actualAsJsonPrimitive = actualParseStringResult.getAsJsonPrimitive();
    assertSame(actualParseStringResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonParser#parseString(String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return {@link JsonNull}.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parseString(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parseString(String)"})
  public void testParseString_whenEmptyString_thenReturnJsonNull() throws JsonSyntaxException {
    // Arrange and Act
    JsonElement actualParseStringResult = JsonParser.parseString("");

    // Assert
    assertTrue(actualParseStringResult instanceof JsonNull);
    assertFalse(actualParseStringResult.isJsonPrimitive());
    assertTrue(actualParseStringResult.isJsonNull());
    assertSame(
        ((JsonNull) actualParseStringResult).INSTANCE, actualParseStringResult.getAsJsonNull());
  }

  /**
   * Test {@link JsonParser#parseString(String)}.
   *
   * <ul>
   *   <li>When {@code in == null}.
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parseString(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parseString(String)"})
  public void testParseString_whenInNull_thenThrowJsonSyntaxException() throws JsonSyntaxException {
    // Arrange, Act and Assert
    assertThrows(JsonSyntaxException.class, () -> JsonParser.parseString("in == null"));
  }

  /**
   * Test {@link JsonParser#parseString(String)}.
   *
   * <ul>
   *   <li>When {@code Json}.
   *   <li>Then AsNumber return {@link LazilyParsedNumber}.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parseString(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parseString(String)"})
  public void testParseString_whenJson_thenAsNumberReturnLazilyParsedNumber()
      throws JsonSyntaxException {
    // Arrange and Act
    JsonElement actualParseStringResult = JsonParser.parseString("Json");

    // Assert
    assertTrue(actualParseStringResult instanceof JsonPrimitive);
    Number asNumber = actualParseStringResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("Json", actualParseStringResult.getAsString());
    assertEquals("Json", asNumber.toString());
    assertEquals('J', actualParseStringResult.getAsCharacter());
    assertFalse(((JsonPrimitive) actualParseStringResult).isNumber());
    assertTrue(((JsonPrimitive) actualParseStringResult).isString());
    JsonPrimitive actualAsJsonPrimitive = actualParseStringResult.getAsJsonPrimitive();
    assertSame(actualParseStringResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonParser#parseReader(JsonReader)} with {@code JsonReader}.
   *
   * <p>Method under test: {@link JsonParser#parseReader(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parseReader(JsonReader)"})
  public void testParseReaderWithJsonReader() throws JsonIOException, JsonSyntaxException {
    // Arrange
    CharArrayReader in = new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray());

    JsonReader reader = new JsonReader(in);
    reader.setStrictness(Strictness.LENIENT);

    // Act
    JsonElement actualParseReaderResult = JsonParser.parseReader(reader);

    // Assert
    assertTrue(actualParseReaderResult instanceof JsonPrimitive);
    Number asNumber = actualParseReaderResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("\u0001\u0006\u0001\u0006", actualParseReaderResult.getAsString());
    assertEquals("\u0001\u0006\u0001\u0006", asNumber.toString());
    assertEquals('\u0001', actualParseReaderResult.getAsCharacter());
    JsonPrimitive actualAsJsonPrimitive = actualParseReaderResult.getAsJsonPrimitive();
    assertSame(actualParseReaderResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonParser#parseReader(JsonReader)} with {@code JsonReader}.
   *
   * <p>Method under test: {@link JsonParser#parseReader(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parseReader(JsonReader)"})
  public void testParseReaderWithJsonReader2() throws JsonIOException, JsonSyntaxException {
    // Arrange
    CharArrayReader in = new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray());

    JsonReader reader = new JsonReader(in);
    reader.setStrictness(Strictness.LENIENT);

    // Act
    JsonElement actualParseReaderResult = JsonParser.parseReader(reader);

    // Assert
    assertTrue(actualParseReaderResult instanceof JsonPrimitive);
    Number asNumber = actualParseReaderResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("\u0006\u0001\u0006", actualParseReaderResult.getAsString());
    assertEquals("\u0006\u0001\u0006", asNumber.toString());
    assertEquals('\u0006', actualParseReaderResult.getAsCharacter());
    JsonPrimitive actualAsJsonPrimitive = actualParseReaderResult.getAsJsonPrimitive();
    assertSame(actualParseReaderResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonParser#parseReader(JsonReader)} with {@code JsonReader}.
   *
   * <ul>
   *   <li>Then not {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE} hasNext.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parseReader(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parseReader(JsonReader)"})
  public void testParseReaderWithJsonReader_thenNotJsonTreeReaderWithElementIsInstanceHasNext()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    JsonTreeReader reader = new JsonTreeReader(JsonNull.INSTANCE);

    // Act
    JsonElement actualParseReaderResult = JsonParser.parseReader(reader);

    // Assert
    assertTrue(actualParseReaderResult instanceof JsonNull);
    assertFalse(actualParseReaderResult.isJsonPrimitive());
    assertFalse(reader.hasNext());
    assertTrue(actualParseReaderResult.isJsonNull());
    assertSame(
        ((JsonNull) actualParseReaderResult).INSTANCE, actualParseReaderResult.getAsJsonNull());
  }

  /**
   * Test {@link JsonParser#parseReader(JsonReader)} with {@code JsonReader}.
   *
   * <ul>
   *   <li>Then return AsString is {@code e}.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parseReader(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parseReader(JsonReader)"})
  public void testParseReaderWithJsonReader_thenReturnAsStringIsE()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    StringReader in = new StringReader("\nSee ");
    in.read("\u0001\u0006\u0001\u0006".toCharArray(), 1, 3);

    JsonReader reader = new JsonReader(in);
    reader.setStrictness(Strictness.LENIENT);

    // Act
    JsonElement actualParseReaderResult = JsonParser.parseReader(reader);

    // Assert
    assertTrue(actualParseReaderResult instanceof JsonPrimitive);
    Number asNumber = actualParseReaderResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("e", actualParseReaderResult.getAsString());
    assertEquals("e", asNumber.toString());
    assertEquals('e', actualParseReaderResult.getAsCharacter());
    JsonPrimitive actualAsJsonPrimitive = actualParseReaderResult.getAsJsonPrimitive();
    assertSame(actualParseReaderResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonParser#parseReader(JsonReader)} with {@code JsonReader}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then return AsString is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parseReader(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parseReader(JsonReader)"})
  public void testParseReaderWithJsonReader_whenStringReaderWith42_thenReturnAsStringIs42()
      throws JsonIOException, JsonSyntaxException {
    // Arrange and Act
    JsonElement actualParseReaderResult =
        JsonParser.parseReader(new JsonReader(new StringReader("42")));

    // Assert
    assertTrue(actualParseReaderResult instanceof JsonPrimitive);
    assertEquals("42", actualParseReaderResult.getAsString());
    assertEquals('4', actualParseReaderResult.getAsCharacter());
    assertEquals(42, actualParseReaderResult.getAsInt());
    assertEquals(42.0d, actualParseReaderResult.getAsDouble(), 0.0);
    assertEquals(42.0f, actualParseReaderResult.getAsFloat(), 0.0f);
    assertEquals(42L, actualParseReaderResult.getAsLong());
    assertEquals((short) 42, actualParseReaderResult.getAsShort());
    assertTrue(((JsonPrimitive) actualParseReaderResult).isNumber());
    assertEquals(new BigDecimal("42"), actualParseReaderResult.getAsBigDecimal());
    assertEquals('*', actualParseReaderResult.getAsByte());
    JsonPrimitive actualAsJsonPrimitive = actualParseReaderResult.getAsJsonPrimitive();
    assertSame(actualParseReaderResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonParser#parseReader(JsonReader)} with {@code JsonReader}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code at line}.
   *   <li>Then return AsString is {@code at}.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parseReader(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parseReader(JsonReader)"})
  public void testParseReaderWithJsonReader_whenStringReaderWithAtLine_thenReturnAsStringIsAt()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    JsonReader reader = new JsonReader(new StringReader(" at line "));
    reader.setStrictness(Strictness.LENIENT);

    // Act
    JsonElement actualParseReaderResult = JsonParser.parseReader(reader);

    // Assert
    assertTrue(actualParseReaderResult instanceof JsonPrimitive);
    Number asNumber = actualParseReaderResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("at", actualParseReaderResult.getAsString());
    assertEquals("at", asNumber.toString());
    assertEquals('a', actualParseReaderResult.getAsCharacter());
    JsonPrimitive actualAsJsonPrimitive = actualParseReaderResult.getAsJsonPrimitive();
    assertSame(actualParseReaderResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonParser#parseReader(JsonReader)} with {@code JsonReader}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then return {@link JsonNull}.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parseReader(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parseReader(JsonReader)"})
  public void testParseReaderWithJsonReader_whenStringReaderWithEmptyString_thenReturnJsonNull()
      throws JsonIOException, JsonSyntaxException {
    // Arrange and Act
    JsonElement actualParseReaderResult =
        JsonParser.parseReader(new JsonReader(new StringReader("")));

    // Assert
    assertTrue(actualParseReaderResult instanceof JsonNull);
    assertFalse(actualParseReaderResult.isJsonPrimitive());
    assertTrue(actualParseReaderResult.isJsonNull());
    assertSame(
        ((JsonNull) actualParseReaderResult).INSTANCE, actualParseReaderResult.getAsJsonNull());
  }

  /**
   * Test {@link JsonParser#parseReader(JsonReader)} with {@code JsonReader}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then return {@link JsonNull}.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parseReader(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parseReader(JsonReader)"})
  public void testParseReaderWithJsonReader_whenStringReaderWithEmptyString_thenReturnJsonNull2()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    JsonReader reader = new JsonReader(new StringReader(""));
    reader.setStrictness(Strictness.STRICT);

    // Act
    JsonElement actualParseReaderResult = JsonParser.parseReader(reader);

    // Assert
    assertTrue(actualParseReaderResult instanceof JsonNull);
    assertFalse(actualParseReaderResult.isJsonPrimitive());
    assertTrue(actualParseReaderResult.isJsonNull());
    assertSame(
        ((JsonNull) actualParseReaderResult).INSTANCE, actualParseReaderResult.getAsJsonNull());
  }

  /**
   * Test {@link JsonParser#parseReader(JsonReader)} with {@code JsonReader}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parseReader(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parseReader(JsonReader)"})
  public void testParseReaderWithJsonReader_whenStringReaderWithFalseToString()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    StringReader in = new StringReader(Boolean.FALSE.toString());

    // Act
    JsonElement actualParseReaderResult = JsonParser.parseReader(new JsonReader(in));

    // Assert
    assertTrue(actualParseReaderResult instanceof JsonPrimitive);
    assertFalse(((JsonPrimitive) actualParseReaderResult).isString());
    assertTrue(((JsonPrimitive) actualParseReaderResult).isBoolean());
    assertEquals(Boolean.FALSE.toString(), actualParseReaderResult.getAsString());
    JsonPrimitive actualAsJsonPrimitive = actualParseReaderResult.getAsJsonPrimitive();
    assertSame(actualParseReaderResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonParser#parseReader(JsonReader)} with {@code JsonReader}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parseReader(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parseReader(JsonReader)"})
  public void testParseReaderWithJsonReader_whenStringReaderWithFalseToString2()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    StringReader in = new StringReader(Boolean.FALSE.toString());

    JsonReader reader = new JsonReader(in);
    reader.setStrictness(Strictness.STRICT);

    // Act
    JsonElement actualParseReaderResult = JsonParser.parseReader(reader);

    // Assert
    assertTrue(actualParseReaderResult instanceof JsonPrimitive);
    assertFalse(((JsonPrimitive) actualParseReaderResult).isString());
    assertTrue(((JsonPrimitive) actualParseReaderResult).isBoolean());
    assertEquals(Boolean.FALSE.toString(), actualParseReaderResult.getAsString());
    JsonPrimitive actualAsJsonPrimitive = actualParseReaderResult.getAsJsonPrimitive();
    assertSame(actualParseReaderResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonParser#parseReader(JsonReader)} with {@code JsonReader}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code FALSE}.
   *   <li>Then return not String.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parseReader(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parseReader(JsonReader)"})
  public void testParseReaderWithJsonReader_whenStringReaderWithFalse_thenReturnNotString()
      throws JsonIOException, JsonSyntaxException {
    // Arrange and Act
    JsonElement actualParseReaderResult =
        JsonParser.parseReader(new JsonReader(new StringReader("FALSE")));

    // Assert
    assertTrue(actualParseReaderResult instanceof JsonPrimitive);
    assertFalse(((JsonPrimitive) actualParseReaderResult).isString());
    assertTrue(((JsonPrimitive) actualParseReaderResult).isBoolean());
    assertEquals(Boolean.FALSE.toString(), actualParseReaderResult.getAsString());
    JsonPrimitive actualAsJsonPrimitive = actualParseReaderResult.getAsJsonPrimitive();
    assertSame(actualParseReaderResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonParser#parseReader(JsonReader)} with {@code JsonReader}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then return AsString is {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parseReader(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parseReader(JsonReader)"})
  public void testParseReaderWithJsonReader_whenStringReaderWithFoo_thenReturnAsStringIsFoo()
      throws JsonIOException, JsonSyntaxException {
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
    JsonPrimitive actualAsJsonPrimitive = actualParseReaderResult.getAsJsonPrimitive();
    assertSame(actualParseReaderResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonParser#parseReader(JsonReader)} with {@code JsonReader}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then return AsString is {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parseReader(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parseReader(JsonReader)"})
  public void testParseReaderWithJsonReader_whenStringReaderWithFoo_thenReturnAsStringIsFoo2()
      throws JsonIOException, JsonSyntaxException {
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
    JsonPrimitive actualAsJsonPrimitive = actualParseReaderResult.getAsJsonPrimitive();
    assertSame(actualParseReaderResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonParser#parseReader(JsonReader)} with {@code JsonReader}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code path}.
   *   <li>Then return AsString is {@code th}.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parseReader(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parseReader(JsonReader)"})
  public void testParseReaderWithJsonReader_whenStringReaderWithPath_thenReturnAsStringIsTh()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    StringReader in = new StringReader(" path ");
    in.read("\u0001\u0006\u0001\u0006".toCharArray(), 1, 3);

    JsonReader reader = new JsonReader(in);
    reader.setStrictness(Strictness.LENIENT);

    // Act
    JsonElement actualParseReaderResult = JsonParser.parseReader(reader);

    // Assert
    assertTrue(actualParseReaderResult instanceof JsonPrimitive);
    Number asNumber = actualParseReaderResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("th", actualParseReaderResult.getAsString());
    assertEquals("th", asNumber.toString());
    assertEquals('t', actualParseReaderResult.getAsCharacter());
    JsonPrimitive actualAsJsonPrimitive = actualParseReaderResult.getAsJsonPrimitive();
    assertSame(actualParseReaderResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonParser#parseReader(JsonReader)} with {@code JsonReader}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code See}.
   *   <li>Then return AsString is {@code See}.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parseReader(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parseReader(JsonReader)"})
  public void testParseReaderWithJsonReader_whenStringReaderWithSee_thenReturnAsStringIsSee()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    JsonReader reader = new JsonReader(new StringReader("\nSee "));
    reader.setStrictness(Strictness.LENIENT);

    // Act
    JsonElement actualParseReaderResult = JsonParser.parseReader(reader);

    // Assert
    assertTrue(actualParseReaderResult instanceof JsonPrimitive);
    Number asNumber = actualParseReaderResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("See", actualParseReaderResult.getAsString());
    assertEquals("See", asNumber.toString());
    assertEquals('S', actualParseReaderResult.getAsCharacter());
    JsonPrimitive actualAsJsonPrimitive = actualParseReaderResult.getAsJsonPrimitive();
    assertSame(actualParseReaderResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonParser#parseReader(Reader)} with {@code Reader}.
   *
   * <ul>
   *   <li>Then AsNumber return {@link LazilyParsedNumber}.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parseReader(Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parseReader(Reader)"})
  public void testParseReaderWithReader_thenAsNumberReturnLazilyParsedNumber()
      throws JsonIOException, JsonSyntaxException {
    // Arrange and Act
    JsonElement actualParseReaderResult = JsonParser.parseReader(new StringReader("foo"));

    // Assert
    assertTrue(actualParseReaderResult instanceof JsonPrimitive);
    Number asNumber = actualParseReaderResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("foo", actualParseReaderResult.getAsString());
    assertEquals("foo", asNumber.toString());
    assertTrue(((JsonPrimitive) actualParseReaderResult).isString());
    JsonPrimitive actualAsJsonPrimitive = actualParseReaderResult.getAsJsonPrimitive();
    assertSame(actualParseReaderResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonParser#parseReader(Reader)} with {@code Reader}.
   *
   * <ul>
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parseReader(Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parseReader(Reader)"})
  public void testParseReaderWithReader_thenThrowJsonSyntaxException()
      throws JsonIOException, JsonSyntaxException {
    // Arrange, Act and Assert
    assertThrows(
        JsonSyntaxException.class, () -> JsonParser.parseReader(new StringReader("in == null")));
  }

  /**
   * Test {@link JsonParser#parseReader(Reader)} with {@code Reader}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then return AsString is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parseReader(Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parseReader(Reader)"})
  public void testParseReaderWithReader_whenStringReaderWith42_thenReturnAsStringIs42()
      throws JsonIOException, JsonSyntaxException {
    // Arrange and Act
    JsonElement actualParseReaderResult = JsonParser.parseReader(new StringReader("42"));

    // Assert
    assertTrue(actualParseReaderResult instanceof JsonPrimitive);
    assertEquals("42", actualParseReaderResult.getAsString());
    assertEquals('4', actualParseReaderResult.getAsCharacter());
    assertEquals(42, actualParseReaderResult.getAsInt());
    assertEquals(42.0d, actualParseReaderResult.getAsDouble(), 0.0);
    assertEquals(42.0f, actualParseReaderResult.getAsFloat(), 0.0f);
    assertEquals(42L, actualParseReaderResult.getAsLong());
    assertEquals((short) 42, actualParseReaderResult.getAsShort());
    assertTrue(((JsonPrimitive) actualParseReaderResult).isNumber());
    assertEquals(new BigDecimal("42"), actualParseReaderResult.getAsBigDecimal());
    assertEquals('*', actualParseReaderResult.getAsByte());
    JsonPrimitive actualAsJsonPrimitive = actualParseReaderResult.getAsJsonPrimitive();
    assertSame(actualParseReaderResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonParser#parseReader(Reader)} with {@code Reader}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then return {@link JsonNull}.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parseReader(Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parseReader(Reader)"})
  public void testParseReaderWithReader_whenStringReaderWithEmptyString_thenReturnJsonNull()
      throws JsonIOException, JsonSyntaxException {
    // Arrange and Act
    JsonElement actualParseReaderResult = JsonParser.parseReader(new StringReader(""));

    // Assert
    assertTrue(actualParseReaderResult instanceof JsonNull);
    assertFalse(actualParseReaderResult.isJsonPrimitive());
    assertTrue(actualParseReaderResult.isJsonNull());
    assertSame(
        ((JsonNull) actualParseReaderResult).INSTANCE, actualParseReaderResult.getAsJsonNull());
  }

  /**
   * Test {@link JsonParser#parseReader(Reader)} with {@code Reader}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parseReader(Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parseReader(Reader)"})
  public void testParseReaderWithReader_whenStringReaderWithFalseToString()
      throws JsonIOException, JsonSyntaxException {
    // Arrange and Act
    JsonElement actualParseReaderResult =
        JsonParser.parseReader(new StringReader(Boolean.FALSE.toString()));

    // Assert
    assertTrue(actualParseReaderResult instanceof JsonPrimitive);
    assertEquals('f', actualParseReaderResult.getAsCharacter());
    assertFalse(((JsonPrimitive) actualParseReaderResult).isNumber());
    assertFalse(((JsonPrimitive) actualParseReaderResult).isString());
    assertTrue(((JsonPrimitive) actualParseReaderResult).isBoolean());
    assertEquals(Boolean.FALSE.toString(), actualParseReaderResult.getAsString());
    JsonPrimitive actualAsJsonPrimitive = actualParseReaderResult.getAsJsonPrimitive();
    assertSame(actualParseReaderResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonParser#parseReader(Reader)} with {@code Reader}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code FALSE}.
   *   <li>Then return AsCharacter is {@code f}.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parseReader(Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parseReader(Reader)"})
  public void testParseReaderWithReader_whenStringReaderWithFalse_thenReturnAsCharacterIsF()
      throws JsonIOException, JsonSyntaxException {
    // Arrange and Act
    JsonElement actualParseReaderResult = JsonParser.parseReader(new StringReader("FALSE"));

    // Assert
    assertTrue(actualParseReaderResult instanceof JsonPrimitive);
    assertEquals('f', actualParseReaderResult.getAsCharacter());
    assertFalse(((JsonPrimitive) actualParseReaderResult).isNumber());
    assertFalse(((JsonPrimitive) actualParseReaderResult).isString());
    assertTrue(((JsonPrimitive) actualParseReaderResult).isBoolean());
    assertEquals(Boolean.FALSE.toString(), actualParseReaderResult.getAsString());
    JsonPrimitive actualAsJsonPrimitive = actualParseReaderResult.getAsJsonPrimitive();
    assertSame(actualParseReaderResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonParser#parse(JsonReader)} with {@code JsonReader}.
   *
   * <p>Method under test: {@link JsonParser#parse(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parse(JsonReader)"})
  public void testParseWithJsonReader() throws JsonIOException, JsonSyntaxException {
    // Arrange
    JsonParser jsonParser = new JsonParser();
    CharArrayReader in = new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray());

    JsonReader json = new JsonReader(in);
    json.setStrictness(Strictness.LENIENT);

    // Act
    JsonElement actualParseResult = jsonParser.parse(json);

    // Assert
    assertTrue(actualParseResult instanceof JsonPrimitive);
    Number asNumber = actualParseResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("\u0001\u0006\u0001\u0006", actualParseResult.getAsString());
    assertEquals("\u0001\u0006\u0001\u0006", asNumber.toString());
    assertEquals('\u0001', actualParseResult.getAsCharacter());
    JsonPrimitive actualAsJsonPrimitive = actualParseResult.getAsJsonPrimitive();
    assertSame(actualParseResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonParser#parse(JsonReader)} with {@code JsonReader}.
   *
   * <ul>
   *   <li>Then not {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE} hasNext.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parse(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parse(JsonReader)"})
  public void testParseWithJsonReader_thenNotJsonTreeReaderWithElementIsInstanceHasNext()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    JsonParser jsonParser = new JsonParser();
    JsonTreeReader json = new JsonTreeReader(JsonNull.INSTANCE);

    // Act
    JsonElement actualParseResult = jsonParser.parse(json);

    // Assert
    assertFalse(json.hasNext());
    assertSame(((JsonNull) actualParseResult).INSTANCE, actualParseResult);
  }

  /**
   * Test {@link JsonParser#parse(JsonReader)} with {@code JsonReader}.
   *
   * <ul>
   *   <li>Then return AsString is acknowledge start of heading acknowledge.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parse(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parse(JsonReader)"})
  public void testParseWithJsonReader_thenReturnAsStringIsAcknowledgeStartOfHeadingAcknowledge()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    JsonParser jsonParser = new JsonParser();
    CharArrayReader in = new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray());

    JsonReader json = new JsonReader(in);
    json.setStrictness(Strictness.LENIENT);

    // Act
    JsonElement actualParseResult = jsonParser.parse(json);

    // Assert
    assertTrue(actualParseResult instanceof JsonPrimitive);
    Number asNumber = actualParseResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("\u0006\u0001\u0006", actualParseResult.getAsString());
    assertEquals("\u0006\u0001\u0006", asNumber.toString());
    assertEquals('\u0006', actualParseResult.getAsCharacter());
    JsonPrimitive actualAsJsonPrimitive = actualParseResult.getAsJsonPrimitive();
    assertSame(actualParseResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonParser#parse(JsonReader)} with {@code JsonReader}.
   *
   * <ul>
   *   <li>Then return AsString is {@code e}.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parse(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parse(JsonReader)"})
  public void testParseWithJsonReader_thenReturnAsStringIsE()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    JsonParser jsonParser = new JsonParser();

    StringReader in = new StringReader("\nSee ");
    in.read("\u0001\u0006\u0001\u0006".toCharArray(), 1, 3);

    JsonReader json = new JsonReader(in);
    json.setStrictness(Strictness.LENIENT);

    // Act
    JsonElement actualParseResult = jsonParser.parse(json);

    // Assert
    assertTrue(actualParseResult instanceof JsonPrimitive);
    Number asNumber = actualParseResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("e", actualParseResult.getAsString());
    assertEquals("e", asNumber.toString());
    assertEquals('e', actualParseResult.getAsCharacter());
    JsonPrimitive actualAsJsonPrimitive = actualParseResult.getAsJsonPrimitive();
    assertSame(actualParseResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonParser#parse(JsonReader)} with {@code JsonReader}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then return AsString is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parse(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parse(JsonReader)"})
  public void testParseWithJsonReader_whenStringReaderWith42_thenReturnAsStringIs42()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    JsonParser jsonParser = new JsonParser();

    // Act
    JsonElement actualParseResult = jsonParser.parse(new JsonReader(new StringReader("42")));

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
   * Test {@link JsonParser#parse(JsonReader)} with {@code JsonReader}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code at line}.
   *   <li>Then return AsString is {@code at}.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parse(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parse(JsonReader)"})
  public void testParseWithJsonReader_whenStringReaderWithAtLine_thenReturnAsStringIsAt()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    JsonParser jsonParser = new JsonParser();

    JsonReader json = new JsonReader(new StringReader(" at line "));
    json.setStrictness(Strictness.LENIENT);

    // Act
    JsonElement actualParseResult = jsonParser.parse(json);

    // Assert
    assertTrue(actualParseResult instanceof JsonPrimitive);
    Number asNumber = actualParseResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("at", actualParseResult.getAsString());
    assertEquals("at", asNumber.toString());
    assertEquals('a', actualParseResult.getAsCharacter());
    JsonPrimitive actualAsJsonPrimitive = actualParseResult.getAsJsonPrimitive();
    assertSame(actualParseResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonParser#parse(JsonReader)} with {@code JsonReader}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then return {@link JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parse(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parse(JsonReader)"})
  public void testParseWithJsonReader_whenStringReaderWithEmptyString_thenReturnInstance()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    JsonParser jsonParser = new JsonParser();

    // Act
    JsonElement actualParseResult = jsonParser.parse(new JsonReader(new StringReader("")));

    // Assert
    assertSame(((JsonNull) actualParseResult).INSTANCE, actualParseResult);
  }

  /**
   * Test {@link JsonParser#parse(JsonReader)} with {@code JsonReader}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then return {@link JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parse(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parse(JsonReader)"})
  public void testParseWithJsonReader_whenStringReaderWithEmptyString_thenReturnInstance2()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    JsonParser jsonParser = new JsonParser();

    JsonReader json = new JsonReader(new StringReader(""));
    json.setStrictness(Strictness.STRICT);

    // Act
    JsonElement actualParseResult = jsonParser.parse(json);

    // Assert
    assertSame(((JsonNull) actualParseResult).INSTANCE, actualParseResult);
  }

  /**
   * Test {@link JsonParser#parse(JsonReader)} with {@code JsonReader}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then return not String.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parse(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parse(JsonReader)"})
  public void testParseWithJsonReader_whenStringReaderWithFalseToString_thenReturnNotString()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    JsonParser jsonParser = new JsonParser();
    StringReader in = new StringReader(Boolean.FALSE.toString());

    // Act
    JsonElement actualParseResult = jsonParser.parse(new JsonReader(in));

    // Assert
    assertTrue(actualParseResult instanceof JsonPrimitive);
    assertFalse(((JsonPrimitive) actualParseResult).isString());
    assertTrue(((JsonPrimitive) actualParseResult).isBoolean());
    assertEquals(Boolean.FALSE.toString(), actualParseResult.getAsString());
    JsonPrimitive actualAsJsonPrimitive = actualParseResult.getAsJsonPrimitive();
    assertSame(actualParseResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonParser#parse(JsonReader)} with {@code JsonReader}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then return not String.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parse(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parse(JsonReader)"})
  public void testParseWithJsonReader_whenStringReaderWithFalseToString_thenReturnNotString2()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    JsonParser jsonParser = new JsonParser();
    StringReader in = new StringReader(Boolean.FALSE.toString());

    JsonReader json = new JsonReader(in);
    json.setStrictness(Strictness.STRICT);

    // Act
    JsonElement actualParseResult = jsonParser.parse(json);

    // Assert
    assertTrue(actualParseResult instanceof JsonPrimitive);
    assertFalse(((JsonPrimitive) actualParseResult).isString());
    assertTrue(((JsonPrimitive) actualParseResult).isBoolean());
    assertEquals(Boolean.FALSE.toString(), actualParseResult.getAsString());
    JsonPrimitive actualAsJsonPrimitive = actualParseResult.getAsJsonPrimitive();
    assertSame(actualParseResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonParser#parse(JsonReader)} with {@code JsonReader}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code FALSE}.
   *   <li>Then return not String.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parse(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parse(JsonReader)"})
  public void testParseWithJsonReader_whenStringReaderWithFalse_thenReturnNotString()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    JsonParser jsonParser = new JsonParser();

    // Act
    JsonElement actualParseResult = jsonParser.parse(new JsonReader(new StringReader("FALSE")));

    // Assert
    assertTrue(actualParseResult instanceof JsonPrimitive);
    assertFalse(((JsonPrimitive) actualParseResult).isString());
    assertTrue(((JsonPrimitive) actualParseResult).isBoolean());
    assertEquals(Boolean.FALSE.toString(), actualParseResult.getAsString());
    JsonPrimitive actualAsJsonPrimitive = actualParseResult.getAsJsonPrimitive();
    assertSame(actualParseResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonParser#parse(JsonReader)} with {@code JsonReader}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then return AsString is {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parse(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parse(JsonReader)"})
  public void testParseWithJsonReader_whenStringReaderWithFoo_thenReturnAsStringIsFoo()
      throws JsonIOException, JsonSyntaxException {
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
    JsonPrimitive actualAsJsonPrimitive = actualParseResult.getAsJsonPrimitive();
    assertSame(actualParseResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonParser#parse(JsonReader)} with {@code JsonReader}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then return AsString is {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parse(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parse(JsonReader)"})
  public void testParseWithJsonReader_whenStringReaderWithFoo_thenReturnAsStringIsFoo2()
      throws JsonIOException, JsonSyntaxException {
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
    JsonPrimitive actualAsJsonPrimitive = actualParseResult.getAsJsonPrimitive();
    assertSame(actualParseResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonParser#parse(JsonReader)} with {@code JsonReader}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code path}.
   *   <li>Then return AsString is {@code th}.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parse(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parse(JsonReader)"})
  public void testParseWithJsonReader_whenStringReaderWithPath_thenReturnAsStringIsTh()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    JsonParser jsonParser = new JsonParser();

    StringReader in = new StringReader(" path ");
    in.read("\u0001\u0006\u0001\u0006".toCharArray(), 1, 3);

    JsonReader json = new JsonReader(in);
    json.setStrictness(Strictness.LENIENT);

    // Act
    JsonElement actualParseResult = jsonParser.parse(json);

    // Assert
    assertTrue(actualParseResult instanceof JsonPrimitive);
    Number asNumber = actualParseResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("th", actualParseResult.getAsString());
    assertEquals("th", asNumber.toString());
    assertEquals('t', actualParseResult.getAsCharacter());
    JsonPrimitive actualAsJsonPrimitive = actualParseResult.getAsJsonPrimitive();
    assertSame(actualParseResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonParser#parse(JsonReader)} with {@code JsonReader}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code See}.
   *   <li>Then return AsString is {@code See}.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parse(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parse(JsonReader)"})
  public void testParseWithJsonReader_whenStringReaderWithSee_thenReturnAsStringIsSee()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    JsonParser jsonParser = new JsonParser();

    JsonReader json = new JsonReader(new StringReader("\nSee "));
    json.setStrictness(Strictness.LENIENT);

    // Act
    JsonElement actualParseResult = jsonParser.parse(json);

    // Assert
    assertTrue(actualParseResult instanceof JsonPrimitive);
    Number asNumber = actualParseResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("See", actualParseResult.getAsString());
    assertEquals("See", asNumber.toString());
    assertEquals('S', actualParseResult.getAsCharacter());
    JsonPrimitive actualAsJsonPrimitive = actualParseResult.getAsJsonPrimitive();
    assertSame(actualParseResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonParser#parse(Reader)} with {@code Reader}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then return AsString is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parse(Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parse(Reader)"})
  public void testParseWithReader_whenStringReaderWith42_thenReturnAsStringIs42()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    JsonParser jsonParser = new JsonParser();

    // Act
    JsonElement actualParseResult = jsonParser.parse(new StringReader("42"));

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
   * Test {@link JsonParser#parse(Reader)} with {@code Reader}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then return {@link JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parse(Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parse(Reader)"})
  public void testParseWithReader_whenStringReaderWithEmptyString_thenReturnInstance()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    JsonParser jsonParser = new JsonParser();

    // Act
    JsonElement actualParseResult = jsonParser.parse(new StringReader(""));

    // Assert
    assertSame(((JsonNull) actualParseResult).INSTANCE, actualParseResult);
  }

  /**
   * Test {@link JsonParser#parse(Reader)} with {@code Reader}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then return AsCharacter is {@code f}.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parse(Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parse(Reader)"})
  public void testParseWithReader_whenStringReaderWithFalseToString_thenReturnAsCharacterIsF()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    JsonParser jsonParser = new JsonParser();

    // Act
    JsonElement actualParseResult = jsonParser.parse(new StringReader(Boolean.FALSE.toString()));

    // Assert
    assertTrue(actualParseResult instanceof JsonPrimitive);
    assertEquals('f', actualParseResult.getAsCharacter());
    assertFalse(((JsonPrimitive) actualParseResult).isNumber());
    assertFalse(((JsonPrimitive) actualParseResult).isString());
    assertTrue(((JsonPrimitive) actualParseResult).isBoolean());
    assertEquals(Boolean.FALSE.toString(), actualParseResult.getAsString());
    JsonPrimitive actualAsJsonPrimitive = actualParseResult.getAsJsonPrimitive();
    assertSame(actualParseResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonParser#parse(Reader)} with {@code Reader}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code FALSE}.
   *   <li>Then return AsCharacter is {@code f}.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parse(Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parse(Reader)"})
  public void testParseWithReader_whenStringReaderWithFalse_thenReturnAsCharacterIsF()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    JsonParser jsonParser = new JsonParser();

    // Act
    JsonElement actualParseResult = jsonParser.parse(new StringReader("FALSE"));

    // Assert
    assertTrue(actualParseResult instanceof JsonPrimitive);
    assertEquals('f', actualParseResult.getAsCharacter());
    assertFalse(((JsonPrimitive) actualParseResult).isNumber());
    assertFalse(((JsonPrimitive) actualParseResult).isString());
    assertTrue(((JsonPrimitive) actualParseResult).isBoolean());
    assertEquals(Boolean.FALSE.toString(), actualParseResult.getAsString());
    JsonPrimitive actualAsJsonPrimitive = actualParseResult.getAsJsonPrimitive();
    assertSame(actualParseResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonParser#parse(Reader)} with {@code Reader}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then AsNumber return {@link LazilyParsedNumber}.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parse(Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parse(Reader)"})
  public void testParseWithReader_whenStringReaderWithFoo_thenAsNumberReturnLazilyParsedNumber()
      throws JsonIOException, JsonSyntaxException {
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
    assertTrue(((JsonPrimitive) actualParseResult).isString());
    JsonPrimitive actualAsJsonPrimitive = actualParseResult.getAsJsonPrimitive();
    assertSame(actualParseResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonParser#parse(Reader)} with {@code Reader}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code in == null}.
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parse(Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parse(Reader)"})
  public void testParseWithReader_whenStringReaderWithInNull_thenThrowJsonSyntaxException()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    JsonParser jsonParser = new JsonParser();

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> jsonParser.parse(new StringReader("in == null")));
  }

  /**
   * Test {@link JsonParser#parse(String)} with {@code String}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return AsString is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parse(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parse(String)"})
  public void testParseWithString_when42_thenReturnAsStringIs42() throws JsonSyntaxException {
    // Arrange and Act
    JsonElement actualParseResult = new JsonParser().parse("42");

    // Assert
    assertTrue(actualParseResult instanceof JsonPrimitive);
    assertEquals("42", actualParseResult.getAsString());
    assertEquals('4', actualParseResult.getAsCharacter());
    assertEquals(42, actualParseResult.getAsInt());
    assertEquals(42.0d, actualParseResult.getAsDouble(), 0.0);
    assertEquals(42.0f, actualParseResult.getAsFloat(), 0.0f);
    assertEquals(42L, actualParseResult.getAsLong());
    assertEquals((short) 42, actualParseResult.getAsShort());
    assertFalse(((JsonPrimitive) actualParseResult).isString());
    assertTrue(((JsonPrimitive) actualParseResult).isNumber());
    assertEquals(new BigDecimal("42"), actualParseResult.getAsBigDecimal());
    assertEquals('*', actualParseResult.getAsByte());
    JsonPrimitive actualAsJsonPrimitive = actualParseResult.getAsJsonPrimitive();
    assertSame(actualParseResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonParser#parse(String)} with {@code String}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return {@link JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parse(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parse(String)"})
  public void testParseWithString_whenEmptyString_thenReturnInstance() throws JsonSyntaxException {
    // Arrange and Act
    JsonElement actualParseResult = new JsonParser().parse("");

    // Assert
    assertSame(((JsonNull) actualParseResult).INSTANCE, actualParseResult);
  }

  /**
   * Test {@link JsonParser#parse(String)} with {@code String}.
   *
   * <ul>
   *   <li>When {@code in == null}.
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parse(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parse(String)"})
  public void testParseWithString_whenInNull_thenThrowJsonSyntaxException()
      throws JsonSyntaxException {
    // Arrange, Act and Assert
    assertThrows(JsonSyntaxException.class, () -> new JsonParser().parse("in == null"));
  }

  /**
   * Test {@link JsonParser#parse(String)} with {@code String}.
   *
   * <ul>
   *   <li>When {@code Json}.
   *   <li>Then AsNumber return {@link LazilyParsedNumber}.
   * </ul>
   *
   * <p>Method under test: {@link JsonParser#parse(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonParser.parse(String)"})
  public void testParseWithString_whenJson_thenAsNumberReturnLazilyParsedNumber()
      throws JsonSyntaxException {
    // Arrange and Act
    JsonElement actualParseResult = new JsonParser().parse("Json");

    // Assert
    assertTrue(actualParseResult instanceof JsonPrimitive);
    Number asNumber = actualParseResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("Json", actualParseResult.getAsString());
    assertEquals("Json", asNumber.toString());
    assertEquals('J', actualParseResult.getAsCharacter());
    assertFalse(((JsonPrimitive) actualParseResult).isNumber());
    assertTrue(((JsonPrimitive) actualParseResult).isString());
    JsonPrimitive actualAsJsonPrimitive = actualParseResult.getAsJsonPrimitive();
    assertSame(actualParseResult, actualAsJsonPrimitive);
  }
}
