package com.google.gson.internal.bind;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.common.primitives.UnsignedInteger;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import com.google.gson.Strictness;
import com.google.gson.ToNumberPolicy;
import com.google.gson.ToNumberStrategy;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

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
   * <p>Method under test: {@link NumberTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number NumberTypeAdapter.read(JsonReader)"})
  public void testRead() throws IOException {
    // Arrange
    ToNumberStrategy toNumberStrategy = mock(ToNumberStrategy.class);
    when(toNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new JsonSyntaxException(Boolean.FALSE.toString()));
    NumberTypeAdapter numberTypeAdapter = new NumberTypeAdapter(toNumberStrategy);

    JsonReader in = new JsonReader(new StringReader("foo"));
    in.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> numberTypeAdapter.read(in));
    verify(toNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link NumberTypeAdapter#read(JsonReader)}.
   *
   * <p>Method under test: {@link NumberTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number NumberTypeAdapter.read(JsonReader)"})
  public void testRead2() throws IOException {
    // Arrange
    ToNumberStrategy toNumberStrategy = mock(ToNumberStrategy.class);
    when(toNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new JsonSyntaxException(Boolean.FALSE.toString()));
    NumberTypeAdapter numberTypeAdapter = new NumberTypeAdapter(toNumberStrategy);
    CharArrayReader in = new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray());

    JsonReader in2 = new JsonReader(in);
    in2.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> numberTypeAdapter.read(in2));
    verify(toNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link NumberTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
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
  public void testRead_givenLenient_whenStringReaderWithFalse_thenThrowJsonSyntaxException()
      throws IOException {
    // Arrange
    NumberTypeAdapter numberTypeAdapter = new NumberTypeAdapter(mock(ToNumberStrategy.class));

    JsonReader in = new JsonReader(new StringReader("FALSE"));
    in.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> numberTypeAdapter.read(in));
  }

  /**
   * Test {@link NumberTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@link ToNumberStrategy} {@link ToNumberStrategy#readNumber(JsonReader)} return
   *       valueOf one.
   *   <li>Then return intValue is one.
   * </ul>
   *
   * <p>Method under test: {@link NumberTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number NumberTypeAdapter.read(JsonReader)"})
  public void testRead_givenToNumberStrategyReadNumberReturnValueOfOne_thenReturnIntValueIsOne()
      throws IOException {
    // Arrange
    ToNumberStrategy toNumberStrategy = mock(ToNumberStrategy.class);
    Integer valueOfResult = Integer.valueOf(1);
    when(toNumberStrategy.readNumber(Mockito.<JsonReader>any())).thenReturn(valueOfResult);
    NumberTypeAdapter numberTypeAdapter = new NumberTypeAdapter(toNumberStrategy);

    JsonReader in = new JsonReader(new StringReader("foo"));
    in.setStrictness(Strictness.LENIENT);

    // Act
    Number actualReadResult = numberTypeAdapter.read(in);

    // Assert
    verify(toNumberStrategy).readNumber(isA(JsonReader.class));
    assertEquals(1, actualReadResult.intValue());
    assertSame(valueOfResult, actualReadResult);
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
    NumberTypeAdapter numberTypeAdapter = new NumberTypeAdapter(mock(ToNumberStrategy.class));
    JsonTreeReader in = new JsonTreeReader(JsonNull.INSTANCE);

    // Act
    Number actualReadResult = numberTypeAdapter.read(in);

    // Assert
    assertNull(actualReadResult);
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link NumberTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then return intValue is one.
   * </ul>
   *
   * <p>Method under test: {@link NumberTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number NumberTypeAdapter.read(JsonReader)"})
  public void testRead_whenStringReaderWith42_thenReturnIntValueIsOne() throws IOException {
    // Arrange
    ToNumberStrategy toNumberStrategy = mock(ToNumberStrategy.class);
    Integer valueOfResult = Integer.valueOf(1);
    when(toNumberStrategy.readNumber(Mockito.<JsonReader>any())).thenReturn(valueOfResult);
    NumberTypeAdapter numberTypeAdapter = new NumberTypeAdapter(toNumberStrategy);

    JsonReader in = new JsonReader(new StringReader("42"));
    in.setStrictness(Strictness.LENIENT);

    // Act
    Number actualReadResult = numberTypeAdapter.read(in);

    // Assert
    verify(toNumberStrategy).readNumber(isA(JsonReader.class));
    assertEquals(1, actualReadResult.intValue());
    assertSame(valueOfResult, actualReadResult);
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
    NumberTypeAdapter numberTypeAdapter = new NumberTypeAdapter(mock(ToNumberStrategy.class));
    StringReader in = new StringReader(Boolean.FALSE.toString());

    JsonReader in2 = new JsonReader(in);
    in2.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> numberTypeAdapter.read(in2));
  }

  /**
   * Test {@link NumberTypeAdapter#write(JsonWriter, Number)} with {@code JsonWriter}, {@code
   * Number}.
   *
   * <p>Method under test: {@link NumberTypeAdapter#write(JsonWriter, Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NumberTypeAdapter.write(JsonWriter, Number)"})
  public void testWriteWithJsonWriterNumber() throws IOException {
    // Arrange
    NumberTypeAdapter numberTypeAdapter = new NumberTypeAdapter(mock(ToNumberStrategy.class));
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    numberTypeAdapter.write(out, (short) 291);

    // Assert
    assertEquals("291", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link NumberTypeAdapter#write(JsonWriter, Number)} with {@code JsonWriter}, {@code
   * Number}.
   *
   * <p>Method under test: {@link NumberTypeAdapter#write(JsonWriter, Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NumberTypeAdapter.write(JsonWriter, Number)"})
  public void testWriteWithJsonWriterNumber2() throws IOException {
    // Arrange
    NumberTypeAdapter numberTypeAdapter = new NumberTypeAdapter(mock(ToNumberStrategy.class));
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    numberTypeAdapter.write(out, 10.0d);

    // Assert
    assertEquals("10.0", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link NumberTypeAdapter#write(JsonWriter, Number)} with {@code JsonWriter}, {@code
   * Number}.
   *
   * <p>Method under test: {@link NumberTypeAdapter#write(JsonWriter, Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NumberTypeAdapter.write(JsonWriter, Number)"})
  public void testWriteWithJsonWriterNumber3() throws IOException {
    // Arrange
    NumberTypeAdapter numberTypeAdapter = new NumberTypeAdapter(mock(ToNumberStrategy.class));
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    numberTypeAdapter.write(out, 10.0f);

    // Assert
    assertEquals("10.0", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link NumberTypeAdapter#write(JsonWriter, Number)} with {@code JsonWriter}, {@code
   * Number}.
   *
   * <p>Method under test: {@link NumberTypeAdapter#write(JsonWriter, Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NumberTypeAdapter.write(JsonWriter, Number)"})
  public void testWriteWithJsonWriterNumber4() throws IOException {
    // Arrange
    NumberTypeAdapter numberTypeAdapter = new NumberTypeAdapter(mock(ToNumberStrategy.class));
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    numberTypeAdapter.write(out, null);

    // Assert
    assertEquals("null", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link NumberTypeAdapter#write(JsonWriter, Number)} with {@code JsonWriter}, {@code
   * Number}.
   *
   * <p>Method under test: {@link NumberTypeAdapter#write(JsonWriter, Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NumberTypeAdapter.write(JsonWriter, Number)"})
  public void testWriteWithJsonWriterNumber5() throws IOException {
    // Arrange
    NumberTypeAdapter numberTypeAdapter = new NumberTypeAdapter(mock(ToNumberStrategy.class));

    JsonWriter out = new JsonWriter(new StringWriter());
    out.setStrictness(Strictness.LENIENT);

    // Act
    numberTypeAdapter.write(out, Double.NaN);

    // Assert
    assertEquals("NaN", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link NumberTypeAdapter#write(JsonWriter, Number)} with {@code JsonWriter}, {@code
   * Number}.
   *
   * <ul>
   *   <li>Then {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()} Out toString is {@code 1}.
   * </ul>
   *
   * <p>Method under test: {@link NumberTypeAdapter#write(JsonWriter, Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NumberTypeAdapter.write(JsonWriter, Number)"})
  public void testWriteWithJsonWriterNumber_thenJsonWriterWithOutIsStringWriterOutToStringIs1()
      throws IOException {
    // Arrange
    NumberTypeAdapter numberTypeAdapter = new NumberTypeAdapter(mock(ToNumberStrategy.class));
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    numberTypeAdapter.write(out, Integer.valueOf(1));

    // Assert
    assertEquals("1", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link NumberTypeAdapter#write(JsonWriter, Number)} with {@code JsonWriter}, {@code
   * Number}.
   *
   * <ul>
   *   <li>Then {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()} Out toString is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link NumberTypeAdapter#write(JsonWriter, Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NumberTypeAdapter.write(JsonWriter, Number)"})
  public void testWriteWithJsonWriterNumber_thenJsonWriterWithOutIsStringWriterOutToStringIs42()
      throws IOException {
    // Arrange
    NumberTypeAdapter numberTypeAdapter = new NumberTypeAdapter(mock(ToNumberStrategy.class));
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    numberTypeAdapter.write(out, 42L);

    // Assert
    assertEquals("42", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link NumberTypeAdapter#write(JsonWriter, Number)} with {@code JsonWriter}, {@code
   * Number}.
   *
   * <ul>
   *   <li>Then {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()} Out toString is {@code 65}.
   * </ul>
   *
   * <p>Method under test: {@link NumberTypeAdapter#write(JsonWriter, Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NumberTypeAdapter.write(JsonWriter, Number)"})
  public void testWriteWithJsonWriterNumber_thenJsonWriterWithOutIsStringWriterOutToStringIs65()
      throws IOException {
    // Arrange
    NumberTypeAdapter numberTypeAdapter = new NumberTypeAdapter(mock(ToNumberStrategy.class));
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    numberTypeAdapter.write(out, (byte) 'A');

    // Assert
    assertEquals("65", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link NumberTypeAdapter#write(JsonWriter, Number)} with {@code JsonWriter}, {@code
   * Number}.
   *
   * <ul>
   *   <li>When fromIntBits one.
   * </ul>
   *
   * <p>Method under test: {@link NumberTypeAdapter#write(JsonWriter, Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NumberTypeAdapter.write(JsonWriter, Number)"})
  public void testWriteWithJsonWriterNumber_whenFromIntBitsOne() throws IOException {
    // Arrange
    NumberTypeAdapter numberTypeAdapter = new NumberTypeAdapter(mock(ToNumberStrategy.class));
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    numberTypeAdapter.write(out, UnsignedInteger.fromIntBits(1));

    // Assert
    assertEquals("1", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
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
    NumberTypeAdapter numberTypeAdapter = new NumberTypeAdapter(mock(ToNumberStrategy.class));

    JsonTreeWriter out = new JsonTreeWriter();
    out.setStrictness(Strictness.LENIENT);
    Integer value = Integer.valueOf(1);

    // Act
    numberTypeAdapter.write(out, value);

    // Assert
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonPrimitive);
    assertEquals("1", getResult.getAsString());
    assertEquals('1', getResult.getAsCharacter());
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
    assertSame(value, getResult.getAsInt());
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
    NumberTypeAdapter numberTypeAdapter = new NumberTypeAdapter(mock(ToNumberStrategy.class));
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    numberTypeAdapter.write(out, null);

    // Assert that nothing has changed
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonNull);
    assertFalse(getResult.isJsonPrimitive());
    assertTrue(getResult.isJsonNull());
    assertArrayEquals(
        new int[] {
          6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
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
    NumberTypeAdapter numberTypeAdapter = new NumberTypeAdapter(mock(ToNumberStrategy.class));
    JsonTreeWriter out = new JsonTreeWriter();
    Integer value = Integer.valueOf(1);

    // Act
    numberTypeAdapter.write(out, value);

    // Assert
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonPrimitive);
    assertEquals("1", getResult.getAsString());
    assertEquals('1', getResult.getAsCharacter());
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
    assertSame(value, getResult.getAsInt());
    assertSame(value, getResult.getAsNumber());
    assertSame(getResult, getResult.getAsJsonPrimitive());
  }
}
