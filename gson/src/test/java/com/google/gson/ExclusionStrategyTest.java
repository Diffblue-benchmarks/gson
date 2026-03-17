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

public class ExclusionStrategyTest {

  @Test
  public void testShouldSkipFieldReturnsTrue() throws Exception {
    ExclusionStrategy strategy = new ExclusionStrategy() {
      @Override
      public boolean shouldSkipField(FieldAttributes f) {
        return true;
      }

      @Override
      public boolean shouldSkipClass(Class<?> clazz) {
        return false;
      }
    };

    FieldAttributes field = new FieldAttributes(TestClass.class.getDeclaredField("testField"));
    assertThat(strategy.shouldSkipField(field)).isTrue();
  }

  @Test
  public void testShouldSkipFieldReturnsFalse() throws Exception {
    ExclusionStrategy strategy = new ExclusionStrategy() {
      @Override
      public boolean shouldSkipField(FieldAttributes f) {
        return false;
      }

      @Override
      public boolean shouldSkipClass(Class<?> clazz) {
        return false;
      }
    };

    FieldAttributes field = new FieldAttributes(TestClass.class.getDeclaredField("testField"));
    assertThat(strategy.shouldSkipField(field)).isFalse();
  }

  @Test
  public void testShouldSkipFieldBasedOnName() throws Exception {
    ExclusionStrategy strategy = new ExclusionStrategy() {
      @Override
      public boolean shouldSkipField(FieldAttributes f) {
        return f.getName().equals("testField");
      }

      @Override
      public boolean shouldSkipClass(Class<?> clazz) {
        return false;
      }
    };

    FieldAttributes field = new FieldAttributes(TestClass.class.getDeclaredField("testField"));
    assertThat(strategy.shouldSkipField(field)).isTrue();

    FieldAttributes otherField = new FieldAttributes(TestClass.class.getDeclaredField("otherField"));
    assertThat(strategy.shouldSkipField(otherField)).isFalse();
  }

  @Test
  public void testShouldSkipClassReturnsTrue() {
    ExclusionStrategy strategy = new ExclusionStrategy() {
      @Override
      public boolean shouldSkipField(FieldAttributes f) {
        return false;
      }

      @Override
      public boolean shouldSkipClass(Class<?> clazz) {
        return true;
      }
    };

    assertThat(strategy.shouldSkipClass(TestClass.class)).isTrue();
  }

  @Test
  public void testShouldSkipClassReturnsFalse() {
    ExclusionStrategy strategy = new ExclusionStrategy() {
      @Override
      public boolean shouldSkipField(FieldAttributes f) {
        return false;
      }

      @Override
      public boolean shouldSkipClass(Class<?> clazz) {
        return false;
      }
    };

    assertThat(strategy.shouldSkipClass(TestClass.class)).isFalse();
  }

  @Test
  public void testShouldSkipClassBasedOnType() {
    ExclusionStrategy strategy = new ExclusionStrategy() {
      @Override
      public boolean shouldSkipField(FieldAttributes f) {
        return false;
      }

      @Override
      public boolean shouldSkipClass(Class<?> clazz) {
        return clazz.equals(String.class);
      }
    };

    assertThat(strategy.shouldSkipClass(String.class)).isTrue();
    assertThat(strategy.shouldSkipClass(TestClass.class)).isFalse();
  }

  @Test
  public void testShouldSkipClassBasedOnSuperclass() {
    ExclusionStrategy strategy = new ExclusionStrategy() {
      @Override
      public boolean shouldSkipField(FieldAttributes f) {
        return false;
      }

      @Override
      public boolean shouldSkipClass(Class<?> clazz) {
        return clazz.getSuperclass() == Number.class;
      }
    };

    assertThat(strategy.shouldSkipClass(Integer.class)).isTrue();
    assertThat(strategy.shouldSkipClass(String.class)).isFalse();
  }

  private static class TestClass {
    private String testField;
    private int otherField;
  }
}
