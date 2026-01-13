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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.ReflectionAccessFilter;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

/** Tests for {@link ReflectiveTypeAdapterFactory}. */
public class ReflectiveTypeAdapterFactoryClaudeTest {

  // ==========================================================================
  // Constructor tests
  // ==========================================================================

  @Test
  public void constructor_withDefaultParameters_createsFactory() {
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory =
        new JsonAdapterAnnotationTypeAdapterFactory(constructorConstructor);

    ReflectiveTypeAdapterFactory factory =
        new ReflectiveTypeAdapterFactory(
            constructorConstructor,
            FieldNamingPolicy.IDENTITY,
            Excluder.DEFAULT,
            jsonAdapterFactory,
            Collections.emptyList());

    assertNotNull(factory);
  }

  @Test
  public void constructor_withCustomFieldNamingStrategy_createsFactory() {
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory =
        new JsonAdapterAnnotationTypeAdapterFactory(constructorConstructor);
    FieldNamingStrategy customStrategy = field -> "custom_" + field.getName();

    ReflectiveTypeAdapterFactory factory =
        new ReflectiveTypeAdapterFactory(
            constructorConstructor,
            customStrategy,
            Excluder.DEFAULT,
            jsonAdapterFactory,
            Collections.emptyList());

    assertNotNull(factory);
  }

  @Test
  public void constructor_withReflectionFilters_createsFactory() {
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory =
        new JsonAdapterAnnotationTypeAdapterFactory(constructorConstructor);
    List<ReflectionAccessFilter> filters = new ArrayList<>();
    filters.add(rawClass -> ReflectionAccessFilter.FilterResult.ALLOW);

    ReflectiveTypeAdapterFactory factory =
        new ReflectiveTypeAdapterFactory(
            constructorConstructor,
            FieldNamingPolicy.IDENTITY,
            Excluder.DEFAULT,
            jsonAdapterFactory,
            filters);

    assertNotNull(factory);
  }

  // ==========================================================================
  // create() tests - null return for primitives
  // ==========================================================================

  @Test
  public void create_withPrimitiveInt_returnsNull() {
    Gson gson = new Gson();
    ReflectiveTypeAdapterFactory factory = createFactory();
    TypeAdapter<Integer> adapter = factory.create(gson, TypeToken.get(int.class));
    assertNull(adapter);
  }

  @Test
  public void create_withPrimitiveLong_returnsNull() {
    Gson gson = new Gson();
    ReflectiveTypeAdapterFactory factory = createFactory();
    TypeAdapter<Long> adapter = factory.create(gson, TypeToken.get(long.class));
    assertNull(adapter);
  }

  @Test
  public void create_withPrimitiveBoolean_returnsNull() {
    Gson gson = new Gson();
    ReflectiveTypeAdapterFactory factory = createFactory();
    TypeAdapter<Boolean> adapter = factory.create(gson, TypeToken.get(boolean.class));
    assertNull(adapter);
  }

  @Test
  public void create_withPrimitiveDouble_returnsNull() {
    Gson gson = new Gson();
    ReflectiveTypeAdapterFactory factory = createFactory();
    TypeAdapter<Double> adapter = factory.create(gson, TypeToken.get(double.class));
    assertNull(adapter);
  }

  @Test
  public void create_withPrimitiveFloat_returnsNull() {
    Gson gson = new Gson();
    ReflectiveTypeAdapterFactory factory = createFactory();
    TypeAdapter<Float> adapter = factory.create(gson, TypeToken.get(float.class));
    assertNull(adapter);
  }

  @Test
  public void create_withPrimitiveChar_returnsNull() {
    Gson gson = new Gson();
    ReflectiveTypeAdapterFactory factory = createFactory();
    TypeAdapter<Character> adapter = factory.create(gson, TypeToken.get(char.class));
    assertNull(adapter);
  }

  @Test
  public void create_withPrimitiveByte_returnsNull() {
    Gson gson = new Gson();
    ReflectiveTypeAdapterFactory factory = createFactory();
    TypeAdapter<Byte> adapter = factory.create(gson, TypeToken.get(byte.class));
    assertNull(adapter);
  }

  @Test
  public void create_withPrimitiveShort_returnsNull() {
    Gson gson = new Gson();
    ReflectiveTypeAdapterFactory factory = createFactory();
    TypeAdapter<Short> adapter = factory.create(gson, TypeToken.get(short.class));
    assertNull(adapter);
  }

  // ==========================================================================
  // create() tests - returns adapter for regular classes
  // ==========================================================================

  @Test
  public void create_withRegularClass_returnsAdapter() {
    Gson gson = new Gson();
    ReflectiveTypeAdapterFactory factory = createFactory();
    TypeAdapter<SimplePojo> adapter = factory.create(gson, TypeToken.get(SimplePojo.class));
    assertNotNull(adapter);
  }

