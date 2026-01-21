package com.google.gson.internal.bind;

import static org.junit.Assert.assertArrayEquals;
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
import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
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
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.reflect.ReflectionHelperFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.util.List;
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
    Excluder excluderResult = gson2.excluder();
    assertEquals(-1.0d, excluderResult.getVersion(), 0.0);
    assertEquals(136, excluderResult.getModifiers());
    assertFalse(gson2.serializeNulls());
    assertFalse(excluderResult.isRequireExpose());
    assertTrue(gson2.htmlSafe());
    assertTrue(excluderResult.isSerializeInnerClasses());
    List<ExclusionStrategy> deserializationStrategies =
        excluderResult.getDeserializationStrategies();
    assertTrue(deserializationStrategies.isEmpty());
    TypeAdapter<Object> actualSerializationDelegate =
        actualTreeTypeAdapter.getSerializationDelegate();
    assertSame(actualTreeTypeAdapter, actualSerializationDelegate);
    assertSame(deserializationStrategies, excluderResult.getSerializationStrategies());
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
    Excluder excluderResult = gson2.excluder();
    assertEquals(-1.0d, excluderResult.getVersion(), 0.0);
    assertEquals(136, excluderResult.getModifiers());
    assertFalse(gson2.serializeNulls());
    assertFalse(excluderResult.isRequireExpose());
    assertTrue(gson2.htmlSafe());
    assertTrue(excluderResult.isSerializeInnerClasses());
    List<ExclusionStrategy> deserializationStrategies =
        excluderResult.getDeserializationStrategies();
    assertTrue(deserializationStrategies.isEmpty());
    TypeAdapter<Object> actualSerializationDelegate =
        actualTreeTypeAdapter.getSerializationDelegate();
    assertSame(actualTreeTypeAdapter, actualSerializationDelegate);
    assertSame(deserializationStrategies, excluderResult.getSerializationStrategies());
  }

  /**
   * Test {@link TreeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LEGACY_STRICT}.
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
  public void testRead_givenLegacyStrict_whenStringReaderWithEmptyString_thenReturnNull()
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
    in.setStrictness(Strictness.LEGACY_STRICT);

    // Act
    Object actualReadResult = treeTypeAdapter.read(in);

    // Assert
    assertNull(actualReadResult);
  }

  /**
   * Test {@link TreeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LEGACY_STRICT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
  public void testRead_givenLegacyStrict_whenStringReaderWithFalseToString()
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

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    StringReader in = new StringReader(Boolean.FALSE.toString());

    JsonReader in2 = new JsonReader(in);
    in2.setStrictness(Strictness.LEGACY_STRICT);

    // Act
    Object actualReadResult = treeTypeAdapter.read(in2);

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertEquals("Deserialize", actualReadResult);
  }

  /**
   * Test {@link TreeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then return {@code Deserialize}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_whenStringReaderWith42_thenReturnDeserialize()
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
    assertEquals("Deserialize", actualReadResult);
  }

  /**
   * Test {@link TreeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code at line}.
   *   <li>Then return {@code Deserialize}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_whenStringReaderWithAtLine_thenReturnDeserialize()
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
    assertEquals("Deserialize", actualReadResult);
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
   *   <li>Then return {@code Deserialize}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_whenStringReaderWithEndOfInput_thenReturnDeserialize()
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
    assertEquals("Deserialize", actualReadResult);
  }

  /**
   * Test {@link TreeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then return {@code Deserialize}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_whenStringReaderWithFalseToString_thenReturnDeserialize()
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
    assertEquals("Deserialize", actualReadResult);
  }

  /**
   * Test {@link TreeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code FALSE}.
   *   <li>Then return {@code Deserialize}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_whenStringReaderWithFalse_thenReturnDeserialize()
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

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    JsonReader in = new JsonReader(new StringReader("FALSE"));
    in.setStrictness(Strictness.LENIENT);

    // Act
    Object actualReadResult = treeTypeAdapter.read(in);

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertEquals("Deserialize", actualReadResult);
  }

  /**
   * Test {@link TreeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code falsefalse}.
   *   <li>Then return {@code Deserialize}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_whenStringReaderWithFalsefalse_thenReturnDeserialize()
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

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    JsonReader in = new JsonReader(new StringReader("falsefalse"));
    in.setStrictness(Strictness.LENIENT);

    // Act
    Object actualReadResult = treeTypeAdapter.read(in);

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertEquals("Deserialize", actualReadResult);
  }

  /**
   * Test {@link TreeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then return {@code Deserialize}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_whenStringReaderWithFoo_thenReturnDeserialize()
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
    assertEquals("Deserialize", actualReadResult);
  }

  /**
   * Test {@link TreeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code See}.
   *   <li>Then return {@code Deserialize}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_whenStringReaderWithSee_thenReturnDeserialize()
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

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    JsonReader in = new JsonReader(new StringReader("\nSee "));
    in.setStrictness(Strictness.LENIENT);

    // Act
    Object actualReadResult = treeTypeAdapter.read(in);

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertEquals("Deserialize", actualReadResult);
  }

  /**
   * Test {@link TreeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code STRICT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then return {@code Deserialize}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
  public void testRead_givenStrict_whenStringReaderWithFalseToString_thenReturnDeserialize()
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
    assertEquals("Deserialize", actualReadResult);
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
   *   <li>When {@link JsonReader#JsonReader(Reader)} with in is {@link
   *       CharArrayReader#CharArrayReader(char[])} Strictness is {@code LENIENT}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
  public void testRead_whenJsonReaderWithInIsCharArrayReaderStrictnessIsLenient()
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

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    CharArrayReader in = new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray());

    JsonReader in2 = new JsonReader(in);
    in2.setStrictness(Strictness.LENIENT);

    // Act
    Object actualReadResult = treeTypeAdapter.read(in2);

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertEquals("Deserialize", actualReadResult);
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
        .thenReturn(new JsonArray(3));
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    treeTypeAdapter.write(out, "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertEquals("[]", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
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
    treeTypeAdapter.write(out, "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertEquals("{}", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
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
        .thenReturn(new JsonPrimitive("null"));
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
    treeTypeAdapter.write(out, "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertEquals("\"null\"", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Then {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()} Out toString is {@code [1,true]}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_thenJsonWriterWithOutIsStringWriterOutToStringIs1True() throws IOException {
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

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    treeTypeAdapter.write(out, "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertEquals("[1,true]", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Then {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()} Out toString is {@code [false,true]}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_thenJsonWriterWithOutIsStringWriterOutToStringIsFalseTrue()
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

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    treeTypeAdapter.write(out, "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertEquals("[false,true]", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Then {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()} Out toString is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_thenJsonWriterWithOutIsStringWriterOutToStringIsNull() throws IOException {
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
    treeTypeAdapter.write(out, "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertEquals("null", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Then {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()} Out toString is {@code "null"}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_thenJsonWriterWithOutIsStringWriterOutToStringIsNull2() throws IOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonPrimitive("null"));
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    treeTypeAdapter.write(out, "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertEquals("\"null\"", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Then {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()} Out toString is {@code {"null":null,"Property":null}}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_thenJsonWriterWithOutIsStringWriterOutToStringIsNullNullPropertyNull()
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

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    treeTypeAdapter.write(out, "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertEquals("{\"null\":null,\"Property\":null}", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Then {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()} Out toString is {@code [null,true]}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_thenJsonWriterWithOutIsStringWriterOutToStringIsNullTrue()
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

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    treeTypeAdapter.write(out, "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertEquals("[null,true]", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Then {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()} Out toString is {@code {"Property":null}}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_thenJsonWriterWithOutIsStringWriterOutToStringIsPropertyNull()
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
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    treeTypeAdapter.write(out, "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertEquals("{\"Property\":null}", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Then {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()} Out toString is {@code [true]}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_thenJsonWriterWithOutIsStringWriterOutToStringIsTrue() throws IOException {
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

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    treeTypeAdapter.write(out, "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertEquals("[true]", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Then {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()} Out toString is {@link Boolean#TRUE} toString.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_thenJsonWriterWithOutIsStringWriterOutToStringIsTrueToString()
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
    treeTypeAdapter.write(out, "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertEquals(Boolean.TRUE.toString(), out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Then {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()} Out toString is {@code "\u0001"}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_thenJsonWriterWithOutIsStringWriterOutToStringIsU0001() throws IOException {
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
    treeTypeAdapter.write(out, "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertEquals("\"\\u0001\"", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Then {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()} Out toString is {@code ["\u0003",true]}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_thenJsonWriterWithOutIsStringWriterOutToStringIsU0003True()
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

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    treeTypeAdapter.write(out, "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertEquals("[\"\\u0003\",true]", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>When {@link JsonTreeWriter} (default constructor).
   *   <li>Then {@link JsonTreeWriter} (default constructor) Stack is array of {@code int} with six
   *       and zero.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_whenJsonTreeWriter_thenJsonTreeWriterStackIsArrayOfIntWithSixAndZero()
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
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    treeTypeAdapter.write(out, null);

    // Assert that nothing has changed
    assertArrayEquals(
        new int[] {
          6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()} Out toString is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_whenNull_thenJsonWriterWithOutIsStringWriterOutToStringIsNull()
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
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    treeTypeAdapter.write(out, null);

    // Assert
    assertEquals("null", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
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
    assertEquals("{}", treeTypeAdapter.getSerializationDelegate().toJson("Value"));
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
    assertEquals("{}", treeTypeAdapter.getSerializationDelegate().toJson("Value"));
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
    assertEquals("{}", treeTypeAdapter.getSerializationDelegate().toJson("Value"));
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
    Excluder excluderResult = gson2.excluder();
    assertEquals(-1.0d, excluderResult.getVersion(), 0.0);
    assertEquals(136, excluderResult.getModifiers());
    assertFalse(gson2.serializeNulls());
    assertFalse(excluderResult.isRequireExpose());
    assertTrue(gson2.htmlSafe());
    assertTrue(excluderResult.isSerializeInnerClasses());
    List<ExclusionStrategy> deserializationStrategies =
        excluderResult.getDeserializationStrategies();
    assertTrue(deserializationStrategies.isEmpty());
    assertSame(
        actualCreateResult,
        ((TreeTypeAdapter<Object>) actualCreateResult).getSerializationDelegate());
    assertSame(deserializationStrategies, excluderResult.getSerializationStrategies());
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
    Class<?> type = ReflectionHelperFactory.createRecordClass();
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
    Class<?> type = ReflectionHelperFactory.createRecordClass();
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
    Class<?> type = ReflectionHelperFactory.createRecordClass();
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
    Excluder excluderResult = gson2.excluder();
    assertEquals(-1.0d, excluderResult.getVersion(), 0.0);
    assertEquals(136, excluderResult.getModifiers());
    assertFalse(gson2.serializeNulls());
    assertFalse(excluderResult.isRequireExpose());
    assertTrue(gson2.htmlSafe());
    assertTrue(excluderResult.isSerializeInnerClasses());
    List<ExclusionStrategy> deserializationStrategies =
        excluderResult.getDeserializationStrategies();
    assertTrue(deserializationStrategies.isEmpty());
    assertSame(
        actualCreateResult,
        ((TreeTypeAdapter<Object>) actualCreateResult).getSerializationDelegate());
    assertSame(deserializationStrategies, excluderResult.getSerializationStrategies());
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
    Class<?> type = ReflectionHelperFactory.createRecordClass();
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
    Class<?> type = ReflectionHelperFactory.createRecordClass();
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
    Class<?> hierarchyType = ReflectionHelperFactory.createRecordClass();

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
    Class<?> hierarchyType = ReflectionHelperFactory.createRecordClass();

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
