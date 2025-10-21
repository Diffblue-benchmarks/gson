package com.google.gson.metrics;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.Gson;
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
   * Test {@link CollectionsDeserializationBenchmark#setUp()}.
   *
   * <p>Method under test: {@link CollectionsDeserializationBenchmark#setUp()}
   */
  @Test
  @DisplayName("Test setUp()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CollectionsDeserializationBenchmark.setUp()"})
  void testSetUp() throws Exception {
    // Arrange
    CollectionsDeserializationBenchmark collectionsDeserializationBenchmark =
        new CollectionsDeserializationBenchmark();

    // Act
    collectionsDeserializationBenchmark.setUp();

    // Assert
    assertEquals(
        "[{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1"
            + ",\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue"
            + "\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,"
            + "\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\""
            + ":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},"
            + "{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1"
            + ",\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue"
            + "\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,"
            + "\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\""
            + ":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},"
            + "{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1"
            + ",\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue"
            + "\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,"
            + "\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\""
            + ":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},"
            + "{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1"
            + ",\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue"
            + "\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,"
            + "\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\""
            + ":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},"
            + "{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1"
            + ",\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue"
            + "\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,"
            + "\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\""
            + ":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},"
            + "{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1"
            + ",\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue"
            + "\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,"
            + "\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\""
            + ":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},"
            + "{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1"
            + ",\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue"
            + "\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,"
            + "\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\""
            + ":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},"
            + "{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1"
            + ",\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue"
            + "\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,"
            + "\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\""
            + ":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},"
            + "{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1"
            + ",\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue"
            + "\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,"
            + "\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\""
            + ":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},"
            + "{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1"
            + ",\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue"
            + "\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,"
            + "\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\""
            + ":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},"
            + "{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1"
            + ",\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue"
            + "\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,"
            + "\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\""
            + ":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},"
            + "{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1"
            + ",\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue"
            + "\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,"
            + "\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\""
            + ":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},"
            + "{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1"
            + ",\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue"
            + "\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,"
            + "\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\""
            + ":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},"
            + "{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1"
            + ",\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue"
            + "\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,"
            + "\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\""
            + ":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},"
            + "{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"},{\"longValue\":10,\"intValue\":1"
            + ",\"booleanValue\":false,\"stringValue\":\"foo\"}]",
        collectionsDeserializationBenchmark.getJson());
    Gson gson = collectionsDeserializationBenchmark.getGson();
    assertFalse(gson.serializeNulls());
    assertTrue(gson.htmlSafe());
  }

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

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link CollectionsDeserializationBenchmark}
   *   <li>{@link CollectionsDeserializationBenchmark#getGson()}
   *   <li>{@link CollectionsDeserializationBenchmark#getJson()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CollectionsDeserializationBenchmark.<init>()",
    "Gson CollectionsDeserializationBenchmark.getGson()",
    "java.lang.String CollectionsDeserializationBenchmark.getJson()"
  })
  void testGettersAndSetters() {
    // Arrange and Act
    CollectionsDeserializationBenchmark actualCollectionsDeserializationBenchmark =
        new CollectionsDeserializationBenchmark();
    Gson actualGson = actualCollectionsDeserializationBenchmark.getGson();

    // Assert
    assertNull(actualGson);
    assertNull(actualCollectionsDeserializationBenchmark.getJson());
  }
}
