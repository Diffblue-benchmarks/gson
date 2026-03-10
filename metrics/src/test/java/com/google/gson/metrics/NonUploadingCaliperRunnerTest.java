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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;

import org.junit.Test;

/**
 * Unit tests for {@link NonUploadingCaliperRunner}.
 */
public class NonUploadingCaliperRunnerTest {

  /**
   * Helper method to invoke the private concat method via reflection.
   */
  private String[] invokeConcat(String first, String... others) throws Exception {
    Method concatMethod = NonUploadingCaliperRunner.class.getDeclaredMethod(
        "concat", String.class, String[].class);
    concatMethod.setAccessible(true);
    return (String[]) concatMethod.invoke(null, first, others);
  }

  @Test
  public void testConcatWithNoOtherArgs() throws Exception {
    String[] result = invokeConcat("first");

    assertEquals(1, result.length);
    assertEquals("first", result[0]);
  }

  @Test
  public void testConcatWithSingleOtherArg() throws Exception {
    String[] result = invokeConcat("first", "second");

    assertEquals(2, result.length);
    assertEquals("first", result[0]);
    assertEquals("second", result[1]);
  }

  @Test
  public void testConcatWithMultipleOtherArgs() throws Exception {
    String[] result = invokeConcat("first", "second", "third", "fourth");

    assertArrayEquals(new String[] {"first", "second", "third", "fourth"}, result);
  }

  @Test
  public void testConcatWithEmptyFirst() throws Exception {
    String[] result = invokeConcat("", "second");

    assertEquals(2, result.length);
    assertEquals("", result[0]);
    assertEquals("second", result[1]);
  }

  @Test
  public void testConcatWithEmptyOtherArgs() throws Exception {
    String[] result = invokeConcat("first", "", "");

    assertArrayEquals(new String[] {"first", "", ""}, result);
  }
}
