package com.google.gson.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import com.diffblue.cover.annotations.MaintainedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.FieldAttributesDiffblueTestFactory;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class NonNullElementWrapperListDiffblueTest {
  /**
   * Test {@link NonNullElementWrapperList#NonNullElementWrapperList(ArrayList)}.
   *
   * <ul>
   *   <li>Given createField.
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#NonNullElementWrapperList(ArrayList)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void NonNullElementWrapperList.<init>(ArrayList)"})
  public void testNewNonNullElementWrapperList_givenCreateField_thenReturnSizeIsOne()
      throws NoSuchFieldException {
    // Arrange
    ArrayList<Object> delegate = new ArrayList<>();
    Field createFieldResult = FieldAttributesDiffblueTestFactory.createField();
    delegate.add(createFieldResult);

    // Act
    NonNullElementWrapperList<Object> actualObjectList = new NonNullElementWrapperList<>(delegate);

    // Assert
    assertEquals(1, actualObjectList.size());
    assertSame(createFieldResult, actualObjectList.get(0));
  }

  /**
   * Test {@link NonNullElementWrapperList#NonNullElementWrapperList(ArrayList)}.
   *
   * <ul>
   *   <li>Given createField.
   *   <li>Then return size is two.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#NonNullElementWrapperList(ArrayList)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void NonNullElementWrapperList.<init>(ArrayList)"})
  public void testNewNonNullElementWrapperList_givenCreateField_thenReturnSizeIsTwo()
      throws NoSuchFieldException {
    // Arrange
    ArrayList<Object> delegate = new ArrayList<>();
    delegate.add(FieldAttributesDiffblueTestFactory.createField());
    Field createFieldResult = FieldAttributesDiffblueTestFactory.createField();
    delegate.add(createFieldResult);

    // Act
    NonNullElementWrapperList<Object> actualObjectList = new NonNullElementWrapperList<>(delegate);

    // Assert
    assertEquals(2, actualObjectList.size());
    assertSame(createFieldResult, actualObjectList.get(1));
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void NonNullElementWrapperList.<init>(ArrayList)"})
  public void testNewNonNullElementWrapperList_whenArrayList_thenReturnArrayList() {
    // Arrange
    ArrayList<Object> delegate = new ArrayList<>();

    // Act and Assert
    assertEquals(delegate, new NonNullElementWrapperList<>(delegate));
  }

  /**
   * Test {@link NonNullElementWrapperList#get(int)}.
   *
   * <ul>
   *   <li>Then return {@code element2}.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#get(int)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"Object NonNullElementWrapperList.get(int)"})
  public void testGet_thenReturnElement2() {
    // Arrange
    NonNullElementWrapperList<Object> createNonNullElementWrapperListWithMultipleElementsResult =
        NonNullElementWrapperListDiffblueTestFactory
            .createNonNullElementWrapperListWithMultipleElements();

    // Act and Assert
    assertEquals("element2", createNonNullElementWrapperListWithMultipleElementsResult.get(1));
  }

  /**
   * Test {@link NonNullElementWrapperList#size()}.
   *
   * <p>Method under test: {@link NonNullElementWrapperList#size()}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"int NonNullElementWrapperList.size()"})
  public void testSize() {
    // Arrange
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();

    // Act and Assert
    assertEquals(0, createEmptyNonNullElementWrapperListResult.size());
  }

  /**
   * Test {@link NonNullElementWrapperList#set(int, Object)}.
   *
   * <ul>
   *   <li>Then createNonNullElementWrapperListWithMultipleElements size is three.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#set(int, Object)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"Object NonNullElementWrapperList.set(int, Object)"})
  public void testSet_thenCreateNonNullElementWrapperListWithMultipleElementsSizeIsThree()
      throws NoSuchFieldException {
    // Arrange
    NonNullElementWrapperList<Object> createNonNullElementWrapperListWithMultipleElementsResult =
        NonNullElementWrapperListDiffblueTestFactory
            .createNonNullElementWrapperListWithMultipleElements();
    Field createFieldResult = FieldAttributesDiffblueTestFactory.createField();

    // Act
    Object actualSetResult =
        createNonNullElementWrapperListWithMultipleElementsResult.set(1, createFieldResult);

    // Assert
    assertEquals(3, createNonNullElementWrapperListWithMultipleElementsResult.size());
    assertEquals("element2", actualSetResult);
    assertSame(createFieldResult, createNonNullElementWrapperListWithMultipleElementsResult.get(1));
  }

  /**
   * Test {@link NonNullElementWrapperList#add(int, Object)} with {@code int}, {@code Object}.
   *
   * <p>Method under test: {@link NonNullElementWrapperList#add(int, Object)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void NonNullElementWrapperList.add(int, Object)"})
  public void testAddWithIntObject() throws NoSuchFieldException {
    // Arrange
    NonNullElementWrapperList<Object> createNonNullElementWrapperListWithMultipleElementsResult =
        NonNullElementWrapperListDiffblueTestFactory
            .createNonNullElementWrapperListWithMultipleElements();
    Field createFieldResult = FieldAttributesDiffblueTestFactory.createField();

    // Act
    createNonNullElementWrapperListWithMultipleElementsResult.add(1, createFieldResult);

    // Assert
    assertEquals(4, createNonNullElementWrapperListWithMultipleElementsResult.size());
    assertEquals("element2", createNonNullElementWrapperListWithMultipleElementsResult.get(2));
    assertEquals("element3", createNonNullElementWrapperListWithMultipleElementsResult.get(3));
    assertSame(createFieldResult, createNonNullElementWrapperListWithMultipleElementsResult.get(1));
  }

  /**
   * Test {@link NonNullElementWrapperList#remove(int)} with {@code index}.
   *
   * <p>Method under test: {@link NonNullElementWrapperList#remove(int)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"Object NonNullElementWrapperList.remove(int)"})
  public void testRemoveWithIndex() {
    // Arrange
    NonNullElementWrapperList<Object> createNonNullElementWrapperListWithMultipleElementsResult =
        NonNullElementWrapperListDiffblueTestFactory
            .createNonNullElementWrapperListWithMultipleElements();

    // Act
    Object actualRemoveResult = createNonNullElementWrapperListWithMultipleElementsResult.remove(1);

    // Assert
    assertEquals(2, createNonNullElementWrapperListWithMultipleElementsResult.size());
    assertEquals("element2", actualRemoveResult);
    assertEquals("element3", createNonNullElementWrapperListWithMultipleElementsResult.get(1));
  }

  /**
   * Test {@link NonNullElementWrapperList#remove(Object)} with {@code o}.
   *
   * <p>Method under test: {@link NonNullElementWrapperList#remove(Object)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"boolean NonNullElementWrapperList.remove(Object)"})
  public void testRemoveWithO() throws NoSuchFieldException {
    // Arrange
    NonNullElementWrapperList<Object> createNonNullElementWrapperListWithMultipleElementsResult =
        NonNullElementWrapperListDiffblueTestFactory
            .createNonNullElementWrapperListWithMultipleElements();
    createNonNullElementWrapperListWithMultipleElementsResult.add(
        FieldAttributesDiffblueTestFactory.createField());
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();

    // Act
    boolean actualRemoveResult =
        createNonNullElementWrapperListWithMultipleElementsResult.remove(
            createEmptyNonNullElementWrapperListResult);

    // Assert
    assertEquals(4, createNonNullElementWrapperListWithMultipleElementsResult.size());
    assertFalse(actualRemoveResult);
  }

  /**
   * Test {@link NonNullElementWrapperList#remove(Object)} with {@code o}.
   *
   * <p>Method under test: {@link NonNullElementWrapperList#remove(Object)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"boolean NonNullElementWrapperList.remove(Object)"})
  public void testRemoveWithO2() throws NoSuchFieldException {
    // Arrange
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();
    NonNullElementWrapperList<Object> createNonNullElementWrapperListWithMultipleElementsResult =
        NonNullElementWrapperListDiffblueTestFactory
            .createNonNullElementWrapperListWithMultipleElements();
    createEmptyNonNullElementWrapperListResult.add(
        createNonNullElementWrapperListWithMultipleElementsResult);

    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add(FieldAttributesDiffblueTestFactory.createField());

    // Act
    boolean actualRemoveResult = createEmptyNonNullElementWrapperListResult.remove(objectList);

    // Assert
    assertEquals(1, createEmptyNonNullElementWrapperListResult.size());
    assertFalse(actualRemoveResult);
  }

  /**
   * Test {@link NonNullElementWrapperList#remove(Object)} with {@code o}.
   *
   * <ul>
   *   <li>Given createEmptyNonNullElementWrapperList add createField.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#remove(Object)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"boolean NonNullElementWrapperList.remove(Object)"})
  public void testRemoveWithO_givenCreateEmptyNonNullElementWrapperListAddCreateField()
      throws NoSuchFieldException {
    // Arrange
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();
    createEmptyNonNullElementWrapperListResult.add(
        FieldAttributesDiffblueTestFactory.createField());
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult2 =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();

    // Act and Assert
    assertFalse(
        createEmptyNonNullElementWrapperListResult.remove(
            createEmptyNonNullElementWrapperListResult2));
    assertTrue(createEmptyNonNullElementWrapperListResult2.isEmpty());
  }

  /**
   * Test {@link NonNullElementWrapperList#remove(Object)} with {@code o}.
   *
   * <ul>
   *   <li>Given createEmptyNonNullElementWrapperList.
   *   <li>When createField.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#remove(Object)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"boolean NonNullElementWrapperList.remove(Object)"})
  public void testRemoveWithO_givenCreateEmptyNonNullElementWrapperList_whenCreateField()
      throws NoSuchFieldException {
    // Arrange
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();

    // Act and Assert
    assertFalse(
        createEmptyNonNullElementWrapperListResult.remove(
            FieldAttributesDiffblueTestFactory.createField()));
    assertTrue(createEmptyNonNullElementWrapperListResult.isEmpty());
  }

  /**
   * Test {@link NonNullElementWrapperList#remove(Object)} with {@code o}.
   *
   * <ul>
   *   <li>Then createEmptyNonNullElementWrapperList.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#remove(Object)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"boolean NonNullElementWrapperList.remove(Object)"})
  public void testRemoveWithO_thenCreateEmptyNonNullElementWrapperList() {
    // Arrange
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult2 =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();
    createEmptyNonNullElementWrapperListResult.add(createEmptyNonNullElementWrapperListResult2);
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult3 =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();

    // Act and Assert
    assertTrue(
        createEmptyNonNullElementWrapperListResult.remove(
            createEmptyNonNullElementWrapperListResult3));
    assertEquals(
        createEmptyNonNullElementWrapperListResult, createEmptyNonNullElementWrapperListResult3);
  }

  /**
   * Test {@link NonNullElementWrapperList#remove(Object)} with {@code o}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#remove(Object)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"boolean NonNullElementWrapperList.remove(Object)"})
  public void testRemoveWithO_thenReturnTrue() throws NoSuchFieldException {
    // Arrange
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();
    createEmptyNonNullElementWrapperListResult.add(
        FieldAttributesDiffblueTestFactory.createField());

    // Act and Assert
    assertTrue(
        createEmptyNonNullElementWrapperListResult.remove(
            FieldAttributesDiffblueTestFactory.createField()));
    assertTrue(createEmptyNonNullElementWrapperListResult.isEmpty());
  }

  /**
   * Test {@link NonNullElementWrapperList#remove(Object)} with {@code o}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()} add createField.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#remove(Object)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"boolean NonNullElementWrapperList.remove(Object)"})
  public void testRemoveWithO_whenArrayListAddCreateField() throws NoSuchFieldException {
    // Arrange
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult2 =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();
    createEmptyNonNullElementWrapperListResult.add(createEmptyNonNullElementWrapperListResult2);

    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add(FieldAttributesDiffblueTestFactory.createField());

    // Act
    boolean actualRemoveResult = createEmptyNonNullElementWrapperListResult.remove(objectList);

    // Assert
    assertEquals(1, createEmptyNonNullElementWrapperListResult.size());
    assertFalse(actualRemoveResult);
  }

  /**
   * Test {@link NonNullElementWrapperList#remove(Object)} with {@code o}.
   *
   * <ul>
   *   <li>When {@link LinkedList#LinkedList()} add createField.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#remove(Object)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"boolean NonNullElementWrapperList.remove(Object)"})
  public void testRemoveWithO_whenLinkedListAddCreateField() throws NoSuchFieldException {
    // Arrange
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult2 =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();
    createEmptyNonNullElementWrapperListResult.add(createEmptyNonNullElementWrapperListResult2);

    LinkedList<Object> objectList = new LinkedList<>();
    objectList.add(FieldAttributesDiffblueTestFactory.createField());

    // Act
    boolean actualRemoveResult = createEmptyNonNullElementWrapperListResult.remove(objectList);

    // Assert
    assertEquals(1, createEmptyNonNullElementWrapperListResult.size());
    assertFalse(actualRemoveResult);
  }

  /**
   * Test {@link NonNullElementWrapperList#removeAll(Collection)}.
   *
   * <ul>
   *   <li>Given createField.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#removeAll(Collection)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"boolean NonNullElementWrapperList.removeAll(Collection)"})
  public void testRemoveAll_givenCreateField() throws NoSuchFieldException {
    // Arrange
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();
    NonNullElementWrapperList<Object> c =
        NonNullElementWrapperListDiffblueTestFactory
            .createNonNullElementWrapperListWithOneElement();
    c.add(FieldAttributesDiffblueTestFactory.createField());

    // Act and Assert
    assertFalse(createEmptyNonNullElementWrapperListResult.removeAll(c));
  }

  /**
   * Test {@link NonNullElementWrapperList#removeAll(Collection)}.
   *
   * <ul>
   *   <li>Given createNonNullElementWrapperListWithMultipleElements.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#removeAll(Collection)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"boolean NonNullElementWrapperList.removeAll(Collection)"})
  public void testRemoveAll_givenCreateNonNullElementWrapperListWithMultipleElements() {
    // Arrange
    NonNullElementWrapperList<Object> createNonNullElementWrapperListWithMultipleElementsResult =
        NonNullElementWrapperListDiffblueTestFactory
            .createNonNullElementWrapperListWithMultipleElements();
    NonNullElementWrapperList<Object> c =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();

    // Act and Assert
    assertFalse(createNonNullElementWrapperListWithMultipleElementsResult.removeAll(c));
  }

  /**
   * Test {@link NonNullElementWrapperList#removeAll(Collection)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#removeAll(Collection)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"boolean NonNullElementWrapperList.removeAll(Collection)"})
  public void testRemoveAll_thenReturnTrue() {
    // Arrange
    NonNullElementWrapperList<Object> createNonNullElementWrapperListWithMultipleElementsResult =
        NonNullElementWrapperListDiffblueTestFactory
            .createNonNullElementWrapperListWithMultipleElements();
    NonNullElementWrapperList<Object> c =
        NonNullElementWrapperListDiffblueTestFactory
            .createNonNullElementWrapperListWithMultipleElements();

    // Act and Assert
    assertTrue(createNonNullElementWrapperListWithMultipleElementsResult.removeAll(c));
  }

  /**
   * Test {@link NonNullElementWrapperList#removeAll(Collection)}.
   *
   * <ul>
   *   <li>When createEmptyNonNullElementWrapperList.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#removeAll(Collection)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"boolean NonNullElementWrapperList.removeAll(Collection)"})
  public void testRemoveAll_whenCreateEmptyNonNullElementWrapperList_thenReturnFalse() {
    // Arrange
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();
    NonNullElementWrapperList<Object> c =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();

    // Act and Assert
    assertFalse(createEmptyNonNullElementWrapperListResult.removeAll(c));
  }

  /**
   * Test {@link NonNullElementWrapperList#removeAll(Collection)}.
   *
   * <ul>
   *   <li>When createNonNullElementWrapperListWithOneElement.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#removeAll(Collection)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"boolean NonNullElementWrapperList.removeAll(Collection)"})
  public void testRemoveAll_whenCreateNonNullElementWrapperListWithOneElement_thenReturnFalse() {
    // Arrange
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();
    NonNullElementWrapperList<Object> c =
        NonNullElementWrapperListDiffblueTestFactory
            .createNonNullElementWrapperListWithOneElement();

    // Act and Assert
    assertFalse(createEmptyNonNullElementWrapperListResult.removeAll(c));
  }

  /**
   * Test {@link NonNullElementWrapperList#retainAll(Collection)}.
   *
   * <ul>
   *   <li>Then createEmptyNonNullElementWrapperList.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#retainAll(Collection)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"boolean NonNullElementWrapperList.retainAll(Collection)"})
  public void testRetainAll_thenCreateEmptyNonNullElementWrapperList() {
    // Arrange
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();
    NonNullElementWrapperList<Object> c =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();

    // Act and Assert
    assertFalse(createEmptyNonNullElementWrapperListResult.retainAll(c));
    assertEquals(createEmptyNonNullElementWrapperListResult, c);
  }

  /**
   * Test {@link NonNullElementWrapperList#retainAll(Collection)}.
   *
   * <ul>
   *   <li>Then createNonNullElementWrapperListWithOneElement size is one.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#retainAll(Collection)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"boolean NonNullElementWrapperList.retainAll(Collection)"})
  public void testRetainAll_thenCreateNonNullElementWrapperListWithOneElementSizeIsOne() {
    // Arrange
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();
    NonNullElementWrapperList<Object> c =
        NonNullElementWrapperListDiffblueTestFactory
            .createNonNullElementWrapperListWithOneElement();

    // Act
    boolean actualRetainAllResult = createEmptyNonNullElementWrapperListResult.retainAll(c);

    // Assert
    assertEquals(1, c.size());
    assertFalse(actualRetainAllResult);
    assertTrue(createEmptyNonNullElementWrapperListResult.isEmpty());
  }

  /**
   * Test {@link NonNullElementWrapperList#retainAll(Collection)}.
   *
   * <ul>
   *   <li>Then createNonNullElementWrapperListWithOneElement size is two.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#retainAll(Collection)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"boolean NonNullElementWrapperList.retainAll(Collection)"})
  public void testRetainAll_thenCreateNonNullElementWrapperListWithOneElementSizeIsTwo()
      throws NoSuchFieldException {
    // Arrange
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();
    NonNullElementWrapperList<Object> c =
        NonNullElementWrapperListDiffblueTestFactory
            .createNonNullElementWrapperListWithOneElement();
    c.add(FieldAttributesDiffblueTestFactory.createField());

    // Act
    boolean actualRetainAllResult = createEmptyNonNullElementWrapperListResult.retainAll(c);

    // Assert
    assertEquals(2, c.size());
    assertFalse(actualRetainAllResult);
    assertTrue(createEmptyNonNullElementWrapperListResult.isEmpty());
  }

  /**
   * Test {@link NonNullElementWrapperList#retainAll(Collection)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#retainAll(Collection)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"boolean NonNullElementWrapperList.retainAll(Collection)"})
  public void testRetainAll_thenReturnTrue() {
    // Arrange
    NonNullElementWrapperList<Object> createNonNullElementWrapperListWithMultipleElementsResult =
        NonNullElementWrapperListDiffblueTestFactory
            .createNonNullElementWrapperListWithMultipleElements();
    NonNullElementWrapperList<Object> c =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();

    // Act and Assert
    assertTrue(createNonNullElementWrapperListWithMultipleElementsResult.retainAll(c));
    assertTrue(createNonNullElementWrapperListWithMultipleElementsResult.isEmpty());
    assertEquals(createNonNullElementWrapperListWithMultipleElementsResult, c);
  }

  /**
   * Test {@link NonNullElementWrapperList#contains(Object)}.
   *
   * <p>Method under test: {@link NonNullElementWrapperList#contains(Object)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"boolean NonNullElementWrapperList.contains(Object)"})
  public void testContains() throws NoSuchFieldException {
    // Arrange
    NonNullElementWrapperList<Object> createNonNullElementWrapperListWithMultipleElementsResult =
        NonNullElementWrapperListDiffblueTestFactory
            .createNonNullElementWrapperListWithMultipleElements();
    createNonNullElementWrapperListWithMultipleElementsResult.add(
        FieldAttributesDiffblueTestFactory.createField());
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();

    // Act and Assert
    assertFalse(
        createNonNullElementWrapperListWithMultipleElementsResult.contains(
            createEmptyNonNullElementWrapperListResult));
  }

  /**
   * Test {@link NonNullElementWrapperList#contains(Object)}.
   *
   * <p>Method under test: {@link NonNullElementWrapperList#contains(Object)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"boolean NonNullElementWrapperList.contains(Object)"})
  public void testContains2() throws NoSuchFieldException {
    // Arrange
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();
    NonNullElementWrapperList<Object> createNonNullElementWrapperListWithMultipleElementsResult =
        NonNullElementWrapperListDiffblueTestFactory
            .createNonNullElementWrapperListWithMultipleElements();
    createEmptyNonNullElementWrapperListResult.add(
        createNonNullElementWrapperListWithMultipleElementsResult);

    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add(FieldAttributesDiffblueTestFactory.createField());

    // Act and Assert
    assertFalse(createEmptyNonNullElementWrapperListResult.contains(objectList));
  }

  /**
   * Test {@link NonNullElementWrapperList#contains(Object)}.
   *
   * <ul>
   *   <li>Given createEmptyNonNullElementWrapperList add createField.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#contains(Object)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"boolean NonNullElementWrapperList.contains(Object)"})
  public void testContains_givenCreateEmptyNonNullElementWrapperListAddCreateField()
      throws NoSuchFieldException {
    // Arrange
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();
    createEmptyNonNullElementWrapperListResult.add(
        FieldAttributesDiffblueTestFactory.createField());
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult2 =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();

    // Act and Assert
    assertFalse(
        createEmptyNonNullElementWrapperListResult.contains(
            createEmptyNonNullElementWrapperListResult2));
  }

  /**
   * Test {@link NonNullElementWrapperList#contains(Object)}.
   *
   * <ul>
   *   <li>Given createEmptyNonNullElementWrapperList.
   *   <li>When createField.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#contains(Object)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"boolean NonNullElementWrapperList.contains(Object)"})
  public void testContains_givenCreateEmptyNonNullElementWrapperList_whenCreateField()
      throws NoSuchFieldException {
    // Arrange
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();

    // Act and Assert
    assertFalse(
        createEmptyNonNullElementWrapperListResult.contains(
            FieldAttributesDiffblueTestFactory.createField()));
  }

  /**
   * Test {@link NonNullElementWrapperList#contains(Object)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#contains(Object)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"boolean NonNullElementWrapperList.contains(Object)"})
  public void testContains_thenReturnTrue() throws NoSuchFieldException {
    // Arrange
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();
    createEmptyNonNullElementWrapperListResult.add(
        FieldAttributesDiffblueTestFactory.createField());

    // Act and Assert
    assertTrue(
        createEmptyNonNullElementWrapperListResult.contains(
            FieldAttributesDiffblueTestFactory.createField()));
  }

  /**
   * Test {@link NonNullElementWrapperList#contains(Object)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#contains(Object)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"boolean NonNullElementWrapperList.contains(Object)"})
  public void testContains_thenReturnTrue2() {
    // Arrange
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult2 =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();
    createEmptyNonNullElementWrapperListResult.add(createEmptyNonNullElementWrapperListResult2);
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult3 =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();

    // Act and Assert
    assertTrue(
        createEmptyNonNullElementWrapperListResult.contains(
            createEmptyNonNullElementWrapperListResult3));
  }

  /**
   * Test {@link NonNullElementWrapperList#contains(Object)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()} add createField.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#contains(Object)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"boolean NonNullElementWrapperList.contains(Object)"})
  public void testContains_whenArrayListAddCreateField() throws NoSuchFieldException {
    // Arrange
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult2 =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();
    createEmptyNonNullElementWrapperListResult.add(createEmptyNonNullElementWrapperListResult2);

    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add(FieldAttributesDiffblueTestFactory.createField());

    // Act and Assert
    assertFalse(createEmptyNonNullElementWrapperListResult.contains(objectList));
  }

  /**
   * Test {@link NonNullElementWrapperList#contains(Object)}.
   *
   * <ul>
   *   <li>When {@link LinkedList#LinkedList()} add createField.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#contains(Object)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"boolean NonNullElementWrapperList.contains(Object)"})
  public void testContains_whenLinkedListAddCreateField() throws NoSuchFieldException {
    // Arrange
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult2 =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();
    createEmptyNonNullElementWrapperListResult.add(createEmptyNonNullElementWrapperListResult2);

    LinkedList<Object> objectList = new LinkedList<>();
    objectList.add(FieldAttributesDiffblueTestFactory.createField());

    // Act and Assert
    assertFalse(createEmptyNonNullElementWrapperListResult.contains(objectList));
  }

  /**
   * Test {@link NonNullElementWrapperList#indexOf(Object)}.
   *
   * <p>Method under test: {@link NonNullElementWrapperList#indexOf(Object)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"int NonNullElementWrapperList.indexOf(Object)"})
  public void testIndexOf() throws NoSuchFieldException {
    // Arrange
    NonNullElementWrapperList<Object> createNonNullElementWrapperListWithMultipleElementsResult =
        NonNullElementWrapperListDiffblueTestFactory
            .createNonNullElementWrapperListWithMultipleElements();
    NonNullElementWrapperList<Object> createNonNullElementWrapperListWithMultipleElementsResult2 =
        NonNullElementWrapperListDiffblueTestFactory
            .createNonNullElementWrapperListWithMultipleElements();
    createNonNullElementWrapperListWithMultipleElementsResult.add(
        createNonNullElementWrapperListWithMultipleElementsResult2);

    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add(FieldAttributesDiffblueTestFactory.createField());

    // Act and Assert
    assertEquals(-1, createNonNullElementWrapperListWithMultipleElementsResult.indexOf(objectList));
  }

  /**
   * Test {@link NonNullElementWrapperList#indexOf(Object)}.
   *
   * <ul>
   *   <li>Given createEmptyNonNullElementWrapperList.
   *   <li>When createField.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#indexOf(Object)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"int NonNullElementWrapperList.indexOf(Object)"})
  public void testIndexOf_givenCreateEmptyNonNullElementWrapperList_whenCreateField()
      throws NoSuchFieldException {
    // Arrange
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();

    // Act and Assert
    assertEquals(
        -1,
        createEmptyNonNullElementWrapperListResult.indexOf(
            FieldAttributesDiffblueTestFactory.createField()));
  }

  /**
   * Test {@link NonNullElementWrapperList#indexOf(Object)}.
   *
   * <ul>
   *   <li>Given createNonNullElementWrapperListWithMultipleElements.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#indexOf(Object)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"int NonNullElementWrapperList.indexOf(Object)"})
  public void testIndexOf_givenCreateNonNullElementWrapperListWithMultipleElements() {
    // Arrange
    NonNullElementWrapperList<Object> createNonNullElementWrapperListWithMultipleElementsResult =
        NonNullElementWrapperListDiffblueTestFactory
            .createNonNullElementWrapperListWithMultipleElements();
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();

    // Act and Assert
    assertEquals(
        -1,
        createNonNullElementWrapperListWithMultipleElementsResult.indexOf(
            createEmptyNonNullElementWrapperListResult));
  }

  /**
   * Test {@link NonNullElementWrapperList#indexOf(Object)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()} add createField.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#indexOf(Object)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"int NonNullElementWrapperList.indexOf(Object)"})
  public void testIndexOf_whenArrayListAddCreateField() throws NoSuchFieldException {
    // Arrange
    NonNullElementWrapperList<Object> createNonNullElementWrapperListWithMultipleElementsResult =
        NonNullElementWrapperListDiffblueTestFactory
            .createNonNullElementWrapperListWithMultipleElements();
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();
    createNonNullElementWrapperListWithMultipleElementsResult.add(
        createEmptyNonNullElementWrapperListResult);

    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add(FieldAttributesDiffblueTestFactory.createField());

    // Act and Assert
    assertEquals(-1, createNonNullElementWrapperListWithMultipleElementsResult.indexOf(objectList));
  }

  /**
   * Test {@link NonNullElementWrapperList#indexOf(Object)}.
   *
   * <ul>
   *   <li>When createEmptyNonNullElementWrapperList.
   *   <li>Then return three.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#indexOf(Object)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"int NonNullElementWrapperList.indexOf(Object)"})
  public void testIndexOf_whenCreateEmptyNonNullElementWrapperList_thenReturnThree() {
    // Arrange
    NonNullElementWrapperList<Object> createNonNullElementWrapperListWithMultipleElementsResult =
        NonNullElementWrapperListDiffblueTestFactory
            .createNonNullElementWrapperListWithMultipleElements();
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();
    createNonNullElementWrapperListWithMultipleElementsResult.add(
        createEmptyNonNullElementWrapperListResult);
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult2 =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();

    // Act and Assert
    assertEquals(
        3,
        createNonNullElementWrapperListWithMultipleElementsResult.indexOf(
            createEmptyNonNullElementWrapperListResult2));
  }

  /**
   * Test {@link NonNullElementWrapperList#indexOf(Object)}.
   *
   * <ul>
   *   <li>When {@link LinkedList#LinkedList()} add createField.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#indexOf(Object)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"int NonNullElementWrapperList.indexOf(Object)"})
  public void testIndexOf_whenLinkedListAddCreateField() throws NoSuchFieldException {
    // Arrange
    NonNullElementWrapperList<Object> createNonNullElementWrapperListWithMultipleElementsResult =
        NonNullElementWrapperListDiffblueTestFactory
            .createNonNullElementWrapperListWithMultipleElements();
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();
    createNonNullElementWrapperListWithMultipleElementsResult.add(
        createEmptyNonNullElementWrapperListResult);

    LinkedList<Object> objectList = new LinkedList<>();
    objectList.add(FieldAttributesDiffblueTestFactory.createField());

    // Act and Assert
    assertEquals(-1, createNonNullElementWrapperListWithMultipleElementsResult.indexOf(objectList));
  }

  /**
   * Test {@link NonNullElementWrapperList#lastIndexOf(Object)}.
   *
   * <p>Method under test: {@link NonNullElementWrapperList#lastIndexOf(Object)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"int NonNullElementWrapperList.lastIndexOf(Object)"})
  public void testLastIndexOf() throws NoSuchFieldException {
    // Arrange
    NonNullElementWrapperList<Object> createNonNullElementWrapperListWithMultipleElementsResult =
        NonNullElementWrapperListDiffblueTestFactory
            .createNonNullElementWrapperListWithMultipleElements();
    NonNullElementWrapperList<Object> createNonNullElementWrapperListWithMultipleElementsResult2 =
        NonNullElementWrapperListDiffblueTestFactory
            .createNonNullElementWrapperListWithMultipleElements();
    createNonNullElementWrapperListWithMultipleElementsResult.add(
        createNonNullElementWrapperListWithMultipleElementsResult2);

    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add(FieldAttributesDiffblueTestFactory.createField());

    // Act and Assert
    assertEquals(
        -1, createNonNullElementWrapperListWithMultipleElementsResult.lastIndexOf(objectList));
  }

  /**
   * Test {@link NonNullElementWrapperList#lastIndexOf(Object)}.
   *
   * <ul>
   *   <li>Given createEmptyNonNullElementWrapperList.
   *   <li>When createField.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#lastIndexOf(Object)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"int NonNullElementWrapperList.lastIndexOf(Object)"})
  public void testLastIndexOf_givenCreateEmptyNonNullElementWrapperList_whenCreateField()
      throws NoSuchFieldException {
    // Arrange
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();

    // Act and Assert
    assertEquals(
        -1,
        createEmptyNonNullElementWrapperListResult.lastIndexOf(
            FieldAttributesDiffblueTestFactory.createField()));
  }

  /**
   * Test {@link NonNullElementWrapperList#lastIndexOf(Object)}.
   *
   * <ul>
   *   <li>Given createNonNullElementWrapperListWithMultipleElements.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#lastIndexOf(Object)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"int NonNullElementWrapperList.lastIndexOf(Object)"})
  public void testLastIndexOf_givenCreateNonNullElementWrapperListWithMultipleElements() {
    // Arrange
    NonNullElementWrapperList<Object> createNonNullElementWrapperListWithMultipleElementsResult =
        NonNullElementWrapperListDiffblueTestFactory
            .createNonNullElementWrapperListWithMultipleElements();
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();

    // Act and Assert
    assertEquals(
        -1,
        createNonNullElementWrapperListWithMultipleElementsResult.lastIndexOf(
            createEmptyNonNullElementWrapperListResult));
  }

  /**
   * Test {@link NonNullElementWrapperList#lastIndexOf(Object)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()} add createField.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#lastIndexOf(Object)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"int NonNullElementWrapperList.lastIndexOf(Object)"})
  public void testLastIndexOf_whenArrayListAddCreateField() throws NoSuchFieldException {
    // Arrange
    NonNullElementWrapperList<Object> createNonNullElementWrapperListWithMultipleElementsResult =
        NonNullElementWrapperListDiffblueTestFactory
            .createNonNullElementWrapperListWithMultipleElements();
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();
    createNonNullElementWrapperListWithMultipleElementsResult.add(
        createEmptyNonNullElementWrapperListResult);

    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add(FieldAttributesDiffblueTestFactory.createField());

    // Act and Assert
    assertEquals(
        -1, createNonNullElementWrapperListWithMultipleElementsResult.lastIndexOf(objectList));
  }

  /**
   * Test {@link NonNullElementWrapperList#lastIndexOf(Object)}.
   *
   * <ul>
   *   <li>When createEmptyNonNullElementWrapperList.
   *   <li>Then return three.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#lastIndexOf(Object)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"int NonNullElementWrapperList.lastIndexOf(Object)"})
  public void testLastIndexOf_whenCreateEmptyNonNullElementWrapperList_thenReturnThree() {
    // Arrange
    NonNullElementWrapperList<Object> createNonNullElementWrapperListWithMultipleElementsResult =
        NonNullElementWrapperListDiffblueTestFactory
            .createNonNullElementWrapperListWithMultipleElements();
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();
    createNonNullElementWrapperListWithMultipleElementsResult.add(
        createEmptyNonNullElementWrapperListResult);
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult2 =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();

    // Act and Assert
    assertEquals(
        3,
        createNonNullElementWrapperListWithMultipleElementsResult.lastIndexOf(
            createEmptyNonNullElementWrapperListResult2));
  }

  /**
   * Test {@link NonNullElementWrapperList#lastIndexOf(Object)}.
   *
   * <ul>
   *   <li>When {@link LinkedList#LinkedList()} add createField.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#lastIndexOf(Object)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"int NonNullElementWrapperList.lastIndexOf(Object)"})
  public void testLastIndexOf_whenLinkedListAddCreateField() throws NoSuchFieldException {
    // Arrange
    NonNullElementWrapperList<Object> createNonNullElementWrapperListWithMultipleElementsResult =
        NonNullElementWrapperListDiffblueTestFactory
            .createNonNullElementWrapperListWithMultipleElements();
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();
    createNonNullElementWrapperListWithMultipleElementsResult.add(
        createEmptyNonNullElementWrapperListResult);

    LinkedList<Object> objectList = new LinkedList<>();
    objectList.add(FieldAttributesDiffblueTestFactory.createField());

    // Act and Assert
    assertEquals(
        -1, createNonNullElementWrapperListWithMultipleElementsResult.lastIndexOf(objectList));
  }

  /**
   * Test {@link NonNullElementWrapperList#toArray()}.
   *
   * <p>Method under test: {@link NonNullElementWrapperList#toArray()}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"Object[] NonNullElementWrapperList.toArray()"})
  public void testToArray() {
    // Arrange
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();

    // Act and Assert
    assertEquals(0, createEmptyNonNullElementWrapperListResult.toArray().length);
  }

  /**
   * Test {@link NonNullElementWrapperList#equals(Object)}, and {@link
   * NonNullElementWrapperList#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link NonNullElementWrapperList#equals(Object)}
   *   <li>{@link NonNullElementWrapperList#hashCode()}
   * </ul>
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
    "boolean NonNullElementWrapperList.equals(Object)",
    "int NonNullElementWrapperList.hashCode()"
  })
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult2 =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();

    // Act and Assert
    assertEquals(
        createEmptyNonNullElementWrapperListResult, createEmptyNonNullElementWrapperListResult2);
    int expectedHashCodeResult = createEmptyNonNullElementWrapperListResult.hashCode();
    assertEquals(expectedHashCodeResult, createEmptyNonNullElementWrapperListResult2.hashCode());
  }

  /**
   * Test {@link NonNullElementWrapperList#equals(Object)}, and {@link
   * NonNullElementWrapperList#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link NonNullElementWrapperList#equals(Object)}
   *   <li>{@link NonNullElementWrapperList#hashCode()}
   * </ul>
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
    "boolean NonNullElementWrapperList.equals(Object)",
    "int NonNullElementWrapperList.hashCode()"
  })
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual2() {
    // Arrange
    NonNullElementWrapperList<Object> createNonNullElementWrapperListWithMultipleElementsResult =
        NonNullElementWrapperListDiffblueTestFactory
            .createNonNullElementWrapperListWithMultipleElements();
    NonNullElementWrapperList<Object> createNonNullElementWrapperListWithMultipleElementsResult2 =
        NonNullElementWrapperListDiffblueTestFactory
            .createNonNullElementWrapperListWithMultipleElements();

    // Act and Assert
    assertEquals(
        createNonNullElementWrapperListWithMultipleElementsResult,
        createNonNullElementWrapperListWithMultipleElementsResult2);
    int expectedHashCodeResult =
        createNonNullElementWrapperListWithMultipleElementsResult.hashCode();
    assertEquals(
        expectedHashCodeResult,
        createNonNullElementWrapperListWithMultipleElementsResult2.hashCode());
  }

  /**
   * Test {@link NonNullElementWrapperList#equals(Object)}, and {@link
   * NonNullElementWrapperList#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link NonNullElementWrapperList#equals(Object)}
   *   <li>{@link NonNullElementWrapperList#hashCode()}
   * </ul>
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
    "boolean NonNullElementWrapperList.equals(Object)",
    "int NonNullElementWrapperList.hashCode()"
  })
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();
    NonNullElementWrapperList<Object> anotherEmptyList =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();

    // Act and Assert
    assertEquals(createEmptyNonNullElementWrapperListResult, anotherEmptyList);
    int expectedHashCodeResult = createEmptyNonNullElementWrapperListResult.hashCode();
    assertEquals(expectedHashCodeResult, anotherEmptyList.hashCode());
  }

  /**
   * Test {@link NonNullElementWrapperList#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#equals(Object)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
    "boolean NonNullElementWrapperList.equals(Object)",
    "int NonNullElementWrapperList.hashCode()"
  })
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    NonNullElementWrapperList<Object> createNonNullElementWrapperListWithMultipleElementsResult =
        NonNullElementWrapperListDiffblueTestFactory
            .createNonNullElementWrapperListWithMultipleElements();
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();

    // Act and Assert
    assertNotEquals(
        createNonNullElementWrapperListWithMultipleElementsResult,
        createEmptyNonNullElementWrapperListResult);
  }

  /**
   * Test {@link NonNullElementWrapperList#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#equals(Object)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
    "boolean NonNullElementWrapperList.equals(Object)",
    "int NonNullElementWrapperList.hashCode()"
  })
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();

    // Act and Assert
    assertNotEquals(createEmptyNonNullElementWrapperListResult, null);
  }

  /**
   * Test {@link NonNullElementWrapperList#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#equals(Object)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
    "boolean NonNullElementWrapperList.equals(Object)",
    "int NonNullElementWrapperList.hashCode()"
  })
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    NonNullElementWrapperList<Object> createEmptyNonNullElementWrapperListResult =
        NonNullElementWrapperListDiffblueTestFactory.createEmptyNonNullElementWrapperList();

    // Act and Assert
    assertNotEquals(
        createEmptyNonNullElementWrapperListResult, "Different type to NonNullElementWrapperList");
  }
}
