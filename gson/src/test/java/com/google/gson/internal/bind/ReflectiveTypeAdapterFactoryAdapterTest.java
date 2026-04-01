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

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.junit.Test;

public final class ReflectiveTypeAdapterFactoryAdapterTest {

  private static final class SimpleModel {
    String name;
    int count;

    SimpleModel() {}

    SimpleModel(String name, int count) {
      this.name = name;
      this.count = count;
    }
  }

  @Test
  public void testWriteNonNullObject() {
    Gson gson = new Gson();
    SimpleModel model = new SimpleModel("hello", 42);

    String json = gson.toJson(model);

    assertThat(json).isEqualTo("{\"name\":\"hello\",\"count\":42}");
  }

  @Test
  public void testWriteNullObject() {
    Gson gson = new Gson();

    String json = gson.toJson(null, SimpleModel.class);

    assertThat(json).isEqualTo("null");
  }

  @Test
  public void testReadNonNullObject() {
    Gson gson = new Gson();

    SimpleModel model = gson.fromJson("{\"name\":\"hello\",\"count\":42}", SimpleModel.class);

    assertThat(model.name).isEqualTo("hello");
    assertThat(model.count).isEqualTo(42);
  }

  @Test
  public void testReadNullToken() {
    Gson gson = new Gson();

    SimpleModel model = gson.fromJson("null", SimpleModel.class);

    assertThat(model).isNull();
  }

  @Test
  public void testReadUnknownFieldsAreSkipped() {
    Gson gson = new Gson();

    SimpleModel model =
        gson.fromJson(
            "{\"name\":\"test\",\"unknown\":\"value\",\"count\":5}", SimpleModel.class);

    assertThat(model.name).isEqualTo("test");
    assertThat(model.count).isEqualTo(5);
  }

  @Test
  public void testReadWrongJsonTypeThrowsJsonSyntaxException() {
    Gson gson = new Gson();

    assertThrows(
        JsonSyntaxException.class, () -> gson.fromJson("[1, 2, 3]", SimpleModel.class));
  }

  @Test
  public void testRoundTrip() {
    Gson gson = new Gson();
    SimpleModel original = new SimpleModel("world", 99);

    String json = gson.toJson(original);
    SimpleModel deserialized = gson.fromJson(json, SimpleModel.class);

    assertThat(deserialized.name).isEqualTo(original.name);
    assertThat(deserialized.count).isEqualTo(original.count);
  }
}
