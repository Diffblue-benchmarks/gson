/*
 * Copyright (C) 2018 Google Inc.
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
import java.util.ArrayList;

/** Factory class for creating NonNullElementWrapperList instances for Diffblue Cover testing. */
public class NonNullElementWrapperListDiffblueTestFactory {

  /**
   * Creates a NonNullElementWrapperList with an empty ArrayList delegate. This factory method
   * ensures proper instantiation by providing a valid non-null ArrayList parameter.
   *
   * @return a NonNullElementWrapperList instance with an empty delegate for testing
   */
  @InterestingTestFactory
  public static NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperList() {
    return new NonNullElementWrapperList<>(new ArrayList<>());
  }

  /**
   * Creates a NonNullElementWrapperList with a single element. This factory provides instances that
   * can be used for testing methods like equals and hashCode with non-empty lists.
   *
   * @return a NonNullElementWrapperList instance with one element for testing
   */
  @InterestingTestFactory
  public static NonNullElementWrapperList<Object> createNonNullElementWrapperListWithOneElement() {
    ArrayList<Object> delegate = new ArrayList<>();
    delegate.add("element1");
    return new NonNullElementWrapperList<>(delegate);
  }

  /**
   * Creates a NonNullElementWrapperList with multiple elements. This factory provides instances for
   * comprehensive testing of collection methods.
   *
   * @return a NonNullElementWrapperList instance with multiple elements for testing
   */
  @InterestingTestFactory
  public static NonNullElementWrapperList<Object>
      createNonNullElementWrapperListWithMultipleElements() {
    ArrayList<Object> delegate = new ArrayList<>();
    delegate.add("element1");
    delegate.add("element2");
    delegate.add("element3");
    return new NonNullElementWrapperList<>(delegate);
  }

  /**
   * Creates a NonNullElementWrapperList with raw type. This is the simplest factory for Diffblue
   * Cover to use when constructing instances.
   *
   * @return a NonNullElementWrapperList instance with raw type for testing
   */
  @SuppressWarnings({"rawtypes", "unchecked"})
  @InterestingTestFactory
  public static NonNullElementWrapperList createNonNullElementWrapperList() {
    return new NonNullElementWrapperList(new ArrayList());
  }
}
