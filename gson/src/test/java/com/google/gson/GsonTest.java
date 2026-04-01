/*
 * Copyright (C) 2008 Google Inc.
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

package com.google.gson;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.EOFException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLongArray;
import org.junit.Before;
import org.junit.Test;

public final class GsonTest {

  private Gson gson;

  @Before
  public void setUp() {
    gson = new Gson();
  }

  @Test
  public void testDefaultConstructor() {
    Gson g = new Gson();
    assertThat(g).isNotNull();
  }

  @Test
  public void testNewBuilder() {
    GsonBuilder builder = gson.newBuilder();
    assertThat(builder).isNotNull();
    Gson rebuilt = builder.create();
    assertThat(rebuilt).isNotNull();
  }

  @SuppressWarnings("deprecation")
  @Test
  public void testExcluder() {
    assertThat(gson.excluder()).isNotNull();
  }

  @Test
  public void testFieldNamingStrategy() {
    assertThat(gson.fieldNamingStrategy()).isNotNull();
  }

  @Test
  public void testSerializeNullsDefaultFalse() {
    assertThat(gson.serializeNulls()).isFalse();
  }

  @Test
  public void testSerializeNullsEnabled() {
    Gson g = new GsonBuilder().serializeNulls().create();
    assertThat(g.serializeNulls()).isTrue();
  }

  @Test
  public void testHtmlSafeDefaultTrue() {
    assertThat(gson.htmlSafe()).isTrue();
  }

  @Test
  public void testHtmlSafeDisabled() {
    Gson g = new GsonBuilder().disableHtmlEscaping().create();
    assertThat(g.htmlSafe()).isFalse();
  }

  @Test
  public void testCheckValidFloatingPointValid() {
    // Should not throw
    Gson.checkValidFloatingPoint(1.0);
    Gson.checkValidFloatingPoint(0.0);
    Gson.checkValidFloatingPoint(-1.5);
  }

  @Test
  public void testCheckValidFloatingPointNaN() {
    assertThrows(IllegalArgumentException.class, () -> Gson.checkValidFloatingPoint(Double.NaN));
  }

  @Test
  public void testCheckValidFloatingPointInfinite() {
    assertThrows(
        IllegalArgumentException.class,
        () -> Gson.checkValidFloatingPoint(Double.POSITIVE_INFINITY));
    assertThrows(
        IllegalArgumentException.class,
        () -> Gson.checkValidFloatingPoint(Double.NEGATIVE_INFINITY));
  }

  @Test
  public void testGetAdapterByTypeToken() {
    TypeAdapter<String> adapter = gson.getAdapter(TypeToken.get(String.class));
    assertThat(adapter).isNotNull();
  }

  @Test
  public void testGetAdapterByClass() {
    TypeAdapter<Integer> adapter = gson.getAdapter(Integer.class);
    assertThat(adapter).isNotNull();
  }

  @Test
  public void testGetDelegateAdapter() {
    TypeAdapterFactory factory = new TypeAdapterFactory() {
      @Override
      public <T> TypeAdapter<T> create(Gson g, TypeToken<T> type) {
        return null;
      }
    };
    Gson g = new GsonBuilder().registerTypeAdapterFactory(factory).create();
    TypeAdapter<String> delegate = g.getDelegateAdapter(factory, TypeToken.get(String.class));
    assertThat(delegate).isNotNull();
  }

  @Test
  public void testToJsonTreeObject() {
    JsonElement element = gson.toJsonTree("hello");
    assertThat(element.getAsString()).isEqualTo("hello");
  }

  @Test
  public void testToJsonTreeNullObject() {
    JsonElement element = gson.toJsonTree(null);
    assertThat(element).isEqualTo(JsonNull.INSTANCE);
  }

  @Test
  public void testToJsonTreeWithType() {
    List<String> list = Arrays.asList("a", "b");
    Type type = new TypeToken<List<String>>() {}.getType();
    JsonElement element = gson.toJsonTree(list, type);
    assertThat(element.isJsonArray()).isTrue();
    assertThat(element.getAsJsonArray().size()).isEqualTo(2);
  }

  @Test
  public void testToJsonString() {
    String json = gson.toJson("hello");
    assertThat(json).isEqualTo("\"hello\"");
  }

  @Test
  public void testToJsonNullObject() {
    String json = gson.toJson(null);
    assertThat(json).isEqualTo("null");
  }

  @Test
  public void testToJsonWithType() {
    List<String> list = Arrays.asList("a", "b");
    Type type = new TypeToken<List<String>>() {}.getType();
    String json = gson.toJson(list, type);
    assertThat(json).isEqualTo("[\"a\",\"b\"]");
  }

  @Test
  public void testToJsonAppendable() throws IOException {
    StringBuilder sb = new StringBuilder();
    gson.toJson("hello", sb);
    assertThat(sb.toString()).isEqualTo("\"hello\"");
  }

  @Test
  public void testToJsonNullAppendable() throws IOException {
    StringBuilder sb = new StringBuilder();
    gson.toJson(null, sb);
    assertThat(sb.toString()).isEqualTo("null");
  }

  @Test
  public void testToJsonWithTypeAppendable() throws IOException {
    StringBuilder sb = new StringBuilder();
    List<String> list = Arrays.asList("x");
    Type type = new TypeToken<List<String>>() {}.getType();
    gson.toJson(list, type, sb);
    assertThat(sb.toString()).isEqualTo("[\"x\"]");
  }

  @Test
  public void testToJsonWithTypeJsonWriter() throws IOException {
    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    gson.toJson("hello", String.class, writer);
    writer.flush();
    assertThat(sw.toString()).isEqualTo("\"hello\"");
  }

  @Test
  public void testToJsonJsonElement() {
    JsonPrimitive element = new JsonPrimitive(42);
    String json = gson.toJson(element);
    assertThat(json).isEqualTo("42");
  }

  @Test
  public void testToJsonJsonElementAppendable() throws IOException {
    StringBuilder sb = new StringBuilder();
    gson.toJson(new JsonPrimitive(true), sb);
    assertThat(sb.toString()).isEqualTo("true");
  }

  @Test
  public void testToJsonJsonElementJsonWriter() throws IOException {
    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    gson.toJson(new JsonPrimitive("test"), writer);
    writer.flush();
    assertThat(sw.toString()).isEqualTo("\"test\"");
  }

  @Test
  public void testNewJsonWriter() throws IOException {
    StringWriter sw = new StringWriter();
    JsonWriter writer = gson.newJsonWriter(sw);
    assertThat(writer).isNotNull();
    writer.value("hello");
    writer.flush();
    assertThat(sw.toString()).isEqualTo("\"hello\"");
  }

  @Test
  public void testNewJsonReader() {
    JsonReader reader = gson.newJsonReader(new StringReader("\"hello\""));
    assertThat(reader).isNotNull();
  }

  @Test
  public void testFromJsonStringClass() {
    String result = gson.fromJson("\"world\"", String.class);
    assertThat(result).isEqualTo("world");
  }

  @Test
  public void testFromJsonNullStringClass() {
    String result = gson.fromJson((String) null, String.class);
    assertThat(result).isNull();
  }

  @Test
  public void testFromJsonStringType() {
    Type type = String.class;
    String result = gson.fromJson("\"world\"", type);
    assertThat(result).isEqualTo("world");
  }

  @Test
  public void testFromJsonStringTypeToken() {
    String result = gson.fromJson("\"world\"", TypeToken.get(String.class));
    assertThat(result).isEqualTo("world");
  }

  @Test
  public void testFromJsonNullStringTypeToken() {
    String result = gson.fromJson((String) null, TypeToken.get(String.class));
    assertThat(result).isNull();
  }

  @Test
  public void testFromJsonReaderClass() {
    String result = gson.fromJson(new StringReader("\"hello\""), String.class);
    assertThat(result).isEqualTo("hello");
  }

  @Test
  public void testFromJsonReaderType() {
    Type type = String.class;
    String result = gson.fromJson(new StringReader("\"hello\""), type);
    assertThat(result).isEqualTo("hello");
  }

  @Test
  public void testFromJsonReaderTypeToken() {
    String result = gson.fromJson(new StringReader("\"hello\""), TypeToken.get(String.class));
    assertThat(result).isEqualTo("hello");
  }

  @Test
  public void testFromJsonReaderTypeTokenTrailingDataThrows() {
    assertThrows(
        JsonSyntaxException.class,
        () -> gson.fromJson(new StringReader("\"hello\" \"extra\""), TypeToken.get(String.class)));
  }

  @Test
  public void testFromJsonJsonReaderType() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("\"hello\""));
    String result = gson.fromJson(reader, (Type) String.class);
    assertThat(result).isEqualTo("hello");
  }

  @Test
  public void testFromJsonJsonReaderTypeToken() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("42"));
    Integer result = gson.fromJson(reader, TypeToken.get(Integer.class));
    assertThat(result).isEqualTo(42);
  }

  @Test
  public void testFromJsonJsonReaderEof() throws IOException {
    JsonReader reader = new JsonReader(new StringReader(""));
    String result = gson.fromJson(reader, TypeToken.get(String.class));
    assertThat(result).isNull();
  }

  @Test
  public void testFromJsonJsonElementClass() {
    JsonPrimitive element = new JsonPrimitive("hello");
    String result = gson.fromJson(element, String.class);
    assertThat(result).isEqualTo("hello");
  }

  @Test
  public void testFromJsonNullJsonElementClass() {
    String result = gson.fromJson((JsonElement) null, String.class);
    assertThat(result).isNull();
  }

  @Test
  public void testFromJsonJsonElementType() {
    JsonPrimitive element = new JsonPrimitive(99);
    Integer result = gson.fromJson(element, (Type) Integer.class);
    assertThat(result).isEqualTo(99);
  }

  @Test
  public void testFromJsonNullJsonElementType() {
    Integer result = gson.fromJson((JsonElement) null, (Type) Integer.class);
    assertThat(result).isNull();
  }

  @Test
  public void testFromJsonJsonElementTypeToken() {
    JsonPrimitive element = new JsonPrimitive(true);
    Boolean result = gson.fromJson(element, TypeToken.get(Boolean.class));
    assertThat(result).isTrue();
  }

  @Test
  public void testFromJsonNullJsonElementTypeToken() {
    Boolean result = gson.fromJson((JsonElement) null, TypeToken.get(Boolean.class));
    assertThat(result).isNull();
  }

  @Test
  public void testToString() {
    String str = gson.toString();
    assertThat(str).contains("serializeNulls");
    assertThat(str).contains("factories");
    assertThat(str).contains("instanceCreators");
  }

  @Test
  public void testSerializeSpecialFloatingPointValues() {
    Gson g = new GsonBuilder().serializeSpecialFloatingPointValues().create();
    String json = g.toJson(Double.NaN);
    assertThat(json).isEqualTo("NaN");
  }

  @Test
  public void testSerializeNanThrowsWithoutSpecialFloatValues() {
    assertThrows(IllegalArgumentException.class, () -> gson.toJson(Double.NaN));
  }

  @Test
  public void testLongSerializationPolicyString() {
    Gson g = new GsonBuilder().setLongSerializationPolicy(LongSerializationPolicy.STRING).create();
    String json = g.toJson(123456789012345L);
    assertThat(json).isEqualTo("\"123456789012345\"");
  }

  @Test
  public void testToJsonListWithTypeToken() {
    List<Integer> list = Arrays.asList(1, 2, 3);
    String json = gson.toJson(list, new TypeToken<List<Integer>>() {}.getType());
    assertThat(json).isEqualTo("[1,2,3]");
  }

  @Test
  public void testFromJsonListWithTypeToken() {
    List<Integer> list =
        gson.fromJson("[1,2,3]", new TypeToken<List<Integer>>() {});
    assertThat(list).containsExactly(1, 2, 3).inOrder();
  }

  @Test
  public void testFromJsonReaderTypeTokenWithGsonStrictness() {
    Gson lenientGson = new GsonBuilder().setStrictness(Strictness.LENIENT).create();
    JsonReader reader = new JsonReader(new StringReader("\"hello\""));
    String result = lenientGson.fromJson(reader, TypeToken.get(String.class));
    assertThat(result).isEqualTo("hello");
  }

  @Test
  public void testFromJsonReaderTypeTokenClassCastException() {
    @SuppressWarnings({"unchecked", "rawtypes"})
    TypeAdapter rawAdapter =
        new TypeAdapter() {
          @Override
          public void write(JsonWriter out, Object value) throws IOException {}

          @Override
          public Object read(JsonReader in) throws IOException {
            in.nextString();
            return Integer.valueOf(42);
          }
        };
    @SuppressWarnings("unchecked")
    Gson badAdapterGson =
        new GsonBuilder().registerTypeAdapter(String.class, rawAdapter).create();
    JsonReader reader = new JsonReader(new StringReader("\"hello\""));
    assertThrows(
        ClassCastException.class,
        () -> badAdapterGson.fromJson(reader, TypeToken.get(String.class)));
  }

  @Test
  public void testFromJsonReaderTypeTokenEOFExceptionAfterPeek() {
    Gson eofGson =
        new GsonBuilder()
            .registerTypeAdapter(
                String.class,
                new TypeAdapter<String>() {
                  @Override
                  public void write(JsonWriter out, String value) throws IOException {}

                  @Override
                  public String read(JsonReader in) throws IOException {
                    in.nextString();
                    throw new EOFException("unexpected eof during read");
                  }
                })
            .create();
    JsonReader reader = new JsonReader(new StringReader("\"hello\""));
    assertThrows(
        JsonSyntaxException.class,
        () -> eofGson.fromJson(reader, TypeToken.get(String.class)));
  }

  @Test
  public void testFromJsonReaderTypeTokenIllegalStateException() {
    Gson illegalStateGson =
        new GsonBuilder()
            .registerTypeAdapter(
                String.class,
                new TypeAdapter<String>() {
                  @Override
                  public void write(JsonWriter out, String value) throws IOException {}

                  @Override
                  public String read(JsonReader in) throws IOException {
                    throw new IllegalStateException("illegal state during read");
                  }
                })
            .create();
    JsonReader reader = new JsonReader(new StringReader("\"hello\""));
    assertThrows(
        JsonSyntaxException.class,
        () -> illegalStateGson.fromJson(reader, TypeToken.get(String.class)));
  }

  @Test
  public void testFromJsonReaderTypeTokenIOException() {
    Gson ioExceptionGson =
        new GsonBuilder()
            .registerTypeAdapter(
                String.class,
                new TypeAdapter<String>() {
                  @Override
                  public void write(JsonWriter out, String value) throws IOException {}

                  @Override
                  public String read(JsonReader in) throws IOException {
                    throw new IOException("io error during read");
                  }
                })
            .create();
    JsonReader reader = new JsonReader(new StringReader("\"hello\""));
    assertThrows(
        JsonSyntaxException.class,
        () -> ioExceptionGson.fromJson(reader, TypeToken.get(String.class)));
  }

  @Test
  public void testFromJsonReaderTypeTokenAssertionError() {
    Gson assertionErrorGson =
        new GsonBuilder()
            .registerTypeAdapter(
                String.class,
                new TypeAdapter<String>() {
                  @Override
                  public void write(JsonWriter out, String value) throws IOException {}

                  @Override
                  public String read(JsonReader in) throws IOException {
                    throw new AssertionError("adapter assertion failed");
                  }
                })
            .create();
    JsonReader reader = new JsonReader(new StringReader("\"hello\""));
    AssertionError e =
        assertThrows(
            AssertionError.class,
            () -> assertionErrorGson.fromJson(reader, TypeToken.get(String.class)));
    assertThat(e).hasMessageThat().contains("AssertionError (GSON");
  }

  @Test
  public void testToJsonJsonElementJsonWriterWithGsonStrictness() throws IOException {
    Gson strictGson = new GsonBuilder().setStrictness(Strictness.STRICT).create();
    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    strictGson.toJson(new JsonPrimitive("test"), writer);
    writer.flush();
    assertThat(sw.toString()).isEqualTo("\"test\"");
  }

  @Test
  public void testToJsonJsonElementJsonWriterIOException() {
    Writer throwingWriter =
        new Writer() {
          @Override
          public void write(char[] cbuf, int off, int len) throws IOException {
            throw new IOException("simulated io error");
          }

          @Override
          public void flush() throws IOException {}

          @Override
          public void close() throws IOException {}
        };
    JsonWriter jsonWriter = new JsonWriter(throwingWriter);
    assertThrows(JsonIOException.class, () -> gson.toJson(new JsonPrimitive("hello"), jsonWriter));
  }

  @Test
  public void testToJsonJsonElementJsonWriterAssertionError() {
    JsonWriter throwingWriter =
        new JsonWriter(new StringWriter()) {
          @Override
          public JsonWriter value(String value) throws IOException {
            throw new AssertionError("simulated assertion error");
          }
        };
    AssertionError e =
        assertThrows(
            AssertionError.class, () -> gson.toJson(new JsonPrimitive("hello"), throwingWriter));
    assertThat(e).hasMessageThat().contains("AssertionError (GSON");
  }

  @Test
  public void testToJsonWithTypeJsonWriterWithGsonStrictness() throws IOException {
    Gson strictGson = new GsonBuilder().setStrictness(Strictness.STRICT).create();
    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    strictGson.toJson("hello", String.class, writer);
    writer.flush();
    assertThat(sw.toString()).isEqualTo("\"hello\"");
  }

  @Test
  public void testToJsonWithTypeJsonWriterIOException() {
    Writer throwingWriter =
        new Writer() {
          @Override
          public void write(char[] cbuf, int off, int len) throws IOException {
            throw new IOException("simulated io error");
          }

          @Override
          public void flush() throws IOException {}

          @Override
          public void close() throws IOException {}
        };
    JsonWriter jsonWriter = new JsonWriter(throwingWriter);
    assertThrows(
        JsonIOException.class, () -> gson.toJson("hello", String.class, jsonWriter));
  }

  @Test
  public void testAtomicLongArraySerialization() {
    AtomicLongArray array = new AtomicLongArray(new long[] {1L, 2L, 3L});
    String json = gson.toJson(array);
    assertThat(json).isEqualTo("[1,2,3]");
  }

  @Test
  public void testAtomicLongArraySerializationEmpty() {
    AtomicLongArray array = new AtomicLongArray(0);
    String json = gson.toJson(array);
    assertThat(json).isEqualTo("[]");
  }

  @Test
  public void testAtomicLongArrayDeserialization() {
    AtomicLongArray array = gson.fromJson("[10,20,30]", AtomicLongArray.class);
    assertThat(array.length()).isEqualTo(3);
    assertThat(array.get(0)).isEqualTo(10L);
    assertThat(array.get(1)).isEqualTo(20L);
    assertThat(array.get(2)).isEqualTo(30L);
  }

  @Test
  public void testAtomicLongArrayDeserializationEmpty() {
    AtomicLongArray array = gson.fromJson("[]", AtomicLongArray.class);
    assertThat(array.length()).isEqualTo(0);
  }

  @Test
  public void testAtomicLongArrayRoundTrip() {
    AtomicLongArray original = new AtomicLongArray(new long[] {Long.MIN_VALUE, 0L, Long.MAX_VALUE});
    String json = gson.toJson(original);
    AtomicLongArray deserialized = gson.fromJson(json, AtomicLongArray.class);
    assertThat(deserialized.length()).isEqualTo(original.length());
    for (int i = 0; i < original.length(); i++) {
      assertThat(deserialized.get(i)).isEqualTo(original.get(i));
    }
  }

  @Test
  public void testAtomicLongArrayToJsonWithWriterAssertionError() {
    JsonWriter throwingWriter =
        new JsonWriter(new StringWriter()) {
          @Override
          public JsonWriter value(String value) throws IOException {
            throw new AssertionError("simulated assertion error");
          }
        };
    AssertionError e =
        assertThrows(
            AssertionError.class,
            () -> gson.toJson("hello", String.class, throwingWriter));
    assertThat(e).hasMessageThat().contains("AssertionError (GSON");
  }
}
