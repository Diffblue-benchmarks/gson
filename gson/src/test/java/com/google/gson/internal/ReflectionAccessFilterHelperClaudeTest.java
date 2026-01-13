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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.google.gson.ReflectionAccessFilter;
import com.google.gson.ReflectionAccessFilter.FilterResult;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

/** Tests for {@link ReflectionAccessFilterHelper}. */
public class ReflectionAccessFilterHelperClaudeTest {

  // ==========================================================================
  // isJavaType tests
  // ==========================================================================

  @Test
  public void isJavaType_withJavaLangClass_returnsTrue() {
    assertTrue(ReflectionAccessFilterHelper.isJavaType(String.class));
  }

  @Test
  public void isJavaType_withJavaUtilClass_returnsTrue() {
    assertTrue(ReflectionAccessFilterHelper.isJavaType(ArrayList.class));
  }

  @Test
  public void isJavaType_withJavaIoClass_returnsTrue() {
    assertTrue(ReflectionAccessFilterHelper.isJavaType(java.io.File.class));
  }

  @Test
  public void isJavaType_withJavaxClass_returnsTrue() {
    // javax.crypto.Cipher is a common javax class
    assertTrue(ReflectionAccessFilterHelper.isJavaType(javax.crypto.Cipher.class));
  }

  @Test
  public void isJavaType_withCustomClass_returnsFalse() {
    // This test class itself is not a Java platform type
    assertFalse(ReflectionAccessFilterHelper.isJavaType(ReflectionAccessFilterHelperClaudeTest.class));
  }

  @Test
  public void isJavaType_withGsonClass_returnsFalse() {
    assertFalse(ReflectionAccessFilterHelper.isJavaType(ReflectionAccessFilterHelper.class));
  }

  @Test
  public void isJavaType_withPrimitiveClass_returnsFalse() {
    // Primitives don't start with java. or javax.
    assertFalse(ReflectionAccessFilterHelper.isJavaType(int.class));
  }

  @Test
  public void isJavaType_withArrayOfJavaClass_returnsFalse() {
    // Arrays have names like "[Ljava.lang.String;" which don't start with "java."
    assertFalse(ReflectionAccessFilterHelper.isJavaType(String[].class));
  }

  // ==========================================================================
  // isAndroidType tests
  // ==========================================================================

  @Test
  public void isAndroidType_withJavaLangClass_returnsTrue() {
    // Java types are also considered Android types
    assertTrue(ReflectionAccessFilterHelper.isAndroidType(String.class));
  }

  @Test
  public void isAndroidType_withJavaxClass_returnsTrue() {
    assertTrue(ReflectionAccessFilterHelper.isAndroidType(javax.crypto.Cipher.class));
  }

  @Test
  public void isAndroidType_withCustomClass_returnsFalse() {
    assertFalse(ReflectionAccessFilterHelper.isAndroidType(ReflectionAccessFilterHelperClaudeTest.class));
  }

  @Test
  public void isAndroidType_withGsonClass_returnsFalse() {
    assertFalse(ReflectionAccessFilterHelper.isAndroidType(ReflectionAccessFilterHelper.class));
  }

  @Test
  public void isAndroidType_withPrimitiveClass_returnsFalse() {
    assertFalse(ReflectionAccessFilterHelper.isAndroidType(int.class));
  }

  // ==========================================================================
  // isAnyPlatformType tests
  // ==========================================================================

  @Test
  public void isAnyPlatformType_withJavaLangClass_returnsTrue() {
    assertTrue(ReflectionAccessFilterHelper.isAnyPlatformType(String.class));
  }

  @Test
  public void isAnyPlatformType_withJavaxClass_returnsTrue() {
    assertTrue(ReflectionAccessFilterHelper.isAnyPlatformType(javax.crypto.Cipher.class));
  }

  @Test
  public void isAnyPlatformType_withCustomClass_returnsFalse() {
    assertFalse(ReflectionAccessFilterHelper.isAnyPlatformType(ReflectionAccessFilterHelperClaudeTest.class));
  }

  @Test
  public void isAnyPlatformType_withGsonClass_returnsFalse() {
    assertFalse(ReflectionAccessFilterHelper.isAnyPlatformType(ReflectionAccessFilterHelper.class));
  }

  @Test
  public void isAnyPlatformType_withPrimitiveClass_returnsFalse() {
    assertFalse(ReflectionAccessFilterHelper.isAnyPlatformType(int.class));
  }

  // ==========================================================================
  // getFilterResult tests
  // ==========================================================================

