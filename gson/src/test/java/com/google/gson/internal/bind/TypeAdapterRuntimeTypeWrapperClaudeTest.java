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
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

/**
 * Tests for {@link TypeAdapterRuntimeTypeWrapper}.
 *
 * <p>TypeAdapterRuntimeTypeWrapper is a package-private class used internally by Gson to handle
 * runtime type polymorphism during serialization of collections, maps, and arrays. This test class
 * exercises it through the public Gson API.
 */
public class TypeAdapterRuntimeTypeWrapperClaudeTest {

  // ==========================================================================
  // Test classes for polymorphism
  // ==========================================================================

  static class Base {
    String baseField = "base";
  }

  static class Derived extends Base {
    String derivedField = "derived";
  }

  static class DoublyDerived extends Derived {
    String doublyDerivedField = "doublyDerived";
  }

  // ==========================================================================
  // Constructor test - tests via instantiation through collection serialization
  // The constructor is package-private, so we test via the factories that create it
  // ==========================================================================

  @Test
  public void constructor_viaCollectionSerialization_createsWrapper() {
    Gson gson = new Gson();
    List<Base> list = new ArrayList<>();
    list.add(new Derived());

    // This exercises the constructor via CollectionTypeAdapterFactory
    String json = gson.toJson(list);
    assertTrue(json.contains("derivedField"));
  }

  // ==========================================================================
  // read() tests - read simply delegates to underlying adapter
  // ==========================================================================

  @Test
  public void read_delegatesToUnderlyingAdapter_vialist() {
    Gson gson = new Gson();
    String json = "[{\"baseField\":\"fromJson\"}]";

    List<Base> result = gson.fromJson(json, new TypeToken<List<Base>>() {}.getType());

    assertEquals(1, result.size());
    assertEquals("fromJson", result.get(0).baseField);
  }

  @Test
  public void read_nullElement_returnsNullInList() {
    Gson gson = new Gson();
    String json = "[null]";

    List<Base> result = gson.fromJson(json, new TypeToken<List<Base>>() {}.getType());

    assertEquals(1, result.size());
    assertNull(result.get(0));
  }

  @Test
  public void read_emptyList_returnsEmptyList() {
    Gson gson = new Gson();
    String json = "[]";

    List<Base> result = gson.fromJson(json, new TypeToken<List<Base>>() {}.getType());

    assertEquals(0, result.size());
  }

  @Test
  public void read_multipleElements_readsAll() {
    Gson gson = new Gson();
    String json = "[{\"baseField\":\"first\"},{\"baseField\":\"second\"},{\"baseField\":\"third\"}]";

    List<Base> result = gson.fromJson(json, new TypeToken<List<Base>>() {}.getType());

    assertEquals(3, result.size());
    assertEquals("first", result.get(0).baseField);
    assertEquals("second", result.get(1).baseField);
    assertEquals("third", result.get(2).baseField);
  }

  // ==========================================================================
  // write() tests - runtime type detection and adapter selection
  // ==========================================================================

  // Tests case: runtimeType == declaredType (no special handling needed)
  @Test
  public void write_sameRuntimeTypeAsDeclared_usesDelegate() {
    Gson gson = new Gson();
    List<Base> list = new ArrayList<>();
    list.add(new Base());

    String json = gson.toJson(list);

    assertTrue(json.contains("\"baseField\":\"base\""));
    assertTrue(!json.contains("derivedField"));
  }

  // Tests case: runtimeType is more specific (subclass) than declared type
  // Using reflective adapters for both declared and runtime type
  @Test
  public void write_subclassRuntimeType_usesRuntimeTypeAdapter() {
    Gson gson = new Gson();
    List<Base> list = new ArrayList<>();
    list.add(new Derived());

    String json = gson.toJson(list);

    // Should serialize both base and derived fields
    assertTrue(json.contains("\"baseField\":\"base\""));
    assertTrue(json.contains("\"derivedField\":\"derived\""));
  }

  // Tests case: deeply nested inheritance
  @Test
  public void write_doublyDerivedRuntimeType_usesDeepestRuntimeTypeAdapter() {
    Gson gson = new Gson();
    List<Base> list = new ArrayList<>();
    list.add(new DoublyDerived());

    String json = gson.toJson(list);

    // Should serialize all fields from entire hierarchy
    assertTrue(json.contains("\"baseField\":\"base\""));
    assertTrue(json.contains("\"derivedField\":\"derived\""));
    assertTrue(json.contains("\"doublyDerivedField\":\"doublyDerived\""));
  }

