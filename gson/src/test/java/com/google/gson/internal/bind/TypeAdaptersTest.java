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
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.junit.Test;

public class TypeAdaptersTest {

  @Test
  public void testPrivateConstructorThrowsException() throws Exception {
    Constructor<TypeAdapters> constructor = TypeAdapters.class.getDeclaredConstructor();
    constructor.setAccessible(true);

    try {
      constructor.newInstance();
      throw new AssertionError("Expected UnsupportedOperationException");
    } catch (InvocationTargetException e) {
      assertThat(e.getCause()).isInstanceOf(UnsupportedOperationException.class);
    }
  }

  @Test
  public void testNewFactoryWithTypeToken() {
    TypeToken<String> typeToken = TypeToken.get(String.class);
    TypeAdapter<String> typeAdapter = new TypeAdapter<String>() {
      @Override
      public void write(JsonWriter out, String value) throws IOException {
        out.value(value);
      }

      @Override
      public String read(JsonReader in) throws IOException {
        return in.nextString();
      }
    };

    TypeAdapterFactory factory = TypeAdapters.newFactory(typeToken, typeAdapter);
    Gson gson = new Gson();

    TypeAdapter<String> result = factory.create(gson, typeToken);

    assertThat(result).isSameInstanceAs(typeAdapter);
  }

  @Test
  public void testNewFactoryWithTypeTokenReturnsNullForDifferentType() {
    TypeToken<String> stringToken = TypeToken.get(String.class);
    TypeToken<Integer> integerToken = TypeToken.get(Integer.class);
    TypeAdapter<String> typeAdapter = new TypeAdapter<String>() {
      @Override
      public void write(JsonWriter out, String value) throws IOException {
        out.value(value);
      }

      @Override
      public String read(JsonReader in) throws IOException {
        return in.nextString();
      }
    };

    TypeAdapterFactory factory = TypeAdapters.newFactory(stringToken, typeAdapter);
    Gson gson = new Gson();

    TypeAdapter<Integer> result = factory.create(gson, integerToken);

    assertThat(result).isNull();
  }

  @Test
  public void testNewFactoryWithClass() {
    TypeAdapter<String> typeAdapter = new TypeAdapter<String>() {
      @Override
      public void write(JsonWriter out, String value) throws IOException {
        out.value(value);
      }

      @Override
      public String read(JsonReader in) throws IOException {
        return in.nextString();
      }
    };

    TypeAdapterFactory factory = TypeAdapters.newFactory(String.class, typeAdapter);
    Gson gson = new Gson();

    TypeAdapter<String> result = factory.create(gson, TypeToken.get(String.class));

    assertThat(result).isSameInstanceAs(typeAdapter);
  }

  @Test
  public void testNewFactoryWithClassReturnsNullForDifferentType() {
    TypeAdapter<String> typeAdapter = new TypeAdapter<String>() {
      @Override
      public void write(JsonWriter out, String value) throws IOException {
        out.value(value);
      }

      @Override
      public String read(JsonReader in) throws IOException {
        return in.nextString();
      }
    };

    TypeAdapterFactory factory = TypeAdapters.newFactory(String.class, typeAdapter);
    Gson gson = new Gson();

    TypeAdapter<Integer> result = factory.create(gson, TypeToken.get(Integer.class));

    assertThat(result).isNull();
  }

  @Test
  public void testNewFactoryWithClassToString() {
    TypeAdapter<String> typeAdapter = new TypeAdapter<String>() {
      @Override
      public void write(JsonWriter out, String value) throws IOException {
        out.value(value);
      }

      @Override
      public String read(JsonReader in) throws IOException {
        return in.nextString();
      }

      @Override
      public String toString() {
        return "StringAdapter";
      }
    };

    TypeAdapterFactory factory = TypeAdapters.newFactory(String.class, typeAdapter);

    assertThat(factory.toString()).isEqualTo("Factory[type=java.lang.String,adapter=StringAdapter]");
  }

