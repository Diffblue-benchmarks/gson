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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import org.junit.Test;

/** Tests for {@link CollectionTypeAdapterFactory}. */
public class CollectionTypeAdapterFactoryClaudeTest {

  private final Gson gson = new Gson();

  // ==========================================================================
  // Constructor tests - verify factory can be instantiated
  // ==========================================================================

  @Test
  public void constructor_withValidConstructorConstructor_createsFactory() {
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    CollectionTypeAdapterFactory factory = new CollectionTypeAdapterFactory(constructorConstructor);
    assertNotNull(factory);
  }

  // ==========================================================================
  // create() tests - returns null for non-Collection types
  // ==========================================================================

  @Test
  public void create_withStringType_returnsNull() {
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    CollectionTypeAdapterFactory factory = new CollectionTypeAdapterFactory(constructorConstructor);

    TypeAdapter<String> adapter = factory.create(gson, TypeToken.get(String.class));
    assertNull(adapter);
  }

  @Test
  public void create_withIntegerType_returnsNull() {
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    CollectionTypeAdapterFactory factory = new CollectionTypeAdapterFactory(constructorConstructor);

    TypeAdapter<Integer> adapter = factory.create(gson, TypeToken.get(Integer.class));
    assertNull(adapter);
  }

  @Test
  public void create_withArrayType_returnsNull() {
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    CollectionTypeAdapterFactory factory = new CollectionTypeAdapterFactory(constructorConstructor);

    TypeAdapter<String[]> adapter = factory.create(gson, TypeToken.get(String[].class));
    assertNull(adapter);
  }

  @Test
  public void create_withObjectType_returnsNull() {
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    CollectionTypeAdapterFactory factory = new CollectionTypeAdapterFactory(constructorConstructor);

    TypeAdapter<Object> adapter = factory.create(gson, TypeToken.get(Object.class));
    assertNull(adapter);
  }

  @Test
  public void create_withMapType_returnsNull() {
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    CollectionTypeAdapterFactory factory = new CollectionTypeAdapterFactory(constructorConstructor);

    TypeAdapter<?> adapter =
        factory.create(gson, new TypeToken<java.util.Map<String, String>>() {});
    assertNull(adapter);
  }

  // ==========================================================================
  // create() tests - returns adapter for Collection types
  // ==========================================================================

  @Test
  public void create_withListType_returnsAdapter() {
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    CollectionTypeAdapterFactory factory = new CollectionTypeAdapterFactory(constructorConstructor);

    TypeAdapter<?> adapter = factory.create(gson, new TypeToken<List<String>>() {});
    assertNotNull(adapter);
  }

  @Test
  public void create_withSetType_returnsAdapter() {
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    CollectionTypeAdapterFactory factory = new CollectionTypeAdapterFactory(constructorConstructor);

    TypeAdapter<?> adapter = factory.create(gson, new TypeToken<Set<String>>() {});
    assertNotNull(adapter);
  }

  @Test
  public void create_withCollectionType_returnsAdapter() {
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    CollectionTypeAdapterFactory factory = new CollectionTypeAdapterFactory(constructorConstructor);

    TypeAdapter<?> adapter = factory.create(gson, new TypeToken<Collection<String>>() {});
    assertNotNull(adapter);
  }

  @Test
  public void create_withQueueType_returnsAdapter() {
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    CollectionTypeAdapterFactory factory = new CollectionTypeAdapterFactory(constructorConstructor);

    TypeAdapter<?> adapter = factory.create(gson, new TypeToken<Queue<Integer>>() {});
    assertNotNull(adapter);
  }

  @Test
  public void create_withDequeType_returnsAdapter() {
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    CollectionTypeAdapterFactory factory = new CollectionTypeAdapterFactory(constructorConstructor);

    TypeAdapter<?> adapter = factory.create(gson, new TypeToken<Deque<String>>() {});
    assertNotNull(adapter);
  }

  // ==========================================================================
  // read() tests - via Gson (null handling)
  // ==========================================================================

