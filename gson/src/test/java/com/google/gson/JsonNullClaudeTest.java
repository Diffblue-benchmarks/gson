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

import org.junit.Test;

/**
 * Unit tests for {@link JsonNull}.
 *
 * @author Claude
 */
public class JsonNullClaudeTest {

  // ========== Constructor <init>.()V Tests ==========

  @SuppressWarnings("deprecation")
  @Test
  public void testDefaultConstructorCreatesInstance() {
    JsonNull jsonNull = new JsonNull();

    assertThat(jsonNull).isNotNull();
    assertThat(jsonNull).isInstanceOf(JsonNull.class);
    assertThat(jsonNull).isInstanceOf(JsonElement.class);
  }

  @SuppressWarnings("deprecation")
  @Test
  public void testDefaultConstructorCreatesNewInstance() {
    JsonNull jsonNull1 = new JsonNull();
    JsonNull jsonNull2 = new JsonNull();

    // Each call creates a new instance (they are different objects)
    assertThat(jsonNull1).isNotSameInstanceAs(jsonNull2);
  }

  @SuppressWarnings("deprecation")
  @Test
  public void testDefaultConstructorInstanceNotSameAsStaticInstance() {
    JsonNull jsonNull = new JsonNull();

    // A newly constructed instance is not the same object as INSTANCE
    assertThat(jsonNull).isNotSameInstanceAs(JsonNull.INSTANCE);
  }

  @Test
  public void testStaticInstanceExists() {
    assertThat(JsonNull.INSTANCE).isNotNull();
    assertThat(JsonNull.INSTANCE).isInstanceOf(JsonNull.class);
  }

  // ========== deepCopy.()Lcom/google/gson/JsonNull; Tests ==========

  @Test
  public void testDeepCopyReturnsSameInstance() {
    JsonNull deepCopy = JsonNull.INSTANCE.deepCopy();

    // deepCopy returns the same INSTANCE since JsonNull is immutable
    assertThat(deepCopy).isSameInstanceAs(JsonNull.INSTANCE);
  }

  @SuppressWarnings("deprecation")
  @Test
  public void testDeepCopyOnNewInstanceReturnsStaticInstance() {
    JsonNull jsonNull = new JsonNull();
    JsonNull deepCopy = jsonNull.deepCopy();

    // deepCopy always returns the static INSTANCE, even when called on a new instance
    assertThat(deepCopy).isSameInstanceAs(JsonNull.INSTANCE);
  }

  @Test
  public void testDeepCopyIsIdempotent() {
    JsonNull deepCopy1 = JsonNull.INSTANCE.deepCopy();
    JsonNull deepCopy2 = deepCopy1.deepCopy();

    assertThat(deepCopy1).isSameInstanceAs(deepCopy2);
    assertThat(deepCopy2).isSameInstanceAs(JsonNull.INSTANCE);
  }

  @Test
  public void testDeepCopyReturnTypeIsJsonNull() {
    // Verify the return type is JsonNull (not just JsonElement)
    JsonNull deepCopy = JsonNull.INSTANCE.deepCopy();

    assertThat(deepCopy).isInstanceOf(JsonNull.class);
  }

  // ========== hashCode.()I Tests ==========

  @Test
  public void testHashCodeReturnsClassHashCode() {
    int hashCode = JsonNull.INSTANCE.hashCode();

    // hashCode returns the hash code of the JsonNull class
    assertThat(hashCode).isEqualTo(JsonNull.class.hashCode());
  }

  @SuppressWarnings("deprecation")
  @Test
  public void testHashCodeConsistentAcrossInstances() {
    JsonNull jsonNull1 = new JsonNull();
    JsonNull jsonNull2 = new JsonNull();

    // All instances should have the same hash code
    assertThat(jsonNull1.hashCode()).isEqualTo(jsonNull2.hashCode());
    assertThat(jsonNull1.hashCode()).isEqualTo(JsonNull.INSTANCE.hashCode());
  }

  @Test
  public void testHashCodeIsConsistent() {
    int hashCode1 = JsonNull.INSTANCE.hashCode();
    int hashCode2 = JsonNull.INSTANCE.hashCode();

    // Multiple calls should return the same value
    assertThat(hashCode1).isEqualTo(hashCode2);
  }

