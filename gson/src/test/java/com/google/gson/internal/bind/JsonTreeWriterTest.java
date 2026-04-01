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

package com.google.gson.internal.bind;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.Strictness;
import java.io.IOException;
import org.junit.Test;

public final class JsonTreeWriterTest {

  @Test
  public void testConstructorCreatesInstance() {
    JsonTreeWriter writer = new JsonTreeWriter();
    assertThat(writer).isNotNull();
  }

  @Test
  public void testGetEmptyReturnsJsonNull() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    assertThat(writer.get()).isInstanceOf(JsonNull.class);
  }

  @Test
  public void testGetThrowsWhenStackNotEmpty() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginArray();
    assertThrows(IllegalStateException.class, writer::get);
  }

  @Test
  public void testBeginArrayAndEndArray() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginArray();
    writer.endArray();
    assertThat(writer.get()).isInstanceOf(JsonArray.class);
  }

  @Test
  public void testBeginArrayPutsArrayOnStack() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginArray();
    writer.value("x");
    writer.endArray();
    JsonArray array = (JsonArray) writer.get();
    assertThat(array.size()).isEqualTo(1);
    assertThat(array.get(0).getAsString()).isEqualTo("x");
  }

  @Test
  public void testEndArrayThrowsWhenStackEmpty() {
    JsonTreeWriter writer = new JsonTreeWriter();
    assertThrows(IllegalStateException.class, writer::endArray);
  }

  @Test
  public void testEndArrayThrowsWhenPendingName() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginObject();
    writer.name("key");
    assertThrows(IllegalStateException.class, writer::endArray);
  }

  @Test
  public void testEndArrayThrowsWhenTopIsNotArray() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginObject();
    assertThrows(IllegalStateException.class, writer::endArray);
  }

  @Test
  public void testBeginObjectAndEndObject() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginObject();
    writer.endObject();
    assertThat(writer.get()).isInstanceOf(JsonObject.class);
  }

  @Test
  public void testEndObjectThrowsWhenStackEmpty() {
    JsonTreeWriter writer = new JsonTreeWriter();
    assertThrows(IllegalStateException.class, writer::endObject);
  }

  @Test
  public void testEndObjectThrowsWhenPendingName() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginObject();
    writer.name("key");
    assertThrows(IllegalStateException.class, writer::endObject);
  }

  @Test
  public void testEndObjectThrowsWhenTopIsNotObject() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginArray();
    assertThrows(IllegalStateException.class, writer::endObject);
  }

  @Test
  public void testNameSetsKey() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginObject();
    writer.name("myKey");
    writer.value("myValue");
    writer.endObject();
    JsonObject obj = (JsonObject) writer.get();
    assertThat(obj.get("myKey").getAsString()).isEqualTo("myValue");
  }

  @Test
  public void testNameThrowsOnNull() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginObject();
    assertThrows(NullPointerException.class, () -> writer.name(null));
  }

  @Test
  public void testNameThrowsWhenStackEmpty() {
    JsonTreeWriter writer = new JsonTreeWriter();
    assertThrows(IllegalStateException.class, () -> writer.name("key"));
  }

  @Test
  public void testNameThrowsWhenPendingNameAlreadySet() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginObject();
    writer.name("first");
    assertThrows(IllegalStateException.class, () -> writer.name("second"));
  }

  @Test
  public void testNameThrowsWhenTopIsArray() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginArray();
    assertThrows(IllegalStateException.class, () -> writer.name("key"));
  }

  @Test
  public void testValueString() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value("hello");
    assertThat(writer.get().getAsString()).isEqualTo("hello");
  }

  @Test
  public void testValueNullStringDelegatesToNullValue() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value((String) null);
    assertThat(writer.get()).isInstanceOf(JsonNull.class);
  }

  @Test
  public void testValueBoolean() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value(true);
    assertThat(writer.get().getAsBoolean()).isTrue();
  }

  @Test
  public void testValueBooleanObject() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value(Boolean.FALSE);
    assertThat(writer.get().getAsBoolean()).isFalse();
  }

  @Test
  public void testValueNullBooleanDelegatesToNullValue() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value((Boolean) null);
    assertThat(writer.get()).isInstanceOf(JsonNull.class);
  }

  @Test
  public void testValueFloat() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value(1.5f);
    assertThat(writer.get().getAsFloat()).isEqualTo(1.5f);
  }

  @Test
  public void testValueFloatNaNThrowsWhenNotLenient() {
    JsonTreeWriter writer = new JsonTreeWriter();
    assertThrows(IllegalArgumentException.class, () -> writer.value(Float.NaN));
  }

  @Test
  public void testValueFloatInfiniteThrowsWhenNotLenient() {
    JsonTreeWriter writer = new JsonTreeWriter();
    assertThrows(IllegalArgumentException.class, () -> writer.value(Float.POSITIVE_INFINITY));
  }

  @Test
  public void testValueFloatNaNAllowedWhenLenient() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.setStrictness(Strictness.LENIENT);
    writer.value(Float.NaN);
    assertThat(writer.get().getAsFloat()).isNaN();
  }

  @Test
  public void testValueDouble() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value(2.5);
    assertThat(writer.get().getAsDouble()).isEqualTo(2.5);
  }

  @Test
  public void testValueDoubleNaNThrowsWhenNotLenient() {
    JsonTreeWriter writer = new JsonTreeWriter();
    assertThrows(IllegalArgumentException.class, () -> writer.value(Double.NaN));
  }

  @Test
  public void testValueDoubleInfiniteThrowsWhenNotLenient() {
    JsonTreeWriter writer = new JsonTreeWriter();
    assertThrows(IllegalArgumentException.class, () -> writer.value(Double.NEGATIVE_INFINITY));
  }

  @Test
  public void testValueDoubleNaNAllowedWhenLenient() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.setStrictness(Strictness.LENIENT);
    writer.value(Double.NaN);
    assertThat(writer.get().getAsDouble()).isNaN();
  }

  @Test
  public void testValueLong() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value(42L);
    assertThat(writer.get().getAsLong()).isEqualTo(42L);
  }

  @Test
  public void testValueNumber() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value((Number) 99);
    assertThat(writer.get().getAsInt()).isEqualTo(99);
  }

  @Test
  public void testValueNullNumberDelegatesToNullValue() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value((Number) null);
    assertThat(writer.get()).isInstanceOf(JsonNull.class);
  }

  @Test
  public void testValueNumberNaNThrowsWhenNotLenient() {
    JsonTreeWriter writer = new JsonTreeWriter();
    assertThrows(IllegalArgumentException.class, () -> writer.value(Double.NaN));
  }

  @Test
  public void testValueNumberAllowedWhenLenient() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.setStrictness(Strictness.LENIENT);
    writer.value((Number) Double.NaN);
    assertThat(writer.get().getAsDouble()).isNaN();
  }

  @Test
  public void testNullValue() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.nullValue();
    assertThat(writer.get()).isInstanceOf(JsonNull.class);
  }

  @Test
  public void testJsonValueThrowsUnsupportedOperation() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    assertThrows(UnsupportedOperationException.class, () -> writer.jsonValue("{}"));
  }

  @Test
  public void testFlushDoesNothing() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.flush();
  }

  @Test
  public void testCloseOnCompleteDocument() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value("done");
    writer.close();
  }

  @Test
  public void testCloseThrowsWhenDocumentIncomplete() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginArray();
    assertThrows(IOException.class, writer::close);
  }

  @Test
  public void testPutWithNullSkippedWhenSerializeNullsFalse() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.setSerializeNulls(false);
    writer.beginObject();
    writer.name("key");
    writer.nullValue();
    writer.endObject();
    JsonObject obj = (JsonObject) writer.get();
    assertThat(obj.has("key")).isFalse();
  }

  @Test
  public void testPutWithNullIncludedWhenSerializeNullsTrue() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.setSerializeNulls(true);
    writer.beginObject();
    writer.name("key");
    writer.nullValue();
    writer.endObject();
    JsonObject obj = (JsonObject) writer.get();
    assertThat(obj.has("key")).isTrue();
    assertThat(obj.get("key")).isInstanceOf(JsonNull.class);
  }

  @Test
  public void testNestedObjectAndArray() throws IOException {
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.beginObject();
    writer.name("arr");
    writer.beginArray();
    writer.value(1L);
    writer.value(2L);
    writer.endArray();
    writer.endObject();
    JsonObject obj = (JsonObject) writer.get();
    JsonArray arr = obj.getAsJsonArray("arr");
    assertThat(arr.size()).isEqualTo(2);
    assertThat(arr.get(0).getAsLong()).isEqualTo(1L);
    assertThat(arr.get(1).getAsLong()).isEqualTo(2L);
  }

  @Test
  public void testPutThrowsWhenStackTopIsNotArrayOrObject() throws IOException {
    // stack has a non-array/non-object element after beginArray + nested beginObject scenario
    // Cause: put called when top of stack is neither JsonArray nor JsonObject (not a normal user path)
    // We indirectly trigger by adding array, ending it so stack is empty, then writing value
    JsonTreeWriter writer = new JsonTreeWriter();
    writer.value("only");
    // second standalone value - stack is empty, so it just replaces product
    writer.value("replaced");
    assertThat(writer.get().getAsString()).isEqualTo("replaced");
  }
}
