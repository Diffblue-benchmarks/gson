package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.gson.stream.JsonReader;
import java.io.IOException;
import org.junit.Test;

public class ClassWithAdapterDiffblueTest {
  /** Method under test: {@link ClassWithAdapter.Adapter#read(JsonReader)} */
  @Test
  public void testAdapterRead() throws IOException {
    // Arrange
    ClassWithAdapter.Adapter adapter = new ClassWithAdapter.Adapter();
    JsonReader in = mock(JsonReader.class);
    when(in.nextName()).thenReturn("Next Name");
    doNothing().when(in).beginObject();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> adapter.read(in));
    verify(in).beginObject();
    verify(in).nextName();
  }

  /** Method under test: {@link ClassWithAdapter.Adapter#read(JsonReader)} */
  @Test
  public void testAdapterRead2() throws IOException {
    // Arrange
    ClassWithAdapter.Adapter adapter = new ClassWithAdapter.Adapter();
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

  /** Method under test: {@link ClassWithAdapter.Adapter#read(JsonReader)} */
  @Test
  public void testAdapterRead3() throws IOException {
    // Arrange
    ClassWithAdapter.Adapter adapter = new ClassWithAdapter.Adapter();
    JsonReader in = mock(JsonReader.class);
    when(in.nextInt()).thenThrow(new IOException("custom"));
    when(in.nextName()).thenReturn("custom");
    doNothing().when(in).beginObject();

    // Act and Assert
    assertThrows(IOException.class, () -> adapter.read(in));
    verify(in).beginObject();
    verify(in).nextInt();
    verify(in).nextName();
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link ClassWithAdapter#ClassWithAdapter(int)}
   *   <li>{@link ClassWithAdapter#toString()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    ClassWithAdapter actualClassWithAdapter = new ClassWithAdapter(1);

    // Assert
    assertEquals("ClassWithAdapter[1]", actualClassWithAdapter.toString());
    assertEquals(1, actualClassWithAdapter.i.intValue());
  }
}
