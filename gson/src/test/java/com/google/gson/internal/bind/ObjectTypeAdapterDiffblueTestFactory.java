/*
 * Copyright (C) 2011 Google Inc.
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
 * Factory class for creating ObjectTypeAdapter instances for Diffblue Cover testing.
 *
 * <p>Since ObjectTypeAdapter has a private constructor, this factory provides methods to create
 * instances using the public factory method available through getFactory.
 */
public class ObjectTypeAdapterDiffblueTestFactory {

  /**
   * Creates an ObjectTypeAdapter instance using the DOUBLE policy. This is the default strategy
   * used for Object deserialization.
   *
   * @return an ObjectTypeAdapter instance with DOUBLE strategy
   */
  @InterestingTestFactory
  public static ObjectTypeAdapter createDoubleAdapter() {
    TypeAdapterFactory factory = ObjectTypeAdapter.getFactory(ToNumberPolicy.DOUBLE);
    Gson gson = new Gson();
    TypeToken<Object> typeToken = TypeToken.get(Object.class);
    @SuppressWarnings("unchecked")
    ObjectTypeAdapter adapter = (ObjectTypeAdapter) factory.create(gson, typeToken);
    return adapter;
  }

  /**
   * Creates an ObjectTypeAdapter instance using the LAZILY_PARSED_NUMBER policy. This strategy
   * lazily parses numbers as needed.
   *
   * @return an ObjectTypeAdapter instance with LAZILY_PARSED_NUMBER strategy
   */
  @InterestingTestFactory
  public static ObjectTypeAdapter createLazilyParsedNumberAdapter() {
    TypeAdapterFactory factory = ObjectTypeAdapter.getFactory(ToNumberPolicy.LAZILY_PARSED_NUMBER);
    Gson gson = new Gson();
    TypeToken<Object> typeToken = TypeToken.get(Object.class);
    @SuppressWarnings("unchecked")
    ObjectTypeAdapter adapter = (ObjectTypeAdapter) factory.create(gson, typeToken);
    return adapter;
  }

  /**
   * Creates an ObjectTypeAdapter instance using the LONG_OR_DOUBLE policy. This ensures numbers are
   * read as Long or Double depending on their representation.
   *
   * @return an ObjectTypeAdapter instance with LONG_OR_DOUBLE strategy
   */
  @InterestingTestFactory
  public static ObjectTypeAdapter createLongOrDoubleAdapter() {
    TypeAdapterFactory factory = ObjectTypeAdapter.getFactory(ToNumberPolicy.LONG_OR_DOUBLE);
    Gson gson = new Gson();
    TypeToken<Object> typeToken = TypeToken.get(Object.class);
    @SuppressWarnings("unchecked")
    ObjectTypeAdapter adapter = (ObjectTypeAdapter) factory.create(gson, typeToken);
    return adapter;
  }

  /**
   * Creates an ObjectTypeAdapter instance using the BIG_DECIMAL policy. This ensures numbers are
   * read as BigDecimal for arbitrary precision.
   *
   * @return an ObjectTypeAdapter instance with BIG_DECIMAL strategy
   */
  @InterestingTestFactory
  public static ObjectTypeAdapter createBigDecimalAdapter() {
    TypeAdapterFactory factory = ObjectTypeAdapter.getFactory(ToNumberPolicy.BIG_DECIMAL);
    Gson gson = new Gson();
    TypeToken<Object> typeToken = TypeToken.get(Object.class);
    @SuppressWarnings("unchecked")
    ObjectTypeAdapter adapter = (ObjectTypeAdapter) factory.create(gson, typeToken);
    return adapter;
  }
}
