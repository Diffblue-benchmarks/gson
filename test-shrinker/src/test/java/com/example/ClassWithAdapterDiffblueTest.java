package com.example;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.example.ClassWithAdapter.Adapter;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.Strictness;
import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringWriter;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ClassWithAdapterDiffblueTest {
  /**
   * Test Adapter {@link Adapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@link IllegalArgumentException#IllegalArgumentException()}.
   * </ul>
   *
   * <p>Method under test: {@link Adapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ClassWithAdapter Adapter.read(JsonReader)"})
  public void testAdapterRead_givenIllegalArgumentException() throws IOException {
    // Arrange
    Adapter adapter = new Adapter();

    JsonReader in = mock(JsonReader.class);
    when(in.nextInt()).thenThrow(new IllegalArgumentException());
    when(in.nextName()).thenReturn("custom");
    doNothing().when(in).beginObject();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> adapter.read(in));
    verify(in).beginObject();
    verify(in).nextInt();
    verify(in).nextName();
  }

  /**
   * Test Adapter {@link Adapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given {@code Next Name}.
   *   <li>When {@link JsonReader} {@link JsonReader#nextName()} return {@code Next Name}.
   * </ul>
   *
   * <p>Method under test: {@link Adapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ClassWithAdapter Adapter.read(JsonReader)"})
  public void testAdapterRead_givenNextName_whenJsonReaderNextNameReturnNextName()
      throws IOException {
    // Arrange
    Adapter adapter = new Adapter();

    JsonReader in = mock(JsonReader.class);
    when(in.nextName()).thenReturn("Next Name");
    doNothing().when(in).beginObject();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> adapter.read(in));
    verify(in).beginObject();
    verify(in).nextName();
  }

  /**
   * Test Adapter {@link Adapter#read(JsonReader)}.
   *
   * <ul>
   *   <li>Given one.
   *   <li>When {@link JsonReader} {@link JsonReader#nextInt()} return one.
   *   <li>Then return {@link ClassWithAdapter#i} intValue is one.
   * </ul>
   *
   * <p>Method under test: {@link Adapter#read(JsonReader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ClassWithAdapter Adapter.read(JsonReader)"})
  public void testAdapterRead_givenOne_whenJsonReaderNextIntReturnOne_thenReturnIIntValueIsOne()
      throws IOException {
    // Arrange
    Adapter adapter = new Adapter();

    JsonReader in = mock(JsonReader.class);
    when(in.nextInt()).thenReturn(1);
    doNothing().when(in).endObject();
    when(in.nextName()).thenReturn("custom");
    doNothing().when(in).beginObject();

    // Act
    ClassWithAdapter actualReadResult = adapter.read(in);

    // Assert
    verify(in).beginObject();
    verify(in).endObject();
    verify(in).nextInt();
    verify(in).nextName();
    assertEquals(1, actualReadResult.i.intValue());
  }

  /**
   * Test Adapter {@link Adapter#write(JsonWriter, ClassWithAdapter)} with {@code JsonWriter},
   * {@code ClassWithAdapter}.
   *
   * <p>Method under test: {@link Adapter#write(JsonWriter, ClassWithAdapter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Adapter.write(JsonWriter, ClassWithAdapter)"})
  public void testAdapterWriteWithJsonWriterClassWithAdapter() throws IOException {
    // Arrange
    Adapter adapter = new Adapter();
    JsonWriter out = new JsonWriter(new StringWriter());

    // Act
    adapter.write(out, new ClassWithAdapter(1));

    // Assert
    assertEquals("{\"custom\":1}", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test Adapter {@link Adapter#write(JsonWriter, ClassWithAdapter)} with {@code JsonWriter},
   * {@code ClassWithAdapter}.
   *
   * <ul>
   *   <li>Given {@code LENIENT}.
   * </ul>
   *
   * <p>Method under test: {@link Adapter#write(JsonWriter, ClassWithAdapter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Adapter.write(JsonWriter, ClassWithAdapter)"})
  public void testAdapterWriteWithJsonWriterClassWithAdapter_givenLenient() throws IOException {
    // Arrange
    Adapter adapter = new Adapter();

    JsonTreeWriter out = new JsonTreeWriter();
    out.setStrictness(Strictness.LENIENT);

    // Act
    adapter.write(out, new ClassWithAdapter(1));

    // Assert
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonObject);
    assertEquals(1, ((JsonObject) getResult).size());
    assertFalse(getResult.isJsonNull());
    assertFalse(((JsonObject) getResult).isEmpty());
    assertTrue(getResult.isJsonObject());
    assertSame(getResult, getResult.getAsJsonObject());
    assertArrayEquals(
        new int[] {
          6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test Adapter {@link Adapter#write(JsonWriter, ClassWithAdapter)} with {@code JsonWriter},
   * {@code ClassWithAdapter}.
   *
   * <ul>
   *   <li>Given {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link Adapter#write(JsonWriter, ClassWithAdapter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Adapter.write(JsonWriter, ClassWithAdapter)"})
  public void testAdapterWriteWithJsonWriterClassWithAdapter_givenTrue() throws IOException {
    // Arrange
    Adapter adapter = new Adapter();

    JsonWriter out = new JsonWriter(new StringWriter());
    out.setHtmlSafe(true);

    // Act
    adapter.write(out, new ClassWithAdapter(1));

    // Assert
    assertEquals("{\"custom\":1}", out.getOut().toString());
    assertArrayEquals(
        new int[] {
          7, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test Adapter {@link Adapter#write(JsonWriter, ClassWithAdapter)} with {@code JsonWriter},
   * {@code ClassWithAdapter}.
   *
   * <ul>
   *   <li>When {@link JsonTreeWriter} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link Adapter#write(JsonWriter, ClassWithAdapter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Adapter.write(JsonWriter, ClassWithAdapter)"})
  public void testAdapterWriteWithJsonWriterClassWithAdapter_whenJsonTreeWriter()
      throws IOException {
    // Arrange
    Adapter adapter = new Adapter();
    JsonTreeWriter out = new JsonTreeWriter();

    // Act
    adapter.write(out, new ClassWithAdapter(1));

    // Assert
    JsonElement getResult = out.get();
    assertTrue(getResult instanceof JsonObject);
    assertEquals(1, ((JsonObject) getResult).size());
    assertFalse(getResult.isJsonNull());
    assertFalse(((JsonObject) getResult).isEmpty());
    assertTrue(getResult.isJsonObject());
    assertSame(getResult, getResult.getAsJsonObject());
    assertArrayEquals(
        new int[] {
          6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0
        },
        out.getStack());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ClassWithAdapter#ClassWithAdapter(int)}
   *   <li>{@link ClassWithAdapter#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ClassWithAdapter.<init>(int)",
    "java.lang.String ClassWithAdapter.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    ClassWithAdapter actualClassWithAdapter = new ClassWithAdapter(1);

    // Assert
    assertEquals("ClassWithAdapter[1]", actualClassWithAdapter.toString());
    assertEquals(1, actualClassWithAdapter.i.intValue());
  }
}
