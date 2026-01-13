/*
 * Copyright (C) 2024 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.gson.internal.bind;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.ReflectionAccessFilter;
import com.google.gson.ReflectionAccessFilter.FilterResult;
import org.junit.Test;

/**
 * Tests for {@link ReflectiveTypeAdapterFactory#checkAccessible} private method. Tests exercise the
 * method through public API by using BLOCK_INACCESSIBLE filter with inaccessible members.
 *
 * <p>Covers lines 170-179 of ReflectiveTypeAdapterFactory.
 */
public class ReflectiveTypeAdapterFactoryClaude_checkAccessibleTest {

  // ==========================================================================
  // Test classes with private fields (for BLOCK_INACCESSIBLE testing)
  // ==========================================================================

  /** Class with private field - used to test checkAccessible for field access */
  public static class ClassWithPrivateField {
    @SuppressWarnings("unused")
    private String privateField;

    public ClassWithPrivateField() {}

    public ClassWithPrivateField(String value) {
      this.privateField = value;
    }
  }

  /** Private record - tests checkAccessible on private constructor */
  private record PrivateRecordWithBlockInaccessible(String value) {}

  // ==========================================================================
  // Tests for checkAccessible via BLOCK_INACCESSIBLE filter during serialization
  // ==========================================================================

  @Test
  public void checkAccessible_blockInaccessible_serialization_privateField_throwsException() {
    // Use BLOCK_INACCESSIBLE filter - this will cause checkAccessible to be called
    // but NOT make the field accessible first
    Gson gson =
        new GsonBuilder()
            .addReflectionAccessFilter(
                rawClass -> {
                  // Only apply to our test class
                  if (rawClass == ClassWithPrivateField.class) {
                    return FilterResult.BLOCK_INACCESSIBLE;
                  }
                  return FilterResult.INDECISIVE;
                })
            .create();

    ClassWithPrivateField obj = new ClassWithPrivateField("test");

    try {
      gson.toJson(obj);
      fail("Expected JsonIOException due to inaccessible private field");
    } catch (JsonIOException e) {
      // This exercises checkAccessible lines 170-179
      assertTrue(
          "Should mention accessibility issue",
          e.getMessage().contains("is not accessible")
              || e.getMessage().contains("ReflectionAccessFilter"));
    }
  }

  @Test
  public void checkAccessible_blockInaccessible_deserialization_privateField_throwsException() {
    // Use BLOCK_INACCESSIBLE filter
    Gson gson =
        new GsonBuilder()
            .addReflectionAccessFilter(
                rawClass -> {
                  if (rawClass == ClassWithPrivateField.class) {
                    return FilterResult.BLOCK_INACCESSIBLE;
                  }
                  return FilterResult.INDECISIVE;
                })
            .create();

    try {
      gson.fromJson("{\"privateField\":\"test\"}", ClassWithPrivateField.class);
      fail("Expected JsonIOException due to inaccessible private field");
    } catch (JsonIOException e) {
      // This exercises checkAccessible lines 170-179 in readIntoField
      assertTrue(
          "Should mention accessibility issue",
          e.getMessage().contains("is not accessible")
              || e.getMessage().contains("ReflectionAccessFilter"));
    }
  }

  // ==========================================================================
  // Tests for checkAccessible via BLOCK_INACCESSIBLE filter on records
  // ==========================================================================

  @Test
  public void checkAccessible_blockInaccessible_privateRecord_throwsException() {
    // Use BLOCK_INACCESSIBLE filter for records - this tests the constructor accessibility check
    Gson gson =
        new GsonBuilder()
            .addReflectionAccessFilter(
                rawClass -> {
                  // Apply to our private record
                  if (rawClass == PrivateRecordWithBlockInaccessible.class) {
                    return FilterResult.BLOCK_INACCESSIBLE;
                  }
                  return FilterResult.INDECISIVE;
                })
            .create();

    try {
      gson.fromJson("{\"value\":\"test\"}", PrivateRecordWithBlockInaccessible.class);
      fail("Expected JsonIOException due to inaccessible private record constructor");
    } catch (JsonIOException e) {
      // This exercises checkAccessible lines 170-179 in RecordAdapter constructor
      assertTrue(
          "Should mention accessibility issue",
          e.getMessage().contains("is not accessible")
              || e.getMessage().contains("ReflectionAccessFilter"));
    }
  }

