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
 * Tests for {@link ParseBenchmark.GsonStreamParser}.
 *
 * <p>This test uses reflection to access the private GsonStreamParser class and its methods, since
 * the benchmark classes are intentionally private.
 */
public class GsonStreamParserTest {

  /** Gets the private Parser interface and its parse method via reflection. */
  private static Object createGsonStreamParser() throws Exception {
    // Access the private Api enum
    Class<?> apiClass = Class.forName("com.google.gson.metrics.ParseBenchmark$Api");

    // Get the GSON_STREAM enum constant
    Object gsonStream = null;
    for (Object enumConstant : apiClass.getEnumConstants()) {
      if (enumConstant.toString().equals("GSON_STREAM")) {
        gsonStream = enumConstant;
        break;
      }
    }

    // Call newParser() to get a GsonStreamParser instance
    Method newParserMethod = apiClass.getDeclaredMethod("newParser");
    newParserMethod.setAccessible(true);
    return newParserMethod.invoke(gsonStream);
  }

  /** Gets a Document enum constant by name via reflection. */
  private static Object getDocument(String name) throws Exception {
    Class<?> documentClass = Class.forName("com.google.gson.metrics.ParseBenchmark$Document");
    for (Object enumConstant : documentClass.getEnumConstants()) {
      if (enumConstant.toString().equals(name)) {
        return enumConstant;
      }
    }
    throw new IllegalArgumentException("No Document named: " + name);
  }

  /** Invokes the parse method on a parser with the given JSON data. */
  private static void invokeParse(Object parser, char[] data) throws Exception {
    Class<?> documentClass = Class.forName("com.google.gson.metrics.ParseBenchmark$Document");
    Method parseMethod = parser.getClass().getMethod("parse", char[].class, documentClass);
    parseMethod.setAccessible(true);
    parseMethod.invoke(parser, data, getDocument("TWEETS"));
  }

  @Test
  public void testParseEmptyObject() throws Exception {
    Object parser = createGsonStreamParser();
    invokeParse(parser, "{}".toCharArray());
  }

  @Test
  public void testParseEmptyArray() throws Exception {
    Object parser = createGsonStreamParser();
    invokeParse(parser, "[]".toCharArray());
  }

  @Test
  public void testParseObjectWithString() throws Exception {
    Object parser = createGsonStreamParser();
    invokeParse(parser, "{\"name\":\"value\"}".toCharArray());
  }

  @Test
  public void testParseObjectWithNumber() throws Exception {
    Object parser = createGsonStreamParser();
    invokeParse(parser, "{\"count\":42}".toCharArray());
  }

  @Test
  public void testParseObjectWithBoolean() throws Exception {
    Object parser = createGsonStreamParser();
    invokeParse(parser, "{\"active\":true,\"deleted\":false}".toCharArray());
  }

  @Test
  public void testParseObjectWithNull() throws Exception {
    Object parser = createGsonStreamParser();
    invokeParse(parser, "{\"value\":null}".toCharArray());
  }

  @Test
  public void testParseNestedObject() throws Exception {
    Object parser = createGsonStreamParser();
    invokeParse(parser, "{\"outer\":{\"inner\":\"value\"}}".toCharArray());
  }

  @Test
  public void testParseArrayWithMixedValues() throws Exception {
    Object parser = createGsonStreamParser();
    invokeParse(
        parser, "[\"string\",42,true,false,null,{\"key\":\"value\"},[1,2,3]]".toCharArray());
  }

  @Test
  public void testParseComplexStructure() throws Exception {
    Object parser = createGsonStreamParser();
    String json =
        "{\"id\":12345,\"name\":\"test\",\"active\":true,\"tags\":[\"a\",\"b\"],"
            + "\"nested\":{\"count\":100,\"flag\":false,\"nothing\":null}}";
    invokeParse(parser, json.toCharArray());
  }
}
