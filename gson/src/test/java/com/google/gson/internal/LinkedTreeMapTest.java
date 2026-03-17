/*
 * Copyright (C) 2010 The Android Open Source Project
 * Copyright (C) 2012 Google Inc.
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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.junit.Test;

public class LinkedTreeMapTest {

  @Test
  public void testDefaultConstructor() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    assertThat(map.size()).isEqualTo(0);
    assertThat(map.entrySet()).isEmpty();
  }

  @Test
  public void testConstructorWithAllowNullValues() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>(true);
    assertThat(map.size()).isEqualTo(0);
    map.put("key", null);
    assertThat(map.get("key")).isNull();
  }

  @Test
  public void testConstructorWithAllowNullValuesFalse() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>(false);
    assertThat(map.size()).isEqualTo(0);
    try {
      map.put("key", null);
      throw new AssertionError("Expected NullPointerException");
    } catch (NullPointerException expected) {
      assertThat(expected).hasMessageThat().contains("value == null");
    }
  }

  @Test
  public void testConstructorWithComparator() {
    Comparator<String> comparator = new Comparator<String>() {
      @Override
      public int compare(String a, String b) {
        return a.compareToIgnoreCase(b);
      }
    };
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>(comparator, true);
    assertThat(map.size()).isEqualTo(0);
    map.put("A", "valueA");
    map.put("a", "valuea");
    assertThat(map.size()).isEqualTo(1);
    assertThat(map.get("A")).isEqualTo("valuea");
  }

  @Test
  public void testSize() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    assertThat(map.size()).isEqualTo(0);
    map.put("key1", "value1");
    assertThat(map.size()).isEqualTo(1);
    map.put("key2", "value2");
    assertThat(map.size()).isEqualTo(2);
    map.remove("key1");
    assertThat(map.size()).isEqualTo(1);
  }

  @Test
  public void testGet() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    assertThat(map.get("key1")).isEqualTo("value1");
    assertThat(map.get("key2")).isNull();
    assertThat(map.get(null)).isNull();
  }

  @Test
  public void testContainsKey() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    assertThat(map.containsKey("key1")).isTrue();
    assertThat(map.containsKey("key2")).isFalse();
    assertThat(map.containsKey(null)).isFalse();
  }

  @Test
  public void testPut() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    assertThat(map.put("key1", "value1")).isNull();
    assertThat(map.put("key1", "value2")).isEqualTo("value1");
    assertThat(map.get("key1")).isEqualTo("value2");
  }

  @Test
  public void testPutNullKey() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    try {
      map.put(null, "value");
      throw new AssertionError("Expected NullPointerException");
    } catch (NullPointerException expected) {
      assertThat(expected).hasMessageThat().contains("key == null");
    }
  }

  @Test
  public void testPutNullValueWhenNotAllowed() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>(false);
    try {
      map.put("key", null);
      throw new AssertionError("Expected NullPointerException");
    } catch (NullPointerException expected) {
      assertThat(expected).hasMessageThat().contains("value == null");
    }
  }

  @Test
  public void testClear() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    assertThat(map.size()).isEqualTo(2);
    map.clear();
    assertThat(map.size()).isEqualTo(0);
    assertThat(map.containsKey("key1")).isFalse();
    assertThat(map.containsKey("key2")).isFalse();
  }

  @Test
  public void testRemove() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    assertThat(map.remove("key1")).isEqualTo("value1");
    assertThat(map.remove("key2")).isNull();
    assertThat(map.size()).isEqualTo(0);
  }

  @Test
  public void testEntrySet() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    Set<Map.Entry<String, String>> entrySet = map.entrySet();
    assertThat(entrySet).isNotNull();
    assertThat(entrySet.size()).isEqualTo(2);
    assertThat(map.entrySet()).isSameInstanceAs(entrySet);
  }

  @Test
  public void testKeySet() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    Set<String> keySet = map.keySet();
    assertThat(keySet).isNotNull();
    assertThat(keySet.size()).isEqualTo(2);
    assertThat(map.keySet()).isSameInstanceAs(keySet);
  }

  @Test
  public void testIterationOrder() {
    LinkedTreeMap<Integer, String> map = new LinkedTreeMap<>();
    map.put(3, "c");
    map.put(1, "a");
    map.put(2, "b");
    Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
    assertThat(iterator.next().getKey()).isEqualTo(3);
    assertThat(iterator.next().getKey()).isEqualTo(1);
    assertThat(iterator.next().getKey()).isEqualTo(2);
    assertThat(iterator.hasNext()).isFalse();
  }

  @Test
  public void testRemoveWithIterator() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    map.put("key3", "value3");
    Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
    iterator.next();
    iterator.remove();
    assertThat(map.size()).isEqualTo(2);
  }

  @Test
  public void testKeySetIterator() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    Iterator<String> iterator = map.keySet().iterator();
    assertThat(iterator.next()).isEqualTo("key1");
    assertThat(iterator.next()).isEqualTo("key2");
    assertThat(iterator.hasNext()).isFalse();
  }

  @Test
  public void testKeySetContains() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    assertThat(map.keySet().contains("key1")).isTrue();
    assertThat(map.keySet().contains("key2")).isFalse();
  }

  @Test
  public void testKeySetRemove() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    assertThat(map.keySet().remove("key1")).isTrue();
    assertThat(map.size()).isEqualTo(1);
    assertThat(map.keySet().remove("key1")).isFalse();
  }

  @Test
  public void testKeySetClear() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    map.keySet().clear();
    assertThat(map.size()).isEqualTo(0);
  }

  @Test
  public void testEntrySetContains() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    Map.Entry<String, String> entry = map.entrySet().iterator().next();
    assertThat(map.entrySet().contains(entry)).isTrue();
  }

  @Test
  public void testEntrySetRemove() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    Map.Entry<String, String> entry = map.entrySet().iterator().next();
    assertThat(map.entrySet().remove(entry)).isTrue();
    assertThat(map.size()).isEqualTo(1);
  }

  @Test
  public void testEntrySetClear() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    map.entrySet().clear();
    assertThat(map.size()).isEqualTo(0);
  }

  @Test
  public void testFindByObjectWithIncomparableKey() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    assertThat(map.get(Integer.valueOf(1))).isNull();
  }

  @Test
  public void testPutMany() {
    LinkedTreeMap<Integer, String> map = new LinkedTreeMap<>();
    for (int i = 0; i < 100; i++) {
      map.put(i, "value" + i);
    }
    assertThat(map.size()).isEqualTo(100);
    for (int i = 0; i < 100; i++) {
      assertThat(map.get(i)).isEqualTo("value" + i);
    }
  }

  @Test
  public void testRemoveMany() {
    LinkedTreeMap<Integer, String> map = new LinkedTreeMap<>();
    for (int i = 0; i < 100; i++) {
      map.put(i, "value" + i);
    }
    for (int i = 0; i < 50; i++) {
      assertThat(map.remove(i)).isEqualTo("value" + i);
    }
    assertThat(map.size()).isEqualTo(50);
    for (int i = 50; i < 100; i++) {
      assertThat(map.get(i)).isEqualTo("value" + i);
    }
  }

  @Test
  public void testTreeRotations() {
    LinkedTreeMap<Integer, String> map = new LinkedTreeMap<>();
    // Insert in order to trigger tree rotations
    for (int i = 1; i <= 20; i++) {
      map.put(i, "value" + i);
    }
    assertThat(map.size()).isEqualTo(20);
    // Remove in reverse order to trigger more rotations
    for (int i = 20; i >= 1; i--) {
      assertThat(map.remove(i)).isEqualTo("value" + i);
    }
    assertThat(map.size()).isEqualTo(0);
  }

  @Test
  public void testNonComparableKey() {
    LinkedTreeMap<Object, String> map = new LinkedTreeMap<>();
    Object key = new Object();
    try {
      map.put(key, "value");
      throw new AssertionError("Expected ClassCastException");
    } catch (ClassCastException expected) {
      assertThat(expected).hasMessageThat().contains("is not Comparable");
    }
  }

  @Test
  public void testWriteReplace() throws Exception {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(baos);
    oos.writeObject(map);
    oos.close();
    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    ObjectInputStream ois = new ObjectInputStream(bais);
    @SuppressWarnings("unchecked")
    Map<String, String> deserialized = (Map<String, String>) ois.readObject();
    assertThat(deserialized.size()).isEqualTo(2);
    assertThat(deserialized.get("key1")).isEqualTo("value1");
    assertThat(deserialized.get("key2")).isEqualTo("value2");
  }

  @Test
  public void testReplaceValue() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key1", "value2");
    assertThat(map.get("key1")).isEqualTo("value2");
    assertThat(map.size()).isEqualTo(1);
  }

  @Test
  public void testGetAfterRemove() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.remove("key1");
    assertThat(map.get("key1")).isNull();
  }

  @Test
  public void testMultiplePutsAndRemoves() {
    LinkedTreeMap<Integer, String> map = new LinkedTreeMap<>();
    for (int i = 0; i < 10; i++) {
      map.put(i, "value" + i);
    }
    map.remove(5);
    map.remove(3);
    map.remove(7);
    map.put(5, "newValue5");
    assertThat(map.size()).isEqualTo(8);
    assertThat(map.get(5)).isEqualTo("newValue5");
    assertThat(map.get(3)).isNull();
  }

  @Test
  public void testRemoveRoot() {
    LinkedTreeMap<Integer, String> map = new LinkedTreeMap<>();
    map.put(2, "value2");
    map.put(1, "value1");
    map.put(3, "value3");
    map.remove(2);
    assertThat(map.size()).isEqualTo(2);
    assertThat(map.get(1)).isEqualTo("value1");
    assertThat(map.get(3)).isEqualTo("value3");
  }

  @Test
  public void testRemoveLeftChild() {
    LinkedTreeMap<Integer, String> map = new LinkedTreeMap<>();
    map.put(2, "value2");
    map.put(1, "value1");
    map.put(3, "value3");
    map.remove(1);
    assertThat(map.size()).isEqualTo(2);
    assertThat(map.get(2)).isEqualTo("value2");
    assertThat(map.get(3)).isEqualTo("value3");
  }

  @Test
  public void testRemoveRightChild() {
    LinkedTreeMap<Integer, String> map = new LinkedTreeMap<>();
    map.put(2, "value2");
    map.put(1, "value1");
    map.put(3, "value3");
    map.remove(3);
    assertThat(map.size()).isEqualTo(2);
    assertThat(map.get(1)).isEqualTo("value1");
    assertThat(map.get(2)).isEqualTo("value2");
  }

  @Test
  public void testRemoveNodeWithTwoChildren() {
    LinkedTreeMap<Integer, String> map = new LinkedTreeMap<>();
    map.put(5, "value5");
    map.put(3, "value3");
    map.put(7, "value7");
    map.put(2, "value2");
    map.put(4, "value4");
    map.put(6, "value6");
    map.put(8, "value8");
    map.remove(5);
    assertThat(map.size()).isEqualTo(6);
    assertThat(map.get(5)).isNull();
    assertThat(map.get(3)).isEqualTo("value3");
    assertThat(map.get(7)).isEqualTo("value7");
  }

  @Test
  public void testClearAfterMultipleOperations() {
    LinkedTreeMap<Integer, String> map = new LinkedTreeMap<>();
    for (int i = 0; i < 20; i++) {
      map.put(i, "value" + i);
    }
    for (int i = 0; i < 10; i++) {
      map.remove(i);
    }
    map.clear();
    assertThat(map.size()).isEqualTo(0);
    assertThat(map.entrySet()).isEmpty();
  }

  @Test
  public void testEntrySetContainsNonEntry() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    assertThat(map.entrySet().contains("not an entry")).isFalse();
  }

  @Test
  public void testEntrySetRemoveNonEntry() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    assertThat(map.entrySet().remove("not an entry")).isFalse();
  }

  @Test
  public void testCustomComparatorWithNullComparator() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>(null, true);
    map.put("b", "valueB");
    map.put("a", "valueA");
    map.put("c", "valueC");
    assertThat(map.size()).isEqualTo(3);
  }

  @Test
  public void testPutWithComplexTreeBalancing() {
    LinkedTreeMap<Integer, String> map = new LinkedTreeMap<>();
    // Create a scenario that requires multiple rotations
    int[] insertOrder = {10, 5, 15, 2, 7, 12, 20, 1, 3, 6, 8, 11, 13, 18, 25};
    for (int key : insertOrder) {
      map.put(key, "value" + key);
    }
    assertThat(map.size()).isEqualTo(insertOrder.length);
    for (int key : insertOrder) {
      assertThat(map.get(key)).isEqualTo("value" + key);
    }
  }

  @Test
  public void testRemoveWithComplexTreeBalancing() {
    LinkedTreeMap<Integer, String> map = new LinkedTreeMap<>();
    for (int i = 1; i <= 15; i++) {
      map.put(i, "value" + i);
    }
    int[] removeOrder = {8, 4, 12, 2, 6, 10, 14};
    for (int key : removeOrder) {
      map.remove(key);
    }
    assertThat(map.size()).isEqualTo(15 - removeOrder.length);
    for (int key : removeOrder) {
      assertThat(map.get(key)).isNull();
    }
  }
}
