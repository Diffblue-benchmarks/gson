package com.google.gson.internal.bind;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.Gson;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.Strictness;
import com.google.gson.ToNumberPolicy;
import com.google.gson.ToNumberStrategy;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.internal.ReflectionAccessFilterHelperFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class ObjectTypeAdapterDiffblueTest {
  /**
   * Test {@link ObjectTypeAdapter#getFactory(ToNumberStrategy)}.
   *
   * <ul>
   *   <li>Then create {@link Gson#Gson()} and {@link Object} return {@link ObjectTypeAdapter}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#getFactory(ToNumberStrategy)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapterFactory ObjectTypeAdapter.getFactory(ToNumberStrategy)"})
  public void testGetFactory_thenCreateGsonAndObjectReturnObjectTypeAdapter() {
    // Arrange and Act
    TypeAdapterFactory actualFactory = ObjectTypeAdapter.getFactory(mock(ToNumberStrategy.class));
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type);

    // Assert
    assertTrue(actualFactory.create(gson, getResult) instanceof ObjectTypeAdapter);
  }

  /**
   * Test {@link ObjectTypeAdapter#getFactory(ToNumberStrategy)}.
   *
   * <ul>
   *   <li>When {@link ToNumberPolicy#DOUBLE}.
   *   <li>Then create {@link Gson#Gson()} and {@link Object} return {@link ObjectTypeAdapter}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#getFactory(ToNumberStrategy)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapterFactory ObjectTypeAdapter.getFactory(ToNumberStrategy)"})
  public void testGetFactory_whenDouble_thenCreateGsonAndObjectReturnObjectTypeAdapter() {
    // Arrange and Act
    TypeAdapterFactory actualFactory = ObjectTypeAdapter.getFactory(ToNumberPolicy.DOUBLE);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> getResult = TypeToken.get(type);

    // Assert
    assertTrue(actualFactory.create(gson, getResult) instanceof ObjectTypeAdapter);
  }

  /**
   * Test {@link ObjectTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>Then return {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ObjectTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_thenReturnFoo() throws IOException {
    // Arrange
    ObjectTypeAdapter objectTypeAdapter =
        new ObjectTypeAdapter(new Gson(), mock(ToNumberStrategy.class));

    JsonReader in = new JsonReader(new StringReader("foo"));
    in.setStrictness(Strictness.LENIENT);

    // Act
    Object actualReadResult = objectTypeAdapter.read(in);

    // Assert
    assertEquals("foo", actualReadResult);
  }

  /**
   * Test {@link ObjectTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link JsonReader#JsonReader(Reader)} with in is {@link
   *       StringReader#StringReader(String)} Strictness is {@code LENIENT}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ObjectTypeAdapter.read(JsonReader)"})
  public void testRead_givenLenient_whenJsonReaderWithInIsStringReaderStrictnessIsLenient()
      throws IOException {
    // Arrange
    ObjectTypeAdapter objectTypeAdapter =
        new ObjectTypeAdapter(new Gson(), mock(ToNumberStrategy.class));
    StringReader in = new StringReader(Boolean.FALSE.toString());

    JsonReader in2 = new JsonReader(in);
    in2.setStrictness(Strictness.LENIENT);

    // Act
    Object actualReadResult = objectTypeAdapter.read(in2);

    // Assert
    assertFalse((Boolean) actualReadResult);
  }

  /**
   * Test {@link ObjectTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code STRICT}.
   *   <li>When {@link JsonReader#JsonReader(Reader)} with in is {@link
   *       StringReader#StringReader(String)} Strictness is {@code STRICT}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ObjectTypeAdapter.read(JsonReader)"})
  public void testRead_givenStrict_whenJsonReaderWithInIsStringReaderStrictnessIsStrict()
      throws IOException {
    // Arrange
    ObjectTypeAdapter objectTypeAdapter =
        new ObjectTypeAdapter(new Gson(), mock(ToNumberStrategy.class));
    StringReader in = new StringReader(Boolean.FALSE.toString());

    JsonReader in2 = new JsonReader(in);
    in2.setStrictness(Strictness.STRICT);

    // Act
    Object actualReadResult = objectTypeAdapter.read(in2);

    // Assert
    assertFalse((Boolean) actualReadResult);
  }

  /**
   * Test {@link ObjectTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@link ToNumberStrategy} {@link ToNumberStrategy#readNumber(JsonReader)} return
   *       valueOf one.
   *   <li>Then return intValue is one.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ObjectTypeAdapter.read(JsonReader)"})
  public void testRead_givenToNumberStrategyReadNumberReturnValueOfOne_thenReturnIntValueIsOne()
      throws IOException {
    // Arrange
    ToNumberStrategy toNumberStrategy = mock(ToNumberStrategy.class);
    Integer valueOfResult = Integer.valueOf(1);
    when(toNumberStrategy.readNumber(Mockito.<JsonReader>any())).thenReturn(valueOfResult);
    ObjectTypeAdapter objectTypeAdapter = new ObjectTypeAdapter(new Gson(), toNumberStrategy);

    // Act
    Object actualReadResult = objectTypeAdapter.read(new JsonReader(new StringReader("42")));

    // Assert
    verify(toNumberStrategy).readNumber(isA(JsonReader.class));
    assertEquals(1, ((Integer) actualReadResult).intValue());
    assertSame(valueOfResult, actualReadResult);
  }

  /**
   * Test {@link ObjectTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link JsonTreeReader#JsonTreeReader(JsonElement)} with element is {@link
   *       JsonNull#INSTANCE}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ObjectTypeAdapter.read(JsonReader)"})
  public void testRead_whenJsonTreeReaderWithElementIsInstance_thenReturnNull() throws IOException {
    // Arrange
    ObjectTypeAdapter objectTypeAdapter =
        new ObjectTypeAdapter(new Gson(), mock(ToNumberStrategy.class));
    JsonTreeReader in = new JsonTreeReader(JsonNull.INSTANCE);

    // Act
    Object actualReadResult = objectTypeAdapter.read(in);

    // Assert
    assertNull(actualReadResult);
    assertFalse(in.hasNext());
  }

  /**
   * Test {@link ObjectTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ObjectTypeAdapter.read(JsonReader)"})
  public void testRead_whenStringReaderWith42_thenThrowIllegalStateException() throws IOException {
    // Arrange
    ToNumberStrategy toNumberStrategy = mock(ToNumberStrategy.class);
    when(toNumberStrategy.readNumber(Mockito.<JsonReader>any()))
        .thenThrow(new IllegalStateException());
    ObjectTypeAdapter objectTypeAdapter = new ObjectTypeAdapter(new Gson(), toNumberStrategy);

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> objectTypeAdapter.read(new JsonReader(new StringReader("42"))));
    verify(toNumberStrategy).readNumber(isA(JsonReader.class));
  }

  /**
   * Test {@link ObjectTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@link Boolean#FALSE} toString.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ObjectTypeAdapter.read(JsonReader)"})
  public void testRead_whenStringReaderWithFalseToString_thenReturnFalse() throws IOException {
    // Arrange
    ObjectTypeAdapter objectTypeAdapter =
        new ObjectTypeAdapter(new Gson(), mock(ToNumberStrategy.class));
    StringReader in = new StringReader(Boolean.FALSE.toString());

    // Act
    Object actualReadResult = objectTypeAdapter.read(new JsonReader(in));

    // Assert
    assertFalse((Boolean) actualReadResult);
  }

  /**
   * Test {@link ObjectTypeAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code FALSE}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ObjectTypeAdapter.read(JsonReader)"})
  public void testRead_whenStringReaderWithFalse_thenReturnFalse() throws IOException {
    // Arrange
    ObjectTypeAdapter objectTypeAdapter =
        new ObjectTypeAdapter(new Gson(), mock(ToNumberStrategy.class));

    // Act
    Object actualReadResult = objectTypeAdapter.read(new JsonReader(new StringReader("FALSE")));

    // Assert
    assertFalse((Boolean) actualReadResult);
  }

  /**
   * Test {@link ObjectTypeAdapter#write(JsonWriter, Object)}.
   *
   * <p>Method under test: {@link ObjectTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ObjectTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite() throws IOException {
    // Arrange
    ObjectTypeAdapter objectTypeAdapter =
        new ObjectTypeAdapter(new Gson(), mock(ToNumberStrategy.class));
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    objectTypeAdapter.write(out, new HashMap<>());

    // Assert
    assertEquals("{}", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link ObjectTypeAdapter#write(JsonWriter, Object)}.
   *
   * <p>Method under test: {@link ObjectTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ObjectTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite2() throws IOException {
    // Arrange
    ObjectTypeAdapter objectTypeAdapter =
        new ObjectTypeAdapter(new Gson(), mock(ToNumberStrategy.class));
    JsonWriter out = new JsonWriter(new StringWriter());
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();

    // Act
    objectTypeAdapter.write(
        out,
        new CollectionTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>())));

    // Assert
    assertEquals(
        "{\"constructorConstructor\":{\"instanceCreators\":{},\"useJdkUnsafe\":true,\"reflectionFilters\":[]}}",
        out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 5, 5, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link ObjectTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()} Strictness is {@code LENIENT}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ObjectTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_givenLenient_whenJsonWriterWithOutIsStringWriterStrictnessIsLenient()
      throws IOException {
    // Arrange
    ObjectTypeAdapter objectTypeAdapter =
        new ObjectTypeAdapter(new Gson(), mock(ToNumberStrategy.class));

    JsonWriter out = new JsonWriter(new StringWriter());
    out.setStrictness(Strictness.LENIENT);
    ObjectTypeAdapter objectTypeAdapter2 =
        new ObjectTypeAdapter(new Gson(), mock(ToNumberStrategy.class));

    // Act
    objectTypeAdapter.write(out, objectTypeAdapter2);

    // Assert
    assertArrayEquals(
        new int[] {
          7, 5, 5, 5, 5, 5, 5, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link ObjectTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@code true}.
   *   <li>Then {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()} Out toString is {@code "Value"}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ObjectTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_givenTrue_thenJsonWriterWithOutIsStringWriterOutToStringIsValue()
      throws IOException {
    // Arrange
    ObjectTypeAdapter objectTypeAdapter =
        new ObjectTypeAdapter(new Gson(), mock(ToNumberStrategy.class));

    JsonWriter out = new JsonWriter(new StringWriter());
    out.setHtmlSafe(true);

    // Act
    objectTypeAdapter.write(out, "Value");

    // Assert
    assertEquals("\"Value\"", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link ObjectTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Then {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()} Out toString is {@code {"Key":{"testField":null}}}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ObjectTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_thenJsonWriterWithOutIsStringWriterOutToStringIsKeyTestFieldNull()
      throws IOException {
    // Arrange
    ObjectTypeAdapter objectTypeAdapter =
        new ObjectTypeAdapter(new Gson(), mock(ToNumberStrategy.class));
    JsonWriter out = new JsonWriter(new StringWriter());

    HashMap<Object, Object> objectObjectMap = new HashMap<>();
    objectObjectMap.put("Key", ReflectionAccessFilterHelperFactory.createTestObject());

    // Act
    objectTypeAdapter.write(out, objectObjectMap);

    // Assert
    assertEquals("{\"Key\":{\"testField\":null}}", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link ObjectTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then {@link JsonTreeWriter} (default constructor) AsNumber {@link LazilyParsedNumber}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ObjectTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_when42_thenJsonTreeWriterAsNumberLazilyParsedNumber() throws IOException {
    // Arrange
    ObjectTypeAdapter objectTypeAdapter =
        new ObjectTypeAdapter(new Gson(), mock(ToNumberStrategy.class));
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    objectTypeAdapter.write(out, "42");

    // Assert
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonPrimitive);
    Number asNumber = getResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("42", asNumber.toString());
    assertFalse(((JsonPrimitive) getResult).isNumber());
    assertTrue(((JsonPrimitive) getResult).isString());
  }

  /**
   * Test {@link ObjectTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>When createTestObject.
   *   <li>Then {@link JsonTreeWriter} (default constructor) {@link JsonObject}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ObjectTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_whenCreateTestObject_thenJsonTreeWriterJsonObject() throws IOException {
    // Arrange
    ObjectTypeAdapter objectTypeAdapter =
        new ObjectTypeAdapter(new Gson(), mock(ToNumberStrategy.class));
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    objectTypeAdapter.write(out, ReflectionAccessFilterHelperFactory.createTestObject());

    // Assert
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonObject);
    assertEquals(1, ((JsonObject) getResult).size());
    assertFalse(((JsonObject) getResult).isEmpty());
    assertTrue(getResult.isJsonObject());
    assertSame(getResult, getResult.getAsJsonObject());
  }

  /**
   * Test {@link ObjectTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>When forty-two.
   *   <li>Then {@link JsonTreeWriter} (default constructor) AsNumber longValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ObjectTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_whenFortyTwo_thenJsonTreeWriterAsNumberLongValueIsFortyTwo()
      throws IOException {
    // Arrange
    ObjectTypeAdapter objectTypeAdapter =
        new ObjectTypeAdapter(new Gson(), mock(ToNumberStrategy.class));
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    objectTypeAdapter.write(out, 42);

    // Assert
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonPrimitive);
    assertEquals(42L, getResult.getAsNumber().longValue());
    assertFalse(((JsonPrimitive) getResult).isString());
    assertTrue(((JsonPrimitive) getResult).isNumber());
  }

  /**
   * Test {@link ObjectTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>When {@link JsonTreeWriter} (default constructor).
   *   <li>Then {@link JsonTreeWriter} (default constructor) {@link JsonNull}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ObjectTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_whenJsonTreeWriter_thenJsonTreeWriterJsonNull() throws IOException {
    // Arrange
    ObjectTypeAdapter objectTypeAdapter =
        new ObjectTypeAdapter(new Gson(), mock(ToNumberStrategy.class));
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    objectTypeAdapter.write(out, null);

    // Assert that nothing has changed
    assertTrue(out.get() instanceof JsonNull);
    assertArrayEquals(
        new int[] {
          6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link ObjectTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()} Out toString is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ObjectTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_whenNull_thenJsonWriterWithOutIsStringWriterOutToStringIsNull()
      throws IOException {
    // Arrange
    ObjectTypeAdapter objectTypeAdapter =
        new ObjectTypeAdapter(new Gson(), mock(ToNumberStrategy.class));
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    objectTypeAdapter.write(out, null);

    // Assert
    assertEquals("null", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link ObjectTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>When {@link ObjectTypeAdapter#ObjectTypeAdapter(Gson, ToNumberStrategy)} with gson is
   *       {@link Gson#Gson()} and {@link ToNumberStrategy}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ObjectTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_whenObjectTypeAdapterWithGsonIsGsonAndToNumberStrategy()
      throws IOException {
    // Arrange
    ObjectTypeAdapter objectTypeAdapter =
        new ObjectTypeAdapter(new Gson(), mock(ToNumberStrategy.class));
    JsonWriter out = new JsonWriter(new StringWriter());
    ObjectTypeAdapter objectTypeAdapter2 =
        new ObjectTypeAdapter(new Gson(), mock(ToNumberStrategy.class));

    // Act
    objectTypeAdapter.write(out, objectTypeAdapter2);

    // Assert
    assertArrayEquals(
        new int[] {
          7, 5, 5, 5, 5, 5, 5, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test {@link ObjectTypeAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>When {@code Value}.
   *   <li>Then {@link JsonWriter#JsonWriter(Writer)} with out is {@link
   *       StringWriter#StringWriter()} Out toString is {@code "Value"}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ObjectTypeAdapter.write(JsonWriter, Object)"})
  public void testWrite_whenValue_thenJsonWriterWithOutIsStringWriterOutToStringIsValue()
      throws IOException {
    // Arrange
    ObjectTypeAdapter objectTypeAdapter =
        new ObjectTypeAdapter(new Gson(), mock(ToNumberStrategy.class));
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    objectTypeAdapter.write(out, "Value");

    // Assert
    assertEquals("\"Value\"", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }
}
