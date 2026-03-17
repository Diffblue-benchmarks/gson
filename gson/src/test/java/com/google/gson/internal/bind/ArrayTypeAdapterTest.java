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
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;

public class ArrayTypeAdapterTest {

  @Test
  public void testConstructor() {
    Gson gson = new Gson();
    TypeAdapter<String> stringAdapter = gson.getAdapter(String.class);
    ArrayTypeAdapter<String> adapter = new ArrayTypeAdapter<>(gson, stringAdapter, String.class);

    assertThat(adapter).isNotNull();
  }

  @Test
  public void testReadNull() throws IOException {
    Gson gson = new Gson();
    String[] result = gson.fromJson("null", String[].class);

    assertThat(result).isNull();
  }

  @Test
  public void testReadEmptyArray() throws IOException {
    Gson gson = new Gson();
    String[] result = gson.fromJson("[]", String[].class);

    assertThat(result).isNotNull();
    assertThat(result).hasLength(0);
  }

  @Test
  public void testReadStringArray() throws IOException {
    Gson gson = new Gson();
    String[] result = gson.fromJson("[\"a\",\"b\",\"c\"]", String[].class);

    assertThat(result).isNotNull();
    assertThat(result).hasLength(3);
    assertThat(result[0]).isEqualTo("a");
    assertThat(result[1]).isEqualTo("b");
    assertThat(result[2]).isEqualTo("c");
  }

  @Test
  public void testReadIntArray() throws IOException {
    Gson gson = new Gson();
    int[] result = gson.fromJson("[1,2,3]", int[].class);

    assertThat(result).isNotNull();
    assertThat(result).hasLength(3);
    assertThat(result[0]).isEqualTo(1);
    assertThat(result[1]).isEqualTo(2);
    assertThat(result[2]).isEqualTo(3);
  }

  @Test
  public void testReadBooleanArray() throws IOException {
    Gson gson = new Gson();
    boolean[] result = gson.fromJson("[true,false,true]", boolean[].class);

    assertThat(result).isNotNull();
    assertThat(result).hasLength(3);
    assertThat(result[0]).isTrue();
    assertThat(result[1]).isFalse();
    assertThat(result[2]).isTrue();
  }

  @Test
  public void testReadDoubleArray() throws IOException {
    Gson gson = new Gson();
    double[] result = gson.fromJson("[1.5,2.5,3.5]", double[].class);

    assertThat(result).isNotNull();
    assertThat(result).hasLength(3);
    assertThat(result[0]).isEqualTo(1.5);
    assertThat(result[1]).isEqualTo(2.5);
    assertThat(result[2]).isEqualTo(3.5);
  }

  @Test
  public void testReadIntegerArray() throws IOException {
    Gson gson = new Gson();
    Integer[] result = gson.fromJson("[1,2,3]", Integer[].class);

    assertThat(result).isNotNull();
    assertThat(result).hasLength(3);
    assertThat(result[0]).isEqualTo(1);
    assertThat(result[1]).isEqualTo(2);
    assertThat(result[2]).isEqualTo(3);
  }

  @Test
  public void testReadArrayWithNullElements() throws IOException {
    Gson gson = new Gson();
    String[] result = gson.fromJson("[\"a\",null,\"c\"]", String[].class);

    assertThat(result).isNotNull();
    assertThat(result).hasLength(3);
    assertThat(result[0]).isEqualTo("a");
    assertThat(result[1]).isNull();
    assertThat(result[2]).isEqualTo("c");
  }

  @Test
  public void testWriteNull() throws IOException {
    Gson gson = new Gson();
    String json = gson.toJson(null, String[].class);

    assertThat(json).isEqualTo("null");
  }

  @Test
  public void testWriteEmptyArray() throws IOException {
    Gson gson = new Gson();
    String[] array = new String[0];
    String json = gson.toJson(array);

    assertThat(json).isEqualTo("[]");
  }

  @Test
  public void testWriteStringArray() throws IOException {
    Gson gson = new Gson();
    String[] array = {"a", "b", "c"};
    String json = gson.toJson(array);

    assertThat(json).isEqualTo("[\"a\",\"b\",\"c\"]");
  }

  @Test
  public void testWriteIntArray() throws IOException {
    Gson gson = new Gson();
    int[] array = {1, 2, 3};
    String json = gson.toJson(array);

    assertThat(json).isEqualTo("[1,2,3]");
  }