  @Test
  public void create_withObject_returnsAdapter() {
    Gson gson = new Gson();
    ReflectiveTypeAdapterFactory factory = createFactory();
    TypeAdapter<SimplePojo> adapter = factory.create(gson, TypeToken.get(SimplePojo.class));
    assertNotNull(adapter);
  }

  @Test
  public void create_withNestedPojo_returnsAdapter() {
    Gson gson = new Gson();
    ReflectiveTypeAdapterFactory factory = createFactory();
    TypeAdapter<OuterPojo> adapter = factory.create(gson, TypeToken.get(OuterPojo.class));
    assertNotNull(adapter);
  }

  // ==========================================================================
  // create() tests - anonymous/local class handling
  // ==========================================================================

  @Test
  public void create_withAnonymousClass_returnsSpecialAdapter() {
    Gson gson = new Gson();

    @SuppressWarnings("unused")
    Object anonymous =
        new Object() {
          String name = "test";
        };

    String json = gson.toJson(anonymous);
    assertEquals("null", json);
  }

  @Test
  public void create_withAnonymousClass_deserializesAsNull() {
    Gson gson = new Gson();

    @SuppressWarnings("unused")
    Object anonymous =
        new Object() {
          String name = "test";
        };

    Object result = gson.fromJson("{\"name\":\"test\"}", anonymous.getClass());
    assertNull(result);
  }

  @Test
  public void create_withLocalClass_serializesAsNull() {
    Gson gson = new Gson();

    class LocalClass {
      @SuppressWarnings("unused")
      String value = "test";
    }

    LocalClass local = new LocalClass();
    String json = gson.toJson(local);
    assertEquals("null", json);
  }

  @Test
  public void create_withLocalClass_deserializesAsNull() {
    Gson gson = new Gson();

    class LocalClass {
      @SuppressWarnings("unused")
      String value = "test";
    }

    LocalClass result = gson.fromJson("{\"value\":\"test\"}", LocalClass.class);
    assertNull(result);
  }

  // ==========================================================================
  // Serialization tests - simple POJOs
  // ==========================================================================

  @Test
  public void serialize_simplePojo_writesAllFields() {
    Gson gson = new Gson();
    SimplePojo pojo = new SimplePojo();
    pojo.name = "Alice";
    pojo.age = 30;

    String json = gson.toJson(pojo);
    assertTrue(json.contains("\"name\":\"Alice\""));
    assertTrue(json.contains("\"age\":30"));
  }

  @Test
  public void serialize_nullPojo_writesNull() {
    Gson gson = new Gson();
    String json = gson.toJson(null, SimplePojo.class);
    assertEquals("null", json);
  }

  @Test
  public void serialize_pojoWithNullFields_writesNullValues() {
    Gson gson = new GsonBuilder().serializeNulls().create();
    SimplePojo pojo = new SimplePojo();
    pojo.name = null;
    pojo.age = 0;

    String json = gson.toJson(pojo);
    assertTrue(json.contains("\"name\":null"));
    assertTrue(json.contains("\"age\":0"));
  }

  @Test
  public void serialize_pojoWithNullFields_omitsNullsByDefault() {
    Gson gson = new Gson();
    SimplePojo pojo = new SimplePojo();
    pojo.name = null;
    pojo.age = 0;

    String json = gson.toJson(pojo);
    assertTrue(!json.contains("\"name\""));
    assertTrue(json.contains("\"age\":0"));
  }

  @Test
  public void serialize_nestedPojo_writesNestedObject() {
    Gson gson = new Gson();
    OuterPojo outer = new OuterPojo();
    outer.inner = new SimplePojo();
    outer.inner.name = "Bob";
    outer.inner.age = 25;

    String json = gson.toJson(outer);
    assertTrue(json.contains("\"inner\":{"));
    assertTrue(json.contains("\"name\":\"Bob\""));
    assertTrue(json.contains("\"age\":25"));
  }

  // ==========================================================================
  // Deserialization tests - simple POJOs
  // ==========================================================================

  @Test
  public void deserialize_simplePojo_readsAllFields() {
    Gson gson = new Gson();
    String json = "{\"name\":\"Carol\",\"age\":35}";

    SimplePojo result = gson.fromJson(json, SimplePojo.class);
    assertEquals("Carol", result.name);
    assertEquals(35, result.age);
  }

  @Test
  public void deserialize_nullJson_returnsNull() {
    Gson gson = new Gson();
    SimplePojo result = gson.fromJson("null", SimplePojo.class);
    assertNull(result);
  }

  @Test
  public void deserialize_emptyObject_createsDefaultPojo() {
    Gson gson = new Gson();
    SimplePojo result = gson.fromJson("{}", SimplePojo.class);
    assertNotNull(result);
    assertNull(result.name);
    assertEquals(0, result.age);
  }

