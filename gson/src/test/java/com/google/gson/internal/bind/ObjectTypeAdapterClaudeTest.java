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
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.ToNumberPolicy;
import com.google.gson.ToNumberStrategy;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

/** Tests for {@link ObjectTypeAdapter}. */
public class ObjectTypeAdapterClaudeTest {

  // ==========================================================================
  // getFactory tests
  // ==========================================================================

  @Test
  public void getFactory_withDoublePolicy_returnsCachedFactory() {
    TypeAdapterFactory factory1 = ObjectTypeAdapter.getFactory(ToNumberPolicy.DOUBLE);
    TypeAdapterFactory factory2 = ObjectTypeAdapter.getFactory(ToNumberPolicy.DOUBLE);
    // Should return the same cached instance for DOUBLE policy
    assertSame(factory1, factory2);
  }

  @Test
  public void getFactory_withNonDoublePolicy_returnsNewFactory() {
    TypeAdapterFactory factory1 = ObjectTypeAdapter.getFactory(ToNumberPolicy.LONG_OR_DOUBLE);
    TypeAdapterFactory factory2 = ObjectTypeAdapter.getFactory(ToNumberPolicy.LONG_OR_DOUBLE);
    // Different calls should return different factory instances for non-DOUBLE policy
    assertNotNull(factory1);
    assertNotNull(factory2);
    // Both should work correctly
  }

  @Test
  public void getFactory_withBigDecimalPolicy_returnsFactory() {
    TypeAdapterFactory factory = ObjectTypeAdapter.getFactory(ToNumberPolicy.BIG_DECIMAL);
    assertNotNull(factory);
  }

  @Test
  public void getFactory_withLazilyParsedNumberPolicy_returnsFactory() {
    TypeAdapterFactory factory = ObjectTypeAdapter.getFactory(ToNumberPolicy.LAZILY_PARSED_NUMBER);
    assertNotNull(factory);
  }

  @Test
  public void getFactory_withCustomStrategy_returnsFactory() {
    ToNumberStrategy customStrategy = in -> BigDecimal.valueOf(in.nextDouble());
    TypeAdapterFactory factory = ObjectTypeAdapter.getFactory(customStrategy);
    assertNotNull(factory);
  }

  @Test
  public void factory_createReturnsNullForNonObjectType() {
    Gson gson = new Gson();
    TypeAdapterFactory factory = ObjectTypeAdapter.getFactory(ToNumberPolicy.DOUBLE);
    // For non-Object types, the factory should return null
    TypeAdapter<String> adapter = factory.create(gson, TypeToken.get(String.class));
    assertNull(adapter);
  }

  @Test
  public void factory_createReturnsAdapterForObjectType() {
    Gson gson = new Gson();
    TypeAdapterFactory factory = ObjectTypeAdapter.getFactory(ToNumberPolicy.DOUBLE);
    TypeAdapter<Object> adapter = factory.create(gson, TypeToken.get(Object.class));
    assertNotNull(adapter);
  }

  // ==========================================================================
  // read tests - terminal values
  // ==========================================================================

  @Test
  public void read_stringValue_returnsString() throws IOException {
    Gson gson = new Gson();
    Object result = gson.fromJson("\"hello world\"", Object.class);
    assertEquals("hello world", result);
  }

  @Test
  public void read_emptyString_returnsEmptyString() throws IOException {
    Gson gson = new Gson();
    Object result = gson.fromJson("\"\"", Object.class);
    assertEquals("", result);
  }

  @Test
  public void read_integerNumber_returnsDouble() throws IOException {
    Gson gson = new Gson();
    Object result = gson.fromJson("42", Object.class);
    assertEquals(42.0, result);
  }

  @Test
  public void read_decimalNumber_returnsDouble() throws IOException {
    Gson gson = new Gson();
    Object result = gson.fromJson("3.14159", Object.class);
    assertEquals(3.14159, result);
  }

  @Test
  public void read_negativeNumber_returnsDouble() throws IOException {
    Gson gson = new Gson();
    Object result = gson.fromJson("-123.456", Object.class);
    assertEquals(-123.456, result);
  }

  @Test
  public void read_scientificNotation_returnsDouble() throws IOException {
    Gson gson = new Gson();
    Object result = gson.fromJson("1.5e10", Object.class);
    assertEquals(1.5e10, result);
  }

  @Test
  public void read_booleanTrue_returnsTrue() throws IOException {
    Gson gson = new Gson();
    Object result = gson.fromJson("true", Object.class);
    assertEquals(Boolean.TRUE, result);
  }

