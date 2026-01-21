package com.example;

import com.diffblue.cover.annotations.InterestingTestFactory;
import com.example.NoSerializedNameMain.TestClassHasArgsConstructor;

/**
 * Factory class for creating instances of NoSerializedNameMain.TestClassHasArgsConstructor for test
 * purposes. This factory helps the Cover test generation tool create valid instances.
 */
public class NoSerializedNameMainTestClassHasArgsConstructorFactory {

  /**
   * Creates an instance of TestClassHasArgsConstructor with a default value. This factory method
   * helps avoid issues with Gson deserialization when JDK Unsafe is unavailable.
   *
   * @return a new instance of TestClassHasArgsConstructor
   */
  @InterestingTestFactory
  public static TestClassHasArgsConstructor createTestClassHasArgsConstructor() {
    return new TestClassHasArgsConstructor("");
  }
}
