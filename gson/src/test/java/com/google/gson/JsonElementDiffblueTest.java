package com.google.gson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.internal.LazilyParsedNumber;
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
    assertTrue(JsonArrayDiffblueTestFactory.createJsonArrayWithElements().isJsonArray());
  }

  /**
   * Test {@link JsonElement#isJsonObject()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#isJsonObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonElement.isJsonObject()"})
  public void testIsJsonObject_givenCreateJsonArrayWithElements_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(JsonArrayDiffblueTestFactory.createJsonArrayWithElements().isJsonObject());
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
   *   <li>Given createJsonArrayWithElements.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#isJsonPrimitive()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonElement.isJsonPrimitive()"})
  public void testIsJsonPrimitive_givenCreateJsonArrayWithElements_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(JsonArrayDiffblueTestFactory.createJsonArrayWithElements().isJsonPrimitive());
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
   *   <li>Given createJsonArrayWithElements.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#isJsonNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonElement.isJsonNull()"})
  public void testIsJsonNull_givenCreateJsonArrayWithElements_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(JsonArrayDiffblueTestFactory.createJsonArrayWithElements().isJsonNull());
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
   * <p>Method under test: {@link JsonElement#getAsJsonObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonElement.getAsJsonObject()"})
  public void testGetAsJsonObject() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(new LazilyParsedNumber("42"));
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> createJsonArrayWithElementsResult.getAsJsonObject());
  }

  /**
   * Test {@link JsonElement#getAsJsonObject()}.
   *
   * <p>Method under test: {@link JsonElement#getAsJsonObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonElement.getAsJsonObject()"})
  public void testGetAsJsonObject2() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(new LazilyParsedNumber("-Infinity"));
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> createJsonArrayWithElementsResult.getAsJsonObject());
  }

  /**
   * Test {@link JsonElement#getAsJsonObject()}.
   *
   * <p>Method under test: {@link JsonElement#getAsJsonObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonElement.getAsJsonObject()"})
  public void testGetAsJsonObject3() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(new LazilyParsedNumber("Infinity"));
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> createJsonArrayWithElementsResult.getAsJsonObject());
  }

  /**
   * Test {@link JsonElement#getAsJsonObject()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonElement.getAsJsonObject()"})
  public void testGetAsJsonObject_givenCreateJsonArrayWithElements() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> JsonArrayDiffblueTestFactory.createJsonArrayWithElements().getAsJsonObject());
  }

  /**
   * Test {@link JsonElement#getAsJsonObject()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add {@code A}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonElement.getAsJsonObject()"})
  public void testGetAsJsonObject_givenCreateJsonArrayWithElementsAddA() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add((byte) 'A');
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> createJsonArrayWithElementsResult.getAsJsonObject());
  }

  /**
   * Test {@link JsonElement#getAsJsonObject()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add end of text.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonElement.getAsJsonObject()"})
  public void testGetAsJsonObject_givenCreateJsonArrayWithElementsAddEndOfText() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add('\u0003');

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> createJsonArrayWithElementsResult.getAsJsonObject());
  }

  /**
   * Test {@link JsonElement#getAsJsonObject()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonElement.getAsJsonObject()"})
  public void testGetAsJsonObject_givenCreateJsonArrayWithElementsAddFalse() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(false);
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> createJsonArrayWithElementsResult.getAsJsonObject());
  }

  /**
   * Test {@link JsonElement#getAsJsonObject()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonElement.getAsJsonObject()"})
  public void testGetAsJsonObject_givenCreateJsonArrayWithElementsAddNaN() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(Double.NaN);
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> createJsonArrayWithElementsResult.getAsJsonObject());
  }

  /**
   * Test {@link JsonElement#getAsJsonObject()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonElement.getAsJsonObject()"})
  public void testGetAsJsonObject_givenCreateJsonArrayWithElementsAddNull() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add((Number) null);
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> createJsonArrayWithElementsResult.getAsJsonObject());
  }

  /**
   * Test {@link JsonElement#getAsJsonObject()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add ten.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonElement.getAsJsonObject()"})
  public void testGetAsJsonObject_givenCreateJsonArrayWithElementsAddTen() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(10.0d);
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> createJsonArrayWithElementsResult.getAsJsonObject());
  }

  /**
   * Test {@link JsonElement#getAsJsonObject()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add ten.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonElement.getAsJsonObject()"})
  public void testGetAsJsonObject_givenCreateJsonArrayWithElementsAddTen2() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(10.0f);
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> createJsonArrayWithElementsResult.getAsJsonObject());
  }

  /**
   * Test {@link JsonElement#getAsJsonObject()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add three.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonElement.getAsJsonObject()"})
  public void testGetAsJsonObject_givenCreateJsonArrayWithElementsAddThree() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(3L);
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> createJsonArrayWithElementsResult.getAsJsonObject());
  }

  /**
   * Test {@link JsonElement#getAsJsonObject()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add three.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonElement.getAsJsonObject()"})
  public void testGetAsJsonObject_givenCreateJsonArrayWithElementsAddThree2() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add((short) 3);
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> createJsonArrayWithElementsResult.getAsJsonObject());
  }

  /**
   * Test {@link JsonElement#getAsJsonObject()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonElement.getAsJsonObject()"})
  public void testGetAsJsonObject_givenCreateJsonArrayWithElementsAddTrue() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> createJsonArrayWithElementsResult.getAsJsonObject());
  }

  /**
   * Test {@link JsonElement#getAsJsonObject()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add valueOf three.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonElement.getAsJsonObject()"})
  public void testGetAsJsonObject_givenCreateJsonArrayWithElementsAddValueOfThree() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(Integer.valueOf(3));
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> createJsonArrayWithElementsResult.getAsJsonObject());
  }

  /**
   * Test {@link JsonElement#getAsJsonObject()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray()}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonElement.getAsJsonObject()"})
  public void testGetAsJsonObject_givenJsonArray_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> new JsonArray().getAsJsonObject());
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
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();

    // Act
    JsonArray actualAsJsonArray = createJsonArrayWithElementsResult.getAsJsonArray();

    // Assert
    assertSame(createJsonArrayWithElementsResult, actualAsJsonArray);
  }

  /**
   * Test {@link JsonElement#getAsJsonPrimitive()}.
   *
   * <p>Method under test: {@link JsonElement#getAsJsonPrimitive()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonPrimitive JsonElement.getAsJsonPrimitive()"})
  public void testGetAsJsonPrimitive() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(new LazilyParsedNumber("42"));
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> createJsonArrayWithElementsResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElement#getAsJsonPrimitive()}.
   *
   * <p>Method under test: {@link JsonElement#getAsJsonPrimitive()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonPrimitive JsonElement.getAsJsonPrimitive()"})
  public void testGetAsJsonPrimitive2() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(new LazilyParsedNumber("-Infinity"));
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> createJsonArrayWithElementsResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElement#getAsJsonPrimitive()}.
   *
   * <p>Method under test: {@link JsonElement#getAsJsonPrimitive()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonPrimitive JsonElement.getAsJsonPrimitive()"})
  public void testGetAsJsonPrimitive3() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(new LazilyParsedNumber("Infinity"));
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> createJsonArrayWithElementsResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElement#getAsJsonPrimitive()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonPrimitive()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonPrimitive JsonElement.getAsJsonPrimitive()"})
  public void testGetAsJsonPrimitive_givenCreateJsonArrayWithElements() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> JsonArrayDiffblueTestFactory.createJsonArrayWithElements().getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElement#getAsJsonPrimitive()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add {@code A}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonPrimitive()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonPrimitive JsonElement.getAsJsonPrimitive()"})
  public void testGetAsJsonPrimitive_givenCreateJsonArrayWithElementsAddA() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add((byte) 'A');
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> createJsonArrayWithElementsResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElement#getAsJsonPrimitive()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add end of text.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonPrimitive()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonPrimitive JsonElement.getAsJsonPrimitive()"})
  public void testGetAsJsonPrimitive_givenCreateJsonArrayWithElementsAddEndOfText() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add('\u0003');

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> createJsonArrayWithElementsResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElement#getAsJsonPrimitive()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonPrimitive()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonPrimitive JsonElement.getAsJsonPrimitive()"})
  public void testGetAsJsonPrimitive_givenCreateJsonArrayWithElementsAddFalse() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(false);
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> createJsonArrayWithElementsResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElement#getAsJsonPrimitive()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonPrimitive()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonPrimitive JsonElement.getAsJsonPrimitive()"})
  public void testGetAsJsonPrimitive_givenCreateJsonArrayWithElementsAddNaN() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(Double.NaN);
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> createJsonArrayWithElementsResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElement#getAsJsonPrimitive()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonPrimitive()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonPrimitive JsonElement.getAsJsonPrimitive()"})
  public void testGetAsJsonPrimitive_givenCreateJsonArrayWithElementsAddNull() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add((Number) null);
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> createJsonArrayWithElementsResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElement#getAsJsonPrimitive()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add ten.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonPrimitive()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonPrimitive JsonElement.getAsJsonPrimitive()"})
  public void testGetAsJsonPrimitive_givenCreateJsonArrayWithElementsAddTen() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(10.0d);
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> createJsonArrayWithElementsResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElement#getAsJsonPrimitive()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add ten.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonPrimitive()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonPrimitive JsonElement.getAsJsonPrimitive()"})
  public void testGetAsJsonPrimitive_givenCreateJsonArrayWithElementsAddTen2() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(10.0f);
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> createJsonArrayWithElementsResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElement#getAsJsonPrimitive()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add three.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonPrimitive()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonPrimitive JsonElement.getAsJsonPrimitive()"})
  public void testGetAsJsonPrimitive_givenCreateJsonArrayWithElementsAddThree() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(3L);
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> createJsonArrayWithElementsResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElement#getAsJsonPrimitive()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add three.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonPrimitive()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonPrimitive JsonElement.getAsJsonPrimitive()"})
  public void testGetAsJsonPrimitive_givenCreateJsonArrayWithElementsAddThree2() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add((short) 3);
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> createJsonArrayWithElementsResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElement#getAsJsonPrimitive()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonPrimitive()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonPrimitive JsonElement.getAsJsonPrimitive()"})
  public void testGetAsJsonPrimitive_givenCreateJsonArrayWithElementsAddTrue() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> createJsonArrayWithElementsResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElement#getAsJsonPrimitive()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add valueOf three.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonPrimitive()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonPrimitive JsonElement.getAsJsonPrimitive()"})
  public void testGetAsJsonPrimitive_givenCreateJsonArrayWithElementsAddValueOfThree() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(Integer.valueOf(3));
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> createJsonArrayWithElementsResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElement#getAsJsonPrimitive()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray()}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonPrimitive()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonPrimitive JsonElement.getAsJsonPrimitive()"})
  public void testGetAsJsonPrimitive_givenJsonArray_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> new JsonArray().getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElement#getAsJsonNull()}.
   *
   * <p>Method under test: {@link JsonElement#getAsJsonNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonNull JsonElement.getAsJsonNull()"})
  public void testGetAsJsonNull() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(new LazilyParsedNumber("42"));
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> createJsonArrayWithElementsResult.getAsJsonNull());
  }

  /**
   * Test {@link JsonElement#getAsJsonNull()}.
   *
   * <p>Method under test: {@link JsonElement#getAsJsonNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonNull JsonElement.getAsJsonNull()"})
  public void testGetAsJsonNull2() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(new LazilyParsedNumber("-Infinity"));
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> createJsonArrayWithElementsResult.getAsJsonNull());
  }

  /**
   * Test {@link JsonElement#getAsJsonNull()}.
   *
   * <p>Method under test: {@link JsonElement#getAsJsonNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonNull JsonElement.getAsJsonNull()"})
  public void testGetAsJsonNull3() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(new LazilyParsedNumber("Infinity"));
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> createJsonArrayWithElementsResult.getAsJsonNull());
  }

  /**
   * Test {@link JsonElement#getAsJsonNull()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonNull JsonElement.getAsJsonNull()"})
  public void testGetAsJsonNull_givenCreateJsonArrayWithElements() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> JsonArrayDiffblueTestFactory.createJsonArrayWithElements().getAsJsonNull());
  }

  /**
   * Test {@link JsonElement#getAsJsonNull()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add {@code A}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonNull JsonElement.getAsJsonNull()"})
  public void testGetAsJsonNull_givenCreateJsonArrayWithElementsAddA() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add((byte) 'A');
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> createJsonArrayWithElementsResult.getAsJsonNull());
  }

  /**
   * Test {@link JsonElement#getAsJsonNull()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add end of text.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonNull JsonElement.getAsJsonNull()"})
  public void testGetAsJsonNull_givenCreateJsonArrayWithElementsAddEndOfText() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add('\u0003');

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> createJsonArrayWithElementsResult.getAsJsonNull());
  }

  /**
   * Test {@link JsonElement#getAsJsonNull()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonNull JsonElement.getAsJsonNull()"})
  public void testGetAsJsonNull_givenCreateJsonArrayWithElementsAddFalse() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(false);
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> createJsonArrayWithElementsResult.getAsJsonNull());
  }

  /**
   * Test {@link JsonElement#getAsJsonNull()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonNull JsonElement.getAsJsonNull()"})
  public void testGetAsJsonNull_givenCreateJsonArrayWithElementsAddNaN() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(Double.NaN);
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> createJsonArrayWithElementsResult.getAsJsonNull());
  }

  /**
   * Test {@link JsonElement#getAsJsonNull()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonNull JsonElement.getAsJsonNull()"})
  public void testGetAsJsonNull_givenCreateJsonArrayWithElementsAddNull() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add((Number) null);
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> createJsonArrayWithElementsResult.getAsJsonNull());
  }

  /**
   * Test {@link JsonElement#getAsJsonNull()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add ten.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonNull JsonElement.getAsJsonNull()"})
  public void testGetAsJsonNull_givenCreateJsonArrayWithElementsAddTen() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(10.0d);
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> createJsonArrayWithElementsResult.getAsJsonNull());
  }

  /**
   * Test {@link JsonElement#getAsJsonNull()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add ten.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonNull JsonElement.getAsJsonNull()"})
  public void testGetAsJsonNull_givenCreateJsonArrayWithElementsAddTen2() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(10.0f);
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> createJsonArrayWithElementsResult.getAsJsonNull());
  }

  /**
   * Test {@link JsonElement#getAsJsonNull()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add three.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonNull JsonElement.getAsJsonNull()"})
  public void testGetAsJsonNull_givenCreateJsonArrayWithElementsAddThree() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(3L);
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> createJsonArrayWithElementsResult.getAsJsonNull());
  }

  /**
   * Test {@link JsonElement#getAsJsonNull()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add three.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonNull JsonElement.getAsJsonNull()"})
  public void testGetAsJsonNull_givenCreateJsonArrayWithElementsAddThree2() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add((short) 3);
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> createJsonArrayWithElementsResult.getAsJsonNull());
  }

  /**
   * Test {@link JsonElement#getAsJsonNull()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonNull JsonElement.getAsJsonNull()"})
  public void testGetAsJsonNull_givenCreateJsonArrayWithElementsAddTrue() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> createJsonArrayWithElementsResult.getAsJsonNull());
  }

  /**
   * Test {@link JsonElement#getAsJsonNull()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add valueOf three.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonNull JsonElement.getAsJsonNull()"})
  public void testGetAsJsonNull_givenCreateJsonArrayWithElementsAddValueOfThree() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(Integer.valueOf(3));
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> createJsonArrayWithElementsResult.getAsJsonNull());
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
   * Test {@link JsonElement#getAsJsonNull()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray()}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonNull JsonElement.getAsJsonNull()"})
  public void testGetAsJsonNull_givenJsonArray_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> new JsonArray().getAsJsonNull());
  }

  /**
   * Test {@link JsonElement#getAsBoolean()}.
   *
   * <p>Method under test: {@link JsonElement#getAsBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonElement.getAsBoolean()"})
  public void testGetAsBoolean() {
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
  @MethodsUnderTest({"Number JsonElement.getAsNumber()"})
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
   *   <li>Given createJsonArrayWithElements add thirty-two.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonElement.toString()"})
  public void testToString_givenCreateJsonArrayWithElementsAddThirtyTwo() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(32L);
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertEquals(
        "[\"element0\",\"element1\",32,true]", createJsonArrayWithElementsResult.toString());
  }

  /**
   * Test {@link JsonElement#toString()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add thirty-two.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonElement.toString()"})
  public void testToString_givenCreateJsonArrayWithElementsAddThirtyTwo2() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add((short) 32);
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertEquals(
        "[\"element0\",\"element1\",32,true]", createJsonArrayWithElementsResult.toString());
  }

  /**
   * Test {@link JsonElement#toString()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add valueOf {@link Integer#SIZE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonElement.toString()"})
  public void testToString_givenCreateJsonArrayWithElementsAddValueOfSize() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(Integer.valueOf(Integer.SIZE));
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertEquals(
        "[\"element0\",\"element1\",32,true]", createJsonArrayWithElementsResult.toString());
  }

  /**
   * Test {@link JsonElement#toString()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements.
   *   <li>Then return {@code ["element0","element1"]}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonElement.toString()"})
  public void testToString_givenCreateJsonArrayWithElements_thenReturnElement0Element1() {
    // Arrange, Act and Assert
    assertEquals(
        "[\"element0\",\"element1\"]",
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements().toString());
  }

  /**
   * Test {@link JsonElement#toString()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray()}.
   *   <li>Then return {@code []}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonElement.toString()"})
  public void testToString_givenJsonArray_thenReturnLeftSquareBracketRightSquareBracket() {
    // Arrange, Act and Assert
    assertEquals("[]", new JsonArray().toString());
  }

  /**
   * Test {@link JsonElement#toString()}.
   *
   * <ul>
   *   <li>Then return {@code ["element0","element1",false,true]}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonElement.toString()"})
  public void testToString_thenReturnElement0Element1FalseTrue() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(false);
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertEquals(
        "[\"element0\",\"element1\",false,true]", createJsonArrayWithElementsResult.toString());
  }

  /**
   * Test {@link JsonElement#toString()}.
   *
   * <ul>
   *   <li>Then return {@code ["element0","element1",-Infinity,true]}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonElement.toString()"})
  public void testToString_thenReturnElement0Element1InfinityTrue() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(new LazilyParsedNumber("-Infinity"));
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertEquals(
        "[\"element0\",\"element1\",-Infinity,true]", createJsonArrayWithElementsResult.toString());
  }

  /**
   * Test {@link JsonElement#toString()}.
   *
   * <ul>
   *   <li>Then return {@code ["element0","element1",Infinity,true]}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonElement.toString()"})
  public void testToString_thenReturnElement0Element1InfinityTrue2() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(new LazilyParsedNumber("Infinity"));
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertEquals(
        "[\"element0\",\"element1\",Infinity,true]", createJsonArrayWithElementsResult.toString());
  }

  /**
   * Test {@link JsonElement#toString()}.
   *
   * <ul>
   *   <li>Then return {@code ["element0","element1",NaN,true]}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonElement.toString()"})
  public void testToString_thenReturnElement0Element1NaNTrue() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(Double.NaN);
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertEquals(
        "[\"element0\",\"element1\",NaN,true]", createJsonArrayWithElementsResult.toString());
  }

  /**
   * Test {@link JsonElement#toString()}.
   *
   * <ul>
   *   <li>Then return {@code ["element0","element1",null,true]}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonElement.toString()"})
  public void testToString_thenReturnElement0Element1NullTrue() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add((Number) null);
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertEquals(
        "[\"element0\",\"element1\",null,true]", createJsonArrayWithElementsResult.toString());
  }

  /**
   * Test {@link JsonElement#toString()}.
   *
   * <ul>
   *   <li>Then return {@code ["element0","element1",true]}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonElement.toString()"})
  public void testToString_thenReturnElement0Element1True() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertEquals("[\"element0\",\"element1\",true]", createJsonArrayWithElementsResult.toString());
  }

  /**
   * Test {@link JsonElement#toString()}.
   *
   * <ul>
   *   <li>Then return {@code ["element0","element1","\u0006",true]}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonElement.toString()"})
  public void testToString_thenReturnElement0Element1U0006True() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add('\u0006');
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertEquals(
        "[\"element0\",\"element1\",\"\\u0006\",true]",
        createJsonArrayWithElementsResult.toString());
  }

  /**
   * Test {@link JsonElement#toString()}.
   *
   * <ul>
   *   <li>Then return {@code ["element0","element1",42,true]}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonElement.toString()"})
  public void testToString_thenReturnElement0Element142True() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(new LazilyParsedNumber("42"));
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertEquals(
        "[\"element0\",\"element1\",42,true]", createJsonArrayWithElementsResult.toString());
  }

  /**
   * Test {@link JsonElement#toString()}.
   *
   * <ul>
   *   <li>Then return {@code ["element0","element1",65,true]}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonElement.toString()"})
  public void testToString_thenReturnElement0Element165True() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add((byte) 'A');
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertEquals(
        "[\"element0\",\"element1\",65,true]", createJsonArrayWithElementsResult.toString());
  }

  /**
   * Test {@link JsonElement#toString()}.
   *
   * <ul>
   *   <li>Then return {@code ["element0","element1",10.0,true]}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonElement.toString()"})
  public void testToString_thenReturnElement0Element1100True() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(10.0d);
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertEquals(
        "[\"element0\",\"element1\",10.0,true]", createJsonArrayWithElementsResult.toString());
  }

  /**
   * Test {@link JsonElement#toString()}.
   *
   * <ul>
   *   <li>Then return {@code ["element0","element1",10.0,true]}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonElement.toString()"})
  public void testToString_thenReturnElement0Element1100True2() {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(10.0f);
    createJsonArrayWithElementsResult.add(true);

    // Act and Assert
    assertEquals(
        "[\"element0\",\"element1\",10.0,true]", createJsonArrayWithElementsResult.toString());
  }
}
