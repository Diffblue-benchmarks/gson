package com.google.gson.internal.bind;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class TypeAdaptersDiffblueTest {
  /**
   * Test {@link TypeAdapters#newFactory(Class, Class, TypeAdapter)} with {@code Class}, {@code
   * Class}, {@code TypeAdapter}.
   *
   * <p>Method under test: {@link TypeAdapters#newFactory(Class, Class, TypeAdapter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapterFactory TypeAdapters.newFactory(Class, Class, TypeAdapter)"})
  public void testNewFactoryWithClassClassTypeAdapter() {
    // Arrange
    Class<Object> unboxed = Object.class;
    Class<Object> boxed = Object.class;
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
    ArrayTypeAdapter<Object> typeAdapter =
        new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType);

    // Act
    TypeAdapterFactory actualNewFactoryResult =
        TypeAdapters.newFactory(unboxed, boxed, typeAdapter);
    Gson gson2 = new Gson();
    Class<Object> type2 = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type2);

    // Assert
    assertSame(typeAdapter, actualNewFactoryResult.create(gson2, getResult));
  }

  /**
   * Test {@link TypeAdapters#newFactory(Class, TypeAdapter)} with {@code Class}, {@code
   * TypeAdapter}.
   *
   * <p>Method under test: {@link TypeAdapters#newFactory(Class, TypeAdapter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapterFactory TypeAdapters.newFactory(Class, TypeAdapter)"})
  public void testNewFactoryWithClassTypeAdapter() {
    // Arrange
    Class<Object> type = Object.class;
    Gson context = new Gson();
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type2 = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type2);
    TreeTypeAdapter<Object> componentTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Class<Object> componentType = Object.class;
    ArrayTypeAdapter<Object> typeAdapter =
        new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType);

    // Act
    TypeAdapterFactory actualNewFactoryResult = TypeAdapters.newFactory(type, typeAdapter);
    Gson gson2 = new Gson();
    Class<Object> type3 = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type3);

    // Assert
    assertSame(typeAdapter, actualNewFactoryResult.create(gson2, getResult));
  }

  /**
   * Test {@link TypeAdapters#newFactory(TypeToken, TypeAdapter)} with {@code TypeToken}, {@code
   * TypeAdapter}.
   *
   * <p>Method under test: {@link TypeAdapters#newFactory(TypeToken, TypeAdapter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapterFactory TypeAdapters.newFactory(TypeToken, TypeAdapter)"})
  public void testNewFactoryWithTypeTokenTypeAdapter() {
    // Arrange
    Class<Object> type = Object.class;
    TypeToken<Object> type2 = TypeToken.get(type);
    Gson context = new Gson();
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type3 = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type3);
    TreeTypeAdapter<Object> componentTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Class<Object> componentType = Object.class;
    ArrayTypeAdapter<Object> typeAdapter =
        new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType);

    // Act
    TypeAdapterFactory actualNewFactoryResult = TypeAdapters.newFactory(type2, typeAdapter);
    Gson gson2 = new Gson();
    Class<Object> type4 = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type4);

    // Assert
    assertSame(typeAdapter, actualNewFactoryResult.create(gson2, getResult));
  }

  /**
   * Test {@link TypeAdapters#newFactoryForMultipleTypes(Class, Class, TypeAdapter)}.
   *
   * <p>Method under test: {@link TypeAdapters#newFactoryForMultipleTypes(Class, Class,
   * TypeAdapter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "TypeAdapterFactory TypeAdapters.newFactoryForMultipleTypes(Class, Class, TypeAdapter)"
  })
  public void testNewFactoryForMultipleTypes() {
    // Arrange
    Class<Object> base = Object.class;
    Class<Object> sub = Object.class;
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
    ArrayTypeAdapter<Object> typeAdapter =
        new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType);

    // Act
    TypeAdapterFactory actualNewFactoryForMultipleTypesResult =
        TypeAdapters.newFactoryForMultipleTypes(base, sub, typeAdapter);
    Gson gson2 = new Gson();
    Class<Object> type2 = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type2);

    // Assert
    assertSame(typeAdapter, actualNewFactoryForMultipleTypesResult.create(gson2, getResult));
  }
}
