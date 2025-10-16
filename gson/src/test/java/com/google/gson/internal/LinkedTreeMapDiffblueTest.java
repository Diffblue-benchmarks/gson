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
import com.google.gson.internal.reflect.ReflectionHelperTestFactory;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
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
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} one is createPublicField.
   *   <li>When forName {@code UTF-8}.
   * </ul>
   *
   * <p>Method under test: {@link KeySet#contains(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean KeySet.contains(Object)"})
  public void testKeySetContains_givenLinkedTreeMapOneIsCreatePublicField_whenForNameUtf8()
      throws NoSuchFieldException {
    // Arrange
    LinkedTreeMap linkedTreeMap = new LinkedTreeMap();
    linkedTreeMap.put(1, ReflectionHelperTestFactory.createPublicField());
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
  public void testNodeEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual2()
      throws NoSuchFieldException {
    // Arrange
    Node<Object, Object> node = new Node<>(true);
    node.setValue(ReflectionHelperTestFactory.createPublicField());

    Node<Object, Object> node2 = new Node<>(true);
    node2.setValue(ReflectionHelperTestFactory.createPublicField());

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
  public void testNodeEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual3()
      throws NoSuchFieldException {
    // Arrange
    Node<Object, Object> parent = new Node<>(true);
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();
    Node<Object, Object> next = new Node<>(true);

    Node<Object, Object> node =
        new Node<>(true, parent, createPublicFieldResult, next, new Node<>(true));
    node.setValue(ReflectionHelperTestFactory.createPublicField());
    Field createPublicFieldResult2 = ReflectionHelperTestFactory.createPublicField();
    SimpleEntry<Object, Object> simpleEntry =
        new SimpleEntry<>(
            createPublicFieldResult2, ReflectionHelperTestFactory.createPublicField());

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
  public void testNodeEquals_whenOtherIsDifferent_thenReturnNotEqual() throws NoSuchFieldException {
    // Arrange
    Node<Object, Object> parent = new Node<>(true);
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();
    Node<Object, Object> next = new Node<>(true);

    Node<Object, Object> node =
        new Node<>(true, parent, createPublicFieldResult, next, new Node<>(true));

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
  public void testNodeEquals_whenOtherIsDifferent_thenReturnNotEqual2()
      throws NoSuchFieldException {
    // Arrange
    Node<Object, Object> node = new Node<>(true);
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();

    // Act and Assert
    assertNotEquals(
        node,
        new SimpleEntry<>(
            createPublicFieldResult, ReflectionHelperTestFactory.createPublicField()));
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
  public void testNodeEquals_whenOtherIsDifferent_thenReturnNotEqual3()
      throws NoSuchFieldException {
    // Arrange
    Node<Object, Object> node = new Node<>(true);
    node.setValue(ReflectionHelperTestFactory.createPublicField());

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
  public void testNodeEquals_whenOtherIsDifferent_thenReturnNotEqual5()
      throws NoSuchFieldException {
    // Arrange
    Node<Object, Object> parent = new Node<>(true);
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();
    Node<Object, Object> next = new Node<>(true);

    Node<Object, Object> node =
        new Node<>(true, parent, createPublicFieldResult, next, new Node<>(true));
    Field createPublicFieldResult2 = ReflectionHelperTestFactory.createPublicField();

    // Act and Assert
    assertNotEquals(
        node,
        new SimpleEntry<>(
            createPublicFieldResult2, ReflectionHelperTestFactory.createPublicField()));
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
   *   <li>Then Key return {@link Field}.
   * </ul>
   *
   * <p>Method under test: {@link Node#Node(boolean, Node, Object, Node, Node)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Node.<init>(boolean, Node, Object, Node, Node)"})
  public void testNodeNewNode_whenNodeWithAllowNullValueIsTrue_thenKeyReturnField()
      throws NoSuchFieldException {
    // Arrange
    Node<Object, Object> parent = new Node<>(true);
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();
    Node<Object, Object> next = new Node<>(true);

    // Act
    Node<Object, Object> actualNode =
        new Node<>(true, parent, createPublicFieldResult, next, new Node<>(true));

    // Assert
    Object key = actualNode.getKey();
    assertTrue(key instanceof Field);
    assertNull(actualNode.left);
    assertNull(actualNode.right);
    assertNull(actualNode.getValue());
    assertEquals(1, actualNode.height);
    assertTrue(actualNode.allowNullValue);
    Node<Object, Object> actualFirstResult = actualNode.first();
    assertSame(actualNode, actualFirstResult);
    assertSame(createPublicFieldResult, key);
    Node<Object, Object> expectedFirstResult = actualNode.parent;
    assertSame(expectedFirstResult, parent.first());
  }

  /**
   * Test Node {@link Node#setValue(Object)}.
   *
   * <ul>
   *   <li>Then {@link Node#Node(boolean)} with allowNullValue is {@code true} Value is
   *       createPublicField.
   * </ul>
   *
   * <p>Method under test: {@link Node#setValue(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Node.setValue(Object)"})
  public void testNodeSetValue_thenNodeWithAllowNullValueIsTrueValueIsCreatePublicField()
      throws NoSuchFieldException {
    // Arrange
    Node<Object, Object> node = new Node<>(true);
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();

    // Act and Assert
    assertNull(node.setValue(createPublicFieldResult));
    assertSame(createPublicFieldResult, node.getValue());
    assertSame(createPublicFieldResult, node.next.getValue());
    assertSame(createPublicFieldResult, node.prev.getValue());
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
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code false} is createPublicField.
   *   <li>When {@code true}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#get(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.get(Object)"})
  public void testGet_givenLinkedTreeMapFalseIsCreatePublicField_whenTrue_thenReturnNull()
      throws NoSuchFieldException {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put(false, ReflectionHelperTestFactory.createPublicField());

    // Act and Assert
    assertNull(objectObjectMap.get(true));
  }

  /**
   * Test {@link LinkedTreeMap#get(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} one is createPublicField.
   *   <li>When createPublicField.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#get(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.get(Object)"})
  public void testGet_givenLinkedTreeMapOneIsCreatePublicField_whenCreatePublicField()
      throws NoSuchFieldException {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put(1, ReflectionHelperTestFactory.createPublicField());

    // Act and Assert
    assertNull(objectObjectMap.get(ReflectionHelperTestFactory.createPublicField()));
  }

  /**
   * Test {@link LinkedTreeMap#get(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} one is createPublicField.
   *   <li>When {@code true}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#get(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.get(Object)"})
  public void testGet_givenLinkedTreeMapOneIsCreatePublicField_whenTrue_thenReturnNull()
      throws NoSuchFieldException {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put(1, ReflectionHelperTestFactory.createPublicField());

    // Act and Assert
    assertNull(objectObjectMap.get(true));
  }

  /**
   * Test {@link LinkedTreeMap#get(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} one is createPublicField.
   *   <li>When zero.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#get(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.get(Object)"})
  public void testGet_givenLinkedTreeMapOneIsCreatePublicField_whenZero_thenReturnNull()
      throws NoSuchFieldException {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put(1, ReflectionHelperTestFactory.createPublicField());

    // Act and Assert
    assertNull(objectObjectMap.get(0));
  }

  /**
   * Test {@link LinkedTreeMap#get(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} one is {@code false}.
   *   <li>When one.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#get(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.get(Object)"})
  public void testGet_givenLinkedTreeMapOneIsFalse_whenOne_thenReturnFalse() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put(1, false);

    // Act and Assert
    assertFalse((Boolean) objectObjectMap.get(1));
  }

  /**
   * Test {@link LinkedTreeMap#get(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} one is {@code true}.
   *   <li>When one.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#get(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.get(Object)"})
  public void testGet_givenLinkedTreeMapOneIsTrue_whenOne_thenReturnTrue() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put(1, true);

    // Act and Assert
    assertTrue((Boolean) objectObjectMap.get(1));
  }

  /**
   * Test {@link LinkedTreeMap#get(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()}.
   *   <li>When createPublicField.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#get(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.get(Object)"})
  public void testGet_givenLinkedTreeMap_whenCreatePublicField_thenReturnNull()
      throws NoSuchFieldException {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act and Assert
    assertNull(objectObjectMap.get(ReflectionHelperTestFactory.createPublicField()));
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
   *   <li>When one.
   *   <li>Then return createPublicField.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#get(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.get(Object)"})
  public void testGet_whenOne_thenReturnCreatePublicField() throws NoSuchFieldException {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();
    objectObjectMap.put(1, createPublicFieldResult);

    // Act and Assert
    assertSame(createPublicFieldResult, objectObjectMap.get(1));
  }

  /**
   * Test {@link LinkedTreeMap#containsKey(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code false} is createPublicField.
   *   <li>When {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#containsKey(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean LinkedTreeMap.containsKey(Object)"})
  public void testContainsKey_givenLinkedTreeMapFalseIsCreatePublicField_whenTrue()
      throws NoSuchFieldException {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put(false, ReflectionHelperTestFactory.createPublicField());

    // Act and Assert
    assertFalse(objectObjectMap.containsKey(true));
  }

  /**
   * Test {@link LinkedTreeMap#containsKey(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} one is createPublicField.
   *   <li>When createPublicField.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#containsKey(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean LinkedTreeMap.containsKey(Object)"})
  public void testContainsKey_givenLinkedTreeMapOneIsCreatePublicField_whenCreatePublicField()
      throws NoSuchFieldException {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put(1, ReflectionHelperTestFactory.createPublicField());

    // Act and Assert
    assertFalse(objectObjectMap.containsKey(ReflectionHelperTestFactory.createPublicField()));
  }

  /**
   * Test {@link LinkedTreeMap#containsKey(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} one is createPublicField.
   *   <li>When one.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#containsKey(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean LinkedTreeMap.containsKey(Object)"})
  public void testContainsKey_givenLinkedTreeMapOneIsCreatePublicField_whenOne_thenReturnTrue()
      throws NoSuchFieldException {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put(1, ReflectionHelperTestFactory.createPublicField());

    // Act and Assert
    assertTrue(objectObjectMap.containsKey(1));
  }

  /**
   * Test {@link LinkedTreeMap#containsKey(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} one is createPublicField.
   *   <li>When {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#containsKey(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean LinkedTreeMap.containsKey(Object)"})
  public void testContainsKey_givenLinkedTreeMapOneIsCreatePublicField_whenTrue()
      throws NoSuchFieldException {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put(1, ReflectionHelperTestFactory.createPublicField());

    // Act and Assert
    assertFalse(objectObjectMap.containsKey(true));
  }

  /**
   * Test {@link LinkedTreeMap#containsKey(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} one is createPublicField.
   *   <li>When zero.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#containsKey(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean LinkedTreeMap.containsKey(Object)"})
  public void testContainsKey_givenLinkedTreeMapOneIsCreatePublicField_whenZero()
      throws NoSuchFieldException {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put(1, ReflectionHelperTestFactory.createPublicField());

    // Act and Assert
    assertFalse(objectObjectMap.containsKey(0));
  }

  /**
   * Test {@link LinkedTreeMap#containsKey(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()}.
   *   <li>When createPublicField.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#containsKey(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean LinkedTreeMap.containsKey(Object)"})
  public void testContainsKey_givenLinkedTreeMap_whenCreatePublicField_thenReturnFalse()
      throws NoSuchFieldException {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act and Assert
    assertFalse(objectObjectMap.containsKey(ReflectionHelperTestFactory.createPublicField()));
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
   *   <li>When forName {@code UTF-8}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#put(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.put(Object, Object)"})
  public void testPut_whenForNameUtf8_thenReturnNull() throws NoSuchFieldException {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    Charset forNameResult = Charset.forName("UTF-8");

    // Act and Assert
    assertNull(objectObjectMap.put(forNameResult, ReflectionHelperTestFactory.createPublicField()));
    assertEquals(1, objectObjectMap.size());
  }

  /**
   * Test {@link LinkedTreeMap#put(Object, Object)}.
   *
   * <ul>
   *   <li>When forName {@code UTF-8}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#put(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.put(Object, Object)"})
  public void testPut_whenForNameUtf8_thenReturnNull2() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act and Assert
    assertNull(objectObjectMap.put(Charset.forName("UTF-8"), null));
    assertEquals(1, objectObjectMap.size());
  }

  /**
   * Test {@link LinkedTreeMap#remove(Object)} with {@code Object}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} one is {@code false}.
   *   <li>When one.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#remove(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.remove(Object)"})
  public void testRemoveWithObject_givenLinkedTreeMapOneIsFalse_whenOne_thenReturnFalse() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put(1, false);

    // Act and Assert
    assertFalse((Boolean) objectObjectMap.remove(1));
    assertTrue(objectObjectMap.isEmpty());
  }

  /**
   * Test {@link LinkedTreeMap#remove(Object)} with {@code Object}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} one is {@code true}.
   *   <li>When one.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#remove(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.remove(Object)"})
  public void testRemoveWithObject_givenLinkedTreeMapOneIsTrue_whenOne_thenReturnTrue() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put(1, true);

    // Act
    Object actualRemoveResult = objectObjectMap.remove(1);

    // Assert
    assertTrue(objectObjectMap.isEmpty());
    assertTrue((Boolean) actualRemoveResult);
  }

  /**
   * Test {@link LinkedTreeMap#remove(Object)} with {@code Object}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()}.
   *   <li>When createPublicField.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#remove(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.remove(Object)"})
  public void testRemoveWithObject_givenLinkedTreeMap_whenCreatePublicField()
      throws NoSuchFieldException {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act and Assert
    assertNull(objectObjectMap.remove(ReflectionHelperTestFactory.createPublicField()));
    assertTrue(objectObjectMap.isEmpty());
  }

  /**
   * Test {@link LinkedTreeMap#remove(Object)} with {@code Object}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()}.
   *   <li>When {@code null}.
   *   <li>Then {@link LinkedTreeMap#LinkedTreeMap()} Empty.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#remove(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.remove(Object)"})
  public void testRemoveWithObject_givenLinkedTreeMap_whenNull_thenLinkedTreeMapEmpty() {
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
   *   <li>Then {@link LinkedTreeMap#LinkedTreeMap()} containsKey {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#remove(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.remove(Object)"})
  public void testRemoveWithObject_thenLinkedTreeMapContainsKeyFalse() throws NoSuchFieldException {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put(false, ReflectionHelperTestFactory.createPublicField());

    // Act and Assert
    assertNull(objectObjectMap.remove(true));
    assertEquals(1, objectObjectMap.size());
    assertTrue(objectObjectMap.containsKey(false));
  }

  /**
   * Test {@link LinkedTreeMap#remove(Object)} with {@code Object}.
   *
   * <ul>
   *   <li>When createPublicField.
   *   <li>Then {@link LinkedTreeMap#LinkedTreeMap()} containsKey one.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#remove(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.remove(Object)"})
  public void testRemoveWithObject_whenCreatePublicField_thenLinkedTreeMapContainsKeyOne()
      throws NoSuchFieldException {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put(1, ReflectionHelperTestFactory.createPublicField());

    // Act and Assert
    assertNull(objectObjectMap.remove(ReflectionHelperTestFactory.createPublicField()));
    assertEquals(1, objectObjectMap.size());
    assertTrue(objectObjectMap.containsKey(1));
  }

  /**
   * Test {@link LinkedTreeMap#remove(Object)} with {@code Object}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return createPublicField.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#remove(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.remove(Object)"})
  public void testRemoveWithObject_whenOne_thenReturnCreatePublicField()
      throws NoSuchFieldException {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();
    objectObjectMap.put(1, createPublicFieldResult);

    // Act and Assert
    assertSame(createPublicFieldResult, objectObjectMap.remove(1));
  }

  /**
   * Test {@link LinkedTreeMap#remove(Object)} with {@code Object}.
   *
   * <ul>
   *   <li>When {@code true}.
   *   <li>Then {@link LinkedTreeMap#LinkedTreeMap()} containsKey one.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#remove(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.remove(Object)"})
  public void testRemoveWithObject_whenTrue_thenLinkedTreeMapContainsKeyOne()
      throws NoSuchFieldException {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put(1, ReflectionHelperTestFactory.createPublicField());

    // Act and Assert
    assertNull(objectObjectMap.remove(true));
    assertEquals(1, objectObjectMap.size());
    assertTrue(objectObjectMap.containsKey(1));
  }

  /**
   * Test {@link LinkedTreeMap#remove(Object)} with {@code Object}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then {@link LinkedTreeMap#LinkedTreeMap()} containsKey one.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#remove(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LinkedTreeMap.remove(Object)"})
  public void testRemoveWithObject_whenZero_thenLinkedTreeMapContainsKeyOne()
      throws NoSuchFieldException {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put(1, ReflectionHelperTestFactory.createPublicField());

    // Act and Assert
    assertNull(objectObjectMap.remove(0));
    assertEquals(1, objectObjectMap.size());
    assertTrue(objectObjectMap.containsKey(1));
  }

  /**
   * Test {@link LinkedTreeMap#find(Object, boolean)}.
   *
   * <p>Method under test: {@link LinkedTreeMap#find(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.find(Object, boolean)"})
  public void testFind() {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act and Assert
    assertNull(objectObjectMap.find(Charset.forName("UTF-8"), false));
  }

  /**
   * Test {@link LinkedTreeMap#findByObject(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code false} is createPublicField.
   *   <li>When {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#findByObject(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.findByObject(Object)"})
  public void testFindByObject_givenLinkedTreeMapFalseIsCreatePublicField_whenTrue()
      throws NoSuchFieldException {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put(false, ReflectionHelperTestFactory.createPublicField());

    // Act and Assert
    assertNull(objectObjectMap.findByObject(true));
  }

  /**
   * Test {@link LinkedTreeMap#findByObject(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} one is createPublicField.
   *   <li>When createPublicField.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#findByObject(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.findByObject(Object)"})
  public void testFindByObject_givenLinkedTreeMapOneIsCreatePublicField_whenCreatePublicField()
      throws NoSuchFieldException {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put(1, ReflectionHelperTestFactory.createPublicField());

    // Act and Assert
    assertNull(objectObjectMap.findByObject(ReflectionHelperTestFactory.createPublicField()));
  }

  /**
   * Test {@link LinkedTreeMap#findByObject(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} one is createPublicField.
   *   <li>When {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#findByObject(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.findByObject(Object)"})
  public void testFindByObject_givenLinkedTreeMapOneIsCreatePublicField_whenTrue()
      throws NoSuchFieldException {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put(1, ReflectionHelperTestFactory.createPublicField());

    // Act and Assert
    assertNull(objectObjectMap.findByObject(true));
  }

  /**
   * Test {@link LinkedTreeMap#findByObject(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} one is createPublicField.
   *   <li>When zero.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#findByObject(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.findByObject(Object)"})
  public void testFindByObject_givenLinkedTreeMapOneIsCreatePublicField_whenZero()
      throws NoSuchFieldException {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put(1, ReflectionHelperTestFactory.createPublicField());

    // Act and Assert
    assertNull(objectObjectMap.findByObject(0));
  }

  /**
   * Test {@link LinkedTreeMap#findByObject(Object)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()}.
   *   <li>When createPublicField.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#findByObject(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.findByObject(Object)"})
  public void testFindByObject_givenLinkedTreeMap_whenCreatePublicField_thenReturnNull()
      throws NoSuchFieldException {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act and Assert
    assertNull(objectObjectMap.findByObject(ReflectionHelperTestFactory.createPublicField()));
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
   *   <li>When one.
   *   <li>Then return {@link LinkedTreeMap#LinkedTreeMap()} {@link LinkedTreeMap#root}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#findByObject(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.findByObject(Object)"})
  public void testFindByObject_whenOne_thenReturnLinkedTreeMapRoot() throws NoSuchFieldException {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put(1, ReflectionHelperTestFactory.createPublicField());

    // Act
    Node<Object, Object> actualFindByObjectResult = objectObjectMap.findByObject(1);

    // Assert
    assertSame(objectObjectMap.root, actualFindByObjectResult);
  }

  /**
   * Test {@link LinkedTreeMap#findByEntry(Entry)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} {@code false} is createPublicField.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#findByEntry(Entry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.findByEntry(Entry)"})
  public void testFindByEntry_givenLinkedTreeMapFalseIsCreatePublicField()
      throws NoSuchFieldException {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put(false, ReflectionHelperTestFactory.createPublicField());

    // Act
    Node<Object, Object> actualFindByEntryResult =
        objectObjectMap.findByEntry(
            new SimpleEntry<>(true, ReflectionHelperTestFactory.createPublicField()));

    // Assert
    assertNull(actualFindByEntryResult);
  }

  /**
   * Test {@link LinkedTreeMap#findByEntry(Entry)}.
   *
   * <ul>
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()} one is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#findByEntry(Entry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.findByEntry(Entry)"})
  public void testFindByEntry_givenLinkedTreeMapOneIs42() throws NoSuchFieldException {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put(1, "42");

    // Act
    Node<Object, Object> actualFindByEntryResult =
        objectObjectMap.findByEntry(
            new SimpleEntry<>(1, ReflectionHelperTestFactory.createPublicField()));

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
   *   <li>When {@link SimpleEntry#SimpleEntry(Object, Object)} with {@code null} and
   *       createPublicField.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#findByEntry(Entry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.findByEntry(Entry)"})
  public void testFindByEntry_givenLinkedTreeMap_whenSimpleEntryWithNullAndCreatePublicField()
      throws NoSuchFieldException {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act
    Node<Object, Object> actualFindByEntryResult =
        objectObjectMap.findByEntry(
            new SimpleEntry<>(null, ReflectionHelperTestFactory.createPublicField()));

    // Assert
    assertNull(actualFindByEntryResult);
  }

  /**
   * Test {@link LinkedTreeMap#findByEntry(Entry)}.
   *
   * <ul>
   *   <li>Then return {@link LinkedTreeMap#LinkedTreeMap()} {@link LinkedTreeMap#root}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#findByEntry(Entry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.findByEntry(Entry)"})
  public void testFindByEntry_thenReturnLinkedTreeMapRoot() throws NoSuchFieldException {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put(1, ReflectionHelperTestFactory.createPublicField());

    // Act
    Node<Object, Object> actualFindByEntryResult =
        objectObjectMap.findByEntry(
            new SimpleEntry<>(1, ReflectionHelperTestFactory.createPublicField()));

    // Assert
    assertSame(objectObjectMap.root, actualFindByEntryResult);
  }

  /**
   * Test {@link LinkedTreeMap#findByEntry(Entry)}.
   *
   * <ul>
   *   <li>When {@link SimpleEntry#SimpleEntry(Object, Object)} with createPublicField and
   *       createPublicField.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#findByEntry(Entry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.findByEntry(Entry)"})
  public void testFindByEntry_whenSimpleEntryWithCreatePublicFieldAndCreatePublicField()
      throws NoSuchFieldException {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();

    // Act
    Node<Object, Object> actualFindByEntryResult =
        objectObjectMap.findByEntry(
            new SimpleEntry<>(
                createPublicFieldResult, ReflectionHelperTestFactory.createPublicField()));

    // Assert
    assertNull(actualFindByEntryResult);
  }

  /**
   * Test {@link LinkedTreeMap#findByEntry(Entry)}.
   *
   * <ul>
   *   <li>When {@link SimpleEntry#SimpleEntry(Object, Object)} with createPublicField and
   *       createPublicField.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#findByEntry(Entry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.findByEntry(Entry)"})
  public void testFindByEntry_whenSimpleEntryWithCreatePublicFieldAndCreatePublicField2()
      throws NoSuchFieldException {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put(1, ReflectionHelperTestFactory.createPublicField());
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();

    // Act
    Node<Object, Object> actualFindByEntryResult =
        objectObjectMap.findByEntry(
            new SimpleEntry<>(
                createPublicFieldResult, ReflectionHelperTestFactory.createPublicField()));

    // Assert
    assertNull(actualFindByEntryResult);
  }

  /**
   * Test {@link LinkedTreeMap#findByEntry(Entry)}.
   *
   * <ul>
   *   <li>When {@link SimpleEntry#SimpleEntry(Object, Object)} with {@code true} and
   *       createPublicField.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#findByEntry(Entry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.findByEntry(Entry)"})
  public void testFindByEntry_whenSimpleEntryWithTrueAndCreatePublicField()
      throws NoSuchFieldException {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put(1, ReflectionHelperTestFactory.createPublicField());

    // Act
    Node<Object, Object> actualFindByEntryResult =
        objectObjectMap.findByEntry(
            new SimpleEntry<>(true, ReflectionHelperTestFactory.createPublicField()));

    // Assert
    assertNull(actualFindByEntryResult);
  }

  /**
   * Test {@link LinkedTreeMap#findByEntry(Entry)}.
   *
   * <ul>
   *   <li>When {@link SimpleEntry#SimpleEntry(Object, Object)} with zero and createPublicField.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#findByEntry(Entry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.findByEntry(Entry)"})
  public void testFindByEntry_whenSimpleEntryWithZeroAndCreatePublicField()
      throws NoSuchFieldException {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put(1, ReflectionHelperTestFactory.createPublicField());

    // Act
    Node<Object, Object> actualFindByEntryResult =
        objectObjectMap.findByEntry(
            new SimpleEntry<>(0, ReflectionHelperTestFactory.createPublicField()));

    // Assert
    assertNull(actualFindByEntryResult);
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
   *   <li>Given {@link LinkedTreeMap#LinkedTreeMap()}.
   *   <li>When createPublicField.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#removeInternalByKey(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.removeInternalByKey(Object)"})
  public void testRemoveInternalByKey_givenLinkedTreeMap_whenCreatePublicField()
      throws NoSuchFieldException {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();

    // Act and Assert
    assertNull(
        objectObjectMap.removeInternalByKey(ReflectionHelperTestFactory.createPublicField()));
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
   *   <li>Then {@link LinkedTreeMap#LinkedTreeMap()} containsKey {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#removeInternalByKey(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.removeInternalByKey(Object)"})
  public void testRemoveInternalByKey_thenLinkedTreeMapContainsKeyFalse()
      throws NoSuchFieldException {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put(false, ReflectionHelperTestFactory.createPublicField());

    // Act and Assert
    assertNull(objectObjectMap.removeInternalByKey(true));
    assertEquals(1, objectObjectMap.size());
    assertTrue(objectObjectMap.containsKey(false));
  }

  /**
   * Test {@link LinkedTreeMap#removeInternalByKey(Object)}.
   *
   * <ul>
   *   <li>When createPublicField.
   *   <li>Then {@link LinkedTreeMap#LinkedTreeMap()} containsKey one.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#removeInternalByKey(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.removeInternalByKey(Object)"})
  public void testRemoveInternalByKey_whenCreatePublicField_thenLinkedTreeMapContainsKeyOne()
      throws NoSuchFieldException {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put(1, ReflectionHelperTestFactory.createPublicField());

    // Act and Assert
    assertNull(
        objectObjectMap.removeInternalByKey(ReflectionHelperTestFactory.createPublicField()));
    assertEquals(1, objectObjectMap.size());
    assertTrue(objectObjectMap.containsKey(1));
  }

  /**
   * Test {@link LinkedTreeMap#removeInternalByKey(Object)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then Value return {@link Field}.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#removeInternalByKey(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.removeInternalByKey(Object)"})
  public void testRemoveInternalByKey_whenOne_thenValueReturnField() throws NoSuchFieldException {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();
    objectObjectMap.put(1, createPublicFieldResult);

    // Act
    Node<Object, Object> actualRemoveInternalByKeyResult = objectObjectMap.removeInternalByKey(1);

    // Assert
    Object value = actualRemoveInternalByKeyResult.getValue();
    assertTrue(value instanceof Field);
    assertNull(actualRemoveInternalByKeyResult.left);
    assertNull(actualRemoveInternalByKeyResult.parent);
    assertNull(actualRemoveInternalByKeyResult.right);
    assertEquals(1, ((Integer) actualRemoveInternalByKeyResult.getKey()).intValue());
    assertEquals(1, actualRemoveInternalByKeyResult.height);
    assertTrue(objectObjectMap.isEmpty());
    assertTrue(actualRemoveInternalByKeyResult.allowNullValue);
    Node<Object, Object> actualFirstResult = actualRemoveInternalByKeyResult.first();
    assertSame(actualRemoveInternalByKeyResult, actualFirstResult);
    assertSame(createPublicFieldResult, value);
  }

  /**
   * Test {@link LinkedTreeMap#removeInternalByKey(Object)}.
   *
   * <ul>
   *   <li>When {@code true}.
   *   <li>Then {@link LinkedTreeMap#LinkedTreeMap()} containsKey one.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#removeInternalByKey(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.removeInternalByKey(Object)"})
  public void testRemoveInternalByKey_whenTrue_thenLinkedTreeMapContainsKeyOne()
      throws NoSuchFieldException {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put(1, ReflectionHelperTestFactory.createPublicField());

    // Act and Assert
    assertNull(objectObjectMap.removeInternalByKey(true));
    assertEquals(1, objectObjectMap.size());
    assertTrue(objectObjectMap.containsKey(1));
  }

  /**
   * Test {@link LinkedTreeMap#removeInternalByKey(Object)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then {@link LinkedTreeMap#LinkedTreeMap()} containsKey one.
   * </ul>
   *
   * <p>Method under test: {@link LinkedTreeMap#removeInternalByKey(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node LinkedTreeMap.removeInternalByKey(Object)"})
  public void testRemoveInternalByKey_whenZero_thenLinkedTreeMapContainsKeyOne()
      throws NoSuchFieldException {
    // Arrange
    LinkedTreeMap<Object, Object> objectObjectMap = new LinkedTreeMap<>();
    objectObjectMap.put(1, ReflectionHelperTestFactory.createPublicField());

    // Act and Assert
    assertNull(objectObjectMap.removeInternalByKey(0));
    assertEquals(1, objectObjectMap.size());
    assertTrue(objectObjectMap.containsKey(1));
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
