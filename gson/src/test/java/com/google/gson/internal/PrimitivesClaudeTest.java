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

package com.google.gson.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import org.junit.Test;

/** Tests for {@link Primitives}. */
public class PrimitivesClaudeTest {

  // ==========================================================================
  // isPrimitive tests
  // ==========================================================================

  @Test
  public void isPrimitive_withIntClass_returnsTrue() {
    assertTrue(Primitives.isPrimitive(int.class));
  }

  @Test
  public void isPrimitive_withFloatClass_returnsTrue() {
    assertTrue(Primitives.isPrimitive(float.class));
  }

  @Test
  public void isPrimitive_withByteClass_returnsTrue() {
    assertTrue(Primitives.isPrimitive(byte.class));
  }

  @Test
  public void isPrimitive_withDoubleClass_returnsTrue() {
    assertTrue(Primitives.isPrimitive(double.class));
  }

  @Test
  public void isPrimitive_withLongClass_returnsTrue() {
    assertTrue(Primitives.isPrimitive(long.class));
  }

  @Test
  public void isPrimitive_withCharClass_returnsTrue() {
    assertTrue(Primitives.isPrimitive(char.class));
  }

  @Test
  public void isPrimitive_withBooleanClass_returnsTrue() {
    assertTrue(Primitives.isPrimitive(boolean.class));
  }

  @Test
  public void isPrimitive_withShortClass_returnsTrue() {
    assertTrue(Primitives.isPrimitive(short.class));
  }

  @Test
  public void isPrimitive_withVoidClass_returnsTrue() {
    assertTrue(Primitives.isPrimitive(void.class));
  }

  @Test
  public void isPrimitive_withIntegerClass_returnsFalse() {
    assertFalse(Primitives.isPrimitive(Integer.class));
  }

  @Test
  public void isPrimitive_withStringClass_returnsFalse() {
    assertFalse(Primitives.isPrimitive(String.class));
  }

  @Test
  public void isPrimitive_withObjectClass_returnsFalse() {
    assertFalse(Primitives.isPrimitive(Object.class));
  }

  @Test
  public void isPrimitive_withParameterizedType_returnsFalse() {
    // A parameterized type (List<String>) is not a primitive
    Type listOfString = new TypeToken<List<String>>() {}.getType();
    assertFalse(Primitives.isPrimitive(listOfString));
  }

  // ==========================================================================
  // isWrapperType tests
  // ==========================================================================

  @Test
  public void isWrapperType_withIntegerClass_returnsTrue() {
    assertTrue(Primitives.isWrapperType(Integer.class));
  }

  @Test
  public void isWrapperType_withFloatClass_returnsTrue() {
    assertTrue(Primitives.isWrapperType(Float.class));
  }

  @Test
  public void isWrapperType_withByteClass_returnsTrue() {
    assertTrue(Primitives.isWrapperType(Byte.class));
  }

  @Test
  public void isWrapperType_withDoubleClass_returnsTrue() {
    assertTrue(Primitives.isWrapperType(Double.class));
  }

  @Test
  public void isWrapperType_withLongClass_returnsTrue() {
    assertTrue(Primitives.isWrapperType(Long.class));
  }

  @Test
  public void isWrapperType_withCharacterClass_returnsTrue() {
    assertTrue(Primitives.isWrapperType(Character.class));
  }

  @Test
  public void isWrapperType_withBooleanClass_returnsTrue() {
    assertTrue(Primitives.isWrapperType(Boolean.class));
  }

  @Test
  public void isWrapperType_withShortClass_returnsTrue() {
    assertTrue(Primitives.isWrapperType(Short.class));
  }

  @Test
  public void isWrapperType_withVoidClass_returnsTrue() {
    assertTrue(Primitives.isWrapperType(Void.class));
  }

  @Test
  public void isWrapperType_withPrimitiveInt_returnsFalse() {
    assertFalse(Primitives.isWrapperType(int.class));
  }

  @Test
  public void isWrapperType_withStringClass_returnsFalse() {
    assertFalse(Primitives.isWrapperType(String.class));
  }

  @Test
  public void isWrapperType_withObjectClass_returnsFalse() {
    assertFalse(Primitives.isWrapperType(Object.class));
  }

  @Test
  public void isWrapperType_withParameterizedType_returnsFalse() {
    Type listOfString = new TypeToken<List<String>>() {}.getType();
    assertFalse(Primitives.isWrapperType(listOfString));
  }

  // ==========================================================================
  // wrap tests
  // ==========================================================================

  @Test
  public void wrap_intClass_returnsIntegerClass() {
    assertEquals(Integer.class, Primitives.wrap(int.class));
  }

  @Test
  public void wrap_floatClass_returnsFloatClass() {
    assertEquals(Float.class, Primitives.wrap(float.class));
  }

  @Test
  public void wrap_byteClass_returnsByteClass() {
    assertEquals(Byte.class, Primitives.wrap(byte.class));
  }

  @Test
  public void wrap_doubleClass_returnsDoubleClass() {
    assertEquals(Double.class, Primitives.wrap(double.class));
  }

  @Test
  public void wrap_longClass_returnsLongClass() {
    assertEquals(Long.class, Primitives.wrap(long.class));
  }

