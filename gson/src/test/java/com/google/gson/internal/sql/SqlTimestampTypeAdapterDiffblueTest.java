package com.google.gson.internal.sql;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
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
import com.google.gson.internal.bind.DefaultDateTypeAdapter;
import com.google.gson.internal.bind.TreeTypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SqlTimestampTypeAdapterDiffblueTest {
  /**
   * Test {@link SqlTimestampTypeAdapter#read(JsonReader)}.
   *
   * <p>Method under test: {@link SqlTimestampTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Timestamp SqlTimestampTypeAdapter.read(JsonReader)"})
  public void testRead() throws IOException {
    // Arrange
    DefaultDateTypeAdapter<Date> dateTypeAdapter = mock(DefaultDateTypeAdapter.class);
    when(dateTypeAdapter.read(Mockito.<JsonReader>any()))
        .thenReturn(
            Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    SqlTimestampTypeAdapter sqlTimestampTypeAdapter = new SqlTimestampTypeAdapter(dateTypeAdapter);

    // Act
    sqlTimestampTypeAdapter.read(new JsonReader(new StringReader("foo")));

    // Assert
    verify(dateTypeAdapter).read(isA(JsonReader.class));
  }

  /**
   * Test {@link SqlTimestampTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@link DefaultDateTypeAdapter} {@link DefaultDateTypeAdapter#read(JsonReader)}
   *       return {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SqlTimestampTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Timestamp SqlTimestampTypeAdapter.read(JsonReader)"})
  public void testRead_givenDefaultDateTypeAdapterReadReturnNull_thenReturnNull()
      throws IOException {
    // Arrange
    DefaultDateTypeAdapter<Date> dateTypeAdapter = mock(DefaultDateTypeAdapter.class);
    when(dateTypeAdapter.read(Mockito.<JsonReader>any())).thenReturn(null);
    SqlTimestampTypeAdapter sqlTimestampTypeAdapter = new SqlTimestampTypeAdapter(dateTypeAdapter);

    // Act
    Timestamp actualReadResult =
        sqlTimestampTypeAdapter.read(new JsonReader(new StringReader("foo")));

    // Assert
    verify(dateTypeAdapter).read(isA(JsonReader.class));
    assertNull(actualReadResult);
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
    sqlTimestampTypeAdapter.write(out, null);

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
    assertEquals("null", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
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
        .thenReturn(new JsonArray(3));
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
    assertEquals("[]", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
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
    assertEquals("{}", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
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
    JsonSerializer<Date> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Date>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonPrimitive("null"));
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
    assertEquals("\"null\"", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
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
    assertEquals(Boolean.TRUE.toString(), out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
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
    JsonSerializer<Date> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Date>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonPrimitive('\u0001'));
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
    assertEquals("\"\\u0001\"", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
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
    DefaultDateTypeAdapter<Date> dateTypeAdapter = mock(DefaultDateTypeAdapter.class);
    doNothing().when(dateTypeAdapter).write(Mockito.<JsonWriter>any(), Mockito.<Date>any());
    SqlTimestampTypeAdapter sqlTimestampTypeAdapter = new SqlTimestampTypeAdapter(dateTypeAdapter);
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    sqlTimestampTypeAdapter.write(out, new Timestamp(10L));

    // Assert that nothing has changed
    verify(dateTypeAdapter).write(isA(JsonWriter.class), isA(Date.class));
    assertEquals("", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }
}
