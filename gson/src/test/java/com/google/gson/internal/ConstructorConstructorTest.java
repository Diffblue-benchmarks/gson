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

import com.google.gson.InstanceCreator;
import com.google.gson.JsonIOException;
import com.google.gson.ReflectionAccessFilter;
import com.google.gson.ReflectionAccessFilter.FilterResult;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
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

public class ConstructorConstructorTest {

  private enum TestEnum {
    VALUE1, VALUE2
  }

  private static class SimpleClass {
    public SimpleClass() {}
  }

  private static class NoArgsConstructorClass {
    public NoArgsConstructorClass() {}
  }

  private static class PrivateConstructorClass {
    private PrivateConstructorClass() {}
  }

  private interface TestInterface {}

  private abstract static class AbstractClass {}

  @Test
  public void testConstructor() {
    Map<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    List<ReflectionAccessFilter> reflectionFilters = new ArrayList<>();

    ConstructorConstructor cc = new ConstructorConstructor(instanceCreators, true, reflectionFilters);

    assertThat(cc).isNotNull();
    assertThat(cc.toString()).isEqualTo(instanceCreators.toString());
  }

  @Test
  public void testCheckInstantiableWithInterface() {
    String result = ConstructorConstructor.checkInstantiable(TestInterface.class);

    assertThat(result).isNotNull();
    assertThat(result).contains("Interfaces can't be instantiated");
    assertThat(result).contains(TestInterface.class.getName());
  }

  @Test
  public void testCheckInstantiableWithAbstractClass() {
    String result = ConstructorConstructor.checkInstantiable(AbstractClass.class);

    assertThat(result).isNotNull();
    assertThat(result).contains("Abstract classes can't be instantiated");
    assertThat(result).contains(AbstractClass.class.getName());
  }

  @Test
  public void testCheckInstantiableWithConcreteClass() {
    String result = ConstructorConstructor.checkInstantiable(SimpleClass.class);

    assertThat(result).isNull();
  }

  @Test
  public void testGetWithInstanceCreatorForType() {
    Map<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    SimpleClass instance = new SimpleClass();
    instanceCreators.put(SimpleClass.class, new InstanceCreator<SimpleClass>() {
      @Override
      public SimpleClass createInstance(Type type) {
        return instance;
      }
    });

    ConstructorConstructor cc = new ConstructorConstructor(instanceCreators, true, Collections.<ReflectionAccessFilter>emptyList());
    ObjectConstructor<SimpleClass> constructor = cc.get(TypeToken.get(SimpleClass.class));

    assertThat(constructor).isNotNull();
    assertThat(constructor.construct()).isSameInstanceAs(instance);
  }

  @Test
  public void testGetWithInstanceCreatorForRawType() {
    Map<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    SimpleClass instance = new SimpleClass();
    instanceCreators.put(SimpleClass.class, new InstanceCreator<SimpleClass>() {
      @Override
      public SimpleClass createInstance(Type type) {
        return instance;
      }
    });

    ConstructorConstructor cc = new ConstructorConstructor(instanceCreators, true, Collections.<ReflectionAccessFilter>emptyList());
    ObjectConstructor<SimpleClass> constructor = cc.get(TypeToken.get(SimpleClass.class));

    assertThat(constructor).isNotNull();
    assertThat(constructor.construct()).isSameInstanceAs(instance);
  }

  @Test
  public void testGetWithEnumSet() {
    ConstructorConstructor cc = new ConstructorConstructor(
        Collections.<Type, InstanceCreator<?>>emptyMap(), true, Collections.<ReflectionAccessFilter>emptyList());

    TypeToken<EnumSet<TestEnum>> typeToken = new TypeToken<EnumSet<TestEnum>>() {};
    ObjectConstructor<EnumSet<TestEnum>> constructor = cc.get(typeToken);

    assertThat(constructor).isNotNull();
    EnumSet<TestEnum> result = constructor.construct();
    assertThat(result).isNotNull();
    assertThat(result).isEmpty();
  }

