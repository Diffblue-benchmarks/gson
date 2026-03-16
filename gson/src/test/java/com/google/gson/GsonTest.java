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

import com.google.gson.reflect.TypeToken;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class GsonTest {

  @Test
  public void testConstructor() {
    Gson gson = new Gson();
    assertThat(gson).isNotNull();
  }

  @Test
  public void testToJson_primitiveInt() {
    Gson gson = new Gson();
    String json = gson.toJson(123);
    assertThat(json).isEqualTo("123");
  }

  @Test
  public void testToJson_primitiveString() {
    Gson gson = new Gson();
    String json = gson.toJson("hello");
    assertThat(json).isEqualTo("\"hello\"");
  }

  @Test
  public void testToJson_primitiveBoolean() {
    Gson gson = new Gson();
    assertThat(gson.toJson(true)).isEqualTo("true");
    assertThat(gson.toJson(false)).isEqualTo("false");
  }

  @Test
  public void testToJson_null() {
    Gson gson = new Gson();
    String json = gson.toJson(null);
    assertThat(json).isEqualTo("null");
  }

  @Test
  public void testToJson_simpleObject() {
    Gson gson = new Gson();
    SimpleClass obj = new SimpleClass();
    obj.name = "test";
    obj.value = 42;
    String json = gson.toJson(obj);
    assertThat(json).contains("\"name\":\"test\"");
    assertThat(json).contains("\"value\":42");
  }

  @Test
  public void testFromJson_primitiveInt() {
    Gson gson = new Gson();
    Integer result = gson.fromJson("123", Integer.class);
    assertThat(result).isEqualTo(123);
  }

  @Test
  public void testFromJson_primitiveString() {
    Gson gson = new Gson();
    String result = gson.fromJson("\"hello\"", String.class);
    assertThat(result).isEqualTo("hello");
  }

  @Test
  public void testFromJson_primitiveBoolean() {
    Gson gson = new Gson();
    assertThat(gson.fromJson("true", Boolean.class)).isTrue();
    assertThat(gson.fromJson("false", Boolean.class)).isFalse();
  }

  @Test
  public void testFromJson_null() {
    Gson gson = new Gson();
    Object result = gson.fromJson("null", Object.class);
    assertThat(result).isNull();
  }

  @Test
  public void testFromJson_simpleObject() {
    Gson gson = new Gson();
    String json = "{\"name\":\"test\",\"value\":42}";
    SimpleClass result = gson.fromJson(json, SimpleClass.class);
    assertThat(result.name).isEqualTo("test");
    assertThat(result.value).isEqualTo(42);
  }

  @Test
  public void testToJsonTree_primitiveInt() {
    Gson gson = new Gson();
    JsonElement element = gson.toJsonTree(123);
    assertThat(element.isJsonPrimitive()).isTrue();
    assertThat(element.getAsInt()).isEqualTo(123);
  }

  @Test
  public void testToJsonTree_simpleObject() {
    Gson gson = new Gson();
    SimpleClass obj = new SimpleClass();
    obj.name = "test";
    obj.value = 42;
    JsonElement element = gson.toJsonTree(obj);
    assertThat(element.isJsonObject()).isTrue();
    JsonObject jsonObject = element.getAsJsonObject();
    assertThat(jsonObject.get("name").getAsString()).isEqualTo("test");
    assertThat(jsonObject.get("value").getAsInt()).isEqualTo(42);
  }

  @Test
  public void testFromJson_jsonElement() {
    Gson gson = new Gson();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("name", "test");
    jsonObject.addProperty("value", 42);
    SimpleClass result = gson.fromJson(jsonObject, SimpleClass.class);
    assertThat(result.name).isEqualTo("test");
    assertThat(result.value).isEqualTo(42);
  }

  @Test
  public void testToJson_array() {
    Gson gson = new Gson();
    int[] array = {1, 2, 3};
    String json = gson.toJson(array);
    assertThat(json).isEqualTo("[1,2,3]");
  }

  @Test
  public void testFromJson_array() {
    Gson gson = new Gson();
    String json = "[1,2,3]";
    int[] result = gson.fromJson(json, int[].class);
    assertThat(result).asList().containsExactly(1, 2, 3).inOrder();
  }

  @Test
  public void testToJson_list() {
    Gson gson = new Gson();
    List<String> list = new ArrayList<>();
    list.add("a");
    list.add("b");
    String json = gson.toJson(list);
    assertThat(json).isEqualTo("[\"a\",\"b\"]");
  }

  @Test
  public void testFromJson_list() {
    Gson gson = new Gson();
    String json = "[\"a\",\"b\"]";
    @SuppressWarnings("unchecked")
    List<String> result =
        gson.fromJson(json, new TypeToken<List<String>>() {}.getType());
    assertThat(result).containsExactly("a", "b").inOrder();
  }

  @Test
  public void testToJson_map() {
    Gson gson = new Gson();
    Map<String, Integer> map = new HashMap<>();
    map.put("one", 1);
    map.put("two", 2);
    String json = gson.toJson(map);
    assertThat(json).contains("\"one\":1");
    assertThat(json).contains("\"two\":2");
  }

  @Test
  public void testFromJson_map() {
    Gson gson = new Gson();
    String json = "{\"one\":1,\"two\":2}";
    @SuppressWarnings("unchecked")
    Map<String, Integer> result =
        gson.fromJson(json, new TypeToken<Map<String, Integer>>() {}.getType());
    assertThat(result).containsEntry("one", 1);
    assertThat(result).containsEntry("two", 2);
  }

  @Test
  public void testToJson_withWriter() throws Exception {
    Gson gson = new Gson();
    StringWriter writer = new StringWriter();
    gson.toJson(123, writer);
    assertThat(writer.toString()).isEqualTo("123");
  }

  @Test
  public void testFromJson_withReader() {
    Gson gson = new Gson();
    StringReader reader = new StringReader("123");
    Integer result = gson.fromJson(reader, Integer.class);
    assertThat(result).isEqualTo(123);
  }

  @Test
  public void testFromJson_invalidJson_throwsException() {
    Gson gson = new Gson();
    assertThrows(JsonSyntaxException.class, () -> gson.fromJson("{invalid}", Object.class));
  }

  @Test
  public void testToJson_bigInteger() {
    Gson gson = new Gson();
    BigInteger bigInt = new BigInteger("12345678901234567890");
    String json = gson.toJson(bigInt);
    assertThat(json).isEqualTo("12345678901234567890");
  }

  @Test
  public void testFromJson_bigInteger() {
    Gson gson = new Gson();
    BigInteger result = gson.fromJson("12345678901234567890", BigInteger.class);
    assertThat(result).isEqualTo(new BigInteger("12345678901234567890"));
  }

  @Test
  public void testToJson_bigDecimal() {
    Gson gson = new Gson();
    BigDecimal bigDec = new BigDecimal("123.456");
    String json = gson.toJson(bigDec);
    assertThat(json).isEqualTo("123.456");
  }

  @Test
  public void testFromJson_bigDecimal() {
    Gson gson = new Gson();
    BigDecimal result = gson.fromJson("123.456", BigDecimal.class);
    assertThat(result).isEqualTo(new BigDecimal("123.456"));
  }

  @Test
  public void testToJson_nestedObject() {
    Gson gson = new Gson();
    NestedClass nested = new NestedClass();
    nested.inner = new SimpleClass();
    nested.inner.name = "inner";
    nested.inner.value = 10;
    nested.outer = "outer";

    String json = gson.toJson(nested);
    assertThat(json).contains("\"outer\":\"outer\"");
    assertThat(json).contains("\"inner\":{");
    assertThat(json).contains("\"name\":\"inner\"");
  }

  @Test
  public void testFromJson_nestedObject() {
    Gson gson = new Gson();
    String json = "{\"outer\":\"outer\",\"inner\":{\"name\":\"inner\",\"value\":10}}";
    NestedClass result = gson.fromJson(json, NestedClass.class);
    assertThat(result.outer).isEqualTo("outer");
    assertThat(result.inner.name).isEqualTo("inner");
    assertThat(result.inner.value).isEqualTo(10);
  }

  @Test
  public void testToJson_withTypeToken() {
    Gson gson = new Gson();
    List<String> list = new ArrayList<>();
    list.add("a");
    list.add("b");
    String json = gson.toJson(list, new TypeToken<List<String>>() {}.getType());
    assertThat(json).isEqualTo("[\"a\",\"b\"]");
  }


  @Test
  public void testToJson_long() {
    Gson gson = new Gson();
    assertThat(gson.toJson(Long.MAX_VALUE)).isEqualTo(String.valueOf(Long.MAX_VALUE));
    assertThat(gson.toJson(Long.MIN_VALUE)).isEqualTo(String.valueOf(Long.MIN_VALUE));
  }

  @Test
  public void testFromJson_long() {
    Gson gson = new Gson();
    Long result = gson.fromJson(String.valueOf(Long.MAX_VALUE), Long.class);
    assertThat(result).isEqualTo(Long.MAX_VALUE);
  }

  @Test
  public void testToJson_double() {
    Gson gson = new Gson();
    assertThat(gson.toJson(123.456)).isEqualTo("123.456");
  }

  @Test
  public void testFromJson_double() {
    Gson gson = new Gson();
    Double result = gson.fromJson("123.456", Double.class);
    assertThat(result).isEqualTo(123.456);
  }

  @Test
  public void testToJson_character() {
    Gson gson = new Gson();
    assertThat(gson.toJson('a')).isEqualTo("\"a\"");
  }

  @Test
  public void testFromJson_character() {
    Gson gson = new Gson();
    Character result = gson.fromJson("\"a\"", Character.class);
    assertThat(result).isEqualTo('a');
  }

  private static class SimpleClass {
    String name;
    int value;
  }

  private static class NestedClass {
    String outer;
    SimpleClass inner;
  }
}
