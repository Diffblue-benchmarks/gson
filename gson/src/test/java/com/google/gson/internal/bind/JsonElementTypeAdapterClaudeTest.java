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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;

/** Tests for {@link JsonElementTypeAdapter}. */
public class JsonElementTypeAdapterClaudeTest {

  private final Gson gson = new Gson();
  private final TypeAdapter<JsonElement> adapter = gson.getAdapter(JsonElement.class);

  // ==========================================================================
  // read() tests - null
  // ==========================================================================

  @Test
  public void read_nullValue_returnsJsonNull() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("null"));
    JsonElement result = adapter.read(reader);
    assertTrue(result.isJsonNull());
  }

  // ==========================================================================
  // read() tests - primitive string
  // ==========================================================================

  @Test
  public void read_stringValue_returnsJsonPrimitive() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("\"hello\""));
    JsonElement result = adapter.read(reader);
    assertTrue(result.isJsonPrimitive());
    assertEquals("hello", result.getAsString());
  }

  @Test
  public void read_emptyString_returnsJsonPrimitive() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("\"\""));
    JsonElement result = adapter.read(reader);
    assertTrue(result.isJsonPrimitive());
    assertEquals("", result.getAsString());
  }

  @Test
  public void read_stringWithSpecialChars_returnsCorrectValue() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("\"hello\\nworld\\t!\""));
    JsonElement result = adapter.read(reader);
    assertTrue(result.isJsonPrimitive());
    assertEquals("hello\nworld\t!", result.getAsString());
  }

  // ==========================================================================
  // read() tests - primitive numbers
  // ==========================================================================

  @Test
  public void read_integerValue_returnsJsonPrimitive() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("42"));
    JsonElement result = adapter.read(reader);
    assertTrue(result.isJsonPrimitive());
    assertEquals(42, result.getAsInt());
  }

  @Test
  public void read_negativeInteger_returnsJsonPrimitive() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("-123"));
    JsonElement result = adapter.read(reader);
    assertTrue(result.isJsonPrimitive());
    assertEquals(-123, result.getAsInt());
  }

  @Test
  public void read_doubleValue_returnsJsonPrimitive() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("3.14159"));
    JsonElement result = adapter.read(reader);
    assertTrue(result.isJsonPrimitive());
    assertEquals(3.14159, result.getAsDouble(), 0.00001);
  }

  @Test
  public void read_negativeDouble_returnsJsonPrimitive() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("-99.5"));
    JsonElement result = adapter.read(reader);
    assertTrue(result.isJsonPrimitive());
    assertEquals(-99.5, result.getAsDouble(), 0.001);
  }

  @Test
  public void read_scientificNotation_returnsJsonPrimitive() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("1.5e10"));
    JsonElement result = adapter.read(reader);
    assertTrue(result.isJsonPrimitive());
    assertEquals(1.5e10, result.getAsDouble(), 0.001);
  }

  @Test
  public void read_longValue_returnsJsonPrimitive() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("9999999999999"));
    JsonElement result = adapter.read(reader);
    assertTrue(result.isJsonPrimitive());
    assertEquals(9999999999999L, result.getAsLong());
  }

  // ==========================================================================
  // read() tests - primitive boolean
  // ==========================================================================

  @Test
  public void read_trueValue_returnsJsonPrimitive() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("true"));
    JsonElement result = adapter.read(reader);
    assertTrue(result.isJsonPrimitive());
    assertTrue(result.getAsBoolean());
  }

  @Test
  public void read_falseValue_returnsJsonPrimitive() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("false"));
    JsonElement result = adapter.read(reader);
    assertTrue(result.isJsonPrimitive());
    assertFalse(result.getAsBoolean());
  }

  // ==========================================================================
  // read() tests - empty array
  // ==========================================================================

  @Test
  public void read_emptyArray_returnsEmptyJsonArray() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[]"));
    JsonElement result = adapter.read(reader);
    assertTrue(result.isJsonArray());
    assertEquals(0, result.getAsJsonArray().size());
  }

  // ==========================================================================
  // read() tests - array with primitives
  // ==========================================================================

  @Test
  public void read_arrayWithStrings_returnsJsonArray() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[\"a\", \"b\", \"c\"]"));
    JsonElement result = adapter.read(reader);
    assertTrue(result.isJsonArray());
    JsonArray array = result.getAsJsonArray();
    assertEquals(3, array.size());
    assertEquals("a", array.get(0).getAsString());
    assertEquals("b", array.get(1).getAsString());
    assertEquals("c", array.get(2).getAsString());
  }

  @Test
  public void read_arrayWithNumbers_returnsJsonArray() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[1, 2, 3]"));
    JsonElement result = adapter.read(reader);
    assertTrue(result.isJsonArray());
    JsonArray array = result.getAsJsonArray();
    assertEquals(3, array.size());
    assertEquals(1, array.get(0).getAsInt());
    assertEquals(2, array.get(1).getAsInt());
    assertEquals(3, array.get(2).getAsInt());
  }

  @Test
  public void read_arrayWithBooleans_returnsJsonArray() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[true, false, true]"));
    JsonElement result = adapter.read(reader);
    assertTrue(result.isJsonArray());
    JsonArray array = result.getAsJsonArray();
    assertEquals(3, array.size());
    assertTrue(array.get(0).getAsBoolean());
    assertFalse(array.get(1).getAsBoolean());
    assertTrue(array.get(2).getAsBoolean());
  }

  @Test
  public void read_arrayWithNull_returnsJsonArray() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[null, null]"));
    JsonElement result = adapter.read(reader);
    assertTrue(result.isJsonArray());
    JsonArray array = result.getAsJsonArray();
    assertEquals(2, array.size());
    assertTrue(array.get(0).isJsonNull());
    assertTrue(array.get(1).isJsonNull());
  }

  @Test
  public void read_arrayWithMixedTypes_returnsJsonArray() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[\"string\", 42, true, null]"));
    JsonElement result = adapter.read(reader);
    assertTrue(result.isJsonArray());
    JsonArray array = result.getAsJsonArray();
    assertEquals(4, array.size());
    assertEquals("string", array.get(0).getAsString());
    assertEquals(42, array.get(1).getAsInt());
    assertTrue(array.get(2).getAsBoolean());
    assertTrue(array.get(3).isJsonNull());
  }

  // ==========================================================================
  // read() tests - nested arrays
  // ==========================================================================

  @Test
  public void read_nestedArrays_returnsJsonArray() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[[1, 2], [3, 4]]"));
    JsonElement result = adapter.read(reader);
    assertTrue(result.isJsonArray());
    JsonArray outer = result.getAsJsonArray();
    assertEquals(2, outer.size());

    JsonArray inner1 = outer.get(0).getAsJsonArray();
    assertEquals(2, inner1.size());
    assertEquals(1, inner1.get(0).getAsInt());
    assertEquals(2, inner1.get(1).getAsInt());

    JsonArray inner2 = outer.get(1).getAsJsonArray();
    assertEquals(2, inner2.size());
    assertEquals(3, inner2.get(0).getAsInt());
    assertEquals(4, inner2.get(1).getAsInt());
  }

  @Test
  public void read_deeplyNestedArrays_returnsJsonArray() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[[[1]]]"));
    JsonElement result = adapter.read(reader);
    assertTrue(result.isJsonArray());
    JsonElement level1 = result.getAsJsonArray().get(0);
    assertTrue(level1.isJsonArray());
    JsonElement level2 = level1.getAsJsonArray().get(0);
    assertTrue(level2.isJsonArray());
    assertEquals(1, level2.getAsJsonArray().get(0).getAsInt());
  }

  // ==========================================================================
  // read() tests - empty object
  // ==========================================================================

  @Test
  public void read_emptyObject_returnsEmptyJsonObject() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    JsonElement result = adapter.read(reader);
    assertTrue(result.isJsonObject());
    assertEquals(0, result.getAsJsonObject().size());
  }

  // ==========================================================================
  // read() tests - object with primitives
  // ==========================================================================

  @Test
  public void read_objectWithStringValue_returnsJsonObject() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{\"key\": \"value\"}"));
    JsonElement result = adapter.read(reader);
    assertTrue(result.isJsonObject());
    JsonObject obj = result.getAsJsonObject();
    assertEquals("value", obj.get("key").getAsString());
  }

  @Test
  public void read_objectWithNumberValue_returnsJsonObject() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{\"count\": 42}"));
    JsonElement result = adapter.read(reader);
    assertTrue(result.isJsonObject());
    JsonObject obj = result.getAsJsonObject();
    assertEquals(42, obj.get("count").getAsInt());
  }

  @Test
  public void read_objectWithBooleanValue_returnsJsonObject() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{\"active\": true}"));
    JsonElement result = adapter.read(reader);
    assertTrue(result.isJsonObject());
    JsonObject obj = result.getAsJsonObject();
    assertTrue(obj.get("active").getAsBoolean());
  }

  @Test
  public void read_objectWithNullValue_returnsJsonObject() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{\"nothing\": null}"));
    JsonElement result = adapter.read(reader);
    assertTrue(result.isJsonObject());
    JsonObject obj = result.getAsJsonObject();
    assertTrue(obj.get("nothing").isJsonNull());
  }

  @Test
  public void read_objectWithMultipleProperties_returnsJsonObject() throws IOException {
    JsonReader reader =
        new JsonReader(
            new StringReader("{\"name\": \"test\", \"count\": 5, \"active\": false}"));
    JsonElement result = adapter.read(reader);
    assertTrue(result.isJsonObject());
    JsonObject obj = result.getAsJsonObject();
    assertEquals(3, obj.size());
    assertEquals("test", obj.get("name").getAsString());
    assertEquals(5, obj.get("count").getAsInt());
    assertFalse(obj.get("active").getAsBoolean());
  }

  // ==========================================================================
  // read() tests - nested objects
  // ==========================================================================

  @Test
  public void read_nestedObject_returnsJsonObject() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{\"outer\": {\"inner\": 42}}"));
    JsonElement result = adapter.read(reader);
    assertTrue(result.isJsonObject());
    JsonObject outer = result.getAsJsonObject();
    JsonObject inner = outer.get("outer").getAsJsonObject();
    assertEquals(42, inner.get("inner").getAsInt());
  }

  @Test
  public void read_deeplyNestedObject_returnsJsonObject() throws IOException {
    JsonReader reader =
        new JsonReader(new StringReader("{\"a\": {\"b\": {\"c\": \"deep\"}}}"));
    JsonElement result = adapter.read(reader);
    assertTrue(result.isJsonObject());
    JsonElement c =
        result
            .getAsJsonObject()
            .get("a")
            .getAsJsonObject()
            .get("b")
            .getAsJsonObject()
            .get("c");
    assertEquals("deep", c.getAsString());
  }

  // ==========================================================================
  // read() tests - mixed arrays and objects
  // ==========================================================================

  @Test
  public void read_objectWithArray_returnsJsonObject() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{\"items\": [1, 2, 3]}"));
    JsonElement result = adapter.read(reader);
    assertTrue(result.isJsonObject());
    JsonArray items = result.getAsJsonObject().get("items").getAsJsonArray();
    assertEquals(3, items.size());
  }

  @Test
  public void read_arrayWithObjects_returnsJsonArray() throws IOException {
    JsonReader reader =
        new JsonReader(new StringReader("[{\"name\": \"a\"}, {\"name\": \"b\"}]"));
    JsonElement result = adapter.read(reader);
    assertTrue(result.isJsonArray());
    JsonArray array = result.getAsJsonArray();
    assertEquals(2, array.size());
    assertEquals("a", array.get(0).getAsJsonObject().get("name").getAsString());
    assertEquals("b", array.get(1).getAsJsonObject().get("name").getAsString());
  }

  @Test
  public void read_complexNestedStructure_returnsCorrectStructure() throws IOException {
    String json =
        "{\"users\": [{\"name\": \"Alice\", \"scores\": [95, 87]}, {\"name\": \"Bob\", \"scores\": [72, 88]}]}";
    JsonReader reader = new JsonReader(new StringReader(json));
    JsonElement result = adapter.read(reader);

    assertTrue(result.isJsonObject());
    JsonArray users = result.getAsJsonObject().get("users").getAsJsonArray();
    assertEquals(2, users.size());

    JsonObject alice = users.get(0).getAsJsonObject();
    assertEquals("Alice", alice.get("name").getAsString());
    JsonArray aliceScores = alice.get("scores").getAsJsonArray();
    assertEquals(95, aliceScores.get(0).getAsInt());
    assertEquals(87, aliceScores.get(1).getAsInt());

    JsonObject bob = users.get(1).getAsJsonObject();
    assertEquals("Bob", bob.get("name").getAsString());
    JsonArray bobScores = bob.get("scores").getAsJsonArray();
    assertEquals(72, bobScores.get(0).getAsInt());
    assertEquals(88, bobScores.get(1).getAsInt());
  }

  // ==========================================================================
  // read() tests - JsonTreeReader optimization path
  // ==========================================================================

  @Test
  public void read_fromJsonTreeReader_returnsOriginalElement() throws IOException {
    JsonObject original = new JsonObject();
    original.addProperty("key", "value");
    original.addProperty("number", 42);

    JsonTreeReader treeReader = new JsonTreeReader(original);
    JsonElement result = adapter.read(treeReader);

    // The optimization path should return the same JsonElement
    assertNotNull(result);
    assertTrue(result.isJsonObject());
    assertEquals("value", result.getAsJsonObject().get("key").getAsString());
    assertEquals(42, result.getAsJsonObject().get("number").getAsInt());
  }

  @Test
  public void read_fromJsonTreeReaderWithArray_returnsOriginalElement() throws IOException {
    JsonArray original = new JsonArray();
    original.add(1);
    original.add(2);
    original.add(3);

    JsonTreeReader treeReader = new JsonTreeReader(original);
    JsonElement result = adapter.read(treeReader);

    assertNotNull(result);
    assertTrue(result.isJsonArray());
    assertEquals(3, result.getAsJsonArray().size());
  }

  @Test
  public void read_fromJsonTreeReaderWithPrimitive_returnsElement() throws IOException {
    JsonPrimitive original = new JsonPrimitive("test");

    JsonTreeReader treeReader = new JsonTreeReader(original);
    JsonElement result = adapter.read(treeReader);

    assertNotNull(result);
    assertTrue(result.isJsonPrimitive());
    assertEquals("test", result.getAsString());
  }

  @Test
  public void read_fromJsonTreeReaderWithNull_returnsJsonNull() throws IOException {
    JsonTreeReader treeReader = new JsonTreeReader(JsonNull.INSTANCE);
    JsonElement result = adapter.read(treeReader);

    assertNotNull(result);
    assertTrue(result.isJsonNull());
  }

  // ==========================================================================
  // read() tests - error handling
  // ==========================================================================

  @Test
  public void read_invalidState_throwsIllegalStateException() throws IOException {
    // Create a reader in an invalid state by reading past the end
    JsonReader reader = new JsonReader(new StringReader("{}"));
    reader.beginObject();
    reader.endObject();

    // Now the reader is in END_DOCUMENT state, which should throw
    try {
      adapter.read(reader);
      fail("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertTrue(e.getMessage().contains("END_DOCUMENT"));
    }
  }

  // ==========================================================================
  // write() tests - null values
  // ==========================================================================

  @Test
  public void write_nullValue_writesNull() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, null);
    assertEquals("null", stringWriter.toString());
  }

  @Test
  public void write_jsonNull_writesNull() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, JsonNull.INSTANCE);
    assertEquals("null", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - primitive string
  // ==========================================================================

  @Test
  public void write_stringPrimitive_writesCorrectJson() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, new JsonPrimitive("hello"));
    assertEquals("\"hello\"", stringWriter.toString());
  }

  @Test
  public void write_emptyStringPrimitive_writesCorrectJson() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, new JsonPrimitive(""));
    assertEquals("\"\"", stringWriter.toString());
  }

  @Test
  public void write_stringWithSpecialChars_escapesCorrectly() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, new JsonPrimitive("hello\nworld\t!"));
    assertTrue(stringWriter.toString().contains("\\n"));
    assertTrue(stringWriter.toString().contains("\\t"));
  }

  // ==========================================================================
  // write() tests - primitive numbers
  // ==========================================================================

  @Test
  public void write_integerPrimitive_writesCorrectJson() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, new JsonPrimitive(42));
    assertEquals("42", stringWriter.toString());
  }

  @Test
  public void write_negativePrimitive_writesCorrectJson() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, new JsonPrimitive(-123));
    assertEquals("-123", stringWriter.toString());
  }

  @Test
  public void write_doublePrimitive_writesCorrectJson() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, new JsonPrimitive(3.14));
    assertEquals("3.14", stringWriter.toString());
  }

  @Test
  public void write_longPrimitive_writesCorrectJson() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, new JsonPrimitive(9999999999999L));
    assertEquals("9999999999999", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - primitive boolean
  // ==========================================================================

  @Test
  public void write_truePrimitive_writesCorrectJson() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, new JsonPrimitive(true));
    assertEquals("true", stringWriter.toString());
  }

  @Test
  public void write_falsePrimitive_writesCorrectJson() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, new JsonPrimitive(false));
    assertEquals("false", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - empty array
  // ==========================================================================

  @Test
  public void write_emptyArray_writesCorrectJson() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, new JsonArray());
    assertEquals("[]", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - array with primitives
  // ==========================================================================

  @Test
  public void write_arrayWithStrings_writesCorrectJson() throws IOException {
    JsonArray array = new JsonArray();
    array.add("a");
    array.add("b");
    array.add("c");

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, array);
    assertEquals("[\"a\",\"b\",\"c\"]", stringWriter.toString());
  }

  @Test
  public void write_arrayWithNumbers_writesCorrectJson() throws IOException {
    JsonArray array = new JsonArray();
    array.add(1);
    array.add(2);
    array.add(3);

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, array);
    assertEquals("[1,2,3]", stringWriter.toString());
  }

  @Test
  public void write_arrayWithBooleans_writesCorrectJson() throws IOException {
    JsonArray array = new JsonArray();
    array.add(true);
    array.add(false);

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, array);
    assertEquals("[true,false]", stringWriter.toString());
  }

  @Test
  public void write_arrayWithNulls_writesCorrectJson() throws IOException {
    JsonArray array = new JsonArray();
    array.add(JsonNull.INSTANCE);
    array.add(JsonNull.INSTANCE);

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, array);
    assertEquals("[null,null]", stringWriter.toString());
  }

  @Test
  public void write_arrayWithMixedTypes_writesCorrectJson() throws IOException {
    JsonArray array = new JsonArray();
    array.add("text");
    array.add(42);
    array.add(true);
    array.add(JsonNull.INSTANCE);

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, array);
    assertEquals("[\"text\",42,true,null]", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - nested arrays
  // ==========================================================================

  @Test
  public void write_nestedArrays_writesCorrectJson() throws IOException {
    JsonArray inner1 = new JsonArray();
    inner1.add(1);
    inner1.add(2);

    JsonArray inner2 = new JsonArray();
    inner2.add(3);
    inner2.add(4);

    JsonArray outer = new JsonArray();
    outer.add(inner1);
    outer.add(inner2);

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, outer);
    assertEquals("[[1,2],[3,4]]", stringWriter.toString());
  }

  @Test
  public void write_deeplyNestedArrays_writesCorrectJson() throws IOException {
    JsonArray deepest = new JsonArray();
    deepest.add(1);

    JsonArray middle = new JsonArray();
    middle.add(deepest);

    JsonArray outer = new JsonArray();
    outer.add(middle);

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, outer);
    assertEquals("[[[1]]]", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - empty object
  // ==========================================================================

  @Test
  public void write_emptyObject_writesCorrectJson() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, new JsonObject());
    assertEquals("{}", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - object with primitives
  // ==========================================================================

  @Test
  public void write_objectWithStringProperty_writesCorrectJson() throws IOException {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", "value");

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, obj);
    assertEquals("{\"key\":\"value\"}", stringWriter.toString());
  }

  @Test
  public void write_objectWithNumberProperty_writesCorrectJson() throws IOException {
    JsonObject obj = new JsonObject();
    obj.addProperty("count", 42);

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, obj);
    assertEquals("{\"count\":42}", stringWriter.toString());
  }

  @Test
  public void write_objectWithBooleanProperty_writesCorrectJson() throws IOException {
    JsonObject obj = new JsonObject();
    obj.addProperty("active", true);

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, obj);
    assertEquals("{\"active\":true}", stringWriter.toString());
  }

  @Test
  public void write_objectWithNullProperty_writesCorrectJson() throws IOException {
    JsonObject obj = new JsonObject();
    obj.add("nothing", JsonNull.INSTANCE);

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, obj);
    assertEquals("{\"nothing\":null}", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - nested objects
  // ==========================================================================

  @Test
  public void write_nestedObject_writesCorrectJson() throws IOException {
    JsonObject inner = new JsonObject();
    inner.addProperty("inner", 42);

    JsonObject outer = new JsonObject();
    outer.add("outer", inner);

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, outer);
    assertEquals("{\"outer\":{\"inner\":42}}", stringWriter.toString());
  }

  @Test
  public void write_deeplyNestedObject_writesCorrectJson() throws IOException {
    JsonObject c = new JsonObject();
    c.addProperty("c", "deep");

    JsonObject b = new JsonObject();
    b.add("b", c);

    JsonObject a = new JsonObject();
    a.add("a", b);

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, a);
    assertEquals("{\"a\":{\"b\":{\"c\":\"deep\"}}}", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - mixed arrays and objects
  // ==========================================================================

  @Test
  public void write_objectWithArray_writesCorrectJson() throws IOException {
    JsonArray array = new JsonArray();
    array.add(1);
    array.add(2);
    array.add(3);

    JsonObject obj = new JsonObject();
    obj.add("items", array);

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, obj);
    assertEquals("{\"items\":[1,2,3]}", stringWriter.toString());
  }

  @Test
  public void write_arrayWithObjects_writesCorrectJson() throws IOException {
    JsonObject obj1 = new JsonObject();
    obj1.addProperty("name", "a");

    JsonObject obj2 = new JsonObject();
    obj2.addProperty("name", "b");

    JsonArray array = new JsonArray();
    array.add(obj1);
    array.add(obj2);

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, array);
    assertEquals("[{\"name\":\"a\"},{\"name\":\"b\"}]", stringWriter.toString());
  }

  // ==========================================================================
  // Round-trip tests (read and write)
  // ==========================================================================

  @Test
  public void roundTrip_primitiveString_preservesData() throws IOException {
    JsonPrimitive original = new JsonPrimitive("hello world");
    String json = toJson(original);
    JsonElement result = fromJson(json);
    assertEquals(original.getAsString(), result.getAsString());
  }

  @Test
  public void roundTrip_primitiveNumber_preservesData() throws IOException {
    JsonPrimitive original = new JsonPrimitive(12345);
    String json = toJson(original);
    JsonElement result = fromJson(json);
    assertEquals(original.getAsInt(), result.getAsInt());
  }

  @Test
  public void roundTrip_primitiveBoolean_preservesData() throws IOException {
    JsonPrimitive original = new JsonPrimitive(true);
    String json = toJson(original);
    JsonElement result = fromJson(json);
    assertEquals(original.getAsBoolean(), result.getAsBoolean());
  }

  @Test
  public void roundTrip_jsonNull_preservesData() throws IOException {
    String json = toJson(JsonNull.INSTANCE);
    JsonElement result = fromJson(json);
    assertTrue(result.isJsonNull());
  }

  @Test
  public void roundTrip_emptyArray_preservesData() throws IOException {
    JsonArray original = new JsonArray();
    String json = toJson(original);
    JsonElement result = fromJson(json);
    assertTrue(result.isJsonArray());
    assertEquals(0, result.getAsJsonArray().size());
  }

  @Test
  public void roundTrip_arrayWithMixedTypes_preservesData() throws IOException {
    JsonArray original = new JsonArray();
    original.add("text");
    original.add(42);
    original.add(true);
    original.add(JsonNull.INSTANCE);

    String json = toJson(original);
    JsonElement result = fromJson(json);

    assertTrue(result.isJsonArray());
    JsonArray arr = result.getAsJsonArray();
    assertEquals(4, arr.size());
    assertEquals("text", arr.get(0).getAsString());
    assertEquals(42, arr.get(1).getAsInt());
    assertTrue(arr.get(2).getAsBoolean());
    assertTrue(arr.get(3).isJsonNull());
  }

  @Test
  public void roundTrip_emptyObject_preservesData() throws IOException {
    JsonObject original = new JsonObject();
    String json = toJson(original);
    JsonElement result = fromJson(json);
    assertTrue(result.isJsonObject());
    assertEquals(0, result.getAsJsonObject().size());
  }

  @Test
  public void roundTrip_complexObject_preservesData() throws IOException {
    JsonObject user = new JsonObject();
    user.addProperty("name", "Alice");
    user.addProperty("age", 30);
    user.addProperty("active", true);

    JsonArray scores = new JsonArray();
    scores.add(95);
    scores.add(87);
    user.add("scores", scores);

    String json = toJson(user);
    JsonElement result = fromJson(json);

    assertTrue(result.isJsonObject());
    JsonObject obj = result.getAsJsonObject();
    assertEquals("Alice", obj.get("name").getAsString());
    assertEquals(30, obj.get("age").getAsInt());
    assertTrue(obj.get("active").getAsBoolean());

    JsonArray resultScores = obj.get("scores").getAsJsonArray();
    assertEquals(2, resultScores.size());
    assertEquals(95, resultScores.get(0).getAsInt());
    assertEquals(87, resultScores.get(1).getAsInt());
  }

  // ==========================================================================
  // Helper methods
  // ==========================================================================

  private String toJson(JsonElement element) throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, element);
    return stringWriter.toString();
  }

  private JsonElement fromJson(String json) throws IOException {
    JsonReader reader = new JsonReader(new StringReader(json));
    return adapter.read(reader);
  }
}
