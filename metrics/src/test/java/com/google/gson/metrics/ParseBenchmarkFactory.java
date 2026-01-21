package com.google.gson.metrics;

import com.diffblue.cover.annotations.InterestingTestFactory;
import java.util.ArrayList;

/** Factory class for creating test instances of ParseBenchmark. */
public class ParseBenchmarkFactory {

  /**
   * Creates a properly initialized instance of ParseBenchmark with non-null document and api fields
   * to avoid NullPointerException when setUp() is called.
   *
   * @return A ParseBenchmark instance ready for testing
   */
  @InterestingTestFactory
  public static ParseBenchmark createParseBenchmark() {
    ParseBenchmark benchmark = new ParseBenchmark();

    // Use reflection to set the @Param fields that would normally be set by Caliper
    // The document field needs to be non-null for setUp() to work properly
    try {
      // Get the Document enum class (it's a private inner class)
      Class<?> documentClass = null;
      Class<?> apiClass = null;
      for (Class<?> innerClass : ParseBenchmark.class.getDeclaredClasses()) {
        if (innerClass.getSimpleName().equals("Document")) {
          documentClass = innerClass;
        } else if (innerClass.getSimpleName().equals("Api")) {
          apiClass = innerClass;
        }
      }

      if (documentClass == null || apiClass == null) {
        throw new RuntimeException("Could not find Document or Api enum classes");
      }

      // Get enum values - use TWEETS for document and GSON_BIND for api
      Object[] documentValues = (Object[]) documentClass.getMethod("values").invoke(null);
      Object[] apiValues = (Object[]) apiClass.getMethod("values").invoke(null);

      if (documentValues.length == 0 || apiValues.length == 0) {
        throw new RuntimeException("Document or Api enum has no values");
      }

      // Set document field to TWEETS (first value)
      java.lang.reflect.Field documentField = ParseBenchmark.class.getDeclaredField("document");
      documentField.setAccessible(true);
      documentField.set(benchmark, documentValues[0]); // TWEETS

      // Set api field to JACKSON_STREAM (first value)
      java.lang.reflect.Field apiField = ParseBenchmark.class.getDeclaredField("api");
      apiField.setAccessible(true);
      apiField.set(benchmark, apiValues[0]); // JACKSON_STREAM

      // Initialize parser and text fields to avoid NullPointerException in timeParse
      // Set text to a minimal valid JSON array
      java.lang.reflect.Field textField = ParseBenchmark.class.getDeclaredField("text");
      textField.setAccessible(true);
      textField.set(benchmark, "[]".toCharArray());

      // Set parser to a working parser instance
      java.lang.reflect.Field parserField = ParseBenchmark.class.getDeclaredField("parser");
      parserField.setAccessible(true);
      parserField.set(
          benchmark, apiValues[0].getClass().getMethod("newParser").invoke(apiValues[0]));

    } catch (Exception e) {
      throw new RuntimeException("Failed to initialize ParseBenchmark", e);
    }

    return benchmark;
  }

  /**
   * Creates a properly initialized instance of ParseBenchmark.Feed with non-null items list to
   * avoid NullPointerException in toString().
   *
   * @return A Feed instance ready for testing
   */
  @InterestingTestFactory
  public static ParseBenchmark.Feed createFeed() {
    ParseBenchmark.Feed feed = new ParseBenchmark.Feed();

    // Initialize fields to avoid NullPointerException
    try {
      java.lang.reflect.Field idField = ParseBenchmark.Feed.class.getDeclaredField("id");
      idField.setAccessible(true);
      idField.set(feed, "test-feed-id");

      java.lang.reflect.Field titleField = ParseBenchmark.Feed.class.getDeclaredField("title");
      titleField.setAccessible(true);
      titleField.set(feed, "Test Feed");

      java.lang.reflect.Field descriptionField =
          ParseBenchmark.Feed.class.getDeclaredField("description");
      descriptionField.setAccessible(true);
      descriptionField.set(feed, "Test Description");

      java.lang.reflect.Field alternatesField =
          ParseBenchmark.Feed.class.getDeclaredField("alternates");
      alternatesField.setAccessible(true);
      alternatesField.set(feed, new ArrayList<ParseBenchmark.Link>());

      java.lang.reflect.Field updatedField = ParseBenchmark.Feed.class.getDeclaredField("updated");
      updatedField.setAccessible(true);
      updatedField.set(feed, 0L);

      // Most importantly, set items to an empty list to avoid NullPointerException
      java.lang.reflect.Field itemsField = ParseBenchmark.Feed.class.getDeclaredField("items");
      itemsField.setAccessible(true);
      itemsField.set(feed, new ArrayList<ParseBenchmark.Item>());

    } catch (Exception e) {
      throw new RuntimeException("Failed to initialize Feed", e);
    }

    return feed;
  }
}
