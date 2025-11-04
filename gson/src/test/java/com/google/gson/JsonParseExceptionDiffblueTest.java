package com.google.gson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

public class JsonParseExceptionDiffblueTest {
  /** Method under test: {@link JsonParseException#JsonParseException(String)} */
  @Test
  public void testNewJsonParseException() {
    // Arrange and Act
    JsonParseException actualJsonParseException = new JsonParseException("Msg");

    // Assert
    assertEquals("Msg", actualJsonParseException.getMessage());
    assertNull(actualJsonParseException.getCause());
    assertEquals(0, actualJsonParseException.getSuppressed().length);
  }

  /** Method under test: {@link JsonParseException#JsonParseException(String, Throwable)} */
  @Test
  public void testNewJsonParseException2() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    JsonParseException actualJsonParseException = new JsonParseException("Msg", cause);

    // Assert
    assertEquals("Msg", actualJsonParseException.getMessage());
    assertEquals(0, actualJsonParseException.getSuppressed().length);
    assertSame(cause, actualJsonParseException.getCause());
  }

  /** Method under test: {@link JsonParseException#JsonParseException(Throwable)} */
  @Test
  public void testNewJsonParseException3() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    JsonParseException actualJsonParseException = new JsonParseException(cause);

    // Assert
    assertEquals("java.lang.Throwable", actualJsonParseException.getMessage());
    assertEquals(0, actualJsonParseException.getSuppressed().length);
    assertSame(cause, actualJsonParseException.getCause());
  }
}
