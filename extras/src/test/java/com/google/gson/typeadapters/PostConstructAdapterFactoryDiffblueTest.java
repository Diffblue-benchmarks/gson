package com.google.gson.typeadapters;

import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.bind.ObjectTypeAdapter;
import com.google.gson.internal.bind.TreeTypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.google.gson.typeadapters.PostConstructAdapterFactory.PostConstructAdapter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Type;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class PostConstructAdapterFactoryDiffblueTest {
  /**
   * Test {@link PostConstructAdapterFactory#create(Gson, TypeToken)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link PostConstructAdapterFactory#create(Gson, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "com.google.gson.TypeAdapter PostConstructAdapterFactory.create(Gson, TypeToken)"
  })
  public void testCreate_whenJavaLangObject_thenReturnNull() {
    // Arrange
    PostConstructAdapterFactory postConstructAdapterFactory = new PostConstructAdapterFactory();
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> type2 = TypeToken.get(type);

    // Act and Assert
    assertNull(postConstructAdapterFactory.create(gson, type2));
  }

  /**
   * Test PostConstructAdapter {@link PostConstructAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@link ObjectTypeAdapter} {@link ObjectTypeAdapter#read(JsonReader)} return {@code
   *       null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link PostConstructAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PostConstructAdapter.read(JsonReader)"})
  public void testPostConstructAdapterRead_givenObjectTypeAdapterReadReturnNull_thenReturnNull()
      throws IOException {
    // Arrange
    ObjectTypeAdapter delegate = mock(ObjectTypeAdapter.class);
    when(delegate.read(Mockito.<JsonReader>any())).thenReturn(null);
    PostConstructAdapter<Object> postConstructAdapter = new PostConstructAdapter<>(delegate, null);

    // Act
    Object actualReadResult = postConstructAdapter.read(new JsonReader(new StringReader("foo")));

    // Assert
    verify(delegate).read(isA(JsonReader.class));
    assertNull(actualReadResult);
  }

  /**
   * Test PostConstructAdapter {@link PostConstructAdapter#write(JsonWriter, Object)}.
   *
   * <p>Method under test: {@link PostConstructAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PostConstructAdapter.write(JsonWriter, Object)"})
  public void testPostConstructAdapterWrite() throws IOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonPrimitive("String"));
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    PostConstructAdapter<Object> postConstructAdapter = new PostConstructAdapter<>(delegate, null);

    // Act
    postConstructAdapter.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test PostConstructAdapter {@link PostConstructAdapter#write(JsonWriter, Object)}.
   *
   * <p>Method under test: {@link PostConstructAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PostConstructAdapter.write(JsonWriter, Object)"})
  public void testPostConstructAdapterWrite2() throws IOException {
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
    PostConstructAdapter<Object> postConstructAdapter = new PostConstructAdapter<>(delegate, null);

    // Act
    postConstructAdapter.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test PostConstructAdapter {@link PostConstructAdapter#write(JsonWriter, Object)}.
   *
   * <p>Method under test: {@link PostConstructAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PostConstructAdapter.write(JsonWriter, Object)"})
  public void testPostConstructAdapterWrite3() throws IOException {
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
    PostConstructAdapter<Object> postConstructAdapter = new PostConstructAdapter<>(delegate, null);

    // Act
    postConstructAdapter.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test PostConstructAdapter {@link PostConstructAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray()} add {@code false}.
   *   <li>Then calls {@link JsonSerializer#serialize(Object, Type, JsonSerializationContext)}.
   * </ul>
   *
   * <p>Method under test: {@link PostConstructAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PostConstructAdapter.write(JsonWriter, Object)"})
  public void testPostConstructAdapterWrite_givenJsonArrayAddFalse_thenCallsSerialize()
      throws IOException {
    // Arrange
    JsonArray jsonArray = new JsonArray();
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
    PostConstructAdapter<Object> postConstructAdapter = new PostConstructAdapter<>(delegate, null);

    // Act
    postConstructAdapter.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test PostConstructAdapter {@link PostConstructAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray()} add {@link JsonArray#JsonArray()}.
   *   <li>Then calls {@link JsonSerializer#serialize(Object, Type, JsonSerializationContext)}.
   * </ul>
   *
   * <p>Method under test: {@link PostConstructAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PostConstructAdapter.write(JsonWriter, Object)"})
  public void testPostConstructAdapterWrite_givenJsonArrayAddJsonArray_thenCallsSerialize()
      throws IOException {
    // Arrange
    JsonArray jsonArray = new JsonArray();
    jsonArray.add(new JsonArray());

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
    PostConstructAdapter<Object> postConstructAdapter = new PostConstructAdapter<>(delegate, null);

    // Act
    postConstructAdapter.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test PostConstructAdapter {@link PostConstructAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray()} add start of heading.
   *   <li>Then calls {@link JsonSerializer#serialize(Object, Type, JsonSerializationContext)}.
   * </ul>
   *
   * <p>Method under test: {@link PostConstructAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PostConstructAdapter.write(JsonWriter, Object)"})
  public void testPostConstructAdapterWrite_givenJsonArrayAddStartOfHeading_thenCallsSerialize()
      throws IOException {
    // Arrange
    JsonArray jsonArray = new JsonArray();
    jsonArray.add('\u0001');

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
    PostConstructAdapter<Object> postConstructAdapter = new PostConstructAdapter<>(delegate, null);

    // Act
    postConstructAdapter.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test PostConstructAdapter {@link PostConstructAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray()} add {@code true}.
   *   <li>Then calls {@link JsonSerializer#serialize(Object, Type, JsonSerializationContext)}.
   * </ul>
   *
   * <p>Method under test: {@link PostConstructAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PostConstructAdapter.write(JsonWriter, Object)"})
  public void testPostConstructAdapterWrite_givenJsonArrayAddTrue_thenCallsSerialize()
      throws IOException {
    // Arrange
    JsonArray jsonArray = new JsonArray();
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
    PostConstructAdapter<Object> postConstructAdapter = new PostConstructAdapter<>(delegate, null);

    // Act
    postConstructAdapter.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test PostConstructAdapter {@link PostConstructAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray()} add valueOf one.
   *   <li>Then calls {@link JsonSerializer#serialize(Object, Type, JsonSerializationContext)}.
   * </ul>
   *
   * <p>Method under test: {@link PostConstructAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PostConstructAdapter.write(JsonWriter, Object)"})
  public void testPostConstructAdapterWrite_givenJsonArrayAddValueOfOne_thenCallsSerialize()
      throws IOException {
    // Arrange
    JsonArray jsonArray = new JsonArray();
    jsonArray.add(Integer.valueOf(1));

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
    PostConstructAdapter<Object> postConstructAdapter = new PostConstructAdapter<>(delegate, null);

    // Act
    postConstructAdapter.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test PostConstructAdapter {@link PostConstructAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonSerializer} {@link JsonSerializer#serialize(Object, Type,
   *       JsonSerializationContext)} return {@link JsonArray#JsonArray()}.
   * </ul>
   *
   * <p>Method under test: {@link PostConstructAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PostConstructAdapter.write(JsonWriter, Object)"})
  public void testPostConstructAdapterWrite_givenJsonSerializerSerializeReturnJsonArray()
      throws IOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonArray());
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    PostConstructAdapter<Object> postConstructAdapter = new PostConstructAdapter<>(delegate, null);

    // Act
    postConstructAdapter.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test PostConstructAdapter {@link PostConstructAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonSerializer} {@link JsonSerializer#serialize(Object, Type,
   *       JsonSerializationContext)} return {@link JsonNull} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link PostConstructAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PostConstructAdapter.write(JsonWriter, Object)"})
  public void testPostConstructAdapterWrite_givenJsonSerializerSerializeReturnJsonNull()
      throws IOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonNull());
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    PostConstructAdapter<Object> postConstructAdapter = new PostConstructAdapter<>(delegate, null);

    // Act
    postConstructAdapter.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test PostConstructAdapter {@link PostConstructAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonSerializer} {@link JsonSerializer#serialize(Object, Type,
   *       JsonSerializationContext)} return {@link JsonObject} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link PostConstructAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PostConstructAdapter.write(JsonWriter, Object)"})
  public void testPostConstructAdapterWrite_givenJsonSerializerSerializeReturnJsonObject()
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
    PostConstructAdapter<Object> postConstructAdapter = new PostConstructAdapter<>(delegate, null);

    // Act
    postConstructAdapter.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test PostConstructAdapter {@link PostConstructAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Then calls {@link ObjectTypeAdapter#write(JsonWriter, Object)}.
   * </ul>
   *
   * <p>Method under test: {@link PostConstructAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PostConstructAdapter.write(JsonWriter, Object)"})
  public void testPostConstructAdapterWrite_thenCallsWrite() throws IOException {
    // Arrange
    ObjectTypeAdapter delegate = mock(ObjectTypeAdapter.class);
    doNothing().when(delegate).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());
    PostConstructAdapter<Object> postConstructAdapter = new PostConstructAdapter<>(delegate, null);

    // Act
    postConstructAdapter.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(delegate).write(isA(JsonWriter.class), isA(Object.class));
  }
}
