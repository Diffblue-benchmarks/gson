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
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.reflect.ReflectionHelperDiffblueTestFactory;
import com.google.gson.internal.sql.SqlTimestampTypeAdapterDiffblueTestFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Type;
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
    "void TreeTypeAdapter.<init>(JsonSerializer, JsonDeserializer, Gson, TypeToken,"
        + " TypeAdapterFactory)"
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
    "void TreeTypeAdapter.<init>(JsonSerializer, JsonDeserializer, Gson, TypeToken,"
        + " TypeAdapterFactory, boolean)"
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
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
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

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    JsonTreeReader in =
        new JsonTreeReader(JsonArrayDiffblueTestFactory.createJsonArrayWithElements());

    // Act
    Object actualReadResult = treeTypeAdapter.read(in);

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertEquals(0, in.getStackSize());
    assertFalse(in.hasNext());
    assertSame(createSqlTimestampTypeAdapterResult, actualReadResult);
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
    assertEquals(0, in.getStackSize());
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
    assertEquals(0, in.getStackSize());
    assertFalse(in.hasNext());
    assertTrue((Boolean) actualReadResult);
  }

  /**
   * Test {@link TreeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_whenStringReaderWith42()
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
    assertSame(createSqlTimestampTypeAdapterResult, actualReadResult);
  }

  /**
   * Test {@link TreeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code at line}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_whenStringReaderWithAtLine()
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

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    JsonReader in = new JsonReader(new StringReader(" at line "));
    in.setStrictness(Strictness.LENIENT);

    // Act
    Object actualReadResult = treeTypeAdapter.read(in);

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertSame(createSqlTimestampTypeAdapterResult, actualReadResult);
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
   *   <li>When {@link StringReader#StringReader(String)} with {@code End of input}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_whenStringReaderWithEndOfInput()
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

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    JsonReader in = new JsonReader(new StringReader("End of input"));
    in.setStrictness(Strictness.LENIENT);

    // Act
    Object actualReadResult = treeTypeAdapter.read(in);

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertSame(createSqlTimestampTypeAdapterResult, actualReadResult);
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
    assertSame(createSqlTimestampTypeAdapterResult, actualReadResult);
  }

  /**
   * Test {@link TreeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_whenStringReaderWithFoo()
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
    assertSame(createSqlTimestampTypeAdapterResult, actualReadResult);
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
    assertSame(createSqlTimestampTypeAdapterResult, actualReadResult);
  }

  /**
   * Test {@link TreeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Then {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE} StackSize is zero.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
  public void testRead_thenJsonTreeReaderWithElementIsInstanceStackSizeIsZero() throws IOException {
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
    assertEquals(0, in.getStackSize());
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link TreeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Then {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonObject} (default constructor) StackSize is zero.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
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
    assertEquals(0, in.getStackSize());
    assertFalse(in.hasNext());
    assertSame(createSqlTimestampTypeAdapterResult, actualReadResult);
  }

  /**
   * Test {@link TreeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Then {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonPrimitive#JsonPrimitive(String)} StackSize is zero.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
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
    assertEquals(0, in.getStackSize());
    assertFalse(in.hasNext());
    assertSame(createSqlTimestampTypeAdapterResult, actualReadResult);
  }

  /**
   * Test {@link TreeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Then {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonPrimitive#JsonPrimitive(Boolean)} StackSize is zero.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
  public void testRead_thenJsonTreeReaderWithElementIsJsonPrimitiveStackSizeIsZero2()
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
    assertEquals(0, in.getStackSize());
    assertFalse(in.hasNext());
    assertSame(createSqlTimestampTypeAdapterResult, actualReadResult);
  }

  /**
   * Test {@link TreeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then return createSqlTimestampTypeAdapter.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
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

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    // Act
    Object actualReadResult = treeTypeAdapter.read(new JsonReader(new StringReader("42")));

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertSame(createSqlTimestampTypeAdapterResult, actualReadResult);
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
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
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
    assertSame(createSqlTimestampTypeAdapterResult, actualReadResult);
  }

  /**
   * Test {@link TreeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code FALSE}.
   *   <li>Then return createSqlTimestampTypeAdapter.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
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

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    // Act
    Object actualReadResult = treeTypeAdapter.read(new JsonReader(new StringReader("FALSE")));

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertSame(createSqlTimestampTypeAdapterResult, actualReadResult);
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

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    treeTypeAdapter.write(
        out, SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
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
  public void testWrite2() throws IOException {
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

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class), false);
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    treeTypeAdapter.write(
        out, SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonArray);
    assertEquals(createJsonArrayWithElementsResult, getResult);
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add createJsonArrayWithElements.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
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

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    treeTypeAdapter.write(
        out, SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_givenCreateJsonArrayWithElementsAddFalse() throws IOException {
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

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    treeTypeAdapter.write(
        out, SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add {@link JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_givenCreateJsonArrayWithElementsAddInstance() throws IOException {
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

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    treeTypeAdapter.write(
        out, SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add {@link JsonObject} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_givenCreateJsonArrayWithElementsAddJsonObject() throws IOException {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(new JsonObject());

    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(createJsonArrayWithElementsResult);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    treeTypeAdapter.write(
        out, SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_givenCreateJsonArrayWithElementsAddTrue() throws IOException {
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

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    treeTypeAdapter.write(
        out, SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add valueOf one.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_givenCreateJsonArrayWithElementsAddValueOfOne() throws IOException {
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

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    treeTypeAdapter.write(
        out, SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code 42} and
   *       createJsonArrayWithElements.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_givenJsonObjectAdd42AndCreateJsonArrayWithElements() throws IOException {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("42", JsonArrayDiffblueTestFactory.createJsonArrayWithElements());

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
    treeTypeAdapter.write(
        out, SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code name == null} and
   *       createJsonArrayWithElements.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_givenJsonObjectAddNameNullAndCreateJsonArrayWithElements()
      throws IOException {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("name == null", JsonArrayDiffblueTestFactory.createJsonArrayWithElements());
    jsonObject.add("42", JsonArrayDiffblueTestFactory.createJsonArrayWithElements());

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
    treeTypeAdapter.write(
        out, SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code Property} and
   *       createJsonArrayWithElements.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_givenJsonObjectAddPropertyAndCreateJsonArrayWithElements()
      throws IOException {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", JsonArrayDiffblueTestFactory.createJsonArrayWithElements());

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
    treeTypeAdapter.write(
        out, SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter());

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
  public void testWrite_givenJsonObjectAddPropertyAndInstance() throws IOException {
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
    treeTypeAdapter.write(
        out, SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter());

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

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class), true);
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    treeTypeAdapter.write(
        out, SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter());

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
   *       JsonSerializationContext)} return {@link JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
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

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    treeTypeAdapter.write(
        out, SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter());

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

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class), true);
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    treeTypeAdapter.write(
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

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    treeTypeAdapter.write(
        out, SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter());

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

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    treeTypeAdapter.write(
        out, SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter());

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

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    treeTypeAdapter.write(
        out, SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter());

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
      throws IOException {
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
    treeTypeAdapter.write(
        out, SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
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

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    JsonWriter out = new JsonWriter(new StringWriter());
    out.setHtmlSafe(true);

    // Act
    treeTypeAdapter.write(
        out, SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
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

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class), true);
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    treeTypeAdapter.write(
        out, SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter());

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
  public void testWrite_thenJsonTreeWriterIsJsonPrimitiveWithString() throws IOException {
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
    treeTypeAdapter.write(
        out, SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter());

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
   *   <li>Then {@link JsonTreeWriter} (default constructor) {@link JsonObject}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_thenJsonTreeWriterJsonObject() throws IOException {
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
    treeTypeAdapter.write(
        out, SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter());

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
   *   <li>When {@link JsonTreeWriter} (default constructor).
   *   <li>Then {@link JsonTreeWriter} (default constructor) is createJsonArrayWithElements.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_whenJsonTreeWriter_thenJsonTreeWriterIsCreateJsonArrayWithElements()
      throws IOException {
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

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class), true);
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    treeTypeAdapter.write(
        out, SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter());

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonArray);
    assertEquals(createJsonArrayWithElementsResult, getResult);
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
    assertFalse(getResult.isJsonArray());
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
    Class<?> type = ReflectionHelperDiffblueTestFactory.createNonNullClass();
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
    Class<?> type = ReflectionHelperDiffblueTestFactory.createNonNullClass();
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
    Class<?> type = ReflectionHelperDiffblueTestFactory.createNonNullClass();
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
    Class<?> type = ReflectionHelperDiffblueTestFactory.createNonNullClass();
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
    Class<?> type = ReflectionHelperDiffblueTestFactory.createNonNullClass();
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
    Class<?> hierarchyType = ReflectionHelperDiffblueTestFactory.createNonNullClass();

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
    Class<?> hierarchyType = ReflectionHelperDiffblueTestFactory.createNonNullClass();

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
