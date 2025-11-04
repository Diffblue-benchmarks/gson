package com.google.gson.stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

public class MalformedJsonExceptionDiffblueTest {
  /** Method under test: {@link MalformedJsonException#MalformedJsonException(String)} */
  @Test
  public void testNewMalformedJsonException() {
    // Arrange and Act
    MalformedJsonException actualMalformedJsonException = new MalformedJsonException("Msg");

    // Assert
    assertEquals("Msg", actualMalformedJsonException.getMessage());
    assertNull(actualMalformedJsonException.getCause());
    assertEquals(0, actualMalformedJsonException.getSuppressed().length);
  }

  /** Method under test: {@link MalformedJsonException#MalformedJsonException(String, Throwable)} */
  @Test
  public void testNewMalformedJsonException2() {
    // Arrange
    Throwable throwable = new Throwable();

    // Act
    MalformedJsonException actualMalformedJsonException =
        new MalformedJsonException("Msg", throwable);

    // Assert
    assertEquals("Msg", actualMalformedJsonException.getMessage());
    assertEquals(0, actualMalformedJsonException.getSuppressed().length);
    assertSame(throwable, actualMalformedJsonException.getCause());
  }

  /** Method under test: {@link MalformedJsonException#MalformedJsonException(Throwable)} */
  @Test
  public void testNewMalformedJsonException3() {
    // Arrange
    Throwable throwable = new Throwable();

    // Act
    MalformedJsonException actualMalformedJsonException = new MalformedJsonException(throwable);

    // Assert
    assertEquals("java.lang.Throwable", actualMalformedJsonException.getMessage());
    assertEquals(0, actualMalformedJsonException.getSuppressed().length);
    assertSame(throwable, actualMalformedJsonException.getCause());
  }
}
