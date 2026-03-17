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

import static com.google.common.truth.Truth.assertThat;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import org.junit.Test;

public class TreeTypeAdapterGsonContextImplTest {

  static class TestData {
    String value;
    int number;

    TestData(String value, int number) {
      this.value = value;
      this.number = number;
    }
  }

  static class NestedData {
    TestData testData;

    NestedData(TestData testData) {
      this.testData = testData;
    }
  }

  @Test
  public void testSerializeWithoutType() {
    JsonSerializer<TestData> serializer =
        new JsonSerializer<TestData>() {
          @Override
          public JsonElement serialize(
              TestData src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject obj = new JsonObject();
            obj.addProperty("custom", true);
            obj.add("value", context.serialize(src.value));
            obj.add("number", context.serialize(src.number));
            return obj;
          }
        };

    Gson gson =
        new Gson()
            .newBuilder()
            .registerTypeAdapter(TestData.class, serializer)
            .create();

    TestData data = new TestData("test", 42);
    JsonElement result = gson.toJsonTree(data);

    assertThat(result).isInstanceOf(JsonObject.class);
    JsonObject obj = (JsonObject) result;
    assertThat(obj.get("custom").getAsBoolean()).isTrue();
    assertThat(obj.get("value").getAsString()).isEqualTo("test");
    assertThat(obj.get("number").getAsInt()).isEqualTo(42);
  }

  @Test
  public void testSerializeWithType() {
    JsonSerializer<NestedData> serializer =
        new JsonSerializer<NestedData>() {
          @Override
          public JsonElement serialize(
              NestedData src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject obj = new JsonObject();
            obj.add("testData", context.serialize(src.testData, TestData.class));
            return obj;
          }
        };

    Gson gson =
        new Gson()
            .newBuilder()
            .registerTypeAdapter(NestedData.class, serializer)
            .create();

    TestData testData = new TestData("nested", 100);
    NestedData data = new NestedData(testData);
    JsonElement result = gson.toJsonTree(data);

    assertThat(result).isInstanceOf(JsonObject.class);
    JsonObject obj = (JsonObject) result;
    assertThat(obj.has("testData")).isTrue();
    JsonElement testDataElement = obj.get("testData");
    assertThat(testDataElement).isInstanceOf(JsonObject.class);
    JsonObject testDataObj = (JsonObject) testDataElement;
    assertThat(testDataObj.get("value").getAsString()).isEqualTo("nested");
    assertThat(testDataObj.get("number").getAsInt()).isEqualTo(100);
  }

  @Test
  public void testDeserialize() {
    JsonDeserializer<TestData> deserializer =
        new JsonDeserializer<TestData>() {
          @Override
          public TestData deserialize(
              JsonElement json, Type typeOfT, JsonDeserializationContext context)
              throws JsonParseException {
            JsonObject obj = json.getAsJsonObject();
            String value = context.deserialize(obj.get("value"), String.class);
            Integer number = context.deserialize(obj.get("number"), Integer.class);
            return new TestData(value, number);
          }
        };

    Gson gson =
        new Gson()
            .newBuilder()
            .registerTypeAdapter(TestData.class, deserializer)
            .create();

    JsonObject json = new JsonObject();
    json.add("value", new JsonPrimitive("deserialized"));
    json.add("number", new JsonPrimitive(123));

    TestData result = gson.fromJson(json, TestData.class);

    assertThat(result).isNotNull();
    assertThat(result.value).isEqualTo("deserialized");
    assertThat(result.number).isEqualTo(123);
  }

  @Test
  public void testDeserializeNested() {
    JsonDeserializer<NestedData> deserializer =
        new JsonDeserializer<NestedData>() {
          @Override
          public NestedData deserialize(
              JsonElement json, Type typeOfT, JsonDeserializationContext context)
              throws JsonParseException {
            JsonObject obj = json.getAsJsonObject();
            TestData testData = context.deserialize(obj.get("testData"), TestData.class);
            return new NestedData(testData);
          }
        };

    Gson gson =
        new Gson()
            .newBuilder()
            .registerTypeAdapter(NestedData.class, deserializer)
            .create();

    JsonObject testDataJson = new JsonObject();
    testDataJson.add("value", new JsonPrimitive("nested-value"));
    testDataJson.add("number", new JsonPrimitive(456));

    JsonObject json = new JsonObject();
    json.add("testData", testDataJson);

    NestedData result = gson.fromJson(json, NestedData.class);

    assertThat(result).isNotNull();
    assertThat(result.testData).isNotNull();
    assertThat(result.testData.value).isEqualTo("nested-value");
    assertThat(result.testData.number).isEqualTo(456);
  }

  @Test
  public void testSerializeNull() {
    JsonSerializer<TestData> serializer =
        new JsonSerializer<TestData>() {
          @Override
          public JsonElement serialize(
              TestData src, Type typeOfSrc, JsonSerializationContext context) {
            return context.serialize(null);
          }
        };

    Gson gson =
        new Gson()
            .newBuilder()
            .registerTypeAdapter(TestData.class, serializer)
            .create();

    TestData data = new TestData("test", 42);
    JsonElement result = gson.toJsonTree(data);

    assertThat(result).isNotNull();
    assertThat(result.isJsonNull()).isTrue();
  }
}
