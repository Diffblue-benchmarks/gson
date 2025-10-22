package com.google.gson.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.graph.GraphAdapterBuilder.Factory;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.HashMap;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class GraphAdapterBuilderDiffblueTest {
  /**
   * Test {@link GraphAdapterBuilder#addType(Type, InstanceCreator)} with {@code type}, {@code
   * instanceCreator}.
   *
   * <ul>
   *   <li>Then return {@link GraphAdapterBuilder} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link GraphAdapterBuilder#addType(Type, InstanceCreator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"GraphAdapterBuilder GraphAdapterBuilder.addType(Type, InstanceCreator)"})
  public void testAddTypeWithTypeInstanceCreator_thenReturnGraphAdapterBuilder() {
    // Arrange
    GraphAdapterBuilder graphAdapterBuilder = new GraphAdapterBuilder();
    Class<Object> type = Object.class;

    // Act
    GraphAdapterBuilder actualAddTypeResult =
        graphAdapterBuilder.addType(type, mock(InstanceCreator.class));

    // Assert
    assertSame(graphAdapterBuilder, actualAddTypeResult);
  }

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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"GraphAdapterBuilder GraphAdapterBuilder.addType(Type)"})
  public void testAddTypeWithType_whenJavaLangObject_thenReturnGraphAdapterBuilder() {
    // Arrange
    GraphAdapterBuilder graphAdapterBuilder = new GraphAdapterBuilder();
    Class<Object> type = Object.class;

    // Act
    GraphAdapterBuilder actualAddTypeResult = graphAdapterBuilder.addType(type);

    // Assert
    assertSame(graphAdapterBuilder, actualAddTypeResult);
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Factory.createInstance(Type)"})
  public void testFactoryCreateInstance_whenNull_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new Factory(new HashMap<>()).createInstance(null));
  }

  /**
   * Test Factory {@link Factory#create(Gson, TypeToken)}.
   *
   * <ul>
   *   <li>Given {@code Object}.
   *   <li>When {@link Gson#Gson()}.
   *   <li>Then return toJson {@code Value} is {@code {"0x1":"Value"}}.
   * </ul>
   *
   * <p>Method under test: {@link Factory#create(Gson, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"com.google.gson.TypeAdapter Factory.create(Gson, TypeToken)"})
  public void testFactoryCreate_givenJavaLangObject_whenGson_thenReturnToJsonValueIs0x1Value() {
    // Arrange
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    Class<Object> forNameResult = Object.class;
    instanceCreators.put(forNameResult, mock(InstanceCreator.class));
    Factory factory = new Factory(instanceCreators);
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> type2 = TypeToken.get(type);

    // Act and Assert
    assertEquals("{\"0x1\":\"Value\"}", factory.create(gson, type2).toJson("Value"));
  }

  /**
   * Test Factory {@link Factory#create(Gson, TypeToken)}.
   *
   * <ul>
   *   <li>When {@link Gson#Gson()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Factory#create(Gson, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"com.google.gson.TypeAdapter Factory.create(Gson, TypeToken)"})
  public void testFactoryCreate_whenGson_thenReturnNull() {
    // Arrange
    Factory factory = new Factory(new HashMap<>());
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> type2 = TypeToken.get(type);

    // Act and Assert
    assertNull(factory.create(gson, type2));
  }

  /**
   * Test {@link GraphAdapterBuilder#registerOn(GsonBuilder)}.
   *
   * <ul>
   *   <li>Given {@link GraphAdapterBuilder} (default constructor).
   *   <li>Then calls {@link GsonBuilder#registerTypeAdapterFactory(TypeAdapterFactory)}.
   * </ul>
   *
   * <p>Method under test: {@link GraphAdapterBuilder#registerOn(GsonBuilder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void GraphAdapterBuilder.registerOn(GsonBuilder)"})
  public void testRegisterOn_givenGraphAdapterBuilder_thenCallsRegisterTypeAdapterFactory() {
    // Arrange
    GraphAdapterBuilder graphAdapterBuilder = new GraphAdapterBuilder();

    GsonBuilder gsonBuilder = mock(GsonBuilder.class);
    when(gsonBuilder.registerTypeAdapterFactory(Mockito.<TypeAdapterFactory>any()))
        .thenReturn(new GsonBuilder());

    // Act
    graphAdapterBuilder.registerOn(gsonBuilder);

    // Assert
    verify(gsonBuilder).registerTypeAdapterFactory(isA(TypeAdapterFactory.class));
  }

  /**
   * Test {@link GraphAdapterBuilder#registerOn(GsonBuilder)}.
   *
   * <ul>
   *   <li>Then calls {@link GsonBuilder#registerTypeAdapter(Type, Object)}.
   * </ul>
   *
   * <p>Method under test: {@link GraphAdapterBuilder#registerOn(GsonBuilder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void GraphAdapterBuilder.registerOn(GsonBuilder)"})
  public void testRegisterOn_thenCallsRegisterTypeAdapter() {
    // Arrange
    GraphAdapterBuilder graphAdapterBuilder = new GraphAdapterBuilder();
    Class<Object> type = Object.class;
    graphAdapterBuilder.addType(type);

    GsonBuilder gsonBuilder = mock(GsonBuilder.class);
    when(gsonBuilder.registerTypeAdapter(Mockito.<Type>any(), Mockito.<Object>any()))
        .thenReturn(new GsonBuilder());
    when(gsonBuilder.registerTypeAdapterFactory(Mockito.<TypeAdapterFactory>any()))
        .thenReturn(new GsonBuilder());

    // Act
    graphAdapterBuilder.registerOn(gsonBuilder);

    // Assert
    verify(gsonBuilder).registerTypeAdapter(isA(Type.class), isA(Object.class));
    verify(gsonBuilder).registerTypeAdapterFactory(isA(TypeAdapterFactory.class));
  }
}
