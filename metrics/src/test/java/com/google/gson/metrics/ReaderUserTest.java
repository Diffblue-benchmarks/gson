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

package com.google.gson.metrics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 * Unit tests for {@link ParseBenchmark.ReaderUser}.
 */
public class ReaderUserTest {

  @Test
  public void testToStringReturnsUserId() {
    ParseBenchmark.ReaderUser readerUser = new ParseBenchmark.ReaderUser();
    readerUser.userId = "user123";

    assertEquals("user123", readerUser.toString());
  }

  @Test
  public void testToStringWithNullUserId() {
    ParseBenchmark.ReaderUser readerUser = new ParseBenchmark.ReaderUser();

    assertNull(readerUser.toString());
  }

  @Test
  public void testToStringWithEmptyUserId() {
    ParseBenchmark.ReaderUser readerUser = new ParseBenchmark.ReaderUser();
    readerUser.userId = "";

    assertEquals("", readerUser.toString());
  }
}
