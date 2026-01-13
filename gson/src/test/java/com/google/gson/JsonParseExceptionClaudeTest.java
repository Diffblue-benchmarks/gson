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

import java.io.IOException;
import org.junit.Test;

/**
 * Unit tests for {@link JsonParseException}.
 *
 * @author Claude
 */
public class JsonParseExceptionClaudeTest {

  // ========== Constructor <init>.(Ljava/lang/String;)V Tests ==========

  @Test
  public void testStringConstructorWithMessage() {
    String message = "Test parse error message";
    JsonParseException exception = new JsonParseException(message);

    assertThat(exception.getMessage()).isEqualTo(message);
    assertThat(exception.getCause()).isNull();
  }

  @Test
  public void testStringConstructorWithNullMessage() {
    JsonParseException exception = new JsonParseException((String) null);

    assertThat(exception.getMessage()).isNull();
    assertThat(exception.getCause()).isNull();
  }

  @Test
  public void testStringConstructorWithEmptyMessage() {
    JsonParseException exception = new JsonParseException("");

    assertThat(exception.getMessage()).isEmpty();
    assertThat(exception.getCause()).isNull();
  }

  @Test
  public void testStringConstructorExtendsRuntimeException() {
    JsonParseException exception = new JsonParseException("test");

    assertThat(exception).isInstanceOf(RuntimeException.class);
    assertThat(exception).isInstanceOf(Exception.class);
    assertThat(exception).isInstanceOf(Throwable.class);
  }

  @Test
  public void testStringConstructorWithSpecialCharacters() {
    String message = "Parse error with special chars: äöü αβγ 你好 \n\t\"quotes\"";
    JsonParseException exception = new JsonParseException(message);

    assertThat(exception.getMessage()).isEqualTo(message);
  }

  // ========== Constructor <init>.(Ljava/lang/String;Ljava/lang/Throwable;)V Tests ==========

  @Test
  public void testStringThrowableConstructorWithMessageAndCause() {
    String message = "Parse operation failed";
    Throwable cause = new IOException("Underlying error");
    JsonParseException exception = new JsonParseException(message, cause);

    assertThat(exception.getMessage()).isEqualTo(message);
    assertThat(exception.getCause()).isSameInstanceAs(cause);
  }

  @Test
  public void testStringThrowableConstructorWithNullMessage() {
    Throwable cause = new IOException("Underlying error");
    JsonParseException exception = new JsonParseException(null, cause);

    assertThat(exception.getMessage()).isNull();
    assertThat(exception.getCause()).isSameInstanceAs(cause);
  }

  @Test
  public void testStringThrowableConstructorWithNullCause() {
    String message = "Parse operation failed";
    JsonParseException exception = new JsonParseException(message, null);

    assertThat(exception.getMessage()).isEqualTo(message);
    assertThat(exception.getCause()).isNull();
  }

  @Test
  public void testStringThrowableConstructorWithBothNull() {
    JsonParseException exception = new JsonParseException(null, null);

    assertThat(exception.getMessage()).isNull();
    assertThat(exception.getCause()).isNull();
  }

  @Test
  public void testStringThrowableConstructorWithNestedCause() {
    IOException rootCause = new IOException("Root cause");
    RuntimeException intermediateCause = new RuntimeException("Intermediate", rootCause);
    JsonParseException exception = new JsonParseException("Top level error", intermediateCause);

    assertThat(exception.getMessage()).isEqualTo("Top level error");
    assertThat(exception.getCause()).isSameInstanceAs(intermediateCause);
    assertThat(exception.getCause().getCause()).isSameInstanceAs(rootCause);
  }

  @Test
  public void testStringThrowableConstructorWithEmptyMessageAndCause() {
    Throwable cause = new IllegalArgumentException("Bad argument");
    JsonParseException exception = new JsonParseException("", cause);

    assertThat(exception.getMessage()).isEmpty();
    assertThat(exception.getCause()).isSameInstanceAs(cause);
  }

  // ========== Constructor <init>.(Ljava/lang/Throwable;)V Tests ==========

  @Test
  public void testThrowableConstructorWithCause() {
    Throwable cause = new IOException("IO failure");
    JsonParseException exception = new JsonParseException(cause);

    // When using Throwable-only constructor, message is derived from cause
    assertThat(exception.getCause()).isSameInstanceAs(cause);
    assertThat(exception.getMessage()).contains("IOException");
  }

