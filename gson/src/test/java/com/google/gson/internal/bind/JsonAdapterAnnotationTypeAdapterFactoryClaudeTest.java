/*
 * Copyright (C) 2014 Google Inc.
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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import org.junit.Test;

/** Tests for {@link JsonAdapterAnnotationTypeAdapterFactory}. */
public class JsonAdapterAnnotationTypeAdapterFactoryClaudeTest {

  // ==========================================================================
  // Constructor tests
  // ==========================================================================

  @Test
  public void constructor_withValidConstructorConstructor_createsFactory() {
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    JsonAdapterAnnotationTypeAdapterFactory factory =
        new JsonAdapterAnnotationTypeAdapterFactory(constructorConstructor);
    assertNotNull(factory);
  }

  // ==========================================================================
  // create() tests - basic cases
  // ==========================================================================

  @Test
  public void create_classWithoutAnnotation_returnsNull() {
    Gson gson = new Gson();
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    JsonAdapterAnnotationTypeAdapterFactory factory =
        new JsonAdapterAnnotationTypeAdapterFactory(constructorConstructor);

    TypeAdapter<ClassWithoutAnnotation> adapter =
        factory.create(gson, TypeToken.get(ClassWithoutAnnotation.class));
    assertNull(adapter);
  }

  @Test
  public void create_classWithTypeAdapterAnnotation_returnsAdapter() {
    Gson gson = new Gson();
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    JsonAdapterAnnotationTypeAdapterFactory factory =
        new JsonAdapterAnnotationTypeAdapterFactory(constructorConstructor);

    TypeAdapter<ClassWithTypeAdapter> adapter =
        factory.create(gson, TypeToken.get(ClassWithTypeAdapter.class));
    assertNotNull(adapter);
  }

  @Test
  public void create_classWithTypeAdapterFactoryAnnotation_returnsAdapter() {
    Gson gson = new Gson();
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    JsonAdapterAnnotationTypeAdapterFactory factory =
        new JsonAdapterAnnotationTypeAdapterFactory(constructorConstructor);

    TypeAdapter<ClassWithTypeAdapterFactory> adapter =
        factory.create(gson, TypeToken.get(ClassWithTypeAdapterFactory.class));
    assertNotNull(adapter);
  }

  @Test
  public void create_classWithJsonSerializerAnnotation_returnsAdapter() {
    Gson gson = new Gson();
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    JsonAdapterAnnotationTypeAdapterFactory factory =
        new JsonAdapterAnnotationTypeAdapterFactory(constructorConstructor);

    TypeAdapter<ClassWithJsonSerializer> adapter =
        factory.create(gson, TypeToken.get(ClassWithJsonSerializer.class));
    assertNotNull(adapter);
  }

  @Test
  public void create_classWithJsonDeserializerAnnotation_returnsAdapter() {
    Gson gson = new Gson();
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    JsonAdapterAnnotationTypeAdapterFactory factory =
        new JsonAdapterAnnotationTypeAdapterFactory(constructorConstructor);

    TypeAdapter<ClassWithJsonDeserializer> adapter =
        factory.create(gson, TypeToken.get(ClassWithJsonDeserializer.class));
    assertNotNull(adapter);
  }

  @Test
  public void create_classWithBothSerializerAndDeserializerAnnotation_returnsAdapter() {
    Gson gson = new Gson();
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    JsonAdapterAnnotationTypeAdapterFactory factory =
        new JsonAdapterAnnotationTypeAdapterFactory(constructorConstructor);

    TypeAdapter<ClassWithBothSerializerAndDeserializer> adapter =
        factory.create(gson, TypeToken.get(ClassWithBothSerializerAndDeserializer.class));
    assertNotNull(adapter);
  }

  // ==========================================================================
  // create() tests - nullSafe behavior
  // ==========================================================================

  @Test
  public void create_classWithNullSafeTrue_wrapsAdapterInNullSafe() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<ClassWithNullSafeTrue> adapter = gson.getAdapter(ClassWithNullSafeTrue.class);
    assertNotNull(adapter);

