/*
 * Copyright (C) 2026 Google Inc.
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
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.bind.DefaultDateTypeAdapter.DateType;
import com.google.gson.reflect.TypeToken;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;
import org.junit.Test;

public class DefaultDateTypeAdapterTest {

  private static class TestDateType extends DateType<Timestamp> {
    TestDateType() {
      super(Timestamp.class);
    }

    @Override
    protected Timestamp deserialize(Date date) {
      return new Timestamp(date.getTime());
    }
  }

  @Test
  public void testDateTypeConstructorAndDeserialize() {
    TestDateType dateType = new TestDateType();
    Date date = new Date(1000000L);
    Timestamp result = dateType.deserialize(date);

    assertThat(result).isNotNull();
    assertThat(result.getTime()).isEqualTo(1000000L);
  }

  @Test
  public void testCreateAdapterFactoryWithPattern() {
    TestDateType dateType = new TestDateType();
    TypeAdapterFactory factory = dateType.createAdapterFactory("yyyy-MM-dd");

    assertThat(factory).isNotNull();

    Gson gson = new Gson();
    TypeAdapter<Timestamp> adapter = factory.create(gson, TypeToken.get(Timestamp.class));

    assertThat(adapter).isNotNull();
  }

  @Test
  public void testCreateAdapterFactoryWithDateTimeStyles() {
    TestDateType dateType = new TestDateType();
    TypeAdapterFactory factory = dateType.createAdapterFactory(DateFormat.MEDIUM, DateFormat.MEDIUM);

    assertThat(factory).isNotNull();

    Gson gson = new Gson();
    TypeAdapter<Timestamp> adapter = factory.create(gson, TypeToken.get(Timestamp.class));

    assertThat(adapter).isNotNull();
  }

  @Test
  public void testCreateAdapterFactoryWithPatternReturnsNullForWrongType() {
    TestDateType dateType = new TestDateType();
    TypeAdapterFactory factory = dateType.createAdapterFactory("yyyy-MM-dd");

    Gson gson = new Gson();
    TypeAdapter<String> adapter = factory.create(gson, TypeToken.get(String.class));

    assertThat(adapter).isNull();
  }

  @Test
  public void testCreateAdapterFactoryWithStylesReturnsNullForWrongType() {
    TestDateType dateType = new TestDateType();
    TypeAdapterFactory factory = dateType.createAdapterFactory(DateFormat.SHORT, DateFormat.SHORT);

    Gson gson = new Gson();
    TypeAdapter<String> adapter = factory.create(gson, TypeToken.get(String.class));

    assertThat(adapter).isNull();
  }

}
