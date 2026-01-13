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

import java.util.Map;
import java.util.Set;
import org.junit.Test;

/**
 * Tests for {@link LinkedTreeMap.EntrySet#size()}.
 *
 * @author Claude
 */
public class LinkedTreeMapClaude_sizeTest {

  // ==========================================================================
  // Tests for EntrySet.size()
  // ==========================================================================

  @Test
  public void testEntrySetSizeOnEmptyMap() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    assertThat(entrySet.size()).isEqualTo(0);
  }

  @Test
  public void testEntrySetSizeWithOneEntry() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    assertThat(entrySet.size()).isEqualTo(1);
  }

  @Test
  public void testEntrySetSizeWithMultipleEntries() {
    LinkedTreeMap<String, Integer> map = new LinkedTreeMap<>();
    map.put("one", 1);
    map.put("two", 2);
    map.put("three", 3);
    Set<Map.Entry<String, Integer>> entrySet = map.entrySet();

    assertThat(entrySet.size()).isEqualTo(3);
  }

  @Test
  public void testEntrySetSizeAfterPut() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    assertThat(entrySet.size()).isEqualTo(0);

    map.put("key1", "value1");
    assertThat(entrySet.size()).isEqualTo(1);

    map.put("key2", "value2");
    assertThat(entrySet.size()).isEqualTo(2);
  }

  @Test
  public void testEntrySetSizeAfterRemove() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    assertThat(entrySet.size()).isEqualTo(2);

    var unused1 = map.remove("key1");
    assertThat(entrySet.size()).isEqualTo(1);

    var unused2 = map.remove("key2");
    assertThat(entrySet.size()).isEqualTo(0);
  }

  @Test
  public void testEntrySetSizeAfterClear() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    assertThat(entrySet.size()).isEqualTo(2);

    map.clear();
    assertThat(entrySet.size()).isEqualTo(0);
  }

  @Test
  public void testEntrySetSizeConsistentWithMapSize() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("a", "1");
    map.put("b", "2");
    map.put("c", "3");

    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    assertThat(entrySet.size()).isEqualTo(map.size());
  }

  @Test
  public void testEntrySetSizeAfterReplacingValue() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value1");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    assertThat(entrySet.size()).isEqualTo(1);

    // Replacing a value should not change size
    map.put("key", "value2");
    assertThat(entrySet.size()).isEqualTo(1);
  }

  @Test
  public void testEntrySetSizeAfterRemoveNonExistentKey() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    assertThat(entrySet.size()).isEqualTo(1);

    // Removing non-existent key should not change size
    var unused = map.remove("nonexistent");
    assertThat(entrySet.size()).isEqualTo(1);
  }

  @Test
  public void testEntrySetSizeWithNullValues() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>(true); // allowNullValues = true
    map.put("key1", null);
    map.put("key2", "value");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    assertThat(entrySet.size()).isEqualTo(2);
  }

  @Test
  public void testEntrySetSizeAfterEntrySetClear() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    assertThat(entrySet.size()).isEqualTo(2);

    // Clearing via entrySet should reflect in size
    entrySet.clear();
    assertThat(entrySet.size()).isEqualTo(0);
    assertThat(map.size()).isEqualTo(0);
  }

  @Test
  public void testEntrySetSizeAfterRemovingViaEntrySet() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    assertThat(entrySet.size()).isEqualTo(1);

    // Get the entry and remove it via entry set
    Map.Entry<String, String> entry = entrySet.iterator().next();
    entrySet.remove(entry);

    assertThat(entrySet.size()).isEqualTo(0);
  }

  @Test
  public void testEntrySetSizeLargeDataset() {
    LinkedTreeMap<Integer, String> map = new LinkedTreeMap<>();
    Set<Map.Entry<Integer, String>> entrySet = map.entrySet();

    int count = 100;
    for (int i = 0; i < count; i++) {
      map.put(i, "value" + i);
    }

    assertThat(entrySet.size()).isEqualTo(count);
    assertThat(entrySet.size()).isEqualTo(map.size());
  }

  @Test
  public void testEntrySetSizeRemoveViaIterator() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();

    assertThat(entrySet.size()).isEqualTo(2);

    // Remove via iterator
    var iterator = entrySet.iterator();
    iterator.next();
    iterator.remove();

    assertThat(entrySet.size()).isEqualTo(1);
  }

  @Test
  public void testMultipleEntrySetCallsReturnSameSize() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");

    // Multiple calls to entrySet() should return consistent size
    Set<Map.Entry<String, String>> entrySet1 = map.entrySet();
    Set<Map.Entry<String, String>> entrySet2 = map.entrySet();

    assertThat(entrySet1.size()).isEqualTo(entrySet2.size());
    assertThat(entrySet1.size()).isEqualTo(2);
  }
}
