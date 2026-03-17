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

package com.google.gson;

import static com.google.common.truth.Truth.assertThat;

import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.StringReader;
import org.junit.Test;

public class ToNumberStrategyTest {

  @Test
  public void testReadNumberWithCustomImplementation() throws IOException {
    ToNumberStrategy strategy = new ToNumberStrategy() {
      @Override
      public Number readNumber(JsonReader in) throws IOException {
        return in.nextDouble();
      }
    };

    JsonReader reader = new JsonReader(new StringReader("42.5"));
    Number result = strategy.readNumber(reader);

    assertThat(result).isEqualTo(42.5);
  }

  @Test
  public void testReadNumberWithIntegerValue() throws IOException {
    ToNumberStrategy strategy = new ToNumberStrategy() {
      @Override
      public Number readNumber(JsonReader in) throws IOException {
        return in.nextInt();
      }
    };

    JsonReader reader = new JsonReader(new StringReader("123"));
    Number result = strategy.readNumber(reader);

    assertThat(result).isEqualTo(123);
  }

  @Test
  public void testReadNumberWithLongValue() throws IOException {
    ToNumberStrategy strategy = new ToNumberStrategy() {
      @Override
      public Number readNumber(JsonReader in) throws IOException {
        return in.nextLong();
      }
    };

    JsonReader reader = new JsonReader(new StringReader("9223372036854775807"));
    Number result = strategy.readNumber(reader);

    assertThat(result).isEqualTo(9223372036854775807L);
  }
}
