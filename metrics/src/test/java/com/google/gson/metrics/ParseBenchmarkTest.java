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

package com.google.gson.metrics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import org.junit.Test;

/**
 * Unit tests for {@link ParseBenchmark}
 */
public class ParseBenchmarkTest {

  @Test
  public void testSetUpWithGsonBind() throws Exception {
    ParseBenchmark benchmark = new ParseBenchmark();
    setField(benchmark, "document", getDocumentEnum("TWEETS"));
    setField(benchmark, "api", getApiEnum("GSON_BIND"));

    benchmark.setUp();

    // Verify that text and parser fields were initialized
    Field textField = ParseBenchmark.class.getDeclaredField("text");
    textField.setAccessible(true);
    char[] text = (char[]) textField.get(benchmark);
    assertNotNull(text);
    assertTrue(text.length > 0);

    Field parserField = ParseBenchmark.class.getDeclaredField("parser");
    parserField.setAccessible(true);
    Object parser = parserField.get(benchmark);
    assertNotNull(parser);
  }

  @Test
  public void testSetUpWithGsonStream() throws Exception {
    ParseBenchmark benchmark = new ParseBenchmark();
    setField(benchmark, "document", getDocumentEnum("READER_SHORT"));
    setField(benchmark, "api", getApiEnum("GSON_STREAM"));

    benchmark.setUp();

    Field textField = ParseBenchmark.class.getDeclaredField("text");
    textField.setAccessible(true);
    char[] text = (char[]) textField.get(benchmark);
    assertNotNull(text);
    assertTrue(text.length > 0);
  }

  @Test
  public void testSetUpWithGsonDom() throws Exception {
    ParseBenchmark benchmark = new ParseBenchmark();
    setField(benchmark, "document", getDocumentEnum("READER_LONG"));
    setField(benchmark, "api", getApiEnum("GSON_DOM"));

    benchmark.setUp();

    Field textField = ParseBenchmark.class.getDeclaredField("text");
    textField.setAccessible(true);
    char[] text = (char[]) textField.get(benchmark);
    assertNotNull(text);
    assertTrue(text.length > 0);
  }

  @Test
  public void testTimeParseWithGsonBind() throws Exception {
    ParseBenchmark benchmark = new ParseBenchmark();
    setField(benchmark, "document", getDocumentEnum("TWEETS"));
    setField(benchmark, "api", getApiEnum("GSON_BIND"));

    benchmark.setUp();
    // Should complete without throwing an exception
    benchmark.timeParse(1);
  }

  @Test
  public void testTimeParseWithGsonStream() throws Exception {
    ParseBenchmark benchmark = new ParseBenchmark();
    setField(benchmark, "document", getDocumentEnum("TWEETS"));
    setField(benchmark, "api", getApiEnum("GSON_STREAM"));

    benchmark.setUp();
    benchmark.timeParse(2);
  }

  @Test
  public void testTimeParseWithGsonSkip() throws Exception {
    ParseBenchmark benchmark = new ParseBenchmark();
    setField(benchmark, "document", getDocumentEnum("READER_SHORT"));
    setField(benchmark, "api", getApiEnum("GSON_SKIP"));

    benchmark.setUp();
    benchmark.timeParse(1);
  }

  @Test
  public void testTimeParseWithJacksonBind() throws Exception {
    ParseBenchmark benchmark = new ParseBenchmark();
    setField(benchmark, "document", getDocumentEnum("TWEETS"));
    setField(benchmark, "api", getApiEnum("JACKSON_BIND"));

    benchmark.setUp();
    benchmark.timeParse(1);
  }

  @Test
  public void testTimeParseWithJacksonStream() throws Exception {
    ParseBenchmark benchmark = new ParseBenchmark();
    setField(benchmark, "document", getDocumentEnum("TWEETS"));
    setField(benchmark, "api", getApiEnum("JACKSON_STREAM"));

    benchmark.setUp();
    benchmark.timeParse(1);
  }

  @Test
  public void testGetResourceFileThrowsForNonExistentResource() throws Exception {
    Method method = ParseBenchmark.class.getDeclaredMethod("getResourceFile", String.class);
    method.setAccessible(true);

    try {
      method.invoke(null, "/nonexistent-resource.txt");
      fail("Expected IllegalArgumentException");
    } catch (java.lang.reflect.InvocationTargetException e) {
      Throwable cause = e.getCause();
      assertTrue(cause instanceof IllegalArgumentException);
      assertTrue(cause.getMessage().contains("does not exist"));
    }
  }

  @Test
  public void testResourceToStringReadsTweetsJson() throws Exception {
    Method method = ParseBenchmark.class.getDeclaredMethod("resourceToString", String.class);
    method.setAccessible(true);

    String content = (String) method.invoke(null, "TWEETS.json");
    assertNotNull(content);
    assertTrue(content.length() > 0);
    // TWEETS.json should contain JSON array with tweet objects
    assertTrue(content.startsWith("["));
  }

  @Test
  public void testResourceToStringReadsReaderShortJson() throws Exception {
    Method method = ParseBenchmark.class.getDeclaredMethod("resourceToString", String.class);
    method.setAccessible(true);

    String content = (String) method.invoke(null, "READER_SHORT.json");
    assertNotNull(content);
    assertTrue(content.length() > 0);
  }

