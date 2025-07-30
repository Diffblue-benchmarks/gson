package com.google.gson.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.internal.LinkedTreeMap.EntrySet;
import com.google.gson.internal.LinkedTreeMap.KeySet;
import com.google.gson.internal.LinkedTreeMap.Node;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.Comparator;
import java.util.Map.Entry;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LinkedTreeMapDiffblueTest {
  /**
   * Test EntrySet {@link EntrySet#EntrySet(LinkedTreeMap)}.
   *
   * <p>Method under test: {@link EntrySet#EntrySet(LinkedTreeMap)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void EntrySet.<init>(LinkedTreeMap)"})
  public void testEntrySetNewEntrySet() {
    // Arrange, Act and Assert
    assertTrue(new LinkedTreeMap().new EntrySet().isEmpty());
  }

  /**
   * Test KeySet {@link KeySet#contains(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code LinkedTreeMap} is {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link KeySet#contains(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean KeySet.contains(Object)"})
  public void testKeySetContains_givenLinkedTreeMapComGoogleGsonInternalLinkedTreeMapIsValue() {
    // Arrange
    LinkedTreeMap linkedTreeMap = new LinkedTreeMap();
    linkedTreeMap.put("com.google.gson.internal.LinkedTreeMap", "Value");
    linkedTreeMap.put("Key", "Value");

    // Act and Assert
    assertFalse(linkedTreeMap.new KeySet().contains("42"));
  }

  /**
   * Test KeySet {@link KeySet#contains(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} empty string is {@code Value}.
   *   <li>When {@code 42}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link KeySet#contains(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean KeySet.contains(Object)"})
  public void testKeySetContains_givenLinkedTreeMapEmptyStringIsValue_when42_thenReturnFalse() {
    // Arrange
    LinkedTreeMap linkedTreeMap = new LinkedTreeMap();
    linkedTreeMap.put("", "Value");

    // Act and Assert
    assertFalse(linkedTreeMap.new KeySet().contains("42"));
  }

  /**
   * Test KeySet {@link KeySet#contains(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} IfAbsent {@code 42} is {@code 42}.
   *   <li>When {@code 42}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link KeySet#contains(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean KeySet.contains(Object)"})
  public void testKeySetContains_givenLinkedTreeMapIfAbsent42Is42_when42_thenReturnTrue() {
    // Arrange
    LinkedTreeMap linkedTreeMap = new LinkedTreeMap();
    linkedTreeMap.putIfAbsent("42", "42");
    linkedTreeMap.put("Key", "Value");

    // Act and Assert
    assertTrue(linkedTreeMap.new KeySet().contains("42"));
  }

  /**
   * Test KeySet {@link KeySet#contains(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code Key} is {@code Value}.
   *   <li>When {@code 42}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link KeySet#contains(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean KeySet.contains(Object)"})
  public void testKeySetContains_givenLinkedTreeMapKeyIsValue_when42_thenReturnFalse() {
    // Arrange
    LinkedTreeMap linkedTreeMap = new LinkedTreeMap();
    linkedTreeMap.put("Key", "Value");

    // Act and Assert
    assertFalse(linkedTreeMap.new KeySet().contains("42"));
  }

  /**
   * Test KeySet {@link KeySet#contains(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code true} is {@code Value}.
   *   <li>When {@code 42}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link KeySet#contains(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean KeySet.contains(Object)"})
  public void testKeySetContains_givenLinkedTreeMapTrueIsValue_when42_thenReturnFalse() {
    // Arrange
    LinkedTreeMap linkedTreeMap = new LinkedTreeMap();
    linkedTreeMap.put(true, "Value");

    // Act and Assert
    assertFalse(linkedTreeMap.new KeySet().contains("42"));
  }

  /**
   * Test KeySet {@link KeySet#contains(Object)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link KeySet#contains(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean KeySet.contains(Object)"})
  public void testKeySetContains_when42_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new LinkedTreeMap().new KeySet().contains("42"));
  }

  /**
   * Test KeySet {@link KeySet#contains(Object)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link KeySet#contains(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean KeySet.contains(Object)"})
  public void testKeySetContains_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new LinkedTreeMap().new KeySet().contains(null));
  }

  /**
   * Test KeySet {@link KeySet#KeySet(LinkedTreeMap)}.
   *
   * <p>Method under test: {@link KeySet#KeySet(LinkedTreeMap)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void KeySet.<init>(LinkedTreeMap)"})
  public void testKeySetNewKeySet() {
    // Arrange, Act and Assert
    assertTrue(new LinkedTreeMap().new KeySet().isEmpty());
  }

  /**
   * Test {@link LinkedTreeMap#LinkedTreeMap()}.
   *
   * <p>Method under test: {@link LinkedTreeMap#LinkedTreeMap()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void LinkedTreeMap.<init>()"})
  public void testNewLinkedTreeMap() {
    // Arrange and Act
    LinkedTreeMap<Object, Object> actualObjectObjectMap = new LinkedTreeMap<>();

    // Assert
    assertTrue(actualObjectObjectMap.isEmpty());
  }

  /**
   * Test {@link LinkedTreeMap#LinkedTreeMap(boolean)}.
   *
   * <p>Method under test: {@link LinkedTreeMap#LinkedTreeMap(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void LinkedTreeMap.<init>(boolean)"})
  public void testNewLinkedTreeMap2() {
    // Arrange and Act
    LinkedTreeMap<Object, Object> actualObjectObjectMap = new LinkedTreeMap<>(true);

    // Assert
    assertTrue(actualObjectObjectMap.isEmpty());
  }

  /**
   * Test {@link LinkedTreeMap#LinkedTreeMap(Comparator, boolean)}.
   *
   * <ul>
   *   <li>When {@link Comparator}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#LinkedTreeMap(Comparator, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void LinkedTreeMap.<init>(Comparator, boolean)"})
  public void testNewLinkedTreeMap_whenComparator() {
    // Arrange and Act
    LinkedTreeMap<Object, Object> actualObjectObjectMap =
        new LinkedTreeMap<>(mock(Comparator.class), true);

    // Assert
    assertTrue(actualObjectObjectMap.isEmpty());
  }

  /**
   * Test {@link LinkedTreeMap#LinkedTreeMap(Comparator, boolean)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#LinkedTreeMap(Comparator, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void LinkedTreeMap.<init>(Comparator, boolean)"})
  public void testNewLinkedTreeMap_whenNull() {
    // Arrange and Act
    LinkedTreeMap<Object, Object> actualObjectObjectMap = new LinkedTreeMap<>(null, true);

    // Assert
    assertTrue(actualObjectObjectMap.isEmpty());
  }

  /**
   * Test Node {@link Node#equals(Object)}, and {@link Node#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Node#equals(Object)}
   *   <li>{@link Node#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Node.equals(Object)", "int Node.hashCode()"})
  public void testNodeEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Node<Object, Object> node = new Node<>(true);
    Node<Object, Object> node2 = new Node<>(true);

    // Act and Assert
    assertEquals(node, node2);
    int expectedHashCodeResult = node.hashCode();
    assertEquals(expectedHashCodeResult, node2.hashCode());
  }

  /**
   * Test Node {@link Node#equals(Object)}, and {@link Node#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Node#equals(Object)}
   *   <li>{@link Node#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Node.equals(Object)", "int Node.hashCode()"})
  public void testNodeEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual2() {
    // Arrange
    Node<Object, Object> parent = new Node<>(true);
    Node<Object, Object> next = new Node<>(true);
    Node<Object, Object> node = new Node<>(true, parent, "Key", next, new Node<>(true));
    Node<Object, Object> parent2 = new Node<>(true);
    Node<Object, Object> next2 = new Node<>(true);
    Node<Object, Object> node2 = new Node<>(true, parent2, "Key", next2, new Node<>(true));

    // Act and Assert
    assertEquals(node, node2);
    int expectedHashCodeResult = node.hashCode();
    assertEquals(expectedHashCodeResult, node2.hashCode());
  }

  /**
   * Test Node {@link Node#equals(Object)}, and {@link Node#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Node#equals(Object)}
   *   <li>{@link Node#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Node.equals(Object)", "int Node.hashCode()"})
  public void testNodeEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual3() {
    // Arrange
    Node<Object, Object> node = new Node<>(true);
    node.setValue("Value");

    Node<Object, Object> node2 = new Node<>(true);
    node2.setValue("Value");

    // Act and Assert
    assertEquals(node, node2);
    int expectedHashCodeResult = node.hashCode();
    assertEquals(expectedHashCodeResult, node2.hashCode());
  }

  /**
   * Test Node {@link Node#equals(Object)}, and {@link Node#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Node#equals(Object)}
   *   <li>{@link Node#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Node.equals(Object)", "int Node.hashCode()"})
  public void testNodeEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Node<Object, Object> node = new Node<>(true);

    // Act and Assert
    assertEquals(node, node);
    int expectedHashCodeResult = node.hashCode();
    assertEquals(expectedHashCodeResult, node.hashCode());
  }

  /**
   * Test Node {@link Node#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link Node#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Node.equals(Object)", "int Node.hashCode()"})
  public void testNodeEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Node<Object, Object> parent = new Node<>(true);
    Node<Object, Object> next = new Node<>(true);
    Node<Object, Object> node = new Node<>(true, parent, "Key", next, new Node<>(true));

    // Act and Assert
    assertNotEquals(node, new Node<>(true));
  }

  /**
   * Test Node {@link Node#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link Node#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Node.equals(Object)", "int Node.hashCode()"})
  public void testNodeEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Node<Object, Object> node = new Node<>(true);

    // Act and Assert
    assertNotEquals(node, new SimpleEntry<>("42", "42"));
  }

  /**
   * Test Node {@link Node#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link Node#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Node.equals(Object)", "int Node.hashCode()"})
  public void testNodeEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Node<Object, Object> node = new Node<>(true);
    node.setValue("Value");

    // Act and Assert
    assertNotEquals(node, new Node<>(true));
  }

  /**
   * Test Node {@link Node#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link Node#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Node.equals(Object)", "int Node.hashCode()"})
  public void testNodeEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    Node<Object, Object> parent = new Node<>(true);
    Node<Object, Object> node = new Node<>(true);
    Node<Object, Object> next = new Node<>(true);
    Node<Object, Object> node2 = new Node<>(true, parent, node, next, new Node<>(true));

    // Act and Assert
    assertNotEquals(node2, new Node<>(true));
  }

  /**
   * Test Node {@link Node#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link Node#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Node.equals(Object)", "int Node.hashCode()"})
  public void testNodeEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    Node<Object, Object> parent = new Node<>(true);
    Node<Object, Object> next = new Node<>(true);
    Node<Object, Object> node = new Node<>(true, parent, "Key", next, new Node<>(true));

    // Act and Assert
    assertNotEquals(node, new SimpleEntry<>("42", "42"));
  }

  /**
   * Test Node {@link Node#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link Node#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Node.equals(Object)", "int Node.hashCode()"})
  public void testNodeEquals_whenOtherIsDifferent_thenReturnNotEqual6() {
    // Arrange
    Node<Object, Object> node = new Node<>(true);

    Node<Object, Object> node2 = new Node<>(true);
    node2.setValue("Value");

    // Act and Assert
    assertNotEquals(node, node2);
  }

  /**
   * Test Node {@link Node#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link Node#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Node.equals(Object)", "int Node.hashCode()"})
  public void testNodeEquals_whenOtherIsDifferent_thenReturnNotEqual7() {
    // Arrange
    Node<Object, Object> node = new Node<>(true);

    // Act and Assert
    assertNotEquals(node, new SimpleEntry<>(null, "42"));
  }

  /**
   * Test Node {@link Node#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link Node#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Node.equals(Object)", "int Node.hashCode()"})
  public void testNodeEquals_whenOtherIsDifferent_thenReturnNotEqual8() {
    // Arrange
    Node<Object, Object> node = new Node<>(true);
    node.setValue(new Node<>(true));

    // Act and Assert
    assertNotEquals(node, new Node<>(true));
  }

  /**
   * Test Node {@link Node#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link Node#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Node.equals(Object)", "int Node.hashCode()"})
  public void testNodeEquals_whenOtherIsDifferent_thenReturnNotEqual9() {
    // Arrange
    Node<Object, Object> node = new Node<>(true);
    node.setValue("Value");

    // Act and Assert
    assertNotEquals(node, new SimpleEntry<>(null, "42"));
  }

  /**
   * Test Node {@link Node#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link Node#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Node.equals(Object)", "int Node.hashCode()"})
  public void testNodeEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Node<Object, Object> node = new Node<>(true);

    // Act and Assert
    assertNotEquals(node, null);
  }

  /**
   * Test Node {@link Node#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link Node#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Node.equals(Object)", "int Node.hashCode()"})
  public void testNodeEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Node<Object, Object> node = new Node<>(true);

    // Act and Assert
    assertNotEquals(node, "Different type to Node");
  }

  /**
   * Test Node {@link Node#first()}.
   *
   * <p>Method under test: {@link Node#first()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node Node.first()"})
  public void testNodeFirst() {
    // Arrange
    Node<Object, Object> node = new Node<>(true);

    // Act
    Node<Object, Object> actualFirstResult = node.first();

    // Assert
    assertSame(actualFirstResult.prev, actualFirstResult);
  }

  /**
   * Test Node getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Node#toString()}
   *   <li>{@link Node#getKey()}
   *   <li>{@link Node#getValue()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Node.getKey()", "Object Node.getValue()", "String Node.toString()"})
  public void testNodeGettersAndSetters() {
    // Arrange
    Node<Object, Object> node = new Node<>(true);

    // Act
    String actualToStringResult = node.toString();
    Object actualKey = node.getKey();

    // Assert
    assertEquals("null=null", actualToStringResult);
    assertNull(actualKey);
    assertNull(node.getValue());
  }

  /**
   * Test Node {@link Node#last()}.
   *
   * <p>Method under test: {@link Node#last()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node Node.last()"})
  public void testNodeLast() {
    // Arrange
    Node<Object, Object> node = new Node<>(true);

    // Act
    Node<Object, Object> actualLastResult = node.last();

    // Assert
    Node<Object, Object> node2 = actualLastResult.prev;
    assertSame(node2, node.first());
    assertSame(node2, actualLastResult);
  }

  /**
   * Test Node {@link Node#Node(boolean)}.
   *
   * <p>Method under test: {@link Node#Node(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Node.<init>(boolean)"})
  public void testNodeNewNode() {
    // Arrange and Act
    Node<Object, Object> actualNode = new Node<>(true);

    // Assert
    assertNull(actualNode.left);
    assertNull(actualNode.parent);
    assertNull(actualNode.right);
    assertNull(actualNode.getKey());
    assertNull(actualNode.getValue());
    assertEquals(0, actualNode.height);
    assertTrue(actualNode.allowNullValue);
    Node<Object, Object> expectedFirstResult = actualNode.prev;
    assertSame(expectedFirstResult, actualNode.first());
  }

  /**
   * Test Node {@link Node#Node(boolean, Node, Object, Node, Node)}.
   *
   * <ul>
   *   <li>When {@link Node#Node(boolean)} with allowNullValue is {@code true}.
   *   <li>Then return {@code Key}.
   * </ul>
   *
   * <p>Method under test: {@link Node#Node(boolean, Node, Object, Node, Node)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Node.<init>(boolean, Node, Object, Node, Node)"})
  public void testNodeNewNode_whenNodeWithAllowNullValueIsTrue_thenReturnKey() {
    // Arrange
    Node<Object, Object> parent = new Node<>(true);
    Node<Object, Object> next = new Node<>(true);

    // Act
    Node<Object, Object> actualNode = new Node<>(true, parent, "Key", next, new Node<>(true));

    // Assert
    assertEquals("Key", actualNode.getKey());
    assertNull(actualNode.left);
    assertNull(actualNode.right);
    assertNull(actualNode.getValue());
    assertEquals(1, actualNode.height);
    assertTrue(actualNode.allowNullValue);
    assertSame(actualNode, actualNode.first());
    Node<Object, Object> expectedFirstResult = actualNode.parent;
    assertSame(expectedFirstResult, parent.first());
  }

  /**
   * Test Node {@link Node#setValue(Object)}.
   *
   * <ul>
   *   <li>Then {@link Node#Node(boolean)} with allowNullValue is {@code true} Value is {@code
   *       null}.
   * </ul>
   *
   * <p>Method under test: {@link Node#setValue(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Node.setValue(Object)"})
  public void testNodeSetValue_thenNodeWithAllowNullValueIsTrueValueIsNull() {
    // Arrange
    Node<Object, Object> node = new Node<>(true);

    // Act
    Object actualSetValueResult = node.setValue(null);

    // Assert
    assertNull(node.getValue());
    assertNull(node.next.getValue());
    assertNull(node.prev.getValue());
    assertNull(actualSetValueResult);
  }

  /**
   * Test Node {@link Node#setValue(Object)}.
   *
   * <ul>
   *   <li>When {@code Value}.
   *   <li>Then {@link Node#Node(boolean)} with allowNullValue is {@code true} Value is {@code
   *       Value}.
   * </ul>
   *
   * <p>Method under test: {@link Node#setValue(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Node.setValue(Object)"})
  public void testNodeSetValue_whenValue_thenNodeWithAllowNullValueIsTrueValueIsValue() {
    // Arrange
    Node<Object, Object> node = new Node<>(true);

    // Act
    node.setValue("Value");

    // Assert
    assertEquals("Value", node.getValue());
    assertEquals("Value", node.next.getValue());
    assertEquals("Value", node.prev.getValue());
  }

  /**
   * Test {@link LinkedTreeMap#size()}.
   *
   * <p>Method under test: {@link LinkedTreeMap#size()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int LinkedTreeMap.size()"})
  public void testSize() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act and Assert
    assertEquals(0, objectObjectMap.size());
  }

  /**
   * Test {@link LinkedTreeMap#get(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code LinkedTreeMap} is {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#get(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.get(Object)"})
  public void testGet_givenLinkedTreeMapComGoogleGsonInternalLinkedTreeMapIsValue() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("com.google.gson.internal.LinkedTreeMap", "Value");

    // Act and Assert
    assertNull(objectObjectMap.get("Key"));
  }

  /**
   * Test {@link LinkedTreeMap#get(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} IfAbsent {@code 42} is {@code 42}.
   *   <li>When {@code Key}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#get(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.get(Object)"})
  public void testGet_givenLinkedTreeMapIfAbsent42Is42_whenKey_thenReturnNull() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.putIfAbsent("42", "42");

    // Act and Assert
    assertNull(objectObjectMap.get("Key"));
  }

  /**
   * Test {@link LinkedTreeMap#get(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} IfAbsent {@code 42} is {@code 42}.
   *   <li>When {@code Key}.
   *   <li>Then return {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#get(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.get(Object)"})
  public void testGet_givenLinkedTreeMapIfAbsent42Is42_whenKey_thenReturnValue() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.putIfAbsent("42", "42");
    objectObjectMap.put("Key", "Value");

    // Act and Assert
    assertEquals("Value", objectObjectMap.get("Key"));
  }

  /**
   * Test {@link LinkedTreeMap#get(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code Key} is {@code false}.
   *   <li>When {@code Key}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#get(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.get(Object)"})
  public void testGet_givenLinkedTreeMapKeyIsFalse_whenKey_thenReturnFalse() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("Key", false);

    // Act and Assert
    assertFalse((Boolean) objectObjectMap.get("Key"));
  }

  /**
   * Test {@link LinkedTreeMap#get(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code Key} is {@code true}.
   *   <li>When {@code Key}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#get(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.get(Object)"})
  public void testGet_givenLinkedTreeMapKeyIsTrue_whenKey_thenReturnTrue() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("Key", true);

    // Act and Assert
    assertTrue((Boolean) objectObjectMap.get("Key"));
  }

  /**
   * Test {@link LinkedTreeMap#get(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code Key} is {@code Value}.
   *   <li>When {@code Key}.
   *   <li>Then return {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#get(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.get(Object)"})
  public void testGet_givenLinkedTreeMapKeyIsValue_whenKey_thenReturnValue() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("Key", "Value");

    // Act and Assert
    assertEquals("Value", objectObjectMap.get("Key"));
  }

  /**
   * Test {@link LinkedTreeMap#get(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code true} is {@code Value}.
   *   <li>When {@code Key}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#get(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.get(Object)"})
  public void testGet_givenLinkedTreeMapTrueIsValue_whenKey_thenReturnNull() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put(true, "Value");

    // Act and Assert
    assertNull(objectObjectMap.get("Key"));
  }

  /**
   * Test {@link LinkedTreeMap#get(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()}.
   *   <li>When {@code Key}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#get(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.get(Object)"})
  public void testGet_givenLinkedTreeMap_whenKey_thenReturnNull() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act and Assert
    assertNull(objectObjectMap.get("Key"));
  }

  /**
   * Test {@link LinkedTreeMap#get(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()}.
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#get(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.get(Object)"})
  public void testGet_givenLinkedTreeMap_whenNull_thenReturnNull() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act and Assert
    assertNull(objectObjectMap.get(null));
  }

  /**
   * Test {@link LinkedTreeMap#containsKey(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code LinkedTreeMap} is {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#containsKey(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean LinkedTreeMap.containsKey(Object)"})
  public void testContainsKey_givenLinkedTreeMapComGoogleGsonInternalLinkedTreeMapIsValue() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("com.google.gson.internal.LinkedTreeMap", "Value");

    // Act and Assert
    assertFalse(objectObjectMap.containsKey("Key"));
  }

  /**
   * Test {@link LinkedTreeMap#containsKey(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} IfAbsent {@code 42} is {@code 42}.
   *   <li>When {@code Key}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#containsKey(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean LinkedTreeMap.containsKey(Object)"})
  public void testContainsKey_givenLinkedTreeMapIfAbsent42Is42_whenKey_thenReturnFalse() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.putIfAbsent("42", "42");

    // Act and Assert
    assertFalse(objectObjectMap.containsKey("Key"));
  }

  /**
   * Test {@link LinkedTreeMap#containsKey(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} IfAbsent {@code 42} is {@code 42}.
   *   <li>When {@code Key}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#containsKey(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean LinkedTreeMap.containsKey(Object)"})
  public void testContainsKey_givenLinkedTreeMapIfAbsent42Is42_whenKey_thenReturnTrue() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.putIfAbsent("42", "42");
    objectObjectMap.put("Key", "Value");

    // Act and Assert
    assertTrue(objectObjectMap.containsKey("Key"));
  }

  /**
   * Test {@link LinkedTreeMap#containsKey(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code Key} is {@code Value}.
   *   <li>When {@code Key}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#containsKey(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean LinkedTreeMap.containsKey(Object)"})
  public void testContainsKey_givenLinkedTreeMapKeyIsValue_whenKey_thenReturnTrue() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("Key", "Value");

    // Act and Assert
    assertTrue(objectObjectMap.containsKey("Key"));
  }

  /**
   * Test {@link LinkedTreeMap#containsKey(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code true} is {@code Value}.
   *   <li>When {@code Key}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#containsKey(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean LinkedTreeMap.containsKey(Object)"})
  public void testContainsKey_givenLinkedTreeMapTrueIsValue_whenKey_thenReturnFalse() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put(true, "Value");

    // Act and Assert
    assertFalse(objectObjectMap.containsKey("Key"));
  }

  /**
   * Test {@link LinkedTreeMap#containsKey(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()}.
   *   <li>When {@code Key}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#containsKey(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean LinkedTreeMap.containsKey(Object)"})
  public void testContainsKey_givenLinkedTreeMap_whenKey_thenReturnFalse() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act and Assert
    assertFalse(objectObjectMap.containsKey("Key"));
  }

  /**
   * Test {@link LinkedTreeMap#containsKey(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()}.
   *   <li>When {@code null}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#containsKey(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean LinkedTreeMap.containsKey(Object)"})
  public void testContainsKey_givenLinkedTreeMap_whenNull_thenReturnFalse() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act and Assert
    assertFalse(objectObjectMap.containsKey(null));
  }

  /**
   * Test {@link LinkedTreeMap#put(Object, Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} IfAbsent {@code 42} is {@code 42}.
   *   <li>Then {@link LinkedTreeMap#LinkedTreeMap()} containsKey {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#put(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.put(Object, Object)"})
  public void testPut_givenLinkedTreeMapIfAbsent42Is42_thenLinkedTreeMapContainsKey42() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.putIfAbsent("42", "42");

    // Act
    Object actualPutResult = objectObjectMap.put("Key", "Value");

    // Assert
    assertEquals(2, objectObjectMap.size());
    assertEquals("Value", objectObjectMap.get("Key"));
    assertNull(actualPutResult);
    assertTrue(objectObjectMap.containsKey("42"));
  }

  /**
   * Test {@link LinkedTreeMap#put(Object, Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} IfAbsent {@code 42} is {@code 42}.
   *   <li>When {@code Value}.
   *   <li>Then return {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#put(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.put(Object, Object)"})
  public void testPut_givenLinkedTreeMapIfAbsent42Is42_whenValue_thenReturnValue() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.putIfAbsent("42", "42");
    objectObjectMap.put("Key", "Value");

    // Act
    Object actualPutResult = objectObjectMap.put("Key", "Value");

    // Assert
    assertEquals(2, objectObjectMap.size());
    assertEquals("Value", objectObjectMap.get("Key"));
    assertEquals("Value", actualPutResult);
    assertTrue(objectObjectMap.containsKey("42"));
  }

  /**
   * Test {@link LinkedTreeMap#put(Object, Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code Key} is {@code Value}.
   *   <li>When {@code Value}.
   *   <li>Then return {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#put(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.put(Object, Object)"})
  public void testPut_givenLinkedTreeMapKeyIsValue_whenValue_thenReturnValue() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("Key", "Value");

    // Act
    Object actualPutResult = objectObjectMap.put("Key", "Value");

    // Assert
    assertEquals(1, objectObjectMap.size());
    assertEquals("Value", objectObjectMap.get("Key"));
    assertEquals("Value", actualPutResult);
  }

  /**
   * Test {@link LinkedTreeMap#put(Object, Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()}.
   *   <li>When {@code null}.
   *   <li>Then {@link LinkedTreeMap#LinkedTreeMap()} {@code Key} is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#put(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.put(Object, Object)"})
  public void testPut_givenLinkedTreeMap_whenNull_thenLinkedTreeMapKeyIsNull() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act
    Object actualPutResult = objectObjectMap.put("Key", null);

    // Assert
    assertEquals(1, objectObjectMap.size());
    assertNull(objectObjectMap.get("Key"));
    assertNull(actualPutResult);
  }

  /**
   * Test {@link LinkedTreeMap#put(Object, Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()}.
   *   <li>When {@code Value}.
   *   <li>Then {@link LinkedTreeMap#LinkedTreeMap()} size is one.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#put(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.put(Object, Object)"})
  public void testPut_givenLinkedTreeMap_whenValue_thenLinkedTreeMapSizeIsOne() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act
    Object actualPutResult = objectObjectMap.put("Key", "Value");

    // Assert
    assertEquals(1, objectObjectMap.size());
    assertEquals("Value", objectObjectMap.get("Key"));
    assertNull(actualPutResult);
  }

  /**
   * Test {@link LinkedTreeMap#put(Object, Object)}.
   *
   * <ul>
   *   <li>Then {@link LinkedTreeMap#LinkedTreeMap()} containsKey {@code LinkedTreeMap}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#put(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.put(Object, Object)"})
  public void testPut_thenLinkedTreeMapContainsKeyComGoogleGsonInternalLinkedTreeMap() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("com.google.gson.internal.LinkedTreeMap", "Value");

    // Act
    Object actualPutResult = objectObjectMap.put("Key", "Value");

    // Assert
    assertEquals(2, objectObjectMap.size());
    assertEquals("Value", objectObjectMap.get("Key"));
    assertNull(actualPutResult);
    assertTrue(objectObjectMap.containsKey("com.google.gson.internal.LinkedTreeMap"));
  }

  /**
   * Test {@link LinkedTreeMap#remove(Object)} with {@code Object}.
   *
   * <p>Method under test: {@link LinkedTreeMap#remove(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.remove(Object)"})
  public void testRemoveWithObject() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("com.google.gson.internal.LinkedTreeMap", "Value");

    // Act and Assert
    assertNull(objectObjectMap.remove("Key"));
    assertEquals(1, objectObjectMap.size());
    assertTrue(objectObjectMap.containsKey("com.google.gson.internal.LinkedTreeMap"));
  }

  /**
   * Test {@link LinkedTreeMap#remove(Object)} with {@code Object}.
   *
   * <p>Method under test: {@link LinkedTreeMap#remove(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.remove(Object)"})
  public void testRemoveWithObject2() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("com.google.gson.internal.LinkedTreeMap", "Value");
    objectObjectMap.put("Key", "Value");

    // Act
    Object actualRemoveResult = objectObjectMap.remove("Key");

    // Assert
    assertEquals(1, objectObjectMap.size());
    assertEquals("Value", actualRemoveResult);
    assertTrue(objectObjectMap.containsKey("com.google.gson.internal.LinkedTreeMap"));
  }

  /**
   * Test {@link LinkedTreeMap#remove(Object)} with {@code Object}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} IfAbsent {@code 42} is {@code 42}.
   *   <li>When {@code 42}.
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#remove(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.remove(Object)"})
  public void testRemoveWithObject_givenLinkedTreeMapIfAbsent42Is42_when42_thenReturn42() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.putIfAbsent("42", "42");
    objectObjectMap.put("Key", "Value");

    // Act
    Object actualRemoveResult = objectObjectMap.remove("42");

    // Assert
    assertEquals(1, objectObjectMap.size());
    assertEquals("42", actualRemoveResult);
    assertTrue(objectObjectMap.containsKey("Key"));
  }

  /**
   * Test {@link LinkedTreeMap#remove(Object)} with {@code Object}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code Key} is {@code false}.
   *   <li>When {@code Key}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#remove(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.remove(Object)"})
  public void testRemoveWithObject_givenLinkedTreeMapKeyIsFalse_whenKey_thenReturnFalse() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("Key", false);

    // Act and Assert
    assertFalse((Boolean) objectObjectMap.remove("Key"));
    assertTrue(objectObjectMap.isEmpty());
  }

  /**
   * Test {@link LinkedTreeMap#remove(Object)} with {@code Object}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code Key} is {@code true}.
   *   <li>When {@code Key}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#remove(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.remove(Object)"})
  public void testRemoveWithObject_givenLinkedTreeMapKeyIsTrue_whenKey_thenReturnTrue() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("Key", true);

    // Act
    Object actualRemoveResult = objectObjectMap.remove("Key");

    // Assert
    assertTrue(objectObjectMap.isEmpty());
    assertTrue((Boolean) actualRemoveResult);
  }

  /**
   * Test {@link LinkedTreeMap#remove(Object)} with {@code Object}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code Key} is {@code Value}.
   *   <li>When {@code Key}.
   *   <li>Then return {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#remove(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.remove(Object)"})
  public void testRemoveWithObject_givenLinkedTreeMapKeyIsValue_whenKey_thenReturnValue() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("Key", "Value");

    // Act and Assert
    assertEquals("Value", objectObjectMap.remove("Key"));
    assertTrue(objectObjectMap.isEmpty());
  }

  /**
   * Test {@link LinkedTreeMap#remove(Object)} with {@code Object}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()}.
   *   <li>When {@code Key}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#remove(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.remove(Object)"})
  public void testRemoveWithObject_givenLinkedTreeMap_whenKey_thenReturnNull() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act and Assert
    assertNull(objectObjectMap.remove("Key"));
    assertTrue(objectObjectMap.isEmpty());
  }

  /**
   * Test {@link LinkedTreeMap#remove(Object)} with {@code Object}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()}.
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#remove(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.remove(Object)"})
  public void testRemoveWithObject_givenLinkedTreeMap_whenNull_thenReturnNull() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act and Assert
    assertNull(objectObjectMap.remove(null));
    assertTrue(objectObjectMap.isEmpty());
  }

  /**
   * Test {@link LinkedTreeMap#remove(Object)} with {@code Object}.
   *
   * <ul>
   *   <li>Then {@link LinkedTreeMap#LinkedTreeMap()} containsKey {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#remove(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.remove(Object)"})
  public void testRemoveWithObject_thenLinkedTreeMapContainsKey42() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.putIfAbsent("42", "42");
    objectObjectMap.put("Key", "Value");

    // Act
    Object actualRemoveResult = objectObjectMap.remove("Key");

    // Assert
    assertEquals(1, objectObjectMap.size());
    assertEquals("Value", actualRemoveResult);
    assertTrue(objectObjectMap.containsKey("42"));
  }

  /**
   * Test {@link LinkedTreeMap#remove(Object)} with {@code Object}.
   *
   * <ul>
   *   <li>Then {@link LinkedTreeMap#LinkedTreeMap()} containsKey {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#remove(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.remove(Object)"})
  public void testRemoveWithObject_thenLinkedTreeMapContainsKey422() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.putIfAbsent("42", "42");

    // Act and Assert
    assertNull(objectObjectMap.remove("Key"));
    assertEquals(1, objectObjectMap.size());
    assertTrue(objectObjectMap.containsKey("42"));
  }

  /**
   * Test {@link LinkedTreeMap#remove(Object)} with {@code Object}.
   *
   * <ul>
   *   <li>Then {@link LinkedTreeMap#LinkedTreeMap()} containsKey {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#remove(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.remove(Object)"})
  public void testRemoveWithObject_thenLinkedTreeMapContainsKey423() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("Key", "Value");
    objectObjectMap.putIfAbsent("42", "42");
    objectObjectMap.put("Key", "Value");

    // Act
    Object actualRemoveResult = objectObjectMap.remove("Key");

    // Assert
    assertEquals(1, objectObjectMap.size());
    assertEquals("Value", actualRemoveResult);
    assertTrue(objectObjectMap.containsKey("42"));
  }

  /**
   * Test {@link LinkedTreeMap#remove(Object)} with {@code Object}.
   *
   * <ul>
   *   <li>Then {@link LinkedTreeMap#LinkedTreeMap()} containsKey {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#remove(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.remove(Object)"})
  public void testRemoveWithObject_thenLinkedTreeMapContainsKeyTrue() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put(true, "Value");

    // Act and Assert
    assertNull(objectObjectMap.remove("Key"));
    assertEquals(1, objectObjectMap.size());
    assertTrue(objectObjectMap.containsKey(true));
  }

  /**
   * Test {@link LinkedTreeMap#remove(Object)} with {@code Object}.
   *
   * <ul>
   *   <li>Then {@link LinkedTreeMap#LinkedTreeMap()} size is two.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#remove(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.remove(Object)"})
  public void testRemoveWithObject_thenLinkedTreeMapSizeIsTwo() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("com.google.gson.internal.LinkedTreeMap", "Value");
    objectObjectMap.putIfAbsent("42", "42");
    objectObjectMap.put("Key", "Value");

    // Act
    Object actualRemoveResult = objectObjectMap.remove("Key");

    // Assert
    assertEquals(2, objectObjectMap.size());
    assertEquals("Value", actualRemoveResult);
    assertTrue(objectObjectMap.containsKey("42"));
    assertTrue(objectObjectMap.containsKey("com.google.gson.internal.LinkedTreeMap"));
  }

  /**
   * Test {@link LinkedTreeMap#find(Object, boolean)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} IfAbsent {@code 42} is {@code 42}.
   *   <li>When {@code Key}.
   *   <li>Then {@link LinkedTreeMap#LinkedTreeMap()} size is two.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#find(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.find(Object, boolean)"})
  public void testFind_givenLinkedTreeMapIfAbsent42Is42_whenKey_thenLinkedTreeMapSizeIsTwo() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.putIfAbsent("42", "42");

    // Act
    Node<Object, Object> actualFindResult = objectObjectMap.find("Key", true);

    // Assert
    assertEquals(2, objectObjectMap.size());
    Node<Object, Object> node = actualFindResult.parent;
    assertEquals("42", node.getKey());
    Node<Object, Object> node2 = actualFindResult.prev;
    assertEquals("42", node2.getKey());
    assertEquals("42", node.getValue());
    assertEquals("42", node2.getValue());
    assertNull(node.left);
    assertTrue(objectObjectMap.containsKey("42"));
    assertTrue(objectObjectMap.containsKey("Key"));
    assertSame(actualFindResult, node.right);
    assertSame(actualFindResult, node2.right);
    Node<Object, Object> expectedFirstResult = actualFindResult.parent;
    assertSame(expectedFirstResult, node2.first());
    Node<Object, Object> expectedFirstResult2 = actualFindResult.prev;
    assertSame(expectedFirstResult2, node.first());
  }

  /**
   * Test {@link LinkedTreeMap#find(Object, boolean)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} IfAbsent {@code 42} is {@code 42}.
   *   <li>When {@code Key}.
   *   <li>Then return {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#find(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.find(Object, boolean)"})
  public void testFind_givenLinkedTreeMapIfAbsent42Is42_whenKey_thenReturnValue() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.putIfAbsent("42", "42");
    objectObjectMap.put("Key", "Value");

    // Act
    Node<Object, Object> actualFindResult = objectObjectMap.find("Key", true);

    // Assert
    Node<Object, Object> node = actualFindResult.parent;
    assertEquals("42", node.getKey());
    Node<Object, Object> node2 = actualFindResult.prev;
    assertEquals("42", node2.getKey());
    assertEquals("42", node.getValue());
    assertEquals("42", node2.getValue());
    assertEquals("Value", actualFindResult.getValue());
    assertNull(node.left);
    assertSame(actualFindResult, node.right);
    assertSame(actualFindResult, node2.right);
    Node<Object, Object> expectedFirstResult = actualFindResult.parent;
    assertSame(expectedFirstResult, node2.first());
    Node<Object, Object> expectedFirstResult2 = actualFindResult.prev;
    assertSame(expectedFirstResult2, node.first());
  }

  /**
   * Test {@link LinkedTreeMap#find(Object, boolean)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code Key} is {@code Value}.
   *   <li>When {@code Key}.
   *   <li>Then return {@link LinkedTreeMap#LinkedTreeMap()} {@link LinkedTreeMap#root}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#find(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.find(Object, boolean)"})
  public void testFind_givenLinkedTreeMapKeyIsValue_whenKey_thenReturnLinkedTreeMapRoot() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("Key", "Value");

    // Act and Assert
    assertSame(objectObjectMap.root, objectObjectMap.find("Key", true));
  }

  /**
   * Test {@link LinkedTreeMap#find(Object, boolean)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()}.
   *   <li>When {@code Key}.
   *   <li>Then {@link LinkedTreeMap#LinkedTreeMap()} size is one.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#find(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.find(Object, boolean)"})
  public void testFind_givenLinkedTreeMap_whenKey_thenLinkedTreeMapSizeIsOne() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act
    Node<Object, Object> actualFindResult = objectObjectMap.find("Key", true);

    // Assert
    assertEquals(1, objectObjectMap.size());
    assertTrue(objectObjectMap.containsKey("Key"));
    assertSame(objectObjectMap.root, actualFindResult);
  }

  /**
   * Test {@link LinkedTreeMap#find(Object, boolean)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()}.
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#find(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.find(Object, boolean)"})
  public void testFind_givenLinkedTreeMap_whenNull_thenReturnNull() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act and Assert
    assertNull(objectObjectMap.find(null, false));
    assertTrue(objectObjectMap.isEmpty());
  }

  /**
   * Test {@link LinkedTreeMap#find(Object, boolean)}.
   *
   * <ul>
   *   <li>Then return {@link Node#parent} Value is {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#find(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.find(Object, boolean)"})
  public void testFind_thenReturnParentValueIsValue() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("com.google.gson.internal.LinkedTreeMap", "Value");

    // Act
    Node<Object, Object> actualFindResult = objectObjectMap.find("Key", true);

    // Assert
    Node<Object, Object> node = actualFindResult.parent;
    assertEquals("Value", node.getValue());
    Node<Object, Object> node2 = actualFindResult.prev;
    assertEquals("Value", node2.getValue());
    assertEquals("com.google.gson.internal.LinkedTreeMap", node.getKey());
    assertEquals("com.google.gson.internal.LinkedTreeMap", node2.getKey());
    assertNull(node.right);
    assertSame(actualFindResult, node.first());
    assertSame(actualFindResult, node2.first());
    assertSame(actualFindResult, node.left);
    assertSame(actualFindResult, node2.left);
  }

  /**
   * Test {@link LinkedTreeMap#findByObject(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code LinkedTreeMap} is {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#findByObject(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.findByObject(Object)"})
  public void testFindByObject_givenLinkedTreeMapComGoogleGsonInternalLinkedTreeMapIsValue() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("com.google.gson.internal.LinkedTreeMap", "Value");

    // Act and Assert
    assertNull(objectObjectMap.findByObject("Key"));
  }

  /**
   * Test {@link LinkedTreeMap#findByObject(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} IfAbsent {@code 42} is {@code 42}.
   *   <li>Then return {@link Node#parent} Key is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#findByObject(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.findByObject(Object)"})
  public void testFindByObject_givenLinkedTreeMapIfAbsent42Is42_thenReturnParentKeyIs42() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.putIfAbsent("42", "42");
    objectObjectMap.put("Key", "Value");

    // Act
    Node<Object, Object> actualFindByObjectResult = objectObjectMap.findByObject("Key");

    // Assert
    Node<Object, Object> node = actualFindByObjectResult.parent;
    assertEquals("42", node.getKey());
    Node<Object, Object> node2 = actualFindByObjectResult.prev;
    assertEquals("42", node2.getKey());
    assertEquals("42", node.getValue());
    assertEquals("42", node2.getValue());
    assertNull(node.left);
    assertNull(node.parent);
    assertEquals(2, node.height);
    assertEquals(2, node2.height);
    assertTrue(node.allowNullValue);
    assertSame(actualFindByObjectResult, node.next);
    assertSame(actualFindByObjectResult, node.right);
    assertSame(actualFindByObjectResult, node2.right);
    Node<Object, Object> node3 = actualFindByObjectResult.next;
    assertSame(node3, node.prev);
    assertSame(node3, node2.prev);
    Node<Object, Object> expectedFirstResult = actualFindByObjectResult.parent;
    assertSame(expectedFirstResult, node2.first());
    Node<Object, Object> node4 = actualFindByObjectResult.prev;
    assertSame(node4, node.first());
    assertSame(node4, actualFindByObjectResult.next.next);
  }

  /**
   * Test {@link LinkedTreeMap#findByObject(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} IfAbsent {@code 42} is {@code 42}.
   *   <li>When {@code Key}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#findByObject(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.findByObject(Object)"})
  public void testFindByObject_givenLinkedTreeMapIfAbsent42Is42_whenKey_thenReturnNull() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.putIfAbsent("42", "42");

    // Act and Assert
    assertNull(objectObjectMap.findByObject("Key"));
  }

  /**
   * Test {@link LinkedTreeMap#findByObject(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code Key} is {@code Value}.
   *   <li>Then return {@link LinkedTreeMap#LinkedTreeMap()} {@link LinkedTreeMap#root}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#findByObject(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.findByObject(Object)"})
  public void testFindByObject_givenLinkedTreeMapKeyIsValue_thenReturnLinkedTreeMapRoot() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("Key", "Value");

    // Act and Assert
    assertSame(objectObjectMap.root, objectObjectMap.findByObject("Key"));
  }

  /**
   * Test {@link LinkedTreeMap#findByObject(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code true} is {@code Value}.
   *   <li>When {@code Key}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#findByObject(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.findByObject(Object)"})
  public void testFindByObject_givenLinkedTreeMapTrueIsValue_whenKey_thenReturnNull() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put(true, "Value");

    // Act and Assert
    assertNull(objectObjectMap.findByObject("Key"));
  }

  /**
   * Test {@link LinkedTreeMap#findByObject(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()}.
   *   <li>When {@code Key}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#findByObject(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.findByObject(Object)"})
  public void testFindByObject_givenLinkedTreeMap_whenKey_thenReturnNull() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act and Assert
    assertNull(objectObjectMap.findByObject("Key"));
  }

  /**
   * Test {@link LinkedTreeMap#findByObject(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()}.
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#findByObject(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.findByObject(Object)"})
  public void testFindByObject_givenLinkedTreeMap_whenNull_thenReturnNull() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act and Assert
    assertNull(objectObjectMap.findByObject(null));
  }

  /**
   * Test {@link LinkedTreeMap#findByEntry(Entry)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code 42} is {@code Value}.
   *   <li>When {@link SimpleEntry#SimpleEntry(Object, Object)} with {@code 42} and {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#findByEntry(Entry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.findByEntry(Entry)"})
  public void testFindByEntry_givenLinkedTreeMap42IsValue_whenSimpleEntryWith42And42() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("42", "Value");

    // Act and Assert
    assertNull(objectObjectMap.findByEntry(new SimpleEntry<>("42", "42")));
  }

  /**
   * Test {@link LinkedTreeMap#findByEntry(Entry)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code LinkedTreeMap} is {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#findByEntry(Entry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.findByEntry(Entry)"})
  public void testFindByEntry_givenLinkedTreeMapComGoogleGsonInternalLinkedTreeMapIsValue() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("com.google.gson.internal.LinkedTreeMap", "Value");
    objectObjectMap.put("Key", "Value");

    // Act and Assert
    assertNull(objectObjectMap.findByEntry(new SimpleEntry<>("42", "42")));
  }

  /**
   * Test {@link LinkedTreeMap#findByEntry(Entry)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} empty string is {@code Value}.
   *   <li>When {@link SimpleEntry#SimpleEntry(Object, Object)} with {@code 42} and {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#findByEntry(Entry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.findByEntry(Entry)"})
  public void testFindByEntry_givenLinkedTreeMapEmptyStringIsValue_whenSimpleEntryWith42And42() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("", "Value");

    // Act and Assert
    assertNull(objectObjectMap.findByEntry(new SimpleEntry<>("42", "42")));
  }

  /**
   * Test {@link LinkedTreeMap#findByEntry(Entry)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} IfAbsent {@code 42} is {@code 42}.
   *   <li>Then return {@link LinkedTreeMap#LinkedTreeMap()} {@link LinkedTreeMap#root}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#findByEntry(Entry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.findByEntry(Entry)"})
  public void testFindByEntry_givenLinkedTreeMapIfAbsent42Is42_thenReturnLinkedTreeMapRoot() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.putIfAbsent("42", "42");
    objectObjectMap.put("Key", "Value");

    // Act and Assert
    assertSame(objectObjectMap.root, objectObjectMap.findByEntry(new SimpleEntry<>("42", "42")));
  }

  /**
   * Test {@link LinkedTreeMap#findByEntry(Entry)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code Key} is {@code Value}.
   *   <li>When {@link SimpleEntry#SimpleEntry(Object, Object)} with {@code 42} and {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#findByEntry(Entry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.findByEntry(Entry)"})
  public void testFindByEntry_givenLinkedTreeMapKeyIsValue_whenSimpleEntryWith42And42() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("Key", "Value");

    // Act and Assert
    assertNull(objectObjectMap.findByEntry(new SimpleEntry<>("42", "42")));
  }

  /**
   * Test {@link LinkedTreeMap#findByEntry(Entry)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code true} is {@code Value}.
   *   <li>When {@link SimpleEntry#SimpleEntry(Object, Object)} with {@code 42} and {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#findByEntry(Entry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.findByEntry(Entry)"})
  public void testFindByEntry_givenLinkedTreeMapTrueIsValue_whenSimpleEntryWith42And42() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put(true, "Value");

    // Act and Assert
    assertNull(objectObjectMap.findByEntry(new SimpleEntry<>("42", "42")));
  }

  /**
   * Test {@link LinkedTreeMap#findByEntry(Entry)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()}.
   *   <li>When {@link Node#Node(boolean)} with allowNullValue is {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#findByEntry(Entry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.findByEntry(Entry)"})
  public void testFindByEntry_givenLinkedTreeMap_whenNodeWithAllowNullValueIsTrue() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act and Assert
    assertNull(objectObjectMap.findByEntry(new Node<>(true)));
  }

  /**
   * Test {@link LinkedTreeMap#findByEntry(Entry)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()}.
   *   <li>When {@link SimpleEntry#SimpleEntry(Object, Object)} with {@code 42} and {@code 42}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#findByEntry(Entry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.findByEntry(Entry)"})
  public void testFindByEntry_givenLinkedTreeMap_whenSimpleEntryWith42And42_thenReturnNull() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act and Assert
    assertNull(objectObjectMap.findByEntry(new SimpleEntry<>("42", "42")));
  }

  /**
   * Test {@link LinkedTreeMap#findByEntry(Entry)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()}.
   *   <li>When {@link SimpleEntry#SimpleEntry(Object, Object)} with {@code null} and {@code 42}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#findByEntry(Entry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.findByEntry(Entry)"})
  public void testFindByEntry_givenLinkedTreeMap_whenSimpleEntryWithNullAnd42_thenReturnNull() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act and Assert
    assertNull(objectObjectMap.findByEntry(new SimpleEntry<>(null, "42")));
  }

  /**
   * Test {@link LinkedTreeMap#removeInternal(Node, boolean)}.
   *
   * <ul>
   *   <li>Then {@link LinkedTreeMap#LinkedTreeMap()} size is minus one.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#removeInternal(Node, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void LinkedTreeMap.removeInternal(Node, boolean)"})
  public void testRemoveInternal_thenLinkedTreeMapSizeIsMinusOne() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act
    objectObjectMap.removeInternal(new Node<>(true), true);

    // Assert
    assertEquals(-1, objectObjectMap.size());
  }

  /**
   * Test {@link LinkedTreeMap#removeInternalByKey(Object)}.
   *
   * <p>Method under test: {@link LinkedTreeMap#removeInternalByKey(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.removeInternalByKey(Object)"})
  public void testRemoveInternalByKey() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("com.google.gson.internal.LinkedTreeMap", "Value");

    // Act and Assert
    assertNull(objectObjectMap.removeInternalByKey("Key"));
    assertEquals(1, objectObjectMap.size());
    assertTrue(objectObjectMap.containsKey("com.google.gson.internal.LinkedTreeMap"));
  }

  /**
   * Test {@link LinkedTreeMap#removeInternalByKey(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} IfAbsent {@code 42} is {@code 42}.
   *   <li>Then return {@link Node#next} Key is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#removeInternalByKey(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.removeInternalByKey(Object)"})
  public void testRemoveInternalByKey_givenLinkedTreeMapIfAbsent42Is42_thenReturnNextKeyIs42() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("Key", "Value");
    objectObjectMap.putIfAbsent("42", "42");
    objectObjectMap.put("Key", "Value");

    // Act
    Node<Object, Object> actualRemoveInternalByKeyResult =
        objectObjectMap.removeInternalByKey("Key");

    // Assert
    Node<Object, Object> node = actualRemoveInternalByKeyResult.next;
    assertEquals("42", node.getKey());
    assertEquals("42", node.getValue());
    assertEquals(1, node.height);
    Node<Object, Object> node2 = objectObjectMap.root;
    assertSame(node2, node.first());
    assertSame(node2, actualRemoveInternalByKeyResult.prev.next);
  }

  /**
   * Test {@link LinkedTreeMap#removeInternalByKey(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} IfAbsent {@code 42} is {@code 42}.
   *   <li>Then return {@link Node#prev} Key is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#removeInternalByKey(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.removeInternalByKey(Object)"})
  public void testRemoveInternalByKey_givenLinkedTreeMapIfAbsent42Is42_thenReturnPrevKeyIs42() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.putIfAbsent("42", "42");
    objectObjectMap.put("Key", "Value");

    // Act
    Node<Object, Object> actualRemoveInternalByKeyResult =
        objectObjectMap.removeInternalByKey("Key");

    // Assert
    Node<Object, Object> node = actualRemoveInternalByKeyResult.prev;
    assertEquals("42", node.getKey());
    assertEquals("42", node.getValue());
    assertEquals(1, node.height);
    assertSame(objectObjectMap.header, node.prev);
    Node<Object, Object> node2 = objectObjectMap.root;
    assertSame(node2, node.first());
    Node<Object, Object> node3 = actualRemoveInternalByKeyResult.next;
    assertSame(node2, node3.next);
    assertSame(node2, node3.prev);
  }

  /**
   * Test {@link LinkedTreeMap#removeInternalByKey(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code Key} is {@code Value}.
   *   <li>Then return {@link Node#prev} first is {@link Node#next}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#removeInternalByKey(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.removeInternalByKey(Object)"})
  public void testRemoveInternalByKey_givenLinkedTreeMapKeyIsValue_thenReturnPrevFirstIsNext() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("Key", "Value");

    // Act
    Node<Object, Object> actualRemoveInternalByKeyResult =
        objectObjectMap.removeInternalByKey("Key");

    // Assert
    assertTrue(objectObjectMap.isEmpty());
    Node<Object, Object> node = actualRemoveInternalByKeyResult.next;
    Node<Object, Object> node2 = actualRemoveInternalByKeyResult.prev;
    assertSame(node, node2.first());
    Node<Object, Object> node3 = actualRemoveInternalByKeyResult.next;
    assertSame(node, node3.next);
    assertSame(node, node3.prev);
    Node<Object, Object> node4 = actualRemoveInternalByKeyResult.prev;
    assertSame(node4, node3.first());
    assertSame(node4, node2.next);
    assertSame(node4, node2.prev);
  }

  /**
   * Test {@link LinkedTreeMap#removeInternalByKey(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()}.
   *   <li>When {@code Key}.
   *   <li>Then {@link LinkedTreeMap#LinkedTreeMap()} Empty.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#removeInternalByKey(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.removeInternalByKey(Object)"})
  public void testRemoveInternalByKey_givenLinkedTreeMap_whenKey_thenLinkedTreeMapEmpty() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act and Assert
    assertNull(objectObjectMap.removeInternalByKey("Key"));
    assertTrue(objectObjectMap.isEmpty());
  }

  /**
   * Test {@link LinkedTreeMap#removeInternalByKey(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()}.
   *   <li>When {@code null}.
   *   <li>Then {@link LinkedTreeMap#LinkedTreeMap()} Empty.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#removeInternalByKey(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.removeInternalByKey(Object)"})
  public void testRemoveInternalByKey_givenLinkedTreeMap_whenNull_thenLinkedTreeMapEmpty() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act and Assert
    assertNull(objectObjectMap.removeInternalByKey(null));
    assertTrue(objectObjectMap.isEmpty());
  }

  /**
   * Test {@link LinkedTreeMap#removeInternalByKey(Object)}.
   *
   * <ul>
   *   <li>Then {@link LinkedTreeMap#LinkedTreeMap()} containsKey {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#removeInternalByKey(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.removeInternalByKey(Object)"})
  public void testRemoveInternalByKey_thenLinkedTreeMapContainsKey42() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.putIfAbsent("42", "42");

    // Act and Assert
    assertNull(objectObjectMap.removeInternalByKey("Key"));
    assertEquals(1, objectObjectMap.size());
    assertTrue(objectObjectMap.containsKey("42"));
  }

  /**
   * Test {@link LinkedTreeMap#removeInternalByKey(Object)}.
   *
   * <ul>
   *   <li>Then {@link LinkedTreeMap#LinkedTreeMap()} containsKey {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#removeInternalByKey(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.removeInternalByKey(Object)"})
  public void testRemoveInternalByKey_thenLinkedTreeMapContainsKeyTrue() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put(true, "Value");

    // Act and Assert
    assertNull(objectObjectMap.removeInternalByKey("Key"));
    assertEquals(1, objectObjectMap.size());
    assertTrue(objectObjectMap.containsKey(true));
  }

  /**
   * Test {@link LinkedTreeMap#removeInternalByKey(Object)}.
   *
   * <ul>
   *   <li>Then return {@link Node#next} {@link Node#next} Value is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#removeInternalByKey(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.removeInternalByKey(Object)"})
  public void testRemoveInternalByKey_thenReturnNextNextValueIs42() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.putIfAbsent("foo", "42");
    objectObjectMap.put("com.google.gson.internal.LinkedTreeMap", "Value");
    objectObjectMap.put("Key", "Value");

    // Act
    Node<Object, Object> actualRemoveInternalByKeyResult =
        objectObjectMap.removeInternalByKey("Key");

    // Assert
    Node<Object, Object> node = actualRemoveInternalByKeyResult.next.next;
    assertEquals("42", node.getValue());
    assertEquals("foo", node.getKey());
    assertNull(node.left);
    assertEquals(1, node.height);
    assertEquals(2, actualRemoveInternalByKeyResult.prev.height);
    assertSame(objectObjectMap.root, node.next);
    assertSame(actualRemoveInternalByKeyResult.prev, node.parent);
  }

  /**
   * Test {@link LinkedTreeMap#removeInternalByKey(Object)}.
   *
   * <ul>
   *   <li>Then return {@link Node#next} {@link Node#next} Value is {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#removeInternalByKey(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.removeInternalByKey(Object)"})
  public void testRemoveInternalByKey_thenReturnNextNextValueIsValue() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("com.google.gson.internal.LinkedTreeMap", "Value");
    objectObjectMap.putIfAbsent("42", "42");
    objectObjectMap.put("Key", "Value");

    // Act
    Node<Object, Object> actualRemoveInternalByKeyResult =
        objectObjectMap.removeInternalByKey("Key");

    // Assert
    Node<Object, Object> node = actualRemoveInternalByKeyResult.next.next;
    assertEquals("Value", node.getValue());
    assertEquals("com.google.gson.internal.LinkedTreeMap", node.getKey());
    assertNull(node.parent);
    assertEquals(2, node.height);
    assertSame(objectObjectMap.root, actualRemoveInternalByKeyResult.prev.parent);
    Node<Object, Object> node2 = actualRemoveInternalByKeyResult.prev;
    assertSame(node2, node.first());
    assertSame(node2, node.left);
  }

  /**
   * Test {@link LinkedTreeMap#removeInternalByKey(Object)}.
   *
   * <ul>
   *   <li>Then return {@link Node#prev} Value is {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#removeInternalByKey(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.removeInternalByKey(Object)"})
  public void testRemoveInternalByKey_thenReturnPrevValueIsValue() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("com.google.gson.internal.LinkedTreeMap", "Value");
    objectObjectMap.put("Key", "Value");

    // Act
    Node<Object, Object> actualRemoveInternalByKeyResult =
        objectObjectMap.removeInternalByKey("Key");

    // Assert
    Node<Object, Object> node = actualRemoveInternalByKeyResult.prev;
    assertEquals("Value", node.getValue());
    assertEquals("com.google.gson.internal.LinkedTreeMap", node.getKey());
    assertEquals(1, node.height);
    assertSame(objectObjectMap.header, node.prev);
    Node<Object, Object> node2 = objectObjectMap.root;
    assertSame(node2, node.first());
    Node<Object, Object> node3 = actualRemoveInternalByKeyResult.next;
    assertSame(node2, node3.next);
    assertSame(node2, node3.prev);
  }

  /**
   * Test {@link LinkedTreeMap#removeInternalByKey(Object)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return Key is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#removeInternalByKey(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.removeInternalByKey(Object)"})
  public void testRemoveInternalByKey_when42_thenReturnKeyIs42() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.putIfAbsent("42", "42");
    objectObjectMap.put("Key", "Value");

    // Act
    Node<Object, Object> actualRemoveInternalByKeyResult =
        objectObjectMap.removeInternalByKey("42");

    // Assert
    assertEquals("42", actualRemoveInternalByKeyResult.getKey());
    assertEquals("42", actualRemoveInternalByKeyResult.getValue());
    Node<Object, Object> node = actualRemoveInternalByKeyResult.next;
    assertEquals("Key", node.getKey());
    assertEquals("Value", node.getValue());
    assertSame(actualRemoveInternalByKeyResult, actualRemoveInternalByKeyResult.first());
    Node<Object, Object> node2 = actualRemoveInternalByKeyResult.next;
    assertSame(node2, node.first());
    Node<Object, Object> node3 = actualRemoveInternalByKeyResult.prev;
    assertSame(node2, node3.next);
    assertSame(node2, node3.prev);
    Node<Object, Object> node4 = actualRemoveInternalByKeyResult.prev;
    assertSame(node4, node3.first());
    assertSame(node4, node.next);
    assertSame(node4, node.prev);
  }

  /**
   * Test {@link LinkedTreeMap#entrySet()}.
   *
   * <p>Method under test: {@link LinkedTreeMap#entrySet()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Set LinkedTreeMap.entrySet()"})
  public void testEntrySet() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act and Assert
    assertTrue(objectObjectMap.entrySet().isEmpty());
  }

  /**
   * Test {@link LinkedTreeMap#keySet()}.
   *
   * <p>Method under test: {@link LinkedTreeMap#keySet()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Set LinkedTreeMap.keySet()"})
  public void testKeySet() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act and Assert
    assertTrue(objectObjectMap.keySet().isEmpty());
  }
}