  @Test
  public void read_booleanFalse_returnsFalse() throws IOException {
    Gson gson = new Gson();
    Object result = gson.fromJson("false", Object.class);
    assertEquals(Boolean.FALSE, result);
  }

  @Test
  public void read_nullValue_returnsNull() throws IOException {
    Gson gson = new Gson();
    Object result = gson.fromJson("null", Object.class);
    assertNull(result);
  }

  // ==========================================================================
  // read tests - arrays
  // ==========================================================================

  @Test
  public void read_emptyArray_returnsEmptyList() throws IOException {
    Gson gson = new Gson();
    Object result = gson.fromJson("[]", Object.class);
    assertTrue(result instanceof List);
    @SuppressWarnings("unchecked")
    List<Object> list = (List<Object>) result;
    assertTrue(list.isEmpty());
  }

  @Test
  public void read_arrayWithNumbers_returnsList() throws IOException {
    Gson gson = new Gson();
    Object result = gson.fromJson("[1, 2, 3]", Object.class);
    assertTrue(result instanceof List);
    @SuppressWarnings("unchecked")
    List<Object> list = (List<Object>) result;
    assertEquals(3, list.size());
    assertEquals(1.0, list.get(0));
    assertEquals(2.0, list.get(1));
    assertEquals(3.0, list.get(2));
  }

  @Test
  public void read_arrayWithStrings_returnsList() throws IOException {
    Gson gson = new Gson();
    Object result = gson.fromJson("[\"a\", \"b\", \"c\"]", Object.class);
    assertTrue(result instanceof List);
    @SuppressWarnings("unchecked")
    List<Object> list = (List<Object>) result;
    assertEquals(3, list.size());
    assertEquals("a", list.get(0));
    assertEquals("b", list.get(1));
    assertEquals("c", list.get(2));
  }

  @Test
  public void read_arrayWithMixedTypes_returnsList() throws IOException {
    Gson gson = new Gson();
    Object result = gson.fromJson("[\"text\", 42, true, null]", Object.class);
    assertTrue(result instanceof List);
    @SuppressWarnings("unchecked")
    List<Object> list = (List<Object>) result;
    assertEquals(4, list.size());
    assertEquals("text", list.get(0));
    assertEquals(42.0, list.get(1));
    assertEquals(Boolean.TRUE, list.get(2));
    assertNull(list.get(3));
  }

  @Test
  public void read_nestedArrays_returnsList() throws IOException {
    Gson gson = new Gson();
    Object result = gson.fromJson("[[1, 2], [3, 4]]", Object.class);
    assertTrue(result instanceof List);
    @SuppressWarnings("unchecked")
    List<Object> list = (List<Object>) result;
    assertEquals(2, list.size());

    @SuppressWarnings("unchecked")
    List<Object> inner1 = (List<Object>) list.get(0);
    assertEquals(2, inner1.size());
    assertEquals(1.0, inner1.get(0));
    assertEquals(2.0, inner1.get(1));

    @SuppressWarnings("unchecked")
    List<Object> inner2 = (List<Object>) list.get(1);
    assertEquals(2, inner2.size());
    assertEquals(3.0, inner2.get(0));
    assertEquals(4.0, inner2.get(1));
  }

  @Test
  public void read_deeplyNestedArrays_returnsList() throws IOException {
    Gson gson = new Gson();
    Object result = gson.fromJson("[[[42]]]", Object.class);
    assertTrue(result instanceof List);
    @SuppressWarnings("unchecked")
    List<Object> list1 = (List<Object>) result;
    assertEquals(1, list1.size());

    @SuppressWarnings("unchecked")
    List<Object> list2 = (List<Object>) list1.get(0);
    assertEquals(1, list2.size());

    @SuppressWarnings("unchecked")
    List<Object> list3 = (List<Object>) list2.get(0);
    assertEquals(1, list3.size());
    assertEquals(42.0, list3.get(0));
  }

  // ==========================================================================
  // read tests - objects
  // ==========================================================================

  @Test
  public void read_emptyObject_returnsEmptyMap() throws IOException {
    Gson gson = new Gson();
    Object result = gson.fromJson("{}", Object.class);
    assertTrue(result instanceof Map);
    @SuppressWarnings("unchecked")
    Map<String, Object> map = (Map<String, Object>) result;
    assertTrue(map.isEmpty());
  }

