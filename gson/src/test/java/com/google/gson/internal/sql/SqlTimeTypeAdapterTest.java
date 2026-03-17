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

package com.google.gson.internal.sql;

import static com.google.common.truth.Truth.assertThat;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Time;
import org.junit.Test;

public class SqlTimeTypeAdapterTest {

  @Test
  public void testFactoryCreatesAdapterForTimeClass() {
    Gson gson = new Gson();
    TypeAdapter<Time> adapter = SqlTimeTypeAdapter.FACTORY.create(gson, TypeToken.get(Time.class));
    assertThat(adapter).isNotNull();
    assertThat(adapter).isInstanceOf(SqlTimeTypeAdapter.class);
  }

  @Test
  public void testFactoryReturnsNullForOtherClasses() {
    Gson gson = new Gson();
    TypeAdapter<?> adapter = SqlTimeTypeAdapter.FACTORY.create(gson, TypeToken.get(String.class));
    assertThat(adapter).isNull();
  }

  @Test
  public void testReadNull() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Time> adapter = SqlTimeTypeAdapter.FACTORY.create(gson, TypeToken.get(Time.class));
    JsonReader reader = new JsonReader(new StringReader("null"));
    Time result = adapter.read(reader);
    assertThat(result).isNull();
  }

  @Test
  public void testReadValidTime() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Time> adapter = SqlTimeTypeAdapter.FACTORY.create(gson, TypeToken.get(Time.class));
    JsonReader reader = new JsonReader(new StringReader("\"10:30:45 AM\""));
    Time result = adapter.read(reader);
    assertThat(result).isNotNull();
  }

  @Test
  public void testReadInvalidTimeThrowsException() {
    Gson gson = new Gson();
    TypeAdapter<Time> adapter = SqlTimeTypeAdapter.FACTORY.create(gson, TypeToken.get(Time.class));
    JsonReader reader = new JsonReader(new StringReader("\"invalid-time\""));
    try {
      adapter.read(reader);
      throw new AssertionError("Expected JsonSyntaxException");
    } catch (JsonSyntaxException e) {
      assertThat(e.getMessage()).contains("Failed parsing");
      assertThat(e.getMessage()).contains("invalid-time");
      assertThat(e.getCause()).isInstanceOf(java.text.ParseException.class);
    } catch (IOException e) {
      throw new AssertionError("Unexpected IOException", e);
    }
  }

  @Test
  public void testWriteNull() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Time> adapter = SqlTimeTypeAdapter.FACTORY.create(gson, TypeToken.get(Time.class));
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, null);
    assertThat(stringWriter.toString()).isEqualTo("null");
  }

  @Test
  public void testWriteValidTime() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Time> adapter = SqlTimeTypeAdapter.FACTORY.create(gson, TypeToken.get(Time.class));
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    Time time = Time.valueOf("10:30:45");
    adapter.write(writer, time);
    String result = stringWriter.toString();
    assertThat(result).isNotEmpty();
    assertThat(result).contains(":");
  }
}
