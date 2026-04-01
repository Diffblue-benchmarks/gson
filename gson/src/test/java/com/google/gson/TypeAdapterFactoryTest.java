/*
 * Copyright (C) 2011 Google Inc.
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

package com.google.gson;

import static com.google.common.truth.Truth.assertThat;

import com.google.gson.reflect.TypeToken;
import org.junit.Test;

public final class TypeAdapterFactoryTest {

  @Test
  public void testCreateReturnsNullForUnsupportedType() {
    TypeAdapterFactory factory = new TypeAdapterFactory() {
      @Override
      public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        return null;
      }
    };

    Gson gson = new Gson();
    TypeAdapter<String> adapter = factory.create(gson, TypeToken.get(String.class));

    assertThat(adapter).isNull();
  }

  @Test
  public void testCreateReturnsDelegateAdapter() {
    TypeAdapterFactory factory = new TypeAdapterFactory() {
      @Override
      public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        return gson.getDelegateAdapter(this, type);
      }
    };

    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
    String result = gson.fromJson("\"hello\"", String.class);

    assertThat(result).isEqualTo("hello");
  }
}
