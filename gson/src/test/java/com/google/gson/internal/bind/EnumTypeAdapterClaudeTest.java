/*
 * Copyright (C) 2011 Google Inc.
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
import static org.junit.Assert.assertNull;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;

/** Tests for {@link EnumTypeAdapter}. */
public class EnumTypeAdapterClaudeTest {

  private final Gson gson = new Gson();

  // ==========================================================================
  // Test enums for various scenarios
  // ==========================================================================

  /** Simple enum with basic constants. */
  private enum SimpleEnum {
    VALUE_A,
    VALUE_B,
    VALUE_C
  }

  /** Enum with @SerializedName annotation. */
  private enum EnumWithSerializedName {
    @SerializedName("custom_name_a")
    VALUE_A,

    @SerializedName("custom_name_b")
    VALUE_B,

    VALUE_C // No annotation, uses default name
  }

  /** Enum with @SerializedName annotation including alternates. */
  private enum EnumWithAlternates {
    @SerializedName(value = "primary", alternate = {"alt1", "alt2"})
    VALUE_A,

    @SerializedName(value = "main", alternate = {"secondary"})
    VALUE_B
  }

  /** Enum with custom toString() implementation. */
  private enum EnumWithCustomToString {
    VALUE_A {
      @Override
      public String toString() {
        return "custom_toString_A";
      }
    },
    VALUE_B {
      @Override
      public String toString() {
        return "custom_toString_B";
      }
    }
  }

  /** Enum with both @SerializedName and custom toString(). */
  private enum EnumWithBothAnnotationAndToString {
    @SerializedName("serialized")
    VALUE_A {
      @Override
      public String toString() {
        return "toStringValue";
      }
    }
  }

  /** Enum with empty string as SerializedName. */
  private enum EnumWithEmptySerializedName {
    @SerializedName("")
    EMPTY_NAME,

    NORMAL_NAME
  }

  // ==========================================================================
  // FACTORY tests
  // ==========================================================================

  @Test
  public void factory_createsAdapterForSimpleEnum() {
    TypeAdapter<SimpleEnum> adapter = gson.getAdapter(SimpleEnum.class);
    assertNotNull(adapter);
  }

  @Test
  public void factory_returnsNullForNonEnumClass() {
    // String is not an enum, should be handled by different adapter
    TypeAdapter<String> adapter =
        EnumTypeAdapter.FACTORY.create(gson, TypeToken.get(String.class));
    assertNull(adapter);
  }

  @Test
  public void factory_returnsNullForBaseEnumClass() {
    // Enum.class itself should return null
    @SuppressWarnings({"unchecked", "rawtypes"})
    TypeAdapter<Enum> adapter =
        EnumTypeAdapter.FACTORY.create(gson, (TypeToken<Enum>) (TypeToken) TypeToken.get(Enum.class));
    assertNull(adapter);
  }

  @Test
  public void factory_createsAdapterForEnumWithSerializedName() {
    TypeAdapter<EnumWithSerializedName> adapter = gson.getAdapter(EnumWithSerializedName.class);
    assertNotNull(adapter);
  }

  @Test
  public void factory_handlesAnonymousEnumSubclass() {
    // EnumWithCustomToString has anonymous subclasses for constants
    TypeAdapter<EnumWithCustomToString> adapter = gson.getAdapter(EnumWithCustomToString.class);
    assertNotNull(adapter);
  }

  // ==========================================================================
  // read() tests - null handling
  // ==========================================================================

  @Test
  public void read_nullJson_returnsNull() throws IOException {
    TypeAdapter<SimpleEnum> adapter = gson.getAdapter(SimpleEnum.class);
    JsonReader reader = new JsonReader(new StringReader("null"));
    SimpleEnum result = adapter.read(reader);
    assertNull(result);
  }

  // ==========================================================================
  // read() tests - basic enum values
  // ==========================================================================

  @Test
  public void read_simpleEnumByName_returnsCorrectConstant() throws IOException {
    TypeAdapter<SimpleEnum> adapter = gson.getAdapter(SimpleEnum.class);
    JsonReader reader = new JsonReader(new StringReader("\"VALUE_A\""));
    SimpleEnum result = adapter.read(reader);
    assertEquals(SimpleEnum.VALUE_A, result);
  }

