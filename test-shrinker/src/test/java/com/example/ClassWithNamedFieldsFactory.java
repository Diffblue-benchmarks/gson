package com.example;

import com.diffblue.cover.annotations.InterestingTestFactory;

/**
 * Factory class for creating instances of ClassWithNamedFields for test purposes. This factory
 * helps the Cover test generation tool create valid instances.
 */
public class ClassWithNamedFieldsFactory {

  /**
   * Creates an instance of ClassWithNamedFields with a default value. This factory method helps
   * avoid issues with Gson deserialization when JDK Unsafe is unavailable.
   *
   * @return a new instance of ClassWithNamedFields
   */
  @InterestingTestFactory
  public static ClassWithNamedFields createClassWithNamedFields() {
    return new ClassWithNamedFields(0);
  }
}
