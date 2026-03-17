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

import java.util.AbstractMap;
import java.util.Map;
import org.junit.Test;

public class LinkedTreeMapNodeTest {

  @Test
  public void testHeaderNodeConstructor() {
    LinkedTreeMap.Node<String, Integer> header = new LinkedTreeMap.Node<String, Integer>(true);
    assertThat(header.key).isNull();
    assertThat(header.allowNullValue).isTrue();
    assertThat(header.next).isSameInstanceAs(header);
    assertThat(header.prev).isSameInstanceAs(header);
  }

  @Test
  public void testHeaderNodeConstructorWithFalseAllowNull() {
    LinkedTreeMap.Node<String, Integer> header = new LinkedTreeMap.Node<String, Integer>(false);
    assertThat(header.key).isNull();
    assertThat(header.allowNullValue).isFalse();
    assertThat(header.next).isSameInstanceAs(header);
    assertThat(header.prev).isSameInstanceAs(header);
  }

  @Test
  public void testRegularNodeConstructor() {
    LinkedTreeMap.Node<String, Integer> header = new LinkedTreeMap.Node<String, Integer>(true);
    LinkedTreeMap.Node<String, Integer> parent = new LinkedTreeMap.Node<String, Integer>(true);

    LinkedTreeMap.Node<String, Integer> node =
        new LinkedTreeMap.Node<String, Integer>(true, parent, "key1", header, header.prev);

    assertThat(node.parent).isSameInstanceAs(parent);
    assertThat(node.key).isEqualTo("key1");
    assertThat(node.allowNullValue).isTrue();
    assertThat(node.height).isEqualTo(1);
    assertThat(node.next).isSameInstanceAs(header);
    assertThat(node.prev).isSameInstanceAs(header);
    assertThat(header.prev).isSameInstanceAs(node);
    assertThat(header.next).isSameInstanceAs(node);
  }

  @Test
  public void testRegularNodeConstructorWithFalseAllowNull() {
    LinkedTreeMap.Node<String, Integer> header = new LinkedTreeMap.Node<String, Integer>(false);
    LinkedTreeMap.Node<String, Integer> parent = new LinkedTreeMap.Node<String, Integer>(false);

    LinkedTreeMap.Node<String, Integer> node =
        new LinkedTreeMap.Node<String, Integer>(false, parent, "key2", header, header.prev);

    assertThat(node.allowNullValue).isFalse();
    assertThat(node.key).isEqualTo("key2");
    assertThat(node.height).isEqualTo(1);
  }

  @Test
  public void testGetKey() {
    LinkedTreeMap.Node<String, Integer> header = new LinkedTreeMap.Node<String, Integer>(true);
    LinkedTreeMap.Node<String, Integer> parent = new LinkedTreeMap.Node<String, Integer>(true);
    LinkedTreeMap.Node<String, Integer> node =
        new LinkedTreeMap.Node<String, Integer>(true, parent, "testKey", header, header.prev);

    assertThat(node.getKey()).isEqualTo("testKey");
  }

  @Test
  public void testGetKeyNull() {
    LinkedTreeMap.Node<String, Integer> header = new LinkedTreeMap.Node<String, Integer>(true);
    assertThat(header.getKey()).isNull();
  }

  @Test
  public void testGetValue() {
    LinkedTreeMap.Node<String, Integer> header = new LinkedTreeMap.Node<String, Integer>(true);
    LinkedTreeMap.Node<String, Integer> parent = new LinkedTreeMap.Node<String, Integer>(true);
    LinkedTreeMap.Node<String, Integer> node =
        new LinkedTreeMap.Node<String, Integer>(true, parent, "key", header, header.prev);

    node.value = 42;
    assertThat(node.getValue()).isEqualTo(42);
  }

  @Test
  public void testGetValueNull() {
    LinkedTreeMap.Node<String, Integer> header = new LinkedTreeMap.Node<String, Integer>(true);
    LinkedTreeMap.Node<String, Integer> parent = new LinkedTreeMap.Node<String, Integer>(true);
    LinkedTreeMap.Node<String, Integer> node =
        new LinkedTreeMap.Node<String, Integer>(true, parent, "key", header, header.prev);

    assertThat(node.getValue()).isNull();
  }

  @Test
  public void testSetValue() {
    LinkedTreeMap.Node<String, Integer> header = new LinkedTreeMap.Node<String, Integer>(true);
    LinkedTreeMap.Node<String, Integer> parent = new LinkedTreeMap.Node<String, Integer>(true);
    LinkedTreeMap.Node<String, Integer> node =
        new LinkedTreeMap.Node<String, Integer>(true, parent, "key", header, header.prev);

    Integer oldValue = node.setValue(100);
    assertThat(oldValue).isNull();
    assertThat(node.value).isEqualTo(100);

    Integer oldValue2 = node.setValue(200);
    assertThat(oldValue2).isEqualTo(100);
    assertThat(node.value).isEqualTo(200);
  }