  @Test
  public void getFilterResult_withEmptyList_returnsAllow() {
    List<ReflectionAccessFilter> filters = Collections.emptyList();
    FilterResult result = ReflectionAccessFilterHelper.getFilterResult(filters, String.class);
    assertEquals(FilterResult.ALLOW, result);
  }

  @Test
  public void getFilterResult_withSingleAllowFilter_returnsAllow() {
    ReflectionAccessFilter allowFilter = rawClass -> FilterResult.ALLOW;
    List<ReflectionAccessFilter> filters = Collections.singletonList(allowFilter);
    FilterResult result = ReflectionAccessFilterHelper.getFilterResult(filters, String.class);
    assertEquals(FilterResult.ALLOW, result);
  }

  @Test
  public void getFilterResult_withSingleBlockAllFilter_returnsBlockAll() {
    ReflectionAccessFilter blockAllFilter = rawClass -> FilterResult.BLOCK_ALL;
    List<ReflectionAccessFilter> filters = Collections.singletonList(blockAllFilter);
    FilterResult result = ReflectionAccessFilterHelper.getFilterResult(filters, String.class);
    assertEquals(FilterResult.BLOCK_ALL, result);
  }

  @Test
  public void getFilterResult_withSingleBlockInaccessibleFilter_returnsBlockInaccessible() {
    ReflectionAccessFilter blockInaccessibleFilter = rawClass -> FilterResult.BLOCK_INACCESSIBLE;
    List<ReflectionAccessFilter> filters = Collections.singletonList(blockInaccessibleFilter);
    FilterResult result = ReflectionAccessFilterHelper.getFilterResult(filters, String.class);
    assertEquals(FilterResult.BLOCK_INACCESSIBLE, result);
  }

  @Test
  public void getFilterResult_withSingleIndecisiveFilter_returnsAllow() {
    ReflectionAccessFilter indecisiveFilter = rawClass -> FilterResult.INDECISIVE;
    List<ReflectionAccessFilter> filters = Collections.singletonList(indecisiveFilter);
    FilterResult result = ReflectionAccessFilterHelper.getFilterResult(filters, String.class);
    assertEquals(FilterResult.ALLOW, result);
  }

  @Test
  public void getFilterResult_withAllIndecisiveFilters_returnsAllow() {
    ReflectionAccessFilter indecisiveFilter1 = rawClass -> FilterResult.INDECISIVE;
    ReflectionAccessFilter indecisiveFilter2 = rawClass -> FilterResult.INDECISIVE;
    List<ReflectionAccessFilter> filters = Arrays.asList(indecisiveFilter1, indecisiveFilter2);
    FilterResult result = ReflectionAccessFilterHelper.getFilterResult(filters, String.class);
    assertEquals(FilterResult.ALLOW, result);
  }

  @Test
  public void getFilterResult_withIndecisiveThenBlockAll_returnsBlockAll() {
    ReflectionAccessFilter indecisiveFilter = rawClass -> FilterResult.INDECISIVE;
    ReflectionAccessFilter blockAllFilter = rawClass -> FilterResult.BLOCK_ALL;
    List<ReflectionAccessFilter> filters = Arrays.asList(indecisiveFilter, blockAllFilter);
    FilterResult result = ReflectionAccessFilterHelper.getFilterResult(filters, String.class);
    assertEquals(FilterResult.BLOCK_ALL, result);
  }

  @Test
  public void getFilterResult_withBlockAllThenIndecisive_returnsBlockAll() {
    ReflectionAccessFilter blockAllFilter = rawClass -> FilterResult.BLOCK_ALL;
    ReflectionAccessFilter indecisiveFilter = rawClass -> FilterResult.INDECISIVE;
    List<ReflectionAccessFilter> filters = Arrays.asList(blockAllFilter, indecisiveFilter);
    FilterResult result = ReflectionAccessFilterHelper.getFilterResult(filters, String.class);
    assertEquals(FilterResult.BLOCK_ALL, result);
  }

  @Test
  public void getFilterResult_firstNonIndecisiveWins() {
    ReflectionAccessFilter indecisiveFilter = rawClass -> FilterResult.INDECISIVE;
    ReflectionAccessFilter allowFilter = rawClass -> FilterResult.ALLOW;
    ReflectionAccessFilter blockAllFilter = rawClass -> FilterResult.BLOCK_ALL;
    List<ReflectionAccessFilter> filters = Arrays.asList(indecisiveFilter, allowFilter, blockAllFilter);
    FilterResult result = ReflectionAccessFilterHelper.getFilterResult(filters, String.class);
    assertEquals(FilterResult.ALLOW, result);
  }

