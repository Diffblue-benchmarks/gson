/*
 * Copyright (C) 2009 Google Inc.
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

package com.google.gson;

import com.diffblue.cover.annotations.InterestingTestFactory;
import com.google.gson.annotations.SerializedName;
import java.lang.reflect.Field;

/** Factory class for creating FieldAttributes instances for Diffblue Cover testing. */
public class FieldAttributesDiffblueTestFactory {

  /**
   * Creates a valid Field instance for testing. This factory method provides a non-null Field
   * parameter to avoid NullPointerException in the FieldAttributes constructor.
   *
   * @return a Field instance for testing
   * @throws NoSuchFieldException if the field cannot be found
   */
  @InterestingTestFactory
  public static Field createField() throws NoSuchFieldException {
    return TestClass.class.getDeclaredField("testField");
  }

  /**
   * Creates a valid FieldAttributes instance with a non-null Field. This factory method avoids the
   * NullPointerException that would occur if a null Field is passed to the FieldAttributes
   * constructor.
   *
   * @return a FieldAttributes instance for testing
   * @throws NoSuchFieldException if the field cannot be found
   */
  @InterestingTestFactory
  public static FieldAttributes createFieldAttributes() throws NoSuchFieldException {
    // Use a simple test class with a String field
    Field field = TestClass.class.getDeclaredField("testField");
    return new FieldAttributes(field);
  }

  /**
   * Creates a valid FieldAttributes instance with an annotated field. This factory method provides
   * a FieldAttributes instance with a field that has a SerializedName annotation, useful for
   * testing the getAnnotation method.
   *
   * @return a FieldAttributes instance with an annotated field for testing
   * @throws NoSuchFieldException if the field cannot be found
   */
  @InterestingTestFactory
  public static FieldAttributes createFieldAttributesWithAnnotation() throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("annotatedField");
    return new FieldAttributes(field);
  }

  /** Simple test class used to obtain a valid Field instance. */
  private static class TestClass {
    @SuppressWarnings("unused")
    private String testField;

    @SuppressWarnings("unused")
    @SerializedName("serialized_name")
    private String annotatedField;
  }
}
