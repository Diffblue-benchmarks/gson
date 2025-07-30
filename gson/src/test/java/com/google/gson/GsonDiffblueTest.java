package com.google.gson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.Gson.FutureTypeAdapter;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.internal.bind.CollectionTypeAdapterFactory;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.internal.bind.ObjectTypeAdapter;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.google.gson.internal.bind.TreeTypeAdapter;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.EOFException;
import java.io.FileDescriptor;
import java.io.FileReader;
import java.io.IOException;
import java.io.PipedWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.GenericMetadataSupport;
import org.mockito.internal.util.reflection.GenericMetadataSupport.TypeVarBoundedType;

public class GsonDiffblueTest {
  /**
   * Test FutureTypeAdapter {@link FutureTypeAdapter#getSerializationDelegate()}.
   *
   * <ul>
   *   <li>Then return {@link FutureTypeAdapter}.
   * </ul>
   *
   * <p>Method under test: {@link FutureTypeAdapter#getSerializationDelegate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapter FutureTypeAdapter.getSerializationDelegate()"})
  public void testFutureTypeAdapterGetSerializationDelegate_thenReturnFutureTypeAdapter() {
    // Arrange
    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    FutureTypeAdapter<Object> typeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    TypeAdapter<Object> actualSerializationDelegate = futureTypeAdapter.getSerializationDelegate();

    // Assert
    assertTrue(actualSerializationDelegate instanceof FutureTypeAdapter);
    assertSame(typeAdapter, actualSerializationDelegate);
  }

  /**
   * Test FutureTypeAdapter {@link FutureTypeAdapter#getSerializationDelegate()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link FutureTypeAdapter#getSerializationDelegate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapter FutureTypeAdapter.getSerializationDelegate()"})
  public void testFutureTypeAdapterGetSerializationDelegate_thenThrowIllegalStateException() {
    // Arrange
    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> futureTypeAdapter.getSerializationDelegate());
  }

  /**
   * Test FutureTypeAdapter {@link FutureTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@link FutureTypeAdapter} (default constructor) Delegate is {@link
   *       FutureTypeAdapter} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link FutureTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object FutureTypeAdapter.read(JsonReader)"})
  public void testFutureTypeAdapterRead_givenFutureTypeAdapterDelegateIsFutureTypeAdapter()
      throws IOException {
    // Arrange
    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(new FutureTypeAdapter<>());

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> futureTypeAdapter.read(new JsonReader(new StringReader("foo"))));
  }

  /**
   * Test FutureTypeAdapter {@link FutureTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@link FutureTypeAdapter} (default constructor).
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link FutureTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object FutureTypeAdapter.read(JsonReader)"})
  public void testFutureTypeAdapterRead_givenFutureTypeAdapter_thenThrowIllegalStateException()
      throws IOException {
    // Arrange
    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> futureTypeAdapter.read(new JsonReader(new StringReader("foo"))));
  }

  /**
   * Test FutureTypeAdapter {@link FutureTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then return {@code Deserialize}.
   * </ul>
   *
   * <p>Method under test: {@link FutureTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object FutureTypeAdapter.read(JsonReader)"})
  public void testFutureTypeAdapterRead_whenStringReaderWith42_thenReturnDeserialize()
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
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    Object actualReadResult = futureTypeAdapter.read(new JsonReader(new StringReader("42")));

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertEquals("Deserialize", actualReadResult);
  }

  /**
   * Test FutureTypeAdapter {@link FutureTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link FutureTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object FutureTypeAdapter.read(JsonReader)"})
  public void testFutureTypeAdapterRead_whenStringReaderWithEmptyString_thenReturnNull()
      throws IOException {
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
    assertNull(futureTypeAdapter.read(new JsonReader(new StringReader(""))));
  }

  /**
   * Test FutureTypeAdapter {@link FutureTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   * </ul>
   *
   * <p>Method under test: {@link FutureTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object FutureTypeAdapter.read(JsonReader)"})
  public void testFutureTypeAdapterRead_whenStringReaderWithFalseToString()
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
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    Object actualReadResult =
        futureTypeAdapter.read(new JsonReader(new StringReader(Boolean.FALSE.toString())));

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertEquals("Deserialize", actualReadResult);
  }

  /**
   * Test FutureTypeAdapter {@link FutureTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code FALSE}.
   *   <li>Then return {@code Deserialize}.
   * </ul>
   *
   * <p>Method under test: {@link FutureTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object FutureTypeAdapter.read(JsonReader)"})
  public void testFutureTypeAdapterRead_whenStringReaderWithFalse_thenReturnDeserialize()
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
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    Object actualReadResult = futureTypeAdapter.read(new JsonReader(new StringReader("FALSE")));

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
    assertEquals("Deserialize", actualReadResult);
  }

  /**
   * Test FutureTypeAdapter {@link FutureTypeAdapter#write(JsonWriter, Object)}.
   *
   * <p>Method under test: {@link FutureTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FutureTypeAdapter.write(JsonWriter, Object)"})
  public void testFutureTypeAdapterWrite() throws IOException {
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
    futureTypeAdapter.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test FutureTypeAdapter {@link FutureTypeAdapter#write(JsonWriter, Object)}.
   *
   * <p>Method under test: {@link FutureTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FutureTypeAdapter.write(JsonWriter, Object)"})
  public void testFutureTypeAdapterWrite2() throws IOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonPrimitive("null"));
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
    futureTypeAdapter.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test FutureTypeAdapter {@link FutureTypeAdapter#write(JsonWriter, Object)}.
   *
   * <p>Method under test: {@link FutureTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FutureTypeAdapter.write(JsonWriter, Object)"})
  public void testFutureTypeAdapterWrite3() throws IOException {
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
    futureTypeAdapter.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test FutureTypeAdapter {@link FutureTypeAdapter#write(JsonWriter, Object)}.
   *
   * <p>Method under test: {@link FutureTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FutureTypeAdapter.write(JsonWriter, Object)"})
  public void testFutureTypeAdapterWrite4() throws IOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonPrimitive('\u0001'));
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
    futureTypeAdapter.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test FutureTypeAdapter {@link FutureTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link FutureTypeAdapter} (default constructor) Delegate is {@link
   *       FutureTypeAdapter} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link FutureTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FutureTypeAdapter.write(JsonWriter, Object)"})
  public void testFutureTypeAdapterWrite_givenFutureTypeAdapterDelegateIsFutureTypeAdapter()
      throws IOException {
    // Arrange
    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(new FutureTypeAdapter<>());

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> futureTypeAdapter.write(new JsonWriter(new StringWriter()), "Value"));
  }

  /**
   * Test FutureTypeAdapter {@link FutureTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link FutureTypeAdapter} (default constructor).
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link FutureTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FutureTypeAdapter.write(JsonWriter, Object)"})
  public void testFutureTypeAdapterWrite_givenFutureTypeAdapter_thenThrowIllegalStateException()
      throws IOException {
    // Arrange
    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> futureTypeAdapter.write(new JsonWriter(new StringWriter()), "Value"));
  }

  /**
   * Test FutureTypeAdapter {@link FutureTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add end of text.
   * </ul>
   *
   * <p>Method under test: {@link FutureTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FutureTypeAdapter.write(JsonWriter, Object)"})
  public void testFutureTypeAdapterWrite_givenJsonArrayWithCapacityIsThreeAddEndOfText()
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
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    futureTypeAdapter.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test FutureTypeAdapter {@link FutureTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link FutureTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FutureTypeAdapter.write(JsonWriter, Object)"})
  public void testFutureTypeAdapterWrite_givenJsonArrayWithCapacityIsThreeAddFalse()
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
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    futureTypeAdapter.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test FutureTypeAdapter {@link FutureTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link FutureTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FutureTypeAdapter.write(JsonWriter, Object)"})
  public void testFutureTypeAdapterWrite_givenJsonArrayWithCapacityIsThreeAddInstance()
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
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    futureTypeAdapter.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test FutureTypeAdapter {@link FutureTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link FutureTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FutureTypeAdapter.write(JsonWriter, Object)"})
  public void testFutureTypeAdapterWrite_givenJsonArrayWithCapacityIsThreeAddTrue()
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
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    futureTypeAdapter.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test FutureTypeAdapter {@link FutureTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add valueOf one.
   * </ul>
   *
   * <p>Method under test: {@link FutureTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FutureTypeAdapter.write(JsonWriter, Object)"})
  public void testFutureTypeAdapterWrite_givenJsonArrayWithCapacityIsThreeAddValueOfOne()
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
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    futureTypeAdapter.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test FutureTypeAdapter {@link FutureTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code null} and {@link
   *       JsonNull#INSTANCE}.
   *   <li>Then calls {@link JsonSerializer#serialize(Object, Type, JsonSerializationContext)}.
   * </ul>
   *
   * <p>Method under test: {@link FutureTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FutureTypeAdapter.write(JsonWriter, Object)"})
  public void testFutureTypeAdapterWrite_givenJsonObjectAddNullAndInstance_thenCallsSerialize()
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
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    futureTypeAdapter.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test FutureTypeAdapter {@link FutureTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code Property} and {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link FutureTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FutureTypeAdapter.write(JsonWriter, Object)"})
  public void testFutureTypeAdapterWrite_givenJsonObjectAddPropertyAndInstance()
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
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    futureTypeAdapter.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test FutureTypeAdapter {@link FutureTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonSerializer} {@link JsonSerializer#serialize(Object, Type,
   *       JsonSerializationContext)} return {@link JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link FutureTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FutureTypeAdapter.write(JsonWriter, Object)"})
  public void testFutureTypeAdapterWrite_givenJsonSerializerSerializeReturnInstance()
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
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    futureTypeAdapter.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test FutureTypeAdapter {@link FutureTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonSerializer} {@link JsonSerializer#serialize(Object, Type,
   *       JsonSerializationContext)} return {@link JsonObject} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link FutureTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FutureTypeAdapter.write(JsonWriter, Object)"})
  public void testFutureTypeAdapterWrite_givenJsonSerializerSerializeReturnJsonObject()
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
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    futureTypeAdapter.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link Gson#Gson()}.
   *
   * <p>Method under test: {@link Gson#Gson()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.<init>()"})
  public void testNewGson() {
    // Arrange and Act
    Gson actualGson = new Gson();

    // Assert
    assertNull(actualGson.strictness);
    assertNull(actualGson.datePattern);
    assertEquals(2, actualGson.dateStyle);
    assertEquals(2, actualGson.timeStyle);
    assertEquals(42, actualGson.factories.size());
    assertEquals(LongSerializationPolicy.DEFAULT, actualGson.longSerializationPolicy);
    assertFalse(actualGson.serializeNulls());
    assertFalse(actualGson.complexMapKeySerialization);
    assertFalse(actualGson.generateNonExecutableJson);
    assertFalse(actualGson.serializeNulls);
    assertFalse(actualGson.serializeSpecialFloatingPointValues);
    assertTrue(actualGson.htmlSafe());
    assertTrue(actualGson.builderFactories.isEmpty());
    assertTrue(actualGson.builderHierarchyFactories.isEmpty());
    assertTrue(actualGson.reflectionFilters.isEmpty());
    assertTrue(actualGson.instanceCreators.isEmpty());
    assertTrue(actualGson.htmlSafe);
    assertTrue(actualGson.useJdkUnsafe);
    Excluder expectedExcluderResult = actualGson.excluder;
    assertSame(expectedExcluderResult, actualGson.excluder());
    FieldNamingStrategy expectedFieldNamingStrategyResult = actualGson.fieldNamingStrategy;
    assertSame(expectedFieldNamingStrategyResult, actualGson.fieldNamingStrategy());
  }

  /**
   * Test {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean,
   * FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List,
   * List, List, ToNumberStrategy, ToNumberStrategy, List)}.
   *
   * <ul>
   *   <li>Given {@link ReflectionAccessFilter}.
   *   <li>Then {@link ArrayList#ArrayList()} size is one.
   * </ul>
   *
   * <p>Method under test: {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean,
   * boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy,
   * String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Gson.<init>(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)"
  })
  public void testNewGson_givenReflectionAccessFilter_thenArrayListSizeIsOne() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();
    reflectionFilters.add(mock(ReflectionAccessFilter.class));

    // Act
    Gson actualGson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            reflectionFilters);

    // Assert
    assertEquals(1, reflectionFilters.size());
    assertEquals(1, actualGson.reflectionFilters.size());
    List<TypeAdapterFactory> typeAdapterFactoryList = actualGson.factories;
    assertEquals(42, typeAdapterFactoryList.size());
    Excluder excluder = actualGson.excluder;
    assertSame(excluder, actualGson.excluder());
    assertSame(excluder, typeAdapterFactoryList.get(2));
    FieldNamingStrategy expectedFieldNamingStrategyResult = actualGson.fieldNamingStrategy;
    assertSame(expectedFieldNamingStrategyResult, actualGson.fieldNamingStrategy());
  }

  /**
   * Test {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean,
   * FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List,
   * List, List, ToNumberStrategy, ToNumberStrategy, List)}.
   *
   * <ul>
   *   <li>Given {@link ReflectionAccessFilter}.
   *   <li>Then {@link ArrayList#ArrayList()} size is two.
   * </ul>
   *
   * <p>Method under test: {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean,
   * boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy,
   * String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Gson.<init>(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)"
  })
  public void testNewGson_givenReflectionAccessFilter_thenArrayListSizeIsTwo() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();
    reflectionFilters.add(mock(ReflectionAccessFilter.class));
    reflectionFilters.add(mock(ReflectionAccessFilter.class));

    // Act
    Gson actualGson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            reflectionFilters);

    // Assert
    assertEquals(2, reflectionFilters.size());
    assertEquals(2, actualGson.reflectionFilters.size());
    List<TypeAdapterFactory> typeAdapterFactoryList = actualGson.factories;
    assertEquals(42, typeAdapterFactoryList.size());
    Excluder excluder = actualGson.excluder;
    assertSame(excluder, actualGson.excluder());
    assertSame(excluder, typeAdapterFactoryList.get(2));
    FieldNamingStrategy expectedFieldNamingStrategyResult = actualGson.fieldNamingStrategy;
    assertSame(expectedFieldNamingStrategyResult, actualGson.fieldNamingStrategy());
  }

  /**
   * Test {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean,
   * FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List,
   * List, List, ToNumberStrategy, ToNumberStrategy, List)}.
   *
   * <ul>
   *   <li>Given {@link TypeAdapterFactory}.
   *   <li>Then return {@link Gson#builderFactories} size is one.
   * </ul>
   *
   * <p>Method under test: {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean,
   * boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy,
   * String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Gson.<init>(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)"
  })
  public void testNewGson_givenTypeAdapterFactory_thenReturnBuilderFactoriesSizeIsOne() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();

    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    builderFactories.add(mock(TypeAdapterFactory.class));
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();

    // Act
    Gson actualGson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            reflectionFilters);

    // Assert
    assertEquals(1, actualGson.builderFactories.size());
    List<TypeAdapterFactory> typeAdapterFactoryList = actualGson.factories;
    assertEquals(42, typeAdapterFactoryList.size());
    assertEquals(actualGson.builderHierarchyFactories, reflectionFilters);
    Excluder excluder = actualGson.excluder;
    assertSame(excluder, actualGson.excluder());
    assertSame(excluder, typeAdapterFactoryList.get(2));
    FieldNamingStrategy expectedFieldNamingStrategyResult = actualGson.fieldNamingStrategy;
    assertSame(expectedFieldNamingStrategyResult, actualGson.fieldNamingStrategy());
  }

  /**
   * Test {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean,
   * FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List,
   * List, List, ToNumberStrategy, ToNumberStrategy, List)}.
   *
   * <ul>
   *   <li>Given {@link TypeAdapterFactory}.
   *   <li>Then return {@link Gson#builderFactories} size is two.
   * </ul>
   *
   * <p>Method under test: {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean,
   * boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy,
   * String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Gson.<init>(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)"
  })
  public void testNewGson_givenTypeAdapterFactory_thenReturnBuilderFactoriesSizeIsTwo() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();

    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    builderFactories.add(mock(TypeAdapterFactory.class));
    builderFactories.add(mock(TypeAdapterFactory.class));
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();

    // Act
    Gson actualGson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            reflectionFilters);

    // Assert
    assertEquals(2, actualGson.builderFactories.size());
    List<TypeAdapterFactory> typeAdapterFactoryList = actualGson.factories;
    assertEquals(42, typeAdapterFactoryList.size());
    assertEquals(actualGson.builderHierarchyFactories, reflectionFilters);
    Excluder excluder = actualGson.excluder;
    assertSame(excluder, actualGson.excluder());
    assertSame(excluder, typeAdapterFactoryList.get(2));
    FieldNamingStrategy expectedFieldNamingStrategyResult = actualGson.fieldNamingStrategy;
    assertSame(expectedFieldNamingStrategyResult, actualGson.fieldNamingStrategy());
  }

  /**
   * Test {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean,
   * FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List,
   * List, List, ToNumberStrategy, ToNumberStrategy, List)}.
   *
   * <ul>
   *   <li>Given {@link TypeAdapterFactory}.
   *   <li>Then return {@link Gson#builderHierarchyFactories} size is one.
   * </ul>
   *
   * <p>Method under test: {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean,
   * boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy,
   * String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Gson.<init>(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)"
  })
  public void testNewGson_givenTypeAdapterFactory_thenReturnBuilderHierarchyFactoriesSizeIsOne() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();

    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    builderHierarchyFactories.add(mock(TypeAdapterFactory.class));
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();

    // Act
    Gson actualGson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            reflectionFilters);

    // Assert
    assertEquals(1, actualGson.builderHierarchyFactories.size());
    List<TypeAdapterFactory> typeAdapterFactoryList = actualGson.factories;
    assertEquals(42, typeAdapterFactoryList.size());
    assertEquals(actualGson.builderFactories, reflectionFilters);
    Excluder excluder = actualGson.excluder;
    assertSame(excluder, actualGson.excluder());
    assertSame(excluder, typeAdapterFactoryList.get(2));
    FieldNamingStrategy expectedFieldNamingStrategyResult = actualGson.fieldNamingStrategy;
    assertSame(expectedFieldNamingStrategyResult, actualGson.fieldNamingStrategy());
  }

  /**
   * Test {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean,
   * FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List,
   * List, List, ToNumberStrategy, ToNumberStrategy, List)}.
   *
   * <ul>
   *   <li>Given {@link TypeAdapterFactory}.
   *   <li>Then return {@link Gson#builderHierarchyFactories} size is two.
   * </ul>
   *
   * <p>Method under test: {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean,
   * boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy,
   * String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Gson.<init>(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)"
  })
  public void testNewGson_givenTypeAdapterFactory_thenReturnBuilderHierarchyFactoriesSizeIsTwo() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();

    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    builderHierarchyFactories.add(mock(TypeAdapterFactory.class));
    builderHierarchyFactories.add(mock(TypeAdapterFactory.class));
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();

    // Act
    Gson actualGson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            reflectionFilters);

    // Assert
    assertEquals(2, actualGson.builderHierarchyFactories.size());
    List<TypeAdapterFactory> typeAdapterFactoryList = actualGson.factories;
    assertEquals(42, typeAdapterFactoryList.size());
    assertEquals(actualGson.builderFactories, reflectionFilters);
    Excluder excluder = actualGson.excluder;
    assertSame(excluder, actualGson.excluder());
    assertSame(excluder, typeAdapterFactoryList.get(2));
    FieldNamingStrategy expectedFieldNamingStrategyResult = actualGson.fieldNamingStrategy;
    assertSame(expectedFieldNamingStrategyResult, actualGson.fieldNamingStrategy());
  }

  /**
   * Test {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean,
   * FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List,
   * List, List, ToNumberStrategy, ToNumberStrategy, List)}.
   *
   * <ul>
   *   <li>Given {@link TypeAdapterFactory}.
   *   <li>Then return {@link Gson#factories} size is forty-four.
   * </ul>
   *
   * <p>Method under test: {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean,
   * boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy,
   * String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Gson.<init>(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)"
  })
  public void testNewGson_givenTypeAdapterFactory_thenReturnFactoriesSizeIsFortyFour() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(mock(TypeAdapterFactory.class));
    factoriesToBeAdded.add(mock(TypeAdapterFactory.class));
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();

    // Act
    Gson actualGson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            reflectionFilters);

    // Assert
    List<TypeAdapterFactory> typeAdapterFactoryList = actualGson.factories;
    assertEquals(44, typeAdapterFactoryList.size());
    assertTrue(typeAdapterFactoryList.get(41) instanceof JsonAdapterAnnotationTypeAdapterFactory);
    assertTrue(typeAdapterFactoryList.get(43) instanceof ReflectiveTypeAdapterFactory);
    assertEquals(actualGson.builderFactories, reflectionFilters);
  }

  /**
   * Test {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean,
   * FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List,
   * List, List, ToNumberStrategy, ToNumberStrategy, List)}.
   *
   * <ul>
   *   <li>Given {@link TypeAdapterFactory}.
   *   <li>Then return {@link Gson#factories} size is forty-three.
   * </ul>
   *
   * <p>Method under test: {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean,
   * boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy,
   * String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Gson.<init>(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)"
  })
  public void testNewGson_givenTypeAdapterFactory_thenReturnFactoriesSizeIsFortyThree() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(mock(TypeAdapterFactory.class));
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();

    // Act
    Gson actualGson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            reflectionFilters);

    // Assert
    List<TypeAdapterFactory> typeAdapterFactoryList = actualGson.factories;
    assertEquals(43, typeAdapterFactoryList.size());
    assertTrue(typeAdapterFactoryList.get(40) instanceof JsonAdapterAnnotationTypeAdapterFactory);
    assertTrue(typeAdapterFactoryList.get(42) instanceof ReflectiveTypeAdapterFactory);
    assertEquals(actualGson.builderFactories, reflectionFilters);
  }

  /**
   * Test {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean,
   * FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List,
   * List, List, ToNumberStrategy, ToNumberStrategy, List)}.
   *
   * <ul>
   *   <li>When {@link Gson#DEFAULT_NUMBER_TO_NUMBER_STRATEGY}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean,
   * boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy,
   * String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Gson.<init>(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)"
  })
  public void testNewGson_whenDefault_number_to_number_strategy() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();

    // Act
    Gson actualGson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            Gson.DEFAULT_NUMBER_TO_NUMBER_STRATEGY,
            reflectionFilters);

    // Assert
    List<TypeAdapterFactory> typeAdapterFactoryList = actualGson.factories;
    assertEquals(42, typeAdapterFactoryList.size());
    assertEquals(actualGson.builderFactories, reflectionFilters);
    Excluder excluder = actualGson.excluder;
    assertSame(excluder, actualGson.excluder());
    assertSame(excluder, typeAdapterFactoryList.get(2));
    FieldNamingStrategy expectedFieldNamingStrategyResult = actualGson.fieldNamingStrategy;
    assertSame(expectedFieldNamingStrategyResult, actualGson.fieldNamingStrategy());
  }

  /**
   * Test {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean,
   * FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List,
   * List, List, ToNumberStrategy, ToNumberStrategy, List)}.
   *
   * <ul>
   *   <li>When {@link Gson#DEFAULT_OBJECT_TO_NUMBER_STRATEGY}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean,
   * boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy,
   * String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Gson.<init>(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)"
  })
  public void testNewGson_whenDefault_object_to_number_strategy() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();

    // Act
    Gson actualGson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            Gson.DEFAULT_OBJECT_TO_NUMBER_STRATEGY,
            numberToNumberStrategy,
            reflectionFilters);

    // Assert
    List<TypeAdapterFactory> typeAdapterFactoryList = actualGson.factories;
    assertEquals(42, typeAdapterFactoryList.size());
    assertEquals(actualGson.builderFactories, reflectionFilters);
    Excluder excluder = actualGson.excluder;
    assertSame(excluder, actualGson.excluder());
    assertSame(excluder, typeAdapterFactoryList.get(2));
    FieldNamingStrategy expectedFieldNamingStrategyResult = actualGson.fieldNamingStrategy;
    assertSame(expectedFieldNamingStrategyResult, actualGson.fieldNamingStrategy());
  }

  /**
   * Test {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean,
   * FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List,
   * List, List, ToNumberStrategy, ToNumberStrategy, List)}.
   *
   * <ul>
   *   <li>When {@code DEFAULT}.
   *   <li>Then {@link ArrayList#ArrayList()} is {@link Gson#builderFactories}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean,
   * boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy,
   * String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Gson.<init>(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)"
  })
  public void testNewGson_whenDefault_thenArrayListIsBuilderFactories() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();

    // Act
    Gson actualGson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            reflectionFilters);

    // Assert
    List<TypeAdapterFactory> typeAdapterFactoryList = actualGson.factories;
    assertEquals(42, typeAdapterFactoryList.size());
    assertEquals(actualGson.builderFactories, reflectionFilters);
    Excluder excluder = actualGson.excluder;
    assertSame(excluder, actualGson.excluder());
    assertSame(excluder, typeAdapterFactoryList.get(2));
    FieldNamingStrategy expectedFieldNamingStrategyResult = actualGson.fieldNamingStrategy;
    assertSame(expectedFieldNamingStrategyResult, actualGson.fieldNamingStrategy());
  }

  /**
   * Test {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean,
   * FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List,
   * List, List, ToNumberStrategy, ToNumberStrategy, List)}.
   *
   * <ul>
   *   <li>When {@code false}.
   *   <li>Then return not {@link Gson#serializeSpecialFloatingPointValues}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean,
   * boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy,
   * String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Gson.<init>(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)"
  })
  public void testNewGson_whenFalse_thenReturnNotSerializeSpecialFloatingPointValues() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();

    // Act
    Gson actualGson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            false,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            reflectionFilters);

    // Assert
    List<TypeAdapterFactory> typeAdapterFactoryList = actualGson.factories;
    assertEquals(42, typeAdapterFactoryList.size());
    assertFalse(actualGson.serializeSpecialFloatingPointValues);
    assertEquals(actualGson.builderFactories, reflectionFilters);
    Excluder excluder = actualGson.excluder;
    assertSame(excluder, actualGson.excluder());
    assertSame(excluder, typeAdapterFactoryList.get(2));
    FieldNamingStrategy expectedFieldNamingStrategyResult = actualGson.fieldNamingStrategy;
    assertSame(expectedFieldNamingStrategyResult, actualGson.fieldNamingStrategy());
  }

  /**
   * Test {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean,
   * FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List,
   * List, List, ToNumberStrategy, ToNumberStrategy, List)}.
   *
   * <ul>
   *   <li>When {@code STRING}.
   *   <li>Then return {@link Gson#longSerializationPolicy} is {@code STRING}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean,
   * boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy,
   * String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Gson.<init>(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)"
  })
  public void testNewGson_whenString_thenReturnLongSerializationPolicyIsString() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();

    // Act
    Gson actualGson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.STRING,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            reflectionFilters);

    // Assert
    List<TypeAdapterFactory> typeAdapterFactoryList = actualGson.factories;
    assertEquals(42, typeAdapterFactoryList.size());
    assertEquals(LongSerializationPolicy.STRING, actualGson.longSerializationPolicy);
    assertEquals(actualGson.builderFactories, reflectionFilters);
    Excluder excluder = actualGson.excluder;
    assertSame(excluder, actualGson.excluder());
    assertSame(excluder, typeAdapterFactoryList.get(2));
    FieldNamingStrategy expectedFieldNamingStrategyResult = actualGson.fieldNamingStrategy;
    assertSame(expectedFieldNamingStrategyResult, actualGson.fieldNamingStrategy());
  }

  /**
   * Test {@link Gson#newBuilder()}.
   *
   * <p>Method under test: {@link Gson#newBuilder()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"GsonBuilder Gson.newBuilder()"})
  public void testNewBuilder() {
    // Arrange
    Gson gson = new Gson();

    // Act and Assert
    Gson createResult = gson.newBuilder().create();
    assertNull(createResult.strictness);
    assertNull(createResult.datePattern);
    assertEquals(2, createResult.dateStyle);
    assertEquals(2, createResult.timeStyle);
    assertEquals(42, createResult.factories.size());
    assertEquals(LongSerializationPolicy.DEFAULT, createResult.longSerializationPolicy);
    assertFalse(createResult.serializeNulls());
    assertFalse(createResult.complexMapKeySerialization);
    assertFalse(createResult.generateNonExecutableJson);
    assertFalse(createResult.serializeNulls);
    assertFalse(createResult.serializeSpecialFloatingPointValues);
    assertTrue(createResult.htmlSafe());
    assertTrue(createResult.builderFactories.isEmpty());
    assertTrue(createResult.builderHierarchyFactories.isEmpty());
    assertTrue(createResult.reflectionFilters.isEmpty());
    assertTrue(createResult.instanceCreators.isEmpty());
    assertTrue(createResult.htmlSafe);
    assertTrue(createResult.useJdkUnsafe);
    Excluder excluder = gson.excluder;
    assertSame(excluder, createResult.excluder());
    assertSame(excluder, createResult.excluder);
    FieldNamingStrategy fieldNamingStrategy = gson.fieldNamingStrategy;
    assertSame(fieldNamingStrategy, createResult.fieldNamingStrategy());
    assertSame(fieldNamingStrategy, createResult.fieldNamingStrategy);
    assertSame(gson.formattingStyle, createResult.formattingStyle);
    assertSame(gson.numberToNumberStrategy, createResult.numberToNumberStrategy);
    assertSame(gson.objectToNumberStrategy, createResult.objectToNumberStrategy);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Gson#toString()}
   *   <li>{@link Gson#excluder()}
   *   <li>{@link Gson#fieldNamingStrategy()}
   *   <li>{@link Gson#htmlSafe()}
   *   <li>{@link Gson#serializeNulls()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Excluder Gson.excluder()",
    "FieldNamingStrategy Gson.fieldNamingStrategy()",
    "boolean Gson.htmlSafe()",
    "boolean Gson.serializeNulls()",
    "String Gson.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    Gson gson = new Gson();

    // Act
    gson.toString();
    Excluder actualExcluderResult = gson.excluder();
    FieldNamingStrategy actualFieldNamingStrategyResult = gson.fieldNamingStrategy();
    boolean actualHtmlSafeResult = gson.htmlSafe();

    // Assert
    assertFalse(gson.serializeNulls());
    assertTrue(actualHtmlSafeResult);
    assertSame(gson.fieldNamingStrategy, actualFieldNamingStrategyResult);
    assertSame(actualExcluderResult.DEFAULT, actualExcluderResult);
  }

  /**
   * Test {@link Gson#checkValidFloatingPoint(double)}.
   *
   * <ul>
   *   <li>When {@link Double#NaN}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#checkValidFloatingPoint(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.checkValidFloatingPoint(double)"})
  public void testCheckValidFloatingPoint_whenNaN_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> Gson.checkValidFloatingPoint(Double.NaN));
  }

  /**
   * Test {@link Gson#getAdapter(Class)} with {@code Class}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code Object}.
   *   <li>Then return {@link ObjectTypeAdapter}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#getAdapter(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapter Gson.getAdapter(Class)"})
  public void testGetAdapterWithClass_givenGson_whenJavaLangObject_thenReturnObjectTypeAdapter() {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;

    // Act and Assert
    assertTrue(gson.getAdapter(type) instanceof ObjectTypeAdapter);
  }

  /**
   * Test {@link Gson#getAdapter(TypeToken)} with {@code TypeToken}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return {@link ObjectTypeAdapter}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#getAdapter(TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapter Gson.getAdapter(TypeToken)"})
  public void testGetAdapterWithTypeToken_whenJavaLangObject_thenReturnObjectTypeAdapter() {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> type2 = TypeToken.get(type);

    // Act and Assert
    assertTrue(gson.getAdapter(type2) instanceof ObjectTypeAdapter);
  }

  /**
   * Test {@link Gson#getDelegateAdapter(TypeAdapterFactory, TypeToken)}.
   *
   * <ul>
   *   <li>When {@link Excluder#DEFAULT}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#getDelegateAdapter(TypeAdapterFactory, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapter Gson.getDelegateAdapter(TypeAdapterFactory, TypeToken)"})
  public void testGetDelegateAdapter_whenDefault() {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> type2 = TypeToken.get(type);

    // Act and Assert
    assertEquals("{}", gson.getDelegateAdapter(Excluder.DEFAULT, type2).toJson("Value"));
  }

  /**
   * Test {@link Gson#getDelegateAdapter(TypeAdapterFactory, TypeToken)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return {@link ObjectTypeAdapter}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#getDelegateAdapter(TypeAdapterFactory, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapter Gson.getDelegateAdapter(TypeAdapterFactory, TypeToken)"})
  public void testGetDelegateAdapter_whenJavaLangObject_thenReturnObjectTypeAdapter() {
    // Arrange
    Gson gson = new Gson();
    TypeAdapterFactory skipPast = mock(TypeAdapterFactory.class);
    Class<Object> type = Object.class;
    TypeToken<Object> type2 = TypeToken.get(type);

    // Act and Assert
    assertTrue(gson.getDelegateAdapter(skipPast, type2) instanceof ObjectTypeAdapter);
  }

  /**
   * Test {@link Gson#getDelegateAdapter(TypeAdapterFactory, TypeToken)}.
   *
   * <ul>
   *   <li>When {@link TypeAdapters#JSON_ELEMENT_FACTORY}.
   *   <li>Then return {@link ObjectTypeAdapter}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#getDelegateAdapter(TypeAdapterFactory, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapter Gson.getDelegateAdapter(TypeAdapterFactory, TypeToken)"})
  public void testGetDelegateAdapter_whenJson_element_factory_thenReturnObjectTypeAdapter() {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> type2 = TypeToken.get(type);

    // Act and Assert
    assertTrue(
        gson.getDelegateAdapter(TypeAdapters.JSON_ELEMENT_FACTORY, type2)
            instanceof ObjectTypeAdapter);
  }

  /**
   * Test {@link Gson#toJsonTree(Object)} with {@code src}.
   *
   * <p>Method under test: {@link Gson#toJsonTree(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Gson.toJsonTree(Object)"})
  public void testToJsonTreeWithSrc() {
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
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    // Act
    JsonElement actualToJsonTreeResult =
        new Gson(
                Excluder.DEFAULT,
                fieldNamingStrategy,
                instanceCreators,
                true,
                true,
                true,
                true,
                Gson.DEFAULT_FORMATTING_STYLE,
                Strictness.LENIENT,
                true,
                true,
                LongSerializationPolicy.DEFAULT,
                "2020-03-01",
                Integer.SIZE,
                Integer.SIZE,
                builderFactories,
                builderHierarchyFactories,
                factoriesToBeAdded,
                objectToNumberStrategy,
                numberToNumberStrategy,
                new ArrayList<>())
            .toJsonTree("Src");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertTrue(actualToJsonTreeResult instanceof JsonArray);
    assertEquals(jsonArray, actualToJsonTreeResult);
  }

  /**
   * Test {@link Gson#toJsonTree(Object, Type)} with {@code src}, {@code typeOfSrc}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@link Excluder#DEFAULT}.
   *   <li>Then return size is six.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJsonTree(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Gson.toJsonTree(Object, Type)"})
  public void testToJsonTreeWithSrcTypeOfSrc_givenGson_whenDefault_thenReturnSizeIsSix() {
    // Arrange
    Gson gson = new Gson();
    Class<Object> typeOfSrc = Object.class;

    // Act
    JsonElement actualToJsonTreeResult = gson.toJsonTree(Excluder.DEFAULT, typeOfSrc);

    // Assert
    assertTrue(actualToJsonTreeResult instanceof JsonObject);
    assertEquals(6, ((JsonObject) actualToJsonTreeResult).size());
    assertFalse(((JsonObject) actualToJsonTreeResult).isEmpty());
    assertTrue(actualToJsonTreeResult.isJsonObject());
    assertSame(actualToJsonTreeResult, actualToJsonTreeResult.getAsJsonObject());
  }

  /**
   * Test {@link Gson#toJsonTree(Object, Type)} with {@code src}, {@code typeOfSrc}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@link HashMap#HashMap()}.
   *   <li>Then return size is zero.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJsonTree(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Gson.toJsonTree(Object, Type)"})
  public void testToJsonTreeWithSrcTypeOfSrc_givenGson_whenHashMap_thenReturnSizeIsZero() {
    // Arrange
    Gson gson = new Gson();
    HashMap<Object, Object> objectObjectMap = new HashMap<>();
    Class<Object> typeOfSrc = Object.class;

    // Act
    JsonElement actualToJsonTreeResult = gson.toJsonTree(objectObjectMap, typeOfSrc);

    // Assert
    assertTrue(actualToJsonTreeResult instanceof JsonObject);
    assertEquals(0, ((JsonObject) actualToJsonTreeResult).size());
    assertTrue(actualToJsonTreeResult.isJsonObject());
    assertTrue(((JsonObject) actualToJsonTreeResult).isEmpty());
    assertSame(actualToJsonTreeResult, actualToJsonTreeResult.getAsJsonObject());
  }

  /**
   * Test {@link Gson#toJsonTree(Object, Type)} with {@code src}, {@code typeOfSrc}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code null}.
   *   <li>Then return {@link JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJsonTree(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Gson.toJsonTree(Object, Type)"})
  public void testToJsonTreeWithSrcTypeOfSrc_givenGson_whenNull_thenReturnInstance() {
    // Arrange
    Gson gson = new Gson();
    Class<Object> typeOfSrc = Object.class;

    // Act
    JsonElement actualToJsonTreeResult = gson.toJsonTree(null, typeOfSrc);

    // Assert
    assertSame(((JsonNull) actualToJsonTreeResult).INSTANCE, actualToJsonTreeResult);
  }

  /**
   * Test {@link Gson#toJsonTree(Object, Type)} with {@code src}, {@code typeOfSrc}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code null}.
   *   <li>Then return {@link JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJsonTree(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Gson.toJsonTree(Object, Type)"})
  public void testToJsonTreeWithSrcTypeOfSrc_givenGson_whenNull_thenReturnInstance2() {
    // Arrange
    Gson gson = new Gson();
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act
    JsonElement actualToJsonTreeResult = gson.toJsonTree(null, typeOfSrc);

    // Assert
    assertSame(((JsonNull) actualToJsonTreeResult).INSTANCE, actualToJsonTreeResult);
  }

  /**
   * Test {@link Gson#toJsonTree(Object, Type)} with {@code src}, {@code typeOfSrc}.
   *
   * <ul>
   *   <li>Given {@link JsonSerializer} {@link JsonSerializer#serialize(Object, Type,
   *       JsonSerializationContext)} return {@link JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJsonTree(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Gson.toJsonTree(Object, Type)"})
  public void testToJsonTreeWithSrcTypeOfSrc_givenJsonSerializerSerializeReturnInstance() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(JsonNull.INSTANCE);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson2 =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act
    JsonElement actualToJsonTreeResult = gson2.toJsonTree("Src", typeOfSrc);

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertSame(((JsonNull) actualToJsonTreeResult).INSTANCE, actualToJsonTreeResult);
  }

  /**
   * Test {@link Gson#toJsonTree(Object, Type)} with {@code src}, {@code typeOfSrc}.
   *
   * <ul>
   *   <li>Then AsNumber return {@link LazilyParsedNumber}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJsonTree(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Gson.toJsonTree(Object, Type)"})
  public void testToJsonTreeWithSrcTypeOfSrc_thenAsNumberReturnLazilyParsedNumber() {
    // Arrange
    Gson gson = new Gson();
    Class<Object> typeOfSrc = Object.class;

    // Act
    JsonElement actualToJsonTreeResult = gson.toJsonTree("Src", typeOfSrc);

    // Assert
    assertTrue(actualToJsonTreeResult instanceof JsonPrimitive);
    Number asNumber = actualToJsonTreeResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("Src", actualToJsonTreeResult.getAsString());
    assertEquals("Src", asNumber.toString());
    assertEquals('S', actualToJsonTreeResult.getAsCharacter());
    assertSame(actualToJsonTreeResult, actualToJsonTreeResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link Gson#toJsonTree(Object, Type)} with {@code src}, {@code typeOfSrc}.
   *
   * <ul>
   *   <li>Then AsNumber return {@link LazilyParsedNumber}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJsonTree(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Gson.toJsonTree(Object, Type)"})
  public void testToJsonTreeWithSrcTypeOfSrc_thenAsNumberReturnLazilyParsedNumber2() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> typeOfSrc = Object.class;

    // Act
    JsonElement actualToJsonTreeResult = gson.toJsonTree("Src", typeOfSrc);

    // Assert
    assertTrue(actualToJsonTreeResult instanceof JsonPrimitive);
    Number asNumber = actualToJsonTreeResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("Src", actualToJsonTreeResult.getAsString());
    assertEquals("Src", asNumber.toString());
    assertEquals('S', actualToJsonTreeResult.getAsCharacter());
    assertSame(actualToJsonTreeResult, actualToJsonTreeResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link Gson#toJsonTree(Object, Type)} with {@code src}, {@code typeOfSrc}.
   *
   * <ul>
   *   <li>Then return AsString is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJsonTree(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Gson.toJsonTree(Object, Type)"})
  public void testToJsonTreeWithSrcTypeOfSrc_thenReturnAsStringIs42() {
    // Arrange
    Gson gson = new Gson();
    LazilyParsedNumber lazilyParsedNumber = new LazilyParsedNumber("42");
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act
    JsonElement actualToJsonTreeResult = gson.toJsonTree(lazilyParsedNumber, typeOfSrc);

    // Assert
    assertTrue(actualToJsonTreeResult instanceof JsonPrimitive);
    assertEquals("42", actualToJsonTreeResult.getAsString());
    assertEquals('4', actualToJsonTreeResult.getAsCharacter());
    assertEquals(42, actualToJsonTreeResult.getAsInt());
    assertEquals(42.0d, actualToJsonTreeResult.getAsDouble(), 0.0);
    assertEquals(42.0f, actualToJsonTreeResult.getAsFloat(), 0.0f);
    assertEquals(42L, actualToJsonTreeResult.getAsLong());
    assertEquals((short) 42, actualToJsonTreeResult.getAsShort());
    assertTrue(((JsonPrimitive) actualToJsonTreeResult).isNumber());
    BigDecimal expectedAsBigDecimal = new BigDecimal("42");
    assertEquals(expectedAsBigDecimal, actualToJsonTreeResult.getAsBigDecimal());
    assertEquals('*', actualToJsonTreeResult.getAsByte());
    assertSame(lazilyParsedNumber, actualToJsonTreeResult.getAsNumber());
    assertSame(actualToJsonTreeResult, actualToJsonTreeResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link Gson#toJsonTree(Object, Type)} with {@code src}, {@code typeOfSrc}.
   *
   * <ul>
   *   <li>Then return {@link JsonArray}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJsonTree(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Gson.toJsonTree(Object, Type)"})
  public void testToJsonTreeWithSrcTypeOfSrc_thenReturnJsonArray() {
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
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson2 =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act
    JsonElement actualToJsonTreeResult = gson2.toJsonTree("Src", typeOfSrc);

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertTrue(actualToJsonTreeResult instanceof JsonArray);
    assertEquals(jsonArray, actualToJsonTreeResult);
  }

  /**
   * Test {@link Gson#toJsonTree(Object, Type)} with {@code src}, {@code typeOfSrc}.
   *
   * <ul>
   *   <li>Then return {@link JsonObject} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJsonTree(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Gson.toJsonTree(Object, Type)"})
  public void testToJsonTreeWithSrcTypeOfSrc_thenReturnJsonObject() {
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
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson2 =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act
    JsonElement actualToJsonTreeResult = gson2.toJsonTree("Src", typeOfSrc);

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertTrue(actualToJsonTreeResult instanceof JsonObject);
    assertEquals(jsonObject, actualToJsonTreeResult);
  }

  /**
   * Test {@link Gson#toJsonTree(Object, Type)} with {@code src}, {@code typeOfSrc}.
   *
   * <ul>
   *   <li>Then return {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJsonTree(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Gson.toJsonTree(Object, Type)"})
  public void testToJsonTreeWithSrcTypeOfSrc_thenReturnJsonPrimitiveWithBoolIsTrue() {
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
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson2 =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act
    JsonElement actualToJsonTreeResult = gson2.toJsonTree("Src", typeOfSrc);

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertTrue(actualToJsonTreeResult instanceof JsonPrimitive);
    assertEquals(jsonPrimitive, actualToJsonTreeResult);
  }

  /**
   * Test {@link Gson#toJsonTree(Object, Type)} with {@code src}, {@code typeOfSrc}.
   *
   * <ul>
   *   <li>Then return {@link JsonPrimitive#JsonPrimitive(String)} with string is {@code out ==
   *       null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJsonTree(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Gson.toJsonTree(Object, Type)"})
  public void testToJsonTreeWithSrcTypeOfSrc_thenReturnJsonPrimitiveWithStringIsOutNull() {
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
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson2 =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act
    JsonElement actualToJsonTreeResult = gson2.toJsonTree("Src", typeOfSrc);

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertTrue(actualToJsonTreeResult instanceof JsonPrimitive);
    assertEquals(jsonPrimitive, actualToJsonTreeResult);
  }

  /**
   * Test {@link Gson#toJsonTree(Object, Type)} with {@code src}, {@code typeOfSrc}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJsonTree(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Gson.toJsonTree(Object, Type)"})
  public void testToJsonTreeWithSrcTypeOfSrc_thenReturnSizeIsOne() {
    // Arrange
    Gson gson = new Gson();
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    CollectionTypeAdapterFactory collectionTypeAdapterFactory =
        new CollectionTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>()));
    Class<Object> typeOfSrc = Object.class;

    // Act
    JsonElement actualToJsonTreeResult = gson.toJsonTree(collectionTypeAdapterFactory, typeOfSrc);

    // Assert
    assertTrue(actualToJsonTreeResult instanceof JsonObject);
    assertEquals(1, ((JsonObject) actualToJsonTreeResult).size());
    assertFalse(((JsonObject) actualToJsonTreeResult).isEmpty());
    assertTrue(actualToJsonTreeResult.isJsonObject());
    assertSame(actualToJsonTreeResult, actualToJsonTreeResult.getAsJsonObject());
  }

  /**
   * Test {@link Gson#toJsonTree(Object, Type)} with {@code src}, {@code typeOfSrc}.
   *
   * <ul>
   *   <li>When {@code TypeAdapterFactory}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJsonTree(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Gson.toJsonTree(Object, Type)"})
  public void testToJsonTreeWithSrcTypeOfSrc_whenComGoogleGsonTypeAdapterFactory() {
    // Arrange
    Gson gson = new Gson();
    Class<TypeAdapterFactory> typeOfSrc = TypeAdapterFactory.class;

    // Act
    JsonElement actualToJsonTreeResult = gson.toJsonTree("Src", typeOfSrc);

    // Assert
    assertTrue(actualToJsonTreeResult instanceof JsonObject);
    assertEquals(0, ((JsonObject) actualToJsonTreeResult).size());
    assertTrue(actualToJsonTreeResult.isJsonObject());
    assertTrue(((JsonObject) actualToJsonTreeResult).isEmpty());
    assertSame(actualToJsonTreeResult, actualToJsonTreeResult.getAsJsonObject());
  }

  /**
   * Test {@link Gson#toJsonTree(Object, Type)} with {@code src}, {@code typeOfSrc}.
   *
   * <ul>
   *   <li>When {@link FutureTypeAdapter} (default constructor).
   *   <li>Then return size is zero.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJsonTree(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Gson.toJsonTree(Object, Type)"})
  public void testToJsonTreeWithSrcTypeOfSrc_whenFutureTypeAdapter_thenReturnSizeIsZero() {
    // Arrange
    Gson gson = new Gson();
    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    Class<Object> typeOfSrc = Object.class;

    // Act
    JsonElement actualToJsonTreeResult = gson.toJsonTree(futureTypeAdapter, typeOfSrc);

    // Assert
    assertTrue(actualToJsonTreeResult instanceof JsonObject);
    assertEquals(0, ((JsonObject) actualToJsonTreeResult).size());
    assertTrue(actualToJsonTreeResult.isJsonObject());
    assertTrue(((JsonObject) actualToJsonTreeResult).isEmpty());
    assertSame(actualToJsonTreeResult, actualToJsonTreeResult.getAsJsonObject());
  }

  /**
   * Test {@link Gson#toJsonTree(Object, Type)} with {@code src}, {@code typeOfSrc}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJsonTree(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Gson.toJsonTree(Object, Type)"})
  public void testToJsonTreeWithSrcTypeOfSrc_whenNull_thenReturnInstance() {
    // Arrange
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson2 =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act
    JsonElement actualToJsonTreeResult = gson2.toJsonTree(null, typeOfSrc);

    // Assert
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertSame(((JsonNull) actualToJsonTreeResult).INSTANCE, actualToJsonTreeResult);
  }

  /**
   * Test {@link Gson#toJsonTree(Object)} with {@code src}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@link Excluder#DEFAULT}.
   *   <li>Then return size is six.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJsonTree(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Gson.toJsonTree(Object)"})
  public void testToJsonTreeWithSrc_givenGson_whenDefault_thenReturnSizeIsSix() {
    // Arrange and Act
    JsonElement actualToJsonTreeResult = new Gson().toJsonTree(Excluder.DEFAULT);

    // Assert
    assertTrue(actualToJsonTreeResult instanceof JsonObject);
    assertEquals(6, ((JsonObject) actualToJsonTreeResult).size());
    assertFalse(((JsonObject) actualToJsonTreeResult).isEmpty());
    assertTrue(actualToJsonTreeResult.isJsonObject());
    assertSame(actualToJsonTreeResult, actualToJsonTreeResult.getAsJsonObject());
  }

  /**
   * Test {@link Gson#toJsonTree(Object)} with {@code src}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@link FutureTypeAdapter} (default constructor).
   *   <li>Then return size is zero.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJsonTree(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Gson.toJsonTree(Object)"})
  public void testToJsonTreeWithSrc_givenGson_whenFutureTypeAdapter_thenReturnSizeIsZero() {
    // Arrange
    Gson gson = new Gson();

    // Act
    JsonElement actualToJsonTreeResult = gson.toJsonTree(new FutureTypeAdapter<>());

    // Assert
    assertTrue(actualToJsonTreeResult instanceof JsonObject);
    assertEquals(0, ((JsonObject) actualToJsonTreeResult).size());
    assertTrue(actualToJsonTreeResult.isJsonObject());
    assertTrue(((JsonObject) actualToJsonTreeResult).isEmpty());
    assertSame(actualToJsonTreeResult, actualToJsonTreeResult.getAsJsonObject());
  }

  /**
   * Test {@link Gson#toJsonTree(Object)} with {@code src}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@link HashMap#HashMap()}.
   *   <li>Then return size is zero.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJsonTree(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Gson.toJsonTree(Object)"})
  public void testToJsonTreeWithSrc_givenGson_whenHashMap_thenReturnSizeIsZero() {
    // Arrange
    Gson gson = new Gson();

    // Act
    JsonElement actualToJsonTreeResult = gson.toJsonTree(new HashMap<>());

    // Assert
    assertTrue(actualToJsonTreeResult instanceof JsonObject);
    assertEquals(0, ((JsonObject) actualToJsonTreeResult).size());
    assertTrue(actualToJsonTreeResult.isJsonObject());
    assertTrue(((JsonObject) actualToJsonTreeResult).isEmpty());
    assertSame(actualToJsonTreeResult, actualToJsonTreeResult.getAsJsonObject());
  }

  /**
   * Test {@link Gson#toJsonTree(Object)} with {@code src}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code null}.
   *   <li>Then return {@link JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJsonTree(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Gson.toJsonTree(Object)"})
  public void testToJsonTreeWithSrc_givenGson_whenNull_thenReturnInstance() {
    // Arrange and Act
    JsonElement actualToJsonTreeResult = new Gson().toJsonTree(null);

    // Assert
    assertSame(((JsonNull) actualToJsonTreeResult).INSTANCE, actualToJsonTreeResult);
  }

  /**
   * Test {@link Gson#toJsonTree(Object)} with {@code src}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code Src}.
   *   <li>Then AsNumber return {@link LazilyParsedNumber}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJsonTree(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Gson.toJsonTree(Object)"})
  public void testToJsonTreeWithSrc_givenGson_whenSrc_thenAsNumberReturnLazilyParsedNumber() {
    // Arrange and Act
    JsonElement actualToJsonTreeResult = new Gson().toJsonTree("Src");

    // Assert
    assertTrue(actualToJsonTreeResult instanceof JsonPrimitive);
    Number asNumber = actualToJsonTreeResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("Src", actualToJsonTreeResult.getAsString());
    assertEquals("Src", asNumber.toString());
    assertEquals('S', actualToJsonTreeResult.getAsCharacter());
    assertSame(actualToJsonTreeResult, actualToJsonTreeResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link Gson#toJsonTree(Object)} with {@code src}.
   *
   * <ul>
   *   <li>Given {@link JsonSerializer} {@link JsonSerializer#serialize(Object, Type,
   *       JsonSerializationContext)} return {@link JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJsonTree(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Gson.toJsonTree(Object)"})
  public void testToJsonTreeWithSrc_givenJsonSerializerSerializeReturnInstance() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(JsonNull.INSTANCE);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    // Act
    JsonElement actualToJsonTreeResult =
        new Gson(
                Excluder.DEFAULT,
                fieldNamingStrategy,
                instanceCreators,
                true,
                true,
                true,
                true,
                Gson.DEFAULT_FORMATTING_STYLE,
                Strictness.LENIENT,
                true,
                true,
                LongSerializationPolicy.DEFAULT,
                "2020-03-01",
                Integer.SIZE,
                Integer.SIZE,
                builderFactories,
                builderHierarchyFactories,
                factoriesToBeAdded,
                objectToNumberStrategy,
                numberToNumberStrategy,
                new ArrayList<>())
            .toJsonTree("Src");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertSame(((JsonNull) actualToJsonTreeResult).INSTANCE, actualToJsonTreeResult);
  }

  /**
   * Test {@link Gson#toJsonTree(Object)} with {@code src}.
   *
   * <ul>
   *   <li>Given {@link TypeAdapterFactory} {@link TypeAdapterFactory#create(Gson, TypeToken)}
   *       return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJsonTree(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Gson.toJsonTree(Object)"})
  public void testToJsonTreeWithSrc_givenTypeAdapterFactoryCreateReturnNull() {
    // Arrange
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(null);

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    // Act
    JsonElement actualToJsonTreeResult =
        new Gson(
                Excluder.DEFAULT,
                fieldNamingStrategy,
                instanceCreators,
                true,
                true,
                true,
                true,
                Gson.DEFAULT_FORMATTING_STYLE,
                Strictness.LENIENT,
                true,
                true,
                LongSerializationPolicy.DEFAULT,
                "2020-03-01",
                Integer.SIZE,
                Integer.SIZE,
                builderFactories,
                builderHierarchyFactories,
                factoriesToBeAdded,
                objectToNumberStrategy,
                numberToNumberStrategy,
                new ArrayList<>())
            .toJsonTree("Src");

    // Assert
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertTrue(actualToJsonTreeResult instanceof JsonPrimitive);
    assertFalse(actualToJsonTreeResult.getAsBoolean());
    assertFalse(((JsonPrimitive) actualToJsonTreeResult).isBoolean());
    assertTrue(((JsonPrimitive) actualToJsonTreeResult).isString());
    assertSame(actualToJsonTreeResult, actualToJsonTreeResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link Gson#toJsonTree(Object)} with {@code src}.
   *
   * <ul>
   *   <li>Then return {@link JsonObject} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJsonTree(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Gson.toJsonTree(Object)"})
  public void testToJsonTreeWithSrc_thenReturnJsonObject() {
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
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    // Act
    JsonElement actualToJsonTreeResult =
        new Gson(
                Excluder.DEFAULT,
                fieldNamingStrategy,
                instanceCreators,
                true,
                true,
                true,
                true,
                Gson.DEFAULT_FORMATTING_STYLE,
                Strictness.LENIENT,
                true,
                true,
                LongSerializationPolicy.DEFAULT,
                "2020-03-01",
                Integer.SIZE,
                Integer.SIZE,
                builderFactories,
                builderHierarchyFactories,
                factoriesToBeAdded,
                objectToNumberStrategy,
                numberToNumberStrategy,
                new ArrayList<>())
            .toJsonTree("Src");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertTrue(actualToJsonTreeResult instanceof JsonObject);
    assertEquals(jsonObject, actualToJsonTreeResult);
  }

  /**
   * Test {@link Gson#toJsonTree(Object)} with {@code src}.
   *
   * <ul>
   *   <li>Then return {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJsonTree(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Gson.toJsonTree(Object)"})
  public void testToJsonTreeWithSrc_thenReturnJsonPrimitiveWithBoolIsTrue() {
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
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    // Act
    JsonElement actualToJsonTreeResult =
        new Gson(
                Excluder.DEFAULT,
                fieldNamingStrategy,
                instanceCreators,
                true,
                true,
                true,
                true,
                Gson.DEFAULT_FORMATTING_STYLE,
                Strictness.LENIENT,
                true,
                true,
                LongSerializationPolicy.DEFAULT,
                "2020-03-01",
                Integer.SIZE,
                Integer.SIZE,
                builderFactories,
                builderHierarchyFactories,
                factoriesToBeAdded,
                objectToNumberStrategy,
                numberToNumberStrategy,
                new ArrayList<>())
            .toJsonTree("Src");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertTrue(actualToJsonTreeResult instanceof JsonPrimitive);
    assertEquals(jsonPrimitive, actualToJsonTreeResult);
  }

  /**
   * Test {@link Gson#toJsonTree(Object)} with {@code src}.
   *
   * <ul>
   *   <li>Then return {@link JsonPrimitive#JsonPrimitive(String)} with string is {@code out ==
   *       null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJsonTree(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Gson.toJsonTree(Object)"})
  public void testToJsonTreeWithSrc_thenReturnJsonPrimitiveWithStringIsOutNull() {
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
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    // Act
    JsonElement actualToJsonTreeResult =
        new Gson(
                Excluder.DEFAULT,
                fieldNamingStrategy,
                instanceCreators,
                true,
                true,
                true,
                true,
                Gson.DEFAULT_FORMATTING_STYLE,
                Strictness.LENIENT,
                true,
                true,
                LongSerializationPolicy.DEFAULT,
                "2020-03-01",
                Integer.SIZE,
                Integer.SIZE,
                builderFactories,
                builderHierarchyFactories,
                factoriesToBeAdded,
                objectToNumberStrategy,
                numberToNumberStrategy,
                new ArrayList<>())
            .toJsonTree("Src");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertTrue(actualToJsonTreeResult instanceof JsonPrimitive);
    assertEquals(jsonPrimitive, actualToJsonTreeResult);
  }

  /**
   * Test {@link Gson#toJsonTree(Object)} with {@code src}.
   *
   * <ul>
   *   <li>Then return not AsBoolean.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJsonTree(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Gson.toJsonTree(Object)"})
  public void testToJsonTreeWithSrc_thenReturnNotAsBoolean() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    // Act
    JsonElement actualToJsonTreeResult =
        new Gson(
                Excluder.DEFAULT,
                fieldNamingStrategy,
                instanceCreators,
                true,
                true,
                true,
                true,
                Gson.DEFAULT_FORMATTING_STYLE,
                Strictness.LENIENT,
                true,
                true,
                LongSerializationPolicy.DEFAULT,
                "2020-03-01",
                Integer.SIZE,
                Integer.SIZE,
                builderFactories,
                builderHierarchyFactories,
                factoriesToBeAdded,
                objectToNumberStrategy,
                numberToNumberStrategy,
                new ArrayList<>())
            .toJsonTree("Src");

    // Assert
    assertTrue(actualToJsonTreeResult instanceof JsonPrimitive);
    assertFalse(actualToJsonTreeResult.getAsBoolean());
    assertFalse(((JsonPrimitive) actualToJsonTreeResult).isBoolean());
    assertTrue(((JsonPrimitive) actualToJsonTreeResult).isString());
    assertSame(actualToJsonTreeResult, actualToJsonTreeResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link Gson#toJsonTree(Object)} with {@code src}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJsonTree(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Gson.toJsonTree(Object)"})
  public void testToJsonTreeWithSrc_thenReturnSizeIsOne() {
    // Arrange
    Gson gson = new Gson();
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();

    // Act
    JsonElement actualToJsonTreeResult =
        gson.toJsonTree(
            new CollectionTypeAdapterFactory(
                new ConstructorConstructor(instanceCreators, true, new ArrayList<>())));

    // Assert
    assertTrue(actualToJsonTreeResult instanceof JsonObject);
    assertEquals(1, ((JsonObject) actualToJsonTreeResult).size());
    assertFalse(((JsonObject) actualToJsonTreeResult).isEmpty());
    assertTrue(actualToJsonTreeResult.isJsonObject());
    assertSame(actualToJsonTreeResult, actualToJsonTreeResult.getAsJsonObject());
  }

  /**
   * Test {@link Gson#toJsonTree(Object)} with {@code src}.
   *
   * <ul>
   *   <li>When {@link JsonNull#INSTANCE}.
   *   <li>Then {@link JsonNull#INSTANCE} AsJsonNull is {@link JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJsonTree(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Gson.toJsonTree(Object)"})
  public void testToJsonTreeWithSrc_whenInstance_thenInstanceAsJsonNullIsInstance() {
    // Arrange
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(mock(TypeAdapterFactory.class));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    JsonNull jsonNull = JsonNull.INSTANCE;

    // Act
    JsonElement actualToJsonTreeResult =
        new Gson(
                Excluder.DEFAULT,
                fieldNamingStrategy,
                instanceCreators,
                true,
                true,
                true,
                true,
                Gson.DEFAULT_FORMATTING_STYLE,
                Strictness.LENIENT,
                true,
                true,
                LongSerializationPolicy.DEFAULT,
                "2020-03-01",
                Integer.SIZE,
                Integer.SIZE,
                builderFactories,
                builderHierarchyFactories,
                factoriesToBeAdded,
                objectToNumberStrategy,
                numberToNumberStrategy,
                new ArrayList<>())
            .toJsonTree(jsonNull);

    // Assert
    JsonNull jsonNull2 = ((JsonNull) actualToJsonTreeResult).INSTANCE;
    assertSame(jsonNull2, actualToJsonTreeResult);
    assertSame(jsonNull2, jsonNull.getAsJsonNull());
  }

  /**
   * Test {@link Gson#toJsonTree(Object)} with {@code src}.
   *
   * <ul>
   *   <li>When {@link JsonArray#JsonArray(int)} with capacity is three.
   *   <li>Then return {@link JsonArray}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJsonTree(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Gson.toJsonTree(Object)"})
  public void testToJsonTreeWithSrc_whenJsonArrayWithCapacityIsThree_thenReturnJsonArray() {
    // Arrange
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(mock(TypeAdapterFactory.class));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonArray jsonArray = new JsonArray(3);

    // Act
    JsonElement actualToJsonTreeResult = gson.toJsonTree(jsonArray);

    // Assert
    assertTrue(actualToJsonTreeResult instanceof JsonArray);
    assertEquals(jsonArray, actualToJsonTreeResult);
  }

  /**
   * Test {@link Gson#toJson(JsonElement)} with {@code JsonElement}.
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(JsonElement)"})
  public void testToJsonWithJsonElement() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    // Act and Assert
    assertEquals(
        "null",
        new Gson(
                Excluder.DEFAULT,
                fieldNamingStrategy,
                instanceCreators,
                true,
                true,
                false,
                true,
                Gson.DEFAULT_FORMATTING_STYLE,
                Strictness.LENIENT,
                true,
                true,
                LongSerializationPolicy.DEFAULT,
                "2020-03-01",
                1,
                1,
                builderFactories,
                builderHierarchyFactories,
                factoriesToBeAdded,
                objectToNumberStrategy,
                numberToNumberStrategy,
                new ArrayList<>())
            .toJson(JsonNull.INSTANCE));
  }

  /**
   * Test {@link Gson#toJson(JsonElement)} with {@code JsonElement}.
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(JsonElement)"})
  public void testToJsonWithJsonElement2() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    // Act and Assert
    assertEquals(
        ")]}'\nnull",
        new Gson(
                Excluder.DEFAULT,
                fieldNamingStrategy,
                instanceCreators,
                true,
                true,
                true,
                true,
                Gson.DEFAULT_FORMATTING_STYLE,
                Strictness.LENIENT,
                true,
                true,
                LongSerializationPolicy.DEFAULT,
                "2020-03-01",
                Integer.SIZE,
                Integer.SIZE,
                builderFactories,
                builderHierarchyFactories,
                factoriesToBeAdded,
                objectToNumberStrategy,
                numberToNumberStrategy,
                new ArrayList<>())
            .toJson(JsonNull.INSTANCE));
  }

  /**
   * Test {@link Gson#toJson(JsonElement)} with {@code JsonElement}.
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(JsonElement)"})
  public void testToJsonWithJsonElement3() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    // Act and Assert
    assertEquals(
        ")]}'\nnull",
        new Gson(
                Excluder.DEFAULT,
                fieldNamingStrategy,
                instanceCreators,
                true,
                true,
                true,
                true,
                FormattingStyle.PRETTY,
                Strictness.LENIENT,
                true,
                true,
                LongSerializationPolicy.DEFAULT,
                "2020-03-01",
                Integer.SIZE,
                Integer.SIZE,
                builderFactories,
                builderHierarchyFactories,
                factoriesToBeAdded,
                objectToNumberStrategy,
                numberToNumberStrategy,
                new ArrayList<>())
            .toJson(JsonNull.INSTANCE));
  }

  /**
   * Test {@link Gson#toJson(JsonElement, Appendable)} with {@code JsonElement}, {@code Appendable}.
   *
   * <ul>
   *   <li>Then {@link CharArrayWriter#CharArrayWriter(int)} with one size is eight.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, Appendable)"})
  public void testToJsonWithJsonElementAppendable_thenCharArrayWriterWithOneSizeIsEight()
      throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    JsonPrimitive jsonElement = new JsonPrimitive('\u0001');
    CharArrayWriter writer = new CharArrayWriter(1);

    // Act
    gson.toJson(jsonElement, writer);

    // Assert
    assertEquals(8, writer.size());
  }

  /**
   * Test {@link Gson#toJson(JsonElement, Appendable)} with {@code JsonElement}, {@code Appendable}.
   *
   * <ul>
   *   <li>Then {@link CharArrayWriter#CharArrayWriter(int)} with one size is four.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, Appendable)"})
  public void testToJsonWithJsonElementAppendable_thenCharArrayWriterWithOneSizeIsFour()
      throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    CharArrayWriter writer = new CharArrayWriter(1);

    // Act
    gson.toJson(JsonNull.INSTANCE, writer);

    // Assert
    assertEquals(4, writer.size());
  }

  /**
   * Test {@link Gson#toJson(JsonElement, Appendable)} with {@code JsonElement}, {@code Appendable}.
   *
   * <ul>
   *   <li>Then {@link CharArrayWriter#CharArrayWriter(int)} with one size is nine.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, Appendable)"})
  public void testToJsonWithJsonElementAppendable_thenCharArrayWriterWithOneSizeIsNine()
      throws JsonIOException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    CharArrayWriter writer = new CharArrayWriter(1);

    // Act
    gson.toJson(JsonNull.INSTANCE, writer);

    // Assert
    assertEquals(9, writer.size());
  }

  /**
   * Test {@link Gson#toJson(JsonElement, Appendable)} with {@code JsonElement}, {@code Appendable}.
   *
   * <ul>
   *   <li>Then {@link CharArrayWriter#CharArrayWriter(int)} with one size is twenty-three.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, Appendable)"})
  public void testToJsonWithJsonElementAppendable_thenCharArrayWriterWithOneSizeIsTwentyThree()
      throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    JsonPrimitive jsonElement = new JsonPrimitive("out == null");
    CharArrayWriter writer = new CharArrayWriter(1);

    // Act
    gson.toJson(jsonElement, writer);

    // Assert
    assertEquals(23, writer.size());
  }

  /**
   * Test {@link Gson#toJson(JsonElement, Appendable)} with {@code JsonElement}, {@code Appendable}.
   *
   * <ul>
   *   <li>Then {@link StringWriter#StringWriter()} toString is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, Appendable)"})
  public void testToJsonWithJsonElementAppendable_thenStringWriterToStringIsNull()
      throws JsonIOException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            false,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringWriter writer = new StringWriter();

    // Act
    gson.toJson(JsonNull.INSTANCE, writer);

    // Assert
    assertEquals("null", writer.toString());
  }

  /**
   * Test {@link Gson#toJson(JsonElement, Appendable)} with {@code JsonElement}, {@code Appendable}.
   *
   * <ul>
   *   <li>When {@link JsonArray#JsonArray(int)} with capacity is three.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, Appendable)"})
  public void testToJsonWithJsonElementAppendable_whenJsonArrayWithCapacityIsThree()
      throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    JsonArray jsonElement = new JsonArray(3);
    CharArrayWriter writer = new CharArrayWriter(1);

    // Act
    gson.toJson(jsonElement, writer);

    // Assert
    assertEquals(2, writer.size());
  }

  /**
   * Test {@link Gson#toJson(JsonElement, Appendable)} with {@code JsonElement}, {@code Appendable}.
   *
   * <ul>
   *   <li>When {@link JsonObject} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, Appendable)"})
  public void testToJsonWithJsonElementAppendable_whenJsonObject() throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    JsonObject jsonElement = new JsonObject();
    CharArrayWriter writer = new CharArrayWriter(1);

    // Act
    gson.toJson(jsonElement, writer);

    // Assert
    assertEquals(2, writer.size());
  }

  /**
   * Test {@link Gson#toJson(JsonElement, Appendable)} with {@code JsonElement}, {@code Appendable}.
   *
   * <ul>
   *   <li>When {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, Appendable)"})
  public void testToJsonWithJsonElementAppendable_whenJsonPrimitiveWithBoolIsTrue()
      throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    JsonPrimitive jsonElement = new JsonPrimitive(true);
    CharArrayWriter writer = new CharArrayWriter(1);

    // Act
    gson.toJson(jsonElement, writer);

    // Assert
    assertEquals(4, writer.size());
  }

  /**
   * Test {@link Gson#toJson(JsonElement, Appendable)} with {@code JsonElement}, {@code Appendable}.
   *
   * <ul>
   *   <li>When {@link PipedWriter#PipedWriter()}.
   *   <li>Then throw {@link JsonIOException}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, Appendable)"})
  public void testToJsonWithJsonElementAppendable_whenPipedWriter_thenThrowJsonIOException()
      throws JsonIOException {
    // Arrange
    Gson gson = new Gson();

    // Act and Assert
    assertThrows(JsonIOException.class, () -> gson.toJson(JsonNull.INSTANCE, new PipedWriter()));
  }

  /**
   * Test {@link Gson#toJson(JsonElement, JsonWriter)} with {@code JsonElement}, {@code JsonWriter}.
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, JsonWriter)"})
  public void testToJsonWithJsonElementJsonWriter() throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    JsonElement jsonElement = mock(JsonElement.class);
    when(jsonElement.isJsonNull()).thenThrow(new IllegalArgumentException("Couldn't write "));
    JsonWriter writer = mock(JsonWriter.class);
    when(writer.getStrictness()).thenReturn(Strictness.LEGACY_STRICT);
    when(writer.getSerializeNulls()).thenReturn(true);
    when(writer.isHtmlSafe()).thenReturn(true);
    doNothing().when(writer).setHtmlSafe(anyBoolean());
    doNothing().when(writer).setSerializeNulls(anyBoolean());
    doNothing().when(writer).setStrictness(Mockito.<Strictness>any());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> gson.toJson(jsonElement, writer));
    verify(jsonElement).isJsonNull();
    verify(writer).getSerializeNulls();
    verify(writer, atLeast(1)).getStrictness();
    verify(writer).isHtmlSafe();
    verify(writer, atLeast(1)).setHtmlSafe(eq(true));
    verify(writer, atLeast(1)).setSerializeNulls(anyBoolean());
    verify(writer, atLeast(1)).setStrictness(Mockito.<Strictness>any());
  }

  /**
   * Test {@link Gson#toJson(JsonElement, JsonWriter)} with {@code JsonElement}, {@code JsonWriter}.
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, JsonWriter)"})
  public void testToJsonWithJsonElementJsonWriter2() throws JsonIOException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            3,
            3,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonElement jsonElement = mock(JsonElement.class);
    when(jsonElement.isJsonNull()).thenThrow(new IllegalArgumentException("Couldn't write "));
    JsonWriter writer = mock(JsonWriter.class);
    when(writer.getStrictness()).thenReturn(Strictness.LEGACY_STRICT);
    when(writer.getSerializeNulls()).thenReturn(true);
    when(writer.isHtmlSafe()).thenReturn(true);
    doNothing().when(writer).setHtmlSafe(anyBoolean());
    doNothing().when(writer).setSerializeNulls(anyBoolean());
    doNothing().when(writer).setStrictness(Mockito.<Strictness>any());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> gson.toJson(jsonElement, writer));
    verify(jsonElement).isJsonNull();
    verify(writer).getSerializeNulls();
    verify(writer).getStrictness();
    verify(writer).isHtmlSafe();
    verify(writer, atLeast(1)).setHtmlSafe(eq(true));
    verify(writer, atLeast(1)).setSerializeNulls(eq(true));
    verify(writer, atLeast(1)).setStrictness(Mockito.<Strictness>any());
  }

  /**
   * Test {@link Gson#toJson(JsonElement, JsonWriter)} with {@code JsonElement}, {@code JsonWriter}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, JsonWriter)"})
  public void testToJsonWithJsonElementJsonWriter_givenLenient() throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    JsonElement jsonElement = mock(JsonElement.class);
    when(jsonElement.isJsonNull()).thenThrow(new IllegalArgumentException("Couldn't write "));
    JsonWriter writer = mock(JsonWriter.class);
    when(writer.getSerializeNulls()).thenReturn(true);
    when(writer.isHtmlSafe()).thenReturn(true);
    when(writer.getStrictness()).thenReturn(Strictness.LENIENT);
    doNothing().when(writer).setHtmlSafe(anyBoolean());
    doNothing().when(writer).setSerializeNulls(anyBoolean());
    doNothing().when(writer).setStrictness(Mockito.<Strictness>any());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> gson.toJson(jsonElement, writer));
    verify(jsonElement).isJsonNull();
    verify(writer).getSerializeNulls();
    verify(writer, atLeast(1)).getStrictness();
    verify(writer).isHtmlSafe();
    verify(writer, atLeast(1)).setHtmlSafe(eq(true));
    verify(writer, atLeast(1)).setSerializeNulls(anyBoolean());
    verify(writer).setStrictness(eq(Strictness.LENIENT));
  }

  /**
   * Test {@link Gson#toJson(JsonElement, JsonWriter)} with {@code JsonElement}, {@code JsonWriter}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, JsonWriter)"})
  public void testToJsonWithJsonElementJsonWriter_thenThrowIllegalArgumentException()
      throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    JsonElement jsonElement = mock(JsonElement.class);
    when(jsonElement.isJsonNull()).thenThrow(new IllegalArgumentException("Couldn't write "));

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> gson.toJson(jsonElement, new JsonWriter(new StringWriter())));
    verify(jsonElement).isJsonNull();
  }

  /**
   * Test {@link Gson#toJson(JsonElement)} with {@code JsonElement}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>Then return {@code {"out \u003d\u003d null":"42"}}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(JsonElement)"})
  public void testToJsonWithJsonElement_given42_thenReturnOutU003dU003dNull42() {
    // Arrange
    Gson gson = new Gson();

    JsonObject jsonElement = new JsonObject();
    jsonElement.addProperty("out == null", "42");

    // Act and Assert
    assertEquals("{\"out \\u003d\\u003d null\":\"42\"}", gson.toJson(jsonElement));
  }

  /**
   * Test {@link Gson#toJson(JsonElement)} with {@code JsonElement}.
   *
   * <ul>
   *   <li>Given acknowledge.
   *   <li>Then return {@code ["\u0006",true]}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(JsonElement)"})
  public void testToJsonWithJsonElement_givenAcknowledge_thenReturnU0006True() {
    // Arrange
    Gson gson = new Gson();

    JsonArray jsonElement = new JsonArray(3);
    jsonElement.add('\u0006');
    jsonElement.add(true);

    // Act and Assert
    assertEquals("[\"\\u0006\",true]", gson.toJson(jsonElement));
  }

  /**
   * Test {@link Gson#toJson(JsonElement)} with {@code JsonElement}.
   *
   * <ul>
   *   <li>Given {@code :}.
   *   <li>Then return {@code {":":",","out \u003d\u003d null":"42"}}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(JsonElement)"})
  public void testToJsonWithJsonElement_givenColon_thenReturnOutU003dU003dNull42() {
    // Arrange
    Gson gson = new Gson();

    JsonObject jsonElement = new JsonObject();
    jsonElement.addProperty(":", ",");
    jsonElement.addProperty("out == null", "42");

    // Act and Assert
    assertEquals("{\":\":\",\",\"out \\u003d\\u003d null\":\"42\"}", gson.toJson(jsonElement));
  }

  /**
   * Test {@link Gson#toJson(JsonElement)} with {@code JsonElement}.
   *
   * <ul>
   *   <li>Given {@code false}.
   *   <li>Then return {@code [false,true]}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(JsonElement)"})
  public void testToJsonWithJsonElement_givenFalse_thenReturnFalseTrue() {
    // Arrange
    Gson gson = new Gson();

    JsonArray jsonElement = new JsonArray(3);
    jsonElement.add(false);
    jsonElement.add(true);

    // Act and Assert
    assertEquals("[false,true]", gson.toJson(jsonElement));
  }

  /**
   * Test {@link Gson#toJson(JsonElement)} with {@code JsonElement}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@link JsonNull#INSTANCE}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(JsonElement)"})
  public void testToJsonWithJsonElement_givenGson_whenInstance_thenReturnNull() {
    // Arrange, Act and Assert
    assertEquals("null", new Gson().toJson(JsonNull.INSTANCE));
  }

  /**
   * Test {@link Gson#toJson(JsonElement)} with {@code JsonElement}.
   *
   * <ul>
   *   <li>Given {@code true}.
   *   <li>Then return {@code [true]}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(JsonElement)"})
  public void testToJsonWithJsonElement_givenTrue_thenReturnTrue() {
    // Arrange
    Gson gson = new Gson();

    JsonArray jsonElement = new JsonArray(3);
    jsonElement.add(true);

    // Act and Assert
    assertEquals("[true]", gson.toJson(jsonElement));
  }

  /**
   * Test {@link Gson#toJson(JsonElement)} with {@code JsonElement}.
   *
   * <ul>
   *   <li>Given valueOf {@link Integer#SIZE}.
   *   <li>Then return {@code [32,true]}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(JsonElement)"})
  public void testToJsonWithJsonElement_givenValueOfSize_thenReturn32True() {
    // Arrange
    Gson gson = new Gson();

    JsonArray jsonElement = new JsonArray(3);
    jsonElement.add(Integer.valueOf(Integer.SIZE));
    jsonElement.add(true);

    // Act and Assert
    assertEquals("[32,true]", gson.toJson(jsonElement));
  }

  /**
   * Test {@link Gson#toJson(JsonElement)} with {@code JsonElement}.
   *
   * <ul>
   *   <li>Then return {@code {}}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(JsonElement)"})
  public void testToJsonWithJsonElement_thenReturnLeftCurlyBracketRightCurlyBracket() {
    // Arrange
    Gson gson = new Gson();

    JsonObject jsonElement = new JsonObject();
    jsonElement.add("out == null", JsonNull.INSTANCE);

    // Act and Assert
    assertEquals("{}", gson.toJson(jsonElement));
  }

  /**
   * Test {@link Gson#toJson(JsonElement)} with {@code JsonElement}.
   *
   * <ul>
   *   <li>Then return {@code []}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(JsonElement)"})
  public void testToJsonWithJsonElement_thenReturnLeftSquareBracketRightSquareBracket() {
    // Arrange
    Gson gson = new Gson();

    // Act and Assert
    assertEquals("[]", gson.toJson(new JsonArray(3)));
  }

  /**
   * Test {@link Gson#toJson(JsonElement)} with {@code JsonElement}.
   *
   * <ul>
   *   <li>Then return {@code [null,true]}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(JsonElement)"})
  public void testToJsonWithJsonElement_thenReturnNullTrue() {
    // Arrange
    Gson gson = new Gson();

    JsonArray jsonElement = new JsonArray(3);
    jsonElement.add(JsonNull.INSTANCE);
    jsonElement.add(true);

    // Act and Assert
    assertEquals("[null,true]", gson.toJson(jsonElement));
  }

  /**
   * Test {@link Gson#toJson(JsonElement)} with {@code JsonElement}.
   *
   * <ul>
   *   <li>Then return {@code "out \u003d\u003d null"}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(JsonElement)"})
  public void testToJsonWithJsonElement_thenReturnOutU003dU003dNull() {
    // Arrange
    Gson gson = new Gson();

    // Act and Assert
    assertEquals("\"out \\u003d\\u003d null\"", gson.toJson(new JsonPrimitive("out == null")));
  }

  /**
   * Test {@link Gson#toJson(JsonElement)} with {@code JsonElement}.
   *
   * <ul>
   *   <li>Then return {@code {",":"out \u003d\u003d null"}}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(JsonElement)"})
  public void testToJsonWithJsonElement_thenReturnOutU003dU003dNull2() {
    // Arrange
    Gson gson = new Gson();

    JsonObject jsonElement = new JsonObject();
    jsonElement.addProperty(",", "out == null");
    jsonElement.add("out == null", JsonNull.INSTANCE);

    // Act and Assert
    assertEquals("{\",\":\"out \\u003d\\u003d null\"}", gson.toJson(jsonElement));
  }

  /**
   * Test {@link Gson#toJson(JsonElement)} with {@code JsonElement}.
   *
   * <ul>
   *   <li>When {@link JsonObject} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(JsonElement)"})
  public void testToJsonWithJsonElement_whenJsonObject() {
    // Arrange
    Gson gson = new Gson();

    // Act and Assert
    assertEquals("{}", gson.toJson(new JsonObject()));
  }

  /**
   * Test {@link Gson#toJson(JsonElement)} with {@code JsonElement}.
   *
   * <ul>
   *   <li>When {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.
   *   <li>Then return {@link Boolean#TRUE} toString.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(JsonElement)"})
  public void testToJsonWithJsonElement_whenJsonPrimitiveWithBoolIsTrue_thenReturnTrueToString() {
    // Arrange
    Gson gson = new Gson();

    // Act
    String actualToJsonResult = gson.toJson(new JsonPrimitive(true));

    // Assert
    assertEquals(Boolean.TRUE.toString(), actualToJsonResult);
  }

  /**
   * Test {@link Gson#toJson(Object)} with {@code Object}.
   *
   * <p>Method under test: {@link Gson#toJson(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object)"})
  public void testToJsonWithObject() {
    // Arrange
    Gson gson = new Gson();
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();

    // Act and Assert
    assertEquals(
        "{\"constructorConstructor\":{\"instanceCreators\":{},\"useJdkUnsafe\":true,\"reflectionFilters\":[]}}",
        gson.toJson(
            new CollectionTypeAdapterFactory(
                new ConstructorConstructor(instanceCreators, true, new ArrayList<>()))));
  }

  /**
   * Test {@link Gson#toJson(Object)} with {@code Object}.
   *
   * <p>Method under test: {@link Gson#toJson(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object)"})
  public void testToJsonWithObject2() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    // Act and Assert
    assertEquals(
        ")]}'\n\"Src\"",
        new Gson(
                Excluder.DEFAULT,
                fieldNamingStrategy,
                instanceCreators,
                true,
                true,
                true,
                false,
                Gson.DEFAULT_FORMATTING_STYLE,
                Strictness.LENIENT,
                true,
                true,
                LongSerializationPolicy.DEFAULT,
                "2020-03-01",
                Integer.SIZE,
                Integer.SIZE,
                builderFactories,
                builderHierarchyFactories,
                factoriesToBeAdded,
                objectToNumberStrategy,
                numberToNumberStrategy,
                new ArrayList<>())
            .toJson("Src"));
  }

  /**
   * Test {@link Gson#toJson(Object)} with {@code Object}.
   *
   * <p>Method under test: {@link Gson#toJson(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object)"})
  public void testToJsonWithObject3() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    // Act and Assert
    assertEquals(
        ")]}'\n\"Src\"",
        new Gson(
                Excluder.DEFAULT,
                fieldNamingStrategy,
                instanceCreators,
                true,
                true,
                true,
                true,
                FormattingStyle.PRETTY,
                Strictness.LENIENT,
                true,
                true,
                LongSerializationPolicy.DEFAULT,
                "2020-03-01",
                Integer.SIZE,
                Integer.SIZE,
                builderFactories,
                builderHierarchyFactories,
                factoriesToBeAdded,
                objectToNumberStrategy,
                numberToNumberStrategy,
                new ArrayList<>())
            .toJson("Src"));
  }

  /**
   * Test {@link Gson#toJson(Object)} with {@code Object}.
   *
   * <p>Method under test: {@link Gson#toJson(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object)"})
  public void testToJsonWithObject4() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonArray(3));
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    // Act
    String actualToJsonResult =
        new Gson(
                Excluder.DEFAULT,
                fieldNamingStrategy,
                instanceCreators,
                true,
                true,
                true,
                true,
                Gson.DEFAULT_FORMATTING_STYLE,
                Strictness.LENIENT,
                true,
                true,
                LongSerializationPolicy.DEFAULT,
                "2020-03-01",
                Integer.SIZE,
                Integer.SIZE,
                builderFactories,
                builderHierarchyFactories,
                factoriesToBeAdded,
                objectToNumberStrategy,
                numberToNumberStrategy,
                new ArrayList<>())
            .toJson("Src");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertEquals(")]}'\n[]", actualToJsonResult);
  }

  /**
   * Test {@link Gson#toJson(Object)} with {@code Object}.
   *
   * <p>Method under test: {@link Gson#toJson(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object)"})
  public void testToJsonWithObject5() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonObject());
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    // Act
    String actualToJsonResult =
        new Gson(
                Excluder.DEFAULT,
                fieldNamingStrategy,
                instanceCreators,
                true,
                true,
                true,
                true,
                Gson.DEFAULT_FORMATTING_STYLE,
                Strictness.LENIENT,
                true,
                true,
                LongSerializationPolicy.DEFAULT,
                "2020-03-01",
                Integer.SIZE,
                Integer.SIZE,
                builderFactories,
                builderHierarchyFactories,
                factoriesToBeAdded,
                objectToNumberStrategy,
                numberToNumberStrategy,
                new ArrayList<>())
            .toJson("Src");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertEquals(")]}'\n{}", actualToJsonResult);
  }

  /**
   * Test {@link Gson#toJson(Object, Appendable)} with {@code Object}, {@code Appendable}.
   *
   * <p>Method under test: {@link Gson#toJson(Object, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(Object, Appendable)"})
  public void testToJsonWithObjectAppendable() throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    CharArrayWriter writer = new CharArrayWriter(1);

    // Act
    gson.toJson(Excluder.DEFAULT, writer);

    // Assert
    assertEquals(143, writer.size());
  }

  /**
   * Test {@link Gson#toJson(Object, Appendable)} with {@code Object}, {@code Appendable}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code null}.
   *   <li>Then {@link StringWriter#StringWriter()} toString is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(Object, Appendable)"})
  public void testToJsonWithObjectAppendable_givenGson_whenNull_thenStringWriterToStringIsNull()
      throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    StringWriter writer = new StringWriter();

    // Act
    gson.toJson((Object) null, writer);

    // Assert
    assertEquals("null", writer.toString());
  }

  /**
   * Test {@link Gson#toJson(Object, Appendable)} with {@code Object}, {@code Appendable}.
   *
   * <ul>
   *   <li>Then {@link CharArrayWriter#CharArrayWriter(int)} with one size is ninety-three.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(Object, Appendable)"})
  public void testToJsonWithObjectAppendable_thenCharArrayWriterWithOneSizeIsNinetyThree()
      throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    CollectionTypeAdapterFactory collectionTypeAdapterFactory =
        new CollectionTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>()));
    CharArrayWriter writer = new CharArrayWriter(1);

    // Act
    gson.toJson(collectionTypeAdapterFactory, writer);

    // Assert
    assertEquals(93, writer.size());
  }

  /**
   * Test {@link Gson#toJson(Object, Appendable)} with {@code Object}, {@code Appendable}.
   *
   * <ul>
   *   <li>Then {@link CharArrayWriter#CharArrayWriter(int)} with one size is ten.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(Object, Appendable)"})
  public void testToJsonWithObjectAppendable_thenCharArrayWriterWithOneSizeIsTen()
      throws JsonIOException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    CharArrayWriter writer = new CharArrayWriter(1);

    // Act
    gson.toJson("Src", writer);

    // Assert
    assertEquals(10, writer.size());
  }

  /**
   * Test {@link Gson#toJson(Object, Appendable)} with {@code Object}, {@code Appendable}.
   *
   * <ul>
   *   <li>Then {@link CharArrayWriter#CharArrayWriter(int)} with one size is twenty-three.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(Object, Appendable)"})
  public void testToJsonWithObjectAppendable_thenCharArrayWriterWithOneSizeIsTwentyThree()
      throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    CharArrayWriter writer = new CharArrayWriter(1);

    // Act
    gson.toJson("out == null", writer);

    // Assert
    assertEquals(23, writer.size());
  }

  /**
   * Test {@link Gson#toJson(Object, Appendable)} with {@code Object}, {@code Appendable}.
   *
   * <ul>
   *   <li>When empty string.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(Object, Appendable)"})
  public void testToJsonWithObjectAppendable_whenEmptyString() throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    CharArrayWriter writer = new CharArrayWriter(1);

    // Act
    gson.toJson("", writer);

    // Assert
    assertEquals(2, writer.size());
  }

  /**
   * Test {@link Gson#toJson(Object, Appendable)} with {@code Object}, {@code Appendable}.
   *
   * <ul>
   *   <li>When {@link FutureTypeAdapter} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(Object, Appendable)"})
  public void testToJsonWithObjectAppendable_whenFutureTypeAdapter() throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    CharArrayWriter writer = new CharArrayWriter(1);

    // Act
    gson.toJson(futureTypeAdapter, writer);

    // Assert
    assertEquals(2, writer.size());
  }

  /**
   * Test {@link Gson#toJson(Object, Appendable)} with {@code Object}, {@code Appendable}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   *   <li>Then {@link CharArrayWriter#CharArrayWriter(int)} with one size is two.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(Object, Appendable)"})
  public void testToJsonWithObjectAppendable_whenHashMap_thenCharArrayWriterWithOneSizeIsTwo()
      throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    HashMap<Object, Object> objectObjectMap = new HashMap<>();
    CharArrayWriter writer = new CharArrayWriter(1);

    // Act
    gson.toJson(objectObjectMap, writer);

    // Assert
    assertEquals(2, writer.size());
  }

  /**
   * Test {@link Gson#toJson(Object, Appendable)} with {@code Object}, {@code Appendable}.
   *
   * <ul>
   *   <li>When {@link PipedWriter#PipedWriter()}.
   *   <li>Then throw {@link JsonIOException}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(Object, Appendable)"})
  public void testToJsonWithObjectAppendable_whenPipedWriter_thenThrowJsonIOException()
      throws JsonIOException {
    // Arrange
    Gson gson = new Gson();

    // Act and Assert
    assertThrows(JsonIOException.class, () -> gson.toJson("Src", new PipedWriter()));
  }

  /**
   * Test {@link Gson#toJson(Object, Appendable)} with {@code Object}, {@code Appendable}.
   *
   * <ul>
   *   <li>When {@code Src}.
   *   <li>Then {@link CharArrayWriter#CharArrayWriter(int)} with one size is five.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(Object, Appendable)"})
  public void testToJsonWithObjectAppendable_whenSrc_thenCharArrayWriterWithOneSizeIsFive()
      throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    CharArrayWriter writer = new CharArrayWriter(1);

    // Act
    gson.toJson("Src", writer);

    // Assert
    assertEquals(5, writer.size());
  }

  /**
   * Test {@link Gson#toJson(Object, Type)} with {@code Object}, {@code Type}.
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object, Type)"})
  public void testToJsonWithObjectType() {
    // Arrange
    Gson gson = new Gson();
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    CollectionTypeAdapterFactory collectionTypeAdapterFactory =
        new CollectionTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>()));
    Class<Object> typeOfSrc = Object.class;

    // Act and Assert
    assertEquals(
        "{\"constructorConstructor\":{\"instanceCreators\":{},\"useJdkUnsafe\":true,\"reflectionFilters\":[]}}",
        gson.toJson(collectionTypeAdapterFactory, typeOfSrc));
  }

  /**
   * Test {@link Gson#toJson(Object, Type)} with {@code Object}, {@code Type}.
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object, Type)"})
  public void testToJsonWithObjectType2() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(JsonNull.INSTANCE);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson2 =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            FormattingStyle.PRETTY,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act
    String actualToJsonResult = gson2.toJson("Src", typeOfSrc);

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertEquals(")]}'\nnull", actualToJsonResult);
  }

  /**
   * Test {@link Gson#toJson(Object, Type)} with {@code Object}, {@code Type}.
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object, Type)"})
  public void testToJsonWithObjectType3() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonArray(3));
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson2 =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act
    String actualToJsonResult = gson2.toJson("Src", typeOfSrc);

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertEquals(")]}'\n[]", actualToJsonResult);
  }

  /**
   * Test {@link Gson#toJson(Object, Type)} with {@code Object}, {@code Type}.
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object, Type)"})
  public void testToJsonWithObjectType4() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonObject());
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson2 =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act
    String actualToJsonResult = gson2.toJson("Src", typeOfSrc);

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertEquals(")]}'\n{}", actualToJsonResult);
  }

  /**
   * Test {@link Gson#toJson(Object, Type, Appendable)} with {@code Object}, {@code Type}, {@code
   * Appendable}.
   *
   * <ul>
   *   <li>Then {@link CharArrayWriter#CharArrayWriter(int)} with one size is five.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(Object, Type, Appendable)"})
  public void testToJsonWithObjectTypeAppendable_thenCharArrayWriterWithOneSizeIsFive()
      throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> typeOfSrc = Object.class;
    CharArrayWriter writer = new CharArrayWriter(1);

    // Act
    gson.toJson("Src", typeOfSrc, writer);

    // Assert
    assertEquals(5, writer.size());
  }

  /**
   * Test {@link Gson#toJson(Object, Type, Appendable)} with {@code Object}, {@code Type}, {@code
   * Appendable}.
   *
   * <ul>
   *   <li>Then {@link CharArrayWriter#CharArrayWriter(int)} with one size is ten.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(Object, Type, Appendable)"})
  public void testToJsonWithObjectTypeAppendable_thenCharArrayWriterWithOneSizeIsTen()
      throws JsonIOException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> typeOfSrc = Object.class;
    CharArrayWriter writer = new CharArrayWriter(1);

    // Act
    gson.toJson("Src", typeOfSrc, writer);

    // Assert
    assertEquals(10, writer.size());
  }

  /**
   * Test {@link Gson#toJson(Object, Type, Appendable)} with {@code Object}, {@code Type}, {@code
   * Appendable}.
   *
   * <ul>
   *   <li>When {@link PipedWriter#PipedWriter()}.
   *   <li>Then throw {@link JsonIOException}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(Object, Type, Appendable)"})
  public void testToJsonWithObjectTypeAppendable_whenPipedWriter_thenThrowJsonIOException()
      throws JsonIOException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    TypeVarBoundedType typeOfSrc = new TypeVarBoundedType(null);

    // Act and Assert
    assertThrows(JsonIOException.class, () -> gson.toJson("Src", typeOfSrc, new PipedWriter()));
  }

  /**
   * Test {@link Gson#toJson(Object, Type, JsonWriter)} with {@code Object}, {@code Type}, {@code
   * JsonWriter}.
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(Object, Type, JsonWriter)"})
  public void testToJsonWithObjectTypeJsonWriter() throws JsonIOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonArray(3));
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson2 =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act
    gson2.toJson("Src", typeOfSrc, new JsonWriter(new StringWriter()));

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
  }

  /**
   * Test {@link Gson#toJson(Object, Type, JsonWriter)} with {@code Object}, {@code Type}, {@code
   * JsonWriter}.
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(Object, Type, JsonWriter)"})
  public void testToJsonWithObjectTypeJsonWriter2() throws JsonIOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonPrimitive("type must not be null"));
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson2 =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act
    gson2.toJson("Src", typeOfSrc, new JsonWriter(new StringWriter()));

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
  }

  /**
   * Test {@link Gson#toJson(Object, Type, JsonWriter)} with {@code Object}, {@code Type}, {@code
   * JsonWriter}.
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(Object, Type, JsonWriter)"})
  public void testToJsonWithObjectTypeJsonWriter3() throws JsonIOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonPrimitive(true));
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson2 =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act
    gson2.toJson("Src", typeOfSrc, new JsonWriter(new StringWriter()));

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
  }

  /**
   * Test {@link Gson#toJson(Object, Type, JsonWriter)} with {@code Object}, {@code Type}, {@code
   * JsonWriter}.
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(Object, Type, JsonWriter)"})
  public void testToJsonWithObjectTypeJsonWriter4() throws JsonIOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonPrimitive('\u0001'));
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson2 =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act
    gson2.toJson("Src", typeOfSrc, new JsonWriter(new StringWriter()));

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
  }

  /**
   * Test {@link Gson#toJson(Object, Type, JsonWriter)} with {@code Object}, {@code Type}, {@code
   * JsonWriter}.
   *
   * <ul>
   *   <li>Given {@link JsonSerializer} {@link JsonSerializer#serialize(Object, Type,
   *       JsonSerializationContext)} return {@link JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(Object, Type, JsonWriter)"})
  public void testToJsonWithObjectTypeJsonWriter_givenJsonSerializerSerializeReturnInstance()
      throws JsonIOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(JsonNull.INSTANCE);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson2 =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act
    gson2.toJson("Src", typeOfSrc, new JsonWriter(new StringWriter()));

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
  }

  /**
   * Test {@link Gson#toJson(Object, Type, JsonWriter)} with {@code Object}, {@code Type}, {@code
   * JsonWriter}.
   *
   * <ul>
   *   <li>Given {@link JsonSerializer} {@link JsonSerializer#serialize(Object, Type,
   *       JsonSerializationContext)} return {@link JsonObject} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(Object, Type, JsonWriter)"})
  public void testToJsonWithObjectTypeJsonWriter_givenJsonSerializerSerializeReturnJsonObject()
      throws JsonIOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonObject());
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson2 =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act
    gson2.toJson("Src", typeOfSrc, new JsonWriter(new StringWriter()));

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
  }

  /**
   * Test {@link Gson#toJson(Object, Type, JsonWriter)} with {@code Object}, {@code Type}, {@code
   * JsonWriter}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then calls {@link TypeAdapterFactory#create(Gson, TypeToken)}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(Object, Type, JsonWriter)"})
  public void testToJsonWithObjectTypeJsonWriter_whenNull_thenCallsCreate() throws JsonIOException {
    // Arrange
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson2 =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act
    gson2.toJson(null, typeOfSrc, new JsonWriter(new StringWriter()));

    // Assert
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
  }

  /**
   * Test {@link Gson#toJson(Object, Type)} with {@code Object}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@link Excluder#DEFAULT}.
   *   <li>Then return a string.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object, Type)"})
  public void testToJsonWithObjectType_givenGson_whenDefault_thenReturnAString() {
    // Arrange
    Gson gson = new Gson();
    Class<Object> typeOfSrc = Object.class;

    // Act and Assert
    assertEquals(
        "{\"version\":-1.0,\"modifiers\":136,\"serializeInnerClasses\":true,\"requireExpose\":false,\"serializationStrategies"
            + "\":[],\"deserializationStrategies\":[]}",
        gson.toJson(Excluder.DEFAULT, typeOfSrc));
  }

  /**
   * Test {@link Gson#toJson(Object, Type)} with {@code Object}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code Object}.
   *   <li>Then return {@code "Src"}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object, Type)"})
  public void testToJsonWithObjectType_givenGson_whenJavaLangObject_thenReturnSrc() {
    // Arrange
    Gson gson = new Gson();
    Class<Object> typeOfSrc = Object.class;

    // Act and Assert
    assertEquals("\"Src\"", gson.toJson("Src", typeOfSrc));
  }

  /**
   * Test {@link Gson#toJson(Object, Type)} with {@code Object}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object, Type)"})
  public void testToJsonWithObjectType_givenGson_whenNull_thenReturnNull() {
    // Arrange
    Gson gson = new Gson();
    Class<Object> typeOfSrc = Object.class;

    // Act and Assert
    assertEquals("null", gson.toJson(null, typeOfSrc));
  }

  /**
   * Test {@link Gson#toJson(Object, Type)} with {@code Object}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object, Type)"})
  public void testToJsonWithObjectType_givenGson_whenNull_thenReturnNull2() {
    // Arrange
    Gson gson = new Gson();
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act and Assert
    assertEquals("null", gson.toJson(null, typeOfSrc));
  }

  /**
   * Test {@link Gson#toJson(Object, Type)} with {@code Object}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code out == null}.
   *   <li>Then return {@code "out \u003d\u003d null"}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object, Type)"})
  public void testToJsonWithObjectType_givenGson_whenOutNull_thenReturnOutU003dU003dNull() {
    // Arrange
    Gson gson = new Gson();
    Class<Object> typeOfSrc = Object.class;

    // Act and Assert
    assertEquals("\"out \\u003d\\u003d null\"", gson.toJson("out == null", typeOfSrc));
  }

  /**
   * Test {@link Gson#toJson(Object, Type)} with {@code Object}, {@code Type}.
   *
   * <ul>
   *   <li>Then return {@code )]}' null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object, Type)"})
  public void testToJsonWithObjectType_thenReturnNull() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(JsonNull.INSTANCE);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson2 =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act
    String actualToJsonResult = gson2.toJson("Src", typeOfSrc);

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertEquals(")]}'\nnull", actualToJsonResult);
  }

  /**
   * Test {@link Gson#toJson(Object, Type)} with {@code Object}, {@code Type}.
   *
   * <ul>
   *   <li>Then return {@code )]}' "Src"}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object, Type)"})
  public void testToJsonWithObjectType_thenReturnSrc() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> typeOfSrc = Object.class;

    // Act and Assert
    assertEquals(")]}'\n\"Src\"", gson.toJson("Src", typeOfSrc));
  }

  /**
   * Test {@link Gson#toJson(Object, Type)} with {@code Object}, {@code Type}.
   *
   * <ul>
   *   <li>Then return {@code )]}' true}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object, Type)"})
  public void testToJsonWithObjectType_thenReturnTrue() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonPrimitive(true));
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson2 =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act
    String actualToJsonResult = gson2.toJson("Src", typeOfSrc);

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertEquals(")]}'\ntrue", actualToJsonResult);
  }

  /**
   * Test {@link Gson#toJson(Object, Type)} with {@code Object}, {@code Type}.
   *
   * <ul>
   *   <li>Then return {@code )]}' ")]}\u0027\n"}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object, Type)"})
  public void testToJsonWithObjectType_thenReturnU0027N() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonPrimitive(")]}'\n"));
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson2 =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act
    String actualToJsonResult = gson2.toJson("Src", typeOfSrc);

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertEquals(")]}'\n\")]}\\u0027\\n\"", actualToJsonResult);
  }

  /**
   * Test {@link Gson#toJson(Object, Type)} with {@code Object}, {@code Type}.
   *
   * <ul>
   *   <li>When {@code TypeAdapterFactory}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object, Type)"})
  public void testToJsonWithObjectType_whenComGoogleGsonTypeAdapterFactory() {
    // Arrange
    Gson gson = new Gson();
    Class<TypeAdapterFactory> typeOfSrc = TypeAdapterFactory.class;

    // Act and Assert
    assertEquals("{}", gson.toJson("Src", typeOfSrc));
  }

  /**
   * Test {@link Gson#toJson(Object, Type)} with {@code Object}, {@code Type}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return {@code ""}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object, Type)"})
  public void testToJsonWithObjectType_whenEmptyString_thenReturnQuotationMarkQuotationMark() {
    // Arrange
    Gson gson = new Gson();
    Class<Object> typeOfSrc = Object.class;

    // Act and Assert
    assertEquals("\"\"", gson.toJson("", typeOfSrc));
  }

  /**
   * Test {@link Gson#toJson(Object, Type)} with {@code Object}, {@code Type}.
   *
   * <ul>
   *   <li>When {@link FutureTypeAdapter} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object, Type)"})
  public void testToJsonWithObjectType_whenFutureTypeAdapter() {
    // Arrange
    Gson gson = new Gson();
    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    Class<Object> typeOfSrc = Object.class;

    // Act and Assert
    assertEquals("{}", gson.toJson(futureTypeAdapter, typeOfSrc));
  }

  /**
   * Test {@link Gson#toJson(Object, Type)} with {@code Object}, {@code Type}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   *   <li>Then return {@code {}}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object, Type)"})
  public void testToJsonWithObjectType_whenHashMap_thenReturnLeftCurlyBracketRightCurlyBracket() {
    // Arrange
    Gson gson = new Gson();
    HashMap<Object, Object> objectObjectMap = new HashMap<>();
    Class<Object> typeOfSrc = Object.class;

    // Act and Assert
    assertEquals("{}", gson.toJson(objectObjectMap, typeOfSrc));
  }

  /**
   * Test {@link Gson#toJson(Object, Type)} with {@code Object}, {@code Type}.
   *
   * <ul>
   *   <li>When {@link LazilyParsedNumber#LazilyParsedNumber(String)} with value is {@code 42}.
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object, Type)"})
  public void testToJsonWithObjectType_whenLazilyParsedNumberWithValueIs42_thenReturn42() {
    // Arrange
    Gson gson = new Gson();
    LazilyParsedNumber lazilyParsedNumber = new LazilyParsedNumber("42");
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act and Assert
    assertEquals("42", gson.toJson(lazilyParsedNumber, typeOfSrc));
  }

  /**
   * Test {@link Gson#toJson(Object, Type)} with {@code Object}, {@code Type}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code )]}' null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object, Type)"})
  public void testToJsonWithObjectType_whenNull_thenReturnNull() {
    // Arrange
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson2 =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> typeOfSrc = LazilyParsedNumber.class;

    // Act
    String actualToJsonResult = gson2.toJson(null, typeOfSrc);

    // Assert
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertEquals(")]}'\nnull", actualToJsonResult);
  }

  /**
   * Test {@link Gson#toJson(Object)} with {@code Object}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link TypeAdapterFactory}.
   *   <li>When {@code null}.
   *   <li>Then return {@code )]}' null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object)"})
  public void testToJsonWithObject_givenArrayListAddTypeAdapterFactory_whenNull_thenReturnNull() {
    // Arrange
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(mock(TypeAdapterFactory.class));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    // Act and Assert
    assertEquals(
        ")]}'\nnull",
        new Gson(
                Excluder.DEFAULT,
                fieldNamingStrategy,
                instanceCreators,
                true,
                true,
                true,
                true,
                Gson.DEFAULT_FORMATTING_STYLE,
                Strictness.LENIENT,
                true,
                true,
                LongSerializationPolicy.DEFAULT,
                "2020-03-01",
                Integer.SIZE,
                Integer.SIZE,
                builderFactories,
                builderHierarchyFactories,
                factoriesToBeAdded,
                objectToNumberStrategy,
                numberToNumberStrategy,
                new ArrayList<>())
            .toJson((Object) null));
  }

  /**
   * Test {@link Gson#toJson(Object)} with {@code Object}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@link Excluder#DEFAULT}.
   *   <li>Then return a string.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object)"})
  public void testToJsonWithObject_givenGson_whenDefault_thenReturnAString() {
    // Arrange, Act and Assert
    assertEquals(
        "{\"version\":-1.0,\"modifiers\":136,\"serializeInnerClasses\":true,\"requireExpose\":false,\"serializationStrategies"
            + "\":[],\"deserializationStrategies\":[]}",
        new Gson().toJson(Excluder.DEFAULT));
  }

  /**
   * Test {@link Gson#toJson(Object)} with {@code Object}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object)"})
  public void testToJsonWithObject_givenGson_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertEquals("null", new Gson().toJson((Object) null));
  }

  /**
   * Test {@link Gson#toJson(Object)} with {@code Object}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code out == null}.
   *   <li>Then return {@code "out \u003d\u003d null"}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object)"})
  public void testToJsonWithObject_givenGson_whenOutNull_thenReturnOutU003dU003dNull() {
    // Arrange, Act and Assert
    assertEquals("\"out \\u003d\\u003d null\"", new Gson().toJson("out == null"));
  }

  /**
   * Test {@link Gson#toJson(Object)} with {@code Object}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code Src}.
   *   <li>Then return {@code "Src"}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object)"})
  public void testToJsonWithObject_givenGson_whenSrc_thenReturnSrc() {
    // Arrange, Act and Assert
    assertEquals("\"Src\"", new Gson().toJson("Src"));
  }

  /**
   * Test {@link Gson#toJson(Object)} with {@code Object}.
   *
   * <ul>
   *   <li>Given {@link JsonSerializer} {@link JsonSerializer#serialize(Object, Type,
   *       JsonSerializationContext)} return {@link JsonNull#INSTANCE}.
   *   <li>Then return {@code )]}' null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object)"})
  public void testToJsonWithObject_givenJsonSerializerSerializeReturnInstance_thenReturnNull() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(JsonNull.INSTANCE);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    // Act
    String actualToJsonResult =
        new Gson(
                Excluder.DEFAULT,
                fieldNamingStrategy,
                instanceCreators,
                true,
                true,
                true,
                true,
                Gson.DEFAULT_FORMATTING_STYLE,
                Strictness.LENIENT,
                true,
                true,
                LongSerializationPolicy.DEFAULT,
                "2020-03-01",
                Integer.SIZE,
                Integer.SIZE,
                builderFactories,
                builderHierarchyFactories,
                factoriesToBeAdded,
                objectToNumberStrategy,
                numberToNumberStrategy,
                new ArrayList<>())
            .toJson("Src");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertEquals(")]}'\nnull", actualToJsonResult);
  }

  /**
   * Test {@link Gson#toJson(Object)} with {@code Object}.
   *
   * <ul>
   *   <li>Given {@link TypeAdapterFactory} {@link TypeAdapterFactory#create(Gson, TypeToken)}
   *       return {@code null}.
   *   <li>Then return {@code )]}' "Src"}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object)"})
  public void testToJsonWithObject_givenTypeAdapterFactoryCreateReturnNull_thenReturnSrc() {
    // Arrange
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(null);

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    // Act
    String actualToJsonResult =
        new Gson(
                Excluder.DEFAULT,
                fieldNamingStrategy,
                instanceCreators,
                true,
                true,
                true,
                true,
                Gson.DEFAULT_FORMATTING_STYLE,
                Strictness.LENIENT,
                true,
                true,
                LongSerializationPolicy.DEFAULT,
                "2020-03-01",
                Integer.SIZE,
                Integer.SIZE,
                builderFactories,
                builderHierarchyFactories,
                factoriesToBeAdded,
                objectToNumberStrategy,
                numberToNumberStrategy,
                new ArrayList<>())
            .toJson("Src");

    // Assert
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertEquals(")]}'\n\"Src\"", actualToJsonResult);
  }

  /**
   * Test {@link Gson#toJson(Object)} with {@code Object}.
   *
   * <ul>
   *   <li>Then return {@code )]}' "Src"}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object)"})
  public void testToJsonWithObject_thenReturnSrc() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    // Act and Assert
    assertEquals(
        ")]}'\n\"Src\"",
        new Gson(
                Excluder.DEFAULT,
                fieldNamingStrategy,
                instanceCreators,
                true,
                true,
                true,
                true,
                Gson.DEFAULT_FORMATTING_STYLE,
                Strictness.LENIENT,
                true,
                true,
                LongSerializationPolicy.DEFAULT,
                "2020-03-01",
                Integer.SIZE,
                Integer.SIZE,
                builderFactories,
                builderHierarchyFactories,
                factoriesToBeAdded,
                objectToNumberStrategy,
                numberToNumberStrategy,
                new ArrayList<>())
            .toJson("Src"));
  }

  /**
   * Test {@link Gson#toJson(Object)} with {@code Object}.
   *
   * <ul>
   *   <li>Then return {@code )]}' true}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object)"})
  public void testToJsonWithObject_thenReturnTrue() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonPrimitive(true));
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    // Act
    String actualToJsonResult =
        new Gson(
                Excluder.DEFAULT,
                fieldNamingStrategy,
                instanceCreators,
                true,
                true,
                true,
                true,
                Gson.DEFAULT_FORMATTING_STYLE,
                Strictness.LENIENT,
                true,
                true,
                LongSerializationPolicy.DEFAULT,
                "2020-03-01",
                Integer.SIZE,
                Integer.SIZE,
                builderFactories,
                builderHierarchyFactories,
                factoriesToBeAdded,
                objectToNumberStrategy,
                numberToNumberStrategy,
                new ArrayList<>())
            .toJson("Src");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertEquals(")]}'\ntrue", actualToJsonResult);
  }

  /**
   * Test {@link Gson#toJson(Object)} with {@code Object}.
   *
   * <ul>
   *   <li>Then return {@code )]}' ")]}\u0027\n"}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object)"})
  public void testToJsonWithObject_thenReturnU0027N() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonPrimitive(")]}'\n"));
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(
            new TreeTypeAdapter<>(
                serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class)));

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    // Act
    String actualToJsonResult =
        new Gson(
                Excluder.DEFAULT,
                fieldNamingStrategy,
                instanceCreators,
                true,
                true,
                true,
                true,
                Gson.DEFAULT_FORMATTING_STYLE,
                Strictness.LENIENT,
                true,
                true,
                LongSerializationPolicy.DEFAULT,
                "2020-03-01",
                Integer.SIZE,
                Integer.SIZE,
                builderFactories,
                builderHierarchyFactories,
                factoriesToBeAdded,
                objectToNumberStrategy,
                numberToNumberStrategy,
                new ArrayList<>())
            .toJson("Src");

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    assertEquals(")]}'\n\")]}\\u0027\\n\"", actualToJsonResult);
  }

  /**
   * Test {@link Gson#toJson(Object)} with {@code Object}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return {@code ""}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object)"})
  public void testToJsonWithObject_whenEmptyString_thenReturnQuotationMarkQuotationMark() {
    // Arrange, Act and Assert
    assertEquals("\"\"", new Gson().toJson(""));
  }

  /**
   * Test {@link Gson#toJson(Object)} with {@code Object}.
   *
   * <ul>
   *   <li>When {@link FutureTypeAdapter} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object)"})
  public void testToJsonWithObject_whenFutureTypeAdapter() {
    // Arrange
    Gson gson = new Gson();

    // Act and Assert
    assertEquals("{}", gson.toJson(new FutureTypeAdapter<>()));
  }

  /**
   * Test {@link Gson#toJson(Object)} with {@code Object}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   *   <li>Then return {@code {}}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object)"})
  public void testToJsonWithObject_whenHashMap_thenReturnLeftCurlyBracketRightCurlyBracket() {
    // Arrange
    Gson gson = new Gson();

    // Act and Assert
    assertEquals("{}", gson.toJson(new HashMap<>()));
  }

  /**
   * Test {@link Gson#toJson(Object)} with {@code Object}.
   *
   * <ul>
   *   <li>When {@link JsonNull#INSTANCE}.
   *   <li>Then return {@code )]}' null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object)"})
  public void testToJsonWithObject_whenInstance_thenReturnNull() {
    // Arrange
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(mock(TypeAdapterFactory.class));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    // Act and Assert
    assertEquals(
        ")]}'\nnull",
        new Gson(
                Excluder.DEFAULT,
                fieldNamingStrategy,
                instanceCreators,
                true,
                true,
                true,
                true,
                Gson.DEFAULT_FORMATTING_STYLE,
                Strictness.LENIENT,
                true,
                true,
                LongSerializationPolicy.DEFAULT,
                "2020-03-01",
                Integer.SIZE,
                Integer.SIZE,
                builderFactories,
                builderHierarchyFactories,
                factoriesToBeAdded,
                objectToNumberStrategy,
                numberToNumberStrategy,
                new ArrayList<>())
            .toJson((Object) JsonNull.INSTANCE));
  }

  /**
   * Test {@link Gson#toJson(Object)} with {@code Object}.
   *
   * <ul>
   *   <li>When {@link JsonArray#JsonArray(int)} with capacity is three.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object)"})
  public void testToJsonWithObject_whenJsonArrayWithCapacityIsThree() {
    // Arrange
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(mock(TypeAdapterFactory.class));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());

    // Act and Assert
    assertEquals(")]}'\n[]", gson.toJson((Object) new JsonArray(3)));
  }

  /**
   * Test {@link Gson#newJsonWriter(Writer)}.
   *
   * <p>Method under test: {@link Gson#newJsonWriter(Writer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter Gson.newJsonWriter(Writer)"})
  public void testNewJsonWriter() throws IOException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringWriter writer = new StringWriter();

    // Act
    JsonWriter actualNewJsonWriterResult = gson.newJsonWriter(writer);

    // Assert
    assertEquals(")]}'\n", writer.toString());
    assertEquals(Strictness.LENIENT, actualNewJsonWriterResult.getStrictness());
    assertTrue(actualNewJsonWriterResult.getSerializeNulls());
    assertTrue(actualNewJsonWriterResult.isLenient());
    FormattingStyle expectedFormattingStyle = gson.formattingStyle;
    assertSame(expectedFormattingStyle, actualNewJsonWriterResult.getFormattingStyle());
  }

  /**
   * Test {@link Gson#newJsonWriter(Writer)}.
   *
   * <p>Method under test: {@link Gson#newJsonWriter(Writer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter Gson.newJsonWriter(Writer)"})
  public void testNewJsonWriter2() throws IOException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            FormattingStyle.PRETTY,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringWriter writer = new StringWriter();

    // Act
    JsonWriter actualNewJsonWriterResult = gson.newJsonWriter(writer);

    // Assert
    assertEquals(")]}'\n", writer.toString());
    FormattingStyle expectedFormattingStyle = gson.formattingStyle;
    assertSame(expectedFormattingStyle, actualNewJsonWriterResult.getFormattingStyle());
  }

  /**
   * Test {@link Gson#newJsonWriter(Writer)}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@link StringWriter#StringWriter()}.
   *   <li>Then return Strictness is {@code LEGACY_STRICT}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#newJsonWriter(Writer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter Gson.newJsonWriter(Writer)"})
  public void testNewJsonWriter_givenGson_whenStringWriter_thenReturnStrictnessIsLegacyStrict()
      throws IOException {
    // Arrange
    Gson gson = new Gson();

    // Act
    JsonWriter actualNewJsonWriterResult = gson.newJsonWriter(new StringWriter());

    // Assert
    assertEquals(Strictness.LEGACY_STRICT, actualNewJsonWriterResult.getStrictness());
    assertFalse(actualNewJsonWriterResult.getSerializeNulls());
    assertFalse(actualNewJsonWriterResult.isLenient());
    FormattingStyle expectedFormattingStyle = gson.formattingStyle;
    assertSame(expectedFormattingStyle, actualNewJsonWriterResult.getFormattingStyle());
  }

  /**
   * Test {@link Gson#newJsonWriter(Writer)}.
   *
   * <ul>
   *   <li>Then {@link StringWriter#StringWriter()} toString is empty string.
   * </ul>
   *
   * <p>Method under test: {@link Gson#newJsonWriter(Writer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter Gson.newJsonWriter(Writer)"})
  public void testNewJsonWriter_thenStringWriterToStringIsEmptyString() throws IOException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            false,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringWriter writer = new StringWriter();

    // Act
    JsonWriter actualNewJsonWriterResult = gson.newJsonWriter(writer);

    // Assert
    assertEquals("", writer.toString());
    assertEquals(Strictness.LENIENT, actualNewJsonWriterResult.getStrictness());
    assertTrue(actualNewJsonWriterResult.getSerializeNulls());
    assertTrue(actualNewJsonWriterResult.isLenient());
    FormattingStyle expectedFormattingStyle = gson.formattingStyle;
    assertSame(expectedFormattingStyle, actualNewJsonWriterResult.getFormattingStyle());
  }

  /**
   * Test {@link Gson#newJsonReader(Reader)}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>Then return Strictness is {@code LEGACY_STRICT}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#newJsonReader(Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonReader Gson.newJsonReader(Reader)"})
  public void testNewJsonReader_givenGson_thenReturnStrictnessIsLegacyStrict() {
    // Arrange
    Gson gson = new Gson();

    // Act
    JsonReader actualNewJsonReaderResult = gson.newJsonReader(new StringReader("foo"));

    // Assert
    assertEquals("$", actualNewJsonReaderResult.getPath());
    assertEquals("$", actualNewJsonReaderResult.getPreviousPath());
    assertEquals(255, actualNewJsonReaderResult.getNestingLimit());
    assertEquals(Strictness.LEGACY_STRICT, actualNewJsonReaderResult.getStrictness());
    assertFalse(actualNewJsonReaderResult.isLenient());
  }

  /**
   * Test {@link Gson#newJsonReader(Reader)}.
   *
   * <ul>
   *   <li>Then return Strictness is {@code LENIENT}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#newJsonReader(Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonReader Gson.newJsonReader(Reader)"})
  public void testNewJsonReader_thenReturnStrictnessIsLenient() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());

    // Act
    JsonReader actualNewJsonReaderResult = gson.newJsonReader(new StringReader("foo"));

    // Assert
    assertEquals("$", actualNewJsonReaderResult.getPath());
    assertEquals("$", actualNewJsonReaderResult.getPreviousPath());
    assertEquals(255, actualNewJsonReaderResult.getNestingLimit());
    assertEquals(Strictness.LENIENT, actualNewJsonReaderResult.getStrictness());
    assertTrue(actualNewJsonReaderResult.isLenient());
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Class)} with {@code JsonElement}, {@code Class}.
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Class)"})
  public void testFromJsonWithJsonElementClass() throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertNull(gson.fromJson(JsonNull.INSTANCE, classOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Class)} with {@code JsonElement}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>Then return size is four.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Class)"})
  public void testFromJsonWithJsonElementClass_given42_thenReturnSizeIsFour()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("42", JsonNull.INSTANCE);
    json.add("Property", JsonNull.INSTANCE);
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> classOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(4, ((Map<String, Object>) actualFromJsonResult).size());
    assertNull(((Map<String, Object>) actualFromJsonResult).get("42"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("Property"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("type must not be null"));
    assertTrue(((Map<String, Object>) actualFromJsonResult).containsKey("in == null"));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Class)} with {@code JsonElement}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@link JsonNull#INSTANCE}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Class)"})
  public void testFromJsonWithJsonElementClass_givenGson_whenInstance_thenReturnNull()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertNull(gson.fromJson(JsonNull.INSTANCE, classOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Class)} with {@code JsonElement}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code Long}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Class)"})
  public void testFromJsonWithJsonElementClass_givenGson_whenJavaLangLong_thenReturnNull()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Long> classOfT = Long.class;

    // Act and Assert
    assertNull(gson.fromJson(JsonNull.INSTANCE, classOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Class)} with {@code JsonElement}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code BigDecimal}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Class)"})
  public void testFromJsonWithJsonElementClass_givenGson_whenJavaMathBigDecimal_thenReturnNull()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<BigDecimal> classOfT = BigDecimal.class;

    // Act and Assert
    assertNull(gson.fromJson(JsonNull.INSTANCE, classOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Class)} with {@code JsonElement}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@link JsonObject} (default constructor).
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Class)"})
  public void testFromJsonWithJsonElementClass_givenGson_whenJsonObject_thenReturnEmpty()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonObject json = new JsonObject();
    Class<Object> classOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertTrue(((Map<Object, Object>) actualFromJsonResult).isEmpty());
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Class)} with {@code JsonElement}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Class)"})
  public void testFromJsonWithJsonElementClass_givenGson_whenNull_thenReturnNull()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertNull(gson.fromJson((JsonElement) null, classOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Class)} with {@code JsonElement}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@link JsonNull#INSTANCE}.
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Class)"})
  public void testFromJsonWithJsonElementClass_givenInstance_thenReturnSizeIsOne()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> classOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(1, ((Map<String, Object>) actualFromJsonResult).size());
    assertNull(((Map<String, Object>) actualFromJsonResult).get("in == null"));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Class)} with {@code JsonElement}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Class)"})
  public void testFromJsonWithJsonElementClass_givenJsonArrayWithCapacityIsThree()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("in == null", new JsonArray(3));
    Class<Object> classOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(1, ((Map<String, ArrayList>) actualFromJsonResult).size());
    assertTrue(((Map<String, ArrayList>) actualFromJsonResult).get("in == null").isEmpty());
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Class)} with {@code JsonElement}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Class)"})
  public void testFromJsonWithJsonElementClass_givenJsonObject() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("in == null", new JsonObject());
    Class<Object> classOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(1, ((Map<String, LinkedTreeMap>) actualFromJsonResult).size());
    assertTrue(((Map<String, LinkedTreeMap>) actualFromJsonResult).get("in == null").isEmpty());
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Class)} with {@code JsonElement}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@code Property}.
   *   <li>Then return size is three.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Class)"})
  public void testFromJsonWithJsonElementClass_givenProperty_thenReturnSizeIsThree()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("Property", JsonNull.INSTANCE);
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> classOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(3, ((Map<String, Object>) actualFromJsonResult).size());
    assertNull(((Map<String, Object>) actualFromJsonResult).get("Property"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("type must not be null"));
    assertTrue(((Map<String, Object>) actualFromJsonResult).containsKey("in == null"));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Class)} with {@code JsonElement}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@code Property}.
   *   <li>Then return size is three.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Class)"})
  public void testFromJsonWithJsonElementClass_givenProperty_thenReturnSizeIsThree2()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("in == null", JsonNull.INSTANCE);
    json.add("Property", JsonNull.INSTANCE);
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> classOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(3, ((Map<String, Object>) actualFromJsonResult).size());
    assertNull(((Map<String, Object>) actualFromJsonResult).get("Property"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("type must not be null"));
    assertTrue(((Map<String, Object>) actualFromJsonResult).containsKey("in == null"));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Class)} with {@code JsonElement}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@code Property}.
   *   <li>Then return size is three.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Class)"})
  public void testFromJsonWithJsonElementClass_givenProperty_thenReturnSizeIsThree3()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("in == null", JsonNull.INSTANCE);
    json.add("Property", JsonNull.INSTANCE);
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> classOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(3, ((Map<String, Object>) actualFromJsonResult).size());
    assertNull(((Map<String, Object>) actualFromJsonResult).get("Property"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("type must not be null"));
    assertTrue(((Map<String, Object>) actualFromJsonResult).containsKey("in == null"));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Class)} with {@code JsonElement}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@code Property}.
   *   <li>Then return size is three.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Class)"})
  public void testFromJsonWithJsonElementClass_givenProperty_thenReturnSizeIsThree4()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("Property", JsonNull.INSTANCE);
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> classOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(3, ((Map<String, Object>) actualFromJsonResult).size());
    assertNull(((Map<String, Object>) actualFromJsonResult).get("Property"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("type must not be null"));
    assertTrue(((Map<String, Object>) actualFromJsonResult).containsKey("in == null"));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Class)} with {@code JsonElement}, {@code Class}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Class)"})
  public void testFromJsonWithJsonElementClass_thenReturnFalse() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonPrimitive json = new JsonPrimitive(false);
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertFalse((Boolean) gson.fromJson(json, classOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Class)} with {@code JsonElement}, {@code Class}.
   *
   * <ul>
   *   <li>Then return {@code in == null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Class)"})
  public void testFromJsonWithJsonElementClass_thenReturnInNull() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonPrimitive json = new JsonPrimitive("in == null");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertEquals("in == null", gson.fromJson(json, classOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Class)} with {@code JsonElement}, {@code Class}.
   *
   * <ul>
   *   <li>Then return {@link LazilyParsedNumber}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Class)"})
  public void testFromJsonWithJsonElementClass_thenReturnLazilyParsedNumber()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonPrimitive json = new JsonPrimitive("in == null");
    Class<LazilyParsedNumber> classOfT = LazilyParsedNumber.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof LazilyParsedNumber);
    assertEquals("in == null", actualFromJsonResult.toString());
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Class)} with {@code JsonElement}, {@code Class}.
   *
   * <ul>
   *   <li>Then return not first.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Class)"})
  public void testFromJsonWithJsonElementClass_thenReturnNotFirst() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonArray json = new JsonArray(3);
    json.add(false);
    Class<Object> classOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof List);
    assertEquals(1, ((List<Boolean>) actualFromJsonResult).size());
    assertFalse(((List<Boolean>) actualFromJsonResult).get(0));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Class)} with {@code JsonElement}, {@code Class}.
   *
   * <ul>
   *   <li>Then return not {@code type must not be null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Class)"})
  public void testFromJsonWithJsonElementClass_thenReturnNotTypeMustNotBeNull()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.addProperty("type must not be null", false);
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> classOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(2, ((Map<String, Boolean>) actualFromJsonResult).size());
    assertNull(((Map<String, Boolean>) actualFromJsonResult).get("in == null"));
    assertFalse(((Map<String, Boolean>) actualFromJsonResult).get("type must not be null"));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Class)} with {@code JsonElement}, {@code Class}.
   *
   * <ul>
   *   <li>Then return size is two.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Class)"})
  public void testFromJsonWithJsonElementClass_thenReturnSizeIsTwo() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> classOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(2, ((Map<String, Object>) actualFromJsonResult).size());
    assertNull(((Map<String, Object>) actualFromJsonResult).get("in == null"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("type must not be null"));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Class)} with {@code JsonElement}, {@code Class}.
   *
   * <ul>
   *   <li>Then return {@code type must not be null} doubleValue is one.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Class)"})
  public void testFromJsonWithJsonElementClass_thenReturnTypeMustNotBeNullDoubleValueIsOne()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.addProperty("type must not be null", Integer.valueOf(1));
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> classOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(2, ((Map<String, Double>) actualFromJsonResult).size());
    assertNull(((Map<String, Double>) actualFromJsonResult).get("in == null"));
    assertEquals(
        1.0d,
        ((Map<String, Double>) actualFromJsonResult).get("type must not be null").doubleValue(),
        0.0);
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Class)} with {@code JsonElement}, {@code Class}.
   *
   * <ul>
   *   <li>Then return {@code type must not be null} is {@code in == null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Class)"})
  public void testFromJsonWithJsonElementClass_thenReturnTypeMustNotBeNullIsInNull()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.addProperty("type must not be null", "in == null");
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> classOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(2, ((Map<String, String>) actualFromJsonResult).size());
    assertEquals(
        "in == null", ((Map<String, String>) actualFromJsonResult).get("type must not be null"));
    assertNull(((Map<String, String>) actualFromJsonResult).get("in == null"));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Class)} with {@code JsonElement}, {@code Class}.
   *
   * <ul>
   *   <li>When {@code LazilyParsedNumber}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Class)"})
  public void testFromJsonWithJsonElementClass_whenComGoogleGsonInternalLazilyParsedNumber()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<LazilyParsedNumber> classOfT = LazilyParsedNumber.class;

    // Act and Assert
    assertNull(gson.fromJson(JsonNull.INSTANCE, classOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Class)} with {@code JsonElement}, {@code Class}.
   *
   * <ul>
   *   <li>When {@code TypeAdapterFactory}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Class)"})
  public void testFromJsonWithJsonElementClass_whenComGoogleGsonTypeAdapterFactory()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<TypeAdapterFactory> classOfT = TypeAdapterFactory.class;

    // Act and Assert
    assertNull(gson.fromJson(JsonNull.INSTANCE, classOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Class)} with {@code JsonElement}, {@code Class}.
   *
   * <ul>
   *   <li>When {@code Long}.
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Class)"})
  public void testFromJsonWithJsonElementClass_whenJavaLangLong_thenThrowJsonSyntaxException()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonObject json = new JsonObject();
    Class<Long> classOfT = Long.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, classOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Class)} with {@code JsonElement}, {@code Class}.
   *
   * <ul>
   *   <li>When {@link JsonArray#JsonArray(int)} with capacity is three.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Class)"})
  public void testFromJsonWithJsonElementClass_whenJsonArrayWithCapacityIsThree()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonArray json = new JsonArray(3);
    Class<LazilyParsedNumber> classOfT = LazilyParsedNumber.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, classOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Class)} with {@code JsonElement}, {@code Class}.
   *
   * <ul>
   *   <li>When {@link JsonArray#JsonArray(int)} with capacity is three.
   *   <li>Then return {@link List}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Class)"})
  public void testFromJsonWithJsonElementClass_whenJsonArrayWithCapacityIsThree_thenReturnList()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonArray json = new JsonArray(3);
    Class<Object> classOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof List);
    assertTrue(((List<Object>) actualFromJsonResult).isEmpty());
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Class)} with {@code JsonElement}, {@code Class}.
   *
   * <ul>
   *   <li>When {@link JsonObject} (default constructor).
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Class)"})
  public void testFromJsonWithJsonElementClass_whenJsonObject_thenThrowJsonSyntaxException()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonObject json = new JsonObject();
    Class<LazilyParsedNumber> classOfT = LazilyParsedNumber.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, classOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Class)} with {@code JsonElement}, {@code Class}.
   *
   * <ul>
   *   <li>When {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Class)"})
  public void testFromJsonWithJsonElementClass_whenJsonPrimitiveWithBoolIsTrue()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonPrimitive json = new JsonPrimitive(true);
    Class<LazilyParsedNumber> classOfT = LazilyParsedNumber.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, classOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Class)} with {@code JsonElement}, {@code Class}.
   *
   * <ul>
   *   <li>When {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Class)"})
  public void testFromJsonWithJsonElementClass_whenJsonPrimitiveWithBoolIsTrue_thenReturnTrue()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonPrimitive json = new JsonPrimitive(true);
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertTrue((Boolean) gson.fromJson(json, classOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Type)} with {@code JsonElement}, {@code Type}.
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Type)"})
  public void testFromJsonWithJsonElementType() throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertNull(gson.fromJson(JsonNull.INSTANCE, (Type) typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, TypeToken)} with {@code JsonElement}, {@code TypeToken}.
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, TypeToken)"})
  public void testFromJsonWithJsonElementTypeToken() throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
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
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertNull(gson.fromJson(JsonNull.INSTANCE, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, TypeToken)} with {@code JsonElement}, {@code TypeToken}.
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, TypeToken)"})
  public void testFromJsonWithJsonElementTypeToken2() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.addProperty("type must not be null", Integer.valueOf(255));
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(json, typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(2, ((Map<String, Double>) actualFromJsonResult).size());
    assertNull(((Map<String, Double>) actualFromJsonResult).get("in == null"));
    assertEquals(
        255.0d,
        ((Map<String, Double>) actualFromJsonResult).get("type must not be null").doubleValue(),
        0.0);
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, TypeToken)} with {@code JsonElement}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>Then return size is four.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, TypeToken)"})
  public void testFromJsonWithJsonElementTypeToken_given42_thenReturnSizeIsFour()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("42", JsonNull.INSTANCE);
    json.add("Property", JsonNull.INSTANCE);
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(json, typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(4, ((Map<String, Object>) actualFromJsonResult).size());
    assertNull(((Map<String, Object>) actualFromJsonResult).get("42"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("Property"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("type must not be null"));
    assertTrue(((Map<String, Object>) actualFromJsonResult).containsKey("in == null"));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, TypeToken)} with {@code JsonElement}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@link JsonNull#INSTANCE}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, TypeToken)"})
  public void testFromJsonWithJsonElementTypeToken_givenGson_whenInstance_thenReturnNull()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertNull(gson.fromJson(JsonNull.INSTANCE, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, TypeToken)} with {@code JsonElement}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@link JsonObject} (default constructor).
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, TypeToken)"})
  public void testFromJsonWithJsonElementTypeToken_givenGson_whenJsonObject_thenReturnEmpty()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonObject json = new JsonObject();
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(json, typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertTrue(((Map<Object, Object>) actualFromJsonResult).isEmpty());
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, TypeToken)} with {@code JsonElement}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, TypeToken)"})
  public void testFromJsonWithJsonElementTypeToken_givenGson_whenNull_thenReturnNull()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertNull(gson.fromJson((JsonElement) null, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, TypeToken)} with {@code JsonElement}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Given {@link JsonNull#INSTANCE}.
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, TypeToken)"})
  public void testFromJsonWithJsonElementTypeToken_givenInstance_thenReturnSizeIsOne()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(json, typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(1, ((Map<String, Object>) actualFromJsonResult).size());
    assertNull(((Map<String, Object>) actualFromJsonResult).get("in == null"));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, TypeToken)} with {@code JsonElement}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, TypeToken)"})
  public void testFromJsonWithJsonElementTypeToken_givenJsonArrayWithCapacityIsThree()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("in == null", new JsonArray(3));
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(json, typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(1, ((Map<String, ArrayList>) actualFromJsonResult).size());
    assertTrue(((Map<String, ArrayList>) actualFromJsonResult).get("in == null").isEmpty());
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, TypeToken)} with {@code JsonElement}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, TypeToken)"})
  public void testFromJsonWithJsonElementTypeToken_givenJsonObject() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("in == null", new JsonObject());
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(json, typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(1, ((Map<String, LinkedTreeMap>) actualFromJsonResult).size());
    assertTrue(((Map<String, LinkedTreeMap>) actualFromJsonResult).get("in == null").isEmpty());
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, TypeToken)} with {@code JsonElement}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Given {@code Property}.
   *   <li>Then return size is three.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, TypeToken)"})
  public void testFromJsonWithJsonElementTypeToken_givenProperty_thenReturnSizeIsThree()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("Property", JsonNull.INSTANCE);
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(json, typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(3, ((Map<String, Object>) actualFromJsonResult).size());
    assertNull(((Map<String, Object>) actualFromJsonResult).get("Property"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("type must not be null"));
    assertTrue(((Map<String, Object>) actualFromJsonResult).containsKey("in == null"));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, TypeToken)} with {@code JsonElement}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Given {@code Property}.
   *   <li>Then return size is three.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, TypeToken)"})
  public void testFromJsonWithJsonElementTypeToken_givenProperty_thenReturnSizeIsThree2()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("in == null", JsonNull.INSTANCE);
    json.add("Property", JsonNull.INSTANCE);
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(json, typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(3, ((Map<String, Object>) actualFromJsonResult).size());
    assertNull(((Map<String, Object>) actualFromJsonResult).get("Property"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("type must not be null"));
    assertTrue(((Map<String, Object>) actualFromJsonResult).containsKey("in == null"));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, TypeToken)} with {@code JsonElement}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Given {@code Property}.
   *   <li>Then return size is three.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, TypeToken)"})
  public void testFromJsonWithJsonElementTypeToken_givenProperty_thenReturnSizeIsThree3()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("in == null", JsonNull.INSTANCE);
    json.add("Property", JsonNull.INSTANCE);
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(json, typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(3, ((Map<String, Object>) actualFromJsonResult).size());
    assertNull(((Map<String, Object>) actualFromJsonResult).get("Property"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("type must not be null"));
    assertTrue(((Map<String, Object>) actualFromJsonResult).containsKey("in == null"));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, TypeToken)} with {@code JsonElement}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Given {@code Property}.
   *   <li>Then return size is three.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, TypeToken)"})
  public void testFromJsonWithJsonElementTypeToken_givenProperty_thenReturnSizeIsThree4()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("Property", JsonNull.INSTANCE);
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(json, typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(3, ((Map<String, Object>) actualFromJsonResult).size());
    assertNull(((Map<String, Object>) actualFromJsonResult).get("Property"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("type must not be null"));
    assertTrue(((Map<String, Object>) actualFromJsonResult).containsKey("in == null"));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, TypeToken)} with {@code JsonElement}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, TypeToken)"})
  public void testFromJsonWithJsonElementTypeToken_thenReturnFalse() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonPrimitive json = new JsonPrimitive(false);
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertFalse((Boolean) gson.fromJson(json, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, TypeToken)} with {@code JsonElement}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Then return {@code in == null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, TypeToken)"})
  public void testFromJsonWithJsonElementTypeToken_thenReturnInNull() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonPrimitive json = new JsonPrimitive("in == null");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertEquals("in == null", gson.fromJson(json, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, TypeToken)} with {@code JsonElement}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Then return {@link List}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, TypeToken)"})
  public void testFromJsonWithJsonElementTypeToken_thenReturnList() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonArray json = new JsonArray(3);
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(json, typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof List);
    assertTrue(((List<Object>) actualFromJsonResult).isEmpty());
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, TypeToken)} with {@code JsonElement}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Then return not first.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, TypeToken)"})
  public void testFromJsonWithJsonElementTypeToken_thenReturnNotFirst() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonArray json = new JsonArray(3);
    json.add(false);
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(json, typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof List);
    assertEquals(1, ((List<Boolean>) actualFromJsonResult).size());
    assertFalse(((List<Boolean>) actualFromJsonResult).get(0));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, TypeToken)} with {@code JsonElement}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Then return not {@code type must not be null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, TypeToken)"})
  public void testFromJsonWithJsonElementTypeToken_thenReturnNotTypeMustNotBeNull()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.addProperty("type must not be null", false);
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(json, typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(2, ((Map<String, Boolean>) actualFromJsonResult).size());
    assertNull(((Map<String, Boolean>) actualFromJsonResult).get("in == null"));
    assertFalse(((Map<String, Boolean>) actualFromJsonResult).get("type must not be null"));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, TypeToken)} with {@code JsonElement}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Then return size is two.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, TypeToken)"})
  public void testFromJsonWithJsonElementTypeToken_thenReturnSizeIsTwo()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(json, typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(2, ((Map<String, Object>) actualFromJsonResult).size());
    assertNull(((Map<String, Object>) actualFromJsonResult).get("in == null"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("type must not be null"));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, TypeToken)} with {@code JsonElement}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, TypeToken)"})
  public void testFromJsonWithJsonElementTypeToken_thenReturnTrue() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonPrimitive json = new JsonPrimitive(true);
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertTrue((Boolean) gson.fromJson(json, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, TypeToken)} with {@code JsonElement}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Then return {@code type must not be null} is {@code in == null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, TypeToken)"})
  public void testFromJsonWithJsonElementTypeToken_thenReturnTypeMustNotBeNullIsInNull()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.addProperty("type must not be null", "in == null");
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(json, typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(2, ((Map<String, String>) actualFromJsonResult).size());
    assertEquals(
        "in == null", ((Map<String, String>) actualFromJsonResult).get("type must not be null"));
    assertNull(((Map<String, String>) actualFromJsonResult).get("in == null"));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Type)} with {@code JsonElement}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>Then return size is four.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Type)"})
  public void testFromJsonWithJsonElementType_given42_thenReturnSizeIsFour()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("42", JsonNull.INSTANCE);
    json.add("Property", JsonNull.INSTANCE);
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> typeOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, (Type) typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(4, ((Map<String, Object>) actualFromJsonResult).size());
    assertNull(((Map<String, Object>) actualFromJsonResult).get("42"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("Property"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("type must not be null"));
    assertTrue(((Map<String, Object>) actualFromJsonResult).containsKey("in == null"));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Type)} with {@code JsonElement}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@link JsonNull#INSTANCE}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Type)"})
  public void testFromJsonWithJsonElementType_givenGson_whenInstance_thenReturnNull()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertNull(gson.fromJson(JsonNull.INSTANCE, (Type) typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Type)} with {@code JsonElement}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code Long}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Type)"})
  public void testFromJsonWithJsonElementType_givenGson_whenJavaLangLong_thenReturnNull()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Long> typeOfT = Long.class;

    // Act and Assert
    assertNull(gson.fromJson(JsonNull.INSTANCE, (Type) typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Type)} with {@code JsonElement}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code BigDecimal}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Type)"})
  public void testFromJsonWithJsonElementType_givenGson_whenJavaMathBigDecimal_thenReturnNull()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<BigDecimal> typeOfT = BigDecimal.class;

    // Act and Assert
    assertNull(gson.fromJson(JsonNull.INSTANCE, (Type) typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Type)} with {@code JsonElement}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@link JsonObject} (default constructor).
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Type)"})
  public void testFromJsonWithJsonElementType_givenGson_whenJsonObject_thenReturnEmpty()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonObject json = new JsonObject();
    Class<Object> typeOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, (Type) typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertTrue(((Map<Object, Object>) actualFromJsonResult).isEmpty());
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Type)} with {@code JsonElement}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Type)"})
  public void testFromJsonWithJsonElementType_givenGson_whenNull_thenReturnNull()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertNull(gson.fromJson((JsonElement) null, (Type) typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Type)} with {@code JsonElement}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@code in == null}.
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Type)"})
  public void testFromJsonWithJsonElementType_givenInNull_thenReturnSizeIsOne()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> typeOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, (Type) typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(1, ((Map<String, Object>) actualFromJsonResult).size());
    assertNull(((Map<String, Object>) actualFromJsonResult).get("in == null"));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Type)} with {@code JsonElement}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@code Property}.
   *   <li>Then return size is three.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Type)"})
  public void testFromJsonWithJsonElementType_givenProperty_thenReturnSizeIsThree()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("Property", JsonNull.INSTANCE);
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> typeOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, (Type) typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(3, ((Map<String, Object>) actualFromJsonResult).size());
    assertNull(((Map<String, Object>) actualFromJsonResult).get("Property"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("type must not be null"));
    assertTrue(((Map<String, Object>) actualFromJsonResult).containsKey("in == null"));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Type)} with {@code JsonElement}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@code Property}.
   *   <li>Then return size is three.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Type)"})
  public void testFromJsonWithJsonElementType_givenProperty_thenReturnSizeIsThree2()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("in == null", JsonNull.INSTANCE);
    json.add("Property", JsonNull.INSTANCE);
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> typeOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, (Type) typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(3, ((Map<String, Object>) actualFromJsonResult).size());
    assertNull(((Map<String, Object>) actualFromJsonResult).get("Property"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("type must not be null"));
    assertTrue(((Map<String, Object>) actualFromJsonResult).containsKey("in == null"));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Type)} with {@code JsonElement}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@code Property}.
   *   <li>Then return size is three.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Type)"})
  public void testFromJsonWithJsonElementType_givenProperty_thenReturnSizeIsThree3()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("in == null", JsonNull.INSTANCE);
    json.add("Property", JsonNull.INSTANCE);
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> typeOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, (Type) typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(3, ((Map<String, Object>) actualFromJsonResult).size());
    assertNull(((Map<String, Object>) actualFromJsonResult).get("Property"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("type must not be null"));
    assertTrue(((Map<String, Object>) actualFromJsonResult).containsKey("in == null"));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Type)} with {@code JsonElement}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@code Property}.
   *   <li>Then return size is three.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Type)"})
  public void testFromJsonWithJsonElementType_givenProperty_thenReturnSizeIsThree4()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("Property", JsonNull.INSTANCE);
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> typeOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, (Type) typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(3, ((Map<String, Object>) actualFromJsonResult).size());
    assertNull(((Map<String, Object>) actualFromJsonResult).get("Property"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("type must not be null"));
    assertTrue(((Map<String, Object>) actualFromJsonResult).containsKey("in == null"));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Type)} with {@code JsonElement}, {@code Type}.
   *
   * <ul>
   *   <li>Then return {@code in == null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Type)"})
  public void testFromJsonWithJsonElementType_thenReturnInNull() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonPrimitive json = new JsonPrimitive("in == null");
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertEquals("in == null", gson.fromJson(json, (Type) typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Type)} with {@code JsonElement}, {@code Type}.
   *
   * <ul>
   *   <li>Then return {@link LazilyParsedNumber}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Type)"})
  public void testFromJsonWithJsonElementType_thenReturnLazilyParsedNumber()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonPrimitive json = new JsonPrimitive("in == null");
    Class<LazilyParsedNumber> typeOfT = LazilyParsedNumber.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, (Type) typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof LazilyParsedNumber);
    assertEquals("in == null", actualFromJsonResult.toString());
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Type)} with {@code JsonElement}, {@code Type}.
   *
   * <ul>
   *   <li>Then return not first.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Type)"})
  public void testFromJsonWithJsonElementType_thenReturnNotFirst() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonArray json = new JsonArray(3);
    json.add(false);
    Class<Object> typeOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, (Type) typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof List);
    assertEquals(1, ((List<Boolean>) actualFromJsonResult).size());
    assertFalse(((List<Boolean>) actualFromJsonResult).get(0));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Type)} with {@code JsonElement}, {@code Type}.
   *
   * <ul>
   *   <li>Then return not {@code type must not be null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Type)"})
  public void testFromJsonWithJsonElementType_thenReturnNotTypeMustNotBeNull()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.addProperty("type must not be null", false);
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> typeOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, (Type) typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(2, ((Map<String, Boolean>) actualFromJsonResult).size());
    assertNull(((Map<String, Boolean>) actualFromJsonResult).get("in == null"));
    assertFalse(((Map<String, Boolean>) actualFromJsonResult).get("type must not be null"));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Type)} with {@code JsonElement}, {@code Type}.
   *
   * <ul>
   *   <li>Then return size is two.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Type)"})
  public void testFromJsonWithJsonElementType_thenReturnSizeIsTwo() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.add("type must not be null", JsonNull.INSTANCE);
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> typeOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, (Type) typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(2, ((Map<String, Object>) actualFromJsonResult).size());
    assertNull(((Map<String, Object>) actualFromJsonResult).get("in == null"));
    assertNull(((Map<String, Object>) actualFromJsonResult).get("type must not be null"));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Type)} with {@code JsonElement}, {@code Type}.
   *
   * <ul>
   *   <li>Then return {@code type must not be null} doubleValue is one.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Type)"})
  public void testFromJsonWithJsonElementType_thenReturnTypeMustNotBeNullDoubleValueIsOne()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.addProperty("type must not be null", Integer.valueOf(1));
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> typeOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, (Type) typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(2, ((Map<String, Double>) actualFromJsonResult).size());
    assertNull(((Map<String, Double>) actualFromJsonResult).get("in == null"));
    assertEquals(
        1.0d,
        ((Map<String, Double>) actualFromJsonResult).get("type must not be null").doubleValue(),
        0.0);
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Type)} with {@code JsonElement}, {@code Type}.
   *
   * <ul>
   *   <li>Then return {@code type must not be null} is {@code in == null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Type)"})
  public void testFromJsonWithJsonElementType_thenReturnTypeMustNotBeNullIsInNull()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonObject json = new JsonObject();
    json.addProperty("type must not be null", "in == null");
    json.add("in == null", JsonNull.INSTANCE);
    Class<Object> typeOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, (Type) typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertEquals(2, ((Map<String, String>) actualFromJsonResult).size());
    assertEquals(
        "in == null", ((Map<String, String>) actualFromJsonResult).get("type must not be null"));
    assertNull(((Map<String, String>) actualFromJsonResult).get("in == null"));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Type)} with {@code JsonElement}, {@code Type}.
   *
   * <ul>
   *   <li>When {@code LazilyParsedNumber}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Type)"})
  public void testFromJsonWithJsonElementType_whenComGoogleGsonInternalLazilyParsedNumber()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<LazilyParsedNumber> typeOfT = LazilyParsedNumber.class;

    // Act and Assert
    assertNull(gson.fromJson(JsonNull.INSTANCE, (Type) typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Type)} with {@code JsonElement}, {@code Type}.
   *
   * <ul>
   *   <li>When {@code TypeAdapterFactory}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Type)"})
  public void testFromJsonWithJsonElementType_whenComGoogleGsonTypeAdapterFactory()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<TypeAdapterFactory> typeOfT = TypeAdapterFactory.class;

    // Act and Assert
    assertNull(gson.fromJson(JsonNull.INSTANCE, (Type) typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Type)} with {@code JsonElement}, {@code Type}.
   *
   * <ul>
   *   <li>When {@code Long}.
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Type)"})
  public void testFromJsonWithJsonElementType_whenJavaLangLong_thenThrowJsonSyntaxException()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonObject json = new JsonObject();
    Class<Long> typeOfT = Long.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, (Type) typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Type)} with {@code JsonElement}, {@code Type}.
   *
   * <ul>
   *   <li>When {@link JsonArray#JsonArray(int)} with capacity is three.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Type)"})
  public void testFromJsonWithJsonElementType_whenJsonArrayWithCapacityIsThree()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonArray json = new JsonArray(3);
    Class<LazilyParsedNumber> typeOfT = LazilyParsedNumber.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, (Type) typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Type)} with {@code JsonElement}, {@code Type}.
   *
   * <ul>
   *   <li>When {@link JsonArray#JsonArray(int)} with capacity is three.
   *   <li>Then return {@link List}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Type)"})
  public void testFromJsonWithJsonElementType_whenJsonArrayWithCapacityIsThree_thenReturnList()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonArray json = new JsonArray(3);
    Class<Object> typeOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, (Type) typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof List);
    assertTrue(((List<Object>) actualFromJsonResult).isEmpty());
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Type)} with {@code JsonElement}, {@code Type}.
   *
   * <ul>
   *   <li>When {@link JsonObject} (default constructor).
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Type)"})
  public void testFromJsonWithJsonElementType_whenJsonObject_thenThrowJsonSyntaxException()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonObject json = new JsonObject();
    Class<LazilyParsedNumber> typeOfT = LazilyParsedNumber.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, (Type) typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Type)} with {@code JsonElement}, {@code Type}.
   *
   * <ul>
   *   <li>When {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Type)"})
  public void testFromJsonWithJsonElementType_whenJsonPrimitiveWithBoolIsFalse_thenReturnFalse()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonPrimitive json = new JsonPrimitive(false);
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertFalse((Boolean) gson.fromJson(json, (Type) typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Type)} with {@code JsonElement}, {@code Type}.
   *
   * <ul>
   *   <li>When {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Type)"})
  public void testFromJsonWithJsonElementType_whenJsonPrimitiveWithBoolIsTrue()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonPrimitive json = new JsonPrimitive(true);
    Class<LazilyParsedNumber> typeOfT = LazilyParsedNumber.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, (Type) typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Type)} with {@code JsonElement}, {@code Type}.
   *
   * <ul>
   *   <li>When {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Type)"})
  public void testFromJsonWithJsonElementType_whenJsonPrimitiveWithBoolIsTrue_thenReturnTrue()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonPrimitive json = new JsonPrimitive(true);
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertTrue((Boolean) gson.fromJson(json, (Type) typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, Type)} with {@code JsonReader}, {@code Type}.
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, Type)"})
  public void testFromJsonWithJsonReaderType() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LEGACY_STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new StringReader("foo"));
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(reader, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, Type)} with {@code JsonReader}, {@code Type}.
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, Type)"})
  public void testFromJsonWithJsonReaderType2() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new StringReader("foo"));
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(reader, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, Type)} with {@code JsonReader}, {@code Type}.
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, Type)"})
  public void testFromJsonWithJsonReaderType3()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new IOException("type must not be null"));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new StringReader("42"));
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(reader, typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, Type)} with {@code JsonReader}, {@code Type}.
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, Type)"})
  public void testFromJsonWithJsonReaderType4()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new EOFException("type must not be null"));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new StringReader("42"));
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(reader, typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, Type)} with {@code JsonReader}, {@code Type}.
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, Type)"})
  public void testFromJsonWithJsonReaderType5()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new IllegalStateException("type must not be null"));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new StringReader("42"));
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(reader, typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, TypeToken)} with {@code JsonReader}, {@code TypeToken}.
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, TypeToken)"})
  public void testFromJsonWithJsonReaderTypeToken() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LEGACY_STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new StringReader("foo"));
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(reader, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, TypeToken)} with {@code JsonReader}, {@code TypeToken}.
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, TypeToken)"})
  public void testFromJsonWithJsonReaderTypeToken2() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new StringReader("foo"));
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(reader, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, TypeToken)} with {@code JsonReader}, {@code TypeToken}.
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, TypeToken)"})
  public void testFromJsonWithJsonReaderTypeToken3()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new IOException("type must not be null"));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new StringReader("42"));
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(reader, typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, TypeToken)} with {@code JsonReader}, {@code TypeToken}.
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, TypeToken)"})
  public void testFromJsonWithJsonReaderTypeToken4()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new EOFException("type must not be null"));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new StringReader("42"));
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(reader, typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, TypeToken)} with {@code JsonReader}, {@code TypeToken}.
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, TypeToken)"})
  public void testFromJsonWithJsonReaderTypeToken5()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new IllegalStateException("type must not be null"));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new StringReader("42"));
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(reader, typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, TypeToken)} with {@code JsonReader}, {@code TypeToken}.
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, TypeToken)"})
  public void testFromJsonWithJsonReaderTypeToken6()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonTreeReader reader = new JsonTreeReader(JsonNull.INSTANCE);
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertNull(gson.fromJson(reader, typeOfT));
    assertFalse(reader.hasNext());
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, TypeToken)} with {@code JsonReader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Given {@link ToNumberStrategy} {@link ToNumberStrategy#readNumber(JsonReader)} return
   *       {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, TypeToken)"})
  public void testFromJsonWithJsonReaderTypeToken_givenToNumberStrategyReadNumberReturnNull()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any())).thenReturn(null);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new StringReader("42"));
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(reader, typeOfT);

    // Assert
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
    assertNull(actualFromJsonResult);
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, TypeToken)} with {@code JsonReader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Then return doubleValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, TypeToken)"})
  public void testFromJsonWithJsonReaderTypeToken_thenReturnDoubleValueIsFortyTwo()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonReader reader = new JsonReader(new StringReader("42"));
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertEquals(42.0d, ((Double) gson.fromJson(reader, typeOfT)).doubleValue(), 0.0);
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, TypeToken)} with {@code JsonReader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Then return intValue is one.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, TypeToken)"})
  public void testFromJsonWithJsonReaderTypeToken_thenReturnIntValueIsOne()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    Integer valueOfResult = Integer.valueOf(1);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any())).thenReturn(valueOfResult);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new StringReader("42"));
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(reader, typeOfT);

    // Assert
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
    assertEquals(1, ((Integer) actualFromJsonResult).intValue());
    assertSame(valueOfResult, actualFromJsonResult);
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, TypeToken)} with {@code JsonReader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Then return {@code type}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, TypeToken)"})
  public void testFromJsonWithJsonReaderTypeToken_thenReturnType()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonReader reader = new JsonReader(new StringReader("type must not be null"));
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertEquals("type", gson.fromJson(reader, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, TypeToken)} with {@code JsonReader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>When {@link FileReader#FileReader(FileDescriptor)} with {@link
   *       FileDescriptor#FileDescriptor()}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, TypeToken)"})
  public void testFromJsonWithJsonReaderTypeToken_whenFileReaderWithFileDescriptor()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new FileReader(new FileDescriptor()));
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(reader, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, TypeToken)} with {@code JsonReader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with empty string.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, TypeToken)"})
  public void testFromJsonWithJsonReaderTypeToken_whenStringReaderWithEmptyString()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonReader reader = new JsonReader(new StringReader(""));
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertNull(gson.fromJson(reader, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, TypeToken)} with {@code JsonReader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, TypeToken)"})
  public void testFromJsonWithJsonReaderTypeToken_whenStringReaderWithFalseToString()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonReader reader = new JsonReader(new StringReader(Boolean.FALSE.toString()));
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertFalse((Boolean) gson.fromJson(reader, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, TypeToken)} with {@code JsonReader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code FALSE}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, TypeToken)"})
  public void testFromJsonWithJsonReaderTypeToken_whenStringReaderWithFalse_thenReturnFalse()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonReader reader = new JsonReader(new StringReader("FALSE"));
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertFalse((Boolean) gson.fromJson(reader, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, TypeToken)} with {@code JsonReader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then return {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, TypeToken)"})
  public void testFromJsonWithJsonReaderTypeToken_whenStringReaderWithFoo_thenReturnFoo()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonReader reader = new JsonReader(new StringReader("foo"));
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertEquals("foo", gson.fromJson(reader, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, TypeToken)} with {@code JsonReader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then return {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, TypeToken)"})
  public void testFromJsonWithJsonReaderTypeToken_whenStringReaderWithFoo_thenReturnFoo2()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new StringReader("foo"));
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertEquals("foo", gson.fromJson(reader, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, Type)} with {@code JsonReader}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then return {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, Type)"})
  public void testFromJsonWithJsonReaderType_givenGson_whenStringReaderWithFoo_thenReturnFoo()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonReader reader = new JsonReader(new StringReader("foo"));
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertEquals("foo", gson.fromJson(reader, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, Type)} with {@code JsonReader}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link ToNumberStrategy} {@link ToNumberStrategy#readNumber(JsonReader)} return
   *       {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, Type)"})
  public void testFromJsonWithJsonReaderType_givenToNumberStrategyReadNumberReturnNull()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any())).thenReturn(null);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new StringReader("42"));
    Class<Object> typeOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(reader, typeOfT);

    // Assert
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
    assertNull(actualFromJsonResult);
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, Type)} with {@code JsonReader}, {@code Type}.
   *
   * <ul>
   *   <li>Then not {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE} hasNext.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, Type)"})
  public void testFromJsonWithJsonReaderType_thenNotJsonTreeReaderWithElementIsInstanceHasNext()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonTreeReader reader = new JsonTreeReader(JsonNull.INSTANCE);
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertNull(gson.fromJson(reader, typeOfT));
    assertFalse(reader.hasNext());
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, Type)} with {@code JsonReader}, {@code Type}.
   *
   * <ul>
   *   <li>Then return doubleValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, Type)"})
  public void testFromJsonWithJsonReaderType_thenReturnDoubleValueIsFortyTwo()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonReader reader = new JsonReader(new StringReader("42"));
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertEquals(42.0d, ((Double) gson.fromJson(reader, typeOfT)).doubleValue(), 0.0);
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, Type)} with {@code JsonReader}, {@code Type}.
   *
   * <ul>
   *   <li>Then return intValue is one.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, Type)"})
  public void testFromJsonWithJsonReaderType_thenReturnIntValueIsOne()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    Integer valueOfResult = Integer.valueOf(1);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any())).thenReturn(valueOfResult);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new StringReader("42"));
    Class<Object> typeOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(reader, typeOfT);

    // Assert
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
    assertEquals(1, ((Integer) actualFromJsonResult).intValue());
    assertSame(valueOfResult, actualFromJsonResult);
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, Type)} with {@code JsonReader}, {@code Type}.
   *
   * <ul>
   *   <li>Then return null.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, Type)"})
  public void testFromJsonWithJsonReaderType_thenReturnNull()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new CharArrayReader("A\u0000A\u0000".toCharArray(), 1, 1));
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertEquals("\u0000", gson.fromJson(reader, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, Type)} with {@code JsonReader}, {@code Type}.
   *
   * <ul>
   *   <li>Then return toString is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, Type)"})
  public void testFromJsonWithJsonReaderType_thenReturnToStringIs42()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new StringReader("42"));
    Class<LazilyParsedNumber> typeOfT = LazilyParsedNumber.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(reader, typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof LazilyParsedNumber);
    assertEquals("42", actualFromJsonResult.toString());
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, Type)} with {@code JsonReader}, {@code Type}.
   *
   * <ul>
   *   <li>Then return toString is {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, Type)"})
  public void testFromJsonWithJsonReaderType_thenReturnToStringIsFoo()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonReader reader = new JsonReader(new StringReader("foo"));
    Class<LazilyParsedNumber> typeOfT = LazilyParsedNumber.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(reader, typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof LazilyParsedNumber);
    assertEquals("foo", actualFromJsonResult.toString());
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, Type)} with {@code JsonReader}, {@code Type}.
   *
   * <ul>
   *   <li>Then return {@code type}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, Type)"})
  public void testFromJsonWithJsonReaderType_thenReturnType()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonReader reader = new JsonReader(new StringReader("type must not be null"));
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertEquals("type", gson.fromJson(reader, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, Type)} with {@code JsonReader}, {@code Type}.
   *
   * <ul>
   *   <li>When {@link FileReader#FileReader(FileDescriptor)} with {@link
   *       FileDescriptor#FileDescriptor()}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, Type)"})
  public void testFromJsonWithJsonReaderType_whenFileReaderWithFileDescriptor()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new FileReader(new FileDescriptor()));
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(reader, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, Type)} with {@code JsonReader}, {@code Type}.
   *
   * <ul>
   *   <li>When {@code Long}.
   *   <li>Then return longValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, Type)"})
  public void testFromJsonWithJsonReaderType_whenJavaLangLong_thenReturnLongValueIsFortyTwo()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new StringReader("42"));
    Class<Long> typeOfT = Long.class;

    // Act and Assert
    assertEquals(42L, ((Long) gson.fromJson(reader, typeOfT)).longValue());
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, Type)} with {@code JsonReader}, {@code Type}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, Type)"})
  public void testFromJsonWithJsonReaderType_whenStringReaderWithEmptyString_thenReturnNull()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonReader reader = new JsonReader(new StringReader(""));
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertNull(gson.fromJson(reader, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, Type)} with {@code JsonReader}, {@code Type}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, Type)"})
  public void testFromJsonWithJsonReaderType_whenStringReaderWithFalseToString_thenReturnFalse()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonReader reader = new JsonReader(new StringReader(Boolean.FALSE.toString()));
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertFalse((Boolean) gson.fromJson(reader, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, Type)} with {@code JsonReader}, {@code Type}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code FALSE}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, Type)"})
  public void testFromJsonWithJsonReaderType_whenStringReaderWithFalse_thenReturnFalse()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonReader reader = new JsonReader(new StringReader("FALSE"));
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertFalse((Boolean) gson.fromJson(reader, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, Type)} with {@code JsonReader}, {@code Type}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then return {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, Type)"})
  public void testFromJsonWithJsonReaderType_whenStringReaderWithFoo_thenReturnFoo()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new StringReader("foo"));
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertEquals("foo", gson.fromJson(reader, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Class)} with {@code Reader}, {@code Class}.
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Class)"})
  public void testFromJsonWithReaderClass() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LEGACY_STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("foo");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, classOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Class)} with {@code Reader}, {@code Class}.
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Class)"})
  public void testFromJsonWithReaderClass2() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("foo");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, classOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Class)} with {@code Reader}, {@code Class}.
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Class)"})
  public void testFromJsonWithReaderClass3()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new IOException("in == null"));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("42");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, classOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Class)} with {@code Reader}, {@code Class}.
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Class)"})
  public void testFromJsonWithReaderClass4()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new EOFException("in == null"));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("42");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, classOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Class)} with {@code Reader}, {@code Class}.
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Class)"})
  public void testFromJsonWithReaderClass5()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new IllegalStateException("in == null"));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("42");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, classOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Class)} with {@code Reader}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code FALSE}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Class)"})
  public void testFromJsonWithReaderClass_givenGson_whenStringReaderWithFalse_thenReturnFalse()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("FALSE");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertFalse((Boolean) gson.fromJson(json, classOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Class)} with {@code Reader}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then return {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Class)"})
  public void testFromJsonWithReaderClass_givenGson_whenStringReaderWithFoo_thenReturnFoo()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("foo");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertEquals("foo", gson.fromJson(json, classOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Class)} with {@code Reader}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@link ToNumberStrategy} {@link ToNumberStrategy#readNumber(JsonReader)} return
   *       {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Class)"})
  public void testFromJsonWithReaderClass_givenToNumberStrategyReadNumberReturnNull()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any())).thenReturn(null);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("42");
    Class<Object> classOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, classOfT);

    // Assert
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
    assertNull(actualFromJsonResult);
  }

  /**
   * Test {@link Gson#fromJson(Reader, Class)} with {@code Reader}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@link ToNumberStrategy} {@link ToNumberStrategy#readNumber(JsonReader)} return
   *       valueOf one.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Class)"})
  public void testFromJsonWithReaderClass_givenToNumberStrategyReadNumberReturnValueOfOne()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenReturn(Integer.valueOf(1));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("42");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, classOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Class)} with {@code Reader}, {@code Class}.
   *
   * <ul>
   *   <li>Then return doubleValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Class)"})
  public void testFromJsonWithReaderClass_thenReturnDoubleValueIsFortyTwo()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("42");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertEquals(42.0d, ((Double) gson.fromJson(json, classOfT)).doubleValue(), 0.0);
  }

  /**
   * Test {@link Gson#fromJson(Reader, Class)} with {@code Reader}, {@code Class}.
   *
   * <ul>
   *   <li>Then return toString is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Class)"})
  public void testFromJsonWithReaderClass_thenReturnToStringIs42()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("42");
    Class<LazilyParsedNumber> classOfT = LazilyParsedNumber.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof LazilyParsedNumber);
    assertEquals("42", actualFromJsonResult.toString());
  }

  /**
   * Test {@link Gson#fromJson(Reader, Class)} with {@code Reader}, {@code Class}.
   *
   * <ul>
   *   <li>Then return toString is {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Class)"})
  public void testFromJsonWithReaderClass_thenReturnToStringIsFoo()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("foo");
    Class<LazilyParsedNumber> classOfT = LazilyParsedNumber.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof LazilyParsedNumber);
    assertEquals("foo", actualFromJsonResult.toString());
  }

  /**
   * Test {@link Gson#fromJson(Reader, Class)} with {@code Reader}, {@code Class}.
   *
   * <ul>
   *   <li>Then return ÿ start of heading ÿ.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Class)"})
  public void testFromJsonWithReaderClass_thenReturnŸStartOfHeadingŸ()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    Gson gson = new Gson();
    CharArrayReader json = new CharArrayReader("\u0001ÿ\u0001ÿ".toCharArray());
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertEquals("\u0001ÿ\u0001ÿ", gson.fromJson(json, classOfT));
    assertFalse(json.ready());
  }

  /**
   * Test {@link Gson#fromJson(Reader, Class)} with {@code Reader}, {@code Class}.
   *
   * <ul>
   *   <li>When {@link FileReader#FileReader(FileDescriptor)} with {@link
   *       FileDescriptor#FileDescriptor()}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Class)"})
  public void testFromJsonWithReaderClass_whenFileReaderWithFileDescriptor()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    FileReader json = new FileReader(new FileDescriptor());
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, classOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Class)} with {@code Reader}, {@code Class}.
   *
   * <ul>
   *   <li>When {@code Long}.
   *   <li>Then return longValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Class)"})
  public void testFromJsonWithReaderClass_whenJavaLangLong_thenReturnLongValueIsFortyTwo()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("42");
    Class<Long> classOfT = Long.class;

    // Act and Assert
    assertEquals(42L, ((Long) gson.fromJson(json, classOfT)).longValue());
  }

  /**
   * Test {@link Gson#fromJson(Reader, Class)} with {@code Reader}, {@code Class}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Class)"})
  public void testFromJsonWithReaderClass_whenStringReaderWithEmptyString_thenReturnNull()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertNull(gson.fromJson(json, classOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Class)} with {@code Reader}, {@code Class}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Class)"})
  public void testFromJsonWithReaderClass_whenStringReaderWithFalseToString_thenReturnFalse()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader(Boolean.FALSE.toString());
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertFalse((Boolean) gson.fromJson(json, classOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Class)} with {@code Reader}, {@code Class}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then return {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Class)"})
  public void testFromJsonWithReaderClass_whenStringReaderWithFoo_thenReturnFoo()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("foo");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertEquals("foo", gson.fromJson(json, classOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Class)} with {@code Reader}, {@code Class}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code in == null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Class)"})
  public void testFromJsonWithReaderClass_whenStringReaderWithInNull()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("in == null");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, classOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Class)} with {@code Reader}, {@code Class}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code in == null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Class)"})
  public void testFromJsonWithReaderClass_whenStringReaderWithInNull2()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("in == null");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, classOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Class)} with {@code Reader}, {@code Class}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code type must not be null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Class)"})
  public void testFromJsonWithReaderClass_whenStringReaderWithTypeMustNotBeNull()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("type must not be null");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, classOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Class)} with {@code Reader}, {@code Class}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code type must not be null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Class)"})
  public void testFromJsonWithReaderClass_whenStringReaderWithTypeMustNotBeNull2()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("type must not be null");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, classOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Type)} with {@code Reader}, {@code Type}.
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Type)"})
  public void testFromJsonWithReaderType() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LEGACY_STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("foo");
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, (Type) typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Type)} with {@code Reader}, {@code Type}.
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Type)"})
  public void testFromJsonWithReaderType2() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("foo");
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, (Type) typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Type)} with {@code Reader}, {@code Type}.
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Type)"})
  public void testFromJsonWithReaderType3()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new IOException("in == null"));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("42");
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, (Type) typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Type)} with {@code Reader}, {@code Type}.
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Type)"})
  public void testFromJsonWithReaderType4()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new EOFException("in == null"));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("42");
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, (Type) typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Type)} with {@code Reader}, {@code Type}.
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Type)"})
  public void testFromJsonWithReaderType5()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new IllegalStateException("in == null"));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("42");
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, (Type) typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(Reader, TypeToken)} with {@code Reader}, {@code TypeToken}.
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, TypeToken)"})
  public void testFromJsonWithReaderTypeToken() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LEGACY_STRICT,
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
    StringReader json = new StringReader("foo");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, TypeToken)} with {@code Reader}, {@code TypeToken}.
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, TypeToken)"})
  public void testFromJsonWithReaderTypeToken2() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.STRICT,
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
    StringReader json = new StringReader("foo");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, TypeToken)} with {@code Reader}, {@code TypeToken}.
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, TypeToken)"})
  public void testFromJsonWithReaderTypeToken3()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new IOException("in == null"));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
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
    StringReader json = new StringReader("42");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(Reader, TypeToken)} with {@code Reader}, {@code TypeToken}.
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, TypeToken)"})
  public void testFromJsonWithReaderTypeToken4()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new EOFException("in == null"));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
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
    StringReader json = new StringReader("42");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(Reader, TypeToken)} with {@code Reader}, {@code TypeToken}.
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, TypeToken)"})
  public void testFromJsonWithReaderTypeToken5()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new IllegalStateException("in == null"));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
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
    StringReader json = new StringReader("42");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(Reader, TypeToken)} with {@code Reader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then return {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, TypeToken)"})
  public void testFromJsonWithReaderTypeToken_givenGson_whenStringReaderWithFoo_thenReturnFoo()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("foo");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertEquals("foo", gson.fromJson(json, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, TypeToken)} with {@code Reader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Given {@link ToNumberStrategy} {@link ToNumberStrategy#readNumber(JsonReader)} return
   *       {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, TypeToken)"})
  public void testFromJsonWithReaderTypeToken_givenToNumberStrategyReadNumberReturnNull()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any())).thenReturn(null);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
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
    StringReader json = new StringReader("42");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(json, typeOfT);

    // Assert
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
    assertNull(actualFromJsonResult);
  }

  /**
   * Test {@link Gson#fromJson(Reader, TypeToken)} with {@code Reader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Given {@link ToNumberStrategy} {@link ToNumberStrategy#readNumber(JsonReader)} return
   *       valueOf one.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, TypeToken)"})
  public void testFromJsonWithReaderTypeToken_givenToNumberStrategyReadNumberReturnValueOfOne()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenReturn(Integer.valueOf(1));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
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
    StringReader json = new StringReader("42");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(Reader, TypeToken)} with {@code Reader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Then return doubleValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, TypeToken)"})
  public void testFromJsonWithReaderTypeToken_thenReturnDoubleValueIsFortyTwo()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("42");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertEquals(42.0d, ((Double) gson.fromJson(json, typeOfT)).doubleValue(), 0.0);
  }

  /**
   * Test {@link Gson#fromJson(Reader, TypeToken)} with {@code Reader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>When {@link FileReader#FileReader(FileDescriptor)} with {@link
   *       FileDescriptor#FileDescriptor()}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, TypeToken)"})
  public void testFromJsonWithReaderTypeToken_whenFileReaderWithFileDescriptor()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    FileReader json = new FileReader(new FileDescriptor());
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, TypeToken)} with {@code Reader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, TypeToken)"})
  public void testFromJsonWithReaderTypeToken_whenStringReaderWithEmptyString_thenReturnNull()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertNull(gson.fromJson(json, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, TypeToken)} with {@code Reader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, TypeToken)"})
  public void testFromJsonWithReaderTypeToken_whenStringReaderWithFalseToString()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader(Boolean.FALSE.toString());
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertFalse((Boolean) gson.fromJson(json, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, TypeToken)} with {@code Reader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code FALSE}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, TypeToken)"})
  public void testFromJsonWithReaderTypeToken_whenStringReaderWithFalse_thenReturnFalse()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("FALSE");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertFalse((Boolean) gson.fromJson(json, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, TypeToken)} with {@code Reader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then return {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, TypeToken)"})
  public void testFromJsonWithReaderTypeToken_whenStringReaderWithFoo_thenReturnFoo()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
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
    StringReader json = new StringReader("foo");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertEquals("foo", gson.fromJson(json, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, TypeToken)} with {@code Reader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code in == null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, TypeToken)"})
  public void testFromJsonWithReaderTypeToken_whenStringReaderWithInNull()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("in == null");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, TypeToken)} with {@code Reader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code in == null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, TypeToken)"})
  public void testFromJsonWithReaderTypeToken_whenStringReaderWithInNull2()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
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
    StringReader json = new StringReader("in == null");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, TypeToken)} with {@code Reader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code type must not be null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, TypeToken)"})
  public void testFromJsonWithReaderTypeToken_whenStringReaderWithTypeMustNotBeNull()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("type must not be null");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, TypeToken)} with {@code Reader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code type must not be null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, TypeToken)"})
  public void testFromJsonWithReaderTypeToken_whenStringReaderWithTypeMustNotBeNull2()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
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
    StringReader json = new StringReader("type must not be null");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Type)} with {@code Reader}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code FALSE}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Type)"})
  public void testFromJsonWithReaderType_givenGson_whenStringReaderWithFalse_thenReturnFalse()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("FALSE");
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertFalse((Boolean) gson.fromJson(json, (Type) typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Type)} with {@code Reader}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then return {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Type)"})
  public void testFromJsonWithReaderType_givenGson_whenStringReaderWithFoo_thenReturnFoo()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("foo");
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertEquals("foo", gson.fromJson(json, (Type) typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Type)} with {@code Reader}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link ToNumberStrategy} {@link ToNumberStrategy#readNumber(JsonReader)} return
   *       {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Type)"})
  public void testFromJsonWithReaderType_givenToNumberStrategyReadNumberReturnNull()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any())).thenReturn(null);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("42");
    Class<Object> typeOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, (Type) typeOfT);

    // Assert
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
    assertNull(actualFromJsonResult);
  }

  /**
   * Test {@link Gson#fromJson(Reader, Type)} with {@code Reader}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link ToNumberStrategy} {@link ToNumberStrategy#readNumber(JsonReader)} return
   *       valueOf one.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Type)"})
  public void testFromJsonWithReaderType_givenToNumberStrategyReadNumberReturnValueOfOne()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenReturn(Integer.valueOf(1));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("42");
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, (Type) typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Type)} with {@code Reader}, {@code Type}.
   *
   * <ul>
   *   <li>Then return doubleValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Type)"})
  public void testFromJsonWithReaderType_thenReturnDoubleValueIsFortyTwo()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("42");
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertEquals(42.0d, ((Double) gson.fromJson(json, (Type) typeOfT)).doubleValue(), 0.0);
  }

  /**
   * Test {@link Gson#fromJson(Reader, Type)} with {@code Reader}, {@code Type}.
   *
   * <ul>
   *   <li>Then return toString is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Type)"})
  public void testFromJsonWithReaderType_thenReturnToStringIs42()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("42");
    Class<LazilyParsedNumber> typeOfT = LazilyParsedNumber.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, (Type) typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof LazilyParsedNumber);
    assertEquals("42", actualFromJsonResult.toString());
  }

  /**
   * Test {@link Gson#fromJson(Reader, Type)} with {@code Reader}, {@code Type}.
   *
   * <ul>
   *   <li>Then return toString is {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Type)"})
  public void testFromJsonWithReaderType_thenReturnToStringIsFoo()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("foo");
    Class<LazilyParsedNumber> typeOfT = LazilyParsedNumber.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, (Type) typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof LazilyParsedNumber);
    assertEquals("foo", actualFromJsonResult.toString());
  }

  /**
   * Test {@link Gson#fromJson(Reader, Type)} with {@code Reader}, {@code Type}.
   *
   * <ul>
   *   <li>Then return ÿ start of heading ÿ.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Type)"})
  public void testFromJsonWithReaderType_thenReturnŸStartOfHeadingŸ()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    Gson gson = new Gson();
    CharArrayReader json = new CharArrayReader("\u0001ÿ\u0001ÿ".toCharArray());
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertEquals("\u0001ÿ\u0001ÿ", gson.fromJson(json, (Type) typeOfT));
    assertFalse(json.ready());
  }

  /**
   * Test {@link Gson#fromJson(Reader, Type)} with {@code Reader}, {@code Type}.
   *
   * <ul>
   *   <li>When {@link FileReader#FileReader(FileDescriptor)} with {@link
   *       FileDescriptor#FileDescriptor()}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Type)"})
  public void testFromJsonWithReaderType_whenFileReaderWithFileDescriptor()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    FileReader json = new FileReader(new FileDescriptor());
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, (Type) typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Type)} with {@code Reader}, {@code Type}.
   *
   * <ul>
   *   <li>When {@code Long}.
   *   <li>Then return longValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Type)"})
  public void testFromJsonWithReaderType_whenJavaLangLong_thenReturnLongValueIsFortyTwo()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("42");
    Class<Long> typeOfT = Long.class;

    // Act and Assert
    assertEquals(42L, ((Long) gson.fromJson(json, (Type) typeOfT)).longValue());
  }

  /**
   * Test {@link Gson#fromJson(Reader, Type)} with {@code Reader}, {@code Type}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Type)"})
  public void testFromJsonWithReaderType_whenStringReaderWithEmptyString_thenReturnNull()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("");
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertNull(gson.fromJson(json, (Type) typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Type)} with {@code Reader}, {@code Type}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Type)"})
  public void testFromJsonWithReaderType_whenStringReaderWithFalseToString_thenReturnFalse()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader(Boolean.FALSE.toString());
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertFalse((Boolean) gson.fromJson(json, (Type) typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Type)} with {@code Reader}, {@code Type}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then return {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Type)"})
  public void testFromJsonWithReaderType_whenStringReaderWithFoo_thenReturnFoo()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("foo");
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertEquals("foo", gson.fromJson(json, (Type) typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Type)} with {@code Reader}, {@code Type}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code in == null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Type)"})
  public void testFromJsonWithReaderType_whenStringReaderWithInNull()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("in == null");
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, (Type) typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Type)} with {@code Reader}, {@code Type}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code in == null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Type)"})
  public void testFromJsonWithReaderType_whenStringReaderWithInNull2()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("in == null");
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, (Type) typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Type)} with {@code Reader}, {@code Type}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code type must not be null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Type)"})
  public void testFromJsonWithReaderType_whenStringReaderWithTypeMustNotBeNull()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("type must not be null");
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, (Type) typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Type)} with {@code Reader}, {@code Type}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code type must not be null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Type)"})
  public void testFromJsonWithReaderType_whenStringReaderWithTypeMustNotBeNull2()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("type must not be null");
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, (Type) typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, Class)} with {@code String}, {@code Class}.
   *
   * <p>Method under test: {@link Gson#fromJson(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Class)"})
  public void testFromJsonWithStringClass() throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LEGACY_STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("Json", classOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, Class)} with {@code String}, {@code Class}.
   *
   * <p>Method under test: {@link Gson#fromJson(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Class)"})
  public void testFromJsonWithStringClass2() throws JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new IOException("in == null"));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("42", classOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(String, Class)} with {@code String}, {@code Class}.
   *
   * <p>Method under test: {@link Gson#fromJson(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Class)"})
  public void testFromJsonWithStringClass3() throws JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new EOFException("in == null"));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("42", classOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(String, Class)} with {@code String}, {@code Class}.
   *
   * <p>Method under test: {@link Gson#fromJson(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Class)"})
  public void testFromJsonWithStringClass4() throws JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new IllegalStateException("in == null"));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("42", classOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(String, Class)} with {@code String}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code 42}.
   *   <li>Then return doubleValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Class)"})
  public void testFromJsonWithStringClass_givenGson_when42_thenReturnDoubleValueIsFortyTwo()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertEquals(42.0d, ((Double) gson.fromJson("42", classOfT)).doubleValue(), 0.0);
  }

  /**
   * Test {@link Gson#fromJson(String, Class)} with {@code String}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When empty string.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Class)"})
  public void testFromJsonWithStringClass_givenGson_whenEmptyString_thenReturnNull()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertNull(gson.fromJson("", classOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, Class)} with {@code String}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code in == null}.
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Class)"})
  public void testFromJsonWithStringClass_givenGson_whenInNull_thenThrowJsonSyntaxException()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("in == null", classOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, Class)} with {@code String}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code Json}.
   *   <li>Then return {@code Json}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Class)"})
  public void testFromJsonWithStringClass_givenGson_whenJson_thenReturnJson()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertEquals("Json", gson.fromJson("Json", classOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, Class)} with {@code String}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Class)"})
  public void testFromJsonWithStringClass_givenGson_whenNull_thenReturnNull()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertNull(gson.fromJson((String) null, classOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, Class)} with {@code String}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@link ToNumberStrategy} {@link ToNumberStrategy#readNumber(JsonReader)} return
   *       {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Class)"})
  public void testFromJsonWithStringClass_givenToNumberStrategyReadNumberReturnNull()
      throws JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any())).thenReturn(null);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> classOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson("42", classOfT);

    // Assert
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
    assertNull(actualFromJsonResult);
  }

  /**
   * Test {@link Gson#fromJson(String, Class)} with {@code String}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@link ToNumberStrategy} {@link ToNumberStrategy#readNumber(JsonReader)} return
   *       valueOf one.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Class)"})
  public void testFromJsonWithStringClass_givenToNumberStrategyReadNumberReturnValueOfOne()
      throws JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenReturn(Integer.valueOf(1));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("42", classOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(String, Class)} with {@code String}, {@code Class}.
   *
   * <ul>
   *   <li>Then return toString is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Class)"})
  public void testFromJsonWithStringClass_thenReturnToStringIs42() throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> classOfT = LazilyParsedNumber.class;

    // Act
    Object actualFromJsonResult = gson.fromJson("42", classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof LazilyParsedNumber);
    assertEquals("42", actualFromJsonResult.toString());
  }

  /**
   * Test {@link Gson#fromJson(String, Class)} with {@code String}, {@code Class}.
   *
   * <ul>
   *   <li>Then return toString is {@code Json}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Class)"})
  public void testFromJsonWithStringClass_thenReturnToStringIsJson() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<LazilyParsedNumber> classOfT = LazilyParsedNumber.class;

    // Act
    Object actualFromJsonResult = gson.fromJson("Json", classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof LazilyParsedNumber);
    assertEquals("Json", actualFromJsonResult.toString());
  }

  /**
   * Test {@link Gson#fromJson(String, Class)} with {@code String}, {@code Class}.
   *
   * <ul>
   *   <li>When {@code in == null}.
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Class)"})
  public void testFromJsonWithStringClass_whenInNull_thenThrowJsonSyntaxException()
      throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("in == null", classOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, Class)} with {@code String}, {@code Class}.
   *
   * <ul>
   *   <li>When {@code Long}.
   *   <li>Then return longValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Class)"})
  public void testFromJsonWithStringClass_whenJavaLangLong_thenReturnLongValueIsFortyTwo()
      throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Long> classOfT = Long.class;

    // Act and Assert
    assertEquals(42L, ((Long) gson.fromJson("42", classOfT)).longValue());
  }

  /**
   * Test {@link Gson#fromJson(String, Class)} with {@code String}, {@code Class}.
   *
   * <ul>
   *   <li>When {@code Json}.
   *   <li>Then return {@code Json}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Class)"})
  public void testFromJsonWithStringClass_whenJson_thenReturnJson() throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertEquals("Json", gson.fromJson("Json", classOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, Class)} with {@code String}, {@code Class}.
   *
   * <ul>
   *   <li>When {@code type must not be null}.
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Class)"})
  public void testFromJsonWithStringClass_whenTypeMustNotBeNull_thenThrowJsonSyntaxException()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("type must not be null", classOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, Class)} with {@code String}, {@code Class}.
   *
   * <ul>
   *   <li>When {@code type must not be null}.
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Class)"})
  public void testFromJsonWithStringClass_whenTypeMustNotBeNull_thenThrowJsonSyntaxException2()
      throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("type must not be null", classOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, Type)} with {@code String}, {@code Type}.
   *
   * <p>Method under test: {@link Gson#fromJson(String, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Type)"})
  public void testFromJsonWithStringType() throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LEGACY_STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("Json", (Type) typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, Type)} with {@code String}, {@code Type}.
   *
   * <p>Method under test: {@link Gson#fromJson(String, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Type)"})
  public void testFromJsonWithStringType2() throws JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new IOException("in == null"));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("42", (Type) typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(String, Type)} with {@code String}, {@code Type}.
   *
   * <p>Method under test: {@link Gson#fromJson(String, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Type)"})
  public void testFromJsonWithStringType3() throws JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new EOFException("in == null"));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("42", (Type) typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(String, Type)} with {@code String}, {@code Type}.
   *
   * <p>Method under test: {@link Gson#fromJson(String, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Type)"})
  public void testFromJsonWithStringType4() throws JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new IllegalStateException("in == null"));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("42", (Type) typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(String, TypeToken)} with {@code String}, {@code TypeToken}.
   *
   * <p>Method under test: {@link Gson#fromJson(String, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, TypeToken)"})
  public void testFromJsonWithStringTypeToken() throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LEGACY_STRICT,
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
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("Json", typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, TypeToken)} with {@code String}, {@code TypeToken}.
   *
   * <p>Method under test: {@link Gson#fromJson(String, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, TypeToken)"})
  public void testFromJsonWithStringTypeToken2() throws JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new IOException("in == null"));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
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
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("42", typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(String, TypeToken)} with {@code String}, {@code TypeToken}.
   *
   * <p>Method under test: {@link Gson#fromJson(String, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, TypeToken)"})
  public void testFromJsonWithStringTypeToken3() throws JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new EOFException("in == null"));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
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
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("42", typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(String, TypeToken)} with {@code String}, {@code TypeToken}.
   *
   * <p>Method under test: {@link Gson#fromJson(String, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, TypeToken)"})
  public void testFromJsonWithStringTypeToken4() throws JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new IllegalStateException("in == null"));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
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
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("42", typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(String, TypeToken)} with {@code String}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code 42}.
   *   <li>Then return doubleValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, TypeToken)"})
  public void testFromJsonWithStringTypeToken_givenGson_when42_thenReturnDoubleValueIsFortyTwo()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertEquals(42.0d, ((Double) gson.fromJson("42", typeOfT)).doubleValue(), 0.0);
  }

  /**
   * Test {@link Gson#fromJson(String, TypeToken)} with {@code String}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When empty string.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, TypeToken)"})
  public void testFromJsonWithStringTypeToken_givenGson_whenEmptyString_thenReturnNull()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertNull(gson.fromJson("", typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, TypeToken)} with {@code String}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code Json}.
   *   <li>Then return {@code Json}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, TypeToken)"})
  public void testFromJsonWithStringTypeToken_givenGson_whenJson_thenReturnJson()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertEquals("Json", gson.fromJson("Json", typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, TypeToken)} with {@code String}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, TypeToken)"})
  public void testFromJsonWithStringTypeToken_givenGson_whenNull_thenReturnNull()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertNull(gson.fromJson((String) null, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, TypeToken)} with {@code String}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Given {@link ToNumberStrategy} {@link ToNumberStrategy#readNumber(JsonReader)} return
   *       {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, TypeToken)"})
  public void testFromJsonWithStringTypeToken_givenToNumberStrategyReadNumberReturnNull()
      throws JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any())).thenReturn(null);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
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
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson("42", typeOfT);

    // Assert
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
    assertNull(actualFromJsonResult);
  }

  /**
   * Test {@link Gson#fromJson(String, TypeToken)} with {@code String}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Given {@link ToNumberStrategy} {@link ToNumberStrategy#readNumber(JsonReader)} return
   *       valueOf one.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, TypeToken)"})
  public void testFromJsonWithStringTypeToken_givenToNumberStrategyReadNumberReturnValueOfOne()
      throws JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenReturn(Integer.valueOf(1));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
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
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("42", typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(String, TypeToken)} with {@code String}, {@code TypeToken}.
   *
   * <ul>
   *   <li>When {@code in == null}.
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, TypeToken)"})
  public void testFromJsonWithStringTypeToken_whenInNull_thenThrowJsonSyntaxException()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("in == null", typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, TypeToken)} with {@code String}, {@code TypeToken}.
   *
   * <ul>
   *   <li>When {@code in == null}.
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, TypeToken)"})
  public void testFromJsonWithStringTypeToken_whenInNull_thenThrowJsonSyntaxException2()
      throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
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
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("in == null", typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, TypeToken)} with {@code String}, {@code TypeToken}.
   *
   * <ul>
   *   <li>When {@code Json}.
   *   <li>Then return {@code Json}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, TypeToken)"})
  public void testFromJsonWithStringTypeToken_whenJson_thenReturnJson() throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
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
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertEquals("Json", gson.fromJson("Json", typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, TypeToken)} with {@code String}, {@code TypeToken}.
   *
   * <ul>
   *   <li>When {@code type must not be null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, TypeToken)"})
  public void testFromJsonWithStringTypeToken_whenTypeMustNotBeNull() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("type must not be null", typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, TypeToken)} with {@code String}, {@code TypeToken}.
   *
   * <ul>
   *   <li>When {@code type must not be null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, TypeToken)"})
  public void testFromJsonWithStringTypeToken_whenTypeMustNotBeNull2() throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
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
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("type must not be null", typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, Type)} with {@code String}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code 42}.
   *   <li>Then return doubleValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Type)"})
  public void testFromJsonWithStringType_givenGson_when42_thenReturnDoubleValueIsFortyTwo()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertEquals(42.0d, ((Double) gson.fromJson("42", (Type) typeOfT)).doubleValue(), 0.0);
  }

  /**
   * Test {@link Gson#fromJson(String, Type)} with {@code String}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When empty string.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Type)"})
  public void testFromJsonWithStringType_givenGson_whenEmptyString_thenReturnNull()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertNull(gson.fromJson("", (Type) typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, Type)} with {@code String}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code in == null}.
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Type)"})
  public void testFromJsonWithStringType_givenGson_whenInNull_thenThrowJsonSyntaxException()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("in == null", (Type) typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, Type)} with {@code String}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code Json}.
   *   <li>Then return {@code Json}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Type)"})
  public void testFromJsonWithStringType_givenGson_whenJson_thenReturnJson()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertEquals("Json", gson.fromJson("Json", (Type) typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, Type)} with {@code String}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Type)"})
  public void testFromJsonWithStringType_givenGson_whenNull_thenReturnNull()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertNull(gson.fromJson((String) null, (Type) typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, Type)} with {@code String}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link ToNumberStrategy} {@link ToNumberStrategy#readNumber(JsonReader)} return
   *       {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Type)"})
  public void testFromJsonWithStringType_givenToNumberStrategyReadNumberReturnNull()
      throws JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any())).thenReturn(null);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> typeOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson("42", (Type) typeOfT);

    // Assert
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
    assertNull(actualFromJsonResult);
  }

  /**
   * Test {@link Gson#fromJson(String, Type)} with {@code String}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link ToNumberStrategy} {@link ToNumberStrategy#readNumber(JsonReader)} return
   *       valueOf one.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Type)"})
  public void testFromJsonWithStringType_givenToNumberStrategyReadNumberReturnValueOfOne()
      throws JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenReturn(Integer.valueOf(1));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("42", (Type) typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(String, Type)} with {@code String}, {@code Type}.
   *
   * <ul>
   *   <li>Then return toString is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Type)"})
  public void testFromJsonWithStringType_thenReturnToStringIs42() throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> typeOfT = LazilyParsedNumber.class;

    // Act
    Object actualFromJsonResult = gson.fromJson("42", (Type) typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof LazilyParsedNumber);
    assertEquals("42", actualFromJsonResult.toString());
  }

  /**
   * Test {@link Gson#fromJson(String, Type)} with {@code String}, {@code Type}.
   *
   * <ul>
   *   <li>Then return toString is {@code Json}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Type)"})
  public void testFromJsonWithStringType_thenReturnToStringIsJson() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<LazilyParsedNumber> typeOfT = LazilyParsedNumber.class;

    // Act
    Object actualFromJsonResult = gson.fromJson("Json", (Type) typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof LazilyParsedNumber);
    assertEquals("Json", actualFromJsonResult.toString());
  }

  /**
   * Test {@link Gson#fromJson(String, Type)} with {@code String}, {@code Type}.
   *
   * <ul>
   *   <li>When {@code in == null}.
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Type)"})
  public void testFromJsonWithStringType_whenInNull_thenThrowJsonSyntaxException()
      throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("in == null", (Type) typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, Type)} with {@code String}, {@code Type}.
   *
   * <ul>
   *   <li>When {@code Long}.
   *   <li>Then return longValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Type)"})
  public void testFromJsonWithStringType_whenJavaLangLong_thenReturnLongValueIsFortyTwo()
      throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Long> typeOfT = Long.class;

    // Act and Assert
    assertEquals(42L, ((Long) gson.fromJson("42", (Type) typeOfT)).longValue());
  }

  /**
   * Test {@link Gson#fromJson(String, Type)} with {@code String}, {@code Type}.
   *
   * <ul>
   *   <li>When {@code Json}.
   *   <li>Then return {@code Json}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Type)"})
  public void testFromJsonWithStringType_whenJson_thenReturnJson() throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertEquals("Json", gson.fromJson("Json", (Type) typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, Type)} with {@code String}, {@code Type}.
   *
   * <ul>
   *   <li>When {@code type must not be null}.
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Type)"})
  public void testFromJsonWithStringType_whenTypeMustNotBeNull_thenThrowJsonSyntaxException()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class, () -> gson.fromJson("type must not be null", (Type) typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, Type)} with {@code String}, {@code Type}.
   *
   * <ul>
   *   <li>When {@code type must not be null}.
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Type)"})
  public void testFromJsonWithStringType_whenTypeMustNotBeNull_thenThrowJsonSyntaxException2()
      throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class, () -> gson.fromJson("type must not be null", (Type) typeOfT));
  }
}
