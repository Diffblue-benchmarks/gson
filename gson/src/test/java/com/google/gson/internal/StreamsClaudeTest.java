/*
 * Copyright (C) 2010 Google Inc.
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

package com.google.gson.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import org.junit.Test;

/** Tests for {@link Streams}. */
public class StreamsClaudeTest {

  // ==========================================================================
  // parse tests
  // ==========================================================================

  @Test
  public void parse_emptyInput_returnsJsonNull() {
    JsonReader reader = new JsonReader(new StringReader(""));
    JsonElement result = Streams.parse(reader);
    assertSame(JsonNull.INSTANCE, result);
  }

  @Test
  public void parse_nullLiteral_returnsJsonNull() {
    JsonReader reader = new JsonReader(new StringReader("null"));
    JsonElement result = Streams.parse(reader);
    assertTrue(result.isJsonNull());
  }

  @Test
  public void parse_stringValue_returnsJsonPrimitive() {
    JsonReader reader = new JsonReader(new StringReader("\"hello\""));
    JsonElement result = Streams.parse(reader);
    assertTrue(result.isJsonPrimitive());
    assertEquals("hello", result.getAsString());
  }

  @Test
  public void parse_integerValue_returnsJsonPrimitive() {
    JsonReader reader = new JsonReader(new StringReader("42"));
    JsonElement result = Streams.parse(reader);
    assertTrue(result.isJsonPrimitive());
    assertEquals(42, result.getAsInt());
  }

  @Test
  public void parse_doubleValue_returnsJsonPrimitive() {
    JsonReader reader = new JsonReader(new StringReader("3.14"));
    JsonElement result = Streams.parse(reader);
    assertTrue(result.isJsonPrimitive());
    assertEquals(3.14, result.getAsDouble(), 0.001);
  }

  @Test
  public void parse_booleanTrue_returnsJsonPrimitive() {
    JsonReader reader = new JsonReader(new StringReader("true"));
    JsonElement result = Streams.parse(reader);
    assertTrue(result.isJsonPrimitive());
    assertTrue(result.getAsBoolean());
  }

  @Test
  public void parse_booleanFalse_returnsJsonPrimitive() {
    JsonReader reader = new JsonReader(new StringReader("false"));
    JsonElement result = Streams.parse(reader);
    assertTrue(result.isJsonPrimitive());
    assertEquals(false, result.getAsBoolean());
  }

  @Test
  public void parse_emptyArray_returnsJsonArray() {
    JsonReader reader = new JsonReader(new StringReader("[]"));
    JsonElement result = Streams.parse(reader);
    assertTrue(result.isJsonArray());
    assertEquals(0, result.getAsJsonArray().size());
  }

  @Test
  public void parse_arrayWithElements_returnsJsonArrayWithElements() {
    JsonReader reader = new JsonReader(new StringReader("[1, 2, 3]"));
    JsonElement result = Streams.parse(reader);
    assertTrue(result.isJsonArray());
    JsonArray array = result.getAsJsonArray();
    assertEquals(3, array.size());
    assertEquals(1, array.get(0).getAsInt());
    assertEquals(2, array.get(1).getAsInt());
    assertEquals(3, array.get(2).getAsInt());
  }

  @Test
  public void parse_emptyObject_returnsJsonObject() {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    JsonElement result = Streams.parse(reader);
    assertTrue(result.isJsonObject());
    assertEquals(0, result.getAsJsonObject().size());
  }

  @Test
  public void parse_objectWithProperties_returnsJsonObjectWithProperties() {
    JsonReader reader = new JsonReader(new StringReader("{\"name\": \"John\", \"age\": 30}"));
    JsonElement result = Streams.parse(reader);
    assertTrue(result.isJsonObject());
    JsonObject obj = result.getAsJsonObject();
    assertEquals("John", obj.get("name").getAsString());
    assertEquals(30, obj.get("age").getAsInt());
  }

  @Test
  public void parse_nestedStructure_returnsCorrectlyNestedElements() {
    String json = "{\"users\": [{\"name\": \"Alice\"}, {\"name\": \"Bob\"}]}";
    JsonReader reader = new JsonReader(new StringReader(json));
    JsonElement result = Streams.parse(reader);
    assertTrue(result.isJsonObject());
    JsonArray users = result.getAsJsonObject().getAsJsonArray("users");
    assertEquals(2, users.size());
    assertEquals("Alice", users.get(0).getAsJsonObject().get("name").getAsString());
  }

