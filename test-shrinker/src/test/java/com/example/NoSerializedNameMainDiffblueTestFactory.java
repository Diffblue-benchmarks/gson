package com.example;

import com.diffblue.cover.annotations.InterestingTestFactory;
import com.example.NoSerializedNameMain.TestClassHasArgsConstructor;

/** Factory class for creating NoSerializedNameMain test instances for Diffblue Cover testing. */
public class NoSerializedNameMainDiffblueTestFactory {

  /**
   * Creates a TestClassHasArgsConstructor instance to avoid RuntimeException when Gson attempts to
   * deserialize. This factory method provides a properly initialized instance that can be used in
   * tests where Gson's unsafe allocator fails to create an instance of a class without a no-args
   * constructor.
   *
   * @return a TestClassHasArgsConstructor instance with initialized field for testing
   */
  @InterestingTestFactory
  public static TestClassHasArgsConstructor createTestClassHasArgsConstructor() {
    return new TestClassHasArgsConstructor("value");
  }
}