  @Test
  public void deserialize_extraFields_ignoresUnknownFields() {
    Gson gson = new Gson();
    String json = "{\"name\":\"Dave\",\"age\":40,\"unknownField\":\"ignored\"}";

    SimplePojo result = gson.fromJson(json, SimplePojo.class);
    assertEquals("Dave", result.name);
    assertEquals(40, result.age);
  }

  @Test
  public void deserialize_nestedPojo_readsNestedObject() {
    Gson gson = new Gson();
    String json = "{\"inner\":{\"name\":\"Eve\",\"age\":28}}";

    OuterPojo result = gson.fromJson(json, OuterPojo.class);
    assertNotNull(result.inner);
    assertEquals("Eve", result.inner.name);
    assertEquals(28, result.inner.age);
  }

  // ==========================================================================
  // Field naming strategy tests
  // ==========================================================================

  @Test
  public void serialize_withLowerCaseWithUnderscores_usesStrategy() {
    Gson gson =
        new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    CamelCasePojo pojo = new CamelCasePojo();
    pojo.firstName = "Frank";
    pojo.lastName = "Smith";

    String json = gson.toJson(pojo);
    assertTrue(json.contains("\"first_name\":\"Frank\""));
    assertTrue(json.contains("\"last_name\":\"Smith\""));
  }

  @Test
  public void deserialize_withLowerCaseWithUnderscores_usesStrategy() {
    Gson gson =
        new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    String json = "{\"first_name\":\"Grace\",\"last_name\":\"Lee\"}";

    CamelCasePojo result = gson.fromJson(json, CamelCasePojo.class);
    assertEquals("Grace", result.firstName);
    assertEquals("Lee", result.lastName);
  }

  @Test
  public void serialize_withUpperCamelCase_usesStrategy() {
    Gson gson =
        new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
    CamelCasePojo pojo = new CamelCasePojo();
    pojo.firstName = "Henry";
    pojo.lastName = "Wong";

    String json = gson.toJson(pojo);
    assertTrue(json.contains("\"FirstName\":\"Henry\""));
    assertTrue(json.contains("\"LastName\":\"Wong\""));
  }

  @Test
  public void serialize_withCustomFieldNamingStrategy_usesStrategy() {
    FieldNamingStrategy customStrategy = field -> "prefix_" + field.getName();
    Gson gson = new GsonBuilder().setFieldNamingStrategy(customStrategy).create();

    SimplePojo pojo = new SimplePojo();
    pojo.name = "Ivan";
    pojo.age = 45;

    String json = gson.toJson(pojo);
    assertTrue(json.contains("\"prefix_name\":\"Ivan\""));
    assertTrue(json.contains("\"prefix_age\":45"));
  }

  @Test
  public void deserialize_withCustomFieldNamingStrategy_usesStrategy() {
    FieldNamingStrategy customStrategy = field -> "prefix_" + field.getName();
    Gson gson = new GsonBuilder().setFieldNamingStrategy(customStrategy).create();

    String json = "{\"prefix_name\":\"Julia\",\"prefix_age\":50}";
    SimplePojo result = gson.fromJson(json, SimplePojo.class);
    assertEquals("Julia", result.name);
    assertEquals(50, result.age);
  }

  @Test
  public void serialize_withFieldNamingStrategyWithAlternates_usesMainName() {
    FieldNamingStrategy customStrategy =
        new FieldNamingStrategy() {
          @Override
          public String translateName(Field f) {
            return "main_" + f.getName();
          }

          @Override
          public List<String> alternateNames(Field f) {
            List<String> alternates = new ArrayList<>();
            alternates.add("alt_" + f.getName());
            return alternates;
          }
        };
    Gson gson = new GsonBuilder().setFieldNamingStrategy(customStrategy).create();

    SimplePojo pojo = new SimplePojo();
    pojo.name = "Test";
    pojo.age = 25;

    String json = gson.toJson(pojo);
    assertTrue(json.contains("\"main_name\":\"Test\""));
    assertTrue(json.contains("\"main_age\":25"));
  }

  @Test
  public void deserialize_withFieldNamingStrategyWithAlternates_acceptsAlternate() {
    FieldNamingStrategy customStrategy =
        new FieldNamingStrategy() {
          @Override
          public String translateName(Field f) {
            return "main_" + f.getName();
          }

          @Override
          public List<String> alternateNames(Field f) {
            List<String> alternates = new ArrayList<>();
            alternates.add("alt_" + f.getName());
            return alternates;
          }
        };
    Gson gson = new GsonBuilder().setFieldNamingStrategy(customStrategy).create();

    String json = "{\"alt_name\":\"AltTest\",\"alt_age\":30}";
    SimplePojo result = gson.fromJson(json, SimplePojo.class);
    assertEquals("AltTest", result.name);
    assertEquals(30, result.age);
  }

