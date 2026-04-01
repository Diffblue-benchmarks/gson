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

package com.google.gson;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;

public final class FutureTypeAdapterTest {

  @Test
  public void testSetDelegate() {
    Gson.FutureTypeAdapter<String> adapter = new Gson.FutureTypeAdapter<>();
    TypeAdapter<String> stringAdapter = new Gson().getAdapter(String.class);

    adapter.setDelegate(stringAdapter);

    assertThat(adapter.getSerializationDelegate()).isSameInstanceAs(stringAdapter);
  }

  @Test
  public void testSetDelegateTwiceThrows() {
    Gson.FutureTypeAdapter<String> adapter = new Gson.FutureTypeAdapter<>();
    TypeAdapter<String> stringAdapter = new Gson().getAdapter(String.class);
    adapter.setDelegate(stringAdapter);

    AssertionError e = assertThrows(AssertionError.class, () -> adapter.setDelegate(stringAdapter));
    assertThat(e).hasMessageThat().isEqualTo("Delegate is already set");
  }

  @Test
  public void testGetSerializationDelegateThrowsWhenNotSet() {
    Gson.FutureTypeAdapter<String> adapter = new Gson.FutureTypeAdapter<>();

    IllegalStateException e =
        assertThrows(IllegalStateException.class, () -> adapter.getSerializationDelegate());
    assertThat(e).hasMessageThat().contains("cyclic dependency");
  }

  @Test
  public void testReadDelegates() throws IOException {
    Gson.FutureTypeAdapter<String> adapter = new Gson.FutureTypeAdapter<>();
    TypeAdapter<String> stringAdapter = new Gson().getAdapter(String.class);
    adapter.setDelegate(stringAdapter);

    JsonReader reader = new JsonReader(new StringReader("\"hello\""));
    String result = adapter.read(reader);

    assertThat(result).isEqualTo("hello");
  }

  @Test
  public void testReadThrowsWhenDelegateNotSet() {
    Gson.FutureTypeAdapter<String> adapter = new Gson.FutureTypeAdapter<>();

    JsonReader reader = new JsonReader(new StringReader("\"hello\""));
    assertThrows(IllegalStateException.class, () -> adapter.read(reader));
  }

  @Test
  public void testWriteDelegates() throws IOException {
    Gson.FutureTypeAdapter<String> adapter = new Gson.FutureTypeAdapter<>();
    TypeAdapter<String> stringAdapter = new Gson().getAdapter(String.class);
    adapter.setDelegate(stringAdapter);

    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    adapter.write(writer, "world");
    writer.flush();

    assertThat(sw.toString()).isEqualTo("\"world\"");
  }

  @Test
  public void testWriteThrowsWhenDelegateNotSet() {
    Gson.FutureTypeAdapter<String> adapter = new Gson.FutureTypeAdapter<>();

    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    assertThrows(IllegalStateException.class, () -> adapter.write(writer, "value"));
  }
}
