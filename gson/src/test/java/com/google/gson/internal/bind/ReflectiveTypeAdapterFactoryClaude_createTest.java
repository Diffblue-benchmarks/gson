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
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.google.gson.ReflectionAccessFilter;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;

/**
 * Tests for {@link ReflectiveTypeAdapterFactory#create} method, specifically covering the Record
 * adapter path (lines 156, 159, 160).
 */
public class ReflectiveTypeAdapterFactoryClaude_createTest {

  // ==========================================================================
  // Record deserialization tests - cover lines 156, 159, 160
  // ==========================================================================

  /** Simple record with a single field */
  public record SimpleRecord(String name) {}

  @Test
  public void create_withSimpleRecord_returnsRecordAdapter() {
    Gson gson = new Gson();
    TypeAdapter<SimpleRecord> adapter = gson.getAdapter(SimpleRecord.class);
    assertNotNull(adapter);
  }

  @Test
  public void create_withSimpleRecord_deserializes() {
    Gson gson = new Gson();
    SimpleRecord result = gson.fromJson("{\"name\":\"test\"}", SimpleRecord.class);
    assertNotNull(result);
    assertEquals("test", result.name());
  }

  @Test
  public void create_withSimpleRecord_serializes() {
    Gson gson = new Gson();
    SimpleRecord record = new SimpleRecord("hello");
    String json = gson.toJson(record);
    assertTrue(json.contains("\"name\":\"hello\""));
  }

  /** Record with multiple fields */
  public record MultiFieldRecord(String name, int age, boolean active) {}

  @Test
  public void create_withMultiFieldRecord_deserializes() {
    Gson gson = new Gson();
    MultiFieldRecord result =
        gson.fromJson("{\"name\":\"Alice\",\"age\":30,\"active\":true}", MultiFieldRecord.class);
    assertNotNull(result);
    assertEquals("Alice", result.name());
    assertEquals(30, result.age());
    assertTrue(result.active());
  }

  @Test
  public void create_withMultiFieldRecord_serializes() {
    Gson gson = new Gson();
    MultiFieldRecord record = new MultiFieldRecord("Bob", 25, false);
    String json = gson.toJson(record);
    assertTrue(json.contains("\"name\":\"Bob\""));
    assertTrue(json.contains("\"age\":25"));
    assertTrue(json.contains("\"active\":false"));
  }

  /** Record with primitive fields */
  public record PrimitiveFieldRecord(int intVal, long longVal, double doubleVal, boolean boolVal) {}

  @Test
  public void create_withPrimitiveFieldRecord_deserializes() {
    Gson gson = new Gson();
    PrimitiveFieldRecord result =
        gson.fromJson(
            "{\"intVal\":42,\"longVal\":999999999999,\"doubleVal\":3.14,\"boolVal\":true}",
            PrimitiveFieldRecord.class);
    assertNotNull(result);
    assertEquals(42, result.intVal());
    assertEquals(999999999999L, result.longVal());
    assertEquals(3.14, result.doubleVal(), 0.001);
    assertTrue(result.boolVal());
  }

  @Test
  public void create_withPrimitiveFieldRecord_nullForPrimitive_throwsException() {
    Gson gson = new Gson();
    try {
      gson.fromJson("{\"intVal\":null,\"longVal\":1,\"doubleVal\":1.0,\"boolVal\":true}",
          PrimitiveFieldRecord.class);
      fail("Expected JsonParseException");
    } catch (JsonParseException e) {
      assertTrue(e.getMessage().contains("null is not allowed"));
      assertTrue(e.getMessage().contains("primitive type"));
    }
  }

  /** Record with nested record */
  public record InnerRecord(String value) {}

  public record OuterRecord(String name, InnerRecord inner) {}

  @Test
  public void create_withNestedRecord_deserializes() {
    Gson gson = new Gson();
    OuterRecord result =
        gson.fromJson("{\"name\":\"outer\",\"inner\":{\"value\":\"inner\"}}", OuterRecord.class);
    assertNotNull(result);
    assertEquals("outer", result.name());
    assertNotNull(result.inner());
    assertEquals("inner", result.inner().value());
  }

