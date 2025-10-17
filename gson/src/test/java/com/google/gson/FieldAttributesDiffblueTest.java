package com.google.gson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.internal.reflect.ReflectionHelperTestFactory;
import com.google.gson.internal.reflect.ReflectionHelperTestFactory.TestClass;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class FieldAttributesDiffblueTest {
  /**
   * Test {@link FieldAttributes#FieldAttributes(Field)}.
   *
   * <ul>
   *   <li>When createPublicField.
   *   <li>Then Annotations return {@link List}.
   * </ul>
   *
   * <p>Method under test: {@link FieldAttributes#FieldAttributes(Field)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FieldAttributes.<init>(Field)"})
  public void testNewFieldAttributes_whenCreatePublicField_thenAnnotationsReturnList()
      throws NoSuchFieldException {
    // Arrange and Act
    FieldAttributes actualFieldAttributes =
        new FieldAttributes(ReflectionHelperTestFactory.createPublicField());

    // Assert
    Collection<Annotation> annotations = actualFieldAttributes.getAnnotations();
    assertTrue(annotations instanceof List);
    assertEquals("int", actualFieldAttributes.getDeclaredClass().getName());
    assertEquals("publicField", actualFieldAttributes.getName());
    assertTrue(annotations.isEmpty());
    Class<TestClass> expectedDeclaringClass = TestClass.class;
    assertEquals(expectedDeclaringClass, actualFieldAttributes.getDeclaringClass());
  }

  /**
   * Test {@link FieldAttributes#getDeclaringClass()}.
   *
   * <p>Method under test: {@link FieldAttributes#getDeclaringClass()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class FieldAttributes.getDeclaringClass()"})
  public void testGetDeclaringClass() throws NoSuchFieldException {
    // Arrange and Act
    Class<?> actualDeclaringClass =
        FieldAttributesTestFactory.createFieldAttributesWithPublicField().getDeclaringClass();

    // Assert
    Class<FieldAttributesTestFactory.TestClass> expectedDeclaringClass =
        FieldAttributesTestFactory.TestClass.class;
    assertEquals(expectedDeclaringClass, actualDeclaringClass);
  }

  /**
   * Test {@link FieldAttributes#getName()}.
   *
   * <p>Method under test: {@link FieldAttributes#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String FieldAttributes.getName()"})
  public void testGetName() throws NoSuchFieldException {
    // Arrange, Act and Assert
    assertEquals(
        "intField", FieldAttributesTestFactory.createFieldAttributesWithPublicField().getName());
  }

  /**
   * Test {@link FieldAttributes#getDeclaredClass()}.
   *
   * <p>Method under test: {@link FieldAttributes#getDeclaredClass()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class FieldAttributes.getDeclaredClass()"})
  public void testGetDeclaredClass() throws NoSuchFieldException {
    // Arrange, Act and Assert
    assertEquals(
        "int",
        FieldAttributesTestFactory.createFieldAttributesWithPublicField()
            .getDeclaredClass()
            .getName());
  }

  /**
   * Test {@link FieldAttributes#getAnnotation(Class)}.
   *
   * <ul>
   *   <li>When {@code Annotation}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link FieldAttributes#getAnnotation(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Annotation FieldAttributes.getAnnotation(Class)"})
  public void testGetAnnotation_whenJavaLangAnnotationAnnotation_thenReturnNull()
      throws NoSuchFieldException {
    // Arrange
    FieldAttributes createFieldAttributesWithPublicFieldResult =
        FieldAttributesTestFactory.createFieldAttributesWithPublicField();
    Class<Annotation> annotation = Annotation.class;

    // Act and Assert
    assertNull(createFieldAttributesWithPublicFieldResult.getAnnotation(annotation));
  }

  /**
   * Test {@link FieldAttributes#getAnnotations()}.
   *
   * <p>Method under test: {@link FieldAttributes#getAnnotations()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection FieldAttributes.getAnnotations()"})
  public void testGetAnnotations() throws NoSuchFieldException {
    // Arrange and Act
    Collection<Annotation> actualAnnotations =
        FieldAttributesTestFactory.createFieldAttributesWithPublicField().getAnnotations();

    // Assert
    assertTrue(actualAnnotations instanceof List);
    assertTrue(actualAnnotations.isEmpty());
  }

  /**
   * Test {@link FieldAttributes#hasModifier(int)}.
   *
   * <ul>
   *   <li>Given createFieldAttributesWithProtectedField.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link FieldAttributes#hasModifier(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FieldAttributes.hasModifier(int)"})
  public void testHasModifier_givenCreateFieldAttributesWithProtectedField_thenReturnFalse()
      throws NoSuchFieldException {
    // Arrange, Act and Assert
    assertFalse(
        FieldAttributesTestFactory.createFieldAttributesWithProtectedField().hasModifier(1));
  }

  /**
   * Test {@link FieldAttributes#hasModifier(int)}.
   *
   * <ul>
   *   <li>Given createFieldAttributesWithPublicField.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link FieldAttributes#hasModifier(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FieldAttributes.hasModifier(int)"})
  public void testHasModifier_givenCreateFieldAttributesWithPublicField_thenReturnTrue()
      throws NoSuchFieldException {
    // Arrange, Act and Assert
    assertTrue(FieldAttributesTestFactory.createFieldAttributesWithPublicField().hasModifier(1));
  }

  /**
   * Test {@link FieldAttributes#toString()}.
   *
   * <p>Method under test: {@link FieldAttributes#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String FieldAttributes.toString()"})
  public void testToString() throws NoSuchFieldException {
    // Arrange, Act and Assert
    assertEquals(
        "public int com.google.gson.internal.reflect.ReflectionHelperTestFactory$TestClass.publicField",
        new FieldAttributes(ReflectionHelperTestFactory.createPublicField()).toString());
  }
}
