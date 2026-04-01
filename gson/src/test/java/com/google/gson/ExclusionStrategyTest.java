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

package com.google.gson;

import static com.google.common.truth.Truth.assertThat;

import java.lang.reflect.Field;
import org.junit.Test;

public final class ExclusionStrategyTest {

  private static class SampleModel {
    String name;
  }

  @Test
  public void testShouldSkipFieldReturnsFalse() throws NoSuchFieldException {
    ExclusionStrategy strategy =
        new ExclusionStrategy() {
          @Override
          public boolean shouldSkipField(FieldAttributes f) {
            return false;
          }

          @Override
          public boolean shouldSkipClass(Class<?> clazz) {
            return false;
          }
        };

    Field field = SampleModel.class.getDeclaredField("name");
    FieldAttributes fieldAttributes = new FieldAttributes(field);

    assertThat(strategy.shouldSkipField(fieldAttributes)).isFalse();
  }

  @Test
  public void testShouldSkipFieldReturnsTrue() throws NoSuchFieldException {
    ExclusionStrategy strategy =
        new ExclusionStrategy() {
          @Override
          public boolean shouldSkipField(FieldAttributes f) {
            return true;
          }

          @Override
          public boolean shouldSkipClass(Class<?> clazz) {
            return false;
          }
        };

    Field field = SampleModel.class.getDeclaredField("name");
    FieldAttributes fieldAttributes = new FieldAttributes(field);

    assertThat(strategy.shouldSkipField(fieldAttributes)).isTrue();
  }

  @Test
  public void testShouldSkipClassReturnsFalse() {
    ExclusionStrategy strategy =
        new ExclusionStrategy() {
          @Override
          public boolean shouldSkipField(FieldAttributes f) {
            return false;
          }

          @Override
          public boolean shouldSkipClass(Class<?> clazz) {
            return false;
          }
        };

    assertThat(strategy.shouldSkipClass(String.class)).isFalse();
  }

  @Test
  public void testShouldSkipClassReturnsTrue() {
    ExclusionStrategy strategy =
        new ExclusionStrategy() {
          @Override
          public boolean shouldSkipField(FieldAttributes f) {
            return false;
          }

          @Override
          public boolean shouldSkipClass(Class<?> clazz) {
            return clazz == String.class;
          }
        };

    assertThat(strategy.shouldSkipClass(String.class)).isTrue();
    assertThat(strategy.shouldSkipClass(Integer.class)).isFalse();
  }

  @Test
  public void testExclusionStrategyWithGsonBuilder() throws NoSuchFieldException {
    ExclusionStrategy strategy =
        new ExclusionStrategy() {
          @Override
          public boolean shouldSkipField(FieldAttributes f) {
            return f.getName().equals("name");
          }

          @Override
          public boolean shouldSkipClass(Class<?> clazz) {
            return clazz == Integer.class;
          }
        };

    Field field = SampleModel.class.getDeclaredField("name");
    FieldAttributes fieldAttributes = new FieldAttributes(field);

    assertThat(strategy.shouldSkipField(fieldAttributes)).isTrue();
    assertThat(strategy.shouldSkipClass(Integer.class)).isTrue();
    assertThat(strategy.shouldSkipClass(String.class)).isFalse();
  }
}
