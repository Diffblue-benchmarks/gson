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

/**
 * Factory class for creating FieldAttributes instances for testing.
 */
public class FieldAttributesTestFactory {

  /**
   * A simple test class with various field types to use for reflection.
   */
  public static class TestClass {
    private String stringField;
    public int intField;
    protected Object objectField;

    @SerializedName("annotatedField")
    private String fieldWithAnnotation;
  }

  /**
   * Creates a FieldAttributes object with a valid Field for testing.
   * This factory method provides a non-null Field object to avoid NullPointerException
   * in the FieldAttributes constructor.
   *
   * @return a FieldAttributes instance for testing
   * @throws NoSuchFieldException if the field cannot be found (should not happen)
   */
  @InterestingTestFactory
  public static FieldAttributes createFieldAttributes() throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("stringField");
    return new FieldAttributes(field);
  }

  /**
   * Creates a FieldAttributes object with a public field for testing.
   *
   * @return a FieldAttributes instance with a public field
   * @throws NoSuchFieldException if the field cannot be found (should not happen)
   */
  @InterestingTestFactory
  public static FieldAttributes createFieldAttributesWithPublicField() throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("intField");
    return new FieldAttributes(field);
  }

  /**
   * Creates a FieldAttributes object with a protected field for testing.
   *
   * @return a FieldAttributes instance with a protected field
   * @throws NoSuchFieldException if the field cannot be found (should not happen)
   */
  @InterestingTestFactory
  public static FieldAttributes createFieldAttributesWithProtectedField() throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("objectField");
    return new FieldAttributes(field);
  }

  /**
   * Creates a FieldAttributes object with an annotated field for testing.
   * This is useful for testing methods like getAnnotation().
   *
   * @return a FieldAttributes instance with an annotated field
   * @throws NoSuchFieldException if the field cannot be found (should not happen)
   */
  @InterestingTestFactory
  public static FieldAttributes createFieldAttributesWithAnnotatedField() throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("fieldWithAnnotation");
    return new FieldAttributes(field);
  }
}
