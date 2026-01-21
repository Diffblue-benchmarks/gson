package com.google.gson.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Collection;
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

    // Act
    NonNullElementWrapperList<Object> actualObjectList = new NonNullElementWrapperList<>(delegate);

    // Assert
    assertEquals(delegate, actualObjectList);
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

    // Act
    NonNullElementWrapperList<Object> actualObjectList = new NonNullElementWrapperList<>(delegate);

    // Assert
    assertEquals(delegate, actualObjectList);
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

    // Act
    NonNullElementWrapperList<Object> actualObjectList = new NonNullElementWrapperList<>(delegate);

    // Assert
    assertEquals(delegate, actualObjectList);
  }

  /**
   * Test {@link NonNullElementWrapperList#createForTesting()}.
   *
   * <p>Method under test: {@link NonNullElementWrapperList#createForTesting()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"NonNullElementWrapperList NonNullElementWrapperList.createForTesting()"})
  public void testCreateForTesting() {
    // Arrange and Act
    NonNullElementWrapperList<Object> actualCreateForTestingResult =
        NonNullElementWrapperList.createForTesting();

    // Assert
    assertTrue(actualCreateForTestingResult.isEmpty());
  }

  /**
   * Test {@link NonNullElementWrapperList#get(int)}.
   *
   * <ul>
   *   <li>Given createForTesting add {@code 42}.
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#get(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object NonNullElementWrapperList.get(int)"})
  public void testGet_givenCreateForTestingAdd42_thenReturn42() {
    // Arrange
    NonNullElementWrapperList<Object> createForTestingResult =
        NonNullElementWrapperList.createForTesting();
    createForTestingResult.add("42");
    createForTestingResult.add("42");

    // Act and Assert
    assertEquals("42", createForTestingResult.get(1));
  }

  /**
   * Test {@link NonNullElementWrapperList#size()}.
   *
   * <p>Method under test: {@link NonNullElementWrapperList#size()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int NonNullElementWrapperList.size()"})
  public void testSize() {
    // Arrange
    NonNullElementWrapperList<Object> createForTestingResult =
        NonNullElementWrapperList.createForTesting();

    // Act and Assert
    assertEquals(0, createForTestingResult.size());
  }

  /**
   * Test {@link NonNullElementWrapperList#set(int, Object)}.
   *
   * <ul>
   *   <li>Given createForTesting add {@code 42}.
   *   <li>When {@code Element}.
   *   <li>Then createForTesting size is two.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#set(int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object NonNullElementWrapperList.set(int, Object)"})
  public void testSet_givenCreateForTestingAdd42_whenElement_thenCreateForTestingSizeIsTwo() {
    // Arrange
    NonNullElementWrapperList<Object> createForTestingResult =
        NonNullElementWrapperList.createForTesting();
    createForTestingResult.add("42");
    createForTestingResult.add("42");

    // Act
    Object actualSetResult = createForTestingResult.set(1, "Element");

    // Assert
    assertEquals(2, createForTestingResult.size());
    assertEquals("42", actualSetResult);
    assertEquals("Element", createForTestingResult.get(1));
  }

  /**
   * Test {@link NonNullElementWrapperList#add(int, Object)} with {@code int}, {@code Object}.
   *
   * <ul>
   *   <li>Given createForTesting add {@code 42}.
   *   <li>Then createForTesting size is two.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#add(int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NonNullElementWrapperList.add(int, Object)"})
  public void testAddWithIntObject_givenCreateForTestingAdd42_thenCreateForTestingSizeIsTwo() {
    // Arrange
    NonNullElementWrapperList<Object> createForTestingResult =
        NonNullElementWrapperList.createForTesting();
    createForTestingResult.add("42");

    // Act
    createForTestingResult.add(1, "Element");

    // Assert
    assertEquals(2, createForTestingResult.size());
    assertEquals("Element", createForTestingResult.get(1));
  }

  /**
   * Test {@link NonNullElementWrapperList#remove(int)} with {@code index}.
   *
   * <ul>
   *   <li>Given createForTesting add {@code 42}.
   *   <li>Then createForTesting size is one.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#remove(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object NonNullElementWrapperList.remove(int)"})
  public void testRemoveWithIndex_givenCreateForTestingAdd42_thenCreateForTestingSizeIsOne() {
    // Arrange
    NonNullElementWrapperList<Object> createForTestingResult =
        NonNullElementWrapperList.createForTesting();
    createForTestingResult.add("42");
    createForTestingResult.add("42");

    // Act
    Object actualRemoveResult = createForTestingResult.remove(1);

    // Assert
    assertEquals(1, createForTestingResult.size());
    assertEquals("42", actualRemoveResult);
  }

  /**
   * Test {@link NonNullElementWrapperList#remove(Object)} with {@code o}.
   *
   * <ul>
   *   <li>Given createForTesting add {@code 42}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#remove(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NonNullElementWrapperList.remove(Object)"})
  public void testRemoveWithO_givenCreateForTestingAdd42_thenReturnTrue() {
    // Arrange
    NonNullElementWrapperList<Object> createForTestingResult =
        NonNullElementWrapperList.createForTesting();
    createForTestingResult.add("42");

    // Act and Assert
    assertTrue(createForTestingResult.remove("42"));
    assertTrue(createForTestingResult.isEmpty());
  }

  /**
   * Test {@link NonNullElementWrapperList#remove(Object)} with {@code o}.
   *
   * <ul>
   *   <li>Given createForTesting.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#remove(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NonNullElementWrapperList.remove(Object)"})
  public void testRemoveWithO_givenCreateForTesting_thenReturnFalse() {
    // Arrange
    NonNullElementWrapperList<Object> createForTestingResult =
        NonNullElementWrapperList.createForTesting();

    // Act and Assert
    assertFalse(createForTestingResult.remove("42"));
    assertTrue(createForTestingResult.isEmpty());
  }

  /**
   * Test {@link NonNullElementWrapperList#removeAll(Collection)}.
   *
   * <ul>
   *   <li>Given createForTesting add {@code 42}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#removeAll(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NonNullElementWrapperList.removeAll(Collection)"})
  public void testRemoveAll_givenCreateForTestingAdd42_whenArrayListAdd42_thenReturnTrue() {
    // Arrange
    NonNullElementWrapperList<Object> createForTestingResult =
        NonNullElementWrapperList.createForTesting();
    createForTestingResult.add("42");

    ArrayList<Object> c = new ArrayList<>();
    c.add("42");

    // Act and Assert
    assertTrue(createForTestingResult.removeAll(c));
    assertTrue(createForTestingResult.isEmpty());
  }

  /**
   * Test {@link NonNullElementWrapperList#removeAll(Collection)}.
   *
   * <ul>
   *   <li>Given createForTesting.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#removeAll(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NonNullElementWrapperList.removeAll(Collection)"})
  public void testRemoveAll_givenCreateForTesting_whenArrayListAdd42_thenReturnFalse() {
    // Arrange
    NonNullElementWrapperList<Object> createForTestingResult =
        NonNullElementWrapperList.createForTesting();

    ArrayList<Object> c = new ArrayList<>();
    c.add("42");

    // Act and Assert
    assertFalse(createForTestingResult.removeAll(c));
    assertTrue(createForTestingResult.isEmpty());
  }

  /**
   * Test {@link NonNullElementWrapperList#removeAll(Collection)}.
   *
   * <ul>
   *   <li>Given createForTesting.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#removeAll(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NonNullElementWrapperList.removeAll(Collection)"})
  public void testRemoveAll_givenCreateForTesting_whenArrayListAdd42_thenReturnFalse2() {
    // Arrange
    NonNullElementWrapperList<Object> createForTestingResult =
        NonNullElementWrapperList.createForTesting();

    ArrayList<Object> c = new ArrayList<>();
    c.add("42");
    c.add("42");

    // Act and Assert
    assertFalse(createForTestingResult.removeAll(c));
    assertTrue(createForTestingResult.isEmpty());
  }

  /**
   * Test {@link NonNullElementWrapperList#removeAll(Collection)}.
   *
   * <ul>
   *   <li>Given createForTesting.
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#removeAll(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NonNullElementWrapperList.removeAll(Collection)"})
  public void testRemoveAll_givenCreateForTesting_whenArrayList_thenReturnFalse() {
    // Arrange
    NonNullElementWrapperList<Object> createForTestingResult =
        NonNullElementWrapperList.createForTesting();

    // Act and Assert
    assertFalse(createForTestingResult.removeAll(new ArrayList<>()));
    assertTrue(createForTestingResult.isEmpty());
  }

  /**
   * Test {@link NonNullElementWrapperList#retainAll(Collection)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.
   *   <li>Then {@link ArrayList#ArrayList()} size is one.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#retainAll(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NonNullElementWrapperList.retainAll(Collection)"})
  public void testRetainAll_given42_whenArrayListAdd42_thenArrayListSizeIsOne() {
    // Arrange
    NonNullElementWrapperList<Object> createForTestingResult =
        NonNullElementWrapperList.createForTesting();

    ArrayList<Object> c = new ArrayList<>();
    c.add("42");

    // Act
    boolean actualRetainAllResult = createForTestingResult.retainAll(c);

    // Assert
    assertEquals(1, c.size());
    assertFalse(actualRetainAllResult);
    assertTrue(createForTestingResult.isEmpty());
  }

  /**
   * Test {@link NonNullElementWrapperList#retainAll(Collection)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.
   *   <li>Then {@link ArrayList#ArrayList()} size is two.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#retainAll(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NonNullElementWrapperList.retainAll(Collection)"})
  public void testRetainAll_given42_whenArrayListAdd42_thenArrayListSizeIsTwo() {
    // Arrange
    NonNullElementWrapperList<Object> createForTestingResult =
        NonNullElementWrapperList.createForTesting();

    ArrayList<Object> c = new ArrayList<>();
    c.add("42");
    c.add("42");

    // Act
    boolean actualRetainAllResult = createForTestingResult.retainAll(c);

    // Assert
    assertEquals(2, c.size());
    assertFalse(actualRetainAllResult);
    assertTrue(createForTestingResult.isEmpty());
  }

  /**
   * Test {@link NonNullElementWrapperList#retainAll(Collection)}.
   *
   * <ul>
   *   <li>Given createForTesting add {@code 42}.
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#retainAll(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NonNullElementWrapperList.retainAll(Collection)"})
  public void testRetainAll_givenCreateForTestingAdd42_whenArrayList_thenReturnTrue() {
    // Arrange
    NonNullElementWrapperList<Object> createForTestingResult =
        NonNullElementWrapperList.createForTesting();
    createForTestingResult.add("42");
    ArrayList<Object> c = new ArrayList<>();

    // Act and Assert
    assertTrue(createForTestingResult.retainAll(c));
    assertTrue(createForTestingResult.isEmpty());
    assertEquals(createForTestingResult, c);
  }

  /**
   * Test {@link NonNullElementWrapperList#retainAll(Collection)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then {@link ArrayList#ArrayList()} is createForTesting.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#retainAll(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NonNullElementWrapperList.retainAll(Collection)"})
  public void testRetainAll_whenArrayList_thenArrayListIsCreateForTesting() {
    // Arrange
    NonNullElementWrapperList<Object> createForTestingResult =
        NonNullElementWrapperList.createForTesting();
    ArrayList<Object> c = new ArrayList<>();

    // Act and Assert
    assertFalse(createForTestingResult.retainAll(c));
    assertTrue(createForTestingResult.isEmpty());
    assertEquals(createForTestingResult, c);
  }

  /**
   * Test {@link NonNullElementWrapperList#contains(Object)}.
   *
   * <ul>
   *   <li>Given createForTesting add {@code 42}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#contains(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NonNullElementWrapperList.contains(Object)"})
  public void testContains_givenCreateForTestingAdd42_thenReturnTrue() {
    // Arrange
    NonNullElementWrapperList<Object> createForTestingResult =
        NonNullElementWrapperList.createForTesting();
    createForTestingResult.add("42");

    // Act and Assert
    assertTrue(createForTestingResult.contains("42"));
  }

  /**
   * Test {@link NonNullElementWrapperList#contains(Object)}.
   *
   * <ul>
   *   <li>Given createForTesting.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link NonNullElementWrapperList#contains(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NonNullElementWrapperList.contains(Object)"})
  public void testContains_givenCreateForTesting_thenReturnFalse() {
    // Arrange
    NonNullElementWrapperList<Object> createForTestingResult =
        NonNullElementWrapperList.createForTesting();

    // Act and Assert
    assertFalse(createForTestingResult.contains("42"));
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
    NonNullElementWrapperList<Object> createForTestingResult =
        NonNullElementWrapperList.createForTesting();

    // Act and Assert
    assertEquals(-1, createForTestingResult.indexOf("42"));
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
    NonNullElementWrapperList<Object> createForTestingResult =
        NonNullElementWrapperList.createForTesting();

    // Act and Assert
    assertEquals(-1, createForTestingResult.lastIndexOf("42"));
  }

  /**
   * Test {@link NonNullElementWrapperList#toArray()}.
   *
   * <p>Method under test: {@link NonNullElementWrapperList#toArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object[] NonNullElementWrapperList.toArray()"})
  public void testToArray() {
    // Arrange
    NonNullElementWrapperList<Object> createForTestingResult =
        NonNullElementWrapperList.createForTesting();

    // Act and Assert
    assertEquals(0, createForTestingResult.toArray().length);
  }

  /**
   * Test {@link NonNullElementWrapperList#toArray(Object[])} with {@code Object[]}.
   *
   * <p>Method under test: {@link NonNullElementWrapperList#toArray(Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object[] NonNullElementWrapperList.toArray(Object[])"})
  public void testToArrayWithObject() {
    // Arrange
    NonNullElementWrapperList<Object> createForTestingResult =
        NonNullElementWrapperList.createForTesting();
    Object[] a = new Object[] {"42"};

    // Act
    Object[] actualToArrayResult = createForTestingResult.toArray(a);

    // Assert
    assertNull(a[0]);
    assertEquals(1, a.length);
    assertSame(a, actualToArrayResult);
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean NonNullElementWrapperList.equals(Object)",
    "int NonNullElementWrapperList.hashCode()"
  })
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    NonNullElementWrapperList<Object> createForTestingResult =
        NonNullElementWrapperList.createForTesting();
    NonNullElementWrapperList<Object> createForTestingResult2 =
        NonNullElementWrapperList.createForTesting();

    // Act and Assert
    assertEquals(createForTestingResult, createForTestingResult2);
    assertEquals(createForTestingResult.hashCode(), createForTestingResult2.hashCode());
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean NonNullElementWrapperList.equals(Object)",
    "int NonNullElementWrapperList.hashCode()"
  })
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual2() {
    // Arrange
    NonNullElementWrapperList<Object> createForTestingResult =
        NonNullElementWrapperList.createForTesting();
    createForTestingResult.add("42");

    NonNullElementWrapperList<Object> createForTestingResult2 =
        NonNullElementWrapperList.createForTesting();
    createForTestingResult2.add("42");

    // Act and Assert
    assertEquals(createForTestingResult, createForTestingResult2);
    assertEquals(createForTestingResult.hashCode(), createForTestingResult2.hashCode());
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean NonNullElementWrapperList.equals(Object)",
    "int NonNullElementWrapperList.hashCode()"
  })
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    NonNullElementWrapperList<Object> createForTestingResult =
        NonNullElementWrapperList.createForTesting();

    // Act and Assert
    assertEquals(createForTestingResult, createForTestingResult);
    int expectedHashCodeResult = createForTestingResult.hashCode();
    assertEquals(expectedHashCodeResult, createForTestingResult.hashCode());
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean NonNullElementWrapperList.equals(Object)",
    "int NonNullElementWrapperList.hashCode()"
  })
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    NonNullElementWrapperList<Object> createForTestingResult =
        NonNullElementWrapperList.createForTesting();
    createForTestingResult.add("42");
    NonNullElementWrapperList<Object> createForTestingResult2 =
        NonNullElementWrapperList.createForTesting();

    // Act and Assert
    assertNotEquals(createForTestingResult, createForTestingResult2);
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean NonNullElementWrapperList.equals(Object)",
    "int NonNullElementWrapperList.hashCode()"
  })
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    NonNullElementWrapperList<Object> createForTestingResult =
        NonNullElementWrapperList.createForTesting();

    // Act and Assert
    assertNotEquals(createForTestingResult, null);
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean NonNullElementWrapperList.equals(Object)",
    "int NonNullElementWrapperList.hashCode()"
  })
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    NonNullElementWrapperList<Object> createForTestingResult =
        NonNullElementWrapperList.createForTesting();

    // Act and Assert
    assertNotEquals(createForTestingResult, "Different type to NonNullElementWrapperList");
  }
}
