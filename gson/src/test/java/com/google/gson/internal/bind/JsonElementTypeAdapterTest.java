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
package com.google.gson.internal.bind;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.google.gson.JsonElement;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;

public final class JsonElementTypeAdapterTest {

  @Test
  public void testReadThrowsOnUnexpectedToken() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{}"));
    // Fully consume the document
    reader.beginObject();
    reader.endObject();
    // Now reader is at END_DOCUMENT state

    IllegalStateException e =
        assertThrows(
            IllegalStateException.class,
            () -> JsonElementTypeAdapter.ADAPTER.read(reader));
    assertThat(e).hasMessageThat().contains("Unexpected token");
    assertThat(e).hasMessageThat().contains("END_DOCUMENT");
  }

  @Test
  public void testWriteThrowsOnCustomJsonElementSubclass() throws IOException {
    @SuppressWarnings("deprecation") // superclass constructor
    class CustomJsonElement extends JsonElement {
      @Override
      public JsonElement deepCopy() {
        return this;
      }
    }

    JsonWriter writer = new JsonWriter(new StringWriter());
    writer.beginArray();

    CustomJsonElement customElement = new CustomJsonElement();
    IllegalArgumentException e =
        assertThrows(
            IllegalArgumentException.class,
            () -> JsonElementTypeAdapter.ADAPTER.write(writer, customElement));
    assertThat(e).hasMessageThat().contains("Couldn't write");
    assertThat(e).hasMessageThat().contains("CustomJsonElement");
  }
}
