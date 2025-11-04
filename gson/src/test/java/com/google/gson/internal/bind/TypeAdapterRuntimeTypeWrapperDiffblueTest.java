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

import com.diffblue.cover.annotations.MaintainedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
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
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.GenericMetadataSupport.TypeVarBoundedType;

public class TypeAdapterRuntimeTypeWrapperDiffblueTest {
  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@link TypeAdapter} {@link TypeAdapter#read(JsonReader)} return {@code Read}.
   *   <li>Then return {@code Read}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_givenTypeAdapterReadReturnRead_thenReturnRead() throws IOException {
    // Arrange
    TypeAdapter<Object> delegate = mock(TypeAdapter.class);
    when(delegate.read(Mockito.<JsonReader>any())).thenReturn("Read");
    Gson context = new Gson();
    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));

    // Act
    Object actualReadResult =
        typeAdapterRuntimeTypeWrapper.read(new JsonReader(new StringReader("foo")));

    // Assert
    verify(delegate).read(isA(JsonReader.class));
    assertEquals("Read", actualReadResult);
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link HashMap#HashMap()} {@code 42} is {@code 42}.
   *   <li>Then calls {@link JsonWriter#value(String)}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_given42_whenHashMap42Is42_thenCallsValue() throws IOException {
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

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()}.
   *   <li>When {@link HashMap#HashMap()} {@code 42} is {@link HashMap#HashMap()}.
   *   <li>Then calls {@link JsonWriter#name(String)}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_givenHashMap_whenHashMap42IsHashMap_thenCallsName() throws IOException {
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

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()}.
   *   <li>When {@link HashMap#HashMap()}.
   *   <li>Then calls {@link JsonWriter#beginObject()}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_givenJsonWriterWithOutIsStringWriter_whenHashMap_thenCallsBeginObject()
      throws IOException {
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

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link HashMap#HashMap()} {@code 42} is {@code null}.
   *   <li>Then calls {@link JsonWriter#nullValue()}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_givenNull_whenHashMap42IsNull_thenCallsNullValue() throws IOException {
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

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link TreeMap#TreeMap()}.
   *   <li>When {@link HashMap#HashMap()} {@code 42} is {@link TreeMap#TreeMap()}.
   *   <li>Then calls {@link JsonWriter#name(String)}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_givenTreeMap_whenHashMap42IsTreeMap_thenCallsName() throws IOException {
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

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Then calls {@link JsonWriter#value(String)}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_thenCallsValue() throws IOException {
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

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Then calls {@link TypeAdapter#write(JsonWriter, Object)}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_thenCallsWrite() throws IOException {
    // Arrange
    TypeAdapter<Object> delegate = mock(TypeAdapter.class);
    doNothing().when(delegate).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());
    Gson context = new Gson();
    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));

    // Act
    typeAdapterRuntimeTypeWrapper.write(new JsonWriter(new StringWriter()), "Value");

    // Assert
    verify(delegate).write(isA(JsonWriter.class), isA(Object.class));
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Then calls {@link TypeAdapter#write(JsonWriter, Object)}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_thenCallsWrite2() throws IOException {
    // Arrange
    TypeAdapter<Object> delegate = mock(TypeAdapter.class);
    doNothing().when(delegate).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());
    Gson context = new Gson();
    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));

    // Act
    typeAdapterRuntimeTypeWrapper.write(new JsonWriter(new StringWriter()), null);

    // Assert
    verify(delegate).write(isA(JsonWriter.class), isNull());
  }
}
