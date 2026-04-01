/*
 * Copyright (C) 2008 Google Inc.
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

import java.lang.reflect.Type;
import org.junit.Test;

public final class JsonSerializerTest {

  @Test
  public void testSerializeCustomSerializer() {
    JsonSerializer<String> serializer =
        new JsonSerializer<String>() {
          @Override
          public JsonElement serialize(
              String src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive("custom:" + src);
          }
        };

    Gson gson = new GsonBuilder().registerTypeAdapter(String.class, serializer).create();

    String result = gson.toJson("hello");

    assertThat(result).isEqualTo("\"custom:hello\"");
  }

  @Test
  public void testSerializeReturnsJsonElement() {
    JsonSerializer<Integer> serializer =
        new JsonSerializer<Integer>() {
          @Override
          public JsonElement serialize(
              Integer src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src * 2);
          }
        };

    Gson gson = new GsonBuilder().registerTypeAdapter(Integer.class, serializer).create();

    String result = gson.toJson(5);

    assertThat(result).isEqualTo("10");
  }

  @Test
  public void testSerializeWithContext() {
    JsonSerializer<int[]> serializer =
        new JsonSerializer<int[]>() {
          @Override
          public JsonElement serialize(
              int[] src, Type typeOfSrc, JsonSerializationContext context) {
            JsonArray array = new JsonArray();
            for (int val : src) {
              array.add(context.serialize(val));
            }
            return array;
          }
        };

    Gson gson = new GsonBuilder().registerTypeAdapter(int[].class, serializer).create();

    String result = gson.toJson(new int[] {1, 2, 3});

    assertThat(result).isEqualTo("[1,2,3]");
  }
}
