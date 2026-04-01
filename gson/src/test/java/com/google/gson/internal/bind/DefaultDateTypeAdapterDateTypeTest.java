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
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import java.text.DateFormat;
import java.util.Date;
import org.junit.Test;

public final class DefaultDateTypeAdapterDateTypeTest {

  @Test
  public void testDateTypeDateConstructorAndDeserialize() {
    Date now = new Date();
    Date result = DefaultDateTypeAdapter.DateType.DATE.deserialize(now);

    assertThat(result).isSameInstanceAs(now);
  }

  @Test
  public void testCreateAdapterFactoryWithDatePattern() {
    TypeAdapterFactory factory =
        DefaultDateTypeAdapter.DateType.DATE.createAdapterFactory("yyyy-MM-dd");

    assertThat(factory).isNotNull();
  }

  @Test
  public void testCreateAdapterFactoryWithDatePatternCreatesWorkingAdapter() {
    TypeAdapterFactory factory =
        DefaultDateTypeAdapter.DateType.DATE.createAdapterFactory("yyyy-MM-dd");
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();

    TypeAdapter<Date> adapter = factory.create(gson, TypeToken.get(Date.class));
    assertThat(adapter).isNotNull();
  }

  @Test
  public void testCreateAdapterFactoryWithDatePatternReturnsNullForOtherTypes() {
    TypeAdapterFactory factory =
        DefaultDateTypeAdapter.DateType.DATE.createAdapterFactory("yyyy-MM-dd");
    Gson gson = new Gson();

    TypeAdapter<String> adapter = factory.create(gson, TypeToken.get(String.class));
    assertThat(adapter).isNull();
  }

  @Test
  public void testCreateAdapterFactoryWithStyles() {
    TypeAdapterFactory factory =
        DefaultDateTypeAdapter.DateType.DATE.createAdapterFactory(DateFormat.DEFAULT, DateFormat.DEFAULT);

    assertThat(factory).isNotNull();
  }

  @Test
  public void testCreateAdapterFactoryWithStylesCreatesWorkingAdapter() {
    TypeAdapterFactory factory =
        DefaultDateTypeAdapter.DateType.DATE.createAdapterFactory(
            DateFormat.DEFAULT, DateFormat.DEFAULT);
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();

    TypeAdapter<Date> adapter = factory.create(gson, TypeToken.get(Date.class));
    assertThat(adapter).isNotNull();
  }

  @Test
  public void testCreateAdapterFactoryWithStylesReturnsNullForOtherTypes() {
    TypeAdapterFactory factory =
        DefaultDateTypeAdapter.DateType.DATE.createAdapterFactory(
            DateFormat.SHORT, DateFormat.SHORT);
    Gson gson = new Gson();

    TypeAdapter<String> adapter = factory.create(gson, TypeToken.get(String.class));
    assertThat(adapter).isNull();
  }

  @Test
  public void testCustomDateTypeConstructorAndDeserialize() {
    DefaultDateTypeAdapter.DateType<Date> customDateType =
        new DefaultDateTypeAdapter.DateType<Date>(Date.class) {
          @Override
          protected Date deserialize(Date date) {
            return new Date(date.getTime() + 1000);
          }
        };

    Date input = new Date(0);
    Date result = customDateType.deserialize(input);
    assertThat(result.getTime()).isEqualTo(1000L);
  }

  @Test
  public void testCustomDateTypeCreateAdapterFactoryWithPattern() {
    DefaultDateTypeAdapter.DateType<Date> customDateType =
        new DefaultDateTypeAdapter.DateType<Date>(Date.class) {
          @Override
          protected Date deserialize(Date date) {
            return date;
          }
        };

    TypeAdapterFactory factory = customDateType.createAdapterFactory("yyyy/MM/dd");
    assertThat(factory).isNotNull();
  }

  @Test
  public void testCustomDateTypeCreateAdapterFactoryWithStyles() {
    DefaultDateTypeAdapter.DateType<Date> customDateType =
        new DefaultDateTypeAdapter.DateType<Date>(Date.class) {
          @Override
          protected Date deserialize(Date date) {
            return date;
          }
        };

    TypeAdapterFactory factory =
        customDateType.createAdapterFactory(DateFormat.LONG, DateFormat.LONG);
    assertThat(factory).isNotNull();
  }
}
