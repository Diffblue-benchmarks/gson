package com.google.gson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.internal.reflect.ReflectionHelperDiffblueTestFactory;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class FieldAttributesDiffblueTest {
  /**
   * Test {@link FieldAttributes#getName()}.
   *
   * <p>Method under test: {@link FieldAttributes#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String FieldAttributes.getName()"})
  public void testGetName() throws NoSuchFieldException {
    // Arrange, Act and Assert
    assertEquals(
        "annotatedField",
        FieldAttributesDiffblueTestFactory.createFieldAttributesWithAnnotation().getName());
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
    // Arrange and Act
    Class<?> actualDeclaredClass =
        FieldAttributesDiffblueTestFactory.createFieldAttributesWithAnnotation().getDeclaredClass();

    // Assert
    Class<String> expectedDeclaredClass = String.class;
    assertEquals(expectedDeclaredClass, actualDeclaredClass);
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
    FieldAttributes createFieldAttributesWithAnnotationResult =
        FieldAttributesDiffblueTestFactory.createFieldAttributesWithAnnotation();
    Class<Annotation> annotation = Annotation.class;

    // Act and Assert
    assertNull(createFieldAttributesWithAnnotationResult.getAnnotation(annotation));
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
        FieldAttributesDiffblueTestFactory.createFieldAttributesWithAnnotation().getAnnotations();

    // Assert
    assertTrue(actualAnnotations instanceof List);
    assertEquals(1, actualAnnotations.size());
  }

  /**
   * Test {@link FieldAttributes#hasModifier(int)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link FieldAttributes#hasModifier(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FieldAttributes.hasModifier(int)"})
  public void testHasModifier_whenMinusOne_thenReturnTrue() throws NoSuchFieldException {
    // Arrange, Act and Assert
    assertTrue(
        FieldAttributesDiffblueTestFactory.createFieldAttributesWithAnnotation().hasModifier(-1));
  }

  /**
   * Test {@link FieldAttributes#hasModifier(int)}.
   *
   * <ul>
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
  public void testHasModifier_whenOne_thenReturnFalse() throws NoSuchFieldException {
    // Arrange, Act and Assert
    assertFalse(
        FieldAttributesDiffblueTestFactory.createFieldAttributesWithAnnotation().hasModifier(1));
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
  public void testToString() throws NoSuchFieldException {
    // Arrange, Act and Assert
    assertEquals(
        "private java.lang.String"
            + " com.google.gson.internal.reflect.ReflectionHelperDiffblueTestFactory$TestClass"
            + ".testField",
        new FieldAttributes(ReflectionHelperDiffblueTestFactory.createFieldForGetAccessor())
            .toString());
  }
}