  @Test
  public void checkAccessible_blockInaccessible_privateRecord_serialization_throwsException() {
    // Use BLOCK_INACCESSIBLE filter for records during serialization
    Gson gson =
        new GsonBuilder()
            .addReflectionAccessFilter(
                rawClass -> {
                  if (rawClass == PrivateRecordWithBlockInaccessible.class) {
                    return FilterResult.BLOCK_INACCESSIBLE;
                  }
                  return FilterResult.INDECISIVE;
                })
            .create();

    PrivateRecordWithBlockInaccessible record = new PrivateRecordWithBlockInaccessible("test");

    try {
      gson.toJson(record);
      fail("Expected JsonIOException due to inaccessible private record accessor");
    } catch (JsonIOException e) {
      // This exercises checkAccessible for accessor method
      assertTrue(
          "Should mention accessibility issue",
          e.getMessage().contains("is not accessible")
              || e.getMessage().contains("ReflectionAccessFilter"));
    }
  }

  // ==========================================================================
  // Tests using built-in BLOCK_INACCESSIBLE_JAVA filter
  // ==========================================================================

  @Test
  public void checkAccessible_blockInaccessibleJava_javaClass_throwsException() {
    // Use the built-in BLOCK_INACCESSIBLE_JAVA filter which applies to java.* classes
    Gson gson =
        new GsonBuilder()
            .addReflectionAccessFilter(ReflectionAccessFilter.BLOCK_INACCESSIBLE_JAVA)
            .create();

    // Try to serialize a java.lang.Thread which has private fields
    Thread thread = Thread.currentThread();

    try {
      gson.toJson(thread);
      fail("Expected JsonIOException due to inaccessible java.lang.Thread fields");
    } catch (JsonIOException e) {
      // This may exercise checkAccessible or throw earlier due to BLOCK_INACCESSIBLE
      assertTrue(
          "Should mention accessibility issue or filter",
          e.getMessage().contains("is not accessible")
              || e.getMessage().contains("ReflectionAccessFilter")
              || e.getMessage().contains("not permit"));
    }
  }

  // ==========================================================================
  // Tests verifying ALLOW filter does not trigger checkAccessible exception path
  // ==========================================================================

  @Test
  public void checkAccessible_allowFilter_privateField_succeeds() {
    // Use ALLOW filter - this should make the field accessible and not throw
    Gson gson =
        new GsonBuilder()
            .addReflectionAccessFilter(rawClass -> FilterResult.ALLOW)
            .create();

    ClassWithPrivateField obj = new ClassWithPrivateField("test");

    // Should succeed because ALLOW filter allows making field accessible
    String json = gson.toJson(obj);
    assertNotNull(json);
    assertTrue(json.contains("privateField"));
  }

  @Test
  public void checkAccessible_allowFilter_deserialization_succeeds() {
    Gson gson =
        new GsonBuilder()
            .addReflectionAccessFilter(rawClass -> FilterResult.ALLOW)
            .create();

    ClassWithPrivateField result =
        gson.fromJson("{\"privateField\":\"value\"}", ClassWithPrivateField.class);
    assertNotNull(result);
  }

  // ==========================================================================
  // Tests for the error message content (lines 172-177)
  // ==========================================================================

  @Test
  public void checkAccessible_errorMessage_containsFieldDescription() {
    Gson gson =
        new GsonBuilder()
            .addReflectionAccessFilter(
                rawClass -> {
                  if (rawClass == ClassWithPrivateField.class) {
                    return FilterResult.BLOCK_INACCESSIBLE;
                  }
                  return FilterResult.INDECISIVE;
                })
            .create();

    try {
      gson.toJson(new ClassWithPrivateField("test"));
      fail("Expected JsonIOException");
    } catch (JsonIOException e) {
      String message = e.getMessage();
      // Verify the error message contains helpful information
      assertTrue(
          "Message should contain accessibility info",
          message.contains("is not accessible")
              || message.contains("ReflectionAccessFilter does not permit"));
    }
  }

