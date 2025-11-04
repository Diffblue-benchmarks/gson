package com.google.gson.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import org.junit.Test;

public class LinkedTreeMapDiffblueTest {
  /** Method under test: {@link LinkedTreeMap.EntrySet#EntrySet(LinkedTreeMap)} */
  @Test
  public void testEntrySetNewEntrySet() {
    // Arrange, Act and Assert
    assertTrue(((new LinkedTreeMap()).new EntrySet()).isEmpty());
  }

  /** Method under test: {@link LinkedTreeMap.KeySet#KeySet(LinkedTreeMap)} */
  @Test
  public void testKeySetNewKeySet() {
    // Arrange, Act and Assert
    assertTrue(((new LinkedTreeMap()).new KeySet()).isEmpty());
  }

  /** Method under test: {@link LinkedTreeMap#LinkedTreeMap()} */
  @Test
  public void testNewLinkedTreeMap() {
    // Arrange and Act
    LinkedTreeMap<Object, Object> actualObjectObjectMap = new LinkedTreeMap<>();

    // Assert
    assertTrue(actualObjectObjectMap.isEmpty());
  }

  /** Method under test: {@link LinkedTreeMap#LinkedTreeMap(Comparator, boolean)} */
  @Test
  public void testNewLinkedTreeMap2() {
    // Arrange and Act
    LinkedTreeMap<Object, Object> actualObjectObjectMap =
        new LinkedTreeMap<>(mock(Comparator.class), true);

    // Assert
    assertTrue(actualObjectObjectMap.isEmpty());
  }

  /** Method under test: {@link LinkedTreeMap#LinkedTreeMap(Comparator, boolean)} */
  @Test
  public void testNewLinkedTreeMap3() {
    // Arrange and Act
    LinkedTreeMap<Object, Object> actualObjectObjectMap = new LinkedTreeMap<>(null, true);

    // Assert
    assertTrue(actualObjectObjectMap.isEmpty());
  }

  /** Method under test: {@link LinkedTreeMap#LinkedTreeMap(boolean)} */
  @Test
  public void testNewLinkedTreeMap4() {
    // Arrange and Act
    LinkedTreeMap<Object, Object> actualObjectObjectMap = new LinkedTreeMap<>(true);

    // Assert
    assertTrue(actualObjectObjectMap.isEmpty());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link LinkedTreeMap.Node#equals(Object)}
   *   <li>{@link LinkedTreeMap.Node#hashCode()}
   * </ul>
   */
  @Test
  public void testNodeEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    LinkedTreeMap.Node<Object, Object> node = new LinkedTreeMap.Node<>(true);
    LinkedTreeMap.Node<Object, Object> node2 = new LinkedTreeMap.Node<>(true);

    // Act and Assert
    assertEquals(node, node2);
    int expectedHashCodeResult = node.hashCode();
    assertEquals(expectedHashCodeResult, node2.hashCode());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link LinkedTreeMap.Node#equals(Object)}
   *   <li>{@link LinkedTreeMap.Node#hashCode()}
   * </ul>
   */
  @Test
  public void testNodeEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual2() {
    // Arrange
    LinkedTreeMap.Node<Object, Object> parent = new LinkedTreeMap.Node<>(true);
    LinkedTreeMap.Node<Object, Object> next = new LinkedTreeMap.Node<>(true);
    LinkedTreeMap.Node<Object, Object> node =
        new LinkedTreeMap.Node<>(true, parent, "Key", next, new LinkedTreeMap.Node<>(true));
    LinkedTreeMap.Node<Object, Object> parent2 = new LinkedTreeMap.Node<>(true);
    LinkedTreeMap.Node<Object, Object> next2 = new LinkedTreeMap.Node<>(true);
    LinkedTreeMap.Node<Object, Object> node2 =
        new LinkedTreeMap.Node<>(true, parent2, "Key", next2, new LinkedTreeMap.Node<>(true));

    // Act and Assert
    assertEquals(node, node2);
    int expectedHashCodeResult = node.hashCode();
    assertEquals(expectedHashCodeResult, node2.hashCode());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link LinkedTreeMap.Node#equals(Object)}
   *   <li>{@link LinkedTreeMap.Node#hashCode()}
   * </ul>
   */
  @Test
  public void testNodeEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual3() {
    // Arrange
    LinkedTreeMap.Node<Object, Object> node = new LinkedTreeMap.Node<>(true);
    node.setValue("Value");

    LinkedTreeMap.Node<Object, Object> node2 = new LinkedTreeMap.Node<>(true);
    node2.setValue("Value");

    // Act and Assert
    assertEquals(node, node2);
    int expectedHashCodeResult = node.hashCode();
    assertEquals(expectedHashCodeResult, node2.hashCode());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link LinkedTreeMap.Node#equals(Object)}
   *   <li>{@link LinkedTreeMap.Node#hashCode()}
   * </ul>
   */
  @Test
  public void testNodeEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    LinkedTreeMap.Node<Object, Object> node = new LinkedTreeMap.Node<>(true);

    // Act and Assert
    assertEquals(node, node);
    int expectedHashCodeResult = node.hashCode();
    assertEquals(expectedHashCodeResult, node.hashCode());
  }

  /** Method under test: {@link LinkedTreeMap.Node#equals(Object)} */
  @Test
  public void testNodeEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    LinkedTreeMap.Node<Object, Object> parent = new LinkedTreeMap.Node<>(true);
    LinkedTreeMap.Node<Object, Object> next = new LinkedTreeMap.Node<>(true);
    LinkedTreeMap.Node<Object, Object> node =
        new LinkedTreeMap.Node<>(true, parent, "Key", next, new LinkedTreeMap.Node<>(true));

    // Act and Assert
    assertNotEquals(node, new LinkedTreeMap.Node<>(true));
  }

  /** Method under test: {@link LinkedTreeMap.Node#equals(Object)} */
  @Test
  public void testNodeEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    LinkedTreeMap.Node<Object, Object> node = new LinkedTreeMap.Node<>(true);

    // Act and Assert
    assertNotEquals(node, new AbstractMap.SimpleEntry<>("42", "42"));
  }

  /** Method under test: {@link LinkedTreeMap.Node#equals(Object)} */
  @Test
  public void testNodeEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    LinkedTreeMap.Node<Object, Object> node = new LinkedTreeMap.Node<>(true);
    node.setValue("Value");

    // Act and Assert
    assertNotEquals(node, new LinkedTreeMap.Node<>(true));
  }

  /** Method under test: {@link LinkedTreeMap.Node#equals(Object)} */
  @Test
  public void testNodeEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    LinkedTreeMap.Node<Object, Object> parent = new LinkedTreeMap.Node<>(true);
    LinkedTreeMap.Node<Object, Object> node = new LinkedTreeMap.Node<>(true);
    LinkedTreeMap.Node<Object, Object> next = new LinkedTreeMap.Node<>(true);
    LinkedTreeMap.Node<Object, Object> node2 =
        new LinkedTreeMap.Node<>(true, parent, node, next, new LinkedTreeMap.Node<>(true));

    // Act and Assert
    assertNotEquals(node2, new LinkedTreeMap.Node<>(true));
  }

  /** Method under test: {@link LinkedTreeMap.Node#equals(Object)} */
  @Test
  public void testNodeEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    LinkedTreeMap.Node<Object, Object> parent = new LinkedTreeMap.Node<>(true);
    LinkedTreeMap.Node<Object, Object> next = new LinkedTreeMap.Node<>(true);
    LinkedTreeMap.Node<Object, Object> node =
        new LinkedTreeMap.Node<>(true, parent, "Key", next, new LinkedTreeMap.Node<>(true));

    // Act and Assert
    assertNotEquals(node, new AbstractMap.SimpleEntry<>("42", "42"));
  }

  /** Method under test: {@link LinkedTreeMap.Node#equals(Object)} */
  @Test
  public void testNodeEquals_whenOtherIsDifferent_thenReturnNotEqual6() {
    // Arrange
    LinkedTreeMap.Node<Object, Object> node = new LinkedTreeMap.Node<>(true);

    LinkedTreeMap.Node<Object, Object> node2 = new LinkedTreeMap.Node<>(true);
    node2.setValue("Value");

    // Act and Assert
    assertNotEquals(node, node2);
  }

  /** Method under test: {@link LinkedTreeMap.Node#equals(Object)} */
  @Test
  public void testNodeEquals_whenOtherIsDifferent_thenReturnNotEqual7() {
    // Arrange
    LinkedTreeMap.Node<Object, Object> node = new LinkedTreeMap.Node<>(true);

    // Act and Assert
    assertNotEquals(node, new AbstractMap.SimpleEntry<>(null, "42"));
  }

  /** Method under test: {@link LinkedTreeMap.Node#equals(Object)} */
  @Test
  public void testNodeEquals_whenOtherIsDifferent_thenReturnNotEqual8() {
    // Arrange
    LinkedTreeMap.Node<Object, Object> node = new LinkedTreeMap.Node<>(true);
    node.setValue(new LinkedTreeMap.Node<>(true));

    // Act and Assert
    assertNotEquals(node, new LinkedTreeMap.Node<>(true));
  }

  /** Method under test: {@link LinkedTreeMap.Node#equals(Object)} */
  @Test
  public void testNodeEquals_whenOtherIsDifferent_thenReturnNotEqual9() {
    // Arrange
    LinkedTreeMap.Node<Object, Object> node = new LinkedTreeMap.Node<>(true);
    node.setValue("Value");

    // Act and Assert
    assertNotEquals(node, new AbstractMap.SimpleEntry<>(null, "42"));
  }

  /** Method under test: {@link LinkedTreeMap.Node#equals(Object)} */
  @Test
  public void testNodeEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    LinkedTreeMap.Node<Object, Object> node = new LinkedTreeMap.Node<>(true);

    // Act and Assert
    assertNotEquals(node, null);
  }

