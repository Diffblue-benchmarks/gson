package com.google.gson.internal.bind;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.JsonNull;
import com.google.gson.JsonPrimitive;
import com.google.gson.Strictness;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.awt.Component.BaselineResizeBehavior;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class EnumTypeAdapterDiffblueTest {
  /**
   * Test {@link EnumTypeAdapter#read(JsonReader)}.
   *
   * <p>Method under test: {@link EnumTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Enum EnumTypeAdapter.read(JsonReader)"})
  public void testRead() throws IOException {
    // Arrange
    Class<BaselineResizeBehavior> classOfT = BaselineResizeBehavior.class;
    EnumTypeAdapter<BaselineResizeBehavior> enumTypeAdapter = new EnumTypeAdapter<>(classOfT);

    JsonReader in =
        new JsonReader(
            new StringReader(
                "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed JSON"));
    in.setStrictness(Strictness.LENIENT);

    // Act
    BaselineResizeBehavior actualReadResult = enumTypeAdapter.read(in);

    // Assert
    assertNull(actualReadResult);
  }

  /**
   * Test {@link EnumTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link JsonReader#JsonReader(Reader)} with in is {@link
   *       CharArrayReader#CharArrayReader(char[])} Strictness is {@code LENIENT}.
   * </ul>
   *
   * <p>Method under test: {@link EnumTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Enum EnumTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_whenJsonReaderWithInIsCharArrayReaderStrictnessIsLenient()
      throws IOException {
    // Arrange
    Class<BaselineResizeBehavior> classOfT = BaselineResizeBehavior.class;
    EnumTypeAdapter<BaselineResizeBehavior> enumTypeAdapter = new EnumTypeAdapter<>(classOfT);
    CharArrayReader in = new CharArrayReader("\u0001\u0006\u0001\u0006".toCharArray());

    JsonReader in2 = new JsonReader(in);
    in2.setStrictness(Strictness.LENIENT);

    // Act
    BaselineResizeBehavior actualReadResult = enumTypeAdapter.read(in2);

    // Assert
    assertNull(actualReadResult);
  }

  /**
   * Test {@link EnumTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code at line}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link EnumTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Enum EnumTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_whenStringReaderWithAtLine_thenReturnNull() throws IOException {
    // Arrange
    Class<BaselineResizeBehavior> classOfT = BaselineResizeBehavior.class;
    EnumTypeAdapter<BaselineResizeBehavior> enumTypeAdapter = new EnumTypeAdapter<>(classOfT);

    JsonReader in = new JsonReader(new StringReader(" at line "));
    in.setStrictness(Strictness.LENIENT);

    // Act
    BaselineResizeBehavior actualReadResult = enumTypeAdapter.read(in);

    // Assert
    assertNull(actualReadResult);
  }

  /**
   * Test {@link EnumTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code Expected}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link EnumTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Enum EnumTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_whenStringReaderWithExpected_thenReturnNull()
      throws IOException {
    // Arrange
    Class<BaselineResizeBehavior> classOfT = BaselineResizeBehavior.class;
    EnumTypeAdapter<BaselineResizeBehavior> enumTypeAdapter = new EnumTypeAdapter<>(classOfT);

    JsonReader in = new JsonReader(new StringReader("Expected "));
    in.setStrictness(Strictness.LENIENT);

    // Act
    BaselineResizeBehavior actualReadResult = enumTypeAdapter.read(in);

    // Assert
    assertNull(actualReadResult);
  }

  /**
   * Test {@link EnumTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link EnumTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Enum EnumTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_whenStringReaderWithFoo_thenReturnNull() throws IOException {
    // Arrange
    Class<BaselineResizeBehavior> classOfT = BaselineResizeBehavior.class;
    EnumTypeAdapter<BaselineResizeBehavior> enumTypeAdapter = new EnumTypeAdapter<>(classOfT);

    JsonReader in = new JsonReader(new StringReader("foo"));
    in.setStrictness(Strictness.LENIENT);

    // Act
    BaselineResizeBehavior actualReadResult = enumTypeAdapter.read(in);

    // Assert
    assertNull(actualReadResult);
  }

  /**
   * Test {@link EnumTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code See}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link EnumTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Enum EnumTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_whenStringReaderWithSee_thenReturnNull() throws IOException {
    // Arrange
    Class<BaselineResizeBehavior> classOfT = BaselineResizeBehavior.class;
    EnumTypeAdapter<BaselineResizeBehavior> enumTypeAdapter = new EnumTypeAdapter<>(classOfT);

    JsonReader in = new JsonReader(new StringReader("\nSee "));
    in.setStrictness(Strictness.LENIENT);

    // Act
    BaselineResizeBehavior actualReadResult = enumTypeAdapter.read(in);

    // Assert
    assertNull(actualReadResult);
  }

  /**
   * Test {@link EnumTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Then not {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE} hasNext.
   * </ul>
   *
   * <p>Method under test: {@link EnumTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Enum EnumTypeAdapter.read(JsonReader)"})
  public void testRead_thenNotJsonTreeReaderWithElementIsInstanceHasNext() throws IOException {
    // Arrange
    Class<BaselineResizeBehavior> classOfT = BaselineResizeBehavior.class;
    EnumTypeAdapter<BaselineResizeBehavior> enumTypeAdapter = new EnumTypeAdapter<>(classOfT);
    JsonTreeReader in = new JsonTreeReader(JsonNull.INSTANCE);

    // Act
    BaselineResizeBehavior actualReadResult = enumTypeAdapter.read(in);

    // Assert
    assertNull(actualReadResult);
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link EnumTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Then not {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonPrimitive#JsonPrimitive(String)} hasNext.
   * </ul>
   *
   * <p>Method under test: {@link EnumTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Enum EnumTypeAdapter.read(JsonReader)"})
  public void testRead_thenNotJsonTreeReaderWithElementIsJsonPrimitiveHasNext() throws IOException {
    // Arrange
    Class<BaselineResizeBehavior> classOfT = BaselineResizeBehavior.class;
    EnumTypeAdapter<BaselineResizeBehavior> enumTypeAdapter = new EnumTypeAdapter<>(classOfT);
    JsonTreeReader in = new JsonTreeReader(new JsonPrimitive("String"));

    // Act
    BaselineResizeBehavior actualReadResult = enumTypeAdapter.read(in);

    // Assert
    assertNull(actualReadResult);
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link EnumTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link EnumTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Enum EnumTypeAdapter.read(JsonReader)"})
  public void testRead_whenStringReaderWith42_thenReturnNull() throws IOException {
    // Arrange
    Class<BaselineResizeBehavior> classOfT = BaselineResizeBehavior.class;
    EnumTypeAdapter<BaselineResizeBehavior> enumTypeAdapter = new EnumTypeAdapter<>(classOfT);

    // Act
    BaselineResizeBehavior actualReadResult =
        enumTypeAdapter.read(new JsonReader(new StringReader("42")));

    // Assert
    assertNull(actualReadResult);
  }

  /**
   * Test {@link EnumTypeAdapter#write(JsonWriter, Enum)} with {@code JsonWriter}, {@code
   * BaselineResizeBehavior}.
   *
   * <p>Method under test: {@link EnumTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void EnumTypeAdapter.write(JsonWriter, Enum)"})
  public void testWriteWithJsonWriterBaselineResizeBehavior() throws IOException {
    // Arrange
    Class<BaselineResizeBehavior> classOfT = BaselineResizeBehavior.class;
    EnumTypeAdapter<BaselineResizeBehavior> enumTypeAdapter = new EnumTypeAdapter<>(classOfT);
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    enumTypeAdapter.write(out, BaselineResizeBehavior.CONSTANT_ASCENT);

    // Assert
    assertEquals("\"CONSTANT_ASCENT\"", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link EnumTypeAdapter#write(JsonWriter, Enum)} with {@code JsonWriter}, {@code
   * BaselineResizeBehavior}.
   *
   * <p>Method under test: {@link EnumTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void EnumTypeAdapter.write(JsonWriter, Enum)"})
  public void testWriteWithJsonWriterBaselineResizeBehavior2() throws IOException {
    // Arrange
    Class<BaselineResizeBehavior> classOfT = BaselineResizeBehavior.class;
    EnumTypeAdapter<BaselineResizeBehavior> enumTypeAdapter = new EnumTypeAdapter<>(classOfT);
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    enumTypeAdapter.write(out, null);

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
   * Test {@link EnumTypeAdapter#write(JsonWriter, Enum)} with {@code JsonWriter}, {@code
   * BaselineResizeBehavior}.
   *
   * <p>Method under test: {@link EnumTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void EnumTypeAdapter.write(JsonWriter, Enum)"})
  public void testWriteWithJsonWriterBaselineResizeBehavior3() throws IOException {
    // Arrange
    Class<BaselineResizeBehavior> classOfT = BaselineResizeBehavior.class;
    EnumTypeAdapter<BaselineResizeBehavior> enumTypeAdapter = new EnumTypeAdapter<>(classOfT);
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    enumTypeAdapter.write(out, null);

    // Assert that nothing has changed
    assertArrayEquals(
        new int[] {
          6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link EnumTypeAdapter#write(JsonWriter, Enum)} with {@code JsonWriter}, {@code
   * BaselineResizeBehavior}.
   *
   * <ul>
   *   <li>Given {@code Enum}.
   * </ul>
   *
   * <p>Method under test: {@link EnumTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void EnumTypeAdapter.write(JsonWriter, Enum)"})
  public void testWriteWithJsonWriterBaselineResizeBehavior_givenJavaLangEnum() throws IOException {
    // Arrange
    Class<Enum> classOfT = Enum.class;
    EnumTypeAdapter<BaselineResizeBehavior> enumTypeAdapter = new EnumTypeAdapter<>(classOfT);
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    enumTypeAdapter.write(out, BaselineResizeBehavior.CONSTANT_ASCENT);

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
   * Test {@link EnumTypeAdapter#write(JsonWriter, Enum)} with {@code JsonWriter}, {@code
   * BaselineResizeBehavior}.
   *
   * <ul>
   *   <li>Given {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link EnumTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void EnumTypeAdapter.write(JsonWriter, Enum)"})
  public void testWriteWithJsonWriterBaselineResizeBehavior_givenTrue() throws IOException {
    // Arrange
    Class<BaselineResizeBehavior> classOfT = BaselineResizeBehavior.class;
    EnumTypeAdapter<BaselineResizeBehavior> enumTypeAdapter = new EnumTypeAdapter<>(classOfT);

    JsonWriter out = new JsonWriter(new StringWriter());
    out.setHtmlSafe(true);

    // Act
    enumTypeAdapter.write(out, BaselineResizeBehavior.CONSTANT_ASCENT);

    // Assert
    assertEquals("\"CONSTANT_ASCENT\"", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }
}
