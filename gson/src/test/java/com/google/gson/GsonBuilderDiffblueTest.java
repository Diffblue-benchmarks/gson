package com.google.gson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.Gson.FutureTypeAdapter;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.google.gson.internal.reflect.ReflectionHelperTestFactory;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class GsonBuilderDiffblueTest {
  /**
   * Test {@link GsonBuilder#GsonBuilder()}.
   *
   * <p>Method under test: {@link GsonBuilder#GsonBuilder()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void GsonBuilder.<init>()"})
  public void testNewGsonBuilder() {
    // Arrange, Act and Assert
    Gson createResult = new GsonBuilder().create();
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
  }

  /**
   * Test {@link GsonBuilder#GsonBuilder(Gson)}.
   *
   * <ul>
   *   <li>When {@link Gson#Gson()}.
   *   <li>Then return create {@link Gson#strictness} is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link GsonBuilder#GsonBuilder(Gson)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void GsonBuilder.<init>(Gson)"})
  public void testNewGsonBuilder_whenGson_thenReturnCreateStrictnessIsNull() {
    // Arrange
    Gson gson = new Gson();

    // Act and Assert
    Gson createResult = new GsonBuilder(gson).create();
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
   * Test {@link GsonBuilder#setVersion(double)}.
   *
   * <ul>
   *   <li>When {@code -1.0E-10}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link GsonBuilder#setVersion(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"GsonBuilder GsonBuilder.setVersion(double)"})
  public void testSetVersion_when10e10_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> GsonBuilderTestFactory.createGsonBuilder().setVersion(-1.0E-10d));
  }

  /**
   * Test {@link GsonBuilder#setVersion(double)}.
   *
   * <ul>
   *   <li>When {@link Double#NaN}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link GsonBuilder#setVersion(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"GsonBuilder GsonBuilder.setVersion(double)"})
  public void testSetVersion_whenNaN_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> GsonBuilderTestFactory.createGsonBuilder().setVersion(Double.NaN));
  }

  /**
   * Test {@link GsonBuilder#setVersion(double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return createGsonBuilder.
   * </ul>
   *
   * <p>Method under test: {@link GsonBuilder#setVersion(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"GsonBuilder GsonBuilder.setVersion(double)"})
  public void testSetVersion_whenTen_thenReturnCreateGsonBuilder() {
    // Arrange
    GsonBuilder createGsonBuilderResult = GsonBuilderTestFactory.createGsonBuilder();

    // Act
    GsonBuilder actualSetVersionResult = createGsonBuilderResult.setVersion(10.0d);

    // Assert
    assertSame(createGsonBuilderResult, actualSetVersionResult);
  }

  /**
   * Test {@link GsonBuilder#excludeFieldsWithModifiers(int[])}.
   *
   * <p>Method under test: {@link GsonBuilder#excludeFieldsWithModifiers(int[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"GsonBuilder GsonBuilder.excludeFieldsWithModifiers(int[])"})
  public void testExcludeFieldsWithModifiers() {
    // Arrange
    GsonBuilder createGsonBuilderResult = GsonBuilderTestFactory.createGsonBuilder();

    // Act
    GsonBuilder actualExcludeFieldsWithModifiersResult =
        createGsonBuilderResult.excludeFieldsWithModifiers(1, 0, 1, 0);

    // Assert
    assertSame(createGsonBuilderResult, actualExcludeFieldsWithModifiersResult);
  }

  /**
   * Test {@link GsonBuilder#excludeFieldsWithoutExposeAnnotation()}.
   *
   * <p>Method under test: {@link GsonBuilder#excludeFieldsWithoutExposeAnnotation()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"GsonBuilder GsonBuilder.excludeFieldsWithoutExposeAnnotation()"})
  public void testExcludeFieldsWithoutExposeAnnotation() {
    // Arrange
    GsonBuilder createGsonBuilderResult = GsonBuilderTestFactory.createGsonBuilder();

    // Act
    GsonBuilder actualExcludeFieldsWithoutExposeAnnotationResult =
        createGsonBuilderResult.excludeFieldsWithoutExposeAnnotation();

    // Assert
    assertSame(createGsonBuilderResult, actualExcludeFieldsWithoutExposeAnnotationResult);
  }

  /**
   * Test {@link GsonBuilder#disableInnerClassSerialization()}.
   *
   * <p>Method under test: {@link GsonBuilder#disableInnerClassSerialization()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"GsonBuilder GsonBuilder.disableInnerClassSerialization()"})
  public void testDisableInnerClassSerialization() {
    // Arrange
    GsonBuilder createGsonBuilderResult = GsonBuilderTestFactory.createGsonBuilder();

    // Act
    GsonBuilder actualDisableInnerClassSerializationResult =
        createGsonBuilderResult.disableInnerClassSerialization();

    // Assert
    assertSame(createGsonBuilderResult, actualDisableInnerClassSerializationResult);
  }

  /**
   * Test {@link GsonBuilder#setFieldNamingPolicy(FieldNamingPolicy)}.
   *
   * <p>Method under test: {@link GsonBuilder#setFieldNamingPolicy(FieldNamingPolicy)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"GsonBuilder GsonBuilder.setFieldNamingPolicy(FieldNamingPolicy)"})
  public void testSetFieldNamingPolicy() {
    // Arrange
    GsonBuilder createGsonBuilderResult = GsonBuilderTestFactory.createGsonBuilder();

    // Act
    GsonBuilder actualSetFieldNamingPolicyResult =
        createGsonBuilderResult.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY);

    // Assert
    assertSame(createGsonBuilderResult, actualSetFieldNamingPolicyResult);
  }

  /**
   * Test {@link GsonBuilder#setExclusionStrategies(ExclusionStrategy[])}.
   *
   * <p>Method under test: {@link GsonBuilder#setExclusionStrategies(ExclusionStrategy[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"GsonBuilder GsonBuilder.setExclusionStrategies(ExclusionStrategy[])"})
  public void testSetExclusionStrategies() {
    // Arrange
    GsonBuilder createGsonBuilderResult = GsonBuilderTestFactory.createGsonBuilder();

    // Act
    GsonBuilder actualSetExclusionStrategiesResult =
        createGsonBuilderResult.setExclusionStrategies(mock(ExclusionStrategy.class));

    // Assert
    assertSame(createGsonBuilderResult, actualSetExclusionStrategiesResult);
  }

  /**
   * Test {@link GsonBuilder#addSerializationExclusionStrategy(ExclusionStrategy)}.
   *
   * <p>Method under test: {@link GsonBuilder#addSerializationExclusionStrategy(ExclusionStrategy)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "GsonBuilder GsonBuilder.addSerializationExclusionStrategy(ExclusionStrategy)"
  })
  public void testAddSerializationExclusionStrategy() {
    // Arrange
    GsonBuilder createGsonBuilderResult = GsonBuilderTestFactory.createGsonBuilder();

    // Act
    GsonBuilder actualAddSerializationExclusionStrategyResult =
        createGsonBuilderResult.addSerializationExclusionStrategy(mock(ExclusionStrategy.class));

    // Assert
    assertSame(createGsonBuilderResult, actualAddSerializationExclusionStrategyResult);
  }

  /**
   * Test {@link GsonBuilder#addDeserializationExclusionStrategy(ExclusionStrategy)}.
   *
   * <p>Method under test: {@link
   * GsonBuilder#addDeserializationExclusionStrategy(ExclusionStrategy)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "GsonBuilder GsonBuilder.addDeserializationExclusionStrategy(ExclusionStrategy)"
  })
  public void testAddDeserializationExclusionStrategy() {
    // Arrange
    GsonBuilder createGsonBuilderResult = GsonBuilderTestFactory.createGsonBuilder();

    // Act
    GsonBuilder actualAddDeserializationExclusionStrategyResult =
        createGsonBuilderResult.addDeserializationExclusionStrategy(mock(ExclusionStrategy.class));

    // Assert
    assertSame(createGsonBuilderResult, actualAddDeserializationExclusionStrategyResult);
  }

  /**
   * Test {@link GsonBuilder#setPrettyPrinting()}.
   *
   * <p>Method under test: {@link GsonBuilder#setPrettyPrinting()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"GsonBuilder GsonBuilder.setPrettyPrinting()"})
  public void testSetPrettyPrinting() {
    // Arrange
    GsonBuilder createGsonBuilderResult = GsonBuilderTestFactory.createGsonBuilder();

    // Act
    GsonBuilder actualSetPrettyPrintingResult = createGsonBuilderResult.setPrettyPrinting();

    // Assert
    assertSame(createGsonBuilderResult, actualSetPrettyPrintingResult);
  }

  /**
   * Test {@link GsonBuilder#setLenient()}.
   *
   * <p>Method under test: {@link GsonBuilder#setLenient()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"GsonBuilder GsonBuilder.setLenient()"})
  public void testSetLenient() {
    // Arrange
    GsonBuilder createGsonBuilderResult = GsonBuilderTestFactory.createGsonBuilder();

    // Act
    GsonBuilder actualSetLenientResult = createGsonBuilderResult.setLenient();

    // Assert
    assertEquals(Strictness.LENIENT, createGsonBuilderResult.create().strictness);
    assertSame(createGsonBuilderResult, actualSetLenientResult);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "GsonBuilder GsonBuilder.disableHtmlEscaping()",
    "GsonBuilder GsonBuilder.disableJdkUnsafe()",
    "GsonBuilder GsonBuilder.enableComplexMapKeySerialization()",
    "GsonBuilder GsonBuilder.generateNonExecutableJson()",
    "GsonBuilder GsonBuilder.serializeNulls()",
    "GsonBuilder GsonBuilder.serializeSpecialFloatingPointValues()",
    "GsonBuilder GsonBuilder.setFieldNamingStrategy(FieldNamingStrategy)",
    "GsonBuilder GsonBuilder.setFormattingStyle(FormattingStyle)",
    "GsonBuilder GsonBuilder.setLongSerializationPolicy(LongSerializationPolicy)",
    "GsonBuilder GsonBuilder.setNumberToNumberStrategy(ToNumberStrategy)",
    "GsonBuilder GsonBuilder.setObjectToNumberStrategy(ToNumberStrategy)",
    "GsonBuilder GsonBuilder.setStrictness(Strictness)"
  })
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
    GsonBuilder actualSerializeSpecialFloatingPointValuesResult =
        gsonBuilder.serializeSpecialFloatingPointValues();

    // Assert
    assertSame(gsonBuilder, actualDisableHtmlEscapingResult);
    assertSame(gsonBuilder, actualDisableJdkUnsafeResult);
    assertSame(gsonBuilder, actualEnableComplexMapKeySerializationResult);
    assertSame(gsonBuilder, actualGenerateNonExecutableJsonResult);
    assertSame(gsonBuilder, actualSerializeNullsResult);
    assertSame(gsonBuilder, actualSerializeSpecialFloatingPointValuesResult);
    assertSame(gsonBuilder, actualSetFieldNamingStrategyResult);
    assertSame(gsonBuilder, actualSetFormattingStyleResult);
    assertSame(gsonBuilder, actualSetLongSerializationPolicyResult);
    assertSame(gsonBuilder, actualSetNumberToNumberStrategyResult);
    assertSame(gsonBuilder, actualSetObjectToNumberStrategyResult);
    assertSame(gsonBuilder, actualSetStrictnessResult);
  }

  /**
   * Test {@link GsonBuilder#setDateFormat(int, int)} with {@code dateStyle}, {@code timeStyle}.
   *
   * <p>Method under test: {@link GsonBuilder#setDateFormat(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"GsonBuilder GsonBuilder.setDateFormat(int, int)"})
  public void testSetDateFormatWithDateStyleTimeStyle() {
    // Arrange
    GsonBuilder createGsonBuilderResult = GsonBuilderTestFactory.createGsonBuilder();

    // Act
    GsonBuilder actualSetDateFormatResult = createGsonBuilderResult.setDateFormat(1, 1);

    // Assert
    Gson createResult = createGsonBuilderResult.create();
    assertEquals(1, createResult.dateStyle);
    assertEquals(1, createResult.timeStyle);
    assertEquals(45, createResult.factories.size());
    assertSame(createGsonBuilderResult, actualSetDateFormatResult);
  }

  /**
   * Test {@link GsonBuilder#setDateFormat(int, int)} with {@code dateStyle}, {@code timeStyle}.
   *
   * <ul>
   *   <li>When four.
   * </ul>
   *
   * <p>Method under test: {@link GsonBuilder#setDateFormat(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"GsonBuilder GsonBuilder.setDateFormat(int, int)"})
  public void testSetDateFormatWithDateStyleTimeStyle_whenFour() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> GsonBuilderTestFactory.createGsonBuilder().setDateFormat(0, 4));
  }

  /**
   * Test {@link GsonBuilder#setDateFormat(int, int)} with {@code dateStyle}, {@code timeStyle}.
   *
   * <ul>
   *   <li>When minus one.
   * </ul>
   *
   * <p>Method under test: {@link GsonBuilder#setDateFormat(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"GsonBuilder GsonBuilder.setDateFormat(int, int)"})
  public void testSetDateFormatWithDateStyleTimeStyle_whenMinusOne() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> GsonBuilderTestFactory.createGsonBuilder().setDateFormat(0, -1));
  }

  /**
   * Test {@link GsonBuilder#setDateFormat(int, int)} with {@code dateStyle}, {@code timeStyle}.
   *
   * <ul>
   *   <li>When minus one.
   * </ul>
   *
   * <p>Method under test: {@link GsonBuilder#setDateFormat(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"GsonBuilder GsonBuilder.setDateFormat(int, int)"})
  public void testSetDateFormatWithDateStyleTimeStyle_whenMinusOne2() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> GsonBuilderTestFactory.createGsonBuilder().setDateFormat(-1, 0));
  }

  /**
   * Test {@link GsonBuilder#setDateFormat(int)} with {@code dateStyle}.
   *
   * <ul>
   *   <li>When four.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link GsonBuilder#setDateFormat(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"GsonBuilder GsonBuilder.setDateFormat(int)"})
  public void testSetDateFormatWithDateStyle_whenFour_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> GsonBuilderTestFactory.createGsonBuilder().setDateFormat(4));
  }

  /**
   * Test {@link GsonBuilder#setDateFormat(int)} with {@code dateStyle}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link GsonBuilder#setDateFormat(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"GsonBuilder GsonBuilder.setDateFormat(int)"})
  public void testSetDateFormatWithDateStyle_whenMinusOne_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> GsonBuilderTestFactory.createGsonBuilder().setDateFormat(-1));
  }

  /**
   * Test {@link GsonBuilder#setDateFormat(int)} with {@code dateStyle}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then createGsonBuilder create {@link Gson#dateStyle} is one.
   * </ul>
   *
   * <p>Method under test: {@link GsonBuilder#setDateFormat(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"GsonBuilder GsonBuilder.setDateFormat(int)"})
  public void testSetDateFormatWithDateStyle_whenOne_thenCreateGsonBuilderCreateDateStyleIsOne() {
    // Arrange
    GsonBuilder createGsonBuilderResult = GsonBuilderTestFactory.createGsonBuilder();

    // Act
    GsonBuilder actualSetDateFormatResult = createGsonBuilderResult.setDateFormat(1);

    // Assert
    Gson createResult = createGsonBuilderResult.create();
    assertEquals(1, createResult.dateStyle);
    assertEquals(45, createResult.factories.size());
    assertSame(createGsonBuilderResult, actualSetDateFormatResult);
  }

  /**
   * Test {@link GsonBuilder#setDateFormat(String)} with {@code pattern}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return create {@link Gson#factories} size is forty-five.
   * </ul>
   *
   * <p>Method under test: {@link GsonBuilder#setDateFormat(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"GsonBuilder GsonBuilder.setDateFormat(String)"})
  public void testSetDateFormatWithPattern_when42_thenReturnCreateFactoriesSizeIsFortyFive() {
    // Arrange
    GsonBuilder createGsonBuilderResult = GsonBuilderTestFactory.createGsonBuilder();

    // Act and Assert
    Gson createResult = createGsonBuilderResult.setDateFormat("42").create();
    List<TypeAdapterFactory> typeAdapterFactoryList = createResult.factories;
    assertEquals(45, typeAdapterFactoryList.size());
    assertTrue(typeAdapterFactoryList.get(44) instanceof ReflectiveTypeAdapterFactory);
    Gson createResult2 = createGsonBuilderResult.create();
    List<TypeAdapterFactory> typeAdapterFactoryList2 = createResult2.factories;
    assertEquals(45, typeAdapterFactoryList2.size());
    assertTrue(typeAdapterFactoryList2.get(44) instanceof ReflectiveTypeAdapterFactory);
    assertEquals("42", createResult.datePattern);
    assertEquals("42", createResult2.datePattern);
  }

  /**
   * Test {@link GsonBuilder#setDateFormat(String)} with {@code pattern}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return create {@link Gson#factories} size is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link GsonBuilder#setDateFormat(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"GsonBuilder GsonBuilder.setDateFormat(String)"})
  public void testSetDateFormatWithPattern_whenNull_thenReturnCreateFactoriesSizeIsFortyTwo() {
    // Arrange
    GsonBuilder createGsonBuilderResult = GsonBuilderTestFactory.createGsonBuilder();

    // Act and Assert
    Gson createResult = createGsonBuilderResult.setDateFormat(null).create();
    List<TypeAdapterFactory> typeAdapterFactoryList = createResult.factories;
    assertEquals(42, typeAdapterFactoryList.size());
    assertTrue(typeAdapterFactoryList.get(41) instanceof ReflectiveTypeAdapterFactory);
    assertNull(createResult.datePattern);
    Gson createResult2 = createGsonBuilderResult.create();
    assertNull(createResult2.datePattern);
    assertEquals(42, createResult2.factories.size());
  }

  /**
   * Test {@link GsonBuilder#setDateFormat(String)} with {@code pattern}.
   *
   * <ul>
   *   <li>When {@code Pattern}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link GsonBuilder#setDateFormat(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"GsonBuilder GsonBuilder.setDateFormat(String)"})
  public void testSetDateFormatWithPattern_whenPattern_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> GsonBuilderTestFactory.createGsonBuilder().setDateFormat("Pattern"));
  }

  /**
   * Test {@link GsonBuilder#registerTypeAdapterFactory(TypeAdapterFactory)}.
   *
   * <p>Method under test: {@link GsonBuilder#registerTypeAdapterFactory(TypeAdapterFactory)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"GsonBuilder GsonBuilder.registerTypeAdapterFactory(TypeAdapterFactory)"})
  public void testRegisterTypeAdapterFactory() {
    // Arrange
    GsonBuilder createGsonBuilderResult = GsonBuilderTestFactory.createGsonBuilder();

    // Act
    GsonBuilder actualRegisterTypeAdapterFactoryResult =
        createGsonBuilderResult.registerTypeAdapterFactory(mock(TypeAdapterFactory.class));

    // Assert
    Gson createResult = createGsonBuilderResult.create();
    assertEquals(1, createResult.builderFactories.size());
    assertEquals(43, createResult.factories.size());
    assertSame(createGsonBuilderResult, actualRegisterTypeAdapterFactoryResult);
  }

  /**
   * Test {@link GsonBuilder#registerTypeHierarchyAdapter(Class, Object)}.
   *
   * <ul>
   *   <li>Then return create {@link Gson#builderFactories} size is one.
   * </ul>
   *
   * <p>Method under test: {@link GsonBuilder#registerTypeHierarchyAdapter(Class, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"GsonBuilder GsonBuilder.registerTypeHierarchyAdapter(Class, Object)"})
  public void testRegisterTypeHierarchyAdapter_thenReturnCreateBuilderFactoriesSizeIsOne() {
    // Arrange
    GsonBuilder createGsonBuilderResult = GsonBuilderTestFactory.createGsonBuilder();
    Class<?> baseType = ReflectionHelperTestFactory.createStringClass();

    // Act and Assert
    Gson createResult =
        createGsonBuilderResult
            .registerTypeHierarchyAdapter(baseType, new FutureTypeAdapter<>())
            .create();
    assertEquals(1, createResult.builderFactories.size());
    Gson createResult2 = createGsonBuilderResult.create();
    assertEquals(1, createResult2.builderFactories.size());
    assertTrue(createResult.builderHierarchyFactories.isEmpty());
    assertTrue(createResult2.builderHierarchyFactories.isEmpty());
  }

  /**
   * Test {@link GsonBuilder#registerTypeHierarchyAdapter(Class, Object)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link GsonBuilder#registerTypeHierarchyAdapter(Class, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"GsonBuilder GsonBuilder.registerTypeHierarchyAdapter(Class, Object)"})
  public void testRegisterTypeHierarchyAdapter_thenThrowIllegalArgumentException()
      throws NoSuchFieldException {
    // Arrange
    GsonBuilder createGsonBuilderResult = GsonBuilderTestFactory.createGsonBuilder();
    Class<?> baseType = ReflectionHelperTestFactory.createStringClass();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            createGsonBuilderResult.registerTypeHierarchyAdapter(
                baseType, ReflectionHelperTestFactory.createPublicField()));
  }

  /**
   * Test {@link GsonBuilder#registerTypeHierarchyAdapter(Class, Object)}.
   *
   * <ul>
   *   <li>When {@link JsonDeserializer}.
   * </ul>
   *
   * <p>Method under test: {@link GsonBuilder#registerTypeHierarchyAdapter(Class, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"GsonBuilder GsonBuilder.registerTypeHierarchyAdapter(Class, Object)"})
  public void testRegisterTypeHierarchyAdapter_whenJsonDeserializer() {
    // Arrange
    GsonBuilder createGsonBuilderResult = GsonBuilderTestFactory.createGsonBuilder();
    Class<?> baseType = ReflectionHelperTestFactory.createStringClass();

    // Act and Assert
    Gson createResult =
        createGsonBuilderResult
            .registerTypeHierarchyAdapter(baseType, mock(JsonDeserializer.class))
            .create();
    assertEquals(1, createResult.builderHierarchyFactories.size());
    Gson createResult2 = createGsonBuilderResult.create();
    assertEquals(1, createResult2.builderHierarchyFactories.size());
    assertTrue(createResult.builderFactories.isEmpty());
    assertTrue(createResult2.builderFactories.isEmpty());
  }

  /**
   * Test {@link GsonBuilder#registerTypeHierarchyAdapter(Class, Object)}.
   *
   * <ul>
   *   <li>When {@link JsonSerializer}.
   * </ul>
   *
   * <p>Method under test: {@link GsonBuilder#registerTypeHierarchyAdapter(Class, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"GsonBuilder GsonBuilder.registerTypeHierarchyAdapter(Class, Object)"})
  public void testRegisterTypeHierarchyAdapter_whenJsonSerializer() {
    // Arrange
    GsonBuilder createGsonBuilderResult = GsonBuilderTestFactory.createGsonBuilder();
    Class<?> baseType = ReflectionHelperTestFactory.createStringClass();

    // Act and Assert
    Gson createResult =
        createGsonBuilderResult
            .registerTypeHierarchyAdapter(baseType, mock(JsonSerializer.class))
            .create();
    assertEquals(1, createResult.builderHierarchyFactories.size());
    Gson createResult2 = createGsonBuilderResult.create();
    assertEquals(1, createResult2.builderHierarchyFactories.size());
    assertTrue(createResult.builderFactories.isEmpty());
    assertTrue(createResult2.builderFactories.isEmpty());
  }

  /**
   * Test {@link GsonBuilder#addReflectionAccessFilter(ReflectionAccessFilter)}.
   *
   * <p>Method under test: {@link GsonBuilder#addReflectionAccessFilter(ReflectionAccessFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"GsonBuilder GsonBuilder.addReflectionAccessFilter(ReflectionAccessFilter)"})
  public void testAddReflectionAccessFilter() {
    // Arrange
    GsonBuilder createGsonBuilderResult = GsonBuilderTestFactory.createGsonBuilder();

    // Act
    GsonBuilder actualAddReflectionAccessFilterResult =
        createGsonBuilderResult.addReflectionAccessFilter(mock(ReflectionAccessFilter.class));

    // Assert
    assertEquals(1, createGsonBuilderResult.create().reflectionFilters.size());
    assertSame(createGsonBuilderResult, actualAddReflectionAccessFilterResult);
  }

  /**
   * Test {@link GsonBuilder#create()}.
   *
   * <ul>
   *   <li>Given createGsonBuilder NumberToNumberStrategy is {@link ToNumberStrategy}.
   * </ul>
   *
   * <p>Method under test: {@link GsonBuilder#create()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Gson GsonBuilder.create()"})
  public void testCreate_givenCreateGsonBuilderNumberToNumberStrategyIsToNumberStrategy() {
    // Arrange
    GsonBuilder createGsonBuilderResult = GsonBuilderTestFactory.createGsonBuilder();
    createGsonBuilderResult.setNumberToNumberStrategy(mock(ToNumberStrategy.class));

    // Act
    Gson actualCreateResult = createGsonBuilderResult.create();

    // Assert
    assertNull(actualCreateResult.strictness);
    assertNull(actualCreateResult.datePattern);
    assertEquals(2, actualCreateResult.dateStyle);
    assertEquals(2, actualCreateResult.timeStyle);
    assertEquals(42, actualCreateResult.factories.size());
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
    Excluder expectedExcluderResult = actualCreateResult.excluder;
    assertSame(expectedExcluderResult, actualCreateResult.excluder());
    FieldNamingStrategy expectedFieldNamingStrategyResult = actualCreateResult.fieldNamingStrategy;
    assertSame(expectedFieldNamingStrategyResult, actualCreateResult.fieldNamingStrategy());
  }

  /**
   * Test {@link GsonBuilder#create()}.
   *
   * <ul>
   *   <li>Given createGsonBuilder ObjectToNumberStrategy is {@link ToNumberStrategy}.
   * </ul>
   *
   * <p>Method under test: {@link GsonBuilder#create()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Gson GsonBuilder.create()"})
  public void testCreate_givenCreateGsonBuilderObjectToNumberStrategyIsToNumberStrategy() {
    // Arrange
    GsonBuilder createGsonBuilderResult = GsonBuilderTestFactory.createGsonBuilder();
    createGsonBuilderResult.setObjectToNumberStrategy(mock(ToNumberStrategy.class));

    // Act
    Gson actualCreateResult = createGsonBuilderResult.create();

    // Assert
    assertNull(actualCreateResult.strictness);
    assertNull(actualCreateResult.datePattern);
    assertEquals(2, actualCreateResult.dateStyle);
    assertEquals(2, actualCreateResult.timeStyle);
    assertEquals(42, actualCreateResult.factories.size());
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
    Excluder expectedExcluderResult = actualCreateResult.excluder;
    assertSame(expectedExcluderResult, actualCreateResult.excluder());
    FieldNamingStrategy expectedFieldNamingStrategyResult = actualCreateResult.fieldNamingStrategy;
    assertSame(expectedFieldNamingStrategyResult, actualCreateResult.fieldNamingStrategy());
  }

  /**
   * Test {@link GsonBuilder#create()}.
   *
   * <ul>
   *   <li>Given createGsonBuilder.
   *   <li>Then return {@link Gson#longSerializationPolicy} is {@code DEFAULT}.
   * </ul>
   *
   * <p>Method under test: {@link GsonBuilder#create()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Gson GsonBuilder.create()"})
  public void testCreate_givenCreateGsonBuilder_thenReturnLongSerializationPolicyIsDefault() {
    // Arrange and Act
    Gson actualCreateResult = GsonBuilderTestFactory.createGsonBuilder().create();

    // Assert
    assertNull(actualCreateResult.strictness);
    assertNull(actualCreateResult.datePattern);
    assertEquals(2, actualCreateResult.dateStyle);
    assertEquals(2, actualCreateResult.timeStyle);
    assertEquals(42, actualCreateResult.factories.size());
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
    Excluder expectedExcluderResult = actualCreateResult.excluder;
    assertSame(expectedExcluderResult, actualCreateResult.excluder());
    FieldNamingStrategy expectedFieldNamingStrategyResult = actualCreateResult.fieldNamingStrategy;
    assertSame(expectedFieldNamingStrategyResult, actualCreateResult.fieldNamingStrategy());
  }

  /**
   * Test {@link GsonBuilder#create()}.
   *
   * <ul>
   *   <li>Then return {@link Gson#longSerializationPolicy} is {@code STRING}.
   * </ul>
   *
   * <p>Method under test: {@link GsonBuilder#create()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Gson GsonBuilder.create()"})
  public void testCreate_thenReturnLongSerializationPolicyIsString() {
    // Arrange
    GsonBuilder createGsonBuilderResult = GsonBuilderTestFactory.createGsonBuilder();
    createGsonBuilderResult.setLongSerializationPolicy(LongSerializationPolicy.STRING);

    // Act
    Gson actualCreateResult = createGsonBuilderResult.create();

    // Assert
    assertNull(actualCreateResult.strictness);
    assertNull(actualCreateResult.datePattern);
    assertEquals(2, actualCreateResult.dateStyle);
    assertEquals(2, actualCreateResult.timeStyle);
    assertEquals(42, actualCreateResult.factories.size());
    assertEquals(LongSerializationPolicy.STRING, actualCreateResult.longSerializationPolicy);
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
    Excluder expectedExcluderResult = actualCreateResult.excluder;
    assertSame(expectedExcluderResult, actualCreateResult.excluder());
    FieldNamingStrategy expectedFieldNamingStrategyResult = actualCreateResult.fieldNamingStrategy;
    assertSame(expectedFieldNamingStrategyResult, actualCreateResult.fieldNamingStrategy());
  }
}
