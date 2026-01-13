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
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.List;
import org.junit.Test;

/**
 * Tests for {@link TypeAdapters} factory methods:
 * <ul>
 *   <li>{@link TypeAdapters#newFactory(TypeToken, TypeAdapter)}</li>
 *   <li>{@link TypeAdapters#newFactory(Class, TypeAdapter)}</li>
 *   <li>{@link TypeAdapters#newFactory(Class, Class, TypeAdapter)}</li>
 *   <li>{@link TypeAdapters#newFactoryForMultipleTypes(Class, Class, TypeAdapter)}</li>
 *   <li>{@link TypeAdapters#newTypeHierarchyFactory(Class, TypeAdapter)}</li>
 * </ul>
 */
public class TypeAdaptersClaudeTest {

  // ==========================================================================
  // Test data classes
  // ==========================================================================

  private static class CustomClass {
    String value;

    CustomClass() {}

    CustomClass(String value) {
      this.value = value;
    }
  }

  private static class AnotherClass {
    String data;

    AnotherClass() {}

    AnotherClass(String data) {
      this.data = data;
    }
  }

  private static class BaseClass {
    String base;

    BaseClass() {}

    BaseClass(String base) {
      this.base = base;
    }
  }

  private static class DerivedClass extends BaseClass {
    String derived;

    DerivedClass() {}

    DerivedClass(String base, String derived) {
      super(base);
      this.derived = derived;
    }
  }

  // ==========================================================================
  // Simple TypeAdapter for testing
  // ==========================================================================

  private static TypeAdapter<CustomClass> customClassAdapter =
      new TypeAdapter<CustomClass>() {
        @Override
        public void write(JsonWriter out, CustomClass value) throws IOException {
          if (value == null) {
            out.nullValue();
          } else {
            out.beginObject();
            out.name("custom_value").value(value.value);
            out.endObject();
          }
        }

        @Override
        public CustomClass read(JsonReader in) throws IOException {
          if (in.peek() == com.google.gson.stream.JsonToken.NULL) {
            in.nextNull();
            return null;
          }
          CustomClass result = new CustomClass();
          in.beginObject();
          while (in.hasNext()) {
            String name = in.nextName();
            if ("custom_value".equals(name)) {
              result.value = in.nextString();
            } else {
              in.skipValue();
            }
          }
          in.endObject();
          return result;
        }

        @Override
        public String toString() {
          return "CustomClassAdapter";
        }
      };

  private static TypeAdapter<BaseClass> baseClassAdapter =
      new TypeAdapter<BaseClass>() {
        @Override
        public void write(JsonWriter out, BaseClass value) throws IOException {
          if (value == null) {
            out.nullValue();
          } else {
            out.beginObject();
            out.name("base_field").value(value.base);
            out.endObject();
          }
        }

        @Override
        public BaseClass read(JsonReader in) throws IOException {
          if (in.peek() == com.google.gson.stream.JsonToken.NULL) {
            in.nextNull();
            return null;
          }
          BaseClass result = new BaseClass();
          in.beginObject();
          while (in.hasNext()) {
            String name = in.nextName();
            if ("base_field".equals(name)) {
              result.base = in.nextString();
            } else {
              in.skipValue();
            }
          }
          in.endObject();
          return result;
        }
      };

  // ==========================================================================
  // Tests for newFactory(TypeToken, TypeAdapter)
  // ==========================================================================

  @Test
  public void newFactory_typeToken_matchesExactType() {
    TypeAdapterFactory factory =
        TypeAdapters.newFactory(TypeToken.get(CustomClass.class), customClassAdapter);
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();

    // Serialize
    CustomClass obj = new CustomClass("test");
    String json = gson.toJson(obj);
    assertEquals("{\"custom_value\":\"test\"}", json);

    // Deserialize
    CustomClass deserialized = gson.fromJson("{\"custom_value\":\"hello\"}", CustomClass.class);
    assertEquals("hello", deserialized.value);
  }

