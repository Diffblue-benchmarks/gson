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
import static org.junit.Assert.assertThrows;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.junit.Test;

public final class TypeAdaptersTest {

  @Test
  public void testConstructorThrowsUnsupportedOperationException() throws Exception {
    Constructor<TypeAdapters> constructor = TypeAdapters.class.getDeclaredConstructor();
    constructor.setAccessible(true);
    try {
      constructor.newInstance();
    } catch (InvocationTargetException e) {
      assertThat(e.getCause()).isInstanceOf(UnsupportedOperationException.class);
      return;
    }
    throw new AssertionError("Expected InvocationTargetException was not thrown");
  }

  @Test
  public void testNewFactoryTypeToken_returnsAdapterWhenTypeMatches() {
    TypeToken<String> token = TypeToken.get(String.class);
    TypeAdapterFactory factory = TypeAdapters.newFactory(token, TypeAdapters.STRING);
    Gson gson = new Gson();

    TypeAdapter<String> adapter = factory.create(gson, TypeToken.get(String.class));

    assertThat(adapter).isSameInstanceAs(TypeAdapters.STRING);
  }

  @Test
  public void testNewFactoryTypeToken_returnsNullWhenTypeDoesNotMatch() {
    TypeToken<String> token = TypeToken.get(String.class);
    TypeAdapterFactory factory = TypeAdapters.newFactory(token, TypeAdapters.STRING);
    Gson gson = new Gson();

    TypeAdapter<Integer> adapter = factory.create(gson, TypeToken.get(Integer.class));

    assertThat(adapter).isNull();
  }

  @Test
  public void testNewFactoryClass_returnsAdapterWhenTypeMatches() {
    TypeAdapterFactory factory = TypeAdapters.newFactory(String.class, TypeAdapters.STRING);
    Gson gson = new Gson();

    TypeAdapter<String> adapter = factory.create(gson, TypeToken.get(String.class));

    assertThat(adapter).isSameInstanceAs(TypeAdapters.STRING);
  }

  @Test
  public void testNewFactoryClass_returnsNullWhenTypeDoesNotMatch() {
    TypeAdapterFactory factory = TypeAdapters.newFactory(String.class, TypeAdapters.STRING);
    Gson gson = new Gson();

    TypeAdapter<Integer> adapter = factory.create(gson, TypeToken.get(Integer.class));

    assertThat(adapter).isNull();
  }

  @Test
  public void testNewFactoryClass_toStringContainsTypeAndAdapter() {
    TypeAdapterFactory factory = TypeAdapters.newFactory(String.class, TypeAdapters.STRING);

    assertThat(factory.toString())
        .isEqualTo(
            "Factory[type=java.lang.String,adapter=" + TypeAdapters.STRING + "]");
  }

  @Test
  public void testNewFactoryUnboxedBoxed_returnsAdapterForUnboxedType() {
    TypeAdapterFactory factory =
        TypeAdapters.newFactory(int.class, Integer.class, TypeAdapters.INTEGER);
    Gson gson = new Gson();

    TypeAdapter<Integer> adapter = factory.create(gson, TypeToken.get(int.class));

    assertThat(adapter).isSameInstanceAs(TypeAdapters.INTEGER);
  }

  @Test
  public void testNewFactoryUnboxedBoxed_returnsAdapterForBoxedType() {
    TypeAdapterFactory factory =
        TypeAdapters.newFactory(int.class, Integer.class, TypeAdapters.INTEGER);
    Gson gson = new Gson();

    TypeAdapter<Integer> adapter = factory.create(gson, TypeToken.get(Integer.class));

    assertThat(adapter).isSameInstanceAs(TypeAdapters.INTEGER);
  }

  @Test
  public void testNewFactoryUnboxedBoxed_returnsNullForOtherType() {
    TypeAdapterFactory factory =
        TypeAdapters.newFactory(int.class, Integer.class, TypeAdapters.INTEGER);
    Gson gson = new Gson();

    TypeAdapter<Long> adapter = factory.create(gson, TypeToken.get(Long.class));

    assertThat(adapter).isNull();
  }

  @Test
  public void testNewFactoryUnboxedBoxed_toStringContainsTypes() {
    TypeAdapterFactory factory =
        TypeAdapters.newFactory(int.class, Integer.class, TypeAdapters.INTEGER);

    assertThat(factory.toString())
        .isEqualTo(
            "Factory[type=java.lang.Integer+int,adapter=" + TypeAdapters.INTEGER + "]");
  }

