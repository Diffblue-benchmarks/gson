package com.google.gson.internal.bind;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.Strictness;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import org.junit.Test;

public class JsonTreeReaderDiffblueTest {
  /** Method under test: {@link JsonTreeReader#beginArray()} */
  @Test
  public void testBeginArray() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> (new JsonTreeReader(JsonNull.INSTANCE)).beginArray());
    assertThrows(
        IllegalStateException.class, () -> (new JsonTreeReader(new JsonObject())).beginArray());
    assertThrows(
        IllegalStateException.class,
        () -> (new JsonTreeReader(new JsonPrimitive("Expected "))).beginArray());
    assertThrows(
        IllegalStateException.class,
        () -> (new JsonTreeReader(new JsonPrimitive(true))).beginArray());
  }

  /** Method under test: {@link JsonTreeReader#beginArray()} */
  @Test
  public void testBeginArray2() throws IOException {
    // Arrange
    JsonTreeReader jsonTreeReader = new JsonTreeReader(new JsonArray(3));

    // Act
    jsonTreeReader.beginArray();

    // Assert
    assertEquals("$[0]", jsonTreeReader.getPath());
    assertEquals("$[0]", jsonTreeReader.getPreviousPath());
    assertFalse(jsonTreeReader.hasNext());
  }

  /** Method under test: {@link JsonTreeReader#endArray()} */
  @Test
  public void testEndArray() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> (new JsonTreeReader(JsonNull.INSTANCE)).endArray());
    assertThrows(
        IllegalStateException.class, () -> (new JsonTreeReader(new JsonObject())).endArray());
    assertThrows(
        IllegalStateException.class, () -> (new JsonTreeReader(new JsonArray(3))).endArray());
    assertThrows(
        IllegalStateException.class,
        () -> (new JsonTreeReader(new JsonPrimitive("Expected "))).endArray());
    assertThrows(
        IllegalStateException.class,
        () -> (new JsonTreeReader(new JsonPrimitive(true))).endArray());
  }

  /** Method under test: {@link JsonTreeReader#beginObject()} */
  @Test
  public void testBeginObject() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> (new JsonTreeReader(JsonNull.INSTANCE)).beginObject());
    assertThrows(
        IllegalStateException.class, () -> (new JsonTreeReader(new JsonArray(3))).beginObject());
    assertThrows(
        IllegalStateException.class,
        () -> (new JsonTreeReader(new JsonPrimitive("Expected "))).beginObject());
    assertThrows(
        IllegalStateException.class,
        () -> (new JsonTreeReader(new JsonPrimitive(true))).beginObject());
  }

  /** Method under test: {@link JsonTreeReader#beginObject()} */
  @Test
  public void testBeginObject2() throws IOException {
    // Arrange
    JsonTreeReader jsonTreeReader = new JsonTreeReader(new JsonObject());

    // Act
    jsonTreeReader.beginObject();

    // Assert
    assertEquals("$.", jsonTreeReader.getPath());
    assertEquals("$.", jsonTreeReader.getPreviousPath());
    assertFalse(jsonTreeReader.hasNext());
  }

  /** Method under test: {@link JsonTreeReader#endObject()} */
  @Test
  public void testEndObject() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> (new JsonTreeReader(JsonNull.INSTANCE)).endObject());
    assertThrows(
        IllegalStateException.class, () -> (new JsonTreeReader(new JsonObject())).endObject());
    assertThrows(
        IllegalStateException.class, () -> (new JsonTreeReader(new JsonArray(3))).endObject());
    assertThrows(
        IllegalStateException.class,
        () -> (new JsonTreeReader(new JsonPrimitive("Expected "))).endObject());
    assertThrows(
        IllegalStateException.class,
        () -> (new JsonTreeReader(new JsonPrimitive(true))).endObject());
  }

  /** Method under test: {@link JsonTreeReader#hasNext()} */
  @Test
  public void testHasNext() throws IOException {
    // Arrange, Act and Assert
    assertTrue((new JsonTreeReader(JsonNull.INSTANCE)).hasNext());
    assertTrue((new JsonTreeReader(new JsonObject())).hasNext());
    assertTrue((new JsonTreeReader(new JsonArray(3))).hasNext());
    assertTrue((new JsonTreeReader(new JsonPrimitive("String"))).hasNext());
    assertTrue((new JsonTreeReader(new JsonPrimitive(true))).hasNext());
  }

  /** Method under test: {@link JsonTreeReader#peek()} */
  @Test
  public void testPeek() throws IOException {
    // Arrange, Act and Assert
    assertEquals(JsonToken.NULL, (new JsonTreeReader(JsonNull.INSTANCE)).peek());
    assertEquals(JsonToken.BEGIN_OBJECT, (new JsonTreeReader(new JsonObject())).peek());
    assertEquals(JsonToken.BEGIN_ARRAY, (new JsonTreeReader(new JsonArray(3))).peek());
    assertEquals(JsonToken.STRING, (new JsonTreeReader(new JsonPrimitive("String"))).peek());
    assertEquals(JsonToken.BOOLEAN, (new JsonTreeReader(new JsonPrimitive(true))).peek());
  }

  /** Method under test: {@link JsonTreeReader#nextName()} */
  @Test
  public void testNextName() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> (new JsonTreeReader(JsonNull.INSTANCE)).nextName());
    assertThrows(
        IllegalStateException.class, () -> (new JsonTreeReader(new JsonObject())).nextName());
    assertThrows(
        IllegalStateException.class, () -> (new JsonTreeReader(new JsonArray(3))).nextName());
    assertThrows(
        IllegalStateException.class,
        () -> (new JsonTreeReader(new JsonPrimitive("Expected "))).nextName());
    assertThrows(
        IllegalStateException.class,
        () -> (new JsonTreeReader(new JsonPrimitive(true))).nextName());
  }

  /** Method under test: {@link JsonTreeReader#nextString()} */
  @Test
  public void testNextString() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> (new JsonTreeReader(JsonNull.INSTANCE)).nextString());
    assertThrows(
        IllegalStateException.class, () -> (new JsonTreeReader(new JsonObject())).nextString());
    assertThrows(
        IllegalStateException.class, () -> (new JsonTreeReader(new JsonArray(3))).nextString());
    assertThrows(
        IllegalStateException.class,
        () -> (new JsonTreeReader(new JsonPrimitive(true))).nextString());
  }

  /** Method under test: {@link JsonTreeReader#nextString()} */
  @Test
  public void testNextString2() throws IOException {
    // Arrange
    JsonTreeReader jsonTreeReader = new JsonTreeReader(new JsonPrimitive("Expected "));

    // Act and Assert
    assertEquals("Expected ", jsonTreeReader.nextString());
    assertFalse(jsonTreeReader.hasNext());
  }

  /** Method under test: {@link JsonTreeReader#nextBoolean()} */
  @Test
  public void testNextBoolean() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> (new JsonTreeReader(JsonNull.INSTANCE)).nextBoolean());
    assertThrows(
        IllegalStateException.class, () -> (new JsonTreeReader(new JsonObject())).nextBoolean());
    assertThrows(
        IllegalStateException.class, () -> (new JsonTreeReader(new JsonArray(3))).nextBoolean());
    assertThrows(
        IllegalStateException.class,
        () -> (new JsonTreeReader(new JsonPrimitive("Expected "))).nextBoolean());
  }

  /** Method under test: {@link JsonTreeReader#nextBoolean()} */
  @Test
  public void testNextBoolean2() throws IOException {
    // Arrange
    JsonTreeReader jsonTreeReader = new JsonTreeReader(new JsonPrimitive(true));

    // Act
    boolean actualNextBooleanResult = jsonTreeReader.nextBoolean();

    // Assert
    assertFalse(jsonTreeReader.hasNext());
    assertTrue(actualNextBooleanResult);
  }

  /** Method under test: {@link JsonTreeReader#nextBoolean()} */
  @Test
  public void testNextBoolean3() throws IOException {
    // Arrange
    JsonTreeReader jsonTreeReader = new JsonTreeReader(new JsonPrimitive(false));

    // Act
    boolean actualNextBooleanResult = jsonTreeReader.nextBoolean();

    // Assert
    assertFalse(jsonTreeReader.hasNext());
    assertFalse(actualNextBooleanResult);
  }

  /** Method under test: {@link JsonTreeReader#nextNull()} */
  @Test
  public void testNextNull() throws IOException {
    // Arrange
    JsonTreeReader jsonTreeReader = new JsonTreeReader(JsonNull.INSTANCE);

    // Act
    jsonTreeReader.nextNull();

    // Assert
    assertFalse(jsonTreeReader.hasNext());
  }

  /** Method under test: {@link JsonTreeReader#nextNull()} */
  @Test
  public void testNextNull2() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> (new JsonTreeReader(new JsonObject())).nextNull());
  }

  /** Method under test: {@link JsonTreeReader#nextNull()} */
  @Test
  public void testNextNull3() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> (new JsonTreeReader(new JsonArray(3))).nextNull());
  }

  /** Method under test: {@link JsonTreeReader#nextNull()} */
  @Test
  public void testNextNull4() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> (new JsonTreeReader(new JsonPrimitive("String"))).nextNull());
  }

  /** Method under test: {@link JsonTreeReader#nextNull()} */
  @Test
  public void testNextNull5() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> (new JsonTreeReader(new JsonPrimitive(true))).nextNull());
  }

  /** Method under test: {@link JsonTreeReader#nextDouble()} */
  @Test
  public void testNextDouble() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> (new JsonTreeReader(JsonNull.INSTANCE)).nextDouble());
    assertThrows(
        IllegalStateException.class, () -> (new JsonTreeReader(new JsonObject())).nextDouble());
    assertThrows(
        IllegalStateException.class, () -> (new JsonTreeReader(new JsonArray(3))).nextDouble());
    assertThrows(
        IllegalStateException.class,
        () -> (new JsonTreeReader(new JsonPrimitive(true))).nextDouble());
  }

  /** Method under test: {@link JsonTreeReader#nextDouble()} */
  @Test
  public void testNextDouble2() throws IOException {
    // Arrange
    JsonTreeReader jsonTreeReader = new JsonTreeReader(new JsonPrimitive("42"));

    // Act and Assert
    assertEquals(42.0d, jsonTreeReader.nextDouble(), 0.0);
    assertFalse(jsonTreeReader.hasNext());
  }

  /** Method under test: {@link JsonTreeReader#nextDouble()} */
  @Test
  public void testNextDouble3() throws IOException {
    // Arrange
    JsonTreeReader jsonTreeReader = new JsonTreeReader(new JsonPrimitive("42"));
    jsonTreeReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertEquals(42.0d, jsonTreeReader.nextDouble(), 0.0);
    assertFalse(jsonTreeReader.hasNext());
  }

  /** Method under test: {@link JsonTreeReader#nextLong()} */
  @Test
  public void testNextLong() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> (new JsonTreeReader(JsonNull.INSTANCE)).nextLong());
    assertThrows(
        IllegalStateException.class, () -> (new JsonTreeReader(new JsonObject())).nextLong());
    assertThrows(
        IllegalStateException.class, () -> (new JsonTreeReader(new JsonArray(3))).nextLong());
    assertThrows(
        IllegalStateException.class,
        () -> (new JsonTreeReader(new JsonPrimitive(true))).nextLong());
  }

  /** Method under test: {@link JsonTreeReader#nextLong()} */
  @Test
  public void testNextLong2() throws IOException {
    // Arrange
    JsonTreeReader jsonTreeReader = new JsonTreeReader(new JsonPrimitive("42"));

    // Act and Assert
    assertEquals(42L, jsonTreeReader.nextLong());
    assertFalse(jsonTreeReader.hasNext());
  }

  /** Method under test: {@link JsonTreeReader#nextInt()} */
  @Test
  public void testNextInt() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> (new JsonTreeReader(JsonNull.INSTANCE)).nextInt());
    assertThrows(
        IllegalStateException.class, () -> (new JsonTreeReader(new JsonObject())).nextInt());
    assertThrows(
        IllegalStateException.class, () -> (new JsonTreeReader(new JsonArray(3))).nextInt());
    assertThrows(
        IllegalStateException.class, () -> (new JsonTreeReader(new JsonPrimitive(true))).nextInt());
  }

  /** Method under test: {@link JsonTreeReader#nextInt()} */
  @Test
  public void testNextInt2() throws IOException {
    // Arrange
    JsonTreeReader jsonTreeReader = new JsonTreeReader(new JsonPrimitive("42"));

    // Act and Assert
    assertEquals(42, jsonTreeReader.nextInt());
    assertFalse(jsonTreeReader.hasNext());
  }

  /** Method under test: {@link JsonTreeReader#nextJsonElement()} */
  @Test
  public void testNextJsonElement() throws IOException {
    // Arrange
    JsonTreeReader jsonTreeReader = new JsonTreeReader(JsonNull.INSTANCE);

    // Act
    JsonElement actualNextJsonElementResult = jsonTreeReader.nextJsonElement();

    // Assert
    assertFalse(jsonTreeReader.hasNext());
    assertSame(((JsonNull) actualNextJsonElementResult).INSTANCE, actualNextJsonElementResult);
  }

  /** Method under test: {@link JsonTreeReader#nextJsonElement()} */
  @Test
  public void testNextJsonElement2() throws IOException {
    // Arrange
    JsonObject element = new JsonObject();
    JsonTreeReader jsonTreeReader = new JsonTreeReader(element);

    // Act
    JsonElement actualNextJsonElementResult = jsonTreeReader.nextJsonElement();

    // Assert
    assertFalse(jsonTreeReader.hasNext());
    assertSame(element, actualNextJsonElementResult);
  }

  /** Method under test: {@link JsonTreeReader#nextJsonElement()} */
  @Test
  public void testNextJsonElement3() throws IOException {
    // Arrange
    JsonArray element = new JsonArray(3);
    JsonTreeReader jsonTreeReader = new JsonTreeReader(element);

    // Act
    JsonElement actualNextJsonElementResult = jsonTreeReader.nextJsonElement();

    // Assert
    assertFalse(jsonTreeReader.hasNext());
    assertSame(element, actualNextJsonElementResult);
  }

  /** Method under test: {@link JsonTreeReader#nextJsonElement()} */
  @Test
  public void testNextJsonElement4() throws IOException {
    // Arrange
    JsonPrimitive element = new JsonPrimitive("String");
    JsonTreeReader jsonTreeReader = new JsonTreeReader(element);

    // Act
    JsonElement actualNextJsonElementResult = jsonTreeReader.nextJsonElement();

    // Assert
    assertFalse(jsonTreeReader.hasNext());
    assertSame(element, actualNextJsonElementResult);
  }

  /** Method under test: {@link JsonTreeReader#nextJsonElement()} */
  @Test
  public void testNextJsonElement5() throws IOException {
    // Arrange
    JsonPrimitive element = new JsonPrimitive(true);
    JsonTreeReader jsonTreeReader = new JsonTreeReader(element);

    // Act
    JsonElement actualNextJsonElementResult = jsonTreeReader.nextJsonElement();

    // Assert
    assertFalse(jsonTreeReader.hasNext());
    assertSame(element, actualNextJsonElementResult);
  }

  /** Method under test: {@link JsonTreeReader#skipValue()} */
  @Test
  public void testSkipValue() throws IOException {
    // Arrange
    JsonTreeReader jsonTreeReader = new JsonTreeReader(JsonNull.INSTANCE);

    // Act
    jsonTreeReader.skipValue();

    // Assert
    assertFalse(jsonTreeReader.hasNext());
  }

  /** Method under test: {@link JsonTreeReader#skipValue()} */
  @Test
  public void testSkipValue2() throws IOException {
    // Arrange
    JsonTreeReader jsonTreeReader = new JsonTreeReader(new JsonObject());

    // Act
    jsonTreeReader.skipValue();

    // Assert
    assertFalse(jsonTreeReader.hasNext());
  }

  /** Method under test: {@link JsonTreeReader#skipValue()} */
  @Test
  public void testSkipValue3() throws IOException {
    // Arrange
    JsonTreeReader jsonTreeReader = new JsonTreeReader(new JsonArray(3));

    // Act
    jsonTreeReader.skipValue();

    // Assert
    assertFalse(jsonTreeReader.hasNext());
  }

  /** Method under test: {@link JsonTreeReader#skipValue()} */
  @Test
  public void testSkipValue4() throws IOException {
    // Arrange
    JsonTreeReader jsonTreeReader = new JsonTreeReader(new JsonPrimitive("String"));

    // Act
    jsonTreeReader.skipValue();

    // Assert
    assertFalse(jsonTreeReader.hasNext());
  }

  /** Method under test: {@link JsonTreeReader#skipValue()} */
  @Test
  public void testSkipValue5() throws IOException {
    // Arrange
    JsonTreeReader jsonTreeReader = new JsonTreeReader(new JsonPrimitive(true));

    // Act
    jsonTreeReader.skipValue();

    // Assert
    assertFalse(jsonTreeReader.hasNext());
  }

  /** Method under test: {@link JsonTreeReader#JsonTreeReader(JsonElement)} */
  @Test
  public void testNewJsonTreeReader() throws IOException {
    // Arrange and Act
    JsonTreeReader actualJsonTreeReader = new JsonTreeReader(JsonNull.INSTANCE);

    // Assert
    assertEquals("$", actualJsonTreeReader.getPath());
    assertEquals("$", actualJsonTreeReader.getPreviousPath());
    assertEquals(255, actualJsonTreeReader.getNestingLimit());
    assertEquals(Strictness.LEGACY_STRICT, actualJsonTreeReader.getStrictness());
    assertFalse(actualJsonTreeReader.isLenient());
    assertTrue(actualJsonTreeReader.hasNext());
  }

  /** Method under test: {@link JsonTreeReader#toString()} */
  @Test
  public void testToString() {
    // Arrange, Act and Assert
    assertEquals("JsonTreeReader at path $", (new JsonTreeReader(JsonNull.INSTANCE)).toString());
  }

  /** Method under test: {@link JsonTreeReader#promoteNameToValue()} */
  @Test
  public void testPromoteNameToValue() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> (new JsonTreeReader(JsonNull.INSTANCE)).promoteNameToValue());
    assertThrows(
        IllegalStateException.class,
        () -> (new JsonTreeReader(new JsonObject())).promoteNameToValue());
    assertThrows(
        IllegalStateException.class,
        () -> (new JsonTreeReader(new JsonArray(3))).promoteNameToValue());
    assertThrows(
        IllegalStateException.class,
        () -> (new JsonTreeReader(new JsonPrimitive("Expected "))).promoteNameToValue());
    assertThrows(
        IllegalStateException.class,
        () -> (new JsonTreeReader(new JsonPrimitive(true))).promoteNameToValue());
  }

  /** Method under test: {@link JsonTreeReader#getPath()} */
  @Test
  public void testGetPath() {
    // Arrange, Act and Assert
    assertEquals("$", (new JsonTreeReader(JsonNull.INSTANCE)).getPath());
    assertEquals("$", (new JsonTreeReader(new JsonArray(3))).getPath());
    assertEquals("$", (new JsonTreeReader(new JsonObject())).getPath());
  }

  /** Method under test: {@link JsonTreeReader#getPreviousPath()} */
  @Test
  public void testGetPreviousPath() {
    // Arrange, Act and Assert
    assertEquals("$", (new JsonTreeReader(JsonNull.INSTANCE)).getPreviousPath());
    assertEquals("$", (new JsonTreeReader(new JsonArray(3))).getPreviousPath());
    assertEquals("$", (new JsonTreeReader(new JsonObject())).getPreviousPath());
  }
}
