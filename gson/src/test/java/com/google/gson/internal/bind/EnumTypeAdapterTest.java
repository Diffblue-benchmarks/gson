/*
 * Copyright (C) 2024 Google Inc.
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

package com.google.gson.internal.bind;

import static com.google.common.truth.Truth.assertThat;

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

public class EnumTypeAdapterTest {

  private enum SimpleEnum {
    VALUE_A,
    VALUE_B,
    VALUE_C
  }

  private enum EnumWithSerializedName {
    @SerializedName("custom_a")
    VALUE_A,
    @SerializedName("custom_b")
    VALUE_B
  }

  private enum EnumWithAlternateNames {
    @SerializedName(value = "primary", alternate = {"alt1", "alt2"})
    VALUE_A,
    @SerializedName(value = "secondary", alternate = {"alt3"})
    VALUE_B
  }

  private enum EnumWithToString {
    VALUE_A {
      @Override
      public String toString() {
        return "custom_string_a";
      }
    },
    VALUE_B {
      @Override
      public String toString() {
        return "custom_string_b";
      }
    }
  }

  @Test
  public void testReadSimpleEnum() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<SimpleEnum> adapter = gson.getAdapter(TypeToken.get(SimpleEnum.class));

    JsonReader reader = new JsonReader(new StringReader("\"VALUE_A\""));
    SimpleEnum result = adapter.read(reader);

    assertThat(result).isEqualTo(SimpleEnum.VALUE_A);
  }

  @Test
  public void testReadNull() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<SimpleEnum> adapter = gson.getAdapter(TypeToken.get(SimpleEnum.class));

    JsonReader reader = new JsonReader(new StringReader("null"));
    SimpleEnum result = adapter.read(reader);

    assertThat(result).isNull();
  }

  @Test
  public void testReadEnumWithSerializedName() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<EnumWithSerializedName> adapter = gson.getAdapter(TypeToken.get(EnumWithSerializedName.class));

    JsonReader reader = new JsonReader(new StringReader("\"custom_a\""));
    EnumWithSerializedName result = adapter.read(reader);

    assertThat(result).isEqualTo(EnumWithSerializedName.VALUE_A);
  }

  @Test
  public void testReadEnumWithAlternateName() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<EnumWithAlternateNames> adapter = gson.getAdapter(TypeToken.get(EnumWithAlternateNames.class));

    JsonReader reader = new JsonReader(new StringReader("\"alt1\""));
    EnumWithAlternateNames result = adapter.read(reader);

    assertThat(result).isEqualTo(EnumWithAlternateNames.VALUE_A);
  }

  @Test
  public void testReadEnumWithAlternateNameSecond() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<EnumWithAlternateNames> adapter = gson.getAdapter(TypeToken.get(EnumWithAlternateNames.class));

    JsonReader reader = new JsonReader(new StringReader("\"alt2\""));
    EnumWithAlternateNames result = adapter.read(reader);

    assertThat(result).isEqualTo(EnumWithAlternateNames.VALUE_A);
  }

  @Test
  public void testReadEnumWithPrimaryName() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<EnumWithAlternateNames> adapter = gson.getAdapter(TypeToken.get(EnumWithAlternateNames.class));

    JsonReader reader = new JsonReader(new StringReader("\"primary\""));
    EnumWithAlternateNames result = adapter.read(reader);

    assertThat(result).isEqualTo(EnumWithAlternateNames.VALUE_A);
  }

  @Test
  public void testReadEnumByToString() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<EnumWithToString> adapter = gson.getAdapter(TypeToken.get(EnumWithToString.class));

    JsonReader reader = new JsonReader(new StringReader("\"custom_string_a\""));
    EnumWithToString result = adapter.read(reader);

    assertThat(result).isEqualTo(EnumWithToString.VALUE_A);
  }

  @Test
  public void testReadNonExistentValue() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<SimpleEnum> adapter = gson.getAdapter(TypeToken.get(SimpleEnum.class));

    JsonReader reader = new JsonReader(new StringReader("\"NON_EXISTENT\""));
    SimpleEnum result = adapter.read(reader);

    assertThat(result).isNull();
  }

  @Test
  public void testWriteSimpleEnum() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<SimpleEnum> adapter = gson.getAdapter(TypeToken.get(SimpleEnum.class));

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, SimpleEnum.VALUE_A);

    assertThat(stringWriter.toString()).isEqualTo("\"VALUE_A\"");
  }

  @Test
  public void testWriteNull() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<SimpleEnum> adapter = gson.getAdapter(TypeToken.get(SimpleEnum.class));

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, null);

    assertThat(stringWriter.toString()).isEqualTo("null");
  }

  @Test
  public void testWriteEnumWithSerializedName() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<EnumWithSerializedName> adapter = gson.getAdapter(TypeToken.get(EnumWithSerializedName.class));

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, EnumWithSerializedName.VALUE_A);

    assertThat(stringWriter.toString()).isEqualTo("\"custom_a\"");
  }

  @Test
  public void testWriteEnumWithAlternateNames() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<EnumWithAlternateNames> adapter = gson.getAdapter(TypeToken.get(EnumWithAlternateNames.class));

    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, EnumWithAlternateNames.VALUE_A);

    assertThat(stringWriter.toString()).isEqualTo("\"primary\"");
  }

  @Test
  public void testFactoryReturnsNullForEnumBaseClass() {
    Gson gson = new Gson();
    @SuppressWarnings("rawtypes")
    TypeAdapter<Enum> adapter = EnumTypeAdapter.FACTORY.create(gson, TypeToken.get(Enum.class));

    assertThat(adapter).isNull();
  }

  @Test
  public void testFactoryReturnsNullForNonEnumClass() {
    Gson gson = new Gson();
    TypeAdapter<String> adapter = EnumTypeAdapter.FACTORY.create(gson, TypeToken.get(String.class));

    assertThat(adapter).isNull();
  }

  @Test
  public void testFactoryCreatesAdapterForEnum() {
    Gson gson = new Gson();
    TypeAdapter<SimpleEnum> adapter = EnumTypeAdapter.FACTORY.create(gson, TypeToken.get(SimpleEnum.class));

    assertThat(adapter).isNotNull();
  }

  @Test
  public void testMultipleEnumConstants() throws IOException {
    Gson gson = new Gson();
    TypeAdapter<SimpleEnum> adapter = gson.getAdapter(TypeToken.get(SimpleEnum.class));

    JsonReader readerA = new JsonReader(new StringReader("\"VALUE_A\""));
    assertThat(adapter.read(readerA)).isEqualTo(SimpleEnum.VALUE_A);

    JsonReader readerB = new JsonReader(new StringReader("\"VALUE_B\""));
    assertThat(adapter.read(readerB)).isEqualTo(SimpleEnum.VALUE_B);

    JsonReader readerC = new JsonReader(new StringReader("\"VALUE_C\""));
    assertThat(adapter.read(readerC)).isEqualTo(SimpleEnum.VALUE_C);
  }
}