  @Test
  public void testWriteBooleanArray() throws IOException {
    Gson gson = new Gson();
    boolean[] array = {true, false, true};
    String json = gson.toJson(array);

    assertThat(json).isEqualTo("[true,false,true]");
  }

  @Test
  public void testWriteDoubleArray() throws IOException {
    Gson gson = new Gson();
    double[] array = {1.5, 2.5, 3.5};
    String json = gson.toJson(array);

    assertThat(json).isEqualTo("[1.5,2.5,3.5]");
  }

  @Test
  public void testWriteIntegerArray() throws IOException {
    Gson gson = new Gson();
    Integer[] array = {1, 2, 3};
    String json = gson.toJson(array);

    assertThat(json).isEqualTo("[1,2,3]");
  }

  @Test
  public void testWriteArrayWithNullElements() throws IOException {
    Gson gson = new Gson();
    String[] array = {"a", null, "c"};
    String json = gson.toJson(array);

    assertThat(json).isEqualTo("[\"a\",null,\"c\"]");
  }

  @Test
  public void testRoundTripStringArray() throws IOException {
    Gson gson = new Gson();
    String[] original = {"hello", "world"};
    String json = gson.toJson(original);
    String[] result = gson.fromJson(json, String[].class);

    assertThat(result).isNotNull();
    assertThat(result).hasLength(2);
    assertThat(result[0]).isEqualTo("hello");
    assertThat(result[1]).isEqualTo("world");
  }

  @Test
  public void testRoundTripIntArray() throws IOException {
    Gson gson = new Gson();
    int[] original = {10, 20, 30};
    String json = gson.toJson(original);
    int[] result = gson.fromJson(json, int[].class);

    assertThat(result).isNotNull();
    assertThat(result).hasLength(3);
    assertThat(result[0]).isEqualTo(10);
    assertThat(result[1]).isEqualTo(20);
    assertThat(result[2]).isEqualTo(30);
  }

  @Test
  public void testReadDirectly() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<String> stringAdapter = gson.getAdapter(String.class);
    ArrayTypeAdapter<String> adapter = new ArrayTypeAdapter<>(gson, stringAdapter, String.class);

    JsonReader reader = new JsonReader(new StringReader("[\"x\",\"y\"]"));
    Object result = adapter.read(reader);

    assertThat(result).isNotNull();
    assertThat(result).isInstanceOf(String[].class);
    String[] array = (String[]) result;
    assertThat(array).hasLength(2);
    assertThat(array[0]).isEqualTo("x");
    assertThat(array[1]).isEqualTo("y");
  }

  @Test
  public void testReadDirectlyPrimitiveArray() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Integer> intAdapter = gson.getAdapter(Integer.class);
    ArrayTypeAdapter<Integer> adapter = new ArrayTypeAdapter<>(gson, intAdapter, int.class);

    JsonReader reader = new JsonReader(new StringReader("[5,10,15]"));
    Object result = adapter.read(reader);

    assertThat(result).isNotNull();
    assertThat(result).isInstanceOf(int[].class);
    int[] array = (int[]) result;
    assertThat(array).hasLength(3);
    assertThat(array[0]).isEqualTo(5);
    assertThat(array[1]).isEqualTo(10);
    assertThat(array[2]).isEqualTo(15);
  }

  @Test
  public void testWriteDirectly() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<String> stringAdapter = gson.getAdapter(String.class);
    ArrayTypeAdapter<String> adapter = new ArrayTypeAdapter<>(gson, stringAdapter, String.class);

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    String[] array = {"foo", "bar"};
    adapter.write(writer, array);

    assertThat(stringWriter.toString()).isEqualTo("[\"foo\",\"bar\"]");
  }

  @Test
  public void testWriteDirectlyNull() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<String> stringAdapter = gson.getAdapter(String.class);
    ArrayTypeAdapter<String> adapter = new ArrayTypeAdapter<>(gson, stringAdapter, String.class);

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, null);

    assertThat(stringWriter.toString()).isEqualTo("null");
  }

  @Test
  public void testReadDirectlyNull() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<String> stringAdapter = gson.getAdapter(String.class);
    ArrayTypeAdapter<String> adapter = new ArrayTypeAdapter<>(gson, stringAdapter, String.class);

    JsonReader reader = new JsonReader(new StringReader("null"));
    Object result = adapter.read(reader);

    assertThat(result).isNull();
  }
}
