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
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;

public class NonNullElementWrapperListTest {

  @Test
  public void testConstructor() {
    ArrayList<String> delegate = new ArrayList<>();
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    assertThat(list).isNotNull();
    assertThat(list.size()).isEqualTo(0);
  }

  @Test
  public void testConstructorWithNull() {
    assertThrows(NullPointerException.class, () -> {
      new NonNullElementWrapperList<String>(null);
    });
  }

  @Test
  public void testGet() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("first");
    delegate.add("second");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    assertThat(list.get(0)).isEqualTo("first");
    assertThat(list.get(1)).isEqualTo("second");
  }

  @Test
  public void testSize() {
    ArrayList<String> delegate = new ArrayList<>();
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    assertThat(list.size()).isEqualTo(0);

    delegate.add("element");
    assertThat(list.size()).isEqualTo(1);
  }

  @Test
  public void testSetWithNonNullElement() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("original");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    String old = list.set(0, "updated");

    assertThat(old).isEqualTo("original");
    assertThat(list.get(0)).isEqualTo("updated");
  }

  @Test
  public void testSetWithNullElement() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("original");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      list.set(0, null);
    });
    assertThat(exception.getMessage()).isEqualTo("Element must be non-null");
  }

  @Test
  public void testAddWithNonNullElement() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("first");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    list.add(0, "inserted");

    assertThat(list.size()).isEqualTo(2);
    assertThat(list.get(0)).isEqualTo("inserted");
    assertThat(list.get(1)).isEqualTo("first");
  }

  @Test
  public void testAddWithNullElement() {
    ArrayList<String> delegate = new ArrayList<>();
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      list.add(0, null);
    });
    assertThat(exception.getMessage()).isEqualTo("Element must be non-null");
  }

  @Test
  public void testRemove() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("first");
    delegate.add("second");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    String removed = list.remove(0);

    assertThat(removed).isEqualTo("first");
    assertThat(list.size()).isEqualTo(1);
    assertThat(list.get(0)).isEqualTo("second");
  }

  @Test
  public void testClear() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("first");
    delegate.add("second");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    list.clear();

    assertThat(list.size()).isEqualTo(0);
  }

  @Test
  public void testRemoveObject() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("first");
    delegate.add("second");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    boolean removed = list.remove("first");

    assertThat(removed).isTrue();
    assertThat(list.size()).isEqualTo(1);
    assertThat(list.get(0)).isEqualTo("second");
  }

  @Test
  public void testRemoveObjectNotFound() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("first");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    boolean removed = list.remove("notfound");

    assertThat(removed).isFalse();
  }

  @Test
  public void testRemoveAll() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("first");
    delegate.add("second");
    delegate.add("third");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    boolean removed = list.removeAll(Arrays.asList("first", "third"));

    assertThat(removed).isTrue();
    assertThat(list.size()).isEqualTo(1);
    assertThat(list.get(0)).isEqualTo("second");
  }

  @Test
  public void testRetainAll() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("first");
    delegate.add("second");
    delegate.add("third");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    boolean retained = list.retainAll(Arrays.asList("first", "third"));

    assertThat(retained).isTrue();
    assertThat(list.size()).isEqualTo(2);
    assertThat(list.get(0)).isEqualTo("first");
    assertThat(list.get(1)).isEqualTo("third");
  }

  @Test
  public void testContains() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("first");
    delegate.add("second");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    assertThat(list.contains("first")).isTrue();
    assertThat(list.contains("notfound")).isFalse();
  }

  @Test
  public void testContainsNull() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("first");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    assertThat(list.contains(null)).isFalse();
  }

  @Test
  public void testIndexOf() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("first");
    delegate.add("second");
    delegate.add("first");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    assertThat(list.indexOf("first")).isEqualTo(0);
    assertThat(list.indexOf("second")).isEqualTo(1);
    assertThat(list.indexOf("notfound")).isEqualTo(-1);
  }

  @Test
  public void testLastIndexOf() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("first");
    delegate.add("second");
    delegate.add("first");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    assertThat(list.lastIndexOf("first")).isEqualTo(2);
    assertThat(list.lastIndexOf("second")).isEqualTo(1);
    assertThat(list.lastIndexOf("notfound")).isEqualTo(-1);
  }

  @Test
  public void testToArray() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("first");
    delegate.add("second");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    Object[] array = list.toArray();

    assertThat(array).hasLength(2);
    assertThat(array[0]).isEqualTo("first");
    assertThat(array[1]).isEqualTo("second");
  }

  @Test
  public void testToArrayWithType() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("first");
    delegate.add("second");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    String[] array = list.toArray(new String[0]);

    assertThat(array).hasLength(2);
    assertThat(array[0]).isEqualTo("first");
    assertThat(array[1]).isEqualTo("second");
  }

  @Test
  public void testEquals() {
    ArrayList<String> delegate1 = new ArrayList<>();
    delegate1.add("first");
    delegate1.add("second");
    NonNullElementWrapperList<String> list1 = new NonNullElementWrapperList<>(delegate1);

    ArrayList<String> delegate2 = new ArrayList<>();
    delegate2.add("first");
    delegate2.add("second");

    assertThat(list1.equals(delegate2)).isTrue();
  }

  @Test
  public void testHashCode() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("first");
    delegate.add("second");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    assertThat(list.hashCode()).isEqualTo(delegate.hashCode());
  }
}
