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

import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.junit.Test;

/**
 * Tests for {@link ParseBenchmark.GsonDomParser}.
 */
public class ParseBenchmarkGsonDomParserTest {

  @Test
  public void testParseValidJson() throws Exception {
    // Access the private GsonDomParser inner class via reflection
    Class<?> gsonDomParserClass = Class.forName(
        "com.google.gson.metrics.ParseBenchmark$GsonDomParser");
    Constructor<?> constructor = gsonDomParserClass.getDeclaredConstructor();
    constructor.setAccessible(true);
    Object parser = constructor.newInstance();

    // Access the private Document enum
    Class<?> documentClass = Class.forName(
        "com.google.gson.metrics.ParseBenchmark$Document");
    Object[] documentValues = documentClass.getEnumConstants();
    assertNotNull(documentValues);

    // Get the parse method
    Method parseMethod = gsonDomParserClass.getMethod("parse", char[].class, documentClass);

    // Test parsing valid JSON object
    String json = "{\"name\":\"test\",\"value\":123}";
    parseMethod.invoke(parser, json.toCharArray(), documentValues[0]);
  }

  @Test
  public void testParseValidJsonArray() throws Exception {
    // Access the private GsonDomParser inner class via reflection
    Class<?> gsonDomParserClass = Class.forName(
        "com.google.gson.metrics.ParseBenchmark$GsonDomParser");
    Constructor<?> constructor = gsonDomParserClass.getDeclaredConstructor();
    constructor.setAccessible(true);
    Object parser = constructor.newInstance();

    // Access the private Document enum
    Class<?> documentClass = Class.forName(
        "com.google.gson.metrics.ParseBenchmark$Document");
    Object[] documentValues = documentClass.getEnumConstants();

    // Get the parse method
    Method parseMethod = gsonDomParserClass.getMethod("parse", char[].class, documentClass);

    // Test parsing valid JSON array
    String json = "[1,2,3,\"test\",true,null]";
    parseMethod.invoke(parser, json.toCharArray(), documentValues[0]);
  }
}
