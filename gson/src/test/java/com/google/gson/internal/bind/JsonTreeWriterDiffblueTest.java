package com.google.gson.internal.bind;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.FormattingStyle;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonPrimitive;
import com.google.gson.Strictness;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.math.BigDecimal;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JsonTreeWriterDiffblueTest {
  /**
   * Test new {@link JsonTreeWriter} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link JsonTreeWriter}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonTreeWriter.<init>()"})
  public void testNewJsonTreeWriter() {
    // Arrange and Act
    JsonTreeWriter actualJsonTreeWriter = new JsonTreeWriter();

    // Assert
    JsonElement getResult = actualJsonTreeWriter.get();
    assertTrue(getResult instanceof JsonNull);
    FormattingStyle formattingStyle = actualJsonTreeWriter.getFormattingStyle();
    assertEquals("", formattingStyle.getIndent());
    assertEquals("", formattingStyle.getNewline());
    assertEquals(Strictness.LEGACY_STRICT, actualJsonTreeWriter.getStrictness());
    assertFalse(getResult.isJsonArray());
    assertFalse(getResult.isJsonObject());
    assertFalse(getResult.isJsonPrimitive());
    assertFalse(actualJsonTreeWriter.isHtmlSafe());
    assertFalse(actualJsonTreeWriter.isLenient());
    assertTrue(getResult.isJsonNull());
    assertTrue(actualJsonTreeWriter.getSerializeNulls());
    assertSame(getResult, getResult.getAsJsonNull());
  }

  /**
   * Test {@link JsonTreeWriter#get()}.
   *
   * <p>Method under test: {@link JsonTreeWriter#get()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonTreeWriter.get()"})
  public void testGet() {
    // Arrange and Act
    JsonElement actualGetResult = new JsonTreeWriter().get();

    // Assert
    assertSame(((JsonNull) actualGetResult).INSTANCE, actualGetResult);
  }

  /**
   * Test {@link JsonTreeWriter#beginArray()}.
   *
   * <p>Method under test: {@link JsonTreeWriter#beginArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonTreeWriter.beginArray()"})
  public void testBeginArray() throws IOException {
    // Arrange
    JsonTreeWriter jsonTreeWriter = new JsonTreeWriter();

    // Act and Assert
    assertSame(jsonTreeWriter, jsonTreeWriter.beginArray());
  }

  /**
   * Test {@link JsonTreeWriter#endArray()}.
   *
   * <p>Method under test: {@link JsonTreeWriter#endArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonTreeWriter.endArray()"})
  public void testEndArray() throws IOException {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> new JsonTreeWriter().endArray());
  }

  /**
   * Test {@link JsonTreeWriter#beginObject()}.
   *
   * <p>Method under test: {@link JsonTreeWriter#beginObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonTreeWriter.beginObject()"})
  public void testBeginObject() throws IOException {
    // Arrange
    JsonTreeWriter jsonTreeWriter = new JsonTreeWriter();

    // Act and Assert
    assertSame(jsonTreeWriter, jsonTreeWriter.beginObject());
  }

  /**
   * Test {@link JsonTreeWriter#endObject()}.
   *
   * <p>Method under test: {@link JsonTreeWriter#endObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonTreeWriter.endObject()"})
  public void testEndObject() throws IOException {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> new JsonTreeWriter().endObject());
  }

  /**
   * Test {@link JsonTreeWriter#name(String)}.
   *
   * <ul>
   *   <li>When {@code Name}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeWriter#name(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonTreeWriter.name(String)"})
  public void testName_whenName_thenThrowIllegalStateException() throws IOException {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> new JsonTreeWriter().name("Name"));
  }

  /**
   * Test {@link JsonTreeWriter#value(boolean)} with {@code boolean}.
   *
   * <p>Method under test: {@link JsonTreeWriter#value(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonTreeWriter.value(boolean)"})
  public void testValueWithBoolean() throws IOException {
    // Arrange
    JsonTreeWriter jsonTreeWriter = new JsonTreeWriter();

    // Act and Assert
    assertSame(jsonTreeWriter, jsonTreeWriter.value(true));
  }

  /**
   * Test {@link JsonTreeWriter#value(Boolean)} with {@code Boolean}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@link JsonTreeWriter#get()} return {@link JsonNull}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeWriter#value(Boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonTreeWriter.value(Boolean)"})
  public void testValueWithBoolean_whenNull_thenGetReturnJsonNull() throws IOException {
    // Arrange and Act
    JsonWriter actualValueResult = new JsonTreeWriter().value((Boolean) null);

    // Assert
    JsonElement getResult = ((JsonTreeWriter) actualValueResult).get();
    assertTrue(getResult instanceof JsonNull);
    assertTrue(actualValueResult instanceof JsonTreeWriter);
    assertFalse(getResult.isJsonPrimitive());
    assertTrue(getResult.isJsonNull());
    assertSame(getResult, getResult.getAsJsonNull());
  }

  /**
   * Test {@link JsonTreeWriter#value(Boolean)} with {@code Boolean}.
   *
   * <ul>
   *   <li>When {@code true}.
   *   <li>Then {@link JsonTreeWriter#get()} return {@link JsonPrimitive}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeWriter#value(Boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonTreeWriter.value(Boolean)"})
  public void testValueWithBoolean_whenTrue_thenGetReturnJsonPrimitive() throws IOException {
    // Arrange and Act
    JsonWriter actualValueResult = new JsonTreeWriter().value((Boolean) true);

    // Assert
    JsonElement getResult = ((JsonTreeWriter) actualValueResult).get();
    assertTrue(getResult instanceof JsonPrimitive);
    assertTrue(actualValueResult instanceof JsonTreeWriter);
    assertEquals('t', getResult.getAsCharacter());
    assertFalse(getResult.isJsonNull());
    assertFalse(((JsonPrimitive) getResult).isNumber());
    assertFalse(((JsonPrimitive) getResult).isString());
    assertTrue(getResult.getAsBoolean());
    assertTrue(getResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) getResult).isBoolean());
    String expectedAsString = Boolean.TRUE.toString();
    assertEquals(expectedAsString, getResult.getAsString());
    assertSame(getResult, getResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonTreeWriter#value(double)} with {@code double}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeWriter} (default constructor) Strictness is {@code LENIENT}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeWriter#value(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonTreeWriter.value(double)"})
  public void testValueWithDouble_givenJsonTreeWriterStrictnessIsLenient() throws IOException {
    // Arrange
    JsonTreeWriter jsonTreeWriter = new JsonTreeWriter();
    jsonTreeWriter.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertSame(jsonTreeWriter, jsonTreeWriter.value(10.0d));
  }

  /**
   * Test {@link JsonTreeWriter#value(double)} with {@code double}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeWriter} (default constructor).
   *   <li>When ten.
   *   <li>Then return {@link JsonTreeWriter} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeWriter#value(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonTreeWriter.value(double)"})
  public void testValueWithDouble_givenJsonTreeWriter_whenTen_thenReturnJsonTreeWriter()
      throws IOException {
    // Arrange
    JsonTreeWriter jsonTreeWriter = new JsonTreeWriter();

    // Act and Assert
    assertSame(jsonTreeWriter, jsonTreeWriter.value(10.0d));
  }

  /**
   * Test {@link JsonTreeWriter#value(double)} with {@code double}.
   *
   * <ul>
   *   <li>When {@link Double#NaN}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeWriter#value(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonTreeWriter.value(double)"})
  public void testValueWithDouble_whenNaN_thenThrowIllegalArgumentException() throws IOException {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new JsonTreeWriter().value(Double.NaN));
  }

  /**
   * Test {@link JsonTreeWriter#value(float)} with {@code float}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeWriter} (default constructor) Strictness is {@code LENIENT}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeWriter#value(float)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonTreeWriter.value(float)"})
  public void testValueWithFloat_givenJsonTreeWriterStrictnessIsLenient() throws IOException {
    // Arrange
    JsonTreeWriter jsonTreeWriter = new JsonTreeWriter();
    jsonTreeWriter.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertSame(jsonTreeWriter, jsonTreeWriter.value(10.0f));
  }

  /**
   * Test {@link JsonTreeWriter#value(float)} with {@code float}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeWriter} (default constructor).
   *   <li>When {@link Float#NaN}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeWriter#value(float)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonTreeWriter.value(float)"})
  public void testValueWithFloat_givenJsonTreeWriter_whenNaN_thenThrowIllegalArgumentException()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new JsonTreeWriter().value(Float.NaN));
  }

  /**
   * Test {@link JsonTreeWriter#value(float)} with {@code float}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeWriter} (default constructor).
   *   <li>When ten.
   *   <li>Then return {@link JsonTreeWriter} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeWriter#value(float)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonTreeWriter.value(float)"})
  public void testValueWithFloat_givenJsonTreeWriter_whenTen_thenReturnJsonTreeWriter()
      throws IOException {
    // Arrange
    JsonTreeWriter jsonTreeWriter = new JsonTreeWriter();

    // Act and Assert
    assertSame(jsonTreeWriter, jsonTreeWriter.value(10.0f));
  }

  /**
   * Test {@link JsonTreeWriter#value(long)} with {@code long}.
   *
   * <p>Method under test: {@link JsonTreeWriter#value(long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonTreeWriter.value(long)"})
  public void testValueWithLong() throws IOException {
    // Arrange
    JsonTreeWriter jsonTreeWriter = new JsonTreeWriter();

    // Act and Assert
    assertSame(jsonTreeWriter, jsonTreeWriter.value(42L));
  }

  /**
   * Test {@link JsonTreeWriter#value(Number)} with {@code Number}.
   *
   * <ul>
   *   <li>Given {@link JsonTreeWriter} (default constructor).
   *   <li>When {@code null}.
   *   <li>Then {@link JsonTreeWriter#get()} return {@link JsonNull}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeWriter#value(Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonTreeWriter.value(Number)"})
  public void testValueWithNumber_givenJsonTreeWriter_whenNull_thenGetReturnJsonNull()
      throws IOException {
    // Arrange and Act
    JsonWriter actualValueResult = new JsonTreeWriter().value((Number) null);

    // Assert
    JsonElement getResult = ((JsonTreeWriter) actualValueResult).get();
    assertTrue(getResult instanceof JsonNull);
    assertTrue(actualValueResult instanceof JsonTreeWriter);
    assertFalse(getResult.isJsonPrimitive());
    assertTrue(getResult.isJsonNull());
    assertSame(getResult, getResult.getAsJsonNull());
  }

  /**
   * Test {@link JsonTreeWriter#value(Number)} with {@code Number}.
   *
   * <ul>
   *   <li>Then return Strictness is {@code LENIENT}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeWriter#value(Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonTreeWriter.value(Number)"})
  public void testValueWithNumber_thenReturnStrictnessIsLenient() throws IOException {
    // Arrange
    JsonTreeWriter jsonTreeWriter = new JsonTreeWriter();
    jsonTreeWriter.setStrictness(Strictness.LENIENT);

    // Act
    JsonWriter actualValueResult = jsonTreeWriter.value(Integer.valueOf(1));

    // Assert
    assertTrue(((JsonTreeWriter) actualValueResult).get() instanceof JsonPrimitive);
    assertTrue(actualValueResult instanceof JsonTreeWriter);
    assertEquals(Strictness.LENIENT, actualValueResult.getStrictness());
    assertTrue(actualValueResult.isLenient());
  }

  /**
   * Test {@link JsonTreeWriter#value(Number)} with {@code Number}.
   *
   * <ul>
   *   <li>When {@link Double#NaN}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeWriter#value(Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonTreeWriter.value(Number)"})
  public void testValueWithNumber_whenNaN_thenThrowIllegalArgumentException() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> new JsonTreeWriter().value((Number) Double.NaN));
  }

  /**
   * Test {@link JsonTreeWriter#value(Number)} with {@code Number}.
   *
   * <ul>
   *   <li>When valueOf one.
   *   <li>Then return Strictness is {@code LEGACY_STRICT}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeWriter#value(Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonTreeWriter.value(Number)"})
  public void testValueWithNumber_whenValueOfOne_thenReturnStrictnessIsLegacyStrict()
      throws IOException {
    // Arrange
    JsonTreeWriter jsonTreeWriter = new JsonTreeWriter();

    // Act
    JsonWriter actualValueResult = jsonTreeWriter.value(Integer.valueOf(1));

    // Assert
    assertTrue(((JsonTreeWriter) actualValueResult).get() instanceof JsonPrimitive);
    assertTrue(actualValueResult instanceof JsonTreeWriter);
    assertEquals(Strictness.LEGACY_STRICT, actualValueResult.getStrictness());
    assertFalse(actualValueResult.isLenient());
  }

  /**
   * Test {@link JsonTreeWriter#value(String)} with {@code String}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then {@link JsonTreeWriter#get()} return {@link JsonPrimitive}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeWriter#value(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonTreeWriter.value(String)"})
  public void testValueWithString_when42_thenGetReturnJsonPrimitive() throws IOException {
    // Arrange and Act
    JsonWriter actualValueResult = new JsonTreeWriter().value("42");

    // Assert
    JsonElement getResult = ((JsonTreeWriter) actualValueResult).get();
    assertTrue(getResult instanceof JsonPrimitive);
    assertTrue(getResult.getAsNumber() instanceof LazilyParsedNumber);
    assertTrue(actualValueResult instanceof JsonTreeWriter);
    assertEquals("42", getResult.getAsString());
    assertEquals('4', getResult.getAsCharacter());
    assertEquals(42, getResult.getAsInt());
    assertEquals(42.0d, getResult.getAsDouble(), 0.0);
    assertEquals(42.0f, getResult.getAsFloat(), 0.0f);
    assertEquals(42L, getResult.getAsLong());
    assertEquals((short) 42, getResult.getAsShort());
    assertFalse(getResult.getAsBoolean());
    assertFalse(getResult.isJsonNull());
    assertFalse(((JsonPrimitive) getResult).isBoolean());
    assertFalse(((JsonPrimitive) getResult).isNumber());
    assertTrue(getResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) getResult).isString());
    BigDecimal expectedAsBigDecimal = new BigDecimal("42");
    assertEquals(expectedAsBigDecimal, getResult.getAsBigDecimal());
    assertEquals('*', getResult.getAsByte());
    assertSame(getResult, getResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonTreeWriter#value(String)} with {@code String}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@link JsonTreeWriter#get()} return {@link JsonNull}.
   * </ul>
   *
   * <p>Method under test: {@link JsonTreeWriter#value(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonTreeWriter.value(String)"})
  public void testValueWithString_whenNull_thenGetReturnJsonNull() throws IOException {
    // Arrange and Act
    JsonWriter actualValueResult = new JsonTreeWriter().value((String) null);

    // Assert
    JsonElement getResult = ((JsonTreeWriter) actualValueResult).get();
    assertTrue(getResult instanceof JsonNull);
    assertTrue(actualValueResult instanceof JsonTreeWriter);
    assertFalse(getResult.isJsonPrimitive());
    assertTrue(getResult.isJsonNull());
    assertSame(getResult, getResult.getAsJsonNull());
  }

  /**
   * Test {@link JsonTreeWriter#nullValue()}.
   *
   * <p>Method under test: {@link JsonTreeWriter#nullValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonTreeWriter.nullValue()"})
  public void testNullValue() throws IOException {
    // Arrange
    JsonTreeWriter jsonTreeWriter = new JsonTreeWriter();

    // Act and Assert
    assertSame(jsonTreeWriter, jsonTreeWriter.nullValue());
  }

  /**
   * Test {@link JsonTreeWriter#jsonValue(String)}.
   *
   * <p>Method under test: {@link JsonTreeWriter#jsonValue(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonWriter JsonTreeWriter.jsonValue(String)"})
  public void testJsonValue() throws IOException {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> new JsonTreeWriter().jsonValue("42"));
  }
}