  @Test
  public void read_simpleEnumSecondValue_returnsCorrectConstant() throws IOException {
    TypeAdapter<SimpleEnum> adapter = gson.getAdapter(SimpleEnum.class);
    JsonReader reader = new JsonReader(new StringReader("\"VALUE_B\""));
    SimpleEnum result = adapter.read(reader);
    assertEquals(SimpleEnum.VALUE_B, result);
  }

  @Test
  public void read_simpleEnumThirdValue_returnsCorrectConstant() throws IOException {
    TypeAdapter<SimpleEnum> adapter = gson.getAdapter(SimpleEnum.class);
    JsonReader reader = new JsonReader(new StringReader("\"VALUE_C\""));
    SimpleEnum result = adapter.read(reader);
    assertEquals(SimpleEnum.VALUE_C, result);
  }

  // ==========================================================================
  // read() tests - @SerializedName support
  // ==========================================================================

  @Test
  public void read_withSerializedName_usesCustomName() throws IOException {
    TypeAdapter<EnumWithSerializedName> adapter = gson.getAdapter(EnumWithSerializedName.class);
    JsonReader reader = new JsonReader(new StringReader("\"custom_name_a\""));
    EnumWithSerializedName result = adapter.read(reader);
    assertEquals(EnumWithSerializedName.VALUE_A, result);
  }

  @Test
  public void read_withSerializedName_secondValue_usesCustomName() throws IOException {
    TypeAdapter<EnumWithSerializedName> adapter = gson.getAdapter(EnumWithSerializedName.class);
    JsonReader reader = new JsonReader(new StringReader("\"custom_name_b\""));
    EnumWithSerializedName result = adapter.read(reader);
    assertEquals(EnumWithSerializedName.VALUE_B, result);
  }

  @Test
  public void read_withSerializedName_noAnnotationValue_usesDefaultName() throws IOException {
    TypeAdapter<EnumWithSerializedName> adapter = gson.getAdapter(EnumWithSerializedName.class);
    JsonReader reader = new JsonReader(new StringReader("\"VALUE_C\""));
    EnumWithSerializedName result = adapter.read(reader);
    assertEquals(EnumWithSerializedName.VALUE_C, result);
  }

  // ==========================================================================
  // read() tests - alternate names
  // ==========================================================================

  @Test
  public void read_withAlternate_primaryName_returnsCorrectConstant() throws IOException {
    TypeAdapter<EnumWithAlternates> adapter = gson.getAdapter(EnumWithAlternates.class);
    JsonReader reader = new JsonReader(new StringReader("\"primary\""));
    EnumWithAlternates result = adapter.read(reader);
    assertEquals(EnumWithAlternates.VALUE_A, result);
  }

  @Test
  public void read_withAlternate_firstAlternate_returnsCorrectConstant() throws IOException {
    TypeAdapter<EnumWithAlternates> adapter = gson.getAdapter(EnumWithAlternates.class);
    JsonReader reader = new JsonReader(new StringReader("\"alt1\""));
    EnumWithAlternates result = adapter.read(reader);
    assertEquals(EnumWithAlternates.VALUE_A, result);
  }

  @Test
  public void read_withAlternate_secondAlternate_returnsCorrectConstant() throws IOException {
    TypeAdapter<EnumWithAlternates> adapter = gson.getAdapter(EnumWithAlternates.class);
    JsonReader reader = new JsonReader(new StringReader("\"alt2\""));
    EnumWithAlternates result = adapter.read(reader);
    assertEquals(EnumWithAlternates.VALUE_A, result);
  }

  @Test
  public void read_withAlternate_secondEnumAlternate_returnsCorrectConstant() throws IOException {
    TypeAdapter<EnumWithAlternates> adapter = gson.getAdapter(EnumWithAlternates.class);
    JsonReader reader = new JsonReader(new StringReader("\"secondary\""));
    EnumWithAlternates result = adapter.read(reader);
    assertEquals(EnumWithAlternates.VALUE_B, result);
  }

  // ==========================================================================
  // read() tests - toString fallback
  // ==========================================================================

  @Test
  public void read_withCustomToString_fallbackToToString() throws IOException {
    TypeAdapter<EnumWithCustomToString> adapter = gson.getAdapter(EnumWithCustomToString.class);
    // Using the toString value instead of the constant name
    JsonReader reader = new JsonReader(new StringReader("\"custom_toString_A\""));
    EnumWithCustomToString result = adapter.read(reader);
    assertEquals(EnumWithCustomToString.VALUE_A, result);
  }

