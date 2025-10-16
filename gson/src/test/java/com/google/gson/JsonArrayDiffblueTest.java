package com.google.gson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.internal.LazilyParsedNumber;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JsonArrayDiffblueTest {
  /**
   * Test {@link JsonArray#JsonArray()}.
   *
   * <p>Method under test: {@link JsonArray#JsonArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonArray.<init>()"})
  public void testNewJsonArray() {
    // Arrange and Act
    JsonArray actualJsonArray = new JsonArray();

    // Assert
    assertEquals(0, actualJsonArray.size());
    assertFalse(actualJsonArray.isJsonNull());
    assertFalse(actualJsonArray.isJsonObject());
    assertFalse(actualJsonArray.isJsonPrimitive());
    assertFalse(actualJsonArray.iterator().hasNext());
    assertTrue(actualJsonArray.isEmpty());
    assertTrue(actualJsonArray.isJsonArray());
    JsonArray actualAsJsonArray = actualJsonArray.getAsJsonArray();
    assertSame(actualJsonArray, actualAsJsonArray);
  }

  /**
   * Test {@link JsonArray#JsonArray(int)}.
   *
   * <ul>
   *   <li>When three.
   *   <li>Then return size is zero.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#JsonArray(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonArray.<init>(int)"})
  public void testNewJsonArray_whenThree_thenReturnSizeIsZero() {
    // Arrange and Act
    JsonArray actualJsonArray = new JsonArray(3);

    // Assert
    assertEquals(0, actualJsonArray.size());
    assertFalse(actualJsonArray.isJsonNull());
    assertFalse(actualJsonArray.isJsonObject());
    assertFalse(actualJsonArray.isJsonPrimitive());
    assertFalse(actualJsonArray.iterator().hasNext());
    assertTrue(actualJsonArray.isEmpty());
    assertTrue(actualJsonArray.isJsonArray());
    JsonArray actualAsJsonArray = actualJsonArray.getAsJsonArray();
    assertSame(actualJsonArray, actualAsJsonArray);
  }

  /**
   * Test {@link JsonArray#deepCopy()}.
   *
   * <ul>
   *   <li>Given createEmptyJsonArray.
   *   <li>Then return createEmptyJsonArray.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#deepCopy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonArray JsonArray.deepCopy()"})
  public void testDeepCopy_givenCreateEmptyJsonArray_thenReturnCreateEmptyJsonArray() {
    // Arrange
    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();

    // Act
    JsonArray actualDeepCopyResult = createEmptyJsonArrayResult.deepCopy();

    // Assert
    assertEquals(createEmptyJsonArrayResult, actualDeepCopyResult);
  }

  /**
   * Test {@link JsonArray#deepCopy()}.
   *
   * <ul>
   *   <li>Then AsNumber return {@link LazilyParsedNumber}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#deepCopy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonArray JsonArray.deepCopy()"})
  public void testDeepCopy_thenAsNumberReturnLazilyParsedNumber() {
    // Arrange and Act
    JsonArray actualDeepCopyResult =
        JsonArrayTestFactory.createJsonArrayWithOneElement().deepCopy();

    // Assert
    Iterator<JsonElement> iteratorResult = actualDeepCopyResult.iterator();
    JsonElement nextResult = iteratorResult.next();
    assertTrue(nextResult instanceof JsonPrimitive);
    Number asNumber = actualDeepCopyResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    Number asNumber2 = nextResult.getAsNumber();
    assertTrue(asNumber2 instanceof LazilyParsedNumber);
    assertEquals("singleElement", actualDeepCopyResult.getAsString());
    assertEquals("singleElement", asNumber.toString());
    assertEquals('s', actualDeepCopyResult.getAsCharacter());
    assertEquals(1, actualDeepCopyResult.size());
    assertFalse(actualDeepCopyResult.getAsBoolean());
    assertFalse(iteratorResult.hasNext());
    assertEquals(asNumber, asNumber2);
  }

  /**
   * Test {@link JsonArray#deepCopy()}.
   *
   * <ul>
   *   <li>Then iterator next return {@link JsonNull}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#deepCopy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonArray JsonArray.deepCopy()"})
  public void testDeepCopy_thenIteratorNextReturnJsonNull() {
    // Arrange
    JsonArray createJsonArrayWithOneElementResult =
        JsonArrayTestFactory.createJsonArrayWithOneElement();
    createJsonArrayWithOneElementResult.add(JsonNull.INSTANCE);

    // Act
    JsonArray actualDeepCopyResult = createJsonArrayWithOneElementResult.deepCopy();

    // Assert
    Iterator<JsonElement> iteratorResult = actualDeepCopyResult.iterator();
    JsonElement nextResult = iteratorResult.next();
    JsonElement nextResult2 = iteratorResult.next();
    boolean actualHasNextResult = iteratorResult.hasNext();
    assertTrue(nextResult2 instanceof JsonNull);
    assertTrue(nextResult instanceof JsonPrimitive);
    Number asNumber = nextResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("singleElement", asNumber.toString());
    assertEquals(2, actualDeepCopyResult.size());
    assertFalse(nextResult2.isJsonArray());
    assertFalse(nextResult2.isJsonPrimitive());
    assertFalse(actualHasNextResult);
    assertTrue(nextResult2.isJsonNull());
    assertSame(nextResult2, nextResult2.getAsJsonNull());
  }

  /**
   * Test {@link JsonArray#deepCopy()}.
   *
   * <ul>
   *   <li>Then return createJsonArrayWithMixedTypes.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#deepCopy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonArray JsonArray.deepCopy()"})
  public void testDeepCopy_thenReturnCreateJsonArrayWithMixedTypes() {
    // Arrange
    JsonArray createJsonArrayWithMixedTypesResult =
        JsonArrayTestFactory.createJsonArrayWithMixedTypes();

    // Act
    JsonArray actualDeepCopyResult = createJsonArrayWithMixedTypesResult.deepCopy();

    // Assert
    assertEquals(createJsonArrayWithMixedTypesResult, actualDeepCopyResult);
  }

  /**
   * Test {@link JsonArray#remove(JsonElement)} with {@code element}.
   *
   * <p>Method under test: {@link JsonArray#remove(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.remove(JsonElement)"})
  public void testRemoveWithElement() {
    // Arrange
    JsonArray createJsonArrayWithOneElementResult =
        JsonArrayTestFactory.createJsonArrayWithOneElement();
    JsonArray element = JsonArrayTestFactory.createJsonArrayWithOneElement();
    createJsonArrayWithOneElementResult.add(element);

    // Act
    createJsonArrayWithOneElementResult.remove(new JsonPrimitive("singleElement"));

    // Assert
    assertTrue(createJsonArrayWithOneElementResult.getAsNumber() instanceof LazilyParsedNumber);
    Iterator<JsonElement> iteratorResult = createJsonArrayWithOneElementResult.iterator();
    JsonElement actualNextResult = iteratorResult.next();
    assertFalse(iteratorResult.hasNext());
    assertSame(element, actualNextResult);
  }

  /**
   * Test {@link JsonArray#remove(JsonElement)} with {@code element}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithOneElement.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#remove(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.remove(JsonElement)"})
  public void testRemoveWithElement_givenCreateJsonArrayWithOneElement_thenReturnFalse() {
    // Arrange
    JsonArray createJsonArrayWithOneElementResult =
        JsonArrayTestFactory.createJsonArrayWithOneElement();

    // Act and Assert
    assertFalse(
        createJsonArrayWithOneElementResult.remove(
            JsonArrayTestFactory.createJsonArrayWithOneElement()));
  }

  /**
   * Test {@link JsonArray#remove(JsonElement)} with {@code element}.
   *
   * <ul>
   *   <li>Then createEmptyJsonArray size is zero.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#remove(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.remove(JsonElement)"})
  public void testRemoveWithElement_thenCreateEmptyJsonArraySizeIsZero() {
    // Arrange
    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();

    // Act
    boolean actualRemoveResult =
        createEmptyJsonArrayResult.remove(JsonArrayTestFactory.createJsonArrayWithOneElement());

    // Assert
    assertEquals(0, createEmptyJsonArrayResult.size());
    assertFalse(actualRemoveResult);
    assertFalse(createEmptyJsonArrayResult.iterator().hasNext());
  }

  /**
   * Test {@link JsonArray#remove(JsonElement)} with {@code element}.
   *
   * <ul>
   *   <li>Then createJsonArrayWithOneElement AsString is {@code singleElement}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#remove(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.remove(JsonElement)"})
  public void testRemoveWithElement_thenCreateJsonArrayWithOneElementAsStringIsSingleElement() {
    // Arrange
    JsonArray createJsonArrayWithOneElementResult =
        JsonArrayTestFactory.createJsonArrayWithOneElement();
    createJsonArrayWithOneElementResult.add(JsonArrayTestFactory.createJsonArrayWithNumbers());

    // Act
    createJsonArrayWithOneElementResult.remove(JsonArrayTestFactory.createJsonArrayWithNumbers());

    // Assert
    Iterator<JsonElement> iteratorResult = createJsonArrayWithOneElementResult.iterator();
    JsonElement nextResult = iteratorResult.next();
    assertTrue(nextResult instanceof JsonPrimitive);
    Number asNumber = createJsonArrayWithOneElementResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    Number asNumber2 = nextResult.getAsNumber();
    assertTrue(asNumber2 instanceof LazilyParsedNumber);
    assertEquals("singleElement", createJsonArrayWithOneElementResult.getAsString());
    assertEquals("singleElement", asNumber.toString());
    assertEquals('s', createJsonArrayWithOneElementResult.getAsCharacter());
    assertEquals(1, createJsonArrayWithOneElementResult.size());
    assertFalse(createJsonArrayWithOneElementResult.getAsBoolean());
    assertFalse(iteratorResult.hasNext());
    assertEquals(asNumber, asNumber2);
  }

  /**
   * Test {@link JsonArray#remove(JsonElement)} with {@code element}.
   *
   * <ul>
   *   <li>Then createJsonArrayWithOneElement size is one.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#remove(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.remove(JsonElement)"})
  public void testRemoveWithElement_thenCreateJsonArrayWithOneElementSizeIsOne() {
    // Arrange
    JsonArray createJsonArrayWithOneElementResult =
        JsonArrayTestFactory.createJsonArrayWithOneElement();
    createJsonArrayWithOneElementResult.add(JsonArrayTestFactory.createJsonArrayWithNumbers());
    JsonArray element = JsonArrayTestFactory.createJsonArrayWithOneElement();

    // Act
    createJsonArrayWithOneElementResult.remove(element);

    // Assert that nothing has changed
    Iterator<JsonElement> iteratorResult = element.iterator();
    JsonElement nextResult = iteratorResult.next();
    assertTrue(nextResult instanceof JsonPrimitive);
    Number asNumber = element.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    Number asNumber2 = nextResult.getAsNumber();
    assertTrue(asNumber2 instanceof LazilyParsedNumber);
    assertEquals(1, element.size());
    assertFalse(iteratorResult.hasNext());
    assertEquals(asNumber, asNumber2);
  }

  /**
   * Test {@link JsonArray#remove(JsonElement)} with {@code element}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#remove(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.remove(JsonElement)"})
  public void testRemoveWithElement_thenReturnTrue() {
    // Arrange
    JsonArray createJsonArrayWithOneElementResult =
        JsonArrayTestFactory.createJsonArrayWithOneElement();
    createJsonArrayWithOneElementResult.add(JsonArrayTestFactory.createJsonArrayWithOneElement());

    // Act and Assert
    assertTrue(
        createJsonArrayWithOneElementResult.remove(
            JsonArrayTestFactory.createJsonArrayWithOneElement()));
  }

  /**
   * Test {@link JsonArray#contains(JsonElement)}.
   *
   * <ul>
   *   <li>Given createEmptyJsonArray.
   *   <li>When createJsonArrayWithOneElement.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#contains(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.contains(JsonElement)"})
  public void testContains_givenCreateEmptyJsonArray_whenCreateJsonArrayWithOneElement() {
    // Arrange
    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();

    // Act and Assert
    assertFalse(
        createEmptyJsonArrayResult.contains(JsonArrayTestFactory.createJsonArrayWithOneElement()));
  }

  /**
   * Test {@link JsonArray#contains(JsonElement)}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithNumbers.
   *   <li>When createJsonArrayWithOneElement.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#contains(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.contains(JsonElement)"})
  public void testContains_givenCreateJsonArrayWithNumbers_whenCreateJsonArrayWithOneElement() {
    // Arrange
    JsonArray createJsonArrayWithNumbersResult = JsonArrayTestFactory.createJsonArrayWithNumbers();

    // Act and Assert
    assertFalse(
        createJsonArrayWithNumbersResult.contains(
            JsonArrayTestFactory.createJsonArrayWithOneElement()));
  }

  /**
   * Test {@link JsonArray#contains(JsonElement)}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithOneElement.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#contains(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.contains(JsonElement)"})
  public void testContains_givenCreateJsonArrayWithOneElement() {
    // Arrange
    JsonArray createJsonArrayWithOneElementResult =
        JsonArrayTestFactory.createJsonArrayWithOneElement();

    // Act and Assert
    assertFalse(
        createJsonArrayWithOneElementResult.contains(
            JsonArrayTestFactory.createJsonArrayWithOneElement()));
  }

  /**
   * Test {@link JsonArray#contains(JsonElement)}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithOneElement add createJsonArrayWithNumbers.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#contains(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.contains(JsonElement)"})
  public void testContains_givenCreateJsonArrayWithOneElementAddCreateJsonArrayWithNumbers() {
    // Arrange
    JsonArray createJsonArrayWithOneElementResult =
        JsonArrayTestFactory.createJsonArrayWithOneElement();
    createJsonArrayWithOneElementResult.add(JsonArrayTestFactory.createJsonArrayWithNumbers());

    // Act and Assert
    assertFalse(
        createJsonArrayWithOneElementResult.contains(
            JsonArrayTestFactory.createJsonArrayWithOneElement()));
  }

  /**
   * Test {@link JsonArray#contains(JsonElement)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#contains(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.contains(JsonElement)"})
  public void testContains_thenReturnTrue() {
    // Arrange
    JsonArray createJsonArrayWithOneElementResult =
        JsonArrayTestFactory.createJsonArrayWithOneElement();
    createJsonArrayWithOneElementResult.add(JsonArrayTestFactory.createJsonArrayWithOneElement());

    // Act and Assert
    assertTrue(
        createJsonArrayWithOneElementResult.contains(
            JsonArrayTestFactory.createJsonArrayWithOneElement()));
  }

  /**
   * Test {@link JsonArray#contains(JsonElement)}.
   *
   * <ul>
   *   <li>When createJsonArray.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#contains(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.contains(JsonElement)"})
  public void testContains_whenCreateJsonArray() {
    // Arrange
    JsonArray createJsonArrayWithOneElementResult =
        JsonArrayTestFactory.createJsonArrayWithOneElement();
    createJsonArrayWithOneElementResult.add(JsonArrayTestFactory.createJsonArrayWithNumbers());

    // Act and Assert
    assertFalse(
        createJsonArrayWithOneElementResult.contains(JsonArrayTestFactory.createJsonArray()));
  }

  /**
   * Test {@link JsonArray#contains(JsonElement)}.
   *
   * <ul>
   *   <li>When createJsonArrayWithNumbers.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#contains(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.contains(JsonElement)"})
  public void testContains_whenCreateJsonArrayWithNumbers_thenReturnTrue() {
    // Arrange
    JsonArray createJsonArrayWithOneElementResult =
        JsonArrayTestFactory.createJsonArrayWithOneElement();
    createJsonArrayWithOneElementResult.add(JsonArrayTestFactory.createJsonArrayWithNumbers());

    // Act and Assert
    assertTrue(
        createJsonArrayWithOneElementResult.contains(
            JsonArrayTestFactory.createJsonArrayWithNumbers()));
  }

  /**
   * Test {@link JsonArray#contains(JsonElement)}.
   *
   * <ul>
   *   <li>When {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#contains(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.contains(JsonElement)"})
  public void testContains_whenJsonPrimitiveWithBoolIsTrue() {
    // Arrange
    JsonArray createJsonArrayWithOneElementResult =
        JsonArrayTestFactory.createJsonArrayWithOneElement();
    createJsonArrayWithOneElementResult.add(JsonArrayTestFactory.createJsonArrayWithOneElement());

    // Act
    boolean actualContainsResult =
        createJsonArrayWithOneElementResult.contains(new JsonPrimitive(true));

    // Assert
    assertFalse(actualContainsResult);
  }

  /**
   * Test {@link JsonArray#contains(JsonElement)}.
   *
   * <ul>
   *   <li>When {@link JsonPrimitive#JsonPrimitive(String)} with string is {@code singleElement}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#contains(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.contains(JsonElement)"})
  public void testContains_whenJsonPrimitiveWithStringIsSingleElement_thenReturnTrue() {
    // Arrange
    JsonArray createJsonArrayWithOneElementResult =
        JsonArrayTestFactory.createJsonArrayWithOneElement();
    createJsonArrayWithOneElementResult.add(JsonArrayTestFactory.createJsonArrayWithOneElement());

    // Act
    boolean actualContainsResult =
        createJsonArrayWithOneElementResult.contains(new JsonPrimitive("singleElement"));

    // Assert
    assertTrue(actualContainsResult);
  }

  /**
   * Test {@link JsonArray#size()}.
   *
   * <p>Method under test: {@link JsonArray#size()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonArray.size()"})
  public void testSize() {
    // Arrange, Act and Assert
    assertEquals(1, JsonArrayTestFactory.createJsonArrayWithOneElement().size());
  }

  /**
   * Test {@link JsonArray#isEmpty()}.
   *
   * <ul>
   *   <li>Given createEmptyJsonArray.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#isEmpty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.isEmpty()"})
  public void testIsEmpty_givenCreateEmptyJsonArray_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(JsonArrayTestFactory.createEmptyJsonArray().isEmpty());
  }

  /**
   * Test {@link JsonArray#isEmpty()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithOneElement.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#isEmpty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.isEmpty()"})
  public void testIsEmpty_givenCreateJsonArrayWithOneElement_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(JsonArrayTestFactory.createJsonArrayWithOneElement().isEmpty());
  }

  /**
   * Test {@link JsonArray#iterator()}.
   *
   * <p>Method under test: {@link JsonArray#iterator()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Iterator JsonArray.iterator()"})
  public void testIterator() {
    // Arrange and Act
    Iterator<JsonElement> actualIteratorResult =
        JsonArrayTestFactory.createJsonArrayWithOneElement().iterator();

    // Assert
    JsonElement nextResult = actualIteratorResult.next();
    assertTrue(nextResult instanceof JsonPrimitive);
    assertTrue(nextResult.getAsNumber() instanceof LazilyParsedNumber);
    assertEquals("singleElement", nextResult.getAsString());
    assertEquals('s', nextResult.getAsCharacter());
    assertFalse(nextResult.getAsBoolean());
    assertFalse(nextResult.isJsonArray());
    assertFalse(nextResult.isJsonNull());
    assertFalse(nextResult.isJsonObject());
    assertFalse(((JsonPrimitive) nextResult).isBoolean());
    assertFalse(((JsonPrimitive) nextResult).isNumber());
    assertFalse(actualIteratorResult.hasNext());
    assertTrue(nextResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) nextResult).isString());
    assertSame(nextResult, nextResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonArray#get(int)}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithNumbers.
   *   <li>Then return {@link JsonPrimitive}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#get(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonArray.get(int)"})
  public void testGet_givenCreateJsonArrayWithNumbers_thenReturnJsonPrimitive() {
    // Arrange and Act
    JsonElement actualGetResult = JsonArrayTestFactory.createJsonArrayWithNumbers().get(1);

    // Assert
    assertTrue(actualGetResult instanceof JsonPrimitive);
    assertEquals("2", actualGetResult.getAsString());
    assertEquals('2', actualGetResult.getAsCharacter());
    assertEquals(2, actualGetResult.getAsInt());
    assertEquals(2, actualGetResult.getAsNumber().intValue());
    assertEquals(2.0d, actualGetResult.getAsDouble(), 0.0);
    assertEquals(2.0f, actualGetResult.getAsFloat(), 0.0f);
    assertEquals(2L, actualGetResult.getAsLong());
    assertEquals((byte) 2, actualGetResult.getAsByte());
    assertEquals((short) 2, actualGetResult.getAsShort());
    assertFalse(actualGetResult.getAsBoolean());
    assertFalse(actualGetResult.isJsonArray());
    assertFalse(actualGetResult.isJsonNull());
    assertFalse(actualGetResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualGetResult).isBoolean());
    assertFalse(((JsonPrimitive) actualGetResult).isString());
    assertTrue(actualGetResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualGetResult).isNumber());
    assertEquals(new BigDecimal("2"), actualGetResult.getAsBigDecimal());
    JsonPrimitive actualAsJsonPrimitive = actualGetResult.getAsJsonPrimitive();
    assertSame(actualGetResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonArray#getAsNumber()}.
   *
   * <ul>
   *   <li>Given createEmptyJsonArray add createJsonArrayWithOneElement.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsNumber()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number JsonArray.getAsNumber()"})
  public void testGetAsNumber_givenCreateEmptyJsonArrayAddCreateJsonArrayWithOneElement() {
    // Arrange
    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();
    createEmptyJsonArrayResult.add(JsonArrayTestFactory.createJsonArrayWithOneElement());

    // Act
    Number actualAsNumber = createEmptyJsonArrayResult.getAsNumber();

    // Assert
    assertTrue(actualAsNumber instanceof LazilyParsedNumber);
    assertEquals("singleElement", actualAsNumber.toString());
  }

  /**
   * Test {@link JsonArray#getAsNumber()}.
   *
   * <ul>
   *   <li>Given createEmptyJsonArray add valueOf one.
   *   <li>Then return intValue is one.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsNumber()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number JsonArray.getAsNumber()"})
  public void testGetAsNumber_givenCreateEmptyJsonArrayAddValueOfOne_thenReturnIntValueIsOne() {
    // Arrange
    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();
    Integer number = Integer.valueOf(1);
    createEmptyJsonArrayResult.add(number);

    // Act
    Number actualAsNumber = createEmptyJsonArrayResult.getAsNumber();

    // Assert
    assertEquals(1, actualAsNumber.intValue());
    assertSame(number, actualAsNumber);
  }

  /**
   * Test {@link JsonArray#getAsNumber()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithNumbers.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsNumber()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number JsonArray.getAsNumber()"})
  public void testGetAsNumber_givenCreateJsonArrayWithNumbers_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> JsonArrayTestFactory.createJsonArrayWithNumbers().getAsNumber());
  }

  /**
   * Test {@link JsonArray#getAsNumber()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithOneElement.
   *   <li>Then return {@link LazilyParsedNumber}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsNumber()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number JsonArray.getAsNumber()"})
  public void testGetAsNumber_givenCreateJsonArrayWithOneElement_thenReturnLazilyParsedNumber() {
    // Arrange and Act
    Number actualAsNumber = JsonArrayTestFactory.createJsonArrayWithOneElement().getAsNumber();

    // Assert
    assertTrue(actualAsNumber instanceof LazilyParsedNumber);
    assertEquals("singleElement", actualAsNumber.toString());
  }

  /**
   * Test {@link JsonArray#getAsNumber()}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsNumber()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number JsonArray.getAsNumber()"})
  public void testGetAsNumber_thenThrowUnsupportedOperationException() {
    // Arrange
    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();
    createEmptyJsonArrayResult.add((Number) null);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class, () -> createEmptyJsonArrayResult.getAsNumber());
  }

  /**
   * Test {@link JsonArray#getAsString()}.
   *
   * <ul>
   *   <li>Given createEmptyJsonArray add createJsonArrayWithOneElement.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonArray.getAsString()"})
  public void testGetAsString_givenCreateEmptyJsonArrayAddCreateJsonArrayWithOneElement() {
    // Arrange
    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();
    createEmptyJsonArrayResult.add(JsonArrayTestFactory.createJsonArrayWithOneElement());

    // Act and Assert
    assertEquals("singleElement", createEmptyJsonArrayResult.getAsString());
  }

  /**
   * Test {@link JsonArray#getAsString()}.
   *
   * <ul>
   *   <li>Given createEmptyJsonArray add {@code true}.
   *   <li>Then return {@link Boolean#TRUE} toString.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonArray.getAsString()"})
  public void testGetAsString_givenCreateEmptyJsonArrayAddTrue_thenReturnTrueToString() {
    // Arrange
    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();
    createEmptyJsonArrayResult.add(true);

    // Act and Assert
    assertEquals(Boolean.TRUE.toString(), createEmptyJsonArrayResult.getAsString());
  }

  /**
   * Test {@link JsonArray#getAsString()}.
   *
   * <ul>
   *   <li>Given createEmptyJsonArray add valueOf one.
   *   <li>Then return {@code 1}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonArray.getAsString()"})
  public void testGetAsString_givenCreateEmptyJsonArrayAddValueOfOne_thenReturn1() {
    // Arrange
    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();
    createEmptyJsonArrayResult.add(Integer.valueOf(1));

    // Act and Assert
    assertEquals("1", createEmptyJsonArrayResult.getAsString());
  }

  /**
   * Test {@link JsonArray#getAsString()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithNumbers.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonArray.getAsString()"})
  public void testGetAsString_givenCreateJsonArrayWithNumbers_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> JsonArrayTestFactory.createJsonArrayWithNumbers().getAsString());
  }

  /**
   * Test {@link JsonArray#getAsString()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithOneElement.
   *   <li>Then return {@code singleElement}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonArray.getAsString()"})
  public void testGetAsString_givenCreateJsonArrayWithOneElement_thenReturnSingleElement() {
    // Arrange, Act and Assert
    assertEquals(
        "singleElement", JsonArrayTestFactory.createJsonArrayWithOneElement().getAsString());
  }

  /**
   * Test {@link JsonArray#getAsString()}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JsonArray.getAsString()"})
  public void testGetAsString_thenThrowUnsupportedOperationException() {
    // Arrange
    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();
    createEmptyJsonArrayResult.add((Number) null);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class, () -> createEmptyJsonArrayResult.getAsString());
  }

  /**
   * Test {@link JsonArray#getAsDouble()}.
   *
   * <ul>
   *   <li>Given createEmptyJsonArray add createEmptyJsonArray.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JsonArray.getAsDouble()"})
  public void testGetAsDouble_givenCreateEmptyJsonArrayAddCreateEmptyJsonArray_thenReturnOne() {
    // Arrange
    JsonArray element = JsonArrayTestFactory.createEmptyJsonArray();
    element.add(Integer.valueOf(1));

    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();
    createEmptyJsonArrayResult.add(element);

    // Act and Assert
    assertEquals(1.0d, createEmptyJsonArrayResult.getAsDouble(), 0.0);
  }

  /**
   * Test {@link JsonArray#getAsDouble()}.
   *
   * <ul>
   *   <li>Given createEmptyJsonArray add valueOf one.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JsonArray.getAsDouble()"})
  public void testGetAsDouble_givenCreateEmptyJsonArrayAddValueOfOne_thenReturnOne() {
    // Arrange
    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();
    createEmptyJsonArrayResult.add(Integer.valueOf(1));

    // Act and Assert
    assertEquals(1.0d, createEmptyJsonArrayResult.getAsDouble(), 0.0);
  }

  /**
   * Test {@link JsonArray#getAsDouble()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithNumbers.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JsonArray.getAsDouble()"})
  public void testGetAsDouble_givenCreateJsonArrayWithNumbers_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> JsonArrayTestFactory.createJsonArrayWithNumbers().getAsDouble());
  }

  /**
   * Test {@link JsonArray#getAsDouble()}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JsonArray.getAsDouble()"})
  public void testGetAsDouble_thenThrowUnsupportedOperationException() {
    // Arrange
    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();
    createEmptyJsonArrayResult.add((Number) null);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class, () -> createEmptyJsonArrayResult.getAsDouble());
  }

  /**
   * Test {@link JsonArray#getAsBigDecimal()}.
   *
   * <ul>
   *   <li>Given createEmptyJsonArray add createEmptyJsonArray.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsBigDecimal()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigDecimal JsonArray.getAsBigDecimal()"})
  public void testGetAsBigDecimal_givenCreateEmptyJsonArrayAddCreateEmptyJsonArray() {
    // Arrange
    JsonArray element = JsonArrayTestFactory.createEmptyJsonArray();
    element.add(Integer.valueOf(1));

    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();
    createEmptyJsonArrayResult.add(element);

    // Act
    BigDecimal actualAsBigDecimal = createEmptyJsonArrayResult.getAsBigDecimal();

    // Assert
    assertEquals(new BigDecimal("1"), actualAsBigDecimal);
  }

  /**
   * Test {@link JsonArray#getAsBigDecimal()}.
   *
   * <ul>
   *   <li>Then return {@link BigDecimal#BigDecimal(String)} with {@code 1}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsBigDecimal()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigDecimal JsonArray.getAsBigDecimal()"})
  public void testGetAsBigDecimal_thenReturnBigDecimalWith1() {
    // Arrange
    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();
    createEmptyJsonArrayResult.add(Integer.valueOf(1));

    // Act
    BigDecimal actualAsBigDecimal = createEmptyJsonArrayResult.getAsBigDecimal();

    // Assert
    assertEquals(new BigDecimal("1"), actualAsBigDecimal);
  }

  /**
   * Test {@link JsonArray#getAsBigDecimal()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsBigDecimal()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigDecimal JsonArray.getAsBigDecimal()"})
  public void testGetAsBigDecimal_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> JsonArrayTestFactory.createJsonArrayWithNumbers().getAsBigDecimal());
  }

  /**
   * Test {@link JsonArray#getAsBigDecimal()}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsBigDecimal()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigDecimal JsonArray.getAsBigDecimal()"})
  public void testGetAsBigDecimal_thenThrowUnsupportedOperationException() {
    // Arrange
    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();
    createEmptyJsonArrayResult.add((Number) null);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class, () -> createEmptyJsonArrayResult.getAsBigDecimal());
  }

  /**
   * Test {@link JsonArray#getAsBigInteger()}.
   *
   * <ul>
   *   <li>Given createEmptyJsonArray add {@code A}.
   *   <li>Then return toString is {@code 65}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsBigInteger()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigInteger JsonArray.getAsBigInteger()"})
  public void testGetAsBigInteger_givenCreateEmptyJsonArrayAddA_thenReturnToStringIs65() {
    // Arrange
    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();
    createEmptyJsonArrayResult.add((byte) 'A');

    // Act
    BigInteger actualAsBigInteger = createEmptyJsonArrayResult.getAsBigInteger();

    // Assert
    Iterator<JsonElement> iteratorResult = createEmptyJsonArrayResult.iterator();
    JsonElement nextResult = iteratorResult.next();
    assertTrue(nextResult instanceof JsonPrimitive);
    assertEquals("65", actualAsBigInteger.toString());
    BigInteger sqrtResult = actualAsBigInteger.sqrt();
    assertEquals("8", sqrtResult.toString());
    assertEquals(3, sqrtResult.getLowestSetBit());
    assertFalse(iteratorResult.hasNext());
    BigInteger bigInteger = BigInteger.TWO;
    assertEquals(bigInteger, sqrtResult.sqrt());
    assertEquals(bigInteger, nextResult.getAsBigInteger().sqrt().sqrt());
  }

  /**
   * Test {@link JsonArray#getAsBigInteger()}.
   *
   * <ul>
   *   <li>Given createEmptyJsonArray add one.
   *   <li>Then return {@link BigInteger#ONE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsBigInteger()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigInteger JsonArray.getAsBigInteger()"})
  public void testGetAsBigInteger_givenCreateEmptyJsonArrayAddOne_thenReturnOne() {
    // Arrange
    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();
    createEmptyJsonArrayResult.add(1L);

    // Act
    BigInteger actualAsBigInteger = createEmptyJsonArrayResult.getAsBigInteger();

    // Assert
    Iterator<JsonElement> iteratorResult = createEmptyJsonArrayResult.iterator();
    JsonElement nextResult = iteratorResult.next();
    assertTrue(nextResult instanceof JsonPrimitive);
    assertFalse(iteratorResult.hasNext());
    BigInteger bigInteger = BigInteger.ONE;
    assertSame(bigInteger, actualAsBigInteger);
    assertSame(bigInteger, nextResult.getAsBigInteger());
  }

  /**
   * Test {@link JsonArray#getAsBigInteger()}.
   *
   * <ul>
   *   <li>Given createEmptyJsonArray add one.
   *   <li>Then return {@link BigInteger#ONE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsBigInteger()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigInteger JsonArray.getAsBigInteger()"})
  public void testGetAsBigInteger_givenCreateEmptyJsonArrayAddOne_thenReturnOne2() {
    // Arrange
    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();
    createEmptyJsonArrayResult.add((short) 1);

    // Act
    BigInteger actualAsBigInteger = createEmptyJsonArrayResult.getAsBigInteger();

    // Assert
    Iterator<JsonElement> iteratorResult = createEmptyJsonArrayResult.iterator();
    JsonElement nextResult = iteratorResult.next();
    assertTrue(nextResult instanceof JsonPrimitive);
    assertFalse(iteratorResult.hasNext());
    BigInteger bigInteger = BigInteger.ONE;
    assertSame(bigInteger, actualAsBigInteger);
    assertSame(bigInteger, nextResult.getAsBigInteger());
  }

  /**
   * Test {@link JsonArray#getAsBigInteger()}.
   *
   * <ul>
   *   <li>Then return sqrt signum is valueOf one.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsBigInteger()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigInteger JsonArray.getAsBigInteger()"})
  public void testGetAsBigInteger_thenReturnSqrtSignumIsValueOfOne() {
    // Arrange
    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();
    Integer number = Integer.valueOf(1);
    createEmptyJsonArrayResult.add(number);

    // Act and Assert
    BigInteger sqrtResult = createEmptyJsonArrayResult.getAsBigInteger().sqrt();
    assertSame(number, sqrtResult.signum());
    BigInteger sqrtResult2 = sqrtResult.sqrt();
    assertSame(number, sqrtResult2.signum());
    BigInteger sqrtResult3 = sqrtResult2.sqrt();
    assertSame(number, sqrtResult3.signum());
    BigInteger sqrtResult4 = sqrtResult3.sqrt();
    assertSame(number, sqrtResult4.signum());
    BigInteger sqrtResult5 = sqrtResult4.sqrt();
    assertSame(number, sqrtResult5.signum());
    BigInteger sqrtResult6 = sqrtResult5.sqrt();
    assertSame(number, sqrtResult6.signum());
    BigInteger sqrtResult7 = sqrtResult6.sqrt();
    assertSame(number, sqrtResult7.signum());
    assertSame(number, sqrtResult7.sqrt().signum());
  }

  /**
   * Test {@link JsonArray#getAsBigInteger()}.
   *
   * <ul>
   *   <li>Then return toString is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsBigInteger()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigInteger JsonArray.getAsBigInteger()"})
  public void testGetAsBigInteger_thenReturnToStringIs42() {
    // Arrange
    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();
    createEmptyJsonArrayResult.add(new LazilyParsedNumber("42"));

    // Act
    BigInteger actualAsBigInteger = createEmptyJsonArrayResult.getAsBigInteger();

    // Assert
    assertEquals("42", actualAsBigInteger.toString());
    BigInteger sqrtResult = actualAsBigInteger.sqrt();
    assertEquals("6", sqrtResult.toString());
    assertEquals(1, actualAsBigInteger.getLowestSetBit());
    assertEquals(1, sqrtResult.getLowestSetBit());
  }

  /**
   * Test {@link JsonArray#getAsBigInteger()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsBigInteger()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigInteger JsonArray.getAsBigInteger()"})
  public void testGetAsBigInteger_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> JsonArrayTestFactory.createJsonArrayWithNumbers().getAsBigInteger());
  }

  /**
   * Test {@link JsonArray#getAsBigInteger()}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsBigInteger()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigInteger JsonArray.getAsBigInteger()"})
  public void testGetAsBigInteger_thenThrowUnsupportedOperationException() {
    // Arrange
    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();
    createEmptyJsonArrayResult.add((Number) null);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class, () -> createEmptyJsonArrayResult.getAsBigInteger());
  }

  /**
   * Test {@link JsonArray#getAsFloat()}.
   *
   * <ul>
   *   <li>Given createEmptyJsonArray add createEmptyJsonArray.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsFloat()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"float JsonArray.getAsFloat()"})
  public void testGetAsFloat_givenCreateEmptyJsonArrayAddCreateEmptyJsonArray_thenReturnOne() {
    // Arrange
    JsonArray element = JsonArrayTestFactory.createEmptyJsonArray();
    element.add(Integer.valueOf(1));

    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();
    createEmptyJsonArrayResult.add(element);

    // Act and Assert
    assertEquals(1.0f, createEmptyJsonArrayResult.getAsFloat(), 0.0f);
  }

  /**
   * Test {@link JsonArray#getAsFloat()}.
   *
   * <ul>
   *   <li>Given createEmptyJsonArray add valueOf one.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsFloat()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"float JsonArray.getAsFloat()"})
  public void testGetAsFloat_givenCreateEmptyJsonArrayAddValueOfOne_thenReturnOne() {
    // Arrange
    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();
    createEmptyJsonArrayResult.add(Integer.valueOf(1));

    // Act and Assert
    assertEquals(1.0f, createEmptyJsonArrayResult.getAsFloat(), 0.0f);
  }

  /**
   * Test {@link JsonArray#getAsFloat()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithNumbers.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsFloat()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"float JsonArray.getAsFloat()"})
  public void testGetAsFloat_givenCreateJsonArrayWithNumbers_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> JsonArrayTestFactory.createJsonArrayWithNumbers().getAsFloat());
  }

  /**
   * Test {@link JsonArray#getAsFloat()}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsFloat()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"float JsonArray.getAsFloat()"})
  public void testGetAsFloat_thenThrowUnsupportedOperationException() {
    // Arrange
    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();
    createEmptyJsonArrayResult.add((Number) null);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class, () -> createEmptyJsonArrayResult.getAsFloat());
  }

  /**
   * Test {@link JsonArray#getAsLong()}.
   *
   * <ul>
   *   <li>Given createEmptyJsonArray add createEmptyJsonArray.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsLong()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JsonArray.getAsLong()"})
  public void testGetAsLong_givenCreateEmptyJsonArrayAddCreateEmptyJsonArray_thenReturnOne() {
    // Arrange
    JsonArray element = JsonArrayTestFactory.createEmptyJsonArray();
    element.add(Integer.valueOf(1));

    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();
    createEmptyJsonArrayResult.add(element);

    // Act and Assert
    assertEquals(1L, createEmptyJsonArrayResult.getAsLong());
  }

  /**
   * Test {@link JsonArray#getAsLong()}.
   *
   * <ul>
   *   <li>Given createEmptyJsonArray add valueOf one.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsLong()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JsonArray.getAsLong()"})
  public void testGetAsLong_givenCreateEmptyJsonArrayAddValueOfOne_thenReturnOne() {
    // Arrange
    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();
    createEmptyJsonArrayResult.add(Integer.valueOf(1));

    // Act and Assert
    assertEquals(1L, createEmptyJsonArrayResult.getAsLong());
  }

  /**
   * Test {@link JsonArray#getAsLong()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithNumbers.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsLong()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JsonArray.getAsLong()"})
  public void testGetAsLong_givenCreateJsonArrayWithNumbers_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> JsonArrayTestFactory.createJsonArrayWithNumbers().getAsLong());
  }

  /**
   * Test {@link JsonArray#getAsLong()}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsLong()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JsonArray.getAsLong()"})
  public void testGetAsLong_thenThrowUnsupportedOperationException() {
    // Arrange
    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();
    createEmptyJsonArrayResult.add((Number) null);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> createEmptyJsonArrayResult.getAsLong());
  }

  /**
   * Test {@link JsonArray#getAsInt()}.
   *
   * <ul>
   *   <li>Given createEmptyJsonArray add createEmptyJsonArray.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsInt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonArray.getAsInt()"})
  public void testGetAsInt_givenCreateEmptyJsonArrayAddCreateEmptyJsonArray_thenReturnOne() {
    // Arrange
    JsonArray element = JsonArrayTestFactory.createEmptyJsonArray();
    element.add(Integer.valueOf(1));

    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();
    createEmptyJsonArrayResult.add(element);

    // Act and Assert
    assertEquals(1, createEmptyJsonArrayResult.getAsInt());
  }

  /**
   * Test {@link JsonArray#getAsInt()}.
   *
   * <ul>
   *   <li>Given createEmptyJsonArray add valueOf one.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsInt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonArray.getAsInt()"})
  public void testGetAsInt_givenCreateEmptyJsonArrayAddValueOfOne_thenReturnOne() {
    // Arrange
    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();
    createEmptyJsonArrayResult.add(Integer.valueOf(1));

    // Act and Assert
    assertEquals(1, createEmptyJsonArrayResult.getAsInt());
  }

  /**
   * Test {@link JsonArray#getAsInt()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithNumbers.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsInt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonArray.getAsInt()"})
  public void testGetAsInt_givenCreateJsonArrayWithNumbers_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> JsonArrayTestFactory.createJsonArrayWithNumbers().getAsInt());
  }

  /**
   * Test {@link JsonArray#getAsInt()}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsInt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonArray.getAsInt()"})
  public void testGetAsInt_thenThrowUnsupportedOperationException() {
    // Arrange
    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();
    createEmptyJsonArrayResult.add((Number) null);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> createEmptyJsonArrayResult.getAsInt());
  }

  /**
   * Test {@link JsonArray#getAsByte()}.
   *
   * <ul>
   *   <li>Given createEmptyJsonArray add createEmptyJsonArray.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsByte()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte JsonArray.getAsByte()"})
  public void testGetAsByte_givenCreateEmptyJsonArrayAddCreateEmptyJsonArray_thenReturnOne() {
    // Arrange
    JsonArray element = JsonArrayTestFactory.createEmptyJsonArray();
    element.add(Integer.valueOf(1));

    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();
    createEmptyJsonArrayResult.add(element);

    // Act and Assert
    assertEquals((byte) 1, createEmptyJsonArrayResult.getAsByte());
  }

  /**
   * Test {@link JsonArray#getAsByte()}.
   *
   * <ul>
   *   <li>Given createEmptyJsonArray add valueOf one.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsByte()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte JsonArray.getAsByte()"})
  public void testGetAsByte_givenCreateEmptyJsonArrayAddValueOfOne_thenReturnOne() {
    // Arrange
    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();
    createEmptyJsonArrayResult.add(Integer.valueOf(1));

    // Act and Assert
    assertEquals((byte) 1, createEmptyJsonArrayResult.getAsByte());
  }

  /**
   * Test {@link JsonArray#getAsByte()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithNumbers.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsByte()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte JsonArray.getAsByte()"})
  public void testGetAsByte_givenCreateJsonArrayWithNumbers_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> JsonArrayTestFactory.createJsonArrayWithNumbers().getAsByte());
  }

  /**
   * Test {@link JsonArray#getAsByte()}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsByte()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte JsonArray.getAsByte()"})
  public void testGetAsByte_thenThrowUnsupportedOperationException() {
    // Arrange
    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();
    createEmptyJsonArrayResult.add((Number) null);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> createEmptyJsonArrayResult.getAsByte());
  }

  /**
   * Test {@link JsonArray#getAsCharacter()}.
   *
   * <ul>
   *   <li>Given createEmptyJsonArray add createJsonArrayWithOneElement.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsCharacter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"char JsonArray.getAsCharacter()"})
  public void testGetAsCharacter_givenCreateEmptyJsonArrayAddCreateJsonArrayWithOneElement() {
    // Arrange
    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();
    createEmptyJsonArrayResult.add(JsonArrayTestFactory.createJsonArrayWithOneElement());

    // Act and Assert
    assertEquals('s', createEmptyJsonArrayResult.getAsCharacter());
  }

  /**
   * Test {@link JsonArray#getAsCharacter()}.
   *
   * <ul>
   *   <li>Given createEmptyJsonArray add {@code true}.
   *   <li>Then return {@code t}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsCharacter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"char JsonArray.getAsCharacter()"})
  public void testGetAsCharacter_givenCreateEmptyJsonArrayAddTrue_thenReturnT() {
    // Arrange
    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();
    createEmptyJsonArrayResult.add(true);

    // Act and Assert
    assertEquals('t', createEmptyJsonArrayResult.getAsCharacter());
  }

  /**
   * Test {@link JsonArray#getAsCharacter()}.
   *
   * <ul>
   *   <li>Given createEmptyJsonArray add valueOf one.
   *   <li>Then return {@code 1}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsCharacter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"char JsonArray.getAsCharacter()"})
  public void testGetAsCharacter_givenCreateEmptyJsonArrayAddValueOfOne_thenReturn1() {
    // Arrange
    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();
    createEmptyJsonArrayResult.add(Integer.valueOf(1));

    // Act and Assert
    assertEquals('1', createEmptyJsonArrayResult.getAsCharacter());
  }

  /**
   * Test {@link JsonArray#getAsCharacter()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithOneElement.
   *   <li>Then return {@code s}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsCharacter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"char JsonArray.getAsCharacter()"})
  public void testGetAsCharacter_givenCreateJsonArrayWithOneElement_thenReturnS() {
    // Arrange, Act and Assert
    assertEquals('s', JsonArrayTestFactory.createJsonArrayWithOneElement().getAsCharacter());
  }

  /**
   * Test {@link JsonArray#getAsCharacter()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsCharacter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"char JsonArray.getAsCharacter()"})
  public void testGetAsCharacter_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> JsonArrayTestFactory.createJsonArrayWithNumbers().getAsCharacter());
  }

  /**
   * Test {@link JsonArray#getAsCharacter()}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsCharacter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"char JsonArray.getAsCharacter()"})
  public void testGetAsCharacter_thenThrowUnsupportedOperationException() {
    // Arrange
    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();
    createEmptyJsonArrayResult.add((Number) null);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class, () -> createEmptyJsonArrayResult.getAsCharacter());
  }

  /**
   * Test {@link JsonArray#getAsShort()}.
   *
   * <ul>
   *   <li>Given createEmptyJsonArray add createEmptyJsonArray.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsShort()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"short JsonArray.getAsShort()"})
  public void testGetAsShort_givenCreateEmptyJsonArrayAddCreateEmptyJsonArray_thenReturnOne() {
    // Arrange
    JsonArray element = JsonArrayTestFactory.createEmptyJsonArray();
    element.add(Integer.valueOf(1));

    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();
    createEmptyJsonArrayResult.add(element);

    // Act and Assert
    assertEquals((short) 1, createEmptyJsonArrayResult.getAsShort());
  }

  /**
   * Test {@link JsonArray#getAsShort()}.
   *
   * <ul>
   *   <li>Given createEmptyJsonArray add valueOf one.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsShort()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"short JsonArray.getAsShort()"})
  public void testGetAsShort_givenCreateEmptyJsonArrayAddValueOfOne_thenReturnOne() {
    // Arrange
    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();
    createEmptyJsonArrayResult.add(Integer.valueOf(1));

    // Act and Assert
    assertEquals((short) 1, createEmptyJsonArrayResult.getAsShort());
  }

  /**
   * Test {@link JsonArray#getAsShort()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithNumbers.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsShort()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"short JsonArray.getAsShort()"})
  public void testGetAsShort_givenCreateJsonArrayWithNumbers_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> JsonArrayTestFactory.createJsonArrayWithNumbers().getAsShort());
  }

  /**
   * Test {@link JsonArray#getAsShort()}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsShort()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"short JsonArray.getAsShort()"})
  public void testGetAsShort_thenThrowUnsupportedOperationException() {
    // Arrange
    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();
    createEmptyJsonArrayResult.add((Number) null);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class, () -> createEmptyJsonArrayResult.getAsShort());
  }

  /**
   * Test {@link JsonArray#getAsBoolean()}.
   *
   * <ul>
   *   <li>Given createEmptyJsonArray add createJsonArrayWithOneElement.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.getAsBoolean()"})
  public void testGetAsBoolean_givenCreateEmptyJsonArrayAddCreateJsonArrayWithOneElement() {
    // Arrange
    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();
    createEmptyJsonArrayResult.add(JsonArrayTestFactory.createJsonArrayWithOneElement());

    // Act and Assert
    assertFalse(createEmptyJsonArrayResult.getAsBoolean());
  }

  /**
   * Test {@link JsonArray#getAsBoolean()}.
   *
   * <ul>
   *   <li>Given createEmptyJsonArray add {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.getAsBoolean()"})
  public void testGetAsBoolean_givenCreateEmptyJsonArrayAddTrue_thenReturnTrue() {
    // Arrange
    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();
    createEmptyJsonArrayResult.add(true);

    // Act and Assert
    assertTrue(createEmptyJsonArrayResult.getAsBoolean());
  }

  /**
   * Test {@link JsonArray#getAsBoolean()}.
   *
   * <ul>
   *   <li>Given createEmptyJsonArray add valueOf one.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.getAsBoolean()"})
  public void testGetAsBoolean_givenCreateEmptyJsonArrayAddValueOfOne_thenReturnFalse() {
    // Arrange
    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();
    createEmptyJsonArrayResult.add(Integer.valueOf(1));

    // Act and Assert
    assertFalse(createEmptyJsonArrayResult.getAsBoolean());
  }

  /**
   * Test {@link JsonArray#getAsBoolean()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithNumbers.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.getAsBoolean()"})
  public void testGetAsBoolean_givenCreateJsonArrayWithNumbers_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> JsonArrayTestFactory.createJsonArrayWithNumbers().getAsBoolean());
  }

  /**
   * Test {@link JsonArray#getAsBoolean()}.
   *
   * <ul>
   *   <li>Given createJsonArrayWithOneElement.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.getAsBoolean()"})
  public void testGetAsBoolean_givenCreateJsonArrayWithOneElement_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(JsonArrayTestFactory.createJsonArrayWithOneElement().getAsBoolean());
  }

  /**
   * Test {@link JsonArray#getAsBoolean()}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.getAsBoolean()"})
  public void testGetAsBoolean_thenThrowUnsupportedOperationException() {
    // Arrange
    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();
    createEmptyJsonArrayResult.add((Number) null);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class, () -> createEmptyJsonArrayResult.getAsBoolean());
  }

  /**
   * Test {@link JsonArray#asList()}.
   *
   * <p>Method under test: {@link JsonArray#asList()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List JsonArray.asList()"})
  public void testAsList() {
    // Arrange and Act
    List<JsonElement> actualAsListResult =
        JsonArrayTestFactory.createJsonArrayWithOneElement().asList();

    // Assert
    assertEquals(1, actualAsListResult.size());
    JsonElement getResult = actualAsListResult.get(0);
    assertTrue(getResult instanceof JsonPrimitive);
    assertTrue(getResult.getAsNumber() instanceof LazilyParsedNumber);
    assertEquals("singleElement", getResult.getAsString());
    assertEquals('s', getResult.getAsCharacter());
    assertFalse(getResult.getAsBoolean());
    assertFalse(getResult.isJsonArray());
    assertFalse(getResult.isJsonNull());
    assertFalse(getResult.isJsonObject());
    assertFalse(((JsonPrimitive) getResult).isBoolean());
    assertFalse(((JsonPrimitive) getResult).isNumber());
    assertTrue(getResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) getResult).isString());
    assertSame(getResult, getResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonArray#equals(Object)}, and {@link JsonArray#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link JsonArray#equals(Object)}
   *   <li>{@link JsonArray#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.equals(Object)", "int JsonArray.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    JsonArray createJsonArrayWithOneElementResult =
        JsonArrayTestFactory.createJsonArrayWithOneElement();
    JsonArray createJsonArrayWithOneElementResult2 =
        JsonArrayTestFactory.createJsonArrayWithOneElement();

    // Act and Assert
    assertEquals(createJsonArrayWithOneElementResult, createJsonArrayWithOneElementResult2);
    assertEquals(
        createJsonArrayWithOneElementResult.hashCode(),
        createJsonArrayWithOneElementResult2.hashCode());
  }

  /**
   * Test {@link JsonArray#equals(Object)}, and {@link JsonArray#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link JsonArray#equals(Object)}
   *   <li>{@link JsonArray#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.equals(Object)", "int JsonArray.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    JsonArray createJsonArrayWithOneElementResult =
        JsonArrayTestFactory.createJsonArrayWithOneElement();

    // Act and Assert
    assertEquals(createJsonArrayWithOneElementResult, createJsonArrayWithOneElementResult);
    int expectedHashCodeResult = createJsonArrayWithOneElementResult.hashCode();
    assertEquals(expectedHashCodeResult, createJsonArrayWithOneElementResult.hashCode());
  }

  /**
   * Test {@link JsonArray#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.equals(Object)", "int JsonArray.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    JsonArray createJsonArrayWithNumbersResult = JsonArrayTestFactory.createJsonArrayWithNumbers();

    // Act and Assert
    assertNotEquals(
        createJsonArrayWithNumbersResult, JsonArrayTestFactory.createJsonArrayWithOneElement());
  }

  /**
   * Test {@link JsonArray#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.equals(Object)", "int JsonArray.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    JsonArray createJsonArrayWithOneElementResult =
        JsonArrayTestFactory.createJsonArrayWithOneElement();

    JsonArray createEmptyJsonArrayResult = JsonArrayTestFactory.createEmptyJsonArray();
    createEmptyJsonArrayResult.add(JsonArrayTestFactory.createJsonArrayWithOneElement());

    // Act and Assert
    assertNotEquals(createJsonArrayWithOneElementResult, createEmptyJsonArrayResult);
  }

  /**
   * Test {@link JsonArray#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.equals(Object)", "int JsonArray.hashCode()"})
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(JsonArrayTestFactory.createJsonArrayWithOneElement(), null);
  }

  /**
   * Test {@link JsonArray#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.equals(Object)", "int JsonArray.hashCode()"})
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(
        JsonArrayTestFactory.createJsonArrayWithOneElement(), "Different type to JsonArray");
  }
}