  @Test
  public void testNewFactoryForMultipleTypes_returnsAdapterForBaseType() {
    TypeAdapterFactory factory =
        TypeAdapters.newFactoryForMultipleTypes(
            Number.class, Integer.class, TypeAdapters.INTEGER);
    Gson gson = new Gson();

    TypeAdapter<Number> adapter = factory.create(gson, TypeToken.get(Number.class));

    assertThat(adapter).isSameInstanceAs(TypeAdapters.INTEGER);
  }

  @Test
  public void testNewFactoryForMultipleTypes_returnsAdapterForSubType() {
    TypeAdapterFactory factory =
        TypeAdapters.newFactoryForMultipleTypes(
            Number.class, Integer.class, TypeAdapters.INTEGER);
    Gson gson = new Gson();

    TypeAdapter<Integer> adapter = factory.create(gson, TypeToken.get(Integer.class));

    assertThat(adapter).isSameInstanceAs(TypeAdapters.INTEGER);
  }

  @Test
  public void testNewFactoryForMultipleTypes_returnsNullForOtherType() {
    TypeAdapterFactory factory =
        TypeAdapters.newFactoryForMultipleTypes(
            Number.class, Integer.class, TypeAdapters.INTEGER);
    Gson gson = new Gson();

    TypeAdapter<String> adapter = factory.create(gson, TypeToken.get(String.class));

    assertThat(adapter).isNull();
  }

  @Test
  public void testNewFactoryForMultipleTypes_toStringContainsTypes() {
    TypeAdapterFactory factory =
        TypeAdapters.newFactoryForMultipleTypes(
            Number.class, Integer.class, TypeAdapters.INTEGER);

    assertThat(factory.toString())
        .isEqualTo(
            "Factory[type=java.lang.Number+java.lang.Integer,adapter="
                + TypeAdapters.INTEGER
                + "]");
  }

  @Test
  public void testNewTypeHierarchyFactory_returnsNullForNonAssignableType() {
    TypeAdapterFactory factory =
        TypeAdapters.newTypeHierarchyFactory(String.class, TypeAdapters.STRING);
    Gson gson = new Gson();

    TypeAdapter<Integer> adapter = factory.create(gson, TypeToken.get(Integer.class));

    assertThat(adapter).isNull();
  }

  @Test
  public void testNewTypeHierarchyFactory_returnsAdapterForAssignableType() {
    TypeAdapterFactory factory =
        TypeAdapters.newTypeHierarchyFactory(String.class, TypeAdapters.STRING);
    Gson gson = new Gson();

    TypeAdapter<String> adapter = factory.create(gson, TypeToken.get(String.class));

    assertThat(adapter).isNotNull();
  }

  @Test
  public void testNewTypeHierarchyFactory_toStringContainsHierarchyAndAdapter() {
    TypeAdapterFactory factory =
        TypeAdapters.newTypeHierarchyFactory(String.class, TypeAdapters.STRING);

    assertThat(factory.toString())
        .isEqualTo(
            "Factory[typeHierarchy=java.lang.String,adapter=" + TypeAdapters.STRING + "]");
  }

  @Test
  public void testNewTypeHierarchyFactory_readDelegatesSuccessfully() throws IOException {
    TypeAdapterFactory factory =
        TypeAdapters.newTypeHierarchyFactory(String.class, TypeAdapters.STRING);
    Gson gson = new Gson();
    TypeAdapter<String> adapter = factory.create(gson, TypeToken.get(String.class));

    String result = adapter.fromJson("\"hello\"");

    assertThat(result).isEqualTo("hello");
  }

  @Test
  public void testNewTypeHierarchyFactory_readThrowsJsonSyntaxExceptionOnTypeMismatch()
      throws IOException {
    TypeAdapter<Number> returnsIntAdapter =
        new TypeAdapter<Number>() {
          @Override
          public void write(JsonWriter out, Number value) throws IOException {
            out.value(value.longValue());
          }

          @Override
          public Number read(JsonReader in) throws IOException {
            return in.nextInt();
          }
        };

    TypeAdapterFactory factory =
        TypeAdapters.newTypeHierarchyFactory(Number.class, returnsIntAdapter);
    Gson gson = new Gson();
    TypeAdapter<Long> adapter = factory.create(gson, TypeToken.get(Long.class));

    assertThrows(JsonSyntaxException.class, () -> adapter.fromJson("42"));
  }
}
