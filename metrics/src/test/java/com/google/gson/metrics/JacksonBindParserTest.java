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
 * Unit tests for {@link ParseBenchmark.JacksonBindParser}.
 */
public class JacksonBindParserTest {

  /**
   * Tests parsing a JSON array of tweets using JacksonBindParser.
   * Uses reflection to access the private inner class and enum.
   */
  @Test
  public void testParseTweetsDocument() throws Exception {
    // Get the private JacksonBindParser class
    Class<?> parserClass = null;
    for (Class<?> innerClass : ParseBenchmark.class.getDeclaredClasses()) {
      if (innerClass.getSimpleName().equals("JacksonBindParser")) {
        parserClass = innerClass;
        break;
      }
    }
    assertNotNull("JacksonBindParser class not found", parserClass);

    // Get the Document enum
    Class<?> documentClass = null;
    for (Class<?> innerClass : ParseBenchmark.class.getDeclaredClasses()) {
      if (innerClass.getSimpleName().equals("Document")) {
        documentClass = innerClass;
        break;
      }
    }
    assertNotNull("Document enum not found", documentClass);

    // Get TWEETS enum constant
    Object tweetsDocument = null;
    for (Object enumConstant : documentClass.getEnumConstants()) {
      if (enumConstant.toString().equals("TWEETS")) {
        tweetsDocument = enumConstant;
        break;
      }
    }
    assertNotNull("TWEETS document not found", tweetsDocument);

    // Create parser instance - get the no-arg constructor
    Constructor<?> constructor = parserClass.getDeclaredConstructor();
    constructor.setAccessible(true);
    Object parser = constructor.newInstance();

    // Get parse method
    Method parseMethod = parserClass.getDeclaredMethod("parse", char[].class, documentClass);
    parseMethod.setAccessible(true);

    // Create valid JSON representing a list of tweets
    String json = "[{\"id\": 123456789, \"text\": \"Hello world\", \"favorited\": false, "
        + "\"truncated\": false, \"retweeted\": false, "
        + "\"user\": {\"id\": 987654321, \"name\": \"Test User\", \"screen_name\": \"testuser\"}}]";

    // Call parse method - this covers lines 302-303
    parseMethod.invoke(parser, json.toCharArray(), tweetsDocument);
  }

  /**
   * Tests parsing a JSON feed using JacksonBindParser with READER_SHORT document.
   */
  @Test
  public void testParseFeedDocument() throws Exception {
    // Get the private JacksonBindParser class
    Class<?> parserClass = null;
    for (Class<?> innerClass : ParseBenchmark.class.getDeclaredClasses()) {
      if (innerClass.getSimpleName().equals("JacksonBindParser")) {
        parserClass = innerClass;
        break;
      }
    }
    assertNotNull("JacksonBindParser class not found", parserClass);

    // Get the Document enum
    Class<?> documentClass = null;
    for (Class<?> innerClass : ParseBenchmark.class.getDeclaredClasses()) {
      if (innerClass.getSimpleName().equals("Document")) {
        documentClass = innerClass;
        break;
      }
    }
    assertNotNull("Document enum not found", documentClass);

    // Get READER_SHORT enum constant
    Object readerShortDocument = null;
    for (Object enumConstant : documentClass.getEnumConstants()) {
      if (enumConstant.toString().equals("READER_SHORT")) {
        readerShortDocument = enumConstant;
        break;
      }
    }
    assertNotNull("READER_SHORT document not found", readerShortDocument);

    // Create parser instance - get the no-arg constructor
    Constructor<?> constructor = parserClass.getDeclaredConstructor();
    constructor.setAccessible(true);
    Object parser = constructor.newInstance();

    // Get parse method
    Method parseMethod = parserClass.getDeclaredMethod("parse", char[].class, documentClass);
    parseMethod.setAccessible(true);

    // Create valid JSON representing a Feed
    String json = "{\"id\": \"feed-123\", \"title\": \"Test Feed\", \"description\": \"A test feed\", "
        + "\"updated\": 1234567890, \"items\": []}";

    // Call parse method - this covers lines 302-303
    parseMethod.invoke(parser, json.toCharArray(), readerShortDocument);
  }
}
