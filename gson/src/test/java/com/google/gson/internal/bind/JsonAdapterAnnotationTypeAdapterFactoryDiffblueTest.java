package com.google.gson.internal.bind;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.diffblue.cover.annotations.MaintainedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.Gson;
import com.google.gson.InstanceCreator;
import com.google.gson.ReflectionAccessFilter;
import com.google.gson.ReflectionAccessFilter.FilterResult;
import com.google.gson.TypeAdapter;
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
   *   <li>Given {@code Object}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JsonAdapterAnnotationTypeAdapterFactory#create(Gson, TypeToken)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"TypeAdapter JsonAdapterAnnotationTypeAdapterFactory.create(Gson, TypeToken)"})
  public void testCreate_givenJavaLangObject_thenReturnNull() {
    // Arrange
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory =
        new JsonAdapterAnnotationTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>()));
    Gson gson = new Gson();
    TypeToken<Object> targetType = mock(TypeToken.class);
    Class<Object> forNameResult = Object.class;
    Mockito.<Class<? super Object>>when(targetType.getRawType()).thenReturn(forNameResult);

    // Act
    TypeAdapter<Object> actualCreateResult =
        jsonAdapterAnnotationTypeAdapterFactory.create(gson, targetType);

    // Assert
    verify(targetType).getRawType();
    assertNull(actualCreateResult);
  }

  /**
   * Test {@link JsonAdapterAnnotationTypeAdapterFactory#create(Gson, TypeToken)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonAdapterAnnotationTypeAdapterFactory#create(Gson, TypeToken)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"TypeAdapter JsonAdapterAnnotationTypeAdapterFactory.create(Gson, TypeToken)"})
  public void testCreate_thenThrowIllegalArgumentException() {
    // Arrange
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory =
        new JsonAdapterAnnotationTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>()));
    Gson gson = new Gson();
    TypeToken<Object> targetType = mock(TypeToken.class);
    Mockito.<Class<? super Object>>when(targetType.getRawType())
        .thenThrow(new IllegalArgumentException("foo"));

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> jsonAdapterAnnotationTypeAdapterFactory.create(gson, targetType));
    verify(targetType).getRawType();
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
    "TypeAdapter JsonAdapterAnnotationTypeAdapterFactory.getTypeAdapter(ConstructorConstructor,"
        + " Gson, TypeToken, JsonAdapter, boolean)"
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
    TypeToken<?> type = mock(TypeToken.class);
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
                constructorConstructor, gson, type, annotation, true));
    verify(annotation).nullSafe();
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
    "TypeAdapter JsonAdapterAnnotationTypeAdapterFactory.getTypeAdapter(ConstructorConstructor,"
        + " Gson, TypeToken, JsonAdapter, boolean)"
  })
  public void testGetTypeAdapter_givenReflectionAccessFilterCheckReturnAllow_thenCallsCheck() {
    // Arrange
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory =
        new JsonAdapterAnnotationTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>()));
    ReflectionAccessFilter reflectionAccessFilter = mock(ReflectionAccessFilter.class);
    when(reflectionAccessFilter.check(Mockito.<Class<Object>>any())).thenReturn(FilterResult.ALLOW);

    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();
    reflectionFilters.add(reflectionAccessFilter);
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(new HashMap<>(), true, reflectionFilters);

    Gson gson = new Gson();
    TypeToken<?> type = mock(TypeToken.class);
    JsonAdapter annotation = mock(JsonAdapter.class);
    when(annotation.nullSafe()).thenReturn(true);
    Class<Object> forNameResult = Object.class;
    Mockito.<Class<?>>when(annotation.value()).thenReturn(forNameResult);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            jsonAdapterAnnotationTypeAdapterFactory.getTypeAdapter(
                constructorConstructor, gson, type, annotation, true));
    verify(reflectionAccessFilter).check(isA(Class.class));
    verify(annotation).nullSafe();
    verify(annotation).value();
  }

  /**
   * Test {@link JsonAdapterAnnotationTypeAdapterFactory#getTypeAdapter(ConstructorConstructor,
   * Gson, TypeToken, JsonAdapter, boolean)}.
   *
   * <ul>
   *   <li>Given {@code true}.
   *   <li>When {@link JsonAdapter} {@link JsonAdapter#nullSafe()} return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link
   * JsonAdapterAnnotationTypeAdapterFactory#getTypeAdapter(ConstructorConstructor, Gson, TypeToken,
   * JsonAdapter, boolean)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
    "TypeAdapter JsonAdapterAnnotationTypeAdapterFactory.getTypeAdapter(ConstructorConstructor,"
        + " Gson, TypeToken, JsonAdapter, boolean)"
  })
  public void testGetTypeAdapter_givenTrue_whenJsonAdapterNullSafeReturnTrue() {
    // Arrange
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory =
        new JsonAdapterAnnotationTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>()));
    HashMap<Type, InstanceCreator<?>> instanceCreators2 = new HashMap<>();
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(instanceCreators2, true, new ArrayList<>());

    Gson gson = new Gson();
    TypeToken<?> type = mock(TypeToken.class);
    JsonAdapter annotation = mock(JsonAdapter.class);
    when(annotation.nullSafe()).thenReturn(true);
    Class<Object> forNameResult = Object.class;
    Mockito.<Class<?>>when(annotation.value()).thenReturn(forNameResult);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            jsonAdapterAnnotationTypeAdapterFactory.getTypeAdapter(
                constructorConstructor, gson, type, annotation, true));
    verify(annotation).nullSafe();
    verify(annotation).value();
  }
}
