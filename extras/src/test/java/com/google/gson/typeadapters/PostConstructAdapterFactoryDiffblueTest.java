package com.google.gson.typeadapters;

import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.Gson;
import com.google.gson.internal.bind.ObjectTypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.typeadapters.PostConstructAdapterFactory.PostConstructAdapter;
import java.io.IOException;
import java.io.StringReader;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class PostConstructAdapterFactoryDiffblueTest {
  /**
   * Test {@link PostConstructAdapterFactory#create(Gson, TypeToken)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link PostConstructAdapterFactory#create(Gson, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "com.google.gson.TypeAdapter PostConstructAdapterFactory.create(Gson, TypeToken)"
  })
  public void testCreate_whenJavaLangObject_thenReturnNull() {
    // Arrange
    PostConstructAdapterFactory postConstructAdapterFactory = new PostConstructAdapterFactory();
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> type2 = TypeToken.get(type);

    // Act and Assert
    assertNull(postConstructAdapterFactory.create(gson, type2));
  }

  /**
   * Test PostConstructAdapter {@link PostConstructAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@link ObjectTypeAdapter} {@link ObjectTypeAdapter#read(JsonReader)} return {@code
   *       null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link PostConstructAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PostConstructAdapter.read(JsonReader)"})
  public void testPostConstructAdapterRead_givenObjectTypeAdapterReadReturnNull_thenReturnNull()
      throws IOException {
    // Arrange
    ObjectTypeAdapter delegate = mock(ObjectTypeAdapter.class);
    when(delegate.read(Mockito.<JsonReader>any())).thenReturn(null);
    PostConstructAdapter<Object> postConstructAdapter = new PostConstructAdapter<>(delegate, null);

    // Act
    Object actualReadResult =
        postConstructAdapter.read(
            new JsonReader(
                new StringReader(
                    "\"This is a test string for the java.io.StringReader method. It includes"
                        + " various characters such as numbers 123, special characters @#$%, and"
                        + " spaces. It's designed to test the method's functionality.\"")));

    // Assert
    verify(delegate).read(isA(JsonReader.class));
    assertNull(actualReadResult);
  }
}
