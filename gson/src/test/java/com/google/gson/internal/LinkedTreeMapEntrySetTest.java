/*
 * Copyright (C) 2024 Google Inc.
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
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

public final class LinkedTreeMapEntrySetTest {

  private LinkedTreeMap<String, Integer> map;
  private Set<Entry<String, Integer>> entrySet;

  @Before
  public void setUp() {
    map = new LinkedTreeMap<>();
    map.put("a", 1);
    map.put("b", 2);
    map.put("c", 3);
    entrySet = map.entrySet();
  }

  @Test
  public void testEntrySetSize() {
    assertThat(entrySet.size()).isEqualTo(3);
  }

  @Test
  public void testEntrySetSizeEmpty() {
    LinkedTreeMap<String, Integer> empty = new LinkedTreeMap<>();
    assertThat(empty.entrySet().size()).isEqualTo(0);
  }

  @Test
  public void testEntrySetIterator() {
    Iterator<Entry<String, Integer>> it = entrySet.iterator();
    assertThat(it).isNotNull();
    assertThat(it.hasNext()).isTrue();

    Entry<String, Integer> first = it.next();
    assertThat(first).isNotNull();
    assertThat(first.getKey()).isEqualTo("a");
    assertThat(first.getValue()).isEqualTo(1);
  }

  @Test
  public void testEntrySetIteratorTraversesAllEntries() {
    int count = 0;
    for (Entry<String, Integer> entry : entrySet) {
      assertThat(entry).isNotNull();
      count++;
    }
    assertThat(count).isEqualTo(3);
  }

  @Test
  public void testEntrySetContainsExistingEntry() {
    Entry<String, Integer> entry = new AbstractMap.SimpleEntry<>("a", 1);
    assertThat(entrySet.contains(entry)).isTrue();
  }

  @Test
  public void testEntrySetContainsNonEntry() {
    assertThat(entrySet.contains("not-an-entry")).isFalse();
  }

  @Test
  public void testEntrySetContainsEntryWithWrongValue() {
    Entry<String, Integer> entry = new AbstractMap.SimpleEntry<>("a", 99);
    assertThat(entrySet.contains(entry)).isFalse();
  }

  @Test
  public void testEntrySetContainsEntryWithMissingKey() {
    Entry<String, Integer> entry = new AbstractMap.SimpleEntry<>("z", 1);
    assertThat(entrySet.contains(entry)).isFalse();
  }

  @Test
  public void testEntrySetRemoveExistingEntry() {
    Entry<String, Integer> entry = new AbstractMap.SimpleEntry<>("b", 2);
    boolean removed = entrySet.remove(entry);
    assertThat(removed).isTrue();
    assertThat(map.containsKey("b")).isFalse();
    assertThat(entrySet.size()).isEqualTo(2);
  }

  @Test
  public void testEntrySetRemoveNonEntry() {
    boolean removed = entrySet.remove("not-an-entry");
    assertThat(removed).isFalse();
    assertThat(entrySet.size()).isEqualTo(3);
  }

  @Test
  public void testEntrySetRemoveEntryWithWrongValue() {
    Entry<String, Integer> entry = new AbstractMap.SimpleEntry<>("a", 99);
    boolean removed = entrySet.remove(entry);
    assertThat(removed).isFalse();
    assertThat(map.containsKey("a")).isTrue();
  }

  @Test
  public void testEntrySetRemoveEntryWithMissingKey() {
    Entry<String, Integer> entry = new AbstractMap.SimpleEntry<>("z", 1);
    boolean removed = entrySet.remove(entry);
    assertThat(removed).isFalse();
    assertThat(entrySet.size()).isEqualTo(3);
  }

  @Test
  public void testEntrySetClear() {
    entrySet.clear();
    assertThat(entrySet.size()).isEqualTo(0);
    assertThat(map.size()).isEqualTo(0);
  }
}
