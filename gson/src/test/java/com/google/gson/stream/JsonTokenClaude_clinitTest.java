/*
 * Copyright (C) 2010 Google Inc.
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

package com.google.gson.stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * Test class for {@link JsonToken} static initialization ({@code <clinit>}).
 *
 * <p>This test ensures that all enum constants are properly initialized when the class is loaded.
 * The static initializer runs when any enum constant is first accessed.
 */
public class JsonTokenClaude_clinitTest {

  /**
   * Tests that accessing the enum values triggers class initialization and all constants are
   * created correctly.
   */
  @Test
  public void testClassInitialization_allEnumConstantsCreated() {
    // Accessing values() triggers <clinit> and creates all enum constants
    JsonToken[] tokens = JsonToken.values();

    // Verify all 10 constants exist
    assertEquals(10, tokens.length);

    // Verify each constant is not null (was properly initialized)
    assertNotNull(JsonToken.BEGIN_ARRAY);
    assertNotNull(JsonToken.END_ARRAY);
    assertNotNull(JsonToken.BEGIN_OBJECT);
    assertNotNull(JsonToken.END_OBJECT);
    assertNotNull(JsonToken.NAME);
    assertNotNull(JsonToken.STRING);
    assertNotNull(JsonToken.NUMBER);
    assertNotNull(JsonToken.BOOLEAN);
    assertNotNull(JsonToken.NULL);
    assertNotNull(JsonToken.END_DOCUMENT);
  }

  /**
   * Tests that each enum constant has the correct ordinal value, which is assigned during static
   * initialization.
   */
  @Test
  public void testClassInitialization_ordinalsAssignedCorrectly() {
    assertEquals(0, JsonToken.BEGIN_ARRAY.ordinal());
    assertEquals(1, JsonToken.END_ARRAY.ordinal());
    assertEquals(2, JsonToken.BEGIN_OBJECT.ordinal());
    assertEquals(3, JsonToken.END_OBJECT.ordinal());
    assertEquals(4, JsonToken.NAME.ordinal());
    assertEquals(5, JsonToken.STRING.ordinal());
    assertEquals(6, JsonToken.NUMBER.ordinal());
    assertEquals(7, JsonToken.BOOLEAN.ordinal());
    assertEquals(8, JsonToken.NULL.ordinal());
    assertEquals(9, JsonToken.END_DOCUMENT.ordinal());
  }

  /**
   * Tests that each enum constant has a properly initialized name, which is set during static
   * initialization.
   */
  @Test
  public void testClassInitialization_namesAssignedCorrectly() {
    assertEquals("BEGIN_ARRAY", JsonToken.BEGIN_ARRAY.name());
    assertEquals("END_ARRAY", JsonToken.END_ARRAY.name());
    assertEquals("BEGIN_OBJECT", JsonToken.BEGIN_OBJECT.name());
    assertEquals("END_OBJECT", JsonToken.END_OBJECT.name());
    assertEquals("NAME", JsonToken.NAME.name());
    assertEquals("STRING", JsonToken.STRING.name());
    assertEquals("NUMBER", JsonToken.NUMBER.name());
    assertEquals("BOOLEAN", JsonToken.BOOLEAN.name());
    assertEquals("NULL", JsonToken.NULL.name());
    assertEquals("END_DOCUMENT", JsonToken.END_DOCUMENT.name());
  }

  /**
   * Tests that the enum class itself is properly initialized.
   */
  @Test
  public void testClassInitialization_enumClassInitialized() {
    // Access the class to ensure static initialization
    Class<JsonToken> clazz = JsonToken.class;

    assertNotNull(clazz);
    assertEquals("JsonToken", clazz.getSimpleName());
    assertEquals(true, clazz.isEnum());
  }
}
