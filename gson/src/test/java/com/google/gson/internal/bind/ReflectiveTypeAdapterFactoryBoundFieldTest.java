/*
 * Copyright (C) 2011 Google Inc.
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

import com.google.gson.JsonParseException;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import org.junit.Test;

public class ReflectiveTypeAdapterFactoryBoundFieldTest {

  private static class TestClass {
    public String testField;
  }

  private static class TestBoundField extends ReflectiveTypeAdapterFactory.BoundField {
    protected TestBoundField(String serializedName, Field field) {
      super(serializedName, field);
    }

    @Override
    void write(JsonWriter writer, Object source) throws IOException, IllegalAccessException {
      // Simple implementation for testing
      writer.name(serializedName);
      Object value = field.get(source);
      if (value == null) {
        writer.nullValue();
      } else {
        writer.value(value.toString());
      }
    }

    @Override
    void readIntoArray(JsonReader reader, int index, Object[] target)
        throws IOException, JsonParseException {
      // Simple implementation for testing
      target[index] = reader.nextString();
    }

    @Override
    void readIntoField(JsonReader reader, Object target)
        throws IOException, IllegalAccessException {
      // Simple implementation for testing
      field.set(target, reader.nextString());
    }
  }

  @Test
  public void testConstructor() throws NoSuchFieldException {
    Field field = TestClass.class.getField("testField");
    String serializedName = "test_field";

    TestBoundField boundField = new TestBoundField(serializedName, field);

    assertThat(boundField.serializedName).isEqualTo(serializedName);
    assertThat(boundField.field).isEqualTo(field);
    assertThat(boundField.fieldName).isEqualTo("testField");
  }

  @Test
  public void testConstructorWithDifferentFieldName() throws NoSuchFieldException {
    Field field = TestClass.class.getField("testField");
    String serializedName = "different_name";

    TestBoundField boundField = new TestBoundField(serializedName, field);

    assertThat(boundField.serializedName).isEqualTo(serializedName);
    assertThat(boundField.field).isEqualTo(field);
    assertThat(boundField.fieldName).isEqualTo("testField");
  }

  @Test
  public void testWriteMethod() throws Exception {
    Field field = TestClass.class.getField("testField");
    TestBoundField boundField = new TestBoundField("test_field", field);
    java.io.StringWriter stringWriter = new java.io.StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    TestClass source = new TestClass();
    source.testField = "testValue";

    writer.beginObject();
    boundField.write(writer, source);
    writer.endObject();
    writer.close();

    String json = stringWriter.toString();
    assertThat(json).contains("test_field");
    assertThat(json).contains("testValue");
  }

  @Test
  public void testReadIntoArrayMethod() throws Exception {
    Field field = TestClass.class.getField("testField");
    TestBoundField boundField = new TestBoundField("test_field", field);
    JsonReader reader = new JsonReader(new java.io.StringReader("\"arrayValue\""));
    Object[] target = new Object[1];

    boundField.readIntoArray(reader, 0, target);

    assertThat(target[0]).isEqualTo("arrayValue");
  }

  @Test
  public void testReadIntoFieldMethod() throws Exception {
    Field field = TestClass.class.getField("testField");
    TestBoundField boundField = new TestBoundField("test_field", field);
    JsonReader reader = new JsonReader(new java.io.StringReader("\"fieldValue\""));
    TestClass target = new TestClass();

    boundField.readIntoField(reader, target);

    assertThat(target.testField).isEqualTo("fieldValue");
  }
}
