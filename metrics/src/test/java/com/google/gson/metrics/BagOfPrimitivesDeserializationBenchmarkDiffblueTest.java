package com.google.gson.metrics;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BagOfPrimitivesDeserializationBenchmarkDiffblueTest {
  @InjectMocks
  private BagOfPrimitivesDeserializationBenchmark bagOfPrimitivesDeserializationBenchmark;

  /**
   * Test {@link BagOfPrimitivesDeserializationBenchmark#timeBagOfPrimitivesDefault(int)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link
   * BagOfPrimitivesDeserializationBenchmark#timeBagOfPrimitivesDefault(int)}
   */
  @Test
  @DisplayName("Test timeBagOfPrimitivesDefault(int); when zero; then does not throw")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BagOfPrimitivesDeserializationBenchmark.timeBagOfPrimitivesDefault(int)"
  })
  void testTimeBagOfPrimitivesDefault_whenZero_thenDoesNotThrow() {
    // Arrange, Act and Assert
    assertDoesNotThrow(() -> bagOfPrimitivesDeserializationBenchmark.timeBagOfPrimitivesDefault(0));
  }
}
