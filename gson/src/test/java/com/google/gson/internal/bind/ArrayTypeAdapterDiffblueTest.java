package com.google.gson.internal.bind;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

public class ArrayTypeAdapterDiffblueTest {
  /** Method under test: {@link ArrayTypeAdapter#read(JsonReader)} */
  @Test
  public void testRead() throws IOException {
    // Arrange
    Gson context = new Gson();
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    TreeTypeAdapter<Object> componentTypeAdapter =
        new TreeTypeAdapter<>(
            serializer,
            deserializer,
            new Gson(),
            mock(TypeToken.class),
            mock(TypeAdapterFactory.class));

    Class<Object> componentType = Object.class;
    ArrayTypeAdapter<Object> arrayTypeAdapter =
        new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType);
    JsonTreeReader in = new JsonTreeReader(JsonNull.INSTANCE);

    // Act and Assert
    assertNull(arrayTypeAdapter.read(in));
    assertFalse(in.hasNext());
  }

  /** Method under test: {@link ArrayTypeAdapter#write(JsonWriter, Object)} */
  @Test
  public void testWrite() throws IOException {
    // Arrange
    Gson context = new Gson();
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    TreeTypeAdapter<Object> componentTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, new Gson(), null, mock(TypeAdapterFactory.class));

    Class<Object> componentType = Object.class;
    ArrayTypeAdapter<Object> arrayTypeAdapter =
        new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType);
    JsonWriter out = mock(JsonWriter.class);
    when(out.nullValue()).thenReturn(new JsonWriter(new StringWriter()));

    // Act
    arrayTypeAdapter.write(out, null);

    // Assert that nothing has changed
    verify(out).nullValue();
  }

  /** Method under test: {@link ArrayTypeAdapter#write(JsonWriter, Object)} */
  @Test
  public void testWrite2() throws IOException {
    // Arrange
    Gson context = new Gson();
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    TreeTypeAdapter<Object> componentTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, new Gson(), null, mock(TypeAdapterFactory.class));

    Class<Object> componentType = Object.class;
    ArrayTypeAdapter<Object> arrayTypeAdapter =
        new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType);
    JsonWriter out = mock(JsonWriter.class);
    when(out.nullValue()).thenThrow(new IOException("foo"));

    // Act and Assert
    assertThrows(IOException.class, () -> arrayTypeAdapter.write(out, null));
    verify(out).nullValue();
  }
}