  @Test
  public void read_withCustomToString_secondValue_fallbackToToString() throws IOException {
    TypeAdapter<EnumWithCustomToString> adapter = gson.getAdapter(EnumWithCustomToString.class);
    JsonReader reader = new JsonReader(new StringReader("\"custom_toString_B\""));
    EnumWithCustomToString result = adapter.read(reader);
    assertEquals(EnumWithCustomToString.VALUE_B, result);
  }

  @Test
  public void read_withCustomToString_byConstantName_worksViaMap() throws IOException {
    TypeAdapter<EnumWithCustomToString> adapter = gson.getAdapter(EnumWithCustomToString.class);
    // EnumWithCustomToString doesn't have @SerializedName, so nameToConstant uses constant.name()
    JsonReader reader = new JsonReader(new StringReader("\"VALUE_A\""));
    EnumWithCustomToString result = adapter.read(reader);
    assertEquals(EnumWithCustomToString.VALUE_A, result);
  }

  @Test
  public void read_withSerializedNameAndToString_prefersSerializedName() throws IOException {
    TypeAdapter<EnumWithBothAnnotationAndToString> adapter =
        gson.getAdapter(EnumWithBothAnnotationAndToString.class);
    // @SerializedName should be used for nameToConstant lookup
    JsonReader reader = new JsonReader(new StringReader("\"serialized\""));
    EnumWithBothAnnotationAndToString result = adapter.read(reader);
    assertEquals(EnumWithBothAnnotationAndToString.VALUE_A, result);
  }

  @Test
  public void read_withSerializedNameAndToString_toStringAlsoWorks() throws IOException {
    TypeAdapter<EnumWithBothAnnotationAndToString> adapter =
        gson.getAdapter(EnumWithBothAnnotationAndToString.class);
    // toString value should work as fallback via stringToConstant
    JsonReader reader = new JsonReader(new StringReader("\"toStringValue\""));
    EnumWithBothAnnotationAndToString result = adapter.read(reader);
    assertEquals(EnumWithBothAnnotationAndToString.VALUE_A, result);
  }

  // ==========================================================================
  // read() tests - unknown/invalid values
  // ==========================================================================

  @Test
  public void read_unknownValue_returnsNull() throws IOException {
    TypeAdapter<SimpleEnum> adapter = gson.getAdapter(SimpleEnum.class);
    JsonReader reader = new JsonReader(new StringReader("\"UNKNOWN_VALUE\""));
    SimpleEnum result = adapter.read(reader);
    assertNull(result);
  }

  @Test
  public void read_emptyString_returnsNull() throws IOException {
    TypeAdapter<SimpleEnum> adapter = gson.getAdapter(SimpleEnum.class);
    JsonReader reader = new JsonReader(new StringReader("\"\""));
    SimpleEnum result = adapter.read(reader);
    assertNull(result);
  }

  @Test
  public void read_emptySerializedName_matchesEmptyString() throws IOException {
    TypeAdapter<EnumWithEmptySerializedName> adapter =
        gson.getAdapter(EnumWithEmptySerializedName.class);
    JsonReader reader = new JsonReader(new StringReader("\"\""));
    EnumWithEmptySerializedName result = adapter.read(reader);
    assertEquals(EnumWithEmptySerializedName.EMPTY_NAME, result);
  }

  // ==========================================================================
  // write() tests - null handling
  // ==========================================================================

