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
import java.io.CharArrayReader;
import java.io.FileDescriptor;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JsonStreamParserDiffblueTest {
  /**
   * Test {@link JsonStreamParser#JsonStreamParser(String)}.
   *
   * <p>Method under test: {@link JsonStreamParser#JsonStreamParser(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonStreamParser.<init>(String)"})
  public void testNewJsonStreamParser() throws JsonParseException {
    // Arrange and Act
    JsonStreamParser actualJsonStreamParser = new JsonStreamParser("Json");

    // Assert
    JsonElement nextResult = actualJsonStreamParser.next();
    assertTrue(nextResult instanceof JsonPrimitive);
    assertTrue(nextResult.getAsNumber() instanceof LazilyParsedNumber);
    assertEquals("Json", nextResult.getAsString());
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

  /**
   * Test {@link JsonStreamParser#JsonStreamParser(Reader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then next return {@link JsonPrimitive}.
   * </ul>
   *
   * <p>Method under test: {@link JsonStreamParser#JsonStreamParser(Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonStreamParser.<init>(Reader)"})
  public void testNewJsonStreamParser_whenStringReaderWithFoo_thenNextReturnJsonPrimitive()
      throws JsonParseException {
    // Arrange and Act
    JsonStreamParser actualJsonStreamParser = new JsonStreamParser(new StringReader("foo"));

    // Assert
    JsonElement nextResult = actualJsonStreamParser.next();
    assertTrue(nextResult instanceof JsonPrimitive);
    assertTrue(nextResult.getAsNumber() instanceof LazilyParsedNumber);
    assertEquals("foo", nextResult.getAsString());
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

  /**
   * Test {@link JsonStreamParser#next()}.
   *
   * <ul>
   *   <li>Given {@link FileReader#FileReader(FileDescriptor)} with {@link
   *       FileDescriptor#FileDescriptor()}.
   *   <li>Then throw {@link JsonIOException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonStreamParser#next()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonStreamParser.next()"})
  public void testNext_givenFileReaderWithFileDescriptor_thenThrowJsonIOException()
      throws JsonParseException {
    // Arrange, Act and Assert
    assertThrows(
        JsonIOException.class,
        () -> new JsonStreamParser(new FileReader(new FileDescriptor())).next());
  }

  /**
   * Test {@link JsonStreamParser#next()}.
   *
   * <ul>
   *   <li>Given {@link JsonStreamParser#JsonStreamParser(String)} with json is {@code 42}.
   *   <li>Then return AsString is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link JsonStreamParser#next()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonStreamParser.next()"})
  public void testNext_givenJsonStreamParserWithJsonIs42_thenReturnAsStringIs42()
      throws JsonParseException {
    // Arrange and Act
    JsonElement actualNextResult = new JsonStreamParser("42").next();

    // Assert
    assertTrue(actualNextResult instanceof JsonPrimitive);
    assertEquals("42", actualNextResult.getAsString());
    assertEquals('4', actualNextResult.getAsCharacter());
    assertEquals(42, actualNextResult.getAsInt());
    assertEquals(42.0d, actualNextResult.getAsDouble(), 0.0);
    assertEquals(42.0f, actualNextResult.getAsFloat(), 0.0f);
    assertEquals(42L, actualNextResult.getAsLong());
    assertEquals((short) 42, actualNextResult.getAsShort());
    assertTrue(((JsonPrimitive) actualNextResult).isNumber());
    BigDecimal expectedAsBigDecimal = new BigDecimal("42");
    assertEquals(expectedAsBigDecimal, actualNextResult.getAsBigDecimal());
    assertEquals('*', actualNextResult.getAsByte());
    assertSame(actualNextResult, actualNextResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonStreamParser#next()}.
   *
   * <ul>
   *   <li>Given {@link JsonStreamParser#JsonStreamParser(String)} with json is empty string.
   *   <li>Then throw {@link JsonIOException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonStreamParser#next()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonStreamParser.next()"})
  public void testNext_givenJsonStreamParserWithJsonIsEmptyString_thenThrowJsonIOException()
      throws JsonParseException {
    // Arrange, Act and Assert
    assertThrows(JsonIOException.class, () -> new JsonStreamParser("").next());
  }

  /**
   * Test {@link JsonStreamParser#next()}.
   *
   * <ul>
   *   <li>Given {@link JsonStreamParser#JsonStreamParser(String)} with {@code Json}.
   *   <li>Then return AsString is {@code Json}.
   * </ul>
   *
   * <p>Method under test: {@link JsonStreamParser#next()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonStreamParser.next()"})
  public void testNext_givenJsonStreamParserWithJson_thenReturnAsStringIsJson()
      throws JsonParseException {
    // Arrange and Act
    JsonElement actualNextResult = new JsonStreamParser("Json").next();

    // Assert
    assertTrue(actualNextResult instanceof JsonPrimitive);
    Number asNumber = actualNextResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("Json", actualNextResult.getAsString());
    assertEquals("Json", asNumber.toString());
    assertEquals('J', actualNextResult.getAsCharacter());
    assertSame(actualNextResult, actualNextResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonStreamParser#next()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then return not String.
   * </ul>
   *
   * <p>Method under test: {@link JsonStreamParser#next()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonStreamParser.next()"})
  public void testNext_givenStringReaderWithFalseToString_thenReturnNotString()
      throws JsonParseException {
    // Arrange and Act
    JsonElement actualNextResult =
        new JsonStreamParser(new StringReader(Boolean.FALSE.toString())).next();

    // Assert
    assertTrue(actualNextResult instanceof JsonPrimitive);
    assertFalse(((JsonPrimitive) actualNextResult).isString());
    assertTrue(((JsonPrimitive) actualNextResult).isBoolean());
    String expectedAsString = Boolean.FALSE.toString();
    assertEquals(expectedAsString, actualNextResult.getAsString());
    assertSame(actualNextResult, actualNextResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonStreamParser#next()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code FALSE}.
   *   <li>Then return not String.
   * </ul>
   *
   * <p>Method under test: {@link JsonStreamParser#next()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonStreamParser.next()"})
  public void testNext_givenStringReaderWithFalse_thenReturnNotString() throws JsonParseException {
    // Arrange and Act
    JsonElement actualNextResult = new JsonStreamParser(new StringReader("FALSE")).next();

    // Assert
    assertTrue(actualNextResult instanceof JsonPrimitive);
    assertFalse(((JsonPrimitive) actualNextResult).isString());
    assertTrue(((JsonPrimitive) actualNextResult).isBoolean());
    String expectedAsString = Boolean.FALSE.toString();
    assertEquals(expectedAsString, actualNextResult.getAsString());
    assertSame(actualNextResult, actualNextResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonStreamParser#next()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code falsefalse}.
   *   <li>Then return AsString is {@code falsefalse}.
   * </ul>
   *
   * <p>Method under test: {@link JsonStreamParser#next()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonStreamParser.next()"})
  public void testNext_givenStringReaderWithFalsefalse_thenReturnAsStringIsFalsefalse()
      throws JsonParseException {
    // Arrange and Act
    JsonElement actualNextResult = new JsonStreamParser(new StringReader("falsefalse")).next();

    // Assert
    assertTrue(actualNextResult instanceof JsonPrimitive);
    Number asNumber = actualNextResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("falsefalse", actualNextResult.getAsString());
    assertEquals("falsefalse", asNumber.toString());
    assertTrue(((JsonPrimitive) actualNextResult).isString());
    assertSame(actualNextResult, actualNextResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonStreamParser#next()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then return AsString is {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link JsonStreamParser#next()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonStreamParser.next()"})
  public void testNext_givenStringReaderWithFoo_thenReturnAsStringIsFoo()
      throws JsonParseException {
    // Arrange and Act
    JsonElement actualNextResult = new JsonStreamParser(new StringReader("foo")).next();

    // Assert
    assertTrue(actualNextResult instanceof JsonPrimitive);
    Number asNumber = actualNextResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("foo", actualNextResult.getAsString());
    assertEquals("foo", asNumber.toString());
    assertTrue(((JsonPrimitive) actualNextResult).isString());
    assertSame(actualNextResult, actualNextResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonStreamParser#hasNext()}.
   *
   * <p>Method under test: {@link JsonStreamParser#hasNext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonStreamParser.hasNext()"})
  public void testHasNext() {
    // Arrange, Act and Assert
    assertTrue(
        new JsonStreamParser(new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray()))
            .hasNext());
  }

  /**
   * Test {@link JsonStreamParser#hasNext()}.
   *
   * <p>Method under test: {@link JsonStreamParser#hasNext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonStreamParser.hasNext()"})
  public void testHasNext2() {
    // Arrange, Act and Assert
    assertTrue(
        new JsonStreamParser(new CharArrayReader("\n\u0006\u0001\u0006".toCharArray())).hasNext());
  }

  /**
   * Test {@link JsonStreamParser#hasNext()}.
   *
   * <ul>
   *   <li>Given {@link CharArrayReader#CharArrayReader(char[])} with {@code ﻿} toCharArray.
   * </ul>
   *
   * <p>Method under test: {@link JsonStreamParser#hasNext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonStreamParser.hasNext()"})
  public void testHasNext_givenCharArrayReaderWithZeroWidthNoBreakSpaceToCharArray() {
    // Arrange, Act and Assert
    assertTrue(
        new JsonStreamParser(new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray())).hasNext());
  }

  /**
   * Test {@link JsonStreamParser#hasNext()}.
   *
   * <ul>
   *   <li>Given {@link FileReader#FileReader(FileDescriptor)} with {@link
   *       FileDescriptor#FileDescriptor()}.
   *   <li>Then throw {@link JsonIOException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonStreamParser#hasNext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonStreamParser.hasNext()"})
  public void testHasNext_givenFileReaderWithFileDescriptor_thenThrowJsonIOException() {
    // Arrange, Act and Assert
    assertThrows(
        JsonIOException.class,
        () -> new JsonStreamParser(new FileReader(new FileDescriptor())).hasNext());
  }

  /**
   * Test {@link JsonStreamParser#hasNext()}.
   *
   * <ul>
   *   <li>Given {@link JsonStreamParser#JsonStreamParser(String)} with json is {@code 42}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonStreamParser#hasNext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonStreamParser.hasNext()"})
  public void testHasNext_givenJsonStreamParserWithJsonIs42_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new JsonStreamParser("42").hasNext());
  }

  /**
   * Test {@link JsonStreamParser#hasNext()}.
   *
   * <ul>
   *   <li>Given {@link JsonStreamParser#JsonStreamParser(String)} with json is empty string.
   *   <li>Then throw {@link JsonIOException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonStreamParser#hasNext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonStreamParser.hasNext()"})
  public void testHasNext_givenJsonStreamParserWithJsonIsEmptyString_thenThrowJsonIOException() {
    // Arrange, Act and Assert
    assertThrows(JsonIOException.class, () -> new JsonStreamParser("").hasNext());
  }

  /**
   * Test {@link JsonStreamParser#hasNext()}.
   *
   * <ul>
   *   <li>Given {@link JsonStreamParser#JsonStreamParser(String)} with {@code Json}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonStreamParser#hasNext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonStreamParser.hasNext()"})
  public void testHasNext_givenJsonStreamParserWithJson_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new JsonStreamParser("Json").hasNext());
  }

  /**
   * Test {@link JsonStreamParser#hasNext()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonStreamParser#hasNext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonStreamParser.hasNext()"})
  public void testHasNext_givenStringReaderWithFalseToString_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new JsonStreamParser(new StringReader(Boolean.FALSE.toString())).hasNext());
  }

  /**
   * Test {@link JsonStreamParser#hasNext()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code FALSE}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonStreamParser#hasNext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonStreamParser.hasNext()"})
  public void testHasNext_givenStringReaderWithFalse_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new JsonStreamParser(new StringReader("FALSE")).hasNext());
  }

  /**
   * Test {@link JsonStreamParser#hasNext()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonStreamParser#hasNext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonStreamParser.hasNext()"})
  public void testHasNext_givenStringReaderWithFoo_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new JsonStreamParser(new StringReader("foo")).hasNext());
  }

  /**
   * Test {@link JsonStreamParser#remove()}.
   *
   * <p>Method under test: {@link JsonStreamParser#remove()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonStreamParser.remove()"})
  public void testRemove() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> new JsonStreamParser("Json").remove());
  }
}
