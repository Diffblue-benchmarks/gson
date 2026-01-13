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

package com.google.gson;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import org.junit.Test;

/**
 * Unit tests for {@link TypeAdapter}.
 *
 * @author Claude
 */
public class TypeAdapterClaudeTest {

  // ========== Simple TypeAdapter implementations for testing ==========

  /**
   * A simple TypeAdapter for String that does NOT handle nulls automatically.
   * This is used to test the nullSafe() wrapper.
   */
  private static final TypeAdapter<String> STRING_ADAPTER =
      new TypeAdapter<String>() {
        @Override
        public void write(JsonWriter out, String value) throws IOException {
          out.value(value);
        }

        @Override
        public String read(JsonReader in) throws IOException {
          return in.nextString();
        }
      };

  /**
   * A simple TypeAdapter for Integer.
   */
  private static final TypeAdapter<Integer> INTEGER_ADAPTER =
      new TypeAdapter<Integer>() {
        @Override
        public void write(JsonWriter out, Integer value) throws IOException {
          if (value == null) {
            out.nullValue();
          } else {
            out.value(value);
          }
        }

        @Override
        public Integer read(JsonReader in) throws IOException {
          if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
          }
          return in.nextInt();
        }
      };

  /**
   * A simple Point class for testing object serialization/deserialization.
   */
  private static class Point {
    final int x;
    final int y;

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

  /**
   * TypeAdapter for Point that writes/reads as "x,y" format.
   */
  private static final TypeAdapter<Point> POINT_ADAPTER =
      new TypeAdapter<Point>() {
        @Override
        public void write(JsonWriter out, Point value) throws IOException {
          if (value == null) {
            out.nullValue();
          } else {
            out.value(value.x + "," + value.y);
          }
        }

        @Override
        public Point read(JsonReader in) throws IOException {
          if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
          }
          String[] parts = in.nextString().split(",");
          return new Point(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
        }
      };

  /**
   * A TypeAdapter that throws IOException on write for testing exception wrapping.
   */
  private static final TypeAdapter<String> THROWING_WRITE_ADAPTER =
      new TypeAdapter<String>() {
        @Override
        public void write(JsonWriter out, String value) throws IOException {
          throw new IOException("Write failed");
        }

        @Override
        public String read(JsonReader in) throws IOException {
          return in.nextString();
        }
      };

  /**
   * A TypeAdapter that throws IOException on read for testing exception wrapping.
   */
  private static final TypeAdapter<String> THROWING_READ_ADAPTER =
      new TypeAdapter<String>() {
        @Override
        public void write(JsonWriter out, String value) throws IOException {
          out.value(value);
        }

        @Override
        public String read(JsonReader in) throws IOException {
          throw new IOException("Read failed");
        }
      };

  // ========== Tests for toJson(Writer, Object) ==========

  @Test
  public void testToJsonWithWriter_stringValue() throws IOException {
    StringWriter writer = new StringWriter();
    STRING_ADAPTER.toJson(writer, "hello");
    assertThat(writer.toString()).isEqualTo("\"hello\"");
  }

  @Test
  public void testToJsonWithWriter_integerValue() throws IOException {
    StringWriter writer = new StringWriter();
    INTEGER_ADAPTER.toJson(writer, 42);
    assertThat(writer.toString()).isEqualTo("42");
  }

  @Test
  public void testToJsonWithWriter_nullValue() throws IOException {
    StringWriter writer = new StringWriter();
    INTEGER_ADAPTER.toJson(writer, null);
    assertThat(writer.toString()).isEqualTo("null");
  }

  @Test
  public void testToJsonWithWriter_pointValue() throws IOException {
    StringWriter writer = new StringWriter();
    POINT_ADAPTER.toJson(writer, new Point(5, 8));
    assertThat(writer.toString()).isEqualTo("\"5,8\"");
  }

  @Test
  public void testToJsonWithWriter_emptyString() throws IOException {
    StringWriter writer = new StringWriter();
    STRING_ADAPTER.toJson(writer, "");
    assertThat(writer.toString()).isEqualTo("\"\"");
  }

  @Test
  public void testToJsonWithWriter_specialCharacters() throws IOException {
    StringWriter writer = new StringWriter();
    STRING_ADAPTER.toJson(writer, "hello\nworld");
    assertThat(writer.toString()).isEqualTo("\"hello\\nworld\"");
  }

  @Test
  public void testToJsonWithWriter_throwsIOException() {
    StringWriter writer = new StringWriter();
    assertThrows(IOException.class, () -> THROWING_WRITE_ADAPTER.toJson(writer, "test"));
  }

  // ========== Tests for toJson(Object) ==========

  @Test
  public void testToJsonString_stringValue() {
    String result = STRING_ADAPTER.toJson("hello");
    assertThat(result).isEqualTo("\"hello\"");
  }

  @Test
  public void testToJsonString_integerValue() {
    String result = INTEGER_ADAPTER.toJson(42);
    assertThat(result).isEqualTo("42");
  }

  @Test
  public void testToJsonString_nullValue() {
    String result = INTEGER_ADAPTER.toJson(null);
    assertThat(result).isEqualTo("null");
  }

  @Test
  public void testToJsonString_pointValue() {
    String result = POINT_ADAPTER.toJson(new Point(10, 20));
    assertThat(result).isEqualTo("\"10,20\"");
  }

  @Test
  public void testToJsonString_negativeNumber() {
    String result = INTEGER_ADAPTER.toJson(-100);
    assertThat(result).isEqualTo("-100");
  }

  @Test
  public void testToJsonString_zeroValue() {
    String result = INTEGER_ADAPTER.toJson(0);
    assertThat(result).isEqualTo("0");
  }

  @Test
  public void testToJsonString_wrapsIOException() {
    JsonIOException e = assertThrows(JsonIOException.class, () -> THROWING_WRITE_ADAPTER.toJson("test"));
    assertThat(e.getCause()).isInstanceOf(IOException.class);
    assertThat(e.getCause().getMessage()).isEqualTo("Write failed");
  }

  // ========== Tests for toJsonTree(Object) ==========

  @Test
  public void testToJsonTree_stringValue() {
    JsonElement result = STRING_ADAPTER.toJsonTree("hello");
    assertThat(result.isJsonPrimitive()).isTrue();
    assertThat(result.getAsString()).isEqualTo("hello");
  }

  @Test
  public void testToJsonTree_integerValue() {
    JsonElement result = INTEGER_ADAPTER.toJsonTree(42);
    assertThat(result.isJsonPrimitive()).isTrue();
    assertThat(result.getAsInt()).isEqualTo(42);
  }

  @Test
  public void testToJsonTree_nullValue() {
    JsonElement result = INTEGER_ADAPTER.toJsonTree(null);
    assertThat(result.isJsonNull()).isTrue();
  }

  @Test
  public void testToJsonTree_pointValue() {
    JsonElement result = POINT_ADAPTER.toJsonTree(new Point(3, 4));
    assertThat(result.isJsonPrimitive()).isTrue();
    assertThat(result.getAsString()).isEqualTo("3,4");
  }

  @Test
  public void testToJsonTree_wrapsIOException() {
    JsonIOException e = assertThrows(JsonIOException.class, () -> THROWING_WRITE_ADAPTER.toJsonTree("test"));
    assertThat(e.getCause()).isInstanceOf(IOException.class);
    assertThat(e.getCause().getMessage()).isEqualTo("Write failed");
  }

  // ========== Tests for fromJson(Reader) ==========

  @Test
  public void testFromJsonReader_stringValue() throws IOException {
    Reader reader = new StringReader("\"hello\"");
    String result = STRING_ADAPTER.fromJson(reader);
    assertThat(result).isEqualTo("hello");
  }

  @Test
  public void testFromJsonReader_integerValue() throws IOException {
    Reader reader = new StringReader("42");
    Integer result = INTEGER_ADAPTER.fromJson(reader);
    assertThat(result).isEqualTo(42);
  }

  @Test
  public void testFromJsonReader_nullValue() throws IOException {
    Reader reader = new StringReader("null");
    Integer result = INTEGER_ADAPTER.fromJson(reader);
    assertThat(result).isNull();
  }

  @Test
  public void testFromJsonReader_pointValue() throws IOException {
    Reader reader = new StringReader("\"5,8\"");
    Point result = POINT_ADAPTER.fromJson(reader);
    assertThat(result).isEqualTo(new Point(5, 8));
  }

  @Test
  public void testFromJsonReader_negativeNumber() throws IOException {
    Reader reader = new StringReader("-100");
    Integer result = INTEGER_ADAPTER.fromJson(reader);
    assertThat(result).isEqualTo(-100);
  }

  @Test
  public void testFromJsonReader_emptyString() throws IOException {
    Reader reader = new StringReader("\"\"");
    String result = STRING_ADAPTER.fromJson(reader);
    assertThat(result).isEqualTo("");
  }

  @Test
  public void testFromJsonReader_throwsIOException() {
    Reader reader = new StringReader("\"test\"");
    assertThrows(IOException.class, () -> THROWING_READ_ADAPTER.fromJson(reader));
  }

  // ========== Tests for fromJson(String) ==========

  @Test
  public void testFromJsonString_stringValue() throws IOException {
    String result = STRING_ADAPTER.fromJson("\"hello\"");
    assertThat(result).isEqualTo("hello");
  }

  @Test
  public void testFromJsonString_integerValue() throws IOException {
    Integer result = INTEGER_ADAPTER.fromJson("42");
    assertThat(result).isEqualTo(42);
  }

  @Test
  public void testFromJsonString_nullValue() throws IOException {
    Integer result = INTEGER_ADAPTER.fromJson("null");
    assertThat(result).isNull();
  }

  @Test
  public void testFromJsonString_pointValue() throws IOException {
    Point result = POINT_ADAPTER.fromJson("\"10,20\"");
    assertThat(result).isEqualTo(new Point(10, 20));
  }

  @Test
  public void testFromJsonString_zeroValue() throws IOException {
    Integer result = INTEGER_ADAPTER.fromJson("0");
    assertThat(result).isEqualTo(0);
  }

  @Test
  public void testFromJsonString_escapedCharacters() throws IOException {
    String result = STRING_ADAPTER.fromJson("\"hello\\nworld\"");
    assertThat(result).isEqualTo("hello\nworld");
  }

  @Test
  public void testFromJsonString_throwsIOException() {
    assertThrows(IOException.class, () -> THROWING_READ_ADAPTER.fromJson("\"test\""));
  }

  // ========== Tests for fromJsonTree(JsonElement) ==========

  @Test
  public void testFromJsonTree_stringValue() {
    JsonElement element = new JsonPrimitive("hello");
    String result = STRING_ADAPTER.fromJsonTree(element);
    assertThat(result).isEqualTo("hello");
  }

  @Test
  public void testFromJsonTree_integerValue() {
    JsonElement element = new JsonPrimitive(42);
    Integer result = INTEGER_ADAPTER.fromJsonTree(element);
    assertThat(result).isEqualTo(42);
  }

  @Test
  public void testFromJsonTree_nullValue() {
    JsonElement element = JsonNull.INSTANCE;
    Integer result = INTEGER_ADAPTER.fromJsonTree(element);
    assertThat(result).isNull();
  }

  @Test
  public void testFromJsonTree_pointValue() {
    JsonElement element = new JsonPrimitive("5,8");
    Point result = POINT_ADAPTER.fromJsonTree(element);
    assertThat(result).isEqualTo(new Point(5, 8));
  }

  @Test
  public void testFromJsonTree_wrapsIOException() {
    JsonElement element = new JsonPrimitive("test");
    JsonIOException e = assertThrows(JsonIOException.class, () -> THROWING_READ_ADAPTER.fromJsonTree(element));
    assertThat(e.getCause()).isInstanceOf(IOException.class);
    assertThat(e.getCause().getMessage()).isEqualTo("Read failed");
  }

  // ========== Tests for nullSafe() ==========

  @Test
  public void testNullSafe_readNullValue() throws IOException {
    TypeAdapter<String> nullSafeAdapter = STRING_ADAPTER.nullSafe();
    Reader reader = new StringReader("null");
    String result = nullSafeAdapter.fromJson(reader);
    assertThat(result).isNull();
  }

  @Test
  public void testNullSafe_writeNullValue() throws IOException {
    TypeAdapter<String> nullSafeAdapter = STRING_ADAPTER.nullSafe();
    StringWriter writer = new StringWriter();
    nullSafeAdapter.toJson(writer, null);
    assertThat(writer.toString()).isEqualTo("null");
  }

  @Test
  public void testNullSafe_readNonNullValue() throws IOException {
    TypeAdapter<String> nullSafeAdapter = STRING_ADAPTER.nullSafe();
    Reader reader = new StringReader("\"hello\"");
    String result = nullSafeAdapter.fromJson(reader);
    assertThat(result).isEqualTo("hello");
  }

  @Test
  public void testNullSafe_writeNonNullValue() throws IOException {
    TypeAdapter<String> nullSafeAdapter = STRING_ADAPTER.nullSafe();
    StringWriter writer = new StringWriter();
    nullSafeAdapter.toJson(writer, "hello");
    assertThat(writer.toString()).isEqualTo("\"hello\"");
  }

  @Test
  public void testNullSafe_returnsNewAdapter() {
    TypeAdapter<String> nullSafeAdapter = STRING_ADAPTER.nullSafe();
    assertThat(nullSafeAdapter).isNotSameInstanceAs(STRING_ADAPTER);
  }

  @Test
  public void testNullSafe_calledTwiceReturnsSameInstance() {
    TypeAdapter<String> nullSafeAdapter1 = STRING_ADAPTER.nullSafe();
    TypeAdapter<String> nullSafeAdapter2 = nullSafeAdapter1.nullSafe();
    assertThat(nullSafeAdapter2).isSameInstanceAs(nullSafeAdapter1);
  }

  @Test
  public void testNullSafe_toStringIncludesOriginalAdapter() {
    TypeAdapter<String> adapter = new TypeAdapter<String>() {
      @Override
      public void write(JsonWriter out, String value) throws IOException {
        out.value(value);
      }

      @Override
      public String read(JsonReader in) throws IOException {
        return in.nextString();
      }

      @Override
      public String toString() {
        return "CustomStringAdapter";
      }
    };
    TypeAdapter<String> nullSafeAdapter = adapter.nullSafe();
    assertThat(nullSafeAdapter.toString()).contains("CustomStringAdapter");
    assertThat(nullSafeAdapter.toString()).contains("NullSafeTypeAdapter");
  }

  @Test
  public void testNullSafe_toJsonStringWithNull() {
    TypeAdapter<String> nullSafeAdapter = STRING_ADAPTER.nullSafe();
    String result = nullSafeAdapter.toJson(null);
    assertThat(result).isEqualTo("null");
  }

  @Test
  public void testNullSafe_toJsonTreeWithNull() {
    TypeAdapter<String> nullSafeAdapter = STRING_ADAPTER.nullSafe();
    JsonElement result = nullSafeAdapter.toJsonTree(null);
    assertThat(result.isJsonNull()).isTrue();
  }

  @Test
  public void testNullSafe_fromJsonStringWithNull() throws IOException {
    TypeAdapter<String> nullSafeAdapter = STRING_ADAPTER.nullSafe();
    String result = nullSafeAdapter.fromJson("null");
    assertThat(result).isNull();
  }

  @Test
  public void testNullSafe_fromJsonTreeWithNull() {
    TypeAdapter<String> nullSafeAdapter = STRING_ADAPTER.nullSafe();
    String result = nullSafeAdapter.fromJsonTree(JsonNull.INSTANCE);
    assertThat(result).isNull();
  }

  // ========== Additional edge case tests ==========

  @Test
  public void testRoundTrip_viaWriter() throws IOException {
    Point original = new Point(100, 200);
    StringWriter writer = new StringWriter();
    POINT_ADAPTER.toJson(writer, original);
    String json = writer.toString();

    Reader reader = new StringReader(json);
    Point restored = POINT_ADAPTER.fromJson(reader);
    assertThat(restored).isEqualTo(original);
  }

  @Test
  public void testRoundTrip_viaString() throws IOException {
    Point original = new Point(100, 200);
    String json = POINT_ADAPTER.toJson(original);
    Point restored = POINT_ADAPTER.fromJson(json);
    assertThat(restored).isEqualTo(original);
  }

  @Test
  public void testRoundTrip_viaJsonTree() {
    Point original = new Point(100, 200);
    JsonElement tree = POINT_ADAPTER.toJsonTree(original);
    Point restored = POINT_ADAPTER.fromJsonTree(tree);
    assertThat(restored).isEqualTo(original);
  }

  @Test
  public void testNullSafe_roundTripWithNull() throws IOException {
    TypeAdapter<Point> nullSafePointAdapter = POINT_ADAPTER.nullSafe();

    String json = nullSafePointAdapter.toJson(null);
    Point restored = nullSafePointAdapter.fromJson(json);
    assertThat(restored).isNull();
  }

  @Test
  public void testNullSafe_roundTripWithNonNull() throws IOException {
    TypeAdapter<Point> nullSafePointAdapter = POINT_ADAPTER.nullSafe();
    Point original = new Point(7, 11);

    String json = nullSafePointAdapter.toJson(original);
    Point restored = nullSafePointAdapter.fromJson(json);
    assertThat(restored).isEqualTo(original);
  }

  @Test
  public void testNullSafe_withIntegerAdapter() throws IOException {
    // Test nullSafe with an adapter that already handles nulls properly
    TypeAdapter<Integer> nullSafeIntAdapter = INTEGER_ADAPTER.nullSafe();

    // Write null
    String nullJson = nullSafeIntAdapter.toJson(null);
    assertThat(nullJson).isEqualTo("null");

    // Read null
    Integer nullResult = nullSafeIntAdapter.fromJson("null");
    assertThat(nullResult).isNull();

    // Write non-null
    String nonNullJson = nullSafeIntAdapter.toJson(42);
    assertThat(nonNullJson).isEqualTo("42");

    // Read non-null
    Integer nonNullResult = nullSafeIntAdapter.fromJson("42");
    assertThat(nonNullResult).isEqualTo(42);
  }

  /**
   * TypeAdapter for testing complex object serialization.
   */
  private static final TypeAdapter<JsonObject> JSON_OBJECT_ADAPTER =
      new TypeAdapter<JsonObject>() {
        @Override
        public void write(JsonWriter out, JsonObject value) throws IOException {
          if (value == null) {
            out.nullValue();
            return;
          }
          out.beginObject();
          for (String key : value.keySet()) {
            out.name(key);
            JsonElement element = value.get(key);
            if (element.isJsonPrimitive()) {
              JsonPrimitive primitive = element.getAsJsonPrimitive();
              if (primitive.isNumber()) {
                out.value(primitive.getAsNumber());
              } else if (primitive.isBoolean()) {
                out.value(primitive.getAsBoolean());
              } else {
                out.value(primitive.getAsString());
              }
            } else if (element.isJsonNull()) {
              out.nullValue();
            }
          }
          out.endObject();
        }

        @Override
        public JsonObject read(JsonReader in) throws IOException {
          if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
          }
          JsonObject result = new JsonObject();
          in.beginObject();
          while (in.hasNext()) {
            String name = in.nextName();
            switch (in.peek()) {
              case STRING:
                result.addProperty(name, in.nextString());
                break;
              case NUMBER:
                result.addProperty(name, in.nextDouble());
                break;
              case BOOLEAN:
                result.addProperty(name, in.nextBoolean());
                break;
              case NULL:
                in.nextNull();
                result.add(name, JsonNull.INSTANCE);
                break;
              default:
                in.skipValue();
            }
          }
          in.endObject();
          return result;
        }
      };

  @Test
  public void testToJsonTree_complexObject() {
    JsonObject input = new JsonObject();
    input.addProperty("name", "test");
    input.addProperty("value", 123);
    input.addProperty("active", true);

    JsonElement result = JSON_OBJECT_ADAPTER.toJsonTree(input);
    assertThat(result.isJsonObject()).isTrue();
    JsonObject obj = result.getAsJsonObject();
    assertThat(obj.get("name").getAsString()).isEqualTo("test");
    assertThat(obj.get("value").getAsNumber().intValue()).isEqualTo(123);
    assertThat(obj.get("active").getAsBoolean()).isTrue();
  }

  @Test
  public void testFromJsonTree_complexObject() {
    JsonObject input = new JsonObject();
    input.addProperty("name", "test");
    input.addProperty("value", 456.5);
    input.addProperty("flag", false);

    JsonObject result = JSON_OBJECT_ADAPTER.fromJsonTree(input);
    assertThat(result.get("name").getAsString()).isEqualTo("test");
    assertThat(result.get("value").getAsDouble()).isEqualTo(456.5);
    assertThat(result.get("flag").getAsBoolean()).isFalse();
  }
}
