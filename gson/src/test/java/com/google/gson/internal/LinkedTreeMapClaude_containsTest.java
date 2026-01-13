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
 * Tests for {@link LinkedTreeMap.EntrySet#contains(Object)}.
 *
 * @author Claude
 */
public class LinkedTreeMapClaude_containsTest {

  // ==========================================================================
  // Tests for EntrySet.contains() - basic functionality
  // ==========================================================================

  @Test
  public void testContainsOnEmptyMap() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    Map.Entry<String, String> entry = new AbstractMap.SimpleEntry<>("key", "value");
    assertThat(entrySet.contains(entry)).isFalse();
  }

  @Test
  public void testContainsExistingEntry() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    Map.Entry<String, String> entry = new AbstractMap.SimpleEntry<>("key", "value");
    assertThat(entrySet.contains(entry)).isTrue();
  }

  @Test
  public void testContainsNonExistingKey() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    Map.Entry<String, String> entry = new AbstractMap.SimpleEntry<>("otherKey", "value");
    assertThat(entrySet.contains(entry)).isFalse();
  }

  @Test
  public void testContainsExistingKeyButDifferentValue() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    Map.Entry<String, String> entry = new AbstractMap.SimpleEntry<>("key", "differentValue");
    assertThat(entrySet.contains(entry)).isFalse();
  }

  @Test
  public void testContainsMultipleEntries() {
    LinkedTreeMap<String, Integer> map = new LinkedTreeMap<>();
    map.put("one", 1);
    map.put("two", 2);
    map.put("three", 3);
    Set<Map.Entry<String, Integer>> entrySet = map.entrySet();

    assertThat(entrySet.contains(new AbstractMap.SimpleEntry<>("one", 1))).isTrue();
    assertThat(entrySet.contains(new AbstractMap.SimpleEntry<>("two", 2))).isTrue();
    assertThat(entrySet.contains(new AbstractMap.SimpleEntry<>("three", 3))).isTrue();
    assertThat(entrySet.contains(new AbstractMap.SimpleEntry<>("four", 4))).isFalse();
  }

  // ==========================================================================
  // Tests for EntrySet.contains() with null values
  // ==========================================================================

  @Test
  public void testContainsEntryWithNullValue() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>(true); // allowNullValues = true
    map.put("key", null);
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    Map.Entry<String, String> entry = new AbstractMap.SimpleEntry<>("key", null);
    assertThat(entrySet.contains(entry)).isTrue();
  }

  @Test
  public void testContainsEntryWithNullValueWhenMapHasNonNullValue() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    Map.Entry<String, String> entry = new AbstractMap.SimpleEntry<>("key", null);
    assertThat(entrySet.contains(entry)).isFalse();
  }

  @Test
  public void testContainsEntryWithNonNullValueWhenMapHasNullValue() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>(true); // allowNullValues = true
    map.put("key", null);
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    Map.Entry<String, String> entry = new AbstractMap.SimpleEntry<>("key", "value");
    assertThat(entrySet.contains(entry)).isFalse();
  }

  // ==========================================================================
  // Tests for EntrySet.contains() with non-Entry objects
  // ==========================================================================

  @Test
  public void testContainsNull() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    assertThat(entrySet.contains(null)).isFalse();
  }

  @Test
  @SuppressWarnings("CollectionIncompatibleType")
  public void testContainsNonEntryObject() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    // These tests verify that contains() handles non-Entry objects gracefully
    assertThat(entrySet.contains("not an entry")).isFalse();
    assertThat(entrySet.contains(123)).isFalse();
    assertThat(entrySet.contains(new Object())).isFalse();
  }

  @Test
  @SuppressWarnings("CollectionIncompatibleType")
  public void testContainsString() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    // These tests verify that contains() returns false for String arguments
    assertThat(entrySet.contains("key")).isFalse();
    assertThat(entrySet.contains("value")).isFalse();
    assertThat(entrySet.contains("key=value")).isFalse();
  }

  // ==========================================================================
  // Tests for EntrySet.contains() with actual entry from the map
  // ==========================================================================

  @Test
  public void testContainsActualEntryFromIterator() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    Map.Entry<String, String> actualEntry = entrySet.iterator().next();
    assertThat(entrySet.contains(actualEntry)).isTrue();
  }

  @Test
  public void testContainsActualEntryAfterValueChange() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value1");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    Map.Entry<String, String> actualEntry = entrySet.iterator().next();
    actualEntry.setValue("value2"); // modify value through entry

    // Entry should still be contained (with new value)
    assertThat(entrySet.contains(actualEntry)).isTrue();

    // Old value entry should not be contained
    Map.Entry<String, String> oldEntry = new AbstractMap.SimpleEntry<>("key", "value1");
    assertThat(entrySet.contains(oldEntry)).isFalse();
  }

  // ==========================================================================
  // Tests for EntrySet.contains() after map modifications
  // ==========================================================================

  @Test
  public void testContainsAfterPut() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    Map.Entry<String, String> entry = new AbstractMap.SimpleEntry<>("key", "value");
    assertThat(entrySet.contains(entry)).isFalse();

    map.put("key", "value");
    assertThat(entrySet.contains(entry)).isTrue();
  }

  @Test
  public void testContainsAfterRemove() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    Map.Entry<String, String> entry = new AbstractMap.SimpleEntry<>("key", "value");
    assertThat(entrySet.contains(entry)).isTrue();

    var unused = map.remove("key");
    assertThat(entrySet.contains(entry)).isFalse();
  }

  @Test
  public void testContainsAfterClear() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    Map.Entry<String, String> entry1 = new AbstractMap.SimpleEntry<>("key1", "value1");
    Map.Entry<String, String> entry2 = new AbstractMap.SimpleEntry<>("key2", "value2");

    assertThat(entrySet.contains(entry1)).isTrue();
    assertThat(entrySet.contains(entry2)).isTrue();

    map.clear();

    assertThat(entrySet.contains(entry1)).isFalse();
    assertThat(entrySet.contains(entry2)).isFalse();
  }

  @Test
  public void testContainsAfterValueUpdate() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value1");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    Map.Entry<String, String> oldEntry = new AbstractMap.SimpleEntry<>("key", "value1");
    Map.Entry<String, String> newEntry = new AbstractMap.SimpleEntry<>("key", "value2");

    assertThat(entrySet.contains(oldEntry)).isTrue();
    assertThat(entrySet.contains(newEntry)).isFalse();

    map.put("key", "value2"); // update value

    assertThat(entrySet.contains(oldEntry)).isFalse();
    assertThat(entrySet.contains(newEntry)).isTrue();
  }

  // ==========================================================================
  // Tests for EntrySet.contains() with different key types
  // ==========================================================================

  @Test
  public void testContainsWithIntegerKeys() {
    LinkedTreeMap<Integer, String> map = new LinkedTreeMap<>();
    map.put(1, "one");
    map.put(2, "two");
    Set<Map.Entry<Integer, String>> entrySet = map.entrySet();

    assertThat(entrySet.contains(new AbstractMap.SimpleEntry<>(1, "one"))).isTrue();
    assertThat(entrySet.contains(new AbstractMap.SimpleEntry<>(2, "two"))).isTrue();
    assertThat(entrySet.contains(new AbstractMap.SimpleEntry<>(3, "three"))).isFalse();
  }

  @Test
  public void testContainsWithLongKeys() {
    LinkedTreeMap<Long, String> map = new LinkedTreeMap<>();
    map.put(Long.MAX_VALUE, "max");
    map.put(Long.MIN_VALUE, "min");
    Set<Map.Entry<Long, String>> entrySet = map.entrySet();

    assertThat(entrySet.contains(new AbstractMap.SimpleEntry<>(Long.MAX_VALUE, "max"))).isTrue();
    assertThat(entrySet.contains(new AbstractMap.SimpleEntry<>(Long.MIN_VALUE, "min"))).isTrue();
  }

  // ==========================================================================
  // Tests for EntrySet.contains() - edge cases
  // ==========================================================================

  @Test
  public void testContainsWithSameKeyDifferentEntryInstances() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    // Different entry instances with same key-value should be considered equal
    Map.Entry<String, String> entry1 = new AbstractMap.SimpleEntry<>("key", "value");
    Map.Entry<String, String> entry2 = new AbstractMap.SimpleEntry<>("key", "value");

    assertThat(entrySet.contains(entry1)).isTrue();
    assertThat(entrySet.contains(entry2)).isTrue();
  }

  @Test
  public void testContainsLargeDataset() {
    LinkedTreeMap<Integer, String> map = new LinkedTreeMap<>();
    int count = 100;
    for (int i = 0; i < count; i++) {
      map.put(i, "value" + i);
    }
    Set<Map.Entry<Integer, String>> entrySet = map.entrySet();

    // All entries should be contained
    for (int i = 0; i < count; i++) {
      Map.Entry<Integer, String> entry = new AbstractMap.SimpleEntry<>(i, "value" + i);
      assertThat(entrySet.contains(entry)).isTrue();
    }

    // Entry with non-existent key should not be contained
    Map.Entry<Integer, String> nonExistent = new AbstractMap.SimpleEntry<>(count, "valueX");
    assertThat(entrySet.contains(nonExistent)).isFalse();
  }

  @Test
  public void testContainsWithImmutableEntry() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    // Using SimpleImmutableEntry
    Map.Entry<String, String> immutableEntry =
        new AbstractMap.SimpleImmutableEntry<>("key", "value");
    assertThat(entrySet.contains(immutableEntry)).isTrue();
  }

  @Test
  public void testContainsReflectsMapState() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    Set<Map.Entry<String, String>> entrySet = map.entrySet();
    Map.Entry<String, String> entry = new AbstractMap.SimpleEntry<>("key", "value");

    // Initially not contained
    assertThat(entrySet.contains(entry)).isFalse();

    // Add entry
    map.put("key", "value");
    assertThat(entrySet.contains(entry)).isTrue();

    // Change value
    map.put("key", "newValue");
    assertThat(entrySet.contains(entry)).isFalse();

    // Restore value
    map.put("key", "value");
    assertThat(entrySet.contains(entry)).isTrue();

    // Remove entry
    var unused = map.remove("key");
    assertThat(entrySet.contains(entry)).isFalse();
  }
}
