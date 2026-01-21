package com.google.gson.metrics;

import com.diffblue.cover.annotations.InterestingTestFactory;
import com.google.gson.Gson;

/** Factory class for creating test instances of BagOfPrimitivesDeserializationBenchmark. */
public class BagOfPrimitivesDeserializationBenchmarkFactory {

  /**
   * Creates a properly initialized instance of BagOfPrimitivesDeserializationBenchmark with
   * non-null gson and json fields to avoid NullPointerException.
   *
   * @return A BagOfPrimitivesDeserializationBenchmark instance ready for testing
   */
  @InterestingTestFactory
  public static BagOfPrimitivesDeserializationBenchmark
      createBagOfPrimitivesDeserializationBenchmark() {
    BagOfPrimitivesDeserializationBenchmark benchmark =
        new BagOfPrimitivesDeserializationBenchmark();

    // Initialize the benchmark similarly to how setUp() does it
    Gson gson = new Gson();
    BagOfPrimitives bag = new BagOfPrimitives(10L, 1, false, "foo");
    String json = gson.toJson(bag);

    // Use reflection to set the private fields since setUp() is not being called
    try {
      java.lang.reflect.Field gsonField =
          BagOfPrimitivesDeserializationBenchmark.class.getDeclaredField("gson");
      gsonField.setAccessible(true);
      gsonField.set(benchmark, gson);

      java.lang.reflect.Field jsonField =
          BagOfPrimitivesDeserializationBenchmark.class.getDeclaredField("json");
      jsonField.setAccessible(true);
      jsonField.set(benchmark, json);
    } catch (Exception e) {
      throw new RuntimeException("Failed to initialize BagOfPrimitivesDeserializationBenchmark", e);
    }

    return benchmark;
  }
}
