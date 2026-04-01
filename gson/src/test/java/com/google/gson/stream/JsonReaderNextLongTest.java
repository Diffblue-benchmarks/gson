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

package com.google.gson.stream;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.google.gson.Strictness;
import java.io.IOException;
import java.io.StringReader;
import org.junit.Test;

/** Unit tests for {@link JsonReader#nextLong()} covering uncovered code paths. */
public final class JsonReaderNextLongTest {

  @Test
  public void testNextLongFromDecimalNumberExact() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[1.0]"));
    reader.beginArray();
    assertThat(reader.nextLong()).isEqualTo(1L);
    reader.endArray();
    reader.close();
  }

  @Test
  public void testNextLongFromDecimalNumberPrecisionLoss() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[1.5]"));
    reader.beginArray();
    assertThrows(NumberFormatException.class, reader::nextLong);
    reader.close();
  }

  @Test
  public void testNextLongFromQuotedStringDecimalExact() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[\"1.0\"]"));
    reader.beginArray();
    assertThat(reader.nextLong()).isEqualTo(1L);
    reader.endArray();
    reader.close();
  }

  @Test
  public void testNextLongFromQuotedStringDecimalPrecisionLoss() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[\"1.5\"]"));
    reader.beginArray();
    assertThrows(NumberFormatException.class, reader::nextLong);
    reader.close();
  }

  @Test
  public void testNextLongFromUnquotedValueThrows() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("[NaN]"));
    reader.setStrictness(Strictness.LENIENT);
    reader.beginArray();
    assertThrows(NumberFormatException.class, reader::nextLong);
    reader.close();
  }
}
