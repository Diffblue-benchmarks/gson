package com.google.gson.internal.bind;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.Gson;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.GenericMetadataSupport;
import org.mockito.internal.util.reflection.GenericMetadataSupport.TypeVarBoundedType;

public class TypeAdapterRuntimeTypeWrapperDiffblueTest {
  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@link ObjectTypeAdapter} {@link ObjectTypeAdapter#read(JsonReader)} return {@code
   *       Read}.
   *   <li>Then return {@code Read}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_givenObjectTypeAdapterReadReturnRead_thenReturnRead() throws IOException {
    // Arrange
    ObjectTypeAdapter delegate = mock(ObjectTypeAdapter.class);
    when(delegate.read(Mockito.<JsonReader>any())).thenReturn("Read");
    Gson context = new Gson();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));

    // Act
    Object actualReadResult =
        typeAdapterRuntimeTypeWrapper.read(new JsonReader(new StringReader("foo")));

    // Assert
    verify(delegate).read(isA(JsonReader.class));
    assertEquals("Read", actualReadResult);
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
    ObjectTypeAdapter delegate = mock(ObjectTypeAdapter.class);
    doNothing().when(delegate).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());
    Gson context = new Gson();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    typeAdapterRuntimeTypeWrapper.write(out, null);

    // Assert that nothing has changed
    verify(delegate).write(isA(JsonWriter.class), (Object) isNull());
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonNull);
    assertFalse(getResult.isJsonArray());
    assertFalse(getResult.isJsonObject());
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
    ObjectTypeAdapter delegate = mock(ObjectTypeAdapter.class);
    doNothing().when(delegate).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());
    Gson context = new Gson();
    Class<Object> type = Object.class;

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, type);
    JsonTreeWriter out = new JsonTreeWriter();
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();

    // Act
    typeAdapterRuntimeTypeWrapper.write(
        out,
        new CollectionTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>())));

    // Assert that nothing has changed
    verify(delegate).write(isA(JsonWriter.class), isA(Object.class));
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonNull);
    assertFalse(getResult.isJsonArray());
    assertFalse(getResult.isJsonObject());
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
    ObjectTypeAdapter delegate = mock(ObjectTypeAdapter.class);
    doNothing().when(delegate).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());
    Gson context = new Gson();
    Class<Object> type = Object.class;

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, type);
    JsonTreeWriter out = new JsonTreeWriter();
    Gson context2 = new Gson();
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type2 = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type2);

    TreeTypeAdapter<Object> componentTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    Class<Object> componentType = Object.class;

    ArrayTypeAdapter<Object> arrayTypeAdapter =
        new ArrayTypeAdapter<>(context2, componentTypeAdapter, componentType);

    // Act
    typeAdapterRuntimeTypeWrapper.write(out, arrayTypeAdapter);

    // Assert that nothing has changed
    verify(delegate).write(isA(JsonWriter.class), isA(Object.class));
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonNull);
    assertFalse(getResult.isJsonArray());
    assertFalse(getResult.isJsonObject());
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
    ObjectTypeAdapter delegate = mock(ObjectTypeAdapter.class);
    doNothing().when(delegate).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());
    Gson context = new Gson();
    Class<Object> type = Object.class;

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, type);
    JsonTreeWriter out = new JsonTreeWriter();
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();

    // Act
    typeAdapterRuntimeTypeWrapper.write(
        out,
        new JsonAdapterAnnotationTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>())));

    // Assert that nothing has changed
    verify(delegate).write(isA(JsonWriter.class), isA(Object.class));
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonNull);
    assertFalse(getResult.isJsonArray());
    assertFalse(getResult.isJsonObject());
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
    ObjectTypeAdapter delegate = mock(ObjectTypeAdapter.class);
    doNothing().when(delegate).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());
    Gson context = new Gson();
    Class<Object> type = Object.class;

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, type);
    JsonTreeWriter out = new JsonTreeWriter();
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();

    // Act
    typeAdapterRuntimeTypeWrapper.write(
        out,
        new MapTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>()), true));

    // Assert that nothing has changed
    verify(delegate).write(isA(JsonWriter.class), isA(Object.class));
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonNull);
    assertFalse(getResult.isJsonArray());
    assertFalse(getResult.isJsonObject());
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code 42} and {@link
   *       JsonNull#INSTANCE}.
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
  public void testWrite_givenJsonObjectAdd42AndInstance_thenJsonTreeWriterIsJsonObject()
      throws IOException {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("42", JsonNull.INSTANCE);

    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
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
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    typeAdapterRuntimeTypeWrapper.write(out, "Value");

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
   *   <li>Given {@link JsonObject} (default constructor) addProperty {@code 42} and {@code
   *       JsonObject}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_givenJsonObjectAddProperty42AndComGoogleGsonJsonObject()
      throws IOException {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("42", "com.google.gson.JsonObject");

    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
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
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    typeAdapterRuntimeTypeWrapper.write(out, "Value");

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
   *   <li>Given {@link JsonSerializer} {@link JsonSerializer#serialize(Object, Type,
   *       JsonSerializationContext)} return {@link JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_givenJsonSerializerSerializeReturnInstance() throws IOException {
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
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));

    // Act
    typeAdapterRuntimeTypeWrapper.write(new JsonWriter(new StringWriter()), "Value");

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
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    typeAdapterRuntimeTypeWrapper.write(out, "Value");

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
   *       JsonSerializationContext)} return {@link JsonArray#JsonArray(int)} with capacity is
   *       three.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_givenJsonSerializerSerializeReturnJsonArrayWithCapacityIsThree()
      throws IOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonArray(3));
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    Gson context = new Gson();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));

    // Act
    typeAdapterRuntimeTypeWrapper.write(new JsonWriter(new StringWriter()), "Value");

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
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_givenJsonSerializerSerializeReturnJsonObject() throws IOException {
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
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));

    // Act
    typeAdapterRuntimeTypeWrapper.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonSerializer} {@link JsonSerializer#serialize(Object, Type,
   *       JsonSerializationContext)} return {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool
   *       is {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_givenJsonSerializerSerializeReturnJsonPrimitiveWithBoolIsTrue()
      throws IOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonPrimitive(true));
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    Gson context = new Gson();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));

    // Act
    typeAdapterRuntimeTypeWrapper.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonSerializer} {@link JsonSerializer#serialize(Object, Type,
   *       JsonSerializationContext)} return {@link JsonPrimitive#JsonPrimitive(Character)} with c
   *       is start of heading.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_givenJsonSerializerSerializeReturnJsonPrimitiveWithCIsStartOfHeading()
      throws IOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonPrimitive('\u0001'));
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    Gson context = new Gson();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));

    // Act
    typeAdapterRuntimeTypeWrapper.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonSerializer} {@link JsonSerializer#serialize(Object, Type,
   *       JsonSerializationContext)} return {@link JsonPrimitive#JsonPrimitive(String)} with string
   *       is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_givenJsonSerializerSerializeReturnJsonPrimitiveWithStringIsNull()
      throws IOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonPrimitive("null"));
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    Gson context = new Gson();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));

    // Act
    typeAdapterRuntimeTypeWrapper.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@code Key}.
   *   <li>When {@link HashMap#HashMap()} {@code Key} is {@code Value}.
   *   <li>Then not {@link JsonTreeWriter} (default constructor) Empty.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_givenKey_whenHashMapKeyIsValue_thenNotJsonTreeWriterEmpty()
      throws IOException {
    // Arrange
    Gson context = new Gson();
    ObjectTypeAdapter delegate = mock(ObjectTypeAdapter.class);
    Class<Object> type = Object.class;

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, type);
    JsonTreeWriter out = new JsonTreeWriter();

    HashMap<Object, Object> objectObjectMap = new HashMap<>();
    objectObjectMap.put("Key", "Value");

    // Act
    typeAdapterRuntimeTypeWrapper.write(out, objectObjectMap);

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
   *   <li>Then {@link JsonTreeWriter} (default constructor) AsCharacter is {@code t}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_thenJsonTreeWriterAsCharacterIsT() throws IOException {
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

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class), true);
    Gson context = new Gson();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    typeAdapterRuntimeTypeWrapper.write(out, "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonArray);
    Iterator<JsonElement> iteratorResult = ((JsonArray) getResult).iterator();
    JsonElement nextResult = iteratorResult.next();
    assertTrue(nextResult instanceof JsonPrimitive);
    assertEquals('t', getResult.getAsCharacter());
    assertEquals('t', nextResult.getAsCharacter());
    assertEquals(1, ((JsonArray) getResult).size());
    assertFalse(iteratorResult.hasNext());
    assertTrue(getResult.getAsBoolean());
    assertTrue(nextResult.getAsBoolean());
    assertTrue(((JsonPrimitive) nextResult).isBoolean());
    assertEquals(Boolean.TRUE.toString(), getResult.getAsString());
    assertEquals(Boolean.TRUE.toString(), nextResult.getAsString());
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Then {@link JsonTreeWriter} (default constructor) AsNumber {@link LazilyParsedNumber}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_thenJsonTreeWriterAsNumberLazilyParsedNumber() throws IOException {
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
    Class<Object> type2 = Object.class;

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, type2);
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    typeAdapterRuntimeTypeWrapper.write(out, "Value");

    // Assert
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonPrimitive);
    Number asNumber = getResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("Value", getResult.getAsString());
    assertEquals("Value", asNumber.toString());
    assertEquals('V', getResult.getAsCharacter());
    assertTrue(((JsonPrimitive) getResult).isString());
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Then {@link JsonTreeWriter} (default constructor) is {@link JsonArray#JsonArray(int)}
   *       with capacity is three.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_thenJsonTreeWriterIsJsonArrayWithCapacityIsThree() throws IOException {
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

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class), true);
    Gson context = new Gson();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    typeAdapterRuntimeTypeWrapper.write(out, "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonArray);
    assertEquals(jsonArray, getResult);
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
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    typeAdapterRuntimeTypeWrapper.write(out, "Value");

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
   *   <li>Then {@link JsonTreeWriter} (default constructor) is {@link
   *       JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_thenJsonTreeWriterIsJsonPrimitiveWithBoolIsTrue() throws IOException {
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

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class), true);
    Gson context = new Gson();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    typeAdapterRuntimeTypeWrapper.write(out, "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonPrimitive);
    assertEquals(jsonPrimitive, getResult);
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Then {@link JsonTreeWriter} (default constructor) is {@link
   *       JsonPrimitive#JsonPrimitive(String)} with string is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_thenJsonTreeWriterIsJsonPrimitiveWithStringIsNull() throws IOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonPrimitive jsonPrimitive = new JsonPrimitive("null");
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(jsonPrimitive);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class), true);
    Gson context = new Gson();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    typeAdapterRuntimeTypeWrapper.write(out, "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonPrimitive);
    assertEquals(jsonPrimitive, getResult);
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>When {@code A}.
   *   <li>Then {@link JsonTreeWriter} (default constructor) AsString is {@code 65}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_whenA_thenJsonTreeWriterAsStringIs65() throws IOException {
    // Arrange
    Gson context = new Gson();
    ObjectTypeAdapter delegate = mock(ObjectTypeAdapter.class);
    Class<Object> type = Object.class;

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, type);
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    typeAdapterRuntimeTypeWrapper.write(out, (byte) 'A');

    // Assert
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonPrimitive);
    assertEquals("65", getResult.getAsString());
    assertEquals('6', getResult.getAsCharacter());
    assertEquals(65, getResult.getAsInt());
    assertEquals(65.0d, getResult.getAsDouble(), 0.0);
    assertEquals(65.0f, getResult.getAsFloat(), 0.0f);
    assertEquals(65L, getResult.getAsLong());
    assertEquals(65L, getResult.getAsNumber().longValue());
    assertEquals((short) 65, getResult.getAsShort());
    assertEquals(new BigDecimal("65"), getResult.getAsBigDecimal());
    assertEquals('A', getResult.getAsByte());
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>When forty-two.
   *   <li>Then {@link JsonTreeWriter} (default constructor) AsString is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_whenFortyTwo_thenJsonTreeWriterAsStringIs42() throws IOException {
    // Arrange
    Gson context = new Gson();
    ObjectTypeAdapter delegate = mock(ObjectTypeAdapter.class);
    Class<Object> type = Object.class;

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, type);
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    typeAdapterRuntimeTypeWrapper.write(out, 42);

    // Assert
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonPrimitive);
    assertEquals("42", getResult.getAsString());
    assertEquals('4', getResult.getAsCharacter());
    assertEquals(42, getResult.getAsInt());
    assertEquals(42.0d, getResult.getAsDouble(), 0.0);
    assertEquals(42.0f, getResult.getAsFloat(), 0.0f);
    assertEquals(42L, getResult.getAsLong());
    assertEquals(42L, getResult.getAsNumber().longValue());
    assertEquals((short) 42, getResult.getAsShort());
    assertEquals(new BigDecimal("42"), getResult.getAsBigDecimal());
    assertEquals('*', getResult.getAsByte());
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   *   <li>Then {@link JsonTreeWriter} (default constructor) size is zero.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_whenHashMap_thenJsonTreeWriterSizeIsZero() throws IOException {
    // Arrange
    Gson context = new Gson();
    ObjectTypeAdapter delegate = mock(ObjectTypeAdapter.class);
    Class<Object> type = Object.class;

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, type);
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    typeAdapterRuntimeTypeWrapper.write(out, new HashMap<>());

    // Assert
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonObject);
    assertEquals(0, ((JsonObject) getResult).size());
    assertTrue(getResult.isJsonObject());
    assertTrue(((JsonObject) getResult).isEmpty());
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
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));
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
   *   <li>When {@link TreeMap#TreeMap()}.
   *   <li>Then {@link JsonTreeWriter} (default constructor) size is zero.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_whenTreeMap_thenJsonTreeWriterSizeIsZero() throws IOException {
    // Arrange
    Gson context = new Gson();
    ObjectTypeAdapter delegate = mock(ObjectTypeAdapter.class);
    Class<Object> type = Object.class;

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, type);
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    typeAdapterRuntimeTypeWrapper.write(out, new TreeMap<>());

    // Assert
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonObject);
    assertEquals(0, ((JsonObject) getResult).size());
    assertTrue(getResult.isJsonObject());
    assertTrue(((JsonObject) getResult).isEmpty());
    assertSame(getResult, getResult.getAsJsonObject());
  }
}
