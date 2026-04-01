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

  private static class NestedBean {
    String label;
    SimpleBean inner;

    NestedBean() {}
  }

  private static class MultiFieldBean {
    String first;
    String second;
    int count;

    MultiFieldBean() {}
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

  @Test
  public void testFieldReflectionAdapterCreatesAccumulator() {
    Gson gson = new GsonBuilder().create();

    SimpleBean bean = gson.fromJson("{}", SimpleBean.class);

    assertThat(bean).isNotNull();
    assertThat(bean.name).isNull();
    assertThat(bean.value).isEqualTo(0);
  }

  @Test
  public void testFieldReflectionAdapterReadFieldSetsValues() {
    Gson gson = new GsonBuilder().create();

    MultiFieldBean bean =
        gson.fromJson(
            "{\"first\":\"alpha\",\"second\":\"beta\",\"count\":3}", MultiFieldBean.class);

    assertThat(bean.first).isEqualTo("alpha");
    assertThat(bean.second).isEqualTo("beta");
    assertThat(bean.count).isEqualTo(3);
  }

  @Test
  public void testFieldReflectionAdapterFinalizeReturnsPopulatedObject() {
    Gson gson = new GsonBuilder().create();
    String json = "{\"name\":\"finalized\",\"value\":99}";

    SimpleBean bean = gson.fromJson(json, SimpleBean.class);

    assertThat(bean).isNotNull();
    assertThat(bean.name).isEqualTo("finalized");
    assertThat(bean.value).isEqualTo(99);
  }

  @Test
  public void testFieldReflectionAdapterWithNestedObject() {
    Gson gson = new GsonBuilder().create();
    String json = "{\"label\":\"outer\",\"inner\":{\"name\":\"inner\",\"value\":5}}";

    NestedBean bean = gson.fromJson(json, NestedBean.class);

    assertThat(bean.label).isEqualTo("outer");
    assertThat(bean.inner).isNotNull();
    assertThat(bean.inner.name).isEqualTo("inner");
    assertThat(bean.inner.value).isEqualTo(5);
  }

  @Test
  public void testFieldReflectionAdapterRoundTrip() {
    Gson gson = new GsonBuilder().create();
    SimpleBean original = new SimpleBean("roundtrip", 123);

    String json = gson.toJson(original);
    SimpleBean deserialized = gson.fromJson(json, SimpleBean.class);

    assertThat(deserialized.name).isEqualTo("roundtrip");
    assertThat(deserialized.value).isEqualTo(123);
  }
}
