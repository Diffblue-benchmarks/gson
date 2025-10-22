package com.google.gson.internal.bind;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doThrow;
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
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.sql.SqlTimestampTypeAdapterDiffblueTestFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
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
   *   <li>Then calls {@link JsonTreeReader#nextJsonElement()}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_givenCreateJsonArrayWithElements_thenCallsNextJsonElement()
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

    JsonTreeReader in = mock(JsonTreeReader.class);
    when(in.nextJsonElement())
        .thenReturn(JsonArrayDiffblueTestFactory.createJsonArrayWithElements());
    when(in.peek()).thenReturn(JsonToken.BEGIN_ARRAY);

    // Act
    Object actualReadResult = typeAdapterRuntimeTypeWrapper.read(in);

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    verify(in).nextJsonElement();
    verify(in).peek();
    assertSame(createSqlTimestampTypeAdapterResult, actualReadResult);
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>Then calls {@link ArrayTypeAdapter#read(JsonReader)}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_thenCallsRead() throws IOException {
    // Arrange
    ArrayTypeAdapter<Object> delegate = mock(ArrayTypeAdapter.class);
    Object createSqlTimestampTypeAdapterResult =
        SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter();
    when(delegate.read(Mockito.<JsonReader>any())).thenReturn(createSqlTimestampTypeAdapterResult);
    Gson context = new Gson();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());

    // Act
    Object actualReadResult = typeAdapterRuntimeTypeWrapper.read(mock(JsonTreeReader.class));

    // Assert
    verify(delegate).read(isA(JsonReader.class));
    assertSame(createSqlTimestampTypeAdapterResult, actualReadResult);
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>Then return a string.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_thenReturnAString() throws IOException {
    // Arrange
    Gson context = new Gson();
    ObjectTypeAdapter delegate = ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());

    // Act
    Object actualReadResult =
        typeAdapterRuntimeTypeWrapper.read(
            new JsonReader(
                new StringReader(
                    "\"This is a test string for the java.io.StringReader method. It includes"
                        + " various characters such as numbers 123, special characters @#$%, and"
                        + " spaces.\"")));

    // Assert
    assertEquals(
        "This is a test string for the java.io.StringReader method. It includes various characters"
            + " such as numbers 123, special characters @#$%, and spaces.",
        actualReadResult);
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonTreeReader} {@link JsonTreeReader#beginArray()} throw {@link
   *       IOException#IOException()}.
   *   <li>Then throw {@link IOException}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_whenJsonTreeReaderBeginArrayThrowIOException_thenThrowIOException()
      throws IOException {
    // Arrange
    Gson context = new Gson();
    ObjectTypeAdapter delegate = ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, GsonBuilderDiffblueTestFactory.createType());

    JsonTreeReader in = mock(JsonTreeReader.class);
    doThrow(new IOException()).when(in).beginArray();
    when(in.peek()).thenReturn(JsonToken.BEGIN_ARRAY);

    // Act and Assert
    assertThrows(IOException.class, () -> typeAdapterRuntimeTypeWrapper.read(in));
    verify(in).beginArray();
    verify(in).peek();
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with a string.
   *   <li>Then calls {@link JsonDeserializer#deserialize(JsonElement, Type,
   *       JsonDeserializationContext)}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_whenStringReaderWithAString_thenCallsDeserialize()
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
        typeAdapterRuntimeTypeWrapper.read(
            new JsonReader(
                new StringReader(
                    "\"This is a test string for the java.io.StringReader method. It includes"
                        + " various characters such as numbers 123, special characters @#$%, and"
                        + " spaces.\"")));

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
   *   <li>Given createJsonArrayWithElements add {@code "Test string for JsonArray add method"}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_givenCreateJsonArrayWithElementsAddTestStringForJsonArrayAddMethod()
      throws IOException {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add("\"Test string for JsonArray add method\"");

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
   *   <li>Then {@link JsonTreeWriter} (default constructor) {@link JsonObject}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_thenJsonTreeWriterJsonObject() throws IOException {
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
    assertFalse(getResult.isJsonNull());
    assertFalse(((JsonObject) getResult).isEmpty());
    assertTrue(getResult.isJsonObject());
    assertSame(getResult, getResult.getAsJsonObject());
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>When createSqlTimestampTypeAdapter.
   *   <li>Then calls {@link JsonSerializer#serialize(Object, Type, JsonSerializationContext)}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_whenCreateSqlTimestampTypeAdapter_thenCallsSerialize() throws IOException {
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
   * <ul>
   *   <li>When {@link JsonTreeWriter} (default constructor).
   *   <li>Then {@link JsonTreeWriter} (default constructor) {@link JsonNull}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_whenJsonTreeWriter_thenJsonTreeWriterJsonNull() throws IOException {
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
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    typeAdapterRuntimeTypeWrapper.write(out, null);

    // Assert that nothing has changed
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonNull);
    assertFalse(getResult.isJsonObject());
    assertTrue(getResult.isJsonNull());
  }
}
