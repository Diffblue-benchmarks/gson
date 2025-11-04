package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class NoSerializedNameMainDiffblueTest {
  /** Method under test: {@link NoSerializedNameMain#runTestNoArgsConstructor()} */
  @Test
  public void testRunTestNoArgsConstructor() {
    // Arrange, Act and Assert
    assertEquals("value", NoSerializedNameMain.runTestNoArgsConstructor());
  }

  /** Method under test: {@link NoSerializedNameMain#runTestNoJdkUnsafe()} */
  @Test
  public void testRunTestNoJdkUnsafe() {
    // Arrange, Act and Assert
    assertEquals("value", NoSerializedNameMain.runTestNoJdkUnsafe());
  }

  /**
   * Method under test: {@link
   * NoSerializedNameMain.TestClassHasArgsConstructor#TestClassHasArgsConstructor(String)}
   */
  @Test
  public void testTestClassHasArgsConstructorNewTestClassHasArgsConstructor() {
    // Arrange, Act and Assert
    assertEquals("foo", (new NoSerializedNameMain.TestClassHasArgsConstructor("foo")).s);
  }

  /**
   * Method under test: default or parameterless constructor of {@link
   * NoSerializedNameMain.TestClassNoArgsConstructor}
   */
  @Test
  public void testTestClassNoArgsConstructorNewTestClassNoArgsConstructor() {
    // Arrange, Act and Assert
    assertNull((new NoSerializedNameMain.TestClassNoArgsConstructor()).s);
  }

  /**
   * Method under test: default or parameterless constructor of {@link
   * NoSerializedNameMain.TestClassNotAbstract}
   */
  @Test
  public void testTestClassNotAbstractNewTestClassNotAbstract() {
    // Arrange, Act and Assert
    assertNull((new NoSerializedNameMain.TestClassNotAbstract()).s);
  }
}
