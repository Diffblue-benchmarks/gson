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

import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;

public final class MalformedJsonExceptionTest {

  @Test
  public void testConstructorWithMessage() {
    MalformedJsonException exception = new MalformedJsonException("test message");

    assertThat(exception.getMessage()).isEqualTo("test message");
  }

  @Test
  public void testConstructorWithMessageAndCause() {
    Throwable cause = new RuntimeException("cause");
    MalformedJsonException exception = new MalformedJsonException("test message", cause);

    assertThat(exception.getMessage()).isEqualTo("test message");
    assertThat(exception.getCause()).isEqualTo(cause);
  }

  @Test
  public void testConstructorWithCause() {
    Throwable cause = new RuntimeException("cause message");
    MalformedJsonException exception = new MalformedJsonException(cause);

    assertThat(exception.getCause()).isEqualTo(cause);
  }
}
