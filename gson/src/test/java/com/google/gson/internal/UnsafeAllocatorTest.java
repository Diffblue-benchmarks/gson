/*
 * Copyright (C) 2024 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.gson.internal;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

public class UnsafeAllocatorTest {

  private static class ConcreteClass {
    private int value = 42;
  }

  private static class ClassWithNoDefaultConstructor {
    private final String name;

    public ClassWithNoDefaultConstructor(String name) {
      this.name = name;
    }
  }

  private interface TestInterface {
  }

  private static abstract class AbstractTestClass {
  }

  @Test
  public void testNewInstanceWithConcreteClass() throws Exception {
    ConcreteClass instance = UnsafeAllocator.INSTANCE.newInstance(ConcreteClass.class);

    assertThat(instance).isNotNull();
    // UnsafeAllocator creates instances without calling constructors, so field initializers don't run
    assertThat(instance.value).isEqualTo(0);
  }

  @Test
  public void testNewInstanceWithClassWithNoDefaultConstructor() throws Exception {
    ClassWithNoDefaultConstructor instance = UnsafeAllocator.INSTANCE.newInstance(ClassWithNoDefaultConstructor.class);

    assertThat(instance).isNotNull();
    assertThat(instance.name).isNull();
  }

  @Test
  public void testNewInstanceWithInterface() {
    try {
      UnsafeAllocator.INSTANCE.newInstance(TestInterface.class);
      fail("Expected AssertionError");
    } catch (AssertionError e) {
      assertThat(e.getMessage()).contains("UnsafeAllocator is used for non-instantiable type");
      assertThat(e.getMessage()).contains("Interfaces can't be instantiated");
    } catch (Exception e) {
      fail("Expected AssertionError but got: " + e);
    }
  }

  @Test
  public void testNewInstanceWithAbstractClass() {
    try {
      UnsafeAllocator.INSTANCE.newInstance(AbstractTestClass.class);
      fail("Expected AssertionError");
    } catch (AssertionError e) {
      assertThat(e.getMessage()).contains("UnsafeAllocator is used for non-instantiable type");
      assertThat(e.getMessage()).contains("Abstract classes can't be instantiated");
    } catch (Exception e) {
      fail("Expected AssertionError but got: " + e);
    }
  }

  @Test
  public void testInstanceIsNotNull() {
    assertThat(UnsafeAllocator.INSTANCE).isNotNull();
  }
}
