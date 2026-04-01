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

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import org.junit.Test;

public final class LinkedTreeMapIteratorTest {

  @Test
  public void testHasNextEmptyMap() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();

    Iterator<String> it = map.keySet().iterator();

    assertThat(it.hasNext()).isFalse();
  }

  @Test
  public void testHasNextNonEmptyMap() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("a", "1");

    Iterator<String> it = map.keySet().iterator();

    assertThat(it.hasNext()).isTrue();
    it.next();
    assertThat(it.hasNext()).isFalse();
  }

  @Test
  public void testNextNodeReturnsElementsInInsertionOrder() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("a", "1");
    map.put("b", "2");
    map.put("c", "3");

    Iterator<String> it = map.keySet().iterator();

    assertThat(it.next()).isEqualTo("a");
    assertThat(it.next()).isEqualTo("b");
    assertThat(it.next()).isEqualTo("c");
  }

  @Test
  public void testNextNodeThrowsNoSuchElementOnEmptyMap() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();

    Iterator<String> it = map.keySet().iterator();

    assertThrows(NoSuchElementException.class, it::next);
  }

  @Test
  public void testNextNodeThrowsNoSuchElementAfterLastElement() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("a", "1");

    Iterator<String> it = map.keySet().iterator();
    it.next();

    assertThrows(NoSuchElementException.class, it::next);
  }

  @Test
  public void testNextNodeThrowsConcurrentModificationException() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("a", "1");
    map.put("b", "2");

    Iterator<String> it = map.keySet().iterator();
    it.next();
    map.put("c", "3");

    assertThrows(ConcurrentModificationException.class, it::next);
  }

  @Test
  public void testRemoveAfterNext() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("a", "1");
    map.put("b", "2");

    Iterator<String> it = map.keySet().iterator();
    it.next();
    it.remove();

    assertThat(map.size()).isEqualTo(1);
    assertThat(map.containsKey("a")).isFalse();
    assertThat(map.containsKey("b")).isTrue();
  }

  @Test
  public void testRemoveThrowsIllegalStateBeforeNext() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("a", "1");

    Iterator<String> it = map.keySet().iterator();

    assertThrows(IllegalStateException.class, it::remove);
  }

  @Test
  public void testRemoveThrowsIllegalStateWhenCalledTwice() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("a", "1");
    map.put("b", "2");

    Iterator<String> it = map.keySet().iterator();
    it.next();
    it.remove();

    assertThrows(IllegalStateException.class, it::remove);
  }

  @Test
  public void testEntrySetIteratorNextAndRemove() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("x", "10");
    map.put("y", "20");

    Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
    Map.Entry<String, String> entry = it.next();

    assertThat(entry.getKey()).isEqualTo("x");
    assertThat(entry.getValue()).isEqualTo("10");

    it.remove();

    assertThat(map.size()).isEqualTo(1);
    assertThat(map.containsKey("x")).isFalse();
  }
}
