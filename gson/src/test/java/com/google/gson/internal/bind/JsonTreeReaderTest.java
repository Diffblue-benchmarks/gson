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
import static org.junit.Assert.assertThrows;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import org.junit.Test;

/** Unit tests for {@link JsonTreeReader}. */
public final class JsonTreeReaderTest {

  @Test
  public void testConstructorWithJsonElement() throws IOException {
    JsonObject obj = new JsonObject();
    JsonTreeReader reader = new JsonTreeReader(obj);
    assertThat(reader.peek()).isEqualTo(JsonToken.BEGIN_OBJECT);
  }

  @Test
  public void testBeginAndEndArray() throws IOException {
    JsonArray array = new JsonArray();
    array.add(new JsonPrimitive(1));
    JsonTreeReader reader = new JsonTreeReader(array);

    reader.beginArray();
    assertThat(reader.peek()).isEqualTo(JsonToken.NUMBER);
    reader.nextInt();
    reader.endArray();
    assertThat(reader.peek()).isEqualTo(JsonToken.END_DOCUMENT);
  }

  @Test
  public void testEndArrayIncrementsPathIndex() throws IOException {
    JsonArray outer = new JsonArray();
    JsonArray inner = new JsonArray();
    outer.add(inner);
    outer.add(new JsonPrimitive(42));

    JsonTreeReader reader = new JsonTreeReader(outer);
    reader.beginArray();
    reader.beginArray();
    reader.endArray();
    assertThat(reader.peek()).isEqualTo(JsonToken.NUMBER);
  }

  @Test
  public void testBeginAndEndObject() throws IOException {
    JsonObject object = new JsonObject();
    object.addProperty("key", "value");
    JsonTreeReader reader = new JsonTreeReader(object);

    reader.beginObject();
    assertThat(reader.hasNext()).isTrue();
    assertThat(reader.nextName()).isEqualTo("key");
    assertThat(reader.nextString()).isEqualTo("value");
    reader.endObject();
    assertThat(reader.peek()).isEqualTo(JsonToken.END_DOCUMENT);
  }

  @Test
  public void testEndObjectIncrementsPathIndex() throws IOException {
    JsonArray array = new JsonArray();
    JsonObject obj = new JsonObject();
    array.add(obj);
    array.add(new JsonPrimitive(1));

    JsonTreeReader reader = new JsonTreeReader(array);
    reader.beginArray();
    reader.beginObject();
    reader.endObject();
    assertThat(reader.peek()).isEqualTo(JsonToken.NUMBER);
  }

  @Test
  public void testHasNextReturnsTrueForArray() throws IOException {
    JsonArray array = new JsonArray();
    array.add(new JsonPrimitive(1));
    JsonTreeReader reader = new JsonTreeReader(array);
    reader.beginArray();
    assertThat(reader.hasNext()).isTrue();
  }

  @Test
  public void testHasNextReturnsFalseAtEndOfArray() throws IOException {
    JsonArray array = new JsonArray();
    JsonTreeReader reader = new JsonTreeReader(array);
    reader.beginArray();
    assertThat(reader.hasNext()).isFalse();
  }