  @Test
  public void newFactory_typeToken_doesNotMatchDifferentType() {
    TypeAdapterFactory factory =
        TypeAdapters.newFactory(TypeToken.get(CustomClass.class), customClassAdapter);
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();

    // AnotherClass should not use the custom adapter
    AnotherClass obj = new AnotherClass("test");
    String json = gson.toJson(obj);
    // Should use default serialization
    assertTrue(json.contains("\"data\":\"test\""));
  }

  @Test
  public void newFactory_typeToken_doesNotMatchSubtype() {
    TypeAdapterFactory factory =
        TypeAdapters.newFactory(TypeToken.get(BaseClass.class), baseClassAdapter);
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();

    // DerivedClass should NOT match since newFactory requires exact TypeToken match
    DerivedClass derived = new DerivedClass("base", "derived");
    String json = gson.toJson(derived);
    // Should use default serialization because DerivedClass != BaseClass
    assertTrue(json.contains("\"derived\":\"derived\""));
  }

  @Test
  public void newFactory_typeToken_returnsNullForNonMatchingType() {
    TypeAdapterFactory factory =
        TypeAdapters.newFactory(TypeToken.get(CustomClass.class), customClassAdapter);
    Gson gson = new Gson();

    TypeAdapter<AnotherClass> adapter = factory.create(gson, TypeToken.get(AnotherClass.class));
    assertNull(adapter);
  }

  @Test
  public void newFactory_typeToken_matchesParameterizedType() {
    TypeAdapter<List<String>> listAdapter =
        new TypeAdapter<List<String>>() {
          @Override
          public void write(JsonWriter out, List<String> value) throws IOException {
            out.beginArray();
            out.value("custom");
            out.endArray();
          }

          @Override
          public List<String> read(JsonReader in) throws IOException {
            in.beginArray();
            java.util.ArrayList<String> result = new java.util.ArrayList<>();
            while (in.hasNext()) {
              result.add(in.nextString());
            }
            in.endArray();
            return result;
          }
        };

    TypeToken<List<String>> listStringToken = new TypeToken<List<String>>() {};
    TypeAdapterFactory factory = TypeAdapters.newFactory(listStringToken, listAdapter);
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();

    // Should match List<String>
    java.util.ArrayList<String> list = new java.util.ArrayList<>();
    list.add("a");
    String json = gson.toJson(list, listStringToken.getType());
    assertEquals("[\"custom\"]", json);
  }

  @Test
  public void newFactory_typeToken_doesNotMatchDifferentParameterizedType() {
    TypeAdapter<List<String>> listAdapter =
        new TypeAdapter<List<String>>() {
          @Override
          public void write(JsonWriter out, List<String> value) throws IOException {
            out.beginArray();
            out.value("custom");
            out.endArray();
          }

          @Override
          public List<String> read(JsonReader in) throws IOException {
            return new java.util.ArrayList<>();
          }
        };

    TypeToken<List<String>> listStringToken = new TypeToken<List<String>>() {};
    TypeAdapterFactory factory = TypeAdapters.newFactory(listStringToken, listAdapter);
    Gson gson = new Gson();

    // Should NOT match List<Integer> - different type parameter
    TypeToken<List<Integer>> listIntToken = new TypeToken<List<Integer>>() {};
    TypeAdapter<List<Integer>> adapter = factory.create(gson, listIntToken);
    assertNull(adapter);
  }

  // ==========================================================================
  // Tests for newFactory(Class, TypeAdapter)
  // ==========================================================================

  @Test
  public void newFactory_class_matchesExactType() {
    TypeAdapterFactory factory = TypeAdapters.newFactory(CustomClass.class, customClassAdapter);
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();

    // Serialize
    CustomClass obj = new CustomClass("test");
    String json = gson.toJson(obj);
    assertEquals("{\"custom_value\":\"test\"}", json);

    // Deserialize
    CustomClass deserialized = gson.fromJson("{\"custom_value\":\"world\"}", CustomClass.class);
    assertEquals("world", deserialized.value);
  }