  @Test
  public void testSetValueNullWhenAllowed() {
    LinkedTreeMap.Node<String, Integer> header = new LinkedTreeMap.Node<String, Integer>(true);
    LinkedTreeMap.Node<String, Integer> parent = new LinkedTreeMap.Node<String, Integer>(true);
    LinkedTreeMap.Node<String, Integer> node =
        new LinkedTreeMap.Node<String, Integer>(true, parent, "key", header, header.prev);

    node.value = 100;
    Integer oldValue = node.setValue(null);
    assertThat(oldValue).isEqualTo(100);
    assertThat(node.value).isNull();
  }

  @Test
  public void testSetValueNullWhenNotAllowed() {
    LinkedTreeMap.Node<String, Integer> header = new LinkedTreeMap.Node<String, Integer>(false);
    LinkedTreeMap.Node<String, Integer> parent = new LinkedTreeMap.Node<String, Integer>(false);
    LinkedTreeMap.Node<String, Integer> node =
        new LinkedTreeMap.Node<String, Integer>(false, parent, "key", header, header.prev);

    try {
      node.setValue(null);
      throw new AssertionError("Expected NullPointerException");
    } catch (NullPointerException expected) {
      assertThat(expected.getMessage()).isEqualTo("value == null");
    }
  }

  @Test
  public void testEqualsWithSameNode() {
    LinkedTreeMap.Node<String, Integer> header = new LinkedTreeMap.Node<String, Integer>(true);
    LinkedTreeMap.Node<String, Integer> parent = new LinkedTreeMap.Node<String, Integer>(true);
    LinkedTreeMap.Node<String, Integer> node =
        new LinkedTreeMap.Node<String, Integer>(true, parent, "key", header, header.prev);
    node.value = 42;

    assertThat(node.equals(node)).isTrue();
  }

  @Test
  public void testEqualsWithEqualNode() {
    LinkedTreeMap.Node<String, Integer> header = new LinkedTreeMap.Node<String, Integer>(true);
    LinkedTreeMap.Node<String, Integer> parent = new LinkedTreeMap.Node<String, Integer>(true);
    LinkedTreeMap.Node<String, Integer> node1 =
        new LinkedTreeMap.Node<String, Integer>(true, parent, "key", header, header.prev);
    node1.value = 42;

    LinkedTreeMap.Node<String, Integer> node2 =
        new LinkedTreeMap.Node<String, Integer>(true, parent, "key", header, header.prev);
    node2.value = 42;

    assertThat(node1.equals(node2)).isTrue();
  }

  @Test
  public void testEqualsWithDifferentKey() {
    LinkedTreeMap.Node<String, Integer> header = new LinkedTreeMap.Node<String, Integer>(true);
    LinkedTreeMap.Node<String, Integer> parent = new LinkedTreeMap.Node<String, Integer>(true);
    LinkedTreeMap.Node<String, Integer> node1 =
        new LinkedTreeMap.Node<String, Integer>(true, parent, "key1", header, header.prev);
    node1.value = 42;

    LinkedTreeMap.Node<String, Integer> node2 =
        new LinkedTreeMap.Node<String, Integer>(true, parent, "key2", header, header.prev);
    node2.value = 42;

    assertThat(node1.equals(node2)).isFalse();
  }

  @Test
  public void testEqualsWithDifferentValue() {
    LinkedTreeMap.Node<String, Integer> header = new LinkedTreeMap.Node<String, Integer>(true);
    LinkedTreeMap.Node<String, Integer> parent = new LinkedTreeMap.Node<String, Integer>(true);
    LinkedTreeMap.Node<String, Integer> node1 =
        new LinkedTreeMap.Node<String, Integer>(true, parent, "key", header, header.prev);
    node1.value = 42;

    LinkedTreeMap.Node<String, Integer> node2 =
        new LinkedTreeMap.Node<String, Integer>(true, parent, "key", header, header.prev);
    node2.value = 43;

    assertThat(node1.equals(node2)).isFalse();
  }

  @Test
  public void testEqualsWithNullKeys() {
    LinkedTreeMap.Node<String, Integer> node1 = new LinkedTreeMap.Node<String, Integer>(true);
    node1.value = 42;

    LinkedTreeMap.Node<String, Integer> node2 = new LinkedTreeMap.Node<String, Integer>(true);
    node2.value = 42;

    assertThat(node1.equals(node2)).isTrue();
  }

