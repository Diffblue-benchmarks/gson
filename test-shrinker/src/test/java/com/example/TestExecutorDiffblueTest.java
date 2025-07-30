package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class TestExecutorDiffblueTest {
  /**
   * Test {@link TestExecutor#run(BiConsumer, String, Supplier)}.
   *
   * <ul>
   *   <li>Given {@code Get}.
   *   <li>When {@link BiConsumer} {@link BiConsumer#accept(Object, Object)} does nothing.
   *   <li>Then calls {@link BiConsumer#accept(Object, Object)}.
   * </ul>
   *
   * <p>Method under test: {@link TestExecutor#run(BiConsumer, String, Supplier)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TestExecutor.run(BiConsumer, String, Supplier)"})
  public void testRun_givenGet_whenBiConsumerAcceptDoesNothing_thenCallsAccept() {
    // Arrange
    BiConsumer<String, String> outputConsumer = mock(BiConsumer.class);
    doNothing().when(outputConsumer).accept(Mockito.<String>any(), Mockito.<String>any());
    Supplier<String> resultSupplier = mock(Supplier.class);
    when(resultSupplier.get()).thenReturn("Get");

    // Act
    TestExecutor.run(outputConsumer, "Name", resultSupplier);

    // Assert
    verify(outputConsumer).accept(eq("Name"), eq("Get"));
    verify(resultSupplier).get();
  }

  /**
   * Test {@link TestExecutor#run(BiConsumer, String, Supplier)}.
   *
   * <ul>
   *   <li>Given {@link RuntimeException#RuntimeException(String)} with {@code foo}.
   *   <li>When {@link BiConsumer}.
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link TestExecutor#run(BiConsumer, String, Supplier)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TestExecutor.run(BiConsumer, String, Supplier)"})
  public void testRun_givenRuntimeExceptionWithFoo_whenBiConsumer_thenThrowRuntimeException() {
    // Arrange
    BiConsumer<String, String> outputConsumer = mock(BiConsumer.class);
    Supplier<String> resultSupplier = mock(Supplier.class);
    when(resultSupplier.get()).thenThrow(new RuntimeException("foo"));

    // Act and Assert
    assertThrows(
        RuntimeException.class, () -> TestExecutor.run(outputConsumer, "Name", resultSupplier));
    verify(resultSupplier).get();
  }

  /**
   * Test {@link TestExecutor#run(BiConsumer, String, Supplier)}.
   *
   * <ul>
   *   <li>When {@link BiConsumer} {@link BiConsumer#accept(Object, Object)} throw {@link
   *       RuntimeException#RuntimeException(String)} with {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link TestExecutor#run(BiConsumer, String, Supplier)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TestExecutor.run(BiConsumer, String, Supplier)"})
  public void testRun_whenBiConsumerAcceptThrowRuntimeExceptionWithFoo() {
    // Arrange
    BiConsumer<String, String> outputConsumer = mock(BiConsumer.class);
    doThrow(new RuntimeException("foo"))
        .when(outputConsumer)
        .accept(Mockito.<String>any(), Mockito.<String>any());
    Supplier<String> resultSupplier = mock(Supplier.class);
    when(resultSupplier.get()).thenReturn("Get");

    // Act and Assert
    assertThrows(
        RuntimeException.class, () -> TestExecutor.run(outputConsumer, "Name", resultSupplier));
    verify(outputConsumer).accept(eq("Name"), eq("Get"));
    verify(resultSupplier).get();
  }

  /**
   * Test {@link TestExecutor#same(Object)}.
   *
   * <p>Method under test: {@link TestExecutor#same(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TestExecutor.same(Object)"})
  public void testSame() {
    // Arrange, Act and Assert
    assertEquals("42", TestExecutor.same("42"));
  }
}
