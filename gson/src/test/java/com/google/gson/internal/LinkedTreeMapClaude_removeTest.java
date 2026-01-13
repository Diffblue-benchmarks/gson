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

import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;
import org.junit.Test;

/**
 * Tests for {@link LinkedTreeMap.EntrySet#remove(Object)}.
 *
 * @author Claude
 */
public class LinkedTreeMapClaude_removeTest {

  // ==========================================================================
  // Tests for EntrySet.remove() - basic functionality
  // ==========================================================================

  @Test
  public void testRemoveFromEmptyMap() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    Map.Entry<String, String> entry = new AbstractMap.SimpleEntry<>("key", "value");
    assertThat(entrySet.remove(entry)).isFalse();
    assertThat(map.size()).isEqualTo(0);
  }

  @Test
  public void testRemoveExistingEntry() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    Map.Entry<String, String> entry = new AbstractMap.SimpleEntry<>("key", "value");
    assertThat(entrySet.remove(entry)).isTrue();
    assertThat(map.size()).isEqualTo(0);
    assertThat(map.containsKey("key")).isFalse();
  }

  @Test
  public void testRemoveNonExistingKey() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    Map.Entry<String, String> entry = new AbstractMap.SimpleEntry<>("otherKey", "value");
    assertThat(entrySet.remove(entry)).isFalse();
    assertThat(map.size()).isEqualTo(1);
    assertThat(map.containsKey("key")).isTrue();
  }

  @Test
  public void testRemoveExistingKeyButDifferentValue() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    Map.Entry<String, String> entry = new AbstractMap.SimpleEntry<>("key", "differentValue");
    assertThat(entrySet.remove(entry)).isFalse();
    assertThat(map.size()).isEqualTo(1);
    assertThat(map.get("key")).isEqualTo("value");
  }

  @Test
  public void testRemoveMultipleEntries() {
    LinkedTreeMap<String, Integer> map = new LinkedTreeMap<>();
    map.put("one", 1);
    map.put("two", 2);
    map.put("three", 3);
    Set<Map.Entry<String, Integer>> entrySet = map.entrySet();

    assertThat(entrySet.remove(new AbstractMap.SimpleEntry<>("two", 2))).isTrue();
    assertThat(map.size()).isEqualTo(2);
    assertThat(map.containsKey("one")).isTrue();
    assertThat(map.containsKey("two")).isFalse();
    assertThat(map.containsKey("three")).isTrue();

    assertThat(entrySet.remove(new AbstractMap.SimpleEntry<>("one", 1))).isTrue();
    assertThat(map.size()).isEqualTo(1);

    assertThat(entrySet.remove(new AbstractMap.SimpleEntry<>("three", 3))).isTrue();
    assertThat(map.size()).isEqualTo(0);
  }

  // ==========================================================================
  // Tests for EntrySet.remove() with null values
  // ==========================================================================

  @Test
  public void testRemoveEntryWithNullValue() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>(true); // allowNullValues = true
    map.put("key", null);
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    Map.Entry<String, String> entry = new AbstractMap.SimpleEntry<>("key", null);
    assertThat(entrySet.remove(entry)).isTrue();
    assertThat(map.size()).isEqualTo(0);
  }

  @Test
  public void testRemoveEntryWithNullValueWhenMapHasNonNullValue() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    Map.Entry<String, String> entry = new AbstractMap.SimpleEntry<>("key", null);
    assertThat(entrySet.remove(entry)).isFalse();
    assertThat(map.size()).isEqualTo(1);
  }

  @Test
  public void testRemoveEntryWithNonNullValueWhenMapHasNullValue() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>(true); // allowNullValues = true
    map.put("key", null);
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    Map.Entry<String, String> entry = new AbstractMap.SimpleEntry<>("key", "value");
    assertThat(entrySet.remove(entry)).isFalse();
    assertThat(map.size()).isEqualTo(1);
  }

  // ==========================================================================
  // Tests for EntrySet.remove() with non-Entry objects
  // ==========================================================================

  @Test
  public void testRemoveNull() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    assertThat(entrySet.remove(null)).isFalse();
    assertThat(map.size()).isEqualTo(1);
  }

  @Test
  @SuppressWarnings("CollectionIncompatibleType")
  public void testRemoveNonEntryObject() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    // These tests verify that remove() handles non-Entry objects gracefully
    assertThat(entrySet.remove("not an entry")).isFalse();
    assertThat(entrySet.remove(123)).isFalse();
    assertThat(entrySet.remove(new Object())).isFalse();
    assertThat(map.size()).isEqualTo(1);
  }

  @Test
  @SuppressWarnings("CollectionIncompatibleType")
  public void testRemoveString() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    // These tests verify that remove() returns false for String arguments
    assertThat(entrySet.remove("key")).isFalse();
    assertThat(entrySet.remove("value")).isFalse();
    assertThat(entrySet.remove("key=value")).isFalse();
    assertThat(map.size()).isEqualTo(1);
  }

  // ==========================================================================
  // Tests for EntrySet.remove() with actual entry from the map
  // ==========================================================================

  @Test
  public void testRemoveActualEntryFromIterator() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    Map.Entry<String, String> actualEntry = entrySet.iterator().next();
    assertThat(entrySet.remove(actualEntry)).isTrue();
    assertThat(map.size()).isEqualTo(0);
  }

  @Test
  public void testRemoveActualEntryAfterValueChange() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value1");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    Map.Entry<String, String> actualEntry = entrySet.iterator().next();
    actualEntry.setValue("value2"); // modify value through entry

    // Entry should still be removable (with new value)
    assertThat(entrySet.remove(actualEntry)).isTrue();
    assertThat(map.size()).isEqualTo(0);
  }

  // ==========================================================================
  // Tests for EntrySet.remove() effects on map
  // ==========================================================================

  @Test
  public void testRemoveAffectsMap() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    Map.Entry<String, String> entry = new AbstractMap.SimpleEntry<>("key1", "value1");
    entrySet.remove(entry);

    assertThat(map.get("key1")).isNull();
    assertThat(map.containsKey("key1")).isFalse();
    assertThat(map.get("key2")).isEqualTo("value2");
  }

  @Test
  public void testRemoveAffectsEntrySetSize() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    assertThat(entrySet.size()).isEqualTo(2);

    Map.Entry<String, String> entry = new AbstractMap.SimpleEntry<>("key1", "value1");
    entrySet.remove(entry);

    assertThat(entrySet.size()).isEqualTo(1);
  }

  @Test
  public void testRemoveAffectsEntrySetContains() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    Map.Entry<String, String> entry = new AbstractMap.SimpleEntry<>("key", "value");
    assertThat(entrySet.contains(entry)).isTrue();

    entrySet.remove(entry);

    assertThat(entrySet.contains(entry)).isFalse();
  }

  @Test
  public void testRemoveSameEntryTwice() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    Map.Entry<String, String> entry = new AbstractMap.SimpleEntry<>("key", "value");

    assertThat(entrySet.remove(entry)).isTrue();
    assertThat(entrySet.remove(entry)).isFalse(); // second removal should fail
    assertThat(map.size()).isEqualTo(0);
  }

  // ==========================================================================
  // Tests for EntrySet.remove() with different key types
  // ==========================================================================

  @Test
  public void testRemoveWithIntegerKeys() {
    LinkedTreeMap<Integer, String> map = new LinkedTreeMap<>();
    map.put(1, "one");
    map.put(2, "two");
    Set<Map.Entry<Integer, String>> entrySet = map.entrySet();

    assertThat(entrySet.remove(new AbstractMap.SimpleEntry<>(1, "one"))).isTrue();
    assertThat(map.size()).isEqualTo(1);
    assertThat(map.containsKey(1)).isFalse();
    assertThat(map.containsKey(2)).isTrue();
  }

  @Test
  public void testRemoveWithLongKeys() {
    LinkedTreeMap<Long, String> map = new LinkedTreeMap<>();
    map.put(Long.MAX_VALUE, "max");
    map.put(Long.MIN_VALUE, "min");
    Set<Map.Entry<Long, String>> entrySet = map.entrySet();

    assertThat(entrySet.remove(new AbstractMap.SimpleEntry<>(Long.MAX_VALUE, "max"))).isTrue();
    assertThat(map.size()).isEqualTo(1);
    assertThat(map.containsKey(Long.MAX_VALUE)).isFalse();
    assertThat(map.containsKey(Long.MIN_VALUE)).isTrue();
  }

  // ==========================================================================
  // Tests for EntrySet.remove() - edge cases
  // ==========================================================================

  @Test
  public void testRemoveWithDifferentEntryInstances() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    // Different entry instances with same key-value should still remove
    Map.Entry<String, String> entry1 = new AbstractMap.SimpleEntry<>("key", "value");
    Map.Entry<String, String> entry2 = new AbstractMap.SimpleEntry<>("key", "value");

    assertThat(entrySet.remove(entry1)).isTrue();
    assertThat(entrySet.remove(entry2)).isFalse(); // already removed
  }

  @Test
  public void testRemoveLargeDataset() {
    LinkedTreeMap<Integer, String> map = new LinkedTreeMap<>();
    int count = 100;
    for (int i = 0; i < count; i++) {
      map.put(i, "value" + i);
    }
    Set<Map.Entry<Integer, String>> entrySet = map.entrySet();

    // Remove every other entry
    for (int i = 0; i < count; i += 2) {
      Map.Entry<Integer, String> entry = new AbstractMap.SimpleEntry<>(i, "value" + i);
      assertThat(entrySet.remove(entry)).isTrue();
    }

    assertThat(map.size()).isEqualTo(count / 2);

    // Verify remaining entries
    for (int i = 0; i < count; i++) {
      if (i % 2 == 0) {
        assertThat(map.containsKey(i)).isFalse();
      } else {
        assertThat(map.containsKey(i)).isTrue();
      }
    }
  }

  @Test
  public void testRemoveWithImmutableEntry() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    // Using SimpleImmutableEntry
    Map.Entry<String, String> immutableEntry =
        new AbstractMap.SimpleImmutableEntry<>("key", "value");
    assertThat(entrySet.remove(immutableEntry)).isTrue();
    assertThat(map.size()).isEqualTo(0);
  }

  @Test
  public void testRemoveAllEntriesViaEntrySet() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    map.put("key3", "value3");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    entrySet.remove(new AbstractMap.SimpleEntry<>("key1", "value1"));
    entrySet.remove(new AbstractMap.SimpleEntry<>("key2", "value2"));
    entrySet.remove(new AbstractMap.SimpleEntry<>("key3", "value3"));

    assertThat(map.isEmpty()).isTrue();
    assertThat(entrySet.isEmpty()).isTrue();
  }

  @Test
  public void testRemovePreservesInsertionOrderOfRemainingEntries() {
    LinkedTreeMap<String, Integer> map = new LinkedTreeMap<>();
    map.put("a", 1);
    map.put("b", 2);
    map.put("c", 3);
    map.put("d", 4);
    Set<Map.Entry<String, Integer>> entrySet = map.entrySet();

    // Remove middle entry
    entrySet.remove(new AbstractMap.SimpleEntry<>("b", 2));

    // Verify remaining entries are in correct order
    var iterator = entrySet.iterator();
    assertThat(iterator.next().getKey()).isEqualTo("a");
    assertThat(iterator.next().getKey()).isEqualTo("c");
    assertThat(iterator.next().getKey()).isEqualTo("d");
    assertThat(iterator.hasNext()).isFalse();
  }
}
