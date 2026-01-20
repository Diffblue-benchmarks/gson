package com.google.gson.internal.bind;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.Strictness;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JsonElementTypeAdapterDiffblueTest {
  /**
   * Test {@link JsonElementTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given eleven.
   *   <li>Then return AsString is {@code t}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_givenEleven_thenReturnAsStringIsT() throws IOException {
    // Arrange
    StringReader in = new StringReader("End of input");
    in.skip(11L);

    JsonReader in2 = new JsonReader(in);
    in2.setStrictness(Strictness.LENIENT);

    // Act
    JsonElement actualReadResult = JsonElementTypeAdapter.ADAPTER.read(in2);

    // Assert
    assertTrue(actualReadResult instanceof JsonPrimitive);
    Number asNumber = actualReadResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("t", actualReadResult.getAsString());
    assertEquals("t", asNumber.toString());
    assertEquals('t', actualReadResult.getAsCharacter());
    JsonPrimitive actualAsJsonPrimitive = actualReadResult.getAsJsonPrimitive();
    assertSame(actualReadResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonElementTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code at line}.
   *   <li>Then return AsString is {@code at}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_whenStringReaderWithAtLine_thenReturnAsStringIsAt()
      throws IOException {
    // Arrange
    JsonReader in = new JsonReader(new StringReader(" at line "));
    in.setStrictness(Strictness.LENIENT);

    // Act
    JsonElement actualReadResult = JsonElementTypeAdapter.ADAPTER.read(in);

    // Assert
    assertTrue(actualReadResult instanceof JsonPrimitive);
    Number asNumber = actualReadResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("at", actualReadResult.getAsString());
    assertEquals("at", asNumber.toString());
    assertEquals('a', actualReadResult.getAsCharacter());
    JsonPrimitive actualAsJsonPrimitive = actualReadResult.getAsJsonPrimitive();
    assertSame(actualReadResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonElementTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code End of input}.
   *   <li>Then return AsString is {@code End}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_whenStringReaderWithEndOfInput_thenReturnAsStringIsEnd()
      throws IOException {
    // Arrange
    JsonReader in = new JsonReader(new StringReader("End of input"));
    in.setStrictness(Strictness.LENIENT);

    // Act
    JsonElement actualReadResult = JsonElementTypeAdapter.ADAPTER.read(in);

    // Assert
    assertTrue(actualReadResult instanceof JsonPrimitive);
    Number asNumber = actualReadResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("End", actualReadResult.getAsString());
    assertEquals("End", asNumber.toString());
    assertEquals('E', actualReadResult.getAsCharacter());
    JsonPrimitive actualAsJsonPrimitive = actualReadResult.getAsJsonPrimitive();
    assertSame(actualReadResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonElementTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then return not String.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_whenStringReaderWithFalseToString_thenReturnNotString()
      throws IOException {
    // Arrange
    StringReader in = new StringReader(Boolean.FALSE.toString());

    JsonReader in2 = new JsonReader(in);
    in2.setStrictness(Strictness.LENIENT);

    // Act
    JsonElement actualReadResult = JsonElementTypeAdapter.ADAPTER.read(in2);

    // Assert
    assertTrue(actualReadResult instanceof JsonPrimitive);
    assertFalse(((JsonPrimitive) actualReadResult).isString());
    assertTrue(((JsonPrimitive) actualReadResult).isBoolean());
    assertEquals(Boolean.FALSE.toString(), actualReadResult.getAsString());
    JsonPrimitive actualAsJsonPrimitive = actualReadResult.getAsJsonPrimitive();
    assertSame(actualReadResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonElementTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then return AsString is {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_whenStringReaderWithFoo_thenReturnAsStringIsFoo()
      throws IOException {
    // Arrange
    JsonReader in = new JsonReader(new StringReader("foo"));
    in.setStrictness(Strictness.LENIENT);

    // Act
    JsonElement actualReadResult = JsonElementTypeAdapter.ADAPTER.read(in);

    // Assert
    assertTrue(actualReadResult instanceof JsonPrimitive);
    Number asNumber = actualReadResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("foo", actualReadResult.getAsString());
    assertEquals("foo", asNumber.toString());
    assertEquals('f', actualReadResult.getAsCharacter());
    JsonPrimitive actualAsJsonPrimitive = actualReadResult.getAsJsonPrimitive();
    assertSame(actualReadResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonElementTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code See}.
   *   <li>Then return AsString is {@code See}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_whenStringReaderWithSee_thenReturnAsStringIsSee()
      throws IOException {
    // Arrange
    JsonReader in = new JsonReader(new StringReader("\nSee "));
    in.setStrictness(Strictness.LENIENT);

    // Act
    JsonElement actualReadResult = JsonElementTypeAdapter.ADAPTER.read(in);

    // Assert
    assertTrue(actualReadResult instanceof JsonPrimitive);
    Number asNumber = actualReadResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("See", actualReadResult.getAsString());
    assertEquals("See", asNumber.toString());
    assertEquals('S', actualReadResult.getAsCharacter());
    JsonPrimitive actualAsJsonPrimitive = actualReadResult.getAsJsonPrimitive();
    assertSame(actualReadResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonElementTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given one.
   *   <li>When {@link StringReader#StringReader(String)} with {@code End of input} skip one.
   *   <li>Then return AsString is {@code nd}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_givenOne_whenStringReaderWithEndOfInputSkipOne_thenReturnAsStringIsNd()
      throws IOException {
    // Arrange
    StringReader in = new StringReader("End of input");
    in.skip(1L);

    JsonReader in2 = new JsonReader(in);
    in2.setStrictness(Strictness.LENIENT);

    // Act
    JsonElement actualReadResult = JsonElementTypeAdapter.ADAPTER.read(in2);

    // Assert
    assertTrue(actualReadResult instanceof JsonPrimitive);
    Number asNumber = actualReadResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("nd", actualReadResult.getAsString());
    assertEquals("nd", asNumber.toString());
    assertEquals('n', actualReadResult.getAsCharacter());
    JsonPrimitive actualAsJsonPrimitive = actualReadResult.getAsJsonPrimitive();
    assertSame(actualReadResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonElementTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code STRICT}.
   *   <li>When {@link JsonReader#JsonReader(Reader)} with in is {@link
   *       StringReader#StringReader(String)} Strictness is {@code STRICT}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_givenStrict_whenJsonReaderWithInIsStringReaderStrictnessIsStrict()
      throws IOException {
    // Arrange
    StringReader in = new StringReader(Boolean.FALSE.toString());

    JsonReader in2 = new JsonReader(in);
    in2.setStrictness(Strictness.STRICT);

    // Act
    JsonElement actualReadResult = JsonElementTypeAdapter.ADAPTER.read(in2);

    // Assert
    assertTrue(actualReadResult instanceof JsonPrimitive);
    assertFalse(((JsonPrimitive) actualReadResult).isString());
    assertTrue(((JsonPrimitive) actualReadResult).isBoolean());
    assertEquals(Boolean.FALSE.toString(), actualReadResult.getAsString());
    JsonPrimitive actualAsJsonPrimitive = actualReadResult.getAsJsonPrimitive();
    assertSame(actualReadResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonElementTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Then return AsString is {@code Use}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_thenReturnAsStringIsUse() throws IOException {
    // Arrange
    JsonReader in =
        new JsonReader(
            new StringReader(
                "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed JSON"));
    in.setStrictness(Strictness.LENIENT);

    // Act
    JsonElement actualReadResult = JsonElementTypeAdapter.ADAPTER.read(in);

    // Assert
    assertTrue(actualReadResult instanceof JsonPrimitive);
    Number asNumber = actualReadResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("Use", actualReadResult.getAsString());
    assertEquals("Use", asNumber.toString());
    assertEquals('U', actualReadResult.getAsCharacter());
    JsonPrimitive actualAsJsonPrimitive = actualReadResult.getAsJsonPrimitive();
    assertSame(actualReadResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonElementTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonArray#JsonArray(int)} with capacity is three.
   *   <li>Then return {@link JsonArray#JsonArray(int)} with capacity is three.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_whenJsonArrayWithCapacityIsThree_thenReturnJsonArrayWithCapacityIsThree()
      throws IOException {
    // Arrange
    JsonArray element = new JsonArray(3);

    // Act
    JsonElement actualReadResult = JsonElementTypeAdapter.ADAPTER.read(new JsonTreeReader(element));

    // Assert
    assertSame(element, actualReadResult);
  }

  /**
   * Test {@link JsonElementTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.
   *   <li>Then return {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_whenJsonPrimitiveWithBoolIsTrue_thenReturnJsonPrimitiveWithBoolIsTrue()
      throws IOException {
    // Arrange
    JsonPrimitive element = new JsonPrimitive(true);

    // Act
    JsonElement actualReadResult = JsonElementTypeAdapter.ADAPTER.read(new JsonTreeReader(element));

    // Assert
    assertSame(element, actualReadResult);
  }

  /**
   * Test {@link JsonElementTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonPrimitive#JsonPrimitive(String)} with {@code String}.
   *   <li>Then return {@link JsonPrimitive#JsonPrimitive(String)} with {@code String}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_whenJsonPrimitiveWithString_thenReturnJsonPrimitiveWithString()
      throws IOException {
    // Arrange
    JsonPrimitive element = new JsonPrimitive("String");

    // Act
    JsonElement actualReadResult = JsonElementTypeAdapter.ADAPTER.read(new JsonTreeReader(element));

    // Assert
    assertSame(element, actualReadResult);
  }

  /**
   * Test {@link JsonElementTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE}.
   *   <li>Then return {@link JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_whenJsonTreeReaderWithElementIsInstance_thenReturnInstance()
      throws IOException {
    // Arrange and Act
    JsonElement actualReadResult =
        JsonElementTypeAdapter.ADAPTER.read(new JsonTreeReader(JsonNull.INSTANCE));

    // Assert
    assertSame(((JsonNull) actualReadResult).INSTANCE, actualReadResult);
  }

  /**
   * Test {@link JsonElementTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonObject} (default constructor).
   *   <li>Then return {@link JsonObject} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_whenJsonTreeReaderWithElementIsJsonObject_thenReturnJsonObject()
      throws IOException {
    // Arrange
    JsonObject element = new JsonObject();

    // Act
    JsonElement actualReadResult = JsonElementTypeAdapter.ADAPTER.read(new JsonTreeReader(element));

    // Assert
    assertSame(element, actualReadResult);
  }

  /**
   * Test {@link JsonElementTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then return AsString is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_whenStringReaderWith42_thenReturnAsStringIs42() throws IOException {
    // Arrange and Act
    JsonElement actualReadResult =
        JsonElementTypeAdapter.ADAPTER.read(new JsonReader(new StringReader("42")));

    // Assert
    assertTrue(actualReadResult instanceof JsonPrimitive);
    assertEquals("42", actualReadResult.getAsString());
    assertEquals('4', actualReadResult.getAsCharacter());
    assertEquals(42, actualReadResult.getAsInt());
    assertEquals(42.0d, actualReadResult.getAsDouble(), 0.0);
    assertEquals(42.0f, actualReadResult.getAsFloat(), 0.0f);
    assertEquals(42L, actualReadResult.getAsLong());
    assertEquals((short) 42, actualReadResult.getAsShort());
    assertTrue(((JsonPrimitive) actualReadResult).isNumber());
    assertEquals(new BigDecimal("42"), actualReadResult.getAsBigDecimal());
    assertEquals('*', actualReadResult.getAsByte());
    JsonPrimitive actualAsJsonPrimitive = actualReadResult.getAsJsonPrimitive();
    assertSame(actualReadResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonElementTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code false at line}.
   *   <li>Then return not String.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_whenStringReaderWithFalseAtLine_thenReturnNotString() throws IOException {
    // Arrange and Act
    JsonElement actualReadResult =
        JsonElementTypeAdapter.ADAPTER.read(new JsonReader(new StringReader("false at line ")));

    // Assert
    assertTrue(actualReadResult instanceof JsonPrimitive);
    assertFalse(((JsonPrimitive) actualReadResult).isString());
    assertTrue(((JsonPrimitive) actualReadResult).isBoolean());
    assertEquals(Boolean.FALSE.toString(), actualReadResult.getAsString());
    JsonPrimitive actualAsJsonPrimitive = actualReadResult.getAsJsonPrimitive();
    assertSame(actualReadResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonElementTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then return not String.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_whenStringReaderWithFalseToString_thenReturnNotString() throws IOException {
    // Arrange
    StringReader in = new StringReader(Boolean.FALSE.toString());

    // Act
    JsonElement actualReadResult = JsonElementTypeAdapter.ADAPTER.read(new JsonReader(in));

    // Assert
    assertTrue(actualReadResult instanceof JsonPrimitive);
    assertFalse(((JsonPrimitive) actualReadResult).isString());
    assertTrue(((JsonPrimitive) actualReadResult).isBoolean());
    assertEquals(Boolean.FALSE.toString(), actualReadResult.getAsString());
    JsonPrimitive actualAsJsonPrimitive = actualReadResult.getAsJsonPrimitive();
    assertSame(actualReadResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonElementTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code FALSE}.
   *   <li>Then return not String.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_whenStringReaderWithFalse_thenReturnNotString() throws IOException {
    // Arrange and Act
    JsonElement actualReadResult =
        JsonElementTypeAdapter.ADAPTER.read(new JsonReader(new StringReader("FALSE")));

    // Assert
    assertTrue(actualReadResult instanceof JsonPrimitive);
    assertFalse(((JsonPrimitive) actualReadResult).isString());
    assertTrue(((JsonPrimitive) actualReadResult).isBoolean());
    assertEquals(Boolean.FALSE.toString(), actualReadResult.getAsString());
    JsonPrimitive actualAsJsonPrimitive = actualReadResult.getAsJsonPrimitive();
    assertSame(actualReadResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonElementTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code falsefalse}.
   *   <li>Then return AsString is {@code falsefalse}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_whenStringReaderWithFalsefalse_thenReturnAsStringIsFalsefalse()
      throws IOException {
    // Arrange
    JsonReader in = new JsonReader(new StringReader("falsefalse"));
    in.setStrictness(Strictness.LENIENT);

    // Act
    JsonElement actualReadResult = JsonElementTypeAdapter.ADAPTER.read(in);

    // Assert
    assertTrue(actualReadResult instanceof JsonPrimitive);
    Number asNumber = actualReadResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("falsefalse", actualReadResult.getAsString());
    assertEquals("falsefalse", asNumber.toString());
    assertEquals('f', actualReadResult.getAsCharacter());
    JsonPrimitive actualAsJsonPrimitive = actualReadResult.getAsJsonPrimitive();
    assertSame(actualReadResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement() throws IOException {
    // Arrange
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, JsonNull.INSTANCE);

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
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement2() throws IOException {
    // Arrange
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, null);

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
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement3() throws IOException {
    // Arrange
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, new JsonArray(3));

    // Assert
    assertEquals("[]", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement4() throws IOException {
    // Arrange
    JsonWriter out = new JsonWriter(new StringWriter());

    JsonArray value = new JsonArray(3);
    value.add((Boolean) null);

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, value);

    // Assert
    assertEquals("[null]", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement5() throws IOException {
    // Arrange
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, new JsonPrimitive(Boolean.TRUE.toString()));

    // Assert
    assertEquals("\"true\"", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement6() throws IOException {
    // Arrange
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, new JsonObject());

    // Assert
    assertEquals("{}", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement7() throws IOException {
    // Arrange
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, new JsonPrimitive(true));

    // Assert
    assertEquals(Boolean.TRUE.toString(), out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement8() throws IOException {
    // Arrange
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, new JsonPrimitive('\u0003'));

    // Assert
    assertEquals("\"\\u0003\"", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement9() throws IOException {
    // Arrange
    JsonWriter out = new JsonWriter(new StringWriter());

    JsonArray value = new JsonArray(3);
    value.add(Integer.valueOf(3));

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, value);

    // Assert
    assertEquals("[3]", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement10() throws IOException {
    // Arrange
    JsonWriter out = new JsonWriter(new StringWriter());

    JsonArray value = new JsonArray(3);
    value.add(false);
    value.add(Integer.valueOf(3));

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, value);

    // Assert
    assertEquals("[false,3]", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement11() throws IOException {
    // Arrange
    JsonWriter out = new JsonWriter(new StringWriter());

    JsonObject value = new JsonObject();
    value.add("42", JsonNull.INSTANCE);

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, value);

    // Assert
    assertEquals("{\"42\":null}", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement12() throws IOException {
    // Arrange
    JsonWriter out = new JsonWriter(new StringWriter());

    JsonObject value = new JsonObject();
    value.add("name == null", JsonNull.INSTANCE);
    value.add("42", JsonNull.INSTANCE);

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, value);

    // Assert
    assertEquals("{\"name == null\":null,\"42\":null}", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement13() throws IOException {
    // Arrange
    JsonWriter out = new JsonWriter(new StringWriter());
    out.setHtmlSafe(true);

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, new JsonPrimitive(Boolean.TRUE.toString()));

    // Assert
    assertEquals("\"true\"", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement14() throws IOException {
    // Arrange
    JsonWriter out = new JsonWriter(new StringWriter());
    out.setHtmlSafe(true);

    JsonObject value = new JsonObject();
    value.add("name == null", JsonNull.INSTANCE);
    value.add("42", JsonNull.INSTANCE);

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, value);

    // Assert
    assertEquals("{\"name \\u003d\\u003d null\":null,\"42\":null}", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement15() throws IOException {
    // Arrange
    JsonTreeWriter out = new JsonTreeWriter();
    JsonArray value = new JsonArray(3);

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, value);

    // Assert
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonArray);
    assertEquals(value, getResult);
  }

  /**
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement16() throws IOException {
    // Arrange
    JsonTreeWriter out = new JsonTreeWriter();
    JsonPrimitive value = new JsonPrimitive(true);

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, value);

    // Assert
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonPrimitive);
    assertEquals(value, getResult);
  }

  /**
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement17() throws IOException {
    // Arrange
    JsonTreeWriter out = new JsonTreeWriter();

    JsonObject value = new JsonObject();
    value.add("com.google.gson.JsonObject", JsonNull.INSTANCE);

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, value);

    // Assert
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonObject);
    assertEquals(value, getResult);
  }

  /**
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement18() throws IOException {
    // Arrange
    JsonTreeWriter out = new JsonTreeWriter();

    JsonObject value = new JsonObject();
    value.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    value.add("Property", JsonNull.INSTANCE);
    value.add("name == null", JsonNull.INSTANCE);
    value.addProperty("com.google.gson.JsonObject", "Value");

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, value);

    // Assert
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonObject);
    assertEquals(value, getResult);
  }

  /**
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement19() throws IOException {
    // Arrange
    JsonTreeWriter out = new JsonTreeWriter();

    JsonObject value = new JsonObject();
    value.add("name == null", JsonNull.INSTANCE);
    value.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    value.add("Property", JsonNull.INSTANCE);
    value.add("name == null", JsonNull.INSTANCE);
    value.addProperty("com.google.gson.JsonObject", "Value");

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, value);

    // Assert
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonObject);
    assertEquals(value, getResult);
  }

  /**
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link JsonObject} (default constructor) add {@code 42} and {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement_given42_whenJsonObjectAdd42AndInstance()
      throws IOException {
    // Arrange
    JsonTreeWriter out = new JsonTreeWriter();

    JsonObject value = new JsonObject();
    value.add("42", JsonNull.INSTANCE);
    value.add("Property", JsonNull.INSTANCE);
    value.add("name == null", JsonNull.INSTANCE);
    value.addProperty("com.google.gson.JsonObject", "Value");

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, value);

    // Assert
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonObject);
    assertEquals(value, getResult);
  }

  /**
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <ul>
   *   <li>Given {@code Property}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement_givenProperty() throws IOException {
    // Arrange
    JsonTreeWriter out = new JsonTreeWriter();

    JsonObject value = new JsonObject();
    value.add("Property", JsonNull.INSTANCE);
    value.add("name == null", JsonNull.INSTANCE);
    value.addProperty("com.google.gson.JsonObject", "Value");

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, value);

    // Assert
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonObject);
    assertEquals(value, getResult);
  }

  /**
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <ul>
   *   <li>Given {@code Property}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement_givenProperty2() throws IOException {
    // Arrange
    JsonTreeWriter out = new JsonTreeWriter();

    JsonObject value = new JsonObject();
    value.add("name == null", JsonNull.INSTANCE);
    value.add("Property", JsonNull.INSTANCE);
    value.add("name == null", JsonNull.INSTANCE);
    value.addProperty("com.google.gson.JsonObject", "Value");

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, value);

    // Assert
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonObject);
    assertEquals(value, getResult);
  }

  /**
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <ul>
   *   <li>Given {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement_givenValue() throws IOException {
    // Arrange
    JsonTreeWriter out = new JsonTreeWriter();

    JsonObject value = new JsonObject();
    value.addProperty("com.google.gson.JsonObject", "Value");

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, value);

    // Assert
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonObject);
    assertEquals(value, getResult);
  }

  /**
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <ul>
   *   <li>Given {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement_givenValue2() throws IOException {
    // Arrange
    JsonTreeWriter out = new JsonTreeWriter();

    JsonObject value = new JsonObject();
    value.add("name == null", JsonNull.INSTANCE);
    value.addProperty("com.google.gson.JsonObject", "Value");

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, value);

    // Assert
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonObject);
    assertEquals(value, getResult);
  }

  /**
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <ul>
   *   <li>Then {@link JsonTreeWriter} (default constructor) is {@link
   *       JsonPrimitive#JsonPrimitive(String)} with {@code String}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement_thenJsonTreeWriterIsJsonPrimitiveWithString()
      throws IOException {
    // Arrange
    JsonTreeWriter out = new JsonTreeWriter();
    JsonPrimitive value = new JsonPrimitive("String");

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, value);

    // Assert
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonPrimitive);
    assertEquals(value, getResult);
  }

  /**
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <ul>
   *   <li>When {@link JsonObject} (default constructor).
   *   <li>Then {@link JsonTreeWriter} (default constructor) {@link JsonObject}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement_whenJsonObject_thenJsonTreeWriterJsonObject()
      throws IOException {
    // Arrange
    JsonTreeWriter out = new JsonTreeWriter();
    JsonObject value = new JsonObject();

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, value);

    // Assert
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonObject);
    assertEquals(value, getResult);
  }

  /**
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@link JsonTreeWriter} (default constructor) {@link JsonNull}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement_whenNull_thenJsonTreeWriterJsonNull()
      throws IOException {
    // Arrange
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, null);

    // Assert that nothing has changed
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonNull);
    assertFalse(getResult.isJsonObject());
    assertArrayEquals(
        new int[] {
          6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }
}