  @Test
  public void create_withNestedRecord_serializes() {
    Gson gson = new Gson();
    OuterRecord record = new OuterRecord("outer", new InnerRecord("inner"));
    String json = gson.toJson(record);
    assertTrue(json.contains("\"name\":\"outer\""));
    assertTrue(json.contains("\"inner\":{\"value\":\"inner\"}"));
  }

  /** Record with @SerializedName annotation */
  public record SerializedNameRecord(@SerializedName("custom_name") String name) {}

  @Test
  public void create_withSerializedNameRecord_deserializes() {
    Gson gson = new Gson();
    SerializedNameRecord result =
        gson.fromJson("{\"custom_name\":\"test\"}", SerializedNameRecord.class);
    assertNotNull(result);
    assertEquals("test", result.name());
  }

  @Test
  public void create_withSerializedNameRecord_serializes() {
    Gson gson = new Gson();
    SerializedNameRecord record = new SerializedNameRecord("test");
    String json = gson.toJson(record);
    assertTrue(json.contains("\"custom_name\":\"test\""));
  }

  /** Record with null value */
  @Test
  public void create_withRecord_nullJson_returnsNull() {
    Gson gson = new Gson();
    SimpleRecord result = gson.fromJson("null", SimpleRecord.class);
    assertNull(result);
  }

  /** Record with missing fields */
  @Test
  public void create_withRecord_missingFields_usesDefaults() {
    Gson gson = new Gson();
    // Only provide name, not age or active
    MultiFieldRecord result = gson.fromJson("{\"name\":\"test\"}", MultiFieldRecord.class);
    assertNotNull(result);
    assertEquals("test", result.name());
    assertEquals(0, result.age());
    assertTrue(!result.active());
  }

  /** Record with extra fields in JSON (should be ignored) */
  @Test
  public void create_withRecord_extraFields_ignoresExtras() {
    Gson gson = new Gson();
    SimpleRecord result =
        gson.fromJson("{\"name\":\"test\",\"extra\":\"ignored\"}", SimpleRecord.class);
    assertNotNull(result);
    assertEquals("test", result.name());
  }

  /** Record with null field value (for reference type) */
  @Test
  public void create_withRecord_nullReferenceField_acceptsNull() {
    Gson gson = new Gson();
    SimpleRecord result = gson.fromJson("{\"name\":null}", SimpleRecord.class);
    assertNotNull(result);
    assertNull(result.name());
  }

  /** Record round-trip test */
  @Test
  public void create_withRecord_roundTrip() {
    Gson gson = new Gson();
    MultiFieldRecord original = new MultiFieldRecord("RoundTrip", 42, true);
    String json = gson.toJson(original);
    MultiFieldRecord restored = gson.fromJson(json, MultiFieldRecord.class);

    assertEquals(original.name(), restored.name());
    assertEquals(original.age(), restored.age());
    assertEquals(original.active(), restored.active());
  }

  /** Record with empty JSON object */
  @Test
  public void create_withRecord_emptyObject_createsWithDefaults() {
    Gson gson = new Gson();
    MultiFieldRecord result = gson.fromJson("{}", MultiFieldRecord.class);
    assertNotNull(result);
    assertNull(result.name());
    assertEquals(0, result.age());
    assertTrue(!result.active());
  }

  /** Private record - tests accessibility */
  private record PrivateRecord(String value) {}

  @Test
  public void create_withPrivateRecord_deserializes() {
    Gson gson = new Gson();
    PrivateRecord result = gson.fromJson("{\"value\":\"private\"}", PrivateRecord.class);
    assertNotNull(result);
    assertEquals("private", result.value());
  }

  @Test
  public void create_withPrivateRecord_serializes() {
    Gson gson = new Gson();
    PrivateRecord record = new PrivateRecord("private");
    String json = gson.toJson(record);
    assertTrue(json.contains("\"value\":\"private\""));
  }

  /** Record with BLOCK_ALL filter throws exception */
  @Test
  public void create_withRecord_blockAllFilter_throwsException() {
    Gson gson =
        new GsonBuilder()
            .addReflectionAccessFilter(rawClass -> ReflectionAccessFilter.FilterResult.BLOCK_ALL)
            .create();

    try {
      gson.fromJson("{\"name\":\"test\"}", SimpleRecord.class);
      fail("Expected JsonIOException");
    } catch (JsonIOException e) {
      assertTrue(e.getMessage().contains("ReflectionAccessFilter does not permit"));
    }
  }

