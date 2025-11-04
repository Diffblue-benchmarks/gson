package com.google.gson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import com.google.gson.internal.LazilyParsedNumber;
import org.junit.Test;

public class JsonElementDiffblueTest {
  /** Method under test: {@link JsonElement#isJsonArray()} */
  @Test
  public void testIsJsonArray() {
    // Arrange, Act and Assert
    assertTrue((new JsonArray(3)).isJsonArray());
  }

  /** Method under test: {@link JsonElement#isJsonObject()} */
  @Test
  public void testIsJsonObject() {
    // Arrange, Act and Assert
    assertFalse((new JsonArray(3)).isJsonObject());
    assertTrue((new JsonObject()).isJsonObject());
  }

  /** Method under test: {@link JsonElement#isJsonPrimitive()} */
  @Test
  public void testIsJsonPrimitive() {
    // Arrange, Act and Assert
    assertFalse((new JsonArray(3)).isJsonPrimitive());
    assertTrue((new JsonPrimitive("String")).isJsonPrimitive());
  }

  /** Method under test: {@link JsonElement#isJsonNull()} */
  @Test
  public void testIsJsonNull() {
    // Arrange, Act and Assert
    assertFalse((new JsonArray(3)).isJsonNull());
    assertTrue(JsonNull.INSTANCE.isJsonNull());
  }

