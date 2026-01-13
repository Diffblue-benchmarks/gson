package com.google.gson.internal.bind;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.Gson;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.Strictness;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.internal.util.reflection.GenericMetadataSupport.TypeVarBoundedType;

public class TypeAdapterRuntimeTypeWrapperDiffblueTest {
  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead() throws IOException {
    // Arrange
    Gson context = new Gson();
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            null, null, gson, typeToken, DefaultDateTypeAdapter.DEFAULT_STYLE_FACTORY);

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));
    JsonTreeReader in = new JsonTreeReader(JsonNull.INSTANCE);

    // Act
    Object actualReadResult = typeAdapterRuntimeTypeWrapper.read(in);

    // Assert
    assertNull(actualReadResult);
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead2() throws IOException {
    // Arrange
    Gson context = new Gson();
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(null, null, gson, typeToken, TypeAdapters.ATOMIC_BOOLEAN_FACTORY);

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));
    JsonTreeReader in = new JsonTreeReader(JsonNull.INSTANCE);

    // Act
    Object actualReadResult = typeAdapterRuntimeTypeWrapper.read(in);

    // Assert
    assertNull(actualReadResult);
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead3() throws IOException {
    // Arrange
    Gson context = new Gson();
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(null, null, gson, typeToken, TypeAdapters.BOOLEAN_FACTORY);

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));
    JsonTreeReader in = new JsonTreeReader(JsonNull.INSTANCE);

    // Act
    Object actualReadResult = typeAdapterRuntimeTypeWrapper.read(in);

    // Assert
    assertNull(actualReadResult);
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code false}.
   *   <li>When {@link JsonObject} (default constructor) addProperty {@code Property} and {@code
   *       false}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_givenFalse_whenJsonObjectAddPropertyPropertyAndFalse() throws IOException {
    // Arrange
    Gson context = new Gson();
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(null, null, gson, typeToken, ArrayTypeAdapter.FACTORY);

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));

    JsonObject element = new JsonObject();
    element.addProperty("Property", false);
    JsonTreeReader in = new JsonTreeReader(element);

    // Act
    typeAdapterRuntimeTypeWrapper.read(in);

    // Assert
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@link JsonNull#INSTANCE}.
   *   <li>When {@link JsonObject} (default constructor) add {@code Property} and {@link
   *       JsonNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_givenInstance_whenJsonObjectAddPropertyAndInstance() throws IOException {
    // Arrange
    Gson context = new Gson();
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(null, null, gson, typeToken, ArrayTypeAdapter.FACTORY);

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));

    JsonObject element = new JsonObject();
    element.add("Property", JsonNull.INSTANCE);
    JsonTreeReader in = new JsonTreeReader(element);

    // Act
    typeAdapterRuntimeTypeWrapper.read(in);

    // Assert
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>Then return {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_givenLenient_thenReturnFoo() throws IOException {
    // Arrange
    Gson context = new Gson();
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            null,
            null,
            gson,
            typeToken,
            new CollectionTypeAdapterFactory(
                new ConstructorConstructor(instanceCreators, true, new ArrayList<>())));

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));

    JsonReader in = new JsonReader(new StringReader("foo"));
    in.setStrictness(Strictness.LENIENT);

    // Act
    Object actualReadResult = typeAdapterRuntimeTypeWrapper.read(in);

    // Assert
    assertEquals("foo", actualReadResult);
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_givenLenient_whenStringReaderWithFalseToString_thenReturnFalse()
      throws IOException {
    // Arrange
    Gson context = new Gson();
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            null,
            null,
            gson,
            typeToken,
            new CollectionTypeAdapterFactory(
                new ConstructorConstructor(instanceCreators, true, new ArrayList<>())));

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));
    StringReader in = new StringReader(Boolean.FALSE.toString());

    JsonReader in2 = new JsonReader(in);
    in2.setStrictness(Strictness.LENIENT);

    // Act
    Object actualReadResult = typeAdapterRuntimeTypeWrapper.read(in2);

    // Assert
    assertFalse((Boolean) actualReadResult);
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code STRICT}.
   *   <li>When {@link JsonReader#JsonReader(Reader)} with in is {@link
   *       StringReader#StringReader(String)} Strictness is {@code STRICT}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_givenStrict_whenJsonReaderWithInIsStringReaderStrictnessIsStrict()
      throws IOException {
    // Arrange
    Gson context = new Gson();
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            null,
            null,
            gson,
            typeToken,
            new CollectionTypeAdapterFactory(
                new ConstructorConstructor(instanceCreators, true, new ArrayList<>())));

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));
    StringReader in = new StringReader(Boolean.FALSE.toString());

    JsonReader in2 = new JsonReader(in);
    in2.setStrictness(Strictness.STRICT);

    // Act
    Object actualReadResult = typeAdapterRuntimeTypeWrapper.read(in2);

    // Assert
    assertFalse((Boolean) actualReadResult);
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code type must not be null}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_givenTypeMustNotBeNull() throws IOException {
    // Arrange
    Gson context = new Gson();
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(null, null, gson, typeToken, ArrayTypeAdapter.FACTORY);

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));

    JsonObject element = new JsonObject();
    element.addProperty("Property", "type must not be null");
    JsonTreeReader in = new JsonTreeReader(element);

    // Act
    typeAdapterRuntimeTypeWrapper.read(in);

    // Assert
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given valueOf one.
   *   <li>When {@link JsonObject} (default constructor) addProperty {@code Property} and valueOf
   *       one.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_givenValueOfOne_whenJsonObjectAddPropertyPropertyAndValueOfOne()
      throws IOException {
    // Arrange
    Gson context = new Gson();
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(null, null, gson, typeToken, ArrayTypeAdapter.FACTORY);

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));

    JsonObject element = new JsonObject();
    element.addProperty("Property", Integer.valueOf(1));
    JsonTreeReader in = new JsonTreeReader(element);

    // Act
    typeAdapterRuntimeTypeWrapper.read(in);

    // Assert
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>Then not {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonObject} (default constructor) hasNext.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_thenNotJsonTreeReaderWithElementIsJsonObjectHasNext() throws IOException {
    // Arrange
    Gson context = new Gson();
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(null, null, gson, typeToken, ArrayTypeAdapter.FACTORY);

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));
    JsonTreeReader in = new JsonTreeReader(new JsonObject());

    // Act
    typeAdapterRuntimeTypeWrapper.read(in);

    // Assert
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>Then return {@link Map}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_thenReturnMap() throws IOException {
    // Arrange
    Gson context = new Gson();
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            null,
            null,
            gson,
            typeToken,
            new CollectionTypeAdapterFactory(
                new ConstructorConstructor(instanceCreators, true, new ArrayList<>())));

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));
    JsonTreeReader in = new JsonTreeReader(new JsonObject());

    // Act
    Object actualReadResult = typeAdapterRuntimeTypeWrapper.read(in);

    // Assert
    assertTrue(actualReadResult instanceof Map);
    assertFalse(in.hasNext());
    assertTrue(((Map<Object, Object>) actualReadResult).isEmpty());
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonArray#JsonArray(int)} with capacity is three.
   *   <li>Then return {@code Object[]}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_whenJsonArrayWithCapacityIsThree_thenReturnObject() throws IOException {
    // Arrange
    Gson context = new Gson();
    Gson context2 = new Gson();
    TreeTypeAdapter<Object> componentTypeAdapter =
        new TreeTypeAdapter<>(null, null, new Gson(), null, ArrayTypeAdapter.FACTORY);
    Class<Object> componentType = Object.class;

    ArrayTypeAdapter<Object> delegate =
        new ArrayTypeAdapter<>(context2, componentTypeAdapter, componentType);

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));
    JsonTreeReader in = new JsonTreeReader(new JsonArray(3));

    // Act
    Object actualReadResult = typeAdapterRuntimeTypeWrapper.read(in);

    // Assert
    assertTrue(actualReadResult instanceof Object[]);
    assertEquals(0, ((Object[]) actualReadResult).length);
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_whenJsonTreeReaderWithElementIsInstance_thenReturnNull() throws IOException {
    // Arrange
    Gson context = new Gson();
    Gson context2 = new Gson();
    TreeTypeAdapter<Object> componentTypeAdapter =
        new TreeTypeAdapter<>(null, null, new Gson(), null, ArrayTypeAdapter.FACTORY);
    Class<Object> componentType = Object.class;

    ArrayTypeAdapter<Object> delegate =
        new ArrayTypeAdapter<>(context2, componentTypeAdapter, componentType);

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));
    JsonTreeReader in = new JsonTreeReader(JsonNull.INSTANCE);

    // Act
    Object actualReadResult = typeAdapterRuntimeTypeWrapper.read(in);

    // Assert
    assertNull(actualReadResult);
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_whenJsonTreeReaderWithElementIsInstance_thenReturnNull2()
      throws IOException {
    // Arrange
    Gson context = new Gson();
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(null, null, gson, typeToken, ArrayTypeAdapter.FACTORY);

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));
    JsonTreeReader in = new JsonTreeReader(JsonNull.INSTANCE);

    // Act
    Object actualReadResult = typeAdapterRuntimeTypeWrapper.read(in);

    // Assert
    assertNull(actualReadResult);
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_whenJsonTreeReaderWithElementIsInstance_thenReturnNull3()
      throws IOException {
    // Arrange
    Gson context = new Gson();
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            null,
            null,
            gson,
            typeToken,
            new CollectionTypeAdapterFactory(
                new ConstructorConstructor(instanceCreators, true, new ArrayList<>())));

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));
    JsonTreeReader in = new JsonTreeReader(JsonNull.INSTANCE);

    // Act
    Object actualReadResult = typeAdapterRuntimeTypeWrapper.read(in);

    // Assert
    assertNull(actualReadResult);
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TypeAdapterRuntimeTypeWrapper.read(JsonReader)"})
  public void testRead_whenStringReaderWithFalseToString_thenReturnFalse() throws IOException {
    // Arrange
    Gson context = new Gson();
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            null,
            null,
            gson,
            typeToken,
            new CollectionTypeAdapterFactory(
                new ConstructorConstructor(instanceCreators, true, new ArrayList<>())));

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));
    StringReader in = new StringReader(Boolean.FALSE.toString());

    // Act
    Object actualReadResult = typeAdapterRuntimeTypeWrapper.read(new JsonReader(in));

    // Assert
    assertFalse((Boolean) actualReadResult);
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Then {@link JsonTreeWriter} (default constructor) {@link JsonNull}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_thenJsonTreeWriterJsonNull() throws IOException {
    // Arrange
    Gson context = new Gson();
    Gson context2 = new Gson();
    TreeTypeAdapter<Object> componentTypeAdapter =
        new TreeTypeAdapter<>(null, null, new Gson(), null, ArrayTypeAdapter.FACTORY);
    Class<Object> componentType = Object.class;

    ArrayTypeAdapter<Object> delegate =
        new ArrayTypeAdapter<>(context2, componentTypeAdapter, componentType);

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    typeAdapterRuntimeTypeWrapper.write(out, null);

    // Assert that nothing has changed
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonNull);
    assertFalse(getResult.isJsonObject());
    assertFalse(getResult.isJsonPrimitive());
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Then {@link JsonTreeWriter} (default constructor) {@link JsonNull}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_thenJsonTreeWriterJsonNull2() throws IOException {
    // Arrange
    Gson context = new Gson();
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(null, null, gson, typeToken, ArrayTypeAdapter.FACTORY);

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    typeAdapterRuntimeTypeWrapper.write(out, null);

    // Assert that nothing has changed
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonNull);
    assertFalse(getResult.isJsonObject());
    assertFalse(getResult.isJsonPrimitive());
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Then {@link JsonTreeWriter} (default constructor) {@link JsonNull}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_thenJsonTreeWriterJsonNull3() throws IOException {
    // Arrange
    Gson context = new Gson();
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(
            null,
            null,
            gson,
            typeToken,
            new CollectionTypeAdapterFactory(
                new ConstructorConstructor(instanceCreators, true, new ArrayList<>())));

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    typeAdapterRuntimeTypeWrapper.write(out, null);

    // Assert that nothing has changed
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonNull);
    assertFalse(getResult.isJsonObject());
    assertFalse(getResult.isJsonPrimitive());
  }

  /**
   * Test {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Then {@link JsonTreeWriter} (default constructor) {@link JsonObject}.
   * </ul>
   *
   * <p>Method under test: {@link TypeAdapterRuntimeTypeWrapper#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TypeAdapterRuntimeTypeWrapper.write(JsonWriter, Object)"})
  public void testWrite_thenJsonTreeWriterJsonObject() throws IOException {
    // Arrange
    Gson context = new Gson();
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> delegate =
        new TreeTypeAdapter<>(null, null, gson, typeToken, EnumTypeAdapter.FACTORY);

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, delegate, new TypeVarBoundedType(null));
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    typeAdapterRuntimeTypeWrapper.write(out, "Value");

    // Assert
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonObject);
    assertEquals(0, ((JsonObject) getResult).size());
    assertTrue(getResult.isJsonObject());
    assertTrue(((JsonObject) getResult).isEmpty());
    assertSame(getResult, getResult.getAsJsonObject());
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
    Class<Object> type = Object.class;

    TypeAdapterRuntimeTypeWrapper<Object> typeAdapterRuntimeTypeWrapper =
        new TypeAdapterRuntimeTypeWrapper<>(context, null, type);
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    typeAdapterRuntimeTypeWrapper.write(out, "Value");

    // Assert
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonPrimitive);
    Number asNumber = getResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("Value", getResult.getAsString());
    assertEquals("Value", asNumber.toString());
    assertEquals('V', getResult.getAsCharacter());
    assertFalse(getResult.getAsBoolean());
    assertFalse(((JsonPrimitive) getResult).isBoolean());
    assertFalse(((JsonPrimitive) getResult).isNumber());
    assertTrue(getResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) getResult).isString());
    assertSame(getResult, getResult.getAsJsonPrimitive());
  }
}
