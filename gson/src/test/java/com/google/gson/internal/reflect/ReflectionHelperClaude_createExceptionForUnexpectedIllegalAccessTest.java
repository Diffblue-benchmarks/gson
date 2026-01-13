/*
 * Copyright (C) 2021 Google Inc.
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

package com.google.gson.internal.reflect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.google.gson.internal.GsonBuildConfig;
import org.junit.Test;

/**
 * Tests for {@link
 * ReflectionHelper#createExceptionForUnexpectedIllegalAccess(IllegalAccessException)}.
 */
public class ReflectionHelperClaude_createExceptionForUnexpectedIllegalAccessTest {

  // ==========================================================================
  // Tests for exception throwing behavior
  // ==========================================================================

  @Test
  public void createExceptionForUnexpectedIllegalAccess_throwsRuntimeException() {
    IllegalAccessException cause = new IllegalAccessException("test message");

    try {
      ReflectionHelper.createExceptionForUnexpectedIllegalAccess(cause);
      fail("Expected RuntimeException to be thrown");
    } catch (RuntimeException e) {
      // Expected
      assertNotNull(e);
    }
  }

  @Test
  public void createExceptionForUnexpectedIllegalAccess_preservesCause() {
    IllegalAccessException cause = new IllegalAccessException("original cause");

    try {
      ReflectionHelper.createExceptionForUnexpectedIllegalAccess(cause);
      fail("Expected RuntimeException to be thrown");
    } catch (RuntimeException e) {
      assertSame(cause, e.getCause());
    }
  }

  // ==========================================================================
  // Tests for exception message content
  // ==========================================================================

  @Test
  public void createExceptionForUnexpectedIllegalAccess_messageContainsGsonVersion() {
    IllegalAccessException cause = new IllegalAccessException();

    try {
      ReflectionHelper.createExceptionForUnexpectedIllegalAccess(cause);
      fail("Expected RuntimeException to be thrown");
    } catch (RuntimeException e) {
      assertTrue(
          "Message should contain Gson version",
          e.getMessage().contains("Gson " + GsonBuildConfig.VERSION));
    }
  }

  @Test
  public void createExceptionForUnexpectedIllegalAccess_messageContainsIllegalAccessException() {
    IllegalAccessException cause = new IllegalAccessException();

    try {
      ReflectionHelper.createExceptionForUnexpectedIllegalAccess(cause);
      fail("Expected RuntimeException to be thrown");
    } catch (RuntimeException e) {
      assertTrue(
          "Message should mention IllegalAccessException",
          e.getMessage().contains("IllegalAccessException"));
    }
  }

  @Test
  public void createExceptionForUnexpectedIllegalAccess_messageContainsReflectionAccessFilter() {
    IllegalAccessException cause = new IllegalAccessException();

    try {
      ReflectionHelper.createExceptionForUnexpectedIllegalAccess(cause);
      fail("Expected RuntimeException to be thrown");
    } catch (RuntimeException e) {
      assertTrue(
          "Message should mention ReflectionAccessFilter",
          e.getMessage().contains("ReflectionAccessFilter"));
    }
  }

  @Test
  public void createExceptionForUnexpectedIllegalAccess_messageContainsJava9Requirement() {
    IllegalAccessException cause = new IllegalAccessException();

    try {
      ReflectionHelper.createExceptionForUnexpectedIllegalAccess(cause);
      fail("Expected RuntimeException to be thrown");
    } catch (RuntimeException e) {
      assertTrue("Message should mention Java >= 9", e.getMessage().contains("Java >= 9"));
    }
  }

  @Test
  public void createExceptionForUnexpectedIllegalAccess_messageContainsReportingInstructions() {
    IllegalAccessException cause = new IllegalAccessException();

    try {
      ReflectionHelper.createExceptionForUnexpectedIllegalAccess(cause);
      fail("Expected RuntimeException to be thrown");
    } catch (RuntimeException e) {
      assertTrue(
          "Message should mention reporting to maintainers",
          e.getMessage().contains("report this to the Gson maintainers"));
    }
  }

  // ==========================================================================
  // Tests with different IllegalAccessException configurations
  // ==========================================================================

  @Test
  public void createExceptionForUnexpectedIllegalAccess_withNullMessage() {
    IllegalAccessException cause = new IllegalAccessException();

    try {
      ReflectionHelper.createExceptionForUnexpectedIllegalAccess(cause);
      fail("Expected RuntimeException to be thrown");
    } catch (RuntimeException e) {
      assertNotNull(e.getMessage());
      assertSame(cause, e.getCause());
    }
  }

  @Test
  public void createExceptionForUnexpectedIllegalAccess_withDetailedMessage() {
    IllegalAccessException cause =
        new IllegalAccessException("Cannot access field X in class Y");

    try {
      ReflectionHelper.createExceptionForUnexpectedIllegalAccess(cause);
      fail("Expected RuntimeException to be thrown");
    } catch (RuntimeException e) {
      assertSame(cause, e.getCause());
      // The original message is preserved in the cause
      assertEquals("Cannot access field X in class Y", e.getCause().getMessage());
    }
  }

  // ==========================================================================
  // Tests for exception type
  // ==========================================================================

  @Test
  public void createExceptionForUnexpectedIllegalAccess_returnsRuntimeException() {
    IllegalAccessException cause = new IllegalAccessException();

    try {
      ReflectionHelper.createExceptionForUnexpectedIllegalAccess(cause);
      fail("Expected RuntimeException to be thrown");
    } catch (RuntimeException e) {
      // Verify it's exactly RuntimeException, not a subclass
      assertEquals(RuntimeException.class, e.getClass());
    }
  }

  // ==========================================================================
  // Tests for full message verification
  // ==========================================================================

  @Test
  public void createExceptionForUnexpectedIllegalAccess_fullMessageFormat() {
    IllegalAccessException cause = new IllegalAccessException();

    try {
      ReflectionHelper.createExceptionForUnexpectedIllegalAccess(cause);
      fail("Expected RuntimeException to be thrown");
    } catch (RuntimeException e) {
      String expectedMessage =
          "Unexpected IllegalAccessException occurred (Gson "
              + GsonBuildConfig.VERSION
              + "). Certain ReflectionAccessFilter features require Java >= 9 to work correctly."
              + " If you are not using ReflectionAccessFilter, report this to the Gson"
              + " maintainers.";
      assertEquals(expectedMessage, e.getMessage());
    }
  }
}
