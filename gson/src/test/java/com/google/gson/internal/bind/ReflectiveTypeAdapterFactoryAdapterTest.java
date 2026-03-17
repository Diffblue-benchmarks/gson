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
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;

public class ReflectiveTypeAdapterFactoryAdapterTest {

  static class SimpleClass {
    String name;
    int value;

    SimpleClass() {}

    SimpleClass(String name, int value) {
      this.name = name;
      this.value = value;
    }
  }

  static class ClassWithMultipleFields {
    String field1;
    int field2;
    boolean field3;
    double field4;

    ClassWithMultipleFields() {}

    ClassWithMultipleFields(String field1, int field2, boolean field3, double field4) {
      this.field1 = field1;
      this.field2 = field2;
      this.field3 = field3;
      this.field4 = field4;
    }
  }

  @Test
  public void testWriteNullValue() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<SimpleClass> adapter = gson.getAdapter(SimpleClass.class);

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, null);

    assertThat(stringWriter.toString()).isEqualTo("null");
  }

  @Test
  public void testWriteObject() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<SimpleClass> adapter = gson.getAdapter(SimpleClass.class);

    SimpleClass obj = new SimpleClass("test", 42);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, obj);

    assertThat(stringWriter.toString()).isEqualTo("{\"name\":\"test\",\"value\":42}");
  }

  @Test
  public void testWriteObjectWithMultipleFields() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<ClassWithMultipleFields> adapter = gson.getAdapter(ClassWithMultipleFields.class);

    ClassWithMultipleFields obj = new ClassWithMultipleFields("value1", 100, true, 3.14);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, obj);

    String json = stringWriter.toString();
    assertThat(json).contains("\"field1\":\"value1\"");
    assertThat(json).contains("\"field2\":100");
    assertThat(json).contains("\"field3\":true");
    assertThat(json).contains("\"field4\":3.14");
  }

  @Test
  public void testReadNullValue() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<SimpleClass> adapter = gson.getAdapter(SimpleClass.class);

    JsonReader reader = new JsonReader(new StringReader("null"));
    SimpleClass result = adapter.read(reader);

    assertThat(result).isNull();
  }

  @Test
  public void testReadObject() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<SimpleClass> adapter = gson.getAdapter(SimpleClass.class);

    JsonReader reader = new JsonReader(new StringReader("{\"name\":\"test\",\"value\":42}"));
    SimpleClass result = adapter.read(reader);

    assertThat(result).isNotNull();
    assertThat(result.name).isEqualTo("test");
    assertThat(result.value).isEqualTo(42);
  }

  @Test
  public void testReadObjectWithUnknownFields() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<SimpleClass> adapter = gson.getAdapter(SimpleClass.class);

    JsonReader reader = new JsonReader(new StringReader("{\"name\":\"test\",\"value\":42,\"unknown\":\"ignored\"}"));
    SimpleClass result = adapter.read(reader);

    assertThat(result).isNotNull();
    assertThat(result.name).isEqualTo("test");
    assertThat(result.value).isEqualTo(42);
  }

  @Test
  public void testReadObjectWithMultipleFields() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<ClassWithMultipleFields> adapter = gson.getAdapter(ClassWithMultipleFields.class);

    JsonReader reader = new JsonReader(new StringReader("{\"field1\":\"value1\",\"field2\":100,\"field3\":true,\"field4\":3.14}"));
    ClassWithMultipleFields result = adapter.read(reader);

    assertThat(result).isNotNull();
    assertThat(result.field1).isEqualTo("value1");
    assertThat(result.field2).isEqualTo(100);
    assertThat(result.field3).isTrue();
    assertThat(result.field4).isEqualTo(3.14);
  }

  @Test
  public void testReadObjectWithPartialFields() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<ClassWithMultipleFields> adapter = gson.getAdapter(ClassWithMultipleFields.class);

    JsonReader reader = new JsonReader(new StringReader("{\"field1\":\"partial\",\"field3\":false}"));
    ClassWithMultipleFields result = adapter.read(reader);

    assertThat(result).isNotNull();
    assertThat(result.field1).isEqualTo("partial");
    assertThat(result.field2).isEqualTo(0);
    assertThat(result.field3).isFalse();
    assertThat(result.field4).isEqualTo(0.0);
  }

  @Test
  public void testReadInvalidJson() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<SimpleClass> adapter = gson.getAdapter(SimpleClass.class);

    JsonReader reader = new JsonReader(new StringReader("{\"name\":\"test\",\"value\":42"));
    try {
      adapter.read(reader);
      throw new AssertionError("Expected IOException");
    } catch (IOException expected) {
      // Expected exception for malformed JSON
    }
  }

  @Test
  public void testReadWriteRoundTrip() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<SimpleClass> adapter = gson.getAdapter(SimpleClass.class);

    SimpleClass original = new SimpleClass("roundtrip", 123);

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, original);

    String json = stringWriter.toString();
    JsonReader reader = new JsonReader(new StringReader(json));
    SimpleClass result = adapter.read(reader);

    assertThat(result).isNotNull();
    assertThat(result.name).isEqualTo(original.name);
    assertThat(result.value).isEqualTo(original.value);
  }

  @Test
  public void testReadEmptyObject() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<SimpleClass> adapter = gson.getAdapter(SimpleClass.class);

    JsonReader reader = new JsonReader(new StringReader("{}"));
    SimpleClass result = adapter.read(reader);

    assertThat(result).isNotNull();
    assertThat(result.name).isNull();
    assertThat(result.value).isEqualTo(0);
  }

  @Test
  public void testWriteEmptyObject() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<SimpleClass> adapter = gson.getAdapter(SimpleClass.class);

    SimpleClass obj = new SimpleClass();
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, obj);

    assertThat(stringWriter.toString()).isEqualTo("{\"name\":null,\"value\":0}");
  }
}
