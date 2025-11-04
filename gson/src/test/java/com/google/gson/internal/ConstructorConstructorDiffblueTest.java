package com.google.gson.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.gson.InstanceCreator;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.mockito.Mockito;

public class ConstructorConstructorDiffblueTest {
  /** Method under test: {@link ConstructorConstructor#checkInstantiable(Class)} */
  @Test
  public void testCheckInstantiable() {
    // Arrange
    Class<Object> c = Object.class;

    // Act and Assert
    assertNull(ConstructorConstructor.checkInstantiable(c));
  }

  /** Method under test: {@link ConstructorConstructor#checkInstantiable(Class)} */
  @Test
  public void testCheckInstantiable2() {
    // Arrange
    Class<ParameterizedType> c = ParameterizedType.class;

    // Act and Assert
    assertEquals(
        "Interfaces can't be instantiated! Register an InstanceCreator or a TypeAdapter for this"
            + " type. Interface name: java.lang.reflect.ParameterizedType",
        ConstructorConstructor.checkInstantiable(c));
  }

  /** Method under test: {@link ConstructorConstructor#checkInstantiable(Class)} */
  @Test
  public void testCheckInstantiable3() {
    // Arrange
    Class<EnumSet> c = EnumSet.class;

    // Act and Assert
    assertEquals(
        "Abstract classes can't be instantiated! Adjust the R8 configuration or register an"
            + " InstanceCreator or a TypeAdapter for this type. Class name: java.util.EnumSet\n"
            + "See https://github.com/google/gson/blob/main/Troubleshooting.md#r8-abstract-class",
        ConstructorConstructor.checkInstantiable(c));
  }

  /** Method under test: {@link ConstructorConstructor#get(TypeToken)} */
  @Test
  public void testGet() {
    // Arrange
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(instanceCreators, true, new ArrayList<>());
    TypeToken<Object> typeToken = mock(TypeToken.class);
    Class<Object> forNameResult = Object.class;
    Mockito.<Class<? super Object>>when(typeToken.getRawType()).thenReturn(forNameResult);
    Class<Object> forNameResult2 = Object.class;
    when(typeToken.getType()).thenReturn(forNameResult2);

    // Act
    constructorConstructor.get(typeToken).construct();

    // Assert
    verify(typeToken).getRawType();
    verify(typeToken).getType();
  }

  /** Method under test: {@link ConstructorConstructor#get(TypeToken, boolean)} */
  @Test
  public void testGet2() {
    // Arrange
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(instanceCreators, true, new ArrayList<>());
    TypeToken<Object> typeToken = mock(TypeToken.class);
    Class<Object> forNameResult = Object.class;
    Mockito.<Class<? super Object>>when(typeToken.getRawType()).thenReturn(forNameResult);
    Class<Object> forNameResult2 = Object.class;
    when(typeToken.getType()).thenReturn(forNameResult2);

    // Act
    constructorConstructor.get(typeToken, true).construct();

    // Assert
    verify(typeToken).getRawType();
    verify(typeToken).getType();
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link ConstructorConstructor#ConstructorConstructor(Map, boolean, List)}
   *   <li>{@link ConstructorConstructor#toString()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();

    // Act and Assert
    assertEquals(
        "{}", (new ConstructorConstructor(instanceCreators, true, new ArrayList<>())).toString());
  }
}
