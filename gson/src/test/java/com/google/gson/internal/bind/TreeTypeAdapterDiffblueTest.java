package com.google.gson.internal.bind;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.Excluder;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.GenericMetadataSupport;

public class TreeTypeAdapterDiffblueTest {
  /** Method under test: {@link TreeTypeAdapter#getSerializationDelegate()} */
  @Test
  public void testGetSerializationDelegate() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer,
            deserializer,
            new Gson(),
            mock(TypeToken.class),
            mock(TypeAdapterFactory.class));

    // Act and Assert
    assertSame(treeTypeAdapter, treeTypeAdapter.getSerializationDelegate());
  }

  /** Method under test: {@link TreeTypeAdapter#getSerializationDelegate()} */
  @Test
  public void testGetSerializationDelegate2() {
    // Arrange
    TypeToken<Object> typeToken = mock(TypeToken.class);
    Class<Object> forNameResult = Object.class;
    Mockito.<Class<? super Object>>when(typeToken.getRawType()).thenReturn(forNameResult);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            null, deserializer, new Gson(), typeToken, mock(TypeAdapterFactory.class));

    // Act
    TypeAdapter<Object> actualSerializationDelegate = treeTypeAdapter.getSerializationDelegate();

    // Assert
    verify(typeToken, atLeast(1)).getRawType();
    assertTrue(actualSerializationDelegate instanceof ObjectTypeAdapter);
  }

  /** Method under test: {@link TreeTypeAdapter#getSerializationDelegate()} */
  @Test
  public void testGetSerializationDelegate3() {
    // Arrange
    TypeToken<Object> typeToken = mock(TypeToken.class);
    when(typeToken.getType()).thenReturn(new GenericMetadataSupport.TypeVarBoundedType(null));
    Class<Object> forNameResult = Object.class;
    Mockito.<Class<? super Object>>when(typeToken.getRawType()).thenReturn(forNameResult);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(null, deserializer, new Gson(), typeToken, ArrayTypeAdapter.FACTORY);

    // Act
    TypeAdapter<Object> actualSerializationDelegate = treeTypeAdapter.getSerializationDelegate();

    // Assert
    verify(typeToken, atLeast(1)).getRawType();
    verify(typeToken, atLeast(1)).getType();
    assertEquals("{}", actualSerializationDelegate.toJson("Value"));
  }

  /** Method under test: {@link TreeTypeAdapter#getSerializationDelegate()} */
  @Test
  public void testGetSerializationDelegate4() {
    // Arrange
    TypeToken<Object> typeToken = mock(TypeToken.class);
    when(typeToken.getType()).thenReturn(new GenericMetadataSupport.TypeVarBoundedType(null));
    Class<Object> forNameResult = Object.class;
    Mockito.<Class<? super Object>>when(typeToken.getRawType()).thenReturn(forNameResult);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            null,
            deserializer,
            new Gson(),
            typeToken,
            DefaultDateTypeAdapter.DEFAULT_STYLE_FACTORY);

    // Act
    TypeAdapter<Object> actualSerializationDelegate = treeTypeAdapter.getSerializationDelegate();

    // Assert
    verify(typeToken, atLeast(1)).getRawType();
    verify(typeToken, atLeast(1)).getType();
    assertEquals("{}", actualSerializationDelegate.toJson("Value"));
  }

  /** Method under test: {@link TreeTypeAdapter#getSerializationDelegate()} */
  @Test
  public void testGetSerializationDelegate5() {
    // Arrange
    TypeToken<Object> typeToken = mock(TypeToken.class);
    when(typeToken.getType()).thenReturn(new GenericMetadataSupport.TypeVarBoundedType(null));
    Class<Object> forNameResult = Object.class;
    Mockito.<Class<? super Object>>when(typeToken.getRawType()).thenReturn(forNameResult);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(null, deserializer, new Gson(), typeToken, Excluder.DEFAULT);

    // Act
    TypeAdapter<Object> actualSerializationDelegate = treeTypeAdapter.getSerializationDelegate();

    // Assert
    verify(typeToken, atLeast(1)).getRawType();
    verify(typeToken, atLeast(1)).getType();
    assertEquals("{}", actualSerializationDelegate.toJson("Value"));
  }

  /** Method under test: {@link TreeTypeAdapter#getSerializationDelegate()} */
  @Test
  public void testGetSerializationDelegate6() {
    // Arrange
    TypeToken<Object> typeToken = mock(TypeToken.class);
    Class<Object> forNameResult = Object.class;
    Mockito.<Class<? super Object>>when(typeToken.getRawType()).thenReturn(forNameResult);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);
    TreeTypeAdapter<Object> treeTypeAdapter =
        new TreeTypeAdapter<>(
            null, deserializer, new Gson(), typeToken, TypeAdapters.JSON_ELEMENT_FACTORY);

    // Act
    TypeAdapter<Object> actualSerializationDelegate = treeTypeAdapter.getSerializationDelegate();

    // Assert
    verify(typeToken, atLeast(1)).getRawType();
    assertTrue(actualSerializationDelegate instanceof ObjectTypeAdapter);
  }

  /** Method under test: {@link TreeTypeAdapter#newFactory(TypeToken, Object)} */
  @Test
  public void testNewFactory() {
    // Arrange and Act
    TypeAdapterFactory actualNewFactoryResult =
        TreeTypeAdapter.newFactory(mock(TypeToken.class), mock(JsonSerializer.class));

    // Assert
    assertNull(actualNewFactoryResult.<Object>create(new Gson(), mock(TypeToken.class)));
  }

  /** Method under test: {@link TreeTypeAdapter#newTypeHierarchyFactory(Class, Object)} */
  @Test
  public void testNewTypeHierarchyFactory() {
    // Arrange
    Class<Object> hierarchyType = Object.class;

    // Act
    TypeAdapterFactory actualNewTypeHierarchyFactoryResult =
        TreeTypeAdapter.newTypeHierarchyFactory(hierarchyType, mock(JsonSerializer.class));
    Gson gson = new Gson();
    TypeToken<Object> typeToken = mock(TypeToken.class);
    Class<Object> forNameResult = Object.class;
    Mockito.<Class<? super Object>>when(typeToken.getRawType()).thenReturn(forNameResult);
    TypeAdapter<Object> actualCreateResult =
        actualNewTypeHierarchyFactoryResult.create(gson, typeToken);

    // Assert
    verify(typeToken).getRawType();
    assertTrue(actualCreateResult instanceof TreeTypeAdapter);
    Gson gson2 = ((TreeTypeAdapter<Object>) actualCreateResult).gson;
    assertFalse(gson2.serializeNulls());
    assertTrue(gson2.htmlSafe());
    assertSame(
        actualCreateResult,
        ((TreeTypeAdapter<Object>) actualCreateResult).getSerializationDelegate());
  }

  /**
   * Method under test: {@link TreeTypeAdapter#TreeTypeAdapter(JsonSerializer, JsonDeserializer,
   * Gson, TypeToken, TypeAdapterFactory)}
   */
  @Test
  public void testNewTreeTypeAdapter() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);

    // Act
    TreeTypeAdapter<Object> actualTreeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer,
            deserializer,
            new Gson(),
            mock(TypeToken.class),
            mock(TypeAdapterFactory.class));

    // Assert
    Gson gson = actualTreeTypeAdapter.gson;
    assertFalse(gson.serializeNulls());
    assertTrue(gson.htmlSafe());
    assertSame(actualTreeTypeAdapter, actualTreeTypeAdapter.getSerializationDelegate());
  }

  /**
   * Method under test: {@link TreeTypeAdapter#TreeTypeAdapter(JsonSerializer, JsonDeserializer,
   * Gson, TypeToken, TypeAdapterFactory, boolean)}
   */
  @Test
  public void testNewTreeTypeAdapter2() {
    // Arrange
    JsonSerializer<Object> serializer = mock(JsonSerializer.class);
    JsonDeserializer<Object> deserializer = mock(JsonDeserializer.class);

    // Act
    TreeTypeAdapter<Object> actualTreeTypeAdapter =
        new TreeTypeAdapter<>(
            serializer,
            deserializer,
            new Gson(),
            mock(TypeToken.class),
            mock(TypeAdapterFactory.class),
            true);

    // Assert
    Gson gson = actualTreeTypeAdapter.gson;
    assertFalse(gson.serializeNulls());
    assertTrue(gson.htmlSafe());
    assertSame(actualTreeTypeAdapter, actualTreeTypeAdapter.getSerializationDelegate());
  }
}
