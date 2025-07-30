package com.google.gson.internal.bind;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.InstanceCreator;
import com.google.gson.ReflectionAccessFilter;
import com.google.gson.ReflectionAccessFilter.FilterResult;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class ReflectiveTypeAdapterFactoryDiffblueTest {
  /**
   * Test {@link ReflectiveTypeAdapterFactory#create(Gson, TypeToken)}.
   *
   * <ul>
   *   <li>Given {@link ReflectionAccessFilter} {@link ReflectionAccessFilter#check(Class)} return
   *       {@code ALLOW}.
   *   <li>Then calls {@link ReflectionAccessFilter#check(Class)}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectiveTypeAdapterFactory#create(Gson, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapter ReflectiveTypeAdapterFactory.create(Gson, TypeToken)"})
  public void testCreate_givenReflectionAccessFilterCheckReturnAllow_thenCallsCheck() {
    // Arrange
    ReflectionAccessFilter reflectionAccessFilter = mock(ReflectionAccessFilter.class);
    when(reflectionAccessFilter.check(Mockito.<Class<?>>any())).thenReturn(FilterResult.ALLOW);

    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();
    reflectionFilters.add(reflectionAccessFilter);
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(new HashMap<>(), true, reflectionFilters);

    FieldNamingStrategy fieldNamingPolicy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory =
        new JsonAdapterAnnotationTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>()));
    ReflectiveTypeAdapterFactory reflectiveTypeAdapterFactory =
        new ReflectiveTypeAdapterFactory(
            constructorConstructor,
            fieldNamingPolicy,
            Excluder.DEFAULT,
            jsonAdapterFactory,
            new ArrayList<>());
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> type2 = TypeToken.get(type);

    // Act
    TypeAdapter<Object> actualCreateResult = reflectiveTypeAdapterFactory.create(gson, type2);

    // Assert
    verify(reflectionAccessFilter).check(isA(Class.class));
    assertEquals("{}", actualCreateResult.toJson("Value"));
  }

  /**
   * Test {@link ReflectiveTypeAdapterFactory#create(Gson, TypeToken)}.
   *
   * <ul>
   *   <li>Then return toJson {@code Value} is {@code {}}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectiveTypeAdapterFactory#create(Gson, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapter ReflectiveTypeAdapterFactory.create(Gson, TypeToken)"})
  public void testCreate_thenReturnToJsonValueIsLeftCurlyBracketRightCurlyBracket() {
    // Arrange
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(instanceCreators, true, new ArrayList<>());

    FieldNamingStrategy fieldNamingPolicy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators2 = new HashMap<>();
    JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory =
        new JsonAdapterAnnotationTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators2, true, new ArrayList<>()));
    ReflectiveTypeAdapterFactory reflectiveTypeAdapterFactory =
        new ReflectiveTypeAdapterFactory(
            constructorConstructor,
            fieldNamingPolicy,
            Excluder.DEFAULT,
            jsonAdapterFactory,
            new ArrayList<>());
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> type2 = TypeToken.get(type);

    // Act and Assert
    assertEquals("{}", reflectiveTypeAdapterFactory.create(gson, type2).toJson("Value"));
  }
}
