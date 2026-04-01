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
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringWriter;
import org.junit.Test;

public final class ObjectTypeAdapterWriteTest {

  @Test
  public void testWriteNull() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Object> adapter = gson.getAdapter(Object.class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter jsonWriter = new JsonWriter(stringWriter);

    adapter.write(jsonWriter, null);

    assertThat(stringWriter.toString()).isEqualTo("null");
  }

  @Test
  public void testWriteObjectUsesEmptyJsonObject() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Object> adapter = gson.getAdapter(Object.class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter jsonWriter = new JsonWriter(stringWriter);

    adapter.write(jsonWriter, new Object());

    assertThat(stringWriter.toString()).isEqualTo("{}");
  }

  @Test
  public void testWriteStringDelegatesToStringAdapter() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Object> adapter = gson.getAdapter(Object.class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter jsonWriter = new JsonWriter(stringWriter);

    adapter.write(jsonWriter, "hello");

    assertThat(stringWriter.toString()).isEqualTo("\"hello\"");
  }

  @Test
  public void testWriteIntegerDelegatesToNumberAdapter() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Object> adapter = gson.getAdapter(Object.class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter jsonWriter = new JsonWriter(stringWriter);

    adapter.write(jsonWriter, 42);

    assertThat(stringWriter.toString()).isEqualTo("42");
  }

  @Test
  public void testWriteBooleanDelegatesToBooleanAdapter() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Object> adapter = gson.getAdapter(Object.class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter jsonWriter = new JsonWriter(stringWriter);

    adapter.write(jsonWriter, true);

    assertThat(stringWriter.toString()).isEqualTo("true");
  }
}
