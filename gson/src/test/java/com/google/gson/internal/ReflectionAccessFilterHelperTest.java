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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class ReflectionAccessFilterHelperTest {

  @Test
  public void testIsJavaType_withJavaLangClass() {
    assertThat(ReflectionAccessFilterHelper.isJavaType(String.class)).isTrue();
  }

  @Test
  public void testIsJavaType_withJavaUtilClass() {
    assertThat(ReflectionAccessFilterHelper.isJavaType(java.util.ArrayList.class)).isTrue();
  }

  @Test
  public void testIsJavaType_withJavaxClass() {
    assertThat(ReflectionAccessFilterHelper.isJavaType(javax.crypto.Cipher.class)).isTrue();
  }

  @Test
  public void testIsJavaType_withNonJavaClass() {
    assertThat(ReflectionAccessFilterHelper.isJavaType(ReflectionAccessFilterHelper.class)).isFalse();
  }

  @Test
  public void testIsAndroidType_withNonAndroidNonJavaClass() {
    assertThat(ReflectionAccessFilterHelper.isAndroidType(ReflectionAccessFilterHelper.class)).isFalse();
  }

  @Test
  public void testIsAndroidType_withJavaClass() {
    assertThat(ReflectionAccessFilterHelper.isAndroidType(String.class)).isTrue();
  }

  @Test
  public void testIsAnyPlatformType_withJavaClass() {
    assertThat(ReflectionAccessFilterHelper.isAnyPlatformType(String.class)).isTrue();
  }

  @Test
  public void testIsAnyPlatformType_withJavaxClass() {
    assertThat(ReflectionAccessFilterHelper.isAnyPlatformType(javax.crypto.Cipher.class)).isTrue();
  }

  @Test
  public void testIsAnyPlatformType_withNonPlatformClass() {
    assertThat(ReflectionAccessFilterHelper.isAnyPlatformType(ReflectionAccessFilterHelper.class)).isFalse();
  }

  @Test
  public void testGetFilterResult_withEmptyList() {
    List<ReflectionAccessFilter> filters = new ArrayList<ReflectionAccessFilter>();
    FilterResult result = ReflectionAccessFilterHelper.getFilterResult(filters, String.class);
    assertThat(result).isEqualTo(FilterResult.ALLOW);
  }

  @Test
  public void testGetFilterResult_withAllIndecisive() {
    List<ReflectionAccessFilter> filters = Arrays.asList(
        new ReflectionAccessFilter() {
          @Override
          public FilterResult check(Class<?> rawClass) {
            return FilterResult.INDECISIVE;
          }
        },
        new ReflectionAccessFilter() {
          @Override
          public FilterResult check(Class<?> rawClass) {
            return FilterResult.INDECISIVE;
          }
        }
    );
    FilterResult result = ReflectionAccessFilterHelper.getFilterResult(filters, String.class);
    assertThat(result).isEqualTo(FilterResult.ALLOW);
  }

  @Test
  public void testGetFilterResult_withBlockAll() {
    List<ReflectionAccessFilter> filters = Arrays.asList(
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
        }
    );
    FilterResult result = ReflectionAccessFilterHelper.getFilterResult(filters, String.class);
    assertThat(result).isEqualTo(FilterResult.BLOCK_ALL);
  }

  @Test
  public void testGetFilterResult_withBlockInaccessible() {
    List<ReflectionAccessFilter> filters = Arrays.asList(
        new ReflectionAccessFilter() {
          @Override
          public FilterResult check(Class<?> rawClass) {
            return FilterResult.BLOCK_INACCESSIBLE;
          }
        }
    );
    FilterResult result = ReflectionAccessFilterHelper.getFilterResult(filters, String.class);
    assertThat(result).isEqualTo(FilterResult.BLOCK_INACCESSIBLE);
  }

  @Test
  public void testGetFilterResult_stopsAtFirstDecisiveResult() {
    List<ReflectionAccessFilter> filters = Arrays.asList(
        new ReflectionAccessFilter() {
          @Override
          public FilterResult check(Class<?> rawClass) {
            return FilterResult.BLOCK_ALL;
          }
        },
        new ReflectionAccessFilter() {
          @Override
          public FilterResult check(Class<?> rawClass) {
            throw new AssertionError("Should not be called");
          }
        }
    );
    FilterResult result = ReflectionAccessFilterHelper.getFilterResult(filters, String.class);
    assertThat(result).isEqualTo(FilterResult.BLOCK_ALL);
  }

  @Test
  public void testCanAccess_withAccessibleField() throws Exception {
    TestClass instance = new TestClass();
    Field publicField = TestClass.class.getDeclaredField("publicField");
    boolean canAccess = ReflectionAccessFilterHelper.canAccess(publicField, instance);
    assertThat(canAccess).isTrue();
  }

  private static class TestClass {
    public String publicField = "test";
  }
}