  @Test
  public void testNewFactoryWithUnboxedAndBoxed() {
    TypeAdapter<Integer> typeAdapter = new TypeAdapter<Integer>() {
      @Override
      public void write(JsonWriter out, Integer value) throws IOException {
        out.value(value);
      }

      @Override
      public Integer read(JsonReader in) throws IOException {
        return in.nextInt();
      }
    };

    TypeAdapterFactory factory = TypeAdapters.newFactory(int.class, Integer.class, typeAdapter);
    Gson gson = new Gson();

    TypeAdapter<Integer> boxedResult = factory.create(gson, TypeToken.get(Integer.class));
    TypeAdapter<?> unboxedResult = factory.create(gson, TypeToken.get(int.class));

    assertThat(boxedResult).isSameInstanceAs(typeAdapter);
    assertThat(unboxedResult).isSameInstanceAs(typeAdapter);
  }

  @Test
  public void testNewFactoryWithUnboxedAndBoxedReturnsNullForDifferentType() {
    TypeAdapter<Integer> typeAdapter = new TypeAdapter<Integer>() {
      @Override
      public void write(JsonWriter out, Integer value) throws IOException {
        out.value(value);
      }

      @Override
      public Integer read(JsonReader in) throws IOException {
        return in.nextInt();
      }
    };

    TypeAdapterFactory factory = TypeAdapters.newFactory(int.class, Integer.class, typeAdapter);
    Gson gson = new Gson();

    TypeAdapter<String> result = factory.create(gson, TypeToken.get(String.class));

    assertThat(result).isNull();
  }

  @Test
  public void testNewFactoryWithUnboxedAndBoxedToString() {
    TypeAdapter<Integer> typeAdapter = new TypeAdapter<Integer>() {
      @Override
      public void write(JsonWriter out, Integer value) throws IOException {
        out.value(value);
      }

      @Override
      public Integer read(JsonReader in) throws IOException {
        return in.nextInt();
      }

      @Override
      public String toString() {
        return "IntAdapter";
      }
    };

    TypeAdapterFactory factory = TypeAdapters.newFactory(int.class, Integer.class, typeAdapter);

    assertThat(factory.toString()).isEqualTo("Factory[type=java.lang.Integer+int,adapter=IntAdapter]");
  }

  @Test
  public void testNewFactoryForMultipleTypes() {
    TypeAdapter<Number> typeAdapter = new TypeAdapter<Number>() {
      @Override
      public void write(JsonWriter out, Number value) throws IOException {
        out.value(value);
      }

      @Override
      public Number read(JsonReader in) throws IOException {
        return in.nextInt();
      }
    };

    TypeAdapterFactory factory = TypeAdapters.newFactoryForMultipleTypes(Number.class, Integer.class, typeAdapter);
    Gson gson = new Gson();

    TypeAdapter<Number> baseResult = factory.create(gson, TypeToken.get(Number.class));
    TypeAdapter<Integer> subResult = factory.create(gson, TypeToken.get(Integer.class));

    assertThat(baseResult).isSameInstanceAs(typeAdapter);
    assertThat(subResult).isSameInstanceAs(typeAdapter);
  }

  @Test
  public void testNewFactoryForMultipleTypesReturnsNullForDifferentType() {
    TypeAdapter<Number> typeAdapter = new TypeAdapter<Number>() {
      @Override
      public void write(JsonWriter out, Number value) throws IOException {
        out.value(value);
      }

      @Override
      public Number read(JsonReader in) throws IOException {
        return in.nextInt();
      }
    };

    TypeAdapterFactory factory = TypeAdapters.newFactoryForMultipleTypes(Number.class, Integer.class, typeAdapter);
    Gson gson = new Gson();

    TypeAdapter<String> result = factory.create(gson, TypeToken.get(String.class));

    assertThat(result).isNull();
  }

  @Test
  public void testNewFactoryForMultipleTypesToString() {
    TypeAdapter<Number> typeAdapter = new TypeAdapter<Number>() {
      @Override
      public void write(JsonWriter out, Number value) throws IOException {
        out.value(value);
      }

      @Override
      public Number read(JsonReader in) throws IOException {
        return in.nextInt();
      }

      @Override
      public String toString() {
        return "NumberAdapter";
      }
    };

    TypeAdapterFactory factory = TypeAdapters.newFactoryForMultipleTypes(Number.class, Integer.class, typeAdapter);

    assertThat(factory.toString()).isEqualTo("Factory[type=java.lang.Number+java.lang.Integer,adapter=NumberAdapter]");
  }

