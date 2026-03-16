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

package com.google.gson;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Modifier;
import org.junit.Test;

public class GsonBuilderTest {

  @Test
  public void testConstructor() {
    GsonBuilder builder = new GsonBuilder();
    assertThat(builder).isNotNull();
    Gson gson = builder.create();
    assertThat(gson).isNotNull();
  }

  @Test
  public void testCreate() {
    Gson gson = new GsonBuilder().create();
    assertThat(gson).isNotNull();
  }

  @Test
  public void testSerializeNulls() {
    Gson gson = new GsonBuilder().serializeNulls().create();
    TestClass obj = new TestClass();
    obj.value = null;
    String json = gson.toJson(obj);
    assertThat(json).contains("\"value\":null");
  }

  @Test
  public void testSerializeNulls_disabled() {
    Gson gson = new GsonBuilder().create();
    TestClass obj = new TestClass();
    obj.value = null;
    String json = gson.toJson(obj);
    assertThat(json).doesNotContain("value");
  }

  @Test
  public void testPrettyPrinting() {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    TestClass obj = new TestClass();
    obj.name = "test";
    obj.value = 123;
    String json = gson.toJson(obj);
    assertThat(json).contains("\n");
    assertThat(json).contains("  ");
  }

  @Test
  public void testPrettyPrinting_disabled() {
    Gson gson = new GsonBuilder().create();
    TestClass obj = new TestClass();
    obj.name = "test";
    obj.value = 123;
    String json = gson.toJson(obj);
    assertThat(json).doesNotContain("\n");
  }

  @Test
  public void testFieldNamingPolicy_identity() {
    Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
    TestClass obj = new TestClass();
    obj.name = "test";
    String json = gson.toJson(obj);
    assertThat(json).contains("\"name\":");
  }

  @Test
  public void testFieldNamingPolicy_upperCamelCase() {
    Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
    TestClass obj = new TestClass();
    obj.name = "test";
    String json = gson.toJson(obj);
    assertThat(json).contains("\"Name\":");
  }

  @Test
  public void testFieldNamingPolicy_lowerCaseWithUnderscores() {
    Gson gson =
        new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();
    CamelCaseClass obj = new CamelCaseClass();
    obj.someField = "test";
    String json = gson.toJson(obj);
    assertThat(json).contains("\"some_field\":");
  }

  @Test
  public void testLongSerializationPolicy_default() {
    Gson gson =
        new GsonBuilder()
            .setLongSerializationPolicy(LongSerializationPolicy.DEFAULT)
            .create();
    assertThat(gson.toJson(123L)).isEqualTo("123");
  }

  @Test
  public void testLongSerializationPolicy_string() {
    Gson gson =
        new GsonBuilder().setLongSerializationPolicy(LongSerializationPolicy.STRING).create();
    assertThat(gson.toJson(123L)).isEqualTo("\"123\"");
  }

  @Test
  public void testExcludeFieldsWithoutExposeAnnotation() {
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    ExposedClass obj = new ExposedClass();
    obj.exposed = "visible";
    obj.notExposed = "hidden";
    String json = gson.toJson(obj);
    assertThat(json).contains("\"exposed\":");
    assertThat(json).doesNotContain("notExposed");
  }

  @Test
  public void testVersion_withSinceAnnotation() {
    Gson gson = new GsonBuilder().setVersion(2.0).create();
    VersionedClass obj = new VersionedClass();
    obj.since1 = "old";
    obj.since2 = "new";
    String json = gson.toJson(obj);
    assertThat(json).contains("\"since1\":");
    assertThat(json).contains("\"since2\":");
  }

  @Test
  public void testVersion_excludesNewerFields() {
    Gson gson = new GsonBuilder().setVersion(1.5).create();
    VersionedClass obj = new VersionedClass();
    obj.since1 = "old";
    obj.since2 = "new";
    String json = gson.toJson(obj);
    assertThat(json).contains("\"since1\":");
    assertThat(json).doesNotContain("since2");
  }

  @Test
  public void testVersion_withUntilAnnotation() {
    Gson gson = new GsonBuilder().setVersion(1.5).create();
    VersionedClass obj = new VersionedClass();
    obj.until2 = "deprecated";
    String json = gson.toJson(obj);
    assertThat(json).contains("\"until2\":");
  }

