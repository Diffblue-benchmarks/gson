/*
 * Copyright (C) 2026 Google Inc.
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
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.Test;

public class CollectionTypeAdapterFactoryTest {

  @Test
  public void testReadNull() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Collection<String>> adapter =
        gson.getAdapter(new TypeToken<Collection<String>>() {});
    JsonReader reader = new JsonReader(new StringReader("null"));
    Collection<String> result = adapter.read(reader);
    assertThat(result).isNull();
  }

  @Test
  public void testReadEmptyArray() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Collection<String>> adapter =
        gson.getAdapter(new TypeToken<Collection<String>>() {});
    JsonReader reader = new JsonReader(new StringReader("[]"));
    Collection<String> result = adapter.read(reader);
    assertThat(result).isNotNull();
    assertThat(result).isEmpty();
  }

  @Test
  public void testReadArrayWithElements() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Collection<String>> adapter =
        gson.getAdapter(new TypeToken<Collection<String>>() {});
    JsonReader reader = new JsonReader(new StringReader("[\"one\",\"two\",\"three\"]"));
    Collection<String> result = adapter.read(reader);
    assertThat(result).isNotNull();
    assertThat(result).hasSize(3);
    assertThat(result).containsExactly("one", "two", "three").inOrder();
  }

  @Test
  public void testReadArrayWithIntegerElements() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Collection<Integer>> adapter =
        gson.getAdapter(new TypeToken<Collection<Integer>>() {});
    JsonReader reader = new JsonReader(new StringReader("[1,2,3,4,5]"));
    Collection<Integer> result = adapter.read(reader);
    assertThat(result).isNotNull();
    assertThat(result).hasSize(5);
    assertThat(result).containsExactly(1, 2, 3, 4, 5).inOrder();
  }

  @Test
  public void testReadArrayWithNullElements() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Collection<String>> adapter =
        gson.getAdapter(new TypeToken<Collection<String>>() {});
    JsonReader reader = new JsonReader(new StringReader("[\"one\",null,\"three\"]"));
    Collection<String> result = adapter.read(reader);
    assertThat(result).isNotNull();
    assertThat(result).hasSize(3);
    assertThat(result).containsExactly("one", null, "three").inOrder();
  }

  @Test
  public void testWriteNull() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Collection<String>> adapter =
        gson.getAdapter(new TypeToken<Collection<String>>() {});
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, null);
    assertThat(stringWriter.toString()).isEqualTo("null");
  }

  @Test
  public void testWriteEmptyCollection() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Collection<String>> adapter =
        gson.getAdapter(new TypeToken<Collection<String>>() {});
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    Collection<String> collection = new ArrayList<String>();
    adapter.write(writer, collection);
    assertThat(stringWriter.toString()).isEqualTo("[]");
  }

  @Test
  public void testWriteCollectionWithElements() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Collection<String>> adapter =
        gson.getAdapter(new TypeToken<Collection<String>>() {});
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    Collection<String> collection = new ArrayList<String>();
    collection.add("one");
    collection.add("two");
    collection.add("three");
    adapter.write(writer, collection);
    assertThat(stringWriter.toString()).isEqualTo("[\"one\",\"two\",\"three\"]");
  }

  @Test
  public void testWriteCollectionWithIntegerElements() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Collection<Integer>> adapter =
        gson.getAdapter(new TypeToken<Collection<Integer>>() {});
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    Collection<Integer> collection = new ArrayList<Integer>();
    collection.add(1);
    collection.add(2);
    collection.add(3);
    adapter.write(writer, collection);
    assertThat(stringWriter.toString()).isEqualTo("[1,2,3]");
  }

  @Test
  public void testWriteCollectionWithNullElements() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Collection<String>> adapter =
        gson.getAdapter(new TypeToken<Collection<String>>() {});
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    Collection<String> collection = new ArrayList<String>();
    collection.add("one");
    collection.add(null);
    collection.add("three");
    adapter.write(writer, collection);
    assertThat(stringWriter.toString()).isEqualTo("[\"one\",null,\"three\"]");
  }

  @Test
  public void testReadWriteRoundTrip() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<List<String>> adapter = gson.getAdapter(new TypeToken<List<String>>() {});
    List<String> original = new ArrayList<String>();
    original.add("first");
    original.add("second");
    original.add("third");

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, original);

    String json = stringWriter.toString();
    JsonReader reader = new JsonReader(new StringReader(json));
    List<String> result = adapter.read(reader);

    assertThat(result).containsExactlyElementsIn(original).inOrder();
  }
}
