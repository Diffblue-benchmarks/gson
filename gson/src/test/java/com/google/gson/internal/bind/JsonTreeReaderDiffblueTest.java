package com.google.gson.internal.bind;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
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
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JsonTreeReaderDiffblueTest {
  /**
   * Test {@link JsonTreeReader#JsonTreeReader(JsonElement)}.
   *
   * <p>Method under test: {@link JsonTreeReader#JsonTreeReader(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonTreeReader.<init>(JsonElement)"})
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

  /**
   * Test {@link JsonTreeReader#beginArray()}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#beginArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonTreeReader.beginArray()"})
  public void testBeginArray_givenJsonPrimitiveWithBoolIsTrue_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> new JsonTreeReader(new JsonPrimitive(true)).beginArray());
  }

  /**
   * Test {@link JsonTreeReader#beginArray()}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(String)} with string is {@code Expected}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#beginArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonTreeReader.beginArray()"})
  public void testBeginArray_givenJsonPrimitiveWithStringIsExpected() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> new JsonTreeReader(new JsonPrimitive("Expected ")).beginArray());
  }

  /**
   * Test {@link JsonTreeReader#beginArray()}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#beginArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonTreeReader.beginArray()"})
  public void testBeginArray_givenJsonTreeReaderWithElementIsInstance() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonTreeReader(JsonNull.INSTANCE).beginArray());
  }

  /**
   * Test {@link JsonTreeReader#beginArray()}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonObject} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#beginArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonTreeReader.beginArray()"})
  public void testBeginArray_givenJsonTreeReaderWithElementIsJsonObject() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonTreeReader(new JsonObject()).beginArray());
  }

  /**
   * Test {@link JsonTreeReader#beginArray()}.
   *
   * <ul>
   *   <li>Then {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonArray#JsonArray(int)} Path is {@code $[0]}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#beginArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonTreeReader.beginArray()"})
  public void testBeginArray_thenJsonTreeReaderWithElementIsJsonArrayPathIs0() throws IOException {
    // Arrange
    JsonTreeReader jsonTreeReader = new JsonTreeReader(new JsonArray(3));

    // Act
    jsonTreeReader.beginArray();

    // Assert
    assertEquals("$[0]", jsonTreeReader.getPath());
    assertEquals("$[0]", jsonTreeReader.getPreviousPath());
    assertFalse(jsonTreeReader.hasNext());
  }

  /**
   * Test {@link JsonTreeReader#endArray()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#endArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonTreeReader.endArray()"})
  public void testEndArray_givenJsonArrayWithCapacityIsThree_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonTreeReader(new JsonArray(3)).endArray());
  }

  /**
   * Test {@link JsonTreeReader#endArray()}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#endArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonTreeReader.endArray()"})
  public void testEndArray_givenJsonPrimitiveWithBoolIsTrue_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonTreeReader(new JsonPrimitive(true)).endArray());
  }

  /**
   * Test {@link JsonTreeReader#endArray()}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(String)} with string is {@code Expected}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#endArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonTreeReader.endArray()"})
  public void testEndArray_givenJsonPrimitiveWithStringIsExpected() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> new JsonTreeReader(new JsonPrimitive("Expected ")).endArray());
  }

  /**
   * Test {@link JsonTreeReader#endArray()}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#endArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonTreeReader.endArray()"})
  public void testEndArray_givenJsonTreeReaderWithElementIsInstance() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonTreeReader(JsonNull.INSTANCE).endArray());
  }

  /**
   * Test {@link JsonTreeReader#endArray()}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonObject} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#endArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonTreeReader.endArray()"})
  public void testEndArray_givenJsonTreeReaderWithElementIsJsonObject() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonTreeReader(new JsonObject()).endArray());
  }

  /**
   * Test {@link JsonTreeReader#beginObject()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#beginObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonTreeReader.beginObject()"})
  public void testBeginObject_givenJsonArrayWithCapacityIsThree_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonTreeReader(new JsonArray(3)).beginObject());
  }

  /**
   * Test {@link JsonTreeReader#beginObject()}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#beginObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonTreeReader.beginObject()"})
  public void testBeginObject_givenJsonPrimitiveWithBoolIsTrue_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> new JsonTreeReader(new JsonPrimitive(true)).beginObject());
  }

  /**
   * Test {@link JsonTreeReader#beginObject()}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(String)} with string is {@code Expected}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#beginObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonTreeReader.beginObject()"})
  public void testBeginObject_givenJsonPrimitiveWithStringIsExpected() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> new JsonTreeReader(new JsonPrimitive("Expected ")).beginObject());
  }

  /**
   * Test {@link JsonTreeReader#beginObject()}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#beginObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonTreeReader.beginObject()"})
  public void testBeginObject_givenJsonTreeReaderWithElementIsInstance() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonTreeReader(JsonNull.INSTANCE).beginObject());
  }

  /**
   * Test {@link JsonTreeReader#beginObject()}.
   *
   * <ul>
   *   <li>Then {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonObject} (default constructor) Path is {@code $.}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#beginObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonTreeReader.beginObject()"})
  public void testBeginObject_thenJsonTreeReaderWithElementIsJsonObjectPathIsDollarSignDot()
      throws IOException {
    // Arrange
    JsonTreeReader jsonTreeReader = new JsonTreeReader(new JsonObject());

    // Act
    jsonTreeReader.beginObject();

    // Assert
    assertEquals("$.", jsonTreeReader.getPath());
    assertEquals("$.", jsonTreeReader.getPreviousPath());
    assertFalse(jsonTreeReader.hasNext());
  }

  /**
   * Test {@link JsonTreeReader#endObject()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#endObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonTreeReader.endObject()"})
  public void testEndObject_givenJsonArrayWithCapacityIsThree_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonTreeReader(new JsonArray(3)).endObject());
  }

  /**
   * Test {@link JsonTreeReader#endObject()}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#endObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonTreeReader.endObject()"})
  public void testEndObject_givenJsonPrimitiveWithBoolIsTrue_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonTreeReader(new JsonPrimitive(true)).endObject());
  }

  /**
   * Test {@link JsonTreeReader#endObject()}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(String)} with string is {@code Expected}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#endObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonTreeReader.endObject()"})
  public void testEndObject_givenJsonPrimitiveWithStringIsExpected() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> new JsonTreeReader(new JsonPrimitive("Expected ")).endObject());
  }

  /**
   * Test {@link JsonTreeReader#endObject()}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#endObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonTreeReader.endObject()"})
  public void testEndObject_givenJsonTreeReaderWithElementIsInstance() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonTreeReader(JsonNull.INSTANCE).endObject());
  }

  /**
   * Test {@link JsonTreeReader#endObject()}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonObject} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#endObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonTreeReader.endObject()"})
  public void testEndObject_givenJsonTreeReaderWithElementIsJsonObject() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonTreeReader(new JsonObject()).endObject());
  }

  /**
   * Test {@link JsonTreeReader#hasNext()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#hasNext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonTreeReader.hasNext()"})
  public void testHasNext_givenJsonArrayWithCapacityIsThree_thenReturnTrue() throws IOException {
    // Arrange, Act and Assert
    assertTrue(new JsonTreeReader(new JsonArray(3)).hasNext());
  }

  /**
   * Test {@link JsonTreeReader#hasNext()}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#hasNext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonTreeReader.hasNext()"})
  public void testHasNext_givenJsonPrimitiveWithBoolIsTrue_thenReturnTrue() throws IOException {
    // Arrange, Act and Assert
    assertTrue(new JsonTreeReader(new JsonPrimitive(true)).hasNext());
  }

  /**
   * Test {@link JsonTreeReader#hasNext()}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(String)} with {@code String}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#hasNext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonTreeReader.hasNext()"})
  public void testHasNext_givenJsonPrimitiveWithString_thenReturnTrue() throws IOException {
    // Arrange, Act and Assert
    assertTrue(new JsonTreeReader(new JsonPrimitive("String")).hasNext());
  }

  /**
   * Test {@link JsonTreeReader#hasNext()}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#hasNext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonTreeReader.hasNext()"})
  public void testHasNext_givenJsonTreeReaderWithElementIsInstance_thenReturnTrue()
      throws IOException {
    // Arrange, Act and Assert
    assertTrue(new JsonTreeReader(JsonNull.INSTANCE).hasNext());
  }

  /**
   * Test {@link JsonTreeReader#hasNext()}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonObject} (default constructor).
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#hasNext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonTreeReader.hasNext()"})
  public void testHasNext_givenJsonTreeReaderWithElementIsJsonObject_thenReturnTrue()
      throws IOException {
    // Arrange, Act and Assert
    assertTrue(new JsonTreeReader(new JsonObject()).hasNext());
  }

  /**
   * Test {@link JsonTreeReader#peek()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three.
   *   <li>Then return {@code BEGIN_ARRAY}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#peek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonToken JsonTreeReader.peek()"})
  public void testPeek_givenJsonArrayWithCapacityIsThree_thenReturnBeginArray() throws IOException {
    // Arrange, Act and Assert
    assertEquals(JsonToken.BEGIN_ARRAY, new JsonTreeReader(new JsonArray(3)).peek());
  }

  /**
   * Test {@link JsonTreeReader#peek()}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.
   *   <li>Then return {@code BOOLEAN}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#peek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonToken JsonTreeReader.peek()"})
  public void testPeek_givenJsonPrimitiveWithBoolIsTrue_thenReturnBoolean() throws IOException {
    // Arrange, Act and Assert
    assertEquals(JsonToken.BOOLEAN, new JsonTreeReader(new JsonPrimitive(true)).peek());
  }

  /**
   * Test {@link JsonTreeReader#peek()}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(String)} with {@code String}.
   *   <li>Then return {@code STRING}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#peek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonToken JsonTreeReader.peek()"})
  public void testPeek_givenJsonPrimitiveWithString_thenReturnString() throws IOException {
    // Arrange, Act and Assert
    assertEquals(JsonToken.STRING, new JsonTreeReader(new JsonPrimitive("String")).peek());
  }

  /**
   * Test {@link JsonTreeReader#peek()}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE}.
   *   <li>Then return {@code NULL}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#peek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonToken JsonTreeReader.peek()"})
  public void testPeek_givenJsonTreeReaderWithElementIsInstance_thenReturnNull()
      throws IOException {
    // Arrange, Act and Assert
    assertEquals(JsonToken.NULL, new JsonTreeReader(JsonNull.INSTANCE).peek());
  }

  /**
   * Test {@link JsonTreeReader#peek()}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonObject} (default constructor).
   *   <li>Then return {@code BEGIN_OBJECT}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#peek()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonToken JsonTreeReader.peek()"})
  public void testPeek_givenJsonTreeReaderWithElementIsJsonObject_thenReturnBeginObject()
      throws IOException {
    // Arrange, Act and Assert
    assertEquals(JsonToken.BEGIN_OBJECT, new JsonTreeReader(new JsonObject()).peek());
  }

  /**
   * Test {@link JsonTreeReader#nextName()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#nextName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonTreeReader.nextName()"})
  public void testNextName_givenJsonArrayWithCapacityIsThree_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonTreeReader(new JsonArray(3)).nextName());
  }

  /**
   * Test {@link JsonTreeReader#nextName()}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#nextName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonTreeReader.nextName()"})
  public void testNextName_givenJsonPrimitiveWithBoolIsTrue_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonTreeReader(new JsonPrimitive(true)).nextName());
  }

  /**
   * Test {@link JsonTreeReader#nextName()}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(String)} with string is {@code Expected}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#nextName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonTreeReader.nextName()"})
  public void testNextName_givenJsonPrimitiveWithStringIsExpected() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> new JsonTreeReader(new JsonPrimitive("Expected ")).nextName());
  }

  /**
   * Test {@link JsonTreeReader#nextName()}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#nextName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonTreeReader.nextName()"})
  public void testNextName_givenJsonTreeReaderWithElementIsInstance() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonTreeReader(JsonNull.INSTANCE).nextName());
  }

  /**
   * Test {@link JsonTreeReader#nextName()}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonObject} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#nextName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonTreeReader.nextName()"})
  public void testNextName_givenJsonTreeReaderWithElementIsJsonObject() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonTreeReader(new JsonObject()).nextName());
  }

  /**
   * Test {@link JsonTreeReader#nextString()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#nextString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonTreeReader.nextString()"})
  public void testNextString_givenJsonArrayWithCapacityIsThree_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonTreeReader(new JsonArray(3)).nextString());
  }

  /**
   * Test {@link JsonTreeReader#nextString()}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#nextString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonTreeReader.nextString()"})
  public void testNextString_givenJsonPrimitiveWithBoolIsTrue_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> new JsonTreeReader(new JsonPrimitive(true)).nextString());
  }

  /**
   * Test {@link JsonTreeReader#nextString()}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(String)} with string is {@code Expected}.
   *   <li>Then return {@code Expected}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#nextString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonTreeReader.nextString()"})
  public void testNextString_givenJsonPrimitiveWithStringIsExpected_thenReturnExpected()
      throws IOException {
    // Arrange
    JsonTreeReader jsonTreeReader = new JsonTreeReader(new JsonPrimitive("Expected "));

    // Act and Assert
    assertEquals("Expected ", jsonTreeReader.nextString());
    assertFalse(jsonTreeReader.hasNext());
  }

  /**
   * Test {@link JsonTreeReader#nextString()}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#nextString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonTreeReader.nextString()"})
  public void testNextString_givenJsonTreeReaderWithElementIsInstance() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonTreeReader(JsonNull.INSTANCE).nextString());
  }

  /**
   * Test {@link JsonTreeReader#nextString()}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonObject} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#nextString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonTreeReader.nextString()"})
  public void testNextString_givenJsonTreeReaderWithElementIsJsonObject() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonTreeReader(new JsonObject()).nextString());
  }

  /**
   * Test {@link JsonTreeReader#nextBoolean()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#nextBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonTreeReader.nextBoolean()"})
  public void testNextBoolean_givenJsonArrayWithCapacityIsThree_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonTreeReader(new JsonArray(3)).nextBoolean());
  }

  /**
   * Test {@link JsonTreeReader#nextBoolean()}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#nextBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonTreeReader.nextBoolean()"})
  public void testNextBoolean_givenJsonPrimitiveWithBoolIsFalse_thenReturnFalse()
      throws IOException {
    // Arrange
    JsonTreeReader jsonTreeReader = new JsonTreeReader(new JsonPrimitive(false));

    // Act
    boolean actualNextBooleanResult = jsonTreeReader.nextBoolean();

    // Assert
    assertFalse(jsonTreeReader.hasNext());
    assertFalse(actualNextBooleanResult);
  }

  /**
   * Test {@link JsonTreeReader#nextBoolean()}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#nextBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonTreeReader.nextBoolean()"})
  public void testNextBoolean_givenJsonPrimitiveWithBoolIsTrue_thenReturnTrue() throws IOException {
    // Arrange
    JsonTreeReader jsonTreeReader = new JsonTreeReader(new JsonPrimitive(true));

    // Act
    boolean actualNextBooleanResult = jsonTreeReader.nextBoolean();

    // Assert
    assertFalse(jsonTreeReader.hasNext());
    assertTrue(actualNextBooleanResult);
  }

  /**
   * Test {@link JsonTreeReader#nextBoolean()}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(String)} with string is {@code Expected}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#nextBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonTreeReader.nextBoolean()"})
  public void testNextBoolean_givenJsonPrimitiveWithStringIsExpected() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> new JsonTreeReader(new JsonPrimitive("Expected ")).nextBoolean());
  }

  /**
   * Test {@link JsonTreeReader#nextBoolean()}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#nextBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonTreeReader.nextBoolean()"})
  public void testNextBoolean_givenJsonTreeReaderWithElementIsInstance() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonTreeReader(JsonNull.INSTANCE).nextBoolean());
  }

  /**
   * Test {@link JsonTreeReader#nextBoolean()}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonObject} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#nextBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonTreeReader.nextBoolean()"})
  public void testNextBoolean_givenJsonTreeReaderWithElementIsJsonObject() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonTreeReader(new JsonObject()).nextBoolean());
  }

  /**
   * Test {@link JsonTreeReader#nextNull()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#nextNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonTreeReader.nextNull()"})
  public void testNextNull_givenJsonArrayWithCapacityIsThree_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonTreeReader(new JsonArray(3)).nextNull());
  }

  /**
   * Test {@link JsonTreeReader#nextNull()}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#nextNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonTreeReader.nextNull()"})
  public void testNextNull_givenJsonPrimitiveWithBoolIsTrue_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonTreeReader(new JsonPrimitive(true)).nextNull());
  }

  /**
   * Test {@link JsonTreeReader#nextNull()}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(String)} with {@code String}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#nextNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonTreeReader.nextNull()"})
  public void testNextNull_givenJsonPrimitiveWithString_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> new JsonTreeReader(new JsonPrimitive("String")).nextNull());
  }

  /**
   * Test {@link JsonTreeReader#nextNull()}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonObject} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#nextNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonTreeReader.nextNull()"})
  public void testNextNull_givenJsonTreeReaderWithElementIsJsonObject() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonTreeReader(new JsonObject()).nextNull());
  }

  /**
   * Test {@link JsonTreeReader#nextNull()}.
   *
   * <ul>
   *   <li>Then not {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE} hasNext.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#nextNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonTreeReader.nextNull()"})
  public void testNextNull_thenNotJsonTreeReaderWithElementIsInstanceHasNext() throws IOException {
    // Arrange
    JsonTreeReader jsonTreeReader = new JsonTreeReader(JsonNull.INSTANCE);

    // Act
    jsonTreeReader.nextNull();

    // Assert
    assertFalse(jsonTreeReader.hasNext());
  }

  /**
   * Test {@link JsonTreeReader#nextDouble()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#nextDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JsonTreeReader.nextDouble()"})
  public void testNextDouble_givenJsonArrayWithCapacityIsThree_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonTreeReader(new JsonArray(3)).nextDouble());
  }

  /**
   * Test {@link JsonTreeReader#nextDouble()}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#nextDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JsonTreeReader.nextDouble()"})
  public void testNextDouble_givenJsonPrimitiveWithBoolIsTrue_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> new JsonTreeReader(new JsonPrimitive(true)).nextDouble());
  }

  /**
   * Test {@link JsonTreeReader#nextDouble()}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(String)} with string is {@code 42}.
   *   <li>Then return forty-two.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#nextDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JsonTreeReader.nextDouble()"})
  public void testNextDouble_givenJsonPrimitiveWithStringIs42_thenReturnFortyTwo()
      throws IOException {
    // Arrange
    JsonTreeReader jsonTreeReader = new JsonTreeReader(new JsonPrimitive("42"));

    // Act and Assert
    assertEquals(42.0d, jsonTreeReader.nextDouble(), 0.0);
    assertFalse(jsonTreeReader.hasNext());
  }

  /**
   * Test {@link JsonTreeReader#nextDouble()}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#nextDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JsonTreeReader.nextDouble()"})
  public void testNextDouble_givenJsonTreeReaderWithElementIsInstance() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonTreeReader(JsonNull.INSTANCE).nextDouble());
  }

  /**
   * Test {@link JsonTreeReader#nextDouble()}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonObject} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#nextDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JsonTreeReader.nextDouble()"})
  public void testNextDouble_givenJsonTreeReaderWithElementIsJsonObject() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonTreeReader(new JsonObject()).nextDouble());
  }

  /**
   * Test {@link JsonTreeReader#nextDouble()}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonPrimitive#JsonPrimitive(String)} Strictness is {@code LENIENT}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#nextDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JsonTreeReader.nextDouble()"})
  public void testNextDouble_givenJsonTreeReaderWithElementIsJsonPrimitiveStrictnessIsLenient()
      throws IOException {
    // Arrange
    JsonTreeReader jsonTreeReader = new JsonTreeReader(new JsonPrimitive("42"));
    jsonTreeReader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertEquals(42.0d, jsonTreeReader.nextDouble(), 0.0);
    assertFalse(jsonTreeReader.hasNext());
  }

  /**
   * Test {@link JsonTreeReader#nextLong()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#nextLong()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JsonTreeReader.nextLong()"})
  public void testNextLong_givenJsonArrayWithCapacityIsThree_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonTreeReader(new JsonArray(3)).nextLong());
  }

  /**
   * Test {@link JsonTreeReader#nextLong()}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#nextLong()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JsonTreeReader.nextLong()"})
  public void testNextLong_givenJsonPrimitiveWithBoolIsTrue_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonTreeReader(new JsonPrimitive(true)).nextLong());
  }

  /**
   * Test {@link JsonTreeReader#nextLong()}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(String)} with string is {@code 42}.
   *   <li>Then return forty-two.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#nextLong()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JsonTreeReader.nextLong()"})
  public void testNextLong_givenJsonPrimitiveWithStringIs42_thenReturnFortyTwo()
      throws IOException {
    // Arrange
    JsonTreeReader jsonTreeReader = new JsonTreeReader(new JsonPrimitive("42"));

    // Act and Assert
    assertEquals(42L, jsonTreeReader.nextLong());
    assertFalse(jsonTreeReader.hasNext());
  }

  /**
   * Test {@link JsonTreeReader#nextLong()}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#nextLong()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JsonTreeReader.nextLong()"})
  public void testNextLong_givenJsonTreeReaderWithElementIsInstance() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonTreeReader(JsonNull.INSTANCE).nextLong());
  }

  /**
   * Test {@link JsonTreeReader#nextLong()}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonObject} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#nextLong()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JsonTreeReader.nextLong()"})
  public void testNextLong_givenJsonTreeReaderWithElementIsJsonObject() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonTreeReader(new JsonObject()).nextLong());
  }

  /**
   * Test {@link JsonTreeReader#nextInt()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#nextInt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonTreeReader.nextInt()"})
  public void testNextInt_givenJsonArrayWithCapacityIsThree_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> new JsonTreeReader(new JsonArray(3)).nextInt());
  }

  /**
   * Test {@link JsonTreeReader#nextInt()}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#nextInt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonTreeReader.nextInt()"})
  public void testNextInt_givenJsonPrimitiveWithBoolIsTrue_thenThrowIllegalStateException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonTreeReader(new JsonPrimitive(true)).nextInt());
  }

  /**
   * Test {@link JsonTreeReader#nextInt()}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(String)} with string is {@code 42}.
   *   <li>Then return forty-two.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#nextInt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonTreeReader.nextInt()"})
  public void testNextInt_givenJsonPrimitiveWithStringIs42_thenReturnFortyTwo() throws IOException {
    // Arrange
    JsonTreeReader jsonTreeReader = new JsonTreeReader(new JsonPrimitive("42"));

    // Act and Assert
    assertEquals(42, jsonTreeReader.nextInt());
    assertFalse(jsonTreeReader.hasNext());
  }

  /**
   * Test {@link JsonTreeReader#nextInt()}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#nextInt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonTreeReader.nextInt()"})
  public void testNextInt_givenJsonTreeReaderWithElementIsInstance() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonTreeReader(JsonNull.INSTANCE).nextInt());
  }

  /**
   * Test {@link JsonTreeReader#nextInt()}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonObject} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#nextInt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonTreeReader.nextInt()"})
  public void testNextInt_givenJsonTreeReaderWithElementIsJsonObject() throws IOException {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> new JsonTreeReader(new JsonObject()).nextInt());
  }

  /**
   * Test {@link JsonTreeReader#nextJsonElement()}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE}.
   *   <li>Then return {@link JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#nextJsonElement()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonTreeReader.nextJsonElement()"})
  public void testNextJsonElement_givenJsonTreeReaderWithElementIsInstance_thenReturnInstance()
      throws IOException {
    // Arrange and Act
    JsonElement actualNextJsonElementResult =
        new JsonTreeReader(JsonNull.INSTANCE).nextJsonElement();

    // Assert
    assertSame(((JsonNull) actualNextJsonElementResult).INSTANCE, actualNextJsonElementResult);
  }

  /**
   * Test {@link JsonTreeReader#nextJsonElement()}.
   *
   * <ul>
   *   <li>Then return {@link JsonArray#JsonArray(int)} with capacity is three.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#nextJsonElement()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonTreeReader.nextJsonElement()"})
  public void testNextJsonElement_thenReturnJsonArrayWithCapacityIsThree() throws IOException {
    // Arrange
    JsonArray element = new JsonArray(3);

    // Act and Assert
    assertSame(element, new JsonTreeReader(element).nextJsonElement());
  }

  /**
   * Test {@link JsonTreeReader#nextJsonElement()}.
   *
   * <ul>
   *   <li>Then return {@link JsonObject} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#nextJsonElement()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonTreeReader.nextJsonElement()"})
  public void testNextJsonElement_thenReturnJsonObject() throws IOException {
    // Arrange
    JsonObject element = new JsonObject();

    // Act and Assert
    assertSame(element, new JsonTreeReader(element).nextJsonElement());
  }

  /**
   * Test {@link JsonTreeReader#nextJsonElement()}.
   *
   * <ul>
   *   <li>Then return {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#nextJsonElement()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonTreeReader.nextJsonElement()"})
  public void testNextJsonElement_thenReturnJsonPrimitiveWithBoolIsTrue() throws IOException {
    // Arrange
    JsonPrimitive element = new JsonPrimitive(true);

    // Act and Assert
    assertSame(element, new JsonTreeReader(element).nextJsonElement());
  }

  /**
   * Test {@link JsonTreeReader#nextJsonElement()}.
   *
   * <ul>
   *   <li>Then return {@link JsonPrimitive#JsonPrimitive(String)} with {@code String}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#nextJsonElement()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonTreeReader.nextJsonElement()"})
  public void testNextJsonElement_thenReturnJsonPrimitiveWithString() throws IOException {
    // Arrange
    JsonPrimitive element = new JsonPrimitive("String");

    // Act and Assert
    assertSame(element, new JsonTreeReader(element).nextJsonElement());
  }

  /**
   * Test {@link JsonTreeReader#skipValue()}.
   *
   * <ul>
   *   <li>Then not {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE} hasNext.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#skipValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonTreeReader.skipValue()"})
  public void testSkipValue_thenNotJsonTreeReaderWithElementIsInstanceHasNext() throws IOException {
    // Arrange
    JsonTreeReader jsonTreeReader = new JsonTreeReader(JsonNull.INSTANCE);

    // Act
    jsonTreeReader.skipValue();

    // Assert
    assertFalse(jsonTreeReader.hasNext());
  }

  /**
   * Test {@link JsonTreeReader#skipValue()}.
   *
   * <ul>
   *   <li>Then not {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonArray#JsonArray(int)} hasNext.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#skipValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonTreeReader.skipValue()"})
  public void testSkipValue_thenNotJsonTreeReaderWithElementIsJsonArrayHasNext()
      throws IOException {
    // Arrange
    JsonTreeReader jsonTreeReader = new JsonTreeReader(new JsonArray(3));

    // Act
    jsonTreeReader.skipValue();

    // Assert
    assertFalse(jsonTreeReader.hasNext());
  }

  /**
   * Test {@link JsonTreeReader#skipValue()}.
   *
   * <ul>
   *   <li>Then not {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonObject} (default constructor) hasNext.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#skipValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonTreeReader.skipValue()"})
  public void testSkipValue_thenNotJsonTreeReaderWithElementIsJsonObjectHasNext()
      throws IOException {
    // Arrange
    JsonTreeReader jsonTreeReader = new JsonTreeReader(new JsonObject());

    // Act
    jsonTreeReader.skipValue();

    // Assert
    assertFalse(jsonTreeReader.hasNext());
  }

  /**
   * Test {@link JsonTreeReader#skipValue()}.
   *
   * <ul>
   *   <li>Then not {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonPrimitive#JsonPrimitive(String)} hasNext.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#skipValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonTreeReader.skipValue()"})
  public void testSkipValue_thenNotJsonTreeReaderWithElementIsJsonPrimitiveHasNext()
      throws IOException {
    // Arrange
    JsonTreeReader jsonTreeReader = new JsonTreeReader(new JsonPrimitive("String"));

    // Act
    jsonTreeReader.skipValue();

    // Assert
    assertFalse(jsonTreeReader.hasNext());
  }

  /**
   * Test {@link JsonTreeReader#skipValue()}.
   *
   * <ul>
   *   <li>Then not {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonPrimitive#JsonPrimitive(Boolean)} hasNext.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#skipValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonTreeReader.skipValue()"})
  public void testSkipValue_thenNotJsonTreeReaderWithElementIsJsonPrimitiveHasNext2()
      throws IOException {
    // Arrange
    JsonTreeReader jsonTreeReader = new JsonTreeReader(new JsonPrimitive(true));

    // Act
    jsonTreeReader.skipValue();

    // Assert
    assertFalse(jsonTreeReader.hasNext());
  }

  /**
   * Test {@link JsonTreeReader#toString()}.
   *
   * <p>Method under test: {@link JsonTreeReader#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonTreeReader.toString()"})
  public void testToString() {
    // Arrange, Act and Assert
    assertEquals("JsonTreeReader at path $", new JsonTreeReader(JsonNull.INSTANCE).toString());
  }

  /**
   * Test {@link JsonTreeReader#promoteNameToValue()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#promoteNameToValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonTreeReader.promoteNameToValue()"})
  public void testPromoteNameToValue_givenJsonArrayWithCapacityIsThree() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> new JsonTreeReader(new JsonArray(3)).promoteNameToValue());
  }

  /**
   * Test {@link JsonTreeReader#promoteNameToValue()}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#promoteNameToValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonTreeReader.promoteNameToValue()"})
  public void testPromoteNameToValue_givenJsonPrimitiveWithBoolIsTrue() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> new JsonTreeReader(new JsonPrimitive(true)).promoteNameToValue());
  }

  /**
   * Test {@link JsonTreeReader#promoteNameToValue()}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(String)} with string is {@code Expected}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#promoteNameToValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonTreeReader.promoteNameToValue()"})
  public void testPromoteNameToValue_givenJsonPrimitiveWithStringIsExpected() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> new JsonTreeReader(new JsonPrimitive("Expected ")).promoteNameToValue());
  }

  /**
   * Test {@link JsonTreeReader#promoteNameToValue()}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#promoteNameToValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonTreeReader.promoteNameToValue()"})
  public void testPromoteNameToValue_givenJsonTreeReaderWithElementIsInstance() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> new JsonTreeReader(JsonNull.INSTANCE).promoteNameToValue());
  }

  /**
   * Test {@link JsonTreeReader#promoteNameToValue()}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonObject} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#promoteNameToValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonTreeReader.promoteNameToValue()"})
  public void testPromoteNameToValue_givenJsonTreeReaderWithElementIsJsonObject()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> new JsonTreeReader(new JsonObject()).promoteNameToValue());
  }

  /**
   * Test {@link JsonTreeReader#getPath()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#getPath()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonTreeReader.getPath()"})
  public void testGetPath_givenJsonArrayWithCapacityIsThree() {
    // Arrange, Act and Assert
    assertEquals("$", new JsonTreeReader(new JsonArray(3)).getPath());
  }

  /**
   * Test {@link JsonTreeReader#getPath()}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#getPath()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonTreeReader.getPath()"})
  public void testGetPath_givenJsonTreeReaderWithElementIsInstance() {
    // Arrange, Act and Assert
    assertEquals("$", new JsonTreeReader(JsonNull.INSTANCE).getPath());
  }

  /**
   * Test {@link JsonTreeReader#getPath()}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonObject} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#getPath()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonTreeReader.getPath()"})
  public void testGetPath_givenJsonTreeReaderWithElementIsJsonObject() {
    // Arrange, Act and Assert
    assertEquals("$", new JsonTreeReader(new JsonObject()).getPath());
  }

  /**
   * Test {@link JsonTreeReader#getPreviousPath()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#getPreviousPath()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonTreeReader.getPreviousPath()"})
  public void testGetPreviousPath_givenJsonArrayWithCapacityIsThree() {
    // Arrange, Act and Assert
    assertEquals("$", new JsonTreeReader(new JsonArray(3)).getPreviousPath());
  }

  /**
   * Test {@link JsonTreeReader#getPreviousPath()}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#getPreviousPath()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonTreeReader.getPreviousPath()"})
  public void testGetPreviousPath_givenJsonTreeReaderWithElementIsInstance() {
    // Arrange, Act and Assert
    assertEquals("$", new JsonTreeReader(JsonNull.INSTANCE).getPreviousPath());
  }

  /**
   * Test {@link JsonTreeReader#getPreviousPath()}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonObject} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeReader#getPreviousPath()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonTreeReader.getPreviousPath()"})
  public void testGetPreviousPath_givenJsonTreeReaderWithElementIsJsonObject() {
    // Arrange, Act and Assert
    assertEquals("$", new JsonTreeReader(new JsonObject()).getPreviousPath());
  }
}
