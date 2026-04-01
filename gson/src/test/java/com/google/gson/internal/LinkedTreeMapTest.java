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
import static org.junit.Assert.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.junit.Test;

public final class LinkedTreeMapTest {

  @Test
  public void testDefaultConstructorCreatesEmptyMap() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();

    assertThat(map.size()).isEqualTo(0);
    assertThat(map.isEmpty()).isTrue();
  }

  @Test
  public void testConstructorWithAllowNullValuesTrue() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>(true);

    map.put("key", null);
    assertThat(map.get("key")).isNull();
  }

  @Test
  public void testConstructorWithAllowNullValuesFalse() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>(false);

    NullPointerException e = assertThrows(NullPointerException.class, () -> map.put("key", null));
    assertThat(e).hasMessageThat().isEqualTo("value == null");
  }

  @Test
  public void testConstructorWithCustomComparator() {
    Comparator<String> caseInsensitive = String.CASE_INSENSITIVE_ORDER;
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>(caseInsensitive, true);

    map.put("Key", "value");
    assertThat(map.get("key")).isEqualTo("value");
    assertThat(map.get("KEY")).isEqualTo("value");
  }

  @Test
  public void testConstructorWithNullComparatorUsesNaturalOrder() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>(null, true);

    map.put("b", "second");
    map.put("a", "first");
    assertThat(map.get("a")).isEqualTo("first");
    assertThat(map.get("b")).isEqualTo("second");
  }

  @Test
  public void testSize() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();

    assertThat(map.size()).isEqualTo(0);
    map.put("a", "1");
    assertThat(map.size()).isEqualTo(1);
    map.put("b", "2");
    assertThat(map.size()).isEqualTo(2);
  }

  @Test
  public void testGetExistingKey() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");

    assertThat(map.get("key")).isEqualTo("value");
  }

  @Test
  public void testGetMissingKey() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();

    assertThat(map.get("missing")).isNull();
  }

  @Test
  public void testGetNullKeyReturnsNull() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();

    assertThat(map.get(null)).isNull();
  }

  @Test
  public void testGetWrongTypeReturnsNull() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");

    assertThat(map.get(42)).isNull();
  }

  @Test
  public void testContainsKeyPresent() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");

    assertThat(map.containsKey("key")).isTrue();
  }

  @Test
  public void testContainsKeyAbsent() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();

    assertThat(map.containsKey("missing")).isFalse();
  }

  @Test
  public void testContainsKeyNull() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();

    assertThat(map.containsKey(null)).isFalse();
  }

  @Test
  public void testPutReturnsOldValue() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "old");

    String old = map.put("key", "new");
    assertThat(old).isEqualTo("old");
    assertThat(map.get("key")).isEqualTo("new");
  }

  @Test
  public void testPutNewKeyReturnsNull() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();

    String old = map.put("key", "value");
    assertThat(old).isNull();
  }

  @Test
  public void testPutNullKeyThrows() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();

    NullPointerException e =
        assertThrows(NullPointerException.class, () -> map.put(null, "value"));
    assertThat(e).hasMessageThat().isEqualTo("key == null");
  }

  @Test
  public void testPutNullValueThrowsWhenNotAllowed() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>(false);

    NullPointerException e =
        assertThrows(NullPointerException.class, () -> map.put("key", null));
    assertThat(e).hasMessageThat().isEqualTo("value == null");
  }

  @Test
  public void testPutNullValueAllowedByDefault() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();

    map.put("key", null);
    assertThat(map.get("key")).isNull();
    assertThat(map.containsKey("key")).isTrue();
  }

  @Test
  public void testClearEmptiesMap() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("a", "1");
    map.put("b", "2");

    map.clear();
    assertThat(map.size()).isEqualTo(0);
    assertThat(map.get("a")).isNull();
    assertThat(map.get("b")).isNull();
  }

  @Test
  public void testClearAllowsReuse() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("a", "1");
    map.clear();

    map.put("b", "2");
    assertThat(map.size()).isEqualTo(1);
    assertThat(map.get("b")).isEqualTo("2");
  }

  @Test
  public void testRemoveExistingKey() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");

    String removed = map.remove("key");
    assertThat(removed).isEqualTo("value");
    assertThat(map.containsKey("key")).isFalse();
    assertThat(map.size()).isEqualTo(0);
  }

  @Test
  public void testRemoveMissingKeyReturnsNull() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();

    assertThat(map.remove("missing")).isNull();
  }

  @Test
  public void testRemoveNullKeyReturnsNull() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");

    assertThat(map.remove(null)).isNull();
    assertThat(map.size()).isEqualTo(1);
  }

  @Test
  public void testPutManyKeysTriggersRebalancing() {
    LinkedTreeMap<Integer, String> map = new LinkedTreeMap<>();
    // Insert many keys to trigger AVL tree rotations
    for (int i = 0; i < 100; i++) {
      map.put(i, "value" + i);
    }

    assertThat(map.size()).isEqualTo(100);
    for (int i = 0; i < 100; i++) {
      assertThat(map.get(i)).isEqualTo("value" + i);
    }
  }

  @Test
  public void testInsertionOrderPreserved() {
    LinkedTreeMap<String, Integer> map = new LinkedTreeMap<>();
    map.put("c", 3);
    map.put("a", 1);
    map.put("b", 2);

    // Iteration should be in insertion order
    Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
    assertThat(it.next().getKey()).isEqualTo("c");
    assertThat(it.next().getKey()).isEqualTo("a");
    assertThat(it.next().getKey()).isEqualTo("b");
  }

  @Test
  public void testEntrySetSize() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("a", "1");
    map.put("b", "2");

    Set<Map.Entry<String, String>> entries = map.entrySet();
    assertThat(entries.size()).isEqualTo(2);
  }

  @Test
  public void testEntrySetIsCached() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();

    Set<Map.Entry<String, String>> first = map.entrySet();
    Set<Map.Entry<String, String>> second = map.entrySet();
    assertThat(first).isSameInstanceAs(second);
  }

  @Test
  public void testEntrySetContains() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");

    Map.Entry<String, String> entry = map.entrySet().iterator().next();
    assertThat(map.entrySet().contains(entry)).isTrue();
  }

  @Test
  public void testEntrySetRemove() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");

    Map.Entry<String, String> entry = map.entrySet().iterator().next();
    boolean removed = map.entrySet().remove(entry);
    assertThat(removed).isTrue();
    assertThat(map.size()).isEqualTo(0);
  }

  @Test
  public void testKeySetSize() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("a", "1");
    map.put("b", "2");

    Set<String> keys = map.keySet();
    assertThat(keys.size()).isEqualTo(2);
  }

  @Test
  public void testKeySetIsCached() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();

    Set<String> first = map.keySet();
    Set<String> second = map.keySet();
    assertThat(first).isSameInstanceAs(second);
  }

  @Test
  public void testKeySetContains() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");

    assertThat(map.keySet().contains("key")).isTrue();
    assertThat(map.keySet().contains("missing")).isFalse();
  }

  @Test
  public void testKeySetRemove() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");

    boolean removed = map.keySet().remove("key");
    assertThat(removed).isTrue();
    assertThat(map.size()).isEqualTo(0);
  }

  @Test
  public void testRemoveFromMiddleOfTree() {
    LinkedTreeMap<Integer, String> map = new LinkedTreeMap<>();
    for (int i = 1; i <= 7; i++) {
      map.put(i, "v" + i);
    }

    map.remove(4); // remove root-ish node
    assertThat(map.size()).isEqualTo(6);
    assertThat(map.containsKey(4)).isFalse();
    for (int i = 1; i <= 7; i++) {
      if (i != 4) {
        assertThat(map.get(i)).isEqualTo("v" + i);
      }
    }
  }

  @Test
  public void testWriteReplaceSerializesAsLinkedHashMap() throws Exception {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("a", "1");
    map.put("b", "2");

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(baos);
    oos.writeObject(map);
    oos.close();

    ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
    Object deserialized = ois.readObject();
    ois.close();

    assertThat(deserialized).isInstanceOf(LinkedHashMap.class);
    @SuppressWarnings("unchecked")
    LinkedHashMap<String, String> result = (LinkedHashMap<String, String>) deserialized;
    assertThat(result).containsEntry("a", "1");
    assertThat(result).containsEntry("b", "2");
  }

  @Test
  public void testReadObjectThrowsInvalidObjectException() throws Exception {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    java.lang.reflect.Method readObject =
        LinkedTreeMap.class.getDeclaredMethod("readObject", ObjectInputStream.class);
    readObject.setAccessible(true);

    java.lang.reflect.InvocationTargetException e =
        assertThrows(
            java.lang.reflect.InvocationTargetException.class,
            () -> readObject.invoke(map, (ObjectInputStream) null));
    assertThat(e.getCause()).isInstanceOf(InvalidObjectException.class);
    assertThat(e.getCause()).hasMessageThat().isEqualTo("Deserialization is unsupported");
  }

  @Test
  public void testFindWithNonComparableKeyThrows() {
    LinkedTreeMap<Object, String> map = new LinkedTreeMap<>();

    // Object is not Comparable; putting a first key is fine until comparison is needed
    // The cast to Comparable happens inside find(), so a second distinct key triggers the error
    assertThrows(ClassCastException.class, () -> {
      map.put(new Object(), "a");
      map.put(new Object(), "b");
    });
  }

  @Test
  public void testIteratorRemove() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("a", "1");
    map.put("b", "2");
    map.put("c", "3");

    Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
    it.next();
    it.remove();

    assertThat(map.size()).isEqualTo(2);
  }

  @Test
  public void testDescendingInsertionTriggersLeftRotation() {
    LinkedTreeMap<Integer, String> map = new LinkedTreeMap<>();
    // Ascending insertion triggers right-right case (left rotations)
    for (int i = 1; i <= 10; i++) {
      map.put(i, "v" + i);
    }

    assertThat(map.size()).isEqualTo(10);
    for (int i = 1; i <= 10; i++) {
      assertThat(map.get(i)).isEqualTo("v" + i);
    }
  }

  @Test
  public void testAscendingThenDescendingInsertionTriggersRightRotation() {
    LinkedTreeMap<Integer, String> map = new LinkedTreeMap<>();
    // Descending insertion triggers left-left case (right rotations)
    for (int i = 10; i >= 1; i--) {
      map.put(i, "v" + i);
    }

    assertThat(map.size()).isEqualTo(10);
    for (int i = 1; i <= 10; i++) {
      assertThat(map.get(i)).isEqualTo("v" + i);
    }
  }
}
