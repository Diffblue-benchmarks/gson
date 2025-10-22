package com.google.gson.internal.sql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
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
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.internal.bind.DefaultDateTypeAdapter;
import com.google.gson.internal.bind.DefaultDateTypeAdapterDiffblueTestFactory;
import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.internal.bind.TreeTypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.Date;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SqlTimestampTypeAdapterDiffblueTest {
  /**
   * Test {@link SqlTimestampTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SqlTimestampTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Timestamp SqlTimestampTypeAdapter.read(JsonReader)"})
  public void testRead_whenJsonTreeReaderWithElementIsInstance_thenReturnNull() throws IOException {
    // Arrange
    SqlTimestampTypeAdapter createSqlTimestampTypeAdapterResult =
        SqlTimestampTypeAdapterDiffblueTestFactory.createSqlTimestampTypeAdapter();
    JsonTreeReader in = new JsonTreeReader(JsonNull.INSTANCE);

    // Act
    Timestamp actualReadResult = createSqlTimestampTypeAdapterResult.read(in);

    // Assert
    assertNull(actualReadResult);
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)} with {@code JsonWriter},
   * {@code Timestamp}.
   *
   * <p>Method under test: {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SqlTimestampTypeAdapter.write(JsonWriter, Timestamp)"})
  public void testWriteWithJsonWriterTimestamp() throws IOException {
    // Arrange
    JsonSerializer<Date> serializer = mock(JsonSerializer.class);
    JsonPrimitive jsonPrimitive = new JsonPrimitive("\"Test string for JsonPrimitive method\"");
    when(serializer.serialize(
            Mockito.<Date>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(jsonPrimitive);
    JsonDeserializer<Date> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Date> type = Date.class;
    TypeToken<Date> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Date> dateTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    SqlTimestampTypeAdapter sqlTimestampTypeAdapter = new SqlTimestampTypeAdapter(dateTypeAdapter);
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    sqlTimestampTypeAdapter.write(out, new Timestamp(10L));

    // Assert
    verify(serializer)
        .serialize(isA(Date.class), isA(Type.class), isA(JsonSerializationContext.class));
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonPrimitive);
    assertEquals(jsonPrimitive, getResult);
  }

  /**
   * Test {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)} with {@code JsonWriter},
   * {@code Timestamp}.
   *
   * <p>Method under test: {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SqlTimestampTypeAdapter.write(JsonWriter, Timestamp)"})
  public void testWriteWithJsonWriterTimestamp2() throws IOException {
    // Arrange
    JsonSerializer<Date> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Date>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(JsonArrayDiffblueTestFactory.createJsonArrayWithElements());
    JsonDeserializer<Date> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Date> type = Date.class;
    TypeToken<Date> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Date> dateTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    SqlTimestampTypeAdapter sqlTimestampTypeAdapter = new SqlTimestampTypeAdapter(dateTypeAdapter);
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    sqlTimestampTypeAdapter.write(out, new Timestamp(10L));

    // Assert
    verify(serializer)
        .serialize(isA(Date.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)} with {@code JsonWriter},
   * {@code Timestamp}.
   *
   * <p>Method under test: {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SqlTimestampTypeAdapter.write(JsonWriter, Timestamp)"})
  public void testWriteWithJsonWriterTimestamp3() throws IOException {
    // Arrange
    JsonSerializer<Date> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Date>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonPrimitive("\"Test string for JsonPrimitive method\""));
    JsonDeserializer<Date> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Date> type = Date.class;
    TypeToken<Date> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Date> dateTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    SqlTimestampTypeAdapter sqlTimestampTypeAdapter = new SqlTimestampTypeAdapter(dateTypeAdapter);
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    sqlTimestampTypeAdapter.write(out, new Timestamp(10L));

    // Assert
    verify(serializer)
        .serialize(isA(Date.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)} with {@code JsonWriter},
   * {@code Timestamp}.
   *
   * <p>Method under test: {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SqlTimestampTypeAdapter.write(JsonWriter, Timestamp)"})
  public void testWriteWithJsonWriterTimestamp4() throws IOException {
    // Arrange
    JsonSerializer<Date> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Date>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonPrimitive(true));
    JsonDeserializer<Date> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Date> type = Date.class;
    TypeToken<Date> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Date> dateTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    SqlTimestampTypeAdapter sqlTimestampTypeAdapter = new SqlTimestampTypeAdapter(dateTypeAdapter);
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    sqlTimestampTypeAdapter.write(out, new Timestamp(10L));

    // Assert
    verify(serializer)
        .serialize(isA(Date.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)} with {@code JsonWriter},
   * {@code Timestamp}.
   *
   * <p>Method under test: {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SqlTimestampTypeAdapter.write(JsonWriter, Timestamp)"})
  public void testWriteWithJsonWriterTimestamp5() throws IOException {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements());

    JsonSerializer<Date> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Date>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(createJsonArrayWithElementsResult);
    JsonDeserializer<Date> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Date> type = Date.class;
    TypeToken<Date> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Date> dateTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    SqlTimestampTypeAdapter sqlTimestampTypeAdapter = new SqlTimestampTypeAdapter(dateTypeAdapter);
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    sqlTimestampTypeAdapter.write(out, new Timestamp(10L));

    // Assert
    verify(serializer)
        .serialize(isA(Date.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)} with {@code JsonWriter},
   * {@code Timestamp}.
   *
   * <p>Method under test: {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SqlTimestampTypeAdapter.write(JsonWriter, Timestamp)"})
  public void testWriteWithJsonWriterTimestamp6() throws IOException {
    // Arrange
    JsonSerializer<Date> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Date>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonPrimitive(false));
    JsonDeserializer<Date> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Date> type = Date.class;
    TypeToken<Date> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Date> dateTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    SqlTimestampTypeAdapter sqlTimestampTypeAdapter = new SqlTimestampTypeAdapter(dateTypeAdapter);
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    sqlTimestampTypeAdapter.write(out, new Timestamp(10L));

    // Assert
    verify(serializer)
        .serialize(isA(Date.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)} with {@code JsonWriter},
   * {@code Timestamp}.
   *
   * <p>Method under test: {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SqlTimestampTypeAdapter.write(JsonWriter, Timestamp)"})
  public void testWriteWithJsonWriterTimestamp7() throws IOException {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add(
        "\"employeeDetails\"", JsonArrayDiffblueTestFactory.createJsonArrayWithElements());

    JsonSerializer<Date> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Date>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(jsonObject);
    JsonDeserializer<Date> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Date> type = Date.class;
    TypeToken<Date> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Date> dateTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    SqlTimestampTypeAdapter sqlTimestampTypeAdapter = new SqlTimestampTypeAdapter(dateTypeAdapter);
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    sqlTimestampTypeAdapter.write(out, new Timestamp(10L));

    // Assert
    verify(serializer)
        .serialize(isA(Date.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)} with {@code JsonWriter},
   * {@code Timestamp}.
   *
   * <p>Method under test: {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SqlTimestampTypeAdapter.write(JsonWriter, Timestamp)"})
  public void testWriteWithJsonWriterTimestamp8() throws IOException {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("name == null", JsonArrayDiffblueTestFactory.createJsonArrayWithElements());
    jsonObject.add(
        "\"employeeDetails\"", JsonArrayDiffblueTestFactory.createJsonArrayWithElements());

    JsonSerializer<Date> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Date>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(jsonObject);
    JsonDeserializer<Date> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Date> type = Date.class;
    TypeToken<Date> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Date> dateTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    SqlTimestampTypeAdapter sqlTimestampTypeAdapter = new SqlTimestampTypeAdapter(dateTypeAdapter);
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    sqlTimestampTypeAdapter.write(out, new Timestamp(10L));

    // Assert
    verify(serializer)
        .serialize(isA(Date.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)} with {@code JsonWriter},
   * {@code Timestamp}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add {@link JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SqlTimestampTypeAdapter.write(JsonWriter, Timestamp)"})
  public void testWriteWithJsonWriterTimestamp_givenCreateJsonArrayWithElementsAddInstance()
      throws IOException {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(JsonNull.INSTANCE);

    JsonSerializer<Date> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Date>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(createJsonArrayWithElementsResult);
    JsonDeserializer<Date> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Date> type = Date.class;
    TypeToken<Date> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Date> dateTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    SqlTimestampTypeAdapter sqlTimestampTypeAdapter = new SqlTimestampTypeAdapter(dateTypeAdapter);
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    sqlTimestampTypeAdapter.write(out, new Timestamp(10L));

    // Assert
    verify(serializer)
        .serialize(isA(Date.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)} with {@code JsonWriter},
   * {@code Timestamp}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add {@link JsonObject} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SqlTimestampTypeAdapter.write(JsonWriter, Timestamp)"})
  public void testWriteWithJsonWriterTimestamp_givenCreateJsonArrayWithElementsAddJsonObject()
      throws IOException {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(new JsonObject());

    JsonSerializer<Date> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Date>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(createJsonArrayWithElementsResult);
    JsonDeserializer<Date> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Date> type = Date.class;
    TypeToken<Date> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Date> dateTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    SqlTimestampTypeAdapter sqlTimestampTypeAdapter = new SqlTimestampTypeAdapter(dateTypeAdapter);
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    sqlTimestampTypeAdapter.write(out, new Timestamp(10L));

    // Assert
    verify(serializer)
        .serialize(isA(Date.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)} with {@code JsonWriter},
   * {@code Timestamp}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SqlTimestampTypeAdapter.write(JsonWriter, Timestamp)"})
  public void testWriteWithJsonWriterTimestamp_givenCreateJsonArrayWithElementsAddTrue()
      throws IOException {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(true);

    JsonSerializer<Date> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Date>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(createJsonArrayWithElementsResult);
    JsonDeserializer<Date> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Date> type = Date.class;
    TypeToken<Date> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Date> dateTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    SqlTimestampTypeAdapter sqlTimestampTypeAdapter = new SqlTimestampTypeAdapter(dateTypeAdapter);
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    sqlTimestampTypeAdapter.write(out, new Timestamp(10L));

    // Assert
    verify(serializer)
        .serialize(isA(Date.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)} with {@code JsonWriter},
   * {@code Timestamp}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithElements add valueOf one.
   * </ul>
   *
   * <p>Method under test: {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SqlTimestampTypeAdapter.write(JsonWriter, Timestamp)"})
  public void testWriteWithJsonWriterTimestamp_givenCreateJsonArrayWithElementsAddValueOfOne()
      throws IOException {
    // Arrange
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    createJsonArrayWithElementsResult.add(Integer.valueOf(1));

    JsonSerializer<Date> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Date>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(createJsonArrayWithElementsResult);
    JsonDeserializer<Date> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Date> type = Date.class;
    TypeToken<Date> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Date> dateTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    SqlTimestampTypeAdapter sqlTimestampTypeAdapter = new SqlTimestampTypeAdapter(dateTypeAdapter);
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    sqlTimestampTypeAdapter.write(out, new Timestamp(10L));

    // Assert
    verify(serializer)
        .serialize(isA(Date.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)} with {@code JsonWriter},
   * {@code Timestamp}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code "employeeDetails"} and {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SqlTimestampTypeAdapter.write(JsonWriter, Timestamp)"})
  public void testWriteWithJsonWriterTimestamp_givenJsonObjectAddEmployeeDetailsAndInstance()
      throws IOException {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("\"employeeDetails\"", JsonNull.INSTANCE);

    JsonSerializer<Date> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Date>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(jsonObject);
    JsonDeserializer<Date> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Date> type = Date.class;
    TypeToken<Date> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Date> dateTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    SqlTimestampTypeAdapter sqlTimestampTypeAdapter = new SqlTimestampTypeAdapter(dateTypeAdapter);
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    sqlTimestampTypeAdapter.write(out, new Timestamp(10L));

    // Assert
    verify(serializer)
        .serialize(isA(Date.class), isA(Type.class), isA(JsonSerializationContext.class));
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonObject);
    assertEquals(jsonObject, getResult);
  }

  /**
   * Test {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)} with {@code JsonWriter},
   * {@code Timestamp}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code "employeeDetails"} and {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SqlTimestampTypeAdapter.write(JsonWriter, Timestamp)"})
  public void testWriteWithJsonWriterTimestamp_givenJsonObjectAddEmployeeDetailsAndInstance2()
      throws IOException {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("\"employeeDetails\"", JsonNull.INSTANCE);

    JsonSerializer<Date> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Date>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(jsonObject);
    JsonDeserializer<Date> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Date> type = Date.class;
    TypeToken<Date> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Date> dateTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    SqlTimestampTypeAdapter sqlTimestampTypeAdapter = new SqlTimestampTypeAdapter(dateTypeAdapter);
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    sqlTimestampTypeAdapter.write(out, new Timestamp(10L));

    // Assert
    verify(serializer)
        .serialize(isA(Date.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)} with {@code JsonWriter},
   * {@code Timestamp}.
   *
   * <ul>
   *   <li>Given {@link JsonSerializer} {@link JsonSerializer#serialize(Object, Type,
   *       JsonSerializationContext)} return {@link JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SqlTimestampTypeAdapter.write(JsonWriter, Timestamp)"})
  public void testWriteWithJsonWriterTimestamp_givenJsonSerializerSerializeReturnInstance()
      throws IOException {
    // Arrange
    JsonSerializer<Date> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Date>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(JsonNull.INSTANCE);
    JsonDeserializer<Date> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Date> type = Date.class;
    TypeToken<Date> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Date> dateTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    SqlTimestampTypeAdapter sqlTimestampTypeAdapter = new SqlTimestampTypeAdapter(dateTypeAdapter);
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    sqlTimestampTypeAdapter.write(out, new Timestamp(10L));

    // Assert
    verify(serializer)
        .serialize(isA(Date.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)} with {@code JsonWriter},
   * {@code Timestamp}.
   *
   * <ul>
   *   <li>Given {@link JsonSerializer} {@link JsonSerializer#serialize(Object, Type,
   *       JsonSerializationContext)} return {@link JsonObject} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SqlTimestampTypeAdapter.write(JsonWriter, Timestamp)"})
  public void testWriteWithJsonWriterTimestamp_givenJsonSerializerSerializeReturnJsonObject()
      throws IOException {
    // Arrange
    JsonSerializer<Date> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Date>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonObject());
    JsonDeserializer<Date> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Date> type = Date.class;
    TypeToken<Date> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Date> dateTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    SqlTimestampTypeAdapter sqlTimestampTypeAdapter = new SqlTimestampTypeAdapter(dateTypeAdapter);
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    sqlTimestampTypeAdapter.write(out, new Timestamp(10L));

    // Assert
    verify(serializer)
        .serialize(isA(Date.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)} with {@code JsonWriter},
   * {@code Timestamp}.
   *
   * <ul>
   *   <li>Then {@link JsonTreeWriter} (default constructor) AsNumber {@link LazilyParsedNumber}.
   * </ul>
   *
   * <p>Method under test: {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SqlTimestampTypeAdapter.write(JsonWriter, Timestamp)"})
  public void testWriteWithJsonWriterTimestamp_thenJsonTreeWriterAsNumberLazilyParsedNumber()
      throws IOException {
    // Arrange
    DefaultDateTypeAdapter<Date> dateTypeAdapter =
        DefaultDateTypeAdapterDiffblueTestFactory.createSimpleDateAdapter();
    SqlTimestampTypeAdapter sqlTimestampTypeAdapter = new SqlTimestampTypeAdapter(dateTypeAdapter);
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    sqlTimestampTypeAdapter.write(out, new Timestamp(10L));

    // Assert
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonPrimitive);
    assertTrue(getResult.getAsNumber() instanceof LazilyParsedNumber);
    assertEquals('1', getResult.getAsCharacter());
    assertFalse(getResult.getAsBoolean());
    assertFalse(((JsonPrimitive) getResult).isBoolean());
    assertTrue(((JsonPrimitive) getResult).isString());
  }

  /**
   * Test {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)} with {@code JsonWriter},
   * {@code Timestamp}.
   *
   * <ul>
   *   <li>Then {@link JsonTreeWriter} (default constructor) is createJsonArrayWithElements.
   * </ul>
   *
   * <p>Method under test: {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SqlTimestampTypeAdapter.write(JsonWriter, Timestamp)"})
  public void testWriteWithJsonWriterTimestamp_thenJsonTreeWriterIsCreateJsonArrayWithElements()
      throws IOException {
    // Arrange
    JsonSerializer<Date> serializer = mock(JsonSerializer.class);
    JsonArray createJsonArrayWithElementsResult =
        JsonArrayDiffblueTestFactory.createJsonArrayWithElements();
    when(serializer.serialize(
            Mockito.<Date>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(createJsonArrayWithElementsResult);
    JsonDeserializer<Date> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Date> type = Date.class;
    TypeToken<Date> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Date> dateTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    SqlTimestampTypeAdapter sqlTimestampTypeAdapter = new SqlTimestampTypeAdapter(dateTypeAdapter);
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    sqlTimestampTypeAdapter.write(out, new Timestamp(10L));

    // Assert
    verify(serializer)
        .serialize(isA(Date.class), isA(Type.class), isA(JsonSerializationContext.class));
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonArray);
    assertEquals(createJsonArrayWithElementsResult, getResult);
  }

  /**
   * Test {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)} with {@code JsonWriter},
   * {@code Timestamp}.
   *
   * <ul>
   *   <li>Then {@link JsonTreeWriter} (default constructor) is {@link
   *       JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SqlTimestampTypeAdapter.write(JsonWriter, Timestamp)"})
  public void testWriteWithJsonWriterTimestamp_thenJsonTreeWriterIsJsonPrimitiveWithBoolIsTrue()
      throws IOException {
    // Arrange
    JsonSerializer<Date> serializer = mock(JsonSerializer.class);
    JsonPrimitive jsonPrimitive = new JsonPrimitive(true);
    when(serializer.serialize(
            Mockito.<Date>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(jsonPrimitive);
    JsonDeserializer<Date> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Date> type = Date.class;
    TypeToken<Date> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Date> dateTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    SqlTimestampTypeAdapter sqlTimestampTypeAdapter = new SqlTimestampTypeAdapter(dateTypeAdapter);
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    sqlTimestampTypeAdapter.write(out, new Timestamp(10L));

    // Assert
    verify(serializer)
        .serialize(isA(Date.class), isA(Type.class), isA(JsonSerializationContext.class));
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonPrimitive);
    assertEquals(jsonPrimitive, getResult);
  }

  /**
   * Test {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)} with {@code JsonWriter},
   * {@code Timestamp}.
   *
   * <ul>
   *   <li>Then {@link JsonTreeWriter} (default constructor) {@link JsonNull}.
   * </ul>
   *
   * <p>Method under test: {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SqlTimestampTypeAdapter.write(JsonWriter, Timestamp)"})
  public void testWriteWithJsonWriterTimestamp_thenJsonTreeWriterJsonNull() throws IOException {
    // Arrange
    DefaultDateTypeAdapter<Date> dateTypeAdapter =
        DefaultDateTypeAdapterDiffblueTestFactory.createSimpleDateAdapter();
    SqlTimestampTypeAdapter sqlTimestampTypeAdapter = new SqlTimestampTypeAdapter(dateTypeAdapter);
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    sqlTimestampTypeAdapter.write(out, null);

    // Assert that nothing has changed
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonNull);
    assertFalse(getResult.isJsonArray());
    assertFalse(getResult.isJsonObject());
  }

  /**
   * Test {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)} with {@code JsonWriter},
   * {@code Timestamp}.
   *
   * <ul>
   *   <li>Then {@link JsonTreeWriter} (default constructor) {@link JsonNull}.
   * </ul>
   *
   * <p>Method under test: {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SqlTimestampTypeAdapter.write(JsonWriter, Timestamp)"})
  public void testWriteWithJsonWriterTimestamp_thenJsonTreeWriterJsonNull2() throws IOException {
    // Arrange
    JsonSerializer<Date> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Date>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(JsonNull.INSTANCE);
    JsonDeserializer<Date> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Date> type = Date.class;
    TypeToken<Date> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Date> dateTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    SqlTimestampTypeAdapter sqlTimestampTypeAdapter = new SqlTimestampTypeAdapter(dateTypeAdapter);
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    sqlTimestampTypeAdapter.write(out, new Timestamp(10L));

    // Assert that nothing has changed
    verify(serializer)
        .serialize(isA(Date.class), isA(Type.class), isA(JsonSerializationContext.class));
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonNull);
    assertFalse(getResult.isJsonArray());
    assertFalse(getResult.isJsonObject());
  }

  /**
   * Test {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)} with {@code JsonWriter},
   * {@code Timestamp}.
   *
   * <ul>
   *   <li>Then {@link JsonTreeWriter} (default constructor) {@link JsonObject}.
   * </ul>
   *
   * <p>Method under test: {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SqlTimestampTypeAdapter.write(JsonWriter, Timestamp)"})
  public void testWriteWithJsonWriterTimestamp_thenJsonTreeWriterJsonObject() throws IOException {
    // Arrange
    JsonSerializer<Date> serializer = mock(JsonSerializer.class);
    JsonObject jsonObject = new JsonObject();
    when(serializer.serialize(
            Mockito.<Date>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(jsonObject);
    JsonDeserializer<Date> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Date> type = Date.class;
    TypeToken<Date> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Date> dateTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    SqlTimestampTypeAdapter sqlTimestampTypeAdapter = new SqlTimestampTypeAdapter(dateTypeAdapter);
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    sqlTimestampTypeAdapter.write(out, new Timestamp(10L));

    // Assert
    verify(serializer)
        .serialize(isA(Date.class), isA(Type.class), isA(JsonSerializationContext.class));
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonObject);
    assertEquals(jsonObject, getResult);
  }

  /**
   * Test {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)} with {@code JsonWriter},
   * {@code Timestamp}.
   *
   * <ul>
   *   <li>Then {@link JsonTreeWriter} (default constructor) {@link JsonObject}.
   * </ul>
   *
   * <p>Method under test: {@link SqlTimestampTypeAdapter#write(JsonWriter, Timestamp)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SqlTimestampTypeAdapter.write(JsonWriter, Timestamp)"})
  public void testWriteWithJsonWriterTimestamp_thenJsonTreeWriterJsonObject2() throws IOException {
    // Arrange
    JsonObject jsonObject = new JsonObject();
    jsonObject.add(
        "\"employeeDetails\"", JsonArrayDiffblueTestFactory.createJsonArrayWithElements());

    JsonSerializer<Date> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Date>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(jsonObject);
    JsonDeserializer<Date> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Date> type = Date.class;
    TypeToken<Date> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Date> dateTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    SqlTimestampTypeAdapter sqlTimestampTypeAdapter = new SqlTimestampTypeAdapter(dateTypeAdapter);
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    sqlTimestampTypeAdapter.write(out, new Timestamp(10L));

    // Assert
    verify(serializer)
        .serialize(isA(Date.class), isA(Type.class), isA(JsonSerializationContext.class));
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonObject);
    assertEquals(jsonObject, getResult);
  }
}
