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

/** Factory class for creating JsonArray instances for Diffblue Cover testing. */
public class JsonArrayDiffblueTestFactory {

  /**
   * Creates a JsonArray with at least two elements to avoid IndexOutOfBoundsException when calling
   * remove(int index) with index 1. This factory method ensures the array has sufficient elements
   * for index-based operations.
   *
   * @return a JsonArray instance with two elements for testing
   */
  @InterestingTestFactory
  public static JsonArray createJsonArrayWithElements() {
    JsonArray array = new JsonArray();
    array.add(new JsonPrimitive("element0"));
    array.add(new JsonPrimitive("element1"));
    return array;
  }

  /**
   * Creates a non-null JsonArray to be used as a parameter for addAll method. This factory prevents
   * NullPointerException when the addAll method attempts to access the elements field of the
   * parameter array.
   *
   * @return a non-null JsonArray instance with elements for testing addAll
   */
  @InterestingTestFactory
  public static JsonArray createJsonArrayForAddAll() {
    JsonArray array = new JsonArray();
    array.add(new JsonPrimitive("test1"));
    array.add(new JsonPrimitive("test2"));
    return array;
  }
}