  /** Method under test: {@link JsonElement#getAsJsonObject()} */
  @Test
  public void testGetAsJsonObject() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new JsonArray(3)).getAsJsonObject());
  }

  /** Method under test: {@link JsonElement#getAsJsonObject()} */
  @Test
  public void testGetAsJsonObject2() {
    // Arrange
    JsonObject jsonObject = new JsonObject();

    // Act and Assert
    assertSame(jsonObject, jsonObject.getAsJsonObject());
  }

  /** Method under test: {@link JsonElement#getAsJsonObject()} */
  @Test
  public void testGetAsJsonObject3() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonObject());
  }

  /** Method under test: {@link JsonElement#getAsJsonObject()} */
  @Test
  public void testGetAsJsonObject4() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(false);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonObject());
  }

  /** Method under test: {@link JsonElement#getAsJsonObject()} */
  @Test
  public void testGetAsJsonObject5() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(' ');
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonObject());
  }

  /** Method under test: {@link JsonElement#getAsJsonObject()} */
  @Test
  public void testGetAsJsonObject6() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(Integer.valueOf(3));
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonObject());
  }

  /** Method under test: {@link JsonElement#getAsJsonObject()} */
  @Test
  public void testGetAsJsonObject7() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(JsonNull.INSTANCE);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonObject());
  }

  /** Method under test: {@link JsonElement#getAsJsonObject()} */
  @Test
  public void testGetAsJsonObject8() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add('\u0003');

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonObject());
  }

  /** Method under test: {@link JsonElement#getAsJsonObject()} */
  @Test
  public void testGetAsJsonObject9() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add((byte) 'A');
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonObject());
  }

  /** Method under test: {@link JsonElement#getAsJsonObject()} */
  @Test
  public void testGetAsJsonObject10() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(3L);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonObject());
  }

  /** Method under test: {@link JsonElement#getAsJsonObject()} */
  @Test
  public void testGetAsJsonObject11() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add((short) 3);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonObject());
  }

  /** Method under test: {@link JsonElement#getAsJsonObject()} */
  @Test
  public void testGetAsJsonObject12() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(10.0d);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonObject());
  }

  /** Method under test: {@link JsonElement#getAsJsonObject()} */
  @Test
  public void testGetAsJsonObject13() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(10.0f);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonObject());
  }

  /** Method under test: {@link JsonElement#getAsJsonObject()} */
  @Test
  public void testGetAsJsonObject14() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(Double.NaN);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonObject());
  }

  /** Method under test: {@link JsonElement#getAsJsonObject()} */
  @Test
  public void testGetAsJsonObject15() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new LazilyParsedNumber("42"));
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonObject());
  }

  /** Method under test: {@link JsonElement#getAsJsonObject()} */
  @Test
  public void testGetAsJsonObject16() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new JsonObject());
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonObject());
  }

  /** Method under test: {@link JsonElement#getAsJsonObject()} */
  @Test
  public void testGetAsJsonObject17() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new LazilyParsedNumber("-Infinity"));
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonObject());
  }

  /** Method under test: {@link JsonElement#getAsJsonObject()} */
  @Test
  public void testGetAsJsonObject18() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new LazilyParsedNumber("Infinity"));
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonObject());
  }

  /** Method under test: {@link JsonElement#getAsJsonObject()} */
  @Test
  public void testGetAsJsonObject19() {
    // Arrange
    JsonObject element = new JsonObject();
    element.add("Not a JSON Object: ", JsonNull.INSTANCE);

    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(element);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonObject());
  }

  /** Method under test: {@link JsonElement#getAsJsonObject()} */
  @Test
  public void testGetAsJsonObject20() {
    // Arrange
    JsonObject element = new JsonObject();
    element.add("out == null", JsonNull.INSTANCE);
    element.add("Not a JSON Object: ", JsonNull.INSTANCE);

    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(element);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonObject());
  }

  /** Method under test: {@link JsonElement#getAsJsonArray()} */
  @Test
  public void testGetAsJsonArray() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);

    // Act and Assert
    assertSame(jsonArray, jsonArray.getAsJsonArray());
  }

  /** Method under test: {@link JsonElement#getAsJsonPrimitive()} */
  @Test
  public void testGetAsJsonPrimitive() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new JsonArray(3)).getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonElement#getAsJsonPrimitive()} */
  @Test
  public void testGetAsJsonPrimitive2() {
    // Arrange
    JsonPrimitive jsonPrimitive = new JsonPrimitive("String");

    // Act and Assert
    assertSame(jsonPrimitive, jsonPrimitive.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonElement#getAsJsonPrimitive()} */
  @Test
  public void testGetAsJsonPrimitive3() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonElement#getAsJsonPrimitive()} */
  @Test
  public void testGetAsJsonPrimitive4() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(false);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonElement#getAsJsonPrimitive()} */
  @Test
  public void testGetAsJsonPrimitive5() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(' ');
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonElement#getAsJsonPrimitive()} */
  @Test
  public void testGetAsJsonPrimitive6() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(Integer.valueOf(3));
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonElement#getAsJsonPrimitive()} */
  @Test
  public void testGetAsJsonPrimitive7() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(JsonNull.INSTANCE);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonElement#getAsJsonPrimitive()} */
  @Test
  public void testGetAsJsonPrimitive8() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add('\u0003');

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonElement#getAsJsonPrimitive()} */
  @Test
  public void testGetAsJsonPrimitive9() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add((byte) 'A');
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonElement#getAsJsonPrimitive()} */
  @Test
  public void testGetAsJsonPrimitive10() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(3L);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonElement#getAsJsonPrimitive()} */
  @Test
  public void testGetAsJsonPrimitive11() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add((short) 3);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonElement#getAsJsonPrimitive()} */
  @Test
  public void testGetAsJsonPrimitive12() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(10.0d);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonElement#getAsJsonPrimitive()} */
  @Test
  public void testGetAsJsonPrimitive13() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(10.0f);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonElement#getAsJsonPrimitive()} */
  @Test
  public void testGetAsJsonPrimitive14() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(Double.NaN);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonElement#getAsJsonPrimitive()} */
  @Test
  public void testGetAsJsonPrimitive15() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new LazilyParsedNumber("42"));
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonElement#getAsJsonPrimitive()} */
  @Test
  public void testGetAsJsonPrimitive16() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new JsonObject());
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonElement#getAsJsonPrimitive()} */
  @Test
  public void testGetAsJsonPrimitive17() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new LazilyParsedNumber("-Infinity"));
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonElement#getAsJsonPrimitive()} */
  @Test
  public void testGetAsJsonPrimitive18() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new LazilyParsedNumber("Infinity"));
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonElement#getAsJsonPrimitive()} */
  @Test
  public void testGetAsJsonPrimitive19() {
    // Arrange
    JsonObject element = new JsonObject();
    element.add("Not a JSON Primitive: ", JsonNull.INSTANCE);

    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(element);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonElement#getAsJsonPrimitive()} */
  @Test
  public void testGetAsJsonPrimitive20() {
    // Arrange
    JsonObject element = new JsonObject();
    element.add("out == null", JsonNull.INSTANCE);
    element.add("Not a JSON Primitive: ", JsonNull.INSTANCE);

    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(element);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonPrimitive());
  }

  /** Method under test: {@link JsonElement#getAsJsonNull()} */
  @Test
  public void testGetAsJsonNull() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new JsonArray(3)).getAsJsonNull());
  }

  /** Method under test: {@link JsonElement#getAsJsonNull()} */
  @Test
  public void testGetAsJsonNull2() {
    // Arrange and Act
    JsonNull actualAsJsonNull = JsonNull.INSTANCE.getAsJsonNull();

    // Assert
    assertSame(actualAsJsonNull.INSTANCE, actualAsJsonNull);
  }

  /** Method under test: {@link JsonElement#getAsJsonNull()} */
  @Test
  public void testGetAsJsonNull3() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonNull());
  }

  /** Method under test: {@link JsonElement#getAsJsonNull()} */
  @Test
  public void testGetAsJsonNull4() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(false);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonNull());
  }

  /** Method under test: {@link JsonElement#getAsJsonNull()} */
  @Test
  public void testGetAsJsonNull5() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(' ');
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonNull());
  }

  /** Method under test: {@link JsonElement#getAsJsonNull()} */
  @Test
  public void testGetAsJsonNull6() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(Integer.valueOf(3));
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonNull());
  }

  /** Method under test: {@link JsonElement#getAsJsonNull()} */
  @Test
  public void testGetAsJsonNull7() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(JsonNull.INSTANCE);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonNull());
  }

  /** Method under test: {@link JsonElement#getAsJsonNull()} */
  @Test
  public void testGetAsJsonNull8() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add('\u0003');

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonNull());
  }

  /** Method under test: {@link JsonElement#getAsJsonNull()} */
  @Test
  public void testGetAsJsonNull9() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add((byte) 'A');
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonNull());
  }

  /** Method under test: {@link JsonElement#getAsJsonNull()} */
  @Test
  public void testGetAsJsonNull10() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(3L);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonNull());
  }

  /** Method under test: {@link JsonElement#getAsJsonNull()} */
  @Test
  public void testGetAsJsonNull11() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add((short) 3);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonNull());
  }

  /** Method under test: {@link JsonElement#getAsJsonNull()} */
  @Test
  public void testGetAsJsonNull12() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(10.0d);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonNull());
  }

  /** Method under test: {@link JsonElement#getAsJsonNull()} */
  @Test
  public void testGetAsJsonNull13() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(10.0f);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonNull());
  }

  /** Method under test: {@link JsonElement#getAsJsonNull()} */
  @Test
  public void testGetAsJsonNull14() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(Double.NaN);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonNull());
  }

  /** Method under test: {@link JsonElement#getAsJsonNull()} */
  @Test
  public void testGetAsJsonNull15() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new LazilyParsedNumber("42"));
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonNull());
  }

  /** Method under test: {@link JsonElement#getAsJsonNull()} */
  @Test
  public void testGetAsJsonNull16() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new JsonObject());
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonNull());
  }

  /** Method under test: {@link JsonElement#getAsJsonNull()} */
  @Test
  public void testGetAsJsonNull17() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new LazilyParsedNumber("-Infinity"));
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonNull());
  }

  /** Method under test: {@link JsonElement#getAsJsonNull()} */
  @Test
  public void testGetAsJsonNull18() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new LazilyParsedNumber("Infinity"));
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonNull());
  }

  /** Method under test: {@link JsonElement#getAsJsonNull()} */
  @Test
  public void testGetAsJsonNull19() {
    // Arrange
    JsonObject element = new JsonObject();
    element.add("Not a JSON Null: ", JsonNull.INSTANCE);

    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(element);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonNull());
  }

  /** Method under test: {@link JsonElement#getAsJsonNull()} */
  @Test
  public void testGetAsJsonNull20() {
    // Arrange
    JsonObject element = new JsonObject();
    element.add("out == null", JsonNull.INSTANCE);
    element.add("Not a JSON Null: ", JsonNull.INSTANCE);

    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(element);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonNull());
  }

  /** Method under test: {@link JsonElement#getAsBoolean()} */
  @Test
  public void testGetAsBoolean() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> JsonNull.INSTANCE.getAsBoolean());
  }

  /** Method under test: {@link JsonElement#getAsNumber()} */
  @Test
  public void testGetAsNumber() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> JsonNull.INSTANCE.getAsNumber());
  }

  /** Method under test: {@link JsonElement#getAsString()} */
  @Test
  public void testGetAsString() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> JsonNull.INSTANCE.getAsString());
  }

  /** Method under test: {@link JsonElement#getAsDouble()} */
  @Test
  public void testGetAsDouble() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> JsonNull.INSTANCE.getAsDouble());
  }

  /** Method under test: {@link JsonElement#getAsFloat()} */
  @Test
  public void testGetAsFloat() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> JsonNull.INSTANCE.getAsFloat());
  }

  /** Method under test: {@link JsonElement#getAsLong()} */
  @Test
  public void testGetAsLong() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> JsonNull.INSTANCE.getAsLong());
  }

  /** Method under test: {@link JsonElement#getAsInt()} */
  @Test
  public void testGetAsInt() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> JsonNull.INSTANCE.getAsInt());
  }

  /** Method under test: {@link JsonElement#getAsByte()} */
  @Test
  public void testGetAsByte() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> JsonNull.INSTANCE.getAsByte());
  }

  /** Method under test: {@link JsonElement#getAsCharacter()} */
  @Test
  public void testGetAsCharacter() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> JsonNull.INSTANCE.getAsCharacter());
  }

  /** Method under test: {@link JsonElement#getAsBigDecimal()} */
  @Test
  public void testGetAsBigDecimal() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> JsonNull.INSTANCE.getAsBigDecimal());
  }

  /** Method under test: {@link JsonElement#getAsBigInteger()} */
  @Test
  public void testGetAsBigInteger() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> JsonNull.INSTANCE.getAsBigInteger());
  }

  /** Method under test: {@link JsonElement#getAsShort()} */
  @Test
  public void testGetAsShort() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> JsonNull.INSTANCE.getAsShort());
  }

  /** Method under test: {@link JsonElement#toString()} */
  @Test
  public void testToString() {
    // Arrange, Act and Assert
    assertEquals("[]", (new JsonArray(3)).toString());
    assertEquals("null", JsonNull.INSTANCE.toString());
  }

  /** Method under test: {@link JsonElement#toString()} */
  @Test
  public void testToString2() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(true);

    // Act and Assert
    assertEquals("[true]", jsonArray.toString());
  }

  /** Method under test: {@link JsonElement#toString()} */
  @Test
  public void testToString3() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(false);
    jsonArray.add(true);

    // Act and Assert
    assertEquals("[false,true]", jsonArray.toString());
  }

  /** Method under test: {@link JsonElement#toString()} */
  @Test
  public void testToString4() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add('\u0006');
    jsonArray.add(true);

    // Act and Assert
    assertEquals("[\"\\u0006\",true]", jsonArray.toString());
  }

  /** Method under test: {@link JsonElement#toString()} */
  @Test
  public void testToString5() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(Integer.valueOf(Integer.SIZE));
    jsonArray.add(true);

    // Act and Assert
    assertEquals("[32,true]", jsonArray.toString());
  }

  /** Method under test: {@link JsonElement#toString()} */
  @Test
  public void testToString6() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(",");
    jsonArray.add(true);

    // Act and Assert
    assertEquals("[\",\",true]", jsonArray.toString());
  }

  /** Method under test: {@link JsonElement#toString()} */
  @Test
  public void testToString7() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add((byte) 'A');
    jsonArray.add(true);

    // Act and Assert
    assertEquals("[65,true]", jsonArray.toString());
  }

  /** Method under test: {@link JsonElement#toString()} */
  @Test
  public void testToString8() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(32L);
    jsonArray.add(true);

    // Act and Assert
    assertEquals("[32,true]", jsonArray.toString());
  }

  /** Method under test: {@link JsonElement#toString()} */
  @Test
  public void testToString9() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add((short) 32);
    jsonArray.add(true);

    // Act and Assert
    assertEquals("[32,true]", jsonArray.toString());
  }

  /** Method under test: {@link JsonElement#toString()} */
  @Test
  public void testToString10() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(10.0d);
    jsonArray.add(true);

    // Act and Assert
    assertEquals("[10.0,true]", jsonArray.toString());
  }

  /** Method under test: {@link JsonElement#toString()} */
  @Test
  public void testToString11() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(10.0f);
    jsonArray.add(true);

    // Act and Assert
    assertEquals("[10.0,true]", jsonArray.toString());
  }

  /** Method under test: {@link JsonElement#toString()} */
  @Test
  public void testToString12() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(Double.NaN);
    jsonArray.add(true);

    // Act and Assert
    assertEquals("[NaN,true]", jsonArray.toString());
  }

  /** Method under test: {@link JsonElement#toString()} */
  @Test
  public void testToString13() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new LazilyParsedNumber("42"));
    jsonArray.add(true);

    // Act and Assert
    assertEquals("[42,true]", jsonArray.toString());
  }

  /** Method under test: {@link JsonElement#toString()} */
  @Test
  public void testToString14() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new LazilyParsedNumber("-Infinity"));
    jsonArray.add(true);

    // Act and Assert
    assertEquals("[-Infinity,true]", jsonArray.toString());
  }

  /** Method under test: {@link JsonElement#toString()} */
  @Test
  public void testToString15() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new LazilyParsedNumber("Infinity"));
    jsonArray.add(true);

    // Act and Assert
    assertEquals("[Infinity,true]", jsonArray.toString());
  }
}
