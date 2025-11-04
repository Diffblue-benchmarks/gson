package com.google.gson.internal.bind;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.TreeMap;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.GenericMetadataSupport;

public class TypeAdapterRuntimeTypeWrapperDiffblueTest {
  /** Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)} */
  @Test
  public void testRead() throws IOException {
    // Arrange
    TypeAdapter<Object> delegate = mock(TypeAdapter.class);
    when(delegate.read(Mockito.<JsonReader>any())).thenReturn("Read");
    Gson context = new Gson();
    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, new GenericMetadataSupport.TypeVarBoundedType(null));

    // Act
    Object actualReadResult =
        typeAdapterRuntimeTypeWrapper.read(new JsonReader(new StringReader("foo")));

    // Assert
    verify(delegate).read(isA(JsonReader.class));
    assertEquals("Read", actualReadResult);
  }

  /** Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)} */
  @Test
  public void testWrite() throws IOException {
    // Arrange
    TypeAdapter<Object> delegate = mock(TypeAdapter.class);
    doNothing().when(delegate).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());
    Gson context = new Gson();
    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, new GenericMetadataSupport.TypeVarBoundedType(null));

    // Act
    typeAdapterRuntimeTypeWrapper.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(delegate).write(isA(JsonWriter.class), isA(Object.class));
  }

  /** Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)} */
  @Test
  public void testWrite2() throws IOException {
    // Arrange
    TypeAdapter<Object> delegate = mock(TypeAdapter.class);
    doNothing().when(delegate).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());
    Gson context = new Gson();
    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(
            context, delegate, new GenericMetadataSupport.TypeVarBoundedType(null));

    // Act
    typeAdapterRuntimeTypeWrapper.write(new JsonWriter(new StringWriter()), null);

    // Assert
    verify(delegate).write(isA(JsonWriter.class), isNull());
  }

  /** Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)} */
  @Test
  public void testWrite3() throws IOException {
    // Arrange
    Gson context = new Gson();
    TypeAdapter<Object> delegate = mock(TypeAdapter.class);
    Class<Object> type = Object.class;
    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, type);
    JsonWriter out = mock(JsonWriter.class);
    when(out.value(Mockito.<String>any())).thenReturn(new JsonWriter(new StringWriter()));

    // Act
    typeAdapterRuntimeTypeWrapper.write(out, "Value");

    // Assert
    verify(out).value(eq("Value"));
  }

  /** Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)} */
  @Test
  public void testWrite4() throws IOException {
    // Arrange
    Gson context = new Gson();
    TypeAdapter<Object> delegate = mock(TypeAdapter.class);
    Class<Object> type = Object.class;
    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, type);
    JsonWriter out = mock(JsonWriter.class);
    when(out.beginObject()).thenReturn(new JsonWriter(new StringWriter()));
    when(out.endObject()).thenReturn(new JsonWriter(new StringWriter()));

    // Act
    typeAdapterRuntimeTypeWrapper.write(out, new HashMap<>());

    // Assert
    verify(out).beginObject();
    verify(out).endObject();
  }

  /** Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)} */
  @Test
  public void testWrite5() throws IOException {
    // Arrange
    Gson context = new Gson();
    TypeAdapter<Object> delegate = mock(TypeAdapter.class);
    Class<Object> type = Object.class;
    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, type);
    JsonWriter out = mock(JsonWriter.class);
    when(out.name(Mockito.<String>any())).thenReturn(new JsonWriter(new StringWriter()));
    when(out.beginObject()).thenReturn(new JsonWriter(new StringWriter()));
    when(out.endObject()).thenReturn(new JsonWriter(new StringWriter()));
    when(out.value(Mockito.<String>any())).thenReturn(new JsonWriter(new StringWriter()));

    HashMap<Object, Object> objectObjectMap = new HashMap<>();
    objectObjectMap.put("42", "42");

    // Act
    typeAdapterRuntimeTypeWrapper.write(out, objectObjectMap);

    // Assert
    verify(out).beginObject();
    verify(out).endObject();
    verify(out).name(eq("42"));
    verify(out).value(eq("42"));
  }

  /** Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)} */
  @Test
  public void testWrite6() throws IOException {
    // Arrange
    Gson context = new Gson();
    TypeAdapter<Object> delegate = mock(TypeAdapter.class);
    Class<Object> type = Object.class;
    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, type);
    JsonWriter out = mock(JsonWriter.class);
    when(out.nullValue()).thenReturn(new JsonWriter(new StringWriter()));
    when(out.name(Mockito.<String>any())).thenReturn(new JsonWriter(new StringWriter()));
    when(out.beginObject()).thenReturn(new JsonWriter(new StringWriter()));
    when(out.endObject()).thenReturn(new JsonWriter(new StringWriter()));

    HashMap<Object, Object> objectObjectMap = new HashMap<>();
    objectObjectMap.put("42", null);

    // Act
    typeAdapterRuntimeTypeWrapper.write(out, objectObjectMap);

    // Assert
    verify(out).beginObject();
    verify(out).endObject();
    verify(out).name(eq("42"));
    verify(out).nullValue();
  }

  /** Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)} */
  @Test
  public void testWrite7() throws IOException {
    // Arrange
    Gson context = new Gson();
    TypeAdapter<Object> delegate = mock(TypeAdapter.class);
    Class<Object> type = Object.class;
    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, type);
    JsonWriter out = mock(JsonWriter.class);
    when(out.name(Mockito.<String>any())).thenReturn(new JsonWriter(new StringWriter()));
    when(out.beginObject()).thenReturn(new JsonWriter(new StringWriter()));
    when(out.endObject()).thenReturn(new JsonWriter(new StringWriter()));

    HashMap<Object, Object> objectObjectMap = new HashMap<>();
    objectObjectMap.put("42", new HashMap<>());

    // Act
    typeAdapterRuntimeTypeWrapper.write(out, objectObjectMap);

    // Assert
    verify(out, atLeast(1)).beginObject();
    verify(out, atLeast(1)).endObject();
    verify(out).name(eq("42"));
  }

  /** Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)} */
  @Test
  public void testWrite8() throws IOException {
    // Arrange
    Gson context = new Gson();
    TypeAdapter<Object> delegate = mock(TypeAdapter.class);
    Class<Object> type = Object.class;
    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, type);
    JsonWriter out = mock(JsonWriter.class);
    when(out.name(Mockito.<String>any())).thenReturn(new JsonWriter(new StringWriter()));
    when(out.beginObject()).thenReturn(new JsonWriter(new StringWriter()));
    when(out.endObject()).thenReturn(new JsonWriter(new StringWriter()));

    HashMap<Object, Object> objectObjectMap = new HashMap<>();
    objectObjectMap.put("42", new TreeMap<>());

    // Act
    typeAdapterRuntimeTypeWrapper.write(out, objectObjectMap);

    // Assert
    verify(out, atLeast(1)).beginObject();
    verify(out, atLeast(1)).endObject();
    verify(out).name(eq("42"));
  }
}