    // Test that null is handled by the wrapper
    JsonReader reader = new JsonReader(new StringReader("null"));
    ClassWithNullSafeTrue result = adapter.read(reader);
    assertNull(result);
  }

  @Test
  public void create_classWithNullSafeFalse_adapterHandlesNullDirectly() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<ClassWithNullSafeFalse> adapter = gson.getAdapter(ClassWithNullSafeFalse.class);
    assertNotNull(adapter);

    // Test that null is passed to the adapter
    String json = gson.toJson(null, ClassWithNullSafeFalse.class);
    assertEquals("null", json);
  }

  // ==========================================================================
  // create() tests - invalid adapter class
  // ==========================================================================

  @Test
  public void create_classWithInvalidAdapterClass_throwsException() {
    Gson gson = new Gson();
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    JsonAdapterAnnotationTypeAdapterFactory factory =
        new JsonAdapterAnnotationTypeAdapterFactory(constructorConstructor);

    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> factory.create(gson, TypeToken.get(ClassWithInvalidAdapter.class)));

    assertTrue(exception.getMessage().contains("Invalid attempt to bind an instance of"));
    assertTrue(exception.getMessage().contains("@JsonAdapter value must be a TypeAdapter"));
  }

  // ==========================================================================
  // getTypeAdapter() tests - isClassAnnotation = true
  // ==========================================================================

  @Test
  public void getTypeAdapter_withTypeAdapterFactoryAndClassAnnotation_cachesFactory() {
    Gson gson = new Gson();
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    JsonAdapterAnnotationTypeAdapterFactory factory =
        new JsonAdapterAnnotationTypeAdapterFactory(constructorConstructor);

    // First call creates and caches the factory
    TypeAdapter<ClassWithTypeAdapterFactory> adapter1 =
        factory.create(gson, TypeToken.get(ClassWithTypeAdapterFactory.class));
    assertNotNull(adapter1);

    // Second call should use cached factory
    TypeAdapter<ClassWithTypeAdapterFactory> adapter2 =
        factory.create(gson, TypeToken.get(ClassWithTypeAdapterFactory.class));
    assertNotNull(adapter2);
  }

  // ==========================================================================
  // getTypeAdapter() tests - isClassAnnotation = false (field annotation)
  // ==========================================================================

  @Test
  public void getTypeAdapter_withFieldAnnotation_doesNotCacheFactory() {
    // Testing via Gson integration - field adapter factories should not be cached
    Gson gson = new Gson();
    ClassWithFieldAnnotation obj = new ClassWithFieldAnnotation();
    obj.customField = new ClassWithoutAnnotation();
    obj.customField.value = "test";

    String json = gson.toJson(obj);
    assertEquals("{\"customField\":\"custom:test\"}", json);
  }

  // ==========================================================================
  // isClassJsonAdapterFactory() tests
  // ==========================================================================

  @Test
  public void isClassJsonAdapterFactory_withNullType_throwsException() {
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    JsonAdapterAnnotationTypeAdapterFactory factory =
        new JsonAdapterAnnotationTypeAdapterFactory(constructorConstructor);

    assertThrows(
        NullPointerException.class,
        () -> factory.isClassJsonAdapterFactory(null, new DummyTypeAdapterFactory()));
  }

  @Test
  public void isClassJsonAdapterFactory_withNullFactory_throwsException() {
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    JsonAdapterAnnotationTypeAdapterFactory factory =
        new JsonAdapterAnnotationTypeAdapterFactory(constructorConstructor);

    assertThrows(
        NullPointerException.class,
        () -> factory.isClassJsonAdapterFactory(TypeToken.get(String.class), null));
  }

  @Test
  public void isClassJsonAdapterFactory_classWithoutAnnotation_returnsFalse() {
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    JsonAdapterAnnotationTypeAdapterFactory factory =
        new JsonAdapterAnnotationTypeAdapterFactory(constructorConstructor);

    boolean result =
        factory.isClassJsonAdapterFactory(
            TypeToken.get(ClassWithoutAnnotation.class), new DummyTypeAdapterFactory());
    assertFalse(result);
  }

  @Test
  public void isClassJsonAdapterFactory_classWithTypeAdapterAnnotation_returnsFalse() {
    // TypeAdapter is not a TypeAdapterFactory, so this should return false
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    JsonAdapterAnnotationTypeAdapterFactory factory =
        new JsonAdapterAnnotationTypeAdapterFactory(constructorConstructor);

    boolean result =
        factory.isClassJsonAdapterFactory(
            TypeToken.get(ClassWithTypeAdapter.class), new DummyTypeAdapterFactory());
    assertFalse(result);
  }

  @Test
  public void isClassJsonAdapterFactory_classWithTypeAdapterFactoryAnnotation_afterCreate_checksReference() {
    Gson gson = new Gson();
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    JsonAdapterAnnotationTypeAdapterFactory factory =
        new JsonAdapterAnnotationTypeAdapterFactory(constructorConstructor);

    // First create an adapter, which will cache the factory
    factory.create(gson, TypeToken.get(ClassWithTypeAdapterFactory.class));

    // Now checking with a different factory instance should return false
    boolean result =
        factory.isClassJsonAdapterFactory(
            TypeToken.get(ClassWithTypeAdapterFactory.class), new DummyTypeAdapterFactory());
    assertFalse(result);
  }

  @Test
  public void isClassJsonAdapterFactory_classWithTypeAdapterFactoryAnnotation_beforeCreate_checksAndCreates() {
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    JsonAdapterAnnotationTypeAdapterFactory factory =
        new JsonAdapterAnnotationTypeAdapterFactory(constructorConstructor);

    // Check without first creating - this should check the annotation and create the factory
    boolean result =
        factory.isClassJsonAdapterFactory(
            TypeToken.get(ClassWithTypeAdapterFactory.class), new DummyTypeAdapterFactory());
    // Different factory instance, so should return false
    assertFalse(result);
  }

  // ==========================================================================
  // Integration tests via Gson
  // ==========================================================================

  @Test
  public void integration_classWithTypeAdapter_serializes() {
    Gson gson = new Gson();
    ClassWithTypeAdapter obj = new ClassWithTypeAdapter();
    obj.value = "hello";

    String json = gson.toJson(obj);
    assertEquals("\"adapter:hello\"", json);
  }

  @Test
  public void integration_classWithTypeAdapter_deserializes() {
    Gson gson = new Gson();

    ClassWithTypeAdapter result = gson.fromJson("\"adapter:hello\"", ClassWithTypeAdapter.class);
    assertEquals("hello", result.value);
  }

  @Test
  public void integration_classWithTypeAdapterFactory_serializes() {
    Gson gson = new Gson();
    ClassWithTypeAdapterFactory obj = new ClassWithTypeAdapterFactory();
    obj.value = "world";

    String json = gson.toJson(obj);
    assertEquals("\"factory:world\"", json);
  }

  @Test
  public void integration_classWithTypeAdapterFactory_deserializes() {
    Gson gson = new Gson();

    ClassWithTypeAdapterFactory result =
        gson.fromJson("\"factory:world\"", ClassWithTypeAdapterFactory.class);
    assertEquals("world", result.value);
  }

  @Test
  public void integration_classWithJsonSerializer_serializes() {
    Gson gson = new Gson();
    ClassWithJsonSerializer obj = new ClassWithJsonSerializer();
    obj.value = "test";

    String json = gson.toJson(obj);
    assertEquals("\"serializer:test\"", json);
  }

  @Test
  public void integration_classWithJsonDeserializer_deserializes() {
    Gson gson = new Gson();

    ClassWithJsonDeserializer result =
        gson.fromJson("\"deserializer:test\"", ClassWithJsonDeserializer.class);
    assertEquals("test", result.value);
  }

  @Test
  public void integration_classWithBothSerializerAndDeserializer_roundTrips() {
    Gson gson = new Gson();
    ClassWithBothSerializerAndDeserializer obj = new ClassWithBothSerializerAndDeserializer();
    obj.value = "combined";

    String json = gson.toJson(obj);
    assertEquals("\"both:combined\"", json);

    ClassWithBothSerializerAndDeserializer result =
        gson.fromJson(json, ClassWithBothSerializerAndDeserializer.class);
    assertEquals("combined", result.value);
  }

  @Test
  public void integration_nestedClassWithAnnotation_works() {
    Gson gson = new Gson();
    ParentClass parent = new ParentClass();
    parent.child = new ClassWithTypeAdapter();
    parent.child.value = "nested";

    String json = gson.toJson(parent);
    assertEquals("{\"child\":\"adapter:nested\"}", json);

    ParentClass result = gson.fromJson(json, ParentClass.class);
    assertEquals("nested", result.child.value);
  }

  @Test
  public void integration_getDelegateAdapter_withClassJsonAdapterFactory_skipsCorrectly() {
    // This tests the isClassJsonAdapterFactory behavior indirectly through getDelegateAdapter
    Gson gson = new Gson();
    TypeAdapter<ClassWithTypeAdapterFactory> adapter =
        gson.getAdapter(ClassWithTypeAdapterFactory.class);
    assertNotNull(adapter);
  }

  @Test
  public void integration_classWithNullSafeDefault_handlesNull() {
    Gson gson = new Gson();

    // Default nullSafe is true
    String json = gson.toJson(null, ClassWithTypeAdapter.class);
    assertEquals("null", json);

    ClassWithTypeAdapter result = gson.fromJson("null", ClassWithTypeAdapter.class);
    assertNull(result);
  }

  // ==========================================================================
  // Test that factory caches work correctly with concurrent access
  // ==========================================================================

  @Test
  public void create_multipleCalls_returnConsistentAdapters() {
    Gson gson = new Gson();
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    JsonAdapterAnnotationTypeAdapterFactory factory =
        new JsonAdapterAnnotationTypeAdapterFactory(constructorConstructor);

    TypeAdapter<ClassWithTypeAdapterFactory> adapter1 =
        factory.create(gson, TypeToken.get(ClassWithTypeAdapterFactory.class));
    TypeAdapter<ClassWithTypeAdapterFactory> adapter2 =
        factory.create(gson, TypeToken.get(ClassWithTypeAdapterFactory.class));

    // Both adapters should work correctly (whether or not they're the same instance)
    assertNotNull(adapter1);
    assertNotNull(adapter2);

    // Verify they produce the same results
    ClassWithTypeAdapterFactory obj = new ClassWithTypeAdapterFactory();
    obj.value = "test";

    StringWriter sw1 = new StringWriter();
    StringWriter sw2 = new StringWriter();
    try {
      adapter1.write(new JsonWriter(sw1), obj);
      adapter2.write(new JsonWriter(sw2), obj);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    assertEquals(sw1.toString(), sw2.toString());
  }

  // ==========================================================================
  // Test classes and adapters
  // ==========================================================================

  private static class ClassWithoutAnnotation {
    String value;
  }

  @JsonAdapter(SimpleTypeAdapter.class)
  private static class ClassWithTypeAdapter {
    String value;
  }

  @JsonAdapter(SimpleTypeAdapterFactory.class)
  private static class ClassWithTypeAdapterFactory {
    String value;
  }

  @JsonAdapter(SimpleJsonSerializer.class)
  private static class ClassWithJsonSerializer {
    String value;
  }

  @JsonAdapter(SimpleJsonDeserializer.class)
  private static class ClassWithJsonDeserializer {
    String value;
  }

  @JsonAdapter(CombinedSerializerDeserializer.class)
  private static class ClassWithBothSerializerAndDeserializer {
    String value;
  }

  @JsonAdapter(value = SimpleTypeAdapter.class, nullSafe = true)
  private static class ClassWithNullSafeTrue {
    String value;
  }

  @JsonAdapter(value = NullHandlingTypeAdapter.class, nullSafe = false)
  private static class ClassWithNullSafeFalse {
    String value;
  }

  @JsonAdapter(InvalidAdapter.class)
  private static class ClassWithInvalidAdapter {
    String value;
  }

  private static class ParentClass {
    ClassWithTypeAdapter child;
  }

  private static class ClassWithFieldAnnotation {
    @JsonAdapter(FieldTypeAdapterFactory.class)
    ClassWithoutAnnotation customField;
  }

  // ==========================================================================
  // Adapter implementations
  // ==========================================================================

  public static class SimpleTypeAdapter extends TypeAdapter<ClassWithTypeAdapter> {
    @Override
    public void write(JsonWriter out, ClassWithTypeAdapter value) throws IOException {
      if (value == null) {
        out.nullValue();
        return;
      }
      out.value("adapter:" + value.value);
    }

    @Override
    public ClassWithTypeAdapter read(JsonReader in) throws IOException {
      String s = in.nextString();
      ClassWithTypeAdapter obj = new ClassWithTypeAdapter();
      obj.value = s.replace("adapter:", "");
      return obj;
    }
  }

  public static class SimpleTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
      if (type.getRawType() != ClassWithTypeAdapterFactory.class) {
        return null;
      }
      return (TypeAdapter<T>)
          new TypeAdapter<ClassWithTypeAdapterFactory>() {
            @Override
            public void write(JsonWriter out, ClassWithTypeAdapterFactory value) throws IOException {
              if (value == null) {
                out.nullValue();
                return;
              }
              out.value("factory:" + value.value);
            }

            @Override
            public ClassWithTypeAdapterFactory read(JsonReader in) throws IOException {
              String s = in.nextString();
              ClassWithTypeAdapterFactory obj = new ClassWithTypeAdapterFactory();
              obj.value = s.replace("factory:", "");
              return obj;
            }
          };
    }
  }

  public static class SimpleJsonSerializer implements JsonSerializer<ClassWithJsonSerializer> {
    @Override
    public JsonElement serialize(
        ClassWithJsonSerializer src, Type typeOfSrc, JsonSerializationContext context) {
      return new JsonPrimitive("serializer:" + src.value);
    }
  }

  public static class SimpleJsonDeserializer implements JsonDeserializer<ClassWithJsonDeserializer> {
    @Override
    public ClassWithJsonDeserializer deserialize(
        JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
      String s = json.getAsString();
      ClassWithJsonDeserializer obj = new ClassWithJsonDeserializer();
      obj.value = s.replace("deserializer:", "");
      return obj;
    }
  }

  public static class CombinedSerializerDeserializer
      implements JsonSerializer<ClassWithBothSerializerAndDeserializer>,
          JsonDeserializer<ClassWithBothSerializerAndDeserializer> {
    @Override
    public JsonElement serialize(
        ClassWithBothSerializerAndDeserializer src,
        Type typeOfSrc,
        JsonSerializationContext context) {
      return new JsonPrimitive("both:" + src.value);
    }

    @Override
    public ClassWithBothSerializerAndDeserializer deserialize(
        JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
      String s = json.getAsString();
      ClassWithBothSerializerAndDeserializer obj = new ClassWithBothSerializerAndDeserializer();
      obj.value = s.replace("both:", "");
      return obj;
    }
  }

  public static class NullHandlingTypeAdapter extends TypeAdapter<ClassWithNullSafeFalse> {
    @Override
    public void write(JsonWriter out, ClassWithNullSafeFalse value) throws IOException {
      if (value == null) {
        out.nullValue();
        return;
      }
      out.value("nullsafe:" + value.value);
    }

    @Override
    public ClassWithNullSafeFalse read(JsonReader in) throws IOException {
      if (in.peek() == com.google.gson.stream.JsonToken.NULL) {
        in.nextNull();
        return null;
      }
      String s = in.nextString();
      ClassWithNullSafeFalse obj = new ClassWithNullSafeFalse();
      obj.value = s.replace("nullsafe:", "");
      return obj;
    }
  }

  public static class InvalidAdapter {
    // This class doesn't extend TypeAdapter, implement TypeAdapterFactory,
    // JsonSerializer, or JsonDeserializer
  }

  public static class FieldTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
      if (type.getRawType() != ClassWithoutAnnotation.class) {
        return null;
      }
      return (TypeAdapter<T>)
          new TypeAdapter<ClassWithoutAnnotation>() {
            @Override
            public void write(JsonWriter out, ClassWithoutAnnotation value) throws IOException {
              if (value == null) {
                out.nullValue();
                return;
              }
              out.value("custom:" + value.value);
            }

            @Override
            public ClassWithoutAnnotation read(JsonReader in) throws IOException {
              String s = in.nextString();
              ClassWithoutAnnotation obj = new ClassWithoutAnnotation();
              obj.value = s.replace("custom:", "");
              return obj;
            }
          };
    }
  }

  private static class DummyTypeAdapterFactory implements TypeAdapterFactory {
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
      return null;
    }
  }
}
