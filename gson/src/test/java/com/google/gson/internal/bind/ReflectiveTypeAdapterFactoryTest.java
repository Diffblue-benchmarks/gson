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

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;

public final class ReflectiveTypeAdapterFactoryTest {
  private Gson gson;
  private ReflectiveTypeAdapterFactory factory;

  @Before
  public void setUp() {
    gson = new Gson();
    // Create a ReflectiveTypeAdapterFactory directly for testing
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    factory =
        new ReflectiveTypeAdapterFactory(
            constructorConstructor,
            FieldNamingPolicy.IDENTITY,
            Excluder.DEFAULT,
            new JsonAdapterAnnotationTypeAdapterFactory(constructorConstructor),
            Collections.emptyList());
  }

  @Test
  public void testPrimitiveTypeReturnsNull() {
    // The ReflectiveTypeAdapterFactory should return null for primitive types
    // since Object.class.isAssignableFrom(primitive) is false
    TypeAdapter<?> adapter = factory.create(gson, TypeToken.get(int.class));
    assertThat(adapter).isNull();

    // Also test other primitives
    assertThat(factory.create(gson, TypeToken.get(boolean.class))).isNull();
    assertThat(factory.create(gson, TypeToken.get(double.class))).isNull();
    assertThat(factory.create(gson, TypeToken.get(long.class))).isNull();
    assertThat(factory.create(gson, TypeToken.get(char.class))).isNull();
  }

  @Test
  public void testAnonymousClassAdapterRead() throws IOException {
    // Create an anonymous class
    Object anonymousInstance =
        new Object() {
          @SuppressWarnings("unused")
          String value = "test";
        };
    @SuppressWarnings("unchecked")
    TypeToken<Object> typeToken = (TypeToken<Object>) TypeToken.get(anonymousInstance.getClass());

    // Get the adapter directly from the factory for the anonymous class
    TypeAdapter<Object> adapter = factory.create(gson, typeToken);
    assertThat(adapter).isNotNull();

    // The anonymous class adapter should skip JSON input and return null
    JsonReader reader = new JsonReader(new StringReader("{\"value\":\"hello\"}"));
    Object result = adapter.read(reader);
    assertThat(result).isNull();
  }

  @Test
  public void testAnonymousClassAdapterToString() {
    // Create an anonymous class
    Object anonymousInstance = new Object() {};
    @SuppressWarnings("unchecked")
    TypeToken<Object> typeToken = (TypeToken<Object>) TypeToken.get(anonymousInstance.getClass());

    // Get the adapter directly from the factory
    TypeAdapter<?> adapter = factory.create(gson, typeToken);
    assertThat(adapter).isNotNull();

    // The adapter's toString should indicate it's for anonymous/local classes
    assertThat(adapter.toString()).isEqualTo("AnonymousOrNonStaticLocalClassAdapter");
  }

  @Test
  public void testAnonymousClassAdapterWrite() throws IOException {
    // Create an anonymous class
    Object anonymousInstance =
        new Object() {
          @SuppressWarnings("unused")
          String value = "test";
        };
    @SuppressWarnings("unchecked")
    TypeToken<Object> typeToken = (TypeToken<Object>) TypeToken.get(anonymousInstance.getClass());

    // Get the adapter directly from the factory
    TypeAdapter<Object> adapter = factory.create(gson, typeToken);
    assertThat(adapter).isNotNull();

    // The anonymous class adapter should write null for any value
    StringWriter stringWriter = new StringWriter();
    com.google.gson.stream.JsonWriter writer = new com.google.gson.stream.JsonWriter(stringWriter);
    adapter.write(writer, anonymousInstance);
    assertThat(stringWriter.toString()).isEqualTo("null");
  }

  @Test
  public void testLocalClassAdapterRead() throws IOException {
    class LocalClass {
      @SuppressWarnings("unused")
      String field;
    }

    TypeAdapter<LocalClass> adapter = factory.create(gson, TypeToken.get(LocalClass.class));
    assertThat(adapter).isNotNull();

    // The local class adapter should skip JSON input and return null
    JsonReader reader = new JsonReader(new StringReader("{\"field\":\"value\"}"));
    LocalClass result = adapter.read(reader);
    assertThat(result).isNull();
  }

  @Test
  public void testLocalClassAdapterToString() {
    class LocalClass {}

    TypeAdapter<LocalClass> adapter = factory.create(gson, TypeToken.get(LocalClass.class));
    assertThat(adapter).isNotNull();

    // The adapter's toString should indicate it's for anonymous/local classes
    assertThat(adapter.toString()).isEqualTo("AnonymousOrNonStaticLocalClassAdapter");
  }
}
