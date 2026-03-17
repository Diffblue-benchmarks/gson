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
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import org.junit.Test;

public class TreeTypeAdapterSingleTypeFactoryTest {

  private static class TestSerializer implements JsonSerializer<String> {
    @Override
    public JsonElement serialize(String src, Type typeOfSrc, JsonSerializationContext context) {
      return new JsonPrimitive(src);
    }
  }

  private static class TestDeserializer implements JsonDeserializer<String> {
    @Override
    public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
      return json.getAsString();
    }
  }

  private static class TestSerializerDeserializer
      implements JsonSerializer<String>, JsonDeserializer<String> {
    @Override
    public JsonElement serialize(String src, Type typeOfSrc, JsonSerializationContext context) {
      return new JsonPrimitive(src);
    }

    @Override
    public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
      return json.getAsString();
    }
  }

  private static class InvalidTypeAdapter {}

  @Test
  public void testNewFactoryWithSerializer() {
    TypeToken<String> typeToken = TypeToken.get(String.class);
    TestSerializer serializer = new TestSerializer();
    TypeAdapterFactory factory = TreeTypeAdapter.newFactory(typeToken, serializer);

    assertThat(factory).isNotNull();

    Gson gson = new Gson();
    TypeAdapter<String> adapter = factory.create(gson, typeToken);
    assertThat(adapter).isNotNull();
  }

  @Test
  public void testNewFactoryWithDeserializer() {
    TypeToken<String> typeToken = TypeToken.get(String.class);
    TestDeserializer deserializer = new TestDeserializer();
    TypeAdapterFactory factory = TreeTypeAdapter.newFactory(typeToken, deserializer);

    assertThat(factory).isNotNull();

    Gson gson = new Gson();
    TypeAdapter<String> adapter = factory.create(gson, typeToken);
    assertThat(adapter).isNotNull();
  }

  @Test
  public void testNewFactoryWithSerializerDeserializer() {
    TypeToken<String> typeToken = TypeToken.get(String.class);
    TestSerializerDeserializer serializerDeserializer = new TestSerializerDeserializer();
    TypeAdapterFactory factory = TreeTypeAdapter.newFactory(typeToken, serializerDeserializer);

    assertThat(factory).isNotNull();

    Gson gson = new Gson();
    TypeAdapter<String> adapter = factory.create(gson, typeToken);
    assertThat(adapter).isNotNull();
  }

  @Test
  public void testNewFactoryWithInvalidTypeAdapter() {
    TypeToken<String> typeToken = TypeToken.get(String.class);
    InvalidTypeAdapter invalidAdapter = new InvalidTypeAdapter();

    try {
      TreeTypeAdapter.newFactory(typeToken, invalidAdapter);
      assertThat(false).isTrue(); // Should not reach here
    } catch (IllegalArgumentException e) {
      assertThat(e.getMessage())
          .contains("must implement JsonSerializer or JsonDeserializer");
      assertThat(e.getMessage()).contains(InvalidTypeAdapter.class.getName());
    }
  }

  @Test
  public void testNewFactoryWithNullTypeAdapter() {
    TypeToken<String> typeToken = TypeToken.get(String.class);

    try {
      TreeTypeAdapter.newFactory(typeToken, null);
      assertThat(false).isTrue(); // Should not reach here
    } catch (NullPointerException e) {
      // Expected
    }
  }

  @Test
  public void testCreateWithExactTypeMatch() {
    TypeToken<String> typeToken = TypeToken.get(String.class);
    TestSerializer serializer = new TestSerializer();
    TypeAdapterFactory factory = TreeTypeAdapter.newFactory(typeToken, serializer);

    Gson gson = new Gson();
    TypeAdapter<String> adapter = factory.create(gson, typeToken);
    assertThat(adapter).isNotNull();
    assertThat(adapter).isInstanceOf(TreeTypeAdapter.class);
  }

  @Test
  public void testCreateWithNoMatch() {
    TypeToken<String> stringTypeToken = TypeToken.get(String.class);
    TypeToken<Integer> integerTypeToken = TypeToken.get(Integer.class);
    TestSerializer serializer = new TestSerializer();
    TypeAdapterFactory factory = TreeTypeAdapter.newFactory(stringTypeToken, serializer);

    Gson gson = new Gson();
    TypeAdapter<Integer> adapter = factory.create(gson, integerTypeToken);
    assertThat(adapter).isNull();
  }

  @Test
  public void testNewFactoryWithMatchRawType() {
    TypeToken<String> typeToken = TypeToken.get(String.class);
    TestSerializer serializer = new TestSerializer();
    TypeAdapterFactory factory = TreeTypeAdapter.newFactoryWithMatchRawType(typeToken, serializer);

    assertThat(factory).isNotNull();

    Gson gson = new Gson();
    TypeAdapter<String> adapter = factory.create(gson, typeToken);
    assertThat(adapter).isNotNull();
  }

  @Test
  public void testNewTypeHierarchyFactory() {
    TestSerializer serializer = new TestSerializer();
    TypeAdapterFactory factory = TreeTypeAdapter.newTypeHierarchyFactory(CharSequence.class, serializer);

    assertThat(factory).isNotNull();

    Gson gson = new Gson();
    TypeToken<String> stringToken = TypeToken.get(String.class);
    TypeAdapter<String> adapter = factory.create(gson, stringToken);
    assertThat(adapter).isNotNull();
  }

  @Test
  public void testNewTypeHierarchyFactoryNoMatch() {
    TestSerializer serializer = new TestSerializer();
    TypeAdapterFactory factory = TreeTypeAdapter.newTypeHierarchyFactory(Number.class, serializer);

    assertThat(factory).isNotNull();

    Gson gson = new Gson();
    TypeToken<String> stringToken = TypeToken.get(String.class);
    TypeAdapter<String> adapter = factory.create(gson, stringToken);
    assertThat(adapter).isNull();
  }

  @Test
  public void testMatchRawTypeWithRawTypeMatch() {
    TypeToken<String> typeToken = TypeToken.get(String.class);
    TestSerializer serializer = new TestSerializer();
    TypeAdapterFactory factory = TreeTypeAdapter.newFactoryWithMatchRawType(typeToken, serializer);

    Gson gson = new Gson();
    TypeAdapter<String> adapter = factory.create(gson, TypeToken.get(String.class));
    assertThat(adapter).isNotNull();
  }

  @Test
  public void testMatchRawTypeWithNoRawTypeMatch() {
    TypeToken<String> typeToken = TypeToken.get(String.class);
    TestSerializer serializer = new TestSerializer();
    TypeAdapterFactory factory = TreeTypeAdapter.newFactoryWithMatchRawType(typeToken, serializer);

    Gson gson = new Gson();
    TypeAdapter<Integer> adapter = factory.create(gson, TypeToken.get(Integer.class));
    assertThat(adapter).isNull();
  }

  @Test
  @SuppressWarnings("rawtypes")
  public void testMatchRawTypeWithParameterizedType() {
    TypeToken<List> rawListToken = TypeToken.get(List.class);
    TestSerializer serializer = new TestSerializer();
    TypeAdapterFactory factory = TreeTypeAdapter.newFactoryWithMatchRawType(rawListToken, serializer);

    Gson gson = new Gson();
    TypeToken<List<String>> parameterizedListToken = new TypeToken<List<String>>() {};
    TypeAdapter<List<String>> adapter = factory.create(gson, parameterizedListToken);
    assertThat(adapter).isNotNull();
  }
}
