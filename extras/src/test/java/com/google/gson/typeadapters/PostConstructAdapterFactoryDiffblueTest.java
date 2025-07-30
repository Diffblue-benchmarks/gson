package com.google.gson.typeadapters;

import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.isA;
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
import com.google.gson.internal.bind.TreeTypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.google.gson.typeadapters.PostConstructAdapterFactory.PostConstructAdapter;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.nio.file.Paths;
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
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link PostConstructAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PostConstructAdapter.read(JsonReader)"})
  public void testPostConstructAdapterRead_thenReturnNull() throws IOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    PostConstructAdapter<Object> postConstructAdapter =
        new PostConstructAdapter<>(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class), true),
            null);

    // Act and Assert
    assertNull(
        postConstructAdapter.read(
            new JsonReader(
                new FileReader(
                    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()))));
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
        .thenReturn(new JsonArray(3));
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    PostConstructAdapter<Object> postConstructAdapter =
        new PostConstructAdapter<>(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)),
            null);

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
        .thenReturn(new JsonPrimitive("String"));
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    PostConstructAdapter<Object> postConstructAdapter =
        new PostConstructAdapter<>(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)),
            null);

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
        .thenReturn(new JsonPrimitive(true));
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    PostConstructAdapter<Object> postConstructAdapter =
        new PostConstructAdapter<>(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)),
            null);

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
  public void testPostConstructAdapterWrite4() throws IOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonPrimitive('\u0001'));
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    PostConstructAdapter<Object> postConstructAdapter =
        new PostConstructAdapter<>(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)),
            null);

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
  public void testPostConstructAdapterWrite5() throws IOException {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new JsonArray(3));
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(jsonArray);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    PostConstructAdapter<Object> postConstructAdapter =
        new PostConstructAdapter<>(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)),
            null);

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
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link PostConstructAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PostConstructAdapter.write(JsonWriter, Object)"})
  public void testPostConstructAdapterWrite_givenJsonArrayWithCapacityIsThreeAddFalse()
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
    PostConstructAdapter<Object> postConstructAdapter =
        new PostConstructAdapter<>(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)),
            null);

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
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add start of heading.
   * </ul>
   *
   * <p>Method under test: {@link PostConstructAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PostConstructAdapter.write(JsonWriter, Object)"})
  public void testPostConstructAdapterWrite_givenJsonArrayWithCapacityIsThreeAddStartOfHeading()
      throws IOException {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add('\u0001');
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(jsonArray);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    PostConstructAdapter<Object> postConstructAdapter =
        new PostConstructAdapter<>(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)),
            null);

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
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link PostConstructAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PostConstructAdapter.write(JsonWriter, Object)"})
  public void testPostConstructAdapterWrite_givenJsonArrayWithCapacityIsThreeAddTrue()
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
    PostConstructAdapter<Object> postConstructAdapter =
        new PostConstructAdapter<>(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)),
            null);

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
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add valueOf one.
   * </ul>
   *
   * <p>Method under test: {@link PostConstructAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PostConstructAdapter.write(JsonWriter, Object)"})
  public void testPostConstructAdapterWrite_givenJsonArrayWithCapacityIsThreeAddValueOfOne()
      throws IOException {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(Integer.valueOf(1));
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(jsonArray);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    PostConstructAdapter<Object> postConstructAdapter =
        new PostConstructAdapter<>(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)),
            null);

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
    PostConstructAdapter<Object> postConstructAdapter =
        new PostConstructAdapter<>(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)),
            null);

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
    PostConstructAdapter<Object> postConstructAdapter =
        new PostConstructAdapter<>(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)),
            null);

    // Act
    postConstructAdapter.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }
}
