package com.example;

import static org.junit.Assert.assertEquals;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.example.GenericClasses.DummyClass;
import com.example.GenericClasses.GenericClass;
import com.example.GenericClasses.GenericUsingGenericClass;
import com.example.GenericClasses.UsingGenericClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class GenericClassesDiffblueTest {
  /**
   * Test DummyClass getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DummyClass#DummyClass(String)}
   *   <li>{@link DummyClass#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DummyClass.<init>(String)", "String DummyClass.toString()"})
  public void testDummyClassGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals("foo", new DummyClass("foo").toString());
  }

  /**
   * Test GenericClass getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link GenericClass}
   *   <li>{@link GenericClass#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void GenericClass.<init>()", "String GenericClass.toString()"})
  public void testGenericClassGettersAndSetters() {
    // Arrange and Act
    GenericClass<Object> actualGenericClass = new GenericClass<>();

    // Assert
    assertEquals("{t=null}", actualGenericClass.toString());
  }

  /**
   * Test GenericUsingGenericClass getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link GenericUsingGenericClass}
   *   <li>{@link GenericUsingGenericClass#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void GenericUsingGenericClass.<init>()",
    "String GenericUsingGenericClass.toString()"
  })
  public void testGenericUsingGenericClassGettersAndSetters() {
    // Arrange and Act
    GenericUsingGenericClass<Object> actualGenericUsingGenericClass =
        new GenericUsingGenericClass<>();

    // Assert
    assertEquals("{g=null}", actualGenericUsingGenericClass.toString());
  }

  /**
   * Test UsingGenericClass getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link UsingGenericClass}
   *   <li>{@link UsingGenericClass#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void UsingGenericClass.<init>()", "String UsingGenericClass.toString()"})
  public void testUsingGenericClassGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals("{g=null}", new UsingGenericClass().toString());
  }
}
