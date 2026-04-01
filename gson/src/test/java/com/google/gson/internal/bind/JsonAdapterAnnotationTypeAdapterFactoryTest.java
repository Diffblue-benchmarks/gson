/*
 * Copyright (C) 2023 Google Inc.
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

import com.google.gson.TypeAdapterFactory;
import java.lang.reflect.Field;
import org.junit.Test;

public final class JsonAdapterAnnotationTypeAdapterFactoryTest {

  @Test
  public void testDummyTypeAdapterFactoryCreateThrowsAssertionError() throws Exception {
    Field field =
        JsonAdapterAnnotationTypeAdapterFactory.class.getDeclaredField(
            "TREE_TYPE_CLASS_DUMMY_FACTORY");
    field.setAccessible(true);
    TypeAdapterFactory dummyFactory = (TypeAdapterFactory) field.get(null);

    AssertionError e = assertThrows(AssertionError.class, () -> dummyFactory.create(null, null));
    assertThat(e).hasMessageThat().isEqualTo("Factory should not be used");
  }
}
