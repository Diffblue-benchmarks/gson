/*
 * Copyright (C) 2008 Google Inc.
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

package com.google.gson.internal;

import com.diffblue.cover.annotations.InterestingTestFactory;
import java.lang.reflect.Field;

/** Factory class for creating Field instances for Diffblue Cover testing of Excluder. */
public class ExcluderDiffblueTestFactory {

  /**
   * Creates a valid Field instance to avoid NullPointerException in Excluder.excludeField. This
   * factory method provides a non-null Field object that can be used to test the excludeField
   * method.
   *
   * @return a Field instance for testing
   * @throws NoSuchFieldException if the field cannot be found
   */
  @InterestingTestFactory
  public static Field createField() throws NoSuchFieldException {
    // Use a simple test class with a String field
    return TestClass.class.getDeclaredField("testField");
  }

  /** Simple test class used to obtain a valid Field instance. */
  private static class TestClass {
    @SuppressWarnings("unused")
    private String testField;
  }
}
