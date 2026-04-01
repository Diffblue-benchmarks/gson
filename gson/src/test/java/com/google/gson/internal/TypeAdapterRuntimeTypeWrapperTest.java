/*
 * Copyright (C) 2011 Google Inc.
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
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;

public final class TypeAdapterRuntimeTypeWrapperTest {

  private static class Base {
    String name;

    Base(String name) {
      this.name = name;
    }
  }

  private static class Sub extends Base {
    int age;

    Sub(String name, int age) {
      super(name);
      this.age = age;
    }
  }

  @Test
  public void testConstructorStoresFields() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Base> delegate = gson.getAdapter(Base.class);
    TypeAdapterRuntimeTypeWrapper<Base> wrapper =
        new TypeAdapterRuntimeTypeWrapper<>(gson, delegate, Base.class);

    StringWriter sw = new StringWriter();
    JsonWriter out = new JsonWriter(sw);
    wrapper.write(out, new Base("hello"));
    out.flush();
    assertThat(sw.toString()).contains("hello");
  }

  @Test
  public void testReadDelegatesToDelegate() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Base> delegate = gson.getAdapter(Base.class);
    TypeAdapterRuntimeTypeWrapper<Base> wrapper =
        new TypeAdapterRuntimeTypeWrapper<>(gson, delegate, Base.class);

    JsonReader reader = new JsonReader(new StringReader("{\"name\":\"hello\"}"));
    Base result = wrapper.read(reader);
    assertThat(result.name).isEqualTo("hello");
  }

  @Test
  public void testWriteNullValueUsesDeclaredTypeAdapter() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Base> delegate = gson.getAdapter(Base.class);
    TypeAdapterRuntimeTypeWrapper<Base> wrapper =
        new TypeAdapterRuntimeTypeWrapper<>(gson, delegate, Base.class);

    StringWriter sw = new StringWriter();
    JsonWriter out = new JsonWriter(sw);
    wrapper.write(out, null);
    out.flush();
    assertThat(sw.toString()).isEqualTo("null");
  }

  @Test
  public void testWriteWhenRuntimeTypeEqualsDeclaredType() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Base> delegate = gson.getAdapter(Base.class);
    TypeAdapterRuntimeTypeWrapper<Base> wrapper =
        new TypeAdapterRuntimeTypeWrapper<>(gson, delegate, Base.class);

    StringWriter sw = new StringWriter();
    JsonWriter out = new JsonWriter(sw);
    wrapper.write(out, new Base("direct"));
    out.flush();
    assertThat(sw.toString()).isEqualTo("{\"name\":\"direct\"}");
  }

  @Test
  public void testWriteUsesRuntimeTypeAdapterWhenCustomAdapterRegistered() throws IOException {
    Gson gson =
        new GsonBuilder()
            .registerTypeAdapter(
                Sub.class,
                new TypeAdapter<Sub>() {
                  @Override
                  public void write(JsonWriter out, Sub value) throws IOException {
                    out.beginObject();
                    out.name("custom");
                    out.value(value.name);
                    out.endObject();
                  }

                  @Override
                  public Sub read(JsonReader in) throws IOException {
                    return null;
                  }
                })
            .create();

    TypeAdapter<Base> delegate = gson.getAdapter(Base.class);
    TypeAdapterRuntimeTypeWrapper<Base> wrapper =
        new TypeAdapterRuntimeTypeWrapper<>(gson, delegate, Base.class);

    StringWriter sw = new StringWriter();
    JsonWriter out = new JsonWriter(sw);
    wrapper.write(out, new Sub("Alice", 30));
    out.flush();
    assertThat(sw.toString()).isEqualTo("{\"custom\":\"Alice\"}");
  }

  @Test
  public void testWriteUsesDelegateWhenRuntimeAdapterIsReflectiveButDelegateIsCustom()
      throws IOException {
    Gson gson =
        new GsonBuilder()
            .registerTypeAdapter(
                Base.class,
                new TypeAdapter<Base>() {
                  @Override
                  public void write(JsonWriter out, Base value) throws IOException {
                    out.beginObject();
                    out.name("base_custom");
                    out.value(value.name);
                    out.endObject();
                  }

                  @Override
                  public Base read(JsonReader in) throws IOException {
                    return null;
                  }
                })
            .create();

    TypeAdapter<Base> delegate = gson.getAdapter(Base.class);
    TypeAdapterRuntimeTypeWrapper<Base> wrapper =
        new TypeAdapterRuntimeTypeWrapper<>(gson, delegate, Base.class);

    StringWriter sw = new StringWriter();
    JsonWriter out = new JsonWriter(sw);
    wrapper.write(out, new Sub("Bob", 25));
    out.flush();
    assertThat(sw.toString()).isEqualTo("{\"base_custom\":\"Bob\"}");
  }

  @Test
  public void testWriteUsesRuntimeReflectiveAdapterWhenBothAdaptersAreReflective()
      throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Base> delegate = gson.getAdapter(Base.class);
    TypeAdapterRuntimeTypeWrapper<Base> wrapper =
        new TypeAdapterRuntimeTypeWrapper<>(gson, delegate, Base.class);

    StringWriter sw = new StringWriter();
    JsonWriter out = new JsonWriter(sw);
    wrapper.write(out, new Sub("Carol", 20));
    out.flush();
    assertThat(sw.toString()).contains("\"age\":20");
    assertThat(sw.toString()).contains("\"name\":\"Carol\"");
  }

  @Test
  public void testWriteWithSerializationDelegatingAdapterWrappingNonReflective()
      throws IOException {
    Gson gson = new Gson();

    final TypeAdapter<Base> customAdapter =
        new TypeAdapter<Base>() {
          @Override
          public void write(JsonWriter out, Base value) throws IOException {
            out.beginObject();
            out.name("delegated");
            out.value(value.name);
            out.endObject();
          }

          @Override
          public Base read(JsonReader in) throws IOException {
            return null;
          }
        };

    SerializationDelegatingTypeAdapter<Base> delegatingAdapter =
        new SerializationDelegatingTypeAdapter<Base>() {
          @Override
          public TypeAdapter<Base> getSerializationDelegate() {
            return customAdapter;
          }

          @Override
          public void write(JsonWriter out, Base value) throws IOException {
            customAdapter.write(out, value);
          }

          @Override
          public Base read(JsonReader in) throws IOException {
            return null;
          }
        };

    TypeAdapterRuntimeTypeWrapper<Base> wrapper =
        new TypeAdapterRuntimeTypeWrapper<>(gson, delegatingAdapter, Base.class);

    StringWriter sw = new StringWriter();
    JsonWriter out = new JsonWriter(sw);
    wrapper.write(out, new Sub("Eve", 35));
    out.flush();
    assertThat(sw.toString()).isEqualTo("{\"delegated\":\"Eve\"}");
  }

  @Test
  public void testWriteWithSelfReferencingSerializationDelegatingAdapter() throws IOException {
    Gson gson = new Gson();

    SerializationDelegatingTypeAdapter<Base> selfAdapter =
        new SerializationDelegatingTypeAdapter<Base>() {
          @Override
          public TypeAdapter<Base> getSerializationDelegate() {
            return this;
          }

          @Override
          public void write(JsonWriter out, Base value) throws IOException {
            out.beginObject();
            out.name("self");
            out.value(value.name);
            out.endObject();
          }

          @Override
          public Base read(JsonReader in) throws IOException {
            return null;
          }
        };

    TypeAdapterRuntimeTypeWrapper<Base> wrapper =
        new TypeAdapterRuntimeTypeWrapper<>(gson, selfAdapter, Base.class);

    StringWriter sw = new StringWriter();
    JsonWriter out = new JsonWriter(sw);
    wrapper.write(out, new Sub("Frank", 40));
    out.flush();
    assertThat(sw.toString()).isEqualTo("{\"self\":\"Frank\"}");
  }
}
