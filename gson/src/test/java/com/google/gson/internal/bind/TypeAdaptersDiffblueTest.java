package com.google.gson.internal.bind;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import org.mockito.Mockito;

public class TypeAdaptersDiffblueTest {
  /** Method under test: {@link TypeAdapters#newFactory(TypeToken, TypeAdapter)} */
  @Test
  public void testNewFactory() {
    // Arrange
    Gson context = new Gson();
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    TreeTypeAdapter<Object> componentTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, new Gson(), null, mock(TypeAdapterFactory.class));

    Class<Object> componentType = Object.class;

    // Act
    TypeAdapterFactory actualNewFactoryResult =
        TypeAdapters.newFactory(
            (TypeToken<Object>) null,
            new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType));

    // Assert
    assertNull(actualNewFactoryResult.<Object>create(new Gson(), mock(TypeToken.class)));
  }

  /** Method under test: {@link TypeAdapters#newFactory(Class, TypeAdapter)} */
  @Test
  public void testNewFactory2() {
    // Arrange
    Class<Object> type = Object.class;
    Gson context = new Gson();
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    TreeTypeAdapter<Object> componentTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, new Gson(), null, mock(TypeAdapterFactory.class));

    Class<Object> componentType = Object.class;
    ArrayTypeAdapter<Object> typeAdapter =
        new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType);

    // Act
    TypeAdapterFactory actualNewFactoryResult = TypeAdapters.newFactory(type, typeAdapter);
    Gson gson = new Gson();
    TypeToken<Object> typeToken = mock(TypeToken.class);
    Class<Object> forNameResult = Object.class;
    Mockito.<Class<? super Object>>when(typeToken.getRawType()).thenReturn(forNameResult);
    TypeAdapter<Object> actualCreateResult = actualNewFactoryResult.create(gson, typeToken);

    // Assert
    verify(typeToken).getRawType();
    assertSame(typeAdapter, actualCreateResult);
  }

  /** Method under test: {@link TypeAdapters#newFactory(Class, TypeAdapter)} */
  @Test
  public void testNewFactory3() {
    // Arrange
    Class<Object> type = Object.class;
    Gson context = new Gson();
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    TreeTypeAdapter<Object> componentTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, new Gson(), null, mock(TypeAdapterFactory.class));

    Class<Object> componentType = Object.class;

    // Act
    TypeAdapterFactory actualNewFactoryResult =
        TypeAdapters.newFactory(
            type, new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType));
    Gson gson = new Gson();
    TypeToken<Object> typeToken = mock(TypeToken.class);
    Mockito.<Class<? super Object>>when(typeToken.getRawType())
        .thenThrow(new UnsupportedOperationException("foo"));

    // Assert
    assertThrows(
        UnsupportedOperationException.class, () -> actualNewFactoryResult.create(gson, typeToken));
    verify(typeToken).getRawType();
  }

  /** Method under test: {@link TypeAdapters#newFactory(Class, Class, TypeAdapter)} */
  @Test
  public void testNewFactory4() {
    // Arrange
    Class<Object> unboxed = Object.class;
    Class<Object> boxed = Object.class;
    Gson context = new Gson();
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    TreeTypeAdapter<Object> componentTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, new Gson(), null, mock(TypeAdapterFactory.class));

    Class<Object> componentType = Object.class;
    ArrayTypeAdapter<Object> typeAdapter =
        new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType);

    // Act
    TypeAdapterFactory actualNewFactoryResult =
        TypeAdapters.newFactory(unboxed, boxed, typeAdapter);
    Gson gson = new Gson();
    TypeToken<Object> typeToken = mock(TypeToken.class);
    Class<Object> forNameResult = Object.class;
    Mockito.<Class<? super Object>>when(typeToken.getRawType()).thenReturn(forNameResult);
    TypeAdapter<Object> actualCreateResult = actualNewFactoryResult.create(gson, typeToken);

    // Assert
    verify(typeToken).getRawType();
    assertSame(typeAdapter, actualCreateResult);
  }

  /** Method under test: {@link TypeAdapters#newFactory(Class, Class, TypeAdapter)} */
  @Test
  public void testNewFactory5() {
    // Arrange
    Class<Object> unboxed = Object.class;
    Class<Object> boxed = Object.class;
    Gson context = new Gson();
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    TreeTypeAdapter<Object> componentTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, new Gson(), null, mock(TypeAdapterFactory.class));

    Class<Object> componentType = Object.class;

    // Act
    TypeAdapterFactory actualNewFactoryResult =
        TypeAdapters.newFactory(
            unboxed, boxed, new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType));
    Gson gson = new Gson();
    TypeToken<Object> typeToken = mock(TypeToken.class);
    Mockito.<Class<? super Object>>when(typeToken.getRawType())
        .thenThrow(new UnsupportedOperationException("foo"));

    // Assert
    assertThrows(
        UnsupportedOperationException.class, () -> actualNewFactoryResult.create(gson, typeToken));
    verify(typeToken).getRawType();
  }

  /**
   * Method under test: {@link TypeAdapters#newFactoryForMultipleTypes(Class, Class, TypeAdapter)}
   */
  @Test
  public void testNewFactoryForMultipleTypes() {
    // Arrange
    Class<Object> base = Object.class;
    Class<Object> sub = Object.class;
    Gson context = new Gson();
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    TreeTypeAdapter<Object> componentTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, new Gson(), null, mock(TypeAdapterFactory.class));

    Class<Object> componentType = Object.class;
    ArrayTypeAdapter<Object> typeAdapter =
        new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType);

    // Act
    TypeAdapterFactory actualNewFactoryForMultipleTypesResult =
        TypeAdapters.newFactoryForMultipleTypes(base, sub, typeAdapter);
    Gson gson = new Gson();
    TypeToken<Object> typeToken = mock(TypeToken.class);
    Class<Object> forNameResult = Object.class;
    Mockito.<Class<? super Object>>when(typeToken.getRawType()).thenReturn(forNameResult);
    TypeAdapter<Object> actualCreateResult =
        actualNewFactoryForMultipleTypesResult.create(gson, typeToken);

    // Assert
    verify(typeToken).getRawType();
    assertSame(typeAdapter, actualCreateResult);
  }

  /**
   * Method under test: {@link TypeAdapters#newFactoryForMultipleTypes(Class, Class, TypeAdapter)}
   */
  @Test
  public void testNewFactoryForMultipleTypes2() {
    // Arrange
    Class<Object> base = Object.class;
    Class<Object> sub = Object.class;
    Gson context = new Gson();
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    TreeTypeAdapter<Object> componentTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, new Gson(), null, mock(TypeAdapterFactory.class));

    Class<Object> componentType = Object.class;

    // Act
    TypeAdapterFactory actualNewFactoryForMultipleTypesResult =
        TypeAdapters.newFactoryForMultipleTypes(
            base, sub, new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType));
    Gson gson = new Gson();
    TypeToken<Object> typeToken = mock(TypeToken.class);
    Mockito.<Class<? super Object>>when(typeToken.getRawType())
        .thenThrow(new UnsupportedOperationException("foo"));

    // Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> actualNewFactoryForMultipleTypesResult.create(gson, typeToken));
    verify(typeToken).getRawType();
  }

  /** Method under test: {@link TypeAdapters#newTypeHierarchyFactory(Class, TypeAdapter)} */
  @Test
  public void testNewTypeHierarchyFactory() {
    // Arrange
    Class<Object> clazz = Object.class;
    Gson context = new Gson();
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    TreeTypeAdapter<Object> componentTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, new Gson(), null, mock(TypeAdapterFactory.class));

    Class<Object> componentType = Object.class;

    // Act
    TypeAdapterFactory actualNewTypeHierarchyFactoryResult =
        TypeAdapters.newTypeHierarchyFactory(
            clazz, new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType));
    Gson gson = new Gson();
    TypeToken<Object> typeToken = mock(TypeToken.class);
    Class<Object> forNameResult = Object.class;
    Mockito.<Class<? super Object>>when(typeToken.getRawType()).thenReturn(forNameResult);
    actualNewTypeHierarchyFactoryResult.create(gson, typeToken);

    // Assert
    verify(typeToken).getRawType();
  }

  /** Method under test: {@link TypeAdapters#newTypeHierarchyFactory(Class, TypeAdapter)} */
  @Test
  public void testNewTypeHierarchyFactory2() {
    // Arrange
    Class<Object> clazz = Object.class;
    Gson context = new Gson();
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    TreeTypeAdapter<Object> componentTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, new Gson(), null, mock(TypeAdapterFactory.class));

    Class<Object> componentType = Object.class;

    // Act
    TypeAdapterFactory actualNewTypeHierarchyFactoryResult =
        TypeAdapters.newTypeHierarchyFactory(
            clazz, new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType));
    Gson gson = new Gson();
    TypeToken<Object> typeToken = mock(TypeToken.class);
    Mockito.<Class<? super Object>>when(typeToken.getRawType())
        .thenThrow(new UnsupportedOperationException("foo"));

    // Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> actualNewTypeHierarchyFactoryResult.create(gson, typeToken));
    verify(typeToken).getRawType();
  }
}