  @Test
  @SuppressWarnings({"rawtypes", "unchecked"})
  public void testGetWithEnumSetNonParameterized() {
    ConstructorConstructor cc = new ConstructorConstructor(
        Collections.<Type, InstanceCreator<?>>emptyMap(), true, Collections.<ReflectionAccessFilter>emptyList());

    ObjectConstructor<EnumSet> constructor = cc.get(TypeToken.get(EnumSet.class));

    assertThat(constructor).isNotNull();
    try {
      constructor.construct();
      assertThat(false).isTrue(); // Should not reach here
    } catch (JsonIOException e) {
      assertThat(e.getMessage()).contains("Invalid EnumSet type");
    }
  }

  @Test
  public void testGetWithEnumMap() {
    ConstructorConstructor cc = new ConstructorConstructor(
        Collections.<Type, InstanceCreator<?>>emptyMap(), true, Collections.<ReflectionAccessFilter>emptyList());

    TypeToken<EnumMap<TestEnum, String>> typeToken = new TypeToken<EnumMap<TestEnum, String>>() {};
    ObjectConstructor<EnumMap<TestEnum, String>> constructor = cc.get(typeToken);

    assertThat(constructor).isNotNull();
    EnumMap<TestEnum, String> result = constructor.construct();
    assertThat(result).isNotNull();
    assertThat(result).isEmpty();
  }

  @Test
  @SuppressWarnings({"rawtypes", "unchecked"})
  public void testGetWithEnumMapNonParameterized() {
    ConstructorConstructor cc = new ConstructorConstructor(
        Collections.<Type, InstanceCreator<?>>emptyMap(), true, Collections.<ReflectionAccessFilter>emptyList());

    ObjectConstructor<EnumMap> constructor = cc.get(TypeToken.get(EnumMap.class));

    assertThat(constructor).isNotNull();
    try {
      constructor.construct();
      assertThat(false).isTrue(); // Should not reach here
    } catch (JsonIOException e) {
      assertThat(e.getMessage()).contains("Invalid EnumMap type");
    }
  }

  @Test
  public void testGetWithDefaultConstructor() {
    ConstructorConstructor cc = new ConstructorConstructor(
        Collections.<Type, InstanceCreator<?>>emptyMap(), true, Collections.<ReflectionAccessFilter>emptyList());

    ObjectConstructor<NoArgsConstructorClass> constructor = cc.get(TypeToken.get(NoArgsConstructorClass.class));

    assertThat(constructor).isNotNull();
    NoArgsConstructorClass result = constructor.construct();
    assertThat(result).isNotNull();
  }

  @Test
  public void testGetWithAbstractClass() {
    ConstructorConstructor cc = new ConstructorConstructor(
        Collections.<Type, InstanceCreator<?>>emptyMap(), true, Collections.<ReflectionAccessFilter>emptyList());

    ObjectConstructor<AbstractClass> constructor = cc.get(TypeToken.get(AbstractClass.class));

    assertThat(constructor).isNotNull();
    try {
      constructor.construct();
      assertThat(false).isTrue(); // Should not reach here
    } catch (JsonIOException e) {
      assertThat(e.getMessage()).contains("Abstract classes can't be instantiated");
    }
  }

  @Test
  public void testGetWithInterface() {
    ConstructorConstructor cc = new ConstructorConstructor(
        Collections.<Type, InstanceCreator<?>>emptyMap(), true, Collections.<ReflectionAccessFilter>emptyList());

    ObjectConstructor<TestInterface> constructor = cc.get(TypeToken.get(TestInterface.class));

    assertThat(constructor).isNotNull();
    try {
      constructor.construct();
      assertThat(false).isTrue(); // Should not reach here
    } catch (JsonIOException e) {
      assertThat(e.getMessage()).contains("Interfaces can't be instantiated");
    }
  }

  @Test
  @SuppressWarnings({"rawtypes", "unchecked"})
  public void testGetWithList() {
    ConstructorConstructor cc = new ConstructorConstructor(
        Collections.<Type, InstanceCreator<?>>emptyMap(), true, Collections.<ReflectionAccessFilter>emptyList());

    ObjectConstructor<List> constructor = cc.get(TypeToken.get(List.class));

    assertThat(constructor).isNotNull();
    List result = constructor.construct();
    assertThat(result).isNotNull();
    assertThat(result).isInstanceOf(ArrayList.class);
  }