  @Test
  public void testVersion_excludesOlderFields() {
    Gson gson = new GsonBuilder().setVersion(2.5).create();
    VersionedClass obj = new VersionedClass();
    obj.until2 = "deprecated";
    String json = gson.toJson(obj);
    assertThat(json).doesNotContain("until2");
  }

  @Test
  public void testSetVersion_negativeVersion_throwsException() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new GsonBuilder().setVersion(-1.0));
  }

  @Test
  public void testSetVersion_nanVersion_throwsException() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new GsonBuilder().setVersion(Double.NaN));
  }

  @Test
  public void testDisableHtmlEscaping() {
    Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    String json = gson.toJson("<html>");
    assertThat(json).isEqualTo("\"<html>\"");
  }

  @Test
  public void testHtmlEscaping_enabled() {
    Gson gson = new GsonBuilder().create();
    String json = gson.toJson("<html>");
    assertThat(json).isEqualTo("\"\\u003chtml\\u003e\"");
  }

  @Test
  public void testGenerateNonExecutableJson() {
    Gson gson = new GsonBuilder().generateNonExecutableJson().create();
    TestClass obj = new TestClass();
    obj.name = "test";
    String json = gson.toJson(obj);
    assertThat(json).startsWith(")]}'\n");
  }

  @Test
  public void testEnableComplexMapKeySerialization() {
    Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
    assertThat(gson).isNotNull();
  }

  @Test
  public void testDisableInnerClassSerialization() {
    Gson gson = new GsonBuilder().disableInnerClassSerialization().create();
    assertThat(gson).isNotNull();
  }

  @Test
  public void testSerializeSpecialFloatingPointValues() {
    Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues().create();
    assertThat(gson.toJson(Double.NaN)).isEqualTo("NaN");
    assertThat(gson.toJson(Double.POSITIVE_INFINITY)).isEqualTo("Infinity");
    assertThat(gson.toJson(Double.NEGATIVE_INFINITY)).isEqualTo("-Infinity");
  }

  @Test
  public void testSerializeSpecialFloatingPointValues_disabled() {
    Gson gson = new GsonBuilder().create();
    assertThrows(IllegalArgumentException.class, () -> gson.toJson(Double.NaN));
  }

  @Test
  public void testSetStrictness_lenient() {
    Gson gson = new GsonBuilder().setStrictness(Strictness.LENIENT).create();
    assertThat(gson).isNotNull();
  }

  @Test
  public void testSetStrictness_strict() {
    Gson gson = new GsonBuilder().setStrictness(Strictness.STRICT).create();
    assertThat(gson).isNotNull();
  }

  @Test
  public void testExcludeFieldsWithModifiers() {
    Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.STATIC).create();
    assertThat(gson).isNotNull();
  }

  @Test
  public void testChainedConfiguration() {
    Gson gson =
        new GsonBuilder()
            .serializeNulls()
            .setPrettyPrinting()
            .disableHtmlEscaping()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();
    assertThat(gson).isNotNull();
  }

  @Test
  public void testRegisterTypeAdapter() {
    TypeAdapter<TestClass> adapter =
        new TypeAdapter<TestClass>() {
          @Override
          public void write(JsonWriter out, TestClass value) throws IOException {
            out.value("custom");
          }

          @Override
          public TestClass read(JsonReader in) throws IOException {
            @SuppressWarnings("unused")
            String unused = in.nextString();
            return new TestClass();
          }
        };

    Gson gson = new GsonBuilder().registerTypeAdapter(TestClass.class, adapter).create();
    TestClass obj = new TestClass();
    obj.name = "test";
    String json = gson.toJson(obj);
    assertThat(json).isEqualTo("\"custom\"");
  }

  private static class TestClass {
    @SuppressWarnings("unused")
    String name;

    @SuppressWarnings("unused")
    Integer value;
  }

  private static class CamelCaseClass {
    @SuppressWarnings("unused")
    String someField;
  }

  private static class ExposedClass {
    @Expose @SuppressWarnings("unused") String exposed;

    @SuppressWarnings("unused")
    String notExposed;
  }

  private static class VersionedClass {
    @Since(1.0)
    @SuppressWarnings("unused")
    String since1;

    @Since(2.0)
    @SuppressWarnings("unused")
    String since2;

    @Until(2.0)
    @SuppressWarnings("unused")
    String until2;
  }
}
