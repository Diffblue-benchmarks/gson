package com.google.gson.interceptors;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.bind.ArrayTypeAdapter;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import org.mockito.Mockito;

public class InterceptorFactoryDiffblueTest {
  /** Method under test: {@link InterceptorFactory#create(Gson, TypeToken)} */
  @Test
  public void testCreate() {
    // Arrange
    InterceptorFactory interceptorFactory = new InterceptorFactory();
    Gson gson = new Gson();
    TypeToken<Object> type = mock(TypeToken.class);
    Class<Object> forNameResult = Object.class;
    Mockito.<Class<? super Object>>when(type.getRawType()).thenReturn(forNameResult);

    // Act
    TypeAdapter<Object> actualCreateResult = interceptorFactory.create(gson, type);

    // Assert
    verify(type).getRawType();
    assertNull(actualCreateResult);
  }

  /**
   * Method under test: {@link InterceptorFactory.InterceptorAdapter#InterceptorAdapter(TypeAdapter,
   * Intercept)}
   */
  @Test
  public void testInterceptorAdapterNewInterceptorAdapter() {
    // Arrange
    Gson context = new Gson();
    TypeAdapter<Object> componentTypeAdapter = mock(TypeAdapter.class);
    Class<Object> componentType = Object.class;
    ArrayTypeAdapter<Object> delegate =
        new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType);

    Intercept intercept = mock(Intercept.class);
    Class<JsonPostDeserializer> forNameResult = JsonPostDeserializer.class;
    Mockito.<Class<? extends JsonPostDeserializer>>when(intercept.postDeserialize())
        .thenReturn(forNameResult);

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () -> new InterceptorFactory.InterceptorAdapter<>(delegate, intercept));

    verify(intercept).postDeserialize();
  }

  /**
   * Method under test: {@link InterceptorFactory.InterceptorAdapter#InterceptorAdapter(TypeAdapter,
   * Intercept)}
   */
  @Test
  public void testInterceptorAdapterNewInterceptorAdapter2() {
    // Arrange
    Gson context = new Gson();
    TypeAdapter<Object> componentTypeAdapter = mock(TypeAdapter.class);
    Class<Object> componentType = Object.class;
    ArrayTypeAdapter<Object> delegate =
        new ArrayTypeAdapter<>(context, componentTypeAdapter, componentType);

    Intercept intercept = mock(Intercept.class);
    Mockito.<Class<? extends JsonPostDeserializer>>when(intercept.postDeserialize())
        .thenThrow(new RuntimeException("foo"));

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () -> new InterceptorFactory.InterceptorAdapter<>(delegate, intercept));

    verify(intercept).postDeserialize();
  }
}
