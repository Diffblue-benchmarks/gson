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

package com.google.gson.internal.bind;

import com.diffblue.cover.annotations.InterestingTestFactory;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import java.util.concurrent.TimeUnit;

/**
 * Factory class for creating EnumTypeAdapter instances for Diffblue Cover testing.
 *
 * <p>Since EnumTypeAdapter has a private constructor, this factory provides methods to create
 * instances using the public FACTORY field available in the EnumTypeAdapter class.
 */
public class EnumTypeAdapterDiffblueTestFactory {

  /** Simple test enum for basic enum adapter testing. */
  public enum SimpleEnum {
    VALUE_A,
    VALUE_B,
    VALUE_C
  }

  /** Enum with SerializedName annotations for testing custom serialization names. */
  public enum AnnotatedEnum {
    @SerializedName("first")
    FIRST_VALUE,

    @SerializedName(
        value = "second",
        alternate = {"2nd", "sec"})
    SECOND_VALUE,

    @SerializedName("third")
    THIRD_VALUE
  }

  /** Enum with custom toString() implementation. */
  public enum CustomToStringEnum {
    ALPHA,
    BETA,
    GAMMA;

    @Override
    public String toString() {
      return name().toLowerCase();
    }
  }

  /**
   * Creates an EnumTypeAdapter instance for a simple enum.
   *
   * @return an EnumTypeAdapter instance for SimpleEnum
   */
  @InterestingTestFactory
  public static EnumTypeAdapter<SimpleEnum> createSimpleEnumAdapter() {
    Gson gson = new Gson();
    TypeToken<SimpleEnum> typeToken = TypeToken.get(SimpleEnum.class);
    @SuppressWarnings("unchecked")
    EnumTypeAdapter<SimpleEnum> adapter =
        (EnumTypeAdapter<SimpleEnum>) EnumTypeAdapter.FACTORY.create(gson, typeToken);
    return adapter;
  }

  /**
   * Creates an EnumTypeAdapter instance for an enum with SerializedName annotations.
   *
   * @return an EnumTypeAdapter instance for AnnotatedEnum
   */
  @InterestingTestFactory
  public static EnumTypeAdapter<AnnotatedEnum> createAnnotatedEnumAdapter() {
    Gson gson = new Gson();
    TypeToken<AnnotatedEnum> typeToken = TypeToken.get(AnnotatedEnum.class);
    @SuppressWarnings("unchecked")
    EnumTypeAdapter<AnnotatedEnum> adapter =
        (EnumTypeAdapter<AnnotatedEnum>) EnumTypeAdapter.FACTORY.create(gson, typeToken);
    return adapter;
  }

  /**
   * Creates an EnumTypeAdapter instance for an enum with custom toString().
   *
   * @return an EnumTypeAdapter instance for CustomToStringEnum
   */
  @InterestingTestFactory
  public static EnumTypeAdapter<CustomToStringEnum> createCustomToStringEnumAdapter() {
    Gson gson = new Gson();
    TypeToken<CustomToStringEnum> typeToken = TypeToken.get(CustomToStringEnum.class);
    @SuppressWarnings("unchecked")
    EnumTypeAdapter<CustomToStringEnum> adapter =
        (EnumTypeAdapter<CustomToStringEnum>) EnumTypeAdapter.FACTORY.create(gson, typeToken);
    return adapter;
  }

  /**
   * Creates an EnumTypeAdapter instance for a JDK enum (TimeUnit).
   *
   * @return an EnumTypeAdapter instance for TimeUnit
   */
  @InterestingTestFactory
  public static EnumTypeAdapter<TimeUnit> createTimeUnitAdapter() {
    Gson gson = new Gson();
    TypeToken<TimeUnit> typeToken = TypeToken.get(TimeUnit.class);
    @SuppressWarnings("unchecked")
    EnumTypeAdapter<TimeUnit> adapter =
        (EnumTypeAdapter<TimeUnit>) EnumTypeAdapter.FACTORY.create(gson, typeToken);
    return adapter;
  }
}
