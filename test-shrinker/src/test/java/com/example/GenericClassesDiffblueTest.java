package com.example;

import static org.junit.Assert.assertEquals;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.example.GenericClasses.DummyClass.Adapter;
import com.example.GenericClasses.GenericClass;
import com.example.GenericClasses.GenericUsingGenericClass;
import com.example.GenericClasses.UsingGenericClass;
import com.google.gson.Strictness;
import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.StringReader;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class GenericClassesDiffblueTest {
  /**
   * Test DummyClass_Adapter {@link Adapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then return toString is {@code read-42}.
   * </ul>
   *
   * <p>Method under test: {@link Adapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DummyClass Adapter.read(JsonReader)"})
  public void testDummyClass_AdapterRead_whenStringReaderWith42_thenReturnToStringIsRead42()
      throws IOException {
    // Arrange
    Adapter adapter = new Adapter();

    // Act and Assert
    assertEquals("read-42", adapter.read(new JsonReader(new StringReader("42"))).toString());
  }

  /**
   * Test DummyClass_Adapter {@link Adapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then return toString is {@code read-42}.
   * </ul>
   *
   * <p>Method under test: {@link Adapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DummyClass Adapter.read(JsonReader)"})
  public void testDummyClass_AdapterRead_whenStringReaderWith42_thenReturnToStringIsRead422()
      throws IOException {
    // Arrange
    Adapter adapter = new Adapter();

    JsonReader in = new JsonReader(new StringReader("42"));
    in.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertEquals("read-42", adapter.read(in).toString());
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
  @MethodsUnderTest({"void GenericClass.<init>()", "java.lang.String GenericClass.toString()"})
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
    "java.lang.String GenericUsingGenericClass.toString()"
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
  @MethodsUnderTest({
    "void UsingGenericClass.<init>()",
    "java.lang.String UsingGenericClass.toString()"
  })
  public void testUsingGenericClassGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals("{g=null}", new UsingGenericClass().toString());
  }
}