  // Tests case: null value - no runtime type detection
  @Test
  public void write_nullValue_writesNull() {
    Gson gson = new Gson();
    List<Base> list = new ArrayList<>();
    list.add(null);

    String json = gson.toJson(list);

    assertEquals("[null]", json);
  }

  // Tests case: mixed null and non-null values
  @Test
  public void write_mixedNullAndNonNull_serializesCorrectly() {
    Gson gson = new Gson();
    List<Base> list = new ArrayList<>();
    list.add(new Base());
    list.add(null);
    list.add(new Derived());

    String json = gson.toJson(list);

    // Check structure: [{base fields},null,{derived fields}]
    assertTrue(json.startsWith("[{"));
    assertTrue(json.contains(",null,"));
    assertTrue(json.contains("derivedField"));
  }

  // ==========================================================================
  // write() tests - custom registered adapter for runtime type
  // Tests: "First preference: a type adapter registered for the runtime type"
  // ==========================================================================

  @Test
  public void write_customAdapterForRuntimeType_usesCustomAdapter() {
    Gson gson =
        new GsonBuilder()
            .registerTypeAdapter(
                Derived.class,
                new TypeAdapter<Derived>() {
                  @Override
                  public void write(JsonWriter out, Derived value) throws IOException {
                    out.beginObject();
                    out.name("custom").value("customValue");
                    out.endObject();
                  }

                  @Override
                  public Derived read(JsonReader in) throws IOException {
                    return null;
                  }
                })
            .create();

    List<Base> list = new ArrayList<>();
    list.add(new Derived());

    String json = gson.toJson(list);

    // Should use the custom adapter, not the reflective one
    assertTrue(json.contains("\"custom\":\"customValue\""));
    assertTrue(!json.contains("baseField"));
    assertTrue(!json.contains("derivedField"));
  }

  // ==========================================================================
  // write() tests - custom registered adapter for declared type
  // Tests: "Second preference: a type adapter registered for the declared type"
  // When delegate is not reflective and runtime type adapter is reflective
  // ==========================================================================

  @Test
  public void write_customAdapterForDeclaredType_runtimeTypeReflective_usesCustomAdapter() {
    Gson gson =
        new GsonBuilder()
            .registerTypeAdapter(
                Base.class,
                new TypeAdapter<Base>() {
                  @Override
                  public void write(JsonWriter out, Base value) throws IOException {
                    out.beginObject();
                    out.name("customBase").value("baseCustomValue");
                    out.endObject();
                  }

                  @Override
                  public Base read(JsonReader in) throws IOException {
                    return null;
                  }
                })
            .create();

    List<Base> list = new ArrayList<>();
    list.add(new Derived());

    // Must use explicit type so Gson knows element type is Base
    // Without explicit type, element type would be Object (due to type erasure)
    Type listType = new TypeToken<List<Base>>() {}.getType();
    String json = gson.toJson(list, listType);

    // Should use the custom adapter for declared type since it's non-reflective
    // and the runtime type adapter would be reflective
    assertTrue(json.contains("\"customBase\":\"baseCustomValue\""));
    assertTrue(!json.contains("baseField"));
    assertTrue(!json.contains("derivedField"));
  }

  // ==========================================================================
  // write() tests - both declared and runtime type have custom adapters
  // Runtime type's custom adapter should win
  // ==========================================================================

  @Test
  public void write_customAdapterForBothTypes_usesRuntimeTypeAdapter() {
    Gson gson =
        new GsonBuilder()
            .registerTypeAdapter(
                Base.class,
                new TypeAdapter<Base>() {
                  @Override
                  public void write(JsonWriter out, Base value) throws IOException {
                    out.beginObject();
                    out.name("fromBase").value(true);
                    out.endObject();
                  }

                  @Override
                  public Base read(JsonReader in) throws IOException {
                    return null;
                  }
                })
            .registerTypeAdapter(
                Derived.class,
                new TypeAdapter<Derived>() {
                  @Override
                  public void write(JsonWriter out, Derived value) throws IOException {
                    out.beginObject();
                    out.name("fromDerived").value(true);
                    out.endObject();
                  }

                  @Override
                  public Derived read(JsonReader in) throws IOException {
                    return null;
                  }
                })
            .create();

    List<Base> list = new ArrayList<>();
    list.add(new Derived());

    String json = gson.toJson(list);

    // Runtime type's custom adapter should be used
    assertTrue(json.contains("\"fromDerived\":true"));
    assertTrue(!json.contains("fromBase"));
  }

