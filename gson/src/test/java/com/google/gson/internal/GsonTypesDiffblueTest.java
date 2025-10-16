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
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.GsonBuilderTestFactory;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ParameterizedType GsonTypes.newParameterizedTypeWithOwner(Type, Class, Type[])"
  })
  public void testNewParameterizedTypeWithOwner() {
    // Arrange
    Type ownerType = GsonBuilderTestFactory.createType();
    Class<Object> rawType = Object.class;

    // Act
    ParameterizedType actualNewParameterizedTypeWithOwnerResult =
        GsonTypes.newParameterizedTypeWithOwner(
            ownerType, rawType, GsonBuilderTestFactory.createType());

    // Assert
    assertEquals(
        "java.lang.Object<java.lang.String>",
        actualNewParameterizedTypeWithOwnerResult.getTypeName());
  }

  /**
   * Test {@link GsonTypes#newParameterizedTypeWithOwner(Type, Class, Type[])}.
   *
   * <p>Method under test: {@link GsonTypes#newParameterizedTypeWithOwner(Type, Class, Type[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ParameterizedType GsonTypes.newParameterizedTypeWithOwner(Type, Class, Type[])"
  })
  public void testNewParameterizedTypeWithOwner2() {
    // Arrange
    Class<Object> rawType = Object.class;

    // Act
    ParameterizedType actualNewParameterizedTypeWithOwnerResult =
        GsonTypes.newParameterizedTypeWithOwner(null, rawType, GsonBuilderTestFactory.createType());

    // Assert
    assertEquals(
        "java.lang.Object<java.lang.String>",
        actualNewParameterizedTypeWithOwnerResult.getTypeName());
  }

  /**
   * Test {@link GsonTypes#newParameterizedTypeWithOwner(Type, Class, Type[])}.
   *
   * <ul>
   *   <li>When {@link GenericMetadataSupport.TypeVarBoundedType#TypeVarBoundedType(TypeVariable)}
   *       with typeVariable is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#newParameterizedTypeWithOwner(Type, Class, Type[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ParameterizedType GsonTypes.newParameterizedTypeWithOwner(Type, Class, Type[])"
  })
  public void testNewParameterizedTypeWithOwner_whenTypeVarBoundedTypeWithTypeVariableIsNull() {
    // Arrange
    TypeVarBoundedType ownerType = new TypeVarBoundedType(null);
    Class<Object> rawType = Object.class;

    // Act
    ParameterizedType actualNewParameterizedTypeWithOwnerResult =
        GsonTypes.newParameterizedTypeWithOwner(
            ownerType, rawType, GsonBuilderTestFactory.createType());

    // Assert
    assertEquals(
        "java.lang.Object<java.lang.String>",
        actualNewParameterizedTypeWithOwnerResult.getTypeName());
  }

  /**
   * Test {@link GsonTypes#arrayOf(Type)}.
   *
   * <ul>
   *   <li>Given {@link GenericMetadataSupport.TypeVarBoundedType#TypeVarBoundedType(TypeVariable)}
   *       with typeVariable is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#arrayOf(Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"GenericArrayType GsonTypes.arrayOf(Type)"})
  public void testArrayOf_givenTypeVarBoundedTypeWithTypeVariableIsNull() {
    // Arrange
    GenericArrayType componentType = mock(GenericArrayType.class);
    when(componentType.getGenericComponentType()).thenReturn(new TypeVarBoundedType(null));

    // Act
    GsonTypes.arrayOf(componentType).getGenericComponentType();

    // Assert
    verify(componentType).getGenericComponentType();
  }

  /**
   * Test {@link GsonTypes#arrayOf(Type)}.
   *
   * <ul>
   *   <li>Then return GenericComponentType TypeName is {@code String[]}.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#arrayOf(Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"GenericArrayType GsonTypes.arrayOf(Type)"})
  public void testArrayOf_thenReturnGenericComponentTypeTypeNameIsJavaLangString() {
    // Arrange
    GenericArrayType componentType = mock(GenericArrayType.class);
    when(componentType.getGenericComponentType()).thenReturn(GsonBuilderTestFactory.createType());

    // Act
    GenericArrayType actualArrayOfResult = GsonTypes.arrayOf(componentType);
    Type actualGenericComponentType = actualArrayOfResult.getGenericComponentType();

    // Assert
    verify(componentType).getGenericComponentType();
    assertEquals("java.lang.String[]", actualGenericComponentType.getTypeName());
    assertEquals("java.lang.String[][]", actualArrayOfResult.getTypeName());
  }

  /**
   * Test {@link GsonTypes#arrayOf(Type)}.
   *
   * <ul>
   *   <li>When createType.
   *   <li>Then return TypeName is {@code String[]}.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#arrayOf(Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"GenericArrayType GsonTypes.arrayOf(Type)"})
  public void testArrayOf_whenCreateType_thenReturnTypeNameIsJavaLangString() {
    // Arrange
    Type componentType = GsonBuilderTestFactory.createType();

    // Act
    GenericArrayType actualArrayOfResult = GsonTypes.arrayOf(componentType);
    Type actualGenericComponentType = actualArrayOfResult.getGenericComponentType();

    // Assert
    assertEquals("java.lang.String[]", actualArrayOfResult.getTypeName());
    assertSame(componentType, actualGenericComponentType);
  }

  /**
   * Test {@link GsonTypes#subtypeOf(Type)}.
   *
   * <ul>
   *   <li>When createType.
   *   <li>Then return TypeName is {@code ? extends String}.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#subtypeOf(Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.reflect.WildcardType GsonTypes.subtypeOf(Type)"})
  public void testSubtypeOf_whenCreateType_thenReturnTypeNameIsExtendsJavaLangString() {
    // Arrange, Act and Assert
    assertEquals(
        "? extends java.lang.String",
        GsonTypes.subtypeOf(GsonBuilderTestFactory.createType()).getTypeName());
  }

  /**
   * Test {@link GsonTypes#supertypeOf(Type)}.
   *
   * <ul>
   *   <li>When createType.
   *   <li>Then return TypeName is {@code ? super String}.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#supertypeOf(Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.reflect.WildcardType GsonTypes.supertypeOf(Type)"})
  public void testSupertypeOf_whenCreateType_thenReturnTypeNameIsSuperJavaLangString() {
    // Arrange, Act and Assert
    assertEquals(
        "? super java.lang.String",
        GsonTypes.supertypeOf(GsonBuilderTestFactory.createType()).getTypeName());
  }

  /**
   * Test {@link GsonTypes#canonicalize(Type)}.
   *
   * <p>Method under test: {@link GsonTypes#canonicalize(Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Type GsonTypes.canonicalize(Type)"})
  public void testCanonicalize() {
    // Arrange
    GenericArrayType genericArrayType = mock(GenericArrayType.class);
    when(genericArrayType.getGenericComponentType()).thenThrow(new UnsupportedOperationException());

    GenericArrayType type = mock(GenericArrayType.class);
    when(type.getGenericComponentType()).thenReturn(genericArrayType);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> GsonTypes.canonicalize(type));
    verify(type).getGenericComponentType();
    verify(genericArrayType).getGenericComponentType();
  }

  /**
   * Test {@link GsonTypes#canonicalize(Type)}.
   *
   * <ul>
   *   <li>Given {@code Object}.
   *   <li>Then return TypeName is {@code Object[]}.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#canonicalize(Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Type GsonTypes.canonicalize(Type)"})
  public void testCanonicalize_givenJavaLangObject_thenReturnTypeNameIsJavaLangObject() {
    // Arrange
    GenericArrayType type = mock(GenericArrayType.class);
    Class<Object> forNameResult = Object.class;
    when(type.getGenericComponentType()).thenReturn(forNameResult);

    // Act
    Type actualCanonicalizeResult = GsonTypes.canonicalize(type);

    // Assert
    verify(type).getGenericComponentType();
    assertEquals("java.lang.Object[]", actualCanonicalizeResult.getTypeName());
  }

  /**
   * Test {@link GsonTypes#canonicalize(Type)}.
   *
   * <ul>
   *   <li>Given {@link UnsupportedOperationException#UnsupportedOperationException()}.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#canonicalize(Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Type GsonTypes.canonicalize(Type)"})
  public void testCanonicalize_givenUnsupportedOperationException() {
    // Arrange
    GenericArrayType type = mock(GenericArrayType.class);
    when(type.getGenericComponentType()).thenThrow(new UnsupportedOperationException());

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> GsonTypes.canonicalize(type));
    verify(type).getGenericComponentType();
  }

  /**
   * Test {@link GsonTypes#canonicalize(Type)}.
   *
   * <ul>
   *   <li>When createType.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#canonicalize(Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Type GsonTypes.canonicalize(Type)"})
  public void testCanonicalize_whenCreateType_thenDoesNotThrow() {
    // Arrange, Act and Assert
    GsonTypes.canonicalize(GsonBuilderTestFactory.createType());
  }

  /**
   * Test {@link GsonTypes#canonicalize(Type)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#canonicalize(Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Type GsonTypes.canonicalize(Type)"})
  public void testCanonicalize_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(GsonTypes.canonicalize(null));
  }

  /**
   * Test {@link GsonTypes#getRawType(Type)}.
   *
   * <ul>
   *   <li>When createType.
   *   <li>Then return {@link String}.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#getRawType(Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class GsonTypes.getRawType(Type)"})
  public void testGetRawType_whenCreateType_thenReturnString() {
    // Arrange and Act
    Class<?> actualRawType = GsonTypes.getRawType(GsonBuilderTestFactory.createType());

    // Assert
    Class<String> expectedRawType = String.class;
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class GsonTypes.getRawType(Type)"})
  public void testGetRawType_whenNull_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> GsonTypes.getRawType(null));
  }

  /**
   * Test {@link GsonTypes#equals(Type, Type)} with {@code Type}, {@code Type}.
   *
   * <p>Method under test: {@link GsonTypes#equals(Type, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean GsonTypes.equals(Type, Type)"})
  public void testEqualsWithTypeType() {
    // Arrange
    GenericArrayType genericArrayType = mock(GenericArrayType.class);
    when(genericArrayType.getGenericComponentType()).thenThrow(new UnsupportedOperationException());

    GenericArrayType a = mock(GenericArrayType.class);
    when(a.getGenericComponentType()).thenReturn(genericArrayType);

    GenericArrayType b = mock(GenericArrayType.class);
    when(b.getGenericComponentType()).thenReturn(mock(GenericArrayType.class));

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> GsonTypes.equals(a, b));
    verify(a).getGenericComponentType();
    verify(genericArrayType).getGenericComponentType();
    verify(b).getGenericComponentType();
  }

  /**
   * Test {@link GsonTypes#equals(Type, Type)} with {@code Type}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@code Object}.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#equals(Type, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean GsonTypes.equals(Type, Type)"})
  public void testEqualsWithTypeType_givenJavaLangObject() {
    // Arrange
    GenericArrayType a = mock(GenericArrayType.class);
    Class<Object> forNameResult = Object.class;
    when(a.getGenericComponentType()).thenReturn(forNameResult);

    GenericArrayType b = mock(GenericArrayType.class);
    when(b.getGenericComponentType()).thenThrow(new UnsupportedOperationException());

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> GsonTypes.equals(a, b));
    verify(a).getGenericComponentType();
    verify(b).getGenericComponentType();
  }

  /**
   * Test {@link GsonTypes#equals(Type, Type)} with {@code Type}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@code Object}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#equals(Type, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean GsonTypes.equals(Type, Type)"})
  public void testEqualsWithTypeType_givenJavaLangObject_thenReturnTrue() {
    // Arrange
    GenericArrayType a = mock(GenericArrayType.class);
    Class<Object> forNameResult = Object.class;
    when(a.getGenericComponentType()).thenReturn(forNameResult);

    GenericArrayType b = mock(GenericArrayType.class);
    Class<Object> forNameResult2 = Object.class;
    when(b.getGenericComponentType()).thenReturn(forNameResult2);

    // Act
    boolean actualEqualsResult = GsonTypes.equals(a, b);

    // Assert
    verify(a).getGenericComponentType();
    verify(b).getGenericComponentType();
    assertTrue(actualEqualsResult);
  }

  /**
   * Test {@link GsonTypes#equals(Type, Type)} with {@code Type}, {@code Type}.
   *
   * <ul>
   *   <li>Given {@link UnsupportedOperationException#UnsupportedOperationException()}.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#equals(Type, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean GsonTypes.equals(Type, Type)"})
  public void testEqualsWithTypeType_givenUnsupportedOperationException() {
    // Arrange
    GenericArrayType a = mock(GenericArrayType.class);
    when(a.getGenericComponentType()).thenThrow(new UnsupportedOperationException());

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> GsonTypes.equals(a, mock(GenericArrayType.class)));
    verify(a).getGenericComponentType();
  }

  /**
   * Test {@link GsonTypes#equals(Type, Type)} with {@code Type}, {@code Type}.
   *
   * <ul>
   *   <li>When createType.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#equals(Type, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean GsonTypes.equals(Type, Type)"})
  public void testEqualsWithTypeType_whenCreateType_thenReturnTrue() {
    // Arrange
    Type a = GsonBuilderTestFactory.createType();

    // Act and Assert
    assertTrue(GsonTypes.equals(a, GsonBuilderTestFactory.createType()));
  }

  /**
   * Test {@link GsonTypes#equals(Type, Type)} with {@code Type}, {@code Type}.
   *
   * <ul>
   *   <li>When {@link GenericArrayType}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#equals(Type, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean GsonTypes.equals(Type, Type)"})
  public void testEqualsWithTypeType_whenGenericArrayType_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(GsonTypes.equals(mock(GenericArrayType.class), null));
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean GsonTypes.equals(Type, Type)"})
  public void testEqualsWithTypeType_whenJavaLangObject_thenReturnFalse() {
    // Arrange
    Class<Object> a = Object.class;

    // Act and Assert
    assertFalse(GsonTypes.equals(a, null));
  }

  /**
   * Test {@link GsonTypes#equals(Type, Type)} with {@code Type}, {@code Type}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#equals(Type, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean GsonTypes.equals(Type, Type)"})
  public void testEqualsWithTypeType_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(GsonTypes.equals(null, mock(GenericArrayType.class)));
  }

  /**
   * Test {@link GsonTypes#typeToString(Type)}.
   *
   * <ul>
   *   <li>When createType.
   *   <li>Then return {@code String}.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#typeToString(Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String GsonTypes.typeToString(Type)"})
  public void testTypeToString_whenCreateType_thenReturnJavaLangString() {
    // Arrange, Act and Assert
    assertEquals("java.lang.String", GsonTypes.typeToString(GsonBuilderTestFactory.createType()));
  }

  /**
   * Test {@link GsonTypes#getArrayComponentType(Type)}.
   *
   * <ul>
   *   <li>Given createType.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#getArrayComponentType(Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Type GsonTypes.getArrayComponentType(Type)"})
  public void testGetArrayComponentType_givenCreateType() {
    // Arrange
    GenericArrayType array = mock(GenericArrayType.class);
    when(array.getGenericComponentType()).thenReturn(GsonBuilderTestFactory.createType());

    // Act
    GsonTypes.getArrayComponentType(array);

    // Assert
    verify(array).getGenericComponentType();
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Type GsonTypes.getArrayComponentType(Type)"})
  public void testGetArrayComponentType_thenThrowUnsupportedOperationException() {
    // Arrange
    GenericArrayType array = mock(GenericArrayType.class);
    when(array.getGenericComponentType()).thenThrow(new UnsupportedOperationException());

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> GsonTypes.getArrayComponentType(array));
    verify(array).getGenericComponentType();
  }

  /**
   * Test {@link GsonTypes#getArrayComponentType(Type)}.
   *
   * <ul>
   *   <li>When createType.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#getArrayComponentType(Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Type GsonTypes.getArrayComponentType(Type)"})
  public void testGetArrayComponentType_whenCreateType_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(GsonTypes.getArrayComponentType(GsonBuilderTestFactory.createType()));
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Type GsonTypes.getCollectionElementType(Type, Class)"})
  public void testGetCollectionElementType_thenThrowIllegalArgumentException() {
    // Arrange
    Type context = GsonBuilderTestFactory.createType();
    Class<Object> contextRawType = Object.class;

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> GsonTypes.getCollectionElementType(context, contextRawType));
  }

  /**
   * Test {@link GsonTypes#getCollectionElementType(Type, Class)}.
   *
   * <ul>
   *   <li>When {@code Collection}.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#getCollectionElementType(Type, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Type GsonTypes.getCollectionElementType(Type, Class)"})
  public void testGetCollectionElementType_whenJavaUtilCollection_thenDoesNotThrow() {
    // Arrange
    Type context = GsonBuilderTestFactory.createType();
    Class<Collection> contextRawType = Collection.class;

    // Act
    GsonTypes.getCollectionElementType(context, contextRawType);
  }

  /**
   * Test {@link GsonTypes#getCollectionElementType(Type, Class)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#getCollectionElementType(Type, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Type GsonTypes.getCollectionElementType(Type, Class)"})
  public void testGetCollectionElementType_whenNull_thenDoesNotThrow() {
    // Arrange
    Class<Collection> contextRawType = Collection.class;

    // Act
    GsonTypes.getCollectionElementType(null, contextRawType);
  }

  /**
   * Test {@link GsonTypes#getMapKeyAndValueTypes(Type, Class)}.
   *
   * <ul>
   *   <li>Then return first element is createType.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#getMapKeyAndValueTypes(Type, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Type[] GsonTypes.getMapKeyAndValueTypes(Type, Class)"})
  public void testGetMapKeyAndValueTypes_thenReturnFirstElementIsCreateType() {
    // Arrange
    Type context = GsonBuilderTestFactory.createType();
    Class<Properties> contextRawType = Properties.class;

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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Type[] GsonTypes.getMapKeyAndValueTypes(Type, Class)"})
  public void testGetMapKeyAndValueTypes_whenJavaLangObject_thenThrowIllegalArgumentException() {
    // Arrange
    Type context = GsonBuilderTestFactory.createType();
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
   *   <li>Then return array length is two.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#getMapKeyAndValueTypes(Type, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Type[] GsonTypes.getMapKeyAndValueTypes(Type, Class)"})
  public void testGetMapKeyAndValueTypes_whenJavaUtilMap_thenReturnArrayLengthIsTwo() {
    // Arrange
    Type context = GsonBuilderTestFactory.createType();
    Class<Map> contextRawType = Map.class;

    // Act and Assert
    assertEquals(2, GsonTypes.getMapKeyAndValueTypes(context, contextRawType).length);
  }

  /**
   * Test {@link GsonTypes#getMapKeyAndValueTypes(Type, Class)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return array length is two.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#getMapKeyAndValueTypes(Type, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Type[] GsonTypes.getMapKeyAndValueTypes(Type, Class)"})
  public void testGetMapKeyAndValueTypes_whenNull_thenReturnArrayLengthIsTwo() {
    // Arrange
    Class<Map> contextRawType = Map.class;

    // Act
    Type[] actualMapKeyAndValueTypes = GsonTypes.getMapKeyAndValueTypes(null, contextRawType);

    // Assert
    assertEquals(2, actualMapKeyAndValueTypes.length);
  }

  /**
   * Test {@link GsonTypes#resolve(Type, Class, Type)} with {@code context}, {@code contextRawType},
   * {@code toResolve}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#resolve(Type, Class, Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Type GsonTypes.resolve(Type, Class, Type)"})
  public void testResolveWithContextContextRawTypeToResolve_whenNull_thenReturnNull() {
    // Arrange
    Type context = GsonBuilderTestFactory.createType();
    Class<Object> contextRawType = Object.class;

    // Act
    Type actualResolveResult = GsonTypes.resolve(context, contextRawType, null);

    // Assert
    assertNull(actualResolveResult);
  }

  /**
   * Test {@link GsonTypes#requiresOwnerType(Type)}.
   *
   * <ul>
   *   <li>When createType.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#requiresOwnerType(Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean GsonTypes.requiresOwnerType(Type)"})
  public void testRequiresOwnerType_whenCreateType() {
    // Arrange, Act and Assert
    assertFalse(GsonTypes.requiresOwnerType(GsonBuilderTestFactory.createType()));
  }

  /**
   * Test {@link GsonTypes#requiresOwnerType(Type)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link GsonTypes#requiresOwnerType(Type)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean GsonTypes.requiresOwnerType(Type)"})
  public void testRequiresOwnerType_whenNull() {
    // Arrange, Act and Assert
    assertFalse(GsonTypes.requiresOwnerType(null));
  }
}
