/*
 * Copyright (C) 2024 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.gson.internal;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

public final class LinkedTreeMapKeySetTest {

  private LinkedTreeMap<String, Integer> map;
  private Set<String> keySet;

  @Before
  public void setUp() {
    map = new LinkedTreeMap<>();
    map.put("a", 1);
    map.put("b", 2);
    map.put("c", 3);
    keySet = map.keySet();
  }

  @Test
  public void testKeySetSize() {
    assertThat(keySet.size()).isEqualTo(3);
  }

  @Test
  public void testKeySetSizeEmpty() {
    LinkedTreeMap<String, Integer> empty = new LinkedTreeMap<>();
    assertThat(empty.keySet().size()).isEqualTo(0);
  }

  @Test
  public void testKeySetIterator() {
    Iterator<String> it = keySet.iterator();
    List<String> keys = new ArrayList<>();
    while (it.hasNext()) {
      keys.add(it.next());
    }
    assertThat(keys).containsExactly("a", "b", "c");
  }

  @Test
  public void testKeySetIteratorReturnsKeys() {
    Iterator<String> it = keySet.iterator();
    assertThat(it.hasNext()).isTrue();
    assertThat(it.next()).isEqualTo("a");
  }

  @Test
  public void testKeySetContainsExistingKey() {
    assertThat(keySet.contains("a")).isTrue();
  }

  @Test
  public void testKeySetContainsMissingKey() {
    assertThat(keySet.contains("z")).isFalse();
  }

  @Test
  public void testKeySetRemoveExistingKey() {
    boolean removed = keySet.remove("b");
    assertThat(removed).isTrue();
    assertThat(map.containsKey("b")).isFalse();
    assertThat(map.size()).isEqualTo(2);
  }

  @Test
  public void testKeySetRemoveMissingKey() {
    boolean removed = keySet.remove("z");
    assertThat(removed).isFalse();
    assertThat(map.size()).isEqualTo(3);
  }

  @Test
  public void testKeySetClear() {
    keySet.clear();
    assertThat(map.size()).isEqualTo(0);
    assertThat(keySet.size()).isEqualTo(0);
  }
}
