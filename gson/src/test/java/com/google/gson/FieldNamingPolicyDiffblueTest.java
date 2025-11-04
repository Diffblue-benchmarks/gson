package com.google.gson;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FieldNamingPolicyDiffblueTest {
  /** Method under test: {@link FieldNamingPolicy#separateCamelCase(String, char)} */
  @Test
  public void testSeparateCamelCase() {
    // Arrange, Act and Assert
    assertEquals("Name", FieldNamingPolicy.separateCamelCase("Name", 'A'));
    assertEquals("IADAEANATAIATAY", FieldNamingPolicy.separateCamelCase("IDENTITY", 'A'));
  }

  /** Method under test: {@link FieldNamingPolicy#upperCaseFirstLetter(String)} */
  @Test
  public void testUpperCaseFirstLetter() {
    // Arrange, Act and Assert
    assertEquals("Foo", FieldNamingPolicy.upperCaseFirstLetter("foo"));
    assertEquals("IDENTITY", FieldNamingPolicy.upperCaseFirstLetter("IDENTITY"));
    assertEquals("42", FieldNamingPolicy.upperCaseFirstLetter("42"));
    assertEquals("42Foo", FieldNamingPolicy.upperCaseFirstLetter("42foo"));
  }
}
