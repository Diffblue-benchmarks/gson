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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;

/** Tests for {@link ArrayTypeAdapter}. */
public class ArrayTypeAdapterClaudeTest {

  private final Gson gson = new Gson();

  // ==========================================================================
  // Constructor tests - via FACTORY since constructor is tested through usage
  // ==========================================================================

  @Test
  public void factory_createsAdapterForStringArray() {
    TypeAdapter<String[]> adapter = gson.getAdapter(String[].class);
    assertTrue(adapter != null);
  }

  @Test
  public void factory_createsAdapterForIntArray() {
    TypeAdapter<int[]> adapter = gson.getAdapter(int[].class);
    assertTrue(adapter != null);
  }

  @Test
  public void factory_createsAdapterForDoubleArray() {
    TypeAdapter<double[]> adapter = gson.getAdapter(double[].class);
    assertTrue(adapter != null);
  }

  @Test
  public void factory_createsAdapterForBooleanArray() {
    TypeAdapter<boolean[]> adapter = gson.getAdapter(boolean[].class);
    assertTrue(adapter != null);
  }

  @Test
  public void factory_createsAdapterForObjectArray() {
    TypeAdapter<Object[]> adapter = gson.getAdapter(Object[].class);
    assertTrue(adapter != null);
  }

  @Test
  public void factory_createsAdapterForGenericArrayType() {
    TypeAdapter<String[][]> adapter = gson.getAdapter(new TypeToken<String[][]>() {});
    assertTrue(adapter != null);
  }

  // ==========================================================================
  // read() tests - null handling
  // ==========================================================================

  @Test
  public void read_nullJson_returnsNull() throws IOException {
    TypeAdapter<String[]> adapter = gson.getAdapter(String[].class);
    JsonReader reader = new JsonReader(new StringReader("null"));
    String[] result = adapter.read(reader);
    assertNull(result);
  }

  @Test
  public void read_nullJsonForPrimitiveArray_returnsNull() throws IOException {
    TypeAdapter<int[]> adapter = gson.getAdapter(int[].class);
    JsonReader reader = new JsonReader(new StringReader("null"));
    int[] result = adapter.read(reader);
    assertNull(result);
  }

  // ==========================================================================
  // read() tests - empty arrays
  // ==========================================================================

  @Test
  public void read_emptyArray_returnsEmptyStringArray() throws IOException {
    TypeAdapter<String[]> adapter = gson.getAdapter(String[].class);
    JsonReader reader = new JsonReader(new StringReader("[]"));
    String[] result = adapter.read(reader);
    assertEquals(0, result.length);
  }

  @Test
  public void read_emptyArray_returnsEmptyIntArray() throws IOException {
    TypeAdapter<int[]> adapter = gson.getAdapter(int[].class);
    JsonReader reader = new JsonReader(new StringReader("[]"));
    int[] result = adapter.read(reader);
    assertEquals(0, result.length);
  }

  // ==========================================================================
  // read() tests - String arrays (Object arrays)
  // ==========================================================================

  @Test
  public void read_stringArray_returnsCorrectArray() throws IOException {
    TypeAdapter<String[]> adapter = gson.getAdapter(String[].class);
    JsonReader reader = new JsonReader(new StringReader("[\"a\", \"b\", \"c\"]"));
    String[] result = adapter.read(reader);
    assertArrayEquals(new String[] {"a", "b", "c"}, result);
  }

  @Test
  public void read_stringArrayWithNull_handlesNullElement() throws IOException {
    TypeAdapter<String[]> adapter = gson.getAdapter(String[].class);
    JsonReader reader = new JsonReader(new StringReader("[\"a\", null, \"c\"]"));
    String[] result = adapter.read(reader);
    assertArrayEquals(new String[] {"a", null, "c"}, result);
  }

  @Test
  public void read_singleElementStringArray_returnsCorrectArray() throws IOException {
    TypeAdapter<String[]> adapter = gson.getAdapter(String[].class);
    JsonReader reader = new JsonReader(new StringReader("[\"only\"]"));
    String[] result = adapter.read(reader);
    assertArrayEquals(new String[] {"only"}, result);
  }

  // ==========================================================================
  // read() tests - primitive int arrays
  // ==========================================================================

  @Test
  public void read_intArray_returnsCorrectArray() throws IOException {
    TypeAdapter<int[]> adapter = gson.getAdapter(int[].class);
    JsonReader reader = new JsonReader(new StringReader("[1, 2, 3, 4, 5]"));
    int[] result = adapter.read(reader);
    assertArrayEquals(new int[] {1, 2, 3, 4, 5}, result);
  }