  @Test
  public void write_nullValue_writesNull() throws IOException {
    TypeAdapter<SimpleEnum> adapter = gson.getAdapter(SimpleEnum.class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, null);
    assertEquals("null", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - basic enum values
  // ==========================================================================

  @Test
  public void write_simpleEnumFirstValue_writesCorrectName() throws IOException {
    TypeAdapter<SimpleEnum> adapter = gson.getAdapter(SimpleEnum.class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, SimpleEnum.VALUE_A);
    assertEquals("\"VALUE_A\"", stringWriter.toString());
  }

  @Test
  public void write_simpleEnumSecondValue_writesCorrectName() throws IOException {
    TypeAdapter<SimpleEnum> adapter = gson.getAdapter(SimpleEnum.class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, SimpleEnum.VALUE_B);
    assertEquals("\"VALUE_B\"", stringWriter.toString());
  }

  @Test
  public void write_simpleEnumThirdValue_writesCorrectName() throws IOException {
    TypeAdapter<SimpleEnum> adapter = gson.getAdapter(SimpleEnum.class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, SimpleEnum.VALUE_C);
    assertEquals("\"VALUE_C\"", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - @SerializedName support
  // ==========================================================================

  @Test
  public void write_withSerializedName_usesCustomName() throws IOException {
    TypeAdapter<EnumWithSerializedName> adapter = gson.getAdapter(EnumWithSerializedName.class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, EnumWithSerializedName.VALUE_A);
    assertEquals("\"custom_name_a\"", stringWriter.toString());
  }

  @Test
  public void write_withSerializedName_secondValue_usesCustomName() throws IOException {
    TypeAdapter<EnumWithSerializedName> adapter = gson.getAdapter(EnumWithSerializedName.class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, EnumWithSerializedName.VALUE_B);
    assertEquals("\"custom_name_b\"", stringWriter.toString());
  }

  @Test
  public void write_withSerializedName_noAnnotation_usesDefaultName() throws IOException {
    TypeAdapter<EnumWithSerializedName> adapter = gson.getAdapter(EnumWithSerializedName.class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, EnumWithSerializedName.VALUE_C);
    assertEquals("\"VALUE_C\"", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - alternates (not used for writing)
  // ==========================================================================

  @Test
  public void write_withAlternates_usesPrimaryName() throws IOException {
    TypeAdapter<EnumWithAlternates> adapter = gson.getAdapter(EnumWithAlternates.class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, EnumWithAlternates.VALUE_A);
    // Alternates are for reading only, writing should use primary name
    assertEquals("\"primary\"", stringWriter.toString());
  }

  @Test
  public void write_withAlternates_secondValue_usesPrimaryName() throws IOException {
    TypeAdapter<EnumWithAlternates> adapter = gson.getAdapter(EnumWithAlternates.class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, EnumWithAlternates.VALUE_B);
    assertEquals("\"main\"", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - custom toString (not used for writing)
  // ==========================================================================

  @Test
  public void write_withCustomToString_usesConstantName() throws IOException {
    TypeAdapter<EnumWithCustomToString> adapter = gson.getAdapter(EnumWithCustomToString.class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, EnumWithCustomToString.VALUE_A);
    // Write uses constantToName which is the constant.name(), not toString()
    assertEquals("\"VALUE_A\"", stringWriter.toString());
  }

  @Test
  public void write_withSerializedNameAndToString_usesSerializedName() throws IOException {
    TypeAdapter<EnumWithBothAnnotationAndToString> adapter =
        gson.getAdapter(EnumWithBothAnnotationAndToString.class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, EnumWithBothAnnotationAndToString.VALUE_A);
    // @SerializedName takes precedence
    assertEquals("\"serialized\"", stringWriter.toString());
  }

  // ==========================================================================
  // write() tests - empty serialized name
  // ==========================================================================

  @Test
  public void write_withEmptySerializedName_writesEmptyString() throws IOException {
    TypeAdapter<EnumWithEmptySerializedName> adapter =
        gson.getAdapter(EnumWithEmptySerializedName.class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, EnumWithEmptySerializedName.EMPTY_NAME);
    assertEquals("\"\"", stringWriter.toString());
  }

  @Test
  public void write_withEmptySerializedName_normalValue_writesDefaultName() throws IOException {
    TypeAdapter<EnumWithEmptySerializedName> adapter =
        gson.getAdapter(EnumWithEmptySerializedName.class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, EnumWithEmptySerializedName.NORMAL_NAME);
    assertEquals("\"NORMAL_NAME\"", stringWriter.toString());
  }

  // ==========================================================================
  // Round-trip tests
  // ==========================================================================

  @Test
  public void roundTrip_simpleEnum_preservesValue() throws IOException {
    String json = gson.toJson(SimpleEnum.VALUE_B);
    SimpleEnum result = gson.fromJson(json, SimpleEnum.class);
    assertEquals(SimpleEnum.VALUE_B, result);
  }

  @Test
  public void roundTrip_enumWithSerializedName_preservesValue() throws IOException {
    String json = gson.toJson(EnumWithSerializedName.VALUE_A);
    EnumWithSerializedName result = gson.fromJson(json, EnumWithSerializedName.class);
    assertEquals(EnumWithSerializedName.VALUE_A, result);
  }

  @Test
  public void roundTrip_enumWithAlternates_preservesValue() throws IOException {
    String json = gson.toJson(EnumWithAlternates.VALUE_A);
    EnumWithAlternates result = gson.fromJson(json, EnumWithAlternates.class);
    assertEquals(EnumWithAlternates.VALUE_A, result);
  }

  @Test
  public void roundTrip_enumWithCustomToString_preservesValue() throws IOException {
    String json = gson.toJson(EnumWithCustomToString.VALUE_A);
    EnumWithCustomToString result = gson.fromJson(json, EnumWithCustomToString.class);
    assertEquals(EnumWithCustomToString.VALUE_A, result);
  }

  @Test
  public void roundTrip_nullValue_preservesNull() throws IOException {
    String json = gson.toJson(null, SimpleEnum.class);
    SimpleEnum result = gson.fromJson(json, SimpleEnum.class);
    assertNull(result);
  }

  // ==========================================================================
  // Gson integration tests
  // ==========================================================================

  @Test
  public void gsonToJson_simpleEnum_returnsExpectedJson() {
    String json = gson.toJson(SimpleEnum.VALUE_A);
    assertEquals("\"VALUE_A\"", json);
  }

  @Test
  public void gsonFromJson_simpleEnum_returnsExpectedConstant() {
    SimpleEnum result = gson.fromJson("\"VALUE_B\"", SimpleEnum.class);
    assertEquals(SimpleEnum.VALUE_B, result);
  }

  @Test
  public void gsonToJson_enumWithSerializedName_returnsCustomName() {
    String json = gson.toJson(EnumWithSerializedName.VALUE_A);
    assertEquals("\"custom_name_a\"", json);
  }

  @Test
  public void gsonFromJson_enumWithSerializedName_parsesCustomName() {
    EnumWithSerializedName result =
        gson.fromJson("\"custom_name_b\"", EnumWithSerializedName.class);
    assertEquals(EnumWithSerializedName.VALUE_B, result);
  }

  // ==========================================================================
  // Edge case tests
  // ==========================================================================

  @Test
  public void read_caseInsensitiveDoesNotMatch() throws IOException {
    // EnumTypeAdapter is case-sensitive
    TypeAdapter<SimpleEnum> adapter = gson.getAdapter(SimpleEnum.class);
    JsonReader reader = new JsonReader(new StringReader("\"value_a\""));
    SimpleEnum result = adapter.read(reader);
    assertNull(result); // Case mismatch, returns null
  }

  @Test
  public void read_whitespace_returnsNull() throws IOException {
    TypeAdapter<SimpleEnum> adapter = gson.getAdapter(SimpleEnum.class);
    JsonReader reader = new JsonReader(new StringReader("\" VALUE_A \""));
    SimpleEnum result = adapter.read(reader);
    assertNull(result); // Extra whitespace is not trimmed
  }

  // ==========================================================================
  // Test with object containing enum field
  // ==========================================================================

  @Test
  public void objectWithEnumField_serialization_works() {
    ObjectWithEnum obj = new ObjectWithEnum();
    obj.status = SimpleEnum.VALUE_B;
    obj.name = "test";
    String json = gson.toJson(obj);
    assertEquals("{\"status\":\"VALUE_B\",\"name\":\"test\"}", json);
  }

  @Test
  public void objectWithEnumField_deserialization_works() {
    String json = "{\"status\":\"VALUE_C\",\"name\":\"test\"}";
    ObjectWithEnum result = gson.fromJson(json, ObjectWithEnum.class);
    assertEquals(SimpleEnum.VALUE_C, result.status);
    assertEquals("test", result.name);
  }

  @Test
  public void objectWithEnumField_nullEnum_serialization_works() {
    ObjectWithEnum obj = new ObjectWithEnum();
    obj.status = null;
    obj.name = "test";
    String json = gson.toJson(obj);
    assertEquals("{\"name\":\"test\"}", json);
  }

  @Test
  public void objectWithEnumField_nullEnum_deserialization_works() {
    String json = "{\"status\":null,\"name\":\"test\"}";
    ObjectWithEnum result = gson.fromJson(json, ObjectWithEnum.class);
    assertNull(result.status);
    assertEquals("test", result.name);
  }

  private static class ObjectWithEnum {
    SimpleEnum status;
    String name;
  }
}
