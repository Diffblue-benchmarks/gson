/*
 * Copyright (C) 2026 Google Inc.
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

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.MalformedJsonException;
import java.io.IOException;
import org.junit.Test;

public class JsonTreeReaderTest {

  @Test
  public void testConstructor() throws IOException {
    JsonElement element = new JsonPrimitive("test");
    JsonTreeReader reader = new JsonTreeReader(element);
    assertThat(reader).isNotNull();
    assertThat(reader.peek()).isEqualTo(JsonToken.STRING);
  }

  @Test
  public void testBeginArray() throws IOException {
    JsonArray array = new JsonArray();
    array.add("element1");
    array.add("element2");
    JsonTreeReader reader = new JsonTreeReader(array);

    reader.beginArray();
    assertThat(reader.peek()).isEqualTo(JsonToken.STRING);
  }

  @Test
  public void testEndArray() throws IOException {
    JsonArray array = new JsonArray();
    JsonTreeReader reader = new JsonTreeReader(array);

    reader.beginArray();
    reader.endArray();
    assertThat(reader.peek()).isEqualTo(JsonToken.END_DOCUMENT);
  }

  @Test
  public void testBeginObject() throws IOException {
    JsonObject object = new JsonObject();
    object.addProperty("key", "value");
    JsonTreeReader reader = new JsonTreeReader(object);

    reader.beginObject();
    assertThat(reader.peek()).isEqualTo(JsonToken.NAME);
  }

  @Test
  public void testEndObject() throws IOException {
    JsonObject object = new JsonObject();
    JsonTreeReader reader = new JsonTreeReader(object);

    reader.beginObject();
    reader.endObject();
    assertThat(reader.peek()).isEqualTo(JsonToken.END_DOCUMENT);
  }

  @Test
  public void testHasNextInArray() throws IOException {
    JsonArray array = new JsonArray();
    array.add("element");
    JsonTreeReader reader = new JsonTreeReader(array);

    reader.beginArray();
    assertThat(reader.hasNext()).isTrue();
    reader.nextString();
    assertThat(reader.hasNext()).isFalse();
  }

  @Test
  public void testHasNextInObject() throws IOException {
    JsonObject object = new JsonObject();
    object.addProperty("key", "value");
    JsonTreeReader reader = new JsonTreeReader(object);

    reader.beginObject();
    assertThat(reader.hasNext()).isTrue();
    reader.nextName();
    reader.nextString();
    assertThat(reader.hasNext()).isFalse();
  }

  @Test
  public void testPeekEndDocument() throws IOException {
    JsonElement element = new JsonPrimitive("test");
    JsonTreeReader reader = new JsonTreeReader(element);
    reader.nextString();

    assertThat(reader.peek()).isEqualTo(JsonToken.END_DOCUMENT);
  }

  @Test
  public void testPeekBeginObject() throws IOException {
    JsonObject object = new JsonObject();
    JsonTreeReader reader = new JsonTreeReader(object);

    assertThat(reader.peek()).isEqualTo(JsonToken.BEGIN_OBJECT);
  }

  @Test
  public void testPeekBeginArray() throws IOException {
    JsonArray array = new JsonArray();
    JsonTreeReader reader = new JsonTreeReader(array);

    assertThat(reader.peek()).isEqualTo(JsonToken.BEGIN_ARRAY);
  }

  @Test
  public void testPeekString() throws IOException {
    JsonElement element = new JsonPrimitive("test");
    JsonTreeReader reader = new JsonTreeReader(element);

    assertThat(reader.peek()).isEqualTo(JsonToken.STRING);
  }

  @Test
  public void testPeekBoolean() throws IOException {
    JsonElement element = new JsonPrimitive(true);
    JsonTreeReader reader = new JsonTreeReader(element);

    assertThat(reader.peek()).isEqualTo(JsonToken.BOOLEAN);
  }

  @Test
  public void testPeekNumber() throws IOException {
    JsonElement element = new JsonPrimitive(42);
    JsonTreeReader reader = new JsonTreeReader(element);

    assertThat(reader.peek()).isEqualTo(JsonToken.NUMBER);
  }

  @Test
  public void testPeekNull() throws IOException {
    JsonElement element = JsonNull.INSTANCE;
    JsonTreeReader reader = new JsonTreeReader(element);

    assertThat(reader.peek()).isEqualTo(JsonToken.NULL);
  }

  @Test
  public void testPeekNameInObject() throws IOException {
    JsonObject object = new JsonObject();
    object.addProperty("key", "value");
    JsonTreeReader reader = new JsonTreeReader(object);

    reader.beginObject();
    assertThat(reader.peek()).isEqualTo(JsonToken.NAME);
  }

  @Test
  public void testPeekEndObjectWhenEmpty() throws IOException {
    JsonObject object = new JsonObject();
    JsonTreeReader reader = new JsonTreeReader(object);

    reader.beginObject();
    assertThat(reader.peek()).isEqualTo(JsonToken.END_OBJECT);
  }

  @Test
  public void testPeekEndArrayWhenEmpty() throws IOException {
    JsonArray array = new JsonArray();
    JsonTreeReader reader = new JsonTreeReader(array);

    reader.beginArray();
    assertThat(reader.peek()).isEqualTo(JsonToken.END_ARRAY);
  }

  @Test
  public void testPeekInArrayWithElements() throws IOException {
    JsonArray array = new JsonArray();
    array.add("element");
    JsonTreeReader reader = new JsonTreeReader(array);

    reader.beginArray();
    assertThat(reader.peek()).isEqualTo(JsonToken.STRING);
  }

  @Test
  public void testNextName() throws IOException {
    JsonObject object = new JsonObject();
    object.addProperty("testKey", "value");
    JsonTreeReader reader = new JsonTreeReader(object);

    reader.beginObject();
    String name = reader.nextName();
    assertThat(name).isEqualTo("testKey");
  }

  @Test
  public void testNextString() throws IOException {
    JsonElement element = new JsonPrimitive("testValue");
    JsonTreeReader reader = new JsonTreeReader(element);

    String value = reader.nextString();
    assertThat(value).isEqualTo("testValue");
  }

  @Test
  public void testNextStringFromNumber() throws IOException {
    JsonElement element = new JsonPrimitive(123);
    JsonTreeReader reader = new JsonTreeReader(element);

    String value = reader.nextString();
    assertThat(value).isEqualTo("123");
  }

  @Test
  public void testNextBoolean() throws IOException {
    JsonElement element = new JsonPrimitive(true);
    JsonTreeReader reader = new JsonTreeReader(element);

    boolean value = reader.nextBoolean();
    assertThat(value).isTrue();
  }

  @Test
  public void testNextBooleanFalse() throws IOException {
    JsonElement element = new JsonPrimitive(false);
    JsonTreeReader reader = new JsonTreeReader(element);

    boolean value = reader.nextBoolean();
    assertThat(value).isFalse();
  }

  @Test
  public void testNextNull() throws IOException {
    JsonElement element = JsonNull.INSTANCE;
    JsonTreeReader reader = new JsonTreeReader(element);

    reader.nextNull();
    assertThat(reader.peek()).isEqualTo(JsonToken.END_DOCUMENT);
  }

  @Test
  public void testNextDouble() throws IOException {
    JsonElement element = new JsonPrimitive(3.14);
    JsonTreeReader reader = new JsonTreeReader(element);

    double value = reader.nextDouble();
    assertThat(value).isEqualTo(3.14);
  }

  @Test
  public void testNextDoubleFromString() throws IOException {
    JsonElement element = new JsonPrimitive("2.718");
    JsonTreeReader reader = new JsonTreeReader(element);

    double value = reader.nextDouble();
    assertThat(value).isEqualTo(2.718);
  }

  @Test
  public void testNextLong() throws IOException {
    JsonElement element = new JsonPrimitive(123456789L);
    JsonTreeReader reader = new JsonTreeReader(element);

    long value = reader.nextLong();
    assertThat(value).isEqualTo(123456789L);
  }

  @Test
  public void testNextLongFromString() throws IOException {
    JsonElement element = new JsonPrimitive("987654321");
    JsonTreeReader reader = new JsonTreeReader(element);

    long value = reader.nextLong();
    assertThat(value).isEqualTo(987654321L);
  }

  @Test
  public void testNextInt() throws IOException {
    JsonElement element = new JsonPrimitive(42);
    JsonTreeReader reader = new JsonTreeReader(element);

    int value = reader.nextInt();
    assertThat(value).isEqualTo(42);
  }

  @Test
  public void testNextIntFromString() throws IOException {
    JsonElement element = new JsonPrimitive("123");
    JsonTreeReader reader = new JsonTreeReader(element);

    int value = reader.nextInt();
    assertThat(value).isEqualTo(123);
  }

  @Test
  public void testNextJsonElement() throws IOException {
    JsonElement element = new JsonPrimitive("test");
    JsonTreeReader reader = new JsonTreeReader(element);

    JsonElement result = reader.nextJsonElement();
    assertThat(result).isEqualTo(element);
  }

  @Test
  public void testClose() throws IOException {
    JsonElement element = new JsonPrimitive("test");
    JsonTreeReader reader = new JsonTreeReader(element);

    reader.close();

    try {
      reader.peek();
      throw new AssertionError("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertThat(e.getMessage()).contains("JsonReader is closed");
    }
  }

  @Test
  public void testSkipValueWithString() throws IOException {
    JsonElement element = new JsonPrimitive("skip me");
    JsonTreeReader reader = new JsonTreeReader(element);

    reader.skipValue();
    assertThat(reader.peek()).isEqualTo(JsonToken.END_DOCUMENT);
  }

  @Test
  public void testSkipValueWithName() throws IOException {
    JsonObject object = new JsonObject();
    object.addProperty("key", "value");
    JsonTreeReader reader = new JsonTreeReader(object);

    reader.beginObject();
    reader.skipValue();
    assertThat(reader.peek()).isEqualTo(JsonToken.STRING);
  }

  @Test
  public void testSkipValueEndArray() throws IOException {
    JsonArray array = new JsonArray();
    JsonTreeReader reader = new JsonTreeReader(array);

    reader.beginArray();
    reader.skipValue();
    assertThat(reader.peek()).isEqualTo(JsonToken.END_DOCUMENT);
  }

  @Test
  public void testSkipValueEndObject() throws IOException {
    JsonObject object = new JsonObject();
    JsonTreeReader reader = new JsonTreeReader(object);

    reader.beginObject();
    reader.skipValue();
    assertThat(reader.peek()).isEqualTo(JsonToken.END_DOCUMENT);
  }

  @Test
  public void testSkipValueEndDocument() throws IOException {
    JsonElement element = new JsonPrimitive("test");
    JsonTreeReader reader = new JsonTreeReader(element);
    reader.nextString();

    reader.skipValue();
    assertThat(reader.peek()).isEqualTo(JsonToken.END_DOCUMENT);
  }

  @Test
  public void testToString() {
    JsonElement element = new JsonPrimitive("test");
    JsonTreeReader reader = new JsonTreeReader(element);

    String result = reader.toString();
    assertThat(result).contains("JsonTreeReader");
    assertThat(result).contains("$");
  }

  @Test
  public void testPromoteNameToValue() throws IOException {
    JsonObject object = new JsonObject();
    object.addProperty("testKey", "testValue");
    JsonTreeReader reader = new JsonTreeReader(object);

    reader.beginObject();
    reader.promoteNameToValue();
    assertThat(reader.peek()).isEqualTo(JsonToken.STRING);
    assertThat(reader.nextString()).isEqualTo("testKey");
  }

  @Test
  public void testGetPath() throws IOException {
    JsonObject object = new JsonObject();
    object.addProperty("key", "value");
    JsonTreeReader reader = new JsonTreeReader(object);

    reader.beginObject();
    String path = reader.getPath();
    assertThat(path).isEqualTo("$.");
  }

  @Test
  public void testGetPathWithArrayIndex() throws IOException {
    JsonArray array = new JsonArray();
    array.add("element1");
    array.add("element2");
    JsonTreeReader reader = new JsonTreeReader(array);

    reader.beginArray();
    reader.nextString();
    String path = reader.getPath();
    assertThat(path).contains("[");
  }

  @Test
  public void testGetPathNested() throws IOException {
    JsonObject outer = new JsonObject();
    JsonObject inner = new JsonObject();
    inner.addProperty("innerKey", "value");
    outer.add("outerKey", inner);
    JsonTreeReader reader = new JsonTreeReader(outer);

    reader.beginObject();
    reader.nextName();
    reader.beginObject();
    String path = reader.getPath();
    assertThat(path).contains("outerKey");
  }

  @Test
  public void testGetPreviousPath() throws IOException {
    JsonArray array = new JsonArray();
    array.add("element");
    JsonTreeReader reader = new JsonTreeReader(array);

    reader.beginArray();
    reader.nextString();
    String path = reader.getPreviousPath();
    assertThat(path).isNotNull();
  }

  @Test
  public void testExpectThrowsWhenTokenMismatch() throws IOException {
    JsonElement element = new JsonPrimitive("test");
    JsonTreeReader reader = new JsonTreeReader(element);

    try {
      reader.beginArray();
      throw new AssertionError("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertThat(e.getMessage()).contains("Expected");
      assertThat(e.getMessage()).contains("BEGIN_ARRAY");
    }
  }

  @Test
  public void testNextStringThrowsWhenNotStringOrNumber() throws IOException {
    JsonElement element = new JsonPrimitive(true);
    JsonTreeReader reader = new JsonTreeReader(element);

    try {
      reader.nextString();
      throw new AssertionError("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertThat(e.getMessage()).contains("Expected");
      assertThat(e.getMessage()).contains("STRING");
    }
  }

  @Test
  public void testNextBooleanThrowsWhenNotBoolean() throws IOException {
    JsonElement element = new JsonPrimitive("not a boolean");
    JsonTreeReader reader = new JsonTreeReader(element);

    try {
      reader.nextBoolean();
      throw new AssertionError("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertThat(e.getMessage()).contains("Expected");
      assertThat(e.getMessage()).contains("BOOLEAN");
    }
  }

  @Test
  public void testNextNullThrowsWhenNotNull() throws IOException {
    JsonElement element = new JsonPrimitive("not null");
    JsonTreeReader reader = new JsonTreeReader(element);

    try {
      reader.nextNull();
      throw new AssertionError("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertThat(e.getMessage()).contains("Expected");
      assertThat(e.getMessage()).contains("NULL");
    }
  }

  @Test
  public void testNextDoubleThrowsWhenNotNumberOrString() throws IOException {
    JsonElement element = new JsonPrimitive(true);
    JsonTreeReader reader = new JsonTreeReader(element);

    try {
      reader.nextDouble();
      throw new AssertionError("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertThat(e.getMessage()).contains("Expected");
      assertThat(e.getMessage()).contains("NUMBER");
    }
  }

  @Test
  public void testNextLongThrowsWhenNotNumberOrString() throws IOException {
    JsonElement element = new JsonPrimitive(true);
    JsonTreeReader reader = new JsonTreeReader(element);

    try {
      reader.nextLong();
      throw new AssertionError("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertThat(e.getMessage()).contains("Expected");
      assertThat(e.getMessage()).contains("NUMBER");
    }
  }

  @Test
  public void testNextIntThrowsWhenNotNumberOrString() throws IOException {
    JsonElement element = new JsonPrimitive(true);
    JsonTreeReader reader = new JsonTreeReader(element);

    try {
      reader.nextInt();
      throw new AssertionError("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertThat(e.getMessage()).contains("Expected");
      assertThat(e.getMessage()).contains("NUMBER");
    }
  }

  @Test
  public void testNextJsonElementThrowsOnName() throws IOException {
    JsonObject object = new JsonObject();
    object.addProperty("key", "value");
    JsonTreeReader reader = new JsonTreeReader(object);

    reader.beginObject();
    try {
      reader.nextJsonElement();
      throw new AssertionError("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertThat(e.getMessage()).contains("Unexpected");
      assertThat(e.getMessage()).contains("NAME");
    }
  }

  @Test
  public void testNextJsonElementThrowsOnEndArray() throws IOException {
    JsonArray array = new JsonArray();
    JsonTreeReader reader = new JsonTreeReader(array);

    reader.beginArray();
    try {
      reader.nextJsonElement();
      throw new AssertionError("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertThat(e.getMessage()).contains("Unexpected");
      assertThat(e.getMessage()).contains("END_ARRAY");
    }
  }

  @Test
  public void testNextJsonElementThrowsOnEndObject() throws IOException {
    JsonObject object = new JsonObject();
    JsonTreeReader reader = new JsonTreeReader(object);

    reader.beginObject();
    try {
      reader.nextJsonElement();
      throw new AssertionError("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertThat(e.getMessage()).contains("Unexpected");
      assertThat(e.getMessage()).contains("END_OBJECT");
    }
  }

  @Test
  public void testNextJsonElementThrowsOnEndDocument() throws IOException {
    JsonElement element = new JsonPrimitive("test");
    JsonTreeReader reader = new JsonTreeReader(element);
    reader.nextString();

    try {
      reader.nextJsonElement();
      throw new AssertionError("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      assertThat(e.getMessage()).contains("Unexpected");
      assertThat(e.getMessage()).contains("END_DOCUMENT");
    }
  }

  @Test
  public void testArrayWithMultipleElements() throws IOException {
    JsonArray array = new JsonArray();
    array.add("first");
    array.add("second");
    array.add("third");
    JsonTreeReader reader = new JsonTreeReader(array);

    reader.beginArray();
    assertThat(reader.nextString()).isEqualTo("first");
    assertThat(reader.nextString()).isEqualTo("second");
    assertThat(reader.nextString()).isEqualTo("third");
    reader.endArray();
  }

  @Test
  public void testObjectWithMultipleProperties() throws IOException {
    JsonObject object = new JsonObject();
    object.addProperty("key1", "value1");
    object.addProperty("key2", "value2");
    JsonTreeReader reader = new JsonTreeReader(object);

    reader.beginObject();
    assertThat(reader.nextName()).isEqualTo("key1");
    assertThat(reader.nextString()).isEqualTo("value1");
    assertThat(reader.nextName()).isEqualTo("key2");
    assertThat(reader.nextString()).isEqualTo("value2");
    reader.endObject();
  }

  @Test
  public void testNestedArrayInObject() throws IOException {
    JsonObject object = new JsonObject();
    JsonArray array = new JsonArray();
    array.add("element");
    object.add("arrayKey", array);
    JsonTreeReader reader = new JsonTreeReader(object);

    reader.beginObject();
    assertThat(reader.nextName()).isEqualTo("arrayKey");
    reader.beginArray();
    assertThat(reader.nextString()).isEqualTo("element");
    reader.endArray();
    reader.endObject();
  }

  @Test
  public void testNestedObjectInArray() throws IOException {
    JsonArray array = new JsonArray();
    JsonObject object = new JsonObject();
    object.addProperty("key", "value");
    array.add(object);
    JsonTreeReader reader = new JsonTreeReader(array);

    reader.beginArray();
    reader.beginObject();
    assertThat(reader.nextName()).isEqualTo("key");
    assertThat(reader.nextString()).isEqualTo("value");
    reader.endObject();
    reader.endArray();
  }

  @Test
  public void testStackGrowth() throws IOException {
    JsonObject root = new JsonObject();
    JsonObject current = root;

    for (int i = 0; i < 40; i++) {
      JsonObject next = new JsonObject();
      current.add("nested" + i, next);
      current = next;
    }

    JsonTreeReader reader = new JsonTreeReader(root);
    reader.beginObject();
    for (int i = 0; i < 40; i++) {
      reader.nextName();
      reader.beginObject();
    }

    assertThat(reader.getPath()).isNotNull();
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testNextDoubleWithNaNInLenientMode() throws IOException {
    JsonElement element = new JsonPrimitive(Double.NaN);
    JsonTreeReader reader = new JsonTreeReader(element);
    reader.setLenient(true);

    double value = reader.nextDouble();
    assertThat(Double.isNaN(value)).isTrue();
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testNextDoubleWithInfinityInLenientMode() throws IOException {
    JsonElement element = new JsonPrimitive(Double.POSITIVE_INFINITY);
    JsonTreeReader reader = new JsonTreeReader(element);
    reader.setLenient(true);

    double value = reader.nextDouble();
    assertThat(Double.isInfinite(value)).isTrue();
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testNextDoubleWithNaNInStrictMode() throws IOException {
    JsonElement element = new JsonPrimitive(Double.NaN);
    JsonTreeReader reader = new JsonTreeReader(element);
    reader.setLenient(false);

    try {
      reader.nextDouble();
      throw new AssertionError("Expected MalformedJsonException");
    } catch (MalformedJsonException e) {
      assertThat(e.getMessage()).contains("NaN");
    }
  }

  @Test
  @SuppressWarnings("deprecation")
  public void testNextDoubleWithInfinityInStrictMode() throws IOException {
    JsonElement element = new JsonPrimitive(Double.POSITIVE_INFINITY);
    JsonTreeReader reader = new JsonTreeReader(element);
    reader.setLenient(false);

    try {
      reader.nextDouble();
      throw new AssertionError("Expected MalformedJsonException");
    } catch (MalformedJsonException e) {
      assertThat(e.getMessage()).contains("infinit");
    }
  }

  @Test
  public void testPeekAfterNextInArray() throws IOException {
    JsonArray array = new JsonArray();
    array.add("first");
    array.add("second");
    JsonTreeReader reader = new JsonTreeReader(array);

    reader.beginArray();
    reader.nextString();
    assertThat(reader.peek()).isEqualTo(JsonToken.STRING);
  }

  @Test
  public void testGetPathInArray() throws IOException {
    JsonArray array = new JsonArray();
    array.add("element1");
    array.add("element2");
    array.add("element3");
    JsonTreeReader reader = new JsonTreeReader(array);

    reader.beginArray();
    String path = reader.getPath();
    assertThat(path).isEqualTo("$[0]");
    reader.nextString();
    path = reader.getPath();
    assertThat(path).isEqualTo("$[1]");
  }

  @Test
  public void testGetPathInObject() throws IOException {
    JsonObject object = new JsonObject();
    object.addProperty("key", "value");
    JsonTreeReader reader = new JsonTreeReader(object);

    reader.beginObject();
    reader.nextName();
    String path = reader.getPath();
    assertThat(path).contains("key");
  }

  @Test
  public void testPromoteNameToValueAndReadValue() throws IOException {
    JsonObject object = new JsonObject();
    object.addProperty("myKey", "myValue");
    JsonTreeReader reader = new JsonTreeReader(object);

    reader.beginObject();
    reader.promoteNameToValue();
    assertThat(reader.nextString()).isEqualTo("myKey");
    assertThat(reader.nextString()).isEqualTo("myValue");
    reader.endObject();
  }
}
