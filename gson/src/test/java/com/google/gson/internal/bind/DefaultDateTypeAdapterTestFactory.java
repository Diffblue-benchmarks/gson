/*
 * Copyright (C) 2025 Google Inc.
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

import com.diffblue.cover.annotations.InterestingTestFactory;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.bind.DefaultDateTypeAdapter.DateType;
import com.google.gson.reflect.TypeToken;
import java.text.DateFormat;
import java.util.Date;

/**
 * Factory class for creating DefaultDateTypeAdapter instances for testing.
 * Since DefaultDateTypeAdapter has only private constructors, we use the public
 * factory methods provided by DateType to create instances.
 */
public class DefaultDateTypeAdapterTestFactory {

  /**
   * Creates a DefaultDateTypeAdapter instance using a date pattern.
   * This prevents issues with the private constructor.
   *
   * @return a DefaultDateTypeAdapter instance for testing
   */
  @InterestingTestFactory
  public static TypeAdapter<Date> createDefaultDateTypeAdapterWithPattern() {
    TypeAdapterFactory factory = DateType.DATE.createAdapterFactory("yyyy-MM-dd");
    Gson gson = new Gson();
    return factory.create(gson, TypeToken.get(Date.class));
  }

  /**
   * Creates a DefaultDateTypeAdapter instance using date and time styles.
   * This uses the DEFAULT style for both date and time.
   *
   * @return a DefaultDateTypeAdapter instance for testing
   */
  @InterestingTestFactory
  public static TypeAdapter<Date> createDefaultDateTypeAdapterWithStyles() {
    TypeAdapterFactory factory = DateType.DATE.createAdapterFactory(DateFormat.DEFAULT, DateFormat.DEFAULT);
    Gson gson = new Gson();
    return factory.create(gson, TypeToken.get(Date.class));
  }

  /**
   * Creates a DefaultDateTypeAdapter instance using MEDIUM style.
   *
   * @return a DefaultDateTypeAdapter instance for testing
   */
  @InterestingTestFactory
  public static TypeAdapter<Date> createDefaultDateTypeAdapterMediumStyle() {
    TypeAdapterFactory factory = DateType.DATE.createAdapterFactory(DateFormat.MEDIUM, DateFormat.MEDIUM);
    Gson gson = new Gson();
    return factory.create(gson, TypeToken.get(Date.class));
  }

  /**
   * Creates a DefaultDateTypeAdapter instance using SHORT style.
   *
   * @return a DefaultDateTypeAdapter instance for testing
   */
  @InterestingTestFactory
  public static TypeAdapter<Date> createDefaultDateTypeAdapterShortStyle() {
    TypeAdapterFactory factory = DateType.DATE.createAdapterFactory(DateFormat.SHORT, DateFormat.SHORT);
    Gson gson = new Gson();
    return factory.create(gson, TypeToken.get(Date.class));
  }

  /**
   * Creates a DefaultDateTypeAdapter instance using ISO 8601 date pattern.
   *
   * @return a DefaultDateTypeAdapter instance for testing
   */
  @InterestingTestFactory
  public static TypeAdapter<Date> createDefaultDateTypeAdapterISO8601() {
    TypeAdapterFactory factory = DateType.DATE.createAdapterFactory("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    Gson gson = new Gson();
    return factory.create(gson, TypeToken.get(Date.class));
  }

  /**
   * Creates a DefaultDateTypeAdapter instance using a simple date pattern.
   *
   * @return a DefaultDateTypeAdapter instance for testing
   */
  @InterestingTestFactory
  public static TypeAdapter<Date> createDefaultDateTypeAdapterSimple() {
    TypeAdapterFactory factory = DateType.DATE.createAdapterFactory("MMM dd, yyyy");
    Gson gson = new Gson();
    return factory.create(gson, TypeToken.get(Date.class));
  }
}
