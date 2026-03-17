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

package com.google.gson.internal.sql;

import static com.google.common.truth.Truth.assertThat;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Date;
import org.junit.Test;

public class SqlDateTypeAdapterTest {

  @Test
  public void testFactoryCreatesAdapter() {
    Gson gson = new Gson();
    TypeToken<Date> typeToken = TypeToken.get(Date.class);

    TypeAdapter<Date> adapter = SqlDateTypeAdapter.FACTORY.create(gson, typeToken);

    assertThat(adapter).isNotNull();
    assertThat(adapter).isInstanceOf(SqlDateTypeAdapter.class);
  }

  @Test
  public void testFactoryReturnsNullForNonSqlDateType() {
    Gson gson = new Gson();
    TypeToken<String> typeToken = TypeToken.get(String.class);

    TypeAdapter<String> adapter = SqlDateTypeAdapter.FACTORY.create(gson, typeToken);

    assertThat(adapter).isNull();
  }

  @Test
  public void testReadNull() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Date> adapter = SqlDateTypeAdapter.FACTORY.create(gson, TypeToken.get(Date.class));
    JsonReader reader = new JsonReader(new StringReader("null"));

    Date result = adapter.read(reader);

    assertThat(result).isNull();
  }

  @Test
  public void testReadValidDate() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Date> adapter = SqlDateTypeAdapter.FACTORY.create(gson, TypeToken.get(Date.class));
    JsonReader reader = new JsonReader(new StringReader("\"Jan 1, 2020\""));

    Date result = adapter.read(reader);

    assertThat(result).isNotNull();
    assertThat(result).isInstanceOf(Date.class);
  }

  @Test
  public void testReadAnotherValidDate() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Date> adapter = SqlDateTypeAdapter.FACTORY.create(gson, TypeToken.get(Date.class));
    JsonReader reader = new JsonReader(new StringReader("\"Dec 31, 2021\""));

    Date result = adapter.read(reader);

    assertThat(result).isNotNull();
    assertThat(result).isInstanceOf(Date.class);
  }

  @Test
  public void testReadInvalidDateThrowsJsonSyntaxException() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Date> adapter = SqlDateTypeAdapter.FACTORY.create(gson, TypeToken.get(Date.class));
    JsonReader reader = new JsonReader(new StringReader("\"invalid-date\""));

    try {
      adapter.read(reader);
      throw new AssertionError("Expected JsonSyntaxException");
    } catch (JsonSyntaxException e) {
      assertThat(e.getMessage()).contains("Failed parsing 'invalid-date' as SQL Date");
    }
  }

  @Test
  public void testReadInvalidDateFormat() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Date> adapter = SqlDateTypeAdapter.FACTORY.create(gson, TypeToken.get(Date.class));
    JsonReader reader = new JsonReader(new StringReader("\"2020-01-01\""));

    try {
      adapter.read(reader);
      throw new AssertionError("Expected JsonSyntaxException");
    } catch (JsonSyntaxException e) {
      assertThat(e.getMessage()).contains("Failed parsing '2020-01-01' as SQL Date");
    }
  }

  @Test
  public void testWriteNull() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Date> adapter = SqlDateTypeAdapter.FACTORY.create(gson, TypeToken.get(Date.class));
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    adapter.write(writer, null);

    assertThat(stringWriter.toString()).isEqualTo("null");
  }

  @Test
  public void testWriteValidDate() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Date> adapter = SqlDateTypeAdapter.FACTORY.create(gson, TypeToken.get(Date.class));
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    Date date = Date.valueOf("2020-01-01");

    adapter.write(writer, date);

    String result = stringWriter.toString();
    assertThat(result).isNotNull();
    assertThat(result).contains("Jan");
    assertThat(result).contains("2020");
  }

  @Test
  public void testWriteAnotherValidDate() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Date> adapter = SqlDateTypeAdapter.FACTORY.create(gson, TypeToken.get(Date.class));
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    Date date = Date.valueOf("2021-12-31");

    adapter.write(writer, date);

    String result = stringWriter.toString();
    assertThat(result).isNotNull();
    assertThat(result).contains("Dec");
    assertThat(result).contains("2021");
  }

  @Test
  public void testRoundTrip() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<Date> adapter = SqlDateTypeAdapter.FACTORY.create(gson, TypeToken.get(Date.class));
    Date originalDate = Date.valueOf("2020-06-15");
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);

    adapter.write(writer, originalDate);
    String json = stringWriter.toString();
    JsonReader reader = new JsonReader(new StringReader(json));
    Date deserializedDate = adapter.read(reader);

    assertThat(deserializedDate).isNotNull();
    assertThat(deserializedDate.toString()).isEqualTo(originalDate.toString());
  }
}
