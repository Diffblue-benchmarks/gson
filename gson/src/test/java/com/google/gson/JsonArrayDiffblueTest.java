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
   *   <li>Then iterator next return {@link JsonPrimitive}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#deepCopy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonArray JsonArray.deepCopy()"})
  public void testDeepCopy_thenIteratorNextReturnJsonPrimitive() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(true);

    // Act
    JsonArray actualDeepCopyResult = jsonArray.deepCopy();

    // Assert
    Iterator<JsonElement> iteratorResult = actualDeepCopyResult.iterator();
    JsonElement nextResult = iteratorResult.next();
    assertTrue(nextResult instanceof JsonPrimitive);
    assertEquals('t', actualDeepCopyResult.getAsCharacter());
    assertEquals('t', nextResult.getAsCharacter());
    assertEquals(1, actualDeepCopyResult.size());
    assertFalse(((JsonPrimitive) nextResult).isNumber());
    assertFalse(((JsonPrimitive) nextResult).isString());
    assertFalse(iteratorResult.hasNext());
    assertTrue(actualDeepCopyResult.getAsBoolean());
    assertTrue(nextResult.getAsBoolean());
    assertTrue(nextResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) nextResult).isBoolean());
    assertEquals(Boolean.TRUE.toString(), actualDeepCopyResult.getAsString());
    assertEquals(Boolean.TRUE.toString(), nextResult.getAsString());
    assertSame(nextResult, nextResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonArray#deepCopy()}.
   *
   * <ul>
   *   <li>Then return {@link JsonArray#JsonArray(int)} with capacity is three.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#deepCopy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonArray JsonArray.deepCopy()"})
  public void testDeepCopy_thenReturnJsonArrayWithCapacityIsThree() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);

    // Act
    JsonArray actualDeepCopyResult = jsonArray.deepCopy();

    // Assert
    assertEquals(jsonArray, actualDeepCopyResult);
  }

  /**
   * Test {@link JsonArray#add(Boolean)} with {@code bool}.
   *
   * <ul>
   *   <li>Then {@link JsonArray#JsonArray(int)} with capacity is three iterator next {@link
   *       JsonPrimitive}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#add(Boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonArray.add(Boolean)"})
  public void testAddWithBool_thenJsonArrayWithCapacityIsThreeIteratorNextJsonPrimitive() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);

    // Act
    jsonArray.add(true);

    // Assert
    Iterator<JsonElement> iteratorResult = jsonArray.iterator();
    JsonElement nextResult = iteratorResult.next();
    assertTrue(nextResult instanceof JsonPrimitive);
    assertEquals('t', jsonArray.getAsCharacter());
    assertEquals('t', nextResult.getAsCharacter());
    assertFalse(nextResult.isJsonNull());
    assertFalse(((JsonPrimitive) nextResult).isNumber());
    assertFalse(((JsonPrimitive) nextResult).isString());
    assertFalse(iteratorResult.hasNext());
    assertTrue(jsonArray.getAsBoolean());
    assertTrue(nextResult.getAsBoolean());
    assertTrue(nextResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) nextResult).isBoolean());
    assertEquals(Boolean.TRUE.toString(), jsonArray.getAsString());
    assertEquals(Boolean.TRUE.toString(), nextResult.getAsString());
    assertSame(nextResult, nextResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link JsonArray#add(Boolean)} with {@code bool}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@link JsonArray#JsonArray(int)} with capacity is three iterator next {@link
   *       JsonNull}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#add(Boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonArray.add(Boolean)"})
  public void testAddWithBool_whenNull_thenJsonArrayWithCapacityIsThreeIteratorNextJsonNull() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);

    // Act
    jsonArray.add((Boolean) null);

    // Assert
    Iterator<JsonElement> iteratorResult = jsonArray.iterator();
    JsonElement nextResult = iteratorResult.next();
    assertTrue(nextResult instanceof JsonNull);
    assertFalse(nextResult.isJsonPrimitive());
    assertFalse(iteratorResult.hasNext());
    assertTrue(nextResult.isJsonNull());
    assertSame(nextResult, nextResult.getAsJsonNull());
  }

  /**
   * Test {@link JsonArray#add(Character)} with {@code character}.
   *
   * <ul>
   *   <li>Then {@link JsonArray#JsonArray(int)} with capacity is three iterator next {@link
   *       JsonNull}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#add(Character)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonArray.add(Character)"})
  public void testAddWithCharacter_thenJsonArrayWithCapacityIsThreeIteratorNextJsonNull() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);

    // Act
    jsonArray.add((Character) null);

    // Assert
    Iterator<JsonElement> iteratorResult = jsonArray.iterator();
    JsonElement nextResult = iteratorResult.next();
    assertTrue(nextResult instanceof JsonNull);
    assertFalse(nextResult.isJsonPrimitive());
    assertFalse(iteratorResult.hasNext());
    assertTrue(nextResult.isJsonNull());
    assertSame(nextResult, nextResult.getAsJsonNull());
  }

  /**
   * Test {@link JsonArray#add(Character)} with {@code character}.
   *
   * <ul>
   *   <li>Then {@link JsonArray#JsonArray(int)} with capacity is three iterator next {@link
   *       JsonPrimitive}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#add(Character)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonArray.add(Character)"})
  public void testAddWithCharacter_thenJsonArrayWithCapacityIsThreeIteratorNextJsonPrimitive() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);

    // Act
    jsonArray.add('A');

    // Assert
    Iterator<JsonElement> iteratorResult = jsonArray.iterator();
    assertTrue(iteratorResult.next() instanceof JsonPrimitive);
    Number asNumber = jsonArray.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("A", jsonArray.getAsString());
    assertEquals("A", asNumber.toString());
    assertEquals('A', jsonArray.getAsCharacter());
    assertFalse(jsonArray.getAsBoolean());
    assertFalse(iteratorResult.hasNext());
  }

  /**
   * Test {@link JsonArray#add(JsonElement)} with {@code element}.
   *
   * <ul>
   *   <li>When {@link JsonNull#INSTANCE}.
   *   <li>Then not {@link JsonArray#JsonArray(int)} with capacity is three Empty.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#add(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonArray.add(JsonElement)"})
  public void testAddWithElement_whenInstance_thenNotJsonArrayWithCapacityIsThreeEmpty() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);

    // Act
    jsonArray.add(JsonNull.INSTANCE);

    // Assert
    assertFalse(jsonArray.isEmpty());
    Iterator<JsonElement> iteratorResult = jsonArray.iterator();
    JsonElement actualNextResult = iteratorResult.next();
    assertFalse(iteratorResult.hasNext());
    assertSame(JsonNull.INSTANCE, actualNextResult);
    assertEquals(1, jsonArray.size());
  }

  /**
   * Test {@link JsonArray#add(JsonElement)} with {@code element}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@link JsonArray#JsonArray(int)} with capacity is three iterator next {@link
   *       JsonNull}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#add(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonArray.add(JsonElement)"})
  public void testAddWithElement_whenNull_thenJsonArrayWithCapacityIsThreeIteratorNextJsonNull() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);

    // Act
    jsonArray.add((JsonElement) null);

    // Assert
    Iterator<JsonElement> iteratorResult = jsonArray.iterator();
    JsonElement nextResult = iteratorResult.next();
    assertTrue(nextResult instanceof JsonNull);
    assertFalse(nextResult.isJsonArray());
    assertFalse(nextResult.isJsonObject());
    assertFalse(nextResult.isJsonPrimitive());
    assertFalse(iteratorResult.hasNext());
    assertTrue(nextResult.isJsonNull());
    assertSame(nextResult, nextResult.getAsJsonNull());
  }

  /**
   * Test {@link JsonArray#add(Number)} with {@code number}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@link JsonArray#JsonArray(int)} with capacity is three iterator next {@link
   *       JsonNull}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#add(Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonArray.add(Number)"})
  public void testAddWithNumber_whenNull_thenJsonArrayWithCapacityIsThreeIteratorNextJsonNull() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);

    // Act
    jsonArray.add((Number) null);

    // Assert
    Iterator<JsonElement> iteratorResult = jsonArray.iterator();
    JsonElement nextResult = iteratorResult.next();
    assertTrue(nextResult instanceof JsonNull);
    assertEquals(1, jsonArray.size());
    assertFalse(nextResult.isJsonPrimitive());
    assertFalse(iteratorResult.hasNext());
    assertTrue(nextResult.isJsonNull());
    assertSame(nextResult, nextResult.getAsJsonNull());
  }

  /**
   * Test {@link JsonArray#add(Number)} with {@code number}.
   *
   * <ul>
   *   <li>When valueOf one.
   *   <li>Then {@link JsonArray#JsonArray(int)} with capacity is three AsString is {@code 1}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#add(Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonArray.add(Number)"})
  public void testAddWithNumber_whenValueOfOne_thenJsonArrayWithCapacityIsThreeAsStringIs1() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    Integer number = Integer.valueOf(1);

    // Act
    jsonArray.add(number);

    // Assert
    assertEquals("1", jsonArray.getAsString());
    assertEquals('1', jsonArray.getAsCharacter());
    assertEquals(1, jsonArray.getAsInt());
    assertEquals(1.0d, jsonArray.getAsDouble(), 0.0);
    assertEquals(1.0f, jsonArray.getAsFloat(), 0.0f);
    assertEquals(1L, jsonArray.getAsLong());
    assertEquals((byte) 1, jsonArray.getAsByte());
    assertEquals((short) 1, jsonArray.getAsShort());
    assertFalse(jsonArray.getAsBoolean());
    assertEquals(new BigDecimal("1"), jsonArray.getAsBigDecimal());
    assertSame(number, jsonArray.getAsNumber());
    assertSame(number, jsonArray.size());
  }

  /**
   * Test {@link JsonArray#add(String)} with {@code string}.
   *
   * <ul>
   *   <li>Then {@link JsonArray#JsonArray(int)} with capacity is three iterator next {@link
   *       JsonPrimitive}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#add(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonArray.add(String)"})
  public void testAddWithString_thenJsonArrayWithCapacityIsThreeIteratorNextJsonPrimitive() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);

    // Act
    jsonArray.add("String");

    // Assert
    Iterator<JsonElement> iteratorResult = jsonArray.iterator();
    assertTrue(iteratorResult.next() instanceof JsonPrimitive);
    Number asNumber = jsonArray.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("String", jsonArray.getAsString());
    assertEquals("String", asNumber.toString());
    assertEquals('S', jsonArray.getAsCharacter());
    assertFalse(jsonArray.getAsBoolean());
    assertFalse(iteratorResult.hasNext());
  }

  /**
   * Test {@link JsonArray#add(String)} with {@code string}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@link JsonArray#JsonArray(int)} with capacity is three iterator next {@link
   *       JsonNull}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#add(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonArray.add(String)"})
  public void testAddWithString_whenNull_thenJsonArrayWithCapacityIsThreeIteratorNextJsonNull() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);

    // Act
    jsonArray.add((String) null);

    // Assert
    Iterator<JsonElement> iteratorResult = jsonArray.iterator();
    JsonElement nextResult = iteratorResult.next();
    assertTrue(nextResult instanceof JsonNull);
    assertFalse(nextResult.isJsonPrimitive());
    assertFalse(iteratorResult.hasNext());
    assertTrue(nextResult.isJsonNull());
    assertSame(nextResult, nextResult.getAsJsonNull());
  }

  /**
   * Test {@link JsonArray#set(int, JsonElement)}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code false}.
   *   <li>Then return AsCharacter is {@code t}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#set(int, JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonArray.set(int, JsonElement)"})
  public void testSet_givenJsonArrayWithCapacityIsThreeAddFalse_thenReturnAsCharacterIsT() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(false);
    jsonArray.add(true);

    // Act
    JsonElement actualSetResult = jsonArray.set(1, JsonNull.INSTANCE);

    // Assert
    assertTrue(actualSetResult instanceof JsonPrimitive);
    assertEquals('t', actualSetResult.getAsCharacter());
    assertFalse(actualSetResult.isJsonArray());
    assertFalse(actualSetResult.isJsonNull());
    assertFalse(actualSetResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualSetResult).isNumber());
    assertFalse(((JsonPrimitive) actualSetResult).isString());
    assertTrue(actualSetResult.getAsBoolean());
    assertTrue(actualSetResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualSetResult).isBoolean());
    assertEquals(Boolean.TRUE.toString(), actualSetResult.getAsString());
    JsonPrimitive actualAsJsonPrimitive = actualSetResult.getAsJsonPrimitive();
    assertSame(actualSetResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonArray#remove(JsonElement)} with {@code element}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#remove(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.remove(JsonElement)"})
  public void testRemoveWithElement_givenJsonArrayWithCapacityIsThreeAddInstance() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(JsonNull.INSTANCE);
    JsonArray element = new JsonArray(3);

    // Act
    boolean actualRemoveResult = jsonArray.remove(element);

    // Assert
    assertEquals(0, element.size());
    assertFalse(actualRemoveResult);
    assertFalse(element.iterator().hasNext());
    assertTrue(element.isEmpty());
  }

  /**
   * Test {@link JsonArray#remove(JsonElement)} with {@code element}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#remove(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.remove(JsonElement)"})
  public void testRemoveWithElement_givenJsonArrayWithCapacityIsThreeAddInstance2() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(true);
    jsonArray.add(JsonNull.INSTANCE);
    JsonArray element = new JsonArray(3);

    // Act
    boolean actualRemoveResult = jsonArray.remove(element);

    // Assert
    assertEquals(0, element.size());
    assertFalse(actualRemoveResult);
    assertFalse(element.iterator().hasNext());
    assertTrue(element.isEmpty());
  }

  /**
   * Test {@link JsonArray#remove(JsonElement)} with {@code element}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#remove(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.remove(JsonElement)"})
  public void testRemoveWithElement_givenJsonArrayWithCapacityIsThreeAddTrue() {
    // Arrange
    JsonArray element = new JsonArray(3);
    element.add(true);

    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(element);
    JsonArray element2 = new JsonArray(3);

    // Act
    boolean actualRemoveResult = jsonArray.remove(element2);

    // Assert
    assertEquals(0, element2.size());
    assertFalse(actualRemoveResult);
    assertFalse(element2.iterator().hasNext());
    assertTrue(element2.isEmpty());
  }

  /**
   * Test {@link JsonArray#remove(JsonElement)} with {@code element}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three.
   *   <li>When {@link JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#remove(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.remove(JsonElement)"})
  public void testRemoveWithElement_givenJsonArrayWithCapacityIsThree_whenInstance() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);

    // Act
    boolean actualRemoveResult = jsonArray.remove(JsonNull.INSTANCE);

    // Assert
    assertEquals(0, jsonArray.size());
    assertFalse(actualRemoveResult);
    assertFalse(jsonArray.iterator().hasNext());
    assertTrue(jsonArray.isEmpty());
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
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new JsonArray(3));

    // Act
    boolean actualRemoveResult = jsonArray.remove(new JsonArray(3));

    // Assert
    assertTrue(actualRemoveResult);
  }

  /**
   * Test {@link JsonArray#remove(JsonElement)} with {@code element}.
   *
   * <ul>
   *   <li>When {@link JsonNull#INSTANCE}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#remove(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.remove(JsonElement)"})
  public void testRemoveWithElement_whenInstance_thenReturnTrue() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(JsonNull.INSTANCE);

    // Act
    boolean actualRemoveResult = jsonArray.remove(JsonNull.INSTANCE);

    // Assert
    assertEquals(0, jsonArray.size());
    assertFalse(jsonArray.iterator().hasNext());
    assertTrue(jsonArray.isEmpty());
    assertTrue(actualRemoveResult);
  }

  /**
   * Test {@link JsonArray#contains(JsonElement)}.
   *
   * <ul>
   *   <li>Given {@code false}.
   *   <li>When {@link JsonArray#JsonArray(int)} with capacity is three add {@code false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#contains(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.contains(JsonElement)"})
  public void testContains_givenFalse_whenJsonArrayWithCapacityIsThreeAddFalse_thenReturnFalse() {
    // Arrange
    JsonArray element = new JsonArray(3);
    element.add(true);

    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(element);

    JsonArray element2 = new JsonArray(3);
    element2.add(false);

    // Act
    boolean actualContainsResult = jsonArray.contains(element2);

    // Assert
    assertFalse(actualContainsResult);
  }

  /**
   * Test {@link JsonArray#contains(JsonElement)}.
   *
   * <ul>
   *   <li>Given {@link JsonNull#INSTANCE}.
   *   <li>When {@link JsonArray#JsonArray(int)} with capacity is three add {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#contains(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.contains(JsonElement)"})
  public void testContains_givenInstance_whenJsonArrayWithCapacityIsThreeAddInstance() {
    // Arrange
    JsonArray element = new JsonArray(3);
    element.add(true);

    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(element);

    JsonArray element2 = new JsonArray(3);
    element2.add(JsonNull.INSTANCE);

    // Act
    boolean actualContainsResult = jsonArray.contains(element2);

    // Assert
    assertFalse(actualContainsResult);
  }

  /**
   * Test {@link JsonArray#contains(JsonElement)}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#contains(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.contains(JsonElement)"})
  public void testContains_givenJsonArrayWithCapacityIsThreeAddInstance() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(JsonNull.INSTANCE);

    // Act
    boolean actualContainsResult = jsonArray.contains(new JsonArray(3));

    // Assert
    assertFalse(actualContainsResult);
  }

  /**
   * Test {@link JsonArray#contains(JsonElement)}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#contains(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.contains(JsonElement)"})
  public void testContains_givenJsonArrayWithCapacityIsThreeAddInstance2() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(true);
    jsonArray.add(JsonNull.INSTANCE);

    // Act
    boolean actualContainsResult = jsonArray.contains(new JsonArray(3));

    // Assert
    assertFalse(actualContainsResult);
  }

  /**
   * Test {@link JsonArray#contains(JsonElement)}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#contains(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.contains(JsonElement)"})
  public void testContains_givenJsonArrayWithCapacityIsThreeAddTrue() {
    // Arrange
    JsonArray element = new JsonArray(3);
    element.add(true);

    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(element);

    // Act
    boolean actualContainsResult = jsonArray.contains(new JsonArray(3));

    // Assert
    assertFalse(actualContainsResult);
  }

  /**
   * Test {@link JsonArray#contains(JsonElement)}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three.
   *   <li>When {@link JsonNull#INSTANCE}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#contains(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.contains(JsonElement)"})
  public void testContains_givenJsonArrayWithCapacityIsThree_whenInstance_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new JsonArray(3).contains(JsonNull.INSTANCE));
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
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new JsonArray(3));

    // Act
    boolean actualContainsResult = jsonArray.contains(new JsonArray(3));

    // Assert
    assertTrue(actualContainsResult);
  }

  /**
   * Test {@link JsonArray#contains(JsonElement)}.
   *
   * <ul>
   *   <li>When {@link JsonNull#INSTANCE}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#contains(JsonElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.contains(JsonElement)"})
  public void testContains_whenInstance_thenReturnTrue() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(JsonNull.INSTANCE);

    // Act and Assert
    assertTrue(jsonArray.contains(JsonNull.INSTANCE));
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
    assertEquals(0, new JsonArray(3).size());
  }

  /**
   * Test {@link JsonArray#isEmpty()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code true}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#isEmpty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.isEmpty()"})
  public void testIsEmpty_givenJsonArrayWithCapacityIsThreeAddTrue_thenReturnFalse() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(true);

    // Act and Assert
    assertFalse(jsonArray.isEmpty());
  }

  /**
   * Test {@link JsonArray#isEmpty()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#isEmpty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.isEmpty()"})
  public void testIsEmpty_givenJsonArrayWithCapacityIsThree_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new JsonArray(3).isEmpty());
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
    // Arrange, Act and Assert
    assertFalse(new JsonArray(3).iterator().hasNext());
  }

  /**
   * Test {@link JsonArray#get(int)}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code false}.
   *   <li>Then return {@link JsonPrimitive}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#get(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonElement JsonArray.get(int)"})
  public void testGet_givenJsonArrayWithCapacityIsThreeAddFalse_thenReturnJsonPrimitive() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(false);
    jsonArray.add(true);

    // Act
    JsonElement actualGetResult = jsonArray.get(1);

    // Assert
    assertTrue(actualGetResult instanceof JsonPrimitive);
    assertEquals('t', actualGetResult.getAsCharacter());
    assertFalse(actualGetResult.isJsonArray());
    assertFalse(actualGetResult.isJsonNull());
    assertFalse(actualGetResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualGetResult).isNumber());
    assertFalse(((JsonPrimitive) actualGetResult).isString());
    assertTrue(actualGetResult.getAsBoolean());
    assertTrue(actualGetResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualGetResult).isBoolean());
    assertEquals(Boolean.TRUE.toString(), actualGetResult.getAsString());
    JsonPrimitive actualAsJsonPrimitive = actualGetResult.getAsJsonPrimitive();
    assertSame(actualGetResult, actualAsJsonPrimitive);
  }

  /**
   * Test {@link JsonArray#getAsNumber()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@link
   *       JsonArray#JsonArray(int)} with capacity is three.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsNumber()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number JsonArray.getAsNumber()"})
  public void testGetAsNumber_givenJsonArrayWithCapacityIsThreeAddJsonArrayWithCapacityIsThree() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new JsonArray(3));

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsNumber());
  }

  /**
   * Test {@link JsonArray#getAsNumber()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsNumber()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number JsonArray.getAsNumber()"})
  public void testGetAsNumber_givenJsonArrayWithCapacityIsThree_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> new JsonArray(3).getAsNumber());
  }

  /**
   * Test {@link JsonArray#getAsNumber()}.
   *
   * <ul>
   *   <li>Then return intValue is one.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsNumber()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number JsonArray.getAsNumber()"})
  public void testGetAsNumber_thenReturnIntValueIsOne() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    Integer number = Integer.valueOf(1);
    jsonArray.add(number);

    // Act
    Number actualAsNumber = jsonArray.getAsNumber();

    // Assert
    assertEquals(1, actualAsNumber.intValue());
    assertSame(number, actualAsNumber);
  }

  /**
   * Test {@link JsonArray#getAsNumber()}.
   *
   * <ul>
   *   <li>Then return {@link LazilyParsedNumber}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsNumber()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number JsonArray.getAsNumber()"})
  public void testGetAsNumber_thenReturnLazilyParsedNumber() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add('\u0001');

    // Act
    Number actualAsNumber = jsonArray.getAsNumber();

    // Assert
    assertTrue(actualAsNumber instanceof LazilyParsedNumber);
    assertEquals("\u0001", actualAsNumber.toString());
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
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(JsonNull.INSTANCE);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> jsonArray.getAsNumber());
  }

  /**
   * Test {@link JsonArray#getAsString()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@link
   *       JsonArray#JsonArray(int)} with capacity is three.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonArray.getAsString()"})
  public void testGetAsString_givenJsonArrayWithCapacityIsThreeAddJsonArrayWithCapacityIsThree() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new JsonArray(3));

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsString());
  }

  /**
   * Test {@link JsonArray#getAsString()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code true}.
   *   <li>Then return {@link Boolean#TRUE} toString.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonArray.getAsString()"})
  public void testGetAsString_givenJsonArrayWithCapacityIsThreeAddTrue_thenReturnTrueToString() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(true);

    // Act and Assert
    assertEquals(Boolean.TRUE.toString(), jsonArray.getAsString());
  }

  /**
   * Test {@link JsonArray#getAsString()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add valueOf one.
   *   <li>Then return {@code 1}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonArray.getAsString()"})
  public void testGetAsString_givenJsonArrayWithCapacityIsThreeAddValueOfOne_thenReturn1() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(Integer.valueOf(1));

    // Act and Assert
    assertEquals("1", jsonArray.getAsString());
  }

  /**
   * Test {@link JsonArray#getAsString()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonArray.getAsString()"})
  public void testGetAsString_givenJsonArrayWithCapacityIsThree_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> new JsonArray(3).getAsString());
  }

  /**
   * Test {@link JsonArray#getAsString()}.
   *
   * <ul>
   *   <li>Then return start of heading.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonArray.getAsString()"})
  public void testGetAsString_thenReturnStartOfHeading() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add('\u0001');

    // Act and Assert
    assertEquals("\u0001", jsonArray.getAsString());
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
  @MethodsUnderTest({"String JsonArray.getAsString()"})
  public void testGetAsString_thenThrowUnsupportedOperationException() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(JsonNull.INSTANCE);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> jsonArray.getAsString());
  }

  /**
   * Test {@link JsonArray#getAsDouble()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code 42}.
   *   <li>Then return forty-two.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JsonArray.getAsDouble()"})
  public void testGetAsDouble_givenJsonArrayWithCapacityIsThreeAdd42_thenReturnFortyTwo() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add("42");

    // Act and Assert
    assertEquals(42.0d, jsonArray.getAsDouble(), 0.0);
  }

  /**
   * Test {@link JsonArray#getAsDouble()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@link
   *       JsonArray#JsonArray(int)} with capacity is three.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JsonArray.getAsDouble()"})
  public void testGetAsDouble_givenJsonArrayWithCapacityIsThreeAddJsonArrayWithCapacityIsThree() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new JsonArray(3));

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsDouble());
  }

  /**
   * Test {@link JsonArray#getAsDouble()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add valueOf one.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JsonArray.getAsDouble()"})
  public void testGetAsDouble_givenJsonArrayWithCapacityIsThreeAddValueOfOne_thenReturnOne() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(Integer.valueOf(1));

    // Act and Assert
    assertEquals(1.0d, jsonArray.getAsDouble(), 0.0);
  }

  /**
   * Test {@link JsonArray#getAsDouble()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JsonArray.getAsDouble()"})
  public void testGetAsDouble_givenJsonArrayWithCapacityIsThree_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> new JsonArray(3).getAsDouble());
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
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(JsonNull.INSTANCE);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> jsonArray.getAsDouble());
  }

  /**
   * Test {@link JsonArray#getAsBigDecimal()}.
   *
   * <p>Method under test: {@link JsonArray#getAsBigDecimal()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigDecimal JsonArray.getAsBigDecimal()"})
  public void testGetAsBigDecimal() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new JsonArray(3));

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsBigDecimal());
  }

  /**
   * Test {@link JsonArray#getAsBigDecimal()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsBigDecimal()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigDecimal JsonArray.getAsBigDecimal()"})
  public void testGetAsBigDecimal_givenJsonArrayWithCapacityIsThree() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> new JsonArray(3).getAsBigDecimal());
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
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(Integer.valueOf(1));

    // Act
    BigDecimal actualAsBigDecimal = jsonArray.getAsBigDecimal();

    // Assert
    assertEquals(new BigDecimal("1"), actualAsBigDecimal);
  }

  /**
   * Test {@link JsonArray#getAsBigDecimal()}.
   *
   * <ul>
   *   <li>Then return {@link BigDecimal#BigDecimal(String)} with {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsBigDecimal()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigDecimal JsonArray.getAsBigDecimal()"})
  public void testGetAsBigDecimal_thenReturnBigDecimalWith42() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add("42");

    // Act
    BigDecimal actualAsBigDecimal = jsonArray.getAsBigDecimal();

    // Assert
    assertEquals(new BigDecimal("42"), actualAsBigDecimal);
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
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(JsonNull.INSTANCE);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> jsonArray.getAsBigDecimal());
  }

  /**
   * Test {@link JsonArray#getAsBigInteger()}.
   *
   * <p>Method under test: {@link JsonArray#getAsBigInteger()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigInteger JsonArray.getAsBigInteger()"})
  public void testGetAsBigInteger() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new JsonArray(3));

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsBigInteger());
  }

  /**
   * Test {@link JsonArray#getAsBigInteger()}.
   *
   * <p>Method under test: {@link JsonArray#getAsBigInteger()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigInteger JsonArray.getAsBigInteger()"})
  public void testGetAsBigInteger2() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new LazilyParsedNumber("42"));

    // Act
    BigInteger actualAsBigInteger = jsonArray.getAsBigInteger();

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
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsBigInteger()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigInteger JsonArray.getAsBigInteger()"})
  public void testGetAsBigInteger_givenJsonArrayWithCapacityIsThree() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> new JsonArray(3).getAsBigInteger());
  }

  /**
   * Test {@link JsonArray#getAsBigInteger()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsBigInteger()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigInteger JsonArray.getAsBigInteger()"})
  public void testGetAsBigInteger_givenJsonArrayWithCapacityIsThreeAdd42() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add("42");

    // Act
    BigInteger actualAsBigInteger = jsonArray.getAsBigInteger();

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
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code A}.
   *   <li>Then return toString is {@code 65}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsBigInteger()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigInteger JsonArray.getAsBigInteger()"})
  public void testGetAsBigInteger_givenJsonArrayWithCapacityIsThreeAddA_thenReturnToStringIs65() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add((byte) 'A');

    // Act
    BigInteger actualAsBigInteger = jsonArray.getAsBigInteger();

    // Assert
    Iterator<JsonElement> iteratorResult = jsonArray.iterator();
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
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add one.
   *   <li>Then return {@link BigInteger#ONE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsBigInteger()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigInteger JsonArray.getAsBigInteger()"})
  public void testGetAsBigInteger_givenJsonArrayWithCapacityIsThreeAddOne_thenReturnOne() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(1L);

    // Act
    BigInteger actualAsBigInteger = jsonArray.getAsBigInteger();

    // Assert
    Iterator<JsonElement> iteratorResult = jsonArray.iterator();
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
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add one.
   *   <li>Then return {@link BigInteger#ONE}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsBigInteger()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigInteger JsonArray.getAsBigInteger()"})
  public void testGetAsBigInteger_givenJsonArrayWithCapacityIsThreeAddOne_thenReturnOne2() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add((short) 1);

    // Act
    BigInteger actualAsBigInteger = jsonArray.getAsBigInteger();

    // Assert
    Iterator<JsonElement> iteratorResult = jsonArray.iterator();
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
    JsonArray jsonArray = new JsonArray(3);
    Integer number = Integer.valueOf(1);
    jsonArray.add(number);

    // Act and Assert
    BigInteger sqrtResult = jsonArray.getAsBigInteger().sqrt();
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
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(JsonNull.INSTANCE);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> jsonArray.getAsBigInteger());
  }

  /**
   * Test {@link JsonArray#getAsFloat()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code 42}.
   *   <li>Then return forty-two.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsFloat()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"float JsonArray.getAsFloat()"})
  public void testGetAsFloat_givenJsonArrayWithCapacityIsThreeAdd42_thenReturnFortyTwo() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add("42");

    // Act and Assert
    assertEquals(42.0f, jsonArray.getAsFloat(), 0.0f);
  }

  /**
   * Test {@link JsonArray#getAsFloat()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@link
   *       JsonArray#JsonArray(int)} with capacity is three.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsFloat()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"float JsonArray.getAsFloat()"})
  public void testGetAsFloat_givenJsonArrayWithCapacityIsThreeAddJsonArrayWithCapacityIsThree() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new JsonArray(3));

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsFloat());
  }

  /**
   * Test {@link JsonArray#getAsFloat()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add valueOf one.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsFloat()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"float JsonArray.getAsFloat()"})
  public void testGetAsFloat_givenJsonArrayWithCapacityIsThreeAddValueOfOne_thenReturnOne() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(Integer.valueOf(1));

    // Act and Assert
    assertEquals(1.0f, jsonArray.getAsFloat(), 0.0f);
  }

  /**
   * Test {@link JsonArray#getAsFloat()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsFloat()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"float JsonArray.getAsFloat()"})
  public void testGetAsFloat_givenJsonArrayWithCapacityIsThree_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> new JsonArray(3).getAsFloat());
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
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(JsonNull.INSTANCE);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> jsonArray.getAsFloat());
  }

  /**
   * Test {@link JsonArray#getAsLong()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code 42}.
   *   <li>Then return forty-two.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsLong()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JsonArray.getAsLong()"})
  public void testGetAsLong_givenJsonArrayWithCapacityIsThreeAdd42_thenReturnFortyTwo() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add("42");

    // Act and Assert
    assertEquals(42L, jsonArray.getAsLong());
  }

  /**
   * Test {@link JsonArray#getAsLong()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@link
   *       JsonArray#JsonArray(int)} with capacity is three.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsLong()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JsonArray.getAsLong()"})
  public void testGetAsLong_givenJsonArrayWithCapacityIsThreeAddJsonArrayWithCapacityIsThree() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new JsonArray(3));

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsLong());
  }

  /**
   * Test {@link JsonArray#getAsLong()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add valueOf one.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsLong()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JsonArray.getAsLong()"})
  public void testGetAsLong_givenJsonArrayWithCapacityIsThreeAddValueOfOne_thenReturnOne() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(Integer.valueOf(1));

    // Act and Assert
    assertEquals(1L, jsonArray.getAsLong());
  }

  /**
   * Test {@link JsonArray#getAsLong()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsLong()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JsonArray.getAsLong()"})
  public void testGetAsLong_givenJsonArrayWithCapacityIsThree_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> new JsonArray(3).getAsLong());
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
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(JsonNull.INSTANCE);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> jsonArray.getAsLong());
  }

  /**
   * Test {@link JsonArray#getAsInt()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code 42}.
   *   <li>Then return forty-two.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsInt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonArray.getAsInt()"})
  public void testGetAsInt_givenJsonArrayWithCapacityIsThreeAdd42_thenReturnFortyTwo() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add("42");

    // Act and Assert
    assertEquals(42, jsonArray.getAsInt());
  }

  /**
   * Test {@link JsonArray#getAsInt()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@link
   *       JsonArray#JsonArray(int)} with capacity is three.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsInt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonArray.getAsInt()"})
  public void testGetAsInt_givenJsonArrayWithCapacityIsThreeAddJsonArrayWithCapacityIsThree() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new JsonArray(3));

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsInt());
  }

  /**
   * Test {@link JsonArray#getAsInt()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add valueOf one.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsInt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonArray.getAsInt()"})
  public void testGetAsInt_givenJsonArrayWithCapacityIsThreeAddValueOfOne_thenReturnOne() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(Integer.valueOf(1));

    // Act and Assert
    assertEquals(1, jsonArray.getAsInt());
  }

  /**
   * Test {@link JsonArray#getAsInt()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsInt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JsonArray.getAsInt()"})
  public void testGetAsInt_givenJsonArrayWithCapacityIsThree_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> new JsonArray(3).getAsInt());
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
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(JsonNull.INSTANCE);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> jsonArray.getAsInt());
  }

  /**
   * Test {@link JsonArray#getAsByte()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code 42}.
   *   <li>Then return {@code *}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsByte()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte JsonArray.getAsByte()"})
  public void testGetAsByte_givenJsonArrayWithCapacityIsThreeAdd42_thenReturnAsterisk() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add("42");

    // Act and Assert
    assertEquals('*', jsonArray.getAsByte());
  }

  /**
   * Test {@link JsonArray#getAsByte()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@link
   *       JsonArray#JsonArray(int)} with capacity is three.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsByte()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte JsonArray.getAsByte()"})
  public void testGetAsByte_givenJsonArrayWithCapacityIsThreeAddJsonArrayWithCapacityIsThree() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new JsonArray(3));

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsByte());
  }

  /**
   * Test {@link JsonArray#getAsByte()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add valueOf one.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsByte()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte JsonArray.getAsByte()"})
  public void testGetAsByte_givenJsonArrayWithCapacityIsThreeAddValueOfOne_thenReturnOne() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(Integer.valueOf(1));

    // Act and Assert
    assertEquals((byte) 1, jsonArray.getAsByte());
  }

  /**
   * Test {@link JsonArray#getAsByte()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsByte()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte JsonArray.getAsByte()"})
  public void testGetAsByte_givenJsonArrayWithCapacityIsThree_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> new JsonArray(3).getAsByte());
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
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(JsonNull.INSTANCE);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> jsonArray.getAsByte());
  }

  /**
   * Test {@link JsonArray#getAsCharacter()}.
   *
   * <p>Method under test: {@link JsonArray#getAsCharacter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"char JsonArray.getAsCharacter()"})
  public void testGetAsCharacter() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new JsonArray(3));

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsCharacter());
  }

  /**
   * Test {@link JsonArray#getAsCharacter()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsCharacter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"char JsonArray.getAsCharacter()"})
  public void testGetAsCharacter_givenJsonArrayWithCapacityIsThree() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> new JsonArray(3).getAsCharacter());
  }

  /**
   * Test {@link JsonArray#getAsCharacter()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code true}.
   *   <li>Then return {@code t}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsCharacter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"char JsonArray.getAsCharacter()"})
  public void testGetAsCharacter_givenJsonArrayWithCapacityIsThreeAddTrue_thenReturnT() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(true);

    // Act and Assert
    assertEquals('t', jsonArray.getAsCharacter());
  }

  /**
   * Test {@link JsonArray#getAsCharacter()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add valueOf one.
   *   <li>Then return {@code 1}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsCharacter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"char JsonArray.getAsCharacter()"})
  public void testGetAsCharacter_givenJsonArrayWithCapacityIsThreeAddValueOfOne_thenReturn1() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(Integer.valueOf(1));

    // Act and Assert
    assertEquals('1', jsonArray.getAsCharacter());
  }

  /**
   * Test {@link JsonArray#getAsCharacter()}.
   *
   * <ul>
   *   <li>Then return start of heading.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsCharacter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"char JsonArray.getAsCharacter()"})
  public void testGetAsCharacter_thenReturnStartOfHeading() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add('\u0001');

    // Act and Assert
    assertEquals('\u0001', jsonArray.getAsCharacter());
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
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(JsonNull.INSTANCE);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> jsonArray.getAsCharacter());
  }

  /**
   * Test {@link JsonArray#getAsShort()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code 42}.
   *   <li>Then return forty-two.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsShort()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"short JsonArray.getAsShort()"})
  public void testGetAsShort_givenJsonArrayWithCapacityIsThreeAdd42_thenReturnFortyTwo() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add("42");

    // Act and Assert
    assertEquals((short) 42, jsonArray.getAsShort());
  }

  /**
   * Test {@link JsonArray#getAsShort()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@link
   *       JsonArray#JsonArray(int)} with capacity is three.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsShort()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"short JsonArray.getAsShort()"})
  public void testGetAsShort_givenJsonArrayWithCapacityIsThreeAddJsonArrayWithCapacityIsThree() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new JsonArray(3));

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsShort());
  }

  /**
   * Test {@link JsonArray#getAsShort()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add valueOf one.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsShort()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"short JsonArray.getAsShort()"})
  public void testGetAsShort_givenJsonArrayWithCapacityIsThreeAddValueOfOne_thenReturnOne() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(Integer.valueOf(1));

    // Act and Assert
    assertEquals((short) 1, jsonArray.getAsShort());
  }

  /**
   * Test {@link JsonArray#getAsShort()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsShort()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"short JsonArray.getAsShort()"})
  public void testGetAsShort_givenJsonArrayWithCapacityIsThree_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> new JsonArray(3).getAsShort());
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
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(JsonNull.INSTANCE);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> jsonArray.getAsShort());
  }

  /**
   * Test {@link JsonArray#getAsBoolean()}.
   *
   * <p>Method under test: {@link JsonArray#getAsBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.getAsBoolean()"})
  public void testGetAsBoolean() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new JsonArray(3));

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonArray.getAsBoolean());
  }

  /**
   * Test {@link JsonArray#getAsBoolean()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.getAsBoolean()"})
  public void testGetAsBoolean_givenJsonArrayWithCapacityIsThree() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> new JsonArray(3).getAsBoolean());
  }

  /**
   * Test {@link JsonArray#getAsBoolean()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add start of heading.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.getAsBoolean()"})
  public void testGetAsBoolean_givenJsonArrayWithCapacityIsThreeAddStartOfHeading() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add('\u0001');

    // Act and Assert
    assertFalse(jsonArray.getAsBoolean());
  }

  /**
   * Test {@link JsonArray#getAsBoolean()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.getAsBoolean()"})
  public void testGetAsBoolean_givenJsonArrayWithCapacityIsThreeAddTrue_thenReturnTrue() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(true);

    // Act and Assert
    assertTrue(jsonArray.getAsBoolean());
  }

  /**
   * Test {@link JsonArray#getAsBoolean()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add valueOf one.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JsonArray#getAsBoolean()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonArray.getAsBoolean()"})
  public void testGetAsBoolean_givenJsonArrayWithCapacityIsThreeAddValueOfOne_thenReturnFalse() {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(Integer.valueOf(1));

    // Act and Assert
    assertFalse(jsonArray.getAsBoolean());
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
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(JsonNull.INSTANCE);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> jsonArray.getAsBoolean());
  }

  /**
   * Test {@link JsonArray#asList()}.
   *
   * <p>Method under test: {@link JsonArray#asList()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.List JsonArray.asList()"})
  public void testAsList() {
    // Arrange, Act and Assert
    assertTrue(new JsonArray(3).asList().isEmpty());
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
    JsonArray jsonArray = new JsonArray(3);
    JsonArray jsonArray2 = new JsonArray(3);

    // Act and Assert
    assertEquals(jsonArray, jsonArray2);
    assertEquals(jsonArray.hashCode(), jsonArray2.hashCode());
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
    JsonArray jsonArray = new JsonArray(3);

    // Act and Assert
    assertEquals(jsonArray, jsonArray);
    int expectedHashCodeResult = jsonArray.hashCode();
    assertEquals(expectedHashCodeResult, jsonArray.hashCode());
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
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(true);

    // Act and Assert
    assertNotEquals(jsonArray, new JsonArray(3));
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
    assertNotEquals(new JsonArray(3), null);
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
    assertNotEquals(new JsonArray(3), "Different type to JsonArray");
  }
}