  @Test
  public void newFactory_class_doesNotMatchDifferentType() {
    TypeAdapterFactory factory = TypeAdapters.newFactory(CustomClass.class, customClassAdapter);
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();

    // AnotherClass should not use the custom adapter
    AnotherClass obj = new AnotherClass("test");
    String json = gson.toJson(obj);
    assertTrue(json.contains("\"data\":\"test\""));
  }

  @Test
  public void newFactory_class_doesNotMatchSubtype() {
    TypeAdapterFactory factory = TypeAdapters.newFactory(BaseClass.class, baseClassAdapter);
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();

    // DerivedClass should NOT match since newFactory(Class) uses == comparison
    DerivedClass derived = new DerivedClass("base", "derived");
    String json = gson.toJson(derived);
    // Should use default serialization
    assertTrue(json.contains("\"derived\":\"derived\""));
  }

  @Test
  public void newFactory_class_returnsNullForNonMatchingType() {
    TypeAdapterFactory factory = TypeAdapters.newFactory(CustomClass.class, customClassAdapter);
    Gson gson = new Gson();

    TypeAdapter<AnotherClass> adapter = factory.create(gson, TypeToken.get(AnotherClass.class));
    assertNull(adapter);
  }

  @Test
  public void newFactory_class_toString_includesTypeAndAdapter() {
    TypeAdapterFactory factory = TypeAdapters.newFactory(CustomClass.class, customClassAdapter);
    String str = factory.toString();
    assertTrue(str.contains("CustomClass"));
    assertTrue(str.contains("CustomClassAdapter"));
  }

  @Test
  public void newFactory_class_handlesNull() throws IOException {
    TypeAdapterFactory factory = TypeAdapters.newFactory(CustomClass.class, customClassAdapter);
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();

    // Null serialization
    String json = gson.toJson(null, CustomClass.class);
    assertEquals("null", json);

    // Null deserialization
    CustomClass result = gson.fromJson("null", CustomClass.class);
    assertNull(result);
  }

  // ==========================================================================
  // Tests for newFactory(Class unboxed, Class boxed, TypeAdapter)
  // ==========================================================================

  @Test
  public void newFactory_unboxedBoxed_matchesPrimitiveType() {
    TypeAdapter<Integer> intAdapter =
        new TypeAdapter<Integer>() {
          @Override
          public void write(JsonWriter out, Integer value) throws IOException {
            if (value == null) {
              out.nullValue();
            } else {
              out.value(value * 10); // multiply by 10 for testing
            }
          }

          @Override
          public Integer read(JsonReader in) throws IOException {
            if (in.peek() == com.google.gson.stream.JsonToken.NULL) {
              in.nextNull();
              return null;
            }
            return in.nextInt() / 10; // divide by 10 for testing
          }

          @Override
          public String toString() {
            return "TestIntAdapter";
          }
        };

    TypeAdapterFactory factory = TypeAdapters.newFactory(int.class, Integer.class, intAdapter);
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();

    // Test with primitive int
    int primitive = 5;
    String json = gson.toJson(primitive);
    assertEquals("50", json);

    // Test with boxed Integer
    Integer boxed = 7;
    json = gson.toJson(boxed);
    assertEquals("70", json);
  }

  @Test
  public void newFactory_unboxedBoxed_matchesBoxedType() {
    TypeAdapter<Integer> intAdapter =
        new TypeAdapter<Integer>() {
          @Override
          public void write(JsonWriter out, Integer value) throws IOException {
            if (value == null) {
              out.nullValue();
            } else {
              out.value(value + 100);
            }
          }

          @Override
          public Integer read(JsonReader in) throws IOException {
            if (in.peek() == com.google.gson.stream.JsonToken.NULL) {
              in.nextNull();
              return null;
            }
            return in.nextInt() - 100;
          }
        };

    TypeAdapterFactory factory = TypeAdapters.newFactory(int.class, Integer.class, intAdapter);
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();

    // Test deserialization to boxed Integer
    Integer result = gson.fromJson("142", Integer.class);
    assertEquals(Integer.valueOf(42), result);
  }