  @Test
  public void read_intArrayWithNegatives_returnsCorrectArray() throws IOException {
    TypeAdapter<int[]> adapter = gson.getAdapter(int[].class);
    JsonReader reader = new JsonReader(new StringReader("[-1, 0, 1]"));
    int[] result = adapter.read(reader);
    assertArrayEquals(new int[] {-1, 0, 1}, result);
  }

  @Test
  public void read_singleElementIntArray_returnsCorrectArray() throws IOException {
    TypeAdapter<int[]> adapter = gson.getAdapter(int[].class);
    JsonReader reader = new JsonReader(new StringReader("[42]"));
    int[] result = adapter.read(reader);
    assertArrayEquals(new int[] {42}, result);
  }

  // ==========================================================================
  // read() tests - primitive double arrays
  // ==========================================================================

  @Test
  public void read_doubleArray_returnsCorrectArray() throws IOException {
    TypeAdapter<double[]> adapter = gson.getAdapter(double[].class);
    JsonReader reader = new JsonReader(new StringReader("[1.1, 2.2, 3.3]"));
    double[] result = adapter.read(reader);
    assertArrayEquals(new double[] {1.1, 2.2, 3.3}, result, 0.001);
  }

  @Test
  public void read_doubleArrayWithNegatives_returnsCorrectArray() throws IOException {
    TypeAdapter<double[]> adapter = gson.getAdapter(double[].class);
    JsonReader reader = new JsonReader(new StringReader("[-1.5, 0.0, 1.5]"));
    double[] result = adapter.read(reader);
    assertArrayEquals(new double[] {-1.5, 0.0, 1.5}, result, 0.001);
  }

  // ==========================================================================
  // read() tests - primitive boolean arrays
  // ==========================================================================

  @Test
  public void read_booleanArray_returnsCorrectArray() throws IOException {
    TypeAdapter<boolean[]> adapter = gson.getAdapter(boolean[].class);
    JsonReader reader = new JsonReader(new StringReader("[true, false, true]"));
    boolean[] result = adapter.read(reader);
    assertArrayEquals(new boolean[] {true, false, true}, result);
  }

  @Test
  public void read_booleanArrayAllTrue_returnsCorrectArray() throws IOException {
    TypeAdapter<boolean[]> adapter = gson.getAdapter(boolean[].class);
    JsonReader reader = new JsonReader(new StringReader("[true, true]"));
    boolean[] result = adapter.read(reader);
    assertArrayEquals(new boolean[] {true, true}, result);
  }

  @Test
  public void read_booleanArrayAllFalse_returnsCorrectArray() throws IOException {
    TypeAdapter<boolean[]> adapter = gson.getAdapter(boolean[].class);
    JsonReader reader = new JsonReader(new StringReader("[false, false]"));
    boolean[] result = adapter.read(reader);
    assertArrayEquals(new boolean[] {false, false}, result);
  }

  // ==========================================================================
  // read() tests - primitive long arrays
  // ==========================================================================

  @Test
  public void read_longArray_returnsCorrectArray() throws IOException {
    TypeAdapter<long[]> adapter = gson.getAdapter(long[].class);
    JsonReader reader = new JsonReader(new StringReader("[1000000000000, 2000000000000]"));
    long[] result = adapter.read(reader);
    assertArrayEquals(new long[] {1000000000000L, 2000000000000L}, result);
  }

  // ==========================================================================
  // read() tests - primitive float arrays
  // ==========================================================================

  @Test
  public void read_floatArray_returnsCorrectArray() throws IOException {
    TypeAdapter<float[]> adapter = gson.getAdapter(float[].class);
    JsonReader reader = new JsonReader(new StringReader("[1.5, 2.5, 3.5]"));
    float[] result = adapter.read(reader);
    assertArrayEquals(new float[] {1.5f, 2.5f, 3.5f}, result, 0.001f);
  }

  // ==========================================================================
  // read() tests - primitive byte arrays
  // ==========================================================================

  @Test
  public void read_byteArray_returnsCorrectArray() throws IOException {
    TypeAdapter<byte[]> adapter = gson.getAdapter(byte[].class);
    JsonReader reader = new JsonReader(new StringReader("[1, 2, 3]"));
    byte[] result = adapter.read(reader);
    assertArrayEquals(new byte[] {1, 2, 3}, result);
  }

