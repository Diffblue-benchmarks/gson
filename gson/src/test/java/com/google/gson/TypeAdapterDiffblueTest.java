package com.google.gson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.gson.internal.Excluder;
import com.google.gson.internal.bind.ArrayTypeAdapter;
import com.google.gson.internal.bind.TreeTypeAdapter;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Test;
import org.mockito.Mockito;

public class TypeAdapterDiffblueTest {
  /** Method under test: {@link TypeAdapter#toJson(Object)} */
  @Test
  public void testToJson() {
    // Arrange
    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    Gson context = new Gson();
    Gson.FutureTypeAdapter<Object> componentTypeAdapter = new Gson.FutureTypeAdapter<>();
    Class<Object> componentType = Object.class;
    futureTypeAdapter.setDelegate(
        new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType));

    // Act and Assert
    assertEquals("null", futureTypeAdapter.toJson(null));
  }

  /** Method under test: {@link TypeAdapter#toJson(Object)} */
  @Test
  public void testToJson2() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(JsonNull.INSTANCE);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    String actualToJsonResult = futureTypeAdapter.toJson("Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertEquals("null", actualToJsonResult);
  }

  /** Method under test: {@link TypeAdapter#toJson(Object)} */
  @Test
  public void testToJson3() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonArray(3));
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    String actualToJsonResult = futureTypeAdapter.toJson("Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertEquals("[]", actualToJsonResult);
  }

  /** Method under test: {@link TypeAdapter#toJson(Object)} */
  @Test
  public void testToJson4() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonObject());
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    String actualToJsonResult = futureTypeAdapter.toJson("Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertEquals("{}", actualToJsonResult);
  }

  /** Method under test: {@link TypeAdapter#toJson(Object)} */
  @Test
  public void testToJson5() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonPrimitive("out == null"));
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    String actualToJsonResult = futureTypeAdapter.toJson("Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertEquals("\"out == null\"", actualToJsonResult);
  }

  /** Method under test: {@link TypeAdapter#toJson(Object)} */
  @Test
  public void testToJson6() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonPrimitive(true));
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    String actualToJsonResult = futureTypeAdapter.toJson("Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertEquals(Boolean.TRUE.toString(), actualToJsonResult);
  }

  /** Method under test: {@link TypeAdapter#toJson(Object)} */
  @Test
  public void testToJson7() {
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
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    String actualToJsonResult = futureTypeAdapter.toJson("Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertEquals("[true]", actualToJsonResult);
  }

  /** Method under test: {@link TypeAdapter#toJson(Object)} */
  @Test
  public void testToJson8() {
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
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    String actualToJsonResult = futureTypeAdapter.toJson("Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertEquals("[false,true]", actualToJsonResult);
  }

  /** Method under test: {@link TypeAdapter#toJson(Object)} */
  @Test
  public void testToJson9() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add('\u0006');
    jsonArray.add(true);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(jsonArray);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    String actualToJsonResult = futureTypeAdapter.toJson("Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertEquals("[\"\\u0006\",true]", actualToJsonResult);
  }

  /** Method under test: {@link TypeAdapter#toJson(Object)} */
  @Test
  public void testToJson10() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(Integer.valueOf(Integer.SIZE));
    jsonArray.add(true);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(jsonArray);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    String actualToJsonResult = futureTypeAdapter.toJson("Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertEquals("[32,true]", actualToJsonResult);
  }

  /** Method under test: {@link TypeAdapter#toJson(Object)} */
  @Test
  public void testToJson11() {
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
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    String actualToJsonResult = futureTypeAdapter.toJson("Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertEquals("[null,true]", actualToJsonResult);
  }

  /** Method under test: {@link TypeAdapter#toJson(Object)} */
  @Test
  public void testToJson12() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("out == null", JsonNull.INSTANCE);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(jsonObject);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    String actualToJsonResult = futureTypeAdapter.toJson("Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertEquals("{\"out == null\":null}", actualToJsonResult);
  }

  /** Method under test: {@link TypeAdapter#toJson(Object)} */
  @Test
  public void testToJson13() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add(",", JsonNull.INSTANCE);
    jsonObject.add("out == null", JsonNull.INSTANCE);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(jsonObject);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    String actualToJsonResult = futureTypeAdapter.toJson("Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertEquals("{\",\":null,\"out == null\":null}", actualToJsonResult);
  }

  /** Method under test: {@link TypeAdapter#toJsonTree(Object)} */
  @Test
  public void testToJsonTree() {
    // Arrange
    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    Gson context = new Gson();
    Gson.FutureTypeAdapter<Object> componentTypeAdapter = new Gson.FutureTypeAdapter<>();
    Class<Object> componentType = Object.class;
    futureTypeAdapter.setDelegate(
        new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType));

    // Act
    JsonElement actualToJsonTreeResult = futureTypeAdapter.toJsonTree(null);

    // Assert
    assertSame(((JsonNull) actualToJsonTreeResult).INSTANCE, actualToJsonTreeResult);
  }

  /** Method under test: {@link TypeAdapter#toJsonTree(Object)} */
  @Test
  public void testToJsonTree2() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(JsonNull.INSTANCE);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    JsonElement actualToJsonTreeResult = futureTypeAdapter.toJsonTree("Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertSame(((JsonNull) actualToJsonTreeResult).INSTANCE, actualToJsonTreeResult);
  }

  /** Method under test: {@link TypeAdapter#toJsonTree(Object)} */
  @Test
  public void testToJsonTree3() {
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
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    JsonElement actualToJsonTreeResult = futureTypeAdapter.toJsonTree("Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertTrue(actualToJsonTreeResult instanceof JsonArray);
    assertEquals(jsonArray, actualToJsonTreeResult);
  }

  /** Method under test: {@link TypeAdapter#toJsonTree(Object)} */
  @Test
  public void testToJsonTree4() {
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
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    JsonElement actualToJsonTreeResult = futureTypeAdapter.toJsonTree("Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertTrue(actualToJsonTreeResult instanceof JsonObject);
    assertEquals(jsonObject, actualToJsonTreeResult);
  }

  /** Method under test: {@link TypeAdapter#toJsonTree(Object)} */
  @Test
  public void testToJsonTree5() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonPrimitive jsonPrimitive = new JsonPrimitive("out == null");
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(jsonPrimitive);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    JsonElement actualToJsonTreeResult = futureTypeAdapter.toJsonTree("Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertTrue(actualToJsonTreeResult instanceof JsonPrimitive);
    assertEquals(jsonPrimitive, actualToJsonTreeResult);
  }

  /** Method under test: {@link TypeAdapter#toJsonTree(Object)} */
  @Test
  public void testToJsonTree6() {
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
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    JsonElement actualToJsonTreeResult = futureTypeAdapter.toJsonTree("Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertTrue(actualToJsonTreeResult instanceof JsonPrimitive);
    assertEquals(jsonPrimitive, actualToJsonTreeResult);
  }

  /** Method under test: {@link TypeAdapter#toJsonTree(Object)} */
  @Test
  public void testToJsonTree7() {
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
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    JsonElement actualToJsonTreeResult = futureTypeAdapter.toJsonTree("Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertTrue(actualToJsonTreeResult instanceof JsonArray);
    assertEquals(jsonArray, actualToJsonTreeResult);
  }

  /** Method under test: {@link TypeAdapter#toJsonTree(Object)} */
  @Test
  public void testToJsonTree8() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(Integer.valueOf(Integer.SIZE));
    jsonArray.add(true);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(jsonArray);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    JsonElement actualToJsonTreeResult = futureTypeAdapter.toJsonTree("Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertTrue(actualToJsonTreeResult instanceof JsonArray);
    assertEquals(jsonArray, actualToJsonTreeResult);
  }

  /** Method under test: {@link TypeAdapter#toJsonTree(Object)} */
  @Test
  public void testToJsonTree9() {
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
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    JsonElement actualToJsonTreeResult = futureTypeAdapter.toJsonTree("Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertTrue(actualToJsonTreeResult instanceof JsonArray);
    assertEquals(jsonArray, actualToJsonTreeResult);
  }

  /** Method under test: {@link TypeAdapter#toJsonTree(Object)} */
  @Test
  public void testToJsonTree10() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add(",", JsonNull.INSTANCE);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(jsonObject);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    JsonElement actualToJsonTreeResult = futureTypeAdapter.toJsonTree("Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertTrue(actualToJsonTreeResult instanceof JsonObject);
    assertEquals(jsonObject, actualToJsonTreeResult);
  }

  /** Method under test: {@link TypeAdapter#toJsonTree(Object)} */
  @Test
  public void testToJsonTree11() {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty(",", "out == null");
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(jsonObject);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    JsonElement actualToJsonTreeResult = futureTypeAdapter.toJsonTree("Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertTrue(actualToJsonTreeResult instanceof JsonObject);
    assertEquals(jsonObject, actualToJsonTreeResult);
  }

  /** Method under test: {@link TypeAdapter#fromJson(Reader)} */
  @Test
  public void testFromJson() throws JsonParseException, IOException {
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

    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
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

  /** Method under test: {@link TypeAdapter#fromJson(Reader)} */
  @Test
  public void testFromJson2() throws JsonParseException, IOException {
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

    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    Object actualFromJsonResult = futureTypeAdapter.fromJson(new StringReader("42"));

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertEquals("Deserialize", actualFromJsonResult);
  }

  /** Method under test: {@link TypeAdapter#fromJson(Reader)} */
  @Test
  public void testFromJson3() throws IOException {
    // Arrange
    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    futureTypeAdapter.setDelegate(
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    // Act and Assert
    assertNull(futureTypeAdapter.fromJson(new StringReader("")));
  }

  /** Method under test: {@link TypeAdapter#fromJson(String)} */
  @Test
  public void testFromJson4() throws JsonParseException, IOException {
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

    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    Object actualFromJsonResult = futureTypeAdapter.fromJson("42");

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertEquals("Deserialize", actualFromJsonResult);
  }

  /** Method under test: {@link TypeAdapter#fromJson(String)} */
  @Test
  public void testFromJson5() throws IOException {
    // Arrange
    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    futureTypeAdapter.setDelegate(
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    // Act and Assert
    assertNull(futureTypeAdapter.fromJson(""));
  }

  /** Method under test: {@link TypeAdapter#fromJsonTree(JsonElement)} */
  @Test
  public void testFromJsonTree() {
    // Arrange
    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    Gson context = new Gson();
    Gson.FutureTypeAdapter<Object> componentTypeAdapter = new Gson.FutureTypeAdapter<>();
    Class<Object> componentType = Object.class;
    futureTypeAdapter.setDelegate(
        new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType));

    // Act and Assert
    assertNull(futureTypeAdapter.fromJsonTree(JsonNull.INSTANCE));
  }

  /** Method under test: {@link TypeAdapter#fromJsonTree(JsonElement)} */
  @Test
  public void testFromJsonTree2() {
    // Arrange
    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson context =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            FormattingStyle.COMPACT,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            255,
            255,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());

    Gson.FutureTypeAdapter<Object> componentTypeAdapter = new Gson.FutureTypeAdapter<>();
    Class<Object> componentType = Object.class;
    futureTypeAdapter.setDelegate(
        new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType));

    // Act and Assert
    assertNull(futureTypeAdapter.fromJsonTree(JsonNull.INSTANCE));
  }

  /** Method under test: {@link TypeAdapter#fromJsonTree(JsonElement)} */
  @Test
  public void testFromJsonTree3() {
    // Arrange
    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    Gson context = new Gson();
    Gson.FutureTypeAdapter<Object> componentTypeAdapter = new Gson.FutureTypeAdapter<>();
    Class<Object> componentType = Object.class;
    futureTypeAdapter.setDelegate(
        new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType));

    // Act
    Object actualFromJsonTreeResult = futureTypeAdapter.fromJsonTree(new JsonArray(3));

    // Assert
    assertTrue(actualFromJsonTreeResult instanceof Object[]);
    assertEquals(0, ((Object[]) actualFromJsonTreeResult).length);
  }

  /** Method under test: {@link TypeAdapter#fromJsonTree(JsonElement)} */
  @Test
  public void testFromJsonTree4() {
    // Arrange
    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    futureTypeAdapter.setDelegate(
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    // Act and Assert
    assertNull(futureTypeAdapter.fromJsonTree(JsonNull.INSTANCE));
  }

  /** Method under test: {@link TypeAdapter#nullSafe()} */
  @Test
  public void testNullSafe() {
    // Arrange
    Gson.FutureTypeAdapter<Object> futureTypeAdapter = new Gson.FutureTypeAdapter<>();
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    futureTypeAdapter.setDelegate(
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    // Act and Assert
    assertEquals("null", futureTypeAdapter.nullSafe().toJson("Value"));
  }
}
