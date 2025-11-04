package com.google.gson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

public class JsonSyntaxExceptionDiffblueTest {
  /** Method under test: {@link JsonSyntaxException#JsonSyntaxException(String)} */
  @Test
  public void testNewJsonSyntaxException() {
    // Arrange and Act
    JsonSyntaxException actualJsonSyntaxException = new JsonSyntaxException("Msg");

    // Assert
    assertEquals("Msg", actualJsonSyntaxException.getMessage());
    assertNull(actualJsonSyntaxException.getCause());
    assertEquals(0, actualJsonSyntaxException.getSuppressed().length);
  }

  /** Method under test: {@link JsonSyntaxException#JsonSyntaxException(String, Throwable)} */
  @Test
  public void testNewJsonSyntaxException2() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    JsonSyntaxException actualJsonSyntaxException = new JsonSyntaxException("Msg", cause);

    // Assert
    assertEquals("Msg", actualJsonSyntaxException.getMessage());
    assertEquals(0, actualJsonSyntaxException.getSuppressed().length);
    assertSame(cause, actualJsonSyntaxException.getCause());
  }

  /** Method under test: {@link JsonSyntaxException#JsonSyntaxException(Throwable)} */
  @Test
  public void testNewJsonSyntaxException3() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    JsonSyntaxException actualJsonSyntaxException = new JsonSyntaxException(cause);

    // Assert
    assertEquals("java.lang.Throwable", actualJsonSyntaxException.getMessage());
    assertEquals(0, actualJsonSyntaxException.getSuppressed().length);
    assertSame(cause, actualJsonSyntaxException.getCause());
  }
}
