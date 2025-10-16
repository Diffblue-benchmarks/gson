package com.google.gson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
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
import com.google.gson.internal.reflect.ReflectionHelperTestFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.PipedWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.lang.reflect.Field;
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
      throws IOException, NoSuchFieldException {
    // Arrange
    ObjectTypeAdapter typeAdapter = mock(ObjectTypeAdapter.class);
    doNothing().when(typeAdapter).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);
    PipedWriter out = new PipedWriter();

    // Act
    futureTypeAdapter.toJson(out, ReflectionHelperTestFactory.createPublicField());

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
      throws IOException, NoSuchFieldException {
    // Arrange
    ObjectTypeAdapter typeAdapter = mock(ObjectTypeAdapter.class);
    doNothing().when(typeAdapter).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    String actualToJsonResult =
        futureTypeAdapter.toJson(ReflectionHelperTestFactory.createPublicField());

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
  public void testToJsonWithValue_thenThrowJsonIOException()
      throws IOException, NoSuchFieldException {
    // Arrange
    ObjectTypeAdapter typeAdapter = mock(ObjectTypeAdapter.class);
    doThrow(new IOException())
        .when(typeAdapter)
        .write(Mockito.<JsonWriter>any(), Mockito.<Object>any());

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act and Assert
    assertThrows(
        JsonIOException.class,
        () -> futureTypeAdapter.toJson(ReflectionHelperTestFactory.createPublicField()));
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
      throws IOException, NoSuchFieldException {
    // Arrange
    ObjectTypeAdapter typeAdapter = mock(ObjectTypeAdapter.class);
    doNothing().when(typeAdapter).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    JsonElement actualToJsonTreeResult =
        futureTypeAdapter.toJsonTree(ReflectionHelperTestFactory.createPublicField());

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
  public void testToJsonTree_thenThrowJsonIOException() throws IOException, NoSuchFieldException {
    // Arrange
    ObjectTypeAdapter typeAdapter = mock(ObjectTypeAdapter.class);
    doThrow(new IOException())
        .when(typeAdapter)
        .write(Mockito.<JsonWriter>any(), Mockito.<Object>any());

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act and Assert
    assertThrows(
        JsonIOException.class,
        () -> futureTypeAdapter.toJsonTree(ReflectionHelperTestFactory.createPublicField()));
    verify(typeAdapter).write(isA(JsonWriter.class), isA(Object.class));
  }

  /**
   * Test {@link TypeAdapter#fromJson(Reader)} with {@code in}.
   *
   * <ul>
   *   <li>Then calls {@link JsonDeserializer#deserialize(JsonElement, Type,
   *       JsonDeserializationContext)}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#fromJson(Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapter.fromJson(Reader)"})
  public void testFromJsonWithIn_thenCallsDeserialize()
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
    assertSame(createPublicFieldResult, actualFromJsonResult);
  }

  /**
   * Test {@link TypeAdapter#fromJson(Reader)} with {@code in}.
   *
   * <ul>
   *   <li>Then calls {@link ObjectTypeAdapter#read(JsonReader)}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#fromJson(Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapter.fromJson(Reader)"})
  public void testFromJsonWithIn_thenCallsRead() throws IOException, NoSuchFieldException {
    // Arrange
    ObjectTypeAdapter typeAdapter = mock(ObjectTypeAdapter.class);
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();
    when(typeAdapter.read(Mockito.<JsonReader>any())).thenReturn(createPublicFieldResult);

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    Object actualFromJsonResult =
        futureTypeAdapter.fromJson(new StringReader(Boolean.FALSE.toString()));

    // Assert
    verify(typeAdapter).read(isA(JsonReader.class));
    assertSame(createPublicFieldResult, actualFromJsonResult);
  }

  /**
   * Test {@link TypeAdapter#fromJson(String)} with {@code json}.
   *
   * <ul>
   *   <li>Then calls {@link JsonDeserializer#deserialize(JsonElement, Type,
   *       JsonDeserializationContext)}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#fromJson(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapter.fromJson(String)"})
  public void testFromJsonWithJson_thenCallsDeserialize()
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
    assertSame(createPublicFieldResult, actualFromJsonResult);
  }

  /**
   * Test {@link TypeAdapter#fromJson(String)} with {@code json}.
   *
   * <ul>
   *   <li>Then calls {@link ObjectTypeAdapter#read(JsonReader)}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#fromJson(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapter.fromJson(String)"})
  public void testFromJsonWithJson_thenCallsRead() throws IOException, NoSuchFieldException {
    // Arrange
    ObjectTypeAdapter typeAdapter = mock(ObjectTypeAdapter.class);
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();
    when(typeAdapter.read(Mockito.<JsonReader>any())).thenReturn(createPublicFieldResult);

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    Object actualFromJsonResult = futureTypeAdapter.fromJson("42");

    // Assert
    verify(typeAdapter).read(isA(JsonReader.class));
    assertSame(createPublicFieldResult, actualFromJsonResult);
  }

  /**
   * Test {@link TypeAdapter#fromJsonTree(JsonElement)}.
   *
   * <p>Method under test: {@link TypeAdapter#fromJsonTree(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapter.fromJsonTree(JsonElement)"})
  public void testFromJsonTree() throws JsonParseException, NoSuchFieldException {
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

    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    Object actualFromJsonTreeResult =
        futureTypeAdapter.fromJsonTree(JsonArrayTestFactory.createJsonArrayWithOneElement());

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertSame(createPublicFieldResult, actualFromJsonTreeResult);
  }

  /**
   * Test {@link TypeAdapter#fromJsonTree(JsonElement)}.
   *
   * <ul>
   *   <li>Given {@link ObjectTypeAdapter} {@link ObjectTypeAdapter#read(JsonReader)} return
   *       createPublicField.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#fromJsonTree(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapter.fromJsonTree(JsonElement)"})
  public void testFromJsonTree_givenObjectTypeAdapterReadReturnCreatePublicField()
      throws IOException, NoSuchFieldException {
    // Arrange
    ObjectTypeAdapter typeAdapter = mock(ObjectTypeAdapter.class);
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();
    when(typeAdapter.read(Mockito.<JsonReader>any())).thenReturn(createPublicFieldResult);

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    Object actualFromJsonTreeResult =
        futureTypeAdapter.fromJsonTree(JsonArrayTestFactory.createJsonArrayWithOneElement());

    // Assert
    verify(typeAdapter).read(isA(JsonReader.class));
    assertSame(createPublicFieldResult, actualFromJsonTreeResult);
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
    assertThrows(
        JsonIOException.class,
        () -> futureTypeAdapter.fromJsonTree(JsonArrayTestFactory.createJsonArrayWithOneElement()));
    verify(typeAdapter).read(isA(JsonReader.class));
  }

  /**
   * Test {@link TypeAdapter#nullSafe()}.
   *
   * <p>Method under test: {@link TypeAdapter#nullSafe()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapter TypeAdapter.nullSafe()"})
  public void testNullSafe() {
    // Arrange
    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();

    // Act and Assert
    assertEquals("null", futureTypeAdapter.nullSafe().toJson(null));
  }
}
