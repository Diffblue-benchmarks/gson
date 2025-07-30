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
    assertTrue(new JsonArray(3).isJsonArray());
  }

  /**
   * Test {@link JsonElement#isJsonObject()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#isJsonObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonElement.isJsonObject()"})
  public void testIsJsonObject_givenJsonArrayWithCapacityIsThree_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new JsonArray(3).isJsonObject());
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
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#isJsonPrimitive()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonElement.isJsonPrimitive()"})
  public void testIsJsonPrimitive_givenJsonArrayWithCapacityIsThree_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new JsonArray(3).isJsonPrimitive());
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
   * Test {@link JsonElement#isJsonNull()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#isJsonNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonElement.isJsonNull()"})
  public void testIsJsonNull_givenJsonArrayWithCapacityIsThree_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new JsonArray(3).isJsonNull());
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
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new LazilyParsedNumber("42"));
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonObject());
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
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new LazilyParsedNumber("-Infinity"));
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonObject());
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
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new LazilyParsedNumber("Infinity"));
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonObject());
  }

  /**
   * Test {@link JsonElement#getAsJsonObject()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonElement.getAsJsonObject()"})
  public void testGetAsJsonObject_givenJsonArrayWithCapacityIsThree() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> new JsonArray(3).getAsJsonObject());
  }

  /**
   * Test {@link JsonElement#getAsJsonObject()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code A}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonElement.getAsJsonObject()"})
  public void testGetAsJsonObject_givenJsonArrayWithCapacityIsThreeAddA() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add((byte) 'A');
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonObject());
  }

  /**
   * Test {@link JsonElement#getAsJsonObject()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add end of text.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonElement.getAsJsonObject()"})
  public void testGetAsJsonObject_givenJsonArrayWithCapacityIsThreeAddEndOfText() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add('\u0003');

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonObject());
  }

  /**
   * Test {@link JsonElement#getAsJsonObject()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonElement.getAsJsonObject()"})
  public void testGetAsJsonObject_givenJsonArrayWithCapacityIsThreeAddFalse() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(false);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonObject());
  }

  /**
   * Test {@link JsonElement#getAsJsonObject()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonElement.getAsJsonObject()"})
  public void testGetAsJsonObject_givenJsonArrayWithCapacityIsThreeAddInstance() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(JsonNull.INSTANCE);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonObject());
  }

  /**
   * Test {@link JsonElement#getAsJsonObject()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@link JsonObject}
   *       (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonElement.getAsJsonObject()"})
  public void testGetAsJsonObject_givenJsonArrayWithCapacityIsThreeAddJsonObject() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new JsonObject());
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonObject());
  }

  /**
   * Test {@link JsonElement#getAsJsonObject()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonElement.getAsJsonObject()"})
  public void testGetAsJsonObject_givenJsonArrayWithCapacityIsThreeAddNaN() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(Double.NaN);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonObject());
  }

  /**
   * Test {@link JsonElement#getAsJsonObject()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add space.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonElement.getAsJsonObject()"})
  public void testGetAsJsonObject_givenJsonArrayWithCapacityIsThreeAddSpace() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(' ');
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonObject());
  }

  /**
   * Test {@link JsonElement#getAsJsonObject()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add ten.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonElement.getAsJsonObject()"})
  public void testGetAsJsonObject_givenJsonArrayWithCapacityIsThreeAddTen() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(10.0d);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonObject());
  }

  /**
   * Test {@link JsonElement#getAsJsonObject()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add ten.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonElement.getAsJsonObject()"})
  public void testGetAsJsonObject_givenJsonArrayWithCapacityIsThreeAddTen2() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(10.0f);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonObject());
  }

  /**
   * Test {@link JsonElement#getAsJsonObject()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add three.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonElement.getAsJsonObject()"})
  public void testGetAsJsonObject_givenJsonArrayWithCapacityIsThreeAddThree() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(3L);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonObject());
  }

  /**
   * Test {@link JsonElement#getAsJsonObject()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add three.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonElement.getAsJsonObject()"})
  public void testGetAsJsonObject_givenJsonArrayWithCapacityIsThreeAddThree2() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add((short) 3);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonObject());
  }

  /**
   * Test {@link JsonElement#getAsJsonObject()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonElement.getAsJsonObject()"})
  public void testGetAsJsonObject_givenJsonArrayWithCapacityIsThreeAddTrue() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonObject());
  }

  /**
   * Test {@link JsonElement#getAsJsonObject()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add valueOf three.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonElement.getAsJsonObject()"})
  public void testGetAsJsonObject_givenJsonArrayWithCapacityIsThreeAddValueOfThree() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(Integer.valueOf(3));
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonObject());
  }

  /**
   * Test {@link JsonElement#getAsJsonObject()}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code Not a JSON Object:} and {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonElement.getAsJsonObject()"})
  public void testGetAsJsonObject_givenJsonObjectAddNotAJsonObjectAndInstance() {
    // Arrange
    JsonObject element = new JsonObject();
    element.add("Not a JSON Object: ", JsonNull.INSTANCE);

    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(element);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonObject());
  }

  /**
   * Test {@link JsonElement#getAsJsonObject()}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code out == null} and {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonElement.getAsJsonObject()"})
  public void testGetAsJsonObject_givenJsonObjectAddOutNullAndInstance() {
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

    // Act and Assert
    assertSame(jsonObject, jsonObject.getAsJsonObject());
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
    JsonArray jsonArray = new JsonArray(3);

    // Act and Assert
    assertSame(jsonArray, jsonArray.getAsJsonArray());
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
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new LazilyParsedNumber("42"));
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonPrimitive());
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
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new LazilyParsedNumber("-Infinity"));
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonPrimitive());
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
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new LazilyParsedNumber("Infinity"));
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElement#getAsJsonPrimitive()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonPrimitive()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonPrimitive JsonElement.getAsJsonPrimitive()"})
  public void testGetAsJsonPrimitive_givenJsonArrayWithCapacityIsThree() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> new JsonArray(3).getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElement#getAsJsonPrimitive()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code A}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonPrimitive()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonPrimitive JsonElement.getAsJsonPrimitive()"})
  public void testGetAsJsonPrimitive_givenJsonArrayWithCapacityIsThreeAddA() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add((byte) 'A');
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElement#getAsJsonPrimitive()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add end of text.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonPrimitive()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonPrimitive JsonElement.getAsJsonPrimitive()"})
  public void testGetAsJsonPrimitive_givenJsonArrayWithCapacityIsThreeAddEndOfText() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add('\u0003');

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElement#getAsJsonPrimitive()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonPrimitive()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonPrimitive JsonElement.getAsJsonPrimitive()"})
  public void testGetAsJsonPrimitive_givenJsonArrayWithCapacityIsThreeAddFalse() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(false);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElement#getAsJsonPrimitive()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonPrimitive()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonPrimitive JsonElement.getAsJsonPrimitive()"})
  public void testGetAsJsonPrimitive_givenJsonArrayWithCapacityIsThreeAddInstance() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(JsonNull.INSTANCE);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElement#getAsJsonPrimitive()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@link JsonObject}
   *       (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonPrimitive()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonPrimitive JsonElement.getAsJsonPrimitive()"})
  public void testGetAsJsonPrimitive_givenJsonArrayWithCapacityIsThreeAddJsonObject() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new JsonObject());
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElement#getAsJsonPrimitive()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonPrimitive()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonPrimitive JsonElement.getAsJsonPrimitive()"})
  public void testGetAsJsonPrimitive_givenJsonArrayWithCapacityIsThreeAddNaN() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(Double.NaN);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElement#getAsJsonPrimitive()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add space.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonPrimitive()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonPrimitive JsonElement.getAsJsonPrimitive()"})
  public void testGetAsJsonPrimitive_givenJsonArrayWithCapacityIsThreeAddSpace() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(' ');
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElement#getAsJsonPrimitive()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add ten.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonPrimitive()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonPrimitive JsonElement.getAsJsonPrimitive()"})
  public void testGetAsJsonPrimitive_givenJsonArrayWithCapacityIsThreeAddTen() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(10.0d);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElement#getAsJsonPrimitive()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add ten.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonPrimitive()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonPrimitive JsonElement.getAsJsonPrimitive()"})
  public void testGetAsJsonPrimitive_givenJsonArrayWithCapacityIsThreeAddTen2() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(10.0f);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElement#getAsJsonPrimitive()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add three.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonPrimitive()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonPrimitive JsonElement.getAsJsonPrimitive()"})
  public void testGetAsJsonPrimitive_givenJsonArrayWithCapacityIsThreeAddThree() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(3L);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElement#getAsJsonPrimitive()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add three.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonPrimitive()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonPrimitive JsonElement.getAsJsonPrimitive()"})
  public void testGetAsJsonPrimitive_givenJsonArrayWithCapacityIsThreeAddThree2() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add((short) 3);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElement#getAsJsonPrimitive()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonPrimitive()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonPrimitive JsonElement.getAsJsonPrimitive()"})
  public void testGetAsJsonPrimitive_givenJsonArrayWithCapacityIsThreeAddTrue() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElement#getAsJsonPrimitive()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add valueOf three.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonPrimitive()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonPrimitive JsonElement.getAsJsonPrimitive()"})
  public void testGetAsJsonPrimitive_givenJsonArrayWithCapacityIsThreeAddValueOfThree() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(Integer.valueOf(3));
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElement#getAsJsonPrimitive()}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code Not a JSON Primitive:} and
   *       {@link JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonPrimitive()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonPrimitive JsonElement.getAsJsonPrimitive()"})
  public void testGetAsJsonPrimitive_givenJsonObjectAddNotAJsonPrimitiveAndInstance() {
    // Arrange
    JsonObject element = new JsonObject();
    element.add("Not a JSON Primitive: ", JsonNull.INSTANCE);

    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(element);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElement#getAsJsonPrimitive()}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code out == null} and {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonPrimitive()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonPrimitive JsonElement.getAsJsonPrimitive()"})
  public void testGetAsJsonPrimitive_givenJsonObjectAddOutNullAndInstance() {
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

  /**
   * Test {@link JsonElement#getAsJsonPrimitive()}.
   *
   * <ul>
   *   <li>Then return {@link JsonPrimitive#JsonPrimitive(String)} with {@code String}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonPrimitive()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonPrimitive JsonElement.getAsJsonPrimitive()"})
  public void testGetAsJsonPrimitive_thenReturnJsonPrimitiveWithString() {
    // Arrange
    JsonPrimitive jsonPrimitive = new JsonPrimitive("String");

    // Act and Assert
    assertSame(jsonPrimitive, jsonPrimitive.getAsJsonPrimitive());
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
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new LazilyParsedNumber("42"));
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonNull());
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
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new LazilyParsedNumber("-Infinity"));
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonNull());
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
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new LazilyParsedNumber("Infinity"));
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonNull());
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
    // Arrange and Act
    JsonNull actualAsJsonNull = JsonNull.INSTANCE.getAsJsonNull();

    // Assert
    assertSame(actualAsJsonNull.INSTANCE, actualAsJsonNull);
  }

  /**
   * Test {@link JsonElement#getAsJsonNull()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonNull JsonElement.getAsJsonNull()"})
  public void testGetAsJsonNull_givenJsonArrayWithCapacityIsThree() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> new JsonArray(3).getAsJsonNull());
  }

  /**
   * Test {@link JsonElement#getAsJsonNull()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code A}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonNull JsonElement.getAsJsonNull()"})
  public void testGetAsJsonNull_givenJsonArrayWithCapacityIsThreeAddA() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add((byte) 'A');
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonNull());
  }

  /**
   * Test {@link JsonElement#getAsJsonNull()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add end of text.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonNull JsonElement.getAsJsonNull()"})
  public void testGetAsJsonNull_givenJsonArrayWithCapacityIsThreeAddEndOfText() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add('\u0003');

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonNull());
  }

  /**
   * Test {@link JsonElement#getAsJsonNull()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonNull JsonElement.getAsJsonNull()"})
  public void testGetAsJsonNull_givenJsonArrayWithCapacityIsThreeAddFalse() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(false);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonNull());
  }

  /**
   * Test {@link JsonElement#getAsJsonNull()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonNull JsonElement.getAsJsonNull()"})
  public void testGetAsJsonNull_givenJsonArrayWithCapacityIsThreeAddInstance() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(JsonNull.INSTANCE);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonNull());
  }

  /**
   * Test {@link JsonElement#getAsJsonNull()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@link JsonObject}
   *       (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonNull JsonElement.getAsJsonNull()"})
  public void testGetAsJsonNull_givenJsonArrayWithCapacityIsThreeAddJsonObject() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new JsonObject());
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonNull());
  }

  /**
   * Test {@link JsonElement#getAsJsonNull()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonNull JsonElement.getAsJsonNull()"})
  public void testGetAsJsonNull_givenJsonArrayWithCapacityIsThreeAddNaN() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(Double.NaN);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonNull());
  }

  /**
   * Test {@link JsonElement#getAsJsonNull()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add space.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonNull JsonElement.getAsJsonNull()"})
  public void testGetAsJsonNull_givenJsonArrayWithCapacityIsThreeAddSpace() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(' ');
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonNull());
  }

  /**
   * Test {@link JsonElement#getAsJsonNull()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add ten.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonNull JsonElement.getAsJsonNull()"})
  public void testGetAsJsonNull_givenJsonArrayWithCapacityIsThreeAddTen() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(10.0d);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonNull());
  }

  /**
   * Test {@link JsonElement#getAsJsonNull()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add ten.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonNull JsonElement.getAsJsonNull()"})
  public void testGetAsJsonNull_givenJsonArrayWithCapacityIsThreeAddTen2() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(10.0f);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonNull());
  }

  /**
   * Test {@link JsonElement#getAsJsonNull()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add three.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonNull JsonElement.getAsJsonNull()"})
  public void testGetAsJsonNull_givenJsonArrayWithCapacityIsThreeAddThree() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(3L);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonNull());
  }

  /**
   * Test {@link JsonElement#getAsJsonNull()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add three.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonNull JsonElement.getAsJsonNull()"})
  public void testGetAsJsonNull_givenJsonArrayWithCapacityIsThreeAddThree2() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add((short) 3);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonNull());
  }

  /**
   * Test {@link JsonElement#getAsJsonNull()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonNull JsonElement.getAsJsonNull()"})
  public void testGetAsJsonNull_givenJsonArrayWithCapacityIsThreeAddTrue() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonNull());
  }

  /**
   * Test {@link JsonElement#getAsJsonNull()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add valueOf three.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonNull JsonElement.getAsJsonNull()"})
  public void testGetAsJsonNull_givenJsonArrayWithCapacityIsThreeAddValueOfThree() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(Integer.valueOf(3));
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonNull());
  }

  /**
   * Test {@link JsonElement#getAsJsonNull()}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code Not a JSON Null:} and {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonNull JsonElement.getAsJsonNull()"})
  public void testGetAsJsonNull_givenJsonObjectAddNotAJsonNullAndInstance() {
    // Arrange
    JsonObject element = new JsonObject();
    element.add("Not a JSON Null: ", JsonNull.INSTANCE);

    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(element);
    jsonArray.add(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsJsonNull());
  }

  /**
   * Test {@link JsonElement#getAsJsonNull()}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code out == null} and {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#getAsJsonNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonNull JsonElement.getAsJsonNull()"})
  public void testGetAsJsonNull_givenJsonObjectAddOutNullAndInstance() {
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
   *   <li>Given {@link JsonNull#INSTANCE}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonElement.toString()"})
  public void testToString_givenInstance_thenReturnNull() {
    // Arrange, Act and Assert
    assertEquals("null", JsonNull.INSTANCE.toString());
  }

  /**
   * Test {@link JsonElement#toString()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code A}.
   *   <li>Then return {@code [65,true]}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonElement.toString()"})
  public void testToString_givenJsonArrayWithCapacityIsThreeAddA_thenReturn65True() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add((byte) 'A');
    jsonArray.add(true);

    // Act and Assert
    assertEquals("[65,true]", jsonArray.toString());
  }

  /**
   * Test {@link JsonElement#toString()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add acknowledge.
   *   <li>Then return {@code ["\u0006",true]}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonElement.toString()"})
  public void testToString_givenJsonArrayWithCapacityIsThreeAddAcknowledge_thenReturnU0006True() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add('\u0006');
    jsonArray.add(true);

    // Act and Assert
    assertEquals("[\"\\u0006\",true]", jsonArray.toString());
  }

  /**
   * Test {@link JsonElement#toString()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code ,}.
   *   <li>Then return {@code [",",true]}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonElement.toString()"})
  public void testToString_givenJsonArrayWithCapacityIsThreeAddComma_thenReturnTrue() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(",");
    jsonArray.add(true);

    // Act and Assert
    assertEquals("[\",\",true]", jsonArray.toString());
  }

  /**
   * Test {@link JsonElement#toString()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code false}.
   *   <li>Then return {@code [false,true]}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonElement.toString()"})
  public void testToString_givenJsonArrayWithCapacityIsThreeAddFalse_thenReturnFalseTrue() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(false);
    jsonArray.add(true);

    // Act and Assert
    assertEquals("[false,true]", jsonArray.toString());
  }

  /**
   * Test {@link JsonElement#toString()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@link Double#NaN}.
   *   <li>Then return {@code [NaN,true]}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonElement.toString()"})
  public void testToString_givenJsonArrayWithCapacityIsThreeAddNaN_thenReturnNaNTrue() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(Double.NaN);
    jsonArray.add(true);

    // Act and Assert
    assertEquals("[NaN,true]", jsonArray.toString());
  }

  /**
   * Test {@link JsonElement#toString()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add ten.
   *   <li>Then return {@code [10.0,true]}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonElement.toString()"})
  public void testToString_givenJsonArrayWithCapacityIsThreeAddTen_thenReturn100True() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(10.0d);
    jsonArray.add(true);

    // Act and Assert
    assertEquals("[10.0,true]", jsonArray.toString());
  }

  /**
   * Test {@link JsonElement#toString()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add ten.
   *   <li>Then return {@code [10.0,true]}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonElement.toString()"})
  public void testToString_givenJsonArrayWithCapacityIsThreeAddTen_thenReturn100True2() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(10.0f);
    jsonArray.add(true);

    // Act and Assert
    assertEquals("[10.0,true]", jsonArray.toString());
  }

  /**
   * Test {@link JsonElement#toString()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add thirty-two.
   *   <li>Then return {@code [32,true]}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonElement.toString()"})
  public void testToString_givenJsonArrayWithCapacityIsThreeAddThirtyTwo_thenReturn32True() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(32L);
    jsonArray.add(true);

    // Act and Assert
    assertEquals("[32,true]", jsonArray.toString());
  }

  /**
   * Test {@link JsonElement#toString()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add thirty-two.
   *   <li>Then return {@code [32,true]}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonElement.toString()"})
  public void testToString_givenJsonArrayWithCapacityIsThreeAddThirtyTwo_thenReturn32True2() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add((short) 32);
    jsonArray.add(true);

    // Act and Assert
    assertEquals("[32,true]", jsonArray.toString());
  }

  /**
   * Test {@link JsonElement#toString()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code true}.
   *   <li>Then return {@code [true]}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonElement.toString()"})
  public void testToString_givenJsonArrayWithCapacityIsThreeAddTrue_thenReturnTrue() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(true);

    // Act and Assert
    assertEquals("[true]", jsonArray.toString());
  }

  /**
   * Test {@link JsonElement#toString()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add valueOf {@link
   *       Integer#SIZE}.
   *   <li>Then return {@code [32,true]}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonElement.toString()"})
  public void testToString_givenJsonArrayWithCapacityIsThreeAddValueOfSize_thenReturn32True() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(Integer.valueOf(Integer.SIZE));
    jsonArray.add(true);

    // Act and Assert
    assertEquals("[32,true]", jsonArray.toString());
  }

  /**
   * Test {@link JsonElement#toString()}.
   *
   * <ul>
   *   <li>Then return {@code [42,true]}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonElement.toString()"})
  public void testToString_thenReturn42True() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new LazilyParsedNumber("42"));
    jsonArray.add(true);

    // Act and Assert
    assertEquals("[42,true]", jsonArray.toString());
  }

  /**
   * Test {@link JsonElement#toString()}.
   *
   * <ul>
   *   <li>Then return {@code [-Infinity,true]}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonElement.toString()"})
  public void testToString_thenReturnInfinityTrue() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new LazilyParsedNumber("-Infinity"));
    jsonArray.add(true);

    // Act and Assert
    assertEquals("[-Infinity,true]", jsonArray.toString());
  }

  /**
   * Test {@link JsonElement#toString()}.
   *
   * <ul>
   *   <li>Then return {@code [Infinity,true]}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonElement.toString()"})
  public void testToString_thenReturnInfinityTrue2() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new LazilyParsedNumber("Infinity"));
    jsonArray.add(true);

    // Act and Assert
    assertEquals("[Infinity,true]", jsonArray.toString());
  }

  /**
   * Test {@link JsonElement#toString()}.
   *
   * <ul>
   *   <li>Then return {@code []}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElement#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonElement.toString()"})
  public void testToString_thenReturnLeftSquareBracketRightSquareBracket() {
    // Arrange, Act and Assert
    assertEquals("[]", new JsonArray(3).toString());
  }
}