  /** Record with direct TypeAdapter usage */
  @Test
  public void create_withRecord_directAdapterRead() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<SimpleRecord> adapter = gson.getAdapter(SimpleRecord.class);

    SimpleRecord result =
        adapter.read(new com.google.gson.stream.JsonReader(new StringReader("{\"name\":\"direct\"}")));
    assertNotNull(result);
    assertEquals("direct", result.name());
  }

  @Test
  public void create_withRecord_directAdapterWrite() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<SimpleRecord> adapter = gson.getAdapter(SimpleRecord.class);

    StringWriter stringWriter = new StringWriter();
    com.google.gson.stream.JsonWriter jsonWriter =
        new com.google.gson.stream.JsonWriter(stringWriter);
    adapter.write(jsonWriter, new SimpleRecord("direct"));

    assertTrue(stringWriter.toString().contains("\"name\":\"direct\""));
  }

  /** Record adapter write null value */
  @Test
  public void create_withRecord_writeNull() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<SimpleRecord> adapter = gson.getAdapter(SimpleRecord.class);

    StringWriter stringWriter = new StringWriter();
    com.google.gson.stream.JsonWriter jsonWriter =
        new com.google.gson.stream.JsonWriter(stringWriter);
    adapter.write(jsonWriter, null);

    assertEquals("null", stringWriter.toString());
  }

  /** Record with all primitive types */
  public record AllPrimitivesRecord(
      byte byteVal,
      short shortVal,
      int intVal,
      long longVal,
      float floatVal,
      double doubleVal,
      char charVal,
      boolean boolVal) {}

  @Test
  public void create_withAllPrimitivesRecord_deserializesWithDefaults() {
    Gson gson = new Gson();
    // Missing fields should get primitive defaults
    AllPrimitivesRecord result = gson.fromJson("{}", AllPrimitivesRecord.class);
    assertNotNull(result);
    assertEquals((byte) 0, result.byteVal());
    assertEquals((short) 0, result.shortVal());
    assertEquals(0, result.intVal());
    assertEquals(0L, result.longVal());
    assertEquals(0.0f, result.floatVal(), 0.001f);
    assertEquals(0.0, result.doubleVal(), 0.001);
    assertEquals('\0', result.charVal());
    assertTrue(!result.boolVal());
  }

  @Test
  public void create_withAllPrimitivesRecord_deserializesWithValues() {
    Gson gson = new Gson();
    String json =
        "{\"byteVal\":1,\"shortVal\":2,\"intVal\":3,\"longVal\":4,"
            + "\"floatVal\":5.5,\"doubleVal\":6.6,\"charVal\":\"A\",\"boolVal\":true}";
    AllPrimitivesRecord result = gson.fromJson(json, AllPrimitivesRecord.class);
    assertNotNull(result);
    assertEquals((byte) 1, result.byteVal());
    assertEquals((short) 2, result.shortVal());
    assertEquals(3, result.intVal());
    assertEquals(4L, result.longVal());
    assertEquals(5.5f, result.floatVal(), 0.001f);
    assertEquals(6.6, result.doubleVal(), 0.001);
    assertEquals('A', result.charVal());
    assertTrue(result.boolVal());
  }

  /** Record with serializeNulls configuration */
  @Test
  public void create_withRecord_serializeNulls() {
    Gson gson = new GsonBuilder().serializeNulls().create();
    SimpleRecord record = new SimpleRecord(null);
    String json = gson.toJson(record);
    assertTrue(json.contains("\"name\":null"));
  }

  /** Record fields out of order in JSON */
  @Test
  public void create_withRecord_fieldsOutOfOrder() {
    Gson gson = new Gson();
    MultiFieldRecord result =
        gson.fromJson("{\"active\":true,\"name\":\"test\",\"age\":42}", MultiFieldRecord.class);
    assertNotNull(result);
    assertEquals("test", result.name());
    assertEquals(42, result.age());
    assertTrue(result.active());
  }

  /** Record serialization with pretty printing */
  @Test
  public void create_withRecord_prettyPrinting() {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    SimpleRecord record = new SimpleRecord("pretty");
    String json = gson.toJson(record);
    assertTrue(json.contains("\"name\":"));
    assertTrue(json.contains("\"pretty\""));
  }
}
