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
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import org.junit.Test;

/** Unit tests for {@link ConstructorConstructor}. */
public final class ConstructorConstructorTest {

  private static ConstructorConstructor newConstructorConstructor() {
    return new ConstructorConstructor(
        Collections.<Type, InstanceCreator<?>>emptyMap(),
        true,
        Collections.<ReflectionAccessFilter>emptyList());
  }

  // ---- checkInstantiable ----

  @Test
  public void testCheckInstantiableInterface() {
    String result = ConstructorConstructor.checkInstantiable(Runnable.class);
    assertThat(result).contains("Interfaces can't be instantiated");
    assertThat(result).contains("Runnable");
  }

  @Test
  public void testCheckInstantiableAbstractClass() {
    String result = ConstructorConstructor.checkInstantiable(AbstractBase.class);
    assertThat(result).contains("Abstract classes can't be instantiated");
    assertThat(result).contains("AbstractBase");
  }

  @Test
  public void testCheckInstantiableConcreteClass() {
    String result = ConstructorConstructor.checkInstantiable(SimpleBean.class);
    assertThat(result).isNull();
  }

  // ---- constructor ----

  @Test
  public void testConstructorConstructorInit() {
    Map<Type, InstanceCreator<?>> creators = new HashMap<>();
    creators.put(SimpleBean.class, type -> new SimpleBean());
    ConstructorConstructor cc =
        new ConstructorConstructor(
            creators, false, Collections.<ReflectionAccessFilter>emptyList());
    assertThat(cc.toString()).contains("SimpleBean");
  }

  // ---- toString ----

  @Test
  public void testToString() {
    ConstructorConstructor cc = newConstructorConstructor();
    assertThat(cc.toString()).isNotNull();
  }

  // ---- get() via InstanceCreator ----

  @Test
  public void testGetWithTypeInstanceCreator() {
    Map<Type, InstanceCreator<?>> creators = new HashMap<>();
    SimpleBean instance = new SimpleBean();
    creators.put(SimpleBean.class, type -> instance);
    ConstructorConstructor cc =
        new ConstructorConstructor(
            creators, true, Collections.<ReflectionAccessFilter>emptyList());
    ObjectConstructor<SimpleBean> ctor = cc.get(TypeToken.get(SimpleBean.class));
    assertThat(ctor.construct()).isSameInstanceAs(instance);
  }

  @Test
  public void testGetWithRawTypeInstanceCreator() {
    Map<Type, InstanceCreator<?>> creators = new HashMap<>();
    SimpleBean instance = new SimpleBean();
    // Use raw type as key; parameterized type will fall through to raw type lookup
    creators.put(SimpleBean.class, type -> instance);
    ConstructorConstructor cc =
        new ConstructorConstructor(
            creators, true, Collections.<ReflectionAccessFilter>emptyList());
    // TypeToken with no parameterization — rawType == type, so it goes through type lookup
    // Force raw type lookup by using a subclass of the map that returns null for the exact type
    ConstructorConstructor cc2 =
        new ConstructorConstructor(
            new HashMap<Type, InstanceCreator<?>>() {
              private boolean firstLookup = true;

              @Override
              public InstanceCreator<?> get(Object key) {
                if (firstLookup) {
                  firstLookup = false;
                  return null; // skip exact type match
                }
                return creators.get(key);
              }
            },
            true,
            Collections.<ReflectionAccessFilter>emptyList());
    ObjectConstructor<SimpleBean> ctor = cc2.get(TypeToken.get(SimpleBean.class));
    assertThat(ctor.construct()).isSameInstanceAs(instance);
  }

  // ---- get() for default constructor ----

  @Test
  public void testGetDefaultConstructorForSimpleClass() {
    ConstructorConstructor cc = newConstructorConstructor();
    ObjectConstructor<SimpleBean> ctor = cc.get(TypeToken.get(SimpleBean.class));
    assertThat(ctor.construct()).isInstanceOf(SimpleBean.class);
  }

  // ---- get() for Collection types ----

  @Test
  public void testGetListType() {
    ConstructorConstructor cc = newConstructorConstructor();
    ObjectConstructor<List<Object>> ctor = cc.get(new TypeToken<List<Object>>() {});
    assertThat(ctor.construct()).isInstanceOf(ArrayList.class);
  }

  @Test
  public void testGetSetType() {
    ConstructorConstructor cc = newConstructorConstructor();
    ObjectConstructor<Set<Object>> ctor = cc.get(new TypeToken<Set<Object>>() {});
    assertThat(ctor.construct()).isInstanceOf(LinkedHashSet.class);
  }

