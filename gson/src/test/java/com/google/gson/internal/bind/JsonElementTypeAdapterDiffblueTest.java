package com.google.gson.internal.bind;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.Strictness;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JsonElementTypeAdapterDiffblueTest {
  /**
   * Test {@link JsonElementTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code at line}.
   *   <li>Then return AsString is {@code at}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_whenStringReaderWithAtLine_thenReturnAsStringIsAt()
      throws IOException {
    // Arrange
    JsonReader in = new JsonReader(new StringReader(" at line "));
    in.setStrictness(Strictness.LENIENT);

    // Act
    JsonElement actualReadResult = JsonElementTypeAdapter.ADAPTER.read(in);

    // Assert
    assertTrue(actualReadResult instanceof JsonPrimitive);
    Number asNumber = actualReadResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("at", actualReadResult.getAsString());
    assertEquals("at", asNumber.toString());
    assertEquals('a', actualReadResult.getAsCharacter());
    assertSame(actualReadResult, actualReadResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElementTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code End of input}.
   *   <li>Then return AsString is {@code End}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_whenStringReaderWithEndOfInput_thenReturnAsStringIsEnd()
      throws IOException {
    // Arrange
    JsonReader in = new JsonReader(new StringReader("End of input"));
    in.setStrictness(Strictness.LENIENT);

    // Act
    JsonElement actualReadResult = JsonElementTypeAdapter.ADAPTER.read(in);

    // Assert
    assertTrue(actualReadResult instanceof JsonPrimitive);
    Number asNumber = actualReadResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("End", actualReadResult.getAsString());
    assertEquals("End", asNumber.toString());
    assertEquals('E', actualReadResult.getAsCharacter());
    assertSame(actualReadResult, actualReadResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElementTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then return not String.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_whenStringReaderWithFalseToString_thenReturnNotString()
      throws IOException {
    // Arrange
    JsonReader in = new JsonReader(new StringReader(Boolean.FALSE.toString()));
    in.setStrictness(Strictness.LENIENT);

    // Act
    JsonElement actualReadResult = JsonElementTypeAdapter.ADAPTER.read(in);

    // Assert
    assertTrue(actualReadResult instanceof JsonPrimitive);
    assertFalse(((JsonPrimitive) actualReadResult).isString());
    assertTrue(((JsonPrimitive) actualReadResult).isBoolean());
    String expectedAsString = Boolean.FALSE.toString();
    assertEquals(expectedAsString, actualReadResult.getAsString());
    assertSame(actualReadResult, actualReadResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElementTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then return AsString is {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_whenStringReaderWithFoo_thenReturnAsStringIsFoo()
      throws IOException {
    // Arrange
    JsonReader in = new JsonReader(new StringReader("foo"));
    in.setStrictness(Strictness.LENIENT);

    // Act
    JsonElement actualReadResult = JsonElementTypeAdapter.ADAPTER.read(in);

    // Assert
    assertTrue(actualReadResult instanceof JsonPrimitive);
    Number asNumber = actualReadResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("foo", actualReadResult.getAsString());
    assertEquals("foo", asNumber.toString());
    assertEquals('f', actualReadResult.getAsCharacter());
    assertSame(actualReadResult, actualReadResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElementTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code See}.
   *   <li>Then return AsString is {@code See}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_whenStringReaderWithSee_thenReturnAsStringIsSee()
      throws IOException {
    // Arrange
    JsonReader in = new JsonReader(new StringReader("\nSee "));
    in.setStrictness(Strictness.LENIENT);

    // Act
    JsonElement actualReadResult = JsonElementTypeAdapter.ADAPTER.read(in);

    // Assert
    assertTrue(actualReadResult instanceof JsonPrimitive);
    Number asNumber = actualReadResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("See", actualReadResult.getAsString());
    assertEquals("See", asNumber.toString());
    assertEquals('S', actualReadResult.getAsCharacter());
    assertSame(actualReadResult, actualReadResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElementTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code STRICT}.
   *   <li>When {@link JsonReader#JsonReader(Reader)} with in is {@link
   *       StringReader#StringReader(String)} Strictness is {@code STRICT}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_givenStrict_whenJsonReaderWithInIsStringReaderStrictnessIsStrict()
      throws IOException {
    // Arrange
    JsonReader in = new JsonReader(new StringReader(Boolean.FALSE.toString()));
    in.setStrictness(Strictness.STRICT);

    // Act
    JsonElement actualReadResult = JsonElementTypeAdapter.ADAPTER.read(in);

    // Assert
    assertTrue(actualReadResult instanceof JsonPrimitive);
    assertFalse(((JsonPrimitive) actualReadResult).isString());
    assertTrue(((JsonPrimitive) actualReadResult).isBoolean());
    String expectedAsString = Boolean.FALSE.toString();
    assertEquals(expectedAsString, actualReadResult.getAsString());
    assertSame(actualReadResult, actualReadResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElementTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Then return AsString is {@code nd}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_thenReturnAsStringIsNd() throws IOException {
    // Arrange
    StringReader in = new StringReader("End of input");
    in.read("\u0001\u0006\u0001\u0006".toCharArray(), 1, 1);

    JsonReader in2 = new JsonReader(in);
    in2.setStrictness(Strictness.LENIENT);

    // Act
    JsonElement actualReadResult = JsonElementTypeAdapter.ADAPTER.read(in2);

    // Assert
    assertTrue(actualReadResult instanceof JsonPrimitive);
    Number asNumber = actualReadResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("nd", actualReadResult.getAsString());
    assertEquals("nd", asNumber.toString());
    assertEquals('n', actualReadResult.getAsCharacter());
    assertSame(actualReadResult, actualReadResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElementTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Then return AsString is {@code Use}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_thenReturnAsStringIsUse() throws IOException {
    // Arrange
    JsonReader in =
        new JsonReader(
            new StringReader(
                "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed JSON"));
    in.setStrictness(Strictness.LENIENT);

    // Act
    JsonElement actualReadResult = JsonElementTypeAdapter.ADAPTER.read(in);

    // Assert
    assertTrue(actualReadResult instanceof JsonPrimitive);
    Number asNumber = actualReadResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("Use", actualReadResult.getAsString());
    assertEquals("Use", asNumber.toString());
    assertEquals('U', actualReadResult.getAsCharacter());
    assertSame(actualReadResult, actualReadResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElementTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonArray#JsonArray(int)} with capacity is three.
   *   <li>Then return {@link JsonArray#JsonArray(int)} with capacity is three.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_whenJsonArrayWithCapacityIsThree_thenReturnJsonArrayWithCapacityIsThree()
      throws IOException {
    // Arrange
    JsonArray element = new JsonArray(3);

    // Act and Assert
    assertSame(element, JsonElementTypeAdapter.ADAPTER.read(new JsonTreeReader(element)));
  }

  /**
   * Test {@link JsonElementTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.
   *   <li>Then return {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_whenJsonPrimitiveWithBoolIsTrue_thenReturnJsonPrimitiveWithBoolIsTrue()
      throws IOException {
    // Arrange
    JsonPrimitive element = new JsonPrimitive(true);

    // Act and Assert
    assertSame(element, JsonElementTypeAdapter.ADAPTER.read(new JsonTreeReader(element)));
  }

  /**
   * Test {@link JsonElementTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonPrimitive#JsonPrimitive(String)} with {@code String}.
   *   <li>Then return {@link JsonPrimitive#JsonPrimitive(String)} with {@code String}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_whenJsonPrimitiveWithString_thenReturnJsonPrimitiveWithString()
      throws IOException {
    // Arrange
    JsonPrimitive element = new JsonPrimitive("String");

    // Act and Assert
    assertSame(element, JsonElementTypeAdapter.ADAPTER.read(new JsonTreeReader(element)));
  }

  /**
   * Test {@link JsonElementTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE}.
   *   <li>Then return {@link JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_whenJsonTreeReaderWithElementIsInstance_thenReturnInstance()
      throws IOException {
    // Arrange and Act
    JsonElement actualReadResult =
        JsonElementTypeAdapter.ADAPTER.read(new JsonTreeReader(JsonNull.INSTANCE));

    // Assert
    assertSame(((JsonNull) actualReadResult).INSTANCE, actualReadResult);
  }

  /**
   * Test {@link JsonElementTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonObject} (default constructor).
   *   <li>Then return {@link JsonObject} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_whenJsonTreeReaderWithElementIsJsonObject_thenReturnJsonObject()
      throws IOException {
    // Arrange
    JsonObject element = new JsonObject();

    // Act and Assert
    assertSame(element, JsonElementTypeAdapter.ADAPTER.read(new JsonTreeReader(element)));
  }

  /**
   * Test {@link JsonElementTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then return AsString is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_whenStringReaderWith42_thenReturnAsStringIs42() throws IOException {
    // Arrange and Act
    JsonElement actualReadResult =
        JsonElementTypeAdapter.ADAPTER.read(new JsonReader(new StringReader("42")));

    // Assert
    assertTrue(actualReadResult instanceof JsonPrimitive);
    assertEquals("42", actualReadResult.getAsString());
    assertEquals('4', actualReadResult.getAsCharacter());
    assertEquals(42, actualReadResult.getAsInt());
    assertEquals(42.0d, actualReadResult.getAsDouble(), 0.0);
    assertEquals(42.0f, actualReadResult.getAsFloat(), 0.0f);
    assertEquals(42L, actualReadResult.getAsLong());
    assertEquals((short) 42, actualReadResult.getAsShort());
    assertTrue(((JsonPrimitive) actualReadResult).isNumber());
    BigDecimal expectedAsBigDecimal = new BigDecimal("42");
    assertEquals(expectedAsBigDecimal, actualReadResult.getAsBigDecimal());
    assertEquals('*', actualReadResult.getAsByte());
    assertSame(actualReadResult, actualReadResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElementTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code false at line}.
   *   <li>Then return not String.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_whenStringReaderWithFalseAtLine_thenReturnNotString() throws IOException {
    // Arrange and Act
    JsonElement actualReadResult =
        JsonElementTypeAdapter.ADAPTER.read(new JsonReader(new StringReader("false at line ")));

    // Assert
    assertTrue(actualReadResult instanceof JsonPrimitive);
    assertFalse(((JsonPrimitive) actualReadResult).isString());
    assertTrue(((JsonPrimitive) actualReadResult).isBoolean());
    String expectedAsString = Boolean.FALSE.toString();
    assertEquals(expectedAsString, actualReadResult.getAsString());
    assertSame(actualReadResult, actualReadResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElementTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then return not String.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_whenStringReaderWithFalseToString_thenReturnNotString() throws IOException {
    // Arrange and Act
    JsonElement actualReadResult =
        JsonElementTypeAdapter.ADAPTER.read(
            new JsonReader(new StringReader(Boolean.FALSE.toString())));

    // Assert
    assertTrue(actualReadResult instanceof JsonPrimitive);
    assertFalse(((JsonPrimitive) actualReadResult).isString());
    assertTrue(((JsonPrimitive) actualReadResult).isBoolean());
    String expectedAsString = Boolean.FALSE.toString();
    assertEquals(expectedAsString, actualReadResult.getAsString());
    assertSame(actualReadResult, actualReadResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElementTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code FALSE}.
   *   <li>Then return not String.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_whenStringReaderWithFalse_thenReturnNotString() throws IOException {
    // Arrange and Act
    JsonElement actualReadResult =
        JsonElementTypeAdapter.ADAPTER.read(new JsonReader(new StringReader("FALSE")));

    // Assert
    assertTrue(actualReadResult instanceof JsonPrimitive);
    assertFalse(((JsonPrimitive) actualReadResult).isString());
    assertTrue(((JsonPrimitive) actualReadResult).isBoolean());
    String expectedAsString = Boolean.FALSE.toString();
    assertEquals(expectedAsString, actualReadResult.getAsString());
    assertSame(actualReadResult, actualReadResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonElementTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code falsefalse}.
   *   <li>Then return AsString is {@code falsefalse}.
   * </ul>
   *
   * <p>Method under test: {@link JsonElementTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonElementTypeAdapter.read(JsonReader)"})
  public void testRead_whenStringReaderWithFalsefalse_thenReturnAsStringIsFalsefalse()
      throws IOException {
    // Arrange
    JsonReader in = new JsonReader(new StringReader("falsefalse"));
    in.setStrictness(Strictness.LENIENT);

    // Act
    JsonElement actualReadResult = JsonElementTypeAdapter.ADAPTER.read(in);

    // Assert
    assertTrue(actualReadResult instanceof JsonPrimitive);
    Number asNumber = actualReadResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("falsefalse", actualReadResult.getAsString());
    assertEquals("falsefalse", asNumber.toString());
    assertEquals('f', actualReadResult.getAsCharacter());
    assertSame(actualReadResult, actualReadResult.getAsJsonPrimitive());
  }
}
