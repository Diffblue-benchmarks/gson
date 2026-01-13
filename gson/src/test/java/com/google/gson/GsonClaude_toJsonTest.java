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

import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringWriter;
import org.junit.Test;

/**
 * Tests for {@link Gson#toJson(Object, java.lang.reflect.Type, JsonWriter)} focusing on exception
 * handling paths.
 *
 * @author Claude
 */
public class GsonClaude_toJsonTest {

  // ==========================================================================
  // IOException handling tests (lines 944-945)
  // ==========================================================================

  @Test
  public void testToJson_ioExceptionDuringWrite_throwsJsonIOException() throws IOException {
    // Create a TypeAdapter that throws IOException during write
    TypeAdapter<String> throwingAdapter =
        new TypeAdapter<String>() {
          @Override
          public void write(JsonWriter out, String value) throws IOException {
            throw new IOException("Simulated IO failure");
          }

          @Override
          public String read(JsonReader in) throws IOException {
            return null;
          }
        };

    TypeAdapterFactory throwingFactory =
        new TypeAdapterFactory() {
          @SuppressWarnings("unchecked")
          @Override
          public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            if (type.getRawType() == String.class) {
              return (TypeAdapter<T>) throwingAdapter;
            }
            return null;
          }
        };

    Gson gson = new GsonBuilder().registerTypeAdapterFactory(throwingFactory).create();

    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);

    JsonIOException exception =
        assertThrows(JsonIOException.class, () -> gson.toJson("test", String.class, writer));

    assertThat(exception.getCause()).isInstanceOf(IOException.class);
    assertThat(exception.getCause().getMessage()).isEqualTo("Simulated IO failure");
  }

  @Test
  public void testToJson_ioExceptionDuringWrite_preservesOriginalException() throws IOException {
    IOException originalException = new IOException("Original IO error");

    TypeAdapter<Integer> throwingAdapter =
        new TypeAdapter<Integer>() {
          @Override
          public void write(JsonWriter out, Integer value) throws IOException {
            throw originalException;
          }

          @Override
          public Integer read(JsonReader in) throws IOException {
            return null;
          }
        };

    TypeAdapterFactory throwingFactory =
        new TypeAdapterFactory() {
          @SuppressWarnings("unchecked")
          @Override
          public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            if (type.getRawType() == Integer.class) {
              return (TypeAdapter<T>) throwingAdapter;
            }
            return null;
          }
        };

    Gson gson = new GsonBuilder().registerTypeAdapterFactory(throwingFactory).create();

    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);

    JsonIOException exception =
        assertThrows(JsonIOException.class, () -> gson.toJson(42, Integer.class, writer));

    assertThat(exception.getCause()).isSameInstanceAs(originalException);
  }

  // ==========================================================================
  // AssertionError handling tests (lines 946-948)
  // ==========================================================================

  @Test
  public void testToJson_assertionErrorDuringWrite_throwsWrappedAssertionError() throws IOException {
    // Create a TypeAdapter that throws AssertionError during write
    TypeAdapter<String> throwingAdapter =
        new TypeAdapter<String>() {
          @Override
          public void write(JsonWriter out, String value) throws IOException {
            throw new AssertionError("Simulated assertion failure");
          }

          @Override
          public String read(JsonReader in) throws IOException {
            return null;
          }
        };

    TypeAdapterFactory throwingFactory =
        new TypeAdapterFactory() {
          @SuppressWarnings("unchecked")
          @Override
          public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            if (type.getRawType() == String.class) {
              return (TypeAdapter<T>) throwingAdapter;
            }
            return null;
          }
        };

    Gson gson = new GsonBuilder().registerTypeAdapterFactory(throwingFactory).create();

    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);

    AssertionError exception =
        assertThrows(AssertionError.class, () -> gson.toJson("test", String.class, writer));

    assertThat(exception.getMessage()).contains("AssertionError (GSON");
    assertThat(exception.getMessage()).contains("Simulated assertion failure");
    assertThat(exception.getCause()).isInstanceOf(AssertionError.class);
    assertThat(exception.getCause().getMessage()).isEqualTo("Simulated assertion failure");
  }

  @Test
  public void testToJson_assertionErrorWithNullMessage_handlesGracefully() throws IOException {
    // Create a TypeAdapter that throws AssertionError with null message
    TypeAdapter<String> throwingAdapter =
        new TypeAdapter<String>() {
          @Override
          public void write(JsonWriter out, String value) throws IOException {
            throw new AssertionError((String) null);
          }

          @Override
          public String read(JsonReader in) throws IOException {
            return null;
          }
        };

    TypeAdapterFactory throwingFactory =
        new TypeAdapterFactory() {
          @SuppressWarnings("unchecked")
          @Override
          public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            if (type.getRawType() == String.class) {
              return (TypeAdapter<T>) throwingAdapter;
            }
            return null;
          }
        };

    Gson gson = new GsonBuilder().registerTypeAdapterFactory(throwingFactory).create();

    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);

    AssertionError exception =
        assertThrows(AssertionError.class, () -> gson.toJson("test", String.class, writer));

    assertThat(exception.getMessage()).contains("AssertionError (GSON");
    assertThat(exception.getCause()).isInstanceOf(AssertionError.class);
  }

  @Test
  public void testToJson_assertionErrorPreservesCause() throws IOException {
    AssertionError originalError = new AssertionError("Original assertion error");

    TypeAdapter<Long> throwingAdapter =
        new TypeAdapter<Long>() {
          @Override
          public void write(JsonWriter out, Long value) throws IOException {
            throw originalError;
          }

          @Override
          public Long read(JsonReader in) throws IOException {
            return null;
          }
        };

    TypeAdapterFactory throwingFactory =
        new TypeAdapterFactory() {
          @SuppressWarnings("unchecked")
          @Override
          public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            if (type.getRawType() == Long.class) {
              return (TypeAdapter<T>) throwingAdapter;
            }
            return null;
          }
        };

    Gson gson = new GsonBuilder().registerTypeAdapterFactory(throwingFactory).create();

    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);

    AssertionError exception =
        assertThrows(AssertionError.class, () -> gson.toJson(100L, Long.class, writer));

    assertThat(exception.getCause()).isSameInstanceAs(originalError);
  }

  // ==========================================================================
  // Additional edge case tests
  // ==========================================================================

  @Test
  public void testToJson_ioExceptionDuringWrite_stillRestoresWriterSettings() throws IOException {
    TypeAdapter<String> throwingAdapter =
        new TypeAdapter<String>() {
          @Override
          public void write(JsonWriter out, String value) throws IOException {
            throw new IOException("Write failed");
          }

          @Override
          public String read(JsonReader in) throws IOException {
            return null;
          }
        };

    TypeAdapterFactory throwingFactory =
        new TypeAdapterFactory() {
          @SuppressWarnings("unchecked")
          @Override
          public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            if (type.getRawType() == String.class) {
              return (TypeAdapter<T>) throwingAdapter;
            }
            return null;
          }
        };

    Gson gson = new GsonBuilder().registerTypeAdapterFactory(throwingFactory).create();

    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    writer.setStrictness(Strictness.STRICT);
    writer.setHtmlSafe(false);
    writer.setSerializeNulls(true);

    assertThrows(JsonIOException.class, () -> gson.toJson("test", String.class, writer));

    // Verify settings are restored in finally block
    assertThat(writer.getStrictness()).isEqualTo(Strictness.STRICT);
    assertThat(writer.isHtmlSafe()).isFalse();
    assertThat(writer.getSerializeNulls()).isTrue();
  }

  @Test
  public void testToJson_assertionErrorDuringWrite_stillRestoresWriterSettings() throws IOException {
    TypeAdapter<String> throwingAdapter =
        new TypeAdapter<String>() {
          @Override
          public void write(JsonWriter out, String value) throws IOException {
            throw new AssertionError("Assertion failed");
          }

          @Override
          public String read(JsonReader in) throws IOException {
            return null;
          }
        };

    TypeAdapterFactory throwingFactory =
        new TypeAdapterFactory() {
          @SuppressWarnings("unchecked")
          @Override
          public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            if (type.getRawType() == String.class) {
              return (TypeAdapter<T>) throwingAdapter;
            }
            return null;
          }
        };

    Gson gson = new GsonBuilder().registerTypeAdapterFactory(throwingFactory).create();

    StringWriter sw = new StringWriter();
    JsonWriter writer = new JsonWriter(sw);
    writer.setStrictness(Strictness.STRICT);
    writer.setHtmlSafe(false);
    writer.setSerializeNulls(true);

    assertThrows(AssertionError.class, () -> gson.toJson("test", String.class, writer));

    // Verify settings are restored in finally block
    assertThat(writer.getStrictness()).isEqualTo(Strictness.STRICT);
    assertThat(writer.isHtmlSafe()).isFalse();
    assertThat(writer.getSerializeNulls()).isTrue();
  }
}
