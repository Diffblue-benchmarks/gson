package com.google.gson.internal.bind;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import org.mockito.Mockito;

public class JsonElementTypeAdapterDiffblueTest {
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
   *   <li>Then return AsString is {@code nd}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_thenReturnAsStringIsNd() throws IOException {
    // Arrange
    StringReader in = new StringReader("End of input");
    in.read("\u0001\u0006\u0001\u0006".toCharArray(), 1, 1);

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
    JsonWriter out = mock(JsonWriter.class);
    when(out.beginArray()).thenThrow(new IllegalStateException());

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> JsonElementTypeAdapter.ADAPTER.write(out, new JsonArray(3)));
    verify(out).beginArray();
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
    JsonWriter out = mock(JsonWriter.class);

    JsonNull value = mock(JsonNull.class);
    when(value.isJsonNull()).thenThrow(new IllegalStateException());

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> JsonElementTypeAdapter.ADAPTER.write(out, value));
    verify(value).isJsonNull();
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
    JsonWriter out = mock(JsonWriter.class);
    when(out.beginArray()).thenReturn(new JsonWriter(new StringWriter()));

    JsonNull value = mock(JsonNull.class);
    when(value.getAsJsonArray()).thenThrow(new IllegalStateException());
    when(value.isJsonArray()).thenReturn(true);
    when(value.isJsonNull()).thenReturn(false);
    when(value.isJsonPrimitive()).thenReturn(false);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> JsonElementTypeAdapter.ADAPTER.write(out, value));
    verify(value).getAsJsonArray();
    verify(value).isJsonArray();
    verify(value).isJsonNull();
    verify(value).isJsonPrimitive();
    verify(out).beginArray();
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
    JsonWriter out = mock(JsonWriter.class);
    when(out.value(Mockito.<Number>any())).thenReturn(new JsonWriter(new StringWriter()));
    when(out.beginArray()).thenReturn(new JsonWriter(new StringWriter()));
    when(out.endArray()).thenReturn(new JsonWriter(new StringWriter()));

    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(Integer.valueOf(3));

    JsonNull value = mock(JsonNull.class);
    when(value.getAsJsonArray()).thenReturn(jsonArray);
    when(value.isJsonArray()).thenReturn(true);
    when(value.isJsonNull()).thenReturn(false);
    when(value.isJsonPrimitive()).thenReturn(false);

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, value);

    // Assert
    verify(value).getAsJsonArray();
    verify(value).isJsonArray();
    verify(value).isJsonNull();
    verify(value).isJsonPrimitive();
    verify(out).beginArray();
    verify(out).endArray();
    verify(out).value(isA(Number.class));
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
    JsonWriter out = mock(JsonWriter.class);

    JsonNull value = mock(JsonNull.class);
    when(value.getAsJsonPrimitive()).thenThrow(new IllegalStateException());
    when(value.isJsonNull()).thenReturn(false);
    when(value.isJsonPrimitive()).thenReturn(true);

    JsonArray jsonArray = mock(JsonArray.class);
    doNothing().when(jsonArray).add(Mockito.<Number>any());
    jsonArray.add(Integer.valueOf(3));

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> JsonElementTypeAdapter.ADAPTER.write(out, value));
    verify(jsonArray).add(isA(Number.class));
    verify(value).getAsJsonPrimitive();
    verify(value).isJsonNull();
    verify(value).isJsonPrimitive();
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
    JsonWriter out = mock(JsonWriter.class);

    JsonPrimitive jsonPrimitive = mock(JsonPrimitive.class);
    when(jsonPrimitive.getAsNumber()).thenThrow(new IllegalStateException());
    when(jsonPrimitive.isNumber()).thenReturn(true);

    JsonNull value = mock(JsonNull.class);
    when(value.getAsJsonPrimitive()).thenReturn(jsonPrimitive);
    when(value.isJsonNull()).thenReturn(false);
    when(value.isJsonPrimitive()).thenReturn(true);

    JsonArray jsonArray = mock(JsonArray.class);
    doNothing().when(jsonArray).add(Mockito.<Number>any());
    jsonArray.add(Integer.valueOf(3));

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> JsonElementTypeAdapter.ADAPTER.write(out, value));
    verify(jsonArray).add(isA(Number.class));
    verify(value).getAsJsonPrimitive();
    verify(value).isJsonNull();
    verify(value).isJsonPrimitive();
    verify(jsonPrimitive).getAsNumber();
    verify(jsonPrimitive).isNumber();
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
    JsonWriter out = mock(JsonWriter.class);

    JsonPrimitive jsonPrimitive = mock(JsonPrimitive.class);
    when(jsonPrimitive.getAsBoolean()).thenThrow(new IllegalStateException());
    when(jsonPrimitive.isBoolean()).thenReturn(true);
    when(jsonPrimitive.isNumber()).thenReturn(false);

    JsonNull value = mock(JsonNull.class);
    when(value.getAsJsonPrimitive()).thenReturn(jsonPrimitive);
    when(value.isJsonNull()).thenReturn(false);
    when(value.isJsonPrimitive()).thenReturn(true);

    JsonArray jsonArray = mock(JsonArray.class);
    doNothing().when(jsonArray).add(Mockito.<Number>any());
    jsonArray.add(Integer.valueOf(3));

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> JsonElementTypeAdapter.ADAPTER.write(out, value));
    verify(jsonArray).add(isA(Number.class));
    verify(value).getAsJsonPrimitive();
    verify(value).isJsonNull();
    verify(value).isJsonPrimitive();
    verify(jsonPrimitive).getAsBoolean();
    verify(jsonPrimitive).isBoolean();
    verify(jsonPrimitive).isNumber();
  }

  /**
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement_givenJsonArrayWithCapacityIsThree()
      throws IOException {
    // Arrange
    JsonWriter out = mock(JsonWriter.class);
    when(out.beginArray()).thenReturn(new JsonWriter(new StringWriter()));
    when(out.endArray()).thenReturn(new JsonWriter(new StringWriter()));

    JsonNull value = mock(JsonNull.class);
    when(value.getAsJsonArray()).thenReturn(new JsonArray(3));
    when(value.isJsonArray()).thenReturn(true);
    when(value.isJsonNull()).thenReturn(false);
    when(value.isJsonPrimitive()).thenReturn(false);

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, value);

    // Assert
    verify(value).getAsJsonArray();
    verify(value).isJsonArray();
    verify(value).isJsonNull();
    verify(value).isJsonPrimitive();
    verify(out).beginArray();
    verify(out).endArray();
  }

  /**
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement_givenJsonArrayWithCapacityIsThreeAddNull()
      throws IOException {
    // Arrange
    JsonWriter out = mock(JsonWriter.class);
    when(out.beginArray()).thenReturn(new JsonWriter(new StringWriter()));
    when(out.endArray()).thenReturn(new JsonWriter(new StringWriter()));
    when(out.nullValue()).thenReturn(new JsonWriter(new StringWriter()));

    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add((Boolean) null);

    JsonNull value = mock(JsonNull.class);
    when(value.getAsJsonArray()).thenReturn(jsonArray);
    when(value.isJsonArray()).thenReturn(true);
    when(value.isJsonNull()).thenReturn(false);
    when(value.isJsonPrimitive()).thenReturn(false);

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, value);

    // Assert
    verify(value).getAsJsonArray();
    verify(value).isJsonArray();
    verify(value).isJsonNull();
    verify(value).isJsonPrimitive();
    verify(out).beginArray();
    verify(out).endArray();
    verify(out).nullValue();
  }

  /**
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive} {@link JsonPrimitive#getAsBoolean()} return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement_givenJsonPrimitiveGetAsBooleanReturnFalse()
      throws IOException {
    // Arrange
    JsonWriter out = mock(JsonWriter.class);
    when(out.value(anyBoolean())).thenReturn(new JsonWriter(new StringWriter()));

    JsonPrimitive jsonPrimitive = mock(JsonPrimitive.class);
    when(jsonPrimitive.getAsBoolean()).thenReturn(false);
    when(jsonPrimitive.isBoolean()).thenReturn(true);
    when(jsonPrimitive.isNumber()).thenReturn(false);

    JsonNull value = mock(JsonNull.class);
    when(value.getAsJsonPrimitive()).thenReturn(jsonPrimitive);
    when(value.isJsonNull()).thenReturn(false);
    when(value.isJsonPrimitive()).thenReturn(true);

    JsonArray jsonArray = mock(JsonArray.class);
    doNothing().when(jsonArray).add(Mockito.<Number>any());
    jsonArray.add(Integer.valueOf(3));

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, value);

    // Assert
    verify(jsonArray).add(isA(Number.class));
    verify(value).getAsJsonPrimitive();
    verify(value).isJsonNull();
    verify(value).isJsonPrimitive();
    verify(jsonPrimitive).getAsBoolean();
    verify(jsonPrimitive).isBoolean();
    verify(jsonPrimitive).isNumber();
    verify(out).value(false);
  }

  /**
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive} {@link JsonPrimitive#getAsNumber()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement_givenJsonPrimitiveGetAsNumberReturnNull()
      throws IOException {
    // Arrange
    JsonWriter out = mock(JsonWriter.class);
    when(out.value(Mockito.<Number>any())).thenReturn(new JsonWriter(new StringWriter()));

    JsonPrimitive jsonPrimitive = mock(JsonPrimitive.class);
    when(jsonPrimitive.getAsNumber()).thenReturn(null);
    when(jsonPrimitive.isNumber()).thenReturn(true);

    JsonNull value = mock(JsonNull.class);
    when(value.getAsJsonPrimitive()).thenReturn(jsonPrimitive);
    when(value.isJsonNull()).thenReturn(false);
    when(value.isJsonPrimitive()).thenReturn(true);

    JsonArray jsonArray = mock(JsonArray.class);
    doNothing().when(jsonArray).add(Mockito.<Number>any());
    jsonArray.add(Integer.valueOf(3));

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, value);

    // Assert
    verify(jsonArray).add(isA(Number.class));
    verify(value).getAsJsonPrimitive();
    verify(value).isJsonNull();
    verify(value).isJsonPrimitive();
    verify(jsonPrimitive).getAsNumber();
    verify(jsonPrimitive).isNumber();
    verify(out).value((Number) isNull());
  }

  /**
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement_givenJsonPrimitiveWithBoolIsTrue()
      throws IOException {
    // Arrange
    JsonWriter out = mock(JsonWriter.class);
    when(out.value(anyBoolean())).thenReturn(new JsonWriter(new StringWriter()));

    JsonNull value = mock(JsonNull.class);
    when(value.getAsJsonPrimitive()).thenReturn(new JsonPrimitive(true));
    when(value.isJsonNull()).thenReturn(false);
    when(value.isJsonPrimitive()).thenReturn(true);

    JsonArray jsonArray = mock(JsonArray.class);
    doNothing().when(jsonArray).add(Mockito.<Number>any());
    jsonArray.add(Integer.valueOf(3));

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, value);

    // Assert
    verify(jsonArray).add(isA(Number.class));
    verify(value).getAsJsonPrimitive();
    verify(value).isJsonNull();
    verify(value).isJsonPrimitive();
    verify(out).value(true);
  }

  /**
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement_thenThrowIllegalArgumentException()
      throws IOException {
    // Arrange
    JsonWriter out = mock(JsonWriter.class);

    JsonNull value = mock(JsonNull.class);
    when(value.isJsonArray()).thenReturn(false);
    when(value.isJsonNull()).thenReturn(false);
    when(value.isJsonObject()).thenReturn(false);
    when(value.isJsonPrimitive()).thenReturn(false);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> JsonElementTypeAdapter.ADAPTER.write(out, value));
    verify(value).isJsonArray();
    verify(value).isJsonNull();
    verify(value).isJsonObject();
    verify(value).isJsonPrimitive();
  }

  /**
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <ul>
   *   <li>When {@link JsonNull#INSTANCE}.
   *   <li>Then calls {@link JsonWriter#nullValue()}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement_whenInstance_thenCallsNullValue()
      throws IOException {
    // Arrange
    JsonWriter out = mock(JsonWriter.class);
    when(out.nullValue()).thenReturn(new JsonWriter(new StringWriter()));

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, JsonNull.INSTANCE);

    // Assert
    verify(out).nullValue();
  }

  /**
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <ul>
   *   <li>When {@link JsonArray#JsonArray(int)} with capacity is three.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement_whenJsonArrayWithCapacityIsThree()
      throws IOException {
    // Arrange
    JsonWriter out = mock(JsonWriter.class);
    when(out.beginArray()).thenReturn(new JsonWriter(new StringWriter()));
    when(out.endArray()).thenReturn(new JsonWriter(new StringWriter()));

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, new JsonArray(3));

    // Assert
    verify(out).beginArray();
    verify(out).endArray();
  }

  /**
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <ul>
   *   <li>When {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement_whenJsonPrimitiveWithBoolIsTrue()
      throws IOException {
    // Arrange
    JsonWriter out = mock(JsonWriter.class);
    when(out.value(anyBoolean())).thenReturn(new JsonWriter(new StringWriter()));

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, new JsonPrimitive(true));

    // Assert
    verify(out).value(true);
  }

  /**
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <ul>
   *   <li>When {@link JsonPrimitive#JsonPrimitive(String)} with {@code String}.
   *   <li>Then calls {@link JsonWriter#value(String)}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement_whenJsonPrimitiveWithString_thenCallsValue()
      throws IOException {
    // Arrange
    JsonWriter out = mock(JsonWriter.class);
    when(out.value(Mockito.<String>any())).thenReturn(new JsonWriter(new StringWriter()));

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, new JsonPrimitive("String"));

    // Assert
    verify(out).value("String");
  }

  /**
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <ul>
   *   <li>When {@link JsonWriter} {@link JsonWriter#value(String)} throw {@link
   *       IllegalStateException#IllegalStateException()}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement_whenJsonWriterValueThrowIllegalStateException()
      throws IOException {
    // Arrange
    JsonWriter out = mock(JsonWriter.class);
    when(out.value(Mockito.<String>any())).thenThrow(new IllegalStateException());

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> JsonElementTypeAdapter.ADAPTER.write(out, new JsonPrimitive("String")));
    verify(out).value("String");
  }

  /**
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <ul>
   *   <li>When {@link JsonWriter} {@link JsonWriter#value(boolean)} throw {@link
   *       IllegalStateException#IllegalStateException()}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement_whenJsonWriterValueThrowIllegalStateException2()
      throws IOException {
    // Arrange
    JsonWriter out = mock(JsonWriter.class);
    when(out.value(anyBoolean())).thenThrow(new IllegalStateException());

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> JsonElementTypeAdapter.ADAPTER.write(out, new JsonPrimitive(true)));
    verify(out).value(true);
  }

  /**
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <ul>
   *   <li>When {@link JsonWriter} {@link JsonWriter#value(Number)} throw {@link
   *       IllegalStateException#IllegalStateException()}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement_whenJsonWriterValueThrowIllegalStateException3()
      throws IOException {
    // Arrange
    JsonWriter out = mock(JsonWriter.class);
    when(out.value(Mockito.<Number>any())).thenThrow(new IllegalStateException());
    when(out.beginArray()).thenReturn(new JsonWriter(new StringWriter()));

    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(Integer.valueOf(3));

    JsonNull value = mock(JsonNull.class);
    when(value.getAsJsonArray()).thenReturn(jsonArray);
    when(value.isJsonArray()).thenReturn(true);
    when(value.isJsonNull()).thenReturn(false);
    when(value.isJsonPrimitive()).thenReturn(false);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> JsonElementTypeAdapter.ADAPTER.write(out, value));
    verify(value).getAsJsonArray();
    verify(value).isJsonArray();
    verify(value).isJsonNull();
    verify(value).isJsonPrimitive();
    verify(out).beginArray();
    verify(out).value(isA(Number.class));
  }

  /**
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then calls {@link JsonWriter#nullValue()}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement_whenNull_thenCallsNullValue() throws IOException {
    // Arrange
    JsonWriter out = mock(JsonWriter.class);
    when(out.nullValue()).thenReturn(new JsonWriter(new StringWriter()));

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, null);

    // Assert
    verify(out).nullValue();
  }
}
