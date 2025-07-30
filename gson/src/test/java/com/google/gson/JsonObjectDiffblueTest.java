package com.google.gson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JsonObjectDiffblueTest {
  /**
   * Test new {@link JsonObject} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link JsonObject}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.<init>()"})
  public void testNewJsonObject() {
    // Arrange and Act
    JsonObject actualJsonObject = new JsonObject();

    // Assert
    assertEquals(0, actualJsonObject.size());
    assertFalse(actualJsonObject.isJsonArray());
    assertFalse(actualJsonObject.isJsonNull());
    assertFalse(actualJsonObject.isJsonPrimitive());
    assertTrue(actualJsonObject.isJsonObject());
    assertTrue(actualJsonObject.isEmpty());
    assertSame(actualJsonObject, actualJsonObject.getAsJsonObject());
  }

  /**
   * Test {@link JsonObject#deepCopy()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#deepCopy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonObject.deepCopy()"})
  public void testDeepCopy_givenJsonArrayWithCapacityIsThreeAddTrue() {
    // Arrange
    JsonArray value = new JsonArray(3);
    value.add(true);

    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", value);

    // Act and Assert
    assertEquals(jsonObject, jsonObject.deepCopy());
  }

  /**
   * Test {@link JsonObject#deepCopy()}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#deepCopy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonObject.deepCopy()"})
  public void testDeepCopy_givenJsonObject() {
    // Arrange
    JsonObject jsonObject = new JsonObject();

    // Act and Assert
    assertEquals(jsonObject, jsonObject.deepCopy());
  }

  /**
   * Test {@link JsonObject#deepCopy()}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code 42} and {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#deepCopy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonObject.deepCopy()"})
  public void testDeepCopy_givenJsonObjectAdd42AndInstance() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("42", JsonNull.INSTANCE);
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act and Assert
    assertEquals(jsonObject, jsonObject.deepCopy());
  }

  /**
   * Test {@link JsonObject#deepCopy()}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code 42} and {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#deepCopy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonObject.deepCopy()"})
  public void testDeepCopy_givenJsonObjectAdd42AndInstance2() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("42", JsonNull.INSTANCE);
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act and Assert
    assertEquals(jsonObject, jsonObject.deepCopy());
  }

  /**
   * Test {@link JsonObject#deepCopy()}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code 42} and {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#deepCopy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonObject.deepCopy()"})
  public void testDeepCopy_givenJsonObjectAdd42AndInstance3() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", JsonNull.INSTANCE);
    jsonObject.add("42", JsonNull.INSTANCE);
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act and Assert
    assertEquals(jsonObject, jsonObject.deepCopy());
  }

  /**
   * Test {@link JsonObject#deepCopy()}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code JsonObject} and {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#deepCopy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonObject.deepCopy()"})
  public void testDeepCopy_givenJsonObjectAddComGoogleGsonJsonObjectAndInstance() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act and Assert
    assertEquals(jsonObject, jsonObject.deepCopy());
  }

  /**
   * Test {@link JsonObject#deepCopy()}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add empty string and {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#deepCopy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonObject.deepCopy()"})
  public void testDeepCopy_givenJsonObjectAddEmptyStringAndInstance() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("", JsonNull.INSTANCE);
    jsonObject.add("42", JsonNull.INSTANCE);
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act and Assert
    assertEquals(jsonObject, jsonObject.deepCopy());
  }

  /**
   * Test {@link JsonObject#deepCopy()}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add empty string and {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#deepCopy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonObject.deepCopy()"})
  public void testDeepCopy_givenJsonObjectAddEmptyStringAndInstance2() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("42", JsonNull.INSTANCE);
    jsonObject.add("", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act and Assert
    assertEquals(jsonObject, jsonObject.deepCopy());
  }

  /**
   * Test {@link JsonObject#deepCopy()}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code Property} and {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#deepCopy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonObject.deepCopy()"})
  public void testDeepCopy_givenJsonObjectAddPropertyAndInstance() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act and Assert
    assertEquals(jsonObject, jsonObject.deepCopy());
  }

  /**
   * Test {@link JsonObject#deepCopy()}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code Property} and {@link
   *       JsonArray#JsonArray(int)} with capacity is three.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#deepCopy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonObject.deepCopy()"})
  public void testDeepCopy_givenJsonObjectAddPropertyAndJsonArrayWithCapacityIsThree() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", new JsonArray(3));

    // Act and Assert
    assertEquals(jsonObject, jsonObject.deepCopy());
  }

  /**
   * Test {@link JsonObject#deepCopy()}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code Property} and {@link
   *       JsonObject} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#deepCopy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonObject.deepCopy()"})
  public void testDeepCopy_givenJsonObjectAddPropertyAndJsonObject() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", new JsonObject());

    // Act and Assert
    assertEquals(jsonObject, jsonObject.deepCopy());
  }

  /**
   * Test {@link JsonObject#deepCopy()}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) addProperty {@code JsonObject} and {@code
   *       Value}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#deepCopy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonObject.deepCopy()"})
  public void testDeepCopy_givenJsonObjectAddPropertyComGoogleGsonJsonObjectAndValue() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("com.google.gson.JsonObject", "Value");
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act and Assert
    assertEquals(jsonObject, jsonObject.deepCopy());
  }

  /**
   * Test {@link JsonObject#add(String, JsonElement)}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code 42} and {@link
   *       JsonNull#INSTANCE}.
   *   <li>When {@code Property}.
   *   <li>Then {@link JsonObject} (default constructor) size is three.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#add(String, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.add(String, JsonElement)"})
  public void testAdd_givenJsonObjectAdd42AndInstance_whenProperty_thenJsonObjectSizeIsThree() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("42", JsonNull.INSTANCE);

    // Act
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Assert
    assertEquals(3, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#add(String, JsonElement)}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code 42} and {@link
   *       JsonNull#INSTANCE}.
   *   <li>When {@code Property}.
   *   <li>Then {@link JsonObject} (default constructor) size is three.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#add(String, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.add(String, JsonElement)"})
  public void testAdd_givenJsonObjectAdd42AndInstance_whenProperty_thenJsonObjectSizeIsThree2() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("42", JsonNull.INSTANCE);
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);

    // Act
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Assert
    assertEquals(3, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#add(String, JsonElement)}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code 42} and {@link
   *       JsonNull#INSTANCE}.
   *   <li>When {@code Property}.
   *   <li>Then {@link JsonObject} (default constructor) size is two.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#add(String, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.add(String, JsonElement)"})
  public void testAdd_givenJsonObjectAdd42AndInstance_whenProperty_thenJsonObjectSizeIsTwo() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("42", JsonNull.INSTANCE);

    // Act
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Assert
    assertEquals(2, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#add(String, JsonElement)}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code Property} and {@link
   *       JsonNull#INSTANCE}.
   *   <li>Then {@link JsonObject} (default constructor) size is one.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#add(String, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.add(String, JsonElement)"})
  public void testAdd_givenJsonObjectAddPropertyAndInstance_thenJsonObjectSizeIsOne() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Assert that nothing has changed
    assertEquals(1, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#add(String, JsonElement)}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code Property} and {@link
   *       JsonNull#INSTANCE}.
   *   <li>Then {@link JsonObject} (default constructor) size is two.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#add(String, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.add(String, JsonElement)"})
  public void testAdd_givenJsonObjectAddPropertyAndInstance_thenJsonObjectSizeIsTwo() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Assert that nothing has changed
    assertEquals(2, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#add(String, JsonElement)}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code Property} and {@link
   *       JsonNull#INSTANCE}.
   *   <li>When {@code 42}.
   *   <li>Then {@link JsonObject} (default constructor) size is three.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#add(String, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.add(String, JsonElement)"})
  public void testAdd_givenJsonObjectAddPropertyAndInstance_when42_thenJsonObjectSizeIsThree() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act
    jsonObject.add("42", JsonNull.INSTANCE);

    // Assert
    assertEquals(3, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#add(String, JsonElement)}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code Property} and {@link
   *       JsonNull#INSTANCE}.
   *   <li>When {@code 42}.
   *   <li>Then {@link JsonObject} (default constructor) size is three.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#add(String, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.add(String, JsonElement)"})
  public void testAdd_givenJsonObjectAddPropertyAndInstance_when42_thenJsonObjectSizeIsThree2() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", JsonNull.INSTANCE);
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act
    jsonObject.add("42", JsonNull.INSTANCE);

    // Assert
    assertEquals(3, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#add(String, JsonElement)}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) addProperty empty string and {@code
   *       Value}.
   *   <li>Then {@link JsonObject} (default constructor) size is three.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#add(String, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.add(String, JsonElement)"})
  public void testAdd_givenJsonObjectAddPropertyEmptyStringAndValue_thenJsonObjectSizeIsThree() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("", "Value");
    jsonObject.add("42", JsonNull.INSTANCE);

    // Act
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Assert
    assertEquals(3, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#add(String, JsonElement)}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor).
   *   <li>When {@code null}.
   *   <li>Then {@link JsonObject} (default constructor) size is one.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#add(String, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.add(String, JsonElement)"})
  public void testAdd_givenJsonObject_whenNull_thenJsonObjectSizeIsOne() {
    // Arrange
    JsonObject jsonObject = new JsonObject();

    // Act
    jsonObject.add("Property", null);

    // Assert
    assertEquals(1, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#add(String, JsonElement)}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor).
   *   <li>When {@code Property}.
   *   <li>Then {@link JsonObject} (default constructor) size is one.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#add(String, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.add(String, JsonElement)"})
  public void testAdd_givenJsonObject_whenProperty_thenJsonObjectSizeIsOne() {
    // Arrange
    JsonObject jsonObject = new JsonObject();

    // Act
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Assert
    assertEquals(1, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#add(String, JsonElement)}.
   *
   * <ul>
   *   <li>Then {@link JsonObject} (default constructor) size is two.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#add(String, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.add(String, JsonElement)"})
  public void testAdd_thenJsonObjectSizeIsTwo() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);

    // Act
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Assert
    assertEquals(2, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#remove(String)}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code 42} and {@link
   *       JsonNull#INSTANCE}.
   *   <li>When {@code 42}.
   *   <li>Then {@link JsonObject} (default constructor) size is two.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#remove(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonObject.remove(String)"})
  public void testRemove_givenJsonObjectAdd42AndInstance_when42_thenJsonObjectSizeIsTwo() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("42", JsonNull.INSTANCE);
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act
    JsonElement actualRemoveResult = jsonObject.remove("42");

    // Assert
    assertEquals(2, jsonObject.size());
    assertSame(((JsonNull) actualRemoveResult).INSTANCE, actualRemoveResult);
  }

  /**
   * Test {@link JsonObject#remove(String)}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code 42} and {@link
   *       JsonNull#INSTANCE}.
   *   <li>When {@code Property}.
   *   <li>Then {@link JsonObject} (default constructor) size is two.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#remove(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonObject.remove(String)"})
  public void testRemove_givenJsonObjectAdd42AndInstance_whenProperty_thenJsonObjectSizeIsTwo() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("42", JsonNull.INSTANCE);
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act
    JsonElement actualRemoveResult = jsonObject.remove("Property");

    // Assert
    assertEquals(2, jsonObject.size());
    assertSame(((JsonNull) actualRemoveResult).INSTANCE, actualRemoveResult);
  }

  /**
   * Test {@link JsonObject#remove(String)}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code 42} and {@link
   *       JsonNull#INSTANCE}.
   *   <li>When {@code Property}.
   *   <li>Then not {@link JsonObject} (default constructor) Empty.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#remove(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonObject.remove(String)"})
  public void testRemove_givenJsonObjectAdd42AndInstance_whenProperty_thenNotJsonObjectEmpty() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("42", JsonNull.INSTANCE);

    // Act and Assert
    assertNull(jsonObject.remove("Property"));
    assertEquals(1, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#remove(String)}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add empty string and {@link
   *       JsonNull#INSTANCE}.
   *   <li>Then {@link JsonObject} (default constructor) size is three.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#remove(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonObject.remove(String)"})
  public void testRemove_givenJsonObjectAddEmptyStringAndInstance_thenJsonObjectSizeIsThree() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("", JsonNull.INSTANCE);
    jsonObject.add("42", JsonNull.INSTANCE);
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act
    JsonElement actualRemoveResult = jsonObject.remove("Property");

    // Assert
    assertEquals(3, jsonObject.size());
    assertSame(((JsonNull) actualRemoveResult).INSTANCE, actualRemoveResult);
  }

  /**
   * Test {@link JsonObject#remove(String)}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor).
   *   <li>When {@code Property}.
   *   <li>Then {@link JsonObject} (default constructor) size is zero.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#remove(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonObject.remove(String)"})
  public void testRemove_givenJsonObject_whenProperty_thenJsonObjectSizeIsZero() {
    // Arrange
    JsonObject jsonObject = new JsonObject();

    // Act and Assert
    assertNull(jsonObject.remove("Property"));
    assertEquals(0, jsonObject.size());
    assertTrue(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#remove(String)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then {@link JsonObject} (default constructor) size is three.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#remove(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonObject.remove(String)"})
  public void testRemove_when42_thenJsonObjectSizeIsThree() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("", JsonNull.INSTANCE);
    jsonObject.add("42", JsonNull.INSTANCE);
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act
    JsonElement actualRemoveResult = jsonObject.remove("42");

    // Assert
    assertEquals(3, jsonObject.size());
    assertSame(((JsonNull) actualRemoveResult).INSTANCE, actualRemoveResult);
  }

  /**
   * Test {@link JsonObject#remove(String)}.
   *
   * <ul>
   *   <li>When {@code JsonObject}.
   *   <li>Then {@link JsonObject} (default constructor) size is one.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#remove(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonObject.remove(String)"})
  public void testRemove_whenComGoogleGsonJsonObject_thenJsonObjectSizeIsOne() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act
    JsonElement actualRemoveResult = jsonObject.remove("com.google.gson.JsonObject");

    // Assert
    assertEquals(1, jsonObject.size());
    assertSame(((JsonNull) actualRemoveResult).INSTANCE, actualRemoveResult);
  }

  /**
   * Test {@link JsonObject#remove(String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then {@link JsonObject} (default constructor) size is three.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#remove(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonObject.remove(String)"})
  public void testRemove_whenEmptyString_thenJsonObjectSizeIsThree() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("", JsonNull.INSTANCE);
    jsonObject.add("42", JsonNull.INSTANCE);
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act
    JsonElement actualRemoveResult = jsonObject.remove("");

    // Assert
    assertEquals(3, jsonObject.size());
    assertSame(((JsonNull) actualRemoveResult).INSTANCE, actualRemoveResult);
  }

  /**
   * Test {@link JsonObject#remove(String)}.
   *
   * <ul>
   *   <li>When {@code Property}.
   *   <li>Then {@link JsonObject} (default constructor) size is one.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#remove(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonObject.remove(String)"})
  public void testRemove_whenProperty_thenJsonObjectSizeIsOne() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act
    JsonElement actualRemoveResult = jsonObject.remove("Property");

    // Assert
    assertEquals(1, jsonObject.size());
    assertSame(((JsonNull) actualRemoveResult).INSTANCE, actualRemoveResult);
  }

  /**
   * Test {@link JsonObject#remove(String)}.
   *
   * <ul>
   *   <li>When {@code Property}.
   *   <li>Then {@link JsonObject} (default constructor) size is one.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#remove(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonObject.remove(String)"})
  public void testRemove_whenProperty_thenJsonObjectSizeIsOne2() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", JsonNull.INSTANCE);
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act
    JsonElement actualRemoveResult = jsonObject.remove("Property");

    // Assert
    assertEquals(1, jsonObject.size());
    assertSame(((JsonNull) actualRemoveResult).INSTANCE, actualRemoveResult);
  }

  /**
   * Test {@link JsonObject#remove(String)}.
   *
   * <ul>
   *   <li>When {@code Property}.
   *   <li>Then {@link JsonObject} (default constructor) size is zero.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#remove(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonObject.remove(String)"})
  public void testRemove_whenProperty_thenJsonObjectSizeIsZero() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act
    JsonElement actualRemoveResult = jsonObject.remove("Property");

    // Assert
    assertEquals(0, jsonObject.size());
    assertTrue(jsonObject.isEmpty());
    assertSame(((JsonNull) actualRemoveResult).INSTANCE, actualRemoveResult);
  }

  /**
   * Test {@link JsonObject#remove(String)}.
   *
   * <ul>
   *   <li>When {@code Property}.
   *   <li>Then not {@link JsonObject} (default constructor) Empty.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#remove(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonObject.remove(String)"})
  public void testRemove_whenProperty_thenNotJsonObjectEmpty() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);

    // Act and Assert
    assertNull(jsonObject.remove("Property"));
    assertEquals(1, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#addProperty(String, Boolean)} with {@code String}, {@code Boolean}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code 42} and {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#addProperty(String, Boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.addProperty(String, Boolean)"})
  public void testAddPropertyWithStringBoolean_givenJsonObjectAdd42AndInstance() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("42", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("Property", true);

    // Assert
    assertEquals(3, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#addProperty(String, Boolean)} with {@code String}, {@code Boolean}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code 42} and {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#addProperty(String, Boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.addProperty(String, Boolean)"})
  public void testAddPropertyWithStringBoolean_givenJsonObjectAdd42AndInstance2() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("42", JsonNull.INSTANCE);
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("Property", true);

    // Assert
    assertEquals(3, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#addProperty(String, Boolean)} with {@code String}, {@code Boolean}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code Property} and {@link
   *       JsonNull#INSTANCE}.
   *   <li>When {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#addProperty(String, Boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.addProperty(String, Boolean)"})
  public void testAddPropertyWithStringBoolean_givenJsonObjectAddPropertyAndInstance_when42() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("42", true);

    // Assert
    assertEquals(3, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#addProperty(String, Boolean)} with {@code String}, {@code Boolean}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code Property} and {@link
   *       JsonNull#INSTANCE}.
   *   <li>When {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#addProperty(String, Boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.addProperty(String, Boolean)"})
  public void testAddPropertyWithStringBoolean_givenJsonObjectAddPropertyAndInstance_when422() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", JsonNull.INSTANCE);
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("42", true);

    // Assert
    assertEquals(3, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#addProperty(String, Boolean)} with {@code String}, {@code Boolean}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) addProperty empty string and {@code
   *       Value}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#addProperty(String, Boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.addProperty(String, Boolean)"})
  public void testAddPropertyWithStringBoolean_givenJsonObjectAddPropertyEmptyStringAndValue() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("", "Value");
    jsonObject.add("42", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("Property", true);

    // Assert
    assertEquals(3, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#addProperty(String, Boolean)} with {@code String}, {@code Boolean}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor).
   *   <li>Then {@link JsonObject} (default constructor) size is one.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#addProperty(String, Boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.addProperty(String, Boolean)"})
  public void testAddPropertyWithStringBoolean_givenJsonObject_thenJsonObjectSizeIsOne() {
    // Arrange
    JsonObject jsonObject = new JsonObject();

    // Act
    jsonObject.addProperty("Property", true);

    // Assert
    assertEquals(1, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#addProperty(String, Boolean)} with {@code String}, {@code Boolean}.
   *
   * <ul>
   *   <li>Then {@link JsonObject} (default constructor) size is one.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#addProperty(String, Boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.addProperty(String, Boolean)"})
  public void testAddPropertyWithStringBoolean_thenJsonObjectSizeIsOne() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("Property", true);

    // Assert that nothing has changed
    assertEquals(1, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#addProperty(String, Boolean)} with {@code String}, {@code Boolean}.
   *
   * <ul>
   *   <li>Then {@link JsonObject} (default constructor) size is two.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#addProperty(String, Boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.addProperty(String, Boolean)"})
  public void testAddPropertyWithStringBoolean_thenJsonObjectSizeIsTwo() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("Property", true);

    // Assert that nothing has changed
    assertEquals(2, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#addProperty(String, Boolean)} with {@code String}, {@code Boolean}.
   *
   * <ul>
   *   <li>Then {@link JsonObject} (default constructor) size is two.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#addProperty(String, Boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.addProperty(String, Boolean)"})
  public void testAddPropertyWithStringBoolean_thenJsonObjectSizeIsTwo2() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("Property", true);

    // Assert
    assertEquals(2, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#addProperty(String, Boolean)} with {@code String}, {@code Boolean}.
   *
   * <ul>
   *   <li>Then {@link JsonObject} (default constructor) size is two.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#addProperty(String, Boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.addProperty(String, Boolean)"})
  public void testAddPropertyWithStringBoolean_thenJsonObjectSizeIsTwo3() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("42", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("Property", true);

    // Assert
    assertEquals(2, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#addProperty(String, Boolean)} with {@code String}, {@code Boolean}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@link JsonObject} (default constructor) size is one.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#addProperty(String, Boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.addProperty(String, Boolean)"})
  public void testAddPropertyWithStringBoolean_whenNull_thenJsonObjectSizeIsOne() {
    // Arrange
    JsonObject jsonObject = new JsonObject();

    // Act
    jsonObject.addProperty("Property", (Boolean) null);

    // Assert
    assertEquals(1, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#addProperty(String, Character)} with {@code String}, {@code Character}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code 42} and {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#addProperty(String, Character)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.addProperty(String, Character)"})
  public void testAddPropertyWithStringCharacter_givenJsonObjectAdd42AndInstance() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("42", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("Property", 'A');

    // Assert
    assertEquals(3, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#addProperty(String, Character)} with {@code String}, {@code Character}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code 42} and {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#addProperty(String, Character)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.addProperty(String, Character)"})
  public void testAddPropertyWithStringCharacter_givenJsonObjectAdd42AndInstance2() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("42", JsonNull.INSTANCE);
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("Property", 'A');

    // Assert
    assertEquals(3, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#addProperty(String, Character)} with {@code String}, {@code Character}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code Property} and {@link
   *       JsonNull#INSTANCE}.
   *   <li>When {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#addProperty(String, Character)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.addProperty(String, Character)"})
  public void testAddPropertyWithStringCharacter_givenJsonObjectAddPropertyAndInstance_when42() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("42", 'A');

    // Assert
    assertEquals(3, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#addProperty(String, Character)} with {@code String}, {@code Character}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code Property} and {@link
   *       JsonNull#INSTANCE}.
   *   <li>When {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#addProperty(String, Character)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.addProperty(String, Character)"})
  public void testAddPropertyWithStringCharacter_givenJsonObjectAddPropertyAndInstance_when422() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", JsonNull.INSTANCE);
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("42", 'A');

    // Assert
    assertEquals(3, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#addProperty(String, Character)} with {@code String}, {@code Character}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) addProperty empty string and {@code
   *       Value}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#addProperty(String, Character)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.addProperty(String, Character)"})
  public void testAddPropertyWithStringCharacter_givenJsonObjectAddPropertyEmptyStringAndValue() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("", "Value");
    jsonObject.add("42", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("Property", 'A');

    // Assert
    assertEquals(3, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#addProperty(String, Character)} with {@code String}, {@code Character}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor).
   *   <li>Then {@link JsonObject} (default constructor) size is one.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#addProperty(String, Character)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.addProperty(String, Character)"})
  public void testAddPropertyWithStringCharacter_givenJsonObject_thenJsonObjectSizeIsOne() {
    // Arrange
    JsonObject jsonObject = new JsonObject();

    // Act
    jsonObject.addProperty("Property", 'A');

    // Assert
    assertEquals(1, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#addProperty(String, Character)} with {@code String}, {@code Character}.
   *
   * <ul>
   *   <li>Then {@link JsonObject} (default constructor) size is one.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#addProperty(String, Character)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.addProperty(String, Character)"})
  public void testAddPropertyWithStringCharacter_thenJsonObjectSizeIsOne() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("Property", 'A');

    // Assert that nothing has changed
    assertEquals(1, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#addProperty(String, Character)} with {@code String}, {@code Character}.
   *
   * <ul>
   *   <li>Then {@link JsonObject} (default constructor) size is two.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#addProperty(String, Character)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.addProperty(String, Character)"})
  public void testAddPropertyWithStringCharacter_thenJsonObjectSizeIsTwo() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("Property", 'A');

    // Assert that nothing has changed
    assertEquals(2, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#addProperty(String, Character)} with {@code String}, {@code Character}.
   *
   * <ul>
   *   <li>Then {@link JsonObject} (default constructor) size is two.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#addProperty(String, Character)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.addProperty(String, Character)"})
  public void testAddPropertyWithStringCharacter_thenJsonObjectSizeIsTwo2() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("Property", 'A');

    // Assert
    assertEquals(2, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#addProperty(String, Character)} with {@code String}, {@code Character}.
   *
   * <ul>
   *   <li>Then {@link JsonObject} (default constructor) size is two.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#addProperty(String, Character)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.addProperty(String, Character)"})
  public void testAddPropertyWithStringCharacter_thenJsonObjectSizeIsTwo3() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("42", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("Property", 'A');

    // Assert
    assertEquals(2, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#addProperty(String, Character)} with {@code String}, {@code Character}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@link JsonObject} (default constructor) size is one.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#addProperty(String, Character)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.addProperty(String, Character)"})
  public void testAddPropertyWithStringCharacter_whenNull_thenJsonObjectSizeIsOne() {
    // Arrange
    JsonObject jsonObject = new JsonObject();

    // Act
    jsonObject.addProperty("Property", (Character) null);

    // Assert
    assertEquals(1, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#addProperty(String, Number)} with {@code String}, {@code Number}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code 42} and {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#addProperty(String, Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.addProperty(String, Number)"})
  public void testAddPropertyWithStringNumber_givenJsonObjectAdd42AndInstance() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("42", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("Property", Integer.valueOf(1));

    // Assert
    assertEquals(3, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#addProperty(String, Number)} with {@code String}, {@code Number}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code 42} and {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#addProperty(String, Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.addProperty(String, Number)"})
  public void testAddPropertyWithStringNumber_givenJsonObjectAdd42AndInstance2() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("42", JsonNull.INSTANCE);
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("Property", Integer.valueOf(1));

    // Assert
    assertEquals(3, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#addProperty(String, Number)} with {@code String}, {@code Number}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code Property} and {@link
   *       JsonNull#INSTANCE}.
   *   <li>When {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#addProperty(String, Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.addProperty(String, Number)"})
  public void testAddPropertyWithStringNumber_givenJsonObjectAddPropertyAndInstance_when42() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("42", Integer.valueOf(1));

    // Assert
    assertEquals(3, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#addProperty(String, Number)} with {@code String}, {@code Number}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code Property} and {@link
   *       JsonNull#INSTANCE}.
   *   <li>When {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#addProperty(String, Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.addProperty(String, Number)"})
  public void testAddPropertyWithStringNumber_givenJsonObjectAddPropertyAndInstance_when422() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", JsonNull.INSTANCE);
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("42", Integer.valueOf(1));

    // Assert
    assertEquals(3, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#addProperty(String, Number)} with {@code String}, {@code Number}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) addProperty empty string and {@code
   *       Value}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#addProperty(String, Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.addProperty(String, Number)"})
  public void testAddPropertyWithStringNumber_givenJsonObjectAddPropertyEmptyStringAndValue() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("", "Value");
    jsonObject.add("42", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("Property", Integer.valueOf(1));

    // Assert
    assertEquals(3, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#addProperty(String, Number)} with {@code String}, {@code Number}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor).
   *   <li>Then {@link JsonObject} (default constructor) size is one.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#addProperty(String, Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.addProperty(String, Number)"})
  public void testAddPropertyWithStringNumber_givenJsonObject_thenJsonObjectSizeIsOne() {
    // Arrange
    JsonObject jsonObject = new JsonObject();

    // Act
    jsonObject.addProperty("Property", Integer.valueOf(1));

    // Assert
    assertEquals(1, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#addProperty(String, Number)} with {@code String}, {@code Number}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor).
   *   <li>When {@code null}.
   *   <li>Then {@link JsonObject} (default constructor) size is one.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#addProperty(String, Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.addProperty(String, Number)"})
  public void testAddPropertyWithStringNumber_givenJsonObject_whenNull_thenJsonObjectSizeIsOne() {
    // Arrange
    JsonObject jsonObject = new JsonObject();

    // Act
    jsonObject.addProperty("Property", (Number) null);

    // Assert
    assertEquals(1, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#addProperty(String, Number)} with {@code String}, {@code Number}.
   *
   * <ul>
   *   <li>Then {@link JsonObject} (default constructor) size is one.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#addProperty(String, Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.addProperty(String, Number)"})
  public void testAddPropertyWithStringNumber_thenJsonObjectSizeIsOne() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("Property", Integer.valueOf(1));

    // Assert
    assertEquals(1, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#addProperty(String, Number)} with {@code String}, {@code Number}.
   *
   * <ul>
   *   <li>Then {@link JsonObject} (default constructor) size is two.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#addProperty(String, Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.addProperty(String, Number)"})
  public void testAddPropertyWithStringNumber_thenJsonObjectSizeIsTwo() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("Property", Integer.valueOf(1));

    // Assert that nothing has changed
    assertEquals(2, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#addProperty(String, Number)} with {@code String}, {@code Number}.
   *
   * <ul>
   *   <li>Then {@link JsonObject} (default constructor) size is two.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#addProperty(String, Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.addProperty(String, Number)"})
  public void testAddPropertyWithStringNumber_thenJsonObjectSizeIsTwo2() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("Property", Integer.valueOf(1));

    // Assert
    assertEquals(2, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#addProperty(String, Number)} with {@code String}, {@code Number}.
   *
   * <ul>
   *   <li>Then {@link JsonObject} (default constructor) size is two.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#addProperty(String, Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.addProperty(String, Number)"})
  public void testAddPropertyWithStringNumber_thenJsonObjectSizeIsTwo3() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("42", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("Property", Integer.valueOf(1));

    // Assert
    assertEquals(2, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#addProperty(String, String)} with {@code String}, {@code String}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code 42} and {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#addProperty(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.addProperty(String, String)"})
  public void testAddPropertyWithStringString_givenJsonObjectAdd42AndInstance() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("42", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("Property", "42");

    // Assert
    assertEquals(3, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#addProperty(String, String)} with {@code String}, {@code String}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code 42} and {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#addProperty(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.addProperty(String, String)"})
  public void testAddPropertyWithStringString_givenJsonObjectAdd42AndInstance2() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("42", JsonNull.INSTANCE);
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("Property", "42");

    // Assert
    assertEquals(3, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#addProperty(String, String)} with {@code String}, {@code String}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code Property} and {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#addProperty(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.addProperty(String, String)"})
  public void testAddPropertyWithStringString_givenJsonObjectAddPropertyAndInstance() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("42", "42");

    // Assert
    assertEquals(3, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#addProperty(String, String)} with {@code String}, {@code String}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code Property} and {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#addProperty(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.addProperty(String, String)"})
  public void testAddPropertyWithStringString_givenJsonObjectAddPropertyAndInstance2() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", JsonNull.INSTANCE);
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("42", "42");

    // Assert
    assertEquals(3, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#addProperty(String, String)} with {@code String}, {@code String}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) addProperty empty string and {@code
   *       Value}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#addProperty(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.addProperty(String, String)"})
  public void testAddPropertyWithStringString_givenJsonObjectAddPropertyEmptyStringAndValue() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("", "Value");
    jsonObject.add("42", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("Property", "42");

    // Assert
    assertEquals(3, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#addProperty(String, String)} with {@code String}, {@code String}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor).
   *   <li>Then {@link JsonObject} (default constructor) size is one.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#addProperty(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.addProperty(String, String)"})
  public void testAddPropertyWithStringString_givenJsonObject_thenJsonObjectSizeIsOne() {
    // Arrange
    JsonObject jsonObject = new JsonObject();

    // Act
    jsonObject.addProperty("Property", "42");

    // Assert
    assertEquals(1, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#addProperty(String, String)} with {@code String}, {@code String}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor).
   *   <li>When {@code null}.
   *   <li>Then {@link JsonObject} (default constructor) size is one.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#addProperty(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.addProperty(String, String)"})
  public void testAddPropertyWithStringString_givenJsonObject_whenNull_thenJsonObjectSizeIsOne() {
    // Arrange
    JsonObject jsonObject = new JsonObject();

    // Act
    jsonObject.addProperty("Property", (String) null);

    // Assert
    assertEquals(1, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#addProperty(String, String)} with {@code String}, {@code String}.
   *
   * <ul>
   *   <li>Then {@link JsonObject} (default constructor) size is one.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#addProperty(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.addProperty(String, String)"})
  public void testAddPropertyWithStringString_thenJsonObjectSizeIsOne() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("Property", "42");

    // Assert that nothing has changed
    assertEquals(1, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#addProperty(String, String)} with {@code String}, {@code String}.
   *
   * <ul>
   *   <li>Then {@link JsonObject} (default constructor) size is two.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#addProperty(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.addProperty(String, String)"})
  public void testAddPropertyWithStringString_thenJsonObjectSizeIsTwo() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("Property", "42");

    // Assert that nothing has changed
    assertEquals(2, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#addProperty(String, String)} with {@code String}, {@code String}.
   *
   * <ul>
   *   <li>Then {@link JsonObject} (default constructor) size is two.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#addProperty(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.addProperty(String, String)"})
  public void testAddPropertyWithStringString_thenJsonObjectSizeIsTwo2() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("Property", "42");

    // Assert
    assertEquals(2, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#addProperty(String, String)} with {@code String}, {@code String}.
   *
   * <ul>
   *   <li>Then {@link JsonObject} (default constructor) size is two.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#addProperty(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonObject.addProperty(String, String)"})
  public void testAddPropertyWithStringString_thenJsonObjectSizeIsTwo3() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("42", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("Property", "42");

    // Assert
    assertEquals(2, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#entrySet()}.
   *
   * <p>Method under test: {@link JsonObject#entrySet()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Set JsonObject.entrySet()"})
  public void testEntrySet() {
    // Arrange, Act and Assert
    assertTrue(new JsonObject().entrySet().isEmpty());
  }

  /**
   * Test {@link JsonObject#keySet()}.
   *
   * <p>Method under test: {@link JsonObject#keySet()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Set JsonObject.keySet()"})
  public void testKeySet() {
    // Arrange, Act and Assert
    assertTrue(new JsonObject().keySet().isEmpty());
  }

  /**
   * Test {@link JsonObject#size()}.
   *
   * <p>Method under test: {@link JsonObject#size()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonObject.size()"})
  public void testSize() {
    // Arrange, Act and Assert
    assertEquals(0, new JsonObject().size());
  }

  /**
   * Test {@link JsonObject#isEmpty()}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code Property} and {@link
   *       JsonNull#INSTANCE}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#isEmpty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonObject.isEmpty()"})
  public void testIsEmpty_givenJsonObjectAddPropertyAndInstance_thenReturnFalse() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act and Assert
    assertFalse(jsonObject.isEmpty());
  }

  /**
   * Test {@link JsonObject#isEmpty()}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor).
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#isEmpty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonObject.isEmpty()"})
  public void testIsEmpty_givenJsonObject_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new JsonObject().isEmpty());
  }

  /**
   * Test {@link JsonObject#has(String)}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code 42} and {@link
   *       JsonNull#INSTANCE}.
   *   <li>When {@code Member Name}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#has(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonObject.has(String)"})
  public void testHas_givenJsonObjectAdd42AndInstance_whenMemberName_thenReturnFalse() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("42", JsonNull.INSTANCE);

    // Act and Assert
    assertFalse(jsonObject.has("Member Name"));
  }

  /**
   * Test {@link JsonObject#has(String)}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code JsonObject} and {@link
   *       JsonNull#INSTANCE}.
   *   <li>When {@code Member Name}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#has(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonObject.has(String)"})
  public void testHas_givenJsonObjectAddComGoogleGsonJsonObjectAndInstance_whenMemberName() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act and Assert
    assertFalse(jsonObject.has("Member Name"));
  }

  /**
   * Test {@link JsonObject#has(String)}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code Property} and {@link
   *       JsonNull#INSTANCE}.
   *   <li>When {@code Member Name}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#has(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonObject.has(String)"})
  public void testHas_givenJsonObjectAddPropertyAndInstance_whenMemberName_thenReturnFalse() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act and Assert
    assertFalse(jsonObject.has("Member Name"));
  }

  /**
   * Test {@link JsonObject#has(String)}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor).
   *   <li>When {@code Member Name}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#has(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonObject.has(String)"})
  public void testHas_givenJsonObject_whenMemberName_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new JsonObject().has("Member Name"));
  }

  /**
   * Test {@link JsonObject#has(String)}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor).
   *   <li>When {@code null}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#has(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonObject.has(String)"})
  public void testHas_givenJsonObject_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new JsonObject().has(null));
  }

  /**
   * Test {@link JsonObject#has(String)}.
   *
   * <ul>
   *   <li>When {@code JsonObject}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#has(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonObject.has(String)"})
  public void testHas_whenComGoogleGsonJsonObject_thenReturnTrue() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act and Assert
    assertTrue(jsonObject.has("com.google.gson.JsonObject"));
  }

  /**
   * Test {@link JsonObject#get(String)}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code 42} and {@link
   *       JsonNull#INSTANCE}.
   *   <li>When {@code Member Name}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#get(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonObject.get(String)"})
  public void testGet_givenJsonObjectAdd42AndInstance_whenMemberName_thenReturnNull() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("42", JsonNull.INSTANCE);

    // Act and Assert
    assertNull(jsonObject.get("Member Name"));
  }

  /**
   * Test {@link JsonObject#get(String)}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code JsonObject} and {@link
   *       JsonNull#INSTANCE}.
   *   <li>When {@code Member Name}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#get(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonObject.get(String)"})
  public void testGet_givenJsonObjectAddComGoogleGsonJsonObjectAndInstance_whenMemberName() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act and Assert
    assertNull(jsonObject.get("Member Name"));
  }

  /**
   * Test {@link JsonObject#get(String)}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code Property} and {@link
   *       JsonNull#INSTANCE}.
   *   <li>When {@code Member Name}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#get(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonObject.get(String)"})
  public void testGet_givenJsonObjectAddPropertyAndInstance_whenMemberName_thenReturnNull() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act and Assert
    assertNull(jsonObject.get("Member Name"));
  }

  /**
   * Test {@link JsonObject#get(String)}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor).
   *   <li>When {@code Member Name}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#get(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonObject.get(String)"})
  public void testGet_givenJsonObject_whenMemberName_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new JsonObject().get("Member Name"));
  }

  /**
   * Test {@link JsonObject#get(String)}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor).
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#get(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonObject.get(String)"})
  public void testGet_givenJsonObject_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new JsonObject().get(null));
  }

  /**
   * Test {@link JsonObject#get(String)}.
   *
   * <ul>
   *   <li>When {@code JsonObject}.
   *   <li>Then return {@link JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#get(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonObject.get(String)"})
  public void testGet_whenComGoogleGsonJsonObject_thenReturnInstance() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act
    JsonElement actualGetResult = jsonObject.get("com.google.gson.JsonObject");

    // Assert
    assertSame(((JsonNull) actualGetResult).INSTANCE, actualGetResult);
  }

  /**
   * Test {@link JsonObject#getAsJsonPrimitive(String)} with {@code String}.
   *
   * <p>Method under test: {@link JsonObject#getAsJsonPrimitive(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"com.google.gson.JsonPrimitive JsonObject.getAsJsonPrimitive(String)"})
  public void testGetAsJsonPrimitiveWithString() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act and Assert
    assertNull(jsonObject.getAsJsonPrimitive("Member Name"));
  }

  /**
   * Test {@link JsonObject#getAsJsonPrimitive(String)} with {@code String}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code 42} and {@link
   *       JsonNull#INSTANCE}.
   *   <li>When {@code Member Name}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#getAsJsonPrimitive(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"com.google.gson.JsonPrimitive JsonObject.getAsJsonPrimitive(String)"})
  public void testGetAsJsonPrimitiveWithString_givenJsonObjectAdd42AndInstance_whenMemberName() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("42", JsonNull.INSTANCE);

    // Act and Assert
    assertNull(jsonObject.getAsJsonPrimitive("Member Name"));
  }

  /**
   * Test {@link JsonObject#getAsJsonPrimitive(String)} with {@code String}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code Property} and {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#getAsJsonPrimitive(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"com.google.gson.JsonPrimitive JsonObject.getAsJsonPrimitive(String)"})
  public void testGetAsJsonPrimitiveWithString_givenJsonObjectAddPropertyAndInstance() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act and Assert
    assertNull(jsonObject.getAsJsonPrimitive("Member Name"));
  }

  /**
   * Test {@link JsonObject#getAsJsonPrimitive(String)} with {@code String}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor).
   *   <li>When {@code Member Name}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#getAsJsonPrimitive(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"com.google.gson.JsonPrimitive JsonObject.getAsJsonPrimitive(String)"})
  public void testGetAsJsonPrimitiveWithString_givenJsonObject_whenMemberName() {
    // Arrange, Act and Assert
    assertNull(new JsonObject().getAsJsonPrimitive("Member Name"));
  }

  /**
   * Test {@link JsonObject#getAsJsonPrimitive(String)} with {@code String}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor).
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#getAsJsonPrimitive(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"com.google.gson.JsonPrimitive JsonObject.getAsJsonPrimitive(String)"})
  public void testGetAsJsonPrimitiveWithString_givenJsonObject_whenNull() {
    // Arrange, Act and Assert
    assertNull(new JsonObject().getAsJsonPrimitive(null));
  }

  /**
   * Test {@link JsonObject#getAsJsonArray(String)} with {@code String}.
   *
   * <p>Method under test: {@link JsonObject#getAsJsonArray(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonArray JsonObject.getAsJsonArray(String)"})
  public void testGetAsJsonArrayWithString() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act and Assert
    assertNull(jsonObject.getAsJsonArray("Member Name"));
  }

  /**
   * Test {@link JsonObject#getAsJsonArray(String)} with {@code String}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code 42} and {@link
   *       JsonNull#INSTANCE}.
   *   <li>When {@code Member Name}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#getAsJsonArray(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonArray JsonObject.getAsJsonArray(String)"})
  public void testGetAsJsonArrayWithString_givenJsonObjectAdd42AndInstance_whenMemberName() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("42", JsonNull.INSTANCE);

    // Act and Assert
    assertNull(jsonObject.getAsJsonArray("Member Name"));
  }

  /**
   * Test {@link JsonObject#getAsJsonArray(String)} with {@code String}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code Property} and {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#getAsJsonArray(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonArray JsonObject.getAsJsonArray(String)"})
  public void testGetAsJsonArrayWithString_givenJsonObjectAddPropertyAndInstance() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act and Assert
    assertNull(jsonObject.getAsJsonArray("Member Name"));
  }

  /**
   * Test {@link JsonObject#getAsJsonArray(String)} with {@code String}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor).
   *   <li>When {@code Member Name}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#getAsJsonArray(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonArray JsonObject.getAsJsonArray(String)"})
  public void testGetAsJsonArrayWithString_givenJsonObject_whenMemberName() {
    // Arrange, Act and Assert
    assertNull(new JsonObject().getAsJsonArray("Member Name"));
  }

  /**
   * Test {@link JsonObject#getAsJsonArray(String)} with {@code String}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor).
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#getAsJsonArray(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonArray JsonObject.getAsJsonArray(String)"})
  public void testGetAsJsonArrayWithString_givenJsonObject_whenNull() {
    // Arrange, Act and Assert
    assertNull(new JsonObject().getAsJsonArray(null));
  }

  /**
   * Test {@link JsonObject#getAsJsonObject(String)} with {@code String}.
   *
   * <p>Method under test: {@link JsonObject#getAsJsonObject(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonObject.getAsJsonObject(String)"})
  public void testGetAsJsonObjectWithString() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act and Assert
    assertNull(jsonObject.getAsJsonObject("Member Name"));
  }

  /**
   * Test {@link JsonObject#getAsJsonObject(String)} with {@code String}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code 42} and {@link
   *       JsonNull#INSTANCE}.
   *   <li>When {@code Member Name}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#getAsJsonObject(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonObject.getAsJsonObject(String)"})
  public void testGetAsJsonObjectWithString_givenJsonObjectAdd42AndInstance_whenMemberName() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("42", JsonNull.INSTANCE);

    // Act and Assert
    assertNull(jsonObject.getAsJsonObject("Member Name"));
  }

  /**
   * Test {@link JsonObject#getAsJsonObject(String)} with {@code String}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code Property} and {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#getAsJsonObject(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonObject.getAsJsonObject(String)"})
  public void testGetAsJsonObjectWithString_givenJsonObjectAddPropertyAndInstance() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act and Assert
    assertNull(jsonObject.getAsJsonObject("Member Name"));
  }

  /**
   * Test {@link JsonObject#getAsJsonObject(String)} with {@code String}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor).
   *   <li>When {@code Member Name}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#getAsJsonObject(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonObject.getAsJsonObject(String)"})
  public void testGetAsJsonObjectWithString_givenJsonObject_whenMemberName() {
    // Arrange, Act and Assert
    assertNull(new JsonObject().getAsJsonObject("Member Name"));
  }

  /**
   * Test {@link JsonObject#getAsJsonObject(String)} with {@code String}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor).
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#getAsJsonObject(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonObject JsonObject.getAsJsonObject(String)"})
  public void testGetAsJsonObjectWithString_givenJsonObject_whenNull() {
    // Arrange, Act and Assert
    assertNull(new JsonObject().getAsJsonObject(null));
  }

  /**
   * Test {@link JsonObject#asMap()}.
   *
   * <p>Method under test: {@link JsonObject#asMap()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Map JsonObject.asMap()"})
  public void testAsMap() {
    // Arrange, Act and Assert
    assertTrue(new JsonObject().asMap().isEmpty());
  }

  /**
   * Test {@link JsonObject#equals(Object)}, and {@link JsonObject#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link JsonObject#equals(Object)}
   *   <li>{@link JsonObject#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonObject.equals(Object)", "int JsonObject.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    JsonObject jsonObject2 = new JsonObject();

    // Act and Assert
    assertEquals(jsonObject, jsonObject2);
    int expectedHashCodeResult = jsonObject.hashCode();
    assertEquals(expectedHashCodeResult, jsonObject2.hashCode());
  }

  /**
   * Test {@link JsonObject#equals(Object)}, and {@link JsonObject#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link JsonObject#equals(Object)}
   *   <li>{@link JsonObject#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonObject.equals(Object)", "int JsonObject.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    JsonObject jsonObject = new JsonObject();

    // Act and Assert
    assertEquals(jsonObject, jsonObject);
    int expectedHashCodeResult = jsonObject.hashCode();
    assertEquals(expectedHashCodeResult, jsonObject.hashCode());
  }

  /**
   * Test {@link JsonObject#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonObject.equals(Object)", "int JsonObject.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act and Assert
    assertNotEquals(jsonObject, new JsonObject());
  }

  /**
   * Test {@link JsonObject#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonObject.equals(Object)", "int JsonObject.hashCode()"})
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new JsonObject(), null);
  }

  /**
   * Test {@link JsonObject#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link JsonObject#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonObject.equals(Object)", "int JsonObject.hashCode()"})
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new JsonObject(), "Different type to JsonObject");
  }
}
