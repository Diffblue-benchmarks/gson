package com.google.gson.internal.bind;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import java.math.BigInteger;
import org.junit.Test;
import org.mockito.Mockito;

public class JsonElementTypeAdapterDiffblueTest {
  /** Method under test: {@link JsonElementTypeAdapter#read(JsonReader)} */
  @Test
  public void testRead() throws IOException {
    // Arrange and Act
    JsonElement actualReadResult =
        JsonElementTypeAdapter.ADAPTER.read(
            new JsonReader(new StringReader(Boolean.FALSE.toString())));

    // Assert
    assertTrue(actualReadResult instanceof JsonPrimitive);
    assertEquals('f', actualReadResult.getAsCharacter());
    assertFalse(actualReadResult.getAsBoolean());
    assertFalse(actualReadResult.isJsonArray());
    assertFalse(actualReadResult.isJsonNull());
    assertFalse(actualReadResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualReadResult).isNumber());
    assertFalse(((JsonPrimitive) actualReadResult).isString());
    assertTrue(actualReadResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualReadResult).isBoolean());
    String expectedAsString = Boolean.FALSE.toString();
    assertEquals(expectedAsString, actualReadResult.getAsString());
    assertSame(actualReadResult, actualReadResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonElementTypeAdapter#read(JsonReader)} */
  @Test
  public void testRead2() throws IOException {
    // Arrange and Act
    JsonElement actualReadResult =
        JsonElementTypeAdapter.ADAPTER.read(new JsonReader(new StringReader("FALSE")));

    // Assert
    assertTrue(actualReadResult instanceof JsonPrimitive);
    assertEquals('f', actualReadResult.getAsCharacter());
    assertFalse(actualReadResult.getAsBoolean());
    assertFalse(actualReadResult.isJsonArray());
    assertFalse(actualReadResult.isJsonNull());
    assertFalse(actualReadResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualReadResult).isNumber());
    assertFalse(((JsonPrimitive) actualReadResult).isString());
    assertTrue(actualReadResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualReadResult).isBoolean());
    String expectedAsString = Boolean.FALSE.toString();
    assertEquals(expectedAsString, actualReadResult.getAsString());
    assertSame(actualReadResult, actualReadResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonElementTypeAdapter#read(JsonReader)} */
  @Test
  public void testRead3() throws IOException {
    // Arrange and Act
    JsonElement actualReadResult =
        JsonElementTypeAdapter.ADAPTER.read(new JsonReader(new StringReader("42")));

    // Assert
    assertTrue(actualReadResult instanceof JsonPrimitive);
    Number asNumber = actualReadResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("42", actualReadResult.getAsString());
    assertEquals("42", asNumber.toString());
    BigInteger asBigInteger = actualReadResult.getAsBigInteger();
    assertEquals("42", asBigInteger.toString());
    assertEquals('4', actualReadResult.getAsCharacter());
    assertEquals(1, asBigInteger.getLowestSetBit());
    assertEquals(1, asBigInteger.signum());
    assertEquals(42, actualReadResult.getAsInt());
    assertEquals(42.0d, actualReadResult.getAsDouble(), 0.0);
    assertEquals(42.0f, actualReadResult.getAsFloat(), 0.0f);
    assertEquals(42L, actualReadResult.getAsLong());
    assertEquals((short) 42, actualReadResult.getAsShort());
    assertFalse(actualReadResult.getAsBoolean());
    assertFalse(actualReadResult.isJsonArray());
    assertFalse(actualReadResult.isJsonNull());
    assertFalse(actualReadResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualReadResult).isBoolean());
    assertFalse(((JsonPrimitive) actualReadResult).isString());
    assertTrue(actualReadResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualReadResult).isNumber());
    BigDecimal expectedAsBigDecimal = new BigDecimal("42");
    assertEquals(expectedAsBigDecimal, actualReadResult.getAsBigDecimal());
    assertEquals('*', actualReadResult.getAsByte());
    assertSame(actualReadResult, actualReadResult.getAsJsonPrimitive());
    assertArrayEquals(new byte[] {'*'}, asBigInteger.toByteArray());
  }

  /** Method under test: {@link JsonElementTypeAdapter#read(JsonReader)} */
  @Test
  public void testRead4() throws IOException {
    // Arrange
    JsonTreeReader in = new JsonTreeReader(JsonNull.INSTANCE);

    // Act
    JsonElement actualReadResult = JsonElementTypeAdapter.ADAPTER.read(in);

    // Assert
    assertFalse(in.hasNext());
    assertSame(((JsonNull) actualReadResult).INSTANCE, actualReadResult);
  }

  /** Method under test: {@link JsonElementTypeAdapter#read(JsonReader)} */
  @Test
  public void testRead5() throws IOException {
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
    assertFalse(actualReadResult.getAsBoolean());
    assertFalse(actualReadResult.isJsonArray());
    assertFalse(actualReadResult.isJsonNull());
    assertFalse(actualReadResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualReadResult).isBoolean());
    assertFalse(((JsonPrimitive) actualReadResult).isNumber());
    assertTrue(actualReadResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualReadResult).isString());
    assertSame(actualReadResult, actualReadResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonElementTypeAdapter#read(JsonReader)} */
  @Test
  public void testRead6() throws IOException {
    // Arrange
    JsonObject element = new JsonObject();
    JsonTreeReader in = new JsonTreeReader(element);

    // Act
    JsonElement actualReadResult = JsonElementTypeAdapter.ADAPTER.read(in);

    // Assert
    assertFalse(in.hasNext());
    assertSame(element, actualReadResult);
  }

  /** Method under test: {@link JsonElementTypeAdapter#read(JsonReader)} */
  @Test
  public void testRead7() throws IOException {
    // Arrange
    JsonArray element = new JsonArray(3);
    JsonTreeReader in = new JsonTreeReader(element);

    // Act
    JsonElement actualReadResult = JsonElementTypeAdapter.ADAPTER.read(in);

    // Assert
    assertFalse(in.hasNext());
    assertSame(element, actualReadResult);
  }

  /** Method under test: {@link JsonElementTypeAdapter#read(JsonReader)} */
  @Test
  public void testRead8() throws IOException {
    // Arrange
    JsonPrimitive element = new JsonPrimitive("String");
    JsonTreeReader in = new JsonTreeReader(element);

    // Act
    JsonElement actualReadResult = JsonElementTypeAdapter.ADAPTER.read(in);

    // Assert
    assertFalse(in.hasNext());
    assertSame(element, actualReadResult);
  }

  /** Method under test: {@link JsonElementTypeAdapter#read(JsonReader)} */
  @Test
  public void testRead9() throws IOException {
    // Arrange
    JsonPrimitive element = new JsonPrimitive(true);
    JsonTreeReader in = new JsonTreeReader(element);

    // Act
    JsonElement actualReadResult = JsonElementTypeAdapter.ADAPTER.read(in);

    // Assert
    assertFalse(in.hasNext());
    assertSame(element, actualReadResult);
  }

  /** Method under test: {@link JsonElementTypeAdapter#read(JsonReader)} */
  @Test
  public void testRead10() throws IOException {
    // Arrange
    JsonReader in = new JsonReader(new StringReader(Boolean.FALSE.toString()));
    in.setStrictness(Strictness.LENIENT);

    // Act
    JsonElement actualReadResult = JsonElementTypeAdapter.ADAPTER.read(in);

    // Assert
    assertTrue(actualReadResult instanceof JsonPrimitive);
    assertEquals('f', actualReadResult.getAsCharacter());
    assertFalse(actualReadResult.getAsBoolean());
    assertFalse(actualReadResult.isJsonArray());
    assertFalse(actualReadResult.isJsonNull());
    assertFalse(actualReadResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualReadResult).isNumber());
    assertFalse(((JsonPrimitive) actualReadResult).isString());
    assertTrue(actualReadResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualReadResult).isBoolean());
    String expectedAsString = Boolean.FALSE.toString();
    assertEquals(expectedAsString, actualReadResult.getAsString());
    assertSame(actualReadResult, actualReadResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonElementTypeAdapter#read(JsonReader)} */
  @Test
  public void testRead11() throws IOException {
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
    assertFalse(actualReadResult.getAsBoolean());
    assertFalse(actualReadResult.isJsonArray());
    assertFalse(actualReadResult.isJsonNull());
    assertFalse(actualReadResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualReadResult).isBoolean());
    assertFalse(((JsonPrimitive) actualReadResult).isNumber());
    assertTrue(actualReadResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualReadResult).isString());
    assertSame(actualReadResult, actualReadResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonElementTypeAdapter#read(JsonReader)} */
  @Test
  public void testRead12() throws IOException {
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
    assertFalse(actualReadResult.getAsBoolean());
    assertFalse(actualReadResult.isJsonArray());
    assertFalse(actualReadResult.isJsonNull());
    assertFalse(actualReadResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualReadResult).isBoolean());
    assertFalse(((JsonPrimitive) actualReadResult).isNumber());
    assertTrue(actualReadResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualReadResult).isString());
    assertSame(actualReadResult, actualReadResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonElementTypeAdapter#read(JsonReader)} */
  @Test
  public void testRead13() throws IOException {
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
    assertFalse(actualReadResult.getAsBoolean());
    assertFalse(actualReadResult.isJsonArray());
    assertFalse(actualReadResult.isJsonNull());
    assertFalse(actualReadResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualReadResult).isBoolean());
    assertFalse(((JsonPrimitive) actualReadResult).isNumber());
    assertTrue(actualReadResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualReadResult).isString());
    assertSame(actualReadResult, actualReadResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonElementTypeAdapter#read(JsonReader)} */
  @Test
  public void testRead14() throws IOException {
    // Arrange and Act
    JsonElement actualReadResult =
        JsonElementTypeAdapter.ADAPTER.read(new JsonReader(new StringReader("false at line ")));

    // Assert
    assertTrue(actualReadResult instanceof JsonPrimitive);
    assertEquals('f', actualReadResult.getAsCharacter());
    assertFalse(actualReadResult.getAsBoolean());
    assertFalse(actualReadResult.isJsonArray());
    assertFalse(actualReadResult.isJsonNull());
    assertFalse(actualReadResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualReadResult).isNumber());
    assertFalse(((JsonPrimitive) actualReadResult).isString());
    assertTrue(actualReadResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualReadResult).isBoolean());
    String expectedAsString = Boolean.FALSE.toString();
    assertEquals(expectedAsString, actualReadResult.getAsString());
    assertSame(actualReadResult, actualReadResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} */
  @Test
  public void testWrite() throws IOException {
    // Arrange
    JsonWriter out = mock(JsonWriter.class);
    when(out.nullValue()).thenReturn(new JsonWriter(new StringWriter()));

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, JsonNull.INSTANCE);

    // Assert that nothing has changed
    verify(out).nullValue();
  }

  /** Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} */
  @Test
  public void testWrite2() throws IOException {
    // Arrange
    JsonWriter out = mock(JsonWriter.class);
    when(out.nullValue()).thenReturn(new JsonWriter(new StringWriter()));

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, null);

    // Assert that nothing has changed
    verify(out).nullValue();
  }

  /** Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} */
  @Test
  public void testWrite3() throws IOException {
    // Arrange
    JsonWriter out = mock(JsonWriter.class);
    when(out.beginArray()).thenReturn(new JsonWriter(new StringWriter()));
    when(out.endArray()).thenReturn(new JsonWriter(new StringWriter()));

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, new JsonArray(3));

    // Assert that nothing has changed
    verify(out).beginArray();
    verify(out).endArray();
  }

  /** Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} */
  @Test
  public void testWrite4() throws IOException {
    // Arrange
    JsonWriter out = mock(JsonWriter.class);
    when(out.value(Mockito.<String>any())).thenReturn(new JsonWriter(new StringWriter()));

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, new JsonPrimitive("String"));

    // Assert that nothing has changed
    verify(out).value(eq("String"));
  }

  /** Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} */
  @Test
  public void testWrite5() throws IOException {
    // Arrange
    JsonWriter out = mock(JsonWriter.class);
    when(out.value(anyBoolean())).thenReturn(new JsonWriter(new StringWriter()));

    // Act
    JsonElementTypeAdapter.ADAPTER.write(out, new JsonPrimitive(true));

    // Assert that nothing has changed
    verify(out).value(eq(true));
  }

  /** Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} */
  @Test
  public void testWrite6() throws IOException {
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

  /** Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} */
  @Test
  public void testWrite7() throws IOException {
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

  /** Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} */
  @Test
  public void testWrite8() throws IOException {
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

  /** Method under test: {@link JsonElementTypeAdapter#write(JsonWriter, JsonElement)} */
  @Test
  public void testWrite9() throws IOException {
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
}
