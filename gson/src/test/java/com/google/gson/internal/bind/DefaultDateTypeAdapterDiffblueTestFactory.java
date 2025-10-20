/*
 * Copyright (C) 2008 Google Inc.
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
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import java.text.DateFormat;
import java.util.Date;

/**
 * Factory class for creating DefaultDateTypeAdapter instances for Diffblue Cover testing.
 *
 * <p>Since DefaultDateTypeAdapter has private constructors, this factory provides methods to create
 * instances using the public factory methods available through DateType and TypeAdapterFactory.
 */
public class DefaultDateTypeAdapterDiffblueTestFactory {

  /**
   * Creates a DefaultDateTypeAdapter instance using the DEFAULT_STYLE_FACTORY with
   * DateFormat.DEFAULT for both date and time styles.
   *
   * @return a DefaultDateTypeAdapter instance for Date
   */
  @InterestingTestFactory
  public static DefaultDateTypeAdapter<Date> createDefaultStyleAdapter() {
    TypeAdapterFactory factory = DefaultDateTypeAdapter.DEFAULT_STYLE_FACTORY;
    Gson gson = new Gson();
    TypeToken<Date> typeToken = TypeToken.get(Date.class);
    @SuppressWarnings("unchecked")
    DefaultDateTypeAdapter<Date> adapter =
        (DefaultDateTypeAdapter<Date>) factory.create(gson, typeToken);
    return adapter;
  }

  /**
   * Creates a DefaultDateTypeAdapter instance using a custom date pattern.
   *
   * @return a DefaultDateTypeAdapter instance with a custom date pattern
   */
  @InterestingTestFactory
  public static DefaultDateTypeAdapter<Date> createCustomPatternAdapter() {
    TypeAdapterFactory factory =
        DefaultDateTypeAdapter.DateType.DATE.createAdapterFactory("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    Gson gson = new Gson();
    TypeToken<Date> typeToken = TypeToken.get(Date.class);
    @SuppressWarnings("unchecked")
    DefaultDateTypeAdapter<Date> adapter =
        (DefaultDateTypeAdapter<Date>) factory.create(gson, typeToken);
    return adapter;
  }

  /**
   * Creates a DefaultDateTypeAdapter instance using custom date and time styles.
   *
   * @return a DefaultDateTypeAdapter instance with MEDIUM date and time styles
   */
  @InterestingTestFactory
  public static DefaultDateTypeAdapter<Date> createCustomStyleAdapter() {
    TypeAdapterFactory factory =
        DefaultDateTypeAdapter.DateType.DATE.createAdapterFactory(
            DateFormat.MEDIUM, DateFormat.MEDIUM);
    Gson gson = new Gson();
    TypeToken<Date> typeToken = TypeToken.get(Date.class);
    @SuppressWarnings("unchecked")
    DefaultDateTypeAdapter<Date> adapter =
        (DefaultDateTypeAdapter<Date>) factory.create(gson, typeToken);
    return adapter;
  }

  /**
   * Creates a DefaultDateTypeAdapter instance using SHORT date and time styles.
   *
   * @return a DefaultDateTypeAdapter instance with SHORT date and time styles
   */
  @InterestingTestFactory
  public static DefaultDateTypeAdapter<Date> createShortStyleAdapter() {
    TypeAdapterFactory factory =
        DefaultDateTypeAdapter.DateType.DATE.createAdapterFactory(
            DateFormat.SHORT, DateFormat.SHORT);
    Gson gson = new Gson();
    TypeToken<Date> typeToken = TypeToken.get(Date.class);
    @SuppressWarnings("unchecked")
    DefaultDateTypeAdapter<Date> adapter =
        (DefaultDateTypeAdapter<Date>) factory.create(gson, typeToken);
    return adapter;
  }

  /**
   * Creates a DefaultDateTypeAdapter instance using LONG date and time styles.
   *
   * @return a DefaultDateTypeAdapter instance with LONG date and time styles
   */
  @InterestingTestFactory
  public static DefaultDateTypeAdapter<Date> createLongStyleAdapter() {
    TypeAdapterFactory factory =
        DefaultDateTypeAdapter.DateType.DATE.createAdapterFactory(DateFormat.LONG, DateFormat.LONG);
    Gson gson = new Gson();
    TypeToken<Date> typeToken = TypeToken.get(Date.class);
    @SuppressWarnings("unchecked")
    DefaultDateTypeAdapter<Date> adapter =
        (DefaultDateTypeAdapter<Date>) factory.create(gson, typeToken);
    return adapter;
  }

  /**
   * Creates a DefaultDateTypeAdapter instance using a simple date pattern.
   *
   * @return a DefaultDateTypeAdapter instance with a simple date pattern
   */
  @InterestingTestFactory
  public static DefaultDateTypeAdapter<Date> createSimpleDateAdapter() {
    TypeAdapterFactory factory =
        DefaultDateTypeAdapter.DateType.DATE.createAdapterFactory("yyyy-MM-dd");
    Gson gson = new Gson();
    TypeToken<Date> typeToken = TypeToken.get(Date.class);
    @SuppressWarnings("unchecked")
    DefaultDateTypeAdapter<Date> adapter =
        (DefaultDateTypeAdapter<Date>) factory.create(gson, typeToken);
    return adapter;
  }
}
