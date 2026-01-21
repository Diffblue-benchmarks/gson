package com.google.gson.interceptors;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.Strictness;
import com.google.gson.interceptors.InterceptorFactory.InterceptorAdapter;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class InterceptorFactoryDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link InterceptorFactory}
   *   <li>{@link InterceptorFactory#isInitialized()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void InterceptorFactory.<init>()",
    "boolean InterceptorFactory.isInitialized()"
  })
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertTrue(new InterceptorFactory().isInitialized());
  }

  /**
   * Test {@link InterceptorFactory#create(Gson, TypeToken)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link InterceptorFactory#create(Gson, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"com.google.gson.TypeAdapter InterceptorFactory.create(Gson, TypeToken)"})
  public void testCreate_whenJavaLangObject_thenReturnNull() {
    // Arrange
    InterceptorFactory interceptorFactory = new InterceptorFactory();
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> type2 = TypeToken.get(type);

    // Act and Assert
    assertNull(interceptorFactory.create(gson, type2));
  }

  /**
   * Test InterceptorAdapter {@link InterceptorAdapter#read(JsonReader)}.
   *
   * <p>Method under test: {@link InterceptorAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object InterceptorAdapter.read(JsonReader)"})
  public void testInterceptorAdapterRead() throws IOException {
    // Arrange
    InterceptorAdapter<String> createInterceptorAdapterResult =
        InterceptorAdapterTestFactory.createInterceptorAdapter();
    JsonTreeReader in = new JsonTreeReader(new JsonPrimitive("Expected "));

    // Act
    String actualReadResult = createInterceptorAdapterResult.read(in);

    // Assert
    assertEquals("Expected ", actualReadResult);
    assertFalse(in.hasNext());
  }

  /**
   * Test InterceptorAdapter {@link InterceptorAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code at line}.
   *   <li>Then return {@code at}.
   * </ul>
   *
   * <p>Method under test: {@link InterceptorAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object InterceptorAdapter.read(JsonReader)"})
  public void testInterceptorAdapterRead_givenLenient_whenStringReaderWithAtLine_thenReturnAt()
      throws IOException {
    // Arrange
    InterceptorAdapter<String> createInterceptorAdapterResult =
        InterceptorAdapterTestFactory.createInterceptorAdapter();

    JsonReader in = new JsonReader(new StringReader(" at line "));
    in.setStrictness(Strictness.LENIENT);

    // Act
    String actualReadResult = createInterceptorAdapterResult.read(in);

    // Assert
    assertEquals("at", actualReadResult);
  }

  /**
   * Test InterceptorAdapter {@link InterceptorAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then return {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link InterceptorAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object InterceptorAdapter.read(JsonReader)"})
  public void testInterceptorAdapterRead_givenLenient_whenStringReaderWithFoo_thenReturnFoo()
      throws IOException {
    // Arrange
    InterceptorAdapter<String> createInterceptorAdapterResult =
        InterceptorAdapterTestFactory.createInterceptorAdapter();

    JsonReader in = new JsonReader(new StringReader("foo"));
    in.setStrictness(Strictness.LENIENT);

    // Act
    String actualReadResult = createInterceptorAdapterResult.read(in);

    // Assert
    assertEquals("foo", actualReadResult);
  }

  /**
   * Test InterceptorAdapter {@link InterceptorAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   *   <li>When {@link StringReader#StringReader(String)} with {@code See}.
   *   <li>Then return {@code See}.
   * </ul>
   *
   * <p>Method under test: {@link InterceptorAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object InterceptorAdapter.read(JsonReader)"})
  public void testInterceptorAdapterRead_givenLenient_whenStringReaderWithSee_thenReturnSee()
      throws IOException {
    // Arrange
    InterceptorAdapter<String> createInterceptorAdapterResult =
        InterceptorAdapterTestFactory.createInterceptorAdapter();

    JsonReader in = new JsonReader(new StringReader("\nSee "));
    in.setStrictness(Strictness.LENIENT);

    // Act
    String actualReadResult = createInterceptorAdapterResult.read(in);

    // Assert
    assertEquals("See", actualReadResult);
  }

  /**
   * Test InterceptorAdapter {@link InterceptorAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Then return {@code Use}.
   * </ul>
   *
   * <p>Method under test: {@link InterceptorAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object InterceptorAdapter.read(JsonReader)"})
  public void testInterceptorAdapterRead_thenReturnUse() throws IOException {
    // Arrange
    InterceptorAdapter<String> createInterceptorAdapterResult =
        InterceptorAdapterTestFactory.createInterceptorAdapter();

    JsonReader in =
        new JsonReader(
            new StringReader(
                "Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed JSON"));
    in.setStrictness(Strictness.LENIENT);

    // Act
    String actualReadResult = createInterceptorAdapterResult.read(in);

    // Assert
    assertEquals("Use", actualReadResult);
  }

  /**
   * Test InterceptorAdapter {@link InterceptorAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code 42}.
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link InterceptorAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object InterceptorAdapter.read(JsonReader)"})
  public void testInterceptorAdapterRead_whenStringReaderWith42_thenReturn42() throws IOException {
    // Arrange
    InterceptorAdapter<String> createInterceptorAdapterResult =
        InterceptorAdapterTestFactory.createInterceptorAdapter();

    // Act
    String actualReadResult =
        createInterceptorAdapterResult.read(new JsonReader(new StringReader("42")));

    // Assert
    assertEquals("42", actualReadResult);
  }

  /**
   * Test InterceptorAdapter {@link InterceptorAdapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code Expected}.
   *   <li>Then return {@code Expected}.
   * </ul>
   *
   * <p>Method under test: {@link InterceptorAdapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object InterceptorAdapter.read(JsonReader)"})
  public void testInterceptorAdapterRead_whenStringReaderWithExpected_thenReturnExpected()
      throws IOException {
    // Arrange
    InterceptorAdapter<String> createInterceptorAdapterResult =
        InterceptorAdapterTestFactory.createInterceptorAdapter();

    JsonReader in = new JsonReader(new StringReader("Expected "));
    in.setStrictness(Strictness.LENIENT);

    // Act
    String actualReadResult = createInterceptorAdapterResult.read(in);

    // Assert
    assertEquals("Expected", actualReadResult);
  }

  /**
   * Test InterceptorAdapter {@link InterceptorAdapter#write(JsonWriter, Object)}.
   *
   * <p>Method under test: {@link InterceptorAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void InterceptorAdapter.write(JsonWriter, Object)"})
  public void testInterceptorAdapterWrite() throws IOException {
    // Arrange
    InterceptorAdapter<String> createInterceptorAdapterResult =
        InterceptorAdapterTestFactory.createInterceptorAdapter();
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    createInterceptorAdapterResult.write(out, "Value");

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
   * Test InterceptorAdapter {@link InterceptorAdapter#write(JsonWriter, Object)}.
   *
   * <p>Method under test: {@link InterceptorAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void InterceptorAdapter.write(JsonWriter, Object)"})
  public void testInterceptorAdapterWrite2() throws IOException {
    // Arrange
    InterceptorAdapter<String> createInterceptorAdapterResult =
        InterceptorAdapterTestFactory.createInterceptorAdapter();
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    createInterceptorAdapterResult.write(out, "");

    // Assert
    assertEquals("\"\"", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test InterceptorAdapter {@link InterceptorAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@code false}.
   *   <li>Then {@link JsonTreeWriter} (default constructor) {@link JsonPrimitive}.
   * </ul>
   *
   * <p>Method under test: {@link InterceptorAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void InterceptorAdapter.write(JsonWriter, Object)"})
  public void testInterceptorAdapterWrite_givenFalse_thenJsonTreeWriterJsonPrimitive()
      throws IOException {
    // Arrange
    InterceptorAdapter<String> createInterceptorAdapterResult =
        InterceptorAdapterTestFactory.createInterceptorAdapter();

    JsonTreeWriter out = new JsonTreeWriter();
    out.setHtmlSafe(false);

    // Act
    createInterceptorAdapterResult.write(out, "Value");

    // Assert
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonPrimitive);
    assertTrue(getResult.getAsNumber() instanceof LazilyParsedNumber);
    assertEquals("Value", getResult.getAsString());
    assertEquals('V', getResult.getAsCharacter());
    assertFalse(getResult.getAsBoolean());
    assertFalse(getResult.isJsonNull());
    assertFalse(((JsonPrimitive) getResult).isBoolean());
    assertFalse(((JsonPrimitive) getResult).isNumber());
    assertTrue(getResult.isJsonPrimitive());
    assertTrue(((JsonPrimitive) getResult).isString());
    assertSame(getResult, getResult.getAsJsonPrimitive());
    assertArrayEquals(
        new int[] {
          6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test InterceptorAdapter {@link InterceptorAdapter#write(JsonWriter, Object)}.
   *
   * <ul>
   *   <li>Given {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link InterceptorAdapter#write(JsonWriter, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void InterceptorAdapter.write(JsonWriter, Object)"})
  public void testInterceptorAdapterWrite_givenTrue() throws IOException {
    // Arrange
    InterceptorAdapter<String> createInterceptorAdapterResult =
        InterceptorAdapterTestFactory.createInterceptorAdapter();

    JsonWriter out = new JsonWriter(new StringWriter());
    out.setHtmlSafe(true);

    // Act
    createInterceptorAdapterResult.write(out, "Value");

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
