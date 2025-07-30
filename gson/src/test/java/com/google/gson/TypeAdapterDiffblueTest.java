package com.google.gson;

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
import com.google.gson.Gson.FutureTypeAdapter;
import com.google.gson.internal.bind.ArrayTypeAdapter;
import com.google.gson.internal.bind.TreeTypeAdapter;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Iterator;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class TypeAdapterDiffblueTest {
  /**
   * Test {@link TypeAdapter#toJson(Object)} with {@code value}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code true}.
   *   <li>Then return {@code [true]}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#toJson(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String TypeAdapter.toJson(Object)"})
  public void testToJsonWithValue_givenJsonArrayWithCapacityIsThreeAddTrue_thenReturnTrue() {
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

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    String actualToJsonResult = futureTypeAdapter.toJson("Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertEquals("[true]", actualToJsonResult);
  }

  /**
   * Test {@link TypeAdapter#toJson(Object)} with {@code value}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code ,} and {@link
   *       JsonNull#INSTANCE}.
   *   <li>Then return {@code {",":null,"out == null":null}}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#toJson(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String TypeAdapter.toJson(Object)"})
  public void testToJsonWithValue_givenJsonObjectAddCommaAndInstance_thenReturnNullOutNullNull() {
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

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    String actualToJsonResult = futureTypeAdapter.toJson("Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertEquals("{\",\":null,\"out == null\":null}", actualToJsonResult);
  }

  /**
   * Test {@link TypeAdapter#toJson(Object)} with {@code value}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code out == null} and {@link
   *       JsonNull#INSTANCE}.
   *   <li>Then return {@code {"out == null":null}}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#toJson(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String TypeAdapter.toJson(Object)"})
  public void testToJsonWithValue_givenJsonObjectAddOutNullAndInstance_thenReturnOutNullNull() {
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

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    String actualToJsonResult = futureTypeAdapter.toJson("Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertEquals("{\"out == null\":null}", actualToJsonResult);
  }

  /**
   * Test {@link TypeAdapter#toJson(Object)} with {@code value}.
   *
   * <ul>
   *   <li>Given {@link JsonSerializer} {@link JsonSerializer#serialize(Object, Type,
   *       JsonSerializationContext)} return {@link JsonNull#INSTANCE}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#toJson(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String TypeAdapter.toJson(Object)"})
  public void testToJsonWithValue_givenJsonSerializerSerializeReturnInstance_thenReturnNull() {
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

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    String actualToJsonResult = futureTypeAdapter.toJson("Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertEquals("null", actualToJsonResult);
  }

  /**
   * Test {@link TypeAdapter#toJson(Object)} with {@code value}.
   *
   * <ul>
   *   <li>Then return {@code [32,true]}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#toJson(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String TypeAdapter.toJson(Object)"})
  public void testToJsonWithValue_thenReturn32True() {
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

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    String actualToJsonResult = futureTypeAdapter.toJson("Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertEquals("[32,true]", actualToJsonResult);
  }

  /**
   * Test {@link TypeAdapter#toJson(Object)} with {@code value}.
   *
   * <ul>
   *   <li>Then return {@code [false,true]}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#toJson(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String TypeAdapter.toJson(Object)"})
  public void testToJsonWithValue_thenReturnFalseTrue() {
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

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    String actualToJsonResult = futureTypeAdapter.toJson("Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertEquals("[false,true]", actualToJsonResult);
  }

  /**
   * Test {@link TypeAdapter#toJson(Object)} with {@code value}.
   *
   * <ul>
   *   <li>Then return {@code {}}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#toJson(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String TypeAdapter.toJson(Object)"})
  public void testToJsonWithValue_thenReturnLeftCurlyBracketRightCurlyBracket() {
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

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    String actualToJsonResult = futureTypeAdapter.toJson("Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertEquals("{}", actualToJsonResult);
  }

  /**
   * Test {@link TypeAdapter#toJson(Object)} with {@code value}.
   *
   * <ul>
   *   <li>Then return {@code []}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#toJson(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String TypeAdapter.toJson(Object)"})
  public void testToJsonWithValue_thenReturnLeftSquareBracketRightSquareBracket() {
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

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    String actualToJsonResult = futureTypeAdapter.toJson("Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertEquals("[]", actualToJsonResult);
  }

  /**
   * Test {@link TypeAdapter#toJson(Object)} with {@code value}.
   *
   * <ul>
   *   <li>Then return {@code [null,true]}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#toJson(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String TypeAdapter.toJson(Object)"})
  public void testToJsonWithValue_thenReturnNullTrue() {
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

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    String actualToJsonResult = futureTypeAdapter.toJson("Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertEquals("[null,true]", actualToJsonResult);
  }

  /**
   * Test {@link TypeAdapter#toJson(Object)} with {@code value}.
   *
   * <ul>
   *   <li>Then return {@code "out == null"}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#toJson(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String TypeAdapter.toJson(Object)"})
  public void testToJsonWithValue_thenReturnOutNull() {
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

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    String actualToJsonResult = futureTypeAdapter.toJson("Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertEquals("\"out == null\"", actualToJsonResult);
  }

  /**
   * Test {@link TypeAdapter#toJson(Object)} with {@code value}.
   *
   * <ul>
   *   <li>Then return {@link Boolean#TRUE} toString.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#toJson(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String TypeAdapter.toJson(Object)"})
  public void testToJsonWithValue_thenReturnTrueToString() {
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

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    String actualToJsonResult = futureTypeAdapter.toJson("Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertEquals(Boolean.TRUE.toString(), actualToJsonResult);
  }

  /**
   * Test {@link TypeAdapter#toJson(Object)} with {@code value}.
   *
   * <ul>
   *   <li>Then return {@code ["\u0006",true]}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#toJson(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String TypeAdapter.toJson(Object)"})
  public void testToJsonWithValue_thenReturnU0006True() {
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

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    String actualToJsonResult = futureTypeAdapter.toJson("Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertEquals("[\"\\u0006\",true]", actualToJsonResult);
  }

  /**
   * Test {@link TypeAdapter#toJson(Object)} with {@code value}.
   *
   * <ul>
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
  public void testToJsonWithValue_whenNull_thenReturnNull() {
    // Arrange
    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    Gson context = new Gson();
    FutureTypeAdapter<Object> componentTypeAdapter = new FutureTypeAdapter<>();
    Class<Object> componentType = Object.class;
    futureTypeAdapter.setDelegate(
        new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType));

    // Act and Assert
    assertEquals("null", futureTypeAdapter.toJson(null));
  }

  /**
   * Test {@link TypeAdapter#toJsonTree(Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code true}.
   *   <li>Then return AsCharacter is {@code t}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#toJsonTree(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement TypeAdapter.toJsonTree(Object)"})
  public void testToJsonTree_givenJsonArrayWithCapacityIsThreeAddTrue_thenReturnAsCharacterIsT() {
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

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    JsonElement actualToJsonTreeResult = futureTypeAdapter.toJsonTree("Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertTrue(actualToJsonTreeResult instanceof JsonArray);
    Iterator<JsonElement> iteratorResult = ((JsonArray) actualToJsonTreeResult).iterator();
    JsonElement nextResult = iteratorResult.next();
    assertTrue(nextResult instanceof JsonPrimitive);
    assertEquals('t', actualToJsonTreeResult.getAsCharacter());
    assertEquals('t', nextResult.getAsCharacter());
    assertEquals(1, ((JsonArray) actualToJsonTreeResult).size());
    assertFalse(((JsonPrimitive) nextResult).isNumber());
    assertFalse(iteratorResult.hasNext());
    assertTrue(actualToJsonTreeResult.getAsBoolean());
    assertTrue(nextResult.getAsBoolean());
    assertTrue(((JsonPrimitive) nextResult).isBoolean());
    String expectedAsString = Boolean.TRUE.toString();
    assertEquals(expectedAsString, actualToJsonTreeResult.getAsString());
    String expectedAsString2 = Boolean.TRUE.toString();
    assertEquals(expectedAsString2, nextResult.getAsString());
  }

  /**
   * Test {@link TypeAdapter#toJsonTree(Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code ,} and {@link
   *       JsonNull#INSTANCE}.
   *   <li>When {@code Value}.
   *   <li>Then return {@link JsonObject}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#toJsonTree(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement TypeAdapter.toJsonTree(Object)"})
  public void testToJsonTree_givenJsonObjectAddCommaAndInstance_whenValue_thenReturnJsonObject() {
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

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    JsonElement actualToJsonTreeResult = futureTypeAdapter.toJsonTree("Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertTrue(actualToJsonTreeResult instanceof JsonObject);
    assertEquals(jsonObject, actualToJsonTreeResult);
  }

  /**
   * Test {@link TypeAdapter#toJsonTree(Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) addProperty {@code ,} and {@code out ==
   *       null}.
   *   <li>Then return {@link JsonObject}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#toJsonTree(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement TypeAdapter.toJsonTree(Object)"})
  public void testToJsonTree_givenJsonObjectAddPropertyCommaAndOutNull_thenReturnJsonObject() {
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

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    JsonElement actualToJsonTreeResult = futureTypeAdapter.toJsonTree("Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertTrue(actualToJsonTreeResult instanceof JsonObject);
    assertEquals(jsonObject, actualToJsonTreeResult);
  }

  /**
   * Test {@link TypeAdapter#toJsonTree(Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonSerializer} {@link JsonSerializer#serialize(Object, Type,
   *       JsonSerializationContext)} return {@link JsonNull#INSTANCE}.
   *   <li>Then return {@link JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#toJsonTree(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement TypeAdapter.toJsonTree(Object)"})
  public void testToJsonTree_givenJsonSerializerSerializeReturnInstance_thenReturnInstance() {
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

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    JsonElement actualToJsonTreeResult = futureTypeAdapter.toJsonTree("Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertSame(((JsonNull) actualToJsonTreeResult).INSTANCE, actualToJsonTreeResult);
  }

  /**
   * Test {@link TypeAdapter#toJsonTree(Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonSerializer} {@link JsonSerializer#serialize(Object, Type,
   *       JsonSerializationContext)} return {@link JsonObject} (default constructor).
   *   <li>Then return {@link JsonObject}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#toJsonTree(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement TypeAdapter.toJsonTree(Object)"})
  public void testToJsonTree_givenJsonSerializerSerializeReturnJsonObject_thenReturnJsonObject() {
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

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    JsonElement actualToJsonTreeResult = futureTypeAdapter.toJsonTree("Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertTrue(actualToJsonTreeResult instanceof JsonObject);
    assertEquals(jsonObject, actualToJsonTreeResult);
  }

  /**
   * Test {@link TypeAdapter#toJsonTree(Object)}.
   *
   * <ul>
   *   <li>Then return {@link JsonArray#JsonArray(int)} with capacity is three.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#toJsonTree(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement TypeAdapter.toJsonTree(Object)"})
  public void testToJsonTree_thenReturnJsonArrayWithCapacityIsThree() {
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

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    JsonElement actualToJsonTreeResult = futureTypeAdapter.toJsonTree("Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertTrue(actualToJsonTreeResult instanceof JsonArray);
    assertEquals(jsonArray, actualToJsonTreeResult);
  }

  /**
   * Test {@link TypeAdapter#toJsonTree(Object)}.
   *
   * <ul>
   *   <li>Then return {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#toJsonTree(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement TypeAdapter.toJsonTree(Object)"})
  public void testToJsonTree_thenReturnJsonPrimitiveWithBoolIsTrue() {
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

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    JsonElement actualToJsonTreeResult = futureTypeAdapter.toJsonTree("Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertTrue(actualToJsonTreeResult instanceof JsonPrimitive);
    assertEquals(jsonPrimitive, actualToJsonTreeResult);
  }

  /**
   * Test {@link TypeAdapter#toJsonTree(Object)}.
   *
   * <ul>
   *   <li>Then return {@link JsonPrimitive#JsonPrimitive(String)} with string is {@code out ==
   *       null}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#toJsonTree(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement TypeAdapter.toJsonTree(Object)"})
  public void testToJsonTree_thenReturnJsonPrimitiveWithStringIsOutNull() {
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

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    JsonElement actualToJsonTreeResult = futureTypeAdapter.toJsonTree("Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    assertTrue(actualToJsonTreeResult instanceof JsonPrimitive);
    assertEquals(jsonPrimitive, actualToJsonTreeResult);
  }

  /**
   * Test {@link TypeAdapter#toJsonTree(Object)}.
   *
   * <ul>
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
  public void testToJsonTree_whenNull_thenReturnInstance() {
    // Arrange
    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    Gson context = new Gson();
    FutureTypeAdapter<Object> componentTypeAdapter = new FutureTypeAdapter<>();
    Class<Object> componentType = Object.class;
    futureTypeAdapter.setDelegate(
        new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType));

    // Act
    JsonElement actualToJsonTreeResult = futureTypeAdapter.toJsonTree(null);

    // Assert
    assertSame(((JsonNull) actualToJsonTreeResult).INSTANCE, actualToJsonTreeResult);
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
   * Test {@link TypeAdapter#fromJson(String)} with {@code json}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#fromJson(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapter.fromJson(String)"})
  public void testFromJsonWithJson_whenEmptyString_thenReturnNull() throws IOException {
    // Arrange
    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
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

  /**
   * Test {@link TypeAdapter#fromJsonTree(JsonElement)}.
   *
   * <ul>
   *   <li>Given {@link JsonDeserializer} {@link JsonDeserializer#deserialize(JsonElement, Type,
   *       JsonDeserializationContext)} return {@code false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#fromJsonTree(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapter.fromJsonTree(JsonElement)"})
  public void testFromJsonTree_givenJsonDeserializerDeserializeReturnFalse_thenReturnFalse()
      throws JsonParseException {
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
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    Object actualFromJsonTreeResult = futureTypeAdapter.fromJsonTree(new JsonPrimitive(true));

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertFalse((Boolean) actualFromJsonTreeResult);
  }

  /**
   * Test {@link TypeAdapter#fromJsonTree(JsonElement)}.
   *
   * <ul>
   *   <li>Given {@link JsonDeserializer} {@link JsonDeserializer#deserialize(JsonElement, Type,
   *       JsonDeserializationContext)} return {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#fromJsonTree(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapter.fromJsonTree(JsonElement)"})
  public void testFromJsonTree_givenJsonDeserializerDeserializeReturnTrue_thenReturnTrue()
      throws JsonParseException {
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
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    Object actualFromJsonTreeResult = futureTypeAdapter.fromJsonTree(new JsonPrimitive(true));

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertTrue((Boolean) actualFromJsonTreeResult);
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
    Object actualFromJsonTreeResult = futureTypeAdapter.fromJsonTree(new JsonPrimitive(true));

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
    futureTypeAdapter.setDelegate(
        new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType));

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
    futureTypeAdapter.setDelegate(
        new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType));

    // Act
    Object actualFromJsonTreeResult = futureTypeAdapter.fromJsonTree(new JsonArray(3));

    // Assert
    assertTrue(actualFromJsonTreeResult instanceof Object[]);
    assertEquals(0, ((Object[]) actualFromJsonTreeResult).length);
  }

  /**
   * Test {@link TypeAdapter#fromJsonTree(JsonElement)}.
   *
   * <ul>
   *   <li>When {@link JsonObject} (default constructor).
   *   <li>Then return {@code Deserialize}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#fromJsonTree(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapter.fromJsonTree(JsonElement)"})
  public void testFromJsonTree_whenJsonObject_thenReturnDeserialize() throws JsonParseException {
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
   *   <li>When {@link JsonPrimitive#JsonPrimitive(String)} with string is {@code in == null}.
   *   <li>Then return {@code Deserialize}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapter#fromJsonTree(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapter.fromJsonTree(JsonElement)"})
  public void testFromJsonTree_whenJsonPrimitiveWithStringIsInNull_thenReturnDeserialize()
      throws JsonParseException {
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
    Object actualFromJsonTreeResult =
        futureTypeAdapter.fromJsonTree(new JsonPrimitive("in == null"));

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertEquals("Deserialize", actualFromJsonTreeResult);
  }
}
