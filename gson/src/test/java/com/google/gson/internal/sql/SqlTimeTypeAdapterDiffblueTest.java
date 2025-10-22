package com.google.gson.internal.sql;

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
import java.sql.Time;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SqlTimeTypeAdapterDiffblueTest {
  /**
   * Test {@link SqlTimeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given eleven.
   *   <li>When {@link StringReader#StringReader(String)} with {@code ' as SQL Time; at path} skip
   *       eleven.
   * </ul>
   *
   * <p>Method under test: {@link SqlTimeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Time SqlTimeTypeAdapter.read(JsonReader)"})
  public void testRead_givenEleven_whenStringReaderWithAsSqlTimeAtPathSkipEleven()
      throws IOException {
    // Arrange
    SqlTimeTypeAdapter sqlTimeTypeAdapter = new SqlTimeTypeAdapter();

    StringReader in = new StringReader("' as SQL Time; at path ");
    in.skip(11L);

    JsonReader in2 = new JsonReader(in);
    in2.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> sqlTimeTypeAdapter.read(in2));
  }

  /**
   * Test {@link SqlTimeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given eleven.
   *   <li>When {@link StringReader#StringReader(String)} with {@code End of input} skip eleven.
   * </ul>
   *
   * <p>Method under test: {@link SqlTimeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Time SqlTimeTypeAdapter.read(JsonReader)"})
  public void testRead_givenEleven_whenStringReaderWithEndOfInputSkipEleven() throws IOException {
    // Arrange
    SqlTimeTypeAdapter sqlTimeTypeAdapter = new SqlTimeTypeAdapter();

    StringReader in = new StringReader("End of input");
    in.skip(11L);

    JsonReader in2 = new JsonReader(in);
    in2.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> sqlTimeTypeAdapter.read(in2));
  }

  /**
   * Test {@link SqlTimeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with a string.
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link SqlTimeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Time SqlTimeTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_whenStringReaderWithAString_thenThrowJsonSyntaxException()
      throws IOException {
    // Arrange
    SqlTimeTypeAdapter sqlTimeTypeAdapter = new SqlTimeTypeAdapter();

    JsonReader in =
        new JsonReader(
            new StringReader(
                "\"This is a test string for the java.io.StringReader method. It includes various"
                    + " characters such as numbers 123, special characters @#$%, and spaces.\""));
    in.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> sqlTimeTypeAdapter.read(in));
  }

  /**
   * Test {@link SqlTimeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code at line}.
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link SqlTimeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Time SqlTimeTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_whenStringReaderWithAtLine_thenThrowJsonSyntaxException()
      throws IOException {
    // Arrange
    SqlTimeTypeAdapter sqlTimeTypeAdapter = new SqlTimeTypeAdapter();

    JsonReader in = new JsonReader(new StringReader(" at line "));
    in.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> sqlTimeTypeAdapter.read(in));
  }

  /**
   * Test {@link SqlTimeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link SqlTimeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Time SqlTimeTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_whenStringReaderWithFoo_thenThrowJsonSyntaxException()
      throws IOException {
    // Arrange
    SqlTimeTypeAdapter sqlTimeTypeAdapter = new SqlTimeTypeAdapter();

    JsonReader in = new JsonReader(new StringReader("foo"));
    in.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> sqlTimeTypeAdapter.read(in));
  }

  /**
   * Test {@link SqlTimeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code See}.
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link SqlTimeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Time SqlTimeTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_whenStringReaderWithSee_thenThrowJsonSyntaxException()
      throws IOException {
    // Arrange
    SqlTimeTypeAdapter sqlTimeTypeAdapter = new SqlTimeTypeAdapter();

    JsonReader in = new JsonReader(new StringReader("\nSee "));
    in.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> sqlTimeTypeAdapter.read(in));
  }

  /**
   * Test {@link SqlTimeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given one.
   *   <li>When {@link StringReader#StringReader(String)} with {@code End of input} skip one.
   * </ul>
   *
   * <p>Method under test: {@link SqlTimeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Time SqlTimeTypeAdapter.read(JsonReader)"})
  public void testRead_givenOne_whenStringReaderWithEndOfInputSkipOne() throws IOException {
    // Arrange
    SqlTimeTypeAdapter sqlTimeTypeAdapter = new SqlTimeTypeAdapter();

    StringReader in = new StringReader("End of input");
    in.skip(1L);

    JsonReader in2 = new JsonReader(in);
    in2.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> sqlTimeTypeAdapter.read(in2));
  }

  /**
   * Test {@link SqlTimeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code STRICT}.
   *   <li>When {@link JsonReader#JsonReader(Reader)} with in is {@link
   *       StringReader#StringReader(String)} Strictness is {@code STRICT}.
   * </ul>
   *
   * <p>Method under test: {@link SqlTimeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Time SqlTimeTypeAdapter.read(JsonReader)"})
  public void testRead_givenStrict_whenJsonReaderWithInIsStringReaderStrictnessIsStrict()
      throws IOException {
    // Arrange
    SqlTimeTypeAdapter sqlTimeTypeAdapter = new SqlTimeTypeAdapter();

    JsonReader in =
        new JsonReader(
            new StringReader(
                "\"This is a test string for the java.io.StringReader method. It includes various"
                    + " characters such as numbers 123, special characters @#$%, and spaces.\""));
    in.setStrictness(Strictness.STRICT);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> sqlTimeTypeAdapter.read(in));
  }

  /**
   * Test {@link SqlTimeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonPrimitive#JsonPrimitive(String)} with string is {@code "Test string for
   *       JsonPrimitive method"}.
   * </ul>
   *
   * <p>Method under test: {@link SqlTimeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Time SqlTimeTypeAdapter.read(JsonReader)"})
  public void testRead_whenJsonPrimitiveWithStringIsTestStringForJsonPrimitiveMethod()
      throws IOException {
    // Arrange
    SqlTimeTypeAdapter sqlTimeTypeAdapter = new SqlTimeTypeAdapter();

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class,
        () ->
            sqlTimeTypeAdapter.read(
                new JsonTreeReader(new JsonPrimitive("\"Test string for JsonPrimitive method\""))));
  }

  /**
   * Test {@link SqlTimeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SqlTimeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Time SqlTimeTypeAdapter.read(JsonReader)"})
  public void testRead_whenJsonTreeReaderWithElementIsInstance_thenReturnNull() throws IOException {
    // Arrange
    SqlTimeTypeAdapter sqlTimeTypeAdapter = new SqlTimeTypeAdapter();
    JsonTreeReader in = new JsonTreeReader(JsonNull.INSTANCE);

    // Act
    Time actualReadResult = sqlTimeTypeAdapter.read(in);

    // Assert
    assertNull(actualReadResult);
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link SqlTimeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link SqlTimeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Time SqlTimeTypeAdapter.read(JsonReader)"})
  public void testRead_whenStringReaderWith42_thenThrowJsonSyntaxException() throws IOException {
    // Arrange
    SqlTimeTypeAdapter sqlTimeTypeAdapter = new SqlTimeTypeAdapter();

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class,
        () -> sqlTimeTypeAdapter.read(new JsonReader(new StringReader("42"))));
  }

  /**
   * Test {@link SqlTimeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with a string.
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link SqlTimeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Time SqlTimeTypeAdapter.read(JsonReader)"})
  public void testRead_whenStringReaderWithAString_thenThrowJsonSyntaxException()
      throws IOException {
    // Arrange
    SqlTimeTypeAdapter sqlTimeTypeAdapter = new SqlTimeTypeAdapter();

    // Act and Assert
    assertThrows(
        JsonSyntaxException.class,
        () ->
            sqlTimeTypeAdapter.read(
                new JsonReader(
                    new StringReader(
                        "\"This is a test string for the java.io.StringReader method. It includes"
                            + " various characters such as numbers 123, special characters @#$%,"
                            + " and spaces.\""))));
  }

  /**
   * Test {@link SqlTimeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code End of input}.
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link SqlTimeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Time SqlTimeTypeAdapter.read(JsonReader)"})
  public void testRead_whenStringReaderWithEndOfInput_thenThrowJsonSyntaxException()
      throws IOException {
    // Arrange
    SqlTimeTypeAdapter sqlTimeTypeAdapter = new SqlTimeTypeAdapter();

    JsonReader in = new JsonReader(new StringReader("End of input"));
    in.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> sqlTimeTypeAdapter.read(in));
  }

  /**
   * Test {@link SqlTimeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code Failed parsing '}.
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link SqlTimeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Time SqlTimeTypeAdapter.read(JsonReader)"})
  public void testRead_whenStringReaderWithFailedParsing_thenThrowJsonSyntaxException()
      throws IOException {
    // Arrange
    SqlTimeTypeAdapter sqlTimeTypeAdapter = new SqlTimeTypeAdapter();

    JsonReader in = new JsonReader(new StringReader("Failed parsing '"));
    in.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertThrows(JsonSyntaxException.class, () -> sqlTimeTypeAdapter.read(in));
  }

  /**
   * Test {@link SqlTimeTypeAdapter#write(JsonWriter, Time)} with {@code JsonWriter}, {@code Time}.
   *
   * <ul>
   *   <li>When {@link JsonTreeWriter} (default constructor).
   *   <li>Then {@link JsonTreeWriter} (default constructor) {@link JsonNull}.
   * </ul>
   *
   * <p>Method under test: {@link SqlTimeTypeAdapter#write(JsonWriter, Time)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SqlTimeTypeAdapter.write(JsonWriter, Time)"})
  public void testWriteWithJsonWriterTime_whenJsonTreeWriter_thenJsonTreeWriterJsonNull()
      throws IOException {
    // Arrange
    SqlTimeTypeAdapter sqlTimeTypeAdapter = new SqlTimeTypeAdapter();
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    sqlTimeTypeAdapter.write(out, null);

    // Assert that nothing has changed
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonNull);
    assertFalse(getResult.isJsonPrimitive());
    assertTrue(getResult.isJsonNull());
  }

  /**
   * Test {@link SqlTimeTypeAdapter#write(JsonWriter, Time)} with {@code JsonWriter}, {@code Time}.
   *
   * <ul>
   *   <li>When {@link JsonTreeWriter} (default constructor).
   *   <li>Then {@link JsonTreeWriter} (default constructor) {@link JsonPrimitive}.
   * </ul>
   *
   * <p>Method under test: {@link SqlTimeTypeAdapter#write(JsonWriter, Time)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SqlTimeTypeAdapter.write(JsonWriter, Time)"})
  public void testWriteWithJsonWriterTime_whenJsonTreeWriter_thenJsonTreeWriterJsonPrimitive()
      throws IOException {
    // Arrange
    SqlTimeTypeAdapter sqlTimeTypeAdapter = new SqlTimeTypeAdapter();
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    sqlTimeTypeAdapter.write(out, new Time(10L));

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
}
