package com.google.gson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JsonIOExceptionDiffblueTest {
  /**
   * Test {@link JsonIOException#JsonIOException(String)}.
   *
   * <ul>
   *   <li>When {@code Msg}.
   *   <li>Then return Cause is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JsonIOException#JsonIOException(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JsonIOException.<init>(String)",
    "void JsonIOException.<init>(String, Throwable)",
    "void JsonIOException.<init>(Throwable)"
  })
  public void testNewJsonIOException_whenMsg_thenReturnCauseIsNull() {
    // Arrange and Act
    JsonIOException actualJsonIOException = new JsonIOException("Msg");

    // Assert
    assertEquals("Msg", actualJsonIOException.getMessage());
    assertNull(actualJsonIOException.getCause());
    assertEquals(0, actualJsonIOException.getSuppressed().length);
  }

  /**
   * Test {@link JsonIOException#JsonIOException(String, Throwable)}.
   *
   * <ul>
   *   <li>When {@code Msg}.
   *   <li>Then return Message is {@code Msg}.
   * </ul>
   *
   * <p>Method under test: {@link JsonIOException#JsonIOException(String, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JsonIOException.<init>(String)",
    "void JsonIOException.<init>(String, Throwable)",
    "void JsonIOException.<init>(Throwable)"
  })
  public void testNewJsonIOException_whenMsg_thenReturnMessageIsMsg() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    JsonIOException actualJsonIOException = new JsonIOException("Msg", cause);

    // Assert
    assertEquals("Msg", actualJsonIOException.getMessage());
    assertEquals(0, actualJsonIOException.getSuppressed().length);
    assertSame(cause, actualJsonIOException.getCause());
  }

  /**
   * Test {@link JsonIOException#JsonIOException(Throwable)}.
   *
   * <ul>
   *   <li>When {@link Throwable#Throwable()}.
   *   <li>Then return Message is {@code Throwable}.
   * </ul>
   *
   * <p>Method under test: {@link JsonIOException#JsonIOException(Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JsonIOException.<init>(String)",
    "void JsonIOException.<init>(String, Throwable)",
    "void JsonIOException.<init>(Throwable)"
  })
  public void testNewJsonIOException_whenThrowable_thenReturnMessageIsJavaLangThrowable() {
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
