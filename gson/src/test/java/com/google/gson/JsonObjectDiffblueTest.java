package com.google.gson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Test;

public class JsonObjectDiffblueTest {
  /** Method under test: {@link JsonObject#deepCopy()} */
  @Test
  public void testDeepCopy() {
    // Arrange
    JsonObject jsonObject = new JsonObject();

    // Act and Assert
    assertEquals(jsonObject, jsonObject.deepCopy());
  }

  /** Method under test: {@link JsonObject#deepCopy()} */
  @Test
  public void testDeepCopy2() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act and Assert
    assertEquals(jsonObject, jsonObject.deepCopy());
  }

  /** Method under test: {@link JsonObject#deepCopy()} */
  @Test
  public void testDeepCopy3() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act and Assert
    assertEquals(jsonObject, jsonObject.deepCopy());
  }

  /** Method under test: {@link JsonObject#deepCopy()} */
  @Test
  public void testDeepCopy4() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("com.google.gson.JsonObject", "Value");
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act and Assert
    assertEquals(jsonObject, jsonObject.deepCopy());
  }

  /** Method under test: {@link JsonObject#deepCopy()} */
  @Test
  public void testDeepCopy5() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("42", JsonNull.INSTANCE);
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act and Assert
    assertEquals(jsonObject, jsonObject.deepCopy());
  }

  /** Method under test: {@link JsonObject#deepCopy()} */
  @Test
  public void testDeepCopy6() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("42", JsonNull.INSTANCE);
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act and Assert
    assertEquals(jsonObject, jsonObject.deepCopy());
  }

  /** Method under test: {@link JsonObject#deepCopy()} */
  @Test
  public void testDeepCopy7() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", JsonNull.INSTANCE);
    jsonObject.add("42", JsonNull.INSTANCE);
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act and Assert
    assertEquals(jsonObject, jsonObject.deepCopy());
  }

  /** Method under test: {@link JsonObject#deepCopy()} */
  @Test
  public void testDeepCopy8() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("", JsonNull.INSTANCE);
    jsonObject.add("42", JsonNull.INSTANCE);
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act and Assert
    assertEquals(jsonObject, jsonObject.deepCopy());
  }

  /** Method under test: {@link JsonObject#deepCopy()} */
  @Test
  public void testDeepCopy9() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", new JsonArray(3));

    // Act and Assert
    assertEquals(jsonObject, jsonObject.deepCopy());
  }

  /** Method under test: {@link JsonObject#deepCopy()} */
  @Test
  public void testDeepCopy10() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", new JsonObject());

    // Act and Assert
    assertEquals(jsonObject, jsonObject.deepCopy());
  }

  /** Method under test: {@link JsonObject#deepCopy()} */
  @Test
  public void testDeepCopy11() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("42", JsonNull.INSTANCE);
    jsonObject.add("", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act and Assert
    assertEquals(jsonObject, jsonObject.deepCopy());
  }

  /** Method under test: {@link JsonObject#deepCopy()} */
  @Test
  public void testDeepCopy12() {
    // Arrange
    JsonArray value = new JsonArray(3);
    value.add(true);

    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", value);

    // Act and Assert
    assertEquals(jsonObject, jsonObject.deepCopy());
  }

  /** Method under test: {@link JsonObject#add(String, JsonElement)} */
  @Test
  public void testAdd() {
    // Arrange
    JsonObject jsonObject = new JsonObject();

    // Act
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Assert
    assertEquals(1, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /** Method under test: {@link JsonObject#add(String, JsonElement)} */
  @Test
  public void testAdd2() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Assert
    assertEquals(1, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /** Method under test: {@link JsonObject#add(String, JsonElement)} */
  @Test
  public void testAdd3() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Assert
    assertEquals(2, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /** Method under test: {@link JsonObject#add(String, JsonElement)} */
  @Test
  public void testAdd4() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);

    // Act
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Assert
    assertEquals(2, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /** Method under test: {@link JsonObject#add(String, JsonElement)} */
  @Test
  public void testAdd5() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("42", JsonNull.INSTANCE);

    // Act
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Assert
    assertEquals(2, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /** Method under test: {@link JsonObject#add(String, JsonElement)} */
  @Test
  public void testAdd6() {
    // Arrange
    JsonObject jsonObject = new JsonObject();

    // Act
    jsonObject.add("Property", null);

    // Assert
    assertEquals(1, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /** Method under test: {@link JsonObject#add(String, JsonElement)} */
  @Test
  public void testAdd7() {
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

  /** Method under test: {@link JsonObject#add(String, JsonElement)} */
  @Test
  public void testAdd8() {
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

  /** Method under test: {@link JsonObject#add(String, JsonElement)} */
  @Test
  public void testAdd9() {
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

  /** Method under test: {@link JsonObject#add(String, JsonElement)} */
  @Test
  public void testAdd10() {
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

  /** Method under test: {@link JsonObject#add(String, JsonElement)} */
  @Test
  public void testAdd11() {
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

  /** Method under test: {@link JsonObject#remove(String)} */
  @Test
  public void testRemove() {
    // Arrange
    JsonObject jsonObject = new JsonObject();

    // Act and Assert
    assertNull(jsonObject.remove("Property"));
    assertEquals(0, jsonObject.size());
    assertTrue(jsonObject.isEmpty());
  }

  /** Method under test: {@link JsonObject#remove(String)} */
  @Test
  public void testRemove2() {
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

  /** Method under test: {@link JsonObject#remove(String)} */
  @Test
  public void testRemove3() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act
    JsonElement actualRemoveResult = jsonObject.remove("Property");

    // Assert
    assertEquals(1, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
    assertSame(((JsonNull) actualRemoveResult).INSTANCE, actualRemoveResult);
  }

  /** Method under test: {@link JsonObject#remove(String)} */
  @Test
  public void testRemove4() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);

    // Act and Assert
    assertNull(jsonObject.remove("Property"));
    assertEquals(1, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /** Method under test: {@link JsonObject#remove(String)} */
  @Test
  public void testRemove5() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("42", JsonNull.INSTANCE);

    // Act and Assert
    assertNull(jsonObject.remove("Property"));
    assertEquals(1, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /** Method under test: {@link JsonObject#remove(String)} */
  @Test
  public void testRemove6() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("42", JsonNull.INSTANCE);
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act
    JsonElement actualRemoveResult = jsonObject.remove("Property");

    // Assert
    assertEquals(2, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
    assertSame(((JsonNull) actualRemoveResult).INSTANCE, actualRemoveResult);
  }

  /** Method under test: {@link JsonObject#remove(String)} */
  @Test
  public void testRemove7() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", JsonNull.INSTANCE);
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act
    JsonElement actualRemoveResult = jsonObject.remove("Property");

    // Assert
    assertEquals(1, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
    assertSame(((JsonNull) actualRemoveResult).INSTANCE, actualRemoveResult);
  }

  /** Method under test: {@link JsonObject#remove(String)} */
  @Test
  public void testRemove8() {
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
    assertFalse(jsonObject.isEmpty());
    assertSame(((JsonNull) actualRemoveResult).INSTANCE, actualRemoveResult);
  }

  /** Method under test: {@link JsonObject#remove(String)} */
  @Test
  public void testRemove9() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act
    JsonElement actualRemoveResult = jsonObject.remove("com.google.gson.JsonObject");

    // Assert
    assertEquals(1, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
    assertSame(((JsonNull) actualRemoveResult).INSTANCE, actualRemoveResult);
  }

  /** Method under test: {@link JsonObject#remove(String)} */
  @Test
  public void testRemove10() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("42", JsonNull.INSTANCE);
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act
    JsonElement actualRemoveResult = jsonObject.remove("42");

    // Assert
    assertEquals(2, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
    assertSame(((JsonNull) actualRemoveResult).INSTANCE, actualRemoveResult);
  }

  /** Method under test: {@link JsonObject#remove(String)} */
  @Test
  public void testRemove11() {
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
    assertFalse(jsonObject.isEmpty());
    assertSame(((JsonNull) actualRemoveResult).INSTANCE, actualRemoveResult);
  }

  /** Method under test: {@link JsonObject#remove(String)} */
  @Test
  public void testRemove12() {
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
    assertFalse(jsonObject.isEmpty());
    assertSame(((JsonNull) actualRemoveResult).INSTANCE, actualRemoveResult);
  }

  /** Method under test: {@link JsonObject#addProperty(String, Boolean)} */
  @Test
  public void testAddProperty() {
    // Arrange
    JsonObject jsonObject = new JsonObject();

    // Act
    jsonObject.addProperty("Property", true);

    // Assert
    assertEquals(1, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /** Method under test: {@link JsonObject#addProperty(String, Boolean)} */
  @Test
  public void testAddProperty2() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("Property", true);

    // Assert
    assertEquals(1, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /** Method under test: {@link JsonObject#addProperty(String, Boolean)} */
  @Test
  public void testAddProperty3() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("Property", true);

    // Assert
    assertEquals(2, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /** Method under test: {@link JsonObject#addProperty(String, Boolean)} */
  @Test
  public void testAddProperty4() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("Property", true);

    // Assert
    assertEquals(2, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /** Method under test: {@link JsonObject#addProperty(String, Boolean)} */
  @Test
  public void testAddProperty5() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("42", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("Property", true);

    // Assert
    assertEquals(2, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /** Method under test: {@link JsonObject#addProperty(String, Boolean)} */
  @Test
  public void testAddProperty6() {
    // Arrange
    JsonObject jsonObject = new JsonObject();

    // Act
    jsonObject.addProperty("Property", (Boolean) null);

    // Assert
    assertEquals(1, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /** Method under test: {@link JsonObject#addProperty(String, Boolean)} */
  @Test
  public void testAddProperty7() {
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

  /** Method under test: {@link JsonObject#addProperty(String, Boolean)} */
  @Test
  public void testAddProperty8() {
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

  /** Method under test: {@link JsonObject#addProperty(String, Boolean)} */
  @Test
  public void testAddProperty9() {
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

  /** Method under test: {@link JsonObject#addProperty(String, Boolean)} */
  @Test
  public void testAddProperty10() {
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

  /** Method under test: {@link JsonObject#addProperty(String, Boolean)} */
  @Test
  public void testAddProperty11() {
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

  /** Method under test: {@link JsonObject#addProperty(String, Character)} */
  @Test
  public void testAddProperty12() {
    // Arrange
    JsonObject jsonObject = new JsonObject();

    // Act
    jsonObject.addProperty("Property", 'A');

    // Assert
    assertEquals(1, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /** Method under test: {@link JsonObject#addProperty(String, Character)} */
  @Test
  public void testAddProperty13() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("Property", 'A');

    // Assert
    assertEquals(1, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /** Method under test: {@link JsonObject#addProperty(String, Character)} */
  @Test
  public void testAddProperty14() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("Property", 'A');

    // Assert
    assertEquals(2, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /** Method under test: {@link JsonObject#addProperty(String, Character)} */
  @Test
  public void testAddProperty15() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("Property", 'A');

    // Assert
    assertEquals(2, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /** Method under test: {@link JsonObject#addProperty(String, Character)} */
  @Test
  public void testAddProperty16() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("42", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("Property", 'A');

    // Assert
    assertEquals(2, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /** Method under test: {@link JsonObject#addProperty(String, Character)} */
  @Test
  public void testAddProperty17() {
    // Arrange
    JsonObject jsonObject = new JsonObject();

    // Act
    jsonObject.addProperty("Property", (Character) null);

    // Assert
    assertEquals(1, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /** Method under test: {@link JsonObject#addProperty(String, Character)} */
  @Test
  public void testAddProperty18() {
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

  /** Method under test: {@link JsonObject#addProperty(String, Character)} */
  @Test
  public void testAddProperty19() {
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

  /** Method under test: {@link JsonObject#addProperty(String, Character)} */
  @Test
  public void testAddProperty20() {
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

  /** Method under test: {@link JsonObject#addProperty(String, Character)} */
  @Test
  public void testAddProperty21() {
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

  /** Method under test: {@link JsonObject#addProperty(String, Character)} */
  @Test
  public void testAddProperty22() {
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

  /** Method under test: {@link JsonObject#addProperty(String, Number)} */
  @Test
  public void testAddProperty23() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    Integer value = Integer.valueOf(1);

    // Act
    jsonObject.addProperty("Property", value);

    // Assert
    int sizeResult = jsonObject.size();
    assertEquals(1, sizeResult);
    assertFalse(jsonObject.isEmpty());
    assertSame(value, sizeResult);
  }

  /** Method under test: {@link JsonObject#addProperty(String, Number)} */
  @Test
  public void testAddProperty24() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", JsonNull.INSTANCE);
    Integer value = Integer.valueOf(1);

    // Act
    jsonObject.addProperty("Property", value);

    // Assert
    int sizeResult = jsonObject.size();
    assertEquals(1, sizeResult);
    assertFalse(jsonObject.isEmpty());
    assertSame(value, sizeResult);
  }

  /** Method under test: {@link JsonObject#addProperty(String, Number)} */
  @Test
  public void testAddProperty25() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("Property", Integer.valueOf(1));

    // Assert
    assertEquals(2, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /** Method under test: {@link JsonObject#addProperty(String, Number)} */
  @Test
  public void testAddProperty26() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("Property", Integer.valueOf(1));

    // Assert
    assertEquals(2, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /** Method under test: {@link JsonObject#addProperty(String, Number)} */
  @Test
  public void testAddProperty27() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("42", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("Property", Integer.valueOf(1));

    // Assert
    assertEquals(2, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /** Method under test: {@link JsonObject#addProperty(String, Number)} */
  @Test
  public void testAddProperty28() {
    // Arrange
    JsonObject jsonObject = new JsonObject();

    // Act
    jsonObject.addProperty("Property", (Number) null);

    // Assert
    assertEquals(1, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /** Method under test: {@link JsonObject#addProperty(String, Number)} */
  @Test
  public void testAddProperty29() {
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

  /** Method under test: {@link JsonObject#addProperty(String, Number)} */
  @Test
  public void testAddProperty30() {
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

  /** Method under test: {@link JsonObject#addProperty(String, Number)} */
  @Test
  public void testAddProperty31() {
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

  /** Method under test: {@link JsonObject#addProperty(String, Number)} */
  @Test
  public void testAddProperty32() {
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

  /** Method under test: {@link JsonObject#addProperty(String, Number)} */
  @Test
  public void testAddProperty33() {
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

  /** Method under test: {@link JsonObject#addProperty(String, String)} */
  @Test
  public void testAddProperty34() {
    // Arrange
    JsonObject jsonObject = new JsonObject();

    // Act
    jsonObject.addProperty("Property", "42");

    // Assert
    assertEquals(1, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /** Method under test: {@link JsonObject#addProperty(String, String)} */
  @Test
  public void testAddProperty35() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("Property", "42");

    // Assert
    assertEquals(1, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /** Method under test: {@link JsonObject#addProperty(String, String)} */
  @Test
  public void testAddProperty36() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("Property", "42");

    // Assert
    assertEquals(2, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /** Method under test: {@link JsonObject#addProperty(String, String)} */
  @Test
  public void testAddProperty37() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("Property", "42");

    // Assert
    assertEquals(2, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /** Method under test: {@link JsonObject#addProperty(String, String)} */
  @Test
  public void testAddProperty38() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("42", JsonNull.INSTANCE);

    // Act
    jsonObject.addProperty("Property", "42");

    // Assert
    assertEquals(2, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /** Method under test: {@link JsonObject#addProperty(String, String)} */
  @Test
  public void testAddProperty39() {
    // Arrange
    JsonObject jsonObject = new JsonObject();

    // Act
    jsonObject.addProperty("Property", (String) null);

    // Assert
    assertEquals(1, jsonObject.size());
    assertFalse(jsonObject.isEmpty());
  }

  /** Method under test: {@link JsonObject#addProperty(String, String)} */
  @Test
  public void testAddProperty40() {
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

  /** Method under test: {@link JsonObject#addProperty(String, String)} */
  @Test
  public void testAddProperty41() {
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

  /** Method under test: {@link JsonObject#addProperty(String, String)} */
  @Test
  public void testAddProperty42() {
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

  /** Method under test: {@link JsonObject#addProperty(String, String)} */
  @Test
  public void testAddProperty43() {
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

  /** Method under test: {@link JsonObject#addProperty(String, String)} */
  @Test
  public void testAddProperty44() {
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

  /** Method under test: {@link JsonObject#entrySet()} */
  @Test
  public void testEntrySet() {
    // Arrange, Act and Assert
    assertTrue((new JsonObject()).entrySet().isEmpty());
  }

  /** Method under test: {@link JsonObject#keySet()} */
  @Test
  public void testKeySet() {
    // Arrange, Act and Assert
    assertTrue((new JsonObject()).keySet().isEmpty());
  }

  /** Method under test: {@link JsonObject#size()} */
  @Test
  public void testSize() {
    // Arrange, Act and Assert
    assertEquals(0, (new JsonObject()).size());
  }

  /** Method under test: {@link JsonObject#isEmpty()} */
  @Test
  public void testIsEmpty() {
    // Arrange, Act and Assert
    assertTrue((new JsonObject()).isEmpty());
  }

  /** Method under test: {@link JsonObject#isEmpty()} */
  @Test
  public void testIsEmpty2() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act and Assert
    assertFalse(jsonObject.isEmpty());
  }

  /** Method under test: {@link JsonObject#has(String)} */
  @Test
  public void testHas() {
    // Arrange, Act and Assert
    assertFalse((new JsonObject()).has("Member Name"));
  }

  /** Method under test: {@link JsonObject#has(String)} */
  @Test
  public void testHas2() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act and Assert
    assertFalse(jsonObject.has("Member Name"));
  }

  /** Method under test: {@link JsonObject#has(String)} */
  @Test
  public void testHas3() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act and Assert
    assertFalse(jsonObject.has("Member Name"));
  }

  /** Method under test: {@link JsonObject#has(String)} */
  @Test
  public void testHas4() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("42", JsonNull.INSTANCE);

    // Act and Assert
    assertFalse(jsonObject.has("Member Name"));
  }

  /** Method under test: {@link JsonObject#has(String)} */
  @Test
  public void testHas5() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act and Assert
    assertTrue(jsonObject.has("com.google.gson.JsonObject"));
  }

  /** Method under test: {@link JsonObject#get(String)} */
  @Test
  public void testGet() {
    // Arrange, Act and Assert
    assertNull((new JsonObject()).get("Member Name"));
  }

  /** Method under test: {@link JsonObject#get(String)} */
  @Test
  public void testGet2() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act and Assert
    assertNull(jsonObject.get("Member Name"));
  }

  /** Method under test: {@link JsonObject#get(String)} */
  @Test
  public void testGet3() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act and Assert
    assertNull(jsonObject.get("Member Name"));
  }

  /** Method under test: {@link JsonObject#get(String)} */
  @Test
  public void testGet4() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("42", JsonNull.INSTANCE);

    // Act and Assert
    assertNull(jsonObject.get("Member Name"));
  }

  /** Method under test: {@link JsonObject#get(String)} */
  @Test
  public void testGet5() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act
    JsonElement actualGetResult = jsonObject.get("com.google.gson.JsonObject");

    // Assert
    assertSame(((JsonNull) actualGetResult).INSTANCE, actualGetResult);
  }

  /** Method under test: {@link JsonObject#getAsJsonPrimitive(String)} */
  @Test
  public void testGetAsJsonPrimitive() {
    // Arrange, Act and Assert
    assertNull((new JsonObject()).getAsJsonPrimitive("Member Name"));
  }

  /** Method under test: {@link JsonObject#getAsJsonPrimitive(String)} */
  @Test
  public void testGetAsJsonPrimitive2() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act and Assert
    assertNull(jsonObject.getAsJsonPrimitive("Member Name"));
  }

  /** Method under test: {@link JsonObject#getAsJsonPrimitive(String)} */
  @Test
  public void testGetAsJsonPrimitive3() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act and Assert
    assertNull(jsonObject.getAsJsonPrimitive("Member Name"));
  }

  /** Method under test: {@link JsonObject#getAsJsonPrimitive(String)} */
  @Test
  public void testGetAsJsonPrimitive4() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("42", JsonNull.INSTANCE);

    // Act and Assert
    assertNull(jsonObject.getAsJsonPrimitive("Member Name"));
  }

  /** Method under test: {@link JsonObject#getAsJsonArray(String)} */
  @Test
  public void testGetAsJsonArray() {
    // Arrange, Act and Assert
    assertNull((new JsonObject()).getAsJsonArray("Member Name"));
  }

  /** Method under test: {@link JsonObject#getAsJsonArray(String)} */
  @Test
  public void testGetAsJsonArray2() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act and Assert
    assertNull(jsonObject.getAsJsonArray("Member Name"));
  }

  /** Method under test: {@link JsonObject#getAsJsonArray(String)} */
  @Test
  public void testGetAsJsonArray3() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act and Assert
    assertNull(jsonObject.getAsJsonArray("Member Name"));
  }

  /** Method under test: {@link JsonObject#getAsJsonArray(String)} */
  @Test
  public void testGetAsJsonArray4() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("42", JsonNull.INSTANCE);

    // Act and Assert
    assertNull(jsonObject.getAsJsonArray("Member Name"));
  }

  /** Method under test: {@link JsonObject#getAsJsonObject(String)} */
  @Test
  public void testGetAsJsonObject() {
    // Arrange, Act and Assert
    assertNull((new JsonObject()).getAsJsonObject("Member Name"));
  }

  /** Method under test: {@link JsonObject#getAsJsonObject(String)} */
  @Test
  public void testGetAsJsonObject2() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act and Assert
    assertNull(jsonObject.getAsJsonObject("Member Name"));
  }

  /** Method under test: {@link JsonObject#getAsJsonObject(String)} */
  @Test
  public void testGetAsJsonObject3() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act and Assert
    assertNull(jsonObject.getAsJsonObject("Member Name"));
  }

  /** Method under test: {@link JsonObject#getAsJsonObject(String)} */
  @Test
  public void testGetAsJsonObject4() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("42", JsonNull.INSTANCE);

    // Act and Assert
    assertNull(jsonObject.getAsJsonObject("Member Name"));
  }

  /** Method under test: {@link JsonObject#asMap()} */
  @Test
  public void testAsMap() {
    // Arrange, Act and Assert
    assertTrue((new JsonObject()).asMap().isEmpty());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link JsonObject#equals(Object)}
   *   <li>{@link JsonObject#hashCode()}
   * </ul>
   */
  @Test
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
   * Methods under test:
   *
   * <ul>
   *   <li>{@link JsonObject#equals(Object)}
   *   <li>{@link JsonObject#hashCode()}
   * </ul>
   */
  @Test
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    JsonObject jsonObject = new JsonObject();

    // Act and Assert
    assertEquals(jsonObject, jsonObject);
    int expectedHashCodeResult = jsonObject.hashCode();
    assertEquals(expectedHashCodeResult, jsonObject.hashCode());
  }

  /** Method under test: {@link JsonObject#equals(Object)} */
  @Test
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", JsonNull.INSTANCE);

    // Act and Assert
    assertNotEquals(jsonObject, new JsonObject());
  }

  /** Method under test: {@link JsonObject#equals(Object)} */
  @Test
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", mock(JsonElement.class));

    // Act and Assert
    assertNotEquals(jsonObject, new JsonObject());
  }

  /** Method under test: {@link JsonObject#equals(Object)} */
  @Test
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new JsonObject(), null);
  }

  /** Method under test: {@link JsonObject#equals(Object)} */
  @Test
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new JsonObject(), "Different type to JsonObject");
  }

  /** Method under test: default or parameterless constructor of {@link JsonObject} */
  @Test
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
}