  @Test
  public void testHashCodeNonZero() {
    // The hash code should be non-zero (a reasonable expectation for class hash codes)
    assertThat(JsonNull.INSTANCE.hashCode()).isNotEqualTo(0);
  }

  // ========== equals.(Ljava/lang/Object;)Z Tests ==========

  @Test
  public void testEqualsWithSameInstance() {
    assertThat(JsonNull.INSTANCE.equals(JsonNull.INSTANCE)).isTrue();
  }

  @SuppressWarnings("deprecation")
  @Test
  public void testEqualsWithDifferentJsonNullInstances() {
    JsonNull jsonNull1 = new JsonNull();
    JsonNull jsonNull2 = new JsonNull();

    // All JsonNull instances are considered equal
    assertThat(jsonNull1.equals(jsonNull2)).isTrue();
    assertThat(jsonNull2.equals(jsonNull1)).isTrue();
  }

  @SuppressWarnings("deprecation")
  @Test
  public void testEqualsWithStaticInstance() {
    JsonNull jsonNull = new JsonNull();

    // A new instance should be equal to the static INSTANCE
    assertThat(jsonNull.equals(JsonNull.INSTANCE)).isTrue();
    assertThat(JsonNull.INSTANCE.equals(jsonNull)).isTrue();
  }

  @Test
  public void testEqualsWithNull() {
    // equals(null) should return false (null is not an instance of JsonNull)
    assertThat(JsonNull.INSTANCE.equals(null)).isFalse();
  }

  @Test
  public void testEqualsWithDifferentType() {
    // equals with a different type should return false
    assertThat(JsonNull.INSTANCE.equals("null")).isFalse();
    assertThat(JsonNull.INSTANCE.equals(0)).isFalse();
    assertThat(JsonNull.INSTANCE.equals(new Object())).isFalse();
  }

  @Test
  public void testEqualsWithOtherJsonElementTypes() {
    // equals with other JsonElement subclasses should return false
    assertThat(JsonNull.INSTANCE.equals(new JsonPrimitive("null"))).isFalse();
    assertThat(JsonNull.INSTANCE.equals(new JsonPrimitive(0))).isFalse();
    assertThat(JsonNull.INSTANCE.equals(new JsonArray())).isFalse();
    assertThat(JsonNull.INSTANCE.equals(new JsonObject())).isFalse();
  }

  @Test
  public void testEqualsIsReflexive() {
    // x.equals(x) should be true
    assertThat(JsonNull.INSTANCE.equals(JsonNull.INSTANCE)).isTrue();
  }

  @SuppressWarnings("deprecation")
  @Test
  public void testEqualsIsSymmetric() {
    JsonNull jsonNull = new JsonNull();

    // x.equals(y) should equal y.equals(x)
    assertThat(JsonNull.INSTANCE.equals(jsonNull)).isEqualTo(jsonNull.equals(JsonNull.INSTANCE));
  }

  @SuppressWarnings("deprecation")
  @Test
  public void testEqualsIsTransitive() {
    JsonNull jsonNull1 = new JsonNull();
    JsonNull jsonNull2 = new JsonNull();

    // If x.equals(y) and y.equals(z), then x.equals(z)
    assertThat(JsonNull.INSTANCE.equals(jsonNull1)).isTrue();
    assertThat(jsonNull1.equals(jsonNull2)).isTrue();
    assertThat(JsonNull.INSTANCE.equals(jsonNull2)).isTrue();
  }

  // ========== Hash code and equals contract tests ==========

  @SuppressWarnings("deprecation")
  @Test
  public void testHashCodeEqualsContract() {
    JsonNull jsonNull1 = new JsonNull();
    JsonNull jsonNull2 = new JsonNull();

    // If two objects are equal, they must have the same hash code
    assertThat(jsonNull1.equals(jsonNull2)).isTrue();
    assertThat(jsonNull1.hashCode()).isEqualTo(jsonNull2.hashCode());
  }

  @Test
  public void testHashCodeEqualsContractWithStaticInstance() {
    // INSTANCE should have consistent hash code
    assertThat(JsonNull.INSTANCE.equals(JsonNull.INSTANCE)).isTrue();
    assertThat(JsonNull.INSTANCE.hashCode()).isEqualTo(JsonNull.INSTANCE.hashCode());
  }
}
