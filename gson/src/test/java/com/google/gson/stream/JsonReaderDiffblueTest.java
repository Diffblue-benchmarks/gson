package com.google.gson.stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.JsonNull;
import com.google.gson.Strictness;
import com.google.gson.internal.bind.JsonTreeReader;
import java.io.CharArrayReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JsonReaderDiffblueTest {
  /**
   * Test {@link JsonReader#JsonReader(Reader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then return Path is {@code $}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#JsonReader(Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.<init>(Reader)"})
  public void testNewJsonReader_whenStringReaderWithFoo_thenReturnPathIsDollarSign() {
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

  /**
   * Test {@link JsonReader#setLenient(boolean)}.
   *
   * <ul>
   *   <li>Then {@link JsonReader#JsonReader(Reader)} with in is {@link
   *       StringReader#StringReader(String)} Strictness is {@code LEGACY_STRICT}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#setLenient(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.setLenient(boolean)"})
  public void testSetLenient_thenJsonReaderWithInIsStringReaderStrictnessIsLegacyStrict() {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));

    // Act
    jsonReader.setLenient(false);

    // Assert that nothing has changed
    assertEquals(Strictness.LEGACY_STRICT, jsonReader.getStrictness());
    assertFalse(jsonReader.isLenient());
  }

  /**
   * Test {@link JsonReader#setLenient(boolean)}.
   *
   * <ul>
   *   <li>When {@code true}.
   *   <li>Then {@link JsonReader#JsonReader(Reader)} with in is {@link
   *       StringReader#StringReader(String)} Strictness is {@code LENIENT}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#setLenient(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.setLenient(boolean)"})
  public void testSetLenient_whenTrue_thenJsonReaderWithInIsStringReaderStrictnessIsLenient() {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));

    // Act
    jsonReader.setLenient(true);

    // Assert
    assertEquals(Strictness.LENIENT, jsonReader.getStrictness());
    assertTrue(jsonReader.isLenient());
  }

  /**
   * Test {@link JsonReader#isLenient()}.
   *
   * <ul>
   *   <li>Given {@link JsonReader#JsonReader(Reader)} with in is {@link
   *       StringReader#StringReader(String)}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#isLenient()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.isLenient()"})
  public void testIsLenient_givenJsonReaderWithInIsStringReader_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new JsonReader(new StringReader("foo")).isLenient());
  }

  /**
   * Test {@link JsonReader#isLenient()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#isLenient()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.isLenient()"})
  public void testIsLenient_thenReturnTrue() {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertTrue(jsonReader.isLenient());
  }

  /**
   * Test {@link JsonReader#setNestingLimit(int)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#setNestingLimit(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.setNestingLimit(int)"})
  public void testSetNestingLimit_whenMinusOne_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new JsonReader(new StringReader("foo")).setNestingLimit(-1));
  }

  /**
   * Test {@link JsonReader#setNestingLimit(int)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then {@link JsonReader#JsonReader(Reader)} with in is {@link
   *       StringReader#StringReader(String)} NestingLimit is one.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#setNestingLimit(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.setNestingLimit(int)"})
  public void testSetNestingLimit_whenOne_thenJsonReaderWithInIsStringReaderNestingLimitIsOne() {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));

    // Act
    jsonReader.setNestingLimit(1);

    // Assert
    assertEquals(1, jsonReader.getNestingLimit());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link JsonReader#setStrictness(Strictness)}
   *   <li>{@link JsonReader#toString()}
   *   <li>{@link JsonReader#getNestingLimit()}
   *   <li>{@link JsonReader#getStrictness()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int JsonReader.getNestingLimit()",
    "Strictness JsonReader.getStrictness()",
    "void JsonReader.setStrictness(Strictness)",
    "String JsonReader.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));

    // Act
    jsonReader.setStrictness(Strictness.LENIENT);
    String actualToStringResult = jsonReader.toString();
    int actualNestingLimit = jsonReader.getNestingLimit();

    // Assert
    assertEquals("JsonReader at line 1 column 1 path $", actualToStringResult);
    assertEquals(255, actualNestingLimit);
    assertEquals(Strictness.LENIENT, jsonReader.getStrictness());
  }

  /**
   * Test {@link JsonReader#beginArray()}.
   *
   * <p>Method under test: {@link JsonReader#beginArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginArray()"})
  public void testBeginArray() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () ->
            new JsonReader(
                    new StringReader(
                        "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed JSON"))
                .beginArray());
  }

  /**
   * Test {@link JsonReader#beginArray()}.
   *
   * <p>Method under test: {@link JsonReader#beginArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginArray()"})
  public void testBeginArray2() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () ->
            new JsonReader(new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray()))
                .beginArray());
  }

  /**
   * Test {@link JsonReader#beginArray()}.
   *
   * <ul>
   *   <li>Given {@link CharArrayReader#CharArrayReader(char[])} with {@code ﻿} toCharArray.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#beginArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginArray()"})
  public void testBeginArray_givenCharArrayReaderWithZeroWidthNoBreakSpaceToCharArray()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () ->
            new JsonReader(new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray())).beginArray());
  }

  /**
   * Test {@link JsonReader#beginArray()}.
   *
   * <ul>
   *   <li>Given {@link JsonReader#JsonReader(Reader)} with in is {@link
   *       StringReader#StringReader(String)} Strictness is {@code STRICT}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#beginArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginArray()"})
  public void testBeginArray_givenJsonReaderWithInIsStringReaderStrictnessIsStrict()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.STRICT);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.beginArray());
  }

  /**
   * Test {@link JsonReader#beginArray()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#beginArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginArray()"})
  public void testBeginArray_givenStringReaderWith42_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonReader(new StringReader("42")).beginArray());
  }

  /**
   * Test {@link JsonReader#beginArray()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code at line}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#beginArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginArray()"})
  public void testBeginArray_givenStringReaderWithAtLine_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new StringReader(" at line ")).beginArray());
  }

  /**
   * Test {@link JsonReader#beginArray()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then throw {@link EOFException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#beginArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginArray()"})
  public void testBeginArray_givenStringReaderWithEmptyString_thenThrowEOFException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(EOFException.class, () -> new JsonReader(new StringReader("")).beginArray());
  }

  /**
   * Test {@link JsonReader#beginArray()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then throw {@link EOFException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#beginArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginArray()"})
  public void testBeginArray_givenStringReaderWithEmptyString_thenThrowEOFException2()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(""));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(EOFException.class, () -> jsonReader.beginArray());
  }

  /**
   * Test {@link JsonReader#beginArray()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code Expected}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#beginArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginArray()"})
  public void testBeginArray_givenStringReaderWithExpected_thenThrowIllegalStateException()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("Expected "));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.beginArray());
  }

  /**
   * Test {@link JsonReader#beginArray()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#beginArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginArray()"})
  public void testBeginArray_givenStringReaderWithFalseToString_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> new JsonReader(new StringReader(Boolean.FALSE.toString())).beginArray());
  }

  /**
   * Test {@link JsonReader#beginArray()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#beginArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginArray()"})
  public void testBeginArray_givenStringReaderWithFalseToString_thenThrowIllegalStateException2()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(Boolean.FALSE.toString()));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.beginArray());
  }

  /**
   * Test {@link JsonReader#beginArray()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code FALSE}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#beginArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginArray()"})
  public void testBeginArray_givenStringReaderWithFalse_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonReader(new StringReader("FALSE")).beginArray());
  }

  /**
   * Test {@link JsonReader#beginArray()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#beginArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginArray()"})
  public void testBeginArray_givenStringReaderWithFoo_thenThrowIllegalStateException()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.beginArray());
  }

  /**
   * Test {@link JsonReader#beginArray()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#beginArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginArray()"})
  public void testBeginArray_givenStringReaderWithFoo_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class, () -> new JsonReader(new StringReader("foo")).beginArray());
  }

  /**
   * Test {@link JsonReader#beginArray()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code See}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#beginArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginArray()"})
  public void testBeginArray_givenStringReaderWithSee_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new StringReader("\nSee ")).beginArray());
  }

  /**
   * Test {@link JsonReader#endArray()}.
   *
   * <p>Method under test: {@link JsonReader#endArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endArray()"})
  public void testEndArray() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () ->
            new JsonReader(
                    new StringReader(
                        "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed JSON"))
                .endArray());
  }

  /**
   * Test {@link JsonReader#endArray()}.
   *
   * <p>Method under test: {@link JsonReader#endArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endArray()"})
  public void testEndArray2() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () ->
            new JsonReader(new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray()))
                .endArray());
  }

  /**
   * Test {@link JsonReader#endArray()}.
   *
   * <ul>
   *   <li>Given {@link CharArrayReader#CharArrayReader(char[])} with {@code ﻿} toCharArray.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endArray()"})
  public void testEndArray_givenCharArrayReaderWithZeroWidthNoBreakSpaceToCharArray()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray())).endArray());
  }

  /**
   * Test {@link JsonReader#endArray()}.
   *
   * <ul>
   *   <li>Given {@link JsonReader#JsonReader(Reader)} with in is {@link
   *       StringReader#StringReader(String)} Strictness is {@code STRICT}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endArray()"})
  public void testEndArray_givenJsonReaderWithInIsStringReaderStrictnessIsStrict()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.STRICT);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.endArray());
  }

  /**
   * Test {@link JsonReader#endArray()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endArray()"})
  public void testEndArray_givenStringReaderWith42_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonReader(new StringReader("42")).endArray());
  }

  /**
   * Test {@link JsonReader#endArray()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code at line}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endArray()"})
  public void testEndArray_givenStringReaderWithAtLine_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new StringReader(" at line ")).endArray());
  }

  /**
   * Test {@link JsonReader#endArray()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then throw {@link EOFException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endArray()"})
  public void testEndArray_givenStringReaderWithEmptyString_thenThrowEOFException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(EOFException.class, () -> new JsonReader(new StringReader("")).endArray());
  }

  /**
   * Test {@link JsonReader#endArray()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then throw {@link EOFException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endArray()"})
  public void testEndArray_givenStringReaderWithEmptyString_thenThrowEOFException2()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(""));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(EOFException.class, () -> jsonReader.endArray());
  }

  /**
   * Test {@link JsonReader#endArray()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code END_ARRAY}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endArray()"})
  public void testEndArray_givenStringReaderWithEndArray_thenThrowIllegalStateException()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("END_ARRAY"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.endArray());
  }

  /**
   * Test {@link JsonReader#endArray()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code END_ARRAY}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endArray()"})
  public void testEndArray_givenStringReaderWithEndArray_thenThrowIllegalStateException2()
      throws IOException {
    // Arrange
    StringReader in = new StringReader("END_ARRAY");
    in.read("\u0001\u0006\u0001\u0006".toCharArray(), 1, 1);

    JsonReader jsonReader = new JsonReader(in);
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.endArray());
  }

  /**
   * Test {@link JsonReader#endArray()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endArray()"})
  public void testEndArray_givenStringReaderWithFalseToString_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> new JsonReader(new StringReader(Boolean.FALSE.toString())).endArray());
  }

  /**
   * Test {@link JsonReader#endArray()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endArray()"})
  public void testEndArray_givenStringReaderWithFalseToString_thenThrowIllegalStateException2()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(Boolean.FALSE.toString()));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.endArray());
  }

  /**
   * Test {@link JsonReader#endArray()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code FALSE}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endArray()"})
  public void testEndArray_givenStringReaderWithFalse_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonReader(new StringReader("FALSE")).endArray());
  }

  /**
   * Test {@link JsonReader#endArray()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endArray()"})
  public void testEndArray_givenStringReaderWithFoo_thenThrowIllegalStateException()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.endArray());
  }

  /**
   * Test {@link JsonReader#endArray()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endArray()"})
  public void testEndArray_givenStringReaderWithFoo_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class, () -> new JsonReader(new StringReader("foo")).endArray());
  }

  /**
   * Test {@link JsonReader#endArray()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code See}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endArray()"})
  public void testEndArray_givenStringReaderWithSee_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class, () -> new JsonReader(new StringReader("\nSee ")).endArray());
  }

  /**
   * Test {@link JsonReader#endArray()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code unexpected-json-structure}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endArray()"})
  public void testEndArray_givenStringReaderWithUnexpectedJsonStructure() throws IOException {
    // Arrange
    StringReader in = new StringReader("unexpected-json-structure");
    in.read("\u0001\u0006\u0001\u0006".toCharArray(), 1, 1);

    JsonReader jsonReader = new JsonReader(in);
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.endArray());
  }

  /**
   * Test {@link JsonReader#beginObject()}.
   *
   * <p>Method under test: {@link JsonReader#beginObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginObject()"})
  public void testBeginObject() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () ->
            new JsonReader(
                    new StringReader(
                        "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed JSON"))
                .beginObject());
  }

  /**
   * Test {@link JsonReader#beginObject()}.
   *
   * <p>Method under test: {@link JsonReader#beginObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginObject()"})
  public void testBeginObject2() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () ->
            new JsonReader(new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray()))
                .beginObject());
  }

  /**
   * Test {@link JsonReader#beginObject()}.
   *
   * <ul>
   *   <li>Given {@link CharArrayReader#CharArrayReader(char[])} with {@code ﻿} toCharArray.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#beginObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginObject()"})
  public void testBeginObject_givenCharArrayReaderWithZeroWidthNoBreakSpaceToCharArray()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () ->
            new JsonReader(new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray())).beginObject());
  }

  /**
   * Test {@link JsonReader#beginObject()}.
   *
   * <ul>
   *   <li>Given {@link JsonReader#JsonReader(Reader)} with in is {@link
   *       StringReader#StringReader(String)} Strictness is {@code STRICT}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#beginObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginObject()"})
  public void testBeginObject_givenJsonReaderWithInIsStringReaderStrictnessIsStrict()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.STRICT);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.beginObject());
  }

  /**
   * Test {@link JsonReader#beginObject()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#beginObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginObject()"})
  public void testBeginObject_givenStringReaderWith42_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonReader(new StringReader("42")).beginObject());
  }

  /**
   * Test {@link JsonReader#beginObject()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code at line}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#beginObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginObject()"})
  public void testBeginObject_givenStringReaderWithAtLine_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new StringReader(" at line ")).beginObject());
  }

  /**
   * Test {@link JsonReader#beginObject()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then throw {@link EOFException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#beginObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginObject()"})
  public void testBeginObject_givenStringReaderWithEmptyString_thenThrowEOFException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(EOFException.class, () -> new JsonReader(new StringReader("")).beginObject());
  }

  /**
   * Test {@link JsonReader#beginObject()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then throw {@link EOFException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#beginObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginObject()"})
  public void testBeginObject_givenStringReaderWithEmptyString_thenThrowEOFException2()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(""));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(EOFException.class, () -> jsonReader.beginObject());
  }

  /**
   * Test {@link JsonReader#beginObject()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code Expected}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#beginObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginObject()"})
  public void testBeginObject_givenStringReaderWithExpected_thenThrowIllegalStateException()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("Expected "));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.beginObject());
  }

  /**
   * Test {@link JsonReader#beginObject()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#beginObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginObject()"})
  public void testBeginObject_givenStringReaderWithFalseToString() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> new JsonReader(new StringReader(Boolean.FALSE.toString())).beginObject());
  }

  /**
   * Test {@link JsonReader#beginObject()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#beginObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginObject()"})
  public void testBeginObject_givenStringReaderWithFalseToString2() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(Boolean.FALSE.toString()));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.beginObject());
  }

  /**
   * Test {@link JsonReader#beginObject()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code FALSE}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#beginObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginObject()"})
  public void testBeginObject_givenStringReaderWithFalse_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonReader(new StringReader("FALSE")).beginObject());
  }

  /**
   * Test {@link JsonReader#beginObject()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#beginObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginObject()"})
  public void testBeginObject_givenStringReaderWithFoo_thenThrowIllegalStateException()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.beginObject());
  }

  /**
   * Test {@link JsonReader#beginObject()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#beginObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginObject()"})
  public void testBeginObject_givenStringReaderWithFoo_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class, () -> new JsonReader(new StringReader("foo")).beginObject());
  }

  /**
   * Test {@link JsonReader#beginObject()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code See}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#beginObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginObject()"})
  public void testBeginObject_givenStringReaderWithSee_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new StringReader("\nSee ")).beginObject());
  }

  /**
   * Test {@link JsonReader#endObject()}.
   *
   * <p>Method under test: {@link JsonReader#endObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endObject()"})
  public void testEndObject() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () ->
            new JsonReader(
                    new StringReader(
                        "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed JSON"))
                .endObject());
  }

  /**
   * Test {@link JsonReader#endObject()}.
   *
   * <p>Method under test: {@link JsonReader#endObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endObject()"})
  public void testEndObject2() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () ->
            new JsonReader(new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray()))
                .endObject());
  }

  /**
   * Test {@link JsonReader#endObject()}.
   *
   * <ul>
   *   <li>Given {@link CharArrayReader#CharArrayReader(char[])} with {@code ﻿} toCharArray.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endObject()"})
  public void testEndObject_givenCharArrayReaderWithZeroWidthNoBreakSpaceToCharArray()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray())).endObject());
  }

  /**
   * Test {@link JsonReader#endObject()}.
   *
   * <ul>
   *   <li>Given {@link JsonReader#JsonReader(Reader)} with in is {@link
   *       StringReader#StringReader(String)} Strictness is {@code STRICT}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endObject()"})
  public void testEndObject_givenJsonReaderWithInIsStringReaderStrictnessIsStrict()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.STRICT);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.endObject());
  }

  /**
   * Test {@link JsonReader#endObject()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endObject()"})
  public void testEndObject_givenStringReaderWith42_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonReader(new StringReader("42")).endObject());
  }

  /**
   * Test {@link JsonReader#endObject()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code at line}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endObject()"})
  public void testEndObject_givenStringReaderWithAtLine_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new StringReader(" at line ")).endObject());
  }

  /**
   * Test {@link JsonReader#endObject()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then throw {@link EOFException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endObject()"})
  public void testEndObject_givenStringReaderWithEmptyString_thenThrowEOFException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(EOFException.class, () -> new JsonReader(new StringReader("")).endObject());
  }

  /**
   * Test {@link JsonReader#endObject()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then throw {@link EOFException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endObject()"})
  public void testEndObject_givenStringReaderWithEmptyString_thenThrowEOFException2()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(""));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(EOFException.class, () -> jsonReader.endObject());
  }

  /**
   * Test {@link JsonReader#endObject()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code END_OBJECT}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endObject()"})
  public void testEndObject_givenStringReaderWithEndObject_thenThrowIllegalStateException()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("END_OBJECT"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.endObject());
  }

  /**
   * Test {@link JsonReader#endObject()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code END_OBJECT}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endObject()"})
  public void testEndObject_givenStringReaderWithEndObject_thenThrowIllegalStateException2()
      throws IOException {
    // Arrange
    StringReader in = new StringReader("END_OBJECT");
    in.read("\u0001\u0006\u0001\u0006".toCharArray(), 1, 1);

    JsonReader jsonReader = new JsonReader(in);
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.endObject());
  }

  /**
   * Test {@link JsonReader#endObject()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endObject()"})
  public void testEndObject_givenStringReaderWithFalseToString_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> new JsonReader(new StringReader(Boolean.FALSE.toString())).endObject());
  }

  /**
   * Test {@link JsonReader#endObject()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endObject()"})
  public void testEndObject_givenStringReaderWithFalseToString_thenThrowIllegalStateException2()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(Boolean.FALSE.toString()));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.endObject());
  }

  /**
   * Test {@link JsonReader#endObject()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code FALSE}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endObject()"})
  public void testEndObject_givenStringReaderWithFalse_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonReader(new StringReader("FALSE")).endObject());
  }

  /**
   * Test {@link JsonReader#endObject()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endObject()"})
  public void testEndObject_givenStringReaderWithFoo_thenThrowIllegalStateException()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.endObject());
  }

  /**
   * Test {@link JsonReader#endObject()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endObject()"})
  public void testEndObject_givenStringReaderWithFoo_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class, () -> new JsonReader(new StringReader("foo")).endObject());
  }

  /**
   * Test {@link JsonReader#endObject()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code See}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endObject()"})
  public void testEndObject_givenStringReaderWithSee_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class, () -> new JsonReader(new StringReader("\nSee ")).endObject());
  }

  /**
   * Test {@link JsonReader#endObject()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code unexpected-json-structure}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endObject()"})
  public void testEndObject_givenStringReaderWithUnexpectedJsonStructure() throws IOException {
    // Arrange
    StringReader in = new StringReader("unexpected-json-structure");
    in.read("\u0001\u0006\u0001\u0006".toCharArray(), 1, 1);

    JsonReader jsonReader = new JsonReader(in);
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.endObject());
  }

  /**
   * Test {@link JsonReader#hasNext()}.
   *
   * <p>Method under test: {@link JsonReader#hasNext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.hasNext()"})
  public void testHasNext() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () ->
            new JsonReader(
                    new StringReader(
                        "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed JSON"))
                .hasNext());
  }

  /**
   * Test {@link JsonReader#hasNext()}.
   *
   * <p>Method under test: {@link JsonReader#hasNext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.hasNext()"})
  public void testHasNext2() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () ->
            new JsonReader(new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray()))
                .hasNext());
  }

  /**
   * Test {@link JsonReader#hasNext()}.
   *
   * <ul>
   *   <li>Given {@link CharArrayReader#CharArrayReader(char[])} with {@code ﻿} toCharArray.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#hasNext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.hasNext()"})
  public void testHasNext_givenCharArrayReaderWithZeroWidthNoBreakSpaceToCharArray()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray())).hasNext());
  }

  /**
   * Test {@link JsonReader#hasNext()}.
   *
   * <ul>
   *   <li>Given {@link JsonReader#JsonReader(Reader)} with in is {@link
   *       StringReader#StringReader(String)} Strictness is {@code STRICT}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#hasNext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.hasNext()"})
  public void testHasNext_givenJsonReaderWithInIsStringReaderStrictnessIsStrict()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.STRICT);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.hasNext());
  }

  /**
   * Test {@link JsonReader#hasNext()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code at line}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#hasNext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.hasNext()"})
  public void testHasNext_givenStringReaderWithAtLine_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new StringReader(" at line ")).hasNext());
  }

  /**
   * Test {@link JsonReader#hasNext()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then throw {@link EOFException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#hasNext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.hasNext()"})
  public void testHasNext_givenStringReaderWithEmptyString_thenThrowEOFException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(EOFException.class, () -> new JsonReader(new StringReader("")).hasNext());
  }

  /**
   * Test {@link JsonReader#hasNext()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then throw {@link EOFException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#hasNext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.hasNext()"})
  public void testHasNext_givenStringReaderWithEmptyString_thenThrowEOFException2()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(""));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(EOFException.class, () -> jsonReader.hasNext());
  }

  /**
   * Test {@link JsonReader#hasNext()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#hasNext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.hasNext()"})
  public void testHasNext_givenStringReaderWithFoo_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class, () -> new JsonReader(new StringReader("foo")).hasNext());
  }

  /**
   * Test {@link JsonReader#hasNext()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code See}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#hasNext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.hasNext()"})
  public void testHasNext_givenStringReaderWithSee_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class, () -> new JsonReader(new StringReader("\nSee ")).hasNext());
  }

  /**
   * Test {@link JsonReader#peek()}.
   *
   * <p>Method under test: {@link JsonReader#peek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"com.google.gson.stream.JsonToken JsonReader.peek()"})
  public void testPeek() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () ->
            new JsonReader(
                    new StringReader(
                        "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed JSON"))
                .peek());
  }

  /**
   * Test {@link JsonReader#peek()}.
   *
   * <p>Method under test: {@link JsonReader#peek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"com.google.gson.stream.JsonToken JsonReader.peek()"})
  public void testPeek2() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray())).peek());
  }

  /**
   * Test {@link JsonReader#peek()}.
   *
   * <ul>
   *   <li>Given {@link CharArrayReader#CharArrayReader(char[])} with {@code ﻿} toCharArray.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#peek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"com.google.gson.stream.JsonToken JsonReader.peek()"})
  public void testPeek_givenCharArrayReaderWithZeroWidthNoBreakSpaceToCharArray()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray())).peek());
  }

  /**
   * Test {@link JsonReader#peek()}.
   *
   * <ul>
   *   <li>Given {@link JsonReader#JsonReader(Reader)} with in is {@link
   *       StringReader#StringReader(String)} Strictness is {@code STRICT}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#peek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"com.google.gson.stream.JsonToken JsonReader.peek()"})
  public void testPeek_givenJsonReaderWithInIsStringReaderStrictnessIsStrict() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.STRICT);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.peek());
  }

  /**
   * Test {@link JsonReader#peek()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code at line}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#peek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"com.google.gson.stream.JsonToken JsonReader.peek()"})
  public void testPeek_givenStringReaderWithAtLine_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class, () -> new JsonReader(new StringReader(" at line ")).peek());
  }

  /**
   * Test {@link JsonReader#peek()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then throw {@link EOFException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#peek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"com.google.gson.stream.JsonToken JsonReader.peek()"})
  public void testPeek_givenStringReaderWithEmptyString_thenThrowEOFException() throws IOException {
    // Arrange, Act and Assert
    assertThrows(EOFException.class, () -> new JsonReader(new StringReader("")).peek());
  }

  /**
   * Test {@link JsonReader#peek()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then throw {@link EOFException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#peek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"com.google.gson.stream.JsonToken JsonReader.peek()"})
  public void testPeek_givenStringReaderWithEmptyString_thenThrowEOFException2()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(""));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(EOFException.class, () -> jsonReader.peek());
  }

  /**
   * Test {@link JsonReader#peek()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#peek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"com.google.gson.stream.JsonToken JsonReader.peek()"})
  public void testPeek_givenStringReaderWithFoo_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class, () -> new JsonReader(new StringReader("foo")).peek());
  }

  /**
   * Test {@link JsonReader#peek()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code See}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#peek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"com.google.gson.stream.JsonToken JsonReader.peek()"})
  public void testPeek_givenStringReaderWithSee_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class, () -> new JsonReader(new StringReader("\nSee ")).peek());
  }

  /**
   * Test {@link JsonReader#doPeek()}.
   *
   * <p>Method under test: {@link JsonReader#doPeek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonReader.doPeek()"})
  public void testDoPeek() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () ->
            new JsonReader(
                    new StringReader(
                        "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed JSON"))
                .doPeek());
  }

  /**
   * Test {@link JsonReader#doPeek()}.
   *
   * <p>Method under test: {@link JsonReader#doPeek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonReader.doPeek()"})
  public void testDoPeek2() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () ->
            new JsonReader(new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray())).doPeek());
  }

  /**
   * Test {@link JsonReader#doPeek()}.
   *
   * <p>Method under test: {@link JsonReader#doPeek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonReader.doPeek()"})
  public void testDoPeek3() throws IOException {
    // Arrange
    StringReader in = new StringReader("End of input");
    in.read("\u0001\u0006\u0001\u0006".toCharArray(), 1, 1);

    JsonReader jsonReader = new JsonReader(in);
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertEquals(10, jsonReader.doPeek());
    assertEquals(10, jsonReader.peeked);
  }

  /**
   * Test {@link JsonReader#doPeek()}.
   *
   * <ul>
   *   <li>Given {@link CharArrayReader#CharArrayReader(char[])} with {@code ﻿} toCharArray.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#doPeek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonReader.doPeek()"})
  public void testDoPeek_givenCharArrayReaderWithZeroWidthNoBreakSpaceToCharArray()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray())).doPeek());
  }

  /**
   * Test {@link JsonReader#doPeek()}.
   *
   * <ul>
   *   <li>Given {@link JsonReader#JsonReader(Reader)} with in is {@link
   *       StringReader#StringReader(String)} Strictness is {@code STRICT}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#doPeek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonReader.doPeek()"})
  public void testDoPeek_givenJsonReaderWithInIsStringReaderStrictnessIsStrict()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.STRICT);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.doPeek());
  }

  /**
   * Test {@link JsonReader#doPeek()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then return fifteen.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#doPeek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonReader.doPeek()"})
  public void testDoPeek_givenStringReaderWith42_thenReturnFifteen() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("42"));

    // Act and Assert
    assertEquals(15, jsonReader.doPeek());
    assertEquals(15, jsonReader.peeked);
  }

  /**
   * Test {@link JsonReader#doPeek()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code at line}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#doPeek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonReader.doPeek()"})
  public void testDoPeek_givenStringReaderWithAtLine_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class, () -> new JsonReader(new StringReader(" at line ")).doPeek());
  }

  /**
   * Test {@link JsonReader#doPeek()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then throw {@link EOFException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#doPeek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonReader.doPeek()"})
  public void testDoPeek_givenStringReaderWithEmptyString_thenThrowEOFException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(EOFException.class, () -> new JsonReader(new StringReader("")).doPeek());
  }

  /**
   * Test {@link JsonReader#doPeek()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then throw {@link EOFException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#doPeek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonReader.doPeek()"})
  public void testDoPeek_givenStringReaderWithEmptyString_thenThrowEOFException2()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(""));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(EOFException.class, () -> jsonReader.doPeek());
  }

  /**
   * Test {@link JsonReader#doPeek()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code End of input}.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#doPeek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonReader.doPeek()"})
  public void testDoPeek_givenStringReaderWithEndOfInput_thenReturnTen() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("End of input"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertEquals(10, jsonReader.doPeek());
    assertEquals(10, jsonReader.peeked);
  }

  /**
   * Test {@link JsonReader#doPeek()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then return six.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#doPeek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonReader.doPeek()"})
  public void testDoPeek_givenStringReaderWithFalseToString_thenReturnSix() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(Boolean.FALSE.toString()));

    // Act and Assert
    assertEquals(6, jsonReader.doPeek());
    assertEquals(6, jsonReader.peeked);
  }

  /**
   * Test {@link JsonReader#doPeek()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then return six.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#doPeek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonReader.doPeek()"})
  public void testDoPeek_givenStringReaderWithFalseToString_thenReturnSix2() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(Boolean.FALSE.toString()));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertEquals(6, jsonReader.doPeek());
    assertEquals(6, jsonReader.peeked);
  }

  /**
   * Test {@link JsonReader#doPeek()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code FALSE}.
   *   <li>Then return six.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#doPeek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonReader.doPeek()"})
  public void testDoPeek_givenStringReaderWithFalse_thenReturnSix() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("FALSE"));

    // Act and Assert
    assertEquals(6, jsonReader.doPeek());
    assertEquals(6, jsonReader.peeked);
  }

  /**
   * Test {@link JsonReader#doPeek()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#doPeek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonReader.doPeek()"})
  public void testDoPeek_givenStringReaderWithFoo_thenReturnTen() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertEquals(10, jsonReader.doPeek());
    assertEquals(10, jsonReader.peeked);
  }

  /**
   * Test {@link JsonReader#doPeek()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#doPeek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonReader.doPeek()"})
  public void testDoPeek_givenStringReaderWithFoo_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class, () -> new JsonReader(new StringReader("foo")).doPeek());
  }

  /**
   * Test {@link JsonReader#doPeek()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code See}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#doPeek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonReader.doPeek()"})
  public void testDoPeek_givenStringReaderWithSee_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class, () -> new JsonReader(new StringReader("\nSee ")).doPeek());
  }

  /**
   * Test {@link JsonReader#nextName()}.
   *
   * <p>Method under test: {@link JsonReader#nextName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextName()"})
  public void testNextName() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () ->
            new JsonReader(
                    new StringReader(
                        "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed JSON"))
                .nextName());
  }

  /**
   * Test {@link JsonReader#nextName()}.
   *
   * <p>Method under test: {@link JsonReader#nextName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextName()"})
  public void testNextName2() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () ->
            new JsonReader(new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray()))
                .nextName());
  }

  /**
   * Test {@link JsonReader#nextName()}.
   *
   * <ul>
   *   <li>Given {@link CharArrayReader#CharArrayReader(char[])} with {@code ﻿} toCharArray.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextName()"})
  public void testNextName_givenCharArrayReaderWithZeroWidthNoBreakSpaceToCharArray()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray())).nextName());
  }

  /**
   * Test {@link JsonReader#nextName()}.
   *
   * <ul>
   *   <li>Given {@link JsonReader#JsonReader(Reader)} with in is {@link
   *       StringReader#StringReader(String)} Strictness is {@code STRICT}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextName()"})
  public void testNextName_givenJsonReaderWithInIsStringReaderStrictnessIsStrict()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.STRICT);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.nextName());
  }

  /**
   * Test {@link JsonReader#nextName()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextName()"})
  public void testNextName_givenStringReaderWith42_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonReader(new StringReader("42")).nextName());
  }

  /**
   * Test {@link JsonReader#nextName()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code at line}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextName()"})
  public void testNextName_givenStringReaderWithAtLine_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new StringReader(" at line ")).nextName());
  }

  /**
   * Test {@link JsonReader#nextName()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then throw {@link EOFException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextName()"})
  public void testNextName_givenStringReaderWithEmptyString_thenThrowEOFException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(EOFException.class, () -> new JsonReader(new StringReader("")).nextName());
  }

  /**
   * Test {@link JsonReader#nextName()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then throw {@link EOFException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextName()"})
  public void testNextName_givenStringReaderWithEmptyString_thenThrowEOFException2()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(""));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(EOFException.class, () -> jsonReader.nextName());
  }

  /**
   * Test {@link JsonReader#nextName()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code Expected}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextName()"})
  public void testNextName_givenStringReaderWithExpected_thenThrowIllegalStateException()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("Expected "));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.nextName());
  }

  /**
   * Test {@link JsonReader#nextName()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextName()"})
  public void testNextName_givenStringReaderWithFalseToString_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> new JsonReader(new StringReader(Boolean.FALSE.toString())).nextName());
  }

  /**
   * Test {@link JsonReader#nextName()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextName()"})
  public void testNextName_givenStringReaderWithFalseToString_thenThrowIllegalStateException2()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(Boolean.FALSE.toString()));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.nextName());
  }

  /**
   * Test {@link JsonReader#nextName()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code FALSE}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextName()"})
  public void testNextName_givenStringReaderWithFalse_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonReader(new StringReader("FALSE")).nextName());
  }

  /**
   * Test {@link JsonReader#nextName()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextName()"})
  public void testNextName_givenStringReaderWithFoo_thenThrowIllegalStateException()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.nextName());
  }

  /**
   * Test {@link JsonReader#nextName()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextName()"})
  public void testNextName_givenStringReaderWithFoo_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class, () -> new JsonReader(new StringReader("foo")).nextName());
  }

  /**
   * Test {@link JsonReader#nextName()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code See}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextName()"})
  public void testNextName_givenStringReaderWithSee_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class, () -> new JsonReader(new StringReader("\nSee ")).nextName());
  }

  /**
   * Test {@link JsonReader#nextString()}.
   *
   * <p>Method under test: {@link JsonReader#nextString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextString()"})
  public void testNextString() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () ->
            new JsonReader(
                    new StringReader(
                        "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed JSON"))
                .nextString());
  }

  /**
   * Test {@link JsonReader#nextString()}.
   *
   * <p>Method under test: {@link JsonReader#nextString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextString()"})
  public void testNextString2() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () ->
            new JsonReader(new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray()))
                .nextString());
  }

  /**
   * Test {@link JsonReader#nextString()}.
   *
   * <ul>
   *   <li>Given {@link CharArrayReader#CharArrayReader(char[])} with {@code ﻿} toCharArray.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextString()"})
  public void testNextString_givenCharArrayReaderWithZeroWidthNoBreakSpaceToCharArray()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () ->
            new JsonReader(new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray())).nextString());
  }

  /**
   * Test {@link JsonReader#nextString()}.
   *
   * <ul>
   *   <li>Given {@link JsonReader#JsonReader(Reader)} with in is {@link
   *       StringReader#StringReader(String)} Strictness is {@code STRICT}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextString()"})
  public void testNextString_givenJsonReaderWithInIsStringReaderStrictnessIsStrict()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.STRICT);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.nextString());
  }

  /**
   * Test {@link JsonReader#nextString()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextString()"})
  public void testNextString_givenStringReaderWith42_thenReturn42() throws IOException {
    // Arrange, Act and Assert
    assertEquals("42", new JsonReader(new StringReader("42")).nextString());
  }

  /**
   * Test {@link JsonReader#nextString()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code at line}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextString()"})
  public void testNextString_givenStringReaderWithAtLine_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new StringReader(" at line ")).nextString());
  }

  /**
   * Test {@link JsonReader#nextString()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then throw {@link EOFException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextString()"})
  public void testNextString_givenStringReaderWithEmptyString_thenThrowEOFException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(EOFException.class, () -> new JsonReader(new StringReader("")).nextString());
  }

  /**
   * Test {@link JsonReader#nextString()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then throw {@link EOFException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextString()"})
  public void testNextString_givenStringReaderWithEmptyString_thenThrowEOFException2()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(""));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(EOFException.class, () -> jsonReader.nextString());
  }

  /**
   * Test {@link JsonReader#nextString()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code Expected}.
   *   <li>Then return {@code Expected}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextString()"})
  public void testNextString_givenStringReaderWithExpected_thenReturnExpected() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("Expected "));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertEquals("Expected", jsonReader.nextString());
  }

  /**
   * Test {@link JsonReader#nextString()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextString()"})
  public void testNextString_givenStringReaderWithFalseToString_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> new JsonReader(new StringReader(Boolean.FALSE.toString())).nextString());
  }

  /**
   * Test {@link JsonReader#nextString()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextString()"})
  public void testNextString_givenStringReaderWithFalseToString_thenThrowIllegalStateException2()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(Boolean.FALSE.toString()));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.nextString());
  }

  /**
   * Test {@link JsonReader#nextString()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code FALSE}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextString()"})
  public void testNextString_givenStringReaderWithFalse_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonReader(new StringReader("FALSE")).nextString());
  }

  /**
   * Test {@link JsonReader#nextString()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then return {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextString()"})
  public void testNextString_givenStringReaderWithFoo_thenReturnFoo() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertEquals("foo", jsonReader.nextString());
  }

  /**
   * Test {@link JsonReader#nextString()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextString()"})
  public void testNextString_givenStringReaderWithFoo_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class, () -> new JsonReader(new StringReader("foo")).nextString());
  }

  /**
   * Test {@link JsonReader#nextString()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code See}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextString()"})
  public void testNextString_givenStringReaderWithSee_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new StringReader("\nSee ")).nextString());
  }

  /**
   * Test {@link JsonReader#nextString()}.
   *
   * <ul>
   *   <li>Then return {@code Use}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextString()"})
  public void testNextString_thenReturnUse() throws IOException {
    // Arrange
    JsonReader jsonReader =
        new JsonReader(
            new StringReader(
                "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed JSON"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertEquals("Use", jsonReader.nextString());
  }

  /**
   * Test {@link JsonReader#nextBoolean()}.
   *
   * <p>Method under test: {@link JsonReader#nextBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.nextBoolean()"})
  public void testNextBoolean() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () ->
            new JsonReader(
                    new StringReader(
                        "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed JSON"))
                .nextBoolean());
  }

  /**
   * Test {@link JsonReader#nextBoolean()}.
   *
   * <p>Method under test: {@link JsonReader#nextBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.nextBoolean()"})
  public void testNextBoolean2() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () ->
            new JsonReader(new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray()))
                .nextBoolean());
  }

  /**
   * Test {@link JsonReader#nextBoolean()}.
   *
   * <ul>
   *   <li>Given {@link CharArrayReader#CharArrayReader(char[])} with {@code ﻿} toCharArray.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.nextBoolean()"})
  public void testNextBoolean_givenCharArrayReaderWithZeroWidthNoBreakSpaceToCharArray()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () ->
            new JsonReader(new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray())).nextBoolean());
  }

  /**
   * Test {@link JsonReader#nextBoolean()}.
   *
   * <ul>
   *   <li>Given {@link JsonReader#JsonReader(Reader)} with in is {@link
   *       StringReader#StringReader(String)} Strictness is {@code STRICT}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.nextBoolean()"})
  public void testNextBoolean_givenJsonReaderWithInIsStringReaderStrictnessIsStrict()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.STRICT);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.nextBoolean());
  }

  /**
   * Test {@link JsonReader#nextBoolean()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.nextBoolean()"})
  public void testNextBoolean_givenStringReaderWith42_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonReader(new StringReader("42")).nextBoolean());
  }

  /**
   * Test {@link JsonReader#nextBoolean()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code at line}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.nextBoolean()"})
  public void testNextBoolean_givenStringReaderWithAtLine_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new StringReader(" at line ")).nextBoolean());
  }

  /**
   * Test {@link JsonReader#nextBoolean()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then throw {@link EOFException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.nextBoolean()"})
  public void testNextBoolean_givenStringReaderWithEmptyString_thenThrowEOFException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(EOFException.class, () -> new JsonReader(new StringReader("")).nextBoolean());
  }

  /**
   * Test {@link JsonReader#nextBoolean()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then throw {@link EOFException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.nextBoolean()"})
  public void testNextBoolean_givenStringReaderWithEmptyString_thenThrowEOFException2()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(""));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(EOFException.class, () -> jsonReader.nextBoolean());
  }

  /**
   * Test {@link JsonReader#nextBoolean()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code Expected}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.nextBoolean()"})
  public void testNextBoolean_givenStringReaderWithExpected_thenThrowIllegalStateException()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("Expected "));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.nextBoolean());
  }

  /**
   * Test {@link JsonReader#nextBoolean()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.nextBoolean()"})
  public void testNextBoolean_givenStringReaderWithFalseToString_thenReturnFalse()
      throws IOException {
    // Arrange, Act and Assert
    assertFalse(new JsonReader(new StringReader(Boolean.FALSE.toString())).nextBoolean());
  }

  /**
   * Test {@link JsonReader#nextBoolean()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.nextBoolean()"})
  public void testNextBoolean_givenStringReaderWithFalseToString_thenReturnFalse2()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(Boolean.FALSE.toString()));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertFalse(jsonReader.nextBoolean());
  }

  /**
   * Test {@link JsonReader#nextBoolean()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code FALSE}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.nextBoolean()"})
  public void testNextBoolean_givenStringReaderWithFalse_thenReturnFalse() throws IOException {
    // Arrange, Act and Assert
    assertFalse(new JsonReader(new StringReader("FALSE")).nextBoolean());
  }

  /**
   * Test {@link JsonReader#nextBoolean()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.nextBoolean()"})
  public void testNextBoolean_givenStringReaderWithFoo_thenThrowIllegalStateException()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.nextBoolean());
  }

  /**
   * Test {@link JsonReader#nextBoolean()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.nextBoolean()"})
  public void testNextBoolean_givenStringReaderWithFoo_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class, () -> new JsonReader(new StringReader("foo")).nextBoolean());
  }

  /**
   * Test {@link JsonReader#nextBoolean()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code See}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.nextBoolean()"})
  public void testNextBoolean_givenStringReaderWithSee_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new StringReader("\nSee ")).nextBoolean());
  }

  /**
   * Test {@link JsonReader#nextNull()}.
   *
   * <p>Method under test: {@link JsonReader#nextNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.nextNull()"})
  public void testNextNull() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () ->
            new JsonReader(
                    new StringReader(
                        "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed JSON"))
                .nextNull());
  }

  /**
   * Test {@link JsonReader#nextNull()}.
   *
   * <p>Method under test: {@link JsonReader#nextNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.nextNull()"})
  public void testNextNull2() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () ->
            new JsonReader(new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray()))
                .nextNull());
  }

  /**
   * Test {@link JsonReader#nextNull()}.
   *
   * <ul>
   *   <li>Given {@link CharArrayReader#CharArrayReader(char[])} with {@code ﻿} toCharArray.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.nextNull()"})
  public void testNextNull_givenCharArrayReaderWithZeroWidthNoBreakSpaceToCharArray()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray())).nextNull());
  }

  /**
   * Test {@link JsonReader#nextNull()}.
   *
   * <ul>
   *   <li>Given {@link JsonReader#JsonReader(Reader)} with in is {@link
   *       StringReader#StringReader(String)} Strictness is {@code STRICT}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.nextNull()"})
  public void testNextNull_givenJsonReaderWithInIsStringReaderStrictnessIsStrict()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.STRICT);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.nextNull());
  }

  /**
   * Test {@link JsonReader#nextNull()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.nextNull()"})
  public void testNextNull_givenStringReaderWith42_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonReader(new StringReader("42")).nextNull());
  }

  /**
   * Test {@link JsonReader#nextNull()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code at line}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.nextNull()"})
  public void testNextNull_givenStringReaderWithAtLine_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new StringReader(" at line ")).nextNull());
  }

  /**
   * Test {@link JsonReader#nextNull()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then throw {@link EOFException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.nextNull()"})
  public void testNextNull_givenStringReaderWithEmptyString_thenThrowEOFException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(EOFException.class, () -> new JsonReader(new StringReader("")).nextNull());
  }

  /**
   * Test {@link JsonReader#nextNull()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then throw {@link EOFException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.nextNull()"})
  public void testNextNull_givenStringReaderWithEmptyString_thenThrowEOFException2()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(""));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(EOFException.class, () -> jsonReader.nextNull());
  }

  /**
   * Test {@link JsonReader#nextNull()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code Expected}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.nextNull()"})
  public void testNextNull_givenStringReaderWithExpected_thenThrowIllegalStateException()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("Expected "));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.nextNull());
  }

  /**
   * Test {@link JsonReader#nextNull()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.nextNull()"})
  public void testNextNull_givenStringReaderWithFalseToString_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> new JsonReader(new StringReader(Boolean.FALSE.toString())).nextNull());
  }

  /**
   * Test {@link JsonReader#nextNull()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.nextNull()"})
  public void testNextNull_givenStringReaderWithFalseToString_thenThrowIllegalStateException2()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(Boolean.FALSE.toString()));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.nextNull());
  }

  /**
   * Test {@link JsonReader#nextNull()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code FALSE}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.nextNull()"})
  public void testNextNull_givenStringReaderWithFalse_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonReader(new StringReader("FALSE")).nextNull());
  }

  /**
   * Test {@link JsonReader#nextNull()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.nextNull()"})
  public void testNextNull_givenStringReaderWithFoo_thenThrowIllegalStateException()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.nextNull());
  }

  /**
   * Test {@link JsonReader#nextNull()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.nextNull()"})
  public void testNextNull_givenStringReaderWithFoo_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class, () -> new JsonReader(new StringReader("foo")).nextNull());
  }

  /**
   * Test {@link JsonReader#nextNull()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code See}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.nextNull()"})
  public void testNextNull_givenStringReaderWithSee_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class, () -> new JsonReader(new StringReader("\nSee ")).nextNull());
  }

  /**
   * Test {@link JsonReader#nextDouble()}.
   *
   * <p>Method under test: {@link JsonReader#nextDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JsonReader.nextDouble()"})
  public void testNextDouble() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () ->
            new JsonReader(
                    new StringReader(
                        "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed JSON"))
                .nextDouble());
  }

  /**
   * Test {@link JsonReader#nextDouble()}.
   *
   * <p>Method under test: {@link JsonReader#nextDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JsonReader.nextDouble()"})
  public void testNextDouble2() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () ->
            new JsonReader(new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray()))
                .nextDouble());
  }

  /**
   * Test {@link JsonReader#nextDouble()}.
   *
   * <ul>
   *   <li>Given {@link CharArrayReader#CharArrayReader(char[])} with {@code ﻿} toCharArray.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JsonReader.nextDouble()"})
  public void testNextDouble_givenCharArrayReaderWithZeroWidthNoBreakSpaceToCharArray()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () ->
            new JsonReader(new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray())).nextDouble());
  }

  /**
   * Test {@link JsonReader#nextDouble()}.
   *
   * <ul>
   *   <li>Given {@link JsonReader#JsonReader(Reader)} with in is {@link
   *       StringReader#StringReader(String)} Strictness is {@code STRICT}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JsonReader.nextDouble()"})
  public void testNextDouble_givenJsonReaderWithInIsStringReaderStrictnessIsStrict()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.STRICT);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.nextDouble());
  }

  /**
   * Test {@link JsonReader#nextDouble()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then return forty-two.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JsonReader.nextDouble()"})
  public void testNextDouble_givenStringReaderWith42_thenReturnFortyTwo() throws IOException {
    // Arrange, Act and Assert
    assertEquals(42.0d, new JsonReader(new StringReader("42")).nextDouble(), 0.0);
  }

  /**
   * Test {@link JsonReader#nextDouble()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then return forty-two.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JsonReader.nextDouble()"})
  public void testNextDouble_givenStringReaderWith42_thenReturnFortyTwo2() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("42"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertEquals(42.0d, jsonReader.nextDouble(), 0.0);
  }

  /**
   * Test {@link JsonReader#nextDouble()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code at line}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JsonReader.nextDouble()"})
  public void testNextDouble_givenStringReaderWithAtLine_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new StringReader(" at line ")).nextDouble());
  }

  /**
   * Test {@link JsonReader#nextDouble()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then throw {@link EOFException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JsonReader.nextDouble()"})
  public void testNextDouble_givenStringReaderWithEmptyString_thenThrowEOFException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(EOFException.class, () -> new JsonReader(new StringReader("")).nextDouble());
  }

  /**
   * Test {@link JsonReader#nextDouble()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then throw {@link EOFException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JsonReader.nextDouble()"})
  public void testNextDouble_givenStringReaderWithEmptyString_thenThrowEOFException2()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(""));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(EOFException.class, () -> jsonReader.nextDouble());
  }

  /**
   * Test {@link JsonReader#nextDouble()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JsonReader.nextDouble()"})
  public void testNextDouble_givenStringReaderWithFalseToString_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> new JsonReader(new StringReader(Boolean.FALSE.toString())).nextDouble());
  }

  /**
   * Test {@link JsonReader#nextDouble()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JsonReader.nextDouble()"})
  public void testNextDouble_givenStringReaderWithFalseToString_thenThrowIllegalStateException2()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(Boolean.FALSE.toString()));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.nextDouble());
  }

  /**
   * Test {@link JsonReader#nextDouble()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code FALSE}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JsonReader.nextDouble()"})
  public void testNextDouble_givenStringReaderWithFalse_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonReader(new StringReader("FALSE")).nextDouble());
  }

  /**
   * Test {@link JsonReader#nextDouble()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JsonReader.nextDouble()"})
  public void testNextDouble_givenStringReaderWithFoo_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class, () -> new JsonReader(new StringReader("foo")).nextDouble());
  }

  /**
   * Test {@link JsonReader#nextDouble()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code See}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JsonReader.nextDouble()"})
  public void testNextDouble_givenStringReaderWithSee_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new StringReader("\nSee ")).nextDouble());
  }

  /**
   * Test {@link JsonReader#nextLong()}.
   *
   * <p>Method under test: {@link JsonReader#nextLong()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JsonReader.nextLong()"})
  public void testNextLong() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () ->
            new JsonReader(
                    new StringReader(
                        "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed JSON"))
                .nextLong());
  }

  /**
   * Test {@link JsonReader#nextLong()}.
   *
   * <p>Method under test: {@link JsonReader#nextLong()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JsonReader.nextLong()"})
  public void testNextLong2() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () ->
            new JsonReader(new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray()))
                .nextLong());
  }

  /**
   * Test {@link JsonReader#nextLong()}.
   *
   * <ul>
   *   <li>Given {@link CharArrayReader#CharArrayReader(char[])} with {@code ﻿} toCharArray.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextLong()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JsonReader.nextLong()"})
  public void testNextLong_givenCharArrayReaderWithZeroWidthNoBreakSpaceToCharArray()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray())).nextLong());
  }

  /**
   * Test {@link JsonReader#nextLong()}.
   *
   * <ul>
   *   <li>Given {@link JsonReader#JsonReader(Reader)} with in is {@link
   *       StringReader#StringReader(String)} Strictness is {@code STRICT}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextLong()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JsonReader.nextLong()"})
  public void testNextLong_givenJsonReaderWithInIsStringReaderStrictnessIsStrict()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.STRICT);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.nextLong());
  }

  /**
   * Test {@link JsonReader#nextLong()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then return forty-two.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextLong()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JsonReader.nextLong()"})
  public void testNextLong_givenStringReaderWith42_thenReturnFortyTwo() throws IOException {
    // Arrange, Act and Assert
    assertEquals(42L, new JsonReader(new StringReader("42")).nextLong());
  }

  /**
   * Test {@link JsonReader#nextLong()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then return forty-two.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextLong()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JsonReader.nextLong()"})
  public void testNextLong_givenStringReaderWith42_thenReturnFortyTwo2() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("42"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertEquals(42L, jsonReader.nextLong());
  }

  /**
   * Test {@link JsonReader#nextLong()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code at line}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextLong()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JsonReader.nextLong()"})
  public void testNextLong_givenStringReaderWithAtLine_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new StringReader(" at line ")).nextLong());
  }

  /**
   * Test {@link JsonReader#nextLong()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then throw {@link EOFException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextLong()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JsonReader.nextLong()"})
  public void testNextLong_givenStringReaderWithEmptyString_thenThrowEOFException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(EOFException.class, () -> new JsonReader(new StringReader("")).nextLong());
  }

  /**
   * Test {@link JsonReader#nextLong()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then throw {@link EOFException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextLong()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JsonReader.nextLong()"})
  public void testNextLong_givenStringReaderWithEmptyString_thenThrowEOFException2()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(""));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(EOFException.class, () -> jsonReader.nextLong());
  }

  /**
   * Test {@link JsonReader#nextLong()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextLong()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JsonReader.nextLong()"})
  public void testNextLong_givenStringReaderWithFalseToString_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> new JsonReader(new StringReader(Boolean.FALSE.toString())).nextLong());
  }

  /**
   * Test {@link JsonReader#nextLong()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextLong()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JsonReader.nextLong()"})
  public void testNextLong_givenStringReaderWithFalseToString_thenThrowIllegalStateException2()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(Boolean.FALSE.toString()));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.nextLong());
  }

  /**
   * Test {@link JsonReader#nextLong()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code FALSE}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextLong()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JsonReader.nextLong()"})
  public void testNextLong_givenStringReaderWithFalse_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonReader(new StringReader("FALSE")).nextLong());
  }

  /**
   * Test {@link JsonReader#nextLong()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextLong()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JsonReader.nextLong()"})
  public void testNextLong_givenStringReaderWithFoo_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class, () -> new JsonReader(new StringReader("foo")).nextLong());
  }

  /**
   * Test {@link JsonReader#nextLong()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code See}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextLong()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JsonReader.nextLong()"})
  public void testNextLong_givenStringReaderWithSee_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class, () -> new JsonReader(new StringReader("\nSee ")).nextLong());
  }

  /**
   * Test {@link JsonReader#nextInt()}.
   *
   * <p>Method under test: {@link JsonReader#nextInt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonReader.nextInt()"})
  public void testNextInt() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () ->
            new JsonReader(
                    new StringReader(
                        "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed JSON"))
                .nextInt());
  }

  /**
   * Test {@link JsonReader#nextInt()}.
   *
   * <p>Method under test: {@link JsonReader#nextInt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonReader.nextInt()"})
  public void testNextInt2() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () ->
            new JsonReader(new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray()))
                .nextInt());
  }

  /**
   * Test {@link JsonReader#nextInt()}.
   *
   * <ul>
   *   <li>Given {@link CharArrayReader#CharArrayReader(char[])} with {@code ﻿} toCharArray.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextInt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonReader.nextInt()"})
  public void testNextInt_givenCharArrayReaderWithZeroWidthNoBreakSpaceToCharArray()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray())).nextInt());
  }

  /**
   * Test {@link JsonReader#nextInt()}.
   *
   * <ul>
   *   <li>Given {@link JsonReader#JsonReader(Reader)} with in is {@link
   *       StringReader#StringReader(String)} Strictness is {@code STRICT}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextInt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonReader.nextInt()"})
  public void testNextInt_givenJsonReaderWithInIsStringReaderStrictnessIsStrict()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.STRICT);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.nextInt());
  }

  /**
   * Test {@link JsonReader#nextInt()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then return forty-two.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextInt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonReader.nextInt()"})
  public void testNextInt_givenStringReaderWith42_thenReturnFortyTwo() throws IOException {
    // Arrange, Act and Assert
    assertEquals(42, new JsonReader(new StringReader("42")).nextInt());
  }

  /**
   * Test {@link JsonReader#nextInt()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then return forty-two.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextInt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonReader.nextInt()"})
  public void testNextInt_givenStringReaderWith42_thenReturnFortyTwo2() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("42"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertEquals(42, jsonReader.nextInt());
  }

  /**
   * Test {@link JsonReader#nextInt()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code at line}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextInt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonReader.nextInt()"})
  public void testNextInt_givenStringReaderWithAtLine_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new StringReader(" at line ")).nextInt());
  }

  /**
   * Test {@link JsonReader#nextInt()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then throw {@link EOFException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextInt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonReader.nextInt()"})
  public void testNextInt_givenStringReaderWithEmptyString_thenThrowEOFException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(EOFException.class, () -> new JsonReader(new StringReader("")).nextInt());
  }

  /**
   * Test {@link JsonReader#nextInt()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then throw {@link EOFException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextInt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonReader.nextInt()"})
  public void testNextInt_givenStringReaderWithEmptyString_thenThrowEOFException2()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(""));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(EOFException.class, () -> jsonReader.nextInt());
  }

  /**
   * Test {@link JsonReader#nextInt()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextInt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonReader.nextInt()"})
  public void testNextInt_givenStringReaderWithFalseToString_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> new JsonReader(new StringReader(Boolean.FALSE.toString())).nextInt());
  }

  /**
   * Test {@link JsonReader#nextInt()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextInt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonReader.nextInt()"})
  public void testNextInt_givenStringReaderWithFalseToString_thenThrowIllegalStateException2()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(Boolean.FALSE.toString()));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.nextInt());
  }

  /**
   * Test {@link JsonReader#nextInt()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code FALSE}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextInt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonReader.nextInt()"})
  public void testNextInt_givenStringReaderWithFalse_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonReader(new StringReader("FALSE")).nextInt());
  }

  /**
   * Test {@link JsonReader#nextInt()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextInt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonReader.nextInt()"})
  public void testNextInt_givenStringReaderWithFoo_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class, () -> new JsonReader(new StringReader("foo")).nextInt());
  }

  /**
   * Test {@link JsonReader#nextInt()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code See}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextInt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonReader.nextInt()"})
  public void testNextInt_givenStringReaderWithSee_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class, () -> new JsonReader(new StringReader("\nSee ")).nextInt());
  }

  /**
   * Test {@link JsonReader#skipValue()}.
   *
   * <p>Method under test: {@link JsonReader#skipValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.skipValue()"})
  public void testSkipValue() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () ->
            new JsonReader(
                    new StringReader(
                        "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed JSON"))
                .skipValue());
  }

  /**
   * Test {@link JsonReader#skipValue()}.
   *
   * <p>Method under test: {@link JsonReader#skipValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.skipValue()"})
  public void testSkipValue2() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () ->
            new JsonReader(new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray()))
                .skipValue());
  }

  /**
   * Test {@link JsonReader#skipValue()}.
   *
   * <ul>
   *   <li>Given {@link CharArrayReader#CharArrayReader(char[])} with {@code ﻿} toCharArray.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#skipValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.skipValue()"})
  public void testSkipValue_givenCharArrayReaderWithZeroWidthNoBreakSpaceToCharArray()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray())).skipValue());
  }

  /**
   * Test {@link JsonReader#skipValue()}.
   *
   * <ul>
   *   <li>Given {@link JsonReader#JsonReader(Reader)} with in is {@link
   *       StringReader#StringReader(String)} Strictness is {@code STRICT}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#skipValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.skipValue()"})
  public void testSkipValue_givenJsonReaderWithInIsStringReaderStrictnessIsStrict()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.STRICT);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.skipValue());
  }

  /**
   * Test {@link JsonReader#skipValue()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code at line}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#skipValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.skipValue()"})
  public void testSkipValue_givenStringReaderWithAtLine_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new StringReader(" at line ")).skipValue());
  }

  /**
   * Test {@link JsonReader#skipValue()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then throw {@link EOFException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#skipValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.skipValue()"})
  public void testSkipValue_givenStringReaderWithEmptyString_thenThrowEOFException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(EOFException.class, () -> new JsonReader(new StringReader("")).skipValue());
  }

  /**
   * Test {@link JsonReader#skipValue()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then throw {@link EOFException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#skipValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.skipValue()"})
  public void testSkipValue_givenStringReaderWithEmptyString_thenThrowEOFException2()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(""));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(EOFException.class, () -> jsonReader.skipValue());
  }

  /**
   * Test {@link JsonReader#skipValue()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#skipValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.skipValue()"})
  public void testSkipValue_givenStringReaderWithFoo_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class, () -> new JsonReader(new StringReader("foo")).skipValue());
  }

  /**
   * Test {@link JsonReader#skipValue()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code See}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#skipValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.skipValue()"})
  public void testSkipValue_givenStringReaderWithSee_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class, () -> new JsonReader(new StringReader("\nSee ")).skipValue());
  }

  /**
   * Test {@link JsonReader#locationString()}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#locationString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.locationString()"})
  public void testLocationString_givenJsonTreeReaderWithElementIsInstance() {
    // Arrange, Act and Assert
    assertEquals(
        " at line 1 column 1 path $",
        ((JsonReader) new JsonTreeReader(JsonNull.INSTANCE)).locationString());
  }

  /**
   * Test {@link JsonReader#locationString()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#locationString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.locationString()"})
  public void testLocationString_givenStringReaderWithFoo() {
    // Arrange, Act and Assert
    assertEquals(
        " at line 1 column 1 path $", new JsonReader(new StringReader("foo")).locationString());
  }

  /**
   * Test {@link JsonReader#getPath()}.
   *
   * <p>Method under test: {@link JsonReader#getPath()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.getPath()"})
  public void testGetPath() {
    // Arrange, Act and Assert
    assertEquals("$", new JsonReader(new StringReader("foo")).getPath());
  }

  /**
   * Test {@link JsonReader#getPreviousPath()}.
   *
   * <p>Method under test: {@link JsonReader#getPreviousPath()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.getPreviousPath()"})
  public void testGetPreviousPath() {
    // Arrange, Act and Assert
    assertEquals("$", new JsonReader(new StringReader("foo")).getPreviousPath());
  }
}
