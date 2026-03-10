/*
 * Copyright (C) 2022 Google Inc.
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

import android.test.StubAndroidClass;
import androidx.test.StubAndroidXClass;
import kotlin.test.StubKotlinClass;
import kotlinx.test.StubKotlinXClass;
import org.junit.Test;
import scala.test.StubScalaClass;

/** Unit tests for {@link ReflectionAccessFilterHelper}. */
public class ReflectionAccessFilterHelperTest {

  @Test
  public void testIsAndroidTypeWithAndroidClass() {
    assertThat(ReflectionAccessFilterHelper.isAndroidType(StubAndroidClass.class)).isTrue();
  }

  @Test
  public void testIsAndroidTypeWithAndroidXClass() {
    assertThat(ReflectionAccessFilterHelper.isAndroidType(StubAndroidXClass.class)).isTrue();
  }

  @Test
  public void testIsAndroidTypeWithJavaClass() {
    assertThat(ReflectionAccessFilterHelper.isAndroidType(String.class)).isTrue();
  }

  @Test
  public void testIsAndroidTypeWithJavaxClass() {
    assertThat(ReflectionAccessFilterHelper.isAndroidType(javax.xml.parsers.DocumentBuilder.class))
        .isTrue();
  }

  @Test
  public void testIsAndroidTypeWithNonPlatformClass() {
    assertThat(ReflectionAccessFilterHelper.isAndroidType(ReflectionAccessFilterHelperTest.class))
        .isFalse();
  }

  @Test
  public void testIsAnyPlatformTypeWithJavaClass() {
    assertThat(ReflectionAccessFilterHelper.isAnyPlatformType(String.class)).isTrue();
  }

  @Test
  public void testIsAnyPlatformTypeWithAndroidClass() {
    assertThat(ReflectionAccessFilterHelper.isAnyPlatformType(StubAndroidClass.class)).isTrue();
  }

  @Test
  public void testIsAnyPlatformTypeWithKotlinClass() {
    assertThat(ReflectionAccessFilterHelper.isAnyPlatformType(StubKotlinClass.class)).isTrue();
  }

  @Test
  public void testIsAnyPlatformTypeWithKotlinXClass() {
    assertThat(ReflectionAccessFilterHelper.isAnyPlatformType(StubKotlinXClass.class)).isTrue();
  }

  @Test
  public void testIsAnyPlatformTypeWithScalaClass() {
    assertThat(ReflectionAccessFilterHelper.isAnyPlatformType(StubScalaClass.class)).isTrue();
  }

  @Test
  public void testIsAnyPlatformTypeWithNonPlatformClass() {
    assertThat(ReflectionAccessFilterHelper.isAnyPlatformType(ReflectionAccessFilterHelperTest.class))
        .isFalse();
  }
}
