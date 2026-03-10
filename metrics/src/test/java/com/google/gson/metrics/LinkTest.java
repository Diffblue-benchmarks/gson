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

/** Tests for {@link ParseBenchmark.Link}. */
public class LinkTest {

  @Test
  public void testToString_returnsHref() {
    ParseBenchmark.Link link = new ParseBenchmark.Link();
    link.href = "https://example.com";

    assertEquals("https://example.com", link.toString());
  }

  @Test
  public void testToString_withNullHref() {
    ParseBenchmark.Link link = new ParseBenchmark.Link();
    link.href = null;

    assertNull(link.toString());
  }

  @Test
  public void testToString_withEmptyHref() {
    ParseBenchmark.Link link = new ParseBenchmark.Link();
    link.href = "";

    assertEquals("", link.toString());
  }
}
