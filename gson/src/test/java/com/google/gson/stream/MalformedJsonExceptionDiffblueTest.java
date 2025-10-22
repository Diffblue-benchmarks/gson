package com.google.gson.stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class MalformedJsonExceptionDiffblueTest {
  /**
   * Test {@link MalformedJsonException#MalformedJsonException(String)}.
   *
   * <p>Method under test: {@link MalformedJsonException#MalformedJsonException(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MalformedJsonException.<init>(String)",
    "void MalformedJsonException.<init>(String, Throwable)",
    "void MalformedJsonException.<init>(Throwable)"
  })
  public void testNewMalformedJsonException() {
    // Arrange and Act
    MalformedJsonException actualMalformedJsonException =
        new MalformedJsonException(
            "\"{ 'name': 'John', 'age': 30, 'city': 'New York' 'country': 'USA' }\"");

    // Assert
    assertEquals(
        "\"{ 'name': 'John', 'age': 30, 'city': 'New York' 'country': 'USA' }\"",
        actualMalformedJsonException.getMessage());
    assertNull(actualMalformedJsonException.getCause());
    assertEquals(0, actualMalformedJsonException.getSuppressed().length);
  }

  /**
   * Test {@link MalformedJsonException#MalformedJsonException(String, Throwable)}.
   *
   * <p>Method under test: {@link MalformedJsonException#MalformedJsonException(String, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MalformedJsonException.<init>(String)",
    "void MalformedJsonException.<init>(String, Throwable)",
    "void MalformedJsonException.<init>(Throwable)"
  })
  public void testNewMalformedJsonException2() {
    // Arrange
    Throwable throwable = new Throwable();

    // Act
    MalformedJsonException actualMalformedJsonException =
        new MalformedJsonException(
            "\"Invalid JSON format: Expected '{' at line 1 column 2 path $.\"", throwable);

    // Assert
    assertEquals(
        "\"Invalid JSON format: Expected '{' at line 1 column 2 path $.\"",
        actualMalformedJsonException.getMessage());
    assertEquals(0, actualMalformedJsonException.getSuppressed().length);
    assertSame(throwable, actualMalformedJsonException.getCause());
  }

  /**
   * Test {@link MalformedJsonException#MalformedJsonException(Throwable)}.
   *
   * <ul>
   *   <li>When {@link Throwable#Throwable()}.
   *   <li>Then return Message is {@code Throwable}.
   * </ul>
   *
   * <p>Method under test: {@link MalformedJsonException#MalformedJsonException(Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MalformedJsonException.<init>(String)",
    "void MalformedJsonException.<init>(String, Throwable)",
    "void MalformedJsonException.<init>(Throwable)"
  })
  public void testNewMalformedJsonException_whenThrowable_thenReturnMessageIsJavaLangThrowable() {
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
