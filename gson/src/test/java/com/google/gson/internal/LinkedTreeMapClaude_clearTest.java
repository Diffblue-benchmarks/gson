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
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.junit.Test;

/**
 * Tests for {@link LinkedTreeMap.EntrySet#clear()}.
 *
 * @author Claude
 */
public class LinkedTreeMapClaude_clearTest {

  // ==========================================================================
  // Tests for EntrySet.clear() - basic functionality
  // ==========================================================================

  @Test
  public void testClearOnEmptyMap() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    entrySet.clear();

    assertThat(map.size()).isEqualTo(0);
    assertThat(entrySet.size()).isEqualTo(0);
    assertThat(map.isEmpty()).isTrue();
    assertThat(entrySet.isEmpty()).isTrue();
  }

  @Test
  public void testClearWithSingleEntry() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    entrySet.clear();

    assertThat(map.size()).isEqualTo(0);
    assertThat(entrySet.size()).isEqualTo(0);
    assertThat(map.containsKey("key")).isFalse();
  }

  @Test
  public void testClearWithMultipleEntries() {
    LinkedTreeMap<String, Integer> map = new LinkedTreeMap<>();
    map.put("one", 1);
    map.put("two", 2);
    map.put("three", 3);
    Set<Map.Entry<String, Integer>> entrySet = map.entrySet();

    entrySet.clear();

    assertThat(map.size()).isEqualTo(0);
    assertThat(entrySet.size()).isEqualTo(0);
    assertThat(map.containsKey("one")).isFalse();
    assertThat(map.containsKey("two")).isFalse();
    assertThat(map.containsKey("three")).isFalse();
  }

  // ==========================================================================
  // Tests for EntrySet.clear() effects on map
  // ==========================================================================

  @Test
  public void testClearAffectsMap() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    entrySet.clear();

    assertThat(map.get("key1")).isNull();
    assertThat(map.get("key2")).isNull();
    assertThat(map.isEmpty()).isTrue();
  }

  @Test
  public void testClearAffectsContainsKey() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    assertThat(map.containsKey("key")).isTrue();

    entrySet.clear();

    assertThat(map.containsKey("key")).isFalse();
  }

  @Test
  public void testClearAffectsEntrySetContains() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    Map.Entry<String, String> entry = new AbstractMap.SimpleEntry<>("key", "value");
    assertThat(entrySet.contains(entry)).isTrue();

    entrySet.clear();

    assertThat(entrySet.contains(entry)).isFalse();
  }

  @Test
  public void testClearAffectsKeySet() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    entrySet.clear();

    assertThat(map.keySet().isEmpty()).isTrue();
    assertThat(map.keySet().size()).isEqualTo(0);
  }

  // ==========================================================================
  // Tests for EntrySet.clear() and iterator behavior
  // ==========================================================================

  @Test
  public void testClearInvalidatesIterator() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();
    Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();

    iterator.next(); // start iterating
    entrySet.clear(); // clear via entrySet

    assertThrows(ConcurrentModificationException.class, iterator::next);
  }

  @Test
  public void testIteratorAfterClearHasNoElements() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    entrySet.clear();

    Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();
    assertThat(iterator.hasNext()).isFalse();
  }

  @Test
  public void testForEachAfterClear() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    entrySet.clear();

    int count = 0;
    for (Map.Entry<String, String> entry : entrySet) {
      count++;
    }
    assertThat(count).isEqualTo(0);
  }

  // ==========================================================================
  // Tests for multiple clears and operations after clear
  // ==========================================================================

  @Test
  public void testClearTwice() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    entrySet.clear();
    entrySet.clear(); // second clear should be no-op

    assertThat(map.isEmpty()).isTrue();
    assertThat(entrySet.isEmpty()).isTrue();
  }

  @Test
  public void testPutAfterClear() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    entrySet.clear();

    map.put("key2", "value2");

    assertThat(map.size()).isEqualTo(1);
    assertThat(entrySet.size()).isEqualTo(1);
    assertThat(map.get("key2")).isEqualTo("value2");
    assertThat(map.containsKey("key1")).isFalse();
  }

  @Test
  public void testMultipleClearAndPutCycles() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    // First cycle
    map.put("key1", "value1");
    assertThat(map.size()).isEqualTo(1);
    entrySet.clear();
    assertThat(map.size()).isEqualTo(0);

    // Second cycle
    map.put("key2", "value2");
    map.put("key3", "value3");
    assertThat(map.size()).isEqualTo(2);
    entrySet.clear();
    assertThat(map.size()).isEqualTo(0);

    // Third cycle
    map.put("key4", "value4");
    assertThat(map.size()).isEqualTo(1);
    assertThat(map.get("key4")).isEqualTo("value4");
  }

  // ==========================================================================
  // Tests for EntrySet.clear() with null values
  // ==========================================================================

  @Test
  public void testClearWithNullValues() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>(true); // allowNullValues = true
    map.put("key1", null);
    map.put("key2", "value2");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    entrySet.clear();

    assertThat(map.isEmpty()).isTrue();
    assertThat(entrySet.isEmpty()).isTrue();
  }

  // ==========================================================================
  // Tests for EntrySet.clear() with large dataset
  // ==========================================================================

  @Test
  public void testClearLargeDataset() {
    LinkedTreeMap<Integer, String> map = new LinkedTreeMap<>();
    int count = 100;
    for (int i = 0; i < count; i++) {
      map.put(i, "value" + i);
    }
    Set<Map.Entry<Integer, String>> entrySet = map.entrySet();

    assertThat(map.size()).isEqualTo(count);

    entrySet.clear();

    assertThat(map.size()).isEqualTo(0);
    assertThat(entrySet.size()).isEqualTo(0);

    // Verify all entries are gone
    for (int i = 0; i < count; i++) {
      assertThat(map.containsKey(i)).isFalse();
    }
  }

  // ==========================================================================
  // Tests for EntrySet.clear() vs Map.clear()
  // ==========================================================================

  @Test
  public void testEntrySetClearEquivalentToMapClear() {
    LinkedTreeMap<String, String> map1 = new LinkedTreeMap<>();
    LinkedTreeMap<String, String> map2 = new LinkedTreeMap<>();

    map1.put("key1", "value1");
    map1.put("key2", "value2");
    map2.put("key1", "value1");
    map2.put("key2", "value2");

    map1.entrySet().clear();
    map2.clear();

    assertThat(map1.size()).isEqualTo(map2.size());
    assertThat(map1.isEmpty()).isEqualTo(map2.isEmpty());
  }

  @Test
  public void testClearPreservesMapFunctionality() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    entrySet.clear();

    // Map should still be fully functional after clear
    map.put("newKey", "newValue");
    assertThat(map.get("newKey")).isEqualTo("newValue");
    assertThat(map.containsKey("newKey")).isTrue();
    assertThat(map.size()).isEqualTo(1);

    var unused = map.remove("newKey");
    assertThat(map.isEmpty()).isTrue();
  }

  @Test
  public void testClearMaintainsInsertionOrderForNewEntries() {
    LinkedTreeMap<String, Integer> map = new LinkedTreeMap<>();
    map.put("a", 1);
    map.put("b", 2);
    Set<Map.Entry<String, Integer>> entrySet = map.entrySet();

    entrySet.clear();

    // Add new entries
    map.put("z", 26);
    map.put("y", 25);
    map.put("x", 24);

    // Verify insertion order is maintained
    var iterator = entrySet.iterator();
    assertThat(iterator.next().getKey()).isEqualTo("z");
    assertThat(iterator.next().getKey()).isEqualTo("y");
    assertThat(iterator.next().getKey()).isEqualTo("x");
    assertThat(iterator.hasNext()).isFalse();
  }
}
