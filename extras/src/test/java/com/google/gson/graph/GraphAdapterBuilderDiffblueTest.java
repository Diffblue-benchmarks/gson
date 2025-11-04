package com.google.gson.graph;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.gson.Gson;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.util.HashMap;
import org.junit.Test;
import org.mockito.Mockito;

public class GraphAdapterBuilderDiffblueTest {
  /** Method under test: {@link GraphAdapterBuilder#addType(Type, InstanceCreator)} */
  @Test
  public void testAddType() {
    // Arrange
    GraphAdapterBuilder graphAdapterBuilder = new GraphAdapterBuilder();
    Class<Object> type = Object.class;

    // Act and Assert
    assertSame(graphAdapterBuilder, graphAdapterBuilder.addType(type, mock(InstanceCreator.class)));
  }

  /** Method under test: {@link GraphAdapterBuilder.Element#read(GraphAdapterBuilder.Graph)} */
  @Test
  public void testElementRead() {
    // Arrange
    TypeAdapter<Object> typeAdapter = mock(TypeAdapter.class);
    when(typeAdapter.fromJsonTree(Mockito.<JsonElement>any())).thenReturn("From Json Tree");
    GraphAdapterBuilder.Element<Object> element =
        new GraphAdapterBuilder.Element<>("Value", "42", typeAdapter, new JsonArray());

    // Act
    element.read(mock(GraphAdapterBuilder.Graph.class));

    // Assert
    verify(typeAdapter).fromJsonTree(isA(JsonElement.class));
  }

