package com.google.gson.stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.JsonArrayDiffblueTestFactory;
import com.google.gson.Strictness;
import com.google.gson.internal.bind.JsonTreeReader;
import java.io.CharArrayReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JsonReaderDiffblueTest {
  @InjectMocks private JsonReader jsonReader;

  @Mock private Reader reader;

  /**
   * Test {@link JsonReader#JsonReader(Reader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with a string.
   *   <li>Then return Path is {@code $}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#JsonReader(Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.<init>(Reader)"})
  public void testNewJsonReader_whenStringReaderWithAString_thenReturnPathIsDollarSign() {
    // Arrange and Act
    JsonReader actualJsonReader =
        new JsonReader(
            new StringReader(
                "\"This is a test string for the java.io.StringReader method. It includes various"
                    + " characters such as numbers 123, special characters @#$%, and spaces.\""));

    // Assert
    assertEquals("$", actualJsonReader.getPath());
    assertEquals("$", actualJsonReader.getPreviousPath());
    assertEquals(0, actualJsonReader.getPeeked());
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
    JsonReader jsonReader =
        new JsonReader(
            new StringReader(
                "\"This is a test string for the java.io.StringReader method. It includes various"
                    + " characters such as numbers 123, special characters @#$%, and spaces.\""));

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
    JsonReader jsonReader =
        new JsonReader(
            new StringReader(
                "\"This is a test string for the java.io.StringReader method. It includes various"
                    + " characters such as numbers 123, special characters @#$%, and spaces.\""));

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
   *   <li>Given {@link StringReader#StringReader(String)} with a string.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#isLenient()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.isLenient()"})
  public void testIsLenient_givenStringReaderWithAString_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(
        new JsonReader(
                new StringReader(
                    "\"This is a test string for the java.io.StringReader method. It includes"
                        + " various characters such as numbers 123, special characters @#$%, and"
                        + " spaces.\""))
            .isLenient());
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
    JsonTreeReader jsonTreeReader =
        new JsonTreeReader(JsonArrayDiffblueTestFactory.createJsonArrayWithElements());
    jsonTreeReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertTrue(jsonTreeReader.isLenient());
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
        () ->
            new JsonReader(
                    new StringReader(
                        "\"This is a test string for the java.io.StringReader method. It includes"
                            + " various characters such as numbers 123, special characters @#$%,"
                            + " and spaces.\""))
                .setNestingLimit(-1));
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
    JsonReader jsonReader =
        new JsonReader(
            new StringReader(
                "\"This is a test string for the java.io.StringReader method. It includes various"
                    + " characters such as numbers 123, special characters @#$%, and spaces.\""));

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
   *   <li>{@link JsonReader#getPeeked()}
   *   <li>{@link JsonReader#getStrictness()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int JsonReader.getNestingLimit()",
    "int JsonReader.getPeeked()",
    "Strictness JsonReader.getStrictness()",
    "void JsonReader.setStrictness(Strictness)",
    "String JsonReader.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    JsonReader jsonReader =
        new JsonReader(
            new StringReader(
                "\"This is a test string for the java.io.StringReader method. It includes various"
                    + " characters such as numbers 123, special characters @#$%, and spaces.\""));

    // Act
    jsonReader.setStrictness(Strictness.LENIENT);
    String actualToStringResult = jsonReader.toString();
    int actualNestingLimit = jsonReader.getNestingLimit();
    int actualPeeked = jsonReader.getPeeked();

    // Assert
    assertEquals("JsonReader at line 1 column 1 path $", actualToStringResult);
    assertEquals(0, actualPeeked);
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
    // Arrange
    CharArrayReader in = new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray());

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).beginArray());
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
    // Arrange
    StringReader in = new StringReader(" but was ");
    in.read("\u0001\u0006\u0001\u0006".toCharArray(), 1, 3);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).beginArray());
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
    // Arrange
    CharArrayReader in = new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray());

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).beginArray());
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
   *   <li>Given {@link StringReader#StringReader(String)} with a string skip one.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#beginArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginArray()"})
  public void testBeginArray_givenStringReaderWithAStringSkipOne() throws IOException {
    // Arrange
    StringReader in =
        new StringReader(
            "\"This is a test string for the java.io.StringReader method. It includes various"
                + " characters such as numbers 123, special characters @#$%, and spaces.\"");
    in.skip(1L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).beginArray());
  }

  /**
   * Test {@link JsonReader#beginArray()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with a string.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#beginArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginArray()"})
  public void testBeginArray_givenStringReaderWithAString_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () ->
            new JsonReader(
                    new StringReader(
                        "\"This is a test string for the java.io.StringReader method. It includes"
                            + " various characters such as numbers 123, special characters @#$%,"
                            + " and spaces.\""))
                .beginArray());
  }

  /**
   * Test {@link JsonReader#beginArray()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with a string.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#beginArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginArray()"})
  public void testBeginArray_givenStringReaderWithAString_thenThrowIllegalStateException2()
      throws IOException {
    // Arrange
    JsonReader jsonReader =
        new JsonReader(
            new StringReader(
                "\"This is a test string for the java.io.StringReader method. It includes various"
                    + " characters such as numbers 123, special characters @#$%, and spaces.\""));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.beginArray());
  }

  /**
   * Test {@link JsonReader#beginArray()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code BEGIN_ARRAY}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#beginArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginArray()"})
  public void testBeginArray_givenStringReaderWithBeginArray_thenThrowIllegalStateException()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("BEGIN_ARRAY"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.beginArray());
  }

  /**
   * Test {@link JsonReader#beginArray()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code BEGIN_ARRAY}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#beginArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginArray()"})
  public void testBeginArray_givenStringReaderWithBeginArray_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new StringReader("BEGIN_ARRAY")).beginArray());
  }

  /**
   * Test {@link JsonReader#beginArray()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code but was}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#beginArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginArray()"})
  public void testBeginArray_givenStringReaderWithButWas_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new StringReader(" but was ")).beginArray());
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
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#beginArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginArray()"})
  public void testBeginArray_givenStringReaderWithExpected_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new StringReader("Expected ")).beginArray());
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
    // Arrange
    StringReader in = new StringReader(Boolean.FALSE.toString());

    JsonReader jsonReader = new JsonReader(in);
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
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("FALSE"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.beginArray());
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
   * Test {@link JsonReader#beginArray()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#beginArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginArray()"})
  public void testBeginArray_thenThrowIllegalArgumentException() throws IOException {
    // Arrange
    when(reader.read(Mockito.<char[]>any(), anyInt(), anyInt()))
        .thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> jsonReader.beginArray());
    verify(reader).read(isA(char[].class), eq(0), eq(1024));
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
    // Arrange
    CharArrayReader in = new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray());

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).endArray());
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
    // Arrange
    StringReader in = new StringReader(" but was ");
    in.read("\u0001\u0006\u0001\u0006".toCharArray(), 1, 3);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).endArray());
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
    // Arrange
    CharArrayReader in = new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray());

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).endArray());
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
   *   <li>Given {@link StringReader#StringReader(String)} with a string skip one.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endArray()"})
  public void testEndArray_givenStringReaderWithAStringSkipOne_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange
    StringReader in =
        new StringReader(
            "\"This is a test string for the java.io.StringReader method. It includes various"
                + " characters such as numbers 123, special characters @#$%, and spaces.\"");
    in.skip(1L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).endArray());
  }

  /**
   * Test {@link JsonReader#endArray()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with a string.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endArray()"})
  public void testEndArray_givenStringReaderWithAString_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () ->
            new JsonReader(
                    new StringReader(
                        "\"This is a test string for the java.io.StringReader method. It includes"
                            + " various characters such as numbers 123, special characters @#$%,"
                            + " and spaces.\""))
                .endArray());
  }

  /**
   * Test {@link JsonReader#endArray()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with a string.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endArray()"})
  public void testEndArray_givenStringReaderWithAString_thenThrowIllegalStateException2()
      throws IOException {
    // Arrange
    JsonReader jsonReader =
        new JsonReader(
            new StringReader(
                "\"This is a test string for the java.io.StringReader method. It includes various"
                    + " characters such as numbers 123, special characters @#$%, and spaces.\""));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.endArray());
  }

  /**
   * Test {@link JsonReader#endArray()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code but was}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endArray()"})
  public void testEndArray_givenStringReaderWithButWas_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new StringReader(" but was ")).endArray());
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
   *   <li>Given {@link StringReader#StringReader(String)} with {@code END_ARRAY} skip one.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endArray()"})
  public void testEndArray_givenStringReaderWithEndArraySkipOne() throws IOException {
    // Arrange
    StringReader in = new StringReader("END_ARRAY");
    in.skip(1L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).endArray());
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
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endArray()"})
  public void testEndArray_givenStringReaderWithEndArray_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new StringReader("END_ARRAY")).endArray());
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
    // Arrange
    StringReader in = new StringReader(Boolean.FALSE.toString());

    JsonReader jsonReader = new JsonReader(in);
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
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("FALSE"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.endArray());
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
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new StringReader("unexpected-json-structure")).endArray());
  }

  /**
   * Test {@link JsonReader#endArray()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code unexpected-json-structure}
   *       skip one.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endArray()"})
  public void testEndArray_givenStringReaderWithUnexpectedJsonStructureSkipOne()
      throws IOException {
    // Arrange
    StringReader in = new StringReader("unexpected-json-structure");
    in.skip(1L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).endArray());
  }

  /**
   * Test {@link JsonReader#endArray()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endArray()"})
  public void testEndArray_thenThrowIllegalArgumentException() throws IOException {
    // Arrange
    when(reader.read(Mockito.<char[]>any(), anyInt(), anyInt()))
        .thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> jsonReader.endArray());
    verify(reader).read(isA(char[].class), eq(0), eq(1024));
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
    // Arrange
    CharArrayReader in = new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray());

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).beginObject());
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
    // Arrange
    StringReader in = new StringReader(" but was ");
    in.read("\u0001\u0006\u0001\u0006".toCharArray(), 1, 3);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).beginObject());
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
    // Arrange
    CharArrayReader in = new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray());

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).beginObject());
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
   *   <li>Given {@link StringReader#StringReader(String)} with a string skip one.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#beginObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginObject()"})
  public void testBeginObject_givenStringReaderWithAStringSkipOne() throws IOException {
    // Arrange
    StringReader in =
        new StringReader(
            "\"This is a test string for the java.io.StringReader method. It includes various"
                + " characters such as numbers 123, special characters @#$%, and spaces.\"");
    in.skip(1L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).beginObject());
  }

  /**
   * Test {@link JsonReader#beginObject()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with a string.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#beginObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginObject()"})
  public void testBeginObject_givenStringReaderWithAString_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () ->
            new JsonReader(
                    new StringReader(
                        "\"This is a test string for the java.io.StringReader method. It includes"
                            + " various characters such as numbers 123, special characters @#$%,"
                            + " and spaces.\""))
                .beginObject());
  }

  /**
   * Test {@link JsonReader#beginObject()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with a string.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#beginObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginObject()"})
  public void testBeginObject_givenStringReaderWithAString_thenThrowIllegalStateException2()
      throws IOException {
    // Arrange
    JsonReader jsonReader =
        new JsonReader(
            new StringReader(
                "\"This is a test string for the java.io.StringReader method. It includes various"
                    + " characters such as numbers 123, special characters @#$%, and spaces.\""));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.beginObject());
  }

  /**
   * Test {@link JsonReader#beginObject()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code BEGIN_OBJECT}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#beginObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginObject()"})
  public void testBeginObject_givenStringReaderWithBeginObject_thenThrowIllegalStateException()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("BEGIN_OBJECT"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.beginObject());
  }

  /**
   * Test {@link JsonReader#beginObject()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code BEGIN_OBJECT}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#beginObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginObject()"})
  public void testBeginObject_givenStringReaderWithBeginObject_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new StringReader("BEGIN_OBJECT")).beginObject());
  }

  /**
   * Test {@link JsonReader#beginObject()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code but was}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#beginObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginObject()"})
  public void testBeginObject_givenStringReaderWithButWas_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new StringReader(" but was ")).beginObject());
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
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#beginObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginObject()"})
  public void testBeginObject_givenStringReaderWithExpected_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new StringReader("Expected ")).beginObject());
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
    // Arrange
    StringReader in = new StringReader(Boolean.FALSE.toString());

    JsonReader jsonReader = new JsonReader(in);
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
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("FALSE"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.beginObject());
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
   * Test {@link JsonReader#beginObject()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#beginObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.beginObject()"})
  public void testBeginObject_thenThrowIllegalArgumentException() throws IOException {
    // Arrange
    when(reader.read(Mockito.<char[]>any(), anyInt(), anyInt()))
        .thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> jsonReader.beginObject());
    verify(reader).read(isA(char[].class), eq(0), eq(1024));
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
    // Arrange
    CharArrayReader in = new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray());

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).endObject());
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
    // Arrange
    StringReader in = new StringReader(" but was ");
    in.read("\u0001\u0006\u0001\u0006".toCharArray(), 1, 3);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).endObject());
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
    // Arrange
    CharArrayReader in = new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray());

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).endObject());
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
   *   <li>Given {@link StringReader#StringReader(String)} with a string skip one.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endObject()"})
  public void testEndObject_givenStringReaderWithAStringSkipOne() throws IOException {
    // Arrange
    StringReader in =
        new StringReader(
            "\"This is a test string for the java.io.StringReader method. It includes various"
                + " characters such as numbers 123, special characters @#$%, and spaces.\"");
    in.skip(1L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).endObject());
  }

  /**
   * Test {@link JsonReader#endObject()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with a string.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endObject()"})
  public void testEndObject_givenStringReaderWithAString_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () ->
            new JsonReader(
                    new StringReader(
                        "\"This is a test string for the java.io.StringReader method. It includes"
                            + " various characters such as numbers 123, special characters @#$%,"
                            + " and spaces.\""))
                .endObject());
  }

  /**
   * Test {@link JsonReader#endObject()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with a string.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endObject()"})
  public void testEndObject_givenStringReaderWithAString_thenThrowIllegalStateException2()
      throws IOException {
    // Arrange
    JsonReader jsonReader =
        new JsonReader(
            new StringReader(
                "\"This is a test string for the java.io.StringReader method. It includes various"
                    + " characters such as numbers 123, special characters @#$%, and spaces.\""));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.endObject());
  }

  /**
   * Test {@link JsonReader#endObject()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code but was}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endObject()"})
  public void testEndObject_givenStringReaderWithButWas_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new StringReader(" but was ")).endObject());
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
   *   <li>Given {@link StringReader#StringReader(String)} with {@code END_OBJECT} skip nine.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endObject()"})
  public void testEndObject_givenStringReaderWithEndObjectSkipNine() throws IOException {
    // Arrange
    StringReader in = new StringReader("END_OBJECT");
    in.skip(9L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).endObject());
  }

  /**
   * Test {@link JsonReader#endObject()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code END_OBJECT} skip one.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endObject()"})
  public void testEndObject_givenStringReaderWithEndObjectSkipOne() throws IOException {
    // Arrange
    StringReader in = new StringReader("END_OBJECT");
    in.skip(1L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).endObject());
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
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endObject()"})
  public void testEndObject_givenStringReaderWithEndObject_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new StringReader("END_OBJECT")).endObject());
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
    // Arrange
    StringReader in = new StringReader(Boolean.FALSE.toString());

    JsonReader jsonReader = new JsonReader(in);
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
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("FALSE"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.endObject());
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
   *   <li>Given {@link StringReader#StringReader(String)} with {@code malformed-json} skip nine.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endObject()"})
  public void testEndObject_givenStringReaderWithMalformedJsonSkipNine() throws IOException {
    // Arrange
    StringReader in = new StringReader("malformed-json");
    in.skip(9L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).endObject());
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
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new StringReader("unexpected-json-structure")).endObject());
  }

  /**
   * Test {@link JsonReader#endObject()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code unexpected-json-structure}
   *       skip one.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endObject()"})
  public void testEndObject_givenStringReaderWithUnexpectedJsonStructureSkipOne()
      throws IOException {
    // Arrange
    StringReader in = new StringReader("unexpected-json-structure");
    in.skip(1L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).endObject());
  }

  /**
   * Test {@link JsonReader#endObject()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#endObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.endObject()"})
  public void testEndObject_thenThrowIllegalArgumentException() throws IOException {
    // Arrange
    when(reader.read(Mockito.<char[]>any(), anyInt(), anyInt()))
        .thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> jsonReader.endObject());
    verify(reader).read(isA(char[].class), eq(0), eq(1024));
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
    // Arrange
    CharArrayReader in = new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray());

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).hasNext());
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
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(1L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).hasNext());
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
  public void testHasNext3() throws IOException {
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(6L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).hasNext());
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
  public void testHasNext4() throws IOException {
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(5L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).hasNext());
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
  public void testHasNext5() throws IOException {
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(6L);

    JsonReader jsonReader = new JsonReader(in);
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(EOFException.class, () -> jsonReader.hasNext());
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
  public void testHasNext6() throws IOException {
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(59L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).hasNext());
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
  public void testHasNext7() throws IOException {
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(59L);

    JsonReader jsonReader = new JsonReader(in);
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(EOFException.class, () -> jsonReader.hasNext());
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
  public void testHasNext8() throws IOException {
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(35L);

    JsonReader jsonReader = new JsonReader(in);
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.hasNext());
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
    // Arrange
    CharArrayReader in = new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray());

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).hasNext());
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
   *   <li>Given {@link StringReader#StringReader(String)} with a string skip one.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#hasNext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.hasNext()"})
  public void testHasNext_givenStringReaderWithAStringSkipOne_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange
    StringReader in =
        new StringReader(
            "\"This is a test string for the java.io.StringReader method. It includes various"
                + " characters such as numbers 123, special characters @#$%, and spaces.\"");
    in.skip(1L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).hasNext());
  }

  /**
   * Test {@link JsonReader#hasNext()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code at line}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#hasNext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.hasNext()"})
  public void testHasNext_givenStringReaderWithAtLine() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(" at line "));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act
    boolean actualHasNextResult = jsonReader.hasNext();

    // Assert
    assertEquals(10, jsonReader.getPeeked());
    assertTrue(actualHasNextResult);
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
   *   <li>Given {@link StringReader#StringReader(String)} with {@code End of input}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#hasNext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.hasNext()"})
  public void testHasNext_givenStringReaderWithEndOfInput() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("End of input"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act
    boolean actualHasNextResult = jsonReader.hasNext();

    // Assert
    assertEquals(10, jsonReader.getPeeked());
    assertTrue(actualHasNextResult);
  }

  /**
   * Test {@link JsonReader#hasNext()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code End of input} skip eleven.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#hasNext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.hasNext()"})
  public void testHasNext_givenStringReaderWithEndOfInputSkipEleven() throws IOException {
    // Arrange
    StringReader in = new StringReader("End of input");
    in.skip(11L);

    JsonReader jsonReader = new JsonReader(in);
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act
    boolean actualHasNextResult = jsonReader.hasNext();

    // Assert
    assertEquals(10, jsonReader.getPeeked());
    assertTrue(actualHasNextResult);
  }

  /**
   * Test {@link JsonReader#hasNext()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code End of input} skip one.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#hasNext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.hasNext()"})
  public void testHasNext_givenStringReaderWithEndOfInputSkipOne() throws IOException {
    // Arrange
    StringReader in = new StringReader("End of input");
    in.skip(1L);

    JsonReader jsonReader = new JsonReader(in);
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act
    boolean actualHasNextResult = jsonReader.hasNext();

    // Assert
    assertEquals(10, jsonReader.getPeeked());
    assertTrue(actualHasNextResult);
  }

  /**
   * Test {@link JsonReader#hasNext()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code FALSE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#hasNext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.hasNext()"})
  public void testHasNext_givenStringReaderWithFalse() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("FALSE"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act
    boolean actualHasNextResult = jsonReader.hasNext();

    // Assert
    assertEquals(6, jsonReader.getPeeked());
    assertTrue(actualHasNextResult);
  }

  /**
   * Test {@link JsonReader#hasNext()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#hasNext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.hasNext()"})
  public void testHasNext_givenStringReaderWithFalseToString() throws IOException {
    // Arrange
    StringReader in = new StringReader(Boolean.FALSE.toString());

    JsonReader jsonReader = new JsonReader(in);
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act
    boolean actualHasNextResult = jsonReader.hasNext();

    // Assert
    assertEquals(6, jsonReader.getPeeked());
    assertTrue(actualHasNextResult);
  }

  /**
   * Test {@link JsonReader#hasNext()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#hasNext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.hasNext()"})
  public void testHasNext_givenStringReaderWithFoo() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act
    boolean actualHasNextResult = jsonReader.hasNext();

    // Assert
    assertEquals(10, jsonReader.getPeeked());
    assertTrue(actualHasNextResult);
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
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("\nSee "));
    jsonReader.setStrictness(Strictness.STRICT);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.hasNext());
  }

  /**
   * Test {@link JsonReader#hasNext()}.
   *
   * <ul>
   *   <li>Then {@link JsonReader#JsonReader(Reader)} with in is {@link
   *       StringReader#StringReader(String)} Peeked is fifteen.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#hasNext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.hasNext()"})
  public void testHasNext_thenJsonReaderWithInIsStringReaderPeekedIsFifteen() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("42"));

    // Act
    boolean actualHasNextResult = jsonReader.hasNext();

    // Assert
    assertEquals(15, jsonReader.getPeeked());
    assertTrue(actualHasNextResult);
  }

  /**
   * Test {@link JsonReader#hasNext()}.
   *
   * <ul>
   *   <li>Then {@link JsonReader#JsonReader(Reader)} with in is {@link
   *       StringReader#StringReader(String)} Peeked is nine.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#hasNext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.hasNext()"})
  public void testHasNext_thenJsonReaderWithInIsStringReaderPeekedIsNine() throws IOException {
    // Arrange
    JsonReader jsonReader =
        new JsonReader(
            new StringReader(
                "\"This is a test string for the java.io.StringReader method. It includes various"
                    + " characters such as numbers 123, special characters @#$%, and spaces.\""));

    // Act
    boolean actualHasNextResult = jsonReader.hasNext();

    // Assert
    assertEquals(9, jsonReader.getPeeked());
    assertTrue(actualHasNextResult);
  }

  /**
   * Test {@link JsonReader#hasNext()}.
   *
   * <ul>
   *   <li>Then {@link JsonReader#JsonReader(Reader)} with in is {@link
   *       StringReader#StringReader(String)} Peeked is nine.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#hasNext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.hasNext()"})
  public void testHasNext_thenJsonReaderWithInIsStringReaderPeekedIsNine2() throws IOException {
    // Arrange
    JsonReader jsonReader =
        new JsonReader(
            new StringReader(
                "\"This is a test string for the java.io.StringReader method. It includes various"
                    + " characters such as numbers 123, special characters @#$%, and spaces.\""));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act
    boolean actualHasNextResult = jsonReader.hasNext();

    // Assert
    assertEquals(9, jsonReader.getPeeked());
    assertTrue(actualHasNextResult);
  }

  /**
   * Test {@link JsonReader#hasNext()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#hasNext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.hasNext()"})
  public void testHasNext_thenThrowIllegalArgumentException() throws IOException {
    // Arrange
    when(reader.read(Mockito.<char[]>any(), anyInt(), anyInt()))
        .thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> jsonReader.hasNext());
    verify(reader).read(isA(char[].class), eq(0), eq(1024));
  }

  /**
   * Test {@link JsonReader#peek()}.
   *
   * <p>Method under test: {@link JsonReader#peek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonToken JsonReader.peek()"})
  public void testPeek() throws IOException {
    // Arrange
    CharArrayReader in = new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray());

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).peek());
  }

  /**
   * Test {@link JsonReader#peek()}.
   *
   * <p>Method under test: {@link JsonReader#peek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonToken JsonReader.peek()"})
  public void testPeek2() throws IOException {
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(1L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).peek());
  }

  /**
   * Test {@link JsonReader#peek()}.
   *
   * <p>Method under test: {@link JsonReader#peek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonToken JsonReader.peek()"})
  public void testPeek3() throws IOException {
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(6L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).peek());
  }

  /**
   * Test {@link JsonReader#peek()}.
   *
   * <p>Method under test: {@link JsonReader#peek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonToken JsonReader.peek()"})
  public void testPeek4() throws IOException {
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(5L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).peek());
  }

  /**
   * Test {@link JsonReader#peek()}.
   *
   * <p>Method under test: {@link JsonReader#peek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonToken JsonReader.peek()"})
  public void testPeek5() throws IOException {
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(6L);

    JsonReader jsonReader = new JsonReader(in);
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(EOFException.class, () -> jsonReader.peek());
  }

  /**
   * Test {@link JsonReader#peek()}.
   *
   * <p>Method under test: {@link JsonReader#peek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonToken JsonReader.peek()"})
  public void testPeek6() throws IOException {
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(59L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).peek());
  }

  /**
   * Test {@link JsonReader#peek()}.
   *
   * <p>Method under test: {@link JsonReader#peek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonToken JsonReader.peek()"})
  public void testPeek7() throws IOException {
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(59L);

    JsonReader jsonReader = new JsonReader(in);
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(EOFException.class, () -> jsonReader.peek());
  }

  /**
   * Test {@link JsonReader#peek()}.
   *
   * <p>Method under test: {@link JsonReader#peek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonToken JsonReader.peek()"})
  public void testPeek8() throws IOException {
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(35L);

    JsonReader jsonReader = new JsonReader(in);
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.peek());
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
  @MethodsUnderTest({"JsonToken JsonReader.peek()"})
  public void testPeek_givenCharArrayReaderWithZeroWidthNoBreakSpaceToCharArray()
      throws IOException {
    // Arrange
    CharArrayReader in = new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray());

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).peek());
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
  @MethodsUnderTest({"JsonToken JsonReader.peek()"})
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
   *   <li>Given {@link StringReader#StringReader(String)} with a string skip one.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#peek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonToken JsonReader.peek()"})
  public void testPeek_givenStringReaderWithAStringSkipOne_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange
    StringReader in =
        new StringReader(
            "\"This is a test string for the java.io.StringReader method. It includes various"
                + " characters such as numbers 123, special characters @#$%, and spaces.\"");
    in.skip(1L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).peek());
  }

  /**
   * Test {@link JsonReader#peek()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code at line}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#peek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonToken JsonReader.peek()"})
  public void testPeek_givenStringReaderWithAtLine() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(" at line "));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act
    JsonToken actualPeekResult = jsonReader.peek();

    // Assert
    assertEquals(10, jsonReader.getPeeked());
    assertEquals(JsonToken.STRING, actualPeekResult);
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
  @MethodsUnderTest({"JsonToken JsonReader.peek()"})
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
  @MethodsUnderTest({"JsonToken JsonReader.peek()"})
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
   *   <li>Given {@link StringReader#StringReader(String)} with {@code End of input}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#peek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonToken JsonReader.peek()"})
  public void testPeek_givenStringReaderWithEndOfInput() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("End of input"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act
    JsonToken actualPeekResult = jsonReader.peek();

    // Assert
    assertEquals(10, jsonReader.getPeeked());
    assertEquals(JsonToken.STRING, actualPeekResult);
  }

  /**
   * Test {@link JsonReader#peek()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code End of input} skip eleven.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#peek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonToken JsonReader.peek()"})
  public void testPeek_givenStringReaderWithEndOfInputSkipEleven() throws IOException {
    // Arrange
    StringReader in = new StringReader("End of input");
    in.skip(11L);

    JsonReader jsonReader = new JsonReader(in);
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act
    JsonToken actualPeekResult = jsonReader.peek();

    // Assert
    assertEquals(10, jsonReader.getPeeked());
    assertEquals(JsonToken.STRING, actualPeekResult);
  }

  /**
   * Test {@link JsonReader#peek()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code End of input} skip one.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#peek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonToken JsonReader.peek()"})
  public void testPeek_givenStringReaderWithEndOfInputSkipOne() throws IOException {
    // Arrange
    StringReader in = new StringReader("End of input");
    in.skip(1L);

    JsonReader jsonReader = new JsonReader(in);
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act
    JsonToken actualPeekResult = jsonReader.peek();

    // Assert
    assertEquals(10, jsonReader.getPeeked());
    assertEquals(JsonToken.STRING, actualPeekResult);
  }

  /**
   * Test {@link JsonReader#peek()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code FALSE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#peek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonToken JsonReader.peek()"})
  public void testPeek_givenStringReaderWithFalse() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("FALSE"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act
    JsonToken actualPeekResult = jsonReader.peek();

    // Assert
    assertEquals(6, jsonReader.getPeeked());
    assertEquals(JsonToken.BOOLEAN, actualPeekResult);
  }

  /**
   * Test {@link JsonReader#peek()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#peek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonToken JsonReader.peek()"})
  public void testPeek_givenStringReaderWithFalseToString() throws IOException {
    // Arrange
    StringReader in = new StringReader(Boolean.FALSE.toString());

    JsonReader jsonReader = new JsonReader(in);
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act
    JsonToken actualPeekResult = jsonReader.peek();

    // Assert
    assertEquals(6, jsonReader.getPeeked());
    assertEquals(JsonToken.BOOLEAN, actualPeekResult);
  }

  /**
   * Test {@link JsonReader#peek()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then {@link JsonReader#JsonReader(Reader)} with in is {@link
   *       StringReader#StringReader(String)} Peeked is ten.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#peek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonToken JsonReader.peek()"})
  public void testPeek_givenStringReaderWithFoo_thenJsonReaderWithInIsStringReaderPeekedIsTen()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act
    JsonToken actualPeekResult = jsonReader.peek();

    // Assert
    assertEquals(10, jsonReader.getPeeked());
    assertEquals(JsonToken.STRING, actualPeekResult);
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
  @MethodsUnderTest({"JsonToken JsonReader.peek()"})
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
  @MethodsUnderTest({"JsonToken JsonReader.peek()"})
  public void testPeek_givenStringReaderWithSee_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("\nSee "));
    jsonReader.setStrictness(Strictness.STRICT);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.peek());
  }

  /**
   * Test {@link JsonReader#peek()}.
   *
   * <ul>
   *   <li>Then {@link JsonReader#JsonReader(Reader)} with in is {@link
   *       StringReader#StringReader(String)} Peeked is fifteen.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#peek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonToken JsonReader.peek()"})
  public void testPeek_thenJsonReaderWithInIsStringReaderPeekedIsFifteen() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("42"));

    // Act
    JsonToken actualPeekResult = jsonReader.peek();

    // Assert
    assertEquals(15, jsonReader.getPeeked());
    assertEquals(JsonToken.NUMBER, actualPeekResult);
  }

  /**
   * Test {@link JsonReader#peek()}.
   *
   * <ul>
   *   <li>Then {@link JsonReader#JsonReader(Reader)} with in is {@link
   *       StringReader#StringReader(String)} Peeked is nine.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#peek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonToken JsonReader.peek()"})
  public void testPeek_thenJsonReaderWithInIsStringReaderPeekedIsNine() throws IOException {
    // Arrange
    JsonReader jsonReader =
        new JsonReader(
            new StringReader(
                "\"This is a test string for the java.io.StringReader method. It includes various"
                    + " characters such as numbers 123, special characters @#$%, and spaces.\""));

    // Act
    JsonToken actualPeekResult = jsonReader.peek();

    // Assert
    assertEquals(9, jsonReader.getPeeked());
    assertEquals(JsonToken.STRING, actualPeekResult);
  }

  /**
   * Test {@link JsonReader#peek()}.
   *
   * <ul>
   *   <li>Then {@link JsonReader#JsonReader(Reader)} with in is {@link
   *       StringReader#StringReader(String)} Peeked is nine.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#peek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonToken JsonReader.peek()"})
  public void testPeek_thenJsonReaderWithInIsStringReaderPeekedIsNine2() throws IOException {
    // Arrange
    JsonReader jsonReader =
        new JsonReader(
            new StringReader(
                "\"This is a test string for the java.io.StringReader method. It includes various"
                    + " characters such as numbers 123, special characters @#$%, and spaces.\""));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act
    JsonToken actualPeekResult = jsonReader.peek();

    // Assert
    assertEquals(9, jsonReader.getPeeked());
    assertEquals(JsonToken.STRING, actualPeekResult);
  }

  /**
   * Test {@link JsonReader#peek()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#peek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonToken JsonReader.peek()"})
  public void testPeek_thenThrowIllegalArgumentException() throws IOException {
    // Arrange
    when(reader.read(Mockito.<char[]>any(), anyInt(), anyInt()))
        .thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> jsonReader.peek());
    verify(reader).read(isA(char[].class), eq(0), eq(1024));
  }

  /**
   * Test {@link JsonReader#doPeek()}.
   *
   * <ul>
   *   <li>Given {@link Reader} {@link Reader#read(char[], int, int)} return minus one.
   *   <li>Then throw {@link EOFException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#doPeek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonReader.doPeek()"})
  public void testDoPeek_givenReaderReadReturnMinusOne_thenThrowEOFException() throws IOException {
    // Arrange
    when(reader.read(Mockito.<char[]>any(), anyInt(), anyInt())).thenReturn(-1);

    // Act and Assert
    assertThrows(EOFException.class, () -> jsonReader.doPeek());
    verify(reader).read(isA(char[].class), eq(0), eq(1024));
  }

  /**
   * Test {@link JsonReader#doPeek()}.
   *
   * <ul>
   *   <li>Given {@link Reader} {@link Reader#read(char[], int, int)} return one.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#doPeek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonReader.doPeek()"})
  public void testDoPeek_givenReaderReadReturnOne_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange
    when(reader.read(Mockito.<char[]>any(), anyInt(), anyInt())).thenReturn(1);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.doPeek());
    verify(reader).read(isA(char[].class), eq(0), eq(1024));
  }

  /**
   * Test {@link JsonReader#doPeek()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with a string.
   *   <li>Then return nine.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#doPeek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonReader.doPeek()"})
  public void testDoPeek_givenStringReaderWithAString_thenReturnNine() throws IOException {
    // Arrange
    JsonReader jsonReader =
        new JsonReader(
            new StringReader(
                "\"This is a test string for the java.io.StringReader method. It includes various"
                    + " characters such as numbers 123, special characters @#$%, and spaces.\""));

    // Act and Assert
    assertEquals(9, jsonReader.doPeek());
    assertEquals(9, jsonReader.getPeeked());
  }

  /**
   * Test {@link JsonReader#doPeek()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#doPeek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonReader.doPeek()"})
  public void testDoPeek_thenThrowIllegalArgumentException() throws IOException {
    // Arrange
    when(reader.read(Mockito.<char[]>any(), anyInt(), anyInt()))
        .thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> jsonReader.doPeek());
    verify(reader).read(isA(char[].class), eq(0), eq(1024));
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
    // Arrange
    CharArrayReader in = new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray());

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).nextName());
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
    // Arrange
    StringReader in = new StringReader(" but was ");
    in.read("\u0001\u0006\u0001\u0006".toCharArray(), 1, 3);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).nextName());
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
    // Arrange
    CharArrayReader in = new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray());

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).nextName());
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
   *   <li>Given {@link StringReader#StringReader(String)} with {@code a name} skip one.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextName()"})
  public void testNextName_givenStringReaderWithANameSkipOne_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange
    StringReader in = new StringReader("a name");
    in.skip(1L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).nextName());
  }

  /**
   * Test {@link JsonReader#nextName()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code a name}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextName()"})
  public void testNextName_givenStringReaderWithAName_thenThrowIllegalStateException()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("a name"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.nextName());
  }

  /**
   * Test {@link JsonReader#nextName()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code a name}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextName()"})
  public void testNextName_givenStringReaderWithAName_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class, () -> new JsonReader(new StringReader("a name")).nextName());
  }

  /**
   * Test {@link JsonReader#nextName()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with a string skip one.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextName()"})
  public void testNextName_givenStringReaderWithAStringSkipOne_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange
    StringReader in =
        new StringReader(
            "\"This is a test string for the java.io.StringReader method. It includes various"
                + " characters such as numbers 123, special characters @#$%, and spaces.\"");
    in.skip(1L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).nextName());
  }

  /**
   * Test {@link JsonReader#nextName()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with a string.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextName()"})
  public void testNextName_givenStringReaderWithAString_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () ->
            new JsonReader(
                    new StringReader(
                        "\"This is a test string for the java.io.StringReader method. It includes"
                            + " various characters such as numbers 123, special characters @#$%,"
                            + " and spaces.\""))
                .nextName());
  }

  /**
   * Test {@link JsonReader#nextName()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with a string.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextName()"})
  public void testNextName_givenStringReaderWithAString_thenThrowIllegalStateException2()
      throws IOException {
    // Arrange
    JsonReader jsonReader =
        new JsonReader(
            new StringReader(
                "\"This is a test string for the java.io.StringReader method. It includes various"
                    + " characters such as numbers 123, special characters @#$%, and spaces.\""));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.nextName());
  }

  /**
   * Test {@link JsonReader#nextName()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code but was}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextName()"})
  public void testNextName_givenStringReaderWithButWas_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new StringReader(" but was ")).nextName());
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
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextName()"})
  public void testNextName_givenStringReaderWithExpected_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new StringReader("Expected ")).nextName());
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
    // Arrange
    StringReader in = new StringReader(Boolean.FALSE.toString());

    JsonReader jsonReader = new JsonReader(in);
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
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("FALSE"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.nextName());
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
   * Test {@link JsonReader#nextName()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextName()"})
  public void testNextName_thenThrowIllegalArgumentException() throws IOException {
    // Arrange
    when(reader.read(Mockito.<char[]>any(), anyInt(), anyInt()))
        .thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> jsonReader.nextName());
    verify(reader).read(isA(char[].class), eq(0), eq(1024));
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
    // Arrange
    CharArrayReader in = new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray());

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).nextString());
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
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(1L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).nextString());
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
  public void testNextString3() throws IOException {
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(6L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).nextString());
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
  public void testNextString4() throws IOException {
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(5L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).nextString());
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
  public void testNextString5() throws IOException {
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.read("\u0001\u0006\u0001\u0006".toCharArray(), 1, 1);
    in.skip(6L);

    JsonReader jsonReader = new JsonReader(in);
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.nextString());
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
  public void testNextString6() throws IOException {
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(59L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).nextString());
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
  public void testNextString7() throws IOException {
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(59L);

    JsonReader jsonReader = new JsonReader(in);
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(EOFException.class, () -> jsonReader.nextString());
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
    // Arrange
    CharArrayReader in = new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray());

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).nextString());
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
   *   <li>Given {@link StringReader#StringReader(String)} with a string skip one.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextString()"})
  public void testNextString_givenStringReaderWithAStringSkipOne() throws IOException {
    // Arrange
    StringReader in =
        new StringReader(
            "\"This is a test string for the java.io.StringReader method. It includes various"
                + " characters such as numbers 123, special characters @#$%, and spaces.\"");
    in.skip(1L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).nextString());
  }

  /**
   * Test {@link JsonReader#nextString()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with a string skip one hundred
   *       forty-eight.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextString()"})
  public void testNextString_givenStringReaderWithAStringSkipOneHundredFortyEight()
      throws IOException {
    // Arrange
    StringReader in =
        new StringReader(
            "\"This is a test string for the java.io.StringReader method. It includes various"
                + " characters such as numbers 123, special characters @#$%, and spaces.\"");
    in.skip(148L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).nextString());
  }

  /**
   * Test {@link JsonReader#nextString()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with a string.
   *   <li>Then return a string.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextString()"})
  public void testNextString_givenStringReaderWithAString_thenReturnAString() throws IOException {
    // Arrange, Act and Assert
    assertEquals(
        "This is a test string for the java.io.StringReader method. It includes various characters"
            + " such as numbers 123, special characters @#$%, and spaces.",
        new JsonReader(
                new StringReader(
                    "\"This is a test string for the java.io.StringReader method. It includes"
                        + " various characters such as numbers 123, special characters @#$%, and"
                        + " spaces.\""))
            .nextString());
  }

  /**
   * Test {@link JsonReader#nextString()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with a string.
   *   <li>Then return a string.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextString()"})
  public void testNextString_givenStringReaderWithAString_thenReturnAString2() throws IOException {
    // Arrange
    JsonReader jsonReader =
        new JsonReader(
            new StringReader(
                "\"This is a test string for the java.io.StringReader method. It includes various"
                    + " characters such as numbers 123, special characters @#$%, and spaces.\""));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertEquals(
        "This is a test string for the java.io.StringReader method. It includes various characters"
            + " such as numbers 123, special characters @#$%, and spaces.",
        jsonReader.nextString());
  }

  /**
   * Test {@link JsonReader#nextString()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with a string.
   *   <li>Then return a string.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextString()"})
  public void testNextString_givenStringReaderWithAString_thenReturnAString3() throws IOException {
    // Arrange
    JsonReader jsonReader =
        new JsonReader(
            new StringReader(
                "\"This is a test string for the java.io.StringReader method. It includes various"
                    + " characters such as numbers 123, special characters @#$%, and spaces.\""));
    jsonReader.setStrictness(Strictness.STRICT);

    // Act and Assert
    assertEquals(
        "This is a test string for the java.io.StringReader method. It includes various characters"
            + " such as numbers 123, special characters @#$%, and spaces.",
        jsonReader.nextString());
  }

  /**
   * Test {@link JsonReader#nextString()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code at line}.
   *   <li>Then return {@code at}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextString()"})
  public void testNextString_givenStringReaderWithAtLine_thenReturnAt() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(" at line "));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertEquals("at", jsonReader.nextString());
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
   *   <li>Given {@link StringReader#StringReader(String)} with {@code End of input} skip eleven.
   *   <li>Then return {@code t}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextString()"})
  public void testNextString_givenStringReaderWithEndOfInputSkipEleven_thenReturnT()
      throws IOException {
    // Arrange
    StringReader in = new StringReader("End of input");
    in.skip(11L);

    JsonReader jsonReader = new JsonReader(in);
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertEquals("t", jsonReader.nextString());
  }

  /**
   * Test {@link JsonReader#nextString()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code End of input} skip one.
   *   <li>Then return {@code nd}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextString()"})
  public void testNextString_givenStringReaderWithEndOfInputSkipOne_thenReturnNd()
      throws IOException {
    // Arrange
    StringReader in = new StringReader("End of input");
    in.skip(1L);

    JsonReader jsonReader = new JsonReader(in);
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertEquals("nd", jsonReader.nextString());
  }

  /**
   * Test {@link JsonReader#nextString()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code End of input}.
   *   <li>Then return {@code End}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextString()"})
  public void testNextString_givenStringReaderWithEndOfInput_thenReturnEnd() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("End of input"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertEquals("End", jsonReader.nextString());
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
    // Arrange
    StringReader in = new StringReader(Boolean.FALSE.toString());

    JsonReader jsonReader = new JsonReader(in);
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
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("FALSE"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.nextString());
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
  public void testNextString_givenStringReaderWithFoo_thenThrowMalformedJsonException2()
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
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("\nSee "));
    jsonReader.setStrictness(Strictness.STRICT);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.nextString());
  }

  /**
   * Test {@link JsonReader#nextString()}.
   *
   * <ul>
   *   <li>Then return {@code ithub.com}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextString()"})
  public void testNextString_thenReturnIthubCom() throws IOException {
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.read("\u0001\u0006\u0001\u0006".toCharArray(), 1, 3);
    in.skip(6L);

    JsonReader jsonReader = new JsonReader(in);
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertEquals("ithub.com", jsonReader.nextString());
  }

  /**
   * Test {@link JsonReader#nextString()}.
   *
   * <ul>
   *   <li>Then throw {@link EOFException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextString()"})
  public void testNextString_thenThrowEOFException() throws IOException {
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(6L);

    JsonReader jsonReader = new JsonReader(in);
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(EOFException.class, () -> jsonReader.nextString());
  }

  /**
   * Test {@link JsonReader#nextString()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.nextString()"})
  public void testNextString_thenThrowIllegalArgumentException() throws IOException {
    // Arrange
    when(reader.read(Mockito.<char[]>any(), anyInt(), anyInt()))
        .thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> jsonReader.nextString());
    verify(reader).read(isA(char[].class), eq(0), eq(1024));
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
    // Arrange
    CharArrayReader in = new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray());

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).nextBoolean());
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
    // Arrange
    StringReader in = new StringReader(" but was ");
    in.read("\u0001\u0006\u0001\u0006".toCharArray(), 1, 3);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).nextBoolean());
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
    // Arrange
    CharArrayReader in = new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray());

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).nextBoolean());
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
   *   <li>Given {@link StringReader#StringReader(String)} with {@code a boolean}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.nextBoolean()"})
  public void testNextBoolean_givenStringReaderWithABoolean_thenThrowIllegalStateException()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("a boolean"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.nextBoolean());
  }

  /**
   * Test {@link JsonReader#nextBoolean()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code a boolean}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.nextBoolean()"})
  public void testNextBoolean_givenStringReaderWithABoolean_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new StringReader("a boolean")).nextBoolean());
  }

  /**
   * Test {@link JsonReader#nextBoolean()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with a string skip one.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.nextBoolean()"})
  public void testNextBoolean_givenStringReaderWithAStringSkipOne() throws IOException {
    // Arrange
    StringReader in =
        new StringReader(
            "\"This is a test string for the java.io.StringReader method. It includes various"
                + " characters such as numbers 123, special characters @#$%, and spaces.\"");
    in.skip(1L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).nextBoolean());
  }

  /**
   * Test {@link JsonReader#nextBoolean()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with a string.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.nextBoolean()"})
  public void testNextBoolean_givenStringReaderWithAString_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () ->
            new JsonReader(
                    new StringReader(
                        "\"This is a test string for the java.io.StringReader method. It includes"
                            + " various characters such as numbers 123, special characters @#$%,"
                            + " and spaces.\""))
                .nextBoolean());
  }

  /**
   * Test {@link JsonReader#nextBoolean()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with a string.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.nextBoolean()"})
  public void testNextBoolean_givenStringReaderWithAString_thenThrowIllegalStateException2()
      throws IOException {
    // Arrange
    JsonReader jsonReader =
        new JsonReader(
            new StringReader(
                "\"This is a test string for the java.io.StringReader method. It includes various"
                    + " characters such as numbers 123, special characters @#$%, and spaces.\""));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.nextBoolean());
  }

  /**
   * Test {@link JsonReader#nextBoolean()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code but was}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.nextBoolean()"})
  public void testNextBoolean_givenStringReaderWithButWas_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new StringReader(" but was ")).nextBoolean());
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
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.nextBoolean()"})
  public void testNextBoolean_givenStringReaderWithExpected_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new StringReader("Expected ")).nextBoolean());
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
    // Arrange
    StringReader in = new StringReader(Boolean.FALSE.toString());

    JsonReader jsonReader = new JsonReader(in);
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
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("FALSE"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertFalse(jsonReader.nextBoolean());
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
   * Test {@link JsonReader#nextBoolean()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonReader.nextBoolean()"})
  public void testNextBoolean_thenThrowIllegalArgumentException() throws IOException {
    // Arrange
    when(reader.read(Mockito.<char[]>any(), anyInt(), anyInt()))
        .thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> jsonReader.nextBoolean());
    verify(reader).read(isA(char[].class), eq(0), eq(1024));
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
    // Arrange
    CharArrayReader in = new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray());

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).nextNull());
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
    // Arrange
    StringReader in = new StringReader(" but was ");
    in.read("\u0001\u0006\u0001\u0006".toCharArray(), 1, 3);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).nextNull());
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
    // Arrange
    CharArrayReader in = new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray());

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).nextNull());
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
    JsonReader jsonReader = new JsonReader(new StringReader("null"));
    jsonReader.setStrictness(Strictness.STRICT);

    // Act and Assert
    jsonReader.nextNull();
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
   *   <li>Given {@link StringReader#StringReader(String)} with a string skip one.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.nextNull()"})
  public void testNextNull_givenStringReaderWithAStringSkipOne_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange
    StringReader in =
        new StringReader(
            "\"This is a test string for the java.io.StringReader method. It includes various"
                + " characters such as numbers 123, special characters @#$%, and spaces.\"");
    in.skip(1L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).nextNull());
  }

  /**
   * Test {@link JsonReader#nextNull()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with a string.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.nextNull()"})
  public void testNextNull_givenStringReaderWithAString_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () ->
            new JsonReader(
                    new StringReader(
                        "\"This is a test string for the java.io.StringReader method. It includes"
                            + " various characters such as numbers 123, special characters @#$%,"
                            + " and spaces.\""))
                .nextNull());
  }

  /**
   * Test {@link JsonReader#nextNull()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with a string.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.nextNull()"})
  public void testNextNull_givenStringReaderWithAString_thenThrowIllegalStateException2()
      throws IOException {
    // Arrange
    JsonReader jsonReader =
        new JsonReader(
            new StringReader(
                "\"This is a test string for the java.io.StringReader method. It includes various"
                    + " characters such as numbers 123, special characters @#$%, and spaces.\""));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.nextNull());
  }

  /**
   * Test {@link JsonReader#nextNull()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code but was}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.nextNull()"})
  public void testNextNull_givenStringReaderWithButWas_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new StringReader(" but was ")).nextNull());
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
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.nextNull()"})
  public void testNextNull_givenStringReaderWithExpected_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new StringReader("Expected ")).nextNull());
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
   *   <li>Given {@link StringReader#StringReader(String)} with {@code null}.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.nextNull()"})
  public void testNextNull_givenStringReaderWithNull_thenDoesNotThrow() throws IOException {
    // Arrange, Act and Assert
    new JsonReader(new StringReader("null")).nextNull();
  }

  /**
   * Test {@link JsonReader#nextNull()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code null}.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.nextNull()"})
  public void testNextNull_givenStringReaderWithNull_thenDoesNotThrow2() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("null"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    jsonReader.nextNull();
  }

  /**
   * Test {@link JsonReader#nextNull()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code NULL}.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.nextNull()"})
  public void testNextNull_givenStringReaderWithNull_thenDoesNotThrow3() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("NULL"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    jsonReader.nextNull();
  }

  /**
   * Test {@link JsonReader#nextNull()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code NULL}.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.nextNull()"})
  public void testNextNull_givenStringReaderWithNull_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("NULL"));
    jsonReader.setStrictness(Strictness.STRICT);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.nextNull());
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
   * Test {@link JsonReader#nextNull()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code unexpected-json-structure}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.nextNull()"})
  public void testNextNull_givenStringReaderWithUnexpectedJsonStructure() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        MalformedJsonException.class,
        () -> new JsonReader(new StringReader("unexpected-json-structure")).nextNull());
  }

  /**
   * Test {@link JsonReader#nextNull()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code unexpected-json-structure}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.nextNull()"})
  public void testNextNull_givenStringReaderWithUnexpectedJsonStructure2() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("unexpected-json-structure"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.nextNull());
  }

  /**
   * Test {@link JsonReader#nextNull()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.nextNull()"})
  public void testNextNull_thenThrowIllegalArgumentException() throws IOException {
    // Arrange
    when(reader.read(Mockito.<char[]>any(), anyInt(), anyInt()))
        .thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> jsonReader.nextNull());
    verify(reader).read(isA(char[].class), eq(0), eq(1024));
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
    // Arrange
    CharArrayReader in = new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray());

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).nextDouble());
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
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(1L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).nextDouble());
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
  public void testNextDouble3() throws IOException {
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(6L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).nextDouble());
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
  public void testNextDouble4() throws IOException {
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(5L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).nextDouble());
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
  public void testNextDouble5() throws IOException {
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(6L);

    JsonReader jsonReader = new JsonReader(in);
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(EOFException.class, () -> jsonReader.nextDouble());
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
  public void testNextDouble6() throws IOException {
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.read("\u0001\u0006\u0001\u0006".toCharArray(), 1, 1);
    in.skip(6L);

    JsonReader jsonReader = new JsonReader(in);
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.nextDouble());
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
  public void testNextDouble7() throws IOException {
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(59L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).nextDouble());
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
  public void testNextDouble8() throws IOException {
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(59L);

    JsonReader jsonReader = new JsonReader(in);
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(EOFException.class, () -> jsonReader.nextDouble());
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
    // Arrange
    CharArrayReader in = new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray());

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).nextDouble());
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
   *   <li>Given {@link StringReader#StringReader(String)} with a string skip one.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JsonReader.nextDouble()"})
  public void testNextDouble_givenStringReaderWithAStringSkipOne() throws IOException {
    // Arrange
    StringReader in =
        new StringReader(
            "\"This is a test string for the java.io.StringReader method. It includes various"
                + " characters such as numbers 123, special characters @#$%, and spaces.\"");
    in.skip(1L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).nextDouble());
  }

  /**
   * Test {@link JsonReader#nextDouble()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with a string skip one hundred
   *       forty-eight.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JsonReader.nextDouble()"})
  public void testNextDouble_givenStringReaderWithAStringSkipOneHundredFortyEight()
      throws IOException {
    // Arrange
    StringReader in =
        new StringReader(
            "\"This is a test string for the java.io.StringReader method. It includes various"
                + " characters such as numbers 123, special characters @#$%, and spaces.\"");
    in.skip(148L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).nextDouble());
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
    // Arrange
    StringReader in = new StringReader(Boolean.FALSE.toString());

    JsonReader jsonReader = new JsonReader(in);
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
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("FALSE"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.nextDouble());
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
  public void testNextDouble_givenStringReaderWithFoo_thenThrowMalformedJsonException2()
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
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("\nSee "));
    jsonReader.setStrictness(Strictness.STRICT);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.nextDouble());
  }

  /**
   * Test {@link JsonReader#nextDouble()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JsonReader.nextDouble()"})
  public void testNextDouble_thenThrowIllegalArgumentException() throws IOException {
    // Arrange
    when(reader.read(Mockito.<char[]>any(), anyInt(), anyInt()))
        .thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> jsonReader.nextDouble());
    verify(reader).read(isA(char[].class), eq(0), eq(1024));
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
    // Arrange
    CharArrayReader in = new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray());

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).nextLong());
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
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(1L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).nextLong());
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
  public void testNextLong3() throws IOException {
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(6L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).nextLong());
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
  public void testNextLong4() throws IOException {
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(5L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).nextLong());
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
  public void testNextLong5() throws IOException {
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(6L);

    JsonReader jsonReader = new JsonReader(in);
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(EOFException.class, () -> jsonReader.nextLong());
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
  public void testNextLong6() throws IOException {
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.read("\u0001\u0006\u0001\u0006".toCharArray(), 1, 1);
    in.skip(6L);

    JsonReader jsonReader = new JsonReader(in);
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.nextLong());
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
  public void testNextLong7() throws IOException {
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(59L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).nextLong());
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
  public void testNextLong8() throws IOException {
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(59L);

    JsonReader jsonReader = new JsonReader(in);
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(EOFException.class, () -> jsonReader.nextLong());
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
    // Arrange
    CharArrayReader in = new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray());

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).nextLong());
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
   *   <li>Given {@link StringReader#StringReader(String)} with a string skip one hundred
   *       forty-eight.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextLong()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JsonReader.nextLong()"})
  public void testNextLong_givenStringReaderWithAStringSkipOneHundredFortyEight()
      throws IOException {
    // Arrange
    StringReader in =
        new StringReader(
            "\"This is a test string for the java.io.StringReader method. It includes various"
                + " characters such as numbers 123, special characters @#$%, and spaces.\"");
    in.skip(148L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).nextLong());
  }

  /**
   * Test {@link JsonReader#nextLong()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with a string skip one.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextLong()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JsonReader.nextLong()"})
  public void testNextLong_givenStringReaderWithAStringSkipOne_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange
    StringReader in =
        new StringReader(
            "\"This is a test string for the java.io.StringReader method. It includes various"
                + " characters such as numbers 123, special characters @#$%, and spaces.\"");
    in.skip(1L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).nextLong());
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
    // Arrange
    StringReader in = new StringReader(Boolean.FALSE.toString());

    JsonReader jsonReader = new JsonReader(in);
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
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("FALSE"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.nextLong());
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
  public void testNextLong_givenStringReaderWithFoo_thenThrowMalformedJsonException2()
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
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("\nSee "));
    jsonReader.setStrictness(Strictness.STRICT);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.nextLong());
  }

  /**
   * Test {@link JsonReader#nextLong()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextLong()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JsonReader.nextLong()"})
  public void testNextLong_thenThrowIllegalArgumentException() throws IOException {
    // Arrange
    when(reader.read(Mockito.<char[]>any(), anyInt(), anyInt()))
        .thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> jsonReader.nextLong());
    verify(reader).read(isA(char[].class), eq(0), eq(1024));
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
    // Arrange
    CharArrayReader in = new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray());

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).nextInt());
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
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(1L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).nextInt());
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
  public void testNextInt3() throws IOException {
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(6L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).nextInt());
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
  public void testNextInt4() throws IOException {
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(5L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).nextInt());
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
  public void testNextInt5() throws IOException {
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(6L);

    JsonReader jsonReader = new JsonReader(in);
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(EOFException.class, () -> jsonReader.nextInt());
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
  public void testNextInt6() throws IOException {
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.read("\u0001\u0006\u0001\u0006".toCharArray(), 1, 1);
    in.skip(6L);

    JsonReader jsonReader = new JsonReader(in);
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.nextInt());
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
  public void testNextInt7() throws IOException {
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(59L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).nextInt());
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
  public void testNextInt8() throws IOException {
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(59L);

    JsonReader jsonReader = new JsonReader(in);
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(EOFException.class, () -> jsonReader.nextInt());
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
    // Arrange
    CharArrayReader in = new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray());

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).nextInt());
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
   *   <li>Given {@link StringReader#StringReader(String)} with a string skip one hundred
   *       forty-eight.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextInt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonReader.nextInt()"})
  public void testNextInt_givenStringReaderWithAStringSkipOneHundredFortyEight()
      throws IOException {
    // Arrange
    StringReader in =
        new StringReader(
            "\"This is a test string for the java.io.StringReader method. It includes various"
                + " characters such as numbers 123, special characters @#$%, and spaces.\"");
    in.skip(148L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).nextInt());
  }

  /**
   * Test {@link JsonReader#nextInt()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with a string skip one.
   *   <li>Then throw {@link MalformedJsonException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextInt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonReader.nextInt()"})
  public void testNextInt_givenStringReaderWithAStringSkipOne_thenThrowMalformedJsonException()
      throws IOException {
    // Arrange
    StringReader in =
        new StringReader(
            "\"This is a test string for the java.io.StringReader method. It includes various"
                + " characters such as numbers 123, special characters @#$%, and spaces.\"");
    in.skip(1L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).nextInt());
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
    // Arrange
    StringReader in = new StringReader(Boolean.FALSE.toString());

    JsonReader jsonReader = new JsonReader(in);
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
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("FALSE"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonReader.nextInt());
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
  public void testNextInt_givenStringReaderWithFoo_thenThrowMalformedJsonException2()
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
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("\nSee "));
    jsonReader.setStrictness(Strictness.STRICT);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.nextInt());
  }

  /**
   * Test {@link JsonReader#nextInt()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#nextInt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonReader.nextInt()"})
  public void testNextInt_thenThrowIllegalArgumentException() throws IOException {
    // Arrange
    when(reader.read(Mockito.<char[]>any(), anyInt(), anyInt()))
        .thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> jsonReader.nextInt());
    verify(reader).read(isA(char[].class), eq(0), eq(1024));
  }

  /**
   * Test {@link JsonReader#close()}.
   *
   * <ul>
   *   <li>Given {@link Reader} {@link Reader#close()} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#close()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.close()"})
  public void testClose_givenReaderCloseDoesNothing() throws IOException {
    // Arrange
    doNothing().when(reader).close();

    // Act
    jsonReader.close();

    // Assert
    verify(reader).close();
  }

  /**
   * Test {@link JsonReader#close()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#close()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.close()"})
  public void testClose_thenThrowIllegalArgumentException() throws IOException {
    // Arrange
    doThrow(new IllegalArgumentException()).when(reader).close();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> jsonReader.close());
    verify(reader).close();
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
    // Arrange
    CharArrayReader in = new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray());

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).skipValue());
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
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(1L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).skipValue());
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
  public void testSkipValue3() throws IOException {
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(6L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).skipValue());
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
  public void testSkipValue4() throws IOException {
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(5L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).skipValue());
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
  public void testSkipValue5() throws IOException {
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.read("\u0001\u0006\u0001\u0006".toCharArray(), 1, 3);
    in.skip(6L);

    JsonReader jsonReader = new JsonReader(in);
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    jsonReader.skipValue();
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
  public void testSkipValue6() throws IOException {
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.read("\u0001\u0006\u0001\u0006".toCharArray(), 1, 1);
    in.skip(6L);

    JsonReader jsonReader = new JsonReader(in);
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.skipValue());
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
  public void testSkipValue7() throws IOException {
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(59L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).skipValue());
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
  public void testSkipValue8() throws IOException {
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(59L);

    JsonReader jsonReader = new JsonReader(in);
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(EOFException.class, () -> jsonReader.skipValue());
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
    // Arrange
    CharArrayReader in = new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray());

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).skipValue());
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
   *   <li>Given {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#skipValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.skipValue()"})
  public void testSkipValue_givenStringReaderWith42_thenDoesNotThrow() throws IOException {
    // Arrange, Act and Assert
    new JsonReader(new StringReader("42")).skipValue();
  }

  /**
   * Test {@link JsonReader#skipValue()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with a string skip one.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#skipValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.skipValue()"})
  public void testSkipValue_givenStringReaderWithAStringSkipOne() throws IOException {
    // Arrange
    StringReader in =
        new StringReader(
            "\"This is a test string for the java.io.StringReader method. It includes various"
                + " characters such as numbers 123, special characters @#$%, and spaces.\"");
    in.skip(1L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).skipValue());
  }

  /**
   * Test {@link JsonReader#skipValue()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with a string skip one hundred
   *       forty-eight.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#skipValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.skipValue()"})
  public void testSkipValue_givenStringReaderWithAStringSkipOneHundredFortyEight()
      throws IOException {
    // Arrange
    StringReader in =
        new StringReader(
            "\"This is a test string for the java.io.StringReader method. It includes various"
                + " characters such as numbers 123, special characters @#$%, and spaces.\"");
    in.skip(148L);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> new JsonReader(in).skipValue());
  }

  /**
   * Test {@link JsonReader#skipValue()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with a string.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#skipValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.skipValue()"})
  public void testSkipValue_givenStringReaderWithAString_thenDoesNotThrow() throws IOException {
    // Arrange, Act and Assert
    new JsonReader(
            new StringReader(
                "\"This is a test string for the java.io.StringReader method. It includes various"
                    + " characters such as numbers 123, special characters @#$%, and spaces.\""))
        .skipValue();
  }

  /**
   * Test {@link JsonReader#skipValue()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with a string.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#skipValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.skipValue()"})
  public void testSkipValue_givenStringReaderWithAString_thenDoesNotThrow2() throws IOException {
    // Arrange
    JsonReader jsonReader =
        new JsonReader(
            new StringReader(
                "\"This is a test string for the java.io.StringReader method. It includes various"
                    + " characters such as numbers 123, special characters @#$%, and spaces.\""));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    jsonReader.skipValue();
  }

  /**
   * Test {@link JsonReader#skipValue()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code at line}.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#skipValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.skipValue()"})
  public void testSkipValue_givenStringReaderWithAtLine_thenDoesNotThrow() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader(" at line "));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    jsonReader.skipValue();
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
   *   <li>Given {@link StringReader#StringReader(String)} with {@code End of input} skip eleven.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#skipValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.skipValue()"})
  public void testSkipValue_givenStringReaderWithEndOfInputSkipEleven_thenDoesNotThrow()
      throws IOException {
    // Arrange
    StringReader in = new StringReader("End of input");
    in.skip(11L);

    JsonReader jsonReader = new JsonReader(in);
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    jsonReader.skipValue();
  }

  /**
   * Test {@link JsonReader#skipValue()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code End of input} skip one.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#skipValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.skipValue()"})
  public void testSkipValue_givenStringReaderWithEndOfInputSkipOne_thenDoesNotThrow()
      throws IOException {
    // Arrange
    StringReader in = new StringReader("End of input");
    in.skip(1L);

    JsonReader jsonReader = new JsonReader(in);
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    jsonReader.skipValue();
  }

  /**
   * Test {@link JsonReader#skipValue()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code End of input}.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#skipValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.skipValue()"})
  public void testSkipValue_givenStringReaderWithEndOfInput_thenDoesNotThrow() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("End of input"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    jsonReader.skipValue();
  }

  /**
   * Test {@link JsonReader#skipValue()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#skipValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.skipValue()"})
  public void testSkipValue_givenStringReaderWithFalseToString_thenDoesNotThrow()
      throws IOException {
    // Arrange
    StringReader in = new StringReader(Boolean.FALSE.toString());

    JsonReader jsonReader = new JsonReader(in);
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    jsonReader.skipValue();
  }

  /**
   * Test {@link JsonReader#skipValue()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code FALSE}.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#skipValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.skipValue()"})
  public void testSkipValue_givenStringReaderWithFalse_thenDoesNotThrow() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("FALSE"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    jsonReader.skipValue();
  }

  /**
   * Test {@link JsonReader#skipValue()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#skipValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.skipValue()"})
  public void testSkipValue_givenStringReaderWithFoo_thenDoesNotThrow() throws IOException {
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("foo"));
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    jsonReader.skipValue();
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
    // Arrange
    JsonReader jsonReader = new JsonReader(new StringReader("\nSee "));
    jsonReader.setStrictness(Strictness.STRICT);

    // Act and Assert
    assertThrows(MalformedJsonException.class, () -> jsonReader.skipValue());
  }

  /**
   * Test {@link JsonReader#skipValue()}.
   *
   * <ul>
   *   <li>Then throw {@link EOFException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#skipValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.skipValue()"})
  public void testSkipValue_thenThrowEOFException() throws IOException {
    // Arrange
    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(6L);

    JsonReader jsonReader = new JsonReader(in);
    jsonReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(EOFException.class, () -> jsonReader.skipValue());
  }

  /**
   * Test {@link JsonReader#skipValue()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#skipValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonReader.skipValue()"})
  public void testSkipValue_thenThrowIllegalArgumentException() throws IOException {
    // Arrange
    when(reader.read(Mockito.<char[]>any(), anyInt(), anyInt()))
        .thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> jsonReader.skipValue());
    verify(reader).read(isA(char[].class), eq(0), eq(1024));
  }

  /**
   * Test {@link JsonReader#locationString()}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is
   *       createJsonArrayWithElements.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#locationString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.locationString()"})
  public void testLocationString_givenJsonTreeReaderWithElementIsCreateJsonArrayWithElements() {
    // Arrange, Act and Assert
    assertEquals(
        " at line 1 column 1 path $",
        ((JsonReader)
                new JsonTreeReader(JsonArrayDiffblueTestFactory.createJsonArrayWithElements()))
            .locationString());
  }

  /**
   * Test {@link JsonReader#locationString()}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with a string.
   * </ul>
   *
   * <p>Method under test: {@link JsonReader#locationString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonReader.locationString()"})
  public void testLocationString_givenStringReaderWithAString() {
    // Arrange, Act and Assert
    assertEquals(
        " at line 1 column 1 path $",
        new JsonReader(
                new StringReader(
                    "\"This is a test string for the java.io.StringReader method. It includes"
                        + " various characters such as numbers 123, special characters @#$%, and"
                        + " spaces.\""))
            .locationString());
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
    assertEquals(
        "$",
        new JsonReader(
                new StringReader(
                    "\"This is a test string for the java.io.StringReader method. It includes"
                        + " various characters such as numbers 123, special characters @#$%, and"
                        + " spaces.\""))
            .getPath());
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
    assertEquals(
        "$",
        new JsonReader(
                new StringReader(
                    "\"This is a test string for the java.io.StringReader method. It includes"
                        + " various characters such as numbers 123, special characters @#$%, and"
                        + " spaces.\""))
            .getPreviousPath());
  }
}
