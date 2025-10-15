package com.google.gson.internal.bind;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
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
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class MapTypeAdapterFactoryDiffblueTest {
  /**
   * Test {@link MapTypeAdapterFactory#MapTypeAdapterFactory(ConstructorConstructor, boolean)}.
   *
   * <p>Method under test: {@link
   * MapTypeAdapterFactory#MapTypeAdapterFactory(ConstructorConstructor, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MapTypeAdapterFactory.<init>(ConstructorConstructor, boolean)"})
  public void testNewMapTypeAdapterFactory() {
    // Arrange
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();

    // Act and Assert
    assertTrue(
        new MapTypeAdapterFactory(
                new ConstructorConstructor(instanceCreators, true, new ArrayList<>()), true)
            .complexMapKeySerialization);
  }

  /**
   * Test {@link MapTypeAdapterFactory#create(Gson, TypeToken)}.
   *
   * <ul>
   *   <li>Given {@link ReflectionAccessFilter} {@link ReflectionAccessFilter#check(Class)} return
   *       {@code ALLOW}.
   *   <li>Then calls {@link ReflectionAccessFilter#check(Class)}.
   * </ul>
   *
   * <p>Method under test: {@link MapTypeAdapterFactory#create(Gson, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"com.google.gson.TypeAdapter MapTypeAdapterFactory.create(Gson, TypeToken)"})
  public void testCreate_givenReflectionAccessFilterCheckReturnAllow_thenCallsCheck() {
    // Arrange
    ReflectionAccessFilter reflectionAccessFilter = mock(ReflectionAccessFilter.class);
    when(reflectionAccessFilter.check(Mockito.<Class<?>>any())).thenReturn(FilterResult.ALLOW);

    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();
    reflectionFilters.add(reflectionAccessFilter);
    MapTypeAdapterFactory mapTypeAdapterFactory =
        new MapTypeAdapterFactory(
            new ConstructorConstructor(new HashMap<>(), true, reflectionFilters), true);
    Gson gson = new Gson();
    Class<Map> type = Map.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    // Act
    mapTypeAdapterFactory.create(gson, typeToken);

    // Assert
    verify(reflectionAccessFilter).check(isA(Class.class));
  }

  /**
   * Test {@link MapTypeAdapterFactory#create(Gson, TypeToken)}.
   *
   * <ul>
   *   <li>Given {@link ReflectionAccessFilter} {@link ReflectionAccessFilter#check(Class)} return
   *       {@code INDECISIVE}.
   *   <li>Then calls {@link ReflectionAccessFilter#check(Class)}.
   * </ul>
   *
   * <p>Method under test: {@link MapTypeAdapterFactory#create(Gson, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"com.google.gson.TypeAdapter MapTypeAdapterFactory.create(Gson, TypeToken)"})
  public void testCreate_givenReflectionAccessFilterCheckReturnIndecisive_thenCallsCheck() {
    // Arrange
    ReflectionAccessFilter reflectionAccessFilter = mock(ReflectionAccessFilter.class);
    when(reflectionAccessFilter.check(Mockito.<Class<?>>any())).thenReturn(FilterResult.INDECISIVE);

    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();
    reflectionFilters.add(reflectionAccessFilter);
    MapTypeAdapterFactory mapTypeAdapterFactory =
        new MapTypeAdapterFactory(
            new ConstructorConstructor(new HashMap<>(), true, reflectionFilters), true);
    Gson gson = new Gson();
    Class<Map> type = Map.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    // Act
    mapTypeAdapterFactory.create(gson, typeToken);

    // Assert
    verify(reflectionAccessFilter).check(isA(Class.class));
  }

  /**
   * Test {@link MapTypeAdapterFactory#create(Gson, TypeToken)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link MapTypeAdapterFactory#create(Gson, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"com.google.gson.TypeAdapter MapTypeAdapterFactory.create(Gson, TypeToken)"})
  public void testCreate_whenJavaLangObject_thenReturnNull() {
    // Arrange
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    MapTypeAdapterFactory mapTypeAdapterFactory =
        new MapTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>()), true);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    // Act and Assert
    assertNull(mapTypeAdapterFactory.create(gson, typeToken));
  }
}
