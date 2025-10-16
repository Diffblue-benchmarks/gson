package com.example;

import com.diffblue.cover.annotations.InterestingTestFactory;
import com.example.NoSerializedNameMain.TestClassHasArgsConstructor;

public class NoSerializedNameMainTestFactory {

  /**
   * Creates a TestClassHasArgsConstructor instance for testing.
   * This factory method is needed because the class only has a constructor with arguments,
   * preventing Gson from instantiating it during deserialization tests.
   *
   * @return a TestClassHasArgsConstructor instance with a non-null string value
   */
  @InterestingTestFactory
  public static TestClassHasArgsConstructor createTestClassHasArgsConstructor() {
    return new TestClassHasArgsConstructor("test-value");
  }

  /**
   * Creates a TestClassHasArgsConstructor instance with an empty string.
   *
   * @return a TestClassHasArgsConstructor instance with an empty string
   */
  @InterestingTestFactory
  public static TestClassHasArgsConstructor createTestClassHasArgsConstructorEmpty() {
    return new TestClassHasArgsConstructor("");
  }

  /**
   * Creates a TestClassHasArgsConstructor instance with a multi-word string.
   *
   * @return a TestClassHasArgsConstructor instance with a multi-word string
   */
  @InterestingTestFactory
  public static TestClassHasArgsConstructor createTestClassHasArgsConstructorMultiWord() {
    return new TestClassHasArgsConstructor("value with spaces");
  }
}
