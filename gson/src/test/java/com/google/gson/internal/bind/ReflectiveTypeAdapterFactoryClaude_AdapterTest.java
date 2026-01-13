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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;

/**
 * Tests for {@link ReflectiveTypeAdapterFactory.Adapter#write} and {@link
 * ReflectiveTypeAdapterFactory.Adapter#read} methods.
 *
 * <p>Focuses on branch and condition coverage for the Adapter inner class.
 */
public class ReflectiveTypeAdapterFactoryClaude_AdapterTest {

  // ==========================================================================
  // Test POJOs
  // ==========================================================================

  static class SimplePojo {
    String name;
    int age;
  }

  static class NestedPojo {
    SimplePojo inner;
    String label;
  }

  static class DeeplyNestedPojo {
    NestedPojo level1;
    String name;
  }

  static class PojoWithMultipleFields {
    String field1;
    String field2;
    String field3;
    int intField;
    boolean boolField;
    double doubleField;
  }

  static class PojoWithNullableFields {
    String stringField;
    Integer integerField;
    SimplePojo objectField;
  }

  // ==========================================================================
  // write() method tests - null value handling (line 485-488)
  // ==========================================================================

  @Test
  public void write_null_outputsNullValue() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<SimplePojo> adapter = gson.getAdapter(SimplePojo.class);

    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    adapter.write(writer, null);

