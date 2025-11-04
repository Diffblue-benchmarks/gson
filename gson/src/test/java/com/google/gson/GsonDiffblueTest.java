package com.google.gson;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.gson.internal.Excluder;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.internal.bind.ArrayTypeAdapter;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.internal.bind.ObjectTypeAdapter;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.google.gson.internal.bind.TreeTypeAdapter;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.BufferedWriter;
import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.EOFException;
import java.io.FileDescriptor;
import java.io.FileReader;
import java.io.IOException;
import java.io.PipedWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.GenericMetadataSupport;

public class GsonDiffblueTest {
  /** Method under test: {@link Gson#checkValidFloatingPoint(double)} */
  @Test
  public void testCheckValidFloatingPoint() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> Gson.checkValidFloatingPoint(Double.NaN));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, TypeToken)} */
  @Test
  public void testFromJson() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertNull(gson.fromJson(JsonNull.INSTANCE, typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, TypeToken)} */
  @Test
  public void testFromJson2() throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            255,
            255,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertNull(gson.fromJson(JsonNull.INSTANCE, typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, TypeToken)} */
  @Test
  public void testFromJson3() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonObject json = new JsonObject();
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(json, typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertTrue(((Map<Object, Object>) actualFromJsonResult).isEmpty());
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, TypeToken)} */
  @Test
  public void testFromJson4() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonArray json = new JsonArray(3);
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(json, typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof List);
    assertTrue(((List<Object>) actualFromJsonResult).isEmpty());
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, TypeToken)} */
  @Test
  public void testFromJson5() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonPrimitive json = new JsonPrimitive("in == null");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertEquals("in == null", gson.fromJson(json, typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, TypeToken)} */
  @Test
  public void testFromJson6() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertNull(gson.fromJson((JsonElement) null, typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, TypeToken)} */
  @Test
  public void testFromJson7() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(json, typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(1, ((Map<String, Object>) actualFromJsonResult).size());
    assertNull(((Map<String, Object>) actualFromJsonResult).get("in == null"));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, TypeToken)} */
  @Test
  public void testFromJson8() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(json, typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(2, ((Map<String, Object>) actualFromJsonResult).size());
    assertNull(((Map<String, Object>) actualFromJsonResult).get("in == null"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("type must not be null"));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, TypeToken)} */
  @Test
  public void testFromJson9() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.addProperty("type must not be null", "in == null");
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(json, typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(2, ((Map<String, String>) actualFromJsonResult).size());
    assertEquals(
        "in == null", ((Map<String, String>) actualFromJsonResult).get("type must not be null"));
    assertNull(((Map<String, String>) actualFromJsonResult).get("in == null"));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, TypeToken)} */
  @Test
  public void testFromJson10() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.addProperty("type must not be null", Integer.valueOf(255));
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(json, typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(2, ((Map<String, Double>) actualFromJsonResult).size());
    assertNull(((Map<String, Double>) actualFromJsonResult).get("in == null"));
    assertEquals(
        255.0d,
        ((Map<String, Double>) actualFromJsonResult).get("type must not be null").doubleValue(),
        0.0);
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, TypeToken)} */
  @Test
  public void testFromJson11() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.addProperty("type must not be null", false);
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(json, typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(2, ((Map<String, Boolean>) actualFromJsonResult).size());
    assertNull(((Map<String, Boolean>) actualFromJsonResult).get("in == null"));
    assertFalse(((Map<String, Boolean>) actualFromJsonResult).get("type must not be null"));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, TypeToken)} */
  @Test
  public void testFromJson12() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("Property", JsonNull.INSTANCE);
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(json, typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(3, ((Map<String, Object>) actualFromJsonResult).size());
    assertNull(((Map<String, Object>) actualFromJsonResult).get("Property"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("in == null"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("type must not be null"));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, TypeToken)} */
  @Test
  public void testFromJson13() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("in == null", JsonNull.INSTANCE);
    json.add("Property", JsonNull.INSTANCE);
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(json, typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(3, ((Map<String, Object>) actualFromJsonResult).size());
    assertNull(((Map<String, Object>) actualFromJsonResult).get("Property"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("in == null"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("type must not be null"));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, TypeToken)} */
  @Test
  public void testFromJson14() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("in == null", JsonNull.INSTANCE);
    json.add("Property", JsonNull.INSTANCE);
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(json, typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(3, ((Map<String, Object>) actualFromJsonResult).size());
    assertNull(((Map<String, Object>) actualFromJsonResult).get("Property"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("in == null"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("type must not be null"));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, TypeToken)} */
  @Test
  public void testFromJson15() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("Property", JsonNull.INSTANCE);
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(json, typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(3, ((Map<String, Object>) actualFromJsonResult).size());
    assertNull(((Map<String, Object>) actualFromJsonResult).get("Property"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("in == null"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("type must not be null"));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, TypeToken)} */
  @Test
  public void testFromJson16() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("42", JsonNull.INSTANCE);
    json.add("Property", JsonNull.INSTANCE);
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(json, typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(4, ((Map<String, Object>) actualFromJsonResult).size());
    assertNull(((Map<String, Object>) actualFromJsonResult).get("42"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("Property"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("in == null"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("type must not be null"));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, TypeToken)} */
  @Test
  public void testFromJson17() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonArray json = new JsonArray(3);
    json.add(false);
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(json, typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof List);
    assertEquals(1, ((List<Boolean>) actualFromJsonResult).size());
    assertFalse(((List<Boolean>) actualFromJsonResult).get(0));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Class)} */
  @Test
  public void testFromJson18() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertNull(gson.fromJson(JsonNull.INSTANCE, classOfT));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Class)} */
  @Test
  public void testFromJson19() throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertNull(gson.fromJson(JsonNull.INSTANCE, classOfT));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Class)} */
  @Test
  public void testFromJson20() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonObject json = new JsonObject();
    Class<Object> classOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertTrue(((Map<Object, Object>) actualFromJsonResult).isEmpty());
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Class)} */
  @Test
  public void testFromJson21() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonArray json = new JsonArray(3);
    Class<Object> classOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof List);
    assertTrue(((List<Object>) actualFromJsonResult).isEmpty());
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Class)} */
  @Test
  public void testFromJson22() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonPrimitive json = new JsonPrimitive("in == null");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertEquals("in == null", gson.fromJson(json, classOfT));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Class)} */
  @Test
  public void testFromJson23() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertNull(gson.fromJson((JsonElement) null, classOfT));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Class)} */
  @Test
  public void testFromJson24() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<TypeAdapterFactory> classOfT = TypeAdapterFactory.class;

    // Act and Assert
    assertNull(gson.fromJson(JsonNull.INSTANCE, classOfT));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Class)} */
  @Test
  public void testFromJson25() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<LazilyParsedNumber> classOfT = LazilyParsedNumber.class;

    // Act and Assert
    assertNull(gson.fromJson(JsonNull.INSTANCE, classOfT));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Class)} */
  @Test
  public void testFromJson26() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Long> classOfT = Long.class;

    // Act and Assert
    assertNull(gson.fromJson(JsonNull.INSTANCE, classOfT));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Class)} */
  @Test
  public void testFromJson27() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> classOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(1, ((Map<String, Object>) actualFromJsonResult).size());
    assertNull(((Map<String, Object>) actualFromJsonResult).get("in == null"));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Class)} */
  @Test
  public void testFromJson28() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> classOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(2, ((Map<String, Object>) actualFromJsonResult).size());
    assertNull(((Map<String, Object>) actualFromJsonResult).get("in == null"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("type must not be null"));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Class)} */
  @Test
  public void testFromJson29() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.addProperty("type must not be null", "in == null");
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> classOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(2, ((Map<String, String>) actualFromJsonResult).size());
    assertEquals(
        "in == null", ((Map<String, String>) actualFromJsonResult).get("type must not be null"));
    assertNull(((Map<String, String>) actualFromJsonResult).get("in == null"));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Class)} */
  @Test
  public void testFromJson30() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.addProperty("type must not be null", Integer.valueOf(1));
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> classOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(2, ((Map<String, Double>) actualFromJsonResult).size());
    assertNull(((Map<String, Double>) actualFromJsonResult).get("in == null"));
    assertEquals(
        1.0d,
        ((Map<String, Double>) actualFromJsonResult).get("type must not be null").doubleValue(),
        0.0);
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Class)} */
  @Test
  public void testFromJson31() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.addProperty("type must not be null", false);
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> classOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(2, ((Map<String, Boolean>) actualFromJsonResult).size());
    assertNull(((Map<String, Boolean>) actualFromJsonResult).get("in == null"));
    assertFalse(((Map<String, Boolean>) actualFromJsonResult).get("type must not be null"));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Class)} */
  @Test
  public void testFromJson32() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("Property", JsonNull.INSTANCE);
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> classOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(3, ((Map<String, Object>) actualFromJsonResult).size());
    assertNull(((Map<String, Object>) actualFromJsonResult).get("Property"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("in == null"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("type must not be null"));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Class)} */
  @Test
  public void testFromJson33() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("in == null", JsonNull.INSTANCE);
    json.add("Property", JsonNull.INSTANCE);
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> classOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(3, ((Map<String, Object>) actualFromJsonResult).size());
    assertNull(((Map<String, Object>) actualFromJsonResult).get("Property"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("in == null"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("type must not be null"));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Class)} */
  @Test
  public void testFromJson34() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("in == null", JsonNull.INSTANCE);
    json.add("Property", JsonNull.INSTANCE);
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> classOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(3, ((Map<String, Object>) actualFromJsonResult).size());
    assertNull(((Map<String, Object>) actualFromJsonResult).get("Property"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("in == null"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("type must not be null"));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Class)} */
  @Test
  public void testFromJson35() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("Property", JsonNull.INSTANCE);
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> classOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(3, ((Map<String, Object>) actualFromJsonResult).size());
    assertNull(((Map<String, Object>) actualFromJsonResult).get("Property"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("in == null"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("type must not be null"));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Class)} */
  @Test
  public void testFromJson36() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("42", JsonNull.INSTANCE);
    json.add("Property", JsonNull.INSTANCE);
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> classOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(4, ((Map<String, Object>) actualFromJsonResult).size());
    assertNull(((Map<String, Object>) actualFromJsonResult).get("42"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("Property"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("in == null"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("type must not be null"));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Class)} */
  @Test
  public void testFromJson37() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonObject json = new JsonObject();
    Class<LazilyParsedNumber> classOfT = LazilyParsedNumber.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, classOfT));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Class)} */
  @Test
  public void testFromJson38() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonObject json = new JsonObject();
    Class<Long> classOfT = Long.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, classOfT));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Class)} */
  @Test
  public void testFromJson39() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonArray json = new JsonArray(3);
    json.add(false);
    Class<Object> classOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof List);
    assertEquals(1, ((List<Boolean>) actualFromJsonResult).size());
    assertFalse(((List<Boolean>) actualFromJsonResult).get(0));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Class)} */
  @Test
  public void testFromJson40() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonArray json = new JsonArray(3);
    Class<LazilyParsedNumber> classOfT = LazilyParsedNumber.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, classOfT));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Class)} */
  @Test
  public void testFromJson41() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonPrimitive json = new JsonPrimitive("in == null");
    Class<LazilyParsedNumber> classOfT = LazilyParsedNumber.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof LazilyParsedNumber);
    assertEquals("in == null", actualFromJsonResult.toString());
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Class)} */
  @Test
  public void testFromJson42() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonPrimitive json = new JsonPrimitive(true);
    Class<LazilyParsedNumber> classOfT = LazilyParsedNumber.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, classOfT));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Type)} */
  @Test
  public void testFromJson43() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertNull(gson.fromJson(JsonNull.INSTANCE, (Type) typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Type)} */
  @Test
  public void testFromJson44() throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertNull(gson.fromJson(JsonNull.INSTANCE, (Type) typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Type)} */
  @Test
  public void testFromJson45() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonObject json = new JsonObject();
    Class<Object> typeOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, (Type) typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertTrue(((Map<Object, Object>) actualFromJsonResult).isEmpty());
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Type)} */
  @Test
  public void testFromJson46() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonArray json = new JsonArray(3);
    Class<Object> typeOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, (Type) typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof List);
    assertTrue(((List<Object>) actualFromJsonResult).isEmpty());
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Type)} */
  @Test
  public void testFromJson47() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonPrimitive json = new JsonPrimitive("in == null");
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertEquals("in == null", gson.fromJson(json, (Type) typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Type)} */
  @Test
  public void testFromJson48() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertNull(gson.fromJson((JsonElement) null, (Type) typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Type)} */
  @Test
  public void testFromJson49() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<TypeAdapterFactory> typeOfT = TypeAdapterFactory.class;

    // Act and Assert
    assertNull(gson.fromJson(JsonNull.INSTANCE, (Type) typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Type)} */
  @Test
  public void testFromJson50() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<LazilyParsedNumber> typeOfT = LazilyParsedNumber.class;

    // Act and Assert
    assertNull(gson.fromJson(JsonNull.INSTANCE, (Type) typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Type)} */
  @Test
  public void testFromJson51() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Long> typeOfT = Long.class;

    // Act and Assert
    assertNull(gson.fromJson(JsonNull.INSTANCE, (Type) typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Type)} */
  @Test
  public void testFromJson52() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> typeOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, (Type) typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(1, ((Map<String, Object>) actualFromJsonResult).size());
    assertNull(((Map<String, Object>) actualFromJsonResult).get("in == null"));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Type)} */
  @Test
  public void testFromJson53() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> typeOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, (Type) typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(2, ((Map<String, Object>) actualFromJsonResult).size());
    assertNull(((Map<String, Object>) actualFromJsonResult).get("in == null"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("type must not be null"));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Type)} */
  @Test
  public void testFromJson54() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.addProperty("type must not be null", "in == null");
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> typeOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, (Type) typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(2, ((Map<String, String>) actualFromJsonResult).size());
    assertEquals(
        "in == null", ((Map<String, String>) actualFromJsonResult).get("type must not be null"));
    assertNull(((Map<String, String>) actualFromJsonResult).get("in == null"));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Type)} */
  @Test
  public void testFromJson55() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.addProperty("type must not be null", Integer.valueOf(1));
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> typeOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, (Type) typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(2, ((Map<String, Double>) actualFromJsonResult).size());
    assertNull(((Map<String, Double>) actualFromJsonResult).get("in == null"));
    assertEquals(
        1.0d,
        ((Map<String, Double>) actualFromJsonResult).get("type must not be null").doubleValue(),
        0.0);
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Type)} */
  @Test
  public void testFromJson56() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.addProperty("type must not be null", false);
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> typeOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, (Type) typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(2, ((Map<String, Boolean>) actualFromJsonResult).size());
    assertNull(((Map<String, Boolean>) actualFromJsonResult).get("in == null"));
    assertFalse(((Map<String, Boolean>) actualFromJsonResult).get("type must not be null"));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Type)} */
  @Test
  public void testFromJson57() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("Property", JsonNull.INSTANCE);
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> typeOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, (Type) typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(3, ((Map<String, Object>) actualFromJsonResult).size());
    assertNull(((Map<String, Object>) actualFromJsonResult).get("Property"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("in == null"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("type must not be null"));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Type)} */
  @Test
  public void testFromJson58() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("in == null", JsonNull.INSTANCE);
    json.add("Property", JsonNull.INSTANCE);
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> typeOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, (Type) typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(3, ((Map<String, Object>) actualFromJsonResult).size());
    assertNull(((Map<String, Object>) actualFromJsonResult).get("Property"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("in == null"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("type must not be null"));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Type)} */
  @Test
  public void testFromJson59() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("in == null", JsonNull.INSTANCE);
    json.add("Property", JsonNull.INSTANCE);
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> typeOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, (Type) typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(3, ((Map<String, Object>) actualFromJsonResult).size());
    assertNull(((Map<String, Object>) actualFromJsonResult).get("Property"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("in == null"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("type must not be null"));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Type)} */
  @Test
  public void testFromJson60() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("Property", JsonNull.INSTANCE);
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> typeOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, (Type) typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(3, ((Map<String, Object>) actualFromJsonResult).size());
    assertNull(((Map<String, Object>) actualFromJsonResult).get("Property"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("in == null"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("type must not be null"));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Type)} */
  @Test
  public void testFromJson61() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("42", JsonNull.INSTANCE);
    json.add("Property", JsonNull.INSTANCE);
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> typeOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, (Type) typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(4, ((Map<String, Object>) actualFromJsonResult).size());
    assertNull(((Map<String, Object>) actualFromJsonResult).get("42"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("Property"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("in == null"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("type must not be null"));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Type)} */
  @Test
  public void testFromJson62() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonObject json = new JsonObject();
    Class<LazilyParsedNumber> typeOfT = LazilyParsedNumber.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, (Type) typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Type)} */
  @Test
  public void testFromJson63() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonObject json = new JsonObject();
    Class<Long> typeOfT = Long.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, (Type) typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(JsonElement, Type)} */
  @Test
  public void testFromJson64() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonArray json = new JsonArray(3);
    json.add(false);
    Class<Object> typeOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, (Type) typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof List);
    assertEquals(1, ((List<Boolean>) actualFromJsonResult).size());
    assertFalse(((List<Boolean>) actualFromJsonResult).get(0));
  }

  /** Method under test: {@link Gson#fromJson(JsonReader, TypeToken)} */
  @Test
  public void testFromJson65() throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonReader reader = new JsonReader(new StringReader("foo"));
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertEquals("foo", gson.fromJson(reader, typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(JsonReader, TypeToken)} */
  @Test
  public void testFromJson66() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new StringReader("foo"));
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertEquals("foo", gson.fromJson(reader, typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(JsonReader, TypeToken)} */
  @Test
  public void testFromJson67() throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonReader reader = new JsonReader(new StringReader("type must not be null"));
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertEquals("type", gson.fromJson(reader, typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(JsonReader, TypeToken)} */
  @Test
  public void testFromJson68() throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonReader reader = new JsonReader(new StringReader("42"));
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertEquals(42.0d, ((Double) gson.fromJson(reader, typeOfT)).doubleValue(), 0.0);
  }

  /** Method under test: {@link Gson#fromJson(JsonReader, TypeToken)} */
  @Test
  public void testFromJson69() throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonReader reader = new JsonReader(new StringReader(""));
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertNull(gson.fromJson(reader, typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(JsonReader, TypeToken)} */
  @Test
  public void testFromJson70() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LEGACY_STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new StringReader("foo"));
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(reader, typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(JsonReader, TypeToken)} */
  @Test
  public void testFromJson71() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new StringReader("foo"));
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(reader, typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(JsonReader, TypeToken)} */
  @Test
  public void testFromJson72() throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    Integer valueOfResult = Integer.valueOf(1);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any())).thenReturn(valueOfResult);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new StringReader("42"));
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(reader, typeOfT);

    // Assert
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
    assertSame(valueOfResult, actualFromJsonResult);
  }

  /** Method under test: {@link Gson#fromJson(JsonReader, TypeToken)} */
  @Test
  public void testFromJson73() throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any())).thenReturn(null);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new StringReader("42"));
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(reader, typeOfT);

    // Assert
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
    assertNull(actualFromJsonResult);
  }

  /** Method under test: {@link Gson#fromJson(JsonReader, TypeToken)} */
  @Test
  public void testFromJson74() throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new IOException("type must not be null"));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new StringReader("42"));
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(reader, typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /** Method under test: {@link Gson#fromJson(JsonReader, TypeToken)} */
  @Test
  public void testFromJson75() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new FileReader(new FileDescriptor()));
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(reader, typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(JsonReader, TypeToken)} */
  @Test
  public void testFromJson76() throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonTreeReader reader = new JsonTreeReader(JsonNull.INSTANCE);
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertNull(gson.fromJson(reader, typeOfT));
    assertFalse(reader.hasNext());
  }

  /** Method under test: {@link Gson#fromJson(JsonReader, TypeToken)} */
  @Test
  public void testFromJson77() throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new EOFException("type must not be null"));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new StringReader("42"));
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(reader, typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /** Method under test: {@link Gson#fromJson(JsonReader, Type)} */
  @Test
  public void testFromJson78() throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonReader reader = new JsonReader(new StringReader("foo"));
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertEquals("foo", gson.fromJson(reader, typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(JsonReader, Type)} */
  @Test
  public void testFromJson79() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new StringReader("foo"));
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertEquals("foo", gson.fromJson(reader, typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(JsonReader, Type)} */
  @Test
  public void testFromJson80() throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonReader reader = new JsonReader(new StringReader("type must not be null"));
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertEquals("type", gson.fromJson(reader, typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(JsonReader, Type)} */
  @Test
  public void testFromJson81() throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonReader reader = new JsonReader(new StringReader("42"));
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertEquals(42.0d, ((Double) gson.fromJson(reader, typeOfT)).doubleValue(), 0.0);
  }

  /** Method under test: {@link Gson#fromJson(JsonReader, Type)} */
  @Test
  public void testFromJson82() throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonReader reader = new JsonReader(new StringReader(""));
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertNull(gson.fromJson(reader, typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(JsonReader, Type)} */
  @Test
  public void testFromJson83() throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonReader reader = new JsonReader(new StringReader("foo"));
    Class<LazilyParsedNumber> typeOfT = LazilyParsedNumber.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(reader, typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof LazilyParsedNumber);
    assertEquals("foo", actualFromJsonResult.toString());
  }

  /** Method under test: {@link Gson#fromJson(JsonReader, Type)} */
  @Test
  public void testFromJson84() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LEGACY_STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new StringReader("foo"));
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(reader, typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(JsonReader, Type)} */
  @Test
  public void testFromJson85() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new StringReader("foo"));
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(reader, typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(JsonReader, Type)} */
  @Test
  public void testFromJson86() throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    Integer valueOfResult = Integer.valueOf(1);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any())).thenReturn(valueOfResult);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new StringReader("42"));
    Class<Object> typeOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(reader, typeOfT);

    // Assert
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
    assertSame(valueOfResult, actualFromJsonResult);
  }

  /** Method under test: {@link Gson#fromJson(JsonReader, Type)} */
  @Test
  public void testFromJson87() throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any())).thenReturn(null);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new StringReader("42"));
    Class<Object> typeOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(reader, typeOfT);

    // Assert
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
    assertNull(actualFromJsonResult);
  }

  /** Method under test: {@link Gson#fromJson(JsonReader, Type)} */
  @Test
  public void testFromJson88() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new StringReader("42"));
    Class<LazilyParsedNumber> typeOfT = LazilyParsedNumber.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(reader, typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof LazilyParsedNumber);
    assertEquals("42", actualFromJsonResult.toString());
  }

  /** Method under test: {@link Gson#fromJson(JsonReader, Type)} */
  @Test
  public void testFromJson89() throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new IOException("type must not be null"));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new StringReader("42"));
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(reader, typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /** Method under test: {@link Gson#fromJson(JsonReader, Type)} */
  @Test
  public void testFromJson90() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new CharArrayReader("A\u0000A\u0000".toCharArray(), 1, 1));
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertEquals("\u0000", gson.fromJson(reader, typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(JsonReader, Type)} */
  @Test
  public void testFromJson91() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new FileReader(new FileDescriptor()));
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(reader, typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(JsonReader, Type)} */
  @Test
  public void testFromJson92() throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonTreeReader reader = new JsonTreeReader(JsonNull.INSTANCE);
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertNull(gson.fromJson(reader, typeOfT));
    assertFalse(reader.hasNext());
  }

  /** Method under test: {@link Gson#fromJson(Reader, TypeToken)} */
  @Test
  public void testFromJson93() throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("foo");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertEquals("foo", gson.fromJson(json, typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(Reader, TypeToken)} */
  @Test
  public void testFromJson94() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            255,
            255,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("foo");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertEquals("foo", gson.fromJson(json, typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(Reader, TypeToken)} */
  @Test
  public void testFromJson95() throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("in == null");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(Reader, TypeToken)} */
  @Test
  public void testFromJson96() throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("type must not be null");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(Reader, TypeToken)} */
  @Test
  public void testFromJson97() throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("42");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertEquals(42.0d, ((Double) gson.fromJson(json, typeOfT)).doubleValue(), 0.0);
  }

  /** Method under test: {@link Gson#fromJson(Reader, TypeToken)} */
  @Test
  public void testFromJson98() throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertNull(gson.fromJson(json, typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(Reader, TypeToken)} */
  @Test
  public void testFromJson99() throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    FileReader json = new FileReader(new FileDescriptor());
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(Reader, TypeToken)} */
  @Test
  public void testFromJson100() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LEGACY_STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            255,
            255,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("foo");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(Reader, TypeToken)} */
  @Test
  public void testFromJson101() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            255,
            255,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("foo");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(Reader, TypeToken)} */
  @Test
  public void testFromJson102() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            255,
            255,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("in == null");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(Reader, TypeToken)} */
  @Test
  public void testFromJson103() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            255,
            255,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("type must not be null");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(Reader, TypeToken)} */
  @Test
  public void testFromJson104() throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenReturn(Integer.valueOf(1));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            255,
            255,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("42");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /** Method under test: {@link Gson#fromJson(Reader, TypeToken)} */
  @Test
  public void testFromJson105() throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any())).thenReturn(null);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            255,
            255,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("42");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(json, typeOfT);

    // Assert
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
    assertNull(actualFromJsonResult);
  }

  /** Method under test: {@link Gson#fromJson(Reader, TypeToken)} */
  @Test
  public void testFromJson106() throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new IOException("in == null"));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            255,
            255,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("42");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /** Method under test: {@link Gson#fromJson(Reader, TypeToken)} */
  @Test
  public void testFromJson107() throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new EOFException("in == null"));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            255,
            255,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("42");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /** Method under test: {@link Gson#fromJson(Reader, Class)} */
  @Test
  public void testFromJson108() throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("foo");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertEquals("foo", gson.fromJson(json, classOfT));
  }

  /** Method under test: {@link Gson#fromJson(Reader, Class)} */
  @Test
  public void testFromJson109() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("foo");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertEquals("foo", gson.fromJson(json, classOfT));
  }

  /** Method under test: {@link Gson#fromJson(Reader, Class)} */
  @Test
  public void testFromJson110() throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("in == null");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, classOfT));
  }

  /** Method under test: {@link Gson#fromJson(Reader, Class)} */
  @Test
  public void testFromJson111() throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("type must not be null");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, classOfT));
  }

  /** Method under test: {@link Gson#fromJson(Reader, Class)} */
  @Test
  public void testFromJson112() throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("42");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertEquals(42.0d, ((Double) gson.fromJson(json, classOfT)).doubleValue(), 0.0);
  }

  /** Method under test: {@link Gson#fromJson(Reader, Class)} */
  @Test
  public void testFromJson113() throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertNull(gson.fromJson(json, classOfT));
  }

  /** Method under test: {@link Gson#fromJson(Reader, Class)} */
  @Test
  public void testFromJson114() throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    Gson gson = new Gson();
    CharArrayReader json = new CharArrayReader("\u0001ÿ\u0001ÿ".toCharArray());
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertEquals("\u0001ÿ\u0001ÿ", gson.fromJson(json, classOfT));
    assertFalse(json.ready());
  }

  /** Method under test: {@link Gson#fromJson(Reader, Class)} */
  @Test
  public void testFromJson115() throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    FileReader json = new FileReader(new FileDescriptor());
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, classOfT));
  }

  /** Method under test: {@link Gson#fromJson(Reader, Class)} */
  @Test
  public void testFromJson116() throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("foo");
    Class<LazilyParsedNumber> classOfT = LazilyParsedNumber.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof LazilyParsedNumber);
    assertEquals("foo", actualFromJsonResult.toString());
  }

  /** Method under test: {@link Gson#fromJson(Reader, Class)} */
  @Test
  public void testFromJson117() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LEGACY_STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("foo");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, classOfT));
  }

  /** Method under test: {@link Gson#fromJson(Reader, Class)} */
  @Test
  public void testFromJson118() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("foo");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, classOfT));
  }

  /** Method under test: {@link Gson#fromJson(Reader, Class)} */
  @Test
  public void testFromJson119() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("in == null");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, classOfT));
  }

  /** Method under test: {@link Gson#fromJson(Reader, Class)} */
  @Test
  public void testFromJson120() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("type must not be null");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, classOfT));
  }

  /** Method under test: {@link Gson#fromJson(Reader, Class)} */
  @Test
  public void testFromJson121() throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenReturn(Integer.valueOf(1));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("42");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, classOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /** Method under test: {@link Gson#fromJson(Reader, Class)} */
  @Test
  public void testFromJson122() throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any())).thenReturn(null);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("42");
    Class<Object> classOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, classOfT);

    // Assert
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
    assertNull(actualFromJsonResult);
  }

  /** Method under test: {@link Gson#fromJson(Reader, Class)} */
  @Test
  public void testFromJson123() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("42");
    Class<LazilyParsedNumber> classOfT = LazilyParsedNumber.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof LazilyParsedNumber);
    assertEquals("42", actualFromJsonResult.toString());
  }

  /** Method under test: {@link Gson#fromJson(Reader, Class)} */
  @Test
  public void testFromJson124() throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new IOException("in == null"));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("42");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, classOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /** Method under test: {@link Gson#fromJson(Reader, Class)} */
  @Test
  public void testFromJson125() throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new EOFException("in == null"));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("42");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, classOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /** Method under test: {@link Gson#fromJson(Reader, Type)} */
  @Test
  public void testFromJson126() throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("foo");
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertEquals("foo", gson.fromJson(json, (Type) typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(Reader, Type)} */
  @Test
  public void testFromJson127() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("foo");
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertEquals("foo", gson.fromJson(json, (Type) typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(Reader, Type)} */
  @Test
  public void testFromJson128() throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("in == null");
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, (Type) typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(Reader, Type)} */
  @Test
  public void testFromJson129() throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("type must not be null");
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, (Type) typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(Reader, Type)} */
  @Test
  public void testFromJson130() throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("42");
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertEquals(42.0d, ((Double) gson.fromJson(json, (Type) typeOfT)).doubleValue(), 0.0);
  }

  /** Method under test: {@link Gson#fromJson(Reader, Type)} */
  @Test
  public void testFromJson131() throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("");
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertNull(gson.fromJson(json, (Type) typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(Reader, Type)} */
  @Test
  public void testFromJson132() throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    Gson gson = new Gson();
    CharArrayReader json = new CharArrayReader("\u0001ÿ\u0001ÿ".toCharArray());
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertEquals("\u0001ÿ\u0001ÿ", gson.fromJson(json, (Type) typeOfT));
    assertFalse(json.ready());
  }

  /** Method under test: {@link Gson#fromJson(Reader, Type)} */
  @Test
  public void testFromJson133() throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    FileReader json = new FileReader(new FileDescriptor());
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, (Type) typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(Reader, Type)} */
  @Test
  public void testFromJson134() throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("foo");
    Class<LazilyParsedNumber> typeOfT = LazilyParsedNumber.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, (Type) typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof LazilyParsedNumber);
    assertEquals("foo", actualFromJsonResult.toString());
  }

  /** Method under test: {@link Gson#fromJson(Reader, Type)} */
  @Test
  public void testFromJson135() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LEGACY_STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("foo");
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, (Type) typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(Reader, Type)} */
  @Test
  public void testFromJson136() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("foo");
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, (Type) typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(Reader, Type)} */
  @Test
  public void testFromJson137() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("in == null");
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, (Type) typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(Reader, Type)} */
  @Test
  public void testFromJson138() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("type must not be null");
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, (Type) typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(Reader, Type)} */
  @Test
  public void testFromJson139() throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenReturn(Integer.valueOf(1));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("42");
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, (Type) typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /** Method under test: {@link Gson#fromJson(Reader, Type)} */
  @Test
  public void testFromJson140() throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any())).thenReturn(null);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("42");
    Class<Object> typeOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, (Type) typeOfT);

    // Assert
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
    assertNull(actualFromJsonResult);
  }

  /** Method under test: {@link Gson#fromJson(Reader, Type)} */
  @Test
  public void testFromJson141() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("42");
    Class<LazilyParsedNumber> typeOfT = LazilyParsedNumber.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, (Type) typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof LazilyParsedNumber);
    assertEquals("42", actualFromJsonResult.toString());
  }

  /** Method under test: {@link Gson#fromJson(Reader, Type)} */
  @Test
  public void testFromJson142() throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new IOException("in == null"));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("42");
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, (Type) typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /** Method under test: {@link Gson#fromJson(String, TypeToken)} */
  @Test
  public void testFromJson143() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertEquals("Json", gson.fromJson("Json", typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(String, TypeToken)} */
  @Test
  public void testFromJson144() throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            255,
            255,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertEquals("Json", gson.fromJson("Json", typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(String, TypeToken)} */
  @Test
  public void testFromJson145() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("in == null", typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(String, TypeToken)} */
  @Test
  public void testFromJson146() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("type must not be null", typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(String, TypeToken)} */
  @Test
  public void testFromJson147() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertNull(gson.fromJson((String) null, typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(String, TypeToken)} */
  @Test
  public void testFromJson148() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertEquals(42.0d, ((Double) gson.fromJson("42", typeOfT)).doubleValue(), 0.0);
  }

  /** Method under test: {@link Gson#fromJson(String, TypeToken)} */
  @Test
  public void testFromJson149() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertNull(gson.fromJson("", typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(String, TypeToken)} */
  @Test
  public void testFromJson150() throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LEGACY_STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            255,
            255,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("Json", typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(String, TypeToken)} */
  @Test
  public void testFromJson151() throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            255,
            255,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("in == null", typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(String, TypeToken)} */
  @Test
  public void testFromJson152() throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            255,
            255,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("type must not be null", typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(String, TypeToken)} */
  @Test
  public void testFromJson153() throws JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenReturn(Integer.valueOf(1));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            255,
            255,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("42", typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /** Method under test: {@link Gson#fromJson(String, TypeToken)} */
  @Test
  public void testFromJson154() throws JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any())).thenReturn(null);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            255,
            255,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson("42", typeOfT);

    // Assert
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
    assertNull(actualFromJsonResult);
  }

  /** Method under test: {@link Gson#fromJson(String, TypeToken)} */
  @Test
  public void testFromJson155() throws JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new IOException("in == null"));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            255,
            255,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("42", typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /** Method under test: {@link Gson#fromJson(String, TypeToken)} */
  @Test
  public void testFromJson156() throws JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new EOFException("in == null"));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            255,
            255,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("42", typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /** Method under test: {@link Gson#fromJson(String, Class)} */
  @Test
  public void testFromJson157() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertEquals("Json", gson.fromJson("Json", classOfT));
  }

  /** Method under test: {@link Gson#fromJson(String, Class)} */
  @Test
  public void testFromJson158() throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertEquals("Json", gson.fromJson("Json", classOfT));
  }

  /** Method under test: {@link Gson#fromJson(String, Class)} */
  @Test
  public void testFromJson159() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("in == null", classOfT));
  }

  /** Method under test: {@link Gson#fromJson(String, Class)} */
  @Test
  public void testFromJson160() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("type must not be null", classOfT));
  }

  /** Method under test: {@link Gson#fromJson(String, Class)} */
  @Test
  public void testFromJson161() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertNull(gson.fromJson((String) null, classOfT));
  }

  /** Method under test: {@link Gson#fromJson(String, Class)} */
  @Test
  public void testFromJson162() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertEquals(42.0d, ((Double) gson.fromJson("42", classOfT)).doubleValue(), 0.0);
  }

  /** Method under test: {@link Gson#fromJson(String, Class)} */
  @Test
  public void testFromJson163() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertNull(gson.fromJson("", classOfT));
  }

  /** Method under test: {@link Gson#fromJson(String, Class)} */
  @Test
  public void testFromJson164() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<LazilyParsedNumber> classOfT = LazilyParsedNumber.class;

    // Act
    Object actualFromJsonResult = gson.fromJson("Json", classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof LazilyParsedNumber);
    assertEquals("Json", actualFromJsonResult.toString());
  }

  /** Method under test: {@link Gson#fromJson(String, Class)} */
  @Test
  public void testFromJson165() throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LEGACY_STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("Json", classOfT));
  }

  /** Method under test: {@link Gson#fromJson(String, Class)} */
  @Test
  public void testFromJson166() throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("in == null", classOfT));
  }

  /** Method under test: {@link Gson#fromJson(String, Class)} */
  @Test
  public void testFromJson167() throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("type must not be null", classOfT));
  }

  /** Method under test: {@link Gson#fromJson(String, Class)} */
  @Test
  public void testFromJson168() throws JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenReturn(Integer.valueOf(1));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("42", classOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /** Method under test: {@link Gson#fromJson(String, Class)} */
  @Test
  public void testFromJson169() throws JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any())).thenReturn(null);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> classOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson("42", classOfT);

    // Assert
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
    assertNull(actualFromJsonResult);
  }

  /** Method under test: {@link Gson#fromJson(String, Class)} */
  @Test
  public void testFromJson170() throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> classOfT = LazilyParsedNumber.class;

    // Act
    Object actualFromJsonResult = gson.fromJson("42", classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof LazilyParsedNumber);
    assertEquals("42", actualFromJsonResult.toString());
  }

  /** Method under test: {@link Gson#fromJson(String, Class)} */
  @Test
  public void testFromJson171() throws JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new IOException("in == null"));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("42", classOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /** Method under test: {@link Gson#fromJson(String, Class)} */
  @Test
  public void testFromJson172() throws JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new EOFException("in == null"));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("42", classOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /** Method under test: {@link Gson#fromJson(String, Type)} */
  @Test
  public void testFromJson173() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertEquals("Json", gson.fromJson("Json", (Type) typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(String, Type)} */
  @Test
  public void testFromJson174() throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertEquals("Json", gson.fromJson("Json", (Type) typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(String, Type)} */
  @Test
  public void testFromJson175() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("in == null", (Type) typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(String, Type)} */
  @Test
  public void testFromJson176() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class, () -> gson.fromJson("type must not be null", (Type) typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(String, Type)} */
  @Test
  public void testFromJson177() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertNull(gson.fromJson((String) null, (Type) typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(String, Type)} */
  @Test
  public void testFromJson178() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertEquals(42.0d, ((Double) gson.fromJson("42", (Type) typeOfT)).doubleValue(), 0.0);
  }

  /** Method under test: {@link Gson#fromJson(String, Type)} */
  @Test
  public void testFromJson179() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertNull(gson.fromJson("", (Type) typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(String, Type)} */
  @Test
  public void testFromJson180() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<LazilyParsedNumber> typeOfT = LazilyParsedNumber.class;

    // Act
    Object actualFromJsonResult = gson.fromJson("Json", (Type) typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof LazilyParsedNumber);
    assertEquals("Json", actualFromJsonResult.toString());
  }

  /** Method under test: {@link Gson#fromJson(String, Type)} */
  @Test
  public void testFromJson181() throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LEGACY_STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("Json", (Type) typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(String, Type)} */
  @Test
  public void testFromJson182() throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("in == null", (Type) typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(String, Type)} */
  @Test
  public void testFromJson183() throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class, () -> gson.fromJson("type must not be null", (Type) typeOfT));
  }

  /** Method under test: {@link Gson#fromJson(String, Type)} */
  @Test
  public void testFromJson184() throws JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenReturn(Integer.valueOf(1));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("42", (Type) typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /** Method under test: {@link Gson#fromJson(String, Type)} */
  @Test
  public void testFromJson185() throws JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any())).thenReturn(null);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> typeOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson("42", (Type) typeOfT);

    // Assert
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
    assertNull(actualFromJsonResult);
  }

  /** Method under test: {@link Gson#fromJson(String, Type)} */
  @Test
  public void testFromJson186() throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> typeOfT = LazilyParsedNumber.class;

    // Act
    Object actualFromJsonResult = gson.fromJson("42", (Type) typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof LazilyParsedNumber);
    assertEquals("42", actualFromJsonResult.toString());
  }

  /** Method under test: {@link Gson#fromJson(String, Type)} */
  @Test
  public void testFromJson187() throws JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new IOException("in == null"));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("42", (Type) typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /** Method under test: {@link Gson#fromJson(String, Type)} */
  @Test
  public void testFromJson188() throws JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new EOFException("in == null"));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("42", (Type) typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /** Method under test: {@link Gson.FutureTypeAdapter#getSerializationDelegate()} */
  @Test
  public void testFutureTypeAdapterGetSerializationDelegate() {
    // Arrange
    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> futureTypeAdapter.getSerializationDelegate());
  }

  /** Method under test: {@link Gson.FutureTypeAdapter#getSerializationDelegate()} */
  @Test
  public void testFutureTypeAdapterGetSerializationDelegate2() {
    // Arrange
    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    Gson.FutureTypeAdapter<Object> typeAdapter = new Gson.FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    TypeAdapter<Object> actualSerializationDelegate = futureTypeAdapter.getSerializationDelegate();

    // Assert
    assertTrue(actualSerializationDelegate instanceof Gson.FutureTypeAdapter);
    assertSame(typeAdapter, actualSerializationDelegate);
  }

  /** Method under test: {@link Gson.FutureTypeAdapter#getSerializationDelegate()} */
  @Test
  public void testFutureTypeAdapterGetSerializationDelegate3() {
    // Arrange
    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    futureTypeAdapter.setDelegate(typeAdapter);

    // Act and Assert
    assertSame(typeAdapter, futureTypeAdapter.getSerializationDelegate());
  }

  /** Method under test: {@link Gson.FutureTypeAdapter#read(JsonReader)} */
  @Test
  public void testFutureTypeAdapterRead() throws IOException {
    // Arrange
    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> futureTypeAdapter.read(new JsonReader(new StringReader("foo"))));
  }

  /** Method under test: {@link Gson.FutureTypeAdapter#read(JsonReader)} */
  @Test
  public void testFutureTypeAdapterRead2() throws IOException {
    // Arrange
    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(new Gson.FutureTypeAdapter<>());

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> futureTypeAdapter.read(new JsonReader(new StringReader("foo"))));
  }

  /** Method under test: {@link Gson.FutureTypeAdapter#read(JsonReader)} */
  @Test
  public void testFutureTypeAdapterRead3() throws JsonParseException, IOException {
    // Arrange
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    when(deserializer.deserialize(
            Mockito.<JsonElement>any(),
            Mockito.<Type>any(),
            Mockito.<JsonDeserializationContext>any()))
        .thenReturn("Deserialize");
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    Object actualReadResult =
        futureTypeAdapter.read(new JsonReader(new StringReader(Boolean.FALSE.toString())));

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertEquals("Deserialize", actualReadResult);
  }

  /** Method under test: {@link Gson.FutureTypeAdapter#read(JsonReader)} */
  @Test
  public void testFutureTypeAdapterRead4() throws JsonParseException, IOException {
    // Arrange
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    when(deserializer.deserialize(
            Mockito.<JsonElement>any(),
            Mockito.<Type>any(),
            Mockito.<JsonDeserializationContext>any()))
        .thenReturn("Deserialize");
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    Object actualReadResult = futureTypeAdapter.read(new JsonReader(new StringReader("42")));

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertEquals("Deserialize", actualReadResult);
  }

  /** Method under test: {@link Gson.FutureTypeAdapter#read(JsonReader)} */
  @Test
  public void testFutureTypeAdapterRead5() throws IOException {
    // Arrange
    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    futureTypeAdapter.setDelegate(
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    // Act and Assert
    assertNull(futureTypeAdapter.read(new JsonReader(new StringReader(""))));
  }

  /** Method under test: {@link Gson.FutureTypeAdapter#setDelegate(TypeAdapter)} */
  @Test
  public void testFutureTypeAdapterSetDelegate() {
    // Arrange
    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    // Act
    futureTypeAdapter.setDelegate(typeAdapter);

    // Assert
    assertSame(typeAdapter, futureTypeAdapter.getSerializationDelegate());
  }

  /** Method under test: {@link Gson.FutureTypeAdapter#write(JsonWriter, Object)} */
  @Test
  public void testFutureTypeAdapterWrite() throws IOException {
    // Arrange
    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> futureTypeAdapter.write(new JsonWriter(new StringWriter()), "Value"));
  }

  /** Method under test: {@link Gson.FutureTypeAdapter#write(JsonWriter, Object)} */
  @Test
  public void testFutureTypeAdapterWrite2() throws IOException {
    // Arrange
    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(new Gson.FutureTypeAdapter<>());

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> futureTypeAdapter.write(new JsonWriter(new StringWriter()), "Value"));
  }

  /** Method under test: {@link Gson.FutureTypeAdapter#write(JsonWriter, Object)} */
  @Test
  public void testFutureTypeAdapterWrite3() throws IOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(JsonNull.INSTANCE);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    futureTypeAdapter.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /** Method under test: {@link Gson.FutureTypeAdapter#write(JsonWriter, Object)} */
  @Test
  public void testFutureTypeAdapterWrite4() throws IOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonArray(3));
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    futureTypeAdapter.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /** Method under test: {@link Gson.FutureTypeAdapter#write(JsonWriter, Object)} */
  @Test
  public void testFutureTypeAdapterWrite5() throws IOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonObject());
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    futureTypeAdapter.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /** Method under test: {@link Gson.FutureTypeAdapter#write(JsonWriter, Object)} */
  @Test
  public void testFutureTypeAdapterWrite6() throws IOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonPrimitive("null"));
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    futureTypeAdapter.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /** Method under test: {@link Gson.FutureTypeAdapter#write(JsonWriter, Object)} */
  @Test
  public void testFutureTypeAdapterWrite7() throws IOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonPrimitive(true));
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    futureTypeAdapter.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /** Method under test: {@link Gson.FutureTypeAdapter#write(JsonWriter, Object)} */
  @Test
  public void testFutureTypeAdapterWrite8() throws IOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonPrimitive('\u0001'));
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    futureTypeAdapter.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /** Method under test: {@link Gson.FutureTypeAdapter#write(JsonWriter, Object)} */
  @Test
  public void testFutureTypeAdapterWrite9() throws IOException {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(true);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(jsonArray);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    futureTypeAdapter.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /** Method under test: {@link Gson.FutureTypeAdapter#write(JsonWriter, Object)} */
  @Test
  public void testFutureTypeAdapterWrite10() throws IOException {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(false);
    jsonArray.add(true);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(jsonArray);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    futureTypeAdapter.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /** Method under test: {@link Gson.FutureTypeAdapter#write(JsonWriter, Object)} */
  @Test
  public void testFutureTypeAdapterWrite11() throws IOException {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add('\u0003');
    jsonArray.add(true);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(jsonArray);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    futureTypeAdapter.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /** Method under test: {@link Gson.FutureTypeAdapter#write(JsonWriter, Object)} */
  @Test
  public void testFutureTypeAdapterWrite12() throws IOException {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(Integer.valueOf(1));
    jsonArray.add(true);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(jsonArray);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    futureTypeAdapter.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /** Method under test: {@link Gson.FutureTypeAdapter#write(JsonWriter, Object)} */
  @Test
  public void testFutureTypeAdapterWrite13() throws IOException {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(JsonNull.INSTANCE);
    jsonArray.add(true);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(jsonArray);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    futureTypeAdapter.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /** Method under test: {@link Gson.FutureTypeAdapter#write(JsonWriter, Object)} */
  @Test
  public void testFutureTypeAdapterWrite14() throws IOException {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", JsonNull.INSTANCE);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(jsonObject);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    futureTypeAdapter.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /** Method under test: {@link Gson.FutureTypeAdapter#write(JsonWriter, Object)} */
  @Test
  public void testFutureTypeAdapterWrite15() throws IOException {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("null", JsonNull.INSTANCE);
    jsonObject.add("Property", JsonNull.INSTANCE);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(jsonObject);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    futureTypeAdapter.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /** Method under test: {@link Gson#getAdapter(TypeToken)} */
  @Test
  public void testGetAdapter() {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> type2 = TypeToken.get(type);

    // Act and Assert
    assertTrue(gson.getAdapter(type2) instanceof ObjectTypeAdapter);
  }

  /** Method under test: {@link Gson#getAdapter(TypeToken)} */
  @Test
  public void testGetAdapter2() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> type = Object.class;
    TypeToken<Object> type2 = TypeToken.get(type);

    // Act and Assert
    assertTrue(gson.getAdapter(type2) instanceof ObjectTypeAdapter);
  }

  /** Method under test: {@link Gson#getAdapter(Class)} */
  @Test
  public void testGetAdapter3() {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;

    // Act and Assert
    assertTrue(gson.getAdapter(type) instanceof ObjectTypeAdapter);
  }

  /** Method under test: {@link Gson#getAdapter(Class)} */
  @Test
  public void testGetAdapter4() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> type = Object.class;

    // Act and Assert
    assertTrue(gson.getAdapter(type) instanceof ObjectTypeAdapter);
  }

  /** Method under test: {@link Gson#getDelegateAdapter(TypeAdapterFactory, TypeToken)} */
  @Test
  public void testGetDelegateAdapter() {
    // Arrange
    Gson gson = new Gson();
    TypeAdapterFactory skipPast = mock(TypeAdapterFactory.class);
    Class<Object> type = Object.class;
    TypeToken<Object> type2 = TypeToken.get(type);

    // Act and Assert
    assertTrue(gson.getDelegateAdapter(skipPast, type2) instanceof ObjectTypeAdapter);
  }

  /** Method under test: {@link Gson#getDelegateAdapter(TypeAdapterFactory, TypeToken)} */
  @Test
  public void testGetDelegateAdapter2() {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> type2 = TypeToken.get(type);

    // Act and Assert
    assertEquals("{}", gson.getDelegateAdapter(Excluder.DEFAULT, type2).toJson("Value"));
  }

  /** Method under test: {@link Gson#getDelegateAdapter(TypeAdapterFactory, TypeToken)} */
  @Test
  public void testGetDelegateAdapter3() {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> type2 = TypeToken.get(type);

    // Act and Assert
    assertTrue(
        gson.getDelegateAdapter(TypeAdapters.JSON_ELEMENT_FACTORY, type2)
            instanceof ObjectTypeAdapter);
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link Gson#toString()}
   *   <li>{@link Gson#excluder()}
   *   <li>{@link Gson#fieldNamingStrategy()}
   *   <li>{@link Gson#htmlSafe()}
   *   <li>{@link Gson#serializeNulls()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    Gson gson = new Gson();

    // Act
    gson.toString();
    Excluder actualExcluderResult = gson.excluder();
    FieldNamingStrategy actualFieldNamingStrategyResult = gson.fieldNamingStrategy();
    boolean actualHtmlSafeResult = gson.htmlSafe();

    // Assert
    assertFalse(gson.serializeNulls());
    assertTrue(actualHtmlSafeResult);
    assertSame(gson.fieldNamingStrategy, actualFieldNamingStrategyResult);
    assertSame(actualExcluderResult.DEFAULT, actualExcluderResult);
  }

  /** Method under test: {@link Gson#newBuilder()} */
  @Test
  public void testNewBuilder() {
    // Arrange
    Gson gson = new Gson();

    // Act and Assert
    Gson createResult = gson.newBuilder().create();
    List<TypeAdapterFactory> typeAdapterFactoryList = createResult.factories;
    assertEquals(42, typeAdapterFactoryList.size());
    assertTrue(typeAdapterFactoryList.get(41) instanceof ReflectiveTypeAdapterFactory);
    assertNull(createResult.strictness);
    assertNull(createResult.datePattern);
    assertEquals(2, createResult.dateStyle);
    assertEquals(2, createResult.timeStyle);
    assertEquals(LongSerializationPolicy.DEFAULT, createResult.longSerializationPolicy);
    assertFalse(createResult.serializeNulls());
    assertFalse(createResult.complexMapKeySerialization);
    assertFalse(createResult.generateNonExecutableJson);
    assertFalse(createResult.serializeNulls);
    assertFalse(createResult.serializeSpecialFloatingPointValues);
    assertTrue(createResult.htmlSafe());
    assertTrue(createResult.builderFactories.isEmpty());
    assertTrue(createResult.builderHierarchyFactories.isEmpty());
    assertTrue(createResult.reflectionFilters.isEmpty());
    assertTrue(createResult.instanceCreators.isEmpty());
    assertTrue(createResult.htmlSafe);
    assertTrue(createResult.useJdkUnsafe);
    Excluder excluder = gson.excluder;
    assertSame(excluder, createResult.excluder());
    assertSame(excluder, createResult.excluder);
    FieldNamingStrategy fieldNamingStrategy = gson.fieldNamingStrategy;
    assertSame(fieldNamingStrategy, createResult.fieldNamingStrategy());
    assertSame(fieldNamingStrategy, createResult.fieldNamingStrategy);
    assertSame(gson.formattingStyle, createResult.formattingStyle);
    assertSame(gson.numberToNumberStrategy, createResult.numberToNumberStrategy);
    assertSame(gson.objectToNumberStrategy, createResult.objectToNumberStrategy);
  }

  /** Method under test: {@link Gson#newBuilder()} */
  @Test
  public void testNewBuilder2() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            2,
            2,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());

    // Act and Assert
    Gson createResult = gson.newBuilder().create();
    List<TypeAdapterFactory> typeAdapterFactoryList = createResult.factories;
    assertEquals(45, typeAdapterFactoryList.size());
    assertTrue(typeAdapterFactoryList.get(44) instanceof ReflectiveTypeAdapterFactory);
    assertEquals("2020-03-01", createResult.datePattern);
    assertEquals(2, createResult.dateStyle);
    assertEquals(2, createResult.timeStyle);
    assertEquals(LongSerializationPolicy.DEFAULT, createResult.longSerializationPolicy);
    assertEquals(Strictness.LENIENT, createResult.strictness);
    assertTrue(createResult.htmlSafe());
    assertTrue(createResult.serializeNulls());
    assertTrue(createResult.builderFactories.isEmpty());
    assertTrue(createResult.builderHierarchyFactories.isEmpty());
    assertTrue(createResult.reflectionFilters.isEmpty());
    assertTrue(createResult.instanceCreators.isEmpty());
    assertTrue(createResult.complexMapKeySerialization);
    assertTrue(createResult.generateNonExecutableJson);
    assertTrue(createResult.htmlSafe);
    assertTrue(createResult.serializeNulls);
    assertTrue(createResult.serializeSpecialFloatingPointValues);
    assertTrue(createResult.useJdkUnsafe);
    Excluder excluder = gson.excluder;
    assertSame(excluder, createResult.excluder());
    assertSame(excluder, createResult.excluder);
    FieldNamingStrategy fieldNamingStrategy2 = gson.fieldNamingStrategy;
    assertSame(fieldNamingStrategy2, createResult.fieldNamingStrategy());
    assertSame(fieldNamingStrategy2, createResult.fieldNamingStrategy);
    assertSame(gson.formattingStyle, createResult.formattingStyle);
    assertSame(gson.numberToNumberStrategy, createResult.numberToNumberStrategy);
    assertSame(gson.objectToNumberStrategy, createResult.objectToNumberStrategy);
  }

  /** Method under test: {@link Gson#Gson()} */
  @Test
  public void testNewGson() {
    // Arrange and Act
    Gson actualGson = new Gson();

    // Assert
    List<TypeAdapterFactory> typeAdapterFactoryList = actualGson.factories;
    assertEquals(42, typeAdapterFactoryList.size());
    assertTrue(typeAdapterFactoryList.get(39) instanceof JsonAdapterAnnotationTypeAdapterFactory);
    assertTrue(typeAdapterFactoryList.get(41) instanceof ReflectiveTypeAdapterFactory);
    FormattingStyle formattingStyle = actualGson.formattingStyle;
    assertEquals("", formattingStyle.getIndent());
    assertEquals("", formattingStyle.getNewline());
    assertNull(actualGson.strictness);
    assertNull(actualGson.datePattern);
    assertEquals(2, actualGson.dateStyle);
    assertEquals(2, actualGson.timeStyle);
    assertEquals(LongSerializationPolicy.DEFAULT, actualGson.longSerializationPolicy);
    assertFalse(actualGson.serializeNulls());
    assertFalse(actualGson.complexMapKeySerialization);
    assertFalse(actualGson.generateNonExecutableJson);
    assertFalse(actualGson.serializeNulls);
    assertFalse(actualGson.serializeSpecialFloatingPointValues);
    assertTrue(actualGson.htmlSafe());
    assertTrue(actualGson.builderFactories.isEmpty());
    assertTrue(actualGson.builderHierarchyFactories.isEmpty());
    assertTrue(actualGson.reflectionFilters.isEmpty());
    assertTrue(actualGson.instanceCreators.isEmpty());
    assertTrue(actualGson.htmlSafe);
    assertTrue(actualGson.useJdkUnsafe);
    Excluder excluder = actualGson.excluder;
    assertSame(excluder, actualGson.excluder());
    assertSame(excluder, typeAdapterFactoryList.get(2));
    FieldNamingStrategy expectedFieldNamingStrategyResult = actualGson.fieldNamingStrategy;
    assertSame(expectedFieldNamingStrategyResult, actualGson.fieldNamingStrategy());
  }

  /**
   * Method under test: {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean,
   * boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy,
   * String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)}
   */
  @Test
  public void testNewGson2() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();

    // Act
    Gson actualGson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            reflectionFilters);

    // Assert
    List<TypeAdapterFactory> typeAdapterFactoryList = actualGson.factories;
    assertEquals(42, typeAdapterFactoryList.size());
    assertTrue(typeAdapterFactoryList.get(39) instanceof JsonAdapterAnnotationTypeAdapterFactory);
    assertTrue(typeAdapterFactoryList.get(41) instanceof ReflectiveTypeAdapterFactory);
    FormattingStyle formattingStyle = actualGson.formattingStyle;
    assertEquals("", formattingStyle.getIndent());
    assertEquals("", formattingStyle.getNewline());
    assertEquals("2020-03-01", actualGson.datePattern);
    assertEquals(1, actualGson.dateStyle);
    assertEquals(1, actualGson.timeStyle);
    assertEquals(LongSerializationPolicy.DEFAULT, actualGson.longSerializationPolicy);
    assertEquals(Strictness.LENIENT, actualGson.strictness);
    assertTrue(actualGson.htmlSafe());
    assertTrue(actualGson.serializeNulls());
    assertTrue(reflectionFilters.isEmpty());
    assertTrue(actualGson.builderFactories.isEmpty());
    assertTrue(actualGson.builderHierarchyFactories.isEmpty());
    assertTrue(actualGson.reflectionFilters.isEmpty());
    assertTrue(actualGson.instanceCreators.isEmpty());
    assertTrue(actualGson.complexMapKeySerialization);
    assertTrue(actualGson.generateNonExecutableJson);
    assertTrue(actualGson.htmlSafe);
    assertTrue(actualGson.serializeNulls);
    assertTrue(actualGson.serializeSpecialFloatingPointValues);
    assertTrue(actualGson.useJdkUnsafe);
    Excluder excluder = actualGson.excluder;
    assertSame(excluder, actualGson.excluder());
    assertSame(excluder, typeAdapterFactoryList.get(2));
    FieldNamingStrategy expectedFieldNamingStrategyResult = actualGson.fieldNamingStrategy;
    assertSame(expectedFieldNamingStrategyResult, actualGson.fieldNamingStrategy());
  }

  /**
   * Method under test: {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean,
   * boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy,
   * String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)}
   */
  @Test
  public void testNewGson3() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();

    // Act
    Gson actualGson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            false,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            reflectionFilters);

    // Assert
    List<TypeAdapterFactory> typeAdapterFactoryList = actualGson.factories;
    assertEquals(42, typeAdapterFactoryList.size());
    assertTrue(typeAdapterFactoryList.get(39) instanceof JsonAdapterAnnotationTypeAdapterFactory);
    assertTrue(typeAdapterFactoryList.get(41) instanceof ReflectiveTypeAdapterFactory);
    FormattingStyle formattingStyle = actualGson.formattingStyle;
    assertEquals("", formattingStyle.getIndent());
    assertEquals("", formattingStyle.getNewline());
    assertEquals("2020-03-01", actualGson.datePattern);
    assertEquals(1, actualGson.dateStyle);
    assertEquals(1, actualGson.timeStyle);
    assertEquals(LongSerializationPolicy.DEFAULT, actualGson.longSerializationPolicy);
    assertEquals(Strictness.LENIENT, actualGson.strictness);
    assertFalse(actualGson.serializeSpecialFloatingPointValues);
    assertTrue(actualGson.htmlSafe());
    assertTrue(actualGson.serializeNulls());
    assertTrue(reflectionFilters.isEmpty());
    assertTrue(actualGson.builderFactories.isEmpty());
    assertTrue(actualGson.builderHierarchyFactories.isEmpty());
    assertTrue(actualGson.reflectionFilters.isEmpty());
    assertTrue(actualGson.instanceCreators.isEmpty());
    assertTrue(actualGson.complexMapKeySerialization);
    assertTrue(actualGson.generateNonExecutableJson);
    assertTrue(actualGson.htmlSafe);
    assertTrue(actualGson.serializeNulls);
    assertTrue(actualGson.useJdkUnsafe);
    Excluder excluder = actualGson.excluder;
    assertSame(excluder, actualGson.excluder());
    assertSame(excluder, typeAdapterFactoryList.get(2));
    FieldNamingStrategy expectedFieldNamingStrategyResult = actualGson.fieldNamingStrategy;
    assertSame(expectedFieldNamingStrategyResult, actualGson.fieldNamingStrategy());
  }

  /**
   * Method under test: {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean,
   * boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy,
   * String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)}
   */
  @Test
  public void testNewGson4() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();

    // Act
    Gson actualGson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.STRING,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            reflectionFilters);

    // Assert
    List<TypeAdapterFactory> typeAdapterFactoryList = actualGson.factories;
    assertEquals(42, typeAdapterFactoryList.size());
    assertTrue(typeAdapterFactoryList.get(39) instanceof JsonAdapterAnnotationTypeAdapterFactory);
    assertTrue(typeAdapterFactoryList.get(41) instanceof ReflectiveTypeAdapterFactory);
    FormattingStyle formattingStyle = actualGson.formattingStyle;
    assertEquals("", formattingStyle.getIndent());
    assertEquals("", formattingStyle.getNewline());
    assertEquals("2020-03-01", actualGson.datePattern);
    assertEquals(1, actualGson.dateStyle);
    assertEquals(1, actualGson.timeStyle);
    assertEquals(LongSerializationPolicy.STRING, actualGson.longSerializationPolicy);
    assertEquals(Strictness.LENIENT, actualGson.strictness);
    assertTrue(actualGson.htmlSafe());
    assertTrue(actualGson.serializeNulls());
    assertTrue(reflectionFilters.isEmpty());
    assertTrue(actualGson.builderFactories.isEmpty());
    assertTrue(actualGson.builderHierarchyFactories.isEmpty());
    assertTrue(actualGson.reflectionFilters.isEmpty());
    assertTrue(actualGson.instanceCreators.isEmpty());
    assertTrue(actualGson.complexMapKeySerialization);
    assertTrue(actualGson.generateNonExecutableJson);
    assertTrue(actualGson.htmlSafe);
    assertTrue(actualGson.serializeNulls);
    assertTrue(actualGson.serializeSpecialFloatingPointValues);
    assertTrue(actualGson.useJdkUnsafe);
    Excluder excluder = actualGson.excluder;
    assertSame(excluder, actualGson.excluder());
    assertSame(excluder, typeAdapterFactoryList.get(2));
    FieldNamingStrategy expectedFieldNamingStrategyResult = actualGson.fieldNamingStrategy;
    assertSame(expectedFieldNamingStrategyResult, actualGson.fieldNamingStrategy());
  }

  /**
   * Method under test: {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean,
   * boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy,
   * String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)}
   */
  @Test
  public void testNewGson5() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();

    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    builderFactories.add(mock(TypeAdapterFactory.class));
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();

    // Act
    Gson actualGson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            reflectionFilters);

    // Assert
    List<TypeAdapterFactory> typeAdapterFactoryList = actualGson.factories;
    assertEquals(42, typeAdapterFactoryList.size());
    assertTrue(typeAdapterFactoryList.get(39) instanceof JsonAdapterAnnotationTypeAdapterFactory);
    assertTrue(typeAdapterFactoryList.get(41) instanceof ReflectiveTypeAdapterFactory);
    FormattingStyle formattingStyle = actualGson.formattingStyle;
    assertEquals("", formattingStyle.getIndent());
    assertEquals("", formattingStyle.getNewline());
    assertEquals("2020-03-01", actualGson.datePattern);
    assertEquals(1, actualGson.builderFactories.size());
    assertEquals(1, actualGson.dateStyle);
    assertEquals(1, actualGson.timeStyle);
    assertEquals(LongSerializationPolicy.DEFAULT, actualGson.longSerializationPolicy);
    assertEquals(Strictness.LENIENT, actualGson.strictness);
    assertTrue(actualGson.htmlSafe());
    assertTrue(actualGson.serializeNulls());
    assertTrue(reflectionFilters.isEmpty());
    assertTrue(actualGson.builderHierarchyFactories.isEmpty());
    assertTrue(actualGson.reflectionFilters.isEmpty());
    assertTrue(actualGson.instanceCreators.isEmpty());
    assertTrue(actualGson.complexMapKeySerialization);
    assertTrue(actualGson.generateNonExecutableJson);
    assertTrue(actualGson.htmlSafe);
    assertTrue(actualGson.serializeNulls);
    assertTrue(actualGson.serializeSpecialFloatingPointValues);
    assertTrue(actualGson.useJdkUnsafe);
    Excluder excluder = actualGson.excluder;
    assertSame(excluder, actualGson.excluder());
    assertSame(excluder, typeAdapterFactoryList.get(2));
    FieldNamingStrategy expectedFieldNamingStrategyResult = actualGson.fieldNamingStrategy;
    assertSame(expectedFieldNamingStrategyResult, actualGson.fieldNamingStrategy());
  }

  /**
   * Method under test: {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean,
   * boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy,
   * String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)}
   */
  @Test
  public void testNewGson6() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();

    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    builderFactories.add(mock(TypeAdapterFactory.class));
    builderFactories.add(mock(TypeAdapterFactory.class));
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();

    // Act
    Gson actualGson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            reflectionFilters);

    // Assert
    List<TypeAdapterFactory> typeAdapterFactoryList = actualGson.factories;
    assertEquals(42, typeAdapterFactoryList.size());
    assertTrue(typeAdapterFactoryList.get(39) instanceof JsonAdapterAnnotationTypeAdapterFactory);
    assertTrue(typeAdapterFactoryList.get(41) instanceof ReflectiveTypeAdapterFactory);
    FormattingStyle formattingStyle = actualGson.formattingStyle;
    assertEquals("", formattingStyle.getIndent());
    assertEquals("", formattingStyle.getNewline());
    assertEquals("2020-03-01", actualGson.datePattern);
    assertEquals(1, actualGson.dateStyle);
    assertEquals(1, actualGson.timeStyle);
    assertEquals(2, actualGson.builderFactories.size());
    assertEquals(LongSerializationPolicy.DEFAULT, actualGson.longSerializationPolicy);
    assertEquals(Strictness.LENIENT, actualGson.strictness);
    assertTrue(actualGson.htmlSafe());
    assertTrue(actualGson.serializeNulls());
    assertTrue(reflectionFilters.isEmpty());
    assertTrue(actualGson.builderHierarchyFactories.isEmpty());
    assertTrue(actualGson.reflectionFilters.isEmpty());
    assertTrue(actualGson.instanceCreators.isEmpty());
    assertTrue(actualGson.complexMapKeySerialization);
    assertTrue(actualGson.generateNonExecutableJson);
    assertTrue(actualGson.htmlSafe);
    assertTrue(actualGson.serializeNulls);
    assertTrue(actualGson.serializeSpecialFloatingPointValues);
    assertTrue(actualGson.useJdkUnsafe);
    Excluder excluder = actualGson.excluder;
    assertSame(excluder, actualGson.excluder());
    assertSame(excluder, typeAdapterFactoryList.get(2));
    FieldNamingStrategy expectedFieldNamingStrategyResult = actualGson.fieldNamingStrategy;
    assertSame(expectedFieldNamingStrategyResult, actualGson.fieldNamingStrategy());
  }

  /**
   * Method under test: {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean,
   * boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy,
   * String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)}
   */
  @Test
  public void testNewGson7() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();

    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    builderHierarchyFactories.add(mock(TypeAdapterFactory.class));
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();

    // Act
    Gson actualGson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            reflectionFilters);

    // Assert
    List<TypeAdapterFactory> typeAdapterFactoryList = actualGson.factories;
    assertEquals(42, typeAdapterFactoryList.size());
    assertTrue(typeAdapterFactoryList.get(39) instanceof JsonAdapterAnnotationTypeAdapterFactory);
    assertTrue(typeAdapterFactoryList.get(41) instanceof ReflectiveTypeAdapterFactory);
    FormattingStyle formattingStyle = actualGson.formattingStyle;
    assertEquals("", formattingStyle.getIndent());
    assertEquals("", formattingStyle.getNewline());
    assertEquals("2020-03-01", actualGson.datePattern);
    assertEquals(1, actualGson.builderHierarchyFactories.size());
    assertEquals(1, actualGson.dateStyle);
    assertEquals(1, actualGson.timeStyle);
    assertEquals(LongSerializationPolicy.DEFAULT, actualGson.longSerializationPolicy);
    assertEquals(Strictness.LENIENT, actualGson.strictness);
    assertTrue(actualGson.htmlSafe());
    assertTrue(actualGson.serializeNulls());
    assertTrue(reflectionFilters.isEmpty());
    assertTrue(actualGson.builderFactories.isEmpty());
    assertTrue(actualGson.reflectionFilters.isEmpty());
    assertTrue(actualGson.instanceCreators.isEmpty());
    assertTrue(actualGson.complexMapKeySerialization);
    assertTrue(actualGson.generateNonExecutableJson);
    assertTrue(actualGson.htmlSafe);
    assertTrue(actualGson.serializeNulls);
    assertTrue(actualGson.serializeSpecialFloatingPointValues);
    assertTrue(actualGson.useJdkUnsafe);
    Excluder excluder = actualGson.excluder;
    assertSame(excluder, actualGson.excluder());
    assertSame(excluder, typeAdapterFactoryList.get(2));
    FieldNamingStrategy expectedFieldNamingStrategyResult = actualGson.fieldNamingStrategy;
    assertSame(expectedFieldNamingStrategyResult, actualGson.fieldNamingStrategy());
  }

  /**
   * Method under test: {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean,
   * boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy,
   * String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)}
   */
  @Test
  public void testNewGson8() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();

    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    builderHierarchyFactories.add(mock(TypeAdapterFactory.class));
    builderHierarchyFactories.add(mock(TypeAdapterFactory.class));
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();

    // Act
    Gson actualGson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            reflectionFilters);

    // Assert
    List<TypeAdapterFactory> typeAdapterFactoryList = actualGson.factories;
    assertEquals(42, typeAdapterFactoryList.size());
    assertTrue(typeAdapterFactoryList.get(39) instanceof JsonAdapterAnnotationTypeAdapterFactory);
    assertTrue(typeAdapterFactoryList.get(41) instanceof ReflectiveTypeAdapterFactory);
    FormattingStyle formattingStyle = actualGson.formattingStyle;
    assertEquals("", formattingStyle.getIndent());
    assertEquals("", formattingStyle.getNewline());
    assertEquals("2020-03-01", actualGson.datePattern);
    assertEquals(1, actualGson.dateStyle);
    assertEquals(1, actualGson.timeStyle);
    assertEquals(2, actualGson.builderHierarchyFactories.size());
    assertEquals(LongSerializationPolicy.DEFAULT, actualGson.longSerializationPolicy);
    assertEquals(Strictness.LENIENT, actualGson.strictness);
    assertTrue(actualGson.htmlSafe());
    assertTrue(actualGson.serializeNulls());
    assertTrue(reflectionFilters.isEmpty());
    assertTrue(actualGson.builderFactories.isEmpty());
    assertTrue(actualGson.reflectionFilters.isEmpty());
    assertTrue(actualGson.instanceCreators.isEmpty());
    assertTrue(actualGson.complexMapKeySerialization);
    assertTrue(actualGson.generateNonExecutableJson);
    assertTrue(actualGson.htmlSafe);
    assertTrue(actualGson.serializeNulls);
    assertTrue(actualGson.serializeSpecialFloatingPointValues);
    assertTrue(actualGson.useJdkUnsafe);
    Excluder excluder = actualGson.excluder;
    assertSame(excluder, actualGson.excluder());
    assertSame(excluder, typeAdapterFactoryList.get(2));
    FieldNamingStrategy expectedFieldNamingStrategyResult = actualGson.fieldNamingStrategy;
    assertSame(expectedFieldNamingStrategyResult, actualGson.fieldNamingStrategy());
  }

  /**
   * Method under test: {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean,
   * boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy,
   * String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)}
   */
  @Test
  public void testNewGson9() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(mock(TypeAdapterFactory.class));
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();

    // Act
    Gson actualGson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            reflectionFilters);

    // Assert
    List<TypeAdapterFactory> typeAdapterFactoryList = actualGson.factories;
    assertEquals(43, typeAdapterFactoryList.size());
    assertTrue(typeAdapterFactoryList.get(40) instanceof JsonAdapterAnnotationTypeAdapterFactory);
    assertTrue(typeAdapterFactoryList.get(42) instanceof ReflectiveTypeAdapterFactory);
    FormattingStyle formattingStyle = actualGson.formattingStyle;
    assertEquals("", formattingStyle.getIndent());
    assertEquals("", formattingStyle.getNewline());
    assertEquals("2020-03-01", actualGson.datePattern);
    assertEquals(1, actualGson.dateStyle);
    assertEquals(1, actualGson.timeStyle);
    assertEquals(LongSerializationPolicy.DEFAULT, actualGson.longSerializationPolicy);
    assertEquals(Strictness.LENIENT, actualGson.strictness);
    assertTrue(actualGson.htmlSafe());
    assertTrue(actualGson.serializeNulls());
    assertTrue(reflectionFilters.isEmpty());
    assertTrue(actualGson.builderFactories.isEmpty());
    assertTrue(actualGson.builderHierarchyFactories.isEmpty());
    assertTrue(actualGson.reflectionFilters.isEmpty());
    assertTrue(actualGson.instanceCreators.isEmpty());
    assertTrue(actualGson.complexMapKeySerialization);
    assertTrue(actualGson.generateNonExecutableJson);
    assertTrue(actualGson.htmlSafe);
    assertTrue(actualGson.serializeNulls);
    assertTrue(actualGson.serializeSpecialFloatingPointValues);
    assertTrue(actualGson.useJdkUnsafe);
    Excluder excluder = actualGson.excluder;
    assertSame(excluder, actualGson.excluder());
    assertSame(excluder, typeAdapterFactoryList.get(2));
    FieldNamingStrategy expectedFieldNamingStrategyResult = actualGson.fieldNamingStrategy;
    assertSame(expectedFieldNamingStrategyResult, actualGson.fieldNamingStrategy());
  }

  /**
   * Method under test: {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean,
   * boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy,
   * String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)}
   */
  @Test
  public void testNewGson10() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(mock(TypeAdapterFactory.class));
    factoriesToBeAdded.add(mock(TypeAdapterFactory.class));
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();

    // Act
    Gson actualGson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            reflectionFilters);

    // Assert
    List<TypeAdapterFactory> typeAdapterFactoryList = actualGson.factories;
    assertEquals(44, typeAdapterFactoryList.size());
    assertTrue(typeAdapterFactoryList.get(41) instanceof JsonAdapterAnnotationTypeAdapterFactory);
    assertTrue(typeAdapterFactoryList.get(43) instanceof ReflectiveTypeAdapterFactory);
    FormattingStyle formattingStyle = actualGson.formattingStyle;
    assertEquals("", formattingStyle.getIndent());
    assertEquals("", formattingStyle.getNewline());
    assertEquals("2020-03-01", actualGson.datePattern);
    assertEquals(1, actualGson.dateStyle);
    assertEquals(1, actualGson.timeStyle);
    assertEquals(LongSerializationPolicy.DEFAULT, actualGson.longSerializationPolicy);
    assertEquals(Strictness.LENIENT, actualGson.strictness);
    assertTrue(actualGson.htmlSafe());
    assertTrue(actualGson.serializeNulls());
    assertTrue(reflectionFilters.isEmpty());
    assertTrue(actualGson.builderFactories.isEmpty());
    assertTrue(actualGson.builderHierarchyFactories.isEmpty());
    assertTrue(actualGson.reflectionFilters.isEmpty());
    assertTrue(actualGson.instanceCreators.isEmpty());
    assertTrue(actualGson.complexMapKeySerialization);
    assertTrue(actualGson.generateNonExecutableJson);
    assertTrue(actualGson.htmlSafe);
    assertTrue(actualGson.serializeNulls);
    assertTrue(actualGson.serializeSpecialFloatingPointValues);
    assertTrue(actualGson.useJdkUnsafe);
    Excluder excluder = actualGson.excluder;
    assertSame(excluder, actualGson.excluder());
    assertSame(excluder, typeAdapterFactoryList.get(2));
    FieldNamingStrategy expectedFieldNamingStrategyResult = actualGson.fieldNamingStrategy;
    assertSame(expectedFieldNamingStrategyResult, actualGson.fieldNamingStrategy());
  }

  /**
   * Method under test: {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean,
   * boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy,
   * String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)}
   */
  @Test
  public void testNewGson11() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();

    // Act
    Gson actualGson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            Gson.DEFAULT_OBJECT_TO_NUMBER_STRATEGY,
            numberToNumberStrategy,
            reflectionFilters);

    // Assert
    List<TypeAdapterFactory> typeAdapterFactoryList = actualGson.factories;
    assertEquals(42, typeAdapterFactoryList.size());
    assertTrue(typeAdapterFactoryList.get(39) instanceof JsonAdapterAnnotationTypeAdapterFactory);
    assertTrue(typeAdapterFactoryList.get(41) instanceof ReflectiveTypeAdapterFactory);
    FormattingStyle formattingStyle = actualGson.formattingStyle;
    assertEquals("", formattingStyle.getIndent());
    assertEquals("", formattingStyle.getNewline());
    assertEquals("2020-03-01", actualGson.datePattern);
    assertEquals(1, actualGson.dateStyle);
    assertEquals(1, actualGson.timeStyle);
    assertEquals(LongSerializationPolicy.DEFAULT, actualGson.longSerializationPolicy);
    assertEquals(Strictness.LENIENT, actualGson.strictness);
    assertTrue(actualGson.htmlSafe());
    assertTrue(actualGson.serializeNulls());
    assertTrue(reflectionFilters.isEmpty());
    assertTrue(actualGson.builderFactories.isEmpty());
    assertTrue(actualGson.builderHierarchyFactories.isEmpty());
    assertTrue(actualGson.reflectionFilters.isEmpty());
    assertTrue(actualGson.instanceCreators.isEmpty());
    assertTrue(actualGson.complexMapKeySerialization);
    assertTrue(actualGson.generateNonExecutableJson);
    assertTrue(actualGson.htmlSafe);
    assertTrue(actualGson.serializeNulls);
    assertTrue(actualGson.serializeSpecialFloatingPointValues);
    assertTrue(actualGson.useJdkUnsafe);
    Excluder excluder = actualGson.excluder;
    assertSame(excluder, actualGson.excluder());
    assertSame(excluder, typeAdapterFactoryList.get(2));
    FieldNamingStrategy expectedFieldNamingStrategyResult = actualGson.fieldNamingStrategy;
    assertSame(expectedFieldNamingStrategyResult, actualGson.fieldNamingStrategy());
  }

  /**
   * Method under test: {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean,
   * boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy,
   * String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)}
   */
  @Test
  public void testNewGson12() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();

    // Act
    Gson actualGson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            Gson.DEFAULT_NUMBER_TO_NUMBER_STRATEGY,
            reflectionFilters);

    // Assert
    List<TypeAdapterFactory> typeAdapterFactoryList = actualGson.factories;
    assertEquals(42, typeAdapterFactoryList.size());
    assertTrue(typeAdapterFactoryList.get(39) instanceof JsonAdapterAnnotationTypeAdapterFactory);
    assertTrue(typeAdapterFactoryList.get(41) instanceof ReflectiveTypeAdapterFactory);
    FormattingStyle formattingStyle = actualGson.formattingStyle;
    assertEquals("", formattingStyle.getIndent());
    assertEquals("", formattingStyle.getNewline());
    assertEquals("2020-03-01", actualGson.datePattern);
    assertEquals(1, actualGson.dateStyle);
    assertEquals(1, actualGson.timeStyle);
    assertEquals(LongSerializationPolicy.DEFAULT, actualGson.longSerializationPolicy);
    assertEquals(Strictness.LENIENT, actualGson.strictness);
    assertTrue(actualGson.htmlSafe());
    assertTrue(actualGson.serializeNulls());
    assertTrue(reflectionFilters.isEmpty());
    assertTrue(actualGson.builderFactories.isEmpty());
    assertTrue(actualGson.builderHierarchyFactories.isEmpty());
    assertTrue(actualGson.reflectionFilters.isEmpty());
    assertTrue(actualGson.instanceCreators.isEmpty());
    assertTrue(actualGson.complexMapKeySerialization);
    assertTrue(actualGson.generateNonExecutableJson);
    assertTrue(actualGson.htmlSafe);
    assertTrue(actualGson.serializeNulls);
    assertTrue(actualGson.serializeSpecialFloatingPointValues);
    assertTrue(actualGson.useJdkUnsafe);
    Excluder excluder = actualGson.excluder;
    assertSame(excluder, actualGson.excluder());
    assertSame(excluder, typeAdapterFactoryList.get(2));
    FieldNamingStrategy expectedFieldNamingStrategyResult = actualGson.fieldNamingStrategy;
    assertSame(expectedFieldNamingStrategyResult, actualGson.fieldNamingStrategy());
  }

  /**
   * Method under test: {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean,
   * boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy,
   * String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)}
   */
  @Test
  public void testNewGson13() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();
    reflectionFilters.add(mock(ReflectionAccessFilter.class));

    // Act
    Gson actualGson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            reflectionFilters);

    // Assert
    assertEquals(1, reflectionFilters.size());
    List<TypeAdapterFactory> typeAdapterFactoryList = actualGson.factories;
    assertEquals(42, typeAdapterFactoryList.size());
    assertTrue(typeAdapterFactoryList.get(39) instanceof JsonAdapterAnnotationTypeAdapterFactory);
    assertTrue(typeAdapterFactoryList.get(41) instanceof ReflectiveTypeAdapterFactory);
    FormattingStyle formattingStyle = actualGson.formattingStyle;
    assertEquals("", formattingStyle.getIndent());
    assertEquals("", formattingStyle.getNewline());
    assertEquals("2020-03-01", actualGson.datePattern);
    assertEquals(1, actualGson.reflectionFilters.size());
    assertEquals(1, actualGson.dateStyle);
    assertEquals(1, actualGson.timeStyle);
    assertEquals(LongSerializationPolicy.DEFAULT, actualGson.longSerializationPolicy);
    assertEquals(Strictness.LENIENT, actualGson.strictness);
    assertTrue(actualGson.htmlSafe());
    assertTrue(actualGson.serializeNulls());
    assertTrue(actualGson.builderFactories.isEmpty());
    assertTrue(actualGson.builderHierarchyFactories.isEmpty());
    assertTrue(actualGson.instanceCreators.isEmpty());
    assertTrue(actualGson.complexMapKeySerialization);
    assertTrue(actualGson.generateNonExecutableJson);
    assertTrue(actualGson.htmlSafe);
    assertTrue(actualGson.serializeNulls);
    assertTrue(actualGson.serializeSpecialFloatingPointValues);
    assertTrue(actualGson.useJdkUnsafe);
    Excluder excluder = actualGson.excluder;
    assertSame(excluder, actualGson.excluder());
    assertSame(excluder, typeAdapterFactoryList.get(2));
    FieldNamingStrategy expectedFieldNamingStrategyResult = actualGson.fieldNamingStrategy;
    assertSame(expectedFieldNamingStrategyResult, actualGson.fieldNamingStrategy());
  }

  /**
   * Method under test: {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean,
   * boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy,
   * String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)}
   */
  @Test
  public void testNewGson14() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();
    reflectionFilters.add(mock(ReflectionAccessFilter.class));
    reflectionFilters.add(mock(ReflectionAccessFilter.class));

    // Act
    Gson actualGson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            reflectionFilters);

    // Assert
    assertEquals(2, reflectionFilters.size());
    List<TypeAdapterFactory> typeAdapterFactoryList = actualGson.factories;
    assertEquals(42, typeAdapterFactoryList.size());
    assertTrue(typeAdapterFactoryList.get(39) instanceof JsonAdapterAnnotationTypeAdapterFactory);
    assertTrue(typeAdapterFactoryList.get(41) instanceof ReflectiveTypeAdapterFactory);
    FormattingStyle formattingStyle = actualGson.formattingStyle;
    assertEquals("", formattingStyle.getIndent());
    assertEquals("", formattingStyle.getNewline());
    assertEquals("2020-03-01", actualGson.datePattern);
    assertEquals(1, actualGson.dateStyle);
    assertEquals(1, actualGson.timeStyle);
    assertEquals(2, actualGson.reflectionFilters.size());
    assertEquals(LongSerializationPolicy.DEFAULT, actualGson.longSerializationPolicy);
    assertEquals(Strictness.LENIENT, actualGson.strictness);
    assertTrue(actualGson.htmlSafe());
    assertTrue(actualGson.serializeNulls());
    assertTrue(actualGson.builderFactories.isEmpty());
    assertTrue(actualGson.builderHierarchyFactories.isEmpty());
    assertTrue(actualGson.instanceCreators.isEmpty());
    assertTrue(actualGson.complexMapKeySerialization);
    assertTrue(actualGson.generateNonExecutableJson);
    assertTrue(actualGson.htmlSafe);
    assertTrue(actualGson.serializeNulls);
    assertTrue(actualGson.serializeSpecialFloatingPointValues);
    assertTrue(actualGson.useJdkUnsafe);
    Excluder excluder = actualGson.excluder;
    assertSame(excluder, actualGson.excluder());
    assertSame(excluder, typeAdapterFactoryList.get(2));
    FieldNamingStrategy expectedFieldNamingStrategyResult = actualGson.fieldNamingStrategy;
    assertSame(expectedFieldNamingStrategyResult, actualGson.fieldNamingStrategy());
  }

  /** Method under test: {@link Gson#newJsonReader(Reader)} */
  @Test
  public void testNewJsonReader() {
    // Arrange
    Gson gson = new Gson();

    // Act
    JsonReader actualNewJsonReaderResult = gson.newJsonReader(new StringReader("foo"));

    // Assert
    assertEquals("$", actualNewJsonReaderResult.getPath());
    assertEquals("$", actualNewJsonReaderResult.getPreviousPath());
    assertEquals(255, actualNewJsonReaderResult.getNestingLimit());
    assertEquals(Strictness.LEGACY_STRICT, actualNewJsonReaderResult.getStrictness());
    assertFalse(actualNewJsonReaderResult.isLenient());
  }

  /** Method under test: {@link Gson#newJsonReader(Reader)} */
  @Test
  public void testNewJsonReader2() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            255,
            255,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());

    // Act
    JsonReader actualNewJsonReaderResult = gson.newJsonReader(new StringReader("foo"));

    // Assert
    assertEquals("$", actualNewJsonReaderResult.getPath());
    assertEquals("$", actualNewJsonReaderResult.getPreviousPath());
    assertEquals(255, actualNewJsonReaderResult.getNestingLimit());
    assertEquals(Strictness.LENIENT, actualNewJsonReaderResult.getStrictness());
    assertTrue(actualNewJsonReaderResult.isLenient());
  }

  /** Method under test: {@link Gson#newJsonWriter(Writer)} */
  @Test
  public void testNewJsonWriter() throws IOException {
    // Arrange
    Gson gson = new Gson();
    StringWriter writer = new StringWriter();

    // Act
    JsonWriter actualNewJsonWriterResult = gson.newJsonWriter(writer);

    // Assert
    assertEquals("", writer.toString());
    assertEquals(Strictness.LEGACY_STRICT, actualNewJsonWriterResult.getStrictness());
    assertFalse(actualNewJsonWriterResult.getSerializeNulls());
    assertFalse(actualNewJsonWriterResult.isLenient());
    assertTrue(actualNewJsonWriterResult.isHtmlSafe());
    FormattingStyle expectedFormattingStyle = gson.formattingStyle;
    assertSame(expectedFormattingStyle, actualNewJsonWriterResult.getFormattingStyle());
  }

  /** Method under test: {@link Gson#newJsonWriter(Writer)} */
  @Test
  public void testNewJsonWriter2() throws IOException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringWriter writer = new StringWriter();

    // Act
    JsonWriter actualNewJsonWriterResult = gson.newJsonWriter(writer);

    // Assert
    assertEquals(")]}'\n", writer.toString());
    assertEquals(Strictness.LENIENT, actualNewJsonWriterResult.getStrictness());
    assertTrue(actualNewJsonWriterResult.getSerializeNulls());
    assertTrue(actualNewJsonWriterResult.isHtmlSafe());
    assertTrue(actualNewJsonWriterResult.isLenient());
    FormattingStyle expectedFormattingStyle = gson.formattingStyle;
    assertSame(expectedFormattingStyle, actualNewJsonWriterResult.getFormattingStyle());
  }

  /** Method under test: {@link Gson#newJsonWriter(Writer)} */
  @Test
  public void testNewJsonWriter3() throws IOException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            FormattingStyle.PRETTY,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringWriter writer = new StringWriter();

    // Act
    JsonWriter actualNewJsonWriterResult = gson.newJsonWriter(writer);

    // Assert
    assertEquals(")]}'\n", writer.toString());
    assertEquals(Strictness.LENIENT, actualNewJsonWriterResult.getStrictness());
    assertTrue(actualNewJsonWriterResult.getSerializeNulls());
    assertTrue(actualNewJsonWriterResult.isHtmlSafe());
    assertTrue(actualNewJsonWriterResult.isLenient());
    FormattingStyle expectedFormattingStyle = gson.formattingStyle;
    assertSame(expectedFormattingStyle, actualNewJsonWriterResult.getFormattingStyle());
  }

  /** Method under test: {@link Gson#toJson(JsonElement)} */
  @Test
  public void testToJson() {
    // Arrange, Act and Assert
    assertEquals("null", (new Gson()).toJson(JsonNull.INSTANCE));
    assertEquals("\"Src\"", (new Gson()).toJson("Src"));
    assertEquals(
        "{\"version\":-1.0,\"modifiers\":136,\"serializeInnerClasses\":true,\"requireExpose\":false,\"serializationStrategies"
            + "\":[],\"deserializationStrategies\":[]}",
        (new Gson()).toJson(Excluder.DEFAULT));
    assertEquals("\"out \\u003d\\u003d null\"", (new Gson()).toJson("out == null"));
    assertEquals("null", (new Gson()).toJson((Object) null));
  }

  /** Method under test: {@link Gson#toJson(JsonElement)} */
  @Test
  public void testToJson2() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    // Act and Assert
    assertEquals(
        ")]}'\nnull",
        (new Gson(
                Excluder.DEFAULT,
                fieldNamingStrategy,
                instanceCreators,
                true,
                true,
                true,
                true,
                Gson.DEFAULT_FORMATTING_STYLE,
                Strictness.LENIENT,
                true,
                true,
                LongSerializationPolicy.DEFAULT,
                "2020-03-01",
                Integer.SIZE,
                Integer.SIZE,
                builderFactories,
                builderHierarchyFactories,
                factoriesToBeAdded,
                objectToNumberStrategy,
                numberToNumberStrategy,
                new ArrayList<>()))
            .toJson(JsonNull.INSTANCE));
  }

  /** Method under test: {@link Gson#toJson(JsonElement)} */
  @Test
  public void testToJson3() {
    // Arrange
    Gson gson = new Gson();

    // Act and Assert
    assertEquals("[]", gson.toJson(new JsonArray(3)));
  }

  /** Method under test: {@link Gson#toJson(JsonElement)} */
  @Test
  public void testToJson4() {
    // Arrange
    Gson gson = new Gson();

    // Act and Assert
    assertEquals("{}", gson.toJson(new JsonObject()));
  }

  /** Method under test: {@link Gson#toJson(JsonElement)} */
  @Test
  public void testToJson5() {
    // Arrange
    Gson gson = new Gson();

    // Act and Assert
    assertEquals("\"out \\u003d\\u003d null\"", gson.toJson(new JsonPrimitive("out == null")));
  }

  /** Method under test: {@link Gson#toJson(JsonElement)} */
  @Test
  public void testToJson6() {
    // Arrange
    Gson gson = new Gson();

    // Act
    String actualToJsonResult = gson.toJson(new JsonPrimitive(true));

    // Assert
    assertEquals(Boolean.TRUE.toString(), actualToJsonResult);
  }

  /** Method under test: {@link Gson#toJson(JsonElement)} */
  @Test
  public void testToJson7() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    // Act and Assert
    assertEquals(
        ")]}'\nnull",
        (new Gson(
                Excluder.DEFAULT,
                fieldNamingStrategy,
                instanceCreators,
                true,
                true,
                true,
                true,
                FormattingStyle.PRETTY,
                Strictness.LENIENT,
                true,
                true,
                LongSerializationPolicy.DEFAULT,
                "2020-03-01",
                Integer.SIZE,
                Integer.SIZE,
                builderFactories,
                builderHierarchyFactories,
                factoriesToBeAdded,
                objectToNumberStrategy,
                numberToNumberStrategy,
                new ArrayList<>()))
            .toJson(JsonNull.INSTANCE));
  }

  /** Method under test: {@link Gson#toJson(JsonElement)} */
  @Test
  public void testToJson8() {
    // Arrange
    Gson gson = new Gson();

    JsonArray jsonElement = new JsonArray(3);
    jsonElement.add(true);

    // Act and Assert
    assertEquals("[true]", gson.toJson(jsonElement));
  }

  /** Method under test: {@link Gson#toJson(JsonElement)} */
  @Test
  public void testToJson9() {
    // Arrange
    Gson gson = new Gson();

    JsonArray jsonElement = new JsonArray(3);
    jsonElement.add(false);
    jsonElement.add(true);

    // Act and Assert
    assertEquals("[false,true]", gson.toJson(jsonElement));
  }

  /** Method under test: {@link Gson#toJson(JsonElement)} */
  @Test
  public void testToJson10() {
    // Arrange
    Gson gson = new Gson();

    JsonArray jsonElement = new JsonArray(3);
    jsonElement.add('\u0006');
    jsonElement.add(true);

    // Act and Assert
    assertEquals("[\"\\u0006\",true]", gson.toJson(jsonElement));
  }

  /** Method under test: {@link Gson#toJson(JsonElement)} */
  @Test
  public void testToJson11() {
    // Arrange
    Gson gson = new Gson();

    JsonArray jsonElement = new JsonArray(3);
    jsonElement.add(Integer.valueOf(Integer.SIZE));
    jsonElement.add(true);

    // Act and Assert
    assertEquals("[32,true]", gson.toJson(jsonElement));
  }

  /** Method under test: {@link Gson#toJson(JsonElement)} */
  @Test
  public void testToJson12() {
    // Arrange
    Gson gson = new Gson();

    JsonArray jsonElement = new JsonArray(3);
    jsonElement.add(JsonNull.INSTANCE);
    jsonElement.add(true);

    // Act and Assert
    assertEquals("[null,true]", gson.toJson(jsonElement));
  }

  /** Method under test: {@link Gson#toJson(JsonElement)} */
  @Test
  public void testToJson13() {
    // Arrange
    Gson gson = new Gson();

    JsonObject jsonElement = new JsonObject();
    jsonElement.add("out == null", JsonNull.INSTANCE);

    // Act and Assert
    assertEquals("{}", gson.toJson(jsonElement));
  }

  /** Method under test: {@link Gson#toJson(JsonElement)} */
  @Test
  public void testToJson14() {
    // Arrange
    Gson gson = new Gson();

    JsonObject jsonElement = new JsonObject();
    jsonElement.addProperty("out == null", "42");

    // Act and Assert
    assertEquals("{\"out \\u003d\\u003d null\":\"42\"}", gson.toJson(jsonElement));
  }

  /** Method under test: {@link Gson#toJson(JsonElement)} */
  @Test
  public void testToJson15() {
    // Arrange
    Gson gson = new Gson();

    JsonObject jsonElement = new JsonObject();
    jsonElement.addProperty(",", "out == null");
    jsonElement.add("out == null", JsonNull.INSTANCE);

    // Act and Assert
    assertEquals("{\",\":\"out \\u003d\\u003d null\"}", gson.toJson(jsonElement));
  }

  /** Method under test: {@link Gson#toJson(JsonElement)} */
  @Test
  public void testToJson16() {
    // Arrange
    Gson gson = new Gson();

    JsonObject jsonElement = new JsonObject();
    jsonElement.addProperty(":", ",");
    jsonElement.addProperty("out == null", "42");

    // Act and Assert
    assertEquals("{\":\":\",\",\"out \\u003d\\u003d null\":\"42\"}", gson.toJson(jsonElement));
  }

  /** Method under test: {@link Gson#toJson(JsonElement, JsonWriter)} */
  @Test
  public void testToJson17() throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    JsonElement jsonElement = mock(JsonElement.class);
    when(jsonElement.getAsJsonPrimitive()).thenReturn(new JsonPrimitive("String"));
    when(jsonElement.isJsonNull()).thenReturn(false);
    when(jsonElement.isJsonPrimitive()).thenReturn(true);

    // Act and Assert
    assertThrows(
        JsonIOException.class, () -> gson.toJson(jsonElement, new JsonWriter(new PipedWriter())));
    verify(jsonElement).getAsJsonPrimitive();
    verify(jsonElement).isJsonNull();
    verify(jsonElement).isJsonPrimitive();
  }

  /** Method under test: {@link Gson#toJson(JsonElement, Appendable)} */
  @Test
  public void testToJson18() throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    CharArrayWriter writer = new CharArrayWriter(1);

    // Act
    gson.toJson(JsonNull.INSTANCE, writer);

    // Assert
    assertEquals(4, writer.size());
  }

  /** Method under test: {@link Gson#toJson(JsonElement, Appendable)} */
  @Test
  public void testToJson19() throws JsonIOException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    CharArrayWriter writer = new CharArrayWriter(1);

    // Act
    gson.toJson(JsonNull.INSTANCE, writer);

    // Assert
    assertEquals(9, writer.size());
  }

  /** Method under test: {@link Gson#toJson(JsonElement, Appendable)} */
  @Test
  public void testToJson20() throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    JsonArray jsonElement = new JsonArray(3);
    CharArrayWriter writer = new CharArrayWriter(1);

    // Act
    gson.toJson(jsonElement, writer);

    // Assert
    assertEquals(2, writer.size());
  }

  /** Method under test: {@link Gson#toJson(JsonElement, Appendable)} */
  @Test
  public void testToJson21() throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    JsonObject jsonElement = new JsonObject();
    CharArrayWriter writer = new CharArrayWriter(1);

    // Act
    gson.toJson(jsonElement, writer);

    // Assert
    assertEquals(2, writer.size());
  }

  /** Method under test: {@link Gson#toJson(JsonElement, Appendable)} */
  @Test
  public void testToJson22() throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    JsonPrimitive jsonElement = new JsonPrimitive("out == null");
    CharArrayWriter writer = new CharArrayWriter(1);

    // Act
    gson.toJson(jsonElement, writer);

    // Assert
    assertEquals(23, writer.size());
  }

  /** Method under test: {@link Gson#toJson(JsonElement, Appendable)} */
  @Test
  public void testToJson23() throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    JsonPrimitive jsonElement = new JsonPrimitive(true);
    CharArrayWriter writer = new CharArrayWriter(1);

    // Act
    gson.toJson(jsonElement, writer);

    // Assert
    assertEquals(4, writer.size());
  }

  /** Method under test: {@link Gson#toJson(JsonElement, Appendable)} */
  @Test
  public void testToJson24() throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    JsonPrimitive jsonElement = new JsonPrimitive('\u0001');
    CharArrayWriter writer = new CharArrayWriter(1);

    // Act
    gson.toJson(jsonElement, writer);

    // Assert
    assertEquals(8, writer.size());
  }

  /** Method under test: {@link Gson#toJson(JsonElement, Appendable)} */
  @Test
  public void testToJson25() throws JsonIOException {
    // Arrange
    Gson gson = new Gson();

    // Act and Assert
    assertThrows(JsonIOException.class, () -> gson.toJson(JsonNull.INSTANCE, new PipedWriter()));
  }

  /** Method under test: {@link Gson#toJson(Object)} */
  @Test
  public void testToJson26() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    // Act and Assert
    assertEquals(
        ")]}'\n\"Src\"",
        (new Gson(
                Excluder.DEFAULT,
                fieldNamingStrategy,
                instanceCreators,
                true,
                true,
                true,
                true,
                Gson.DEFAULT_FORMATTING_STYLE,
                Strictness.LENIENT,
                true,
                true,
                LongSerializationPolicy.DEFAULT,
                "2020-03-01",
                Integer.SIZE,
                Integer.SIZE,
                builderFactories,
                builderHierarchyFactories,
                factoriesToBeAdded,
                objectToNumberStrategy,
                numberToNumberStrategy,
                new ArrayList<>()))
            .toJson("Src"));
  }

  /** Method under test: {@link Gson#toJson(Object)} */
  @Test
  public void testToJson27() {
    // Arrange
    Gson gson = new Gson();

    // Act and Assert
    assertEquals("{\"buf\":\"\",\"lock\":\"\"}", gson.toJson(new StringWriter()));
  }

  /** Method under test: {@link Gson#toJson(Object)} */
  @Test
  public void testToJson28() {
    // Arrange
    Gson gson = new Gson();

    // Act and Assert
    assertEquals("{}", gson.toJson(new Gson.FutureTypeAdapter<>()));
  }

  /** Method under test: {@link Gson#toJson(Object)} */
  @Test
  public void testToJson29() {
    // Arrange
    Gson gson = new Gson();

    // Act and Assert
    assertEquals("{}", gson.toJson(new HashMap<>()));
  }

  /** Method under test: {@link Gson#toJson(Object)} */
  @Test
  public void testToJson30() {
    // Arrange
    Gson gson = new Gson();

    // Act and Assert
    assertEquals(
        "{\"out\":{\"buf\":\"\",\"lock\":\"\"},\"cb\":[\"\\u0000\",\"\\u0000\",\"\\u0000\",\"\\u0000\",\"\\u0000\",\"\\u0000\",\"\\u0000\",\""
            + "\\u0000\",\"\\u0000\",\"\\u0000\",\"\\u0000\",\"\\u0000\",\"\\u0000\",\"\\u0000\",\"\\u0000\",\"\\u0000\",\"\\u0000\",\"\\u0000\",\""
            + "\\u0000\",\"\\u0000\",\"\\u0000\",\"\\u0000\",\"\\u0000\",\"\\u0000\",\"\\u0000\",\"\\u0000\",\"\\u0000\",\"\\u0000\",\"\\u0000\",\""
            + "\\u0000\",\"\\u0000\",\"\\u0000\"],\"nChars\":32,\"nextChar\":0,\"lock\":{\"buf\":\"\",\"lock\":\"\"}}",
        gson.toJson(new BufferedWriter(new StringWriter(), Integer.SIZE)));
  }

  /** Method under test: {@link Gson#toJson(Object)} */
  @Test
  public void testToJson31() {
    // Arrange
    Gson gson = new Gson();

    // Act and Assert
    assertEquals("{}", gson.toJson(new GenericMetadataSupport.TypeVarBoundedType(null)));
  }

  /** Method under test: {@link Gson#toJson(Object)} */
  @Test
  public void testToJson32() {
    // Arrange
    Gson gson = new Gson();

    // Act and Assert
    assertEquals("{}", gson.toJson(new TreeMap<>()));
  }

  /** Method under test: {@link Gson#toJson(Object)} */
  @Test
  public void testToJson33() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    // Act and Assert
    assertEquals(
        ")]}'\n\"Src\"",
        (new Gson(
                Excluder.DEFAULT,
                fieldNamingStrategy,
                instanceCreators,
                true,
                true,
                true,
                false,
                Gson.DEFAULT_FORMATTING_STYLE,
                Strictness.LENIENT,
                true,
                true,
                LongSerializationPolicy.DEFAULT,
                "2020-03-01",
                Integer.SIZE,
                Integer.SIZE,
                builderFactories,
                builderHierarchyFactories,
                factoriesToBeAdded,
                objectToNumberStrategy,
                numberToNumberStrategy,
                new ArrayList<>()))
            .toJson("Src"));
  }

  /** Method under test: {@link Gson#toJson(Object)} */
  @Test
  public void testToJson34() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    // Act and Assert
    assertEquals(
        ")]}'\n\"Src\"",
        (new Gson(
                Excluder.DEFAULT,
                fieldNamingStrategy,
                instanceCreators,
                true,
                true,
                true,
                true,
                FormattingStyle.PRETTY,
                Strictness.LENIENT,
                true,
                true,
                LongSerializationPolicy.DEFAULT,
                "2020-03-01",
                Integer.SIZE,
                Integer.SIZE,
                builderFactories,
                builderHierarchyFactories,
                factoriesToBeAdded,
                objectToNumberStrategy,
                numberToNumberStrategy,
                new ArrayList<>()))
            .toJson("Src"));
  }

  /** Method under test: {@link Gson#toJson(Object)} */
  @Test
  public void testToJson35() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(JsonNull.INSTANCE);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    // Act
    String actualToJsonResult =
        (new Gson(
                Excluder.DEFAULT,
                fieldNamingStrategy,
                instanceCreators,
                true,
                true,
                true,
                true,
                Gson.DEFAULT_FORMATTING_STYLE,
                Strictness.LENIENT,
                true,
                true,
                LongSerializationPolicy.DEFAULT,
                "2020-03-01",
                Integer.SIZE,
                Integer.SIZE,
                builderFactories,
                builderHierarchyFactories,
                factoriesToBeAdded,
                objectToNumberStrategy,
                numberToNumberStrategy,
                new ArrayList<>()))
            .toJson("Src");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertEquals(")]}'\nnull", actualToJsonResult);
  }

  /** Method under test: {@link Gson#toJson(Object)} */
  @Test
  public void testToJson36() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonArray(3));
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    // Act
    String actualToJsonResult =
        (new Gson(
                Excluder.DEFAULT,
                fieldNamingStrategy,
                instanceCreators,
                true,
                true,
                true,
                true,
                Gson.DEFAULT_FORMATTING_STYLE,
                Strictness.LENIENT,
                true,
                true,
                LongSerializationPolicy.DEFAULT,
                "2020-03-01",
                Integer.SIZE,
                Integer.SIZE,
                builderFactories,
                builderHierarchyFactories,
                factoriesToBeAdded,
                objectToNumberStrategy,
                numberToNumberStrategy,
                new ArrayList<>()))
            .toJson("Src");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertEquals(")]}'\n[]", actualToJsonResult);
  }

  /** Method under test: {@link Gson#toJson(Object)} */
  @Test
  public void testToJson37() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonObject());
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    // Act
    String actualToJsonResult =
        (new Gson(
                Excluder.DEFAULT,
                fieldNamingStrategy,
                instanceCreators,
                true,
                true,
                true,
                true,
                Gson.DEFAULT_FORMATTING_STYLE,
                Strictness.LENIENT,
                true,
                true,
                LongSerializationPolicy.DEFAULT,
                "2020-03-01",
                Integer.SIZE,
                Integer.SIZE,
                builderFactories,
                builderHierarchyFactories,
                factoriesToBeAdded,
                objectToNumberStrategy,
                numberToNumberStrategy,
                new ArrayList<>()))
            .toJson("Src");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertEquals(")]}'\n{}", actualToJsonResult);
  }

  /** Method under test: {@link Gson#toJson(Object)} */
  @Test
  public void testToJson38() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonPrimitive(")]}'\n"));
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    // Act
    String actualToJsonResult =
        (new Gson(
                Excluder.DEFAULT,
                fieldNamingStrategy,
                instanceCreators,
                true,
                true,
                true,
                true,
                Gson.DEFAULT_FORMATTING_STYLE,
                Strictness.LENIENT,
                true,
                true,
                LongSerializationPolicy.DEFAULT,
                "2020-03-01",
                Integer.SIZE,
                Integer.SIZE,
                builderFactories,
                builderHierarchyFactories,
                factoriesToBeAdded,
                objectToNumberStrategy,
                numberToNumberStrategy,
                new ArrayList<>()))
            .toJson("Src");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertEquals(")]}'\n\")]}\\u0027\\n\"", actualToJsonResult);
  }

  /** Method under test: {@link Gson#toJson(Object)} */
  @Test
  public void testToJson39() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonPrimitive(true));
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    // Act
    String actualToJsonResult =
        (new Gson(
                Excluder.DEFAULT,
                fieldNamingStrategy,
                instanceCreators,
                true,
                true,
                true,
                true,
                Gson.DEFAULT_FORMATTING_STYLE,
                Strictness.LENIENT,
                true,
                true,
                LongSerializationPolicy.DEFAULT,
                "2020-03-01",
                Integer.SIZE,
                Integer.SIZE,
                builderFactories,
                builderHierarchyFactories,
                factoriesToBeAdded,
                objectToNumberStrategy,
                numberToNumberStrategy,
                new ArrayList<>()))
            .toJson("Src");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertEquals(")]}'\ntrue", actualToJsonResult);
  }

  /** Method under test: {@link Gson#toJson(Object)} */
  @Test
  public void testToJson40() {
    // Arrange
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(mock(TypeAdapterFactory.class));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    // Act and Assert
    assertEquals(
        ")]}'\nnull",
        (new Gson(
                Excluder.DEFAULT,
                fieldNamingStrategy,
                instanceCreators,
                true,
                true,
                true,
                true,
                Gson.DEFAULT_FORMATTING_STYLE,
                Strictness.LENIENT,
                true,
                true,
                LongSerializationPolicy.DEFAULT,
                "2020-03-01",
                Integer.SIZE,
                Integer.SIZE,
                builderFactories,
                builderHierarchyFactories,
                factoriesToBeAdded,
                objectToNumberStrategy,
                numberToNumberStrategy,
                new ArrayList<>()))
            .toJson((Object) null));
  }

  /** Method under test: {@link Gson#toJson(Object)} */
  @Test
  public void testToJson41() {
    // Arrange
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(mock(TypeAdapterFactory.class));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    // Act and Assert
    assertEquals(
        ")]}'\nnull",
        (new Gson(
                Excluder.DEFAULT,
                fieldNamingStrategy,
                instanceCreators,
                true,
                true,
                true,
                true,
                Gson.DEFAULT_FORMATTING_STYLE,
                Strictness.LENIENT,
                true,
                true,
                LongSerializationPolicy.DEFAULT,
                "2020-03-01",
                Integer.SIZE,
                Integer.SIZE,
                builderFactories,
                builderHierarchyFactories,
                factoriesToBeAdded,
                objectToNumberStrategy,
                numberToNumberStrategy,
                new ArrayList<>()))
            .toJson((Object) JsonNull.INSTANCE));
  }

  /** Method under test: {@link Gson#toJson(Object)} */
  @Test
  public void testToJson42() {
    // Arrange
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(mock(TypeAdapterFactory.class));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());

    // Act and Assert
    assertEquals(")]}'\n\")]}\\u0027\\n\"", gson.toJson((Object) new JsonPrimitive(")]}'\n")));
  }

  /** Method under test: {@link Gson#toJson(Object, Appendable)} */
  @Test
  public void testToJson43() throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    CharArrayWriter writer = new CharArrayWriter(1);

    // Act
    gson.toJson("Src", writer);

    // Assert
    assertEquals(5, writer.size());
  }

  /** Method under test: {@link Gson#toJson(Object, Appendable)} */
  @Test
  public void testToJson44() throws JsonIOException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    CharArrayWriter writer = new CharArrayWriter(1);

    // Act
    gson.toJson("Src", writer);

    // Assert
    assertEquals(10, writer.size());
  }

  /** Method under test: {@link Gson#toJson(Object, Appendable)} */
  @Test
  public void testToJson45() throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    CharArrayWriter writer = new CharArrayWriter(1);

    // Act
    gson.toJson(Excluder.DEFAULT, writer);

    // Assert
    assertEquals(143, writer.size());
  }

  /** Method under test: {@link Gson#toJson(Object, Appendable)} */
  @Test
  public void testToJson46() throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    CharArrayWriter writer = new CharArrayWriter(1);

    // Act
    gson.toJson("out == null", writer);

    // Assert
    assertEquals(23, writer.size());
  }

  /** Method under test: {@link Gson#toJson(Object, Appendable)} */
  @Test
  public void testToJson47() throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    StringWriter stringWriter = new StringWriter();
    CharArrayWriter writer = new CharArrayWriter(1);

    // Act
    gson.toJson(stringWriter, writer);

    // Assert
    assertEquals(20, writer.size());
  }

  /** Method under test: {@link Gson#toJson(Object, Appendable)} */
  @Test
  public void testToJson48() throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    CharArrayWriter writer = new CharArrayWriter(1);

    // Act
    gson.toJson((Object) null, writer);

    // Assert
    assertEquals(4, writer.size());
  }

  /** Method under test: {@link Gson#toJson(Object, Appendable)} */
  @Test
  public void testToJson49() throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    CharArrayWriter writer = new CharArrayWriter(1);

    // Act
    gson.toJson(futureTypeAdapter, writer);

    // Assert
    assertEquals(2, writer.size());
  }

  /** Method under test: {@link Gson#toJson(Object, Appendable)} */
  @Test
  public void testToJson50() throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    HashMap<Object, Object> objectObjectMap = new HashMap<>();
    CharArrayWriter writer = new CharArrayWriter(1);

    // Act
    gson.toJson(objectObjectMap, writer);

    // Assert
    assertEquals(2, writer.size());
  }

  /** Method under test: {@link Gson#toJson(Object, Appendable)} */
  @Test
  public void testToJson51() throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    BufferedWriter bufferedWriter = new BufferedWriter(new StringWriter(), 1);

    CharArrayWriter writer = new CharArrayWriter(1);

    // Act
    gson.toJson(bufferedWriter, writer);

    // Assert
    assertEquals(96, writer.size());
  }

  /** Method under test: {@link Gson#toJson(Object, Appendable)} */
  @Test
  public void testToJson52() throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    GenericMetadataSupport.TypeVarBoundedType typeVarBoundedType =
        new GenericMetadataSupport.TypeVarBoundedType(null);
    CharArrayWriter writer = new CharArrayWriter(1);

    // Act
    gson.toJson(typeVarBoundedType, writer);

    // Assert
    assertEquals(2, writer.size());
  }

  /** Method under test: {@link Gson#toJson(Object, Appendable)} */
  @Test
  public void testToJson53() throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    TreeMap<Object, Object> objectObjectMap = new TreeMap<>();
    CharArrayWriter writer = new CharArrayWriter(1);

    // Act
    gson.toJson(objectObjectMap, writer);

    // Assert
    assertEquals(2, writer.size());
  }

  /** Method under test: {@link Gson#toJson(Object, Appendable)} */
  @Test
  public void testToJson54() throws JsonIOException {
    // Arrange
    Gson gson = new Gson();

    // Act and Assert
    assertThrows(JsonIOException.class, () -> gson.toJson("Src", new PipedWriter()));
  }

  /** Method under test: {@link Gson#toJson(Object, Type)} */
  @Test
  public void testToJson55() {
    // Arrange
    Gson gson = new Gson();
    Class<Object> typeOfSrc = Object.class;

    // Act and Assert
    assertEquals("\"Src\"", gson.toJson("Src", typeOfSrc));
  }

  /** Method under test: {@link Gson#toJson(Object, Type)} */
  @Test
  public void testToJson56() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> typeOfSrc = Object.class;

    // Act and Assert
    assertEquals(")]}'\n\"Src\"", gson.toJson("Src", typeOfSrc));
  }

  /** Method under test: {@link Gson#toJson(Object, Type)} */
  @Test
  public void testToJson57() {
    // Arrange
    Gson gson = new Gson();
    Class<Object> typeOfSrc = Object.class;

    // Act and Assert
    assertEquals(
        "{\"version\":-1.0,\"modifiers\":136,\"serializeInnerClasses\":true,\"requireExpose\":false,\"serializationStrategies"
            + "\":[],\"deserializationStrategies\":[]}",
        gson.toJson(Excluder.DEFAULT, typeOfSrc));
  }

  /** Method under test: {@link Gson#toJson(Object, Type)} */
  @Test
  public void testToJson58() {
    // Arrange
    Gson gson = new Gson();
    Class<Object> typeOfSrc = Object.class;

    // Act and Assert
    assertEquals("\"out \\u003d\\u003d null\"", gson.toJson("out == null", typeOfSrc));
  }

  /** Method under test: {@link Gson#toJson(Object, Type)} */
  @Test
  public void testToJson59() {
    // Arrange
    Gson gson = new Gson();
    StringWriter stringWriter = new StringWriter();
    Class<Object> typeOfSrc = Object.class;

    // Act and Assert
    assertEquals("{\"buf\":\"\",\"lock\":\"\"}", gson.toJson(stringWriter, typeOfSrc));
  }

  /** Method under test: {@link Gson#toJson(Object, Type)} */
  @Test
  public void testToJson60() {
    // Arrange
    Gson gson = new Gson();
    Class<Object> typeOfSrc = Object.class;

    // Act and Assert
    assertEquals("null", gson.toJson(null, typeOfSrc));
  }

  /** Method under test: {@link Gson#toJson(Object, Type)} */
  @Test
  public void testToJson61() {
    // Arrange
    Gson gson = new Gson();
    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    Class<Object> typeOfSrc = Object.class;

    // Act and Assert
    assertEquals("{}", gson.toJson(futureTypeAdapter, typeOfSrc));
  }

  /** Method under test: {@link Gson#toJson(Object, Type)} */
  @Test
  public void testToJson62() {
    // Arrange
    Gson gson = new Gson();
    HashMap<Object, Object> objectObjectMap = new HashMap<>();
    Class<Object> typeOfSrc = Object.class;

    // Act and Assert
    assertEquals("{}", gson.toJson(objectObjectMap, typeOfSrc));
  }

  /** Method under test: {@link Gson#toJson(Object, Type)} */
  @Test
  public void testToJson63() {
    // Arrange
    Gson gson = new Gson();
    BufferedWriter bufferedWriter = new BufferedWriter(new StringWriter(), Integer.SIZE);

    Class<Object> typeOfSrc = Object.class;

    // Act and Assert
    assertEquals(
        "{\"out\":{\"buf\":\"\",\"lock\":\"\"},\"cb\":[\"\\u0000\",\"\\u0000\",\"\\u0000\",\"\\u0000\",\"\\u0000\",\"\\u0000\",\"\\u0000\",\""
            + "\\u0000\",\"\\u0000\",\"\\u0000\",\"\\u0000\",\"\\u0000\",\"\\u0000\",\"\\u0000\",\"\\u0000\",\"\\u0000\",\"\\u0000\",\"\\u0000\",\""
            + "\\u0000\",\"\\u0000\",\"\\u0000\",\"\\u0000\",\"\\u0000\",\"\\u0000\",\"\\u0000\",\"\\u0000\",\"\\u0000\",\"\\u0000\",\"\\u0000\",\""
            + "\\u0000\",\"\\u0000\",\"\\u0000\"],\"nChars\":32,\"nextChar\":0,\"lock\":{\"buf\":\"\",\"lock\":\"\"}}",
        gson.toJson(bufferedWriter, typeOfSrc));
  }

  /** Method under test: {@link Gson#toJson(Object, Type)} */
  @Test
  public void testToJson64() {
    // Arrange
    Gson gson = new Gson();
    GenericMetadataSupport.TypeVarBoundedType typeVarBoundedType =
        new GenericMetadataSupport.TypeVarBoundedType(null);
    Class<Object> typeOfSrc = Object.class;

    // Act and Assert
    assertEquals("{}", gson.toJson(typeVarBoundedType, typeOfSrc));
  }

  /** Method under test: {@link Gson#toJson(Object, Type)} */
  @Test
  public void testToJson65() {
    // Arrange
    Gson gson = new Gson();
    TreeMap<Object, Object> objectObjectMap = new TreeMap<>();
    Class<Object> typeOfSrc = Object.class;

    // Act and Assert
    assertEquals("{}", gson.toJson(objectObjectMap, typeOfSrc));
  }

  /** Method under test: {@link Gson#toJson(Object, Type)} */
  @Test
  public void testToJson66() {
    // Arrange
    Gson gson = new Gson();
    Class<TypeAdapterFactory> typeOfSrc = TypeAdapterFactory.class;

    // Act and Assert
    assertEquals("{}", gson.toJson("Src", typeOfSrc));
  }

  /** Method under test: {@link Gson#toJson(Object, Type)} */
  @Test
  public void testToJson67() {
    // Arrange
    Gson gson = new Gson();
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act and Assert
    assertEquals("null", gson.toJson(null, typeOfSrc));
  }

  /** Method under test: {@link Gson#toJson(Object, Type)} */
  @Test
  public void testToJson68() {
    // Arrange
    Gson gson = new Gson();
    LazilyParsedNumber lazilyParsedNumber = new LazilyParsedNumber("42");
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act and Assert
    assertEquals("42", gson.toJson(lazilyParsedNumber, typeOfSrc));
  }

  /** Method under test: {@link Gson#toJson(Object, Type)} */
  @Test
  public void testToJson69() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(JsonNull.INSTANCE);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson2 =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act
    String actualToJsonResult = gson2.toJson("Src", typeOfSrc);

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertEquals(")]}'\nnull", actualToJsonResult);
  }

  /** Method under test: {@link Gson#toJson(Object, Type)} */
  @Test
  public void testToJson70() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonArray(3));
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson2 =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act
    String actualToJsonResult = gson2.toJson("Src", typeOfSrc);

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertEquals(")]}'\n[]", actualToJsonResult);
  }

  /** Method under test: {@link Gson#toJson(Object, Type)} */
  @Test
  public void testToJson71() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonObject());
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson2 =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act
    String actualToJsonResult = gson2.toJson("Src", typeOfSrc);

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertEquals(")]}'\n{}", actualToJsonResult);
  }

  /** Method under test: {@link Gson#toJson(Object, Type)} */
  @Test
  public void testToJson72() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonPrimitive(")]}'\n"));
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson2 =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act
    String actualToJsonResult = gson2.toJson("Src", typeOfSrc);

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertEquals(")]}'\n\")]}\\u0027\\n\"", actualToJsonResult);
  }

  /** Method under test: {@link Gson#toJson(Object, Type)} */
  @Test
  public void testToJson73() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonPrimitive(true));
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson2 =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act
    String actualToJsonResult = gson2.toJson("Src", typeOfSrc);

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertEquals(")]}'\ntrue", actualToJsonResult);
  }

  /** Method under test: {@link Gson#toJson(Object, Type)} */
  @Test
  public void testToJson74() {
    // Arrange
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class), true));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson2 =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act
    String actualToJsonResult = gson2.toJson(null, typeOfSrc);

    // Assert
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertEquals(")]}'\nnull", actualToJsonResult);
  }

  /** Method under test: {@link Gson#toJson(Object, Type, JsonWriter)} */
  @Test
  public void testToJson75() throws JsonIOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(JsonNull.INSTANCE);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson2 =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act
    gson2.toJson("Src", typeOfSrc, new JsonWriter(new StringWriter()));

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
  }

  /** Method under test: {@link Gson#toJson(Object, Type, JsonWriter)} */
  @Test
  public void testToJson76() throws JsonIOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonArray(3));
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson2 =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act
    gson2.toJson("Src", typeOfSrc, new JsonWriter(new StringWriter()));

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
  }

  /** Method under test: {@link Gson#toJson(Object, Type, JsonWriter)} */
  @Test
  public void testToJson77() throws JsonIOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonObject());
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson2 =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act
    gson2.toJson("Src", typeOfSrc, new JsonWriter(new StringWriter()));

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
  }

  /** Method under test: {@link Gson#toJson(Object, Type, JsonWriter)} */
  @Test
  public void testToJson78() throws JsonIOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonPrimitive("type must not be null"));
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson2 =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act
    gson2.toJson("Src", typeOfSrc, new JsonWriter(new StringWriter()));

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
  }

  /** Method under test: {@link Gson#toJson(Object, Type, JsonWriter)} */
  @Test
  public void testToJson79() throws JsonIOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonPrimitive(true));
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson2 =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act
    gson2.toJson("Src", typeOfSrc, new JsonWriter(new StringWriter()));

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
  }

  /** Method under test: {@link Gson#toJson(Object, Type, JsonWriter)} */
  @Test
  public void testToJson80() throws JsonIOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonPrimitive('\u0001'));
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson2 =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act
    gson2.toJson("Src", typeOfSrc, new JsonWriter(new StringWriter()));

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
  }

  /** Method under test: {@link Gson#toJson(Object, Type, JsonWriter)} */
  @Test
  public void testToJson81() throws JsonIOException {
    // Arrange
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class), true));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson2 =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act
    gson2.toJson(null, typeOfSrc, new JsonWriter(new StringWriter()));

    // Assert
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
  }

  /** Method under test: {@link Gson#toJson(Object, Type, JsonWriter)} */
  @Test
  public void testToJson82() throws JsonIOException {
    // Arrange
    new IllegalArgumentException("type must not be null");
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    Gson context = new Gson();
    Gson.FutureTypeAdapter<Object> componentTypeAdapter = new Gson.FutureTypeAdapter<>();
    Class<Object> componentType = Object.class;
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act
    gson.toJson(null, typeOfSrc, new JsonWriter(new StringWriter()));

    // Assert
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
  }

  /** Method under test: {@link Gson#toJson(Object, Type, Appendable)} */
  @Test
  public void testToJson83() throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> typeOfSrc = Object.class;
    CharArrayWriter writer = new CharArrayWriter(1);

    // Act
    gson.toJson("Src", typeOfSrc, writer);

    // Assert
    assertEquals(5, writer.size());
  }

  /** Method under test: {@link Gson#toJson(Object, Type, Appendable)} */
  @Test
  public void testToJson84() throws JsonIOException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> typeOfSrc = Object.class;
    CharArrayWriter writer = new CharArrayWriter(1);

    // Act
    gson.toJson("Src", typeOfSrc, writer);

    // Assert
    assertEquals(10, writer.size());
  }

  /** Method under test: {@link Gson#toJson(Object, Type, Appendable)} */
  @Test
  public void testToJson85() throws JsonIOException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    GenericMetadataSupport.TypeVarBoundedType typeOfSrc =
        new GenericMetadataSupport.TypeVarBoundedType(null);

    // Act and Assert
    assertThrows(JsonIOException.class, () -> gson.toJson("Src", typeOfSrc, new PipedWriter()));
  }

  /** Method under test: {@link Gson#toJsonTree(Object)} */
  @Test
  public void testToJsonTree() {
    // Arrange and Act
    JsonElement actualToJsonTreeResult = (new Gson()).toJsonTree("Src");

    // Assert
    assertTrue(actualToJsonTreeResult instanceof JsonPrimitive);
    Number asNumber = actualToJsonTreeResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("Src", actualToJsonTreeResult.getAsString());
    assertEquals("Src", asNumber.toString());
    assertEquals('S', actualToJsonTreeResult.getAsCharacter());
    assertFalse(actualToJsonTreeResult.getAsBoolean());
    assertFalse(actualToJsonTreeResult.isJsonArray());
    assertFalse(actualToJsonTreeResult.isJsonNull());
    assertFalse(actualToJsonTreeResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualToJsonTreeResult).isBoolean());
    assertFalse(((JsonPrimitive) actualToJsonTreeResult).isNumber());
    assertTrue(actualToJsonTreeResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualToJsonTreeResult).isString());
    assertSame(actualToJsonTreeResult, actualToJsonTreeResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link Gson#toJsonTree(Object)} */
  @Test
  public void testToJsonTree2() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    // Act
    JsonElement actualToJsonTreeResult =
        (new Gson(
                Excluder.DEFAULT,
                fieldNamingStrategy,
                instanceCreators,
                true,
                true,
                true,
                true,
                Gson.DEFAULT_FORMATTING_STYLE,
                Strictness.LENIENT,
                true,
                true,
                LongSerializationPolicy.DEFAULT,
                "2020-03-01",
                Integer.SIZE,
                Integer.SIZE,
                builderFactories,
                builderHierarchyFactories,
                factoriesToBeAdded,
                objectToNumberStrategy,
                numberToNumberStrategy,
                new ArrayList<>()))
            .toJsonTree("Src");

    // Assert
    assertTrue(actualToJsonTreeResult instanceof JsonPrimitive);
    Number asNumber = actualToJsonTreeResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("Src", actualToJsonTreeResult.getAsString());
    assertEquals("Src", asNumber.toString());
    assertEquals('S', actualToJsonTreeResult.getAsCharacter());
    assertFalse(actualToJsonTreeResult.getAsBoolean());
    assertFalse(actualToJsonTreeResult.isJsonArray());
    assertFalse(actualToJsonTreeResult.isJsonNull());
    assertFalse(actualToJsonTreeResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualToJsonTreeResult).isBoolean());
    assertFalse(((JsonPrimitive) actualToJsonTreeResult).isNumber());
    assertTrue(actualToJsonTreeResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualToJsonTreeResult).isString());
    assertSame(actualToJsonTreeResult, actualToJsonTreeResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link Gson#toJsonTree(Object)} */
  @Test
  public void testToJsonTree3() {
    // Arrange and Act
    JsonElement actualToJsonTreeResult = (new Gson()).toJsonTree(Excluder.DEFAULT);

    // Assert
    assertTrue(actualToJsonTreeResult instanceof JsonObject);
    assertEquals(6, ((JsonObject) actualToJsonTreeResult).size());
    assertFalse(actualToJsonTreeResult.isJsonArray());
    assertFalse(actualToJsonTreeResult.isJsonNull());
    assertFalse(actualToJsonTreeResult.isJsonPrimitive());
    assertFalse(((JsonObject) actualToJsonTreeResult).isEmpty());
    assertTrue(actualToJsonTreeResult.isJsonObject());
    assertSame(actualToJsonTreeResult, actualToJsonTreeResult.getAsJsonObject());
  }

  /** Method under test: {@link Gson#toJsonTree(Object)} */
  @Test
  public void testToJsonTree4() {
    // Arrange
    Gson gson = new Gson();

    // Act
    JsonElement actualToJsonTreeResult = gson.toJsonTree(new StringWriter());

    // Assert
    assertTrue(actualToJsonTreeResult instanceof JsonObject);
    assertEquals(2, ((JsonObject) actualToJsonTreeResult).size());
    assertFalse(actualToJsonTreeResult.isJsonArray());
    assertFalse(actualToJsonTreeResult.isJsonNull());
    assertFalse(actualToJsonTreeResult.isJsonPrimitive());
    assertFalse(((JsonObject) actualToJsonTreeResult).isEmpty());
    assertTrue(actualToJsonTreeResult.isJsonObject());
    assertSame(actualToJsonTreeResult, actualToJsonTreeResult.getAsJsonObject());
  }

  /** Method under test: {@link Gson#toJsonTree(Object)} */
  @Test
  public void testToJsonTree5() {
    // Arrange and Act
    JsonElement actualToJsonTreeResult = (new Gson()).toJsonTree(null);

    // Assert
    assertSame(((JsonNull) actualToJsonTreeResult).INSTANCE, actualToJsonTreeResult);
  }

  /** Method under test: {@link Gson#toJsonTree(Object)} */
  @Test
  public void testToJsonTree6() {
    // Arrange
    Gson gson = new Gson();

    // Act
    JsonElement actualToJsonTreeResult = gson.toJsonTree(new Gson.FutureTypeAdapter<>());

    // Assert
    assertTrue(actualToJsonTreeResult instanceof JsonObject);
    assertEquals(0, ((JsonObject) actualToJsonTreeResult).size());
    assertFalse(actualToJsonTreeResult.isJsonArray());
    assertFalse(actualToJsonTreeResult.isJsonNull());
    assertFalse(actualToJsonTreeResult.isJsonPrimitive());
    assertTrue(actualToJsonTreeResult.isJsonObject());
    assertTrue(((JsonObject) actualToJsonTreeResult).isEmpty());
    assertSame(actualToJsonTreeResult, actualToJsonTreeResult.getAsJsonObject());
  }

  /** Method under test: {@link Gson#toJsonTree(Object)} */
  @Test
  public void testToJsonTree7() {
    // Arrange
    Gson gson = new Gson();

    // Act
    JsonElement actualToJsonTreeResult = gson.toJsonTree(new HashMap<>());

    // Assert
    assertTrue(actualToJsonTreeResult instanceof JsonObject);
    assertEquals(0, ((JsonObject) actualToJsonTreeResult).size());
    assertFalse(actualToJsonTreeResult.isJsonArray());
    assertFalse(actualToJsonTreeResult.isJsonNull());
    assertFalse(actualToJsonTreeResult.isJsonPrimitive());
    assertTrue(actualToJsonTreeResult.isJsonObject());
    assertTrue(((JsonObject) actualToJsonTreeResult).isEmpty());
    assertSame(actualToJsonTreeResult, actualToJsonTreeResult.getAsJsonObject());
  }

  /** Method under test: {@link Gson#toJsonTree(Object)} */
  @Test
  public void testToJsonTree8() {
    // Arrange
    Gson gson = new Gson();

    // Act
    JsonElement actualToJsonTreeResult =
        gson.toJsonTree(new BufferedWriter(new StringWriter(), Integer.SIZE));

    // Assert
    assertTrue(actualToJsonTreeResult instanceof JsonObject);
    assertEquals(5, ((JsonObject) actualToJsonTreeResult).size());
    assertFalse(actualToJsonTreeResult.isJsonArray());
    assertFalse(actualToJsonTreeResult.isJsonNull());
    assertFalse(actualToJsonTreeResult.isJsonPrimitive());
    assertFalse(((JsonObject) actualToJsonTreeResult).isEmpty());
    assertTrue(actualToJsonTreeResult.isJsonObject());
    assertSame(actualToJsonTreeResult, actualToJsonTreeResult.getAsJsonObject());
  }

  /** Method under test: {@link Gson#toJsonTree(Object)} */
  @Test
  public void testToJsonTree9() {
    // Arrange
    Gson gson = new Gson();

    // Act
    JsonElement actualToJsonTreeResult =
        gson.toJsonTree(new GenericMetadataSupport.TypeVarBoundedType(null));

    // Assert
    assertTrue(actualToJsonTreeResult instanceof JsonObject);
    assertEquals(0, ((JsonObject) actualToJsonTreeResult).size());
    assertFalse(actualToJsonTreeResult.isJsonArray());
    assertFalse(actualToJsonTreeResult.isJsonNull());
    assertFalse(actualToJsonTreeResult.isJsonPrimitive());
    assertTrue(actualToJsonTreeResult.isJsonObject());
    assertTrue(((JsonObject) actualToJsonTreeResult).isEmpty());
    assertSame(actualToJsonTreeResult, actualToJsonTreeResult.getAsJsonObject());
  }

  /** Method under test: {@link Gson#toJsonTree(Object)} */
  @Test
  public void testToJsonTree10() {
    // Arrange
    Gson gson = new Gson();

    // Act
    JsonElement actualToJsonTreeResult = gson.toJsonTree(new TreeMap<>());

    // Assert
    assertTrue(actualToJsonTreeResult instanceof JsonObject);
    assertEquals(0, ((JsonObject) actualToJsonTreeResult).size());
    assertFalse(actualToJsonTreeResult.isJsonArray());
    assertFalse(actualToJsonTreeResult.isJsonNull());
    assertFalse(actualToJsonTreeResult.isJsonPrimitive());
    assertTrue(actualToJsonTreeResult.isJsonObject());
    assertTrue(((JsonObject) actualToJsonTreeResult).isEmpty());
    assertSame(actualToJsonTreeResult, actualToJsonTreeResult.getAsJsonObject());
  }

  /** Method under test: {@link Gson#toJsonTree(Object)} */
  @Test
  public void testToJsonTree11() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(JsonNull.INSTANCE);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    // Act
    JsonElement actualToJsonTreeResult =
        (new Gson(
                Excluder.DEFAULT,
                fieldNamingStrategy,
                instanceCreators,
                true,
                true,
                true,
                true,
                Gson.DEFAULT_FORMATTING_STYLE,
                Strictness.LENIENT,
                true,
                true,
                LongSerializationPolicy.DEFAULT,
                "2020-03-01",
                Integer.SIZE,
                Integer.SIZE,
                builderFactories,
                builderHierarchyFactories,
                factoriesToBeAdded,
                objectToNumberStrategy,
                numberToNumberStrategy,
                new ArrayList<>()))
            .toJsonTree("Src");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertSame(((JsonNull) actualToJsonTreeResult).INSTANCE, actualToJsonTreeResult);
  }

  /** Method under test: {@link Gson#toJsonTree(Object)} */
  @Test
  public void testToJsonTree12() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonArray jsonArray = new JsonArray(3);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(jsonArray);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    // Act
    JsonElement actualToJsonTreeResult =
        (new Gson(
                Excluder.DEFAULT,
                fieldNamingStrategy,
                instanceCreators,
                true,
                true,
                true,
                true,
                Gson.DEFAULT_FORMATTING_STYLE,
                Strictness.LENIENT,
                true,
                true,
                LongSerializationPolicy.DEFAULT,
                "2020-03-01",
                Integer.SIZE,
                Integer.SIZE,
                builderFactories,
                builderHierarchyFactories,
                factoriesToBeAdded,
                objectToNumberStrategy,
                numberToNumberStrategy,
                new ArrayList<>()))
            .toJsonTree("Src");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertTrue(actualToJsonTreeResult instanceof JsonArray);
    assertEquals(jsonArray, actualToJsonTreeResult);
  }

  /** Method under test: {@link Gson#toJsonTree(Object)} */
  @Test
  public void testToJsonTree13() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonObject jsonObject = new JsonObject();
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(jsonObject);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    // Act
    JsonElement actualToJsonTreeResult =
        (new Gson(
                Excluder.DEFAULT,
                fieldNamingStrategy,
                instanceCreators,
                true,
                true,
                true,
                true,
                Gson.DEFAULT_FORMATTING_STYLE,
                Strictness.LENIENT,
                true,
                true,
                LongSerializationPolicy.DEFAULT,
                "2020-03-01",
                Integer.SIZE,
                Integer.SIZE,
                builderFactories,
                builderHierarchyFactories,
                factoriesToBeAdded,
                objectToNumberStrategy,
                numberToNumberStrategy,
                new ArrayList<>()))
            .toJsonTree("Src");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertTrue(actualToJsonTreeResult instanceof JsonObject);
    assertEquals(jsonObject, actualToJsonTreeResult);
  }

  /** Method under test: {@link Gson#toJsonTree(Object)} */
  @Test
  public void testToJsonTree14() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonPrimitive jsonPrimitive = new JsonPrimitive("out == null");
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(jsonPrimitive);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    // Act
    JsonElement actualToJsonTreeResult =
        (new Gson(
                Excluder.DEFAULT,
                fieldNamingStrategy,
                instanceCreators,
                true,
                true,
                true,
                true,
                Gson.DEFAULT_FORMATTING_STYLE,
                Strictness.LENIENT,
                true,
                true,
                LongSerializationPolicy.DEFAULT,
                "2020-03-01",
                Integer.SIZE,
                Integer.SIZE,
                builderFactories,
                builderHierarchyFactories,
                factoriesToBeAdded,
                objectToNumberStrategy,
                numberToNumberStrategy,
                new ArrayList<>()))
            .toJsonTree("Src");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertTrue(actualToJsonTreeResult instanceof JsonPrimitive);
    assertEquals(jsonPrimitive, actualToJsonTreeResult);
  }

  /** Method under test: {@link Gson#toJsonTree(Object)} */
  @Test
  public void testToJsonTree15() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonPrimitive jsonPrimitive = new JsonPrimitive(true);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(jsonPrimitive);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    // Act
    JsonElement actualToJsonTreeResult =
        (new Gson(
                Excluder.DEFAULT,
                fieldNamingStrategy,
                instanceCreators,
                true,
                true,
                true,
                true,
                Gson.DEFAULT_FORMATTING_STYLE,
                Strictness.LENIENT,
                true,
                true,
                LongSerializationPolicy.DEFAULT,
                "2020-03-01",
                Integer.SIZE,
                Integer.SIZE,
                builderFactories,
                builderHierarchyFactories,
                factoriesToBeAdded,
                objectToNumberStrategy,
                numberToNumberStrategy,
                new ArrayList<>()))
            .toJsonTree("Src");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertTrue(actualToJsonTreeResult instanceof JsonPrimitive);
    assertEquals(jsonPrimitive, actualToJsonTreeResult);
  }

  /** Method under test: {@link Gson#toJsonTree(Object, Type)} */
  @Test
  public void testToJsonTree16() {
    // Arrange
    Gson gson = new Gson();
    Class<Object> typeOfSrc = Object.class;

    // Act
    JsonElement actualToJsonTreeResult = gson.toJsonTree("Src", typeOfSrc);

    // Assert
    assertTrue(actualToJsonTreeResult instanceof JsonPrimitive);
    Number asNumber = actualToJsonTreeResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("Src", actualToJsonTreeResult.getAsString());
    assertEquals("Src", asNumber.toString());
    assertEquals('S', actualToJsonTreeResult.getAsCharacter());
    assertFalse(actualToJsonTreeResult.getAsBoolean());
    assertFalse(actualToJsonTreeResult.isJsonArray());
    assertFalse(actualToJsonTreeResult.isJsonNull());
    assertFalse(actualToJsonTreeResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualToJsonTreeResult).isBoolean());
    assertFalse(((JsonPrimitive) actualToJsonTreeResult).isNumber());
    assertTrue(actualToJsonTreeResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualToJsonTreeResult).isString());
    assertSame(actualToJsonTreeResult, actualToJsonTreeResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link Gson#toJsonTree(Object, Type)} */
  @Test
  public void testToJsonTree17() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> typeOfSrc = Object.class;

    // Act
    JsonElement actualToJsonTreeResult = gson.toJsonTree("Src", typeOfSrc);

    // Assert
    assertTrue(actualToJsonTreeResult instanceof JsonPrimitive);
    Number asNumber = actualToJsonTreeResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("Src", actualToJsonTreeResult.getAsString());
    assertEquals("Src", asNumber.toString());
    assertEquals('S', actualToJsonTreeResult.getAsCharacter());
    assertFalse(actualToJsonTreeResult.getAsBoolean());
    assertFalse(actualToJsonTreeResult.isJsonArray());
    assertFalse(actualToJsonTreeResult.isJsonNull());
    assertFalse(actualToJsonTreeResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualToJsonTreeResult).isBoolean());
    assertFalse(((JsonPrimitive) actualToJsonTreeResult).isNumber());
    assertTrue(actualToJsonTreeResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualToJsonTreeResult).isString());
    assertSame(actualToJsonTreeResult, actualToJsonTreeResult.getAsJsonPrimitive());
  }

  /** Method under test: {@link Gson#toJsonTree(Object, Type)} */
  @Test
  public void testToJsonTree18() {
    // Arrange
    Gson gson = new Gson();
    Class<Object> typeOfSrc = Object.class;

    // Act
    JsonElement actualToJsonTreeResult = gson.toJsonTree(Excluder.DEFAULT, typeOfSrc);

    // Assert
    assertTrue(actualToJsonTreeResult instanceof JsonObject);
    assertEquals(6, ((JsonObject) actualToJsonTreeResult).size());
    assertFalse(actualToJsonTreeResult.isJsonArray());
    assertFalse(actualToJsonTreeResult.isJsonNull());
    assertFalse(actualToJsonTreeResult.isJsonPrimitive());
    assertFalse(((JsonObject) actualToJsonTreeResult).isEmpty());
    assertTrue(actualToJsonTreeResult.isJsonObject());
    assertSame(actualToJsonTreeResult, actualToJsonTreeResult.getAsJsonObject());
  }

  /** Method under test: {@link Gson#toJsonTree(Object, Type)} */
  @Test
  public void testToJsonTree19() {
    // Arrange
    Gson gson = new Gson();
    StringWriter stringWriter = new StringWriter();
    Class<Object> typeOfSrc = Object.class;

    // Act
    JsonElement actualToJsonTreeResult = gson.toJsonTree(stringWriter, typeOfSrc);

    // Assert
    assertTrue(actualToJsonTreeResult instanceof JsonObject);
    assertEquals(2, ((JsonObject) actualToJsonTreeResult).size());
    assertFalse(actualToJsonTreeResult.isJsonArray());
    assertFalse(actualToJsonTreeResult.isJsonNull());
    assertFalse(actualToJsonTreeResult.isJsonPrimitive());
    assertFalse(((JsonObject) actualToJsonTreeResult).isEmpty());
    assertTrue(actualToJsonTreeResult.isJsonObject());
    assertSame(actualToJsonTreeResult, actualToJsonTreeResult.getAsJsonObject());
  }

  /** Method under test: {@link Gson#toJsonTree(Object, Type)} */
  @Test
  public void testToJsonTree20() {
    // Arrange
    Gson gson = new Gson();
    Class<Object> typeOfSrc = Object.class;

    // Act
    JsonElement actualToJsonTreeResult = gson.toJsonTree(null, typeOfSrc);

    // Assert
    assertSame(((JsonNull) actualToJsonTreeResult).INSTANCE, actualToJsonTreeResult);
  }

  /** Method under test: {@link Gson#toJsonTree(Object, Type)} */
  @Test
  public void testToJsonTree21() {
    // Arrange
    Gson gson = new Gson();
    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    Class<Object> typeOfSrc = Object.class;

    // Act
    JsonElement actualToJsonTreeResult = gson.toJsonTree(futureTypeAdapter, typeOfSrc);

    // Assert
    assertTrue(actualToJsonTreeResult instanceof JsonObject);
    assertEquals(0, ((JsonObject) actualToJsonTreeResult).size());
    assertFalse(actualToJsonTreeResult.isJsonArray());
    assertFalse(actualToJsonTreeResult.isJsonNull());
    assertFalse(actualToJsonTreeResult.isJsonPrimitive());
    assertTrue(actualToJsonTreeResult.isJsonObject());
    assertTrue(((JsonObject) actualToJsonTreeResult).isEmpty());
    assertSame(actualToJsonTreeResult, actualToJsonTreeResult.getAsJsonObject());
  }

  /** Method under test: {@link Gson#toJsonTree(Object, Type)} */
  @Test
  public void testToJsonTree22() {
    // Arrange
    Gson gson = new Gson();
    HashMap<Object, Object> objectObjectMap = new HashMap<>();
    Class<Object> typeOfSrc = Object.class;

    // Act
    JsonElement actualToJsonTreeResult = gson.toJsonTree(objectObjectMap, typeOfSrc);

    // Assert
    assertTrue(actualToJsonTreeResult instanceof JsonObject);
    assertEquals(0, ((JsonObject) actualToJsonTreeResult).size());
    assertFalse(actualToJsonTreeResult.isJsonArray());
    assertFalse(actualToJsonTreeResult.isJsonNull());
    assertFalse(actualToJsonTreeResult.isJsonPrimitive());
    assertTrue(actualToJsonTreeResult.isJsonObject());
    assertTrue(((JsonObject) actualToJsonTreeResult).isEmpty());
    assertSame(actualToJsonTreeResult, actualToJsonTreeResult.getAsJsonObject());
  }

  /** Method under test: {@link Gson#toJsonTree(Object, Type)} */
  @Test
  public void testToJsonTree23() {
    // Arrange
    Gson gson = new Gson();
    BufferedWriter bufferedWriter = new BufferedWriter(new StringWriter(), Integer.SIZE);

    Class<Object> typeOfSrc = Object.class;

    // Act
    JsonElement actualToJsonTreeResult = gson.toJsonTree(bufferedWriter, typeOfSrc);

    // Assert
    assertTrue(actualToJsonTreeResult instanceof JsonObject);
    assertEquals(5, ((JsonObject) actualToJsonTreeResult).size());
    assertFalse(actualToJsonTreeResult.isJsonArray());
    assertFalse(actualToJsonTreeResult.isJsonNull());
    assertFalse(actualToJsonTreeResult.isJsonPrimitive());
    assertFalse(((JsonObject) actualToJsonTreeResult).isEmpty());
    assertTrue(actualToJsonTreeResult.isJsonObject());
    assertSame(actualToJsonTreeResult, actualToJsonTreeResult.getAsJsonObject());
  }

  /** Method under test: {@link Gson#toJsonTree(Object, Type)} */
  @Test
  public void testToJsonTree24() {
    // Arrange
    Gson gson = new Gson();
    GenericMetadataSupport.TypeVarBoundedType typeVarBoundedType =
        new GenericMetadataSupport.TypeVarBoundedType(null);
    Class<Object> typeOfSrc = Object.class;

    // Act
    JsonElement actualToJsonTreeResult = gson.toJsonTree(typeVarBoundedType, typeOfSrc);

    // Assert
    assertTrue(actualToJsonTreeResult instanceof JsonObject);
    assertEquals(0, ((JsonObject) actualToJsonTreeResult).size());
    assertFalse(actualToJsonTreeResult.isJsonArray());
    assertFalse(actualToJsonTreeResult.isJsonNull());
    assertFalse(actualToJsonTreeResult.isJsonPrimitive());
    assertTrue(actualToJsonTreeResult.isJsonObject());
    assertTrue(((JsonObject) actualToJsonTreeResult).isEmpty());
    assertSame(actualToJsonTreeResult, actualToJsonTreeResult.getAsJsonObject());
  }

  /** Method under test: {@link Gson#toJsonTree(Object, Type)} */
  @Test
  public void testToJsonTree25() {
    // Arrange
    Gson gson = new Gson();
    TreeMap<Object, Object> objectObjectMap = new TreeMap<>();
    Class<Object> typeOfSrc = Object.class;

    // Act
    JsonElement actualToJsonTreeResult = gson.toJsonTree(objectObjectMap, typeOfSrc);

    // Assert
    assertTrue(actualToJsonTreeResult instanceof JsonObject);
    assertEquals(0, ((JsonObject) actualToJsonTreeResult).size());
    assertFalse(actualToJsonTreeResult.isJsonArray());
    assertFalse(actualToJsonTreeResult.isJsonNull());
    assertFalse(actualToJsonTreeResult.isJsonPrimitive());
    assertTrue(actualToJsonTreeResult.isJsonObject());
    assertTrue(((JsonObject) actualToJsonTreeResult).isEmpty());
    assertSame(actualToJsonTreeResult, actualToJsonTreeResult.getAsJsonObject());
  }

  /** Method under test: {@link Gson#toJsonTree(Object, Type)} */
  @Test
  public void testToJsonTree26() {
    // Arrange
    Gson gson = new Gson();
    Class<TypeAdapterFactory> typeOfSrc = TypeAdapterFactory.class;

    // Act
    JsonElement actualToJsonTreeResult = gson.toJsonTree("Src", typeOfSrc);

    // Assert
    assertTrue(actualToJsonTreeResult instanceof JsonObject);
    assertEquals(0, ((JsonObject) actualToJsonTreeResult).size());
    assertFalse(actualToJsonTreeResult.isJsonArray());
    assertFalse(actualToJsonTreeResult.isJsonNull());
    assertFalse(actualToJsonTreeResult.isJsonPrimitive());
    assertTrue(actualToJsonTreeResult.isJsonObject());
    assertTrue(((JsonObject) actualToJsonTreeResult).isEmpty());
    assertSame(actualToJsonTreeResult, actualToJsonTreeResult.getAsJsonObject());
  }

  /** Method under test: {@link Gson#toJsonTree(Object, Type)} */
  @Test
  public void testToJsonTree27() {
    // Arrange
    Gson gson = new Gson();
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act
    JsonElement actualToJsonTreeResult = gson.toJsonTree(null, typeOfSrc);

    // Assert
    assertSame(((JsonNull) actualToJsonTreeResult).INSTANCE, actualToJsonTreeResult);
  }

  /** Method under test: {@link Gson#toJsonTree(Object, Type)} */
  @Test
  public void testToJsonTree28() {
    // Arrange
    Gson gson = new Gson();
    LazilyParsedNumber lazilyParsedNumber = new LazilyParsedNumber("42");
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act
    JsonElement actualToJsonTreeResult = gson.toJsonTree(lazilyParsedNumber, typeOfSrc);

    // Assert
    assertTrue(actualToJsonTreeResult instanceof JsonPrimitive);
    Number asNumber = actualToJsonTreeResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("42", actualToJsonTreeResult.getAsString());
    assertEquals("42", asNumber.toString());
    BigInteger asBigInteger = actualToJsonTreeResult.getAsBigInteger();
    assertEquals("42", asBigInteger.toString());
    assertEquals('4', actualToJsonTreeResult.getAsCharacter());
    assertEquals(1, asBigInteger.getLowestSetBit());
    assertEquals(1, asBigInteger.signum());
    assertEquals(42, actualToJsonTreeResult.getAsInt());
    assertEquals(42.0d, actualToJsonTreeResult.getAsDouble(), 0.0);
    assertEquals(42.0f, actualToJsonTreeResult.getAsFloat(), 0.0f);
    assertEquals(42L, actualToJsonTreeResult.getAsLong());
    assertEquals((short) 42, actualToJsonTreeResult.getAsShort());
    assertFalse(actualToJsonTreeResult.getAsBoolean());
    assertFalse(actualToJsonTreeResult.isJsonArray());
    assertFalse(actualToJsonTreeResult.isJsonNull());
    assertFalse(actualToJsonTreeResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualToJsonTreeResult).isBoolean());
    assertFalse(((JsonPrimitive) actualToJsonTreeResult).isString());
    assertTrue(actualToJsonTreeResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualToJsonTreeResult).isNumber());
    BigDecimal expectedAsBigDecimal = new BigDecimal("42");
    assertEquals(expectedAsBigDecimal, actualToJsonTreeResult.getAsBigDecimal());
    assertEquals('*', actualToJsonTreeResult.getAsByte());
    assertSame(lazilyParsedNumber, asNumber);
    assertSame(actualToJsonTreeResult, actualToJsonTreeResult.getAsJsonPrimitive());
    assertArrayEquals(new byte[] {'*'}, asBigInteger.toByteArray());
  }

  /** Method under test: {@link Gson#toJsonTree(Object, Type)} */
  @Test
  public void testToJsonTree29() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(JsonNull.INSTANCE);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson2 =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act
    JsonElement actualToJsonTreeResult = gson2.toJsonTree("Src", typeOfSrc);

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertSame(((JsonNull) actualToJsonTreeResult).INSTANCE, actualToJsonTreeResult);
  }

  /** Method under test: {@link Gson#toJsonTree(Object, Type)} */
  @Test
  public void testToJsonTree30() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonArray jsonArray = new JsonArray(3);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(jsonArray);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson2 =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act
    JsonElement actualToJsonTreeResult = gson2.toJsonTree("Src", typeOfSrc);

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertTrue(actualToJsonTreeResult instanceof JsonArray);
    assertEquals(jsonArray, actualToJsonTreeResult);
  }

  /** Method under test: {@link Gson#toJsonTree(Object, Type)} */
  @Test
  public void testToJsonTree31() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonObject jsonObject = new JsonObject();
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(jsonObject);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson2 =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act
    JsonElement actualToJsonTreeResult = gson2.toJsonTree("Src", typeOfSrc);

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertTrue(actualToJsonTreeResult instanceof JsonObject);
    assertEquals(jsonObject, actualToJsonTreeResult);
  }

  /** Method under test: {@link Gson#toJsonTree(Object, Type)} */
  @Test
  public void testToJsonTree32() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonPrimitive jsonPrimitive = new JsonPrimitive("out == null");
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(jsonPrimitive);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson2 =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act
    JsonElement actualToJsonTreeResult = gson2.toJsonTree("Src", typeOfSrc);

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertTrue(actualToJsonTreeResult instanceof JsonPrimitive);
    assertEquals(jsonPrimitive, actualToJsonTreeResult);
  }

  /** Method under test: {@link Gson#toJsonTree(Object, Type)} */
  @Test
  public void testToJsonTree33() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonPrimitive jsonPrimitive = new JsonPrimitive(true);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(jsonPrimitive);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson2 =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act
    JsonElement actualToJsonTreeResult = gson2.toJsonTree("Src", typeOfSrc);

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertTrue(actualToJsonTreeResult instanceof JsonPrimitive);
    assertEquals(jsonPrimitive, actualToJsonTreeResult);
  }

  /** Method under test: {@link Gson#toJsonTree(Object, Type)} */
  @Test
  public void testToJsonTree34() {
    // Arrange
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class), true));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson2 =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act
    JsonElement actualToJsonTreeResult = gson2.toJsonTree(null, typeOfSrc);

    // Assert
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertSame(((JsonNull) actualToJsonTreeResult).INSTANCE, actualToJsonTreeResult);
  }

  /** Method under test: {@link Gson#toJsonTree(Object, Type)} */
  @Test
  public void testToJsonTree35() {
    // Arrange
    new IllegalArgumentException("out == null");
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    Gson context = new Gson();
    Gson.FutureTypeAdapter<Object> componentTypeAdapter = new Gson.FutureTypeAdapter<>();
    Class<Object> componentType = Object.class;
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act
    JsonElement actualToJsonTreeResult = gson.toJsonTree(null, typeOfSrc);

    // Assert
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertSame(((JsonNull) actualToJsonTreeResult).INSTANCE, actualToJsonTreeResult);
  }
}