  @Test
  public void testResourceToStringReadsReaderLongJson() throws Exception {
    Method method = ParseBenchmark.class.getDeclaredMethod("resourceToString", String.class);
    method.setAccessible(true);

    String content = (String) method.invoke(null, "READER_LONG.json");
    assertNotNull(content);
    assertTrue(content.length() > 0);
  }

  private void setField(Object target, String fieldName, Object value) throws Exception {
    Field field = target.getClass().getDeclaredField(fieldName);
    field.setAccessible(true);
    field.set(target, value);
  }

  @SuppressWarnings("unchecked")
  private Object getDocumentEnum(String name) {
    Class<?>[] declaredClasses = ParseBenchmark.class.getDeclaredClasses();
    for (Class<?> clazz : declaredClasses) {
      if (clazz.getSimpleName().equals("Document") && clazz.isEnum()) {
        return Enum.valueOf((Class<Enum>) clazz, name);
      }
    }
    throw new IllegalArgumentException("Document enum not found");
  }

  @SuppressWarnings("unchecked")
  private Object getApiEnum(String name) {
    Class<?>[] declaredClasses = ParseBenchmark.class.getDeclaredClasses();
    for (Class<?> clazz : declaredClasses) {
      if (clazz.getSimpleName().equals("Api") && clazz.isEnum()) {
        return Enum.valueOf((Class<Enum>) clazz, name);
      }
    }
    throw new IllegalArgumentException("Api enum not found");
  }

  @Test
  public void testFeedToStringWithAllFields() {
    ParseBenchmark.Feed feed = new ParseBenchmark.Feed();
    feed.id = "feed-123";
    feed.title = "Test Feed";
    feed.description = "A test feed description";
    feed.updated = 1234567890L;

    ParseBenchmark.Link link = new ParseBenchmark.Link();
    link.href = "http://example.com";
    feed.alternates = Collections.singletonList(link);

    ParseBenchmark.Item item = new ParseBenchmark.Item();
    item.title = "Item Title";
    item.author = "Author Name";
    item.published = 1000L;
    item.updated = 2000L;
    item.categories = Arrays.asList("cat1", "cat2");
    item.alternates = Collections.emptyList();
    item.likingUsers = Collections.emptyList();

    ParseBenchmark.Content content = new ParseBenchmark.Content();
    content.content = "Item content text";
    item.content = content;

    feed.items = Collections.singletonList(item);

    String result = feed.toString();

    assertTrue(result.contains("feed-123"));
    assertTrue(result.contains("Test Feed"));
    assertTrue(result.contains("A test feed description"));
    assertTrue(result.contains("http://example.com"));
    assertTrue(result.contains("1234567890"));
    assertTrue(result.contains("1: "));
    assertTrue(result.contains("Item Title"));
  }

  @Test
  public void testFeedToStringWithMultipleItems() {
    ParseBenchmark.Feed feed = new ParseBenchmark.Feed();
    feed.id = "multi-item-feed";
    feed.title = "Multi Item Feed";
    feed.description = "Feed with multiple items";
    feed.updated = 9999L;
    feed.alternates = Collections.emptyList();

    ParseBenchmark.Item item1 = new ParseBenchmark.Item();
    item1.title = "First Item";
    item1.author = "Author1";
    item1.published = 100L;
    item1.updated = 200L;
    item1.categories = Collections.emptyList();
    item1.alternates = Collections.emptyList();
    item1.likingUsers = Collections.emptyList();
    item1.content = new ParseBenchmark.Content();

    ParseBenchmark.Item item2 = new ParseBenchmark.Item();
    item2.title = "Second Item";
    item2.author = "Author2";
    item2.published = 300L;
    item2.updated = 400L;
    item2.categories = Collections.emptyList();
    item2.alternates = Collections.emptyList();
    item2.likingUsers = Collections.emptyList();
    item2.content = new ParseBenchmark.Content();

    feed.items = Arrays.asList(item1, item2);

    String result = feed.toString();

    assertTrue(result.contains("1: "));
    assertTrue(result.contains("2: "));
    assertTrue(result.contains("First Item"));
    assertTrue(result.contains("Second Item"));
  }

  @Test
  public void testFeedToStringWithNullFields() {
    ParseBenchmark.Feed feed = new ParseBenchmark.Feed();
    feed.id = null;
    feed.title = null;
    feed.description = null;
    feed.updated = 0L;
    feed.alternates = null;
    feed.items = Collections.emptyList();

    String result = feed.toString();

    assertNotNull(result);
    assertTrue(result.contains("null"));
    assertTrue(result.contains("0"));
  }

  @Test
  public void testFeedToStringWithEmptyItems() {
    ParseBenchmark.Feed feed = new ParseBenchmark.Feed();
    feed.id = "empty-feed";
    feed.title = "Empty Feed";
    feed.description = "No items";
    feed.updated = 5000L;
    feed.alternates = new ArrayList<>();
    feed.items = new ArrayList<>();

    String result = feed.toString();

    assertTrue(result.contains("empty-feed"));
    assertTrue(result.contains("Empty Feed"));
    assertTrue(result.contains("No items"));
    assertTrue(result.contains("5000"));
    // Should not contain any item numbers since items list is empty
    assertTrue(!result.contains("1: "));
  }
}
