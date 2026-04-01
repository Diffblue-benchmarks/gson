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
import static org.junit.Assert.assertThrows;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.bind.DefaultDateTypeAdapter.DateType;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.junit.Test;

public final class DefaultDateTypeAdapterTest {

  private TypeAdapter<Date> adapterForPattern(String pattern) {
    TypeAdapterFactory factory = DateType.DATE.createAdapterFactory(pattern);
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
    return gson.getAdapter(Date.class);
  }

  private TypeAdapter<Date> adapterForStyle(int dateStyle, int timeStyle) {
    TypeAdapterFactory factory = DateType.DATE.createAdapterFactory(dateStyle, timeStyle);
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
    return gson.getAdapter(Date.class);
  }

  @Test
  public void testWriteNullDate() throws IOException {
    TypeAdapter<Date> adapter = adapterForPattern("yyyy-MM-dd");
    String json = adapter.toJson(null);
    assertThat(json).isEqualTo("null");
  }

  @Test
  public void testWriteDate() throws IOException {
    TypeAdapter<Date> adapter = adapterForPattern("yyyy-MM-dd");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    sdf.setTimeZone(TimeZone.getDefault());
    Date date;
    try {
      date = sdf.parse("2024-01-15");
    } catch (java.text.ParseException e) {
      throw new RuntimeException(e);
    }
    String json = adapter.toJson(date);
    assertThat(json).isEqualTo("\"2024-01-15\"");
  }

  @Test
  public void testReadNullDate() throws IOException {
    TypeAdapter<Date> adapter = adapterForPattern("yyyy-MM-dd");
    Date result = adapter.fromJson("null");
    assertThat(result).isNull();
  }

  @Test
  public void testReadDateWithPattern() throws IOException {
    TypeAdapter<Date> adapter = adapterForPattern("yyyy-MM-dd");
    Date result = adapter.fromJson("\"2024-01-15\"");
    assertThat(result).isNotNull();

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    assertThat(sdf.format(result)).isEqualTo("2024-01-15");
  }

  @Test
  public void testReadDateWithISO8601Fallback() throws IOException {
    TypeAdapter<Date> adapter = adapterForPattern("yyyy-MM-dd");
    // ISO 8601 date-time string that won't match "yyyy-MM-dd" pattern
    Date result = adapter.fromJson("\"2024-01-15T10:30:00Z\"");
    assertThat(result).isNotNull();
  }

  @Test
  public void testReadDateInvalidThrowsJsonSyntaxException() {
    TypeAdapter<Date> adapter = adapterForPattern("yyyy-MM-dd");
    assertThrows(JsonSyntaxException.class, () -> adapter.fromJson("\"not-a-date\""));
  }

  @Test
  public void testToStringWithPatternConstructor() {
    TypeAdapterFactory factory = DateType.DATE.createAdapterFactory("yyyy-MM-dd");
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
    TypeAdapter<Date> adapter = gson.getAdapter(Date.class);
    assertThat(adapter.toString()).isEqualTo("DefaultDateTypeAdapter(yyyy-MM-dd)");
  }

  @Test
  public void testToStringWithStyleConstructor() {
    TypeAdapterFactory factory =
        DateType.DATE.createAdapterFactory(DateFormat.DEFAULT, DateFormat.DEFAULT);
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
    TypeAdapter<Date> adapter = gson.getAdapter(Date.class);
    String toStr = adapter.toString();
    assertThat(toStr).startsWith("DefaultDateTypeAdapter(");
  }

  @Test
  public void testWriteAndReadRoundTrip() throws IOException {
    TypeAdapter<Date> adapter = adapterForPattern("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
    Date original;
    try {
      original = sdf.parse("2024-06-15 08:30:00");
    } catch (java.text.ParseException e) {
      throw new RuntimeException(e);
    }

    String json = adapter.toJson(original);
    Date deserialized = adapter.fromJson(json);

    assertThat(sdf.format(deserialized)).isEqualTo(sdf.format(original));
  }

  @Test
  public void testAdapterWithDateStyleAndTimeStyle() throws IOException {
    TypeAdapter<Date> adapter = adapterForStyle(DateFormat.SHORT, DateFormat.SHORT);
    assertThat(adapter).isNotNull();

    // A date formatted with SHORT style and then parsed back should work
    DateFormat fmt = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.US);
    String formatted = fmt.format(new Date(0));
    Date result = adapter.fromJson("\"" + formatted + "\"");
    assertThat(result).isNotNull();
  }

  @Test
  public void testDefaultStyleFactory() {
    Gson gson = new GsonBuilder()
        .registerTypeAdapterFactory(DefaultDateTypeAdapter.DEFAULT_STYLE_FACTORY)
        .create();
    TypeAdapter<Date> adapter = gson.getAdapter(Date.class);
    assertThat(adapter).isNotNull();
  }

  @Test
  public void testDefaultStyleFactoryToString() {
    assertThat(DefaultDateTypeAdapter.DEFAULT_STYLE_FACTORY.toString())
        .isEqualTo("DefaultDateTypeAdapter#DEFAULT_STYLE_FACTORY");
  }

  @Test
  public void testDefaultStyleFactoryReturnsNullForNonDateType() {
    Gson gson = new Gson();
    TypeAdapter<?> adapter =
        DefaultDateTypeAdapter.DEFAULT_STYLE_FACTORY.create(gson, TypeToken.get(String.class));
    assertThat(adapter).isNull();
  }

  @Test
  public void testDeserializeToDateWithMultipleFormats() throws IOException {
    // Use a pattern-based adapter that tries pattern first, then ISO8601
    TypeAdapter<Date> adapter = adapterForPattern("MM/dd/yyyy");
    // Provide ISO 8601 which won't match the pattern
    Date result = adapter.fromJson("\"2024-01-15T00:00:00+00:00\"");
    assertThat(result).isNotNull();
  }
}
