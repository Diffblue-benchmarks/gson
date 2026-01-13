/*
 * Copyright (C) 2022 Google Inc.
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

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;

/**
 * Tests for the private delegate() method in {@link Gson.FutureTypeAdapter}. Since delegate() is
 * private, these tests exercise it through the public methods that call it: read(), write(), and
 * getSerializationDelegate().
 */
public class GsonClaude_delegateTest {

  // ==========================================================================
  // Tests for delegate() through getSerializationDelegate() - success path (line 1514)
  // ==========================================================================

  @Test
  public void testDelegate_returnsDelegateWhenSet_viaGetSerializationDelegate() {
    Gson.FutureTypeAdapter<String> futureAdapter = new Gson.FutureTypeAdapter<>();
    Gson gson = new Gson();
    TypeAdapter<String> stringAdapter = gson.getAdapter(String.class);

    futureAdapter.setDelegate(stringAdapter);

    // This calls delegate() which should return the delegate (line 1514)
    TypeAdapter<String> result = futureAdapter.getSerializationDelegate();
    assertThat(result).isSameInstanceAs(stringAdapter);
  }

  @Test
  public void testDelegate_returnsDelegateWhenSet_viaGetSerializationDelegate_multipleTypes() {
    Gson gson = new Gson();

    // Test with Integer type
    Gson.FutureTypeAdapter<Integer> intFutureAdapter = new Gson.FutureTypeAdapter<>();
    TypeAdapter<Integer> intAdapter = gson.getAdapter(Integer.class);
    intFutureAdapter.setDelegate(intAdapter);
    assertThat(intFutureAdapter.getSerializationDelegate()).isSameInstanceAs(intAdapter);

    // Test with Boolean type
    Gson.FutureTypeAdapter<Boolean> boolFutureAdapter = new Gson.FutureTypeAdapter<>();
    TypeAdapter<Boolean> boolAdapter = gson.getAdapter(Boolean.class);
    boolFutureAdapter.setDelegate(boolAdapter);
    assertThat(boolFutureAdapter.getSerializationDelegate()).isSameInstanceAs(boolAdapter);
  }

  // ==========================================================================
  // Tests for delegate() through getSerializationDelegate() - failure path (lines 1505, 1506, 1510)
  // ==========================================================================

  @Test
  public void testDelegate_throwsWhenDelegateNotSet_viaGetSerializationDelegate() {
    Gson.FutureTypeAdapter<String> futureAdapter = new Gson.FutureTypeAdapter<>();

    // This calls delegate() which should throw IllegalStateException (lines 1505, 1506, 1510)
    IllegalStateException exception =
        assertThrows(IllegalStateException.class, futureAdapter::getSerializationDelegate);
    assertThat(exception.getMessage())
        .contains("Adapter for type with cyclic dependency has been used");
    assertThat(exception.getMessage()).contains("before dependency has been resolved");
  }

  // ==========================================================================
  // Tests for delegate() through read() - success path (line 1514)
  // ==========================================================================

  @Test
  public void testDelegate_returnsDelegateWhenSet_viaRead() throws IOException {
    Gson.FutureTypeAdapter<String> futureAdapter = new Gson.FutureTypeAdapter<>();
    Gson gson = new Gson();
    TypeAdapter<String> stringAdapter = gson.getAdapter(String.class);
    futureAdapter.setDelegate(stringAdapter);

    // This calls delegate() which should return the delegate and delegate the read
    JsonReader reader = new JsonReader(new StringReader("\"test value\""));
    String result = futureAdapter.read(reader);

    assertThat(result).isEqualTo("test value");
  }

  @Test
  public void testDelegate_returnsDelegateWhenSet_viaRead_withInteger() throws IOException {
    Gson.FutureTypeAdapter<Integer> futureAdapter = new Gson.FutureTypeAdapter<>();
    Gson gson = new Gson();
    TypeAdapter<Integer> intAdapter = gson.getAdapter(Integer.class);
    futureAdapter.setDelegate(intAdapter);

    JsonReader reader = new JsonReader(new StringReader("42"));
    Integer result = futureAdapter.read(reader);

    assertThat(result).isEqualTo(42);
  }

  // ==========================================================================
  // Tests for delegate() through read() - failure path (lines 1505, 1506, 1510)
  // ==========================================================================

  @Test
  public void testDelegate_throwsWhenDelegateNotSet_viaRead() {
    Gson.FutureTypeAdapter<String> futureAdapter = new Gson.FutureTypeAdapter<>();
    JsonReader reader = new JsonReader(new StringReader("\"test\""));

    // This calls delegate() which should throw IllegalStateException
    IllegalStateException exception =
        assertThrows(IllegalStateException.class, () -> futureAdapter.read(reader));
    assertThat(exception.getMessage())
        .contains("Adapter for type with cyclic dependency has been used");
  }

  @Test
  public void testDelegate_throwsWhenDelegateNotSet_viaRead_withDifferentTypes() {
    // Test with Integer type
    Gson.FutureTypeAdapter<Integer> intFutureAdapter = new Gson.FutureTypeAdapter<>();
    JsonReader intReader = new JsonReader(new StringReader("123"));

    IllegalStateException intException =
        assertThrows(IllegalStateException.class, () -> intFutureAdapter.read(intReader));
    assertThat(intException.getMessage())
        .contains("Adapter for type with cyclic dependency has been used");

    // Test with Boolean type
    Gson.FutureTypeAdapter<Boolean> boolFutureAdapter = new Gson.FutureTypeAdapter<>();
    JsonReader boolReader = new JsonReader(new StringReader("true"));

    IllegalStateException boolException =
        assertThrows(IllegalStateException.class, () -> boolFutureAdapter.read(boolReader));
    assertThat(boolException.getMessage())
        .contains("Adapter for type with cyclic dependency has been used");
  }

