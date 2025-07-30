package com.google.gson.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.Gson;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.graph.GraphAdapterBuilder.Element;
import com.google.gson.graph.GraphAdapterBuilder.Factory;
import com.google.gson.graph.GraphAdapterBuilder.Graph;
import com.google.gson.internal.bind.ArrayTypeAdapter;
import com.google.gson.internal.bind.TreeTypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.util.HashMap;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class GraphAdapterBuilderDiffblueTest {
  /**
   * Test {@link GraphAdapterBuilder#addType(Type, InstanceCreator)} with {@code type}, {@code
   * instanceCreator}.
   *
   * <ul>
   *   <li>Then return {@link GraphAdapterBuilder} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link GraphAdapterBuilder#addType(Type, InstanceCreator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"GraphAdapterBuilder GraphAdapterBuilder.addType(Type, InstanceCreator)"})
  public void testAddTypeWithTypeInstanceCreator_thenReturnGraphAdapterBuilder() {
    // Arrange
    GraphAdapterBuilder graphAdapterBuilder = new GraphAdapterBuilder();
    Class<Object> type = Object.class;

    // Act and Assert
    assertSame(graphAdapterBuilder, graphAdapterBuilder.addType(type, mock(InstanceCreator.class)));
  }

  /**
   * Test {@link GraphAdapterBuilder#addType(Type)} with {@code type}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return {@link GraphAdapterBuilder} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link GraphAdapterBuilder#addType(Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"GraphAdapterBuilder GraphAdapterBuilder.addType(Type)"})
  public void testAddTypeWithType_whenJavaLangObject_thenReturnGraphAdapterBuilder() {
    // Arrange
    GraphAdapterBuilder graphAdapterBuilder = new GraphAdapterBuilder();
    Class<Object> type = Object.class;

    // Act and Assert
    assertSame(graphAdapterBuilder, graphAdapterBuilder.addType(type));
  }

  /**
   * Test Element {@link Element#read(Graph)}.
   *
   * <p>Method under test: {@link Element#read(Graph)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Element.read(Graph)"})
  public void testElementRead() throws JsonParseException {
    // Arrange
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    when(deserializer.deserialize(
            Mockito.<JsonElement>any(),
            Mockito.<Type>any(),
            Mockito.<JsonDeserializationContext>any()))
        .thenReturn("Deserialize");
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Element<Object> element = new Element<>("Value", "42", typeAdapter, new JsonObject());

    // Act
    element.read(mock(Graph.class));

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
  }

  /**
   * Test Element {@link Element#read(Graph)}.
   *
   * <p>Method under test: {@link Element#read(Graph)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Element.read(Graph)"})
  public void testElementRead2() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Element<Object> element = new Element<>("Value", "42", typeAdapter, new JsonNull());

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> element.read(mock(Graph.class)));
  }

  /**
   * Test Element {@link Element#read(Graph)}.
   *
   * <p>Method under test: {@link Element#read(Graph)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Element.read(Graph)"})
  public void testElementRead3() throws JsonParseException {
    // Arrange
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    when(deserializer.deserialize(
            Mockito.<JsonElement>any(),
            Mockito.<Type>any(),
            Mockito.<JsonDeserializationContext>any()))
        .thenReturn("Deserialize");
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Element<Object> element =
        new Element<>("Value", "42", typeAdapter, new JsonPrimitive("in == null"));

    // Act
    element.read(mock(Graph.class));

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
  }

  /**
   * Test Element {@link Element#read(Graph)}.
   *
   * <p>Method under test: {@link Element#read(Graph)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Element.read(Graph)"})
  public void testElementRead4() throws JsonParseException {
    // Arrange
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    when(deserializer.deserialize(
            Mockito.<JsonElement>any(),
            Mockito.<Type>any(),
            Mockito.<JsonDeserializationContext>any()))
        .thenReturn(null);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Element<Object> element = new Element<>("Value", "42", typeAdapter, new JsonObject());

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> element.read(mock(Graph.class)));
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
  }

  /**
   * Test Element {@link Element#read(Graph)}.
   *
   * <p>Method under test: {@link Element#read(Graph)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Element.read(Graph)"})
  public void testElementRead5() throws JsonParseException {
    // Arrange
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    when(deserializer.deserialize(
            Mockito.<JsonElement>any(),
            Mockito.<Type>any(),
            Mockito.<JsonDeserializationContext>any()))
        .thenReturn(null);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Element<Object> element =
        new Element<>("Value", "42", typeAdapter, new JsonPrimitive("in == null"));

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> element.read(mock(Graph.class)));
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
  }

  /**
   * Test Element {@link Element#read(Graph)}.
   *
   * <p>Method under test: {@link Element#read(Graph)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Element.read(Graph)"})
  public void testElementRead6() {
    // Arrange
    Gson context = new Gson();
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> componentTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Class<Object> componentType = Object.class;
    ArrayTypeAdapter<Object> typeAdapter =
        new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType);

    Element<Object> element = new Element<>("Value", "42", typeAdapter, new JsonNull());

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> element.read(mock(Graph.class)));
  }

  /**
   * Test Element {@link Element#read(Graph)}.
   *
   * <p>Method under test: {@link Element#read(Graph)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Element.read(Graph)"})
  public void testElementRead7() throws JsonParseException {
    // Arrange
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    when(deserializer.deserialize(
            Mockito.<JsonElement>any(),
            Mockito.<Type>any(),
            Mockito.<JsonDeserializationContext>any()))
        .thenReturn(null);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    JsonObject element = new JsonObject();
    element.add("non-null value deserialized to null: ", new JsonArray(3));
    element.add("in == null", new JsonArray(3));
    Element<Object> element2 = new Element<>("Value", "42", typeAdapter, element);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> element2.read(mock(Graph.class)));
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
  }

  /**
   * Test Element {@link Element#read(Graph)}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link Element#read(Graph)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Element.read(Graph)"})
  public void testElementRead_givenJsonArrayWithCapacityIsThreeAddFalse()
      throws JsonParseException {
    // Arrange
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    when(deserializer.deserialize(
            Mockito.<JsonElement>any(),
            Mockito.<Type>any(),
            Mockito.<JsonDeserializationContext>any()))
        .thenReturn(null);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    JsonArray element = new JsonArray(3);
    element.add(false);
    element.add(true);
    Element<Object> element2 = new Element<>("Value", "42", typeAdapter, element);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> element2.read(mock(Graph.class)));
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
  }

  /**
   * Test Element {@link Element#read(Graph)}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@link
   *       JsonArray#JsonArray(int)} with capacity is three.
   * </ul>
   *
   * <p>Method under test: {@link Element#read(Graph)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Element.read(Graph)"})
  public void testElementRead_givenJsonArrayWithCapacityIsThreeAddJsonArrayWithCapacityIsThree()
      throws JsonParseException {
    // Arrange
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    when(deserializer.deserialize(
            Mockito.<JsonElement>any(),
            Mockito.<Type>any(),
            Mockito.<JsonDeserializationContext>any()))
        .thenReturn(null);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    JsonArray element = new JsonArray(3);
    element.add(new JsonArray(3));
    Element<Object> element2 = new Element<>("Value", "42", typeAdapter, element);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> element2.read(mock(Graph.class)));
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
  }

  /**
   * Test Element {@link Element#read(Graph)}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add space.
   * </ul>
   *
   * <p>Method under test: {@link Element#read(Graph)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Element.read(Graph)"})
  public void testElementRead_givenJsonArrayWithCapacityIsThreeAddSpace()
      throws JsonParseException {
    // Arrange
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    when(deserializer.deserialize(
            Mockito.<JsonElement>any(),
            Mockito.<Type>any(),
            Mockito.<JsonDeserializationContext>any()))
        .thenReturn(null);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    JsonArray element = new JsonArray(3);
    element.add(' ');
    Element<Object> element2 = new Element<>("Value", "42", typeAdapter, element);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> element2.read(mock(Graph.class)));
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
  }

  /**
   * Test Element {@link Element#read(Graph)}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link Element#read(Graph)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Element.read(Graph)"})
  public void testElementRead_givenJsonArrayWithCapacityIsThreeAddTrue() throws JsonParseException {
    // Arrange
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    when(deserializer.deserialize(
            Mockito.<JsonElement>any(),
            Mockito.<Type>any(),
            Mockito.<JsonDeserializationContext>any()))
        .thenReturn(null);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    JsonArray element = new JsonArray(3);
    element.add(true);
    Element<Object> element2 = new Element<>("Value", "42", typeAdapter, element);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> element2.read(mock(Graph.class)));
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
  }

  /**
   * Test Element {@link Element#read(Graph)}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add valueOf two hundred
   *       fifty-five.
   * </ul>
   *
   * <p>Method under test: {@link Element#read(Graph)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Element.read(Graph)"})
  public void testElementRead_givenJsonArrayWithCapacityIsThreeAddValueOfTwoHundredFiftyFive()
      throws JsonParseException {
    // Arrange
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    when(deserializer.deserialize(
            Mockito.<JsonElement>any(),
            Mockito.<Type>any(),
            Mockito.<JsonDeserializationContext>any()))
        .thenReturn(null);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    JsonArray element = new JsonArray(3);
    element.add(Integer.valueOf(255));
    Element<Object> element2 = new Element<>("Value", "42", typeAdapter, element);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> element2.read(mock(Graph.class)));
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
  }

  /**
   * Test Element {@link Element#read(Graph)}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link Element#read(Graph)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Element.read(Graph)"})
  public void testElementRead_givenJsonArrayWithCapacityIsThree_thenThrowIllegalStateException()
      throws JsonParseException {
    // Arrange
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    when(deserializer.deserialize(
            Mockito.<JsonElement>any(),
            Mockito.<Type>any(),
            Mockito.<JsonDeserializationContext>any()))
        .thenReturn(null);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Element<Object> element = new Element<>("Value", "42", typeAdapter, new JsonArray(3));

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> element.read(mock(Graph.class)));
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
  }

  /**
   * Test Element {@link Element#read(Graph)}.
   *
   * <ul>
   *   <li>Given {@link JsonDeserializer} {@link JsonDeserializer#deserialize(JsonElement, Type,
   *       JsonDeserializationContext)} return {@code Deserialize}.
   * </ul>
   *
   * <p>Method under test: {@link Element#read(Graph)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Element.read(Graph)"})
  public void testElementRead_givenJsonDeserializerDeserializeReturnDeserialize()
      throws JsonParseException {
    // Arrange
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    when(deserializer.deserialize(
            Mockito.<JsonElement>any(),
            Mockito.<Type>any(),
            Mockito.<JsonDeserializationContext>any()))
        .thenReturn("Deserialize");
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Element<Object> element = new Element<>("Value", "42", typeAdapter, new JsonArray(3));

    // Act
    element.read(mock(Graph.class));

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
  }

  /**
   * Test Element {@link Element#read(Graph)}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code in == null} and {@link
   *       JsonArray#JsonArray(int)} with capacity is three.
   * </ul>
   *
   * <p>Method under test: {@link Element#read(Graph)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Element.read(Graph)"})
  public void testElementRead_givenJsonObjectAddInNullAndJsonArrayWithCapacityIsThree()
      throws JsonParseException {
    // Arrange
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    when(deserializer.deserialize(
            Mockito.<JsonElement>any(),
            Mockito.<Type>any(),
            Mockito.<JsonDeserializationContext>any()))
        .thenReturn(null);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    JsonObject element = new JsonObject();
    element.add("in == null", new JsonArray(3));
    Element<Object> element2 = new Element<>("Value", "42", typeAdapter, element);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> element2.read(mock(Graph.class)));
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
  }

  /**
   * Test Element {@link Element#read(Graph)}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.
   *   <li>Then calls {@link JsonDeserializer#deserialize(JsonElement, Type,
   *       JsonDeserializationContext)}.
   * </ul>
   *
   * <p>Method under test: {@link Element#read(Graph)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Element.read(Graph)"})
  public void testElementRead_givenJsonPrimitiveWithBoolIsTrue_thenCallsDeserialize()
      throws JsonParseException {
    // Arrange
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    when(deserializer.deserialize(
            Mockito.<JsonElement>any(),
            Mockito.<Type>any(),
            Mockito.<JsonDeserializationContext>any()))
        .thenReturn("Deserialize");
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Element<Object> element = new Element<>("Value", "42", typeAdapter, new JsonPrimitive(true));

    // Act
    element.read(mock(Graph.class));

    // Assert
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
  }

  /**
   * Test Element {@link Element#read(Graph)}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link Element#read(Graph)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Element.read(Graph)"})
  public void testElementRead_givenJsonPrimitiveWithBoolIsTrue_thenThrowIllegalStateException()
      throws JsonParseException {
    // Arrange
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    when(deserializer.deserialize(
            Mockito.<JsonElement>any(),
            Mockito.<Type>any(),
            Mockito.<JsonDeserializationContext>any()))
        .thenReturn(null);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Element<Object> element = new Element<>("Value", "42", typeAdapter, new JsonPrimitive(true));

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> element.read(mock(Graph.class)));
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
  }

  /**
   * Test Element {@link Element#read(Graph)}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(Character)} with c is {@code ÿ}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link Element#read(Graph)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Element.read(Graph)"})
  public void testElementRead_givenJsonPrimitiveWithCIs_thenThrowIllegalStateException()
      throws JsonParseException {
    // Arrange
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    when(deserializer.deserialize(
            Mockito.<JsonElement>any(),
            Mockito.<Type>any(),
            Mockito.<JsonDeserializationContext>any()))
        .thenReturn(null);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Element<Object> element = new Element<>("Value", "42", typeAdapter, new JsonPrimitive('ÿ'));

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> element.read(mock(Graph.class)));
    verify(deserializer)
        .deserialize(
            isA(JsonElement.class), isA(Type.class), isA(JsonDeserializationContext.class));
  }

  /**
   * Test Element {@link Element#write(JsonWriter)}.
   *
   * <p>Method under test: {@link Element#write(JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Element.write(JsonWriter)"})
  public void testElementWrite() throws IOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonPrimitive('\u0001'));
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Element<Object> element = new Element<>("Value", "42", typeAdapter, new JsonArray(3));

    // Act
    element.write(new JsonWriter(new StringWriter()));

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test Element {@link Element#write(JsonWriter)}.
   *
   * <p>Method under test: {@link Element#write(JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Element.write(JsonWriter)"})
  public void testElementWrite2() throws IOException {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(new JsonArray(3));
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(jsonArray);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Element<Object> element = new Element<>("Value", "42", typeAdapter, new JsonArray(3));

    // Act
    element.write(new JsonWriter(new StringWriter()));

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test Element {@link Element#write(JsonWriter)}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code false}.
   *   <li>Then calls {@link JsonSerializer#serialize(Object, Type, JsonSerializationContext)}.
   * </ul>
   *
   * <p>Method under test: {@link Element#write(JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Element.write(JsonWriter)"})
  public void testElementWrite_givenJsonArrayWithCapacityIsThreeAddFalse_thenCallsSerialize()
      throws IOException {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(false);
    jsonArray.add(true);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(jsonArray);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Element<Object> element = new Element<>("Value", "42", typeAdapter, new JsonArray(3));

    // Act
    element.write(new JsonWriter(new StringWriter()));

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test Element {@link Element#write(JsonWriter)}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add start of heading.
   * </ul>
   *
   * <p>Method under test: {@link Element#write(JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Element.write(JsonWriter)"})
  public void testElementWrite_givenJsonArrayWithCapacityIsThreeAddStartOfHeading()
      throws IOException {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add('\u0001');
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(jsonArray);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Element<Object> element = new Element<>("Value", "42", typeAdapter, new JsonArray(3));

    // Act
    element.write(new JsonWriter(new StringWriter()));

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test Element {@link Element#write(JsonWriter)}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code true}.
   *   <li>Then calls {@link JsonSerializer#serialize(Object, Type, JsonSerializationContext)}.
   * </ul>
   *
   * <p>Method under test: {@link Element#write(JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Element.write(JsonWriter)"})
  public void testElementWrite_givenJsonArrayWithCapacityIsThreeAddTrue_thenCallsSerialize()
      throws IOException {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(true);
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(jsonArray);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Element<Object> element = new Element<>("Value", "42", typeAdapter, new JsonArray(3));

    // Act
    element.write(new JsonWriter(new StringWriter()));

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test Element {@link Element#write(JsonWriter)}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add valueOf one.
   * </ul>
   *
   * <p>Method under test: {@link Element#write(JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Element.write(JsonWriter)"})
  public void testElementWrite_givenJsonArrayWithCapacityIsThreeAddValueOfOne() throws IOException {
    // Arrange
    JsonArray jsonArray = new JsonArray(3);
    jsonArray.add(Integer.valueOf(1));
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(jsonArray);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Element<Object> element = new Element<>("Value", "42", typeAdapter, new JsonArray(3));

    // Act
    element.write(new JsonWriter(new StringWriter()));

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test Element {@link Element#write(JsonWriter)}.
   *
   * <ul>
   *   <li>Given {@link JsonSerializer} {@link JsonSerializer#serialize(Object, Type,
   *       JsonSerializationContext)} return {@link JsonArray#JsonArray(int)} with capacity is
   *       three.
   * </ul>
   *
   * <p>Method under test: {@link Element#write(JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Element.write(JsonWriter)"})
  public void testElementWrite_givenJsonSerializerSerializeReturnJsonArrayWithCapacityIsThree()
      throws IOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonArray(3));
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Element<Object> element = new Element<>("Value", "42", typeAdapter, new JsonArray(3));

    // Act
    element.write(new JsonWriter(new StringWriter()));

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test Element {@link Element#write(JsonWriter)}.
   *
   * <ul>
   *   <li>Given {@link JsonSerializer} {@link JsonSerializer#serialize(Object, Type,
   *       JsonSerializationContext)} return {@link JsonNull} (default constructor).
   *   <li>Then calls {@link JsonSerializer#serialize(Object, Type, JsonSerializationContext)}.
   * </ul>
   *
   * <p>Method under test: {@link Element#write(JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Element.write(JsonWriter)"})
  public void testElementWrite_givenJsonSerializerSerializeReturnJsonNull_thenCallsSerialize()
      throws IOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonNull());
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Element<Object> element = new Element<>("Value", "42", typeAdapter, new JsonArray(3));

    // Act
    element.write(new JsonWriter(new StringWriter()));

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test Element {@link Element#write(JsonWriter)}.
   *
   * <ul>
   *   <li>Given {@link JsonSerializer} {@link JsonSerializer#serialize(Object, Type,
   *       JsonSerializationContext)} return {@link JsonObject} (default constructor).
   *   <li>Then calls {@link JsonSerializer#serialize(Object, Type, JsonSerializationContext)}.
   * </ul>
   *
   * <p>Method under test: {@link Element#write(JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Element.write(JsonWriter)"})
  public void testElementWrite_givenJsonSerializerSerializeReturnJsonObject_thenCallsSerialize()
      throws IOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonObject());
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Element<Object> element = new Element<>("Value", "42", typeAdapter, new JsonArray(3));

    // Act
    element.write(new JsonWriter(new StringWriter()));

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test Element {@link Element#write(JsonWriter)}.
   *
   * <ul>
   *   <li>Given {@link JsonSerializer} {@link JsonSerializer#serialize(Object, Type,
   *       JsonSerializationContext)} return {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool
   *       is {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link Element#write(JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Element.write(JsonWriter)"})
  public void testElementWrite_givenJsonSerializerSerializeReturnJsonPrimitiveWithBoolIsTrue()
      throws IOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonPrimitive(true));
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Element<Object> element = new Element<>("Value", "42", typeAdapter, new JsonArray(3));

    // Act
    element.write(new JsonWriter(new StringWriter()));

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test Element {@link Element#write(JsonWriter)}.
   *
   * <ul>
   *   <li>Given {@link JsonSerializer} {@link JsonSerializer#serialize(Object, Type,
   *       JsonSerializationContext)} return {@link JsonPrimitive#JsonPrimitive(String)} with {@code
   *       String}.
   * </ul>
   *
   * <p>Method under test: {@link Element#write(JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Element.write(JsonWriter)"})
  public void testElementWrite_givenJsonSerializerSerializeReturnJsonPrimitiveWithString()
      throws IOException {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    when(serializer.serialize(
            Mockito.<Object>any(), Mockito.<Type>any(), Mockito.<JsonSerializationContext>any()))
        .thenReturn(new JsonPrimitive("String"));
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    TreeTypeAdapter<Object> typeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));

    Element<Object> element = new Element<>("Value", "42", typeAdapter, new JsonArray(3));

    // Act
    element.write(new JsonWriter(new StringWriter()));

    // Assert
    verify(serializer)
        .serialize(isA(Object.class), isA(Type.class), isA(JsonSerializationContext.class));
  }

  /**
   * Test Factory {@link Factory#createInstance(Type)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link Factory#createInstance(Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Factory.createInstance(Type)"})
  public void testFactoryCreateInstance_whenNull_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new Factory(new HashMap<>()).createInstance(null));
  }

  /**
   * Test Factory {@link Factory#create(Gson, TypeToken)}.
   *
   * <ul>
   *   <li>Given {@code Object}.
   *   <li>When {@link Gson#Gson()}.
   *   <li>Then return toJson {@code Value} is {@code {"0x1":"Value"}}.
   * </ul>
   *
   * <p>Method under test: {@link Factory#create(Gson, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"com.google.gson.TypeAdapter Factory.create(Gson, TypeToken)"})
  public void testFactoryCreate_givenJavaLangObject_whenGson_thenReturnToJsonValueIs0x1Value() {
    // Arrange
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    Class<Object> forNameResult = Object.class;
    instanceCreators.put(forNameResult, mock(InstanceCreator.class));
    Factory factory = new Factory(instanceCreators);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> type2 = TypeToken.get(type);

    // Act and Assert
    assertEquals("{\"0x1\":\"Value\"}", factory.create(gson, type2).toJson("Value"));
  }

  /**
   * Test Factory {@link Factory#create(Gson, TypeToken)}.
   *
   * <ul>
   *   <li>When {@link Gson#Gson()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Factory#create(Gson, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"com.google.gson.TypeAdapter Factory.create(Gson, TypeToken)"})
  public void testFactoryCreate_whenGson_thenReturnNull() {
    // Arrange
    Factory factory = new Factory(new HashMap<>());
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> type2 = TypeToken.get(type);

    // Act and Assert
    assertNull(factory.create(gson, type2));
  }
}
