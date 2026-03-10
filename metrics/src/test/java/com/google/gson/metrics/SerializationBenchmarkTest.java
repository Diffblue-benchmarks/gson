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

import java.lang.reflect.Field;
import org.junit.Test;

/**
 * Tests for {@link SerializationBenchmark}.
 *
 * <p>This test exercises the benchmark methods to ensure code coverage. The main() method is not
 * tested since it invokes the full Caliper benchmark framework.
 */
public class SerializationBenchmarkTest {

  /** Sets the pretty field on the benchmark instance using reflection. */
  private static void setPrettyField(SerializationBenchmark benchmark, boolean value)
      throws Exception {
    Field prettyField = SerializationBenchmark.class.getDeclaredField("pretty");
    prettyField.setAccessible(true);
    prettyField.setBoolean(benchmark, value);
  }

  @Test
  public void testSetUpWithPrettyFalse() throws Exception {
    SerializationBenchmark benchmark = new SerializationBenchmark();
    setPrettyField(benchmark, false);
    benchmark.setUp();
  }

  @Test
  public void testSetUpWithPrettyTrue() throws Exception {
    SerializationBenchmark benchmark = new SerializationBenchmark();
    setPrettyField(benchmark, true);
    benchmark.setUp();
  }

  @Test
  public void testTimeObjectSerializationWithPrettyFalse() throws Exception {
    SerializationBenchmark benchmark = new SerializationBenchmark();
    setPrettyField(benchmark, false);
    benchmark.setUp();
    benchmark.timeObjectSerialization(10);
  }

  @Test
  public void testTimeObjectSerializationWithPrettyTrue() throws Exception {
    SerializationBenchmark benchmark = new SerializationBenchmark();
    setPrettyField(benchmark, true);
    benchmark.setUp();
    benchmark.timeObjectSerialization(10);
  }

  @Test
  public void testTimeObjectSerializationWithZeroReps() throws Exception {
    SerializationBenchmark benchmark = new SerializationBenchmark();
    setPrettyField(benchmark, false);
    benchmark.setUp();
    benchmark.timeObjectSerialization(0);
  }
}
