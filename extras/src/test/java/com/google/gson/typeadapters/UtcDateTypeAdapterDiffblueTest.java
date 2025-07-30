package com.google.gson.typeadapters;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.Strictness;
import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.stream.JsonReader;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.StringReader;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class UtcDateTypeAdapterDiffblueTest {
  /**
   * Test {@link UtcDateTypeAdapter#read(JsonReader)}.
   *
   * <p>Method under test: {@link UtcDateTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Date UtcDateTypeAdapter.read(JsonReader)"})
  public void testRead() throws IOException {
    // Arrange
    UtcDateTypeAdapter utcDateTypeAdapter = new UtcDateTypeAdapter();

    JsonReader in =
        new JsonReader(
            new StringReader(
                "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed JSON"));
    in.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(JsonParseException.class, () -> utcDateTypeAdapter.read(in));
  }

  /**
   * Test {@link UtcDateTypeAdapter#read(JsonReader)}.
   *
   * <p>Method under test: {@link UtcDateTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Date UtcDateTypeAdapter.read(JsonReader)"})
  public void testRead2() throws IOException {
    // Arrange
    UtcDateTypeAdapter utcDateTypeAdapter = new UtcDateTypeAdapter();

    JsonReader in = new JsonReader(new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray()));
    in.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(JsonParseException.class, () -> utcDateTypeAdapter.read(in));
  }

  /**
   * Test {@link UtcDateTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code Expected}.
   *   <li>Then throw {@link JsonParseException}.
   * </ul>
   *
   * <p>Method under test: {@link UtcDateTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Date UtcDateTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_whenStringReaderWithExpected_thenThrowJsonParseException()
      throws IOException {
    // Arrange
    UtcDateTypeAdapter utcDateTypeAdapter = new UtcDateTypeAdapter();

    JsonReader in = new JsonReader(new StringReader("Expected "));
    in.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(JsonParseException.class, () -> utcDateTypeAdapter.read(in));
  }

  /**
   * Test {@link UtcDateTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then throw {@link JsonParseException}.
   * </ul>
   *
   * <p>Method under test: {@link UtcDateTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Date UtcDateTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_whenStringReaderWithFoo_thenThrowJsonParseException()
      throws IOException {
    // Arrange
    UtcDateTypeAdapter utcDateTypeAdapter = new UtcDateTypeAdapter();

    JsonReader in = new JsonReader(new StringReader("foo"));
    in.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(JsonParseException.class, () -> utcDateTypeAdapter.read(in));
  }

  /**
   * Test {@link UtcDateTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link CharArrayReader#CharArrayReader(char[])} with {@code 1} toCharArray.
   *   <li>Then throw {@link JsonParseException}.
   * </ul>
   *
   * <p>Method under test: {@link UtcDateTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Date UtcDateTypeAdapter.read(JsonReader)"})
  public void testRead_whenCharArrayReaderWith1ToCharArray_thenThrowJsonParseException()
      throws IOException {
    // Arrange
    UtcDateTypeAdapter utcDateTypeAdapter = new UtcDateTypeAdapter();

    JsonReader in = new JsonReader(new CharArrayReader("1\u0006\u0001\u0006".toCharArray()));
    in.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(JsonParseException.class, () -> utcDateTypeAdapter.read(in));
  }

  /**
   * Test {@link UtcDateTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonPrimitive#JsonPrimitive(String)} with {@code String}.
   *   <li>Then throw {@link JsonParseException}.
   * </ul>
   *
   * <p>Method under test: {@link UtcDateTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Date UtcDateTypeAdapter.read(JsonReader)"})
  public void testRead_whenJsonPrimitiveWithString_thenThrowJsonParseException()
      throws IOException {
    // Arrange
    UtcDateTypeAdapter utcDateTypeAdapter = new UtcDateTypeAdapter();

    // Act and Assert
    assertThrows(
        JsonParseException.class,
        () -> utcDateTypeAdapter.read(new JsonTreeReader(new JsonPrimitive("String"))));
  }

  /**
   * Test {@link UtcDateTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link JsonNull}
   *       (default constructor).
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link UtcDateTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Date UtcDateTypeAdapter.read(JsonReader)"})
  public void testRead_whenJsonTreeReaderWithElementIsJsonNull_thenReturnNull() throws IOException {
    // Arrange
    UtcDateTypeAdapter utcDateTypeAdapter = new UtcDateTypeAdapter();
    JsonTreeReader in = new JsonTreeReader(new JsonNull());

    // Act and Assert
    assertNull(utcDateTypeAdapter.read(in));
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link UtcDateTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then throw {@link JsonParseException}.
   * </ul>
   *
   * <p>Method under test: {@link UtcDateTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Date UtcDateTypeAdapter.read(JsonReader)"})
  public void testRead_whenStringReaderWith42_thenThrowJsonParseException() throws IOException {
    // Arrange
    UtcDateTypeAdapter utcDateTypeAdapter = new UtcDateTypeAdapter();

    // Act and Assert
    assertThrows(
        JsonParseException.class,
        () -> utcDateTypeAdapter.read(new JsonReader(new StringReader("42"))));
  }
}
