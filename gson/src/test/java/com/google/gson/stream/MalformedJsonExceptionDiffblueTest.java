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
   * <ul>
   *   <li>When {@code Msg}.
   *   <li>Then return Cause is {@code null}.
   * </ul>
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
  public void testNewMalformedJsonException_whenMsg_thenReturnCauseIsNull() {
    // Arrange and Act
    MalformedJsonException actualMalformedJsonException = new MalformedJsonException("Msg");

    // Assert
    assertEquals("Msg", actualMalformedJsonException.getMessage());
    assertNull(actualMalformedJsonException.getCause());
    assertEquals(0, actualMalformedJsonException.getSuppressed().length);
  }

  /**
   * Test {@link MalformedJsonException#MalformedJsonException(String, Throwable)}.
   *
   * <ul>
   *   <li>When {@code Msg}.
   *   <li>Then return Message is {@code Msg}.
   * </ul>
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
  public void testNewMalformedJsonException_whenMsg_thenReturnMessageIsMsg() {
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
