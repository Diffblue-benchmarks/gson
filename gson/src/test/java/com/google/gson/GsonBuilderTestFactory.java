/*
 * Copyright (C) 2025 Google Inc.
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

import com.diffblue.cover.annotations.InterestingTestFactory;
import java.lang.reflect.Type;

/**
 * Factory class for creating GsonBuilder instances and related objects for testing.
 */
public class GsonBuilderTestFactory {

  /**
   * Creates a GsonBuilder instance for testing.
   * This prevents NullPointerException in methods that require non-null parameters.
   *
   * @return a GsonBuilder instance for testing
   */
  @InterestingTestFactory
  public static GsonBuilder createGsonBuilder() {
    return new GsonBuilder();
  }

  /**
   * Creates a Type object for testing type adapter registration.
   * This provides a non-null Type parameter for registerTypeAdapter.
   *
   * @return a Type instance (String.class) for testing
   */
  @InterestingTestFactory
  public static Type createType() {
    return String.class;
  }

  /**
   * Creates a Type object using Integer class for testing.
   *
   * @return a Type instance (Integer.class) for testing
   */
  @InterestingTestFactory
  public static Type createIntegerType() {
    return Integer.class;
  }

  /**
   * Creates a TypeAdapter object for testing type adapter registration.
   * This provides a non-null typeAdapter parameter for registerTypeAdapter.
   *
   * @return a TypeAdapter instance for String for testing
   */
  @InterestingTestFactory
  public static Object createTypeAdapter() {
    return new TypeAdapter<String>() {
      @Override
      public void write(com.google.gson.stream.JsonWriter out, String value) throws java.io.IOException {
        out.value(value);
      }

      @Override
      public String read(com.google.gson.stream.JsonReader in) throws java.io.IOException {
        return in.nextString();
      }
    };
  }

  /**
   * Creates a JsonSerializer object for testing type adapter registration.
   *
   * @return a JsonSerializer instance for testing
   */
  @InterestingTestFactory
  public static Object createJsonSerializer() {
    return new JsonSerializer<String>() {
      @Override
      public JsonElement serialize(String src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src);
      }
    };
  }

  /**
   * Creates a JsonDeserializer object for testing type adapter registration.
   *
   * @return a JsonDeserializer instance for testing
   */
  @InterestingTestFactory
  public static Object createJsonDeserializer() {
    return new JsonDeserializer<String>() {
      @Override
      public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        return json.getAsString();
      }
    };
  }

  /**
   * Creates an InstanceCreator object for testing type adapter registration.
   *
   * @return an InstanceCreator instance for testing
   */
  @InterestingTestFactory
  public static Object createInstanceCreator() {
    return new InstanceCreator<String>() {
      @Override
      public String createInstance(Type type) {
        return "default";
      }
    };
  }
}
