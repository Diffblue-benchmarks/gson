/*
 * Copyright (C) 2008 Google Inc.
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

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import org.junit.Test;

/**
 * Unit tests for {@link Excluder} constructor to ensure field initialization coverage.
 *
 * @author Claude
 */
public class ExcluderClaude_constructorTest {

  /**
   * Tests that a new Excluder instance can be created and its default field values are properly
   * initialized. This test uses reflection to invoke the package-private constructor because
   * there is no other way to test the constructor directly - Excluder.DEFAULT is a static
   * singleton and clone() copies existing field values rather than re-initializing them.
   */
  @Test
  public void testConstructorInitializesVersionToIgnoreVersions() throws Exception {
    // Use reflection to create a new Excluder instance since the constructor is not public
    // and Excluder.DEFAULT is a pre-created singleton.
    // Reflection is necessary here because:
    // 1. The Excluder constructor is package-private
    // 2. Excluder.DEFAULT is already instantiated at class load time
    // 3. clone() copies fields rather than re-running field initializers
    Constructor<Excluder> constructor = Excluder.class.getDeclaredConstructor();
    constructor.setAccessible(true);
    Excluder excluder = constructor.newInstance();

    // Verify that version is initialized to IGNORE_VERSIONS (-1.0d)
    // When version is IGNORE_VERSIONS, classes with @Since/@Until annotations should NOT be excluded
    assertThat(excluder.excludeClass(SinceVersion2.class, true)).isFalse();
    assertThat(excluder.excludeClass(UntilVersion1.class, true)).isFalse();
  }

  @Test
  public void testConstructorInitializesModifiersToTransientAndStatic() throws Exception {
    Constructor<Excluder> constructor = Excluder.class.getDeclaredConstructor();
    constructor.setAccessible(true);
    Excluder excluder = constructor.newInstance();

    // Verify that default modifiers are TRANSIENT | STATIC
    // Transient fields should be excluded
    Field transientField = TestFieldClass.class.getDeclaredField("transientField");
    assertThat(excluder.excludeField(transientField, true)).isTrue();

    // Static fields should be excluded
    Field staticField = TestFieldClass.class.getDeclaredField("staticField");
    assertThat(excluder.excludeField(staticField, true)).isTrue();

    // Regular fields should NOT be excluded
    Field regularField = TestFieldClass.class.getDeclaredField("regularField");
    assertThat(excluder.excludeField(regularField, true)).isFalse();

    // Private fields should NOT be excluded (PRIVATE is not in default modifiers)
    Field privateField = TestFieldClass.class.getDeclaredField("privateField");
    assertThat(excluder.excludeField(privateField, true)).isFalse();
  }

  @Test
  public void testConstructorInitializesSerializeInnerClassesToTrue() throws Exception {
    Constructor<Excluder> constructor = Excluder.class.getDeclaredConstructor();
    constructor.setAccessible(true);
    Excluder excluder = constructor.newInstance();

    // Verify that serializeInnerClasses is initialized to true
    // Inner classes should NOT be excluded by default
    assertThat(excluder.excludeClass(OuterClass.InnerClass.class, true)).isFalse();
  }

  @Test
  public void testConstructorInitializesRequireExposeToFalse() throws Exception {
    Constructor<Excluder> constructor = Excluder.class.getDeclaredConstructor();
    constructor.setAccessible(true);
    Excluder excluder = constructor.newInstance();

    // Verify that requireExpose is initialized to false
    // Fields without @Expose should NOT be excluded by default
    Field hiddenField = ExposeTestClass.class.getDeclaredField("hiddenField");
    assertThat(excluder.excludeField(hiddenField, true)).isFalse();
  }

