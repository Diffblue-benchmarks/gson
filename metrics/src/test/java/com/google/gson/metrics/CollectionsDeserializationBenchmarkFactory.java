package com.google.gson.metrics;

import com.diffblue.cover.annotations.InterestingTestFactory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/** Factory class for creating test instances of CollectionsDeserializationBenchmark. */
public class CollectionsDeserializationBenchmarkFactory {

  private static final TypeToken<List<BagOfPrimitives>> LIST_TYPE_TOKEN =
      new TypeToken<List<BagOfPrimitives>>() {};
  private static final Type LIST_TYPE = LIST_TYPE_TOKEN.getType();

  /**
   * Creates a properly initialized instance of CollectionsDeserializationBenchmark with non-null
   * gson and json fields to avoid NullPointerException.
   *
   * @return A CollectionsDeserializationBenchmark instance ready for testing
   */
  @InterestingTestFactory
  public static CollectionsDeserializationBenchmark createCollectionsDeserializationBenchmark() {
    CollectionsDeserializationBenchmark benchmark = new CollectionsDeserializationBenchmark();

    // Initialize the benchmark similarly to how setUp() does it
    Gson gson = new Gson();
    List<BagOfPrimitives> bags = new ArrayList<>();
    for (int i = 0; i < 100; ++i) {
      bags.add(new BagOfPrimitives(10L, 1, false, "foo"));
    }
    String json = gson.toJson(bags, LIST_TYPE);

    // Use reflection to set the private fields since setUp() is not being called
    try {
      java.lang.reflect.Field gsonField =
          CollectionsDeserializationBenchmark.class.getDeclaredField("gson");
      gsonField.setAccessible(true);
      gsonField.set(benchmark, gson);

      java.lang.reflect.Field jsonField =
          CollectionsDeserializationBenchmark.class.getDeclaredField("json");
      jsonField.setAccessible(true);
      jsonField.set(benchmark, json);
    } catch (Exception e) {
      throw new RuntimeException("Failed to initialize CollectionsDeserializationBenchmark", e);
    }

    return benchmark;
  }
}
