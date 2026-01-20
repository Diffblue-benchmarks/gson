package com.google.gson.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ExcluderDiffblueTest {
  @InjectMocks private Excluder excluder;

  @Mock private List<ExclusionStrategy> list;

  /**
   * Test {@link Excluder#clone()}.
   *
   * <p>Method under test: {@link Excluder#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Excluder Excluder.clone()"})
  public void testClone() {
    // Arrange and Act
    Excluder actualCloneResult = Excluder.DEFAULT.clone();

    // Assert
    assertEquals(-1.0d, actualCloneResult.getVersion(), 0.0);
    assertEquals(136, actualCloneResult.getModifiers());
    assertFalse(actualCloneResult.isRequireExpose());
    assertTrue(actualCloneResult.isSerializeInnerClasses());
    List<ExclusionStrategy> deserializationStrategies =
        actualCloneResult.getDeserializationStrategies();
    assertTrue(deserializationStrategies.isEmpty());
    assertSame(deserializationStrategies, actualCloneResult.getSerializationStrategies());
  }

  /**
   * Test {@link Excluder#withVersion(double)}.
   *
   * <p>Method under test: {@link Excluder#withVersion(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Excluder Excluder.withVersion(double)"})
  public void testWithVersion() {
    // Arrange and Act
    Excluder actualWithVersionResult = Excluder.DEFAULT.withVersion(10.0d);

    // Assert
    assertEquals(10.0d, actualWithVersionResult.getVersion(), 0.0);
    assertEquals(136, actualWithVersionResult.getModifiers());
    assertFalse(actualWithVersionResult.isRequireExpose());
    assertTrue(actualWithVersionResult.isSerializeInnerClasses());
    List<ExclusionStrategy> deserializationStrategies =
        actualWithVersionResult.getDeserializationStrategies();
    assertTrue(deserializationStrategies.isEmpty());
    assertSame(deserializationStrategies, actualWithVersionResult.getSerializationStrategies());
  }

  /**
   * Test {@link Excluder#withModifiers(int[])}.
   *
   * <p>Method under test: {@link Excluder#withModifiers(int[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Excluder Excluder.withModifiers(int[])"})
  public void testWithModifiers() {
    // Arrange and Act
    Excluder actualWithModifiersResult = Excluder.DEFAULT.withModifiers(1, 0, 1, 0);

    // Assert
    assertEquals(-1.0d, actualWithModifiersResult.getVersion(), 0.0);
    assertEquals(1, actualWithModifiersResult.getModifiers());
    assertFalse(actualWithModifiersResult.isRequireExpose());
    assertTrue(actualWithModifiersResult.isSerializeInnerClasses());
    List<ExclusionStrategy> deserializationStrategies =
        actualWithModifiersResult.getDeserializationStrategies();
    assertTrue(deserializationStrategies.isEmpty());
    assertSame(deserializationStrategies, actualWithModifiersResult.getSerializationStrategies());
  }

  /**
   * Test {@link Excluder#disableInnerClassSerialization()}.
   *
   * <p>Method under test: {@link Excluder#disableInnerClassSerialization()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Excluder Excluder.disableInnerClassSerialization()"})
  public void testDisableInnerClassSerialization() {
    // Arrange and Act
    Excluder actualDisableInnerClassSerializationResult =
        Excluder.DEFAULT.disableInnerClassSerialization();

    // Assert
    assertEquals(-1.0d, actualDisableInnerClassSerializationResult.getVersion(), 0.0);
    assertEquals(136, actualDisableInnerClassSerializationResult.getModifiers());
    assertFalse(actualDisableInnerClassSerializationResult.isRequireExpose());
    assertFalse(actualDisableInnerClassSerializationResult.isSerializeInnerClasses());
    List<ExclusionStrategy> deserializationStrategies =
        actualDisableInnerClassSerializationResult.getDeserializationStrategies();
    assertTrue(deserializationStrategies.isEmpty());
    assertSame(
        deserializationStrategies,
        actualDisableInnerClassSerializationResult.getSerializationStrategies());
  }

  /**
   * Test {@link Excluder#excludeFieldsWithoutExposeAnnotation()}.
   *
   * <p>Method under test: {@link Excluder#excludeFieldsWithoutExposeAnnotation()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Excluder Excluder.excludeFieldsWithoutExposeAnnotation()"})
  public void testExcludeFieldsWithoutExposeAnnotation() {
    // Arrange and Act
    Excluder actualExcludeFieldsWithoutExposeAnnotationResult =
        Excluder.DEFAULT.excludeFieldsWithoutExposeAnnotation();

    // Assert
    assertEquals(-1.0d, actualExcludeFieldsWithoutExposeAnnotationResult.getVersion(), 0.0);
    assertEquals(136, actualExcludeFieldsWithoutExposeAnnotationResult.getModifiers());
    assertTrue(actualExcludeFieldsWithoutExposeAnnotationResult.isRequireExpose());
    assertTrue(actualExcludeFieldsWithoutExposeAnnotationResult.isSerializeInnerClasses());
    List<ExclusionStrategy> deserializationStrategies =
        actualExcludeFieldsWithoutExposeAnnotationResult.getDeserializationStrategies();
    assertTrue(deserializationStrategies.isEmpty());
    assertSame(
        deserializationStrategies,
        actualExcludeFieldsWithoutExposeAnnotationResult.getSerializationStrategies());
  }

  /**
   * Test {@link Excluder#withExclusionStrategy(ExclusionStrategy, boolean, boolean)}.
   *
   * <ul>
   *   <li>When {@code false}.
   *   <li>Then return Version is minus one.
   * </ul>
   *
   * <p>Method under test: {@link Excluder#withExclusionStrategy(ExclusionStrategy, boolean,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Excluder Excluder.withExclusionStrategy(ExclusionStrategy, boolean, boolean)"
  })
  public void testWithExclusionStrategy_whenFalse_thenReturnVersionIsMinusOne() {
    // Arrange and Act
    Excluder actualWithExclusionStrategyResult =
        Excluder.DEFAULT.withExclusionStrategy(mock(ExclusionStrategy.class), false, false);

    // Assert
    assertEquals(-1.0d, actualWithExclusionStrategyResult.getVersion(), 0.0);
    assertEquals(136, actualWithExclusionStrategyResult.getModifiers());
    assertFalse(actualWithExclusionStrategyResult.isRequireExpose());
    assertTrue(actualWithExclusionStrategyResult.isSerializeInnerClasses());
    List<ExclusionStrategy> deserializationStrategies =
        actualWithExclusionStrategyResult.getDeserializationStrategies();
    assertTrue(deserializationStrategies.isEmpty());
    assertSame(
        deserializationStrategies, actualWithExclusionStrategyResult.getSerializationStrategies());
  }

  /**
   * Test {@link Excluder#withExclusionStrategy(ExclusionStrategy, boolean, boolean)}.
   *
   * <ul>
   *   <li>When {@code true}.
   *   <li>Then return DeserializationStrategies size is one.
   * </ul>
   *
   * <p>Method under test: {@link Excluder#withExclusionStrategy(ExclusionStrategy, boolean,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Excluder Excluder.withExclusionStrategy(ExclusionStrategy, boolean, boolean)"
  })
  public void testWithExclusionStrategy_whenTrue_thenReturnDeserializationStrategiesSizeIsOne() {
    // Arrange
    ExclusionStrategy exclusionStrategy = mock(ExclusionStrategy.class);

    // Act
    Excluder actualWithExclusionStrategyResult =
        Excluder.DEFAULT.withExclusionStrategy(exclusionStrategy, true, true);

    // Assert
    List<ExclusionStrategy> deserializationStrategies =
        actualWithExclusionStrategyResult.getDeserializationStrategies();
    assertEquals(1, deserializationStrategies.size());
    assertEquals(
        deserializationStrategies, actualWithExclusionStrategyResult.getSerializationStrategies());
    assertSame(exclusionStrategy, deserializationStrategies.get(0));
  }

  /**
   * Test {@link Excluder#create(Gson, TypeToken)}.
   *
   * <ul>
   *   <li>Given {@link Excluder#DEFAULT}.
   *   <li>When {@code Object}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Excluder#create(Gson, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapter Excluder.create(Gson, TypeToken)"})
  public void testCreate_givenDefault_whenJavaLangObject_thenReturnNull() {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> type2 = TypeToken.get(type);

    // Act and Assert
    assertNull(Excluder.DEFAULT.create(gson, type2));
  }

  /**
   * Test {@link Excluder#create(Gson, TypeToken)}.
   *
   * <ul>
   *   <li>Then return toJson {@code Value} is {@code "Value"}.
   * </ul>
   *
   * <p>Method under test: {@link Excluder#create(Gson, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapter Excluder.create(Gson, TypeToken)"})
  public void testCreate_thenReturnToJsonValueIsValue() {
    // Arrange
    ExclusionStrategy exclusionStrategy = mock(ExclusionStrategy.class);
    when(exclusionStrategy.shouldSkipClass(Mockito.<Class<?>>any())).thenReturn(true);

    ArrayList<ExclusionStrategy> exclusionStrategyList = new ArrayList<>();
    exclusionStrategyList.add(exclusionStrategy);
    when(list.iterator()).thenReturn(exclusionStrategyList.iterator());
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> type2 = TypeToken.get(type);

    // Act
    TypeAdapter<Object> actualCreateResult = excluder.create(gson, type2);

    // Assert
    verify(exclusionStrategy).shouldSkipClass(isA(Class.class));
    verify(list).iterator();
    assertEquals("\"Value\"", actualCreateResult.toJson("Value"));
  }

  /**
   * Test {@link Excluder#excludeClass(Class, boolean)}.
   *
   * <ul>
   *   <li>Given {@link Excluder#DEFAULT}.
   *   <li>When {@code Enum}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link Excluder#excludeClass(Class, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Excluder.excludeClass(Class, boolean)"})
  public void testExcludeClass_givenDefault_whenJavaLangEnum_thenReturnFalse() {
    // Arrange
    Class<Enum> clazz = Enum.class;

    // Act and Assert
    assertFalse(Excluder.DEFAULT.excludeClass(clazz, false));
  }

  /**
   * Test {@link Excluder#excludeClass(Class, boolean)}.
   *
   * <ul>
   *   <li>Given {@link Excluder#DEFAULT}.
   *   <li>When {@code Object}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link Excluder#excludeClass(Class, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Excluder.excludeClass(Class, boolean)"})
  public void testExcludeClass_givenDefault_whenJavaLangObject_thenReturnFalse() {
    // Arrange
    Class<Object> clazz = Object.class;

    // Act and Assert
    assertFalse(Excluder.DEFAULT.excludeClass(clazz, false));
  }

  /**
   * Test {@link Excluder#excludeClass(Class, boolean)}.
   *
   * <ul>
   *   <li>Given {@link Excluder#DEFAULT}.
   *   <li>When {@code true}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link Excluder#excludeClass(Class, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Excluder.excludeClass(Class, boolean)"})
  public void testExcludeClass_givenDefault_whenTrue_thenReturnFalse() {
    // Arrange
    Class<Object> clazz = Object.class;

    // Act and Assert
    assertFalse(Excluder.DEFAULT.excludeClass(clazz, true));
  }

  /**
   * Test {@link Excluder#excludeClass(Class, boolean)}.
   *
   * <ul>
   *   <li>Given {@link ExclusionStrategy} {@link ExclusionStrategy#shouldSkipClass(Class)} return
   *       {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link Excluder#excludeClass(Class, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Excluder.excludeClass(Class, boolean)"})
  public void testExcludeClass_givenExclusionStrategyShouldSkipClassReturnTrue_thenReturnTrue() {
    // Arrange
    ExclusionStrategy exclusionStrategy = mock(ExclusionStrategy.class);
    when(exclusionStrategy.shouldSkipClass(Mockito.<Class<?>>any())).thenReturn(true);

    ArrayList<ExclusionStrategy> exclusionStrategyList = new ArrayList<>();
    exclusionStrategyList.add(exclusionStrategy);
    when(list.iterator()).thenReturn(exclusionStrategyList.iterator());
    Class<Object> clazz = Object.class;

    // Act
    boolean actualExcludeClassResult = excluder.excludeClass(clazz, false);

    // Assert
    verify(exclusionStrategy).shouldSkipClass(isA(Class.class));
    verify(list).iterator();
    assertTrue(actualExcludeClassResult);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Excluder#getDeserializationStrategies()}
   *   <li>{@link Excluder#getModifiers()}
   *   <li>{@link Excluder#getSerializationStrategies()}
   *   <li>{@link Excluder#getVersion()}
   *   <li>{@link Excluder#isRequireExpose()}
   *   <li>{@link Excluder#isSerializeInnerClasses()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List Excluder.getDeserializationStrategies()",
    "int Excluder.getModifiers()",
    "List Excluder.getSerializationStrategies()",
    "double Excluder.getVersion()",
    "boolean Excluder.isRequireExpose()",
    "boolean Excluder.isSerializeInnerClasses()"
  })
  public void testGettersAndSetters() {
    // Arrange
    Excluder excluder = new Excluder();

    // Act
    List<ExclusionStrategy> actualDeserializationStrategies =
        excluder.getDeserializationStrategies();
    int actualModifiers = excluder.getModifiers();
    List<ExclusionStrategy> actualSerializationStrategies = excluder.getSerializationStrategies();
    double actualVersion = excluder.getVersion();
    boolean actualIsRequireExposeResult = excluder.isRequireExpose();

    // Assert
    assertEquals(-1.0d, actualVersion, 0.0);
    assertEquals(136, actualModifiers);
    assertFalse(actualIsRequireExposeResult);
    assertTrue(excluder.isSerializeInnerClasses());
    assertTrue(actualDeserializationStrategies.isEmpty());
    assertSame(actualDeserializationStrategies, actualSerializationStrategies);
  }

  /**
   * Test new {@link Excluder} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link Excluder}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Excluder.<init>()"})
  public void testNewExcluder() {
    // Arrange and Act
    Excluder actualExcluder = new Excluder();

    // Assert
    assertEquals(-1.0d, actualExcluder.getVersion(), 0.0);
    assertEquals(136, actualExcluder.getModifiers());
    assertFalse(actualExcluder.isRequireExpose());
    assertTrue(actualExcluder.isSerializeInnerClasses());
    List<ExclusionStrategy> deserializationStrategies =
        actualExcluder.getDeserializationStrategies();
    assertTrue(deserializationStrategies.isEmpty());
    assertSame(deserializationStrategies, actualExcluder.getSerializationStrategies());
  }
}
