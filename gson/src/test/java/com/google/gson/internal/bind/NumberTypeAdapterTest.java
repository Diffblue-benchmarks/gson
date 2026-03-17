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
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.ToNumberPolicy;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;

public class NumberTypeAdapterTest {

  @Test
  public void testGetFactoryWithLazilyParsedNumber() {
    TypeAdapterFactory factory1 = NumberTypeAdapter.getFactory(ToNumberPolicy.LAZILY_PARSED_NUMBER);
    TypeAdapterFactory factory2 = NumberTypeAdapter.getFactory(ToNumberPolicy.LAZILY_PARSED_NUMBER);
    assertThat(factory1).isSameInstanceAs(factory2);
  }

  @Test
  public void testGetFactoryWithDifferentStrategy() {
    TypeAdapterFactory factory1 = NumberTypeAdapter.getFactory(ToNumberPolicy.DOUBLE);
    TypeAdapterFactory factory2 = NumberTypeAdapter.getFactory(ToNumberPolicy.DOUBLE);
    assertThat(factory1).isNotSameInstanceAs(factory2);
  }

  @Test
  public void testFactoryCreatesAdapterForNumberType() {
    TypeAdapterFactory factory = NumberTypeAdapter.getFactory(ToNumberPolicy.LAZILY_PARSED_NUMBER);
    Gson gson = new Gson();
    TypeAdapter<Number> adapter = factory.create(gson, TypeToken.get(Number.class));
    assertThat(adapter).isNotNull();
  }

  @Test
  public void testFactoryReturnsNullForNonNumberType() {
    TypeAdapterFactory factory = NumberTypeAdapter.getFactory(ToNumberPolicy.LAZILY_PARSED_NUMBER);
    Gson gson = new Gson();
    TypeAdapter<String> adapter = factory.create(gson, TypeToken.get(String.class));
    assertThat(adapter).isNull();
  }

  @Test
  public void testReadNull() throws IOException {
    Gson gson = new GsonBuilder()
        .setNumberToNumberStrategy(ToNumberPolicy.LAZILY_PARSED_NUMBER)
        .create();
    TypeAdapter<Number> adapter = gson.getAdapter(Number.class);
    JsonReader reader = new JsonReader(new StringReader("null"));
    Number result = adapter.read(reader);
    assertThat(result).isNull();
  }

  @Test
  public void testReadNumberToken() throws IOException {
    Gson gson = new GsonBuilder()
        .setNumberToNumberStrategy(ToNumberPolicy.DOUBLE)
        .create();
    TypeAdapter<Number> adapter = gson.getAdapter(Number.class);
    JsonReader reader = new JsonReader(new StringReader("42.5"));
    Number result = adapter.read(reader);
    assertThat(result).isEqualTo(42.5);
  }

  @Test
  public void testReadStringToken() throws IOException {
    Gson gson = new GsonBuilder()
        .setNumberToNumberStrategy(ToNumberPolicy.DOUBLE)
        .create();
    TypeAdapter<Number> adapter = gson.getAdapter(Number.class);
    JsonReader reader = new JsonReader(new StringReader("\"123.45\""));
    Number result = adapter.read(reader);
    assertThat(result).isEqualTo(123.45);
  }

  @Test
  public void testReadInvalidToken() throws IOException {
    Gson gson = new GsonBuilder()
        .setNumberToNumberStrategy(ToNumberPolicy.LAZILY_PARSED_NUMBER)
        .create();
    TypeAdapter<Number> adapter = gson.getAdapter(Number.class);
    JsonReader reader = new JsonReader(new StringReader("true"));
    try {
      adapter.read(reader);
      throw new AssertionError("Expected JsonSyntaxException");
    } catch (JsonSyntaxException expected) {
      assertThat(expected.getMessage()).contains("Expecting number, got: BOOLEAN");
    }
  }

  @Test
  public void testWriteNumber() throws IOException {
    Gson gson = new GsonBuilder()
        .setNumberToNumberStrategy(ToNumberPolicy.LAZILY_PARSED_NUMBER)
        .create();
    TypeAdapter<Number> adapter = gson.getAdapter(Number.class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, 123);
    assertThat(stringWriter.toString()).isEqualTo("123");
  }

  @Test
  public void testWriteDoubleNumber() throws IOException {
    Gson gson = new GsonBuilder()
        .setNumberToNumberStrategy(ToNumberPolicy.DOUBLE)
        .create();
    TypeAdapter<Number> adapter = gson.getAdapter(Number.class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, 123.456);
    assertThat(stringWriter.toString()).isEqualTo("123.456");
  }

  @Test
  public void testWriteNull() throws IOException {
    Gson gson = new GsonBuilder()
        .setNumberToNumberStrategy(ToNumberPolicy.LAZILY_PARSED_NUMBER)
        .create();
    TypeAdapter<Number> adapter = gson.getAdapter(Number.class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, null);
    assertThat(stringWriter.toString()).isEqualTo("null");
  }

  @Test
  public void testReadWriteRoundTrip() throws IOException {
    Gson gson = new GsonBuilder()
        .setNumberToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE)
        .create();
    TypeAdapter<Number> adapter = gson.getAdapter(Number.class);
    Number original = 42;

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, original);

    String json = stringWriter.toString();
    JsonReader reader = new JsonReader(new StringReader(json));
    Number result = adapter.read(reader);

    assertThat(result).isEqualTo(original);
  }

  @Test
  public void testReadBigDecimalStrategy() throws IOException {
    Gson gson = new GsonBuilder()
        .setNumberToNumberStrategy(ToNumberPolicy.BIG_DECIMAL)
        .create();
    TypeAdapter<Number> adapter = gson.getAdapter(Number.class);
    JsonReader reader = new JsonReader(new StringReader("123.456789012345678901234567890"));
    Number result = adapter.read(reader);
    assertThat(result.toString()).isEqualTo("123.456789012345678901234567890");
  }
}
