package com.google.gson.internal.bind;

import static org.junit.Assert.assertNull;
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
import java.util.Collection;
import java.util.HashMap;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class CollectionTypeAdapterFactoryDiffblueTest {
  /**
   * Test {@link CollectionTypeAdapterFactory#create(Gson, TypeToken)}.
   *
   * <ul>
   *   <li>Given {@link ReflectionAccessFilter} {@link ReflectionAccessFilter#check(Class)} return
   *       {@code ALLOW}.
   *   <li>Then calls {@link ReflectionAccessFilter#check(Class)}.
   * </ul>
   *
   * <p>Method under test: {@link CollectionTypeAdapterFactory#create(Gson, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "com.google.gson.TypeAdapter CollectionTypeAdapterFactory.create(Gson, TypeToken)"
  })
  public void testCreate_givenReflectionAccessFilterCheckReturnAllow_thenCallsCheck() {
    // Arrange
    ReflectionAccessFilter reflectionAccessFilter = mock(ReflectionAccessFilter.class);
    when(reflectionAccessFilter.check(Mockito.<Class<?>>any())).thenReturn(FilterResult.ALLOW);

    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();
    reflectionFilters.add(reflectionAccessFilter);
    CollectionTypeAdapterFactory collectionTypeAdapterFactory =
        new CollectionTypeAdapterFactory(
            new ConstructorConstructor(new HashMap<>(), true, reflectionFilters));
    Gson gson = new Gson();
    Class<Collection> type = Collection.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    // Act
    collectionTypeAdapterFactory.create(gson, typeToken);

    // Assert
    verify(reflectionAccessFilter).check(isA(Class.class));
  }

  /**
   * Test {@link CollectionTypeAdapterFactory#create(Gson, TypeToken)}.
   *
   * <ul>
   *   <li>Given {@link ReflectionAccessFilter} {@link ReflectionAccessFilter#check(Class)} return
   *       {@code INDECISIVE}.
   *   <li>Then calls {@link ReflectionAccessFilter#check(Class)}.
   * </ul>
   *
   * <p>Method under test: {@link CollectionTypeAdapterFactory#create(Gson, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "com.google.gson.TypeAdapter CollectionTypeAdapterFactory.create(Gson, TypeToken)"
  })
  public void testCreate_givenReflectionAccessFilterCheckReturnIndecisive_thenCallsCheck() {
    // Arrange
    ReflectionAccessFilter reflectionAccessFilter = mock(ReflectionAccessFilter.class);
    when(reflectionAccessFilter.check(Mockito.<Class<?>>any())).thenReturn(FilterResult.INDECISIVE);

    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();
    reflectionFilters.add(reflectionAccessFilter);
    CollectionTypeAdapterFactory collectionTypeAdapterFactory =
        new CollectionTypeAdapterFactory(
            new ConstructorConstructor(new HashMap<>(), true, reflectionFilters));
    Gson gson = new Gson();
    Class<Collection> type = Collection.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    // Act
    collectionTypeAdapterFactory.create(gson, typeToken);

    // Assert
    verify(reflectionAccessFilter).check(isA(Class.class));
  }

  /**
   * Test {@link CollectionTypeAdapterFactory#create(Gson, TypeToken)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link CollectionTypeAdapterFactory#create(Gson, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "com.google.gson.TypeAdapter CollectionTypeAdapterFactory.create(Gson, TypeToken)"
  })
  public void testCreate_whenJavaLangObject_thenReturnNull() {
    // Arrange
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    CollectionTypeAdapterFactory collectionTypeAdapterFactory =
        new CollectionTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>()));
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    // Act and Assert
    assertNull(collectionTypeAdapterFactory.create(gson, typeToken));
  }
}