  @Test
  @SuppressWarnings({"rawtypes", "unchecked"})
  public void testGetWithCollection() {
    ConstructorConstructor cc = new ConstructorConstructor(
        Collections.<Type, InstanceCreator<?>>emptyMap(), true, Collections.<ReflectionAccessFilter>emptyList());

    ObjectConstructor<Collection> constructor = cc.get(TypeToken.get(Collection.class));

    assertThat(constructor).isNotNull();
    Collection result = constructor.construct();
    assertThat(result).isNotNull();
    assertThat(result).isInstanceOf(ArrayList.class);
  }

  @Test
  @SuppressWarnings({"rawtypes", "unchecked"})
  public void testGetWithSet() {
    ConstructorConstructor cc = new ConstructorConstructor(
        Collections.<Type, InstanceCreator<?>>emptyMap(), true, Collections.<ReflectionAccessFilter>emptyList());

    ObjectConstructor<Set> constructor = cc.get(TypeToken.get(Set.class));

    assertThat(constructor).isNotNull();
    Set result = constructor.construct();
    assertThat(result).isNotNull();
    assertThat(result).isInstanceOf(LinkedHashSet.class);
  }

  @Test
  @SuppressWarnings({"rawtypes", "unchecked"})
  public void testGetWithSortedSet() {
    ConstructorConstructor cc = new ConstructorConstructor(
        Collections.<Type, InstanceCreator<?>>emptyMap(), true, Collections.<ReflectionAccessFilter>emptyList());

    ObjectConstructor<SortedSet> constructor = cc.get(TypeToken.get(SortedSet.class));

    assertThat(constructor).isNotNull();
    SortedSet result = constructor.construct();
    assertThat(result).isNotNull();
    assertThat(result).isInstanceOf(TreeSet.class);
  }

  @Test
  @SuppressWarnings({"rawtypes", "unchecked"})
  public void testGetWithQueue() {
    ConstructorConstructor cc = new ConstructorConstructor(
        Collections.<Type, InstanceCreator<?>>emptyMap(), true, Collections.<ReflectionAccessFilter>emptyList());

    ObjectConstructor<Queue> constructor = cc.get(TypeToken.get(Queue.class));

    assertThat(constructor).isNotNull();
    Queue result = constructor.construct();
    assertThat(result).isNotNull();
    assertThat(result).isInstanceOf(ArrayDeque.class);
  }

  @Test
  @SuppressWarnings({"rawtypes", "unchecked"})
  public void testGetWithMap() {
    ConstructorConstructor cc = new ConstructorConstructor(
        Collections.<Type, InstanceCreator<?>>emptyMap(), true, Collections.<ReflectionAccessFilter>emptyList());

    ObjectConstructor<Map> constructor = cc.get(TypeToken.get(Map.class));

    assertThat(constructor).isNotNull();
    Map result = constructor.construct();
    assertThat(result).isNotNull();
    assertThat(result).isInstanceOf(LinkedTreeMap.class);
  }

  @Test
  public void testGetWithMapStringKey() {
    ConstructorConstructor cc = new ConstructorConstructor(
        Collections.<Type, InstanceCreator<?>>emptyMap(), true, Collections.<ReflectionAccessFilter>emptyList());

    TypeToken<Map<String, Object>> typeToken = new TypeToken<Map<String, Object>>() {};
    ObjectConstructor<Map<String, Object>> constructor = cc.get(typeToken);

    assertThat(constructor).isNotNull();
    Map<String, Object> result = constructor.construct();
    assertThat(result).isNotNull();
    assertThat(result).isInstanceOf(LinkedTreeMap.class);
  }

  @Test
  @SuppressWarnings({"rawtypes", "unchecked"})
  public void testGetWithSortedMap() {
    ConstructorConstructor cc = new ConstructorConstructor(
        Collections.<Type, InstanceCreator<?>>emptyMap(), true, Collections.<ReflectionAccessFilter>emptyList());

    ObjectConstructor<SortedMap> constructor = cc.get(TypeToken.get(SortedMap.class));

    assertThat(constructor).isNotNull();
    SortedMap result = constructor.construct();
    assertThat(result).isNotNull();
    assertThat(result).isInstanceOf(TreeMap.class);
  }

  @Test
  @SuppressWarnings({"rawtypes", "unchecked"})
  public void testGetWithConcurrentMap() {
    ConstructorConstructor cc = new ConstructorConstructor(
        Collections.<Type, InstanceCreator<?>>emptyMap(), true, Collections.<ReflectionAccessFilter>emptyList());

    ObjectConstructor<ConcurrentMap> constructor = cc.get(TypeToken.get(ConcurrentMap.class));

    assertThat(constructor).isNotNull();
    ConcurrentMap result = constructor.construct();
    assertThat(result).isNotNull();
    assertThat(result).isInstanceOf(ConcurrentHashMap.class);
  }

