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
 * Unit tests for {@link ParseBenchmark.JacksonStreamParser} using reflection
 * to access the private inner class.
 */
public class JacksonStreamParserTest {

  /**
   * Helper method to invoke the JacksonStreamParser via reflection.
   */
  private void parseWithJacksonStreamParser(String json) throws Exception {
    Class<?> benchmarkClass = ParseBenchmark.class;

    // Get the Api enum
    Class<?> apiEnum = null;
    for (Class<?> inner : benchmarkClass.getDeclaredClasses()) {
      if (inner.getSimpleName().equals("Api")) {
        apiEnum = inner;
        break;
      }
    }
    if (apiEnum == null) {
      throw new IllegalStateException("Api enum not found");
    }

    // Get JACKSON_STREAM enum constant
    Object jacksonStream = null;
    for (Object constant : apiEnum.getEnumConstants()) {
      if (constant.toString().equals("JACKSON_STREAM")) {
        jacksonStream = constant;
        break;
      }
    }
    if (jacksonStream == null) {
      throw new IllegalStateException("JACKSON_STREAM constant not found");
    }

    // Call newParser() to get a Parser instance
    Method newParserMethod = apiEnum.getDeclaredMethod("newParser");
    newParserMethod.setAccessible(true);
    Object parser = newParserMethod.invoke(jacksonStream);

    // Get the Document enum
    Class<?> documentEnum = null;
    for (Class<?> inner : benchmarkClass.getDeclaredClasses()) {
      if (inner.getSimpleName().equals("Document")) {
        documentEnum = inner;
        break;
      }
    }
    if (documentEnum == null) {
      throw new IllegalStateException("Document enum not found");
    }

    // Get TWEETS enum constant (any document will work since it's not used by JacksonStreamParser)
    Object tweets = null;
    for (Object constant : documentEnum.getEnumConstants()) {
      if (constant.toString().equals("TWEETS")) {
        tweets = constant;
        break;
      }
    }
    if (tweets == null) {
      throw new IllegalStateException("TWEETS constant not found");
    }

    // Call parse(char[], Document)
    Method parseMethod = parser.getClass().getDeclaredMethod("parse", char[].class, documentEnum);
    parseMethod.setAccessible(true);
    parseMethod.invoke(parser, json.toCharArray(), tweets);
  }

  @Test
  public void testParseSimpleObject() throws Exception {
    parseWithJacksonStreamParser("{\"name\":\"value\"}");
  }

  @Test
  public void testParseSimpleArray() throws Exception {
    parseWithJacksonStreamParser("[1, 2, 3]");
  }

  @Test
  public void testParseNestedObject() throws Exception {
    parseWithJacksonStreamParser("{\"outer\":{\"inner\":\"value\"}}");
  }

  @Test
  public void testParseNestedArray() throws Exception {
    parseWithJacksonStreamParser("[[1, 2], [3, 4]]");
  }

  @Test
  public void testParseBooleans() throws Exception {
    parseWithJacksonStreamParser("{\"trueVal\":true,\"falseVal\":false}");
  }

  @Test
  public void testParseNull() throws Exception {
    parseWithJacksonStreamParser("{\"nullVal\":null}");
  }

  @Test
  public void testParseFloatNumber() throws Exception {
    parseWithJacksonStreamParser("{\"floatVal\":3.14159}");
  }

  @Test
  public void testParseIntegerNumber() throws Exception {
    parseWithJacksonStreamParser("{\"intVal\":42}");
  }

  @Test
  public void testParseStringValue() throws Exception {
    parseWithJacksonStreamParser("{\"strVal\":\"hello world\"}");
  }

  @Test
  public void testParseComplexJson() throws Exception {
    parseWithJacksonStreamParser("{\"users\":[{\"name\":\"John\",\"age\":30,\"active\":true,\"score\":98.5,\"address\":null}]}");
  }

  @Test
  public void testParseArrayOfObjects() throws Exception {
    parseWithJacksonStreamParser("[{\"id\":1},{\"id\":2}]");
  }

  @Test
  public void testParseAllTokenTypes() throws Exception {
    parseWithJacksonStreamParser("{\"string\":\"text\",\"int\":123,\"float\":1.5,\"true\":true,\"false\":false,\"null\":null,\"array\":[1,\"a\"],\"object\":{\"nested\":true}}");
  }

  @Test
  public void testParseEmptyObject() throws Exception {
    parseWithJacksonStreamParser("{}");
  }

  @Test
  public void testParseEmptyArray() throws Exception {
    parseWithJacksonStreamParser("[]");
  }
}
