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
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public final class ObjectTypeAdapterReadTest {

  private final Gson gson = new Gson();
  private final TypeAdapter<Object> adapter = gson.getAdapter(Object.class);

  @Test
  public void testReadString() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("\"hello\""));

    Object result = adapter.read(reader);

    assertThat(result).isEqualTo("hello");
  }

  @Test
  public void testReadNumber() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("42.0"));

    Object result = adapter.read(reader);

    assertThat(result).isInstanceOf(Double.class);
    assertThat(result).isEqualTo(42.0);
  }

  @Test
  public void testReadBoolean() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("true"));

    Object result = adapter.read(reader);

    assertThat(result).isEqualTo(true);
  }

  @Test
  public void testReadNull() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("null"));

    Object result = adapter.read(reader);

    assertThat(result).isNull();
  }

  @Test
  public void testReadEmptyArray() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[]"));

    Object result = adapter.read(reader);

    assertThat(result).isInstanceOf(List.class);
    assertThat((List<?>) result).isEmpty();
  }

  @Test
  public void testReadSimpleArray() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[1.0, \"two\", true]"));

    Object result = adapter.read(reader);

    assertThat(result).isInstanceOf(List.class);
    List<?> list = (List<?>) result;
    assertThat(list).hasSize(3);
    assertThat(list.get(0)).isEqualTo(1.0);
    assertThat(list.get(1)).isEqualTo("two");
    assertThat(list.get(2)).isEqualTo(true);
  }

  @Test
  public void testReadArrayWithNullElement() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[null, \"hello\"]"));

    Object result = adapter.read(reader);

    assertThat(result).isInstanceOf(List.class);
    List<?> list = (List<?>) result;
    assertThat(list.get(0)).isNull();
    assertThat(list.get(1)).isEqualTo("hello");
  }

  @Test
  public void testReadEmptyObject() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{}"));

    Object result = adapter.read(reader);

    assertThat(result).isInstanceOf(Map.class);
    assertThat((Map<?, ?>) result).isEmpty();
  }

  @Test
  public void testReadSimpleObject() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{\"name\":\"John\",\"age\":30.0}"));

    Object result = adapter.read(reader);

    assertThat(result).isInstanceOf(Map.class);
    Map<?, ?> map = (Map<?, ?>) result;
    assertThat(map.get("name")).isEqualTo("John");
    assertThat(map.get("age")).isEqualTo(30.0);
  }

  @Test
  public void testReadNestedArray() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[[1.0, 2.0], [3.0]]"));

    Object result = adapter.read(reader);

    assertThat(result).isInstanceOf(List.class);
    List<?> outer = (List<?>) result;
    assertThat(outer).hasSize(2);
    assertThat(outer.get(0)).isInstanceOf(List.class);
    List<?> inner = (List<?>) outer.get(0);
    assertThat(inner.get(0)).isEqualTo(1.0);
    assertThat(inner.get(1)).isEqualTo(2.0);
  }

  @Test
  public void testReadNestedObject() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{\"child\":{\"key\":\"value\"}}"));

    Object result = adapter.read(reader);

    assertThat(result).isInstanceOf(Map.class);
    Map<?, ?> outer = (Map<?, ?>) result;
    assertThat(outer.get("child")).isInstanceOf(Map.class);
    Map<?, ?> inner = (Map<?, ?>) outer.get("child");
    assertThat(inner.get("key")).isEqualTo("value");
  }

  @Test
  public void testReadArrayContainingObject() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[{\"x\":1.0}]"));

    Object result = adapter.read(reader);

    assertThat(result).isInstanceOf(List.class);
    List<?> list = (List<?>) result;
    assertThat(list).hasSize(1);
    assertThat(list.get(0)).isInstanceOf(Map.class);
    Map<?, ?> map = (Map<?, ?>) list.get(0);
    assertThat(map.get("x")).isEqualTo(1.0);
  }

  @Test
  public void testReadObjectContainingArray() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{\"items\":[1.0,2.0]}"));

    Object result = adapter.read(reader);

    assertThat(result).isInstanceOf(Map.class);
    Map<?, ?> map = (Map<?, ?>) result;
    assertThat(map.get("items")).isInstanceOf(List.class);
    List<?> list = (List<?>) map.get("items");
    assertThat(list.get(0)).isEqualTo(1.0);
    assertThat(list.get(1)).isEqualTo(2.0);
  }

  @Test
  public void testReadTerminalThrowsForUnexpectedToken() throws IOException {
    // Manually advance past the opening brace so the reader is positioned at a NAME token.
    // readTerminal has no case for NAME and should throw IllegalStateException.
    JsonReader reader = new JsonReader(new StringReader("{\"key\": \"value\"}"));
    reader.beginObject();

    assertThrows(IllegalStateException.class, () -> adapter.read(reader));
  }
}
