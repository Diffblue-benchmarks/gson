package com.google.gson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JsonNullDiffblueTest {
  /**
   * Test new {@link JsonNull} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link JsonNull}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonNull.<init>()"})
  public void testNewJsonNull() {
    // Arrange and Act
    JsonNull actualJsonNull = new JsonNull();

    // Assert
    assertFalse(actualJsonNull.isJsonArray());
    assertFalse(actualJsonNull.isJsonObject());
    assertFalse(actualJsonNull.isJsonPrimitive());
    assertTrue(actualJsonNull.isJsonNull());
    assertSame(actualJsonNull, actualJsonNull.getAsJsonNull());
  }

  /**
   * Test {@link JsonNull#deepCopy()}.
   *
   * <p>Method under test: {@link JsonNull#deepCopy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonNull JsonNull.deepCopy()"})
  public void testDeepCopy() {
    // Arrange
    JsonNull jsonNull = JsonNull.INSTANCE;

    // Act
    JsonNull actualDeepCopyResult = jsonNull.deepCopy();

    // Assert
    JsonNull jsonNull2 = actualDeepCopyResult.INSTANCE;
    assertSame(jsonNull2, jsonNull.getAsJsonNull());
    assertSame(jsonNull2, actualDeepCopyResult);
  }

  /**
   * Test {@link JsonNull#equals(Object)}, and {@link JsonNull#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link JsonNull#equals(Object)}
   *   <li>{@link JsonNull#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonNull.equals(Object)", "int JsonNull.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    JsonNull jsonNull = JsonNull.INSTANCE;
    JsonNull jsonNull2 = JsonNull.INSTANCE;

    // Act and Assert
    assertEquals(jsonNull, jsonNull2);
    int expectedHashCodeResult = jsonNull.hashCode();
    assertEquals(expectedHashCodeResult, jsonNull2.hashCode());
  }

  /**
   * Test {@link JsonNull#equals(Object)}, and {@link JsonNull#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link JsonNull#equals(Object)}
   *   <li>{@link JsonNull#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonNull.equals(Object)", "int JsonNull.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    JsonNull jsonNull = JsonNull.INSTANCE;

    // Act and Assert
    assertEquals(jsonNull, jsonNull);
    int expectedHashCodeResult = jsonNull.hashCode();
    assertEquals(expectedHashCodeResult, jsonNull.hashCode());
  }

  /**
   * Test {@link JsonNull#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link JsonNull#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonNull.equals(Object)", "int JsonNull.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(JsonNull.INSTANCE, 3);
  }

  /**
   * Test {@link JsonNull#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link JsonNull#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonNull.equals(Object)", "int JsonNull.hashCode()"})
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(JsonNull.INSTANCE, null);
  }

  /**
   * Test {@link JsonNull#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link JsonNull#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JsonNull.equals(Object)", "int JsonNull.hashCode()"})
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(JsonNull.INSTANCE, "Different type to JsonNull");
  }
}
