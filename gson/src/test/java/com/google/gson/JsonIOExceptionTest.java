/*
 * Copyright (C) 2026 Google Inc.
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

public class JsonIOExceptionTest {

  @Test
  public void testConstructorWithMessage() {
    JsonIOException exception = new JsonIOException("test message");
    assertThat(exception).isNotNull();
    assertThat(exception.getMessage()).isEqualTo("test message");
  }

  @Test
  public void testConstructorWithMessageAndCause() {
    IOException cause = new IOException("IO error");
    JsonIOException exception = new JsonIOException("test message", cause);
    assertThat(exception).isNotNull();
    assertThat(exception.getMessage()).isEqualTo("test message");
    assertThat(exception.getCause()).isSameInstanceAs(cause);
  }

  @Test
  public void testConstructorWithCause() {
    IOException cause = new IOException("IO error");
    JsonIOException exception = new JsonIOException(cause);
    assertThat(exception).isNotNull();
    assertThat(exception.getCause()).isSameInstanceAs(cause);
  }
}