  @Test
  @SuppressWarnings({"rawtypes", "unchecked"})
  public void testGetWithConcurrentNavigableMap() {
    ConstructorConstructor cc = new ConstructorConstructor(
        Collections.<Type, InstanceCreator<?>>emptyMap(), true, Collections.<ReflectionAccessFilter>emptyList());

    ObjectConstructor<ConcurrentNavigableMap> constructor = cc.get(TypeToken.get(ConcurrentNavigableMap.class));

    assertThat(constructor).isNotNull();
    ConcurrentNavigableMap result = constructor.construct();
    assertThat(result).isNotNull();
    assertThat(result).isInstanceOf(ConcurrentSkipListMap.class);
  }

  @Test
  public void testGetWithPrivateConstructorAllowFilter() {
    List<ReflectionAccessFilter> filters = new ArrayList<>();
    filters.add(new ReflectionAccessFilter() {
      @Override
      public FilterResult check(Class<?> rawClass) {
        return FilterResult.ALLOW;
      }
    });

    ConstructorConstructor cc = new ConstructorConstructor(
        Collections.<Type, InstanceCreator<?>>emptyMap(), true, filters);

    ObjectConstructor<PrivateConstructorClass> constructor = cc.get(TypeToken.get(PrivateConstructorClass.class));

    assertThat(constructor).isNotNull();
    PrivateConstructorClass result = constructor.construct();
    assertThat(result).isNotNull();
  }

  @Test
  public void testGetWithPrivateConstructorBlockInaccessibleFilter() {
    List<ReflectionAccessFilter> filters = new ArrayList<>();
    filters.add(new ReflectionAccessFilter() {
      @Override
      public FilterResult check(Class<?> rawClass) {
        return FilterResult.BLOCK_INACCESSIBLE;
      }
    });

    ConstructorConstructor cc = new ConstructorConstructor(
        Collections.<Type, InstanceCreator<?>>emptyMap(), true, filters);

    ObjectConstructor<PrivateConstructorClass> constructor = cc.get(TypeToken.get(PrivateConstructorClass.class));

    assertThat(constructor).isNotNull();
    try {
      constructor.construct();
      assertThat(false).isTrue(); // Should not reach here
    } catch (JsonIOException e) {
      assertThat(e.getMessage()).contains("Unable to invoke no-args constructor");
    }
  }

  @Test
  public void testGetWithPrivateConstructorBlockAllFilter() {
    List<ReflectionAccessFilter> filters = new ArrayList<>();
    filters.add(new ReflectionAccessFilter() {
      @Override
      public FilterResult check(Class<?> rawClass) {
        return FilterResult.BLOCK_ALL;
      }
    });

    ConstructorConstructor cc = new ConstructorConstructor(
        Collections.<Type, InstanceCreator<?>>emptyMap(), true, filters);

    ObjectConstructor<PrivateConstructorClass> constructor = cc.get(TypeToken.get(PrivateConstructorClass.class));

    assertThat(constructor).isNotNull();
    try {
      constructor.construct();
      assertThat(false).isTrue(); // Should not reach here
    } catch (JsonIOException e) {
      assertThat(e.getMessage()).contains("constructor is not accessible");
      assertThat(e.getMessage()).contains("ReflectionAccessFilter does not permit making it accessible");
    }
  }

  @Test
  public void testGetWithDisallowUnsafe() {
    ConstructorConstructor cc = new ConstructorConstructor(
        Collections.<Type, InstanceCreator<?>>emptyMap(), false, Collections.<ReflectionAccessFilter>emptyList());

    ObjectConstructor<PrivateConstructorClass> constructor = cc.get(TypeToken.get(PrivateConstructorClass.class), false);

    assertThat(constructor).isNotNull();
    // With ALLOW filter, the private constructor can still be made accessible
    PrivateConstructorClass result = constructor.construct();
    assertThat(result).isNotNull();
  }