  @Test
  public void read_nullJson_returnsNull() throws IOException {
    TypeAdapter<List<String>> adapter = gson.getAdapter(new TypeToken<List<String>>() {});
    JsonReader reader = new JsonReader(new StringReader("null"));
    List<String> result = adapter.read(reader);
    assertNull(result);
  }

  @Test
  public void read_nullJsonForSet_returnsNull() throws IOException {
    TypeAdapter<Set<Integer>> adapter = gson.getAdapter(new TypeToken<Set<Integer>>() {});
    JsonReader reader = new JsonReader(new StringReader("null"));
    Set<Integer> result = adapter.read(reader);
    assertNull(result);
  }

  // ==========================================================================
  // read() tests - empty collections
  // ==========================================================================

  @Test
  public void read_emptyArray_returnsEmptyList() throws IOException {
    TypeAdapter<List<String>> adapter = gson.getAdapter(new TypeToken<List<String>>() {});
    JsonReader reader = new JsonReader(new StringReader("[]"));
    List<String> result = adapter.read(reader);
    assertNotNull(result);
    assertEquals(0, result.size());
  }

  @Test
  public void read_emptyArray_returnsEmptySet() throws IOException {
    TypeAdapter<Set<String>> adapter = gson.getAdapter(new TypeToken<Set<String>>() {});
    JsonReader reader = new JsonReader(new StringReader("[]"));
    Set<String> result = adapter.read(reader);
    assertNotNull(result);
    assertEquals(0, result.size());
  }

  // ==========================================================================
  // read() tests - List<String>
  // ==========================================================================

  @Test
  public void read_stringList_returnsCorrectList() throws IOException {
    TypeAdapter<List<String>> adapter = gson.getAdapter(new TypeToken<List<String>>() {});
    JsonReader reader = new JsonReader(new StringReader("[\"a\", \"b\", \"c\"]"));
    List<String> result = adapter.read(reader);
    assertEquals(3, result.size());
    assertEquals("a", result.get(0));
    assertEquals("b", result.get(1));
    assertEquals("c", result.get(2));
  }

  @Test
  public void read_stringListWithNull_handlesNullElement() throws IOException {
    TypeAdapter<List<String>> adapter = gson.getAdapter(new TypeToken<List<String>>() {});
    JsonReader reader = new JsonReader(new StringReader("[\"a\", null, \"c\"]"));
    List<String> result = adapter.read(reader);
    assertEquals(3, result.size());
    assertEquals("a", result.get(0));
    assertNull(result.get(1));
    assertEquals("c", result.get(2));
  }

  @Test
  public void read_singleElementList_returnsCorrectList() throws IOException {
    TypeAdapter<List<String>> adapter = gson.getAdapter(new TypeToken<List<String>>() {});
    JsonReader reader = new JsonReader(new StringReader("[\"only\"]"));
    List<String> result = adapter.read(reader);
    assertEquals(1, result.size());
    assertEquals("only", result.get(0));
  }

  // ==========================================================================
  // read() tests - List<Integer>
  // ==========================================================================

  @Test
  public void read_integerList_returnsCorrectList() throws IOException {
    TypeAdapter<List<Integer>> adapter = gson.getAdapter(new TypeToken<List<Integer>>() {});
    JsonReader reader = new JsonReader(new StringReader("[1, 2, 3, 4, 5]"));
    List<Integer> result = adapter.read(reader);
    assertEquals(5, result.size());
    assertEquals(Integer.valueOf(1), result.get(0));
    assertEquals(Integer.valueOf(5), result.get(4));
  }

  @Test
  public void read_integerListWithNegatives_returnsCorrectList() throws IOException {
    TypeAdapter<List<Integer>> adapter = gson.getAdapter(new TypeToken<List<Integer>>() {});
    JsonReader reader = new JsonReader(new StringReader("[-1, 0, 1]"));
    List<Integer> result = adapter.read(reader);
    assertEquals(3, result.size());
    assertEquals(Integer.valueOf(-1), result.get(0));
    assertEquals(Integer.valueOf(0), result.get(1));
    assertEquals(Integer.valueOf(1), result.get(2));
  }

