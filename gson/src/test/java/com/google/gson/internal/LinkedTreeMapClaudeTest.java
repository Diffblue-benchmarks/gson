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

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import org.junit.Test;

/**
 * Tests for {@link LinkedTreeMap.KeySet}.
 *
 * @author Claude
 */
public class LinkedTreeMapClaudeTest {

  // ==========================================================================
  // Tests for KeySet initialization (implicitly via keySet() call)
  // ==========================================================================

  @Test
  public void testKeySetInitOnEmptyMap() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    Set<String> keySet = map.keySet();

    assertThat(keySet).isNotNull();
    assertThat(keySet).isEmpty();
  }

  @Test
  public void testKeySetInitOnPopulatedMap() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");

    Set<String> keySet = map.keySet();

    assertThat(keySet).isNotNull();
    assertThat(keySet).containsExactly("key1", "key2");
  }

  @Test
  public void testKeySetReturnsSameInstance() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    Set<String> keySet1 = map.keySet();
    Set<String> keySet2 = map.keySet();

    assertThat(keySet1).isSameInstanceAs(keySet2);
  }

  // ==========================================================================
  // Tests for KeySet.size()
  // ==========================================================================

  @Test
  public void testKeySetSizeOnEmptyMap() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    Set<String> keySet = map.keySet();

    assertThat(keySet.size()).isEqualTo(0);
  }

  @Test
  public void testKeySetSizeWithOneEntry() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");
    Set<String> keySet = map.keySet();

    assertThat(keySet.size()).isEqualTo(1);
  }

  @Test
  public void testKeySetSizeWithMultipleEntries() {
    LinkedTreeMap<String, Integer> map = new LinkedTreeMap<>();
    map.put("one", 1);
    map.put("two", 2);
    map.put("three", 3);
    Set<String> keySet = map.keySet();

    assertThat(keySet.size()).isEqualTo(3);
  }

  @Test
  public void testKeySetSizeReflectsMapChanges() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    Set<String> keySet = map.keySet();

    assertThat(keySet.size()).isEqualTo(0);

    map.put("key1", "value1");
    assertThat(keySet.size()).isEqualTo(1);

    map.put("key2", "value2");
    assertThat(keySet.size()).isEqualTo(2);

    var unused = map.remove("key1");
    assertThat(keySet.size()).isEqualTo(1);

    map.clear();
    assertThat(keySet.size()).isEqualTo(0);
  }

  @Test
  public void testKeySetSizeConsistentWithMapSize() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("a", "1");
    map.put("b", "2");
    map.put("c", "3");

    Set<String> keySet = map.keySet();

    assertThat(keySet.size()).isEqualTo(map.size());
  }

  @Test
  public void testKeySetSizeAfterReplacingValue() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value1");
    Set<String> keySet = map.keySet();

    assertThat(keySet.size()).isEqualTo(1);

    // Replacing a value should not change key set size
    map.put("key", "value2");
    assertThat(keySet.size()).isEqualTo(1);
  }

  @Test
  public void testKeySetSizeLargeDataset() {
    LinkedTreeMap<Integer, String> map = new LinkedTreeMap<>();
    Set<Integer> keySet = map.keySet();

    int count = 100;
    for (int i = 0; i < count; i++) {
      map.put(i, "value" + i);
    }

    assertThat(keySet.size()).isEqualTo(count);
    assertThat(keySet.size()).isEqualTo(map.size());
  }

  // ==========================================================================
  // Tests for KeySet.iterator()
  // ==========================================================================

  @Test
  public void testKeySetIteratorOnEmptyMap() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    Iterator<String> iterator = map.keySet().iterator();

    assertThat(iterator.hasNext()).isFalse();
  }

  @Test
  public void testKeySetIteratorReturnsKeys() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");

    List<String> keys = new ArrayList<>();
    for (String key : map.keySet()) {
      keys.add(key);
    }

    assertThat(keys).containsExactly("key1", "key2");
  }

  @Test
  public void testKeySetIteratorPreservesInsertionOrder() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("c", "3");
    map.put("a", "1");
    map.put("b", "2");

    List<String> keys = new ArrayList<>();
    Iterator<String> iterator = map.keySet().iterator();
    while (iterator.hasNext()) {
      keys.add(iterator.next());
    }

    // Keys should be in insertion order, not alphabetical
    assertThat(keys).containsExactly("c", "a", "b").inOrder();
  }

  @Test
  public void testKeySetIteratorThrowsNoSuchElementAfterEnd() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");

    Iterator<String> iterator = map.keySet().iterator();
    iterator.next(); // consume the only element

    assertThrows(NoSuchElementException.class, iterator::next);
  }

  @Test
  public void testKeySetIteratorRemove() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    map.put("key3", "value3");

    Iterator<String> iterator = map.keySet().iterator();
    iterator.next(); // advance to key1
    iterator.remove();

    assertThat(map.size()).isEqualTo(2);
    assertThat(map.containsKey("key1")).isFalse();
    assertThat(map.containsKey("key2")).isTrue();
    assertThat(map.containsKey("key3")).isTrue();
  }

  @Test
  public void testKeySetIteratorRemoveWithoutNextThrows() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");

    Iterator<String> iterator = map.keySet().iterator();

    assertThrows(IllegalStateException.class, iterator::remove);
  }

  @Test
  public void testKeySetIteratorRemoveTwiceThrows() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");

    Iterator<String> iterator = map.keySet().iterator();
    iterator.next();
    iterator.remove();

    assertThrows(IllegalStateException.class, iterator::remove);
  }

  @Test
  public void testKeySetIteratorConcurrentModificationOnPut() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");

    Iterator<String> iterator = map.keySet().iterator();
    iterator.next(); // consume first element, still has second

    map.put("key3", "value3"); // structural modification

    assertThrows(ConcurrentModificationException.class, iterator::next);
  }

  @Test
  public void testKeySetIteratorConcurrentModificationOnRemove() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");

    Iterator<String> iterator = map.keySet().iterator();
    iterator.next();

    var unused = map.remove("key2"); // structural modification

    assertThrows(ConcurrentModificationException.class, iterator::next);
  }

  @Test
  public void testKeySetIteratorConcurrentModificationOnClear() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");

    Iterator<String> iterator = map.keySet().iterator();
    iterator.next();

    map.clear(); // structural modification

    assertThrows(ConcurrentModificationException.class, iterator::next);
  }

  @Test
  public void testKeySetIteratorCanContinueAfterRemove() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    map.put("key3", "value3");

    Iterator<String> iterator = map.keySet().iterator();
    iterator.next(); // key1
    iterator.remove();
    String secondKey = iterator.next(); // key2

    assertThat(secondKey).isEqualTo("key2");
  }

  // ==========================================================================
  // Tests for KeySet.contains()
  // ==========================================================================

  @Test
  public void testKeySetContainsExistingKey() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");
    Set<String> keySet = map.keySet();

    assertThat(keySet.contains("key")).isTrue();
  }

  @Test
  public void testKeySetContainsNonExistentKey() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");
    Set<String> keySet = map.keySet();

    assertThat(keySet.contains("nonexistent")).isFalse();
  }

  @Test
  public void testKeySetContainsOnEmptyMap() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    Set<String> keySet = map.keySet();

    assertThat(keySet.contains("key")).isFalse();
  }

  @Test
  public void testKeySetContainsWithMultipleKeys() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    map.put("key3", "value3");
    Set<String> keySet = map.keySet();

    assertThat(keySet.contains("key1")).isTrue();
    assertThat(keySet.contains("key2")).isTrue();
    assertThat(keySet.contains("key3")).isTrue();
    assertThat(keySet.contains("key4")).isFalse();
  }

  @Test
  public void testKeySetContainsAfterKeyRemoval() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");
    Set<String> keySet = map.keySet();

    assertThat(keySet.contains("key")).isTrue();

    var unused = map.remove("key");
    assertThat(keySet.contains("key")).isFalse();
  }

  @Test
  public void testKeySetContainsWithNullKeyReturnsFalse() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");
    Set<String> keySet = map.keySet();

    // LinkedTreeMap.findByObject handles null by returning null (false for contains)
    assertThat(keySet.contains(null)).isFalse();
  }

  @Test
  @SuppressWarnings("CollectionIncompatibleType") // Intentionally testing incompatible type behavior
  public void testKeySetContainsWithIncompatibleTypeReturnsFalse() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");
    Set<String> keySet = map.keySet();

    // LinkedTreeMap.findByObject catches ClassCastException and returns null (false for contains)
    assertThat(keySet.contains(Integer.valueOf(123))).isFalse();
  }

  @Test
  public void testKeySetContainsWithNullValueEntry() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>(true); // allowNullValues
    map.put("key", null);
    Set<String> keySet = map.keySet();

    assertThat(keySet.contains("key")).isTrue();
  }

  // ==========================================================================
  // Tests for KeySet.remove()
  // ==========================================================================

  @Test
  public void testKeySetRemoveExistingKey() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");
    Set<String> keySet = map.keySet();

    boolean removed = keySet.remove("key");

    assertThat(removed).isTrue();
    assertThat(map.containsKey("key")).isFalse();
    assertThat(map.size()).isEqualTo(0);
  }

  @Test
  public void testKeySetRemoveNonExistentKey() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");
    Set<String> keySet = map.keySet();

    boolean removed = keySet.remove("nonexistent");

    assertThat(removed).isFalse();
    assertThat(map.size()).isEqualTo(1);
  }

  @Test
  public void testKeySetRemoveOnEmptyMap() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    Set<String> keySet = map.keySet();

    boolean removed = keySet.remove("key");

    assertThat(removed).isFalse();
    assertThat(map.size()).isEqualTo(0);
  }

  @Test
  public void testKeySetRemoveMultipleKeys() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    map.put("key3", "value3");
    Set<String> keySet = map.keySet();

    assertThat(keySet.remove("key2")).isTrue();
    assertThat(map.containsKey("key2")).isFalse();
    assertThat(map.size()).isEqualTo(2);

    assertThat(keySet.remove("key1")).isTrue();
    assertThat(map.containsKey("key1")).isFalse();
    assertThat(map.size()).isEqualTo(1);

    assertThat(keySet.remove("key3")).isTrue();
    assertThat(map.containsKey("key3")).isFalse();
    assertThat(map.size()).isEqualTo(0);
  }

  @Test
  public void testKeySetRemovePreservesOtherKeys() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    map.put("key3", "value3");
    Set<String> keySet = map.keySet();

    keySet.remove("key2");

    assertThat(map.get("key1")).isEqualTo("value1");
    assertThat(map.get("key3")).isEqualTo("value3");
  }

  @Test
  public void testKeySetRemoveWithNullKeyReturnsFalse() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");
    Set<String> keySet = map.keySet();

    // LinkedTreeMap.removeInternalByKey uses findByObject which handles null gracefully
    assertThat(keySet.remove(null)).isFalse();
    assertThat(map.size()).isEqualTo(1); // Map unchanged
  }

  @Test
  @SuppressWarnings("CollectionIncompatibleType") // Intentionally testing incompatible type behavior
  public void testKeySetRemoveWithIncompatibleTypeReturnsFalse() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");
    Set<String> keySet = map.keySet();

    // LinkedTreeMap.removeInternalByKey uses findByObject which catches ClassCastException
    assertThat(keySet.remove(Integer.valueOf(123))).isFalse();
    assertThat(map.size()).isEqualTo(1); // Map unchanged
  }

  @Test
  public void testKeySetRemoveUpdatesSize() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    Set<String> keySet = map.keySet();

    assertThat(keySet.size()).isEqualTo(2);

    keySet.remove("key1");
    assertThat(keySet.size()).isEqualTo(1);

    keySet.remove("nonexistent");
    assertThat(keySet.size()).isEqualTo(1);

    keySet.remove("key2");
    assertThat(keySet.size()).isEqualTo(0);
  }

  @Test
  public void testKeySetRemovePreservesInsertionOrder() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("c", "3");
    map.put("a", "1");
    map.put("b", "2");
    map.put("d", "4");
    Set<String> keySet = map.keySet();

    keySet.remove("a");

    List<String> keys = new ArrayList<>();
    for (String key : keySet) {
      keys.add(key);
    }

    // Should maintain insertion order minus removed key
    assertThat(keys).containsExactly("c", "b", "d").inOrder();
  }

  // ==========================================================================
  // Tests for KeySet.clear()
  // ==========================================================================

  @Test
  public void testKeySetClearOnEmptyMap() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    Set<String> keySet = map.keySet();

    keySet.clear();

    assertThat(keySet.isEmpty()).isTrue();
    assertThat(map.isEmpty()).isTrue();
  }

  @Test
  public void testKeySetClearRemovesAllEntries() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    map.put("key3", "value3");
    Set<String> keySet = map.keySet();

    keySet.clear();

    assertThat(keySet.isEmpty()).isTrue();
    assertThat(keySet.size()).isEqualTo(0);
    assertThat(map.isEmpty()).isTrue();
    assertThat(map.size()).isEqualTo(0);
  }

  @Test
  public void testKeySetClearAffectsMap() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");

    map.keySet().clear();

    assertThat(map.containsKey("key")).isFalse();
    assertThat(map.get("key")).isNull();
  }

  @Test
  public void testMapUsableAfterKeySetClear() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.keySet().clear();

    map.put("key2", "value2");

    assertThat(map.size()).isEqualTo(1);
    assertThat(map.get("key2")).isEqualTo("value2");
  }

  @Test
  public void testKeySetClearConcurrentModificationDetected() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");

    Iterator<String> iterator = map.keySet().iterator();
    iterator.next();

    map.keySet().clear();

    assertThrows(ConcurrentModificationException.class, iterator::next);
  }

  @Test
  public void testKeySetClearMultipleTimes() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    Set<String> keySet = map.keySet();

    // First clear on empty
    keySet.clear();
    assertThat(map.isEmpty()).isTrue();

    // Add entries and clear
    map.put("key1", "value1");
    keySet.clear();
    assertThat(map.isEmpty()).isTrue();

    // Add again and clear
    map.put("key2", "value2");
    map.put("key3", "value3");
    keySet.clear();
    assertThat(map.isEmpty()).isTrue();
  }

  @Test
  public void testKeySetClearWithLargeDataset() {
    LinkedTreeMap<Integer, String> map = new LinkedTreeMap<>();
    for (int i = 0; i < 1000; i++) {
      map.put(i, "value" + i);
    }
    Set<Integer> keySet = map.keySet();

    keySet.clear();

    assertThat(keySet.isEmpty()).isTrue();
    assertThat(map.isEmpty()).isTrue();
  }

  @Test
  public void testKeySetClearAffectsEntrySet() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");

    map.keySet().clear();

    assertThat(map.entrySet().isEmpty()).isTrue();
  }

  // ==========================================================================
  // Integration tests
  // ==========================================================================

  @Test
  public void testKeySetViewIsLive() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    Set<String> keySet = map.keySet();

    // Initially empty
    assertThat(keySet.isEmpty()).isTrue();

    // Add to map, keySet reflects change
    map.put("key1", "value1");
    assertThat(keySet.contains("key1")).isTrue();
    assertThat(keySet.size()).isEqualTo(1);

    // Remove from map, keySet reflects change
    var unused = map.remove("key1");
    assertThat(keySet.contains("key1")).isFalse();
    assertThat(keySet.isEmpty()).isTrue();

    // Remove via keySet, map reflects change
    map.put("key2", "value2");
    keySet.remove("key2");
    assertThat(map.containsKey("key2")).isFalse();
  }

  @Test
  public void testKeySetWithIntegerKeys() {
    LinkedTreeMap<Integer, String> map = new LinkedTreeMap<>();
    map.put(3, "three");
    map.put(1, "one");
    map.put(2, "two");

    Set<Integer> keySet = map.keySet();

    assertThat(keySet.size()).isEqualTo(3);
    assertThat(keySet.contains(1)).isTrue();
    assertThat(keySet.contains(2)).isTrue();
    assertThat(keySet.contains(3)).isTrue();

    // Should maintain insertion order
    List<Integer> keys = new ArrayList<>(keySet);
    assertThat(keys).containsExactly(3, 1, 2).inOrder();
  }

  @Test
  public void testKeySetIteratorRemoveAllElements() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    map.put("key3", "value3");

    Iterator<String> iterator = map.keySet().iterator();
    while (iterator.hasNext()) {
      iterator.next();
      iterator.remove();
    }

    assertThat(map.isEmpty()).isTrue();
    assertThat(map.keySet().isEmpty()).isTrue();
  }
}
