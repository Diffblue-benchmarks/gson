/*
 * Copyright (C) 2010 Google Inc.
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
package com.google.gson.protobuf.functional;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.protobuf.ProtoTypeAdapter;
import com.google.gson.protobuf.ProtoTypeAdapter.EnumSerialization;
import com.google.gson.protobuf.generated.Bag.ProtoWithRepeatedEnum;
import com.google.gson.protobuf.generated.Bag.ProtoWithRepeatedEnum.Status;
import com.google.protobuf.DynamicMessage;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Message;
import org.junit.Before;
import org.junit.Test;

/**
 * Functional tests for protocol buffers with repeated enum fields.
 *
 * @author Generated for coverage improvement
 */
public class ProtoTypeAdapterRepeatedEnumTest {
  private Gson gson;
  private Gson gsonWithEnumNumbers;

  @Before
  public void setUp() throws Exception {
    ProtoTypeAdapter protoTypeAdapter =
        ProtoTypeAdapter.newBuilder()
            .setEnumSerialization(EnumSerialization.NAME)
            .build();
    gson =
        new GsonBuilder()
            .registerTypeHierarchyAdapter(GeneratedMessage.class, protoTypeAdapter)
            .create();
    gsonWithEnumNumbers =
        new GsonBuilder()
            .registerTypeHierarchyAdapter(
                GeneratedMessage.class,
                ProtoTypeAdapter.newBuilder()
                    .setEnumSerialization(EnumSerialization.NUMBER)
                    .build())
            .create();
  }

  @Test
  public void testSerializeRepeatedEnum() {
    ProtoWithRepeatedEnum proto =
        ProtoWithRepeatedEnum.newBuilder()
            .addStatuses(Status.PENDING)
            .addStatuses(Status.ACTIVE)
            .addStatuses(Status.COMPLETED)
            .setName("test")
            .build();
    String json = gson.toJson(proto);
    assertThat(json).isEqualTo("{\"statuses\":[\"PENDING\",\"ACTIVE\",\"COMPLETED\"],\"name\":\"test\"}");
  }

  @Test
  public void testSerializeRepeatedEnum_withNumbers() {
    ProtoWithRepeatedEnum proto =
        ProtoWithRepeatedEnum.newBuilder()
            .addStatuses(Status.PENDING)
            .addStatuses(Status.ACTIVE)
            .setName("numbers")
            .build();
    String json = gsonWithEnumNumbers.toJson(proto);
    assertThat(json).isEqualTo("{\"statuses\":[1,2],\"name\":\"numbers\"}");
  }

  @Test
  public void testDeserializeRepeatedEnum() {
    String json = "{\"statuses\":[\"PENDING\",\"ACTIVE\",\"COMPLETED\"],\"name\":\"test\"}";
    ProtoWithRepeatedEnum proto = gson.fromJson(json, ProtoWithRepeatedEnum.class);
    assertThat(proto.getStatusesList()).containsExactly(Status.PENDING, Status.ACTIVE, Status.COMPLETED).inOrder();
    assertThat(proto.getName()).isEqualTo("test");
  }

  @Test
  public void testDeserializeRepeatedEnum_withNumbers() {
    String json = "{\"statuses\":[1,2,3],\"name\":\"numbers\"}";
    ProtoWithRepeatedEnum proto = gsonWithEnumNumbers.fromJson(json, ProtoWithRepeatedEnum.class);
    assertThat(proto.getStatusesList()).containsExactly(Status.PENDING, Status.ACTIVE, Status.COMPLETED).inOrder();
    assertThat(proto.getName()).isEqualTo("numbers");
  }

  @Test
  public void testDeserializeRepeatedEnum_emptyArray() {
    String json = "{\"statuses\":[],\"name\":\"empty\"}";
    ProtoWithRepeatedEnum proto = gson.fromJson(json, ProtoWithRepeatedEnum.class);
    assertThat(proto.getStatusesList()).isEmpty();
    assertThat(proto.getName()).isEqualTo("empty");
  }

  @Test
  public void testDeserializeDynamicMessage_throwsException() {
    // Register adapter for Message.class to cover DynamicMessage path
    Gson gsonForMessage =
        new GsonBuilder()
            .registerTypeHierarchyAdapter(
                Message.class,
                ProtoTypeAdapter.newBuilder()
                    .setEnumSerialization(EnumSerialization.NAME)
                    .build())
            .create();
    String json = "{}";
    JsonParseException e =
        assertThrows(JsonParseException.class, () -> gsonForMessage.fromJson(json, DynamicMessage.class));
    assertThat(e).hasMessageThat().isEqualTo("Error while parsing proto");
    assertThat(e).hasCauseThat().hasMessageThat().isEqualTo("only generated messages are supported");
  }
}
