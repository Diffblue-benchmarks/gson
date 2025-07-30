package com.google.gson.metrics;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

class NonUploadingCaliperRunnerDiffblueTest {
  /**
   * Test {@link NonUploadingCaliperRunner#run(Class, String[])}.
   *
   * <ul>
   *   <li>When array of {@link String} with {@code Args}.
   *   <li>Then calls {@link Runtime#exit(int)}.
   * </ul>
   *
   * <p>Method under test: {@link NonUploadingCaliperRunner#run(Class, String[])}
   */
  @Test
  @DisplayName("Test run(Class, String[]); when array of String with 'Args'; then calls exit(int)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NonUploadingCaliperRunner.run(Class, String[])"})
  void testRun_whenArrayOfStringWithArgs_thenCallsExit() {
    try (MockedStatic<Runtime> mockRuntime = mockStatic(Runtime.class)) {

      // Arrange
      Runtime runtime = mock(Runtime.class);
      doNothing().when(runtime).exit(anyInt());
      mockRuntime.when(Runtime::getRuntime).thenReturn(runtime);
      Class<Object> c = Object.class;

      // Act
      NonUploadingCaliperRunner.run(c, new String[] {"Args"});

      // Assert
      verify(runtime).exit(eq(1));
      mockRuntime.verify(Runtime::getRuntime);
    }
  }

  /**
   * Test {@link NonUploadingCaliperRunner#run(Class, String[])}.
   *
   * <ul>
   *   <li>When array of {@link String} with {@code -h}.
   *   <li>Then calls {@link Runtime#exit(int)}.
   * </ul>
   *
   * <p>Method under test: {@link NonUploadingCaliperRunner#run(Class, String[])}
   */
  @Test
  @DisplayName("Test run(Class, String[]); when array of String with '-h'; then calls exit(int)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NonUploadingCaliperRunner.run(Class, String[])"})
  void testRun_whenArrayOfStringWithH_thenCallsExit() {
    try (MockedStatic<Runtime> mockRuntime = mockStatic(Runtime.class)) {

      // Arrange
      Runtime runtime = mock(Runtime.class);
      doNothing().when(runtime).exit(anyInt());
      mockRuntime.when(Runtime::getRuntime).thenReturn(runtime);
      Class<Object> c = Object.class;

      // Act
      NonUploadingCaliperRunner.run(c, new String[] {"-h"});

      // Assert
      verify(runtime).exit(eq(0));
      mockRuntime.verify(Runtime::getRuntime);
    }
  }

  /**
   * Test {@link NonUploadingCaliperRunner#run(Class, String[])}.
   *
   * <ul>
   *   <li>When array of {@link String} with {@code --help}.
   *   <li>Then calls {@link Runtime#exit(int)}.
   * </ul>
   *
   * <p>Method under test: {@link NonUploadingCaliperRunner#run(Class, String[])}
   */
  @Test
  @DisplayName(
      "Test run(Class, String[]); when array of String with '--help'; then calls exit(int)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NonUploadingCaliperRunner.run(Class, String[])"})
  void testRun_whenArrayOfStringWithHelp_thenCallsExit() {
    try (MockedStatic<Runtime> mockRuntime = mockStatic(Runtime.class)) {

      // Arrange
      Runtime runtime = mock(Runtime.class);
      doNothing().when(runtime).exit(anyInt());
      mockRuntime.when(Runtime::getRuntime).thenReturn(runtime);
      Class<Object> c = Object.class;

      // Act
      NonUploadingCaliperRunner.run(c, new String[] {"--help"});

      // Assert
      verify(runtime).exit(eq(0));
      mockRuntime.verify(Runtime::getRuntime);
    }
  }

  /**
   * Test {@link NonUploadingCaliperRunner#run(Class, String[])}.
   *
   * <ul>
   *   <li>When array of {@link String} with {@code negative duration: %s}.
   *   <li>Then calls {@link Runtime#exit(int)}.
   * </ul>
   *
   * <p>Method under test: {@link NonUploadingCaliperRunner#run(Class, String[])}
   */
  @Test
  @DisplayName(
      "Test run(Class, String[]); when array of String with 'negative duration: %s'; then calls exit(int)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NonUploadingCaliperRunner.run(Class, String[])"})
  void testRun_whenArrayOfStringWithNegativeDurationS_thenCallsExit() {
    try (MockedStatic<Runtime> mockRuntime = mockStatic(Runtime.class)) {

      // Arrange
      Runtime runtime = mock(Runtime.class);
      doNothing().when(runtime).exit(anyInt());
      mockRuntime.when(Runtime::getRuntime).thenReturn(runtime);
      Class<Object> c = Object.class;

      // Act
      NonUploadingCaliperRunner.run(c, new String[] {"negative duration: %s"});

      // Assert
      verify(runtime).exit(eq(1));
      mockRuntime.verify(Runtime::getRuntime);
    }
  }
}
