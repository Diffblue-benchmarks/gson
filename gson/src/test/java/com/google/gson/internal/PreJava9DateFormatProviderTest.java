/*
 * Copyright (C) 2017 The Gson authors
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
package com.google.gson.internal;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import java.text.DateFormat;
import org.junit.Test;

/** Unit tests for {@link PreJava9DateFormatProvider} */
public final class PreJava9DateFormatProviderTest {

  @Test
  public void testGetUsDateTimeFormatWithInvalidDateStyle() {
    int invalidDateStyle = -1;
    int validTimeStyle = DateFormat.SHORT;

    IllegalArgumentException e =
        assertThrows(
            IllegalArgumentException.class,
            () -> PreJava9DateFormatProvider.getUsDateTimeFormat(invalidDateStyle, validTimeStyle));
    assertThat(e).hasMessageThat().contains("Unknown DateFormat style: " + invalidDateStyle);
  }

  @Test
  public void testGetUsDateTimeFormatWithInvalidTimeStyle() {
    int validDateStyle = DateFormat.SHORT;
    int invalidTimeStyle = 99;

    IllegalArgumentException e =
        assertThrows(
            IllegalArgumentException.class,
            () -> PreJava9DateFormatProvider.getUsDateTimeFormat(validDateStyle, invalidTimeStyle));
    assertThat(e).hasMessageThat().contains("Unknown DateFormat style: " + invalidTimeStyle);
  }
}
