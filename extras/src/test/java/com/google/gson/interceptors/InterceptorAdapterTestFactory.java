/*
 * Copyright (C) 2024 Google Inc.
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
package com.google.gson.interceptors;

import com.diffblue.cover.annotations.InterestingTestFactory;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

/** Test factory for creating InterceptorAdapter instances for Cover test generation. */
public class InterceptorAdapterTestFactory {

  /** Creates a simple JsonPostDeserializer implementation for testing. */
  public static class SimplePostDeserializer implements JsonPostDeserializer<String> {
    @Override
    public void postDeserialize(String object) {
      // Simple no-op implementation for testing
    }
  }

  /** Creates a simple Intercept annotation implementation for testing. */
  @Intercept(postDeserialize = SimplePostDeserializer.class)
  public static class SimpleInterceptedClass {
    private String value;

    public SimpleInterceptedClass() {}

    public SimpleInterceptedClass(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

  /**
   * Factory method to create an InterceptorAdapter for testing. This creates an adapter with valid
   * delegate and intercept annotation.
   */
  @InterestingTestFactory
  public static InterceptorFactory.InterceptorAdapter<String> createInterceptorAdapter() {
    TypeAdapter<String> delegate =
        new TypeAdapter<String>() {
          @Override
          public void write(JsonWriter out, String value) throws IOException {
            out.value(value);
          }

          @Override
          public String read(JsonReader in) throws IOException {
            return in.nextString();
          }
        };

    Intercept intercept = SimpleInterceptedClass.class.getAnnotation(Intercept.class);
    return new InterceptorFactory.InterceptorAdapter<>(delegate, intercept);
  }
}