  // ==========================================================================
  // @SerializedName annotation tests
  // ==========================================================================

  @Test
  public void serialize_withSerializedName_usesAnnotationValue() {
    Gson gson = new Gson();
    SerializedNamePojo pojo = new SerializedNamePojo();
    pojo.renamedField = "test";

    String json = gson.toJson(pojo);
    assertTrue(json.contains("\"customName\":\"test\""));
  }

  @Test
  public void deserialize_withSerializedName_usesAnnotationValue() {
    Gson gson = new Gson();
    String json = "{\"customName\":\"value\"}";

    SerializedNamePojo result = gson.fromJson(json, SerializedNamePojo.class);
    assertEquals("value", result.renamedField);
  }

  @Test
  public void deserialize_withSerializedNameAlternates_acceptsAlternates() {
    Gson gson = new Gson();

    SerializedNameAltPojo result1 = gson.fromJson("{\"primary\":\"v1\"}", SerializedNameAltPojo.class);
    assertEquals("v1", result1.fieldWithAlternates);

    SerializedNameAltPojo result2 = gson.fromJson("{\"alt1\":\"v2\"}", SerializedNameAltPojo.class);
    assertEquals("v2", result2.fieldWithAlternates);

    SerializedNameAltPojo result3 = gson.fromJson("{\"alt2\":\"v3\"}", SerializedNameAltPojo.class);
    assertEquals("v3", result3.fieldWithAlternates);
  }

  @Test
  public void serialize_withSerializedNameAlternates_usesPrimaryName() {
    Gson gson = new Gson();
    SerializedNameAltPojo pojo = new SerializedNameAltPojo();
    pojo.fieldWithAlternates = "testValue";

    String json = gson.toJson(pojo);
    assertTrue(json.contains("\"primary\":\"testValue\""));
  }

  // ==========================================================================
  // Excluder tests
  // ==========================================================================

  @Test
  public void serialize_withTransientField_excludesField() {
    Gson gson = new Gson();
    TransientFieldPojo pojo = new TransientFieldPojo();
    pojo.normalField = "visible";
    pojo.transientField = "hidden";

    String json = gson.toJson(pojo);
    assertTrue(json.contains("\"normalField\":\"visible\""));
    assertTrue(!json.contains("transientField"));
    assertTrue(!json.contains("hidden"));
  }

  @Test
  public void serialize_withStaticField_excludesByDefault() {
    Gson gson = new Gson();
    StaticFieldPojo pojo = new StaticFieldPojo();
    pojo.instanceField = "instance";
    StaticFieldPojo.staticField = "static";

    String json = gson.toJson(pojo);
    assertTrue(json.contains("\"instanceField\":\"instance\""));
    assertTrue(!json.contains("staticField"));
  }

  @Test
  public void serialize_withExcludeFieldsWithModifiers_includesStatic() {
    Gson gson =
        new GsonBuilder()
            .excludeFieldsWithModifiers(java.lang.reflect.Modifier.TRANSIENT)
            .create();
    StaticFieldPojo pojo = new StaticFieldPojo();
    pojo.instanceField = "instance";
    StaticFieldPojo.staticField = "static";

    String json = gson.toJson(pojo);
    assertTrue(json.contains("\"instanceField\":\"instance\""));
    assertTrue(json.contains("\"staticField\":\"static\""));
  }

  @Test
  public void serialize_withExcludeFieldsWithoutExpose_onlyExposedFields() {
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    ExposePojo pojo = new ExposePojo();
    pojo.exposedField = "exposed";
    pojo.nonExposedField = "notExposed";

    String json = gson.toJson(pojo);
    assertTrue(json.contains("\"exposedField\":\"exposed\""));
    assertTrue(!json.contains("nonExposedField"));
  }

  @Test
  public void deserialize_withExcludeFieldsWithoutExpose_onlyExposedFields() {
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    String json = "{\"exposedField\":\"test\",\"nonExposedField\":\"ignored\"}";

    ExposePojo result = gson.fromJson(json, ExposePojo.class);
    assertEquals("test", result.exposedField);
    assertNull(result.nonExposedField);
  }

  @Test
  public void serialize_withExposeSerializeOnly_excludesFromDeserialization() {
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    ExposeSerializeOnlyPojo pojo = new ExposeSerializeOnlyPojo();
    pojo.writeOnlyField = "writeOnly";

    String json = gson.toJson(pojo);
    assertTrue(json.contains("\"writeOnlyField\":\"writeOnly\""));

    ExposeSerializeOnlyPojo result = gson.fromJson("{\"writeOnlyField\":\"test\"}", ExposeSerializeOnlyPojo.class);
    assertNull(result.writeOnlyField);
  }

