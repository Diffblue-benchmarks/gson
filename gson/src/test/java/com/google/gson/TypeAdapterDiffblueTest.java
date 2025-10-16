package com.google.gson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.Gson.FutureTypeAdapter;
import com.google.gson.internal.bind.ArrayTypeAdapter;
import com.google.gson.internal.bind.ObjectTypeAdapter;
import com.google.gson.internal.bind.TreeTypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.PipedWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.lang.reflect.Type;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class TypeAdapterDiffblueTest {
  /**
   * Test {@link TypeAdapter#toJson(Writer, Object)} with {@code out}, {@code value}.
   *
   * <ul>
   *   <li>Given {@link ObjectTypeAdapter} {@link ObjectTypeAdapter#write(JsonWriter, Object)} does
   *       nothing.
   *   <li>Then calls {@link ObjectTypeAdapter#write(JsonWriter, Object)}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#toJson(Writer, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapter.toJson(Writer, Object)"})
  public void testToJsonWithOutValue_givenObjectTypeAdapterWriteDoesNothing_thenCallsWrite()
      throws IOException {
    // Arrange
    ObjectTypeAdapter typeAdapter = mock(ObjectTypeAdapter.class);
    doNothing().when(typeAdapter).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    futureTypeAdapter.toJson(new PipedWriter(), "Value");

    // Assert
    verify(typeAdapter).write(isA(JsonWriter.class), isA(Object.class));
  }

  /**
   * Test {@link TypeAdapter#toJson(Object)} with {@code value}.
   *
   * <ul>
   *   <li>Given {@code Object}.
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#toJson(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String TypeAdapter.toJson(Object)"})
  public void testToJsonWithValue_givenJavaLangObject_whenNull_thenReturnNull() {
    // Arrange
    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    Gson context = new Gson();
    FutureTypeAdapter<Object> componentTypeAdapter = new FutureTypeAdapter<>();
    Class<Object> componentType = Object.class;

    ArrayTypeAdapter<Object> typeAdapter =
        new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType);
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act and Assert
    assertEquals("null", futureTypeAdapter.toJson(null));
  }

  /**
   * Test {@link TypeAdapter#toJson(Object)} with {@code value}.
   *
   * <ul>
   *   <li>Given {@link ObjectTypeAdapter} {@link ObjectTypeAdapter#write(JsonWriter, Object)} does
   *       nothing.
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#toJson(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String TypeAdapter.toJson(Object)"})
  public void testToJsonWithValue_givenObjectTypeAdapterWriteDoesNothing_thenReturnEmptyString()
      throws IOException {
    // Arrange
    ObjectTypeAdapter typeAdapter = mock(ObjectTypeAdapter.class);
    doNothing().when(typeAdapter).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    String actualToJsonResult = futureTypeAdapter.toJson("Value");

    // Assert
    verify(typeAdapter).write(isA(JsonWriter.class), isA(Object.class));
    assertEquals("", actualToJsonResult);
  }

  /**
   * Test {@link TypeAdapter#toJson(Object)} with {@code value}.
   *
   * <ul>
   *   <li>Then throw {@link JsonIOException}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#toJson(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String TypeAdapter.toJson(Object)"})
  public void testToJsonWithValue_thenThrowJsonIOException() throws IOException {
    // Arrange
    ObjectTypeAdapter typeAdapter = mock(ObjectTypeAdapter.class);
    doThrow(new IOException())
        .when(typeAdapter)
        .write(Mockito.<JsonWriter>any(), Mockito.<Object>any());

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act and Assert
    assertThrows(JsonIOException.class, () -> futureTypeAdapter.toJson("Value"));
    verify(typeAdapter).write(isA(JsonWriter.class), isA(Object.class));
  }

  /**
   * Test {@link TypeAdapter#toJsonTree(Object)}.
   *
   * <ul>
   *   <li>Given {@code Object}.
   *   <li>When {@code null}.
   *   <li>Then return {@link JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#toJsonTree(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement TypeAdapter.toJsonTree(Object)"})
  public void testToJsonTree_givenJavaLangObject_whenNull_thenReturnInstance() {
    // Arrange
    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    Gson context = new Gson();
    FutureTypeAdapter<Object> componentTypeAdapter = new FutureTypeAdapter<>();
    Class<Object> componentType = Object.class;

    ArrayTypeAdapter<Object> typeAdapter =
        new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType);
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    JsonElement actualToJsonTreeResult = futureTypeAdapter.toJsonTree(null);

    // Assert
    assertSame(((JsonNull) actualToJsonTreeResult).INSTANCE, actualToJsonTreeResult);
  }

  /**
   * Test {@link TypeAdapter#toJsonTree(Object)}.
   *
   * <ul>
   *   <li>Given {@link ObjectTypeAdapter} {@link ObjectTypeAdapter#write(JsonWriter, Object)} does
   *       nothing.
   *   <li>Then return {@link JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#toJsonTree(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement TypeAdapter.toJsonTree(Object)"})
  public void testToJsonTree_givenObjectTypeAdapterWriteDoesNothing_thenReturnInstance()
      throws IOException {
    // Arrange
    ObjectTypeAdapter typeAdapter = mock(ObjectTypeAdapter.class);
    doNothing().when(typeAdapter).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    JsonElement actualToJsonTreeResult = futureTypeAdapter.toJsonTree("Value");

    // Assert
    verify(typeAdapter).write(isA(JsonWriter.class), isA(Object.class));
    assertSame(((JsonNull) actualToJsonTreeResult).INSTANCE, actualToJsonTreeResult);
  }

  /**
   * Test {@link TypeAdapter#toJsonTree(Object)}.
   *
   * <ul>
   *   <li>Then throw {@link JsonIOException}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#toJsonTree(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement TypeAdapter.toJsonTree(Object)"})
  public void testToJsonTree_thenThrowJsonIOException() throws IOException {
    // Arrange
    ObjectTypeAdapter typeAdapter = mock(ObjectTypeAdapter.class);
    doThrow(new IOException())
        .when(typeAdapter)
        .write(Mockito.<JsonWriter>any(), Mockito.<Object>any());

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act and Assert
    assertThrows(JsonIOException.class, () -> futureTypeAdapter.toJsonTree("Value"));
    verify(typeAdapter).write(isA(JsonWriter.class), isA(Object.class));
  }

  /**
   * Test {@link TypeAdapter#fromJson(Reader)} with {@code in}.
   *
   * <ul>
   *   <li>Given {@link ObjectTypeAdapter} {@link ObjectTypeAdapter#read(JsonReader)} return {@code
   *       Read}.
   *   <li>Then return {@code Read}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#fromJson(Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapter.fromJson(Reader)"})
  public void testFromJsonWithIn_givenObjectTypeAdapterReadReturnRead_thenReturnRead()
      throws IOException {
    // Arrange
    ObjectTypeAdapter typeAdapter = mock(ObjectTypeAdapter.class);
    when(typeAdapter.read(Mockito.<JsonReader>any())).thenReturn("Read");

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    Object actualFromJsonResult =
        futureTypeAdapter.fromJson(new StringReader(Boolean.FALSE.toString()));

    // Assert
    verify(typeAdapter).read(isA(JsonReader.class));
    assertEquals("Read", actualFromJsonResult);
  }

  /**
   * Test {@link TypeAdapter#fromJson(Reader)} with {@code in}.
   *
   * <ul>
   *   <li>Then return {@code Deserialize}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#fromJson(Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapter.fromJson(Reader)"})
  public void testFromJsonWithIn_thenReturnDeserialize() throws JsonParseException, IOException {
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

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    Object actualFromJsonResult =
        futureTypeAdapter.fromJson(new StringReader(Boolean.FALSE.toString()));

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertEquals("Deserialize", actualFromJsonResult);
  }

  /**
   * Test {@link TypeAdapter#fromJson(String)} with {@code json}.
   *
   * <ul>
   *   <li>Given {@link ObjectTypeAdapter} {@link ObjectTypeAdapter#read(JsonReader)} return {@code
   *       Read}.
   *   <li>When {@code 42}.
   *   <li>Then return {@code Read}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#fromJson(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapter.fromJson(String)"})
  public void testFromJsonWithJson_givenObjectTypeAdapterReadReturnRead_when42_thenReturnRead()
      throws IOException {
    // Arrange
    ObjectTypeAdapter typeAdapter = mock(ObjectTypeAdapter.class);
    when(typeAdapter.read(Mockito.<JsonReader>any())).thenReturn("Read");

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    Object actualFromJsonResult = futureTypeAdapter.fromJson("42");

    // Assert
    verify(typeAdapter).read(isA(JsonReader.class));
    assertEquals("Read", actualFromJsonResult);
  }

  /**
   * Test {@link TypeAdapter#fromJson(String)} with {@code json}.
   *
   * <ul>
   *   <li>Then return {@code Deserialize}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#fromJson(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapter.fromJson(String)"})
  public void testFromJsonWithJson_thenReturnDeserialize() throws JsonParseException, IOException {
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

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    Object actualFromJsonResult = futureTypeAdapter.fromJson("42");

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertEquals("Deserialize", actualFromJsonResult);
  }

  /**
   * Test {@link TypeAdapter#fromJsonTree(JsonElement)}.
   *
   * <ul>
   *   <li>Given {@link ObjectTypeAdapter} {@link ObjectTypeAdapter#read(JsonReader)} return {@code
   *       Read}.
   *   <li>Then return {@code Read}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#fromJsonTree(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapter.fromJsonTree(JsonElement)"})
  public void testFromJsonTree_givenObjectTypeAdapterReadReturnRead_thenReturnRead()
      throws IOException {
    // Arrange
    ObjectTypeAdapter typeAdapter = mock(ObjectTypeAdapter.class);
    when(typeAdapter.read(Mockito.<JsonReader>any())).thenReturn("Read");

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    Object actualFromJsonTreeResult = futureTypeAdapter.fromJsonTree(new JsonObject());

    // Assert
    verify(typeAdapter).read(isA(JsonReader.class));
    assertEquals("Read", actualFromJsonTreeResult);
  }

  /**
   * Test {@link TypeAdapter#fromJsonTree(JsonElement)}.
   *
   * <ul>
   *   <li>Then return {@code Deserialize}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#fromJsonTree(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapter.fromJsonTree(JsonElement)"})
  public void testFromJsonTree_thenReturnDeserialize() throws JsonParseException {
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

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    Object actualFromJsonTreeResult = futureTypeAdapter.fromJsonTree(new JsonObject());

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertEquals("Deserialize", actualFromJsonTreeResult);
  }

  /**
   * Test {@link TypeAdapter#fromJsonTree(JsonElement)}.
   *
   * <ul>
   *   <li>Then throw {@link JsonIOException}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#fromJsonTree(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapter.fromJsonTree(JsonElement)"})
  public void testFromJsonTree_thenThrowJsonIOException() throws IOException {
    // Arrange
    ObjectTypeAdapter typeAdapter = mock(ObjectTypeAdapter.class);
    when(typeAdapter.read(Mockito.<JsonReader>any())).thenThrow(new IOException());

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act and Assert
    assertThrows(JsonIOException.class, () -> futureTypeAdapter.fromJsonTree(new JsonObject()));
    verify(typeAdapter).read(isA(JsonReader.class));
  }

  /**
   * Test {@link TypeAdapter#fromJsonTree(JsonElement)}.
   *
   * <ul>
   *   <li>When {@link JsonNull#INSTANCE}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#fromJsonTree(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapter.fromJsonTree(JsonElement)"})
  public void testFromJsonTree_whenInstance_thenReturnNull() {
    // Arrange
    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    Gson context = new Gson();
    FutureTypeAdapter<Object> componentTypeAdapter = new FutureTypeAdapter<>();
    Class<Object> componentType = Object.class;

    ArrayTypeAdapter<Object> typeAdapter =
        new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType);
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act and Assert
    assertNull(futureTypeAdapter.fromJsonTree(JsonNull.INSTANCE));
  }

  /**
   * Test {@link TypeAdapter#fromJsonTree(JsonElement)}.
   *
   * <ul>
   *   <li>When {@link JsonArray#JsonArray(int)} with capacity is three.
   *   <li>Then return {@code Object[]}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#fromJsonTree(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapter.fromJsonTree(JsonElement)"})
  public void testFromJsonTree_whenJsonArrayWithCapacityIsThree_thenReturnObject() {
    // Arrange
    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    Gson context = new Gson();
    FutureTypeAdapter<Object> componentTypeAdapter = new FutureTypeAdapter<>();
    Class<Object> componentType = Object.class;

    ArrayTypeAdapter<Object> typeAdapter =
        new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType);
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    Object actualFromJsonTreeResult = futureTypeAdapter.fromJsonTree(new JsonArray(3));

    // Assert
    assertTrue(actualFromJsonTreeResult instanceof Object[]);
    assertEquals(0, ((Object[]) actualFromJsonTreeResult).length);
  }
}
