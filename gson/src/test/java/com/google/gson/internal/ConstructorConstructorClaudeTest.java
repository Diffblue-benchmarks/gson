/*
 * Copyright (C) 2011 Google Inc.
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

import com.google.gson.InstanceCreator;
import com.google.gson.JsonIOException;
import com.google.gson.ReflectionAccessFilter;
import com.google.gson.ReflectionAccessFilter.FilterResult;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import org.junit.Test;

/**
 * Unit tests for {@link ConstructorConstructor}.
 *
 * @author Claude
 */
public class ConstructorConstructorClaudeTest {

  // ========== Constructor Tests ==========

  @Test
  public void testConstructorWithEmptyInstanceCreators() {
    Map<Type, InstanceCreator<?>> instanceCreators = Collections.emptyMap();
    ConstructorConstructor cc =
        new ConstructorConstructor(instanceCreators, true, Collections.emptyList());
    assertThat(cc).isNotNull();
    assertThat(cc.toString()).isEqualTo("{}");
  }

  @Test
  public void testConstructorWithInstanceCreators() {
    Map<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    instanceCreators.put(String.class, type -> "created");
    ConstructorConstructor cc =
        new ConstructorConstructor(instanceCreators, true, Collections.emptyList());
    assertThat(cc).isNotNull();
    assertThat(cc.toString()).contains("String");
  }

  @Test
  public void testConstructorWithUseJdkUnsafeFalse() {
    ConstructorConstructor cc =
        new ConstructorConstructor(Collections.emptyMap(), false, Collections.emptyList());
    assertThat(cc).isNotNull();
  }

  @Test
  public void testConstructorWithReflectionFilters() {
    List<ReflectionAccessFilter> filters = new ArrayList<>();
    filters.add(ReflectionAccessFilter.BLOCK_ALL_JAVA);
    ConstructorConstructor cc =
        new ConstructorConstructor(Collections.emptyMap(), true, filters);
    assertThat(cc).isNotNull();
  }

  // ========== checkInstantiable Tests ==========

  @Test
  public void testCheckInstantiableWithNormalClass() {
    String result = ConstructorConstructor.checkInstantiable(String.class);
    assertThat(result).isNull();
  }

  @Test
  public void testCheckInstantiableWithConcreteClass() {
    String result = ConstructorConstructor.checkInstantiable(ArrayList.class);
    assertThat(result).isNull();
  }

  @Test
  public void testCheckInstantiableWithInterface() {
    String result = ConstructorConstructor.checkInstantiable(List.class);
    assertThat(result).isNotNull();
    assertThat(result).contains("Interfaces can't be instantiated");
    assertThat(result).contains("java.util.List");
  }

  @Test
  public void testCheckInstantiableWithAbstractClass() {
    String result = ConstructorConstructor.checkInstantiable(java.util.AbstractList.class);
    assertThat(result).isNotNull();
    assertThat(result).contains("Abstract classes can't be instantiated");
    assertThat(result).contains("java.util.AbstractList");
    assertThat(result).contains("r8-abstract-class");
  }

  @Test
  public void testCheckInstantiableWithAbstractMap() {
    String result = ConstructorConstructor.checkInstantiable(java.util.AbstractMap.class);
    assertThat(result).isNotNull();
    assertThat(result).contains("Abstract classes can't be instantiated");
  }

  @Test
  public void testCheckInstantiableWithPrimitiveArrayType() {
    // Primitive arrays are considered abstract in Java's reflection system
    String result = ConstructorConstructor.checkInstantiable(int[].class);
    assertThat(result).isNotNull();
    assertThat(result).contains("Abstract classes can't be instantiated");
  }

  // ========== get(TypeToken) Tests - Uses InstanceCreator ==========

  @Test
  public void testGetWithExactTypeInstanceCreator() {
    Map<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    instanceCreators.put(SimpleClass.class, type -> new SimpleClass("created"));
    ConstructorConstructor cc =
        new ConstructorConstructor(instanceCreators, true, Collections.emptyList());

    ObjectConstructor<SimpleClass> constructor = cc.get(TypeToken.get(SimpleClass.class));
    SimpleClass instance = constructor.construct();
    assertThat(instance).isNotNull();
    assertThat(instance.value).isEqualTo("created");
  }