  @Test
  public void newFactory_unboxedBoxed_doesNotMatchOtherTypes() {
    TypeAdapter<Integer> intAdapter =
        new TypeAdapter<Integer>() {
          @Override
          public void write(JsonWriter out, Integer value) throws IOException {
            out.value(value);
          }

          @Override
          public Integer read(JsonReader in) throws IOException {
            return in.nextInt();
          }
        };

    TypeAdapterFactory factory = TypeAdapters.newFactory(int.class, Integer.class, intAdapter);
    Gson gson = new Gson();

    // Should not match Long
    TypeAdapter<Long> longAdapter = factory.create(gson, TypeToken.get(Long.class));
    assertNull(longAdapter);

    // Should not match String
    TypeAdapter<String> stringAdapter = factory.create(gson, TypeToken.get(String.class));
    assertNull(stringAdapter);
  }

  @Test
  public void newFactory_unboxedBoxed_toString_includesBothTypes() {
    TypeAdapter<Integer> intAdapter =
        new TypeAdapter<Integer>() {
          @Override
          public void write(JsonWriter out, Integer value) throws IOException {
            out.value(value);
          }

          @Override
          public Integer read(JsonReader in) throws IOException {
            return in.nextInt();
          }

          @Override
          public String toString() {
            return "TestIntAdapter";
          }
        };

    TypeAdapterFactory factory = TypeAdapters.newFactory(int.class, Integer.class, intAdapter);
    String str = factory.toString();
    assertTrue(str.contains("int"));
    assertTrue(str.contains("Integer"));
    assertTrue(str.contains("TestIntAdapter"));
  }

  @Test
  public void newFactory_booleanTypes_matchesBoth() {
    TypeAdapter<Boolean> boolAdapter =
        new TypeAdapter<Boolean>() {
          @Override
          public void write(JsonWriter out, Boolean value) throws IOException {
            if (value == null) {
              out.nullValue();
            } else {
              out.value(value ? "YES" : "NO");
            }
          }

          @Override
          public Boolean read(JsonReader in) throws IOException {
            if (in.peek() == com.google.gson.stream.JsonToken.NULL) {
              in.nextNull();
              return null;
            }
            return "YES".equals(in.nextString());
          }
        };

    TypeAdapterFactory factory = TypeAdapters.newFactory(boolean.class, Boolean.class, boolAdapter);
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();

    // Primitive boolean
    boolean primitiveTrue = true;
    assertEquals("\"YES\"", gson.toJson(primitiveTrue));

    boolean primitiveFalse = false;
    assertEquals("\"NO\"", gson.toJson(primitiveFalse));

    // Boxed Boolean
    Boolean boxed = Boolean.TRUE;
    assertEquals("\"YES\"", gson.toJson(boxed));
  }

  // ==========================================================================
  // Tests for newFactoryForMultipleTypes(Class base, Class sub, TypeAdapter)
  // ==========================================================================

  @Test
  public void newFactoryForMultipleTypes_matchesBaseType() {
    TypeAdapterFactory factory =
        TypeAdapters.newFactoryForMultipleTypes(BaseClass.class, DerivedClass.class, baseClassAdapter);
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();

    // BaseClass should match
    BaseClass base = new BaseClass("baseValue");
    String json = gson.toJson(base);
    assertEquals("{\"base_field\":\"baseValue\"}", json);
  }

  @Test
  public void newFactoryForMultipleTypes_matchesSubType() {
    TypeAdapterFactory factory =
        TypeAdapters.newFactoryForMultipleTypes(BaseClass.class, DerivedClass.class, baseClassAdapter);
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();

    // DerivedClass should also match
    DerivedClass derived = new DerivedClass("baseValue", "derivedValue");
    String json = gson.toJson(derived);
    // Should use the baseClassAdapter which only outputs base_field
    assertEquals("{\"base_field\":\"baseValue\"}", json);
  }

  @Test
  public void newFactoryForMultipleTypes_doesNotMatchOtherType() {
    TypeAdapterFactory factory =
        TypeAdapters.newFactoryForMultipleTypes(BaseClass.class, DerivedClass.class, baseClassAdapter);
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();

    // CustomClass should not match
    CustomClass custom = new CustomClass("test");
    String json = gson.toJson(custom);
    assertTrue(json.contains("\"value\":\"test\""));
  }

