/*
 * Copyright (C) 2011 Google Inc.
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
package com.google.gson.metrics;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;

/**
 * Unit tests for {@link BagOfPrimitives}.
 */
public class BagOfPrimitivesTest {

  @Test
  public void testDefaultConstructor() {
    BagOfPrimitives bag = new BagOfPrimitives();

    assertThat(bag.longValue).isEqualTo(BagOfPrimitives.DEFAULT_VALUE);
    assertThat(bag.intValue).isEqualTo(0);
    assertThat(bag.booleanValue).isFalse();
    assertThat(bag.stringValue).isEmpty();
  }

  @Test
  public void testParameterizedConstructor() {
    BagOfPrimitives bag = new BagOfPrimitives(10L, 5, true, "hello");

    assertThat(bag.longValue).isEqualTo(10L);
    assertThat(bag.intValue).isEqualTo(5);
    assertThat(bag.booleanValue).isTrue();
    assertThat(bag.stringValue).isEqualTo("hello");
  }

  @Test
  public void testGetIntValue() {
    BagOfPrimitives bag = new BagOfPrimitives(0L, 42, false, "");

    assertThat(bag.getIntValue()).isEqualTo(42);
  }

  @Test
  public void testGetExpectedJson() {
    BagOfPrimitives bag = new BagOfPrimitives(100L, 20, true, "test");

    String expectedJson = bag.getExpectedJson();

    assertThat(expectedJson).isEqualTo(
        "{\"longValue\":100,\"intValue\":20,\"booleanValue\":true,\"stringValue\":\"test\"}");
  }

  @Test
  public void testHashCode() {
    BagOfPrimitives bag1 = new BagOfPrimitives(10L, 5, true, "hello");
    BagOfPrimitives bag2 = new BagOfPrimitives(10L, 5, true, "hello");

    assertThat(bag1.hashCode()).isEqualTo(bag2.hashCode());
  }

  @Test
  public void testHashCodeWithNullString() {
    BagOfPrimitives bag = new BagOfPrimitives(10L, 5, true, null);

    // Should not throw exception
    int hashCode = bag.hashCode();
    assertThat(hashCode).isNotNull();
  }

  @Test
  public void testHashCodeDifferentValues() {
    BagOfPrimitives bag1 = new BagOfPrimitives(10L, 5, true, "hello");
    BagOfPrimitives bag2 = new BagOfPrimitives(20L, 10, false, "world");

    assertThat(bag1.hashCode()).isNotEqualTo(bag2.hashCode());
  }

  @Test
  public void testEqualsSameInstance() {
    BagOfPrimitives bag = new BagOfPrimitives(10L, 5, true, "hello");

    assertThat(bag.equals(bag)).isTrue();
  }

  @Test
  public void testEqualsEqualObjects() {
    BagOfPrimitives bag1 = new BagOfPrimitives(10L, 5, true, "hello");
    BagOfPrimitives bag2 = new BagOfPrimitives(10L, 5, true, "hello");

    assertThat(bag1.equals(bag2)).isTrue();
  }

  @Test
  public void testEqualsNotInstanceOf() {
    BagOfPrimitives bag = new BagOfPrimitives(10L, 5, true, "hello");

    assertThat(bag.equals("not a BagOfPrimitives")).isFalse();
    assertThat(bag.equals(null)).isFalse();
  }

  @Test
  public void testEqualsDifferentLongValue() {
    BagOfPrimitives bag1 = new BagOfPrimitives(10L, 5, true, "hello");
    BagOfPrimitives bag2 = new BagOfPrimitives(20L, 5, true, "hello");

    assertThat(bag1.equals(bag2)).isFalse();
  }

  @Test
  public void testEqualsDifferentIntValue() {
    BagOfPrimitives bag1 = new BagOfPrimitives(10L, 5, true, "hello");
    BagOfPrimitives bag2 = new BagOfPrimitives(10L, 10, true, "hello");

    assertThat(bag1.equals(bag2)).isFalse();
  }

  @Test
  public void testEqualsDifferentBooleanValue() {
    BagOfPrimitives bag1 = new BagOfPrimitives(10L, 5, true, "hello");
    BagOfPrimitives bag2 = new BagOfPrimitives(10L, 5, false, "hello");

    assertThat(bag1.equals(bag2)).isFalse();
  }

  @Test
  public void testEqualsDifferentStringValue() {
    BagOfPrimitives bag1 = new BagOfPrimitives(10L, 5, true, "hello");
    BagOfPrimitives bag2 = new BagOfPrimitives(10L, 5, true, "world");

    assertThat(bag1.equals(bag2)).isFalse();
  }

  @Test
  public void testEqualsWithNullStrings() {
    BagOfPrimitives bag1 = new BagOfPrimitives(10L, 5, true, null);
    BagOfPrimitives bag2 = new BagOfPrimitives(10L, 5, true, null);

    assertThat(bag1.equals(bag2)).isTrue();
  }

  @Test
  public void testToString() {
    BagOfPrimitives bag = new BagOfPrimitives(100L, 50, true, "test");

    String result = bag.toString();

    assertThat(result).isEqualTo("(longValue=100,intValue=50,booleanValue=true,stringValue=test)");
  }

  @Test
  public void testToStringWithDefaultValues() {
    BagOfPrimitives bag = new BagOfPrimitives();

    String result = bag.toString();

    assertThat(result).isEqualTo("(longValue=0,intValue=0,booleanValue=false,stringValue=)");
  }
}