  @Test
  public void testGetWithRawTypeInstanceCreator() {
    // Using parameterized type but registering instance creator for raw type
    Map<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    instanceCreators.put(GenericClass.class, type -> new GenericClass<>("created"));
    ConstructorConstructor cc =
        new ConstructorConstructor(instanceCreators, true, Collections.emptyList());

    TypeToken<GenericClass<String>> typeToken = new TypeToken<GenericClass<String>>() {};
    ObjectConstructor<GenericClass<String>> constructor = cc.get(typeToken);
    GenericClass<String> instance = constructor.construct();
    assertThat(instance).isNotNull();
    assertThat(instance.value).isEqualTo("created");
  }

  @Test
  public void testGetWithParameterizedTypeInstanceCreator() {
    Map<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    TypeToken<GenericClass<String>> typeToken = new TypeToken<GenericClass<String>>() {};
    instanceCreators.put(typeToken.getType(), type -> new GenericClass<>("parameterized"));
    ConstructorConstructor cc =
        new ConstructorConstructor(instanceCreators, true, Collections.emptyList());

    ObjectConstructor<GenericClass<String>> constructor = cc.get(typeToken);
    GenericClass<String> instance = constructor.construct();
    assertThat(instance).isNotNull();
    assertThat(instance.value).isEqualTo("parameterized");
  }

  // ========== get(TypeToken) Tests - Special Collections ==========

  @Test
  public void testGetWithEnumSet() {
    ConstructorConstructor cc =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    TypeToken<EnumSet<TestEnum>> typeToken = new TypeToken<EnumSet<TestEnum>>() {};
    ObjectConstructor<EnumSet<TestEnum>> constructor = cc.get(typeToken);
    EnumSet<TestEnum> instance = constructor.construct();
    assertThat(instance).isNotNull();
    assertThat(instance).isEmpty();
  }

  @Test
  public void testGetWithEnumSetSubclass() {
    // EnumSet creates specialized implementations (RegularEnumSet, JumboEnumSet) which are subclasses
    ConstructorConstructor cc =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    TypeToken<EnumSet<TestEnum>> typeToken = new TypeToken<EnumSet<TestEnum>>() {};
    ObjectConstructor<EnumSet<TestEnum>> constructor = cc.get(typeToken);
    EnumSet<TestEnum> instance = constructor.construct();
    assertThat(instance).isInstanceOf(EnumSet.class);
  }

  @Test
  public void testGetWithEnumSetRawTypeThrows() {
    ConstructorConstructor cc =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    @SuppressWarnings("rawtypes")
    TypeToken<EnumSet> typeToken = TypeToken.get(EnumSet.class);
    @SuppressWarnings("rawtypes")
    ObjectConstructor<EnumSet> constructor = cc.get(typeToken);
    JsonIOException exception = assertThrows(JsonIOException.class, constructor::construct);
    assertThat(exception.getMessage()).contains("Invalid EnumSet type");
  }

  @Test
  public void testGetWithEnumMap() {
    ConstructorConstructor cc =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    TypeToken<EnumMap<TestEnum, String>> typeToken = new TypeToken<EnumMap<TestEnum, String>>() {};
    ObjectConstructor<EnumMap<TestEnum, String>> constructor = cc.get(typeToken);
    EnumMap<TestEnum, String> instance = constructor.construct();
    assertThat(instance).isNotNull();
    assertThat(instance).isEmpty();
  }

  @Test
  public void testGetWithEnumMapRawTypeThrows() {
    ConstructorConstructor cc =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    @SuppressWarnings("rawtypes")
    TypeToken<EnumMap> typeToken = TypeToken.get(EnumMap.class);
    @SuppressWarnings("rawtypes")
    ObjectConstructor<EnumMap> constructor = cc.get(typeToken);
    JsonIOException exception = assertThrows(JsonIOException.class, constructor::construct);
    assertThat(exception.getMessage()).contains("Invalid EnumMap type");
  }

  // ========== get(TypeToken) Tests - Default Constructor ==========

  @Test
  public void testGetWithNoArgsConstructor() {
    ConstructorConstructor cc =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    ObjectConstructor<ClassWithNoArgsConstructor> constructor =
        cc.get(TypeToken.get(ClassWithNoArgsConstructor.class));
    ClassWithNoArgsConstructor instance = constructor.construct();
    assertThat(instance).isNotNull();
    assertThat(instance.initialized).isTrue();
  }

