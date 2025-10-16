package com.google.gson.internal.bind;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.JsonArray;
import com.google.gson.JsonArrayTestFactory;
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
import java.util.Iterator;
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
   *   <li>Then return createJsonArrayWithOneElement.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_thenReturnCreateJsonArrayWithOneElement() throws IOException {
    // Arrange
    JsonArray element = JsonArrayTestFactory.createJsonArrayWithOneElement();

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
    JsonArray value = JsonArrayTestFactory.createJsonArrayWithOneElement();

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, value);

    // Assert that nothing has changed
    Iterator<JsonElement> iteratorResult = value.iterator();
    JsonElement nextResult = iteratorResult.next();
    assertTrue(nextResult instanceof JsonPrimitive);
    Number asNumber = value.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    Number asNumber2 = nextResult.getAsNumber();
    assertTrue(asNumber2 instanceof LazilyParsedNumber);
    assertFalse(iteratorResult.hasNext());
    assertEquals(asNumber, asNumber2);
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
    JsonTreeWriter out = new JsonTreeWriter();
    JsonArray value = JsonArrayTestFactory.createJsonArrayWithOneElement();

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, value);

    // Assert
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonArray);
    Iterator<JsonElement> iteratorResult = value.iterator();
    JsonElement nextResult = iteratorResult.next();
    assertTrue(nextResult instanceof JsonPrimitive);
    assertTrue(value.getAsNumber() instanceof LazilyParsedNumber);
    assertTrue(nextResult.getAsNumber() instanceof LazilyParsedNumber);
    assertFalse(iteratorResult.hasNext());
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
  public void testWriteWithJsonWriterJsonElement3() throws IOException {
    // Arrange
    JsonTreeWriter out = new JsonTreeWriter();
    JsonArray value = JsonArrayTestFactory.createJsonArrayWithNumbers();

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
  public void testWriteWithJsonWriterJsonElement4() throws IOException {
    // Arrange
    JsonTreeWriter out = new JsonTreeWriter();
    JsonArray value = JsonArrayTestFactory.createJsonArrayWithMixedTypes();

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
   * <ul>
   *   <li>Given {@code LENIENT}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement_givenLenient() throws IOException {
    // Arrange
    JsonTreeWriter out = new JsonTreeWriter();
    out.setStrictness(Strictness.LENIENT);
    JsonArray value = JsonArrayTestFactory.createJsonArrayWithNumbers();

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
   * <ul>
   *   <li>Given {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement_givenTrue() throws IOException {
    // Arrange
    JsonWriter out = new JsonWriter(new StringWriter());
    out.setHtmlSafe(true);
    JsonArray value = JsonArrayTestFactory.createJsonArrayWithOneElement();

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, value);

    // Assert that nothing has changed
    Iterator<JsonElement> iteratorResult = value.iterator();
    JsonElement nextResult = iteratorResult.next();
    assertTrue(nextResult instanceof JsonPrimitive);
    Number asNumber = value.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    Number asNumber2 = nextResult.getAsNumber();
    assertTrue(asNumber2 instanceof LazilyParsedNumber);
    assertFalse(iteratorResult.hasNext());
    assertEquals(asNumber, asNumber2);
  }

  /**
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <ul>
   *   <li>When {@link JsonTreeWriter} (default constructor).
   *   <li>Then {@link JsonTreeWriter} (default constructor) {@link JsonNull}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement_whenJsonTreeWriter_thenJsonTreeWriterJsonNull()
      throws IOException {
    // Arrange
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, null);

    // Assert that nothing has changed
    assertTrue(out.get() instanceof JsonNull);
  }
}