  // ==========================================================================
  // read() tests - Set<String>
  // ==========================================================================

  @Test
  public void read_stringSet_returnsCorrectSet() throws IOException {
    TypeAdapter<Set<String>> adapter = gson.getAdapter(new TypeToken<Set<String>>() {});
    JsonReader reader = new JsonReader(new StringReader("[\"a\", \"b\", \"c\"]"));
    Set<String> result = adapter.read(reader);
    assertEquals(3, result.size());
    assertTrue(result.contains("a"));
    assertTrue(result.contains("b"));
    assertTrue(result.contains("c"));
  }

  @Test
  public void read_stringSetWithDuplicates_removesduplicates() throws IOException {
    TypeAdapter<Set<String>> adapter = gson.getAdapter(new TypeToken<Set<String>>() {});
    JsonReader reader = new JsonReader(new StringReader("[\"a\", \"a\", \"b\"]"));
    Set<String> result = adapter.read(reader);
    assertEquals(2, result.size());
    assertTrue(result.contains("a"));
    assertTrue(result.contains("b"));
  }

  // ==========================================================================
  // read() tests - LinkedHashSet (maintains insertion order)
  // ==========================================================================

  @Test
  public void read_linkedHashSet_maintainsOrder() throws IOException {
    TypeAdapter<LinkedHashSet<String>> adapter =
        gson.getAdapter(new TypeToken<LinkedHashSet<String>>() {});
    JsonReader reader = new JsonReader(new StringReader("[\"first\", \"second\", \"third\"]"));
    LinkedHashSet<String> result = adapter.read(reader);
    assertEquals(3, result.size());
    List<String> asList = new ArrayList<>(result);
    assertEquals("first", asList.get(0));
    assertEquals("second", asList.get(1));
    assertEquals("third", asList.get(2));
  }

  // ==========================================================================
  // read() tests - TreeSet (sorted)
  // ==========================================================================

  @Test
  public void read_treeSet_sortedOrder() throws IOException {
    TypeAdapter<TreeSet<Integer>> adapter = gson.getAdapter(new TypeToken<TreeSet<Integer>>() {});
    JsonReader reader = new JsonReader(new StringReader("[3, 1, 4, 1, 5]"));
    TreeSet<Integer> result = adapter.read(reader);
    assertEquals(4, result.size()); // duplicates removed
    assertEquals(Integer.valueOf(1), result.first());
    assertEquals(Integer.valueOf(5), result.last());
  }

  // ==========================================================================
  // read() tests - ArrayList
  // ==========================================================================

  @Test
  public void read_arrayList_returnsCorrectList() throws IOException {
    TypeAdapter<ArrayList<String>> adapter = gson.getAdapter(new TypeToken<ArrayList<String>>() {});
    JsonReader reader = new JsonReader(new StringReader("[\"x\", \"y\", \"z\"]"));
    ArrayList<String> result = adapter.read(reader);
    assertEquals(3, result.size());
    assertEquals("x", result.get(0));
    assertEquals("y", result.get(1));
    assertEquals("z", result.get(2));
  }

  // ==========================================================================
  // read() tests - LinkedList
  // ==========================================================================

  @Test
  public void read_linkedList_returnsCorrectList() throws IOException {
    TypeAdapter<LinkedList<Integer>> adapter =
        gson.getAdapter(new TypeToken<LinkedList<Integer>>() {});
    JsonReader reader = new JsonReader(new StringReader("[10, 20, 30]"));
    LinkedList<Integer> result = adapter.read(reader);
    assertEquals(3, result.size());
    assertEquals(Integer.valueOf(10), result.getFirst());
    assertEquals(Integer.valueOf(30), result.getLast());
  }

  // ==========================================================================
  // read() tests - Queue / Deque
  // ==========================================================================

  @Test
  public void read_queue_returnsCorrectQueue() throws IOException {
    TypeAdapter<Queue<String>> adapter = gson.getAdapter(new TypeToken<Queue<String>>() {});
    JsonReader reader = new JsonReader(new StringReader("[\"first\", \"second\"]"));
    Queue<String> result = adapter.read(reader);
    assertEquals(2, result.size());
    assertEquals("first", result.peek());
  }

