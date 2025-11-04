package com.google.gson.typeadapters;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import org.mockito.Mockito;

public class RuntimeTypeAdapterFactoryDiffblueTest {
  /** Method under test: {@link RuntimeTypeAdapterFactory#recognizeSubtypes()} */
  @Test
  public void testRecognizeSubtypes() {
    // Arrange
    Class<Object> baseType = Object.class;
    RuntimeTypeAdapterFactory<Object> ofResult = RuntimeTypeAdapterFactory.of(baseType);

    // Act and Assert
    assertSame(ofResult, ofResult.recognizeSubtypes());
  }

  /** Method under test: {@link RuntimeTypeAdapterFactory#registerSubtype(Class)} */
  @Test
  public void testRegisterSubtype() {
    // Arrange
    Class<Object> baseType = Object.class;
    RuntimeTypeAdapterFactory<Object> ofResult = RuntimeTypeAdapterFactory.of(baseType);
    Class<Object> type = Object.class;

    // Act and Assert
    assertSame(ofResult, ofResult.registerSubtype(type));
  }

  /** Method under test: {@link RuntimeTypeAdapterFactory#registerSubtype(Class, String)} */
  @Test
  public void testRegisterSubtype2() {
    // Arrange
    Class<Object> baseType = Object.class;
    RuntimeTypeAdapterFactory<Object> ofResult = RuntimeTypeAdapterFactory.of(baseType);
    Class<Object> type = Object.class;

    // Act and Assert
    assertSame(ofResult, ofResult.registerSubtype(type, "Label"));
  }

  /** Method under test: {@link RuntimeTypeAdapterFactory#create(Gson, TypeToken)} */
  @Test
  public void testCreate() {
    // Arrange
    Class<Object> baseType = Object.class;
    RuntimeTypeAdapterFactory<Object> ofResult = RuntimeTypeAdapterFactory.of(baseType);
    Gson gson = new Gson();
    TypeToken<Object> type = mock(TypeToken.class);
    Class<Object> forNameResult = Object.class;
    Mockito.<Class<? super Object>>when(type.getRawType()).thenReturn(forNameResult);

    // Act
    ofResult.create(gson, type);

    // Assert
    verify(type).getRawType();
  }
}
