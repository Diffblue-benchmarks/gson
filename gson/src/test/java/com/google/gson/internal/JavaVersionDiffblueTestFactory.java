/*
 * Copyright (C) 2017 The Gson authors
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

/**
 * Factory class for creating JavaVersion instances for Diffblue Cover testing.
 *
 * <p>Note: JavaVersion is a utility class with only static methods and a private constructor. This
 * factory returns null as no instance is required for testing static methods.
 */
public class JavaVersionDiffblueTestFactory {

  /**
   * Returns null since JavaVersion is a utility class with only static methods. No instance is
   * required for testing.
   *
   * @return null as JavaVersion should not be instantiated
   */
  @InterestingTestFactory
  public static JavaVersion createJavaVersion() {
    // JavaVersion is a utility class with a private constructor and only static methods.
    // No instance is needed for testing static methods.
    return null;
  }
}