  @Test
  public void testConstructorInitializesExclusionStrategiesToEmptyLists() throws Exception {
    Constructor<Excluder> constructor = Excluder.class.getDeclaredConstructor();
    constructor.setAccessible(true);
    Excluder excluder = constructor.newInstance();

    // Verify that exclusion strategies lists are empty by checking that no class is excluded
    // by exclusion strategy (only default exclusions apply)
    assertThat(excluder.excludeClass(String.class, true)).isFalse();
    assertThat(excluder.excludeClass(String.class, false)).isFalse();

    // Fields should not be excluded by exclusion strategy
    Field regularField = TestFieldClass.class.getDeclaredField("regularField");
    assertThat(excluder.excludeField(regularField, true)).isFalse();
    assertThat(excluder.excludeField(regularField, false)).isFalse();
  }

  @Test
  public void testConstructorCreatesValidTypeAdapterFactory() throws Exception {
    Constructor<Excluder> constructor = Excluder.class.getDeclaredConstructor();
    constructor.setAccessible(true);
    Excluder excluder = constructor.newInstance();

    // Verify that the Excluder can function as a TypeAdapterFactory
    Gson gson = new Gson();
    TypeAdapter<String> adapter = excluder.create(gson, TypeToken.get(String.class));
    // Should return null for non-excluded classes
    assertThat(adapter).isNull();
  }

  @Test
  public void testConstructorFieldInitializationOrder() throws Exception {
    // This test verifies that all field initializations happen correctly
    // by creating a fresh instance and checking all default behaviors
    Constructor<Excluder> constructor = Excluder.class.getDeclaredConstructor();
    constructor.setAccessible(true);
    Excluder excluder = constructor.newInstance();

    // All these should reflect proper initialization:
    // 1. version = IGNORE_VERSIONS (versioned classes not excluded)
    assertThat(excluder.excludeClass(SinceVersion2.class, true)).isFalse();

    // 2. modifiers = TRANSIENT | STATIC (transient excluded, regular not excluded)
    Field transientField = TestFieldClass.class.getDeclaredField("transientField");
    Field regularField = TestFieldClass.class.getDeclaredField("regularField");
    assertThat(excluder.excludeField(transientField, true)).isTrue();
    assertThat(excluder.excludeField(regularField, true)).isFalse();

    // 3. serializeInnerClasses = true (inner classes not excluded)
    assertThat(excluder.excludeClass(OuterClass.InnerClass.class, true)).isFalse();

    // 4. requireExpose = false (fields without @Expose not excluded)
    Field hiddenField = ExposeTestClass.class.getDeclaredField("hiddenField");
    assertThat(excluder.excludeField(hiddenField, true)).isFalse();

    // 5 & 6. serializationStrategies and deserializationStrategies = empty (no strategy exclusions)
    assertThat(excluder.excludeClass(String.class, true)).isFalse();
    assertThat(excluder.excludeClass(String.class, false)).isFalse();
  }

  @Test
  public void testNewExcluderInstanceIsSeparateFromDefault() throws Exception {
    Constructor<Excluder> constructor = Excluder.class.getDeclaredConstructor();
    constructor.setAccessible(true);
    Excluder newExcluder = constructor.newInstance();

    // The new instance should be separate from DEFAULT
    assertThat(newExcluder).isNotSameInstanceAs(Excluder.DEFAULT);

    // But should have the same behavior since they have the same default values
    Field transientField = TestFieldClass.class.getDeclaredField("transientField");
    assertThat(newExcluder.excludeField(transientField, true))
        .isEqualTo(Excluder.DEFAULT.excludeField(transientField, true));
  }

  // ========== Helper Classes ==========

  @com.google.gson.annotations.Since(2.0)
  private static class SinceVersion2 {}

  @com.google.gson.annotations.Until(1.0)
  private static class UntilVersion1 {}

  @SuppressWarnings("unused")
  private static class TestFieldClass {
    String regularField;
    private String privateField;
    transient String transientField;
    static String staticField;
  }

  @SuppressWarnings({"unused", "InnerClassMayBeStatic"})
  private static class OuterClass {
    class InnerClass {
      String value;
    }
  }

  @SuppressWarnings("unused")
  private static class ExposeTestClass {
    String hiddenField;

    @com.google.gson.annotations.Expose
    String exposedField;
  }
}
