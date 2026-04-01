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

package com.google.gson.internal;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public final class UnsafeAllocatorTest {

  static class SimpleBean {
    int value = 42;

    private SimpleBean(int value) {
      this.value = value;
    }
  }

  abstract static class AbstractBean {}

  @Test
  public void testCreateReturnsNonNull() {
    assertThat(UnsafeAllocator.INSTANCE).isNotNull();
  }

  @Test
  public void testNewInstanceCreatesObject() throws Exception {
    SimpleBean bean = UnsafeAllocator.INSTANCE.newInstance(SimpleBean.class);
    assertThat(bean).isNotNull();
    assertThat(bean).isInstanceOf(SimpleBean.class);
  }

  @Test
  public void testNewInstanceDoesNotInvokeConstructor() throws Exception {
    SimpleBean bean = UnsafeAllocator.INSTANCE.newInstance(SimpleBean.class);
    // Unsafe allocation skips the constructor, so value remains the JVM default (0)
    assertThat(bean.value).isEqualTo(0);
  }

  @Test
  public void testAssertInstantiableThrowsForInterface() {
    AssertionError e =
        assertThrows(
            AssertionError.class,
            () -> UnsafeAllocator.INSTANCE.newInstance(Runnable.class));
    assertThat(e).hasMessageThat().contains("Interfaces can't be instantiated");
  }

  @Test
  public void testAssertInstantiableThrowsForAbstractClass() {
    AssertionError e =
        assertThrows(
            AssertionError.class,
            () -> UnsafeAllocator.INSTANCE.newInstance(AbstractBean.class));
    assertThat(e).hasMessageThat().contains("Abstract classes can't be instantiated");
  }
}
