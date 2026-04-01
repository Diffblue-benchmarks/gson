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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import org.junit.Test;

public final class ReflectiveTypeAdapterFactoryBoundFieldTest {

  static class SimpleModel {
    String value;

    SimpleModel(String value) {
      this.value = value;
    }
  }

  static class AnnotatedModel {
    @SerializedName("custom_name")
    String value;

    AnnotatedModel(String value) {
      this.value = value;
    }
  }

  static class MultiFieldModel {
    String name;
    int count;
    boolean active;

    MultiFieldModel(String name, int count, boolean active) {
      this.name = name;
      this.count = count;
      this.active = active;
    }
  }

  @Test
  public void testBoundFieldConstructorViaSerialize() {
    Gson gson = new GsonBuilder().create();
    SimpleModel model = new SimpleModel("hello");

    String json = gson.toJson(model);

    assertThat(json).isEqualTo("{\"value\":\"hello\"}");
  }

  @Test
  public void testBoundFieldConstructorViaDeserialize() {
    Gson gson = new GsonBuilder().create();

    SimpleModel model = gson.fromJson("{\"value\":\"world\"}", SimpleModel.class);

    assertThat(model.value).isEqualTo("world");
  }

  @Test
  public void testBoundFieldWriteWithSerializedName() {
    Gson gson = new GsonBuilder().create();
    AnnotatedModel model = new AnnotatedModel("testValue");

    String json = gson.toJson(model);

    assertThat(json).isEqualTo("{\"custom_name\":\"testValue\"}");
  }

  @Test
  public void testBoundFieldReadIntoFieldWithSerializedName() {
    Gson gson = new GsonBuilder().create();

    AnnotatedModel model = gson.fromJson("{\"custom_name\":\"readValue\"}", AnnotatedModel.class);

    assertThat(model.value).isEqualTo("readValue");
  }

  @Test
  public void testBoundFieldSerializeRoundTrip() {
    Gson gson = new GsonBuilder().create();
    MultiFieldModel original = new MultiFieldModel("test", 42, true);

    String json = gson.toJson(original);
    MultiFieldModel deserialized = gson.fromJson(json, MultiFieldModel.class);

    assertThat(deserialized.name).isEqualTo("test");
    assertThat(deserialized.count).isEqualTo(42);
    assertThat(deserialized.active).isTrue();
  }

  @Test
  public void testBoundFieldWriteNullValue() {
    Gson gson = new GsonBuilder().serializeNulls().create();
    SimpleModel model = new SimpleModel(null);

    String json = gson.toJson(model);

    assertThat(json).isEqualTo("{\"value\":null}");
  }

  @Test
  public void testBoundFieldReadIntoFieldNullValue() {
    Gson gson = new GsonBuilder().create();

    SimpleModel model = gson.fromJson("{\"value\":null}", SimpleModel.class);

    assertThat(model.value).isNull();
  }
}
