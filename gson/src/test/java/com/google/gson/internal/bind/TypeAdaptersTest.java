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

import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.junit.Test;

public final class TypeAdaptersTest {

  private static final TypeAdapter<String> DUMMY_ADAPTER =
      new TypeAdapter<String>() {
        @Override
        public void write(JsonWriter out, String value) throws IOException {
          out.value(value);
        }

        @Override
        public String read(JsonReader in) throws IOException {
          return in.nextString();
        }
      };

  @Test
  public void testPrivateConstructorThrowsException() throws Exception {
    Constructor<TypeAdapters> constructor = TypeAdapters.class.getDeclaredConstructor();
    constructor.setAccessible(true);

    InvocationTargetException exception =
        assertThrows(InvocationTargetException.class, constructor::newInstance);
    assertThat(exception.getCause()).isInstanceOf(UnsupportedOperationException.class);
  }

  @Test
  public void testNewFactorySingleTypeToString() {
    TypeAdapterFactory factory = TypeAdapters.newFactory(String.class, DUMMY_ADAPTER);

    String toString = factory.toString();
    assertThat(toString).contains("Factory[type=");
    assertThat(toString).contains("java.lang.String");
    assertThat(toString).contains("adapter=");
  }

  @Test
  public void testNewFactoryBoxedUnboxedToString() {
    TypeAdapterFactory factory =
        TypeAdapters.newFactory(int.class, Integer.class, TypeAdapters.INTEGER);

    String toString = factory.toString();
    assertThat(toString).contains("Factory[type=");
    assertThat(toString).contains("java.lang.Integer");
    assertThat(toString).contains("int");
    assertThat(toString).contains("adapter=");
  }

  @Test
  public void testNewFactoryForMultipleTypesToString() {
    TypeAdapterFactory factory =
        TypeAdapters.newFactoryForMultipleTypes(Number.class, Integer.class, TypeAdapters.INTEGER);

    String toString = factory.toString();
    assertThat(toString).contains("Factory[type=");
    assertThat(toString).contains("java.lang.Number");
    assertThat(toString).contains("java.lang.Integer");
    assertThat(toString).contains("adapter=");
  }

  @Test
  public void testNewTypeHierarchyFactoryToString() {
    TypeAdapterFactory factory =
        TypeAdapters.newTypeHierarchyFactory(String.class, TypeAdapters.STRING);

    String toString = factory.toString();
    assertThat(toString).contains("Factory[typeHierarchy=");
    assertThat(toString).contains("java.lang.String");
    assertThat(toString).contains("adapter=");
  }
}
