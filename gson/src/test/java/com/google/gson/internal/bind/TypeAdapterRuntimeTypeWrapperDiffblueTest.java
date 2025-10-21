package com.google.gson.internal.bind;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilderDiffblueTestFactory;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonArray;
import com.google.gson.JsonArrayDiffblueTestFactory;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.Strictness;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.internal.sql.SqlTimestampTypeAdapterDiffblueTestFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class TypeAdapterRuntimeTypeWrapperDiffblueTest {
  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead() throws JsonParseException, IOException {
    // Arrange
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Object createSqlTimestampTypeAdapterResult =
        SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter();
    when(deserializer.deserialize(
            Mockito.<JsonElement>any(),
            Mockito.<Type>any(),
            Mockito.<JsonDeserializationContext>any()))
        .thenReturn(createSqlTimestampTypeAdapterResult);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    Gson context = new Gson();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());
    JsonTreeReader in =
        new JsonTreeReader(JsonArrayDiffblueTestFactory.createJsonArrayWithElements());

    // Act
    Object actualReadResult = typeAdapterRuntimeTypeWrapper.read(in);

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertEquals(0, in.getStackSize());
    assertFalse(in.hasNext());
    assertSame(createSqlTimestampTypeAdapterResult, actualReadResult);
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements.
   *   <li>Then return {@code 42} size is two.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_givenCreateJsonArrayWithElements_thenReturn42SizeIsTwo() throws IOException {
    // Arrange
    Gson context = new Gson();
    ObjectTypeAdapter delegate = ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());

    JsonObject element = new JsonObject();
    element.add("42", JsonArrayDiffblueTestFactory.createJsonArrayWithElements());

    // Act
    Object actualReadResult = typeAdapterRuntimeTypeWrapper.read(new JsonTreeReader(element));

    // Assert
    assertTrue(actualReadResult instanceof Map);
    assertEquals(1, ((Map<String, ArrayList>) actualReadResult).size());
    ArrayList getResult = ((Map<String, ArrayList>) actualReadResult).get("42");
    assertEquals(2, getResult.size());
    assertEquals("element0", getResult.get(0));
    assertEquals("element1", getResult.get(1));
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@link JsonNull#INSTANCE}.
   *   <li>When {@link JsonObject} (default constructor) add {@code 42} and {@link
   *       JsonNull#INSTANCE}.
   *   <li>Then return {@code 42} is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_givenInstance_whenJsonObjectAdd42AndInstance_thenReturn42IsNull()
      throws IOException {
    // Arrange
    Gson context = new Gson();
    ObjectTypeAdapter delegate = ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());

    JsonObject element = new JsonObject();
    element.add("42", JsonNull.INSTANCE);
    JsonTreeReader in = new JsonTreeReader(element);

    // Act
    Object actualReadResult = typeAdapterRuntimeTypeWrapper.read(in);

    // Assert
    assertTrue(actualReadResult instanceof Map);
    assertEquals(1, ((Map<String, Object>) actualReadResult).size());
    assertNull(((Map<String, Object>) actualReadResult).get("42"));
    assertEquals(0, in.getStackSize());
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor).
   *   <li>When {@link JsonObject} (default constructor) add {@code 42} and {@link JsonObject}
   *       (default constructor).
   *   <li>Then return {@code 42} Empty.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_givenJsonObject_whenJsonObjectAdd42AndJsonObject_thenReturn42Empty()
      throws IOException {
    // Arrange
    Gson context = new Gson();
    ObjectTypeAdapter delegate = ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());

    JsonObject element = new JsonObject();
    element.add("42", new JsonObject());

    // Act
    Object actualReadResult = typeAdapterRuntimeTypeWrapper.read(new JsonTreeReader(element));

    // Assert
    assertTrue(actualReadResult instanceof Map);
    assertEquals(1, ((Map<String, LinkedTreeMap>) actualReadResult).size());
    assertTrue(((Map<String, LinkedTreeMap>) actualReadResult).get("42").isEmpty());
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link JsonReader#JsonReader(Reader)} with in is {@link
   *       StringReader#StringReader(String)} Strictness is {@code LENIENT}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_givenLenient_whenJsonReaderWithInIsStringReaderStrictnessIsLenient()
      throws JsonParseException, IOException {
    // Arrange
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Object createSqlTimestampTypeAdapterResult =
        SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter();
    when(deserializer.deserialize(
            Mockito.<JsonElement>any(),
            Mockito.<Type>any(),
            Mockito.<JsonDeserializationContext>any()))
        .thenReturn(createSqlTimestampTypeAdapterResult);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    Gson context = new Gson();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());
    StringReader in = new StringReader(Boolean.FALSE.toString());

    JsonReader in2 = new JsonReader(in);
    in2.setStrictness(Strictness.LENIENT);

    // Act
    Object actualReadResult = typeAdapterRuntimeTypeWrapper.read(in2);

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertSame(createSqlTimestampTypeAdapterResult, actualReadResult);
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link JsonReader#JsonReader(Reader)} with in is {@link
   *       StringReader#StringReader(String)} Strictness is {@code LENIENT}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_givenLenient_whenJsonReaderWithInIsStringReaderStrictnessIsLenient2()
      throws JsonParseException, IOException {
    // Arrange
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Object createSqlTimestampTypeAdapterResult =
        SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter();
    when(deserializer.deserialize(
            Mockito.<JsonElement>any(),
            Mockito.<Type>any(),
            Mockito.<JsonDeserializationContext>any()))
        .thenReturn(createSqlTimestampTypeAdapterResult);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    Gson context = new Gson();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());

    JsonReader in = new JsonReader(new StringReader("42"));
    in.setStrictness(Strictness.LENIENT);

    // Act
    Object actualReadResult = typeAdapterRuntimeTypeWrapper.read(in);

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertSame(createSqlTimestampTypeAdapterResult, actualReadResult);
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_givenLenient_whenStringReaderWithEmptyString_thenReturnNull()
      throws IOException {
    // Arrange
    Gson context = new Gson();
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());

    JsonReader in = new JsonReader(new StringReader(""));
    in.setStrictness(Strictness.LENIENT);

    // Act
    Object actualReadResult = typeAdapterRuntimeTypeWrapper.read(in);

    // Assert
    assertNull(actualReadResult);
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code STRICT}.
   *   <li>When {@link JsonReader#JsonReader(Reader)} with in is {@link
   *       StringReader#StringReader(String)} Strictness is {@code STRICT}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_givenStrict_whenJsonReaderWithInIsStringReaderStrictnessIsStrict()
      throws JsonParseException, IOException {
    // Arrange
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Object createSqlTimestampTypeAdapterResult =
        SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter();
    when(deserializer.deserialize(
            Mockito.<JsonElement>any(),
            Mockito.<Type>any(),
            Mockito.<JsonDeserializationContext>any()))
        .thenReturn(createSqlTimestampTypeAdapterResult);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    Gson context = new Gson();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());
    StringReader in = new StringReader(Boolean.FALSE.toString());

    JsonReader in2 = new JsonReader(in);
    in2.setStrictness(Strictness.STRICT);

    // Act
    Object actualReadResult = typeAdapterRuntimeTypeWrapper.read(in2);

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertSame(createSqlTimestampTypeAdapterResult, actualReadResult);
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code true}.
   *   <li>When {@link JsonObject} (default constructor) addProperty {@code 42} and {@code true}.
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_givenTrue_whenJsonObjectAddProperty42AndTrue_thenReturn42()
      throws IOException {
    // Arrange
    Gson context = new Gson();
    ObjectTypeAdapter delegate = ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());

    JsonObject element = new JsonObject();
    element.addProperty("42", true);
    JsonTreeReader in = new JsonTreeReader(element);

    // Act
    Object actualReadResult = typeAdapterRuntimeTypeWrapper.read(in);

    // Assert
    assertTrue(actualReadResult instanceof Map);
    assertEquals(0, in.getStackSize());
    assertEquals(1, ((Map<String, Boolean>) actualReadResult).size());
    assertFalse(in.hasNext());
    assertTrue(((Map<String, Boolean>) actualReadResult).get("42"));
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given valueOf one.
   *   <li>Then return {@code 42} longValue is one.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_givenValueOfOne_thenReturn42LongValueIsOne() throws IOException {
    // Arrange
    Gson context = new Gson();
    ObjectTypeAdapter delegate = ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());

    JsonObject element = new JsonObject();
    element.addProperty("42", Integer.valueOf(1));
    JsonTreeReader in = new JsonTreeReader(element);

    // Act
    Object actualReadResult = typeAdapterRuntimeTypeWrapper.read(in);

    // Assert
    assertTrue(actualReadResult instanceof Map);
    assertEquals(0, in.getStackSize());
    assertEquals(1, ((Map<String, Long>) actualReadResult).size());
    assertEquals(1L, ((Map<String, Long>) actualReadResult).get("42").longValue());
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>Then {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE} StackSize is zero.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_thenJsonTreeReaderWithElementIsInstanceStackSizeIsZero() throws IOException {
    // Arrange
    Gson context = new Gson();
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());
    JsonTreeReader in = new JsonTreeReader(JsonNull.INSTANCE);

    // Act
    Object actualReadResult = typeAdapterRuntimeTypeWrapper.read(in);

    // Assert
    assertNull(actualReadResult);
    assertEquals(0, in.getStackSize());
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>Then {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE} StackSize is zero.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_thenJsonTreeReaderWithElementIsInstanceStackSizeIsZero2()
      throws IOException {
    // Arrange
    Gson context = new Gson();
    ObjectTypeAdapter delegate = ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());
    JsonTreeReader in = new JsonTreeReader(JsonNull.INSTANCE);

    // Act
    Object actualReadResult = typeAdapterRuntimeTypeWrapper.read(in);

    // Assert
    assertNull(actualReadResult);
    assertEquals(0, in.getStackSize());
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>Then {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE} StackSize is zero.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_thenJsonTreeReaderWithElementIsInstanceStackSizeIsZero3()
      throws IOException {
    // Arrange
    Gson context = new Gson();
    Gson context2 = new Gson();
    ObjectTypeAdapter componentTypeAdapter =
        ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();
    Class<Object> componentType = Object.class;

    ArrayTypeAdapter<Object> delegate =
        new ArrayTypeAdapter<>(context2, componentTypeAdapter, componentType);

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());
    JsonTreeReader in = new JsonTreeReader(JsonNull.INSTANCE);

    // Act
    Object actualReadResult = typeAdapterRuntimeTypeWrapper.read(in);

    // Assert
    assertNull(actualReadResult);
    assertEquals(0, in.getStackSize());
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>Then {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonObject} (default constructor) StackSize is zero.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_thenJsonTreeReaderWithElementIsJsonObjectStackSizeIsZero()
      throws JsonParseException, IOException {
    // Arrange
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Object createSqlTimestampTypeAdapterResult =
        SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter();
    when(deserializer.deserialize(
            Mockito.<JsonElement>any(),
            Mockito.<Type>any(),
            Mockito.<JsonDeserializationContext>any()))
        .thenReturn(createSqlTimestampTypeAdapterResult);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    Gson context = new Gson();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());
    JsonTreeReader in = new JsonTreeReader(new JsonObject());

    // Act
    Object actualReadResult = typeAdapterRuntimeTypeWrapper.read(in);

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertEquals(0, in.getStackSize());
    assertFalse(in.hasNext());
    assertSame(createSqlTimestampTypeAdapterResult, actualReadResult);
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>Then {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonPrimitive#JsonPrimitive(Boolean)} StackSize is zero.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_thenJsonTreeReaderWithElementIsJsonPrimitiveStackSizeIsZero()
      throws JsonParseException, IOException {
    // Arrange
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Object createSqlTimestampTypeAdapterResult =
        SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter();
    when(deserializer.deserialize(
            Mockito.<JsonElement>any(),
            Mockito.<Type>any(),
            Mockito.<JsonDeserializationContext>any()))
        .thenReturn(createSqlTimestampTypeAdapterResult);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    Gson context = new Gson();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());
    JsonTreeReader in = new JsonTreeReader(new JsonPrimitive(true));

    // Act
    Object actualReadResult = typeAdapterRuntimeTypeWrapper.read(in);

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertEquals(0, in.getStackSize());
    assertFalse(in.hasNext());
    assertSame(createSqlTimestampTypeAdapterResult, actualReadResult);
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>Then return {@code 42} doubleValue is one.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_thenReturn42DoubleValueIsOne() throws IOException {
    // Arrange
    Gson context = new Gson();
    ObjectTypeAdapter delegate = ObjectTypeAdapterDiffblueTestFactory.createDoubleAdapter();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());

    JsonObject element = new JsonObject();
    element.addProperty("42", Integer.valueOf(1));
    JsonTreeReader in = new JsonTreeReader(element);

    // Act
    Object actualReadResult = typeAdapterRuntimeTypeWrapper.read(in);

    // Assert
    assertTrue(actualReadResult instanceof Map);
    assertEquals(0, in.getStackSize());
    assertEquals(1, ((Map<String, Double>) actualReadResult).size());
    assertEquals(1.0d, ((Map<String, Double>) actualReadResult).get("42").doubleValue(), 0.0);
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>Then return doubleValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_thenReturnDoubleValueIsFortyTwo() throws IOException {
    // Arrange
    Gson context = new Gson();
    ObjectTypeAdapter delegate = ObjectTypeAdapterDiffblueTestFactory.createDoubleAdapter();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());

    // Act
    Object actualReadResult =
        typeAdapterRuntimeTypeWrapper.read(new JsonReader(new StringReader("42")));

    // Assert
    assertEquals(42.0d, ((Double) actualReadResult).doubleValue(), 0.0);
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>Then return {@link List}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_thenReturnList() throws IOException {
    // Arrange
    Gson context = new Gson();
    ObjectTypeAdapter delegate = ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());

    // Act
    Object actualReadResult =
        typeAdapterRuntimeTypeWrapper.read(
            new JsonTreeReader(JsonArrayDiffblueTestFactory.createJsonArrayWithElements()));

    // Assert
    assertTrue(actualReadResult instanceof List);
    assertEquals(2, ((List<String>) actualReadResult).size());
    assertEquals("element0", ((List<String>) actualReadResult).get(0));
    assertEquals("element1", ((List<String>) actualReadResult).get(1));
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>Then return start of heading acknowledge start of heading acknowledge.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_thenReturnStartOfHeadingAcknowledgeStartOfHeadingAcknowledge()
      throws IOException {
    // Arrange
    Gson context = new Gson();
    ObjectTypeAdapter delegate = ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());
    CharArrayReader in = new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray());

    JsonReader in2 = new JsonReader(in);
    in2.setStrictness(Strictness.LENIENT);

    // Act
    Object actualReadResult = typeAdapterRuntimeTypeWrapper.read(in2);

    // Assert
    assertEquals("\u0001\u0006\u0001\u0006", actualReadResult);
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_whenJsonPrimitiveWithBoolIsTrue_thenReturnTrue() throws IOException {
    // Arrange
    Gson context = new Gson();
    ObjectTypeAdapter delegate = ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());
    JsonTreeReader in = new JsonTreeReader(new JsonPrimitive(true));

    // Act
    Object actualReadResult = typeAdapterRuntimeTypeWrapper.read(in);

    // Assert
    assertEquals(0, in.getStackSize());
    assertFalse(in.hasNext());
    assertTrue((Boolean) actualReadResult);
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonPrimitive#JsonPrimitive(String)} with {@code String}.
   *   <li>Then return {@code String}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_whenJsonPrimitiveWithString_thenReturnString() throws IOException {
    // Arrange
    Gson context = new Gson();
    ObjectTypeAdapter delegate = ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());
    JsonTreeReader in = new JsonTreeReader(new JsonPrimitive("String"));

    // Act
    Object actualReadResult = typeAdapterRuntimeTypeWrapper.read(in);

    // Assert
    assertEquals("String", actualReadResult);
    assertEquals(0, in.getStackSize());
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonObject} (default constructor) Strictness is {@code LENIENT}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_whenJsonTreeReaderWithElementIsJsonObjectStrictnessIsLenient()
      throws IOException {
    // Arrange
    Gson context = new Gson();
    ObjectTypeAdapter delegate = ObjectTypeAdapterDiffblueTestFactory.createDoubleAdapter();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());

    JsonObject element = new JsonObject();
    element.addProperty("42", Integer.valueOf(1));

    JsonTreeReader in = new JsonTreeReader(element);
    in.setStrictness(Strictness.LENIENT);

    // Act
    Object actualReadResult = typeAdapterRuntimeTypeWrapper.read(in);

    // Assert
    assertTrue(actualReadResult instanceof Map);
    assertEquals(0, in.getStackSize());
    assertEquals(1, ((Map<String, Double>) actualReadResult).size());
    assertEquals(1.0d, ((Map<String, Double>) actualReadResult).get("42").doubleValue(), 0.0);
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonObject} (default constructor).
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_whenJsonTreeReaderWithElementIsJsonObject_thenReturnEmpty()
      throws IOException {
    // Arrange
    Gson context = new Gson();
    ObjectTypeAdapter delegate = ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());
    JsonTreeReader in = new JsonTreeReader(new JsonObject());

    // Act
    Object actualReadResult = typeAdapterRuntimeTypeWrapper.read(in);

    // Assert
    assertTrue(actualReadResult instanceof Map);
    assertEquals(0, in.getStackSize());
    assertFalse(in.hasNext());
    assertTrue(((Map<Object, Object>) actualReadResult).isEmpty());
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then return createSqlTimestampTypeAdapter.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_whenStringReaderWith42_thenReturnCreateSqlTimestampTypeAdapter()
      throws JsonParseException, IOException {
    // Arrange
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Object createSqlTimestampTypeAdapterResult =
        SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter();
    when(deserializer.deserialize(
            Mockito.<JsonElement>any(),
            Mockito.<Type>any(),
            Mockito.<JsonDeserializationContext>any()))
        .thenReturn(createSqlTimestampTypeAdapterResult);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    Gson context = new Gson();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());

    // Act
    Object actualReadResult =
        typeAdapterRuntimeTypeWrapper.read(new JsonReader(new StringReader("42")));

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertSame(createSqlTimestampTypeAdapterResult, actualReadResult);
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then return longValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_whenStringReaderWith42_thenReturnLongValueIsFortyTwo() throws IOException {
    // Arrange
    Gson context = new Gson();
    ObjectTypeAdapter delegate = ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());

    // Act
    Object actualReadResult =
        typeAdapterRuntimeTypeWrapper.read(new JsonReader(new StringReader("42")));

    // Assert
    assertEquals(42L, ((Long) actualReadResult).longValue());
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_whenStringReaderWithEmptyString_thenReturnNull() throws IOException {
    // Arrange
    Gson context = new Gson();
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());

    // Act
    Object actualReadResult =
        typeAdapterRuntimeTypeWrapper.read(new JsonReader(new StringReader("")));

    // Assert
    assertNull(actualReadResult);
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_whenStringReaderWithFalseToString() throws JsonParseException, IOException {
    // Arrange
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Object createSqlTimestampTypeAdapterResult =
        SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter();
    when(deserializer.deserialize(
            Mockito.<JsonElement>any(),
            Mockito.<Type>any(),
            Mockito.<JsonDeserializationContext>any()))
        .thenReturn(createSqlTimestampTypeAdapterResult);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    Gson context = new Gson();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());
    StringReader in = new StringReader(Boolean.FALSE.toString());

    // Act
    Object actualReadResult = typeAdapterRuntimeTypeWrapper.read(new JsonReader(in));

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertSame(createSqlTimestampTypeAdapterResult, actualReadResult);
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_whenStringReaderWithFalseToString_thenReturnFalse() throws IOException {
    // Arrange
    Gson context = new Gson();
    ObjectTypeAdapter delegate = ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());
    StringReader in = new StringReader(Boolean.FALSE.toString());

    // Act
    Object actualReadResult = typeAdapterRuntimeTypeWrapper.read(new JsonReader(in));

    // Assert
    assertFalse((Boolean) actualReadResult);
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code FALSE}.
   *   <li>Then return createSqlTimestampTypeAdapter.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_whenStringReaderWithFalse_thenReturnCreateSqlTimestampTypeAdapter()
      throws JsonParseException, IOException {
    // Arrange
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Object createSqlTimestampTypeAdapterResult =
        SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter();
    when(deserializer.deserialize(
            Mockito.<JsonElement>any(),
            Mockito.<Type>any(),
            Mockito.<JsonDeserializationContext>any()))
        .thenReturn(createSqlTimestampTypeAdapterResult);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    Gson context = new Gson();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());

    // Act
    Object actualReadResult =
        typeAdapterRuntimeTypeWrapper.read(new JsonReader(new StringReader("FALSE")));

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertSame(createSqlTimestampTypeAdapterResult, actualReadResult);
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite() throws IOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(JsonArrayDiffblueTestFactory.createJsonArrayWithElements());
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    Gson context = new Gson();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    typeAdapterRuntimeTypeWrapper.write(
        out, SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite2() throws IOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(JsonArrayDiffblueTestFactory.createJsonArrayWithElements());
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    Gson context = new Gson();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());
    JsonWriter out = new JsonWriter(new StringWriter());
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();

    // Act
    typeAdapterRuntimeTypeWrapper.write(
        out,
        new CollectionTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>())));

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite3() throws IOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(JsonArrayDiffblueTestFactory.createJsonArrayWithElements());
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    Gson context = new Gson();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());
    JsonWriter out = new JsonWriter(new StringWriter());
    Gson context2 = new Gson();
    ObjectTypeAdapter componentTypeAdapter =
        ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();
    Class<Object> componentType = Object.class;

    ArrayTypeAdapter<Object> arrayTypeAdapter =
        new ArrayTypeAdapter<>(context2, componentTypeAdapter, componentType);

    // Act
    typeAdapterRuntimeTypeWrapper.write(out, arrayTypeAdapter);

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite4() throws IOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(JsonArrayDiffblueTestFactory.createJsonArrayWithElements());
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    Gson context = new Gson();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());
    JsonWriter out = new JsonWriter(new StringWriter());
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();

    // Act
    typeAdapterRuntimeTypeWrapper.write(
        out,
        new JsonAdapterAnnotationTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>())));

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite5() throws IOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(JsonArrayDiffblueTestFactory.createJsonArrayWithElements());
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    Gson context = new Gson();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());
    JsonWriter out = new JsonWriter(new StringWriter());
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();

    // Act
    typeAdapterRuntimeTypeWrapper.write(
        out,
        new MapTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>()), true));

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add createJsonArrayWithElements.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_givenCreateJsonArrayWithElementsAddCreateJsonArrayWithElements()
      throws IOException {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements());

    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(createJsonArrayWithElementsResult);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    Gson context = new Gson();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    typeAdapterRuntimeTypeWrapper.write(
        out, SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add {@code false}.
   *   <li>Then calls {@link JsonSerializer#serialize(Object, Type, JsonSerializationContext)}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_givenCreateJsonArrayWithElementsAddFalse_thenCallsSerialize()
      throws IOException {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(false);
    createJsonArrayWithElementsResult.add(true);

    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(createJsonArrayWithElementsResult);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    Gson context = new Gson();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    typeAdapterRuntimeTypeWrapper.write(
        out, SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add {@link JsonNull#INSTANCE}.
   *   <li>Then calls {@link JsonSerializer#serialize(Object, Type, JsonSerializationContext)}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_givenCreateJsonArrayWithElementsAddInstance_thenCallsSerialize()
      throws IOException {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(JsonNull.INSTANCE);

    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(createJsonArrayWithElementsResult);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    Gson context = new Gson();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    typeAdapterRuntimeTypeWrapper.write(
        out, SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add start of heading.
   *   <li>Then calls {@link JsonSerializer#serialize(Object, Type, JsonSerializationContext)}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_givenCreateJsonArrayWithElementsAddStartOfHeading_thenCallsSerialize()
      throws IOException {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add('\u0001');

    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(createJsonArrayWithElementsResult);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    Gson context = new Gson();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    typeAdapterRuntimeTypeWrapper.write(
        out, SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add {@code true}.
   *   <li>Then calls {@link JsonSerializer#serialize(Object, Type, JsonSerializationContext)}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_givenCreateJsonArrayWithElementsAddTrue_thenCallsSerialize()
      throws IOException {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(true);

    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(createJsonArrayWithElementsResult);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    Gson context = new Gson();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    typeAdapterRuntimeTypeWrapper.write(
        out, SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add valueOf one.
   *   <li>Then calls {@link JsonSerializer#serialize(Object, Type, JsonSerializationContext)}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_givenCreateJsonArrayWithElementsAddValueOfOne_thenCallsSerialize()
      throws IOException {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(Integer.valueOf(1));

    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(createJsonArrayWithElementsResult);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    Gson context = new Gson();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    typeAdapterRuntimeTypeWrapper.write(
        out, SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonSerializer} {@link JsonSerializer#serialize(Object, Type,
   *       JsonSerializationContext)} return {@link JsonNull#INSTANCE}.
   *   <li>Then calls {@link JsonSerializer#serialize(Object, Type, JsonSerializationContext)}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_givenJsonSerializerSerializeReturnInstance_thenCallsSerialize()
      throws IOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(JsonNull.INSTANCE);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    Gson context = new Gson();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    typeAdapterRuntimeTypeWrapper.write(
        out, SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonSerializer} {@link JsonSerializer#serialize(Object, Type,
   *       JsonSerializationContext)} return {@link JsonNull#INSTANCE}.
   *   <li>Then {@link JsonTreeWriter} (default constructor) {@link JsonNull}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_givenJsonSerializerSerializeReturnInstance_thenJsonTreeWriterJsonNull()
      throws IOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(JsonNull.INSTANCE);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class), true);
    Gson context = new Gson();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    typeAdapterRuntimeTypeWrapper.write(
        out, SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter());

    // Assert that nothing has changed
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonNull);
    assertFalse(getResult.isJsonArray());
    assertFalse(getResult.isJsonObject());
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonSerializer} {@link JsonSerializer#serialize(Object, Type,
   *       JsonSerializationContext)} return {@link JsonObject} (default constructor).
   *   <li>Then calls {@link JsonSerializer#serialize(Object, Type, JsonSerializationContext)}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_givenJsonSerializerSerializeReturnJsonObject_thenCallsSerialize()
      throws IOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonObject());
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    Gson context = new Gson();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    typeAdapterRuntimeTypeWrapper.write(
        out, SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@code true}.
   *   <li>When {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()} HtmlSafe is {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_givenTrue_whenJsonWriterWithOutIsStringWriterHtmlSafeIsTrue()
      throws IOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(JsonArrayDiffblueTestFactory.createJsonArrayWithElements());
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    Gson context = new Gson();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());

    JsonWriter out = new JsonWriter(new StringWriter());
    out.setHtmlSafe(true);

    // Act
    typeAdapterRuntimeTypeWrapper.write(
        out, SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Then {@link JsonTreeWriter} (default constructor) is createJsonArrayWithElements.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_thenJsonTreeWriterIsCreateJsonArrayWithElements() throws IOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(createJsonArrayWithElementsResult);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class), true);
    Gson context = new Gson();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    typeAdapterRuntimeTypeWrapper.write(
        out, SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonArray);
    assertEquals(createJsonArrayWithElementsResult, getResult);
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Then {@link JsonTreeWriter} (default constructor) is {@link JsonObject} (default
   *       constructor).
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_thenJsonTreeWriterIsJsonObject() throws IOException {
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

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class), true);
    Gson context = new Gson();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    typeAdapterRuntimeTypeWrapper.write(
        out, SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonObject);
    assertEquals(jsonObject, getResult);
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Then {@link JsonTreeWriter} (default constructor) {@link JsonNull}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_thenJsonTreeWriterJsonNull() throws IOException {
    // Arrange
    Gson context = new Gson();
    ObjectTypeAdapter delegate = ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    typeAdapterRuntimeTypeWrapper.write(out, null);

    // Assert that nothing has changed
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonNull);
    assertFalse(getResult.isJsonArray());
    assertFalse(getResult.isJsonObject());
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Then {@link JsonTreeWriter} (default constructor) size is one.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_thenJsonTreeWriterSizeIsOne() throws IOException {
    // Arrange
    Gson context = new Gson();
    ObjectTypeAdapter delegate =
        ObjectTypeAdapterDiffblueTestFactory.createLazilyParsedNumberAdapter();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    typeAdapterRuntimeTypeWrapper.write(
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
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@link JsonTreeWriter} (default constructor) {@link JsonNull}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_whenNull_thenJsonTreeWriterJsonNull() throws IOException {
    // Arrange
    Gson context = new Gson();
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class), true);

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    typeAdapterRuntimeTypeWrapper.write(out, null);

    // Assert that nothing has changed
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonNull);
    assertFalse(getResult.isJsonArray());
    assertFalse(getResult.isJsonObject());
  }
}
