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

package com.google.gson;

import static com.google.common.truth.Truth.assertThat;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;

public class TypeAdapterTest {

  @Test
  public void testNullSafeWriteWithNullValue() throws IOException {
    TypeAdapter<String> adapter = new TypeAdapter<String>() {
      @Override
      public void write(JsonWriter out, String value) throws IOException {
        out.value(value);
      }

      @Override
      public String read(JsonReader in) throws IOException {
        return in.nextString();
      }
    };

    TypeAdapter<String> nullSafeAdapter = adapter.nullSafe();
    StringWriter stringWriter = new StringWriter();
    JsonWriter jsonWriter = new JsonWriter(stringWriter);

    nullSafeAdapter.write(jsonWriter, null);

    assertThat(stringWriter.toString()).isEqualTo("null");
  }

  @Test
  public void testNullSafeWriteWithNonNullValue() throws IOException {
    TypeAdapter<String> adapter = new TypeAdapter<String>() {
      @Override
      public void write(JsonWriter out, String value) throws IOException {
        out.value(value);
      }

      @Override
      public String read(JsonReader in) throws IOException {
        return in.nextString();
      }
    };

    TypeAdapter<String> nullSafeAdapter = adapter.nullSafe();
    StringWriter stringWriter = new StringWriter();
    JsonWriter jsonWriter = new JsonWriter(stringWriter);

    nullSafeAdapter.write(jsonWriter, "test");

    assertThat(stringWriter.toString()).isEqualTo("\"test\"");
  }

  @Test
  public void testNullSafeReadWithNullToken() throws IOException {
    TypeAdapter<String> adapter = new TypeAdapter<String>() {
      @Override
      public void write(JsonWriter out, String value) throws IOException {
        out.value(value);
      }

      @Override
      public String read(JsonReader in) throws IOException {
        return in.nextString();
      }
    };

    TypeAdapter<String> nullSafeAdapter = adapter.nullSafe();
    JsonReader jsonReader = new JsonReader(new StringReader("null"));

    String result = nullSafeAdapter.read(jsonReader);

    assertThat(result).isNull();
  }

  @Test
  public void testNullSafeReadWithNonNullToken() throws IOException {
    TypeAdapter<String> adapter = new TypeAdapter<String>() {
      @Override
      public void write(JsonWriter out, String value) throws IOException {
        out.value(value);
      }

      @Override
      public String read(JsonReader in) throws IOException {
        return in.nextString();
      }
    };

    TypeAdapter<String> nullSafeAdapter = adapter.nullSafe();
    JsonReader jsonReader = new JsonReader(new StringReader("\"test\""));

    String result = nullSafeAdapter.read(jsonReader);

    assertThat(result).isEqualTo("test");
  }

  @Test
  public void testNullSafeToString() {
    TypeAdapter<String> adapter = new TypeAdapter<String>() {
      @Override
      public void write(JsonWriter out, String value) throws IOException {
        out.value(value);
      }

      @Override
      public String read(JsonReader in) throws IOException {
        return in.nextString();
      }

      @Override
      public String toString() {
        return "CustomStringAdapter";
      }
    };

    TypeAdapter<String> nullSafeAdapter = adapter.nullSafe();

    String toStringResult = nullSafeAdapter.toString();

    assertThat(toStringResult).isEqualTo("NullSafeTypeAdapter[CustomStringAdapter]");
  }

  @Test
  public void testNullSafeIdempotent() {
    TypeAdapter<String> adapter = new TypeAdapter<String>() {
      @Override
      public void write(JsonWriter out, String value) throws IOException {
        out.value(value);
      }

      @Override
      public String read(JsonReader in) throws IOException {
        return in.nextString();
      }
    };

    TypeAdapter<String> nullSafe1 = adapter.nullSafe();
    TypeAdapter<String> nullSafe2 = nullSafe1.nullSafe();

    assertThat(nullSafe1).isSameInstanceAs(nullSafe2);
  }
}
