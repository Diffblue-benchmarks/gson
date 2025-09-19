package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.example.ClassWithJsonAdapterAnnotation.DummyClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ClassWithJsonAdapterAnnotationDiffblueTest {
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
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ClassWithJsonAdapterAnnotation#ClassWithJsonAdapterAnnotation()}
   *   <li>{@link ClassWithJsonAdapterAnnotation#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ClassWithJsonAdapterAnnotation.<init>()",
    "String ClassWithJsonAdapterAnnotation.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals(
        "ClassWithJsonAdapterAnnotation[f1=null, f2=null, f3=null, f4=null]",
        new ClassWithJsonAdapterAnnotation().toString());
  }

  /**
   * Test {@link ClassWithJsonAdapterAnnotation#ClassWithJsonAdapterAnnotation(int, int, int, int)}.
   *
   * <p>Method under test: {@link ClassWithJsonAdapterAnnotation#ClassWithJsonAdapterAnnotation(int,
   * int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassWithJsonAdapterAnnotation.<init>(int, int, int, int)"})
  public void testNewClassWithJsonAdapterAnnotation() {
    // Arrange and Act
    ClassWithJsonAdapterAnnotation actualClassWithJsonAdapterAnnotation =
        new ClassWithJsonAdapterAnnotation(1, 1, 1, 1);

    // Assert
    assertEquals("1", actualClassWithJsonAdapterAnnotation.f1.toString());
    assertEquals("1", actualClassWithJsonAdapterAnnotation.f2.toString());
    assertEquals("1", actualClassWithJsonAdapterAnnotation.f3.toString());
    assertEquals("1", actualClassWithJsonAdapterAnnotation.f4.toString());
    assertNull(actualClassWithJsonAdapterAnnotation.f);
  }
}
