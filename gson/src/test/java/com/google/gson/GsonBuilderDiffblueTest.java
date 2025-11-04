package com.google.gson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import com.google.gson.internal.Excluder;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.Test;

public class GsonBuilderDiffblueTest {
  /** Method under test: {@link GsonBuilder#setVersion(double)} */
  @Test
  public void testSetVersion() {
    // Arrange
    GsonBuilder gsonBuilder = new GsonBuilder();

    // Act and Assert
    assertSame(gsonBuilder, gsonBuilder.setVersion(10.0d));
  }

  /** Method under test: {@link GsonBuilder#setVersion(double)} */
  @Test
  public void testSetVersion2() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> (new GsonBuilder()).setVersion(-1.0E-10d));
  }

  /** Method under test: {@link GsonBuilder#setVersion(double)} */
  @Test
  public void testSetVersion3() {
    // Arrange
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.addSerializationExclusionStrategy(mock(ExclusionStrategy.class));

    // Act and Assert
    assertSame(gsonBuilder, gsonBuilder.setVersion(10.0d));
  }

  /** Method under test: {@link GsonBuilder#setVersion(double)} */
  @Test
  public void testSetVersion4() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> (new GsonBuilder()).setVersion(Double.NaN));
  }

  /** Method under test: {@link GsonBuilder#excludeFieldsWithModifiers(int[])} */
  @Test
  public void testExcludeFieldsWithModifiers() {
    // Arrange
    GsonBuilder gsonBuilder = new GsonBuilder();

    // Act and Assert
    assertSame(gsonBuilder, gsonBuilder.excludeFieldsWithModifiers(1, 0, 1, 0));
  }

  /** Method under test: {@link GsonBuilder#excludeFieldsWithModifiers(int[])} */
  @Test
  public void testExcludeFieldsWithModifiers2() {
    // Arrange
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.addSerializationExclusionStrategy(mock(ExclusionStrategy.class));

    // Act and Assert
    assertSame(gsonBuilder, gsonBuilder.excludeFieldsWithModifiers(1, 0, 1, 0));
  }

  /** Method under test: {@link GsonBuilder#excludeFieldsWithoutExposeAnnotation()} */
  @Test
  public void testExcludeFieldsWithoutExposeAnnotation() {
    // Arrange
    GsonBuilder gsonBuilder = new GsonBuilder();

    // Act and Assert
    assertSame(gsonBuilder, gsonBuilder.excludeFieldsWithoutExposeAnnotation());
  }

  /** Method under test: {@link GsonBuilder#excludeFieldsWithoutExposeAnnotation()} */
  @Test
  public void testExcludeFieldsWithoutExposeAnnotation2() {
    // Arrange
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.addSerializationExclusionStrategy(mock(ExclusionStrategy.class));

    // Act and Assert
    assertSame(gsonBuilder, gsonBuilder.excludeFieldsWithoutExposeAnnotation());
  }

  /** Method under test: {@link GsonBuilder#disableInnerClassSerialization()} */
  @Test
  public void testDisableInnerClassSerialization() {
    // Arrange
    GsonBuilder gsonBuilder = new GsonBuilder();

    // Act and Assert
    assertSame(gsonBuilder, gsonBuilder.disableInnerClassSerialization());
  }

  /** Method under test: {@link GsonBuilder#disableInnerClassSerialization()} */
  @Test
  public void testDisableInnerClassSerialization2() {
    // Arrange
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.addSerializationExclusionStrategy(mock(ExclusionStrategy.class));

    // Act and Assert
    assertSame(gsonBuilder, gsonBuilder.disableInnerClassSerialization());
  }

  /** Method under test: {@link GsonBuilder#setFieldNamingPolicy(FieldNamingPolicy)} */
  @Test
  public void testSetFieldNamingPolicy() {
    // Arrange
    GsonBuilder gsonBuilder = new GsonBuilder();

    // Act and Assert
    assertSame(gsonBuilder, gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY));
  }

  /** Method under test: {@link GsonBuilder#setFieldNamingPolicy(FieldNamingPolicy)} */
  @Test
  public void testSetFieldNamingPolicy2() {
    // Arrange
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.addSerializationExclusionStrategy(mock(ExclusionStrategy.class));

    // Act and Assert
    assertSame(gsonBuilder, gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY));
  }

  /** Method under test: {@link GsonBuilder#setExclusionStrategies(ExclusionStrategy[])} */
  @Test
  public void testSetExclusionStrategies() {
    // Arrange
    GsonBuilder gsonBuilder = new GsonBuilder();

    // Act and Assert
    assertSame(gsonBuilder, gsonBuilder.setExclusionStrategies(mock(ExclusionStrategy.class)));
  }

  /** Method under test: {@link GsonBuilder#addSerializationExclusionStrategy(ExclusionStrategy)} */
  @Test
  public void testAddSerializationExclusionStrategy() {
    // Arrange
    GsonBuilder gsonBuilder = new GsonBuilder();

    // Act and Assert
    assertSame(
        gsonBuilder, gsonBuilder.addSerializationExclusionStrategy(mock(ExclusionStrategy.class)));
  }

  /**
   * Method under test: {@link GsonBuilder#addDeserializationExclusionStrategy(ExclusionStrategy)}
   */
  @Test
  public void testAddDeserializationExclusionStrategy() {
    // Arrange
    GsonBuilder gsonBuilder = new GsonBuilder();

    // Act and Assert
    assertSame(
        gsonBuilder,
        gsonBuilder.addDeserializationExclusionStrategy(mock(ExclusionStrategy.class)));
  }

  /** Method under test: {@link GsonBuilder#setPrettyPrinting()} */
  @Test
  public void testSetPrettyPrinting() {
    // Arrange
    GsonBuilder gsonBuilder = new GsonBuilder();

    // Act and Assert
    assertSame(gsonBuilder, gsonBuilder.setPrettyPrinting());
  }

  /** Method under test: {@link GsonBuilder#setPrettyPrinting()} */
  @Test
  public void testSetPrettyPrinting2() {
    // Arrange
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.addSerializationExclusionStrategy(mock(ExclusionStrategy.class));

    // Act and Assert
    assertSame(gsonBuilder, gsonBuilder.setPrettyPrinting());
  }

  /** Method under test: {@link GsonBuilder#setLenient()} */
  @Test
  public void testSetLenient() {
    // Arrange
    GsonBuilder gsonBuilder = new GsonBuilder();

    // Act
    GsonBuilder actualSetLenientResult = gsonBuilder.setLenient();

    // Assert
    assertEquals(Strictness.LENIENT, gsonBuilder.create().strictness);
    assertSame(gsonBuilder, actualSetLenientResult);
  }

  /** Method under test: {@link GsonBuilder#setLenient()} */
  @Test
  public void testSetLenient2() {
    // Arrange
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.addSerializationExclusionStrategy(mock(ExclusionStrategy.class));

    // Act
    GsonBuilder actualSetLenientResult = gsonBuilder.setLenient();

    // Assert
    assertEquals(Strictness.LENIENT, gsonBuilder.create().strictness);
    assertSame(gsonBuilder, actualSetLenientResult);
  }

  /** Method under test: {@link GsonBuilder#setDateFormat(int)} */
  @Test
  public void testSetDateFormat() {
    // Arrange
    GsonBuilder gsonBuilder = new GsonBuilder();

    // Act
    GsonBuilder actualSetDateFormatResult = gsonBuilder.setDateFormat(1);

    // Assert
    Gson createResult = gsonBuilder.create();
    List<TypeAdapterFactory> typeAdapterFactoryList = createResult.factories;
    assertEquals(45, typeAdapterFactoryList.size());
    assertTrue(typeAdapterFactoryList.get(44) instanceof ReflectiveTypeAdapterFactory);
    assertEquals(1, createResult.dateStyle);
    assertSame(gsonBuilder, actualSetDateFormatResult);
  }

  /** Method under test: {@link GsonBuilder#setDateFormat(int)} */
  @Test
  public void testSetDateFormat2() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> (new GsonBuilder()).setDateFormat(-1));
  }

  /** Method under test: {@link GsonBuilder#setDateFormat(int)} */
  @Test
  public void testSetDateFormat3() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> (new GsonBuilder()).setDateFormat(4));
  }

  /** Method under test: {@link GsonBuilder#setDateFormat(int)} */
  @Test
  public void testSetDateFormat4() {
    // Arrange
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.addSerializationExclusionStrategy(mock(ExclusionStrategy.class));

    // Act
    GsonBuilder actualSetDateFormatResult = gsonBuilder.setDateFormat(1);

    // Assert
    Gson createResult = gsonBuilder.create();
    List<TypeAdapterFactory> typeAdapterFactoryList = createResult.factories;
    assertEquals(45, typeAdapterFactoryList.size());
    assertTrue(typeAdapterFactoryList.get(44) instanceof ReflectiveTypeAdapterFactory);
    assertEquals(1, createResult.dateStyle);
    assertSame(gsonBuilder, actualSetDateFormatResult);
  }

  /** Method under test: {@link GsonBuilder#setDateFormat(int, int)} */
  @Test
  public void testSetDateFormat5() {
    // Arrange
    GsonBuilder gsonBuilder = new GsonBuilder();

    // Act
    GsonBuilder actualSetDateFormatResult = gsonBuilder.setDateFormat(1, 1);

    // Assert
    Gson createResult = gsonBuilder.create();
    List<TypeAdapterFactory> typeAdapterFactoryList = createResult.factories;
    assertEquals(45, typeAdapterFactoryList.size());
    assertTrue(typeAdapterFactoryList.get(44) instanceof ReflectiveTypeAdapterFactory);
    assertEquals(1, createResult.dateStyle);
    assertEquals(1, createResult.timeStyle);
    assertSame(gsonBuilder, actualSetDateFormatResult);
  }

  /** Method under test: {@link GsonBuilder#setDateFormat(int, int)} */
  @Test
  public void testSetDateFormat6() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> (new GsonBuilder()).setDateFormat(0, -1));
  }

  /** Method under test: {@link GsonBuilder#setDateFormat(int, int)} */
  @Test
  public void testSetDateFormat7() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> (new GsonBuilder()).setDateFormat(0, 4));
  }

  /** Method under test: {@link GsonBuilder#setDateFormat(int, int)} */
  @Test
  public void testSetDateFormat8() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> (new GsonBuilder()).setDateFormat(-1, 0));
  }

  /** Method under test: {@link GsonBuilder#setDateFormat(int, int)} */
  @Test
  public void testSetDateFormat9() {
    // Arrange
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.addSerializationExclusionStrategy(mock(ExclusionStrategy.class));

    // Act
    GsonBuilder actualSetDateFormatResult = gsonBuilder.setDateFormat(1, 1);

    // Assert
    Gson createResult = gsonBuilder.create();
    List<TypeAdapterFactory> typeAdapterFactoryList = createResult.factories;
    assertEquals(45, typeAdapterFactoryList.size());
    assertTrue(typeAdapterFactoryList.get(44) instanceof ReflectiveTypeAdapterFactory);
    assertEquals(1, createResult.dateStyle);
    assertEquals(1, createResult.timeStyle);
    assertSame(gsonBuilder, actualSetDateFormatResult);
  }

  /** Method under test: {@link GsonBuilder#setDateFormat(String)} */
  @Test
  public void testSetDateFormat10() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> (new GsonBuilder()).setDateFormat("Pattern"));
  }

  /** Method under test: {@link GsonBuilder#setDateFormat(String)} */
  @Test
  public void testSetDateFormat11() {
    // Arrange
    GsonBuilder gsonBuilder = new GsonBuilder();

    // Act and Assert
    assertSame(gsonBuilder, gsonBuilder.setDateFormat(null));
  }

  /** Method under test: {@link GsonBuilder#registerTypeAdapterFactory(TypeAdapterFactory)} */
  @Test
  public void testRegisterTypeAdapterFactory() {
    // Arrange
    GsonBuilder gsonBuilder = new GsonBuilder();
    TypeAdapterFactory factory = mock(TypeAdapterFactory.class);

    // Act
    GsonBuilder actualRegisterTypeAdapterFactoryResult =
        gsonBuilder.registerTypeAdapterFactory(factory);

    // Assert
    Gson createResult = gsonBuilder.create();
    List<TypeAdapterFactory> typeAdapterFactoryList = createResult.factories;
    assertEquals(43, typeAdapterFactoryList.size());
    assertTrue(typeAdapterFactoryList.get(42) instanceof ReflectiveTypeAdapterFactory);
    List<TypeAdapterFactory> typeAdapterFactoryList2 = createResult.builderFactories;
    assertEquals(1, typeAdapterFactoryList2.size());
    assertSame(gsonBuilder, actualRegisterTypeAdapterFactoryResult);
    assertSame(factory, typeAdapterFactoryList2.get(0));
  }

  /** Method under test: {@link GsonBuilder#registerTypeHierarchyAdapter(Class, Object)} */
  @Test
  public void testRegisterTypeHierarchyAdapter() {
    // Arrange
    GsonBuilder gsonBuilder = new GsonBuilder();
    Class<Object> baseType = Object.class;

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> gsonBuilder.registerTypeHierarchyAdapter(baseType, "Type Adapter"));
  }

  /** Method under test: {@link GsonBuilder#registerTypeHierarchyAdapter(Class, Object)} */
  @Test
  public void testRegisterTypeHierarchyAdapter2() {
    // Arrange
    GsonBuilder gsonBuilder = new GsonBuilder();
    Class<Object> baseType = Object.class;

    // Act
    GsonBuilder actualRegisterTypeHierarchyAdapterResult =
        gsonBuilder.registerTypeHierarchyAdapter(baseType, mock(JsonSerializer.class));

    // Assert
    Gson createResult = gsonBuilder.create();
    List<TypeAdapterFactory> typeAdapterFactoryList = createResult.factories;
    assertEquals(43, typeAdapterFactoryList.size());
    assertTrue(typeAdapterFactoryList.get(42) instanceof ReflectiveTypeAdapterFactory);
    assertEquals(1, createResult.builderHierarchyFactories.size());
    assertTrue(createResult.builderFactories.isEmpty());
    assertSame(gsonBuilder, actualRegisterTypeHierarchyAdapterResult);
  }

  /** Method under test: {@link GsonBuilder#registerTypeHierarchyAdapter(Class, Object)} */
  @Test
  public void testRegisterTypeHierarchyAdapter3() {
    // Arrange
    GsonBuilder gsonBuilder = new GsonBuilder();
    Class<Object> baseType = Object.class;

    // Act
    GsonBuilder actualRegisterTypeHierarchyAdapterResult =
        gsonBuilder.registerTypeHierarchyAdapter(baseType, mock(JsonDeserializer.class));

    // Assert
    Gson createResult = gsonBuilder.create();
    List<TypeAdapterFactory> typeAdapterFactoryList = createResult.factories;
    assertEquals(43, typeAdapterFactoryList.size());
    assertTrue(typeAdapterFactoryList.get(42) instanceof ReflectiveTypeAdapterFactory);
    assertEquals(1, createResult.builderHierarchyFactories.size());
    assertTrue(createResult.builderFactories.isEmpty());
    assertSame(gsonBuilder, actualRegisterTypeHierarchyAdapterResult);
  }

  /** Method under test: {@link GsonBuilder#registerTypeHierarchyAdapter(Class, Object)} */
  @Test
  public void testRegisterTypeHierarchyAdapter4() {
    // Arrange
    GsonBuilder gsonBuilder = new GsonBuilder();
    Class<Object> baseType = Object.class;

    // Act
    GsonBuilder actualRegisterTypeHierarchyAdapterResult =
        gsonBuilder.registerTypeHierarchyAdapter(baseType, new Gson.FutureTypeAdapter<>());

    // Assert
    Gson createResult = gsonBuilder.create();
    List<TypeAdapterFactory> typeAdapterFactoryList = createResult.factories;
    assertEquals(43, typeAdapterFactoryList.size());
    assertTrue(typeAdapterFactoryList.get(42) instanceof ReflectiveTypeAdapterFactory);
    assertEquals(1, createResult.builderFactories.size());
    assertTrue(createResult.builderHierarchyFactories.isEmpty());
    assertSame(gsonBuilder, actualRegisterTypeHierarchyAdapterResult);
  }

  /** Method under test: {@link GsonBuilder#addReflectionAccessFilter(ReflectionAccessFilter)} */
  @Test
  public void testAddReflectionAccessFilter() {
    // Arrange
    GsonBuilder gsonBuilder = new GsonBuilder();
    ReflectionAccessFilter filter = mock(ReflectionAccessFilter.class);

    // Act
    GsonBuilder actualAddReflectionAccessFilterResult =
        gsonBuilder.addReflectionAccessFilter(filter);

    // Assert
    List<ReflectionAccessFilter> reflectionAccessFilterList =
        gsonBuilder.create().reflectionFilters;
    assertEquals(1, reflectionAccessFilterList.size());
    assertSame(gsonBuilder, actualAddReflectionAccessFilterResult);
    assertSame(filter, reflectionAccessFilterList.get(0));
  }

  /** Method under test: {@link GsonBuilder#create()} */
  @Test
  public void testCreate() {
    // Arrange and Act
    Gson actualCreateResult = (new GsonBuilder()).create();

    // Assert
    List<TypeAdapterFactory> typeAdapterFactoryList = actualCreateResult.factories;
    assertEquals(42, typeAdapterFactoryList.size());
    assertTrue(typeAdapterFactoryList.get(39) instanceof JsonAdapterAnnotationTypeAdapterFactory);
    assertTrue(typeAdapterFactoryList.get(41) instanceof ReflectiveTypeAdapterFactory);
    FormattingStyle formattingStyle = actualCreateResult.formattingStyle;
    assertEquals("", formattingStyle.getIndent());
    assertEquals("", formattingStyle.getNewline());
    assertNull(actualCreateResult.strictness);
    assertNull(actualCreateResult.datePattern);
    assertEquals(2, actualCreateResult.dateStyle);
    assertEquals(2, actualCreateResult.timeStyle);
    assertEquals(LongSerializationPolicy.DEFAULT, actualCreateResult.longSerializationPolicy);
    assertFalse(actualCreateResult.serializeNulls());
    assertFalse(actualCreateResult.complexMapKeySerialization);
    assertFalse(actualCreateResult.generateNonExecutableJson);
    assertFalse(actualCreateResult.serializeNulls);
    assertFalse(actualCreateResult.serializeSpecialFloatingPointValues);
    assertTrue(actualCreateResult.htmlSafe());
    assertTrue(actualCreateResult.builderFactories.isEmpty());
    assertTrue(actualCreateResult.builderHierarchyFactories.isEmpty());
    assertTrue(actualCreateResult.reflectionFilters.isEmpty());
    assertTrue(actualCreateResult.instanceCreators.isEmpty());
    assertTrue(actualCreateResult.htmlSafe);
    assertTrue(actualCreateResult.useJdkUnsafe);
    Excluder excluder = actualCreateResult.excluder;
    assertSame(excluder, actualCreateResult.excluder());
    assertSame(excluder, typeAdapterFactoryList.get(2));
    FieldNamingStrategy expectedFieldNamingStrategyResult = actualCreateResult.fieldNamingStrategy;
    assertSame(expectedFieldNamingStrategyResult, actualCreateResult.fieldNamingStrategy());
  }

  /** Method under test: {@link GsonBuilder#create()} */
  @Test
  public void testCreate2() {
    // Arrange
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.addSerializationExclusionStrategy(mock(ExclusionStrategy.class));

    // Act
    Gson actualCreateResult = gsonBuilder.create();

    // Assert
    List<TypeAdapterFactory> typeAdapterFactoryList = actualCreateResult.factories;
    assertEquals(42, typeAdapterFactoryList.size());
    assertTrue(typeAdapterFactoryList.get(39) instanceof JsonAdapterAnnotationTypeAdapterFactory);
    assertTrue(typeAdapterFactoryList.get(41) instanceof ReflectiveTypeAdapterFactory);
    FormattingStyle formattingStyle = actualCreateResult.formattingStyle;
    assertEquals("", formattingStyle.getIndent());
    assertEquals("", formattingStyle.getNewline());
    assertNull(actualCreateResult.strictness);
    assertNull(actualCreateResult.datePattern);
    assertEquals(2, actualCreateResult.dateStyle);
    assertEquals(2, actualCreateResult.timeStyle);
    assertEquals(LongSerializationPolicy.DEFAULT, actualCreateResult.longSerializationPolicy);
    assertFalse(actualCreateResult.serializeNulls());
    assertFalse(actualCreateResult.complexMapKeySerialization);
    assertFalse(actualCreateResult.generateNonExecutableJson);
    assertFalse(actualCreateResult.serializeNulls);
    assertFalse(actualCreateResult.serializeSpecialFloatingPointValues);
    assertTrue(actualCreateResult.htmlSafe());
    assertTrue(actualCreateResult.builderFactories.isEmpty());
    assertTrue(actualCreateResult.builderHierarchyFactories.isEmpty());
    assertTrue(actualCreateResult.reflectionFilters.isEmpty());
    assertTrue(actualCreateResult.instanceCreators.isEmpty());
    assertTrue(actualCreateResult.htmlSafe);
    assertTrue(actualCreateResult.useJdkUnsafe);
    Excluder excluder = actualCreateResult.excluder;
    assertSame(excluder, actualCreateResult.excluder());
    assertSame(excluder, typeAdapterFactoryList.get(2));
    FieldNamingStrategy expectedFieldNamingStrategyResult = actualCreateResult.fieldNamingStrategy;
    assertSame(expectedFieldNamingStrategyResult, actualCreateResult.fieldNamingStrategy());
  }

  /** Method under test: {@link GsonBuilder#create()} */
  @Test
  public void testCreate3() {
    // Arrange
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.setObjectToNumberStrategy(mock(ToNumberStrategy.class));
    gsonBuilder.addSerializationExclusionStrategy(mock(ExclusionStrategy.class));

    // Act
    Gson actualCreateResult = gsonBuilder.create();

    // Assert
    List<TypeAdapterFactory> typeAdapterFactoryList = actualCreateResult.factories;
    assertEquals(42, typeAdapterFactoryList.size());
    assertTrue(typeAdapterFactoryList.get(39) instanceof JsonAdapterAnnotationTypeAdapterFactory);
    assertTrue(typeAdapterFactoryList.get(41) instanceof ReflectiveTypeAdapterFactory);
    FormattingStyle formattingStyle = actualCreateResult.formattingStyle;
    assertEquals("", formattingStyle.getIndent());
    assertEquals("", formattingStyle.getNewline());
    assertNull(actualCreateResult.strictness);
    assertNull(actualCreateResult.datePattern);
    assertEquals(2, actualCreateResult.dateStyle);
    assertEquals(2, actualCreateResult.timeStyle);
    assertEquals(LongSerializationPolicy.DEFAULT, actualCreateResult.longSerializationPolicy);
    assertFalse(actualCreateResult.serializeNulls());
    assertFalse(actualCreateResult.complexMapKeySerialization);
    assertFalse(actualCreateResult.generateNonExecutableJson);
    assertFalse(actualCreateResult.serializeNulls);
    assertFalse(actualCreateResult.serializeSpecialFloatingPointValues);
    assertTrue(actualCreateResult.htmlSafe());
    assertTrue(actualCreateResult.builderFactories.isEmpty());
    assertTrue(actualCreateResult.builderHierarchyFactories.isEmpty());
    assertTrue(actualCreateResult.reflectionFilters.isEmpty());
    assertTrue(actualCreateResult.instanceCreators.isEmpty());
    assertTrue(actualCreateResult.htmlSafe);
    assertTrue(actualCreateResult.useJdkUnsafe);
    Excluder excluder = actualCreateResult.excluder;
    assertSame(excluder, actualCreateResult.excluder());
    assertSame(excluder, typeAdapterFactoryList.get(2));
    FieldNamingStrategy expectedFieldNamingStrategyResult = actualCreateResult.fieldNamingStrategy;
    assertSame(expectedFieldNamingStrategyResult, actualCreateResult.fieldNamingStrategy());
  }

  /** Method under test: {@link GsonBuilder#create()} */
  @Test
  public void testCreate4() {
    // Arrange
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.setNumberToNumberStrategy(mock(ToNumberStrategy.class));
    gsonBuilder.addSerializationExclusionStrategy(mock(ExclusionStrategy.class));

    // Act
    Gson actualCreateResult = gsonBuilder.create();

    // Assert
    List<TypeAdapterFactory> typeAdapterFactoryList = actualCreateResult.factories;
    assertEquals(42, typeAdapterFactoryList.size());
    assertTrue(typeAdapterFactoryList.get(39) instanceof JsonAdapterAnnotationTypeAdapterFactory);
    assertTrue(typeAdapterFactoryList.get(41) instanceof ReflectiveTypeAdapterFactory);
    FormattingStyle formattingStyle = actualCreateResult.formattingStyle;
    assertEquals("", formattingStyle.getIndent());
    assertEquals("", formattingStyle.getNewline());
    assertNull(actualCreateResult.strictness);
    assertNull(actualCreateResult.datePattern);
    assertEquals(2, actualCreateResult.dateStyle);
    assertEquals(2, actualCreateResult.timeStyle);
    assertEquals(LongSerializationPolicy.DEFAULT, actualCreateResult.longSerializationPolicy);
    assertFalse(actualCreateResult.serializeNulls());
    assertFalse(actualCreateResult.complexMapKeySerialization);
    assertFalse(actualCreateResult.generateNonExecutableJson);
    assertFalse(actualCreateResult.serializeNulls);
    assertFalse(actualCreateResult.serializeSpecialFloatingPointValues);
    assertTrue(actualCreateResult.htmlSafe());
    assertTrue(actualCreateResult.builderFactories.isEmpty());
    assertTrue(actualCreateResult.builderHierarchyFactories.isEmpty());
    assertTrue(actualCreateResult.reflectionFilters.isEmpty());
    assertTrue(actualCreateResult.instanceCreators.isEmpty());
    assertTrue(actualCreateResult.htmlSafe);
    assertTrue(actualCreateResult.useJdkUnsafe);
    Excluder excluder = actualCreateResult.excluder;
    assertSame(excluder, actualCreateResult.excluder());
    assertSame(excluder, typeAdapterFactoryList.get(2));
    FieldNamingStrategy expectedFieldNamingStrategyResult = actualCreateResult.fieldNamingStrategy;
    assertSame(expectedFieldNamingStrategyResult, actualCreateResult.fieldNamingStrategy());
  }

  /** Method under test: {@link GsonBuilder#create()} */
  @Test
  public void testCreate5() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    // Act
    Gson actualCreateResult =
        (new GsonBuilder(
                new Gson(
                    Excluder.DEFAULT,
                    fieldNamingStrategy,
                    instanceCreators,
                    true,
                    true,
                    true,
                    true,
                    FormattingStyle.COMPACT,
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
                    new ArrayList<>())))
            .create();

    // Assert
    List<TypeAdapterFactory> typeAdapterFactoryList = actualCreateResult.factories;
    assertEquals(45, typeAdapterFactoryList.size());
    assertTrue(typeAdapterFactoryList.get(42) instanceof JsonAdapterAnnotationTypeAdapterFactory);
    assertTrue(typeAdapterFactoryList.get(44) instanceof ReflectiveTypeAdapterFactory);
    FormattingStyle formattingStyle = actualCreateResult.formattingStyle;
    assertEquals("", formattingStyle.getIndent());
    assertEquals("", formattingStyle.getNewline());
    assertEquals("2020-03-01", actualCreateResult.datePattern);
    assertEquals(1, actualCreateResult.dateStyle);
    assertEquals(1, actualCreateResult.timeStyle);
    assertEquals(LongSerializationPolicy.DEFAULT, actualCreateResult.longSerializationPolicy);
    assertEquals(Strictness.LENIENT, actualCreateResult.strictness);
    assertTrue(actualCreateResult.htmlSafe());
    assertTrue(actualCreateResult.serializeNulls());
    assertTrue(actualCreateResult.builderFactories.isEmpty());
    assertTrue(actualCreateResult.builderHierarchyFactories.isEmpty());
    assertTrue(actualCreateResult.reflectionFilters.isEmpty());
    assertTrue(actualCreateResult.instanceCreators.isEmpty());
    assertTrue(actualCreateResult.complexMapKeySerialization);
    assertTrue(actualCreateResult.generateNonExecutableJson);
    assertTrue(actualCreateResult.htmlSafe);
    assertTrue(actualCreateResult.serializeNulls);
    assertTrue(actualCreateResult.serializeSpecialFloatingPointValues);
    assertTrue(actualCreateResult.useJdkUnsafe);
    Excluder excluder = actualCreateResult.excluder;
    assertSame(excluder, actualCreateResult.excluder());
    assertSame(excluder, typeAdapterFactoryList.get(2));
    FieldNamingStrategy expectedFieldNamingStrategyResult = actualCreateResult.fieldNamingStrategy;
    assertSame(expectedFieldNamingStrategyResult, actualCreateResult.fieldNamingStrategy());
  }

  /** Method under test: {@link GsonBuilder#create()} */
  @Test
  public void testCreate6() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    // Act
    Gson actualCreateResult =
        (new GsonBuilder(
                new Gson(
                    Excluder.DEFAULT,
                    fieldNamingStrategy,
                    instanceCreators,
                    true,
                    true,
                    true,
                    true,
                    FormattingStyle.COMPACT,
                    Strictness.LENIENT,
                    true,
                    true,
                    null,
                    "2020-03-01",
                    1,
                    1,
                    builderFactories,
                    builderHierarchyFactories,
                    factoriesToBeAdded,
                    objectToNumberStrategy,
                    numberToNumberStrategy,
                    new ArrayList<>())))
            .create();

    // Assert
    List<TypeAdapterFactory> typeAdapterFactoryList = actualCreateResult.factories;
    assertEquals(45, typeAdapterFactoryList.size());
    assertTrue(typeAdapterFactoryList.get(42) instanceof JsonAdapterAnnotationTypeAdapterFactory);
    assertTrue(typeAdapterFactoryList.get(44) instanceof ReflectiveTypeAdapterFactory);
    FormattingStyle formattingStyle = actualCreateResult.formattingStyle;
    assertEquals("", formattingStyle.getIndent());
    assertEquals("", formattingStyle.getNewline());
    assertEquals("2020-03-01", actualCreateResult.datePattern);
    assertNull(actualCreateResult.longSerializationPolicy);
    assertEquals(1, actualCreateResult.dateStyle);
    assertEquals(1, actualCreateResult.timeStyle);
    assertEquals(Strictness.LENIENT, actualCreateResult.strictness);
    assertTrue(actualCreateResult.htmlSafe());
    assertTrue(actualCreateResult.serializeNulls());
    assertTrue(actualCreateResult.builderFactories.isEmpty());
    assertTrue(actualCreateResult.builderHierarchyFactories.isEmpty());
    assertTrue(actualCreateResult.reflectionFilters.isEmpty());
    assertTrue(actualCreateResult.instanceCreators.isEmpty());
    assertTrue(actualCreateResult.complexMapKeySerialization);
    assertTrue(actualCreateResult.generateNonExecutableJson);
    assertTrue(actualCreateResult.htmlSafe);
    assertTrue(actualCreateResult.serializeNulls);
    assertTrue(actualCreateResult.serializeSpecialFloatingPointValues);
    assertTrue(actualCreateResult.useJdkUnsafe);
    Excluder excluder = actualCreateResult.excluder;
    assertSame(excluder, actualCreateResult.excluder());
    assertSame(excluder, typeAdapterFactoryList.get(2));
    FieldNamingStrategy expectedFieldNamingStrategyResult = actualCreateResult.fieldNamingStrategy;
    assertSame(expectedFieldNamingStrategyResult, actualCreateResult.fieldNamingStrategy());
  }

  /** Method under test: {@link GsonBuilder#create()} */
  @Test
  public void testCreate7() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    // Act
    Gson actualCreateResult =
        (new GsonBuilder(
                new Gson(
                    Excluder.DEFAULT,
                    fieldNamingStrategy,
                    instanceCreators,
                    true,
                    true,
                    true,
                    true,
                    FormattingStyle.COMPACT,
                    Strictness.LENIENT,
                    true,
                    true,
                    LongSerializationPolicy.DEFAULT,
                    null,
                    1,
                    1,
                    builderFactories,
                    builderHierarchyFactories,
                    factoriesToBeAdded,
                    objectToNumberStrategy,
                    numberToNumberStrategy,
                    new ArrayList<>())))
            .create();

    // Assert
    List<TypeAdapterFactory> typeAdapterFactoryList = actualCreateResult.factories;
    assertEquals(45, typeAdapterFactoryList.size());
    assertTrue(typeAdapterFactoryList.get(42) instanceof JsonAdapterAnnotationTypeAdapterFactory);
    assertTrue(typeAdapterFactoryList.get(44) instanceof ReflectiveTypeAdapterFactory);
    FormattingStyle formattingStyle = actualCreateResult.formattingStyle;
    assertEquals("", formattingStyle.getIndent());
    assertEquals("", formattingStyle.getNewline());
    assertNull(actualCreateResult.datePattern);
    assertEquals(1, actualCreateResult.dateStyle);
    assertEquals(1, actualCreateResult.timeStyle);
    assertEquals(LongSerializationPolicy.DEFAULT, actualCreateResult.longSerializationPolicy);
    assertEquals(Strictness.LENIENT, actualCreateResult.strictness);
    assertTrue(actualCreateResult.htmlSafe());
    assertTrue(actualCreateResult.serializeNulls());
    assertTrue(actualCreateResult.builderFactories.isEmpty());
    assertTrue(actualCreateResult.builderHierarchyFactories.isEmpty());
    assertTrue(actualCreateResult.reflectionFilters.isEmpty());
    assertTrue(actualCreateResult.instanceCreators.isEmpty());
    assertTrue(actualCreateResult.complexMapKeySerialization);
    assertTrue(actualCreateResult.generateNonExecutableJson);
    assertTrue(actualCreateResult.htmlSafe);
    assertTrue(actualCreateResult.serializeNulls);
    assertTrue(actualCreateResult.serializeSpecialFloatingPointValues);
    assertTrue(actualCreateResult.useJdkUnsafe);
    Excluder excluder = actualCreateResult.excluder;
    assertSame(excluder, actualCreateResult.excluder());
    assertSame(excluder, typeAdapterFactoryList.get(2));
    FieldNamingStrategy expectedFieldNamingStrategyResult = actualCreateResult.fieldNamingStrategy;
    assertSame(expectedFieldNamingStrategyResult, actualCreateResult.fieldNamingStrategy());
  }

  /** Method under test: {@link GsonBuilder#create()} */
  @Test
  public void testCreate8() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    // Act
    Gson actualCreateResult =
        (new GsonBuilder(
                new Gson(
                    Excluder.DEFAULT,
                    fieldNamingStrategy,
                    instanceCreators,
                    true,
                    true,
                    true,
                    true,
                    FormattingStyle.COMPACT,
                    Strictness.LENIENT,
                    true,
                    true,
                    LongSerializationPolicy.DEFAULT,
                    "",
                    1,
                    1,
                    builderFactories,
                    builderHierarchyFactories,
                    factoriesToBeAdded,
                    objectToNumberStrategy,
                    numberToNumberStrategy,
                    new ArrayList<>())))
            .create();

    // Assert
    List<TypeAdapterFactory> typeAdapterFactoryList = actualCreateResult.factories;
    assertEquals(45, typeAdapterFactoryList.size());
    assertTrue(typeAdapterFactoryList.get(42) instanceof JsonAdapterAnnotationTypeAdapterFactory);
    assertTrue(typeAdapterFactoryList.get(44) instanceof ReflectiveTypeAdapterFactory);
    FormattingStyle formattingStyle = actualCreateResult.formattingStyle;
    assertEquals("", formattingStyle.getIndent());
    assertEquals("", formattingStyle.getNewline());
    assertEquals("", actualCreateResult.datePattern);
    assertEquals(1, actualCreateResult.dateStyle);
    assertEquals(1, actualCreateResult.timeStyle);
    assertEquals(LongSerializationPolicy.DEFAULT, actualCreateResult.longSerializationPolicy);
    assertEquals(Strictness.LENIENT, actualCreateResult.strictness);
    assertTrue(actualCreateResult.htmlSafe());
    assertTrue(actualCreateResult.serializeNulls());
    assertTrue(actualCreateResult.builderFactories.isEmpty());
    assertTrue(actualCreateResult.builderHierarchyFactories.isEmpty());
    assertTrue(actualCreateResult.reflectionFilters.isEmpty());
    assertTrue(actualCreateResult.instanceCreators.isEmpty());
    assertTrue(actualCreateResult.complexMapKeySerialization);
    assertTrue(actualCreateResult.generateNonExecutableJson);
    assertTrue(actualCreateResult.htmlSafe);
    assertTrue(actualCreateResult.serializeNulls);
    assertTrue(actualCreateResult.serializeSpecialFloatingPointValues);
    assertTrue(actualCreateResult.useJdkUnsafe);
    Excluder excluder = actualCreateResult.excluder;
    assertSame(excluder, actualCreateResult.excluder());
    assertSame(excluder, typeAdapterFactoryList.get(2));
    FieldNamingStrategy expectedFieldNamingStrategyResult = actualCreateResult.fieldNamingStrategy;
    assertSame(expectedFieldNamingStrategyResult, actualCreateResult.fieldNamingStrategy());
  }

  /** Method under test: {@link GsonBuilder#create()} */
  @Test
  public void testCreate9() {
    // Arrange
    FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ArrayList<TypeAdapterFactory> builderFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> builderHierarchyFactories = new ArrayList<>();
    ArrayList<TypeAdapterFactory> factoriesToBeAdded = new ArrayList<>();
    ToNumberStrategy objectToNumberStrategy = mock(ToNumberStrategy.class);
    ToNumberStrategy numberToNumberStrategy = mock(ToNumberStrategy.class);

    // Act
    Gson actualCreateResult =
        (new GsonBuilder(
                new Gson(
                    Excluder.DEFAULT,
                    fieldNamingStrategy,
                    instanceCreators,
                    true,
                    true,
                    true,
                    true,
                    FormattingStyle.COMPACT,
                    Strictness.LENIENT,
                    true,
                    true,
                    LongSerializationPolicy.DEFAULT,
                    null,
                    2,
                    1,
                    builderFactories,
                    builderHierarchyFactories,
                    factoriesToBeAdded,
                    objectToNumberStrategy,
                    numberToNumberStrategy,
                    new ArrayList<>())))
            .create();

    // Assert
    List<TypeAdapterFactory> typeAdapterFactoryList = actualCreateResult.factories;
    assertEquals(45, typeAdapterFactoryList.size());
    assertTrue(typeAdapterFactoryList.get(42) instanceof JsonAdapterAnnotationTypeAdapterFactory);
    assertTrue(typeAdapterFactoryList.get(44) instanceof ReflectiveTypeAdapterFactory);
    FormattingStyle formattingStyle = actualCreateResult.formattingStyle;
    assertEquals("", formattingStyle.getIndent());
    assertEquals("", formattingStyle.getNewline());
    assertNull(actualCreateResult.datePattern);
    assertEquals(1, actualCreateResult.timeStyle);
    assertEquals(2, actualCreateResult.dateStyle);
    assertEquals(LongSerializationPolicy.DEFAULT, actualCreateResult.longSerializationPolicy);
    assertEquals(Strictness.LENIENT, actualCreateResult.strictness);
    assertTrue(actualCreateResult.htmlSafe());
    assertTrue(actualCreateResult.serializeNulls());
    assertTrue(actualCreateResult.builderFactories.isEmpty());
    assertTrue(actualCreateResult.builderHierarchyFactories.isEmpty());
    assertTrue(actualCreateResult.reflectionFilters.isEmpty());
    assertTrue(actualCreateResult.instanceCreators.isEmpty());
    assertTrue(actualCreateResult.complexMapKeySerialization);
    assertTrue(actualCreateResult.generateNonExecutableJson);
    assertTrue(actualCreateResult.htmlSafe);
    assertTrue(actualCreateResult.serializeNulls);
    assertTrue(actualCreateResult.serializeSpecialFloatingPointValues);
    assertTrue(actualCreateResult.useJdkUnsafe);
    Excluder excluder = actualCreateResult.excluder;
    assertSame(excluder, actualCreateResult.excluder());
    assertSame(excluder, typeAdapterFactoryList.get(2));
    FieldNamingStrategy expectedFieldNamingStrategyResult = actualCreateResult.fieldNamingStrategy;
    assertSame(expectedFieldNamingStrategyResult, actualCreateResult.fieldNamingStrategy());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link GsonBuilder#setFieldNamingStrategy(FieldNamingStrategy)}
   *   <li>{@link GsonBuilder#setFormattingStyle(FormattingStyle)}
   *   <li>{@link GsonBuilder#setLongSerializationPolicy(LongSerializationPolicy)}
   *   <li>{@link GsonBuilder#setNumberToNumberStrategy(ToNumberStrategy)}
   *   <li>{@link GsonBuilder#setObjectToNumberStrategy(ToNumberStrategy)}
   *   <li>{@link GsonBuilder#setStrictness(Strictness)}
   *   <li>{@link GsonBuilder#disableHtmlEscaping()}
   *   <li>{@link GsonBuilder#disableJdkUnsafe()}
   *   <li>{@link GsonBuilder#enableComplexMapKeySerialization()}
   *   <li>{@link GsonBuilder#generateNonExecutableJson()}
   *   <li>{@link GsonBuilder#serializeNulls()}
   *   <li>{@link GsonBuilder#serializeSpecialFloatingPointValues()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    GsonBuilder gsonBuilder = new GsonBuilder();

    // Act
    GsonBuilder actualSetFieldNamingStrategyResult =
        gsonBuilder.setFieldNamingStrategy(mock(FieldNamingStrategy.class));
    GsonBuilder actualSetFormattingStyleResult =
        gsonBuilder.setFormattingStyle(FormattingStyle.COMPACT);
    GsonBuilder actualSetLongSerializationPolicyResult =
        gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.DEFAULT);
    GsonBuilder actualSetNumberToNumberStrategyResult =
        gsonBuilder.setNumberToNumberStrategy(mock(ToNumberStrategy.class));
    GsonBuilder actualSetObjectToNumberStrategyResult =
        gsonBuilder.setObjectToNumberStrategy(mock(ToNumberStrategy.class));
    GsonBuilder actualSetStrictnessResult = gsonBuilder.setStrictness(Strictness.LENIENT);
    GsonBuilder actualDisableHtmlEscapingResult = gsonBuilder.disableHtmlEscaping();
    GsonBuilder actualDisableJdkUnsafeResult = gsonBuilder.disableJdkUnsafe();
    GsonBuilder actualEnableComplexMapKeySerializationResult =
        gsonBuilder.enableComplexMapKeySerialization();
    GsonBuilder actualGenerateNonExecutableJsonResult = gsonBuilder.generateNonExecutableJson();
    GsonBuilder actualSerializeNullsResult = gsonBuilder.serializeNulls();

    // Assert
    assertSame(gsonBuilder, actualDisableHtmlEscapingResult);
    assertSame(gsonBuilder, actualDisableJdkUnsafeResult);
    assertSame(gsonBuilder, actualEnableComplexMapKeySerializationResult);
    assertSame(gsonBuilder, actualGenerateNonExecutableJsonResult);
    assertSame(gsonBuilder, actualSerializeNullsResult);
    assertSame(gsonBuilder, gsonBuilder.serializeSpecialFloatingPointValues());
    assertSame(gsonBuilder, actualSetFieldNamingStrategyResult);
    assertSame(gsonBuilder, actualSetFormattingStyleResult);
    assertSame(gsonBuilder, actualSetLongSerializationPolicyResult);
    assertSame(gsonBuilder, actualSetNumberToNumberStrategyResult);
    assertSame(gsonBuilder, actualSetObjectToNumberStrategyResult);
    assertSame(gsonBuilder, actualSetStrictnessResult);
  }

  /** Method under test: {@link GsonBuilder#GsonBuilder()} */
  @Test
  public void testNewGsonBuilder() {
    // Arrange, Act and Assert
    Gson createResult = (new GsonBuilder()).create();
    List<TypeAdapterFactory> typeAdapterFactoryList = createResult.factories;
    assertEquals(42, typeAdapterFactoryList.size());
    assertTrue(typeAdapterFactoryList.get(41) instanceof ReflectiveTypeAdapterFactory);
    FormattingStyle formattingStyle = createResult.formattingStyle;
    assertEquals("", formattingStyle.getIndent());
    assertEquals("", formattingStyle.getNewline());
    assertNull(createResult.strictness);
    assertNull(createResult.datePattern);
    assertEquals(2, createResult.dateStyle);
    assertEquals(2, createResult.timeStyle);
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
  }

  /** Method under test: {@link GsonBuilder#GsonBuilder(Gson)} */
  @Test
  public void testNewGsonBuilder2() {
    // Arrange
    Gson gson = new Gson();

    // Act and Assert
    Gson createResult = (new GsonBuilder(gson)).create();
    List<TypeAdapterFactory> typeAdapterFactoryList = createResult.factories;
    assertEquals(42, typeAdapterFactoryList.size());
    assertTrue(typeAdapterFactoryList.get(41) instanceof ReflectiveTypeAdapterFactory);
    assertNull(createResult.strictness);
    assertNull(createResult.datePattern);
    assertEquals(2, createResult.dateStyle);
    assertEquals(2, createResult.timeStyle);
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

  /** Method under test: {@link GsonBuilder#GsonBuilder(Gson)} */
  @Test
  public void testNewGsonBuilder3() {
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
            FormattingStyle.COMPACT,
            Strictness.LENIENT,
            true,
            true,
            LongSerializationPolicy.DEFAULT,
            "2020-03-01",
            2,
            2,
            builderFactories,
            builderHierarchyFactories,
            factoriesToBeAdded,
            objectToNumberStrategy,
            numberToNumberStrategy,
            new ArrayList<>());

    // Act and Assert
    Gson createResult = (new GsonBuilder(gson)).create();
    List<TypeAdapterFactory> typeAdapterFactoryList = createResult.factories;
    assertEquals(45, typeAdapterFactoryList.size());
    assertTrue(typeAdapterFactoryList.get(44) instanceof ReflectiveTypeAdapterFactory);
    assertEquals("2020-03-01", createResult.datePattern);
    assertEquals(2, createResult.dateStyle);
    assertEquals(2, createResult.timeStyle);
    assertEquals(LongSerializationPolicy.DEFAULT, createResult.longSerializationPolicy);
    assertEquals(Strictness.LENIENT, createResult.strictness);
    assertTrue(createResult.htmlSafe());
    assertTrue(createResult.serializeNulls());
    assertTrue(createResult.builderFactories.isEmpty());
    assertTrue(createResult.builderHierarchyFactories.isEmpty());
    assertTrue(createResult.reflectionFilters.isEmpty());
    assertTrue(createResult.instanceCreators.isEmpty());
    assertTrue(createResult.complexMapKeySerialization);
    assertTrue(createResult.generateNonExecutableJson);
    assertTrue(createResult.htmlSafe);
    assertTrue(createResult.serializeNulls);
    assertTrue(createResult.serializeSpecialFloatingPointValues);
    assertTrue(createResult.useJdkUnsafe);
    Excluder excluder = gson.excluder;
    assertSame(excluder, createResult.excluder());
    assertSame(excluder, createResult.excluder);
    FieldNamingStrategy fieldNamingStrategy2 = gson.fieldNamingStrategy;
    assertSame(fieldNamingStrategy2, createResult.fieldNamingStrategy());
    assertSame(fieldNamingStrategy2, createResult.fieldNamingStrategy);
    assertSame(gson.formattingStyle, createResult.formattingStyle);
    assertSame(gson.numberToNumberStrategy, createResult.numberToNumberStrategy);
    assertSame(gson.objectToNumberStrategy, createResult.objectToNumberStrategy);
  }
}