  @Test
  public void testHasNextReturnsFalseAtEndDocument() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive(1));
    reader.nextInt();
    assertThat(reader.hasNext()).isFalse();
  }

  @Test
  public void testPeekEndDocument() throws IOException {
    JsonArray array = new JsonArray();
    JsonTreeReader reader = new JsonTreeReader(array);
    reader.beginArray();
    reader.endArray();
    assertThat(reader.peek()).isEqualTo(JsonToken.END_DOCUMENT);
  }

  @Test
  public void testPeekBeginObject() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonObject());
    assertThat(reader.peek()).isEqualTo(JsonToken.BEGIN_OBJECT);
  }

  @Test
  public void testPeekBeginArray() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonArray());
    assertThat(reader.peek()).isEqualTo(JsonToken.BEGIN_ARRAY);
  }

  @Test
  public void testPeekString() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive("hello"));
    assertThat(reader.peek()).isEqualTo(JsonToken.STRING);
  }

  @Test
  public void testPeekBoolean() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive(true));
    assertThat(reader.peek()).isEqualTo(JsonToken.BOOLEAN);
  }

  @Test
  public void testPeekNumber() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive(42));
    assertThat(reader.peek()).isEqualTo(JsonToken.NUMBER);
  }

  @Test
  public void testPeekNull() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(JsonNull.INSTANCE);
    assertThat(reader.peek()).isEqualTo(JsonToken.NULL);
  }

  @Test
  public void testPeekClosedThrows() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive(1));
    reader.close();
    assertThrows(IllegalStateException.class, reader::peek);
  }

  @Test
  public void testPeekObjectIteratorEndObject() throws IOException {
    JsonObject object = new JsonObject();
    JsonTreeReader reader = new JsonTreeReader(object);
    reader.beginObject();
    assertThat(reader.peek()).isEqualTo(JsonToken.END_OBJECT);
  }

  @Test
  public void testPeekArrayIteratorEndArray() throws IOException {
    JsonArray array = new JsonArray();
    JsonTreeReader reader = new JsonTreeReader(array);
    reader.beginArray();
    assertThat(reader.peek()).isEqualTo(JsonToken.END_ARRAY);
  }

  @Test
  public void testNextName() throws IOException {
    JsonObject object = new JsonObject();
    object.addProperty("foo", "bar");
    JsonTreeReader reader = new JsonTreeReader(object);
    reader.beginObject();
    assertThat(reader.nextName()).isEqualTo("foo");
  }

  @Test
  public void testNextString() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive("hello"));
    assertThat(reader.nextString()).isEqualTo("hello");
  }

  @Test
  public void testNextStringFromNumber() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive(3.14));
    assertThat(reader.nextString()).isEqualTo("3.14");
  }

  @Test
  public void testNextStringThrowsOnWrongToken() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonObject());
    assertThrows(IllegalStateException.class, reader::nextString);
  }

  @Test
  public void testNextBoolean() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive(true));
    assertThat(reader.nextBoolean()).isTrue();
  }

  @Test
  public void testNextBooleanFalse() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive(false));
    assertThat(reader.nextBoolean()).isFalse();
  }

  @Test
  public void testNextBooleanThrowsOnWrongToken() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive("notbool"));
    assertThrows(IllegalStateException.class, reader::nextBoolean);
  }

  @Test
  public void testNextNull() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(JsonNull.INSTANCE);
    reader.nextNull();
    assertThat(reader.peek()).isEqualTo(JsonToken.END_DOCUMENT);
  }

  @Test
  public void testNextNullThrowsOnWrongToken() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive(1));
    assertThrows(IllegalStateException.class, reader::nextNull);
  }

  @Test
  public void testNextDouble() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive(1.5));
    assertThat(reader.nextDouble()).isEqualTo(1.5);
  }

  @Test
  public void testNextDoubleFromString() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive("2.5"));
    assertThat(reader.nextDouble()).isEqualTo(2.5);
  }

  @Test
  public void testNextDoubleThrowsOnWrongToken() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonObject());
    assertThrows(IllegalStateException.class, reader::nextDouble);
  }

  @Test
  public void testNextLong() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive(100L));
    assertThat(reader.nextLong()).isEqualTo(100L);
  }

  @Test
  public void testNextLongThrowsOnWrongToken() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonObject());
    assertThrows(IllegalStateException.class, reader::nextLong);
  }

  @Test
  public void testNextInt() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive(42));
    assertThat(reader.nextInt()).isEqualTo(42);
  }

  @Test
  public void testNextIntThrowsOnWrongToken() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonObject());
    assertThrows(IllegalStateException.class, reader::nextInt);
  }

  @Test
  public void testNextJsonElement() throws IOException {
    JsonPrimitive primitive = new JsonPrimitive("test");
    JsonTreeReader reader = new JsonTreeReader(primitive);
    JsonElement element = reader.nextJsonElement();
    assertThat(element).isEqualTo(primitive);
  }

  @Test
  public void testNextJsonElementThrowsOnName() throws IOException {
    JsonObject obj = new JsonObject();
    obj.addProperty("k", "v");
    JsonTreeReader reader = new JsonTreeReader(obj);
    reader.beginObject();
    assertThrows(IllegalStateException.class, reader::nextJsonElement);
  }

  @Test
  public void testNextJsonElementThrowsOnEndDocument() throws IOException {
    JsonArray array = new JsonArray();
    JsonTreeReader reader = new JsonTreeReader(array);
    reader.beginArray();
    reader.endArray();
    assertThrows(IllegalStateException.class, reader::nextJsonElement);
  }

  @Test
  public void testClose() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive(1));
    reader.close();
    assertThrows(IllegalStateException.class, reader::peek);
  }

  @Test
  public void testSkipValuePrimitive() throws IOException {
    JsonArray array = new JsonArray();
    array.add(new JsonPrimitive(1));
    array.add(new JsonPrimitive(2));
    JsonTreeReader reader = new JsonTreeReader(array);
    reader.beginArray();
    reader.skipValue();
    assertThat(reader.nextInt()).isEqualTo(2);
  }

  @Test
  public void testSkipValueName() throws IOException {
    JsonObject obj = new JsonObject();
    obj.addProperty("skip", "val");
    JsonTreeReader reader = new JsonTreeReader(obj);
    reader.beginObject();
    reader.skipValue(); // skips name
    reader.skipValue(); // skips value
    reader.endObject();
    assertThat(reader.peek()).isEqualTo(JsonToken.END_DOCUMENT);
  }

  @Test
  public void testSkipValueEndArray() throws IOException {
    JsonArray array = new JsonArray();
    JsonTreeReader reader = new JsonTreeReader(array);
    reader.beginArray();
    reader.skipValue(); // should endArray
    assertThat(reader.peek()).isEqualTo(JsonToken.END_DOCUMENT);
  }

  @Test
  public void testSkipValueEndObject() throws IOException {
    JsonObject obj = new JsonObject();
    JsonTreeReader reader = new JsonTreeReader(obj);
    reader.beginObject();
    reader.skipValue(); // should endObject
    assertThat(reader.peek()).isEqualTo(JsonToken.END_DOCUMENT);
  }

  @Test
  public void testSkipValueEndDocument() throws IOException {
    JsonArray array = new JsonArray();
    JsonTreeReader reader = new JsonTreeReader(array);
    reader.beginArray();
    reader.endArray();
    reader.skipValue(); // should be a no-op for END_DOCUMENT
    assertThat(reader.peek()).isEqualTo(JsonToken.END_DOCUMENT);
  }

  @Test
  public void testToString() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonObject());
    assertThat(reader.toString()).isEqualTo("JsonTreeReader at path $");
  }

  @Test
  public void testPromoteNameToValue() throws IOException {
    JsonObject obj = new JsonObject();
    obj.addProperty("myKey", "myValue");
    JsonTreeReader reader = new JsonTreeReader(obj);
    reader.beginObject();
    reader.promoteNameToValue();
    assertThat(reader.nextString()).isEqualTo("myKey");
    assertThat(reader.nextString()).isEqualTo("myValue");
    reader.endObject();
  }

  @Test
  public void testGetPath() throws IOException {
    JsonObject obj = new JsonObject();
    obj.addProperty("key", "value");
    JsonTreeReader reader = new JsonTreeReader(obj);
    reader.beginObject();
    reader.nextName();
    assertThat(reader.getPath()).isEqualTo("$.key");
  }

  @Test
  public void testGetPathArray() throws IOException {
    JsonArray array = new JsonArray();
    array.add(new JsonPrimitive(1));
    array.add(new JsonPrimitive(2));
    JsonTreeReader reader = new JsonTreeReader(array);
    reader.beginArray();
    reader.nextInt();
    assertThat(reader.getPath()).isEqualTo("$[1]");
  }

  @Test
  public void testGetPreviousPath() throws IOException {
    JsonArray array = new JsonArray();
    array.add(new JsonPrimitive(1));
    JsonTreeReader reader = new JsonTreeReader(array);
    reader.beginArray();
    reader.nextInt();
    assertThat(reader.getPreviousPath()).isEqualTo("$[0]");
  }

  @Test
  public void testGetPathRoot() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonPrimitive(1));
    assertThat(reader.getPath()).isEqualTo("$");
  }

  @Test
  public void testNextStringInArrayIncrementsPathIndex() throws IOException {
    JsonArray array = new JsonArray();
    array.add(new JsonPrimitive("a"));
    array.add(new JsonPrimitive("b"));
    JsonTreeReader reader = new JsonTreeReader(array);
    reader.beginArray();
    reader.nextString();
    assertThat(reader.getPath()).isEqualTo("$[1]");
  }

  @Test
  public void testNextBooleanInArrayIncrementsPathIndex() throws IOException {
    JsonArray array = new JsonArray();
    array.add(new JsonPrimitive(true));
    array.add(new JsonPrimitive(false));
    JsonTreeReader reader = new JsonTreeReader(array);
    reader.beginArray();
    reader.nextBoolean();
    assertThat(reader.getPath()).isEqualTo("$[1]");
  }

  @Test
  public void testNextNullInArrayIncrementsPathIndex() throws IOException {
    JsonArray array = new JsonArray();
    array.add(JsonNull.INSTANCE);
    array.add(new JsonPrimitive(1));
    JsonTreeReader reader = new JsonTreeReader(array);
    reader.beginArray();
    reader.nextNull();
    assertThat(reader.getPath()).isEqualTo("$[1]");
  }
}
