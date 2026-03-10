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

import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Method;
import org.junit.Test;

/**
 * Unit tests for {@link ParseBenchmark.GsonBindParser}.
 */
@SuppressWarnings("unchecked")
public class ParseBenchmarkGsonBindParserTest {

  @Test
  public void testParseWithTweetsDocument() throws Exception {
    // Get the private Api enum and GsonBindParser
    Class<?> apiEnum = Class.forName("com.google.gson.metrics.ParseBenchmark$Api");
    Object gsonBindApi = Enum.valueOf((Class<Enum>) apiEnum, "GSON_BIND");
    Method newParserMethod = apiEnum.getDeclaredMethod("newParser");
    newParserMethod.setAccessible(true);
    Object parser = newParserMethod.invoke(gsonBindApi);

    // Get the Document enum
    Class<?> documentEnum = Class.forName("com.google.gson.metrics.ParseBenchmark$Document");
    Object tweetsDocument = Enum.valueOf((Class<Enum>) documentEnum, "TWEETS");

    // Create a valid JSON array of Tweet objects
    String json = "[{\"id\":12345,\"text\":\"Hello world\",\"favorited\":false,\"truncated\":false,\"retweeted\":false}]";
    char[] data = json.toCharArray();

    // Get the parse method from the Parser interface
    Class<?> parserInterface = Class.forName("com.google.gson.metrics.ParseBenchmark$Parser");
    Method parseMethod = parserInterface.getDeclaredMethod("parse", char[].class, documentEnum);
    parseMethod.setAccessible(true);

    // Execute the parse method - should not throw
    parseMethod.invoke(parser, data, tweetsDocument);

    // If we get here without exception, parsing was successful
    assertNotNull(parser);
  }

  @Test
  public void testParseWithFeedDocument() throws Exception {
    // Get the private Api enum and GsonBindParser
    Class<?> apiEnum = Class.forName("com.google.gson.metrics.ParseBenchmark$Api");
    Object gsonBindApi = Enum.valueOf((Class<Enum>) apiEnum, "GSON_BIND");
    Method newParserMethod = apiEnum.getDeclaredMethod("newParser");
    newParserMethod.setAccessible(true);
    Object parser = newParserMethod.invoke(gsonBindApi);

    // Get the Document enum - use READER_SHORT for Feed type
    Class<?> documentEnum = Class.forName("com.google.gson.metrics.ParseBenchmark$Document");
    Object readerShortDocument = Enum.valueOf((Class<Enum>) documentEnum, "READER_SHORT");

    // Create a valid JSON Feed object
    String json = "{\"id\":\"feed1\",\"title\":\"Test Feed\",\"description\":\"A test feed\",\"updated\":1234567890,\"items\":[]}";
    char[] data = json.toCharArray();

    // Get the parse method from the Parser interface
    Class<?> parserInterface = Class.forName("com.google.gson.metrics.ParseBenchmark$Parser");
    Method parseMethod = parserInterface.getDeclaredMethod("parse", char[].class, documentEnum);
    parseMethod.setAccessible(true);

    // Execute the parse method - should not throw
    parseMethod.invoke(parser, data, readerShortDocument);

    // If we get here without exception, parsing was successful
    assertNotNull(parser);
  }

  @Test
  public void testParseWithReaderLongDocument() throws Exception {
    // Get the private Api enum and GsonBindParser
    Class<?> apiEnum = Class.forName("com.google.gson.metrics.ParseBenchmark$Api");
    Object gsonBindApi = Enum.valueOf((Class<Enum>) apiEnum, "GSON_BIND");
    Method newParserMethod = apiEnum.getDeclaredMethod("newParser");
    newParserMethod.setAccessible(true);
    Object parser = newParserMethod.invoke(gsonBindApi);

    // Get the Document enum
    Class<?> documentEnum = Class.forName("com.google.gson.metrics.ParseBenchmark$Document");
    Object readerLongDocument = Enum.valueOf((Class<Enum>) documentEnum, "READER_LONG");

    // Create a valid JSON Feed object with items
    String json = "{\"id\":\"feed2\",\"title\":\"Long Feed\",\"description\":\"A longer test feed\",\"updated\":9876543210,\"items\":[{\"title\":\"Item 1\",\"published\":111,\"updated\":222}]}";
    char[] data = json.toCharArray();

    // Get the parse method from the Parser interface
    Class<?> parserInterface = Class.forName("com.google.gson.metrics.ParseBenchmark$Parser");
    Method parseMethod = parserInterface.getDeclaredMethod("parse", char[].class, documentEnum);
    parseMethod.setAccessible(true);

    // Execute the parse method - should not throw and should parse successfully
    parseMethod.invoke(parser, data, readerLongDocument);

    // If we get here without exception, parsing was successful
    assertNotNull(parser);
  }
}