  @Test
  public void deserialize_withExposeDeserializeOnly_excludesFromSerialization() {
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    ExposeDeserializeOnlyPojo pojo = new ExposeDeserializeOnlyPojo();
    pojo.readOnlyField = "readOnly";

    String json = gson.toJson(pojo);
    assertEquals("{}", json);

    ExposeDeserializeOnlyPojo result = gson.fromJson("{\"readOnlyField\":\"test\"}", ExposeDeserializeOnlyPojo.class);
    assertEquals("test", result.readOnlyField);
  }

  @Test
  public void serialize_withSinceAnnotation_respectsVersion() {
    Gson gsonOld = new GsonBuilder().setVersion(1.0).create();
    Gson gsonNew = new GsonBuilder().setVersion(2.0).create();

    VersionedPojo pojo = new VersionedPojo();
    pojo.fieldSince1 = "v1";
    pojo.fieldSince2 = "v2";

    String jsonOld = gsonOld.toJson(pojo);
    String jsonNew = gsonNew.toJson(pojo);

    assertTrue(jsonOld.contains("\"fieldSince1\":\"v1\""));
    assertTrue(!jsonOld.contains("fieldSince2"));

    assertTrue(jsonNew.contains("\"fieldSince1\":\"v1\""));
    assertTrue(jsonNew.contains("\"fieldSince2\":\"v2\""));
  }

  @Test
  public void serialize_withUntilAnnotation_respectsVersion() {
    Gson gsonOld = new GsonBuilder().setVersion(0.5).create();
    Gson gsonNew = new GsonBuilder().setVersion(1.5).create();

    UntilVersionedPojo pojo = new UntilVersionedPojo();
    pojo.deprecatedField = "deprecated";
    pojo.currentField = "current";

    String jsonOld = gsonOld.toJson(pojo);
    String jsonNew = gsonNew.toJson(pojo);

    assertTrue(jsonOld.contains("\"deprecatedField\":\"deprecated\""));
    assertTrue(jsonOld.contains("\"currentField\":\"current\""));

    assertTrue(!jsonNew.contains("deprecatedField"));
    assertTrue(jsonNew.contains("\"currentField\":\"current\""));
  }

  // ==========================================================================
  // @JsonAdapter on field tests
  // ==========================================================================

  @Test
  public void serialize_withFieldJsonAdapter_usesAdapter() {
    Gson gson = new Gson();
    FieldJsonAdapterPojo pojo = new FieldJsonAdapterPojo();
    pojo.customField = "test";

    String json = gson.toJson(pojo);
    assertTrue(json.contains("\"customField\":\"CUSTOM:test\""));
  }

  @Test
  public void deserialize_withFieldJsonAdapter_usesAdapter() {
    Gson gson = new Gson();
    String json = "{\"customField\":\"CUSTOM:hello\"}";

    FieldJsonAdapterPojo result = gson.fromJson(json, FieldJsonAdapterPojo.class);
    assertEquals("hello", result.customField);
  }

  // ==========================================================================
  // Reflection access filter tests
  // ==========================================================================

  @Test
  public void create_withBlockAllFilter_throwsException() {
    Gson gson =
        new GsonBuilder()
            .addReflectionAccessFilter(rawClass -> ReflectionAccessFilter.FilterResult.BLOCK_ALL)
            .create();

    try {
      gson.toJson(new SimplePojo());
      fail("Expected JsonIOException");
    } catch (JsonIOException e) {
      assertTrue(e.getMessage().contains("ReflectionAccessFilter does not permit"));
    }
  }

  @Test
  public void create_withAllowFilter_works() {
    Gson gson =
        new GsonBuilder()
            .addReflectionAccessFilter(rawClass -> ReflectionAccessFilter.FilterResult.ALLOW)
            .create();

    SimplePojo pojo = new SimplePojo();
    pojo.name = "Allowed";
    pojo.age = 100;

    String json = gson.toJson(pojo);
    assertTrue(json.contains("\"name\":\"Allowed\""));
    assertTrue(json.contains("\"age\":100"));
  }

  @Test
  public void create_withIndecisiveFilter_fallsThrough() {
    Gson gson =
        new GsonBuilder()
            .addReflectionAccessFilter(rawClass -> ReflectionAccessFilter.FilterResult.INDECISIVE)
            .create();

    SimplePojo pojo = new SimplePojo();
    pojo.name = "IndecisiveAllow";
    pojo.age = 55;

    String json = gson.toJson(pojo);
    assertTrue(json.contains("\"name\":\"IndecisiveAllow\""));
    assertTrue(json.contains("\"age\":55"));
  }

