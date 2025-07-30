package com.google.gson.internal.bind;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.Gson;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonArray;
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
    when(deserializer.deserialize(
            Mockito.<JsonElement>any(),
            Mockito.<Type>any(),
            Mockito.<JsonDeserializationContext>any()))
        .thenReturn("Deserialize");
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson context = new Gson();
    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));

    JsonReader in = new JsonReader(new StringReader(Boolean.FALSE.toString()));
    in.setStrictness(Strictness.LENIENT);

    // Act
    Object actualReadResult = typeAdapterRuntimeTypeWrapper.read(in);

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertEquals("Deserialize", actualReadResult);
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
    when(deserializer.deserialize(
            Mockito.<JsonElement>any(),
            Mockito.<Type>any(),
            Mockito.<JsonDeserializationContext>any()))
        .thenReturn("Deserialize");
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson context = new Gson();
    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));

    JsonReader in = new JsonReader(new StringReader("foo"));
    in.setStrictness(Strictness.LENIENT);

    // Act
    Object actualReadResult = typeAdapterRuntimeTypeWrapper.read(in);

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertEquals("Deserialize", actualReadResult);
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
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));

    JsonReader in = new JsonReader(new StringReader(""));
    in.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertNull(typeAdapterRuntimeTypeWrapper.read(in));
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
    when(deserializer.deserialize(
            Mockito.<JsonElement>any(),
            Mockito.<Type>any(),
            Mockito.<JsonDeserializationContext>any()))
        .thenReturn("Deserialize");
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson context = new Gson();
    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));

    JsonReader in = new JsonReader(new StringReader(Boolean.FALSE.toString()));
    in.setStrictness(Strictness.STRICT);

    // Act
    Object actualReadResult = typeAdapterRuntimeTypeWrapper.read(in);

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertEquals("Deserialize", actualReadResult);
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>Then not {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE} hasNext.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_thenNotJsonTreeReaderWithElementIsInstanceHasNext() throws IOException {
    // Arrange
    Gson context = new Gson();
    Gson context2 = new Gson();
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    TreeTypeAdapter<Object> componentTypeAdapter =
        new TreeTypeAdapter<>(
            serializer,
            deserializer,
            new Gson(),
            mock(TypeToken.class),
            mock(TypeAdapterFactory.class));

    Class<Object> componentType = Object.class;
    ArrayTypeAdapter<Object> delegate =
        new ArrayTypeAdapter<>(context2, componentTypeAdapter, componentType);

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));
    JsonTreeReader in = new JsonTreeReader(JsonNull.INSTANCE);

    // Act and Assert
    assertNull(typeAdapterRuntimeTypeWrapper.read(in));
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>Then not {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE} hasNext.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_thenNotJsonTreeReaderWithElementIsInstanceHasNext2() throws IOException {
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
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));
    JsonTreeReader in = new JsonTreeReader(JsonNull.INSTANCE);

    // Act and Assert
    assertNull(typeAdapterRuntimeTypeWrapper.read(in));
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>Then not {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonArray#JsonArray(int)} hasNext.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_thenNotJsonTreeReaderWithElementIsJsonArrayHasNext()
      throws JsonParseException, IOException {
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
    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson context = new Gson();
    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));
    JsonTreeReader in = new JsonTreeReader(new JsonArray(3));

    // Act
    Object actualReadResult = typeAdapterRuntimeTypeWrapper.read(in);

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertEquals("Deserialize", actualReadResult);
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>Then not {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonObject} (default constructor) hasNext.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_thenNotJsonTreeReaderWithElementIsJsonObjectHasNext()
      throws JsonParseException, IOException {
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
    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson context = new Gson();
    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));
    JsonTreeReader in = new JsonTreeReader(new JsonObject());

    // Act
    Object actualReadResult = typeAdapterRuntimeTypeWrapper.read(in);

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertEquals("Deserialize", actualReadResult);
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>Then not {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonPrimitive#JsonPrimitive(String)} hasNext.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_thenNotJsonTreeReaderWithElementIsJsonPrimitiveHasNext()
      throws JsonParseException, IOException {
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
    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson context = new Gson();
    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));
    JsonTreeReader in = new JsonTreeReader(new JsonPrimitive("String"));

    // Act
    Object actualReadResult = typeAdapterRuntimeTypeWrapper.read(in);

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertEquals("Deserialize", actualReadResult);
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>Then not {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonPrimitive#JsonPrimitive(Boolean)} hasNext.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_thenNotJsonTreeReaderWithElementIsJsonPrimitiveHasNext2()
      throws JsonParseException, IOException {
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
    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson context = new Gson();
    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));
    JsonTreeReader in = new JsonTreeReader(new JsonPrimitive(true));

    // Act
    Object actualReadResult = typeAdapterRuntimeTypeWrapper.read(in);

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertEquals("Deserialize", actualReadResult);
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonArray#JsonArray(int)} with capacity is three.
   *   <li>Then return {@code Object[]}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_whenJsonArrayWithCapacityIsThree_thenReturnObject() throws IOException {
    // Arrange
    Gson context = new Gson();
    Gson context2 = new Gson();
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> componentTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Class<Object> componentType = Object.class;
    ArrayTypeAdapter<Object> delegate =
        new ArrayTypeAdapter<>(context2, componentTypeAdapter, componentType);

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));
    JsonTreeReader in = new JsonTreeReader(new JsonArray(3));

    // Act
    Object actualReadResult = typeAdapterRuntimeTypeWrapper.read(in);

    // Assert
    assertTrue(actualReadResult instanceof Object[]);
    assertEquals(0, ((Object[]) actualReadResult).length);
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then return {@code Deserialize}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_whenStringReaderWith42_thenReturnDeserialize()
      throws JsonParseException, IOException {
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
    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson context = new Gson();
    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));

    // Act
    Object actualReadResult =
        typeAdapterRuntimeTypeWrapper.read(new JsonReader(new StringReader("42")));

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertEquals("Deserialize", actualReadResult);
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
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));

    // Act and Assert
    assertNull(typeAdapterRuntimeTypeWrapper.read(new JsonReader(new StringReader(""))));
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then return {@code Deserialize}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_whenStringReaderWithFalseToString_thenReturnDeserialize()
      throws JsonParseException, IOException {
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
    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson context = new Gson();
    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));

    // Act
    Object actualReadResult =
        typeAdapterRuntimeTypeWrapper.read(
            new JsonReader(new StringReader(Boolean.FALSE.toString())));

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertEquals("Deserialize", actualReadResult);
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code FALSE}.
   *   <li>Then return {@code Deserialize}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_whenStringReaderWithFalse_thenReturnDeserialize()
      throws JsonParseException, IOException {
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
    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson context = new Gson();
    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));

    // Act
    Object actualReadResult =
        typeAdapterRuntimeTypeWrapper.read(new JsonReader(new StringReader("FALSE")));

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertEquals("Deserialize", actualReadResult);
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
        .thenReturn(JsonNull.INSTANCE);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson context = new Gson();
    Class<Object> type2 = Object.class;
    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, type2);
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
        .thenReturn(JsonNull.INSTANCE);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson context = new Gson();
    Class<Object> type2 = Object.class;
    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, type2);
    JsonWriter out = new JsonWriter(new StringWriter());
    Gson context2 = new Gson();
    JsonSerializer<Object> serializer2 = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer2 = mock(JsonDeserializer.class);
    Gson gson2 = new Gson();
    Class<Object> type3 = Object.class;
    TypeToken<Object> typeToken2 = TypeToken.get(type3);
    TreeTypeAdapter<Object> componentTypeAdapter =
        new TreeTypeAdapter<>(
            serializer2, deserializer2, gson2, typeToken2, mock(TypeAdapterFactory.class));

    Class<Object> componentType = Object.class;

    // Act
    typeAdapterRuntimeTypeWrapper.write(
        out, new ArrayTypeAdapter<>(context2, componentTypeAdapter, componentType));

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
        .thenReturn(JsonNull.INSTANCE);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson context = new Gson();
    Class<Object> type2 = Object.class;
    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, type2);
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
        .thenReturn(JsonNull.INSTANCE);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson context = new Gson();
    Class<Object> type2 = Object.class;
    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, type2);
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
}
