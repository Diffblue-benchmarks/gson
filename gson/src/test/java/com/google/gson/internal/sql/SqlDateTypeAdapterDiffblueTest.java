package com.google.gson.internal.sql;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import com.google.gson.Strictness;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Date;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SqlDateTypeAdapterDiffblueTest {
  /**
   * Test {@link SqlDateTypeAdapter#read(JsonReader)}.
   *
   * <p>Method under test: {@link SqlDateTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date SqlDateTypeAdapter.read(JsonReader)"})
  public void testRead() throws IOException {
    // Arrange
    SqlDateTypeAdapter sqlDateTypeAdapter = new SqlDateTypeAdapter();

    JsonReader in =
        new JsonReader(
            new StringReader(
                "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed JSON"));
    in.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> sqlDateTypeAdapter.read(in));
  }

  /**
   * Test {@link SqlDateTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link SqlDateTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date SqlDateTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_whenStringReaderWithFoo_thenThrowJsonSyntaxException()
      throws IOException {
    // Arrange
    SqlDateTypeAdapter sqlDateTypeAdapter = new SqlDateTypeAdapter();

    JsonReader in = new JsonReader(new StringReader("foo"));
    in.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> sqlDateTypeAdapter.read(in));
  }

  /**
   * Test {@link SqlDateTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonPrimitive#JsonPrimitive(String)} with {@code String}.
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link SqlDateTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date SqlDateTypeAdapter.read(JsonReader)"})
  public void testRead_whenJsonPrimitiveWithString_thenThrowJsonSyntaxException()
      throws IOException {
    // Arrange
    SqlDateTypeAdapter sqlDateTypeAdapter = new SqlDateTypeAdapter();

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class,
        () -> sqlDateTypeAdapter.read(new JsonTreeReader(new JsonPrimitive("String"))));
  }

  /**
   * Test {@link SqlDateTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SqlDateTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date SqlDateTypeAdapter.read(JsonReader)"})
  public void testRead_whenJsonTreeReaderWithElementIsInstance_thenReturnNull() throws IOException {
    // Arrange
    SqlDateTypeAdapter sqlDateTypeAdapter = new SqlDateTypeAdapter();
    JsonTreeReader in = new JsonTreeReader(JsonNull.INSTANCE);

    // Act
    Date actualReadResult = sqlDateTypeAdapter.read(in);

    // Assert
    assertNull(actualReadResult);
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link SqlDateTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link SqlDateTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date SqlDateTypeAdapter.read(JsonReader)"})
  public void testRead_whenStringReaderWith42_thenThrowJsonSyntaxException() throws IOException {
    // Arrange
    SqlDateTypeAdapter sqlDateTypeAdapter = new SqlDateTypeAdapter();

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class,
        () -> sqlDateTypeAdapter.read(new JsonReader(new StringReader("42"))));
  }

  /**
   * Test {@link SqlDateTypeAdapter#write(JsonWriter, Date)} with {@code JsonWriter}, {@code Date}.
   *
   * <ul>
   *   <li>Given {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SqlDateTypeAdapter#write(JsonWriter, Date)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SqlDateTypeAdapter.write(JsonWriter, Date)"})
  public void testWriteWithJsonWriterDate_givenTrue() throws IOException {
    // Arrange
    SqlDateTypeAdapter sqlDateTypeAdapter = new SqlDateTypeAdapter();

    JsonWriter out = new JsonWriter(new StringWriter());
    out.setHtmlSafe(true);

    // Act
    sqlDateTypeAdapter.write(out, new Date(1L));

    // Assert
    assertArrayEquals(
        new int[] {
          7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link SqlDateTypeAdapter#write(JsonWriter, Date)} with {@code JsonWriter}, {@code Date}.
   *
   * <ul>
   *   <li>Then {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()} Out toString is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SqlDateTypeAdapter#write(JsonWriter, Date)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SqlDateTypeAdapter.write(JsonWriter, Date)"})
  public void testWriteWithJsonWriterDate_thenJsonWriterWithOutIsStringWriterOutToStringIsNull()
      throws IOException {
    // Arrange
    SqlDateTypeAdapter sqlDateTypeAdapter = new SqlDateTypeAdapter();
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    sqlDateTypeAdapter.write(out, null);

    // Assert
    assertEquals("null", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link SqlDateTypeAdapter#write(JsonWriter, Date)} with {@code JsonWriter}, {@code Date}.
   *
   * <ul>
   *   <li>When {@link JsonTreeWriter} (default constructor).
   *   <li>Then {@link JsonTreeWriter} (default constructor) {@link JsonNull}.
   * </ul>
   *
   * <p>Method under test: {@link SqlDateTypeAdapter#write(JsonWriter, Date)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SqlDateTypeAdapter.write(JsonWriter, Date)"})
  public void testWriteWithJsonWriterDate_whenJsonTreeWriter_thenJsonTreeWriterJsonNull()
      throws IOException {
    // Arrange
    SqlDateTypeAdapter sqlDateTypeAdapter = new SqlDateTypeAdapter();
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    sqlDateTypeAdapter.write(out, null);

    // Assert that nothing has changed
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonNull);
    assertFalse(getResult.isJsonPrimitive());
    assertTrue(getResult.isJsonNull());
    assertArrayEquals(
        new int[] {
          6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link SqlDateTypeAdapter#write(JsonWriter, Date)} with {@code JsonWriter}, {@code Date}.
   *
   * <ul>
   *   <li>When {@link JsonTreeWriter} (default constructor).
   *   <li>Then {@link JsonTreeWriter} (default constructor) {@link JsonPrimitive}.
   * </ul>
   *
   * <p>Method under test: {@link SqlDateTypeAdapter#write(JsonWriter, Date)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SqlDateTypeAdapter.write(JsonWriter, Date)"})
  public void testWriteWithJsonWriterDate_whenJsonTreeWriter_thenJsonTreeWriterJsonPrimitive()
      throws IOException {
    // Arrange
    SqlDateTypeAdapter sqlDateTypeAdapter = new SqlDateTypeAdapter();
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    sqlDateTypeAdapter.write(out, new Date(1L));

    // Assert
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonPrimitive);
    assertTrue(getResult.getAsNumber() instanceof LazilyParsedNumber);
    assertFalse(getResult.getAsBoolean());
    assertFalse(getResult.isJsonNull());
    assertFalse(((JsonPrimitive) getResult).isBoolean());
    assertFalse(((JsonPrimitive) getResult).isNumber());
    assertTrue(getResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) getResult).isString());
    assertSame(getResult, getResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link SqlDateTypeAdapter#write(JsonWriter, Date)} with {@code JsonWriter}, {@code Date}.
   *
   * <ul>
   *   <li>When {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()}.
   * </ul>
   *
   * <p>Method under test: {@link SqlDateTypeAdapter#write(JsonWriter, Date)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SqlDateTypeAdapter.write(JsonWriter, Date)"})
  public void testWriteWithJsonWriterDate_whenJsonWriterWithOutIsStringWriter() throws IOException {
    // Arrange
    SqlDateTypeAdapter sqlDateTypeAdapter = new SqlDateTypeAdapter();
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    sqlDateTypeAdapter.write(out, new Date(1L));

    // Assert
    assertArrayEquals(
        new int[] {
          7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }
}