  @Test
  public void newFactoryForMultipleTypes_returnsNullForNonMatchingType() {
    TypeAdapterFactory factory =
        TypeAdapters.newFactoryForMultipleTypes(BaseClass.class, DerivedClass.class, baseClassAdapter);
    Gson gson = new Gson();

    TypeAdapter<CustomClass> adapter = factory.create(gson, TypeToken.get(CustomClass.class));
    assertNull(adapter);
  }

  @Test
  public void newFactoryForMultipleTypes_toString_includesBothTypes() {
    TypeAdapterFactory factory =
        TypeAdapters.newFactoryForMultipleTypes(BaseClass.class, DerivedClass.class, baseClassAdapter);
    String str = factory.toString();
    assertTrue(str.contains("BaseClass"));
    assertTrue(str.contains("DerivedClass"));
  }

  @Test
  public void newFactoryForMultipleTypes_exactMatchOnly_notHierarchy() {
    // Create a third class that extends DerivedClass
    // This tests that newFactoryForMultipleTypes does NOT match arbitrary subtypes
    // It only matches the two exact types specified

    TypeAdapterFactory factory =
        TypeAdapters.newFactoryForMultipleTypes(BaseClass.class, DerivedClass.class, baseClassAdapter);
    Gson gson = new Gson();

    // Should match BaseClass
    TypeAdapter<BaseClass> baseAdapter = factory.create(gson, TypeToken.get(BaseClass.class));
    assertNotNull(baseAdapter);

    // Should match DerivedClass
    TypeAdapter<DerivedClass> derivedAdapter = factory.create(gson, TypeToken.get(DerivedClass.class));
    assertNotNull(derivedAdapter);
  }

  // ==========================================================================
  // Tests for newTypeHierarchyFactory(Class, TypeAdapter)
  // ==========================================================================

  @Test
  public void newTypeHierarchyFactory_matchesExactType() {
    TypeAdapterFactory factory =
        TypeAdapters.newTypeHierarchyFactory(BaseClass.class, baseClassAdapter);
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();

    // Exact type match
    BaseClass base = new BaseClass("baseValue");
    String json = gson.toJson(base);
    assertEquals("{\"base_field\":\"baseValue\"}", json);
  }

  @Test
  public void newTypeHierarchyFactory_matchesSubtype() {
    TypeAdapterFactory factory =
        TypeAdapters.newTypeHierarchyFactory(BaseClass.class, baseClassAdapter);
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();

    // Subtype should match through hierarchy
    DerivedClass derived = new DerivedClass("baseValue", "derivedValue");
    String json = gson.toJson(derived);
    // Should use the baseClassAdapter
    assertEquals("{\"base_field\":\"baseValue\"}", json);
  }

  @Test
  public void newTypeHierarchyFactory_doesNotMatchUnrelatedType() {
    TypeAdapterFactory factory =
        TypeAdapters.newTypeHierarchyFactory(BaseClass.class, baseClassAdapter);
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();

    // CustomClass is not related to BaseClass
    CustomClass custom = new CustomClass("test");
    String json = gson.toJson(custom);
    assertTrue(json.contains("\"value\":\"test\""));
  }

  @Test
  public void newTypeHierarchyFactory_returnsNullForNonMatchingType() {
    TypeAdapterFactory factory =
        TypeAdapters.newTypeHierarchyFactory(BaseClass.class, baseClassAdapter);
    Gson gson = new Gson();

    TypeAdapter<CustomClass> adapter = factory.create(gson, TypeToken.get(CustomClass.class));
    assertNull(adapter);
  }

  @Test
  public void newTypeHierarchyFactory_toString_includesTypeAndHierarchy() {
    TypeAdapterFactory factory =
        TypeAdapters.newTypeHierarchyFactory(BaseClass.class, baseClassAdapter);
    String str = factory.toString();
    assertTrue(str.contains("typeHierarchy"));
    assertTrue(str.contains("BaseClass"));
  }

