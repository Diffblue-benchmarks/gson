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
import java.nio.charset.Charset;
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
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code Key} is {@code Value}.
   *   <li>When forName {@code UTF-8}.
   * </ul>
   *
   * <p>Method under test: {@link KeySet#contains(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean KeySet.contains(Object)"})
  public void testKeySetContains_givenLinkedTreeMapKeyIsValue_whenForNameUtf8() {
    // Arrange
    LinkedTreeMap linkedTreeMap = new LinkedTreeMap();
    linkedTreeMap.put("Key", "Value");
    KeySet keySet = linkedTreeMap.new KeySet();

    // Act and Assert
    assertFalse(keySet.contains(Charset.forName("UTF-8")));
  }

  /**
   * Test KeySet {@link KeySet#contains(Object)}.
   *
   * <ul>
   *   <li>When forName {@code UTF-8}.
   * </ul>
   *
   * <p>Method under test: {@link KeySet#contains(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean KeySet.contains(Object)"})
  public void testKeySetContains_whenForNameUtf8() {
    // Arrange
    KeySet keySet = new LinkedTreeMap().new KeySet();

    // Act and Assert
    assertFalse(keySet.contains(Charset.forName("UTF-8")));
  }

  /**
   * Test KeySet {@link KeySet#contains(Object)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link KeySet#contains(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean KeySet.contains(Object)"})
  public void testKeySetContains_whenNull() {
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
    assertEquals(node.hashCode(), node2.hashCode());
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
    Node<Object, Object> node = new Node<>(true);
    node.setValue("Value");

    Node<Object, Object> node2 = new Node<>(true);
    node2.setValue("Value");

    // Act and Assert
    assertEquals(node, node2);
    assertEquals(node.hashCode(), node2.hashCode());
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
    Node<Object, Object> parent = new Node<>(true);
    Node<Object, Object> next = new Node<>(true);

    Node<Object, Object> node = new Node<>(true, parent, "Key", next, new Node<>(true));
    node.setValue("Value");
    SimpleEntry<Object, Object> simpleEntry = new SimpleEntry<>("Key", "Value");

    // Act and Assert
    assertEquals(node, simpleEntry);
    assertEquals(node.hashCode(), simpleEntry.hashCode());
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
    assertNotEquals(node, new SimpleEntry<>("Key", "Value"));
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
    assertNotEquals(node, new SimpleEntry<>("Key", "Value"));
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
    node.setValue(new Node<>(true));

    // Act and Assert
    assertNotEquals(node, new Node<>(true));
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
    Node<Object, Object> expectedFirstResult = actualFirstResult.prev;
    assertSame(expectedFirstResult, actualFirstResult);
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
    Node<Object, Object> actualFirstResult = actualNode.first();
    assertSame(actualNode, actualFirstResult);
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
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code 42} is {@code Value}.
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
  public void testGet_givenLinkedTreeMap42IsValue_whenKey_thenReturnNull() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("42", "Value");

    // Act and Assert
    assertNull(objectObjectMap.get("Key"));
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
   * Test {@link LinkedTreeMap#get(Object)}.
   *
   * <ul>
   *   <li>Then return {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#get(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.get(Object)"})
  public void testGet_thenReturnValue() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("com.google.gson.internal.LinkedTreeMap", "Value");
    objectObjectMap.put("Key", "Value");

    // Act and Assert
    assertEquals("Value", objectObjectMap.get("Key"));
  }

  /**
   * Test {@link LinkedTreeMap#containsKey(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code 42} is {@code Value}.
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
  public void testContainsKey_givenLinkedTreeMap42IsValue_whenKey_thenReturnFalse() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("42", "Value");

    // Act and Assert
    assertFalse(objectObjectMap.containsKey("Key"));
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
   * Test {@link LinkedTreeMap#containsKey(Object)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#containsKey(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean LinkedTreeMap.containsKey(Object)"})
  public void testContainsKey_thenReturnTrue() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("com.google.gson.internal.LinkedTreeMap", "Value");
    objectObjectMap.put("Key", "Value");

    // Act and Assert
    assertTrue(objectObjectMap.containsKey("Key"));
  }

  /**
   * Test {@link LinkedTreeMap#put(Object, Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code 42} is {@code Value}.
   *   <li>When {@code Key}.
   *   <li>Then {@link LinkedTreeMap#LinkedTreeMap()} containsKey {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#put(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.put(Object, Object)"})
  public void testPut_givenLinkedTreeMap42IsValue_whenKey_thenLinkedTreeMapContainsKey42() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("42", "Value");

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
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code Key} is {@code Value}.
   *   <li>When {@code Key}.
   *   <li>Then return {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#put(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.put(Object, Object)"})
  public void testPut_givenLinkedTreeMapKeyIsValue_whenKey_thenReturnValue() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("Key", "Value");

    // Act
    Object actualPutResult = objectObjectMap.put("Key", "Value");

    // Assert
    assertEquals(1, objectObjectMap.size());
    assertEquals("Value", actualPutResult);
    assertTrue(objectObjectMap.containsKey("Key"));
  }

  /**
   * Test {@link LinkedTreeMap#put(Object, Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()}.
   *   <li>When forName {@code UTF-8}.
   *   <li>Then {@link LinkedTreeMap#LinkedTreeMap()} size is one.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#put(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.put(Object, Object)"})
  public void testPut_givenLinkedTreeMap_whenForNameUtf8_thenLinkedTreeMapSizeIsOne() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act and Assert
    assertNull(objectObjectMap.put(Charset.forName("UTF-8"), null));
    assertEquals(1, objectObjectMap.size());
  }

  /**
   * Test {@link LinkedTreeMap#put(Object, Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()}.
   *   <li>When {@code Key}.
   *   <li>Then {@link LinkedTreeMap#LinkedTreeMap()} size is one.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#put(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.put(Object, Object)"})
  public void testPut_givenLinkedTreeMap_whenKey_thenLinkedTreeMapSizeIsOne() {
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
   * <p>Method under test: {@link LinkedTreeMap#remove(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.remove(Object)"})
  public void testRemoveWithObject2() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("Key", "Value");
    objectObjectMap.put("com.google.gson.internal.LinkedTreeMap", "Value");

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
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code 42} is {@code Value}.
   *   <li>Then {@link LinkedTreeMap#LinkedTreeMap()} containsKey {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#remove(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.remove(Object)"})
  public void testRemoveWithObject_givenLinkedTreeMap42IsValue_thenLinkedTreeMapContainsKey42() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("42", "Value");
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
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code 42} is {@code Value}.
   *   <li>Then {@link LinkedTreeMap#LinkedTreeMap()} containsKey {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#remove(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.remove(Object)"})
  public void testRemoveWithObject_givenLinkedTreeMap42IsValue_thenLinkedTreeMapContainsKey422() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("Key", "Value");
    objectObjectMap.put("42", "Value");

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
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code 42} is {@code Value}.
   *   <li>Then {@link LinkedTreeMap#LinkedTreeMap()} size is two.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#remove(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.remove(Object)"})
  public void testRemoveWithObject_givenLinkedTreeMap42IsValue_thenLinkedTreeMapSizeIsTwo() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("42", "Value");
    objectObjectMap.put("Key", "Value");
    objectObjectMap.put("com.google.gson.internal.LinkedTreeMap", "Value");

    // Act
    Object actualRemoveResult = objectObjectMap.remove("Key");

    // Assert
    assertEquals(2, objectObjectMap.size());
    assertEquals("Value", actualRemoveResult);
    assertTrue(objectObjectMap.containsKey("42"));
    assertTrue(objectObjectMap.containsKey("com.google.gson.internal.LinkedTreeMap"));
  }

  /**
   * Test {@link LinkedTreeMap#remove(Object)} with {@code Object}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code 42} is {@code Value}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#remove(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.remove(Object)"})
  public void testRemoveWithObject_givenLinkedTreeMap42IsValue_thenReturnNull() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("42", "Value");

    // Act and Assert
    assertNull(objectObjectMap.remove("Key"));
    assertEquals(1, objectObjectMap.size());
    assertTrue(objectObjectMap.containsKey("42"));
  }

  /**
   * Test {@link LinkedTreeMap#remove(Object)} with {@code Object}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code Key} is {@code false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#remove(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.remove(Object)"})
  public void testRemoveWithObject_givenLinkedTreeMapKeyIsFalse_thenReturnFalse() {
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
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#remove(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.remove(Object)"})
  public void testRemoveWithObject_givenLinkedTreeMapKeyIsTrue_thenReturnTrue() {
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
   *   <li>Then {@link LinkedTreeMap#LinkedTreeMap()} Empty.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#remove(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.remove(Object)"})
  public void testRemoveWithObject_givenLinkedTreeMapKeyIsValue_thenLinkedTreeMapEmpty() {
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
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#remove(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.remove(Object)"})
  public void testRemoveWithObject_givenLinkedTreeMap_thenReturnNull() {
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
   *   <li>Then {@link LinkedTreeMap#LinkedTreeMap()} containsKey {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#remove(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.remove(Object)"})
  public void testRemoveWithObject_thenLinkedTreeMapContainsKeyFoo() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.putIfAbsent("foo", "Value");
    objectObjectMap.put("Key", "Value");
    objectObjectMap.put("com.google.gson.internal.LinkedTreeMap", "Value");

    // Act
    Object actualRemoveResult = objectObjectMap.remove("Key");

    // Assert
    assertEquals(2, objectObjectMap.size());
    assertEquals("Value", actualRemoveResult);
    assertTrue(objectObjectMap.containsKey("com.google.gson.internal.LinkedTreeMap"));
    assertTrue(objectObjectMap.containsKey("foo"));
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
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#remove(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.remove(Object)"})
  public void testRemoveWithObject_thenReturnNull() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("com.google.gson.internal.LinkedTreeMap", "Value");

    // Act and Assert
    assertNull(objectObjectMap.remove("Key"));
    assertEquals(1, objectObjectMap.size());
    assertTrue(objectObjectMap.containsKey("com.google.gson.internal.LinkedTreeMap"));
  }

  /**
   * Test {@link LinkedTreeMap#find(Object, boolean)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code 42} is {@code Value}.
   *   <li>When {@code Key}.
   *   <li>Then return {@link Node#parent} Key is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#find(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.find(Object, boolean)"})
  public void testFind_givenLinkedTreeMap42IsValue_whenKey_thenReturnParentKeyIs42() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("42", "Value");

    // Act
    Node<Object, Object> actualFindResult = objectObjectMap.find("Key", true);

    // Assert
    Node<Object, Object> node = actualFindResult.parent;
    assertEquals("42", node.getKey());
    Node<Object, Object> node2 = actualFindResult.prev;
    assertEquals("42", node2.getKey());
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
   *   <li>Then {@link LinkedTreeMap#LinkedTreeMap()} size is one.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#find(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.find(Object, boolean)"})
  public void testFind_givenLinkedTreeMapKeyIsValue_whenKey_thenLinkedTreeMapSizeIsOne() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("Key", "Value");

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
   *   <li>When forName {@code UTF-8}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#find(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.find(Object, boolean)"})
  public void testFind_givenLinkedTreeMap_whenForNameUtf8_thenReturnNull() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act and Assert
    assertNull(objectObjectMap.find(Charset.forName("UTF-8"), false));
    assertTrue(objectObjectMap.isEmpty());
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
   *   <li>Then return {@link Node#parent} Key is {@code LinkedTreeMap}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#find(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.find(Object, boolean)"})
  public void testFind_thenReturnParentKeyIsComGoogleGsonInternalLinkedTreeMap() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("com.google.gson.internal.LinkedTreeMap", "Value");

    // Act
    Node<Object, Object> actualFindResult = objectObjectMap.find("Key", true);

    // Assert
    Node<Object, Object> node = actualFindResult.parent;
    assertEquals("com.google.gson.internal.LinkedTreeMap", node.getKey());
    Node<Object, Object> node2 = actualFindResult.prev;
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
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code 42} is {@code Value}.
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
  public void testFindByObject_givenLinkedTreeMap42IsValue_whenKey_thenReturnNull() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("42", "Value");

    // Act and Assert
    assertNull(objectObjectMap.findByObject("Key"));
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

    // Act
    Node<Object, Object> actualFindByObjectResult = objectObjectMap.findByObject("Key");

    // Assert
    assertSame(objectObjectMap.root, actualFindByObjectResult);
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
   * Test {@link LinkedTreeMap#findByObject(Object)}.
   *
   * <ul>
   *   <li>Then return {@link Node#parent} Value is {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#findByObject(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.findByObject(Object)"})
  public void testFindByObject_thenReturnParentValueIsValue() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("com.google.gson.internal.LinkedTreeMap", "Value");
    objectObjectMap.put("Key", "Value");

    // Act
    Node<Object, Object> actualFindByObjectResult = objectObjectMap.findByObject("Key");

    // Assert
    Node<Object, Object> node = actualFindByObjectResult.parent;
    assertEquals("Value", node.getValue());
    Node<Object, Object> node2 = actualFindByObjectResult.prev;
    assertEquals("Value", node2.getValue());
    assertEquals("com.google.gson.internal.LinkedTreeMap", node.getKey());
    assertEquals("com.google.gson.internal.LinkedTreeMap", node2.getKey());
    assertNull(node.parent);
    assertNull(node.right);
    assertEquals(2, node.height);
    assertEquals(2, node2.height);
    assertTrue(node.allowNullValue);
    assertSame(actualFindByObjectResult, node.first());
    assertSame(actualFindByObjectResult, node2.first());
    assertSame(actualFindByObjectResult, node.left);
    assertSame(actualFindByObjectResult, node2.left);
    assertSame(actualFindByObjectResult, node.next);
    Node<Object, Object> node3 = actualFindByObjectResult.next;
    assertSame(node3, node.prev);
    assertSame(node3, node2.prev);
    Node<Object, Object> expectedNode = actualFindByObjectResult.prev;
    assertSame(expectedNode, actualFindByObjectResult.next.next);
  }

  /**
   * Test {@link LinkedTreeMap#findByEntry(Entry)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code 42} is {@code Value}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#findByEntry(Entry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.findByEntry(Entry)"})
  public void testFindByEntry_givenLinkedTreeMap42IsValue_thenReturnNull() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("42", "Value");

    // Act
    Node<Object, Object> actualFindByEntryResult =
        objectObjectMap.findByEntry(new SimpleEntry<>("Key", "Value"));

    // Assert
    assertNull(actualFindByEntryResult);
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

    // Act
    Node<Object, Object> actualFindByEntryResult =
        objectObjectMap.findByEntry(new SimpleEntry<>("Key", "Value"));

    // Assert
    assertNull(actualFindByEntryResult);
  }

  /**
   * Test {@link LinkedTreeMap#findByEntry(Entry)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code Key} is {@code 42}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#findByEntry(Entry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.findByEntry(Entry)"})
  public void testFindByEntry_givenLinkedTreeMapKeyIs42_thenReturnNull() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("Key", "42");

    // Act
    Node<Object, Object> actualFindByEntryResult =
        objectObjectMap.findByEntry(new SimpleEntry<>("Key", "Value"));

    // Assert
    assertNull(actualFindByEntryResult);
  }

  /**
   * Test {@link LinkedTreeMap#findByEntry(Entry)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code Key} is {@code Value}.
   *   <li>Then return {@link LinkedTreeMap#LinkedTreeMap()} {@link LinkedTreeMap#root}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#findByEntry(Entry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.findByEntry(Entry)"})
  public void testFindByEntry_givenLinkedTreeMapKeyIsValue_thenReturnLinkedTreeMapRoot() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("Key", "Value");

    // Act
    Node<Object, Object> actualFindByEntryResult =
        objectObjectMap.findByEntry(new SimpleEntry<>("Key", "Value"));

    // Assert
    assertSame(objectObjectMap.root, actualFindByEntryResult);
  }

  /**
   * Test {@link LinkedTreeMap#findByEntry(Entry)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code true} is {@code Value}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#findByEntry(Entry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.findByEntry(Entry)"})
  public void testFindByEntry_givenLinkedTreeMapTrueIsValue_thenReturnNull() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put(true, "Value");

    // Act
    Node<Object, Object> actualFindByEntryResult =
        objectObjectMap.findByEntry(new SimpleEntry<>("Key", "Value"));

    // Assert
    assertNull(actualFindByEntryResult);
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

    // Act
    Node<Object, Object> actualFindByEntryResult = objectObjectMap.findByEntry(new Node<>(true));

    // Assert
    assertNull(actualFindByEntryResult);
  }

  /**
   * Test {@link LinkedTreeMap#findByEntry(Entry)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()}.
   *   <li>When {@link SimpleEntry#SimpleEntry(Object, Object)} with {@code Key} and {@code Value}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#findByEntry(Entry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.findByEntry(Entry)"})
  public void testFindByEntry_givenLinkedTreeMap_whenSimpleEntryWithKeyAndValue_thenReturnNull() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act
    Node<Object, Object> actualFindByEntryResult =
        objectObjectMap.findByEntry(new SimpleEntry<>("Key", "Value"));

    // Assert
    assertNull(actualFindByEntryResult);
  }

  /**
   * Test {@link LinkedTreeMap#findByEntry(Entry)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()}.
   *   <li>When {@link SimpleEntry#SimpleEntry(Object, Object)} with {@code null} and {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#findByEntry(Entry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.findByEntry(Entry)"})
  public void testFindByEntry_givenLinkedTreeMap_whenSimpleEntryWithNullAndValue() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act
    Node<Object, Object> actualFindByEntryResult =
        objectObjectMap.findByEntry(new SimpleEntry<>(null, "Value"));

    // Assert
    assertNull(actualFindByEntryResult);
  }

  /**
   * Test {@link LinkedTreeMap#findByEntry(Entry)}.
   *
   * <ul>
   *   <li>Then return {@link SimpleEntry#SimpleEntry(Object, Object)} with {@code Key} and {@code
   *       Value}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#findByEntry(Entry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.findByEntry(Entry)"})
  public void testFindByEntry_thenReturnSimpleEntryWithKeyAndValue() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("com.google.gson.internal.LinkedTreeMap", "Value");
    objectObjectMap.put("Key", "Value");
    SimpleEntry<Object, Object> entry = new SimpleEntry<>("Key", "Value");

    // Act
    Node<Object, Object> actualFindByEntryResult = objectObjectMap.findByEntry(entry);

    // Assert
    assertEquals(entry, actualFindByEntryResult);
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
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code 42} is {@code Value}.
   *   <li>Then {@link LinkedTreeMap#LinkedTreeMap()} size is one.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#removeInternalByKey(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.removeInternalByKey(Object)"})
  public void testRemoveInternalByKey_givenLinkedTreeMap42IsValue_thenLinkedTreeMapSizeIsOne() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("42", "Value");

    // Act and Assert
    assertNull(objectObjectMap.removeInternalByKey("Key"));
    assertEquals(1, objectObjectMap.size());
    assertTrue(objectObjectMap.containsKey("42"));
  }

  /**
   * Test {@link LinkedTreeMap#removeInternalByKey(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code 42} is {@code Value}.
   *   <li>Then return {@link Node#next} Key is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#removeInternalByKey(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.removeInternalByKey(Object)"})
  public void testRemoveInternalByKey_givenLinkedTreeMap42IsValue_thenReturnNextKeyIs42() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("Key", "Value");
    objectObjectMap.put("42", "Value");

    // Act
    Node<Object, Object> actualRemoveInternalByKeyResult =
        objectObjectMap.removeInternalByKey("Key");

    // Assert
    Node<Object, Object> node = actualRemoveInternalByKeyResult.next;
    assertEquals("42", node.getKey());
    Node<Object, Object> node2 = actualRemoveInternalByKeyResult.prev;
    assertNull(node2.getKey());
    assertNull(node2.getValue());
    assertEquals(0, node2.height);
    assertEquals(1, node.height);
    Node<Object, Object> node3 = objectObjectMap.header;
    assertSame(node3, node2.first());
    assertSame(node3, node.prev);
    Node<Object, Object> node4 = objectObjectMap.root;
    assertSame(node4, node.first());
    assertSame(node4, node2.prev);
  }

  /**
   * Test {@link LinkedTreeMap#removeInternalByKey(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code 42} is {@code Value}.
   *   <li>Then return {@link Node#next} {@link Node#left} is {@link Node#prev}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#removeInternalByKey(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.removeInternalByKey(Object)"})
  public void testRemoveInternalByKey_givenLinkedTreeMap42IsValue_thenReturnNextLeftIsPrev() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("42", "Value");
    objectObjectMap.put("Key", "Value");
    objectObjectMap.put("com.google.gson.internal.LinkedTreeMap", "Value");

    // Act
    Node<Object, Object> actualRemoveInternalByKeyResult =
        objectObjectMap.removeInternalByKey("Key");

    // Assert
    assertEquals(2, objectObjectMap.size());
    assertTrue(objectObjectMap.containsKey("42"));
    assertTrue(objectObjectMap.containsKey("com.google.gson.internal.LinkedTreeMap"));
    Node<Object, Object> node = actualRemoveInternalByKeyResult.prev;
    Node<Object, Object> node2 = actualRemoveInternalByKeyResult.next;
    assertSame(node, node2.left);
    assertSame(node, node2.next.next);
  }

  /**
   * Test {@link LinkedTreeMap#removeInternalByKey(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code 42} is {@code Value}.
   *   <li>Then return {@link Node#prev} Key is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#removeInternalByKey(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.removeInternalByKey(Object)"})
  public void testRemoveInternalByKey_givenLinkedTreeMap42IsValue_thenReturnPrevKeyIs42() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("42", "Value");
    objectObjectMap.put("Key", "Value");

    // Act
    Node<Object, Object> actualRemoveInternalByKeyResult =
        objectObjectMap.removeInternalByKey("Key");

    // Assert
    Node<Object, Object> node = actualRemoveInternalByKeyResult.prev;
    assertEquals("42", node.getKey());
    Node<Object, Object> node2 = actualRemoveInternalByKeyResult.next;
    assertNull(node2.getKey());
    assertNull(node2.getValue());
    assertEquals(0, node2.height);
    Node<Object, Object> node3 = objectObjectMap.header;
    assertSame(node3, node2.first());
    assertSame(node3, node.next);
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
   *   <li>Then {@link LinkedTreeMap#LinkedTreeMap()} size is one.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#removeInternalByKey(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.removeInternalByKey(Object)"})
  public void testRemoveInternalByKey_thenLinkedTreeMapSizeIsOne() {
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
   *   <li>Then {@link LinkedTreeMap#LinkedTreeMap()} size is three.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#removeInternalByKey(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.removeInternalByKey(Object)"})
  public void testRemoveInternalByKey_thenLinkedTreeMapSizeIsThree() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.putIfAbsent("foo", "Value");
    objectObjectMap.put("42", "Value");
    objectObjectMap.put("Key", "Value");
    objectObjectMap.put("com.google.gson.internal.LinkedTreeMap", "Value");

    // Act
    Node<Object, Object> actualRemoveInternalByKeyResult =
        objectObjectMap.removeInternalByKey("Key");

    // Assert
    assertEquals(3, objectObjectMap.size());
    Node<Object, Object> node = actualRemoveInternalByKeyResult.next.right;
    assertEquals("Value", node.getValue());
    assertEquals("foo", node.getKey());
    assertNull(node.left);
    assertNull(node.right);
    assertEquals(1, node.height);
    assertTrue(objectObjectMap.containsKey("42"));
    assertTrue(objectObjectMap.containsKey("com.google.gson.internal.LinkedTreeMap"));
    assertTrue(objectObjectMap.containsKey("foo"));
    assertTrue(node.allowNullValue);
    assertSame(objectObjectMap.header, node.prev);
    assertSame(actualRemoveInternalByKeyResult.next, node.parent);
    assertSame(actualRemoveInternalByKeyResult.prev, node.next);
  }

  /**
   * Test {@link LinkedTreeMap#removeInternalByKey(Object)}.
   *
   * <ul>
   *   <li>Then return {@link Node#prev} Key is {@code LinkedTreeMap}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#removeInternalByKey(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.removeInternalByKey(Object)"})
  public void testRemoveInternalByKey_thenReturnPrevKeyIsComGoogleGsonInternalLinkedTreeMap() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("com.google.gson.internal.LinkedTreeMap", "Value");
    objectObjectMap.put("Key", "Value");

    // Act
    Node<Object, Object> actualRemoveInternalByKeyResult =
        objectObjectMap.removeInternalByKey("Key");

    // Assert
    Node<Object, Object> node = actualRemoveInternalByKeyResult.prev;
    assertEquals("com.google.gson.internal.LinkedTreeMap", node.getKey());
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
   *   <li>Then return {@link Node#prev} Key is {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#removeInternalByKey(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.removeInternalByKey(Object)"})
  public void testRemoveInternalByKey_thenReturnPrevKeyIsFoo() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.putIfAbsent("foo", "Value");
    objectObjectMap.put("Key", "Value");
    objectObjectMap.put("com.google.gson.internal.LinkedTreeMap", "Value");

    // Act
    Node<Object, Object> actualRemoveInternalByKeyResult =
        objectObjectMap.removeInternalByKey("Key");

    // Assert
    assertEquals(2, objectObjectMap.size());
    assertEquals("foo", actualRemoveInternalByKeyResult.prev.getKey());
    assertTrue(objectObjectMap.containsKey("com.google.gson.internal.LinkedTreeMap"));
    assertTrue(objectObjectMap.containsKey("foo"));
    Node<Object, Object> node = actualRemoveInternalByKeyResult.prev;
    Node<Object, Object> node2 = actualRemoveInternalByKeyResult.next;
    assertSame(node, node2.next.next);
    assertSame(node, node2.right);
  }

  /**
   * Test {@link LinkedTreeMap#removeInternalByKey(Object)}.
   *
   * <ul>
   *   <li>Then return {@link Node#prev} Key is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#removeInternalByKey(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.removeInternalByKey(Object)"})
  public void testRemoveInternalByKey_thenReturnPrevKeyIsNull() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("Key", "Value");
    objectObjectMap.put("com.google.gson.internal.LinkedTreeMap", "Value");

    // Act
    Node<Object, Object> actualRemoveInternalByKeyResult =
        objectObjectMap.removeInternalByKey("Key");

    // Assert
    Node<Object, Object> node = actualRemoveInternalByKeyResult.prev;
    assertNull(node.getKey());
    assertNull(node.getValue());
    assertEquals(0, node.height);
    Node<Object, Object> node2 = actualRemoveInternalByKeyResult.next;
    assertEquals(1, node2.height);
    Node<Object, Object> node3 = objectObjectMap.header;
    assertSame(node3, node.first());
    assertSame(node3, node2.prev);
    Node<Object, Object> node4 = objectObjectMap.root;
    assertSame(node4, node2.first());
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

  /**
   * Test {@link LinkedTreeMap#createEntrySetForTesting()}.
   *
   * <p>Method under test: {@link LinkedTreeMap#createEntrySetForTesting()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"EntrySet LinkedTreeMap.createEntrySetForTesting()"})
  public void testCreateEntrySetForTesting() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act and Assert
    assertTrue(objectObjectMap.createEntrySetForTesting().isEmpty());
  }

  /**
   * Test {@link LinkedTreeMap#createKeySetForTesting()}.
   *
   * <p>Method under test: {@link LinkedTreeMap#createKeySetForTesting()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"KeySet LinkedTreeMap.createKeySetForTesting()"})
  public void testCreateKeySetForTesting() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act and Assert
    assertTrue(objectObjectMap.createKeySetForTesting().isEmpty());
  }
}
