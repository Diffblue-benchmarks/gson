package com.google.gson.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.internal.reflect.ReflectionHelperTestFactory;
import java.lang.reflect.Field;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class NonNullElementWrapperListDiffblueTest {
  /**
   * Test {@link NonNullElementWrapperList#NonNullElementWrapperList(ArrayList)}.
   *
   * <ul>
   *   <li>Given createPublicField.
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#NonNullElementWrapperList(ArrayList)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NonNullElementWrapperList.<init>(ArrayList)"})
  public void testNewNonNullElementWrapperList_givenCreatePublicField_thenReturnSizeIsOne()
      throws NoSuchFieldException {
    // Arrange
    ArrayList<Object> delegate = new ArrayList<>();
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();
    delegate.add(createPublicFieldResult);

    // Act
    NonNullElementWrapperList<Object> actualObjectList = new NonNullElementWrapperList<>(delegate);

    // Assert
    assertEquals(1, actualObjectList.size());
    assertSame(createPublicFieldResult, actualObjectList.get(0));
  }

  /**
   * Test {@link NonNullElementWrapperList#NonNullElementWrapperList(ArrayList)}.
   *
   * <ul>
   *   <li>Given createPublicField.
   *   <li>Then return size is two.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#NonNullElementWrapperList(ArrayList)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NonNullElementWrapperList.<init>(ArrayList)"})
  public void testNewNonNullElementWrapperList_givenCreatePublicField_thenReturnSizeIsTwo()
      throws NoSuchFieldException {
    // Arrange
    ArrayList<Object> delegate = new ArrayList<>();
    delegate.add(ReflectionHelperTestFactory.createPublicField());
    Field createPublicFieldResult = ReflectionHelperTestFactory.createPublicField();
    delegate.add(createPublicFieldResult);

    // Act
    NonNullElementWrapperList<Object> actualObjectList = new NonNullElementWrapperList<>(delegate);

    // Assert
    assertEquals(2, actualObjectList.size());
    assertSame(createPublicFieldResult, actualObjectList.get(1));
  }

  /**
   * Test {@link NonNullElementWrapperList#NonNullElementWrapperList(ArrayList)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#NonNullElementWrapperList(ArrayList)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NonNullElementWrapperList.<init>(ArrayList)"})
  public void testNewNonNullElementWrapperList_whenArrayList_thenReturnArrayList() {
    // Arrange
    ArrayList<Object> delegate = new ArrayList<>();

    // Act
    NonNullElementWrapperList<Object> actualObjectList = new NonNullElementWrapperList<>(delegate);

    // Assert
    assertEquals(delegate, actualObjectList);
  }

  /**
   * Test {@link NonNullElementWrapperList#remove(Object)} with {@code o}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add createPublicField.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#remove(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NonNullElementWrapperList.remove(Object)"})
  public void testRemoveWithO_givenArrayListAddCreatePublicField_thenReturnTrue()
      throws NoSuchFieldException {
    // Arrange
    ArrayList<Object> delegate = new ArrayList<>();
    delegate.add(ReflectionHelperTestFactory.createPublicField());
    NonNullElementWrapperList<Object> objectList = new NonNullElementWrapperList<>(delegate);

    // Act and Assert
    assertTrue(objectList.remove(ReflectionHelperTestFactory.createPublicField()));
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
  public void testRemoveWithO_thenReturnFalse() throws NoSuchFieldException {
    // Arrange
    NonNullElementWrapperList<Object> objectList =
        new NonNullElementWrapperList<>(new ArrayList<>());

    // Act and Assert
    assertFalse(objectList.remove(ReflectionHelperTestFactory.createPublicField()));
    assertTrue(objectList.isEmpty());
  }

  /**
   * Test {@link NonNullElementWrapperList#contains(Object)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add createPublicField.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#contains(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NonNullElementWrapperList.contains(Object)"})
  public void testContains_givenArrayListAddCreatePublicField_thenReturnTrue()
      throws NoSuchFieldException {
    // Arrange
    ArrayList<Object> delegate = new ArrayList<>();
    delegate.add(ReflectionHelperTestFactory.createPublicField());
    NonNullElementWrapperList<Object> objectList = new NonNullElementWrapperList<>(delegate);

    // Act and Assert
    assertTrue(objectList.contains(ReflectionHelperTestFactory.createPublicField()));
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
  public void testContains_thenReturnFalse() throws NoSuchFieldException {
    // Arrange
    NonNullElementWrapperList<Object> objectList =
        new NonNullElementWrapperList<>(new ArrayList<>());

    // Act and Assert
    assertFalse(objectList.contains(ReflectionHelperTestFactory.createPublicField()));
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
  public void testIndexOf() throws NoSuchFieldException {
    // Arrange
    ArrayList<Object> delegate = new ArrayList<>();
    delegate.add(ReflectionHelperTestFactory.createPublicField());
    NonNullElementWrapperList<Object> objectList = new NonNullElementWrapperList<>(delegate);

    // Act and Assert
    assertEquals(0, objectList.indexOf(ReflectionHelperTestFactory.createPublicField()));
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
  public void testLastIndexOf() throws NoSuchFieldException {
    // Arrange
    ArrayList<Object> delegate = new ArrayList<>();
    delegate.add(ReflectionHelperTestFactory.createPublicField());
    NonNullElementWrapperList<Object> objectList = new NonNullElementWrapperList<>(delegate);

    // Act and Assert
    assertEquals(0, objectList.lastIndexOf(ReflectionHelperTestFactory.createPublicField()));
  }
}