  @Test
  public void testGetSortedSetType() {
    ConstructorConstructor cc = newConstructorConstructor();
    ObjectConstructor<SortedSet<Object>> ctor = cc.get(new TypeToken<SortedSet<Object>>() {});
    assertThat(ctor.construct()).isInstanceOf(TreeSet.class);
  }

  @Test
  public void testGetQueueType() {
    ConstructorConstructor cc = newConstructorConstructor();
    ObjectConstructor<Queue<Object>> ctor = cc.get(new TypeToken<Queue<Object>>() {});
    assertThat(ctor.construct()).isInstanceOf(ArrayDeque.class);
  }

  @Test
  public void testGetCollectionType() {
    ConstructorConstructor cc = newConstructorConstructor();
    ObjectConstructor<Collection<Object>> ctor = cc.get(new TypeToken<Collection<Object>>() {});
    assertThat(ctor.construct()).isInstanceOf(ArrayList.class);
  }

  // ---- get() for Map types ----

  @Test
  public void testGetMapType() {
    ConstructorConstructor cc = newConstructorConstructor();
    // Non-parameterized Map → hasStringKeyType returns true → LinkedTreeMap
    ObjectConstructor<Map<String, Object>> ctor =
        cc.get(new TypeToken<Map<String, Object>>() {});
    assertThat(ctor.construct()).isInstanceOf(LinkedTreeMap.class);
  }

  @Test
  public void testGetLinkedHashMapType() {
    ConstructorConstructor cc = newConstructorConstructor();
    ObjectConstructor<LinkedHashMap<String, Object>> ctor =
        cc.get(new TypeToken<LinkedHashMap<String, Object>>() {});
    assertThat(ctor.construct()).isInstanceOf(LinkedHashMap.class);
  }

  @Test
  public void testGetSortedMapType() {
    ConstructorConstructor cc = newConstructorConstructor();
    ObjectConstructor<SortedMap<String, Object>> ctor =
        cc.get(new TypeToken<SortedMap<String, Object>>() {});
    assertThat(ctor.construct()).isInstanceOf(TreeMap.class);
  }

  @Test
  public void testGetConcurrentMapType() {
    ConstructorConstructor cc = newConstructorConstructor();
    // ConcurrentHashMap has a public no-args constructor, but rawType == ConcurrentHashMap
    // goes through newDefaultConstructor first. Test ConcurrentNavigableMap (interface) which
    // delegates to newMapConstructor
    ObjectConstructor<ConcurrentNavigableMap<String, Object>> ctor =
        cc.get(new TypeToken<ConcurrentNavigableMap<String, Object>>() {});
    assertThat(ctor.construct()).isInstanceOf(ConcurrentSkipListMap.class);
  }

  // ---- get() for EnumSet (special collection constructor) ----

  @Test
  public void testGetEnumSetParameterized() {
    ConstructorConstructor cc = newConstructorConstructor();
    TypeToken<EnumSet<MyEnum>> token = new TypeToken<EnumSet<MyEnum>>() {};
    ObjectConstructor<EnumSet<MyEnum>> ctor = cc.get(token);
    EnumSet<MyEnum> set = ctor.construct();
    assertThat(set).isInstanceOf(EnumSet.class);
    assertThat(set).isEmpty();
  }

  @Test
  public void testGetEnumSetRawThrows() {
    ConstructorConstructor cc = newConstructorConstructor();
    // Raw EnumSet (non-parameterized) — results in non-parameterized type → throws
    @SuppressWarnings("unchecked")
    ObjectConstructor<EnumSet<MyEnum>> ctor =
        (ObjectConstructor<EnumSet<MyEnum>>) (ObjectConstructor<?>) cc.get(TypeToken.get(EnumSet.class));
    assertThrows(JsonIOException.class, ctor::construct);
  }

  // ---- get() for EnumMap (special map constructor) ----

  @Test
  public void testGetEnumMapParameterized() {
    ConstructorConstructor cc = newConstructorConstructor();
    TypeToken<EnumMap<MyEnum, String>> token = new TypeToken<EnumMap<MyEnum, String>>() {};
    ObjectConstructor<EnumMap<MyEnum, String>> ctor = cc.get(token);
    EnumMap<MyEnum, String> map = ctor.construct();
    assertThat(map).isInstanceOf(EnumMap.class);
    assertThat(map).isEmpty();
  }

  @Test
  public void testGetEnumMapRawThrows() {
    ConstructorConstructor cc = newConstructorConstructor();
    @SuppressWarnings("unchecked")
    ObjectConstructor<EnumMap<MyEnum, String>> ctor =
        (ObjectConstructor<EnumMap<MyEnum, String>>)
            (ObjectConstructor<?>) cc.get(TypeToken.get(EnumMap.class));
    assertThrows(JsonIOException.class, ctor::construct);
  }

