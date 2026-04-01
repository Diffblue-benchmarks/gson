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

import java.util.Map;
import org.junit.Test;

public final class LinkedTreeMapNodeTest {

  @Test
  public void testNodeHeaderConstructorAllowNullValue() {
    LinkedTreeMap.Node<String, String> header = new LinkedTreeMap.Node<>(true);

    assertThat(header.key).isNull();
    assertThat(header.allowNullValue).isTrue();
    assertThat(header.next).isEqualTo(header);
    assertThat(header.prev).isEqualTo(header);
  }

  @Test
  public void testNodeHeaderConstructorDisallowNullValue() {
    LinkedTreeMap.Node<String, String> header = new LinkedTreeMap.Node<>(false);

    assertThat(header.key).isNull();
    assertThat(header.allowNullValue).isFalse();
    assertThat(header.next).isEqualTo(header);
    assertThat(header.prev).isEqualTo(header);
  }

  @Test
  public void testNodeRegularConstructor() {
    LinkedTreeMap.Node<String, String> header = new LinkedTreeMap.Node<>(true);
    LinkedTreeMap.Node<String, String> node =
        new LinkedTreeMap.Node<>(true, null, "key1", header, header);

    assertThat(node.key).isEqualTo("key1");
    assertThat(node.allowNullValue).isTrue();
    assertThat(node.height).isEqualTo(1);
    assertThat(node.parent).isNull();
    assertThat(node.next).isEqualTo(header);
    assertThat(node.prev).isEqualTo(header);
    assertThat(header.prev).isEqualTo(node);
    assertThat(header.next).isEqualTo(node);
  }

  @Test
  public void testGetKey() {
    LinkedTreeMap.Node<String, String> header = new LinkedTreeMap.Node<>(true);
    LinkedTreeMap.Node<String, String> node =
        new LinkedTreeMap.Node<>(true, null, "myKey", header, header);

    assertThat(node.getKey()).isEqualTo("myKey");
  }

  @Test
  public void testGetValue() {
    LinkedTreeMap.Node<String, String> header = new LinkedTreeMap.Node<>(true);
    LinkedTreeMap.Node<String, String> node =
        new LinkedTreeMap.Node<>(true, null, "key", header, header);
    node.value = "myValue";

    assertThat(node.getValue()).isEqualTo("myValue");
  }

  @Test
  public void testSetValue() {
    LinkedTreeMap.Node<String, String> header = new LinkedTreeMap.Node<>(true);
    LinkedTreeMap.Node<String, String> node =
        new LinkedTreeMap.Node<>(true, null, "key", header, header);
    node.value = "oldValue";

    String oldValue = node.setValue("newValue");

    assertThat(oldValue).isEqualTo("oldValue");
    assertThat(node.getValue()).isEqualTo("newValue");
  }

  @Test
  public void testSetValueNullAllowed() {
    LinkedTreeMap.Node<String, String> header = new LinkedTreeMap.Node<>(true);
    LinkedTreeMap.Node<String, String> node =
        new LinkedTreeMap.Node<>(true, null, "key", header, header);
    node.value = "oldValue";

    String oldValue = node.setValue(null);

    assertThat(oldValue).isEqualTo("oldValue");
    assertThat(node.getValue()).isNull();
  }

  @Test
  public void testSetValueNullNotAllowed() {
    LinkedTreeMap.Node<String, String> header = new LinkedTreeMap.Node<>(false);
    LinkedTreeMap.Node<String, String> node =
        new LinkedTreeMap.Node<>(false, null, "key", header, header);

    NullPointerException e =
        assertThrows(NullPointerException.class, () -> node.setValue(null));
    assertThat(e).hasMessageThat().isEqualTo("value == null");
  }

