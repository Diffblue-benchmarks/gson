package com.google.gson.internal.bind;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.Gson;
import com.google.gson.ToNumberPolicy;
import com.google.gson.ToNumberStrategy;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ObjectTypeAdapterDiffblueTest {
  /**
   * Test {@link ObjectTypeAdapter#getFactory(ToNumberStrategy)}.
   *
   * <ul>
   *   <li>Then create {@link Gson#Gson()} and {@link Object} return {@link ObjectTypeAdapter}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#getFactory(ToNumberStrategy)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapterFactory ObjectTypeAdapter.getFactory(ToNumberStrategy)"})
  public void testGetFactory_thenCreateGsonAndObjectReturnObjectTypeAdapter() {
    // Arrange and Act
    TypeAdapterFactory actualFactory = ObjectTypeAdapter.getFactory(mock(ToNumberStrategy.class));
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type);

    // Assert
    assertTrue(actualFactory.create(gson, getResult) instanceof ObjectTypeAdapter);
  }

  /**
   * Test {@link ObjectTypeAdapter#getFactory(ToNumberStrategy)}.
   *
   * <ul>
   *   <li>When {@link ToNumberPolicy#DOUBLE}.
   *   <li>Then create {@link Gson#Gson()} and {@link Object} return {@link ObjectTypeAdapter}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#getFactory(ToNumberStrategy)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapterFactory ObjectTypeAdapter.getFactory(ToNumberStrategy)"})
  public void testGetFactory_whenDouble_thenCreateGsonAndObjectReturnObjectTypeAdapter() {
    // Arrange and Act
    TypeAdapterFactory actualFactory = ObjectTypeAdapter.getFactory(ToNumberPolicy.DOUBLE);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type);

    // Assert
    assertTrue(actualFactory.create(gson, getResult) instanceof ObjectTypeAdapter);
  }
}
