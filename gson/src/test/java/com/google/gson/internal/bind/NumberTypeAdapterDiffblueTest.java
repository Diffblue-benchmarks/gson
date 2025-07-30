package com.google.gson.internal.bind;

import static org.junit.Assert.assertNull;
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

public class NumberTypeAdapterDiffblueTest {
  /**
   * Test {@link NumberTypeAdapter#getFactory(ToNumberStrategy)}.
   *
   * <ul>
   *   <li>When {@link ToNumberPolicy#LAZILY_PARSED_NUMBER}.
   *   <li>Then return create {@link Gson#Gson()} and {@link Object} is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link NumberTypeAdapter#getFactory(ToNumberStrategy)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapterFactory NumberTypeAdapter.getFactory(ToNumberStrategy)"})
  public void testGetFactory_whenLazily_parsed_number_thenReturnCreateGsonAndObjectIsNull() {
    // Arrange and Act
    TypeAdapterFactory actualFactory =
        NumberTypeAdapter.getFactory(ToNumberPolicy.LAZILY_PARSED_NUMBER);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type);

    // Assert
    assertNull(actualFactory.create(gson, getResult));
  }

  /**
   * Test {@link NumberTypeAdapter#getFactory(ToNumberStrategy)}.
   *
   * <ul>
   *   <li>When {@link ToNumberStrategy}.
   *   <li>Then return create {@link Gson#Gson()} and {@link Object} is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link NumberTypeAdapter#getFactory(ToNumberStrategy)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapterFactory NumberTypeAdapter.getFactory(ToNumberStrategy)"})
  public void testGetFactory_whenToNumberStrategy_thenReturnCreateGsonAndObjectIsNull() {
    // Arrange and Act
    TypeAdapterFactory actualFactory = NumberTypeAdapter.getFactory(mock(ToNumberStrategy.class));
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type);

    // Assert
    assertNull(actualFactory.create(gson, getResult));
  }
}
