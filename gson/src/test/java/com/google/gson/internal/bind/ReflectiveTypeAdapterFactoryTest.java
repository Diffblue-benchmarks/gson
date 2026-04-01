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
import org.junit.Test;

public final class ReflectiveTypeAdapterFactoryTest {

  private static class SimpleBean {
    String name;
    int value;

    SimpleBean() {}

    SimpleBean(String name, int value) {
      this.name = name;
      this.value = value;
    }
  }

  @Test
  public void testFieldsDataConstructorViaSerialization() {
    Gson gson = new GsonBuilder().create();
    SimpleBean bean = new SimpleBean("test", 42);

    String json = gson.toJson(bean);

    assertThat(json).contains("\"name\"");
    assertThat(json).contains("\"test\"");
    assertThat(json).contains("\"value\"");
    assertThat(json).contains("42");
  }

  @Test
  public void testFieldsDataConstructorViaDeserialization() {
    Gson gson = new GsonBuilder().create();

    SimpleBean bean = gson.fromJson("{\"name\":\"hello\",\"value\":7}", SimpleBean.class);

    assertThat(bean.name).isEqualTo("hello");
    assertThat(bean.value).isEqualTo(7);
  }
}
