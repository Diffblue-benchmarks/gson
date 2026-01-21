package com.google.gson.internal.bind;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import com.google.gson.Strictness;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.internal.bind.DefaultDateTypeAdapter.DateType;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DefaultDateTypeAdapterDiffblueTest {
  /**
   * Test {@link DefaultDateTypeAdapter#write(JsonWriter, Date)} with {@code JsonWriter}, {@code
   * Date}.
   *
   * <p>Method under test: {@link DefaultDateTypeAdapter#write(JsonWriter, Date)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultDateTypeAdapter.write(JsonWriter, Date)"})
  public void testWriteWithJsonWriterDate() throws IOException {
    // Arrange
    DefaultDateTypeAdapter<Date> defaultDateTypeAdapter =
        new DefaultDateTypeAdapter<>(mock(DateType.class), "2020-03-01");
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    defaultDateTypeAdapter.write(
        out, Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

    // Assert
    assertEquals("\"2020-03-01\"", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link DefaultDateTypeAdapter#write(JsonWriter, Date)} with {@code JsonWriter}, {@code
   * Date}.
   *
   * <p>Method under test: {@link DefaultDateTypeAdapter#write(JsonWriter, Date)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultDateTypeAdapter.write(JsonWriter, Date)"})
  public void testWriteWithJsonWriterDate2() throws IOException {
    // Arrange
    DefaultDateTypeAdapter<Date> defaultDateTypeAdapter =
        new DefaultDateTypeAdapter<>(mock(DateType.class), "");
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    defaultDateTypeAdapter.write(
        out, Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

    // Assert
    assertEquals("\"\"", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link DefaultDateTypeAdapter#write(JsonWriter, Date)} with {@code JsonWriter}, {@code
   * Date}.
   *
   * <ul>
   *   <li>Given {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultDateTypeAdapter#write(JsonWriter, Date)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultDateTypeAdapter.write(JsonWriter, Date)"})
  public void testWriteWithJsonWriterDate_givenTrue() throws IOException {
    // Arrange
    DefaultDateTypeAdapter<Date> defaultDateTypeAdapter =
        new DefaultDateTypeAdapter<>(mock(DateType.class), "2020-03-01");

    JsonWriter out = new JsonWriter(new StringWriter());
    out.setHtmlSafe(true);

    // Act
    defaultDateTypeAdapter.write(
        out, Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

    // Assert
    assertEquals("\"2020-03-01\"", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link DefaultDateTypeAdapter#write(JsonWriter, Date)} with {@code JsonWriter}, {@code
   * Date}.
   *
   * <ul>
   *   <li>Then {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()} Out toString is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultDateTypeAdapter#write(JsonWriter, Date)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultDateTypeAdapter.write(JsonWriter, Date)"})
  public void testWriteWithJsonWriterDate_thenJsonWriterWithOutIsStringWriterOutToStringIsNull()
      throws IOException {
    // Arrange
    DefaultDateTypeAdapter<Date> defaultDateTypeAdapter =
        new DefaultDateTypeAdapter<>(mock(DateType.class), "2020-03-01");
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    defaultDateTypeAdapter.write(out, null);

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
    DefaultDateTypeAdapter<Date> defaultDateTypeAdapter =
        new DefaultDateTypeAdapter<>(mock(DateType.class), "2020-03-01");
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    defaultDateTypeAdapter.write(out, null);

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
   * Test {@link DefaultDateTypeAdapter#write(JsonWriter, Date)} with {@code JsonWriter}, {@code
   * Date}.
   *
   * <ul>
   *   <li>When {@link JsonTreeWriter} (default constructor).
   *   <li>Then {@link JsonTreeWriter} (default constructor) {@link JsonPrimitive}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultDateTypeAdapter#write(JsonWriter, Date)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultDateTypeAdapter.write(JsonWriter, Date)"})
  public void testWriteWithJsonWriterDate_whenJsonTreeWriter_thenJsonTreeWriterJsonPrimitive()
      throws IOException {
    // Arrange
    DefaultDateTypeAdapter<Date> defaultDateTypeAdapter =
        new DefaultDateTypeAdapter<>(mock(DateType.class), "2020-03-01");
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    defaultDateTypeAdapter.write(
        out, Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

    // Assert
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonPrimitive);
    Number asNumber = getResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("2020-03-01", getResult.getAsString());
    assertEquals("2020-03-01", asNumber.toString());
    assertEquals('2', getResult.getAsCharacter());
    assertFalse(getResult.getAsBoolean());
    assertFalse(getResult.isJsonNull());
    assertFalse(((JsonPrimitive) getResult).isBoolean());
    assertFalse(((JsonPrimitive) getResult).isNumber());
    assertTrue(getResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) getResult).isString());
    assertSame(getResult, getResult.getAsJsonPrimitive());
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
    DefaultDateTypeAdapter<Date> defaultDateTypeAdapter =
        new DefaultDateTypeAdapter<>(mock(DateType.class), "2020-03-01");

    JsonReader in =
        new JsonReader(
            new StringReader(
                "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed JSON"));
    in.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> defaultDateTypeAdapter.read(in));
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
    DateType<Date> dateType = mock(DateType.class);
    when(dateType.deserialize(Mockito.<Date>any()))
        .thenReturn(
            Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    DefaultDateTypeAdapter<Date> defaultDateTypeAdapter =
        new DefaultDateTypeAdapter<>(dateType, "42");

    // Act
    defaultDateTypeAdapter.read(new JsonReader(new StringReader("42")));

    // Assert
    verify(dateType).deserialize(isA(Date.class));
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
    DefaultDateTypeAdapter<Date> defaultDateTypeAdapter =
        new DefaultDateTypeAdapter<>(mock(DateType.class), 1, 1);

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class,
        () -> defaultDateTypeAdapter.read(new JsonReader(new StringReader("4242"))));
  }

  /**
   * Test {@link DefaultDateTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@link DateType} {@link DateType#deserialize(Date)} throw {@link
   *       JsonSyntaxException#JsonSyntaxException(String)} with {@code Msg}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultDateTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date DefaultDateTypeAdapter.read(JsonReader)"})
  public void testRead_givenDateTypeDeserializeThrowJsonSyntaxExceptionWithMsg()
      throws IOException {
    // Arrange
    DateType<Date> dateType = mock(DateType.class);
    when(dateType.deserialize(Mockito.<Date>any())).thenThrow(new JsonSyntaxException("Msg"));
    DefaultDateTypeAdapter<Date> defaultDateTypeAdapter =
        new DefaultDateTypeAdapter<>(dateType, "42");

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class,
        () -> defaultDateTypeAdapter.read(new JsonReader(new StringReader("42"))));
    verify(dateType).deserialize(isA(Date.class));
  }

  /**
   * Test {@link DefaultDateTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link JsonReader#JsonReader(Reader)} with in is {@link
   *       CharArrayReader#CharArrayReader(char[])} Strictness is {@code LENIENT}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultDateTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date DefaultDateTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_whenJsonReaderWithInIsCharArrayReaderStrictnessIsLenient()
      throws IOException {
    // Arrange
    DefaultDateTypeAdapter<Date> defaultDateTypeAdapter =
        new DefaultDateTypeAdapter<>(mock(DateType.class), "2020-03-01");
    CharArrayReader in = new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray());

    JsonReader in2 = new JsonReader(in);
    in2.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> defaultDateTypeAdapter.read(in2));
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
    DefaultDateTypeAdapter<Date> defaultDateTypeAdapter =
        new DefaultDateTypeAdapter<>(mock(DateType.class), "2020-03-01");

    JsonReader in = new JsonReader(new StringReader("foo"));
    in.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> defaultDateTypeAdapter.read(in));
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
    DefaultDateTypeAdapter<Date> defaultDateTypeAdapter =
        new DefaultDateTypeAdapter<>(mock(DateType.class), "2020-03-01");

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class,
        () -> defaultDateTypeAdapter.read(new JsonTreeReader(new JsonPrimitive(""))));
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
    DefaultDateTypeAdapter<Date> defaultDateTypeAdapter =
        new DefaultDateTypeAdapter<>(mock(DateType.class), "2020-03-01");

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class,
        () -> defaultDateTypeAdapter.read(new JsonTreeReader(new JsonPrimitive("String"))));
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
    DefaultDateTypeAdapter<Date> defaultDateTypeAdapter =
        new DefaultDateTypeAdapter<>(mock(DateType.class), "2020-03-01");
    JsonTreeReader in = new JsonTreeReader(JsonNull.INSTANCE);

    // Act
    Date actualReadResult = defaultDateTypeAdapter.read(in);

    // Assert
    assertNull(actualReadResult);
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
    DefaultDateTypeAdapter<Date> defaultDateTypeAdapter =
        new DefaultDateTypeAdapter<>(mock(DateType.class), "2020-03-01");

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class,
        () -> defaultDateTypeAdapter.read(new JsonReader(new StringReader("42"))));
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
    DefaultDateTypeAdapter<Date> defaultDateTypeAdapter =
        new DefaultDateTypeAdapter<>(mock(DateType.class), "2020-03-01");

    // Act and Assert
    assertEquals("DefaultDateTypeAdapter(2020-03-01)", defaultDateTypeAdapter.toString());
  }
}