  @Test
  public void parse_malformedJson_throwsJsonSyntaxException() {
    JsonReader reader = new JsonReader(new StringReader("{invalid}"));
    assertThrows(JsonSyntaxException.class, () -> Streams.parse(reader));
  }

  @Test
  public void parse_unclosedArray_throwsJsonSyntaxException() {
    JsonReader reader = new JsonReader(new StringReader("[1, 2, 3"));
    assertThrows(JsonSyntaxException.class, () -> Streams.parse(reader));
  }

  @Test
  public void parse_unclosedObject_throwsJsonSyntaxException() {
    JsonReader reader = new JsonReader(new StringReader("{\"key\": \"value\""));
    assertThrows(JsonSyntaxException.class, () -> Streams.parse(reader));
  }

  @Test
  public void parse_invalidNumber_throwsJsonSyntaxException() {
    // Numbers like 01 are invalid in JSON
    JsonReader reader = new JsonReader(new StringReader("01"));
    assertThrows(JsonSyntaxException.class, () -> Streams.parse(reader));
  }

  @Test
  public void parse_truncatedString_throwsJsonSyntaxException() {
    JsonReader reader = new JsonReader(new StringReader("\"unterminated"));
    assertThrows(JsonSyntaxException.class, () -> Streams.parse(reader));
  }

  @Test
  public void parse_ioException_throwsJsonIOException() {
    // Create a Reader that throws IOException
    Reader failingReader =
        new Reader() {
          @Override
          public int read(char[] cbuf, int off, int len) throws IOException {
            throw new IOException("Simulated IO failure");
          }

          @Override
          public void close() {}
        };
    JsonReader reader = new JsonReader(failingReader);
    assertThrows(JsonIOException.class, () -> Streams.parse(reader));
  }

  @Test
  public void parse_lenientMode_allowsNonStandardJson() {
    // Lenient mode allows unquoted names
    JsonReader reader = new JsonReader(new StringReader("{name: \"value\"}"));
    reader.setLenient(true);
    JsonElement result = Streams.parse(reader);
    assertTrue(result.isJsonObject());
    assertEquals("value", result.getAsJsonObject().get("name").getAsString());
  }

  @Test
  public void parse_whitespaceAroundValue_handledCorrectly() {
    JsonReader reader = new JsonReader(new StringReader("  \n\t  42  \n\t  "));
    JsonElement result = Streams.parse(reader);
    assertEquals(42, result.getAsInt());
  }

  // ==========================================================================
  // write tests
  // ==========================================================================

