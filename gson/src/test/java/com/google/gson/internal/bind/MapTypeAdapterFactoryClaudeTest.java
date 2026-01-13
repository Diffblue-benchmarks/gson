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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import org.junit.Test;

/** Tests for {@link MapTypeAdapterFactory}. */
public class MapTypeAdapterFactoryClaudeTest {

  private final Gson gson = new Gson();
  private final Gson gsonWithComplexKeys =
      new GsonBuilder().enableComplexMapKeySerialization().create();

  // ==========================================================================
  // Constructor tests - verify factory can be instantiated
  // ==========================================================================

  @Test
  public void constructor_withValidParameters_createsFactory() {
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    MapTypeAdapterFactory factory = new MapTypeAdapterFactory(constructorConstructor, false);
    assertNotNull(factory);
  }

  @Test
  public void constructor_withComplexMapKeySerialization_createsFactory() {
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    MapTypeAdapterFactory factory = new MapTypeAdapterFactory(constructorConstructor, true);
    assertNotNull(factory);
    assertTrue(factory.complexMapKeySerialization);
  }

  @Test
  public void constructor_withoutComplexMapKeySerialization_createsFactory() {
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    MapTypeAdapterFactory factory = new MapTypeAdapterFactory(constructorConstructor, false);
    assertNotNull(factory);
    assertTrue(!factory.complexMapKeySerialization);
  }

  // ==========================================================================
  // create() tests - returns null for non-Map types
  // ==========================================================================

  @Test
  public void create_withStringType_returnsNull() {
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    MapTypeAdapterFactory factory = new MapTypeAdapterFactory(constructorConstructor, false);

    TypeAdapter<String> adapter = factory.create(gson, TypeToken.get(String.class));
    assertNull(adapter);
  }

  @Test
  public void create_withIntegerType_returnsNull() {
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    MapTypeAdapterFactory factory = new MapTypeAdapterFactory(constructorConstructor, false);

    TypeAdapter<Integer> adapter = factory.create(gson, TypeToken.get(Integer.class));
    assertNull(adapter);
  }

  @Test
  public void create_withArrayType_returnsNull() {
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    MapTypeAdapterFactory factory = new MapTypeAdapterFactory(constructorConstructor, false);

    TypeAdapter<String[]> adapter = factory.create(gson, TypeToken.get(String[].class));
    assertNull(adapter);
  }

  @Test
  public void create_withListType_returnsNull() {
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    MapTypeAdapterFactory factory = new MapTypeAdapterFactory(constructorConstructor, false);

    TypeAdapter<?> adapter = factory.create(gson, new TypeToken<List<String>>() {});
    assertNull(adapter);
  }

  @Test
  public void create_withObjectType_returnsNull() {
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    MapTypeAdapterFactory factory = new MapTypeAdapterFactory(constructorConstructor, false);

    TypeAdapter<Object> adapter = factory.create(gson, TypeToken.get(Object.class));
    assertNull(adapter);
  }

  // ==========================================================================
  // create() tests - returns adapter for Map types
  // ==========================================================================

  @Test
  public void create_withMapType_returnsAdapter() {
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    MapTypeAdapterFactory factory = new MapTypeAdapterFactory(constructorConstructor, false);

    TypeAdapter<?> adapter = factory.create(gson, new TypeToken<Map<String, String>>() {});
    assertNotNull(adapter);
  }

  @Test
  public void create_withHashMapType_returnsAdapter() {
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    MapTypeAdapterFactory factory = new MapTypeAdapterFactory(constructorConstructor, false);

    TypeAdapter<?> adapter = factory.create(gson, new TypeToken<HashMap<String, Integer>>() {});
    assertNotNull(adapter);
  }

  @Test
  public void create_withLinkedHashMapType_returnsAdapter() {
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    MapTypeAdapterFactory factory = new MapTypeAdapterFactory(constructorConstructor, false);

    TypeAdapter<?> adapter =
        factory.create(gson, new TypeToken<LinkedHashMap<String, String>>() {});
    assertNotNull(adapter);
  }

