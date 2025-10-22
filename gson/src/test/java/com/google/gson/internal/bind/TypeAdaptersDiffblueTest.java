package com.google.gson.internal.bind;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.reflect.ReflectionHelperDiffblueTestFactory;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
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
    ObjectTypeAdapter typeAdapter =
        ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();

    // Act
    TypeAdapterFactory actualNewFactoryResult =
        TypeAdapters.newFactory(unboxed, boxed, typeAdapter);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type);

    // Assert
    assertSame(typeAdapter, actualNewFactoryResult.create(gson, getResult));
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
    ObjectTypeAdapter typeAdapter =
        ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();

    // Act
    TypeAdapterFactory actualNewFactoryResult = TypeAdapters.newFactory(type, typeAdapter);
    Gson gson = new Gson();
    Class<Object> type2 = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type2);

    // Assert
    assertSame(typeAdapter, actualNewFactoryResult.create(gson, getResult));
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
    ObjectTypeAdapter typeAdapter =
        ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();

    // Act
    TypeAdapterFactory actualNewFactoryResult = TypeAdapters.newFactory(type2, typeAdapter);
    Gson gson = new Gson();
    Class<Object> type3 = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type3);

    // Assert
    assertSame(typeAdapter, actualNewFactoryResult.create(gson, getResult));
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
    Class<?> sub = ReflectionHelperDiffblueTestFactory.createNonNullClass();
    ObjectTypeAdapter typeAdapter =
        ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter();

    // Act
    TypeAdapterFactory actualNewFactoryForMultipleTypesResult =
        TypeAdapters.newFactoryForMultipleTypes(base, sub, typeAdapter);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type);

    // Assert
    assertSame(typeAdapter, actualNewFactoryForMultipleTypesResult.create(gson, getResult));
  }

  /**
   * Test {@link TypeAdapters#newTypeHierarchyFactory(Class, TypeAdapter)}.
   *
   * <ul>
   *   <li>Then return create {@link Gson#Gson()} and {@link Object} toJson {@code null} is {@code
   *       null}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapters#newTypeHierarchyFactory(Class, TypeAdapter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapterFactory TypeAdapters.newTypeHierarchyFactory(Class, TypeAdapter)"})
  public void testNewTypeHierarchyFactory_thenReturnCreateGsonAndObjectToJsonNullIsNull()
      throws IOException {
    // Arrange
    Class<Object> clazz = Object.class;

    // Act
    TypeAdapterFactory actualNewTypeHierarchyFactoryResult =
        TypeAdapters.newTypeHierarchyFactory(
            clazz, ObjectTypeAdapterDiffblueTestFactory.createLongOrDoubleAdapter());
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type);
    TypeAdapter<Object> actualCreateResult =
        actualNewTypeHierarchyFactoryResult.create(gson, getResult);

    // Assert
    assertEquals("null", actualCreateResult.toJson(null));
    assertEquals(
        "{\"name\":\"John Doe\",\"age\":30,\"city\":\"New York\"}",
        actualCreateResult.fromJson(
            "\"{\\\"name\\\":\\\"John Doe\\\",\\\"age\\\":30,\\\"city\\\":\\\"New York\\\"}\""));
  }
}
