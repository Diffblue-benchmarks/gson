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
import com.google.gson.JsonArray;
import com.google.gson.JsonArrayTestFactory;
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
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.internal.reflect.ReflectionHelperTestFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Iterator;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class TreeTypeAdapterDiffblueTest {
  /**
   * Test {@link TreeTypeAdapter#TreeTypeAdapter(JsonSerializer, JsonDeserializer, Gson, TypeToken,
   * TypeAdapterFactory)}.
   *
   * <p>Method under test: {@link TreeTypeAdapter#TreeTypeAdapter(JsonSerializer, JsonDeserializer,
   * Gson, TypeToken, TypeAdapterFactory)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TreeTypeAdapter.<init>(JsonSerializer, JsonDeserializer, Gson, TypeToken, TypeAdapterFactory)"
  })
  public void testNewTreeTypeAdapter() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    // Act
    TreeTypeAdapter<Object> actualTreeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    // Assert
    Gson gson2 = actualTreeTypeAdapter.gson;
    assertFalse(gson2.serializeNulls());
    assertTrue(gson2.htmlSafe());
    TypeAdapter<Object> actualSerializationDelegate =
        actualTreeTypeAdapter.getSerializationDelegate();
    assertSame(actualTreeTypeAdapter, actualSerializationDelegate);
  }

  /**
   * Test {@link TreeTypeAdapter#TreeTypeAdapter(JsonSerializer, JsonDeserializer, Gson, TypeToken,
   * TypeAdapterFactory, boolean)}.
   *
   * <p>Method under test: {@link TreeTypeAdapter#TreeTypeAdapter(JsonSerializer, JsonDeserializer,
   * Gson, TypeToken, TypeAdapterFactory, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TreeTypeAdapter.<init>(JsonSerializer, JsonDeserializer, Gson, TypeToken, TypeAdapterFactory, boolean)"
  })
  public void testNewTreeTypeAdapter2() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    // Act
    TreeTypeAdapter<Object> actualTreeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class), true);

    // Assert
    Gson gson2 = actualTreeTypeAdapter.gson;
    assertFalse(gson2.serializeNulls());
    assertTrue(gson2.htmlSafe());
    TypeAdapter<Object> actualSerializationDelegate =
        actualTreeTypeAdapter.getSerializationDelegate();
    assertSame(actualTreeTypeAdapter, actualSerializationDelegate);
  }

  /**
   * Test {@link TreeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@link JsonDeserializer} {@link JsonDeserializer#deserialize(JsonElement, Type,
   *       JsonDeserializationContext)} return {@code false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
  public void testRead_givenJsonDeserializerDeserializeReturnFalse_thenReturnFalse()
      throws JsonParseException, IOException {
    // Arrange
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    when(deserializer.deserialize(
            Mockito.<JsonElement>any(),
            Mockito.<Type>any(),
            Mockito.<JsonDeserializationContext>any()))
        .thenReturn(false);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    JsonTreeReader in = new JsonTreeReader(new JsonPrimitive(true));

    // Act
    Object actualReadResult = treeTypeAdapter.read(in);

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertFalse(in.hasNext());
    assertFalse((Boolean) actualReadResult);
  }

  /**
   * Test {@link TreeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@link JsonDeserializer} {@link JsonDeserializer#deserialize(JsonElement, Type,
   *       JsonDeserializationContext)} return {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
  public void testRead_givenJsonDeserializerDeserializeReturnTrue_thenReturnTrue()
      throws JsonParseException, IOException {
    // Arrange
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    when(deserializer.deserialize(
            Mockito.<JsonElement>any(),
            Mockito.<Type>any(),
            Mockito.<JsonDeserializationContext>any()))
        .thenReturn(true);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    JsonTreeReader in = new JsonTreeReader(new JsonPrimitive(true));

    // Act
    Object actualReadResult = treeTypeAdapter.read(in);

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertFalse(in.hasNext());
    assertTrue((Boolean) actualReadResult);
  }

  /**
   * Test {@link TreeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then return createPublicField.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_whenStringReaderWith42_thenReturnCreatePublicField()
      throws JsonParseException, IOException, NoSuchFieldException {
    // Arrange
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();
    when(deserializer.deserialize(
            Mockito.<JsonElement>any(),
            Mockito.<Type>any(),
            Mockito.<JsonDeserializationContext>any()))
        .thenReturn(createPublicFieldResult);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    JsonReader in = new JsonReader(new StringReader("42"));
    in.setStrictness(Strictness.LENIENT);

    // Act
    Object actualReadResult = treeTypeAdapter.read(in);

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertSame(createPublicFieldResult, actualReadResult);
  }

  /**
   * Test {@link TreeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_whenStringReaderWithEmptyString_thenReturnNull()
      throws IOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    JsonReader in = new JsonReader(new StringReader(""));
    in.setStrictness(Strictness.LENIENT);

    // Act
    Object actualReadResult = treeTypeAdapter.read(in);

    // Assert
    assertNull(actualReadResult);
  }

  /**
   * Test {@link TreeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_whenStringReaderWithFalseToString()
      throws JsonParseException, IOException, NoSuchFieldException {
    // Arrange
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();
    when(deserializer.deserialize(
            Mockito.<JsonElement>any(),
            Mockito.<Type>any(),
            Mockito.<JsonDeserializationContext>any()))
        .thenReturn(createPublicFieldResult);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    StringReader in = new StringReader(Boolean.FALSE.toString());

    JsonReader in2 = new JsonReader(in);
    in2.setStrictness(Strictness.LENIENT);

    // Act
    Object actualReadResult = treeTypeAdapter.read(in2);

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertSame(createPublicFieldResult, actualReadResult);
  }

  /**
   * Test {@link TreeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then return createPublicField.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_whenStringReaderWithFoo_thenReturnCreatePublicField()
      throws JsonParseException, IOException, NoSuchFieldException {
    // Arrange
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();
    when(deserializer.deserialize(
            Mockito.<JsonElement>any(),
            Mockito.<Type>any(),
            Mockito.<JsonDeserializationContext>any()))
        .thenReturn(createPublicFieldResult);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    JsonReader in = new JsonReader(new StringReader("foo"));
    in.setStrictness(Strictness.LENIENT);

    // Act
    Object actualReadResult = treeTypeAdapter.read(in);

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertSame(createPublicFieldResult, actualReadResult);
  }

  /**
   * Test {@link TreeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code STRICT}.
   *   <li>When {@link JsonReader#JsonReader(Reader)} with in is {@link
   *       StringReader#StringReader(String)} Strictness is {@code STRICT}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
  public void testRead_givenStrict_whenJsonReaderWithInIsStringReaderStrictnessIsStrict()
      throws JsonParseException, IOException, NoSuchFieldException {
    // Arrange
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();
    when(deserializer.deserialize(
            Mockito.<JsonElement>any(),
            Mockito.<Type>any(),
            Mockito.<JsonDeserializationContext>any()))
        .thenReturn(createPublicFieldResult);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    StringReader in = new StringReader(Boolean.FALSE.toString());

    JsonReader in2 = new JsonReader(in);
    in2.setStrictness(Strictness.STRICT);

    // Act
    Object actualReadResult = treeTypeAdapter.read(in2);

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertSame(createPublicFieldResult, actualReadResult);
  }

  /**
   * Test {@link TreeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Then not {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is
   *       createJsonArrayWithOneElement hasNext.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
  public void testRead_thenNotJsonTreeReaderWithElementIsCreateJsonArrayWithOneElementHasNext()
      throws JsonParseException, IOException, NoSuchFieldException {
    // Arrange
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();
    when(deserializer.deserialize(
            Mockito.<JsonElement>any(),
            Mockito.<Type>any(),
            Mockito.<JsonDeserializationContext>any()))
        .thenReturn(createPublicFieldResult);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    JsonTreeReader in = new JsonTreeReader(JsonArrayTestFactory.createJsonArrayWithOneElement());

    // Act
    Object actualReadResult = treeTypeAdapter.read(in);

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertFalse(in.hasNext());
    assertSame(createPublicFieldResult, actualReadResult);
  }

  /**
   * Test {@link TreeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Then not {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE} hasNext.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
  public void testRead_thenNotJsonTreeReaderWithElementIsInstanceHasNext() throws IOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    JsonTreeReader in = new JsonTreeReader(JsonNull.INSTANCE);

    // Act
    Object actualReadResult = treeTypeAdapter.read(in);

    // Assert
    assertNull(actualReadResult);
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link TreeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Then not {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonObject} (default constructor) hasNext.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
  public void testRead_thenNotJsonTreeReaderWithElementIsJsonObjectHasNext()
      throws JsonParseException, IOException, NoSuchFieldException {
    // Arrange
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();
    when(deserializer.deserialize(
            Mockito.<JsonElement>any(),
            Mockito.<Type>any(),
            Mockito.<JsonDeserializationContext>any()))
        .thenReturn(createPublicFieldResult);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    JsonTreeReader in = new JsonTreeReader(new JsonObject());

    // Act
    Object actualReadResult = treeTypeAdapter.read(in);

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertFalse(in.hasNext());
    assertSame(createPublicFieldResult, actualReadResult);
  }

  /**
   * Test {@link TreeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Then not {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonPrimitive#JsonPrimitive(String)} hasNext.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
  public void testRead_thenNotJsonTreeReaderWithElementIsJsonPrimitiveHasNext()
      throws JsonParseException, IOException, NoSuchFieldException {
    // Arrange
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();
    when(deserializer.deserialize(
            Mockito.<JsonElement>any(),
            Mockito.<Type>any(),
            Mockito.<JsonDeserializationContext>any()))
        .thenReturn(createPublicFieldResult);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    JsonTreeReader in = new JsonTreeReader(new JsonPrimitive("String"));

    // Act
    Object actualReadResult = treeTypeAdapter.read(in);

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertFalse(in.hasNext());
    assertSame(createPublicFieldResult, actualReadResult);
  }

  /**
   * Test {@link TreeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Then not {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonPrimitive#JsonPrimitive(Boolean)} hasNext.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
  public void testRead_thenNotJsonTreeReaderWithElementIsJsonPrimitiveHasNext2()
      throws JsonParseException, IOException, NoSuchFieldException {
    // Arrange
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();
    when(deserializer.deserialize(
            Mockito.<JsonElement>any(),
            Mockito.<Type>any(),
            Mockito.<JsonDeserializationContext>any()))
        .thenReturn(createPublicFieldResult);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    JsonTreeReader in = new JsonTreeReader(new JsonPrimitive(true));

    // Act
    Object actualReadResult = treeTypeAdapter.read(in);

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertFalse(in.hasNext());
    assertSame(createPublicFieldResult, actualReadResult);
  }

  /**
   * Test {@link TreeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then return createPublicField.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
  public void testRead_whenStringReaderWith42_thenReturnCreatePublicField()
      throws JsonParseException, IOException, NoSuchFieldException {
    // Arrange
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();
    when(deserializer.deserialize(
            Mockito.<JsonElement>any(),
            Mockito.<Type>any(),
            Mockito.<JsonDeserializationContext>any()))
        .thenReturn(createPublicFieldResult);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    // Act
    Object actualReadResult = treeTypeAdapter.read(new JsonReader(new StringReader("42")));

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertSame(createPublicFieldResult, actualReadResult);
  }

  /**
   * Test {@link TreeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
  public void testRead_whenStringReaderWithEmptyString_thenReturnNull() throws IOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    // Act
    Object actualReadResult = treeTypeAdapter.read(new JsonReader(new StringReader("")));

    // Assert
    assertNull(actualReadResult);
  }

  /**
   * Test {@link TreeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then return createPublicField.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
  public void testRead_whenStringReaderWithFalseToString_thenReturnCreatePublicField()
      throws JsonParseException, IOException, NoSuchFieldException {
    // Arrange
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();
    when(deserializer.deserialize(
            Mockito.<JsonElement>any(),
            Mockito.<Type>any(),
            Mockito.<JsonDeserializationContext>any()))
        .thenReturn(createPublicFieldResult);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    StringReader in = new StringReader(Boolean.FALSE.toString());

    // Act
    Object actualReadResult = treeTypeAdapter.read(new JsonReader(in));

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertSame(createPublicFieldResult, actualReadResult);
  }

  /**
   * Test {@link TreeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code FALSE}.
   *   <li>Then return createPublicField.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
  public void testRead_whenStringReaderWithFalse_thenReturnCreatePublicField()
      throws JsonParseException, IOException, NoSuchFieldException {
    // Arrange
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();
    when(deserializer.deserialize(
            Mockito.<JsonElement>any(),
            Mockito.<Type>any(),
            Mockito.<JsonDeserializationContext>any()))
        .thenReturn(createPublicFieldResult);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    // Act
    Object actualReadResult = treeTypeAdapter.read(new JsonReader(new StringReader("FALSE")));

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertSame(createPublicFieldResult, actualReadResult);
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite() throws IOException, NoSuchFieldException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(JsonArrayTestFactory.createJsonArrayWithOneElement());
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class), false);
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    treeTypeAdapter.write(out, ReflectionHelperTestFactory.createPublicField());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonArray);
    Iterator<JsonElement> iteratorResult = ((JsonArray) getResult).iterator();
    JsonElement nextResult = iteratorResult.next();
    assertTrue(nextResult instanceof JsonPrimitive);
    Number asNumber = getResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    Number asNumber2 = nextResult.getAsNumber();
    assertTrue(asNumber2 instanceof LazilyParsedNumber);
    assertEquals("singleElement", getResult.getAsString());
    assertEquals("singleElement", asNumber.toString());
    assertEquals('s', getResult.getAsCharacter());
    assertEquals(1, ((JsonArray) getResult).size());
    assertFalse(getResult.getAsBoolean());
    assertFalse(iteratorResult.hasNext());
    assertEquals(asNumber, asNumber2);
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithOneElement add createJsonArrayWithOneElement.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_givenCreateJsonArrayWithOneElementAddCreateJsonArrayWithOneElement()
      throws IOException, NoSuchFieldException {
    // Arrange
    JsonArray createJsonArrayWithOneElementResult =
        JsonArrayTestFactory.createJsonArrayWithOneElement();
    createJsonArrayWithOneElementResult.add(JsonArrayTestFactory.createJsonArrayWithOneElement());

    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(createJsonArrayWithOneElementResult);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    treeTypeAdapter.write(out, ReflectionHelperTestFactory.createPublicField());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithOneElement add {@link JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_givenCreateJsonArrayWithOneElementAddInstance()
      throws IOException, NoSuchFieldException {
    // Arrange
    JsonArray createJsonArrayWithOneElementResult =
        JsonArrayTestFactory.createJsonArrayWithOneElement();
    createJsonArrayWithOneElementResult.add(JsonNull.INSTANCE);

    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(createJsonArrayWithOneElementResult);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    treeTypeAdapter.write(out, ReflectionHelperTestFactory.createPublicField());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code 42} and
   *       createJsonArrayWithOneElement.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_givenJsonObjectAdd42AndCreateJsonArrayWithOneElement()
      throws IOException, NoSuchFieldException {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("42", JsonArrayTestFactory.createJsonArrayWithOneElement());

    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(jsonObject);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    treeTypeAdapter.write(out, ReflectionHelperTestFactory.createPublicField());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code name == null} and
   *       createJsonArrayWithOneElement.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_givenJsonObjectAddNameNullAndCreateJsonArrayWithOneElement()
      throws IOException, NoSuchFieldException {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("name == null", JsonArrayTestFactory.createJsonArrayWithOneElement());
    jsonObject.add("42", JsonArrayTestFactory.createJsonArrayWithOneElement());

    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(jsonObject);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    treeTypeAdapter.write(out, ReflectionHelperTestFactory.createPublicField());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code Property} and
   *       createJsonArrayWithOneElement.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_givenJsonObjectAddPropertyAndCreateJsonArrayWithOneElement()
      throws IOException, NoSuchFieldException {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", JsonArrayTestFactory.createJsonArrayWithOneElement());

    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(jsonObject);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class), true);
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    treeTypeAdapter.write(out, ReflectionHelperTestFactory.createPublicField());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonObject);
    assertEquals(jsonObject, getResult);
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code Property} and {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_givenJsonObjectAddPropertyAndInstance()
      throws IOException, NoSuchFieldException {
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

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class), true);
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    treeTypeAdapter.write(out, ReflectionHelperTestFactory.createPublicField());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code Property} and {@link
   *       JsonNull#INSTANCE}.
   *   <li>Then {@link JsonTreeWriter} (default constructor) {@link JsonObject}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_givenJsonObjectAddPropertyAndInstance_thenJsonTreeWriterJsonObject()
      throws IOException, NoSuchFieldException {
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

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class), true);
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    treeTypeAdapter.write(out, ReflectionHelperTestFactory.createPublicField());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonObject);
    assertEquals(jsonObject, getResult);
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonSerializer} {@link JsonSerializer#serialize(Object, Type,
   *       JsonSerializationContext)} return createJsonArrayWithBooleans.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_givenJsonSerializerSerializeReturnCreateJsonArrayWithBooleans()
      throws IOException, NoSuchFieldException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(JsonArrayTestFactory.createJsonArrayWithBooleans());
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    treeTypeAdapter.write(out, ReflectionHelperTestFactory.createPublicField());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonSerializer} {@link JsonSerializer#serialize(Object, Type,
   *       JsonSerializationContext)} return createJsonArrayWithMixedTypes.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_givenJsonSerializerSerializeReturnCreateJsonArrayWithMixedTypes()
      throws IOException, NoSuchFieldException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(JsonArrayTestFactory.createJsonArrayWithMixedTypes());
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    treeTypeAdapter.write(out, ReflectionHelperTestFactory.createPublicField());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonSerializer} {@link JsonSerializer#serialize(Object, Type,
   *       JsonSerializationContext)} return createJsonArrayWithNumbers.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_givenJsonSerializerSerializeReturnCreateJsonArrayWithNumbers()
      throws IOException, NoSuchFieldException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(JsonArrayTestFactory.createJsonArrayWithNumbers());
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    treeTypeAdapter.write(out, ReflectionHelperTestFactory.createPublicField());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonSerializer} {@link JsonSerializer#serialize(Object, Type,
   *       JsonSerializationContext)} return createJsonArrayWithOneElement.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_givenJsonSerializerSerializeReturnCreateJsonArrayWithOneElement()
      throws IOException, NoSuchFieldException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(JsonArrayTestFactory.createJsonArrayWithOneElement());
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    treeTypeAdapter.write(out, ReflectionHelperTestFactory.createPublicField());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonSerializer} {@link JsonSerializer#serialize(Object, Type,
   *       JsonSerializationContext)} return {@link JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_givenJsonSerializerSerializeReturnInstance()
      throws IOException, NoSuchFieldException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(JsonNull.INSTANCE);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    treeTypeAdapter.write(out, ReflectionHelperTestFactory.createPublicField());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonSerializer} {@link JsonSerializer#serialize(Object, Type,
   *       JsonSerializationContext)} return {@link JsonNull#INSTANCE}.
   *   <li>Then {@link JsonTreeWriter} (default constructor) {@link JsonNull}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_givenJsonSerializerSerializeReturnInstance_thenJsonTreeWriterJsonNull()
      throws IOException, NoSuchFieldException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(JsonNull.INSTANCE);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class), true);
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    treeTypeAdapter.write(out, ReflectionHelperTestFactory.createPublicField());

    // Assert that nothing has changed
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonNull);
    assertFalse(getResult.isJsonObject());
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonSerializer} {@link JsonSerializer#serialize(Object, Type,
   *       JsonSerializationContext)} return {@link JsonObject} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_givenJsonSerializerSerializeReturnJsonObject()
      throws IOException, NoSuchFieldException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonObject());
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    treeTypeAdapter.write(out, ReflectionHelperTestFactory.createPublicField());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonSerializer} {@link JsonSerializer#serialize(Object, Type,
   *       JsonSerializationContext)} return {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool
   *       is {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_givenJsonSerializerSerializeReturnJsonPrimitiveWithBoolIsTrue()
      throws IOException, NoSuchFieldException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonPrimitive(true));
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    treeTypeAdapter.write(out, ReflectionHelperTestFactory.createPublicField());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonSerializer} {@link JsonSerializer#serialize(Object, Type,
   *       JsonSerializationContext)} return {@link JsonPrimitive#JsonPrimitive(Character)} with c
   *       is start of heading.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_givenJsonSerializerSerializeReturnJsonPrimitiveWithCIsStartOfHeading()
      throws IOException, NoSuchFieldException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonPrimitive('\u0001'));
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    treeTypeAdapter.write(out, ReflectionHelperTestFactory.createPublicField());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonSerializer} {@link JsonSerializer#serialize(Object, Type,
   *       JsonSerializationContext)} return {@link JsonPrimitive#JsonPrimitive(String)} with {@code
   *       String}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_givenJsonSerializerSerializeReturnJsonPrimitiveWithString()
      throws IOException, NoSuchFieldException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonPrimitive("String"));
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    treeTypeAdapter.write(out, ReflectionHelperTestFactory.createPublicField());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link JsonTreeWriter} (default constructor) Strictness is {@code LENIENT}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_givenLenient_whenJsonTreeWriterStrictnessIsLenient()
      throws IOException, NoSuchFieldException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonArray createJsonArrayWithNumbersResult = JsonArrayTestFactory.createJsonArrayWithNumbers();
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(createJsonArrayWithNumbersResult);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class), true);

    JsonTreeWriter out = new JsonTreeWriter();
    out.setStrictness(Strictness.LENIENT);

    // Act
    treeTypeAdapter.write(out, ReflectionHelperTestFactory.createPublicField());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonArray);
    assertEquals(createJsonArrayWithNumbersResult, getResult);
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@code true}.
   *   <li>When {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()} HtmlSafe is {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_givenTrue_whenJsonWriterWithOutIsStringWriterHtmlSafeIsTrue()
      throws IOException, NoSuchFieldException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(JsonArrayTestFactory.createJsonArrayWithOneElement());
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    JsonWriter out = new JsonWriter(new StringWriter());
    out.setHtmlSafe(true);

    // Act
    treeTypeAdapter.write(out, ReflectionHelperTestFactory.createPublicField());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Then {@link JsonTreeWriter} (default constructor) AsNumber {@link LazilyParsedNumber}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_thenJsonTreeWriterAsNumberLazilyParsedNumber()
      throws IOException, NoSuchFieldException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(JsonArrayTestFactory.createJsonArrayWithOneElement());
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class), true);
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    treeTypeAdapter.write(out, ReflectionHelperTestFactory.createPublicField());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonArray);
    Iterator<JsonElement> iteratorResult = ((JsonArray) getResult).iterator();
    JsonElement nextResult = iteratorResult.next();
    assertTrue(nextResult instanceof JsonPrimitive);
    Number asNumber = getResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    Number asNumber2 = nextResult.getAsNumber();
    assertTrue(asNumber2 instanceof LazilyParsedNumber);
    assertEquals("singleElement", getResult.getAsString());
    assertEquals("singleElement", asNumber.toString());
    assertEquals('s', getResult.getAsCharacter());
    assertEquals(1, ((JsonArray) getResult).size());
    assertFalse(getResult.getAsBoolean());
    assertFalse(iteratorResult.hasNext());
    assertEquals(asNumber, asNumber2);
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Then {@link JsonTreeWriter} (default constructor) is createJsonArrayWithMixedTypes.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_thenJsonTreeWriterIsCreateJsonArrayWithMixedTypes()
      throws IOException, NoSuchFieldException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonArray createJsonArrayWithMixedTypesResult =
        JsonArrayTestFactory.createJsonArrayWithMixedTypes();
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(createJsonArrayWithMixedTypesResult);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class), true);
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    treeTypeAdapter.write(out, ReflectionHelperTestFactory.createPublicField());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonArray);
    assertEquals(createJsonArrayWithMixedTypesResult, getResult);
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Then {@link JsonTreeWriter} (default constructor) is createJsonArrayWithNumbers.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_thenJsonTreeWriterIsCreateJsonArrayWithNumbers()
      throws IOException, NoSuchFieldException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonArray createJsonArrayWithNumbersResult = JsonArrayTestFactory.createJsonArrayWithNumbers();
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(createJsonArrayWithNumbersResult);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class), true);
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    treeTypeAdapter.write(out, ReflectionHelperTestFactory.createPublicField());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonArray);
    assertEquals(createJsonArrayWithNumbersResult, getResult);
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Then {@link JsonTreeWriter} (default constructor) is {@link
   *       JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_thenJsonTreeWriterIsJsonPrimitiveWithBoolIsTrue()
      throws IOException, NoSuchFieldException {
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

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class), true);
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    treeTypeAdapter.write(out, ReflectionHelperTestFactory.createPublicField());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonPrimitive);
    assertEquals(jsonPrimitive, getResult);
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Then {@link JsonTreeWriter} (default constructor) is {@link
   *       JsonPrimitive#JsonPrimitive(String)} with {@code String}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_thenJsonTreeWriterIsJsonPrimitiveWithString()
      throws IOException, NoSuchFieldException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonPrimitive jsonPrimitive = new JsonPrimitive("String");
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(jsonPrimitive);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class), true);
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    treeTypeAdapter.write(out, ReflectionHelperTestFactory.createPublicField());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonPrimitive);
    assertEquals(jsonPrimitive, getResult);
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Then {@link JsonTreeWriter} (default constructor) iterator next AsNumber toString is
   *       {@code singleElement}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_thenJsonTreeWriterIteratorNextAsNumberToStringIsSingleElement()
      throws IOException, NoSuchFieldException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);

    JsonArray createJsonArrayWithOneElementResult =
        JsonArrayTestFactory.createJsonArrayWithOneElement();
    createJsonArrayWithOneElementResult.add(JsonNull.INSTANCE);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(createJsonArrayWithOneElementResult);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class), true);
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    treeTypeAdapter.write(out, ReflectionHelperTestFactory.createPublicField());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonArray);
    Iterator<JsonElement> iteratorResult = ((JsonArray) getResult).iterator();
    JsonElement nextResult = iteratorResult.next();
    JsonElement nextResult2 = iteratorResult.next();
    assertFalse(iteratorResult.hasNext());
    assertTrue(nextResult instanceof JsonPrimitive);
    Number asNumber = nextResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("singleElement", asNumber.toString());
    assertTrue(nextResult2 instanceof JsonNull);
    assertSame(nextResult2, nextResult2.getAsJsonNull());
    assertTrue(nextResult2.isJsonNull());
    assertFalse(nextResult2.isJsonPrimitive());
    assertEquals(2, ((JsonArray) getResult).size());
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Then {@link JsonTreeWriter} (default constructor) {@link JsonObject}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_thenJsonTreeWriterJsonObject() throws IOException, NoSuchFieldException {
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

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class), true);
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    treeTypeAdapter.write(out, ReflectionHelperTestFactory.createPublicField());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonObject);
    assertEquals(jsonObject, getResult);
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@link JsonTreeWriter} (default constructor) {@link JsonNull}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_whenNull_thenJsonTreeWriterJsonNull() throws IOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class), true);
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    treeTypeAdapter.write(out, null);

    // Assert that nothing has changed
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonNull);
    assertFalse(getResult.isJsonObject());
  }

  /**
   * Test {@link TreeTypeAdapter#getSerializationDelegate()}.
   *
   * <p>Method under test: {@link TreeTypeAdapter#getSerializationDelegate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapter TreeTypeAdapter.getSerializationDelegate()"})
  public void testGetSerializationDelegate() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    // Act
    TypeAdapter<Object> actualSerializationDelegate = treeTypeAdapter.getSerializationDelegate();

    // Assert
    assertSame(treeTypeAdapter, actualSerializationDelegate);
  }

  /**
   * Test {@link TreeTypeAdapter#getSerializationDelegate()}.
   *
   * <p>Method under test: {@link TreeTypeAdapter#getSerializationDelegate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapter TreeTypeAdapter.getSerializationDelegate()"})
  public void testGetSerializationDelegate2() {
    // Arrange
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(null, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    // Act and Assert
    assertTrue(treeTypeAdapter.getSerializationDelegate() instanceof ObjectTypeAdapter);
  }

  /**
   * Test {@link TreeTypeAdapter#getSerializationDelegate()}.
   *
   * <p>Method under test: {@link TreeTypeAdapter#getSerializationDelegate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapter TreeTypeAdapter.getSerializationDelegate()"})
  public void testGetSerializationDelegate3() {
    // Arrange
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(null, deserializer, gson, typeToken, ArrayTypeAdapter.FACTORY);

    // Act and Assert
    assertEquals("null", treeTypeAdapter.getSerializationDelegate().toJson(null));
  }

  /**
   * Test {@link TreeTypeAdapter#getSerializationDelegate()}.
   *
   * <p>Method under test: {@link TreeTypeAdapter#getSerializationDelegate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapter TreeTypeAdapter.getSerializationDelegate()"})
  public void testGetSerializationDelegate4() {
    // Arrange
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            null, deserializer, gson, typeToken, DefaultDateTypeAdapter.DEFAULT_STYLE_FACTORY);

    // Act and Assert
    assertEquals("null", treeTypeAdapter.getSerializationDelegate().toJson(null));
  }

  /**
   * Test {@link TreeTypeAdapter#getSerializationDelegate()}.
   *
   * <p>Method under test: {@link TreeTypeAdapter#getSerializationDelegate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapter TreeTypeAdapter.getSerializationDelegate()"})
  public void testGetSerializationDelegate5() {
    // Arrange
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(null, deserializer, gson, typeToken, Excluder.DEFAULT);

    // Act and Assert
    assertEquals("null", treeTypeAdapter.getSerializationDelegate().toJson(null));
  }

  /**
   * Test {@link TreeTypeAdapter#getSerializationDelegate()}.
   *
   * <p>Method under test: {@link TreeTypeAdapter#getSerializationDelegate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapter TreeTypeAdapter.getSerializationDelegate()"})
  public void testGetSerializationDelegate6() {
    // Arrange
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            null, deserializer, gson, typeToken, TypeAdapters.JSON_ELEMENT_FACTORY);

    // Act and Assert
    assertTrue(treeTypeAdapter.getSerializationDelegate() instanceof ObjectTypeAdapter);
  }

  /**
   * Test {@link TreeTypeAdapter#newFactory(TypeToken, Object)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then create {@link Gson#Gson()} and {@link Object} return {@link TreeTypeAdapter}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#newFactory(TypeToken, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapterFactory TreeTypeAdapter.newFactory(TypeToken, Object)"})
  public void testNewFactory_whenJavaLangObject_thenCreateGsonAndObjectReturnTreeTypeAdapter() {
    // Arrange
    Class<Object> type = Object.class;
    TypeToken<?> exactType = TypeToken.get(type);

    // Act
    TypeAdapterFactory actualNewFactoryResult =
        TreeTypeAdapter.newFactory(exactType, mock(JsonSerializer.class));
    Gson gson = new Gson();
    Class<Object> type2 = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type2);
    TypeAdapter<Object> actualCreateResult = actualNewFactoryResult.create(gson, getResult);

    // Assert
    assertTrue(actualCreateResult instanceof TreeTypeAdapter);
    Gson gson2 = ((TreeTypeAdapter<Object>) actualCreateResult).gson;
    assertFalse(gson2.serializeNulls());
    assertTrue(gson2.htmlSafe());
    assertSame(
        actualCreateResult,
        ((TreeTypeAdapter<Object>) actualCreateResult).getSerializationDelegate());
  }

  /**
   * Test {@link TreeTypeAdapter#newFactory(TypeToken, Object)}.
   *
   * <ul>
   *   <li>When {@link JsonDeserializer}.
   *   <li>Then return create {@link Gson#Gson()} and {@link Object} is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#newFactory(TypeToken, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapterFactory TreeTypeAdapter.newFactory(TypeToken, Object)"})
  public void testNewFactory_whenJsonDeserializer_thenReturnCreateGsonAndObjectIsNull() {
    // Arrange
    Class<?> type = ReflectionHelperTestFactory.createStringClass();
    TypeToken<?> exactType = TypeToken.get(type);

    // Act
    TypeAdapterFactory actualNewFactoryResult =
        TreeTypeAdapter.newFactory(exactType, mock(JsonDeserializer.class));
    Gson gson = new Gson();
    Class<Object> type2 = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type2);

    // Assert
    assertNull(actualNewFactoryResult.create(gson, getResult));
  }

  /**
   * Test {@link TreeTypeAdapter#newFactory(TypeToken, Object)}.
   *
   * <ul>
   *   <li>When {@link JsonSerializer}.
   *   <li>Then return create {@link Gson#Gson()} and {@code null} is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#newFactory(TypeToken, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapterFactory TreeTypeAdapter.newFactory(TypeToken, Object)"})
  public void testNewFactory_whenJsonSerializer_thenReturnCreateGsonAndNullIsNull() {
    // Arrange
    Class<?> type = ReflectionHelperTestFactory.createStringClass();
    TypeToken<?> exactType = TypeToken.get(type);

    // Act
    TypeAdapterFactory actualNewFactoryResult =
        TreeTypeAdapter.newFactory(exactType, mock(JsonSerializer.class));

    // Assert
    assertNull(actualNewFactoryResult.create(new Gson(), null));
  }

  /**
   * Test {@link TreeTypeAdapter#newFactory(TypeToken, Object)}.
   *
   * <ul>
   *   <li>When {@link JsonSerializer}.
   *   <li>Then return create {@link Gson#Gson()} and {@link Object} is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#newFactory(TypeToken, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapterFactory TreeTypeAdapter.newFactory(TypeToken, Object)"})
  public void testNewFactory_whenJsonSerializer_thenReturnCreateGsonAndObjectIsNull() {
    // Arrange
    Class<?> type = ReflectionHelperTestFactory.createStringClass();
    TypeToken<?> exactType = TypeToken.get(type);

    // Act
    TypeAdapterFactory actualNewFactoryResult =
        TreeTypeAdapter.newFactory(exactType, mock(JsonSerializer.class));
    Gson gson = new Gson();
    Class<Object> type2 = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type2);

    // Assert
    assertNull(actualNewFactoryResult.create(gson, getResult));
  }

  /**
   * Test {@link TreeTypeAdapter#newFactoryWithMatchRawType(TypeToken, Object)}.
   *
   * <ul>
   *   <li>Then create {@link Gson#Gson()} and {@link Object} return {@link TreeTypeAdapter}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#newFactoryWithMatchRawType(TypeToken, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "TypeAdapterFactory TreeTypeAdapter.newFactoryWithMatchRawType(TypeToken, Object)"
  })
  public void testNewFactoryWithMatchRawType_thenCreateGsonAndObjectReturnTreeTypeAdapter() {
    // Arrange
    Class<Object> type = Object.class;
    TypeToken<?> exactType = TypeToken.get(type);

    // Act
    TypeAdapterFactory actualNewFactoryWithMatchRawTypeResult =
        TreeTypeAdapter.newFactoryWithMatchRawType(exactType, mock(JsonSerializer.class));
    Gson gson = new Gson();
    Class<Object> type2 = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type2);
    TypeAdapter<Object> actualCreateResult =
        actualNewFactoryWithMatchRawTypeResult.create(gson, getResult);

    // Assert
    assertTrue(actualCreateResult instanceof TreeTypeAdapter);
    Gson gson2 = ((TreeTypeAdapter<Object>) actualCreateResult).gson;
    assertFalse(gson2.serializeNulls());
    assertTrue(gson2.htmlSafe());
    assertSame(
        actualCreateResult,
        ((TreeTypeAdapter<Object>) actualCreateResult).getSerializationDelegate());
  }

  /**
   * Test {@link TreeTypeAdapter#newFactoryWithMatchRawType(TypeToken, Object)}.
   *
   * <ul>
   *   <li>Then return create {@link Gson#Gson()} and {@link Object} is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#newFactoryWithMatchRawType(TypeToken, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "TypeAdapterFactory TreeTypeAdapter.newFactoryWithMatchRawType(TypeToken, Object)"
  })
  public void testNewFactoryWithMatchRawType_thenReturnCreateGsonAndObjectIsNull() {
    // Arrange
    Class<?> type = ReflectionHelperTestFactory.createStringClass();
    TypeToken<?> exactType = TypeToken.get(type);

    // Act
    TypeAdapterFactory actualNewFactoryWithMatchRawTypeResult =
        TreeTypeAdapter.newFactoryWithMatchRawType(exactType, mock(JsonSerializer.class));
    Gson gson = new Gson();
    Class<Object> type2 = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type2);

    // Assert
    assertNull(actualNewFactoryWithMatchRawTypeResult.create(gson, getResult));
  }

  /**
   * Test {@link TreeTypeAdapter#newFactoryWithMatchRawType(TypeToken, Object)}.
   *
   * <ul>
   *   <li>When {@link JsonDeserializer}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#newFactoryWithMatchRawType(TypeToken, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "TypeAdapterFactory TreeTypeAdapter.newFactoryWithMatchRawType(TypeToken, Object)"
  })
  public void testNewFactoryWithMatchRawType_whenJsonDeserializer() {
    // Arrange
    Class<?> type = ReflectionHelperTestFactory.createStringClass();
    TypeToken<?> exactType = TypeToken.get(type);

    // Act
    TypeAdapterFactory actualNewFactoryWithMatchRawTypeResult =
        TreeTypeAdapter.newFactoryWithMatchRawType(exactType, mock(JsonDeserializer.class));
    Gson gson = new Gson();
    Class<Object> type2 = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type2);

    // Assert
    assertNull(actualNewFactoryWithMatchRawTypeResult.create(gson, getResult));
  }

  /**
   * Test {@link TreeTypeAdapter#newTypeHierarchyFactory(Class, Object)}.
   *
   * <ul>
   *   <li>Then return create {@link Gson#Gson()} and {@link Object} is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#newTypeHierarchyFactory(Class, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapterFactory TreeTypeAdapter.newTypeHierarchyFactory(Class, Object)"})
  public void testNewTypeHierarchyFactory_thenReturnCreateGsonAndObjectIsNull() {
    // Arrange
    Class<?> hierarchyType = ReflectionHelperTestFactory.createStringClass();

    // Act
    TypeAdapterFactory actualNewTypeHierarchyFactoryResult =
        TreeTypeAdapter.newTypeHierarchyFactory(hierarchyType, mock(JsonSerializer.class));
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type);

    // Assert
    assertNull(actualNewTypeHierarchyFactoryResult.create(gson, getResult));
  }

  /**
   * Test {@link TreeTypeAdapter#newTypeHierarchyFactory(Class, Object)}.
   *
   * <ul>
   *   <li>When {@link JsonDeserializer}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#newTypeHierarchyFactory(Class, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapterFactory TreeTypeAdapter.newTypeHierarchyFactory(Class, Object)"})
  public void testNewTypeHierarchyFactory_whenJsonDeserializer() {
    // Arrange
    Class<?> hierarchyType = ReflectionHelperTestFactory.createStringClass();

    // Act
    TypeAdapterFactory actualNewTypeHierarchyFactoryResult =
        TreeTypeAdapter.newTypeHierarchyFactory(hierarchyType, mock(JsonDeserializer.class));
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type);

    // Assert
    assertNull(actualNewTypeHierarchyFactoryResult.create(gson, getResult));
  }
}
