package com.google.gson.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NonNullElementWrapperList.<init>(ArrayList)"})
  public void testNewNonNullElementWrapperList_whenArrayList() {
    // Arrange
    ArrayList<Object> delegate = new ArrayList<>();

    // Act and Assert
    assertEquals(delegate, new NonNullElementWrapperList<>(delegate));
  }

  /**
   * Test {@link NonNullElementWrapperList#remove(Object)} with {@code o}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code 42}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#remove(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NonNullElementWrapperList.remove(Object)"})
  public void testRemoveWithO_givenArrayListAdd42_thenReturnTrue() {
    // Arrange
    ArrayList<Object> delegate = new ArrayList<>();
    delegate.add("42");
    NonNullElementWrapperList<Object> objectList = new NonNullElementWrapperList<>(delegate);

    // Act and Assert
    assertTrue(objectList.remove("42"));
    assertTrue(objectList.isEmpty());
  }

  /**
   * Test {@link NonNullElementWrapperList#remove(Object)} with {@code o}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#remove(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NonNullElementWrapperList.remove(Object)"})
  public void testRemoveWithO_thenReturnFalse() {
    // Arrange
    NonNullElementWrapperList<Object> objectList =
        new NonNullElementWrapperList<>(new ArrayList<>());

    // Act and Assert
    assertFalse(objectList.remove("42"));
    assertTrue(objectList.isEmpty());
  }

  /**
   * Test {@link NonNullElementWrapperList#contains(Object)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code 42}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#contains(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NonNullElementWrapperList.contains(Object)"})
  public void testContains_givenArrayListAdd42_thenReturnTrue() {
    // Arrange
    ArrayList<Object> delegate = new ArrayList<>();
    delegate.add("42");
    NonNullElementWrapperList<Object> objectList = new NonNullElementWrapperList<>(delegate);

    // Act and Assert
    assertTrue(objectList.contains("42"));
  }

  /**
   * Test {@link NonNullElementWrapperList#contains(Object)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#contains(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NonNullElementWrapperList.contains(Object)"})
  public void testContains_thenReturnFalse() {
    // Arrange
    NonNullElementWrapperList<Object> objectList =
        new NonNullElementWrapperList<>(new ArrayList<>());

    // Act and Assert
    assertFalse(objectList.contains("42"));
  }

  /**
   * Test {@link NonNullElementWrapperList#indexOf(Object)}.
   *
   * <p>Method under test: {@link NonNullElementWrapperList#indexOf(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int NonNullElementWrapperList.indexOf(Object)"})
  public void testIndexOf() {
    // Arrange
    ArrayList<Object> delegate = new ArrayList<>();
    delegate.add("Delegate");
    NonNullElementWrapperList<Object> objectList = new NonNullElementWrapperList<>(delegate);

    // Act and Assert
    assertEquals(-1, objectList.indexOf("42"));
  }

  /**
   * Test {@link NonNullElementWrapperList#lastIndexOf(Object)}.
   *
   * <p>Method under test: {@link NonNullElementWrapperList#lastIndexOf(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int NonNullElementWrapperList.lastIndexOf(Object)"})
  public void testLastIndexOf() {
    // Arrange
    ArrayList<Object> delegate = new ArrayList<>();
    delegate.add("Delegate");
    NonNullElementWrapperList<Object> objectList = new NonNullElementWrapperList<>(delegate);

    // Act and Assert
    assertEquals(-1, objectList.lastIndexOf("42"));
  }
}