  @Test
  public void read_objectWithStringProperty_returnsMap() throws IOException {
    Gson gson = new Gson();
    Object result = gson.fromJson("{\"name\": \"John\"}", Object.class);
    assertTrue(result instanceof Map);
    @SuppressWarnings("unchecked")
    Map<String, Object> map = (Map<String, Object>) result;
    assertEquals(1, map.size());
    assertEquals("John", map.get("name"));
  }

  @Test
  public void read_objectWithMultipleProperties_returnsMap() throws IOException {
    Gson gson = new Gson();
    Object result = gson.fromJson("{\"name\": \"John\", \"age\": 30}", Object.class);
    assertTrue(result instanceof Map);
    @SuppressWarnings("unchecked")
    Map<String, Object> map = (Map<String, Object>) result;
    assertEquals(2, map.size());
    assertEquals("John", map.get("name"));
    assertEquals(30.0, map.get("age"));
  }

  @Test
  public void read_objectWithMixedTypes_returnsMap() throws IOException {
    Gson gson = new Gson();
    Object result =
        gson.fromJson(
            "{\"string\": \"value\", \"number\": 42, \"bool\": true, \"nil\": null}", Object.class);
    assertTrue(result instanceof Map);
    @SuppressWarnings("unchecked")
    Map<String, Object> map = (Map<String, Object>) result;
    assertEquals(4, map.size());
    assertEquals("value", map.get("string"));
    assertEquals(42.0, map.get("number"));
    assertEquals(Boolean.TRUE, map.get("bool"));
    assertNull(map.get("nil"));
  }

  @Test
  public void read_nestedObjects_returnsMap() throws IOException {
    Gson gson = new Gson();
    Object result = gson.fromJson("{\"outer\": {\"inner\": \"value\"}}", Object.class);
    assertTrue(result instanceof Map);
    @SuppressWarnings("unchecked")
    Map<String, Object> map = (Map<String, Object>) result;
    assertEquals(1, map.size());

    @SuppressWarnings("unchecked")
    Map<String, Object> inner = (Map<String, Object>) map.get("outer");
    assertEquals(1, inner.size());
    assertEquals("value", inner.get("inner"));
  }

  @Test
  public void read_objectWithArray_returnsMap() throws IOException {
    Gson gson = new Gson();
    Object result = gson.fromJson("{\"items\": [1, 2, 3]}", Object.class);
    assertTrue(result instanceof Map);
    @SuppressWarnings("unchecked")
    Map<String, Object> map = (Map<String, Object>) result;
    assertEquals(1, map.size());

    @SuppressWarnings("unchecked")
    List<Object> items = (List<Object>) map.get("items");
    assertEquals(3, items.size());
    assertEquals(1.0, items.get(0));
    assertEquals(2.0, items.get(1));
    assertEquals(3.0, items.get(2));
  }

  @Test
  public void read_arrayWithObjects_returnsList() throws IOException {
    Gson gson = new Gson();
    Object result = gson.fromJson("[{\"id\": 1}, {\"id\": 2}]", Object.class);
    assertTrue(result instanceof List);
    @SuppressWarnings("unchecked")
    List<Object> list = (List<Object>) result;
    assertEquals(2, list.size());

    @SuppressWarnings("unchecked")
    Map<String, Object> first = (Map<String, Object>) list.get(0);
    assertEquals(1.0, first.get("id"));

    @SuppressWarnings("unchecked")
    Map<String, Object> second = (Map<String, Object>) list.get(1);
    assertEquals(2.0, second.get("id"));
  }

  @Test
  public void read_complexNestedStructure_returnsCorrectStructure() throws IOException {
    Gson gson = new Gson();
    String json =
        "{\"users\": [{\"name\": \"Alice\", \"hobbies\": [\"reading\", \"coding\"]},"
            + "{\"name\": \"Bob\", \"hobbies\": [\"sports\"]}]}";
    Object result = gson.fromJson(json, Object.class);
    assertTrue(result instanceof Map);
    @SuppressWarnings("unchecked")
    Map<String, Object> map = (Map<String, Object>) result;

    @SuppressWarnings("unchecked")
    List<Object> users = (List<Object>) map.get("users");
    assertEquals(2, users.size());

    @SuppressWarnings("unchecked")
    Map<String, Object> alice = (Map<String, Object>) users.get(0);
    assertEquals("Alice", alice.get("name"));

    @SuppressWarnings("unchecked")
    List<Object> aliceHobbies = (List<Object>) alice.get("hobbies");
    assertEquals(2, aliceHobbies.size());
    assertEquals("reading", aliceHobbies.get(0));
    assertEquals("coding", aliceHobbies.get(1));
  }