  // ==========================================================================
  // read() tests - primitive short arrays
  // ==========================================================================

  @Test
  public void read_shortArray_returnsCorrectArray() throws IOException {
    TypeAdapter<short[]> adapter = gson.getAdapter(short[].class);
    JsonReader reader = new JsonReader(new StringReader("[100, 200, 300]"));
    short[] result = adapter.read(reader);
    assertArrayEquals(new short[] {100, 200, 300}, result);
  }

  // ==========================================================================
  // read() tests - primitive char arrays
  // ==========================================================================

  @Test
  public void read_charArray_returnsCorrectArray() throws IOException {
    TypeAdapter<char[]> adapter = gson.getAdapter(char[].class);
    JsonReader reader = new JsonReader(new StringReader("[\"a\", \"b\", \"c\"]"));
    char[] result = adapter.read(reader);
    assertArrayEquals(new char[] {'a', 'b', 'c'}, result);
  }

  // ==========================================================================
  // read() tests - wrapper arrays (Integer[], Double[], etc.)
  // ==========================================================================

  @Test
  public void read_integerWrapperArray_returnsCorrectArray() throws IOException {
    TypeAdapter<Integer[]> adapter = gson.getAdapter(Integer[].class);
    JsonReader reader = new JsonReader(new StringReader("[1, 2, 3]"));
    Integer[] result = adapter.read(reader);
    assertArrayEquals(new Integer[] {1, 2, 3}, result);
  }

  @Test
  public void read_integerWrapperArrayWithNull_handlesNullElement() throws IOException {
    TypeAdapter<Integer[]> adapter = gson.getAdapter(Integer[].class);
    JsonReader reader = new JsonReader(new StringReader("[1, null, 3]"));
    Integer[] result = adapter.read(reader);
    assertArrayEquals(new Integer[] {1, null, 3}, result);
  }

  @Test
  public void read_doubleWrapperArray_returnsCorrectArray() throws IOException {
    TypeAdapter<Double[]> adapter = gson.getAdapter(Double[].class);
    JsonReader reader = new JsonReader(new StringReader("[1.1, 2.2]"));
    Double[] result = adapter.read(reader);
    assertArrayEquals(new Double[] {1.1, 2.2}, result);
  }

  @Test
  public void read_booleanWrapperArray_returnsCorrectArray() throws IOException {
    TypeAdapter<Boolean[]> adapter = gson.getAdapter(Boolean[].class);
    JsonReader reader = new JsonReader(new StringReader("[true, false, null]"));
    Boolean[] result = adapter.read(reader);
    assertArrayEquals(new Boolean[] {true, false, null}, result);
  }

  // ==========================================================================
  // read() tests - nested arrays
  // ==========================================================================

  @Test
  public void read_nestedStringArray_returnsCorrectArray() throws IOException {
    TypeAdapter<String[][]> adapter = gson.getAdapter(String[][].class);
    JsonReader reader = new JsonReader(new StringReader("[[\"a\", \"b\"], [\"c\", \"d\"]]"));
    String[][] result = adapter.read(reader);
    assertEquals(2, result.length);
    assertArrayEquals(new String[] {"a", "b"}, result[0]);
    assertArrayEquals(new String[] {"c", "d"}, result[1]);
  }

  @Test
  public void read_nestedIntArray_returnsCorrectArray() throws IOException {
    TypeAdapter<int[][]> adapter = gson.getAdapter(int[][].class);
    JsonReader reader = new JsonReader(new StringReader("[[1, 2], [3, 4], [5, 6]]"));
    int[][] result = adapter.read(reader);
    assertEquals(3, result.length);
    assertArrayEquals(new int[] {1, 2}, result[0]);
    assertArrayEquals(new int[] {3, 4}, result[1]);
    assertArrayEquals(new int[] {5, 6}, result[2]);
  }

  @Test
  public void read_emptyNestedArrays_returnsCorrectArray() throws IOException {
    TypeAdapter<int[][]> adapter = gson.getAdapter(int[][].class);
    JsonReader reader = new JsonReader(new StringReader("[[], [], []]"));
    int[][] result = adapter.read(reader);
    assertEquals(3, result.length);
    assertEquals(0, result[0].length);
    assertEquals(0, result[1].length);
    assertEquals(0, result[2].length);
  }

  // ==========================================================================
  // read() tests - Object arrays
  // ==========================================================================