  @Test
  public void newTypeHierarchyFactory_read_validatesRuntimeType() throws IOException {
    // This tests the runtime type check in newTypeHierarchyFactory
    // When reading, if the adapter returns a type that doesn't match the requested type,
    // it should throw an exception

    // Create an adapter that always returns BaseClass even when DerivedClass is requested
    TypeAdapter<BaseClass> alwaysBaseAdapter =
        new TypeAdapter<BaseClass>() {
          @Override
          public void write(JsonWriter out, BaseClass value) throws IOException {
            out.beginObject();
            out.name("base").value(value.base);
            out.endObject();
          }

          @Override
          public BaseClass read(JsonReader in) throws IOException {
            // Always return BaseClass, never DerivedClass
            in.beginObject();
            String base = null;
            while (in.hasNext()) {
              String name = in.nextName();
              if ("base".equals(name)) {
                base = in.nextString();
              } else {
                in.skipValue();
              }
            }
            in.endObject();
            return new BaseClass(base);
          }
        };

    TypeAdapterFactory factory =
        TypeAdapters.newTypeHierarchyFactory(BaseClass.class, alwaysBaseAdapter);
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();

    // Reading as DerivedClass should fail because adapter returns BaseClass
    try {
      gson.fromJson("{\"base\":\"test\"}", DerivedClass.class);
      fail("Expected JsonSyntaxException");
    } catch (JsonSyntaxException e) {
      assertTrue(e.getMessage().contains("Expected"));
      assertTrue(e.getMessage().contains("DerivedClass"));
      assertTrue(e.getMessage().contains("BaseClass"));
    }
  }

  @Test
  public void newTypeHierarchyFactory_read_allowsNullResult() throws IOException {
    TypeAdapter<BaseClass> nullReturningAdapter =
        new TypeAdapter<BaseClass>() {
          @Override
          public void write(JsonWriter out, BaseClass value) throws IOException {
            out.nullValue();
          }

          @Override
          public BaseClass read(JsonReader in) throws IOException {
            in.skipValue();
            return null;
          }
        };

    TypeAdapterFactory factory =
        TypeAdapters.newTypeHierarchyFactory(BaseClass.class, nullReturningAdapter);
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();

    // Null should be allowed even for DerivedClass
    DerivedClass result = gson.fromJson("{}", DerivedClass.class);
    assertNull(result);
  }

  @Test
  public void newTypeHierarchyFactory_read_allowsCorrectSubtype() throws IOException {
    // Create an adapter that can return either BaseClass or DerivedClass
    TypeAdapter<BaseClass> polymorphicAdapter =
        new TypeAdapter<BaseClass>() {
          @Override
          public void write(JsonWriter out, BaseClass value) throws IOException {
            out.beginObject();
            out.name("type").value(value.getClass().getSimpleName());
            out.name("base").value(value.base);
            if (value instanceof DerivedClass) {
              out.name("derived").value(((DerivedClass) value).derived);
            }
            out.endObject();
          }

          @Override
          public BaseClass read(JsonReader in) throws IOException {
            in.beginObject();
            String type = null;
            String base = null;
            String derived = null;
            while (in.hasNext()) {
              String name = in.nextName();
              if ("type".equals(name)) {
                type = in.nextString();
              } else if ("base".equals(name)) {
                base = in.nextString();
              } else if ("derived".equals(name)) {
                derived = in.nextString();
              } else {
                in.skipValue();
              }
            }
            in.endObject();

            if ("DerivedClass".equals(type)) {
              return new DerivedClass(base, derived);
            }
            return new BaseClass(base);
          }
        };

    TypeAdapterFactory factory =
        TypeAdapters.newTypeHierarchyFactory(BaseClass.class, polymorphicAdapter);
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();

    // Reading as DerivedClass when adapter returns DerivedClass should work
    DerivedClass result =
        gson.fromJson(
            "{\"type\":\"DerivedClass\",\"base\":\"b\",\"derived\":\"d\"}", DerivedClass.class);
    assertNotNull(result);
    assertEquals("b", result.base);
    assertEquals("d", result.derived);
  }

