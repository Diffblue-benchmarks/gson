/*
 * Copyright (C) 2020 Google Inc.
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

import com.diffblue.cover.annotations.InterestingTestFactory;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import java.util.Date;

/**
 * Test factory for SqlTimestampTypeAdapter to enable Diffblue Cover test generation. This factory
 * provides a way to construct instances of SqlTimestampTypeAdapter which has a package-private
 * constructor requiring a TypeAdapter<Date> parameter.
 */
public class SqlTimestampTypeAdapterDiffblueTestFactory {

  /**
   * Factory method to create instances of SqlTimestampTypeAdapter for testing. Uses a default Gson
   * instance to create the required Date TypeAdapter.
   *
   * @return a new instance of SqlTimestampTypeAdapter
   */
  @InterestingTestFactory
  public static SqlTimestampTypeAdapter createSqlTimestampTypeAdapter() {
    Gson gson = new Gson();
    TypeAdapter<Date> dateTypeAdapter = gson.getAdapter(Date.class);
    return new SqlTimestampTypeAdapter(dateTypeAdapter);
  }
}
