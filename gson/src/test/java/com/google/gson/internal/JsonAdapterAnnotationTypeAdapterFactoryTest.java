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

package com.google.gson.internal;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;

public final class JsonAdapterAnnotationTypeAdapterFactoryTest {

  private ConstructorConstructor constructorConstructor;
  private JsonAdapterAnnotationTypeAdapterFactory adapterFactory;
  private Gson gson;

  @Before
  public void setUp() {
    constructorConstructor =
        new ConstructorConstructor(
            Collections.<Type, com.google.gson.InstanceCreator<?>>emptyMap(),
            true,
            Collections.<com.google.gson.ReflectionAccessFilter>emptyList());
    adapterFactory = new JsonAdapterAnnotationTypeAdapterFactory(constructorConstructor);
    gson = new GsonBuilder().create();
  }

  @Test
  public void testCreateReturnsNullForNonAnnotatedClass() {
    TypeAdapter<?> adapter = adapterFactory.create(gson, TypeToken.get(NonAnnotatedClass.class));
    assertThat(adapter).isNull();
  }

  @Test
  public void testCreateReturnsAdapterForTypeAdapterAnnotation() {
    TypeAdapter<?> adapter =
        adapterFactory.create(gson, TypeToken.get(ClassWithTypeAdapterAnnotation.class));
    assertThat(adapter).isNotNull();
  }

  @Test
  public void testCreateReturnsAdapterForTypeAdapterFactoryAnnotation() {
    TypeAdapter<?> adapter =
        adapterFactory.create(gson, TypeToken.get(ClassWithFactoryAnnotation.class));
    assertThat(adapter).isNotNull();
  }

  @Test
  public void testCreateReturnsAdapterForJsonSerializerAnnotation() {
    TypeAdapter<?> adapter =
        adapterFactory.create(gson, TypeToken.get(ClassWithSerializerAnnotation.class));
    assertThat(adapter).isNotNull();
  }

  @Test
  public void testCreateReturnsAdapterForJsonDeserializerAnnotation() {
    TypeAdapter<?> adapter =
        adapterFactory.create(gson, TypeToken.get(ClassWithDeserializerAnnotation.class));
    assertThat(adapter).isNotNull();
  }

  @Test
  public void testCreateWithInvalidAdapterClassThrowsIllegalArgumentException() {
    assertThrows(
        IllegalArgumentException.class,
        () -> adapterFactory.create(gson, TypeToken.get(ClassWithInvalidAnnotation.class)));
  }

  @Test
  public void testCreateWrapsAdapterInNullSafe() throws IOException {
    TypeAdapter<ClassWithNullSafeAdapter> adapter =
        adapterFactory.create(gson, TypeToken.get(ClassWithNullSafeAdapter.class));
    assertThat(adapter).isNotNull();
    // nullSafe wrapping means writing null produces "null" without calling the adapter
    java.io.StringWriter sw = new java.io.StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    adapter.write(writer, null);
    writer.flush();
    assertThat(sw.toString()).isEqualTo("null");
  }

  @Test
  public void testIsClassJsonAdapterFactoryThrowsForNullType() {
    TypeAdapterFactory factory = new SimpleTypeAdapterFactory();
    assertThrows(
        NullPointerException.class,
        () -> adapterFactory.isClassJsonAdapterFactory(null, factory));
  }

  @Test
  public void testIsClassJsonAdapterFactoryThrowsForNullFactory() {
    assertThrows(
        NullPointerException.class,
        () ->
            adapterFactory.isClassJsonAdapterFactory(
                TypeToken.get(ClassWithFactoryAnnotation.class), null));
  }

  @Test
  public void testIsClassJsonAdapterFactoryReturnsFalseForNonAnnotatedClass() {
    TypeAdapterFactory factory = new SimpleTypeAdapterFactory();
    boolean result =
        adapterFactory.isClassJsonAdapterFactory(
            TypeToken.get(NonAnnotatedClass.class), factory);
    assertThat(result).isFalse();
  }

  @Test
  public void testIsClassJsonAdapterFactoryReturnsFalseForTypeAdapterAnnotation() {
    TypeAdapterFactory factory = new SimpleTypeAdapterFactory();
    boolean result =
        adapterFactory.isClassJsonAdapterFactory(
            TypeToken.get(ClassWithTypeAdapterAnnotation.class), factory);
    assertThat(result).isFalse();
  }

