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

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
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
}
