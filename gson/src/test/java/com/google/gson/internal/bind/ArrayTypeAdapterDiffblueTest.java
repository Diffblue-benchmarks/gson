package com.google.gson.internal.bind;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.IOException;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ArrayTypeAdapterDiffblueTest {
  /**
   * Test {@link ArrayTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonArray#JsonArray(int)} with capacity is three.
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link ArrayTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ArrayTypeAdapter.read(JsonReader)"})
  public void testRead_whenJsonArrayWithCapacityIsThree_thenReturnArrayLengthIsZero()
      throws IOException {
    // Arrange
    Gson context = new Gson();
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> componentTypeAdapter =
        new TreeTypeAdapter<>(null, null, gson, typeToken, ArrayTypeAdapter.FACTORY);
    Class<Object> componentType = Object.class;

    ArrayTypeAdapter<Object> arrayTypeAdapter =
        new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType);
    JsonTreeReader in = new JsonTreeReader(new JsonArray(3));

    // Act
    Object actualReadResult = arrayTypeAdapter.read(in);

    // Assert
    assertTrue(actualReadResult instanceof Object[]);
    assertEquals(0, ((Object[]) actualReadResult).length);
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link ArrayTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ArrayTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ArrayTypeAdapter.read(JsonReader)"})
  public void testRead_whenJsonTreeReaderWithElementIsInstance_thenReturnNull() throws IOException {
    // Arrange
    Gson context = new Gson();
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> componentTypeAdapter =
        new TreeTypeAdapter<>(null, null, gson, typeToken, ArrayTypeAdapter.FACTORY);
    Class<Object> componentType = Object.class;

    ArrayTypeAdapter<Object> arrayTypeAdapter =
        new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType);
    JsonTreeReader in = new JsonTreeReader(JsonNull.INSTANCE);

    // Act
    Object actualReadResult = arrayTypeAdapter.read(in);

    // Assert
    assertNull(actualReadResult);
    assertFalse(in.hasNext());
  }
}