  @Test
  public void testGetWithPrivateNoArgsConstructor() {
    ConstructorConstructor cc =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    ObjectConstructor<ClassWithPrivateConstructor> constructor =
        cc.get(TypeToken.get(ClassWithPrivateConstructor.class));
    ClassWithPrivateConstructor instance = constructor.construct();
    assertThat(instance).isNotNull();
    assertThat(instance.value).isEqualTo("private");
  }

  // ========== get(TypeToken) Tests - Default Implementations for Collections ==========

  @Test
  public void testGetWithListInterface() {
    ConstructorConstructor cc =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    TypeToken<List<String>> typeToken = new TypeToken<List<String>>() {};
    ObjectConstructor<List<String>> constructor = cc.get(typeToken);
    List<String> instance = constructor.construct();
    assertThat(instance).isNotNull();
    assertThat(instance).isInstanceOf(ArrayList.class);
  }

  @Test
  public void testGetWithCollectionInterface() {
    ConstructorConstructor cc =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    TypeToken<Collection<String>> typeToken = new TypeToken<Collection<String>>() {};
    ObjectConstructor<Collection<String>> constructor = cc.get(typeToken);
    Collection<String> instance = constructor.construct();
    assertThat(instance).isNotNull();
    assertThat(instance).isInstanceOf(ArrayList.class);
  }

  @Test
  public void testGetWithSetInterface() {
    ConstructorConstructor cc =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    TypeToken<Set<String>> typeToken = new TypeToken<Set<String>>() {};
    ObjectConstructor<Set<String>> constructor = cc.get(typeToken);
    Set<String> instance = constructor.construct();
    assertThat(instance).isNotNull();
    assertThat(instance).isInstanceOf(LinkedHashSet.class);
  }

  @Test
  public void testGetWithSortedSetInterface() {
    ConstructorConstructor cc =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    TypeToken<SortedSet<String>> typeToken = new TypeToken<SortedSet<String>>() {};
    ObjectConstructor<SortedSet<String>> constructor = cc.get(typeToken);
    SortedSet<String> instance = constructor.construct();
    assertThat(instance).isNotNull();
    assertThat(instance).isInstanceOf(TreeSet.class);
  }

  @Test
  public void testGetWithNavigableSetInterface() {
    ConstructorConstructor cc =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    TypeToken<NavigableSet<String>> typeToken = new TypeToken<NavigableSet<String>>() {};
    ObjectConstructor<NavigableSet<String>> constructor = cc.get(typeToken);
    NavigableSet<String> instance = constructor.construct();
    assertThat(instance).isNotNull();
    assertThat(instance).isInstanceOf(TreeSet.class);
  }

  @Test
  public void testGetWithQueueInterface() {
    ConstructorConstructor cc =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    TypeToken<Queue<String>> typeToken = new TypeToken<Queue<String>>() {};
    ObjectConstructor<Queue<String>> constructor = cc.get(typeToken);
    Queue<String> instance = constructor.construct();
    assertThat(instance).isNotNull();
    assertThat(instance).isInstanceOf(ArrayDeque.class);
  }

  @Test
  public void testGetWithDequeInterface() {
    ConstructorConstructor cc =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    TypeToken<Deque<String>> typeToken = new TypeToken<Deque<String>>() {};
    ObjectConstructor<Deque<String>> constructor = cc.get(typeToken);
    Deque<String> instance = constructor.construct();
    assertThat(instance).isNotNull();
    assertThat(instance).isInstanceOf(ArrayDeque.class);
  }

  // ========== get(TypeToken) Tests - Default Implementations for Maps ==========

  @Test
  public void testGetWithMapInterfaceStringKey() {
    ConstructorConstructor cc =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    TypeToken<Map<String, Integer>> typeToken = new TypeToken<Map<String, Integer>>() {};
    ObjectConstructor<Map<String, Integer>> constructor = cc.get(typeToken);
    Map<String, Integer> instance = constructor.construct();
    assertThat(instance).isNotNull();
    // LinkedTreeMap is used for String keys to avoid DoS attacks
    assertThat(instance).isInstanceOf(LinkedTreeMap.class);
  }

  @Test
  public void testGetWithMapInterfaceNonStringKey() {
    ConstructorConstructor cc =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    TypeToken<Map<Integer, String>> typeToken = new TypeToken<Map<Integer, String>>() {};
    ObjectConstructor<Map<Integer, String>> constructor = cc.get(typeToken);
    Map<Integer, String> instance = constructor.construct();
    assertThat(instance).isNotNull();
    assertThat(instance).isInstanceOf(LinkedHashMap.class);
  }

