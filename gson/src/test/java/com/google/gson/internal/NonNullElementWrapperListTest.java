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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;

/** Unit tests for {@link NonNullElementWrapperList}. */
public final class NonNullElementWrapperListTest {

  private ArrayList<String> delegate;
  private NonNullElementWrapperList<String> list;

  @Before
  public void setUp() {
    delegate = new ArrayList<>();
    list = new NonNullElementWrapperList<>(delegate);
  }

  @Test
  public void testConstructorNullDelegateThrows() {
    assertThrows(NullPointerException.class, () -> new NonNullElementWrapperList<>(null));
  }

  @Test
  public void testGet() {
    delegate.add("a");
    delegate.add("b");

    assertThat(list.get(0)).isEqualTo("a");
    assertThat(list.get(1)).isEqualTo("b");
  }

  @Test
  public void testSize() {
    assertThat(list.size()).isEqualTo(0);

    delegate.add("a");
    assertThat(list.size()).isEqualTo(1);

    delegate.add("b");
    assertThat(list.size()).isEqualTo(2);
  }

  @Test
  public void testSetNullThrows() {
    delegate.add("a");

    assertThrows(NullPointerException.class, () -> list.set(0, null));
  }

  @Test
  public void testSetNonNull() {
    delegate.add("a");

    String previous = list.set(0, "b");

    assertThat(previous).isEqualTo("a");
    assertThat(list.get(0)).isEqualTo("b");
  }

  @Test
  public void testAddNullThrows() {
    assertThrows(NullPointerException.class, () -> list.add(0, null));
  }

  @Test
  public void testAddNonNull() {
    list.add(0, "a");
    list.add(1, "b");

    assertThat(list.get(0)).isEqualTo("a");
    assertThat(list.get(1)).isEqualTo("b");
  }

  @Test
  public void testRemoveByIndex() {
    delegate.add("a");
    delegate.add("b");

    String removed = list.remove(0);

    assertThat(removed).isEqualTo("a");
    assertThat(list.size()).isEqualTo(1);
  }

  @Test
  public void testClear() {
    delegate.add("a");
    delegate.add("b");

    list.clear();

    assertThat(list.size()).isEqualTo(0);
  }

  @Test
  public void testRemoveByObject() {
    delegate.add("a");
    delegate.add("b");

    boolean removed = list.remove("a");

    assertThat(removed).isTrue();
    assertThat(list.size()).isEqualTo(1);
    assertThat(list.get(0)).isEqualTo("b");
  }

  @Test
  public void testRemoveByObjectNotPresent() {
    delegate.add("a");

    boolean removed = list.remove("z");

    assertThat(removed).isFalse();
  }

  @Test
  public void testRemoveAll() {
    delegate.add("a");
    delegate.add("b");
    delegate.add("c");

    boolean changed = list.removeAll(Arrays.asList("a", "c"));

    assertThat(changed).isTrue();
    assertThat(list).containsExactly("b");
  }

  @Test
  public void testRetainAll() {
    delegate.add("a");
    delegate.add("b");
    delegate.add("c");

    boolean changed = list.retainAll(Arrays.asList("b"));

    assertThat(changed).isTrue();
    assertThat(list).containsExactly("b");
  }

  @Test
  public void testContainsPresent() {
    delegate.add("a");

    assertThat(list.contains("a")).isTrue();
  }

  @Test
  public void testContainsAbsent() {
    assertThat(list.contains("a")).isFalse();
  }

  @Test
  public void testContainsNull() {
    assertThat(list.contains(null)).isFalse();
  }

  @Test
  public void testIndexOf() {
    delegate.add("a");
    delegate.add("b");
    delegate.add("a");

    assertThat(list.indexOf("a")).isEqualTo(0);
    assertThat(list.indexOf("b")).isEqualTo(1);
    assertThat(list.indexOf("z")).isEqualTo(-1);
  }

  @Test
  public void testLastIndexOf() {
    delegate.add("a");
    delegate.add("b");
    delegate.add("a");

    assertThat(list.lastIndexOf("a")).isEqualTo(2);
    assertThat(list.lastIndexOf("z")).isEqualTo(-1);
  }

  @Test
  public void testToArray() {
    delegate.add("a");
    delegate.add("b");

    Object[] array = list.toArray();

    assertThat(array).asList().containsExactly("a", "b").inOrder();
  }

  @Test
  public void testToArrayTyped() {
    delegate.add("a");
    delegate.add("b");

    String[] array = list.toArray(new String[0]);

    assertThat(array).asList().containsExactly("a", "b").inOrder();
  }

  @Test
  public void testEquals() {
    delegate.add("a");
    delegate.add("b");

    ArrayList<String> other = new ArrayList<>(Arrays.asList("a", "b"));
    assertThat(list.equals(other)).isTrue();

    other.add("c");
    assertThat(list.equals(other)).isFalse();
  }

  @Test
  public void testHashCode() {
    delegate.add("a");
    delegate.add("b");

    ArrayList<String> other = new ArrayList<>(Arrays.asList("a", "b"));
    assertThat(list.hashCode()).isEqualTo(other.hashCode());
  }

  @Test
  public void testAddNullViaListAddThrows() {
    assertThrows(NullPointerException.class, () -> list.add(null));
  }

  @Test
  public void testSetNullMessageContainsNonNull() {
    delegate.add("a");

    NullPointerException e = assertThrows(NullPointerException.class, () -> list.set(0, null));
    assertThat(e).hasMessageThat().isEqualTo("Element must be non-null");
  }

  @Test
  public void testRemoveAllNoChange() {
    delegate.add("a");

    boolean changed = list.removeAll(Collections.emptyList());

    assertThat(changed).isFalse();
  }

  @Test
  public void testRetainAllNoChange() {
    delegate.add("a");

    boolean changed = list.retainAll(Collections.singletonList("a"));

    assertThat(changed).isFalse();
  }
}
