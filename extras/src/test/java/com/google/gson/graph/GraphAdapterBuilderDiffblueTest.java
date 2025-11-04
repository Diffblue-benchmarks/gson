package com.google.gson.graph;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;

import com.diffblue.cover.annotations.MaintainedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.graph.GraphAdapterBuilder.Factory;
import java.lang.reflect.Type;
import java.util.HashMap;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class GraphAdapterBuilderDiffblueTest {
  /**
   * Test {@link GraphAdapterBuilder#addType(Type)} with {@code type}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return {@link GraphAdapterBuilder} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link GraphAdapterBuilder#addType(Type)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"GraphAdapterBuilder GraphAdapterBuilder.addType(Type)"})
  public void testAddTypeWithType_whenJavaLangObject_thenReturnGraphAdapterBuilder() {
    // Arrange
    GraphAdapterBuilder graphAdapterBuilder = new GraphAdapterBuilder();
    Class<Object> type = Object.class;

    // Act and Assert
    assertSame(graphAdapterBuilder, graphAdapterBuilder.addType(type));
  }

  /**
   * Test Factory {@link Factory#createInstance(Type)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link Factory#createInstance(Type)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"Object Factory.createInstance(Type)"})
  public void testFactoryCreateInstance_whenNull_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> (new Factory(new HashMap<>())).createInstance(null));
  }
}
