package com.google.gson.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Properties;
import org.junit.Test;
import org.mockito.internal.util.reflection.GenericMetadataSupport;

public class GsonTypesDiffblueTest {
  /** Method under test: {@link GsonTypes#newParameterizedTypeWithOwner(Type, Class, Type[])} */
  @Test
  public void testNewParameterizedTypeWithOwner() {
    // Arrange
    GenericMetadataSupport.TypeVarBoundedType ownerType =
        new GenericMetadataSupport.TypeVarBoundedType(null);
    Class<Object> rawType = Object.class;
    Class<Object> forNameResult = Object.class;
    Type[] typeArguments = new Type[] {forNameResult};

    // Act and Assert
    assertEquals(
        "java.lang.Object<java.lang.Object>",
        GsonTypes.newParameterizedTypeWithOwner(ownerType, rawType, typeArguments).getTypeName());
    assertEquals(1, typeArguments.length);
    assertSame(forNameResult, typeArguments[0]);
  }

  /** Method under test: {@link GsonTypes#arrayOf(Type)} */
  @Test
  public void testArrayOf() {
    // Arrange
    GenericMetadataSupport.TypeVarBoundedType componentType =
        new GenericMetadataSupport.TypeVarBoundedType(null);

    // Act and Assert
    assertSame(componentType, GsonTypes.arrayOf(componentType).getGenericComponentType());
  }

  /** Method under test: {@link GsonTypes#arrayOf(Type)} */
  @Test
  public void testArrayOf2() {
    // Arrange
    GenericArrayType componentType = mock(GenericArrayType.class);
    when(componentType.getGenericComponentType())
        .thenReturn(new GenericMetadataSupport.TypeVarBoundedType(null));

    // Act
    GsonTypes.arrayOf(componentType).getGenericComponentType();

    // Assert
    verify(componentType).getGenericComponentType();
  }

  /** Method under test: {@link GsonTypes#subtypeOf(Type)} */
  @Test
  public void testSubtypeOf() {
    // Arrange
    Class<Object> bound = Object.class;

    // Act and Assert
    assertEquals("?", GsonTypes.subtypeOf(bound).getTypeName());
  }

  /** Method under test: {@link GsonTypes#supertypeOf(Type)} */
  @Test
  public void testSupertypeOf() {
    // Arrange
    Class<Object> bound = Object.class;

    // Act and Assert
    assertEquals("? super java.lang.Object", GsonTypes.supertypeOf(bound).getTypeName());
  }

  /** Method under test: {@link GsonTypes#canonicalize(Type)} */
  @Test
  public void testCanonicalize() {
    // Arrange and Act
    Type actualCanonicalizeResult =
        GsonTypes.canonicalize(new GenericMetadataSupport.TypeVarBoundedType(null));

    // Assert
    assertTrue(actualCanonicalizeResult instanceof GenericMetadataSupport.TypeVarBoundedType);
    assertNull(
        ((GenericMetadataSupport.TypeVarBoundedType) actualCanonicalizeResult).typeVariable());
  }

  /** Method under test: {@link GsonTypes#canonicalize(Type)} */
  @Test
  public void testCanonicalize2() {
    // Arrange
    GenericArrayType type = mock(GenericArrayType.class);
    when(type.getGenericComponentType())
        .thenReturn(new GenericMetadataSupport.TypeVarBoundedType(null));

    // Act
    GsonTypes.canonicalize(type);

    // Assert
    verify(type).getGenericComponentType();
  }

