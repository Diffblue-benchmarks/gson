package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Type;
import org.junit.Test;
import org.mockito.internal.util.reflection.GenericMetadataSupport;

public class ClassWithJsonAdapterAnnotationDiffblueTest {
  /** Method under test: {@link ClassWithJsonAdapterAnnotation.Adapter#read(JsonReader)} */
  @Test
  public void testAdapterRead() throws IOException {
    // Arrange
    ClassWithJsonAdapterAnnotation.Adapter adapter = new ClassWithJsonAdapterAnnotation.Adapter();

    // Act and Assert
    assertEquals("adapter-42", adapter.read(new JsonReader(new StringReader("42"))).toString());
  }

  /**
   * Method under test: {@link ClassWithJsonAdapterAnnotation.Deserializer#deserialize(JsonElement,
   * Type, JsonDeserializationContext)}
   */
  @Test
  public void testDeserializerDeserialize() throws JsonParseException {
    // Arrange
    ClassWithJsonAdapterAnnotation.Deserializer deserializer =
        new ClassWithJsonAdapterAnnotation.Deserializer();

    JsonArray json = new JsonArray(3);
    json.add(Integer.valueOf(1));

    // Act and Assert
    assertEquals(
        "deserializer-1",
        deserializer
            .deserialize(
                json,
                new GenericMetadataSupport.TypeVarBoundedType(null),
                mock(JsonDeserializationContext.class))
            .toString());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link ClassWithJsonAdapterAnnotation.DummyClass#DummyClass(String)}
   *   <li>{@link ClassWithJsonAdapterAnnotation.DummyClass#toString()}
   * </ul>
   */
  @Test
  public void testDummyClassGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals("foo", (new ClassWithJsonAdapterAnnotation.DummyClass("foo")).toString());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link ClassWithJsonAdapterAnnotation#ClassWithJsonAdapterAnnotation()}
   *   <li>{@link ClassWithJsonAdapterAnnotation#toString()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals(
        "ClassWithJsonAdapterAnnotation[f1=null, f2=null, f3=null, f4=null]",
        (new ClassWithJsonAdapterAnnotation()).toString());
  }

  /**
   * Method under test: {@link ClassWithJsonAdapterAnnotation#ClassWithJsonAdapterAnnotation(int,
   * int, int, int)}
   */
  @Test
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
   * Method under test: {@link
   * ClassWithJsonAdapterAnnotation.Serializer#serialize(ClassWithJsonAdapterAnnotation.DummyClass,
   * Type, JsonSerializationContext)}
   */
  @Test
  public void testSerializerSerialize() {
    // Arrange
    ClassWithJsonAdapterAnnotation.Serializer serializer =
        new ClassWithJsonAdapterAnnotation.Serializer();
    ClassWithJsonAdapterAnnotation.DummyClass src =
        new ClassWithJsonAdapterAnnotation.DummyClass("foo");

    // Act
    JsonElement actualSerializeResult =
        serializer.serialize(
            src,
            new GenericMetadataSupport.TypeVarBoundedType(null),
            mock(JsonSerializationContext.class));

    // Assert
    assertTrue(actualSerializeResult instanceof JsonPrimitive);
    Number asNumber = actualSerializeResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("serializer-foo", actualSerializeResult.getAsString());
    assertEquals("serializer-foo", asNumber.toString());
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
