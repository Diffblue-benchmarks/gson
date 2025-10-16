package com.google.gson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JsonElementDiffblueTest {
  /**
   * Test {@link JsonElement#isJsonArray()}.
   *
   * <p>Method under test: {@link JsonElement#isJsonArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonElement.isJsonArray()"})
  public void testIsJsonArray() {
    // Arrange, Act and Assert
    assertTrue(JsonArrayTestFactory.createJsonArrayWithOneElement().isJsonArray());
  }

  /**
   * Test {@link JsonElement#isJsonObject()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithOneElement.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#isJsonObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonElement.isJsonObject()"})
  public void testIsJsonObject_givenCreateJsonArrayWithOneElement_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(JsonArrayTestFactory.createJsonArrayWithOneElement().isJsonObject());
  }

  /**
   * Test {@link JsonElement#isJsonObject()}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor).
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#isJsonObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonElement.isJsonObject()"})
  public void testIsJsonObject_givenJsonObject_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new JsonObject().isJsonObject());
  }

  /**
   * Test {@link JsonElement#isJsonPrimitive()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithOneElement.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#isJsonPrimitive()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonElement.isJsonPrimitive()"})
  public void testIsJsonPrimitive_givenCreateJsonArrayWithOneElement_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(JsonArrayTestFactory.createJsonArrayWithOneElement().isJsonPrimitive());
  }

  /**
   * Test {@link JsonElement#isJsonPrimitive()}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(String)} with {@code String}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#isJsonPrimitive()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonElement.isJsonPrimitive()"})
  public void testIsJsonPrimitive_givenJsonPrimitiveWithString_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new JsonPrimitive("String").isJsonPrimitive());
  }

  /**
   * Test {@link JsonElement#isJsonNull()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithOneElement.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#isJsonNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonElement.isJsonNull()"})
  public void testIsJsonNull_givenCreateJsonArrayWithOneElement_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(JsonArrayTestFactory.createJsonArrayWithOneElement().isJsonNull());
  }

  /**
   * Test {@link JsonElement#isJsonNull()}.
   *
   * <ul>
   *   <li>Given {@link JsonNull#INSTANCE}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#isJsonNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonElement.isJsonNull()"})
  public void testIsJsonNull_givenInstance_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(JsonNull.INSTANCE.isJsonNull());
  }

  /**
   * Test {@link JsonElement#getAsJsonObject()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithBooleans.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonElement.getAsJsonObject()"})
  public void testGetAsJsonObject_givenCreateJsonArrayWithBooleans() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> JsonArrayTestFactory.createJsonArrayWithBooleans().getAsJsonObject());
  }

  /**
   * Test {@link JsonElement#getAsJsonObject()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithMixedTypes.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonElement.getAsJsonObject()"})
  public void testGetAsJsonObject_givenCreateJsonArrayWithMixedTypes() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> JsonArrayTestFactory.createJsonArrayWithMixedTypes().getAsJsonObject());
  }

  /**
   * Test {@link JsonElement#getAsJsonObject()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithNumbers.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonElement.getAsJsonObject()"})
  public void testGetAsJsonObject_givenCreateJsonArrayWithNumbers() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> JsonArrayTestFactory.createJsonArrayWithNumbers().getAsJsonObject());
  }

  /**
   * Test {@link JsonElement#getAsJsonObject()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithOneElement.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonElement.getAsJsonObject()"})
  public void testGetAsJsonObject_givenCreateJsonArrayWithOneElement() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> JsonArrayTestFactory.createJsonArrayWithOneElement().getAsJsonObject());
  }

  /**
   * Test {@link JsonElement#getAsJsonObject()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithOneElement add end of text.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonElement.getAsJsonObject()"})
  public void testGetAsJsonObject_givenCreateJsonArrayWithOneElementAddEndOfText() {
    // Arrange
    JsonArray createJsonArrayWithOneElementResult =
        JsonArrayTestFactory.createJsonArrayWithOneElement();
    createJsonArrayWithOneElementResult.add('\u0003');

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> createJsonArrayWithOneElementResult.getAsJsonObject());
  }

  /**
   * Test {@link JsonElement#getAsJsonObject()}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor).
   *   <li>Then return {@link JsonObject} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonElement.getAsJsonObject()"})
  public void testGetAsJsonObject_givenJsonObject_thenReturnJsonObject() {
    // Arrange
    JsonObject jsonObject = new JsonObject();

    // Act
    JsonObject actualAsJsonObject = jsonObject.getAsJsonObject();

    // Assert
    assertSame(jsonObject, actualAsJsonObject);
  }

  /**
   * Test {@link JsonElement#getAsJsonArray()}.
   *
   * <p>Method under test: {@link JsonElement#getAsJsonArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonArray JsonElement.getAsJsonArray()"})
  public void testGetAsJsonArray() {
    // Arrange
    JsonArray createJsonArrayWithOneElementResult =
        JsonArrayTestFactory.createJsonArrayWithOneElement();

    // Act
    JsonArray actualAsJsonArray = createJsonArrayWithOneElementResult.getAsJsonArray();

    // Assert
    assertSame(createJsonArrayWithOneElementResult, actualAsJsonArray);
  }

  /**
   * Test {@link JsonElement#getAsJsonPrimitive()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithBooleans.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonPrimitive()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonPrimitive JsonElement.getAsJsonPrimitive()"})
  public void testGetAsJsonPrimitive_givenCreateJsonArrayWithBooleans() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> JsonArrayTestFactory.createJsonArrayWithBooleans().getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElement#getAsJsonPrimitive()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithMixedTypes.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonPrimitive()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonPrimitive JsonElement.getAsJsonPrimitive()"})
  public void testGetAsJsonPrimitive_givenCreateJsonArrayWithMixedTypes() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> JsonArrayTestFactory.createJsonArrayWithMixedTypes().getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElement#getAsJsonPrimitive()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithNumbers.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonPrimitive()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonPrimitive JsonElement.getAsJsonPrimitive()"})
  public void testGetAsJsonPrimitive_givenCreateJsonArrayWithNumbers() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> JsonArrayTestFactory.createJsonArrayWithNumbers().getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElement#getAsJsonPrimitive()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithOneElement.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonPrimitive()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonPrimitive JsonElement.getAsJsonPrimitive()"})
  public void testGetAsJsonPrimitive_givenCreateJsonArrayWithOneElement() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> JsonArrayTestFactory.createJsonArrayWithOneElement().getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElement#getAsJsonPrimitive()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithOneElement add end of text.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonPrimitive()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonPrimitive JsonElement.getAsJsonPrimitive()"})
  public void testGetAsJsonPrimitive_givenCreateJsonArrayWithOneElementAddEndOfText() {
    // Arrange
    JsonArray createJsonArrayWithOneElementResult =
        JsonArrayTestFactory.createJsonArrayWithOneElement();
    createJsonArrayWithOneElementResult.add('\u0003');

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> createJsonArrayWithOneElementResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElement#getAsJsonPrimitive()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithOneElement add {@link JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonPrimitive()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonPrimitive JsonElement.getAsJsonPrimitive()"})
  public void testGetAsJsonPrimitive_givenCreateJsonArrayWithOneElementAddInstance() {
    // Arrange
    JsonArray createJsonArrayWithOneElementResult =
        JsonArrayTestFactory.createJsonArrayWithOneElement();
    createJsonArrayWithOneElementResult.add(JsonNull.INSTANCE);

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> createJsonArrayWithOneElementResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElement#getAsJsonNull()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithBooleans.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonNull JsonElement.getAsJsonNull()"})
  public void testGetAsJsonNull_givenCreateJsonArrayWithBooleans() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> JsonArrayTestFactory.createJsonArrayWithBooleans().getAsJsonNull());
  }

  /**
   * Test {@link JsonElement#getAsJsonNull()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithMixedTypes.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonNull JsonElement.getAsJsonNull()"})
  public void testGetAsJsonNull_givenCreateJsonArrayWithMixedTypes() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> JsonArrayTestFactory.createJsonArrayWithMixedTypes().getAsJsonNull());
  }

  /**
   * Test {@link JsonElement#getAsJsonNull()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithNumbers.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonNull JsonElement.getAsJsonNull()"})
  public void testGetAsJsonNull_givenCreateJsonArrayWithNumbers_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> JsonArrayTestFactory.createJsonArrayWithNumbers().getAsJsonNull());
  }

  /**
   * Test {@link JsonElement#getAsJsonNull()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithOneElement.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonNull JsonElement.getAsJsonNull()"})
  public void testGetAsJsonNull_givenCreateJsonArrayWithOneElement() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> JsonArrayTestFactory.createJsonArrayWithOneElement().getAsJsonNull());
  }

  /**
   * Test {@link JsonElement#getAsJsonNull()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithOneElement add end of text.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonNull JsonElement.getAsJsonNull()"})
  public void testGetAsJsonNull_givenCreateJsonArrayWithOneElementAddEndOfText() {
    // Arrange
    JsonArray createJsonArrayWithOneElementResult =
        JsonArrayTestFactory.createJsonArrayWithOneElement();
    createJsonArrayWithOneElementResult.add('\u0003');

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> createJsonArrayWithOneElementResult.getAsJsonNull());
  }

  /**
   * Test {@link JsonElement#getAsJsonNull()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithOneElement add {@link JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonNull JsonElement.getAsJsonNull()"})
  public void testGetAsJsonNull_givenCreateJsonArrayWithOneElementAddInstance() {
    // Arrange
    JsonArray createJsonArrayWithOneElementResult =
        JsonArrayTestFactory.createJsonArrayWithOneElement();
    createJsonArrayWithOneElementResult.add(JsonNull.INSTANCE);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> createJsonArrayWithOneElementResult.getAsJsonNull());
  }

  /**
   * Test {@link JsonElement#getAsJsonNull()}.
   *
   * <ul>
   *   <li>Given {@link JsonNull#INSTANCE}.
   *   <li>Then return {@link JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonNull JsonElement.getAsJsonNull()"})
  public void testGetAsJsonNull_givenInstance_thenReturnInstance() {
    // Arrange, Act and Assert
    assertSame(JsonNull.INSTANCE, JsonNull.INSTANCE.getAsJsonNull());
  }

  /**
   * Test {@link JsonElement#getAsBoolean()}.
   *
   * <ul>
   *   <li>Given createEmptyJsonArray add {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonElement.getAsBoolean()"})
  public void testGetAsBoolean_givenCreateEmptyJsonArrayAddTrue_thenReturnTrue() {
    // Arrange
    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();
    createEmptyJsonArrayResult.add(true);

    // Act and Assert
    assertTrue(createEmptyJsonArrayResult.getAsBoolean());
  }

  /**
   * Test {@link JsonElement#getAsBoolean()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithOneElement.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonElement.getAsBoolean()"})
  public void testGetAsBoolean_givenCreateJsonArrayWithOneElement_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(JsonArrayTestFactory.createJsonArrayWithOneElement().getAsBoolean());
  }

  /**
   * Test {@link JsonElement#getAsBoolean()}.
   *
   * <ul>
   *   <li>Given {@link JsonNull#INSTANCE}.
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonElement.getAsBoolean()"})
  public void testGetAsBoolean_givenInstance_thenThrowUnsupportedOperationException() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> JsonNull.INSTANCE.getAsBoolean());
  }

  /**
   * Test {@link JsonElement#getAsNumber()}.
   *
   * <p>Method under test: {@link JsonElement#getAsNumber()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Number JsonElement.getAsNumber()"})
  public void testGetAsNumber() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> JsonNull.INSTANCE.getAsNumber());
  }

  /**
   * Test {@link JsonElement#getAsString()}.
   *
   * <p>Method under test: {@link JsonElement#getAsString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonElement.getAsString()"})
  public void testGetAsString() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> JsonNull.INSTANCE.getAsString());
  }

  /**
   * Test {@link JsonElement#getAsDouble()}.
   *
   * <p>Method under test: {@link JsonElement#getAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JsonElement.getAsDouble()"})
  public void testGetAsDouble() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> JsonNull.INSTANCE.getAsDouble());
  }

  /**
   * Test {@link JsonElement#getAsFloat()}.
   *
   * <p>Method under test: {@link JsonElement#getAsFloat()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"float JsonElement.getAsFloat()"})
  public void testGetAsFloat() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> JsonNull.INSTANCE.getAsFloat());
  }

  /**
   * Test {@link JsonElement#getAsLong()}.
   *
   * <p>Method under test: {@link JsonElement#getAsLong()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JsonElement.getAsLong()"})
  public void testGetAsLong() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> JsonNull.INSTANCE.getAsLong());
  }

  /**
   * Test {@link JsonElement#getAsInt()}.
   *
   * <p>Method under test: {@link JsonElement#getAsInt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonElement.getAsInt()"})
  public void testGetAsInt() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> JsonNull.INSTANCE.getAsInt());
  }

  /**
   * Test {@link JsonElement#getAsByte()}.
   *
   * <p>Method under test: {@link JsonElement#getAsByte()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte JsonElement.getAsByte()"})
  public void testGetAsByte() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> JsonNull.INSTANCE.getAsByte());
  }

  /**
   * Test {@link JsonElement#getAsCharacter()}.
   *
   * <p>Method under test: {@link JsonElement#getAsCharacter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"char JsonElement.getAsCharacter()"})
  public void testGetAsCharacter() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> JsonNull.INSTANCE.getAsCharacter());
  }

  /**
   * Test {@link JsonElement#getAsBigDecimal()}.
   *
   * <p>Method under test: {@link JsonElement#getAsBigDecimal()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.math.BigDecimal JsonElement.getAsBigDecimal()"})
  public void testGetAsBigDecimal() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> JsonNull.INSTANCE.getAsBigDecimal());
  }

  /**
   * Test {@link JsonElement#getAsBigInteger()}.
   *
   * <p>Method under test: {@link JsonElement#getAsBigInteger()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.math.BigInteger JsonElement.getAsBigInteger()"})
  public void testGetAsBigInteger() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> JsonNull.INSTANCE.getAsBigInteger());
  }

  /**
   * Test {@link JsonElement#getAsShort()}.
   *
   * <p>Method under test: {@link JsonElement#getAsShort()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"short JsonElement.getAsShort()"})
  public void testGetAsShort() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> JsonNull.INSTANCE.getAsShort());
  }

  /**
   * Test {@link JsonElement#toString()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithBooleans.
   *   <li>Then return {@code [true,false]}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonElement.toString()"})
  public void testToString_givenCreateJsonArrayWithBooleans_thenReturnTrueFalse() {
    // Arrange, Act and Assert
    assertEquals("[true,false]", JsonArrayTestFactory.createJsonArrayWithBooleans().toString());
  }

  /**
   * Test {@link JsonElement#toString()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithMixedTypes.
   *   <li>Then return {@code ["string",42,true,{}]}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonElement.toString()"})
  public void testToString_givenCreateJsonArrayWithMixedTypes_thenReturnString42True() {
    // Arrange, Act and Assert
    assertEquals(
        "[\"string\",42,true,{}]", JsonArrayTestFactory.createJsonArrayWithMixedTypes().toString());
  }

  /**
   * Test {@link JsonElement#toString()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithNumbers add acknowledge.
   *   <li>Then return {@code [1,2,3,"\u0006"]}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonElement.toString()"})
  public void testToString_givenCreateJsonArrayWithNumbersAddAcknowledge_thenReturn123U0006() {
    // Arrange
    JsonArray createJsonArrayWithNumbersResult = JsonArrayTestFactory.createJsonArrayWithNumbers();
    createJsonArrayWithNumbersResult.add('\u0006');

    // Act and Assert
    assertEquals("[1,2,3,\"\\u0006\"]", createJsonArrayWithNumbersResult.toString());
  }

  /**
   * Test {@link JsonElement#toString()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithNumbers.
   *   <li>Then return {@code [1,2,3]}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonElement.toString()"})
  public void testToString_givenCreateJsonArrayWithNumbers_thenReturn123() {
    // Arrange, Act and Assert
    assertEquals("[1,2,3]", JsonArrayTestFactory.createJsonArrayWithNumbers().toString());
  }

  /**
   * Test {@link JsonElement#toString()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithOneElement.
   *   <li>Then return {@code ["singleElement"]}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonElement.toString()"})
  public void testToString_givenCreateJsonArrayWithOneElement_thenReturnSingleElement() {
    // Arrange, Act and Assert
    assertEquals(
        "[\"singleElement\"]", JsonArrayTestFactory.createJsonArrayWithOneElement().toString());
  }

  /**
   * Test {@link JsonElement#toString()}.
   *
   * <ul>
   *   <li>Then return {@code ["singleElement",null]}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonElement.toString()"})
  public void testToString_thenReturnSingleElementNull() {
    // Arrange
    JsonArray createJsonArrayWithOneElementResult =
        JsonArrayTestFactory.createJsonArrayWithOneElement();
    createJsonArrayWithOneElementResult.add(JsonNull.INSTANCE);

    // Act and Assert
    assertEquals("[\"singleElement\",null]", createJsonArrayWithOneElementResult.toString());
  }
}