  @Test
  public void create_withTreeMapType_returnsAdapter() {
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    MapTypeAdapterFactory factory = new MapTypeAdapterFactory(constructorConstructor, false);

    TypeAdapter<?> adapter = factory.create(gson, new TypeToken<TreeMap<String, String>>() {});
    assertNotNull(adapter);
  }

  @Test
  public void create_withSortedMapType_returnsAdapter() {
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    MapTypeAdapterFactory factory = new MapTypeAdapterFactory(constructorConstructor, false);

    TypeAdapter<?> adapter = factory.create(gson, new TypeToken<SortedMap<String, String>>() {});
    assertNotNull(adapter);
  }

  @Test
  public void create_withConcurrentHashMapType_returnsAdapter() {
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    MapTypeAdapterFactory factory = new MapTypeAdapterFactory(constructorConstructor, false);

    TypeAdapter<?> adapter =
        factory.create(gson, new TypeToken<ConcurrentHashMap<String, String>>() {});
    assertNotNull(adapter);
  }

  // ==========================================================================
  // read() tests - null handling
  // ==========================================================================

  @Test
  public void read_nullJson_returnsNull() throws IOException {
    TypeAdapter<Map<String, String>> adapter =
        gson.getAdapter(new TypeToken<Map<String, String>>() {});
    JsonReader reader = new JsonReader(new StringReader("null"));
    Map<String, String> result = adapter.read(reader);
    assertNull(result);
  }

  // ==========================================================================
  // read() tests - empty maps
  // ==========================================================================

  @Test
  public void read_emptyObject_returnsEmptyMap() throws IOException {
    TypeAdapter<Map<String, String>> adapter =
        gson.getAdapter(new TypeToken<Map<String, String>>() {});
    JsonReader reader = new JsonReader(new StringReader("{}"));
    Map<String, String> result = adapter.read(reader);
    assertNotNull(result);
    assertEquals(0, result.size());
  }

  @Test
  public void read_emptyArray_returnsEmptyMap() throws IOException {
    TypeAdapter<Map<String, String>> adapter =
        gson.getAdapter(new TypeToken<Map<String, String>>() {});
    JsonReader reader = new JsonReader(new StringReader("[]"));
    Map<String, String> result = adapter.read(reader);
    assertNotNull(result);
    assertEquals(0, result.size());
  }

  // ==========================================================================
  // read() tests - Map<String, String>
  // ==========================================================================

  @Test
  public void read_stringStringMap_returnsCorrectMap() throws IOException {
    TypeAdapter<Map<String, String>> adapter =
        gson.getAdapter(new TypeToken<Map<String, String>>() {});
    JsonReader reader = new JsonReader(new StringReader("{\"key1\":\"value1\",\"key2\":\"value2\"}"));
    Map<String, String> result = adapter.read(reader);
    assertEquals(2, result.size());
    assertEquals("value1", result.get("key1"));
    assertEquals("value2", result.get("key2"));
  }

  @Test
  public void read_stringStringMapWithNullValue_handlesNullValue() throws IOException {
    TypeAdapter<Map<String, String>> adapter =
        gson.getAdapter(new TypeToken<Map<String, String>>() {});
    JsonReader reader = new JsonReader(new StringReader("{\"key1\":\"value1\",\"key2\":null}"));
    Map<String, String> result = adapter.read(reader);
    assertEquals(2, result.size());
    assertEquals("value1", result.get("key1"));
    assertNull(result.get("key2"));
  }

  @Test
  public void read_singleEntryMap_returnsCorrectMap() throws IOException {
    TypeAdapter<Map<String, String>> adapter =
        gson.getAdapter(new TypeToken<Map<String, String>>() {});
    JsonReader reader = new JsonReader(new StringReader("{\"only\":\"entry\"}"));
    Map<String, String> result = adapter.read(reader);
    assertEquals(1, result.size());
    assertEquals("entry", result.get("only"));
  }

