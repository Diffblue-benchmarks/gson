package com.example;

import com.diffblue.cover.annotations.InterestingTestFactory;

public class ClassWithNamedFieldsTestFactory {

  /**
   * Creates a ClassWithNamedFields instance for testing.
   * This factory method is needed because the class only has a constructor with arguments,
   * preventing Gson from instantiating it during deserialization tests.
   *
   * @return a ClassWithNamedFields instance with a valid myField value
   */
  @InterestingTestFactory
  public static ClassWithNamedFields createClassWithNamedFields() {
    return new ClassWithNamedFields(42);
  }

  /**
   * Creates a ClassWithNamedFields instance with a zero value.
   *
   * @return a ClassWithNamedFields instance with myField set to 0
   */
  @InterestingTestFactory
  public static ClassWithNamedFields createClassWithNamedFieldsZero() {
    return new ClassWithNamedFields(0);
  }

  /**
   * Creates a ClassWithNamedFields instance with a negative value.
   *
   * @return a ClassWithNamedFields instance with myField set to a negative value
   */
  @InterestingTestFactory
  public static ClassWithNamedFields createClassWithNamedFieldsNegative() {
    return new ClassWithNamedFields(-1);
  }
}
