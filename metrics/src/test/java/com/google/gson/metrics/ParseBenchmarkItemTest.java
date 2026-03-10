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
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import org.junit.Test;

/**
 * Tests for {@link ParseBenchmark.Item#toString()}.
 */
public class ParseBenchmarkItemTest {

  @Test
  public void testToStringWithAllFields() {
    ParseBenchmark.Item item = new ParseBenchmark.Item();
    item.title = "Test Title";
    item.author = "Test Author";
    item.published = 1234567890L;
    item.updated = 9876543210L;

    ParseBenchmark.Content content = new ParseBenchmark.Content();
    content.content = "Test content body";
    item.content = content;

    ParseBenchmark.ReaderUser user = new ParseBenchmark.ReaderUser();
    user.userId = "user123";
    item.likingUsers = Collections.singletonList(user);

    ParseBenchmark.Link link = new ParseBenchmark.Link();
    link.href = "http://example.com";
    item.alternates = Collections.singletonList(link);

    item.categories = Arrays.asList("category1", "category2");

    String result = item.toString();

    assertTrue(result.contains("Test Title"));
    assertTrue(result.contains("author: Test Author"));
    assertTrue(result.contains("published: 1234567890"));
    assertTrue(result.contains("updated: 9876543210"));
    assertTrue(result.contains("Test content body"));
    assertTrue(result.contains("liking users: [user123]"));
    assertTrue(result.contains("alternates: [http://example.com]"));
    assertTrue(result.contains("categories: [category1, category2]"));
  }

  @Test
  public void testToStringWithNullFields() {
    ParseBenchmark.Item item = new ParseBenchmark.Item();
    item.title = "Only Title";

    String result = item.toString();

    assertTrue(result.contains("Only Title"));
    assertTrue(result.contains("author: null"));
    assertTrue(result.contains("published: 0"));
    assertTrue(result.contains("updated: 0"));
    assertTrue(result.contains("liking users: null"));
    assertTrue(result.contains("alternates: null"));
    assertTrue(result.contains("categories: null"));
  }

  @Test
  public void testToStringFormat() {
    ParseBenchmark.Item item = new ParseBenchmark.Item();
    item.title = "Title";
    item.author = "Author";
    item.published = 100L;
    item.updated = 200L;

    String result = item.toString();

    assertEquals(
        "Title\n"
            + "author: Author\n"
            + "published: 100\n"
            + "updated: 200\n"
            + "null\n"
            + "liking users: null\n"
            + "alternates: null\n"
            + "categories: null",
        result);
  }
}
