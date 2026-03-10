/*
 * Copyright (C) 2022 Google Inc.
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
import static org.junit.Assert.assertThrows;

import com.google.gson.JsonIOException;
import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.junit.Test;

public class StreamsTest {
  @Test
  public void testWriterForAppendable() throws IOException {
    StringBuilder stringBuilder = new StringBuilder();
    Writer writer = Streams.writerForAppendable(stringBuilder);

    writer.append('a');
    writer.append('\u1234');
    writer.append("test");
    writer.append(null); // test custom null handling mandated by `append`
    writer.append("abcdef", 2, 4);
    writer.append(null, 1, 3); // test custom null handling mandated by `append`
    writer.append(',');

    writer.write('a');
    writer.write('\u1234');
    // Should only consider the 16 low-order bits
    writer.write(0x4321_1234);
    writer.append(',');

    writer.write("chars".toCharArray());
    assertThrows(NullPointerException.class, () -> writer.write((char[]) null));

    writer.write("chars".toCharArray(), 1, 2);
    assertThrows(NullPointerException.class, () -> writer.write((char[]) null, 1, 2));
    writer.append(',');

    writer.write("string");
    assertThrows(NullPointerException.class, () -> writer.write((String) null));

    writer.write("string", 1, 2);
    assertThrows(NullPointerException.class, () -> writer.write((String) null, 1, 2));

    String actualOutput = stringBuilder.toString();
    assertThat(actualOutput).isEqualTo("a\u1234testnullcdul,a\u1234\u1234,charsha,stringtr");

    writer.flush();
    writer.close();

    // flush() and close() calls should have had no effect
    assertThat(stringBuilder.toString()).isEqualTo(actualOutput);
  }

  @Test
  public void testPrivateConstructorThrowsException() throws Exception {
    Constructor<Streams> constructor = Streams.class.getDeclaredConstructor();
    constructor.setAccessible(true);

    InvocationTargetException e =
        assertThrows(InvocationTargetException.class, () -> constructor.newInstance());
    assertThat(e).hasCauseThat().isInstanceOf(UnsupportedOperationException.class);
  }

  @Test
  public void testParseIOException() {
    Reader failingReader =
        new Reader() {
          private boolean firstRead = true;

          @Override
          public int read(char[] cbuf, int off, int len) throws IOException {
            if (firstRead) {
              // Return valid JSON start so peek() succeeds
              firstRead = false;
              cbuf[off] = '[';
              return 1;
            }
            // Subsequent reads fail with IOException
            throw new IOException("Simulated IO failure");
          }

          @Override
          public void close() {}
        };

    JsonReader jsonReader = new JsonReader(failingReader);
    JsonIOException e = assertThrows(JsonIOException.class, () -> Streams.parse(jsonReader));
    assertThat(e).hasCauseThat().isInstanceOf(IOException.class);
    assertThat(e).hasCauseThat().hasMessageThat().isEqualTo("Simulated IO failure");
  }
}
