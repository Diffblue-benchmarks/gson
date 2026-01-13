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

import static org.junit.Assert.assertTrue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * Tests for the private {@code isReflective} method in {@link TypeAdapterRuntimeTypeWrapper}.
 *
 * <p>The isReflective method is called during write() when:
 * <ul>
 *   <li>The runtime type differs from the declared type</li>
 *   <li>The runtime type's adapter IS a ReflectiveTypeAdapterFactory.Adapter</li>
 * </ul>
 *
 * <p>The method unwraps SerializationDelegatingTypeAdapter chains to determine if the underlying
 * adapter is reflective. This test class covers the various branches in that unwrapping logic.
 */
public class TypeAdapterRuntimeTypeWrapperClaude_isReflectiveTest {

  // ==========================================================================
  // Test classes for polymorphism
  // ==========================================================================

  static class Base {
    String baseField = "base";
  }

  static class Derived extends Base {
    String derivedField = "derived";
  }

  // ==========================================================================
  // Tests for isReflective - SerializationDelegatingTypeAdapter that returns this
  // Covers lines 84-85, 87-88 (the break condition when delegate == typeAdapter)
  // ==========================================================================

  /**
   * Tests the isReflective method when the delegate adapter is a SerializationDelegatingTypeAdapter
   * that returns itself from getSerializationDelegate() (i.e., TreeTypeAdapter with a serializer).
   *
   * <p>This covers the branch where delegate == typeAdapter, triggering the break statement.
   * When a JsonSerializer is registered, TreeTypeAdapter.getSerializationDelegate() returns 'this'.
   */
  @Test
  public void write_withJsonSerializerForBase_checksIsReflectiveWithSelfDelegate() {
    // Register a JsonSerializer for Base - this creates a TreeTypeAdapter
    // where getSerializationDelegate() returns 'this' (same instance)
    Gson gson =
        new GsonBuilder()
            .registerTypeAdapter(
                Base.class,
                new JsonSerializer<Base>() {
                  @Override
                  public JsonElement serialize(Base src, Type typeOfSrc, JsonSerializationContext context) {
                    JsonObject obj = new JsonObject();
                    obj.addProperty("serialized", "byJsonSerializer");
                    return obj;
                  }
                })
            .create();

    List<Base> list = new ArrayList<>();
    list.add(new Derived());

    // Must use explicit type so Gson knows element type is Base
    Type listType = new TypeToken<List<Base>>() {}.getType();
    String json = gson.toJson(list, listType);

    // The JsonSerializer for Base should be used because:
    // 1. Runtime type (Derived) has a reflective adapter
    // 2. isReflective(delegate) is called where delegate is a TreeTypeAdapter
    // 3. TreeTypeAdapter.getSerializationDelegate() returns 'this' (has serializer)
    // 4. Since 'this' == delegate in the loop, it breaks immediately
    // 5. TreeTypeAdapter is NOT instanceof ReflectiveTypeAdapterFactory.Adapter
    // 6. So isReflective returns false, and the delegate (JsonSerializer) is used
    assertTrue(json.contains("\"serialized\":\"byJsonSerializer\""));
  }

  // ==========================================================================
  // Tests for isReflective - SerializationDelegatingTypeAdapter that delegates
  // Covers lines 84-85, 90-91 (the loop continuation when delegate != typeAdapter)
  // ==========================================================================

  /**
   * Tests the isReflective method when the delegate adapter is a SerializationDelegatingTypeAdapter
   * that returns a different adapter from getSerializationDelegate() (i.e., TreeTypeAdapter with
   * only a deserializer, which delegates to the reflective adapter).
   *
   * <p>This covers the branch where delegate != typeAdapter, continuing the loop.
   * When only a JsonDeserializer is registered, TreeTypeAdapter.getSerializationDelegate()
   * returns delegate() which is typically a ReflectiveTypeAdapterFactory.Adapter.
   */
  @Test
  public void write_withJsonDeserializerOnlyForBase_checksIsReflectiveWithDifferentDelegate() {
    // Register only a JsonDeserializer for Base - this creates a TreeTypeAdapter
    // where getSerializationDelegate() returns delegate() (different instance)
    // The delegate is the reflective adapter for Base
    Gson gson =
        new GsonBuilder()
            .registerTypeAdapter(
                Base.class,
                new JsonDeserializer<Base>() {
                  @Override
                  public Base deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                      throws JsonParseException {
                    // Deserializer logic - not relevant for this test
                    return new Base();
                  }
                })
            .create();

    List<Base> list = new ArrayList<>();
    list.add(new Derived());

    // Must use explicit type so Gson knows element type is Base
    Type listType = new TypeToken<List<Base>>() {}.getType();
    String json = gson.toJson(list, listType);

    // The reflective adapter for Derived should be used because:
    // 1. Runtime type (Derived) has a reflective adapter
    // 2. isReflective(delegate) is called where delegate is a TreeTypeAdapter (for Base)
    // 3. TreeTypeAdapter.getSerializationDelegate() returns delegate() (reflective adapter)
    // 4. Since delegate() != TreeTypeAdapter 'this', the loop continues
    // 5. The unwrapped adapter IS a ReflectiveTypeAdapterFactory.Adapter
    // 6. So isReflective returns true, and runtimeTypeAdapter (for Derived) is used
    assertTrue(json.contains("\"derivedField\":\"derived\""));
    assertTrue(json.contains("\"baseField\":\"base\""));
  }

