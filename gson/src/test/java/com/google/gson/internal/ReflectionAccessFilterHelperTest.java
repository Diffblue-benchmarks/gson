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

import com.google.gson.ReflectionAccessFilter;
import com.google.gson.ReflectionAccessFilter.FilterResult;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

public final class ReflectionAccessFilterHelperTest {

  @Test
  public void testIsJavaTypeWithJavaPrefix() {
    assertThat(ReflectionAccessFilterHelper.isJavaType(String.class)).isTrue();
  }

  @Test
  public void testIsJavaTypeWithJavaxPrefix() {
    assertThat(ReflectionAccessFilterHelper.isJavaType(javax.net.SocketFactory.class)).isTrue();
  }

  @Test
  public void testIsJavaTypeWithNonJavaType() {
    assertThat(ReflectionAccessFilterHelper.isJavaType(ReflectionAccessFilterHelperTest.class))
        .isFalse();
  }

  @Test
  public void testIsAndroidTypeWithJavaType() {
    assertThat(ReflectionAccessFilterHelper.isAndroidType(String.class)).isTrue();
  }

  @Test
  public void testIsAndroidTypeWithJavaxType() {
    assertThat(ReflectionAccessFilterHelper.isAndroidType(javax.net.SocketFactory.class)).isTrue();
  }

  @Test
  public void testIsAndroidTypeWithNonPlatformType() {
    assertThat(
            ReflectionAccessFilterHelper.isAndroidType(ReflectionAccessFilterHelperTest.class))
        .isFalse();
  }

  @Test
  public void testIsAnyPlatformTypeWithJavaType() {
    assertThat(ReflectionAccessFilterHelper.isAnyPlatformType(String.class)).isTrue();
  }

  @Test
  public void testIsAnyPlatformTypeWithNonPlatformType() {
    assertThat(
            ReflectionAccessFilterHelper.isAnyPlatformType(
                ReflectionAccessFilterHelperTest.class))
        .isFalse();
  }

  @Test
  public void testGetFilterResultEmptyList() {
    FilterResult result =
        ReflectionAccessFilterHelper.getFilterResult(
            Collections.<ReflectionAccessFilter>emptyList(), String.class);
    assertThat(result).isEqualTo(FilterResult.ALLOW);
  }

  @Test
  public void testGetFilterResultAllIndecisive() {
    List<ReflectionAccessFilter> filters =
        Collections.<ReflectionAccessFilter>singletonList(
            new ReflectionAccessFilter() {
              @Override
              public FilterResult check(Class<?> rawClass) {
                return FilterResult.INDECISIVE;
              }
            });
    FilterResult result = ReflectionAccessFilterHelper.getFilterResult(filters, String.class);
    assertThat(result).isEqualTo(FilterResult.ALLOW);
  }

  @Test
  public void testGetFilterResultNonIndecisiveResult() {
    List<ReflectionAccessFilter> filters =
        Collections.<ReflectionAccessFilter>singletonList(
            new ReflectionAccessFilter() {
              @Override
              public FilterResult check(Class<?> rawClass) {
                return FilterResult.BLOCK_ALL;
              }
            });
    FilterResult result = ReflectionAccessFilterHelper.getFilterResult(filters, String.class);
    assertThat(result).isEqualTo(FilterResult.BLOCK_ALL);
  }

  @Test
  public void testGetFilterResultFirstNonIndecisiveWins() {
    List<ReflectionAccessFilter> filters =
        Arrays.<ReflectionAccessFilter>asList(
            new ReflectionAccessFilter() {
              @Override
              public FilterResult check(Class<?> rawClass) {
                return FilterResult.INDECISIVE;
              }
            },
            new ReflectionAccessFilter() {
              @Override
              public FilterResult check(Class<?> rawClass) {
                return FilterResult.BLOCK_ALL;
              }
            });
    FilterResult result = ReflectionAccessFilterHelper.getFilterResult(filters, String.class);
    assertThat(result).isEqualTo(FilterResult.BLOCK_ALL);
  }

  @Test
  public void testCanAccessPublicStaticField() throws Exception {
    Field field = Integer.class.getDeclaredField("MAX_VALUE");
    assertThat(ReflectionAccessFilterHelper.canAccess(field, null)).isTrue();
  }
}