  /** Method under test: {@link GraphAdapterBuilder.Element#read(GraphAdapterBuilder.Graph)} */
  @Test
  public void testElementRead2() {
    // Arrange
    TypeAdapter<Object> typeAdapter = mock(TypeAdapter.class);
    when(typeAdapter.fromJsonTree(Mockito.<JsonElement>any())).thenReturn(null);
    GraphAdapterBuilder.Element<Object> element =
        new GraphAdapterBuilder.Element<>("Value", "42", typeAdapter, new JsonArray());

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> element.read(mock(GraphAdapterBuilder.Graph.class)));
    verify(typeAdapter).fromJsonTree(isA(JsonElement.class));
  }

  /** Method under test: {@link GraphAdapterBuilder.Element#read(GraphAdapterBuilder.Graph)} */
  @Test
  public void testElementRead3() {
    // Arrange
    TypeAdapter<Object> typeAdapter = mock(TypeAdapter.class);
    when(typeAdapter.fromJsonTree(Mockito.<JsonElement>any())).thenReturn(null);
    GraphAdapterBuilder.Element<Object> element =
        new GraphAdapterBuilder.Element<>("Value", "42", typeAdapter, new JsonNull());

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> element.read(mock(GraphAdapterBuilder.Graph.class)));
    verify(typeAdapter).fromJsonTree(isA(JsonElement.class));
  }

  /** Method under test: {@link GraphAdapterBuilder.Element#read(GraphAdapterBuilder.Graph)} */
  @Test
  public void testElementRead4() {
    // Arrange
    TypeAdapter<Object> typeAdapter = mock(TypeAdapter.class);
    when(typeAdapter.fromJsonTree(Mockito.<JsonElement>any())).thenReturn(null);
    GraphAdapterBuilder.Element<Object> element =
        new GraphAdapterBuilder.Element<>("Value", "42", typeAdapter, new JsonPrimitive("String"));

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> element.read(mock(GraphAdapterBuilder.Graph.class)));
    verify(typeAdapter).fromJsonTree(isA(JsonElement.class));
  }

  /** Method under test: {@link GraphAdapterBuilder.Element#read(GraphAdapterBuilder.Graph)} */
  @Test
  public void testElementRead5() {
    // Arrange
    TypeAdapter<Object> typeAdapter = mock(TypeAdapter.class);
    when(typeAdapter.fromJsonTree(Mockito.<JsonElement>any())).thenReturn(null);
    GraphAdapterBuilder.Element<Object> element =
        new GraphAdapterBuilder.Element<>("Value", "42", typeAdapter, null);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> element.read(mock(GraphAdapterBuilder.Graph.class)));
    verify(typeAdapter).fromJsonTree(isNull());
  }

  /** Method under test: {@link GraphAdapterBuilder.Element#read(GraphAdapterBuilder.Graph)} */
  @Test
  public void testElementRead6() {
    // Arrange
    TypeAdapter<Object> typeAdapter = mock(TypeAdapter.class);
    when(typeAdapter.fromJsonTree(Mockito.<JsonElement>any())).thenReturn(null);
    GraphAdapterBuilder.Element<Object> element =
        new GraphAdapterBuilder.Element<>("Value", "42", typeAdapter, new JsonPrimitive(true));

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> element.read(mock(GraphAdapterBuilder.Graph.class)));
    verify(typeAdapter).fromJsonTree(isA(JsonElement.class));
  }

  /** Method under test: {@link GraphAdapterBuilder.Element#read(GraphAdapterBuilder.Graph)} */
  @Test
  public void testElementRead7() {
    // Arrange
    TypeAdapter<Object> typeAdapter = mock(TypeAdapter.class);
    when(typeAdapter.fromJsonTree(Mockito.<JsonElement>any())).thenReturn(null);
    GraphAdapterBuilder.Element<Object> element =
        new GraphAdapterBuilder.Element<>("Value", "42", typeAdapter, new JsonObject());

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> element.read(mock(GraphAdapterBuilder.Graph.class)));
    verify(typeAdapter).fromJsonTree(isA(JsonElement.class));
  }

  /** Method under test: {@link GraphAdapterBuilder.Element#read(GraphAdapterBuilder.Graph)} */
  @Test
  public void testElementRead8() {
    // Arrange
    TypeAdapter<Object> typeAdapter = mock(TypeAdapter.class);
    when(typeAdapter.fromJsonTree(Mockito.<JsonElement>any())).thenReturn(null);

    JsonArray element = new JsonArray();
    element.add(false);
    GraphAdapterBuilder.Element<Object> element2 =
        new GraphAdapterBuilder.Element<>("Value", "42", typeAdapter, element);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> element2.read(mock(GraphAdapterBuilder.Graph.class)));
    verify(typeAdapter).fromJsonTree(isA(JsonElement.class));
  }

  /** Method under test: {@link GraphAdapterBuilder.Element#read(GraphAdapterBuilder.Graph)} */
  @Test
  public void testElementRead9() {
    // Arrange
    TypeAdapter<Object> typeAdapter = mock(TypeAdapter.class);
    when(typeAdapter.fromJsonTree(Mockito.<JsonElement>any())).thenReturn(null);

    JsonArray element = new JsonArray();
    element.add(' ');
    GraphAdapterBuilder.Element<Object> element2 =
        new GraphAdapterBuilder.Element<>("Value", "42", typeAdapter, element);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> element2.read(mock(GraphAdapterBuilder.Graph.class)));
    verify(typeAdapter).fromJsonTree(isA(JsonElement.class));
  }

  /** Method under test: {@link GraphAdapterBuilder.Element#read(GraphAdapterBuilder.Graph)} */
  @Test
  public void testElementRead10() {
    // Arrange
    TypeAdapter<Object> typeAdapter = mock(TypeAdapter.class);
    when(typeAdapter.fromJsonTree(Mockito.<JsonElement>any())).thenReturn(null);

    JsonArray element = new JsonArray();
    element.add(Integer.valueOf(Integer.SIZE));
    GraphAdapterBuilder.Element<Object> element2 =
        new GraphAdapterBuilder.Element<>("Value", "42", typeAdapter, element);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> element2.read(mock(GraphAdapterBuilder.Graph.class)));
    verify(typeAdapter).fromJsonTree(isA(JsonElement.class));
  }

  /** Method under test: {@link GraphAdapterBuilder.Element#read(GraphAdapterBuilder.Graph)} */
  @Test
  public void testElementRead11() {
    // Arrange
    TypeAdapter<Object> typeAdapter = mock(TypeAdapter.class);
    when(typeAdapter.fromJsonTree(Mockito.<JsonElement>any())).thenReturn(null);

    JsonArray element = new JsonArray();
    element.add(new JsonArray());
    GraphAdapterBuilder.Element<Object> element2 =
        new GraphAdapterBuilder.Element<>("Value", "42", typeAdapter, element);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> element2.read(mock(GraphAdapterBuilder.Graph.class)));
    verify(typeAdapter).fromJsonTree(isA(JsonElement.class));
  }

  /** Method under test: {@link GraphAdapterBuilder.Element#read(GraphAdapterBuilder.Graph)} */
  @Test
  public void testElementRead12() {
    // Arrange
    TypeAdapter<Object> typeAdapter = mock(TypeAdapter.class);
    when(typeAdapter.fromJsonTree(Mockito.<JsonElement>any())).thenReturn(null);

    JsonArray element = new JsonArray();
    element.add(true);
    element.add(false);
    GraphAdapterBuilder.Element<Object> element2 =
        new GraphAdapterBuilder.Element<>("Value", "42", typeAdapter, element);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> element2.read(mock(GraphAdapterBuilder.Graph.class)));
    verify(typeAdapter).fromJsonTree(isA(JsonElement.class));
  }

  /** Method under test: {@link GraphAdapterBuilder.Element#read(GraphAdapterBuilder.Graph)} */
  @Test
  public void testElementRead13() {
    // Arrange
    TypeAdapter<Object> typeAdapter = mock(TypeAdapter.class);
    when(typeAdapter.fromJsonTree(Mockito.<JsonElement>any())).thenReturn(null);

    JsonArray element = new JsonArray();
    element.add('\u0006');
    element.add(false);
    GraphAdapterBuilder.Element<Object> element2 =
        new GraphAdapterBuilder.Element<>("Value", "42", typeAdapter, element);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> element2.read(mock(GraphAdapterBuilder.Graph.class)));
    verify(typeAdapter).fromJsonTree(isA(JsonElement.class));
  }

  /** Method under test: {@link GraphAdapterBuilder.Element#write(JsonWriter)} */
  @Test
  public void testElementWrite() throws IOException {
    // Arrange
    TypeAdapter<Object> typeAdapter = mock(TypeAdapter.class);
    doNothing().when(typeAdapter).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());
    GraphAdapterBuilder.Element<Object> element =
        new GraphAdapterBuilder.Element<>("Value", "42", typeAdapter, new JsonArray());

    // Act
    element.write(new JsonWriter(new StringWriter()));

    // Assert
    verify(typeAdapter).write(isA(JsonWriter.class), isA(Object.class));
  }

  /** Method under test: {@link GraphAdapterBuilder.Factory#create(Gson, TypeToken)} */
  @Test
  public void testFactoryCreate() {
    // Arrange
    GraphAdapterBuilder.Factory factory = new GraphAdapterBuilder.Factory(new HashMap<>());
    Gson gson = new Gson();
    TypeToken<Object> type = mock(TypeToken.class);
    Class<Object> forNameResult = Object.class;
    when(type.getType()).thenReturn(forNameResult);

    // Act
    TypeAdapter<Object> actualCreateResult = factory.create(gson, type);

    // Assert
    verify(type).getType();
    assertNull(actualCreateResult);
  }

  /** Method under test: {@link GraphAdapterBuilder.Factory#create(Gson, TypeToken)} */
  @Test
  public void testFactoryCreate2() {
    // Arrange
    GraphAdapterBuilder.Factory factory = new GraphAdapterBuilder.Factory(new HashMap<>());
    Gson gson = new Gson();
    TypeToken<Object> type = mock(TypeToken.class);
    when(type.getType()).thenThrow(new IllegalStateException("foo"));

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> factory.create(gson, type));
    verify(type).getType();
  }
}
