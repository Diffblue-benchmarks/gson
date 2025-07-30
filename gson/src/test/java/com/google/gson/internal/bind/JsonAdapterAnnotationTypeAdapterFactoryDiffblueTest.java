package com.google.gson.internal.bind;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.Gson;
import com.google.gson.InstanceCreator;
import com.google.gson.ReflectionAccessFilter;
import com.google.gson.ReflectionAccessFilter.FilterResult;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

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
   * Test {@link JsonAdapterAnnotationTypeAdapterFactory#getTypeAdapter(ConstructorConstructor,
   * Gson, TypeToken, JsonAdapter, boolean)}.
   *
   * <p>Method under test: {@link
   * JsonAdapterAnnotationTypeAdapterFactory#getTypeAdapter(ConstructorConstructor, Gson, TypeToken,
   * JsonAdapter, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "com.google.gson.TypeAdapter"
        + " JsonAdapterAnnotationTypeAdapterFactory.getTypeAdapter(ConstructorConstructor, Gson,"
        + " TypeToken, JsonAdapter, boolean)"
  })
  public void testGetTypeAdapter() {
    // Arrange
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory =
        new JsonAdapterAnnotationTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>()));
    HashMap<Type, InstanceCreator<?>> instanceCreators2 = new HashMap<>();
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(instanceCreators2, true, new ArrayList<>());

    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<?> type2 = TypeToken.get(type);
    JsonAdapter annotation = mock(JsonAdapter.class);
    when(annotation.nullSafe())
        .thenThrow(new IllegalArgumentException("Invalid attempt to bind an instance of "));
    Class<Object> forNameResult = Object.class;
    Mockito.<Class<?>>when(annotation.value()).thenReturn(forNameResult);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            jsonAdapterAnnotationTypeAdapterFactory.getTypeAdapter(
                constructorConstructor, gson, type2, annotation, true));
    verify(annotation).nullSafe();
    verify(annotation).value();
  }

  /**
   * Test {@link JsonAdapterAnnotationTypeAdapterFactory#getTypeAdapter(ConstructorConstructor,
   * Gson, TypeToken, JsonAdapter, boolean)}.
   *
   * <ul>
   *   <li>Given {@code false}.
   *   <li>Then calls {@link JsonAdapter#nullSafe()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * JsonAdapterAnnotationTypeAdapterFactory#getTypeAdapter(ConstructorConstructor, Gson, TypeToken,
   * JsonAdapter, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "com.google.gson.TypeAdapter"
        + " JsonAdapterAnnotationTypeAdapterFactory.getTypeAdapter(ConstructorConstructor, Gson,"
        + " TypeToken, JsonAdapter, boolean)"
  })
  public void testGetTypeAdapter_givenFalse_thenCallsNullSafe() {
    // Arrange
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory =
        new JsonAdapterAnnotationTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>()));
    HashMap<Type, InstanceCreator<?>> instanceCreators2 = new HashMap<>();
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(instanceCreators2, true, new ArrayList<>());

    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<?> type2 = TypeToken.get(type);
    JsonAdapter annotation = mock(JsonAdapter.class);
    when(annotation.nullSafe()).thenReturn(false);
    Class<Object> forNameResult = Object.class;
    Mockito.<Class<?>>when(annotation.value()).thenReturn(forNameResult);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            jsonAdapterAnnotationTypeAdapterFactory.getTypeAdapter(
                constructorConstructor, gson, type2, annotation, true));
    verify(annotation).nullSafe();
    verify(annotation).value();
  }

  /**
   * Test {@link JsonAdapterAnnotationTypeAdapterFactory#getTypeAdapter(ConstructorConstructor,
   * Gson, TypeToken, JsonAdapter, boolean)}.
   *
   * <ul>
   *   <li>Given {@link IllegalArgumentException#IllegalArgumentException(String)} with {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link
   * JsonAdapterAnnotationTypeAdapterFactory#getTypeAdapter(ConstructorConstructor, Gson, TypeToken,
   * JsonAdapter, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "com.google.gson.TypeAdapter"
        + " JsonAdapterAnnotationTypeAdapterFactory.getTypeAdapter(ConstructorConstructor, Gson,"
        + " TypeToken, JsonAdapter, boolean)"
  })
  public void testGetTypeAdapter_givenIllegalArgumentExceptionWithFoo() {
    // Arrange
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory =
        new JsonAdapterAnnotationTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>()));
    HashMap<Type, InstanceCreator<?>> instanceCreators2 = new HashMap<>();
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(instanceCreators2, true, new ArrayList<>());

    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<?> type2 = TypeToken.get(type);
    JsonAdapter annotation = mock(JsonAdapter.class);
    Mockito.<Class<?>>when(annotation.value()).thenThrow(new IllegalArgumentException("foo"));

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            jsonAdapterAnnotationTypeAdapterFactory.getTypeAdapter(
                constructorConstructor, gson, type2, annotation, true));
    verify(annotation).value();
  }

  /**
   * Test {@link JsonAdapterAnnotationTypeAdapterFactory#getTypeAdapter(ConstructorConstructor,
   * Gson, TypeToken, JsonAdapter, boolean)}.
   *
   * <ul>
   *   <li>Given {@link ReflectionAccessFilter} {@link ReflectionAccessFilter#check(Class)} return
   *       {@code ALLOW}.
   *   <li>Then calls {@link ReflectionAccessFilter#check(Class)}.
   * </ul>
   *
   * <p>Method under test: {@link
   * JsonAdapterAnnotationTypeAdapterFactory#getTypeAdapter(ConstructorConstructor, Gson, TypeToken,
   * JsonAdapter, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "com.google.gson.TypeAdapter"
        + " JsonAdapterAnnotationTypeAdapterFactory.getTypeAdapter(ConstructorConstructor, Gson,"
        + " TypeToken, JsonAdapter, boolean)"
  })
  public void testGetTypeAdapter_givenReflectionAccessFilterCheckReturnAllow_thenCallsCheck() {
    // Arrange
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory =
        new JsonAdapterAnnotationTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>()));
    ReflectionAccessFilter reflectionAccessFilter = mock(ReflectionAccessFilter.class);
    when(reflectionAccessFilter.check(Mockito.<Class<?>>any())).thenReturn(FilterResult.ALLOW);

    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();
    reflectionFilters.add(reflectionAccessFilter);
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(new HashMap<>(), true, reflectionFilters);

    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<?> type2 = TypeToken.get(type);
    JsonAdapter annotation = mock(JsonAdapter.class);
    when(annotation.nullSafe()).thenReturn(false);
    Class<Object> forNameResult = Object.class;
    Mockito.<Class<?>>when(annotation.value()).thenReturn(forNameResult);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            jsonAdapterAnnotationTypeAdapterFactory.getTypeAdapter(
                constructorConstructor, gson, type2, annotation, true));
    verify(reflectionAccessFilter).check(isA(Class.class));
    verify(annotation).nullSafe();
    verify(annotation).value();
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
            type2, mock(TypeAdapterFactory.class)));
  }
}
