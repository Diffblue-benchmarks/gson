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

import static com.google.common.truth.Truth.assertThat;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

/**
 * Tests for {@link FieldNamingStrategy}.
 *
 * @author Claude
 */
public class FieldNamingStrategyClaudeTest {

  /** Test class with various field names for testing naming strategies. */
  @SuppressWarnings("unused")
  private static class TestClass {
    String simpleField;
    String anotherField;
  }

  // ==========================================================================
  // alternateNames default method tests
  // ==========================================================================

  @Test
  public void testAlternateNames_defaultImplementation_returnsEmptyList()
      throws NoSuchFieldException {
    // Create a simple implementation that only provides translateName
    FieldNamingStrategy strategy =
        new FieldNamingStrategy() {
          @Override
          public String translateName(Field f) {
            return f.getName();
          }
        };

    Field field = TestClass.class.getDeclaredField("simpleField");
    List<String> alternates = strategy.alternateNames(field);

    assertThat(alternates).isNotNull();
    assertThat(alternates).isEmpty();
  }

  @Test
  public void testAlternateNames_defaultImplementation_returnsSameEmptyListForDifferentFields()
      throws NoSuchFieldException {
    FieldNamingStrategy strategy =
        new FieldNamingStrategy() {
          @Override
          public String translateName(Field f) {
            return f.getName();
          }
        };

    Field field1 = TestClass.class.getDeclaredField("simpleField");
    Field field2 = TestClass.class.getDeclaredField("anotherField");

    List<String> alternates1 = strategy.alternateNames(field1);
    List<String> alternates2 = strategy.alternateNames(field2);

    assertThat(alternates1).isEmpty();
    assertThat(alternates2).isEmpty();
  }

  @Test
  public void testAlternateNames_customOverride_returnsCustomList() throws NoSuchFieldException {
    // Create an implementation that overrides alternateNames
    FieldNamingStrategy strategy =
        new FieldNamingStrategy() {
          @Override
          public String translateName(Field f) {
            return f.getName();
          }

          @Override
          public List<String> alternateNames(Field f) {
            return Arrays.asList("alt1", "alt2");
          }
        };

    Field field = TestClass.class.getDeclaredField("simpleField");
    List<String> alternates = strategy.alternateNames(field);

    assertThat(alternates).containsExactly("alt1", "alt2");
  }

  @Test
  public void testAlternateNames_customOverrideBasedOnFieldName_returnsFieldSpecificList()
      throws NoSuchFieldException {
    // Create an implementation that returns alternates based on the field name
    FieldNamingStrategy strategy =
        new FieldNamingStrategy() {
          @Override
          public String translateName(Field f) {
            return f.getName();
          }

          @Override
          public List<String> alternateNames(Field f) {
            if ("simpleField".equals(f.getName())) {
              return Arrays.asList("simple", "basicField");
            }
            return Arrays.asList("other");
          }
        };

    Field simpleField = TestClass.class.getDeclaredField("simpleField");
    Field anotherField = TestClass.class.getDeclaredField("anotherField");

    List<String> simpleAlternates = strategy.alternateNames(simpleField);
    List<String> anotherAlternates = strategy.alternateNames(anotherField);

    assertThat(simpleAlternates).containsExactly("simple", "basicField");
    assertThat(anotherAlternates).containsExactly("other");
  }

  // ==========================================================================
  // FieldNamingPolicy implements FieldNamingStrategy - alternateNames tests
  // ==========================================================================

  @Test
  public void testFieldNamingPolicy_implementsFieldNamingStrategy() {
    // Verify that FieldNamingPolicy is a FieldNamingStrategy
    assertThat(FieldNamingPolicy.IDENTITY).isInstanceOf(FieldNamingStrategy.class);
  }

  @Test
  public void testFieldNamingPolicy_alternateNames_returnsEmptyList() throws NoSuchFieldException {
    // FieldNamingPolicy doesn't override alternateNames, so it uses the default
    Field field = TestClass.class.getDeclaredField("simpleField");

    for (FieldNamingPolicy policy : FieldNamingPolicy.values()) {
      List<String> alternates = policy.alternateNames(field);
      assertThat(alternates).isEmpty();
    }
  }

  // ==========================================================================
  // Interface contract tests
  // ==========================================================================

  @Test
  public void testTranslateName_mustBeImplemented() throws NoSuchFieldException {
    FieldNamingStrategy strategy =
        new FieldNamingStrategy() {
          @Override
          public String translateName(Field f) {
            return "customName_" + f.getName();
          }
        };

    Field field = TestClass.class.getDeclaredField("simpleField");
    assertThat(strategy.translateName(field)).isEqualTo("customName_simpleField");
  }

  @Test
  public void testAlternateNames_canBeOverridden() throws NoSuchFieldException {
    FieldNamingStrategy strategy =
        new FieldNamingStrategy() {
          @Override
          public String translateName(Field f) {
            return f.getName();
          }

          @Override
          public List<String> alternateNames(Field f) {
            return Arrays.asList("override1", "override2", "override3");
          }
        };

    Field field = TestClass.class.getDeclaredField("simpleField");
    List<String> alternates = strategy.alternateNames(field);

    assertThat(alternates).hasSize(3);
    assertThat(alternates).containsExactly("override1", "override2", "override3");
  }
}
