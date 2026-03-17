/*
 * Copyright (C) 2026 Google Inc.
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

package com.google.gson;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;

public class GsonFutureTypeAdapterTest {

  @Test
  public void testSetDelegate() {
    Gson.FutureTypeAdapter<String> futureAdapter = new Gson.FutureTypeAdapter<>();
    TypeAdapter<String> mockAdapter = new TypeAdapter<String>() {
      @Override
      public void write(JsonWriter out, String value) throws IOException {
        out.value(value);
      }

      @Override
      public String read(JsonReader in) throws IOException {
        return in.nextString();
      }
    };

    futureAdapter.setDelegate(mockAdapter);

    assertThat(futureAdapter.getSerializationDelegate()).isSameInstanceAs(mockAdapter);
  }

  @Test
  public void testSetDelegateTwiceThrowsAssertionError() {
    Gson.FutureTypeAdapter<String> futureAdapter = new Gson.FutureTypeAdapter<>();
    TypeAdapter<String> mockAdapter = new TypeAdapter<String>() {
      @Override
      public void write(JsonWriter out, String value) throws IOException {
        out.value(value);
      }

      @Override
      public String read(JsonReader in) throws IOException {
        return in.nextString();
      }
    };

    futureAdapter.setDelegate(mockAdapter);

    AssertionError error = assertThrows(AssertionError.class, () -> {
      futureAdapter.setDelegate(mockAdapter);
    });
    assertThat(error.getMessage()).isEqualTo("Delegate is already set");
  }

  @Test
  public void testGetSerializationDelegateWithoutSettingThrowsIllegalStateException() {
    Gson.FutureTypeAdapter<String> futureAdapter = new Gson.FutureTypeAdapter<>();

    IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
      futureAdapter.getSerializationDelegate();
    });
    assertThat(exception.getMessage()).contains("Adapter for type with cyclic dependency has been used");
    assertThat(exception.getMessage()).contains("before dependency has been resolved");
  }

  @Test
  public void testReadWithoutSettingDelegateThrowsIllegalStateException() throws IOException {
    Gson.FutureTypeAdapter<String> futureAdapter = new Gson.FutureTypeAdapter<>();
    JsonReader reader = new JsonReader(new StringReader("\"test\""));

    IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
      futureAdapter.read(reader);
    });
    assertThat(exception.getMessage()).contains("Adapter for type with cyclic dependency has been used");
  }

  @Test
  public void testReadWithDelegate() throws IOException {
    Gson.FutureTypeAdapter<String> futureAdapter = new Gson.FutureTypeAdapter<>();
    TypeAdapter<String> mockAdapter = new TypeAdapter<String>() {
      @Override
      public void write(JsonWriter out, String value) throws IOException {
        out.value(value);
      }

      @Override
      public String read(JsonReader in) throws IOException {
        return "delegated:" + in.nextString();
      }
    };

    futureAdapter.setDelegate(mockAdapter);

    JsonReader reader = new JsonReader(new StringReader("\"test\""));
    String result = futureAdapter.read(reader);
    assertThat(result).isEqualTo("delegated:test");
  }

  @Test
  public void testWriteWithoutSettingDelegateThrowsIllegalStateException() {
    Gson.FutureTypeAdapter<String> futureAdapter = new Gson.FutureTypeAdapter<>();
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
      futureAdapter.write(writer, "test");
    });
    assertThat(exception.getMessage()).contains("Adapter for type with cyclic dependency has been used");
  }

  @Test
  public void testWriteWithDelegate() throws IOException {
    Gson.FutureTypeAdapter<String> futureAdapter = new Gson.FutureTypeAdapter<>();
    TypeAdapter<String> mockAdapter = new TypeAdapter<String>() {
      @Override
      public void write(JsonWriter out, String value) throws IOException {
        out.value("delegated:" + value);
      }

      @Override
      public String read(JsonReader in) throws IOException {
        return in.nextString();
      }
    };

    futureAdapter.setDelegate(mockAdapter);

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    futureAdapter.write(writer, "test");

    assertThat(stringWriter.toString()).isEqualTo("\"delegated:test\"");
  }
}
