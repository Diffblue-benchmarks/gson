package com.google.gson.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import org.junit.Test;

public class NonNullElementWrapperListDiffblueTest {
  /** Method under test: {@link NonNullElementWrapperList#NonNullElementWrapperList(ArrayList)} */
  @Test
  public void testNewNonNullElementWrapperList() {
    // Arrange and Act
    NonNullElementWrapperList<Object> actualObjectList =
        new NonNullElementWrapperList<>(new ArrayList<>());

    // Assert
    assertTrue(actualObjectList.isEmpty());
  }

  /** Method under test: {@link NonNullElementWrapperList#NonNullElementWrapperList(ArrayList)} */
  @Test
  public void testNewNonNullElementWrapperList2() {
    // Arrange
    ArrayList<Object> delegate = new ArrayList<>();
    delegate.add("42");

    // Act
    NonNullElementWrapperList<Object> actualObjectList = new NonNullElementWrapperList<>(delegate);

    // Assert
    assertEquals(1, actualObjectList.size());
    assertEquals("42", actualObjectList.get(0));
  }

  /** Method under test: {@link NonNullElementWrapperList#NonNullElementWrapperList(ArrayList)} */
  @Test
  public void testNewNonNullElementWrapperList3() {
    // Arrange
    ArrayList<Object> delegate = new ArrayList<>();
    delegate.add("42");
    delegate.add("42");

    // Act and Assert
    assertEquals(delegate, new NonNullElementWrapperList<>(delegate));
  }
}