  @Test
  public void testGetWithSortedMapInterface() {
    ConstructorConstructor cc =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    TypeToken<SortedMap<String, Integer>> typeToken = new TypeToken<SortedMap<String, Integer>>() {};
    ObjectConstructor<SortedMap<String, Integer>> constructor = cc.get(typeToken);
    SortedMap<String, Integer> instance = constructor.construct();
    assertThat(instance).isNotNull();
    assertThat(instance).isInstanceOf(TreeMap.class);
  }

  @Test
  public void testGetWithNavigableMapInterface() {
    ConstructorConstructor cc =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    TypeToken<NavigableMap<String, Integer>> typeToken =
        new TypeToken<NavigableMap<String, Integer>>() {};
    ObjectConstructor<NavigableMap<String, Integer>> constructor = cc.get(typeToken);
    NavigableMap<String, Integer> instance = constructor.construct();
    assertThat(instance).isNotNull();
    assertThat(instance).isInstanceOf(TreeMap.class);
  }

  @Test
  public void testGetWithConcurrentMapInterface() {
    ConstructorConstructor cc =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    TypeToken<ConcurrentMap<String, Integer>> typeToken =
        new TypeToken<ConcurrentMap<String, Integer>>() {};
    ObjectConstructor<ConcurrentMap<String, Integer>> constructor = cc.get(typeToken);
    ConcurrentMap<String, Integer> instance = constructor.construct();
    assertThat(instance).isNotNull();
    assertThat(instance).isInstanceOf(ConcurrentHashMap.class);
  }

  @Test
  public void testGetWithConcurrentNavigableMapInterface() {
    ConstructorConstructor cc =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    TypeToken<ConcurrentNavigableMap<String, Integer>> typeToken =
        new TypeToken<ConcurrentNavigableMap<String, Integer>>() {};
    ObjectConstructor<ConcurrentNavigableMap<String, Integer>> constructor = cc.get(typeToken);
    ConcurrentNavigableMap<String, Integer> instance = constructor.construct();
    assertThat(instance).isNotNull();
    assertThat(instance).isInstanceOf(ConcurrentSkipListMap.class);
  }

  @Test
  public void testGetWithRawMapType() {
    ConstructorConstructor cc =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    @SuppressWarnings("rawtypes")
    TypeToken<Map> typeToken = TypeToken.get(Map.class);
    @SuppressWarnings("rawtypes")
    ObjectConstructor<Map> constructor = cc.get(typeToken);
    @SuppressWarnings("rawtypes")
    Map instance = constructor.construct();
    assertThat(instance).isNotNull();
    // Raw Map type assumes String key
    assertThat(instance).isInstanceOf(LinkedTreeMap.class);
  }

  // ========== get(TypeToken, boolean) Tests - allowUnsafe parameter ==========

  @Test
  public void testGetWithAllowUnsafeFalseForClassWithNoDefaultConstructor() {
    ConstructorConstructor cc =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    ObjectConstructor<ClassWithoutNoArgsConstructor> constructor =
        cc.get(TypeToken.get(ClassWithoutNoArgsConstructor.class), false);
    JsonIOException exception = assertThrows(JsonIOException.class, constructor::construct);
    assertThat(exception.getMessage()).contains("Unable to create instance");
    assertThat(exception.getMessage()).contains("Register an InstanceCreator or a TypeAdapter");
  }

  @Test
  public void testGetWithAllowUnsafeTrueForClassWithNoDefaultConstructor() {
    ConstructorConstructor cc =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    ObjectConstructor<ClassWithoutNoArgsConstructor> constructor =
        cc.get(TypeToken.get(ClassWithoutNoArgsConstructor.class), true);
    // Should use Unsafe to create instance
    ClassWithoutNoArgsConstructor instance = constructor.construct();
    assertThat(instance).isNotNull();
  }

  @Test
  public void testGetWithUseJdkUnsafeFalseForClassWithNoDefaultConstructor() {
    ConstructorConstructor cc =
        new ConstructorConstructor(Collections.emptyMap(), false, Collections.emptyList());
    ObjectConstructor<ClassWithoutNoArgsConstructor> constructor =
        cc.get(TypeToken.get(ClassWithoutNoArgsConstructor.class), true);
    JsonIOException exception = assertThrows(JsonIOException.class, constructor::construct);
    assertThat(exception.getMessage()).contains("Unable to create instance");
    assertThat(exception.getMessage()).contains("usage of JDK Unsafe is disabled");
  }