  // ==========================================================================
  // read() tests - Map<String, Integer>
  // ==========================================================================

  @Test
  public void read_stringIntegerMap_returnsCorrectMap() throws IOException {
    TypeAdapter<Map<String, Integer>> adapter =
        gson.getAdapter(new TypeToken<Map<String, Integer>>() {});
    JsonReader reader = new JsonReader(new StringReader("{\"a\":1,\"b\":2,\"c\":3}"));
    Map<String, Integer> result = adapter.read(reader);
    assertEquals(3, result.size());
    assertEquals(Integer.valueOf(1), result.get("a"));
    assertEquals(Integer.valueOf(2), result.get("b"));
    assertEquals(Integer.valueOf(3), result.get("c"));
  }

  // ==========================================================================
  // read() tests - Map<Integer, String> (numeric keys)
  // ==========================================================================

  @Test
  public void read_integerStringMap_handlesNumericKeys() throws IOException {
    TypeAdapter<Map<Integer, String>> adapter =
        gson.getAdapter(new TypeToken<Map<Integer, String>>() {});
    JsonReader reader = new JsonReader(new StringReader("{\"1\":\"one\",\"2\":\"two\"}"));
    Map<Integer, String> result = adapter.read(reader);
    assertEquals(2, result.size());
    assertEquals("one", result.get(1));
    assertEquals("two", result.get(2));
  }

  // ==========================================================================
  // read() tests - Map<Boolean, String> (boolean keys)
  // ==========================================================================

  @Test
  public void read_booleanStringMap_handlesBooleanKeys() throws IOException {
    TypeAdapter<Map<Boolean, String>> adapter =
        gson.getAdapter(new TypeToken<Map<Boolean, String>>() {});
    JsonReader reader = new JsonReader(new StringReader("{\"true\":\"yes\",\"false\":\"no\"}"));
    Map<Boolean, String> result = adapter.read(reader);
    assertEquals(2, result.size());
    assertEquals("yes", result.get(true));
    assertEquals("no", result.get(false));
  }

  // ==========================================================================
  // read() tests - LinkedHashMap (maintains insertion order)
  // ==========================================================================

  @Test
  public void read_linkedHashMap_maintainsOrder() throws IOException {
    TypeAdapter<LinkedHashMap<String, String>> adapter =
        gson.getAdapter(new TypeToken<LinkedHashMap<String, String>>() {});
    JsonReader reader =
        new JsonReader(new StringReader("{\"first\":\"1\",\"second\":\"2\",\"third\":\"3\"}"));
    LinkedHashMap<String, String> result = adapter.read(reader);
    assertEquals(3, result.size());

    // Verify order is maintained
    String[] keys = result.keySet().toArray(new String[0]);
    assertEquals("first", keys[0]);
    assertEquals("second", keys[1]);
    assertEquals("third", keys[2]);
  }

  // ==========================================================================
  // read() tests - TreeMap (sorted)
  // ==========================================================================

  @Test
  public void read_treeMap_sortedOrder() throws IOException {
    TypeAdapter<TreeMap<String, String>> adapter =
        gson.getAdapter(new TypeToken<TreeMap<String, String>>() {});
    JsonReader reader =
        new JsonReader(new StringReader("{\"c\":\"3\",\"a\":\"1\",\"b\":\"2\"}"));
    TreeMap<String, String> result = adapter.read(reader);
    assertEquals(3, result.size());

    // Verify sorted order
    String[] keys = result.keySet().toArray(new String[0]);
    assertEquals("a", keys[0]);
    assertEquals("b", keys[1]);
    assertEquals("c", keys[2]);
  }

  // ==========================================================================
  // read() tests - duplicate key detection
  // ==========================================================================

