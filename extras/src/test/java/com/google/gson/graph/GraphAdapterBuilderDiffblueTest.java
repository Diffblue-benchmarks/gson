package com.google.gson.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.graph.GraphAdapterBuilder.Element;
import com.google.gson.graph.GraphAdapterBuilder.Factory;
import com.google.gson.graph.GraphAdapterBuilder.Graph;
import com.google.gson.internal.bind.ArrayTypeAdapter;
import com.google.gson.internal.bind.ObjectTypeAdapter;
import com.google.gson.internal.bind.TreeTypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
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

    // Act
    GraphAdapterBuilder actualAddTypeResult =
        graphAdapterBuilder.addType(type, mock(InstanceCreator.class));

    // Assert
    assertSame(graphAdapterBuilder, actualAddTypeResult);
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

    // Act
    GraphAdapterBuilder actualAddTypeResult = graphAdapterBuilder.addType(type);

    // Assert
    assertSame(graphAdapterBuilder, actualAddTypeResult);
  }

  /**
   * Test {@link GraphAdapterBuilder#addType(Type)} with {@code type}.
   *
   * <ul>
   *   <li>When {@code Type}.
   *   <li>Then return {@link GraphAdapterBuilder} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link GraphAdapterBuilder#addType(Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"GraphAdapterBuilder GraphAdapterBuilder.addType(Type)"})
  public void testAddTypeWithType_whenJavaLangReflectType_thenReturnGraphAdapterBuilder() {
    // Arrange
    GraphAdapterBuilder graphAdapterBuilder = new GraphAdapterBuilder();
    Class<Type> type = Type.class;

    // Act
    GraphAdapterBuilder actualAddTypeResult = graphAdapterBuilder.addType(type);

    // Assert
    assertSame(graphAdapterBuilder, actualAddTypeResult);
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
  public void testElementRead() {
    // Arrange
    ObjectTypeAdapter typeAdapter = mock(ObjectTypeAdapter.class);
    when(typeAdapter.fromJsonTree(Mockito.<JsonElement>any())).thenReturn(null);
    Element<Object> element = new Element<>("Value", "42", typeAdapter, new JsonArray());

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> element.read(mock(Graph.class)));
    verify(typeAdapter).fromJsonTree(isA(JsonElement.class));
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
    ObjectTypeAdapter typeAdapter = mock(ObjectTypeAdapter.class);
    when(typeAdapter.fromJsonTree(Mockito.<JsonElement>any())).thenReturn(null);
    Element<Object> element = new Element<>("Value", "42", typeAdapter, new JsonNull());

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> element.read(mock(Graph.class)));
    verify(typeAdapter).fromJsonTree(isA(JsonElement.class));
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
  public void testElementRead3() {
    // Arrange
    ObjectTypeAdapter typeAdapter = mock(ObjectTypeAdapter.class);
    when(typeAdapter.fromJsonTree(Mockito.<JsonElement>any())).thenReturn(null);
    Element<Object> element = new Element<>("Value", "42", typeAdapter, null);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> element.read(mock(Graph.class)));
    verify(typeAdapter).fromJsonTree(isNull());
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
  public void testElementRead4() {
    // Arrange
    ObjectTypeAdapter typeAdapter = mock(ObjectTypeAdapter.class);
    when(typeAdapter.fromJsonTree(Mockito.<JsonElement>any())).thenReturn(null);
    Element<Object> element = new Element<>("Value", "42", typeAdapter, new JsonObject());

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> element.read(mock(Graph.class)));
    verify(typeAdapter).fromJsonTree(isA(JsonElement.class));
  }

  /**
   * Test Element {@link Element#read(Graph)}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray()} add {@code false}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link Element#read(Graph)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Element.read(Graph)"})
  public void testElementRead_givenJsonArrayAddFalse_thenThrowIllegalStateException() {
    // Arrange
    ObjectTypeAdapter typeAdapter = mock(ObjectTypeAdapter.class);
    when(typeAdapter.fromJsonTree(Mockito.<JsonElement>any())).thenReturn(null);

    JsonArray element = new JsonArray();
    element.add(false);
    element.add(true);

    Element<Object> element2 = new Element<>("Value", "42", typeAdapter, element);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> element2.read(mock(Graph.class)));
    verify(typeAdapter).fromJsonTree(isA(JsonElement.class));
  }

  /**
   * Test Element {@link Element#read(Graph)}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray()} add {@link JsonArray#JsonArray()}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link Element#read(Graph)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Element.read(Graph)"})
  public void testElementRead_givenJsonArrayAddJsonArray_thenThrowIllegalStateException() {
    // Arrange
    ObjectTypeAdapter typeAdapter = mock(ObjectTypeAdapter.class);
    when(typeAdapter.fromJsonTree(Mockito.<JsonElement>any())).thenReturn(null);

    JsonArray element = new JsonArray();
    element.add(new JsonArray());

    Element<Object> element2 = new Element<>("Value", "42", typeAdapter, element);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> element2.read(mock(Graph.class)));
    verify(typeAdapter).fromJsonTree(isA(JsonElement.class));
  }

  /**
   * Test Element {@link Element#read(Graph)}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray()} add {@link JsonNull} (default constructor).
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link Element#read(Graph)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Element.read(Graph)"})
  public void testElementRead_givenJsonArrayAddJsonNull_thenThrowIllegalStateException() {
    // Arrange
    ObjectTypeAdapter typeAdapter = mock(ObjectTypeAdapter.class);
    when(typeAdapter.fromJsonTree(Mockito.<JsonElement>any())).thenReturn(null);

    JsonArray element = new JsonArray();
    element.add(new JsonNull());

    Element<Object> element2 = new Element<>("Value", "42", typeAdapter, element);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> element2.read(mock(Graph.class)));
    verify(typeAdapter).fromJsonTree(isA(JsonElement.class));
  }

  /**
   * Test Element {@link Element#read(Graph)}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray()} add {@link JsonObject} (default constructor).
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link Element#read(Graph)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Element.read(Graph)"})
  public void testElementRead_givenJsonArrayAddJsonObject_thenThrowIllegalStateException() {
    // Arrange
    ObjectTypeAdapter typeAdapter = mock(ObjectTypeAdapter.class);
    when(typeAdapter.fromJsonTree(Mockito.<JsonElement>any())).thenReturn(null);

    JsonArray element = new JsonArray();
    element.add(new JsonObject());

    Element<Object> element2 = new Element<>("Value", "42", typeAdapter, element);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> element2.read(mock(Graph.class)));
    verify(typeAdapter).fromJsonTree(isA(JsonElement.class));
  }

  /**
   * Test Element {@link Element#read(Graph)}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray()} add start of heading.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link Element#read(Graph)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Element.read(Graph)"})
  public void testElementRead_givenJsonArrayAddStartOfHeading_thenThrowIllegalStateException() {
    // Arrange
    ObjectTypeAdapter typeAdapter = mock(ObjectTypeAdapter.class);
    when(typeAdapter.fromJsonTree(Mockito.<JsonElement>any())).thenReturn(null);

    JsonArray element = new JsonArray();
    element.add('\u0001');

    Element<Object> element2 = new Element<>("Value", "42", typeAdapter, element);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> element2.read(mock(Graph.class)));
    verify(typeAdapter).fromJsonTree(isA(JsonElement.class));
  }

  /**
   * Test Element {@link Element#read(Graph)}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray()} add {@code true}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link Element#read(Graph)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Element.read(Graph)"})
  public void testElementRead_givenJsonArrayAddTrue_thenThrowIllegalStateException() {
    // Arrange
    ObjectTypeAdapter typeAdapter = mock(ObjectTypeAdapter.class);
    when(typeAdapter.fromJsonTree(Mockito.<JsonElement>any())).thenReturn(null);

    JsonArray element = new JsonArray();
    element.add(true);

    Element<Object> element2 = new Element<>("Value", "42", typeAdapter, element);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> element2.read(mock(Graph.class)));
    verify(typeAdapter).fromJsonTree(isA(JsonElement.class));
  }

  /**
   * Test Element {@link Element#read(Graph)}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray()} add valueOf {@link Integer#SIZE}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link Element#read(Graph)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Element.read(Graph)"})
  public void testElementRead_givenJsonArrayAddValueOfSize_thenThrowIllegalStateException() {
    // Arrange
    ObjectTypeAdapter typeAdapter = mock(ObjectTypeAdapter.class);
    when(typeAdapter.fromJsonTree(Mockito.<JsonElement>any())).thenReturn(null);

    JsonArray element = new JsonArray();
    element.add(Integer.valueOf(Integer.SIZE));

    Element<Object> element2 = new Element<>("Value", "42", typeAdapter, element);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> element2.read(mock(Graph.class)));
    verify(typeAdapter).fromJsonTree(isA(JsonElement.class));
  }

  /**
   * Test Element {@link Element#read(Graph)}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code ,} and {@link
   *       JsonArray#JsonArray()}.
   * </ul>
   *
   * <p>Method under test: {@link Element#read(Graph)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Element.read(Graph)"})
  public void testElementRead_givenJsonObjectAddCommaAndJsonArray() {
    // Arrange
    ObjectTypeAdapter typeAdapter = mock(ObjectTypeAdapter.class);
    when(typeAdapter.fromJsonTree(Mockito.<JsonElement>any())).thenReturn(null);

    JsonObject element = new JsonObject();
    element.add(",", new JsonArray());

    Element<Object> element2 = new Element<>("Value", "42", typeAdapter, element);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> element2.read(mock(Graph.class)));
    verify(typeAdapter).fromJsonTree(isA(JsonElement.class));
  }

  /**
   * Test Element {@link Element#read(Graph)}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code ,} and {@link JsonNull}
   *       (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link Element#read(Graph)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Element.read(Graph)"})
  public void testElementRead_givenJsonObjectAddCommaAndJsonNull() {
    // Arrange
    ObjectTypeAdapter typeAdapter = mock(ObjectTypeAdapter.class);
    when(typeAdapter.fromJsonTree(Mockito.<JsonElement>any())).thenReturn(null);

    JsonObject element = new JsonObject();
    element.add(",", new JsonNull());

    Element<Object> element2 = new Element<>("Value", "42", typeAdapter, element);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> element2.read(mock(Graph.class)));
    verify(typeAdapter).fromJsonTree(isA(JsonElement.class));
  }

  /**
   * Test Element {@link Element#read(Graph)}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) add {@code non-null value deserialized to
   *       null:} and {@link JsonArray#JsonArray()}.
   * </ul>
   *
   * <p>Method under test: {@link Element#read(Graph)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Element.read(Graph)"})
  public void testElementRead_givenJsonObjectAddNonNullValueDeserializedToNullAndJsonArray() {
    // Arrange
    ObjectTypeAdapter typeAdapter = mock(ObjectTypeAdapter.class);
    when(typeAdapter.fromJsonTree(Mockito.<JsonElement>any())).thenReturn(null);

    JsonObject element = new JsonObject();
    element.add("non-null value deserialized to null: ", new JsonArray());
    element.add(",", new JsonArray());

    Element<Object> element2 = new Element<>("Value", "42", typeAdapter, element);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> element2.read(mock(Graph.class)));
    verify(typeAdapter).fromJsonTree(isA(JsonElement.class));
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
  public void testElementRead_givenJsonPrimitiveWithBoolIsTrue_thenThrowIllegalStateException() {
    // Arrange
    ObjectTypeAdapter typeAdapter = mock(ObjectTypeAdapter.class);
    when(typeAdapter.fromJsonTree(Mockito.<JsonElement>any())).thenReturn(null);
    Element<Object> element = new Element<>("Value", "42", typeAdapter, new JsonPrimitive(true));

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> element.read(mock(Graph.class)));
    verify(typeAdapter).fromJsonTree(isA(JsonElement.class));
  }

  /**
   * Test Element {@link Element#read(Graph)}.
   *
   * <ul>
   *   <li>Given {@link JsonPrimitive#JsonPrimitive(String)} with {@code String}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link Element#read(Graph)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Element.read(Graph)"})
  public void testElementRead_givenJsonPrimitiveWithString_thenThrowIllegalStateException() {
    // Arrange
    ObjectTypeAdapter typeAdapter = mock(ObjectTypeAdapter.class);
    when(typeAdapter.fromJsonTree(Mockito.<JsonElement>any())).thenReturn(null);
    Element<Object> element =
        new Element<>("Value", "42", typeAdapter, new JsonPrimitive("String"));

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> element.read(mock(Graph.class)));
    verify(typeAdapter).fromJsonTree(isA(JsonElement.class));
  }

  /**
   * Test Element {@link Element#read(Graph)}.
   *
   * <ul>
   *   <li>Given {@link ObjectTypeAdapter} {@link ObjectTypeAdapter#fromJsonTree(JsonElement)}
   *       return {@code From Json Tree}.
   * </ul>
   *
   * <p>Method under test: {@link Element#read(Graph)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Element.read(Graph)"})
  public void testElementRead_givenObjectTypeAdapterFromJsonTreeReturnFromJsonTree() {
    // Arrange
    ObjectTypeAdapter typeAdapter = mock(ObjectTypeAdapter.class);
    when(typeAdapter.fromJsonTree(Mockito.<JsonElement>any())).thenReturn("From Json Tree");
    Element<Object> element = new Element<>("Value", "42", typeAdapter, new JsonArray());

    // Act
    element.read(mock(Graph.class));

    // Assert
    verify(typeAdapter).fromJsonTree(isA(JsonElement.class));
  }

  /**
   * Test Element {@link Element#read(Graph)}.
   *
   * <ul>
   *   <li>Given {@link StringReader#StringReader(String)} with {@code in == null}.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link Element#read(Graph)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Element.read(Graph)"})
  public void testElementRead_givenStringReaderWithInNull_thenDoesNotThrow() {
    // Arrange
    StringReader stringReader = new StringReader("in == null");
    Gson context = new Gson();
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);

    TreeTypeAdapter<Object> componentTypeAdapter =
        new TreeTypeAdapter<>(
            serializer,
            deserializer,
            new Gson(),
            mock(TypeToken.class),
            mock(TypeAdapterFactory.class));
    Class<Object> componentType = Object.class;

    ArrayTypeAdapter<Object> typeAdapter =
        new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType);

    Element<Object> element = new Element<>(stringReader, "42", typeAdapter, new JsonArray());

    // Act and Assert
    element.read(mock(Graph.class));
  }

  /**
   * Test Element {@link Element#read(Graph)}.
   *
   * <ul>
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
  public void testElementRead_thenCallsDeserialize() throws JsonParseException {
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
    Element<Object> element = new Element<>("Value", "42", typeAdapter, new JsonArray());

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
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link Element#read(Graph)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Element.read(Graph)"})
  public void testElementRead_thenDoesNotThrow() {
    // Arrange
    Gson context = new Gson();
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);

    TreeTypeAdapter<Object> componentTypeAdapter =
        new TreeTypeAdapter<>(
            serializer,
            deserializer,
            new Gson(),
            mock(TypeToken.class),
            mock(TypeAdapterFactory.class));
    Class<Object> componentType = Object.class;

    ArrayTypeAdapter<Object> typeAdapter =
        new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType);
    Element<Object> element = new Element<>("Value", "42", typeAdapter, new JsonArray());

    // Act and Assert
    element.read(mock(Graph.class));
  }

  /**
   * Test Element {@link Element#write(JsonWriter)}.
   *
   * <ul>
   *   <li>Given {@link ObjectTypeAdapter} {@link ObjectTypeAdapter#write(JsonWriter, Object)} does
   *       nothing.
   *   <li>Then calls {@link ObjectTypeAdapter#write(JsonWriter, Object)}.
   * </ul>
   *
   * <p>Method under test: {@link Element#write(JsonWriter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Element.write(JsonWriter)"})
  public void testElementWrite_givenObjectTypeAdapterWriteDoesNothing_thenCallsWrite()
      throws IOException {
    // Arrange
    ObjectTypeAdapter typeAdapter = mock(ObjectTypeAdapter.class);
    doNothing().when(typeAdapter).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());
    Element<Object> element = new Element<>("Value", "42", typeAdapter, new JsonArray());

    // Act
    element.write(new JsonWriter(new StringWriter()));

    // Assert
    verify(typeAdapter).write(isA(JsonWriter.class), isA(Object.class));
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

  /**
   * Test {@link GraphAdapterBuilder#registerOn(GsonBuilder)}.
   *
   * <ul>
   *   <li>Given {@link GraphAdapterBuilder} (default constructor).
   *   <li>Then calls {@link GsonBuilder#registerTypeAdapterFactory(TypeAdapterFactory)}.
   * </ul>
   *
   * <p>Method under test: {@link GraphAdapterBuilder#registerOn(GsonBuilder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void GraphAdapterBuilder.registerOn(GsonBuilder)"})
  public void testRegisterOn_givenGraphAdapterBuilder_thenCallsRegisterTypeAdapterFactory() {
    // Arrange
    GraphAdapterBuilder graphAdapterBuilder = new GraphAdapterBuilder();

    GsonBuilder gsonBuilder = mock(GsonBuilder.class);
    when(gsonBuilder.registerTypeAdapterFactory(Mockito.<TypeAdapterFactory>any()))
        .thenReturn(new GsonBuilder());

    // Act
    graphAdapterBuilder.registerOn(gsonBuilder);

    // Assert
    verify(gsonBuilder).registerTypeAdapterFactory(isA(TypeAdapterFactory.class));
  }

  /**
   * Test {@link GraphAdapterBuilder#registerOn(GsonBuilder)}.
   *
   * <ul>
   *   <li>Then calls {@link GsonBuilder#registerTypeAdapter(Type, Object)}.
   * </ul>
   *
   * <p>Method under test: {@link GraphAdapterBuilder#registerOn(GsonBuilder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void GraphAdapterBuilder.registerOn(GsonBuilder)"})
  public void testRegisterOn_thenCallsRegisterTypeAdapter() {
    // Arrange
    GraphAdapterBuilder graphAdapterBuilder = new GraphAdapterBuilder();
    Class<Object> type = Object.class;
    graphAdapterBuilder.addType(type);

    GsonBuilder gsonBuilder = mock(GsonBuilder.class);
    when(gsonBuilder.registerTypeAdapter(Mockito.<Type>any(), Mockito.<Object>any()))
        .thenReturn(new GsonBuilder());
    when(gsonBuilder.registerTypeAdapterFactory(Mockito.<TypeAdapterFactory>any()))
        .thenReturn(new GsonBuilder());

    // Act
    graphAdapterBuilder.registerOn(gsonBuilder);

    // Assert
    verify(gsonBuilder).registerTypeAdapter(isA(Type.class), isA(Object.class));
    verify(gsonBuilder).registerTypeAdapterFactory(isA(TypeAdapterFactory.class));
  }
}
