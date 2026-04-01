/*
 * Copyright (C) 2009 Google Inc.
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

import java.io.StringReader;
import java.util.NoSuchElementException;
import org.junit.Test;

public final class JsonStreamParserTest {

  @Test
  public void testConstructorWithString() {
    JsonStreamParser parser = new JsonStreamParser("{\"a\":1}");
    assertThat(parser.hasNext()).isTrue();
  }

  @Test
  public void testConstructorWithReader() {
    JsonStreamParser parser = new JsonStreamParser(new StringReader("{\"a\":1}"));
    assertThat(parser.hasNext()).isTrue();
  }

  @Test
  public void testNextReturnsJsonElement() {
    JsonStreamParser parser = new JsonStreamParser("{\"key\":\"value\"}");
    JsonElement element = parser.next();
    assertThat(element.isJsonObject()).isTrue();
    assertThat(element.getAsJsonObject().get("key").getAsString()).isEqualTo("value");
  }

  @Test
  public void testNextMultipleElements() {
    JsonStreamParser parser = new JsonStreamParser("[1,2] {\"x\":3} \"hello\"");
    assertThat(parser.next().isJsonArray()).isTrue();
    assertThat(parser.next().isJsonObject()).isTrue();
    assertThat(parser.next().getAsString()).isEqualTo("hello");
    assertThat(parser.hasNext()).isFalse();
  }

  @Test
  public void testNextThrowsNoSuchElementWhenExhausted() {
    JsonStreamParser parser = new JsonStreamParser("42");
    parser.next();
    assertThrows(NoSuchElementException.class, () -> parser.next());
  }

  @Test
  public void testHasNextReturnsFalseAfterConsuming() {
    JsonStreamParser parser = new JsonStreamParser("null");
    parser.next();
    assertThat(parser.hasNext()).isFalse();
  }

  @Test
  public void testHasNextReturnsTrueWithContent() {
    JsonStreamParser parser = new JsonStreamParser("42");
    assertThat(parser.hasNext()).isTrue();
  }

  @Test
  public void testHasNextAfterConsuming() {
    JsonStreamParser parser = new JsonStreamParser("true");
    parser.next();
    assertThat(parser.hasNext()).isFalse();
  }

  @Test
  public void testRemoveThrowsUnsupportedOperationException() {
    JsonStreamParser parser = new JsonStreamParser("{}");
    assertThrows(UnsupportedOperationException.class, () -> parser.remove());
  }

  @Test
  public void testNextWithMalformedJsonThrowsJsonParseException() {
    JsonStreamParser parser = new JsonStreamParser("{invalid}");
    assertThrows(JsonParseException.class, () -> parser.next());
  }
}