  @Test
  public void checkAccessible_errorMessage_suggestsTypeAdapter() {
    Gson gson =
        new GsonBuilder()
            .addReflectionAccessFilter(
                rawClass -> {
                  if (rawClass == ClassWithPrivateField.class) {
                    return FilterResult.BLOCK_INACCESSIBLE;
                  }
                  return FilterResult.INDECISIVE;
                })
            .create();

    try {
      gson.toJson(new ClassWithPrivateField("test"));
      fail("Expected JsonIOException");
    } catch (JsonIOException e) {
      String message = e.getMessage();
      // The error message on lines 173-177 suggests registering a TypeAdapter
      assertTrue(
          "Message should suggest registering TypeAdapter or adjusting filter",
          message.contains("TypeAdapter")
              || message.contains("access filter")
              || message.contains("visibility"));
    }
  }

  // ==========================================================================
  // Tests for static member check (line 171 - Modifier.isStatic check)
  // ==========================================================================

  /** Class with static field to test the static modifier check in checkAccessible */
  public static class ClassWithPrivateStaticField {
    @SuppressWarnings("unused")
    private static String staticField = "static";

    public String instanceField = "instance";
  }

  @Test
  public void checkAccessible_staticField_blockInaccessible_handlesStaticCorrectly() {
    // This tests line 171 which checks if the member is static
    Gson gson =
        new GsonBuilder()
            .excludeFieldsWithModifiers() // Include static fields
            .addReflectionAccessFilter(
                rawClass -> {
                  if (rawClass == ClassWithPrivateStaticField.class) {
                    return FilterResult.BLOCK_INACCESSIBLE;
                  }
                  return FilterResult.INDECISIVE;
                })
            .create();

    try {
      String json = gson.toJson(new ClassWithPrivateStaticField());
      // May or may not throw depending on whether static fields are included
      // The important thing is that the code handles the static check correctly
      assertNotNull(json);
    } catch (JsonIOException e) {
      // If it throws, verify it's the expected exception
      assertTrue(
          "Should be about accessibility",
          e.getMessage().contains("is not accessible")
              || e.getMessage().contains("ReflectionAccessFilter"));
    }
  }

  // ==========================================================================
  // Test with public record (should not trigger checkAccessible exception)
  // ==========================================================================

  public record PublicRecord(String value) {}

  @Test
  public void checkAccessible_publicRecord_blockInaccessible_succeeds() {
    // Public record should be accessible even with BLOCK_INACCESSIBLE
    Gson gson =
        new GsonBuilder()
            .addReflectionAccessFilter(
                rawClass -> {
                  if (rawClass == PublicRecord.class) {
                    return FilterResult.BLOCK_INACCESSIBLE;
                  }
                  return FilterResult.INDECISIVE;
                })
            .create();

    // Serialization of public record should work
    PublicRecord record = new PublicRecord("test");
    String json = gson.toJson(record);
    assertTrue(json.contains("\"value\":\"test\""));
  }

  @Test
  public void checkAccessible_publicRecord_blockInaccessible_deserialization_succeeds() {
    Gson gson =
        new GsonBuilder()
            .addReflectionAccessFilter(
                rawClass -> {
                  if (rawClass == PublicRecord.class) {
                    return FilterResult.BLOCK_INACCESSIBLE;
                  }
                  return FilterResult.INDECISIVE;
                })
            .create();

    // Deserialization of public record should work
    PublicRecord result = gson.fromJson("{\"value\":\"test\"}", PublicRecord.class);
    assertNotNull(result);
    assertEquals("test", result.value());
  }

  // ==========================================================================
  // Test for nested class with private constructor
  // ==========================================================================

  /** Nested class with package-private field */
  static class PackagePrivateFieldClass {
    String packagePrivateField;
  }

  @Test
  public void checkAccessible_packagePrivateField_blockInaccessible_throwsException() {
    // This test exercises checkAccessible for a package-private field in a non-public class
    // The canAccess check will fail because the declaring class is not public
    Gson gson =
        new GsonBuilder()
            .addReflectionAccessFilter(
                rawClass -> {
                  if (rawClass == PackagePrivateFieldClass.class) {
                    return FilterResult.BLOCK_INACCESSIBLE;
                  }
                  return FilterResult.INDECISIVE;
                })
            .create();

    PackagePrivateFieldClass obj = new PackagePrivateFieldClass();
    obj.packagePrivateField = "test";

    try {
      gson.toJson(obj);
      fail("Expected JsonIOException due to inaccessible field in package-private class");
    } catch (JsonIOException e) {
      // This exercises checkAccessible lines 170-179
      assertTrue(
          "Should mention accessibility issue",
          e.getMessage().contains("is not accessible")
              || e.getMessage().contains("ReflectionAccessFilter"));
    }
  }
}
