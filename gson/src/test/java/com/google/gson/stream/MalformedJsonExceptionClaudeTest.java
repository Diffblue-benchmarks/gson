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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import org.junit.Test;

/**
 * Test class for {@link MalformedJsonException}.
 */
public class MalformedJsonExceptionClaudeTest {

  // ==================== Constructor(String) Tests ====================

  @Test
  public void testConstructorWithMessage_setsMessage() {
    String message = "Invalid JSON syntax";
    MalformedJsonException exception = new MalformedJsonException(message);

    assertEquals(message, exception.getMessage());
  }

  @Test
  public void testConstructorWithMessage_noCause() {
    String message = "Unexpected token";
    MalformedJsonException exception = new MalformedJsonException(message);

    assertNull(exception.getCause());
  }

  @Test
  public void testConstructorWithMessage_nullMessage() {
    MalformedJsonException exception = new MalformedJsonException((String) null);

    assertNull(exception.getMessage());
    assertNull(exception.getCause());
  }

  @Test
  public void testConstructorWithMessage_emptyMessage() {
    MalformedJsonException exception = new MalformedJsonException("");

    assertEquals("", exception.getMessage());
  }

  @Test
  public void testConstructorWithMessage_extendsIOException() {
    MalformedJsonException exception = new MalformedJsonException("test");

    assertTrue(exception instanceof IOException);
  }

  // ==================== Constructor(String, Throwable) Tests ====================

  @Test
  public void testConstructorWithMessageAndCause_setsMessageAndCause() {
    String message = "Malformed JSON at line 5";
    Throwable cause = new RuntimeException("Root cause");
    MalformedJsonException exception = new MalformedJsonException(message, cause);

    assertEquals(message, exception.getMessage());
    assertSame(cause, exception.getCause());
  }

  @Test
  public void testConstructorWithMessageAndCause_nullMessage() {
    Throwable cause = new IllegalStateException("Some error");
    MalformedJsonException exception = new MalformedJsonException(null, cause);

    assertNull(exception.getMessage());
    assertSame(cause, exception.getCause());
  }

  @Test
  public void testConstructorWithMessageAndCause_nullCause() {
    String message = "Parse error";
    MalformedJsonException exception = new MalformedJsonException(message, null);

    assertEquals(message, exception.getMessage());
    assertNull(exception.getCause());
  }

  @Test
  public void testConstructorWithMessageAndCause_bothNull() {
    MalformedJsonException exception = new MalformedJsonException(null, null);

    assertNull(exception.getMessage());
    assertNull(exception.getCause());
  }

  @Test
  public void testConstructorWithMessageAndCause_extendsIOException() {
    MalformedJsonException exception = new MalformedJsonException("test", new Exception());

    assertTrue(exception instanceof IOException);
  }

  @Test
  public void testConstructorWithMessageAndCause_nestedMalformedJsonException() {
    MalformedJsonException innerException = new MalformedJsonException("inner");
    MalformedJsonException exception = new MalformedJsonException("outer", innerException);

    assertEquals("outer", exception.getMessage());
    assertSame(innerException, exception.getCause());
    assertEquals("inner", innerException.getMessage());
  }

  // ==================== Constructor(Throwable) Tests ====================

  @Test
  public void testConstructorWithCause_setsCause() {
    Throwable cause = new NumberFormatException("Not a number");
    MalformedJsonException exception = new MalformedJsonException(cause);

    assertSame(cause, exception.getCause());
  }

  @Test
  public void testConstructorWithCause_messageFromCause() {
    Throwable cause = new RuntimeException("Cause message");
    MalformedJsonException exception = new MalformedJsonException(cause);

    // IOException(Throwable) sets message to cause.toString()
    assertEquals(cause.toString(), exception.getMessage());
  }

  @Test
  public void testConstructorWithCause_nullCause() {
    MalformedJsonException exception = new MalformedJsonException((Throwable) null);

    assertNull(exception.getCause());
    assertNull(exception.getMessage());
  }

  @Test
  public void testConstructorWithCause_extendsIOException() {
    MalformedJsonException exception = new MalformedJsonException(new Exception("test"));

    assertTrue(exception instanceof IOException);
  }

  @Test
  public void testConstructorWithCause_causeWithNullMessage() {
    Throwable cause = new RuntimeException((String) null);
    MalformedJsonException exception = new MalformedJsonException(cause);

    assertSame(cause, exception.getCause());
    // IOException(Throwable) uses cause.toString() which includes class name even with null message
    assertEquals(cause.toString(), exception.getMessage());
  }

  // ==================== General Exception Behavior Tests ====================

  @Test
  public void testExceptionCanBeThrown() {
    boolean caught = false;
    try {
      throw new MalformedJsonException("Test throwing");
    } catch (MalformedJsonException e) {
      caught = true;
      assertEquals("Test throwing", e.getMessage());
    }
    assertTrue("Exception should be caught", caught);
  }

  @Test
  public void testExceptionCanBeCaughtAsIOException() {
    boolean caught = false;
    try {
      throw new MalformedJsonException("IO error");
    } catch (IOException e) {
      caught = true;
      assertTrue(e instanceof MalformedJsonException);
    }
    assertTrue("Exception should be caught as IOException", caught);
  }

  @Test
  public void testExceptionPreservesCauseChain() {
    RuntimeException rootCause = new RuntimeException("Root error");
    IllegalStateException intermediateCause = new IllegalStateException("Intermediate", rootCause);
    MalformedJsonException exception = new MalformedJsonException("Wrapper", intermediateCause);

    // Verify the cause chain is preserved
    assertSame(intermediateCause, exception.getCause());
    assertSame(rootCause, exception.getCause().getCause());
  }
}
