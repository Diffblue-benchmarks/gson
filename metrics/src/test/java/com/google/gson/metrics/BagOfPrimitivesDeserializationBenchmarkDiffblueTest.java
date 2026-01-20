package com.google.gson.metrics;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.internal.Excluder;
import java.util.List;
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
   * Test {@link BagOfPrimitivesDeserializationBenchmark#setUp()}.
   *
   * <p>Method under test: {@link BagOfPrimitivesDeserializationBenchmark#setUp()}
   */
  @Test
  @DisplayName("Test setUp()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void BagOfPrimitivesDeserializationBenchmark.setUp()"})
  void testSetUp() throws Exception {
    // Arrange
    BagOfPrimitivesDeserializationBenchmark bagOfPrimitivesDeserializationBenchmark =
        new BagOfPrimitivesDeserializationBenchmark();

    // Act
    bagOfPrimitivesDeserializationBenchmark.setUp();

    // Assert
    assertEquals(
        "{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"}",
        bagOfPrimitivesDeserializationBenchmark.getJson());
    Gson gson = bagOfPrimitivesDeserializationBenchmark.getGson();
    Excluder excluderResult = gson.excluder();
    assertEquals(-1.0d, excluderResult.getVersion());
    assertEquals(136, excluderResult.getModifiers());
    assertFalse(gson.serializeNulls());
    assertFalse(excluderResult.isRequireExpose());
    assertTrue(gson.htmlSafe());
    assertTrue(excluderResult.isSerializeInnerClasses());
    List<ExclusionStrategy> deserializationStrategies =
        excluderResult.getDeserializationStrategies();
    assertTrue(deserializationStrategies.isEmpty());
    assertSame(deserializationStrategies, excluderResult.getSerializationStrategies());
  }

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

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link BagOfPrimitivesDeserializationBenchmark}
   *   <li>{@link BagOfPrimitivesDeserializationBenchmark#getGson()}
   *   <li>{@link BagOfPrimitivesDeserializationBenchmark#getJson()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BagOfPrimitivesDeserializationBenchmark.<init>()",
    "Gson BagOfPrimitivesDeserializationBenchmark.getGson()",
    "java.lang.String BagOfPrimitivesDeserializationBenchmark.getJson()"
  })
  void testGettersAndSetters() {
    // Arrange and Act
    BagOfPrimitivesDeserializationBenchmark actualBagOfPrimitivesDeserializationBenchmark =
        new BagOfPrimitivesDeserializationBenchmark();
    Gson actualGson = actualBagOfPrimitivesDeserializationBenchmark.getGson();

    // Assert
    assertNull(actualGson);
    assertNull(actualBagOfPrimitivesDeserializationBenchmark.getJson());
  }
}
