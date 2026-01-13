/*
 * Copyright (C) 2009 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.gson;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link FieldAttributes}.
 *
 * @author Claude
 */
public class FieldAttributesClaudeTest {

  /** Custom annotation for testing annotation retrieval. */
  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.FIELD)
  private @interface TestAnnotation {
    String value() default "";
  }

  /** Second custom annotation for testing multiple annotations. */
  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.FIELD)
  private @interface AnotherAnnotation {
    int number() default 0;
  }

  /** Test class with various field types and modifiers. */
  @SuppressWarnings("unused")
  private static class TestClass {
    public String publicField;
    private int privateField;
    protected double protectedField;
    String packagePrivateField;

    public static final String CONSTANT = "constant";
    private transient String transientField;
    private volatile int volatileField;

    @TestAnnotation("test")
    private String annotatedField;

    @TestAnnotation("multi")
    @AnotherAnnotation(number = 42)
    private String multiAnnotatedField;

    private List<String> genericListField;
    private Map<String, Integer> genericMapField;
    private String simpleField;
    private int primitiveField;
  }

  private Field publicField;
  private Field privateField;
  private Field protectedField;
  private Field packagePrivateField;
  private Field constantField;
  private Field transientField;
  private Field volatileField;
  private Field annotatedField;
  private Field multiAnnotatedField;
  private Field genericListField;
  private Field genericMapField;
  private Field simpleField;
  private Field primitiveField;

  @Before
  public void setUp() throws NoSuchFieldException {
    publicField = TestClass.class.getDeclaredField("publicField");
    privateField = TestClass.class.getDeclaredField("privateField");
    protectedField = TestClass.class.getDeclaredField("protectedField");
    packagePrivateField = TestClass.class.getDeclaredField("packagePrivateField");
    constantField = TestClass.class.getDeclaredField("CONSTANT");
    transientField = TestClass.class.getDeclaredField("transientField");
    volatileField = TestClass.class.getDeclaredField("volatileField");
    annotatedField = TestClass.class.getDeclaredField("annotatedField");
    multiAnnotatedField = TestClass.class.getDeclaredField("multiAnnotatedField");
    genericListField = TestClass.class.getDeclaredField("genericListField");
    genericMapField = TestClass.class.getDeclaredField("genericMapField");
    simpleField = TestClass.class.getDeclaredField("simpleField");
    primitiveField = TestClass.class.getDeclaredField("primitiveField");
  }

  // ==========================================================================
  // Constructor tests
  // ==========================================================================

  @Test
  public void testConstructor_nullField_throwsNullPointerException() {
    assertThrows(NullPointerException.class, () -> new FieldAttributes(null));
  }

  @Test
  public void testConstructor_validField_succeeds() {
    FieldAttributes fa = new FieldAttributes(publicField);
    assertThat(fa).isNotNull();
  }

  // ==========================================================================
  // getDeclaringClass tests
  // ==========================================================================

  @Test
  public void testGetDeclaringClass_returnsDeclaringClass() {
    FieldAttributes fa = new FieldAttributes(publicField);
    assertThat(fa.getDeclaringClass()).isEqualTo(TestClass.class);
  }

  @Test
  public void testGetDeclaringClass_differentField_returnsSameDeclaringClass() {
    FieldAttributes fa1 = new FieldAttributes(publicField);
    FieldAttributes fa2 = new FieldAttributes(privateField);
    assertThat(fa1.getDeclaringClass()).isEqualTo(fa2.getDeclaringClass());
  }

  // ==========================================================================
  // getName tests
  // ==========================================================================

  @Test
  public void testGetName_publicField_returnsCorrectName() {
    FieldAttributes fa = new FieldAttributes(publicField);
    assertThat(fa.getName()).isEqualTo("publicField");
  }

  @Test
  public void testGetName_privateField_returnsCorrectName() {
    FieldAttributes fa = new FieldAttributes(privateField);
    assertThat(fa.getName()).isEqualTo("privateField");
  }

  @Test
  public void testGetName_constantField_returnsCorrectName() {
    FieldAttributes fa = new FieldAttributes(constantField);
    assertThat(fa.getName()).isEqualTo("CONSTANT");
  }

  @Test
  public void testGetName_genericField_returnsCorrectName() {
    FieldAttributes fa = new FieldAttributes(genericListField);
    assertThat(fa.getName()).isEqualTo("genericListField");
  }

  // ==========================================================================
  // getDeclaredType tests
  // ==========================================================================

  @Test
  public void testGetDeclaredType_simpleField_returnsClassType() {
    FieldAttributes fa = new FieldAttributes(simpleField);
    assertThat(fa.getDeclaredType()).isEqualTo(String.class);
  }

  @Test
  public void testGetDeclaredType_primitiveField_returnsPrimitiveType() {
    FieldAttributes fa = new FieldAttributes(primitiveField);
    assertThat(fa.getDeclaredType()).isEqualTo(int.class);
  }

  @Test
  public void testGetDeclaredType_genericListField_returnsParameterizedType() {
    FieldAttributes fa = new FieldAttributes(genericListField);
    Type declaredType = fa.getDeclaredType();
    assertThat(declaredType).isInstanceOf(ParameterizedType.class);
    ParameterizedType paramType = (ParameterizedType) declaredType;
    assertThat(paramType.getRawType()).isEqualTo(List.class);
    assertThat(paramType.getActualTypeArguments()[0]).isEqualTo(String.class);
  }

  @Test
  public void testGetDeclaredType_genericMapField_returnsParameterizedType() {
    FieldAttributes fa = new FieldAttributes(genericMapField);
    Type declaredType = fa.getDeclaredType();
    assertThat(declaredType).isInstanceOf(ParameterizedType.class);
    ParameterizedType paramType = (ParameterizedType) declaredType;
    assertThat(paramType.getRawType()).isEqualTo(Map.class);
    assertThat(paramType.getActualTypeArguments()).hasLength(2);
    assertThat(paramType.getActualTypeArguments()[0]).isEqualTo(String.class);
    assertThat(paramType.getActualTypeArguments()[1]).isEqualTo(Integer.class);
  }

  // ==========================================================================
  // getDeclaredClass tests
  // ==========================================================================

  @Test
  public void testGetDeclaredClass_simpleField_returnsStringClass() {
    FieldAttributes fa = new FieldAttributes(simpleField);
    assertThat(fa.getDeclaredClass()).isEqualTo(String.class);
  }

  @Test
  public void testGetDeclaredClass_primitiveField_returnsPrimitiveClass() {
    FieldAttributes fa = new FieldAttributes(primitiveField);
    assertThat(fa.getDeclaredClass()).isEqualTo(int.class);
  }

  @Test
  public void testGetDeclaredClass_genericListField_returnsRawListClass() {
    FieldAttributes fa = new FieldAttributes(genericListField);
    // getDeclaredClass returns the raw type, stripping generic parameters
    assertThat(fa.getDeclaredClass()).isEqualTo(List.class);
  }

  @Test
  public void testGetDeclaredClass_genericMapField_returnsRawMapClass() {
    FieldAttributes fa = new FieldAttributes(genericMapField);
    // getDeclaredClass returns the raw type, stripping generic parameters
    assertThat(fa.getDeclaredClass()).isEqualTo(Map.class);
  }

  @Test
  public void testGetDeclaredType_vs_getDeclaredClass_forGenericField() {
    // For generic fields, getDeclaredType returns the full generic type
    // while getDeclaredClass returns only the raw class
    FieldAttributes fa = new FieldAttributes(genericListField);

    Type declaredType = fa.getDeclaredType();
    Class<?> declaredClass = fa.getDeclaredClass();

    assertThat(declaredType).isNotEqualTo(declaredClass);
    assertThat(declaredType).isInstanceOf(ParameterizedType.class);
    assertThat(declaredClass).isEqualTo(List.class);
  }

  @Test
  public void testGetDeclaredType_vs_getDeclaredClass_forSimpleField() {
    // For non-generic fields, both methods return the same result
    FieldAttributes fa = new FieldAttributes(simpleField);

    Type declaredType = fa.getDeclaredType();
    Class<?> declaredClass = fa.getDeclaredClass();

    assertThat(declaredType).isEqualTo(declaredClass);
  }

  // ==========================================================================
  // getAnnotation tests
  // ==========================================================================

  @Test
  public void testGetAnnotation_annotatedField_returnsAnnotation() {
    FieldAttributes fa = new FieldAttributes(annotatedField);
    TestAnnotation annotation = fa.getAnnotation(TestAnnotation.class);
    assertThat(annotation).isNotNull();
    assertThat(annotation.value()).isEqualTo("test");
  }

  @Test
  public void testGetAnnotation_multiAnnotatedField_returnsCorrectAnnotation() {
    FieldAttributes fa = new FieldAttributes(multiAnnotatedField);

    TestAnnotation testAnnotation = fa.getAnnotation(TestAnnotation.class);
    assertThat(testAnnotation).isNotNull();
    assertThat(testAnnotation.value()).isEqualTo("multi");

    AnotherAnnotation anotherAnnotation = fa.getAnnotation(AnotherAnnotation.class);
    assertThat(anotherAnnotation).isNotNull();
    assertThat(anotherAnnotation.number()).isEqualTo(42);
  }

  @Test
  public void testGetAnnotation_unannotatedField_returnsNull() {
    FieldAttributes fa = new FieldAttributes(simpleField);
    TestAnnotation annotation = fa.getAnnotation(TestAnnotation.class);
    assertThat(annotation).isNull();
  }

  @Test
  public void testGetAnnotation_missingAnnotation_returnsNull() {
    FieldAttributes fa = new FieldAttributes(annotatedField);
    // annotatedField only has TestAnnotation, not AnotherAnnotation
    AnotherAnnotation annotation = fa.getAnnotation(AnotherAnnotation.class);
    assertThat(annotation).isNull();
  }

  // ==========================================================================
  // getAnnotations tests
  // ==========================================================================

  @Test
  public void testGetAnnotations_annotatedField_returnsCollectionWithOneAnnotation() {
    FieldAttributes fa = new FieldAttributes(annotatedField);
    Collection<Annotation> annotations = fa.getAnnotations();
    assertThat(annotations).hasSize(1);
    assertThat(annotations.iterator().next()).isInstanceOf(TestAnnotation.class);
  }

  @Test
  public void testGetAnnotations_multiAnnotatedField_returnsCollectionWithMultipleAnnotations() {
    FieldAttributes fa = new FieldAttributes(multiAnnotatedField);
    Collection<Annotation> annotations = fa.getAnnotations();
    assertThat(annotations).hasSize(2);
  }

  @Test
  public void testGetAnnotations_unannotatedField_returnsEmptyCollection() {
    FieldAttributes fa = new FieldAttributes(simpleField);
    Collection<Annotation> annotations = fa.getAnnotations();
    assertThat(annotations).isEmpty();
  }

  @Test
  public void testGetAnnotations_returnsCollection() {
    FieldAttributes fa = new FieldAttributes(annotatedField);
    assertThat(fa.getAnnotations()).isInstanceOf(Collection.class);
  }

  // ==========================================================================
  // hasModifier tests
  // ==========================================================================

  @Test
  public void testHasModifier_publicField_hasPublicModifier() {
    FieldAttributes fa = new FieldAttributes(publicField);
    assertThat(fa.hasModifier(Modifier.PUBLIC)).isTrue();
    assertThat(fa.hasModifier(Modifier.PRIVATE)).isFalse();
    assertThat(fa.hasModifier(Modifier.PROTECTED)).isFalse();
  }

  @Test
  public void testHasModifier_privateField_hasPrivateModifier() {
    FieldAttributes fa = new FieldAttributes(privateField);
    assertThat(fa.hasModifier(Modifier.PRIVATE)).isTrue();
    assertThat(fa.hasModifier(Modifier.PUBLIC)).isFalse();
    assertThat(fa.hasModifier(Modifier.PROTECTED)).isFalse();
  }

  @Test
  public void testHasModifier_protectedField_hasProtectedModifier() {
    FieldAttributes fa = new FieldAttributes(protectedField);
    assertThat(fa.hasModifier(Modifier.PROTECTED)).isTrue();
    assertThat(fa.hasModifier(Modifier.PUBLIC)).isFalse();
    assertThat(fa.hasModifier(Modifier.PRIVATE)).isFalse();
  }

  @Test
  public void testHasModifier_packagePrivateField_hasNoAccessModifier() {
    FieldAttributes fa = new FieldAttributes(packagePrivateField);
    assertThat(fa.hasModifier(Modifier.PUBLIC)).isFalse();
    assertThat(fa.hasModifier(Modifier.PRIVATE)).isFalse();
    assertThat(fa.hasModifier(Modifier.PROTECTED)).isFalse();
  }

  @Test
  public void testHasModifier_staticField_hasStaticModifier() {
    FieldAttributes fa = new FieldAttributes(constantField);
    assertThat(fa.hasModifier(Modifier.STATIC)).isTrue();
  }

  @Test
  public void testHasModifier_finalField_hasFinalModifier() {
    FieldAttributes fa = new FieldAttributes(constantField);
    assertThat(fa.hasModifier(Modifier.FINAL)).isTrue();
  }

  @Test
  public void testHasModifier_staticFinalField_hasBothModifiers() {
    FieldAttributes fa = new FieldAttributes(constantField);
    assertThat(fa.hasModifier(Modifier.STATIC)).isTrue();
    assertThat(fa.hasModifier(Modifier.FINAL)).isTrue();
    assertThat(fa.hasModifier(Modifier.PUBLIC)).isTrue();
  }

  @Test
  public void testHasModifier_transientField_hasTransientModifier() {
    FieldAttributes fa = new FieldAttributes(transientField);
    assertThat(fa.hasModifier(Modifier.TRANSIENT)).isTrue();
  }

  @Test
  public void testHasModifier_volatileField_hasVolatileModifier() {
    FieldAttributes fa = new FieldAttributes(volatileField);
    assertThat(fa.hasModifier(Modifier.VOLATILE)).isTrue();
  }

  @Test
  public void testHasModifier_nonStaticField_doesNotHaveStaticModifier() {
    FieldAttributes fa = new FieldAttributes(simpleField);
    assertThat(fa.hasModifier(Modifier.STATIC)).isFalse();
  }

  @Test
  public void testHasModifier_nonFinalField_doesNotHaveFinalModifier() {
    FieldAttributes fa = new FieldAttributes(simpleField);
    assertThat(fa.hasModifier(Modifier.FINAL)).isFalse();
  }

  @Test
  public void testHasModifier_combinedModifiers() {
    // Test that we can check for multiple modifiers that are present
    FieldAttributes fa = new FieldAttributes(constantField);
    // CONSTANT is public static final
    assertThat(fa.hasModifier(Modifier.PUBLIC | Modifier.STATIC)).isTrue();
    assertThat(fa.hasModifier(Modifier.PUBLIC | Modifier.FINAL)).isTrue();
    assertThat(fa.hasModifier(Modifier.STATIC | Modifier.FINAL)).isTrue();
  }

  // ==========================================================================
  // toString tests
  // ==========================================================================

  @Test
  public void testToString_containsFieldName() {
    FieldAttributes fa = new FieldAttributes(publicField);
    assertThat(fa.toString()).contains("publicField");
  }

  @Test
  public void testToString_containsDeclaringClassName() {
    FieldAttributes fa = new FieldAttributes(publicField);
    assertThat(fa.toString()).contains("TestClass");
  }

  @Test
  public void testToString_containsFieldType() {
    FieldAttributes fa = new FieldAttributes(publicField);
    assertThat(fa.toString()).contains("String");
  }

  @Test
  public void testToString_forGenericField_containsGenericInfo() {
    FieldAttributes fa = new FieldAttributes(genericListField);
    String toString = fa.toString();
    assertThat(toString).contains("List");
    assertThat(toString).contains("genericListField");
  }

  @Test
  public void testToString_differentFields_produceDifferentStrings() {
    FieldAttributes fa1 = new FieldAttributes(publicField);
    FieldAttributes fa2 = new FieldAttributes(privateField);
    assertThat(fa1.toString()).isNotEqualTo(fa2.toString());
  }

  @Test
  public void testToString_returnsConsistentResult() {
    FieldAttributes fa = new FieldAttributes(publicField);
    String firstCall = fa.toString();
    String secondCall = fa.toString();
    assertThat(firstCall).isEqualTo(secondCall);
  }
}
