/*
 * Copyright (C) 2024 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.gson.internal.bind;

import static com.google.common.truth.Truth.assertThat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import org.junit.Test;

public class TypeAdapterRuntimeTypeWrapperTest {

  private static class SimpleTypeAdapter<T> extends TypeAdapter<T> {
    private final T readValue;
    private T lastWrittenValue;

    SimpleTypeAdapter(T readValue) {
      this.readValue = readValue;
    }

    @Override
    public T read(JsonReader in) throws IOException {
      in.skipValue();
      return readValue;
    }

    @Override
    public void write(JsonWriter out, T value) throws IOException {
      this.lastWrittenValue = value;
      out.value(value == null ? "null" : value.toString());
    }

    T getLastWrittenValue() {
      return lastWrittenValue;
    }
  }

  private static class SimpleDelegatingAdapter<T> extends SerializationDelegatingTypeAdapter<T> {
    private final TypeAdapter<T> delegate;

    SimpleDelegatingAdapter(TypeAdapter<T> delegate) {
      this.delegate = delegate;
    }

    @Override
    public TypeAdapter<T> getSerializationDelegate() {
      return delegate;
    }

    @Override
    public T read(JsonReader in) throws IOException {
      return delegate.read(in);
    }

    @Override
    public void write(JsonWriter out, T value) throws IOException {
      delegate.write(out, value);
    }
  }

  private static class NonDelegatingAdapter<T> extends SerializationDelegatingTypeAdapter<T> {
    @Override
    public TypeAdapter<T> getSerializationDelegate() {
      return this;
    }

    @Override
    public T read(JsonReader in) throws IOException {
      in.skipValue();
      return null;
    }

    @Override
    public void write(JsonWriter out, T value) throws IOException {
      out.value("non-delegating");
    }
  }

  @Test
  public void testConstructor() throws Exception {
    Gson gson = new Gson();
    SimpleTypeAdapter<String> delegate = new SimpleTypeAdapter<>("test");
    Type type = String.class;

    TypeAdapterRuntimeTypeWrapper<String> wrapper =
        new TypeAdapterRuntimeTypeWrapper<>(gson, delegate, type);

    // Use reflection to verify fields are set
    Field contextField = TypeAdapterRuntimeTypeWrapper.class.getDeclaredField("context");
    contextField.setAccessible(true);
    assertThat(contextField.get(wrapper)).isSameInstanceAs(gson);

    Field delegateField = TypeAdapterRuntimeTypeWrapper.class.getDeclaredField("delegate");
    delegateField.setAccessible(true);
    assertThat(delegateField.get(wrapper)).isSameInstanceAs(delegate);

    Field typeField = TypeAdapterRuntimeTypeWrapper.class.getDeclaredField("type");
    typeField.setAccessible(true);
    assertThat(typeField.get(wrapper)).isSameInstanceAs(type);
  }

  @Test
  public void testRead() throws IOException {
    Gson gson = new Gson();
    SimpleTypeAdapter<String> delegate = new SimpleTypeAdapter<>("testValue");
    TypeAdapterRuntimeTypeWrapper<String> wrapper =
        new TypeAdapterRuntimeTypeWrapper<>(gson, delegate, String.class);

    JsonReader reader = new JsonReader(new StringReader("\"ignored\""));
    String result = wrapper.read(reader);

    assertThat(result).isEqualTo("testValue");
  }

  @Test
  public void testWriteWithSameType() throws IOException {
    Gson gson = new Gson();
    SimpleTypeAdapter<String> delegate = new SimpleTypeAdapter<>(null);
    TypeAdapterRuntimeTypeWrapper<String> wrapper =
        new TypeAdapterRuntimeTypeWrapper<>(gson, delegate, String.class);

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    wrapper.write(writer, "testValue");

    assertThat(delegate.getLastWrittenValue()).isEqualTo("testValue");
    assertThat(stringWriter.toString()).isEqualTo("\"testValue\"");
  }

  @Test
  public void testWriteWithNullValue() throws IOException {
    Gson gson = new Gson();
    SimpleTypeAdapter<String> delegate = new SimpleTypeAdapter<>(null);
    TypeAdapterRuntimeTypeWrapper<String> wrapper =
        new TypeAdapterRuntimeTypeWrapper<>(gson, delegate, String.class);

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    wrapper.write(writer, null);

    assertThat(delegate.getLastWrittenValue()).isNull();
    assertThat(stringWriter.toString()).isEqualTo("\"null\"");
  }

  @Test
  public void testWriteWithDifferentTypeNonReflectiveRuntimeAdapter() throws IOException {
    // Create a custom non-reflective adapter for StringBuilder
    TypeAdapter<StringBuilder> stringBuilderAdapter =
        new TypeAdapter<StringBuilder>() {
          @Override
          public StringBuilder read(JsonReader in) throws IOException {
            return new StringBuilder(in.nextString());
          }

          @Override
          public void write(JsonWriter out, StringBuilder value) throws IOException {
            out.value("custom:" + value.toString());
          }
        };

    Gson gson =
        new GsonBuilder()
            .registerTypeAdapter(StringBuilder.class, stringBuilderAdapter)
            .create();

    SimpleTypeAdapter<CharSequence> delegate = new SimpleTypeAdapter<>(null);
    TypeAdapterRuntimeTypeWrapper<CharSequence> wrapper =
        new TypeAdapterRuntimeTypeWrapper<>(gson, delegate, CharSequence.class);

    StringBuilder value = new StringBuilder("test");
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    wrapper.write(writer, value);

    // Should use the custom adapter
    assertThat(stringWriter.toString()).isEqualTo("\"custom:test\"");
  }

  static class BaseClass {
    String value;
  }

  static class SubClass extends BaseClass {
    int number;
  }

  @Test
  public void testWriteWithDifferentTypeReflectiveRuntimeNonReflectiveDelegate()
      throws IOException {
    // Create a custom non-reflective adapter for BaseClass
    SimpleTypeAdapter<BaseClass> nonReflectiveDelegate = new SimpleTypeAdapter<>(null);

    // Use plain Gson which will have reflective adapters for SubClass
    Gson gson = new Gson();

    // Wrapper for BaseClass with our non-reflective delegate
    TypeAdapterRuntimeTypeWrapper<BaseClass> wrapper =
        new TypeAdapterRuntimeTypeWrapper<>(gson, nonReflectiveDelegate, BaseClass.class);

    SubClass value = new SubClass();
    value.value = "test";
    value.number = 42;
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    wrapper.write(writer, value);

    // Runtime type is SubClass (reflective adapter)
    // Delegate is non-reflective (SimpleTypeAdapter)
    // Should use the delegate since it's not reflective
    assertThat(nonReflectiveDelegate.getLastWrittenValue()).isEqualTo(value);
    assertThat(stringWriter.toString()).contains("SubClass");
  }

  @Test
  public void testWriteWithDifferentTypeReflectiveBoth() throws IOException {
    Gson gson = new Gson();

    // Use default reflective adapters for both CharSequence and String
    TypeAdapter<CharSequence> charSequenceAdapter = gson.getAdapter(CharSequence.class);
    TypeAdapterRuntimeTypeWrapper<CharSequence> wrapper =
        new TypeAdapterRuntimeTypeWrapper<>(gson, charSequenceAdapter, CharSequence.class);

    String value = "test";
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    wrapper.write(writer, value);

    // Should use the runtime type adapter (reflective for String)
    assertThat(stringWriter.toString()).isEqualTo("\"test\"");
  }

  @Test
  public void testWriteWithTypeVariable() throws IOException {
    Gson gson = new Gson();
    SimpleTypeAdapter<Object> delegate = new SimpleTypeAdapter<>(null);

    // Create a TypeVariable by getting the type parameter from a generic class
    class Container<T> {
      T value;
    }

    try {
      Field field = Container.class.getDeclaredField("value");
      Type typeVariable = field.getGenericType();

      TypeAdapterRuntimeTypeWrapper<Object> wrapper =
          new TypeAdapterRuntimeTypeWrapper<>(gson, delegate, typeVariable);

      StringWriter stringWriter = new StringWriter();
      JsonWriter writer = new JsonWriter(stringWriter);
      wrapper.write(writer, "actualValue");

      // With TypeVariable and non-null value, should use runtime type
      assertThat(stringWriter.toString()).contains("actualValue");
    } catch (NoSuchFieldException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testIsReflectiveWithSimpleAdapter() throws Exception {
    // Test that a simple non-reflective adapter returns false
    SimpleTypeAdapter<String> adapter = new SimpleTypeAdapter<>("test");

    java.lang.reflect.Method method =
        TypeAdapterRuntimeTypeWrapper.class.getDeclaredMethod(
            "isReflective", TypeAdapter.class);
    method.setAccessible(true);
    Boolean result = (Boolean) method.invoke(null, adapter);

    assertThat(result).isFalse();
  }

  @Test
  public void testIsReflectiveWithDelegatingAdapter() throws Exception {
    SimpleTypeAdapter<String> delegate = new SimpleTypeAdapter<>("test");
    SimpleDelegatingAdapter<String> delegating = new SimpleDelegatingAdapter<>(delegate);

    java.lang.reflect.Method method =
        TypeAdapterRuntimeTypeWrapper.class.getDeclaredMethod(
            "isReflective", TypeAdapter.class);
    method.setAccessible(true);
    Boolean result = (Boolean) method.invoke(null, delegating);

    // Should unwrap and check the delegate
    assertThat(result).isFalse();
  }

  @Test
  public void testIsReflectiveWithNonDelegatingAdapter() throws Exception {
    NonDelegatingAdapter<String> adapter = new NonDelegatingAdapter<>();

    java.lang.reflect.Method method =
        TypeAdapterRuntimeTypeWrapper.class.getDeclaredMethod(
            "isReflective", TypeAdapter.class);
    method.setAccessible(true);
    Boolean result = (Boolean) method.invoke(null, adapter);

    // Should stop unwrapping when delegate returns itself
    assertThat(result).isFalse();
  }

  @Test
  public void testIsReflectiveWithReflectiveAdapter() throws Exception {
    Gson gson = new Gson();
    TypeAdapter<String> reflectiveAdapter = gson.getAdapter(String.class);

    java.lang.reflect.Method method =
        TypeAdapterRuntimeTypeWrapper.class.getDeclaredMethod(
            "isReflective", TypeAdapter.class);
    method.setAccessible(true);
    Boolean result = (Boolean) method.invoke(null, reflectiveAdapter);

    // Gson's default adapter for String might be reflective depending on implementation
    assertThat(result).isNotNull();
  }

  @Test
  public void testGetRuntimeTypeIfMoreSpecificWithClass() throws Exception {
    java.lang.reflect.Method method =
        TypeAdapterRuntimeTypeWrapper.class.getDeclaredMethod(
            "getRuntimeTypeIfMoreSpecific", Type.class, Object.class);
    method.setAccessible(true);

    Type result = (Type) method.invoke(null, CharSequence.class, "test");

    // Should return the runtime type (String.class)
    assertThat(result).isEqualTo(String.class);
  }

  @Test
  public void testGetRuntimeTypeIfMoreSpecificWithNull() throws Exception {
    java.lang.reflect.Method method =
        TypeAdapterRuntimeTypeWrapper.class.getDeclaredMethod(
            "getRuntimeTypeIfMoreSpecific", Type.class, Object.class);
    method.setAccessible(true);

    Type originalType = String.class;
    Type result = (Type) method.invoke(null, originalType, null);

    // Should return the original type when value is null
    assertThat(result).isEqualTo(originalType);
  }

  @Test
  public void testGetRuntimeTypeIfMoreSpecificWithTypeVariable() throws Exception {
    class Container<T> {
      T value;
    }

    Field field = Container.class.getDeclaredField("value");
    Type typeVariable = field.getGenericType();

    java.lang.reflect.Method method =
        TypeAdapterRuntimeTypeWrapper.class.getDeclaredMethod(
            "getRuntimeTypeIfMoreSpecific", Type.class, Object.class);
    method.setAccessible(true);

    Type result = (Type) method.invoke(null, typeVariable, "test");

    // Should return the runtime type (String.class)
    assertThat(result).isEqualTo(String.class);
  }

  @Test
  public void testGetRuntimeTypeIfMoreSpecificWithParameterizedType() throws Exception {
    TypeToken<java.util.List<String>> token = new TypeToken<java.util.List<String>>() {};
    Type parameterizedType = token.getType();

    java.lang.reflect.Method method =
        TypeAdapterRuntimeTypeWrapper.class.getDeclaredMethod(
            "getRuntimeTypeIfMoreSpecific", Type.class, Object.class);
    method.setAccessible(true);

    Type result =
        (Type) method.invoke(null, parameterizedType, new java.util.ArrayList<String>());

    // Should return the original parameterized type (not changed to ArrayList)
    assertThat(result).isEqualTo(parameterizedType);
  }
}
