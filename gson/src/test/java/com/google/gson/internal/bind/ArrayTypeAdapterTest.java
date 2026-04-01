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
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Before;
import org.junit.Test;

public final class ArrayTypeAdapterTest {

  private Gson gson;

  @Before
  public void setUp() {
    gson = new GsonBuilder().create();
  }

  @Test
  public void testReadNullReturnsNull() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("null"));
    ArrayTypeAdapter<String> adapter =
        new ArrayTypeAdapter<>(gson, gson.getAdapter(String.class), String.class);
    Object result = adapter.read(reader);
    assertThat(result).isNull();
  }

  @Test
  public void testReadStringArray() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[\"a\",\"b\",\"c\"]"));
    ArrayTypeAdapter<String> adapter =
        new ArrayTypeAdapter<>(gson, gson.getAdapter(String.class), String.class);
    Object result = adapter.read(reader);
    assertThat(result).isInstanceOf(String[].class);
    String[] array = (String[]) result;
    assertThat(array).asList().containsExactly("a", "b", "c").inOrder();
  }

  @Test
  public void testReadEmptyArray() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[]"));
    ArrayTypeAdapter<String> adapter =
        new ArrayTypeAdapter<>(gson, gson.getAdapter(String.class), String.class);
    Object result = adapter.read(reader);
    assertThat(result).isInstanceOf(String[].class);
    String[] array = (String[]) result;
    assertThat(array).hasLength(0);
  }

  @Test
  public void testReadPrimitiveIntArray() {
    int[] result = gson.fromJson("[1,2,3]", int[].class);
    assertThat(result).isEqualTo(new int[] {1, 2, 3});
  }

  @Test
  public void testReadPrimitiveBooleanArray() {
    boolean[] result = gson.fromJson("[true,false,true]", boolean[].class);
    assertThat(result).isEqualTo(new boolean[] {true, false, true});
  }

  @Test
  public void testReadArrayViaGson() {
    String[] result = gson.fromJson("[\"x\",\"y\"]", String[].class);
    assertThat(result).asList().containsExactly("x", "y").inOrder();
  }

  @Test
  public void testWriteNullValue() throws IOException {
    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    ArrayTypeAdapter<String> adapter =
        new ArrayTypeAdapter<>(gson, gson.getAdapter(String.class), String.class);
    adapter.write(writer, null);
    writer.flush();
    assertThat(sw.toString()).isEqualTo("null");
  }

  @Test
  public void testWriteStringArray() throws IOException {
    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    ArrayTypeAdapter<String> adapter =
        new ArrayTypeAdapter<>(gson, gson.getAdapter(String.class), String.class);
    adapter.write(writer, new String[] {"foo", "bar"});
    writer.flush();
    assertThat(sw.toString()).isEqualTo("[\"foo\",\"bar\"]");
  }

  @Test
  public void testWriteEmptyArray() throws IOException {
    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    ArrayTypeAdapter<String> adapter =
        new ArrayTypeAdapter<>(gson, gson.getAdapter(String.class), String.class);
    adapter.write(writer, new String[0]);
    writer.flush();
    assertThat(sw.toString()).isEqualTo("[]");
  }

  @Test
  public void testWritePrimitiveIntArray() {
    String result = gson.toJson(new int[] {4, 5, 6});
    assertThat(result).isEqualTo("[4,5,6]");
  }

  @Test
  public void testRoundTripStringArray() {
    String[] original = {"hello", "world"};
    String json = gson.toJson(original, String[].class);
    String[] deserialized = gson.fromJson(json, String[].class);
    assertThat(deserialized).asList().containsExactlyElementsIn(original).inOrder();
  }

  @Test
  public void testRoundTripIntegerArray() {
    Integer[] original = {10, 20, 30};
    String json = gson.toJson(original, Integer[].class);
    Integer[] deserialized = gson.fromJson(json, Integer[].class);
    assertThat(deserialized).asList().containsExactlyElementsIn(original).inOrder();
  }

  @Test
  public void testReadArrayOfArrays() {
    int[][] result = gson.fromJson("[[1,2],[3,4]]", int[][].class);
    assertThat(result[0]).isEqualTo(new int[] {1, 2});
    assertThat(result[1]).isEqualTo(new int[] {3, 4});
  }

  @Test
  public void testFactoryReturnsNullForNonArrayType() {
    com.google.gson.TypeAdapter<?> adapter =
        ArrayTypeAdapter.FACTORY.create(gson, TypeToken.get(String.class));
    assertThat(adapter).isNull();
  }

  @Test
  public void testFactoryCreatesAdapterForArrayType() {
    com.google.gson.TypeAdapter<?> adapter =
        ArrayTypeAdapter.FACTORY.create(gson, TypeToken.get(String[].class));
    assertThat(adapter).isNotNull();
    assertThat(adapter).isInstanceOf(ArrayTypeAdapter.class);
  }
}
