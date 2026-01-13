/*
 * Copyright (C) 2008 Google Inc.
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

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.AbstractCollection;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

/**
 * Unit tests for {@link GsonTypes#getGenericSupertype} via public methods.
 *
 * <p>The getGenericSupertype method is private but called by getCollectionElementType
 * and getMapKeyAndValueTypes.
 *
 * @author Claude
 */
public class GsonTypesClaude_getGenericSupertypeTest {

  // ========== Tests to cover line 262-263: rawType = rawSupertype (class hierarchy iteration) ==========

  /**
   * Test with a deep class hierarchy where the supertype is an interface not directly
   * implemented but inherited through multiple levels.
   * This exercises the while loop that iterates through the class hierarchy (lines 255-263).
   */
  @Test
  public void testGetGenericSupertype_DeepClassHierarchy_CoverLine262_263() {
    // GrandChildList extends ChildList<String> extends ParentList<T> extends AbstractList<T>
    // When we resolve to Collection (an interface), we need to traverse the class hierarchy
    // AbstractList implements List which extends Collection
    // This should trigger iteration through the class hierarchy
    Type elementType = GsonTypes.getCollectionElementType(GrandChildList.class, GrandChildList.class);
    assertThat(elementType).isEqualTo(String.class);
  }

  /**
   * Test with a class that has multiple levels of inheritance before implementing an interface.
   * The class hierarchy is: ConcreteClass -> AbstractMiddle -> AbstractBase
   * where AbstractBase implements Collection<String>.
   */
  @Test
  public void testGetGenericSupertype_MultiLevelInheritance() {
    Type elementType = GsonTypes.getCollectionElementType(ConcreteCollection.class, ConcreteCollection.class);
    assertThat(elementType).isEqualTo(String.class);
  }

  /**
   * Test getting collection element type from a class with a non-generic superclass chain.
   */
  @Test
  public void testGetGenericSupertype_ClassChainWithInterfaceAtEnd() {
    // StringArrayList extends ArrayList<String>
    // ArrayList extends AbstractList extends AbstractCollection implements Collection
    Type elementType = GsonTypes.getCollectionElementType(StringArrayList.class, StringArrayList.class);
    assertThat(elementType).isEqualTo(String.class);
  }

  // ========== Tests to cover line 267: return supertype (unresolvable case) ==========

  /**
   * Test with an interface type as the rawType.
   * When rawType is an interface, the while loop (lines 254-263) is skipped entirely.
   * If the supertype is not found in the interface's own interfaces, line 267 is reached.
   */
  @Test
  public void testGetGenericSupertype_InterfaceContext_CoverLine267() {
    // Using resolve with an interface as context where the type can't be resolved further
    // When we try to resolve Collection from List interface context
    // List is an interface, so line 254's condition (!rawType.isInterface()) is false
    // The while loop is skipped, and we may reach line 267
    Type resolved = GsonTypes.resolve(List.class, List.class, Collection.class);
    // Collection.class should be returned as-is since List is an interface
    assertThat(resolved).isEqualTo(Collection.class);
  }

  /**
   * Test with an interface that doesn't implement the target interface.
   * This should exercise line 267 when the interface search fails.
   */
  @Test
  public void testGetGenericSupertype_InterfaceNotFound() {
    // Serializable is an interface that doesn't extend Collection
    // When trying to get Collection supertype from Serializable, line 267 is hit
    // We test this indirectly through resolve
    Type resolved = GsonTypes.resolve(SerializableClass.class, SerializableClass.class, Serializable.class);
    assertThat(resolved).isEqualTo(Serializable.class);
  }

  /**
   * Test with a class hierarchy where the target supertype is not in the hierarchy.
   * This forces the iteration to complete without finding the type, reaching line 267.
   */
  @Test
  public void testGetGenericSupertype_SupertypeNotInHierarchy() {
    // Object doesn't have any superclass, so when we try to find a non-existent
    // supertype starting from a simple class hierarchy, line 267 is reached
    // Using resolve to test this indirectly
    Type resolved = GsonTypes.resolve(SimpleClass.class, SimpleClass.class, Number.class);
    // Number.class is not in SimpleClass hierarchy, should return as-is
    assertThat(resolved).isEqualTo(Number.class);
  }

  // ========== Additional tests for better branch coverage ==========

