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
import java.util.List;
import org.junit.Test;

public class FieldNamingStrategyTest {

  private static class TestClass {
    public String testField;
  }

  @Test
  public void testTranslateName() throws Exception {
    Field field = TestClass.class.getField("testField");

    FieldNamingStrategy strategy = new FieldNamingStrategy() {
      @Override
      public String translateName(Field f) {
        return f.getName().toUpperCase();
      }
    };

    String result = strategy.translateName(field);
    assertThat(result).isEqualTo("TESTFIELD");
  }

  @Test
  public void testAlternateNamesDefaultImplementation() throws Exception {
    Field field = TestClass.class.getField("testField");

    FieldNamingStrategy strategy = new FieldNamingStrategy() {
      @Override
      public String translateName(Field f) {
        return f.getName();
      }
    };

    List<String> alternates = strategy.alternateNames(field);
    assertThat(alternates).isEmpty();
  }
}
