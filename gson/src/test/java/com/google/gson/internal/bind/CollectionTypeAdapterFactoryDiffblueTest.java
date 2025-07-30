package com.google.gson.internal.bind;

import static org.junit.Assert.assertNull;

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

public class CollectionTypeAdapterFactoryDiffblueTest {
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
