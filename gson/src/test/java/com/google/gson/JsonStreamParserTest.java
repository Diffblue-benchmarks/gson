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

import java.io.StringReader;
import java.util.NoSuchElementException;
import org.junit.Test;

public class JsonStreamParserTest {

  @Test
  public void testConstructorWithString() {
    JsonStreamParser parser = new JsonStreamParser("{'key':'value'}");
    assertThat(parser.hasNext()).isTrue();
    JsonElement element = parser.next();
    assertThat(element.isJsonObject()).isTrue();
  }

  @Test
  public void testConstructorWithReader() {
    StringReader reader = new StringReader("{'key':'value'}");
    JsonStreamParser parser = new JsonStreamParser(reader);
    assertThat(parser.hasNext()).isTrue();
    JsonElement element = parser.next();
    assertThat(element.isJsonObject()).isTrue();
  }

  @Test
  public void testNextReturnsJsonElement() {
    JsonStreamParser parser = new JsonStreamParser("'test'");
    JsonElement element = parser.next();
    assertThat(element.isJsonPrimitive()).isTrue();
    assertThat(element.getAsString()).isEqualTo("test");
  }

  @Test
  public void testNextMultipleElements() {
    JsonStreamParser parser = new JsonStreamParser("'first' 'second' 'third'");
    assertThat(parser.next().getAsString()).isEqualTo("first");
    assertThat(parser.next().getAsString()).isEqualTo("second");
    assertThat(parser.next().getAsString()).isEqualTo("third");
  }

  @Test(expected = NoSuchElementException.class)
  public void testNextThrowsNoSuchElementException() {
    JsonStreamParser parser = new JsonStreamParser("'test'");
    parser.next();
    parser.next();
  }

  @Test
  public void testHasNextReturnsTrueWhenElementAvailable() {
    JsonStreamParser parser = new JsonStreamParser("'test'");
    assertThat(parser.hasNext()).isTrue();
  }

  @Test
  public void testHasNextReturnsFalseWhenNoElementAvailable() {
    JsonStreamParser parser = new JsonStreamParser("'test'");
    parser.next();
    assertThat(parser.hasNext()).isFalse();
  }

  @Test
  public void testHasNextMultipleTimes() {
    JsonStreamParser parser = new JsonStreamParser("'test'");
    assertThat(parser.hasNext()).isTrue();
    assertThat(parser.hasNext()).isTrue();
    parser.next();
    assertThat(parser.hasNext()).isFalse();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testRemoveThrowsUnsupportedException() {
    JsonStreamParser parser = new JsonStreamParser("'test'");
    parser.remove();
  }

  @Test
  public void testIterateOverMultipleJsonObjects() {
    JsonStreamParser parser = new JsonStreamParser("{'a':1} {'b':2} {'c':3}");
    int count = 0;
    while (parser.hasNext()) {
      JsonElement element = parser.next();
      assertThat(element.isJsonObject()).isTrue();
      count++;
    }
    assertThat(count).isEqualTo(3);
  }

  @Test
  public void testIterateOverMixedJsonElements() {
    JsonStreamParser parser = new JsonStreamParser("'string' 123 true null {'key':'value'} [1,2,3]");
    assertThat(parser.next().getAsString()).isEqualTo("string");
    assertThat(parser.next().getAsInt()).isEqualTo(123);
    assertThat(parser.next().getAsBoolean()).isTrue();
    assertThat(parser.next().isJsonNull()).isTrue();
    assertThat(parser.next().isJsonObject()).isTrue();
    assertThat(parser.next().isJsonArray()).isTrue();
    assertThat(parser.hasNext()).isFalse();
  }

  @Test(expected = JsonIOException.class)
  public void testEmptyString() {
    JsonStreamParser parser = new JsonStreamParser("");
    parser.hasNext();
  }

  @Test(expected = JsonIOException.class)
  public void testWhitespaceOnly() {
    JsonStreamParser parser = new JsonStreamParser("   \n\t  ");
    parser.hasNext();
  }

  @Test
  public void testLenientParsing() {
    JsonStreamParser parser = new JsonStreamParser("{'key': 'value'}");
    assertThat(parser.hasNext()).isTrue();
    JsonElement element = parser.next();
    assertThat(element.isJsonObject()).isTrue();
  }
}