  /** Method under test: {@link LinkedTreeMap.Node#equals(Object)} */
  @Test
  public void testNodeEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    LinkedTreeMap.Node<Object, Object> node = new LinkedTreeMap.Node<>(true);

    // Act and Assert
    assertNotEquals(node, "Different type to Node");
  }

  /** Method under test: {@link LinkedTreeMap.Node#first()} */
  @Test
  public void testNodeFirst() {
    // Arrange
    LinkedTreeMap.Node<Object, Object> node = new LinkedTreeMap.Node<>(true);

    // Act
    LinkedTreeMap.Node<Object, Object> actualFirstResult = node.first();

    // Assert
    LinkedTreeMap.Node<Object, Object> node2 = actualFirstResult.prev;
    assertSame(node2, actualFirstResult);
    LinkedTreeMap.Node<Object, Object> node3 = node.next;
    assertSame(node2, node3.next);
    LinkedTreeMap.Node<Object, Object> node4 = node.prev;
    assertSame(node2, node4.next);
    assertSame(node2, node3.prev);
    assertSame(node2, node4.prev);
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link LinkedTreeMap.Node#toString()}
   *   <li>{@link LinkedTreeMap.Node#getKey()}
   *   <li>{@link LinkedTreeMap.Node#getValue()}
   * </ul>
   */
  @Test
  public void testNodeGettersAndSetters() {
    // Arrange
    LinkedTreeMap.Node<Object, Object> node = new LinkedTreeMap.Node<>(true);

    // Act
    String actualToStringResult = node.toString();
    Object actualKey = node.getKey();

    // Assert
    assertEquals("null=null", actualToStringResult);
    assertNull(actualKey);
    assertNull(node.getValue());
  }

  /** Method under test: {@link LinkedTreeMap.Node#last()} */
  @Test
  public void testNodeLast() {
    // Arrange
    LinkedTreeMap.Node<Object, Object> node = new LinkedTreeMap.Node<>(true);

    // Act
    LinkedTreeMap.Node<Object, Object> actualLastResult = node.last();

    // Assert
    LinkedTreeMap.Node<Object, Object> node2 = actualLastResult.prev;
    assertSame(node2, node.first());
    LinkedTreeMap.Node<Object, Object> node3 = node.next;
    assertSame(node2, node3.first());
    LinkedTreeMap.Node<Object, Object> node4 = node.prev;
    assertSame(node2, node4.first());
    assertSame(node2, actualLastResult);
    assertSame(node2, node3.next);
    assertSame(node2, node4.next);
    assertSame(node2, node3.prev);
    assertSame(node2, node4.prev);
  }

  /** Method under test: {@link LinkedTreeMap.Node#Node(boolean)} */
  @Test
  public void testNodeNewNode() {
    // Arrange and Act
    LinkedTreeMap.Node<Object, Object> actualNode = new LinkedTreeMap.Node<>(true);

    // Assert
    assertNull(actualNode.left);
    LinkedTreeMap.Node<Object, Object> node = actualNode.next;
    assertNull(node.left);
    LinkedTreeMap.Node<Object, Object> node2 = actualNode.prev;
    assertNull(node2.left);
    assertNull(actualNode.parent);
    assertNull(node.parent);
    assertNull(node2.parent);
    assertNull(actualNode.right);
    assertNull(node.right);
    assertNull(node2.right);
    assertNull(actualNode.getKey());
    assertNull(node.getKey());
    assertNull(node2.getKey());
    assertNull(actualNode.getValue());
    assertNull(node.getValue());
    assertNull(node2.getValue());
    assertEquals(0, actualNode.height);
    assertEquals(0, node.height);
    assertEquals(0, node2.height);
    assertTrue(actualNode.allowNullValue);
    assertTrue(node.allowNullValue);
    assertTrue(node2.allowNullValue);
    LinkedTreeMap.Node<Object, Object> node3 = actualNode.prev;
    assertSame(node3, actualNode.first());
    assertSame(node3, node.first());
    assertSame(node3, node2.first());
    assertSame(node3, node.next);
    assertSame(node3, node2.next);
    assertSame(node3, node.prev);
    assertSame(node3, node2.prev);
  }

  /**
   * Method under test: {@link LinkedTreeMap.Node#Node(boolean, LinkedTreeMap.Node, Object,
   * LinkedTreeMap.Node, LinkedTreeMap.Node)}
   */
  @Test
  public void testNodeNewNode2() {
    // Arrange
    LinkedTreeMap.Node<Object, Object> parent = new LinkedTreeMap.Node<>(true);
    LinkedTreeMap.Node<Object, Object> next = new LinkedTreeMap.Node<>(true);

    // Act
    LinkedTreeMap.Node<Object, Object> actualNode =
        new LinkedTreeMap.Node<>(true, parent, "Key", next, new LinkedTreeMap.Node<>(true));

    // Assert
    assertEquals("Key", actualNode.getKey());
    assertNull(actualNode.left);
    LinkedTreeMap.Node<Object, Object> node = actualNode.parent;
    assertNull(node.left);
    assertNull(node.parent);
    assertNull(actualNode.right);
    assertNull(node.right);
    assertNull(node.getKey());
    assertNull(actualNode.getValue());
    assertNull(node.getValue());
    assertEquals(0, node.height);
    assertEquals(1, actualNode.height);
    assertTrue(actualNode.allowNullValue);
    assertTrue(node.allowNullValue);
    LinkedTreeMap.Node<Object, Object> node2 = actualNode.parent;
    assertEquals(node2, actualNode.next);
    assertEquals(node2, actualNode.prev);
    assertSame(actualNode, actualNode.first());
    assertSame(node2, parent.first());
    LinkedTreeMap.Node<Object, Object> node3 = parent.next;
    assertSame(node2, node3.first());
    assertSame(node2, node.first());
    LinkedTreeMap.Node<Object, Object> node4 = parent.prev;
    assertSame(node2, node4.first());
    assertSame(node2, node3.next);
    assertSame(node2, node.next);
    assertSame(node2, node4.next);
    assertSame(node2, node3.prev);
    assertSame(node2, node.prev);
    assertSame(node2, node4.prev);
  }

  /** Method under test: {@link LinkedTreeMap.Node#setValue(Object)} */
  @Test
  public void testNodeSetValue() {
    // Arrange
    LinkedTreeMap.Node<Object, Object> node = new LinkedTreeMap.Node<>(true);

    // Act
    Object actualSetValueResult = node.setValue("Value");

    // Assert
    assertEquals("Value", node.getValue());
    assertEquals("Value", node.next.getValue());
    assertEquals("Value", node.prev.getValue());
    assertNull(actualSetValueResult);
  }

  /** Method under test: {@link LinkedTreeMap.Node#setValue(Object)} */
  @Test
  public void testNodeSetValue2() {
    // Arrange
    LinkedTreeMap.Node<Object, Object> node = new LinkedTreeMap.Node<>(true);

    // Act
    Object actualSetValueResult = node.setValue(null);

    // Assert
    assertNull(node.getValue());
    assertNull(node.next.getValue());
    assertNull(node.prev.getValue());
    assertNull(actualSetValueResult);
  }

  /** Method under test: {@link LinkedTreeMap#size()} */
  @Test
  public void testSize() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act and Assert
    assertEquals(0, objectObjectMap.size());
  }

  /** Method under test: {@link LinkedTreeMap#get(Object)} */
  @Test
  public void testGet() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act and Assert
    assertNull(objectObjectMap.get("Key"));
  }

  /** Method under test: {@link LinkedTreeMap#get(Object)} */
  @Test
  public void testGet2() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("Key", "Value");

    // Act and Assert
    assertEquals("Value", objectObjectMap.get("Key"));
  }

  /** Method under test: {@link LinkedTreeMap#get(Object)} */
  @Test
  public void testGet3() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.putIfAbsent("42", "42");
    objectObjectMap.put("Key", "Value");

    // Act and Assert
    assertEquals("Value", objectObjectMap.get("Key"));
  }

  /** Method under test: {@link LinkedTreeMap#get(Object)} */
  @Test
  public void testGet4() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.putIfAbsent("42", "42");

    // Act and Assert
    assertNull(objectObjectMap.get("Key"));
  }

  /** Method under test: {@link LinkedTreeMap#get(Object)} */
  @Test
  public void testGet5() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put(true, "Value");

    // Act and Assert
    assertNull(objectObjectMap.get("Key"));
  }

  /** Method under test: {@link LinkedTreeMap#get(Object)} */
  @Test
  public void testGet6() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("com.google.gson.internal.LinkedTreeMap", "Value");

    // Act and Assert
    assertNull(objectObjectMap.get("Key"));
  }

  /** Method under test: {@link LinkedTreeMap#get(Object)} */
  @Test
  public void testGet7() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act and Assert
    assertNull(objectObjectMap.get(null));
  }

  /** Method under test: {@link LinkedTreeMap#containsKey(Object)} */
  @Test
  public void testContainsKey() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act and Assert
    assertFalse(objectObjectMap.containsKey("Key"));
  }

  /** Method under test: {@link LinkedTreeMap#containsKey(Object)} */
  @Test
  public void testContainsKey2() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("Key", "Value");

    // Act and Assert
    assertTrue(objectObjectMap.containsKey("Key"));
  }

  /** Method under test: {@link LinkedTreeMap#containsKey(Object)} */
  @Test
  public void testContainsKey3() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.putIfAbsent("42", "42");
    objectObjectMap.put("Key", "Value");

    // Act and Assert
    assertTrue(objectObjectMap.containsKey("Key"));
  }

  /** Method under test: {@link LinkedTreeMap#containsKey(Object)} */
  @Test
  public void testContainsKey4() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.putIfAbsent("42", "42");

    // Act and Assert
    assertFalse(objectObjectMap.containsKey("Key"));
  }

  /** Method under test: {@link LinkedTreeMap#containsKey(Object)} */
  @Test
  public void testContainsKey5() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put(true, "Value");

    // Act and Assert
    assertFalse(objectObjectMap.containsKey("Key"));
  }

  /** Method under test: {@link LinkedTreeMap#containsKey(Object)} */
  @Test
  public void testContainsKey6() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("com.google.gson.internal.LinkedTreeMap", "Value");

    // Act and Assert
    assertFalse(objectObjectMap.containsKey("Key"));
  }

  /** Method under test: {@link LinkedTreeMap#containsKey(Object)} */
  @Test
  public void testContainsKey7() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act and Assert
    assertFalse(objectObjectMap.containsKey(null));
  }

  /** Method under test: {@link LinkedTreeMap#put(Object, Object)} */
  @Test
  public void testPut() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act
    Object actualPutResult = objectObjectMap.put("Key", "Value");

    // Assert
    assertEquals(1, objectObjectMap.size());
    assertEquals("Value", objectObjectMap.get("Key"));
    assertNull(actualPutResult);
  }

  /** Method under test: {@link LinkedTreeMap#put(Object, Object)} */
  @Test
  public void testPut2() {
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

  /** Method under test: {@link LinkedTreeMap#put(Object, Object)} */
  @Test
  public void testPut3() {
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

  /** Method under test: {@link LinkedTreeMap#put(Object, Object)} */
  @Test
  public void testPut4() {
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

  /** Method under test: {@link LinkedTreeMap#put(Object, Object)} */
  @Test
  public void testPut5() {
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

  /** Method under test: {@link LinkedTreeMap#remove(Object)} */
  @Test
  public void testRemove() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act and Assert
    assertNull(objectObjectMap.remove("Key"));
    assertTrue(objectObjectMap.isEmpty());
  }

  /** Method under test: {@link LinkedTreeMap#remove(Object)} */
  @Test
  public void testRemove2() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("Key", "Value");

    // Act and Assert
    assertEquals("Value", objectObjectMap.remove("Key"));
    assertTrue(objectObjectMap.isEmpty());
  }

  /** Method under test: {@link LinkedTreeMap#remove(Object)} */
  @Test
  public void testRemove3() {
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

  /** Method under test: {@link LinkedTreeMap#remove(Object)} */
  @Test
  public void testRemove4() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.putIfAbsent("42", "42");

    // Act and Assert
    assertNull(objectObjectMap.remove("Key"));
    assertEquals(1, objectObjectMap.size());
    assertTrue(objectObjectMap.containsKey("42"));
  }

  /** Method under test: {@link LinkedTreeMap#remove(Object)} */
  @Test
  public void testRemove5() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put(true, "Value");

    // Act and Assert
    assertNull(objectObjectMap.remove("Key"));
    assertEquals(1, objectObjectMap.size());
    assertTrue(objectObjectMap.containsKey(true));
  }

  /** Method under test: {@link LinkedTreeMap#remove(Object)} */
  @Test
  public void testRemove6() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("com.google.gson.internal.LinkedTreeMap", "Value");

    // Act and Assert
    assertNull(objectObjectMap.remove("Key"));
    assertEquals(1, objectObjectMap.size());
    assertTrue(objectObjectMap.containsKey("com.google.gson.internal.LinkedTreeMap"));
  }

  /** Method under test: {@link LinkedTreeMap#remove(Object)} */
  @Test
  public void testRemove7() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("Key", true);

    // Act
    objectObjectMap.remove("Key");

    // Assert
    assertTrue(objectObjectMap.isEmpty());
  }

  /** Method under test: {@link LinkedTreeMap#remove(Object)} */
  @Test
  public void testRemove8() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("Key", false);

    // Act
    objectObjectMap.remove("Key");

    // Assert
    assertTrue(objectObjectMap.isEmpty());
  }

  /** Method under test: {@link LinkedTreeMap#remove(Object)} */
  @Test
  public void testRemove9() {
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

  /** Method under test: {@link LinkedTreeMap#remove(Object)} */
  @Test
  public void testRemove10() {
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

  /** Method under test: {@link LinkedTreeMap#remove(Object)} */
  @Test
  public void testRemove11() {
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

  /** Method under test: {@link LinkedTreeMap#remove(Object)} */
  @Test
  public void testRemove12() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act and Assert
    assertNull(objectObjectMap.remove(null));
    assertTrue(objectObjectMap.isEmpty());
  }

  /** Method under test: {@link LinkedTreeMap#remove(Object)} */
  @Test
  public void testRemove13() {
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

  /** Method under test: {@link LinkedTreeMap#find(Object, boolean)} */
  @Test
  public void testFind() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act
    LinkedTreeMap.Node<Object, Object> actualFindResult = objectObjectMap.find("Key", true);

    // Assert
    assertEquals(1, objectObjectMap.size());
    assertNull(objectObjectMap.get("Key"));
    assertSame(objectObjectMap.root, actualFindResult);
  }

  /** Method under test: {@link LinkedTreeMap#find(Object, boolean)} */
  @Test
  public void testFind2() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("Key", "Value");

    // Act
    LinkedTreeMap.Node<Object, Object> actualFindResult = objectObjectMap.find("Key", true);

    // Assert
    assertEquals(1, objectObjectMap.size());
    assertEquals("Value", objectObjectMap.get("Key"));
    assertSame(objectObjectMap.root, actualFindResult);
  }

  /** Method under test: {@link LinkedTreeMap#find(Object, boolean)} */
  @Test
  public void testFind3() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.putIfAbsent("42", "42");
    objectObjectMap.put("Key", "Value");

    // Act
    LinkedTreeMap.Node<Object, Object> actualFindResult = objectObjectMap.find("Key", true);

    // Assert
    assertEquals(2, objectObjectMap.size());
    LinkedTreeMap.Node<Object, Object> node = actualFindResult.parent;
    assertEquals("42", node.getKey());
    LinkedTreeMap.Node<Object, Object> node2 = actualFindResult.prev;
    assertEquals("42", node2.getKey());
    assertEquals("42", node.getValue());
    assertEquals("42", node2.getValue());
    assertEquals("Key", actualFindResult.getKey());
    assertEquals("Value", objectObjectMap.get("Key"));
    assertEquals("Value", actualFindResult.getValue());
    assertNull(actualFindResult.left);
    LinkedTreeMap.Node<Object, Object> node3 = actualFindResult.next;
    assertNull(node3.left);
    assertNull(node.left);
    assertNull(node2.left);
    assertNull(node3.parent);
    assertNull(node.parent);
    assertNull(node2.parent);
    assertNull(actualFindResult.right);
    assertNull(node3.right);
    assertNull(node3.getKey());
    assertNull(node3.getValue());
    assertEquals(0, node3.height);
    assertEquals(1, actualFindResult.height);
    assertEquals(2, node.height);
    assertEquals(2, node2.height);
    assertTrue(objectObjectMap.containsKey("42"));
    assertTrue(actualFindResult.allowNullValue);
    assertTrue(node3.allowNullValue);
    assertTrue(node.allowNullValue);
    assertTrue(node2.allowNullValue);
    assertSame(actualFindResult, actualFindResult.first());
    assertSame(actualFindResult, node.next);
    assertSame(actualFindResult, node2.next);
    assertSame(actualFindResult, node3.prev);
    assertSame(actualFindResult, node.right);
    assertSame(actualFindResult, node2.right);
    LinkedTreeMap.Node<Object, Object> node4 = actualFindResult.next;
    assertSame(node4, node3.first());
    assertSame(node4, node.prev);
    assertSame(node4, node2.prev);
    LinkedTreeMap.Node<Object, Object> node5 = actualFindResult.prev;
    assertSame(node5, node.first());
    assertSame(node5, node2.first());
    assertSame(node5, node3.next);
  }

  /** Method under test: {@link LinkedTreeMap#find(Object, boolean)} */
  @Test
  public void testFind4() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.putIfAbsent("42", "42");

    // Act
    LinkedTreeMap.Node<Object, Object> actualFindResult = objectObjectMap.find("Key", true);

    // Assert
    assertEquals(2, objectObjectMap.size());
    LinkedTreeMap.Node<Object, Object> node = actualFindResult.parent;
    assertEquals("42", node.getKey());
    LinkedTreeMap.Node<Object, Object> node2 = actualFindResult.prev;
    assertEquals("42", node2.getKey());
    assertEquals("42", node.getValue());
    assertEquals("42", node2.getValue());
    assertEquals("Key", actualFindResult.getKey());
    assertNull(actualFindResult.left);
    LinkedTreeMap.Node<Object, Object> node3 = actualFindResult.next;
    assertNull(node3.left);
    assertNull(node.left);
    assertNull(node2.left);
    assertNull(node3.parent);
    assertNull(node.parent);
    assertNull(node2.parent);
    assertNull(actualFindResult.right);
    assertNull(node3.right);
    assertNull(objectObjectMap.get("Key"));
    assertNull(node3.getKey());
    assertNull(actualFindResult.getValue());
    assertNull(node3.getValue());
    assertEquals(0, node3.height);
    assertEquals(1, actualFindResult.height);
    assertEquals(2, node.height);
    assertEquals(2, node2.height);
    assertTrue(objectObjectMap.containsKey("42"));
    assertTrue(actualFindResult.allowNullValue);
    assertTrue(node3.allowNullValue);
    assertTrue(node.allowNullValue);
    assertTrue(node2.allowNullValue);
    assertSame(actualFindResult, actualFindResult.first());
    assertSame(actualFindResult, node.next);
    assertSame(actualFindResult, node2.next);
    assertSame(actualFindResult, node3.prev);
    assertSame(actualFindResult, node.right);
    assertSame(actualFindResult, node2.right);
    LinkedTreeMap.Node<Object, Object> node4 = actualFindResult.next;
    assertSame(node4, node3.first());
    assertSame(node4, node.prev);
    assertSame(node4, node2.prev);
    LinkedTreeMap.Node<Object, Object> node5 = actualFindResult.prev;
    assertSame(node5, node.first());
    assertSame(node5, node2.first());
    assertSame(node5, node3.next);
  }

  /** Method under test: {@link LinkedTreeMap#find(Object, boolean)} */
  @Test
  public void testFind5() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("com.google.gson.internal.LinkedTreeMap", "Value");

    // Act
    LinkedTreeMap.Node<Object, Object> actualFindResult = objectObjectMap.find("Key", true);

    // Assert
    assertEquals(2, objectObjectMap.size());
    assertEquals("Key", actualFindResult.getKey());
    LinkedTreeMap.Node<Object, Object> node = actualFindResult.parent;
    assertEquals("Value", node.getValue());
    LinkedTreeMap.Node<Object, Object> node2 = actualFindResult.prev;
    assertEquals("Value", node2.getValue());
    assertEquals("com.google.gson.internal.LinkedTreeMap", node.getKey());
    assertEquals("com.google.gson.internal.LinkedTreeMap", node2.getKey());
    assertNull(actualFindResult.left);
    LinkedTreeMap.Node<Object, Object> node3 = actualFindResult.next;
    assertNull(node3.left);
    assertNull(node3.parent);
    assertNull(node.parent);
    assertNull(node2.parent);
    assertNull(actualFindResult.right);
    assertNull(node3.right);
    assertNull(node.right);
    assertNull(node2.right);
    assertNull(objectObjectMap.get("Key"));
    assertNull(node3.getKey());
    assertNull(actualFindResult.getValue());
    assertNull(node3.getValue());
    assertEquals(0, node3.height);
    assertEquals(1, actualFindResult.height);
    assertEquals(2, node.height);
    assertEquals(2, node2.height);
    assertTrue(objectObjectMap.containsKey("com.google.gson.internal.LinkedTreeMap"));
    assertTrue(actualFindResult.allowNullValue);
    assertTrue(node3.allowNullValue);
    assertTrue(node.allowNullValue);
    assertTrue(node2.allowNullValue);
    assertSame(actualFindResult, actualFindResult.first());
    assertSame(actualFindResult, node.first());
    assertSame(actualFindResult, node2.first());
    assertSame(actualFindResult, node.left);
    assertSame(actualFindResult, node2.left);
    assertSame(actualFindResult, node.next);
    assertSame(actualFindResult, node2.next);
    assertSame(actualFindResult, node3.prev);
    LinkedTreeMap.Node<Object, Object> node4 = actualFindResult.next;
    assertSame(node4, node3.first());
    assertSame(node4, node.prev);
    assertSame(node4, node2.prev);
    assertSame(actualFindResult.prev, node3.next);
  }

  /** Method under test: {@link LinkedTreeMap#findByObject(Object)} */
  @Test
  public void testFindByObject() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act and Assert
    assertNull(objectObjectMap.findByObject("Key"));
  }

  /** Method under test: {@link LinkedTreeMap#findByObject(Object)} */
  @Test
  public void testFindByObject2() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("Key", "Value");

    // Act and Assert
    assertSame(objectObjectMap.root, objectObjectMap.findByObject("Key"));
  }

  /** Method under test: {@link LinkedTreeMap#findByObject(Object)} */
  @Test
  public void testFindByObject3() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.putIfAbsent("42", "42");
    objectObjectMap.put("Key", "Value");

    // Act
    LinkedTreeMap.Node<Object, Object> actualFindByObjectResult =
        objectObjectMap.findByObject("Key");

    // Assert
    LinkedTreeMap.Node<Object, Object> node = actualFindByObjectResult.parent;
    assertEquals("42", node.getKey());
    LinkedTreeMap.Node<Object, Object> node2 = actualFindByObjectResult.prev;
    assertEquals("42", node2.getKey());
    assertEquals("42", node.getValue());
    assertEquals("42", node2.getValue());
    assertEquals("Key", actualFindByObjectResult.getKey());
    assertEquals("Value", actualFindByObjectResult.getValue());
    assertNull(actualFindByObjectResult.left);
    LinkedTreeMap.Node<Object, Object> node3 = actualFindByObjectResult.next;
    assertNull(node3.left);
    assertNull(node.left);
    assertNull(node2.left);
    assertNull(node3.parent);
    assertNull(node.parent);
    assertNull(node2.parent);
    assertNull(actualFindByObjectResult.right);
    assertNull(node3.right);
    assertNull(node3.getKey());
    assertNull(node3.getValue());
    assertEquals(0, node3.height);
    assertEquals(1, actualFindByObjectResult.height);
    assertEquals(2, node.height);
    assertEquals(2, node2.height);
    assertTrue(actualFindByObjectResult.allowNullValue);
    assertTrue(node3.allowNullValue);
    assertTrue(node.allowNullValue);
    assertTrue(node2.allowNullValue);
    assertSame(actualFindByObjectResult, actualFindByObjectResult.first());
    assertSame(actualFindByObjectResult, node.next);
    assertSame(actualFindByObjectResult, node2.next);
    assertSame(actualFindByObjectResult, node3.prev);
    assertSame(actualFindByObjectResult, node.right);
    assertSame(actualFindByObjectResult, node2.right);
    LinkedTreeMap.Node<Object, Object> node4 = actualFindByObjectResult.next;
    assertSame(node4, node3.first());
    assertSame(node4, node.prev);
    assertSame(node4, node2.prev);
    LinkedTreeMap.Node<Object, Object> node5 = actualFindByObjectResult.prev;
    assertSame(node5, node.first());
    assertSame(node5, node2.first());
    assertSame(node5, node3.next);
  }

  /** Method under test: {@link LinkedTreeMap#findByObject(Object)} */
  @Test
  public void testFindByObject4() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.putIfAbsent("42", "42");

    // Act and Assert
    assertNull(objectObjectMap.findByObject("Key"));
  }

  /** Method under test: {@link LinkedTreeMap#findByObject(Object)} */
  @Test
  public void testFindByObject5() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put(true, "Value");

    // Act and Assert
    assertNull(objectObjectMap.findByObject("Key"));
  }

  /** Method under test: {@link LinkedTreeMap#findByObject(Object)} */
  @Test
  public void testFindByObject6() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("com.google.gson.internal.LinkedTreeMap", "Value");

    // Act and Assert
    assertNull(objectObjectMap.findByObject("Key"));
  }

  /** Method under test: {@link LinkedTreeMap#findByObject(Object)} */
  @Test
  public void testFindByObject7() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act and Assert
    assertNull(objectObjectMap.findByObject(null));
  }

  /** Method under test: {@link LinkedTreeMap#findByEntry(Map.Entry)} */
  @Test
  public void testFindByEntry() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act and Assert
    assertNull(objectObjectMap.findByEntry(new AbstractMap.SimpleEntry<>("42", "42")));
  }

  /** Method under test: {@link LinkedTreeMap#findByEntry(Map.Entry)} */
  @Test
  public void testFindByEntry2() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("Key", "Value");

    // Act and Assert
    assertNull(objectObjectMap.findByEntry(new AbstractMap.SimpleEntry<>("42", "42")));
  }

  /** Method under test: {@link LinkedTreeMap#findByEntry(Map.Entry)} */
  @Test
  public void testFindByEntry3() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.putIfAbsent("42", "42");
    objectObjectMap.put("Key", "Value");

    // Act and Assert
    assertSame(
        objectObjectMap.root,
        objectObjectMap.findByEntry(new AbstractMap.SimpleEntry<>("42", "42")));
  }

  /** Method under test: {@link LinkedTreeMap#findByEntry(Map.Entry)} */
  @Test
  public void testFindByEntry4() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put(true, "Value");

    // Act and Assert
    assertNull(objectObjectMap.findByEntry(new AbstractMap.SimpleEntry<>("42", "42")));
  }

  /** Method under test: {@link LinkedTreeMap#findByEntry(Map.Entry)} */
  @Test
  public void testFindByEntry5() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("42", "Value");

    // Act and Assert
    assertNull(objectObjectMap.findByEntry(new AbstractMap.SimpleEntry<>("42", "42")));
  }

  /** Method under test: {@link LinkedTreeMap#findByEntry(Map.Entry)} */
  @Test
  public void testFindByEntry6() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("", "Value");

    // Act and Assert
    assertNull(objectObjectMap.findByEntry(new AbstractMap.SimpleEntry<>("42", "42")));
  }

  /** Method under test: {@link LinkedTreeMap#findByEntry(Map.Entry)} */
  @Test
  public void testFindByEntry7() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("com.google.gson.internal.LinkedTreeMap", "Value");
    objectObjectMap.put("Key", "Value");

    // Act and Assert
    assertNull(objectObjectMap.findByEntry(new AbstractMap.SimpleEntry<>("42", "42")));
  }

  /** Method under test: {@link LinkedTreeMap#findByEntry(Map.Entry)} */
  @Test
  public void testFindByEntry8() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act and Assert
    assertNull(objectObjectMap.findByEntry(new AbstractMap.SimpleEntry<>(null, "42")));
  }

  /** Method under test: {@link LinkedTreeMap#findByEntry(Map.Entry)} */
  @Test
  public void testFindByEntry9() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act and Assert
    assertNull(objectObjectMap.findByEntry(new LinkedTreeMap.Node<>(true)));
  }

  /** Method under test: {@link LinkedTreeMap#removeInternal(LinkedTreeMap.Node, boolean)} */
  @Test
  public void testRemoveInternal() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act
    objectObjectMap.removeInternal(new LinkedTreeMap.Node<>(true), true);

    // Assert
    assertEquals(-1, objectObjectMap.size());
  }

  /** Method under test: {@link LinkedTreeMap#removeInternalByKey(Object)} */
  @Test
  public void testRemoveInternalByKey() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act and Assert
    assertNull(objectObjectMap.removeInternalByKey("Key"));
    assertTrue(objectObjectMap.isEmpty());
  }

  /** Method under test: {@link LinkedTreeMap#removeInternalByKey(Object)} */
  @Test
  public void testRemoveInternalByKey2() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("Key", "Value");

    // Act
    LinkedTreeMap.Node<Object, Object> actualRemoveInternalByKeyResult =
        objectObjectMap.removeInternalByKey("Key");

    // Assert
    assertEquals("Key", actualRemoveInternalByKeyResult.getKey());
    assertEquals("Value", actualRemoveInternalByKeyResult.getValue());
    assertNull(actualRemoveInternalByKeyResult.left);
    LinkedTreeMap.Node<Object, Object> node = actualRemoveInternalByKeyResult.next;
    assertNull(node.left);
    LinkedTreeMap.Node<Object, Object> node2 = actualRemoveInternalByKeyResult.prev;
    assertNull(node2.left);
    assertNull(actualRemoveInternalByKeyResult.parent);
    assertNull(node.parent);
    assertNull(node2.parent);
    assertNull(actualRemoveInternalByKeyResult.right);
    assertNull(node.right);
    assertNull(node2.right);
    assertNull(node.getKey());
    assertNull(node2.getKey());
    assertNull(node.getValue());
    assertNull(node2.getValue());
    assertEquals(0, node.height);
    assertEquals(0, node2.height);
    assertEquals(1, actualRemoveInternalByKeyResult.height);
    assertTrue(objectObjectMap.isEmpty());
    assertTrue(actualRemoveInternalByKeyResult.allowNullValue);
    assertTrue(node.allowNullValue);
    assertTrue(node2.allowNullValue);
    assertSame(actualRemoveInternalByKeyResult, actualRemoveInternalByKeyResult.first());
    LinkedTreeMap.Node<Object, Object> node3 = actualRemoveInternalByKeyResult.prev;
    assertSame(node3, node.first());
    assertSame(node3, node2.first());
    assertSame(node3, node.next);
    assertSame(node3, node2.next);
    assertSame(node3, node.prev);
    assertSame(node3, node2.prev);
  }

  /** Method under test: {@link LinkedTreeMap#removeInternalByKey(Object)} */
  @Test
  public void testRemoveInternalByKey3() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.putIfAbsent("42", "42");
    objectObjectMap.put("Key", "Value");

    // Act
    LinkedTreeMap.Node<Object, Object> actualRemoveInternalByKeyResult =
        objectObjectMap.removeInternalByKey("Key");

    // Assert
    assertEquals(1, objectObjectMap.size());
    LinkedTreeMap.Node<Object, Object> node = actualRemoveInternalByKeyResult.prev;
    assertEquals("42", node.getKey());
    assertEquals("42", node.getValue());
    assertEquals("Key", actualRemoveInternalByKeyResult.getKey());
    assertEquals("Value", actualRemoveInternalByKeyResult.getValue());
    assertNull(actualRemoveInternalByKeyResult.left);
    LinkedTreeMap.Node<Object, Object> node2 = actualRemoveInternalByKeyResult.next;
    assertNull(node2.left);
    assertNull(node.left);
    assertNull(actualRemoveInternalByKeyResult.parent);
    assertNull(node2.parent);
    assertNull(node.parent);
    assertNull(actualRemoveInternalByKeyResult.right);
    assertNull(node2.right);
    assertNull(node.right);
    assertNull(node2.getKey());
    assertNull(node2.getValue());
    assertEquals(0, node2.height);
    assertEquals(1, actualRemoveInternalByKeyResult.height);
    assertEquals(1, node.height);
    assertTrue(objectObjectMap.containsKey("42"));
    assertTrue(actualRemoveInternalByKeyResult.allowNullValue);
    assertTrue(node2.allowNullValue);
    assertTrue(node.allowNullValue);
    assertSame(actualRemoveInternalByKeyResult, actualRemoveInternalByKeyResult.first());
    LinkedTreeMap.Node<Object, Object> node3 = actualRemoveInternalByKeyResult.next;
    assertSame(node3, node2.first());
    assertSame(node3, node.next);
    assertSame(node3, node.prev);
    LinkedTreeMap.Node<Object, Object> node4 = actualRemoveInternalByKeyResult.prev;
    assertSame(node4, node.first());
    assertSame(node4, node2.next);
    assertSame(node4, node2.prev);
  }

  /** Method under test: {@link LinkedTreeMap#removeInternalByKey(Object)} */
  @Test
  public void testRemoveInternalByKey4() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.putIfAbsent("42", "42");

    // Act and Assert
    assertNull(objectObjectMap.removeInternalByKey("Key"));
    assertEquals(1, objectObjectMap.size());
    assertTrue(objectObjectMap.containsKey("42"));
  }

  /** Method under test: {@link LinkedTreeMap#removeInternalByKey(Object)} */
  @Test
  public void testRemoveInternalByKey5() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put(true, "Value");

    // Act and Assert
    assertNull(objectObjectMap.removeInternalByKey("Key"));
    assertEquals(1, objectObjectMap.size());
    assertTrue(objectObjectMap.containsKey(true));
  }

  /** Method under test: {@link LinkedTreeMap#removeInternalByKey(Object)} */
  @Test
  public void testRemoveInternalByKey6() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("com.google.gson.internal.LinkedTreeMap", "Value");

    // Act and Assert
    assertNull(objectObjectMap.removeInternalByKey("Key"));
    assertEquals(1, objectObjectMap.size());
    assertTrue(objectObjectMap.containsKey("com.google.gson.internal.LinkedTreeMap"));
  }

  /** Method under test: {@link LinkedTreeMap#removeInternalByKey(Object)} */
  @Test
  public void testRemoveInternalByKey7() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("com.google.gson.internal.LinkedTreeMap", "Value");
    objectObjectMap.put("Key", "Value");

    // Act
    LinkedTreeMap.Node<Object, Object> actualRemoveInternalByKeyResult =
        objectObjectMap.removeInternalByKey("Key");

    // Assert
    assertEquals(1, objectObjectMap.size());
    assertEquals("Key", actualRemoveInternalByKeyResult.getKey());
    assertEquals("Value", actualRemoveInternalByKeyResult.getValue());
    LinkedTreeMap.Node<Object, Object> node = actualRemoveInternalByKeyResult.prev;
    assertEquals("Value", node.getValue());
    assertEquals("com.google.gson.internal.LinkedTreeMap", node.getKey());
    assertNull(actualRemoveInternalByKeyResult.left);
    LinkedTreeMap.Node<Object, Object> node2 = actualRemoveInternalByKeyResult.next;
    assertNull(node2.left);
    assertNull(node.left);
    assertNull(actualRemoveInternalByKeyResult.parent);
    assertNull(node2.parent);
    assertNull(node.parent);
    assertNull(actualRemoveInternalByKeyResult.right);
    assertNull(node2.right);
    assertNull(node.right);
    assertNull(node2.getKey());
    assertNull(node2.getValue());
    assertEquals(0, node2.height);
    assertEquals(1, actualRemoveInternalByKeyResult.height);
    assertEquals(1, node.height);
    assertTrue(objectObjectMap.containsKey("com.google.gson.internal.LinkedTreeMap"));
    assertTrue(actualRemoveInternalByKeyResult.allowNullValue);
    assertTrue(node2.allowNullValue);
    assertTrue(node.allowNullValue);
    assertSame(actualRemoveInternalByKeyResult, actualRemoveInternalByKeyResult.first());
    LinkedTreeMap.Node<Object, Object> node3 = actualRemoveInternalByKeyResult.next;
    assertSame(node3, node2.first());
    assertSame(node3, node.next);
    assertSame(node3, node.prev);
    LinkedTreeMap.Node<Object, Object> node4 = actualRemoveInternalByKeyResult.prev;
    assertSame(node4, node.first());
    assertSame(node4, node2.next);
    assertSame(node4, node2.prev);
  }

  /** Method under test: {@link LinkedTreeMap#removeInternalByKey(Object)} */
  @Test
  public void testRemoveInternalByKey8() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("Key", "Value");
    objectObjectMap.putIfAbsent("42", "42");
    objectObjectMap.put("Key", "Value");

    // Act
    LinkedTreeMap.Node<Object, Object> actualRemoveInternalByKeyResult =
        objectObjectMap.removeInternalByKey("Key");

    // Assert
    assertEquals(1, objectObjectMap.size());
    LinkedTreeMap.Node<Object, Object> node = actualRemoveInternalByKeyResult.next;
    assertEquals("42", node.getKey());
    assertEquals("42", node.getValue());
    assertEquals("Key", actualRemoveInternalByKeyResult.getKey());
    assertEquals("Value", actualRemoveInternalByKeyResult.getValue());
    assertNull(actualRemoveInternalByKeyResult.left);
    assertNull(node.left);
    LinkedTreeMap.Node<Object, Object> node2 = actualRemoveInternalByKeyResult.prev;
    assertNull(node2.left);
    assertNull(actualRemoveInternalByKeyResult.parent);
    assertNull(node.parent);
    assertNull(node2.parent);
    assertNull(actualRemoveInternalByKeyResult.right);
    assertNull(node.right);
    assertNull(node2.right);
    assertNull(node2.getKey());
    assertNull(node2.getValue());
    assertEquals(0, node2.height);
    assertEquals(1, node.height);
    assertEquals(2, actualRemoveInternalByKeyResult.height);
    assertTrue(objectObjectMap.containsKey("42"));
    assertTrue(actualRemoveInternalByKeyResult.allowNullValue);
    assertTrue(node.allowNullValue);
    assertTrue(node2.allowNullValue);
    assertSame(actualRemoveInternalByKeyResult, actualRemoveInternalByKeyResult.first());
    LinkedTreeMap.Node<Object, Object> node3 = actualRemoveInternalByKeyResult.next;
    assertSame(node3, node.first());
    assertSame(node3, node2.next);
    assertSame(node3, node2.prev);
    LinkedTreeMap.Node<Object, Object> node4 = actualRemoveInternalByKeyResult.prev;
    assertSame(node4, node2.first());
    assertSame(node4, node.next);
    assertSame(node4, node.prev);
  }

  /** Method under test: {@link LinkedTreeMap#removeInternalByKey(Object)} */
  @Test
  public void testRemoveInternalByKey9() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put("com.google.gson.internal.LinkedTreeMap", "Value");
    objectObjectMap.putIfAbsent("42", "42");
    objectObjectMap.put("Key", "Value");

    // Act
    LinkedTreeMap.Node<Object, Object> actualRemoveInternalByKeyResult =
        objectObjectMap.removeInternalByKey("Key");

    // Assert
    assertEquals(2, objectObjectMap.size());
    LinkedTreeMap.Node<Object, Object> node = actualRemoveInternalByKeyResult.prev;
    assertEquals("42", node.getKey());
    assertEquals("42", node.getValue());
    assertEquals("Key", actualRemoveInternalByKeyResult.getKey());
    assertEquals("Value", actualRemoveInternalByKeyResult.getValue());
    assertNull(actualRemoveInternalByKeyResult.left);
    LinkedTreeMap.Node<Object, Object> node2 = actualRemoveInternalByKeyResult.next;
    assertNull(node2.left);
    assertNull(node.left);
    assertNull(actualRemoveInternalByKeyResult.parent);
    assertNull(node2.parent);
    assertNull(actualRemoveInternalByKeyResult.right);
    assertNull(node2.right);
    assertNull(node.right);
    assertNull(node2.getKey());
    assertNull(node2.getValue());
    assertEquals(0, node2.height);
    assertEquals(1, node.height);
    assertEquals(2, actualRemoveInternalByKeyResult.height);
    assertTrue(objectObjectMap.containsKey("42"));
    assertTrue(objectObjectMap.containsKey("com.google.gson.internal.LinkedTreeMap"));
    assertTrue(actualRemoveInternalByKeyResult.allowNullValue);
    assertTrue(node2.allowNullValue);
    assertTrue(node.allowNullValue);
    assertSame(actualRemoveInternalByKeyResult, actualRemoveInternalByKeyResult.first());
    LinkedTreeMap.Node<Object, Object> node3 = objectObjectMap.root;
    assertSame(node3, node2.next);
    assertSame(node3, node.parent);
    assertSame(node3, node.prev);
    LinkedTreeMap.Node<Object, Object> node4 = actualRemoveInternalByKeyResult.next;
    assertSame(node4, node2.first());
    assertSame(node4, node.next);
    LinkedTreeMap.Node<Object, Object> node5 = actualRemoveInternalByKeyResult.prev;
    assertSame(node5, node.first());
    assertSame(node5, node2.prev);
  }

  /** Method under test: {@link LinkedTreeMap#removeInternalByKey(Object)} */
  @Test
  public void testRemoveInternalByKey10() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act and Assert
    assertNull(objectObjectMap.removeInternalByKey(null));
    assertTrue(objectObjectMap.isEmpty());
  }

  /** Method under test: {@link LinkedTreeMap#removeInternalByKey(Object)} */
  @Test
  public void testRemoveInternalByKey11() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.putIfAbsent("42", "42");
    objectObjectMap.put("Key", "Value");

    // Act
    LinkedTreeMap.Node<Object, Object> actualRemoveInternalByKeyResult =
        objectObjectMap.removeInternalByKey("42");

    // Assert
    assertEquals(1, objectObjectMap.size());
    assertEquals("42", actualRemoveInternalByKeyResult.getKey());
    assertEquals("42", actualRemoveInternalByKeyResult.getValue());
    LinkedTreeMap.Node<Object, Object> node = actualRemoveInternalByKeyResult.next;
    assertEquals("Key", node.getKey());
    assertEquals("Value", node.getValue());
    assertNull(actualRemoveInternalByKeyResult.left);
    assertNull(node.left);
    LinkedTreeMap.Node<Object, Object> node2 = actualRemoveInternalByKeyResult.prev;
    assertNull(node2.left);
    assertNull(actualRemoveInternalByKeyResult.parent);
    assertNull(node.parent);
    assertNull(node2.parent);
    assertNull(actualRemoveInternalByKeyResult.right);
    assertNull(node.right);
    assertNull(node2.right);
    assertNull(node2.getKey());
    assertNull(node2.getValue());
    assertEquals(0, node2.height);
    assertEquals(1, node.height);
    assertEquals(2, actualRemoveInternalByKeyResult.height);
    assertTrue(objectObjectMap.containsKey("Key"));
    assertTrue(actualRemoveInternalByKeyResult.allowNullValue);
    assertTrue(node.allowNullValue);
    assertTrue(node2.allowNullValue);
    assertSame(actualRemoveInternalByKeyResult, actualRemoveInternalByKeyResult.first());
    LinkedTreeMap.Node<Object, Object> node3 = actualRemoveInternalByKeyResult.next;
    assertSame(node3, node.first());
    assertSame(node3, node2.next);
    assertSame(node3, node2.prev);
    LinkedTreeMap.Node<Object, Object> node4 = actualRemoveInternalByKeyResult.prev;
    assertSame(node4, node2.first());
    assertSame(node4, node.next);
    assertSame(node4, node.prev);
  }

  /** Method under test: {@link LinkedTreeMap#removeInternalByKey(Object)} */
  @Test
  public void testRemoveInternalByKey12() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.putIfAbsent("foo", "42");
    objectObjectMap.put("com.google.gson.internal.LinkedTreeMap", "Value");
    objectObjectMap.put("Key", "Value");

    // Act
    LinkedTreeMap.Node<Object, Object> actualRemoveInternalByKeyResult =
        objectObjectMap.removeInternalByKey("Key");

    // Assert
    assertEquals(2, objectObjectMap.size());
    LinkedTreeMap.Node<Object, Object> node = actualRemoveInternalByKeyResult.next;
    LinkedTreeMap.Node<Object, Object> node2 = node.next;
    assertEquals("42", node2.getValue());
    assertEquals("Key", actualRemoveInternalByKeyResult.getKey());
    assertEquals("Value", actualRemoveInternalByKeyResult.getValue());
    LinkedTreeMap.Node<Object, Object> node3 = actualRemoveInternalByKeyResult.prev;
    assertEquals("Value", node3.getValue());
    assertEquals("com.google.gson.internal.LinkedTreeMap", node3.getKey());
    assertEquals("foo", node2.getKey());
    assertNull(actualRemoveInternalByKeyResult.left);
    assertNull(node.left);
    assertNull(node2.left);
    assertNull(node3.left);
    assertNull(actualRemoveInternalByKeyResult.parent);
    assertNull(node.parent);
    assertNull(node3.parent);
    assertNull(actualRemoveInternalByKeyResult.right);
    assertNull(node.right);
    assertNull(node2.right);
    assertNull(node.getKey());
    assertNull(node.getValue());
    assertEquals(0, node.height);
    assertEquals(1, actualRemoveInternalByKeyResult.height);
    assertEquals(1, node2.height);
    assertEquals(2, node3.height);
    assertTrue(objectObjectMap.containsKey("com.google.gson.internal.LinkedTreeMap"));
    assertTrue(objectObjectMap.containsKey("foo"));
    assertTrue(actualRemoveInternalByKeyResult.allowNullValue);
    assertTrue(node.allowNullValue);
    assertTrue(node2.allowNullValue);
    assertTrue(node3.allowNullValue);
    assertSame(actualRemoveInternalByKeyResult, actualRemoveInternalByKeyResult.first());
    LinkedTreeMap.Node<Object, Object> node4 = actualRemoveInternalByKeyResult.next;
    assertSame(node4, node.first());
    assertSame(node4, node3.next);
    assertSame(node4, node2.prev);
    LinkedTreeMap.Node<Object, Object> node5 = actualRemoveInternalByKeyResult.prev;
    assertSame(node5, node3.first());
    assertSame(node5, node2.next);
    assertSame(node5, node2.parent);
    assertSame(node5, node.prev);
  }

  /** Method under test: {@link LinkedTreeMap#entrySet()} */
  @Test
  public void testEntrySet() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act and Assert
    assertTrue(objectObjectMap.entrySet().isEmpty());
  }

  /** Method under test: {@link LinkedTreeMap#entrySet()} */
  @Test
  public void testEntrySet2() {
    // Arrange
    HashMap<Object, Object> objectObjectMap = new HashMap<>();
    objectObjectMap.computeIfPresent("42", mock(BiFunction.class));

    LinkedTreeMap<Object, Object> objectObjectMap2 = new LinkedTreeMap<>();
    objectObjectMap2.putAll(objectObjectMap);

    // Act and Assert
    assertTrue(objectObjectMap2.entrySet().isEmpty());
  }

  /** Method under test: {@link LinkedTreeMap#keySet()} */
  @Test
  public void testKeySet() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act and Assert
    assertTrue(objectObjectMap.keySet().isEmpty());
  }

  /** Method under test: {@link LinkedTreeMap#keySet()} */
  @Test
  public void testKeySet2() {
    // Arrange
    HashMap<Object, Object> objectObjectMap = new HashMap<>();
    objectObjectMap.computeIfPresent("42", mock(BiFunction.class));

    LinkedTreeMap<Object, Object> objectObjectMap2 = new LinkedTreeMap<>();
    objectObjectMap2.putAll(objectObjectMap);

    // Act and Assert
    assertTrue(objectObjectMap2.keySet().isEmpty());
  }
}
