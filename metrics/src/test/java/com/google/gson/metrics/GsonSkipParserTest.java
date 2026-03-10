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

import java.lang.reflect.Method;
import org.junit.Test;

/**
 * Tests for {@link ParseBenchmark.GsonSkipParser}.
 *
 * <p>Note: This test uses reflection because GsonSkipParser is a private inner class of
 * ParseBenchmark.
 */
public class GsonSkipParserTest {

  @Test
  public void testParse_withSimpleJsonObject() throws Exception {
    Object parser = getGsonSkipParserInstance();
    Object document = getDocumentEnum("TWEETS");
    char[] data = "{\"key\":\"value\"}".toCharArray();

    invokeParseMethod(parser, data, document);
  }

  @Test
  public void testParse_withJsonArray() throws Exception {
    Object parser = getGsonSkipParserInstance();
    Object document = getDocumentEnum("READER_SHORT");
    char[] data = "[1, 2, 3, 4, 5]".toCharArray();

    invokeParseMethod(parser, data, document);
  }

  @Test
  public void testParse_withNestedJson() throws Exception {
    Object parser = getGsonSkipParserInstance();
    Object document = getDocumentEnum("READER_LONG");
    char[] data = "{\"nested\":{\"array\":[1,2,3],\"obj\":{\"a\":true}}}".toCharArray();

    invokeParseMethod(parser, data, document);
  }

  @Test
  public void testParse_withEmptyObject() throws Exception {
    Object parser = getGsonSkipParserInstance();
    Object document = getDocumentEnum("TWEETS");
    char[] data = "{}".toCharArray();

    invokeParseMethod(parser, data, document);
  }

  @Test
  public void testParse_withEmptyArray() throws Exception {
    Object parser = getGsonSkipParserInstance();
    Object document = getDocumentEnum("TWEETS");
    char[] data = "[]".toCharArray();

    invokeParseMethod(parser, data, document);
  }

  @SuppressWarnings("unchecked")
  private static <E extends Enum<E>> Object getEnumValue(Class<?> enumClass, String name) {
    return Enum.valueOf((Class<E>) enumClass, name);
  }

  private Object getGsonSkipParserInstance() throws Exception {
    Class<?> apiEnumClass = Class.forName("com.google.gson.metrics.ParseBenchmark$Api");
    Object gsonSkipEnum = getEnumValue(apiEnumClass, "GSON_SKIP");
    Method newParserMethod = apiEnumClass.getDeclaredMethod("newParser");
    newParserMethod.setAccessible(true);
    return newParserMethod.invoke(gsonSkipEnum);
  }

  private Object getDocumentEnum(String name) throws Exception {
    Class<?> documentEnumClass = Class.forName("com.google.gson.metrics.ParseBenchmark$Document");
    return getEnumValue(documentEnumClass, name);
  }

  private void invokeParseMethod(Object parser, char[] data, Object document) throws Exception {
    Class<?> parserInterface = Class.forName("com.google.gson.metrics.ParseBenchmark$Parser");
    Class<?> documentClass = Class.forName("com.google.gson.metrics.ParseBenchmark$Document");
    Method parseMethod = parserInterface.getDeclaredMethod("parse", char[].class, documentClass);
    parseMethod.setAccessible(true);
    parseMethod.invoke(parser, data, document);
  }
}
