/*
 * Copyright (C) 2020 Google Inc.
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
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

/**
 * Factory class for creating NumberTypeAdapter instances for Diffblue Cover testing.
 *
 * <p>Since NumberTypeAdapter has a private constructor, this factory provides methods to create
 * instances using the public factory method available through getFactory.
 */
public class NumberTypeAdapterDiffblueTestFactory {

  /**
   * Creates a NumberTypeAdapter instance using the LAZILY_PARSED_NUMBER policy. This is the default
   * strategy used for Number deserialization.
   *
   * @return a NumberTypeAdapter instance with LAZILY_PARSED_NUMBER strategy
   */
  @InterestingTestFactory
  public static NumberTypeAdapter createLazilyParsedNumberAdapter() {
    TypeAdapterFactory factory = NumberTypeAdapter.getFactory(ToNumberPolicy.LAZILY_PARSED_NUMBER);
    Gson gson = new Gson();
    TypeToken<Number> typeToken = TypeToken.get(Number.class);
    @SuppressWarnings("unchecked")
    NumberTypeAdapter adapter = (NumberTypeAdapter) factory.create(gson, typeToken);
    return adapter;
  }

  /**
   * Creates a NumberTypeAdapter instance using the DOUBLE policy. This ensures numbers are read as
   * Double values.
   *
   * @return a NumberTypeAdapter instance with DOUBLE strategy
   */
  @InterestingTestFactory
  public static NumberTypeAdapter createDoubleAdapter() {
    TypeAdapterFactory factory = NumberTypeAdapter.getFactory(ToNumberPolicy.DOUBLE);
    Gson gson = new Gson();
    TypeToken<Number> typeToken = TypeToken.get(Number.class);
    @SuppressWarnings("unchecked")
    NumberTypeAdapter adapter = (NumberTypeAdapter) factory.create(gson, typeToken);
    return adapter;
  }

  /**
   * Creates a NumberTypeAdapter instance using the LONG_OR_DOUBLE policy. This ensures numbers are
   * read as Long or Double depending on their representation.
   *
   * @return a NumberTypeAdapter instance with LONG_OR_DOUBLE strategy
   */
  @InterestingTestFactory
  public static NumberTypeAdapter createLongOrDoubleAdapter() {
    TypeAdapterFactory factory = NumberTypeAdapter.getFactory(ToNumberPolicy.LONG_OR_DOUBLE);
    Gson gson = new Gson();
    TypeToken<Number> typeToken = TypeToken.get(Number.class);
    @SuppressWarnings("unchecked")
    NumberTypeAdapter adapter = (NumberTypeAdapter) factory.create(gson, typeToken);
    return adapter;
  }

  /**
   * Creates a NumberTypeAdapter instance using the BIG_DECIMAL policy. This ensures numbers are
   * read as BigDecimal for arbitrary precision.
   *
   * @return a NumberTypeAdapter instance with BIG_DECIMAL strategy
   */
  @InterestingTestFactory
  public static NumberTypeAdapter createBigDecimalAdapter() {
    TypeAdapterFactory factory = NumberTypeAdapter.getFactory(ToNumberPolicy.BIG_DECIMAL);
    Gson gson = new Gson();
    TypeToken<Number> typeToken = TypeToken.get(Number.class);
    @SuppressWarnings("unchecked")
    NumberTypeAdapter adapter = (NumberTypeAdapter) factory.create(gson, typeToken);
    return adapter;
  }
}