  @Test
  public void read_objectArray_returnsCorrectArray() throws IOException {
    TypeAdapter<Object[]> adapter = gson.getAdapter(Object[].class);
    JsonReader reader = new JsonReader(new StringReader("[\"string\", 42, true, null]"));
    Object[] result = adapter.read(reader);
    assertEquals(4, result.length);
    assertEquals("string", result[0]);
    assertEquals(42.0, result[1]); // Numbers are read as Double by default
    assertEquals(true, result[2]);
    assertNull(result[3]);
  }

  // ==========================================================================
  // write() tests - null handling
  // ==========================================================================

  @Test
  public void write_nullArray_writesNull() throws IOException {
    TypeAdapter<String[]> adapter = gson.getAdapter(String[].class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, null);
    assertEquals("null", stringWriter.toString());
  }

  @Test
  public void write_nullPrimitiveArray_writesNull() throws IOException {
    TypeAdapter<int[]> adapter = gson.getAdapter(int[].class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, null);
    assertEquals("null", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - empty arrays
  // ==========================================================================

  @Test
  public void write_emptyStringArray_writesEmptyArray() throws IOException {
    TypeAdapter<String[]> adapter = gson.getAdapter(String[].class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, new String[0]);
    assertEquals("[]", stringWriter.toString());
  }

  @Test
  public void write_emptyIntArray_writesEmptyArray() throws IOException {
    TypeAdapter<int[]> adapter = gson.getAdapter(int[].class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, new int[0]);
    assertEquals("[]", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - String arrays
  // ==========================================================================

  @Test
  public void write_stringArray_writesCorrectJson() throws IOException {
    TypeAdapter<String[]> adapter = gson.getAdapter(String[].class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, new String[] {"a", "b", "c"});
    assertEquals("[\"a\",\"b\",\"c\"]", stringWriter.toString());
  }

  @Test
  public void write_stringArrayWithNull_handlesNullElement() throws IOException {
    TypeAdapter<String[]> adapter = gson.getAdapter(String[].class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, new String[] {"a", null, "c"});
    assertEquals("[\"a\",null,\"c\"]", stringWriter.toString());
  }

  @Test
  public void write_singleElementStringArray_writesCorrectJson() throws IOException {
    TypeAdapter<String[]> adapter = gson.getAdapter(String[].class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, new String[] {"only"});
    assertEquals("[\"only\"]", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - primitive int arrays
  // ==========================================================================

  @Test
  public void write_intArray_writesCorrectJson() throws IOException {
    TypeAdapter<int[]> adapter = gson.getAdapter(int[].class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, new int[] {1, 2, 3, 4, 5});
    assertEquals("[1,2,3,4,5]", stringWriter.toString());
  }

  @Test
  public void write_intArrayWithNegatives_writesCorrectJson() throws IOException {
    TypeAdapter<int[]> adapter = gson.getAdapter(int[].class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, new int[] {-1, 0, 1});
    assertEquals("[-1,0,1]", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - primitive double arrays
  // ==========================================================================

  @Test
  public void write_doubleArray_writesCorrectJson() throws IOException {
    TypeAdapter<double[]> adapter = gson.getAdapter(double[].class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, new double[] {1.1, 2.2, 3.3});
    assertEquals("[1.1,2.2,3.3]", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - primitive boolean arrays
  // ==========================================================================

  @Test
  public void write_booleanArray_writesCorrectJson() throws IOException {
    TypeAdapter<boolean[]> adapter = gson.getAdapter(boolean[].class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, new boolean[] {true, false, true});
    assertEquals("[true,false,true]", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - primitive long arrays
  // ==========================================================================

  @Test
  public void write_longArray_writesCorrectJson() throws IOException {
    TypeAdapter<long[]> adapter = gson.getAdapter(long[].class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, new long[] {1000000000000L, 2000000000000L});
    assertEquals("[1000000000000,2000000000000]", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - primitive float arrays
  // ==========================================================================

  @Test
  public void write_floatArray_writesCorrectJson() throws IOException {
    TypeAdapter<float[]> adapter = gson.getAdapter(float[].class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, new float[] {1.5f, 2.5f, 3.5f});
    assertEquals("[1.5,2.5,3.5]", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - primitive byte arrays
  // ==========================================================================

  @Test
  public void write_byteArray_writesCorrectJson() throws IOException {
    TypeAdapter<byte[]> adapter = gson.getAdapter(byte[].class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, new byte[] {1, 2, 3});
    assertEquals("[1,2,3]", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - primitive short arrays
  // ==========================================================================

  @Test
  public void write_shortArray_writesCorrectJson() throws IOException {
    TypeAdapter<short[]> adapter = gson.getAdapter(short[].class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, new short[] {100, 200, 300});
    assertEquals("[100,200,300]", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - primitive char arrays
  // ==========================================================================

  @Test
  public void write_charArray_writesCorrectJson() throws IOException {
    TypeAdapter<char[]> adapter = gson.getAdapter(char[].class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, new char[] {'a', 'b', 'c'});
    assertEquals("[\"a\",\"b\",\"c\"]", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - wrapper arrays
  // ==========================================================================

  @Test
  public void write_integerWrapperArray_writesCorrectJson() throws IOException {
    TypeAdapter<Integer[]> adapter = gson.getAdapter(Integer[].class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, new Integer[] {1, 2, 3});
    assertEquals("[1,2,3]", stringWriter.toString());
  }

  @Test
  public void write_integerWrapperArrayWithNull_handlesNullElement() throws IOException {
    TypeAdapter<Integer[]> adapter = gson.getAdapter(Integer[].class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, new Integer[] {1, null, 3});
    assertEquals("[1,null,3]", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - nested arrays
  // ==========================================================================

  @Test
  public void write_nestedStringArray_writesCorrectJson() throws IOException {
    TypeAdapter<String[][]> adapter = gson.getAdapter(String[][].class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, new String[][] {{"a", "b"}, {"c", "d"}});
    assertEquals("[[\"a\",\"b\"],[\"c\",\"d\"]]", stringWriter.toString());
  }

  @Test
  public void write_nestedIntArray_writesCorrectJson() throws IOException {
    TypeAdapter<int[][]> adapter = gson.getAdapter(int[][].class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, new int[][] {{1, 2}, {3, 4}, {5, 6}});
    assertEquals("[[1,2],[3,4],[5,6]]", stringWriter.toString());
  }

  @Test
  public void write_emptyNestedArrays_writesCorrectJson() throws IOException {
    TypeAdapter<int[][]> adapter = gson.getAdapter(int[][].class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, new int[][] {{}, {}, {}});
    assertEquals("[[],[],[]]", stringWriter.toString());
  }

  // ==========================================================================
  // Round-trip tests (read and write)
  // ==========================================================================

  @Test
  public void roundTrip_stringArray_preservesData() throws IOException {
    String[] original = {"hello", "world", "test"};
    String json = gson.toJson(original);
    String[] result = gson.fromJson(json, String[].class);
    assertArrayEquals(original, result);
  }

  @Test
  public void roundTrip_intArray_preservesData() throws IOException {
    int[] original = {1, 2, 3, 4, 5};
    String json = gson.toJson(original);
    int[] result = gson.fromJson(json, int[].class);
    assertArrayEquals(original, result);
  }

  @Test
  public void roundTrip_doubleArray_preservesData() throws IOException {
    double[] original = {1.1, 2.2, 3.3};
    String json = gson.toJson(original);
    double[] result = gson.fromJson(json, double[].class);
    assertArrayEquals(original, result, 0.001);
  }

  @Test
  public void roundTrip_booleanArray_preservesData() throws IOException {
    boolean[] original = {true, false, true, false};
    String json = gson.toJson(original);
    boolean[] result = gson.fromJson(json, boolean[].class);
    assertArrayEquals(original, result);
  }

  @Test
  public void roundTrip_nestedArray_preservesData() throws IOException {
    int[][] original = {{1, 2}, {3, 4}, {5, 6}};
    String json = gson.toJson(original);
    int[][] result = gson.fromJson(json, int[][].class);
    assertEquals(original.length, result.length);
    for (int i = 0; i < original.length; i++) {
      assertArrayEquals(original[i], result[i]);
    }
  }

  // ==========================================================================
  // Custom object array tests
  // ==========================================================================

  @Test
  public void roundTrip_customObjectArray_preservesData() throws IOException {
    Person[] original = {new Person("Alice", 25), new Person("Bob", 30)};
    String json = gson.toJson(original);
    Person[] result = gson.fromJson(json, Person[].class);
    assertEquals(2, result.length);
    assertEquals("Alice", result[0].name);
    assertEquals(25, result[0].age);
    assertEquals("Bob", result[1].name);
    assertEquals(30, result[1].age);
  }

  @Test
  public void read_customObjectArray_handlesNullElements() throws IOException {
    String json = "[{\"name\":\"Alice\",\"age\":25},null]";
    Person[] result = gson.fromJson(json, Person[].class);
    assertEquals(2, result.length);
    assertEquals("Alice", result[0].name);
    assertNull(result[1]);
  }

  // ==========================================================================
  // Edge case tests
  // ==========================================================================

  @Test
  public void read_largeArray_handlesCorrectly() throws IOException {
    StringBuilder json = new StringBuilder("[");
    for (int i = 0; i < 1000; i++) {
      if (i > 0) json.append(",");
      json.append(i);
    }
    json.append("]");

    int[] result = gson.fromJson(json.toString(), int[].class);
    assertEquals(1000, result.length);
    for (int i = 0; i < 1000; i++) {
      assertEquals(i, result[i]);
    }
  }

  @Test
  public void write_largeArray_handlesCorrectly() throws IOException {
    int[] array = new int[1000];
    for (int i = 0; i < 1000; i++) {
      array[i] = i;
    }
    String json = gson.toJson(array);
    assertTrue(json.startsWith("[0,1,2,"));
    assertTrue(json.endsWith(",998,999]"));
  }

  @Test
  public void read_stringArrayWithSpecialChars_handlesCorrectly() throws IOException {
    String json = "[\"hello\\nworld\", \"tab\\there\", \"quote\\\"here\"]";
    String[] result = gson.fromJson(json, String[].class);
    assertEquals(3, result.length);
    assertEquals("hello\nworld", result[0]);
    assertEquals("tab\there", result[1]);
    assertEquals("quote\"here", result[2]);
  }

  @Test
  public void write_stringArrayWithSpecialChars_escapesCorrectly() throws IOException {
    String[] array = {"hello\nworld", "tab\there", "quote\"here"};
    String json = gson.toJson(array);
    assertTrue(json.contains("\\n"));
    assertTrue(json.contains("\\t"));
    assertTrue(json.contains("\\\""));
  }

  // ==========================================================================
  // Configuration tests (serializeNulls, etc.)
  // ==========================================================================

  @Test
  public void write_withSerializeNullsEnabled_serializesNulls() throws IOException {
    Gson gsonWithNulls = new GsonBuilder().serializeNulls().create();
    String[] array = {"a", null, "c"};
    String json = gsonWithNulls.toJson(array);
    assertEquals("[\"a\",null,\"c\"]", json);
  }

  @Test
  public void write_withSerializeNullsDisabled_stillSerializesNullsInArrays() throws IOException {
    // Nulls in arrays should still be serialized even when serializeNulls is disabled
    Gson gsonNoNulls = new Gson();
    String[] array = {"a", null, "c"};
    String json = gsonNoNulls.toJson(array);
    assertEquals("[\"a\",null,\"c\"]", json);
  }

  // ==========================================================================
  // Direct constructor test
  // ==========================================================================

  @Test
  public void constructor_directInstantiation_worksCorrectly() throws IOException {
    // Test direct instantiation of ArrayTypeAdapter
    TypeAdapter<String> stringAdapter = gson.getAdapter(String.class);
    ArrayTypeAdapter<String> arrayAdapter =
        new ArrayTypeAdapter<>(gson, stringAdapter, String.class);

    // Test write
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    arrayAdapter.write(writer, new String[] {"test", "array"});
    assertEquals("[\"test\",\"array\"]", stringWriter.toString());

    // Test read
    JsonReader reader = new JsonReader(new StringReader("[\"read\",\"test\"]"));
    Object result = arrayAdapter.read(reader);
    assertTrue(result instanceof String[]);
    assertArrayEquals(new String[] {"read", "test"}, (String[]) result);
  }

  @Test
  public void constructor_withPrimitiveType_worksCorrectly() throws IOException {
    // Test direct instantiation with a primitive type
    @SuppressWarnings("unchecked")
    TypeAdapter<Integer> intAdapter = (TypeAdapter<Integer>) gson.getAdapter(int.class);
    @SuppressWarnings("unchecked")
    ArrayTypeAdapter<Integer> arrayAdapter =
        new ArrayTypeAdapter<>(gson, intAdapter, (Class<Integer>) (Class<?>) int.class);

    // Test write
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    arrayAdapter.write(writer, new int[] {1, 2, 3});
    assertEquals("[1,2,3]", stringWriter.toString());

    // Test read
    JsonReader reader = new JsonReader(new StringReader("[4,5,6]"));
    Object result = arrayAdapter.read(reader);
    assertTrue(result instanceof int[]);
    assertArrayEquals(new int[] {4, 5, 6}, (int[]) result);
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