  // ---- get() for interface (non-collection) ----

  @Test
  public void testGetInterfaceThrows() {
    ConstructorConstructor cc = newConstructorConstructor();
    ObjectConstructor<Runnable> ctor = cc.get(TypeToken.get(Runnable.class));
    assertThrows(JsonIOException.class, ctor::construct);
  }

  // ---- get() for abstract class ----

  @Test
  public void testGetAbstractClassThrows() {
    ConstructorConstructor cc = newConstructorConstructor();
    ObjectConstructor<AbstractBase> ctor = cc.get(TypeToken.get(AbstractBase.class));
    assertThrows(JsonIOException.class, ctor::construct);
  }

  // ---- get() with allowUnsafe=false ----

  @Test
  public void testGetNoUnsafeForClassWithNoConstructorThrows() {
    ConstructorConstructor cc =
        new ConstructorConstructor(
            Collections.<Type, InstanceCreator<?>>emptyMap(),
            false,
            Collections.<ReflectionAccessFilter>emptyList());
    ObjectConstructor<NoArglessConstructorClass> ctor =
        cc.get(TypeToken.get(NoArglessConstructorClass.class), false);
    assertThrows(JsonIOException.class, ctor::construct);
  }

  // ---- newUnsafeAllocator when useJdkUnsafe=false ----

  @Test
  public void testGetUnsafeDisabledThrows() {
    ConstructorConstructor cc =
        new ConstructorConstructor(
            Collections.<Type, InstanceCreator<?>>emptyMap(),
            false,
            Collections.<ReflectionAccessFilter>emptyList());
    ObjectConstructor<NoArglessConstructorClass> ctor =
        cc.get(TypeToken.get(NoArglessConstructorClass.class));
    JsonIOException ex = assertThrows(JsonIOException.class, ctor::construct);
    assertThat(ex).hasMessageThat().contains("usage of JDK Unsafe is disabled");
  }

  // ---- newDefaultConstructor: BLOCK_ALL with public constructor (covers line 239) ----

  @Test
  public void testNewDefaultConstructorBlockAllPublicConstructorSucceeds() {
    ConstructorConstructor cc =
        new ConstructorConstructor(
            Collections.<Type, InstanceCreator<?>>emptyMap(),
            true,
            Collections.<ReflectionAccessFilter>singletonList(
                clazz -> FilterResult.BLOCK_ALL));
    ObjectConstructor<SimpleBean> ctor = cc.get(TypeToken.get(SimpleBean.class));
    assertThat(ctor.construct()).isInstanceOf(SimpleBean.class);
  }

  // ---- newDefaultConstructor: BLOCK_ALL with non-public constructor (covers lines 246, 253, 254) ----

  @Test
  public void testNewDefaultConstructorBlockAllNonPublicConstructorThrows() {
    ConstructorConstructor cc =
        new ConstructorConstructor(
            Collections.<Type, InstanceCreator<?>>emptyMap(),
            true,
            Collections.<ReflectionAccessFilter>singletonList(
                clazz -> FilterResult.BLOCK_ALL));
    ObjectConstructor<PackagePrivateCtorClass> ctor =
        cc.get(TypeToken.get(PackagePrivateCtorClass.class));
    JsonIOException ex = assertThrows(JsonIOException.class, ctor::construct);
    assertThat(ex).hasMessageThat().contains("Unable to invoke no-args constructor");
    assertThat(ex).hasMessageThat().contains("ReflectionAccessFilter");
  }

  // ---- newDefaultConstructor: constructor throws (covers lines 294, 297, 299, 301) ----

  @Test
  public void testNewDefaultConstructorThrowingConstructorWrapsException() {
    ConstructorConstructor cc = newConstructorConstructor();
    ObjectConstructor<ThrowingConstructorClass> ctor =
        cc.get(TypeToken.get(ThrowingConstructorClass.class));
    RuntimeException ex = assertThrows(RuntimeException.class, ctor::construct);
    assertThat(ex).hasMessageThat().contains("Failed to invoke constructor");
    assertThat(ex.getCause()).hasMessageThat().isEqualTo("constructor threw");
  }

  // ---- helper types ----

  static abstract class AbstractBase {}

  public static final class SimpleBean {
    public SimpleBean() {}
  }

  public static final class NoArglessConstructorClass {
    public NoArglessConstructorClass(String ignored) {}
  }

  public static final class PackagePrivateCtorClass {
    PackagePrivateCtorClass() {}
  }

  public static final class ThrowingConstructorClass {
    public ThrowingConstructorClass() {
      throw new RuntimeException("constructor threw");
    }
  }

  enum MyEnum {
    A,
    B
  }
}
