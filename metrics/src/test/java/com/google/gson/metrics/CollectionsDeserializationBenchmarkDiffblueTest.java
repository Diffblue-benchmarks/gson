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
class CollectionsDeserializationBenchmarkDiffblueTest {
  @InjectMocks private CollectionsDeserializationBenchmark collectionsDeserializationBenchmark;

  /**
   * Test {@link CollectionsDeserializationBenchmark#timeCollectionsDefault(int)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link CollectionsDeserializationBenchmark#timeCollectionsDefault(int)}
   */
  @Test
  @DisplayName("Test timeCollectionsDefault(int); when zero; then does not throw")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CollectionsDeserializationBenchmark.timeCollectionsDefault(int)"})
  void testTimeCollectionsDefault_whenZero_thenDoesNotThrow() {
    // Arrange, Act and Assert
    assertDoesNotThrow(() -> collectionsDeserializationBenchmark.timeCollectionsDefault(0));
  }
}
