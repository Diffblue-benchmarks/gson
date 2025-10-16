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
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.Strictness;
import com.google.gson.internal.bind.JsonTreeWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
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

    // Act
    jsonWriter.setFormattingStyle(FormattingStyle.COMPACT);

    // Assert that nothing has changed
    assertSame(FormattingStyle.COMPACT, jsonWriter.getFormattingStyle());
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

    // Act
    jsonWriter.setFormattingStyle(FormattingStyle.PRETTY);

    // Assert
    assertSame(FormattingStyle.PRETTY, jsonWriter.getFormattingStyle());
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
    assertSame(FormattingStyle.COMPACT, actualFormattingStyle);
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

    // Act
    JsonWriter actualBeginArrayResult = jsonWriter.beginArray();

    // Assert
    assertSame(jsonWriter, actualBeginArrayResult);
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

    // Act
    JsonWriter actualBeginObjectResult = jsonWriter.beginObject();

    // Assert
    assertSame(jsonWriter, actualBeginObjectResult);
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
  public void testValueWithBoolean_thenReturnJsonWriterWithOutIsStringWriter() throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act
    JsonWriter actualValueResult = jsonWriter.value(true);

    // Assert
    assertSame(jsonWriter, actualValueResult);
  }

  /**
   * Test {@link JsonWriter#value(Boolean)} with {@code Boolean}.
   *
   * <ul>
   *   <li>When {@code false}.
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
  public void testValueWithBoolean_whenFalse_thenReturnJsonWriterWithOutIsStringWriter()
      throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act
    JsonWriter actualValueResult = jsonWriter.value((Boolean) false);

    // Assert
    assertSame(jsonWriter, actualValueResult);
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
  public void testValueWithBoolean_whenFalse_thenReturnJsonWriterWithOutIsStringWriter2()
      throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act
    JsonWriter actualValueResult = jsonWriter.value(false);

    // Assert
    assertSame(jsonWriter, actualValueResult);
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

    // Act
    JsonWriter actualValueResult = jsonWriter.value((Boolean) null);

    // Assert
    assertSame(jsonWriter, actualValueResult);
  }

  /**
   * Test {@link JsonWriter#value(Boolean)} with {@code Boolean}.
   *
   * <ul>
   *   <li>When {@code true}.
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
  public void testValueWithBoolean_whenTrue_thenReturnJsonWriterWithOutIsStringWriter()
      throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act
    JsonWriter actualValueResult = jsonWriter.value((Boolean) true);

    // Assert
    assertSame(jsonWriter, actualValueResult);
  }

  /**
   * Test {@link JsonWriter#value(double)} with {@code double}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeWriter} (default constructor) Strictness is {@code LENIENT}.
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#value(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonWriter.value(double)"})
  public void testValueWithDouble_givenJsonTreeWriterStrictnessIsLenient() throws IOException {
    // Arrange
    JsonTreeWriter jsonTreeWriter = new JsonTreeWriter();
    jsonTreeWriter.setStrictness(Strictness.LENIENT);

    // Act
    JsonWriter actualValueResult = jsonTreeWriter.value(10.0d);

    // Assert
    assertSame(jsonTreeWriter, actualValueResult);
  }

  /**
   * Test {@link JsonWriter#value(double)} with {@code double}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeWriter} (default constructor).
   *   <li>When ten.
   *   <li>Then return {@link JsonTreeWriter} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#value(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonWriter.value(double)"})
  public void testValueWithDouble_givenJsonTreeWriter_whenTen_thenReturnJsonTreeWriter()
      throws IOException {
    // Arrange
    JsonTreeWriter jsonTreeWriter = new JsonTreeWriter();

    // Act
    JsonWriter actualValueResult = jsonTreeWriter.value(10.0d);

    // Assert
    assertSame(jsonTreeWriter, actualValueResult);
  }

  /**
   * Test {@link JsonWriter#value(double)} with {@code double}.
   *
   * <ul>
   *   <li>Given {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()} Strictness is {@code LENIENT}.
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#value(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonWriter.value(double)"})
  public void testValueWithDouble_givenJsonWriterWithOutIsStringWriterStrictnessIsLenient()
      throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());
    jsonWriter.setStrictness(Strictness.LENIENT);

    // Act
    JsonWriter actualValueResult = jsonWriter.value(10.0d);

    // Assert
    assertSame(jsonWriter, actualValueResult);
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

    // Act
    JsonWriter actualValueResult = jsonWriter.value(10.0d);

    // Assert
    assertSame(jsonWriter, actualValueResult);
  }

  /**
   * Test {@link JsonWriter#value(double)} with {@code double}.
   *
   * <ul>
   *   <li>When {@link Double#NaN}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#value(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonWriter.value(double)"})
  public void testValueWithDouble_whenNaN_thenThrowIllegalArgumentException() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> new JsonWriter(new StringWriter()).value(Double.NaN));
  }

  /**
   * Test {@link JsonWriter#value(float)} with {@code float}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeWriter} (default constructor) Strictness is {@code LENIENT}.
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#value(float)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonWriter.value(float)"})
  public void testValueWithFloat_givenJsonTreeWriterStrictnessIsLenient() throws IOException {
    // Arrange
    JsonTreeWriter jsonTreeWriter = new JsonTreeWriter();
    jsonTreeWriter.setStrictness(Strictness.LENIENT);

    // Act
    JsonWriter actualValueResult = jsonTreeWriter.value(10.0f);

    // Assert
    assertSame(jsonTreeWriter, actualValueResult);
  }

  /**
   * Test {@link JsonWriter#value(float)} with {@code float}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeWriter} (default constructor).
   *   <li>When ten.
   *   <li>Then return {@link JsonTreeWriter} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#value(float)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonWriter.value(float)"})
  public void testValueWithFloat_givenJsonTreeWriter_whenTen_thenReturnJsonTreeWriter()
      throws IOException {
    // Arrange
    JsonTreeWriter jsonTreeWriter = new JsonTreeWriter();

    // Act
    JsonWriter actualValueResult = jsonTreeWriter.value(10.0f);

    // Assert
    assertSame(jsonTreeWriter, actualValueResult);
  }

  /**
   * Test {@link JsonWriter#value(float)} with {@code float}.
   *
   * <ul>
   *   <li>Given {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()} Strictness is {@code LENIENT}.
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#value(float)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonWriter.value(float)"})
  public void testValueWithFloat_givenJsonWriterWithOutIsStringWriterStrictnessIsLenient()
      throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());
    jsonWriter.setStrictness(Strictness.LENIENT);

    // Act
    JsonWriter actualValueResult = jsonWriter.value(10.0f);

    // Assert
    assertSame(jsonWriter, actualValueResult);
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

    // Act
    JsonWriter actualValueResult = jsonWriter.value(10.0f);

    // Assert
    assertSame(jsonWriter, actualValueResult);
  }

  /**
   * Test {@link JsonWriter#value(float)} with {@code float}.
   *
   * <ul>
   *   <li>When {@link Float#NaN}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#value(float)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonWriter.value(float)"})
  public void testValueWithFloat_whenNaN_thenThrowIllegalArgumentException() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> new JsonWriter(new StringWriter()).value(Float.NaN));
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

    // Act
    JsonWriter actualValueResult = jsonWriter.value(42L);

    // Assert
    assertSame(jsonWriter, actualValueResult);
  }

  /**
   * Test {@link JsonWriter#value(Number)} with {@code Number}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeWriter} (default constructor).
   *   <li>Then {@link JsonTreeWriter#get()} return {@link JsonPrimitive}.
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#value(Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonWriter.value(Number)"})
  public void testValueWithNumber_givenJsonTreeWriter_thenGetReturnJsonPrimitive()
      throws IOException {
    // Arrange
    Integer value = Integer.valueOf(1);

    // Act
    JsonWriter actualValueResult = new JsonTreeWriter().value(value);

    // Assert
    JsonElement getResult = ((JsonTreeWriter) actualValueResult).get();
    assertTrue(getResult instanceof JsonPrimitive);
    assertTrue(actualValueResult instanceof JsonTreeWriter);
    assertEquals("1", getResult.getAsString());
    assertEquals('1', getResult.getAsCharacter());
    assertEquals(1, getResult.getAsInt());
    assertEquals(1.0d, getResult.getAsDouble(), 0.0);
    assertEquals(1.0f, getResult.getAsFloat(), 0.0f);
    assertEquals(1L, getResult.getAsLong());
    assertEquals((byte) 1, getResult.getAsByte());
    assertEquals((short) 1, getResult.getAsShort());
    assertFalse(getResult.getAsBoolean());
    assertFalse(getResult.isJsonArray());
    assertFalse(getResult.isJsonNull());
    assertFalse(getResult.isJsonObject());
    assertFalse(((JsonPrimitive) getResult).isBoolean());
    assertFalse(((JsonPrimitive) getResult).isString());
    assertTrue(getResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) getResult).isNumber());
    assertEquals(new BigDecimal("1"), getResult.getAsBigDecimal());
    assertSame(value, getResult.getAsNumber());
    assertSame(getResult, getResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonWriter#value(Number)} with {@code Number}.
   *
   * <ul>
   *   <li>Given {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()} Strictness is {@code LENIENT}.
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#value(Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonWriter.value(Number)"})
  public void testValueWithNumber_givenJsonWriterWithOutIsStringWriterStrictnessIsLenient()
      throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());
    jsonWriter.setStrictness(Strictness.LENIENT);

    // Act
    JsonWriter actualValueResult = jsonWriter.value((Number) Double.NaN);

    // Assert
    assertSame(jsonWriter, actualValueResult);
  }

  /**
   * Test {@link JsonWriter#value(Number)} with {@code Number}.
   *
   * <ul>
   *   <li>Then return {@link JsonTreeWriter} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#value(Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonWriter.value(Number)"})
  public void testValueWithNumber_thenReturnJsonTreeWriter() throws IOException {
    // Arrange
    JsonTreeWriter jsonTreeWriter = new JsonTreeWriter();
    jsonTreeWriter.setStrictness(Strictness.LENIENT);

    // Act
    JsonWriter actualValueResult = jsonTreeWriter.value(Integer.valueOf(1));

    // Assert
    assertSame(jsonTreeWriter, actualValueResult);
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

    // Act
    JsonWriter actualValueResult = jsonWriter.value((byte) 'A');

    // Assert
    assertSame(jsonWriter, actualValueResult);
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

    // Act
    JsonWriter actualValueResult = jsonWriter.value((Number) 42L);

    // Assert
    assertSame(jsonWriter, actualValueResult);
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

    // Act
    JsonWriter actualValueResult = jsonWriter.value(UnsignedInteger.fromIntBits(1));

    // Assert
    assertSame(jsonWriter, actualValueResult);
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

    // Act
    JsonWriter actualValueResult = jsonWriter.value((Number) null);

    // Assert
    assertSame(jsonWriter, actualValueResult);
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

    // Act
    JsonWriter actualValueResult = jsonWriter.value((Number) 10.0d);

    // Assert
    assertSame(jsonWriter, actualValueResult);
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

    // Act
    JsonWriter actualValueResult = jsonWriter.value((Number) 10.0f);

    // Assert
    assertSame(jsonWriter, actualValueResult);
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

    // Act
    JsonWriter actualValueResult = jsonWriter.value((short) 291);

    // Assert
    assertSame(jsonWriter, actualValueResult);
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

    // Act
    JsonWriter actualValueResult = jsonWriter.value(Integer.valueOf(1));

    // Assert
    assertSame(jsonWriter, actualValueResult);
  }

  /**
   * Test {@link JsonWriter#value(String)} with {@code String}.
   *
   * <ul>
   *   <li>Given {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()} HtmlSafe is {@code true}.
   *   <li>When {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#value(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonWriter.value(String)"})
  public void testValueWithString_givenJsonWriterWithOutIsStringWriterHtmlSafeIsTrue_when42()
      throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());
    jsonWriter.setHtmlSafe(true);

    // Act
    JsonWriter actualValueResult = jsonWriter.value("42");

    // Assert
    assertSame(jsonWriter, actualValueResult);
  }

  /**
   * Test {@link JsonWriter#value(String)} with {@code String}.
   *
   * <ul>
   *   <li>Given {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()}.
   *   <li>When {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#value(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonWriter.value(String)"})
  public void testValueWithString_givenJsonWriterWithOutIsStringWriter_when42() throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act
    JsonWriter actualValueResult = jsonWriter.value("42");

    // Assert
    assertSame(jsonWriter, actualValueResult);
  }

  /**
   * Test {@link JsonWriter#value(String)} with {@code String}.
   *
   * <ul>
   *   <li>Given {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()}.
   *   <li>When empty string.
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#value(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonWriter.value(String)"})
  public void testValueWithString_givenJsonWriterWithOutIsStringWriter_whenEmptyString()
      throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act
    JsonWriter actualValueResult = jsonWriter.value("");

    // Assert
    assertSame(jsonWriter, actualValueResult);
  }

  /**
   * Test {@link JsonWriter#value(String)} with {@code String}.
   *
   * <ul>
   *   <li>Given {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()}.
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#value(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonWriter.value(String)"})
  public void testValueWithString_givenJsonWriterWithOutIsStringWriter_whenNull()
      throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act
    JsonWriter actualValueResult = jsonWriter.value((String) null);

    // Assert
    assertSame(jsonWriter, actualValueResult);
  }

  /**
   * Test {@link JsonWriter#value(String)} with {@code String}.
   *
   * <ul>
   *   <li>Given {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()}.
   *   <li>When {@code \u0027}.
   * </ul>
   *
   * <p>Method under test: {@link JsonWriter#value(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonWriter.value(String)"})
  public void testValueWithString_givenJsonWriterWithOutIsStringWriter_whenU0027()
      throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act
    JsonWriter actualValueResult = jsonWriter.value("\\u0027");

    // Assert
    assertSame(jsonWriter, actualValueResult);
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

    // Act
    JsonWriter actualNullValueResult = jsonWriter.nullValue();

    // Assert
    assertSame(jsonWriter, actualNullValueResult);
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

    // Act
    JsonWriter actualJsonValueResult = jsonWriter.jsonValue("42");

    // Assert
    assertSame(jsonWriter, actualJsonValueResult);
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
  public void testJsonValue_thenReturnJsonWriterWithOutIsStringWriter2() throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act
    JsonWriter actualJsonValueResult = jsonWriter.jsonValue(null);

    // Assert
    assertSame(jsonWriter, actualJsonValueResult);
  }
}
