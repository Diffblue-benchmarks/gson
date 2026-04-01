/*
 * Copyright (C) 2023 Google Inc.
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

public final class NullSafeTypeAdapterTest {

  private static final TypeAdapter<String> STRING_ADAPTER =
      new TypeAdapter<String>() {
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
          return "StringAdapter";
        }
      };

  @Test
  public void testWriteNullValue() throws IOException {
    TypeAdapter<String> nullSafe = STRING_ADAPTER.nullSafe();
    StringWriter sw = new StringWriter();
    JsonWriter jw = new JsonWriter(sw);
    nullSafe.write(jw, null);
    jw.flush();
    assertThat(sw.toString()).isEqualTo("null");
  }

  @Test
  public void testWriteNonNullValue() throws IOException {
    TypeAdapter<String> nullSafe = STRING_ADAPTER.nullSafe();
    StringWriter sw = new StringWriter();
    JsonWriter jw = new JsonWriter(sw);
    nullSafe.write(jw, "hello");
    jw.flush();
    assertThat(sw.toString()).isEqualTo("\"hello\"");
  }

  @Test
  public void testReadNullToken() throws IOException {
    TypeAdapter<String> nullSafe = STRING_ADAPTER.nullSafe();
    JsonReader jr = new JsonReader(new StringReader("null"));
    String result = nullSafe.read(jr);
    assertThat(result).isNull();
  }

  @Test
  public void testReadNonNullValue() throws IOException {
    TypeAdapter<String> nullSafe = STRING_ADAPTER.nullSafe();
    JsonReader jr = new JsonReader(new StringReader("\"world\""));
    String result = nullSafe.read(jr);
    assertThat(result).isEqualTo("world");
  }

  @Test
  public void testToString() {
    TypeAdapter<String> nullSafe = STRING_ADAPTER.nullSafe();
    assertThat(nullSafe.toString()).isEqualTo("NullSafeTypeAdapter[StringAdapter]");
  }
}
