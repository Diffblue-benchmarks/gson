/*
 * Copyright (C) 2022 Google Inc.
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
import static com.google.common.truth.Truth.assertWithMessage;
import static org.junit.Assert.assertThrows;

import com.google.gson.InstanceCreator;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.LinkedBlockingDeque;
import org.junit.Test;

public class ConstructorConstructorTest {
  private ConstructorConstructor constructorConstructor =
      new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());

  private abstract static class AbstractClass {
    @SuppressWarnings("unused")
    public AbstractClass() {}
  }

  private interface Interface {}

  /**
   * Verify that ConstructorConstructor does not try to invoke no-args constructor of abstract
   * class.
   */
  @Test
  public void testGet_AbstractClassNoArgConstructor() {
    ObjectConstructor<AbstractClass> constructor =
        constructorConstructor.get(TypeToken.get(AbstractClass.class));
    var e = assertThrows(RuntimeException.class, () -> constructor.construct());
    assertThat(e)
        .hasMessageThat()
        .isEqualTo(
            "Abstract classes can't be instantiated! Adjust the R8 configuration or register an"
                + " InstanceCreator or a TypeAdapter for this type. Class name:"
                + " com.google.gson.internal.ConstructorConstructorTest$AbstractClass\n"
                + "See https://github.com/google/gson/blob/main/Troubleshooting.md#r8-abstract-class");
  }

  @Test
  public void testGet_Interface() {
    ObjectConstructor<Interface> constructor =
        constructorConstructor.get(TypeToken.get(Interface.class));
    var e = assertThrows(RuntimeException.class, () -> constructor.construct());
    assertThat(e)
        .hasMessageThat()
        .isEqualTo(
            "Interfaces can't be instantiated! Register an InstanceCreator or a TypeAdapter for"
                + " this type. Interface name:"
                + " com.google.gson.internal.ConstructorConstructorTest$Interface");
  }

  @SuppressWarnings("serial")
  private static class CustomSortedSet<E> extends TreeSet<E> {
    // Removes default no-args constructor
    @SuppressWarnings("unused")
    CustomSortedSet(Void v) {}
  }

  @SuppressWarnings("serial")
  private static class CustomSet<E> extends HashSet<E> {
    // Removes default no-args constructor
    @SuppressWarnings("unused")
    CustomSet(Void v) {}
  }

  @SuppressWarnings("serial")
  private static class CustomQueue<E> extends LinkedBlockingDeque<E> {
    // Removes default no-args constructor
    @SuppressWarnings("unused")
    CustomQueue(Void v) {}
  }

  @SuppressWarnings("serial")
  private static class CustomList<E> extends ArrayList<E> {
    // Removes default no-args constructor
    @SuppressWarnings("unused")
    CustomList(Void v) {}
  }

  /**
   * Tests that creation of custom {@code Collection} subclasses without no-args constructor should
   * not use default JDK types (which would cause {@link ClassCastException}).
   *
   * <p>Currently this test is rather contrived because the instances created using Unsafe are not
   * usable because their fields are not properly initialized, but assume that user has custom
   * classes which would be functional.
   */
  @Test
  public void testCustomCollectionCreation() {
    Class<?>[] collectionTypes = {
      CustomSortedSet.class, CustomSet.class, CustomQueue.class, CustomList.class,
    };

    for (Class<?> collectionType : collectionTypes) {
      Object actual =
          constructorConstructor
              .get(TypeToken.getParameterized(collectionType, Integer.class))
              .construct();
      assertWithMessage(
              "Failed for " + collectionType + "; created instance of " + actual.getClass())
          .that(actual)
          .isInstanceOf(collectionType);
    }
  }

  private static interface CustomCollectionInterface extends Collection<String> {}

  private static interface CustomSetInterface extends Set<String> {}

  private static interface CustomListInterface extends List<String> {}

  @Test
  public void testCustomCollectionInterfaceCreation() {
    Class<?>[] interfaces = {
      CustomCollectionInterface.class, CustomSetInterface.class, CustomListInterface.class,
    };

    for (Class<?> interfaceType : interfaces) {
      var objectConstructor = constructorConstructor.get(TypeToken.get(interfaceType));
      var exception = assertThrows(RuntimeException.class, () -> objectConstructor.construct());
      assertThat(exception)
          .hasMessageThat()
          .isEqualTo(
              "Interfaces can't be instantiated! Register an InstanceCreator or a TypeAdapter"
                  + " for this type. Interface name: "
                  + interfaceType.getName());
    }
  }

  @Test
  public void testStringMapCreation() {
    // When creating raw Map should use Gson's LinkedTreeMap, assuming keys could be String
    Object actual = constructorConstructor.get(TypeToken.get(Map.class)).construct();
    assertThat(actual).isInstanceOf(LinkedTreeMap.class);

    // When creating a `Map<String, ...>` should use Gson's LinkedTreeMap
    actual = constructorConstructor.get(new TypeToken<Map<String, Integer>>() {}).construct();
    assertThat(actual).isInstanceOf(LinkedTreeMap.class);

    // But when explicitly requesting a JDK `LinkedHashMap<String, ...>` should use LinkedHashMap
    actual =
        constructorConstructor.get(new TypeToken<LinkedHashMap<String, Integer>>() {}).construct();
    assertThat(actual).isInstanceOf(LinkedHashMap.class);

    // For all Map types with non-String key, should use JDK LinkedHashMap by default
    // This is also done to avoid ClassCastException later, because Gson's LinkedTreeMap requires
    // that keys are Comparable
    Class<?>[] nonStringTypes = {Integer.class, CharSequence.class, Object.class};
    for (Class<?> keyType : nonStringTypes) {
      actual =
          constructorConstructor
              .get(TypeToken.getParameterized(Map.class, keyType, Integer.class))
              .construct();
      assertWithMessage(
              "Failed for key type " + keyType + "; created instance of " + actual.getClass())
          .that(actual)
          .isInstanceOf(LinkedHashMap.class);
    }
  }

  private enum MyEnum {}

  @SuppressWarnings("serial")
  private static class CustomEnumMap<K, V> extends EnumMap<MyEnum, V> {
    @SuppressWarnings("unused")
    CustomEnumMap(Void v) {
      super(MyEnum.class);
    }
  }

  @SuppressWarnings("serial")
  private static class CustomConcurrentNavigableMap<K, V> extends ConcurrentSkipListMap<K, V> {
    // Removes default no-args constructor
    @SuppressWarnings("unused")
    CustomConcurrentNavigableMap(Void v) {}
  }

  @SuppressWarnings("serial")
  private static class CustomConcurrentMap<K, V> extends ConcurrentHashMap<K, V> {
    // Removes default no-args constructor
    @SuppressWarnings("unused")
    CustomConcurrentMap(Void v) {}
  }

  @SuppressWarnings("serial")
  private static class CustomSortedMap<K, V> extends TreeMap<K, V> {
    // Removes default no-args constructor
    @SuppressWarnings("unused")
    CustomSortedMap(Void v) {}
  }

  @SuppressWarnings("serial")
  private static class CustomLinkedHashMap<K, V> extends LinkedHashMap<K, V> {
    // Removes default no-args constructor
    @SuppressWarnings("unused")
    CustomLinkedHashMap(Void v) {}
  }

  /**
   * Tests that creation of custom {@code Map} subclasses without no-args constructor should not use
   * default JDK types (which would cause {@link ClassCastException}).
   *
   * <p>Currently this test is rather contrived because the instances created using Unsafe are not
   * usable because their fields are not properly initialized, but assume that user has custom
   * classes which would be functional.
   */
  @Test
  public void testCustomMapCreation() {
    Class<?>[] mapTypes = {
      CustomEnumMap.class,
      CustomConcurrentNavigableMap.class,
      CustomConcurrentMap.class,
      CustomSortedMap.class,
      CustomLinkedHashMap.class,
    };

    for (Class<?> mapType : mapTypes) {
      Object actual =
          constructorConstructor
              .get(TypeToken.getParameterized(mapType, String.class, Integer.class))
              .construct();
      assertWithMessage("Failed for " + mapType + "; created instance of " + actual.getClass())
          .that(actual)
          .isInstanceOf(mapType);
    }
  }

  private static interface CustomMapInterface extends Map<String, Integer> {}

  @Test
  public void testCustomMapInterfaceCreation() {
    var objectConstructor = constructorConstructor.get(TypeToken.get(CustomMapInterface.class));
    var exception = assertThrows(RuntimeException.class, () -> objectConstructor.construct());
    assertThat(exception)
        .hasMessageThat()
        .isEqualTo(
            "Interfaces can't be instantiated! Register an InstanceCreator or a TypeAdapter"
                + " for this type. Interface name: "
                + CustomMapInterface.class.getName());
  }

  /**
   * Tests that raw EnumSet (without type parameters) throws JsonIOException with appropriate
   * message.
   */
  @Test
  public void testEnumSetRawTypeThrowsException() {
    @SuppressWarnings("rawtypes")
    ObjectConstructor<EnumSet> constructor =
        constructorConstructor.get(TypeToken.get(EnumSet.class));
    JsonIOException exception = assertThrows(JsonIOException.class, () -> constructor.construct());
    assertThat(exception).hasMessageThat().startsWith("Invalid EnumSet type:");
  }

  /**
   * Tests that raw EnumMap (without type parameters) throws JsonIOException with appropriate
   * message.
   */
  @Test
  public void testEnumMapRawTypeThrowsException() {
    @SuppressWarnings("rawtypes")
    ObjectConstructor<EnumMap> constructor =
        constructorConstructor.get(TypeToken.get(EnumMap.class));
    JsonIOException exception = assertThrows(JsonIOException.class, () -> constructor.construct());
    assertThat(exception).hasMessageThat().startsWith("Invalid EnumMap type:");
  }

  /**
   * Tests that EnumSet with WildcardType element throws JsonIOException because the element type is
   * not a Class.
   */
  @Test
  public void testEnumSetWithWildcardTypeThrowsException() {
    // Create a type like EnumSet<? extends MyEnum>
    WildcardType wildcardType = GsonTypes.subtypeOf(MyEnum.class);
    Type enumSetType = GsonTypes.newParameterizedTypeWithOwner(null, EnumSet.class, wildcardType);

    @SuppressWarnings("unchecked")
    TypeToken<EnumSet<?>> typeToken = (TypeToken<EnumSet<?>>) TypeToken.get(enumSetType);
    ObjectConstructor<EnumSet<?>> constructor = constructorConstructor.get(typeToken);

    JsonIOException exception = assertThrows(JsonIOException.class, () -> constructor.construct());
    assertThat(exception).hasMessageThat().startsWith("Invalid EnumSet type:");
  }

  /**
   * Tests that EnumMap with WildcardType key throws JsonIOException because the key type is not a
   * Class.
   */
  @Test
  public void testEnumMapWithWildcardKeyTypeThrowsException() {
    // Create a type like EnumMap<? extends MyEnum, String>
    WildcardType wildcardType = GsonTypes.subtypeOf(MyEnum.class);
    Type enumMapType =
        GsonTypes.newParameterizedTypeWithOwner(null, EnumMap.class, wildcardType, String.class);

    @SuppressWarnings("unchecked")
    TypeToken<EnumMap<?, String>> typeToken =
        (TypeToken<EnumMap<?, String>>) TypeToken.get(enumMapType);
    ObjectConstructor<EnumMap<?, String>> constructor = constructorConstructor.get(typeToken);

    JsonIOException exception = assertThrows(JsonIOException.class, () -> constructor.construct());
    assertThat(exception).hasMessageThat().startsWith("Invalid EnumMap type:");
  }

  /** Tests that ConstructorConstructor.toString() returns the instanceCreators map as string. */
  @Test
  public void testToString() {
    // Create a ConstructorConstructor with empty instanceCreators
    ConstructorConstructor emptyConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    assertThat(emptyConstructor.toString()).isEqualTo("{}");

    // Create a ConstructorConstructor with one instanceCreator
    Map<Type, InstanceCreator<?>> creators = new HashMap<>();
    creators.put(String.class, type -> "test");
    ConstructorConstructor withCreator =
        new ConstructorConstructor(creators, true, Collections.emptyList());
    assertThat(withCreator.toString()).contains("class java.lang.String");
  }

  /**
   * Tests that when JDK Unsafe is disabled and the class has no declared constructors, an
   * appropriate error message is provided mentioning R8 configuration.
   */
  @Test
  public void testUnsafeDisabledWithNoConstructorsShowsR8Message() {
    // Create ConstructorConstructor with useJdkUnsafe=false
    ConstructorConstructor noUnsafeConstructor =
        new ConstructorConstructor(Collections.emptyMap(), false, Collections.emptyList());

    // Use a custom class without a default no-args constructor
    @SuppressWarnings({"unchecked", "rawtypes"})
    ObjectConstructor<CustomList> constructor =
        noUnsafeConstructor.get(TypeToken.get(CustomList.class));

    JsonIOException exception = assertThrows(JsonIOException.class, () -> constructor.construct());
    assertThat(exception).hasMessageThat().contains("Unable to create instance of");
    assertThat(exception).hasMessageThat().contains("usage of JDK Unsafe is disabled");
  }

  /**
   * Tests the edge case in hasStringKeyType where a ParameterizedType has no type arguments. This
   * is an unusual case that can occur with some custom Type implementations.
   */
  @Test
  public void testMapWithEmptyTypeArguments() {
    // Create a custom ParameterizedType with empty type arguments
    ParameterizedType emptyArgsType =
        new ParameterizedType() {
          @Override
          public Type[] getActualTypeArguments() {
            return new Type[0];
          }

          @Override
          public Type getRawType() {
            return Map.class;
          }

          @Override
          public Type getOwnerType() {
            return null;
          }
        };

    @SuppressWarnings("unchecked")
    TypeToken<Map<?, ?>> typeToken = (TypeToken<Map<?, ?>>) TypeToken.get(emptyArgsType);
    ObjectConstructor<Map<?, ?>> constructor = constructorConstructor.get(typeToken);

    // Should create a LinkedHashMap (not LinkedTreeMap) because hasStringKeyType returns false
    Object result = constructor.construct();
    assertThat(result).isInstanceOf(LinkedHashMap.class);
  }

  /** Helper class that throws in constructor to test exception handling. */
  private static class ThrowingConstructorClass {
    @SuppressWarnings("unused")
    public ThrowingConstructorClass() {
      throw new RuntimeException("Constructor failed intentionally");
    }
  }

  /** Tests that InvocationTargetException from constructor is properly wrapped. */
  @Test
  public void testConstructorThrowsExceptionIsWrapped() {
    ObjectConstructor<ThrowingConstructorClass> constructor =
        constructorConstructor.get(TypeToken.get(ThrowingConstructorClass.class));

    RuntimeException exception =
        assertThrows(RuntimeException.class, () -> constructor.construct());
    assertThat(exception)
        .hasMessageThat()
        .contains(
            "Failed to invoke constructor"
                + " 'com.google.gson.internal.ConstructorConstructorTest$ThrowingConstructorClass()'");
    assertThat(exception.getCause()).isInstanceOf(RuntimeException.class);
    assertThat(exception.getCause()).hasMessageThat().isEqualTo("Constructor failed intentionally");
  }
}
