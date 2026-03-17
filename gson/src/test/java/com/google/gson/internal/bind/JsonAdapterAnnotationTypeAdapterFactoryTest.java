/*
 * Copyright (C) 2024 Google Inc.
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

import static com.google.common.truth.Truth.assertThat;

import com.google.gson.Gson;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Field;
import org.junit.Test;

public class JsonAdapterAnnotationTypeAdapterFactoryTest {

  @Test
  public void testDummyTypeAdapterFactoryCreate() throws Exception {
    Field field = JsonAdapterAnnotationTypeAdapterFactory.class.getDeclaredField("TREE_TYPE_CLASS_DUMMY_FACTORY");
    field.setAccessible(true);
    TypeAdapterFactory dummyFactory = (TypeAdapterFactory) field.get(null);

    Gson gson = new Gson();
    TypeToken<String> type = TypeToken.get(String.class);

    try {
      dummyFactory.create(gson, type);
      assertThat(false).isTrue(); // Should not reach here
    } catch (AssertionError e) {
      assertThat(e.getMessage()).isEqualTo("Factory should not be used");
    }
  }
}
