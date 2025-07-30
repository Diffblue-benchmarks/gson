package com.google.gson;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class FieldNamingPolicyDiffblueTest {
  /**
   * Test {@link FieldNamingPolicy#separateCamelCase(String, char)}.
   *
   * <ul>
   *   <li>When {@code IDENTITY}.
   *   <li>Then return {@code IADAEANATAIATAY}.
   * </ul>
   *
   * <p>Method under test: {@link FieldNamingPolicy#separateCamelCase(String, char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String FieldNamingPolicy.separateCamelCase(String, char)"})
  public void testSeparateCamelCase_whenIdentity_thenReturnIadaeanataiatay() {
    // Arrange, Act and Assert
    assertEquals("IADAEANATAIATAY", FieldNamingPolicy.separateCamelCase("IDENTITY", 'A'));
  }

  /**
   * Test {@link FieldNamingPolicy#separateCamelCase(String, char)}.
   *
   * <ul>
   *   <li>When {@code Name}.
   *   <li>Then return {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link FieldNamingPolicy#separateCamelCase(String, char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String FieldNamingPolicy.separateCamelCase(String, char)"})
  public void testSeparateCamelCase_whenName_thenReturnName() {
    // Arrange, Act and Assert
    assertEquals("Name", FieldNamingPolicy.separateCamelCase("Name", 'A'));
  }

  /**
   * Test {@link FieldNamingPolicy#upperCaseFirstLetter(String)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link FieldNamingPolicy#upperCaseFirstLetter(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String FieldNamingPolicy.upperCaseFirstLetter(String)"})
  public void testUpperCaseFirstLetter_when42_thenReturn42() {
    // Arrange, Act and Assert
    assertEquals("42", FieldNamingPolicy.upperCaseFirstLetter("42"));
  }

  /**
   * Test {@link FieldNamingPolicy#upperCaseFirstLetter(String)}.
   *
   * <ul>
   *   <li>When {@code 42foo}.
   *   <li>Then return {@code 42Foo}.
   * </ul>
   *
   * <p>Method under test: {@link FieldNamingPolicy#upperCaseFirstLetter(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String FieldNamingPolicy.upperCaseFirstLetter(String)"})
  public void testUpperCaseFirstLetter_when42foo_thenReturn42Foo() {
    // Arrange, Act and Assert
    assertEquals("42Foo", FieldNamingPolicy.upperCaseFirstLetter("42foo"));
  }

  /**
   * Test {@link FieldNamingPolicy#upperCaseFirstLetter(String)}.
   *
   * <ul>
   *   <li>When {@code foo}.
   *   <li>Then return {@code Foo}.
   * </ul>
   *
   * <p>Method under test: {@link FieldNamingPolicy#upperCaseFirstLetter(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String FieldNamingPolicy.upperCaseFirstLetter(String)"})
  public void testUpperCaseFirstLetter_whenFoo_thenReturnFoo() {
    // Arrange, Act and Assert
    assertEquals("Foo", FieldNamingPolicy.upperCaseFirstLetter("foo"));
  }

  /**
   * Test {@link FieldNamingPolicy#upperCaseFirstLetter(String)}.
   *
   * <ul>
   *   <li>When {@code IDENTITY}.
   *   <li>Then return {@code IDENTITY}.
   * </ul>
   *
   * <p>Method under test: {@link FieldNamingPolicy#upperCaseFirstLetter(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String FieldNamingPolicy.upperCaseFirstLetter(String)"})
  public void testUpperCaseFirstLetter_whenIdentity_thenReturnIdentity() {
    // Arrange, Act and Assert
    assertEquals("IDENTITY", FieldNamingPolicy.upperCaseFirstLetter("IDENTITY"));
  }
}
