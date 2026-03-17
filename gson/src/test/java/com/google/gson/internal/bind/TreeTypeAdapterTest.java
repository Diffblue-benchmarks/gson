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

import static com.google.common.truth.Truth.assertThat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Type;
import org.junit.Test;

public class TreeTypeAdapterTest {

  private static class TestClass {
    String value;

    TestClass(String value) {
      this.value = value;
    }
  }

  private static class TestSerializer implements JsonSerializer<TestClass> {
    @Override
    public JsonElement serialize(TestClass src, Type typeOfSrc, JsonSerializationContext context) {
      return new JsonPrimitive(src.value);
    }
  }

  private static class TestDeserializer implements JsonDeserializer<TestClass> {
    @Override
    public TestClass deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
      return new TestClass(json.getAsString());
    }
  }

  @Test
  public void testConstructorWithNullSafeParameter() {
    Gson gson = new Gson();
    TypeToken<TestClass> typeToken = TypeToken.get(TestClass.class);
    TestSerializer serializer = new TestSerializer();
    TestDeserializer deserializer = new TestDeserializer();
    TypeAdapterFactory skipPast = TreeTypeAdapter.newFactory(typeToken, serializer);

    TreeTypeAdapter<TestClass> adapter =
        new TreeTypeAdapter<>(serializer, deserializer, gson, typeToken, skipPast, true);

    assertThat(adapter).isNotNull();
    assertThat(adapter.gson).isSameInstanceAs(gson);
  }

  @Test
  public void testConstructorWithoutNullSafeParameter() {
    Gson gson = new Gson();
    TypeToken<TestClass> typeToken = TypeToken.get(TestClass.class);
    TestSerializer serializer = new TestSerializer();
    TestDeserializer deserializer = new TestDeserializer();
    TypeAdapterFactory skipPast = TreeTypeAdapter.newFactory(typeToken, serializer);

    TreeTypeAdapter<TestClass> adapter =
        new TreeTypeAdapter<>(serializer, deserializer, gson, typeToken, skipPast);

    assertThat(adapter).isNotNull();
    assertThat(adapter.gson).isSameInstanceAs(gson);
  }

  @Test
  public void testReadWithDeserializer() throws IOException {
    Gson gson = new Gson();
    TypeToken<TestClass> typeToken = TypeToken.get(TestClass.class);
    TestSerializer serializer = new TestSerializer();
    TestDeserializer deserializer = new TestDeserializer();
    TypeAdapterFactory skipPast = TreeTypeAdapter.newFactory(typeToken, serializer);
    TreeTypeAdapter<TestClass> adapter =
        new TreeTypeAdapter<>(serializer, deserializer, gson, typeToken, skipPast);

    JsonReader reader = new JsonReader(new StringReader("\"test\""));
    TestClass result = adapter.read(reader);

    assertThat(result).isNotNull();
    assertThat(result.value).isEqualTo("test");
  }

  @Test
  public void testReadWithDeserializerAndNull() throws IOException {
    Gson gson = new Gson();
    TypeToken<TestClass> typeToken = TypeToken.get(TestClass.class);
    TestSerializer serializer = new TestSerializer();
    TestDeserializer deserializer = new TestDeserializer();
    TypeAdapterFactory skipPast = TreeTypeAdapter.newFactory(typeToken, serializer);
    TreeTypeAdapter<TestClass> adapter =
        new TreeTypeAdapter<>(serializer, deserializer, gson, typeToken, skipPast, true);

    JsonReader reader = new JsonReader(new StringReader("null"));
    TestClass result = adapter.read(reader);

    assertThat(result).isNull();
  }

  @Test
  public void testReadWithoutDeserializerUsesDelegate() throws IOException {
    Gson gson = new Gson();
    TypeToken<String> typeToken = TypeToken.get(String.class);
    TestSerializer serializer = new TestSerializer();
    TypeAdapterFactory skipPast = TreeTypeAdapter.newFactory(typeToken, serializer);
    TreeTypeAdapter<String> adapter =
        new TreeTypeAdapter<>(null, null, gson, typeToken, skipPast);

    JsonReader reader = new JsonReader(new StringReader("\"test\""));
    String result = adapter.read(reader);

    assertThat(result).isEqualTo("test");
  }

  @Test
  public void testWriteWithSerializer() throws IOException {
    Gson gson = new Gson();
    TypeToken<TestClass> typeToken = TypeToken.get(TestClass.class);
    TestSerializer serializer = new TestSerializer();
    TestDeserializer deserializer = new TestDeserializer();
    TypeAdapterFactory skipPast = TreeTypeAdapter.newFactory(typeToken, serializer);
    TreeTypeAdapter<TestClass> adapter =
        new TreeTypeAdapter<>(serializer, deserializer, gson, typeToken, skipPast);

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    TestClass testObject = new TestClass("test");
    adapter.write(writer, testObject);

    assertThat(stringWriter.toString()).isEqualTo("\"test\"");
  }

  @Test
  public void testWriteWithSerializerAndNull() throws IOException {
    Gson gson = new Gson();
    TypeToken<TestClass> typeToken = TypeToken.get(TestClass.class);
    TestSerializer serializer = new TestSerializer();
    TestDeserializer deserializer = new TestDeserializer();
    TypeAdapterFactory skipPast = TreeTypeAdapter.newFactory(typeToken, serializer);
    TreeTypeAdapter<TestClass> adapter =
        new TreeTypeAdapter<>(serializer, deserializer, gson, typeToken, skipPast, true);

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, null);

    assertThat(stringWriter.toString()).isEqualTo("null");
  }

  @Test
  public void testWriteWithoutSerializerUsesDelegate() throws IOException {
    Gson gson = new Gson();
    TypeToken<String> typeToken = TypeToken.get(String.class);
    TestDeserializer deserializer = new TestDeserializer();
    TypeAdapterFactory skipPast = TreeTypeAdapter.newFactory(typeToken, deserializer);
    TreeTypeAdapter<String> adapter =
        new TreeTypeAdapter<>(null, null, gson, typeToken, skipPast);

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, "test");

    assertThat(stringWriter.toString()).isEqualTo("\"test\"");
  }

  @Test
  public void testGetSerializationDelegateWithSerializer() {
    Gson gson = new Gson();
    TypeToken<TestClass> typeToken = TypeToken.get(TestClass.class);
    TestSerializer serializer = new TestSerializer();
    TestDeserializer deserializer = new TestDeserializer();
    TypeAdapterFactory skipPast = TreeTypeAdapter.newFactory(typeToken, serializer);
    TreeTypeAdapter<TestClass> adapter =
        new TreeTypeAdapter<>(serializer, deserializer, gson, typeToken, skipPast);

    TypeAdapter<TestClass> serializationDelegate = adapter.getSerializationDelegate();

    assertThat(serializationDelegate).isSameInstanceAs(adapter);
  }

  @Test
  public void testGetSerializationDelegateWithoutSerializer() {
    Gson gson = new Gson();
    TypeToken<String> typeToken = TypeToken.get(String.class);
    TestDeserializer deserializer = new TestDeserializer();
    TypeAdapterFactory skipPast = TreeTypeAdapter.newFactory(typeToken, deserializer);
    TreeTypeAdapter<String> adapter =
        new TreeTypeAdapter<>(null, null, gson, typeToken, skipPast);

    TypeAdapter<String> serializationDelegate = adapter.getSerializationDelegate();

    assertThat(serializationDelegate).isNotNull();
    assertThat(serializationDelegate).isNotSameInstanceAs(adapter);
  }

  @Test
  public void testNewFactory() {
    TypeToken<TestClass> typeToken = TypeToken.get(TestClass.class);
    TestSerializer serializer = new TestSerializer();

    TypeAdapterFactory factory = TreeTypeAdapter.newFactory(typeToken, serializer);

    assertThat(factory).isNotNull();
  }

  @Test
  public void testNewFactoryWithMatchRawType() {
    TypeToken<TestClass> typeToken = TypeToken.get(TestClass.class);
    TestSerializer serializer = new TestSerializer();

    TypeAdapterFactory factory = TreeTypeAdapter.newFactoryWithMatchRawType(typeToken, serializer);

    assertThat(factory).isNotNull();
  }

  @Test
  public void testNewFactoryWithMatchRawTypeCreatesAdapter() {
    TypeToken<TestClass> typeToken = TypeToken.get(TestClass.class);
    TestSerializer serializer = new TestSerializer();
    Gson gson = new Gson();

    TypeAdapterFactory factory = TreeTypeAdapter.newFactoryWithMatchRawType(typeToken, serializer);
    TypeAdapter<TestClass> adapter = factory.create(gson, typeToken);

    assertThat(adapter).isNotNull();
  }

  @Test
  public void testNewTypeHierarchyFactory() {
    TestSerializer serializer = new TestSerializer();

    TypeAdapterFactory factory = TreeTypeAdapter.newTypeHierarchyFactory(TestClass.class, serializer);

    assertThat(factory).isNotNull();
  }
}
