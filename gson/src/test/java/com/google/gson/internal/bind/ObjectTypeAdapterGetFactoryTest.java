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
import com.google.gson.ToNumberPolicy;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

public final class ObjectTypeAdapterGetFactoryTest {

  @Test
  public void testGetFactoryWithDoubleReturnsSameInstance() {
    TypeAdapterFactory factory1 = ObjectTypeAdapter.getFactory(ToNumberPolicy.DOUBLE);
    TypeAdapterFactory factory2 = ObjectTypeAdapter.getFactory(ToNumberPolicy.DOUBLE);

    assertThat(factory1).isSameInstanceAs(factory2);
  }

  @Test
  public void testGetFactoryWithNonDoublePolicyReturnsNewFactory() {
    TypeAdapterFactory factory = ObjectTypeAdapter.getFactory(ToNumberPolicy.LONG_OR_DOUBLE);

    assertThat(factory).isNotNull();
  }

  @Test
  public void testGetFactoryWithNonDoublePolicyDiffersFromDoubleFactory() {
    TypeAdapterFactory doubleFactory = ObjectTypeAdapter.getFactory(ToNumberPolicy.DOUBLE);
    TypeAdapterFactory longOrDoubleFactory = ObjectTypeAdapter.getFactory(ToNumberPolicy.LONG_OR_DOUBLE);

    assertThat(longOrDoubleFactory).isNotSameInstanceAs(doubleFactory);
  }

  @Test
  public void testGetFactoryWithBigDecimalPolicyCreatesWorkingAdapter() {
    TypeAdapterFactory factory = ObjectTypeAdapter.getFactory(ToNumberPolicy.BIG_DECIMAL);
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
    TypeAdapter<Object> adapter = factory.create(gson, TypeToken.get(Object.class));

    assertThat(adapter).isNotNull();
    assertThat(adapter).isInstanceOf(ObjectTypeAdapter.class);
  }

  @Test
  public void testGetFactoryWithDoubleCreatesObjectTypeAdapter() {
    TypeAdapterFactory factory = ObjectTypeAdapter.getFactory(ToNumberPolicy.DOUBLE);
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
    TypeAdapter<Object> adapter = factory.create(gson, TypeToken.get(Object.class));

    assertThat(adapter).isNotNull();
    assertThat(adapter).isInstanceOf(ObjectTypeAdapter.class);
  }

  @Test
  public void testGetFactoryReturnsNullForNonObjectType() {
    TypeAdapterFactory factory = ObjectTypeAdapter.getFactory(ToNumberPolicy.LONG_OR_DOUBLE);
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
    TypeAdapter<String> adapter = factory.create(gson, TypeToken.get(String.class));

    assertThat(adapter).isNull();
  }
}
