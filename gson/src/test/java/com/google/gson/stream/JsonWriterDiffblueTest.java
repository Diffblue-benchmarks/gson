package com.google.gson.stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hasher;
import com.google.common.hash.HashingOutputStream;
import com.google.common.primitives.UnsignedInteger;
import com.google.gson.FormattingStyle;
import com.google.gson.Strictness;
import com.google.gson.internal.bind.JsonTreeWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import org.junit.Test;

public class JsonWriterDiffblueTest {
  /** Method under test: {@link JsonWriter#setIndent(String)} */
  @Test
  public void testSetIndent() {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act
    jsonWriter.setIndent(" ");

    // Assert
    FormattingStyle formattingStyle = jsonWriter.getFormattingStyle();
    assertEquals(" ", formattingStyle.getIndent());
    assertEquals("\n", formattingStyle.getNewline());
  }

  /** Method under test: {@link JsonWriter#setIndent(String)} */
  @Test
  public void testSetIndent2() {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act
    jsonWriter.setIndent("");

    // Assert
    FormattingStyle formattingStyle = jsonWriter.getFormattingStyle();
    assertEquals("", formattingStyle.getIndent());
    assertEquals("", formattingStyle.getNewline());
  }

  /** Method under test: {@link JsonWriter#setFormattingStyle(FormattingStyle)} */
  @Test
  public void testSetFormattingStyle() {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());
    FormattingStyle formattingStyle = FormattingStyle.COMPACT;

    // Act
    jsonWriter.setFormattingStyle(formattingStyle);