    assertEquals("null", sw.toString());
  }

  @Test
  public void write_nullNestedPojo_outputsNullValue() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<NestedPojo> adapter = gson.getAdapter(NestedPojo.class);

    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    adapter.write(writer, null);

    assertEquals("null", sw.toString());
  }

  // ==========================================================================
  // write() method tests - normal serialization (line 490-498)
  // ==========================================================================

  @Test
  public void write_simplePojo_outputsJsonObject() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<SimplePojo> adapter = gson.getAdapter(SimplePojo.class);

    SimplePojo pojo = new SimplePojo();
    pojo.name = "TestName";
    pojo.age = 25;

    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    adapter.write(writer, pojo);

    String json = sw.toString();
    assertTrue(json.contains("\"name\":\"TestName\""));
    assertTrue(json.contains("\"age\":25"));
  }

  @Test
  public void write_pojoWithMultipleFields_outputsAllFields() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<PojoWithMultipleFields> adapter = gson.getAdapter(PojoWithMultipleFields.class);

    PojoWithMultipleFields pojo = new PojoWithMultipleFields();
    pojo.field1 = "value1";
    pojo.field2 = "value2";
    pojo.field3 = "value3";
    pojo.intField = 42;
    pojo.boolField = true;
    pojo.doubleField = 3.14;

    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    adapter.write(writer, pojo);

    String json = sw.toString();
    assertTrue(json.contains("\"field1\":\"value1\""));
    assertTrue(json.contains("\"field2\":\"value2\""));
    assertTrue(json.contains("\"field3\":\"value3\""));
    assertTrue(json.contains("\"intField\":42"));
    assertTrue(json.contains("\"boolField\":true"));
    assertTrue(json.contains("\"doubleField\":3.14"));
  }

  @Test
  public void write_nestedPojo_outputsNestedStructure() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<NestedPojo> adapter = gson.getAdapter(NestedPojo.class);

    NestedPojo outer = new NestedPojo();
    outer.label = "outer";
    outer.inner = new SimplePojo();
    outer.inner.name = "inner";
    outer.inner.age = 10;

    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    adapter.write(writer, outer);

    String json = sw.toString();
    assertTrue(json.contains("\"label\":\"outer\""));
    assertTrue(json.contains("\"inner\":{"));
    assertTrue(json.contains("\"name\":\"inner\""));
    assertTrue(json.contains("\"age\":10"));
  }

  @Test
  public void write_deeplyNestedPojo_outputsDeeplyNestedStructure() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<DeeplyNestedPojo> adapter = gson.getAdapter(DeeplyNestedPojo.class);

    DeeplyNestedPojo deep = new DeeplyNestedPojo();
    deep.name = "deep";
    deep.level1 = new NestedPojo();
    deep.level1.label = "level1";
    deep.level1.inner = new SimplePojo();
    deep.level1.inner.name = "innermost";
    deep.level1.inner.age = 5;

    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    adapter.write(writer, deep);

    String json = sw.toString();
    assertTrue(json.contains("\"name\":\"deep\""));
    assertTrue(json.contains("\"level1\":{"));
    assertTrue(json.contains("\"label\":\"level1\""));
    assertTrue(json.contains("\"inner\":{"));
    assertTrue(json.contains("\"name\":\"innermost\""));
  }

  @Test
  public void write_pojoWithNullFields_omitsNullsByDefault() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<PojoWithNullableFields> adapter = gson.getAdapter(PojoWithNullableFields.class);

    PojoWithNullableFields pojo = new PojoWithNullableFields();
    pojo.stringField = null;
    pojo.integerField = null;
    pojo.objectField = null;

    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    // Default JsonWriter behavior serializes nulls; set to false to match Gson default
    writer.setSerializeNulls(false);
    adapter.write(writer, pojo);

    String json = sw.toString();
    assertEquals("{}", json);
  }

  @Test
  public void write_pojoWithNullFields_includesNullsWhenSerializeNulls() throws IOException {
    Gson gson = new GsonBuilder().serializeNulls().create();
    TypeAdapter<PojoWithNullableFields> adapter = gson.getAdapter(PojoWithNullableFields.class);

    PojoWithNullableFields pojo = new PojoWithNullableFields();
    pojo.stringField = null;
    pojo.integerField = null;
    pojo.objectField = null;

    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    writer.setSerializeNulls(true);
    adapter.write(writer, pojo);

    String json = sw.toString();
    assertTrue(json.contains("\"stringField\":null"));
    assertTrue(json.contains("\"integerField\":null"));
    assertTrue(json.contains("\"objectField\":null"));
  }

  // ==========================================================================
  // read() method tests - null token handling (line 503-506)
  // ==========================================================================

  @Test
  public void read_nullToken_returnsNull() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<SimplePojo> adapter = gson.getAdapter(SimplePojo.class);

    JsonReader reader = new JsonReader(new StringReader("null"));
    SimplePojo result = adapter.read(reader);

    assertNull(result);
  }

  @Test
  public void read_nullTokenForNestedPojo_returnsNull() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<NestedPojo> adapter = gson.getAdapter(NestedPojo.class);

    JsonReader reader = new JsonReader(new StringReader("null"));
    NestedPojo result = adapter.read(reader);

    assertNull(result);
  }

  // ==========================================================================
  // read() method tests - normal deserialization (line 508-521)
  // ==========================================================================

  @Test
  public void read_simplePojo_readsAllFields() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<SimplePojo> adapter = gson.getAdapter(SimplePojo.class);

    JsonReader reader = new JsonReader(new StringReader("{\"name\":\"Test\",\"age\":30}"));
    SimplePojo result = adapter.read(reader);

    assertNotNull(result);
    assertEquals("Test", result.name);
    assertEquals(30, result.age);
  }

  @Test
  public void read_emptyObject_createsDefaultInstance() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<SimplePojo> adapter = gson.getAdapter(SimplePojo.class);

    JsonReader reader = new JsonReader(new StringReader("{}"));
    SimplePojo result = adapter.read(reader);

    assertNotNull(result);
    assertNull(result.name);
    assertEquals(0, result.age);
  }

  @Test
  public void read_unknownField_skipsValue() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<SimplePojo> adapter = gson.getAdapter(SimplePojo.class);

    JsonReader reader =
        new JsonReader(new StringReader("{\"name\":\"Test\",\"unknown\":\"ignored\",\"age\":25}"));
    SimplePojo result = adapter.read(reader);

    assertNotNull(result);
    assertEquals("Test", result.name);
    assertEquals(25, result.age);
  }

  @Test
  public void read_multipleUnknownFields_skipsAll() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<SimplePojo> adapter = gson.getAdapter(SimplePojo.class);

    JsonReader reader =
        new JsonReader(
            new StringReader(
                "{\"unknown1\":\"a\",\"name\":\"Test\",\"unknown2\":123,\"age\":25,\"unknown3\":{\"nested\":true}}"));
    SimplePojo result = adapter.read(reader);

    assertNotNull(result);
    assertEquals("Test", result.name);
    assertEquals(25, result.age);
  }

  @Test
  public void read_nestedPojo_readsNestedStructure() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<NestedPojo> adapter = gson.getAdapter(NestedPojo.class);

    JsonReader reader =
        new JsonReader(
            new StringReader("{\"label\":\"outer\",\"inner\":{\"name\":\"inner\",\"age\":10}}"));
    NestedPojo result = adapter.read(reader);

    assertNotNull(result);
    assertEquals("outer", result.label);
    assertNotNull(result.inner);
    assertEquals("inner", result.inner.name);
    assertEquals(10, result.inner.age);
  }

  @Test
  public void read_deeplyNestedPojo_readsAllLevels() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<DeeplyNestedPojo> adapter = gson.getAdapter(DeeplyNestedPojo.class);

    JsonReader reader =
        new JsonReader(
            new StringReader(
                "{\"name\":\"deep\",\"level1\":{\"label\":\"l1\",\"inner\":{\"name\":\"innermost\",\"age\":1}}}"));
    DeeplyNestedPojo result = adapter.read(reader);

    assertNotNull(result);
    assertEquals("deep", result.name);
    assertNotNull(result.level1);
    assertEquals("l1", result.level1.label);
    assertNotNull(result.level1.inner);
    assertEquals("innermost", result.level1.inner.name);
    assertEquals(1, result.level1.inner.age);
  }

  @Test
  public void read_nullFieldValue_setsFieldToNull() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<PojoWithNullableFields> adapter = gson.getAdapter(PojoWithNullableFields.class);

    JsonReader reader =
        new JsonReader(
            new StringReader("{\"stringField\":null,\"integerField\":null,\"objectField\":null}"));
    PojoWithNullableFields result = adapter.read(reader);

    assertNotNull(result);
    assertNull(result.stringField);
    assertNull(result.integerField);
    assertNull(result.objectField);
  }

  @Test
  public void read_partialFields_setsOnlyProvidedFields() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<PojoWithMultipleFields> adapter = gson.getAdapter(PojoWithMultipleFields.class);

    JsonReader reader =
        new JsonReader(new StringReader("{\"field1\":\"value1\",\"intField\":100}"));
    PojoWithMultipleFields result = adapter.read(reader);

    assertNotNull(result);
    assertEquals("value1", result.field1);
    assertNull(result.field2);
    assertNull(result.field3);
    assertEquals(100, result.intField);
    assertTrue(!result.boolField);
    assertEquals(0.0, result.doubleField, 0.001);
  }

  // ==========================================================================
  // read() method tests - IllegalStateException handling (line 522-523)
  // ==========================================================================

  @Test
  public void read_invalidJsonExpectingObject_throwsJsonSyntaxException() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<SimplePojo> adapter = gson.getAdapter(SimplePojo.class);

    // Array instead of object
    JsonReader reader = new JsonReader(new StringReader("[1, 2, 3]"));
    try {
      adapter.read(reader);
      fail("Expected JsonSyntaxException for invalid JSON");
    } catch (JsonSyntaxException e) {
      assertTrue(e.getMessage().contains("Expected BEGIN_OBJECT") || e.getCause() != null);
    }
  }

  @Test
  public void read_stringInsteadOfObject_throwsJsonSyntaxException() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<SimplePojo> adapter = gson.getAdapter(SimplePojo.class);

    JsonReader reader = new JsonReader(new StringReader("\"not an object\""));
    try {
      adapter.read(reader);
      fail("Expected JsonSyntaxException for string instead of object");
    } catch (JsonSyntaxException e) {
      assertNotNull(e);
    }
  }

  @Test
  public void read_numberInsteadOfObject_throwsJsonSyntaxException() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<SimplePojo> adapter = gson.getAdapter(SimplePojo.class);

    JsonReader reader = new JsonReader(new StringReader("12345"));
    try {
      adapter.read(reader);
      fail("Expected JsonSyntaxException for number instead of object");
    } catch (JsonSyntaxException e) {
      assertNotNull(e);
    }
  }

  @Test
  public void read_booleanInsteadOfObject_throwsJsonSyntaxException() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<SimplePojo> adapter = gson.getAdapter(SimplePojo.class);

    JsonReader reader = new JsonReader(new StringReader("true"));
    try {
      adapter.read(reader);
      fail("Expected JsonSyntaxException for boolean instead of object");
    } catch (JsonSyntaxException e) {
      assertNotNull(e);
    }
  }

  // ==========================================================================
  // Round-trip tests (write then read)
  // ==========================================================================

  @Test
  public void roundTrip_simplePojo_preservesValues() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<SimplePojo> adapter = gson.getAdapter(SimplePojo.class);

    SimplePojo original = new SimplePojo();
    original.name = "RoundTrip";
    original.age = 99;

    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    adapter.write(writer, original);

    JsonReader reader = new JsonReader(new StringReader(sw.toString()));
    SimplePojo restored = adapter.read(reader);

    assertEquals(original.name, restored.name);
    assertEquals(original.age, restored.age);
  }

  @Test
  public void roundTrip_nestedPojo_preservesStructure() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<NestedPojo> adapter = gson.getAdapter(NestedPojo.class);

    NestedPojo original = new NestedPojo();
    original.label = "outer";
    original.inner = new SimplePojo();
    original.inner.name = "inner";
    original.inner.age = 50;

    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    adapter.write(writer, original);

    JsonReader reader = new JsonReader(new StringReader(sw.toString()));
    NestedPojo restored = adapter.read(reader);

    assertEquals(original.label, restored.label);
    assertNotNull(restored.inner);
    assertEquals(original.inner.name, restored.inner.name);
    assertEquals(original.inner.age, restored.inner.age);
  }

  @Test
  public void roundTrip_pojoWithNullFields_preservesNulls() throws IOException {
    Gson gson = new GsonBuilder().serializeNulls().create();
    TypeAdapter<PojoWithNullableFields> adapter = gson.getAdapter(PojoWithNullableFields.class);

    PojoWithNullableFields original = new PojoWithNullableFields();
    original.stringField = "value";
    original.integerField = null;
    original.objectField = null;

    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    writer.setSerializeNulls(true);
    adapter.write(writer, original);

    JsonReader reader = new JsonReader(new StringReader(sw.toString()));
    PojoWithNullableFields restored = adapter.read(reader);

    assertEquals(original.stringField, restored.stringField);
    assertNull(restored.integerField);
    assertNull(restored.objectField);
  }

  // ==========================================================================
  // Edge cases for field iteration (lines 492-494)
  // ==========================================================================

  static class EmptyPojo {}

  @Test
  public void write_emptyPojo_outputsEmptyObject() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<EmptyPojo> adapter = gson.getAdapter(EmptyPojo.class);

    EmptyPojo pojo = new EmptyPojo();

    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    adapter.write(writer, pojo);

    assertEquals("{}", sw.toString());
  }

  @Test
  public void read_emptyPojoEmptyJson_createsInstance() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<EmptyPojo> adapter = gson.getAdapter(EmptyPojo.class);

    JsonReader reader = new JsonReader(new StringReader("{}"));
    EmptyPojo result = adapter.read(reader);

    assertNotNull(result);
  }

  // ==========================================================================
  // Field order tests
  // ==========================================================================

  @Test
  public void read_fieldsInDifferentOrder_readsCorrectly() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<SimplePojo> adapter = gson.getAdapter(SimplePojo.class);

    // Fields in reverse order compared to class definition
    JsonReader reader = new JsonReader(new StringReader("{\"age\":42,\"name\":\"Reversed\"}"));
    SimplePojo result = adapter.read(reader);

    assertEquals("Reversed", result.name);
    assertEquals(42, result.age);
  }

  // ==========================================================================
  // Special character handling
  // ==========================================================================

  @Test
  public void roundTrip_specialCharacters_preservesValues() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<SimplePojo> adapter = gson.getAdapter(SimplePojo.class);

    SimplePojo original = new SimplePojo();
    original.name = "Special\"\\\n\t\r chars";
    original.age = 0;

    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    adapter.write(writer, original);

    JsonReader reader = new JsonReader(new StringReader(sw.toString()));
    SimplePojo restored = adapter.read(reader);

    assertEquals(original.name, restored.name);
  }

  @Test
  public void roundTrip_unicodeCharacters_preservesValues() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<SimplePojo> adapter = gson.getAdapter(SimplePojo.class);

    SimplePojo original = new SimplePojo();
    original.name = "\u4e2d\u6587\u65e5\u672c\u8a9e\ud83d\ude00";
    original.age = 123;

    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    adapter.write(writer, original);

    JsonReader reader = new JsonReader(new StringReader(sw.toString()));
    SimplePojo restored = adapter.read(reader);

    assertEquals(original.name, restored.name);
  }

  // ==========================================================================
  // Nested null handling
  // ==========================================================================

  @Test
  public void read_nullNestedObject_setsFieldToNull() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<NestedPojo> adapter = gson.getAdapter(NestedPojo.class);

    JsonReader reader = new JsonReader(new StringReader("{\"label\":\"outer\",\"inner\":null}"));
    NestedPojo result = adapter.read(reader);

    assertNotNull(result);
    assertEquals("outer", result.label);
    assertNull(result.inner);
  }

  @Test
  public void write_nestedPojoWithNullInner_omitsNullByDefault() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<NestedPojo> adapter = gson.getAdapter(NestedPojo.class);

    NestedPojo pojo = new NestedPojo();
    pojo.label = "outer";
    pojo.inner = null;

    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    // Default JsonWriter behavior serializes nulls; set to false to match Gson default
    writer.setSerializeNulls(false);
    adapter.write(writer, pojo);

    String json = sw.toString();
    assertTrue(json.contains("\"label\":\"outer\""));
    assertTrue(!json.contains("\"inner\""));
  }

  // ==========================================================================
  // Complex unknown field types to skip
  // ==========================================================================

  @Test
  public void read_unknownNestedObjectField_skipsEntireObject() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<SimplePojo> adapter = gson.getAdapter(SimplePojo.class);

    JsonReader reader =
        new JsonReader(
            new StringReader(
                "{\"name\":\"Test\",\"unknown\":{\"nested\":{\"deep\":true}},\"age\":25}"));
    SimplePojo result = adapter.read(reader);

    assertEquals("Test", result.name);
    assertEquals(25, result.age);
  }

  @Test
  public void read_unknownArrayField_skipsEntireArray() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<SimplePojo> adapter = gson.getAdapter(SimplePojo.class);

    JsonReader reader =
        new JsonReader(
            new StringReader("{\"name\":\"Test\",\"unknown\":[1,2,3,{\"a\":1}],\"age\":25}"));
    SimplePojo result = adapter.read(reader);

    assertEquals("Test", result.name);
    assertEquals(25, result.age);
  }
}
