package com.google.gson.internal.bind;

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
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DefaultDateTypeAdapterDiffblueTest {
  /**
   * Test {@link DefaultDateTypeAdapter#write(JsonWriter, Date)} with {@code JsonWriter}, {@code
   * Date}.
   *
   * <ul>
   *   <li>Then {@link JsonTreeWriter} (default constructor) {@link JsonPrimitive}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultDateTypeAdapter#write(JsonWriter, Date)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultDateTypeAdapter.write(JsonWriter, Date)"})
  public void testWriteWithJsonWriterDate_thenJsonTreeWriterJsonPrimitive() throws IOException {
    // Arrange
    DefaultDateTypeAdapter<Date> createShortStyleAdapterResult =
        DefaultDateTypeAdapterDiffblueTestFactory.createShortStyleAdapter();
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    createShortStyleAdapterResult.write(
        out, Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

    // Assert
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonPrimitive);
    assertTrue(getResult.getAsNumber() instanceof LazilyParsedNumber);
    assertEquals('1', getResult.getAsCharacter());
    assertFalse(getResult.getAsBoolean());
    assertFalse(getResult.isJsonNull());
    assertFalse(((JsonPrimitive) getResult).isBoolean());
    assertFalse(((JsonPrimitive) getResult).isNumber());
    assertTrue(getResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) getResult).isString());
    assertSame(getResult, getResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link DefaultDateTypeAdapter#write(JsonWriter, Date)} with {@code JsonWriter}, {@code
   * Date}.
   *
   * <ul>
   *   <li>When {@link JsonTreeWriter} (default constructor).
   *   <li>Then {@link JsonTreeWriter} (default constructor) {@link JsonNull}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultDateTypeAdapter#write(JsonWriter, Date)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultDateTypeAdapter.write(JsonWriter, Date)"})
  public void testWriteWithJsonWriterDate_whenJsonTreeWriter_thenJsonTreeWriterJsonNull()
      throws IOException {
    // Arrange
    DefaultDateTypeAdapter<Date> createSimpleDateAdapterResult =
        DefaultDateTypeAdapterDiffblueTestFactory.createSimpleDateAdapter();
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    createSimpleDateAdapterResult.write(out, null);

    // Assert that nothing has changed
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonNull);
    assertFalse(getResult.isJsonPrimitive());
    assertTrue(getResult.isJsonNull());
  }

  /**
   * Test {@link DefaultDateTypeAdapter#read(JsonReader)}.
   *
   * <p>Method under test: {@link DefaultDateTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date DefaultDateTypeAdapter.read(JsonReader)"})
  public void testRead() throws IOException {
    // Arrange
    DefaultDateTypeAdapter<Date> createSimpleDateAdapterResult =
        DefaultDateTypeAdapterDiffblueTestFactory.createSimpleDateAdapter();

    JsonReader in =
        new JsonReader(
            new StringReader(
                "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed JSON"));
    in.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> createSimpleDateAdapterResult.read(in));
  }

  /**
   * Test {@link DefaultDateTypeAdapter#read(JsonReader)}.
   *
   * <p>Method under test: {@link DefaultDateTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date DefaultDateTypeAdapter.read(JsonReader)"})
  public void testRead2() throws IOException {
    // Arrange
    DefaultDateTypeAdapter<Date> createSimpleDateAdapterResult =
        DefaultDateTypeAdapterDiffblueTestFactory.createSimpleDateAdapter();
    CharArrayReader in = new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray());

    JsonReader in2 = new JsonReader(in);
    in2.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> createSimpleDateAdapterResult.read(in2));
  }

  /**
   * Test {@link DefaultDateTypeAdapter#read(JsonReader)}.
   *
   * <p>Method under test: {@link DefaultDateTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date DefaultDateTypeAdapter.read(JsonReader)"})
  public void testRead3() throws IOException {
    // Arrange
    DefaultDateTypeAdapter<Date> createSimpleDateAdapterResult =
        DefaultDateTypeAdapterDiffblueTestFactory.createSimpleDateAdapter();

    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.skip(1L);

    JsonReader in2 = new JsonReader(in);
    in2.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> createSimpleDateAdapterResult.read(in2));
  }

  /**
   * Test {@link DefaultDateTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultDateTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date DefaultDateTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_whenStringReaderWithFoo_thenThrowJsonSyntaxException()
      throws IOException {
    // Arrange
    DefaultDateTypeAdapter<Date> createSimpleDateAdapterResult =
        DefaultDateTypeAdapterDiffblueTestFactory.createSimpleDateAdapter();

    JsonReader in = new JsonReader(new StringReader("foo"));
    in.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> createSimpleDateAdapterResult.read(in));
  }

  /**
   * Test {@link DefaultDateTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given one.
   *   <li>When {@link StringReader#StringReader(String)} with {@code Invalid number:} skip one.
   * </ul>
   *
   * <p>Method under test: {@link DefaultDateTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date DefaultDateTypeAdapter.read(JsonReader)"})
  public void testRead_givenOne_whenStringReaderWithInvalidNumberSkipOne() throws IOException {
    // Arrange
    DefaultDateTypeAdapter<Date> createSimpleDateAdapterResult =
        DefaultDateTypeAdapterDiffblueTestFactory.createSimpleDateAdapter();

    StringReader in = new StringReader("Invalid number: ");
    in.skip(1L);

    JsonReader in2 = new JsonReader(in);
    in2.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> createSimpleDateAdapterResult.read(in2));
  }

  /**
   * Test {@link DefaultDateTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given three.
   * </ul>
   *
   * <p>Method under test: {@link DefaultDateTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date DefaultDateTypeAdapter.read(JsonReader)"})
  public void testRead_givenThree() throws IOException {
    // Arrange
    DefaultDateTypeAdapter<Date> createSimpleDateAdapterResult =
        DefaultDateTypeAdapterDiffblueTestFactory.createSimpleDateAdapter();

    StringReader in =
        new StringReader("https://github.com/google/gson/blob/main/Troubleshooting.md#");
    in.read("\u0001\u0006\u0001\u0006".toCharArray(), 1, 3);
    in.skip(6L);

    JsonReader in2 = new JsonReader(in);
    in2.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> createSimpleDateAdapterResult.read(in2));
  }

  /**
   * Test {@link DefaultDateTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link CharArrayReader#CharArrayReader(char[])} with {@code ﻿} toCharArray.
   * </ul>
   *
   * <p>Method under test: {@link DefaultDateTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date DefaultDateTypeAdapter.read(JsonReader)"})
  public void testRead_whenCharArrayReaderWithZeroWidthNoBreakSpaceToCharArray()
      throws IOException {
    // Arrange
    DefaultDateTypeAdapter<Date> createSimpleDateAdapterResult =
        DefaultDateTypeAdapterDiffblueTestFactory.createSimpleDateAdapter();
    CharArrayReader in = new CharArrayReader("﻿\u0006\u0001\u0006".toCharArray());

    JsonReader in2 = new JsonReader(in);
    in2.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> createSimpleDateAdapterResult.read(in2));
  }

  /**
   * Test {@link DefaultDateTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonPrimitive#JsonPrimitive(String)} with string is empty string.
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultDateTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date DefaultDateTypeAdapter.read(JsonReader)"})
  public void testRead_whenJsonPrimitiveWithStringIsEmptyString_thenThrowJsonSyntaxException()
      throws IOException {
    // Arrange
    DefaultDateTypeAdapter<Date> createSimpleDateAdapterResult =
        DefaultDateTypeAdapterDiffblueTestFactory.createSimpleDateAdapter();

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class,
        () -> createSimpleDateAdapterResult.read(new JsonTreeReader(new JsonPrimitive(""))));
  }

  /**
   * Test {@link DefaultDateTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonPrimitive#JsonPrimitive(String)} with {@code String}.
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultDateTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date DefaultDateTypeAdapter.read(JsonReader)"})
  public void testRead_whenJsonPrimitiveWithString_thenThrowJsonSyntaxException()
      throws IOException {
    // Arrange
    DefaultDateTypeAdapter<Date> createSimpleDateAdapterResult =
        DefaultDateTypeAdapterDiffblueTestFactory.createSimpleDateAdapter();

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class,
        () -> createSimpleDateAdapterResult.read(new JsonTreeReader(new JsonPrimitive("String"))));
  }

  /**
   * Test {@link DefaultDateTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultDateTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date DefaultDateTypeAdapter.read(JsonReader)"})
  public void testRead_whenJsonTreeReaderWithElementIsInstance_thenReturnNull() throws IOException {
    // Arrange
    DefaultDateTypeAdapter<Date> createSimpleDateAdapterResult =
        DefaultDateTypeAdapterDiffblueTestFactory.createSimpleDateAdapter();
    JsonTreeReader in = new JsonTreeReader(JsonNull.INSTANCE);

    // Act
    Date actualReadResult = createSimpleDateAdapterResult.read(in);

    // Assert
    assertNull(actualReadResult);
    assertEquals(0, in.getStackSize());
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link DefaultDateTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultDateTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date DefaultDateTypeAdapter.read(JsonReader)"})
  public void testRead_whenStringReaderWith42_thenThrowJsonSyntaxException() throws IOException {
    // Arrange
    DefaultDateTypeAdapter<Date> createSimpleDateAdapterResult =
        DefaultDateTypeAdapterDiffblueTestFactory.createSimpleDateAdapter();

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class,
        () -> createSimpleDateAdapterResult.read(new JsonReader(new StringReader("42"))));
  }

  /**
   * Test {@link DefaultDateTypeAdapter#toString()}.
   *
   * <p>Method under test: {@link DefaultDateTypeAdapter#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String DefaultDateTypeAdapter.toString()"})
  public void testToString() {
    // Arrange
    DefaultDateTypeAdapter<Date> createSimpleDateAdapterResult =
        DefaultDateTypeAdapterDiffblueTestFactory.createSimpleDateAdapter();

    // Act and Assert
    assertEquals("DefaultDateTypeAdapter(yyyy-MM-dd)", createSimpleDateAdapterResult.toString());
  }
}
