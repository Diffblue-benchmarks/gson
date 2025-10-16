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

package com.google.gson.internal.bind;

import com.diffblue.cover.annotations.InterestingTestFactory;
import com.google.gson.Gson;
import com.google.gson.ToNumberPolicy;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

/**
 * Factory class for creating NumberTypeAdapter instances for testing.
 * Since NumberTypeAdapter has a private constructor, we use the public
 * static factory method getFactory(ToNumberStrategy) to create instances.
 */
public class NumberTypeAdapterTestFactory {

  /**
   * Creates a NumberTypeAdapter instance using LAZILY_PARSED_NUMBER policy.
   * This is the default Gson policy for parsing numbers.
   *
   * @return a NumberTypeAdapter instance for testing
   */
  @InterestingTestFactory
  public static TypeAdapter<Number> createNumberTypeAdapterLazilyParsed() {
    TypeAdapterFactory factory = NumberTypeAdapter.getFactory(ToNumberPolicy.LAZILY_PARSED_NUMBER);
    Gson gson = new Gson();
    return factory.create(gson, TypeToken.get(Number.class));
  }

  /**
   * Creates a NumberTypeAdapter instance using DOUBLE policy.
   * This policy reads all numbers as Double.
   *
   * @return a NumberTypeAdapter instance for testing
   */
  @InterestingTestFactory
  public static TypeAdapter<Number> createNumberTypeAdapterDouble() {
    TypeAdapterFactory factory = NumberTypeAdapter.getFactory(ToNumberPolicy.DOUBLE);
    Gson gson = new Gson();
    return factory.create(gson, TypeToken.get(Number.class));
  }

  /**
   * Creates a NumberTypeAdapter instance using LONG_OR_DOUBLE policy.
   * This policy reads numbers as Long if possible, otherwise as Double.
   *
   * @return a NumberTypeAdapter instance for testing
   */
  @InterestingTestFactory
  public static TypeAdapter<Number> createNumberTypeAdapterLongOrDouble() {
    TypeAdapterFactory factory = NumberTypeAdapter.getFactory(ToNumberPolicy.LONG_OR_DOUBLE);
    Gson gson = new Gson();
    return factory.create(gson, TypeToken.get(Number.class));
  }

  /**
   * Creates a NumberTypeAdapter instance using BIG_DECIMAL policy.
   * This policy reads all numbers as BigDecimal.
   *
   * @return a NumberTypeAdapter instance for testing
   */
  @InterestingTestFactory
  public static TypeAdapter<Number> createNumberTypeAdapterBigDecimal() {
    TypeAdapterFactory factory = NumberTypeAdapter.getFactory(ToNumberPolicy.BIG_DECIMAL);
    Gson gson = new Gson();
    return factory.create(gson, TypeToken.get(Number.class));
  }
}
