package com.google.gson.interceptors;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.interceptors.InterceptorFactory.InterceptorAdapter;
import com.google.gson.internal.bind.ArrayTypeAdapter;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class InterceptorFactoryDiffblueTest {
  /**
   * Test {@link InterceptorFactory#create(Gson, TypeToken)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link InterceptorFactory#create(Gson, TypeToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TypeAdapter InterceptorFactory.create(Gson, TypeToken)"})
  public void testCreate_whenJavaLangObject_thenReturnNull() {
    // Arrange
    InterceptorFactory interceptorFactory = new InterceptorFactory();
    Gson gson = new Gson();
    Class<Object> type = Object.class;
    TypeToken<Object> type2 = TypeToken.get(type);

    // Act and Assert
    assertNull(interceptorFactory.create(gson, type2));
  }

  /**
   * Test InterceptorAdapter {@link InterceptorAdapter#InterceptorAdapter(TypeAdapter, Intercept)}.
   *
   * <p>Method under test: {@link InterceptorAdapter#InterceptorAdapter(TypeAdapter, Intercept)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void InterceptorAdapter.<init>(TypeAdapter, Intercept)"})
  public void testInterceptorAdapterNewInterceptorAdapter() {
    // Arrange
    Gson context = new Gson();
    TypeAdapter<Object> componentTypeAdapter = mock(TypeAdapter.class);
    Class<Object> componentType = Object.class;
    ArrayTypeAdapter<Object> delegate =
        new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType);

    Intercept intercept = mock(Intercept.class);
    Class<JsonPostDeserializer> forNameResult = JsonPostDeserializer.class;
    org.mockito.Mockito.<Class<? extends JsonPostDeserializer>>when(intercept.postDeserialize())
        .thenReturn(forNameResult);

    // Act and Assert
    assertThrows(RuntimeException.class, () -> new InterceptorAdapter<>(delegate, intercept));

    verify(intercept).postDeserialize();
  }

  /**
   * Test InterceptorAdapter {@link InterceptorAdapter#InterceptorAdapter(TypeAdapter, Intercept)}.
   *
   * <ul>
   *   <li>Given {@link RuntimeException#RuntimeException(String)} with {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link InterceptorAdapter#InterceptorAdapter(TypeAdapter, Intercept)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void InterceptorAdapter.<init>(TypeAdapter, Intercept)"})
  public void testInterceptorAdapterNewInterceptorAdapter_givenRuntimeExceptionWithFoo() {
    // Arrange
    Gson context = new Gson();
    TypeAdapter<Object> componentTypeAdapter = mock(TypeAdapter.class);
    Class<Object> componentType = Object.class;
    ArrayTypeAdapter<Object> delegate =
        new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType);

    Intercept intercept = mock(Intercept.class);
    org.mockito.Mockito.<Class<? extends JsonPostDeserializer>>when(intercept.postDeserialize())
        .thenThrow(new RuntimeException("foo"));

    // Act and Assert
    assertThrows(RuntimeException.class, () -> new InterceptorAdapter<>(delegate, intercept));

    verify(intercept).postDeserialize();
  }
}
