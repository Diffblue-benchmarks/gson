package com.google.gson.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
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
    // Arrange, Act and Assert
    assertFalse(Excluder.DEFAULT.clone().excludeClass(null, true));
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
    // Arrange, Act and Assert
    assertFalse(Excluder.DEFAULT.withModifiers(1, 0, 1, 0).excludeClass(null, true));
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
    // Arrange, Act and Assert
    assertFalse(Excluder.DEFAULT.excludeFieldsWithoutExposeAnnotation().excludeClass(null, true));
  }

  /**
   * Test {@link Excluder#withExclusionStrategy(ExclusionStrategy, boolean, boolean)}.
   *
   * <ul>
   *   <li>When {@code false}.
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
  public void testWithExclusionStrategy_whenFalse() {
    // Arrange, Act and Assert
    assertFalse(
        Excluder.DEFAULT
            .withExclusionStrategy(mock(ExclusionStrategy.class), false, false)
            .excludeClass(null, true));
  }

  /**
   * Test {@link Excluder#withExclusionStrategy(ExclusionStrategy, boolean, boolean)}.
   *
   * <ul>
   *   <li>When {@code true}.
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
  public void testWithExclusionStrategy_whenTrue() {
    // Arrange, Act and Assert
    assertFalse(
        Excluder.DEFAULT
            .withExclusionStrategy(mock(ExclusionStrategy.class), true, true)
            .excludeClass(null, true));
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
   * Test new {@link Excluder} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link Excluder}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Excluder.<init>()"})
  public void testNewExcluder() {
    // Arrange, Act and Assert
    assertFalse(new Excluder().excludeClass(null, true));
  }
}
