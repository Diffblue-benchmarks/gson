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
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import org.junit.Test;

public final class TreeTypeAdapterTest {

  static class SimpleModel {
    final String name;

    SimpleModel(String name) {
      this.name = name;
    }
  }

  static class Wrapper {
    final SimpleModel inner;

    Wrapper(SimpleModel inner) {
      this.inner = inner;
    }
  }

  @Test
  public void testGsonContextImplSerializeWithoutType() {
    Gson gson =
        new GsonBuilder()
            .registerTypeHierarchyAdapter(
                Wrapper.class,
                (JsonSerializer<Wrapper>)
                    (src, typeOfSrc, context) -> context.serialize(src.inner))
            .create();

    Wrapper wrapper = new Wrapper(new SimpleModel("hello"));
    JsonElement result = gson.toJsonTree(wrapper);

    assertThat(result.getAsJsonObject().get("name").getAsString()).isEqualTo("hello");
  }

  @Test
  public void testGsonContextImplSerializeWithType() {
    Gson gson =
        new GsonBuilder()
            .registerTypeHierarchyAdapter(
                Wrapper.class,
                (JsonSerializer<Wrapper>)
                    (src, typeOfSrc, context) -> context.serialize(src.inner, SimpleModel.class))
            .create();

    Wrapper wrapper = new Wrapper(new SimpleModel("world"));
    JsonElement result = gson.toJsonTree(wrapper);

    assertThat(result.getAsJsonObject().get("name").getAsString()).isEqualTo("world");
  }

  @Test
  public void testGsonContextImplDeserialize() {
    Gson gson =
        new GsonBuilder()
            .registerTypeHierarchyAdapter(
                Wrapper.class,
                (JsonDeserializer<Wrapper>)
                    (json, typeOfT, context) -> {
                      JsonElement innerJson = json.getAsJsonObject().get("inner");
                      SimpleModel inner = context.deserialize(innerJson, SimpleModel.class);
                      return new Wrapper(inner);
                    })
            .create();

    String jsonStr = "{\"inner\":{\"name\":\"deserialized\"}}";
    Wrapper wrapper = gson.fromJson(jsonStr, Wrapper.class);

    assertThat(wrapper.inner.name).isEqualTo("deserialized");
  }
}
