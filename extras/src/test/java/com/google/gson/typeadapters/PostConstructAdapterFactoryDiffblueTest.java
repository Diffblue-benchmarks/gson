package com.google.gson.typeadapters;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonNull;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.bind.ArrayTypeAdapter;
import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.internal.bind.TreeTypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringWriter;
import org.junit.Test;
import org.mockito.Mockito;

public class PostConstructAdapterFactoryDiffblueTest {
  /** Method under test: {@link PostConstructAdapterFactory#create(Gson, TypeToken)} */
  @Test
  public void testCreate() {
    // Arrange
    PostConstructAdapterFactory postConstructAdapterFactory = new PostConstructAdapterFactory();
    Gson gson = new Gson();
    TypeToken<Object> type = mock(TypeToken.class);
    Class<Object> forNameResult = Object.class;
    Mockito.<Class<? super Object>>when(type.getRawType()).thenReturn(forNameResult);

    // Act
    TypeAdapter<Object> actualCreateResult = postConstructAdapterFactory.create(gson, type);

    // Assert
    verify(type).getRawType();
    assertNull(actualCreateResult);
  }

  /**
   * Method under test: {@link PostConstructAdapterFactory.PostConstructAdapter#read(JsonReader)}
   */
  @Test
  public void testPostConstructAdapterRead() throws IOException {
    // Arrange
    new RuntimeException(Boolean.FALSE.toString());
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    PostConstructAdapterFactory.PostConstructAdapter<Object> postConstructAdapter =
        new PostConstructAdapterFactory.PostConstructAdapter<>(
            new TreeTypeAdapter<>(
                serializer,
                deserializer,
                new Gson(),
                mock(TypeToken.class),
                mock(TypeAdapterFactory.class),
                true),
            null);
    JsonTreeReader in = new JsonTreeReader(new JsonNull());

    // Act and Assert
    assertNull(postConstructAdapter.read(in));
    assertFalse(in.hasNext());
  }

  /**
   * Method under test: {@link PostConstructAdapterFactory.PostConstructAdapter#read(JsonReader)}
   */
  @Test
  public void testPostConstructAdapterRead2() throws IOException {
    // Arrange
    new RuntimeException(Boolean.FALSE.toString());
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
    PostConstructAdapterFactory.PostConstructAdapter<Object> postConstructAdapter =
        new PostConstructAdapterFactory.PostConstructAdapter<>(
            new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType), null);
    JsonTreeReader in = new JsonTreeReader(new JsonNull());

    // Act and Assert
    assertNull(postConstructAdapter.read(in));
    assertFalse(in.hasNext());
  }

  /**
   * Method under test: {@link PostConstructAdapterFactory.PostConstructAdapter#write(JsonWriter,
   * Object)}
   */
  @Test
  public void testPostConstructAdapterWrite() throws IOException {
    // Arrange
    TypeAdapter<Object> delegate = mock(TypeAdapter.class);
    doNothing().when(delegate).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());
    PostConstructAdapterFactory.PostConstructAdapter<Object> postConstructAdapter =
        new PostConstructAdapterFactory.PostConstructAdapter<>(delegate, null);

    // Act
    postConstructAdapter.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(delegate).write(isA(JsonWriter.class), isA(Object.class));
  }
}
