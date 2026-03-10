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
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import org.junit.Test;

public final class TreeTypeAdapterTest {

  @Test
  public void testNewFactoryWithExactType() {
    TypeToken<CustomClass> exactType = TypeToken.get(CustomClass.class);
    JsonSerializer<CustomClass> serializer =
        new JsonSerializer<CustomClass>() {
          @Override
          public JsonElement serialize(
              CustomClass src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive("custom:" + src.value);
          }
        };

    TypeAdapterFactory factory = TreeTypeAdapter.newFactory(exactType, serializer);
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();

    CustomClass obj = new CustomClass("test");
    String json = gson.toJson(obj);
    assertThat(json).isEqualTo("\"custom:test\"");
  }

  private static class CustomClass {
    final String value;

    CustomClass(String value) {
      this.value = value;
    }
  }
}
