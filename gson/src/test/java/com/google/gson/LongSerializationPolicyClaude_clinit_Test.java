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

import org.junit.Test;

/**
 * Unit tests for {@link LongSerializationPolicy} static initialization (clinit).
 * These tests ensure the enum class and its constants are properly initialized.
 *
 * @author Claude
 */
public class LongSerializationPolicyClaude_clinit_Test {

  @Test
  public void testEnumClassLoadsSuccessfully() {
    // Accessing the class triggers static initialization (clinit) - covers line 26
    Class<LongSerializationPolicy> clazz = LongSerializationPolicy.class;
    assertThat(clazz).isNotNull();
    assertThat(clazz.isEnum()).isTrue();
  }

  @Test
  public void testDefaultConstantInitialization() {
    // Accessing DEFAULT constant ensures it is initialized - covers line 34
    LongSerializationPolicy policy = LongSerializationPolicy.DEFAULT;
    assertThat(policy).isNotNull();
    assertThat(policy.name()).isEqualTo("DEFAULT");
  }

  @Test
  public void testStringConstantInitialization() {
    // Accessing STRING constant ensures it is initialized - covers line 50
    LongSerializationPolicy policy = LongSerializationPolicy.STRING;
    assertThat(policy).isNotNull();
    assertThat(policy.name()).isEqualTo("STRING");
  }

  @Test
  public void testValuesTriggersClassInitialization() {
    // Calling values() triggers class initialization and returns all constants
    LongSerializationPolicy[] values = LongSerializationPolicy.values();
    assertThat(values).hasLength(2);
    assertThat(values[0]).isEqualTo(LongSerializationPolicy.DEFAULT);
    assertThat(values[1]).isEqualTo(LongSerializationPolicy.STRING);
  }

  @Test
  public void testBothConstantsAreDistinct() {
    // Ensure both enum constants are properly initialized as distinct instances
    assertThat(LongSerializationPolicy.DEFAULT).isNotSameInstanceAs(LongSerializationPolicy.STRING);
  }

}