  @Test
  public void read_duplicateKeysInObject_throwsJsonSyntaxException() throws IOException {
    TypeAdapter<Map<String, String>> adapter =
        gson.getAdapter(new TypeToken<Map<String, String>>() {});
    JsonReader reader = new JsonReader(new StringReader("{\"key\":\"value1\",\"key\":\"value2\"}"));
    try {
      adapter.read(reader);
      fail("Expected JsonSyntaxException for duplicate keys");
    } catch (JsonSyntaxException e) {
      assertTrue(e.getMessage().contains("duplicate key"));
    }
  }

  @Test
  public void read_duplicateKeysInArray_throwsJsonSyntaxException() throws IOException {
    TypeAdapter<Map<String, String>> adapter =
        gson.getAdapter(new TypeToken<Map<String, String>>() {});
    JsonReader reader =
        new JsonReader(new StringReader("[[\"key\",\"value1\"],[\"key\",\"value2\"]]"));
    try {
      adapter.read(reader);
      fail("Expected JsonSyntaxException for duplicate keys");
    } catch (JsonSyntaxException e) {
      assertTrue(e.getMessage().contains("duplicate key"));
    }
  }

  // ==========================================================================
  // read() tests - array format (for complex keys)
  // ==========================================================================

  @Test
  public void read_arrayFormat_parsesCorrectly() throws IOException {
    TypeAdapter<Map<String, String>> adapter =
        gson.getAdapter(new TypeToken<Map<String, String>>() {});
    JsonReader reader =
        new JsonReader(new StringReader("[[\"key1\",\"value1\"],[\"key2\",\"value2\"]]"));
    Map<String, String> result = adapter.read(reader);
    assertEquals(2, result.size());
    assertEquals("value1", result.get("key1"));
    assertEquals("value2", result.get("key2"));
  }

  // ==========================================================================
  // read() tests - nested maps
  // ==========================================================================

  @Test
  public void read_nestedMap_returnsCorrectNestedMap() throws IOException {
    TypeAdapter<Map<String, Map<String, String>>> adapter =
        gson.getAdapter(new TypeToken<Map<String, Map<String, String>>>() {});
    JsonReader reader =
        new JsonReader(new StringReader("{\"outer1\":{\"inner1\":\"value1\"},\"outer2\":{\"inner2\":\"value2\"}}"));
    Map<String, Map<String, String>> result = adapter.read(reader);
    assertEquals(2, result.size());
    assertEquals("value1", result.get("outer1").get("inner1"));
    assertEquals("value2", result.get("outer2").get("inner2"));
  }

  // ==========================================================================
  // read() tests - Map with custom object values
  // ==========================================================================

  @Test
  public void read_mapWithCustomObjectValues_parsesCorrectly() throws IOException {
    TypeAdapter<Map<String, Person>> adapter =
        gson.getAdapter(new TypeToken<Map<String, Person>>() {});
    JsonReader reader =
        new JsonReader(new StringReader("{\"alice\":{\"name\":\"Alice\",\"age\":25}}"));
    Map<String, Person> result = adapter.read(reader);
    assertEquals(1, result.size());
    assertEquals("Alice", result.get("alice").name);
    assertEquals(25, result.get("alice").age);
  }

  // ==========================================================================
  // write() tests - null handling
  // ==========================================================================