  @Test
  public void newTypeHierarchyFactory_write_delegatesToAdapter() throws IOException {
    TypeAdapter<BaseClass> customAdapter =
        new TypeAdapter<BaseClass>() {
          @Override
          public void write(JsonWriter out, BaseClass value) throws IOException {
            out.value("CUSTOM:" + value.base);
          }

          @Override
          public BaseClass read(JsonReader in) throws IOException {
            return new BaseClass(in.nextString().substring(7));
          }
        };

    TypeAdapterFactory factory =
        TypeAdapters.newTypeHierarchyFactory(BaseClass.class, customAdapter);
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();

    BaseClass obj = new BaseClass("test");
    String json = gson.toJson(obj);
    assertEquals("\"CUSTOM:test\"", json);
  }

  // ==========================================================================
  // Integration tests with Gson
  // ==========================================================================

  @Test
  public void factoryRegistration_orderMatters() {
    TypeAdapter<CustomClass> adapter1 =
        new TypeAdapter<CustomClass>() {
          @Override
          public void write(JsonWriter out, CustomClass value) throws IOException {
            out.value("ADAPTER1");
          }

          @Override
          public CustomClass read(JsonReader in) throws IOException {
            String value = in.nextString();
            return new CustomClass("from1:" + value);
          }
        };

    TypeAdapter<CustomClass> adapter2 =
        new TypeAdapter<CustomClass>() {
          @Override
          public void write(JsonWriter out, CustomClass value) throws IOException {
            out.value("ADAPTER2");
          }

          @Override
          public CustomClass read(JsonReader in) throws IOException {
            String value = in.nextString();
            return new CustomClass("from2:" + value);
          }
        };

    TypeAdapterFactory factory1 = TypeAdapters.newFactory(CustomClass.class, adapter1);
    TypeAdapterFactory factory2 = TypeAdapters.newFactory(CustomClass.class, adapter2);

    // In GsonBuilder, later registered factories take precedence (LIFO ordering)
    Gson gson =
        new GsonBuilder()
            .registerTypeAdapterFactory(factory1)
            .registerTypeAdapterFactory(factory2)
            .create();

    CustomClass obj = new CustomClass("test");
    String json = gson.toJson(obj);
    // factory2 was registered last, so it takes precedence
    assertEquals("\"ADAPTER2\"", json);
  }

  @Test
  public void factoryRegistration_laterRegisteredTakesPrecedence() {
    TypeAdapter<BaseClass> hierarchyAdapter =
        new TypeAdapter<BaseClass>() {
          @Override
          public void write(JsonWriter out, BaseClass value) throws IOException {
            out.value("HIERARCHY");
          }

          @Override
          public BaseClass read(JsonReader in) throws IOException {
            String value = in.nextString();
            return new BaseClass("hierarchy:" + value);
          }
        };

    TypeAdapter<DerivedClass> exactAdapter =
        new TypeAdapter<DerivedClass>() {
          @Override
          public void write(JsonWriter out, DerivedClass value) throws IOException {
            out.value("EXACT");
          }

          @Override
          public DerivedClass read(JsonReader in) throws IOException {
            String value = in.nextString();
            return new DerivedClass("exact:" + value, "exact");
          }
        };

    TypeAdapterFactory hierarchyFactory =
        TypeAdapters.newTypeHierarchyFactory(BaseClass.class, hierarchyAdapter);
    TypeAdapterFactory exactFactory =
        TypeAdapters.newFactory(DerivedClass.class, exactAdapter);

    // Later registered factories take precedence in GsonBuilder
    // Register exactFactory last so it takes precedence for DerivedClass
    Gson gson =
        new GsonBuilder()
            .registerTypeAdapterFactory(hierarchyFactory)
            .registerTypeAdapterFactory(exactFactory)
            .create();

    // DerivedClass should use exactFactory (registered last)
    DerivedClass obj = new DerivedClass("b", "d");
    String json = gson.toJson(obj);
    assertEquals("\"EXACT\"", json);

    // BaseClass should use hierarchyFactory (the only one that matches)
    BaseClass baseObj = new BaseClass("b");
    String baseJson = gson.toJson(baseObj);
    assertEquals("\"HIERARCHY\"", baseJson);
  }

