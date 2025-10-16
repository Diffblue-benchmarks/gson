package com.google.gson.internal.bind;

import static org.junit.Assert.assertEquals;
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
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
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

    // Act
    typeAdapterRuntimeTypeWrapper.write(new JsonWriter(new StringWriter()), null);

    // Assert
    verify(delegate).write(isA(JsonWriter.class), (Object) isNull());
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
    JsonWriter out = new JsonWriter(new StringWriter());
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();

    // Act
    typeAdapterRuntimeTypeWrapper.write(
        out,
        new CollectionTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>())));

    // Assert
    verify(delegate).write(isA(JsonWriter.class), isA(Object.class));
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
    JsonWriter out = new JsonWriter(new StringWriter());
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();

    // Act
    typeAdapterRuntimeTypeWrapper.write(
        out,
        new JsonAdapterAnnotationTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>())));

    // Assert
    verify(delegate).write(isA(JsonWriter.class), isA(Object.class));
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add end of text.
   *   <li>Then calls {@link JsonSerializer#serialize(Object, Type, JsonSerializationContext)}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_givenJsonArrayWithCapacityIsThreeAddEndOfText_thenCallsSerialize()
      throws IOException {
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
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code false}.
   *   <li>When {@code Value}.
   *   <li>Then calls {@link JsonSerializer#serialize(Object, Type, JsonSerializationContext)}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_givenJsonArrayWithCapacityIsThreeAddFalse_whenValue_thenCallsSerialize()
      throws IOException {
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
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@link
   *       JsonNull#INSTANCE}.
   *   <li>Then calls {@link JsonSerializer#serialize(Object, Type, JsonSerializationContext)}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_givenJsonArrayWithCapacityIsThreeAddInstance_thenCallsSerialize()
      throws IOException {
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
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code true}.
   *   <li>When {@code Value}.
   *   <li>Then calls {@link JsonSerializer#serialize(Object, Type, JsonSerializationContext)}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_givenJsonArrayWithCapacityIsThreeAddTrue_whenValue_thenCallsSerialize()
      throws IOException {
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
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add valueOf one.
   *   <li>Then calls {@link JsonSerializer#serialize(Object, Type, JsonSerializationContext)}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_givenJsonArrayWithCapacityIsThreeAddValueOfOne_thenCallsSerialize()
      throws IOException {
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
   *   <li>Given {@link JsonObject} (default constructor) add {@code null} and {@link
   *       JsonNull#INSTANCE}.
   *   <li>When {@code Value}.
   *   <li>Then calls {@link JsonSerializer#serialize(Object, Type, JsonSerializationContext)}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_givenJsonObjectAddNullAndInstance_whenValue_thenCallsSerialize()
      throws IOException {
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
   *   <li>Given {@link JsonObject} (default constructor) add {@code Property} and {@link
   *       JsonNull#INSTANCE}.
   *   <li>When {@code Value}.
   *   <li>Then calls {@link JsonSerializer#serialize(Object, Type, JsonSerializationContext)}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_givenJsonObjectAddPropertyAndInstance_whenValue_thenCallsSerialize()
      throws IOException {
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
   *   <li>When {@link DefaultDateTypeAdapter}.
   *   <li>Then calls {@link ObjectTypeAdapter#write(JsonWriter, Object)}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_whenDefaultDateTypeAdapter_thenCallsWrite() throws IOException {
    // Arrange
    ObjectTypeAdapter delegate = mock(ObjectTypeAdapter.class);
    doNothing().when(delegate).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());
    Gson context = new Gson();
    Class<Object> type = Object.class;

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, type);

    // Act
    typeAdapterRuntimeTypeWrapper.write(
        new JsonWriter(new StringWriter()), mock(DefaultDateTypeAdapter.class));

    // Assert
    verify(delegate).write(isA(JsonWriter.class), isA(Object.class));
  }
}
