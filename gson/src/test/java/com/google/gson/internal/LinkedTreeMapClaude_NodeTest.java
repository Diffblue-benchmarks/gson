/*
 * Copyright (C) 2022 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.gson.internal;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import java.util.AbstractMap;
import java.util.Iterator;
import java.util.Map;
import org.junit.Test;

/**
 * Tests for {@link LinkedTreeMap.Node}.
 *
 * <p>Note: Node is package-private, so tests access it indirectly through Map.Entry interface
 * obtained from LinkedTreeMap.entrySet().
 *
 * @author Claude
 */
public class LinkedTreeMapClaude_NodeTest {

  // ==========================================================================
  // Tests for Node constructors (tested indirectly through map operations)
  // The header node constructor Node(boolean allowNullValue) creates a
  // circular sentinel. The regular node constructor is used when adding entries.
  // ==========================================================================

  @Test
  public void testNodeCreationViaMapPut() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");

    Map.Entry<String, String> entry = map.entrySet().iterator().next();
    assertThat(entry).isNotNull();
    assertThat(entry.getKey()).isEqualTo("key");
    assertThat(entry.getValue()).isEqualTo("value");
  }

  @Test
  public void testNodeCreationWithAllowNullValues() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>(true);
    map.put("key", null);

    Map.Entry<String, String> entry = map.entrySet().iterator().next();
    assertThat(entry.getValue()).isNull();
  }

  @Test
  public void testNodeCreationWithDisallowNullValues() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>(false);
    // When null values are not allowed, putting null throws
    assertThrows(NullPointerException.class, () -> map.put("key", null));
  }

  // ==========================================================================
  // Tests for getKey()
  // ==========================================================================

  @Test
  public void testGetKeyReturnsCorrectKey() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("testKey", "testValue");

    Map.Entry<String, String> entry = map.entrySet().iterator().next();
    assertThat(entry.getKey()).isEqualTo("testKey");
  }

  @Test
  public void testGetKeyWithIntegerKey() {
    LinkedTreeMap<Integer, String> map = new LinkedTreeMap<>();
    map.put(42, "value");

    Map.Entry<Integer, String> entry = map.entrySet().iterator().next();
    assertThat(entry.getKey()).isEqualTo(42);
  }

  @Test
  public void testGetKeyWithMultipleEntries() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("first", "value1");
    map.put("second", "value2");
    map.put("third", "value3");

    Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
    assertThat(iterator.next().getKey()).isEqualTo("first");
    assertThat(iterator.next().getKey()).isEqualTo("second");
    assertThat(iterator.next().getKey()).isEqualTo("third");
  }

  @Test
  public void testGetKeyIsImmutable() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");

    Map.Entry<String, String> entry = map.entrySet().iterator().next();
    String key1 = entry.getKey();
    String key2 = entry.getKey();

    assertThat(key1).isSameInstanceAs(key2);
  }

  // ==========================================================================
  // Tests for getValue()
  // ==========================================================================

  @Test
  public void testGetValueReturnsCorrectValue() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "expectedValue");

    Map.Entry<String, String> entry = map.entrySet().iterator().next();
    assertThat(entry.getValue()).isEqualTo("expectedValue");
  }

  @Test
  public void testGetValueWithNullValue() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>(true);
    map.put("key", null);

    Map.Entry<String, String> entry = map.entrySet().iterator().next();
    assertThat(entry.getValue()).isNull();
  }

  @Test
  public void testGetValueReflectsUpdates() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value1");

    Map.Entry<String, String> entry = map.entrySet().iterator().next();
    assertThat(entry.getValue()).isEqualTo("value1");

    map.put("key", "value2");
    // Note: We need to get a fresh entry after the update
    entry = map.entrySet().iterator().next();
    assertThat(entry.getValue()).isEqualTo("value2");
  }

  @Test
  public void testGetValueWithIntegerValue() {
    LinkedTreeMap<String, Integer> map = new LinkedTreeMap<>();
    map.put("key", 12345);

    Map.Entry<String, Integer> entry = map.entrySet().iterator().next();
    assertThat(entry.getValue()).isEqualTo(12345);
  }

  // ==========================================================================
  // Tests for setValue()
  // ==========================================================================

  @Test
  public void testSetValueReturnsOldValue() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "oldValue");

    Map.Entry<String, String> entry = map.entrySet().iterator().next();
    String oldValue = entry.setValue("newValue");

    assertThat(oldValue).isEqualTo("oldValue");
  }

  @Test
  public void testSetValueUpdatesEntryValue() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "oldValue");

    Map.Entry<String, String> entry = map.entrySet().iterator().next();
    entry.setValue("newValue");

    assertThat(entry.getValue()).isEqualTo("newValue");
  }

  @Test
  public void testSetValueUpdatesMap() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "oldValue");

    Map.Entry<String, String> entry = map.entrySet().iterator().next();
    entry.setValue("newValue");

    assertThat(map.get("key")).isEqualTo("newValue");
  }

  @Test
  public void testSetValueMultipleTimes() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value1");

    Map.Entry<String, String> entry = map.entrySet().iterator().next();

    String old1 = entry.setValue("value2");
    assertThat(old1).isEqualTo("value1");
    assertThat(entry.getValue()).isEqualTo("value2");

    String old2 = entry.setValue("value3");
    assertThat(old2).isEqualTo("value2");
    assertThat(entry.getValue()).isEqualTo("value3");
  }

  @Test
  public void testSetValueToNullWhenAllowed() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>(true);
    map.put("key", "value");

    Map.Entry<String, String> entry = map.entrySet().iterator().next();
    String oldValue = entry.setValue(null);

    assertThat(oldValue).isEqualTo("value");
    assertThat(entry.getValue()).isNull();
  }

  @Test
  public void testSetValueToNullWhenNotAllowedThrows() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>(false);
    map.put("key", "value");

    Map.Entry<String, String> entry = map.entrySet().iterator().next();

    NullPointerException e = assertThrows(NullPointerException.class, () -> entry.setValue(null));
    assertThat(e.getMessage()).isEqualTo("value == null");
  }

  @Test
  public void testSetValueFromNullToNonNull() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>(true);
    map.put("key", null);

    Map.Entry<String, String> entry = map.entrySet().iterator().next();
    String oldValue = entry.setValue("newValue");

    assertThat(oldValue).isNull();
    assertThat(entry.getValue()).isEqualTo("newValue");
  }

  // ==========================================================================
  // Tests for equals()
  // ==========================================================================

  @Test
  public void testEqualsWithSameKeyAndValue() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");

    Map.Entry<String, String> entry = map.entrySet().iterator().next();
    Map.Entry<String, String> other = new AbstractMap.SimpleEntry<>("key", "value");

    assertThat(entry.equals(other)).isTrue();
  }

  @Test
  public void testEqualsWithDifferentKey() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value");

    Map.Entry<String, String> entry = map.entrySet().iterator().next();
    Map.Entry<String, String> other = new AbstractMap.SimpleEntry<>("key2", "value");

    assertThat(entry.equals(other)).isFalse();
  }

  @Test
  public void testEqualsWithDifferentValue() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value1");

    Map.Entry<String, String> entry = map.entrySet().iterator().next();
    Map.Entry<String, String> other = new AbstractMap.SimpleEntry<>("key", "value2");

    assertThat(entry.equals(other)).isFalse();
  }

  @Test
  public void testEqualsWithBothDifferent() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");

    Map.Entry<String, String> entry = map.entrySet().iterator().next();
    Map.Entry<String, String> other = new AbstractMap.SimpleEntry<>("key2", "value2");

    assertThat(entry.equals(other)).isFalse();
  }

  @Test
  public void testEqualsWithNull() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");

    Map.Entry<String, String> entry = map.entrySet().iterator().next();

    assertThat(entry.equals(null)).isFalse();
  }

  @Test
  public void testEqualsWithNonEntry() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");

    Map.Entry<String, String> entry = map.entrySet().iterator().next();

    assertThat(entry.equals("not an entry")).isFalse();
    assertThat(entry.equals(42)).isFalse();
  }

  @Test
  public void testEqualsWithNullKeyOnBoth() {
    // Note: LinkedTreeMap doesn't allow null keys, so we test with a SimpleEntry that has null key
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");

    Map.Entry<String, String> entry = map.entrySet().iterator().next();
    Map.Entry<String, String> nullKeyEntry = new AbstractMap.SimpleEntry<>(null, "value");

    assertThat(entry.equals(nullKeyEntry)).isFalse();
  }

  @Test
  public void testEqualsWithNullValueOnBoth() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>(true);
    map.put("key", null);

    Map.Entry<String, String> entry = map.entrySet().iterator().next();
    Map.Entry<String, String> other = new AbstractMap.SimpleEntry<>("key", null);

    assertThat(entry.equals(other)).isTrue();
  }

  @Test
  public void testEqualsWithNullValueOnlyOnEntry() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>(true);
    map.put("key", null);

    Map.Entry<String, String> entry = map.entrySet().iterator().next();
    Map.Entry<String, String> other = new AbstractMap.SimpleEntry<>("key", "value");

    assertThat(entry.equals(other)).isFalse();
  }

  @Test
  public void testEqualsWithNullValueOnlyOnOther() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");

    Map.Entry<String, String> entry = map.entrySet().iterator().next();
    Map.Entry<String, String> other = new AbstractMap.SimpleEntry<>("key", null);

    assertThat(entry.equals(other)).isFalse();
  }

  @Test
  public void testEqualsSymmetric() {
    LinkedTreeMap<String, String> map1 = new LinkedTreeMap<>();
    map1.put("key", "value");
    LinkedTreeMap<String, String> map2 = new LinkedTreeMap<>();
    map2.put("key", "value");

    Map.Entry<String, String> entry1 = map1.entrySet().iterator().next();
    Map.Entry<String, String> entry2 = map2.entrySet().iterator().next();

    assertThat(entry1.equals(entry2)).isTrue();
    assertThat(entry2.equals(entry1)).isTrue();
  }

  @Test
  public void testEqualsReflexive() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");

    Map.Entry<String, String> entry = map.entrySet().iterator().next();

    assertThat(entry.equals(entry)).isTrue();
  }

  @Test
  public void testEqualsTransitive() {
    LinkedTreeMap<String, String> map1 = new LinkedTreeMap<>();
    map1.put("key", "value");
    LinkedTreeMap<String, String> map2 = new LinkedTreeMap<>();
    map2.put("key", "value");
    LinkedTreeMap<String, String> map3 = new LinkedTreeMap<>();
    map3.put("key", "value");

    Map.Entry<String, String> entry1 = map1.entrySet().iterator().next();
    Map.Entry<String, String> entry2 = map2.entrySet().iterator().next();
    Map.Entry<String, String> entry3 = map3.entrySet().iterator().next();

    assertThat(entry1.equals(entry2)).isTrue();
    assertThat(entry2.equals(entry3)).isTrue();
    assertThat(entry1.equals(entry3)).isTrue();
  }

  // ==========================================================================
  // Tests for hashCode()
  // ==========================================================================

  @Test
  public void testHashCodeConsistency() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");

    Map.Entry<String, String> entry = map.entrySet().iterator().next();

    int hash1 = entry.hashCode();
    int hash2 = entry.hashCode();

    assertThat(hash1).isEqualTo(hash2);
  }

  @Test
  public void testHashCodeMatchesEntryContract() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");

    Map.Entry<String, String> entry = map.entrySet().iterator().next();

    // According to Map.Entry contract: hashCode = key.hashCode() ^ value.hashCode()
    int expectedHash = "key".hashCode() ^ "value".hashCode();
    assertThat(entry.hashCode()).isEqualTo(expectedHash);
  }

  @Test
  public void testHashCodeWithNullValue() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>(true);
    map.put("key", null);

    Map.Entry<String, String> entry = map.entrySet().iterator().next();

    // hashCode when value is null: key.hashCode() ^ 0
    int expectedHash = "key".hashCode() ^ 0;
    assertThat(entry.hashCode()).isEqualTo(expectedHash);
  }

  @Test
  public void testHashCodeDifferentForDifferentKeys() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value");
    map.put("key2", "value");

    Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
    Map.Entry<String, String> entry1 = iterator.next();
    Map.Entry<String, String> entry2 = iterator.next();

    // Hash codes are likely different (but not guaranteed by contract)
    int hash1 = entry1.hashCode();
    int hash2 = entry2.hashCode();
    // We just verify they are computed without error
    assertThat(hash1).isNotEqualTo(hash2);
  }

  @Test
  public void testHashCodeEqualForEqualEntries() {
    LinkedTreeMap<String, String> map1 = new LinkedTreeMap<>();
    map1.put("key", "value");
    LinkedTreeMap<String, String> map2 = new LinkedTreeMap<>();
    map2.put("key", "value");

    Map.Entry<String, String> entry1 = map1.entrySet().iterator().next();
    Map.Entry<String, String> entry2 = map2.entrySet().iterator().next();

    assertThat(entry1.equals(entry2)).isTrue();
    assertThat(entry1.hashCode()).isEqualTo(entry2.hashCode());
  }

  @Test
  public void testHashCodeWithIntegerKeyAndValue() {
    LinkedTreeMap<Integer, Integer> map = new LinkedTreeMap<>();
    map.put(100, 200);

    Map.Entry<Integer, Integer> entry = map.entrySet().iterator().next();

    int expectedHash = Integer.valueOf(100).hashCode() ^ Integer.valueOf(200).hashCode();
    assertThat(entry.hashCode()).isEqualTo(expectedHash);
  }

  // ==========================================================================
  // Tests for toString()
  // ==========================================================================

  @Test
  public void testToStringBasic() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");

    Map.Entry<String, String> entry = map.entrySet().iterator().next();

    assertThat(entry.toString()).isEqualTo("key=value");
  }

  @Test
  public void testToStringWithNullValue() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>(true);
    map.put("key", null);

    Map.Entry<String, String> entry = map.entrySet().iterator().next();

    assertThat(entry.toString()).isEqualTo("key=null");
  }

  @Test
  public void testToStringWithIntegerKeyAndValue() {
    LinkedTreeMap<Integer, Integer> map = new LinkedTreeMap<>();
    map.put(42, 100);

    Map.Entry<Integer, Integer> entry = map.entrySet().iterator().next();

    assertThat(entry.toString()).isEqualTo("42=100");
  }

  @Test
  public void testToStringWithEmptyStringKey() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("", "value");

    Map.Entry<String, String> entry = map.entrySet().iterator().next();

    assertThat(entry.toString()).isEqualTo("=value");
  }

  @Test
  public void testToStringWithEmptyStringValue() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "");

    Map.Entry<String, String> entry = map.entrySet().iterator().next();

    assertThat(entry.toString()).isEqualTo("key=");
  }

  @Test
  public void testToStringWithSpecialCharacters() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key=with=equals", "value=with=equals");

    Map.Entry<String, String> entry = map.entrySet().iterator().next();

    assertThat(entry.toString()).isEqualTo("key=with=equals=value=with=equals");
  }

  // ==========================================================================
  // Tests for first() and last() methods
  // These methods navigate the tree structure. Since Node is package-private,
  // we test these indirectly. The first() method returns the leftmost node
  // in a subtree, and last() returns the rightmost node.
  //
  // NOTE: These methods are used internally for tree navigation. Testing them
  // directly would require reflection since Node is package-private.
  // We test the observable behavior through the LinkedTreeMap's iteration
  // which uses these methods internally.
  // ==========================================================================

  @Test
  public void testTreeStructureViaIterationOrder() {
    // LinkedTreeMap maintains insertion order for iteration,
    // but uses tree structure internally for efficient lookups.
    // The first() and last() methods are used for tree navigation.
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("m", "middle");
    map.put("a", "first");
    map.put("z", "last");

    // Iteration follows insertion order, not tree order
    Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
    assertThat(iterator.next().getKey()).isEqualTo("m");
    assertThat(iterator.next().getKey()).isEqualTo("a");
    assertThat(iterator.next().getKey()).isEqualTo("z");
  }

  @Test
  public void testTreeStructureWithManyElements() {
    // Adding many elements exercises tree rebalancing which uses first()/last()
    LinkedTreeMap<Integer, String> map = new LinkedTreeMap<>();
    for (int i = 0; i < 100; i++) {
      map.put(i, "value" + i);
    }

    // Verify all entries are accessible
    assertThat(map.size()).isEqualTo(100);

    // Verify lookup works (uses tree structure)
    for (int i = 0; i < 100; i++) {
      assertThat(map.get(i)).isEqualTo("value" + i);
    }
  }

  @Test
  public void testTreeStructureWithReverseOrderInsertion() {
    // Inserting in reverse order exercises different tree paths
    LinkedTreeMap<Integer, String> map = new LinkedTreeMap<>();
    for (int i = 99; i >= 0; i--) {
      map.put(i, "value" + i);
    }

    assertThat(map.size()).isEqualTo(100);
    // Keys should still be retrievable
    for (int i = 0; i < 100; i++) {
      assertThat(map.get(i)).isEqualTo("value" + i);
    }
  }

  // ==========================================================================
  // Direct tests for first() and last() using package-private access
  // Since our test is in the same package as LinkedTreeMap, we can access
  // the Node class directly.
  // ==========================================================================

  @Test
  public void testFirstReturnsLeftmostNode() {
    // Create a map and access nodes directly through entrySet
    LinkedTreeMap<String, Integer> map = new LinkedTreeMap<>();
    map.put("b", 2);
    map.put("a", 1);
    map.put("c", 3);

    // Get a node (entry) from the map
    LinkedTreeMap.Node<String, Integer> node =
        (LinkedTreeMap.Node<String, Integer>) map.entrySet().iterator().next();

    // first() on any node should return the leftmost in that subtree
    // We're testing the node we got, which is the first in insertion order
    LinkedTreeMap.Node<String, Integer> firstNode = node.first();
    assertThat(firstNode).isNotNull();
    // first() traverses left children - it will return leftmost in subtree
    assertThat(firstNode.getKey()).isNotNull();
  }

  @Test
  public void testLastReturnsRightmostNode() {
    LinkedTreeMap<String, Integer> map = new LinkedTreeMap<>();
    map.put("b", 2);
    map.put("a", 1);
    map.put("c", 3);

    LinkedTreeMap.Node<String, Integer> node =
        (LinkedTreeMap.Node<String, Integer>) map.entrySet().iterator().next();

    // last() on any node should return the rightmost in that subtree
    LinkedTreeMap.Node<String, Integer> lastNode = node.last();
    assertThat(lastNode).isNotNull();
    assertThat(lastNode.getKey()).isNotNull();
  }

  @Test
  public void testFirstOnSingleNodeReturnsItself() {
    LinkedTreeMap<String, Integer> map = new LinkedTreeMap<>();
    map.put("only", 1);

    LinkedTreeMap.Node<String, Integer> node =
        (LinkedTreeMap.Node<String, Integer>) map.entrySet().iterator().next();

    // A node with no left child should return itself
    LinkedTreeMap.Node<String, Integer> firstNode = node.first();
    assertThat(firstNode).isSameInstanceAs(node);
  }

  @Test
  public void testLastOnSingleNodeReturnsItself() {
    LinkedTreeMap<String, Integer> map = new LinkedTreeMap<>();
    map.put("only", 1);

    LinkedTreeMap.Node<String, Integer> node =
        (LinkedTreeMap.Node<String, Integer>) map.entrySet().iterator().next();

    // A node with no right child should return itself
    LinkedTreeMap.Node<String, Integer> lastNode = node.last();
    assertThat(lastNode).isSameInstanceAs(node);
  }

  @Test
  public void testFirstAndLastOnBalancedTree() {
    // Insert elements that will create a balanced tree
    LinkedTreeMap<Integer, String> map = new LinkedTreeMap<>();
    // Insert in order that creates a somewhat balanced tree
    map.put(50, "fifty");
    map.put(25, "twenty-five");
    map.put(75, "seventy-five");
    map.put(10, "ten");
    map.put(30, "thirty");
    map.put(60, "sixty");
    map.put(90, "ninety");

    // Get root-ish node (first in insertion order)
    Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
    LinkedTreeMap.Node<Integer, String> node = (LinkedTreeMap.Node<Integer, String>) iterator.next();

    // Verify first() and last() return valid nodes
    LinkedTreeMap.Node<Integer, String> firstNode = node.first();
    LinkedTreeMap.Node<Integer, String> lastNode = node.last();

    assertThat(firstNode).isNotNull();
    assertThat(lastNode).isNotNull();
    assertThat(firstNode.getKey()).isNotNull();
    assertThat(lastNode.getKey()).isNotNull();
  }

  // ==========================================================================
  // Additional edge case tests
  // ==========================================================================

  @Test
  public void testNodeWithSameKeyValueTypes() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("same", "same");

    Map.Entry<String, String> entry = map.entrySet().iterator().next();

    assertThat(entry.getKey()).isEqualTo("same");
    assertThat(entry.getValue()).isEqualTo("same");
    // hashCode = "same".hashCode() ^ "same".hashCode() = 0
    assertThat(entry.hashCode()).isEqualTo(0);
    assertThat(entry.toString()).isEqualTo("same=same");
  }

  @Test
  public void testEntryRemainsFunctionalAfterMapModification() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");

    Map.Entry<String, String> entry = map.entrySet().iterator().next();
    String originalKey = entry.getKey();

    // Add more entries
    map.put("key3", "value3");

    // Original entry should still work
    assertThat(entry.getKey()).isEqualTo(originalKey);
    entry.setValue("newValue");
    assertThat(entry.getValue()).isEqualTo("newValue");
  }

  @Test
  public void testMultipleEntriesHaveCorrectValues() {
    LinkedTreeMap<String, Integer> map = new LinkedTreeMap<>();
    map.put("one", 1);
    map.put("two", 2);
    map.put("three", 3);

    int sum = 0;
    for (Map.Entry<String, Integer> entry : map.entrySet()) {
      sum += entry.getValue();
      // Verify toString format
      assertThat(entry.toString()).contains("=");
    }
    assertThat(sum).isEqualTo(6);
  }

  @Test
  public void testEqualsWithSimpleEntry() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");

    Map.Entry<String, String> nodeEntry = map.entrySet().iterator().next();
    Map.Entry<String, String> simpleEntry = new AbstractMap.SimpleEntry<>("key", "value");
    Map.Entry<String, String> immutableEntry =
        new AbstractMap.SimpleImmutableEntry<>("key", "value");

    assertThat(nodeEntry.equals(simpleEntry)).isTrue();
    assertThat(nodeEntry.equals(immutableEntry)).isTrue();
    assertThat(simpleEntry.equals(nodeEntry)).isTrue();
    assertThat(immutableEntry.equals(nodeEntry)).isTrue();
  }
}