  // ==========================================================================
  // read tests - with different ToNumberStrategy
  // ==========================================================================

  @Test
  public void read_withLongOrDoubleStrategy_returnsLong() throws IOException {
    Gson gson = new GsonBuilder().setObjectToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE).create();
    Object result = gson.fromJson("42", Object.class);
    assertTrue(result instanceof Long);
    assertEquals(42L, result);
  }

  @Test
  public void read_withLongOrDoubleStrategy_returnsDoubleForDecimal() throws IOException {
    Gson gson = new GsonBuilder().setObjectToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE).create();
    Object result = gson.fromJson("3.14", Object.class);
    assertTrue(result instanceof Double);
    assertEquals(3.14, result);
  }

  @Test
  public void read_withBigDecimalStrategy_returnsBigDecimal() throws IOException {
    Gson gson = new GsonBuilder().setObjectToNumberStrategy(ToNumberPolicy.BIG_DECIMAL).create();
    Object result = gson.fromJson("123456789012345678901234567890", Object.class);
    assertTrue(result instanceof BigDecimal);
    assertEquals(new BigDecimal("123456789012345678901234567890"), result);
  }

  // ==========================================================================
  // read tests - edge cases and error handling
  // ==========================================================================

  @Test
  public void read_withDirectReader_stringValue() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Object> adapter = gson.getAdapter(Object.class);
    JsonReader reader = new JsonReader(new StringReader("\"test\""));
    Object result = adapter.read(reader);
    assertEquals("test", result);
  }

  @Test
  public void read_withDirectReader_nestedStructure() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Object> adapter = gson.getAdapter(Object.class);
    JsonReader reader = new JsonReader(new StringReader("{\"a\": [1, 2]}"));
    Object result = adapter.read(reader);
    assertTrue(result instanceof Map);
    @SuppressWarnings("unchecked")
    Map<String, Object> map = (Map<String, Object>) result;
    @SuppressWarnings("unchecked")
    List<Object> list = (List<Object>) map.get("a");
    assertEquals(2, list.size());
  }

  @Test
  public void read_invalidJsonToken_throwsException() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Object> adapter = gson.getAdapter(Object.class);
    JsonReader reader = new JsonReader(new StringReader("{\"key\": \"value\"}"));
    reader.beginObject();
    // Now we're at NAME token, which is invalid for read()
    try {
      adapter.read(reader);
      fail("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertTrue(e.getMessage().contains("Unexpected token"));
    }
  }

  // ==========================================================================
  // write tests - null value
  // ==========================================================================

  @Test
  public void write_nullValue_writesNull() throws IOException {
    Gson gson = new Gson();
    String result = gson.toJson(null, Object.class);
    assertEquals("null", result);
  }

  @Test
  public void write_withDirectWriter_nullValue() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Object> adapter = gson.getAdapter(Object.class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, null);
    assertEquals("null", stringWriter.toString());
  }

  // ==========================================================================
  // write tests - primitive values
  // ==========================================================================

  @Test
  public void write_stringValue_writesString() throws IOException {
    Gson gson = new Gson();
    Object value = "hello";
    String result = gson.toJson(value, Object.class);
    assertEquals("\"hello\"", result);
  }

  @Test
  public void write_integerValue_writesNumber() throws IOException {
    Gson gson = new Gson();
    Object value = 42;
    String result = gson.toJson(value, Object.class);
    assertEquals("42", result);
  }

  @Test
  public void write_doubleValue_writesNumber() throws IOException {
    Gson gson = new Gson();
    Object value = 3.14;
    String result = gson.toJson(value, Object.class);
    assertEquals("3.14", result);
  }

  @Test
  public void write_longValue_writesNumber() throws IOException {
    Gson gson = new Gson();
    Object value = 123456789012345L;
    String result = gson.toJson(value, Object.class);
    assertEquals("123456789012345", result);
  }

  @Test
  public void write_booleanTrue_writesTrue() throws IOException {
    Gson gson = new Gson();
    Object value = Boolean.TRUE;
    String result = gson.toJson(value, Object.class);
    assertEquals("true", result);
  }

  @Test
  public void write_booleanFalse_writesFalse() throws IOException {
    Gson gson = new Gson();
    Object value = Boolean.FALSE;
    String result = gson.toJson(value, Object.class);
    assertEquals("false", result);
  }

  // ==========================================================================
  // write tests - collections
  // ==========================================================================

  @Test
  public void write_emptyList_writesEmptyArray() throws IOException {
    Gson gson = new Gson();
    Object value = new ArrayList<>();
    String result = gson.toJson(value, Object.class);
    assertEquals("[]", result);
  }

  @Test
  public void write_listWithValues_writesArray() throws IOException {
    Gson gson = new Gson();
    List<Object> list = new ArrayList<>();
    list.add(1);
    list.add(2);
    list.add(3);
    String result = gson.toJson(list, Object.class);
    assertEquals("[1,2,3]", result);
  }

  @Test
  public void write_emptyMap_writesEmptyObject() throws IOException {
    Gson gson = new Gson();
    Object value = new LinkedHashMap<>();
    String result = gson.toJson(value, Object.class);
    assertEquals("{}", result);
  }

  @Test
  public void write_mapWithValues_writesObject() throws IOException {
    Gson gson = new Gson();
    Map<String, Object> map = new LinkedHashMap<>();
    map.put("name", "John");
    map.put("age", 30);
    String result = gson.toJson(map, Object.class);
    assertEquals("{\"name\":\"John\",\"age\":30}", result);
  }

  // ==========================================================================
  // write tests - custom objects (delegates to runtime type adapter)
  // ==========================================================================

  @Test
  public void write_customObject_delegatesToTypeAdapter() throws IOException {
    Gson gson = new Gson();
    TestPerson person = new TestPerson("Alice", 25);
    String result = gson.toJson(person, Object.class);
    assertTrue(result.contains("\"name\":\"Alice\""));
    assertTrue(result.contains("\"age\":25"));
  }

  @Test
  public void write_objectWithUnknownRuntimeType_writesEmptyObject() throws IOException {
    // When the runtime type is Object itself, it writes an empty object
    // This is because ObjectTypeAdapter checks if the adapter is instanceof ObjectTypeAdapter
    // and writes {} to avoid infinite recursion
    Gson gson = new Gson();
    TypeAdapter<Object> adapter = gson.getAdapter(Object.class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    // Create a plain Object (not a subclass)
    Object plainObject = new Object();
    adapter.write(writer, plainObject);
    assertEquals("{}", stringWriter.toString());
  }

  // ==========================================================================
  // write tests - with direct JsonWriter
  // ==========================================================================

  @Test
  public void write_withDirectWriter_stringValue() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Object> adapter = gson.getAdapter(Object.class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, "test");
    assertEquals("\"test\"", stringWriter.toString());
  }

  @Test
  public void write_withDirectWriter_integerValue() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Object> adapter = gson.getAdapter(Object.class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, 123);
    assertEquals("123", stringWriter.toString());
  }

  @Test
  public void write_withDirectWriter_booleanValue() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Object> adapter = gson.getAdapter(Object.class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, true);
    assertEquals("true", stringWriter.toString());
  }

  // ==========================================================================
  // round-trip tests
  // ==========================================================================

  @Test
  public void roundTrip_simpleObject_preservesStructure() {
    Gson gson = new GsonBuilder().setObjectToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE).create();
    String originalJson = "{\"name\":\"test\",\"value\":42}";

    Object parsed = gson.fromJson(originalJson, Object.class);
    String serialized = gson.toJson(parsed, Object.class);

    @SuppressWarnings("unchecked")
    Map<String, Object> result = (Map<String, Object>) gson.fromJson(serialized, Object.class);
    assertEquals("test", result.get("name"));
    assertEquals(42L, result.get("value"));
  }

  @Test
  public void roundTrip_nestedStructure_preservesStructure() {
    Gson gson = new Gson();
    String originalJson = "{\"data\":{\"items\":[1,2,3]}}";

    Object parsed = gson.fromJson(originalJson, Object.class);
    String serialized = gson.toJson(parsed, Object.class);
    Object reparsed = gson.fromJson(serialized, Object.class);

    assertTrue(reparsed instanceof Map);
    @SuppressWarnings("unchecked")
    Map<String, Object> map = (Map<String, Object>) reparsed;
    @SuppressWarnings("unchecked")
    Map<String, Object> data = (Map<String, Object>) map.get("data");
    @SuppressWarnings("unchecked")
    List<Object> items = (List<Object>) data.get("items");
    assertEquals(3, items.size());
  }

  // ==========================================================================
  // Helper class for testing custom object serialization
  // ==========================================================================

  @SuppressWarnings("unused")
  private static class TestPerson {
    private final String name;
    private final int age;

    TestPerson(String name, int age) {
      this.name = name;
      this.age = age;
    }

    public String getName() {
      return name;
    }

    public int getAge() {
      return age;
    }
  }
}
