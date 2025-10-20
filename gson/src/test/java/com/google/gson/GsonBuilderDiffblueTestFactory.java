/*
 * Copyright (C) 2009 Google Inc.
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
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;

/** Factory class for creating type adapter objects for Diffblue Cover testing. */
public class GsonBuilderDiffblueTestFactory {

  /**
   * Creates a simple TypeAdapter instance to avoid NullPointerException when calling
   * registerTypeAdapter. This factory method ensures a valid type adapter is provided as the second
   * parameter.
   *
   * @return a TypeAdapter instance for testing
   */
  @InterestingTestFactory
  public static Object createTypeAdapter() {
    return new TypeAdapter<String>() {
      @Override
      public void write(JsonWriter out, String value) throws IOException {
        out.value(value);
      }

      @Override
      public String read(JsonReader in) throws IOException {
        return in.nextString();
      }
    };
  }

  /**
   * Creates a valid Type instance to avoid NullPointerException when calling registerTypeAdapter.
   * This factory method ensures a valid type is provided as the first parameter.
   *
   * @return a Type instance for testing
   */
  @InterestingTestFactory
  public static Type createType() {
    return String.class;
  }
}
