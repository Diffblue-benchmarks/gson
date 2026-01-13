package com.google.gson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JsonSyntaxExceptionDiffblueTest {
  /**
   * Test {@link JsonSyntaxException#JsonSyntaxException(String)}.
   *
   * <ul>
   *   <li>When {@code Msg}.
   *   <li>Then return Cause is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JsonSyntaxException#JsonSyntaxException(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JsonSyntaxException.<init>(String)",
    "void JsonSyntaxException.<init>(String, Throwable)",
    "void JsonSyntaxException.<init>(Throwable)"
  })
  public void testNewJsonSyntaxException_whenMsg_thenReturnCauseIsNull() {
    // Arrange and Act
    JsonSyntaxException actualJsonSyntaxException = new JsonSyntaxException("Msg");

    // Assert
    assertEquals("Msg", actualJsonSyntaxException.getMessage());
    assertNull(actualJsonSyntaxException.getCause());
    assertEquals(0, actualJsonSyntaxException.getSuppressed().length);
  }

  /**
   * Test {@link JsonSyntaxException#JsonSyntaxException(String, Throwable)}.
   *
   * <ul>
   *   <li>When {@code Msg}.
   *   <li>Then return Message is {@code Msg}.
   * </ul>
   *
   * <p>Method under test: {@link JsonSyntaxException#JsonSyntaxException(String, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JsonSyntaxException.<init>(String)",
    "void JsonSyntaxException.<init>(String, Throwable)",
    "void JsonSyntaxException.<init>(Throwable)"
  })
  public void testNewJsonSyntaxException_whenMsg_thenReturnMessageIsMsg() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    JsonSyntaxException actualJsonSyntaxException = new JsonSyntaxException("Msg", cause);

    // Assert
    assertEquals("Msg", actualJsonSyntaxException.getMessage());
    assertEquals(0, actualJsonSyntaxException.getSuppressed().length);
    assertSame(cause, actualJsonSyntaxException.getCause());
  }

  /**
   * Test {@link JsonSyntaxException#JsonSyntaxException(Throwable)}.
   *
   * <ul>
   *   <li>When {@link Throwable#Throwable()}.
   *   <li>Then return Message is {@code Throwable}.
   * </ul>
   *
   * <p>Method under test: {@link JsonSyntaxException#JsonSyntaxException(Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JsonSyntaxException.<init>(String)",
    "void JsonSyntaxException.<init>(String, Throwable)",
    "void JsonSyntaxException.<init>(Throwable)"
  })
  public void testNewJsonSyntaxException_whenThrowable_thenReturnMessageIsJavaLangThrowable() {
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
