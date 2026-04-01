/*
 * Copyright (C) 2011 Google Inc.
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

public final class TypeAdapterTest {

  /** A simple TypeAdapter<String> implementation for testing. */
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
      };

  @Test
  public void testConstructor() {
    assertThat(STRING_ADAPTER).isNotNull();
  }

  @Test
  public void testWriteToJsonWriter() throws IOException {
    StringWriter sw = new StringWriter();
    JsonWriter jw = new JsonWriter(sw);
    STRING_ADAPTER.write(jw, "hello");
    jw.flush();
    assertThat(sw.toString()).isEqualTo("\"hello\"");
  }

  @Test
  public void testToJsonWriter() throws IOException {
    StringWriter sw = new StringWriter();
    STRING_ADAPTER.toJson(sw, "world");
    assertThat(sw.toString()).isEqualTo("\"world\"");
  }

  @Test
  public void testToJsonString() {
    String result = STRING_ADAPTER.toJson("hello");
    assertThat(result).isEqualTo("\"hello\"");
  }

  @Test
  public void testToJsonStringNull() {
    TypeAdapter<String> nullableAdapter =
        new TypeAdapter<String>() {
          @Override
          public void write(JsonWriter out, String value) throws IOException {
            if (value == null) {
              out.nullValue();
            } else {
              out.value(value);
            }
          }

          @Override
          public String read(JsonReader in) throws IOException {
            return in.nextString();
          }
        };
    String result = nullableAdapter.toJson(null);
    assertThat(result).isEqualTo("null");
  }

  @Test
  public void testToJsonTree() {
    JsonElement element = STRING_ADAPTER.toJsonTree("test");
    assertThat(element).isInstanceOf(JsonPrimitive.class);
    assertThat(element.getAsString()).isEqualTo("test");
  }

  @Test
  public void testReadFromJsonReader() throws IOException {
    JsonReader jr = new JsonReader(new StringReader("\"hello\""));
    String result = STRING_ADAPTER.read(jr);
    assertThat(result).isEqualTo("hello");
  }

  @Test
  public void testFromJsonReader() throws IOException {
    String result = STRING_ADAPTER.fromJson(new StringReader("\"world\""));
    assertThat(result).isEqualTo("world");
  }

  @Test
  public void testFromJsonString() throws IOException {
    String result = STRING_ADAPTER.fromJson("\"gson\"");
    assertThat(result).isEqualTo("gson");
  }

  @Test
  public void testFromJsonTree() {
    JsonElement element = new JsonPrimitive("tree");
    String result = STRING_ADAPTER.fromJsonTree(element);
    assertThat(result).isEqualTo("tree");
  }

  @Test
  public void testNullSafeReturnsDifferentAdapter() {
    TypeAdapter<String> nullSafe = STRING_ADAPTER.nullSafe();
    assertThat(nullSafe).isNotSameInstanceAs(STRING_ADAPTER);
  }

  @Test
  public void testNullSafeOnAlreadyNullSafeReturnsSelf() {
    TypeAdapter<String> nullSafe = STRING_ADAPTER.nullSafe();
    TypeAdapter<String> nullSafeAgain = nullSafe.nullSafe();
    assertThat(nullSafeAgain).isSameInstanceAs(nullSafe);
  }

  @Test
  public void testNullSafeWritesNullForNullValue() throws IOException {
    TypeAdapter<String> nullSafe = STRING_ADAPTER.nullSafe();
    StringWriter sw = new StringWriter();
    JsonWriter jw = new JsonWriter(sw);
    nullSafe.write(jw, null);
    jw.flush();
    assertThat(sw.toString()).isEqualTo("null");
  }

  @Test
  public void testNullSafeWritesValueForNonNullValue() throws IOException {
    TypeAdapter<String> nullSafe = STRING_ADAPTER.nullSafe();
    StringWriter sw = new StringWriter();
    JsonWriter jw = new JsonWriter(sw);
    nullSafe.write(jw, "hello");
    jw.flush();
    assertThat(sw.toString()).isEqualTo("\"hello\"");
  }

  @Test
  public void testNullSafeReadsNullFromJsonNull() throws IOException {
    TypeAdapter<String> nullSafe = STRING_ADAPTER.nullSafe();
    JsonReader jr = new JsonReader(new StringReader("null"));
    String result = nullSafe.read(jr);
    assertThat(result).isNull();
  }

  @Test
  public void testNullSafeReadsNonNullValue() throws IOException {
    TypeAdapter<String> nullSafe = STRING_ADAPTER.nullSafe();
    JsonReader jr = new JsonReader(new StringReader("\"hello\""));
    String result = nullSafe.read(jr);
    assertThat(result).isEqualTo("hello");
  }
}