  /** Method under test: {@link GsonTypes#canonicalize(Type)} */
  @Test
  public void testCanonicalize3() {
    // Arrange
    GenericArrayType type = mock(GenericArrayType.class);
    when(type.getGenericComponentType()).thenThrow(new UnsupportedOperationException("foo"));

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> GsonTypes.canonicalize(type));
    verify(type).getGenericComponentType();
  }

  /** Method under test: {@link GsonTypes#getRawType(Type)} */
  @Test
  public void testGetRawType() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> GsonTypes.getRawType(null));
  }

  /** Method under test: {@link GsonTypes#getRawType(Type)} */
  @Test
  public void testGetRawType2() {
    // Arrange
    Class<Object> type = Object.class;

    // Act
    Class<?> actualRawType = GsonTypes.getRawType(type);

    // Assert
    Class<Object> expectedRawType = Object.class;
    assertEquals(expectedRawType, actualRawType);
  }

  /** Method under test: {@link GsonTypes#equals(Type, Type)} */
  @Test
  public void testEquals() {
    // Arrange
    GenericMetadataSupport.TypeVarBoundedType a =
        new GenericMetadataSupport.TypeVarBoundedType(null);

    // Act and Assert
    assertFalse(GsonTypes.equals(a, new GenericMetadataSupport.TypeVarBoundedType(null)));
  }

  /** Method under test: {@link GsonTypes#equals(Type, Type)} */
  @Test
  public void testEquals2() {
    // Arrange
    Class<Object> a = Object.class;

    // Act and Assert
    assertFalse(GsonTypes.equals(a, new GenericMetadataSupport.TypeVarBoundedType(null)));
  }

  /** Method under test: {@link GsonTypes#equals(Type, Type)} */
  @Test
  public void testEquals3() {
    // Arrange
    Class<Object> a = Object.class;
    Class<Object> b = Object.class;

    // Act and Assert
    assertTrue(GsonTypes.equals(a, b));
  }

  /** Method under test: {@link GsonTypes#typeToString(Type)} */
  @Test
  public void testTypeToString() {
    // Arrange
    Class<Object> type = Object.class;

    // Act and Assert
    assertEquals("java.lang.Object", GsonTypes.typeToString(type));
  }

  /** Method under test: {@link GsonTypes#getArrayComponentType(Type)} */
  @Test
  public void testGetArrayComponentType() {
    // Arrange
    Class<Object> array = Object.class;

    // Act and Assert
    assertNull(GsonTypes.getArrayComponentType(array));
  }

  /** Method under test: {@link GsonTypes#getArrayComponentType(Type)} */
  @Test
  public void testGetArrayComponentType2() {
    // Arrange
    GenericArrayType array = mock(GenericArrayType.class);
    when(array.getGenericComponentType())
        .thenReturn(new GenericMetadataSupport.TypeVarBoundedType(null));

    // Act
    Type actualArrayComponentType = GsonTypes.getArrayComponentType(array);

    // Assert
    verify(array).getGenericComponentType();
    assertTrue(actualArrayComponentType instanceof GenericMetadataSupport.TypeVarBoundedType);
    assertNull(
        ((GenericMetadataSupport.TypeVarBoundedType) actualArrayComponentType).typeVariable());
  }

  /** Method under test: {@link GsonTypes#getArrayComponentType(Type)} */
  @Test
  public void testGetArrayComponentType3() {
    // Arrange
    GenericArrayType array = mock(GenericArrayType.class);
    when(array.getGenericComponentType()).thenThrow(new UnsupportedOperationException("foo"));

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> GsonTypes.getArrayComponentType(array));
    verify(array).getGenericComponentType();
  }

  /** Method under test: {@link GsonTypes#getCollectionElementType(Type, Class)} */
  @Test
  public void testGetCollectionElementType() {
    // Arrange
    GenericMetadataSupport.TypeVarBoundedType context =
        new GenericMetadataSupport.TypeVarBoundedType(null);
    Class<Object> contextRawType = Object.class;

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> GsonTypes.getCollectionElementType(context, contextRawType));
  }

  /** Method under test: {@link GsonTypes#getMapKeyAndValueTypes(Type, Class)} */
  @Test
  public void testGetMapKeyAndValueTypes() {
    // Arrange
    GenericMetadataSupport.TypeVarBoundedType context =
        new GenericMetadataSupport.TypeVarBoundedType(null);
    Class<Object> contextRawType = Object.class;

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> GsonTypes.getMapKeyAndValueTypes(context, contextRawType));
  }

  /** Method under test: {@link GsonTypes#getMapKeyAndValueTypes(Type, Class)} */
  @Test
  public void testGetMapKeyAndValueTypes2() {
    // Arrange
    GenericMetadataSupport.TypeVarBoundedType context =
        new GenericMetadataSupport.TypeVarBoundedType(null);
    Class<Map> contextRawType = Map.class;

    // Act
    Type[] actualMapKeyAndValueTypes = GsonTypes.getMapKeyAndValueTypes(context, contextRawType);

    // Assert
    assertEquals(2, actualMapKeyAndValueTypes.length);
    assertSame(actualMapKeyAndValueTypes[0], actualMapKeyAndValueTypes[1]);
  }

  /** Method under test: {@link GsonTypes#getMapKeyAndValueTypes(Type, Class)} */
  @Test
  public void testGetMapKeyAndValueTypes3() {
    // Arrange
    Class<Object> context = Object.class;
    Class<Map> contextRawType = Map.class;

    // Act
    Type[] actualMapKeyAndValueTypes = GsonTypes.getMapKeyAndValueTypes(context, contextRawType);

    // Assert
    assertEquals(2, actualMapKeyAndValueTypes.length);
    assertSame(context, actualMapKeyAndValueTypes[0]);
    assertSame(context, actualMapKeyAndValueTypes[1]);
  }

  /** Method under test: {@link GsonTypes#getMapKeyAndValueTypes(Type, Class)} */
  @Test
  public void testGetMapKeyAndValueTypes4() {
    // Arrange
    GenericMetadataSupport.TypeVarBoundedType context =
        new GenericMetadataSupport.TypeVarBoundedType(null);
    Class<Properties> contextRawType = Properties.class;

    // Act
    Type[] actualMapKeyAndValueTypes = GsonTypes.getMapKeyAndValueTypes(context, contextRawType);

    // Assert
    assertEquals(2, actualMapKeyAndValueTypes.length);
    assertSame(actualMapKeyAndValueTypes[0], actualMapKeyAndValueTypes[1]);
  }

  /** Method under test: {@link GsonTypes#resolve(Type, Class, Type)} */
  @Test
  public void testResolve() {
    // Arrange
    GenericMetadataSupport.TypeVarBoundedType context =
        new GenericMetadataSupport.TypeVarBoundedType(null);
    Class<Object> contextRawType = Object.class;

    // Act
    Type actualResolveResult =
        GsonTypes.resolve(
            context, contextRawType, new GenericMetadataSupport.TypeVarBoundedType(null));

    // Assert
    assertTrue(actualResolveResult instanceof GenericMetadataSupport.TypeVarBoundedType);
    assertNull(((GenericMetadataSupport.TypeVarBoundedType) actualResolveResult).typeVariable());
  }

  /** Method under test: {@link GsonTypes#requiresOwnerType(Type)} */
  @Test
  public void testRequiresOwnerType() {
    // Arrange, Act and Assert
    assertFalse(GsonTypes.requiresOwnerType(new GenericMetadataSupport.TypeVarBoundedType(null)));
  }

  /** Method under test: {@link GsonTypes#requiresOwnerType(Type)} */
  @Test
  public void testRequiresOwnerType2() {
    // Arrange
    Class<Object> rawType = Object.class;

    // Act and Assert
    assertFalse(GsonTypes.requiresOwnerType(rawType));
  }
}