  @Test
  public void create_withMultipleFilters_lastAddedCheckedFirst() {
    final List<String> checkedClasses = new ArrayList<>();

    ReflectionAccessFilter filter1 =
        rawClass -> {
          checkedClasses.add("filter1:" + rawClass.getSimpleName());
          return ReflectionAccessFilter.FilterResult.INDECISIVE;
        };

    ReflectionAccessFilter filter2 =
        rawClass -> {
          checkedClasses.add("filter2:" + rawClass.getSimpleName());
          return ReflectionAccessFilter.FilterResult.ALLOW;
        };

    // Note: filters are added to the front of the list, so last-added is checked first
    Gson gson =
        new GsonBuilder()
            .addReflectionAccessFilter(filter1)
            .addReflectionAccessFilter(filter2)
            .create();

    SimplePojo pojo = new SimplePojo();
    pojo.name = "MultiFilter";

    String json = gson.toJson(pojo);

    assertNotNull(json);
    // filter2 was added last so it gets checked first
    // Since filter2 returns ALLOW, filter1 is NOT checked
    assertTrue(checkedClasses.contains("filter2:SimplePojo"));
    // filter1 should NOT be checked because filter2 returned ALLOW
    assertTrue(!checkedClasses.contains("filter1:SimplePojo"));
  }

  @Test
  public void create_withMultipleFilters_chainsThroughIndecisive() {
    final List<String> checkedClasses = new ArrayList<>();

    ReflectionAccessFilter filter1 =
        rawClass -> {
          checkedClasses.add("filter1:" + rawClass.getSimpleName());
          return ReflectionAccessFilter.FilterResult.ALLOW;
        };

    ReflectionAccessFilter filter2 =
        rawClass -> {
          checkedClasses.add("filter2:" + rawClass.getSimpleName());
          return ReflectionAccessFilter.FilterResult.INDECISIVE;
        };

    // Note: filters are added to the front, so filter2 is checked before filter1
    Gson gson =
        new GsonBuilder()
            .addReflectionAccessFilter(filter1)
            .addReflectionAccessFilter(filter2)
            .create();

    SimplePojo pojo = new SimplePojo();
    pojo.name = "MultiFilter";

    String json = gson.toJson(pojo);

    assertNotNull(json);
    // Both filters should be checked because filter2 returns INDECISIVE
    assertTrue(checkedClasses.contains("filter2:SimplePojo"));
    assertTrue(checkedClasses.contains("filter1:SimplePojo"));
  }

  // ==========================================================================
  // Duplicate field name tests
  // ==========================================================================

  @Test
  public void create_withDuplicateFieldNames_throwsException() {
    Gson gson = new Gson();

    try {
      gson.toJson(new DuplicateFieldNamePojo());
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertTrue(e.getMessage().contains("declares multiple JSON fields named"));
      assertTrue(e.getMessage().contains("duplicate"));
    }
  }

  @Test
  public void create_withDuplicateFieldNamesFromSerializedName_throwsException() {
    Gson gson = new Gson();

    try {
      gson.toJson(new DuplicateSerializedNamePojo());
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertTrue(e.getMessage().contains("declares multiple JSON fields"));
    }
  }

  @Test
  public void create_withDuplicateFromInheritance_throwsException() {
    Gson gson = new Gson();

    try {
      gson.toJson(new ChildWithDuplicateFieldPojo());
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertTrue(e.getMessage().contains("declares multiple JSON fields named"));
    }
  }

  // ==========================================================================
  // Inheritance tests
  // ==========================================================================

  @Test
  public void serialize_withInheritedFields_includesAllFields() {
    Gson gson = new Gson();
    ChildPojo child = new ChildPojo();
    child.parentField = "parent";
    child.childField = "child";

    String json = gson.toJson(child);
    assertTrue(json.contains("\"parentField\":\"parent\""));
    assertTrue(json.contains("\"childField\":\"child\""));
  }

  @Test
  public void deserialize_withInheritedFields_readsAllFields() {
    Gson gson = new Gson();
    String json = "{\"parentField\":\"fromParent\",\"childField\":\"fromChild\"}";

    ChildPojo result = gson.fromJson(json, ChildPojo.class);
    assertEquals("fromParent", result.parentField);
    assertEquals("fromChild", result.childField);
  }

  // ==========================================================================
  // Interface type tests
  // ==========================================================================

  @Test
  public void create_withInterfaceType_returnsEmptyAdapter() {
    Gson gson = new Gson();
    String json = gson.toJson(null, TestInterface.class);
    assertEquals("null", json);
  }

  // ==========================================================================
  // Null value handling for primitives
  // ==========================================================================

  @Test
  public void deserialize_nullForPrimitiveInt_setsDefault() {
    Gson gson = new Gson();
    String json = "{\"primitiveField\":null}";

    PrimitiveFieldPojo result = gson.fromJson(json, PrimitiveFieldPojo.class);
    assertEquals(0, result.primitiveField);
  }

