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

import java.lang.reflect.Field;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for {@code ReflectionHelper.RecordNotSupportedHelper}, which is the helper used on JVMs
 * that do not support Java records (i.e., JDK &lt; 16).
 */
public final class ReflectionHelperRecordNotSupportedTest {

  private static boolean recordsNotSupported;

  @BeforeClass
  public static void checkRecordsSupport() {
    try {
      Class.class.getMethod("isRecord");
      recordsNotSupported = false;
    } catch (NoSuchMethodException e) {
      recordsNotSupported = true;
    }
  }

  @Test
  public void testIsRecordReturnsFalse() {
    Assume.assumeTrue("Skipping: records are supported on this JVM", recordsNotSupported);

    assertThat(ReflectionHelper.isRecord(TestModel.class)).isFalse();
  }

  @Test
  public void testGetRecordComponentNamesThrowsUnsupportedOperation() {
    Assume.assumeTrue("Skipping: records are supported on this JVM", recordsNotSupported);

    UnsupportedOperationException exception =
        assertThrows(
            UnsupportedOperationException.class,
            () -> ReflectionHelper.getRecordComponentNames(TestModel.class));
    assertThat(exception)
        .hasMessageThat()
        .contains("Records are not supported on this JVM");
  }

  @Test
  public void testGetCanonicalRecordConstructorThrowsUnsupportedOperation() {
    Assume.assumeTrue("Skipping: records are supported on this JVM", recordsNotSupported);

    UnsupportedOperationException exception =
        assertThrows(
            UnsupportedOperationException.class,
            () -> ReflectionHelper.getCanonicalRecordConstructor(TestModel.class));
    assertThat(exception)
        .hasMessageThat()
        .contains("Records are not supported on this JVM");
  }

  @Test
  public void testGetAccessorThrowsUnsupportedOperation() throws NoSuchFieldException {
    Assume.assumeTrue("Skipping: records are supported on this JVM", recordsNotSupported);

    Field field = TestModel.class.getDeclaredField("name");
    UnsupportedOperationException exception =
        assertThrows(
            UnsupportedOperationException.class,
            () -> ReflectionHelper.getAccessor(TestModel.class, field));
    assertThat(exception)
        .hasMessageThat()
        .contains("Records are not supported on this JVM");
  }

  private static class TestModel {
    String name;
  }
}