  @Test
  public void read_deque_returnsCorrectDeque() throws IOException {
    TypeAdapter<Deque<String>> adapter = gson.getAdapter(new TypeToken<Deque<String>>() {});
    JsonReader reader = new JsonReader(new StringReader("[\"head\", \"tail\"]"));
    Deque<String> result = adapter.read(reader);
    assertEquals(2, result.size());
    assertEquals("head", result.peekFirst());
    assertEquals("tail", result.peekLast());
  }

  @Test
  public void read_arrayDeque_returnsCorrectDeque() throws IOException {
    TypeAdapter<ArrayDeque<Integer>> adapter =
        gson.getAdapter(new TypeToken<ArrayDeque<Integer>>() {});
    JsonReader reader = new JsonReader(new StringReader("[1, 2, 3]"));
    ArrayDeque<Integer> result = adapter.read(reader);
    assertEquals(3, result.size());
    assertEquals(Integer.valueOf(1), result.peekFirst());
    assertEquals(Integer.valueOf(3), result.peekLast());
  }

  // ==========================================================================
  // read() tests - List<Double> and List<Boolean>
  // ==========================================================================

  @Test
  public void read_doubleList_returnsCorrectList() throws IOException {
    TypeAdapter<List<Double>> adapter = gson.getAdapter(new TypeToken<List<Double>>() {});
    JsonReader reader = new JsonReader(new StringReader("[1.1, 2.2, 3.3]"));
    List<Double> result = adapter.read(reader);
    assertEquals(3, result.size());
    assertEquals(1.1, result.get(0), 0.001);
    assertEquals(2.2, result.get(1), 0.001);
    assertEquals(3.3, result.get(2), 0.001);
  }

  @Test
  public void read_booleanList_returnsCorrectList() throws IOException {
    TypeAdapter<List<Boolean>> adapter = gson.getAdapter(new TypeToken<List<Boolean>>() {});
    JsonReader reader = new JsonReader(new StringReader("[true, false, true]"));
    List<Boolean> result = adapter.read(reader);
    assertEquals(3, result.size());
    assertEquals(Boolean.TRUE, result.get(0));
    assertEquals(Boolean.FALSE, result.get(1));
    assertEquals(Boolean.TRUE, result.get(2));
  }

  // ==========================================================================
  // read() tests - Nested collections (List<List<String>>)
  // ==========================================================================

  @Test
  public void read_nestedList_returnsCorrectNestedList() throws IOException {
    TypeAdapter<List<List<String>>> adapter =
        gson.getAdapter(new TypeToken<List<List<String>>>() {});
    JsonReader reader =
        new JsonReader(new StringReader("[[\"a\", \"b\"], [\"c\", \"d\"], [\"e\"]]"));
    List<List<String>> result = adapter.read(reader);
    assertEquals(3, result.size());
    assertEquals(2, result.get(0).size());
    assertEquals("a", result.get(0).get(0));
    assertEquals("b", result.get(0).get(1));
    assertEquals(2, result.get(1).size());
    assertEquals(1, result.get(2).size());
  }

  @Test
  public void read_listOfSets_returnsCorrectData() throws IOException {
    TypeAdapter<List<Set<Integer>>> adapter =
        gson.getAdapter(new TypeToken<List<Set<Integer>>>() {});
    JsonReader reader = new JsonReader(new StringReader("[[1, 2], [3, 4, 5]]"));
    List<Set<Integer>> result = adapter.read(reader);
    assertEquals(2, result.size());
    assertEquals(2, result.get(0).size());
    assertEquals(3, result.get(1).size());
    assertTrue(result.get(0).contains(1));
    assertTrue(result.get(1).contains(5));
  }

  // ==========================================================================
  // read() tests - Custom objects in collection
  // ==========================================================================

