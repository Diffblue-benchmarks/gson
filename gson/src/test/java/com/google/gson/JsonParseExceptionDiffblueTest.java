package com.google.gson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JsonParseExceptionDiffblueTest {
  /**
   * Test {@link JsonParseException#JsonParseException(String)}.
   *
   * <ul>
   *   <li>When {@code Msg}.
   *   <li>Then return Cause is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JsonParseException#JsonParseException(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JsonParseException.<init>(String)",
    "void JsonParseException.<init>(String, Throwable)",
    "void JsonParseException.<init>(Throwable)"
  })
  public void testNewJsonParseException_whenMsg_thenReturnCauseIsNull() {
    // Arrange and Act
    JsonParseException actualJsonParseException = new JsonParseException("Msg");

    // Assert
    assertEquals("Msg", actualJsonParseException.getMessage());
    assertNull(actualJsonParseException.getCause());
    assertEquals(0, actualJsonParseException.getSuppressed().length);
  }

  /**
   * Test {@link JsonParseException#JsonParseException(String, Throwable)}.
   *
   * <ul>
   *   <li>When {@code Msg}.
   *   <li>Then return Message is {@code Msg}.
   * </ul>
   *
   * <p>Method under test: {@link JsonParseException#JsonParseException(String, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JsonParseException.<init>(String)",
    "void JsonParseException.<init>(String, Throwable)",
    "void JsonParseException.<init>(Throwable)"
  })
  public void testNewJsonParseException_whenMsg_thenReturnMessageIsMsg() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    JsonParseException actualJsonParseException = new JsonParseException("Msg", cause);

    // Assert
    assertEquals("Msg", actualJsonParseException.getMessage());
    assertEquals(0, actualJsonParseException.getSuppressed().length);
    assertSame(cause, actualJsonParseException.getCause());
  }

  /**
   * Test {@link JsonParseException#JsonParseException(Throwable)}.
   *
   * <ul>
   *   <li>When {@link Throwable#Throwable()}.
   *   <li>Then return Message is {@code Throwable}.
   * </ul>
   *
   * <p>Method under test: {@link JsonParseException#JsonParseException(Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JsonParseException.<init>(String)",
    "void JsonParseException.<init>(String, Throwable)",
    "void JsonParseException.<init>(Throwable)"
  })
  public void testNewJsonParseException_whenThrowable_thenReturnMessageIsJavaLangThrowable() {
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
