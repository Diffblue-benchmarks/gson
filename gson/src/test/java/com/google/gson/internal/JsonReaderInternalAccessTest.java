/*
 * Copyright (C) 2026 Google Inc.
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

package com.google.gson.internal;

import static com.google.common.truth.Truth.assertThat;

import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.StringReader;
import org.junit.Test;

public class JsonReaderInternalAccessTest {

  @Test
  public void testPromoteNameToValue() throws IOException {
    JsonReader reader = new JsonReader(new StringReader("{\"key\": \"value\"}"));
    reader.beginObject();

    // At this point, reader is positioned at the property name "key"
    // Call promoteNameToValue to convert it from a name token to a value token
    JsonReaderInternalAccess.INSTANCE.promoteNameToValue(reader);

    // After promotion, we should be able to read it as a string value
    String result = reader.nextString();

    assertThat(result).isEqualTo("key");
  }
}
