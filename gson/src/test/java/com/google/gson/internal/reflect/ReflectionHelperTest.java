/*
 * Copyright (C) 2024 Google Inc.
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

package com.google.gson.internal.reflect;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;
import static org.junit.Assume.assumeFalse;

import java.lang.reflect.Field;
import org.junit.Before;
import org.junit.Test;

public final class ReflectionHelperTest {

  @Before
  public void setUp() {
    // These tests are for JVMs where records are NOT supported (Java < 14)
    // Skip if records are supported (the class would be a record)
    assumeFalse(
        "Skipping test because records are supported on this JVM",
        ReflectionHelper.isRecord(String.class) || isRecordsSupportedOnJvm());
  }

  private static boolean isRecordsSupportedOnJvm() {
    try {
      Class.class.getMethod("isRecord");
      return true;
    } catch (NoSuchMethodException e) {
      return false;
    }
  }

  @Test
  public void testGetRecordComponentNamesThrowsOnUnsupportedJvm() {
    UnsupportedOperationException exception =
        assertThrows(
            UnsupportedOperationException.class,
            () -> ReflectionHelper.getRecordComponentNames(String.class));
    assertThat(exception)
        .hasMessageThat()
        .contains("Records are not supported on this JVM");
  }

  @Test
  public void testGetCanonicalRecordConstructorThrowsOnUnsupportedJvm() {
    UnsupportedOperationException exception =
        assertThrows(
            UnsupportedOperationException.class,
            () -> ReflectionHelper.getCanonicalRecordConstructor(String.class));
    assertThat(exception)
        .hasMessageThat()
        .contains("Records are not supported on this JVM");
  }

  @Test
  public void testGetAccessorThrowsOnUnsupportedJvm() throws NoSuchFieldException {
    Field field = TestClass.class.getDeclaredField("value");
    UnsupportedOperationException exception =
        assertThrows(
            UnsupportedOperationException.class,
            () -> ReflectionHelper.getAccessor(TestClass.class, field));
    assertThat(exception)
        .hasMessageThat()
        .contains("Records are not supported on this JVM");
  }

  @Test
  public void testIsRecordReturnsFalseOnUnsupportedJvm() {
    assertThat(ReflectionHelper.isRecord(String.class)).isFalse();
    assertThat(ReflectionHelper.isRecord(TestClass.class)).isFalse();
  }

  private static class TestClass {
    @SuppressWarnings("unused")
    private String value;
  }
}