  // ========== get(TypeToken) Tests - Interface and Abstract Class Errors ==========

  @Test
  public void testGetWithInterfaceThrows() {
    ConstructorConstructor cc =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    ObjectConstructor<Runnable> constructor = cc.get(TypeToken.get(Runnable.class));
    JsonIOException exception = assertThrows(JsonIOException.class, constructor::construct);
    assertThat(exception.getMessage()).contains("Interfaces can't be instantiated");
  }

  @Test
  public void testGetWithAbstractClassThrows() {
    ConstructorConstructor cc =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    ObjectConstructor<AbstractTestClass> constructor =
        cc.get(TypeToken.get(AbstractTestClass.class));
    JsonIOException exception = assertThrows(JsonIOException.class, constructor::construct);
    assertThat(exception.getMessage()).contains("Abstract classes can't be instantiated");
  }

  // ========== get(TypeToken) Tests - Concrete collection implementations ==========

  @Test
  public void testGetWithArrayList() {
    ConstructorConstructor cc =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    TypeToken<ArrayList<String>> typeToken = new TypeToken<ArrayList<String>>() {};
    ObjectConstructor<ArrayList<String>> constructor = cc.get(typeToken);
    ArrayList<String> instance = constructor.construct();
    assertThat(instance).isNotNull();
    assertThat(instance).isEmpty();
  }

  @Test
  public void testGetWithLinkedList() {
    ConstructorConstructor cc =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    TypeToken<LinkedList<String>> typeToken = new TypeToken<LinkedList<String>>() {};
    ObjectConstructor<LinkedList<String>> constructor = cc.get(typeToken);
    LinkedList<String> instance = constructor.construct();
    assertThat(instance).isNotNull();
    assertThat(instance).isEmpty();
  }

  @Test
  public void testGetWithHashSet() {
    ConstructorConstructor cc =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    TypeToken<HashSet<String>> typeToken = new TypeToken<HashSet<String>>() {};
    ObjectConstructor<HashSet<String>> constructor = cc.get(typeToken);
    HashSet<String> instance = constructor.construct();
    assertThat(instance).isNotNull();
    assertThat(instance).isEmpty();
  }

  @Test
  public void testGetWithTreeSet() {
    ConstructorConstructor cc =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    TypeToken<TreeSet<String>> typeToken = new TypeToken<TreeSet<String>>() {};
    ObjectConstructor<TreeSet<String>> constructor = cc.get(typeToken);
    TreeSet<String> instance = constructor.construct();
    assertThat(instance).isNotNull();
    assertThat(instance).isEmpty();
  }

  @Test
  public void testGetWithHashMap() {
    ConstructorConstructor cc =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    TypeToken<HashMap<String, Integer>> typeToken = new TypeToken<HashMap<String, Integer>>() {};
    ObjectConstructor<HashMap<String, Integer>> constructor = cc.get(typeToken);
    HashMap<String, Integer> instance = constructor.construct();
    assertThat(instance).isNotNull();
    assertThat(instance).isEmpty();
  }

  @Test
  public void testGetWithLinkedHashMap() {
    ConstructorConstructor cc =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    TypeToken<LinkedHashMap<String, Integer>> typeToken =
        new TypeToken<LinkedHashMap<String, Integer>>() {};
    ObjectConstructor<LinkedHashMap<String, Integer>> constructor = cc.get(typeToken);
    LinkedHashMap<String, Integer> instance = constructor.construct();
    assertThat(instance).isNotNull();
    assertThat(instance).isEmpty();
  }

  @Test
  public void testGetWithTreeMap() {
    ConstructorConstructor cc =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    TypeToken<TreeMap<String, Integer>> typeToken = new TypeToken<TreeMap<String, Integer>>() {};
    ObjectConstructor<TreeMap<String, Integer>> constructor = cc.get(typeToken);
    TreeMap<String, Integer> instance = constructor.construct();
    assertThat(instance).isNotNull();
    assertThat(instance).isEmpty();
  }

  // ========== get(TypeToken) Tests - ReflectionAccessFilter ==========