  @Test
  public void testNewTypeHierarchyFactory() {
    TypeAdapter<Number> typeAdapter = new TypeAdapter<Number>() {
      @Override
      public void write(JsonWriter out, Number value) throws IOException {
        out.value(value);
      }

      @Override
      public Number read(JsonReader in) throws IOException {
        return in.nextInt();
      }
    };

    TypeAdapterFactory factory = TypeAdapters.newTypeHierarchyFactory(Number.class, typeAdapter);
    Gson gson = new Gson();

    TypeAdapter<Number> numberResult = factory.create(gson, TypeToken.get(Number.class));
    TypeAdapter<Integer> integerResult = factory.create(gson, TypeToken.get(Integer.class));

    assertThat(numberResult).isNotNull();
    assertThat(integerResult).isNotNull();
  }

  @Test
  public void testNewTypeHierarchyFactoryReturnsNullForUnrelatedType() {
    TypeAdapter<Number> typeAdapter = new TypeAdapter<Number>() {
      @Override
      public void write(JsonWriter out, Number value) throws IOException {
        out.value(value);
      }

      @Override
      public Number read(JsonReader in) throws IOException {
        return in.nextInt();
      }
    };

    TypeAdapterFactory factory = TypeAdapters.newTypeHierarchyFactory(Number.class, typeAdapter);
    Gson gson = new Gson();

    TypeAdapter<String> result = factory.create(gson, TypeToken.get(String.class));

    assertThat(result).isNull();
  }

  @Test
  public void testNewTypeHierarchyFactoryWrite() throws IOException {
    TypeAdapter<Number> typeAdapter = new TypeAdapter<Number>() {
      @Override
      public void write(JsonWriter out, Number value) throws IOException {
        out.value(value.intValue() * 2);
      }

      @Override
      public Number read(JsonReader in) throws IOException {
        return in.nextInt();
      }
    };

    TypeAdapterFactory factory = TypeAdapters.newTypeHierarchyFactory(Number.class, typeAdapter);
    Gson gson = new Gson();
    TypeAdapter<Integer> integerAdapter = factory.create(gson, TypeToken.get(Integer.class));

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    integerAdapter.write(writer, 5);

    assertThat(stringWriter.toString()).isEqualTo("10");
  }

  @Test
  public void testNewTypeHierarchyFactoryRead() throws IOException {
    TypeAdapter<Number> typeAdapter = new TypeAdapter<Number>() {
      @Override
      public void write(JsonWriter out, Number value) throws IOException {
        out.value(value);
      }

      @Override
      public Number read(JsonReader in) throws IOException {
        return Integer.valueOf(in.nextInt());
      }
    };

    TypeAdapterFactory factory = TypeAdapters.newTypeHierarchyFactory(Number.class, typeAdapter);
    Gson gson = new Gson();
    TypeAdapter<Integer> integerAdapter = factory.create(gson, TypeToken.get(Integer.class));

    JsonReader reader = new JsonReader(new StringReader("42"));
    Number result = integerAdapter.read(reader);

    assertThat(result).isEqualTo(42);
    assertThat(result).isInstanceOf(Integer.class);
  }

  @Test
  public void testNewTypeHierarchyFactoryReadNull() throws IOException {
    TypeAdapter<Number> typeAdapter = new TypeAdapter<Number>() {
      @Override
      public void write(JsonWriter out, Number value) throws IOException {
        out.value(value);
      }

      @Override
      public Number read(JsonReader in) throws IOException {
        in.nextNull();
        return null;
      }
    };

    TypeAdapterFactory factory = TypeAdapters.newTypeHierarchyFactory(Number.class, typeAdapter);
    Gson gson = new Gson();
    TypeAdapter<Integer> integerAdapter = factory.create(gson, TypeToken.get(Integer.class));

    JsonReader reader = new JsonReader(new StringReader("null"));
    Number result = integerAdapter.read(reader);

    assertThat(result).isNull();
  }

  @Test
  public void testNewTypeHierarchyFactoryToString() {
    TypeAdapter<Number> typeAdapter = new TypeAdapter<Number>() {
      @Override
      public void write(JsonWriter out, Number value) throws IOException {
        out.value(value);
      }

      @Override
      public Number read(JsonReader in) throws IOException {
        return in.nextInt();
      }

      @Override
      public String toString() {
        return "HierarchyAdapter";
      }
    };

    TypeAdapterFactory factory = TypeAdapters.newTypeHierarchyFactory(Number.class, typeAdapter);

    assertThat(factory.toString()).isEqualTo("Factory[typeHierarchy=java.lang.Number,adapter=HierarchyAdapter]");
  }
}