  // ==========================================================================
  // Tests for delegate() through write() - success path (line 1514)
  // ==========================================================================

  @Test
  public void testDelegate_returnsDelegateWhenSet_viaWrite() throws IOException {
    Gson.FutureTypeAdapter<String> futureAdapter = new Gson.FutureTypeAdapter<>();
    Gson gson = new Gson();
    TypeAdapter<String> stringAdapter = gson.getAdapter(String.class);
    futureAdapter.setDelegate(stringAdapter);

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    // This calls delegate() which should return the delegate and delegate the write
    futureAdapter.write(writer, "hello world");

    assertThat(stringWriter.toString()).isEqualTo("\"hello world\"");
  }

  @Test
  public void testDelegate_returnsDelegateWhenSet_viaWrite_withInteger() throws IOException {
    Gson.FutureTypeAdapter<Integer> futureAdapter = new Gson.FutureTypeAdapter<>();
    Gson gson = new Gson();
    TypeAdapter<Integer> intAdapter = gson.getAdapter(Integer.class);
    futureAdapter.setDelegate(intAdapter);

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    futureAdapter.write(writer, 999);

    assertThat(stringWriter.toString()).isEqualTo("999");
  }

  // ==========================================================================
  // Tests for delegate() through write() - failure path (lines 1505, 1506, 1510)
  // ==========================================================================

  @Test
  public void testDelegate_throwsWhenDelegateNotSet_viaWrite() {
    Gson.FutureTypeAdapter<String> futureAdapter = new Gson.FutureTypeAdapter<>();
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    // This calls delegate() which should throw IllegalStateException
    IllegalStateException exception =
        assertThrows(IllegalStateException.class, () -> futureAdapter.write(writer, "test"));
    assertThat(exception.getMessage())
        .contains("Adapter for type with cyclic dependency has been used");
  }

  @Test
  public void testDelegate_throwsWhenDelegateNotSet_viaWrite_withDifferentTypes() {
    // Test with Integer type
    Gson.FutureTypeAdapter<Integer> intFutureAdapter = new Gson.FutureTypeAdapter<>();
    StringWriter intStringWriter = new StringWriter();
    JsonWriter intWriter = new JsonWriter(intStringWriter);

    IllegalStateException intException =
        assertThrows(IllegalStateException.class, () -> intFutureAdapter.write(intWriter, 42));
    assertThat(intException.getMessage())
        .contains("Adapter for type with cyclic dependency has been used");

    // Test with Boolean type
    Gson.FutureTypeAdapter<Boolean> boolFutureAdapter = new Gson.FutureTypeAdapter<>();
    StringWriter boolStringWriter = new StringWriter();
    JsonWriter boolWriter = new JsonWriter(boolStringWriter);

    IllegalStateException boolException =
        assertThrows(IllegalStateException.class, () -> boolFutureAdapter.write(boolWriter, true));
    assertThat(boolException.getMessage())
        .contains("Adapter for type with cyclic dependency has been used");
  }

  // ==========================================================================
  // Combined tests ensuring both branches of delegate() are covered
  // ==========================================================================

  @Test
  public void testDelegate_bothBranches_successThenFailure() {
    Gson gson = new Gson();

    // First adapter - delegate set (success path, line 1514)
    Gson.FutureTypeAdapter<String> successAdapter = new Gson.FutureTypeAdapter<>();
    TypeAdapter<String> stringAdapter = gson.getAdapter(String.class);
    successAdapter.setDelegate(stringAdapter);
    assertThat(successAdapter.getSerializationDelegate()).isNotNull();

    // Second adapter - delegate not set (failure path, lines 1505, 1506, 1510)
    Gson.FutureTypeAdapter<String> failureAdapter = new Gson.FutureTypeAdapter<>();
    assertThrows(IllegalStateException.class, failureAdapter::getSerializationDelegate);
  }

  @Test
  public void testDelegate_consistentBehavior_multipleCalls() {
    Gson.FutureTypeAdapter<String> futureAdapter = new Gson.FutureTypeAdapter<>();
    Gson gson = new Gson();
    TypeAdapter<String> stringAdapter = gson.getAdapter(String.class);

    futureAdapter.setDelegate(stringAdapter);

    // Multiple calls to methods that use delegate() should all succeed
    assertThat(futureAdapter.getSerializationDelegate()).isSameInstanceAs(stringAdapter);
    assertThat(futureAdapter.getSerializationDelegate()).isSameInstanceAs(stringAdapter);
    assertThat(futureAdapter.getSerializationDelegate()).isSameInstanceAs(stringAdapter);
  }

  @Test
  public void testDelegate_exceptionMessage_isDescriptive() {
    Gson.FutureTypeAdapter<String> futureAdapter = new Gson.FutureTypeAdapter<>();

    IllegalStateException exception =
        assertThrows(IllegalStateException.class, futureAdapter::getSerializationDelegate);

    // Verify the exception message is helpful for debugging
    String message = exception.getMessage();
    assertThat(message).contains("cyclic dependency");
    assertThat(message).contains("before dependency has been resolved");
  }
}
