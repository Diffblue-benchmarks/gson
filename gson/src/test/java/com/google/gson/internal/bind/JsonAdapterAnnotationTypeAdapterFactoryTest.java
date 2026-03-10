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
import static org.junit.Assert.assertThrows;

import com.google.gson.Gson;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Field;
import org.junit.Test;

/**
 * Tests for {@link JsonAdapterAnnotationTypeAdapterFactory}.
 */
public final class JsonAdapterAnnotationTypeAdapterFactoryTest {

  /**
   * Tests that the internal {@code DummyTypeAdapterFactory.create} method throws
   * {@code AssertionError} when called. This factory is only used as a marker for
   * {@code Gson.getDelegateAdapter} and should never have its {@code create} method invoked.
   */
  @Test
  public void testDummyTypeAdapterFactoryCreateThrows() throws Exception {
    // Access the private TREE_TYPE_CLASS_DUMMY_FACTORY field via reflection
    Field dummyFactoryField =
        JsonAdapterAnnotationTypeAdapterFactory.class.getDeclaredField("TREE_TYPE_CLASS_DUMMY_FACTORY");
    dummyFactoryField.setAccessible(true);
    TypeAdapterFactory dummyFactory = (TypeAdapterFactory) dummyFactoryField.get(null);

    Gson gson = new Gson();
    TypeToken<String> typeToken = TypeToken.get(String.class);

    // Calling create on the dummy factory should throw AssertionError
    AssertionError thrown =
        assertThrows(AssertionError.class, () -> dummyFactory.create(gson, typeToken));

    assertThat(thrown).hasMessageThat().isEqualTo("Factory should not be used");
  }
}