  @Test
  public void getFilterResult_withClassSpecificFilter_usesCorrectClass() {
    // Filter that returns BLOCK_ALL only for String class
    ReflectionAccessFilter stringBlockFilter = rawClass ->
        rawClass == String.class ? FilterResult.BLOCK_ALL : FilterResult.INDECISIVE;
    List<ReflectionAccessFilter> filters = Collections.singletonList(stringBlockFilter);

    assertEquals(FilterResult.BLOCK_ALL,
        ReflectionAccessFilterHelper.getFilterResult(filters, String.class));
    assertEquals(FilterResult.ALLOW,
        ReflectionAccessFilterHelper.getFilterResult(filters, Integer.class));
  }

  @Test
  public void getFilterResult_withPredefinedBlockAllJavaFilter_blocksJavaTypes() {
    List<ReflectionAccessFilter> filters = Collections.singletonList(ReflectionAccessFilter.BLOCK_ALL_JAVA);

    // Java types should be blocked
    assertEquals(FilterResult.BLOCK_ALL,
        ReflectionAccessFilterHelper.getFilterResult(filters, String.class));
    // Non-Java types should be allowed (filter returns INDECISIVE, which becomes ALLOW)
    assertEquals(FilterResult.ALLOW,
        ReflectionAccessFilterHelper.getFilterResult(filters, ReflectionAccessFilterHelper.class));
  }

  @Test
  public void getFilterResult_withPredefinedBlockAllAndroidFilter_blocksJavaTypes() {
    List<ReflectionAccessFilter> filters = Collections.singletonList(ReflectionAccessFilter.BLOCK_ALL_ANDROID);

    // Java types should be blocked (they're also Android types)
    assertEquals(FilterResult.BLOCK_ALL,
        ReflectionAccessFilterHelper.getFilterResult(filters, String.class));
    // Non-Java/Android types should be allowed
    assertEquals(FilterResult.ALLOW,
        ReflectionAccessFilterHelper.getFilterResult(filters, ReflectionAccessFilterHelper.class));
  }

  @Test
  public void getFilterResult_withPredefinedBlockAllPlatformFilter_blocksJavaTypes() {
    List<ReflectionAccessFilter> filters = Collections.singletonList(ReflectionAccessFilter.BLOCK_ALL_PLATFORM);

    // Java types should be blocked
    assertEquals(FilterResult.BLOCK_ALL,
        ReflectionAccessFilterHelper.getFilterResult(filters, String.class));
    // Non-platform types should be allowed
    assertEquals(FilterResult.ALLOW,
        ReflectionAccessFilterHelper.getFilterResult(filters, ReflectionAccessFilterHelper.class));
  }

  // ==========================================================================
  // canAccess tests
  // ==========================================================================

  @Test
  public void canAccess_withPublicMethod_returnsTrue() throws NoSuchMethodException {
    Method publicMethod = String.class.getMethod("length");
    assertTrue(ReflectionAccessFilterHelper.canAccess(publicMethod, "test"));
  }

  @Test
  public void canAccess_withPublicStaticMethod_returnsTrue() throws NoSuchMethodException {
    Method staticMethod = String.class.getMethod("valueOf", int.class);
    assertTrue(ReflectionAccessFilterHelper.canAccess(staticMethod, null));
  }

  @Test
  public void canAccess_withPublicField_returnsTrue() throws NoSuchFieldException {
    // String.CASE_INSENSITIVE_ORDER is a public static field
    Field publicField = String.class.getField("CASE_INSENSITIVE_ORDER");
    assertTrue(ReflectionAccessFilterHelper.canAccess(publicField, null));
  }

  @Test
  public void canAccess_withAccessiblePrivateField_returnsTrue() throws NoSuchFieldException {
    // Get a private field and make it accessible
    Field privateField = TestClassWithPrivateField.class.getDeclaredField("privateField");
    privateField.setAccessible(true);
    TestClassWithPrivateField instance = new TestClassWithPrivateField();
    assertTrue(ReflectionAccessFilterHelper.canAccess(privateField, instance));
  }

  @Test
  public void canAccess_withPublicConstructor_returnsTrue() throws NoSuchMethodException {
    java.lang.reflect.Constructor<?> constructor = String.class.getConstructor();
    assertTrue(ReflectionAccessFilterHelper.canAccess(constructor, null));
  }

  // Helper class for testing private field access
  private static class TestClassWithPrivateField {
    @SuppressWarnings("unused")
    private String privateField = "test";
  }
}
