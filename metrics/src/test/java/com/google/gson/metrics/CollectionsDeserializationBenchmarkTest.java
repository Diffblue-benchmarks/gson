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

import java.lang.reflect.Field;
import org.junit.Test;

/**
 * Unit tests for {@link CollectionsDeserializationBenchmark}
 */
public class CollectionsDeserializationBenchmarkTest {

  @Test
  public void testSetUp() throws Exception {
    CollectionsDeserializationBenchmark benchmark = new CollectionsDeserializationBenchmark();
    benchmark.setUp();

    // Verify gson field was initialized
    Field gsonField = CollectionsDeserializationBenchmark.class.getDeclaredField("gson");
    gsonField.setAccessible(true);
    Object gson = gsonField.get(benchmark);
    assertNotNull(gson);

    // Verify json field was initialized
    Field jsonField = CollectionsDeserializationBenchmark.class.getDeclaredField("json");
    jsonField.setAccessible(true);
    String json = (String) jsonField.get(benchmark);
    assertNotNull(json);
    assertTrue(json.length() > 0);
    // Should be a JSON array
    assertTrue(json.startsWith("["));
    assertTrue(json.endsWith("]"));
  }

  @Test
  public void testTimeCollectionsDefault() throws Exception {
    CollectionsDeserializationBenchmark benchmark = new CollectionsDeserializationBenchmark();
    benchmark.setUp();

    // Should complete without throwing an exception
    benchmark.timeCollectionsDefault(1);
  }

  @Test
  public void testTimeCollectionsDefaultMultipleReps() throws Exception {
    CollectionsDeserializationBenchmark benchmark = new CollectionsDeserializationBenchmark();
    benchmark.setUp();

    // Should handle multiple repetitions
    benchmark.timeCollectionsDefault(5);
  }

  @Test
  public void testTimeCollectionsStreaming() throws Exception {
    CollectionsDeserializationBenchmark benchmark = new CollectionsDeserializationBenchmark();
    benchmark.setUp();

    // Should complete without throwing an exception
    benchmark.timeCollectionsStreaming(1);
  }

  @Test
  public void testTimeCollectionsStreamingMultipleReps() throws Exception {
    CollectionsDeserializationBenchmark benchmark = new CollectionsDeserializationBenchmark();
    benchmark.setUp();

    // Should handle multiple repetitions
    benchmark.timeCollectionsStreaming(3);
  }

  @Test
  public void testTimeCollectionsReflectionStreaming() throws Exception {
    CollectionsDeserializationBenchmark benchmark = new CollectionsDeserializationBenchmark();
    benchmark.setUp();

    // Should complete without throwing an exception
    benchmark.timeCollectionsReflectionStreaming(1);
  }

  @Test
  public void testTimeCollectionsReflectionStreamingMultipleReps() throws Exception {
    CollectionsDeserializationBenchmark benchmark = new CollectionsDeserializationBenchmark();
    benchmark.setUp();

    // Should handle multiple repetitions
    benchmark.timeCollectionsReflectionStreaming(3);
  }

  @Test
  public void testSetUpGeneratesCorrectJson() throws Exception {
    CollectionsDeserializationBenchmark benchmark = new CollectionsDeserializationBenchmark();
    benchmark.setUp();

    Field jsonField = CollectionsDeserializationBenchmark.class.getDeclaredField("json");
    jsonField.setAccessible(true);
    String json = (String) jsonField.get(benchmark);

    // Verify JSON contains expected fields from BagOfPrimitives
    assertTrue(json.contains("longValue"));
    assertTrue(json.contains("intValue"));
    assertTrue(json.contains("booleanValue"));
    assertTrue(json.contains("stringValue"));
    // Verify the values used in setUp
    assertTrue(json.contains("10"));  // longValue = 10L
    assertTrue(json.contains("1"));   // intValue = 1
    assertTrue(json.contains("false")); // booleanValue = false
    assertTrue(json.contains("foo")); // stringValue = "foo"
  }
}