  // ==========================================================================
  // write() tests - via Map (exercises TypeAdapterRuntimeTypeWrapper for values)
  // ==========================================================================

  @Test
  public void write_mapValue_subclassRuntimeType_usesRuntimeTypeAdapter() {
    Gson gson = new Gson();
    Map<String, Base> map = new HashMap<>();
    map.put("key", new Derived());

    String json = gson.toJson(map);

    assertTrue(json.contains("\"baseField\":\"base\""));
    assertTrue(json.contains("\"derivedField\":\"derived\""));
  }

  @Test
  public void write_mapValue_nullValue_writesNull() {
    // Need serializeNulls() to output null values
    Gson gson = new GsonBuilder().serializeNulls().create();
    Map<String, Base> map = new HashMap<>();
    map.put("key", null);

    // Must use explicit type so Gson knows value type is Base
    // Without explicit type, element type would be Object (due to type erasure)
    Type mapType = new TypeToken<Map<String, Base>>() {}.getType();
    String json = gson.toJson(map, mapType);

    assertTrue(json.contains("\"key\":null"));
  }

  // ==========================================================================
  // write() tests - via Array (exercises TypeAdapterRuntimeTypeWrapper)
  // ==========================================================================

  @Test
  public void write_array_subclassRuntimeType_usesRuntimeTypeAdapter() {
    Gson gson = new Gson();
    Base[] array = new Base[] {new Derived()};

    String json = gson.toJson(array);

    assertTrue(json.contains("\"baseField\":\"base\""));
    assertTrue(json.contains("\"derivedField\":\"derived\""));
  }

  @Test
  public void write_array_nullElement_writesNull() {
    Gson gson = new Gson();
    Base[] array = new Base[] {null};

    String json = gson.toJson(array);

    assertEquals("[null]", json);
  }

  @Test
  public void write_array_mixedElements_serializesCorrectly() {
    Gson gson = new Gson();
    Base[] array = new Base[] {new Base(), new Derived(), new DoublyDerived()};

    String json = gson.toJson(array);

    // All types should have appropriate fields serialized
    assertTrue(json.contains("\"baseField\":\"base\""));
    assertTrue(json.contains("\"derivedField\":\"derived\""));
    assertTrue(json.contains("\"doublyDerivedField\":\"doublyDerived\""));
  }

  // ==========================================================================
  // write() tests - complex nesting scenarios
  // ==========================================================================

  @Test
  public void write_listOfLists_serializesNestedPolymorphism() {
    Gson gson = new Gson();
    List<List<Base>> listOfLists = new ArrayList<>();
    List<Base> innerList = new ArrayList<>();
    innerList.add(new Derived());
    listOfLists.add(innerList);

    String json = gson.toJson(listOfLists);

    assertTrue(json.contains("\"baseField\":\"base\""));
    assertTrue(json.contains("\"derivedField\":\"derived\""));
  }

  @Test
  public void write_mapWithListValues_serializesNestedPolymorphism() {
    Gson gson = new Gson();
    Map<String, List<Base>> map = new HashMap<>();
    List<Base> list = new ArrayList<>();
    list.add(new Derived());
    map.put("items", list);

    String json = gson.toJson(map);

    assertTrue(json.contains("\"baseField\":\"base\""));
    assertTrue(json.contains("\"derivedField\":\"derived\""));
  }

  // ==========================================================================
  // write() tests - TypeVariable scenario
  // Tests getRuntimeTypeIfMoreSpecific with TypeVariable
  // ==========================================================================

  static class GenericContainer<T> {
    T item;

    GenericContainer(T item) {
      this.item = item;
    }
  }

  @Test
  public void write_genericContainerWithPolymorphicElement_serializesRuntimeType() {
    Gson gson = new Gson();
    GenericContainer<Base> container = new GenericContainer<>(new Derived());

    String json = gson.toJson(container);

    // The generic field should include runtime type's fields
    assertTrue(json.contains("\"baseField\":\"base\""));
    assertTrue(json.contains("\"derivedField\":\"derived\""));
  }

  // ==========================================================================
  // Round-trip tests
  // ==========================================================================

  @Test
  public void roundTrip_listWithBaseType_preservesData() {
    Gson gson = new Gson();
    List<Base> original = new ArrayList<>();
    Base b = new Base();
    b.baseField = "customBaseValue";
    original.add(b);

    String json = gson.toJson(original);
    List<Base> restored = gson.fromJson(json, new TypeToken<List<Base>>() {}.getType());

    assertEquals(1, restored.size());
    assertEquals("customBaseValue", restored.get(0).baseField);
  }