  @Test
  public void testGetWithBlockAllFilterForCustomClass() {
    List<ReflectionAccessFilter> filters = new ArrayList<>();
    filters.add(
        rawClass -> {
          if (rawClass == ClassWithNoArgsConstructor.class) {
            return FilterResult.BLOCK_ALL;
          }
          return FilterResult.INDECISIVE;
        });
    ConstructorConstructor cc = new ConstructorConstructor(Collections.emptyMap(), true, filters);

    // BLOCK_ALL should still allow accessing public constructor
    ObjectConstructor<ClassWithNoArgsConstructor> constructor =
        cc.get(TypeToken.get(ClassWithNoArgsConstructor.class));
    ClassWithNoArgsConstructor instance = constructor.construct();
    assertThat(instance).isNotNull();
  }

  @Test
  public void testGetWithBlockAllFilterPreventsUnsafe() {
    List<ReflectionAccessFilter> filters = new ArrayList<>();
    filters.add(
        rawClass -> {
          if (rawClass == ClassWithoutNoArgsConstructor.class) {
            return FilterResult.BLOCK_ALL;
          }
          return FilterResult.INDECISIVE;
        });
    ConstructorConstructor cc = new ConstructorConstructor(Collections.emptyMap(), true, filters);

    ObjectConstructor<ClassWithoutNoArgsConstructor> constructor =
        cc.get(TypeToken.get(ClassWithoutNoArgsConstructor.class));
    JsonIOException exception = assertThrows(JsonIOException.class, constructor::construct);
    assertThat(exception.getMessage())
        .contains("ReflectionAccessFilter does not permit using reflection or Unsafe");
  }

  @Test
  public void testGetWithBlockInaccessibleFilterPreventsUnsafe() {
    List<ReflectionAccessFilter> filters = new ArrayList<>();
    filters.add(
        rawClass -> {
          if (rawClass == ClassWithoutNoArgsConstructor.class) {
            return FilterResult.BLOCK_INACCESSIBLE;
          }
          return FilterResult.INDECISIVE;
        });
    ConstructorConstructor cc = new ConstructorConstructor(Collections.emptyMap(), true, filters);

    ObjectConstructor<ClassWithoutNoArgsConstructor> constructor =
        cc.get(TypeToken.get(ClassWithoutNoArgsConstructor.class));
    JsonIOException exception = assertThrows(JsonIOException.class, constructor::construct);
    assertThat(exception.getMessage())
        .contains("ReflectionAccessFilter does not permit using reflection or Unsafe");
  }

  // ========== toString Tests ==========

  @Test
  public void testToStringWithEmptyInstanceCreators() {
    ConstructorConstructor cc =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    assertThat(cc.toString()).isEqualTo("{}");
  }

  @Test
  public void testToStringWithInstanceCreators() {
    Map<Type, InstanceCreator<?>> instanceCreators = new LinkedHashMap<>();
    instanceCreators.put(String.class, type -> "string");
    instanceCreators.put(Integer.class, type -> 42);
    ConstructorConstructor cc =
        new ConstructorConstructor(instanceCreators, true, Collections.emptyList());
    String result = cc.toString();
    assertThat(result).contains("String");
    assertThat(result).contains("Integer");
  }

  // ========== get(TypeToken) Tests - EnumSet and EnumMap edge cases ==========

  @Test
  public void testGetWithEnumSetInvalidTypeArgument() {
    ConstructorConstructor cc =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    // Create a parameterized type with a non-Class type argument
    Type fakeEnumSetType =
        new ParameterizedType() {
          @Override
          public Type[] getActualTypeArguments() {
            // Return a type that is not a Class
            return new Type[] {new TypeToken<List<String>>() {}.getType()};
          }

          @Override
          public Type getRawType() {
            return EnumSet.class;
          }

          @Override
          public Type getOwnerType() {
            return null;
          }
        };

    @SuppressWarnings("unchecked")
    TypeToken<EnumSet<?>> typeToken = (TypeToken<EnumSet<?>>) TypeToken.get(fakeEnumSetType);
    ObjectConstructor<EnumSet<?>> constructor = cc.get(typeToken);
    JsonIOException exception = assertThrows(JsonIOException.class, constructor::construct);
    assertThat(exception.getMessage()).contains("Invalid EnumSet type");
  }

