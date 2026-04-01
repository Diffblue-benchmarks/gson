/*
 * Copyright (C) 2023 Google Inc.
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

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.google.gson.internal.reflect.ReflectionHelper;
import java.lang.reflect.Field;
import org.junit.Test;

public final class ReflectionHelperTest {

  private static class SampleClass {
    public String name;
  }

  @Test
  public void testIsRecordReturnsFalseForNonRecordClass() {
    assertThat(ReflectionHelper.isRecord(SampleClass.class)).isFalse();
  }

  @Test
  public void testIsRecordReturnsFalseForString() {
    assertThat(ReflectionHelper.isRecord(String.class)).isFalse();
  }

  @Test
  public void testGetRecordComponentNamesThrowsForNonRecordOnJava11() {
    // On Java 11 records are not supported; expect UnsupportedOperationException
    boolean recordsSupported = ReflectionHelper.isRecord(SampleClass.class);
    if (!recordsSupported) {
      assertThrows(
          UnsupportedOperationException.class,
          () -> ReflectionHelper.getRecordComponentNames(SampleClass.class));
    }
  }

  @Test
  public void testGetCanonicalRecordConstructorThrowsForNonRecordOnJava11() {
    boolean recordsSupported = ReflectionHelper.isRecord(SampleClass.class);
    if (!recordsSupported) {
      assertThrows(
          UnsupportedOperationException.class,
          () -> ReflectionHelper.getCanonicalRecordConstructor(SampleClass.class));
    }
  }

  @Test
  public void testGetAccessorThrowsForNonRecordOnJava11() throws NoSuchFieldException {
    Field field = SampleClass.class.getField("name");
    boolean recordsSupported = ReflectionHelper.isRecord(SampleClass.class);
    if (!recordsSupported) {
      assertThrows(
          UnsupportedOperationException.class,
          () -> ReflectionHelper.getAccessor(SampleClass.class, field));
    }
  }
}
