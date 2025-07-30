package com.google.gson.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.InstanceCreator;
import com.google.gson.ReflectionAccessFilter;
import com.google.gson.ReflectionAccessFilter.FilterResult;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class ConstructorConstructorDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ConstructorConstructor#ConstructorConstructor(Map, boolean, List)}
   *   <li>{@link ConstructorConstructor#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ConstructorConstructor.<init>(Map, boolean, List)",
    "java.lang.String ConstructorConstructor.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();

    // Act and Assert
    assertEquals(
        "{}", new ConstructorConstructor(instanceCreators, true, new ArrayList<>()).toString());
  }

  /**
   * Test {@link ConstructorConstructor#checkInstantiable(Class)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ConstructorConstructor#checkInstantiable(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String ConstructorConstructor.checkInstantiable(Class)"})
  public void testCheckInstantiable_whenJavaLangObject_thenReturnNull() {
    // Arrange
    Class<Object> c = Object.class;

    // Act and Assert
    assertNull(ConstructorConstructor.checkInstantiable(c));
  }

  /**
   * Test {@link ConstructorConstructor#checkInstantiable(Class)}.
   *
   * <ul>
   *   <li>When {@code ParameterizedType}.
   *   <li>Then return a string.
   * </ul>
   *
   * <p>Method under test: {@link ConstructorConstructor#checkInstantiable(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String ConstructorConstructor.checkInstantiable(Class)"})
  public void testCheckInstantiable_whenJavaLangReflectParameterizedType_thenReturnAString() {
    // Arrange
    Class<ParameterizedType> c = ParameterizedType.class;

    // Act and Assert
    assertEquals(
        "Interfaces can't be instantiated! Register an InstanceCreator or a TypeAdapter for this"
            + " type. Interface name: java.lang.reflect.ParameterizedType",
        ConstructorConstructor.checkInstantiable(c));
  }

  /**
   * Test {@link ConstructorConstructor#checkInstantiable(Class)}.
   *
   * <ul>
   *   <li>When {@code EnumSet}.
   *   <li>Then return a string.
   * </ul>
   *
   * <p>Method under test: {@link ConstructorConstructor#checkInstantiable(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String ConstructorConstructor.checkInstantiable(Class)"})
  public void testCheckInstantiable_whenJavaUtilEnumSet_thenReturnAString() {
    // Arrange
    Class<EnumSet> c = EnumSet.class;

    // Act and Assert
    assertEquals(
        "Abstract classes can't be instantiated! Adjust the R8 configuration or register an"
            + " InstanceCreator or a TypeAdapter for this type. Class name: java.util.EnumSet\n"
            + "See https://github.com/google/gson/blob/main/Troubleshooting.md#r8-abstract-class",
        ConstructorConstructor.checkInstantiable(c));
  }

  /**
   * Test {@link ConstructorConstructor#get(TypeToken, boolean)} with {@code typeToken}, {@code
   * allowUnsafe}.
   *
   * <ul>
   *   <li>Then calls {@link ReflectionAccessFilter#check(Class)}.
   * </ul>
   *
   * <p>Method under test: {@link ConstructorConstructor#get(TypeToken, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "com.google.gson.internal.ObjectConstructor ConstructorConstructor.get(TypeToken, boolean)"
  })
  public void testGetWithTypeTokenAllowUnsafe_thenCallsCheck() {
    // Arrange
    ReflectionAccessFilter reflectionAccessFilter = mock(ReflectionAccessFilter.class);
    when(reflectionAccessFilter.check(Mockito.<Class<?>>any())).thenReturn(FilterResult.ALLOW);

    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();
    reflectionFilters.add(reflectionAccessFilter);
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(new HashMap<>(), true, reflectionFilters);
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    // Act
    constructorConstructor.get(typeToken, true).construct();

    // Assert
    verify(reflectionAccessFilter).check(isA(Class.class));
  }

  /**
   * Test {@link ConstructorConstructor#get(TypeToken)} with {@code typeToken}.
   *
   * <ul>
   *   <li>Given {@link ReflectionAccessFilter} {@link ReflectionAccessFilter#check(Class)} return
   *       {@code ALLOW}.
   *   <li>Then calls {@link ReflectionAccessFilter#check(Class)}.
   * </ul>
   *
   * <p>Method under test: {@link ConstructorConstructor#get(TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "com.google.gson.internal.ObjectConstructor ConstructorConstructor.get(TypeToken)"
  })
  public void testGetWithTypeToken_givenReflectionAccessFilterCheckReturnAllow_thenCallsCheck() {
    // Arrange
    ReflectionAccessFilter reflectionAccessFilter = mock(ReflectionAccessFilter.class);
    when(reflectionAccessFilter.check(Mockito.<Class<?>>any())).thenReturn(FilterResult.ALLOW);

    ArrayList<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();
    reflectionFilters.add(reflectionAccessFilter);
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(new HashMap<>(), true, reflectionFilters);
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    // Act
    constructorConstructor.get(typeToken).construct();

    // Assert
    verify(reflectionAccessFilter).check(isA(Class.class));
  }
}
