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

public final class JsonParseExceptionTest {

  @Test
  public void testConstructorWithMessage() {
    JsonParseException e = new JsonParseException("test error");
    assertThat(e.getMessage()).isEqualTo("test error");
  }

  @Test
  public void testConstructorWithMessageAndCause() {
    Throwable cause = new RuntimeException("cause");
    JsonParseException e = new JsonParseException("test error", cause);
    assertThat(e.getMessage()).isEqualTo("test error");
    assertThat(e.getCause()).isEqualTo(cause);
  }

  @Test
  public void testConstructorWithCause() {
    Throwable cause = new RuntimeException("cause message");
    JsonParseException e = new JsonParseException(cause);
    assertThat(e.getCause()).isEqualTo(cause);
  }
}
