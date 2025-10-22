package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.example.NoSerializedNameMain.TestClassNoArgsConstructor;
import com.example.NoSerializedNameMain.TestClassNotAbstract;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class NoSerializedNameMainDiffblueTest {
  /**
   * Test {@link NoSerializedNameMain#runTestNoArgsConstructor()}.
   *
   * <p>Method under test: {@link NoSerializedNameMain#runTestNoArgsConstructor()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String NoSerializedNameMain.runTestNoArgsConstructor()"})
  public void testRunTestNoArgsConstructor() {
    // Arrange, Act and Assert
    assertEquals("value", NoSerializedNameMain.runTestNoArgsConstructor());
  }

  /**
   * Test {@link NoSerializedNameMain#runTestNoJdkUnsafe()}.
   *
   * <p>Method under test: {@link NoSerializedNameMain#runTestNoJdkUnsafe()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String NoSerializedNameMain.runTestNoJdkUnsafe()"})
  public void testRunTestNoJdkUnsafe() {
    // Arrange, Act and Assert
    assertEquals("value", NoSerializedNameMain.runTestNoJdkUnsafe());
  }

  /**
   * Test TestClassNoArgsConstructor new {@link TestClassNoArgsConstructor} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link
   * TestClassNoArgsConstructor}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TestClassNoArgsConstructor.<init>()"})
  public void testTestClassNoArgsConstructorNewTestClassNoArgsConstructor() {
    // Arrange, Act and Assert
    assertNull(new TestClassNoArgsConstructor().s);
  }

  /**
   * Test TestClassNotAbstract new {@link TestClassNotAbstract} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link TestClassNotAbstract}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TestClassNotAbstract.<init>()"})
  public void testTestClassNotAbstractNewTestClassNotAbstract() {
    // Arrange, Act and Assert
    assertNull(new TestClassNotAbstract().s);
  }
}
