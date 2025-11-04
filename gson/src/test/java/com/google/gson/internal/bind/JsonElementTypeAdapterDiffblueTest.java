package com.google.gson.internal.bind;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.diffblue.cover.annotations.MaintainedByDiffblue;
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
   *   <li>When {@link StringReader#StringReader(String)} with {@code End of input}.
   *   <li>Then return AsString is {@code End}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
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
    assertSame(actualReadResult, actualReadResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElementTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then return AsCharacter is {@code f}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_whenStringReaderWithFalseToString_thenReturnAsCharacterIsF()
      throws IOException {
    // Arrange
    JsonReader in = new JsonReader(new StringReader(Boolean.FALSE.toString()));
    in.setStrictness(Strictness.LENIENT);

    // Act
    JsonElement actualReadResult = JsonElementTypeAdapter.ADAPTER.read(in);

    // Assert
    assertTrue(actualReadResult instanceof JsonPrimitive);
    assertEquals('f', actualReadResult.getAsCharacter());
    assertTrue(((JsonPrimitive) actualReadResult).isBoolean());
    String expectedAsString = Boolean.FALSE.toString();
    assertEquals(expectedAsString, actualReadResult.getAsString());
    assertSame(actualReadResult, actualReadResult.getAsJsonPrimitive());
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
  @Category(MaintainedByDiffblue.class)
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
    assertTrue(((JsonPrimitive) actualReadResult).isString());
    assertSame(actualReadResult, actualReadResult.getAsJsonPrimitive());
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
  @Category(MaintainedByDiffblue.class)
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
    assertSame(actualReadResult, actualReadResult.getAsJsonPrimitive());
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
  @Category(MaintainedByDiffblue.class)
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
    assertSame(actualReadResult, actualReadResult.getAsJsonPrimitive());
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_whenJsonArrayWithCapacityIsThree_thenReturnJsonArrayWithCapacityIsThree()
      throws IOException {
    // Arrange
    JsonArray element = new JsonArray(3);

    // Act and Assert
    assertSame(element, JsonElementTypeAdapter.ADAPTER.read(new JsonTreeReader(element)));
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_whenJsonPrimitiveWithBoolIsTrue_thenReturnJsonPrimitiveWithBoolIsTrue()
      throws IOException {
    // Arrange
    JsonPrimitive element = new JsonPrimitive(true);

    // Act and Assert
    assertSame(element, JsonElementTypeAdapter.ADAPTER.read(new JsonTreeReader(element)));
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_whenJsonPrimitiveWithString_thenReturnJsonPrimitiveWithString()
      throws IOException {
    // Arrange
    JsonPrimitive element = new JsonPrimitive("String");

    // Act and Assert
    assertSame(element, JsonElementTypeAdapter.ADAPTER.read(new JsonTreeReader(element)));
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
  @Category(MaintainedByDiffblue.class)
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_whenJsonTreeReaderWithElementIsJsonObject_thenReturnJsonObject()
      throws IOException {
    // Arrange
    JsonObject element = new JsonObject();

    // Act and Assert
    assertSame(element, JsonElementTypeAdapter.ADAPTER.read(new JsonTreeReader(element)));
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
  @Category(MaintainedByDiffblue.class)
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
    BigDecimal expectedAsBigDecimal = new BigDecimal("42");
    assertEquals(expectedAsBigDecimal, actualReadResult.getAsBigDecimal());
    assertEquals('*', actualReadResult.getAsByte());
    assertSame(actualReadResult, actualReadResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElementTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code false at line}.
   *   <li>Then return AsCharacter is {@code f}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_whenStringReaderWithFalseAtLine_thenReturnAsCharacterIsF()
      throws IOException {
    // Arrange and Act
    JsonElement actualReadResult =
        JsonElementTypeAdapter.ADAPTER.read(new JsonReader(new StringReader("false at line ")));

    // Assert
    assertTrue(actualReadResult instanceof JsonPrimitive);
    assertEquals('f', actualReadResult.getAsCharacter());
    assertTrue(((JsonPrimitive) actualReadResult).isBoolean());
    String expectedAsString = Boolean.FALSE.toString();
    assertEquals(expectedAsString, actualReadResult.getAsString());
    assertSame(actualReadResult, actualReadResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElementTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then return AsCharacter is {@code f}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_whenStringReaderWithFalseToString_thenReturnAsCharacterIsF()
      throws IOException {
    // Arrange and Act
    JsonElement actualReadResult =
        JsonElementTypeAdapter.ADAPTER.read(
            new JsonReader(new StringReader(Boolean.FALSE.toString())));

    // Assert
    assertTrue(actualReadResult instanceof JsonPrimitive);
    assertEquals('f', actualReadResult.getAsCharacter());
    assertTrue(((JsonPrimitive) actualReadResult).isBoolean());
    String expectedAsString = Boolean.FALSE.toString();
    assertEquals(expectedAsString, actualReadResult.getAsString());
    assertSame(actualReadResult, actualReadResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElementTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code FALSE}.
   *   <li>Then return AsCharacter is {@code f}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_whenStringReaderWithFalse_thenReturnAsCharacterIsF() throws IOException {
    // Arrange and Act
    JsonElement actualReadResult =
        JsonElementTypeAdapter.ADAPTER.read(new JsonReader(new StringReader("FALSE")));

    // Assert
    assertTrue(actualReadResult instanceof JsonPrimitive);
    assertEquals('f', actualReadResult.getAsCharacter());
    assertTrue(((JsonPrimitive) actualReadResult).isBoolean());
    String expectedAsString = Boolean.FALSE.toString();
    assertEquals(expectedAsString, actualReadResult.getAsString());
    assertSame(actualReadResult, actualReadResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <ul>
   *   <li>Given {@code JsonObject}.
   *   <li>Then calls {@link JsonWriter#value(Number)}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement_givenComGoogleGsonJsonObject_thenCallsValue()
      throws IOException {
    // Arrange
    JsonWriter out = mock(JsonWriter.class);
    when(out.value(Mockito.<Number>any())).thenReturn(new JsonWriter(new StringWriter()));
    when(out.name(Mockito.<String>any())).thenReturn(new JsonWriter(new StringWriter()));
    when(out.beginObject()).thenReturn(new JsonWriter(new StringWriter()));
    when(out.endObject()).thenReturn(new JsonWriter(new StringWriter()));
    when(out.nullValue()).thenReturn(new JsonWriter(new StringWriter()));

    JsonObject value = new JsonObject();
    value.addProperty("com.google.gson.JsonObject", Integer.valueOf(3));
    value.add("Property", JsonNull.INSTANCE);

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, value);

    // Assert
    verify(out).beginObject();
    verify(out).endObject();
    verify(out, atLeast(1)).name(Mockito.<String>any());
    verify(out).nullValue();
    verify(out).value(isA(Number.class));
  }

  /**
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <ul>
   *   <li>Given {@link JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement_givenInstance() throws IOException {
    // Arrange
    JsonWriter out = mock(JsonWriter.class);
    when(out.name(Mockito.<String>any())).thenReturn(new JsonWriter(new StringWriter()));
    when(out.beginObject()).thenReturn(new JsonWriter(new StringWriter()));
    when(out.endObject()).thenReturn(new JsonWriter(new StringWriter()));
    when(out.nullValue()).thenReturn(new JsonWriter(new StringWriter()));

    JsonObject value = new JsonObject();
    value.add("Property", JsonNull.INSTANCE);

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, value);

    // Assert
    verify(out).beginObject();
    verify(out).endObject();
    verify(out).name(eq("Property"));
    verify(out).nullValue();
  }

  /**
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <ul>
   *   <li>Then calls {@link JsonWriter#beginArray()}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement_thenCallsBeginArray() throws IOException {
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
   *   <li>Then calls {@link JsonElement#isJsonNull()}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement_thenCallsIsJsonNull() throws IOException {
    // Arrange
    JsonWriter out = mock(JsonWriter.class);
    when(out.value(Mockito.<Number>any())).thenReturn(new JsonWriter(new StringWriter()));
    when(out.name(Mockito.<String>any())).thenReturn(new JsonWriter(new StringWriter()));
    when(out.beginObject()).thenReturn(new JsonWriter(new StringWriter()));
    when(out.endObject()).thenReturn(new JsonWriter(new StringWriter()));
    when(out.nullValue()).thenReturn(new JsonWriter(new StringWriter()));
    JsonElement value = mock(JsonElement.class);
    when(value.isJsonNull()).thenReturn(true);

    JsonObject value2 = new JsonObject();
    value2.addProperty("com.google.gson.JsonObject", Integer.valueOf(3));
    value2.add("Property", value);

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, value2);

    // Assert
    verify(value).isJsonNull();
    verify(out).beginObject();
    verify(out).endObject();
    verify(out, atLeast(1)).name(Mockito.<String>any());
    verify(out).nullValue();
    verify(out).value(isA(Number.class));
  }

  /**
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <ul>
   *   <li>Then calls {@link JsonWriter#value(String)}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement_thenCallsValue() throws IOException {
    // Arrange
    JsonWriter out = mock(JsonWriter.class);
    when(out.value(Mockito.<String>any())).thenReturn(new JsonWriter(new StringWriter()));

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, new JsonPrimitive("String"));

    // Assert
    verify(out).value(eq("String"));
  }

  /**
   * Test {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} with {@code JsonWriter},
   * {@code JsonElement}.
   *
   * <ul>
   *   <li>Then calls {@link JsonWriter#value(boolean)}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement_thenCallsValue2() throws IOException {
    // Arrange
    JsonWriter out = mock(JsonWriter.class);
    when(out.value(anyBoolean())).thenReturn(new JsonWriter(new StringWriter()));

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, new JsonPrimitive(true));

    // Assert
    verify(out).value(eq(true));
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
  @Category(MaintainedByDiffblue.class)
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
   *   <li>When {@link JsonObject} (default constructor).
   *   <li>Then calls {@link JsonWriter#beginObject()}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void JsonElementTypeAdapter.write(JsonWriter, JsonElement)"})
  public void testWriteWithJsonWriterJsonElement_whenJsonObject_thenCallsBeginObject()
      throws IOException {
    // Arrange
    JsonWriter out = mock(JsonWriter.class);
    when(out.beginObject()).thenReturn(new JsonWriter(new StringWriter()));
    when(out.endObject()).thenReturn(new JsonWriter(new StringWriter()));

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, new JsonObject());

    // Assert
    verify(out).beginObject();
    verify(out).endObject();
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
  @Category(MaintainedByDiffblue.class)
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
