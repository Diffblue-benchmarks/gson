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
package com.google.gson.typeadapters;

import com.diffblue.cover.annotations.InterestingTestFactory;

/** Test factory for creating RuntimeTypeAdapterFactory instances for Cover test generation. */
public class RuntimeTypeAdapterFactoryTestFactory {

  /** Simple base class for testing RuntimeTypeAdapterFactory. */
  public static class TestBase {
    private String type;

    public TestBase() {}

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }
  }

  /**
   * Factory method for RuntimeTypeAdapterFactory.of(Class) method. Creates a factory with a valid
   * base type.
   */
  @InterestingTestFactory
  public static RuntimeTypeAdapterFactory<TestBase> createRuntimeTypeAdapterFactoryWithClass() {
    return RuntimeTypeAdapterFactory.of(TestBase.class);
  }

  /**
   * Factory method for RuntimeTypeAdapterFactory.of(Class, String) method. Creates a factory with a
   * valid base type and type field name.
   */
  @InterestingTestFactory
  public static RuntimeTypeAdapterFactory<TestBase>
      createRuntimeTypeAdapterFactoryWithClassAndString() {
    return RuntimeTypeAdapterFactory.of(TestBase.class, "type");
  }

  /**
   * Factory method for RuntimeTypeAdapterFactory.of(Class, String, boolean) method. Creates a
   * factory with a valid base type, type field name, and maintainType flag.
   */
  @InterestingTestFactory
  public static RuntimeTypeAdapterFactory<TestBase>
      createRuntimeTypeAdapterFactoryWithClassStringBoolean() {
    return RuntimeTypeAdapterFactory.of(TestBase.class, "type", true);
  }

  /** Factory method providing an alternative configuration. */
  @InterestingTestFactory
  public static RuntimeTypeAdapterFactory<TestBase> createRuntimeTypeAdapterFactoryAlternative() {
    return RuntimeTypeAdapterFactory.of(TestBase.class, "typeField", false);
  }
}
