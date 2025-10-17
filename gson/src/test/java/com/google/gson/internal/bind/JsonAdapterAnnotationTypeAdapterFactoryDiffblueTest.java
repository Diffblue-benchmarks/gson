package com.google.gson.internal.bind;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilderTestFactory;
import com.google.gson.InstanceCreator;
import com.google.gson.ParameterizedTypeFixtures;
import com.google.gson.ParameterizedTypeFixtures.MyParameterizedTypeAdapter;
import com.google.gson.ReflectionAccessFilter;
import com.google.gson.ReflectionAccessFilter.FilterResult;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.reflect.ReflectionHelperTestFactory;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
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
  @MethodsUnderTest({"TypeAdapter JsonAdapterAnnotationTypeAdapterFactory.create(Gson, TypeToken)"})
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
    "TypeAdapter JsonAdapterAnnotationTypeAdapterFactory.getTypeAdapter(ConstructorConstructor, Gson, TypeToken, JsonAdapter, boolean)"
  })
  public void testGetTypeAdapter() {
    // Arrange
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory =
        new JsonAdapterAnnotationTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>()));

    InstanceCreator<Object> instanceCreator = mock(InstanceCreator.class);
    when(instanceCreator.createInstance(Mockito.<Type>any()))
        .thenReturn(mock(DefaultDateTypeAdapter.class));

    HashMap<Type, InstanceCreator<?>> instanceCreators2 = new HashMap<>();
    instanceCreators2.put(GsonBuilderTestFactory.createType(), instanceCreator);
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(instanceCreators2, true, new ArrayList<>());
    Gson gson = new Gson();
    Class<?> type = ReflectionHelperTestFactory.createStringClass();
    TypeToken<?> type2 = TypeToken.get(type);

    JsonAdapter annotation = mock(JsonAdapter.class);
    when(annotation.nullSafe()).thenReturn(false);
    Class<?> createStringClassResult = ReflectionHelperTestFactory.createStringClass();
    Mockito.<Class<?>>when(annotation.value()).thenReturn(createStringClassResult);

    // Act
    jsonAdapterAnnotationTypeAdapterFactory.getTypeAdapter(
        constructorConstructor, gson, type2, annotation, true);

    // Assert
    verify(instanceCreator).createInstance(isA(Type.class));
    verify(annotation).nullSafe();
    verify(annotation).value();
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
    "TypeAdapter JsonAdapterAnnotationTypeAdapterFactory.getTypeAdapter(ConstructorConstructor, Gson, TypeToken, JsonAdapter, boolean)"
  })
  public void testGetTypeAdapter2() {
    // Arrange
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory =
        new JsonAdapterAnnotationTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>()));

    InstanceCreator<Object> instanceCreator = mock(InstanceCreator.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators2 = new HashMap<>();
    when(instanceCreator.createInstance(Mockito.<Type>any()))
        .thenReturn(
            new JsonAdapterAnnotationTypeAdapterFactory(
                new ConstructorConstructor(instanceCreators2, true, new ArrayList<>())));

    HashMap<Type, InstanceCreator<?>> instanceCreators3 = new HashMap<>();
    instanceCreators3.put(GsonBuilderTestFactory.createType(), instanceCreator);
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(instanceCreators3, true, new ArrayList<>());
    Gson gson = new Gson();
    Class<?> type = ReflectionHelperTestFactory.createStringClass();
    TypeToken<?> type2 = TypeToken.get(type);

    JsonAdapter annotation = mock(JsonAdapter.class);
    when(annotation.nullSafe()).thenReturn(false);
    Class<?> createStringClassResult = ReflectionHelperTestFactory.createStringClass();
    Mockito.<Class<?>>when(annotation.value()).thenReturn(createStringClassResult);

    // Act
    TypeAdapter<?> actualTypeAdapter =
        jsonAdapterAnnotationTypeAdapterFactory.getTypeAdapter(
            constructorConstructor, gson, type2, annotation, true);

    // Assert
    verify(instanceCreator).createInstance(isA(Type.class));
    verify(annotation).nullSafe();
    verify(annotation).value();
    assertNull(actualTypeAdapter);
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
    "TypeAdapter JsonAdapterAnnotationTypeAdapterFactory.getTypeAdapter(ConstructorConstructor, Gson, TypeToken, JsonAdapter, boolean)"
  })
  public void testGetTypeAdapter3() {
    // Arrange
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory =
        new JsonAdapterAnnotationTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>()));

    DefaultDateTypeAdapter<Date> defaultDateTypeAdapter = mock(DefaultDateTypeAdapter.class);
    when(defaultDateTypeAdapter.nullSafe()).thenThrow(new IllegalArgumentException());

    InstanceCreator<Object> instanceCreator = mock(InstanceCreator.class);
    when(instanceCreator.createInstance(Mockito.<Type>any())).thenReturn(defaultDateTypeAdapter);

    HashMap<Type, InstanceCreator<?>> instanceCreators2 = new HashMap<>();
    instanceCreators2.put(GsonBuilderTestFactory.createType(), instanceCreator);
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(instanceCreators2, true, new ArrayList<>());
    Gson gson = new Gson();
    Class<?> type = ReflectionHelperTestFactory.createStringClass();
    TypeToken<?> type2 = TypeToken.get(type);

    JsonAdapter annotation = mock(JsonAdapter.class);
    when(annotation.nullSafe()).thenReturn(true);
    Class<?> createStringClassResult = ReflectionHelperTestFactory.createStringClass();
    Mockito.<Class<?>>when(annotation.value()).thenReturn(createStringClassResult);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            jsonAdapterAnnotationTypeAdapterFactory.getTypeAdapter(
                constructorConstructor, gson, type2, annotation, true));
    verify(instanceCreator).createInstance(isA(Type.class));
    verify(defaultDateTypeAdapter).nullSafe();
    verify(annotation).nullSafe();
    verify(annotation).value();
  }

  /**
   * Test {@link JsonAdapterAnnotationTypeAdapterFactory#getTypeAdapter(ConstructorConstructor,
   * Gson, TypeToken, JsonAdapter, boolean)}.
   *
   * <ul>
   *   <li>Given {@code false}.
   *   <li>Then throw {@link IllegalArgumentException}.
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
    "TypeAdapter JsonAdapterAnnotationTypeAdapterFactory.getTypeAdapter(ConstructorConstructor, Gson, TypeToken, JsonAdapter, boolean)"
  })
  public void testGetTypeAdapter_givenFalse_thenThrowIllegalArgumentException() {
    // Arrange
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory =
        new JsonAdapterAnnotationTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>()));
    HashMap<Type, InstanceCreator<?>> instanceCreators2 = new HashMap<>();
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(instanceCreators2, true, new ArrayList<>());
    Gson gson = new Gson();
    Class<?> type = ReflectionHelperTestFactory.createStringClass();
    TypeToken<?> type2 = TypeToken.get(type);

    JsonAdapter annotation = mock(JsonAdapter.class);
    when(annotation.nullSafe()).thenReturn(false);
    Class<?> createStringClassResult = ReflectionHelperTestFactory.createStringClass();
    Mockito.<Class<?>>when(annotation.value()).thenReturn(createStringClassResult);

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
   *   <li>Given {@link InstanceCreator} {@link InstanceCreator#createInstance(Type)} return
   *       createPublicField.
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
    "TypeAdapter JsonAdapterAnnotationTypeAdapterFactory.getTypeAdapter(ConstructorConstructor, Gson, TypeToken, JsonAdapter, boolean)"
  })
  public void testGetTypeAdapter_givenInstanceCreatorCreateInstanceReturnCreatePublicField()
      throws NoSuchFieldException {
    // Arrange
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory =
        new JsonAdapterAnnotationTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>()));

    InstanceCreator<Object> instanceCreator = mock(InstanceCreator.class);
    when(instanceCreator.createInstance(Mockito.<Type>any()))
        .thenReturn(ReflectionHelperTestFactory.createPublicField());

    HashMap<Type, InstanceCreator<?>> instanceCreators2 = new HashMap<>();
    instanceCreators2.put(GsonBuilderTestFactory.createType(), instanceCreator);
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(instanceCreators2, true, new ArrayList<>());
    Gson gson = new Gson();
    Class<?> type = ReflectionHelperTestFactory.createStringClass();
    TypeToken<?> type2 = TypeToken.get(type);

    JsonAdapter annotation = mock(JsonAdapter.class);
    when(annotation.nullSafe()).thenReturn(false);
    Class<?> createStringClassResult = ReflectionHelperTestFactory.createStringClass();
    Mockito.<Class<?>>when(annotation.value()).thenReturn(createStringClassResult);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            jsonAdapterAnnotationTypeAdapterFactory.getTypeAdapter(
                constructorConstructor, gson, type2, annotation, true));
    verify(instanceCreator).createInstance(isA(Type.class));
    verify(annotation).nullSafe();
    verify(annotation).value();
  }

  /**
   * Test {@link JsonAdapterAnnotationTypeAdapterFactory#getTypeAdapter(ConstructorConstructor,
   * Gson, TypeToken, JsonAdapter, boolean)}.
   *
   * <ul>
   *   <li>Given {@link InstanceCreator}.
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
    "TypeAdapter JsonAdapterAnnotationTypeAdapterFactory.getTypeAdapter(ConstructorConstructor, Gson, TypeToken, JsonAdapter, boolean)"
  })
  public void testGetTypeAdapter_givenInstanceCreator_thenCallsCheck() {
    // Arrange
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory =
        new JsonAdapterAnnotationTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>()));

    HashMap<Type, InstanceCreator<?>> instanceCreators2 = new HashMap<>();
    instanceCreators2.put(GsonBuilderTestFactory.createIntegerType(), mock(InstanceCreator.class));

    ReflectionAccessFilter reflectionAccessFilter = mock(ReflectionAccessFilter.class);
    when(reflectionAccessFilter.check(Mockito.<Class<?>>any())).thenReturn(FilterResult.ALLOW);

    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();
    reflectionFilters.add(reflectionAccessFilter);

    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(instanceCreators2, true, reflectionFilters);
    Gson gson = new Gson();
    Class<?> type = ReflectionHelperTestFactory.createStringClass();
    TypeToken<?> type2 = TypeToken.get(type);

    JsonAdapter annotation = mock(JsonAdapter.class);
    when(annotation.nullSafe()).thenReturn(true);
    Class<?> createStringClassResult = ReflectionHelperTestFactory.createStringClass();
    Mockito.<Class<?>>when(annotation.value()).thenReturn(createStringClassResult);

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
   * Test {@link JsonAdapterAnnotationTypeAdapterFactory#getTypeAdapter(ConstructorConstructor,
   * Gson, TypeToken, JsonAdapter, boolean)}.
   *
   * <ul>
   *   <li>Then return {@link DefaultDateTypeAdapter}.
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
    "TypeAdapter JsonAdapterAnnotationTypeAdapterFactory.getTypeAdapter(ConstructorConstructor, Gson, TypeToken, JsonAdapter, boolean)"
  })
  public void testGetTypeAdapter_thenReturnDefaultDateTypeAdapter() {
    // Arrange
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory =
        new JsonAdapterAnnotationTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>()));

    DefaultDateTypeAdapter<Date> defaultDateTypeAdapter = mock(DefaultDateTypeAdapter.class);
    TypeAdapter<Date> createDefaultDateTypeAdapterWithStylesResult =
        DefaultDateTypeAdapterTestFactory.createDefaultDateTypeAdapterWithStyles();
    when(defaultDateTypeAdapter.nullSafe())
        .thenReturn(createDefaultDateTypeAdapterWithStylesResult);

    InstanceCreator<Object> instanceCreator = mock(InstanceCreator.class);
    when(instanceCreator.createInstance(Mockito.<Type>any())).thenReturn(defaultDateTypeAdapter);

    HashMap<Type, InstanceCreator<?>> instanceCreators2 = new HashMap<>();
    instanceCreators2.put(GsonBuilderTestFactory.createType(), instanceCreator);
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(instanceCreators2, true, new ArrayList<>());
    Gson gson = new Gson();
    Class<?> type = ReflectionHelperTestFactory.createStringClass();
    TypeToken<?> type2 = TypeToken.get(type);

    JsonAdapter annotation = mock(JsonAdapter.class);
    when(annotation.nullSafe()).thenReturn(true);
    Class<?> createStringClassResult = ReflectionHelperTestFactory.createStringClass();
    Mockito.<Class<?>>when(annotation.value()).thenReturn(createStringClassResult);

    // Act
    TypeAdapter<?> actualTypeAdapter =
        jsonAdapterAnnotationTypeAdapterFactory.getTypeAdapter(
            constructorConstructor, gson, type2, annotation, true);

    // Assert
    verify(instanceCreator).createInstance(isA(Type.class));
    verify(defaultDateTypeAdapter).nullSafe();
    verify(annotation).nullSafe();
    verify(annotation).value();
    assertTrue(actualTypeAdapter instanceof DefaultDateTypeAdapter);
    assertSame(createDefaultDateTypeAdapterWithStylesResult, actualTypeAdapter);
  }

  /**
   * Test {@link JsonAdapterAnnotationTypeAdapterFactory#getTypeAdapter(ConstructorConstructor,
   * Gson, TypeToken, JsonAdapter, boolean)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
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
    "TypeAdapter JsonAdapterAnnotationTypeAdapterFactory.getTypeAdapter(ConstructorConstructor, Gson, TypeToken, JsonAdapter, boolean)"
  })
  public void testGetTypeAdapter_thenReturnNull() {
    // Arrange
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory =
        new JsonAdapterAnnotationTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>()));

    InstanceCreator<Object> instanceCreator = mock(InstanceCreator.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators2 = new HashMap<>();
    when(instanceCreator.createInstance(Mockito.<Type>any()))
        .thenReturn(
            new CollectionTypeAdapterFactory(
                new ConstructorConstructor(instanceCreators2, true, new ArrayList<>())));

    HashMap<Type, InstanceCreator<?>> instanceCreators3 = new HashMap<>();
    instanceCreators3.put(GsonBuilderTestFactory.createType(), instanceCreator);
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(instanceCreators3, true, new ArrayList<>());
    Gson gson = new Gson();
    Class<?> type = ReflectionHelperTestFactory.createStringClass();
    TypeToken<?> type2 = TypeToken.get(type);

    JsonAdapter annotation = mock(JsonAdapter.class);
    when(annotation.nullSafe()).thenReturn(false);
    Class<?> createStringClassResult = ReflectionHelperTestFactory.createStringClass();
    Mockito.<Class<?>>when(annotation.value()).thenReturn(createStringClassResult);

    // Act
    TypeAdapter<?> actualTypeAdapter =
        jsonAdapterAnnotationTypeAdapterFactory.getTypeAdapter(
            constructorConstructor, gson, type2, annotation, true);

    // Assert
    verify(instanceCreator).createInstance(isA(Type.class));
    verify(annotation).nullSafe();
    verify(annotation).value();
    assertNull(actualTypeAdapter);
  }

  /**
   * Test {@link JsonAdapterAnnotationTypeAdapterFactory#getTypeAdapter(ConstructorConstructor,
   * Gson, TypeToken, JsonAdapter, boolean)}.
   *
   * <ul>
   *   <li>Then return {@link TreeTypeAdapter}.
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
    "TypeAdapter JsonAdapterAnnotationTypeAdapterFactory.getTypeAdapter(ConstructorConstructor, Gson, TypeToken, JsonAdapter, boolean)"
  })
  public void testGetTypeAdapter_thenReturnTreeTypeAdapter() {
    // Arrange
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory =
        new JsonAdapterAnnotationTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>()));

    InstanceCreator<Object> instanceCreator = mock(InstanceCreator.class);
    when(instanceCreator.createInstance(Mockito.<Type>any()))
        .thenReturn(new MyParameterizedTypeAdapter<>());

    HashMap<Type, InstanceCreator<?>> instanceCreators2 = new HashMap<>();
    instanceCreators2.put(GsonBuilderTestFactory.createType(), instanceCreator);
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(instanceCreators2, true, new ArrayList<>());
    Gson gson = new Gson();
    Class<?> type = ReflectionHelperTestFactory.createStringClass();
    TypeToken<?> type2 = TypeToken.get(type);

    JsonAdapter annotation = mock(JsonAdapter.class);
    when(annotation.nullSafe()).thenReturn(false);
    Class<?> createStringClassResult = ReflectionHelperTestFactory.createStringClass();
    Mockito.<Class<?>>when(annotation.value()).thenReturn(createStringClassResult);

    // Act
    TypeAdapter<?> actualTypeAdapter =
        jsonAdapterAnnotationTypeAdapterFactory.getTypeAdapter(
            constructorConstructor, gson, type2, annotation, true);

    // Assert
    verify(instanceCreator).createInstance(isA(Type.class));
    verify(annotation).nullSafe();
    verify(annotation).value();
    assertTrue(actualTypeAdapter instanceof TreeTypeAdapter);
    Gson gson2 = ((TreeTypeAdapter<?>) actualTypeAdapter).gson;
    assertFalse(gson2.serializeNulls());
    assertTrue(gson2.htmlSafe());
    assertSame(
        actualTypeAdapter, ((TreeTypeAdapter<?>) actualTypeAdapter).getSerializationDelegate());
  }

  /**
   * Test {@link JsonAdapterAnnotationTypeAdapterFactory#getTypeAdapter(ConstructorConstructor,
   * Gson, TypeToken, JsonAdapter, boolean)}.
   *
   * <ul>
   *   <li>When {@link JsonAdapter} {@link JsonAdapter#nullSafe()} throw {@link
   *       IllegalArgumentException#IllegalArgumentException()}.
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
    "TypeAdapter JsonAdapterAnnotationTypeAdapterFactory.getTypeAdapter(ConstructorConstructor, Gson, TypeToken, JsonAdapter, boolean)"
  })
  public void testGetTypeAdapter_whenJsonAdapterNullSafeThrowIllegalArgumentException() {
    // Arrange
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory =
        new JsonAdapterAnnotationTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>()));
    HashMap<Type, InstanceCreator<?>> instanceCreators2 = new HashMap<>();
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(instanceCreators2, true, new ArrayList<>());
    Gson gson = new Gson();
    Class<?> type = ReflectionHelperTestFactory.createStringClass();
    TypeToken<?> type2 = TypeToken.get(type);

    JsonAdapter annotation = mock(JsonAdapter.class);
    when(annotation.nullSafe()).thenThrow(new IllegalArgumentException());
    Class<?> createStringClassResult = ReflectionHelperTestFactory.createStringClass();
    Mockito.<Class<?>>when(annotation.value()).thenReturn(createStringClassResult);

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
   *   <li>When {@link JsonAdapter} {@link JsonAdapter#value()} throw {@link
   *       IllegalArgumentException#IllegalArgumentException()}.
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
    "TypeAdapter JsonAdapterAnnotationTypeAdapterFactory.getTypeAdapter(ConstructorConstructor, Gson, TypeToken, JsonAdapter, boolean)"
  })
  public void testGetTypeAdapter_whenJsonAdapterValueThrowIllegalArgumentException() {
    // Arrange
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory =
        new JsonAdapterAnnotationTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>()));
    HashMap<Type, InstanceCreator<?>> instanceCreators2 = new HashMap<>();
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(instanceCreators2, true, new ArrayList<>());
    Gson gson = new Gson();
    Class<?> type = ReflectionHelperTestFactory.createStringClass();
    TypeToken<?> type2 = TypeToken.get(type);

    JsonAdapter annotation = mock(JsonAdapter.class);
    Mockito.<Class<?>>when(annotation.value()).thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            jsonAdapterAnnotationTypeAdapterFactory.getTypeAdapter(
                constructorConstructor, gson, type2, annotation, true));
    verify(annotation).value();
  }

  /**
   * Test {@link JsonAdapterAnnotationTypeAdapterFactory#isClassJsonAdapterFactory(TypeToken,
   * TypeAdapterFactory)}.
   *
   * <ul>
   *   <li>When createStringClass.
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
    "boolean JsonAdapterAnnotationTypeAdapterFactory.isClassJsonAdapterFactory(TypeToken, TypeAdapterFactory)"
  })
  public void testIsClassJsonAdapterFactory_whenCreateStringClass_thenReturnFalse() {
    // Arrange
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory =
        new JsonAdapterAnnotationTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>()));
    Class<?> type = ReflectionHelperTestFactory.createStringClass();
    TypeToken<?> type2 = TypeToken.get(type);

    // Act and Assert
    assertFalse(
        jsonAdapterAnnotationTypeAdapterFactory.isClassJsonAdapterFactory(
            type2, mock(TypeAdapterFactory.class)));
  }
}
