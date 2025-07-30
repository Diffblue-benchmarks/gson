package com.google.gson.stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.common.primitives.UnsignedInteger;
import com.google.gson.FormattingStyle;
import com.google.gson.Strictness;
import com.google.gson.internal.bind.JsonTreeWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JsonWriterDiffblueTest {
  /**
   * Test {@link JsonWriter#JsonWriter(Writer)}.
   *
   * <ul>
   *   <li>When {@link StringWriter#StringWriter()}.
   *   <li>Then return FormattingStyle Indent is empty string.
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#JsonWriter(Writer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonWriter.<init>(Writer)"})
  public void testNewJsonWriter_whenStringWriter_thenReturnFormattingStyleIndentIsEmptyString() {
    // Arrange and Act
    JsonWriter actualJsonWriter = new JsonWriter(new StringWriter());

    // Assert
    FormattingStyle formattingStyle = actualJsonWriter.getFormattingStyle();
    assertEquals("", formattingStyle.getIndent());
    assertEquals("", formattingStyle.getNewline());
    assertEquals(Strictness.LEGACY_STRICT, actualJsonWriter.getStrictness());
    assertFalse(actualJsonWriter.isHtmlSafe());
    assertFalse(actualJsonWriter.isLenient());
    assertTrue(actualJsonWriter.getSerializeNulls());
  }

  /**
   * Test {@link JsonWriter#setIndent(String)}.
   *
   * <p>Method under test: {@link JsonWriter#setIndent(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonWriter.setIndent(String)"})
  public void testSetIndent() {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act
    jsonWriter.setIndent("");

    // Assert that nothing has changed
    FormattingStyle formattingStyle = jsonWriter.getFormattingStyle();
    assertEquals("", formattingStyle.getIndent());
    assertEquals("", formattingStyle.getNewline());
  }

  /**
   * Test {@link JsonWriter#setIndent(String)}.
   *
   * <ul>
   *   <li>Then {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()} FormattingStyle Indent is space.
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#setIndent(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonWriter.setIndent(String)"})
  public void testSetIndent_thenJsonWriterWithOutIsStringWriterFormattingStyleIndentIsSpace() {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act
    jsonWriter.setIndent(" ");

    // Assert
    FormattingStyle formattingStyle = jsonWriter.getFormattingStyle();
    assertEquals(" ", formattingStyle.getIndent());
    assertEquals("\n", formattingStyle.getNewline());
  }

  /**
   * Test {@link JsonWriter#setFormattingStyle(FormattingStyle)}.
   *
   * <p>Method under test: {@link JsonWriter#setFormattingStyle(FormattingStyle)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonWriter.setFormattingStyle(FormattingStyle)"})
  public void testSetFormattingStyle() {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());
    FormattingStyle formattingStyle = FormattingStyle.COMPACT;

    // Act
    jsonWriter.setFormattingStyle(formattingStyle);

    // Assert that nothing has changed
    FormattingStyle expectedFormattingStyle = formattingStyle.COMPACT;
    assertSame(expectedFormattingStyle, jsonWriter.getFormattingStyle());
  }

  /**
   * Test {@link JsonWriter#setFormattingStyle(FormattingStyle)}.
   *
   * <p>Method under test: {@link JsonWriter#setFormattingStyle(FormattingStyle)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonWriter.setFormattingStyle(FormattingStyle)"})
  public void testSetFormattingStyle2() {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());
    FormattingStyle formattingStyle = FormattingStyle.PRETTY;

    // Act
    jsonWriter.setFormattingStyle(formattingStyle);

    // Assert
    FormattingStyle expectedFormattingStyle = formattingStyle.PRETTY;
    assertSame(expectedFormattingStyle, jsonWriter.getFormattingStyle());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link JsonWriter#setHtmlSafe(boolean)}
   *   <li>{@link JsonWriter#setSerializeNulls(boolean)}
   *   <li>{@link JsonWriter#setStrictness(Strictness)}
   *   <li>{@link JsonWriter#getFormattingStyle()}
   *   <li>{@link JsonWriter#getSerializeNulls()}
   *   <li>{@link JsonWriter#getStrictness()}
   *   <li>{@link JsonWriter#isHtmlSafe()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "FormattingStyle JsonWriter.getFormattingStyle()",
    "boolean JsonWriter.getSerializeNulls()",
    "Strictness JsonWriter.getStrictness()",
    "boolean JsonWriter.isHtmlSafe()",
    "void JsonWriter.setHtmlSafe(boolean)",
    "void JsonWriter.setSerializeNulls(boolean)",
    "void JsonWriter.setStrictness(Strictness)"
  })
  public void testGettersAndSetters() {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act
    jsonWriter.setHtmlSafe(true);
    jsonWriter.setSerializeNulls(true);
    jsonWriter.setStrictness(Strictness.LENIENT);
    FormattingStyle actualFormattingStyle = jsonWriter.getFormattingStyle();
    boolean actualSerializeNulls = jsonWriter.getSerializeNulls();
    Strictness actualStrictness = jsonWriter.getStrictness();

    // Assert
    assertEquals(Strictness.LENIENT, actualStrictness);
    assertTrue(actualSerializeNulls);
    assertTrue(jsonWriter.isHtmlSafe());
    assertSame(actualFormattingStyle.COMPACT, actualFormattingStyle);
  }

  /**
   * Test {@link JsonWriter#setLenient(boolean)}.
   *
   * <ul>
   *   <li>Then {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()} Strictness is {@code LEGACY_STRICT}.
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#setLenient(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonWriter.setLenient(boolean)"})
  public void testSetLenient_thenJsonWriterWithOutIsStringWriterStrictnessIsLegacyStrict() {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act
    jsonWriter.setLenient(false);

    // Assert that nothing has changed
    assertEquals(Strictness.LEGACY_STRICT, jsonWriter.getStrictness());
    assertFalse(jsonWriter.isLenient());
  }

  /**
   * Test {@link JsonWriter#setLenient(boolean)}.
   *
   * <ul>
   *   <li>When {@code true}.
   *   <li>Then {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()} Strictness is {@code LENIENT}.
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#setLenient(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonWriter.setLenient(boolean)"})
  public void testSetLenient_whenTrue_thenJsonWriterWithOutIsStringWriterStrictnessIsLenient() {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act
    jsonWriter.setLenient(true);

    // Assert
    assertEquals(Strictness.LENIENT, jsonWriter.getStrictness());
    assertTrue(jsonWriter.isLenient());
  }

  /**
   * Test {@link JsonWriter#isLenient()}.
   *
   * <ul>
   *   <li>Given {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#isLenient()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonWriter.isLenient()"})
  public void testIsLenient_givenJsonWriterWithOutIsStringWriter_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new JsonWriter(new StringWriter()).isLenient());
  }

  /**
   * Test {@link JsonWriter#isLenient()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#isLenient()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonWriter.isLenient()"})
  public void testIsLenient_thenReturnTrue() {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());
    jsonWriter.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertTrue(jsonWriter.isLenient());
  }

  /**
   * Test {@link JsonWriter#beginArray()}.
   *
   * <ul>
   *   <li>Then return {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()}.
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#beginArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonWriter.beginArray()"})
  public void testBeginArray_thenReturnJsonWriterWithOutIsStringWriter() throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act and Assert
    assertSame(jsonWriter, jsonWriter.beginArray());
  }

  /**
   * Test {@link JsonWriter#endArray()}.
   *
   * <p>Method under test: {@link JsonWriter#endArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonWriter.endArray()"})
  public void testEndArray() throws IOException {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> new JsonWriter(new StringWriter()).endArray());
  }

  /**
   * Test {@link JsonWriter#beginObject()}.
   *
   * <ul>
   *   <li>Then return {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()}.
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#beginObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonWriter.beginObject()"})
  public void testBeginObject_thenReturnJsonWriterWithOutIsStringWriter() throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act and Assert
    assertSame(jsonWriter, jsonWriter.beginObject());
  }

  /**
   * Test {@link JsonWriter#endObject()}.
   *
   * <p>Method under test: {@link JsonWriter#endObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonWriter.endObject()"})
  public void testEndObject() throws IOException {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> new JsonWriter(new StringWriter()).endObject());
  }

  /**
   * Test {@link JsonWriter#name(String)}.
   *
   * <ul>
   *   <li>When {@code Name}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#name(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonWriter.name(String)"})
  public void testName_whenName_thenThrowIllegalStateException() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new JsonWriter(new StringWriter()).name("Name"));
  }

  /**
   * Test {@link JsonWriter#value(Boolean)} with {@code Boolean}.
   *
   * <ul>
   *   <li>Then return {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()}.
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#value(Boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonWriter.value(Boolean)"})
  public void testValueWithBoolean_thenReturnJsonWriterWithOutIsStringWriter() throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act and Assert
    assertSame(jsonWriter, jsonWriter.value((Boolean) true));
  }

  /**
   * Test {@link JsonWriter#value(boolean)} with {@code boolean}.
   *
   * <ul>
   *   <li>Then return {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()}.
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#value(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonWriter.value(boolean)"})
  public void testValueWithBoolean_thenReturnJsonWriterWithOutIsStringWriter2() throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act and Assert
    assertSame(jsonWriter, jsonWriter.value(true));
  }

  /**
   * Test {@link JsonWriter#value(boolean)} with {@code boolean}.
   *
   * <ul>
   *   <li>When {@code false}.
   *   <li>Then return {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()}.
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#value(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonWriter.value(boolean)"})
  public void testValueWithBoolean_whenFalse_thenReturnJsonWriterWithOutIsStringWriter()
      throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act and Assert
    assertSame(jsonWriter, jsonWriter.value(false));
  }

  /**
   * Test {@link JsonWriter#value(Boolean)} with {@code Boolean}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()}.
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#value(Boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonWriter.value(Boolean)"})
  public void testValueWithBoolean_whenNull_thenReturnJsonWriterWithOutIsStringWriter()
      throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act and Assert
    assertSame(jsonWriter, jsonWriter.value((Boolean) null));
  }

  /**
   * Test {@link JsonWriter#value(double)} with {@code double}.
   *
   * <ul>
   *   <li>Then return {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()}.
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#value(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonWriter.value(double)"})
  public void testValueWithDouble_thenReturnJsonWriterWithOutIsStringWriter() throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act and Assert
    assertSame(jsonWriter, jsonWriter.value(10.0d));
  }

  /**
   * Test {@link JsonWriter#value(float)} with {@code float}.
   *
   * <ul>
   *   <li>Then return {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()}.
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#value(float)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonWriter.value(float)"})
  public void testValueWithFloat_thenReturnJsonWriterWithOutIsStringWriter() throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act and Assert
    assertSame(jsonWriter, jsonWriter.value(10.0f));
  }

  /**
   * Test {@link JsonWriter#value(long)} with {@code long}.
   *
   * <ul>
   *   <li>Then return {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()}.
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#value(long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonWriter.value(long)"})
  public void testValueWithLong_thenReturnJsonWriterWithOutIsStringWriter() throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act and Assert
    assertSame(jsonWriter, jsonWriter.value(42L));
  }

  /**
   * Test {@link JsonWriter#value(Number)} with {@code Number}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeWriter} (default constructor).
   *   <li>When valueOf one.
   *   <li>Then return {@link JsonTreeWriter} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#value(Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonWriter.value(Number)"})
  public void testValueWithNumber_givenJsonTreeWriter_whenValueOfOne_thenReturnJsonTreeWriter()
      throws IOException {
    // Arrange
    JsonTreeWriter jsonTreeWriter = new JsonTreeWriter();

    // Act and Assert
    assertSame(jsonTreeWriter, jsonTreeWriter.value(Integer.valueOf(1)));
  }

  /**
   * Test {@link JsonWriter#value(Number)} with {@code Number}.
   *
   * <ul>
   *   <li>When {@code A}.
   *   <li>Then return {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()}.
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#value(Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonWriter.value(Number)"})
  public void testValueWithNumber_whenA_thenReturnJsonWriterWithOutIsStringWriter()
      throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act and Assert
    assertSame(jsonWriter, jsonWriter.value((byte) 'A'));
  }

  /**
   * Test {@link JsonWriter#value(Number)} with {@code Number}.
   *
   * <ul>
   *   <li>When forty-two.
   *   <li>Then return {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()}.
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#value(Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonWriter.value(Number)"})
  public void testValueWithNumber_whenFortyTwo_thenReturnJsonWriterWithOutIsStringWriter()
      throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act and Assert
    assertSame(jsonWriter, jsonWriter.value((Number) 42L));
  }

  /**
   * Test {@link JsonWriter#value(Number)} with {@code Number}.
   *
   * <ul>
   *   <li>When fromIntBits one.
   *   <li>Then return {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()}.
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#value(Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonWriter.value(Number)"})
  public void testValueWithNumber_whenFromIntBitsOne_thenReturnJsonWriterWithOutIsStringWriter()
      throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act and Assert
    assertSame(jsonWriter, jsonWriter.value(UnsignedInteger.fromIntBits(1)));
  }

  /**
   * Test {@link JsonWriter#value(Number)} with {@code Number}.
   *
   * <ul>
   *   <li>When {@link Double#NaN}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#value(Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonWriter.value(Number)"})
  public void testValueWithNumber_whenNaN_thenThrowIllegalArgumentException() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new JsonWriter(new StringWriter()).value((Number) Double.NaN));
  }

  /**
   * Test {@link JsonWriter#value(Number)} with {@code Number}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()}.
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#value(Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonWriter.value(Number)"})
  public void testValueWithNumber_whenNull_thenReturnJsonWriterWithOutIsStringWriter()
      throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act and Assert
    assertSame(jsonWriter, jsonWriter.value((Number) null));
  }

  /**
   * Test {@link JsonWriter#value(Number)} with {@code Number}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()}.
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#value(Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonWriter.value(Number)"})
  public void testValueWithNumber_whenTen_thenReturnJsonWriterWithOutIsStringWriter()
      throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act and Assert
    assertSame(jsonWriter, jsonWriter.value((Number) 10.0d));
  }

  /**
   * Test {@link JsonWriter#value(Number)} with {@code Number}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()}.
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#value(Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonWriter.value(Number)"})
  public void testValueWithNumber_whenTen_thenReturnJsonWriterWithOutIsStringWriter2()
      throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act and Assert
    assertSame(jsonWriter, jsonWriter.value((Number) 10.0f));
  }

  /**
   * Test {@link JsonWriter#value(Number)} with {@code Number}.
   *
   * <ul>
   *   <li>When two hundred ninety-one.
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#value(Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonWriter.value(Number)"})
  public void testValueWithNumber_whenTwoHundredNinetyOne() throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act and Assert
    assertSame(jsonWriter, jsonWriter.value((short) 291));
  }

  /**
   * Test {@link JsonWriter#value(Number)} with {@code Number}.
   *
   * <ul>
   *   <li>When valueOf one.
   *   <li>Then return {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()}.
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#value(Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonWriter.value(Number)"})
  public void testValueWithNumber_whenValueOfOne_thenReturnJsonWriterWithOutIsStringWriter()
      throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act and Assert
    assertSame(jsonWriter, jsonWriter.value(Integer.valueOf(1)));
  }

  /**
   * Test {@link JsonWriter#value(String)} with {@code String}.
   *
   * <ul>
   *   <li>Then return {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()}.
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#value(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonWriter.value(String)"})
  public void testValueWithString_thenReturnJsonWriterWithOutIsStringWriter() throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act and Assert
    assertSame(jsonWriter, jsonWriter.value("42"));
  }

  /**
   * Test {@link JsonWriter#value(String)} with {@code String}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()}.
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#value(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonWriter.value(String)"})
  public void testValueWithString_whenNull_thenReturnJsonWriterWithOutIsStringWriter()
      throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act and Assert
    assertSame(jsonWriter, jsonWriter.value((String) null));
  }

  /**
   * Test {@link JsonWriter#nullValue()}.
   *
   * <ul>
   *   <li>Then return {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()}.
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#nullValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonWriter.nullValue()"})
  public void testNullValue_thenReturnJsonWriterWithOutIsStringWriter() throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act and Assert
    assertSame(jsonWriter, jsonWriter.nullValue());
  }

  /**
   * Test {@link JsonWriter#jsonValue(String)}.
   *
   * <ul>
   *   <li>Then return {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()}.
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#jsonValue(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonWriter.jsonValue(String)"})
  public void testJsonValue_thenReturnJsonWriterWithOutIsStringWriter() throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act and Assert
    assertSame(jsonWriter, jsonWriter.jsonValue("42"));
  }

  /**
   * Test {@link JsonWriter#jsonValue(String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()}.
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#jsonValue(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonWriter.jsonValue(String)"})
  public void testJsonValue_whenNull_thenReturnJsonWriterWithOutIsStringWriter()
      throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act and Assert
    assertSame(jsonWriter, jsonWriter.jsonValue(null));
  }

  /**
   * Test {@link JsonWriter#close()}.
   *
   * <p>Method under test: {@link JsonWriter#close()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonWriter.close()"})
  public void testClose() throws IOException {
    // Arrange, Act and Assert
    assertThrows(IOException.class, () -> new JsonWriter(new StringWriter()).close());
  }
}