  @Test
  public void testGetWithEnumMapInvalidTypeArgument() {
    ConstructorConstructor cc =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    // Create a parameterized type with a non-Class type argument
    Type fakeEnumMapType =
        new ParameterizedType() {
          @Override
          public Type[] getActualTypeArguments() {
            // Return a type that is not a Class
            return new Type[] {new TypeToken<List<String>>() {}.getType(), String.class};
          }

          @Override
          public Type getRawType() {
            return EnumMap.class;
          }

          @Override
          public Type getOwnerType() {
            return null;
          }
        };

    @SuppressWarnings("unchecked")
    TypeToken<EnumMap<?, ?>> typeToken = (TypeToken<EnumMap<?, ?>>) TypeToken.get(fakeEnumMapType);
    ObjectConstructor<EnumMap<?, ?>> constructor = cc.get(typeToken);
    JsonIOException exception = assertThrows(JsonIOException.class, constructor::construct);
    assertThat(exception.getMessage()).contains("Invalid EnumMap type");
  }

  // ========== Instance Creator Priority Tests ==========

  @Test
  public void testExactTypeInstanceCreatorTakesPrecedenceOverRawType() {
    Map<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    TypeToken<GenericClass<String>> exactTypeToken = new TypeToken<GenericClass<String>>() {};
    instanceCreators.put(exactTypeToken.getType(), type -> new GenericClass<>("exact"));
    instanceCreators.put(GenericClass.class, type -> new GenericClass<>("raw"));

    ConstructorConstructor cc =
        new ConstructorConstructor(instanceCreators, true, Collections.emptyList());

    ObjectConstructor<GenericClass<String>> constructor = cc.get(exactTypeToken);
    GenericClass<String> instance = constructor.construct();
    assertThat(instance.value).isEqualTo("exact");
  }

  @Test
  public void testInstanceCreatorTakesPrecedenceOverDefaultConstructor() {
    Map<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    instanceCreators.put(
        ClassWithNoArgsConstructor.class,
        type -> {
          ClassWithNoArgsConstructor obj = new ClassWithNoArgsConstructor();
          obj.initialized = false; // Override default initialization
          return obj;
        });

    ConstructorConstructor cc =
        new ConstructorConstructor(instanceCreators, true, Collections.emptyList());

    ObjectConstructor<ClassWithNoArgsConstructor> constructor =
        cc.get(TypeToken.get(ClassWithNoArgsConstructor.class));
    ClassWithNoArgsConstructor instance = constructor.construct();
    assertThat(instance.initialized).isFalse();
  }

  // ========== Edge Cases ==========

  @Test
  public void testGetCalledMultipleTimes() {
    ConstructorConstructor cc =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());

    ObjectConstructor<ClassWithNoArgsConstructor> constructor1 =
        cc.get(TypeToken.get(ClassWithNoArgsConstructor.class));
    ObjectConstructor<ClassWithNoArgsConstructor> constructor2 =
        cc.get(TypeToken.get(ClassWithNoArgsConstructor.class));

    ClassWithNoArgsConstructor instance1 = constructor1.construct();
    ClassWithNoArgsConstructor instance2 = constructor2.construct();

    assertThat(instance1).isNotSameInstanceAs(instance2);
  }

  @Test
  public void testConstructorCreatesNewInstanceEachTime() {
    ConstructorConstructor cc =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());

    ObjectConstructor<ClassWithNoArgsConstructor> constructor =
        cc.get(TypeToken.get(ClassWithNoArgsConstructor.class));

    ClassWithNoArgsConstructor instance1 = constructor.construct();
    ClassWithNoArgsConstructor instance2 = constructor.construct();

    assertThat(instance1).isNotSameInstanceAs(instance2);
  }

  // ========== Test Helper Classes ==========

  enum TestEnum {
    VALUE1,
    VALUE2,
    VALUE3
  }

  static class SimpleClass {
    String value;

    SimpleClass(String value) {
      this.value = value;
    }
  }

  static class GenericClass<T> {
    T value;

    GenericClass(T value) {
      this.value = value;
    }
  }

  public static class ClassWithNoArgsConstructor {
    public boolean initialized = true;

    public ClassWithNoArgsConstructor() {}
  }

  static class ClassWithPrivateConstructor {
    String value;

    private ClassWithPrivateConstructor() {
      this.value = "private";
    }
  }

  static class ClassWithoutNoArgsConstructor {
    String value;

    ClassWithoutNoArgsConstructor(String value) {
      this.value = value;
    }
  }

  abstract static class AbstractTestClass {
    abstract void doSomething();
  }
}
