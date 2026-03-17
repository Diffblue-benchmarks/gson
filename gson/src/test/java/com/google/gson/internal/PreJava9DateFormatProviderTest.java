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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.junit.Test;

public class PreJava9DateFormatProviderTest {

  @Test
  public void testGetUsDateTimeFormatWithShortShort() {
    DateFormat format = PreJava9DateFormatProvider.getUsDateTimeFormat(DateFormat.SHORT, DateFormat.SHORT);
    assertThat(format).isInstanceOf(SimpleDateFormat.class);
    SimpleDateFormat simpleDateFormat = (SimpleDateFormat) format;
    assertThat(simpleDateFormat.toPattern()).isEqualTo("M/d/yy h:mm a");
  }

  @Test
  public void testGetUsDateTimeFormatWithMediumMedium() {
    DateFormat format = PreJava9DateFormatProvider.getUsDateTimeFormat(DateFormat.MEDIUM, DateFormat.MEDIUM);
    assertThat(format).isInstanceOf(SimpleDateFormat.class);
    SimpleDateFormat simpleDateFormat = (SimpleDateFormat) format;
    assertThat(simpleDateFormat.toPattern()).isEqualTo("MMM d, yyyy h:mm:ss a");
  }

  @Test
  public void testGetUsDateTimeFormatWithLongLong() {
    DateFormat format = PreJava9DateFormatProvider.getUsDateTimeFormat(DateFormat.LONG, DateFormat.LONG);
    assertThat(format).isInstanceOf(SimpleDateFormat.class);
    SimpleDateFormat simpleDateFormat = (SimpleDateFormat) format;
    assertThat(simpleDateFormat.toPattern()).isEqualTo("MMMM d, yyyy h:mm:ss a z");
  }

  @Test
  public void testGetUsDateTimeFormatWithFullFull() {
    DateFormat format = PreJava9DateFormatProvider.getUsDateTimeFormat(DateFormat.FULL, DateFormat.FULL);
    assertThat(format).isInstanceOf(SimpleDateFormat.class);
    SimpleDateFormat simpleDateFormat = (SimpleDateFormat) format;
    assertThat(simpleDateFormat.toPattern()).isEqualTo("EEEE, MMMM d, yyyy h:mm:ss a z");
  }

  @Test
  public void testGetUsDateTimeFormatWithShortMedium() {
    DateFormat format = PreJava9DateFormatProvider.getUsDateTimeFormat(DateFormat.SHORT, DateFormat.MEDIUM);
    assertThat(format).isInstanceOf(SimpleDateFormat.class);
    SimpleDateFormat simpleDateFormat = (SimpleDateFormat) format;
    assertThat(simpleDateFormat.toPattern()).isEqualTo("M/d/yy h:mm:ss a");
  }

  @Test
  public void testGetUsDateTimeFormatWithMediumLong() {
    DateFormat format = PreJava9DateFormatProvider.getUsDateTimeFormat(DateFormat.MEDIUM, DateFormat.LONG);
    assertThat(format).isInstanceOf(SimpleDateFormat.class);
    SimpleDateFormat simpleDateFormat = (SimpleDateFormat) format;
    assertThat(simpleDateFormat.toPattern()).isEqualTo("MMM d, yyyy h:mm:ss a z");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetUsDateTimeFormatWithInvalidDateStyle() {
    PreJava9DateFormatProvider.getUsDateTimeFormat(999, DateFormat.SHORT);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetUsDateTimeFormatWithInvalidTimeStyle() {
    PreJava9DateFormatProvider.getUsDateTimeFormat(DateFormat.SHORT, 999);
  }
}