  @Test
  public void write_nullMap_writesNull() throws IOException {
    TypeAdapter<Map<String, String>> adapter =
        gson.getAdapter(new TypeToken<Map<String, String>>() {});
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, null);
    assertEquals("null", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - empty maps
  // ==========================================================================

  @Test
  public void write_emptyMap_writesEmptyObject() throws IOException {
    TypeAdapter<Map<String, String>> adapter =
        gson.getAdapter(new TypeToken<Map<String, String>>() {});
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, new HashMap<>());
    assertEquals("{}", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - Map<String, String>
  // ==========================================================================

  @Test
  public void write_stringStringMap_writesCorrectJson() throws IOException {
    TypeAdapter<Map<String, String>> adapter =
        gson.getAdapter(new TypeToken<Map<String, String>>() {});
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    Map<String, String> map = new LinkedHashMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    adapter.write(writer, map);
    assertEquals("{\"key1\":\"value1\",\"key2\":\"value2\"}", stringWriter.toString());
  }

  @Test
  public void write_stringStringMapWithNullValue_handlesNullValue() throws IOException {
    TypeAdapter<Map<String, String>> adapter =
        gson.getAdapter(new TypeToken<Map<String, String>>() {});
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    writer.setSerializeNulls(true);
    Map<String, String> map = new LinkedHashMap<>();
    map.put("key1", "value1");
    map.put("key2", null);
    adapter.write(writer, map);
    assertEquals("{\"key1\":\"value1\",\"key2\":null}", stringWriter.toString());
  }

  @Test
  public void write_stringStringMapWithNullKey_handlesNullKey() throws IOException {
    TypeAdapter<Map<String, String>> adapter =
        gson.getAdapter(new TypeToken<Map<String, String>>() {});
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    Map<String, String> map = new LinkedHashMap<>();
    map.put(null, "value");
    adapter.write(writer, map);
    assertEquals("{\"null\":\"value\"}", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - Map<String, Integer>
  // ==========================================================================

  @Test
  public void write_stringIntegerMap_writesCorrectJson() throws IOException {
    TypeAdapter<Map<String, Integer>> adapter =
        gson.getAdapter(new TypeToken<Map<String, Integer>>() {});
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    Map<String, Integer> map = new LinkedHashMap<>();
    map.put("a", 1);
    map.put("b", 2);
    adapter.write(writer, map);
    assertEquals("{\"a\":1,\"b\":2}", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - Map<Integer, String> (numeric keys)
  // ==========================================================================

  @Test
  public void write_integerStringMap_writesNumericKeysAsStrings() throws IOException {
    TypeAdapter<Map<Integer, String>> adapter =
        gson.getAdapter(new TypeToken<Map<Integer, String>>() {});
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    Map<Integer, String> map = new LinkedHashMap<>();
    map.put(1, "one");
    map.put(2, "two");
    adapter.write(writer, map);
    assertEquals("{\"1\":\"one\",\"2\":\"two\"}", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - Map<Boolean, String> (boolean keys)
  // ==========================================================================

  @Test
  public void write_booleanStringMap_writesBooleanKeysAsStrings() throws IOException {
    TypeAdapter<Map<Boolean, String>> adapter =
        gson.getAdapter(new TypeToken<Map<Boolean, String>>() {});
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    Map<Boolean, String> map = new LinkedHashMap<>();
    map.put(true, "yes");
    map.put(false, "no");
    adapter.write(writer, map);
    assertEquals("{\"true\":\"yes\",\"false\":\"no\"}", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - LinkedHashMap (maintains insertion order)
  // ==========================================================================

  @Test
  public void write_linkedHashMap_maintainsOrder() throws IOException {
    TypeAdapter<LinkedHashMap<String, String>> adapter =
        gson.getAdapter(new TypeToken<LinkedHashMap<String, String>>() {});
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    LinkedHashMap<String, String> map = new LinkedHashMap<>();
    map.put("first", "1");
    map.put("second", "2");
    map.put("third", "3");
    adapter.write(writer, map);
    assertEquals("{\"first\":\"1\",\"second\":\"2\",\"third\":\"3\"}", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - nested maps
  // ==========================================================================

  @Test
  public void write_nestedMap_writesCorrectJson() throws IOException {
    TypeAdapter<Map<String, Map<String, String>>> adapter =
        gson.getAdapter(new TypeToken<Map<String, Map<String, String>>>() {});
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    Map<String, Map<String, String>> outer = new LinkedHashMap<>();
    Map<String, String> inner = new LinkedHashMap<>();
    inner.put("inner", "value");
    outer.put("outer", inner);
    adapter.write(writer, outer);
    assertEquals("{\"outer\":{\"inner\":\"value\"}}", stringWriter.toString());
  }

  // ==========================================================================
  // Complex key serialization tests (with enableComplexMapKeySerialization)
  // ==========================================================================

  @Test
  public void write_complexKeys_withComplexKeySerialization_writesArrayFormat() {
    Map<Point, String> map = new LinkedHashMap<>();
    map.put(new Point(1, 2), "a");
    map.put(new Point(3, 4), "b");
    String json = gsonWithComplexKeys.toJson(map, new TypeToken<Map<Point, String>>() {}.getType());
    // Complex keys result in array format: [[{key}, value], ...]
    assertTrue(json.startsWith("["));
    assertTrue(json.endsWith("]"));
    assertTrue(json.contains("\"x\":1"));
    assertTrue(json.contains("\"y\":2"));
  }

  @Test
  public void write_simpleKeys_withComplexKeySerialization_writesObjectFormat() {
    Map<String, String> map = new LinkedHashMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    String json =
        gsonWithComplexKeys.toJson(map, new TypeToken<Map<String, String>>() {}.getType());
    assertEquals("{\"key1\":\"value1\",\"key2\":\"value2\"}", json);
  }

  @Test
  public void read_arrayFormatComplexKeys_parsesCorrectly() {
    String json = "[[{\"x\":1,\"y\":2},\"a\"],[{\"x\":3,\"y\":4},\"b\"]]";
    Map<Point, String> result =
        gsonWithComplexKeys.fromJson(json, new TypeToken<Map<Point, String>>() {}.getType());
    assertEquals(2, result.size());
    assertEquals("a", result.get(new Point(1, 2)));
    assertEquals("b", result.get(new Point(3, 4)));
  }

  @Test
  public void write_numericKeys_withComplexKeySerialization_writesObjectFormat() {
    Map<Integer, String> map = new LinkedHashMap<>();
    map.put(1, "one");
    map.put(2, "two");
    String json =
        gsonWithComplexKeys.toJson(map, new TypeToken<Map<Integer, String>>() {}.getType());
    assertEquals("{\"1\":\"one\",\"2\":\"two\"}", json);
  }

  @Test
  public void write_booleanKeys_withComplexKeySerialization_writesObjectFormat() {
    Map<Boolean, String> map = new LinkedHashMap<>();
    map.put(true, "yes");
    map.put(false, "no");
    String json =
        gsonWithComplexKeys.toJson(map, new TypeToken<Map<Boolean, String>>() {}.getType());
    assertEquals("{\"true\":\"yes\",\"false\":\"no\"}", json);
  }

  @Test
  public void write_nullKey_withComplexKeySerialization_writesObjectFormat() {
    Map<String, String> map = new LinkedHashMap<>();
    map.put(null, "nullValue");
    String json =
        gsonWithComplexKeys.toJson(map, new TypeToken<Map<String, String>>() {}.getType());
    assertEquals("{\"null\":\"nullValue\"}", json);
  }

  // ==========================================================================
  // Round-trip tests
  // ==========================================================================

  @Test
  public void roundTrip_stringStringMap_preservesData() {
    Map<String, String> original = new LinkedHashMap<>();
    original.put("key1", "value1");
    original.put("key2", "value2");
    String json = gson.toJson(original, new TypeToken<Map<String, String>>() {}.getType());
    Map<String, String> result =
        gson.fromJson(json, new TypeToken<Map<String, String>>() {}.getType());
    assertEquals(original, result);
  }

  @Test
  public void roundTrip_integerStringMap_preservesData() {
    Map<Integer, String> original = new LinkedHashMap<>();
    original.put(1, "one");
    original.put(2, "two");
    String json = gson.toJson(original, new TypeToken<Map<Integer, String>>() {}.getType());
    Map<Integer, String> result =
        gson.fromJson(json, new TypeToken<Map<Integer, String>>() {}.getType());
    assertEquals(original, result);
  }

  @Test
  public void roundTrip_complexKeys_preservesData() {
    Map<Point, String> original = new LinkedHashMap<>();
    original.put(new Point(1, 2), "a");
    original.put(new Point(3, 4), "b");
    String json =
        gsonWithComplexKeys.toJson(original, new TypeToken<Map<Point, String>>() {}.getType());
    Map<Point, String> result =
        gsonWithComplexKeys.fromJson(json, new TypeToken<Map<Point, String>>() {}.getType());
    assertEquals(original, result);
  }

  @Test
  public void roundTrip_nestedMap_preservesData() {
    Map<String, Map<String, Integer>> original = new LinkedHashMap<>();
    Map<String, Integer> inner = new LinkedHashMap<>();
    inner.put("a", 1);
    inner.put("b", 2);
    original.put("inner", inner);
    String json =
        gson.toJson(original, new TypeToken<Map<String, Map<String, Integer>>>() {}.getType());
    Map<String, Map<String, Integer>> result =
        gson.fromJson(json, new TypeToken<Map<String, Map<String, Integer>>>() {}.getType());
    assertEquals(original, result);
  }

  // ==========================================================================
  // Edge case tests
  // ==========================================================================

  @Test
  public void read_largeMap_handlesCorrectly() {
    StringBuilder json = new StringBuilder("{");
    for (int i = 0; i < 100; i++) {
      if (i > 0) json.append(",");
      json.append("\"key").append(i).append("\":").append(i);
    }
    json.append("}");

    Map<String, Integer> result =
        gson.fromJson(json.toString(), new TypeToken<Map<String, Integer>>() {}.getType());
    assertEquals(100, result.size());
    for (int i = 0; i < 100; i++) {
      assertEquals(Integer.valueOf(i), result.get("key" + i));
    }
  }

  @Test
  public void write_largeMap_handlesCorrectly() {
    Map<String, Integer> map = new LinkedHashMap<>();
    for (int i = 0; i < 100; i++) {
      map.put("key" + i, i);
    }
    String json = gson.toJson(map, new TypeToken<Map<String, Integer>>() {}.getType());
    assertTrue(json.startsWith("{\"key0\":0"));
    assertTrue(json.endsWith("\"key99\":99}"));
  }

  @Test
  public void read_stringMapWithSpecialChars_handlesCorrectly() {
    String json = "{\"key\\nwith\\nnewlines\":\"value\\twith\\ttabs\"}";
    Map<String, String> result =
        gson.fromJson(json, new TypeToken<Map<String, String>>() {}.getType());
    assertEquals(1, result.size());
    assertEquals("value\twith\ttabs", result.get("key\nwith\nnewlines"));
  }

  @Test
  public void write_stringMapWithSpecialChars_escapesCorrectly() {
    Map<String, String> map = new LinkedHashMap<>();
    map.put("key\nwith\nnewlines", "value\twith\ttabs");
    String json = gson.toJson(map, new TypeToken<Map<String, String>>() {}.getType());
    assertTrue(json.contains("\\n"));
    assertTrue(json.contains("\\t"));
  }

  // ==========================================================================
  // Raw type tests
  // ==========================================================================

  @Test
  @SuppressWarnings({"rawtypes", "unchecked"})
  public void read_rawMapType_treatsAsObjectObject() {
    String json = "{\"key\":\"value\"}";
    Map result = gson.fromJson(json, Map.class);
    assertEquals(1, result.size());
    assertEquals("value", result.get("key"));
  }

  @Test
  @SuppressWarnings({"rawtypes", "unchecked"})
  public void write_rawMapType_writesCorrectly() {
    Map map = new LinkedHashMap();
    map.put("key", "value");
    map.put("number", 42);
    String json = gson.toJson(map);
    assertTrue(json.contains("\"key\":\"value\""));
    assertTrue(json.contains("\"number\":42"));
  }

  // ==========================================================================
  // Map interface tests (various implementations)
  // ==========================================================================

  @Test
  public void read_mapInterface_createsMapImplementation() {
    String json = "{\"a\":\"1\",\"b\":\"2\"}";
    Map<String, String> result =
        gson.fromJson(json, new TypeToken<Map<String, String>>() {}.getType());
    // Default Map implementation is Gson's LinkedTreeMap
    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals("1", result.get("a"));
    assertEquals("2", result.get("b"));
  }

  @Test
  public void read_hashMap_returnsHashMap() {
    String json = "{\"a\":\"1\",\"b\":\"2\"}";
    HashMap<String, String> result =
        gson.fromJson(json, new TypeToken<HashMap<String, String>>() {}.getType());
    assertTrue(result instanceof HashMap);
    assertEquals(2, result.size());
  }

  // ==========================================================================
  // Map with List values
  // ==========================================================================

  @Test
  public void read_mapWithListValues_parsesCorrectly() {
    String json = "{\"numbers\":[1,2,3],\"letters\":[\"a\",\"b\"]}";
    Map<String, List<Object>> result =
        gson.fromJson(json, new TypeToken<Map<String, List<Object>>>() {}.getType());
    assertEquals(2, result.size());
    assertEquals(3, result.get("numbers").size());
    assertEquals(2, result.get("letters").size());
  }

  @Test
  public void write_mapWithListValues_writesCorrectJson() {
    Map<String, List<Integer>> map = new LinkedHashMap<>();
    map.put("numbers", java.util.Arrays.asList(1, 2, 3));
    String json = gson.toJson(map, new TypeToken<Map<String, List<Integer>>>() {}.getType());
    assertEquals("{\"numbers\":[1,2,3]}", json);
  }

  // ==========================================================================
  // Double and Long key tests
  // ==========================================================================

  @Test
  public void write_doubleKeys_writesAsStrings() {
    Map<Double, String> map = new LinkedHashMap<>();
    map.put(1.5, "one point five");
    map.put(2.5, "two point five");
    String json = gson.toJson(map, new TypeToken<Map<Double, String>>() {}.getType());
    assertEquals("{\"1.5\":\"one point five\",\"2.5\":\"two point five\"}", json);
  }

  @Test
  public void read_doubleKeys_parsesCorrectly() {
    String json = "{\"1.5\":\"one point five\"}";
    Map<Double, String> result =
        gson.fromJson(json, new TypeToken<Map<Double, String>>() {}.getType());
    assertEquals(1, result.size());
    assertEquals("one point five", result.get(1.5));
  }

  @Test
  public void write_longKeys_writesAsStrings() {
    Map<Long, String> map = new LinkedHashMap<>();
    map.put(100L, "hundred");
    map.put(200L, "two hundred");
    String json = gson.toJson(map, new TypeToken<Map<Long, String>>() {}.getType());
    assertEquals("{\"100\":\"hundred\",\"200\":\"two hundred\"}", json);
  }

  @Test
  public void read_longKeys_parsesCorrectly() {
    String json = "{\"100\":\"hundred\"}";
    Map<Long, String> result =
        gson.fromJson(json, new TypeToken<Map<Long, String>>() {}.getType());
    assertEquals(1, result.size());
    assertEquals("hundred", result.get(100L));
  }

  // ==========================================================================
  // Array key tests (complex key serialization)
  // ==========================================================================

  @Test
  public void write_arrayKeys_withComplexKeySerialization_writesArrayFormat() {
    Map<int[], String> map = new LinkedHashMap<>();
    map.put(new int[] {1, 2}, "value");
    String json = gsonWithComplexKeys.toJson(map, new TypeToken<Map<int[], String>>() {}.getType());
    // Array keys are complex, so should use array format
    assertTrue(json.startsWith("["));
    assertTrue(json.contains("[1,2]"));
  }

  // Helper classes for custom object tests
  private static class Person {
    String name;
    int age;

    @SuppressWarnings("unused")
    Person() {} // For Gson deserialization

    Person(String name, int age) {
      this.name = name;
      this.age = age;
    }
  }

  private static class Point {
    int x;
    int y;

    @SuppressWarnings("unused")
    Point() {} // For Gson deserialization

    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Point point = (Point) o;
      return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
      return 31 * x + y;
    }
  }
}
