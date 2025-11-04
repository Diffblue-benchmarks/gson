package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;

public class GenericClassesDiffblueTest {
  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link GenericClasses.DummyClass#DummyClass(String)}
   *   <li>{@link GenericClasses.DummyClass#toString()}
   * </ul>
   */
  @Test
  public void testDummyClassGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals("foo", (new GenericClasses.DummyClass("foo")).toString());
  }

  /** Method under test: {@link GenericClasses.DummyClass.Adapter#read(JsonReader)} */
  @Test
  public void testDummyClass_AdapterRead() throws IOException {
    // Arrange
    GenericClasses.DummyClass.Adapter adapter = new GenericClasses.DummyClass.Adapter();

    // Act and Assert
    assertEquals("read-42", adapter.read(new JsonReader(new StringReader("42"))).toString());
  }

  /**
   * Method under test: {@link GenericClasses.DummyClass.Adapter#write(JsonWriter,
   * GenericClasses.DummyClass)}
   */
  @Test
  public void testDummyClass_AdapterWrite() throws IOException {
    // Arrange
    GenericClasses.DummyClass.Adapter adapter = new GenericClasses.DummyClass.Adapter();
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> adapter.write(out, new GenericClasses.DummyClass("foo")));
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link GenericClasses.GenericClass}
   *   <li>{@link GenericClasses.GenericClass#toString()}
   * </ul>
   */
  @Test
  public void testGenericClassGettersAndSetters() {
    // Arrange and Act
    GenericClasses.GenericClass<Object> actualGenericClass = new GenericClasses.GenericClass<>();

    // Assert
    assertEquals("{t=null}", actualGenericClass.toString());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link GenericClasses.GenericUsingGenericClass}
   *   <li>{@link GenericClasses.GenericUsingGenericClass#toString()}
   * </ul>
   */
  @Test
  public void testGenericUsingGenericClassGettersAndSetters() {
    // Arrange and Act
    GenericClasses.GenericUsingGenericClass<Object> actualGenericUsingGenericClass =
        new GenericClasses.GenericUsingGenericClass<>();

    // Assert
    assertEquals("{g=null}", actualGenericUsingGenericClass.toString());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link GenericClasses.UsingGenericClass}
   *   <li>{@link GenericClasses.UsingGenericClass#toString()}
   * </ul>
   */
  @Test
  public void testUsingGenericClassGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals("{g=null}", (new GenericClasses.UsingGenericClass()).toString());
  }
}
