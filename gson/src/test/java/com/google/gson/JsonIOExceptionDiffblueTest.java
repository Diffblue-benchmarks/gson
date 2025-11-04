package com.google.gson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

public class JsonIOExceptionDiffblueTest {
  /** Method under test: {@link JsonIOException#JsonIOException(String)} */
  @Test
  public void testNewJsonIOException() {
    // Arrange and Act
    JsonIOException actualJsonIOException = new JsonIOException("Msg");

    // Assert
    assertEquals("Msg", actualJsonIOException.getMessage());
    assertNull(actualJsonIOException.getCause());
    assertEquals(0, actualJsonIOException.getSuppressed().length);
  }

  /** Method under test: {@link JsonIOException#JsonIOException(String, Throwable)} */
  @Test
  public void testNewJsonIOException2() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    JsonIOException actualJsonIOException = new JsonIOException("Msg", cause);

    // Assert
    assertEquals("Msg", actualJsonIOException.getMessage());
    assertEquals(0, actualJsonIOException.getSuppressed().length);
    assertSame(cause, actualJsonIOException.getCause());
  }

  /** Method under test: {@link JsonIOException#JsonIOException(Throwable)} */
  @Test
  public void testNewJsonIOException3() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    JsonIOException actualJsonIOException = new JsonIOException(cause);

    // Assert
    assertEquals("java.lang.Throwable", actualJsonIOException.getMessage());
    assertEquals(0, actualJsonIOException.getSuppressed().length);
    assertSame(cause, actualJsonIOException.getCause());
  }
}