  @Test
  public void write_jsonNull_writesNull() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    Streams.write(JsonNull.INSTANCE, writer);
    assertEquals("null", stringWriter.toString());
  }

  @Test
  public void write_jsonPrimitiveString_writesQuotedString() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    Streams.write(new JsonPrimitive("hello"), writer);
    assertEquals("\"hello\"", stringWriter.toString());
  }

  @Test
  public void write_jsonPrimitiveInteger_writesNumber() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    Streams.write(new JsonPrimitive(42), writer);
    assertEquals("42", stringWriter.toString());
  }

  @Test
  public void write_jsonPrimitiveDouble_writesNumber() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    Streams.write(new JsonPrimitive(3.14), writer);
    assertEquals("3.14", stringWriter.toString());
  }

  @Test
  public void write_jsonPrimitiveBoolean_writesBoolean() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    Streams.write(new JsonPrimitive(true), writer);
    assertEquals("true", stringWriter.toString());
  }

  @Test
  public void write_emptyJsonArray_writesEmptyArray() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    Streams.write(new JsonArray(), writer);
    assertEquals("[]", stringWriter.toString());
  }

  @Test
  public void write_jsonArrayWithElements_writesArrayWithElements() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    JsonArray array = new JsonArray();
    array.add(1);
    array.add(2);
    array.add(3);
    Streams.write(array, writer);
    assertEquals("[1,2,3]", stringWriter.toString());
  }

  @Test
  public void write_emptyJsonObject_writesEmptyObject() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    Streams.write(new JsonObject(), writer);
    assertEquals("{}", stringWriter.toString());
  }

  @Test
  public void write_jsonObjectWithProperties_writesObjectWithProperties() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    JsonObject obj = new JsonObject();
    obj.addProperty("name", "John");
    obj.addProperty("age", 30);
    Streams.write(obj, writer);
    // Properties may be in any order, so check contains
    String result = stringWriter.toString();
    assertTrue(result.contains("\"name\":\"John\""));
    assertTrue(result.contains("\"age\":30"));
  }

  @Test
  public void write_nestedStructure_writesCorrectlyNestedJson() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    JsonObject obj = new JsonObject();
    JsonArray array = new JsonArray();
    JsonObject nested = new JsonObject();
    nested.addProperty("value", 1);
    array.add(nested);
    obj.add("items", array);
    Streams.write(obj, writer);
    assertEquals("{\"items\":[{\"value\":1}]}", stringWriter.toString());
  }

  @Test
  public void write_stringWithSpecialChars_escapesCorrectly() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    Streams.write(new JsonPrimitive("hello\nworld\t!"), writer);
    assertEquals("\"hello\\nworld\\t!\"", stringWriter.toString());
  }

  @Test
  public void write_nullElement_writesNull() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    Streams.write(null, writer);
    assertEquals("null", stringWriter.toString());
  }

  @Test
  public void write_withIndent_writesFormattedJson() throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    writer.setIndent("  ");
    JsonObject obj = new JsonObject();
    obj.addProperty("key", "value");
    Streams.write(obj, writer);
    String expected = "{\n  \"key\": \"value\"\n}";
    assertEquals(expected, stringWriter.toString());
  }

  // ==========================================================================
  // writerForAppendable tests
  // ==========================================================================

  @Test
  public void writerForAppendable_withWriter_returnsSameWriter() {
    StringWriter writer = new StringWriter();
    Writer result = Streams.writerForAppendable(writer);
    assertSame(writer, result);
  }

  @Test
  public void writerForAppendable_withStringBuilder_returnsNewWriter() {
    StringBuilder builder = new StringBuilder();
    Writer result = Streams.writerForAppendable(builder);
    assertNotSame(builder, result);
    assertTrue(result instanceof Writer);
  }

  @Test
  public void writerForAppendable_withStringBuffer_returnsNewWriter() {
    StringBuffer buffer = new StringBuffer();
    Writer result = Streams.writerForAppendable(buffer);
    assertNotSame(buffer, result);
    assertTrue(result instanceof Writer);
  }

  @Test
  public void writerForAppendable_writeSingleChar_appendsToAppendable() throws IOException {
    StringBuilder builder = new StringBuilder();
    Writer writer = Streams.writerForAppendable(builder);
    writer.write('A');
    assertEquals("A", builder.toString());
  }

  @Test
  public void writerForAppendable_writeCharArray_appendsToAppendable() throws IOException {
    StringBuilder builder = new StringBuilder();
    Writer writer = Streams.writerForAppendable(builder);
    writer.write(new char[] {'H', 'e', 'l', 'l', 'o'});
    assertEquals("Hello", builder.toString());
  }

  @Test
  public void writerForAppendable_writeCharArrayWithOffset_appendsSubset() throws IOException {
    StringBuilder builder = new StringBuilder();
    Writer writer = Streams.writerForAppendable(builder);
    writer.write(new char[] {'H', 'e', 'l', 'l', 'o'}, 1, 3);
    assertEquals("ell", builder.toString());
  }

  @Test
  public void writerForAppendable_writeString_appendsToAppendable() throws IOException {
    StringBuilder builder = new StringBuilder();
    Writer writer = Streams.writerForAppendable(builder);
    writer.write("Hello World");
    assertEquals("Hello World", builder.toString());
  }

  @Test
  public void writerForAppendable_writeStringWithOffset_appendsSubset() throws IOException {
    StringBuilder builder = new StringBuilder();
    Writer writer = Streams.writerForAppendable(builder);
    writer.write("Hello World", 6, 5);
    assertEquals("World", builder.toString());
  }

  @Test
  public void writerForAppendable_appendCharSequence_appendsToAppendable() throws IOException {
    StringBuilder builder = new StringBuilder();
    Writer writer = Streams.writerForAppendable(builder);
    CharSequence cs = "Hello";
    writer.append(cs);
    assertEquals("Hello", builder.toString());
  }

  @Test
  public void writerForAppendable_appendCharSequenceWithRange_appendsSubset() throws IOException {
    StringBuilder builder = new StringBuilder();
    Writer writer = Streams.writerForAppendable(builder);
    CharSequence cs = "Hello World";
    writer.append(cs, 0, 5);
    assertEquals("Hello", builder.toString());
  }

  @Test
  public void writerForAppendable_appendNull_appendsNullString() throws IOException {
    StringBuilder builder = new StringBuilder();
    Writer writer = Streams.writerForAppendable(builder);
    writer.append(null);
    assertEquals("null", builder.toString());
  }

  @Test
  public void writerForAppendable_multipleWrites_appendsAll() throws IOException {
    StringBuilder builder = new StringBuilder();
    Writer writer = Streams.writerForAppendable(builder);
    writer.write('H');
    writer.write("ello");
    writer.write(new char[] {' ', 'W', 'o', 'r', 'l', 'd'});
    assertEquals("Hello World", builder.toString());
  }

  @Test
  public void writerForAppendable_flush_noOp() throws IOException {
    StringBuilder builder = new StringBuilder();
    Writer writer = Streams.writerForAppendable(builder);
    writer.write("Hello");
    writer.flush(); // Should not throw
    assertEquals("Hello", builder.toString());
  }

  @Test
  public void writerForAppendable_close_noOp() throws IOException {
    StringBuilder builder = new StringBuilder();
    Writer writer = Streams.writerForAppendable(builder);
    writer.write("Hello");
    writer.close(); // Should not throw
    assertEquals("Hello", builder.toString());
  }

  @Test
  public void writerForAppendable_writeStringNullThrowsNPE() {
    StringBuilder builder = new StringBuilder();
    Writer writer = Streams.writerForAppendable(builder);
    assertThrows(NullPointerException.class, () -> writer.write((String) null, 0, 0));
  }

  @Test
  public void writerForAppendable_appendReturnsWriter() throws IOException {
    StringBuilder builder = new StringBuilder();
    Writer writer = Streams.writerForAppendable(builder);
    Writer returned = writer.append("test");
    assertSame(writer, returned);
  }

  @Test
  public void writerForAppendable_appendRangeReturnsWriter() throws IOException {
    StringBuilder builder = new StringBuilder();
    Writer writer = Streams.writerForAppendable(builder);
    Writer returned = writer.append("testing", 0, 4);
    assertSame(writer, returned);
    assertEquals("test", builder.toString());
  }

  @Test
  public void writerForAppendable_customAppendable_works() throws IOException {
    // Custom Appendable that counts characters
    class CountingAppendable implements Appendable {
      int charCount = 0;
      StringBuilder sb = new StringBuilder();

      @Override
      public Appendable append(CharSequence csq) {
        if (csq != null) {
          charCount += csq.length();
          sb.append(csq);
        }
        return this;
      }

      @Override
      public Appendable append(CharSequence csq, int start, int end) {
        charCount += (end - start);
        sb.append(csq, start, end);
        return this;
      }

      @Override
      public Appendable append(char c) {
        charCount++;
        sb.append(c);
        return this;
      }
    }

    CountingAppendable counting = new CountingAppendable();
    Writer writer = Streams.writerForAppendable(counting);
    writer.write("Hello");
    writer.write(' ');
    writer.write("World");

    assertEquals("Hello World", counting.sb.toString());
    assertEquals(11, counting.charCount);
  }

  // ==========================================================================
  // Integration tests - parse and write round-trip
  // ==========================================================================

  @Test
  public void parseAndWrite_roundTrip_producesEquivalentJson() throws IOException {
    String originalJson = "{\"name\":\"John\",\"age\":30,\"active\":true}";
    JsonReader reader = new JsonReader(new StringReader(originalJson));
    JsonElement element = Streams.parse(reader);

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    Streams.write(element, writer);

    // Parse again to compare
    JsonReader reader2 = new JsonReader(new StringReader(stringWriter.toString()));
    JsonElement element2 = Streams.parse(reader2);

    assertEquals(element, element2);
  }

  @Test
  public void parseAndWrite_complexStructure_preservesStructure() throws IOException {
    String originalJson =
        "[{\"id\":1,\"items\":[1,2,3]},{\"id\":2,\"items\":[4,5,6]}]";
    JsonReader reader = new JsonReader(new StringReader(originalJson));
    JsonElement element = Streams.parse(reader);

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    Streams.write(element, writer);

    // Verify the output
    JsonReader reader2 = new JsonReader(new StringReader(stringWriter.toString()));
    JsonElement element2 = Streams.parse(reader2);

    assertEquals(element, element2);
  }

  @Test
  public void write_usingAppendableWriter_worksCorrectly() throws IOException {
    StringBuilder builder = new StringBuilder();
    Writer appendableWriter = Streams.writerForAppendable(builder);
    JsonWriter jsonWriter = new JsonWriter(appendableWriter);

    JsonObject obj = new JsonObject();
    obj.addProperty("message", "Hello");
    Streams.write(obj, jsonWriter);

    assertEquals("{\"message\":\"Hello\"}", builder.toString());
  }
}
