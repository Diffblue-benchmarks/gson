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
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import com.google.gson.Strictness;
import com.google.gson.TypeAdapterFactory;
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
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class TreeTypeAdapterDiffblueTest {
  /**
   * Test {@link TreeTypeAdapter#TreeTypeAdapter(JsonSerializer, JsonDeserializer, Gson, TypeToken,
   * TypeAdapterFactory)}.
   *
   * <p>Method under test: {@link TreeTypeAdapter#TreeTypeAdapter(JsonSerializer, JsonDeserializer,
   * Gson, TypeToken, TypeAdapterFactory)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TreeTypeAdapter.<init>(JsonSerializer, JsonDeserializer, Gson, TypeToken,"
        + " TypeAdapterFactory)"
  })
  public void testNewTreeTypeAdapter() {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    // Act
    TreeTypeAdapter<Object> actualTreeTypeAdapter =
        new TreeTypeAdapter<>(null, null, gson, typeToken, ArrayTypeAdapter.FACTORY);

    // Assert
    Gson gson2 = actualTreeTypeAdapter.gson;
    assertFalse(gson2.serializeNulls());
    assertTrue(gson2.htmlSafe());
  }

  /**
   * Test {@link TreeTypeAdapter#TreeTypeAdapter(JsonSerializer, JsonDeserializer, Gson, TypeToken,
   * TypeAdapterFactory, boolean)}.
   *
   * <p>Method under test: {@link TreeTypeAdapter#TreeTypeAdapter(JsonSerializer, JsonDeserializer,
   * Gson, TypeToken, TypeAdapterFactory, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TreeTypeAdapter.<init>(JsonSerializer, JsonDeserializer, Gson, TypeToken,"
        + " TypeAdapterFactory, boolean)"
  })
  public void testNewTreeTypeAdapter2() {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    // Act
    TreeTypeAdapter<Object> actualTreeTypeAdapter =
        new TreeTypeAdapter<>(null, null, gson, typeToken, ArrayTypeAdapter.FACTORY, true);

    // Assert
    Gson gson2 = actualTreeTypeAdapter.gson;
    assertFalse(gson2.serializeNulls());
    assertTrue(gson2.htmlSafe());
  }

  /**
   * Test {@link TreeTypeAdapter#read(JsonReader)}.
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
  public void testRead() throws IOException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            null, null, gson, typeToken, DefaultDateTypeAdapter.DEFAULT_STYLE_FACTORY);
    JsonTreeReader in = new JsonTreeReader(JsonNull.INSTANCE);

    // Act
    Object actualReadResult = treeTypeAdapter.read(in);

    // Assert
    assertNull(actualReadResult);
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link TreeTypeAdapter#read(JsonReader)}.
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
  public void testRead2() throws IOException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(null, null, gson, typeToken, TypeAdapters.ATOMIC_BOOLEAN_FACTORY);
    JsonTreeReader in = new JsonTreeReader(JsonNull.INSTANCE);

    // Act
    Object actualReadResult = treeTypeAdapter.read(in);

    // Assert
    assertNull(actualReadResult);
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link TreeTypeAdapter#read(JsonReader)}.
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
  public void testRead3() throws IOException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(null, null, gson, typeToken, TypeAdapters.BOOLEAN_FACTORY);
    JsonTreeReader in = new JsonTreeReader(JsonNull.INSTANCE);

    // Act
    Object actualReadResult = treeTypeAdapter.read(in);

    // Assert
    assertNull(actualReadResult);
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link TreeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>Then return {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_thenReturnFoo() throws IOException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            null,
            null,
            gson,
            typeToken,
            new CollectionTypeAdapterFactory(
                new ConstructorConstructor(instanceCreators, true, new ArrayList<>())));

    JsonReader in = new JsonReader(new StringReader("foo"));
    in.setStrictness(Strictness.LENIENT);

    // Act
    Object actualReadResult = treeTypeAdapter.read(in);

    // Assert
    assertEquals("foo", actualReadResult);
  }

  /**
   * Test {@link TreeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
  public void testRead_whenJsonTreeReaderWithElementIsInstance_thenReturnNull() throws IOException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(null, null, gson, typeToken, ArrayTypeAdapter.FACTORY);
    JsonTreeReader in = new JsonTreeReader(JsonNull.INSTANCE);

    // Act
    Object actualReadResult = treeTypeAdapter.read(in);

    // Assert
    assertNull(actualReadResult);
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link TreeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
  public void testRead_whenJsonTreeReaderWithElementIsInstance_thenReturnNull2()
      throws IOException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            null,
            null,
            gson,
            typeToken,
            new CollectionTypeAdapterFactory(
                new ConstructorConstructor(instanceCreators, true, new ArrayList<>())));
    JsonTreeReader in = new JsonTreeReader(JsonNull.INSTANCE);

    // Act
    Object actualReadResult = treeTypeAdapter.read(in);

    // Assert
    assertNull(actualReadResult);
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link TreeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then return doubleValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
  public void testRead_whenStringReaderWith42_thenReturnDoubleValueIsFortyTwo() throws IOException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            null,
            null,
            gson,
            typeToken,
            new CollectionTypeAdapterFactory(
                new ConstructorConstructor(instanceCreators, true, new ArrayList<>())));

    // Act
    Object actualReadResult = treeTypeAdapter.read(new JsonReader(new StringReader("42")));

    // Assert
    assertEquals(42.0d, ((Double) actualReadResult).doubleValue(), 0.0);
  }

  /**
   * Test {@link TreeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
  public void testRead_whenStringReaderWithFalseToString_thenReturnFalse() throws IOException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            null,
            null,
            gson,
            typeToken,
            new CollectionTypeAdapterFactory(
                new ConstructorConstructor(instanceCreators, true, new ArrayList<>())));
    StringReader in = new StringReader(Boolean.FALSE.toString());

    // Act
    Object actualReadResult = treeTypeAdapter.read(new JsonReader(in));

    // Assert
    assertFalse((Boolean) actualReadResult);
  }

  /**
   * Test {@link TreeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code FALSE}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
  public void testRead_whenStringReaderWithFalse_thenReturnFalse() throws IOException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            null,
            null,
            gson,
            typeToken,
            new CollectionTypeAdapterFactory(
                new ConstructorConstructor(instanceCreators, true, new ArrayList<>())));

    // Act
    Object actualReadResult = treeTypeAdapter.read(new JsonReader(new StringReader("FALSE")));

    // Assert
    assertFalse((Boolean) actualReadResult);
  }

  /**
   * Test {@link TreeTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code skipPast must not be null}.
   *   <li>Then return {@code skipPast}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TreeTypeAdapter.read(JsonReader)"})
  public void testRead_whenStringReaderWithSkipPastMustNotBeNull_thenReturnSkipPast()
      throws IOException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            null,
            null,
            gson,
            typeToken,
            new CollectionTypeAdapterFactory(
                new ConstructorConstructor(instanceCreators, true, new ArrayList<>())));

    JsonReader in = new JsonReader(new StringReader("skipPast must not be null"));
    in.setStrictness(Strictness.LENIENT);

    // Act
    Object actualReadResult = treeTypeAdapter.read(in);

    // Assert
    assertEquals("skipPast", actualReadResult);
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Then {@link JsonTreeWriter} (default constructor) {@link JsonNull}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_thenJsonTreeWriterJsonNull() throws IOException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            null, null, gson, typeToken, DefaultDateTypeAdapter.DEFAULT_STYLE_FACTORY);
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    treeTypeAdapter.write(out, null);

    // Assert that nothing has changed
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonNull);
    assertFalse(getResult.isJsonObject());
    assertFalse(getResult.isJsonPrimitive());
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Then {@link JsonTreeWriter} (default constructor) {@link JsonNull}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_thenJsonTreeWriterJsonNull2() throws IOException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            null,
            null,
            gson,
            typeToken,
            new JsonAdapterAnnotationTypeAdapterFactory(
                new ConstructorConstructor(instanceCreators, true, new ArrayList<>())));
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    treeTypeAdapter.write(out, null);

    // Assert that nothing has changed
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonNull);
    assertFalse(getResult.isJsonObject());
    assertFalse(getResult.isJsonPrimitive());
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Then {@link JsonTreeWriter} (default constructor) {@link JsonObject}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_thenJsonTreeWriterJsonObject() throws IOException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(null, null, gson, typeToken, EnumTypeAdapter.FACTORY);
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    treeTypeAdapter.write(out, "Value");

    // Assert
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonObject);
    assertEquals(0, ((JsonObject) getResult).size());
    assertTrue(getResult.isJsonObject());
    assertTrue(((JsonObject) getResult).isEmpty());
    assertSame(getResult, getResult.getAsJsonObject());
  }

  /**
   * Test {@link TreeTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Then {@link JsonTreeWriter} (default constructor) {@link JsonPrimitive}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TreeTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_thenJsonTreeWriterJsonPrimitive() throws IOException {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            null,
            null,
            gson,
            typeToken,
            new JsonAdapterAnnotationTypeAdapterFactory(
                new ConstructorConstructor(instanceCreators, true, new ArrayList<>())));
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    treeTypeAdapter.write(out, "Value");

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

  /**
   * Test {@link TreeTypeAdapter#getSerializationDelegate()}.
   *
   * <p>Method under test: {@link TreeTypeAdapter#getSerializationDelegate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"com.google.gson.TypeAdapter TreeTypeAdapter.getSerializationDelegate()"})
  public void testGetSerializationDelegate() {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(null, null, gson, typeToken, ArrayTypeAdapter.FACTORY);

    // Act and Assert
    assertEquals("{}", treeTypeAdapter.getSerializationDelegate().toJson("Value"));
  }

  /**
   * Test {@link TreeTypeAdapter#getSerializationDelegate()}.
   *
   * <p>Method under test: {@link TreeTypeAdapter#getSerializationDelegate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"com.google.gson.TypeAdapter TreeTypeAdapter.getSerializationDelegate()"})
  public void testGetSerializationDelegate2() {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            null, null, gson, typeToken, DefaultDateTypeAdapter.DEFAULT_STYLE_FACTORY);

    // Act and Assert
    assertEquals("{}", treeTypeAdapter.getSerializationDelegate().toJson("Value"));
  }

  /**
   * Test {@link TreeTypeAdapter#getSerializationDelegate()}.
   *
   * <p>Method under test: {@link TreeTypeAdapter#getSerializationDelegate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"com.google.gson.TypeAdapter TreeTypeAdapter.getSerializationDelegate()"})
  public void testGetSerializationDelegate3() {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(null, null, gson, typeToken, TypeAdapters.ATOMIC_BOOLEAN_FACTORY);

    // Act and Assert
    assertEquals("{}", treeTypeAdapter.getSerializationDelegate().toJson("Value"));
  }

  /**
   * Test {@link TreeTypeAdapter#getSerializationDelegate()}.
   *
   * <p>Method under test: {@link TreeTypeAdapter#getSerializationDelegate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"com.google.gson.TypeAdapter TreeTypeAdapter.getSerializationDelegate()"})
  public void testGetSerializationDelegate4() {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(null, null, gson, typeToken, TypeAdapters.BOOLEAN_FACTORY);

    // Act and Assert
    assertEquals("{}", treeTypeAdapter.getSerializationDelegate().toJson("Value"));
  }

  /**
   * Test {@link TreeTypeAdapter#getSerializationDelegate()}.
   *
   * <ul>
   *   <li>Then return {@link ObjectTypeAdapter}.
   * </ul>
   *
   * <p>Method under test: {@link TreeTypeAdapter#getSerializationDelegate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"com.google.gson.TypeAdapter TreeTypeAdapter.getSerializationDelegate()"})
  public void testGetSerializationDelegate_thenReturnObjectTypeAdapter() {
    // Arrange
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> typeToken = TypeToken.get(type);
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();

    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            null,
            null,
            gson,
            typeToken,
            new CollectionTypeAdapterFactory(
                new ConstructorConstructor(instanceCreators, true, new ArrayList<>())));

    // Act and Assert
    assertTrue(treeTypeAdapter.getSerializationDelegate() instanceof ObjectTypeAdapter);
  }
}
