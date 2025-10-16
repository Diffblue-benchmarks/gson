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

/**
 * Factory class for creating JsonArray instances for testing.
 */
public class JsonArrayTestFactory {

  /**
   * Creates a JsonArray with multiple elements for testing methods like remove(int).
   * This prevents IndexOutOfBoundsException by providing a non-empty array.
   *
   * @return a JsonArray instance with multiple elements for testing
   */
  @InterestingTestFactory
  public static JsonArray createJsonArray() {
    JsonArray array = new JsonArray();
    array.add("element0");
    array.add("element1");
    array.add("element2");
    return array;
  }

  /**
   * Creates a JsonArray with a single element for testing.
   *
   * @return a JsonArray instance with one element
   */
  @InterestingTestFactory
  public static JsonArray createJsonArrayWithOneElement() {
    JsonArray array = new JsonArray();
    array.add("singleElement");
    return array;
  }

  /**
   * Creates a JsonArray with numeric elements for testing.
   *
   * @return a JsonArray instance with numeric elements
   */
  @InterestingTestFactory
  public static JsonArray createJsonArrayWithNumbers() {
    JsonArray array = new JsonArray();
    array.add(1);
    array.add(2);
    array.add(3);
    return array;
  }

  /**
   * Creates a JsonArray with boolean elements for testing.
   *
   * @return a JsonArray instance with boolean elements
   */
  @InterestingTestFactory
  public static JsonArray createJsonArrayWithBooleans() {
    JsonArray array = new JsonArray();
    array.add(true);
    array.add(false);
    return array;
  }

  /**
   * Creates a JsonArray with mixed types for testing.
   *
   * @return a JsonArray instance with mixed element types
   */
  @InterestingTestFactory
  public static JsonArray createJsonArrayWithMixedTypes() {
    JsonArray array = new JsonArray();
    array.add("string");
    array.add(42);
    array.add(true);
    array.add(new JsonObject());
    return array;
  }

  /**
   * Creates an empty JsonArray for testing methods that work with empty arrays.
   *
   * @return an empty JsonArray instance
   */
  @InterestingTestFactory
  public static JsonArray createEmptyJsonArray() {
    return new JsonArray();
  }

  /**
   * Creates a JsonArray for use as a parameter to addAll method.
   * This prevents NullPointerException by providing a non-null array parameter.
   *
   * @return a JsonArray instance with elements suitable for addAll parameter
   */
  @InterestingTestFactory
  public static JsonArray createJsonArrayForAddAll() {
    JsonArray array = new JsonArray();
    array.add("addAllElement1");
    array.add("addAllElement2");
    return array;
  }

  /**
   * Creates a JsonArray with multiple elements specifically for testing indexed operations.
   * This prevents IndexOutOfBoundsException by providing an array with sufficient elements
   * for operations like remove(int) that may access various indices.
   *
   * @return a JsonArray instance with at least 3 elements for safe indexed access
   */
  @InterestingTestFactory
  public static JsonArray createJsonArrayForIndexedOperations() {
    JsonArray array = new JsonArray();
    array.add("indexedElement0");
    array.add("indexedElement1");
    array.add("indexedElement2");
    array.add("indexedElement3");
    return array;
  }
}
