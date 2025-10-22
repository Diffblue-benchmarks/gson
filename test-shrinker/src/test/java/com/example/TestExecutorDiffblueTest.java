package com.example;

import static org.junit.Assert.assertThrows;
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
   *   <li>Given a string.
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
  public void testRun_givenAString_whenBiConsumerAcceptDoesNothing_thenCallsAccept() {
    // Arrange
    BiConsumer<String, String> outputConsumer = mock(BiConsumer.class);
    doNothing().when(outputConsumer).accept(Mockito.<String>any(), Mockito.<String>any());

    Supplier<String> resultSupplier = mock(Supplier.class);
    when(resultSupplier.get())
        .thenReturn(
            "Since the Supplier.get() method does not take any parameters, it's not possible to"
                + " provide a string as input for this method.");

    // Act
    TestExecutor.run(
        outputConsumer, "\"TestExecutor_RunMethod_ExceptionHandling\"", resultSupplier);

    // Assert
    verify(outputConsumer)
        .accept(
            "\"TestExecutor_RunMethod_ExceptionHandling\"",
            "Since the Supplier.get() method does not take any parameters, it's not possible to"
                + " provide a string as input for this method.");
    verify(resultSupplier).get();
  }

  /**
   * Test {@link TestExecutor#run(BiConsumer, String, Supplier)}.
   *
   * <ul>
   *   <li>Given {@link RuntimeException#RuntimeException()}.
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
  public void testRun_givenRuntimeException_whenBiConsumer_thenThrowRuntimeException() {
    // Arrange
    BiConsumer<String, String> outputConsumer = mock(BiConsumer.class);

    Supplier<String> resultSupplier = mock(Supplier.class);
    when(resultSupplier.get()).thenThrow(new RuntimeException());

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () ->
            TestExecutor.run(
                outputConsumer, "\"TestExecutor_RunMethod_ExceptionHandling\"", resultSupplier));
    verify(resultSupplier).get();
  }

  /**
   * Test {@link TestExecutor#run(BiConsumer, String, Supplier)}.
   *
   * <ul>
   *   <li>When {@link BiConsumer} {@link BiConsumer#accept(Object, Object)} throw {@link
   *       RuntimeException#RuntimeException()}.
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link TestExecutor#run(BiConsumer, String, Supplier)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TestExecutor.run(BiConsumer, String, Supplier)"})
  public void testRun_whenBiConsumerAcceptThrowRuntimeException_thenThrowRuntimeException() {
    // Arrange
    BiConsumer<String, String> outputConsumer = mock(BiConsumer.class);
    doThrow(new RuntimeException())
        .when(outputConsumer)
        .accept(Mockito.<String>any(), Mockito.<String>any());

    Supplier<String> resultSupplier = mock(Supplier.class);
    when(resultSupplier.get())
        .thenReturn(
            "Since the Supplier.get() method does not take any parameters, it's not possible to"
                + " provide a string as input for this method.");

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () ->
            TestExecutor.run(
                outputConsumer, "\"TestExecutor_RunMethod_ExceptionHandling\"", resultSupplier));
    verify(outputConsumer)
        .accept(
            "\"TestExecutor_RunMethod_ExceptionHandling\"",
            "Since the Supplier.get() method does not take any parameters, it's not possible to"
                + " provide a string as input for this method.");
    verify(resultSupplier).get();
  }
}
