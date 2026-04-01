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

package com.google.gson.internal.bind;

import static com.google.common.truth.Truth.assertThat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.ReflectionAccessFilter;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link CollectionTypeAdapterFactory}.
 */
public final class CollectionTypeAdapterFactoryTest {

  private Gson gson;
  private CollectionTypeAdapterFactory factory;

  @Before
  public void setUp() {
    gson = new GsonBuilder().create();
    ConstructorConstructor constructorConstructor = new ConstructorConstructor(
        Collections.<Type, com.google.gson.InstanceCreator<?>>emptyMap(),
        true,
        Collections.<ReflectionAccessFilter>emptyList());
    factory = new CollectionTypeAdapterFactory(constructorConstructor);
  }

  @Test
  public void testCreateReturnsNullForNonCollectionType() {
    TypeAdapter<String> adapter = factory.create(gson, TypeToken.get(String.class));

    assertThat(adapter).isNull();
  }

  @Test
  public void testCreateReturnsNullForIntegerType() {
    TypeAdapter<Integer> adapter = factory.create(gson, TypeToken.get(Integer.class));

    assertThat(adapter).isNull();
  }

  @Test
  public void testCreateReturnsAdapterForListType() {
    TypeAdapter<List<String>> adapter = factory.create(gson, new TypeToken<List<String>>() {});

    assertThat(adapter).isNotNull();
  }

  @Test
  public void testCreateReturnsAdapterForSetType() {
    TypeAdapter<Set<String>> adapter = factory.create(gson, new TypeToken<Set<String>>() {});

    assertThat(adapter).isNotNull();
  }

  @Test
  public void testSerializeListOfStrings() {
    List<String> list = Arrays.asList("hello", "world");
    String json = gson.toJson(list);

    assertThat(json).isEqualTo("[\"hello\",\"world\"]");
  }

  @Test
  public void testDeserializeListOfStrings() {
    List<String> result = gson.fromJson("[\"hello\",\"world\"]", new TypeToken<List<String>>() {});

    assertThat(result).containsExactly("hello", "world").inOrder();
  }

  @Test
  public void testSerializeListOfIntegers() {
    List<Integer> list = Arrays.asList(1, 2, 3);
    String json = gson.toJson(list);

    assertThat(json).isEqualTo("[1,2,3]");
  }

  @Test
  public void testDeserializeListOfIntegers() {
    List<Integer> result = gson.fromJson("[1,2,3]", new TypeToken<List<Integer>>() {});

    assertThat(result).containsExactly(1, 2, 3).inOrder();
  }

  @Test
  public void testDeserializeEmptyList() {
    List<String> result = gson.fromJson("[]", new TypeToken<List<String>>() {});

    assertThat(result).isEmpty();
  }

  @Test
  public void testDeserializeNullList() {
    List<String> result = gson.fromJson("null", new TypeToken<List<String>>() {});

    assertThat(result).isNull();
  }

  @Test
  public void testDeserializeSet() {
    Set<String> result = gson.fromJson("[\"a\",\"b\"]", new TypeToken<Set<String>>() {});

    assertThat(result).containsExactly("a", "b");
  }
}
