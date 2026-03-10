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

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/** Tests for {@link BagOfPrimitivesDeserializationBenchmark}. */
public class BagOfPrimitivesDeserializationBenchmarkTest {

  private BagOfPrimitivesDeserializationBenchmark benchmark;

  @Before
  public void setUp() throws Exception {
    benchmark = new BagOfPrimitivesDeserializationBenchmark();
    benchmark.setUp();
  }

  @Test
  public void testSetUp() throws Exception {
    BagOfPrimitivesDeserializationBenchmark newBenchmark =
        new BagOfPrimitivesDeserializationBenchmark();
    newBenchmark.setUp();
    // setUp() should complete without exception
    // The fields are initialized internally and used by the benchmark methods
  }

  @Test
  public void testTimeBagOfPrimitivesDefault_withSingleRep() {
    benchmark.timeBagOfPrimitivesDefault(1);
  }

  @Test
  public void testTimeBagOfPrimitivesDefault_withMultipleReps() {
    benchmark.timeBagOfPrimitivesDefault(5);
  }

  @Test
  public void testTimeBagOfPrimitivesDefault_withZeroReps() {
    benchmark.timeBagOfPrimitivesDefault(0);
  }

  @Test
  public void testTimeBagOfPrimitivesStreaming_withSingleRep() throws Exception {
    benchmark.timeBagOfPrimitivesStreaming(1);
  }

  @Test
  public void testTimeBagOfPrimitivesStreaming_withMultipleReps() throws Exception {
    benchmark.timeBagOfPrimitivesStreaming(5);
  }

  @Test
  public void testTimeBagOfPrimitivesStreaming_withZeroReps() throws Exception {
    benchmark.timeBagOfPrimitivesStreaming(0);
  }

  @Test
  public void testTimeBagOfPrimitivesReflectionStreaming_withSingleRep() throws Exception {
    benchmark.timeBagOfPrimitivesReflectionStreaming(1);
  }

  @Test
  public void testTimeBagOfPrimitivesReflectionStreaming_withMultipleReps() throws Exception {
    benchmark.timeBagOfPrimitivesReflectionStreaming(5);
  }

  @Test
  public void testTimeBagOfPrimitivesReflectionStreaming_withZeroReps() throws Exception {
    benchmark.timeBagOfPrimitivesReflectionStreaming(0);
  }
}
