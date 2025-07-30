package com.google.gson.internal.bind;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonNull;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringWriter;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ArrayTypeAdapterDiffblueTest {
  /**
   * Test {@link ArrayTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ArrayTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ArrayTypeAdapter.read(JsonReader)"})
  public void testRead_whenJsonTreeReaderWithElementIsInstance_thenReturnNull() throws IOException {
    // Arrange
    Gson context = new Gson();
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> componentTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Class<Object> componentType = Object.class;
    ArrayTypeAdapter<Object> arrayTypeAdapter =
        new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType);
    JsonTreeReader in = new JsonTreeReader(JsonNull.INSTANCE);

    // Act and Assert
    assertNull(arrayTypeAdapter.read(in));
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link ArrayTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link IOException#IOException(String)} with {@code foo}.
   *   <li>Then throw {@link IOException}.
   * </ul>
   *
   * <p>Method under test: {@link ArrayTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ArrayTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_givenIOExceptionWithFoo_thenThrowIOException() throws IOException {
    // Arrange
    Gson context = new Gson();
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> componentTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Class<Object> componentType = Object.class;
    ArrayTypeAdapter<Object> arrayTypeAdapter =
        new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType);
    JsonWriter out = mock(JsonWriter.class);
    when(out.nullValue()).thenThrow(new IOException("foo"));

    // Act and Assert
    assertThrows(IOException.class, () -> arrayTypeAdapter.write(out, null));
    verify(out).nullValue();
  }

  /**
   * Test {@link ArrayTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>When {@link JsonWriter} {@link JsonWriter#nullValue()} return {@link
   *       JsonWriter#JsonWriter(Writer)} with out is {@link StringWriter#StringWriter()}.
   * </ul>
   *
   * <p>Method under test: {@link ArrayTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ArrayTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_whenJsonWriterNullValueReturnJsonWriterWithOutIsStringWriter()
      throws IOException {
    // Arrange
    Gson context = new Gson();
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> componentTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Class<Object> componentType = Object.class;
    ArrayTypeAdapter<Object> arrayTypeAdapter =
        new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType);
    JsonWriter out = mock(JsonWriter.class);
    when(out.nullValue()).thenReturn(new JsonWriter(new StringWriter()));

    // Act
    arrayTypeAdapter.write(out, null);

    // Assert
    verify(out).nullValue();
  }
}