  @Test
  public void wrap_charClass_returnsCharacterClass() {
    assertEquals(Character.class, Primitives.wrap(char.class));
  }

  @Test
  public void wrap_booleanClass_returnsBooleanClass() {
    assertEquals(Boolean.class, Primitives.wrap(boolean.class));
  }

  @Test
  public void wrap_shortClass_returnsShortClass() {
    assertEquals(Short.class, Primitives.wrap(short.class));
  }

  @Test
  public void wrap_voidClass_returnsVoidClass() {
    assertEquals(Void.class, Primitives.wrap(void.class));
  }

  @Test
  public void wrap_integerClass_returnsIntegerClass_idempotent() {
    // wrap should be idempotent - passing wrapper returns same wrapper
    assertEquals(Integer.class, Primitives.wrap(Integer.class));
  }

  @Test
  public void wrap_stringClass_returnsStringClass() {
    // Non-primitive, non-wrapper types should be returned as-is
    assertEquals(String.class, Primitives.wrap(String.class));
  }

  @Test
  public void wrap_objectClass_returnsObjectClass() {
    assertEquals(Object.class, Primitives.wrap(Object.class));
  }

  // ==========================================================================
  // unwrap tests
  // ==========================================================================

  @Test
  public void unwrap_integerClass_returnsIntClass() {
    assertEquals(int.class, Primitives.unwrap(Integer.class));
  }

  @Test
  public void unwrap_floatClass_returnsFloatPrimitive() {
    assertEquals(float.class, Primitives.unwrap(Float.class));
  }

  @Test
  public void unwrap_byteClass_returnsBytePrimitive() {
    assertEquals(byte.class, Primitives.unwrap(Byte.class));
  }

  @Test
  public void unwrap_doubleClass_returnsDoublePrimitive() {
    assertEquals(double.class, Primitives.unwrap(Double.class));
  }

  @Test
  public void unwrap_longClass_returnsLongPrimitive() {
    assertEquals(long.class, Primitives.unwrap(Long.class));
  }

  @Test
  public void unwrap_characterClass_returnsCharPrimitive() {
    assertEquals(char.class, Primitives.unwrap(Character.class));
  }

  @Test
  public void unwrap_booleanClass_returnsBooleanPrimitive() {
    assertEquals(boolean.class, Primitives.unwrap(Boolean.class));
  }

  @Test
  public void unwrap_shortClass_returnsShortPrimitive() {
    assertEquals(short.class, Primitives.unwrap(Short.class));
  }

  @Test
  public void unwrap_voidClass_returnsVoidPrimitive() {
    assertEquals(void.class, Primitives.unwrap(Void.class));
  }

  @Test
  public void unwrap_intClass_returnsIntClass_idempotent() {
    // unwrap should be idempotent - passing primitive returns same primitive
    assertEquals(int.class, Primitives.unwrap(int.class));
  }

  @Test
  public void unwrap_stringClass_returnsStringClass() {
    // Non-primitive, non-wrapper types should be returned as-is
    assertEquals(String.class, Primitives.unwrap(String.class));
  }

  @Test
  public void unwrap_objectClass_returnsObjectClass() {
    assertEquals(Object.class, Primitives.unwrap(Object.class));
  }

  // ==========================================================================
  // Round-trip tests (wrap then unwrap and vice versa)
  // ==========================================================================

  @Test
  public void wrapThenUnwrap_allPrimitives_returnOriginal() {
    assertEquals(int.class, Primitives.unwrap(Primitives.wrap(int.class)));
    assertEquals(float.class, Primitives.unwrap(Primitives.wrap(float.class)));
    assertEquals(byte.class, Primitives.unwrap(Primitives.wrap(byte.class)));
    assertEquals(double.class, Primitives.unwrap(Primitives.wrap(double.class)));
    assertEquals(long.class, Primitives.unwrap(Primitives.wrap(long.class)));
    assertEquals(char.class, Primitives.unwrap(Primitives.wrap(char.class)));
    assertEquals(boolean.class, Primitives.unwrap(Primitives.wrap(boolean.class)));
    assertEquals(short.class, Primitives.unwrap(Primitives.wrap(short.class)));
    assertEquals(void.class, Primitives.unwrap(Primitives.wrap(void.class)));
  }

  @Test
  public void unwrapThenWrap_allWrappers_returnOriginal() {
    assertEquals(Integer.class, Primitives.wrap(Primitives.unwrap(Integer.class)));
    assertEquals(Float.class, Primitives.wrap(Primitives.unwrap(Float.class)));
    assertEquals(Byte.class, Primitives.wrap(Primitives.unwrap(Byte.class)));
    assertEquals(Double.class, Primitives.wrap(Primitives.unwrap(Double.class)));
    assertEquals(Long.class, Primitives.wrap(Primitives.unwrap(Long.class)));
    assertEquals(Character.class, Primitives.wrap(Primitives.unwrap(Character.class)));
    assertEquals(Boolean.class, Primitives.wrap(Primitives.unwrap(Boolean.class)));
    assertEquals(Short.class, Primitives.wrap(Primitives.unwrap(Short.class)));
    assertEquals(Void.class, Primitives.wrap(Primitives.unwrap(Void.class)));
  }
}
