package com.example;

import com.diffblue.cover.annotations.InterestingTestFactory;
import java.util.function.BiConsumer;

/** Factory class for creating Main test instances for Diffblue Cover testing. */
public class MainDiffblueTestFactory {

  /**
   * Creates a BiConsumer that can handle test output. This factory method provides a simple
   * BiConsumer implementation that accepts test name and content pairs without throwing exceptions.
   *
   * @return a BiConsumer instance suitable for testing Main.runTests
   */
  @InterestingTestFactory
  public static BiConsumer<String, String> createBiConsumer() {
    return new BiConsumer<String, String>() {
      @Override
      public void accept(String name, String content) {
        // Simply consume the output - this allows Cover to test the method
        // even if internal tests fail
      }
    };
  }
}
