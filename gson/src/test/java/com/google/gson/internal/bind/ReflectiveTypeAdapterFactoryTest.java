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

import static com.google.common.truth.Truth.assertThat;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class ReflectiveTypeAdapterFactoryTest {

  @Test
  public void testFieldsDataConstructorWithEmptyCollections() throws Exception {
    Class<?> fieldsDataClass = getFieldsDataClass();
    Class<?> boundFieldClass = getBoundFieldClass();

    Constructor<?> constructor =
        fieldsDataClass.getDeclaredConstructor(Map.class, List.class);
    constructor.setAccessible(true);

    Map<String, Object> deserializedFields = Collections.emptyMap();
    List<Object> serializedFields = Collections.emptyList();

    Object fieldsData = constructor.newInstance(deserializedFields, serializedFields);

    Field deserializedFieldsField = fieldsDataClass.getDeclaredField("deserializedFields");
    deserializedFieldsField.setAccessible(true);
    Field serializedFieldsField = fieldsDataClass.getDeclaredField("serializedFields");
    serializedFieldsField.setAccessible(true);

    assertThat(deserializedFieldsField.get(fieldsData)).isSameInstanceAs(deserializedFields);
    assertThat(serializedFieldsField.get(fieldsData)).isSameInstanceAs(serializedFields);
  }

  @Test
  public void testFieldsDataConstructorWithNonEmptyCollections() throws Exception {
    Class<?> fieldsDataClass = getFieldsDataClass();
    Class<?> boundFieldClass = getBoundFieldClass();

    Constructor<?> constructor =
        fieldsDataClass.getDeclaredConstructor(Map.class, List.class);
    constructor.setAccessible(true);

    Map<String, Object> deserializedFields = new HashMap<>();
    List<Object> serializedFields = new ArrayList<>();

    Object fieldsData = constructor.newInstance(deserializedFields, serializedFields);

    Field deserializedFieldsField = fieldsDataClass.getDeclaredField("deserializedFields");
    deserializedFieldsField.setAccessible(true);
    Field serializedFieldsField = fieldsDataClass.getDeclaredField("serializedFields");
    serializedFieldsField.setAccessible(true);

    assertThat(deserializedFieldsField.get(fieldsData)).isSameInstanceAs(deserializedFields);
    assertThat(serializedFieldsField.get(fieldsData)).isSameInstanceAs(serializedFields);
  }

  @Test
  public void testFieldsDataConstructorWithNullValues() throws Exception {
    Class<?> fieldsDataClass = getFieldsDataClass();
    Class<?> boundFieldClass = getBoundFieldClass();

    Constructor<?> constructor =
        fieldsDataClass.getDeclaredConstructor(Map.class, List.class);
    constructor.setAccessible(true);

    Object fieldsData = constructor.newInstance(null, null);

    Field deserializedFieldsField = fieldsDataClass.getDeclaredField("deserializedFields");
    deserializedFieldsField.setAccessible(true);
    Field serializedFieldsField = fieldsDataClass.getDeclaredField("serializedFields");
    serializedFieldsField.setAccessible(true);

    assertThat(deserializedFieldsField.get(fieldsData)).isNull();
    assertThat(serializedFieldsField.get(fieldsData)).isNull();
  }

  private Class<?> getFieldsDataClass() throws ClassNotFoundException {
    Class<?>[] declaredClasses = ReflectiveTypeAdapterFactory.class.getDeclaredClasses();
    for (Class<?> declaredClass : declaredClasses) {
      if (declaredClass.getSimpleName().equals("FieldsData")) {
        return declaredClass;
      }
    }
    throw new ClassNotFoundException("FieldsData class not found");
  }

  private Class<?> getBoundFieldClass() throws ClassNotFoundException {
    Class<?>[] declaredClasses = ReflectiveTypeAdapterFactory.class.getDeclaredClasses();
    for (Class<?> declaredClass : declaredClasses) {
      if (declaredClass.getSimpleName().equals("BoundField")) {
        return declaredClass;
      }
    }
    throw new ClassNotFoundException("BoundField class not found");
  }
}
