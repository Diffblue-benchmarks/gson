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

package com.google.gson;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.google.gson.internal.Excluder;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringWriter;
import org.junit.Test;

/**
 * Additional unit tests for {@link Gson} to improve code coverage.
 */
public final class GsonCoverageTest {

  @SuppressWarnings("deprecation") // for Gson.excluder()
  @Test
  public void testExcluder() {
    Gson gson = new Gson();
    Excluder excluder = gson.excluder();
    assertThat(excluder).isNotNull();
    assertThat(excluder).isEqualTo(Excluder.DEFAULT);
  }

  @Test
  public void testLongSerializationPolicyStringDeserializeNull() {
    Gson gson =
        new GsonBuilder().setLongSerializationPolicy(LongSerializationPolicy.STRING).create();
    Long result = gson.fromJson("null", Long.class);
    assertThat(result).isNull();
  }

  @Test
  public void testToJsonNullObject() {
    Gson gson = new Gson();
    String result = gson.toJson(null);
    assertThat(result).isEqualTo("null");
  }

  @Test
  public void testToJsonNullObjectWithAppendable() {
    Gson gson = new Gson();
    StringBuilder sb = new StringBuilder();
    gson.toJson(null, sb);
    assertThat(sb.toString()).isEqualTo("null");
  }

  @Test
  public void testToJsonObjectTypeAppendableThrowsIOException() {
    Gson gson = new Gson();
    Appendable failingAppendable =
        new Appendable() {
          @Override
          public Appendable append(CharSequence csq) throws IOException {
            throw new IOException("Test IOException");
          }

          @Override
          public Appendable append(CharSequence csq, int start, int end) throws IOException {
            throw new IOException("Test IOException");
          }

          @Override
          public Appendable append(char c) throws IOException {
            throw new IOException("Test IOException");
          }
        };

    JsonIOException e =
        assertThrows(
            JsonIOException.class, () -> gson.toJson("test", String.class, failingAppendable));
    assertThat(e).hasCauseThat().hasMessageThat().isEqualTo("Test IOException");
  }

  @Test
  public void testToJsonWithExplicitStrictness() throws IOException {
    Gson gson = new GsonBuilder().setStrictness(Strictness.STRICT).create();
    StringWriter stringWriter = new StringWriter();
    JsonWriter jsonWriter = new JsonWriter(stringWriter);
    gson.toJson("test", String.class, jsonWriter);
    assertThat(stringWriter.toString()).isEqualTo("\"test\"");
  }

  @Test
  public void testToJsonObjectTypeJsonWriterThrowsIOException() throws IOException {
    Gson gson = new Gson();
    JsonWriter failingWriter =
        new JsonWriter(new StringWriter()) {
          @Override
          public JsonWriter value(String value) throws IOException {
            throw new IOException("Test write IOException");
          }
        };

    JsonIOException e =
        assertThrows(JsonIOException.class, () -> gson.toJson("test", String.class, failingWriter));
    assertThat(e).hasCauseThat().hasMessageThat().isEqualTo("Test write IOException");
  }

  @Test
  public void testToJsonJsonElementAppendableThrowsIOException() {
    Gson gson = new Gson();
    Appendable failingAppendable =
        new Appendable() {
          @Override
          public Appendable append(CharSequence csq) throws IOException {
            throw new IOException("Test appendable IOException");
          }

          @Override
          public Appendable append(CharSequence csq, int start, int end) throws IOException {
            throw new IOException("Test appendable IOException");
          }

          @Override
          public Appendable append(char c) throws IOException {
            throw new IOException("Test appendable IOException");
          }
        };

    JsonIOException e =
        assertThrows(
            JsonIOException.class, () -> gson.toJson(new JsonPrimitive("test"), failingAppendable));
    assertThat(e).hasCauseThat().hasMessageThat().isEqualTo("Test appendable IOException");
  }

  @Test
  public void testToJsonJsonElementJsonWriterWithExplicitStrictness() throws IOException {
    Gson gson = new GsonBuilder().setStrictness(Strictness.STRICT).create();
    StringWriter stringWriter = new StringWriter();
    JsonWriter jsonWriter = new JsonWriter(stringWriter);
    gson.toJson(new JsonPrimitive("test"), jsonWriter);
    assertThat(stringWriter.toString()).isEqualTo("\"test\"");
  }

  @Test
  public void testToJsonJsonElementJsonWriterThrowsIOException() throws IOException {
    Gson gson = new Gson();
    JsonWriter failingWriter =
        new JsonWriter(new StringWriter()) {
          @Override
          public JsonWriter value(String value) throws IOException {
            throw new IOException("Test element write IOException");
          }
        };

    JsonIOException e =
        assertThrows(
            JsonIOException.class, () -> gson.toJson(new JsonPrimitive("test"), failingWriter));
    assertThat(e).hasCauseThat().hasMessageThat().isEqualTo("Test element write IOException");
  }

  @Test
  public void testFromJsonNotFullyConsumed() {
    // Use lenient strictness so it doesn't fail on malformed JSON before assertFullConsumption
    Gson gson = new GsonBuilder().setStrictness(Strictness.LENIENT).create();
    JsonSyntaxException e =
        assertThrows(JsonSyntaxException.class, () -> gson.fromJson("[1] [2]", int[].class));
    assertThat(e).hasMessageThat().isEqualTo("JSON document was not fully consumed.");
  }


  @Test
  public void testToString() {
    Gson gson = new Gson();
    String str = gson.toString();
    assertThat(str).contains("serializeNulls:");
    assertThat(str).contains("factories:");
    assertThat(str).contains("instanceCreators:");
  }

}