  @Test
  public void testIsClassJsonAdapterFactoryReturnsFalseForWrongFactory() {
    TypeAdapterFactory wrongFactory = new SimpleTypeAdapterFactory();
    // First call creates and stores a factory in the map
    adapterFactory.isClassJsonAdapterFactory(
        TypeToken.get(ClassWithFactoryAnnotation.class), wrongFactory);
    // Second call with a different factory should also return false
    TypeAdapterFactory anotherFactory = new SimpleTypeAdapterFactory();
    boolean result =
        adapterFactory.isClassJsonAdapterFactory(
            TypeToken.get(ClassWithFactoryAnnotation.class), anotherFactory);
    assertThat(result).isFalse();
  }

  @Test
  public void testIsClassJsonAdapterFactoryReturnsTrueForCachedFactory() {
    // Use singleton factory so we can get a reference to the stored instance
    TypeToken<ClassWithSingletonFactoryAnnotation> type =
        TypeToken.get(ClassWithSingletonFactoryAnnotation.class);
    SingletonTypeAdapterFactory wrongFactory = new SingletonTypeAdapterFactory();
    // First call: creates factory via ConstructorConstructor, stores it, captures in lastCreated
    adapterFactory.isClassJsonAdapterFactory(type, wrongFactory);
    SingletonTypeAdapterFactory storedFactory = SingletonTypeAdapterFactory.lastCreated;
    // Second call with the stored factory reference should return true
    boolean result = adapterFactory.isClassJsonAdapterFactory(type, storedFactory);
    assertThat(result).isTrue();
  }

  @Test
  public void testConstructorCreatesInstance() {
    JsonAdapterAnnotationTypeAdapterFactory factory =
        new JsonAdapterAnnotationTypeAdapterFactory(constructorConstructor);
    assertThat(factory).isNotNull();
  }

  // Test model classes

  private static class NonAnnotatedClass {}

  @JsonAdapter(NullSafeTypeAdapter.class)
  private static class ClassWithNullSafeAdapter {}

  @JsonAdapter(SimpleTypeAdapter.class)
  private static class ClassWithTypeAdapterAnnotation {}

  @JsonAdapter(SimpleTypeAdapterFactory.class)
  private static class ClassWithFactoryAnnotation {}

  @JsonAdapter(SimpleJsonSerializer.class)
  private static class ClassWithSerializerAnnotation {}

  @JsonAdapter(SimpleJsonDeserializer.class)
  private static class ClassWithDeserializerAnnotation {}

  @JsonAdapter(String.class)
  private static class ClassWithInvalidAnnotation {}

  @JsonAdapter(SingletonTypeAdapterFactory.class)
  private static class ClassWithSingletonFactoryAnnotation {}

  static class SimpleTypeAdapter extends TypeAdapter<Object> {
    @Override
    public void write(JsonWriter out, Object value) throws IOException {
      out.nullValue();
    }

    @Override
    public Object read(JsonReader in) throws IOException {
      in.skipValue();
      return null;
    }
  }

  static class NullSafeTypeAdapter extends TypeAdapter<ClassWithNullSafeAdapter> {
    @Override
    public void write(JsonWriter out, ClassWithNullSafeAdapter value) throws IOException {
      out.beginObject();
      out.endObject();
    }

    @Override
    public ClassWithNullSafeAdapter read(JsonReader in) throws IOException {
      in.skipValue();
      return null;
    }
  }

  static class SimpleTypeAdapterFactory implements TypeAdapterFactory {
    @Override
    @SuppressWarnings("unchecked")
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
      return (TypeAdapter<T>) new SimpleTypeAdapter();
    }
  }

  static class SimpleJsonSerializer implements JsonSerializer<Object> {
    @Override
    public JsonElement serialize(Object src, Type typeOfSrc, JsonSerializationContext context) {
      return context.serialize(src);
    }
  }

  static class SimpleJsonDeserializer implements JsonDeserializer<Object> {
    @Override
    public Object deserialize(
        JsonElement json, Type typeOfT, JsonDeserializationContext context) {
      return null;
    }
  }

  static class SingletonTypeAdapterFactory implements TypeAdapterFactory {
    static volatile SingletonTypeAdapterFactory lastCreated;

    SingletonTypeAdapterFactory() {
      lastCreated = this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
      return (TypeAdapter<T>) new SimpleTypeAdapter();
    }
  }
}