  @Test
  public void testEqualsWithNullValues() {
    LinkedTreeMap.Node<String, Integer> header = new LinkedTreeMap.Node<String, Integer>(true);
    LinkedTreeMap.Node<String, Integer> parent = new LinkedTreeMap.Node<String, Integer>(true);
    LinkedTreeMap.Node<String, Integer> node1 =
        new LinkedTreeMap.Node<String, Integer>(true, parent, "key", header, header.prev);

    LinkedTreeMap.Node<String, Integer> node2 =
        new LinkedTreeMap.Node<String, Integer>(true, parent, "key", header, header.prev);

    assertThat(node1.equals(node2)).isTrue();
  }

  @Test
  public void testEqualsWithMapEntry() {
    LinkedTreeMap.Node<String, Integer> header = new LinkedTreeMap.Node<String, Integer>(true);
    LinkedTreeMap.Node<String, Integer> parent = new LinkedTreeMap.Node<String, Integer>(true);
    LinkedTreeMap.Node<String, Integer> node =
        new LinkedTreeMap.Node<String, Integer>(true, parent, "key", header, header.prev);
    node.value = 42;

    Map.Entry<String, Integer> entry = new AbstractMap.SimpleEntry<String, Integer>("key", 42);
    assertThat(node.equals(entry)).isTrue();
  }

  @Test
  public void testEqualsWithNonEntry() {
    LinkedTreeMap.Node<String, Integer> header = new LinkedTreeMap.Node<String, Integer>(true);
    LinkedTreeMap.Node<String, Integer> parent = new LinkedTreeMap.Node<String, Integer>(true);
    LinkedTreeMap.Node<String, Integer> node =
        new LinkedTreeMap.Node<String, Integer>(true, parent, "key", header, header.prev);

    assertThat(node.equals("not an entry")).isFalse();
  }

  @Test
  public void testHashCode() {
    LinkedTreeMap.Node<String, Integer> header = new LinkedTreeMap.Node<String, Integer>(true);
    LinkedTreeMap.Node<String, Integer> parent = new LinkedTreeMap.Node<String, Integer>(true);
    LinkedTreeMap.Node<String, Integer> node =
        new LinkedTreeMap.Node<String, Integer>(true, parent, "key", header, header.prev);
    node.value = 42;

    int expectedHashCode = "key".hashCode() ^ Integer.valueOf(42).hashCode();
    assertThat(node.hashCode()).isEqualTo(expectedHashCode);
  }

  @Test
  public void testHashCodeWithNullKey() {
    LinkedTreeMap.Node<String, Integer> node = new LinkedTreeMap.Node<String, Integer>(true);
    node.value = 42;

    int expectedHashCode = 0 ^ Integer.valueOf(42).hashCode();
    assertThat(node.hashCode()).isEqualTo(expectedHashCode);
  }

  @Test
  public void testHashCodeWithNullValue() {
    LinkedTreeMap.Node<String, Integer> header = new LinkedTreeMap.Node<String, Integer>(true);
    LinkedTreeMap.Node<String, Integer> parent = new LinkedTreeMap.Node<String, Integer>(true);
    LinkedTreeMap.Node<String, Integer> node =
        new LinkedTreeMap.Node<String, Integer>(true, parent, "key", header, header.prev);

    int expectedHashCode = "key".hashCode() ^ 0;
    assertThat(node.hashCode()).isEqualTo(expectedHashCode);
  }

  @Test
  public void testHashCodeWithBothNull() {
    LinkedTreeMap.Node<String, Integer> node = new LinkedTreeMap.Node<String, Integer>(true);

    assertThat(node.hashCode()).isEqualTo(0);
  }

  @Test
  public void testToString() {
    LinkedTreeMap.Node<String, Integer> header = new LinkedTreeMap.Node<String, Integer>(true);
    LinkedTreeMap.Node<String, Integer> parent = new LinkedTreeMap.Node<String, Integer>(true);
    LinkedTreeMap.Node<String, Integer> node =
        new LinkedTreeMap.Node<String, Integer>(true, parent, "key", header, header.prev);
    node.value = 42;

    assertThat(node.toString()).isEqualTo("key=42");
  }

  @Test
  public void testToStringWithNullKey() {
    LinkedTreeMap.Node<String, Integer> node = new LinkedTreeMap.Node<String, Integer>(true);
    node.value = 42;

    assertThat(node.toString()).isEqualTo("null=42");
  }

