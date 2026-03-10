/*
 * Copyright (C) 2011 Google Inc.
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
package com.google.gson.extras.examples.rawcollections;

import static com.google.common.truth.Truth.assertThat;

import java.lang.reflect.Constructor;
import org.junit.Test;

public class RawCollectionsExampleTest {

  @Test
  public void testEventConstructorAndToString() throws Exception {
    Constructor<RawCollectionsExample.Event> constructor =
        RawCollectionsExample.Event.class.getDeclaredConstructor(String.class, String.class);
    constructor.setAccessible(true);

    RawCollectionsExample.Event event = constructor.newInstance("GREETINGS", "guest");

    assertThat(event).isNotNull();
    assertThat(event.toString()).isEqualTo("(name=GREETINGS, source=guest)");
  }

  @Test
  public void testEventToStringWithNullValues() throws Exception {
    Constructor<RawCollectionsExample.Event> constructor =
        RawCollectionsExample.Event.class.getDeclaredConstructor(String.class, String.class);
    constructor.setAccessible(true);

    RawCollectionsExample.Event event = constructor.newInstance(null, null);

    assertThat(event).isNotNull();
    assertThat(event.toString()).isEqualTo("(name=null, source=null)");
  }
}