  @Test
  public void testGetDelegatesToGetWithAllowUnsafe() {
    ConstructorConstructor cc = new ConstructorConstructor(
        Collections.<Type, InstanceCreator<?>>emptyMap(), true, Collections.<ReflectionAccessFilter>emptyList());

    ObjectConstructor<SimpleClass> constructor = cc.get(TypeToken.get(SimpleClass.class));

    assertThat(constructor).isNotNull();
    SimpleClass result = constructor.construct();
    assertThat(result).isNotNull();
  }

  @Test
  public void testToString() {
    Map<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    instanceCreators.put(String.class, new InstanceCreator<String>() {
      @Override
      public String createInstance(Type type) {
        return "test";
      }
    });

    ConstructorConstructor cc = new ConstructorConstructor(instanceCreators, true, Collections.<ReflectionAccessFilter>emptyList());

    assertThat(cc.toString()).isEqualTo(instanceCreators.toString());
  }

  @Test
  public void testGetWithUnsafeAllocator() {
    ConstructorConstructor cc = new ConstructorConstructor(
        Collections.<Type, InstanceCreator<?>>emptyMap(), true, Collections.<ReflectionAccessFilter>emptyList());

    ObjectConstructor<PrivateConstructorClass> constructor = cc.get(TypeToken.get(PrivateConstructorClass.class));

    assertThat(constructor).isNotNull();
    PrivateConstructorClass result = constructor.construct();
    assertThat(result).isNotNull();
  }

  @Test
  public void testGetWithBlockAllFilterNoUnsafe() {
    List<ReflectionAccessFilter> filters = new ArrayList<>();
    filters.add(new ReflectionAccessFilter() {
      @Override
      public FilterResult check(Class<?> rawClass) {
        return FilterResult.BLOCK_ALL;
      }
    });

    ConstructorConstructor cc = new ConstructorConstructor(
        Collections.<Type, InstanceCreator<?>>emptyMap(), true, filters);

    ObjectConstructor<PrivateConstructorClass> constructor = cc.get(TypeToken.get(PrivateConstructorClass.class), false);

    assertThat(constructor).isNotNull();
    try {
      constructor.construct();
      assertThat(false).isTrue(); // Should not reach here
    } catch (JsonIOException e) {
      assertThat(e.getMessage()).contains("constructor is not accessible");
      assertThat(e.getMessage()).contains("ReflectionAccessFilter does not permit making it accessible");
    }
  }

  private static class ClassWithOnlyParameterizedConstructor {
    private final int value;

    ClassWithOnlyParameterizedConstructor(int value) {
      this.value = value;
    }
  }

  @Test
  public void testNewUnsafeAllocatorWithUnsafeDisabled() {
    ConstructorConstructor cc = new ConstructorConstructor(
        Collections.<Type, InstanceCreator<?>>emptyMap(), false, Collections.<ReflectionAccessFilter>emptyList());

    ObjectConstructor<ClassWithOnlyParameterizedConstructor> constructor =
        cc.get(TypeToken.get(ClassWithOnlyParameterizedConstructor.class));

    assertThat(constructor).isNotNull();
    try {
      constructor.construct();
      // Should not reach here
      throw new AssertionError("Expected JsonIOException");
    } catch (JsonIOException e) {
      assertThat(e.getMessage()).contains("Unable to create instance of");
      assertThat(e.getMessage()).contains("usage of JDK Unsafe is disabled");
      assertThat(e.getMessage()).contains("Registering an InstanceCreator");
    }
  }

  private static class ConstructorThrowsException {
    ConstructorThrowsException() {
      throw new IllegalStateException("Constructor exception");
    }
  }

  @Test
  public void testNewDefaultConstructorWithInvocationTargetException() {
    ConstructorConstructor cc = new ConstructorConstructor(
        Collections.<Type, InstanceCreator<?>>emptyMap(), true, Collections.<ReflectionAccessFilter>emptyList());

    ObjectConstructor<ConstructorThrowsException> constructor =
        cc.get(TypeToken.get(ConstructorThrowsException.class));

    assertThat(constructor).isNotNull();
    try {
      constructor.construct();
      assertThat(false).isTrue(); // Should not reach here
    } catch (RuntimeException e) {
      assertThat(e.getMessage()).contains("Failed to invoke constructor");
      assertThat(e.getCause()).isInstanceOf(IllegalStateException.class);
      assertThat(e.getCause().getMessage()).isEqualTo("Constructor exception");
    }
  }
}
