package com.google.gson.internal.bind;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonIOException;
import com.google.gson.ReflectionAccessFilter;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ReflectiveTypeAdapterFactoryDiffblueTest {
  /**
   * Test {@link ReflectiveTypeAdapterFactory#create(Gson, TypeToken)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link ReflectionAccessFilter#BLOCK_ALL_ANDROID}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectiveTypeAdapterFactory#create(Gson, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "com.google.gson.TypeAdapter ReflectiveTypeAdapterFactory.create(Gson, TypeToken)"
  })
  public void testCreate_givenArrayListAddBlock_all_android() {
    // Arrange
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();
    reflectionFilters.add(ReflectionAccessFilter.BLOCK_ALL_ANDROID);
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(new HashMap<>(), true, reflectionFilters);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory =
        new JsonAdapterAnnotationTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>()));

    ReflectiveTypeAdapterFactory reflectiveTypeAdapterFactory =
        new ReflectiveTypeAdapterFactory(
            constructorConstructor,
            FieldNamingPolicy.IDENTITY,
            Excluder.DEFAULT,
            jsonAdapterFactory,
            new ArrayList<>());
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> type2 = TypeToken.get(type);

    // Act and Assert
    assertEquals("{}", reflectiveTypeAdapterFactory.create(gson, type2).toJson("Value"));
  }

  /**
   * Test {@link ReflectiveTypeAdapterFactory#create(Gson, TypeToken)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link ReflectionAccessFilter#BLOCK_ALL_ANDROID}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectiveTypeAdapterFactory#create(Gson, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "com.google.gson.TypeAdapter ReflectiveTypeAdapterFactory.create(Gson, TypeToken)"
  })
  public void testCreate_givenArrayListAddBlock_all_android2() {
    // Arrange
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();
    reflectionFilters.add(ReflectionAccessFilter.BLOCK_ALL_ANDROID);
    reflectionFilters.add(ReflectionAccessFilter.BLOCK_ALL_ANDROID);
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(new HashMap<>(), true, reflectionFilters);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory =
        new JsonAdapterAnnotationTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>()));

    ReflectiveTypeAdapterFactory reflectiveTypeAdapterFactory =
        new ReflectiveTypeAdapterFactory(
            constructorConstructor,
            FieldNamingPolicy.IDENTITY,
            Excluder.DEFAULT,
            jsonAdapterFactory,
            new ArrayList<>());
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> type2 = TypeToken.get(type);

    // Act and Assert
    assertEquals("{}", reflectiveTypeAdapterFactory.create(gson, type2).toJson("Value"));
  }

  /**
   * Test {@link ReflectiveTypeAdapterFactory#create(Gson, TypeToken)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link ReflectionAccessFilter#BLOCK_ALL_ANDROID}.
   *   <li>Then throw {@link JsonIOException}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectiveTypeAdapterFactory#create(Gson, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "com.google.gson.TypeAdapter ReflectiveTypeAdapterFactory.create(Gson, TypeToken)"
  })
  public void testCreate_givenArrayListAddBlock_all_android_thenThrowJsonIOException() {
    // Arrange
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();
    reflectionFilters.add(ReflectionAccessFilter.BLOCK_ALL_ANDROID);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(instanceCreators, true, new ArrayList<>());
    HashMap<Type, InstanceCreator<?>> instanceCreators2 = new HashMap<>();

    ReflectiveTypeAdapterFactory reflectiveTypeAdapterFactory =
        new ReflectiveTypeAdapterFactory(
            constructorConstructor,
            FieldNamingPolicy.IDENTITY,
            Excluder.DEFAULT,
            new JsonAdapterAnnotationTypeAdapterFactory(
                new ConstructorConstructor(instanceCreators2, true, new ArrayList<>())),
            reflectionFilters);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> type2 = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonIOException.class, () -> reflectiveTypeAdapterFactory.create(gson, type2));
  }

  /**
   * Test {@link ReflectiveTypeAdapterFactory#create(Gson, TypeToken)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link ReflectionAccessFilter#BLOCK_ALL_JAVA}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectiveTypeAdapterFactory#create(Gson, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "com.google.gson.TypeAdapter ReflectiveTypeAdapterFactory.create(Gson, TypeToken)"
  })
  public void testCreate_givenArrayListAddBlock_all_java() {
    // Arrange
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();
    reflectionFilters.add(ReflectionAccessFilter.BLOCK_ALL_JAVA);
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(new HashMap<>(), true, reflectionFilters);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory =
        new JsonAdapterAnnotationTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>()));

    ReflectiveTypeAdapterFactory reflectiveTypeAdapterFactory =
        new ReflectiveTypeAdapterFactory(
            constructorConstructor,
            FieldNamingPolicy.IDENTITY,
            Excluder.DEFAULT,
            jsonAdapterFactory,
            new ArrayList<>());
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> type2 = TypeToken.get(type);

    // Act and Assert
    assertEquals("{}", reflectiveTypeAdapterFactory.create(gson, type2).toJson("Value"));
  }

  /**
   * Test {@link ReflectiveTypeAdapterFactory#create(Gson, TypeToken)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link
   *       ReflectionAccessFilter#BLOCK_ALL_PLATFORM}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectiveTypeAdapterFactory#create(Gson, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "com.google.gson.TypeAdapter ReflectiveTypeAdapterFactory.create(Gson, TypeToken)"
  })
  public void testCreate_givenArrayListAddBlock_all_platform() {
    // Arrange
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();
    reflectionFilters.add(ReflectionAccessFilter.BLOCK_ALL_PLATFORM);
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(new HashMap<>(), true, reflectionFilters);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory =
        new JsonAdapterAnnotationTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>()));

    ReflectiveTypeAdapterFactory reflectiveTypeAdapterFactory =
        new ReflectiveTypeAdapterFactory(
            constructorConstructor,
            FieldNamingPolicy.IDENTITY,
            Excluder.DEFAULT,
            jsonAdapterFactory,
            new ArrayList<>());
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> type2 = TypeToken.get(type);

    // Act and Assert
    assertEquals("{}", reflectiveTypeAdapterFactory.create(gson, type2).toJson("Value"));
  }

  /**
   * Test {@link ReflectiveTypeAdapterFactory#create(Gson, TypeToken)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link
   *       ReflectionAccessFilter#BLOCK_INACCESSIBLE_JAVA}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectiveTypeAdapterFactory#create(Gson, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "com.google.gson.TypeAdapter ReflectiveTypeAdapterFactory.create(Gson, TypeToken)"
  })
  public void testCreate_givenArrayListAddBlock_inaccessible_java() {
    // Arrange
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();
    reflectionFilters.add(ReflectionAccessFilter.BLOCK_INACCESSIBLE_JAVA);
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(new HashMap<>(), true, reflectionFilters);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory =
        new JsonAdapterAnnotationTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>()));

    ReflectiveTypeAdapterFactory reflectiveTypeAdapterFactory =
        new ReflectiveTypeAdapterFactory(
            constructorConstructor,
            FieldNamingPolicy.IDENTITY,
            Excluder.DEFAULT,
            jsonAdapterFactory,
            new ArrayList<>());
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> type2 = TypeToken.get(type);

    // Act and Assert
    assertEquals("{}", reflectiveTypeAdapterFactory.create(gson, type2).toJson("Value"));
  }

  /**
   * Test {@link ReflectiveTypeAdapterFactory#create(Gson, TypeToken)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link
   *       ReflectionAccessFilter#BLOCK_INACCESSIBLE_JAVA}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectiveTypeAdapterFactory#create(Gson, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "com.google.gson.TypeAdapter ReflectiveTypeAdapterFactory.create(Gson, TypeToken)"
  })
  public void testCreate_givenArrayListAddBlock_inaccessible_java2() {
    // Arrange
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();
    reflectionFilters.add(ReflectionAccessFilter.BLOCK_INACCESSIBLE_JAVA);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(instanceCreators, true, new ArrayList<>());
    HashMap<Type, InstanceCreator<?>> instanceCreators2 = new HashMap<>();

    ReflectiveTypeAdapterFactory reflectiveTypeAdapterFactory =
        new ReflectiveTypeAdapterFactory(
            constructorConstructor,
            FieldNamingPolicy.IDENTITY,
            Excluder.DEFAULT,
            new JsonAdapterAnnotationTypeAdapterFactory(
                new ConstructorConstructor(instanceCreators2, true, new ArrayList<>())),
            reflectionFilters);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> type2 = TypeToken.get(type);

    // Act and Assert
    assertEquals("{}", reflectiveTypeAdapterFactory.create(gson, type2).toJson("Value"));
  }

  /**
   * Test {@link ReflectiveTypeAdapterFactory#create(Gson, TypeToken)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} All is {@link HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectiveTypeAdapterFactory#create(Gson, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "com.google.gson.TypeAdapter ReflectiveTypeAdapterFactory.create(Gson, TypeToken)"
  })
  public void testCreate_givenHashMapAllIsHashMap() {
    // Arrange
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    instanceCreators.putAll(new HashMap<>());
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(instanceCreators, true, new ArrayList<>());
    HashMap<Type, InstanceCreator<?>> instanceCreators2 = new HashMap<>();
    JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory =
        new JsonAdapterAnnotationTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators2, true, new ArrayList<>()));

    ReflectiveTypeAdapterFactory reflectiveTypeAdapterFactory =
        new ReflectiveTypeAdapterFactory(
            constructorConstructor,
            FieldNamingPolicy.IDENTITY,
            Excluder.DEFAULT,
            jsonAdapterFactory,
            new ArrayList<>());
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> type2 = TypeToken.get(type);

    // Act and Assert
    assertEquals("{}", reflectiveTypeAdapterFactory.create(gson, type2).toJson("Value"));
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
  @MethodsUnderTest({
    "com.google.gson.TypeAdapter ReflectiveTypeAdapterFactory.create(Gson, TypeToken)"
  })
  public void testCreate_thenReturnToJsonValueIsLeftCurlyBracketRightCurlyBracket() {
    // Arrange
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(instanceCreators, true, new ArrayList<>());
    HashMap<Type, InstanceCreator<?>> instanceCreators2 = new HashMap<>();
    JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory =
        new JsonAdapterAnnotationTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators2, true, new ArrayList<>()));

    ReflectiveTypeAdapterFactory reflectiveTypeAdapterFactory =
        new ReflectiveTypeAdapterFactory(
            constructorConstructor,
            FieldNamingPolicy.IDENTITY,
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
