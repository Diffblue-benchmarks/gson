package com.google.gson.internal.bind;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.Gson;
import com.google.gson.JsonArrayDiffblueTestFactory;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import com.google.gson.Strictness;
import com.google.gson.ToNumberPolicy;
import com.google.gson.ToNumberStrategy;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class NumberTypeAdapterDiffblueTest {
  /**
   * Test {@link NumberTypeAdapter#getFactory(ToNumberStrategy)}.
   *
   * <ul>
   *   <li>When {@link ToNumberPolicy#LAZILY_PARSED_NUMBER}.
   *   <li>Then return create {@link Gson#Gson()} and {@link Object} is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link NumberTypeAdapter#getFactory(ToNumberStrategy)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapterFactory NumberTypeAdapter.getFactory(ToNumberStrategy)"})
  public void testGetFactory_whenLazily_parsed_number_thenReturnCreateGsonAndObjectIsNull() {
    // Arrange and Act
    TypeAdapterFactory actualFactory =
        NumberTypeAdapter.getFactory(ToNumberPolicy.LAZILY_PARSED_NUMBER);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type);

    // Assert
    assertNull(actualFactory.create(gson, getResult));
  }

  /**
   * Test {@link NumberTypeAdapter#getFactory(ToNumberStrategy)}.
   *
   * <ul>
   *   <li>When {@link ToNumberStrategy}.
   *   <li>Then return create {@link Gson#Gson()} and {@link Object} is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link NumberTypeAdapter#getFactory(ToNumberStrategy)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapterFactory NumberTypeAdapter.getFactory(ToNumberStrategy)"})
  public void testGetFactory_whenToNumberStrategy_thenReturnCreateGsonAndObjectIsNull() {
    // Arrange and Act
    TypeAdapterFactory actualFactory = NumberTypeAdapter.getFactory(mock(ToNumberStrategy.class));
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type);

    // Assert
    assertNull(actualFactory.create(gson, getResult));
  }

  /**
   * Test {@link NumberTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given createDoubleAdapter.
   *   <li>Then return doubleValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link NumberTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number NumberTypeAdapter.read(JsonReader)"})
  public void testRead_givenCreateDoubleAdapter_thenReturnDoubleValueIsFortyTwo()
      throws IOException {
    // Arrange
    NumberTypeAdapter createDoubleAdapterResult =
        NumberTypeAdapterDiffblueTestFactory.createDoubleAdapter();

    // Act and Assert
    assertEquals(
        42.0d,
        createDoubleAdapterResult.read(new JsonReader(new StringReader("42"))).doubleValue(),
        0.0);
  }

  /**
   * Test {@link NumberTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given three.
   *   <li>Then return toString is {@code JsonReader.setStrictness(Strictness.LENIENT)}.
   * </ul>
   *
   * <p>Method under test: {@link NumberTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number NumberTypeAdapter.read(JsonReader)"})
  public void testRead_givenThree_thenReturnToStringIsJsonReaderSetStrictnessStrictnessLenient()
      throws IOException {
    // Arrange
    NumberTypeAdapter createLazilyParsedNumberAdapterResult =
        NumberTypeAdapterDiffblueTestFactory.createLazilyParsedNumberAdapter();

    StringReader in =
        new StringReader(
            "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed JSON");
    in.read("\u0001\u0006\u0001\u0006".toCharArray(), 1, 3);

    JsonReader in2 = new JsonReader(in);
    in2.setStrictness(Strictness.LENIENT);

    // Act
    Number actualReadResult = createLazilyParsedNumberAdapterResult.read(in2);

    // Assert
    assertTrue(actualReadResult instanceof LazilyParsedNumber);
    assertEquals("JsonReader.setStrictness(Strictness.LENIENT)", actualReadResult.toString());
  }

  /**
   * Test {@link NumberTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given three.
   *   <li>When {@link StringReader#StringReader(String)} with {@code ; at path}.
   *   <li>Then return toString is {@code t}.
   * </ul>
   *
   * <p>Method under test: {@link NumberTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number NumberTypeAdapter.read(JsonReader)"})
  public void testRead_givenThree_whenStringReaderWithAtPath_thenReturnToStringIsT()
      throws IOException {
    // Arrange
    NumberTypeAdapter createLazilyParsedNumberAdapterResult =
        NumberTypeAdapterDiffblueTestFactory.createLazilyParsedNumberAdapter();

    StringReader in = new StringReader("; at path ");
    in.read("\u0001\u0006\u0001\u0006".toCharArray(), 1, 3);

    JsonReader in2 = new JsonReader(in);
    in2.setStrictness(Strictness.LENIENT);

    // Act
    Number actualReadResult = createLazilyParsedNumberAdapterResult.read(in2);

    // Assert
    assertTrue(actualReadResult instanceof LazilyParsedNumber);
    assertEquals("t", actualReadResult.toString());
  }

  /**
   * Test {@link NumberTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given three.
   *   <li>When {@link StringReader#StringReader(String)} with {@code Cannot parse}.
   *   <li>Then return toString is {@code not}.
   * </ul>
   *
   * <p>Method under test: {@link NumberTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number NumberTypeAdapter.read(JsonReader)"})
  public void testRead_givenThree_whenStringReaderWithCannotParse_thenReturnToStringIsNot()
      throws IOException {
    // Arrange
    NumberTypeAdapter createLazilyParsedNumberAdapterResult =
        NumberTypeAdapterDiffblueTestFactory.createLazilyParsedNumberAdapter();

    StringReader in = new StringReader("Cannot parse ");
    in.read("\u0001\u0006\u0001\u0006".toCharArray(), 1, 3);

    JsonReader in2 = new JsonReader(in);
    in2.setStrictness(Strictness.LENIENT);

    // Act
    Number actualReadResult = createLazilyParsedNumberAdapterResult.read(in2);

    // Assert
    assertTrue(actualReadResult instanceof LazilyParsedNumber);
    assertEquals("not", actualReadResult.toString());
  }

  /**
   * Test {@link NumberTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given two.
   *   <li>Then return toString is {@code e}.
   * </ul>
   *
   * <p>Method under test: {@link NumberTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number NumberTypeAdapter.read(JsonReader)"})
  public void testRead_givenTwo_thenReturnToStringIsE() throws IOException {
    // Arrange
    NumberTypeAdapter createLazilyParsedNumberAdapterResult =
        NumberTypeAdapterDiffblueTestFactory.createLazilyParsedNumberAdapter();

    StringReader in =
        new StringReader(
            "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed JSON");
    in.read("\u0001\u0006\u0001\u0006".toCharArray(), 1, 2);

    JsonReader in2 = new JsonReader(in);
    in2.setStrictness(Strictness.LENIENT);

    // Act
    Number actualReadResult = createLazilyParsedNumberAdapterResult.read(in2);

    // Assert
    assertTrue(actualReadResult instanceof LazilyParsedNumber);
    assertEquals("e", actualReadResult.toString());
  }

  /**
   * Test {@link NumberTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Then return toString is acknowledge start of heading acknowledge.
   * </ul>
   *
   * <p>Method under test: {@link NumberTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number NumberTypeAdapter.read(JsonReader)"})
  public void testRead_thenReturnToStringIsAcknowledgeStartOfHeadingAcknowledge()
      throws IOException {
    // Arrange
    NumberTypeAdapter createLazilyParsedNumberAdapterResult =
        NumberTypeAdapterDiffblueTestFactory.createLazilyParsedNumberAdapter();
    CharArrayReader in = new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray());

    JsonReader in2 = new JsonReader(in);
    in2.setStrictness(Strictness.LENIENT);

    // Act
    Number actualReadResult = createLazilyParsedNumberAdapterResult.read(in2);

    // Assert
    assertTrue(actualReadResult instanceof LazilyParsedNumber);
    assertEquals("\u0006\u0001\u0006", actualReadResult.toString());
  }

  /**
   * Test {@link NumberTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Then return toString is start of heading acknowledge start of heading acknowledge.
   * </ul>
   *
   * <p>Method under test: {@link NumberTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number NumberTypeAdapter.read(JsonReader)"})
  public void testRead_thenReturnToStringIsStartOfHeadingAcknowledgeStartOfHeadingAcknowledge()
      throws IOException {
    // Arrange
    NumberTypeAdapter createLazilyParsedNumberAdapterResult =
        NumberTypeAdapterDiffblueTestFactory.createLazilyParsedNumberAdapter();
    CharArrayReader in = new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray());

    JsonReader in2 = new JsonReader(in);
    in2.setStrictness(Strictness.LENIENT);

    // Act
    Number actualReadResult = createLazilyParsedNumberAdapterResult.read(in2);

    // Assert
    assertTrue(actualReadResult instanceof LazilyParsedNumber);
    assertEquals("\u0001\u0006\u0001\u0006", actualReadResult.toString());
  }

  /**
   * Test {@link NumberTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Then return toString is {@code Use}.
   * </ul>
   *
   * <p>Method under test: {@link NumberTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number NumberTypeAdapter.read(JsonReader)"})
  public void testRead_thenReturnToStringIsUse() throws IOException {
    // Arrange
    NumberTypeAdapter createLazilyParsedNumberAdapterResult =
        NumberTypeAdapterDiffblueTestFactory.createLazilyParsedNumberAdapter();

    JsonReader in =
        new JsonReader(
            new StringReader(
                "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed JSON"));
    in.setStrictness(Strictness.LENIENT);

    // Act
    Number actualReadResult = createLazilyParsedNumberAdapterResult.read(in);

    // Assert
    assertTrue(actualReadResult instanceof LazilyParsedNumber);
    assertEquals("Use", actualReadResult.toString());
  }

  /**
   * Test {@link NumberTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is
   *       createJsonArrayWithElements.
   * </ul>
   *
   * <p>Method under test: {@link NumberTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number NumberTypeAdapter.read(JsonReader)"})
  public void testRead_whenJsonTreeReaderWithElementIsCreateJsonArrayWithElements()
      throws IOException {
    // Arrange
    NumberTypeAdapter createLongOrDoubleAdapterResult =
        NumberTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class,
        () ->
            createLongOrDoubleAdapterResult.read(
                new JsonTreeReader(JsonArrayDiffblueTestFactory.createJsonArrayWithElements())));
  }

  /**
   * Test {@link NumberTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link NumberTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number NumberTypeAdapter.read(JsonReader)"})
  public void testRead_whenJsonTreeReaderWithElementIsInstance_thenReturnNull() throws IOException {
    // Arrange
    NumberTypeAdapter createLongOrDoubleAdapterResult =
        NumberTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();
    JsonTreeReader in = new JsonTreeReader(JsonNull.INSTANCE);

    // Act
    Number actualReadResult = createLongOrDoubleAdapterResult.read(in);

    // Assert
    assertNull(actualReadResult);
    assertEquals(0, in.getStackSize());
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link NumberTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then return longValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link NumberTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number NumberTypeAdapter.read(JsonReader)"})
  public void testRead_whenStringReaderWith42_thenReturnLongValueIsFortyTwo() throws IOException {
    // Arrange
    NumberTypeAdapter createLongOrDoubleAdapterResult =
        NumberTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();

    // Act and Assert
    assertEquals(
        42L,
        createLongOrDoubleAdapterResult.read(new JsonReader(new StringReader("42"))).longValue());
  }

  /**
   * Test {@link NumberTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link NumberTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number NumberTypeAdapter.read(JsonReader)"})
  public void testRead_whenStringReaderWithFalseToString_thenThrowJsonSyntaxException()
      throws IOException {
    // Arrange
    NumberTypeAdapter createLongOrDoubleAdapterResult =
        NumberTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();
    StringReader in = new StringReader(Boolean.FALSE.toString());

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class, () -> createLongOrDoubleAdapterResult.read(new JsonReader(in)));
  }

  /**
   * Test {@link NumberTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link NumberTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number NumberTypeAdapter.read(JsonReader)"})
  public void testRead_whenStringReaderWithFalseToString_thenThrowJsonSyntaxException2()
      throws IOException {
    // Arrange
    NumberTypeAdapter createLongOrDoubleAdapterResult =
        NumberTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();
    StringReader in = new StringReader(Boolean.FALSE.toString());

    JsonReader in2 = new JsonReader(in);
    in2.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> createLongOrDoubleAdapterResult.read(in2));
  }

  /**
   * Test {@link NumberTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code FALSE}.
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link NumberTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number NumberTypeAdapter.read(JsonReader)"})
  public void testRead_whenStringReaderWithFalse_thenThrowJsonSyntaxException() throws IOException {
    // Arrange
    NumberTypeAdapter createLongOrDoubleAdapterResult =
        NumberTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class,
        () -> createLongOrDoubleAdapterResult.read(new JsonReader(new StringReader("FALSE"))));
  }

  /**
   * Test {@link NumberTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then return toString is {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link NumberTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number NumberTypeAdapter.read(JsonReader)"})
  public void testRead_whenStringReaderWithFoo_thenReturnToStringIsFoo() throws IOException {
    // Arrange
    NumberTypeAdapter createLazilyParsedNumberAdapterResult =
        NumberTypeAdapterDiffblueTestFactory.createLazilyParsedNumberAdapter();

    JsonReader in = new JsonReader(new StringReader("foo"));
    in.setStrictness(Strictness.LENIENT);

    // Act
    Number actualReadResult = createLazilyParsedNumberAdapterResult.read(in);

    // Assert
    assertTrue(actualReadResult instanceof LazilyParsedNumber);
    assertEquals("foo", actualReadResult.toString());
  }

  /**
   * Test {@link NumberTypeAdapter#write(JsonWriter, Number)} with {@code JsonWriter}, {@code
   * Number}.
   *
   * <ul>
   *   <li>When {@link JsonTreeWriter} (default constructor) Strictness is {@code LENIENT}.
   * </ul>
   *
   * <p>Method under test: {@link NumberTypeAdapter#write(JsonWriter, Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NumberTypeAdapter.write(JsonWriter, Number)"})
  public void testWriteWithJsonWriterNumber_whenJsonTreeWriterStrictnessIsLenient()
      throws IOException {
    // Arrange
    NumberTypeAdapter createLongOrDoubleAdapterResult =
        NumberTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();

    JsonTreeWriter out = new JsonTreeWriter();
    out.setStrictness(Strictness.LENIENT);
    Integer value = Integer.valueOf(1);

    // Act
    createLongOrDoubleAdapterResult.write(out, value);

    // Assert
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonPrimitive);
    assertEquals("1", getResult.getAsString());
    assertEquals('1', getResult.getAsCharacter());
    assertEquals(1, getResult.getAsInt());
    assertEquals(1.0d, getResult.getAsDouble(), 0.0);
    assertEquals(1.0f, getResult.getAsFloat(), 0.0f);
    assertEquals(1L, getResult.getAsLong());
    assertEquals((byte) 1, getResult.getAsByte());
    assertEquals((short) 1, getResult.getAsShort());
    assertFalse(getResult.getAsBoolean());
    assertFalse(getResult.isJsonNull());
    assertFalse(((JsonPrimitive) getResult).isBoolean());
    assertFalse(((JsonPrimitive) getResult).isString());
    assertTrue(getResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) getResult).isNumber());
    assertEquals(new BigDecimal("1"), getResult.getAsBigDecimal());
    assertSame(value, getResult.getAsNumber());
    assertSame(getResult, getResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link NumberTypeAdapter#write(JsonWriter, Number)} with {@code JsonWriter}, {@code
   * Number}.
   *
   * <ul>
   *   <li>When {@link JsonTreeWriter} (default constructor).
   *   <li>Then {@link JsonTreeWriter} (default constructor) {@link JsonNull}.
   * </ul>
   *
   * <p>Method under test: {@link NumberTypeAdapter#write(JsonWriter, Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NumberTypeAdapter.write(JsonWriter, Number)"})
  public void testWriteWithJsonWriterNumber_whenJsonTreeWriter_thenJsonTreeWriterJsonNull()
      throws IOException {
    // Arrange
    NumberTypeAdapter createLongOrDoubleAdapterResult =
        NumberTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    createLongOrDoubleAdapterResult.write(out, null);

    // Assert that nothing has changed
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonNull);
    assertFalse(getResult.isJsonPrimitive());
    assertTrue(getResult.isJsonNull());
  }

  /**
   * Test {@link NumberTypeAdapter#write(JsonWriter, Number)} with {@code JsonWriter}, {@code
   * Number}.
   *
   * <ul>
   *   <li>When {@link JsonTreeWriter} (default constructor).
   *   <li>Then {@link JsonTreeWriter} (default constructor) {@link JsonPrimitive}.
   * </ul>
   *
   * <p>Method under test: {@link NumberTypeAdapter#write(JsonWriter, Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NumberTypeAdapter.write(JsonWriter, Number)"})
  public void testWriteWithJsonWriterNumber_whenJsonTreeWriter_thenJsonTreeWriterJsonPrimitive()
      throws IOException {
    // Arrange
    NumberTypeAdapter createLongOrDoubleAdapterResult =
        NumberTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();
    JsonTreeWriter out = new JsonTreeWriter();
    Integer value = Integer.valueOf(1);

    // Act
    createLongOrDoubleAdapterResult.write(out, value);

    // Assert
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonPrimitive);
    assertEquals("1", getResult.getAsString());
    assertEquals('1', getResult.getAsCharacter());
    assertEquals(1, getResult.getAsInt());
    assertEquals(1.0d, getResult.getAsDouble(), 0.0);
    assertEquals(1.0f, getResult.getAsFloat(), 0.0f);
    assertEquals(1L, getResult.getAsLong());
    assertEquals((byte) 1, getResult.getAsByte());
    assertEquals((short) 1, getResult.getAsShort());
    assertFalse(getResult.getAsBoolean());
    assertFalse(getResult.isJsonNull());
    assertFalse(((JsonPrimitive) getResult).isBoolean());
    assertFalse(((JsonPrimitive) getResult).isString());
    assertTrue(getResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) getResult).isNumber());
    assertEquals(new BigDecimal("1"), getResult.getAsBigDecimal());
    assertSame(value, getResult.getAsNumber());
    assertSame(getResult, getResult.getAsJsonPrimitive());
  }
}
