/*
 * Copyright (C) 2024 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
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
import com.google.gson.TypeAdapter;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.reflect.TypeToken;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Test;

public class MapTypeAdapterFactoryTest {

  @Test
  public void testConstructorWithComplexKeySerialization() {
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), false, Collections.emptyList());

    MapTypeAdapterFactory factory = new MapTypeAdapterFactory(constructorConstructor, true);

    assertThat(factory).isNotNull();
    assertThat(factory.complexMapKeySerialization).isTrue();
  }

  @Test
  public void testConstructorWithoutComplexKeySerialization() {
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), false, Collections.emptyList());

    MapTypeAdapterFactory factory = new MapTypeAdapterFactory(constructorConstructor, false);

    assertThat(factory).isNotNull();
    assertThat(factory.complexMapKeySerialization).isFalse();
  }

  @Test
  public void testCreateReturnsNullForNonMapType() {
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), false, Collections.emptyList());
    MapTypeAdapterFactory factory = new MapTypeAdapterFactory(constructorConstructor, false);
    Gson gson = new Gson();

    TypeAdapter<String> adapter = factory.create(gson, TypeToken.get(String.class));

    assertThat(adapter).isNull();
  }

  @Test
  public void testCreateReturnsAdapterForMapType() {
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), false, Collections.emptyList());
    MapTypeAdapterFactory factory = new MapTypeAdapterFactory(constructorConstructor, false);
    Gson gson = new Gson();

    TypeAdapter<Map<String, String>> adapter = factory.create(gson, new TypeToken<Map<String, String>>() {});

    assertThat(adapter).isNotNull();
  }

  @Test
  public void testCreateReturnsAdapterForHashMap() {
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), false, Collections.emptyList());
    MapTypeAdapterFactory factory = new MapTypeAdapterFactory(constructorConstructor, false);
    Gson gson = new Gson();

    TypeAdapter<HashMap<String, Integer>> adapter = factory.create(gson, new TypeToken<HashMap<String, Integer>>() {});

    assertThat(adapter).isNotNull();
  }

  @Test
  public void testCreateReturnsAdapterForLinkedHashMap() {
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), false, Collections.emptyList());
    MapTypeAdapterFactory factory = new MapTypeAdapterFactory(constructorConstructor, false);
    Gson gson = new Gson();

    TypeAdapter<LinkedHashMap<Integer, String>> adapter = factory.create(gson, new TypeToken<LinkedHashMap<Integer, String>>() {});

    assertThat(adapter).isNotNull();
  }

  @Test
  public void testCreateReturnsAdapterForMapWithBooleanKeys() {
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), false, Collections.emptyList());
    MapTypeAdapterFactory factory = new MapTypeAdapterFactory(constructorConstructor, false);
    Gson gson = new Gson();

    TypeAdapter<Map<Boolean, String>> adapter = factory.create(gson, new TypeToken<Map<Boolean, String>>() {});

    assertThat(adapter).isNotNull();
  }

  @Test
  public void testCreateReturnsAdapterForMapWithPrimitiveBooleanKeys() {
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), false, Collections.emptyList());
    MapTypeAdapterFactory factory = new MapTypeAdapterFactory(constructorConstructor, false);
    Gson gson = new Gson();

    TypeAdapter<Map<Boolean, Integer>> adapter = factory.create(gson, new TypeToken<Map<Boolean, Integer>>() {});

    assertThat(adapter).isNotNull();
  }

  @Test
  public void testCreateWithComplexKeySerialization() {
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), false, Collections.emptyList());
    MapTypeAdapterFactory factory = new MapTypeAdapterFactory(constructorConstructor, true);
    Gson gson = new Gson();

    TypeAdapter<Map<String, String>> adapter = factory.create(gson, new TypeToken<Map<String, String>>() {});

    assertThat(adapter).isNotNull();
  }
}
