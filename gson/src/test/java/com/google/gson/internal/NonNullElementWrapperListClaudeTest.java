/*
 * Copyright (C) 2018 Google Inc.
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
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;
import org.junit.Test;

/**
 * Tests for {@link NonNullElementWrapperList}.
 *
 * @author Claude
 */
public class NonNullElementWrapperListClaudeTest {

  // ==========================================================================
  // Tests for constructor
  // ==========================================================================

  @Test
  public void testConstructorWithEmptyList() {
    ArrayList<String> delegate = new ArrayList<>();
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    assertThat(list).isNotNull();
    assertThat(list).isEmpty();
  }

  @Test
  public void testConstructorWithPopulatedList() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("one");
    delegate.add("two");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    assertThat(list).containsExactly("one", "two").inOrder();
  }

  @Test
  public void testConstructorWithNullDelegateThrows() {
    assertThrows(NullPointerException.class, () -> new NonNullElementWrapperList<>(null));
  }

  @Test
  public void testImplementsRandomAccess() {
    ArrayList<String> delegate = new ArrayList<>();
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    assertThat(list).isInstanceOf(RandomAccess.class);
  }

  // ==========================================================================
  // Tests for get(int index)
  // ==========================================================================

  @Test
  public void testGetValidIndex() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("first");
    delegate.add("second");
    delegate.add("third");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    assertThat(list.get(0)).isEqualTo("first");
    assertThat(list.get(1)).isEqualTo("second");
    assertThat(list.get(2)).isEqualTo("third");
  }

  @Test
  public void testGetNegativeIndexThrows() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("element");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
  }

  @Test
  public void testGetIndexOutOfBoundsThrows() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("element");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    assertThrows(IndexOutOfBoundsException.class, () -> list.get(100));
  }

  @Test
  public void testGetOnEmptyListThrows() {
    ArrayList<String> delegate = new ArrayList<>();
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
  }

  // ==========================================================================
  // Tests for size()
  // ==========================================================================

  @Test
  public void testSizeOnEmptyList() {
    ArrayList<String> delegate = new ArrayList<>();
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    assertThat(list.size()).isEqualTo(0);
  }

  @Test
  public void testSizeWithElements() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("one");
    delegate.add("two");
    delegate.add("three");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    assertThat(list.size()).isEqualTo(3);
  }

  @Test
  public void testSizeReflectsDelegateChanges() {
    ArrayList<String> delegate = new ArrayList<>();
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    assertThat(list.size()).isEqualTo(0);

    list.add("element");
    assertThat(list.size()).isEqualTo(1);

    list.add("another");
    assertThat(list.size()).isEqualTo(2);

    var unused = list.remove(0);
    assertThat(list.size()).isEqualTo(1);
  }

  // ==========================================================================
  // Tests for set(int index, E element)
  // ==========================================================================

  @Test
  public void testSetValidElement() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("old");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    String oldValue = list.set(0, "new");

    assertThat(oldValue).isEqualTo("old");
    assertThat(list.get(0)).isEqualTo("new");
  }

  @Test
  public void testSetNullElementThrows() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("element");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    NullPointerException e = assertThrows(NullPointerException.class, () -> list.set(0, null));
    assertThat(e).hasMessageThat().isEqualTo("Element must be non-null");
  }

  @Test
  public void testSetInvalidIndexThrows() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("element");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, "value"));
    assertThrows(IndexOutOfBoundsException.class, () -> list.set(1, "value"));
  }

  @Test
  public void testSetMultiplePositions() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("a");
    delegate.add("b");
    delegate.add("c");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    var unused1 = list.set(0, "x");
    var unused2 = list.set(2, "z");

    assertThat(list.get(0)).isEqualTo("x");
    assertThat(list.get(1)).isEqualTo("b");
    assertThat(list.get(2)).isEqualTo("z");
  }

  // ==========================================================================
  // Tests for add(int index, E element)
  // ==========================================================================

  @Test
  public void testAddAtBeginning() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("existing");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    list.add(0, "new");

    assertThat(list).containsExactly("new", "existing").inOrder();
  }

  @Test
  public void testAddAtEnd() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("existing");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    list.add(1, "new");

    assertThat(list).containsExactly("existing", "new").inOrder();
  }

  @Test
  public void testAddInMiddle() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("first");
    delegate.add("third");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    list.add(1, "second");

    assertThat(list).containsExactly("first", "second", "third").inOrder();
  }

  @Test
  public void testAddNullElementThrows() {
    ArrayList<String> delegate = new ArrayList<>();
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    NullPointerException e = assertThrows(NullPointerException.class, () -> list.add(0, null));
    assertThat(e).hasMessageThat().isEqualTo("Element must be non-null");
  }

  @Test
  public void testAddInvalidIndexThrows() {
    ArrayList<String> delegate = new ArrayList<>();
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, "value"));
    assertThrows(IndexOutOfBoundsException.class, () -> list.add(1, "value"));
  }

  @Test
  public void testAddToEmptyList() {
    ArrayList<String> delegate = new ArrayList<>();
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    list.add(0, "first");

    assertThat(list).containsExactly("first");
  }

  // ==========================================================================
  // Tests for remove(int index)
  // ==========================================================================

  @Test
  public void testRemoveByIndex() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("first");
    delegate.add("second");
    delegate.add("third");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    String removed = list.remove(1);

    assertThat(removed).isEqualTo("second");
    assertThat(list).containsExactly("first", "third").inOrder();
  }

  @Test
  public void testRemoveFirstElement() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("first");
    delegate.add("second");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    String removed = list.remove(0);

    assertThat(removed).isEqualTo("first");
    assertThat(list).containsExactly("second");
  }

  @Test
  public void testRemoveLastElement() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("first");
    delegate.add("second");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    String removed = list.remove(1);

    assertThat(removed).isEqualTo("second");
    assertThat(list).containsExactly("first");
  }

  @Test
  public void testRemoveInvalidIndexThrows() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("element");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
    assertThrows(IndexOutOfBoundsException.class, () -> list.remove(1));
  }

  @Test
  public void testRemoveOnEmptyListThrows() {
    ArrayList<String> delegate = new ArrayList<>();
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
  }

  // ==========================================================================
  // Tests for clear()
  // ==========================================================================

  @Test
  public void testClearOnEmptyList() {
    ArrayList<String> delegate = new ArrayList<>();
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    list.clear();

    assertThat(list).isEmpty();
  }

  @Test
  public void testClearOnPopulatedList() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("one");
    delegate.add("two");
    delegate.add("three");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    list.clear();

    assertThat(list).isEmpty();
    assertThat(list.size()).isEqualTo(0);
  }

  @Test
  public void testClearAffectsDelegate() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("element");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    list.clear();

    assertThat(delegate).isEmpty();
  }

  @Test
  public void testListUsableAfterClear() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("old");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    list.clear();
    list.add("new");

    assertThat(list).containsExactly("new");
  }

  // ==========================================================================
  // Tests for remove(Object o)
  // ==========================================================================

  @Test
  public void testRemoveByObjectExisting() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("one");
    delegate.add("two");
    delegate.add("three");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    boolean removed = list.remove("two");

    assertThat(removed).isTrue();
    assertThat(list).containsExactly("one", "three").inOrder();
  }

  @Test
  public void testRemoveByObjectNonExisting() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("one");
    delegate.add("two");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    boolean removed = list.remove("three");

    assertThat(removed).isFalse();
    assertThat(list).containsExactly("one", "two").inOrder();
  }

  @Test
  public void testRemoveByObjectNull() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("one");
    delegate.add("two");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    // Null removal should not throw, just return false (since list cannot contain null)
    boolean removed = list.remove(null);

    assertThat(removed).isFalse();
    assertThat(list).containsExactly("one", "two").inOrder();
  }

  @Test
  public void testRemoveByObjectFirstOccurrence() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("dup");
    delegate.add("other");
    delegate.add("dup");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    boolean removed = list.remove("dup");

    assertThat(removed).isTrue();
    assertThat(list).containsExactly("other", "dup").inOrder();
  }

  // ==========================================================================
  // Tests for removeAll(Collection<?> c)
  // ==========================================================================

  @Test
  public void testRemoveAllWithMatchingElements() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("one");
    delegate.add("two");
    delegate.add("three");
    delegate.add("four");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    boolean modified = list.removeAll(Arrays.asList("two", "four"));

    assertThat(modified).isTrue();
    assertThat(list).containsExactly("one", "three").inOrder();
  }

  @Test
  public void testRemoveAllWithNoMatchingElements() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("one");
    delegate.add("two");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    boolean modified = list.removeAll(Arrays.asList("three", "four"));

    assertThat(modified).isFalse();
    assertThat(list).containsExactly("one", "two").inOrder();
  }

  @Test
  public void testRemoveAllWithEmptyCollection() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("one");
    delegate.add("two");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    boolean modified = list.removeAll(Collections.emptyList());

    assertThat(modified).isFalse();
    assertThat(list).containsExactly("one", "two").inOrder();
  }

  @Test
  public void testRemoveAllFromEmptyList() {
    ArrayList<String> delegate = new ArrayList<>();
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    boolean modified = list.removeAll(Arrays.asList("one", "two"));

    assertThat(modified).isFalse();
    assertThat(list).isEmpty();
  }

  @Test
  public void testRemoveAllRemovesAllOccurrences() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("dup");
    delegate.add("other");
    delegate.add("dup");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    boolean modified = list.removeAll(Collections.singletonList("dup"));

    assertThat(modified).isTrue();
    assertThat(list).containsExactly("other");
  }

  // ==========================================================================
  // Tests for retainAll(Collection<?> c)
  // ==========================================================================

  @Test
  public void testRetainAllWithMatchingElements() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("one");
    delegate.add("two");
    delegate.add("three");
    delegate.add("four");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    boolean modified = list.retainAll(Arrays.asList("one", "three"));

    assertThat(modified).isTrue();
    assertThat(list).containsExactly("one", "three").inOrder();
  }

  @Test
  public void testRetainAllWithNoMatchingElements() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("one");
    delegate.add("two");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    boolean modified = list.retainAll(Arrays.asList("three", "four"));

    assertThat(modified).isTrue();
    assertThat(list).isEmpty();
  }

  @Test
  public void testRetainAllWithEmptyCollection() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("one");
    delegate.add("two");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    boolean modified = list.retainAll(Collections.emptyList());

    assertThat(modified).isTrue();
    assertThat(list).isEmpty();
  }

  @Test
  public void testRetainAllOnEmptyList() {
    ArrayList<String> delegate = new ArrayList<>();
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    boolean modified = list.retainAll(Arrays.asList("one", "two"));

    assertThat(modified).isFalse();
    assertThat(list).isEmpty();
  }

  @Test
  public void testRetainAllWithAllElementsRetained() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("one");
    delegate.add("two");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    boolean modified = list.retainAll(Arrays.asList("one", "two", "three"));

    assertThat(modified).isFalse();
    assertThat(list).containsExactly("one", "two").inOrder();
  }

  // ==========================================================================
  // Tests for contains(Object o)
  // ==========================================================================

  @Test
  public void testContainsExistingElement() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("one");
    delegate.add("two");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    assertThat(list.contains("one")).isTrue();
    assertThat(list.contains("two")).isTrue();
  }

  @Test
  public void testContainsNonExistingElement() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("one");
    delegate.add("two");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    assertThat(list.contains("three")).isFalse();
  }

  @Test
  public void testContainsNull() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("one");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    // contains(null) should not throw, just return false
    assertThat(list.contains(null)).isFalse();
  }

  @Test
  public void testContainsOnEmptyList() {
    ArrayList<String> delegate = new ArrayList<>();
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    assertThat(list.contains("anything")).isFalse();
  }

  // ==========================================================================
  // Tests for indexOf(Object o)
  // ==========================================================================

  @Test
  public void testIndexOfExistingElement() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("first");
    delegate.add("second");
    delegate.add("third");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    assertThat(list.indexOf("first")).isEqualTo(0);
    assertThat(list.indexOf("second")).isEqualTo(1);
    assertThat(list.indexOf("third")).isEqualTo(2);
  }

  @Test
  public void testIndexOfNonExistingElement() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("one");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    assertThat(list.indexOf("two")).isEqualTo(-1);
  }

  @Test
  public void testIndexOfNull() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("one");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    // indexOf(null) should not throw, just return -1
    assertThat(list.indexOf(null)).isEqualTo(-1);
  }

  @Test
  public void testIndexOfFirstOccurrence() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("dup");
    delegate.add("other");
    delegate.add("dup");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    assertThat(list.indexOf("dup")).isEqualTo(0);
  }

  @Test
  public void testIndexOfOnEmptyList() {
    ArrayList<String> delegate = new ArrayList<>();
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    assertThat(list.indexOf("anything")).isEqualTo(-1);
  }

  // ==========================================================================
  // Tests for lastIndexOf(Object o)
  // ==========================================================================

  @Test
  public void testLastIndexOfExistingElement() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("first");
    delegate.add("second");
    delegate.add("third");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    assertThat(list.lastIndexOf("first")).isEqualTo(0);
    assertThat(list.lastIndexOf("second")).isEqualTo(1);
    assertThat(list.lastIndexOf("third")).isEqualTo(2);
  }

  @Test
  public void testLastIndexOfNonExistingElement() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("one");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    assertThat(list.lastIndexOf("two")).isEqualTo(-1);
  }

  @Test
  public void testLastIndexOfNull() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("one");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    // lastIndexOf(null) should not throw, just return -1
    assertThat(list.lastIndexOf(null)).isEqualTo(-1);
  }

  @Test
  public void testLastIndexOfLastOccurrence() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("dup");
    delegate.add("other");
    delegate.add("dup");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    assertThat(list.lastIndexOf("dup")).isEqualTo(2);
  }

  @Test
  public void testLastIndexOfOnEmptyList() {
    ArrayList<String> delegate = new ArrayList<>();
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    assertThat(list.lastIndexOf("anything")).isEqualTo(-1);
  }

  // ==========================================================================
  // Tests for toArray()
  // ==========================================================================

  @Test
  public void testToArrayOnEmptyList() {
    ArrayList<String> delegate = new ArrayList<>();
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    Object[] array = list.toArray();

    assertThat(array).isEmpty();
  }

  @Test
  public void testToArrayOnPopulatedList() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("one");
    delegate.add("two");
    delegate.add("three");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    Object[] array = list.toArray();

    assertThat(array).asList().containsExactly("one", "two", "three").inOrder();
  }

  @Test
  public void testToArrayReturnsNewArray() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("element");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    Object[] array1 = list.toArray();
    Object[] array2 = list.toArray();

    assertThat(array1).isNotSameInstanceAs(array2);
  }

  // ==========================================================================
  // Tests for toArray(T[] a)
  // ==========================================================================

  @Test
  public void testToArrayWithSufficientlyLargeArray() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("one");
    delegate.add("two");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    String[] result = list.toArray(new String[5]);

    assertThat(result[0]).isEqualTo("one");
    assertThat(result[1]).isEqualTo("two");
    assertThat(result[2]).isNull(); // Marker element
  }

  @Test
  public void testToArrayWithExactSizeArray() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("one");
    delegate.add("two");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    String[] input = new String[2];
    String[] result = list.toArray(input);

    assertThat(result).isSameInstanceAs(input);
    assertThat(result).asList().containsExactly("one", "two").inOrder();
  }

  @Test
  public void testToArrayWithSmallerArray() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("one");
    delegate.add("two");
    delegate.add("three");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    String[] result = list.toArray(new String[1]);

    assertThat(result.length).isEqualTo(3);
    assertThat(result).asList().containsExactly("one", "two", "three").inOrder();
  }

  @Test
  public void testToArrayWithEmptyInputArray() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("one");
    delegate.add("two");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    String[] result = list.toArray(new String[0]);

    assertThat(result.length).isEqualTo(2);
    assertThat(result).asList().containsExactly("one", "two").inOrder();
  }

  // ==========================================================================
  // Tests for equals(Object o)
  // ==========================================================================

  @Test
  public void testEqualsWithEqualList() {
    ArrayList<String> delegate1 = new ArrayList<>();
    delegate1.add("one");
    delegate1.add("two");
    NonNullElementWrapperList<String> list1 = new NonNullElementWrapperList<>(delegate1);

    ArrayList<String> delegate2 = new ArrayList<>();
    delegate2.add("one");
    delegate2.add("two");
    NonNullElementWrapperList<String> list2 = new NonNullElementWrapperList<>(delegate2);

    assertThat(list1.equals(list2)).isTrue();
  }

  @Test
  public void testEqualsWithDifferentElements() {
    ArrayList<String> delegate1 = new ArrayList<>();
    delegate1.add("one");
    delegate1.add("two");
    NonNullElementWrapperList<String> list1 = new NonNullElementWrapperList<>(delegate1);

    ArrayList<String> delegate2 = new ArrayList<>();
    delegate2.add("one");
    delegate2.add("three");
    NonNullElementWrapperList<String> list2 = new NonNullElementWrapperList<>(delegate2);

    assertThat(list1.equals(list2)).isFalse();
  }

  @Test
  public void testEqualsWithDifferentSize() {
    ArrayList<String> delegate1 = new ArrayList<>();
    delegate1.add("one");
    delegate1.add("two");
    NonNullElementWrapperList<String> list1 = new NonNullElementWrapperList<>(delegate1);

    ArrayList<String> delegate2 = new ArrayList<>();
    delegate2.add("one");
    NonNullElementWrapperList<String> list2 = new NonNullElementWrapperList<>(delegate2);

    assertThat(list1.equals(list2)).isFalse();
  }

  @Test
  public void testEqualsWithRegularArrayList() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("one");
    delegate.add("two");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    ArrayList<String> regularList = new ArrayList<>();
    regularList.add("one");
    regularList.add("two");

    // NonNullElementWrapperList delegates to ArrayList.equals which compares content
    assertThat(list.equals(regularList)).isTrue();
  }

  @Test
  public void testEqualsWithNull() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("one");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    assertThat(list.equals(null)).isFalse();
  }

  @Test
  public void testEqualsWithSelf() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("one");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    assertThat(list.equals(list)).isTrue();
  }

  @Test
  public void testEqualsEmptyLists() {
    ArrayList<String> delegate1 = new ArrayList<>();
    NonNullElementWrapperList<String> list1 = new NonNullElementWrapperList<>(delegate1);

    ArrayList<String> delegate2 = new ArrayList<>();
    NonNullElementWrapperList<String> list2 = new NonNullElementWrapperList<>(delegate2);

    assertThat(list1.equals(list2)).isTrue();
  }

  @Test
  public void testEqualsWithDifferentOrder() {
    ArrayList<String> delegate1 = new ArrayList<>();
    delegate1.add("one");
    delegate1.add("two");
    NonNullElementWrapperList<String> list1 = new NonNullElementWrapperList<>(delegate1);

    ArrayList<String> delegate2 = new ArrayList<>();
    delegate2.add("two");
    delegate2.add("one");
    NonNullElementWrapperList<String> list2 = new NonNullElementWrapperList<>(delegate2);

    assertThat(list1.equals(list2)).isFalse();
  }

  // ==========================================================================
  // Tests for hashCode()
  // ==========================================================================

  @Test
  public void testHashCodeConsistency() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("one");
    delegate.add("two");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    int hash1 = list.hashCode();
    int hash2 = list.hashCode();

    assertThat(hash1).isEqualTo(hash2);
  }

  @Test
  public void testHashCodeEqualListsHaveSameHash() {
    ArrayList<String> delegate1 = new ArrayList<>();
    delegate1.add("one");
    delegate1.add("two");
    NonNullElementWrapperList<String> list1 = new NonNullElementWrapperList<>(delegate1);

    ArrayList<String> delegate2 = new ArrayList<>();
    delegate2.add("one");
    delegate2.add("two");
    NonNullElementWrapperList<String> list2 = new NonNullElementWrapperList<>(delegate2);

    assertThat(list1.hashCode()).isEqualTo(list2.hashCode());
  }

  @Test
  public void testHashCodeEmptyList() {
    ArrayList<String> delegate = new ArrayList<>();
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    // Empty ArrayList has hash code 1
    assertThat(list.hashCode()).isEqualTo(1);
  }

  @Test
  public void testHashCodeMatchesDelegateHashCode() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("one");
    delegate.add("two");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    assertThat(list.hashCode()).isEqualTo(delegate.hashCode());
  }

  // ==========================================================================
  // Integration tests - wrapper behavior and live view
  // ==========================================================================

  @Test
  public void testWrapperReflectsDelegateChangesDirectly() {
    ArrayList<String> delegate = new ArrayList<>();
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    // Modify delegate directly
    delegate.add("direct");

    assertThat(list).containsExactly("direct");
    assertThat(list.size()).isEqualTo(1);
  }

  @Test
  public void testWrapperChangesAffectDelegate() {
    ArrayList<String> delegate = new ArrayList<>();
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    list.add("via-wrapper");

    assertThat(delegate).containsExactly("via-wrapper");
  }

  @Test
  public void testNullCannotBeAddedViaAppend() {
    ArrayList<String> delegate = new ArrayList<>();
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    // Using the add(E) method inherited from AbstractList which calls add(size(), element)
    assertThrows(NullPointerException.class, () -> list.add(null));
  }

  @Test
  public void testAddAllNullCollection() {
    ArrayList<String> delegate = new ArrayList<>();
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    Collection<String> toAdd = Arrays.asList("one", null, "three");

    // AbstractList.addAll calls add(int, E) which should throw for null
    assertThrows(NullPointerException.class, () -> list.addAll(toAdd));
  }

  @Test
  public void testAddAllValidCollection() {
    ArrayList<String> delegate = new ArrayList<>();
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    boolean modified = list.addAll(Arrays.asList("one", "two", "three"));

    assertThat(modified).isTrue();
    assertThat(list).containsExactly("one", "two", "three").inOrder();
  }

  @Test
  public void testIteratorAccess() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("one");
    delegate.add("two");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    List<String> collected = new ArrayList<>();
    for (String s : list) {
      collected.add(s);
    }

    assertThat(collected).containsExactly("one", "two").inOrder();
  }

  @Test
  public void testSubListNotNull() {
    ArrayList<String> delegate = new ArrayList<>();
    delegate.add("one");
    delegate.add("two");
    delegate.add("three");
    NonNullElementWrapperList<String> list = new NonNullElementWrapperList<>(delegate);

    List<String> subList = list.subList(0, 2);

    assertThat(subList).containsExactly("one", "two").inOrder();
  }

  @Test
  public void testIntegerElementType() {
    ArrayList<Integer> delegate = new ArrayList<>();
    delegate.add(1);
    delegate.add(2);
    delegate.add(3);
    NonNullElementWrapperList<Integer> list = new NonNullElementWrapperList<>(delegate);

    assertThat(list).containsExactly(1, 2, 3).inOrder();
    assertThat(list.indexOf(2)).isEqualTo(1);
    assertThrows(NullPointerException.class, () -> list.add(null));
  }
}
