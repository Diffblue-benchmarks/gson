package com.google.gson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.Gson.FutureTypeAdapter;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.internal.bind.ArrayTypeAdapter;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.internal.bind.ObjectTypeAdapter;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.google.gson.internal.reflect.ReflectionHelperTestFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.EOFException;
import java.io.FileDescriptor;
import java.io.FileReader;
import java.io.IOException;
import java.io.PipedWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class GsonDiffblueTest {
  /**
   * Test FutureTypeAdapter {@link FutureTypeAdapter#getSerializationDelegate()}.
   *
   * <ul>
   *   <li>Then return {@link FutureTypeAdapter}.
   * </ul>
   *
   * <p>Method under test: {@link FutureTypeAdapter#getSerializationDelegate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapter FutureTypeAdapter.getSerializationDelegate()"})
  public void testFutureTypeAdapterGetSerializationDelegate_thenReturnFutureTypeAdapter() {
    // Arrange
    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    FutureTypeAdapter<Object> typeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    TypeAdapter<Object> actualSerializationDelegate = futureTypeAdapter.getSerializationDelegate();

    // Assert
    assertTrue(actualSerializationDelegate instanceof FutureTypeAdapter);
    assertSame(typeAdapter, actualSerializationDelegate);
  }

  /**
   * Test FutureTypeAdapter {@link FutureTypeAdapter#getSerializationDelegate()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link FutureTypeAdapter#getSerializationDelegate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapter FutureTypeAdapter.getSerializationDelegate()"})
  public void testFutureTypeAdapterGetSerializationDelegate_thenThrowIllegalStateException() {
    // Arrange
    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> futureTypeAdapter.getSerializationDelegate());
  }

  /**
   * Test FutureTypeAdapter {@link FutureTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@link FutureTypeAdapter} (default constructor) Delegate is {@link
   *       FutureTypeAdapter} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link FutureTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object FutureTypeAdapter.read(JsonReader)"})
  public void testFutureTypeAdapterRead_givenFutureTypeAdapterDelegateIsFutureTypeAdapter()
      throws IOException {
    // Arrange
    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(new FutureTypeAdapter<>());

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> futureTypeAdapter.read(new JsonReader(new StringReader("foo"))));
  }

  /**
   * Test FutureTypeAdapter {@link FutureTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@link FutureTypeAdapter} (default constructor).
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link FutureTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object FutureTypeAdapter.read(JsonReader)"})
  public void testFutureTypeAdapterRead_givenFutureTypeAdapter_thenThrowIllegalStateException()
      throws IOException {
    // Arrange
    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> futureTypeAdapter.read(new JsonReader(new StringReader("foo"))));
  }

  /**
   * Test FutureTypeAdapter {@link FutureTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Then return createPublicField.
   * </ul>
   *
   * <p>Method under test: {@link FutureTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object FutureTypeAdapter.read(JsonReader)"})
  public void testFutureTypeAdapterRead_thenReturnCreatePublicField()
      throws IOException, NoSuchFieldException {
    // Arrange
    ObjectTypeAdapter typeAdapter = mock(ObjectTypeAdapter.class);
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();
    when(typeAdapter.read(Mockito.<JsonReader>any())).thenReturn(createPublicFieldResult);

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);

    // Act
    Object actualReadResult = futureTypeAdapter.read(new JsonReader(new StringReader("foo")));

    // Assert
    verify(typeAdapter).read(isA(JsonReader.class));
    assertSame(createPublicFieldResult, actualReadResult);
  }

  /**
   * Test FutureTypeAdapter {@link FutureTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link FutureTypeAdapter} (default constructor) Delegate is {@link
   *       FutureTypeAdapter} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link FutureTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FutureTypeAdapter.write(JsonWriter, Object)"})
  public void testFutureTypeAdapterWrite_givenFutureTypeAdapterDelegateIsFutureTypeAdapter()
      throws IOException, NoSuchFieldException {
    // Arrange
    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(new FutureTypeAdapter<>());
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> futureTypeAdapter.write(out, ReflectionHelperTestFactory.createPublicField()));
  }

  /**
   * Test FutureTypeAdapter {@link FutureTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link FutureTypeAdapter} (default constructor).
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link FutureTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FutureTypeAdapter.write(JsonWriter, Object)"})
  public void testFutureTypeAdapterWrite_givenFutureTypeAdapter_thenThrowIllegalStateException()
      throws IOException, NoSuchFieldException {
    // Arrange
    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> futureTypeAdapter.write(out, ReflectionHelperTestFactory.createPublicField()));
  }

  /**
   * Test FutureTypeAdapter {@link FutureTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link ObjectTypeAdapter} {@link ObjectTypeAdapter#write(JsonWriter, Object)} does
   *       nothing.
   *   <li>Then calls {@link ObjectTypeAdapter#write(JsonWriter, Object)}.
   * </ul>
   *
   * <p>Method under test: {@link FutureTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FutureTypeAdapter.write(JsonWriter, Object)"})
  public void testFutureTypeAdapterWrite_givenObjectTypeAdapterWriteDoesNothing_thenCallsWrite()
      throws IOException, NoSuchFieldException {
    // Arrange
    ObjectTypeAdapter typeAdapter = mock(ObjectTypeAdapter.class);
    doNothing().when(typeAdapter).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());

    FutureTypeAdapter<Object> futureTypeAdapter = new FutureTypeAdapter<>();
    futureTypeAdapter.setDelegate(typeAdapter);
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    futureTypeAdapter.write(out, ReflectionHelperTestFactory.createPublicField());

    // Assert
    verify(typeAdapter).write(isA(JsonWriter.class), isA(Object.class));
  }

  /**
   * Test {@link Gson#Gson()}.
   *
   * <p>Method under test: {@link Gson#Gson()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.<init>()"})
  public void testNewGson() {
    // Arrange and Act
    Gson actualGson = new Gson();

    // Assert
    assertNull(actualGson.strictness);
    assertNull(actualGson.datePattern);
    assertEquals(2, actualGson.dateStyle);
    assertEquals(2, actualGson.timeStyle);
    assertEquals(42, actualGson.factories.size());
    assertEquals(LongSerializationPolicy.DEFAULT, actualGson.longSerializationPolicy);
    assertFalse(actualGson.serializeNulls());
    assertFalse(actualGson.complexMapKeySerialization);
    assertFalse(actualGson.generateNonExecutableJson);
    assertFalse(actualGson.serializeNulls);
    assertFalse(actualGson.serializeSpecialFloatingPointValues);
    assertTrue(actualGson.htmlSafe());
    assertTrue(actualGson.builderFactories.isEmpty());
    assertTrue(actualGson.builderHierarchyFactories.isEmpty());
    assertTrue(actualGson.reflectionFilters.isEmpty());
    assertTrue(actualGson.instanceCreators.isEmpty());
    assertTrue(actualGson.htmlSafe);
    assertTrue(actualGson.useJdkUnsafe);
    Excluder expectedExcluderResult = actualGson.excluder;
    assertSame(expectedExcluderResult, actualGson.excluder());
    FieldNamingStrategy expectedFieldNamingStrategyResult = actualGson.fieldNamingStrategy;
    assertSame(expectedFieldNamingStrategyResult, actualGson.fieldNamingStrategy());
  }

  /**
   * Test {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean,
   * FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List,
   * List, List, ToNumberStrategy, ToNumberStrategy, List)}.
   *
   * <ul>
   *   <li>Given {@link ReflectionAccessFilter}.
   *   <li>Then {@link ArrayList#ArrayList()} size is one.
   * </ul>
   *
   * <p>Method under test: {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean,
   * boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy,
   * String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Gson.<init>(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)"
  })
  public void testNewGson_givenReflectionAccessFilter_thenArrayListSizeIsOne() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();
    reflectionFilters.add(mock(ReflectionAccessFilter.class));

    // Act
    Gson actualGson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            reflectionFilters);

    // Assert
    assertEquals(1, reflectionFilters.size());
    assertEquals(1, actualGson.reflectionFilters.size());
    assertTrue(actualGson.builderFactories.isEmpty());
    assertTrue(actualGson.builderHierarchyFactories.isEmpty());
  }

  /**
   * Test {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean,
   * FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List,
   * List, List, ToNumberStrategy, ToNumberStrategy, List)}.
   *
   * <ul>
   *   <li>Given {@link ReflectionAccessFilter}.
   *   <li>Then {@link ArrayList#ArrayList()} size is two.
   * </ul>
   *
   * <p>Method under test: {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean,
   * boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy,
   * String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Gson.<init>(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)"
  })
  public void testNewGson_givenReflectionAccessFilter_thenArrayListSizeIsTwo() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();
    reflectionFilters.add(mock(ReflectionAccessFilter.class));
    reflectionFilters.add(mock(ReflectionAccessFilter.class));

    // Act
    Gson actualGson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            reflectionFilters);

    // Assert
    assertEquals(2, reflectionFilters.size());
    assertEquals(2, actualGson.reflectionFilters.size());
    assertTrue(actualGson.builderFactories.isEmpty());
    assertTrue(actualGson.builderHierarchyFactories.isEmpty());
  }

  /**
   * Test {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean,
   * FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List,
   * List, List, ToNumberStrategy, ToNumberStrategy, List)}.
   *
   * <ul>
   *   <li>Given {@link TypeAdapterFactory}.
   *   <li>Then return {@link Gson#builderFactories} size is one.
   * </ul>
   *
   * <p>Method under test: {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean,
   * boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy,
   * String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Gson.<init>(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)"
  })
  public void testNewGson_givenTypeAdapterFactory_thenReturnBuilderFactoriesSizeIsOne() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();

    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    builderFactories.add(mock(TypeAdapterFactory.class));
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();

    // Act
    Gson actualGson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            reflectionFilters);

    // Assert
    assertEquals(1, actualGson.builderFactories.size());
    assertTrue(reflectionFilters.isEmpty());
    assertTrue(actualGson.builderHierarchyFactories.isEmpty());
    assertTrue(actualGson.reflectionFilters.isEmpty());
  }

  /**
   * Test {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean,
   * FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List,
   * List, List, ToNumberStrategy, ToNumberStrategy, List)}.
   *
   * <ul>
   *   <li>Given {@link TypeAdapterFactory}.
   *   <li>Then return {@link Gson#builderFactories} size is two.
   * </ul>
   *
   * <p>Method under test: {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean,
   * boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy,
   * String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Gson.<init>(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)"
  })
  public void testNewGson_givenTypeAdapterFactory_thenReturnBuilderFactoriesSizeIsTwo() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();

    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    builderFactories.add(mock(TypeAdapterFactory.class));
    builderFactories.add(mock(TypeAdapterFactory.class));
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();

    // Act
    Gson actualGson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            reflectionFilters);

    // Assert
    assertEquals(2, actualGson.builderFactories.size());
    assertTrue(reflectionFilters.isEmpty());
    assertTrue(actualGson.builderHierarchyFactories.isEmpty());
    assertTrue(actualGson.reflectionFilters.isEmpty());
  }

  /**
   * Test {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean,
   * FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List,
   * List, List, ToNumberStrategy, ToNumberStrategy, List)}.
   *
   * <ul>
   *   <li>Given {@link TypeAdapterFactory}.
   *   <li>Then return {@link Gson#builderHierarchyFactories} size is one.
   * </ul>
   *
   * <p>Method under test: {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean,
   * boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy,
   * String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Gson.<init>(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)"
  })
  public void testNewGson_givenTypeAdapterFactory_thenReturnBuilderHierarchyFactoriesSizeIsOne() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();

    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    builderHierarchyFactories.add(mock(TypeAdapterFactory.class));
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();

    // Act
    Gson actualGson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            reflectionFilters);

    // Assert
    assertEquals(1, actualGson.builderHierarchyFactories.size());
    assertTrue(reflectionFilters.isEmpty());
    assertTrue(actualGson.builderFactories.isEmpty());
    assertTrue(actualGson.reflectionFilters.isEmpty());
  }

  /**
   * Test {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean,
   * FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List,
   * List, List, ToNumberStrategy, ToNumberStrategy, List)}.
   *
   * <ul>
   *   <li>Given {@link TypeAdapterFactory}.
   *   <li>Then return {@link Gson#builderHierarchyFactories} size is two.
   * </ul>
   *
   * <p>Method under test: {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean,
   * boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy,
   * String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Gson.<init>(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)"
  })
  public void testNewGson_givenTypeAdapterFactory_thenReturnBuilderHierarchyFactoriesSizeIsTwo() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();

    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    builderHierarchyFactories.add(mock(TypeAdapterFactory.class));
    builderHierarchyFactories.add(mock(TypeAdapterFactory.class));
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();

    // Act
    Gson actualGson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            reflectionFilters);

    // Assert
    assertEquals(2, actualGson.builderHierarchyFactories.size());
    assertTrue(reflectionFilters.isEmpty());
    assertTrue(actualGson.builderFactories.isEmpty());
    assertTrue(actualGson.reflectionFilters.isEmpty());
  }

  /**
   * Test {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean,
   * FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List,
   * List, List, ToNumberStrategy, ToNumberStrategy, List)}.
   *
   * <ul>
   *   <li>Given {@link TypeAdapterFactory}.
   *   <li>Then return {@link Gson#factories} size is forty-four.
   * </ul>
   *
   * <p>Method under test: {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean,
   * boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy,
   * String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Gson.<init>(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)"
  })
  public void testNewGson_givenTypeAdapterFactory_thenReturnFactoriesSizeIsFortyFour() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(mock(TypeAdapterFactory.class));
    factoriesToBeAdded.add(mock(TypeAdapterFactory.class));
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();

    // Act
    Gson actualGson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            reflectionFilters);

    // Assert
    List<TypeAdapterFactory> typeAdapterFactoryList = actualGson.factories;
    assertEquals(44, typeAdapterFactoryList.size());
    assertTrue(typeAdapterFactoryList.get(41) instanceof JsonAdapterAnnotationTypeAdapterFactory);
    assertTrue(typeAdapterFactoryList.get(43) instanceof ReflectiveTypeAdapterFactory);
    assertEquals(actualGson.builderFactories, reflectionFilters);
  }

  /**
   * Test {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean,
   * FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List,
   * List, List, ToNumberStrategy, ToNumberStrategy, List)}.
   *
   * <ul>
   *   <li>Given {@link TypeAdapterFactory}.
   *   <li>Then return {@link Gson#factories} size is forty-three.
   * </ul>
   *
   * <p>Method under test: {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean,
   * boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy,
   * String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Gson.<init>(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)"
  })
  public void testNewGson_givenTypeAdapterFactory_thenReturnFactoriesSizeIsFortyThree() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(mock(TypeAdapterFactory.class));
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();

    // Act
    Gson actualGson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            reflectionFilters);

    // Assert
    List<TypeAdapterFactory> typeAdapterFactoryList = actualGson.factories;
    assertEquals(43, typeAdapterFactoryList.size());
    assertTrue(typeAdapterFactoryList.get(40) instanceof JsonAdapterAnnotationTypeAdapterFactory);
    assertTrue(typeAdapterFactoryList.get(42) instanceof ReflectiveTypeAdapterFactory);
    assertEquals(actualGson.builderFactories, reflectionFilters);
  }

  /**
   * Test {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean,
   * FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List,
   * List, List, ToNumberStrategy, ToNumberStrategy, List)}.
   *
   * <ul>
   *   <li>When {@code DEFAULT}.
   *   <li>Then {@link ArrayList#ArrayList()} Empty.
   * </ul>
   *
   * <p>Method under test: {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean,
   * boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy,
   * String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Gson.<init>(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)"
  })
  public void testNewGson_whenDefault_thenArrayListEmpty() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();

    // Act
    Gson actualGson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            reflectionFilters);

    // Assert
    assertTrue(reflectionFilters.isEmpty());
    assertTrue(actualGson.builderFactories.isEmpty());
    assertTrue(actualGson.builderHierarchyFactories.isEmpty());
    assertTrue(actualGson.reflectionFilters.isEmpty());
  }

  /**
   * Test {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean,
   * FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List,
   * List, List, ToNumberStrategy, ToNumberStrategy, List)}.
   *
   * <ul>
   *   <li>When {@code false}.
   *   <li>Then return not {@link Gson#serializeSpecialFloatingPointValues}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean,
   * boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy,
   * String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Gson.<init>(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)"
  })
  public void testNewGson_whenFalse_thenReturnNotSerializeSpecialFloatingPointValues() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();

    // Act
    Gson actualGson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            false,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            ToNumberPolicy.DOUBLE,
            ToNumberPolicy.LAZILY_PARSED_NUMBER,
            reflectionFilters);

    // Assert
    List<TypeAdapterFactory> typeAdapterFactoryList = actualGson.factories;
    assertEquals(42, typeAdapterFactoryList.size());
    assertFalse(actualGson.serializeSpecialFloatingPointValues);
    assertEquals(actualGson.builderFactories, reflectionFilters);
    Excluder excluder = actualGson.excluder;
    assertSame(excluder, actualGson.excluder());
    assertSame(excluder, typeAdapterFactoryList.get(2));
    FieldNamingStrategy expectedFieldNamingStrategyResult = actualGson.fieldNamingStrategy;
    assertSame(expectedFieldNamingStrategyResult, actualGson.fieldNamingStrategy());
  }

  /**
   * Test {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean,
   * FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List,
   * List, List, ToNumberStrategy, ToNumberStrategy, List)}.
   *
   * <ul>
   *   <li>When {@code STRING}.
   *   <li>Then return {@link Gson#longSerializationPolicy} is {@code STRING}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#Gson(Excluder, FieldNamingStrategy, Map, boolean, boolean,
   * boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy,
   * String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Gson.<init>(Excluder, FieldNamingStrategy, Map, boolean, boolean, boolean, boolean, FormattingStyle, Strictness, boolean, boolean, LongSerializationPolicy, String, int, int, List, List, List, ToNumberStrategy, ToNumberStrategy, List)"
  })
  public void testNewGson_whenString_thenReturnLongSerializationPolicyIsString() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);
    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();

    // Act
    Gson actualGson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.STRING,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            reflectionFilters);

    // Assert
    List<TypeAdapterFactory> typeAdapterFactoryList = actualGson.factories;
    assertEquals(42, typeAdapterFactoryList.size());
    assertEquals(LongSerializationPolicy.STRING, actualGson.longSerializationPolicy);
    assertEquals(actualGson.builderFactories, reflectionFilters);
    Excluder excluder = actualGson.excluder;
    assertSame(excluder, actualGson.excluder());
    assertSame(excluder, typeAdapterFactoryList.get(2));
    FieldNamingStrategy expectedFieldNamingStrategyResult = actualGson.fieldNamingStrategy;
    assertSame(expectedFieldNamingStrategyResult, actualGson.fieldNamingStrategy());
  }

  /**
   * Test {@link Gson#newBuilder()}.
   *
   * <p>Method under test: {@link Gson#newBuilder()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"GsonBuilder Gson.newBuilder()"})
  public void testNewBuilder() {
    // Arrange
    Gson gson = new Gson();

    // Act and Assert
    Gson createResult = gson.newBuilder().create();
    assertNull(createResult.strictness);
    assertNull(createResult.datePattern);
    assertEquals(2, createResult.dateStyle);
    assertEquals(2, createResult.timeStyle);
    assertEquals(42, createResult.factories.size());
    assertEquals(LongSerializationPolicy.DEFAULT, createResult.longSerializationPolicy);
    assertFalse(createResult.serializeNulls());
    assertFalse(createResult.complexMapKeySerialization);
    assertFalse(createResult.generateNonExecutableJson);
    assertFalse(createResult.serializeNulls);
    assertFalse(createResult.serializeSpecialFloatingPointValues);
    assertTrue(createResult.htmlSafe());
    assertTrue(createResult.builderFactories.isEmpty());
    assertTrue(createResult.builderHierarchyFactories.isEmpty());
    assertTrue(createResult.reflectionFilters.isEmpty());
    assertTrue(createResult.instanceCreators.isEmpty());
    assertTrue(createResult.htmlSafe);
    assertTrue(createResult.useJdkUnsafe);
    Excluder excluder = gson.excluder;
    assertSame(excluder, createResult.excluder());
    assertSame(excluder, createResult.excluder);
    FieldNamingStrategy fieldNamingStrategy = gson.fieldNamingStrategy;
    assertSame(fieldNamingStrategy, createResult.fieldNamingStrategy());
    assertSame(fieldNamingStrategy, createResult.fieldNamingStrategy);
    assertSame(gson.formattingStyle, createResult.formattingStyle);
    assertSame(gson.numberToNumberStrategy, createResult.numberToNumberStrategy);
    assertSame(gson.objectToNumberStrategy, createResult.objectToNumberStrategy);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Gson#toString()}
   *   <li>{@link Gson#excluder()}
   *   <li>{@link Gson#fieldNamingStrategy()}
   *   <li>{@link Gson#htmlSafe()}
   *   <li>{@link Gson#serializeNulls()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Excluder Gson.excluder()",
    "FieldNamingStrategy Gson.fieldNamingStrategy()",
    "boolean Gson.htmlSafe()",
    "boolean Gson.serializeNulls()",
    "String Gson.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    Gson gson = new Gson();

    // Act
    gson.toString();
    Excluder actualExcluderResult = gson.excluder();
    FieldNamingStrategy actualFieldNamingStrategyResult = gson.fieldNamingStrategy();
    boolean actualHtmlSafeResult = gson.htmlSafe();

    // Assert
    assertFalse(gson.serializeNulls());
    assertTrue(actualHtmlSafeResult);
    assertSame(gson.fieldNamingStrategy, actualFieldNamingStrategyResult);
    assertSame(Excluder.DEFAULT, actualExcluderResult);
  }

  /**
   * Test {@link Gson#checkValidFloatingPoint(double)}.
   *
   * <ul>
   *   <li>When {@link Double#NaN}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#checkValidFloatingPoint(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.checkValidFloatingPoint(double)"})
  public void testCheckValidFloatingPoint_whenNaN_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> Gson.checkValidFloatingPoint(Double.NaN));
  }

  /**
   * Test {@link Gson#checkValidFloatingPoint(double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link Gson#checkValidFloatingPoint(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.checkValidFloatingPoint(double)"})
  public void testCheckValidFloatingPoint_whenTen_thenDoesNotThrow() {
    // Arrange, Act and Assert
    Gson.checkValidFloatingPoint(10.0d);
  }

  /**
   * Test {@link Gson#getAdapter(Class)} with {@code Class}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code Long}.
   *   <li>Then return toJson {@code null} is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#getAdapter(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapter Gson.getAdapter(Class)"})
  public void testGetAdapterWithClass_givenGson_whenJavaLangLong_thenReturnToJsonNullIsNull() {
    // Arrange
    Gson gson = new Gson();
    Class<Long> type = Long.class;

    // Act and Assert
    assertEquals("null", gson.getAdapter(type).toJson(null));
  }

  /**
   * Test {@link Gson#getAdapter(Class)} with {@code Class}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code Object}.
   *   <li>Then return {@link ObjectTypeAdapter}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#getAdapter(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapter Gson.getAdapter(Class)"})
  public void testGetAdapterWithClass_givenGson_whenJavaLangObject_thenReturnObjectTypeAdapter() {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;

    // Act and Assert
    assertTrue(gson.getAdapter(type) instanceof ObjectTypeAdapter);
  }

  /**
   * Test {@link Gson#getAdapter(Class)} with {@code Class}.
   *
   * <ul>
   *   <li>Then return toJson {@code null} is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#getAdapter(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapter Gson.getAdapter(Class)"})
  public void testGetAdapterWithClass_thenReturnToJsonNullIsNull() {
    // Arrange
    Gson gson = new Gson();
    Class<TypeAdapterFactory> type = TypeAdapterFactory.class;

    // Act and Assert
    assertEquals("null", gson.getAdapter(type).toJson(null));
  }

  /**
   * Test {@link Gson#getAdapter(Class)} with {@code Class}.
   *
   * <ul>
   *   <li>When {@code LazilyParsedNumber}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#getAdapter(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapter Gson.getAdapter(Class)"})
  public void testGetAdapterWithClass_whenComGoogleGsonInternalLazilyParsedNumber() {
    // Arrange
    Gson gson = new Gson();
    Class<LazilyParsedNumber> type = LazilyParsedNumber.class;

    // Act and Assert
    assertEquals("null", gson.getAdapter(type).toJson(null));
  }

  /**
   * Test {@link Gson#getAdapter(TypeToken)} with {@code TypeToken}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return {@link ObjectTypeAdapter}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#getAdapter(TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapter Gson.getAdapter(TypeToken)"})
  public void testGetAdapterWithTypeToken_whenJavaLangObject_thenReturnObjectTypeAdapter() {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> type2 = TypeToken.get(type);

    // Act and Assert
    assertTrue(gson.getAdapter(type2) instanceof ObjectTypeAdapter);
  }

  /**
   * Test {@link Gson#getDelegateAdapter(TypeAdapterFactory, TypeToken)}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@link Excluder#DEFAULT}.
   *   <li>Then return toJson {@code null} is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#getDelegateAdapter(TypeAdapterFactory, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapter Gson.getDelegateAdapter(TypeAdapterFactory, TypeToken)"})
  public void testGetDelegateAdapter_givenGson_whenDefault_thenReturnToJsonNullIsNull() {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> type2 = TypeToken.get(type);

    // Act and Assert
    assertEquals("null", gson.getDelegateAdapter(Excluder.DEFAULT, type2).toJson(null));
  }

  /**
   * Test {@link Gson#getDelegateAdapter(TypeAdapterFactory, TypeToken)}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code Object}.
   *   <li>Then return {@link ObjectTypeAdapter}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#getDelegateAdapter(TypeAdapterFactory, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapter Gson.getDelegateAdapter(TypeAdapterFactory, TypeToken)"})
  public void testGetDelegateAdapter_givenGson_whenJavaLangObject_thenReturnObjectTypeAdapter() {
    // Arrange
    Gson gson = new Gson();
    TypeAdapterFactory skipPast = mock(TypeAdapterFactory.class);
    Class<Object> type = Object.class;
    TypeToken<Object> type2 = TypeToken.get(type);

    // Act and Assert
    assertTrue(gson.getDelegateAdapter(skipPast, type2) instanceof ObjectTypeAdapter);
  }

  /**
   * Test {@link Gson#toJsonTree(Object, Type)} with {@code src}, {@code typeOfSrc}.
   *
   * <p>Method under test: {@link Gson#toJsonTree(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Gson.toJsonTree(Object, Type)"})
  public void testToJsonTreeWithSrcTypeOfSrc() throws IOException, NoSuchFieldException {
    // Arrange
    FutureTypeAdapter<Object> futureTypeAdapter = mock(FutureTypeAdapter.class);
    doNothing().when(futureTypeAdapter).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());

    Excluder excluder = mock(Excluder.class);
    when(excluder.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(futureTypeAdapter);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            excluder,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            null,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();

    // Act
    JsonElement actualToJsonTreeResult =
        gson.toJsonTree(createPublicFieldResult, GsonBuilderTestFactory.createType());

    // Assert
    verify(futureTypeAdapter).write(isA(JsonWriter.class), isA(Object.class));
    verify(excluder).create(isA(Gson.class), isA(TypeToken.class));
    assertSame(((JsonNull) actualToJsonTreeResult).INSTANCE, actualToJsonTreeResult);
  }

  /**
   * Test {@link Gson#toJsonTree(Object, Type)} with {@code src}, {@code typeOfSrc}.
   *
   * <ul>
   *   <li>Then return {@link JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJsonTree(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Gson.toJsonTree(Object, Type)"})
  public void testToJsonTreeWithSrcTypeOfSrc_thenReturnInstance()
      throws IOException, NoSuchFieldException {
    // Arrange
    FutureTypeAdapter<Object> futureTypeAdapter = mock(FutureTypeAdapter.class);
    doNothing().when(futureTypeAdapter).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());

    Excluder excluder = mock(Excluder.class);
    when(excluder.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(futureTypeAdapter);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            excluder,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();

    // Act
    JsonElement actualToJsonTreeResult =
        gson.toJsonTree(createPublicFieldResult, GsonBuilderTestFactory.createType());

    // Assert
    verify(futureTypeAdapter).write(isA(JsonWriter.class), isA(Object.class));
    verify(excluder).create(isA(Gson.class), isA(TypeToken.class));
    assertSame(((JsonNull) actualToJsonTreeResult).INSTANCE, actualToJsonTreeResult);
  }

  /**
   * Test {@link Gson#toJsonTree(Object, Type)} with {@code src}, {@code typeOfSrc}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJsonTree(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Gson.toJsonTree(Object, Type)"})
  public void testToJsonTreeWithSrcTypeOfSrc_thenThrowIllegalArgumentException()
      throws IOException, NoSuchFieldException {
    // Arrange
    FutureTypeAdapter<Object> futureTypeAdapter = mock(FutureTypeAdapter.class);
    doThrow(new IllegalArgumentException())
        .when(futureTypeAdapter)
        .write(Mockito.<JsonWriter>any(), Mockito.<Object>any());

    Excluder excluder = mock(Excluder.class);
    when(excluder.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(futureTypeAdapter);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            excluder,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();
    Class<Object> typeOfSrc = Object.class;

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> gson.toJsonTree(createPublicFieldResult, typeOfSrc));
    verify(futureTypeAdapter).write(isA(JsonWriter.class), isA(Object.class));
    verify(excluder).create(isA(Gson.class), isA(TypeToken.class));
  }

  /**
   * Test {@link Gson#toJsonTree(Object, Type)} with {@code src}, {@code typeOfSrc}.
   *
   * <ul>
   *   <li>Then throw {@link JsonIOException}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJsonTree(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Gson.toJsonTree(Object, Type)"})
  public void testToJsonTreeWithSrcTypeOfSrc_thenThrowJsonIOException()
      throws IOException, NoSuchFieldException {
    // Arrange
    FutureTypeAdapter<Object> futureTypeAdapter = mock(FutureTypeAdapter.class);
    doThrow(new IOException())
        .when(futureTypeAdapter)
        .write(Mockito.<JsonWriter>any(), Mockito.<Object>any());

    Excluder excluder = mock(Excluder.class);
    when(excluder.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(futureTypeAdapter);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            excluder,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();

    // Act and Assert
    assertThrows(
        JsonIOException.class,
        () -> gson.toJsonTree(createPublicFieldResult, GsonBuilderTestFactory.createType()));
    verify(futureTypeAdapter).write(isA(JsonWriter.class), isA(Object.class));
    verify(excluder).create(isA(Gson.class), isA(TypeToken.class));
  }

  /**
   * Test {@link Gson#toJsonTree(Object, Type)} with {@code src}, {@code typeOfSrc}.
   *
   * <ul>
   *   <li>Then throw {@link JsonIOException}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJsonTree(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Gson.toJsonTree(Object, Type)"})
  public void testToJsonTreeWithSrcTypeOfSrc_thenThrowJsonIOException2()
      throws IOException, NoSuchFieldException {
    // Arrange
    FutureTypeAdapter<Object> futureTypeAdapter = mock(FutureTypeAdapter.class);
    doThrow(new IOException())
        .when(futureTypeAdapter)
        .write(Mockito.<JsonWriter>any(), Mockito.<Object>any());

    Excluder excluder = mock(Excluder.class);
    when(excluder.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(futureTypeAdapter);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            excluder,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();
    Class<Object> typeOfSrc = Object.class;

    // Act and Assert
    assertThrows(JsonIOException.class, () -> gson.toJsonTree(createPublicFieldResult, typeOfSrc));
    verify(futureTypeAdapter).write(isA(JsonWriter.class), isA(Object.class));
    verify(excluder).create(isA(Gson.class), isA(TypeToken.class));
  }

  /**
   * Test {@link Gson#toJsonTree(Object, Type)} with {@code src}, {@code typeOfSrc}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return {@link JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJsonTree(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Gson.toJsonTree(Object, Type)"})
  public void testToJsonTreeWithSrcTypeOfSrc_whenJavaLangObject_thenReturnInstance()
      throws IOException, NoSuchFieldException {
    // Arrange
    FutureTypeAdapter<Object> futureTypeAdapter = mock(FutureTypeAdapter.class);
    doNothing().when(futureTypeAdapter).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());

    Excluder excluder = mock(Excluder.class);
    when(excluder.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(futureTypeAdapter);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            excluder,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();
    Class<Object> typeOfSrc = Object.class;

    // Act
    JsonElement actualToJsonTreeResult = gson.toJsonTree(createPublicFieldResult, typeOfSrc);

    // Assert
    verify(futureTypeAdapter).write(isA(JsonWriter.class), isA(Object.class));
    verify(excluder).create(isA(Gson.class), isA(TypeToken.class));
    assertSame(((JsonNull) actualToJsonTreeResult).INSTANCE, actualToJsonTreeResult);
  }

  /**
   * Test {@link Gson#toJsonTree(Object)} with {@code src}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When createPublicField.
   *   <li>Then return {@link JsonObject}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJsonTree(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Gson.toJsonTree(Object)"})
  public void testToJsonTreeWithSrc_givenGson_whenCreatePublicField_thenReturnJsonObject()
      throws NoSuchFieldException {
    // Arrange
    Gson gson = new Gson();

    // Act
    JsonElement actualToJsonTreeResult =
        gson.toJsonTree(ReflectionHelperTestFactory.createPublicField());

    // Assert
    assertTrue(actualToJsonTreeResult instanceof JsonObject);
    assertEquals(0, ((JsonObject) actualToJsonTreeResult).size());
    assertFalse(actualToJsonTreeResult.isJsonNull());
    assertTrue(actualToJsonTreeResult.isJsonObject());
    assertTrue(((JsonObject) actualToJsonTreeResult).isEmpty());
    JsonObject actualAsJsonObject = actualToJsonTreeResult.getAsJsonObject();
    assertSame(actualToJsonTreeResult, actualAsJsonObject);
  }

  /**
   * Test {@link Gson#toJsonTree(Object)} with {@code src}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code null}.
   *   <li>Then return {@link JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJsonTree(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Gson.toJsonTree(Object)"})
  public void testToJsonTreeWithSrc_givenGson_whenNull_thenReturnInstance() {
    // Arrange and Act
    JsonElement actualToJsonTreeResult = new Gson().toJsonTree(null);

    // Assert
    assertSame(((JsonNull) actualToJsonTreeResult).INSTANCE, actualToJsonTreeResult);
  }

  /**
   * Test {@link Gson#toJsonTree(Object)} with {@code src}.
   *
   * <ul>
   *   <li>Then return {@link JsonObject}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJsonTree(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement Gson.toJsonTree(Object)"})
  public void testToJsonTreeWithSrc_thenReturnJsonObject() throws NoSuchFieldException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());

    // Act
    JsonElement actualToJsonTreeResult =
        gson.toJsonTree(ReflectionHelperTestFactory.createPublicField());

    // Assert
    assertTrue(actualToJsonTreeResult instanceof JsonObject);
    assertEquals(0, ((JsonObject) actualToJsonTreeResult).size());
    assertFalse(actualToJsonTreeResult.isJsonNull());
    assertTrue(actualToJsonTreeResult.isJsonObject());
    assertTrue(((JsonObject) actualToJsonTreeResult).isEmpty());
    JsonObject actualAsJsonObject = actualToJsonTreeResult.getAsJsonObject();
    assertSame(actualToJsonTreeResult, actualAsJsonObject);
  }

  /**
   * Test {@link Gson#toJson(JsonElement)} with {@code JsonElement}.
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(JsonElement)"})
  public void testToJsonWithJsonElement() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            false,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());

    // Act and Assert
    assertEquals(
        "[\"singleElement\"]", gson.toJson(JsonArrayTestFactory.createJsonArrayWithOneElement()));
  }

  /**
   * Test {@link Gson#toJson(JsonElement)} with {@code JsonElement}.
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(JsonElement)"})
  public void testToJsonWithJsonElement2() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            false,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());

    // Act and Assert
    assertEquals(
        ")]}'\n[\"singleElement\"]",
        gson.toJson(JsonArrayTestFactory.createJsonArrayWithOneElement()));
  }

  /**
   * Test {@link Gson#toJson(JsonElement, Appendable)} with {@code JsonElement}, {@code Appendable}.
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, Appendable)"})
  public void testToJsonWithJsonElementAppendable() throws JsonIOException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            false,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonArray jsonElement = JsonArrayTestFactory.createJsonArrayWithOneElement();
    CharArrayWriter writer = new CharArrayWriter();

    // Act
    gson.toJson(jsonElement, writer);

    // Assert
    assertEquals(22, writer.size());
  }

  /**
   * Test {@link Gson#toJson(JsonElement, Appendable)} with {@code JsonElement}, {@code Appendable}.
   *
   * <ul>
   *   <li>Then {@link CharArrayWriter#CharArrayWriter()} size is eight.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, Appendable)"})
  public void testToJsonWithJsonElementAppendable_thenCharArrayWriterSizeIsEight()
      throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    JsonPrimitive jsonElement = new JsonPrimitive('\u0001');
    CharArrayWriter writer = new CharArrayWriter();

    // Act
    gson.toJson(jsonElement, writer);

    // Assert
    assertEquals(8, writer.size());
  }

  /**
   * Test {@link Gson#toJson(JsonElement, Appendable)} with {@code JsonElement}, {@code Appendable}.
   *
   * <ul>
   *   <li>Then {@link CharArrayWriter#CharArrayWriter()} size is fifteen.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, Appendable)"})
  public void testToJsonWithJsonElementAppendable_thenCharArrayWriterSizeIsFifteen()
      throws JsonIOException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());

    JsonObject jsonElement = new JsonObject();
    jsonElement.add(":", JsonNull.INSTANCE);
    CharArrayWriter writer = new CharArrayWriter();

    // Act
    gson.toJson(jsonElement, writer);

    // Assert
    assertEquals(15, writer.size());
  }

  /**
   * Test {@link Gson#toJson(JsonElement, Appendable)} with {@code JsonElement}, {@code Appendable}.
   *
   * <ul>
   *   <li>Then {@link CharArrayWriter#CharArrayWriter()} size is seven.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, Appendable)"})
  public void testToJsonWithJsonElementAppendable_thenCharArrayWriterSizeIsSeven()
      throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    JsonArray jsonElement = JsonArrayTestFactory.createJsonArrayWithNumbers();
    CharArrayWriter writer = new CharArrayWriter();

    // Act
    gson.toJson(jsonElement, writer);

    // Assert
    assertEquals(7, writer.size());
  }

  /**
   * Test {@link Gson#toJson(JsonElement, Appendable)} with {@code JsonElement}, {@code Appendable}.
   *
   * <ul>
   *   <li>Then {@link CharArrayWriter#CharArrayWriter()} size is seventeen.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, Appendable)"})
  public void testToJsonWithJsonElementAppendable_thenCharArrayWriterSizeIsSeventeen()
      throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    JsonArray jsonElement = JsonArrayTestFactory.createJsonArrayWithOneElement();
    CharArrayWriter writer = new CharArrayWriter();

    // Act
    gson.toJson(jsonElement, writer);

    // Assert
    assertEquals(17, writer.size());
  }

  /**
   * Test {@link Gson#toJson(JsonElement, Appendable)} with {@code JsonElement}, {@code Appendable}.
   *
   * <ul>
   *   <li>Then {@link CharArrayWriter#CharArrayWriter()} size is sixty-five.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, Appendable)"})
  public void testToJsonWithJsonElementAppendable_thenCharArrayWriterSizeIsSixtyFive()
      throws JsonIOException {
    // Arrange
    Gson gson = new Gson();

    JsonObject jsonElement = new JsonObject();
    jsonElement.add("out == null", JsonArrayTestFactory.createJsonArrayWithOneElement());
    jsonElement.add(":", JsonArrayTestFactory.createJsonArrayWithOneElement());
    CharArrayWriter writer = new CharArrayWriter();

    // Act
    gson.toJson(jsonElement, writer);

    // Assert
    assertEquals(65, writer.size());
  }

  /**
   * Test {@link Gson#toJson(JsonElement, Appendable)} with {@code JsonElement}, {@code Appendable}.
   *
   * <ul>
   *   <li>Then {@link CharArrayWriter#CharArrayWriter()} size is thirty-five.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, Appendable)"})
  public void testToJsonWithJsonElementAppendable_thenCharArrayWriterSizeIsThirtyFive()
      throws JsonIOException {
    // Arrange
    Gson gson = new Gson();

    JsonArray jsonElement = JsonArrayTestFactory.createJsonArrayWithOneElement();
    jsonElement.add(JsonArrayTestFactory.createJsonArrayWithOneElement());
    CharArrayWriter writer = new CharArrayWriter();

    // Act
    gson.toJson(jsonElement, writer);

    // Assert
    assertEquals(35, writer.size());
  }

  /**
   * Test {@link Gson#toJson(JsonElement, Appendable)} with {@code JsonElement}, {@code Appendable}.
   *
   * <ul>
   *   <li>Then {@link CharArrayWriter#CharArrayWriter()} size is twelve.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, Appendable)"})
  public void testToJsonWithJsonElementAppendable_thenCharArrayWriterSizeIsTwelve()
      throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    JsonArray jsonElement = JsonArrayTestFactory.createJsonArrayWithBooleans();
    CharArrayWriter writer = new CharArrayWriter();

    // Act
    gson.toJson(jsonElement, writer);

    // Assert
    assertEquals(12, writer.size());
  }

  /**
   * Test {@link Gson#toJson(JsonElement, Appendable)} with {@code JsonElement}, {@code Appendable}.
   *
   * <ul>
   *   <li>Then {@link CharArrayWriter#CharArrayWriter()} size is twenty-one.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, Appendable)"})
  public void testToJsonWithJsonElementAppendable_thenCharArrayWriterSizeIsTwentyOne()
      throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    JsonArray jsonElement = JsonArrayTestFactory.createJsonArrayWithMixedTypes();
    CharArrayWriter writer = new CharArrayWriter();

    // Act
    gson.toJson(jsonElement, writer);

    // Assert
    assertEquals(21, writer.size());
  }

  /**
   * Test {@link Gson#toJson(JsonElement, Appendable)} with {@code JsonElement}, {@code Appendable}.
   *
   * <ul>
   *   <li>Then {@link CharArrayWriter#CharArrayWriter()} size is twenty-six.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, Appendable)"})
  public void testToJsonWithJsonElementAppendable_thenCharArrayWriterSizeIsTwentySix()
      throws JsonIOException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            FormattingStyle.PRETTY,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonArray jsonElement = JsonArrayTestFactory.createJsonArrayWithOneElement();
    CharArrayWriter writer = new CharArrayWriter();

    // Act
    gson.toJson(jsonElement, writer);

    // Assert
    assertEquals(26, writer.size());
  }

  /**
   * Test {@link Gson#toJson(JsonElement, Appendable)} with {@code JsonElement}, {@code Appendable}.
   *
   * <ul>
   *   <li>Then {@link CharArrayWriter#CharArrayWriter()} size is twenty-three.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, Appendable)"})
  public void testToJsonWithJsonElementAppendable_thenCharArrayWriterSizeIsTwentyThree()
      throws JsonIOException {
    // Arrange
    Gson gson = new Gson();

    JsonObject jsonElement = new JsonObject();
    jsonElement.add(":", JsonArrayTestFactory.createJsonArrayWithOneElement());
    CharArrayWriter writer = new CharArrayWriter();

    // Act
    gson.toJson(jsonElement, writer);

    // Assert
    assertEquals(23, writer.size());
  }

  /**
   * Test {@link Gson#toJson(JsonElement, Appendable)} with {@code JsonElement}, {@code Appendable}.
   *
   * <ul>
   *   <li>Then {@link CharArrayWriter#CharArrayWriter()} size is twenty-two.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, Appendable)"})
  public void testToJsonWithJsonElementAppendable_thenCharArrayWriterSizeIsTwentyTwo()
      throws JsonIOException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonArray jsonElement = JsonArrayTestFactory.createJsonArrayWithOneElement();
    CharArrayWriter writer = new CharArrayWriter();

    // Act
    gson.toJson(jsonElement, writer);

    // Assert
    assertEquals(22, writer.size());
  }

  /**
   * Test {@link Gson#toJson(JsonElement, Appendable)} with {@code JsonElement}, {@code Appendable}.
   *
   * <ul>
   *   <li>Then {@link CharArrayWriter#CharArrayWriter()} size is two.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, Appendable)"})
  public void testToJsonWithJsonElementAppendable_thenCharArrayWriterSizeIsTwo()
      throws JsonIOException {
    // Arrange
    Gson gson = new Gson();

    JsonObject jsonElement = new JsonObject();
    jsonElement.add(":", JsonNull.INSTANCE);
    CharArrayWriter writer = new CharArrayWriter();

    // Act
    gson.toJson(jsonElement, writer);

    // Assert
    assertEquals(2, writer.size());
  }

  /**
   * Test {@link Gson#toJson(JsonElement, Appendable)} with {@code JsonElement}, {@code Appendable}.
   *
   * <ul>
   *   <li>Then {@link StringWriter#StringWriter()} toString is {@code ["singleElement"]}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, Appendable)"})
  public void testToJsonWithJsonElementAppendable_thenStringWriterToStringIsSingleElement()
      throws JsonIOException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            false,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonArray jsonElement = JsonArrayTestFactory.createJsonArrayWithOneElement();
    StringWriter writer = new StringWriter();

    // Act
    gson.toJson(jsonElement, writer);

    // Assert
    assertEquals("[\"singleElement\"]", writer.toString());
  }

  /**
   * Test {@link Gson#toJson(JsonElement, Appendable)} with {@code JsonElement}, {@code Appendable}.
   *
   * <ul>
   *   <li>When createJsonArrayWithOneElement add {@link JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, Appendable)"})
  public void testToJsonWithJsonElementAppendable_whenCreateJsonArrayWithOneElementAddInstance()
      throws JsonIOException {
    // Arrange
    Gson gson = new Gson();

    JsonArray jsonElement = JsonArrayTestFactory.createJsonArrayWithOneElement();
    jsonElement.add(JsonNull.INSTANCE);
    CharArrayWriter writer = new CharArrayWriter();

    // Act
    gson.toJson(jsonElement, writer);

    // Assert
    assertEquals(22, writer.size());
  }

  /**
   * Test {@link Gson#toJson(JsonElement, Appendable)} with {@code JsonElement}, {@code Appendable}.
   *
   * <ul>
   *   <li>When {@link JsonNull#INSTANCE}.
   *   <li>Then {@link CharArrayWriter#CharArrayWriter()} size is four.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, Appendable)"})
  public void testToJsonWithJsonElementAppendable_whenInstance_thenCharArrayWriterSizeIsFour()
      throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    CharArrayWriter writer = new CharArrayWriter();

    // Act
    gson.toJson(JsonNull.INSTANCE, writer);

    // Assert
    assertEquals(4, writer.size());
  }

  /**
   * Test {@link Gson#toJson(JsonElement, Appendable)} with {@code JsonElement}, {@code Appendable}.
   *
   * <ul>
   *   <li>When {@link JsonObject} (default constructor).
   *   <li>Then {@link CharArrayWriter#CharArrayWriter()} size is two.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, Appendable)"})
  public void testToJsonWithJsonElementAppendable_whenJsonObject_thenCharArrayWriterSizeIsTwo()
      throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    JsonObject jsonElement = new JsonObject();
    CharArrayWriter writer = new CharArrayWriter();

    // Act
    gson.toJson(jsonElement, writer);

    // Assert
    assertEquals(2, writer.size());
  }

  /**
   * Test {@link Gson#toJson(JsonElement, Appendable)} with {@code JsonElement}, {@code Appendable}.
   *
   * <ul>
   *   <li>When {@link JsonPrimitive#JsonPrimitive(String)} with string is {@code out == null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, Appendable)"})
  public void testToJsonWithJsonElementAppendable_whenJsonPrimitiveWithStringIsOutNull()
      throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    JsonPrimitive jsonElement = new JsonPrimitive("out == null");
    CharArrayWriter writer = new CharArrayWriter();

    // Act
    gson.toJson(jsonElement, writer);

    // Assert
    assertEquals(23, writer.size());
  }

  /**
   * Test {@link Gson#toJson(JsonElement, Appendable)} with {@code JsonElement}, {@code Appendable}.
   *
   * <ul>
   *   <li>When {@link PipedWriter#PipedWriter()}.
   *   <li>Then throw {@link JsonIOException}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, Appendable)"})
  public void testToJsonWithJsonElementAppendable_whenPipedWriter_thenThrowJsonIOException()
      throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    JsonArray jsonElement = JsonArrayTestFactory.createJsonArrayWithOneElement();

    // Act and Assert
    assertThrows(JsonIOException.class, () -> gson.toJson(jsonElement, new PipedWriter()));
  }

  /**
   * Test {@link Gson#toJson(JsonElement, Appendable)} with {@code JsonElement}, {@code Appendable}.
   *
   * <ul>
   *   <li>When {@link PipedWriter#PipedWriter()}.
   *   <li>Then throw {@link JsonIOException}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, Appendable)"})
  public void testToJsonWithJsonElementAppendable_whenPipedWriter_thenThrowJsonIOException2()
      throws JsonIOException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonArray jsonElement = JsonArrayTestFactory.createJsonArrayWithOneElement();

    // Act and Assert
    assertThrows(JsonIOException.class, () -> gson.toJson(jsonElement, new PipedWriter()));
  }

  /**
   * Test {@link Gson#toJson(JsonElement, JsonWriter)} with {@code JsonElement}, {@code JsonWriter}.
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, JsonWriter)"})
  public void testToJsonWithJsonElementJsonWriter() throws JsonIOException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonArray jsonElement = JsonArrayTestFactory.createJsonArrayWithOneElement();

    JsonWriter writer = new JsonWriter(new StringWriter());
    writer.setStrictness(Strictness.LEGACY_STRICT);

    // Act and Assert
    gson.toJson(jsonElement, writer);
  }

  /**
   * Test {@link Gson#toJson(JsonElement, JsonWriter)} with {@code JsonElement}, {@code JsonWriter}.
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, JsonWriter)"})
  public void testToJsonWithJsonElementJsonWriter2() throws JsonIOException {
    // Arrange
    Gson gson = new Gson();

    JsonArray jsonElement = mock(JsonArray.class);
    when(jsonElement.isJsonNull()).thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> gson.toJson(jsonElement, new JsonWriter(new StringWriter())));
    verify(jsonElement).isJsonNull();
  }

  /**
   * Test {@link Gson#toJson(JsonElement, JsonWriter)} with {@code JsonElement}, {@code JsonWriter}.
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, JsonWriter)"})
  public void testToJsonWithJsonElementJsonWriter3() throws JsonIOException, IOException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            3,
            3,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());

    JsonArray jsonElement = mock(JsonArray.class);
    when(jsonElement.isJsonNull()).thenReturn(true);

    JsonWriter writer = mock(JsonWriter.class);
    when(writer.getStrictness()).thenReturn(Strictness.LEGACY_STRICT);
    when(writer.nullValue()).thenReturn(new JsonWriter(new StringWriter()));
    when(writer.getSerializeNulls()).thenReturn(true);
    when(writer.isHtmlSafe()).thenReturn(true);
    doNothing().when(writer).setHtmlSafe(anyBoolean());
    doNothing().when(writer).setSerializeNulls(anyBoolean());
    doNothing().when(writer).setStrictness(Mockito.<Strictness>any());

    // Act
    gson.toJson(jsonElement, writer);

    // Assert
    verify(jsonElement).isJsonNull();
    verify(writer).getSerializeNulls();
    verify(writer).getStrictness();
    verify(writer).isHtmlSafe();
    verify(writer).nullValue();
    verify(writer, atLeast(1)).setHtmlSafe(true);
    verify(writer, atLeast(1)).setSerializeNulls(true);
    verify(writer, atLeast(1)).setStrictness(Mockito.<Strictness>any());
  }

  /**
   * Test {@link Gson#toJson(JsonElement, JsonWriter)} with {@code JsonElement}, {@code JsonWriter}.
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, JsonWriter)"})
  public void testToJsonWithJsonElementJsonWriter4() throws JsonIOException {
    // Arrange
    Gson gson = new Gson();

    JsonArray jsonElement = mock(JsonArray.class);
    when(jsonElement.getAsJsonPrimitive()).thenThrow(new IllegalArgumentException());
    when(jsonElement.isJsonNull()).thenReturn(false);
    when(jsonElement.isJsonPrimitive()).thenReturn(true);

    JsonWriter writer = mock(JsonWriter.class);
    when(writer.getStrictness()).thenReturn(Strictness.LEGACY_STRICT);
    when(writer.getSerializeNulls()).thenReturn(true);
    when(writer.isHtmlSafe()).thenReturn(true);
    doNothing().when(writer).setHtmlSafe(anyBoolean());
    doNothing().when(writer).setSerializeNulls(anyBoolean());
    doNothing().when(writer).setStrictness(Mockito.<Strictness>any());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> gson.toJson(jsonElement, writer));
    verify(jsonElement).getAsJsonPrimitive();
    verify(jsonElement).isJsonNull();
    verify(jsonElement).isJsonPrimitive();
    verify(writer).getSerializeNulls();
    verify(writer, atLeast(1)).getStrictness();
    verify(writer).isHtmlSafe();
    verify(writer, atLeast(1)).setHtmlSafe(true);
    verify(writer, atLeast(1)).setSerializeNulls(anyBoolean());
    verify(writer, atLeast(1)).setStrictness(Mockito.<Strictness>any());
  }

  /**
   * Test {@link Gson#toJson(JsonElement, JsonWriter)} with {@code JsonElement}, {@code JsonWriter}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@link JsonNull#INSTANCE}.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, JsonWriter)"})
  public void testToJsonWithJsonElementJsonWriter_givenGson_whenInstance_thenDoesNotThrow()
      throws JsonIOException {
    // Arrange
    Gson gson = new Gson();

    // Act and Assert
    gson.toJson(JsonNull.INSTANCE, new JsonWriter(new StringWriter()));
  }

  /**
   * Test {@link Gson#toJson(JsonElement, JsonWriter)} with {@code JsonElement}, {@code JsonWriter}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@link JsonObject} (default constructor).
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, JsonWriter)"})
  public void testToJsonWithJsonElementJsonWriter_givenGson_whenJsonObject_thenDoesNotThrow()
      throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    JsonObject jsonElement = new JsonObject();

    // Act and Assert
    gson.toJson(jsonElement, new JsonWriter(new StringWriter()));
  }

  /**
   * Test {@link Gson#toJson(JsonElement, JsonWriter)} with {@code JsonElement}, {@code JsonWriter}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, JsonWriter)"})
  public void testToJsonWithJsonElementJsonWriter_givenLenient()
      throws JsonIOException, IOException {
    // Arrange
    Gson gson = new Gson();

    JsonArray jsonElement = mock(JsonArray.class);
    when(jsonElement.isJsonNull()).thenReturn(true);

    JsonWriter writer = mock(JsonWriter.class);
    when(writer.nullValue()).thenReturn(new JsonWriter(new StringWriter()));
    when(writer.getSerializeNulls()).thenReturn(true);
    when(writer.isHtmlSafe()).thenReturn(true);
    when(writer.getStrictness()).thenReturn(Strictness.LENIENT);
    doNothing().when(writer).setHtmlSafe(anyBoolean());
    doNothing().when(writer).setSerializeNulls(anyBoolean());
    doNothing().when(writer).setStrictness(Mockito.<Strictness>any());

    // Act
    gson.toJson(jsonElement, writer);

    // Assert
    verify(jsonElement).isJsonNull();
    verify(writer).getSerializeNulls();
    verify(writer, atLeast(1)).getStrictness();
    verify(writer).isHtmlSafe();
    verify(writer).nullValue();
    verify(writer, atLeast(1)).setHtmlSafe(true);
    verify(writer, atLeast(1)).setSerializeNulls(anyBoolean());
    verify(writer).setStrictness(Strictness.LENIENT);
  }

  /**
   * Test {@link Gson#toJson(JsonElement, JsonWriter)} with {@code JsonElement}, {@code JsonWriter}.
   *
   * <ul>
   *   <li>Then calls {@link JsonWriter#nullValue()}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, JsonWriter)"})
  public void testToJsonWithJsonElementJsonWriter_thenCallsNullValue()
      throws JsonIOException, IOException {
    // Arrange
    Gson gson = new Gson();

    JsonArray jsonElement = mock(JsonArray.class);
    when(jsonElement.isJsonNull()).thenReturn(true);

    JsonWriter writer = mock(JsonWriter.class);
    when(writer.getStrictness()).thenReturn(Strictness.LEGACY_STRICT);
    when(writer.nullValue()).thenReturn(new JsonWriter(new StringWriter()));
    when(writer.getSerializeNulls()).thenReturn(true);
    when(writer.isHtmlSafe()).thenReturn(true);
    doNothing().when(writer).setHtmlSafe(anyBoolean());
    doNothing().when(writer).setSerializeNulls(anyBoolean());
    doNothing().when(writer).setStrictness(Mockito.<Strictness>any());

    // Act
    gson.toJson(jsonElement, writer);

    // Assert
    verify(jsonElement).isJsonNull();
    verify(writer).getSerializeNulls();
    verify(writer, atLeast(1)).getStrictness();
    verify(writer).isHtmlSafe();
    verify(writer).nullValue();
    verify(writer, atLeast(1)).setHtmlSafe(true);
    verify(writer, atLeast(1)).setSerializeNulls(anyBoolean());
    verify(writer, atLeast(1)).setStrictness(Mockito.<Strictness>any());
  }

  /**
   * Test {@link Gson#toJson(JsonElement, JsonWriter)} with {@code JsonElement}, {@code JsonWriter}.
   *
   * <ul>
   *   <li>Then throw {@link JsonIOException}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, JsonWriter)"})
  public void testToJsonWithJsonElementJsonWriter_thenThrowJsonIOException()
      throws JsonIOException, IOException {
    // Arrange
    Gson gson = new Gson();

    JsonArray jsonElement = mock(JsonArray.class);
    when(jsonElement.getAsJsonPrimitive()).thenReturn(new JsonPrimitive("String"));
    when(jsonElement.isJsonNull()).thenReturn(false);
    when(jsonElement.isJsonPrimitive()).thenReturn(true);

    JsonWriter writer = mock(JsonWriter.class);
    when(writer.value(Mockito.<String>any())).thenThrow(new IOException());
    when(writer.getStrictness()).thenReturn(Strictness.LEGACY_STRICT);
    when(writer.getSerializeNulls()).thenReturn(true);
    when(writer.isHtmlSafe()).thenReturn(true);
    doNothing().when(writer).setHtmlSafe(anyBoolean());
    doNothing().when(writer).setSerializeNulls(anyBoolean());
    doNothing().when(writer).setStrictness(Mockito.<Strictness>any());

    // Act and Assert
    assertThrows(JsonIOException.class, () -> gson.toJson(jsonElement, writer));
    verify(jsonElement).getAsJsonPrimitive();
    verify(jsonElement).isJsonNull();
    verify(jsonElement).isJsonPrimitive();
    verify(writer).getSerializeNulls();
    verify(writer, atLeast(1)).getStrictness();
    verify(writer).isHtmlSafe();
    verify(writer, atLeast(1)).setHtmlSafe(true);
    verify(writer, atLeast(1)).setSerializeNulls(anyBoolean());
    verify(writer, atLeast(1)).setStrictness(Mockito.<Strictness>any());
    verify(writer).value("String");
  }

  /**
   * Test {@link Gson#toJson(JsonElement, JsonWriter)} with {@code JsonElement}, {@code JsonWriter}.
   *
   * <ul>
   *   <li>When createJsonArrayWithBooleans.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, JsonWriter)"})
  public void testToJsonWithJsonElementJsonWriter_whenCreateJsonArrayWithBooleans()
      throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    JsonArray jsonElement = JsonArrayTestFactory.createJsonArrayWithBooleans();

    // Act and Assert
    gson.toJson(jsonElement, new JsonWriter(new StringWriter()));
  }

  /**
   * Test {@link Gson#toJson(JsonElement, JsonWriter)} with {@code JsonElement}, {@code JsonWriter}.
   *
   * <ul>
   *   <li>When createJsonArrayWithMixedTypes.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, JsonWriter)"})
  public void testToJsonWithJsonElementJsonWriter_whenCreateJsonArrayWithMixedTypes()
      throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    JsonArray jsonElement = JsonArrayTestFactory.createJsonArrayWithMixedTypes();

    // Act and Assert
    gson.toJson(jsonElement, new JsonWriter(new StringWriter()));
  }

  /**
   * Test {@link Gson#toJson(JsonElement, JsonWriter)} with {@code JsonElement}, {@code JsonWriter}.
   *
   * <ul>
   *   <li>When createJsonArrayWithNumbers.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, JsonWriter)"})
  public void testToJsonWithJsonElementJsonWriter_whenCreateJsonArrayWithNumbers()
      throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    JsonArray jsonElement = JsonArrayTestFactory.createJsonArrayWithNumbers();

    // Act and Assert
    gson.toJson(jsonElement, new JsonWriter(new StringWriter()));
  }

  /**
   * Test {@link Gson#toJson(JsonElement, JsonWriter)} with {@code JsonElement}, {@code JsonWriter}.
   *
   * <ul>
   *   <li>When createJsonArrayWithOneElement.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, JsonWriter)"})
  public void testToJsonWithJsonElementJsonWriter_whenCreateJsonArrayWithOneElement()
      throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    JsonArray jsonElement = JsonArrayTestFactory.createJsonArrayWithOneElement();

    // Act and Assert
    gson.toJson(jsonElement, new JsonWriter(new StringWriter()));
  }

  /**
   * Test {@link Gson#toJson(JsonElement, JsonWriter)} with {@code JsonElement}, {@code JsonWriter}.
   *
   * <ul>
   *   <li>When {@link JsonPrimitive#JsonPrimitive(Character)} with c is end of text.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, JsonWriter)"})
  public void testToJsonWithJsonElementJsonWriter_whenJsonPrimitiveWithCIsEndOfText()
      throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    JsonPrimitive jsonElement = new JsonPrimitive('\u0003');

    // Act and Assert
    gson.toJson(jsonElement, new JsonWriter(new StringWriter()));
  }

  /**
   * Test {@link Gson#toJson(JsonElement, JsonWriter)} with {@code JsonElement}, {@code JsonWriter}.
   *
   * <ul>
   *   <li>When {@link JsonPrimitive#JsonPrimitive(String)} with {@code String}.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, JsonWriter)"})
  public void testToJsonWithJsonElementJsonWriter_whenJsonPrimitiveWithString_thenDoesNotThrow()
      throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    JsonPrimitive jsonElement = new JsonPrimitive("String");

    // Act and Assert
    gson.toJson(jsonElement, new JsonWriter(new StringWriter()));
  }

  /**
   * Test {@link Gson#toJson(JsonElement, JsonWriter)} with {@code JsonElement}, {@code JsonWriter}.
   *
   * <ul>
   *   <li>When {@link JsonTreeWriter} (default constructor).
   *   <li>Then calls {@link JsonArray#isJsonNull()}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(JsonElement, JsonWriter)"})
  public void testToJsonWithJsonElementJsonWriter_whenJsonTreeWriter_thenCallsIsJsonNull()
      throws JsonIOException {
    // Arrange
    Gson gson = new Gson();

    JsonArray jsonElement = mock(JsonArray.class);
    when(jsonElement.isJsonNull()).thenReturn(true);

    // Act
    gson.toJson(jsonElement, new JsonTreeWriter());

    // Assert
    verify(jsonElement).isJsonNull();
  }

  /**
   * Test {@link Gson#toJson(JsonElement)} with {@code JsonElement}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When createJsonArrayWithNumbers.
   *   <li>Then return {@code [1,2,3]}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(JsonElement)"})
  public void testToJsonWithJsonElement_givenGson_whenCreateJsonArrayWithNumbers_thenReturn123() {
    // Arrange
    Gson gson = new Gson();

    // Act and Assert
    assertEquals("[1,2,3]", gson.toJson(JsonArrayTestFactory.createJsonArrayWithNumbers()));
  }

  /**
   * Test {@link Gson#toJson(JsonElement)} with {@code JsonElement}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@link JsonNull#INSTANCE}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(JsonElement)"})
  public void testToJsonWithJsonElement_givenGson_whenInstance_thenReturnNull() {
    // Arrange, Act and Assert
    assertEquals("null", new Gson().toJson(JsonNull.INSTANCE));
  }

  /**
   * Test {@link Gson#toJson(JsonElement)} with {@code JsonElement}.
   *
   * <ul>
   *   <li>Then return {@code {}}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(JsonElement)"})
  public void testToJsonWithJsonElement_thenReturnLeftCurlyBracketRightCurlyBracket() {
    // Arrange
    Gson gson = new Gson();

    JsonObject jsonElement = new JsonObject();
    jsonElement.add(":", JsonNull.INSTANCE);

    // Act and Assert
    assertEquals("{}", gson.toJson(jsonElement));
  }

  /**
   * Test {@link Gson#toJson(JsonElement)} with {@code JsonElement}.
   *
   * <ul>
   *   <li>Then return {@code )]}' {":":null}}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(JsonElement)"})
  public void testToJsonWithJsonElement_thenReturnNull() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());

    JsonObject jsonElement = new JsonObject();
    jsonElement.add(":", JsonNull.INSTANCE);

    // Act and Assert
    assertEquals(")]}'\n{\":\":null}", gson.toJson(jsonElement));
  }

  /**
   * Test {@link Gson#toJson(JsonElement)} with {@code JsonElement}.
   *
   * <ul>
   *   <li>Then return {@code "out \u003d\u003d null"}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(JsonElement)"})
  public void testToJsonWithJsonElement_thenReturnOutU003dU003dNull() {
    // Arrange
    Gson gson = new Gson();

    // Act
    String actualToJsonResult = gson.toJson(new JsonPrimitive("out == null"));

    // Assert
    assertEquals("\"out \\u003d\\u003d null\"", actualToJsonResult);
  }

  /**
   * Test {@link Gson#toJson(JsonElement)} with {@code JsonElement}.
   *
   * <ul>
   *   <li>Then return {@code {"out \u003d\u003d null":["singleElement"],":":["singleElement"]}}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(JsonElement)"})
  public void testToJsonWithJsonElement_thenReturnOutU003dU003dNullSingleElementSingleElement() {
    // Arrange
    Gson gson = new Gson();

    JsonObject jsonElement = new JsonObject();
    jsonElement.add("out == null", JsonArrayTestFactory.createJsonArrayWithOneElement());
    jsonElement.add(":", JsonArrayTestFactory.createJsonArrayWithOneElement());

    // Act and Assert
    assertEquals(
        "{\"out \\u003d\\u003d null\":[\"singleElement\"],\":\":[\"singleElement\"]}",
        gson.toJson(jsonElement));
  }

  /**
   * Test {@link Gson#toJson(JsonElement)} with {@code JsonElement}.
   *
   * <ul>
   *   <li>Then return {@code ["singleElement"]}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(JsonElement)"})
  public void testToJsonWithJsonElement_thenReturnSingleElement() {
    // Arrange
    Gson gson = new Gson();

    // Act and Assert
    assertEquals(
        "[\"singleElement\"]", gson.toJson(JsonArrayTestFactory.createJsonArrayWithOneElement()));
  }

  /**
   * Test {@link Gson#toJson(JsonElement)} with {@code JsonElement}.
   *
   * <ul>
   *   <li>Then return {@code )]}' ["singleElement"]}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(JsonElement)"})
  public void testToJsonWithJsonElement_thenReturnSingleElement2() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());

    // Act and Assert
    assertEquals(
        ")]}'\n[\"singleElement\"]",
        gson.toJson(JsonArrayTestFactory.createJsonArrayWithOneElement()));
  }

  /**
   * Test {@link Gson#toJson(JsonElement)} with {@code JsonElement}.
   *
   * <ul>
   *   <li>Then return {@code )]}' [ "singleElement" ]}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(JsonElement)"})
  public void testToJsonWithJsonElement_thenReturnSingleElement3() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            FormattingStyle.PRETTY,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());

    // Act and Assert
    assertEquals(
        ")]}'\n[\n  \"singleElement\"\n]",
        gson.toJson(JsonArrayTestFactory.createJsonArrayWithOneElement()));
  }

  /**
   * Test {@link Gson#toJson(JsonElement)} with {@code JsonElement}.
   *
   * <ul>
   *   <li>Then return {@code {":":["singleElement"]}}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(JsonElement)"})
  public void testToJsonWithJsonElement_thenReturnSingleElement4() {
    // Arrange
    Gson gson = new Gson();

    JsonObject jsonElement = new JsonObject();
    jsonElement.add(":", JsonArrayTestFactory.createJsonArrayWithOneElement());

    // Act and Assert
    assertEquals("{\":\":[\"singleElement\"]}", gson.toJson(jsonElement));
  }

  /**
   * Test {@link Gson#toJson(JsonElement)} with {@code JsonElement}.
   *
   * <ul>
   *   <li>Then return {@code ["singleElement",null]}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(JsonElement)"})
  public void testToJsonWithJsonElement_thenReturnSingleElementNull() {
    // Arrange
    Gson gson = new Gson();

    JsonArray jsonElement = JsonArrayTestFactory.createJsonArrayWithOneElement();
    jsonElement.add(JsonNull.INSTANCE);

    // Act and Assert
    assertEquals("[\"singleElement\",null]", gson.toJson(jsonElement));
  }

  /**
   * Test {@link Gson#toJson(JsonElement)} with {@code JsonElement}.
   *
   * <ul>
   *   <li>Then return {@code ["singleElement",["singleElement"]]}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(JsonElement)"})
  public void testToJsonWithJsonElement_thenReturnSingleElementSingleElement() {
    // Arrange
    Gson gson = new Gson();

    JsonArray jsonElement = JsonArrayTestFactory.createJsonArrayWithOneElement();
    jsonElement.add(JsonArrayTestFactory.createJsonArrayWithOneElement());

    // Act and Assert
    assertEquals("[\"singleElement\",[\"singleElement\"]]", gson.toJson(jsonElement));
  }

  /**
   * Test {@link Gson#toJson(JsonElement)} with {@code JsonElement}.
   *
   * <ul>
   *   <li>Then return {@code ["singleElement","\u0006",["singleElement"]]}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(JsonElement)"})
  public void testToJsonWithJsonElement_thenReturnSingleElementU0006SingleElement() {
    // Arrange
    Gson gson = new Gson();

    JsonArray jsonElement = JsonArrayTestFactory.createJsonArrayWithOneElement();
    jsonElement.add('\u0006');
    jsonElement.add(JsonArrayTestFactory.createJsonArrayWithOneElement());

    // Act and Assert
    assertEquals("[\"singleElement\",\"\\u0006\",[\"singleElement\"]]", gson.toJson(jsonElement));
  }

  /**
   * Test {@link Gson#toJson(JsonElement)} with {@code JsonElement}.
   *
   * <ul>
   *   <li>Then return {@code ["string",42,true,{}]}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(JsonElement)"})
  public void testToJsonWithJsonElement_thenReturnString42True() {
    // Arrange
    Gson gson = new Gson();

    // Act and Assert
    assertEquals(
        "[\"string\",42,true,{}]",
        gson.toJson(JsonArrayTestFactory.createJsonArrayWithMixedTypes()));
  }

  /**
   * Test {@link Gson#toJson(JsonElement)} with {@code JsonElement}.
   *
   * <ul>
   *   <li>When createJsonArrayWithBooleans.
   *   <li>Then return {@code [true,false]}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(JsonElement)"})
  public void testToJsonWithJsonElement_whenCreateJsonArrayWithBooleans_thenReturnTrueFalse() {
    // Arrange
    Gson gson = new Gson();

    // Act and Assert
    assertEquals("[true,false]", gson.toJson(JsonArrayTestFactory.createJsonArrayWithBooleans()));
  }

  /**
   * Test {@link Gson#toJson(JsonElement)} with {@code JsonElement}.
   *
   * <ul>
   *   <li>When {@link JsonObject} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(JsonElement)"})
  public void testToJsonWithJsonElement_whenJsonObject() {
    // Arrange
    Gson gson = new Gson();

    // Act and Assert
    assertEquals("{}", gson.toJson(new JsonObject()));
  }

  /**
   * Test {@link Gson#toJson(Object)} with {@code Object}.
   *
   * <p>Method under test: {@link Gson#toJson(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object)"})
  public void testToJsonWithObject() throws NoSuchFieldException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());

    // Act and Assert
    assertEquals(")]}'\n{}", gson.toJson(ReflectionHelperTestFactory.createPublicField()));
  }

  /**
   * Test {@link Gson#toJson(Object)} with {@code Object}.
   *
   * <p>Method under test: {@link Gson#toJson(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object)"})
  public void testToJsonWithObject2() throws NoSuchFieldException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            FormattingStyle.PRETTY,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());

    // Act and Assert
    assertEquals(")]}'\n{}", gson.toJson(ReflectionHelperTestFactory.createPublicField()));
  }

  /**
   * Test {@link Gson#toJson(Object, Appendable)} with {@code Object}, {@code Appendable}.
   *
   * <p>Method under test: {@link Gson#toJson(Object, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(Object, Appendable)"})
  public void testToJsonWithObjectAppendable() throws JsonIOException, NoSuchFieldException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            FormattingStyle.PRETTY,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();
    CharArrayWriter writer = new CharArrayWriter();

    // Act
    gson.toJson(createPublicFieldResult, writer);

    // Assert
    assertEquals(7, writer.size());
  }

  /**
   * Test {@link Gson#toJson(Object, Appendable)} with {@code Object}, {@code Appendable}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>Then {@link CharArrayWriter#CharArrayWriter()} size is two.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(Object, Appendable)"})
  public void testToJsonWithObjectAppendable_givenGson_thenCharArrayWriterSizeIsTwo()
      throws JsonIOException, NoSuchFieldException {
    // Arrange
    Gson gson = new Gson();
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();
    CharArrayWriter writer = new CharArrayWriter();

    // Act
    gson.toJson(createPublicFieldResult, writer);

    // Assert
    assertEquals(2, writer.size());
  }

  /**
   * Test {@link Gson#toJson(Object, Appendable)} with {@code Object}, {@code Appendable}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code null}.
   *   <li>Then {@link StringWriter#StringWriter()} toString is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(Object, Appendable)"})
  public void testToJsonWithObjectAppendable_givenGson_whenNull_thenStringWriterToStringIsNull()
      throws JsonIOException {
    // Arrange
    Gson gson = new Gson();
    StringWriter writer = new StringWriter();

    // Act
    gson.toJson((Object) null, writer);

    // Assert
    assertEquals("null", writer.toString());
  }

  /**
   * Test {@link Gson#toJson(Object, Appendable)} with {@code Object}, {@code Appendable}.
   *
   * <ul>
   *   <li>Then {@link CharArrayWriter#CharArrayWriter()} size is seven.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(Object, Appendable)"})
  public void testToJsonWithObjectAppendable_thenCharArrayWriterSizeIsSeven()
      throws JsonIOException, NoSuchFieldException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();
    CharArrayWriter writer = new CharArrayWriter();

    // Act
    gson.toJson(createPublicFieldResult, writer);

    // Assert
    assertEquals(7, writer.size());
  }

  /**
   * Test {@link Gson#toJson(Object, Type)} with {@code Object}, {@code Type}.
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object, Type)"})
  public void testToJsonWithObjectType() throws IOException, NoSuchFieldException {
    // Arrange
    FutureTypeAdapter<Object> futureTypeAdapter = mock(FutureTypeAdapter.class);
    doNothing().when(futureTypeAdapter).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());

    Excluder excluder = mock(Excluder.class);
    when(excluder.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(futureTypeAdapter);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            excluder,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();

    // Act
    String actualToJsonResult =
        gson.toJson(createPublicFieldResult, GsonBuilderTestFactory.createType());

    // Assert
    verify(futureTypeAdapter).write(isA(JsonWriter.class), isA(Object.class));
    verify(excluder).create(isA(Gson.class), isA(TypeToken.class));
    assertEquals(")]}'\n", actualToJsonResult);
  }

  /**
   * Test {@link Gson#toJson(Object, Type)} with {@code Object}, {@code Type}.
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object, Type)"})
  public void testToJsonWithObjectType2() throws IOException, NoSuchFieldException {
    // Arrange
    FutureTypeAdapter<Object> futureTypeAdapter = mock(FutureTypeAdapter.class);
    doNothing().when(futureTypeAdapter).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());

    Excluder excluder = mock(Excluder.class);
    when(excluder.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(futureTypeAdapter);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            excluder,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            FormattingStyle.PRETTY,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();

    // Act
    String actualToJsonResult =
        gson.toJson(createPublicFieldResult, GsonBuilderTestFactory.createType());

    // Assert
    verify(futureTypeAdapter).write(isA(JsonWriter.class), isA(Object.class));
    verify(excluder).create(isA(Gson.class), isA(TypeToken.class));
    assertEquals(")]}'\n", actualToJsonResult);
  }

  /**
   * Test {@link Gson#toJson(Object, Type)} with {@code Object}, {@code Type}.
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object, Type)"})
  public void testToJsonWithObjectType3() throws IOException, NoSuchFieldException {
    // Arrange
    FutureTypeAdapter<Object> futureTypeAdapter = mock(FutureTypeAdapter.class);
    doNothing().when(futureTypeAdapter).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());

    Excluder excluder = mock(Excluder.class);
    when(excluder.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(futureTypeAdapter);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            excluder,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            null,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();

    // Act
    String actualToJsonResult =
        gson.toJson(createPublicFieldResult, GsonBuilderTestFactory.createType());

    // Assert
    verify(futureTypeAdapter).write(isA(JsonWriter.class), isA(Object.class));
    verify(excluder).create(isA(Gson.class), isA(TypeToken.class));
    assertEquals(")]}'\n", actualToJsonResult);
  }

  /**
   * Test {@link Gson#toJson(Object, Type)} with {@code Object}, {@code Type}.
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object, Type)"})
  public void testToJsonWithObjectType4() throws NoSuchFieldException {
    // Arrange
    FormattingStyle formattingStyle = mock(FormattingStyle.class);
    when(formattingStyle.usesSpaceAfterSeparators()).thenThrow(new IllegalArgumentException());
    Excluder excluder = mock(Excluder.class);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            excluder,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            formattingStyle,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> gson.toJson(createPublicFieldResult, GsonBuilderTestFactory.createType()));
    verify(formattingStyle).usesSpaceAfterSeparators();
  }

  /**
   * Test {@link Gson#toJson(Object, Type)} with {@code Object}, {@code Type}.
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object, Type)"})
  public void testToJsonWithObjectType5() throws NoSuchFieldException {
    // Arrange
    FormattingStyle formattingStyle = mock(FormattingStyle.class);
    when(formattingStyle.getIndent()).thenThrow(new IllegalArgumentException());
    when(formattingStyle.usesSpaceAfterSeparators()).thenReturn(true);
    when(formattingStyle.getNewline()).thenReturn("");
    Excluder excluder = mock(Excluder.class);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            excluder,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            formattingStyle,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> gson.toJson(createPublicFieldResult, GsonBuilderTestFactory.createType()));
    verify(formattingStyle).getIndent();
    verify(formattingStyle, atLeast(1)).getNewline();
    verify(formattingStyle).usesSpaceAfterSeparators();
  }

  /**
   * Test {@link Gson#toJson(Object, Type, Appendable)} with {@code Object}, {@code Type}, {@code
   * Appendable}.
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(Object, Type, Appendable)"})
  public void testToJsonWithObjectTypeAppendable()
      throws JsonIOException, IOException, NoSuchFieldException {
    // Arrange
    FutureTypeAdapter<Object> futureTypeAdapter = mock(FutureTypeAdapter.class);
    doNothing().when(futureTypeAdapter).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());

    Excluder excluder = mock(Excluder.class);
    when(excluder.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(futureTypeAdapter);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            excluder,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();
    Type typeOfSrc = GsonBuilderTestFactory.createType();
    CharArrayWriter writer = new CharArrayWriter();

    // Act
    gson.toJson(createPublicFieldResult, typeOfSrc, writer);

    // Assert
    verify(futureTypeAdapter).write(isA(JsonWriter.class), isA(Object.class));
    verify(excluder).create(isA(Gson.class), isA(TypeToken.class));
    assertEquals(5, writer.size());
  }

  /**
   * Test {@link Gson#toJson(Object, Type, Appendable)} with {@code Object}, {@code Type}, {@code
   * Appendable}.
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(Object, Type, Appendable)"})
  public void testToJsonWithObjectTypeAppendable2()
      throws JsonIOException, IOException, NoSuchFieldException {
    // Arrange
    FutureTypeAdapter<Object> futureTypeAdapter = mock(FutureTypeAdapter.class);
    doNothing().when(futureTypeAdapter).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());

    Excluder excluder = mock(Excluder.class);
    when(excluder.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(futureTypeAdapter);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            excluder,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            FormattingStyle.PRETTY,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();
    Type typeOfSrc = GsonBuilderTestFactory.createType();
    CharArrayWriter writer = new CharArrayWriter();

    // Act
    gson.toJson(createPublicFieldResult, typeOfSrc, writer);

    // Assert
    verify(futureTypeAdapter).write(isA(JsonWriter.class), isA(Object.class));
    verify(excluder).create(isA(Gson.class), isA(TypeToken.class));
    assertEquals(5, writer.size());
  }

  /**
   * Test {@link Gson#toJson(Object, Type, Appendable)} with {@code Object}, {@code Type}, {@code
   * Appendable}.
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(Object, Type, Appendable)"})
  public void testToJsonWithObjectTypeAppendable3()
      throws JsonIOException, IOException, NoSuchFieldException {
    // Arrange
    FutureTypeAdapter<Object> futureTypeAdapter = mock(FutureTypeAdapter.class);
    doNothing().when(futureTypeAdapter).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());

    Excluder excluder = mock(Excluder.class);
    when(excluder.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(futureTypeAdapter);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            excluder,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            null,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();
    Type typeOfSrc = GsonBuilderTestFactory.createType();
    CharArrayWriter writer = new CharArrayWriter();

    // Act
    gson.toJson(createPublicFieldResult, typeOfSrc, writer);

    // Assert
    verify(futureTypeAdapter).write(isA(JsonWriter.class), isA(Object.class));
    verify(excluder).create(isA(Gson.class), isA(TypeToken.class));
    assertEquals(5, writer.size());
  }

  /**
   * Test {@link Gson#toJson(Object, Type, Appendable)} with {@code Object}, {@code Type}, {@code
   * Appendable}.
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(Object, Type, Appendable)"})
  public void testToJsonWithObjectTypeAppendable4() throws JsonIOException, NoSuchFieldException {
    // Arrange
    FormattingStyle formattingStyle = mock(FormattingStyle.class);
    when(formattingStyle.usesSpaceAfterSeparators()).thenThrow(new IllegalArgumentException());
    Excluder excluder = mock(Excluder.class);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            excluder,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            formattingStyle,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();
    Type typeOfSrc = GsonBuilderTestFactory.createType();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> gson.toJson(createPublicFieldResult, typeOfSrc, new CharArrayWriter()));
    verify(formattingStyle).usesSpaceAfterSeparators();
  }

  /**
   * Test {@link Gson#toJson(Object, Type, Appendable)} with {@code Object}, {@code Type}, {@code
   * Appendable}.
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(Object, Type, Appendable)"})
  public void testToJsonWithObjectTypeAppendable5() throws JsonIOException, NoSuchFieldException {
    // Arrange
    FormattingStyle formattingStyle = mock(FormattingStyle.class);
    when(formattingStyle.getIndent()).thenThrow(new IllegalArgumentException());
    when(formattingStyle.usesSpaceAfterSeparators()).thenReturn(true);
    when(formattingStyle.getNewline()).thenReturn("");
    Excluder excluder = mock(Excluder.class);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            excluder,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            formattingStyle,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();
    Type typeOfSrc = GsonBuilderTestFactory.createType();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> gson.toJson(createPublicFieldResult, typeOfSrc, new CharArrayWriter()));
    verify(formattingStyle).getIndent();
    verify(formattingStyle, atLeast(1)).getNewline();
    verify(formattingStyle).usesSpaceAfterSeparators();
  }

  /**
   * Test {@link Gson#toJson(Object, Type, Appendable)} with {@code Object}, {@code Type}, {@code
   * Appendable}.
   *
   * <ul>
   *   <li>Given {@link FormattingStyle} {@link FormattingStyle#getNewline()} return {@code
   *       Newline}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(Object, Type, Appendable)"})
  public void testToJsonWithObjectTypeAppendable_givenFormattingStyleGetNewlineReturnNewline()
      throws JsonIOException, IOException, NoSuchFieldException {
    // Arrange
    FutureTypeAdapter<Object> futureTypeAdapter = mock(FutureTypeAdapter.class);
    doNothing().when(futureTypeAdapter).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());

    Excluder excluder = mock(Excluder.class);
    when(excluder.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(futureTypeAdapter);

    FormattingStyle formattingStyle = mock(FormattingStyle.class);
    when(formattingStyle.usesSpaceAfterSeparators()).thenReturn(true);
    when(formattingStyle.getNewline()).thenReturn("Newline");
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            excluder,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            formattingStyle,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();
    Type typeOfSrc = GsonBuilderTestFactory.createType();
    CharArrayWriter writer = new CharArrayWriter();

    // Act
    gson.toJson(createPublicFieldResult, typeOfSrc, writer);

    // Assert
    verify(formattingStyle, atLeast(1)).getNewline();
    verify(formattingStyle).usesSpaceAfterSeparators();
    verify(futureTypeAdapter).write(isA(JsonWriter.class), isA(Object.class));
    verify(excluder).create(isA(Gson.class), isA(TypeToken.class));
    assertEquals(5, writer.size());
  }

  /**
   * Test {@link Gson#toJson(Object, Type, Appendable)} with {@code Object}, {@code Type}, {@code
   * Appendable}.
   *
   * <ul>
   *   <li>Given {@link FutureTypeAdapter} {@link FutureTypeAdapter#write(JsonWriter, Object)} throw
   *       {@link IOException#IOException()}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(Object, Type, Appendable)"})
  public void testToJsonWithObjectTypeAppendable_givenFutureTypeAdapterWriteThrowIOException()
      throws JsonIOException, IOException, NoSuchFieldException {
    // Arrange
    FutureTypeAdapter<Object> futureTypeAdapter = mock(FutureTypeAdapter.class);
    doThrow(new IOException())
        .when(futureTypeAdapter)
        .write(Mockito.<JsonWriter>any(), Mockito.<Object>any());

    Excluder excluder = mock(Excluder.class);
    when(excluder.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(futureTypeAdapter);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            excluder,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();
    Type typeOfSrc = GsonBuilderTestFactory.createType();

    // Act and Assert
    assertThrows(
        JsonIOException.class,
        () -> gson.toJson(createPublicFieldResult, typeOfSrc, new CharArrayWriter()));
    verify(futureTypeAdapter).write(isA(JsonWriter.class), isA(Object.class));
    verify(excluder).create(isA(Gson.class), isA(TypeToken.class));
  }

  /**
   * Test {@link Gson#toJson(Object, Type, Appendable)} with {@code Object}, {@code Type}, {@code
   * Appendable}.
   *
   * <ul>
   *   <li>Then calls {@link FormattingStyle#getIndent()}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(Object, Type, Appendable)"})
  public void testToJsonWithObjectTypeAppendable_thenCallsGetIndent()
      throws JsonIOException, IOException, NoSuchFieldException {
    // Arrange
    FutureTypeAdapter<Object> futureTypeAdapter = mock(FutureTypeAdapter.class);
    doNothing().when(futureTypeAdapter).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());

    Excluder excluder = mock(Excluder.class);
    when(excluder.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(futureTypeAdapter);

    FormattingStyle formattingStyle = mock(FormattingStyle.class);
    when(formattingStyle.getIndent()).thenReturn("Indent");
    when(formattingStyle.usesSpaceAfterSeparators()).thenReturn(true);
    when(formattingStyle.getNewline()).thenReturn("");
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            excluder,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            formattingStyle,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();
    Type typeOfSrc = GsonBuilderTestFactory.createType();
    CharArrayWriter writer = new CharArrayWriter();

    // Act
    gson.toJson(createPublicFieldResult, typeOfSrc, writer);

    // Assert
    verify(formattingStyle).getIndent();
    verify(formattingStyle, atLeast(1)).getNewline();
    verify(formattingStyle).usesSpaceAfterSeparators();
    verify(futureTypeAdapter).write(isA(JsonWriter.class), isA(Object.class));
    verify(excluder).create(isA(Gson.class), isA(TypeToken.class));
    assertEquals(5, writer.size());
  }

  /**
   * Test {@link Gson#toJson(Object, Type, Appendable)} with {@code Object}, {@code Type}, {@code
   * Appendable}.
   *
   * <ul>
   *   <li>Then {@link CharArrayWriter#CharArrayWriter()} size is zero.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(Object, Type, Appendable)"})
  public void testToJsonWithObjectTypeAppendable_thenCharArrayWriterSizeIsZero()
      throws JsonIOException, IOException, NoSuchFieldException {
    // Arrange
    FutureTypeAdapter<Object> futureTypeAdapter = mock(FutureTypeAdapter.class);
    doNothing().when(futureTypeAdapter).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());

    Excluder excluder = mock(Excluder.class);
    when(excluder.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(futureTypeAdapter);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            excluder,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            false,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();
    Type typeOfSrc = GsonBuilderTestFactory.createType();
    CharArrayWriter writer = new CharArrayWriter();

    // Act
    gson.toJson(createPublicFieldResult, typeOfSrc, writer);

    // Assert that nothing has changed
    verify(futureTypeAdapter).write(isA(JsonWriter.class), isA(Object.class));
    verify(excluder).create(isA(Gson.class), isA(TypeToken.class));
    assertEquals(0, writer.size());
  }

  /**
   * Test {@link Gson#toJson(Object, Type, Appendable)} with {@code Object}, {@code Type}, {@code
   * Appendable}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then calls {@link FormattingStyle#getIndent()}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(Object, Type, Appendable)"})
  public void testToJsonWithObjectTypeAppendable_whenJavaLangObject_thenCallsGetIndent()
      throws JsonIOException, IOException, NoSuchFieldException {
    // Arrange
    FutureTypeAdapter<Object> futureTypeAdapter = mock(FutureTypeAdapter.class);
    doNothing().when(futureTypeAdapter).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());

    Excluder excluder = mock(Excluder.class);
    when(excluder.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(futureTypeAdapter);

    FormattingStyle formattingStyle = mock(FormattingStyle.class);
    when(formattingStyle.getIndent()).thenReturn("Indent");
    when(formattingStyle.usesSpaceAfterSeparators()).thenReturn(true);
    when(formattingStyle.getNewline()).thenReturn("");
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            excluder,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            formattingStyle,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();
    Class<Object> typeOfSrc = Object.class;
    CharArrayWriter writer = new CharArrayWriter();

    // Act
    gson.toJson(createPublicFieldResult, typeOfSrc, writer);

    // Assert
    verify(formattingStyle).getIndent();
    verify(formattingStyle, atLeast(1)).getNewline();
    verify(formattingStyle).usesSpaceAfterSeparators();
    verify(futureTypeAdapter).write(isA(JsonWriter.class), isA(Object.class));
    verify(excluder).create(isA(Gson.class), isA(TypeToken.class));
    assertEquals(5, writer.size());
  }

  /**
   * Test {@link Gson#toJson(Object, Type, Appendable)} with {@code Object}, {@code Type}, {@code
   * Appendable}.
   *
   * <ul>
   *   <li>When {@link PipedWriter#PipedWriter()}.
   *   <li>Then throw {@link JsonIOException}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type, Appendable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(Object, Type, Appendable)"})
  public void testToJsonWithObjectTypeAppendable_whenPipedWriter_thenThrowJsonIOException()
      throws JsonIOException, NoSuchFieldException {
    // Arrange
    Excluder excluder = mock(Excluder.class);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    FormattingStyle formattingStyle = mock(FormattingStyle.class);
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            excluder,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            formattingStyle,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();
    Type typeOfSrc = GsonBuilderTestFactory.createType();

    // Act and Assert
    assertThrows(
        JsonIOException.class,
        () -> gson.toJson(createPublicFieldResult, typeOfSrc, new PipedWriter()));
  }

  /**
   * Test {@link Gson#toJson(Object, Type, JsonWriter)} with {@code Object}, {@code Type}, {@code
   * JsonWriter}.
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(Object, Type, JsonWriter)"})
  public void testToJsonWithObjectTypeJsonWriter() throws JsonIOException, IOException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Type typeOfSrc = GsonBuilderTestFactory.createType();

    JsonWriter writer = mock(JsonWriter.class);
    when(writer.value(Mockito.<String>any())).thenReturn(new JsonWriter(new StringWriter()));
    when(writer.getStrictness()).thenReturn(Strictness.LEGACY_STRICT);
    when(writer.getSerializeNulls()).thenReturn(true);
    when(writer.isHtmlSafe()).thenReturn(true);
    doNothing().when(writer).setHtmlSafe(anyBoolean());
    doNothing().when(writer).setSerializeNulls(anyBoolean());
    doNothing().when(writer).setStrictness(Mockito.<Strictness>any());

    // Act
    gson.toJson("type must not be null", typeOfSrc, writer);

    // Assert
    verify(writer).getSerializeNulls();
    verify(writer).getStrictness();
    verify(writer).isHtmlSafe();
    verify(writer, atLeast(1)).setHtmlSafe(true);
    verify(writer, atLeast(1)).setSerializeNulls(true);
    verify(writer, atLeast(1)).setStrictness(Mockito.<Strictness>any());
    verify(writer).value("type must not be null");
  }

  /**
   * Test {@link Gson#toJson(Object, Type, JsonWriter)} with {@code Object}, {@code Type}, {@code
   * JsonWriter}.
   *
   * <ul>
   *   <li>Given {@code Object}.
   *   <li>Then calls {@link JsonWriter#beginArray()}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(Object, Type, JsonWriter)"})
  public void testToJsonWithObjectTypeJsonWriter_givenJavaLangObject_thenCallsBeginArray()
      throws JsonIOException, IOException {
    // Arrange
    Excluder excluder = mock(Excluder.class);
    Gson context = new Gson();
    FutureTypeAdapter<Object> componentTypeAdapter = new FutureTypeAdapter<>();
    Class<Object> componentType = Object.class;

    ArrayTypeAdapter<Object> arrayTypeAdapter =
        new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType);
    when(excluder.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(arrayTypeAdapter);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            excluder,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Type typeOfSrc = GsonBuilderTestFactory.createIntegerType();

    JsonWriter writer = mock(JsonWriter.class);
    when(writer.beginArray()).thenThrow(new IOException());
    when(writer.getStrictness()).thenReturn(Strictness.LEGACY_STRICT);
    when(writer.getSerializeNulls()).thenReturn(true);
    when(writer.isHtmlSafe()).thenReturn(true);
    doNothing().when(writer).setHtmlSafe(anyBoolean());
    doNothing().when(writer).setSerializeNulls(anyBoolean());
    doNothing().when(writer).setStrictness(Mockito.<Strictness>any());

    // Act and Assert
    assertThrows(
        JsonIOException.class, () -> gson.toJson("type must not be null", typeOfSrc, writer));
    verify(excluder).create(isA(Gson.class), isA(TypeToken.class));
    verify(writer).beginArray();
    verify(writer).getSerializeNulls();
    verify(writer).getStrictness();
    verify(writer).isHtmlSafe();
    verify(writer, atLeast(1)).setHtmlSafe(true);
    verify(writer, atLeast(1)).setSerializeNulls(true);
    verify(writer, atLeast(1)).setStrictness(Mockito.<Strictness>any());
  }

  /**
   * Test {@link Gson#toJson(Object, Type, JsonWriter)} with {@code Object}, {@code Type}, {@code
   * JsonWriter}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(Object, Type, JsonWriter)"})
  public void testToJsonWithObjectTypeJsonWriter_givenLenient()
      throws JsonIOException, IOException {
    // Arrange
    Gson gson = new Gson();
    Type typeOfSrc = GsonBuilderTestFactory.createType();

    JsonWriter writer = mock(JsonWriter.class);
    when(writer.value(Mockito.<String>any())).thenReturn(new JsonWriter(new StringWriter()));
    when(writer.getStrictness()).thenReturn(Strictness.LENIENT);
    when(writer.getSerializeNulls()).thenReturn(true);
    when(writer.isHtmlSafe()).thenReturn(true);
    doNothing().when(writer).setHtmlSafe(anyBoolean());
    doNothing().when(writer).setSerializeNulls(anyBoolean());
    doNothing().when(writer).setStrictness(Mockito.<Strictness>any());

    // Act
    gson.toJson("type must not be null", typeOfSrc, writer);

    // Assert
    verify(writer).getSerializeNulls();
    verify(writer, atLeast(1)).getStrictness();
    verify(writer).isHtmlSafe();
    verify(writer, atLeast(1)).setHtmlSafe(true);
    verify(writer, atLeast(1)).setSerializeNulls(anyBoolean());
    verify(writer).setStrictness(Strictness.LENIENT);
    verify(writer).value("type must not be null");
  }

  /**
   * Test {@link Gson#toJson(Object, Type, JsonWriter)} with {@code Object}, {@code Type}, {@code
   * JsonWriter}.
   *
   * <ul>
   *   <li>Then calls {@link JsonWriter#value(String)}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(Object, Type, JsonWriter)"})
  public void testToJsonWithObjectTypeJsonWriter_thenCallsValue()
      throws JsonIOException, IOException {
    // Arrange
    Gson gson = new Gson();
    Type typeOfSrc = GsonBuilderTestFactory.createType();

    JsonWriter writer = mock(JsonWriter.class);
    when(writer.value(Mockito.<String>any())).thenReturn(new JsonWriter(new StringWriter()));
    when(writer.getStrictness()).thenReturn(Strictness.LEGACY_STRICT);
    when(writer.getSerializeNulls()).thenReturn(true);
    when(writer.isHtmlSafe()).thenReturn(true);
    doNothing().when(writer).setHtmlSafe(anyBoolean());
    doNothing().when(writer).setSerializeNulls(anyBoolean());
    doNothing().when(writer).setStrictness(Mockito.<Strictness>any());

    // Act
    gson.toJson("type must not be null", typeOfSrc, writer);

    // Assert
    verify(writer).getSerializeNulls();
    verify(writer, atLeast(1)).getStrictness();
    verify(writer).isHtmlSafe();
    verify(writer, atLeast(1)).setHtmlSafe(true);
    verify(writer, atLeast(1)).setSerializeNulls(anyBoolean());
    verify(writer, atLeast(1)).setStrictness(Mockito.<Strictness>any());
    verify(writer).value("type must not be null");
  }

  /**
   * Test {@link Gson#toJson(Object, Type, JsonWriter)} with {@code Object}, {@code Type}, {@code
   * JsonWriter}.
   *
   * <ul>
   *   <li>Then calls {@link FutureTypeAdapter#write(JsonWriter, Object)}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(Object, Type, JsonWriter)"})
  public void testToJsonWithObjectTypeJsonWriter_thenCallsWrite()
      throws JsonIOException, IOException {
    // Arrange
    FutureTypeAdapter<Object> futureTypeAdapter = mock(FutureTypeAdapter.class);
    doNothing().when(futureTypeAdapter).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());

    Excluder excluder = mock(Excluder.class);
    when(excluder.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(futureTypeAdapter);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            excluder,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Type typeOfSrc = GsonBuilderTestFactory.createIntegerType();

    JsonWriter writer = mock(JsonWriter.class);
    when(writer.getStrictness()).thenReturn(Strictness.LEGACY_STRICT);
    when(writer.getSerializeNulls()).thenReturn(true);
    when(writer.isHtmlSafe()).thenReturn(true);
    doNothing().when(writer).setHtmlSafe(anyBoolean());
    doNothing().when(writer).setSerializeNulls(anyBoolean());
    doNothing().when(writer).setStrictness(Mockito.<Strictness>any());

    // Act
    gson.toJson("type must not be null", typeOfSrc, writer);

    // Assert
    verify(futureTypeAdapter).write(isA(JsonWriter.class), isA(Object.class));
    verify(excluder).create(isA(Gson.class), isA(TypeToken.class));
    verify(writer).getSerializeNulls();
    verify(writer).getStrictness();
    verify(writer).isHtmlSafe();
    verify(writer, atLeast(1)).setHtmlSafe(true);
    verify(writer, atLeast(1)).setSerializeNulls(true);
    verify(writer, atLeast(1)).setStrictness(Mockito.<Strictness>any());
  }

  /**
   * Test {@link Gson#toJson(Object, Type, JsonWriter)} with {@code Object}, {@code Type}, {@code
   * JsonWriter}.
   *
   * <ul>
   *   <li>Then calls {@link FutureTypeAdapter#write(JsonWriter, Object)}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(Object, Type, JsonWriter)"})
  public void testToJsonWithObjectTypeJsonWriter_thenCallsWrite2()
      throws JsonIOException, IOException {
    // Arrange
    FutureTypeAdapter<Object> futureTypeAdapter = mock(FutureTypeAdapter.class);
    doNothing().when(futureTypeAdapter).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());

    Excluder excluder = mock(Excluder.class);
    when(excluder.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(futureTypeAdapter);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            excluder,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> typeOfSrc = Object.class;

    JsonWriter writer = mock(JsonWriter.class);
    when(writer.getStrictness()).thenReturn(Strictness.LEGACY_STRICT);
    when(writer.getSerializeNulls()).thenReturn(true);
    when(writer.isHtmlSafe()).thenReturn(true);
    doNothing().when(writer).setHtmlSafe(anyBoolean());
    doNothing().when(writer).setSerializeNulls(anyBoolean());
    doNothing().when(writer).setStrictness(Mockito.<Strictness>any());

    // Act
    gson.toJson("type must not be null", typeOfSrc, writer);

    // Assert
    verify(futureTypeAdapter).write(isA(JsonWriter.class), isA(Object.class));
    verify(excluder).create(isA(Gson.class), isA(TypeToken.class));
    verify(writer).getSerializeNulls();
    verify(writer).getStrictness();
    verify(writer).isHtmlSafe();
    verify(writer, atLeast(1)).setHtmlSafe(true);
    verify(writer, atLeast(1)).setSerializeNulls(true);
    verify(writer, atLeast(1)).setStrictness(Mockito.<Strictness>any());
  }

  /**
   * Test {@link Gson#toJson(Object, Type, JsonWriter)} with {@code Object}, {@code Type}, {@code
   * JsonWriter}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(Object, Type, JsonWriter)"})
  public void testToJsonWithObjectTypeJsonWriter_thenThrowIllegalArgumentException()
      throws JsonIOException, IOException {
    // Arrange
    Gson gson = new Gson();
    Type typeOfSrc = GsonBuilderTestFactory.createType();

    JsonWriter writer = mock(JsonWriter.class);
    when(writer.value(Mockito.<String>any())).thenThrow(new IllegalArgumentException());
    when(writer.getStrictness()).thenReturn(Strictness.LEGACY_STRICT);
    when(writer.getSerializeNulls()).thenReturn(true);
    when(writer.isHtmlSafe()).thenReturn(true);
    doNothing().when(writer).setHtmlSafe(anyBoolean());
    doNothing().when(writer).setSerializeNulls(anyBoolean());
    doNothing().when(writer).setStrictness(Mockito.<Strictness>any());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> gson.toJson("type must not be null", typeOfSrc, writer));
    verify(writer).getSerializeNulls();
    verify(writer, atLeast(1)).getStrictness();
    verify(writer).isHtmlSafe();
    verify(writer, atLeast(1)).setHtmlSafe(true);
    verify(writer, atLeast(1)).setSerializeNulls(anyBoolean());
    verify(writer, atLeast(1)).setStrictness(Mockito.<Strictness>any());
    verify(writer).value("type must not be null");
  }

  /**
   * Test {@link Gson#toJson(Object, Type, JsonWriter)} with {@code Object}, {@code Type}, {@code
   * JsonWriter}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(Object, Type, JsonWriter)"})
  public void testToJsonWithObjectTypeJsonWriter_thenThrowIllegalArgumentException2()
      throws JsonIOException, IOException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> typeOfSrc = Object.class;

    JsonWriter writer = mock(JsonWriter.class);
    when(writer.value(Mockito.<String>any())).thenThrow(new IllegalArgumentException());
    when(writer.getStrictness()).thenReturn(Strictness.LEGACY_STRICT);
    when(writer.getSerializeNulls()).thenReturn(true);
    when(writer.isHtmlSafe()).thenReturn(true);
    doNothing().when(writer).setHtmlSafe(anyBoolean());
    doNothing().when(writer).setSerializeNulls(anyBoolean());
    doNothing().when(writer).setStrictness(Mockito.<Strictness>any());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> gson.toJson("type must not be null", typeOfSrc, writer));
    verify(writer).getSerializeNulls();
    verify(writer, atLeast(1)).getStrictness();
    verify(writer).isHtmlSafe();
    verify(writer, atLeast(1)).setHtmlSafe(true);
    verify(writer, atLeast(1)).setSerializeNulls(anyBoolean());
    verify(writer, atLeast(1)).setStrictness(Mockito.<Strictness>any());
    verify(writer).value("type must not be null");
  }

  /**
   * Test {@link Gson#toJson(Object, Type, JsonWriter)} with {@code Object}, {@code Type}, {@code
   * JsonWriter}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then calls {@link JsonWriter#value(String)}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(Object, Type, JsonWriter)"})
  public void testToJsonWithObjectTypeJsonWriter_whenJavaLangObject_thenCallsValue()
      throws JsonIOException, IOException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> typeOfSrc = Object.class;

    JsonWriter writer = mock(JsonWriter.class);
    when(writer.value(Mockito.<String>any())).thenReturn(new JsonWriter(new StringWriter()));
    when(writer.getStrictness()).thenReturn(Strictness.LEGACY_STRICT);
    when(writer.getSerializeNulls()).thenReturn(true);
    when(writer.isHtmlSafe()).thenReturn(true);
    doNothing().when(writer).setHtmlSafe(anyBoolean());
    doNothing().when(writer).setSerializeNulls(anyBoolean());
    doNothing().when(writer).setStrictness(Mockito.<Strictness>any());

    // Act
    gson.toJson("type must not be null", typeOfSrc, writer);

    // Assert
    verify(writer).getSerializeNulls();
    verify(writer, atLeast(1)).getStrictness();
    verify(writer).isHtmlSafe();
    verify(writer, atLeast(1)).setHtmlSafe(true);
    verify(writer, atLeast(1)).setSerializeNulls(anyBoolean());
    verify(writer, atLeast(1)).setStrictness(Mockito.<Strictness>any());
    verify(writer).value("type must not be null");
  }

  /**
   * Test {@link Gson#toJson(Object, Type, JsonWriter)} with {@code Object}, {@code Type}, {@code
   * JsonWriter}.
   *
   * <ul>
   *   <li>When {@link JsonWriter} {@link JsonWriter#value(String)} throw {@link
   *       IOException#IOException()}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type, JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Gson.toJson(Object, Type, JsonWriter)"})
  public void testToJsonWithObjectTypeJsonWriter_whenJsonWriterValueThrowIOException()
      throws JsonIOException, IOException {
    // Arrange
    Gson gson = new Gson();
    Type typeOfSrc = GsonBuilderTestFactory.createType();

    JsonWriter writer = mock(JsonWriter.class);
    when(writer.value(Mockito.<String>any())).thenThrow(new IOException());
    when(writer.getStrictness()).thenReturn(Strictness.LEGACY_STRICT);
    when(writer.getSerializeNulls()).thenReturn(true);
    when(writer.isHtmlSafe()).thenReturn(true);
    doNothing().when(writer).setHtmlSafe(anyBoolean());
    doNothing().when(writer).setSerializeNulls(anyBoolean());
    doNothing().when(writer).setStrictness(Mockito.<Strictness>any());

    // Act and Assert
    assertThrows(
        JsonIOException.class, () -> gson.toJson("type must not be null", typeOfSrc, writer));
    verify(writer).getSerializeNulls();
    verify(writer, atLeast(1)).getStrictness();
    verify(writer).isHtmlSafe();
    verify(writer, atLeast(1)).setHtmlSafe(true);
    verify(writer, atLeast(1)).setSerializeNulls(anyBoolean());
    verify(writer, atLeast(1)).setStrictness(Mockito.<Strictness>any());
    verify(writer).value("type must not be null");
  }

  /**
   * Test {@link Gson#toJson(Object, Type)} with {@code Object}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link FormattingStyle} {@link FormattingStyle#getNewline()} return {@code
   *       Newline}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object, Type)"})
  public void testToJsonWithObjectType_givenFormattingStyleGetNewlineReturnNewline()
      throws IOException, NoSuchFieldException {
    // Arrange
    FutureTypeAdapter<Object> futureTypeAdapter = mock(FutureTypeAdapter.class);
    doNothing().when(futureTypeAdapter).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());

    Excluder excluder = mock(Excluder.class);
    when(excluder.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(futureTypeAdapter);

    FormattingStyle formattingStyle = mock(FormattingStyle.class);
    when(formattingStyle.usesSpaceAfterSeparators()).thenReturn(true);
    when(formattingStyle.getNewline()).thenReturn("Newline");
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            excluder,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            formattingStyle,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();

    // Act
    String actualToJsonResult =
        gson.toJson(createPublicFieldResult, GsonBuilderTestFactory.createType());

    // Assert
    verify(formattingStyle, atLeast(1)).getNewline();
    verify(formattingStyle).usesSpaceAfterSeparators();
    verify(futureTypeAdapter).write(isA(JsonWriter.class), isA(Object.class));
    verify(excluder).create(isA(Gson.class), isA(TypeToken.class));
    assertEquals(")]}'\n", actualToJsonResult);
  }

  /**
   * Test {@link Gson#toJson(Object, Type)} with {@code Object}, {@code Type}.
   *
   * <ul>
   *   <li>Then calls {@link FormattingStyle#getIndent()}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object, Type)"})
  public void testToJsonWithObjectType_thenCallsGetIndent()
      throws IOException, NoSuchFieldException {
    // Arrange
    FutureTypeAdapter<Object> futureTypeAdapter = mock(FutureTypeAdapter.class);
    doNothing().when(futureTypeAdapter).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());

    Excluder excluder = mock(Excluder.class);
    when(excluder.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(futureTypeAdapter);

    FormattingStyle formattingStyle = mock(FormattingStyle.class);
    when(formattingStyle.getIndent()).thenReturn("Indent");
    when(formattingStyle.usesSpaceAfterSeparators()).thenReturn(true);
    when(formattingStyle.getNewline()).thenReturn("");
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            excluder,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            formattingStyle,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();

    // Act
    String actualToJsonResult =
        gson.toJson(createPublicFieldResult, GsonBuilderTestFactory.createType());

    // Assert
    verify(formattingStyle).getIndent();
    verify(formattingStyle, atLeast(1)).getNewline();
    verify(formattingStyle).usesSpaceAfterSeparators();
    verify(futureTypeAdapter).write(isA(JsonWriter.class), isA(Object.class));
    verify(excluder).create(isA(Gson.class), isA(TypeToken.class));
    assertEquals(")]}'\n", actualToJsonResult);
  }

  /**
   * Test {@link Gson#toJson(Object, Type)} with {@code Object}, {@code Type}.
   *
   * <ul>
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object, Type)"})
  public void testToJsonWithObjectType_thenReturnEmptyString()
      throws IOException, NoSuchFieldException {
    // Arrange
    FutureTypeAdapter<Object> futureTypeAdapter = mock(FutureTypeAdapter.class);
    doNothing().when(futureTypeAdapter).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());

    Excluder excluder = mock(Excluder.class);
    when(excluder.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(futureTypeAdapter);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            excluder,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            false,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();

    // Act
    String actualToJsonResult =
        gson.toJson(createPublicFieldResult, GsonBuilderTestFactory.createType());

    // Assert
    verify(futureTypeAdapter).write(isA(JsonWriter.class), isA(Object.class));
    verify(excluder).create(isA(Gson.class), isA(TypeToken.class));
    assertEquals("", actualToJsonResult);
  }

  /**
   * Test {@link Gson#toJson(Object, Type)} with {@code Object}, {@code Type}.
   *
   * <ul>
   *   <li>Then throw {@link JsonIOException}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object, Type)"})
  public void testToJsonWithObjectType_thenThrowJsonIOException()
      throws IOException, NoSuchFieldException {
    // Arrange
    FutureTypeAdapter<Object> futureTypeAdapter = mock(FutureTypeAdapter.class);
    doThrow(new IOException())
        .when(futureTypeAdapter)
        .write(Mockito.<JsonWriter>any(), Mockito.<Object>any());

    Excluder excluder = mock(Excluder.class);
    when(excluder.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(futureTypeAdapter);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            excluder,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();

    // Act and Assert
    assertThrows(
        JsonIOException.class,
        () -> gson.toJson(createPublicFieldResult, GsonBuilderTestFactory.createType()));
    verify(futureTypeAdapter).write(isA(JsonWriter.class), isA(Object.class));
    verify(excluder).create(isA(Gson.class), isA(TypeToken.class));
  }

  /**
   * Test {@link Gson#toJson(Object, Type)} with {@code Object}, {@code Type}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then calls {@link FormattingStyle#getIndent()}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object, Type)"})
  public void testToJsonWithObjectType_whenJavaLangObject_thenCallsGetIndent()
      throws IOException, NoSuchFieldException {
    // Arrange
    FutureTypeAdapter<Object> futureTypeAdapter = mock(FutureTypeAdapter.class);
    doNothing().when(futureTypeAdapter).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());

    Excluder excluder = mock(Excluder.class);
    when(excluder.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(futureTypeAdapter);

    FormattingStyle formattingStyle = mock(FormattingStyle.class);
    when(formattingStyle.getIndent()).thenReturn("Indent");
    when(formattingStyle.usesSpaceAfterSeparators()).thenReturn(true);
    when(formattingStyle.getNewline()).thenReturn("");
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            excluder,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            formattingStyle,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();
    Class<Object> typeOfSrc = Object.class;

    // Act
    String actualToJsonResult = gson.toJson(createPublicFieldResult, typeOfSrc);

    // Assert
    verify(formattingStyle).getIndent();
    verify(formattingStyle, atLeast(1)).getNewline();
    verify(formattingStyle).usesSpaceAfterSeparators();
    verify(futureTypeAdapter).write(isA(JsonWriter.class), isA(Object.class));
    verify(excluder).create(isA(Gson.class), isA(TypeToken.class));
    assertEquals(")]}'\n", actualToJsonResult);
  }

  /**
   * Test {@link Gson#toJson(Object)} with {@code Object}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>Then return {@code {}}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object)"})
  public void testToJsonWithObject_givenGson_thenReturnLeftCurlyBracketRightCurlyBracket()
      throws NoSuchFieldException {
    // Arrange
    Gson gson = new Gson();

    // Act and Assert
    assertEquals("{}", gson.toJson(ReflectionHelperTestFactory.createPublicField()));
  }

  /**
   * Test {@link Gson#toJson(Object)} with {@code Object}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#toJson(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Gson.toJson(Object)"})
  public void testToJsonWithObject_givenGson_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertEquals("null", new Gson().toJson((Object) null));
  }

  /**
   * Test {@link Gson#newJsonWriter(Writer)}.
   *
   * <p>Method under test: {@link Gson#newJsonWriter(Writer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter Gson.newJsonWriter(Writer)"})
  public void testNewJsonWriter() throws IOException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringWriter writer = new StringWriter();

    // Act
    JsonWriter actualNewJsonWriterResult = gson.newJsonWriter(writer);

    // Assert
    assertEquals(")]}'\n", writer.toString());
    assertEquals(Strictness.LENIENT, actualNewJsonWriterResult.getStrictness());
    assertTrue(actualNewJsonWriterResult.getSerializeNulls());
    assertTrue(actualNewJsonWriterResult.isLenient());
    assertSame(gson.formattingStyle, actualNewJsonWriterResult.getFormattingStyle());
  }

  /**
   * Test {@link Gson#newJsonWriter(Writer)}.
   *
   * <p>Method under test: {@link Gson#newJsonWriter(Writer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter Gson.newJsonWriter(Writer)"})
  public void testNewJsonWriter2() throws IOException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            FormattingStyle.PRETTY,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            Integer.SIZE,
            Integer.SIZE,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringWriter writer = new StringWriter();

    // Act
    JsonWriter actualNewJsonWriterResult = gson.newJsonWriter(writer);

    // Assert
    assertEquals(")]}'\n", writer.toString());
    assertSame(gson.formattingStyle, actualNewJsonWriterResult.getFormattingStyle());
  }

  /**
   * Test {@link Gson#newJsonWriter(Writer)}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@link StringWriter#StringWriter()}.
   *   <li>Then return Strictness is {@code LEGACY_STRICT}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#newJsonWriter(Writer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter Gson.newJsonWriter(Writer)"})
  public void testNewJsonWriter_givenGson_whenStringWriter_thenReturnStrictnessIsLegacyStrict()
      throws IOException {
    // Arrange
    Gson gson = new Gson();

    // Act
    JsonWriter actualNewJsonWriterResult = gson.newJsonWriter(new StringWriter());

    // Assert
    assertEquals(Strictness.LEGACY_STRICT, actualNewJsonWriterResult.getStrictness());
    assertFalse(actualNewJsonWriterResult.getSerializeNulls());
    assertFalse(actualNewJsonWriterResult.isLenient());
    assertSame(gson.formattingStyle, actualNewJsonWriterResult.getFormattingStyle());
  }

  /**
   * Test {@link Gson#newJsonWriter(Writer)}.
   *
   * <ul>
   *   <li>Then {@link StringWriter#StringWriter()} toString is empty string.
   * </ul>
   *
   * <p>Method under test: {@link Gson#newJsonWriter(Writer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter Gson.newJsonWriter(Writer)"})
  public void testNewJsonWriter_thenStringWriterToStringIsEmptyString() throws IOException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            false,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringWriter writer = new StringWriter();

    // Act
    JsonWriter actualNewJsonWriterResult = gson.newJsonWriter(writer);

    // Assert
    assertEquals("", writer.toString());
    assertEquals(Strictness.LENIENT, actualNewJsonWriterResult.getStrictness());
    assertTrue(actualNewJsonWriterResult.getSerializeNulls());
    assertTrue(actualNewJsonWriterResult.isLenient());
    assertSame(gson.formattingStyle, actualNewJsonWriterResult.getFormattingStyle());
  }

  /**
   * Test {@link Gson#newJsonReader(Reader)}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>Then return Strictness is {@code LEGACY_STRICT}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#newJsonReader(Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonReader Gson.newJsonReader(Reader)"})
  public void testNewJsonReader_givenGson_thenReturnStrictnessIsLegacyStrict() {
    // Arrange
    Gson gson = new Gson();

    // Act
    JsonReader actualNewJsonReaderResult = gson.newJsonReader(new StringReader("foo"));

    // Assert
    assertEquals("$", actualNewJsonReaderResult.getPath());
    assertEquals("$", actualNewJsonReaderResult.getPreviousPath());
    assertEquals(255, actualNewJsonReaderResult.getNestingLimit());
    assertEquals(Strictness.LEGACY_STRICT, actualNewJsonReaderResult.getStrictness());
    assertFalse(actualNewJsonReaderResult.isLenient());
  }

  /**
   * Test {@link Gson#newJsonReader(Reader)}.
   *
   * <ul>
   *   <li>Then return Strictness is {@code LENIENT}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#newJsonReader(Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonReader Gson.newJsonReader(Reader)"})
  public void testNewJsonReader_thenReturnStrictnessIsLenient() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());

    // Act
    JsonReader actualNewJsonReaderResult = gson.newJsonReader(new StringReader("foo"));

    // Assert
    assertEquals("$", actualNewJsonReaderResult.getPath());
    assertEquals("$", actualNewJsonReaderResult.getPreviousPath());
    assertEquals(255, actualNewJsonReaderResult.getNestingLimit());
    assertEquals(Strictness.LENIENT, actualNewJsonReaderResult.getStrictness());
    assertTrue(actualNewJsonReaderResult.isLenient());
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Class)} with {@code JsonElement}, {@code Class}.
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Class)"})
  public void testFromJsonWithJsonElementClass() throws JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new EOFException());
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonArray json = JsonArrayTestFactory.createJsonArrayWithNumbers();
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, classOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Class)} with {@code JsonElement}, {@code Class}.
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Class)"})
  public void testFromJsonWithJsonElementClass2() throws JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new IllegalStateException());
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonArray json = JsonArrayTestFactory.createJsonArrayWithNumbers();
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, classOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Class)} with {@code JsonElement}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>Then return size is three.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Class)"})
  public void testFromJsonWithJsonElementClass_givenGson_thenReturnSizeIsThree()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonArray json = JsonArrayTestFactory.createJsonArrayWithNumbers();
    Class<Object> classOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof List);
    assertEquals(3, ((List<Double>) actualFromJsonResult).size());
    assertEquals(1.0d, ((List<Double>) actualFromJsonResult).get(0).doubleValue(), 0.0);
    assertEquals(2.0d, ((List<Double>) actualFromJsonResult).get(1).doubleValue(), 0.0);
    assertEquals(3.0d, ((List<Double>) actualFromJsonResult).get(2).doubleValue(), 0.0);
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Class)} with {@code JsonElement}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@link JsonNull#INSTANCE}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Class)"})
  public void testFromJsonWithJsonElementClass_givenGson_whenInstance_thenReturnNull()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertNull(gson.fromJson(JsonNull.INSTANCE, classOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Class)} with {@code JsonElement}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@link JsonObject} (default constructor).
   *   <li>Then return {@link Map}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Class)"})
  public void testFromJsonWithJsonElementClass_givenGson_whenJsonObject_thenReturnMap()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonObject json = new JsonObject();
    Class<Object> classOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertTrue(((Map<Object, Object>) actualFromJsonResult).isEmpty());
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Class)} with {@code JsonElement}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Class)"})
  public void testFromJsonWithJsonElementClass_givenGson_whenNull_thenReturnNull()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertNull(gson.fromJson((JsonElement) null, classOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Class)} with {@code JsonElement}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@link ToNumberStrategy} {@link ToNumberStrategy#readNumber(JsonReader)} throw
   *       {@link IOException#IOException()}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Class)"})
  public void testFromJsonWithJsonElementClass_givenToNumberStrategyReadNumberThrowIOException()
      throws JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any())).thenThrow(new IOException());
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonArray json = JsonArrayTestFactory.createJsonArrayWithNumbers();
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, classOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Class)} with {@code JsonElement}, {@code Class}.
   *
   * <ul>
   *   <li>Then return {@code in == null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Class)"})
  public void testFromJsonWithJsonElementClass_thenReturnInNull() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonPrimitive json = new JsonPrimitive("in == null");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertEquals("in == null", gson.fromJson(json, classOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Class)} with {@code JsonElement}, {@code Class}.
   *
   * <ul>
   *   <li>Then return size is four.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Class)"})
  public void testFromJsonWithJsonElementClass_thenReturnSizeIsFour() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonArray json = JsonArrayTestFactory.createJsonArrayWithMixedTypes();
    Class<Object> classOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof List);
    assertEquals(4, ((List<Object>) actualFromJsonResult).size());
    Object getResult = ((List<Object>) actualFromJsonResult).get(3);
    assertTrue(getResult instanceof Map);
    assertEquals("string", ((List<Object>) actualFromJsonResult).get(0));
    assertEquals(42.0d, ((Double) ((List<Object>) actualFromJsonResult).get(1)).doubleValue(), 0.0);
    assertTrue(((Map<Object, Object>) getResult).isEmpty());
    assertTrue((Boolean) ((List<Object>) actualFromJsonResult).get(2));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Class)} with {@code JsonElement}, {@code Class}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Class)"})
  public void testFromJsonWithJsonElementClass_thenReturnSizeIsOne() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonArray json = JsonArrayTestFactory.createJsonArrayWithOneElement();
    Class<Object> classOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof List);
    assertEquals(1, ((List<String>) actualFromJsonResult).size());
    assertEquals("singleElement", ((List<String>) actualFromJsonResult).get(0));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Class)} with {@code JsonElement}, {@code Class}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Class)"})
  public void testFromJsonWithJsonElementClass_thenReturnSizeIsOne2() throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonArray json = JsonArrayTestFactory.createJsonArrayWithOneElement();
    Class<Object> classOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof List);
    assertEquals(1, ((List<String>) actualFromJsonResult).size());
    assertEquals("singleElement", ((List<String>) actualFromJsonResult).get(0));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Class)} with {@code JsonElement}, {@code Class}.
   *
   * <ul>
   *   <li>Then return size is two.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Class)"})
  public void testFromJsonWithJsonElementClass_thenReturnSizeIsTwo() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonArray json = JsonArrayTestFactory.createJsonArrayWithOneElement();
    json.add(JsonArrayTestFactory.createJsonArrayWithOneElement());
    Class<Object> classOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof List);
    assertEquals(2, ((List<Object>) actualFromJsonResult).size());
    Object getResult = ((List<Object>) actualFromJsonResult).get(1);
    assertTrue(getResult instanceof List);
    assertEquals(1, ((List<String>) getResult).size());
    assertEquals("singleElement", ((List<String>) getResult).get(0));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Class)} with {@code JsonElement}, {@code Class}.
   *
   * <ul>
   *   <li>When {@code LazilyParsedNumber}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Class)"})
  public void testFromJsonWithJsonElementClass_whenComGoogleGsonInternalLazilyParsedNumber()
      throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonArray json = JsonArrayTestFactory.createJsonArrayWithNumbers();
    Class<LazilyParsedNumber> classOfT = LazilyParsedNumber.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, classOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Class)} with {@code JsonElement}, {@code Class}.
   *
   * <ul>
   *   <li>When {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Class)"})
  public void testFromJsonWithJsonElementClass_whenJsonPrimitiveWithBoolIsTrue_thenReturnTrue()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonPrimitive json = new JsonPrimitive(true);
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertTrue((Boolean) gson.fromJson(json, classOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, TypeToken)} with {@code JsonElement}, {@code TypeToken}.
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, TypeToken)"})
  public void testFromJsonWithJsonElementTypeToken() throws JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any())).thenThrow(new IOException());
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            255,
            255,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonArray json = JsonArrayTestFactory.createJsonArrayWithNumbers();
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, TypeToken)} with {@code JsonElement}, {@code TypeToken}.
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, TypeToken)"})
  public void testFromJsonWithJsonElementTypeToken2() throws JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new EOFException());
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            255,
            255,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonArray json = JsonArrayTestFactory.createJsonArrayWithNumbers();
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, TypeToken)} with {@code JsonElement}, {@code TypeToken}.
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, TypeToken)"})
  public void testFromJsonWithJsonElementTypeToken3() throws JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new IllegalStateException());
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            255,
            255,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonArray json = JsonArrayTestFactory.createJsonArrayWithNumbers();
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, TypeToken)} with {@code JsonElement}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>Then return size is three.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, TypeToken)"})
  public void testFromJsonWithJsonElementTypeToken_givenGson_thenReturnSizeIsThree()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonArray json = JsonArrayTestFactory.createJsonArrayWithNumbers();
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(json, typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof List);
    assertEquals(3, ((List<Double>) actualFromJsonResult).size());
    assertEquals(1.0d, ((List<Double>) actualFromJsonResult).get(0).doubleValue(), 0.0);
    assertEquals(2.0d, ((List<Double>) actualFromJsonResult).get(1).doubleValue(), 0.0);
    assertEquals(3.0d, ((List<Double>) actualFromJsonResult).get(2).doubleValue(), 0.0);
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, TypeToken)} with {@code JsonElement}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@link JsonNull#INSTANCE}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, TypeToken)"})
  public void testFromJsonWithJsonElementTypeToken_givenGson_whenInstance_thenReturnNull()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertNull(gson.fromJson(JsonNull.INSTANCE, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, TypeToken)} with {@code JsonElement}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@link JsonObject} (default constructor).
   *   <li>Then return {@link Map}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, TypeToken)"})
  public void testFromJsonWithJsonElementTypeToken_givenGson_whenJsonObject_thenReturnMap()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonObject json = new JsonObject();
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(json, typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertTrue(((Map<Object, Object>) actualFromJsonResult).isEmpty());
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, TypeToken)} with {@code JsonElement}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, TypeToken)"})
  public void testFromJsonWithJsonElementTypeToken_givenGson_whenNull_thenReturnNull()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertNull(gson.fromJson((JsonElement) null, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, TypeToken)} with {@code JsonElement}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Then return {@code in == null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, TypeToken)"})
  public void testFromJsonWithJsonElementTypeToken_thenReturnInNull() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonPrimitive json = new JsonPrimitive("in == null");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertEquals("in == null", gson.fromJson(json, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, TypeToken)} with {@code JsonElement}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Then return size is four.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, TypeToken)"})
  public void testFromJsonWithJsonElementTypeToken_thenReturnSizeIsFour()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonArray json = JsonArrayTestFactory.createJsonArrayWithMixedTypes();
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(json, typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof List);
    assertEquals(4, ((List<Object>) actualFromJsonResult).size());
    Object getResult = ((List<Object>) actualFromJsonResult).get(3);
    assertTrue(getResult instanceof Map);
    assertEquals("string", ((List<Object>) actualFromJsonResult).get(0));
    assertEquals(42.0d, ((Double) ((List<Object>) actualFromJsonResult).get(1)).doubleValue(), 0.0);
    assertTrue(((Map<Object, Object>) getResult).isEmpty());
    assertTrue((Boolean) ((List<Object>) actualFromJsonResult).get(2));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, TypeToken)} with {@code JsonElement}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, TypeToken)"})
  public void testFromJsonWithJsonElementTypeToken_thenReturnSizeIsOne()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonArray json = JsonArrayTestFactory.createJsonArrayWithOneElement();
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(json, typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof List);
    assertEquals(1, ((List<String>) actualFromJsonResult).size());
    assertEquals("singleElement", ((List<String>) actualFromJsonResult).get(0));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, TypeToken)} with {@code JsonElement}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, TypeToken)"})
  public void testFromJsonWithJsonElementTypeToken_thenReturnSizeIsOne2()
      throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            255,
            255,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonArray json = JsonArrayTestFactory.createJsonArrayWithOneElement();
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(json, typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof List);
    assertEquals(1, ((List<String>) actualFromJsonResult).size());
    assertEquals("singleElement", ((List<String>) actualFromJsonResult).get(0));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, TypeToken)} with {@code JsonElement}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Then return size is two.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, TypeToken)"})
  public void testFromJsonWithJsonElementTypeToken_thenReturnSizeIsTwo()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonArray json = JsonArrayTestFactory.createJsonArrayWithOneElement();
    json.add(JsonArrayTestFactory.createJsonArrayWithOneElement());
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(json, typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof List);
    assertEquals(2, ((List<Object>) actualFromJsonResult).size());
    Object getResult = ((List<Object>) actualFromJsonResult).get(1);
    assertTrue(getResult instanceof List);
    assertEquals(1, ((List<String>) getResult).size());
    assertEquals("singleElement", ((List<String>) getResult).get(0));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, TypeToken)} with {@code JsonElement}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, TypeToken)"})
  public void testFromJsonWithJsonElementTypeToken_thenReturnTrue() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonPrimitive json = new JsonPrimitive(true);
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertTrue((Boolean) gson.fromJson(json, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Type)} with {@code JsonElement}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@link JsonNull#INSTANCE}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Type)"})
  public void testFromJsonWithJsonElementType_givenGson_whenInstance_thenReturnNull()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    // Act and Assert
    assertNull(gson.fromJson(JsonNull.INSTANCE, GsonBuilderTestFactory.createType()));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Type)} with {@code JsonElement}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@link JsonNull#INSTANCE}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Type)"})
  public void testFromJsonWithJsonElementType_givenGson_whenInstance_thenReturnNull2()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertNull(gson.fromJson(JsonNull.INSTANCE, (Type) typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Type)} with {@code JsonElement}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@link JsonObject} (default constructor).
   *   <li>Then return {@link Map}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Type)"})
  public void testFromJsonWithJsonElementType_givenGson_whenJsonObject_thenReturnMap()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonObject json = new JsonObject();
    Class<Object> typeOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, (Type) typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertTrue(((Map<Object, Object>) actualFromJsonResult).isEmpty());
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Type)} with {@code JsonElement}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Type)"})
  public void testFromJsonWithJsonElementType_givenGson_whenNull_thenReturnNull()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    // Act and Assert
    assertNull(gson.fromJson((JsonElement) null, GsonBuilderTestFactory.createType()));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Type)} with {@code JsonElement}, {@code Type}.
   *
   * <ul>
   *   <li>Then return {@code in == null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Type)"})
  public void testFromJsonWithJsonElementType_thenReturnInNull() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonPrimitive json = new JsonPrimitive("in == null");
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertEquals("in == null", gson.fromJson(json, (Type) typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Type)} with {@code JsonElement}, {@code Type}.
   *
   * <ul>
   *   <li>Then return size is four.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Type)"})
  public void testFromJsonWithJsonElementType_thenReturnSizeIsFour() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonArray json = JsonArrayTestFactory.createJsonArrayWithMixedTypes();
    Class<Object> typeOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, (Type) typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof List);
    assertEquals(4, ((List<Object>) actualFromJsonResult).size());
    Object getResult = ((List<Object>) actualFromJsonResult).get(3);
    assertTrue(getResult instanceof Map);
    assertEquals("string", ((List<Object>) actualFromJsonResult).get(0));
    assertEquals(42.0d, ((Double) ((List<Object>) actualFromJsonResult).get(1)).doubleValue(), 0.0);
    assertTrue(((Map<Object, Object>) getResult).isEmpty());
    assertTrue((Boolean) ((List<Object>) actualFromJsonResult).get(2));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Type)} with {@code JsonElement}, {@code Type}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Type)"})
  public void testFromJsonWithJsonElementType_thenReturnSizeIsOne() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonArray json = JsonArrayTestFactory.createJsonArrayWithOneElement();
    Class<Object> typeOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, (Type) typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof List);
    assertEquals(1, ((List<String>) actualFromJsonResult).size());
    assertEquals("singleElement", ((List<String>) actualFromJsonResult).get(0));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Type)} with {@code JsonElement}, {@code Type}.
   *
   * <ul>
   *   <li>Then return size is three.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Type)"})
  public void testFromJsonWithJsonElementType_thenReturnSizeIsThree() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonArray json = JsonArrayTestFactory.createJsonArrayWithNumbers();
    Class<Object> typeOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, (Type) typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof List);
    assertEquals(3, ((List<Double>) actualFromJsonResult).size());
    assertEquals(1.0d, ((List<Double>) actualFromJsonResult).get(0).doubleValue(), 0.0);
    assertEquals(2.0d, ((List<Double>) actualFromJsonResult).get(1).doubleValue(), 0.0);
    assertEquals(3.0d, ((List<Double>) actualFromJsonResult).get(2).doubleValue(), 0.0);
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Type)} with {@code JsonElement}, {@code Type}.
   *
   * <ul>
   *   <li>Then return size is two.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Type)"})
  public void testFromJsonWithJsonElementType_thenReturnSizeIsTwo() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonArray json = JsonArrayTestFactory.createJsonArrayWithOneElement();
    json.add(JsonArrayTestFactory.createJsonArrayWithOneElement());
    Class<Object> typeOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, (Type) typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof List);
    assertEquals(2, ((List<Object>) actualFromJsonResult).size());
    Object getResult = ((List<Object>) actualFromJsonResult).get(1);
    assertTrue(getResult instanceof List);
    assertEquals(1, ((List<String>) getResult).size());
    assertEquals("singleElement", ((List<String>) getResult).get(0));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Type)} with {@code JsonElement}, {@code Type}.
   *
   * <ul>
   *   <li>When createIntegerType.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Type)"})
  public void testFromJsonWithJsonElementType_whenCreateIntegerType() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonArray json = JsonArrayTestFactory.createJsonArrayWithOneElement();

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class,
        () -> gson.fromJson(json, GsonBuilderTestFactory.createIntegerType()));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Type)} with {@code JsonElement}, {@code Type}.
   *
   * <ul>
   *   <li>When createType.
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Type)"})
  public void testFromJsonWithJsonElementType_whenCreateType_thenThrowJsonSyntaxException()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonArray json = JsonArrayTestFactory.createJsonArrayWithOneElement();

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class, () -> gson.fromJson(json, GsonBuilderTestFactory.createType()));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Type)} with {@code JsonElement}, {@code Type}.
   *
   * <ul>
   *   <li>When createType.
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Type)"})
  public void testFromJsonWithJsonElementType_whenCreateType_thenThrowJsonSyntaxException2()
      throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonArray json = JsonArrayTestFactory.createJsonArrayWithOneElement();

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class, () -> gson.fromJson(json, GsonBuilderTestFactory.createType()));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Type)} with {@code JsonElement}, {@code Type}.
   *
   * <ul>
   *   <li>When {@link JsonObject} (default constructor).
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Type)"})
  public void testFromJsonWithJsonElementType_whenJsonObject_thenThrowJsonSyntaxException()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonObject json = new JsonObject();

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class, () -> gson.fromJson(json, GsonBuilderTestFactory.createType()));
  }

  /**
   * Test {@link Gson#fromJson(JsonElement, Type)} with {@code JsonElement}, {@code Type}.
   *
   * <ul>
   *   <li>When {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonElement, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonElement, Type)"})
  public void testFromJsonWithJsonElementType_whenJsonPrimitiveWithBoolIsTrue_thenReturnTrue()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonPrimitive json = new JsonPrimitive(true);
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertTrue((Boolean) gson.fromJson(json, (Type) typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, Type)} with {@code JsonReader}, {@code Type}.
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, Type)"})
  public void testFromJsonWithJsonReaderType() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LEGACY_STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new StringReader("foo"));

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class,
        () -> gson.fromJson(reader, GsonBuilderTestFactory.createType()));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, Type)} with {@code JsonReader}, {@code Type}.
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, Type)"})
  public void testFromJsonWithJsonReaderType2() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new StringReader("foo"));

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class,
        () -> gson.fromJson(reader, GsonBuilderTestFactory.createType()));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, Type)} with {@code JsonReader}, {@code Type}.
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, Type)"})
  public void testFromJsonWithJsonReaderType3() throws JsonIOException, JsonSyntaxException {
    // Arrange
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(new FutureTypeAdapter<>());

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new StringReader("foo"));

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class,
        () -> gson.fromJson(reader, GsonBuilderTestFactory.createType()));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, Type)} with {@code JsonReader}, {@code Type}.
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, Type)"})
  public void testFromJsonWithJsonReaderType4() throws JsonIOException, JsonSyntaxException {
    // Arrange
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenThrow(new IllegalStateException());

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new StringReader("foo"));

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class,
        () -> gson.fromJson(reader, GsonBuilderTestFactory.createType()));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, TypeToken)} with {@code JsonReader}, {@code TypeToken}.
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, TypeToken)"})
  public void testFromJsonWithJsonReaderTypeToken() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new StringReader("foo"));
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertEquals("foo", gson.fromJson(reader, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, TypeToken)} with {@code JsonReader}, {@code TypeToken}.
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, TypeToken)"})
  public void testFromJsonWithJsonReaderTypeToken2() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new StringReader("foo"));
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(reader, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, TypeToken)} with {@code JsonReader}, {@code TypeToken}.
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, TypeToken)"})
  public void testFromJsonWithJsonReaderTypeToken3()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any())).thenThrow(new IOException());
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LEGACY_STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonTreeReader reader = new JsonTreeReader(JsonArrayTestFactory.createJsonArrayWithNumbers());
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(reader, typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, TypeToken)} with {@code JsonReader}, {@code TypeToken}.
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, TypeToken)"})
  public void testFromJsonWithJsonReaderTypeToken4()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new EOFException());
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LEGACY_STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonTreeReader reader = new JsonTreeReader(JsonArrayTestFactory.createJsonArrayWithNumbers());
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(reader, typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, TypeToken)} with {@code JsonReader}, {@code TypeToken}.
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, TypeToken)"})
  public void testFromJsonWithJsonReaderTypeToken5()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new IllegalStateException());
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LEGACY_STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonTreeReader reader = new JsonTreeReader(JsonArrayTestFactory.createJsonArrayWithNumbers());
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(reader, typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, TypeToken)} with {@code JsonReader}, {@code TypeToken}.
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, TypeToken)"})
  public void testFromJsonWithJsonReaderTypeToken6()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LEGACY_STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive(true));
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(reader, typeOfT);

    // Assert
    assertFalse(reader.hasNext());
    assertTrue((Boolean) actualFromJsonResult);
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, TypeToken)} with {@code JsonReader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>Then return size is three.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, TypeToken)"})
  public void testFromJsonWithJsonReaderTypeToken_givenGson_thenReturnSizeIsThree()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonTreeReader reader = new JsonTreeReader(JsonArrayTestFactory.createJsonArrayWithNumbers());
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(reader, typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof List);
    assertEquals(3, ((List<Double>) actualFromJsonResult).size());
    assertEquals(1.0d, ((List<Double>) actualFromJsonResult).get(0).doubleValue(), 0.0);
    assertEquals(2.0d, ((List<Double>) actualFromJsonResult).get(1).doubleValue(), 0.0);
    assertEquals(3.0d, ((List<Double>) actualFromJsonResult).get(2).doubleValue(), 0.0);
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, TypeToken)} with {@code JsonReader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, TypeToken)"})
  public void testFromJsonWithJsonReaderTypeToken_givenLenient()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonReader reader = new JsonReader(new StringReader("foo"));
    reader.setStrictness(Strictness.LENIENT);
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertEquals("foo", gson.fromJson(reader, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, TypeToken)} with {@code JsonReader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, TypeToken)"})
  public void testFromJsonWithJsonReaderTypeToken_thenReturnFalse()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LEGACY_STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader in = new StringReader(Boolean.FALSE.toString());
    JsonReader reader = new JsonReader(in);
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertFalse((Boolean) gson.fromJson(reader, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, TypeToken)} with {@code JsonReader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Then return {@link Map}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, TypeToken)"})
  public void testFromJsonWithJsonReaderTypeToken_thenReturnMap()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LEGACY_STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonTreeReader reader = new JsonTreeReader(new JsonObject());
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(reader, typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof Map);
    assertTrue(((Map<Object, Object>) actualFromJsonResult).isEmpty());
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, TypeToken)} with {@code JsonReader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, TypeToken)"})
  public void testFromJsonWithJsonReaderTypeToken_thenReturnNull()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LEGACY_STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonTreeReader reader = new JsonTreeReader(JsonNull.INSTANCE);
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertNull(gson.fromJson(reader, typeOfT));
    assertFalse(reader.hasNext());
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, TypeToken)} with {@code JsonReader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, TypeToken)"})
  public void testFromJsonWithJsonReaderTypeToken_thenReturnSizeIsOne()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LEGACY_STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonTreeReader reader =
        new JsonTreeReader(JsonArrayTestFactory.createJsonArrayWithOneElement());
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(reader, typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof List);
    assertEquals(1, ((List<String>) actualFromJsonResult).size());
    assertEquals("singleElement", ((List<String>) actualFromJsonResult).get(0));
    assertFalse(reader.hasNext());
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, TypeToken)} with {@code JsonReader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Then return size is two.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, TypeToken)"})
  public void testFromJsonWithJsonReaderTypeToken_thenReturnSizeIsTwo()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LEGACY_STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonTreeReader reader = new JsonTreeReader(JsonArrayTestFactory.createJsonArrayWithBooleans());
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(reader, typeOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof List);
    assertEquals(2, ((List<Boolean>) actualFromJsonResult).size());
    assertFalse(((List<Boolean>) actualFromJsonResult).get(1));
    assertTrue(((List<Boolean>) actualFromJsonResult).get(0));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, TypeToken)} with {@code JsonReader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Then return {@code type must not be null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, TypeToken)"})
  public void testFromJsonWithJsonReaderTypeToken_thenReturnTypeMustNotBeNull()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LEGACY_STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive("type must not be null"));
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertEquals("type must not be null", gson.fromJson(reader, typeOfT));
    assertFalse(reader.hasNext());
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, TypeToken)} with {@code JsonReader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, TypeToken)"})
  public void testFromJsonWithJsonReaderTypeToken_thenThrowJsonSyntaxException()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LEGACY_STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new StringReader("foo"));
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(reader, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, TypeToken)} with {@code JsonReader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then return {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, TypeToken)"})
  public void testFromJsonWithJsonReaderTypeToken_whenStringReaderWithFoo_thenReturnFoo()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonReader reader = new JsonReader(new StringReader("foo"));
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertEquals("foo", gson.fromJson(reader, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, Type)} with {@code JsonReader}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code Object}.
   *   <li>Then return {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, Type)"})
  public void testFromJsonWithJsonReaderType_givenGson_whenJavaLangObject_thenReturnFoo()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonReader reader = new JsonReader(new StringReader("foo"));
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertEquals("foo", gson.fromJson(reader, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, Type)} with {@code JsonReader}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, Type)"})
  public void testFromJsonWithJsonReaderType_givenGson_whenStringReaderWith42_thenReturn42()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonReader reader = new JsonReader(new StringReader("42"));

    // Act and Assert
    assertEquals("42", gson.fromJson(reader, GsonBuilderTestFactory.createType()));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, Type)} with {@code JsonReader}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then return {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, Type)"})
  public void testFromJsonWithJsonReaderType_givenGson_whenStringReaderWithFoo_thenReturnFoo()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonReader reader = new JsonReader(new StringReader("foo"));

    // Act and Assert
    assertEquals("foo", gson.fromJson(reader, GsonBuilderTestFactory.createType()));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, Type)} with {@code JsonReader}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@code Object}.
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, Type)"})
  public void testFromJsonWithJsonReaderType_givenJavaLangObject_thenThrowJsonSyntaxException()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    Gson context = new Gson();
    FutureTypeAdapter<Object> componentTypeAdapter = new FutureTypeAdapter<>();
    Class<Object> componentType = Object.class;

    ArrayTypeAdapter<Object> arrayTypeAdapter =
        new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(arrayTypeAdapter);

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new StringReader("foo"));

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class,
        () -> gson.fromJson(reader, GsonBuilderTestFactory.createType()));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, Type)} with {@code JsonReader}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, Type)"})
  public void testFromJsonWithJsonReaderType_givenLenient()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    JsonReader reader = new JsonReader(new StringReader("foo"));
    reader.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertEquals("foo", gson.fromJson(reader, GsonBuilderTestFactory.createType()));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, Type)} with {@code JsonReader}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link ObjectTypeAdapter} {@link ObjectTypeAdapter#read(JsonReader)} throw {@link
   *       EOFException#EOFException()}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, Type)"})
  public void testFromJsonWithJsonReaderType_givenObjectTypeAdapterReadThrowEOFException()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ObjectTypeAdapter objectTypeAdapter = mock(ObjectTypeAdapter.class);
    when(objectTypeAdapter.read(Mockito.<JsonReader>any())).thenThrow(new EOFException());

    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(objectTypeAdapter);

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new StringReader("foo"));

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class,
        () -> gson.fromJson(reader, GsonBuilderTestFactory.createType()));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    verify(objectTypeAdapter).read(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, Type)} with {@code JsonReader}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link ObjectTypeAdapter} {@link ObjectTypeAdapter#read(JsonReader)} throw {@link
   *       IOException#IOException()}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, Type)"})
  public void testFromJsonWithJsonReaderType_givenObjectTypeAdapterReadThrowIOException()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ObjectTypeAdapter objectTypeAdapter = mock(ObjectTypeAdapter.class);
    when(objectTypeAdapter.read(Mockito.<JsonReader>any())).thenThrow(new IOException());

    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(objectTypeAdapter);

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new StringReader("foo"));

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class,
        () -> gson.fromJson(reader, GsonBuilderTestFactory.createType()));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    verify(objectTypeAdapter).read(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, Type)} with {@code JsonReader}, {@code Type}.
   *
   * <ul>
   *   <li>Then return {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, Type)"})
  public void testFromJsonWithJsonReaderType_thenReturnFoo()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new StringReader("foo"));

    // Act and Assert
    assertEquals("foo", gson.fromJson(reader, GsonBuilderTestFactory.createType()));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, Type)} with {@code JsonReader}, {@code Type}.
   *
   * <ul>
   *   <li>Then return {@code type}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, Type)"})
  public void testFromJsonWithJsonReaderType_thenReturnType()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonReader reader = new JsonReader(new StringReader("type must not be null"));

    // Act and Assert
    assertEquals("type", gson.fromJson(reader, GsonBuilderTestFactory.createType()));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, Type)} with {@code JsonReader}, {@code Type}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, Type)"})
  public void testFromJsonWithJsonReaderType_thenThrowIllegalArgumentException()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ObjectTypeAdapter objectTypeAdapter = mock(ObjectTypeAdapter.class);
    when(objectTypeAdapter.read(Mockito.<JsonReader>any()))
        .thenThrow(new IllegalArgumentException());

    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(objectTypeAdapter);

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonReader reader = new JsonReader(new StringReader("foo"));

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> gson.fromJson(reader, GsonBuilderTestFactory.createType()));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    verify(objectTypeAdapter).read(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, Type)} with {@code JsonReader}, {@code Type}.
   *
   * <ul>
   *   <li>Then throw {@link JsonIOException}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, Type)"})
  public void testFromJsonWithJsonReaderType_thenThrowJsonIOException()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ObjectTypeAdapter objectTypeAdapter = mock(ObjectTypeAdapter.class);
    when(objectTypeAdapter.read(Mockito.<JsonReader>any()))
        .thenThrow(new JsonIOException(Boolean.FALSE.toString()));

    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(objectTypeAdapter);

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    JsonTreeReader reader =
        new JsonTreeReader(JsonArrayTestFactory.createJsonArrayWithOneElement());

    // Act and Assert
    assertThrows(
        JsonIOException.class, () -> gson.fromJson(reader, GsonBuilderTestFactory.createType()));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    verify(objectTypeAdapter).read(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, Type)} with {@code JsonReader}, {@code Type}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, Type)"})
  public void testFromJsonWithJsonReaderType_whenStringReaderWithEmptyString_thenReturnNull()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonReader reader = new JsonReader(new StringReader(""));

    // Act and Assert
    assertNull(gson.fromJson(reader, GsonBuilderTestFactory.createType()));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, Type)} with {@code JsonReader}, {@code Type}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, Type)"})
  public void testFromJsonWithJsonReaderType_whenStringReaderWithFalseToString()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader in = new StringReader(Boolean.FALSE.toString());
    JsonReader reader = new JsonReader(in);

    // Act and Assert
    assertEquals(
        Boolean.FALSE.toString(), gson.fromJson(reader, GsonBuilderTestFactory.createType()));
  }

  /**
   * Test {@link Gson#fromJson(JsonReader, Type)} with {@code JsonReader}, {@code Type}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code FALSE}.
   *   <li>Then return {@link Boolean#FALSE} toString.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(JsonReader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(JsonReader, Type)"})
  public void testFromJsonWithJsonReaderType_whenStringReaderWithFalse_thenReturnFalseToString()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    JsonReader reader = new JsonReader(new StringReader("FALSE"));

    // Act and Assert
    assertEquals(
        Boolean.FALSE.toString(), gson.fromJson(reader, GsonBuilderTestFactory.createType()));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Class)} with {@code Reader}, {@code Class}.
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Class)"})
  public void testFromJsonWithReaderClass() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LEGACY_STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("foo");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, classOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Class)} with {@code Reader}, {@code Class}.
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Class)"})
  public void testFromJsonWithReaderClass2() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("foo");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, classOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Class)} with {@code Reader}, {@code Class}.
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Class)"})
  public void testFromJsonWithReaderClass3()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new IllegalStateException());
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("42");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, classOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Class)} with {@code Reader}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>Then return doubleValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Class)"})
  public void testFromJsonWithReaderClass_givenGson_thenReturnDoubleValueIsFortyTwo()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("42");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertEquals(42.0d, ((Double) gson.fromJson(json, classOfT)).doubleValue(), 0.0);
  }

  /**
   * Test {@link Gson#fromJson(Reader, Class)} with {@code Reader}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code FALSE}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Class)"})
  public void testFromJsonWithReaderClass_givenGson_whenStringReaderWithFalse_thenReturnFalse()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("FALSE");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertFalse((Boolean) gson.fromJson(json, classOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Class)} with {@code Reader}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then return {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Class)"})
  public void testFromJsonWithReaderClass_givenGson_whenStringReaderWithFoo_thenReturnFoo()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("foo");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertEquals("foo", gson.fromJson(json, classOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Class)} with {@code Reader}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code in == null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Class)"})
  public void testFromJsonWithReaderClass_givenGson_whenStringReaderWithInNull()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("in == null");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, classOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Class)} with {@code Reader}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code type must not be null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Class)"})
  public void testFromJsonWithReaderClass_givenGson_whenStringReaderWithTypeMustNotBeNull()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("type must not be null");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, classOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Class)} with {@code Reader}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@link ToNumberStrategy} {@link ToNumberStrategy#readNumber(JsonReader)} return
   *       {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Class)"})
  public void testFromJsonWithReaderClass_givenToNumberStrategyReadNumberReturnNull()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any())).thenReturn(null);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("42");
    Class<Object> classOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, classOfT);

    // Assert
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
    assertNull(actualFromJsonResult);
  }

  /**
   * Test {@link Gson#fromJson(Reader, Class)} with {@code Reader}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@link ToNumberStrategy} {@link ToNumberStrategy#readNumber(JsonReader)} return
   *       valueOf one.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Class)"})
  public void testFromJsonWithReaderClass_givenToNumberStrategyReadNumberReturnValueOfOne()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenReturn(Integer.valueOf(1));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("42");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, classOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Class)} with {@code Reader}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@link ToNumberStrategy} {@link ToNumberStrategy#readNumber(JsonReader)} throw
   *       {@link EOFException#EOFException()}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Class)"})
  public void testFromJsonWithReaderClass_givenToNumberStrategyReadNumberThrowEOFException()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new EOFException());
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("42");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, classOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Class)} with {@code Reader}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@link ToNumberStrategy} {@link ToNumberStrategy#readNumber(JsonReader)} throw
   *       {@link IOException#IOException()}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Class)"})
  public void testFromJsonWithReaderClass_givenToNumberStrategyReadNumberThrowIOException()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any())).thenThrow(new IOException());
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("42");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, classOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Class)} with {@code Reader}, {@code Class}.
   *
   * <ul>
   *   <li>Then return {@link LazilyParsedNumber}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Class)"})
  public void testFromJsonWithReaderClass_thenReturnLazilyParsedNumber()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("42");
    Class<LazilyParsedNumber> classOfT = LazilyParsedNumber.class;

    // Act
    Object actualFromJsonResult = gson.fromJson(json, classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof LazilyParsedNumber);
    assertEquals("42", actualFromJsonResult.toString());
  }

  /**
   * Test {@link Gson#fromJson(Reader, Class)} with {@code Reader}, {@code Class}.
   *
   * <ul>
   *   <li>Then return ÿ start of heading ÿ.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Class)"})
  public void testFromJsonWithReaderClass_thenReturnŸStartOfHeadingŸ()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    CharArrayReader json = new CharArrayReader("\u0001ÿ\u0001ÿ".toCharArray());
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertEquals("\u0001ÿ\u0001ÿ", gson.fromJson(json, classOfT));
    assertFalse(json.ready());
  }

  /**
   * Test {@link Gson#fromJson(Reader, Class)} with {@code Reader}, {@code Class}.
   *
   * <ul>
   *   <li>When {@link FileReader#FileReader(FileDescriptor)} with {@link
   *       FileDescriptor#FileDescriptor()}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Class)"})
  public void testFromJsonWithReaderClass_whenFileReaderWithFileDescriptor()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    FileReader json = new FileReader(new FileDescriptor());
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, classOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Class)} with {@code Reader}, {@code Class}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Class)"})
  public void testFromJsonWithReaderClass_whenStringReaderWithEmptyString_thenReturnNull()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertNull(gson.fromJson(json, classOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Class)} with {@code Reader}, {@code Class}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Class)"})
  public void testFromJsonWithReaderClass_whenStringReaderWithFalseToString_thenReturnFalse()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader(Boolean.FALSE.toString());
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertFalse((Boolean) gson.fromJson(json, classOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Class)} with {@code Reader}, {@code Class}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then return {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Class)"})
  public void testFromJsonWithReaderClass_whenStringReaderWithFoo_thenReturnFoo()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("foo");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertEquals("foo", gson.fromJson(json, classOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Class)} with {@code Reader}, {@code Class}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code in == null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Class)"})
  public void testFromJsonWithReaderClass_whenStringReaderWithInNull()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("in == null");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, classOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Class)} with {@code Reader}, {@code Class}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code type must not be null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Class)"})
  public void testFromJsonWithReaderClass_whenStringReaderWithTypeMustNotBeNull()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("type must not be null");
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, classOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Type)} with {@code Reader}, {@code Type}.
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Type)"})
  public void testFromJsonWithReaderType() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LEGACY_STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("foo");

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class, () -> gson.fromJson(json, GsonBuilderTestFactory.createType()));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Type)} with {@code Reader}, {@code Type}.
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Type)"})
  public void testFromJsonWithReaderType2() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("foo");

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class, () -> gson.fromJson(json, GsonBuilderTestFactory.createType()));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Type)} with {@code Reader}, {@code Type}.
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Type)"})
  public void testFromJsonWithReaderType3() throws JsonIOException, JsonSyntaxException {
    // Arrange
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenThrow(new IllegalStateException());

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("foo");

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class, () -> gson.fromJson(json, GsonBuilderTestFactory.createType()));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
  }

  /**
   * Test {@link Gson#fromJson(Reader, TypeToken)} with {@code Reader}, {@code TypeToken}.
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, TypeToken)"})
  public void testFromJsonWithReaderTypeToken() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LEGACY_STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            255,
            255,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("foo");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, TypeToken)} with {@code Reader}, {@code TypeToken}.
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, TypeToken)"})
  public void testFromJsonWithReaderTypeToken2() throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            255,
            255,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("foo");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, TypeToken)} with {@code Reader}, {@code TypeToken}.
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, TypeToken)"})
  public void testFromJsonWithReaderTypeToken3()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new IllegalStateException());
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            255,
            255,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("42");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(Reader, TypeToken)} with {@code Reader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>Then return doubleValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, TypeToken)"})
  public void testFromJsonWithReaderTypeToken_givenGson_thenReturnDoubleValueIsFortyTwo()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("42");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertEquals(42.0d, ((Double) gson.fromJson(json, typeOfT)).doubleValue(), 0.0);
  }

  /**
   * Test {@link Gson#fromJson(Reader, TypeToken)} with {@code Reader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then return {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, TypeToken)"})
  public void testFromJsonWithReaderTypeToken_givenGson_whenStringReaderWithFoo_thenReturnFoo()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("foo");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertEquals("foo", gson.fromJson(json, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, TypeToken)} with {@code Reader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code in == null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, TypeToken)"})
  public void testFromJsonWithReaderTypeToken_givenGson_whenStringReaderWithInNull()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("in == null");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, TypeToken)} with {@code Reader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code type must not be null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, TypeToken)"})
  public void testFromJsonWithReaderTypeToken_givenGson_whenStringReaderWithTypeMustNotBeNull()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("type must not be null");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, TypeToken)} with {@code Reader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Given {@link ToNumberStrategy} {@link ToNumberStrategy#readNumber(JsonReader)} return
   *       {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, TypeToken)"})
  public void testFromJsonWithReaderTypeToken_givenToNumberStrategyReadNumberReturnNull()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any())).thenReturn(null);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            255,
            255,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("42");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson(json, typeOfT);

    // Assert
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
    assertNull(actualFromJsonResult);
  }

  /**
   * Test {@link Gson#fromJson(Reader, TypeToken)} with {@code Reader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Given {@link ToNumberStrategy} {@link ToNumberStrategy#readNumber(JsonReader)} return
   *       valueOf one.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, TypeToken)"})
  public void testFromJsonWithReaderTypeToken_givenToNumberStrategyReadNumberReturnValueOfOne()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenReturn(Integer.valueOf(1));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            255,
            255,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("42");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(Reader, TypeToken)} with {@code Reader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Given {@link ToNumberStrategy} {@link ToNumberStrategy#readNumber(JsonReader)} throw
   *       {@link EOFException#EOFException()}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, TypeToken)"})
  public void testFromJsonWithReaderTypeToken_givenToNumberStrategyReadNumberThrowEOFException()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new EOFException());
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            255,
            255,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("42");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(Reader, TypeToken)} with {@code Reader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Given {@link ToNumberStrategy} {@link ToNumberStrategy#readNumber(JsonReader)} throw
   *       {@link IOException#IOException()}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, TypeToken)"})
  public void testFromJsonWithReaderTypeToken_givenToNumberStrategyReadNumberThrowIOException()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any())).thenThrow(new IOException());
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            255,
            255,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("42");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(Reader, TypeToken)} with {@code Reader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>When {@link FileReader#FileReader(FileDescriptor)} with {@link
   *       FileDescriptor#FileDescriptor()}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, TypeToken)"})
  public void testFromJsonWithReaderTypeToken_whenFileReaderWithFileDescriptor()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            255,
            255,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    FileReader json = new FileReader(new FileDescriptor());
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, TypeToken)} with {@code Reader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, TypeToken)"})
  public void testFromJsonWithReaderTypeToken_whenStringReaderWithEmptyString_thenReturnNull()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertNull(gson.fromJson(json, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, TypeToken)} with {@code Reader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, TypeToken)"})
  public void testFromJsonWithReaderTypeToken_whenStringReaderWithFalseToString()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader(Boolean.FALSE.toString());
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertFalse((Boolean) gson.fromJson(json, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, TypeToken)} with {@code Reader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code FALSE}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, TypeToken)"})
  public void testFromJsonWithReaderTypeToken_whenStringReaderWithFalse_thenReturnFalse()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("FALSE");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertFalse((Boolean) gson.fromJson(json, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, TypeToken)} with {@code Reader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then return {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, TypeToken)"})
  public void testFromJsonWithReaderTypeToken_whenStringReaderWithFoo_thenReturnFoo()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            255,
            255,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("foo");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertEquals("foo", gson.fromJson(json, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, TypeToken)} with {@code Reader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code in == null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, TypeToken)"})
  public void testFromJsonWithReaderTypeToken_whenStringReaderWithInNull()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            255,
            255,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("in == null");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, TypeToken)} with {@code Reader}, {@code TypeToken}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code type must not be null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, TypeToken)"})
  public void testFromJsonWithReaderTypeToken_whenStringReaderWithTypeMustNotBeNull()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            255,
            255,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("type must not be null");
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Type)} with {@code Reader}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code Object}.
   *   <li>Then return {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Type)"})
  public void testFromJsonWithReaderType_givenGson_whenJavaLangObject_thenReturnFoo()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("foo");
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertEquals("foo", gson.fromJson(json, (Type) typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Type)} with {@code Reader}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Type)"})
  public void testFromJsonWithReaderType_givenGson_whenStringReaderWith42_thenReturn42()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("42");

    // Act and Assert
    assertEquals("42", gson.fromJson(json, GsonBuilderTestFactory.createType()));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Type)} with {@code Reader}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then return {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Type)"})
  public void testFromJsonWithReaderType_givenGson_whenStringReaderWithFoo_thenReturnFoo()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("foo");

    // Act and Assert
    assertEquals("foo", gson.fromJson(json, GsonBuilderTestFactory.createType()));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Type)} with {@code Reader}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@code Object}.
   *   <li>Then calls {@link TypeAdapterFactory#create(Gson, TypeToken)}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Type)"})
  public void testFromJsonWithReaderType_givenJavaLangObject_thenCallsCreate()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    Gson context = new Gson();
    FutureTypeAdapter<Object> componentTypeAdapter = new FutureTypeAdapter<>();
    Class<Object> componentType = Object.class;

    ArrayTypeAdapter<Object> arrayTypeAdapter =
        new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(arrayTypeAdapter);

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("foo");

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class, () -> gson.fromJson(json, GsonBuilderTestFactory.createType()));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Type)} with {@code Reader}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link ObjectTypeAdapter} {@link ObjectTypeAdapter#read(JsonReader)} throw {@link
   *       EOFException#EOFException()}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Type)"})
  public void testFromJsonWithReaderType_givenObjectTypeAdapterReadThrowEOFException()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ObjectTypeAdapter objectTypeAdapter = mock(ObjectTypeAdapter.class);
    when(objectTypeAdapter.read(Mockito.<JsonReader>any())).thenThrow(new EOFException());

    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(objectTypeAdapter);

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("foo");

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class, () -> gson.fromJson(json, GsonBuilderTestFactory.createType()));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    verify(objectTypeAdapter).read(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Type)} with {@code Reader}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link ObjectTypeAdapter} {@link ObjectTypeAdapter#read(JsonReader)} throw {@link
   *       IOException#IOException()}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Type)"})
  public void testFromJsonWithReaderType_givenObjectTypeAdapterReadThrowIOException()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ObjectTypeAdapter objectTypeAdapter = mock(ObjectTypeAdapter.class);
    when(objectTypeAdapter.read(Mockito.<JsonReader>any())).thenThrow(new IOException());

    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(objectTypeAdapter);

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("foo");

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class, () -> gson.fromJson(json, GsonBuilderTestFactory.createType()));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    verify(objectTypeAdapter).read(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Type)} with {@code Reader}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link TypeAdapterFactory} {@link TypeAdapterFactory#create(Gson, TypeToken)}
   *       return {@link FutureTypeAdapter} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Type)"})
  public void testFromJsonWithReaderType_givenTypeAdapterFactoryCreateReturnFutureTypeAdapter()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(new FutureTypeAdapter<>());

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("foo");

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class, () -> gson.fromJson(json, GsonBuilderTestFactory.createType()));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Type)} with {@code Reader}, {@code Type}.
   *
   * <ul>
   *   <li>Then return {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Type)"})
  public void testFromJsonWithReaderType_thenReturnFoo()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("foo");

    // Act and Assert
    assertEquals("foo", gson.fromJson(json, GsonBuilderTestFactory.createType()));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Type)} with {@code Reader}, {@code Type}.
   *
   * <ul>
   *   <li>Then return ÿ start of heading ÿ.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Type)"})
  public void testFromJsonWithReaderType_thenReturnŸStartOfHeadingŸ()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    Gson gson = new Gson();
    CharArrayReader json = new CharArrayReader("\u0001ÿ\u0001ÿ".toCharArray());

    // Act and Assert
    assertEquals("\u0001ÿ\u0001ÿ", gson.fromJson(json, GsonBuilderTestFactory.createType()));
    assertFalse(json.ready());
  }

  /**
   * Test {@link Gson#fromJson(Reader, Type)} with {@code Reader}, {@code Type}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Type)"})
  public void testFromJsonWithReaderType_thenThrowIllegalArgumentException()
      throws JsonIOException, JsonSyntaxException, IOException {
    // Arrange
    ObjectTypeAdapter objectTypeAdapter = mock(ObjectTypeAdapter.class);
    when(objectTypeAdapter.read(Mockito.<JsonReader>any()))
        .thenThrow(new IllegalArgumentException());

    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(objectTypeAdapter);

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("foo");

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> gson.fromJson(json, GsonBuilderTestFactory.createType()));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    verify(objectTypeAdapter).read(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Type)} with {@code Reader}, {@code Type}.
   *
   * <ul>
   *   <li>When {@link FileReader#FileReader(FileDescriptor)} with {@link
   *       FileDescriptor#FileDescriptor()}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Type)"})
  public void testFromJsonWithReaderType_whenFileReaderWithFileDescriptor()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    FileReader json = new FileReader(new FileDescriptor());

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class, () -> gson.fromJson(json, GsonBuilderTestFactory.createType()));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Type)} with {@code Reader}, {@code Type}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with empty string.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Type)"})
  public void testFromJsonWithReaderType_whenStringReaderWithEmptyString_thenReturnNull()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("");

    // Act and Assert
    assertNull(gson.fromJson(json, GsonBuilderTestFactory.createType()));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Type)} with {@code Reader}, {@code Type}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Type)"})
  public void testFromJsonWithReaderType_whenStringReaderWithFalseToString()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader(Boolean.FALSE.toString());

    // Act and Assert
    assertEquals(
        Boolean.FALSE.toString(), gson.fromJson(json, GsonBuilderTestFactory.createType()));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Type)} with {@code Reader}, {@code Type}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code FALSE}.
   *   <li>Then return {@link Boolean#FALSE} toString.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Type)"})
  public void testFromJsonWithReaderType_whenStringReaderWithFalse_thenReturnFalseToString()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("FALSE");

    // Act and Assert
    assertEquals(
        Boolean.FALSE.toString(), gson.fromJson(json, GsonBuilderTestFactory.createType()));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Type)} with {@code Reader}, {@code Type}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code in == null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Type)"})
  public void testFromJsonWithReaderType_whenStringReaderWithInNull()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("in == null");

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class, () -> gson.fromJson(json, GsonBuilderTestFactory.createType()));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Type)} with {@code Reader}, {@code Type}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code in == null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Type)"})
  public void testFromJsonWithReaderType_whenStringReaderWithInNull2()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("in == null");

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class, () -> gson.fromJson(json, GsonBuilderTestFactory.createType()));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Type)} with {@code Reader}, {@code Type}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code type must not be null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Type)"})
  public void testFromJsonWithReaderType_whenStringReaderWithTypeMustNotBeNull()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    StringReader json = new StringReader("type must not be null");

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class, () -> gson.fromJson(json, GsonBuilderTestFactory.createType()));
  }

  /**
   * Test {@link Gson#fromJson(Reader, Type)} with {@code Reader}, {@code Type}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code type must not be null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(Reader, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(Reader, Type)"})
  public void testFromJsonWithReaderType_whenStringReaderWithTypeMustNotBeNull2()
      throws JsonIOException, JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    StringReader json = new StringReader("type must not be null");

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class, () -> gson.fromJson(json, GsonBuilderTestFactory.createType()));
  }

  /**
   * Test {@link Gson#fromJson(String, Class)} with {@code String}, {@code Class}.
   *
   * <p>Method under test: {@link Gson#fromJson(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Class)"})
  public void testFromJsonWithStringClass() throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LEGACY_STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("Json", classOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, Class)} with {@code String}, {@code Class}.
   *
   * <p>Method under test: {@link Gson#fromJson(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Class)"})
  public void testFromJsonWithStringClass2() throws JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new IllegalStateException());
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("42", classOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(String, Class)} with {@code String}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code 42}.
   *   <li>Then return doubleValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Class)"})
  public void testFromJsonWithStringClass_givenGson_when42_thenReturnDoubleValueIsFortyTwo()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertEquals(42.0d, ((Double) gson.fromJson("42", classOfT)).doubleValue(), 0.0);
  }

  /**
   * Test {@link Gson#fromJson(String, Class)} with {@code String}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When empty string.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Class)"})
  public void testFromJsonWithStringClass_givenGson_whenEmptyString_thenReturnNull()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertNull(gson.fromJson("", classOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, Class)} with {@code String}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code in == null}.
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Class)"})
  public void testFromJsonWithStringClass_givenGson_whenInNull_thenThrowJsonSyntaxException()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("in == null", classOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, Class)} with {@code String}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code Json}.
   *   <li>Then return {@code Json}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Class)"})
  public void testFromJsonWithStringClass_givenGson_whenJson_thenReturnJson()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertEquals("Json", gson.fromJson("Json", classOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, Class)} with {@code String}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Class)"})
  public void testFromJsonWithStringClass_givenGson_whenNull_thenReturnNull()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertNull(gson.fromJson((String) null, classOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, Class)} with {@code String}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code type must not be null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Class)"})
  public void testFromJsonWithStringClass_givenGson_whenTypeMustNotBeNull()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("type must not be null", classOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, Class)} with {@code String}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@link ToNumberStrategy} {@link ToNumberStrategy#readNumber(JsonReader)} return
   *       {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Class)"})
  public void testFromJsonWithStringClass_givenToNumberStrategyReadNumberReturnNull()
      throws JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any())).thenReturn(null);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> classOfT = Object.class;

    // Act
    Object actualFromJsonResult = gson.fromJson("42", classOfT);

    // Assert
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
    assertNull(actualFromJsonResult);
  }

  /**
   * Test {@link Gson#fromJson(String, Class)} with {@code String}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@link ToNumberStrategy} {@link ToNumberStrategy#readNumber(JsonReader)} return
   *       valueOf one.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Class)"})
  public void testFromJsonWithStringClass_givenToNumberStrategyReadNumberReturnValueOfOne()
      throws JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenReturn(Integer.valueOf(1));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("42", classOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(String, Class)} with {@code String}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@link ToNumberStrategy} {@link ToNumberStrategy#readNumber(JsonReader)} throw
   *       {@link EOFException#EOFException()}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Class)"})
  public void testFromJsonWithStringClass_givenToNumberStrategyReadNumberThrowEOFException()
      throws JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new EOFException());
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("42", classOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(String, Class)} with {@code String}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@link ToNumberStrategy} {@link ToNumberStrategy#readNumber(JsonReader)} throw
   *       {@link IOException#IOException()}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Class)"})
  public void testFromJsonWithStringClass_givenToNumberStrategyReadNumberThrowIOException()
      throws JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any())).thenThrow(new IOException());
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("42", classOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(String, Class)} with {@code String}, {@code Class}.
   *
   * <ul>
   *   <li>Then return {@link LazilyParsedNumber}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Class)"})
  public void testFromJsonWithStringClass_thenReturnLazilyParsedNumber()
      throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<LazilyParsedNumber> classOfT = LazilyParsedNumber.class;

    // Act
    Object actualFromJsonResult = gson.fromJson("42", classOfT);

    // Assert
    assertTrue(actualFromJsonResult instanceof LazilyParsedNumber);
    assertEquals("42", actualFromJsonResult.toString());
  }

  /**
   * Test {@link Gson#fromJson(String, Class)} with {@code String}, {@code Class}.
   *
   * <ul>
   *   <li>When {@code in == null}.
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Class)"})
  public void testFromJsonWithStringClass_whenInNull_thenThrowJsonSyntaxException()
      throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("in == null", classOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, Class)} with {@code String}, {@code Class}.
   *
   * <ul>
   *   <li>When {@code Json}.
   *   <li>Then return {@code Json}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Class)"})
  public void testFromJsonWithStringClass_whenJson_thenReturnJson() throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertEquals("Json", gson.fromJson("Json", classOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, Class)} with {@code String}, {@code Class}.
   *
   * <ul>
   *   <li>When {@code type must not be null}.
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Class)"})
  public void testFromJsonWithStringClass_whenTypeMustNotBeNull_thenThrowJsonSyntaxException()
      throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> classOfT = Object.class;

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("type must not be null", classOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, Type)} with {@code String}, {@code Type}.
   *
   * <p>Method under test: {@link Gson#fromJson(String, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Type)"})
  public void testFromJsonWithStringType() throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LEGACY_STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class,
        () -> gson.fromJson("Json", GsonBuilderTestFactory.createType()));
  }

  /**
   * Test {@link Gson#fromJson(String, Type)} with {@code String}, {@code Type}.
   *
   * <p>Method under test: {@link Gson#fromJson(String, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Type)"})
  public void testFromJsonWithStringType2() throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class,
        () -> gson.fromJson("type must not be null", GsonBuilderTestFactory.createType()));
  }

  /**
   * Test {@link Gson#fromJson(String, Type)} with {@code String}, {@code Type}.
   *
   * <p>Method under test: {@link Gson#fromJson(String, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Type)"})
  public void testFromJsonWithStringType3() throws JsonSyntaxException {
    // Arrange
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenThrow(new IllegalStateException());

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class,
        () -> gson.fromJson("Json", GsonBuilderTestFactory.createType()));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
  }

  /**
   * Test {@link Gson#fromJson(String, TypeToken)} with {@code String}, {@code TypeToken}.
   *
   * <p>Method under test: {@link Gson#fromJson(String, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, TypeToken)"})
  public void testFromJsonWithStringTypeToken() throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LEGACY_STRICT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            255,
            255,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("Json", typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, TypeToken)} with {@code String}, {@code TypeToken}.
   *
   * <p>Method under test: {@link Gson#fromJson(String, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, TypeToken)"})
  public void testFromJsonWithStringTypeToken2() throws JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new IllegalStateException());
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            255,
            255,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("42", typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(String, TypeToken)} with {@code String}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code 42}.
   *   <li>Then return doubleValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, TypeToken)"})
  public void testFromJsonWithStringTypeToken_givenGson_when42_thenReturnDoubleValueIsFortyTwo()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertEquals(42.0d, ((Double) gson.fromJson("42", typeOfT)).doubleValue(), 0.0);
  }

  /**
   * Test {@link Gson#fromJson(String, TypeToken)} with {@code String}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When empty string.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, TypeToken)"})
  public void testFromJsonWithStringTypeToken_givenGson_whenEmptyString_thenReturnNull()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertNull(gson.fromJson("", typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, TypeToken)} with {@code String}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code in == null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, TypeToken)"})
  public void testFromJsonWithStringTypeToken_givenGson_whenInNull() throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("in == null", typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, TypeToken)} with {@code String}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code Json}.
   *   <li>Then return {@code Json}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, TypeToken)"})
  public void testFromJsonWithStringTypeToken_givenGson_whenJson_thenReturnJson()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertEquals("Json", gson.fromJson("Json", typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, TypeToken)} with {@code String}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, TypeToken)"})
  public void testFromJsonWithStringTypeToken_givenGson_whenNull_thenReturnNull()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertNull(gson.fromJson((String) null, typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, TypeToken)} with {@code String}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code type must not be null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, TypeToken)"})
  public void testFromJsonWithStringTypeToken_givenGson_whenTypeMustNotBeNull()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("type must not be null", typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, TypeToken)} with {@code String}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Given {@link ToNumberStrategy} {@link ToNumberStrategy#readNumber(JsonReader)} return
   *       {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, TypeToken)"})
  public void testFromJsonWithStringTypeToken_givenToNumberStrategyReadNumberReturnNull()
      throws JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any())).thenReturn(null);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            255,
            255,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act
    Object actualFromJsonResult = gson.fromJson("42", typeOfT);

    // Assert
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
    assertNull(actualFromJsonResult);
  }

  /**
   * Test {@link Gson#fromJson(String, TypeToken)} with {@code String}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Given {@link ToNumberStrategy} {@link ToNumberStrategy#readNumber(JsonReader)} return
   *       valueOf one.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, TypeToken)"})
  public void testFromJsonWithStringTypeToken_givenToNumberStrategyReadNumberReturnValueOfOne()
      throws JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenReturn(Integer.valueOf(1));
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            255,
            255,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("42", typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(String, TypeToken)} with {@code String}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Given {@link ToNumberStrategy} {@link ToNumberStrategy#readNumber(JsonReader)} throw
   *       {@link EOFException#EOFException()}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, TypeToken)"})
  public void testFromJsonWithStringTypeToken_givenToNumberStrategyReadNumberThrowEOFException()
      throws JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new EOFException());
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            255,
            255,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("42", typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(String, TypeToken)} with {@code String}, {@code TypeToken}.
   *
   * <ul>
   *   <li>Given {@link ToNumberStrategy} {@link ToNumberStrategy#readNumber(JsonReader)} throw
   *       {@link IOException#IOException()}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, TypeToken)"})
  public void testFromJsonWithStringTypeToken_givenToNumberStrategyReadNumberThrowIOException()
      throws JsonSyntaxException, IOException {
    // Arrange
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    when(objectToNumberStrategy.readNumber(Mockito.<JsonReader>any())).thenThrow(new IOException());
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            255,
            255,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("42", typeOfT));
    verify(objectToNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(String, TypeToken)} with {@code String}, {@code TypeToken}.
   *
   * <ul>
   *   <li>When {@code in == null}.
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, TypeToken)"})
  public void testFromJsonWithStringTypeToken_whenInNull_thenThrowJsonSyntaxException()
      throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            255,
            255,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("in == null", typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, TypeToken)} with {@code String}, {@code TypeToken}.
   *
   * <ul>
   *   <li>When {@code Json}.
   *   <li>Then return {@code Json}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, TypeToken)"})
  public void testFromJsonWithStringTypeToken_whenJson_thenReturnJson() throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            255,
            255,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertEquals("Json", gson.fromJson("Json", typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, TypeToken)} with {@code String}, {@code TypeToken}.
   *
   * <ul>
   *   <li>When {@code type must not be null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, TypeToken)"})
  public void testFromJsonWithStringTypeToken_whenTypeMustNotBeNull() throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            255,
            255,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());
    Class<Object> type = Object.class;
    TypeToken<Object> typeOfT = TypeToken.get(type);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("type must not be null", typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, Type)} with {@code String}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code 42}.
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Type)"})
  public void testFromJsonWithStringType_givenGson_when42_thenReturn42()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    // Act and Assert
    assertEquals("42", gson.fromJson("42", GsonBuilderTestFactory.createType()));
  }

  /**
   * Test {@link Gson#fromJson(String, Type)} with {@code String}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When empty string.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Type)"})
  public void testFromJsonWithStringType_givenGson_whenEmptyString_thenReturnNull()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    // Act and Assert
    assertNull(gson.fromJson("", GsonBuilderTestFactory.createType()));
  }

  /**
   * Test {@link Gson#fromJson(String, Type)} with {@code String}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code in == null}.
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Type)"})
  public void testFromJsonWithStringType_givenGson_whenInNull_thenThrowJsonSyntaxException()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class,
        () -> gson.fromJson("in == null", GsonBuilderTestFactory.createType()));
  }

  /**
   * Test {@link Gson#fromJson(String, Type)} with {@code String}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code Object}.
   *   <li>Then return {@code Json}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Type)"})
  public void testFromJsonWithStringType_givenGson_whenJavaLangObject_thenReturnJson()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> typeOfT = Object.class;

    // Act and Assert
    assertEquals("Json", gson.fromJson("Json", (Type) typeOfT));
  }

  /**
   * Test {@link Gson#fromJson(String, Type)} with {@code String}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code Json}.
   *   <li>Then return {@code Json}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Type)"})
  public void testFromJsonWithStringType_givenGson_whenJson_thenReturnJson()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    // Act and Assert
    assertEquals("Json", gson.fromJson("Json", GsonBuilderTestFactory.createType()));
  }

  /**
   * Test {@link Gson#fromJson(String, Type)} with {@code String}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Type)"})
  public void testFromJsonWithStringType_givenGson_whenNull_thenReturnNull()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    // Act and Assert
    assertNull(gson.fromJson((String) null, GsonBuilderTestFactory.createType()));
  }

  /**
   * Test {@link Gson#fromJson(String, Type)} with {@code String}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link Gson#Gson()}.
   *   <li>When {@code type must not be null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Type)"})
  public void testFromJsonWithStringType_givenGson_whenTypeMustNotBeNull()
      throws JsonSyntaxException {
    // Arrange
    Gson gson = new Gson();

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class,
        () -> gson.fromJson("type must not be null", GsonBuilderTestFactory.createType()));
  }

  /**
   * Test {@link Gson#fromJson(String, Type)} with {@code String}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@code Object}.
   *   <li>When {@code Json}.
   *   <li>Then calls {@link TypeAdapterFactory#create(Gson, TypeToken)}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Type)"})
  public void testFromJsonWithStringType_givenJavaLangObject_whenJson_thenCallsCreate()
      throws JsonSyntaxException {
    // Arrange
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    Gson context = new Gson();
    FutureTypeAdapter<Object> componentTypeAdapter = new FutureTypeAdapter<>();
    Class<Object> componentType = Object.class;

    ArrayTypeAdapter<Object> arrayTypeAdapter =
        new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(arrayTypeAdapter);

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class,
        () -> gson.fromJson("Json", GsonBuilderTestFactory.createType()));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
  }

  /**
   * Test {@link Gson#fromJson(String, Type)} with {@code String}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link ObjectTypeAdapter} {@link ObjectTypeAdapter#read(JsonReader)} throw {@link
   *       EOFException#EOFException()}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Type)"})
  public void testFromJsonWithStringType_givenObjectTypeAdapterReadThrowEOFException()
      throws JsonSyntaxException, IOException {
    // Arrange
    ObjectTypeAdapter objectTypeAdapter = mock(ObjectTypeAdapter.class);
    when(objectTypeAdapter.read(Mockito.<JsonReader>any())).thenThrow(new EOFException());

    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(objectTypeAdapter);

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class,
        () -> gson.fromJson("Json", GsonBuilderTestFactory.createType()));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    verify(objectTypeAdapter).read(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(String, Type)} with {@code String}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link ObjectTypeAdapter} {@link ObjectTypeAdapter#read(JsonReader)} throw {@link
   *       IOException#IOException()}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Type)"})
  public void testFromJsonWithStringType_givenObjectTypeAdapterReadThrowIOException()
      throws JsonSyntaxException, IOException {
    // Arrange
    ObjectTypeAdapter objectTypeAdapter = mock(ObjectTypeAdapter.class);
    when(objectTypeAdapter.read(Mockito.<JsonReader>any())).thenThrow(new IOException());

    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(objectTypeAdapter);

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class,
        () -> gson.fromJson("Json", GsonBuilderTestFactory.createType()));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    verify(objectTypeAdapter).read(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(String, Type)} with {@code String}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link TypeAdapterFactory} {@link TypeAdapterFactory#create(Gson, TypeToken)}
   *       return {@link FutureTypeAdapter} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Type)"})
  public void testFromJsonWithStringType_givenTypeAdapterFactoryCreateReturnFutureTypeAdapter()
      throws JsonSyntaxException {
    // Arrange
    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(new FutureTypeAdapter<>());

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class,
        () -> gson.fromJson("Json", GsonBuilderTestFactory.createType()));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
  }

  /**
   * Test {@link Gson#fromJson(String, Type)} with {@code String}, {@code Type}.
   *
   * <ul>
   *   <li>Then return {@code Json}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Type)"})
  public void testFromJsonWithStringType_thenReturnJson() throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());

    // Act and Assert
    assertEquals("Json", gson.fromJson("Json", GsonBuilderTestFactory.createType()));
  }

  /**
   * Test {@link Gson#fromJson(String, Type)} with {@code String}, {@code Type}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Type)"})
  public void testFromJsonWithStringType_thenThrowIllegalArgumentException()
      throws JsonSyntaxException, IOException {
    // Arrange
    ObjectTypeAdapter objectTypeAdapter = mock(ObjectTypeAdapter.class);
    when(objectTypeAdapter.read(Mockito.<JsonReader>any()))
        .thenThrow(new IllegalArgumentException());

    TypeAdapterFactory typeAdapterFactory = mock(TypeAdapterFactory.class);
    when(typeAdapterFactory.create(Mockito.<Gson>any(), Mockito.<TypeToken<Object>>any()))
        .thenReturn(objectTypeAdapter);

    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    factoriesToBeAdded.add(typeAdapterFactory);
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> gson.fromJson("Json", GsonBuilderTestFactory.createType()));
    verify(typeAdapterFactory).create(isA(Gson.class), isA(TypeToken.class));
    verify(objectTypeAdapter).read(isA(JsonReader.class));
  }

  /**
   * Test {@link Gson#fromJson(String, Type)} with {@code String}, {@code Type}.
   *
   * <ul>
   *   <li>When {@code in == null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Type)"})
  public void testFromJsonWithStringType_whenInNull() throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class,
        () -> gson.fromJson("in == null", GsonBuilderTestFactory.createType()));
  }

  /**
   * Test {@link Gson#fromJson(String, Type)} with {@code String}, {@code Type}.
   *
   * <ul>
   *   <li>When {@code type must not be null}.
   * </ul>
   *
   * <p>Method under test: {@link Gson#fromJson(String, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Gson.fromJson(String, Type)"})
  public void testFromJsonWithStringType_whenTypeMustNotBeNull() throws JsonSyntaxException {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    Gson gson =
        new Gson(
            Excluder.DEFAULT,
            fieldNamingStrategy,
            instanceCreators,
            true,
            true,
            true,
            true,
            Gson.DEFAULT_FORMATTING_STYLE,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            1,
            1,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class,
        () -> gson.fromJson("type must not be null", GsonBuilderTestFactory.createType()));
  }
}
