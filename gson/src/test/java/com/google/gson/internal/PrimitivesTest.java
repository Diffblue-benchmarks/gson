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

import java.lang.reflect.Type;
import org.junit.Test;

public final class PrimitivesTest {

  @Test
  public void testIsPrimitiveWithPrimitiveType() {
    assertThat(Primitives.isPrimitive(int.class)).isTrue();
    assertThat(Primitives.isPrimitive(boolean.class)).isTrue();
    assertThat(Primitives.isPrimitive(byte.class)).isTrue();
    assertThat(Primitives.isPrimitive(char.class)).isTrue();
    assertThat(Primitives.isPrimitive(double.class)).isTrue();
    assertThat(Primitives.isPrimitive(float.class)).isTrue();
    assertThat(Primitives.isPrimitive(long.class)).isTrue();
    assertThat(Primitives.isPrimitive(short.class)).isTrue();
    assertThat(Primitives.isPrimitive(void.class)).isTrue();
  }

  @Test
  public void testIsPrimitiveWithNonPrimitiveType() {
    assertThat(Primitives.isPrimitive(Integer.class)).isFalse();
    assertThat(Primitives.isPrimitive(String.class)).isFalse();
  }

  @Test
  public void testIsPrimitiveWithNonClassType() {
    Type nonClassType = new java.lang.reflect.ParameterizedType() {
      @Override public Type[] getActualTypeArguments() { return new Type[0]; }
      @Override public Type getRawType() { return Object.class; }
      @Override public Type getOwnerType() { return null; }
    };
    assertThat(Primitives.isPrimitive(nonClassType)).isFalse();
  }

  @Test
  public void testIsWrapperTypeWithWrapperTypes() {
    assertThat(Primitives.isWrapperType(Integer.class)).isTrue();
    assertThat(Primitives.isWrapperType(Float.class)).isTrue();
    assertThat(Primitives.isWrapperType(Byte.class)).isTrue();
    assertThat(Primitives.isWrapperType(Double.class)).isTrue();
    assertThat(Primitives.isWrapperType(Long.class)).isTrue();
    assertThat(Primitives.isWrapperType(Character.class)).isTrue();
    assertThat(Primitives.isWrapperType(Boolean.class)).isTrue();
    assertThat(Primitives.isWrapperType(Short.class)).isTrue();
    assertThat(Primitives.isWrapperType(Void.class)).isTrue();
  }

  @Test
  public void testIsWrapperTypeWithNonWrapperTypes() {
    assertThat(Primitives.isWrapperType(String.class)).isFalse();
    assertThat(Primitives.isWrapperType(int.class)).isFalse();
  }

  @Test
  public void testWrapPrimitiveTypes() {
    assertThat(Primitives.wrap(int.class)).isEqualTo(Integer.class);
    assertThat(Primitives.wrap(float.class)).isEqualTo(Float.class);
    assertThat(Primitives.wrap(byte.class)).isEqualTo(Byte.class);
    assertThat(Primitives.wrap(double.class)).isEqualTo(Double.class);
    assertThat(Primitives.wrap(long.class)).isEqualTo(Long.class);
    assertThat(Primitives.wrap(char.class)).isEqualTo(Character.class);
    assertThat(Primitives.wrap(boolean.class)).isEqualTo(Boolean.class);
    assertThat(Primitives.wrap(short.class)).isEqualTo(Short.class);
    assertThat(Primitives.wrap(void.class)).isEqualTo(Void.class);
  }

  @Test
  public void testWrapNonPrimitiveTypeIsIdempotent() {
    assertThat(Primitives.wrap(String.class)).isEqualTo(String.class);
    assertThat(Primitives.wrap(Integer.class)).isEqualTo(Integer.class);
  }

  @Test
  public void testUnwrapWrapperTypes() {
    assertThat(Primitives.unwrap(Integer.class)).isEqualTo(int.class);
    assertThat(Primitives.unwrap(Float.class)).isEqualTo(float.class);
    assertThat(Primitives.unwrap(Byte.class)).isEqualTo(byte.class);
    assertThat(Primitives.unwrap(Double.class)).isEqualTo(double.class);
    assertThat(Primitives.unwrap(Long.class)).isEqualTo(long.class);
    assertThat(Primitives.unwrap(Character.class)).isEqualTo(char.class);
    assertThat(Primitives.unwrap(Boolean.class)).isEqualTo(boolean.class);
    assertThat(Primitives.unwrap(Short.class)).isEqualTo(short.class);
    assertThat(Primitives.unwrap(Void.class)).isEqualTo(void.class);
  }

  @Test
  public void testUnwrapNonWrapperTypeIsIdempotent() {
    assertThat(Primitives.unwrap(String.class)).isEqualTo(String.class);
    assertThat(Primitives.unwrap(int.class)).isEqualTo(int.class);
  }
}
