package com.google.gson.internal.bind;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.Gson;
import com.google.gson.InstanceCreator;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Test;
import org.junit.experimental.categories.Category;

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