  // ==========================================================================
  // Direct adapter test via TypeToken - tests the adapter wrapping behavior
  // ==========================================================================

  @Test
  public void directAdapterUsage_write_handlesPolymorphism() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<List<Base>> adapter = gson.getAdapter(new TypeToken<List<Base>>() {});

    List<Base> list = new ArrayList<>();
    list.add(new Derived());

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, list);

    String json = stringWriter.toString();
    assertTrue(json.contains("\"derivedField\":\"derived\""));
  }

  @Test
  public void directAdapterUsage_read_readsElements() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<List<Base>> adapter = gson.getAdapter(new TypeToken<List<Base>>() {});

    JsonReader reader = new JsonReader(new StringReader("[{\"baseField\":\"readTest\"}]"));
    List<Base> result = adapter.read(reader);

    assertEquals(1, result.size());
    assertEquals("readTest", result.get(0).baseField);
  }

  // ==========================================================================
  // Edge case: same type but different instance
  // ==========================================================================

  @Test
  public void write_exactSameType_usesDelegate() {
    Gson gson = new Gson();
    List<Base> list = new ArrayList<>();
    Base b = new Base();
    b.baseField = "exactType";
    list.add(b);

    String json = gson.toJson(list);

    assertTrue(json.contains("\"baseField\":\"exactType\""));
    assertTrue(!json.contains("derivedField")); // No extra fields
  }

  // ==========================================================================
  // Test with interface type
  // ==========================================================================

  interface Printable {
    String getPrintValue();
  }

  static class PrintableImpl implements Printable {
    String value = "implValue";

    @Override
    public String getPrintValue() {
      return value;
    }
  }

  @Test
  public void write_interfaceType_serializesRuntimeTypeFields() {
    Gson gson = new Gson();
    List<Printable> list = new ArrayList<>();
    list.add(new PrintableImpl());

    String json = gson.toJson(list);

    // Should serialize the concrete class's fields
    assertTrue(json.contains("\"value\":\"implValue\""));
  }

  // ==========================================================================
  // Test with hierarchical TypeAdapter delegation
  // ==========================================================================

  @Test
  public void write_withTypeHierarchyAdapter_appliesCorrectly() {
    Gson gson =
        new GsonBuilder()
            .registerTypeHierarchyAdapter(
                Base.class,
                new TypeAdapter<Base>() {
                  @Override
                  public void write(JsonWriter out, Base value) throws IOException {
                    out.beginObject();
                    out.name("hierarchyAdapter").value(value.getClass().getSimpleName());
                    out.endObject();
                  }

                  @Override
                  public Base read(JsonReader in) throws IOException {
                    return null;
                  }
                })
            .create();

    List<Base> list = new ArrayList<>();
    list.add(new Derived());

    String json = gson.toJson(list);

    assertTrue(json.contains("\"hierarchyAdapter\":\"Derived\""));
  }

  // ==========================================================================
  // Test with Object type (special case for type detection)
  // ==========================================================================

  @Test
  public void write_objectDeclaredType_serializesRuntimeTypeFields() {
    Gson gson = new Gson();
    List<Object> list = new ArrayList<>();
    list.add(new Derived());

    String json = gson.toJson(list);

    assertTrue(json.contains("\"derivedField\":\"derived\""));
  }

  // ==========================================================================
  // Stress test: many elements with mixed types
  // ==========================================================================

  @Test
  public void write_manyElementsMixedTypes_allSerializedCorrectly() {
    Gson gson = new Gson();
    List<Base> list = new ArrayList<>();

    for (int i = 0; i < 100; i++) {
      if (i % 3 == 0) {
        list.add(new Base());
      } else if (i % 3 == 1) {
        list.add(new Derived());
      } else {
        list.add(new DoublyDerived());
      }
    }

    String json = gson.toJson(list);
    List<Map<String, Object>> result =
        gson.fromJson(json, new TypeToken<List<Map<String, Object>>>() {}.getType());

    assertEquals(100, result.size());
    // Verify each element has expected fields based on type
    for (int i = 0; i < 100; i++) {
      Map<String, Object> element = result.get(i);
      assertTrue(element.containsKey("baseField"));

      if (i % 3 == 1) {
        // Derived
        assertTrue(element.containsKey("derivedField"));
        assertTrue(!element.containsKey("doublyDerivedField"));
      } else if (i % 3 == 2) {
        // DoublyDerived
        assertTrue(element.containsKey("derivedField"));
        assertTrue(element.containsKey("doublyDerivedField"));
      }
    }
  }
}