  @Test
  public void testEqualsWithSameKeyAndValue() {
    LinkedTreeMap<String, String> map1 = new LinkedTreeMap<>();
    map1.put("key", "value");
    LinkedTreeMap<String, String> map2 = new LinkedTreeMap<>();
    map2.put("key", "value");

    Map.Entry<String, String> entry1 = map1.entrySet().iterator().next();
    Map.Entry<String, String> entry2 = map2.entrySet().iterator().next();

    assertThat(entry1.equals(entry2)).isTrue();
  }

  @Test
  public void testEqualsWithDifferentValue() {
    LinkedTreeMap<String, String> map1 = new LinkedTreeMap<>();
    map1.put("key", "value1");
    LinkedTreeMap<String, String> map2 = new LinkedTreeMap<>();
    map2.put("key", "value2");

    Map.Entry<String, String> entry1 = map1.entrySet().iterator().next();
    Map.Entry<String, String> entry2 = map2.entrySet().iterator().next();

    assertThat(entry1.equals(entry2)).isFalse();
  }

  @Test
  public void testEqualsWithNonEntry() {
    LinkedTreeMap<String, String> map = new LinkedTreeMap<>();
    map.put("key", "value");

    Map.Entry<String, String> entry = map.entrySet().iterator().next();

    assertThat(entry.equals("not an entry")).isFalse();
  }

  @Test
  public void testHashCode() {
    LinkedTreeMap.Node<String, String> header = new LinkedTreeMap.Node<>(true);
    LinkedTreeMap.Node<String, String> node =
        new LinkedTreeMap.Node<>(true, null, "key", header, header);
    node.value = "value";

    int expected = "key".hashCode() ^ "value".hashCode();
    assertThat(node.hashCode()).isEqualTo(expected);
  }

  @Test
  public void testHashCodeNullKeyAndValue() {
    LinkedTreeMap.Node<String, String> header = new LinkedTreeMap.Node<>(true);

    assertThat(header.hashCode()).isEqualTo(0);
  }

  @Test
  public void testToString() {
    LinkedTreeMap.Node<String, String> header = new LinkedTreeMap.Node<>(true);
    LinkedTreeMap.Node<String, String> node =
        new LinkedTreeMap.Node<>(true, null, "key", header, header);
    node.value = "value";

    assertThat(node.toString()).isEqualTo("key=value");
  }

  @Test
  public void testFirstNoChildren() {
    LinkedTreeMap.Node<String, String> header = new LinkedTreeMap.Node<>(true);
    LinkedTreeMap.Node<String, String> node =
        new LinkedTreeMap.Node<>(true, null, "key", header, header);

    assertThat(node.first()).isEqualTo(node);
  }

  @Test
  public void testFirstWithLeftChildren() {
    LinkedTreeMap.Node<String, String> header = new LinkedTreeMap.Node<>(true);
    LinkedTreeMap.Node<String, String> root =
        new LinkedTreeMap.Node<>(true, null, "b", header, header);
    LinkedTreeMap.Node<String, String> leftChild =
        new LinkedTreeMap.Node<>(true, root, "a", header, header);
    root.left = leftChild;

    assertThat(root.first()).isEqualTo(leftChild);
  }

  @Test
  public void testLastNoChildren() {
    LinkedTreeMap.Node<String, String> header = new LinkedTreeMap.Node<>(true);
    LinkedTreeMap.Node<String, String> node =
        new LinkedTreeMap.Node<>(true, null, "key", header, header);

    assertThat(node.last()).isEqualTo(node);
  }

  @Test
  public void testLastWithRightChildren() {
    LinkedTreeMap.Node<String, String> header = new LinkedTreeMap.Node<>(true);
    LinkedTreeMap.Node<String, String> root =
        new LinkedTreeMap.Node<>(true, null, "b", header, header);
    LinkedTreeMap.Node<String, String> rightChild =
        new LinkedTreeMap.Node<>(true, root, "c", header, header);
    root.right = rightChild;

    assertThat(root.last()).isEqualTo(rightChild);
  }
}