  @Test
  public void deserialize_missingPrimitiveField_setsDefault() {
    Gson gson = new Gson();
    String json = "{}";

    PrimitiveFieldPojo result = gson.fromJson(json, PrimitiveFieldPojo.class);
    assertEquals(0, result.primitiveField);
  }

  // ==========================================================================
  // Direct self-reference avoidance test
  // ==========================================================================

  @Test
  public void serialize_withSelfReference_avoidsInfiniteLoop() {
    Gson gson = new Gson();
    SelfReferencePojo pojo = new SelfReferencePojo();
    pojo.name = "test";
    pojo.self = pojo;

    String json = gson.toJson(pojo);
    assertTrue(json.contains("\"name\":\"test\""));
    assertTrue(!json.contains("\"self\""));
  }

  // ==========================================================================
  // Round-trip tests
  // ==========================================================================

  @Test
  public void roundTrip_simplePojo_preservesData() {
    Gson gson = new Gson();
    SimplePojo original = new SimplePojo();
    original.name = "RoundTrip";
    original.age = 99;

    String json = gson.toJson(original);
    SimplePojo restored = gson.fromJson(json, SimplePojo.class);

    assertEquals(original.name, restored.name);
    assertEquals(original.age, restored.age);
  }

  @Test
  public void roundTrip_nestedPojo_preservesData() {
    Gson gson = new Gson();
    OuterPojo original = new OuterPojo();
    original.inner = new SimplePojo();
    original.inner.name = "Nested";
    original.inner.age = 77;

    String json = gson.toJson(original);
    OuterPojo restored = gson.fromJson(json, OuterPojo.class);

    assertNotNull(restored.inner);
    assertEquals(original.inner.name, restored.inner.name);
    assertEquals(original.inner.age, restored.inner.age);
  }

  @Test
  public void roundTrip_withSpecialCharacters_preservesData() {
    Gson gson = new Gson();
    SimplePojo original = new SimplePojo();
    original.name = "Special chars: \"quotes\", \\backslash, \n\t";
    original.age = 0;

    String json = gson.toJson(original);
    SimplePojo restored = gson.fromJson(json, SimplePojo.class);

    assertEquals(original.name, restored.name);
  }

  // ==========================================================================
  // Direct TypeAdapter usage tests
  // ==========================================================================

  @Test
  public void write_withDirectAdapter_writesJson() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<SimplePojo> adapter = gson.getAdapter(SimplePojo.class);

