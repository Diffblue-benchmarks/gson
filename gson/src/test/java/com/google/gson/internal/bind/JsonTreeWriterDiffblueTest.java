package com.google.gson.internal.bind;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import com.google.gson.FormattingStyle;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.Strictness;
import java.io.IOException;
import org.junit.Test;

public class JsonTreeWriterDiffblueTest {
  /** Method under test: {@link JsonTreeWriter#get()} */
  @Test
  public void testGet() {
    // Arrange and Act
    JsonElement actualGetResult = (new JsonTreeWriter()).get();

    // Assert
    assertSame(((JsonNull) actualGetResult).INSTANCE, actualGetResult);
  }

  /** Method under test: {@link JsonTreeWriter#beginArray()} */
  @Test
  public void testBeginArray() throws IOException {
    // Arrange
    JsonTreeWriter jsonTreeWriter = new JsonTreeWriter();

    // Act and Assert
    assertSame(jsonTreeWriter, jsonTreeWriter.beginArray());
  }

  /** Method under test: {@link JsonTreeWriter#endArray()} */
  @Test
  public void testEndArray() throws IOException {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new JsonTreeWriter()).endArray());
  }

  /** Method under test: {@link JsonTreeWriter#beginObject()} */
  @Test
  public void testBeginObject() throws IOException {
    // Arrange
    JsonTreeWriter jsonTreeWriter = new JsonTreeWriter();

    // Act and Assert
    assertSame(jsonTreeWriter, jsonTreeWriter.beginObject());
  }

  /** Method under test: {@link JsonTreeWriter#endObject()} */
  @Test
  public void testEndObject() throws IOException {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new JsonTreeWriter()).endObject());
  }

  /** Method under test: {@link JsonTreeWriter#name(String)} */
  @Test
  public void testName() throws IOException {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> (new JsonTreeWriter()).name("Name"));
  }

  /** Method under test: {@link JsonTreeWriter#value(double)} */
  @Test
  public void testValue() throws IOException {
    // Arrange
    JsonTreeWriter jsonTreeWriter = new JsonTreeWriter();

    // Act and Assert
    assertSame(jsonTreeWriter, jsonTreeWriter.value(10.0d));
  }

  /** Method under test: {@link JsonTreeWriter#value(double)} */
  @Test
  public void testValue2() throws IOException {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> (new JsonTreeWriter()).value(Double.NaN));
  }

  /** Method under test: {@link JsonTreeWriter#value(double)} */
  @Test
  public void testValue3() throws IOException {
    // Arrange
    JsonTreeWriter jsonTreeWriter = new JsonTreeWriter();
    jsonTreeWriter.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertSame(jsonTreeWriter, jsonTreeWriter.value(10.0d));
  }

  /** Method under test: {@link JsonTreeWriter#value(float)} */
  @Test
  public void testValue4() throws IOException {
    // Arrange
    JsonTreeWriter jsonTreeWriter = new JsonTreeWriter();

    // Act and Assert
    assertSame(jsonTreeWriter, jsonTreeWriter.value(10.0f));
  }

  /** Method under test: {@link JsonTreeWriter#value(float)} */
  @Test
  public void testValue5() throws IOException {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> (new JsonTreeWriter()).value(Float.NaN));
  }

  /** Method under test: {@link JsonTreeWriter#value(float)} */
  @Test
  public void testValue6() throws IOException {
    // Arrange
    JsonTreeWriter jsonTreeWriter = new JsonTreeWriter();
    jsonTreeWriter.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertSame(jsonTreeWriter, jsonTreeWriter.value(10.0f));
  }

  /** Method under test: {@link JsonTreeWriter#value(long)} */
  @Test
  public void testValue7() throws IOException {
    // Arrange
    JsonTreeWriter jsonTreeWriter = new JsonTreeWriter();

    // Act and Assert
    assertSame(jsonTreeWriter, jsonTreeWriter.value(42L));
  }

  /** Method under test: {@link JsonTreeWriter#value(Boolean)} */
  @Test
  public void testValue8() throws IOException {
    // Arrange
    JsonTreeWriter jsonTreeWriter = new JsonTreeWriter();

    // Act and Assert
    assertSame(jsonTreeWriter, jsonTreeWriter.value((Boolean) true));
  }

  /** Method under test: {@link JsonTreeWriter#value(Boolean)} */
  @Test
  public void testValue9() throws IOException {
    // Arrange
    JsonTreeWriter jsonTreeWriter = new JsonTreeWriter();

    // Act and Assert
    assertSame(jsonTreeWriter, jsonTreeWriter.value((Boolean) null));
  }

  /** Method under test: {@link JsonTreeWriter#value(Number)} */
  @Test
  public void testValue10() throws IOException {
    // Arrange
    JsonTreeWriter jsonTreeWriter = new JsonTreeWriter();

    // Act and Assert
    assertSame(jsonTreeWriter, jsonTreeWriter.value(Integer.valueOf(1)));
  }

  /** Method under test: {@link JsonTreeWriter#value(Number)} */
  @Test
  public void testValue11() throws IOException {
    // Arrange
    JsonTreeWriter jsonTreeWriter = new JsonTreeWriter();

    // Act and Assert
    assertSame(jsonTreeWriter, jsonTreeWriter.value((Number) null));
  }

  /** Method under test: {@link JsonTreeWriter#value(Number)} */
  @Test
  public void testValue12() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> (new JsonTreeWriter()).value((Number) Double.NaN));
  }

  /** Method under test: {@link JsonTreeWriter#value(Number)} */
  @Test
  public void testValue13() throws IOException {
    // Arrange
    JsonTreeWriter jsonTreeWriter = new JsonTreeWriter();
    jsonTreeWriter.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertSame(jsonTreeWriter, jsonTreeWriter.value(Integer.valueOf(1)));
  }

  /** Method under test: {@link JsonTreeWriter#value(String)} */
  @Test
  public void testValue14() throws IOException {
    // Arrange
    JsonTreeWriter jsonTreeWriter = new JsonTreeWriter();

    // Act and Assert
    assertSame(jsonTreeWriter, jsonTreeWriter.value("42"));
  }

  /** Method under test: {@link JsonTreeWriter#value(String)} */
  @Test
  public void testValue15() throws IOException {
    // Arrange
    JsonTreeWriter jsonTreeWriter = new JsonTreeWriter();

    // Act and Assert
    assertSame(jsonTreeWriter, jsonTreeWriter.value((String) null));
  }

  /** Method under test: {@link JsonTreeWriter#value(boolean)} */
  @Test
  public void testValue16() throws IOException {
    // Arrange
    JsonTreeWriter jsonTreeWriter = new JsonTreeWriter();

    // Act and Assert
    assertSame(jsonTreeWriter, jsonTreeWriter.value(true));
  }

  /** Method under test: {@link JsonTreeWriter#nullValue()} */
  @Test
  public void testNullValue() throws IOException {
    // Arrange
    JsonTreeWriter jsonTreeWriter = new JsonTreeWriter();

    // Act and Assert
    assertSame(jsonTreeWriter, jsonTreeWriter.nullValue());
  }

  /** Method under test: {@link JsonTreeWriter#jsonValue(String)} */
  @Test
  public void testJsonValue() throws IOException {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> (new JsonTreeWriter()).jsonValue("42"));
  }

  /** Method under test: default or parameterless constructor of {@link JsonTreeWriter} */
  @Test
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
}
