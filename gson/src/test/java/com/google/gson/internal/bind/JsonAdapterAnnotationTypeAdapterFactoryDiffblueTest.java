package com.google.gson.internal.bind;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.Gson;
import com.google.gson.InstanceCreator;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JsonAdapterAnnotationTypeAdapterFactoryDiffblueTest {
  /**
   * Test {@link JsonAdapterAnnotationTypeAdapterFactory#create(Gson, TypeToken)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JsonAdapterAnnotationTypeAdapterFactory#create(Gson, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "com.google.gson.TypeAdapter JsonAdapterAnnotationTypeAdapterFactory.create(Gson, TypeToken)"
  })
  public void testCreate_whenJavaLangObject_thenReturnNull() {
    // Arrange
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory =
        new JsonAdapterAnnotationTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>()));
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> targetType = TypeToken.get(type);

    // Act and Assert
    assertNull(jsonAdapterAnnotationTypeAdapterFactory.create(gson, targetType));
  }

  /**
   * Test {@link JsonAdapterAnnotationTypeAdapterFactory#isClassJsonAdapterFactory(TypeToken,
   * TypeAdapterFactory)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link
   * JsonAdapterAnnotationTypeAdapterFactory#isClassJsonAdapterFactory(TypeToken,
   * TypeAdapterFactory)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean JsonAdapterAnnotationTypeAdapterFactory.isClassJsonAdapterFactory(TypeToken,"
        + " TypeAdapterFactory)"
  })
  public void testIsClassJsonAdapterFactory_whenJavaLangObject_thenReturnFalse() {
    // Arrange
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory =
        new JsonAdapterAnnotationTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>()));
    Class<Object> type = Object.class;
    TypeToken<?> type2 = TypeToken.get(type);

    // Act and Assert
    assertFalse(
        jsonAdapterAnnotationTypeAdapterFactory.isClassJsonAdapterFactory(
            type2, ArrayTypeAdapter.FACTORY));
  }
}