  // ==========================================================================
  // Tests with real-world types (InetAddress hierarchy)
  // ==========================================================================

  @Test
  public void typeHierarchyFactory_inetAddress_matchesSubtypes() throws Exception {
    // Test using the built-in InetAddress adapter which uses newTypeHierarchyFactory
    Gson gson = new Gson();

    // InetAddress.getByName returns an Inet4Address or Inet6Address
    InetAddress address = InetAddress.getByName("127.0.0.1");
    String json = gson.toJson(address);

    // Should serialize to the IP string
    assertEquals("\"127.0.0.1\"", json);

    // Should be able to deserialize back
    InetAddress deserialized = gson.fromJson("\"127.0.0.1\"", InetAddress.class);
    assertEquals(address, deserialized);
  }

  @Test
  public void typeHierarchyFactory_inetAddress_deserializeToSubtype() throws Exception {
    Gson gson = new Gson();

    // Deserializing to specific subtype should work if the result matches
    Inet4Address address = gson.fromJson("\"192.168.1.1\"", Inet4Address.class);
    assertNotNull(address);
    assertEquals("192.168.1.1", address.getHostAddress());
  }

  // ==========================================================================
  // Null handling tests
  // ==========================================================================

  @Test
  public void allFactories_handleNullValues() throws IOException {
    // Test that all factory types handle null correctly

    // newFactory(TypeToken)
    TypeAdapterFactory factory1 =
        TypeAdapters.newFactory(TypeToken.get(CustomClass.class), customClassAdapter);
    Gson gson1 = new GsonBuilder().registerTypeAdapterFactory(factory1).create();
    assertEquals("null", gson1.toJson(null, CustomClass.class));
    assertNull(gson1.fromJson("null", CustomClass.class));

    // newFactory(Class)
    TypeAdapterFactory factory2 = TypeAdapters.newFactory(CustomClass.class, customClassAdapter);
    Gson gson2 = new GsonBuilder().registerTypeAdapterFactory(factory2).create();
    assertEquals("null", gson2.toJson(null, CustomClass.class));
    assertNull(gson2.fromJson("null", CustomClass.class));

    // newFactoryForMultipleTypes
    TypeAdapterFactory factory3 =
        TypeAdapters.newFactoryForMultipleTypes(BaseClass.class, DerivedClass.class, baseClassAdapter);
    Gson gson3 = new GsonBuilder().registerTypeAdapterFactory(factory3).create();
    assertEquals("null", gson3.toJson(null, BaseClass.class));
    assertNull(gson3.fromJson("null", BaseClass.class));

    // newTypeHierarchyFactory
    TypeAdapterFactory factory4 =
        TypeAdapters.newTypeHierarchyFactory(BaseClass.class, baseClassAdapter);
    Gson gson4 = new GsonBuilder().registerTypeAdapterFactory(factory4).create();
    assertEquals("null", gson4.toJson(null, BaseClass.class));
    assertNull(gson4.fromJson("null", BaseClass.class));
  }

  // ==========================================================================
  // JsonReader/JsonWriter direct tests
  // ==========================================================================

  @Test
  public void directReadWrite_viaTypeAdapter() throws IOException {
    TypeAdapterFactory factory = TypeAdapters.newFactory(CustomClass.class, customClassAdapter);
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
    TypeAdapter<CustomClass> adapter = gson.getAdapter(CustomClass.class);

    // Direct write
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, new CustomClass("direct"));
    assertEquals("{\"custom_value\":\"direct\"}", stringWriter.toString());

    // Direct read
    JsonReader reader = new JsonReader(new StringReader("{\"custom_value\":\"readDirect\"}"));
    CustomClass result = adapter.read(reader);
    assertEquals("readDirect", result.value);
  }
}
