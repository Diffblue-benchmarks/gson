package com.google.gson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.internal.reflect.ReflectionHelperFactory;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class FieldAttributesDiffblueTest {
  /**
   * Test {@link FieldAttributes#getDeclaringClass()}.
   *
   * <ul>
   *   <li>Given {@link FieldAttributes#FieldAttributes(Field)} with f is {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link FieldAttributes#getDeclaringClass()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class FieldAttributes.getDeclaringClass()"})
  public void testGetDeclaringClass_givenFieldAttributesWithFIsNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new FieldAttributes(null).getDeclaringClass());
  }

  /**
   * Test {@link FieldAttributes#getName()}.
   *
   * <ul>
   *   <li>Given {@link FieldAttributes#FieldAttributes(Field)} with f is createNonRecordField.
   *   <li>Then return {@code testField}.
   * </ul>
   *
   * <p>Method under test: {@link FieldAttributes#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String FieldAttributes.getName()"})
  public void testGetName_givenFieldAttributesWithFIsCreateNonRecordField_thenReturnTestField() {
    // Arrange, Act and Assert
    assertEquals(
        "testField", new FieldAttributes(ReflectionHelperFactory.createNonRecordField()).getName());
  }

  /**
   * Test {@link FieldAttributes#getName()}.
   *
   * <ul>
   *   <li>Given {@link FieldAttributes#FieldAttributes(Field)} with f is {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link FieldAttributes#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String FieldAttributes.getName()"})
  public void testGetName_givenFieldAttributesWithFIsNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new FieldAttributes(null).getName());
  }

  /**
   * Test {@link FieldAttributes#getDeclaredType()}.
   *
   * <ul>
   *   <li>Given {@link FieldAttributes#FieldAttributes(Field)} with f is {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link FieldAttributes#getDeclaredType()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.reflect.Type FieldAttributes.getDeclaredType()"})
  public void testGetDeclaredType_givenFieldAttributesWithFIsNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new FieldAttributes(null).getDeclaredType());
  }

  /**
   * Test {@link FieldAttributes#getDeclaredClass()}.
   *
   * <ul>
   *   <li>Given {@link FieldAttributes#FieldAttributes(Field)} with f is {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link FieldAttributes#getDeclaredClass()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class FieldAttributes.getDeclaredClass()"})
  public void testGetDeclaredClass_givenFieldAttributesWithFIsNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new FieldAttributes(null).getDeclaredClass());
  }

  /**
   * Test {@link FieldAttributes#getDeclaredClass()}.
   *
   * <ul>
   *   <li>Then return {@link String}.
   * </ul>
   *
   * <p>Method under test: {@link FieldAttributes#getDeclaredClass()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class FieldAttributes.getDeclaredClass()"})
  public void testGetDeclaredClass_thenReturnString() {
    // Arrange and Act
    Class<?> actualDeclaredClass =
        new FieldAttributes(ReflectionHelperFactory.createNonRecordField()).getDeclaredClass();

    // Assert
    Class<String> expectedDeclaredClass = String.class;
    assertEquals(expectedDeclaredClass, actualDeclaredClass);
  }

  /**
   * Test {@link FieldAttributes#getAnnotation(Class)}.
   *
   * <ul>
   *   <li>Given {@link FieldAttributes#FieldAttributes(Field)} with f is createNonRecordField.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link FieldAttributes#getAnnotation(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Annotation FieldAttributes.getAnnotation(Class)"})
  public void testGetAnnotation_givenFieldAttributesWithFIsCreateNonRecordField_thenReturnNull() {
    // Arrange
    FieldAttributes fieldAttributes =
        new FieldAttributes(ReflectionHelperFactory.createNonRecordField());
    Class<Annotation> annotation = Annotation.class;

    // Act and Assert
    assertNull(fieldAttributes.getAnnotation(annotation));
  }

  /**
   * Test {@link FieldAttributes#getAnnotation(Class)}.
   *
   * <ul>
   *   <li>Given {@link FieldAttributes#FieldAttributes(Field)} with f is {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link FieldAttributes#getAnnotation(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Annotation FieldAttributes.getAnnotation(Class)"})
  public void testGetAnnotation_givenFieldAttributesWithFIsNull_thenReturnNull() {
    // Arrange
    FieldAttributes fieldAttributes = new FieldAttributes(null);
    Class<Annotation> annotation = Annotation.class;

    // Act and Assert
    assertNull(fieldAttributes.getAnnotation(annotation));
  }

  /**
   * Test {@link FieldAttributes#getAnnotations()}.
   *
   * <ul>
   *   <li>Given {@link FieldAttributes#FieldAttributes(Field)} with f is createNonRecordField.
   * </ul>
   *
   * <p>Method under test: {@link FieldAttributes#getAnnotations()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection FieldAttributes.getAnnotations()"})
  public void testGetAnnotations_givenFieldAttributesWithFIsCreateNonRecordField() {
    // Arrange and Act
    Collection<Annotation> actualAnnotations =
        new FieldAttributes(ReflectionHelperFactory.createNonRecordField()).getAnnotations();

    // Assert
    assertTrue(actualAnnotations instanceof List);
    assertTrue(actualAnnotations.isEmpty());
  }

  /**
   * Test {@link FieldAttributes#getAnnotations()}.
   *
   * <ul>
   *   <li>Given {@link FieldAttributes#FieldAttributes(Field)} with f is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link FieldAttributes#getAnnotations()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection FieldAttributes.getAnnotations()"})
  public void testGetAnnotations_givenFieldAttributesWithFIsNull() {
    // Arrange and Act
    Collection<Annotation> actualAnnotations = new FieldAttributes(null).getAnnotations();

    // Assert
    assertTrue(actualAnnotations instanceof List);
    assertTrue(actualAnnotations.isEmpty());
  }

  /**
   * Test {@link FieldAttributes#hasModifier(int)}.
   *
   * <ul>
   *   <li>Given {@link FieldAttributes#FieldAttributes(Field)} with f is createNonRecordField.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link FieldAttributes#hasModifier(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FieldAttributes.hasModifier(int)"})
  public void testHasModifier_givenFieldAttributesWithFIsCreateNonRecordField_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new FieldAttributes(ReflectionHelperFactory.createNonRecordField()).hasModifier(1));
  }

  /**
   * Test {@link FieldAttributes#hasModifier(int)}.
   *
   * <ul>
   *   <li>Given {@link FieldAttributes#FieldAttributes(Field)} with f is {@code null}.
   *   <li>When one.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link FieldAttributes#hasModifier(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FieldAttributes.hasModifier(int)"})
  public void testHasModifier_givenFieldAttributesWithFIsNull_whenOne_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new FieldAttributes(null).hasModifier(1));
  }

  /**
   * Test {@link FieldAttributes#hasModifier(int)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link FieldAttributes#hasModifier(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FieldAttributes.hasModifier(int)"})
  public void testHasModifier_whenZero_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new FieldAttributes(ReflectionHelperFactory.createNonRecordField()).hasModifier(0));
  }

  /**
   * Test {@link FieldAttributes#toString()}.
   *
   * <p>Method under test: {@link FieldAttributes#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String FieldAttributes.toString()"})
  public void testToString() {
    // Arrange, Act and Assert
    assertEquals(
        "public java.lang.String"
            + " com.google.gson.internal.reflect.ReflectionHelperFactory$TestClass.testField",
        new FieldAttributes(ReflectionHelperFactory.createNonRecordField()).toString());
  }

  /**
   * Test {@link FieldAttributes#toString()}.
   *
   * <ul>
   *   <li>Given {@link FieldAttributes#FieldAttributes(Field)} with f is {@code null}.
   *   <li>Then return {@code FieldAttributes[field=null]}.
   * </ul>
   *
   * <p>Method under test: {@link FieldAttributes#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String FieldAttributes.toString()"})
  public void testToString_givenFieldAttributesWithFIsNull_thenReturnFieldAttributesFieldNull() {
    // Arrange, Act and Assert
    assertEquals("FieldAttributes[field=null]", new FieldAttributes(null).toString());
  }
}
