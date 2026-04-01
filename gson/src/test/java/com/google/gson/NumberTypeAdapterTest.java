/*
 * Copyright (C) 2020 Google Inc.
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

import com.google.gson.internal.bind.NumberTypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;

public final class NumberTypeAdapterTest {

  @Test
  public void testGetFactoryLazilyParsedNumberReturnsSingleton() {
    TypeAdapterFactory f1 = NumberTypeAdapter.getFactory(ToNumberPolicy.LAZILY_PARSED_NUMBER);
    TypeAdapterFactory f2 = NumberTypeAdapter.getFactory(ToNumberPolicy.LAZILY_PARSED_NUMBER);

    assertThat(f1).isSameInstanceAs(f2);
  }

  @Test
  public void testGetFactoryOtherStrategyReturnsNewInstance() {
    TypeAdapterFactory f1 = NumberTypeAdapter.getFactory(ToNumberPolicy.DOUBLE);
    TypeAdapterFactory f2 = NumberTypeAdapter.getFactory(ToNumberPolicy.DOUBLE);

    assertThat(f1).isNotSameInstanceAs(f2);
  }

  @Test
  public void testGetFactoryCreateReturnsNullForNonNumberType() {
    TypeAdapterFactory factory = NumberTypeAdapter.getFactory(ToNumberPolicy.DOUBLE);
    Gson gson = new Gson();

    TypeAdapter<String> adapter = factory.create(gson, TypeToken.get(String.class));

    assertThat(adapter).isNull();
  }

  @Test
  public void testGetFactoryCreateReturnsAdapterForNumberType() {
    TypeAdapterFactory factory = NumberTypeAdapter.getFactory(ToNumberPolicy.DOUBLE);
    Gson gson = new Gson();

    TypeAdapter<Number> adapter = factory.create(gson, TypeToken.get(Number.class));

    assertThat(adapter).isNotNull();
  }

  @Test
  public void testReadNull() throws IOException {
    Gson gson = new GsonBuilder()
        .registerTypeAdapterFactory(NumberTypeAdapter.getFactory(ToNumberPolicy.DOUBLE))
        .create();
    TypeAdapter<Number> adapter = gson.getAdapter(Number.class);
    JsonReader reader = new JsonReader(new StringReader("null"));

    Number result = adapter.read(reader);

    assertThat(result).isNull();
  }

  @Test
  public void testReadNumberDouble() throws IOException {
    Gson gson = new GsonBuilder()
        .registerTypeAdapterFactory(NumberTypeAdapter.getFactory(ToNumberPolicy.DOUBLE))
        .create();
    TypeAdapter<Number> adapter = gson.getAdapter(Number.class);
    JsonReader reader = new JsonReader(new StringReader("42.5"));

    Number result = adapter.read(reader);

    assertThat(result).isEqualTo(42.5);
  }

  @Test
  public void testReadNumberBigDecimal() throws IOException {
    Gson gson = new GsonBuilder()
        .registerTypeAdapterFactory(NumberTypeAdapter.getFactory(ToNumberPolicy.BIG_DECIMAL))
        .create();
    TypeAdapter<Number> adapter = gson.getAdapter(Number.class);
    JsonReader reader = new JsonReader(new StringReader("12345678901234567890"));

    Number result = adapter.read(reader);

    assertThat(result.toString()).isEqualTo("12345678901234567890");
  }

  @Test
  public void testReadStringToken() throws IOException {
    Gson gson = new GsonBuilder()
        .registerTypeAdapterFactory(
            NumberTypeAdapter.getFactory(ToNumberPolicy.LAZILY_PARSED_NUMBER))
        .create();
    TypeAdapter<Number> adapter = gson.getAdapter(Number.class);
    JsonReader reader = new JsonReader(new StringReader("\"42\""));

    Number result = adapter.read(reader);

    assertThat(result.toString()).isEqualTo("42");
  }

  @Test
  public void testReadInvalidTokenThrowsJsonSyntaxException() throws IOException {
    Gson gson = new GsonBuilder()
        .registerTypeAdapterFactory(NumberTypeAdapter.getFactory(ToNumberPolicy.DOUBLE))
        .create();
    TypeAdapter<Number> adapter = gson.getAdapter(Number.class);
    JsonReader reader = new JsonReader(new StringReader("true"));

    assertThrows(JsonSyntaxException.class, () -> adapter.read(reader));
  }

  @Test
  public void testWrite() throws IOException {
    Gson gson = new GsonBuilder()
        .registerTypeAdapterFactory(NumberTypeAdapter.getFactory(ToNumberPolicy.DOUBLE))
        .create();
    TypeAdapter<Number> adapter = gson.getAdapter(Number.class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter jsonWriter = new JsonWriter(stringWriter);

    adapter.write(jsonWriter, 42.5);

    assertThat(stringWriter.toString()).isEqualTo("42.5");
  }

  @Test
  public void testWriteNull() throws IOException {
    Gson gson = new GsonBuilder()
        .registerTypeAdapterFactory(NumberTypeAdapter.getFactory(ToNumberPolicy.DOUBLE))
        .serializeNulls()
        .create();
    TypeAdapter<Number> adapter = gson.getAdapter(Number.class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter jsonWriter = new JsonWriter(stringWriter);

    adapter.write(jsonWriter, null);

    assertThat(stringWriter.toString()).isEqualTo("null");
  }
}
