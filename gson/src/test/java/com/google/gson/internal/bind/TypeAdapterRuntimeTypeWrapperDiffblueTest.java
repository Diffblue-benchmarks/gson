package com.google.gson.internal.bind;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
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
import com.google.gson.GsonBuilderTestFactory;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.reflect.ReflectionHelperTestFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class TypeAdapterRuntimeTypeWrapperDiffblueTest {
  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>Then return createPublicField.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_thenReturnCreatePublicField() throws IOException, NoSuchFieldException {
    // Arrange
    ObjectTypeAdapter delegate = mock(ObjectTypeAdapter.class);
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();
    when(delegate.read(Mockito.<JsonReader>any())).thenReturn(createPublicFieldResult);
    Gson context = new Gson();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, GsonBuilderTestFactory.createType());

    // Act
    Object actualReadResult =
        typeAdapterRuntimeTypeWrapper.read(new JsonReader(new StringReader("foo")));

    // Assert
    verify(delegate).read(isA(JsonReader.class));
    assertSame(createPublicFieldResult, actualReadResult);
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite() throws IOException, NoSuchFieldException {
    // Arrange
    ObjectTypeAdapter delegate = mock(ObjectTypeAdapter.class);
    doNothing().when(delegate).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());
    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(new Gson(), delegate, null);
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    typeAdapterRuntimeTypeWrapper.write(out, ReflectionHelperTestFactory.createPublicField());

    // Assert
    verify(delegate).write(isA(JsonWriter.class), isA(Object.class));
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite2() throws IOException {
    // Arrange
    ObjectTypeAdapter delegate = mock(ObjectTypeAdapter.class);
    doNothing().when(delegate).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());
    Gson context = new Gson();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, GsonBuilderTestFactory.createType());
    JsonWriter out = new JsonWriter(new StringWriter());
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();

    // Act
    typeAdapterRuntimeTypeWrapper.write(
        out,
        new CollectionTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>())));

    // Assert
    verify(delegate).write(isA(JsonWriter.class), isA(Object.class));
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite3() throws IOException {
    // Arrange
    ObjectTypeAdapter delegate = mock(ObjectTypeAdapter.class);
    doNothing().when(delegate).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());
    Gson context = new Gson();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, GsonBuilderTestFactory.createType());
    JsonWriter out = new JsonWriter(new StringWriter());
    Gson context2 = new Gson();
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> componentTypeAdapter =
        new TreeTypeAdapter<>(
            serializer, deserializer, gson, typeToken, mock(TypeAdapterFactory.class));
    Class<Object> componentType = Object.class;

    ArrayTypeAdapter<Object> arrayTypeAdapter =
        new ArrayTypeAdapter<>(context2, componentTypeAdapter, componentType);

    // Act
    typeAdapterRuntimeTypeWrapper.write(out, arrayTypeAdapter);

    // Assert
    verify(delegate).write(isA(JsonWriter.class), isA(Object.class));
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite4() throws IOException {
    // Arrange
    ObjectTypeAdapter delegate = mock(ObjectTypeAdapter.class);
    doNothing().when(delegate).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());
    Gson context = new Gson();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, GsonBuilderTestFactory.createType());
    JsonWriter out = new JsonWriter(new StringWriter());
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();

    // Act
    typeAdapterRuntimeTypeWrapper.write(
        out,
        new JsonAdapterAnnotationTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>())));

    // Assert
    verify(delegate).write(isA(JsonWriter.class), isA(Object.class));
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite5() throws IOException {
    // Arrange
    ObjectTypeAdapter delegate = mock(ObjectTypeAdapter.class);
    doNothing().when(delegate).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());
    Gson context = new Gson();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, GsonBuilderTestFactory.createType());
    JsonWriter out = new JsonWriter(new StringWriter());
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();

    // Act
    typeAdapterRuntimeTypeWrapper.write(
        out,
        new MapTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>()), true));

    // Assert
    verify(delegate).write(isA(JsonWriter.class), isA(Object.class));
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@link ObjectTypeAdapter} {@link ObjectTypeAdapter#write(JsonWriter, Object)} does
   *       nothing.
   *   <li>When {@code null}.
   *   <li>Then calls {@link ObjectTypeAdapter#write(JsonWriter, Object)}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_givenObjectTypeAdapterWriteDoesNothing_whenNull_thenCallsWrite()
      throws IOException {
    // Arrange
    ObjectTypeAdapter delegate = mock(ObjectTypeAdapter.class);
    doNothing().when(delegate).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());
    Gson context = new Gson();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, GsonBuilderTestFactory.createType());

    // Act
    typeAdapterRuntimeTypeWrapper.write(new JsonWriter(new StringWriter()), null);

    // Assert
    verify(delegate).write(isA(JsonWriter.class), (Object) isNull());
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Then {@link JsonTreeWriter} (default constructor) {@link JsonPrimitive}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_thenJsonTreeWriterJsonPrimitive() throws IOException {
    // Arrange
    Gson context = new Gson();
    ObjectTypeAdapter delegate = mock(ObjectTypeAdapter.class);
    Class<Object> type = Object.class;

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, type);
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    typeAdapterRuntimeTypeWrapper.write(out, 42);

    // Assert
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonPrimitive);
    assertEquals("42", getResult.getAsString());
    assertEquals('4', getResult.getAsCharacter());
    assertEquals(42, getResult.getAsInt());
    assertEquals(42.0d, getResult.getAsDouble(), 0.0);
    assertEquals(42.0f, getResult.getAsFloat(), 0.0f);
    assertEquals(42L, getResult.getAsLong());
    assertEquals(42L, getResult.getAsNumber().longValue());
    assertEquals((short) 42, getResult.getAsShort());
    assertFalse(getResult.getAsBoolean());
    assertFalse(getResult.isJsonNull());
    assertFalse(((JsonPrimitive) getResult).isBoolean());
    assertFalse(((JsonPrimitive) getResult).isString());
    assertTrue(getResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) getResult).isNumber());
    assertEquals(new BigDecimal("42"), getResult.getAsBigDecimal());
    assertEquals('*', getResult.getAsByte());
    assertSame(getResult, getResult.getAsJsonPrimitive());
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>When createPublicField.
   *   <li>Then calls {@link ObjectTypeAdapter#write(JsonWriter, Object)}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_whenCreatePublicField_thenCallsWrite()
      throws IOException, NoSuchFieldException {
    // Arrange
    ObjectTypeAdapter delegate = mock(ObjectTypeAdapter.class);
    doNothing().when(delegate).write(Mockito.<JsonWriter>any(), Mockito.<Object>any());
    Gson context = new Gson();

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, GsonBuilderTestFactory.createType());
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    typeAdapterRuntimeTypeWrapper.write(out, ReflectionHelperTestFactory.createPublicField());

    // Assert
    verify(delegate).write(isA(JsonWriter.class), isA(Object.class));
  }
}
