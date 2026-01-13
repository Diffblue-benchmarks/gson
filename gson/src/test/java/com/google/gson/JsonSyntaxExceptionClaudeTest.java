/*
 * Copyright (C) 2010 Google Inc.
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
 * Unit tests for {@link JsonSyntaxException}.
 *
 * @author Claude
 */
public class JsonSyntaxExceptionClaudeTest {

  // ========== Constructor <init>.(Ljava/lang/String;)V Tests ==========

  @Test
  public void testStringConstructorWithMessage() {
    String message = "Malformed JSON at line 5";
    JsonSyntaxException exception = new JsonSyntaxException(message);

    assertThat(exception.getMessage()).isEqualTo(message);
    assertThat(exception.getCause()).isNull();
  }

  @Test
  public void testStringConstructorWithNullMessage() {
    JsonSyntaxException exception = new JsonSyntaxException((String) null);

    assertThat(exception.getMessage()).isNull();
    assertThat(exception.getCause()).isNull();
  }

  @Test
  public void testStringConstructorWithEmptyMessage() {
    JsonSyntaxException exception = new JsonSyntaxException("");

    assertThat(exception.getMessage()).isEmpty();
    assertThat(exception.getCause()).isNull();
  }

  @Test
  public void testStringConstructorExtendsJsonParseException() {
    JsonSyntaxException exception = new JsonSyntaxException("test");

    assertThat(exception).isInstanceOf(JsonParseException.class);
    assertThat(exception).isInstanceOf(RuntimeException.class);
  }

  // ========== Constructor <init>.(Ljava/lang/String;Ljava/lang/Throwable;)V Tests ==========

  @Test
  public void testStringThrowableConstructorWithMessageAndCause() {
    String message = "Invalid JSON syntax";
    Throwable cause = new IllegalStateException("Unexpected token");
    JsonSyntaxException exception = new JsonSyntaxException(message, cause);

    assertThat(exception.getMessage()).isEqualTo(message);
    assertThat(exception.getCause()).isSameInstanceAs(cause);
  }

  @Test
  public void testStringThrowableConstructorWithNullMessage() {
    Throwable cause = new IllegalStateException("Unexpected token");
    JsonSyntaxException exception = new JsonSyntaxException(null, cause);

    assertThat(exception.getMessage()).isNull();
    assertThat(exception.getCause()).isSameInstanceAs(cause);
  }

  @Test
  public void testStringThrowableConstructorWithNullCause() {
    String message = "Invalid JSON syntax";
    JsonSyntaxException exception = new JsonSyntaxException(message, null);

    assertThat(exception.getMessage()).isEqualTo(message);
    assertThat(exception.getCause()).isNull();
  }

  @Test
  public void testStringThrowableConstructorWithBothNull() {
    JsonSyntaxException exception = new JsonSyntaxException(null, null);

    assertThat(exception.getMessage()).isNull();
    assertThat(exception.getCause()).isNull();
  }

  @Test
  public void testStringThrowableConstructorWithNestedCause() {
    NumberFormatException rootCause = new NumberFormatException("Not a number");
    IllegalStateException intermediateCause =
        new IllegalStateException("Parse error", rootCause);
    JsonSyntaxException exception =
        new JsonSyntaxException("JSON syntax error", intermediateCause);

    assertThat(exception.getMessage()).isEqualTo("JSON syntax error");
    assertThat(exception.getCause()).isSameInstanceAs(intermediateCause);
    assertThat(exception.getCause().getCause()).isSameInstanceAs(rootCause);
  }

  // ========== Constructor <init>.(Ljava/lang/Throwable;)V Tests ==========

  @Test
  public void testThrowableConstructorWithCause() {
    Throwable cause = new IllegalStateException("Parse failure");
    JsonSyntaxException exception = new JsonSyntaxException(cause);

    // When using Throwable-only constructor, message is derived from cause
    assertThat(exception.getCause()).isSameInstanceAs(cause);
    assertThat(exception.getMessage()).contains("IllegalStateException");
  }

  @Test
  public void testThrowableConstructorWithNullCause() {
    JsonSyntaxException exception = new JsonSyntaxException((Throwable) null);

    assertThat(exception.getCause()).isNull();
    assertThat(exception.getMessage()).isNull();
  }

  @Test
  public void testThrowableConstructorWithCauseHavingMessage() {
    String causeMessage = "Expected ':' but got '}' at line 10, column 5";
    Throwable cause = new IllegalStateException(causeMessage);
    JsonSyntaxException exception = new JsonSyntaxException(cause);

    assertThat(exception.getCause()).isSameInstanceAs(cause);
    // Message contains cause's class name and message
    assertThat(exception.getMessage()).contains(causeMessage);
  }

  @Test
  public void testThrowableConstructorWithCauseHavingNullMessage() {
    Throwable cause = new IllegalStateException();
    JsonSyntaxException exception = new JsonSyntaxException(cause);

    assertThat(exception.getCause()).isSameInstanceAs(cause);
    // Message should still contain the cause's class name
    assertThat(exception.getMessage()).contains("IllegalStateException");
  }

  // ========== Exception behavior tests ==========

  @Test
  public void testExceptionCanBeThrown() {
    String message = "Test syntax exception throw";
    try {
      throw new JsonSyntaxException(message);
    } catch (JsonSyntaxException e) {
      assertThat(e.getMessage()).isEqualTo(message);
    }
  }

  @Test
  public void testExceptionCanBeCaughtAsJsonParseException() {
    try {
      throw new JsonSyntaxException("test");
    } catch (JsonParseException e) {
      assertThat(e).isInstanceOf(JsonSyntaxException.class);
    }
  }

  @Test
  public void testExceptionCanBeCaughtAsRuntimeException() {
    try {
      throw new JsonSyntaxException("test");
    } catch (RuntimeException e) {
      assertThat(e).isInstanceOf(JsonSyntaxException.class);
    }
  }

  @Test
  public void testExceptionPreservesStackTrace() {
    JsonSyntaxException exception = new JsonSyntaxException("test");
    StackTraceElement[] stackTrace = exception.getStackTrace();

    assertThat(stackTrace).isNotEmpty();
    assertThat(stackTrace[0].getMethodName()).isEqualTo("testExceptionPreservesStackTrace");
  }
}
