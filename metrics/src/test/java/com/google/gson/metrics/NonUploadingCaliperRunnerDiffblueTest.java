package com.google.gson.metrics;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

class NonUploadingCaliperRunnerDiffblueTest {
  /** Method under test: {@link NonUploadingCaliperRunner#run(Class, String[])} */
  @Test
  void testRun() {
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

  /** Method under test: {@link NonUploadingCaliperRunner#run(Class, String[])} */
  @Test
  void testRun2() {
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

  /** Method under test: {@link NonUploadingCaliperRunner#run(Class, String[])} */
  @Test
  void testRun3() {
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
}