  // ==========================================================================
  // Additional test - nested SerializationDelegatingTypeAdapter chain
  // Covers multiple iterations of the while loop (lines 83-91)
  // ==========================================================================

  /**
   * Tests that isReflective correctly unwraps through multiple nested
   * SerializationDelegatingTypeAdapter instances.
   *
   * <p>This test uses both JsonSerializer and JsonDeserializer to create
   * more complex adapter chains.
   */
  @Test
  public void write_withNestedDelegatingAdapters_unwrapsCorrectly() {
    // Register both JsonSerializer and JsonDeserializer for Base
    // This still creates a TreeTypeAdapter where getSerializationDelegate() returns 'this'
    // because the serializer is present
    Gson gson =
        new GsonBuilder()
            .registerTypeAdapter(
                Base.class,
                new JsonSerializer<Base>() {
                  @Override
                  public JsonElement serialize(Base src, Type typeOfSrc, JsonSerializationContext context) {
                    JsonObject obj = new JsonObject();
                    obj.addProperty("nestedSerialized", src.getClass().getSimpleName());
                    return obj;
                  }
                })
            .registerTypeAdapter(
                Base.class,
                new JsonDeserializer<Base>() {
                  @Override
                  public Base deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                      throws JsonParseException {
                    return new Base();
                  }
                })
            .create();

    List<Base> list = new ArrayList<>();
    list.add(new Derived());

    Type listType = new TypeToken<List<Base>>() {}.getType();
    String json = gson.toJson(list, listType);

    // The custom serializer should be used
    assertTrue(json.contains("\"nestedSerialized\":\"Derived\""));
  }

  // ==========================================================================
  // Test without any custom adapters - baseline behavior
  // ==========================================================================

  /**
   * Tests the baseline behavior where both base and derived types use reflective adapters.
   * In this case, isReflective(delegate) returns true and the runtime type adapter is used.
   */
  @Test
  public void write_withNoCustomAdapters_usesRuntimeTypeReflectiveAdapter() {
    Gson gson = new Gson();

    List<Base> list = new ArrayList<>();
    list.add(new Derived());

    Type listType = new TypeToken<List<Base>>() {}.getType();
    String json = gson.toJson(list, listType);

    // Both adapters are reflective, so runtime type adapter wins
    assertTrue(json.contains("\"derivedField\":\"derived\""));
    assertTrue(json.contains("\"baseField\":\"base\""));
  }

  // ==========================================================================
  // Edge case - JsonSerializer that delegates back to Gson
  // ==========================================================================

  /**
   * Tests isReflective when the JsonSerializer uses context.serialize() which may
   * create additional adapter wrapping.
   */
  @Test
  public void write_withJsonSerializerUsingContext_checksIsReflective() {
    Gson gson =
        new GsonBuilder()
            .registerTypeAdapter(
                Base.class,
                new JsonSerializer<Base>() {
                  @Override
                  public JsonElement serialize(Base src, Type typeOfSrc, JsonSerializationContext context) {
                    JsonObject obj = new JsonObject();
                    obj.addProperty("customWrapper", true);
                    obj.addProperty("baseField", src.baseField);
                    // If it's a Derived, also add derived field manually
                    if (src instanceof Derived) {
                      obj.addProperty("derivedField", ((Derived) src).derivedField);
                    }
                    return obj;
                  }
                })
            .create();

    List<Base> list = new ArrayList<>();
    list.add(new Derived());

    Type listType = new TypeToken<List<Base>>() {}.getType();
    String json = gson.toJson(list, listType);

    // Custom serializer handles both Base and Derived
    assertTrue(json.contains("\"customWrapper\":true"));
    assertTrue(json.contains("\"baseField\":\"base\""));
    assertTrue(json.contains("\"derivedField\":\"derived\""));
  }

  // ==========================================================================
  // Test with type hierarchy adapter
  // ==========================================================================

  /**
   * Tests isReflective when using registerTypeHierarchyAdapter which affects all
   * subclasses including Derived.
   */
  @Test
  public void write_withTypeHierarchyJsonSerializer_usesSerializerForAllSubtypes() {
    Gson gson =
        new GsonBuilder()
            .registerTypeHierarchyAdapter(
                Base.class,
                new JsonSerializer<Base>() {
                  @Override
                  public JsonElement serialize(Base src, Type typeOfSrc, JsonSerializationContext context) {
                    JsonObject obj = new JsonObject();
                    obj.addProperty("hierarchy", src.getClass().getSimpleName());
                    return obj;
                  }
                })
            .create();

    List<Base> list = new ArrayList<>();
    list.add(new Derived());

    Type listType = new TypeToken<List<Base>>() {}.getType();
    String json = gson.toJson(list, listType);

    // Hierarchy adapter applies to Derived as well, so runtime type adapter
    // is NOT a ReflectiveTypeAdapterFactory.Adapter - it uses the hierarchy adapter
    assertTrue(json.contains("\"hierarchy\":\"Derived\""));
  }
}
