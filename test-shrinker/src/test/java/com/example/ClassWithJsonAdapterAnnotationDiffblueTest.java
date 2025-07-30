package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.example.ClassWithJsonAdapterAnnotation.Adapter;
import com.example.ClassWithJsonAdapterAnnotation.Deserializer;
import com.example.ClassWithJsonAdapterAnnotation.DummyClass;
import com.example.ClassWithJsonAdapterAnnotation.Serializer;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.Strictness;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Type;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.internal.util.reflection.GenericMetadataSupport;
import org.mockito.internal.util.reflection.GenericMetadataSupport.TypeVarBoundedType;

public class ClassWithJsonAdapterAnnotationDiffblueTest {
  /**
   * Test Adapter {@link Adapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then return toString is {@code adapter-42}.
   * </ul>
   *
   * <p>Method under test: {@link Adapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DummyClass Adapter.read(JsonReader)"})
  public void testAdapterRead_whenStringReaderWith42_thenReturnToStringIsAdapter42()
      throws IOException {
    // Arrange
    Adapter adapter = new Adapter();

    // Act and Assert
    assertEquals("adapter-42", adapter.read(new JsonReader(new StringReader("42"))).toString());
  }

  /**
   * Test Adapter {@link Adapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then return toString is {@code adapter-42}.
   * </ul>
   *
   * <p>Method under test: {@link Adapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DummyClass Adapter.read(JsonReader)"})
  public void testAdapterRead_whenStringReaderWith42_thenReturnToStringIsAdapter422()
      throws IOException {
    // Arrange
    Adapter adapter = new Adapter();

    JsonReader in = new JsonReader(new StringReader("42"));
    in.setStrictness(Strictness.LENIENT);

    // Act and Assert
    assertEquals("adapter-42", adapter.read(in).toString());
  }

  /**
   * Test Deserializer {@link Deserializer#deserialize(JsonElement, Type,
   * JsonDeserializationContext)}.
   *
   * <ul>
   *   <li>Given valueOf one.
   *   <li>Then return toString is {@code deserializer-1}.
   * </ul>
   *
   * <p>Method under test: {@link Deserializer#deserialize(JsonElement, Type,
   * JsonDeserializationContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DummyClass Deserializer.deserialize(JsonElement, Type, JsonDeserializationContext)"
  })
  public void testDeserializerDeserialize_givenValueOfOne_thenReturnToStringIsDeserializer1()
      throws JsonParseException {
    // Arrange
    Deserializer deserializer = new Deserializer();

    JsonArray json = new JsonArray(3);
    json.add(Integer.valueOf(1));

    // Act and Assert
    assertEquals(
        "deserializer-1",
        deserializer
            .deserialize(json, new TypeVarBoundedType(null), mock(JsonDeserializationContext.class))
            .toString());
  }

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

  /**
   * Test Serializer {@link Serializer#serialize(DummyClass, Type, JsonSerializationContext)} with
   * {@code DummyClass}, {@code Type}, {@code JsonSerializationContext}.
   *
   * <p>Method under test: {@link Serializer#serialize(DummyClass, Type, JsonSerializationContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "JsonElement Serializer.serialize(DummyClass, Type, JsonSerializationContext)"
  })
  public void testSerializerSerializeWithDummyClassTypeJsonSerializationContext() {
    // Arrange
    Serializer serializer = new Serializer();
    DummyClass src = new DummyClass("foo");

    // Act
    JsonElement actualSerializeResult =
        serializer.serialize(
            src, new TypeVarBoundedType(null), mock(JsonSerializationContext.class));

    // Assert
    assertTrue(actualSerializeResult instanceof JsonPrimitive);
    assertTrue(actualSerializeResult.getAsNumber() instanceof LazilyParsedNumber);
    assertEquals("serializer-foo", actualSerializeResult.getAsString());
    assertEquals('s', actualSerializeResult.getAsCharacter());
    assertFalse(actualSerializeResult.getAsBoolean());
    assertFalse(actualSerializeResult.isJsonArray());
    assertFalse(actualSerializeResult.isJsonNull());
    assertFalse(actualSerializeResult.isJsonObject());
    assertFalse(((JsonPrimitive) actualSerializeResult).isBoolean());
    assertFalse(((JsonPrimitive) actualSerializeResult).isNumber());
    assertTrue(actualSerializeResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) actualSerializeResult).isString());
    assertSame(actualSerializeResult, actualSerializeResult.getAsJsonPrimitive());
  }
}
