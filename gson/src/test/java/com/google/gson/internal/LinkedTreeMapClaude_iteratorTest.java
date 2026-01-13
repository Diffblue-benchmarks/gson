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
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.junit.Test;

/**
 * Tests for {@link LinkedTreeMap.EntrySet#iterator()}.
 *
 * @author Claude
 */
public class LinkedTreeMapClaude_iteratorTest {

  // ==========================================================================
  // Tests for EntrySet.iterator() - basic functionality
  // ==========================================================================

  @Test
  public void testIteratorOnEmptyMap() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    Set<Map.Entry<String, String>> entrySet = map.entrySet();
    Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();

    assertThat(iterator.hasNext()).isFalse();
  }

  @Test
  public void testIteratorOnEmptyMapThrowsNoSuchElementException() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    Set<Map.Entry<String, String>> entrySet = map.entrySet();
    Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();

    assertThrows(NoSuchElementException.class, iterator::next);
  }

  @Test
  public void testIteratorWithSingleEntry() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();
    Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();

    assertThat(iterator.hasNext()).isTrue();
    Map.Entry<String, String> entry = iterator.next();
    assertThat(entry.getKey()).isEqualTo("key");
    assertThat(entry.getValue()).isEqualTo("value");
    assertThat(iterator.hasNext()).isFalse();
  }

  @Test
  public void testIteratorWithMultipleEntries() {
    LinkedTreeMap<String, Integer> map = new LinkedTreeMap<>();
    map.put("one", 1);
    map.put("two", 2);
    map.put("three", 3);
    Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
    Iterator<Map.Entry<String, Integer>> iterator = entrySet.iterator();

    List<String> keys = new ArrayList<>();
    List<Integer> values = new ArrayList<>();
    while (iterator.hasNext()) {
      Map.Entry<String, Integer> entry = iterator.next();
      keys.add(entry.getKey());
      values.add(entry.getValue());
    }

    assertThat(keys).containsExactly("one", "two", "three").inOrder();
    assertThat(values).containsExactly(1, 2, 3).inOrder();
  }

  @Test
  public void testIteratorMaintainsInsertionOrder() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("zebra", "z");
    map.put("apple", "a");
    map.put("mango", "m");
    map.put("banana", "b");

    Set<Map.Entry<String, String>> entrySet = map.entrySet();
    Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();

    List<String> keys = new ArrayList<>();
    while (iterator.hasNext()) {
      keys.add(iterator.next().getKey());
    }

    // Should be in insertion order, not sorted order
    assertThat(keys).containsExactly("zebra", "apple", "mango", "banana").inOrder();
  }

  @Test
  public void testIteratorThrowsNoSuchElementExceptionAfterLastEntry() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();
    Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();

    iterator.next(); // consume the only entry
    assertThrows(NoSuchElementException.class, iterator::next);
  }

  // ==========================================================================
  // Tests for EntrySet.iterator().remove()
  // ==========================================================================

  @Test
  public void testIteratorRemove() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();
    Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();

    iterator.next(); // move to first entry
    iterator.remove();

    assertThat(map.size()).isEqualTo(1);
    assertThat(map.containsKey("key1")).isFalse();
    assertThat(map.containsKey("key2")).isTrue();
  }

  @Test
  public void testIteratorRemoveLastEntry() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();
    Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();

    iterator.next(); // first entry
    iterator.next(); // second entry
    iterator.remove(); // remove second entry

    assertThat(map.size()).isEqualTo(1);
    assertThat(map.containsKey("key1")).isTrue();
    assertThat(map.containsKey("key2")).isFalse();
  }

  @Test
  public void testIteratorRemoveAllEntries() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    map.put("key3", "value3");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();
    Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();

    while (iterator.hasNext()) {
      iterator.next();
      iterator.remove();
    }

    assertThat(map.isEmpty()).isTrue();
    assertThat(map.size()).isEqualTo(0);
  }

  @Test
  public void testIteratorRemoveWithoutNextThrowsIllegalStateException() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();
    Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();

    // Calling remove without calling next first
    assertThrows(IllegalStateException.class, iterator::remove);
  }

  @Test
  public void testIteratorRemoveCalledTwiceThrowsIllegalStateException() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();
    Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();

    iterator.next();
    iterator.remove();

    // Calling remove again without calling next
    assertThrows(IllegalStateException.class, iterator::remove);
  }

  @Test
  public void testIteratorRemoveAllowsContinuedIteration() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    map.put("key3", "value3");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();
    Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();

    iterator.next(); // key1
    iterator.remove(); // remove key1

    assertThat(iterator.hasNext()).isTrue();
    Map.Entry<String, String> entry = iterator.next();
    assertThat(entry.getKey()).isEqualTo("key2");
  }

  // ==========================================================================
  // Tests for ConcurrentModificationException
  // ==========================================================================

  @Test
  public void testIteratorThrowsConcurrentModificationExceptionOnPut() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();
    Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();

    iterator.next(); // start iterating
    map.put("key3", "value3"); // modify the map by adding a new key

    assertThrows(ConcurrentModificationException.class, iterator::next);
  }

  @Test
  public void testIteratorThrowsConcurrentModificationExceptionOnRemove() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();
    Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();

    iterator.next(); // start iterating
    var unused = map.remove("key2"); // modify the map

    assertThrows(ConcurrentModificationException.class, iterator::next);
  }

  @Test
  public void testIteratorThrowsConcurrentModificationExceptionOnClear() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();
    Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();

    iterator.next(); // start iterating
    map.clear(); // modify the map

    assertThrows(ConcurrentModificationException.class, iterator::next);
  }

  @Test
  public void testIteratorDoesNotThrowConcurrentModificationOnValueUpdate() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();
    Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();

    iterator.next(); // start iterating
    map.put("key1", "newValue"); // update existing key (doesn't change structure)

    // This should not throw as modCount doesn't change for value updates
    Map.Entry<String, String> entry = iterator.next();
    assertThat(entry.getKey()).isEqualTo("key2");
  }

  // ==========================================================================
  // Tests for edge cases
  // ==========================================================================

  @Test
  public void testIteratorWithNullValues() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>(true); // allowNullValues = true
    map.put("key1", null);
    map.put("key2", "value2");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();
    Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();

    Map.Entry<String, String> entry1 = iterator.next();
    assertThat(entry1.getKey()).isEqualTo("key1");
    assertThat(entry1.getValue()).isNull();

    Map.Entry<String, String> entry2 = iterator.next();
    assertThat(entry2.getKey()).isEqualTo("key2");
    assertThat(entry2.getValue()).isEqualTo("value2");
  }

  @Test
  public void testMultipleIteratorsIndependent() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    Iterator<Map.Entry<String, String>> iter1 = entrySet.iterator();
    Iterator<Map.Entry<String, String>> iter2 = entrySet.iterator();

    // Advance iter1 but not iter2
    iter1.next();

    // iter2 should still be at the beginning
    assertThat(iter2.next().getKey()).isEqualTo("key1");
    assertThat(iter1.next().getKey()).isEqualTo("key2");
  }

  @Test
  public void testIteratorForEachLoop() {
    LinkedTreeMap<String, Integer> map = new LinkedTreeMap<>();
    map.put("a", 1);
    map.put("b", 2);
    map.put("c", 3);

    List<String> keys = new ArrayList<>();
    for (Map.Entry<String, Integer> entry : map.entrySet()) {
      keys.add(entry.getKey());
    }

    assertThat(keys).containsExactly("a", "b", "c").inOrder();
  }

  @Test
  public void testIteratorLargeDataset() {
    LinkedTreeMap<Integer, String> map = new LinkedTreeMap<>();
    int count = 100;
    for (int i = 0; i < count; i++) {
      map.put(i, "value" + i);
    }

    Set<Map.Entry<Integer, String>> entrySet = map.entrySet();
    Iterator<Map.Entry<Integer, String>> iterator = entrySet.iterator();

    int iteratedCount = 0;
    int expectedKey = 0;
    while (iterator.hasNext()) {
      Map.Entry<Integer, String> entry = iterator.next();
      assertThat(entry.getKey()).isEqualTo(expectedKey);
      assertThat(entry.getValue()).isEqualTo("value" + expectedKey);
      expectedKey++;
      iteratedCount++;
    }

    assertThat(iteratedCount).isEqualTo(count);
  }

  @Test
  public void testNewIteratorAfterMapModification() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");

    Iterator<Map.Entry<String, String>> iter1 = map.entrySet().iterator();
    iter1.next(); // consume first entry

    map.put("key2", "value2"); // modify map

    // New iterator should work fine
    Iterator<Map.Entry<String, String>> iter2 = map.entrySet().iterator();
    assertThat(iter2.hasNext()).isTrue();
    assertThat(iter2.next().getKey()).isEqualTo("key1");
    assertThat(iter2.hasNext()).isTrue();
    assertThat(iter2.next().getKey()).isEqualTo("key2");
  }

  @Test
  public void testIteratorRemoveDoesNotAffectOtherIterator() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");

    Iterator<Map.Entry<String, String>> iter1 = map.entrySet().iterator();
    Iterator<Map.Entry<String, String>> iter2 = map.entrySet().iterator();

    iter1.next();
    iter1.remove(); // remove via iter1

    // iter2 should throw ConcurrentModificationException
    assertThrows(ConcurrentModificationException.class, iter2::next);
  }

  @Test
  public void testIteratorReturnsActualEntryNodes() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value1");

    Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
    Map.Entry<String, String> entry = iterator.next();

    // Modify value through entry
    entry.setValue("value2");

    // Map should reflect the change
    assertThat(map.get("key")).isEqualTo("value2");
  }

  @Test
  public void testHasNextDoesNotAdvanceIterator() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");

    Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();

    // Call hasNext multiple times
    assertThat(iterator.hasNext()).isTrue();
    assertThat(iterator.hasNext()).isTrue();
    assertThat(iterator.hasNext()).isTrue();

    // First next() should still return first entry
    assertThat(iterator.next().getKey()).isEqualTo("key1");
  }
}
