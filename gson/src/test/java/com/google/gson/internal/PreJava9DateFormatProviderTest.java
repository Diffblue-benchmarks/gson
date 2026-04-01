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

package com.google.gson.internal;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.junit.Test;

/** Unit tests for {@link PreJava9DateFormatProvider}. */
public final class PreJava9DateFormatProviderTest {

  @Test
  public void testGetUsDateTimeFormatShortShort() {
    DateFormat format = PreJava9DateFormatProvider.getUsDateTimeFormat(DateFormat.SHORT, DateFormat.SHORT);

    assertThat(((SimpleDateFormat) format).toPattern()).isEqualTo("M/d/yy h:mm a");
  }

  @Test
  public void testGetUsDateTimeFormatMediumMedium() {
    DateFormat format = PreJava9DateFormatProvider.getUsDateTimeFormat(DateFormat.MEDIUM, DateFormat.MEDIUM);

    assertThat(((SimpleDateFormat) format).toPattern()).isEqualTo("MMM d, yyyy h:mm:ss a");
  }

  @Test
  public void testGetUsDateTimeFormatLongLong() {
    DateFormat format = PreJava9DateFormatProvider.getUsDateTimeFormat(DateFormat.LONG, DateFormat.LONG);

    assertThat(((SimpleDateFormat) format).toPattern()).isEqualTo("MMMM d, yyyy h:mm:ss a z");
  }

  @Test
  public void testGetUsDateTimeFormatFullFull() {
    DateFormat format = PreJava9DateFormatProvider.getUsDateTimeFormat(DateFormat.FULL, DateFormat.FULL);

    assertThat(((SimpleDateFormat) format).toPattern()).isEqualTo("EEEE, MMMM d, yyyy h:mm:ss a z");
  }

  @Test
  public void testGetUsDateTimeFormatLongFull() {
    DateFormat format = PreJava9DateFormatProvider.getUsDateTimeFormat(DateFormat.LONG, DateFormat.FULL);

    assertThat(((SimpleDateFormat) format).toPattern()).isEqualTo("MMMM d, yyyy h:mm:ss a z");
  }

  @Test
  public void testGetUsDateTimeFormatShortMedium() {
    DateFormat format = PreJava9DateFormatProvider.getUsDateTimeFormat(DateFormat.SHORT, DateFormat.MEDIUM);

    assertThat(((SimpleDateFormat) format).toPattern()).isEqualTo("M/d/yy h:mm:ss a");
  }

  @Test
  public void testGetUsDateTimeFormatUnknownDateStyleThrows() {
    assertThrows(
        IllegalArgumentException.class,
        () -> PreJava9DateFormatProvider.getUsDateTimeFormat(999, DateFormat.SHORT));
  }

  @Test
  public void testGetUsDateTimeFormatUnknownTimeStyleThrows() {
    assertThrows(
        IllegalArgumentException.class,
        () -> PreJava9DateFormatProvider.getUsDateTimeFormat(DateFormat.SHORT, 999));
  }
}