    SimplePojo pojo = new SimplePojo();
    pojo.name = "Direct";
    pojo.age = 42;

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, pojo);

    String json = stringWriter.toString();
    assertTrue(json.contains("\"name\":\"Direct\""));
    assertTrue(json.contains("\"age\":42"));
  }

  @Test
  public void read_withDirectAdapter_readsJson() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<SimplePojo> adapter = gson.getAdapter(SimplePojo.class);

    JsonReader reader = new JsonReader(new StringReader("{\"name\":\"Direct\",\"age\":33}"));
    SimplePojo result = adapter.read(reader);

    assertEquals("Direct", result.name);
    assertEquals(33, result.age);
  }

  @Test
  public void write_nullValue_writesNull() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<SimplePojo> adapter = gson.getAdapter(SimplePojo.class);

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, null);

    assertEquals("null", stringWriter.toString());
  }

  @Test
  public void read_nullValue_returnsNull() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<SimplePojo> adapter = gson.getAdapter(SimplePojo.class);

    JsonReader reader = new JsonReader(new StringReader("null"));
    SimplePojo result = adapter.read(reader);

    assertNull(result);
  }

  // ==========================================================================
  // Generic type tests
  // ==========================================================================

  @Test
  public void serialize_genericPojo_handlesTypeParameters() {
    Gson gson = new Gson();
    GenericPojo<String> pojo = new GenericPojo<>();
    pojo.value = "GenericValue";
    pojo.name = "GenericPojo";

    String json = gson.toJson(pojo);
    assertTrue(json.contains("\"value\":\"GenericValue\""));
    assertTrue(json.contains("\"name\":\"GenericPojo\""));
  }

  @Test
  public void deserialize_genericPojo_handlesTypeParameters() {
    Gson gson = new Gson();
    String json = "{\"value\":\"DeserializedGeneric\",\"name\":\"TestGeneric\"}";

    GenericPojo<String> result =
        gson.fromJson(json, new TypeToken<GenericPojo<String>>() {}.getType());

    assertEquals("DeserializedGeneric", result.value);
    assertEquals("TestGeneric", result.name);
  }

  // ==========================================================================
  // List and array field tests
  // ==========================================================================

  @Test
  public void serialize_pojoWithListField_serializesList() {
    Gson gson = new Gson();
    ListFieldPojo pojo = new ListFieldPojo();
    pojo.items = new ArrayList<>();
    pojo.items.add("item1");
    pojo.items.add("item2");

    String json = gson.toJson(pojo);
    assertTrue(json.contains("\"items\":[\"item1\",\"item2\"]"));
  }

  @Test
  public void deserialize_pojoWithListField_deserializesList() {
    Gson gson = new Gson();
    String json = "{\"items\":[\"a\",\"b\",\"c\"]}";

    ListFieldPojo result = gson.fromJson(json, ListFieldPojo.class);
    assertEquals(3, result.items.size());
    assertEquals("a", result.items.get(0));
    assertEquals("b", result.items.get(1));
    assertEquals("c", result.items.get(2));
  }

  @Test
  public void serialize_pojoWithMapField_serializesMap() {
    Gson gson = new Gson();
    MapFieldPojo pojo = new MapFieldPojo();
    pojo.data = new HashMap<>();
    pojo.data.put("key1", "value1");
    pojo.data.put("key2", "value2");

    String json = gson.toJson(pojo);
    assertTrue(json.contains("\"data\":"));
    assertTrue(json.contains("\"key1\":\"value1\""));
    assertTrue(json.contains("\"key2\":\"value2\""));
  }

  @Test
  public void deserialize_pojoWithMapField_deserializesMap() {
    Gson gson = new Gson();
    String json = "{\"data\":{\"k1\":\"v1\",\"k2\":\"v2\"}}";

    MapFieldPojo result = gson.fromJson(json, MapFieldPojo.class);
    assertEquals(2, result.data.size());
    assertEquals("v1", result.data.get("k1"));
    assertEquals("v2", result.data.get("k2"));
  }

  // ==========================================================================
  // Helper method to create factory
  // ==========================================================================

  private ReflectiveTypeAdapterFactory createFactory() {
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory =
        new JsonAdapterAnnotationTypeAdapterFactory(constructorConstructor);

    return new ReflectiveTypeAdapterFactory(
        constructorConstructor,
        FieldNamingPolicy.IDENTITY,
        Excluder.DEFAULT,
        jsonAdapterFactory,
        Collections.emptyList());
  }

  // ==========================================================================
  // Test helper classes
  // ==========================================================================

  static class SimplePojo {
    String name;
    int age;
  }

  static class OuterPojo {
    SimplePojo inner;
  }

  static class CamelCasePojo {
    String firstName;
    String lastName;
  }

  static class SerializedNamePojo {
    @SerializedName("customName")
    String renamedField;
  }

  static class SerializedNameAltPojo {
    @SerializedName(value = "primary", alternate = {"alt1", "alt2"})
    String fieldWithAlternates;
  }

  static class TransientFieldPojo {
    String normalField;
    transient String transientField;
  }

  static class StaticFieldPojo {
    String instanceField;
    static String staticField;
  }

  static class ExposePojo {
    @Expose String exposedField;
    String nonExposedField;
  }

  static class ExposeSerializeOnlyPojo {
    @Expose(serialize = true, deserialize = false)
    String writeOnlyField;
  }

  static class ExposeDeserializeOnlyPojo {
    @Expose(serialize = false, deserialize = true)
    String readOnlyField;
  }

  static class VersionedPojo {
    @Since(1.0)
    String fieldSince1;

    @Since(2.0)
    String fieldSince2;
  }

  static class UntilVersionedPojo {
    @Until(1.0)
    String deprecatedField;

    String currentField;
  }

  static class FieldJsonAdapterPojo {
    @JsonAdapter(CustomFieldAdapter.class)
    String customField;
  }

  public static class CustomFieldAdapter extends TypeAdapter<String> {
    @Override
    public void write(JsonWriter out, String value) throws IOException {
      out.value("CUSTOM:" + value);
    }

    @Override
    public String read(JsonReader in) throws IOException {
      String value = in.nextString();
      if (value.startsWith("CUSTOM:")) {
        return value.substring(7);
      }
      return value;
    }
  }

  static class DuplicateFieldNamePojo {
    @SerializedName("duplicate")
    String field1;

    @SerializedName("duplicate")
    String field2;
  }

  static class DuplicateSerializedNamePojo {
    String name;

    @SerializedName("name")
    String otherField;
  }

  static class ParentWithField {
    @SerializedName("duplicate")
    String parentField;
  }

  static class ChildWithDuplicateFieldPojo extends ParentWithField {
    @SerializedName("duplicate")
    String childField;
  }

  static class ParentPojo {
    String parentField;
  }

  static class ChildPojo extends ParentPojo {
    String childField;
  }

  interface TestInterface {
    void method();
  }

  static class PrimitiveFieldPojo {
    int primitiveField;
  }

  static class SelfReferencePojo {
    String name;
    SelfReferencePojo self;
  }

  static class GenericPojo<T> {
    T value;
    String name;
  }

  static class ListFieldPojo {
    List<String> items;
  }

  static class MapFieldPojo {
    Map<String, String> data;
  }
}
