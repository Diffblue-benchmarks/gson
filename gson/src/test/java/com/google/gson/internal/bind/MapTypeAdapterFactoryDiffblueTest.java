package com.google.gson.internal.bind;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.diffblue.cover.annotations.MaintainedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.Gson;
import com.google.gson.InstanceCreator;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.internal.util.reflection.GenericMetadataSupport.TypeVarBoundedType;

public class MapTypeAdapterFactoryDiffblueTest {
  /**
   * Test {@link MapTypeAdapterFactory#MapTypeAdapterFactory(ConstructorConstructor, boolean)}.
   *
   * <p>Method under test: {@link
   * MapTypeAdapterFactory#MapTypeAdapterFactory(ConstructorConstructor, boolean)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void MapTypeAdapterFactory.<init>(ConstructorConstructor, boolean)"})
  public void testNewMapTypeAdapterFactory() {
    // Arrange
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();

    // Act and Assert
    assertTrue(
        (new MapTypeAdapterFactory(
                new ConstructorConstructor(instanceCreators, true, new ArrayList<>()), true))
            .complexMapKeySerialization);
  }

  /**
   * Test {@link MapTypeAdapterFactory#create(Gson, TypeToken)}.
   *
   * <ul>
   *   <li>When {@link TypeToken} {@link TypeToken#getRawType()} return {@link Object}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link MapTypeAdapterFactory#create(Gson, TypeToken)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"TypeAdapter MapTypeAdapterFactory.create(Gson, TypeToken)"})
  public void testCreate_whenTypeTokenGetRawTypeReturnObject_thenReturnNull() {
    // Arrange
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    MapTypeAdapterFactory mapTypeAdapterFactory =
        new MapTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>()), true);
    Gson gson = new Gson();
    TypeToken<Object> typeToken = mock(TypeToken.class);
    Class<Object> forNameResult = Object.class;
    org.mockito.Mockito.<Class<? super Object>>when(typeToken.getRawType())
        .thenReturn(forNameResult);
    when(typeToken.getType()).thenReturn(new TypeVarBoundedType(null));

    // Act
    TypeAdapter<Object> actualCreateResult = mapTypeAdapterFactory.create(gson, typeToken);

    // Assert
    verify(typeToken).getRawType();
    verify(typeToken).getType();
    assertNull(actualCreateResult);
  }
}
