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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit tests for {@link BagOfPrimitives}.
 */
public class BagOfPrimitivesTest {

  @Test
  public void testDefaultConstructor() {
    BagOfPrimitives bag = new BagOfPrimitives();

    assertEquals(BagOfPrimitives.DEFAULT_VALUE, bag.longValue);
    assertEquals(0, bag.intValue);
    assertFalse(bag.booleanValue);
    assertEquals("", bag.stringValue);
  }

  @Test
  public void testParameterizedConstructor() {
    BagOfPrimitives bag = new BagOfPrimitives(10L, 5, true, "hello");

    assertEquals(10L, bag.longValue);
    assertEquals(5, bag.intValue);
    assertTrue(bag.booleanValue);
    assertEquals("hello", bag.stringValue);
  }

  @Test
  public void testGetIntValue() {
    BagOfPrimitives bag = new BagOfPrimitives(0L, 42, false, "");

    assertEquals(42, bag.getIntValue());
  }

  @Test
  public void testGetExpectedJson() {
    BagOfPrimitives bag = new BagOfPrimitives(100L, 20, true, "test");

    String expectedJson = bag.getExpectedJson();

    assertEquals(
        "{\"longValue\":100,\"intValue\":20,\"booleanValue\":true,\"stringValue\":\"test\"}",
        expectedJson);
  }

  @Test
  public void testHashCode() {
    BagOfPrimitives bag1 = new BagOfPrimitives(10L, 5, true, "hello");
    BagOfPrimitives bag2 = new BagOfPrimitives(10L, 5, true, "hello");

    assertEquals(bag1.hashCode(), bag2.hashCode());
  }

  @Test
  public void testHashCodeWithNullString() {
    BagOfPrimitives bag = new BagOfPrimitives(10L, 5, true, null);

    // Should not throw exception
    int hashCode = bag.hashCode();
    assertNotNull(hashCode);
  }

  @Test
  public void testHashCodeDifferentValues() {
    BagOfPrimitives bag1 = new BagOfPrimitives(10L, 5, true, "hello");
    BagOfPrimitives bag2 = new BagOfPrimitives(20L, 10, false, "world");

    assertNotEquals(bag1.hashCode(), bag2.hashCode());
  }

  @Test
  public void testEqualsSameInstance() {
    BagOfPrimitives bag = new BagOfPrimitives(10L, 5, true, "hello");

    assertTrue(bag.equals(bag));
  }

  @Test
  public void testEqualsEqualObjects() {
    BagOfPrimitives bag1 = new BagOfPrimitives(10L, 5, true, "hello");
    BagOfPrimitives bag2 = new BagOfPrimitives(10L, 5, true, "hello");

    assertTrue(bag1.equals(bag2));
  }

  @Test
  public void testEqualsNotInstanceOf() {
    BagOfPrimitives bag = new BagOfPrimitives(10L, 5, true, "hello");

    assertFalse(bag.equals("not a BagOfPrimitives"));
    assertFalse(bag.equals(null));
  }

  @Test
  public void testEqualsDifferentLongValue() {
    BagOfPrimitives bag1 = new BagOfPrimitives(10L, 5, true, "hello");
    BagOfPrimitives bag2 = new BagOfPrimitives(20L, 5, true, "hello");

    assertFalse(bag1.equals(bag2));
  }

  @Test
  public void testEqualsDifferentIntValue() {
    BagOfPrimitives bag1 = new BagOfPrimitives(10L, 5, true, "hello");
    BagOfPrimitives bag2 = new BagOfPrimitives(10L, 10, true, "hello");

    assertFalse(bag1.equals(bag2));
  }

  @Test
  public void testEqualsDifferentBooleanValue() {
    BagOfPrimitives bag1 = new BagOfPrimitives(10L, 5, true, "hello");
    BagOfPrimitives bag2 = new BagOfPrimitives(10L, 5, false, "hello");

    assertFalse(bag1.equals(bag2));
  }

  @Test
  public void testEqualsDifferentStringValue() {
    BagOfPrimitives bag1 = new BagOfPrimitives(10L, 5, true, "hello");
    BagOfPrimitives bag2 = new BagOfPrimitives(10L, 5, true, "world");

    assertFalse(bag1.equals(bag2));
  }

  @Test
  public void testEqualsWithNullStrings() {
    BagOfPrimitives bag1 = new BagOfPrimitives(10L, 5, true, null);
    BagOfPrimitives bag2 = new BagOfPrimitives(10L, 5, true, null);

    assertTrue(bag1.equals(bag2));
  }

  @Test
  public void testToString() {
    BagOfPrimitives bag = new BagOfPrimitives(100L, 50, true, "test");

    String result = bag.toString();

    assertEquals("(longValue=100,intValue=50,booleanValue=true,stringValue=test)", result);
  }

  @Test
  public void testToStringWithDefaultValues() {
    BagOfPrimitives bag = new BagOfPrimitives();

    String result = bag.toString();

    assertEquals("(longValue=0,intValue=0,booleanValue=false,stringValue=)", result);
  }
}
