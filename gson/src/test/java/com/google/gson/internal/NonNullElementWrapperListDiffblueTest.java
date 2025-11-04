package com.google.gson.internal;

import static org.junit.Assert.assertEquals;

import com.diffblue.cover.annotations.MaintainedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class NonNullElementWrapperListDiffblueTest {
  /**
   * Test {@link NonNullElementWrapperList#NonNullElementWrapperList(ArrayList)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#NonNullElementWrapperList(ArrayList)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void NonNullElementWrapperList.<init>(ArrayList)"})
  public void testNewNonNullElementWrapperList_given42_whenArrayListAdd42() {
    // Arrange
    ArrayList<Object> delegate = new ArrayList<>();
    delegate.add("42");

    // Act and Assert
    assertEquals(delegate, new NonNullElementWrapperList<>(delegate));
  }

  /**
   * Test {@link NonNullElementWrapperList#NonNullElementWrapperList(ArrayList)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#NonNullElementWrapperList(ArrayList)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void NonNullElementWrapperList.<init>(ArrayList)"})
  public void testNewNonNullElementWrapperList_given42_whenArrayListAdd422() {
    // Arrange
    ArrayList<Object> delegate = new ArrayList<>();
    delegate.add("42");
    delegate.add("42");

    // Act and Assert
    assertEquals(delegate, new NonNullElementWrapperList<>(delegate));
  }

  /**
   * Test {@link NonNullElementWrapperList#NonNullElementWrapperList(ArrayList)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#NonNullElementWrapperList(ArrayList)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void NonNullElementWrapperList.<init>(ArrayList)"})
  public void testNewNonNullElementWrapperList_whenArrayList() {
    // Arrange
    ArrayList<Object> delegate = new ArrayList<>();

    // Act and Assert
    assertEquals(delegate, new NonNullElementWrapperList<>(delegate));
  }
}