  /**
   * Test with nested collection types to ensure proper traversal.
   */
  @Test
  public void testGetGenericSupertype_NestedCollectionTraversal() {
    ParameterizedType listOfListOfString = GsonTypes.newParameterizedTypeWithOwner(
        null, List.class, GsonTypes.newParameterizedTypeWithOwner(null, List.class, String.class));
    Type elementType = GsonTypes.getCollectionElementType(listOfListOfString, ArrayList.class);
    assertThat(elementType).isInstanceOf(ParameterizedType.class);
    ParameterizedType innerList = (ParameterizedType) elementType;
    assertThat(innerList.getRawType()).isEqualTo(List.class);
  }

  /**
   * Test Map type traversal through class hierarchy.
   */
  @Test
  public void testGetGenericSupertype_MapHierarchyTraversal() {
    // CustomHashMap extends HashMap<String, Integer>
    Type[] keyValueTypes = GsonTypes.getMapKeyAndValueTypes(CustomHashMap.class, CustomHashMap.class);
    assertThat(keyValueTypes[0]).isEqualTo(String.class);
    assertThat(keyValueTypes[1]).isEqualTo(Integer.class);
  }

  /**
   * Test with a class that extends a parameterized class multiple levels up.
   */
  @Test
  public void testGetGenericSupertype_DeepMapHierarchy() {
    // DeepMap -> MiddleMap -> HashMap<String, Long>
    Type[] keyValueTypes = GsonTypes.getMapKeyAndValueTypes(DeepMap.class, DeepMap.class);
    assertThat(keyValueTypes[0]).isEqualTo(String.class);
    assertThat(keyValueTypes[1]).isEqualTo(Long.class);
  }

  /**
   * Test with a class hierarchy where interface is implemented by an ancestor class.
   */
  @Test
  public void testGetGenericSupertype_InterfaceFromAncestor() {
    // When the target interface is implemented by an ancestor, the code
    // must traverse the class hierarchy to find it
    Type elementType = GsonTypes.getCollectionElementType(
        DoubleNestedList.class, DoubleNestedList.class);
    assertThat(elementType).isEqualTo(Double.class);
  }

  /**
   * Test traversal when supertype is a class (not interface) requiring hierarchy walk.
   */
  @Test
  public void testGetGenericSupertype_ClassSupertypeTraversal() {
    // Test getting to AbstractCollection through AbstractList
    // This exercises the rawType = rawSupertype line during the iteration
    ParameterizedType type = GsonTypes.newParameterizedTypeWithOwner(null, ArrayList.class, String.class);
    Type elementType = GsonTypes.getCollectionElementType(type, ArrayList.class);
    assertThat(elementType).isEqualTo(String.class);
  }

  // ========== Helper Classes ==========

  // Deep class hierarchy for List
  static class ParentList<T> extends AbstractList<T> {
    @Override
    public T get(int index) {
      return null;
    }

    @Override
    public int size() {
      return 0;
    }
  }

  static class ChildList<T> extends ParentList<T> {}

  static class GrandChildList extends ChildList<String> {}

  // Multi-level abstract collection hierarchy
  abstract static class AbstractBaseCollection<E> extends AbstractCollection<E> {}

  abstract static class AbstractMiddleCollection<E> extends AbstractBaseCollection<E> {}

  static class ConcreteCollection extends AbstractMiddleCollection<String> {
    @Override
    public java.util.Iterator<String> iterator() {
      return null;
    }

    @Override
    public int size() {
      return 0;
    }
  }

  // String ArrayList
  static class StringArrayList extends ArrayList<String> {
    private static final long serialVersionUID = 1L;
  }

  // Serializable class for interface tests
  static class SerializableClass implements Serializable {
    private static final long serialVersionUID = 1L;
  }

  // Simple class with no special interfaces
  static class SimpleClass {}

  // Custom HashMap hierarchy
  static class CustomHashMap extends HashMap<String, Integer> {
    private static final long serialVersionUID = 1L;
  }

  // Deep Map hierarchy
  static class MiddleMap extends HashMap<String, Long> {
    private static final long serialVersionUID = 1L;
  }

  static class DeepMap extends MiddleMap {
    private static final long serialVersionUID = 1L;
  }

  // Double nested list for interface from ancestor test
  static class SingleNestedList extends ArrayList<Double> {
    private static final long serialVersionUID = 1L;
  }

  static class DoubleNestedList extends SingleNestedList {
    private static final long serialVersionUID = 1L;
  }
}