  @Test
  public void testThrowableConstructorWithNullCause() {
    JsonParseException exception = new JsonParseException((Throwable) null);

    assertThat(exception.getCause()).isNull();
    assertThat(exception.getMessage()).isNull();
  }

  @Test
  public void testThrowableConstructorWithCauseHavingMessage() {
    String causeMessage = "Detailed failure description";
    Throwable cause = new IOException(causeMessage);
    JsonParseException exception = new JsonParseException(cause);

    assertThat(exception.getCause()).isSameInstanceAs(cause);
    // Message contains cause's class name and message
    assertThat(exception.getMessage()).contains(causeMessage);
  }

  @Test
  public void testThrowableConstructorWithCauseHavingNullMessage() {
    Throwable cause = new IOException();
    JsonParseException exception = new JsonParseException(cause);

    assertThat(exception.getCause()).isSameInstanceAs(cause);
    // Message should still contain the cause's class name
    assertThat(exception.getMessage()).contains("IOException");
  }

  @Test
  public void testThrowableConstructorWithDifferentCauseTypes() {
    // Test with different exception types as cause
    RuntimeException runtimeCause = new RuntimeException("runtime error");
    JsonParseException exception1 = new JsonParseException(runtimeCause);
    assertThat(exception1.getCause()).isSameInstanceAs(runtimeCause);
    assertThat(exception1.getMessage()).contains("RuntimeException");

    IllegalStateException stateCause = new IllegalStateException("illegal state");
    JsonParseException exception2 = new JsonParseException(stateCause);
    assertThat(exception2.getCause()).isSameInstanceAs(stateCause);
    assertThat(exception2.getMessage()).contains("IllegalStateException");
  }

  // ========== Exception behavior tests ==========

  @Test
  public void testExceptionCanBeThrown() {
    String message = "Test exception throw";
    try {
      throw new JsonParseException(message);
    } catch (JsonParseException e) {
      assertThat(e.getMessage()).isEqualTo(message);
    }
  }

  @Test
  public void testExceptionCanBeCaughtAsRuntimeException() {
    try {
      throw new JsonParseException("test");
    } catch (RuntimeException e) {
      assertThat(e).isInstanceOf(JsonParseException.class);
    }
  }

  @Test
  public void testExceptionCanBeCaughtAsException() {
    try {
      throw new JsonParseException("test");
    } catch (Exception e) {
      assertThat(e).isInstanceOf(JsonParseException.class);
    }
  }

  @Test
  public void testExceptionPreservesStackTrace() {
    JsonParseException exception = new JsonParseException("test");
    StackTraceElement[] stackTrace = exception.getStackTrace();

    assertThat(stackTrace).isNotEmpty();
    assertThat(stackTrace[0].getMethodName()).isEqualTo("testExceptionPreservesStackTrace");
  }

  @Test
  public void testExceptionWithCausePreservesStackTrace() {
    IOException cause = new IOException("cause");
    JsonParseException exception = new JsonParseException("message", cause);

    StackTraceElement[] exceptionStackTrace = exception.getStackTrace();
    StackTraceElement[] causeStackTrace = cause.getStackTrace();

    assertThat(exceptionStackTrace).isNotEmpty();
    assertThat(causeStackTrace).isNotEmpty();
    // Both should be created in this test method
    assertThat(exceptionStackTrace[0].getMethodName())
        .isEqualTo("testExceptionWithCausePreservesStackTrace");
    assertThat(causeStackTrace[0].getMethodName())
        .isEqualTo("testExceptionWithCausePreservesStackTrace");
  }

  @Test
  public void testExceptionThrownWithCause() {
    IOException cause = new IOException("underlying cause");
    try {
      throw new JsonParseException("wrapper", cause);
    } catch (JsonParseException e) {
      assertThat(e.getMessage()).isEqualTo("wrapper");
      assertThat(e.getCause()).isSameInstanceAs(cause);
    }
  }

  @Test
  public void testExceptionThrownWithThrowableCause() {
    Throwable cause = new Error("serious error");
    try {
      throw new JsonParseException(cause);
    } catch (JsonParseException e) {
      assertThat(e.getCause()).isSameInstanceAs(cause);
      assertThat(e.getMessage()).contains("Error");
    }
  }
}
