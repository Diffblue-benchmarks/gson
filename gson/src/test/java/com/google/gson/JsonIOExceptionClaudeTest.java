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
 * Unit tests for {@link JsonIOException}.
 *
 * @author Claude
 */
public class JsonIOExceptionClaudeTest {

  // ========== Constructor <init>.(Ljava/lang/String;)V Tests ==========

  @Test
  public void testStringConstructorWithMessage() {
    String message = "Test IO error message";
    JsonIOException exception = new JsonIOException(message);

    assertThat(exception.getMessage()).isEqualTo(message);
    assertThat(exception.getCause()).isNull();
  }

  @Test
  public void testStringConstructorWithNullMessage() {
    JsonIOException exception = new JsonIOException((String) null);

    assertThat(exception.getMessage()).isNull();
    assertThat(exception.getCause()).isNull();
  }

  @Test
  public void testStringConstructorWithEmptyMessage() {
    JsonIOException exception = new JsonIOException("");

    assertThat(exception.getMessage()).isEmpty();
    assertThat(exception.getCause()).isNull();
  }

  @Test
  public void testStringConstructorExtendsJsonParseException() {
    JsonIOException exception = new JsonIOException("test");

    assertThat(exception).isInstanceOf(JsonParseException.class);
    assertThat(exception).isInstanceOf(RuntimeException.class);
  }

  // ========== Constructor <init>.(Ljava/lang/String;Ljava/lang/Throwable;)V Tests ==========

  @Test
  public void testStringThrowableConstructorWithMessageAndCause() {
    String message = "IO operation failed";
    Throwable cause = new IOException("Underlying IO error");
    JsonIOException exception = new JsonIOException(message, cause);

    assertThat(exception.getMessage()).isEqualTo(message);
    assertThat(exception.getCause()).isSameInstanceAs(cause);
  }

  @Test
  public void testStringThrowableConstructorWithNullMessage() {
    Throwable cause = new IOException("Underlying IO error");
    JsonIOException exception = new JsonIOException(null, cause);

    assertThat(exception.getMessage()).isNull();
    assertThat(exception.getCause()).isSameInstanceAs(cause);
  }

  @Test
  public void testStringThrowableConstructorWithNullCause() {
    String message = "IO operation failed";
    JsonIOException exception = new JsonIOException(message, null);

    assertThat(exception.getMessage()).isEqualTo(message);
    assertThat(exception.getCause()).isNull();
  }

  @Test
  public void testStringThrowableConstructorWithBothNull() {
    JsonIOException exception = new JsonIOException(null, null);

    assertThat(exception.getMessage()).isNull();
    assertThat(exception.getCause()).isNull();
  }

  @Test
  public void testStringThrowableConstructorWithNestedCause() {
    IOException rootCause = new IOException("Root cause");
    RuntimeException intermediateCause = new RuntimeException("Intermediate", rootCause);
    JsonIOException exception = new JsonIOException("Top level error", intermediateCause);

    assertThat(exception.getMessage()).isEqualTo("Top level error");
    assertThat(exception.getCause()).isSameInstanceAs(intermediateCause);
    assertThat(exception.getCause().getCause()).isSameInstanceAs(rootCause);
  }

  // ========== Constructor <init>.(Ljava/lang/Throwable;)V Tests ==========

  @Test
  public void testThrowableConstructorWithCause() {
    Throwable cause = new IOException("IO failure");
    JsonIOException exception = new JsonIOException(cause);

    // When using Throwable-only constructor, message is derived from cause
    assertThat(exception.getCause()).isSameInstanceAs(cause);
    assertThat(exception.getMessage()).contains("IOException");
  }

  @Test
  public void testThrowableConstructorWithNullCause() {
    JsonIOException exception = new JsonIOException((Throwable) null);

    assertThat(exception.getCause()).isNull();
    assertThat(exception.getMessage()).isNull();
  }

  @Test
  public void testThrowableConstructorWithCauseHavingMessage() {
    String causeMessage = "Detailed IO failure description";
    Throwable cause = new IOException(causeMessage);
    JsonIOException exception = new JsonIOException(cause);

    assertThat(exception.getCause()).isSameInstanceAs(cause);
    // Message contains cause's class name and message
    assertThat(exception.getMessage()).contains(causeMessage);
  }

  @Test
  public void testThrowableConstructorWithCauseHavingNullMessage() {
    Throwable cause = new IOException();
    JsonIOException exception = new JsonIOException(cause);

    assertThat(exception.getCause()).isSameInstanceAs(cause);
    // Message should still contain the cause's class name
    assertThat(exception.getMessage()).contains("IOException");
  }

  // ========== Exception behavior tests ==========

  @Test
  public void testExceptionCanBeThrown() {
    String message = "Test exception throw";
    try {
      throw new JsonIOException(message);
    } catch (JsonIOException e) {
      assertThat(e.getMessage()).isEqualTo(message);
    }
  }

  @Test
  public void testExceptionCanBeCaughtAsJsonParseException() {
    try {
      throw new JsonIOException("test");
    } catch (JsonParseException e) {
      assertThat(e).isInstanceOf(JsonIOException.class);
    }
  }

  @Test
  public void testExceptionCanBeCaughtAsRuntimeException() {
    try {
      throw new JsonIOException("test");
    } catch (RuntimeException e) {
      assertThat(e).isInstanceOf(JsonIOException.class);
    }
  }

  @Test
  public void testExceptionPreservesStackTrace() {
    JsonIOException exception = new JsonIOException("test");
    StackTraceElement[] stackTrace = exception.getStackTrace();

    assertThat(stackTrace).isNotEmpty();
    assertThat(stackTrace[0].getMethodName()).isEqualTo("testExceptionPreservesStackTrace");
  }
}
