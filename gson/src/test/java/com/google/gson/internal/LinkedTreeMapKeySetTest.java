/*
 * Copyright (C) 2026 Google Inc.
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

import java.util.Iterator;
import java.util.Set;
import org.junit.Test;

public class LinkedTreeMapKeySetTest {

  @Test
  public void testSize() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    Set<String> keySet = map.keySet();

    assertThat(keySet.size()).isEqualTo(0);

    map.put("key1", "value1");
    assertThat(keySet.size()).isEqualTo(1);

    map.put("key2", "value2");
    assertThat(keySet.size()).isEqualTo(2);
  }

  @Test
  public void testIterator() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    map.put("key3", "value3");

    Set<String> keySet = map.keySet();
    Iterator<String> iterator = keySet.iterator();

    assertThat(iterator.hasNext()).isTrue();
    assertThat(iterator.next()).isEqualTo("key1");
    assertThat(iterator.hasNext()).isTrue();
    assertThat(iterator.next()).isEqualTo("key2");
    assertThat(iterator.hasNext()).isTrue();
    assertThat(iterator.next()).isEqualTo("key3");
    assertThat(iterator.hasNext()).isFalse();
  }

  @Test
  public void testContains() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");

    Set<String> keySet = map.keySet();

    assertThat(keySet.contains("key1")).isTrue();
    assertThat(keySet.contains("key2")).isTrue();
    assertThat(keySet.contains("key3")).isFalse();
    assertThat(keySet.contains(null)).isFalse();
  }

  @Test
  public void testRemove() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");

    Set<String> keySet = map.keySet();

    assertThat(keySet.remove("key1")).isTrue();
    assertThat(keySet.size()).isEqualTo(1);
    assertThat(keySet.contains("key1")).isFalse();

    assertThat(keySet.remove("key3")).isFalse();
    assertThat(keySet.size()).isEqualTo(1);
  }

  @Test
  public void testClear() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    map.put("key3", "value3");

    Set<String> keySet = map.keySet();
    assertThat(keySet.size()).isEqualTo(3);

    keySet.clear();

    assertThat(keySet.size()).isEqualTo(0);
    assertThat(map.size()).isEqualTo(0);
  }
}