  @Test
  public void testToStringWithNullValue() {
    LinkedTreeMap.Node<String, Integer> header = new LinkedTreeMap.Node<String, Integer>(true);
    LinkedTreeMap.Node<String, Integer> parent = new LinkedTreeMap.Node<String, Integer>(true);
    LinkedTreeMap.Node<String, Integer> node =
        new LinkedTreeMap.Node<String, Integer>(true, parent, "key", header, header.prev);

    assertThat(node.toString()).isEqualTo("key=null");
  }

  @Test
  public void testToStringWithBothNull() {
    LinkedTreeMap.Node<String, Integer> node = new LinkedTreeMap.Node<String, Integer>(true);

    assertThat(node.toString()).isEqualTo("null=null");
  }

  @Test
  public void testFirstWithNoLeftChild() {
    LinkedTreeMap.Node<String, Integer> header = new LinkedTreeMap.Node<String, Integer>(true);
    LinkedTreeMap.Node<String, Integer> parent = new LinkedTreeMap.Node<String, Integer>(true);
    LinkedTreeMap.Node<String, Integer> node =
        new LinkedTreeMap.Node<String, Integer>(true, parent, "key", header, header.prev);

    assertThat(node.first()).isSameInstanceAs(node);
  }

  @Test
  public void testFirstWithLeftChild() {
    LinkedTreeMap.Node<String, Integer> header = new LinkedTreeMap.Node<String, Integer>(true);
    LinkedTreeMap.Node<String, Integer> parent = new LinkedTreeMap.Node<String, Integer>(true);
    LinkedTreeMap.Node<String, Integer> root =
        new LinkedTreeMap.Node<String, Integer>(true, parent, "root", header, header.prev);

    LinkedTreeMap.Node<String, Integer> left =
        new LinkedTreeMap.Node<String, Integer>(true, root, "left", header, header.prev);
    root.left = left;

    assertThat(root.first()).isSameInstanceAs(left);
  }

  @Test
  public void testFirstWithMultipleLevels() {
    LinkedTreeMap.Node<String, Integer> header = new LinkedTreeMap.Node<String, Integer>(true);
    LinkedTreeMap.Node<String, Integer> parent = new LinkedTreeMap.Node<String, Integer>(true);
    LinkedTreeMap.Node<String, Integer> root =
        new LinkedTreeMap.Node<String, Integer>(true, parent, "root", header, header.prev);

    LinkedTreeMap.Node<String, Integer> left =
        new LinkedTreeMap.Node<String, Integer>(true, root, "left", header, header.prev);
    root.left = left;

    LinkedTreeMap.Node<String, Integer> leftLeft =
        new LinkedTreeMap.Node<String, Integer>(true, left, "leftLeft", header, header.prev);
    left.left = leftLeft;

    assertThat(root.first()).isSameInstanceAs(leftLeft);
  }

  @Test
  public void testLastWithNoRightChild() {
    LinkedTreeMap.Node<String, Integer> header = new LinkedTreeMap.Node<String, Integer>(true);
    LinkedTreeMap.Node<String, Integer> parent = new LinkedTreeMap.Node<String, Integer>(true);
    LinkedTreeMap.Node<String, Integer> node =
        new LinkedTreeMap.Node<String, Integer>(true, parent, "key", header, header.prev);

    assertThat(node.last()).isSameInstanceAs(node);
  }

  @Test
  public void testLastWithRightChild() {
    LinkedTreeMap.Node<String, Integer> header = new LinkedTreeMap.Node<String, Integer>(true);
    LinkedTreeMap.Node<String, Integer> parent = new LinkedTreeMap.Node<String, Integer>(true);
    LinkedTreeMap.Node<String, Integer> root =
        new LinkedTreeMap.Node<String, Integer>(true, parent, "root", header, header.prev);

    LinkedTreeMap.Node<String, Integer> right =
        new LinkedTreeMap.Node<String, Integer>(true, root, "right", header, header.prev);
    root.right = right;

    assertThat(root.last()).isSameInstanceAs(right);
  }

  @Test
  public void testLastWithMultipleLevels() {
    LinkedTreeMap.Node<String, Integer> header = new LinkedTreeMap.Node<String, Integer>(true);
    LinkedTreeMap.Node<String, Integer> parent = new LinkedTreeMap.Node<String, Integer>(true);
    LinkedTreeMap.Node<String, Integer> root =
        new LinkedTreeMap.Node<String, Integer>(true, parent, "root", header, header.prev);

    LinkedTreeMap.Node<String, Integer> right =
        new LinkedTreeMap.Node<String, Integer>(true, root, "right", header, header.prev);
    root.right = right;

    LinkedTreeMap.Node<String, Integer> rightRight =
        new LinkedTreeMap.Node<String, Integer>(true, right, "rightRight", header, header.prev);
    right.right = rightRight;

    assertThat(root.last()).isSameInstanceAs(rightRight);
  }
}