    // Assert
    FormattingStyle expectedFormattingStyle = formattingStyle.COMPACT;
    assertSame(expectedFormattingStyle, jsonWriter.getFormattingStyle());
  }

  /** Method under test: {@link JsonWriter#setFormattingStyle(FormattingStyle)} */
  @Test
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

  /** Method under test: {@link JsonWriter#setLenient(boolean)} */
  @Test
  public void testSetLenient() {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act
    jsonWriter.setLenient(true);

    // Assert
    assertEquals(Strictness.LENIENT, jsonWriter.getStrictness());
    assertTrue(jsonWriter.isLenient());
  }

  /** Method under test: {@link JsonWriter#setLenient(boolean)} */
  @Test
  public void testSetLenient2() {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act
    jsonWriter.setLenient(false);

    // Assert
    assertEquals(Strictness.LEGACY_STRICT, jsonWriter.getStrictness());
    assertFalse(jsonWriter.isLenient());
  }

  /** Method under test: {@link JsonWriter#isLenient()} */
  @Test
  public void testIsLenient() {
    // Arrange, Act and Assert
    assertFalse((new JsonWriter(new StringWriter())).isLenient());
  }

  /** Method under test: {@link JsonWriter#isLenient()} */
  @Test
  public void testIsLenient2() {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());
    jsonWriter.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertTrue(jsonWriter.isLenient());
  }

  /** Method under test: {@link JsonWriter#beginArray()} */
  @Test
  public void testBeginArray() throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act and Assert
    assertSame(jsonWriter, jsonWriter.beginArray());
  }

  /** Method under test: {@link JsonWriter#endArray()} */
  @Test
  public void testEndArray() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> (new JsonWriter(new StringWriter())).endArray());
  }

  /** Method under test: {@link JsonWriter#beginObject()} */
  @Test
  public void testBeginObject() throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act and Assert
    assertSame(jsonWriter, jsonWriter.beginObject());
  }

  /** Method under test: {@link JsonWriter#endObject()} */
  @Test
  public void testEndObject() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> (new JsonWriter(new StringWriter())).endObject());
  }

  /** Method under test: {@link JsonWriter#name(String)} */
  @Test
  public void testName() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> (new JsonWriter(new StringWriter())).name("Name"));
  }

  /** Method under test: {@link JsonWriter#value(double)} */
  @Test
  public void testValue() throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act and Assert
    assertSame(jsonWriter, jsonWriter.value(10.0d));
  }

  /** Method under test: {@link JsonWriter#value(float)} */
  @Test
  public void testValue2() throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act and Assert
    assertSame(jsonWriter, jsonWriter.value(10.0f));
  }

  /** Method under test: {@link JsonWriter#value(long)} */
  @Test
  public void testValue3() throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act and Assert
    assertSame(jsonWriter, jsonWriter.value(42L));
  }

  /** Method under test: {@link JsonWriter#value(Boolean)} */
  @Test
  public void testValue4() throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act and Assert
    assertSame(jsonWriter, jsonWriter.value((Boolean) true));
  }

  /** Method under test: {@link JsonWriter#value(Number)} */
  @Test
  public void testValue5() throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act and Assert
    assertSame(jsonWriter, jsonWriter.value(Integer.valueOf(1)));
  }

  /** Method under test: {@link JsonWriter#value(Number)} */
  @Test
  public void testValue6() throws IOException {
    // Arrange
    JsonTreeWriter jsonTreeWriter = new JsonTreeWriter();

    // Act and Assert
    assertSame(jsonTreeWriter, jsonTreeWriter.value(Integer.valueOf(1)));
  }

  /** Method under test: {@link JsonWriter#value(Number)} */
  @Test
  public void testValue7() throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act and Assert
    assertSame(jsonWriter, jsonWriter.value((byte) 'A'));
  }

  /** Method under test: {@link JsonWriter#value(Number)} */
  @Test
  public void testValue8() throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act and Assert
    assertSame(jsonWriter, jsonWriter.value((Number) 42L));
  }

  /** Method under test: {@link JsonWriter#value(Number)} */
  @Test
  public void testValue9() throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act and Assert
    assertSame(jsonWriter, jsonWriter.value((short) 291));
  }

  /** Method under test: {@link JsonWriter#value(Number)} */
  @Test
  public void testValue10() throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act and Assert
    assertSame(jsonWriter, jsonWriter.value((Number) null));
  }

  /** Method under test: {@link JsonWriter#value(Number)} */
  @Test
  public void testValue11() throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act and Assert
    assertSame(jsonWriter, jsonWriter.value((Number) 10.0d));
  }

  /** Method under test: {@link JsonWriter#value(Number)} */
  @Test
  public void testValue12() throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act and Assert
    assertSame(jsonWriter, jsonWriter.value((Number) 10.0f));
  }

  /** Method under test: {@link JsonWriter#value(Number)} */
  @Test
  public void testValue13() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> (new JsonWriter(new StringWriter())).value((Number) Double.NaN));
  }

  /** Method under test: {@link JsonWriter#value(Number)} */
  @Test
  public void testValue14() throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act and Assert
    assertSame(jsonWriter, jsonWriter.value(UnsignedInteger.fromIntBits(1)));
  }

  /** Method under test: {@link JsonWriter#value(String)} */
  @Test
  public void testValue15() throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act and Assert
    assertSame(jsonWriter, jsonWriter.value("42"));
  }

  /** Method under test: {@link JsonWriter#value(boolean)} */
  @Test
  public void testValue16() throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act and Assert
    assertSame(jsonWriter, jsonWriter.value(true));
  }

  /** Method under test: {@link JsonWriter#nullValue()} */
  @Test
  public void testNullValue() throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act and Assert
    assertSame(jsonWriter, jsonWriter.nullValue());
  }

  /** Method under test: {@link JsonWriter#jsonValue(String)} */
  @Test
  public void testJsonValue() throws IOException {
    // Arrange
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());

    // Act and Assert
    assertSame(jsonWriter, jsonWriter.jsonValue("42"));
  }

  /** Method under test: {@link JsonWriter#close()} */
  @Test
  public void testClose() throws IOException {
    // Arrange, Act and Assert
    assertThrows(IOException.class, () -> (new JsonWriter(new StringWriter())).close());
  }

  /**
   * Methods under test:
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

    // Assert that nothing has changed
    assertEquals(Strictness.LENIENT, actualStrictness);
    assertTrue(actualSerializeNulls);
    assertTrue(jsonWriter.isHtmlSafe());
    assertSame(actualFormattingStyle.COMPACT, actualFormattingStyle);
  }

  /** Method under test: {@link JsonWriter#JsonWriter(Writer)} */
  @Test
  public void testNewJsonWriter() {
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

  /** Method under test: {@link JsonWriter#JsonWriter(Writer)} */
  @Test
  public void testNewJsonWriter2() {
    // Arrange
    HashFunction hashFunction = mock(HashFunction.class);
    when(hashFunction.newHasher()).thenReturn(mock(Hasher.class));

    // Act
    JsonWriter actualJsonWriter =
        new JsonWriter(
            new OutputStreamWriter(
                new HashingOutputStream(hashFunction, new ByteArrayOutputStream(1))));

    // Assert
    verify(hashFunction).newHasher();
    FormattingStyle formattingStyle = actualJsonWriter.getFormattingStyle();
    assertEquals("", formattingStyle.getIndent());
    assertEquals("", formattingStyle.getNewline());
    assertEquals(Strictness.LEGACY_STRICT, actualJsonWriter.getStrictness());
    assertFalse(actualJsonWriter.isHtmlSafe());
    assertFalse(actualJsonWriter.isLenient());
    assertTrue(actualJsonWriter.getSerializeNulls());
  }
}
