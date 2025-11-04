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

import com.diffblue.cover.annotations.MaintainedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Properties;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.internal.util.reflection.GenericMetadataSupport;
import org.mockito.internal.util.reflection.GenericMetadataSupport.TypeVarBoundedType;

public class GsonTypesDiffblueTest {
  /**
   * Test {@link GsonTypes#newParameterizedTypeWithOwner(Type, Class, Type[])}.
   *
   * <p>Method under test: {@link GsonTypes#newParameterizedTypeWithOwner(Type, Class, Type[])}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
    "java.lang.reflect.ParameterizedType GsonTypes.newParameterizedTypeWithOwner(Type, Class,"
        + " Type[])"
  })
  public void testNewParameterizedTypeWithOwner() {
    // Arrange
    TypeVarBoundedType ownerType = new TypeVarBoundedType(null);
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

  /**
   * Test {@link GsonTypes#newParameterizedTypeWithOwner(Type, Class, Type[])}.
   *
   * <ul>
   *   <li>Then first element {@link GenericMetadataSupport.TypeVarBoundedType}.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#newParameterizedTypeWithOwner(Type, Class, Type[])}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
    "java.lang.reflect.ParameterizedType GsonTypes.newParameterizedTypeWithOwner(Type, Class,"
        + " Type[])"
  })
  public void testNewParameterizedTypeWithOwner_thenFirstElementTypeVarBoundedType() {
    // Arrange
    TypeVarBoundedType ownerType = new TypeVarBoundedType(null);
    Class<Object> rawType = Object.class;
    TypeVarBoundedType typeVarBoundedType = new TypeVarBoundedType(null);
    Type[] typeArguments = new Type[] {typeVarBoundedType};

    // Act
    GsonTypes.newParameterizedTypeWithOwner(ownerType, rawType, typeArguments);

    // Assert that nothing has changed
    Type type = typeArguments[0];
    assertTrue(type instanceof TypeVarBoundedType);
    assertEquals(1, typeArguments.length);
    assertSame(typeVarBoundedType, type);
  }

  /**
   * Test {@link GsonTypes#newParameterizedTypeWithOwner(Type, Class, Type[])}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then first element {@link GenericMetadataSupport.TypeVarBoundedType}.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#newParameterizedTypeWithOwner(Type, Class, Type[])}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
    "java.lang.reflect.ParameterizedType GsonTypes.newParameterizedTypeWithOwner(Type, Class,"
        + " Type[])"
  })
  public void testNewParameterizedTypeWithOwner_whenNull_thenFirstElementTypeVarBoundedType() {
    // Arrange
    Class<Object> rawType = Object.class;
    TypeVarBoundedType typeVarBoundedType = new TypeVarBoundedType(null);
    Type[] typeArguments = new Type[] {typeVarBoundedType};

    // Act
    GsonTypes.newParameterizedTypeWithOwner(null, rawType, typeArguments);

    // Assert that nothing has changed
    Type type = typeArguments[0];
    assertTrue(type instanceof TypeVarBoundedType);
    assertEquals(1, typeArguments.length);
    assertSame(typeVarBoundedType, type);
  }

  /**
   * Test {@link GsonTypes#arrayOf(Type)}.
   *
   * <p>Method under test: {@link GsonTypes#arrayOf(Type)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"GenericArrayType GsonTypes.arrayOf(Type)"})
  public void testArrayOf() {
    // Arrange
    TypeVarBoundedType componentType = new TypeVarBoundedType(null);

    // Act and Assert
    assertSame(componentType, GsonTypes.arrayOf(componentType).getGenericComponentType());
  }

  /**
   * Test {@link GsonTypes#subtypeOf(Type)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return TypeName is {@code ?}.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#subtypeOf(Type)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"java.lang.reflect.WildcardType GsonTypes.subtypeOf(Type)"})
  public void testSubtypeOf_whenJavaLangObject_thenReturnTypeNameIsQuestionMark() {
    // Arrange
    Class<Object> bound = Object.class;

    // Act and Assert
    assertEquals("?", GsonTypes.subtypeOf(bound).getTypeName());
  }

  /**
   * Test {@link GsonTypes#supertypeOf(Type)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return TypeName is {@code ? super Object}.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#supertypeOf(Type)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"java.lang.reflect.WildcardType GsonTypes.supertypeOf(Type)"})
  public void testSupertypeOf_whenJavaLangObject_thenReturnTypeNameIsSuperJavaLangObject() {
    // Arrange
    Class<Object> bound = Object.class;

    // Act and Assert
    assertEquals("? super java.lang.Object", GsonTypes.supertypeOf(bound).getTypeName());
  }

  /**
   * Test {@link GsonTypes#canonicalize(Type)}.
   *
   * <ul>
   *   <li>Given {@link GenericMetadataSupport.TypeVarBoundedType#TypeVarBoundedType(TypeVariable)}
   *       with typeVariable is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#canonicalize(Type)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"Type GsonTypes.canonicalize(Type)"})
  public void testCanonicalize_givenTypeVarBoundedTypeWithTypeVariableIsNull() {
    // Arrange
    GenericArrayType type = mock(GenericArrayType.class);
    when(type.getGenericComponentType()).thenReturn(new TypeVarBoundedType(null));

    // Act
    GsonTypes.canonicalize(type);

    // Assert
    verify(type).getGenericComponentType();
  }

  /**
   * Test {@link GsonTypes#canonicalize(Type)}.
   *
   * <ul>
   *   <li>Then return {@link GenericMetadataSupport.TypeVarBoundedType}.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#canonicalize(Type)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"Type GsonTypes.canonicalize(Type)"})
  public void testCanonicalize_thenReturnTypeVarBoundedType() {
    // Arrange and Act
    Type actualCanonicalizeResult = GsonTypes.canonicalize(new TypeVarBoundedType(null));

    // Assert
    assertTrue(actualCanonicalizeResult instanceof TypeVarBoundedType);
    assertNull(((TypeVarBoundedType) actualCanonicalizeResult).typeVariable());
  }

  /**
   * Test {@link GsonTypes#canonicalize(Type)}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#canonicalize(Type)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"Type GsonTypes.canonicalize(Type)"})
  public void testCanonicalize_thenThrowUnsupportedOperationException() {
    // Arrange
    GenericArrayType type = mock(GenericArrayType.class);
    when(type.getGenericComponentType()).thenThrow(new UnsupportedOperationException("foo"));

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> GsonTypes.canonicalize(type));
    verify(type).getGenericComponentType();
  }

  /**
   * Test {@link GsonTypes#getRawType(Type)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return {@link Object}.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#getRawType(Type)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"Class GsonTypes.getRawType(Type)"})
  public void testGetRawType_whenJavaLangObject_thenReturnObject() {
    // Arrange
    Class<Object> type = Object.class;

    // Act
    Class<?> actualRawType = GsonTypes.getRawType(type);

    // Assert
    Class<Object> expectedRawType = Object.class;
    assertEquals(expectedRawType, actualRawType);
  }

  /**
   * Test {@link GsonTypes#getRawType(Type)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#getRawType(Type)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"Class GsonTypes.getRawType(Type)"})
  public void testGetRawType_whenNull_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> GsonTypes.getRawType(null));
  }

  /**
   * Test {@link GsonTypes#equals(Type, Type)} with {@code Type}, {@code Type}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#equals(Type, Type)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"boolean GsonTypes.equals(Type, Type)"})
  public void testEqualsWithTypeType_thenReturnFalse() {
    // Arrange
    TypeVarBoundedType a = new TypeVarBoundedType(null);

    // Act and Assert
    assertFalse(GsonTypes.equals(a, new TypeVarBoundedType(null)));
  }

  /**
   * Test {@link GsonTypes#equals(Type, Type)} with {@code Type}, {@code Type}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#equals(Type, Type)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"boolean GsonTypes.equals(Type, Type)"})
  public void testEqualsWithTypeType_whenJavaLangObject_thenReturnFalse() {
    // Arrange
    Class<Object> a = Object.class;

    // Act and Assert
    assertFalse(GsonTypes.equals(a, new TypeVarBoundedType(null)));
  }

  /**
   * Test {@link GsonTypes#equals(Type, Type)} with {@code Type}, {@code Type}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#equals(Type, Type)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"boolean GsonTypes.equals(Type, Type)"})
  public void testEqualsWithTypeType_whenJavaLangObject_thenReturnTrue() {
    // Arrange
    Class<Object> a = Object.class;
    Class<Object> b = Object.class;

    // Act and Assert
    assertTrue(GsonTypes.equals(a, b));
  }

  /**
   * Test {@link GsonTypes#typeToString(Type)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return {@code Object}.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#typeToString(Type)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"java.lang.String GsonTypes.typeToString(Type)"})
  public void testTypeToString_whenJavaLangObject_thenReturnJavaLangObject() {
    // Arrange
    Class<Object> type = Object.class;

    // Act and Assert
    assertEquals("java.lang.Object", GsonTypes.typeToString(type));
  }

  /**
   * Test {@link GsonTypes#getArrayComponentType(Type)}.
   *
   * <ul>
   *   <li>Then return {@link GenericMetadataSupport.TypeVarBoundedType}.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#getArrayComponentType(Type)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"Type GsonTypes.getArrayComponentType(Type)"})
  public void testGetArrayComponentType_thenReturnTypeVarBoundedType() {
    // Arrange
    GenericArrayType array = mock(GenericArrayType.class);
    when(array.getGenericComponentType()).thenReturn(new TypeVarBoundedType(null));

    // Act
    Type actualArrayComponentType = GsonTypes.getArrayComponentType(array);

    // Assert
    verify(array).getGenericComponentType();
    assertTrue(actualArrayComponentType instanceof TypeVarBoundedType);
    assertNull(((TypeVarBoundedType) actualArrayComponentType).typeVariable());
  }

  /**
   * Test {@link GsonTypes#getArrayComponentType(Type)}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#getArrayComponentType(Type)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"Type GsonTypes.getArrayComponentType(Type)"})
  public void testGetArrayComponentType_thenThrowUnsupportedOperationException() {
    // Arrange
    GenericArrayType array = mock(GenericArrayType.class);
    when(array.getGenericComponentType()).thenThrow(new UnsupportedOperationException("foo"));

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> GsonTypes.getArrayComponentType(array));
    verify(array).getGenericComponentType();
  }

  /**
   * Test {@link GsonTypes#getArrayComponentType(Type)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#getArrayComponentType(Type)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"Type GsonTypes.getArrayComponentType(Type)"})
  public void testGetArrayComponentType_whenJavaLangObject_thenReturnNull() {
    // Arrange
    Class<Object> array = Object.class;

    // Act and Assert
    assertNull(GsonTypes.getArrayComponentType(array));
  }

  /**
   * Test {@link GsonTypes#getCollectionElementType(Type, Class)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#getCollectionElementType(Type, Class)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"Type GsonTypes.getCollectionElementType(Type, Class)"})
  public void testGetCollectionElementType_thenThrowIllegalArgumentException() {
    // Arrange
    TypeVarBoundedType context = new TypeVarBoundedType(null);
    Class<Object> contextRawType = Object.class;

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> GsonTypes.getCollectionElementType(context, contextRawType));
  }

  /**
   * Test {@link GsonTypes#getMapKeyAndValueTypes(Type, Class)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return first element is {@link Object}.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#getMapKeyAndValueTypes(Type, Class)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"Type[] GsonTypes.getMapKeyAndValueTypes(Type, Class)"})
  public void testGetMapKeyAndValueTypes_whenJavaLangObject_thenReturnFirstElementIsObject() {
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

  /**
   * Test {@link GsonTypes#getMapKeyAndValueTypes(Type, Class)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#getMapKeyAndValueTypes(Type, Class)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"Type[] GsonTypes.getMapKeyAndValueTypes(Type, Class)"})
  public void testGetMapKeyAndValueTypes_whenJavaLangObject_thenThrowIllegalArgumentException() {
    // Arrange
    TypeVarBoundedType context = new TypeVarBoundedType(null);
    Class<Object> contextRawType = Object.class;

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> GsonTypes.getMapKeyAndValueTypes(context, contextRawType));
  }

  /**
   * Test {@link GsonTypes#getMapKeyAndValueTypes(Type, Class)}.
   *
   * <ul>
   *   <li>When {@code Map}.
   *   <li>Then return second element is first element.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#getMapKeyAndValueTypes(Type, Class)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"Type[] GsonTypes.getMapKeyAndValueTypes(Type, Class)"})
  public void testGetMapKeyAndValueTypes_whenJavaUtilMap_thenReturnSecondElementIsFirstElement() {
    // Arrange
    TypeVarBoundedType context = new TypeVarBoundedType(null);
    Class<Map> contextRawType = Map.class;

    // Act
    Type[] actualMapKeyAndValueTypes = GsonTypes.getMapKeyAndValueTypes(context, contextRawType);

    // Assert
    assertEquals(2, actualMapKeyAndValueTypes.length);
    assertSame(actualMapKeyAndValueTypes[0], actualMapKeyAndValueTypes[1]);
  }

  /**
   * Test {@link GsonTypes#getMapKeyAndValueTypes(Type, Class)}.
   *
   * <ul>
   *   <li>When {@code Properties}.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#getMapKeyAndValueTypes(Type, Class)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"Type[] GsonTypes.getMapKeyAndValueTypes(Type, Class)"})
  public void testGetMapKeyAndValueTypes_whenJavaUtilProperties() {
    // Arrange
    TypeVarBoundedType context = new TypeVarBoundedType(null);
    Class<Properties> contextRawType = Properties.class;

    // Act
    Type[] actualMapKeyAndValueTypes = GsonTypes.getMapKeyAndValueTypes(context, contextRawType);

    // Assert
    assertEquals(2, actualMapKeyAndValueTypes.length);
    assertSame(actualMapKeyAndValueTypes[0], actualMapKeyAndValueTypes[1]);
  }

  /**
   * Test {@link GsonTypes#resolve(Type, Class, Type)} with {@code context}, {@code contextRawType},
   * {@code toResolve}.
   *
   * <ul>
   *   <li>Then return {@link GenericMetadataSupport.TypeVarBoundedType}.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#resolve(Type, Class, Type)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"Type GsonTypes.resolve(Type, Class, Type)"})
  public void testResolveWithContextContextRawTypeToResolve_thenReturnTypeVarBoundedType() {
    // Arrange
    TypeVarBoundedType context = new TypeVarBoundedType(null);
    Class<Object> contextRawType = Object.class;

    // Act
    Type actualResolveResult =
        GsonTypes.resolve(context, contextRawType, new TypeVarBoundedType(null));

    // Assert
    assertTrue(actualResolveResult instanceof TypeVarBoundedType);
    assertNull(((TypeVarBoundedType) actualResolveResult).typeVariable());
  }

  /**
   * Test {@link GsonTypes#requiresOwnerType(Type)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#requiresOwnerType(Type)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"boolean GsonTypes.requiresOwnerType(Type)"})
  public void testRequiresOwnerType_whenJavaLangObject() {
    // Arrange
    Class<Object> rawType = Object.class;

    // Act and Assert
    assertFalse(GsonTypes.requiresOwnerType(rawType));
  }

  /**
   * Test {@link GsonTypes#requiresOwnerType(Type)}.
   *
   * <ul>
   *   <li>When {@link GenericMetadataSupport.TypeVarBoundedType#TypeVarBoundedType(TypeVariable)}
   *       with typeVariable is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#requiresOwnerType(Type)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"boolean GsonTypes.requiresOwnerType(Type)"})
  public void testRequiresOwnerType_whenTypeVarBoundedTypeWithTypeVariableIsNull() {
    // Arrange, Act and Assert
    assertFalse(GsonTypes.requiresOwnerType(new TypeVarBoundedType(null)));
  }
}