  @Test
  public void read_listOfCustomObjects_returnsCorrectData() throws IOException {
    TypeAdapter<List<Person>> adapter = gson.getAdapter(new TypeToken<List<Person>>() {});
    JsonReader reader =
        new JsonReader(
            new StringReader(
                "[{\"name\":\"Alice\",\"age\":25}, {\"name\":\"Bob\",\"age\":30}]"));
    List<Person> result = adapter.read(reader);
    assertEquals(2, result.size());
    assertEquals("Alice", result.get(0).name);
    assertEquals(25, result.get(0).age);
    assertEquals("Bob", result.get(1).name);
    assertEquals(30, result.get(1).age);
  }

  @Test
  public void read_setOfCustomObjects_returnsCorrectData() throws IOException {
    TypeAdapter<Set<Person>> adapter = gson.getAdapter(new TypeToken<Set<Person>>() {});
    JsonReader reader =
        new JsonReader(new StringReader("[{\"name\":\"Alice\",\"age\":25}]"));
    Set<Person> result = adapter.read(reader);
    assertEquals(1, result.size());
    Person person = result.iterator().next();
    assertEquals("Alice", person.name);
    assertEquals(25, person.age);
  }

  // ==========================================================================
  // write() tests - null handling
  // ==========================================================================

  @Test
  public void write_nullCollection_writesNull() throws IOException {
    TypeAdapter<List<String>> adapter = gson.getAdapter(new TypeToken<List<String>>() {});
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, null);
    assertEquals("null", stringWriter.toString());
  }

  @Test
  public void write_nullSet_writesNull() throws IOException {
    TypeAdapter<Set<Integer>> adapter = gson.getAdapter(new TypeToken<Set<Integer>>() {});
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, null);
    assertEquals("null", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - empty collections
  // ==========================================================================

  @Test
  public void write_emptyList_writesEmptyArray() throws IOException {
    TypeAdapter<List<String>> adapter = gson.getAdapter(new TypeToken<List<String>>() {});
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, new ArrayList<>());
    assertEquals("[]", stringWriter.toString());
  }

  @Test
  public void write_emptySet_writesEmptyArray() throws IOException {
    TypeAdapter<Set<String>> adapter = gson.getAdapter(new TypeToken<Set<String>>() {});
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, new HashSet<>());
    assertEquals("[]", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - List<String>
  // ==========================================================================

  @Test
  public void write_stringList_writesCorrectJson() throws IOException {
    TypeAdapter<List<String>> adapter = gson.getAdapter(new TypeToken<List<String>>() {});
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    List<String> list = new ArrayList<>();
    list.add("a");
    list.add("b");
    list.add("c");
    adapter.write(writer, list);
    assertEquals("[\"a\",\"b\",\"c\"]", stringWriter.toString());
  }

  @Test
  public void write_stringListWithNull_handlesNullElement() throws IOException {
    TypeAdapter<List<String>> adapter = gson.getAdapter(new TypeToken<List<String>>() {});
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    List<String> list = new ArrayList<>();
    list.add("a");
    list.add(null);
    list.add("c");
    adapter.write(writer, list);
    assertEquals("[\"a\",null,\"c\"]", stringWriter.toString());
  }

  @Test
  public void write_singleElementList_writesCorrectJson() throws IOException {
    TypeAdapter<List<String>> adapter = gson.getAdapter(new TypeToken<List<String>>() {});
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    List<String> list = Collections.singletonList("only");
    adapter.write(writer, list);
    assertEquals("[\"only\"]", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - List<Integer>
  // ==========================================================================

  @Test
  public void write_integerList_writesCorrectJson() throws IOException {
    TypeAdapter<List<Integer>> adapter = gson.getAdapter(new TypeToken<List<Integer>>() {});
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    List<Integer> list = new ArrayList<>();
    list.add(1);
    list.add(2);
    list.add(3);
    adapter.write(writer, list);
    assertEquals("[1,2,3]", stringWriter.toString());
  }

  @Test
  public void write_integerListWithNegatives_writesCorrectJson() throws IOException {
    TypeAdapter<List<Integer>> adapter = gson.getAdapter(new TypeToken<List<Integer>>() {});
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    List<Integer> list = new ArrayList<>();
    list.add(-1);
    list.add(0);
    list.add(1);
    adapter.write(writer, list);
    assertEquals("[-1,0,1]", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - Set<String>
  // ==========================================================================

  @Test
  public void write_linkedHashSet_maintainsOrder() throws IOException {
    TypeAdapter<LinkedHashSet<String>> adapter =
        gson.getAdapter(new TypeToken<LinkedHashSet<String>>() {});
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    LinkedHashSet<String> set = new LinkedHashSet<>();
    set.add("first");
    set.add("second");
    set.add("third");
    adapter.write(writer, set);
    assertEquals("[\"first\",\"second\",\"third\"]", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - List<Double> and List<Boolean>
  // ==========================================================================

  @Test
  public void write_doubleList_writesCorrectJson() throws IOException {
    TypeAdapter<List<Double>> adapter = gson.getAdapter(new TypeToken<List<Double>>() {});
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    List<Double> list = new ArrayList<>();
    list.add(1.1);
    list.add(2.2);
    adapter.write(writer, list);
    assertEquals("[1.1,2.2]", stringWriter.toString());
  }

  @Test
  public void write_booleanList_writesCorrectJson() throws IOException {
    TypeAdapter<List<Boolean>> adapter = gson.getAdapter(new TypeToken<List<Boolean>>() {});
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    List<Boolean> list = new ArrayList<>();
    list.add(true);
    list.add(false);
    adapter.write(writer, list);
    assertEquals("[true,false]", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - Queue and Deque
  // ==========================================================================

  @Test
  public void write_queue_writesCorrectJson() throws IOException {
    TypeAdapter<Queue<String>> adapter = gson.getAdapter(new TypeToken<Queue<String>>() {});
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    Queue<String> queue = new ArrayDeque<>();
    queue.add("first");
    queue.add("second");
    adapter.write(writer, queue);
    assertEquals("[\"first\",\"second\"]", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - Nested collections
  // ==========================================================================

  @Test
  public void write_nestedList_writesCorrectJson() throws IOException {
    TypeAdapter<List<List<String>>> adapter =
        gson.getAdapter(new TypeToken<List<List<String>>>() {});
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    List<List<String>> outerList = new ArrayList<>();
    List<String> innerList1 = new ArrayList<>();
    innerList1.add("a");
    innerList1.add("b");
    List<String> innerList2 = new ArrayList<>();
    innerList2.add("c");
    outerList.add(innerList1);
    outerList.add(innerList2);
    adapter.write(writer, outerList);
    assertEquals("[[\"a\",\"b\"],[\"c\"]]", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - Custom objects
  // ==========================================================================

  @Test
  public void write_listOfCustomObjects_writesCorrectJson() throws IOException {
    TypeAdapter<List<Person>> adapter = gson.getAdapter(new TypeToken<List<Person>>() {});
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    List<Person> list = new ArrayList<>();
    list.add(new Person("Alice", 25));
    list.add(new Person("Bob", 30));
    adapter.write(writer, list);
    String json = stringWriter.toString();
    assertTrue(json.contains("\"name\":\"Alice\""));
    assertTrue(json.contains("\"age\":25"));
    assertTrue(json.contains("\"name\":\"Bob\""));
    assertTrue(json.contains("\"age\":30"));
  }

  // ==========================================================================
  // Round-trip tests (read and write)
  // ==========================================================================

  @Test
  public void roundTrip_stringList_preservesData() {
    List<String> original = new ArrayList<>();
    original.add("hello");
    original.add("world");
    original.add("test");
    String json = gson.toJson(original, new TypeToken<List<String>>() {}.getType());
    List<String> result = gson.fromJson(json, new TypeToken<List<String>>() {}.getType());
    assertEquals(original, result);
  }

  @Test
  public void roundTrip_integerList_preservesData() {
    List<Integer> original = new ArrayList<>();
    original.add(1);
    original.add(2);
    original.add(3);
    String json = gson.toJson(original, new TypeToken<List<Integer>>() {}.getType());
    List<Integer> result = gson.fromJson(json, new TypeToken<List<Integer>>() {}.getType());
    assertEquals(original, result);
  }

  @Test
  public void roundTrip_stringSet_preservesData() {
    Set<String> original = new LinkedHashSet<>();
    original.add("a");
    original.add("b");
    original.add("c");
    String json = gson.toJson(original, new TypeToken<Set<String>>() {}.getType());
    Set<String> result = gson.fromJson(json, new TypeToken<Set<String>>() {}.getType());
    assertEquals(original, result);
  }

  @Test
  public void roundTrip_nestedList_preservesData() {
    List<List<Integer>> original = new ArrayList<>();
    List<Integer> inner1 = new ArrayList<>();
    inner1.add(1);
    inner1.add(2);
    List<Integer> inner2 = new ArrayList<>();
    inner2.add(3);
    inner2.add(4);
    original.add(inner1);
    original.add(inner2);
    String json = gson.toJson(original, new TypeToken<List<List<Integer>>>() {}.getType());
    List<List<Integer>> result =
        gson.fromJson(json, new TypeToken<List<List<Integer>>>() {}.getType());
    assertEquals(original, result);
  }

  @Test
  public void roundTrip_customObjectList_preservesData() {
    List<Person> original = new ArrayList<>();
    original.add(new Person("Alice", 25));
    original.add(new Person("Bob", 30));
    String json = gson.toJson(original, new TypeToken<List<Person>>() {}.getType());
    List<Person> result = gson.fromJson(json, new TypeToken<List<Person>>() {}.getType());
    assertEquals(original.size(), result.size());
    assertEquals(original.get(0).name, result.get(0).name);
    assertEquals(original.get(0).age, result.get(0).age);
    assertEquals(original.get(1).name, result.get(1).name);
    assertEquals(original.get(1).age, result.get(1).age);
  }

  // ==========================================================================
  // Edge case tests
  // ==========================================================================

  @Test
  public void read_largeList_handlesCorrectly() throws IOException {
    StringBuilder json = new StringBuilder("[");
    for (int i = 0; i < 1000; i++) {
      if (i > 0) json.append(",");
      json.append(i);
    }
    json.append("]");

    List<Integer> result =
        gson.fromJson(json.toString(), new TypeToken<List<Integer>>() {}.getType());
    assertEquals(1000, result.size());
    for (int i = 0; i < 1000; i++) {
      assertEquals(Integer.valueOf(i), result.get(i));
    }
  }

  @Test
  public void write_largeList_handlesCorrectly() {
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < 1000; i++) {
      list.add(i);
    }
    String json = gson.toJson(list, new TypeToken<List<Integer>>() {}.getType());
    assertTrue(json.startsWith("[0,1,2,"));
    assertTrue(json.endsWith(",998,999]"));
  }

  @Test
  public void read_stringListWithSpecialChars_handlesCorrectly() throws IOException {
    String json = "[\"hello\\nworld\", \"tab\\there\", \"quote\\\"here\"]";
    List<String> result = gson.fromJson(json, new TypeToken<List<String>>() {}.getType());
    assertEquals(3, result.size());
    assertEquals("hello\nworld", result.get(0));
    assertEquals("tab\there", result.get(1));
    assertEquals("quote\"here", result.get(2));
  }

  @Test
  public void write_stringListWithSpecialChars_escapesCorrectly() {
    List<String> list = new ArrayList<>();
    list.add("hello\nworld");
    list.add("tab\there");
    list.add("quote\"here");
    String json = gson.toJson(list, new TypeToken<List<String>>() {}.getType());
    assertTrue(json.contains("\\n"));
    assertTrue(json.contains("\\t"));
    assertTrue(json.contains("\\\""));
  }

  // ==========================================================================
  // Configuration tests (serializeNulls, etc.)
  // ==========================================================================

  @Test
  public void write_withSerializeNullsEnabled_serializesNulls() {
    Gson gsonWithNulls = new GsonBuilder().serializeNulls().create();
    List<String> list = new ArrayList<>();
    list.add("a");
    list.add(null);
    list.add("c");
    String json = gsonWithNulls.toJson(list, new TypeToken<List<String>>() {}.getType());
    assertEquals("[\"a\",null,\"c\"]", json);
  }

  @Test
  public void write_withSerializeNullsDisabled_stillSerializesNullsInCollections() {
    // Nulls in collections should still be serialized even when serializeNulls is disabled
    Gson gsonNoNulls = new Gson();
    List<String> list = new ArrayList<>();
    list.add("a");
    list.add(null);
    list.add("c");
    String json = gsonNoNulls.toJson(list, new TypeToken<List<String>>() {}.getType());
    assertEquals("[\"a\",null,\"c\"]", json);
  }

  // ==========================================================================
  // InstanceCreator tests (custom collection creation)
  // ==========================================================================

  @Test
  public void roundTrip_withCustomInstanceCreator_usesCustomCreator() {
    Gson customGson =
        new GsonBuilder()
            .registerTypeAdapter(
                new TypeToken<List<String>>() {}.getType(),
                new InstanceCreator<List<String>>() {
                  @Override
                  public List<String> createInstance(Type type) {
                    return new LinkedList<>();
                  }
                })
            .create();

    String json = "[\"a\", \"b\", \"c\"]";
    List<String> result =
        customGson.fromJson(json, new TypeToken<List<String>>() {}.getType());

    // Verify it's a LinkedList (our custom instance)
    assertTrue(result instanceof LinkedList);
    assertEquals(3, result.size());
    assertEquals("a", result.get(0));
    assertEquals("b", result.get(1));
    assertEquals("c", result.get(2));
  }

  // ==========================================================================
  // Raw type collection tests
  // ==========================================================================

  @Test
  @SuppressWarnings({"rawtypes", "unchecked"})
  public void read_rawListType_treatsElementsAsObject() throws IOException {
    TypeAdapter<List> adapter = gson.getAdapter(new TypeToken<List>() {});
    JsonReader reader = new JsonReader(new StringReader("[\"text\", 42, true]"));
    List result = adapter.read(reader);
    assertEquals(3, result.size());
    assertEquals("text", result.get(0));
    // Numbers come as Double for raw types
    assertEquals(42.0, result.get(1));
    assertEquals(true, result.get(2));
  }

  @Test
  @SuppressWarnings({"rawtypes", "unchecked"})
  public void write_rawListType_writesCorrectly() throws IOException {
    TypeAdapter<List> adapter = gson.getAdapter(new TypeToken<List>() {});
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    List list = new ArrayList();
    list.add("text");
    list.add(42);
    list.add(true);
    adapter.write(writer, list);
    String json = stringWriter.toString();
    assertTrue(json.contains("\"text\""));
    assertTrue(json.contains("42"));
    assertTrue(json.contains("true"));
  }

  // ==========================================================================
  // Collection interface tests (various implementations)
  // ==========================================================================

  @Test
  public void read_collectionInterface_createsArrayList() throws IOException {
    TypeAdapter<Collection<String>> adapter =
        gson.getAdapter(new TypeToken<Collection<String>>() {});
    JsonReader reader = new JsonReader(new StringReader("[\"a\", \"b\"]"));
    Collection<String> result = adapter.read(reader);
    // Collection interface should use ArrayList as default implementation
    assertTrue(result instanceof ArrayList);
    assertEquals(2, result.size());
  }

  @Test
  public void read_hashSet_returnsHashSet() throws IOException {
    TypeAdapter<HashSet<String>> adapter = gson.getAdapter(new TypeToken<HashSet<String>>() {});
    JsonReader reader = new JsonReader(new StringReader("[\"a\", \"b\", \"c\"]"));
    HashSet<String> result = adapter.read(reader);
    assertTrue(result instanceof HashSet);
    assertEquals(3, result.size());
    assertTrue(result.contains("a"));
    assertTrue(result.contains("b"));
    assertTrue(result.contains("c"));
  }

  // Helper class for custom object tests
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
}
