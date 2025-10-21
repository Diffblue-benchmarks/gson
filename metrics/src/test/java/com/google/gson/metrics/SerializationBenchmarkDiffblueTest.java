package com.google.gson.metrics;

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

class SerializationBenchmarkDiffblueTest {
  /**
   * Test {@link SerializationBenchmark#setUp()}.
   *
   * <p>Method under test: {@link SerializationBenchmark#setUp()}
   */
  @Test
  @DisplayName("Test setUp()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void SerializationBenchmark.setUp()"})
  void testSetUp() throws Exception {
    // Arrange
    SerializationBenchmark serializationBenchmark = new SerializationBenchmark();

    // Act
    serializationBenchmark.setUp();

    // Assert
    BagOfPrimitives bag = serializationBenchmark.getBag();
    assertEquals("foo", bag.stringValue);
    assertEquals(
        "{\"longValue\":10,\"intValue\":1,\"booleanValue\":false,\"stringValue\":\"foo\"}",
        bag.getExpectedJson());
    assertEquals(1, bag.getIntValue());
    assertEquals(10L, bag.longValue);
    Gson gson = serializationBenchmark.getGson();
    assertFalse(gson.serializeNulls());
    assertFalse(bag.booleanValue);
    assertTrue(gson.htmlSafe());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link SerializationBenchmark}
   *   <li>{@link SerializationBenchmark#getBag()}
   *   <li>{@link SerializationBenchmark#getGson()}
   *   <li>{@link SerializationBenchmark#isPretty()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SerializationBenchmark.<init>()",
    "BagOfPrimitives SerializationBenchmark.getBag()",
    "Gson SerializationBenchmark.getGson()",
    "boolean SerializationBenchmark.isPretty()"
  })
  void testGettersAndSetters() {
    // Arrange and Act
    SerializationBenchmark actualSerializationBenchmark = new SerializationBenchmark();
    BagOfPrimitives actualBag = actualSerializationBenchmark.getBag();
    Gson actualGson = actualSerializationBenchmark.getGson();

    // Assert
    assertNull(actualGson);
    assertNull(actualBag);
    assertFalse(actualSerializationBenchmark.isPretty());
  }
}
