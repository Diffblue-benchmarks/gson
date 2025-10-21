package com.google.gson.internal.bind;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.Gson;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonArray;
import com.google.gson.JsonArrayDiffblueTestFactory;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.ReflectionAccessFilter;
import com.google.gson.Strictness;
import com.google.gson.ToNumberPolicy;
import com.google.gson.ToNumberStrategy;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.internal.sql.SqlTimestampTypeAdapterDiffblueTestFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.internal.util.reflection.GenericMetadataSupport.WildCardBoundedType;

public class ObjectTypeAdapterDiffblueTest {
  /**
   * Test {@link ObjectTypeAdapter#getFactory(ToNumberStrategy)}.
   *
   * <ul>
   *   <li>Then create {@link Gson#Gson()} and {@link Object} return {@link ObjectTypeAdapter}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#getFactory(ToNumberStrategy)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapterFactory ObjectTypeAdapter.getFactory(ToNumberStrategy)"})
  public void testGetFactory_thenCreateGsonAndObjectReturnObjectTypeAdapter() {
    // Arrange and Act
    TypeAdapterFactory actualFactory = ObjectTypeAdapter.getFactory(mock(ToNumberStrategy.class));
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type);

    // Assert
    assertTrue(actualFactory.create(gson, getResult) instanceof ObjectTypeAdapter);
  }

  /**
   * Test {@link ObjectTypeAdapter#getFactory(ToNumberStrategy)}.
   *
   * <ul>
   *   <li>When {@link ToNumberPolicy#DOUBLE}.
   *   <li>Then create {@link Gson#Gson()} and {@link Object} return {@link ObjectTypeAdapter}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#getFactory(ToNumberStrategy)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapterFactory ObjectTypeAdapter.getFactory(ToNumberStrategy)"})
  public void testGetFactory_whenDouble_thenCreateGsonAndObjectReturnObjectTypeAdapter() {
    // Arrange and Act
    TypeAdapterFactory actualFactory = ObjectTypeAdapter.getFactory(ToNumberPolicy.DOUBLE);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type);

    // Assert
    assertTrue(actualFactory.create(gson, getResult) instanceof ObjectTypeAdapter);
  }

  /**
   * Test {@link ObjectTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>Then return size is two.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ObjectTypeAdapter.read(JsonReader)"})
  public void testRead_given42_thenReturnSizeIsTwo() throws IOException {
    // Arrange
    ObjectTypeAdapter createLongOrDoubleAdapterResult =
        ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();

    JsonObject element = new JsonObject();
    element.add("42", JsonArrayDiffblueTestFactory.createJsonArrayWithElements());
    element.add(
        "com.google.gson.JsonObject", JsonArrayDiffblueTestFactory.createJsonArrayWithElements());

    // Act
    Object actualReadResult = createLongOrDoubleAdapterResult.read(new JsonTreeReader(element));

    // Assert
    assertTrue(actualReadResult instanceof Map);
    assertEquals(2, ((Map<String, ArrayList>) actualReadResult).size());
    ArrayList getResult = ((Map<String, ArrayList>) actualReadResult).get("42");
    assertEquals(2, getResult.size());
    assertEquals("element0", getResult.get(0));
    assertEquals("element1", getResult.get(1));
    assertEquals(
        getResult, ((Map<String, ArrayList>) actualReadResult).get("com.google.gson.JsonObject"));
  }

  /**
   * Test {@link ObjectTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given createDoubleAdapter.
   *   <li>Then return third doubleValue is one.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ObjectTypeAdapter.read(JsonReader)"})
  public void testRead_givenCreateDoubleAdapter_thenReturnThirdDoubleValueIsOne()
      throws IOException {
    // Arrange
    ObjectTypeAdapter createDoubleAdapterResult =
        ObjectTypeAdapterDiffblueTestFactory.createDoubleAdapter();

    JsonArray element = JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    element.add(Integer.valueOf(1));
    element.add(true);

    // Act
    Object actualReadResult = createDoubleAdapterResult.read(new JsonTreeReader(element));

    // Assert
    assertTrue(actualReadResult instanceof List);
    assertEquals(4, ((List<Object>) actualReadResult).size());
    assertEquals(1.0d, ((Double) ((List<Object>) actualReadResult).get(2)).doubleValue(), 0.0);
    assertTrue((Boolean) ((List<Object>) actualReadResult).get(3));
  }

  /**
   * Test {@link ObjectTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given empty string.
   *   <li>Then return empty string size is two.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ObjectTypeAdapter.read(JsonReader)"})
  public void testRead_givenEmptyString_thenReturnEmptyStringSizeIsTwo() throws IOException {
    // Arrange
    ObjectTypeAdapter createLongOrDoubleAdapterResult =
        ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();

    JsonObject element = new JsonObject();
    element.add("", JsonArrayDiffblueTestFactory.createJsonArrayWithElements());
    element.add("42", JsonArrayDiffblueTestFactory.createJsonArrayWithElements());
    element.add(
        "com.google.gson.JsonObject", JsonArrayDiffblueTestFactory.createJsonArrayWithElements());

    // Act
    Object actualReadResult = createLongOrDoubleAdapterResult.read(new JsonTreeReader(element));

    // Assert
    assertTrue(actualReadResult instanceof Map);
    assertEquals(3, ((Map<String, ArrayList>) actualReadResult).size());
    ArrayList getResult = ((Map<String, ArrayList>) actualReadResult).get("");
    assertEquals(2, getResult.size());
    assertEquals("element0", getResult.get(0));
    assertEquals("element1", getResult.get(1));
    assertEquals(getResult, ((Map<String, ArrayList>) actualReadResult).get("42"));
    assertEquals(
        getResult, ((Map<String, ArrayList>) actualReadResult).get("com.google.gson.JsonObject"));
  }

  /**
   * Test {@link ObjectTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@link JsonNull#INSTANCE}.
   *   <li>Then return {@code JsonObject} is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ObjectTypeAdapter.read(JsonReader)"})
  public void testRead_givenInstance_thenReturnComGoogleGsonJsonObjectIsNull() throws IOException {
    // Arrange
    ObjectTypeAdapter createLongOrDoubleAdapterResult =
        ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();

    JsonObject element = new JsonObject();
    element.add("com.google.gson.JsonObject", JsonNull.INSTANCE);
    JsonTreeReader in = new JsonTreeReader(element);

    // Act
    Object actualReadResult = createLongOrDoubleAdapterResult.read(in);

    // Assert
    assertTrue(actualReadResult instanceof Map);
    assertEquals(1, ((Map<String, Object>) actualReadResult).size());
    assertNull(((Map<String, Object>) actualReadResult).get("com.google.gson.JsonObject"));
    assertEquals(0, in.getStackSize());
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link ObjectTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor).
   *   <li>Then third return {@link Map}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ObjectTypeAdapter.read(JsonReader)"})
  public void testRead_givenJsonObject_thenThirdReturnMap() throws IOException {
    // Arrange
    ObjectTypeAdapter createLongOrDoubleAdapterResult =
        ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();

    JsonArray element = JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    element.add(new JsonObject());
    element.add(true);

    // Act
    Object actualReadResult = createLongOrDoubleAdapterResult.read(new JsonTreeReader(element));

    // Assert
    assertTrue(actualReadResult instanceof List);
    assertEquals(4, ((List<Object>) actualReadResult).size());
    Object getResult = ((List<Object>) actualReadResult).get(2);
    assertTrue(getResult instanceof Map);
    assertTrue(((Map<Object, Object>) getResult).isEmpty());
    assertTrue((Boolean) ((List<Object>) actualReadResult).get(3));
  }

  /**
   * Test {@link ObjectTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>Then return {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ObjectTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_thenReturnFoo() throws IOException {
    // Arrange
    ObjectTypeAdapter createLongOrDoubleAdapterResult =
        ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();

    JsonReader in = new JsonReader(new StringReader("foo"));
    in.setStrictness(Strictness.LENIENT);

    // Act
    Object actualReadResult = createLongOrDoubleAdapterResult.read(in);

    // Assert
    assertEquals("foo", actualReadResult);
  }

  /**
   * Test {@link ObjectTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ObjectTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_whenStringReaderWithFalseToString_thenReturnFalse()
      throws IOException {
    // Arrange
    ObjectTypeAdapter createLongOrDoubleAdapterResult =
        ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();
    StringReader in = new StringReader(Boolean.FALSE.toString());

    JsonReader in2 = new JsonReader(in);
    in2.setStrictness(Strictness.LENIENT);

    // Act
    Object actualReadResult = createLongOrDoubleAdapterResult.read(in2);

    // Assert
    assertFalse((Boolean) actualReadResult);
  }

  /**
   * Test {@link ObjectTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When createJsonArrayWithElements add {@code null}.
   *   <li>Then return third is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ObjectTypeAdapter.read(JsonReader)"})
  public void testRead_givenNull_whenCreateJsonArrayWithElementsAddNull_thenReturnThirdIsNull()
      throws IOException {
    // Arrange
    ObjectTypeAdapter createLongOrDoubleAdapterResult =
        ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();

    JsonArray element = JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    element.add((Boolean) null);

    // Act
    Object actualReadResult = createLongOrDoubleAdapterResult.read(new JsonTreeReader(element));

    // Assert
    assertTrue(actualReadResult instanceof List);
    assertEquals(3, ((List<String>) actualReadResult).size());
    assertEquals("element0", ((List<String>) actualReadResult).get(0));
    assertEquals("element1", ((List<String>) actualReadResult).get(1));
    assertNull(((List<String>) actualReadResult).get(2));
  }

  /**
   * Test {@link ObjectTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code Property}.
   *   <li>Then return {@code 42} is {@code JsonObject}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ObjectTypeAdapter.read(JsonReader)"})
  public void testRead_givenProperty_thenReturn42IsComGoogleGsonJsonObject() throws IOException {
    // Arrange
    ObjectTypeAdapter createLongOrDoubleAdapterResult =
        ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();

    JsonObject element = new JsonObject();
    element.add(
        "com.google.gson.JsonObject", JsonArrayDiffblueTestFactory.createJsonArrayWithElements());
    element.add("Property", JsonArrayDiffblueTestFactory.createJsonArrayWithElements());
    element.add("42", JsonArrayDiffblueTestFactory.createJsonArrayWithElements());
    element.add(
        "com.google.gson.JsonObject", JsonArrayDiffblueTestFactory.createJsonArrayWithElements());

    // Act
    Object actualReadResult = createLongOrDoubleAdapterResult.read(new JsonTreeReader(element));

    // Assert
    assertTrue(actualReadResult instanceof Map);
    assertEquals(3, ((Map<String, ArrayList>) actualReadResult).size());
    ArrayList getResult =
        ((Map<String, ArrayList>) actualReadResult).get("com.google.gson.JsonObject");
    assertEquals(2, getResult.size());
    assertEquals("element0", getResult.get(0));
    assertEquals("element1", getResult.get(1));
    assertEquals(getResult, ((Map<String, ArrayList>) actualReadResult).get("42"));
    assertEquals(getResult, ((Map<String, ArrayList>) actualReadResult).get("Property"));
  }

  /**
   * Test {@link ObjectTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code Property}.
   *   <li>Then return {@code 42} is {@code JsonObject}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ObjectTypeAdapter.read(JsonReader)"})
  public void testRead_givenProperty_thenReturn42IsComGoogleGsonJsonObject2() throws IOException {
    // Arrange
    ObjectTypeAdapter createLongOrDoubleAdapterResult =
        ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();

    JsonObject element = new JsonObject();
    element.add(
        "com.google.gson.JsonObject", JsonArrayDiffblueTestFactory.createJsonArrayWithElements());
    element.add("42", JsonArrayDiffblueTestFactory.createJsonArrayWithElements());
    element.add(
        "com.google.gson.JsonObject", JsonArrayDiffblueTestFactory.createJsonArrayWithElements());
    element.add("Property", JsonArrayDiffblueTestFactory.createJsonArrayWithElements());
    element.add("42", JsonArrayDiffblueTestFactory.createJsonArrayWithElements());
    element.add(
        "com.google.gson.JsonObject", JsonArrayDiffblueTestFactory.createJsonArrayWithElements());

    // Act
    Object actualReadResult = createLongOrDoubleAdapterResult.read(new JsonTreeReader(element));

    // Assert
    assertTrue(actualReadResult instanceof Map);
    assertEquals(3, ((Map<String, ArrayList>) actualReadResult).size());
    ArrayList getResult =
        ((Map<String, ArrayList>) actualReadResult).get("com.google.gson.JsonObject");
    assertEquals(2, getResult.size());
    assertEquals("element0", getResult.get(0));
    assertEquals("element1", getResult.get(1));
    assertEquals(getResult, ((Map<String, ArrayList>) actualReadResult).get("42"));
    assertEquals(getResult, ((Map<String, ArrayList>) actualReadResult).get("Property"));
  }

  /**
   * Test {@link ObjectTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code Property}.
   *   <li>Then return {@code Property} is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ObjectTypeAdapter.read(JsonReader)"})
  public void testRead_givenProperty_thenReturnPropertyIs42() throws IOException {
    // Arrange
    ObjectTypeAdapter createLongOrDoubleAdapterResult =
        ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();

    JsonObject element = new JsonObject();
    element.add("42", JsonArrayDiffblueTestFactory.createJsonArrayWithElements());
    element.add(
        "com.google.gson.JsonObject", JsonArrayDiffblueTestFactory.createJsonArrayWithElements());
    element.add("Property", JsonArrayDiffblueTestFactory.createJsonArrayWithElements());
    element.add("42", JsonArrayDiffblueTestFactory.createJsonArrayWithElements());
    element.add(
        "com.google.gson.JsonObject", JsonArrayDiffblueTestFactory.createJsonArrayWithElements());

    // Act
    Object actualReadResult = createLongOrDoubleAdapterResult.read(new JsonTreeReader(element));

    // Assert
    assertTrue(actualReadResult instanceof Map);
    assertEquals(3, ((Map<String, ArrayList>) actualReadResult).size());
    ArrayList getResult = ((Map<String, ArrayList>) actualReadResult).get("42");
    assertEquals(2, getResult.size());
    assertEquals("element0", getResult.get(0));
    assertEquals("element1", getResult.get(1));
    assertEquals(getResult, ((Map<String, ArrayList>) actualReadResult).get("Property"));
    assertEquals(
        getResult, ((Map<String, ArrayList>) actualReadResult).get("com.google.gson.JsonObject"));
  }

  /**
   * Test {@link ObjectTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code Property}.
   *   <li>Then return {@code Property} size is two.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ObjectTypeAdapter.read(JsonReader)"})
  public void testRead_givenProperty_thenReturnPropertySizeIsTwo() throws IOException {
    // Arrange
    ObjectTypeAdapter createLongOrDoubleAdapterResult =
        ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();

    JsonObject element = new JsonObject();
    element.add("Property", JsonArrayDiffblueTestFactory.createJsonArrayWithElements());
    element.add("42", JsonArrayDiffblueTestFactory.createJsonArrayWithElements());
    element.add(
        "com.google.gson.JsonObject", JsonArrayDiffblueTestFactory.createJsonArrayWithElements());

    // Act
    Object actualReadResult = createLongOrDoubleAdapterResult.read(new JsonTreeReader(element));

    // Assert
    assertTrue(actualReadResult instanceof Map);
    assertEquals(3, ((Map<String, ArrayList>) actualReadResult).size());
    ArrayList getResult = ((Map<String, ArrayList>) actualReadResult).get("Property");
    assertEquals(2, getResult.size());
    assertEquals("element0", getResult.get(0));
    assertEquals("element1", getResult.get(1));
    assertEquals(getResult, ((Map<String, ArrayList>) actualReadResult).get("42"));
    assertEquals(
        getResult, ((Map<String, ArrayList>) actualReadResult).get("com.google.gson.JsonObject"));
  }

  /**
   * Test {@link ObjectTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code true}.
   *   <li>When createJsonArrayWithElements add {@code true}.
   *   <li>Then return third.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ObjectTypeAdapter.read(JsonReader)"})
  public void testRead_givenTrue_whenCreateJsonArrayWithElementsAddTrue_thenReturnThird()
      throws IOException {
    // Arrange
    ObjectTypeAdapter createLongOrDoubleAdapterResult =
        ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();

    JsonArray element = JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    element.add(true);

    // Act
    Object actualReadResult = createLongOrDoubleAdapterResult.read(new JsonTreeReader(element));

    // Assert
    assertTrue(actualReadResult instanceof List);
    assertEquals(3, ((List<Object>) actualReadResult).size());
    assertTrue((Boolean) ((List<Object>) actualReadResult).get(2));
  }

  /**
   * Test {@link ObjectTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given valueOf one.
   *   <li>Then return third longValue is one.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ObjectTypeAdapter.read(JsonReader)"})
  public void testRead_givenValueOfOne_thenReturnThirdLongValueIsOne() throws IOException {
    // Arrange
    ObjectTypeAdapter createLongOrDoubleAdapterResult =
        ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();

    JsonArray element = JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    element.add(Integer.valueOf(1));
    element.add(true);

    // Act
    Object actualReadResult = createLongOrDoubleAdapterResult.read(new JsonTreeReader(element));

    // Assert
    assertTrue(actualReadResult instanceof List);
    assertEquals(4, ((List<Object>) actualReadResult).size());
    assertEquals(1L, ((Long) ((List<Object>) actualReadResult).get(2)).longValue());
    assertTrue((Boolean) ((List<Object>) actualReadResult).get(3));
  }

  /**
   * Test {@link ObjectTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Then {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonPrimitive#JsonPrimitive(Boolean)} StackSize is zero.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ObjectTypeAdapter.read(JsonReader)"})
  public void testRead_thenJsonTreeReaderWithElementIsJsonPrimitiveStackSizeIsZero()
      throws IOException {
    // Arrange
    ObjectTypeAdapter createLongOrDoubleAdapterResult =
        ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();
    JsonTreeReader in = new JsonTreeReader(new JsonPrimitive(true));

    // Act
    Object actualReadResult = createLongOrDoubleAdapterResult.read(in);

    // Assert
    assertEquals(0, in.getStackSize());
    assertFalse(in.hasNext());
    assertTrue((Boolean) actualReadResult);
  }

  /**
   * Test {@link ObjectTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ObjectTypeAdapter.read(JsonReader)"})
  public void testRead_thenReturnSizeIsOne() throws IOException {
    // Arrange
    ObjectTypeAdapter createLongOrDoubleAdapterResult =
        ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();

    JsonObject element = new JsonObject();
    element.add(
        "com.google.gson.JsonObject", JsonArrayDiffblueTestFactory.createJsonArrayWithElements());

    // Act
    Object actualReadResult = createLongOrDoubleAdapterResult.read(new JsonTreeReader(element));

    // Assert
    assertTrue(actualReadResult instanceof Map);
    assertEquals(1, ((Map<String, ArrayList>) actualReadResult).size());
    ArrayList getResult =
        ((Map<String, ArrayList>) actualReadResult).get("com.google.gson.JsonObject");
    assertEquals(2, getResult.size());
    assertEquals("element0", getResult.get(0));
    assertEquals("element1", getResult.get(1));
  }

  /**
   * Test {@link ObjectTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Then return size is two.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ObjectTypeAdapter.read(JsonReader)"})
  public void testRead_thenReturnSizeIsTwo() throws IOException {
    // Arrange
    ObjectTypeAdapter createLongOrDoubleAdapterResult =
        ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();

    // Act
    Object actualReadResult =
        createLongOrDoubleAdapterResult.read(
            new JsonTreeReader(JsonArrayDiffblueTestFactory.createJsonArrayWithElements()));

    // Assert
    assertTrue(actualReadResult instanceof List);
    assertEquals(2, ((List<String>) actualReadResult).size());
    assertEquals("element0", ((List<String>) actualReadResult).get(0));
    assertEquals("element1", ((List<String>) actualReadResult).get(1));
  }

  /**
   * Test {@link ObjectTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Then third return {@link List}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ObjectTypeAdapter.read(JsonReader)"})
  public void testRead_thenThirdReturnList() throws IOException {
    // Arrange
    ObjectTypeAdapter createLongOrDoubleAdapterResult =
        ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();

    JsonArray element = JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    element.add(JsonArrayDiffblueTestFactory.createJsonArrayWithElements());
    element.add(true);

    // Act
    Object actualReadResult = createLongOrDoubleAdapterResult.read(new JsonTreeReader(element));

    // Assert
    assertTrue(actualReadResult instanceof List);
    assertEquals(4, ((List<Object>) actualReadResult).size());
    Object getResult = ((List<Object>) actualReadResult).get(2);
    assertTrue(getResult instanceof List);
    assertEquals(2, ((List<String>) getResult).size());
    assertEquals("element0", ((List<String>) getResult).get(0));
    assertEquals("element1", ((List<String>) getResult).get(1));
  }

  /**
   * Test {@link ObjectTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonPrimitive#JsonPrimitive(String)} with {@code String}.
   *   <li>Then return {@code String}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ObjectTypeAdapter.read(JsonReader)"})
  public void testRead_whenJsonPrimitiveWithString_thenReturnString() throws IOException {
    // Arrange
    ObjectTypeAdapter createLongOrDoubleAdapterResult =
        ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();
    JsonTreeReader in = new JsonTreeReader(new JsonPrimitive("String"));

    // Act
    Object actualReadResult = createLongOrDoubleAdapterResult.read(in);

    // Assert
    assertEquals("String", actualReadResult);
    assertEquals(0, in.getStackSize());
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link ObjectTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ObjectTypeAdapter.read(JsonReader)"})
  public void testRead_whenJsonTreeReaderWithElementIsInstance_thenReturnNull() throws IOException {
    // Arrange
    ObjectTypeAdapter createLongOrDoubleAdapterResult =
        ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();
    JsonTreeReader in = new JsonTreeReader(JsonNull.INSTANCE);

    // Act
    Object actualReadResult = createLongOrDoubleAdapterResult.read(in);

    // Assert
    assertNull(actualReadResult);
    assertEquals(0, in.getStackSize());
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link ObjectTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonObject} (default constructor).
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ObjectTypeAdapter.read(JsonReader)"})
  public void testRead_whenJsonTreeReaderWithElementIsJsonObject_thenReturnEmpty()
      throws IOException {
    // Arrange
    ObjectTypeAdapter createLongOrDoubleAdapterResult =
        ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();
    JsonTreeReader in = new JsonTreeReader(new JsonObject());

    // Act
    Object actualReadResult = createLongOrDoubleAdapterResult.read(in);

    // Assert
    assertTrue(actualReadResult instanceof Map);
    assertEquals(0, in.getStackSize());
    assertFalse(in.hasNext());
    assertTrue(((Map<Object, Object>) actualReadResult).isEmpty());
  }

  /**
   * Test {@link ObjectTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ObjectTypeAdapter.read(JsonReader)"})
  public void testRead_whenStringReaderWithFalseToString_thenReturnFalse() throws IOException {
    // Arrange
    ObjectTypeAdapter createLongOrDoubleAdapterResult =
        ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();
    StringReader in = new StringReader(Boolean.FALSE.toString());

    // Act
    Object actualReadResult = createLongOrDoubleAdapterResult.read(new JsonReader(in));

    // Assert
    assertFalse((Boolean) actualReadResult);
  }

  /**
   * Test {@link ObjectTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link ReflectionAccessFilter#BLOCK_ALL_ANDROID}.
   *   <li>When {@link ArrayList#ArrayList()} add {@link ReflectionAccessFilter#BLOCK_ALL_ANDROID}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ObjectTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_givenBlock_all_android_whenArrayListAddBlock_all_android()
      throws IOException {
    // Arrange
    ObjectTypeAdapter createLazilyParsedNumberAdapterResult =
        ObjectTypeAdapterDiffblueTestFactory.createLazilyParsedNumberAdapter();
    JsonTreeWriter out = new JsonTreeWriter();

    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();
    reflectionFilters.add(ReflectionAccessFilter.BLOCK_ALL_ANDROID);

    // Act
    createLazilyParsedNumberAdapterResult.write(
        out,
        new CollectionTypeAdapterFactory(
            new ConstructorConstructor(new HashMap<>(), true, reflectionFilters)));

    // Assert
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonObject);
    assertEquals(1, ((JsonObject) getResult).size());
    assertFalse(((JsonObject) getResult).isEmpty());
    assertTrue(getResult.isJsonObject());
    assertSame(getResult, getResult.getAsJsonObject());
  }

  /**
   * Test {@link ObjectTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given createLazilyParsedNumberAdapter.
   *   <li>Then {@link JsonTreeWriter} (default constructor) {@link JsonObject}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ObjectTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_givenCreateLazilyParsedNumberAdapter_thenJsonTreeWriterJsonObject()
      throws IOException {
    // Arrange
    ObjectTypeAdapter createLazilyParsedNumberAdapterResult =
        ObjectTypeAdapterDiffblueTestFactory.createLazilyParsedNumberAdapter();
    JsonTreeWriter out = new JsonTreeWriter();
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();

    // Act
    createLazilyParsedNumberAdapterResult.write(
        out,
        new CollectionTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>())));

    // Assert
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonObject);
    assertEquals(1, ((JsonObject) getResult).size());
    assertFalse(((JsonObject) getResult).isEmpty());
    assertTrue(getResult.isJsonObject());
    assertSame(getResult, getResult.getAsJsonObject());
  }

  /**
   * Test {@link ObjectTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given createLazilyParsedNumberAdapter.
   *   <li>When createSqlTimestampTypeAdapter.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ObjectTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_givenCreateLazilyParsedNumberAdapter_whenCreateSqlTimestampTypeAdapter()
      throws IOException {
    // Arrange
    ObjectTypeAdapter createLazilyParsedNumberAdapterResult =
        ObjectTypeAdapterDiffblueTestFactory.createLazilyParsedNumberAdapter();
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    createLazilyParsedNumberAdapterResult.write(
        out, SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter());

    // Assert
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonObject);
    assertEquals(1, ((JsonObject) getResult).size());
    assertFalse(((JsonObject) getResult).isEmpty());
    assertTrue(getResult.isJsonObject());
    assertSame(getResult, getResult.getAsJsonObject());
  }

  /**
   * Test {@link ObjectTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given createLongOrDoubleAdapter.
   *   <li>When {@code null}.
   *   <li>Then {@link JsonTreeWriter} (default constructor) {@link JsonNull}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ObjectTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_givenCreateLongOrDoubleAdapter_whenNull_thenJsonTreeWriterJsonNull()
      throws IOException {
    // Arrange
    ObjectTypeAdapter createLongOrDoubleAdapterResult =
        ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    createLongOrDoubleAdapterResult.write(out, null);

    // Assert that nothing has changed
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonNull);
    assertFalse(getResult.isJsonObject());
  }

  /**
   * Test {@link ObjectTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then {@link JsonTreeWriter} (default constructor) AsNumber {@link LazilyParsedNumber}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ObjectTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_when42_thenJsonTreeWriterAsNumberLazilyParsedNumber() throws IOException {
    // Arrange
    ObjectTypeAdapter createLongOrDoubleAdapterResult =
        ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    createLongOrDoubleAdapterResult.write(out, "42");

    // Assert
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonPrimitive);
    Number asNumber = getResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("42", asNumber.toString());
    assertFalse(((JsonPrimitive) getResult).isNumber());
    assertTrue(((JsonPrimitive) getResult).isString());
  }

  /**
   * Test {@link ObjectTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>When createLongOrDoubleAdapter.
   *   <li>Then {@link JsonTreeWriter} (default constructor) {@link JsonObject}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ObjectTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_whenCreateLongOrDoubleAdapter_thenJsonTreeWriterJsonObject()
      throws IOException {
    // Arrange
    ObjectTypeAdapter createLongOrDoubleAdapterResult =
        ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    createLongOrDoubleAdapterResult.write(
        out, NumberTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter());

    // Assert
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonObject);
    assertEquals(1, ((JsonObject) getResult).size());
    assertFalse(((JsonObject) getResult).isEmpty());
    assertTrue(getResult.isJsonObject());
    assertSame(getResult, getResult.getAsJsonObject());
  }

  /**
   * Test {@link ObjectTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>When forty-two.
   *   <li>Then {@link JsonTreeWriter} (default constructor) AsNumber longValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ObjectTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_whenFortyTwo_thenJsonTreeWriterAsNumberLongValueIsFortyTwo()
      throws IOException {
    // Arrange
    ObjectTypeAdapter createLongOrDoubleAdapterResult =
        ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    createLongOrDoubleAdapterResult.write(out, 42);

    // Assert
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonPrimitive);
    assertEquals(42L, getResult.getAsNumber().longValue());
    assertFalse(((JsonPrimitive) getResult).isString());
    assertTrue(((JsonPrimitive) getResult).isNumber());
  }

  /**
   * Test {@link ObjectTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>When {@link WildCardBoundedType#WildCardBoundedType(WildcardType)} with wildcard is
   *       {@code null}.
   *   <li>Then {@link JsonTreeWriter} (default constructor) {@link JsonObject}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ObjectTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_whenWildCardBoundedTypeWithWildcardIsNull_thenJsonTreeWriterJsonObject()
      throws IOException {
    // Arrange
    ObjectTypeAdapter createLongOrDoubleAdapterResult =
        ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    createLongOrDoubleAdapterResult.write(out, new WildCardBoundedType(null));

    // Assert
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonObject);
    assertEquals(1, ((JsonObject) getResult).size());
    assertFalse(((JsonObject) getResult).isEmpty());
    assertTrue(getResult.isJsonObject());
    assertSame(getResult, getResult.getAsJsonObject());
  }
}
